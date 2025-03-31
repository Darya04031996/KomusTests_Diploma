package api.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
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
