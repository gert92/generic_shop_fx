package com.sda.generic_shop_fx;

import com.sda.generic_shop_fx.dto.Customer;
import com.sda.generic_shop_fx.dto.Product;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class State {
    private Customer currentCustomer;
//    private Map<Product, Integer> shoppingList = new HashMap<>();
    private MapProperty<Product, Integer> shoppingList = new SimpleMapProperty<>(FXCollections.observableHashMap());
}
