(ns db.core
  (:require [clojure.java.jdbc :as jdbc]
            [environ.core :refer [env]]))

(def connection
  {:dbtype (:db-type env)
   :dbname (:db-name env)
   :host (:db-host env)
   :user (:db-user env)
   :password (:db-password env)})

(def query (partial jdbc/query connection))
(def execute! (partial jdbc/execute! connection))