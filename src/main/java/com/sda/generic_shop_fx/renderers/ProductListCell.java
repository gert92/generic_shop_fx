package com.sda.generic_shop_fx.renderers;

import com.sda.generic_shop_fx.controller.ProductController;
import com.sda.generic_shop_fx.dto.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

import javax.xml.validation.Schema;

public class ProductListCell extends ListCell<Product> {
    private final HBox container;
    private final Label label;
    private final ProductController productController;

    public ProductListCell(){
        label = new Label();
        Button button = new Button("Remove");
        container = new HBox(label, button);
        productController = new ProductController();

        container.setAlignment(Pos.BASELINE_RIGHT);
        container.setSpacing(10);

        button.setOnAction(e -> {
            if (!isEmpty()){
                Product product = getItem();
                container.getChildren().clear();
                productController.deleteProduct(product);
            }
        });
    }

    @Override
    protected void updateItem(Product product, boolean b) {
        super.updateItem(product, b);

        if (b){
            label.textProperty().unbind();
            label.setText("");
        } else {
            String stringDisplay = product.getProductName() +
                    " - $" +
                    product.getPrice() +
                    " - Qty: " +
                    product.getQuantity();
            label.textProperty().bind(new SimpleStringProperty(stringDisplay));

            setGraphic(container);

        }
    }


}
