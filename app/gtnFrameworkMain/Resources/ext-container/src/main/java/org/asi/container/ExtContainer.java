/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.asi.container;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
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
public class ExtContainer<BEANTYPE> extends IndexedContainer {

    private static final long serialVersionUID = 2137736168509249908L;
    Class<? super BEANTYPE> type;
    private DataStructureMode dataStructure = null;
    private List<Object> recordHeader = null;
    private static final String defaultDescriptorName = "properties";
    private String descriptorName = defaultDescriptorName;
    /**
     * Counts how many nested contents change disable calls are in progress.
     *
     * Pending events are only fired when the counter reaches zero again.
     */
    private int contentChangedEventsDisabledCount = 0;
    private boolean contentsChangedEventPending;
    Map<Object, Class> propertiesMap;
    Map<Object, Object> valueMap;
    private boolean indexable = true;

    /**
     * The Enum ColumnHeaderMode.
     */
    public enum DataStructureMode {

        /**
         * Value DTO will contain value in a java.util.Map. This approach is to
         * support dynamic addition of Property ID:s to the DTO.
         */
        MAP,
        /**
         * Value DTO will contain value in a java.util.List This approach is to
         * support dynamic addition of Property ID:s to the DTO. Property ID:s
         * should be given as record header by using setRecordHeader(List). Each
         * Property ID should end with "." and the index where the value will be
         * available inside the List of Value DTO.
         */
        LIST,
        /**
         * Value DTO will is default DTO.
         */
        DEFAULT
    }

    public ExtContainer(Class<? super BEANTYPE> type) {
        this(type, DataStructureMode.DEFAULT);
    }

    public ExtContainer(Class<? super BEANTYPE> type, DataStructureMode dataStructure) {
        try {
            this.type = type;
            this.dataStructure = dataStructure;
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

    public boolean isIndexable() {
        return indexable;
    }

    public void setIndexable(boolean indexable) {
        this.indexable = indexable;
    }

    private static Logger getLogger() {
        return Logger.getLogger(ExtContainer.class.getName());
    }

    protected String getClassName() {
        return this.getClass().getName();
    }

    @Override
    protected void fireItemSetChange(
            com.vaadin.data.Container.ItemSetChangeEvent event) {
        if (contentsChangeEventsOn()) {
            super.fireItemSetChange(event);
        } else {
            contentsChangedEventPending = true;
        }
    }

    private boolean contentsChangeEventsOn() {
        return contentChangedEventsDisabledCount == 0;
    }

    protected void disableContentsChangeEvents() {
        contentChangedEventsDisabledCount++;
    }

    protected void enableAndFireContentsChangeEvents() {
        if (contentChangedEventsDisabledCount <= 0) {
            getLogger()
                    .log(Level.WARNING,
                            "Mismatched calls to disable and enable contents change events in ExtContainer");
            contentChangedEventsDisabledCount = 0;
        } else {
            contentChangedEventsDisabledCount--;
        }
        if (contentChangedEventsDisabledCount == 0) {
            if (contentsChangedEventPending) {
                fireItemSetChange();
            }
            contentsChangedEventPending = false;
        }
    }

    @Override
    public Item addItem(Object itemId) {
        boolean event = false;
        if (getClassName().equalsIgnoreCase(this.getClass().getName())) {
            event = true;
            disableContentsChangeEvents();
        }

        Item item = super.addItem(itemId);
        configureItem(item,itemId,event);
        return item;
    }

    public Object addBean(BEANTYPE bean) {
        Object item = addItem(bean);
        return item;
    }

    @Override
    public Item addItemAt(int index, Object itemId) {
        boolean event = false;
        if (getClassName().equalsIgnoreCase(this.getClass().getName())) {
            event = true;
            disableContentsChangeEvents();
        }

        Item item = super.addItemAt(index,itemId);
        configureItem(item,itemId,event);
        return item;
    }
    
    private void configureItem(Item item,Object itemId,boolean event){
        try {
            BeanInfo info = Introspector.getBeanInfo(type);
            PropertyDescriptor[] descriptiors = info.getPropertyDescriptors();
            for (PropertyDescriptor descriptior : descriptiors) {
                try {
                if (descriptior.getPropertyType() != null && !descriptior.getPropertyType().equals(Class.class)) {
                    if (descriptior.getPropertyType().equals(Map.class) && getDataStructureMode() == DataStructureMode.MAP && descriptior.getName().equalsIgnoreCase(getDescriptorName())) {
                        Map results = (Map) descriptior.getReadMethod().invoke(itemId);
                        for (Iterator it = results.keySet().iterator(); it.hasNext();) {
                            Object key = it.next();
                            Object value = results.get(key);
                            Class a = getType(key);
                            Property pro = item.getItemProperty(key);
                            if (pro != null && value != null && a != null) {
                                pro.setValue(value);
                            }
                        }
                        item.getItemProperty(descriptior.getName()).setValue(descriptior.getReadMethod().invoke(itemId));
                    } else if (descriptior.getPropertyType().equals(List.class) && getDataStructureMode() == DataStructureMode.LIST && getRecordHeader() != null && descriptior.getName().equalsIgnoreCase(getDescriptorName())) {//                        
                        List results = (List) descriptior.getReadMethod().invoke(itemId);
                        for (int j = 0; j < getRecordHeader().size(); j++) {
                            Object key = getRecordHeader().get(j);
                            setProperty(key, getColumnProperty(key));
                            Object value = null;
                            if (j > -1 && j < results.size()) {
                                value = results.get(j);                                
                            }
                            Class a = getType(key);
                            Property pro = item.getItemProperty(key);
                            if (pro != null && value != null && a != null) {
                                pro.setValue(value);
                            }
                        }
                        item.getItemProperty(descriptior.getName()).setValue(descriptior.getReadMethod().invoke(itemId));
                    } else {                        
                        item.getItemProperty(descriptior.getName()).setValue(descriptior.getReadMethod().invoke(itemId));
                    }
                }
            }catch (NullPointerException ex) {
             Logger.getLogger(ExtContainer.class.getName()).log(Level.WARNING, "descriptior.getName()="+descriptior.getName()+" descriptior.getReadMethod()= "+descriptior.getReadMethod()+ " getItemProperty= "+item.getItemProperty(descriptior.getName()));
            throw ex;
        }
            }
        } catch (IntrospectionException ex) {
            Logger.getLogger(ExtContainer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ExtContainer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ExtContainer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ExtContainer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (event) {
                enableAndFireContentsChangeEvents();
            }
        }
    }

    
    public void setColumnProperties(Map<Object, Class> propertiesMap) {
        setColumnProperties(propertiesMap, null);
    }

    public void setColumnProperties(Map<Object, Class> propertiesMap, Map<Object, Object> valueMap) {

        // Visible columns must exist
        if (propertiesMap == null) {
            throw new NullPointerException(
                    "Can not set visible columns to null value as propertiesMap is null");
        }
        if (valueMap == null) {
            valueMap = new HashMap<Object, Object>();
        }
        this.propertiesMap = propertiesMap;
        this.valueMap = valueMap;
        for (Object viscol : propertiesMap.keySet()) {
            if (getType(viscol.toString()) == null) {
                setProperty(viscol, propertiesMap.get(viscol));
            }
        }
    }

    public Map<Object, Object> getDefaultValue() {
        return valueMap;
    }

    public void setDefaultValue(Map<Object, Object> valueMap) {
        this.valueMap = valueMap;
    }

    public Class getColumnProperty(Object fieldName) {
        return propertiesMap.get(fieldName);
    }

    public void setColumnProperty(Object fieldName, Class properties) {
        if (propertiesMap == null) {
            propertiesMap = new HashMap<Object, Class>();
        }
        propertiesMap.put(fieldName, properties);
        setProperty(fieldName, properties);
    }

    protected void setProperty(Object fieldName, Class properties) {
        if (fieldName != null && properties != null) {
            this.addContainerProperty(fieldName, properties, getDefaultValue(fieldName));
        }
    }

    public Object getDefaultValue(Object fieldName) {
        return valueMap.get(fieldName);
    }

    public void setDefaultValue(Object fieldName, Object defaultValue) {
        if (valueMap == null) {
            valueMap = new HashMap<Object, Object>();
        }
        valueMap.put(fieldName, defaultValue);
        setProperty(fieldName, getColumnProperty(fieldName));
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
    public List<?> getItemIds() {
        List<?> results = super.getItemIds();
        for (int i = 0; i < results.size(); i++) {
            try {
                Item item = this.getItem(results.get(i));
                BeanInfo info = Introspector.getBeanInfo(type);
                PropertyDescriptor[] descriptiors = info.getPropertyDescriptors();
                for (int j = 0; j < descriptiors.length; j++) {
                    PropertyDescriptor descriptior = descriptiors[j];
                    if (descriptior.getPropertyType() != null && !descriptior.getPropertyType().equals(Class.class)) {
                        if (descriptior.getPropertyType().equals(Map.class) && getDataStructureMode() == DataStructureMode.MAP && descriptior.getName().equalsIgnoreCase(getDescriptorName())) {
                            Map properites = (Map) item.getItemProperty(descriptior.getName()).getValue();
                            for (Iterator it = properites.keySet().iterator(); it.hasNext();) {
                                Object key = it.next();
                                properites.put(key, item.getItemProperty(key).getValue());
                            }
                        } else if (descriptior.getPropertyType().equals(List.class) && getRecordHeader() != null && getDataStructureMode() == DataStructureMode.LIST && descriptior.getName().equalsIgnoreCase(getDescriptorName())) {
                            List returnList = (List) item.getItemProperty(descriptior.getName()).getValue();
                            for (int k = 0; k < getRecordHeader().size(); k++) {
                                addProperties(k, item.getItemProperty(getRecordHeader().get(k)).getValue(), returnList);
                            }
                        }
                    }
                }
            } catch (IntrospectionException ex) {
                Logger.getLogger(ExtContainer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ExtContainer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return results;
    }

    public List<BEANTYPE> getBeans() {
        List<BEANTYPE> returnList = new ArrayList<BEANTYPE>();
        List<?> results = super.getItemIds();
        for (int i = 0; i < results.size(); i++) {
            try {
                Object listItem = results.get(i);
                Item item = this.getItem(listItem);
                BeanInfo info = Introspector.getBeanInfo(type);
                PropertyDescriptor[] descriptiors = info.getPropertyDescriptors();
                for (int j = 0; j < descriptiors.length; j++) {
                    PropertyDescriptor descriptior = descriptiors[j];
                    if (descriptior.getPropertyType() != null && !descriptior.getPropertyType().equals(Class.class)) {
                        if (descriptior.getPropertyType().equals(Map.class) && getDataStructureMode() == DataStructureMode.MAP && descriptior.getName().equalsIgnoreCase(getDescriptorName())) {
                            Map properites = (Map) item.getItemProperty(descriptior.getName()).getValue();
                            for (Iterator it = properites.keySet().iterator(); it.hasNext();) {
                                Object key = it.next();
                                properites.put(key, item.getItemProperty(key).getValue());
                            }
                        } else if (descriptior.getPropertyType().equals(List.class) && getRecordHeader() != null && getDataStructureMode() == DataStructureMode.LIST && descriptior.getName().equalsIgnoreCase(getDescriptorName())) {

                            List returnMap = (List) item.getItemProperty(descriptior.getName()).getValue();
                            for (int k = 0; k < 10; k++) {
                                addProperties(k, item.getItemProperty(getRecordHeader().get(k)).getValue(), returnMap);
                            }
                        }
                    }
                }
                returnList.add((BEANTYPE) listItem);
            } catch (IntrospectionException ex) {
                Logger.getLogger(ExtContainer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ExtContainer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return returnList;
    }

    public void addAll(Collection<? extends BEANTYPE> collection) {
        for (BEANTYPE bean : collection) {
            this.addBean(bean);
        }
    }

    public DataStructureMode getDataStructureMode() {
        return dataStructure;
    }

    public void setDataStructureMode(DataStructureMode dataStructure) {
        this.dataStructure = dataStructure;
    }

    public List<Object> getRecordHeader() {
        return recordHeader;
    }

    public void setRecordHeader(List<Object> recordHeader) {
        this.recordHeader = recordHeader;
    }

    public String getDescriptorName() {
        return descriptorName;
    }

    public void setDescriptorName(String descriptorName) {
        this.descriptorName = descriptorName;
    }

    public static String getDefaultDescriptorName() {
        return defaultDescriptorName;
    }

    protected boolean addProperties(int index, Object value, final List<Object> propertyList) {
        boolean added = index > -1;
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
}
