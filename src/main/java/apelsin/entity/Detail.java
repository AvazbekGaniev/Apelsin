package apelsin.entity;

import apelsin.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Detail extends AbsEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
    @ManyToOne
    private Product product;
    private Integer quantity;
}
