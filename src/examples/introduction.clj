(ns examples.introduction)

(defn blank? [str]
  (every? Character/isWhitespace str))

(comment
  (blank? "aaa")
  (blank? "")
  ;;
  )

(defn hello-world [username]
  (println (format "Hello, %s" username)))

(comment
  (hello-world "aaa")
  ;;
  )

(def visitors (atom #{}))

(defn hello
  "Writes hello message to *out*. Calls you by username.
   Knows if you have been here before."
  [username]
  (swap! visitors conj username)
  (println (str "Hello, " username)))

(comment
  (hello "Rich")
  @visitors
  ;;
  )
