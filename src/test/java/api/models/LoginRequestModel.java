package api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginRequestModel {

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

}