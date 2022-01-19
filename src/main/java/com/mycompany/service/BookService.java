package com.mycompany.service;

import com.mycompany.model.Book;
import com.mycompany.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<Book> getNamePrice(){
        return repository.getNamePrice();
    }




}
