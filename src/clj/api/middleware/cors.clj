(ns api.middleware.cors
  (:require [clojure.string :as string]
            [environ.core :refer [env]]
            [ring.util.response :refer [response header]]))

(def cors-headers
  {"Access-Control-Allow-Origin" (:frontend-url env)
   "Access-Control-Allow-Headers" ["content-type"]
   "Access-Control-Allow-Methods" ["GET" "POST" "PATCH" "DELETE"]})

(defn- serialize-cors-header
  [[name val]]
  (if (vector? val)
    [name (string/join "," val)]
    [name val]))

(def serialized-cors-headers
  (into {} (map serialize-cors-header) cors-headers))

(defn- with-cors-headers
  [res]
  (reduce #(apply header %1 %2) res serialized-cors-headers))

(defn- wrap-respond
  [respond]
  (fn [res]
    (-> res with-cors-headers respond)))

(defn wrap-cors
  [handler]
  (fn [req respond raise]
    (let [respond-with-cors (wrap-respond respond)]
      (if (= (:request-method req) :options)
        (respond-with-cors (response nil))
        (handler req respond-with-cors raise)))))