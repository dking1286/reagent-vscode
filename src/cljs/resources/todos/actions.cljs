(ns resources.todos.actions
  (:require-macros [frontend.utils.actions :refer [defaction]]))

(defaction create-todo)
(defaction delete-todo)