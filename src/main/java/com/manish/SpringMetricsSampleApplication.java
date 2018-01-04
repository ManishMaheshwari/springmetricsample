package com.manish;

import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.metrics.statsd.StatsdMetricWriter;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringMetricsSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMetricsSampleApplication.class, args);
    }

    @Bean
    @ExportMetricWriter
    MetricWriter metricWriter() {
        return new StatsdMetricWriter("springmetricsample", "localhost", 8125);
    }

    //Exposing the statsD client - has more metric types than supported by SpringBoot wrappers.
    @Bean
    public StatsDClient statsDClient() {
        return new NonBlockingStatsDClient("springmetricsample", "localhost", 8125);
    }

}
