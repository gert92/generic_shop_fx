package com.sda.generic_shop_fx.views;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Getter;
import org.kordamp.bootstrapfx.BootstrapFX;


public class ViewFactory {

    private final StringProperty adminSelectedMenuItem;
    private AnchorPane productsView;
    private AnchorPane customersView;
    private AnchorPane salesView;

    public ViewFactory() {
        this.adminSelectedMenuItem = new SimpleStringProperty("");
    }

    public StringProperty getAdminSelectedMenuItem(){
        return adminSelectedMenuItem;
    }

    public void getStartingWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Starter.fxml"));
        createStage(loader);
    }


    public void getShoppingWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/shopping/shopping.fxml"));
//        Shopping shopping = new Shopping();
//        loader.setController(shopping);
        createStage(loader);
    }

    public void getAdminWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin/admin.fxml"));
        createStage(loader);
    }

    public AnchorPane getProductsView(){
        if (productsView == null){
            try {
                productsView = new FXMLLoader(getClass().getResource("/fxml/admin/product.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return productsView;
    }

    public AnchorPane getCustomersView(){
        if(customersView == null){
            try {
                customersView = new FXMLLoader(getClass().getResource("/fxml/admin/customer.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return customersView;
    }

    public AnchorPane getSalesView() {
        if (salesView ==null){
            try {
                salesView = new FXMLLoader(getClass().getResource("/fxml/admin/sales.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return salesView;
    }


    private void createStage(FXMLLoader loader){
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
//            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        } catch (Exception e){
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Generic Shop");
        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
    }
}
