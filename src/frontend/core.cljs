(ns frontend.core
  (:require [goog.dom :as gdom]
            [reagent.core :as r]
            [frontend.state.core] ;; Ensure that the state generating code is evaluated
            [resources.todos.reducer] ;; Ensure that all multimethod cases are added to the reducer
            [frontend.components.root :refer [root]]))

(enable-console-print!)

(r/render
  [root]
  (gdom/getElement "root"))