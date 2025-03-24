package api.models;

import lombok.Data;

@Data
public class CompanyInfoModel {
    private String uid;
    private String name;
    private Boolean vip;
}