(ns services.network
  (:require [clojure.core.async :refer [chan <! >!]
                                :refer-macros [go]]
            [cljs-http.client :as http]))



