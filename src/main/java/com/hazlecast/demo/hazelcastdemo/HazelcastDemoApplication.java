package com.hazlecast.demo.hazelcastdemo;

import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.locks.Lock;

@SpringBootApplication
public class HazelcastDemoApplication implements CommandLineRunner {

    private static final Logger _logger = LoggerFactory.getLogger(HazelcastDemoApplication.class);

    @Autowired
    private HazelcastInstance client;

    public static void main(String[] args) {
        SpringApplication.run(HazelcastDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Lock lock = client.getLock("greetLock");
        if (lock.tryLock()) {
            _logger.info("We have started");
        }
    }
}
