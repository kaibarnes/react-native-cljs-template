(ns rnshadowcljs.views
  (:require  [steroid.rn.core :as rn]
             [re-frame.core :as re-frame]
             [rnshadowcljs.events]
             [rnshadowcljs.subs]))

(defn button [{:keys [on-press title]}]
  [rn/touchable-opacity {:on-press on-press :style {:margin 20}}
   [rn/view {:style {:background-color :gray :padding 5}}
    [rn/text title]]])

(defn home-screen []
  [rn/safe-area-view {:style {:flex 1}}
   [rn/view {:style {:flex 1 :justify-content :center :align-items :center}}
    [rn/text "Home"]
    [rn/text "The count is " @(re-frame/subscribe [:count])]
    [button {:on-press #(re-frame/dispatch [:increase-count])
             :title "Increase"}]
    [button {:on-press #(re-frame/dispatch [:navigate-to :modal])
             :title "Open modal"}]]])

(defn modal-screen []
  [rn/safe-area-view {:style {:flex 1}}
   [rn/view {:style {:flex 1 :justify-content :center :align-items :center}}
    [rn/text "This is a modal"]
    [button {:on-press #(re-frame/dispatch [:navigate-back])
             :title "Dismiss"}]]])
