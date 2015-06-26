(ns f-it-up-friday.core
  (:require [clojure.tools.cli :refer [cli]]
            [clojure.java.io :refer [writer reader file]])
  (:gen-class))

(def version-number 0.1)

(def help
  ["-h" "--help" "Print this help"
    :flag false])

(def cwd
  (System/getProperty "user.dir"))

(def version
  ["-v" "--version" "Print Version"
   :default false])

(def create
  ["-c" "--create" "Create Controller"
   :parse-fn #()])


(defn write-file [name ext]
  (writer (file (str cwd "/" name ext))))

(defn -main [& args]

  (let [[opts args banner]
        (cli args version help create)]

    (println opts)
    (cond
      (contains? opts :help) (print banner)
      (contains? opts :version) (str "v" version-number))
      (contains? opts :create) (write-file "foo" )))


