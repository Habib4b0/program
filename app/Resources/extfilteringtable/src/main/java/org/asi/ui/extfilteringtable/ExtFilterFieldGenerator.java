/*
 * Copyright 2014 Abhiram.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.asi.ui.extfilteringtable;

import com.vaadin.data.Container;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Container.Filterable;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Compare.Equal;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.TextField;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.asi.ui.extfilteringtable.datefilter.ExtDateFilterPopup;
import org.asi.ui.extfilteringtable.datefilter.ExtDateInterval;
import org.asi.ui.extfilteringtable.numberfilter.ExtNumberFilterPopup;
import org.asi.ui.extfilteringtable.numberfilter.ExtNumberInterval;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTableBase;
import org.asi.ui.extfilteringtable.paged.PagedTreeTableBase;



/**
 * The Class ExtFilterFieldGenerator.
 *
 * @author Abhiram
 */

@SuppressWarnings("serial")
class ExtFilterFieldGenerator implements Serializable {
    
    /** The owner. */
    private final IFilterTable owner;

    /* Mapping for property IDs, filters and components */
    /** The filters. */
    private final Map<Object, Filter> filters = new HashMap<Object, Container.Filter>();
    
    private final Set<Container.Filter> addedFilters = new HashSet<Container.Filter>();
    
    /** The custom fields. */
    private final Map<AbstractField<?>, Object> customFields = new HashMap<AbstractField<?>, Object>();
    
    /** The texts. */
    private final Map<TextField, Object> texts = new HashMap<TextField, Object>();
    
    /** The enums. */
    private final Map<ComboBox, Object> enums = new HashMap<ComboBox, Object>();
    
    /** The booleans. */
    private final Map<ComboBox, Object> booleans = new HashMap<ComboBox, Object>();
    
    /** The dates. */
    private final Map<ExtDateFilterPopup, Object> dates = new HashMap<ExtDateFilterPopup, Object>();
    
    /** The numbers. */
    private final Map<ExtNumberFilterPopup, Object> numbers = new HashMap<ExtNumberFilterPopup, Object>();

    /** The last on demand filter. */
    private And lastOnDemandFilter;

    /* ValueChangeListener for filter components */
    /** The listener. */
    private final ValueChangeListener listener = initializeListener();

    /** The run filters on demand. */
    private boolean runFiltersOnDemand;
    
    /**
     * Instantiates a new ext filter field generator.
     *
     * @param owner the owner
     */
    ExtFilterFieldGenerator(IFilterTable owner) {
        this.owner = owner;
    }

    /**
     * Destroy filter components.
     */
    void destroyFilterComponents() {
        owner.setRefreshingEnabled(false);
        /* Remove all filters from container */
        for (Object propertyId : filters.keySet()) {
            if (owner.getFilterable() != null) {
                owner.getFilterable().removeContainerFilter(
                        filters.get(propertyId));
            }
            if (owner.getFilterGenerator() != null) {
                owner.getFilterGenerator().filterRemoved(propertyId);
            }
        }
        /* Remove listeners */
        removeValueChangeListeners();
        /* Clear the data related to filters */
        customFields.clear();
        filters.clear();
        addedFilters.clear();
        texts.clear();
        enums.clear();
        booleans.clear();
        dates.clear();
        numbers.clear();

        /* also clear on-demand data */
        if (owner.getFilterable() != null) {
            owner.getFilterable().removeContainerFilter(lastOnDemandFilter);
        }

        owner.setRefreshingEnabled(true);
    }

    /**
     * Clear filter data.
     */
    void clearFilterData() {
        owner.setRefreshingEnabled(false);
        /* Remove all filters from container */
        for (Object propertyId : filters.keySet()) {
            if (owner.getFilterable() != null) {
                owner.getFilterable().removeContainerFilter(
                        filters.get(propertyId));
            }
            if (owner.getFilterGenerator() != null) {
                owner.getFilterGenerator().filterRemoved(propertyId);
            }
        }

        filters.clear();
        addedFilters.clear();
        for (AbstractField<?> f : customFields.keySet()) {
            f.setValue(null);
        }
        for (AbstractField<?> f : texts.keySet()) {
            f.setValue(null);
        }
        for (AbstractField<?> f : enums.keySet()) {
            f.setValue(null);
        }
        for (AbstractField<?> f : booleans.keySet()) {
            f.setValue(null);
        }
        for (AbstractField<?> f : dates.keySet()) {
            f.setValue(null);
        }
        for (AbstractField<?> f : numbers.keySet()) {
            f.setValue(null);
        }

        /* also clear on-demand data */
        if (owner.getFilterable() != null) {
            owner.getFilterable().removeContainerFilter(lastOnDemandFilter);
        }

        owner.setRefreshingEnabled(true);
    }

    /**
     * Removes the value change listeners.
     */
    public void removeValueChangeListeners() {
        for (AbstractField<?> af : customFields.keySet()) {
            af.removeValueChangeListener(listener);
        }
        for (TextField tf : texts.keySet()) {
            tf.removeValueChangeListener(listener);
        }
        for (ComboBox cb : enums.keySet()) {
            cb.removeValueChangeListener(listener);
        }
        for (ComboBox cb : booleans.keySet()) {
            cb.removeValueChangeListener(listener);
        }
        for (ExtDateFilterPopup dfp : dates.keySet()) {
            dfp.removeValueChangeListener(listener);
        }
        for (ExtNumberFilterPopup nfp : numbers.keySet()) {
            nfp.removeValueChangeListener(listener);
        }
    }

    /**
     * Adds the value change listeners.
     */
    public void addValueChangeListeners() {
        for (AbstractField<?> af : customFields.keySet()) {
            af.addValueChangeListener(listener);
        }
        for (TextField tf : texts.keySet()) {
            tf.addValueChangeListener(listener);
        }
        for (ComboBox cb : enums.keySet()) {
            cb.addValueChangeListener(listener);
        }
        for (ComboBox cb : booleans.keySet()) {
            cb.addValueChangeListener(listener);
        }
        for (ExtDateFilterPopup dfp : dates.keySet()) {
            dfp.addValueChangeListener(listener);
        }
        for (ExtNumberFilterPopup nfp : numbers.keySet()) {
            nfp.addValueChangeListener(listener);
        }
    }
    
    /**
     * Initialize filter fields.
     */
    void initializeFilterFields() {
        /* Create new filters only if Filterable */
        if (owner.getFilterable() != null) {
            for (Object property : owner.getVisibleColumns()) {
                if (owner.getContainerPropertyIds().contains(property)) {
                    AbstractField<?> filter = createField(property, owner
                            .getContainerDataSource().getType(property));
                    addFilterColumn(property, filter);
                } else {
                    AbstractField<?> filter = createField(property, null);
                    addFilterColumn(property, filter);
                }
            }
            if (!runFiltersOnDemand) {
                addValueChangeListeners();
            }
        }
        
    }
    
    /**
     * Generate filter.
     *
     * @param field the field
     * @param propertyId the property id
     * @param value the value
     * @return the filter
     */
    private Filter generateFilter(Property<?> field, Object propertyId,
            Object value) {
        try {
            /* First try to get custom filter based on the field */
            if (owner.getFilterGenerator() != null && field instanceof Field) {
                Filter newFilter = owner.getFilterGenerator().generateFilter(
                        propertyId, (Field<?>) field);
                if (newFilter != null) {
                    return newFilter;
                }
            }
            if (field instanceof ExtNumberFilterPopup) {
                return generateNumberFilter(field, propertyId, value);
            } else if (field instanceof ExtDateFilterPopup) {
                return generateDateFilter(field, propertyId, value);
            } else if (value != null && !value.equals("")) {
                return generateGenericFilter(field, propertyId, value);
            }
            return null;
        } catch (Exception reason) {
            /* Clear the field on failure during generating filter */
            field.setValue(null);
            /* Notify FilterGenerator, or re-throw if not available */
            if (owner.getFilterGenerator() != null) {
                return owner.getFilterGenerator().filterGeneratorFailed(reason,
                        propertyId, value);
            } else {
                throw new RuntimeException("Creating a filter for property '"
                        + propertyId + "' with value '" + value
                        + "'has failed.", reason);
            }
        }
    }

    /**
     * Generate generic filter.
     *
     * @param field the field
     * @param propertyId the property id
     * @param value the value
     * @return the filter
     */
    private Filter generateGenericFilter(Property<?> field, Object propertyId,
            Object value) {
        /* Handle filtering for other data */
        if (owner.getFilterGenerator() != null) {
            Filter newFilter = owner.getFilterGenerator().generateFilter(
                    propertyId, value);
            if (newFilter != null) {
                return newFilter;
            }
        }
        /* Special handling for ComboBox (= enum properties) */
        if (field instanceof ComboBox) {
            return new Equal(propertyId, value);
        } else {
            return new SimpleStringFilter(propertyId, String.valueOf(value),
                    true, false);
        }
    }

    /**
     * Generate number filter.
     *
     * @param field the field
     * @param propertyId the property id
     * @param value the value
     * @return the filter
     * @throws SecurityException the security exception
     * @throws NoSuchMethodException the no such method exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws IllegalAccessException the illegal access exception
     * @throws InvocationTargetException the invocation target exception
     */
    private Filter generateNumberFilter(Property<?> field, Object propertyId,
            Object value) throws SecurityException, NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        /* Handle number filtering */
        ExtNumberInterval interval = ((ExtNumberFilterPopup) field).getValue();
        if (interval == null) {
            /* Number interval is empty -> no filter */
            return null;
        }
        if (owner.getFilterGenerator() != null) {
            Filter newFilter = owner.getFilterGenerator().generateFilter(
                    propertyId, interval);
            if (newFilter != null) {
                return newFilter;
            }
        }
        String ltValue = interval.getLessThanValue();
        String gtValue = interval.getGreaterThanValue();
        String eqValue = interval.getEqualsValue();
        Class<?> typeClass = owner.getContainerDataSource().getType(propertyId);;

        if (eqValue != null) {
            return new Compare.Equal(propertyId, parseNumberValue(typeClass, eqValue));
        } else if (ltValue != null && gtValue != null) {
            return new And(new Compare.Less(propertyId, parseNumberValue(typeClass,
                    ltValue)), new Compare.Greater(propertyId, parseNumberValue(
                    typeClass, gtValue)));
        } else if (ltValue != null) {
            return new Compare.Less(propertyId, parseNumberValue(typeClass, ltValue));
        } else if (gtValue != null) {
            return new Compare.Greater(propertyId, parseNumberValue(typeClass,
                    gtValue));
        }
        return null;
    }

    private static Object parseNumberValue(Class<?> typeClass, String value) {
    	if (typeClass == BigDecimal.class)
    		return new BigDecimal(value);
    	if (typeClass == BigInteger.class)
    		return new BigInteger(value);
    	if (typeClass == byte.class || typeClass == Byte.class)
    		return Byte.valueOf(value);
    	if (typeClass == short.class || typeClass == Short.class)
    		return Short.valueOf(value);
    	if (typeClass == int.class || typeClass == Integer.class)
    		return Integer.valueOf(value);
    	if (typeClass == long.class || typeClass == Long.class)
    		return Long.valueOf(value);
    	if (typeClass == float.class || typeClass == Float.class)
    		return Float.valueOf(value);
    	if (typeClass == double.class || typeClass == Double.class)
    		return Double.valueOf(value);
    	
    	throw new UnsupportedOperationException("Unsupported number type; " + typeClass.getName());
    }

    private Filter generateDateFilter(Property<?> field, Object propertyId,
            Object value) {
        /* Handle date filtering */
        ExtDateInterval interval = ((ExtDateFilterPopup) field).getValue();
        if (interval == null || interval.isNull()) {
            /* Date interval is empty -> no filter */
            return null;
        }
        /* Try to get a custom filter from a provided filter generator */
        if (owner.getFilterGenerator() != null) {
            Filter newFilter = owner.getFilterGenerator().generateFilter(
                    propertyId, interval);
            if (newFilter != null) {
                return newFilter;
            }
        }
        /* On failure we generate the default filter */
        Comparable<?> actualFrom = interval.getFrom(), actualTo = interval
                .getTo();
        Class<?> type = owner.getType(propertyId);
        if (java.sql.Date.class.equals(type)) {
            actualFrom = actualFrom == null ? null : new java.sql.Date(interval
                    .getFrom().getTime());
            actualTo = actualTo == null ? null : new java.sql.Date(interval
                    .getTo().getTime());
        } else if (Timestamp.class.equals(type)) {
            actualFrom = actualFrom == null ? null : new Timestamp(interval
                    .getFrom().getTime());
            actualTo = actualTo == null ? null : new Timestamp(interval.getTo()
                    .getTime());
        }else if (java.util.Date.class.equals(type)) {
            
            actualFrom = actualFrom == null ? null : new Timestamp(interval
                    .getFrom().getTime());
            actualTo = actualTo == null ? null : new Timestamp(interval.getTo()
                    .getTime());
        }
        if (actualFrom != null && actualTo != null) {
            return new Between(propertyId, actualFrom, actualTo);
        } else if (actualFrom != null) {
            return new Compare.GreaterOrEqual(propertyId, actualFrom);
        } else {
            return new Compare.LessOrEqual(propertyId, actualTo);
        }
    }

    private void addFilterColumn(Object propertyId, Component filter) {
        owner.getColumnIdToFilterMap().put(propertyId, filter);
        filter.setParent(owner.getAsComponent());
    }

    /**
     * Removes the filter.
     *
     * @param propertyId the property id
     */
    private void removeFilter(Object propertyId) {
        if (filters.get(propertyId) != null) {
            if (owner.getFilterable() != null) {
                owner.getFilterable().removeContainerFilter(
                        filters.get(propertyId));
            }
            addedFilters.remove(filters.remove(propertyId));
        }
    }

    /**
     * Sets the filter.
     *
     * @param filter the filter
     * @param propertyId the property id
     */
    private void setFilter(Filter filter, Object propertyId) {
        if (owner.getFilterable() != null) {
            owner.getFilterable().addContainerFilter(filter);
        }
        filters.put(propertyId, filter);
        addedFilters.add(filter);
    }

    /**
     * Creates the field.
     *
     * @param property the property
     * @param type the type
     * @return the abstract field
     */
    private AbstractField<?> createField(Object property, Class<?> type) {
        AbstractField<?> field = null;
        if (owner.getFilterGenerator() != null) {
            field = owner.getFilterGenerator().getCustomFilterComponent(
                    property);
        }
        if (field != null) {
            customFields.put(field, property);
        } else if (type == null) {
            field = new TextField();
            ((TextField) field).setNullRepresentation("");
            texts.put(((TextField) field), property);
        } else if (type == boolean.class || type == Boolean.class) {
            field = createBooleanField(property);
        } else if (type.isEnum()) {
            field = createEnumField(type, property);
        } else if (type == Date.class || type == Timestamp.class
                || type == java.sql.Date.class) {
            ExtDateFilterPopup dfp = createDateField(property);
            dfp.setWidth(100, Unit.PERCENTAGE);
            dfp.setImmediate(true);
            return dfp;
        } else if ((type == Integer.class || type == Long.class
                || type == Float.class || type == Double.class
                || type == Short.class || type == Byte.class
                || type == int.class || type == long.class
                || type == float.class || type == double.class
                || type == short.class || type == byte.class
                || type == BigDecimal.class || type == BigInteger.class)
                && owner.getFilterDecorator() != null
                && owner.getFilterDecorator().usePopupForNumericProperty(
                        property)) {
            ExtNumberFilterPopup nfp = createNumericField(type, property);
            nfp.setWidth(100, Unit.PERCENTAGE);
            nfp.setImmediate(true);
            return nfp;
        } else {
            field = createTextField(property);
        }
        field.setWidth(null);
        field.setImmediate(true);
        return field;
    }

    /**
     * Creates the text field.
     *
     * @param propertyId the property id
     * @return the abstract field
     */
    private AbstractField<?> createTextField(Object propertyId) {
        final TextField textField = new TextField();
        if (owner.getFilterDecorator() != null) {
            if (owner.getFilterDecorator().isTextFilterImmediate(propertyId)) {
                textField.addTextChangeListener(new TextChangeListener() {

                    @Override
                    public void textChange(TextChangeEvent event) {
                        textField.setValue(event.getText());
                    }
                });
                textField.setTextChangeTimeout(owner.getFilterDecorator()
                        .getTextChangeTimeout(propertyId));
            }
            if (owner.getFilterDecorator().getAllItemsVisibleString() != null) {
                textField.setInputPrompt(owner.getFilterDecorator()
                        .getAllItemsVisibleString());
            }
        }
        textField.setNullRepresentation("");
        texts.put(textField, propertyId);
        return textField;
    }

    /**
     * Creates the enum field.
     *
     * @param type the type
     * @param propertyId the property id
     * @return the abstract field
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private AbstractField createEnumField(Class<?> type, Object propertyId) {
        ComboBox enumSelect = new ComboBox();
        /* Add possible 'view all' item */
        if (owner.getFilterDecorator() != null
                && owner.getFilterDecorator().getAllItemsVisibleString() != null) {
            Object nullItem = enumSelect.addItem();
            enumSelect.setNullSelectionItemId(nullItem);
            enumSelect.setItemCaption(nullItem, owner.getFilterDecorator()
                    .getAllItemsVisibleString());
        }
        /* Add items from enumeration */
        for (Object o : EnumSet.allOf((Class<Enum>) type)) {
            enumSelect.addItem(o);
            if (owner.getFilterDecorator() != null) {
                String caption = owner.getFilterDecorator()
                        .getEnumFilterDisplayName(propertyId, o);
                enumSelect.setItemCaption(o, caption == null ? o.toString()
                        : caption);
                Resource icon = owner.getFilterDecorator().getEnumFilterIcon(
                        propertyId, o);
                if (icon != null) {
                    enumSelect.setItemIcon(o, icon);
                }
            } else {
                enumSelect.setItemCaption(o, o.toString());
            }
        }
        enums.put(enumSelect, propertyId);
        return enumSelect;
    }

    /**
     * Creates the boolean field.
     *
     * @param propertyId the property id
     * @return the abstract field
     */
    private AbstractField<?> createBooleanField(Object propertyId) {
        ComboBox booleanSelect = new ComboBox();
        booleanSelect.addItem(true);
        booleanSelect.addItem(false);
        if (owner.getFilterDecorator() != null) {
            /* Add possible 'view all' item */
            if (owner.getFilterDecorator().getAllItemsVisibleString() != null) {
                Object nullItem = booleanSelect.addItem();
                booleanSelect.setNullSelectionItemId(nullItem);
                booleanSelect.setItemCaption(nullItem, owner
                        .getFilterDecorator().getAllItemsVisibleString());
            }
            String caption = owner.getFilterDecorator()
                    .getBooleanFilterDisplayName(propertyId, true);
            booleanSelect.setItemCaption(true, caption == null ? "true"
                    : caption);
            Resource icon = owner.getFilterDecorator().getBooleanFilterIcon(
                    propertyId, true);
            if (icon != null) {
                booleanSelect.setItemIcon(true, icon);
            }
            caption = owner.getFilterDecorator().getBooleanFilterDisplayName(
                    propertyId, false);
            booleanSelect.setItemCaption(false, caption == null ? "false"
                    : caption);
            icon = owner.getFilterDecorator().getBooleanFilterIcon(propertyId,
                    false);
            if (icon != null) {
                booleanSelect.setItemIcon(false, icon);
            }
        } else {
            booleanSelect.setItemCaption(true, "true");
            booleanSelect.setItemCaption(false, "false");
        }
        booleans.put(booleanSelect, propertyId);
        return booleanSelect;
    }

    /**
     * Creates the date field.
     *
     * @param propertyId the property id
     * @return the ext date filter popup
     */
    private ExtDateFilterPopup createDateField(Object propertyId) {
        ExtDateFilterPopup dateFilterPopup = new ExtDateFilterPopup(
                owner.getFilterDecorator(), propertyId);
        dates.put(dateFilterPopup, propertyId);
        return dateFilterPopup;
    }

    /**
     * Creates the numeric field.
     *
     * @param type the type
     * @param propertyId the property id
     * @return the ext number filter popup
     */
    private ExtNumberFilterPopup createNumericField(Class<?> type,
            Object propertyId) {
        ExtNumberFilterPopup numberFilterPopup = new ExtNumberFilterPopup(
                owner.getFilterDecorator());
        numbers.put(numberFilterPopup, propertyId);
        return numberFilterPopup;
    }

    /**
     * Initialize listener.
     *
     * @return the value change listener
     */
    private ValueChangeListener initializeListener() {
        return new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (owner.getFilterable() == null) {
                    return;
                }
                Property<?> field = event.getProperty();
                updateFilterForField(field);
            }
        };
    }

    /**
     * Update filter for field.
     *
     * @param field the field
     */
    private void updateFilterForField(Property<?> field) {
        Object value = field.getValue();
        Object propertyId = null;
        if (customFields.containsKey(field)) {
            propertyId = customFields.get(field);
        } else if (texts.containsKey(field)) {
            propertyId = texts.get(field);
        } else if (dates.containsKey(field)) {
            propertyId = dates.get(field);
        } else if (numbers.containsKey(field)) {
            propertyId = numbers.get(field);
        } else if (enums.containsKey(field)) {
            propertyId = enums.get(field);
        } else if (booleans.containsKey(field)) {
            propertyId = booleans.get(field);
        }
                
        if (owner instanceof ExtPagedTableBase) {
            doStuffOnFilter(field, propertyId, value);
            ((ExtPagedTableBase) owner).setFilterChange(propertyId,addedFilters);       
        }else{
        owner.setRefreshingEnabled(false);
        doStuffOnFilter(field, propertyId, value);        

        /*
         * If the owner is a PagedFilteringTable, move to the first page
         */
        
        
             if (owner instanceof ExtPagedFilterTable<?>) {
            ((ExtPagedFilterTable<?>) owner).setCurrentPage(1);
        }
        
        owner.setRefreshingEnabled(true);
        }
           
        
        
    }

    private void doStuffOnFilter(Property<?> field, Object propertyId,
            Object value){
        // Generate a new filter
        Filter newFilter = generateFilter(field, propertyId, value);

        // Check if the filter is already set
        Filter possiblyExistingFilter = filters.get(propertyId);
        if (possiblyExistingFilter != null && newFilter != null
                && possiblyExistingFilter.equals(newFilter)) {
            return;
        }

        /* Remove the old filter and set the new filter */
        removeFilter(propertyId);
        if (newFilter != null) {
            setFilter(newFilter, propertyId);
            if (owner.getFilterGenerator() != null) {
                owner.getFilterGenerator().filterAdded(propertyId,
                        newFilter.getClass(), value);
            }
        } else {
            if (owner.getFilterGenerator() != null) {
                owner.getFilterGenerator().filterRemoved(propertyId);
            }
        }
    }
    /**
     * Generate filter for field.
     *
     * @param field the field
     * @return the filter
     */
    private Filter generateFilterForField(Property<?> field) {
        Object value = field.getValue();
        Object propertyId = null;
        if (customFields.containsKey(field)) {
            propertyId = customFields.get(field);
        } else if (texts.containsKey(field)) {
            propertyId = texts.get(field);
        } else if (dates.containsKey(field)) {
            propertyId = dates.get(field);
        } else if (numbers.containsKey(field)) {
            propertyId = numbers.get(field);
        } else if (enums.containsKey(field)) {
            propertyId = enums.get(field);
        } else if (booleans.containsKey(field)) {
            propertyId = booleans.get(field);
        }

        return generateFilter(field, propertyId, value);
    }

    /**
     * Run filters now.
     */
    public void runFiltersNow() {
        owner.setRefreshingEnabled(false);
        if (owner.getFilterable() != null) {
            owner.getFilterable().removeContainerFilter(lastOnDemandFilter);
        }
        List<Filter> filters = new ArrayList<Filter>();
        for (AbstractField<?> f : customFields.keySet()) {
            addNonNullFilter(filters, f);
        }
        for (AbstractField<?> f : texts.keySet()) {
            addNonNullFilter(filters, f);
        }
        for (AbstractField<?> f : dates.keySet()) {
            addNonNullFilter(filters, f);
        }
        for (AbstractField<?> f : numbers.keySet()) {
            addNonNullFilter(filters, f);
        }
        for (AbstractField<?> f : enums.keySet()) {
            addNonNullFilter(filters, f);
        }
        for (AbstractField<?> f : booleans.keySet()) {
            addNonNullFilter(filters, f);
        }

        Filter[] filtersArray = filters.toArray(new Filter[0]);
        lastOnDemandFilter = new And(filtersArray);
        if (owner.getFilterable() != null) {            
            owner.getFilterable().addContainerFilter(lastOnDemandFilter);
        }
        addedFilters.add(lastOnDemandFilter);        
        owner.setRefreshingEnabled(true);
        if (owner instanceof PagedTreeTableBase) {
            ((PagedTreeTableBase) owner).setFilterChange(addedFilters);            
        }else if (owner instanceof ExtPagedTable) { 
            ((ExtPagedTable) owner).setFilterChange(addedFilters);            
        }
    }

    /**
     * Adds the non null filter.
     *
     * @param filters the filters
     * @param f the f
     */
    private void addNonNullFilter(List<Filter> filters, AbstractField<?> f) {
        Filter filter = generateFilterForField(f);
        if (null != filter) {
            filters.add(filter);
        }
    }

    /**
     * The Interface IFilterTable.
     */
    interface IFilterTable {

        /**
         * Gets the filterable.
         *
         * @return the filterable
         */
        public Filterable getFilterable();

        /**
         * Gets the filter generator.
         *
         * @return the filter generator
         */
        public ExtFilterGenerator getFilterGenerator();

        /**
         * Gets the visible columns.
         *
         * @return the visible columns
         */
        public Object[] getVisibleColumns();

        /**
         * Gets the container property ids.
         *
         * @return the container property ids
         */
        public Collection<?> getContainerPropertyIds();

        /**
         * Gets the container data source.
         *
         * @return the container data source
         */
        public Container getContainerDataSource();

        /**
         * Gets the type.
         *
         * @param propertyId the property id
         * @return the type
         */
        public Class<?> getType(Object propertyId);

        /**
         * Gets the column id to filter map.
         *
         * @return the column id to filter map
         */
        public Map<Object, Component> getColumnIdToFilterMap();

        /**
         * Gets the filter decorator.
         *
         * @return the filter decorator
         */
        public ExtFilterDecorator getFilterDecorator();

        /**
         * Gets the page length.
         *
         * @return the page length
         */
        public int getPageLength();

        /**
         * Gets the as component.
         *
         * @return the as component
         */
        public HasComponents getAsComponent();

        /**
         * Sets the refreshing enabled.
         *
         * @param enabled the new refreshing enabled
         */
        public void setRefreshingEnabled(boolean enabled);

    }

    /**
     * Sets the filter on demand mode.
     *
     * @param filterOnDemand the new filter on demand mode
     */
    void setFilterOnDemandMode(boolean filterOnDemand) {
        if (runFiltersOnDemand == filterOnDemand) {
            return;
        } else {
            runFiltersOnDemand = filterOnDemand;
            destroyFilterComponents();
            initializeFilterFields();
        }
    }
}