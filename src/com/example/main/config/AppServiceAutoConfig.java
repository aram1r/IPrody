package com.example.main.config;

import com.example.main.service.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppServiceAutoConfig {

    @Bean
    @ConditionalOnProperty(
            name="app.language",
            havingValue = "en",
            matchIfMissing = true
    )
    public AppService engAppService(){
        return new EnglishAppService();
    }

    @Bean
    @ConditionalOnProperty(
            name = "app.language",
            havingValue = "es"
    )
    public AppService spanishAppService() {
        return new SpainAppService();
    }

    @Bean
    @ConditionalOnProperty(
            name="app.currency",
            havingValue = "rub",
            matchIfMissing = true
    )
    public CashierService rubCashierService() {
        return new RubCashierServiceImpl();
    }

    @Bean
    @ConditionalOnProperty(
            name="app.currency",
            havingValue = "usd"
    )
    public CashierService usdCashierService() {
        return new UsdCashierServiceImpl();
    }
}
