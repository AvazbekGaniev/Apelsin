package apelsin.entity;

import apelsin.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends AbsEntity {
    private String name;

    @Column(length = 3)
    private String country;
    private String address;

    @Column(length = 50)
    private String phone;
}
