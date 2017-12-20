package org.asi.ui.customcomponentdemo;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.VerticalLayout;
import org.asi.ui.customcomponentdemo.demo.ActionButtonLayout;
import org.asi.ui.customcomponentdemo.demo.CustomMenuBarDemo;
import org.asi.ui.customcomponentdemo.demo.CustomTextFieldDemo;
import org.asi.ui.customcomponentdemo.demo.CustomWindowDemo;
import org.asi.ui.customcomponentdemo.demo.ExtFilteringTableDemo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abhiram
 */
public class Custom extends VerticalLayout implements View{
    public static final String NAME="";
     ActionButtonLayout actLayout;
    public Custom(ActionButtonLayout actLayout){
        this.actLayout=actLayout;
        addComponent(actLayout);
        setComponentAlignment(actLayout, Alignment.MIDDLE_CENTER);
        init();
    }
    
    private void init() {
        Button x=new Button("Custom Window Demo");
        Button y=new Button("Custom TextField Demo");
        Button z=new Button("Custom MenuBar Demo");
        Button a=new Button("Ext Filtering Table Demo");
        setSizeFull();
        addComponent(x);
        setComponentAlignment(x, Alignment.MIDDLE_CENTER);
        x.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                
                getUI().getNavigator().navigateTo(CustomWindowDemo.NAME);
            }
        });
        addComponent(y);
        setComponentAlignment(y, Alignment.MIDDLE_CENTER);
        y.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                 getUI().getNavigator().navigateTo(CustomTextFieldDemo.NAME);
            }
        });
        addComponent(z);
        setComponentAlignment(z, Alignment.MIDDLE_CENTER);
        z.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                
                getUI().getNavigator().navigateTo(CustomMenuBarDemo.NAME);
            }
        });
        addComponent(a);
        setComponentAlignment(a, Alignment.MIDDLE_CENTER);
        a.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                
                getUI().getNavigator().navigateTo(ExtFilteringTableDemo.NAME);
            }
        });
        
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
}
