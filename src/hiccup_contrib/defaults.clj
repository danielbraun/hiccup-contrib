(ns hiccup-contrib.defaults
  (:require [taoensso.tower :as tower]
            [hiccup compiler core])
  (:import [java.util Locale]))

(defn locale [] (or tower/*locale* (java.util.Locale/getDefault)))

(extend-protocol hiccup.compiler/HtmlRenderer
  java.lang.Number
  (render-html [this]
    (tower/fmt (locale) this))

  java.util.Date
  (render-html [this]
    (hiccup.core/html
     (let [v (tower/fmt (locale) this)]
       [:time {:datetime (str (.toInstant this))
               :data-local :time-ago
               :title v}
        v]))))
