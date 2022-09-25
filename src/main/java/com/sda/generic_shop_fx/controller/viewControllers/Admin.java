package com.sda.generic_shop_fx.controller.viewControllers;

import com.sda.generic_shop_fx.dto.Model;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Admin implements Initializable {
    public BorderPane admin_parent;
    public Button productsButton;
    public Button customersButton;
    public Button salesButton;
    public AnchorPane sideMenu;
    public Text menuText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        menuText.getStyleClass().setAll("h3", "text-primary");
//        admin_parent.setPadding(new Insets(20));
//        sideMenu.getStyleClass().setAll("panel-primary");
//        productsButton.getStyleClass().setAll("btn", "btn-primary");
//        customersButton.getStyleClass().setAll("btn", "btn-primary");
//        salesButton.getStyleClass().setAll("btn", "btn-primary");

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
    }

    private void onCustomer() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Customers");
    }

    private void onProduct(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Products");
    }

    private void onSales(){Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Sales");}

}
