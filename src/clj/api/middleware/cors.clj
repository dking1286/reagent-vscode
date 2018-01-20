(ns api.middleware.cors
  (:require [environ.core :refer [env]]
            [ring.util.response :refer :all]))

(def frontend-url (:frontend-url env))

(defn- wrapped-respond
  [respond]
  (fn [response]
    (-> response
        (header "Access-Control-Allow-Origin" frontend-url)
        respond)))

(defn wrap-cors
  [handler]
  (fn [req respond raise]
    (handler req (wrapped-respond respond) raise)))