/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils;

import com.stpl.ifs.util.HelperDTO;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Item;
import com.vaadin.v7.data.Property;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Mohamed.Shahul
 */
public class HelperDTOFilter implements Container.Filter {

    private final Object helperPropertyId;
    private String filterDescription = StringUtils.EMPTY;
    private int filterId = 0;
    private boolean addFilterById = true;

    /**
     * Method to create Filter for helper DTO objects
     *
     * @param propertyId - columns
     * @param filterHelperDTO - Helper DTO Object
     * @param addFilterById - Values to be sent to filter as ID / Description
     */
    public HelperDTOFilter(Object propertyId,
            HelperDTO filterHelperDTO, boolean addFilterById) {
        this.helperPropertyId = propertyId;
        filterDescription = filterHelperDTO.getDescription();
        filterId = filterHelperDTO.getId();
        this.addFilterById = addFilterById;
    }

    /**
     * Method to create Filter for helper DTO objects
     *
     * @param propertyId - columns
     * @param filterId - Values to be sent to filter as ID
     */
    public HelperDTOFilter(Object propertyId,
            int filterId) {
        this.helperPropertyId = propertyId;
        this.filterId = filterId;
        this.addFilterById = true;
    }

    /**
     * Method to create Filter for helper DTO objects
     *
     * @param propertyId - columns
     * @param filterDescription - Values to be sent to filter as ID
     */
    public HelperDTOFilter(Object propertyId,
            String filterDescription) {
        this.helperPropertyId = propertyId;
        this.filterDescription = filterDescription;
        this.addFilterById = false;
    }

    @Override
    public boolean passesFilter(Object itemId, Item item) {
        final Property<?> p = item.getItemProperty(helperPropertyId);
        if (p == null) {
            return false;
        }
        Object propertyValue = p.getValue();
        if (propertyValue == null) {
            return false;
        }
        if (propertyValue instanceof HelperDTO) {
            final int idValue = ((HelperDTO) propertyValue).getId();
            final String descriptionValue = ((HelperDTO) propertyValue).getDescription();
            if (filterId != 0 && idValue != (filterId)) {
                return false;
            }
            if (filterDescription != null && !"-Select One-".equals(filterDescription) && !descriptionValue.contains(filterDescription)) {
                return false;
            }
        } else {
            final String descriptionValue = propertyValue.toString();
            if (filterDescription != null && !"-Select One-".equals(filterDescription) && !descriptionValue.contains(filterDescription)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean appliesToProperty(Object helperPropertyId) {
        return this.helperPropertyId.equals(helperPropertyId);
    }

    @Override
    public boolean equals(Object helperObj) {
        if (helperObj == null) {
            return false;
        }

        // Only ones of the objects of the same class can be equal
        if (helperObj.getClass() != getClass()) {
            return false;
        }
        final HelperDTOFilter o = (HelperDTOFilter) helperObj;

        // Checks the properties one by one
        if (helperPropertyId != o.helperPropertyId && o.helperPropertyId != null
                && !o.helperPropertyId.equals(helperPropertyId)) {
            return false;
        }
        if (filterId != o.filterId) {
            return false;
        }
        return !(filterDescription != o.filterDescription && o.filterDescription != null
                && !o.filterDescription.equals(filterDescription));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.helperPropertyId);
        hash = 89 * hash + Objects.hashCode(this.filterDescription);
        hash = 89 * hash + this.filterId;
        return hash;
    }

    /**
     * Returns the property identifier to which this filter applies.
     *
     * @return property id
     */
    public Object getPropertyId() {
        return helperPropertyId;
    }

    /**
     * Return Helper DTO decription
     *
     * @return filterDescription
     */
    public String getFilterDescription() {
        return filterDescription;
    }

    /**
     * Return Helper DTO Id
     *
     * @return filterId
     */
    public int getFilterId() {
        return filterId;
    }

    /**
     * Return Boolean = true when the filter value should be passed as ID Return
     * Boolean = false when the filter value should be passed as Description
     *
     * @return
     */
    public boolean isAddFilterById() {
        return addFilterById;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
