package com.sda.generic_shop_fx.views;

import com.sda.generic_shop_fx.dto.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Shopping implements Initializable {

    public AnchorPane sideMenu;
    public Button buyButton;
    public Button adminMenuButton;
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
    }


    private void addListeners() {
        adminMenuButton.setOnAction(e -> onAdmin());
        buyButton.setOnAction(e -> onBuy());

    }

    private void onAdmin() {
        Stage stage = (Stage) adminMenuButton.getScene().getWindow();
        Model.getInstance().getViewFactory().getAdminWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set("");
        Model.getInstance().getState().getCurrentCustomer().set(null);
    }

    private void onBuy(){
        if (Model.getInstance().getState().getCurrentCustomer().get() != null){
            Model.getInstance().getViewFactory().getUserSelectedMenuItem().set("BuyMenu");
        } else {
            onChooseCustomer();
        }
    }

    private void onChooseCustomer() {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set("ChooseCustomer");
    }



}
