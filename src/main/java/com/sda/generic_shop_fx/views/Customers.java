package com.sda.generic_shop_fx.views;

import com.sda.generic_shop_fx.controller.CustomerController;
import com.sda.generic_shop_fx.dto.Customer;
import com.sda.generic_shop_fx.renderers.CustomerTableCell;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Customers implements Initializable {
    public TextField cNameField;
    public TextField cBalanceField;
    private final CustomerController customerController = new CustomerController();
    private final ObservableList<Customer> observableList = FXCollections.observableList(customerController.findAllCustomers());
    public TableView<Customer> customerTable;
    public TableColumn<Customer, String> nameCol;
    public TableColumn<Customer, String> balanceCol;
    public TableColumn<Customer, String> deleteCol;
    public Button customerFormButton;
    public Button customerFormCancelButton;
    public Button customersReloadButton;

    private Customer editableCustomer = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameCol.setCellValueFactory(name -> new SimpleStringProperty(name.getValue().getName()));
        balanceCol.setCellValueFactory(price -> new SimpleStringProperty(String.valueOf(price.getValue().getBalance())));
        deleteCol.setCellFactory(delBtn -> new CustomerTableCell());
        customerTable.setItems(observableList);

        customerTable.setOnMouseClicked(mouseEvent -> {
            editableCustomer = customerTable.getSelectionModel().getSelectedItem();
            if (editableCustomer != null){
                cNameField.setText(editableCustomer.getName());
                cBalanceField.setText(String.valueOf(editableCustomer.getBalance()));
                customerFormButton.setText("Edit customer");
                customerFormCancelButton.setVisible(true);
                customerFormButton.setOnAction(this::editCustomer);
            }
        });

        customersReloadButton.setOnAction(e -> {
            observableList.clear();
            observableList.addAll(customerController.findAllCustomers());
            customerTable.refresh();
        });
    }

    public void addCustomer(ActionEvent actionEvent) {
        Customer customer = customerController.addCustomer(cNameField.getText(), cBalanceField.getText());
        cNameField.clear();
        cBalanceField.clear();
        observableList.add(customer);
    }

    public void editCustomer(ActionEvent actionEvent) {
        customerTable.getItems().remove(editableCustomer);
        editableCustomer.setName(cNameField.getText());
        editableCustomer.setBalance(Float.parseFloat(cBalanceField.getText()));
        customerController.updateFields(editableCustomer);
        customerTable.getItems().add(editableCustomer);
        customerTable.refresh();
        onCancel(actionEvent);
    }

    public void onCancel(ActionEvent actionEvent) {
        editableCustomer = null;
        customerFormButton.setOnAction(this::addCustomer);
        customerFormButton.setText("Add customer");
        cNameField.clear();
        cBalanceField.clear();
        customerFormCancelButton.setVisible(false);
        customerTable.getSelectionModel().clearSelection();
    }
}
