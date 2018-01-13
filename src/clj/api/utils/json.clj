(ns api.utils.json
  (:require [clojure.data.json :as json]
            [ring.util.response :refer [response]]))

(defn json-response
  [{:keys [data]}]
  (response (json/write-str {:data data})))