/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.common.util;

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
 * @author mohamed.hameed
 */
public class AbstractFilterLogic {

    public final SimpleDateFormat dbDate = new SimpleDateFormat("yyyy-MM-dd");
    private static AbstractFilterLogic adminInstance;

    private AbstractFilterLogic() {
    }

    public static synchronized AbstractFilterLogic getAdminInstance() {
        if (adminInstance == null) {
            adminInstance = new AbstractFilterLogic();
        }
        return adminInstance;
    }

    public StringBuilder filterQueryGenerator(java.util.Set<Container.Filter> filterSet, Map<String, String> queryMap) {
        StringBuilder queryStr = new StringBuilder("AND ( * LIKE '?' OR * IS NULL )");
        StringBuilder sqlQuery = new StringBuilder();
        if (filterSet!= null && !filterSet.isEmpty()) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    if (queryMap.get(stringFilter.getPropertyId().toString()) != null && !queryMap.get(stringFilter.getPropertyId().toString()).isEmpty()) {
                        if (sqlQuery.length() == 0) {
                            StringBuilder initial = new StringBuilder("where ( ( * LIKE '?' )");
                            StringBuilder temp = new StringBuilder(initial);
                            temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                            if (stringFilter.getFilterString() == null || "null".equals(stringFilter.getFilterString())) {
                                temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, "%");
                            } else {
                                temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, "%".concat(stringFilter.getFilterString()).concat("%"));
                            }
                            sqlQuery.append(temp);

                        } else {
                            StringBuilder temp = new StringBuilder(queryStr);
                            temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                            temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                            temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, "%".concat(stringFilter.getFilterString()).concat("%"));
                            sqlQuery.append(temp);
                        }
                    }
                }
                if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                    StringBuilder dateStartStr = new StringBuilder("AND ( * >='?')");
                    StringBuilder dateEndStr = new StringBuilder("AND ( * <='?')");
                    if (!queryMap.get(betweenFilter.getPropertyId().toString()).isEmpty()) {
                        Date startDate = (Date) betweenFilter.getStartValue();
                        Date endDate = (Date) betweenFilter.getEndValue();
                        StringBuilder initialStart = new StringBuilder("where ( ( * >= '?' )");
                        StringBuilder initialEnd = new StringBuilder("where ( ( * <= '?' )");
                        if (!betweenFilter.getStartValue().toString().isEmpty()) {
                            StringBuilder tempStart;
                            if (sqlQuery.length() == 0) {
                                tempStart = new StringBuilder(initialStart);
                            } else {
                                tempStart = new StringBuilder(dateStartStr);
                            }
                            tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(betweenFilter.getPropertyId().toString()));
                            tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, dbDate.format(startDate));
                            sqlQuery.append(tempStart);
                        }
                        if (!betweenFilter.getEndValue().toString().isEmpty()) {
                            StringBuilder tempEnd;
                            if (sqlQuery.length() == 0) {
                                tempEnd = new StringBuilder(initialEnd);
                            } else {
                                tempEnd = new StringBuilder(dateEndStr);
                            }

                            tempEnd.replace(tempEnd.indexOf("*"), tempEnd.indexOf("*") + 1, queryMap.get(betweenFilter.getPropertyId().toString()));
                            tempEnd.replace(tempEnd.indexOf("?"), tempEnd.indexOf("?") + 1, dbDate.format(endDate));
                            sqlQuery.append(tempEnd);
                        }
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    if (!queryMap.get(stringFilter.getPropertyId().toString()).isEmpty()) {
                        Compare.Operation operation = stringFilter.getOperation();
                        if (operation.EQUAL.toString().equals(operation.name())) {
                            StringBuilder startstr = new StringBuilder("AND ( * ='?')");
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
                                if (sqlQuery.length() == 0) {
                                    tempStart = new StringBuilder(intStartstr);
                                } else {
                                    tempStart = new StringBuilder(startstr);
                                }
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                                sqlQuery.append(tempStart);
                            }
                        }
                        if (operation.GREATER.toString().equals(operation.name())) {
                           
                            StringBuilder tempStart;
                            int val = (Integer) stringFilter.getValue();
                            String value=String.valueOf(val);
                            if (val < 0) {
                                if (sqlQuery.length() == 0) {
                                  tempStart = new StringBuilder("where ( ( * > '?' or * = '0')");
                                } else {
                                  tempStart = new StringBuilder("AND ( * >'?' or * = '0')");
                                }
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                                sqlQuery.append(tempStart);
                            } else {
                                if (sqlQuery.length() == 0) {
                                    tempStart = new StringBuilder("where ( ( * > '?')");
                                } else {
                                    tempStart = new StringBuilder("AND ( * >'?')");
                                }
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                                sqlQuery.append(tempStart);
                            }
                        }
                        if (operation.LESS.toString().equals(operation.name())) {
                            int val = (Integer) stringFilter.getValue();
                            StringBuilder tempStart;
                            String value=String.valueOf(val);
                            if (val > 0) {
                                if (sqlQuery.length() == 0) {
                                    tempStart = new StringBuilder("where ( ( * < '?' or * = '0')");
                                } else {
                                    tempStart = new StringBuilder("AND ( * <'?' or * = '0')");
                                }
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                                sqlQuery.append(tempStart);
                            } else {
                                if (sqlQuery.length() == 0) {
                                  tempStart = new StringBuilder("where ( ( * < '?')");
                                } else {
                                  tempStart = new StringBuilder("AND ( * <'?')");
                                }
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                                sqlQuery.append(tempStart);
                            }
                        }
                        if (stringFilter.getValue() instanceof Date) {
                            Date value = (Date) stringFilter.getValue();
                            StringBuilder tempStart;
                            if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                                if (sqlQuery.length() == 0) {
                                    tempStart = new StringBuilder("where ( ( * >= '?')");
                                } else {
                                    tempStart = new StringBuilder("AND ( * >='?')");
                                }
                            } else {
                                if (sqlQuery.length() == 0) {
                                    tempStart = new StringBuilder("where ( ( * <='?')");
                                } else {
                                    tempStart = new StringBuilder("AND ( * <='?' )");
                                }
                            }
                            tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                            tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, dbDate.format(value));
                            sqlQuery.append(tempStart);
                        }
                    }
                } else if (filter instanceof And) {
                    
                    And stringFilter = (And) filter;
                    Collection<Container.Filter> value = stringFilter.getFilters();
                    for (Container.Filter filter1 : value) {
                        Object propertyId = "";
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            propertyId = less.getPropertyId();

                            StringBuilder tempStart;
                            String lessValue = String.valueOf(less.getValue());

                            if (sqlQuery.length() == 0) {
                                tempStart = new StringBuilder("where ( ( * < '?')");
                            } else {
                                tempStart = new StringBuilder("AND ( * <'?')");
                            }
                            tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(String.valueOf(propertyId)));
                            tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, lessValue);
                            sqlQuery.append(tempStart);

                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            propertyId = greater.getPropertyId();

                            StringBuilder tempStart;
                            String greaterValue = String.valueOf(greater.getValue());

                            if (sqlQuery.length() == 0) {
                                tempStart = new StringBuilder("where ( ( * > '?')");
                            } else {
                                tempStart = new StringBuilder("AND ( * >'?')");
                            }
                            tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(String.valueOf(propertyId)));
                            tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, greaterValue);
                            sqlQuery.append(tempStart);
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

    public StringBuilder orderByQueryGenerator(List<SortByColumn> sortByColumns, Map<String, String> queryMap, String screenName) {
        boolean asc = false;
        StringBuilder orderByQuery = new StringBuilder("ORDER BY * ?");
        if (sortByColumns != null && !sortByColumns.isEmpty()) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                String columnName = sortByColumn.getName();
                if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                    asc = false;
                    orderByQuery.replace(orderByQuery.indexOf("*"), orderByQuery.indexOf("*") + 1, queryMap.get(columnName));
                } else {
                    asc = true;
                    orderByQuery.replace(orderByQuery.indexOf("*"), orderByQuery.indexOf("*") + 1, queryMap.get(columnName));
                }
            }
        } else {
            for (Map.Entry<String, String> entry : queryMap.entrySet()) {
                final String string1 = entry.getValue();
                if (screenName.equals("CFF_OUTBOUND") && string1.contains("FINANCIAL_FORECAST_ID")) {
                    orderByQuery.replace(orderByQuery.indexOf("*"), orderByQuery.indexOf("*") + 1, string1 + ",PROJECT_ID,YEAR,MONTH");
                } else if (string1.equals("CM.COMPANY_MASTER_SID")) {
                    orderByQuery.replace(orderByQuery.indexOf("*"), orderByQuery.indexOf("*") + 1, string1);
                }
            }
        }
        orderByQuery.replace(orderByQuery.indexOf("?"), orderByQuery.indexOf("?") + 1, !asc ? "ASC" : "DESC");
        return orderByQuery;
    }
}
