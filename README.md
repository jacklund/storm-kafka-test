storm-kafka-test
================

Spike of Clojure project to send information from [Apache
Kafka](http://kafka.apache.org/) to [Apache Storm](https://storm.apache.org/).

Notes
-----

This project is currently using Clojure 1.5.1 rather than 1.6 because [Storm is
stuck at 1.5](https://issues.apache.org/jira/browse/STORM-265)

Usage
-----

To run the example in local mode:

`$ lein run --local `*name*

To run it distributed:

`% lein run `*name*

where *name* is the name you want to register it as with Storm

License
-------

Copyright © 2014 Jack Lund

Distributed under the Eclipse Public License either version 1.0 or (at your
option) any later version.
