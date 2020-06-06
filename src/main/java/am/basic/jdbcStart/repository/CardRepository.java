package am.basic.jdbcStart.repository;

import am.basic.jdbcStart.model.Student;
import am.basic.jdbcStart.util.DataSource;
import am.basic.jdbcStart.model.Card;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CardRepository<V> {
    private DataSource dataSource;
    private Charge charge;

    public CardRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public synchronized void transfer(Card from, Card to, int amount) throws SQLException, InterruptedException {///////////////////////////////////////

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//            preparedStatement = connection.prepareStatement("UPDATE card set balance = ? WHERE client_email = ?");
//
//            preparedStatement.setInt(1, (Integer) from.getBalance() - amount);//5000
//            preparedStatement.setString(2, (String) from.getClient_email());
//            preparedStatement.executeUpdate();

//                new Thread() {
//                    public void run() {
//                        try {
//                            cardRepository.withdraw(from, amou);
//                        } catch (SQLException ex) {
//                            ex.printStackTrace();
//                        }
//                    }
//                }.start();


//                new Thread(){
//                public void run() {
//                    try {
//                        cardRepository.withdraw(from, amount);
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }.start();


//            System.out.println(Thread.currentThread().getThreadGroup());
//            preparedStatement = connection.prepareStatement("UPDATE card set balance = ? WHERE client_email = ?");
//            preparedStatement.setInt(1, (Integer) from.getBalance() - amount);
//            preparedStatement.setString(2, (String) from.getClient_email());
//            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("UPDATE card set balance = ? WHERE client_email = ?");
            preparedStatement.setInt(1, (Integer) to.getBalance() - amount);
            preparedStatement.setString(2, (String) to.getClient_email());
            preparedStatement.executeUpdate();


            preparedStatement = connection.prepareStatement("UPDATE card set balance = ? WHERE client_email = ?");
            preparedStatement.setInt(1, (Integer) to.getBalance() + amount);
            preparedStatement.setString(2, (String) to.getClient_email());
            preparedStatement.executeUpdate();


            connection.commit();


        } catch (Throwable throwable) {
            System.out.println(throwable);
            try {
                connection.rollback();
            } catch (SQLException e) {

                System.out.println(e);
            }


        }
    }


    public void INSERT_INTO(Card card) throws SQLException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO card VALUES(?,?,?,?)  ");
        preparedStatement.setString(1, (String) card.getCard_holder_name());
        preparedStatement.setString(2, (String) card.getCard_number());
        preparedStatement.setString(3, (String) card.getExpiry());
        preparedStatement.setString(4, (String) card.getClient_email());
        int result = preparedStatement.executeUpdate();
        System.out.println("result " + result);


    }

    public Card SELECT_BY_CARD_HOLDER_NAME_AND_CARD_NUMBER(String card_holder_name, String card_number) throws
            SQLException, ClassNotFoundException {
        Card card = null;
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM card WHERE card_holder_name =? AND card_number=? ");
        preparedStatement.setString(1, card_holder_name);
        preparedStatement.setString(2, card_number);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            card = fromAllResult(resultSet);
        }
        resultSet.close();

        return card;

    }

    private Card fromAllResult(ResultSet resultSet) throws SQLException {
        Card card = new Card();
        card.setCard_holder_name(resultSet.getString("card_holder_name"));
        card.setCard_number(resultSet.getString("card_number"));
        card.setExpiry(resultSet.getString("expiry"));
        card.setClient_email(resultSet.getString("client_email"));
        card.setBalance(resultSet.getInt("balance"));
        return card;

    }

    public Card SELECT_BY_CLIENT_EMAIL(String client_email) throws SQLException {
        Card card = null;


        Connection connection = dataSource.getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM card WHERE client_email=?");
        preparedStatement.setString(1, client_email);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            card = fromAllResult(resultSet);
        }
        resultSet.close();
        preparedStatement.close();
        return card;

    }


    public List<?> SELECT_ALL() throws SQLException, ClassNotFoundException {
        Card card = null;
        List<Card> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM card ");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            card = fromAllResult(resultSet);
            list.add(card);
        }
        resultSet.close();
        return list;
    }

    public List<Card> SEARCH_BY_CARD_HOLDER_NAME_AND_CARD_NUMBER(String likeNAME, String likeNUMBER) throws
            SQLException, ClassNotFoundException {
        List<Card> list = new LinkedList<>();
        Card card = null;
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM card WHERE card_holder_name LIKE(CONCAT('%',?,'%')) AND card_number LIKE(CONCAT('%',?,'%'))");
        preparedStatement.setString(1, likeNAME);
        preparedStatement.setString(2, likeNUMBER);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            card = fromAllResult(resultSet);
            list.add(card);
        }
        resultSet.close();
        preparedStatement.close();
        return list;


    }

    public void UPDATE_KEY_IS_EXPIRY(Card card) throws SQLException, ClassNotFoundException {
        System.out.println("enter expiry by it you'll update card_number = ");
        Scanner input = new Scanner(System.in);
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE card SET card_number=? WHERE  expiry=?");
        preparedStatement.setString(1, (String) card.getCard_number());
        preparedStatement.setString(2, input.nextLine());
        int resultSet = preparedStatement.executeUpdate();

        System.out.println(resultSet);
    }

    public void delete() throws SQLException, ClassNotFoundException {
        System.out.println("input id = ");
        Scanner input = new Scanner(System.in);
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM card WHERE card_holder_name = ?");
        statement.setString(1, input.nextLine());
        statement.execute();
        statement.close();
    }

    public synchronized void withdraw(Card from, int amount) throws SQLException {


        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE card SET balance =? WHERE client_email=?");
            preparedStatement.setInt(1, (Integer) from.getBalance() - 0);//20000
            preparedStatement.setString(2, (String) from.getClient_email());

            preparedStatement.executeUpdate();
            notify();

        } catch (Exception e) {
            System.out.println(e);
        }


    }

//    public synchronized void deposit(Card from, int amount) throws SQLException {
//        from.getBalance() = from.getBalance() + amount;
//        notify();
//        Connection connection = dataSource.getConnection();
//        PreparedStatement preparedStatement = null;
//        try {
//            preparedStatement = connection.prepareStatement("UPDATE card SET balance =? WHERE client_email=?");
//            preparedStatement.setInt(1, (Integer) from.getBalance() + amount);
//            preparedStatement.setString(2, (String) from.getClient_email());
//            preparedStatement.executeUpdate();
//
//            notify();
//
//        } catch (SQLException e) {
//            System.out.println(e);
//
//        }

}
//
//    public synchronized void depth(Card from, int amount) throws SQLException {
//        try {
//            wait();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        Connection connection = dataSource.getConnection();
//        PreparedStatement preparedStatement = null;
//        try {
//            preparedStatement = connection.prepareStatement("UPDATE card SET balance =? WHERE client_email=?");
//            preparedStatement.setInt(1, (Integer) from.getBalance() - amount);
//            preparedStatement.setString(2, (String) from.getClient_email());
//            preparedStatement.executeUpdate();
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                System.out.println(e);
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e);
//
//        }

