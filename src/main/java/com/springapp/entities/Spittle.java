package com.springapp.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "spittle")
public class Spittle implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name = "text")
    private String text;


    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "spitter_id")
    Spitter spitter;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    public Spittle(){}

    public Spittle(String text, Spitter spitter) {
        this.text = text;
        this.spitter = spitter;
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Spitter getSpitter() {
        return spitter;
    }

    public void setSpitter(Spitter spitter) {
        this.spitter = spitter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object obj) {
        return  obj instanceof Spittle &&
                id != 0 &&
                ((Spittle) obj).getId() == id;
    }

    @Override
    public int hashCode() {
        return (int) (super.hashCode() + 31*id+19*spitter.hashCode());
    }

    @Override
    public String toString() {
        return String.valueOf(id) + " " + text;
    }
}
