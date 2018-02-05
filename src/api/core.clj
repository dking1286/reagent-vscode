(ns api.core
  (:require [clojure.edn :as edn]
            [clojure.core.async :refer [chan go >!]]
            [environ.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.lint :refer [wrap-lint]]
            [ring.middleware.reload :refer [wrap-reload]]
            [api.middleware.development :refer [dev-only]]
            [api.middleware.request :refer [wrap-json-request-body]]
            [api.middleware.response :refer [wrap-json-response-body]]
            [api.middleware.cors :refer [wrap-cors]]
            [api.routes :refer [root-handler]])
  (:gen-class))

(def environment (get env :environment))
(def port (edn/read-string (or (get env :api-port) (get env :port))))

(def middleware
  (comp
    (dev-only wrap-lint)
    wrap-cors
    wrap-json-request-body
    wrap-json-response-body))

(def app (middleware root-handler))

(defn run-app
  []
  (let [output (chan)]
    (println (str "Server starting on port " port))
    (run-jetty app {:port port :join? false :async? true
                    :configurator (fn [server] (go (>! output server)))})
    output))

(defn -main
  [& args]
  (run-app))