(ns cljs.user
  (:require [clojure.core.async :refer [<!] :refer-macros [go]]
            [state.core]
            [services.network]))

(def state state.core/state)
(def dispatch state.core/dispatch)

(defn net-request
  [& args]
  (go (println (<! (apply services.network/request args)))))
