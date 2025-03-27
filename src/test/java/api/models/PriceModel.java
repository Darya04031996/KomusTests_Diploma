package api.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class PriceModel {
    private String currencyIso;
    private Double value;
    private String priceType;
    private String formattedValue;
    private Boolean calculatedWithPromo;
    private Boolean finalPrice;
    private String priceRegionId;
    private String priceOwnerType;
    private Double coins;
    private Boolean multiplyCoinsByQuantity;
    private Integer priceTypeId;
    private String priceId;
}

