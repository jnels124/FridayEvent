(ns f-it-up-friday.angular.templates
  (:require [clojure.string]
            [f-it-up-friday.util.io :as io]))

(def level (atom 0))

(defn indent
  []
  (apply str (repeat (deref level) "    ")))

;;TODO -> implement function builder
(defn injection-string
  [injections fctn-args]
  (swap! level + 1)
  (str (if (not (empty? injections))
         (str ", [" injections ", function (" fctn-args ") {}]")
         ", function () {}")
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
  (io/write-content (cl-opts :path)
                    (str (cl-opts :file-name) "-controller.js")
                    (module-string cl-opts "controller")))

(defn scope-entry
  [entry]
  (str (indent) (first entry) ": '" (last entry) "'"))

(defn directive-scope-def
  [scope]
  (if (not (empty? scope))
    (str (indent)
         "scope: {\n"
         (indent)
         (->> (clojure.string/split scope #",")
              (map #(-> (clojure.string/split % #":") scope-entry))
              (interpose (str ",\n" (indent)))
              (apply str))
         "\n"
         (indent)
         "}")
    (str (indent) "scope: true")))

(defn directive-controller-def
  [cl-opts]
  (if (cl-opts :controller)
    (str (indent) "controller: '" (first (clojure.string/split (cl-opts :controller) #",")) "',\n")))

(defn directive-bind-to-controller-def
  [cl-opts]
  (if (cl-opts :bind-to-controller)
    (str (indent)
         "controllerAs: '"
         (cl-opts :bind-to-controller)
         "',\n")))

(defn template-path
  [module path]
  (if (empty? path)
    "UNDEFINED"
    (if (= module (first path))
      (apply str (interpose "/" path))
      (template-path module (rest path)))))

(defn directive-template-def
  [cl-opts]
  (if (cl-opts :include-html-template)
    (str (indent) "templateUrl: '" (template-path (cl-opts :module-name)
                                                  (clojure.string/split (cl-opts :path) #"/"))
         "/"
         (cl-opts :file-name) "-template.html,\n")))

(defn directive-link-def
  [include-link]
  (if include-link
    (str ",\n"
         (indent)
         "link: function (scope, element, attributes, controllers) {}")))

(defn directive-required-def
  [required]
  (if (not (empty? required))
    (str (indent)
         "require: ["
         (->> (clojure.string/split required #",")
              (map #(str "'" % "'"))
              (interpose ", ")
              (apply str))
         "],\n"
         )))

;; TODO: Implement args to control restrict
(defn create-directive
  [cl-opts]
  (let [dir-str (str
                  (indent)
                  ((fn [] (swap! level + 1) nil))
                  "return {\n"
                  (indent)
                  "restrict: 'E',\n"
                  (directive-template-def cl-opts)
                  (directive-required-def (cl-opts :require))
                  (directive-controller-def cl-opts)
                  (directive-bind-to-controller-def cl-opts)
                  (directive-scope-def (cl-opts :scope))
                  (directive-link-def (cl-opts :link-fctn))
                  "\n"
                  "};")]
    (io/write-content (cl-opts :path)
                      (str (cl-opts :file-name) "-directive.js")
                      (clojure.string/replace (module-string cl-opts "directive") "{}" (str "{\n   " dir-str "\n}")))))