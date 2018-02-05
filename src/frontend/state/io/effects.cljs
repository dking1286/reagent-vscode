(ns frontend.state.io.effects)

(defn call
  [f & args]
  {:type :call
   :function f
   :args args})

(defn select
  [f & args]
  {:type :select
   :function f
   :args args})

(defn put
  [action]
  {:type :put
   :action action})