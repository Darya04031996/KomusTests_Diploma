package api.models;
import lombok.Data;

@Data
public class ErrorModel {
    private String message;
    private String level;
}
