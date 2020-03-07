(ns rnshadowcljs.core
  (:require [steroid.rn.core :as rn]))

(defn root-comp []
  [rn/safe-area-view
   [rn/view
    [rn/text "Hello Clojure! from CLJS"]]])

(defn init []
  (rn/register-reload-comp "RNShadowCLJS" root-comp))
