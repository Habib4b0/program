/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.forecastdashboard.ui.form;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author maheshj
 */
public class CommonLevelDto {
    
       private Map<Object, Object> properties=new HashMap<Object, Object>();
       
       private String group="";
       private String discountPerUnitProjections=" ";
       private String unitVolumeProjections=" ";
       private String discountPercentProjections=" ";
       private String totalDiscountProjections="";

    public String getTotalDiscountProjections() {
        return totalDiscountProjections;
    }

    public void setTotalDiscountProjections(String totalDiscountProjections) {
        this.totalDiscountProjections = totalDiscountProjections;
    }
       

    public String getDiscountPerUnitProjections() {
        return discountPerUnitProjections;
    }

    public void setDiscountPerUnitProjections(String discountPerUnitProjections) {
        this.discountPerUnitProjections = discountPerUnitProjections;
    }

    public String getUnitVolumeProjections() {
        return unitVolumeProjections;
    }

    public void setUnitVolumeProjections(String unitVolumeProjections) {
        this.unitVolumeProjections = unitVolumeProjections;
    }

    public String getDiscountPercentProjections() {
        return discountPercentProjections;
    }

    public void setDiscountPercentProjections(String discountPercentProjections) {
        this.discountPercentProjections = discountPercentProjections;
    }
       
       

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
       

    public Map<Object, Object> getProperties() {
        return properties;
    }
    public void setProperties(Map<Object, Object> properties) {
        this.properties = properties;
    }
    public Object removeProperties(Object propertyId) {
        return this.properties.remove(propertyId);
    }
    public void addStringProperties(Object propertyId, String value) {
        this.properties.put(propertyId, value);
    }
    
    public void addDoubleProperties(Object propertyId, Double value) {
        this.properties.put(propertyId, value);
    }
   
    public void addBooleanProperties(Object propertyId, Boolean value) {
        this.properties.put(propertyId, value);
}
   
    public void addIntegerProperties(Object propertyId, Integer value) {
        this.properties.put(propertyId, value);
    }
   
    public void addLongProperties(Object propertyId, Long value) {
        this.properties.put(propertyId, value);
    }
    public Object getPropertyValue(Object propertyId) {
        return properties.get(propertyId);
    }
    
}
