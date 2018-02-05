(ns resources.todos.reducer
  (:require [frontend.state.reducer :refer [reducer]]))

(defmethod reducer :create-todo
  [state {:keys [payload]}]
  (-> state
      (update-in [:todos/list] conj payload)))