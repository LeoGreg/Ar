package am.basic.jdbcStart.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String nameAndSurname;

    @NotBlank
    @Size(min = 12)
    private String cardNumber;

    @OneToMany
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    List<SaleCard> cards;

    @ManyToMany
    @JoinTable(name = "buyers_stores",
            joinColumns = @JoinColumn(name = "buyer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "saleStore_id", referencedColumnName = "id"))
    List<SaleStore> stores;
    @Transient
    private int buyerAge;
}
