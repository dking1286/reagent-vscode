(ns state.io.impl
  (:require [clojure.core.async :refer [<! >! chan]
                                :refer-macros [go go-loop]]))

(defn io-nodes-valid?
  [node-list]
  (->> node-list
       (map :id)
       distinct
       count
       (= (count node-list))))

(defn create-nodes-map
  [node-list]
  (into {} (map (fn [{:keys [id] :as node}] [id node])) node-list))

(defn create-input-channel
  [inputs xform]) ;; FIXME finish this

(defn create-node
  [node-map {:keys [inputs xform output]}]
  {:input :output})