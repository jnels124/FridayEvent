(ns f-it-up-friday.core
  (:require [clojure.tools.cli :refer [cli]]
            [clojure.string :as string]
            [clojure.java.io :as io :refer [writer]]
            [f-it-up-friday.angular.templates :as angular]
            [f-it-up-friday.react.templates :as react]
            (:gen-class)))

(def version-number 0.1)

;; TODO
;; [x] - err handleling for when file is already present / check if file present
;; [x] - handle --help, handle --version, --create separetely
;; [x] - handle missing arg
;; [ ] - handle mutiple options flags
;; [x] - fancy ascii art (must)

(def ascii-art
  [
   "         ___         ___         ___    ___   ___         ___         ___"
   "  |     |     |  /        |     |       |  | |      \\ / |    \\  |  | \\"
   "  |     |-+-  | +   |-+-  |      -+-    -+-  |-+-    +   |    || + |  +  "
   "  |     |     |/    |     |         |    | | |       |   |    || \\|  |  "
   "   ---   ---         ---   ---   ---    ---   ---    |    ---     \\   --"
   (str "---------------------------------------------------------------------*v" version-number)])

(def framework "angular" #_(System/getenv "LBTC_FRAMEWORK"))

(def default-template-type (System/getenv "LBTC_DEFAULT_TEMPLATE_TYPE"))

(def required-opts #{:create_foo})

(def cli-options
  [["-h" "--help" "Print Help" :flag true :defaul false]
   ["-v" "--version" "Print Version" :flag true]])

(def react-options
  ["test"])

(def angular-options
  [["-a" "-add-methods" "Expects comma sepearated string of method + args. i.e fctn1[arg1 arg2], fctn2[arg1]"]
   ["-b" "--[no-]bind-to-controller" "Binds all values passed to scope on the controller" :flag true :default false]
   ["-c" "--controller" "Specifies controller with injections. i.e. controllername,inj1,inj2" :flag false]
   ["-d" "--[no-]directive" "Specifies directive. If controller is specified to be created, this will be the directive controller" :flag false]
   ["-h" "--[no-]include-html-template" "Creates a html template file."]
   ["-l" "--[no-]link-fctn" "Flag to determine if link function is included for directive" :flag true :default true]
   ["-m" "--module-name" ""]
   ["-f" "--file-name" "Specifies the base name for files"]
   ["-p" "--path" "Specify path string. If no path is provided, files will be placed in project root"]
   ["-r" "--require" "Comma sepearted list of required directievs"]
   ["-s" "--scope" "Comma separated list of scope var name and type. i.e var1:&,var2:=some,var3:@"]
   ["-t" "--[no-]create-test" "Creates a test(s) if controller and/or directive is created" :flag true :default true]])

(defn missing-required? [opts]
  (not-every? required-opts opts))

(defn exit [code msg]
  (println msg)
  (System/exit code))

(defn choose-template-type [options arguments type]
  (case type
    "component" (react/component options arguments)))

(defn choosen-framework [options arguments]
  (let [default-template (or default-template-type "component")]
    (println (str "will create REACT:" default-template "[default] see env vars"))
    (case framework
      "react" (choose-template-type options arguments default-template)
      "angular" (do
                  (when (:controller options)
                    (angular/create-controller options))
                  (when (:directive options)
                    (angular/create-directive options))))))

(defn -main [& args]
  (println "SOMETHING SHOULD PRINT" (concat cli-options angular-options))
  (let [[options arguments banner] (apply cli args (concat cli-options angular-options))]
    (println options)
    (cond
      (:help options) (doall
                        (println (->> ascii-art (string/join \newline)))
                        (println)
                        (println banner))
      (:version options) (println (str "v" version-number))
      :else (choosen-framework options arguments)
      )))


(-main "-c" "MyNewController,scope,q,backend" "-d" "myDirective,something,something,else" "-f" "something" "-m" "tester")