(ns api.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [resources.todos.controller :as todos-controller]))

(defroutes root-handler
  (GET "/" [] "Running")
  (context "/todos" []
    (GET "/" [] todos-controller/get-all)
    (POST "/" [] todos-controller/create))
  (route/not-found {:status 404
                    :body "Not found"}))