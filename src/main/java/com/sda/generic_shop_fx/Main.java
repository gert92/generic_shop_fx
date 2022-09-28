package com.sda.generic_shop_fx;

import com.sda.generic_shop_fx.dto.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewFactory().getStartingWindow();
    }
}
