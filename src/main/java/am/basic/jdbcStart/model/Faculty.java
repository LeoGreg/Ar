package am.basic.jdbcStart.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}
