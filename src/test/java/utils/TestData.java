package utils;

import tests.web.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestData {
    public static final String email1 = System.getProperty("email", "wrongemail@gmail.com");
    public static final String password1 = System.getProperty("password", "BestLife2024");

    public static final String email = TestBase.credentialsConfig.username();
    public static final String password = TestBase.credentialsConfig.password();

    public static String getTestData(String product) {
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
