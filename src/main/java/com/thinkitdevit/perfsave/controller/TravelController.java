package com.thinkitdevit.perfsave.controller;

import com.thinkitdevit.perfsave.annotation.LogExecutionTime;
import com.thinkitdevit.perfsave.service.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/travel")
public class TravelController {

    private final TravelService travelService;

    @PostMapping("/process")
    @LogExecutionTime
    public void processTravels() {
        long start = System.currentTimeMillis();
        travelService.doSomeStuff();
        long executionTime = System.currentTimeMillis() - start;
        Duration duration = Duration.ofMillis(executionTime);
        log.info("processTravels executed in {} ms  duration : {}", executionTime, duration);
    }
}