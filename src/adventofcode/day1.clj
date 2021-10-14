(ns day1
  (:require [clojure.string :as s]))

;; need to make this have a better run time!! and stop with the duplicates!!
;; leave me alone for now, it worked

(defn convert2int [location]
  "takes a text file location and splits it into a collection of integers with s/split-lines"
  (->> location
       slurp
       s/split-lines
       (map #(Integer/parseInt %))))

(let [expenses (convert2int "/home/patclanet/Coding/adventofcode/adventofcode/resources/day1input.txt")]
  (for [a expenses
        b expenses
        c expenses
        :when (< a b c)
        :when (= 2020 (+ a b c))]
    [(* a b c)]))
