package com.example.Student_Library_Management_System.Models;

import com.example.Student_Library_Management_System.Enums.CardStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "card")
public class Card {

    //plan is to save this card in db
    //before saving i have to set attributess
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  //auto gen
    @CreationTimestamp
    Date createdOn; // auto gen

    @UpdateTimestamp
    Date updatedOn; //auto gen
 @Enumerated(value = EnumType.STRING)//teliing sql to keeep it in string format

     private CardStatus cardStatus;  // set  this one see servicelayer of student
@OneToOne
@JoinColumn
private Student student;

//card is parent wrt book
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    List<Book> booksIssued = new ArrayList<>();

  // connecting card calss to transaction
    // bidirectional mapping
    @OneToMany(mappedBy = "card" , cascade = CascadeType.ALL)
    private List<Transactions> transactionList = new ArrayList<>();

    public List<Transactions> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transactions> transactionList) {
        this.transactionList = transactionList;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Book> getBooksIssued() {
        return booksIssued;
    }

    public void setBooksIssued(List<Book> booksIssued) {
        this.booksIssued = booksIssued;
    }

    public Card() {
       // booksIssued = new ArrayList<>();
    }

    public Card(int id, Date createdOn, Date updatedOn, CardStatus cardStatus) {
        this.id = id;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.cardStatus = cardStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }
}
