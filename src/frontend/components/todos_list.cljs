(ns frontend.components.todos-list
  (:require [frontend.components.todos-list-item :refer [todos-list-item]]))

(defn todos-list
  [{:keys [todos/list]}]
  [:div.todos-list__container
    [:h2 "Todos List"]
    (map-indexed (fn [i todo] [todos-list-item {:todo todo :key i}]) list)])