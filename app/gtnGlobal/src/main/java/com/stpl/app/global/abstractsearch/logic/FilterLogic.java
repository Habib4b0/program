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

    public static final SimpleDateFormat DBDate = new SimpleDateFormat("yyyy-MM-dd");
    private static FilterLogic instance;

    private FilterLogic() {
    }

    public static synchronized FilterLogic getInstance() {
        if (instance == null) {
            instance = new FilterLogic();
        }
        return instance;
    }

    public StringBuilder filterQueryGenerator(java.util.Set<Container.Filter> filterSet, Map<String, String> queryMap) {
        StringBuilder str = new StringBuilder("AND ( * LIKE '?' OR * IS NULL )");
        StringBuilder sql = new StringBuilder();
        if (filterSet != null && !filterSet.isEmpty()) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    if (queryMap.get(stringFilter.getPropertyId().toString()) != null && !queryMap.get(stringFilter.getPropertyId().toString()).isEmpty()) {
                        if (sql.length() == 0) {
                            StringBuilder initial = new StringBuilder("where ( ( * LIKE '?' )");
                            StringBuilder temp = new StringBuilder(initial);
                            temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                            temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, CommonUtil.buildFilterCriteria(stringFilter.getFilterString()));
                            sql.append(temp);

                        } else {
                            StringBuilder temp = new StringBuilder(str);
                            temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                            temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                            temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, CommonUtil.buildFilterCriteria(stringFilter.getFilterString()));
                            sql.append(temp);
                        }
                    }
                }
                if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                    StringBuilder dateStartstr = new StringBuilder("AND ( * >='?')");
                    StringBuilder dateEndstr = new StringBuilder("AND ( * <='?')");
                    if (!queryMap.get(betweenFilter.getPropertyId().toString()).isEmpty()) {
                        Date startValue = (Date) betweenFilter.getStartValue();
                        Date endValue = (Date) betweenFilter.getEndValue();
                        StringBuilder initialStart = new StringBuilder("where ( ( * >= '?' )");
                        StringBuilder initialEnd = new StringBuilder("where ( ( * <= '?' )");
                        if (!betweenFilter.getStartValue().toString().isEmpty()) {
                            StringBuilder tempStart;
                            if (sql.length() == 0) {
                                tempStart = new StringBuilder(initialStart);
                            } else {
                                tempStart = new StringBuilder(dateStartstr);
                            }
                            tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(betweenFilter.getPropertyId().toString()));
                            tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, DBDate.format(startValue));
                            sql.append(tempStart);
                        }
                        if (!betweenFilter.getEndValue().toString().isEmpty()) {
                            StringBuilder tempEnd;
                            if (sql.length() == 0) {
                                tempEnd = new StringBuilder(initialEnd);
                            } else {
                                tempEnd = new StringBuilder(dateEndstr);
                            }

                            tempEnd.replace(tempEnd.indexOf("*"), tempEnd.indexOf("*") + 1, queryMap.get(betweenFilter.getPropertyId().toString()));
                            tempEnd.replace(tempEnd.indexOf("?"), tempEnd.indexOf("?") + 1, DBDate.format(endValue));
                            sql.append(tempEnd);
                        }
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    if (!queryMap.get(stringFilter.getPropertyId().toString()).isEmpty()) {
                        Compare.Operation operation = stringFilter.getOperation();
                        if (Compare.Operation.EQUAL.toString().equals(operation.name())) {
                            StringBuilder Startstr = new StringBuilder("AND ( * ='?')");
                            StringBuilder intStartstr = new StringBuilder("where ( ( * = '?' )");
                            StringBuilder tempStart;
                            String value;
                            if (((Integer) stringFilter.getValue()) == 0) {
                                value = String.valueOf(stringFilter.getValue());
                            } else {
                                int val = (Integer) stringFilter.getValue();
                                value = String.valueOf(val);
                            }
                            if (!value.isEmpty()) {
                                if (sql.length() == 0) {
                                    tempStart = new StringBuilder(intStartstr);
                                } else {
                                    tempStart = new StringBuilder(Startstr);
                                }
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                                sql.append(tempStart);
                            }
                        }
                        if (Compare.Operation.GREATER.toString().equals(operation.name())) {
                            StringBuilder tempStart;
                            int val = (Integer) stringFilter.getValue();
                            String value=String.valueOf(val);
                            if (val < 0) {
                                if (sql.length() == 0) {
                                  tempStart = new StringBuilder("where ( ( * > '?' or * = '0')");
                                } else {
                                  tempStart = new StringBuilder("AND ( * >'?' or * = '0')");
                                }
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                                sql.append(tempStart);
                            } else {
                                if (sql.length() == 0) {
                                    tempStart = new StringBuilder("where ( ( * > '?')");
                                } else {
                                    tempStart = new StringBuilder("AND ( * >'?')");
                                }
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                                sql.append(tempStart);
                            }
                        }
                        if (Compare.Operation.LESS.toString().equals(operation.name())) {
                            int val = (Integer) stringFilter.getValue();
                            StringBuilder tempStart;
                            String value=String.valueOf(val);
                            if (val > 0) {
                                if (sql.length() == 0) {
                                    tempStart = new StringBuilder("where ( ( * < '?' or * = '0')");
                                } else {
                                    tempStart = new StringBuilder("AND ( * <'?' or * = '0')");
                                }
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                                sql.append(tempStart);
                            } else {
                                if (sql.length() == 0) {
                                  tempStart = new StringBuilder("where ( ( * < '?')");
                                } else {
                                  tempStart = new StringBuilder("AND ( * <'?')");
                                }
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                                sql.append(tempStart);
                            }
                        }
                        if (stringFilter.getValue() instanceof Date) {
                            Date value = (Date) stringFilter.getValue();
                            StringBuilder tempStart;
                            if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                                if (sql.length() == 0) {
                                    tempStart = new StringBuilder("where ( ( * >= '?')");
                                } else {
                                    tempStart = new StringBuilder("AND ( * >='?')");
                                }
                            } else {
                                if (sql.length() == 0) {
                                    tempStart = new StringBuilder("where ( ( * <='?')");
                                } else {
                                    tempStart = new StringBuilder("AND ( * <='?' )");
                                }
                            }
                            tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                            tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, DBDate.format(value));
                            sql.append(tempStart);
                        }
                    }
                } else if (filter instanceof And) {
                    And stringFilter = (And) filter;
                    Collection<Container.Filter> value = stringFilter.getFilters();
                    for (Container.Filter filter1 : value) {
                        Object propertyId;
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            propertyId = less.getPropertyId();

                            StringBuilder tempStart;
                            String lessValue = String.valueOf(less.getValue());

                            if (sql.length() == 0) {
                                tempStart = new StringBuilder("where ( ( * < '?')");
                            } else {
                                tempStart = new StringBuilder("AND ( * <'?')");
                            }
                            tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(String.valueOf(propertyId)));
                            tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, lessValue);
                            sql.append(tempStart);

                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            propertyId = greater.getPropertyId();

                            StringBuilder tempStart;
                            String greaterValue = String.valueOf(greater.getValue());

                            if (sql.length() == 0) {
                                tempStart = new StringBuilder("where ( ( * > '?')");
                            } else {
                                tempStart = new StringBuilder("AND ( * >'?')");
                            }
                            tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(String.valueOf(propertyId)));
                            tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, greaterValue);
                            sql.append(tempStart);
                        }
                    }
                }
            }
            if (sql.length() != 0) {
                sql.append(')');
            }
        }
        return sql;
    }

    public StringBuilder orderByQueryGenerator(List<SortByColumn> sortByColumns, Map<String, String> queryMap) {
        boolean asc = false;
        StringBuilder tempStart = new StringBuilder("ORDER BY * ?");
        if (sortByColumns != null && !sortByColumns.isEmpty()) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                String columnName = sortByColumn.getName();
                if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                    asc = false;
                    tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(columnName));
                } else {
                    asc = true;
                    tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(columnName));
                }
            }
        } else {
            for (Map.Entry<String, String> entry : queryMap.entrySet()) {
                final String key = entry.getKey();
                if (key.contains("sid")) {
                    String value = queryMap.get(key);
                    tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, value);
                }
            }
        }
        tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, !asc ? "ASC" : "DESC");
        return tempStart;
    }

    /**
     * method for filter using normal Collections
     *
     * @param filterSet
     * @return
     */
    public void filterQueryForContainer(java.util.Set<Container.Filter> filterSet, Container.Filterable container) {
        container.removeAllContainerFilters();
        if (filterSet != null && !filterSet.isEmpty()) {
            for (Container.Filter filter : filterSet) {
                container.addContainerFilter(filter);
}
        }
    }

    /**
     * Method to sort the collections from the container.
     *
     * @param sortByColumns
     * @param container
     */
    public void orderByQueryForContainer(List<SortByColumn> sortByColumns, Container.Sortable container) {
        if (sortByColumns != null && !sortByColumns.isEmpty()) {
            Object[] propIds = new Object[sortByColumns.size()];
            boolean[] value = new boolean[sortByColumns.size()];
            int i = 0;
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                String columnName = sortByColumn.getName();
                propIds[i] = columnName;
                if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                    value[i] = true;
                } else {
                    value[i] = false;
                }
            }
            container.sort(propIds, value);
        }
    }
}
