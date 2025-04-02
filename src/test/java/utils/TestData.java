package utils;

import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class TestData {
    private static final CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class,System.getProperties());

    public static String getEmail() {
        return System.getProperty("email", credentialsConfig.username());
    }

    public static String getPassword() {
        return System.getProperty("password", credentialsConfig.password());
    }

    public static String getEmail1() {
        return System.getProperty("email1", "wrongemail@gmail.com");
    }

    public static String getPassword1() {
        return System.getProperty("password1", "BestLife2024");
    }


    public static String getTestData(String product) {
        ClassLoader classLoader = TestData.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("data/test.data")).getFile());

        Properties testData = new Properties();
        try {
            testData.load(new FileInputStream(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return testData.getProperty(product);
    }

}
