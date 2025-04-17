package api.models;
import lombok.Data;

@Data
public class MindboxCartDataModel {
    private String komusIm;
    private Integer count;
    private Double pricePerItem;
    private String company;
}