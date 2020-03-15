(ns rnshadowcljs.events
  (:require [re-frame.core :refer [reg-event-db path after]]
            [rnshadowcljs.db :refer [default-db]]
            [cljs.spec.alpha :as s]))

(defn check-and-throw [spec db]
  (when-not (s/valid? spec db)
    (throw (ex-info (str "spec check failed: " (s/explain-str spec db)) {}))))

(def check-spec-interceptor (after (partial check-and-throw :rnshadowcljs.db/db)))

(def count-interceptors [check-spec-interceptor
                         (path :count)])

(reg-event-db
 :initialize
 (fn [_ _]
   default-db))

(defn increase-count [count _]
  (inc count))

(reg-event-db
 :increase-count
 count-interceptors
 increase-count)
