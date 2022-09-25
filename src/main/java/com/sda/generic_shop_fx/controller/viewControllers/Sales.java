package com.sda.generic_shop_fx.controller.viewControllers;

import com.sda.generic_shop_fx.controller.CustomerController;
import com.sda.generic_shop_fx.controller.SaleController;
import com.sda.generic_shop_fx.dto.Customer;
import com.sda.generic_shop_fx.dto.Sale;
import com.sda.generic_shop_fx.renderers.CustomerChoiceListCell;
import com.sda.generic_shop_fx.renderers.CustomerListCell;
import com.sda.generic_shop_fx.renderers.SalesListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class Sales implements Initializable {
    public ListView<Sale> salesListView;
    public ComboBox<Customer> customerSelector;

    public SaleController saleController = new SaleController();
    public CustomerController customerController = new CustomerController();

    private final ObservableList<Sale> observableList = FXCollections.observableList(saleController.findAllSales());
    private final ObservableList<Customer> customersList = FXCollections.observableList(customerController.findAllCustomers());


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        salesListView.setItems(observableList);
        salesListView.setCellFactory(pView -> new SalesListCell());
        customerSelector.setCellFactory(v -> new CustomerChoiceListCell());
        customerSelector.setButtonCell(new CustomerChoiceListCell());
        customerSelector.setItems(customersList);
        customerSelector.setOnAction(actionEvent -> {
            filterByCustomer();
        });
    }

    private void filterByCustomer(){
        ObservableList<Sale> filteredList = FXCollections.observableList(saleController.findSalesByCustomer(customerSelector.getValue()));
        System.out.println(filteredList);
        salesListView.setCellFactory(pView -> new SalesListCell());
        salesListView.setItems(filteredList);
    }
}
