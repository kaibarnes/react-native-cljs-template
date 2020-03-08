(ns rnshadowcljs.core
  (:require [steroid.rn.core :as rn]
            [steroid.rn.navigation.core :as rnn]
            [steroid.rn.navigation.safe-area :as safe-area]
            [steroid.rn.navigation.bottom-tabs :as bottom-tabs]
            [steroid.rn.navigation.stack :as stack]
            steroid.rn.navigation.events
            [re-frame.core :as re-frame]))

(defn home-screen []
  [rn/safe-area-view {:flex 1}
   [rn/view {:style {:flex 1 :justify-content :center :align-items :center}}
    [rn/text "Home"]
    [rn/touchable-opacity {:on-press #(re-frame/dispatch [:navigate-to :modal])
                           :style {:margin-top 20}}
     [rn/view {:style {:background-color :gray :padding 5}}
      [rn/text "Open modal"]]]]])

(defn modal-screen []
  [rn/safe-area-view {:flex 1}
   [rn/view {:style {:flex 1 :justify-content :center :align-items :center}}
    [rn/text "This is a modal"]
    [rn/touchable-opacity {:on-press #(re-frame/dispatch [:navigate-back])
                           :style {:margin-top 20}}
     [rn/view {:style {:background-color :gray :padding 5}}
      [rn/text "Dismiss"]]]]])

(defn tabs-screen []
  (let [[navigator screen] (bottom-tabs/create-bottom-tab-navigator)
        home-comp (rn/reload-comp home-screen)]
    (fn []
      [navigator
       [screen {:name "Home" :component home-comp}]])))

(defn root-stack []
  (let [[navigator screen] (stack/create-stack-navigator)
        tabs-comp (rn/react-comp tabs-screen)
        modal-comp (rn/reload-comp modal-screen)]
    (fn []
      [safe-area/safe-area-provider
       [rnn/navigation-container {:ref rnn/nav-ref-handler}
        [navigator {:mode :modal }
         [screen {:name      :main
                  :component tabs-comp
                  :options {:title "Home"}}]
         [screen {:name      :modal
                  :component modal-comp
                  :options   {:gestureEnabled false
                              :title "Modal"}}]]]])))

(defn init []
  (rn/register-comp "RNShadowCLJS" root-stack))
