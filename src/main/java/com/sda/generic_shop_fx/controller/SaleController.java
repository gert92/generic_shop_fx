package com.sda.generic_shop_fx.controller;

import com.sda.generic_shop_fx.dto.Customer;
import com.sda.generic_shop_fx.dto.Product;
import com.sda.generic_shop_fx.dto.Sale;
import com.sda.generic_shop_fx.repository.CustomerRepository;
import com.sda.generic_shop_fx.repository.ProductRepository;
import com.sda.generic_shop_fx.repository.SaleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public class SaleController {
    SaleRepository repository = new SaleRepository();
    CustomerRepository customerRepository = new CustomerRepository();
    ProductRepository productRepository = new ProductRepository();

    public void addSale(Customer customer, List<Product> products){
        Sale sale = Sale.builder().customer(customer).product(products).build();
        repository.createSale(sale);
        customerRepository.updateCustomer(customer);
        products.forEach(productRepository::updateProduct);
    }

    public List<Sale> findSalesByCustomer(Customer customer){
        return repository.findSalesByCustomerId(customer);
    }

    public List<Sale> findAllSales(){
        return repository.findAllSales();
    }

    public void deleteSale(Sale sale){
        repository.deleteSale(sale);
    }

    public List<String> displayProductsByQuantity(Sale sale){
        Map<Product, Integer> map = new HashMap<>();
        List<String> displayList = new ArrayList<>();
        sale.getProduct()
                .forEach(product -> {
                    if (map.containsKey(product)){
                        map.put(product, map.get(product) + 1);
                    } else {
                        map.put(product, 1);
                    }
                });
        map.forEach((product, integer) -> displayList.add(product.getProductName() + " - " + product.getPrice() + "$" + " Amount: " + integer));
        return displayList;
    }


}
