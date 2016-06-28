/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Lokeshwari
 */
public class ForecastWorkflowView extends VerticalLayout implements View {

    /**
     * View name for navigation.
     */
    public static final String NAME = "workflowView";

    public ForecastWorkflowView(ForecastEditWindow forecastEditWindow) throws Exception {
        addComponent(forecastEditWindow.getContent());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

}
