(ns frontend.state.middleware.io)

(defn io-middleware
  [dispatch]
  (fn [action]
    (dispatch action)))