(ns clojure-project.neural
  (:require [clojure-project.link :as link])
  (:require [clojure.string :as string])
  (:require [clj-time.core :as time])
  (:import (org.neuroph.nnet MultiLayerPerceptron)
           (org.neuroph.core.data DataSet)
           (org.neuroph.core.data DataSetRow)
           )
  )

(defn create-network []
  (let [mlp (new MultiLayerPerceptron (int-array [1 4 9 4 1]))]
    (doto (.getLearningRule mlp)
      (.setMaxError 0.01)
      (.setLearningRate 0.7)
      (.setMaxIterations 10000))
    mlp))

(defn compact-time-str [str]
  (link/compact-time (get (string/split str (re-pattern " ")) 0)))

(defn make-train-map [curr-from curr-to]
 (reduce (fn [a b]
           (if (nil? (get a (compact-time-str b)))
            (assoc a 
                   (compact-time-str b) 
                   (Double/parseDouble (get (string/split b (re-pattern " ")) 1)))
            (assoc a 
                   (compact-time-str b) 
                   (/ (+ 
                        (Double/parseDouble (get (string/split b (re-pattern " ")) 1))
                        (get a (compact-time-str b))
                        ) 2)
             )
            )
          )
        (hash-map) (link/extract-info curr-from curr-to))
 )

(defn training-set [curr-from curr-to]
    (reduce (fn [a b]
              (do
                (.addRow a (new DataSetRow (double-array [(key b)]) (double-array [(val b)])))              
                a
              )
            ) (new DataSet 1 1) (make-train-map curr-from curr-to))
  )

(defn get-network-value [curr-from curr-to time]
  (let [training-set (training-set curr-from curr-to)
        future (link/compact-time (link/extract-time (time/plus (time/now) (time/days time))))
        network (doto (create-network)
                  (.learn training-set)
                  (.setInput (double-array (vector future)))
                  (.calculate))]
    (first (.getOutput network)))
  )