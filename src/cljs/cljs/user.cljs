(ns cljs.user
  (:require [state.core]
            [services.network]))

(def state state.core/state)
(def dispatch state.core/dispatch)

(def get services.network/get)