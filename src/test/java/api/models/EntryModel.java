package api.models;
import lombok.Data;

@Data
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