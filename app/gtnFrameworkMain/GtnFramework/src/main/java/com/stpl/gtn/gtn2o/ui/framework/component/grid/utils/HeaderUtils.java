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
import com.vaadin.ui.components.grid.HeaderRow;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        if (columnStart > columnEnd) {
            return;
        }
        if (columnEnd > columnCount) {
            columnEnd = columnCount;
        }
        repaintGrid( pagedTreeGrid);
        List<Object> currentSingleColumns = pagedTreeGrid.getTableConfig().getVisibleColumns().subList(columnStart, columnEnd);
        for (int j = 0; j < currentSingleColumns.size(); j++) {
            String column = (currentSingleColumns.get(j)).toString();
            PagedTreeGrid.gtnlogger.info("column = " + column);
            String header=pagedTreeGrid.getTableConfig().getColumnHeaders().get(columnStart + j);
            if (pagedTreeGrid.getTableConfig().getAggregationColumnHeader() != null && header.contains(pagedTreeGrid.getTableConfig().getAggregationColumnHeader())) {
               // Aggregation 
                pagedTreeGrid.getGrid().addColumn((GtnWsRecordBean row) -> {
                    int sum = 0;
                    sum = row.getReadOnlyPropeties().stream().
                            filter(e -> e.toString().contains(header))
                            .map(String::valueOf)
                            .filter((columns) -> (!columns.equals(header)))
                            .map((columns) -> GridUtils.getInt(row.getPropertyValue(columns)))
                            .reduce(sum, Integer::sum);
                    return sum;
                }).setCaption(header).setId(column).setWidth(170);
            } else {
                pagedTreeGrid.getGrid().addColumn((GtnWsRecordBean row) -> row.getPropertyValue(column)).setCaption(header).setId(column).setWidth(170);
            }
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
    }

    public static void repaintGrid( PagedTreeGrid pagedTreeGrid) {
        if (pagedTreeGrid.getGrid().getParent() != null) {
            VerticalLayout parent = (VerticalLayout) pagedTreeGrid.getGrid().getParent();
            pagedTreeGrid.setGrid(new TreeGrid<>());
            pagedTreeGrid.getGrid().setWidth(pagedTreeGrid.getComponentConfig().getComponentWidth());
            pagedTreeGrid.getGrid().setHeight(pagedTreeGrid.getComponentConfig().getComponentHight());
            parent.replaceComponent(parent.getComponent(0), pagedTreeGrid.getGrid());
            pagedTreeGrid.initializeGrid(pagedTreeGrid.componentIdInMap);
            
        }
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
            String[] stringArray = getSingleColumnsMapping(currentSingleColumns, joinList).toArray(new String[0]);
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
                Object[] doubleHeaders = pagedTreeGrid.getTableConfig().getRightTableTripleHeaderMap().get(property);
                List<Object> singleList=new ArrayList<>();
                for (Object dbl : doubleHeaders) {
                    if(pagedTreeGrid.getTableConfig().getRightTableDoubleHeaderMap().get(dbl)!=null){
                       singleList.addAll(Arrays.stream(pagedTreeGrid.getTableConfig().getRightTableDoubleHeaderMap().get(dbl))
                             .collect(Collectors.toList())) ;
                    }
                }
                
                String[] columnList = currentSingleColumns.stream().map(String::valueOf).filter(e -> arrayContains(singleList.toArray(), e))
                        .collect(Collectors.toList()).toArray(new String[0]);
                if (pagedTreeGrid.getTableConfig().isEnableCheckBoxInTripleHeader()) {
                    CheckBoxGroup vaadinCheckBoxGroup = new CheckBoxGroup();
                    vaadinCheckBoxGroup.setItems(pagedTreeGrid.getTableConfig().getRightTableTripleVisibleHeaders().get(j++));
                    groupingHeader.join(columnList).setComponent(vaadinCheckBoxGroup);
                } else {
                    if (columnList.length > 1) {
                        groupingHeader.join(columnList).setText(pagedTreeGrid.getTableConfig().getRightTableTripleVisibleHeaders().get(j++));
                    } else if (columnList.length > 0) {
                        groupingHeader.getCell(columnList[columnList.length-1]).setText(pagedTreeGrid.getTableConfig().getRightTableTripleVisibleHeaders().get(j++));
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

    private static List<String> getSingleColumnsMapping(List<Object> currentSingleColumns, Object joinList[]) {

        return currentSingleColumns.stream().filter(e -> arrayContains(joinList, String.valueOf(e))).
                map(Object::toString).collect(Collectors.toList());
    }

    private static boolean arrayContains(Object[] input, String value) {
        return Arrays.stream(input).filter(e -> value.contains(e.toString())).count() > 0;
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
