(ns frontend.state.core
  (:require [frontend.state.factory :refer [create-state-atom]]
            [frontend.state.initial :refer [initial-state]]
            [frontend.state.reducer :refer [reducer]]))

(defonce store
  (create-state-atom
    initial-state
    reducer
    identity))

(defonce state (:state store))
(defonce dispatch (:dispatch store))