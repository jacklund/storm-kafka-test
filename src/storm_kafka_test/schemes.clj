(ns storm-kafka-test.schemes
  (:require [cheshire.core :as json]
            [clojure.tools.logging :as log])
  (:import [backtype.storm.spout Scheme]
           [backtype.storm.tuple Fields])
  (:gen-class :name storm-kafka-test.schemes.JsonScheme
              :implements [backtype.storm.spout.Scheme]))

(defn bytes-to-string [bytes]
  (apply str (map char bytes)))

(defn -deserialize [this ser]
  (let [string (bytes-to-string ser)]
    (try
      (list (json/parse-string string))
      (catch Exception e
        (log/error e "Error parsing JSON string" string)
        nil))))

(defn -getOutputFields [this]
  (Fields. ["json"]))

