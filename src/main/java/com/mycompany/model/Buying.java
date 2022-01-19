package com.mycompany.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Buy")
@Data
public class Buying {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name="ordernumber", nullable = false)
    private int orderNumber;

    @Column(name="buyingdate", nullable = false)
    private Date buyingDate;

    private int shop;

    private int customer;

    private int book;

    private int kolvo;

    @Column(name="buyingsum", nullable = false)
    private double buyingSum;
}
