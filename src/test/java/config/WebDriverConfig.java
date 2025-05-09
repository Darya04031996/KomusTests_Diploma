package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties",
        "system:properties",
        "system:env"
})

public interface WebDriverConfig extends Config {

    @Key("browserName")
    @DefaultValue("CHROME")
    String getBrowserName();

    @Key("browserVersion")
    @DefaultValue("100.0")
    String getBrowserVersion();

    @Key("baseUrl")
    @DefaultValue("https://www.komus.ru/")
    String getBaseUrl();

    @Key("loadStrategy")
    @DefaultValue("eager")
    String getLoadStrategy();

    @Key("browserSize")
    @DefaultValue("1100x1080")
    String getBrowserSize();

    @DefaultValue("false")
    Boolean isRemote();

    @Key("remoteUrl")
    String getRemoteUrl();
}
