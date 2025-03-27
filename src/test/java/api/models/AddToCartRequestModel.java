package api.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddToCartRequestModel {
    private String productCode;
    private Integer qty;
}
