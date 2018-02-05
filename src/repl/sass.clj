(ns repl.sass
  (:require [popen :refer [popen]]
            [hawk.core :as hawk]
            [clj-time.core :refer [now]]
            [clj-time.coerce :refer [to-long]]))

(declare build-sass on-src-change throttle start-sass! stop-sass!)

(def sass-index "resources/sass/styles.scss")
(def css-output "resources/public/css/styles.css")
(defonce state (atom {:sass/watcher nil}))

(defn start-sass!
  []
  (build-sass)
  (let [watcher (hawk/watch! [{:paths ["src"]
                               :handler (throttle 100 on-src-change)}])]
    (swap! state assoc-in [:sass/watcher] watcher)
    nil))

(defn stop-sass!
  []
  (let [watcher (get-in @state [:sass/watcher])]
    (hawk/stop! watcher)
    (swap! state assoc-in [:sass/watcher] nil)
    nil))

(defn- on-src-change
  [ctx e]
  (let [filename (-> e :file .getName)]
    (when (.endsWith filename ".scss")
      (build-sass)
      nil)))

(defn- build-sass
  []
  (popen ["sass" sass-index css-output])
  nil)

(defn- throttle
  [time f]
  (let [last-call-time (atom 0)]
    (fn [& args]
      (let [current-time (-> (now) to-long)
            time-delta (- current-time @last-call-time)]
        (when (>= time-delta time)
          (reset! last-call-time current-time)
          (apply f args))))))