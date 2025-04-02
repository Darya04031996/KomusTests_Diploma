package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.WebDriverConfig;
import helpers.Attachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    boolean isRemote = Boolean.parseBoolean(System.getProperty("isRemote", "false"));
    String environment = System.getProperty("env");

    @BeforeAll
    static void configParams() {
        WebDriverConfig webDriverConfig = new WebDriverConfig();
        webDriverConfig.configParams();
    }
    @BeforeEach
    void setupTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        if (isRemote || environment.equals("remote")) {
            if (!Configuration.browser.equals("firefox")) {
                Attachments.addScreenshot("Test screenshot");
                Attachments.addPageSource();
                Attachments.addBrowserConsoleLogs();
                Attachments.addVideo();
            }
        } else {
            Attachments.addScreenshot("Test screenshot");
            Attachments.addPageSource();
            Attachments.addBrowserConsoleLogs();
        }
        Selenide.closeWebDriver();
    }
}