(ns clojure-project.core
  (:use compojure.core)
  (:use [ring.middleware.params :only [wrap-params]])
  (:use [ring.adapter.jetty :only [run-jetty]])
  (:require [clojure.string :as string])
  (:require [clojure-project.view :as view])
  (:require [compojure.route :as route])
  (:require [compojure.handler :as handler])
  )

(defroutes app-routes
  (GET "/" []
    (view/input))

  (GET "/:id" [& param]
  (view/output (string/lower-case (get (get param "curr") 0)) 
            (string/lower-case (get (get param "curr") 1))))

  (route/not-found "Not Found")
  )

(def app (wrap-params app-routes))
