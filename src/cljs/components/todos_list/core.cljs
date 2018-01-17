(ns components.todos-list.core
  (:require [components.todos-list-item.core :refer [todos-list-item]]))

(defn todos-list
  [{:keys [todos/list]}]
  [:div.todos-list__container
    [:h2 "Todos List"]
    (map-indexed (fn [i todo] [todos-list-item {:todo todo :key i}]) list)])