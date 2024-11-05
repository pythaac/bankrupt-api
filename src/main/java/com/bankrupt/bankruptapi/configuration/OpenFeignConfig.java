package com.bankrupt.bankruptapi.configuration;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.bankrupt.bankruptapi"})
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class OpenFeignConfig {

}
