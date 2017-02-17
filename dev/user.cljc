(ns user
  (:require [void.config :as config]
            [blackdoor.systems :as system]
            [#?(:clj clojure.pprint
                :cljs cljs.pprint)
             :refer [pprint]]
            [com.stuartsierra.component :as component]
            [boot-component.reloaded :refer [system init start stop go reset]]
            [suspendable.core :as suspendable]
            [taoensso.timbre :as log]))

#?(:clj
   (do
     ;; (boot.core/load-data-readers!)

     (boot-component.reloaded/set-init! #'system/new-system)
     (boot-component.reloaded/set-start! #'component/start)

     (defn cljs-repl
       "Start a ClojureScript REPL"
       []
       (eval
        '(do (in-ns 'boot.user)
             (start-repl))))))

(comment
  (reset)
  system
  )
