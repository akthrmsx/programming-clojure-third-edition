(ns examples.primes)

(def primes
  (concat [2 3 5 7]
          (lazy-seq (let [primes-from (fn primes-from [n [f & r]]
                                        (if (->> primes
                                                 (take-while #(<= (* % %) n))
                                                 (some #(zero? (rem n %))))
                                          (recur (+ n f) r)
                                          (lazy-seq (cons n (primes-from (+ n f) r)))))
                          wheel (cycle [2 4 2 4 6 2 6
                                        4 2 4 6 6 2 6
                                        4 2 6 4 6 8 4
                                        2 4 2 4 8 6 4
                                        6 2 4 6 2 6 6
                                        4 2 4 6 2 6 4
                                        2 4 2 10 2 10])]
                      (primes-from 11 wheel)))))

(comment
  (def ordinals-and-primes (map vector (iterate inc 1) primes))

  (->> ordinals-and-primes
       (drop 1000)
       (take 5))
  ;;
  )
