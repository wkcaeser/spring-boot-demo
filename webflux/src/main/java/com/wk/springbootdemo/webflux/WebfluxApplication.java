package com.wk.springbootdemo.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class WebfluxApplication {

    @Autowired
    public WebfluxApplication(TimeHandler timeHandler) {
        this.timeHandler = timeHandler;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebfluxApplication.class, args);
    }

    private final TimeHandler timeHandler;

    @Bean
    public RouterFunction<ServerResponse> timeRouter() {
        return RouterFunctions.route(RequestPredicates.GET("/time"), timeHandler::getTime)
                .andRoute(RequestPredicates.GET("/date"), timeHandler::getDate);
    }
}
