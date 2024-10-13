package com.thinkitdevit.perfsave.service;

import com.thinkitdevit.perfsave.annotation.LogExecutionTime;
import com.thinkitdevit.perfsave.entity.Travel;
import com.thinkitdevit.perfsave.repository.TravelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class TravelService {

    private static final int NUMBER_OF_JUNK = 10000000;

    private static final int NUMBER_OF_TRAVELS = 50000;

    private final TravelRepository travelRepository;

    @Transactional
    public void doSomeStuff(){

        long start = System.currentTimeMillis();
        log.info("START Processing junk");
        List<Travel> junks = computeTravels(NUMBER_OF_JUNK);
        junks.stream().forEach(travel -> travel.setCategory(0));

        long executionTime = System.currentTimeMillis() - start;
        Duration duration = Duration.ofMillis(executionTime);
        log.info("Processing junk executed in {} ms  duration : {}", executionTime, duration);

        start = System.currentTimeMillis();
        log.info("START Processing travels");
        List<Travel> travels = computeTravels(NUMBER_OF_TRAVELS);
        executionTime = System.currentTimeMillis() - start;
        duration = Duration.ofMillis(executionTime);
        log.info("Processing travels executed in {} ms  duration : {}", executionTime, duration);

        start = System.currentTimeMillis();
        saveAllTravels(travels);
        executionTime = System.currentTimeMillis() - start;
        duration = Duration.ofMillis(executionTime);
        log.info("saveAllTravels executed in {} ms  duration : {}", executionTime, duration);
        log.info("END Processing travels");
    }

    @LogExecutionTime
    private static List<Travel> computeTravels(int numberOfTravels){
        List<Travel> travels = new ArrayList<>();
        for(int i = 0; i < numberOfTravels; i++){

            // The category of the travel is 1 if i is odd, 2 if i is even
            int category = i % 2 + 1;
            Travel travel= new Travel(i, "Updated Destination " + i, category,false);
            travels.add(travel);
        }
        return travels;
    }

    @LogExecutionTime
    //@Transactional
    public void saveAllTravels(List<Travel> travels){

        List<Travel> existingTravels = travelRepository.findAll();

        Map<Integer, Travel> existingTravelsMap = existingTravels.stream()
                .collect(Collectors.toMap(Travel::getId, Function.identity()));

        travels.forEach(travel -> {
            if(existingTravelsMap.containsKey(travel.getId())){
                travel.setNew(false);
            } else {
                travel.setNew(true);
            }
        });


        travelRepository.saveAll(travels);

//        if(true){
//            throw new RuntimeException("Error while saving travels");
//        }
    }

}
