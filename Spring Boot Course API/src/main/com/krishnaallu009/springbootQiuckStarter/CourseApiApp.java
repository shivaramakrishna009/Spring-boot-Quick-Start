package com.krishnaallu009.springbootQiuckStarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
/* * This annotation is a convenience annotation that adds:
 * - @Configuration: Tags the class as a source of bean definitions for the application context.
 * - @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings.
 * - @ComponentScan: Tells Spring to look for other components, configurations, and services in the com/krishnaallu009/springbootQiuckStarter package,
 *      allowing it to find the controllers.
 *  The main method uses Spring Boot's SpringApplication.run() method to launch the application.
 * It tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
 */
public class CourseApiApp {
    public static void main(String[] args) {
        SpringApplication.run(CourseApiApp.class, args);
    }
}
