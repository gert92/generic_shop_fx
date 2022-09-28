package com.sda.generic_shop_fx.views;

import com.sda.generic_shop_fx.dto.Model;
import com.sda.generic_shop_fx.views.ViewFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Starter {

    public Button shoppingButton;
    ViewFactory viewFactory = new ViewFactory();
    public void onAction(ActionEvent actionEvent) {
        viewFactory.getShoppingWindow();
        Stage stage = (Stage) shoppingButton.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }

    public void onAdmin(ActionEvent actionEvent) {
        viewFactory.getAdminWindow();
        Stage stage = (Stage) shoppingButton.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
}
