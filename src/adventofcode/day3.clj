(ns day3
  (:require [clojure.string :as s]
            [clojure.java.io :as io]))

(defn read-data [filename]
  "reads txt file and makes the lines lazy, readable vectors"
  (with-open [rdr (io/reader filename)]
    (->> rdr
         line-seq
         vec)))

(def skimap(read-data "resources/day3input.txt"))

(defn navigate [skimap steps-right]
  (let [width (count (nth skimap 0))
        columns (iterate #(mod (+ steps-right %1) width) 0)
        spots (map nth skimap columns)
        trees (filter #(= \# %1) spots)]
    (count trees)))

(*
 (navigate skimap 1)
 (navigate skimap 3)
 (navigate skimap 5)
 (navigate skimap 7)
 (navigate (take-nth 2 skimap) 1))
