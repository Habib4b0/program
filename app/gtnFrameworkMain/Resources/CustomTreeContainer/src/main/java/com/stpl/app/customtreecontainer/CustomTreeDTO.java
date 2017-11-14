/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.customtreecontainer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Abhiram
 */
public class CustomTreeDTO{
    private Map<Object, Object> properties=new HashMap<Object, Object>();

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
