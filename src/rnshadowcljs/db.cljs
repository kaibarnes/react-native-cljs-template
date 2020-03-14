(ns rnshadowcljs.db
  (:require [cljs.spec.alpha :as s]))

(s/def ::count int?)

(s/def ::db (s/keys :req-un [::count]))

(def default-db
  {:count 0})
