package com.sda.generic_shop_fx.views;

import com.sda.generic_shop_fx.dto.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ViewFactory {

    private final StringProperty adminSelectedMenuItem;
    private final StringProperty userSelectedMenuItem;
    private AnchorPane productsView;
    private AnchorPane customersView;
    private AnchorPane salesView;
    private AnchorPane chooseCustomerView;

    private AnchorPane buyMenuView;

    public ViewFactory() {
        this.adminSelectedMenuItem = new SimpleStringProperty("");
        this.userSelectedMenuItem = new SimpleStringProperty("");
    }

    public StringProperty getAdminSelectedMenuItem(){
        return adminSelectedMenuItem;
    }
    public StringProperty getUserSelectedMenuItem(){return userSelectedMenuItem;}

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
                productsView = new FXMLLoader(getClass().getResource("/fxml/admin/product/product.fxml")).load();
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

    public AnchorPane getChooseCustomerView(){
        if (chooseCustomerView == null){
            try {
                chooseCustomerView = new FXMLLoader(getClass().getResource("/fxml/shopping/chooseCustomer.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return chooseCustomerView;
    }

    public AnchorPane getBuyMenuView(){
        if (buyMenuView == null){
            try {
                buyMenuView = new FXMLLoader(getClass().getResource("/fxml/shopping/buyMenu.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return buyMenuView;
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
