(ns f-it-up-friday.core
  (:require [clojure.tools.cli :refer [cli]]
            [clojure.java.io :as io :refer [writer reader file]])
  (:gen-class))

(def version-number 0.1)

;; TODO
;; [x] - err handleling for when file is already present / check if file present
;; [x] - handle --help, handle --version, --create separetely
;; [x] - handle missing arg
;; [ ] - handle mutiple options flags

(def cwd
  (System/getProperty "user.dir"))

(def framework
  (System/getenv "LBTC_FRAMEWORK"))

(def required-opts #{:create})

(def extentions {:javascript "js"
                 :ruby       "rb"
                 :java       "java"})

(def cli-options
  [["-h" "--help" "Print Help" :flag true :defaul false]
   ["-v" "--version" "Print Version" :flag true]
   ["-c" "--create" "Create new file" :flag true]])

(defn write-file [name & [ext]]
  (let [file-name (str cwd "/" name "." (or ext (:javascript extentions)))]
    (if-not (.exists (io/as-file file-name))
      "File Exists! Won't overwrite.")))

(defn missing-required? [opts]
  (not-every? required-opts opts))

(defn exit [code msg]
  (println msg)
  (System/exit code))

(defn choose-framework []
  (println "will create")
  (case framework
    "react" (println "react component")
    "angular" (println "angular controller")))

(defn -main [& args]
  (let [[options arguments errors banner] (apply cli args cli-options)]

    (println options)

    ;; enable this for target
    #_(when (or (:help options) (missing-required? options))
      ;; (exit 0 banner)
      (println banner))

    (cond
      (:version options) (println (str "v" version-number))
      (:create options) (choose-framework))))