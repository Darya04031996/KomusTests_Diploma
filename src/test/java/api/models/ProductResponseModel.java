package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponseModel {
    @JsonProperty("code")
    private String code;

    @JsonProperty("url")
    private String url;

    @JsonProperty("stock")
    private StockResponseModel stock;

    @JsonProperty("price")
    private PriceResponseModel price;
}
