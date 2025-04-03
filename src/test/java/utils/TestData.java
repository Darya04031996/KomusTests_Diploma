package utils;

import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class TestData {
    public static final String email = System.getProperty("email", "darya.melgunova@gmail.com");
    public static final String password = System.getProperty("password", "BestLife2025");
    public static final String email1 = System.getProperty("email", "wrongemail@gmail.com");
    public static final String password1 = System.getProperty("password", "BestLife2024");


    public static String getTestData(String product) {
        ClassLoader classLoader = TestData.class.getClassLoader();
        File file = new File("src/test/resources/data/test.data");

        Properties testData = new Properties();
        try {
            testData.load(new FileInputStream(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return testData.getProperty(product);
    }

}
