package com.sda.generic_shop_fx.controller;

import com.sda.generic_shop_fx.dto.Customer;
import com.sda.generic_shop_fx.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CustomerController {

    CustomerRepository repository = new CustomerRepository();

    public Customer addCustomer(String customerName, String customerBalance){
        Customer customer = Customer.builder()
                .name(customerName)
                .balance(Float.parseFloat(customerBalance)).build();

        return repository.createCustomer(customer);

    }

    public void updateFields(Customer customer) {
        repository.updateCustomer(customer);
        System.out.println("Customer Updated.");
    }

    public List<Customer> findAllCustomers() {
        return repository.showAllCustomer();
    }

    public Customer findCustomerById(Long id){
        return repository.searchCustomerById(id);
    }

    public List<Customer> getAllCustomers(){
        return repository.showAllCustomer();
    }

    public void removeCustomer(Customer customer) {
        repository.removeCustomer(customer);
    }
}

