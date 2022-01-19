package com.mycompany.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="Books")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    @Column(name="bookname", nullable = false)
    private String bookName;

    private double price;

    private int warehouse;

    private int kolvo;


}
