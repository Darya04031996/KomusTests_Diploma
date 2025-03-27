package api.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AddToCartResponseModel {
    private String statusCode;
    private Integer quantityAdded;
    private Integer quantity;
    private EntryModel entry;
    private List<MindboxCartDataModel> mindboxCartData;
    private Double coins;
}
