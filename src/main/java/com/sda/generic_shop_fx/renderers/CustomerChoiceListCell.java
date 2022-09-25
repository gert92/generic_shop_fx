package com.sda.generic_shop_fx.renderers;

import com.sda.generic_shop_fx.dto.Customer;
import javafx.scene.control.ListCell;

public class CustomerChoiceListCell extends ListCell<Customer> {
    @Override
    protected void updateItem(Customer customer, boolean b) {
        super.updateItem(customer, b);
        if (customer != null){
            setText(customer.getName());
        } else {
            setText(null);
        }
    }
}
