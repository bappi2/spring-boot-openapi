package com.mmefta.springbootopenapi;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringBootOpenapiApplication implements CommandLineRunner {

    @Value("${discount.offer.price}")
    private int discountPrice;

    @PostConstruct
    public void init() {
        log.info("SpringBootOpenapiApplication init() has started.. ");
    }

    public static void main(String[] args) {
        log.info("SpringBootOpenapiApplication main() has started.. ");

        SpringApplication.run(SpringBootOpenapiApplication.class, args);

        log.info("SpringBootOpenapiApplication main() has ended.. ");
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("CommandLine runner run()  has started.. ");
        log.info("DISCOUNT Loaded: " + discountPrice);
    }
}
