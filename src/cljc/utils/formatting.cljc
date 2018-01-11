(ns utils.formatting
  (:require [clojure.string :as string]))

(defn- is-capital
  [s]
  (= s (string/capitalize s)))

(defn- boolean-name->db
  [kw]
  (if-not (string/ends-with? kw "?")
    kw
    (keyword (str "is_" (string/replace (name kw) #"\?$" "")))))

(defn- db->boolean-name
  [kw]
  (if-not (string/starts-with? (name kw) "is_")
    kw
    (keyword (str (string/replace (name kw) #"^is_" "") "?"))))

(defn- boolean-name->json
  [kw]
  (if-not (string/ends-with? kw "?")
    kw
    (keyword (str "is" (string/capitalize (string/replace (name kw)
                                                          #"\?$"
                                                          ""))))))

;; TODO finish
(defn- json->boolean-name
  [kw])

(defn- json->boolean-name
  [kw])

(defn- kebab->snake
  [kw]
  (keyword (string/replace (name kw) #"-" "_")))

(defn- kebab->camel
  [kw]
  (as-> (name kw) _
      (string/split _ #"-")
      (map-indexed (fn [i word] (if (zero? i) word (string/capitalize word))) _)
      (string/join "" _)
      (keyword _)))

(defn clj->db
  [m]
  (into {}
        (comp (map (fn [[k v]] [(boolean-name->db k) v]))
              (map (fn [[k v]] [(kebab->snake k) v])))
        m))

(defn db->clj
  [m])

(defn clj->json
  [m])

(defn json->clj
  [m])