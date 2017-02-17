(ns blackdoor.systems
  (:require [blackdoor.components]
            [void.config :as config #?@(:clj [:refer [profile]])]
            [void.components]
            [com.stuartsierra.component :as component]
            [plumbing.core :refer [map-vals]]
            [taoensso.timbre :as log])
  #?(:cljs (:require-macros [void.config :refer [profile]])))

(def base-components
  (merge ;;void.components/database-components
   ;;void.components/parser-components
   ;;void.components/chsk-components
   ;;void.components/router-components
   ))

(def systems
  #?(:clj
     {:server (merge base-components
                     blackdoor.components/test-components
                     ;;void.components/devops-components
                     ;;void.components/http-components
                     )}
     :cljs
     {:client base-components}))

(defn new-system
  []
  (let [config (config/config (profile))
        system (get-in config [:system #?(:clj :clj :cljs :cljs)])
        components (get systems system)]
    (log/info "new system" system)
    (->> (for [[k ctr] components]
           [k (ctr (get config k))])
         (apply concat)
         (apply component/system-map))))
