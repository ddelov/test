package com.estafet.examples.csv;

import java.io.*;
import java.util.Random;

/**
 * Created by Delcho Delov on 23.07.18.
 */
public class GenerateProducts {
    final static String OUT_PATH = "/home/ddelov/dev/nifi-1.7.0/work/csv/";
    final static char field_delimiter ='|';
    final static char string_wrapper = '"';
    final static int RECORDS_COUNT = 25;

    static Random rnd = new Random();

    public static void main(String[] args) {
        try(OutputStream os = new FileOutputStream(OUT_PATH + "products_"+RECORDS_COUNT+".csv");
            PrintWriter w = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));){
            os.write(239);//writes UTF-8 signature
            os.write(187);
            os.write(191);
            for (int i = 0; i < RECORDS_COUNT; ++i) {
                final StringBuilder sb = new StringBuilder();
                sb.append(i).append(field_delimiter).append(string_wrapper).append("Product_"+i).append(string_wrapper).append
                        (field_delimiter).append((0.5+rnd.nextFloat()*240.5));
                w.println(sb.toString());
            }
            w.flush();
        } catch (IOException e) {
            System.out.println("Сра маца в каца : " + e);
        }
        System.out.println("Done");
    }
}
