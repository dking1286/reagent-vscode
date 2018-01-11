(ns db.core
  (:require [clojure.java.jdbc :as jdbc]
            [honeysql.core :as sql]
            [environ.core :refer [env]]
            [utils.formatting :refer [db->clj clj->db]]))

(def connection
  {:dbtype (:db-type env)
   :dbname (:db-name env)
   :host (:db-host env)
   :user (:db-user env)
   :password (:db-password env)})

(defn query
  [query-fn & params]
  (let [q (sql/format (apply query-fn params))]
    (->> (apply query-fn params)
         sql/format
         (jdbc/query connection)
         (map db->clj))))

(defn execute!
  [query-fn & params]
  (->> (map #(if (map? %1) (clj->db %1) %1) params)
       (apply query-fn)
       sql/format
       (jdbc/execute! connection)))