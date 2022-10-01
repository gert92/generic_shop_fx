package com.sda.generic_shop_fx.controller;


import com.sda.generic_shop_fx.dto.Product;
import com.sda.generic_shop_fx.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ProductController {

    ProductRepository repository = new ProductRepository();
    SaleController saleController = new SaleController();

    public List<Product> findAllProducts(){
        return repository.findAllProducts();
    }
    public Product addProduct(String productName, String productQuantity, String productPrice){
        Product product = Product.builder()
                .productName(productName)
                .quantity(Long.valueOf(productQuantity))
                .price(Double.valueOf(productPrice))
                .build();

        repository.createProduct(product);
        return product;

    }

    public void displayAllProducts(){
        List<Product> products = findAllProducts();
        products.forEach(System.out::println);
    }

    public List<Product> displayAllAvailableProducts(){
        return repository.findAllAvailableProducts();
    }


    public Product findProduct(Long id){
        return repository.findProductById(id);
    }

    public void updateProduct(Product product){
        repository.updateProduct(product);
        System.out.println("Product Updated.");
    }

    public void deleteProduct(Product product){
        repository.deleteProduct(product);
    }



}
