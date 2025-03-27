package api.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties
public class StockModel {
    private StockLevelStatusModel stockLevelStatus;
    private Integer stockLevel;
    private String stockStatusText;
    private Boolean show;
    private String stockStatusTextDetailed;
    private Boolean stockStatusTextExtended;
    private String productScenario;
}
