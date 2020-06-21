package am.basic.jdbcStart.model;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String owner_name;

    private String owner_surname;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private List<Stock> stockList;
}
