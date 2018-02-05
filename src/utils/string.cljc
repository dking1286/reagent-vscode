(ns utils.string
  (:require [clojure.string :as string]))

(defn left-pad-zeros
  [s num-digits]
  (let [num-zeros (- num-digits (count s))]
    (str (apply str (repeat (max 0 num-zeros) "0")) s)))

(defn remove-left-pad-zeros
  [s]
  (string/replace s #"^0+" ""))