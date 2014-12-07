(defproject storm-kafka-test "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.apache.storm/storm-core "0.9.3"]
                 [org.apache.storm/storm-kafka "0.9.3"]
                 [org.apache.kafka/kafka_2.10 "0.8.2-beta"]]
  :main ^:skip-aot storm-kafka-test.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
