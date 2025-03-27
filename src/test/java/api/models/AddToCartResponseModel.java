package api.models;


import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties
public class AddToCartResponseModel {
    private String statusCode;
    private Integer quantityAdded;
    private Integer quantity;
    private EntryModel entry;
    private List<MindboxCartDataModel> mindboxCartData;
    private Double coins;
}
