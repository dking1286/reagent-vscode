(ns db.models.todos
  (:require [honeysql.core :as sql]
            [honeysql.helpers :as h]
            [db.core :as db]))

(defn get-by-id
  [id]
  (-> (h/select :*)
      (h/from :todos)
      (h/where [:= :id id])))

(defn list
  []
  (-> (h/select :*)
      (h/from :todos)))

(defn create
  [data]
  (-> (h/insert-into :todos)
      (h/columns :title :body)
      (h/values [[(:title data) (:body data)]])))