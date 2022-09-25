package com.sda.generic_shop_fx.renderers;

import com.sda.generic_shop_fx.controller.CustomerController;
import com.sda.generic_shop_fx.controller.SaleController;
import com.sda.generic_shop_fx.dto.Customer;
import com.sda.generic_shop_fx.dto.Product;
import com.sda.generic_shop_fx.dto.Sale;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SalesListCell extends ListCell<Sale> {
    private final HBox container;
    private final Label label;
    private final Button button;

    private final ListView<String> listView;
    private final SaleController saleController;



    public SalesListCell() {
        label = new Label();
        button = new Button("Remove");
        listView = new ListView<>();
        container = new HBox(label, listView, button);
        saleController = new SaleController();

        container.setAlignment(Pos.BASELINE_RIGHT);
        container.setSpacing(10);

        button.setOnAction(e -> {
            if (!isEmpty()){
                Sale sale = getItem();
                container.getChildren().clear();
                saleController.deleteSale(sale);
            }
        });
    }



    @Override
    protected void updateItem(Sale sale, boolean b) {
        super.updateItem(sale, b);
        if (b){
            label.textProperty().unbind();
            label.setText("");
            listView.setVisible(false);
            button.setVisible(false);

        } else {
            listView.setVisible(true);
            button.setVisible(true);
            ObservableList<String> observableList = FXCollections.observableList(displayProductsByQuantity(sale.getProduct()));
            String stringDisplay = sale.getCustomer().getName();
            listView.setPrefSize(175,50);
            listView.autosize();
            listView.setItems(observableList);
            label.textProperty().bind(new SimpleStringProperty(stringDisplay));
            setGraphic(container);

        }
    }

    public List<String> displayProductsByQuantity(List<Product> products){
        Map<Product, Integer> map = new HashMap<>();
        List<String> displayList = new ArrayList<>();
        products
                .forEach(product -> {
                    if (map.containsKey(product)){
                        map.put(product, map.get(product) + 1);
                    } else {
                        map.put(product, 1);
                    }
                });
        map.forEach((product, integer) -> displayList.add(product.getProductName() + " - " + product.getPrice() + "$" + " Amount: " + integer));
        return displayList;
    }


}
