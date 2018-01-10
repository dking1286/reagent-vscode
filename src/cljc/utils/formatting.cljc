(ns utils.formatting
  (:require [clojure.string :as string]))

(defn- boolean-name->db
  [kw]
  (if-not (string/ends-with? kw "?")
    kw
    (keyword (str "is_" (string/replace (name kw) #"\?$" "")))))

(defn- boolean-name->json
  [kw]
  (if-not (string/ends-with? kw "?")
    kw
    (keyword (str "is" (string/capitalize (string/replace (name kw)
                                                          #"\?$"
                                                          ""))))))

(defn- kebab->snake
  [kw]
  (keyword (string/replace (name kw) #"-" "_")))

(defn kebab->camel
  [kw]
  (as-> (name kw) _
      (string/split _ #"-")
      (map-indexed (fn [i word] (if (zero? i) word (string/capitalize word))) _)
      (string/join "" _)
      (keyword _)))