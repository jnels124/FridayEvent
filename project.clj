(defproject f-it-up-friday "0.1.0-SNAPSHOT"
            :description "FIXME: write description"
            :url "http://example.com/FIXME"
            :license {:name "Eclipse Public License"
                      :url  "http://www.eclipse.org/legal/epl-v10.html"}
            :dependencies [[org.clojure/clojure "1.6.0"]
                           [org.clojure/tools.cli "0.2.4"]]
            :main ^:skip-aot f-it-up-friday.core
            :bin {:name "lbtc"
                  :bin  "/usr/local/bin"}
            :auto-clean false
            :profiles {;; activated automatically during uberjar
                       :uberjar {:aot :all}})
