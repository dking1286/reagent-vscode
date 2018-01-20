(ns resources.todos.controller
  (:require [clojure.core.async :refer [chan go <! >!]]
            [ring.util.response :refer [response status]]
            [api.util.response :refer :all]
            [db.core :refer [query execute!]]
            [resources.todos.model :as todos]))

(defn get-all
  [req respond raise]
  (let [todos (query todos/list)]
    (respond 
     (-> (response {:data todos})))))

(defn get-one
  [id]
  (fn [req respond raise]
    (let [todos (query todos/get-by-id id)]
      (respond
        (if (empty? todos)
          (not-found)
          (response {:data (first todos)}))))))

(defn create
  [req respond raise]
  (let [data (get req :body)
        todo (query todos/create data)]
    (respond
     (-> (response {:data todo})
         (status 201)))))

(defn update-one
  [id]
  (fn [req respond raise]
    (let [data (get req :body)
          todo (query todos/update-by-id id data)]
      (respond
        (-> (response {:data todo}))))))

(defn delete-one
  [id]
  (fn [req respond raise]
    (execute! todos/delete-by-id id)
    (respond
      (-> (response nil)
          (status 204)))))