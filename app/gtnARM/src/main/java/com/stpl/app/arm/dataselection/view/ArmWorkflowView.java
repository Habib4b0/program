/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.view;

import com.stpl.app.arm.dataselection.ui.form.BussinessProcessForm;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Asha.Ravi
 */
public class ArmWorkflowView extends VerticalLayout implements View {
    
    /**
     * View name for navigation.
     */
    public static final String NAME = "workflowViewArm";

    public ArmWorkflowView(BussinessProcessForm editWindow)  {
        addComponent(editWindow.getContent());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
    
}
