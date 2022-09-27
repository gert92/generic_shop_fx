package com.sda.generic_shop_fx.dto;

import com.sda.generic_shop_fx.State;
import com.sda.generic_shop_fx.views.ViewFactory;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;

    private final State state;

    private Model(){
        this.viewFactory = new ViewFactory();
        this.state = new State();
    }

    public static synchronized Model getInstance(){
        if (model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public State getState(){
        return state;
    }
}
