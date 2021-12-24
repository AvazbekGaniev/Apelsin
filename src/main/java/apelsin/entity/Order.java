package apelsin.entity;


import apelsin.entity.template.AbsEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order extends AbsEntity {
    private Date date;
    @ManyToOne
    private Customer customer;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy="order",cascade = CascadeType.ALL)
    private List<Detail> details;


}
