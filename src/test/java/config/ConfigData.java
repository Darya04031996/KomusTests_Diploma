package config;

import org.aeonbits.owner.Config;

import static com.codeborne.selenide.Browsers.CHROME;


@Config.Sources(
        {
                "classpath:${env}.properties",
        }
)

    public interface ConfigData extends Config {

        @Key("baseUrl")
        @DefaultValue("https://www.komus.ru/")
        String baseUrl();

        @Key("browser")
        @Config.DefaultValue(CHROME)
        String browser();

        @Key("browserSize")
        @DefaultValue("1080x920")
        String browserSize();

        @Key("browserVersion")
        @DefaultValue("125")
        String browserVersion();

        @Key("remoteUrl")
        String remoteUrl();

    }

