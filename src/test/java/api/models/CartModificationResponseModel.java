package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.KeyStore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartModificationResponseModel {
    @JsonProperty("statusCode")
    private String statusCode;

    @JsonProperty("quantityAdded")
    private Integer quantityAdded;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("entry")
    private EntryResponseModel entry;

    @JsonProperty("mindboxCartData")
    private List<MindboxCartDatumResponse> mindboxCartData;

    @JsonProperty("coins")
    private Integer coins;
}