(ns state.io.xforms)

(defn filter-types
  [& types]
  (filter (fn [action] (contains? types (:type action)))))