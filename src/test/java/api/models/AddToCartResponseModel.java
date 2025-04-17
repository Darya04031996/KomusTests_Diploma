package api.models;
import lombok.Data;

import java.util.List;

@Data
public class AddToCartResponseModel {
    private String statusCode;
    private Integer quantityAdded;
    private Integer quantity;
    private EntryModel entry;
    private List<MindboxCartDataModel> mindboxCartData;
    private Double coins;
    private List<ErrorModel> errors;

}
