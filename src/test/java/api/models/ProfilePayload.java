package api.models;
import lombok.Data;

@Data
public class ProfilePayload {
    private String email;
    private String fullName;
    private String gender;
    private Boolean isIndividualPerson;
    private Boolean isCurrentUserB2C;
    private Integer totalCountOfCompanies;
    private CompanyInfoModel companyInfo;
}
