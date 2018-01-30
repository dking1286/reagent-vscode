(ns utils.async
  (:require [clojure.core.async :refer [>! chan] :refer-macros [go]]))

(defrecord AsyncResult
  [error value])

(defn promise-to-channel
  [promise]
  (let [out (chan)]
    (-> promise
      (.then (fn [value] (go (>! out (->AsyncResult nil value)))))
      (.catch (fn [error] (go (>! out (->AsyncResult error nil))))))
    out))