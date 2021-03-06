(defproject storm-kafka-test "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [cheshire "5.3.1"]
                 [org.clojure/tools.logging "0.3.1"]
                 [environ "1.0.0"]
                 [org.apache.storm/storm-core "0.9.3"]
                 [org.apache.storm/storm-kafka "0.9.3"]
                 [org.apache.kafka/kafka_2.10 "0.8.2-beta"]]
  :main ^:skip-aot storm-kafka-test.core
  :aot [storm-kafka-test.schemes]
  :target-path "target/%s"
  :plugins [[lein-environ "1.0.0"]]
  :profiles {:dev
             {:env
              {:cluster-mode     :local
               :kafka-topic      "test"
               :topology-debug   true
               :topology-workers 20
               :zookeeper-hosts  "localhost:2181"
               :zookeeper-id     "storm-kafka-test"
               :zookeeper-root   "/kafka"}}})
