package api.models;
import lombok.Data;
import java.util.List;

@Data
public class FavoriteListResponseModel {
    private List<FavoriteProductModel> payload;
}
