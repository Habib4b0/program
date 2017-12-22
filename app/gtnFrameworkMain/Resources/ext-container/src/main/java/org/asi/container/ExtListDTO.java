/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.asi.container;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Abhiram
 */
public class ExtListDTO {

    protected final List<Object> properties = new ArrayList<Object>();

    public List<Object> getProperties() {
        return properties;
    }


    public Object removeProperties(String propertyId) {
        int ind = getIndexOfProperty(propertyId);
        Object value = null;
        if (ind > -1) {
            value = this.properties.remove(ind);
        }
        return value;
    }

    public boolean addProperties(String propertyId, Object value) {
        return addProperties(getIndexOfProperty(propertyId), value,properties);
    }
    public void setProperties(List<Object> properties) {
        properties.clear();
        properties.addAll(properties);
    }
    protected static boolean addProperties(int index, Object value,final List<Object> propertyList) {
        boolean added=index > -1;
        if (added) {
            if (propertyList.size() > index) {
                propertyList.set(index, value);
            } else {
                for (int i = propertyList.size(); i < index; i++) {
                    propertyList.add(i, null);
                }
                propertyList.add(index, value);
            }
        }
        return added;
    }

    public boolean addStringProperties(String propertyId, String value) {
        return addProperties(propertyId, value);
    }

    public boolean addDoubleProperties(String propertyId, Double value) {
        return addProperties(propertyId, value);
    }

    public boolean addBooleanProperties(String propertyId, Boolean value) {
        return addProperties(propertyId, value);
    }

    public boolean addIntegerProperties(String propertyId, Integer value) {
        return addProperties(propertyId, value);
    }

    public boolean addLongProperties(String propertyId, Long value) {
        return addProperties(propertyId, value);
    }
    
    public boolean addDateProperties(String propertyId, Date value) {
        return addProperties(propertyId, value);
    }

    public Object getPropertyValue(String propertyId) {
        return getPropertyValueByIndex(getIndexOfProperty(propertyId));
    }

    public static int getIndexOfProperty(String propertyId) {
        int ind = propertyId.lastIndexOf(".");
        if (ind > -1) {
            try {
                ind = Integer.valueOf(propertyId.substring(ind + 1));
            } catch (Exception e) {
            }
        }
        return ind;
    }
    public static String getPropertyId(String propertyId) {
        String proId=propertyId;
        int ind = propertyId.lastIndexOf(".");
        if (ind > -1) {
            try {
                proId=propertyId.substring(0,ind);
            } catch (Exception e) {
            }
        }
        return proId;
    }

    public Object getPropertyValueByIndex(int index) {
        Object value = null;
        if (index > -1 && properties.size() > index) {
            value = properties.get(index);
        }
        return value;
    }
}
