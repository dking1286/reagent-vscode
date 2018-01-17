(ns state.core
  (:require [state.factory :refer [create-state-atom]]
            [state.initial :refer [initial-state]]
            [state.reducer :refer [reducer]]))

(defonce store
  (create-state-atom
    initial-state
    reducer
    identity))

(defonce state (:state store))
(defonce dispatch (:dispatch store))