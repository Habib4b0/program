/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.asi.container;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Abhiram
 */
public class ExtMapDTO implements Serializable{
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
    public Object addStringProperties(Object propertyId, String value) {
        return addProperties(propertyId, value);
    }
    
    public Object addDoubleProperties(Object propertyId, Double value) {
        return addProperties(propertyId, value);
    }
   
    public Object addBooleanProperties(Object propertyId, Boolean value) {
        return addProperties(propertyId, value);
}
   
    public Object addIntegerProperties(Object propertyId, Integer value) {
        return addProperties(propertyId, value);
    }
   
    public Object addLongProperties(Object propertyId, Long value) {
        return addProperties(propertyId, value);
    }
    
    public Object addDateProperties(Object propertyId, Date value) {
        return addProperties(propertyId, value);
    }
    
    public Object addProperties(Object propertyId, Object value) {
        return this.properties.put(propertyId, value);
    }
    
    public Object getPropertyValue(Object propertyId) {
        return properties.get(propertyId);
    }
   
     public void addAllStringProperties(Map<Object, Object> properties) {
        this.properties.putAll(properties);
}
}
