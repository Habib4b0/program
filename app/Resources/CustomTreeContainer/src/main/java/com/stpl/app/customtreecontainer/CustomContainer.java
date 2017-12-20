/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.customtreecontainer;

import com.vaadin.v7.data.Item;
import com.vaadin.v7.data.util.IndexedContainer;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manikanta
 * @param <BEANTYPE>
 */
public class CustomContainer<BEANTYPE> extends IndexedContainer {

    private static final long serialVersionUID = 2137736168509249908L;
    Class<? super BEANTYPE> type;

    public CustomContainer(Class<? super BEANTYPE> type) {
        try {
            this.type = type;
            BeanInfo info = Introspector.getBeanInfo(type);
            PropertyDescriptor[] descriptiors = info.getPropertyDescriptors();
            for (int i = 0; i < descriptiors.length; i++) {
                PropertyDescriptor descriptior = descriptiors[i];
                if (descriptior.getPropertyType() != null && !descriptior.getPropertyType().equals(Class.class)) {
                    this.addContainerProperty(descriptior.getName(), descriptior.getPropertyType(), new Object());
                }
            }
        } catch (IntrospectionException ex) {
            ex.printStackTrace();
        }
    }
    
    public Object addBean(BEANTYPE bean) {
        Object item = addItem(bean);
        try {
            BeanInfo info = Introspector.getBeanInfo(type);
            PropertyDescriptor[] descriptiors = info.getPropertyDescriptors();
            for (int i = 0; i < descriptiors.length; i++) {
                PropertyDescriptor descriptior = descriptiors[i];
                if (descriptior.getPropertyType() != null && !descriptior.getPropertyType().equals(Class.class)) {
                if (descriptior.getPropertyType().equals(Map.class)) {
                    Map results = (Map) descriptior.getReadMethod().invoke(bean);
                    for (Iterator it = results.keySet().iterator(); it.hasNext();) {
                        Object key = it.next();
                        if (results.get(key) != null) {
                            this.addContainerProperty(String.valueOf(key), results.get(key).getClass(), null);
                        }
                        if (this.getItem(bean).getItemProperty(key) != null) {
                            this.getItem(bean).getItemProperty(key).setValue(results.get(key));
                        }
                    }
                    this.getItem(bean).getItemProperty(descriptior.getName()).setValue(descriptior.getReadMethod().invoke(bean));
                }else {
                    this.getItem(bean).getItemProperty(descriptior.getName()).setValue(descriptior.getReadMethod().invoke(bean));
                }
                }
            }
        } catch (IntrospectionException ex) {
            Logger.getLogger(CustomContainer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CustomContainer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(CustomContainer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(CustomContainer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
    }
    public void setColumnProperties(Map<Object, Class> propertiesMap) {

        // Visible columns must exist
        if (propertiesMap == null) {
            throw new NullPointerException(
                    "Can not set visible columns to null value");
        }
        for(Object viscol:propertiesMap.keySet()){
            if (getType(viscol.toString()) == null) {
                    this.addContainerProperty(viscol, propertiesMap.get(viscol), null);
            }
        }
    }
    public Field getType(String fieldName) {

        Field fType = null;
        Class clazz = type;
        while (fType == null && clazz != null) {
            try {
                fType = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (SecurityException e) {
                return null;
            }
        }
        return fType;
    }
    @Override
    public List<BEANTYPE> getItemIds() {
        List<BEANTYPE> returnList = new ArrayList<BEANTYPE>();
        List<?> results = super.getItemIds();
        for (int i = 0; i < results.size(); i++) {
            try {
                BEANTYPE listItem = (BEANTYPE) type.newInstance();
                Item item = this.getItem(results.get(i));
                BeanInfo info = Introspector.getBeanInfo(type);
                PropertyDescriptor[] descriptiors = info.getPropertyDescriptors();
                for (int j = 0; j < descriptiors.length; j++) {
                    PropertyDescriptor descriptior = descriptiors[j];
                    if (descriptior.getPropertyType().equals(Map.class)) {
                        Map properites = (Map) item.getItemProperty(descriptior.getName()).getValue();
                        Map returnMap = new HashMap();
                        for (Iterator it = properites.keySet().iterator(); it.hasNext();) {
                            Object key = it.next();
                            returnMap.put(key, item.getItemProperty(key).getValue());
                        }
                        descriptior.getWriteMethod().invoke(listItem, returnMap);
                    } else if (!descriptior.getPropertyType().equals(Class.class)) {
                        Object value = item.getItemProperty(descriptior.getName()).getValue();
                        descriptior.getWriteMethod().invoke(listItem, value);
                    }
                }
                returnList.add((BEANTYPE) listItem);
            } catch (IntrospectionException ex) {
                Logger.getLogger(CustomContainer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(CustomContainer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(CustomContainer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(CustomContainer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(CustomContainer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return returnList;
    }

    public void addAll(Collection<? extends BEANTYPE> collection) {
        for (BEANTYPE bean : collection) {
            this.addBean(bean);
        }
    }
}
