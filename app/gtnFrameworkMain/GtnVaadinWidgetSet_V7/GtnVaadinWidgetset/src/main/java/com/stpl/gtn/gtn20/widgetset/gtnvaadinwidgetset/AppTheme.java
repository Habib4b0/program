package com.stpl.gtn.gtn20.widgetset.gtnvaadinwidgetset;

import org.osgi.service.component.annotations.Component;

import com.vaadin.osgi.resources.OsgiVaadinTheme;
import com.vaadin.ui.themes.ValoTheme;

@Component(immediate = true, service = OsgiVaadinTheme.class)
public class AppTheme extends ValoTheme implements OsgiVaadinTheme {
    @Override
    public String getName() {
        return "stpl";
    }

}
