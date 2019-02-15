(ns hiccup-contrib.elements)

(defn table
  ([coll] (table {} coll))
  ([attrs coll]
   (let [columns (->> coll first (map first))]
     [:table attrs
      [:thead
       [:tr
        (for [col columns]
          [:th {} col])]]
      [:tbody
       (for [row (map (partial into {}) coll)]
         [:tr
          (for [col columns] [:td {} (get row col)])])]])))
