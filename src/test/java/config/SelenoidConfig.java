package config;

import org.aeonbits.owner.Config;

public interface SelenoidConfig extends Config {

    @Config.Key("selenoidLogin")
    String username();

    @Key("selenoidPass")
    String password();
}