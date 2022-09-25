module com.sda.generic_shop_fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.java;
    requires org.hibernate.orm.core;
    requires static lombok;
    requires jakarta.persistence;
    requires java.naming;

    opens com.sda.generic_shop_fx to javafx.fxml;
    opens com.sda.generic_shop_fx.dto;
    opens com.sda.generic_shop_fx.repository;
    opens com.sda.generic_shop_fx.controller;
    opens com.sda.generic_shop_fx.controller.viewControllers;
    exports com.sda.generic_shop_fx;
    exports com.sda.generic_shop_fx.dto;
    exports com.sda.generic_shop_fx.repository;
    exports com.sda.generic_shop_fx.controller;
    exports com.sda.generic_shop_fx.controller.viewControllers;
}