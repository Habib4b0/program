/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.queryutils;

import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.ComboBox;
import java.util.ResourceBundle;

/**
 *
 * @author karthikeyans
 */
public class QueryReader {

    private static final ResourceBundle listNameBundle = ResourceBundle.getBundle("custom-sql.PPAQueries");

    public static void main(String[] arg) {
        ComboBox box1 = new ComboBox();
        box1.setData("box1");
        Property pro = (Property) box1;
        pro.setValue("pro");
    }

    public static String getQuery(String key) {
        return listNameBundle.getString(key);
    }

}
