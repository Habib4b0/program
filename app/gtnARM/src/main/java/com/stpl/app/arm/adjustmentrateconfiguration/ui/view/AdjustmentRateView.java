/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentrateconfiguration.ui.view;

import com.stpl.app.arm.adjustmentrateconfiguration.ui.form.AdjustmentRateForm;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.v7.ui.VerticalLayout;
import org.apache.commons.lang.StringUtils;

/**
 * The View Class For Adjustment Rate Configuration which is for adding the UI
 * screens in the portlet when the constructor is called.
 *
 * @author
 */
public class AdjustmentRateView extends VerticalLayout implements View {

    /**
     * The Default View Name For Adjustment Rate Configuration in which
     * navigates to the first View.
     */
    public static final String NAME = StringUtils.EMPTY;

    /**
     * The Session DTO is POJO class used in View class as binder, Which holds
     * UserId and session Id and Mode though out the views so that the Screen is
     * loaded in UI based on Modes
     */
    /**
     * The Constructor of Adjustment Rate View
     *
     * @param sessionDTO
     */
    public AdjustmentRateView() {
        super();
    }

    /**
     * The method is used to adding Forms, and view navigation Performing the
     * initialization in a constructor is not suggested as the method is invoked
     * when the constructor is invoked or the class is instantiated.
     *
     * @param event
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        AdjustmentRateForm adjustmentRateForm = new AdjustmentRateForm();
        addComponent(adjustmentRateForm);

    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
