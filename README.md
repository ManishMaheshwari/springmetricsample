This is a sample project to demonstrate exporting of metrics. It has three service classes, each of which uses a different mechanism to export the metrics.

Running statsD Server
====

Steps to start statsD server:

1. `git clone git@github.com:etsy/statsd.git`
2. Start statsD server

    * `cd <statsd_dir>`
    * `node stats.js exampleConsoleConfig.js`
    * Note: `exampleConsoleConfig.js` configures the `console` backend for statsD, for emitting the statistics. This configuration file is checked into the current project.
3. Test statsD `echo "foo:1|c|@.6" | nc -u -w0 127.0.0.1 8125`


Running SpringBoot Server
====
Steps to start SpringBoot server:

1. Start the SpringBoot application ` ./gradlew clean assemble bootRun`
2. `curl http://localhost:8080/login`
3. Validate the metrics on the statsD console.

Things to Observe
===
1. `java-statsd-client` must be included as a dependency in gradle.
2. `StatsdMetricWriter` bean is made available.
3. Observe the `CounterService` and `GaugeService` usage in `LoginService`, which are SpringBoot wrappers
4. Observe the usage of statsD java client API in `RefreshService`
5. Observe the usage of Dropwizard metrics API in `LogoutService`, to report JMX metrics