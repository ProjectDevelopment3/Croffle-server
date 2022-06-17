package com.sungshin.croffle.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApiKey implements ApplicationListener<ApplicationStartedEvent> {

    @Value("${owner.serviceKey}")
    private String serviceKey;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
    }

    public String getServiceKey() {
        return serviceKey;
    }
}
