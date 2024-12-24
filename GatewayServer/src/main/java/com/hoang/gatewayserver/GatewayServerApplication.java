package com.hoang.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

//    @Bean
//    public RouteLocator routeConfig(RouteLocatorBuilder routeBuilder) {
//        return routeBuilder.routes()
//                .route(p -> p
//                        .path("/api/candidate/**")
//                        .filters( f -> f.rewritePath("/api/candidate/(?<segment>.*)","/${segment}")
//                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
//                                .circuitBreaker(config -> config.setName("candidateCircuitBreaker")
//                                        .setFallbackUri("forward:/contactSupport")))
//                        .uri("lb://CANDIDATE"))
//                .route(p -> p
//                        .path("/api/employer/**")
//                        .filters( f -> f.rewritePath("/api/employer/(?<segment>.*)","/${segment}")
//                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
//                                .circuitBreaker(config -> config.setName("employerCircuitBreaker")
//                                        .setFallbackUri("forward:/contactSupport")))
//                        .uri("lb://EMPLOYER"))
//                .route(p -> p
//                        .path("/api/company/**")
//                        .filters( f -> f.rewritePath("/api/company/(?<segment>.*)","/${segment}")
//                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
//                                .circuitBreaker(config -> config.setName("companyCircuitBreaker")
//                                        .setFallbackUri("forward:/contactSupport")))
//                        .uri("lb://EMPLOYER"))
//                .route(p -> p
//                        .path("/api/job/**")
//                        .filters( f -> f.rewritePath("/api/company/(?<segment>.*)","/${segment}")
//                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
//                                .circuitBreaker(config -> config.setName("companyCircuitBreaker")
//                                        .setFallbackUri("forward:/contactSupport")))
//                        .uri("lb://JOB"))
//                .route(p -> p
//                        .path("/api/company/**")
//                        .filters( f -> f.rewritePath("/api/company/(?<segment>.*)","/${segment}")
//                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
//                                .circuitBreaker(config -> config.setName("companyCircuitBreaker")
//                                        .setFallbackUri("forward:/contactSupport")))
//                        .uri("lb://JOB_APPLICATION")).build();
//
//    }
}
