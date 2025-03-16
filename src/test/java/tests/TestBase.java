package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.LoginPage;


import static com.codeborne.selenide.Selenide.*;

public class TestBase {
    @BeforeAll
    static void settingsBeforeAll() {
        //WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());
        //AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());

        Configuration.browserSize = "920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://www.komus.ru/";
    }

    @AfterEach
    void afterEach() {
    //    Attach.screenshotAs("Last screenshot");
    //    Attach.pageSource();
        //    Attach.browserConsoleLogs();
        //    if (System.getProperty("isRemote") != null && System.getProperty("isRemote").equals("true")) {
        //       Attach.addVideo(System.getProperty("wdHost"));
        //    }
        Selenide.closeWebDriver();
    }
}