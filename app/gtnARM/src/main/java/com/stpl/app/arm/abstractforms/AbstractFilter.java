/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.abstractforms;

import com.stpl.app.arm.common.CommonFilterLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.Between;
import com.vaadin.v7.data.util.filter.Compare;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Porchelvi.G
 */
public class AbstractFilter {

    private final Map<String, String> queryMap = new HashMap<>();
    private final Map<String, String> filterCustomerMap = new HashMap<>();
    private final Map<String, String> filterReserve = new HashMap<>();
    private static AbstractFilter instance;
    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractFilter.class);

    private AbstractFilter() {
        setFilterMap();
        setReserveMap();
    }

    public static synchronized AbstractFilter getInstance() {
        if (instance == null) {
            instance = new AbstractFilter();
        }
        return instance;
    }

    private void setFilterMap() {
        filterReserve.put("customerGroupName", "CG.company_Group_Name");
        filterReserve.put("customerGroupNo", "CG.company_Group_No");
    }

    private void setReserveMap() {
        filterCustomerMap.put("companyNo", "cm.COMPANY_NO");
        filterCustomerMap.put("companyName", "cm.COMPANY_NAME");
        filterCustomerMap.put("businessUnitNo", "bu.COMPANY_NO");
        filterCustomerMap.put("businessUnitName", "bu.COMPANY_NAME");
        filterCustomerMap.put("deductionCategory", "rs_c.DESCRIPTION");
        filterCustomerMap.put("deductionType", "rs_t.DESCRIPTION");
        filterCustomerMap.put("deductionProgram", "RS_P.DESCRIPTION");
        filterCustomerMap.put("createdBy", "(crt.lastName+' '+crt.firstName)");
        filterCustomerMap.put("createdDate", "CONVERT(VARCHAR(10), adj.CREATED_DATE, 101)");
        filterCustomerMap.put("modifiedDate", "CONVERT(VARCHAR(10), adj.MODIFIED_DATE, 101)");
        filterCustomerMap.put("source", "adj.SOURCE");

    }

    public StringBuilder getFilterCustomerLookUp(java.util.Set<Container.Filter> filterSet) {
        queryMap.clear();
        queryMap.putAll(filterCustomerMap);
        StringBuilder sql = filterQueryGenerator(filterSet);
        if (sql != null && !sql.toString().equals(StringUtils.EMPTY)) {
            sql = sql.replace(0, NumericConstants.FIVE, "AND");
        }
        return sql;
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
                final SortByColumn sortByColumn = iterator.next();
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

    public StringBuilder getFilterForReserve(java.util.Set<Container.Filter> filterSet) {
        queryMap.clear();
        queryMap.putAll(filterReserve);
        StringBuilder sql = filterQueryGenerator(filterSet);
        if (sql != null && !sql.toString().equals(StringUtils.EMPTY)) {
            sql = sql.replace(0, NumericConstants.FIVE, "AND");
        }
        return sql;
    }

    public StringBuilder getFilterForReserveEdit(java.util.Set<Container.Filter> filterSet) {
        queryMap.clear();
        queryMap.putAll(ARMUtils.getVisibleToDBColumnMap());
        StringBuilder sql = filterQueryGenerator(filterSet);
        if (sql != null && !sql.toString().equals(StringUtils.EMPTY)) {
            sql = sql.replace(0, NumericConstants.FIVE, "AND");
        }
        return sql;
    }

    public StringBuilder getFilterForAdjustMentConfig(java.util.Set<Container.Filter> filterSet) {
        queryMap.clear();
        queryMap.putAll(ARMUtils.getVisibleToDBColumnMapForConfig());
        StringBuilder sql = filterQueryGenerator(filterSet);
        if (sql != null && !sql.toString().equals(StringUtils.EMPTY)) {
            sql = sql.replace(0, NumericConstants.FIVE, "AND");
        }
        return sql;
    }

    public StringBuilder filterQueryGenerator(java.util.Set<Container.Filter> filterSet) {

        String str = "AND ( * LIKE '?' OR * IS NULL )";
        StringBuilder sql = new StringBuilder();
        try {
            if (!filterSet.isEmpty()) {
                for (Container.Filter filter : filterSet) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        if (queryMap.get(stringFilter.getPropertyId().toString()) != null && !queryMap.get(stringFilter.getPropertyId().toString()).isEmpty() && !stringFilter.getFilterString().isEmpty()) {
                            if (sql.length() == 0) {
                                String initial;
                                initial = "where ( ( * LIKE '?' )";
                                StringBuilder temp = new StringBuilder(initial);
                                String filterStr;
                                if (stringFilter.getFilterString().contains("%")) {
                                    filterStr = "[" + stringFilter.getFilterString() + "]";
                                } else {
                                    filterStr = stringFilter.getFilterString();
                                }
                                String filterString;
                                if (ARMUtils.ADJUSTMENT_CONFIG_CONSTANTS.METHODOLOGY.getPropertyId().equals(stringFilter.getPropertyId().toString())
                                        && "0".equals(filterStr)) {
                                    filterString = "%";
                                } else {
                                    filterString = "%" + filterStr + "%";
                                }
                                temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, filterString);
                                sql.append(temp);
                            } else {
                                String filterStr;
                                if (stringFilter.getFilterString().contains("%")) {
                                    filterStr = "[" + stringFilter.getFilterString() + "]";
                                } else {
                                    filterStr = stringFilter.getFilterString();
                                }
                                String filterString;
                                if (ARMUtils.ADJUSTMENT_CONFIG_CONSTANTS.METHODOLOGY.getPropertyId().equals(stringFilter.getPropertyId().toString())
                                        && "0".equals(filterStr)) {
                                    filterString = "%";
                                } else {
                                    filterString = "%" + filterStr + "%";
                                }
                                StringBuilder temp = new StringBuilder(str);
                                temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, filterString);
                                sql.append(temp);
                            }

                        }
                    }
                    if (filter instanceof Between) {
                        Between betweenFilter = (Between) filter;
                        String dateStartstr = "AND ( * >='?')";
                        String dateEndstr = "AND ( * <='?')";
                        if (!queryMap.get(betweenFilter.getPropertyId().toString()).isEmpty()) {
                            Date startValue = (Date) betweenFilter.getStartValue();
                            Date endValue = (Date) betweenFilter.getEndValue();
                            String initialStart = "where ( ( * >= '?' )";
                            String initialEnd = "where ( ( * <= '?' )";
                            if (!betweenFilter.getStartValue().toString().isEmpty()) {
                                StringBuilder tempStart;
                                if (sql.length() == 0) {
                                    tempStart = new StringBuilder(initialStart);
                                } else {
                                    tempStart = new StringBuilder(dateStartstr);
                                }
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(betweenFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, ARMUtils.getInstance().getDbDate().format(startValue));
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
                                tempEnd.replace(tempEnd.indexOf("?"), tempEnd.indexOf("?") + 1, ARMUtils.getInstance().getDbDate().format(endValue));
                                sql.append(tempEnd);
                            }
                        }
                    } else if (filter instanceof Compare) {

                        Compare stringFilter = (Compare) filter;
                        Compare.Operation operation = stringFilter.getOperation();
                        if ((!queryMap.get(stringFilter.getPropertyId().toString()).isEmpty()) && (stringFilter.getValue() instanceof Date)) {
                            String filterString = ARMUtils.getInstance().getDbDate().format(stringFilter.getValue());
                            if (!String.valueOf(stringFilter.getValue()).isEmpty()) {
                                if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                                    if (sql.length() == 0) {
                                        sql.append("WHERE (( ").append(queryMap.get(stringFilter.getPropertyId().toString())).append(" >= '").append(filterString).append("')");
                                    } else {
                                        sql.append("AND ( ").append(queryMap.get(stringFilter.getPropertyId().toString())).append(" >= '").append(filterString).append("')");
                                    }
                                } else {
                                    if (sql.length() == 0) {
                                        sql.append("WHERE (( ").append(queryMap.get(stringFilter.getPropertyId().toString())).append(" <= '").append(filterString).append("')");
                                    } else {
                                        sql.append("AND ( ").append(queryMap.get(stringFilter.getPropertyId().toString())).append(" <= '").append(filterString).append("')");
                                    }
                                }
                            }
                        }
                    }

                }
                if (sql.length() != 0) {
                    sql.append(")");
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Error in filterQueryGenerator :" + ex);
        }
        return sql;
    }

    public StringBuilder orderByQueryGenerator(List<SortByColumn> sortByColumns, Map<String, String> queryMap) {
        boolean asc = false;
        StringBuilder tempStart = new StringBuilder("ORDER BY * ?");
        if (sortByColumns != null && !sortByColumns.isEmpty()) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = iterator.next();
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

    public StringBuilder getFilterForReserves(java.util.Set<Container.Filter> filterSet) {
        StringBuilder sql = CommonFilterLogic.getInstance().filterQueryGeneratorForBal(filterSet);
        if (sql != null && !sql.toString().equals(StringUtils.EMPTY)) {
            sql = sql.replace(0, NumericConstants.FIVE, "AND");
        }
        return sql;
    }
}
