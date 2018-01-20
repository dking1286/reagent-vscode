(ns api.middleware.request
  (:require [cognitect.transit :as transit]))

(defn- parse-json-request-body
  [req]
  (let [reader (transit/reader :json)
        body (transit/read reader)]
    (assoc req :body body)))

(defn wrap-json-request-body
  [handler]
  (fn [req respond raise]
    (if-not (= (-> req :headers (get "content-type"))
               "application/json")
      (handler req respond raise)
      (handler (parse-json-request-body req) respond raise))))