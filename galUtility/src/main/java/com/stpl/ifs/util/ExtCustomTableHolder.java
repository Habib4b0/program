/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.ifs.util;

import com.stpl.addons.tableexport.ExportableFormattedProperty;
import com.stpl.addons.tableexport.TableHolder;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.UI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 *
 * @author Abhiram
 */
public class ExtCustomTableHolder implements TableHolder {

    /**
     * Whether the Container is a HierarchicalContainer or an extension thereof.
     */
    private boolean hierarchical;
    int headerNo = 0;
    private ExtCustomTable table;
    /**
     * The Property ids of the Items in the Table.
     */
    private Map<Integer, LinkedList<Object>> propIdsMap = new HashMap<Integer, LinkedList<Object>>();

    public ExtCustomTableHolder(ExtCustomTable table) {
        this.table = table;
        propIdsMap.put(0, new LinkedList<Object>(Arrays.asList(table.getVisibleColumns())));
        if (table.isDoubleHeaderVisible()) {
            setHeaderNumber(1);
            propIdsMap.put(1, new LinkedList<Object>(Arrays.asList(table.getDoubleHeaderVisibleColumns())));
        }
        if (table.isTripleHeaderVisible()) {
            setHeaderNumber(2);
            propIdsMap.put(2, new LinkedList<Object>(Arrays.asList(table.getTripleHeaderVisibleColumns())));
        }
        if (table.getContainerDataSource() instanceof Container.Hierarchical) {
            setHierarchical(true);
        } else {
            setHierarchical(false);
        }
        }

    @Override
    public List<Object> getPropIds(int headerNo) {
        LinkedList<Object> propIds = propIdsMap.get(headerNo);
        if (propIds == null) {
            propIds = new LinkedList<Object>();
        }
        return propIds;
    }

    @Override
    public boolean isHierarchical() {
        return hierarchical;
    }

    @Override
    final public void setHierarchical(boolean hierarchical) {
        this.hierarchical = hierarchical;
    }

    @Override
    public Short getCellAlignment(Object propId, int headerNo) {
        ExtCustomTable.Align vaadinAlignment = null;
        if (headerNo == 0) {
            vaadinAlignment = table.getColumnAlignment(propId);
        } else if (headerNo == 1) {
            vaadinAlignment = table.getDoubleHeaderColumnAlignment(propId);
        } else if (headerNo == 2) {
            vaadinAlignment = table.getTripleHeaderColumnAlignment(propId);
        }
        return vaadinAlignmentToCellAlignment(vaadinAlignment);
    }

    private Short vaadinAlignmentToCellAlignment(final ExtCustomTable.Align vaadinAlignment) {
        if (ExtCustomTable.Align.LEFT.equals(vaadinAlignment)) {
            return CellStyle.ALIGN_LEFT;
        } else if (ExtCustomTable.Align.RIGHT.equals(vaadinAlignment)) {
            return CellStyle.ALIGN_RIGHT;
        } else {
            return CellStyle.ALIGN_CENTER;
        }
    }

    @Override
    public boolean isGeneratedColumn(final Object propId) throws IllegalArgumentException {
        return table.getColumnGenerator(propId) != null;
    }

    @Override
    public Property getPropertyForGeneratedColumn(final Object propId, final Object rootItemId) throws IllegalArgumentException {
        Property prop;
        final ExtCustomTable.ColumnGenerator tcg = table.getColumnGenerator(propId);
        if (tcg instanceof ExportableCustomColumnGenerator) {
            prop = ((ExportableCustomColumnGenerator) tcg).getGeneratedProperty(rootItemId, propId);
        } else {
            prop = null;
        }
        return prop;
    }

    @Override
    public Class<?> getPropertyTypeForGeneratedColumn(final Object propId) throws IllegalArgumentException {
        Class<?> classType;
        final ExtCustomTable.ColumnGenerator tcg = table.getColumnGenerator(propId);
        if (tcg instanceof ExportableCustomColumnGenerator) {
            classType = ((ExportableCustomColumnGenerator) tcg).getType();
        } else {
            classType = String.class;
        }
        return classType;
    }

    @Override
    public boolean isExportableFormattedProperty() {
        return table instanceof ExportableFormattedProperty;
    }

    @Override
    public boolean isColumnCollapsed(Object propertyId, int headerNo) {
        if (headerNo == 0) {
            return table.isColumnCollapsed(propertyId);
        } else if (headerNo == 1) {
            return table.isDoubleHeaderColumnCollapsed(propertyId);
        } else if (headerNo == 2) {
            return table.isTripleHeaderColumnCollapsed(propertyId);
        }
        return false;
    }

    @Override
    public UI getUI() {
        return table.getUI();
    }

    @Override
    public String getColumnHeader(Object propertyId, int headerNo) {
        if (headerNo == 0) {
            return table.getColumnHeader(propertyId);
        } else if (headerNo == 1) {
            return table.getDoubleHeaderColumnHeader(propertyId);
        } else if (headerNo == 2) {
            return table.getTripleHeaderColumnHeader(propertyId);
        }
        return "";
    }

    @Override
    public Container getContainerDataSource() {
        return table.getContainerDataSource();
    }

    @Override
    public String getFormattedPropertyValue(Object rowId, Object colId, Property property) {
        return ((ExportableFormattedProperty) table).getFormattedPropertyValue(rowId, colId, property);
    }

    /**
     *
     * @return
     */
    @Override
    public int getHeaderNumber() {
        return headerNo;
    }

    protected void setHeaderNumber(int headerNo) {
        this.headerNo = headerNo;
    }

    @Override
    public boolean isMultiHeaderVisible() {
        return true;
    }

    @Override
    public int getMergeColumnNumber(Object propId,int headerNo) {
         if (headerNo == 0) {
            return 1;
        } else if (headerNo == 1) {
            return table.getMapedHeaderSizeFromDoubleHeader(String.valueOf(propId));
        } else if (headerNo == 2) {
            return table.getMapedHeaderSizeFromTripleHeader(String.valueOf(propId));
        }
         return 1;
    }

    @Override
    public List<Object> getPropIds() {
        return getPropIds(0);
    }

    @Override
    public Short getCellAlignment(Object propId) {
        return getCellAlignment(propId,0);
    }

    @Override
    public boolean isColumnCollapsed(Object propertyId) {
        return isColumnCollapsed(propertyId,0);
    }

    @Override
    public String getColumnHeader(Object propertyId) {
        return getColumnHeader(propertyId,0);
    }

    @Override
    public int getMergeColumnNumber(Object propId) {
        return getMergeColumnNumber(propId,0);
    }
    
}