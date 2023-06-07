package com.thusitha.rsspasser.rsspasserapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RssPasserAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RssPasserAppApplication.class, args);
    }

}
