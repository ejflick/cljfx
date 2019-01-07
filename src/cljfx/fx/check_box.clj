(ns cljfx.fx.check-box
  (:require [cljfx.lifecycle.composite :as lifecycle.composite]
            [cljfx.lifecycle :as lifecycle]
            [cljfx.coerce :as coerce]
            [cljfx.fx.button-base :as fx.button-base])
  (:import [javafx.scene.control CheckBox]
           [javafx.scene AccessibleRole]
           [javafx.geometry Pos]))

(set! *warn-on-reflection* true)

(def lifecycle
  (lifecycle.composite/describe CheckBox
    :ctor []
    :extends [fx.button-base/lifecycle]
    :props {;; overrides
            :style-class [:list lifecycle/scalar :coerce coerce/style-class :default "check-box"]
            :accessible-role [:setter lifecycle/scalar :coerce (coerce/enum AccessibleRole)
                              :default :check-box]
            :alignment [:setter lifecycle/scalar :coerce (coerce/enum Pos) :default :center-left]
            :mnemonic-parsing [:setter lifecycle/scalar :default true]
            ;; definitions
            :allow-indeterminate [:setter lifecycle/scalar :default false]
            :indeterminate [:setter lifecycle/scalar :default false]
            :selected [:setter lifecycle/scalar :default false]
            :on-selected-changed [:property-change-listener
                                  (lifecycle/wrap-coerce lifecycle/event-handler
                                                         coerce/change-listener)]}))