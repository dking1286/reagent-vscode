(ns reagent-vscode.core
  (:require [goog.dom :as gdom]
            [reagent.core :as r]
            [state.core] ;; Ensure that the state generating code is evaluated
            [resources.core] ;; Ensure that all multimethod cases are added to the reducer
            [components.root.core :refer [root]]))

(enable-console-print!)

(r/render
  [root]
  (gdom/getElement "root"))