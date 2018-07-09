package com.estafet.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * Created by Delcho Delov on 09.07.18.
 */
public class SparkContextProvider {

    private static SparkContextProvider INSTANCE = null;

    private SparkConf sparkConf;
    private JavaSparkContext sparkContext;

    private SparkContextProvider() {
    }

    private SparkContextProvider(SparkPiProperties props) {
        this.sparkConf = new SparkConf().setAppName("JavaSparkPi");
        this.sparkConf.setJars(new String[]{props.getJarFile()});
        this.sparkContext = new JavaSparkContext(sparkConf);
    }

    public static boolean init(SparkPiProperties props) {
        try {
            if (INSTANCE == null) {
                INSTANCE = new SparkContextProvider(props);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static JavaSparkContext getContext() {
        return INSTANCE.sparkContext;
    }

}