(ns services.network
  (:require [cognitect.transit :as transit]
            [utils.async :refer [promise-to-channel]]))

(def api-hostname "http://localhost:3000")
(def reader (transit/reader :json))
(def writer (transit/writer :json))

;; FIXME: Not sending the content-type header correctly

(defn- create-fetch-headers
  [data opts]
  (as-> {"accept" "application/transit+json"} $
    (if (nil? data)
      $
      (assoc $ "Content-Type" "application/transit+json"))
    (merge $ (:headers opts))
    (clj->js $)
    (js/Headers. $)))

(defn- create-fetch-init
  [method data opts]
  (as-> opts $
      (assoc $ :method (-> method name .toUpperCase))
      (assoc $ :headers (create-fetch-headers data opts))
      (if (nil? data)
        $
        (assoc $ :body (transit/write writer data)))
      (clj->js $)))

(defn request
  ([method url] (request method url nil nil))
  ([method url data-or-opts]
    (condp = method
      :get (request method url nil data-or-opts)
      :post (request method url data-or-opts nil)
      :patch (request method url data-or-opts nil)
      :delete (request method url nil data-or-opts))) ;; FIXME: I think it's this, need to check if the last argument is data or opts based on request method
  ([method url data opts]
    (let [init (create-fetch-init method data opts)
          full-url (str api-hostname url)]
      (-> (js/fetch full-url init)
        (.then (fn [response]
                 (if-not (.-ok response)
                   (throw (js/Error. (.-statusText response)))
                   (.text response))))
        (.then (fn [text] (transit/read reader text)))
        promise-to-channel))))
