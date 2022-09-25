package com.sda.generic_shop_fx.renderers;

import com.sda.generic_shop_fx.controller.CustomerController;
import com.sda.generic_shop_fx.controller.ProductController;
import com.sda.generic_shop_fx.dto.Customer;
import com.sda.generic_shop_fx.dto.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class CustomerListCell extends ListCell<Customer> {
    private final HBox container;
    private final Label label;

    private final CustomerController customerController;


    public CustomerListCell() {
        label = new Label();
        Button button = new Button("Remove");
        container = new HBox(label, button);
        customerController = new CustomerController();

        container.setAlignment(Pos.BASELINE_RIGHT);
        container.setSpacing(10);

        button.setOnAction(e -> {
            if (!isEmpty()){
                Customer customer = getItem();
                container.getChildren().clear();
                customerController.removeCustomer(customer);
            }
        });
    }

    @Override
    protected void updateItem(Customer customer, boolean b) {
        super.updateItem(customer, b);

        if (b){
            label.textProperty().unbind();
            label.setText("");
        } else {
            String stringDisplay = customer.getName() +
                    " - $" +
                    customer.getBalance();
            label.textProperty().bind(new SimpleStringProperty(stringDisplay));

            setGraphic(container);

        }
    }
}
