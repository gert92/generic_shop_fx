package com.sda.generic_shop_fx.controller.viewControllers;

import com.sda.generic_shop_fx.dto.Customer;
import com.sda.generic_shop_fx.dto.Model;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Shopping implements Initializable {

    public AnchorPane sideMenu;
    public Button buyButton;
    public Button adminMenuButton;
    public Button purchasesButton;
    public BorderPane user_parent;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    user_parent.setMinWidth(1000);
    user_parent.setMinHeight(600);

        Model.getInstance().getViewFactory().getUserSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case "ChooseCustomer" ->
                        user_parent.setCenter(Model.getInstance().getViewFactory().getChooseCustomerView());
                case "BuyMenu" -> user_parent.setCenter(Model.getInstance().getViewFactory().getBuyMenuView());
                default -> user_parent.setCenter(Model.getInstance().getViewFactory().getChooseCustomerView());
            }
        });
        onChooseCustomer();
    }

    private void addListeners() {
        adminMenuButton.setOnAction(e -> onAdmin());

    }

    private void onAdmin() {
        Stage stage = (Stage) adminMenuButton.getScene().getWindow();
        Model.getInstance().getViewFactory().getAdminWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }


    private void onChooseCustomer() {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set("ChooseCustomer");
    }



}
