/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.abstractsearch.logic;

import com.stpl.ifs.util.CommonUtil;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.And;
import com.vaadin.v7.data.util.filter.Between;
import com.vaadin.v7.data.util.filter.Compare;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author STPLROOT
 */
public class FilterLogic {

    public final SimpleDateFormat DBDate = new SimpleDateFormat("yyyy-MM-dd");
    private static FilterLogic instance;

    private FilterLogic() {
    }

    public static synchronized FilterLogic getInstance() {
        if (instance == null) {
            instance = new FilterLogic();
        }
        return instance;
    }

    public StringBuilder filterQueryGenerator(java.util.Set<Container.Filter> containerFilterSet, Map<String, String> filterQueryMap) {
        StringBuilder strObj = new StringBuilder("AND ( * LIKE '?' OR * IS NULL )");
        StringBuilder sqlQuery = new StringBuilder();
        if (containerFilterSet != null && !containerFilterSet.isEmpty()) {
            for (Container.Filter containerFilter : containerFilterSet) {
                if (containerFilter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilterVal = (SimpleStringFilter) containerFilter;
                    if (filterQueryMap.get(stringFilterVal.getPropertyId().toString()) != null && !filterQueryMap.get(stringFilterVal.getPropertyId().toString()).isEmpty()) {
                        if (sqlQuery.length() == 0) {
                            StringBuilder initialValue = new StringBuilder("where ( ( * LIKE '?' )");
                            StringBuilder tempValue = new StringBuilder(initialValue);
                            tempValue.replace(tempValue.indexOf("*"), tempValue.indexOf("*") + 1, filterQueryMap.get(stringFilterVal.getPropertyId().toString()));
                            tempValue.replace(tempValue.indexOf("?"), tempValue.indexOf("?") + 1, CommonUtil.buildFilterCriteria(stringFilterVal.getFilterString()));
                            sqlQuery.append(tempValue);

                        } else {
                            StringBuilder tempValue = new StringBuilder(strObj);
                            tempValue.replace(tempValue.indexOf("*"), tempValue.indexOf("*") + 1, filterQueryMap.get(stringFilterVal.getPropertyId().toString()));
                            tempValue.replace(tempValue.indexOf("*"), tempValue.indexOf("*") + 1, filterQueryMap.get(stringFilterVal.getPropertyId().toString()));
                            tempValue.replace(tempValue.indexOf("?"), tempValue.indexOf("?") + 1, CommonUtil.buildFilterCriteria(stringFilterVal.getFilterString()));
                            sqlQuery.append(tempValue);
                        }
                    }
                }
                if (containerFilter instanceof Between) {
                    Between betweenFilterValue = (Between) containerFilter;
                    StringBuilder dateStartStr = new StringBuilder("AND ( * >='?')");
                    StringBuilder dateEndStr = new StringBuilder("AND ( * <='?')");
                    if (!filterQueryMap.get(betweenFilterValue.getPropertyId().toString()).isEmpty()) {
                        Date startDateValue = (Date) betweenFilterValue.getStartValue();
                        Date endDateValue = (Date) betweenFilterValue.getEndValue();
                        StringBuilder initialStartVal = new StringBuilder("where ( ( * >= '?' )");
                        StringBuilder initialEndVal = new StringBuilder("where ( ( * <= '?' )");
                        if (!betweenFilterValue.getStartValue().toString().isEmpty()) {
                            StringBuilder tempStartVal;
                            if (sqlQuery.length() == 0) {
                                tempStartVal = new StringBuilder(initialStartVal);
                            } else {
                                tempStartVal = new StringBuilder(dateStartStr);
                            }
                            tempStartVal.replace(tempStartVal.indexOf("*"), tempStartVal.indexOf("*") + 1, filterQueryMap.get(betweenFilterValue.getPropertyId().toString()));
                            tempStartVal.replace(tempStartVal.indexOf("?"), tempStartVal.indexOf("?") + 1, DBDate.format(startDateValue));
                            sqlQuery.append(tempStartVal);
                        }
                        if (!betweenFilterValue.getEndValue().toString().isEmpty()) {
                            StringBuilder tempValueEnd;
                            if (sqlQuery.length() == 0) {
                                tempValueEnd = new StringBuilder(initialEndVal);
                            } else {
                                tempValueEnd = new StringBuilder(dateEndStr);
                            }

                            tempValueEnd.replace(tempValueEnd.indexOf("*"), tempValueEnd.indexOf("*") + 1, filterQueryMap.get(betweenFilterValue.getPropertyId().toString()));
                            tempValueEnd.replace(tempValueEnd.indexOf("?"), tempValueEnd.indexOf("?") + 1, DBDate.format(endDateValue));
                            sqlQuery.append(tempValueEnd);
                        }
                    }
                } else if (containerFilter instanceof Compare) {
                    Compare stringFilterValue = (Compare) containerFilter;
                    if (!filterQueryMap.get(stringFilterValue.getPropertyId().toString()).isEmpty()) {
                        Compare.Operation operationData = stringFilterValue.getOperation();
                        if (Compare.Operation.EQUAL.toString().equals(operationData.name())) {
                            StringBuilder startStr = new StringBuilder("AND ( * ='?')");
                            StringBuilder intStartStr = new StringBuilder("where ( ( * = '?' )");
                            StringBuilder tempStartValue;
                            String tmpValue;
                            if (((Integer) stringFilterValue.getValue()) == 0) {
                                tmpValue = String.valueOf(stringFilterValue.getValue());
                            } else {
                                int val = (Integer) stringFilterValue.getValue();
                                tmpValue = String.valueOf(val);
                            }
                            if (!tmpValue.isEmpty()) {
                                if (sqlQuery.length() == 0) {
                                    tempStartValue = new StringBuilder(intStartStr);
                                } else {
                                    tempStartValue = new StringBuilder(startStr);
                                }
                                tempStartValue.replace(tempStartValue.indexOf("*"), tempStartValue.indexOf("*") + 1, filterQueryMap.get(stringFilterValue.getPropertyId().toString()));
                                tempStartValue.replace(tempStartValue.indexOf("?"), tempStartValue.indexOf("?") + 1, tmpValue);
                                sqlQuery.append(tempStartValue);
                            }
                        }
                        if (Compare.Operation.GREATER.toString().equals(operationData.name())) {
                            StringBuilder tempStartData;
                            int idVal = (Integer) stringFilterValue.getValue();
                            String strValue=String.valueOf(idVal);
                            if (idVal < 0) {
                                if (sqlQuery.length() == 0) {
                                  tempStartData = new StringBuilder("where ( ( * > '?' or * = '0')");
                                } else {
                                  tempStartData = new StringBuilder("AND ( * >'?' or * = '0')");
                                }
                                tempStartData.replace(tempStartData.indexOf("*"), tempStartData.indexOf("*") + 1, filterQueryMap.get(stringFilterValue.getPropertyId().toString()));
                                tempStartData.replace(tempStartData.indexOf("?"), tempStartData.indexOf("?") + 1, strValue);
                                sqlQuery.append(tempStartData);
                            } else {
                                if (sqlQuery.length() == 0) {
                                    tempStartData = new StringBuilder("where ( ( * > '?')");
                                } else {
                                    tempStartData = new StringBuilder("AND ( * >'?')");
                                }
                                tempStartData.replace(tempStartData.indexOf("*"), tempStartData.indexOf("*") + 1, filterQueryMap.get(stringFilterValue.getPropertyId().toString()));
                                tempStartData.replace(tempStartData.indexOf("?"), tempStartData.indexOf("?") + 1, strValue);
                                sqlQuery.append(tempStartData);
                            }
                        }
                        if (Compare.Operation.LESS.toString().equals(operationData.name())) {
                            int idValue = (Integer) stringFilterValue.getValue();
                            StringBuilder tempStartData;
                            String tmpValue=String.valueOf(idValue);
                            if (idValue > 0) {
                                if (sqlQuery.length() == 0) {
                                    tempStartData = new StringBuilder("where ( ( * < '?' or * = '0')");
                                } else {
                                    tempStartData = new StringBuilder("AND ( * <'?' or * = '0')");
                                }
                                tempStartData.replace(tempStartData.indexOf("*"), tempStartData.indexOf("*") + 1, filterQueryMap.get(stringFilterValue.getPropertyId().toString()));
                                tempStartData.replace(tempStartData.indexOf("?"), tempStartData.indexOf("?") + 1, tmpValue);
                                sqlQuery.append(tempStartData);
                            } else {
                                if (sqlQuery.length() == 0) {
                                  tempStartData = new StringBuilder("where ( ( * < '?')");
                                } else {
                                  tempStartData = new StringBuilder("AND ( * <'?')");
                                }
                                tempStartData.replace(tempStartData.indexOf("*"), tempStartData.indexOf("*") + 1, filterQueryMap.get(stringFilterValue.getPropertyId().toString()));
                                tempStartData.replace(tempStartData.indexOf("*"), tempStartData.indexOf("*") + 1, filterQueryMap.get(stringFilterValue.getPropertyId().toString()));
                                tempStartData.replace(tempStartData.indexOf("?"), tempStartData.indexOf("?") + 1, tmpValue);
                                sqlQuery.append(tempStartData);
                            }
                        }
                        if (stringFilterValue.getValue() instanceof Date) {
                            Date dateValue = (Date) stringFilterValue.getValue();
                            StringBuilder tempStartValue;
                            if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operationData.name())) {
                                if (sqlQuery.length() == 0) {
                                    tempStartValue = new StringBuilder("where ( ( * >= '?')");
                                } else {
                                    tempStartValue = new StringBuilder("AND ( * >='?')");
                                }
                            } else {
                                if (sqlQuery.length() == 0) {
                                    tempStartValue = new StringBuilder("where ( ( * <='?')");
                                } else {
                                    tempStartValue = new StringBuilder("AND ( * <='?' )");
                                }
                            }
                            tempStartValue.replace(tempStartValue.indexOf("*"), tempStartValue.indexOf("*") + 1, filterQueryMap.get(stringFilterValue.getPropertyId().toString()));
                            tempStartValue.replace(tempStartValue.indexOf("?"), tempStartValue.indexOf("?") + 1, DBDate.format(dateValue));
                            sqlQuery.append(tempStartValue);
                        }
                    }
                } else if (containerFilter instanceof And) {
                    And stringFilter = (And) containerFilter;
                    Collection<Container.Filter> filterValue = stringFilter.getFilters();
                    for (Container.Filter filters : filterValue) {
                        Object propertyId;
                        if (filters instanceof Compare.Less) {

                            Compare.Less lessCompare = (Compare.Less) filters;
                            propertyId = lessCompare.getPropertyId();

                            StringBuilder tempStartValue;
                            String lessValue = String.valueOf(lessCompare.getValue());

                            if (sqlQuery.length() == 0) {
                                tempStartValue = new StringBuilder("where ( ( * < '?')");
                            } else {
                                tempStartValue = new StringBuilder("AND ( * <'?')");
                            }
                            tempStartValue.replace(tempStartValue.indexOf("*"), tempStartValue.indexOf("*") + 1, filterQueryMap.get(String.valueOf(propertyId)));
                            tempStartValue.replace(tempStartValue.indexOf("?"), tempStartValue.indexOf("?") + 1, lessValue);
                            sqlQuery.append(tempStartValue);

                        }
                        if (filters instanceof Compare.Greater) {
                            Compare.Greater greaterCompare = (Compare.Greater) filters;
                            propertyId = greaterCompare.getPropertyId();

                            StringBuilder tempStartValue;
                            String greaterValue = String.valueOf(greaterCompare.getValue());

                            if (sqlQuery.length() == 0) {
                                tempStartValue = new StringBuilder("where ( ( * > '?')");
                            } else {
                                tempStartValue = new StringBuilder("AND ( * >'?')");
                            }
                            tempStartValue.replace(tempStartValue.indexOf("*"), tempStartValue.indexOf("*") + 1, filterQueryMap.get(String.valueOf(propertyId)));
                            tempStartValue.replace(tempStartValue.indexOf("?"), tempStartValue.indexOf("?") + 1, greaterValue);
                            sqlQuery.append(tempStartValue);
                        }
                    }
                }
            }
            if (sqlQuery.length() != 0) {
                sqlQuery.append(')');
            }
        }
        return sqlQuery;
    }

    public StringBuilder orderByQueryGenerator(List<SortByColumn> sortByColumns, Map<String, String> queryMap) {
        boolean isAsc = false;
        StringBuilder tempStartValue = new StringBuilder("ORDER BY * ?");
        if (sortByColumns != null && !sortByColumns.isEmpty()) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                String columnName = sortByColumn.getName();
                if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                    isAsc = false;
                    tempStartValue.replace(tempStartValue.indexOf("*"), tempStartValue.indexOf("*") + 1, queryMap.get(columnName));
                } else {
                    isAsc = true;
                    tempStartValue.replace(tempStartValue.indexOf("*"), tempStartValue.indexOf("*") + 1, queryMap.get(columnName));
                }
            }
        } else {
            for (Map.Entry<String, String> entrySet : queryMap.entrySet()) {
                final String key = entrySet.getKey();
                if (key.contains("sid")) {
                    String value = queryMap.get(key);
                    tempStartValue.replace(tempStartValue.indexOf("*"), tempStartValue.indexOf("*") + 1, value);
                }
            }
        }
        tempStartValue.replace(tempStartValue.indexOf("?"), tempStartValue.indexOf("?") + 1, !isAsc ? "ASC" : "DESC");
        return tempStartValue;
    }

    /**
     * method for filter using normal Collections
     *
     * @param containerfilterSet
     * @return
     */
    public void filterQueryForContainer(java.util.Set<Container.Filter> containerfilterSet, Container.Filterable container) {
        container.removeAllContainerFilters();
        if (containerfilterSet != null && !containerfilterSet.isEmpty()) {
            for (Container.Filter filter : containerfilterSet) {
                container.addContainerFilter(filter);
}
        }
    }

    /**
     * Method to sort the collections from the container.
     *
     * @param sortByColumns
     * @param orderByContainer
     */
    public void orderByQueryForContainer(List<SortByColumn> sortByColumns, Container.Sortable orderByContainer) {
        if (sortByColumns != null && !sortByColumns.isEmpty()) {
            Object[] propertyIds = new Object[sortByColumns.size()];
            boolean[] sortByvalue = new boolean[sortByColumns.size()];
            int i = 0;
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                String columnName = sortByColumn.getName();
                propertyIds[i] = columnName;
                if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                    sortByvalue[i] = true;
                } else {
                    sortByvalue[i] = false;
                }
            }
            orderByContainer.sort(propertyIds, sortByvalue);
        }
    }
}
