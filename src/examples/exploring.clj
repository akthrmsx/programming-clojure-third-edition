(ns examples.exploring
  (:require [clojure.string :as str]))

(defn greeting
  "Returns a greeting of the form 'Hello, username.'
   Default username is 'world'."
  ([] (greeting "world"))
  ([username] (str "Hello, " username)))

(comment
  (greeting "world")
  (greeting)
  ;;
  )

(defn date [person1 person2 & chaperones]
  (println person1 "and" person2 "went out with" (count chaperones) "chaperones."))

(comment
  (date "Romeo" "Juliet" "Friar Lawrence" "Nurse")
  ;;
  )

(defn indexable-words? [word]
  (> (count word) 2))

(comment
  (filter indexable-words? (str/split "A fine day it is" #"\W+"))
  ;;
  )

(defn indexable-words [text]
  (let [indexable-words? (fn [w] (> (count w) 2))]
    (filter indexable-words? (str/split text #"\W+"))))

(comment
  (indexable-words "A fine day it is")
  ;;
  )

(defn make-greeter [prefix]
  (fn [username] (str prefix ", " username)))

(comment
  (def hello-greeting (make-greeter "Hello"))
  (def aloha-greeting (make-greeter "Aloha"))

  (hello-greeting "world")
  (aloha-greeting "world")
  ;;
  )

(defn square-corners [bottom left size]
  (let [top (+ bottom size)
        right (+ left size)]
    [[bottom left] [top left] [top right] [bottom right]]))

(comment
  (square-corners 1 2 3)
  ;;
  )

(defn greet-author [{first-name :first-name}]
  (println "Hello," first-name))

(comment
  (greet-author {:first-name "Vernor" :last-name "Vinge"})
  ;;
  )

(defn ellipsize [words]
  (let [[w1 w2 w3] (str/split words #"\s+")]
    (str/join " " [w1 w2 w3 "..."])))

(comment
  (ellipsize "The quick brown fox jumps over the lazy dog.")
  ;;
  )

(defn is-small? [number]
  (if (< number 100)
    "yes"
    (do (println "Saw a big number" number)
        "no")))

(comment
  (is-small? 50)
  (is-small? 200)
  ;;
  )

(comment
  (loop [result []
         x 5]
    (if (zero? x)
      result
      (recur (conj result x) (dec x))))
  ;;
  )

(defn countdown [result x]
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))

(comment
  (countdown [] 5)
  ;;
  )

(defn indexed [coll]
  (map-indexed vector coll))

(comment
  (indexed "abcde")
  ;;
  )

(defn index-filter [pred coll]
  (when pred
    (for [[idx elt] (indexed coll) :when (pred elt)]
      idx)))

(comment
  (index-filter #{\a \b} "abcde")
  (index-filter #{\a \b} "xyz")
  ;;
  )

(defn index-of-array [pred coll]
  (first (index-filter pred coll)))

(comment
  (index-of-array #{\x \a} "abcdexyz")
  (index-of-array #{\y \b} "abcdexyz")
  ;;
  )

(comment
  (nth (index-filter #{:h} [:t :t :h :t :h :t :t :t :h :h]) 2)
  ;;
  )
