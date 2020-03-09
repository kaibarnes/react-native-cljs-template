(ns rnshadowcljs.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :count
 (fn [db _]
   (:count db)))
