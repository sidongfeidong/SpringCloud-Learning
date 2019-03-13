package com.study.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Hello world!
 *
 */
@EnableConfigServer
@SpringBootApplication
public class Application {
    public static void main( String[] args ){
    	new SpringApplicationBuilder(Application.class).web(true).run(args);
    }
}
