package com.sda.generic_shop_fx.views;

import com.sda.generic_shop_fx.controller.ProductController;
import com.sda.generic_shop_fx.controller.SaleController;
import com.sda.generic_shop_fx.dto.Customer;
import com.sda.generic_shop_fx.dto.Model;
import com.sda.generic_shop_fx.dto.Product;
import com.sda.generic_shop_fx.renderers.ProductTableCell;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Buy implements Initializable {
    public TableView<Product> shoppingCartTable;
    public TableView<Product> productsTable;
    public Label userLabel;
    public StringProperty userLabelProperty = new SimpleStringProperty("");
    public Label balanceLabel;
    public StringProperty balanceLabelProperty = new SimpleStringProperty("");
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
    public Label noMoneyLabel;
    public StringProperty errorLabel = new SimpleStringProperty("");
    private Customer currentCustomer;
    private final ProductController productController = new ProductController();
    private final SaleController saleController = new SaleController();
    private final ObservableList<Product> products = FXCollections.observableList(productController.displayAllAvailableProducts());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setBindings();

        currentCustomer = Model.getInstance().getState().getCurrentCustomer().get();
        userLabelProperty.set(currentCustomer.getName());
        balanceLabelProperty.set(String.format("Your Balance: %.2f$", currentCustomer.getBalance()));
        productsTable.setItems(products);

        pName.setCellValueFactory(pName -> new SimpleStringProperty(pName.getValue().getProductName()));
        pPrice.setCellValueFactory(pPrice -> new SimpleStringProperty(pPrice.getValue().getPrice().toString()));
        pQty.setCellValueFactory(pQty -> new SimpleStringProperty(pQty.getValue().getQuantity().toString()));
        pQtyAction.setCellFactory(p -> new ProductTableCell("shop"));
        pAddAction.setCellFactory(p -> new ProductTableCell("cart"));


        shoppingProductName.setCellValueFactory(name -> new SimpleStringProperty(name.getValue().getProductName()));
        shoppingProductQty.setCellValueFactory(qty -> new SimpleStringProperty(Model.getInstance().getState().getShoppingList().get(qty.getValue()).toString()));
        shoppingActions.setCellFactory(sAction -> new ProductTableCell("cartQty"));



        setListeners();
        setActions();


    }

    private void setBindings(){
        noMoneyLabel.textProperty().bind(errorLabel);
        balanceLabel.textProperty().bind(balanceLabelProperty);
        userLabel.textProperty().bind(userLabelProperty);
    }

    private void setListeners() {
        Model.getInstance().getState().getShoppingList().addListener((observableValue, oldValue, newValue) -> {
            shoppingCartTable.setItems(FXCollections.observableList(newValue.keySet().stream().toList()));
            if (currentCustomer.getBalance() < getTotalCost(newValue)) {
                totalCost.setTextFill(Paint.valueOf("#CB463F"));
            } else {
                totalCost.setTextFill(Paint.valueOf("#000000"));
            }
            totalCost.setText(String.format("Total Cost: %.2f$", getTotalCost(newValue)));
            shoppingCartTable.refresh();
            productsTable.refresh();
        });


        Model.getInstance().getState().getCurrentCustomer().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                currentCustomer = newValue;
                userLabelProperty.set(currentCustomer.getName());
                balanceLabelProperty.set(String.format("Your Balance: %.2f$", currentCustomer.getBalance()));
            }
        });
    }

    private void setActions() {
        buyButton.setOnAction(e -> {
            if (canBuy()) {
                double balance = currentCustomer.getBalance() - getTotalCost(Model.getInstance().getState().getShoppingList());
                currentCustomer.setBalance((float) balance);
                balanceLabelProperty.set(String.format("Your Balance: %.2f$", balance));
                saleController.addSale(currentCustomer, mapToList(Model.getInstance().getState().getShoppingList()));
                Model.getInstance().getState().getShoppingList().clear();
                shoppingCartTable.refresh();
                productsTable.refresh();
            } else {
                errorLabel.set("You do not have enough balance to make a purchase!");
                noMoneyLabel.setVisible(true);
            }
        });
    }

    private boolean canBuy() {
        return shoppingCartTable.getItems().size() > 0 && currentCustomer.getBalance() - getTotalCost(Model.getInstance().getState().getShoppingList()) > 0;
    }

    private List<Product> mapToList(Map<Product, Integer> map) {
        List<Product> list = new ArrayList<>();
        map.forEach((product, integer) -> {
            for (int i = 0; i < integer; i++) {
                list.add(product);
            }
        });
        return list;
    }

    private Double getTotalCost(Map<Product, Integer> values) {
        DoubleProperty total = new SimpleDoubleProperty(0);
        values.forEach((product, integer) -> total.set(total.get() + (product.getPrice() * integer)));
        return total.getValue();
    }
}
