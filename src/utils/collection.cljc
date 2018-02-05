(ns utils.collection)
    
(defn transform-keys
  [c key-map]
  (into {}
        (map (fn [[k v]]
               (if (contains? key-map k)
                 [(get key-map k) v]
                 [k v])))
        c))