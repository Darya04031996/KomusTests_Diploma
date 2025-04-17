package api.models;
import lombok.Data;

@Data
public class StockModel {
    private StockLevelStatusModel stockLevelStatus;
    private Integer stockLevel;
    private String stockStatusText;
    private Boolean show;
    private String stockStatusTextDetailed;
    private Boolean stockStatusTextExtended;
    private String productScenario;
}
