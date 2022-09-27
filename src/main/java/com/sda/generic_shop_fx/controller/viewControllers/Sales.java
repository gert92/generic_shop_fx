package com.sda.generic_shop_fx.controller.viewControllers;

import com.dlsc.formsfx.view.controls.SimpleListViewControl;
import com.sda.generic_shop_fx.controller.CustomerController;
import com.sda.generic_shop_fx.controller.SaleController;
import com.sda.generic_shop_fx.dto.Customer;
import com.sda.generic_shop_fx.dto.Product;
import com.sda.generic_shop_fx.dto.Sale;
import com.sda.generic_shop_fx.renderers.*;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Sales implements Initializable {
//    public ListView<Sale> salesListView;
    public ComboBox<Customer> customerSelector;

    public SaleController saleController = new SaleController();
    public CustomerController customerController = new CustomerController();

    private final ObservableList<Sale> observableList = FXCollections.observableList(saleController.findAllSales());
    private final ObservableList<Customer> customersList = FXCollections.observableList(customerController.findAllCustomers());
    public TableView<Sale> salesTable;
    public TableColumn<Sale, String> deleteCol;
    public TableColumn<Sale, String> customerCol;
    public TableColumn<Sale, String> itemsCol;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerCol.setCellValueFactory(name -> new SimpleStringProperty(name.getValue().getCustomer().getName()));
//        itemsCol.setCellValueFactory(items -> new SimpleListProperty<Product>(FXCollections.observableList(items.getValue()));
        itemsCol.setCellFactory(p-> new SalesTableCell("list"));
        deleteCol.setCellFactory(delBtn -> new SalesTableCell("delete"));
        salesTable.setItems(observableList);
//        salesListView.setItems(observableList);
//        salesListView.setCellFactory(pView -> new SalesListCell());
        customerSelector.setCellFactory(v -> new CustomerChoiceListCell());
        customerSelector.setButtonCell(new CustomerChoiceListCell());
        customerSelector.setItems(customersList);
        customerSelector.setOnAction(actionEvent -> {
            filterByCustomer();
        });
    }

    private void filterByCustomer(){
        ObservableList<Sale> filteredList = FXCollections.observableList(saleController.findSalesByCustomer(customerSelector.getValue()));

//        salesTable.setCellFactory(pView -> new SalesListCell());
        salesTable.setItems(filteredList);
        salesTable.refresh();
    }
}
