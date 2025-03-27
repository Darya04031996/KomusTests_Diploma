package api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartRequestModel {
    private String productCodePost;
    private String refererCartAddedPage;
    private String from;
    private Integer qty;
}
