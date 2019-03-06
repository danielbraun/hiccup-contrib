(ns hiccup-contrib.elements)

(defn table
  ([coll] (table {} coll))
  ([{:keys [render-cell columns]
     :or {render-cell (fn [v _ _ _] [:td v])
          columns (->> coll first (map first))}
     :as attrs} coll]
   [:table (dissoc attrs :render-cell :columns)
    [:thead
     [:tr
      (for [col columns]
        [:th {} col])]]
    [:tbody
     (for [row (map (partial into {}) coll)]
       [:tr
        (for [col columns] (render-cell (get row col) col row coll))])]]))
