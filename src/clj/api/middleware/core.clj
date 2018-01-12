(ns api.middleware.core)

(defn wrap-middleware
  [& args]
  (let [handler (last args)
        middleware (apply comp (butlast args))]
    (middleware handler)))