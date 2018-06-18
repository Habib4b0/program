/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.grid.utils;

import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedTreeGrid;
import static com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedTreeGrid.gtnlogger;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.vaadin.data.TreeData;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TreeGrid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Karthik.Raja
 */
/**
 * *
 *
 * Instance creation not allowed ,Contains Only Static Methods
 */
public class HeaderUtils {

    private HeaderUtils() {
        throw new RuntimeException("Can not create object for this class " + HeaderUtils.class.getName());
    }

    public static void configureGridColumns(int columnStart, int columnEnd, PagedTreeGrid pagedTreeGrid) {
        int columnCount = pagedTreeGrid.getTableConfig().getColumnHeaders().size();
        if (columnEnd > columnCount || columnStart > columnEnd) {
            return;
        }
        TreeData<GtnWsRecordBean> treedata = null;
        treedata = repaintGrid(treedata, pagedTreeGrid);
        List<Object> currentSingleColumns = pagedTreeGrid.getTableConfig().getVisibleColumns().subList(columnStart, columnEnd);
        for (int j = 0; j < currentSingleColumns.size() && columnEnd < columnCount; j++) {
            String column = (currentSingleColumns.get(j)).toString();
            PagedTreeGrid.gtnlogger.info("column = " + column);
            pagedTreeGrid.getGrid().addColumn((GtnWsRecordBean row) -> row.getPropertyValue(column)).setCaption(pagedTreeGrid.getTableConfig().getColumnHeaders().get(columnStart + j)).setId(column).setWidth(170);
        }
        if (pagedTreeGrid.getTableConfig().getCustomFilterConfigMap() != null) {
            pagedTreeGrid.shiftLeftSingeHeader = true;
        }
        if (pagedTreeGrid.getTableConfig().isEnableRadioButtonInSingleHeader()) {
            HeaderRow single = pagedTreeGrid.getGrid().getHeaderRow(0);
            for (int j = 0; j < currentSingleColumns.size(); j++) {
                String column = (currentSingleColumns.get(j)).toString();
                RadioButtonGroup vaadinRadioButtonGroup = new RadioButtonGroup();
                vaadinRadioButtonGroup.setItems(pagedTreeGrid.getTableConfig().getRightTableVisibleHeader()[columnStart + j]);
                single.getCell(column).setComponent(vaadinRadioButtonGroup);
            }
        }
        if (pagedTreeGrid.getTableConfig().isDoubleHeaderVisible()) {
            addDoubleHeader(currentSingleColumns, pagedTreeGrid);
        }
        addTripleHeader(currentSingleColumns, pagedTreeGrid);
        if (treedata != null) {
            pagedTreeGrid.getGrid().setTreeData(treedata);
        }
    }

    public static TreeData<GtnWsRecordBean> repaintGrid(TreeData<GtnWsRecordBean> treedata, PagedTreeGrid pagedTreeGrid) {
        if (pagedTreeGrid.getGrid().getParent() != null) {
            treedata = pagedTreeGrid.getGrid().getTreeData();
            VerticalLayout parent = (VerticalLayout) pagedTreeGrid.getGrid().getParent();
            pagedTreeGrid.setGrid(new TreeGrid<>());
            pagedTreeGrid.getGrid().setWidth(pagedTreeGrid.getComponentConfig().getComponentWidth());
            pagedTreeGrid.getGrid().setHeight(pagedTreeGrid.getComponentConfig().getComponentHight());
            parent.replaceComponent(parent.getComponent(0), pagedTreeGrid.getGrid());
        }
        return treedata;
    }

    public static void addDoubleHeader(List<Object> currentSingleColumns, PagedTreeGrid pagedTreeGrid) {
        HeaderRow groupingHeader = pagedTreeGrid.getGrid().getHeaderRowCount() > 1 ? pagedTreeGrid.getGrid().getHeaderRow(1) : pagedTreeGrid.getGrid().prependHeaderRow();
        int j = 0;
        if (pagedTreeGrid.shiftLeftSingeHeader) {
            shiftLeftHeader(groupingHeader, pagedTreeGrid);
        }
        for (Object property : pagedTreeGrid.getTableConfig().getLeftTableDoubleHeaderVisibleColumns()) {
            Object[] joinList = pagedTreeGrid.getTableConfig().getLeftTableDoubleHeaderMap().get(property);
            if (joinList != null) {
                String[] stringArray = Arrays.copyOf(joinList, joinList.length, String[].class);
                if (stringArray.length > 1) {
                    groupingHeader.join(stringArray).setText(pagedTreeGrid.getTableConfig().getLeftTableDoubleHeaderVisibleHeaders().get(j++));
                } else if (stringArray.length > 0) {
                    groupingHeader.getCell(stringArray[0]).setText(pagedTreeGrid.getTableConfig().getLeftTableDoubleHeaderVisibleHeaders().get(j++));
                }
            }
        }
        j = 0;
        for (Object property : pagedTreeGrid.getTableConfig().getRightTableDoubleHeaderVisibleColumns()) {
            Object[] joinList = pagedTreeGrid.getTableConfig().getRightTableDoubleHeaderMap().get(property);
            String[] stringArray = getSingleColumnsMapping(currentSingleColumns, joinList);
            if (stringArray.length > 1) {
                groupingHeader.join(stringArray).setText(pagedTreeGrid.getTableConfig().getRightTableDoubleVisibleHeaders().get(j++));
            } else if (stringArray.length > 0) {
                groupingHeader.getCell(stringArray[0]).setText(pagedTreeGrid.getTableConfig().getRightTableDoubleVisibleHeaders().get(j++));
            }
        }
    }

    public static void addTripleHeader(List<Object> currentSingleColumns, PagedTreeGrid pagedTreeGrid) {
        if (pagedTreeGrid.getTableConfig().isTripleHeaderVisible()) {
            HeaderRow doubleHeader = pagedTreeGrid.getGrid().getHeaderRow(1);
            HeaderRow groupingHeader = pagedTreeGrid.getGrid().prependHeaderRow();
            int j = 0;
            for (Object property : pagedTreeGrid.getTableConfig().getRightTableTripleHeaderMap().keySet()) {
                Object[] joinList = pagedTreeGrid.getTableConfig().getRightTableTripleHeaderMap().get(property);
                List<String> stringArray = Arrays.asList(getSingleColumnsMapping(currentSingleColumns, joinList));
                Set<HeaderCell> columnList = new HashSet<>();
                for (int i = 0; i < joinList.length; i++) {
                    Object object = joinList[i];
                    if (stringArray.contains(object.toString())) {
                        columnList.add(doubleHeader.getCell(object.toString()));
                    }
                }
                if (pagedTreeGrid.getTableConfig().isEnableCheckBoxInTripleHeader()) {
                    CheckBoxGroup vaadinCheckBoxGroup = new CheckBoxGroup();
                    vaadinCheckBoxGroup.setItems(pagedTreeGrid.getTableConfig().getRightTableTripleVisibleHeaders().get(j++));
                    groupingHeader.join(columnList).setComponent(vaadinCheckBoxGroup);
                } else {
                    if (columnList.size() > 1) {
                        groupingHeader.join(columnList).setText(pagedTreeGrid.getTableConfig().getRightTableTripleVisibleHeaders().get(j++));
                    } else if (columnList.size() > 0) {
                        groupingHeader.getCell(columnList.iterator().next().getColumnId()).setText(pagedTreeGrid.getTableConfig().getRightTableTripleVisibleHeaders().get(j++));
                    }
                }
            }
        }
        if (pagedTreeGrid.getTableConfig().getCustomFilterConfigMap() != null) {
            FilterUtils.setFilterToGrid(pagedTreeGrid);
        }
        addTableHeaderCheck(pagedTreeGrid);
        pagedTreeGrid.shiftLeftSingeHeader = false;
    }

    static String[] getSingleColumnsMapping(List<Object> currentSingleColumns, Object joinList[]) {
        return Arrays.stream(joinList).filter(e -> currentSingleColumns.contains(e)).
                map(Object::toString).collect(Collectors.toList()).toArray(new String[0]);
    }

    // CheckBox in DoubleColumnHeader
    private static void addTableHeaderCheck(PagedTreeGrid pagedTreeGrid) {

        HeaderRow row = pagedTreeGrid.getGrid().getHeaderRowCount() > 2 ? pagedTreeGrid.getGrid().getHeaderRow(1) : pagedTreeGrid.getGrid().getHeaderRow(0);
        if (pagedTreeGrid.getTableConfig().getCheckBoxVisibleColoumn() != null) {
            pagedTreeGrid.getTableConfig().getCheckBoxVisibleColoumn().stream().map((columnId) -> {
                return columnId;
            }).forEach((columnId) -> {
                CheckBox vaadinCheckBoxGroup = new CheckBox();
                row.getCell(columnId).setComponent(vaadinCheckBoxGroup);
            });
        }
    }

    public static void shiftLeftHeader(HeaderRow groupingHeader, PagedTreeGrid pagedTreeGrid) {
        for (int j = 0; j < pagedTreeGrid.getTableConfig().getLeftTableColumnMappingId().length; j++) {
            String column = (pagedTreeGrid.getTableConfig().getLeftTableColumnMappingId()[j]).toString();
            gtnlogger.info("column = " + column);
            groupingHeader.getCell(column).setText(pagedTreeGrid.getTableConfig().getColumnHeaders().get(j));

        }
    }
}
