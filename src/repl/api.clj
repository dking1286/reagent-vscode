(ns repl.api
  (:require [clojure.core.async :refer [go <!]]
            [api.core :refer [run-app]]))

(def server (atom nil))

(defn start-api!
  []
  (go
    (let [the-server (<! (run-app))]
      (swap! server (constantly the-server))))
  nil)

(defn stop-api!
  []
  (let [the-server @server]
    (println "Stopping server")
    (.stop the-server)
    (swap! server (constantly nil))
    nil))