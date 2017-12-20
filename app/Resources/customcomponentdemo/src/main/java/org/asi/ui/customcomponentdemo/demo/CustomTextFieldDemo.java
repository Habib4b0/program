/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.asi.ui.customcomponentdemo.demo;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import org.asi.ui.customcomponentdemo.Custom;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.customwindow.CustomWindow;

/**
 *
 * @author Abhiram
 */
public class CustomTextFieldDemo extends VerticalLayout implements View{
    public static final String NAME="customtextfield-demo";
    public CustomTextFieldDemo(){
        init();
    }
    
    private void init() {
        Button menu=new Button("Main Menu");
        addComponent(menu);
        menu.setDescription("Go to Main Menu");
        setComponentAlignment(menu, Alignment.MIDDLE_CENTER);
        menu.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
               getUI().getNavigator().navigateTo(Custom.NAME);
            }
        });
        CustomTextField component = new CustomTextField();
        addComponent(component);
        final CustomWindow ob=new CustomWindow("Window");
        ob.setWidth("200px");
        ob.setHeight("200px");
        setComponentAlignment(component, Alignment.MIDDLE_CENTER);
        component.addClickListener(new CustomTextField.ClickListener() {

            @Override
            public void click(CustomTextField.ClickEvent event) {
                 UI.getCurrent().addWindow(ob);
            }
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
}
