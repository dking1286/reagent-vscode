(ns frontend.state.reducer)

(defmulti reducer (fn [state action] (:type action)))