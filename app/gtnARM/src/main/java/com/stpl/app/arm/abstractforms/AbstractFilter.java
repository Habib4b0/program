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
 * @author Porchelvi
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
     * @param sortColumns
     * @param resultsContainer
     */
    public void orderByQueryForContainer(List<SortByColumn> sortColumns, Container.Sortable resultsContainer) {
        if (sortColumns != null && !sortColumns.isEmpty()) {
            Object[] propIds = new Object[sortColumns.size()];
            boolean[] value = new boolean[sortColumns.size()];
            int i = 0;
            for (final Iterator<SortByColumn> iterator = sortColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = iterator.next();
                String columnName = sortByColumn.getName();
                propIds[i] = columnName;
                if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                    value[i] = true;
                } else {
                    value[i] = false;
                }
            }
            resultsContainer.sort(propIds, value);
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
                                String filterString = getFilterVal(stringFilter);
                                temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, filterString);
                                sql.append(temp);
                            } else {
                                String filterString = getFilterVal(stringFilter);
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
                                StringBuilder tempstart;
                                if (sql.length() == 0) {
                                    tempstart = new StringBuilder(initialStart);
                                } else {
                                    tempstart = new StringBuilder(dateStartstr);
                                }
                                tempstart.replace(tempstart.indexOf("*"), tempstart.indexOf("*") + 1, queryMap.get(betweenFilter.getPropertyId().toString()));
                                tempstart.replace(tempstart.indexOf("?"), tempstart.indexOf("?") + 1, ARMUtils.getInstance().getDbDate().format(startValue));
                                sql.append(tempstart);
                            }
                            if (!betweenFilter.getEndValue().toString().isEmpty()) {
                                StringBuilder tempend;
                                if (sql.length() == 0) {
                                    tempend = new StringBuilder(initialEnd);
                                } else {
                                    tempend = new StringBuilder(dateEndstr);
                                }

                                tempend.replace(tempend.indexOf("*"), tempend.indexOf("*") + 1, queryMap.get(betweenFilter.getPropertyId().toString()));
                                tempend.replace(tempend.indexOf("?"), tempend.indexOf("?") + 1, ARMUtils.getInstance().getDbDate().format(endValue));
                                sql.append(tempend);
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
                    sql.append(ARMUtils.CLOSE_BRACES);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Error in filterQueryGenerator :", ex);
        }
        return sql;
    }

    private String getFilterVal(SimpleStringFilter stringFilter) {
        String filterVal;
        if (stringFilter.getFilterString().contains("%")) {
            filterVal = "[" + stringFilter.getFilterString() + "]";
        } else {
            filterVal = stringFilter.getFilterString();
        }
        String filterString;
        if (ARMUtils.ADJUSTMENT_CONFIG_CONSTANTS.METHODOLOGY.getPropertyId().equals(stringFilter.getPropertyId().toString())
                && "0".equals(filterVal)) {
            filterString = "%";
        } else {
            filterString = "%" + filterVal + "%";
        }
        return filterString;
    }

    public StringBuilder orderByQueryGenerator(List<SortByColumn> orderByColumns, Map<String, String> orderMap) {
        boolean asc = false;
        StringBuilder tempStart = new StringBuilder("ORDER BY * ?");
        if (orderByColumns != null && !orderByColumns.isEmpty()) {
            for (final Iterator<SortByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = iterator.next();
                String columnName = sortByColumn.getName();
                if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                    asc = false;
                    tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, orderMap.get(columnName));
                } else {
                    asc = true;
                    tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, orderMap.get(columnName));
                }
            }
        } else {
            for (Map.Entry<String, String> entry : orderMap.entrySet()) {
                final String key = entry.getKey();
                if (key.contains("sid")) {
                    String value = orderMap.get(key);
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
