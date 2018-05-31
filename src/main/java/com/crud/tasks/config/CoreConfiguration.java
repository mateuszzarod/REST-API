package com.crud.tasks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


//tworzy tutaj beana
@Configuration
public class CoreConfiguration {
@Bean
    //RestTemplate jest to klasa dostarczana przez Springa,
    // która umożliwia realizowanie żądań HTTP pomiędzy serwerami.
    public RestTemplate restTemplate(){
    return new RestTemplate();
}

}
