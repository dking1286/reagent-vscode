(ns frontend.components.root
  (:require [frontend.containers.todos-list-container :refer [todos-list-container]]
            [frontend.components.new-todo-form :refer [new-todo-form]]))

(defn root
  []
  [:div.root__container
    [:h1 "Root"]
    [todos-list-container]
    [new-todo-form]])