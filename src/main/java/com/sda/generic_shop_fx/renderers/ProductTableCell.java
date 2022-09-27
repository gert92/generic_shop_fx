package com.sda.generic_shop_fx.renderers;

import com.sda.generic_shop_fx.controller.ProductController;
import com.sda.generic_shop_fx.dto.Model;
import com.sda.generic_shop_fx.dto.Product;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.Map;


public class ProductTableCell extends TableCell<Product, String>{

    private static IntegerProperty qty = new SimpleIntegerProperty(0);

    private final IntegerProperty singleQty = new SimpleIntegerProperty(0);
    private final Button deleteButton;

    private final Button addToShoppingCartButton;
    private final HBox container;
    private final ProductController productController;

    private final String buttonType;
//    private final IntegerProperty qty;

    public ProductTableCell(String buttonType) {
        Text qtyText = new Text();
        qtyText.textProperty().bind(singleQty.asString());
        this.buttonType = buttonType;
        container = new HBox();
        container.setSpacing(5);
        container.setAlignment(Pos.CENTER);
        productController = new ProductController();

        FontIcon deleteIcon = new FontIcon();
        deleteIcon.setIconLiteral("fas-trash");
        this.deleteButton = new Button();
        deleteButton.setGraphic(deleteIcon);
        deleteIcon.setFill(Paint.valueOf("#ffffff"));
        deleteIcon.setIconSize(12);
        deleteButton.getStyleClass().add("btn-delete");

        Button addButton = new Button();
        FontIcon addIcon = new FontIcon();
        addIcon.setIconLiteral("fas-plus");
        addButton.setGraphic(addIcon);

        Button reduceButton = new Button();
        FontIcon reduceIcon = new FontIcon();
        reduceIcon.setIconLiteral("fas-minus");
        reduceButton.setGraphic(reduceIcon);

        addToShoppingCartButton = new Button();
        FontIcon addToShoppingCartIcon = new FontIcon();
        addToShoppingCartIcon.setIconLiteral("fas-cart-plus");
        addToShoppingCartButton.setGraphic(addToShoppingCartIcon);


        container.getChildren().addAll(reduceButton, addButton, qtyText);

        singleQty.addListener((observableValue, number, t1) -> {
            qty.set(t1.intValue());
        });


        addButton.setOnAction(e-> {
            Product product = getTableRow().getItem();
            if (product.getQuantity() >= singleQty.get() && product.getQuantity() != 0){
                singleQty.set(singleQty.get() + 1);
//                product.setQuantity(product.getQuantity() - 1);
            }
        });

        reduceButton.setOnAction(e-> {
            Product product = getTableRow().getItem();
            if (singleQty.get() > 0){
                singleQty.set(singleQty.get() - 1);
//                product.setQuantity(product.getQuantity() + 1);
            }
        });

        addToShoppingCartButton.setOnAction(e -> {
            if (!isEmpty()){
                Product product = getTableRow().getItem();
                Map<Product, Integer> map = Model.getInstance().getState().getShoppingList();
                product.setQuantity(product.getQuantity() - qty.getValue());
                if (map.containsKey(product)){
                    Model.getInstance().getState().getShoppingList().put(product, map.get(product) + qty.getValue());
                } else {
                    Model.getInstance().getState().getShoppingList().put(product, qty.getValue());
                }
            }
        });


        deleteButton.setOnAction(e -> {
            if (!isEmpty()){
                Product product = getTableRow().getItem();
                productController.deleteProduct(product);
                getTableView().getItems().remove(product);
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
            if (buttonType.equals("delete")){
                setGraphic(deleteButton);
                setText(null);
            }

            if (buttonType.equals("shop")){
                setGraphic(container);
            }

            if (buttonType.equals("cart")){
                setGraphic(addToShoppingCartButton);
                setText(null);
            }

        }

    }

    public static synchronized IntegerProperty getQtyTest(){
        if (qty == null){
            qty = new SimpleIntegerProperty(0);
        }
        return qty;
    }

}
