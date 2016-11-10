(ns calculator-clj.core
  (:gen-class)
  (:require [clojure.string :as str]))


(def filename "answer.txt")


(defn add [& x] (apply + x))
(defn subtract [& x] (apply - x))
(defn multiply [& x] (apply * x))
(defn divide [& x] (apply / x))


(defn print-menu 
  [] 
  (println "What would you like to do?")
  (println "1: Add")
  (println "2: Subtract")
  (println "3: Multiply")
  (println "4: Divide")
  (println "5: See Last Answer"))


(defn write-last
  [answer]
  (spit filename answer))


(defn get-list
  []
  (slurp filename))


(defn do-op
  [op]
  (println "Please enter the numbers")
  (def numbers (read-line))
  (def answer (apply op 
           (map read-string (str/split numbers #" "))))
  (printf "The answer is: %d\n" answer)
  (write-last answer))


(defn -main
  [& args]
  (while true
    (print-menu)
    (def option (read-string (read-line)))
    (cond
      (= '1 option) (do-op add)
      (= '2 option) (do-op subtract)
      (= '3 option) (do-op multiply)
      (= '4 option) (do-op divide)
      (= '5 option) (printf "%s\n" (get-list))
      :else (printf "I don't understand %s\n" option))))
