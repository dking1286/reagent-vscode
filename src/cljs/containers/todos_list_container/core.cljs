(ns containers.todos-list-container.core
  (:require [state.core :refer [state dispatch]]
            [components.todos-list.core :refer [todos-list]]))

(defn todos-list-container
  []
  (let [{:keys [todos/list]} @state]
    [todos-list {:todos/list list}]))