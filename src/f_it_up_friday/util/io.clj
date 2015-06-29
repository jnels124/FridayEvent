(ns f-it-up-friday.util.io
  (:require [clojure.java.io :as io :refer [writer reader file]]))

(def extentions {:javascript "js"
                 :jsx        "jsx"
                 :ruby       "rb"
                 :java       "java"})

(def cwd (System/getProperty "user.dir"))

(defn write-react-template [name content & [ext]]
  (let [-ext (or (ext extentions) (:javascript extentions))
        file-name (str cwd "/" name "." -ext)]
    (if-not (.exists (io/as-file file-name))
      (spit file-name content)
      (println (str "File: " name "." -ext " Exists! Won't overwrite.")))))

(defn write-content
  [path file-name content]
  (spit
    (str "./"
         path
         "/"
         file-name)
    content))