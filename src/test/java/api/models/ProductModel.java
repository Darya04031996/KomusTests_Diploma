package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties
public class ProductModel {
    private String code;
    private String url;
    private StockModel stock;
    private PriceModel price;
    private Boolean isInFavorites;
    private Boolean inCart;
    private Integer quantity;
    private Boolean hasRegionalAnalogue;
    private Boolean dummy;
    private Boolean isRequiredTradeChannelMissing;
}
