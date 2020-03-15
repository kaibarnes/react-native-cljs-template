(ns test.events-test
  (:require [cljs.test :refer [deftest is]]
            [rnshadowcljs.events :as events]))

(deftest events-counter-test
  (is (= (events/increase-count 1 [])
         2)))
