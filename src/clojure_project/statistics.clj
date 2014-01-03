(ns clojure-project.statistics
  (:use incanter.core)
  (:use incanter.stats)
  (:use incanter.charts)
  (:require [clojure-project.link :as link])
  (:require [clojure-project.neural :as neural])
  )


(defn look [title data]
  (view (line-chart (keys data) (vals data)
                  :title title
                  :x-label "Date"
                  :y-label "Conversion rate")
       ))

(defn stats [data curr-from curr-to]
  (vector
    :p
    (str "Mean: " (mean (vals data)))
    "</br>"
    (str "Median: " (median (vals data)))
    "</br>"
    (str "Variance: " (variance (vals data)))
    "</br>"
    (str "The sum of the squares of the difference between each observation and the sample mean: " (sum-of-square-devs-from-mean (vals data)))
    "</br>" 
    (str "Minimum: " (apply min (vals data)))
    "</br>"
    (str "Maximum: " (apply max (vals data)))
    "</br>"
    (str "Tomorrow: " (neural/get-network-value curr-from curr-to 1))
    "</br>"
    (str "Day after tomorrow: " (neural/get-network-value curr-from curr-to 2))
    "</br>"
    (str "Day after the day which is after tomorrow: " (neural/get-network-value curr-from curr-to 3))
    )
  )
