package api.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class StockLevelStatusModel {
    private String code;
    private String type;
}
