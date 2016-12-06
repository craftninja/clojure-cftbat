(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little teapot"))

(println (str "Hello my name is " "Emily"))
(println (vector 1 2 3 5 8 13))
(println (list 1 2 3 5 8 13))
(println (hash-map :elowyn 9 :luke 9 :emily 12))
(println (hash-set :elowyn :luke :emily))
(println (hash-set :elowyn :luke :emily :emily)) ;dups disappear

(defn plus100
  [number]
  (+ number 100))
(println (plus100 1))
(println (plus100 100))
(println (plus100 1000))

(defn dec-maker
  [decrementor]
  (fn [number]
    (- number decrementor)))
(def dec9 (dec-maker 9))
(println (dec9 10))
(println (dec9 9))
(println (dec9 90))
(def dec42 (dec-maker 42))
(println (dec42 43))
