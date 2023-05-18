package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.AuthorEntryDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public String createAuthor(AuthorEntryDto authorEntryDto){

        //important step is : int the params : the object is of type DTO
        // but  repo only understands entities

        // solution to  this : Convert authorEntryDTo ---> Author

        //we are setting its attribute so that we can save
        //correct values in the DB.

        Author author = new Author();
        author.setName(authorEntryDto.getName());
        author.setAge(authorEntryDto.getAge());
        author.setCountry(authorEntryDto.getCountry());
        author.setRating(authorEntryDto.getRating());

       authorRepository.save(author);
       return "author added successfully";
    }
}
