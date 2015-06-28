(ns f-it-up-friday.util.io
  (:require [clojure.java.io :as io :refer [writer reader file]]))

(def extentions {:javascript "js"
                 :ruby       "rb"
                 :java       "java"})

(def cwd (System/getProperty "user.dir"))

;(defn write-file [name & [ext]]
;  (let [file-name (str cwd "/" name "." (or ext (:javascript extentions)))]
;    (if-not (.exists (io/as-file file-name))
;      "File Exists! Won't overwrite.")))
;;will move this to a utility location eventually

(defn write-content
  [path file-name content]
  (spit
    (str "./"
         path
         "/"
         file-name)
    content))