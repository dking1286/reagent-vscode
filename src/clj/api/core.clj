(ns api.core
  (:require [clojure.pprint :refer [pprint]]
            [environ.core :refer [env]])
  (:gen-class))

(defn -main
  [& args]
  (println "Running the main function")
  (println "The environment is")
  (println (pprint env)))