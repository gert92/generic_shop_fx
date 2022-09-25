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
public class Sale {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "sales", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Product> product = new ArrayList<>();


}
