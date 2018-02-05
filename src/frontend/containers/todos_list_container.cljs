(ns frontend.containers.todos-list-container
  (:require [frontend.state.core :refer [state dispatch]]
            [frontend.components.todos-list :refer [todos-list]]))

(defn todos-list-container
  []
  (let [{:keys [todos/list]} @state]
    [todos-list {:todos/list list}]))