(ns user
  (:require [repl.figwheel :refer :all]
            [repl.sass :refer :all]
            [repl.migration :refer :all]
            [repl.api :refer :all]
            [repl.components :refer :all]))

(defn start-all!
  []
  (start-figwheel!)
  (start-sass!)
  (start-api!))
