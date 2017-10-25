/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastdashboard.ui;


import com.stpl.app.forecastdashboard.ui.form.BigFive;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.jboss.logging.Logger;

/**
 *
 * @author srithar
 */
public class BigFiveUI extends UI{
      Navigator navi;
   private static final Logger LOGGER = Logger.getLogger(BigFiveUI.class);
    /**
     * This method is used to register the navigations for different views.
     *
     * @param request the request
     */
    @Override
    protected void init(VaadinRequest request) {
        addStyleName("bootstrap");
        navi = new Navigator(this, this);
        navi.addView("", new BigFive());
    }
}
