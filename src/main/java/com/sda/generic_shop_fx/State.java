package com.sda.generic_shop_fx;

import com.sda.generic_shop_fx.dto.Customer;
import com.sda.generic_shop_fx.dto.Product;
import javafx.beans.property.MapProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class State {
    private MapProperty<Product, Integer> shoppingList = new SimpleMapProperty<>(FXCollections.observableHashMap());
    private ObjectProperty<Customer> currentCustomer = new SimpleObjectProperty<>(null);
}
