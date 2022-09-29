package com.sda.generic_shop_fx.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String productName;
    private Long quantity;
    private Double price;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Sale> sales = new ArrayList<>();

    public Product(Long id, String productName, Long quantity, Double price) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
}