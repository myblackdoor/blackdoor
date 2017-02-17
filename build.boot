(load-file "build/build.clj")
(require '[clojure.edn :as edn])
(def deps (edn/read-string (slurp "build/deps.edn")))

(def project "blackdoor")
(def version (deduce-version-from-git))
(def repl-port 5600)

(set-env!
 :repositories #(conj % ["my.datomic.com" {:url "https://my.datomic.com/repo"
                                           :username (get-sys-env "DATOMIC_USER")
                                           :password (get-sys-env "DATOMIC_PASS")}])
 :dependencies (vec
                (concat
                 (->> [:void]
                      (pull-deps deps))
                 (->> []
                      (pull-deps deps)
                      (mapv #(conj % :scope "test")))))
 :source-paths #{"src"}
 :resource-paths #(conj % "resources"))

(require
 '[void.build :refer :all])

(set-tasks-options!
 {:project project
  :version version
  :description "A sex positive community where safety comes first."
  :license {}
  :url "http://myblackdoor.com"
  :org "pleasetrythisathome"
  :repl-port repl-port})
