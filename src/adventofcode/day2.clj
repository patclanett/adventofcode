(ns day2
  (:require [clojure.string :as s]
            [clojure.java.io :as io]))

;;Each line gives the password policy and then the password.
;;The password policy indicates the lowest and highest number of times a given letter
;;must appear for the password to be valid. For example, 1-3 a means that
;;the password must contain a at least 1 time and at most 3 times.

;;How many passwords are valid according to their policies?

(defn des-line [line]
  "destructure the parsed lines into a map bu using a regular expression"
  (let [[ _            min    max   ch   password]
        (re-matches #"(\d+)-(\d+) (\w): (\w+)" line)]
    {:min (Integer/parseInt min)
     :max (Integer/parseInt max)
     :ch (nth ch 0)
     :password password}))



(defn read-data [filename]
  (with-open [rdr (io/reader filename)]
    (->> rdr
         line-seq
         (mapv des-line))))


;;;;;; SOLUTION ONE
; (defn valid-password? [{:keys [min max ch password]}]
;   (let [times (->> password
;                    (filter #(= ch %))
;                   count)]
;     (<= min times max)))

(defn valid-password? [{:keys [min max ch password]}]
  (not=
    (= ch (nth password (dec min)))
    (= ch (nth password (dec max)))))




(->> "/home/patclanet/Coding/adventofcode/adventofcode/resources/day2input.txt"
     read-data
     (filter valid-password?)
     count)
