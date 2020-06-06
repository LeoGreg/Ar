package am.basic.jdbcStart.repository;

import am.basic.jdbcStart.model.Card;
import am.basic.jdbcStart.model.Student;
import am.basic.jdbcStart.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Charge extends Card {
    private DataSource dataSource;
    Card f;
    int a;

    public Charge(Card from) {
        System.out.println("insert cash");
        Scanner input = new Scanner(System.in);
        this.f = from;
        this.a = input.nextInt();
    }


}

