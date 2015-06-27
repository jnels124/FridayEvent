(ns f-it-up-friday.angular.templates
  (:require [clojure.string]))

;;will move this to a utility location eventually
(defn write-content
  [path file-name content]
  (spit
    (str "./"
         path
         "/"
         file-name)
    content))

;;TODO -> implement function builder
(defn injection-string
  [injections fctn-args]
  (str (if (not (empty? injections))
         (str ", [" injections ", function(" fctn-args ") {}]")
         ", function () {}"
         )
       ");"))

(defn module-string
  [cl-opts, type]
  (let [mod-opts (clojure.string/split (cl-opts (keyword type)) #",")]
    (str "angular.module('"
         (cl-opts :module-name)
         "')."
         type
         "('"
         (first mod-opts)
         "'"
         (injection-string (apply str (interpose ", " (map #(str "'" % "'") (rest mod-opts))))
                           (apply str (interpose ", " (rest mod-opts)))))))
(defn create-controller
  [cl-opts]
  (write-content (cl-opts :path) (str (cl-opts :file-name) "-controller.js") (module-string cl-opts "controller")))

(defn create-directive
  [cl-opts]
  (let [dir-str (str
                  "return {\n"
                  "   restrict: 'E'\n"
                  "   controller: '" (first (clojure.string/split (cl-opts :controller) #",")) "'\n"
                  "   controllerAs: '" (cl-opts :bind-to-controller) "\n"
                  "   templateUrl: '" (str (cl-opts :file-name) "-template.html'\n")
                  "   scope: " (cl-opts :scope)
                  "\n"
                  "};")]
    (write-content (cl-opts :path) (str (cl-opts :file-name) "-directive.js") (clojure.string/replace (module-string cl-opts "directive") "{}" (str "{\n   " dir-str "\n}")))))