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
public class EntryResponseModel {
    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("product")
    private ProductResponseModel product;

    @JsonProperty("updateable")
    private Boolean updateable;

    @JsonProperty("showVolumePrices")
    private Boolean showVolumePrices;
}
