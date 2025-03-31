package api.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FavoriteListResponseModel {
    private List<FavoriteProductModel> payload;
}
