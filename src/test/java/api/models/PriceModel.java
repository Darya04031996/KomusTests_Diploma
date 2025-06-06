package api.models;
import lombok.Data;

@Data
public class PriceModel {
    private String currencyIso;
    private Double value;
    private String priceType;
    private String formattedValue;
    private String crossedPrice;
    private Boolean calculatedWithPromo;
    private Boolean finalPrice;
    private Integer elementQuantity;
    private String elementPrice;
    private String elementUom;
    private String priceOwnerType;
    private Double coins;
    private Boolean multiplyCoinsByQuantity;
    private Integer priceTypeId;
    private String priceId;
    private String priceRegionId;
}

