(ns api.core
  (:require [clojure.edn :as edn]
            [clojure.core.async :refer [chan go >!]]
            [environ.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.lint :refer [wrap-lint]]
            [ring.middleware.reload :refer [wrap-reload]])
  (:gen-class))

(def environment (get env :environment))
(def port (edn/read-string (or (get env :api-port) (get env :port))))

(defn handler
  [req]
  {:headers {"content-type" "text/plain"}
   :status 200
   :body "Running"})

(def handler-with-middleware
  (cond-> handler
          (= environment "dev") wrap-lint))

(def app
  (if (= environment "dev")
    (wrap-reload #'handler-with-middleware {:dirs ["src/clj"]})
    handler-with-middleware))

(defn run-app
  []
  (let [output (chan)]
    (go
      (println (str "Server starting on port " port))
      (run-jetty app
        {:port port :join? false
         :configurator (fn [server] (go (>! output server)))}))
    output))

(defn -main
  [& args]
  (run-app))