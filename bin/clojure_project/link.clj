(ns clojure-project.link
  (:require [clj-http.client :as client])
  (:require [clj-time.core :as time])
  (:require [clojure.string :as string])
  )

(defn extract-time [date]
  (str (time/year date) "-" (time/month date) "-" (time/day date)))

(defn compact-time [date]
 (reduce (fn [a b]
           (+ a (java.lang.Integer/parseInt b))
           )
   0 (string/split date (re-pattern "-"))))
(defn call-curr-url 
  [curr-from curr-to]
    (client/get (str "http://currencies.apps.grandtrunk.net/getrange/2012-01-01/" (extract-time (time/now)) "/" curr-from "/" curr-to))
  )

(defn extract-info 
  [curr-from curr-to]
  (try (string/split-lines (:body (call-curr-url curr-from curr-to)))
    (catch Exception e (str "Exception: " (.getMessage e)))
    )
  )

(defn info-to-map [info]
  (reduce (fn [a b]
            (assoc a 
                   (get (string/split b (re-pattern " ")) 0)
                   (Double/parseDouble (get (string/split b (re-pattern " ")) 1))
            ))
          (hash-map) info))

