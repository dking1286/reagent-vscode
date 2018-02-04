(ns utils.formatting-test
  (:require [clojure.test :refer :all]
            [utils.formatting :refer :all]))

(deftest to-db
  (testing "utils.formatting/clj->db"
    (testing "should transform boolean values correctly"
      (is (= (clj->db {:done? true})
             {:is_done true})))
    (testing "should transform kebab case into snake case"
      (is (= (clj->db {:first-name "Daniel" :last-name "King"})
             {:first_name "Daniel" :last_name "King"})))))