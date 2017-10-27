/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.logic;

import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.SimpleStringFilter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mohamed.hameed
 */
public class AbstractFilterLogic {

    private static AbstractFilterLogic instance;
    private Map<String, String> filterMap = new HashMap<>();
    private Map<String, String> summaryFilterMap = new HashMap<>();
    private Map<String, String> queryMap = new HashMap<>();
    public static final String CONTRACT_STATUS = "contractStatus";

    private AbstractFilterLogic() {
        setFilterMap();
        setSummaryFilterMap();

    }

    public static synchronized AbstractFilterLogic getInstance() {
        if (instance == null) {
            instance = new AbstractFilterLogic();
        }
        return instance;
    }

    public Map<String, String> getFilterMap() {
        return filterMap;
    }

    public void setFilterMap(Map<String, String> filterMap) {
        this.filterMap = filterMap;
    }

    private void setFilterMap() {
        filterMap.put("contractId", "CN.CONTRACT_ID");
        filterMap.put(Constants.CONTRACT_NO, " CN.CONTRACT_NO");
        filterMap.put(Constants.CONTRACT_NAME, "CN.CONTRACT_NAME");
        filterMap.put(CONTRACT_STATUS, "CN_ST.DESCRIPTION");
        filterMap.put(Constants.START_DATE, "CN.START_DATE");
        filterMap.put(Constants.END_DATE, "CN.END_DATE");
        filterMap.put(Constants.CFP_NAME, "CFC.CFP_NAME");
        filterMap.put(Constants.IFPNAME, "IFC.IFP_NAME");
        filterMap.put(Constants.PSNAME, "PSC.PS_NAME");
        filterMap.put("frequency", " ");
        filterMap.put("rarType", " ");
        filterMap.put("basis", " ");
    }

    private void setSummaryFilterMap() {
        summaryFilterMap.put("contractHolder", "CM.COMPANY_NAME");
        summaryFilterMap.put(Constants.CONTRACT_NO, " CN.CONTRACT_NO");
        summaryFilterMap.put(Constants.CONTRACT_NAME, "CN.CONTRACT_NAME");
        summaryFilterMap.put(Constants.MARKET_TYPE, "HT.DESCRIPTION");
        summaryFilterMap.put(Constants.START_DATE, "CN.START_DATE");
        summaryFilterMap.put(Constants.END_DATE, "CN.END_DATE");
        summaryFilterMap.put(Constants.CFP_NAME, "CFC.CFP_NAME");
        summaryFilterMap.put(Constants.IFPNAME, "IFC.IFP_NAME");
        summaryFilterMap.put(Constants.PSNAME, "PSC.PS_NAME");
        summaryFilterMap.put(Constants.RSNAME, "RSC.RS_NAME");

    }

    public StringBuilder contractfilterQueryGenerator(java.util.Set<Container.Filter> filterSet) {
        queryMap.clear();
        queryMap.putAll(filterMap);
        return filterQueryGenerator(filterSet);
    }

    public StringBuilder summaryfilterQueryGenerator(java.util.Set<Container.Filter> filterSet) {
        queryMap.clear();
        queryMap.putAll(summaryFilterMap);
        return filterQueryGenerator(filterSet);
    }

    public StringBuilder filterQueryGenerator(java.util.Set<Container.Filter> filterSet) {
        StringBuilder str = new StringBuilder("AND ( * LIKE '?' OR * IS NULL )");
        StringBuilder sql = new StringBuilder();
        if (!filterSet.isEmpty()) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    if (queryMap.get(stringFilter.getPropertyId().toString()) != null && !queryMap.get(stringFilter.getPropertyId().toString()).isEmpty()) {
                        if (sql.length() == 0) {
                            StringBuilder initial = new StringBuilder("where ( ( * LIKE '?' )");
                            StringBuilder temp = new StringBuilder(initial);
                            temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                            if (!stringFilter.getPropertyId().toString().equals(CONTRACT_STATUS)) {
                                String filterString = "%" + stringFilter.getFilterString() + "%";
                                temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, filterString);
                                sql.append(temp);
                            } else {
                                temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, stringFilter.getFilterString());
                                sql.append(temp);
                            }

                            if (queryMap.get(stringFilter.getPropertyId().toString()).contains("DESCRIPTION")) {
                                sql.append("AND ").append(queryMap.get(stringFilter.getPropertyId().toString())).append(" NOT IN ('").append(Constants.IndicatorConstants.SELECT_ONE.getConstant()).append("')");
                            }
                        } else {
                            StringBuilder temp = new StringBuilder(str);
                            temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                            temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                            if (!stringFilter.getPropertyId().toString().equals(CONTRACT_STATUS)) {
                                String filterString = "%" + stringFilter.getFilterString() + "%";
                                temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, filterString);
                                sql.append(temp);
                            } else {
                                temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, stringFilter.getFilterString());
                                sql.append(temp);
                            }
                            if (queryMap.get(stringFilter.getPropertyId().toString()).contains("DESCRIPTION")) {
                                sql.append("AND ").append(queryMap.get(stringFilter.getPropertyId().toString())).append(" NOT IN ('").append(Constants.IndicatorConstants.SELECT_ONE.getConstant()).append("')");
                            }
                        }
                    }
                    sql.append(")");
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
                            tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, CommonUtils.dateFormat.format(startValue));
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
                            tempEnd.replace(tempEnd.indexOf("?"), tempEnd.indexOf("?") + 1, CommonUtils.dateFormat.format(endValue));
                            sql.append(tempEnd);
                        }
                    }
                    sql.append(")");
                }
            }

        }
        return sql;
    }
}
