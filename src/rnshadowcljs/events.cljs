(ns rnshadowcljs.events
  (:require [re-frame.core :refer [reg-event-db]]))

(def default-db
  {:count 0})

(reg-event-db
 :initialize
 (fn [_ _]
   default-db))

(reg-event-db
 :increase-count
 (fn [db _]
   (update db :count inc)))
