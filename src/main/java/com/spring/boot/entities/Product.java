package com.spring.boot.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    public Product(long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
}
