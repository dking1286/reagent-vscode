(defproject reagent-vscode "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 
                 ;; Shared dependencies
                 [org.clojure/core.async "0.3.465"]

                 ;; Backend dependencies
                 [me.raynes/fs "1.4.6"]
                 [popen "0.3.1"]
                 [hawk "0.2.11"]
                 [clj-time "0.14.2"]
                 [environ "1.1.0"]
                 [org.postgresql/postgresql "9.4.1212.jre7"]
                 [org.clojure/java.jdbc "0.7.5"]
                 [org.clojure/data.json "0.2.6"]
                 [honeysql "0.9.1"]
                 [ragtime "0.7.2"]
                 [ring "1.6.3"]
                 [compojure "1.6.0"]
                 
                 ;; Frontend dependencies
                 [reagent "0.8.0-alpha2"]
                 
                 ;; Build and development dependencies
                 [org.clojure/tools.nrepl "0.2.10"]
                 [figwheel-sidecar "0.5.14"]
                 [com.cemerick/piggieback "0.2.2"]]

  :plugins [[lein-environ "1.1.0"]
            [lein-cljsbuild "1.1.7"]
            [lein-sass "0.4.0"]
            [lein-shell "0.5.0"]
            [cider/cider-nrepl "0.16.0"]]

  :source-paths ["src/clj" "src/cljc"]

  :target-path "target/%s"

  :clean-targets ^{:protect false}
  ["resources/public/js/compiled" "resources/css/compiled" :target-path]

  :repl-options {:port 9091
                 :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :figwheel {:server-port 9090
             :css-dirs ["resources/public/css"]}

  :cljsbuild
  {:builds {:app {:source-paths ["src/cljs" "src/cljc"]
                  :compiler {:main reagent-vscode.core
                             :asset-path "js/compiled/out"
                             :output-to "resources/public/js/compiled/main.js"
                             :output-dir "resources/public/js/compiled/out"}}}}
                             
  :profiles
  {:dev {:env {:environment "dev"}
         :cljsbuild {:builds {:app {:figwheel true
                                    :compiler {:optimizations :none
                                               :source-map true
                                               :preloads [cljs.user]}}}}}
                                               
   :prod {:env {:environment "prod"}
          :main api.core
          :aot [api.core]
          :cljsbuild {:builds {:app {:compiler {:optimizations :advanced}}}}}}
          
  :aliases
  {"repl:dev" ;; Starts the repl in development mode
   ["do" "clean"
         ["with-profile" "+dev,+local-dev" "repl"]]
         
   "client-postbuild" ;; Cleans up temporary files generated during cljs compilation
   ["shell" "rm" "-rf" "./resources/public/js/compiled/out"]
   
   "build-client:prod" ;; Builds the cljs client in production mode
   ["do" "clean"
         ["with-profile" "prod" "cljsbuild" "once"]
         ["client-postbuild"]]
   
   "build-api:prod"      
   ["do" ["with-profile" "prod" "uberjar"]]})