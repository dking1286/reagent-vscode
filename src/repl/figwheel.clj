(ns repl.figwheel
  (:require [figwheel-sidecar.repl-api :as figwheel]))

(defn start-figwheel!
  []
  (figwheel/start-figwheel! :app))

(defn stop-figwheel!
  []
  (figwheel/stop-figwheel!))

(defn cljs-repl!
  []
  (figwheel/cljs-repl :app))