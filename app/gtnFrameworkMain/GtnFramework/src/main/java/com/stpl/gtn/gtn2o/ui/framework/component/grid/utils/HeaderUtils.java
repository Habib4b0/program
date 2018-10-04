/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.grid.utils;

import static com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedTreeGrid.gtnlogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedTreeGrid;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.AccessDeniedException;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TreeGrid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.HeaderRow;

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
		throw new AccessDeniedException("Can not create object for this class " + HeaderUtils.class.getName());
	}

	public static void configureGridColumns(int columnStart, int columnEnd, PagedTreeGrid pagedTreeGrid) {
		int columnCount = pagedTreeGrid.getTableConfig().getVisibleColumns().size();
		int columnEndCount = columnEnd;

		if ((columnStart + columnEnd) > columnCount) {
			columnEndCount = columnCount;
		}
		repaintGrid(pagedTreeGrid);
		List<Object> currentSingleColumns = addSingleHeader(pagedTreeGrid, columnStart, columnEndCount);
		if (pagedTreeGrid.getTableConfig().getCustomFilterConfigMap() != null) {
			pagedTreeGrid.setShiftLeftSingeHeader(true);
		}
		if (pagedTreeGrid.getTableConfig().isEnableRadioButtonInSingleHeader()) {
			HeaderRow single = pagedTreeGrid.getGrid().getHeaderRow(0);
			for (int j = 0; j < currentSingleColumns.size(); j++) {
				String column = (currentSingleColumns.get(j)).toString();
				RadioButtonGroup vaadinRadioButtonGroup = new RadioButtonGroup();
				vaadinRadioButtonGroup
						.setItems(pagedTreeGrid.getTableConfig().getRightTableVisibleHeader()[columnStart + j]);
				single.getCell(column).setComponent(vaadinRadioButtonGroup);
			}
		}
		if (pagedTreeGrid.getTableConfig().isDoubleHeaderVisible()) {
			addDoubleHeader(currentSingleColumns, pagedTreeGrid);
		}
		addTripleHeader(currentSingleColumns, pagedTreeGrid);
		refreshDataGrid(pagedTreeGrid);
	}

	private static List<Object> addSingleHeader(PagedTreeGrid pagedTreeGrid, int columnStart, int columnEnd) {
		List<Object> leftColumns = Arrays.stream(pagedTreeGrid.getTableConfig().getLeftTableColumnMappingId())
				.collect(Collectors.toList());
		for (int j = 0; j < leftColumns.size(); j++) {
			String column = (leftColumns.get(j)).toString();
			String header = pagedTreeGrid.getTableConfig().getLeftTableVisibleHeader()[j];
			pagedTreeGrid.getGrid().addColumn((GtnWsRecordBean row) -> row.getPropertyValue(column)).setCaption(header)
					.setId(column).setWidth(170);
		}
		long start = Long.parseLong(String.valueOf(columnStart)) + leftColumns.size();
		long limit = Long.parseLong(String.valueOf(columnEnd)) - leftColumns.size();
		List<Object> currentSingleColumns = pagedTreeGrid.getTableConfig().getVisibleColumns().stream().skip(start)
				.limit(limit).distinct().collect(Collectors.toList());
		for (int j = 0; j < currentSingleColumns.size(); j++) {
			String column = (currentSingleColumns.get(j)).toString();
			String header = pagedTreeGrid.getTableConfig().getRightTableVisibleHeader()[columnStart + j];
			pagedTreeGrid.getGrid().addColumn((GtnWsRecordBean row) -> row.getPropertyValue(column)).setCaption(header)
					.setId(column).setWidth(170);
		}
		return currentSingleColumns;
	}

	public static void repaintGrid(PagedTreeGrid pagedTreeGrid) {
		if (pagedTreeGrid.getGrid().getParent() != null) {
			VerticalLayout parent = (VerticalLayout) pagedTreeGrid.getGrid().getParent();
			pagedTreeGrid.setGrid(new TreeGrid<>());
			pagedTreeGrid.getGrid().setWidth(pagedTreeGrid.getComponentConfig().getComponentWidth());
			pagedTreeGrid.getGrid().setHeight(pagedTreeGrid.getComponentConfig().getComponentHight());
			parent.replaceComponent(parent.getComponent(0), pagedTreeGrid.getGrid());

		}
	}

	private static void refreshDataGrid(PagedTreeGrid pagedTreeGrid) {
		pagedTreeGrid.setColumnLazyLoading(true);
		pagedTreeGrid.initialConfig(pagedTreeGrid.getComponentIdInMap());
		pagedTreeGrid.setColumnLazyLoading(false);
	}

	public static void addDoubleHeader(List<Object> currentSingleColumns, PagedTreeGrid pagedTreeGrid) {
		HeaderRow groupingHeader = getGroupingHeader(pagedTreeGrid.getGrid());

		shiftLeftHeader(groupingHeader, pagedTreeGrid);

		Iterator<String> douleLeftHeaders = pagedTreeGrid.getTableConfig().getLeftTableDoubleHeaderVisibleHeaders()
				.iterator();
		pagedTreeGrid.getTableConfig().getLeftTableDoubleHeaderVisibleColumns().stream().distinct()
				.forEach(property -> {
					Object[] joinList = pagedTreeGrid.getTableConfig().getLeftTableDoubleHeaderMap().get(property);
					if (joinList != null) {
						String[] stringArray = Arrays.copyOf(joinList, joinList.length, String[].class);
						if (stringArray.length > 1) {
							groupingHeader.join(stringArray).setText(douleLeftHeaders.next());
						} else if (stringArray.length > 0) {
							groupingHeader.getCell(stringArray[0]).setText(douleLeftHeaders.next());
						}
					}
				});
		Iterator<String> douleHeaders = pagedTreeGrid.getTableConfig().getRightTableDoubleVisibleHeaders().iterator();
		pagedTreeGrid.getTableConfig().getRightTableDoubleHeaderVisibleColumns().stream().distinct()
				.forEach(property -> {
						Object[] joinList = pagedTreeGrid.getTableConfig().getRightTableDoubleHeaderMap().get(property);
					String[] stringArray = getSingleColumnsMapping(currentSingleColumns, joinList)
							.toArray(new String[0]);
					if (stringArray.length > 1) {
						String header = douleHeaders.next();
						if (pagedTreeGrid.getTableConfig().getAggregationColumnHeader()!=null && property.toString().contains(pagedTreeGrid.getTableConfig().getAggregationColumnHeader())) {
							groupingHeader.getCell(stringArray[stringArray.length - 1]).setText(header);
						} else {
							groupingHeader.join(stringArray).setText(header);
						}
					} else if (stringArray.length > 0) {
						groupingHeader.getCell(stringArray[0]).setText(douleHeaders.next());
				}
				});
	}

	private static HeaderRow getGroupingHeader(TreeGrid<GtnWsRecordBean> grid) {
		return grid.getHeaderRowCount() > 1 ? grid.getHeaderRow(1) : grid.prependHeaderRow();
	}

	public static void addTripleHeader(List<Object> currentSingleColumns, PagedTreeGrid pagedTreeGrid) {
		if (pagedTreeGrid.getTableConfig().isTripleHeaderVisible()) {
			HeaderRow groupingHeader = pagedTreeGrid.getGrid().prependHeaderRow();
			int j = 0;
			List<Object> singleList = new ArrayList<>();
			for (Object property : pagedTreeGrid.getTableConfig().getRightTableTripleHeaderVisibleColumns()) {
				singleList.clear();
				Object[] doubleHeaders = pagedTreeGrid.getTableConfig().getRightTableTripleHeaderMap().get(property);
				for (Object dbl : doubleHeaders) {
					if (pagedTreeGrid.getTableConfig().getRightTableDoubleHeaderMap().get(dbl) != null) {
						singleList.addAll(
								Arrays.stream(pagedTreeGrid.getTableConfig().getRightTableDoubleHeaderMap().get(dbl))
										.collect(Collectors.toList()));
					}
				}

				String[] columnList = currentSingleColumns.stream().map(String::valueOf)
						.filter(e -> arrayContains(singleList.toArray(), e)).collect(Collectors.toList())
						.toArray(new String[0]);
				if (pagedTreeGrid.getTableConfig().isEnableCheckBoxInTripleHeader()) {
					CheckBoxGroup vaadinCheckBoxGroup = new CheckBoxGroup();
					vaadinCheckBoxGroup
							.setItems(pagedTreeGrid.getTableConfig().getRightTableTripleVisibleHeaders().get(j++));
					groupingHeader.join(columnList).setComponent(vaadinCheckBoxGroup);
				} else {
					tripleHeaderGrouping(pagedTreeGrid, groupingHeader, columnList, j++);
				}
			}
		}
		if (pagedTreeGrid.getTableConfig().getCustomFilterConfigMap() != null) {
			FilterUtils.setFilterToGrid(pagedTreeGrid);
		}
		addTableHeaderCheck(pagedTreeGrid);
		pagedTreeGrid.setShiftLeftSingeHeader(false);
	}

	private static void tripleHeaderGrouping(PagedTreeGrid pagedTreeGrid, HeaderRow groupingHeader, String[] columnList,
			int j) {
		if (columnList.length > 1) {
			groupingHeader.join(columnList)
					.setText(pagedTreeGrid.getTableConfig().getRightTableTripleVisibleHeaders().get(j));
		} else if (columnList.length > 0) {
			groupingHeader.getCell(columnList[columnList.length - 1])
					.setText(pagedTreeGrid.getTableConfig().getRightTableTripleVisibleHeaders().get(j));
		}
	}

	private static List<String> getSingleColumnsMapping(List<Object> currentSingleColumns, Object[] joinList) {

		return currentSingleColumns.stream().filter(e -> arrayContains(joinList, String.valueOf(e)))
				.map(Object::toString).collect(Collectors.toList());
	}

	private static boolean arrayContains(Object[] input, String value) {
		return Arrays.stream(input)
				.filter(e -> value.contains(e.toString()) || (value.contains("Total") && e.toString().contains(value)))
				.count() > 0;
	}

	// CheckBox in DoubleColumnHeader
	private static void addTableHeaderCheck(PagedTreeGrid pagedTreeGrid) {

		HeaderRow row = pagedTreeGrid.getGrid().getHeaderRowCount() > 2 ? pagedTreeGrid.getGrid().getHeaderRow(1)
				: pagedTreeGrid.getGrid().getHeaderRow(0);
		if (pagedTreeGrid.getTableConfig().getCheckBoxVisibleColoumn() != null) {
			pagedTreeGrid.getTableConfig().getCheckBoxVisibleColoumn().stream().map(columnId -> {
				return columnId;
			}).forEach(columnId -> {
				CheckBox vaadinCheckBoxGroup = new CheckBox();
				row.getCell(columnId).setComponent(vaadinCheckBoxGroup);
			});
		}
	}

	public static void shiftLeftHeader(HeaderRow groupingHeader, PagedTreeGrid pagedTreeGrid) {
		if (pagedTreeGrid.isShiftLeftSingeHeader()) {
			for (int j = 0; j < pagedTreeGrid.getTableConfig().getLeftTableColumnMappingId().length; j++) {
				String column = (pagedTreeGrid.getTableConfig().getLeftTableColumnMappingId()[j]).toString();
				gtnlogger.info("column = " + column);
				groupingHeader.getCell(column).setText(pagedTreeGrid.getTableConfig().getColumnHeaders().get(j));

			}
		}
	}
}
