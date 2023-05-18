package com.example.Student_Library_Management_System.Models;

import com.example.Student_Library_Management_System.Enums.Genre;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int pages;
   @Enumerated(value = EnumType.STRING)
    private Genre genre;

   // book is child to author
    //setting here the foreign key : Standard 3 steps

    @ManyToOne
    @JoinColumn //add an extra attribute of authorid (parent table PK) for the foreign key of child table
    private Author author;

    // book is also child wrt Card

    public List<Transactions> getLisOftransactions() {
        return lisOftransactions;
    }

    public void setLisOftransactions(List<Transactions> lisOftransactions) {
        this.lisOftransactions = lisOftransactions;
    }

    @ManyToOne
    @JoinColumn
    private Card card;

    private boolean issueStatus;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Transactions> lisOftransactions = new ArrayList<>();

    public boolean isIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(boolean issueStatus) {
        this.issueStatus = issueStatus;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Book() {
    }

    public Book(int id, String name, int pages) {
        this.id = id;
        this.name = name;
        this.pages = pages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
