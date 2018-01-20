(ns api.util.response
  (:require [ring.util.response :as r]))

(defn not-found
  []
  (-> (r/response "Not found")
      (r/status 404)))