(ns api.middleware.development
  (:require [clojure.pprint :refer [pprint]]
            [ring.middleware.lint :refer [wrap-lint]]))

(defn wrap-request-logger
  [handler]
  (fn [req]
    (pprint req)
    (handler req)))