package com.sda.generic_shop_fx.renderers;

import com.sda.generic_shop_fx.controller.ProductController;
import com.sda.generic_shop_fx.dto.Model;
import com.sda.generic_shop_fx.dto.Product;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.Map;


public class ProductTableCell extends TableCell<Product, String>{

    private static final IntegerProperty qty = new SimpleIntegerProperty(0);

    private final IntegerProperty singleQty = new SimpleIntegerProperty(0);
    private final Button deleteButton;

    private final Button addToShoppingCartButton;

    private final HBox container;

    private final HBox shoppingCartContainer;
    private final ProductController productController;

    private final String cellType;

    public ProductTableCell(String cellType) {
        Text qtyText = new Text();
        qtyText.textProperty().bind(singleQty.asString());
        this.cellType = cellType;
        container = new HBox();
        container.setSpacing(5);
        container.setAlignment(Pos.CENTER);

        shoppingCartContainer = new HBox();
        shoppingCartContainer.setSpacing(5);
        shoppingCartContainer.setAlignment(Pos.CENTER);

        productController = new ProductController();


        deleteButton = createButton("fas-trash");
        FontIcon deleteIcon = (FontIcon) deleteButton.getGraphic();
        deleteIcon.setFill(Paint.valueOf("#ffffff"));
        deleteIcon.setIconSize(12);
        deleteButton.getStyleClass().add("btn-delete");

        Button addButton = createButton("fas-plus");
        Button reduceButton = createButton("fas-minus");
        addToShoppingCartButton = createButton("fas-cart-plus");
        Button subtractShoppingQtyButton = createButton("fas-minus");


        container.getChildren().addAll(reduceButton, addButton, qtyText);
        shoppingCartContainer.getChildren().addAll(subtractShoppingQtyButton);

        singleQty.addListener((observableValue, number, t1) -> qty.set(t1.intValue()));

        setAvailableProductsQtyActionListeners(addButton, reduceButton);
        setAvailableProductsAddToShoppingActionListener(addToShoppingCartButton);
        shoppingQtyButtonsListener(subtractShoppingQtyButton);
        setDeleteButtonListener(deleteButton);

    }

    private void setContentToCells(String buttonType){
        switch (buttonType){
            case "delete" -> setGraphic(deleteButton);
            case "shop" -> setGraphic(container);
            case "cart" -> setGraphic(addToShoppingCartButton);
            case "cartQty" -> setGraphic(shoppingCartContainer);
        }
    }

    private Button createButton(String iconLiteral){
        FontIcon icon = new FontIcon();
        icon.setIconLiteral(iconLiteral);
        Button button = new Button();
        button.setGraphic(icon);
        return button;
    }

    @Override
    protected void updateItem(String string, boolean b) {
        super.updateItem(string, b);
        if (b){
            setText(null);
            setStyle("");
            setGraphic(null);
        } else {
            setContentToCells(cellType);
        }

    }

    private void shoppingQtyButtonsListener(Button reduceButton){
        reduceButton.setOnAction(e-> {
            Product product = getTableRow().getItem();
            Model.getInstance().getState().getShoppingList().put(product, Model.getInstance().getState().getShoppingList().get(product) - 1);
            product.setQuantity(product.getQuantity() + 1);
            if (Model.getInstance().getState().getShoppingList().get(product) == 0){
                Model.getInstance().getState().getShoppingList().remove(product);
            }
            getTableView().refresh();
        });
    }

    private void setDeleteButtonListener(Button deleteButton) {
        deleteButton.setOnAction(e -> {
            if (!isEmpty()){
                Product product = getTableRow().getItem();
                productController.deleteProduct(product);
                getTableView().getItems().remove(product);
                getTableView().refresh();
            }
        });
    }

    private void setAvailableProductsAddToShoppingActionListener(Button addToShoppingCartButton) {
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
    }

    private void setAvailableProductsQtyActionListeners(Button addButton, Button reduceButton){
        addButton.setOnAction(e-> {
            Product product = getTableRow().getItem();
            if (product.getQuantity() > singleQty.get() && product.getQuantity() != 0){
                singleQty.set(singleQty.get() + 1);
            }
        });

        reduceButton.setOnAction(e-> {
            if (singleQty.get() > 0){
                singleQty.set(singleQty.get() - 1);
            }
        });
    }


}
