(ns cljs.user
  (:require [clojure.core.async :refer [<!] :refer-macros [go]]
            [frontend.state.core]
            [frontend.services.network]))

(def state state.core/state)
(def dispatch state.core/dispatch)

(defn net-request
  [& args]
  (go (println (<! (apply frontend.services.network/request args)))))
