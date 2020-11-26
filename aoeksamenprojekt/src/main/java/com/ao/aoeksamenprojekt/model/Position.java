package com.ao.aoeksamenprojekt.model;

import javax.persistence.*;

@Table(name = "Position") // Indsætter værdierne i en tabel.
@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoinkrementerer ID, og bliver til en primary key
    private int ID;
    private String title;
    private String description;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Position{" +
                "ID=" + ID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
