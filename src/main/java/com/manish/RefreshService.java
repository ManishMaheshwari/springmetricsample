package com.manish;

import com.timgroup.statsd.StatsDClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Uses statsD java client, without SpringBoot wrapper
 * Created by mmaheshwari on 03/01/18.
 */
@Service
@RestController
public class RefreshService {

    @Autowired
    public StatsDClient statsDClient;

    @RequestMapping(method = {RequestMethod.GET}, value = {"/directstats"})
    public String directStats() {
        statsDClient.count("direct.gauge", 100);
        statsDClient.increment("direct.counter");

        long value = Math.round(Math.random() * 1000);
        System.out.println("direct.timer recording: " + value);
        statsDClient.recordExecutionTime("direct.timer", value);

        return "Stats recorded.";

    }

}