/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.logic;

import com.stpl.app.gcm.util.Constants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.Between;
import com.vaadin.v7.data.util.filter.Compare;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author mohamed.hameed
 */
public class GcmtFilterLogic {

    public final SimpleDateFormat dbDate = new SimpleDateFormat(Constants.DBDATE_FORMAT);
    private static GcmtFilterLogic instance;

    private GcmtFilterLogic() {

    }

    public static synchronized GcmtFilterLogic getInstance() {
        if (instance == null) {
            instance = new GcmtFilterLogic();
        }
        return instance;
    }

    public StringBuilder filterQueryGenerator(java.util.Set<Container.Filter> filterSetGcmFilter, Map<String, String> queryMapGcm) {
        StringBuilder strFilterGen = new StringBuilder("AND ( * LIKE '?' OR * IS NULL )");
        StringBuilder sqlFilterGenerator = new StringBuilder();
        if (!filterSetGcmFilter.isEmpty()) {
            for (Container.Filter filterGcm : filterSetGcmFilter) {
                if (filterGcm instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filterGcm;
                    if (queryMapGcm.get(stringFilter.getPropertyId().toString()) != null && !queryMapGcm.get(stringFilter.getPropertyId().toString()).isEmpty()) {
                        if (sqlFilterGenerator.length() == 0) {
                            StringBuilder initial = new StringBuilder("where ( ( * LIKE '?' )");
                            StringBuilder temp = new StringBuilder(initial);
                            temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMapGcm.get(stringFilter.getPropertyId().toString()));
                            temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, "%".concat(stringFilter.getFilterString()).concat("%"));
                            sqlFilterGenerator.append(temp);

                        } else {
                            StringBuilder tempGcm = new StringBuilder(strFilterGen);
                            tempGcm.replace(tempGcm.indexOf("*"), tempGcm.indexOf("*") + 1, queryMapGcm.get(stringFilter.getPropertyId().toString()));
                            tempGcm.replace(tempGcm.indexOf("*"), tempGcm.indexOf("*") + 1, queryMapGcm.get(stringFilter.getPropertyId().toString()));
                            tempGcm.replace(tempGcm.indexOf("?"), tempGcm.indexOf("?") + 1, "%".concat(stringFilter.getFilterString()).concat("%"));
                            sqlFilterGenerator.append(tempGcm);
                        }
                    }
                }
                if (filterGcm instanceof Between) {
                    betweenOperator(filterGcm, queryMapGcm, sqlFilterGenerator);
                } else if (filterGcm instanceof Compare) {
                    Compare stringFilter = (Compare) filterGcm;
                    if (!queryMapGcm.get(stringFilter.getPropertyId().toString()).isEmpty()) {
                        Compare.Operation operation = stringFilter.getOperation();
                        equalOperator(operation, stringFilter, sqlFilterGenerator, queryMapGcm);
                        greaterlOperator(operation, stringFilter, sqlFilterGenerator, queryMapGcm);
                        lessOperator(operation, stringFilter, sqlFilterGenerator, queryMapGcm);
                        dateOperator(stringFilter, operation, sqlFilterGenerator, queryMapGcm);
                    }
                }
            }
            sqlFilterGenerator.append(')');
        }
        return sqlFilterGenerator;
    }

    public void betweenOperator(Container.Filter filter, Map<String, String> queryMap, StringBuilder sql) {
        Between betweenFilter = (Between) filter;
        StringBuilder dateStartstr = new StringBuilder("AND ( * >='?')");
        StringBuilder dateEndstr = new StringBuilder("AND ( * <='?')");
        if (!queryMap.get(betweenFilter.getPropertyId().toString()).isEmpty()) {
            Date startValue = (Date) betweenFilter.getStartValue();
            Date endValue = (Date) betweenFilter.getEndValue();
            StringBuilder initialStart = new StringBuilder("where ( ( * >= '?' )");
            StringBuilder initialEnd = new StringBuilder("where ( ( * <= '?' )");
            startValueBetweenFilter(betweenFilter, sql, initialStart, dateStartstr, queryMap, startValue);
            endValueBetweenFilter(betweenFilter, sql, initialEnd, dateEndstr, queryMap, endValue);
        }
    }

    public void endValueBetweenFilter(Between betweenFilter, StringBuilder sql, StringBuilder initialEnd, StringBuilder dateEndstr, Map<String, String> queryMap, Date endValue) {
        if (!betweenFilter.getEndValue().toString().isEmpty()) {
            StringBuilder tempEnd;
            if (sql.length() == 0) {
                tempEnd = new StringBuilder(initialEnd);
            } else {
                tempEnd = new StringBuilder(dateEndstr);
            }
            
            tempEnd.replace(tempEnd.indexOf("*"), tempEnd.indexOf("*") + 1, queryMap.get(betweenFilter.getPropertyId().toString()));
            tempEnd.replace(tempEnd.indexOf("?"), tempEnd.indexOf("?") + 1, dbDate.format(endValue));
            sql.append(tempEnd);
        }
    }

    public void startValueBetweenFilter(Between betweenFilter, StringBuilder sql, StringBuilder initialStart, StringBuilder dateStartstr, Map<String, String> queryMap, Date startValue) {
        if (!betweenFilter.getStartValue().toString().isEmpty()) {
            StringBuilder tempStart;
            if (sql.length() == 0) {
                tempStart = new StringBuilder(initialStart);
            } else {
                tempStart = new StringBuilder(dateStartstr);
            }
            tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(betweenFilter.getPropertyId().toString()));
            tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, dbDate.format(startValue));
            sql.append(tempStart);
        }
    }

    public void dateOperator(Compare stringFilterGcm, Compare.Operation operation, StringBuilder sqlGcm, Map<String, String> queryMap) {
        if (stringFilterGcm.getValue() instanceof Date) {
            Date valueGcm = (Date) stringFilterGcm.getValue();
            StringBuilder tempStartGcm;
            if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                if (sqlGcm.length() == 0) {
                    tempStartGcm = new StringBuilder("where ( ( * >= '?')");
                } else {
                    tempStartGcm = new StringBuilder("AND ( * >='?')");
                }
            } else if (sqlGcm.length() == 0) {
                tempStartGcm = new StringBuilder("where ( ( * <='?')");
            } else {
                tempStartGcm = new StringBuilder("AND ( * <='?' )");
            }
            tempStartGcm.replace(tempStartGcm.indexOf("*"), tempStartGcm.indexOf("*") + 1, queryMap.get(stringFilterGcm.getPropertyId().toString()));
            tempStartGcm.replace(tempStartGcm.indexOf("?"), tempStartGcm.indexOf("?") + 1, dbDate.format(valueGcm));
            sqlGcm.append(tempStartGcm);
        }
    }

    public void lessOperator(Compare.Operation operation, Compare stringFilter, StringBuilder sql, Map<String, String> queryMap) {
        if (Compare.Operation.LESS.toString().equals(operation.name())) {
            int val = (Integer) stringFilter.getValue();
            StringBuilder tempStart;
            String value = StringUtils.EMPTY;
            if (val > 0) {
                if (sql.length() == 0) {
                    tempStart = new StringBuilder("AND ( * <'?' or * = '0')");
                } else {
                    tempStart = new StringBuilder("where ( ( * < '?' or * = '0')");
                }
                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                sql.append(tempStart);
            } else {
                if (sql.length() == 0) {
                    tempStart = new StringBuilder("AND ( * <'?')");
                } else {
                    tempStart = new StringBuilder("where ( ( * < '?')");
                }
                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                sql.append(tempStart);
            }
        }
    }

    public void greaterlOperator(Compare.Operation operation, Compare stringFilter, StringBuilder sql, Map<String, String> queryMap) {
        if (Compare.Operation.GREATER.toString().equals(operation.name())) {
            StringBuilder tempStart;
            String value = StringUtils.EMPTY;
            int val = (Integer) stringFilter.getValue();
            if (val < 0) {
                if (sql.length() == 0) {
                    tempStart = new StringBuilder("AND ( * >'?' or * = '0')");
                } else {
                    tempStart = new StringBuilder("where ( ( * > '?' or * = '0')");
                }
                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                sql.append(tempStart);
            } else {
                if (sql.length() == 0) {
                    tempStart = new StringBuilder("AND ( * >'?')");
                } else {
                    tempStart = new StringBuilder("where ( ( * > '?')");
                }
                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                sql.append(tempStart);
            }
        }
    }

    public void equalOperator(Compare.Operation operation, Compare stringFilter, StringBuilder sql, Map<String, String> queryMap) {
        if (Compare.Operation.EQUAL.toString().equals(operation.name())) {
            StringBuilder startStr = new StringBuilder("AND ( * ='?')");
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
                    tempStart = new StringBuilder(startStr);
                }
                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                sql.append(tempStart);
            }
        }
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
                final String string1 = entry.getValue();
                if (string1.contains("SID")) {
                    tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, string1);
                }
            }
        }
        tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, !asc ? "ASC" : "DESC");
        return tempStart;
    }
}
