package com.mycompany.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="Shops")
@Data
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    @Column(name="shopname", nullable = false)
    private String shopName;

    private String district;

    private int commission;
}
