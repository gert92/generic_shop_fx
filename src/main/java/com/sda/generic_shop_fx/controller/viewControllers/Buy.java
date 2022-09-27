package com.sda.generic_shop_fx.controller.viewControllers;

import com.sda.generic_shop_fx.controller.ProductController;
import com.sda.generic_shop_fx.dto.Customer;
import com.sda.generic_shop_fx.dto.Model;
import com.sda.generic_shop_fx.dto.Product;
import com.sda.generic_shop_fx.renderers.ProductTableCell;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Buy implements Initializable {
    public TableView<Product> shoppingCartTable;
    public TableView<Product> productsTable;
    public Label userLabel;
    public Label balanceLabel;
    public Button buyButton;
    public TableColumn<Product, String> shoppingProductName;
    public TableColumn<Product, String> shoppingProductQty;
    public TableColumn<Product, String> shoppingActions;
    public TableColumn<Product, String> pName;
    public TableColumn<Product, String> pPrice;
    public TableColumn<Product, String> pQty;
    public TableColumn<Product, String> pQtyAction;
    public TableColumn<Product, String> pAddAction;
    public AnchorPane parent;
    public Label totalCost;

    private Customer currentCustomer;

    private final ProductController productController = new ProductController();

    private final ObservableList<Product> products = FXCollections.observableList(productController.findAllAvailableProducts());

    private final ObservableMap<Product, Integer> shopList = new SimpleMapProperty<>(FXCollections.observableHashMap());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            currentCustomer = Model.getInstance().getState().getCurrentCustomer();
            productsTable.setItems(products);
            pName.setCellValueFactory(pName -> new SimpleStringProperty(pName.getValue().getProductName()));
            pPrice.setCellValueFactory(pPrice -> new SimpleStringProperty(pPrice.getValue().getPrice().toString()));
            pQty.setCellValueFactory(pQty -> new SimpleStringProperty(pQty.getValue().getQuantity().toString()));
            pQtyAction.setCellFactory(p -> new ProductTableCell("shop"));
            pAddAction.setCellFactory(p -> new ProductTableCell("cart"));

            userLabel.setText(currentCustomer.getName());
            balanceLabel.setText("Your balance: " + currentCustomer.getBalance() + "$");

            shoppingProductName.setCellValueFactory(name -> new SimpleStringProperty(name.getValue().getProductName()));
            shoppingProductQty.setCellValueFactory(qty -> new SimpleStringProperty(Model.getInstance().getState().getShoppingList().get(qty.getValue()).toString()));

//            shoppingCartTable.setItems(FXCollections.observableList(Model.getInstance().getState().getShoppingList().keySet().stream().toList()));

            Model.getInstance().getState().getShoppingList().addListener((observableValue, oldValue, newValue) -> {
                shoppingCartTable.setItems(FXCollections.observableList(newValue.keySet().stream().toList()));
                if (currentCustomer.getBalance() < getTotalCost(newValue)){
                    totalCost.setTextFill(Paint.valueOf("#CB463F"));
                }
                totalCost.setText(String.format("Total Cost: %.2f$", getTotalCost(newValue)));
                shoppingCartTable.refresh();
                productsTable.refresh();
            });
    }

    private Double getTotalCost(Map<Product, Integer> values){
        DoubleProperty total = new SimpleDoubleProperty(0);
        values.forEach((product, integer) -> {
            total.set(total.get() + (product.getPrice() * integer));
        });
        return total.getValue();
    }
}
