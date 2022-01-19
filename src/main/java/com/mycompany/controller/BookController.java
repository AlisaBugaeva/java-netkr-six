package com.mycompany.controller;

import com.mycompany.exception.ResourseNotFoundException;
import com.mycompany.model.Book;
import com.mycompany.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class BookController {

    @Autowired
    BookRepository repository;

    @DeleteMapping("/books/{id}")
    public Map<String, Boolean> deleteBook(@PathVariable(value = "id") Integer id) throws ResourseNotFoundException {

        Book book = repository.findById(id).orElseThrow(
                () -> new ResourseNotFoundException("Book is not found by id" + id)
        );

        repository.delete(book);

        Map<String, Boolean> response = new HashMap<>();

        response.put("deleted", true);

        return response;
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book){
        return repository.save(book);
    }

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return repository.findAll();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Integer id)
            throws ResourseNotFoundException {

        Book book = repository.findById(id).orElseThrow(
                ()-> new ResourseNotFoundException("Book is not found by id" + id)
        );

        return ResponseEntity.ok().body(book);
    }

    @PutMapping("/books/{id}")
    Book replaceBook(@RequestBody Book newBook, @PathVariable Integer id)
            throws ResourseNotFoundException {

        return repository.findById(id)
                .map(book -> {
                    book.setBookName (newBook.getBookName());
                    book.setPrice (newBook.getPrice());
                    book.setWarehouse (newBook.getWarehouse());
                    book.setKolvo (newBook.getKolvo());
                    return repository.save(book);
                })
                .orElseThrow(
                        ()-> new ResourseNotFoundException("Book is not found by id" + id)
                );
    }

    @GetMapping("/booksNamePrice")
    public List<Book> getNamePrice(){
        return repository.getNamePrice();
    }





    /*@PatchMapping("/api/users/{id}")
    public void updatePartial(@PathVariable(value = "id") Integer id, @RequestBody final Book book, ) {
        repository.save(partialUpdate, id)

    }


    @PatchMapping("/books/{id}")
    public ResponseEntity<Book> changeContactById(@PathVariable(value = "id") Integer id)
            throws ResourseNotFoundException {

        Book book = repository.findById(id).orElseThrow(
                ()-> new ResourseNotFoundException("Book is not found by id" + id)
        );

        repository.id
        return ResponseEntity.ok().body(book);
    }*/




}
