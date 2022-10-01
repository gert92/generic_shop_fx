package com.sda.generic_shop_fx.views;

import com.sda.generic_shop_fx.controller.CustomerController;
import com.sda.generic_shop_fx.controller.SaleController;
import com.sda.generic_shop_fx.dto.Customer;
import com.sda.generic_shop_fx.dto.Sale;
import com.sda.generic_shop_fx.renderers.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Sales implements Initializable {
    public ComboBox<Customer> customerSelector;

    public SaleController saleController = new SaleController();
    public CustomerController customerController = new CustomerController();

    private final ObservableList<Sale> observableList = FXCollections.observableList(saleController.findAllSales());
    private final ObservableList<Customer> customersList = FXCollections.observableList(customerController.findAllCustomers());
    public TableView<Sale> salesTable;
    public TableColumn<Sale, String> deleteCol;
    public TableColumn<Sale, String> customerCol;
    public TableColumn<Sale, String> itemsCol;
    public Button salesReloadButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerCol.setCellValueFactory(name -> new SimpleStringProperty(name.getValue().getCustomer().getName()));
        itemsCol.setCellFactory(p-> new SalesTableCell("list"));
        deleteCol.setCellFactory(delBtn -> new SalesTableCell("delete"));
        salesTable.setItems(observableList);
        customerSelector.setCellFactory(v -> new CustomerChoiceListCell());
        customerSelector.setButtonCell(new CustomerChoiceListCell());
        customerSelector.setItems(customersList);
        customerSelector.setOnAction(actionEvent -> filterByCustomer());

        salesReloadButton.setOnAction(e -> {
            observableList.clear();
            observableList.addAll(saleController.findAllSales());
            salesTable.refresh();
        });
    }

    private void filterByCustomer(){
        ObservableList<Sale> filteredList = FXCollections.observableList(saleController.findSalesByCustomer(customerSelector.getValue()));
        salesTable.setItems(filteredList);
        salesTable.refresh();
    }
}
