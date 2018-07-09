package com.estafet.spark;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SparkPiBootApplication {

	private static final Logger log = LogManager.getRootLogger();

    @Autowired
    private SparkPiProperties properties;

    @PostConstruct
    public void init() {
        log.info("SparkPi submit jar is: "+properties.getJarFile());
        if (!SparkContextProvider.init(properties)) {
            // masterURL probably not set,
            // meaning this was likely run outside of oshinko
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(SparkPiBootApplication.class, args);
    }
}