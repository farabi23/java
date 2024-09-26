package com.epam.rd.autocode.assessment.appliances.model;

import java.util.Objects;

public class Client extends User {
    private String card;

    // Default Constructor
    public Client() {
    }

    // Parameterized Constructor
    public Client(long id, String name, String email, String password, String card) {
        super(id, name, email, password);
        this.card = card;
    }

    // Getter and Setter for card field
    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    // Overriding equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(card, client.card);
    }

    // Overriding hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), card);
    }

    // Overriding toString method
    @Override
    public String toString() {
        return "Client{id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", card='" + card + '\'' +
                '}';
    }
}
