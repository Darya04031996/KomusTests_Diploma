package api.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class MindboxCartDataModel {
    private String komusIm;
    private Integer count;
    private Double pricePerItem;
    private String company;
}