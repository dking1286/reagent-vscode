(ns api.middleware.json
  (:require [clojure.data.json :as json]))

; (defn- request->json
;   [request]
;   (let [body (:body request)]
;     (if (nil? body)
;       request
;       (assoc request :body (json/read-str body :key-fn keyword)))))

; (defn- response->json
;   [response]
;   (let [body (:body response)]
;     (if (coll? body)
;       (assoc response :body (json/write-str body))
;       response)))

; (defn wrap-json-request-body
;   [handler]
;   (fn
;     ([req]
;       (handler (request->json req)))
;     ([req respond raise]
;       (handler (request->json req) respond raise))))

; (defn wrap-json-response
;   [handler]
;   (fn
;     ([req]
;       (let [response (handler req)]
;         (response->json response)))
;     ([req respond raise]
;       (let [respond-with-json (fn [response]
;                                 (respond (response->json response)))]
;         (handler req respond-with-json raise)))))

(defn- json-request?
  [req]
  (if-let [type (get-in req [:headers "content-type"])]
    (= type "application/json")))

(defn- request->json
  [req]
  (if-not (json-request? req)
    req
    (->> req
         :body
         slurp
         json/read-str
         (into {} (map (fn [[k v]] [(keyword k) v])))
         (assoc req :body))))

(defn wrap-json-request-body
  [handler]
  (fn [req respond raise]
    (handler (request->json req) respond raise)))


