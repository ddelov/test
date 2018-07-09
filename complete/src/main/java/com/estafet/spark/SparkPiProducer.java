package com.estafet.spark;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Delcho Delov on 09.07.18.
 */
public class SparkPiProducer implements Serializable {
    public String GetPi(int scale) {
        JavaSparkContext jsc = SparkContextProvider.getContext();

        int n = 100000 * scale;
        List<Integer> l = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++) {
            l.add(i);
        }

        JavaRDD<Integer> dataSet = jsc.parallelize(l, scale);

        int count = dataSet.map(integer -> {
            double x = Math.random() * 2 - 1;
            double y = Math.random() * 2 - 1;
            return (x * x + y * y < 1) ? 1 : 0;
        }).reduce((integer, integer2) -> integer + integer2);

        String ret = "Pi is rouuuughly " + 4.0 * count / n;

        return ret;
    }
}