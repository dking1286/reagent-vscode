(ns api.routes
  (:require [clojure.edn :as edn]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [resources.todos.controller :as todos-controller]))

(defroutes root-handler
  (GET "/" [] "Running")
  (context "/todos" []
    (GET "/" [] todos-controller/get-all)
    (GET "/:id" [id] (todos-controller/get-one (edn/read-string id)))
    (POST "/" [] todos-controller/create)
    (PATCH "/:id" [id] (todos-controller/update-one (edn/read-string id)))
    (DELETE "/:id" [id] (todos-controller/delete-one (edn/read-string id)))
  (route/not-found {:status 404
                    :body "Not found"})))