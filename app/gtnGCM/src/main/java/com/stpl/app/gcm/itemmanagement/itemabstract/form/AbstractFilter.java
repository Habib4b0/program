/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.form;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.SimpleStringFilter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AbstractFilter {

    private final Map<String, String> queryMap = new HashMap<>();
    private final Map<String, String> filterMap = new HashMap<>();
    private final Map<String, String> summaryFilterMap = new HashMap<>();
    private final Map<String, String> cfpFilterMap = new HashMap<>();
    private final Map<String, String> ifpFilterMap = new HashMap<>();
    private final Map<String, String> psFilterMap = new HashMap<>();
    private final Map<String, String> rsFilterMap = new HashMap<>();
    private final Map<String, String> cfpSearchFilterMap = new HashMap<>();
    private final Map<String, String> ifpSearchFilterMap = new HashMap<>();
    private final Map<String, String> psSearchFilterMap = new HashMap<>();
    Map<String, String> cfpComponentInfoMap = new HashMap<>();
    Map<String, String> ifpComponentInfoMap = new HashMap<>();
    Map<String, String> psComponentInfoMap = new HashMap<>();
    Map<String, String> rsComponentInfoMap = new HashMap<>();
    private static AbstractFilter instance;

    private AbstractFilter() {
        setFilterMap();
        setSummaryFilterMap();
        setCFPFilterMap();
        setIFPFilterMap();
        setPSFilterMap();
        setRSFilterMap();
    }

    public static synchronized AbstractFilter getInstance() {
        if (instance == null) {
            instance = new AbstractFilter();
        }
        return instance;
    }

    private void setFilterMap() {
        filterMap.put(Constants.PROJ_ID_LINK, "Temp.PRICE_PLAN_ID");
        filterMap.put(Constants.WORKFLOW_STATUS, " HT.DESCRIPTION");
        filterMap.put(Constants.CONTRACT_HOLDER, "COMP.COMPANY_NAME");
        filterMap.put(Constants.CONTRACT_NO, StringConstantsUtil.CM_CONTRACT_NO);
        filterMap.put(Constants.CONTRACT_NAME, "CM.CONTRACT_NAME");
        filterMap.put(Constants.MARKET_TYPE, "CM.CONTRACT_TYPE");
        filterMap.put(Constants.STATUS_S, "Temp.ITEM_STATUS");
        filterMap.put("contractPrice", "Temp.contract_price");
        filterMap.put("price", "Temp.price");
        filterMap.put("priceToleranceType", "Temp.price_tolerance_type");
        filterMap.put(Constants.PRICE_TOLERANCE_PROPERTY, "Temp.price_tolerance");
        filterMap.put("priceToleranceFrequency", "Temp.price_tolerance_frequency");
        filterMap.put("priceToleranceInterval", "Temp.price_tolerance_interval");
        filterMap.put("basePrice", "Temp.base_price");
        filterMap.put("formulaId", "Temp.formula_id");
        filterMap.put("rebatePlan", "");
        filterMap.put("formulaMethodId", "Temp.formula_method_id");
        filterMap.put("rebateAmount", "Temp.REBATE_AMOUNT");
        filterMap.put("cfpNO", "CCT.CFP_NO");
        filterMap.put(Constants.CFP_NAME, "CCT.CFP_NAME");
        filterMap.put("ifpNo", "IFP_CONT.IFP_NO");
        filterMap.put(Constants.IFPNAME, "IFP_CONT.IFP_NAME");
        filterMap.put("psNo", "PSC.PS_NO");
        filterMap.put(Constants.PSNAME, "PSC.PS_NAME");
        filterMap.put("rsNo", "RSC.RS_NO");
        filterMap.put(Constants.RSNAME, "RSC.RS_NAME");
        filterMap.put(Constants.START_DATE, "CM.START_DATE");
        filterMap.put(Constants.END_DATE, "CM.END_DATE");
        filterMap.put("itemStartDate", "Temp.START_DATE");
        filterMap.put("itemEndDate", "Temp.END_DATE");
        filterMap.put("cpStartDate", "Temp.CONTRACT_PRICE_START_DATE");
        filterMap.put("cpEndDate", "Temp.CONTRACT_PRICE_END_DATE");
        filterMap.put("priceProtectionStartDate", "Temp.PRICE_PROTECTION_START_DATE");
        filterMap.put("priceProtectionEndDate", "Temp.PRICE_PROTECTION_END_DATE");
        filterMap.put("RSStartDate", "Temp.ITEM_REBATE_START_DATE");
        filterMap.put("RSEndDate", "Temp.ITEM_REBATE_End_DATE");
        filterMap.put(Constants.NEP_PROPERTY, "Temp.NEP");
        filterMap.put(Constants.NEP_FORMULA_PROPERTY, "Temp.NEP_FORMULA");
        filterMap.put(Constants.MAX_INCREMENTAL_CHANGE_PROPERTY, "Temp.MAX_INCREMENTAL_CHANGE");
        filterMap.put(Constants.RESET_ELIGIBLE_PROPERTY, "Temp.RESET_ELIGIBLE");
        filterMap.put(Constants.RESET_TYPE_PROPERTY, "Temp.RESET_TYPE");
        filterMap.put(Constants.RESET_DATE_PROPERTY, "Temp.RESET_DATE");
        filterMap.put(Constants.RESET_INTERVAL_PROPERTY, "Temp.RESET_INTERVAL");
        filterMap.put(Constants.RESET_FREQUENCY_PROPERTY, "Temp.RESET_FREQUENCY");
        filterMap.put(Constants.NET_PRICE_TYPE_PROPERTY, "Temp.NET_PRICE_TYPE");
        filterMap.put(Constants.NET_PRICE_TYPE_FORMULA_PROPERTY, "Temp.NET_PRICE_TYPE_FORMULA");
        filterMap.put(Constants.RESET_PRICE_TYPE_PROPERTY, "Temp.RESET_PRICE_TYPE");
        filterMap.put(Constants.NET_RESET_PRICE_TYPE_PROPERTY, "Temp.NET_RESET_PRICE_TYPE");
        filterMap.put(Constants.NET_RESET_PRICE_FORMULA_PROPERTY, "Temp.NET_RESET_PRICE_FORMULA_ID");
        filterMap.put(Constants.BASE_PRICE_PROPERTY, "Temp.BASE_PRICE_TYPE");
        filterMap.put(Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_PROPERTY, "Temp.SUBSEQUENT_PERIOD_PRICE_TYPE");
        filterMap.put(Constants.NET_SUBSEQUENT_PERIOD_PRICE_PROPERTY, "Temp.NET_SUBSEQUENT_PERIOD_PRICE");
        filterMap.put(Constants.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_PROPERTY, "Temp.NET_SUBSEQUENT_PRICE_FORMULA_ID");
        filterMap.put(Constants.NET_BASELINE_WAC_FORMULA_PROPERTY, "Temp.NET_BASELINE_WAC_FORMULA_ID");
        filterMap.put(Constants.BASELINE_NET_WAC_PROPERTY, "Temp.NET_BASE_PRICE");
        filterMap.put(Constants.PRICE_TYPE_PROPERTY, "Temp.PRICE_TYPE");
        filterMap.put(Constants.MEASUREMENT_PRICE_PROPERTY, "Temp.PRICE_PROTECTION_PRICE_TYPE");
        filterMap.put(Constants.PRICE_PROTECTION_STATUS_PROPERTY, "Temp.PRICE_PROTECTION_STATUS");
        
    }

    public void setFilterMap(String key, String Value) {
        filterMap.put(key, Value);
    }

    public void setFilterMap(Map m) {
        filterMap.putAll(m);
    }

    public Map getFilterMap() {
        return filterMap;
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

    public StringBuilder lookUpfilterQueryGenerator(final String indicator, java.util.Set<Container.Filter> filterSet) {
        queryMap.clear();
        if (indicator.equals(Constants.CFP)) {
            queryMap.putAll(cfpFilterMap);
        } else if (indicator.equals(Constants.IFP)) {
            queryMap.putAll(ifpFilterMap);
        } else if (indicator.equals(Constants.PS)) {
            queryMap.putAll(psFilterMap);
        } else if (indicator.equals(Constants.RS)) {
            queryMap.putAll(rsFilterMap);
        }
        return filterQueryGenerator(filterSet);
    }

    public StringBuilder getComponentfilterQueryGenerator(final String indicator, java.util.Set<Container.Filter> filterSet) {
        queryMap.clear();
        if (indicator.equals(Constants.CFP)) {
            setCfpComponentMap();
            queryMap.putAll(cfpComponentInfoMap);
        } else if (indicator.equals(Constants.IFP)) {
            setIfpComponentMap();
            queryMap.putAll(ifpComponentInfoMap);
        } else if (indicator.equals(Constants.PS)) {
            setPsComponentMap();
            queryMap.putAll(psComponentInfoMap);
        } else if (indicator.equals(Constants.RS)) {
            setRsComponentMap();
            queryMap.putAll(rsComponentInfoMap);
        }
        return filterQueryGenerator(filterSet);
    }

    public StringBuilder lookUpSearchfilterQueryGenerator(final String indicator, java.util.Set<Container.Filter> filterSet) {
        queryMap.clear();
        if (indicator.equals(Constants.CFP)) {
            setCFPSearchFilterMap();
            queryMap.putAll(cfpSearchFilterMap);
        } else if (indicator.equals(Constants.IFP)) {
            setIFPSearchFilterMap();
            queryMap.putAll(ifpSearchFilterMap);
        } else if (indicator.equals(Constants.PS)) {
            setPSSearchFilterMap();
            queryMap.putAll(psSearchFilterMap);
        }
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
                            if (!stringFilter.getPropertyId().toString().equals(Constants.BRAND_PROPERTY)) {
                                String filterString = "%" + stringFilter.getFilterString() + "%";
                                temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, filterString);
                                sql.append(temp);
                            } else {
                                temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, stringFilter.getFilterString());
                                sql.append(temp);
                            }
                            if (stringFilter.getPropertyId().toString().equals(Constants.WORKFLOW_STATUS) || stringFilter.getPropertyId().toString().equals(Constants.PROJ_ID_LINK)) {
                                sql.append("AND HT.DESCRIPTION NOT IN ('Approved')");
                            }
                            if (queryMap.get(stringFilter.getPropertyId().toString()).contains("DESCRIPTION")) {
                                sql.append("AND ").append(queryMap.get(stringFilter.getPropertyId().toString())).append(" NOT IN ('").append(Constants.IndicatorConstants.SELECT_ONE.getConstant()).append("')");
                            }
                        } else {
                            StringBuilder temp = new StringBuilder(str);
                            temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                            temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                            if (!stringFilter.getPropertyId().toString().equals(Constants.BRAND_PROPERTY)) {
                                String filterString = "%" + stringFilter.getFilterString() + "%";
                                temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, filterString);
                                sql.append(temp);
                            } else {
                                temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, stringFilter.getFilterString());
                                sql.append(temp);
                            }
                            if (stringFilter.getPropertyId().toString().equals(Constants.WORKFLOW_STATUS) || stringFilter.getPropertyId().toString().equals(Constants.PROJ_ID_LINK)) {
                                sql.append("AND HT.DESCRIPTION NOT IN ('Approved')");
                            }
                            if (queryMap.get(stringFilter.getPropertyId().toString()).contains("DESCRIPTION")) {
                                sql.append("AND ").append(queryMap.get(stringFilter.getPropertyId().toString())).append(" NOT IN ('").append(Constants.IndicatorConstants.SELECT_ONE.getConstant()).append("')");
                            }
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
                            tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, CommonUtils.DBDate.format(startValue));
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
                            tempEnd.replace(tempEnd.indexOf("?"), tempEnd.indexOf("?") + 1, CommonUtils.DBDate.format(endValue));
                            sql.append(tempEnd);
                        }
                    } else {
                    }
                }

            }
            sql.append(")");
        }
        return sql;

    }

    private void setSummaryFilterMap() {
        summaryFilterMap.put(Constants.CONTRACT_HOLDER, "COMP.COMPANY_NAME");
        summaryFilterMap.put(Constants.CONTRACT_NO, StringConstantsUtil.CM_CONTRACT_NO);
        summaryFilterMap.put(Constants.CONTRACT_NAME, "CM.CONTRACT_NAME");
        summaryFilterMap.put(Constants.MARKET_TYPE, "CM.CONTRACT_TYPE");
        summaryFilterMap.put(Constants.START_DATE, "CM.START_DATE");
        summaryFilterMap.put(Constants.END_DATE, "CM.END_DATE");
        summaryFilterMap.put("cfp", "CFP.CFP_NAME");
        summaryFilterMap.put("ifp", "IFP.IFP_NAME");
        summaryFilterMap.put("ps", "PS.PS_NAME");
        summaryFilterMap.put("rs", "RS.RS_NAME");

    }

    private void setCFPFilterMap() {
        cfpFilterMap.put(StringConstantsUtil.COMPONENT_ID_PROPERTY, "CM.CFP_ID");
        cfpFilterMap.put(StringConstantsUtil.COMPONENT_NO, "CC.CFP_NO");
        cfpFilterMap.put(StringConstantsUtil.COMPONENT_NAME, "CC.CFP_NAME");
        cfpFilterMap.put(StringConstantsUtil.COMPONENT_TYPE, "TY.DESCRIPTION");
        cfpFilterMap.put(Constants.CATEGORY, StringConstantsUtil.CAT_DESCRIPTION);
        cfpFilterMap.put(StringConstantsUtil.DESIGNATION_PROPERTY, "CC.CFP_DESIGNATION");
        cfpFilterMap.put(StringConstantsUtil.COMPONENT_STATUS_PROPERTY, StringConstantsUtil.ST_DESCRIPTION);
        cfpFilterMap.put(Constants.TRADE_CLASS_PROPERTY, "TRADE.DESCRIPTION");
        cfpFilterMap.put(Constants.START_DATE, "CC.CFP_START_DATE");
        cfpFilterMap.put(Constants.END_DATE, "CC.CFP_END_DATE");
    }

    private void setIFPFilterMap() {
        ifpFilterMap.put(StringConstantsUtil.COMPONENT_NO, "IFP_C.IFP_NO");
        ifpFilterMap.put(StringConstantsUtil.COMPONENT_NAME, "IFP_C.IFP_NAME");
        ifpFilterMap.put(StringConstantsUtil.COMPONENT_TYPE, "IFP_TYPE.description");
        ifpFilterMap.put(Constants.CATEGORY, "IFP_Cat.DESCRIPTION");
        ifpFilterMap.put(StringConstantsUtil.DESIGNATION_PROPERTY, "IFP_D.DESCRIPTION");
        ifpFilterMap.put(StringConstantsUtil.COMPONENT_STATUS_PROPERTY, "IFP_S.DESCRIPTION");
        ifpFilterMap.put(Constants.START_DATE, "IFP_C.IFP_START_DATE");
        ifpFilterMap.put(Constants.END_DATE, "IFP_C.IFP_END_DATE");
    }

    private void setPSFilterMap() {
        psFilterMap.put(StringConstantsUtil.COMPONENT_NO, "PS_C.PS_NO");
        psFilterMap.put(StringConstantsUtil.COMPONENT_NAME, "PS_C.PS_NAME");
        psFilterMap.put(StringConstantsUtil.COMPONENT_TYPE, "PS_TYPE.description");
        psFilterMap.put(Constants.CATEGORY, "PS_Cat.DESCRIPTION");
        psFilterMap.put(Constants.TRADE_CLASS_PROPERTY, "PS_T.DESCRIPTION");
        psFilterMap.put(StringConstantsUtil.DESIGNATION_PROPERTY, "PS_D.DESCRIPTION");
        psFilterMap.put(StringConstantsUtil.COMPONENT_STATUS_PROPERTY, "PS_S.DESCRIPTION");
        psFilterMap.put(Constants.START_DATE, "PS_C.IFP_START_DATE");
        psFilterMap.put(Constants.END_DATE, "PS_C.IFP_END_DATE");
    }

    private void setRSFilterMap() {
        rsFilterMap.put(StringConstantsUtil.COMPONENT_ID_PROPERTY, "RS_M.RS_ID");
        rsFilterMap.put(StringConstantsUtil.COMPONENT_NO, "RS_C.RS_NO");
        rsFilterMap.put(StringConstantsUtil.COMPONENT_NAME, "RS_C.RS_NAME");
        rsFilterMap.put(StringConstantsUtil.COMPONENT_TYPE, "RS_TYPE.description");
        rsFilterMap.put("rsProgramType", "RSP_TY.description");
        rsFilterMap.put(Constants.CATEGORY, "RS_Cat.DESCRIPTION");
        rsFilterMap.put(Constants.TRADE_CLASS_PROPERTY, "TR.DESCRIPTION");
        rsFilterMap.put(StringConstantsUtil.DESIGNATION_PROPERTY, "RS_C.RS_DESIGNATION");
        rsFilterMap.put(StringConstantsUtil.COMPONENT_STATUS_PROPERTY, "RS_S.DESCRIPTION");
        rsFilterMap.put(Constants.START_DATE, "RS_C.RS_START_DATE");
        rsFilterMap.put(Constants.END_DATE, "RS_C.RS_END_DATE");
    }

    private void setCFPSearchFilterMap() {
        cfpSearchFilterMap.put(StringConstantsUtil.COMPONENT_ID_PROPERTY, "CM.CFP_ID");
        cfpSearchFilterMap.put(StringConstantsUtil.COMPONENT_NO, "CM.CFP_NO");
        cfpSearchFilterMap.put(StringConstantsUtil.COMPONENT_NAME, "CM.CFP_NAME");
        cfpSearchFilterMap.put(StringConstantsUtil.COMPONENT_TYPE, StringConstantsUtil.TYP_DESCRIPTION);
        cfpSearchFilterMap.put(Constants.CATEGORY, StringConstantsUtil.CAT_DESCRIPTION);
        cfpSearchFilterMap.put(StringConstantsUtil.DESIGNATION_PROPERTY, "CM.CFP_DESIGNATION");
        cfpSearchFilterMap.put(StringConstantsUtil.COMPONENT_STATUS_PROPERTY, StringConstantsUtil.ST_DESCRIPTION);
        cfpSearchFilterMap.put(Constants.TRADE_CLASS_PROPERTY, "TC.DESCRIPTION");
        cfpSearchFilterMap.put(Constants.START_DATE, "CM.CFP_START_DATE");
        cfpSearchFilterMap.put(Constants.END_DATE, "CM.CFP_END_DATE");
    }

    private void setIFPSearchFilterMap() {
        ifpSearchFilterMap.put(StringConstantsUtil.COMPONENT_ID_PROPERTY, "CM.IFP_ID");
        ifpSearchFilterMap.put(StringConstantsUtil.COMPONENT_NO, "CC.IFP_NO");
        ifpSearchFilterMap.put(StringConstantsUtil.COMPONENT_NAME, "CC.IFP_NAME");
        ifpSearchFilterMap.put(StringConstantsUtil.COMPONENT_TYPE, StringConstantsUtil.TYP_DESCRIPTION);
        ifpSearchFilterMap.put(Constants.CATEGORY, StringConstantsUtil.CAT_DESCRIPTION);
        ifpSearchFilterMap.put(StringConstantsUtil.DESIGNATION_PROPERTY, "DESG.DESCRIPTION");
        ifpSearchFilterMap.put(StringConstantsUtil.COMPONENT_STATUS_PROPERTY, StringConstantsUtil.ST_DESCRIPTION);
        ifpSearchFilterMap.put(Constants.TRADE_CLASS_PROPERTY, "TRADE.DESCRIPTION");
        ifpSearchFilterMap.put(Constants.START_DATE, "CC.IFP_START_DATE");
        ifpSearchFilterMap.put(Constants.END_DATE, "CC.IFP_END_DATE");
    }

    private void setPSSearchFilterMap() {
        psSearchFilterMap.put(StringConstantsUtil.COMPONENT_NO, "PS.PS_NO");
        psSearchFilterMap.put(StringConstantsUtil.COMPONENT_NAME, "PS.PS_NAME");
        psSearchFilterMap.put(Constants.CATEGORY, StringConstantsUtil.CAT_DESCRIPTION);
        psSearchFilterMap.put(StringConstantsUtil.COMPONENT_TYPE, StringConstantsUtil.TYP_DESCRIPTION);
        psSearchFilterMap.put(StringConstantsUtil.DESIGNATION_PROPERTY, "desig.DESCRIPTION");
        psSearchFilterMap.put("parentPsId", "PS.PARENT_PS_ID");
        psSearchFilterMap.put("parentPsName", "PS.PARENT_PS_NAME");
        psSearchFilterMap.put(StringConstantsUtil.COMPONENT_STATUS_PROPERTY, StringConstantsUtil.ST_DESCRIPTION);
        psSearchFilterMap.put(Constants.START_DATE, "PS.PS_START_DATE");
        psSearchFilterMap.put(Constants.END_DATE, "PS.PS_END_DATE");
        psSearchFilterMap.put(Constants.TRADE_CLASS_PROPERTY, "TC.DESCRIPTION");
    }

    public void setCfpComponentMap() {
        cfpComponentInfoMap = new HashMap<>();
        cfpComponentInfoMap.put(Constants.ATTACHED_DATE_PROPERTY, "CFP_C.CFP_CONTRACT_ATTACHED_DATE");
        cfpComponentInfoMap.put(Constants.ITEM_NO_PROPERTY, StringConstantsUtil.IM_ITEM_NO);
        cfpComponentInfoMap.put(Constants.ITEM_NAME_PROPERTY, StringConstantsUtil.IM_ITEM_NAME);
        cfpComponentInfoMap.put(Constants.START_DATE, "IM.ITEM_START_DATE");
        cfpComponentInfoMap.put(Constants.END_DATE, "IM.ITEM_END_DATE");
        cfpComponentInfoMap.put("itemContractNo", StringConstantsUtil.CM_CONTRACT_NO);
        cfpComponentInfoMap.put(Constants.TRADE_CLASS_PROPERTY, "TRAD.DESCRIPTION");
        cfpComponentInfoMap.put(Constants.STATUS_S, StringConstantsUtil.ST_DESCRIPTION);
    }

    public void setIfpComponentMap() {
        ifpComponentInfoMap = new HashMap<>();
        ifpComponentInfoMap.put(Constants.ATTACHED_DATE_PROPERTY, "IFP_D.IFP_CONTRACT_ATTACHED_DATE");
        ifpComponentInfoMap.put(Constants.ITEM_NO_PROPERTY, StringConstantsUtil.IM_ITEM_NO);
        ifpComponentInfoMap.put(Constants.ITEM_NAME_PROPERTY, StringConstantsUtil.IM_ITEM_NAME);
        ifpComponentInfoMap.put(Constants.START_DATE, "IFP_D.ITEM_START_DATE");
        ifpComponentInfoMap.put(Constants.END_DATE, "IFP_D.ITEM_END_DATE");
        ifpComponentInfoMap.put(Constants.BRAND_PROPERTY, "BR.BRAND_NAME");
        ifpComponentInfoMap.put(Constants.STATUS_S, StringConstantsUtil.ST_DESCRIPTION);
    }

    public void setPsComponentMap() {
        psComponentInfoMap = new HashMap<>();
        psComponentInfoMap.put(Constants.ITEM_NO_PROPERTY, StringConstantsUtil.IM_ITEM_NO);
        psComponentInfoMap.put(Constants.ITEM_NAME_PROPERTY, StringConstantsUtil.IM_ITEM_NAME);
        psComponentInfoMap.put(Constants.START_DATE, "PS_D.CONTRACT_PRICE_START_DATE");
        psComponentInfoMap.put(Constants.END_DATE, "PS_D.CONTRACT_PRICE_END_DATE");
        psComponentInfoMap.put(Constants.BRAND_PROPERTY, " BM.BRAND_NAME");
        psComponentInfoMap.put(Constants.STATUS_S, StringConstantsUtil.ST_DESCRIPTION);
        psComponentInfoMap.put(Constants.PRICE_PROTECTION_STATUS_PROPERTY, StringConstantsUtil.ST_DESCRIPTION);
        psComponentInfoMap.put("priceProtectionStartDate", "PS_D.CONTRACT_PRICE_START_DATE");
        psComponentInfoMap.put("priceProtectionEndDate", "PS_D.PRICE_PROTECTION_END_DATE");
        psComponentInfoMap.put(Constants.PRICE_TOLERANCE_PROPERTY, " PS_D.PRICE_TOLERANCE");
        psComponentInfoMap.put(Constants.ATTACHED_DATE_PROPERTY, "PS_C.PS_CONTRACT_ATTACHED_DATE");
        psComponentInfoMap.put(Constants.NEP_PROPERTY, "PS_D.NEP");
        psComponentInfoMap.put(Constants.NEP_FORMULA_PROPERTY, "PS_D.NEP_FORMULA");
        psComponentInfoMap.put(Constants.BASE_PRICE_PROPERTY, "BASE_PRICE_TYPE.DESCRIPTION");
        psComponentInfoMap.put(Constants.BASELINE_NET_WAC_PROPERTY, "NET_BASE_PRICE.DESCRIPTION");
        psComponentInfoMap.put(Constants.NET_BASELINE_WAC_FORMULA_PROPERTY, "PS_D.NET_BASELINE_WAC_FORMULA_ID");
        psComponentInfoMap.put(Constants.NET_SUBSEQUENT_PERIOD_PRICE_PROPERTY, "NET_SUBSEQUENT_PERIOD_PRICE.DESCRIPTION");
        psComponentInfoMap.put(Constants.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_PROPERTY, "PS_D.NET_SUBSEQUENT_PRICE_FORMULA_ID");
        psComponentInfoMap.put("priceToleranceInterval", "PRICE_TOLERANCE_INTERVAL.DESCRIPTION");
        psComponentInfoMap.put("priceToleranceFrequency", "PRICE_TOLERANCE_FREQUENCY.DESCRIPTION");
        psComponentInfoMap.put("priceToleranceType", " PRICE_TOLERANCE_TYPE.DESCRIPTION");
        psComponentInfoMap.put(Constants.PRICE_TOLERANCE_PROPERTY, " PS_D.PRICE_TOLERANCE");
        psComponentInfoMap.put(Constants.MAX_INCREMENTAL_CHANGE_PROPERTY, "PS_D.MAX_INCREMENTAL_CHANGE");
        psComponentInfoMap.put(Constants.RESET_ELIGIBLE_PROPERTY, "RESET_ELIGIBLE.DESCRIPTION");
        psComponentInfoMap.put(Constants.RESET_TYPE_PROPERTY, "RESET_TYPE.DESCRIPTION");
        psComponentInfoMap.put(Constants.RESET_DATE_PROPERTY, "PS_D.RESET_DATE");
        psComponentInfoMap.put(Constants.RESET_INTERVAL_PROPERTY, "RESET_INTERVAL.DESCRIPTION");
        psComponentInfoMap.put(Constants.RESET_FREQUENCY_PROPERTY, "RESET_FREQUENCY.DESCRIPTION");
        psComponentInfoMap.put(Constants.NET_PRICE_TYPE_PROPERTY, "NET_PRICE_TYPE.DESCRIPTION");
        psComponentInfoMap.put(Constants.NET_PRICE_TYPE_FORMULA_PROPERTY, "PS_D.NET_PRICE_TYPE_FORMULA");
        psComponentInfoMap.put(Constants.NET_RESET_PRICE_TYPE_PROPERTY, "NET_RESET_PRICE_TYPE.DESCRIPTION");
        psComponentInfoMap.put(Constants.NET_RESET_PRICE_FORMULA_PROPERTY, "PS_D.NET_RESET_PRICE_FORMULA_ID");
        psComponentInfoMap.put(Constants.BASELINE_NET_WAC_PROPERTY, "NET_BASE_PRICE.DESCRIPTION");
        psComponentInfoMap.put(Constants.RESET_PRICE_TYPE_PROPERTY, "PS_D.RESET_PRICE_TYPE");
        psComponentInfoMap.put(Constants.MEASUREMENT_PRICE_PROPERTY, "PS_D.PRICE_PROTECTION_PRICE_TYPE");
        psComponentInfoMap.put(Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_PROPERTY, "PS_D.SUBSEQUENT_PERIOD_PRICE_TYPE");

    }

    public void setRsComponentMap() {
        rsComponentInfoMap = new HashMap<>();
        rsComponentInfoMap.put(Constants.ITEM_NO_PROPERTY, StringConstantsUtil.IM_ITEM_NO);
        rsComponentInfoMap.put(Constants.ITEM_NAME_PROPERTY, StringConstantsUtil.IM_ITEM_NAME);
        rsComponentInfoMap.put(Constants.START_DATE, "RS_DETAILS.ITEM_REBATE_START_DATE");
        rsComponentInfoMap.put(Constants.END_DATE, "RS_DETAILS.ITEM_REBATE_END_DATE");
        rsComponentInfoMap.put(Constants.BRAND_PROPERTY, " BR.BRAND_NAME");
        rsComponentInfoMap.put(Constants.STATUS_S, StringConstantsUtil.ST_DESCRIPTION);
        rsComponentInfoMap.put("formulaId", "FORMULA.FORMULA_NO");
        rsComponentInfoMap.put("formulaName", "FORMULA.FORMULA_DESC");
        rsComponentInfoMap.put("rebatePlanId", "RL.REBATE_PLAN_ID");
        rsComponentInfoMap.put("rebatePlanName", "RL.REBATE_PLAN_NAME");
        rsComponentInfoMap.put("rebateAmount", "RS_DETAILS.REBATE_AMOUNT");
        rsComponentInfoMap.put("bundleNo", "RS_DETAILS.BUNDLE_NO");
        rsComponentInfoMap.put(Constants.ATTACHED_DATE_PROPERTY, "RS_CONT.RS_CONTRACT_ATTACHED_DATE");
    }
}
