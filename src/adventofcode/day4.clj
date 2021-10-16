(ns day4
  (:require [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.walk :as walk]))


(defn split [regex s]
  "swaps string/split parameters for idiomatic ->> threading"
  (s/split s regex))

(defn parse-passport [serialized]
  (->> serialized      ;recieves as "a:1 b:2 c:3"
       (split #"\s")   ;split them by the space between them ["a:1" "b:2" "c:3"]
       (map #(split #":" %))  ;splits them further by removeing the colon (["a" "1"]["b" "2"]["c" "3"])
       (into {})
       walk/keywordize-keys))

(defn passport-valid? [{:keys [byr iyr eyr hgt hcl ecl pid]}]
  (and byr iyr eyr hgt hcl ecl pid))


(->> "resources/day4input.txt"
     slurp
     (split #"\n\n")
     (map parse-passport)
     (filter passport-valid?)
     count)
