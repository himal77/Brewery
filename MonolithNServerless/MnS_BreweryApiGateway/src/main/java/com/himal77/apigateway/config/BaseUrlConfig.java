package com.himal77.apigateway.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "baseurl")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseUrlConfig {
    public String beerurl;
    public String breweryurl;
    public String breworderurl;
    public String customerurl;
    public String customerorderurl;
    public String beerinventoryurl;
    public String serverlessurl;
    public String stepfuncarn;
    public String stepfuncurl;

}
