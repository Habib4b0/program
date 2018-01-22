package com.stpl.addons.tableexport;

import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.Table;
import com.vaadin.ui.UI;
import org.apache.poi.ss.usermodel.CellStyle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Abhiram
 */
public class DefaultTableHolder implements TableHolder {

    /**
     * Whether the Container is a HierarchicalContainer or an extension thereof.
     */
    private boolean hierarchical;
    private Table table;
    /**
     * The Property ids of the Items in the Table.
     */
    private LinkedList<Object> propIds;

    public DefaultTableHolder(Table table) {
        this.table = table;
        this.propIds = new LinkedList<Object>(Arrays.asList(table.getVisibleColumns()));
        if (table.getContainerDataSource() instanceof Container.Hierarchical) {
            setHierarchical(true);
        } else {
            setHierarchical(false);
        }
    }

    @Override
    public List<Object> getPropIds(int headerNo) {
        return propIds;
    }

    @Override
    public List<Object> getPropIds() {
        return getPropIds(0);
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
    public Short getCellAlignment(Object propId) {
        return getCellAlignment(propId, 0);
    }

    @Override
    public Short getCellAlignment(Object propId, int headerNo) {
        final Table.Align vaadinAlignment = table.getColumnAlignment(propId);
        return vaadinAlignmentToCellAlignment(vaadinAlignment);
    }

    private Short vaadinAlignmentToCellAlignment(final Table.Align vaadinAlignment) {
        if (Table.Align.LEFT.equals(vaadinAlignment)) {
            return CellStyle.ALIGN_LEFT;
        } else if (Table.Align.RIGHT.equals(vaadinAlignment)) {
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
        final Table.ColumnGenerator tcg = table.getColumnGenerator(propId);
        if (tcg instanceof ExportableColumnGenerator) {
            prop = ((ExportableColumnGenerator) tcg).getGeneratedProperty(rootItemId, propId);
        } else {
            prop = null;
        }
        return prop;
    }

    @Override
    public Class<?> getPropertyTypeForGeneratedColumn(final Object propId) throws IllegalArgumentException {
        Class<?> classType;
        final Table.ColumnGenerator tcg = table.getColumnGenerator(propId);
        if (tcg instanceof ExportableColumnGenerator) {
            classType = ((ExportableColumnGenerator) tcg).getType();
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
        return table.isColumnCollapsed(propertyId);
    }
    @Override
    public boolean isColumnCollapsed(Object propertyId) {
        return isColumnCollapsed(propertyId,0);
    }
    @Override
    public UI getUI() {
        return table.getUI();
    }

    @Override
    public String getColumnHeader(Object propertyId, int headerNo) {
        return table.getColumnHeader(propertyId);
    }
    @Override
    public String getColumnHeader(Object propertyId) {
        return getColumnHeader(propertyId,0);
    }
    @Override
    public Container getContainerDataSource() {
        return table.getContainerDataSource();
    }

    @Override
    public String getFormattedPropertyValue(Object rowId, Object colId, Property property) {
        return ((ExportableFormattedProperty) table).getFormattedPropertyValue(rowId, colId, property);
    }

    public boolean isMultiHeaderVisible() {
        return false;
    }

    public int getHeaderNumber() {
        return 0;
    }

    public int getMergeColumnNumber(Object propId, int headerNo) {
        return 1;
    }

    public int getMergeColumnNumber(Object propId) {
        return getMergeColumnNumber(propId, 0);
    }
}