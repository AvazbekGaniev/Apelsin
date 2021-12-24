package apelsin.payload;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDTO {
    private int custId;

    private int productId;
    private int quantity;
    private Date due;

}
