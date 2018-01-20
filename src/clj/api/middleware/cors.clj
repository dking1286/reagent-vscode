(ns api.middleware.cors
  (:require [environ.core :refer [env]]
            [ring.util.response :refer :all]))

(defn- wrapped-respond
  [respond]
  (fn [response]
    (-> response
        (header "Access-Control-Allow-Origin" "http://localhost:9090")
        respond)))

(defn wrap-cors
  [handler]
  (fn [req respond raise]
    (handler req (wrapped-respond respond) raise)))