(ns f-it-up-friday.core
  (:require [clojure.tools.cli :refer [cli]]
            [clojure.java.io :as io :refer [writer reader file]])
  (:gen-class))

(def version-number 0.1)

;; TODO
;; [x] - err handleling for when file is already present / check if file present
;; [ ] -

(def cwd
  (System/getProperty "user.dir"))

(def extentions {:javascript "js"
                 :ruby       "rb"
                 :java       "java"})

(def cli-options
  [["-h" "--help" "Print Help"]
   ["-v" "--version" "Print Version"]])

(defn write-file [name & [ext]]
  (let [file-name (str cwd "/" name "." (or ext (:javascript extentions)))]
    (if-not (.exists (io/as-file file-name))
      (writer (io/file file-name))
      "File Exists! Will not overwrite.")))

(defn exit [code msg]
  (println msg)
  (System/exit code))

(defn -main [& args]
  (let [{:keys [options arguments errors summary]} (cli args cli-options)]
    (cond (:help options) (exit 0 summary))))