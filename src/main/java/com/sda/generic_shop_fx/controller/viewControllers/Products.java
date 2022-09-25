package com.sda.generic_shop_fx.controller.viewControllers;

import com.sda.generic_shop_fx.controller.ProductController;
import com.sda.generic_shop_fx.dto.Product;
import com.sda.generic_shop_fx.renderers.ProductListCell;
import com.sda.generic_shop_fx.repository.ProductRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Products implements Initializable {
    public TextField pNameField;
    public TextField pPriceField;
    public TextField pQuantityField;
    @FXML
    private ListView<Product> productListView;
    private final ProductController productController = new ProductController();
    private final ObservableList<Product> observableList = FXCollections.observableList(productController.findAllAvailableProducts());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        productListView.setItems(observableList);
        productListView.setCellFactory(pView -> new ProductListCell());
    }

    public void addProduct(ActionEvent actionEvent) {
        Product product = productController.addProduct(pNameField.getText(), pQuantityField.getText(), pPriceField.getText());
        pNameField.clear();
        pPriceField.clear();
        pQuantityField.clear();
        observableList.add(product);
    }
}
