package com.blaze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.TimeZone;

@SpringBootApplication
public class MutexStudyApplication {
    public static final String BASE_YML = "classpath:";
    public static final String CONFIG_LOCATION = "spring.config.location=";

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        String yml = getYML();

        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(MutexStudyApplication.class);
        springApplicationBuilder.properties(yml);

        SpringApplication springApplication = springApplicationBuilder.build();
        springApplication.run(args);
    }

    public static String getYML() {
        String applicationYML = getApplicationYML();
        String datasourceYML = getDatasourceYML();

        return CONFIG_LOCATION +
                applicationYML +
                datasourceYML;
    }

    public static String getApplicationYML() {
        return BASE_YML + "/application.yml;";
    }

    public static String getDatasourceYML() {


        return BASE_YML + "/datasource.yml";
    }
}