(ns storm-kafka-test.core
  (:use [backtype.storm clojure config])
  (:import [backtype.storm StormSubmitter LocalCluster]
           [backtype.storm.spout SchemeAsMultiScheme]
           [kafka.api OffsetRequest]
           [storm.kafka KafkaConfig ZkHosts KafkaSpout SpoutConfig StringScheme])
  (:gen-class))

(def zk-hosts "localhost:2181")

(def kafka-zk-hosts (ZkHosts. zk-hosts)) 

(def kafka-config
  (let [cfg (SpoutConfig. kafka-zk-hosts "test" "/kafka" "storm-kafka-test")]
    (set! (. cfg scheme) (SchemeAsMultiScheme. (StringScheme.)))
    (set! (. cfg startOffsetTime) (kafka.api.OffsetRequest/LatestTime))
    cfg))

(def spout-map
  (let [kafka-spout (KafkaSpout. kafka-config)]
    {"kafka-spout" (spout-spec kafka-spout)}))

(defbolt print-bolt [] [tuple collector]
  (prn tuple)
  (ack! collector tuple))

(def bolt-map
  (let [spec (bolt-spec {"kafka-spout" :shuffle} print-bolt :p 10)]
    {"print-bolt" spec}))

(defn mk-topology []
  (topology spout-map bolt-map))

(def topology-options
  {TOPOLOGY-DEBUG true TOPOLOGY-WORKERS 20})

(defn run-local! [name]
  (let [cluster (LocalCluster.)]
    (.submitTopology cluster name topology-options (mk-topology))
     (try (while true (do))
         (catch InterruptedException e
           (.shutdown cluster)))))

(defn run-distributed! [name]
  (StormSubmitter/submitTopology name topology-options (mk-topology)))

(defn -main [& args]
  (if (= (first args) "--local")
    (run-local! (second args))
    (run-distributed! (first args))))
