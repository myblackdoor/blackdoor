# Blackdoor

## CIDER integration

These instructions are for use with CIDER 0.15-SNAPSHOT. If your
Emacs is using a previous version, you should upgrade now.

Add the following to your `$HOME/.boot/profile.boot`

```clojure
(deftask cider
  "CIDER profile"
  []
  (require 'boot.repl)
  (swap! @(resolve 'boot.repl/*default-dependencies*)
         concat '[[org.clojure/tools.nrepl "0.2.12"]
                  [org.clojure/tools.namespace "0.3.0-alpha3"]
                  [cider/cider-nrepl "0.15.0-SNAPSHOT"
                   :exclusions [org.clojure/tools.reader
                                org.clojure/java.classpath]]
                  [com.cemerick/piggieback "0.2.1"]
                  [refactor-nrepl "2.3.0-SNAPSHOT"
                   :exclusions [org.clojure/tools.nrepl]]])
  (swap! @(resolve 'boot.repl/*default-middleware*)
         concat '[cider.nrepl/cider-middleware
                  cemerick.piggieback/wrap-cljs-repl
                  refactor-nrepl.middleware/wrap-refactor])
  identity)
```

Ensure the version of the `cider/cider-nrepl` dependency matches the version of CIDER you are using.

Start your REPL with the following:

```
boot cider dev
```

From Emacs, use `M-x cider-connect`

Use port 5600 to connect for a server CLJ REPL

## Copyright & License

Copyright © 2017 Dylan Butman
