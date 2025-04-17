package api.models;
import lombok.Data;

@Data
public class AddToCartRequestModel {
    private String productCode;
    private Integer qty;
}
