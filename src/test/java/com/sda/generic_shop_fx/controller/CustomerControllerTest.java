package com.sda.generic_shop_fx.controller;

import com.sda.generic_shop_fx.dto.Customer;
import com.sda.generic_shop_fx.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class CustomerControllerTest {

    @Mock
    private static CustomerRepository customerRepository;
    private AutoCloseable autoCloseable;
    private CustomerController underTest;

    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CustomerController(customerRepository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    @Order(1)
    void testAddCustomer() {
        Customer customer1 = new Customer(10L, "Someone",200F);
        customerRepository.createCustomer(customer1);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerRepository).createCustomer(customerArgumentCaptor.capture());

        Customer capturedCustomer =  customerArgumentCaptor.getValue();

        assertThat(capturedCustomer).isEqualTo(customer1);
    }

    @Test
    @Order(2)
    void testUpdateCustomerFields() {
        Customer customer1 = new Customer(11L, "Someone",200F);
        customer1.setName("Newone");
        customer1.setBalance(159F);
        underTest.updateFields(customer1);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerRepository).updateCustomer(customerArgumentCaptor.capture());

        Customer capturedCustomer =  customerArgumentCaptor.getValue();

        assertThat(capturedCustomer).isEqualTo(customer1);
    }

    @Test
    @Order(3)
    void testFindAllCustomers() {
        underTest.findAllCustomers();
        verify(customerRepository).showAllCustomer();
    }

    @Test
    @Order(4)
    void testFindCustomerById() {
        Customer customer1 = new Customer(12L, "Someone",200F);
        underTest.findCustomerById(12L);
        verify(customerRepository).searchCustomerById(12L);
    }

    @Test
    @Order(5)
    void getAllCustomers() {
        underTest.getAllCustomers();
        verify(customerRepository).showAllCustomer();
    }

    @Test
    @Order(6)
    void testRemoveCustomer() {
        Customer customer1 = new Customer(13L, "Someone",200F);
        customerRepository.removeCustomer(customer1);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerRepository).removeCustomer(customerArgumentCaptor.capture());

        Customer capturedCustomer =  customerArgumentCaptor.getValue();

        assertThat(capturedCustomer).isEqualTo(customer1);
    }
}