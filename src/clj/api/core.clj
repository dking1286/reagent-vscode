(ns api.core
  (:require [clojure.edn :as edn]
            [clojure.core.async :refer [chan go >!]]
            [environ.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.lint :refer [wrap-lint]]
            [ring.middleware.reload :refer [wrap-reload]]
            [api.middleware.request :refer [wrap-json-request-body]]
            [api.middleware.response :refer [wrap-json-response-body]]
            [api.middleware.cors :refer [wrap-cors]]
            [api.routes :refer [root-handler]])
  (:gen-class))

(def environment (get env :environment))
(def port (edn/read-string (or (get env :api-port) (get env :port))))

(def handler-with-middleware
  (-> root-handler
      wrap-json-response-body
      wrap-json-request-body
      wrap-cors))

(def app
  (if (= environment "dev")
    (-> (wrap-reload #'handler-with-middleware {:dirs ["src/clj"]})
        wrap-lint)
    handler-with-middleware))

(defn run-app
  []
  (let [output (chan)]
    (println (str "Server starting on port " port))
    (run-jetty app
               {:port port
                :join? false
                :async? true
                :configurator (fn [server] (go (>! output server)))})
    output))

(defn -main
  [& args]
  (run-app))