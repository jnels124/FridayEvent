(ns f-it-up-friday.util.io
  (:require [clojure.java.io :as io :refer [writer reader file]]))

(def extentions {:javascript "js"
                 :ruby       "rb"
                 :java       "java"})

(def cwd (System/getProperty "user.dir"))

(defn write-file [name & [ext]]
  (let [file-name (str cwd "/" name "." (or ext (:javascript extentions)))]
    (if-not (.exists (io/as-file file-name))
      "File Exists! Won't overwrite.")))

(defn exit [code msg]
  (println msg)
  (System/exit code))