package org.asi.ui.customcomponentdemo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import javax.servlet.annotation.WebServlet;
import org.asi.ui.customcomponentdemo.demo.ActionButtonLayout;
import org.asi.ui.customcomponentdemo.demo.CustomMenuBarDemo;
import org.asi.ui.customcomponentdemo.demo.CustomTextFieldDemo;
import org.asi.ui.customcomponentdemo.demo.CustomWindowDemo;
import org.asi.ui.customcomponentdemo.demo.ExtFilteringTableDemo;
import org.asi.ui.customcomponentdemo.demo.util.Utils;

@SuppressWarnings("serial")
public class CustomUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = CustomUI.class, widgetset = "org.asi.ui.customcomponentdemo.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }
    Navigator navigator;
    ActionButtonLayout actLayout;
    @Override
    protected void init(VaadinRequest request) {
        setImmediate(true);
        actLayout=new ActionButtonLayout();
        actLayout.setTheme(Utils.customValoTheme);
        navigator=new Navigator(this, this);
        navigator.addView(Custom.NAME, new Custom(actLayout));
        navigator.addView(CustomWindowDemo.NAME, new CustomWindowDemo());
        navigator.addView(CustomTextFieldDemo.NAME, new CustomTextFieldDemo());
        navigator.addView(CustomMenuBarDemo.NAME, new CustomMenuBarDemo());
        navigator.addView(ExtFilteringTableDemo.NAME, new ExtFilteringTableDemo());
//        VaadinSession.getCurrent().getService().
               
    }

}
