(ns frontend.utils.actions)

(defmacro defaction
  [s]
  (let [payload-sym (gensym "payload")
        context-sym (gensym "context")]
    `(defn ~s
       ([]
        {:type ~(keyword s)})
       ([~payload-sym]
        {:type ~(keyword s) :payload ~payload-sym})
       ([~payload-sym ~context-sym]
        {:type ~(keyword s) :payload ~payload-sym :context ~context-sym}))))

(defmacro defactions
  [category-sym & action-syms]
  `(do
    (def ~category-sym ~(into [] (map keyword) action-syms))
    ~@(map (fn [s] `(defaction ~s)) action-syms)))