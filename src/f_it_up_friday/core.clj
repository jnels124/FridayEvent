(ns f-it-up-friday.core
  (:require [clojure.tools.cli :refer [cli]]
            [clojure.java.io :as io :refer [writer]]
            [f-it-up-friday.angular.templates :as angular]))

(defn -main [& args]
  (let [[opts add-args banner]
        (cli args
             ["-a" "-add-methods" "Expects comma sepearated string of method + args. i.e fctn1[arg1 arg2], fctn2[arg1]"]
             ["-b" "--[no-]bind-to-controller" "Binds all values passed to scope on the controller" :flag true :default false]
             ["-c" "--controller" "Specifies controller with injections. i.e. controllername,inj1,inj2" :flag false]
             ["-d" "--[no-]directive" "Specifies directive. If controller is specified to be created, this will be the directive controller" :flag false]
             ["-h" "--[no-]include-html-template" "Creates a html template file."]
             ["-l" "--[no-]link-fctn" "Flag to determine if link function is included for directive" :flag true :default true]
             ["-m" "--module-name" ""]
             ["-n" "--file-name" "Specifies the base name for files"]
             ["-p" "--path" "Specify path string. If no path is provided, files will be placed in project root"]
             ["-r" "--require" "Comma sepearted list of required directievs"]
             ["-s" "--scope" "Comma separated list of scope var name and type. i.e var1:&,var2:=some,var3:@"]
             ["-t" "--[no-]create-test" "Creates a test(s) if controller and/or directive is created" :flag true :default true])]

    (when (opts :controller) #_(and (:controller opts))
      (angular/create-controller opts))

    (when (:directive opts)
      (angular/create-directive opts))))
