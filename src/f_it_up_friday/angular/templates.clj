(ns f-it-up-friday.angular.templates)

(defn create-args
  "Creates comma spearted list from sequable collection"
  [args]
  (apply str (interpose ", " args)))

(defn create-controller
  [cl-opts add-args]
  (let [injections (if (> (count add-args) 0)
                     (create-args add-args))
        path  (if (cl-opts :path)
                (cl-opts :path)
                "./")] ;; Will change to use working directory instead of project root which is ./
    (spit
      (str path
           (cl-opts :file-name)
           "-controller.js")
      (str "angular.module('"
           (cl-opts :module-name)
           "').controller('"
           (cl-opts :controller)
           "'"
           (str (if (not= nil injections)
                  (str "' [" injections ", function(" injections ") {}]")
                  ", function () {}"
                  )
                ");")))))

(defn create-directive
  [name injections]
  (println "Creating directve")
  (spit (str "./" name "-directive.js")
        (str "angular.module('" name "').controller('" name "', [" injections ", function(" injections ") {
        }]);")))