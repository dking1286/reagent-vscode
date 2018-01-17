(ns components.root.core
  (:require [containers.todos-list-container.core :refer [todos-list-container]]
            [components.new-todo-form.core :refer [new-todo-form]]))

(defn root
  []
  [:div.root__container
    [:h1 "Root"]
    [todos-list-container]
    [new-todo-form]])