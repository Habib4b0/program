/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.grid.utils;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedTreeGrid;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.data.HasValue;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.components.grid.HeaderRow;
import java.time.LocalDate;

/**
 *
 * @author Karthik.Raja
 */
/**
 * *
 *
 * Instance creation not allowed ,Contains Only Static Methods
 */
final public class FilterUtils {

    private FilterUtils() {
        throw new RuntimeException("Can not create object for this class " + FilterUtils.class.getName());
    }

    public static Component getCustomFilterComponent(String property, PagedTreeGrid pagedTreeGrid) {
        try {
            PagedTreeGrid.gtnlogger.info("-------property------" + property);
            GtnUIFrameworkPagedTableCustomFilterConfig filterConfig = pagedTreeGrid.getTableConfig().getCustomFilterConfigMap().get(property);
            if (filterConfig.getGtnComponentType() == GtnUIFrameworkComponentType.TEXTBOX_VAADIN8) {
                TextField textField = new TextField();
                textField.setId(property);
                textField.addValueChangeListener(e -> onFilterTextChange(pagedTreeGrid));
                return textField;
            } else if (filterConfig.getGtnComponentType() == GtnUIFrameworkComponentType.DATEFIELDVAADIN8) {
                DateField dateField = new DateField();
                dateField.setId(property);
                dateField.addValueChangeListener(e -> onFilterDateChange(pagedTreeGrid));
                return dateField;
            } else if (filterConfig.getGtnComponentType() == GtnUIFrameworkComponentType.COMBOBOX_VAADIN8) {
                GtnUIFrameworkComponent component = filterConfig.getGtnComponentType().getGtnComponent();
                Component vaadinComponent = null;
                vaadinComponent = component.buildVaadinComponent(filterConfig.getGtnComponentConfig());
                ComboBox vaadinCombobox = (ComboBox) vaadinComponent;
                vaadinCombobox.setId(property);
                vaadinCombobox.addValueChangeListener(e -> onFilterTextChange(pagedTreeGrid));
                return vaadinCombobox;
            } else if (filterConfig.getGtnComponentType() == GtnUIFrameworkComponentType.CALENDAR_FIELD) {
                Button dateFilterPopupButton = new Button("Show all");
                return dateFilterPopupButton;
            }
        } catch (GtnFrameworkGeneralException exception) {
            PagedTreeGrid.gtnlogger.error("Exception while creating the filter component", exception);
        }
        return null;
    }

    private static void onFilterTextChange(PagedTreeGrid pagedTreeGrid) {
        pagedTreeGrid.resetGridToInitialState();
    }

    private static void onFilterDateChange(PagedTreeGrid pagedTreeGrid) {
        pagedTreeGrid.resetGridToInitialState();
    }

    public static HeaderRow setFilterToGrid(PagedTreeGrid pagedTreeGrid) {
        HeaderRow filterRow = pagedTreeGrid.getGrid().getDefaultHeaderRow();
        Component vaadinComponent = null;
        Object[] filterColumnIdList = pagedTreeGrid.getTableConfig().getLeftTableColumnMappingId();
        for (Object column : filterColumnIdList) {
            vaadinComponent = getCustomFilterComponent(String.valueOf(column), pagedTreeGrid);
            vaadinComponent.setId(column.toString());
            filterRow.getCell(String.valueOf(column)).setComponent(vaadinComponent);
        }
        return filterRow;
    }

   
}
