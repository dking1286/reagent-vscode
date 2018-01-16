(ns reagent-vscode.core
  (:require [goog.dom :as gdom]
            [reagent.core :as r]
            [components.root.core :refer [root]]))

(enable-console-print!)

(r/render [root] (gdom/getElement "root"))