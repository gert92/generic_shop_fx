package com.sda.generic_shop_fx.controller.viewControllers;

import com.sda.generic_shop_fx.controller.CustomerController;
import com.sda.generic_shop_fx.dto.Customer;
import com.sda.generic_shop_fx.dto.Model;
import com.sda.generic_shop_fx.renderers.CustomerChoiceListCell;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseCustomer implements Initializable {
    private final CustomerController customerController = new CustomerController();
    public ComboBox<Customer> customerComboBox;
    private final ObservableList<Customer> customers = FXCollections.observableList(customerController.findAllCustomers());


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerComboBox.setCellFactory(p -> new CustomerChoiceListCell());
        customerComboBox.setButtonCell(new CustomerChoiceListCell());
        customerComboBox.setItems(customers);

        customerComboBox.setOnAction(actionEvent -> {
            Model.getInstance().getState().setCurrentCustomer(customerComboBox.getSelectionModel().getSelectedItem());
            Model.getInstance().getViewFactory().getUserSelectedMenuItem().set("BuyMenu");
        });

    }
}
