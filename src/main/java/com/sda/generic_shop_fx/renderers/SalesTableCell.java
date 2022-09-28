package com.sda.generic_shop_fx.renderers;

import com.sda.generic_shop_fx.controller.SaleController;
import com.sda.generic_shop_fx.dto.Product;
import com.sda.generic_shop_fx.dto.Sale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesTableCell extends TableCell<Sale, String> {
    private final ListView<String> listView;
    private final Button deleteButton;

    private final String cellType;
    private final SaleController saleController;

    public SalesTableCell(String cellType){
        saleController = new SaleController();
        this.cellType = cellType;
        this.listView = new ListView<>();
        listView.setMinHeight(25);
        listView.setDisable(true);


        FontIcon deleteIcon = new FontIcon();
        deleteIcon.setIconLiteral("fas-trash");
        this.deleteButton = new Button();
        deleteButton.setGraphic(deleteIcon);
        deleteIcon.setFill(Paint.valueOf("#ffffff"));
        deleteIcon.setIconSize(12);
        deleteButton.getStyleClass().add("btn-delete");

        deleteButton.setOnAction(e -> {
            if (!isEmpty()){
                Sale sale = getTableRow().getItem();
                saleController.deleteSale(sale);
                getTableView().getItems().remove(sale);
            }
        });

    }

    @Override
    protected void updateItem(String string, boolean b) {
        super.updateItem(string, b);
        if (b){
            setText(null);
            setGraphic(null);
        } else {
            if (cellType.equals("list")){
                ObservableList<String> observableList = FXCollections.observableList(displayProductsByQuantity(getTableRow().getItem().getProduct()));
                listView.setItems(observableList);
                setGraphic(listView);
                listView.setPrefSize(150, listView.getItems().size() * 25);
                listView.refresh();
            }
            if (cellType.equals("delete")){
                setGraphic(deleteButton);
                setText(null);
            }
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
