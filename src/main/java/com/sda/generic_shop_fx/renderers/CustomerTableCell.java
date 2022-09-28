package com.sda.generic_shop_fx.renderers;

import com.sda.generic_shop_fx.controller.CustomerController;

import com.sda.generic_shop_fx.dto.Customer;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.paint.Paint;
import org.kordamp.ikonli.javafx.FontIcon;

public class CustomerTableCell extends TableCell<Customer, String> {
    private final Button deleteButton;
    private final CustomerController customerController;

    public CustomerTableCell() {
        customerController = new CustomerController();

        FontIcon deleteIcon = new FontIcon();
        deleteIcon.setIconLiteral("fas-trash");
        this.deleteButton = new Button();
        deleteButton.setGraphic(deleteIcon);
        deleteIcon.setFill(Paint.valueOf("#ffffff"));
        deleteIcon.setIconSize(12);
        deleteButton.getStyleClass().add("btn-delete");

        deleteButton.setOnAction(e -> {
            if (!isEmpty()){
                Customer customer = getTableRow().getItem();
                customerController.removeCustomer(customer);
                getTableView().getItems().remove(customer);
                getTableView().refresh();
            }
        });
    }

    @Override
    protected void updateItem(String string, boolean b) {
        super.updateItem(string, b);

        if (b){
            setText(null);
            setStyle("");
            setGraphic(null);
        } else {
            setGraphic(deleteButton);
            setText(null);
        }

    }
}
