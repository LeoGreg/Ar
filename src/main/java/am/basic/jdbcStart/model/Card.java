package am.basic.jdbcStart.model;

import java.util.Objects;

public class Card {
    private String card_holder_name;
    private String card_number;
    private String expiry;
    private String client_email;
    private int balance;

    public String getCard_holder_name() {
        return card_holder_name;
    }

    public void setCard_holder_name(String card_holder_name) {
        this.card_holder_name = card_holder_name;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getClient_email() {
        return client_email;
    }

    public void setClient_email(String client_email) {
        this.client_email = client_email;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Card client = (Card) o;
        return card_number == client.card_number
                &&
                (
                        card_holder_name == client.card_holder_name
                                || (
                                card_holder_name != null
                                        &&
                                        card_holder_name.equals(client.card_holder_name
                                        )
                        )
                )
                &&
                (
                        client_email == client.client_email
                                ||
                                (
                                        client_email != null
                                                &&
                                                client_email.equals(client.client_email)))
                &&
                (
                        expiry == client.expiry
                                ||
                                (
                                        expiry != null
                                                &&
                                                expiry.equals(client.expiry
                                                )
                                )
                );

    }

    public int hashCode() {
        return Objects.hash(card_holder_name, card_number, client_email, expiry);
    }

    @Override
    public String toString() {
        return "Card{" +
                "card_holder_name='" + card_holder_name + '\'' +
                ", card_number='" + card_number + '\'' +
                ", expiry='" + expiry + '\'' +
                ", client_email='" + client_email + '\'' +
                '}';
    }
}
