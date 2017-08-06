package com.hazlecast.demo.hazelcastdemo;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by rajaniy on 7/27/17.
 */
@Configuration
public class DemoConfig {
    @Bean
    public HazelcastInstance client() {
        ClientConfig config = new ClientConfig();
        HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient(config);
        return hazelcastInstance;
    }
}
