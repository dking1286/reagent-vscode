(ns db.models.todos
  (:require [honeysql.core :as sql]
            [honeysql.helpers :as h]
            [db.core :as db]
            [entities.todo :as todo]))

(defn query
  [query-fn & params]
  (let [q (sql/format (apply query-fn params))]
    (->> q
         db/query
         (map todo/from-db))))

(defn execute!
  [query-fn & params]
  (let [formatted-params (map #(if (map? %1) (todo/to-db %1) %1) params)
        q (sql/format (apply query-fn formatted-params))]
    (->> q
         db/execute!)))

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