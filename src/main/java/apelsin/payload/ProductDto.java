package apelsin.payload;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private int categoryId;
    private String description;
    private double price;

//    private int photoId;
}
