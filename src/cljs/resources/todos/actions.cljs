(ns resources.todos.actions
  (:require-macros [frontend.utils.actions :refer [defactions]]))

(defactions todo-actions
  get-todos
  get-todos-success
  get-todos-failure
  create-todo
  create-todo-success
  create-todo-failure
  delete-todo
  delete-todo-success
  delete-todo-failure)