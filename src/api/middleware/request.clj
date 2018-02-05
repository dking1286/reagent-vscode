(ns api.middleware.request
  (:import [java.io ByteArrayInputStream])
  (:require [cognitect.transit :as transit]
            [api.utils.response :refer [bad-request]]))

(defn- get-reader-type
  [req]
  (condp = (-> req :headers (get "content-type"))
    "application/json" :json-verbose
    "application/transit+json" :json
    :json-verbose))

(defn- parse-request-body
  [body reader-type]
  (let [reader (transit/reader body reader-type)]
    (transit/read reader)))

(defn- wrapped-req
  [req]
  (as-> (:body req) _
      (parse-request-body _ (get-reader-type req))
      (assoc req :body _)))


(defn wrap-json-request-body
  [handler]
  (fn [req respond raise]
    (cond
      (or (= (:request-method req) :get)
          (= (:request-method req) :delete))
      (handler req respond raise)
      
      (nil? (get req :content-type)) ;; FIXME: Check for valid content types
      (respond (bad-request "Missing content-type header in request"))
      
      :else
      (handler (wrapped-req req) respond raise))))