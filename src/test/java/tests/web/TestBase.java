package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import common.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    @BeforeAll
    static void setupConfig() {
        //WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());
        //AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());

        Configuration.browserSize = "920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://www.komus.ru/";
    }
    @BeforeEach
    void setupTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void afterEach() {
   Attach.screenshotAs("Last screenshot");
   Attach.pageSource();
    Attach.browserConsoleLogs();
    if (System.getProperty("isRemote") != null && System.getProperty("isRemote").equals("true")) {
    Attach.addVideo(System.getProperty("wdHost"));
    }
        Selenide.closeWebDriver();
    }
}