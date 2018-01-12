(ns api.middleware.development
  (:require [clojure.pprint :refer [pprint]]))

(defn wrap-request-logger
  [handler]
  (fn [req]
    (pprint req)
    (handler req)))