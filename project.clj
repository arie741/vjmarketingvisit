(defproject vjmarketingvisit "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [org.clojure/java.jdbc "0.7.3"]
                 [clj-postgresql "0.7.0"]
                 [enlive "1.1.6"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler vjmarketingvisit.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})