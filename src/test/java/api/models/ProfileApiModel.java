package api.models;
import lombok.Data;

@Data
public class ProfileApiModel {
    private String email;
    private String fullName;
    private String gender;
    private Boolean isIndividualPerson;
    private Boolean isCurrentUserB2C;
    private Integer totalCountOfCompanies;
    private CompanyInfoModel companyInfo;
}
