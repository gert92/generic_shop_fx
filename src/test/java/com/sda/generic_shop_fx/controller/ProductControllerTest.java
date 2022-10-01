package com.sda.generic_shop_fx.controller;

import com.sda.generic_shop_fx.dto.Product;
import com.sda.generic_shop_fx.repository.ProductRepository;
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
class ProductControllerTest {
    @Mock
    private static ProductRepository productRepository;
    @Mock
    private SaleController saleController;
    private AutoCloseable autoCloseable;
    private ProductController underTest;

    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ProductController(productRepository, saleController);
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    @Order(1)
    void testFindAllProducts() {
        underTest.findAllProducts();
        verify(productRepository).findAllProducts();
    }

    @Test
    @Order(2)
    void testAddProduct() {
        Product product1 = new Product(20L, "Bag of D", 150L, 12.22);
        productRepository.createProduct(product1);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);

        verify(productRepository).createProduct(productArgumentCaptor.capture());

        Product capturedProduct =  productArgumentCaptor.getValue();

        assertThat(capturedProduct).isEqualTo(product1);
    }

    @Test
    @Order(3)
    void displayAllProducts() {
        underTest.displayAllProducts();
        verify(productRepository).findAllProducts();
    }

    @Test
    @Order(4)
    void testDisplayAllAvailableProducts() {
        underTest.displayAllAvailableProducts();
        verify(productRepository).findAllAvailableProducts();
    }

    @Test
    @Order(5)
    void testFindProductById() {
        Product product1 = new Product(21L, "Bag of D", 150L, 12.22);
        underTest.findProduct(21L);
        verify(productRepository).findProductById(21L);
    }

    @Test
    @Order(6)
    void testUpdateProduct() {
        Product product1 = new Product(22L, "Bag of D", 150L, 12.22);
        product1.setProductName("Box of D");
        product1.setPrice(15.95);
        productRepository.updateProduct(product1);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);

        verify(productRepository).updateProduct(productArgumentCaptor.capture());

        Product capturedProduct =  productArgumentCaptor.getValue();

        assertThat(capturedProduct).isEqualTo(product1);
    }

    @Test
    @Order(7)
    void testDeleteProduct() {
        Product product1 = new Product(22L, "Bag of D", 150L, 12.22);
        productRepository.deleteProduct(product1);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);

        verify(productRepository).deleteProduct(productArgumentCaptor.capture());

        Product capturedProduct =  productArgumentCaptor.getValue();

        assertThat(capturedProduct).isEqualTo(product1);
    }
}