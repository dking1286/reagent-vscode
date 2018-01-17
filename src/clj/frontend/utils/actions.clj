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