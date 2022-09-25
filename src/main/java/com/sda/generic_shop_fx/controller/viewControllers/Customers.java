package com.sda.generic_shop_fx.controller.viewControllers;

import com.sda.generic_shop_fx.controller.CustomerController;
import com.sda.generic_shop_fx.controller.ProductController;
import com.sda.generic_shop_fx.dto.Customer;
import com.sda.generic_shop_fx.dto.Product;
import com.sda.generic_shop_fx.renderers.CustomerListCell;
import com.sda.generic_shop_fx.renderers.ProductListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Customers implements Initializable {
    public ListView<Customer> customerListView;
    public TextField cNameField;
    public TextField cBalanceField;

    private final CustomerController customerController = new CustomerController();
    private final ObservableList<Customer> observableList = FXCollections.observableList(customerController.findAllCustomers());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerListView.setItems(observableList);
        customerListView.setCellFactory(pView -> new CustomerListCell());
    }

    public void addCustomer(ActionEvent actionEvent) {
        Customer customer = customerController.addCustomer(cNameField.getText(), cBalanceField.getText());
        cNameField.clear();
        cBalanceField.clear();
        observableList.add(customer);
    }
}
