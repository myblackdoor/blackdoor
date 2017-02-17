(ns blackdoor.components
  (:require [void.config :as config]))

#?(:clj
   (do
     (defrecord Test
         [protocol host port]
       component/Lifecycle
       (start [this]
         (log/info "start" protocol host port)
         this)
       (stop [this]
         this))

     (def test-components
       {:server #'map->Test})))
