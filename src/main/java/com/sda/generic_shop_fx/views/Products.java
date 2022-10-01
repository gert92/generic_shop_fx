package com.sda.generic_shop_fx.views;

import com.sda.generic_shop_fx.controller.ProductController;
import com.sda.generic_shop_fx.dto.Product;
import com.sda.generic_shop_fx.renderers.ProductTableCell;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Products implements Initializable {
    public TextField pNameField;
    public TextField pPriceField;
    public TextField pQuantityField;
    @FXML
    public TableView<Product> productTable;
    public TableColumn<Product, String> nameCol;
    public TableColumn<Product, String> priceCol;
    public TableColumn<Product, String> qtyCol;
    public TableColumn<Product, String> editCol;
    public TableColumn<Product, String> deleteCol;
    @FXML
    public AnchorPane product_parent;
    private final ProductController productController = new ProductController();
    private final ObservableList<Product> observableList = FXCollections.observableList(productController.displayAllAvailableProducts());
    public Button productFormButton;
    public Button productFormCancelButton;
    public Button productsReloadButton;

    private Product editableProduct = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nameCol.setCellValueFactory(name -> new SimpleStringProperty(name.getValue().getProductName()));
        priceCol.setCellValueFactory(price -> new SimpleStringProperty(price.getValue().getPrice().toString()));
        qtyCol.setCellValueFactory(qty -> new SimpleStringProperty(qty.getValue().getQuantity().toString()));
        deleteCol.setCellFactory(delBtn -> new ProductTableCell("delete"));
        productTable.setItems(observableList);


        productTable.setOnMouseClicked(mouseEvent -> {
            editableProduct = productTable.getSelectionModel().getSelectedItem();
            if (editableProduct != null){
                pNameField.setText(editableProduct.getProductName());
                pPriceField.setText(editableProduct.getPrice().toString());
                pQuantityField.setText(editableProduct.getQuantity().toString());
                productFormButton.setText("Edit product");
                productFormCancelButton.setVisible(true);
                productFormButton.setOnAction(this::editProduct);
            }
        });

        productsReloadButton.setOnAction(e -> {
            observableList.clear();
            observableList.addAll(productController.findAllAvailableProducts());
            productTable.refresh();
        });

    }

    public void addProduct(ActionEvent actionEvent) {
        Product product = productController.addProduct(pNameField.getText(), pQuantityField.getText(), pPriceField.getText());
        pNameField.clear();
        pPriceField.clear();
        pQuantityField.clear();
        observableList.add(product);
    }

    public void editProduct(ActionEvent actionEvent) {
        productTable.getItems().remove(editableProduct);
        editableProduct.setProductName(pNameField.getText());
        editableProduct.setPrice(Double.valueOf(pPriceField.getText()));
        editableProduct.setQuantity(Long.valueOf(pQuantityField.getText()));
        productController.updateProduct(editableProduct);
        productTable.getItems().add(editableProduct);
        productTable.refresh();
        formCancel(actionEvent);
    }

    public void formCancel(ActionEvent actionEvent) {
        editableProduct = null;
        productFormButton.setOnAction(this::addProduct);
        productFormButton.setText("Add product");
        pNameField.clear();
        pPriceField.clear();
        pQuantityField.clear();
        productFormCancelButton.setVisible(false);
        productTable.getSelectionModel().clearSelection();
    }
}
