/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnworkflow.ui.view;

import com.stpl.app.gtnworkflow.ui.form.InboxDashBoard;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.v7.ui.VerticalLayout;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author satheesh
 */
public class InboxDashboardView extends VerticalLayout implements View {

    public static final String NAME = StringUtils.EMPTY;
    private InboxDashBoard dashboard = new InboxDashBoard();
    public InboxDashboardView() {
        setSpacing(true);
        addStyleName("bootstrap");
        addComponent(dashboard);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        return;
    }

}
