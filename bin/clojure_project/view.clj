(ns clojure-project.view
  (:use hiccup.core)
  (:use hiccup.page)
  (:use hiccup.form)
  (:require [clojure-project.statistics :as statistics])
  (:require [clojure-project.link :as link])
  (:require [clojure.string :as string])
  )

(defn layout [title & content]
  (html
    (doctype :xhtml-strict)
    (xhtml-tag "en"
      [:head
        [:meta {:http-equiv "Content-type"
                :content "text/html; charset=utf-8"}]
        [:title title]]
      [:body content])))


(defn input []
  (layout 
    "Currency"
    [:h2 "Set currency options:"]
    [:h4 "Chose two currency and check their conversion rates:"]
    (form-to [:get "/curr"]     
             (text-field {:placeholder "First currency"} "curr")
             [:p]
             (text-field {:placeholder "Second currency"} "curr")
             [:p]
             (submit-button "Confirm"))))


(defn output [curr-from curr-to]  
  (do
    (statistics/look
      "Recent behaviour"
      (link/info-to-map (link/extract-info curr-from curr-to))
      )
    (statistics/look
      "Future behaviour" {})
    (layout
     "Conversion rates"
     [:h2 (string/upper-case curr-from) " to " 
      (string/upper-case curr-to)]
     (statistics/stats 
      (link/info-to-map (link/extract-info curr-from curr-to))
      )
     [:a.action {:href "/"} "Choose another currency"])))