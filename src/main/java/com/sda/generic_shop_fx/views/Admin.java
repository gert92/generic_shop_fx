package com.sda.generic_shop_fx.views;

import com.sda.generic_shop_fx.dto.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Admin implements Initializable {
    public BorderPane admin_parent;
    public Button productsButton;
    public Button customersButton;
    public Button salesButton;
    public AnchorPane sideMenu;
    public Text menuText;
    public Button shoppingMenuButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal){
                case "Products" -> admin_parent.setCenter(Model.getInstance().getViewFactory().getProductsView());
                case "Customers" -> admin_parent.setCenter(Model.getInstance().getViewFactory().getCustomersView());
                case "Sales" -> admin_parent.setCenter(Model.getInstance().getViewFactory().getSalesView());
                default -> admin_parent.setCenter(Model.getInstance().getViewFactory().getProductsView());
            }
        });
    }

    private void addListeners(){
        productsButton.setOnAction(e -> onProduct());
        customersButton.setOnAction(e-> onCustomer());
        salesButton.setOnAction(e->onSales());
        shoppingMenuButton.setOnAction(e->onShopping());
    }

    private void onShopping() {
        Stage stage = (Stage) shoppingMenuButton.getScene().getWindow();
        Model.getInstance().getViewFactory().getShoppingWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }

    private void onCustomer() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Customers");
    }

    private void onProduct(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Products");
    }

    private void onSales(){Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Sales");}

}
