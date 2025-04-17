package api.models;

import lombok.Data;
import java.util.List;

@Data
public class FavoriteProductModel {
    private String code;
    private PriceModel price;
    private List<VolumePriceModel> volumePrices;
    private Integer quantity;
    private PriceModel minPrice;
    private Boolean hasRegionalAnalogue;
    private Boolean dummy;
    private Boolean isRequiredTradeChannelMissing;
}
