(ns api.middleware.development
  (:require [environ.core :refer [env]]))

(defn dev-only
  [middleware]
  (if (= "dev" (:environment env)) middleware identity))
