package com.epam.rd.autocode.assessment.appliances.model;

import java.util.Objects;

public class Client extends User{
    private String card;

    public Client() {
    }

    public Client(long id, String name, String email, String password, String card) {
        super(id, name, email, password);
        this.card = card;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(card, client.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), card);
    }

    @Override
    public String toString() {
        return "Client{id="+super.getId()+
                ", name='"+super.getName()+'\'' +
                ", email='"+super.getEmail()+'\'' +
                ", password='"+super.getPassword()+'\'' +
                ", card='" + card + '\'' +
                '}';
    }
}
