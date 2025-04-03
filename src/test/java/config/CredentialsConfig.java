package config;

import org.aeonbits.owner.Config;
@Config.Sources({
        "classpath:credentials.properties",
        "system:properties",

})

public interface CredentialsConfig extends Config {
    @Key("email")
    String username();

    @Key("password")
    String password();

}
