package com.thinkitdevit.perfsave;

import com.thinkitdevit.perfsave.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class PerfsaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerfsaveApplication.class, args);
    }

}
