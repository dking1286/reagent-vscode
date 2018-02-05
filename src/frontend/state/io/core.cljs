(ns frontend.state.io.core
  (:require [frontend.state.io.effects :refer [call put select]]
            [frontend.state.io.xforms :refer [filter-types]]))

; (def io-graph
;   [{:id :root}

;    {:id :get-todos
;     :inputs [:root]
;     :xform (filter-types :get-todos)
;     :output (call network/get "todos")}
                    
;    {:id :get-todos-success
;     :inputs [:get-todos]
;     ;:xform Filter out errors somehow
;     :output (fn [input] (put (get-todos-success input)))}
    
;    {:id :get-todos-failure
;     :inputs [:get-todos]
;     ;:xform Filter only errors somehow
;     :output (fn [input] (put (get-todos-failure input)))}])