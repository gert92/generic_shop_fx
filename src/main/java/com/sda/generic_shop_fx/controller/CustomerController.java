package com.sda.generic_shop_fx.controller;

import com.sda.generic_shop_fx.dto.Customer;
import com.sda.generic_shop_fx.repository.CustomerRepository;

import java.util.List;

public class CustomerController {

    CustomerRepository repository = new CustomerRepository();

    public Customer addCustomer(String customerName, String customerBalance){
        Customer customer = Customer.builder()
                .name(customerName)
                .balance(Float.parseFloat(customerBalance)).build();

        return repository.createCustomer(customer);

    }

    public List<Customer> findAllCustomers() {
        return repository.showAllCustomer();
    }

    public Customer findCustomerById(Long id){
        return repository.findCustomerById(id);
    }

    public List<Customer> getAllCustomers(){
        return repository.showAllCustomer();
    }

    public void removeCustomer(Customer customer) {
        repository.removeCustomer(customer);
    }
}

