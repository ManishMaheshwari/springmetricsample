package com.manish;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Uses DropWizard Metrics
 * Created by mmaheshwari on 04/01/18.
 */
@Service
@RestController
public class LogoutService {

    @Autowired
    private MetricRegistry registry;

    private Timer logoutTimer;

    @PostConstruct
    public void init() {
        this.logoutTimer = registry.timer("logout.time");
        configureReporters(registry);
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/logout"})
    public String logout() {
        Timer.Context timer = logoutTimer.time();
        try {
            TimeUnit.MILLISECONDS.sleep(Math.round(Math.random() * 1000));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            timer.stop();
        }
        return "Logged out";

    }


    /**
     * JMX reported metrics can be verified in JConsole.
     * Alternatively, you can report to 10's of different backends including Console, Graphite, StatsD, etc.
     *
     * @param metricRegistry
     */
    public void configureReporters(MetricRegistry metricRegistry) {
        JmxReporter.forRegistry(metricRegistry).build().start();
    }
}
