(ns api.middleware.response
  (:import (java.io ByteArrayOutputStream))
  (:require [cognitect.transit :as transit]))

(defn- get-writer-type
  [req]
  (condp = (-> req :headers (get "accept"))
    "application/json" :json-verbose
    "application/transit+json" :json
    :json-verbose))

(defn- serialize-response-body
  [body writer-type]
  (let [out-stream (ByteArrayOutputStream.)
        writer (transit/writer out-stream writer-type)]
    (transit/write writer body)
    (.toString out-stream)))

(defn- wrapped-respond
  [req respond]
  (fn [response]
    (if (nil? (:body response))
      (respond response)
      (as-> (:body response) _
            (serialize-response-body _ (get-writer-type req))
            (assoc response :body _)
            (respond _)))))

(defn wrap-json-response-body
  [handler]
  (fn [req respond raise]
    (handler req (wrapped-respond req respond) raise)))