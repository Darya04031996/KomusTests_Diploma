package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties
public class EntryModel {
    private Integer quantity;
    private ProductModel product;
    private Boolean updateable;
    private Boolean showVolumePrices;
    private Boolean showRelatedProducts;
    private Boolean showSparepartProducts;
    private Boolean minOrderQuantityExist;
    private Boolean isMinimalPriceVisible;
}