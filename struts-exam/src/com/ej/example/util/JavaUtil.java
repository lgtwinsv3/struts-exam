package com.ej.example.util;

import java.io.IOException;
import java.util.Properties;

public class JavaUtil {

    public JavaUtil() {
    }

    public static Properties readProperties(String fileName) {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();

        }

        return properties;
    }
}
