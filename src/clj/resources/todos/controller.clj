(ns resources.todos.controller
  (:require [clojure.core.async :refer [chan go <! >!]]
            [ring.util.response :refer :all]
            [api.utils.json :refer [json-response]]
            [db.core :refer [query execute!]]
            [resources.todos.model :as todos]))

(defn get-all
  [req respond raise]
  (go
    (let [todos (query todos/list)]
      (respond 
        (-> (json-response {:data todos})
            (content-type "text/plain"))))))

(defn create
  [req respond raise]
  (go
   (let [data (get req :body)
         todo (query todos/create data)]
     (respond
       (-> (json-response {:data todo})
           (status 201)
           (content-type "text/plain"))))))