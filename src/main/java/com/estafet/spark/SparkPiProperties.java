package com.estafet.spark;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Delcho Delov on 09.07.18.
 */
@Component
public class SparkPiProperties {

    @Value("${sparkpi.jarfile}")
    private String jarFile;

    public String getJarFile() {
        return jarFile;
    }

}