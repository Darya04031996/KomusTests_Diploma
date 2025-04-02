package config;

import org.aeonbits.owner.Config;

public interface CredentialsConfig extends Config {
    @Key("Email")
    String username();

    @Key("Password")
    String password();

}
