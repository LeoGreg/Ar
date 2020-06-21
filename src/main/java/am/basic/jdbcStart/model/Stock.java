package am.basic.jdbcStart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Stock {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int stock_percentage;

}
