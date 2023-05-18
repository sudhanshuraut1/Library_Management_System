package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.BookRequestDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;
    public String addBook(BookRequestDto bookRequestDto){
          // i want to get authorentity ??
       // this was before DTO ---> int authorId= book.getAuthor().getId();

        int authorId = bookRequestDto.getAuthorId();

        //now i will be fetching the authorentity

        Author author = authorRepository.findById(authorId).get();
        // do exception handling here if if throws  not found

        //we have created  this Entity so that we can save it into the DB.
        Book book = new Book();
        // BAsic attrbutes are being set from Dto to the Entity layer
        book.setGenre(bookRequestDto.getGenre());
        book.setIssueStatus(false);
        book.setName(bookRequestDto.getName());
        book.setPages(bookRequestDto.getPages());


        //basic attributes are already set from postman
        // setting the foreign key attr in the child class:

        book.setAuthor(author);

        // update the list of books written in parent class

        List<Book> currentBooksWritten = author.getBooksWritten();
        currentBooksWritten.add(book);

        author.setBooksWritten(currentBooksWritten);

        // now the book is to be saved but also the author is to be saved becuase author is updated
        // only saving author as book will be automatically saved as its child to author  & CAScading effect
        authorRepository.save(author); // date was modified
        // .save acts as both update  and save function
        // if PK is not present it creates new obj else it updates entity attribute

        // bookrepo.save is not req

        return "book added successfully";


    }
}
