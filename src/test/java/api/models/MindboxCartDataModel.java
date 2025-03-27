package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties
public class MindboxCartDataModel {
    private String komusIm;
    private Integer count;
    private Double pricePerItem;
    private String company;
}