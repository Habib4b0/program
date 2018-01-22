/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.ui.view;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.processscheduler.ui.form.processSchedulerForm;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.v7.ui.VerticalLayout;

/**
 *
 * @author Jayaram
 */
public class processSchedulerView extends VerticalLayout implements View {

    public static final String NAME = ConstantsUtils.EMPTY;

    private SessionDTO sessionDTO;

    private processSchedulerForm processScheduler;

    public processSchedulerView(final SessionDTO sessionDTO) {
        super();
        this.sessionDTO = sessionDTO;
        setSpacing(true);
        setStyleName("bootstrap");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        processScheduler = new processSchedulerForm(sessionDTO);
        addComponent(processScheduler);
    }

}
