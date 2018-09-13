package com.magina.resource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class CommonPropertyConfiguration {
    
    private String layout;
    private String fullLayout;
    
    public String getLayout() {
        return layout;
    }
    public void setLayout(String layout) {
        this.layout = layout;
    }
    public String getFullLayout() {
        return fullLayout;
    }
    public void setFullLayout(String fullLayout) {
        this.fullLayout = fullLayout;
    }
    
}