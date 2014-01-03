(defproject clojure-project "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [hiccup "1.0.4"]
                 [compojure "1.1.6"]
                 [ring "1.2.1"]
                 [clj-http "0.7.8"]
                 [incanter "1.5.4"]
                 [clj-time "0.6.0"]
                 [org.neuroph/neuroph-core "2.8"]
                 ]
  :plugins [[lein-ring "0.8.8"] [lein-localrepo "0.4.0"]]
  :ring {:handler clojure-project.core/app})
