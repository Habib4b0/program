package com.stpl.widgetset.vaadin.widgetset;

import org.osgi.service.component.annotations.Component;

import com.vaadin.osgi.resources.OsgiVaadinWidgetset;

@Component(immediate = true, service = OsgiVaadinWidgetset.class)
public class AppWidgetSet implements OsgiVaadinWidgetset {

    @Override
    public String getName() {
        return "com.stpl.widgetset.vaadin.widgetset.AppWidgetSet";
    }

}
