package am.basic.jdbcStart.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
public class MasterCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min = 0)
    private double balance;
    @Transient
    private String action;
    @Transient
    private String details;

    private int year;
}
