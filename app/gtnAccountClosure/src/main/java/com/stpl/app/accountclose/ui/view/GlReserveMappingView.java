/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.view;

import com.stpl.app.accountclose.glReserveMapping.ui.form.GLReserveMappingindex;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author kasiammal.m
 */
public class GlReserveMappingView extends VerticalLayout implements View {

    /**
     * View name for navigation.
     */
    public static final String NAME = "";

    /**
     * Default constructor
     */
    public GlReserveMappingView() throws Exception {
        addComponent(new GLReserveMappingindex());
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
