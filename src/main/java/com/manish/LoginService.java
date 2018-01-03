package com.manish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mmaheshwari on 03/01/18.
 */
@Service
@RestController
public class LoginService {

    @Autowired
    public CounterService counterService;

    @Autowired
    public GaugeService gaugeService;

    @RequestMapping(method = {RequestMethod.GET}, value = {"/login"})
    public String login() {
        counterService.increment("counter.login.success");
        double value = Math.random();
        gaugeService.submit("gauge.login.heat", value);
        System.out.println("Last Value of gauge.login.heat: " + value);
        return "Login success from Server.";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/home"})
    public String home() {
        return "Hello from Server.";

    }
}