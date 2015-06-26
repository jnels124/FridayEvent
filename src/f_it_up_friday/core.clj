(ns f-it-up-friday.core
  (:require [clojure.tools.cli :refer [cli]]
            [clojure.java.io :refer [writer reader file]])
  (:gen-class))

(def version-number 0.1)

(def args ["-c" "name" ["scope" "q" ] ["test" "s"] ])

(def help
  ["-h" "--help" "Print this help"
    :default true])

(def cwd
  (System/getProperty "user.dir"))

(def version
  ["-v" "--version" "Print Version"
   :default false])                                         ;; Print Help Banner by Default

(def create
  ["-c" "--create" "Create Controller"])

(defn write-file [name ext]
  (writer (file (str cwd "/" name "." ext))))

(defn -main [& args]

  (let [[opts args banner]
        (cli args version help create)]

    (println opts)

    (cond
      (contains? opts :help) (print banner)                 ;; -h
      (contains? opts :version) (str "v" version-number))   ;; -v
      (contains? opts :create) (write-file "foo" "js")))    ;; -c

