package com.mycompany.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="Customers")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    @Column(name="lastname", nullable = false)
    private String lastName;

    private String district;

    private int discount;

}
