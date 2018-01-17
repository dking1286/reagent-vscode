(ns components.todos-list-item.core)

(defn todos-list-item
  [{:keys [todo]}]
  [:div.todos-list-item__container
    [:p.todos-list-item__title (:title todo)]
    [:p.todos-list-item__body (:body todo)]])