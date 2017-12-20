/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.v8;

import com.vaadin.osgi.resources.OsgiVaadinWidgetset;
import org.osgi.service.component.annotations.Component;

/**
 *
 * @author Abishek.Ram
 */
@Component(immediate = true, service = OsgiVaadinWidgetset.class)
public class WidgetProvider implements OsgiVaadinWidgetset {
  
    @Override
    public String getName() {
        return "com.stpl.app.v8.AppWidgetSet";
    }  
}
