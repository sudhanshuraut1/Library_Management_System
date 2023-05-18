package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.IssueBookRequestDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transactions;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import com.example.Student_Library_Management_System.Repositories.CardRepository;
import com.example.Student_Library_Management_System.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
  @Autowired
    BookRepository bookRepository;
   @Autowired
    CardRepository cardRepository;
    public String issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception{

        int bookId = issueBookRequestDto.getBookId();
        int cardId = issueBookRequestDto.getCardId();

        // get the book and card entity becausewe want to set attributes

        Book book = bookRepository.findById(bookId).get();
        Card card = cardRepository.findById(cardId).get();

        //final goal is to make Transactionentity, set its attributes and save it.

        Transactions transaction = new Transactions();
         //setting attributes
    transaction.setBook(book);
    transaction.setCard(card);
    transaction.setTransactionId(UUID.randomUUID().toString());
    transaction.setIssueOperation(true);
    transaction.setTransactionStatus(TransactionStatus.PENDING);

     // attribute left is successful / fail
        //check for validation
      if(book ==null || book.isIssueStatus()==true){

          transaction.setTransactionStatus(TransactionStatus.FALED);
          transactionRepository.save(transaction);
          throw new Exception("Book is not available");
      }

      if (card == null || card.getCardStatus()!= CardStatus.ACTIVATED){
          transaction.setTransactionStatus(TransactionStatus.FALED);
          transactionRepository.save(transaction);
          throw new Exception("card is not valid");
      }

      // we have reached success casee

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

       // set attributes of book
     book.setIssueStatus(true);
    // b/w book and transaction : bidirectional
     List<Transactions> listOfTransactionForBook = book.getLisOftransactions();
     listOfTransactionForBook.add(transaction);
     book.setLisOftransactions(listOfTransactionForBook);

     //I need to make changes in the card
      // book and the card
        List<Book> issuedBooksFForcard = card.getBooksIssued();
        issuedBooksFForcard.add(book);
        card.setBooksIssued(issuedBooksFForcard);

        //card adn the transaction : bidirectional(parent class)
     List<Transactions> transactionListForCard = card.getTransactionList();
     transactionListForCard.add(transaction);
     card.setTransactionList(transactionListForCard);

     //save the parent
        cardRepository.save(card);

        // cascading happens by saving card

        return "book isssued";

    }

   public String getTransactions(int bookId,int cardId){
        List<Transactions> transactionsList = transactionRepository.getTransactionsForBookAndCard(bookId,cardId);

        String transactionId = transactionsList.get(0).getTransactionId();
        return transactionId;
    }
}
