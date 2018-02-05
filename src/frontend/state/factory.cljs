(ns frontend.state.factory
  (:require [reagent.core :as r]))

(defn- create-dispatch
  [state-atom reducer middleware]
  (middleware (fn [action]
                (swap! state-atom (fn [state] (reducer state action))))))

(defn create-state-atom
  [initial-state reducer middleware]
  (let [state (r/atom initial-state)]
    {:state state
     :dispatch (create-dispatch state reducer middleware)}))