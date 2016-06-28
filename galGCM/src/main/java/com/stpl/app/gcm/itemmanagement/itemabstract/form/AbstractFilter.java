/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.form;

import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.SimpleStringFilter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AbstractFilter {

    private Map<String, String> queryMap = new HashMap<String, String>();
    private Map<String, String> filterMap = new HashMap<String, String>();
    private Map<String, String> summaryFilterMap = new HashMap<String, String>();
    private Map<String, String> cfpFilterMap = new HashMap<String, String>();
    private Map<String, String> ifpFilterMap = new HashMap<String, String>();
    private Map<String, String> psFilterMap = new HashMap<String, String>();
    private Map<String, String> rsFilterMap = new HashMap<String, String>();
    private Map<String, String> cfpSearchFilterMap = new HashMap<String, String>();
    private Map<String, String> ifpSearchFilterMap = new HashMap<String, String>();
    private Map<String, String> psSearchFilterMap = new HashMap<String, String>();
    Map<String, String> cfpComponentInfoMap = new HashMap<String, String>();
    Map<String, String> ifpComponentInfoMap = new HashMap<String, String>();
    Map<String, String> psComponentInfoMap = new HashMap<String, String>();
    Map<String, String> rsComponentInfoMap = new HashMap<String, String>();
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
        filterMap.put("projectionIdLink", "Temp.PRICE_PLAN_ID");
        filterMap.put("workFlowStatus", " HT.DESCRIPTION");
        filterMap.put(Constants.CONTRACT_HOLDER, "COMP.COMPANY_NAME");
        filterMap.put(Constants.CONTRACT_NO, "CM.CONTRACT_NO");
        filterMap.put(Constants.CONTRACT_NAME, "CM.CONTRACT_NAME");
        filterMap.put(Constants.MARKET_TYPE, "CM.CONTRACT_TYPE");
        filterMap.put("status", "Temp.ITEM_STATUS");
        filterMap.put("contractPrice", "Temp.contract_price");
        filterMap.put("price", "Temp.price");
        filterMap.put("priceToleranceType", "Temp.price_tolerance_type");
        filterMap.put("priceTolerance", "Temp.price_tolerance");
        filterMap.put("priceToleranceFrequency", "Temp.price_tolerance_frequency");
        filterMap.put("priceToleranceInterval", "Temp.price_tolerance_interval");
        filterMap.put("basePrice", "Temp.base_price");
        filterMap.put("formulaId", "Temp.formula_id");
        filterMap.put("rebatePlan", "");
        filterMap.put("formulaMethodId", "Temp.formula_method_id");
        filterMap.put("rebateAmount", "Temp.REBATE_AMOUNT");
        filterMap.put("cfpNO", "CFP_M.CFP_NO");
        filterMap.put(Constants.CFP_NAME, "CCT.CFP_NAME");
        filterMap.put("ifpNo", "IFP_M.IFP_NO");
        filterMap.put(Constants.IFPNAME, "IFP_CONT.IFP_NAME");
        filterMap.put("psNo", "PS_M.PS_NO");
        filterMap.put(Constants.PSNAME, "PSC.PS_NAME");
        filterMap.put("rsNo", "RS_M.RS_NO");
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
                            if (!stringFilter.getPropertyId().toString().equals("brand")) {
                                String filterString = "%" + stringFilter.getFilterString() + "%";
                                temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, filterString);
                                sql.append(temp);
                            } else {
                                temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, stringFilter.getFilterString());
                                sql.append(temp);
                            }
                            if (stringFilter.getPropertyId().toString().equals("workFlowStatus") || stringFilter.getPropertyId().toString().equals("projectionIdLink")) {
                                sql.append("AND HT.DESCRIPTION NOT IN ('Approved')");
                            }
                            if (queryMap.get(stringFilter.getPropertyId().toString()).contains("DESCRIPTION")) {
                                sql.append("AND ").append(queryMap.get(stringFilter.getPropertyId().toString())).append(" NOT IN ('").append(Constants.IndicatorConstants.SELECT_ONE.getConstant()).append("')");
                            }
                        } else {
                            StringBuilder temp = new StringBuilder(str);
                            temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                            temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                            if (!stringFilter.getPropertyId().toString().equals("brand")) {
                                String filterString = "%" + stringFilter.getFilterString() + "%";
                                temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, filterString);
                                sql.append(temp);
                            } else {
                                temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, stringFilter.getFilterString());
                                sql.append(temp);
                            }
                            if (stringFilter.getPropertyId().toString().equals("workFlowStatus") || stringFilter.getPropertyId().toString().equals("projectionIdLink")) {
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
        summaryFilterMap.put(Constants.CONTRACT_NO, "CM.CONTRACT_NO");
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
        cfpFilterMap.put("componentId", "CM.CFP_ID");
        cfpFilterMap.put("componentNo", "CM.CFP_NO");
        cfpFilterMap.put("componentName", "CM.CFP_NAME");
        cfpFilterMap.put("componentType", "TY.DESCRIPTION");
        cfpFilterMap.put("category", "CAT.DESCRIPTION");
        cfpFilterMap.put("designation", "CM.CFP_DESIGNATION");
        cfpFilterMap.put("componentStatus", "ST.DESCRIPTION");
        cfpFilterMap.put("tradeClass", "TRADE.DESCRIPTION");
        cfpFilterMap.put(Constants.START_DATE, "CC.CFP_START_DATE");
        cfpFilterMap.put(Constants.END_DATE, "CC.CFP_END_DATE");
    }

    private void setIFPFilterMap() {
        ifpFilterMap.put("componentNo", "IFP_M.IFP_NO");
        ifpFilterMap.put("componentName", "IFP_C.IFP_NAME");
        ifpFilterMap.put("componentType", "IFP_TYPE.description");
        ifpFilterMap.put("category", "IFP_Cat.DESCRIPTION");
        ifpFilterMap.put("designation", "IFP_D.DESCRIPTION");
        ifpFilterMap.put("componentStatus", "IFP_S.DESCRIPTION");
        ifpFilterMap.put(Constants.START_DATE, "IFP_C.IFP_START_DATE");
        ifpFilterMap.put(Constants.END_DATE, "IFP_C.IFP_END_DATE");
    }

    private void setPSFilterMap() {
        psFilterMap.put("componentNo", "PS_M.PS_NO");
        psFilterMap.put("componentName", "PS_C.PS_NAME");
        psFilterMap.put("componentType", "PS_TYPE.description");
        psFilterMap.put("category", "PS_Cat.DESCRIPTION");
        psFilterMap.put("tradeClass", "PS_T.DESCRIPTION");
        psFilterMap.put("designation", "PS_D.DESCRIPTION");
        psFilterMap.put("componentStatus", "PS_S.DESCRIPTION");
        psFilterMap.put(Constants.START_DATE, "PS_C.IFP_START_DATE");
        psFilterMap.put(Constants.END_DATE, "PS_C.IFP_END_DATE");
    }

    private void setRSFilterMap() {
        rsFilterMap.put("componentId", "RS_M.RS_ID");
        rsFilterMap.put("componentNo", "RS_M.RS_NO");
        rsFilterMap.put("componentName", "RS_C.RS_NAME");
        rsFilterMap.put("componentType", "RS_TYPE.description");
        rsFilterMap.put("rsProgramType", "RSP_TY.description");
        rsFilterMap.put("category", "RS_Cat.DESCRIPTION");
        rsFilterMap.put("tradeClass", "TR.DESCRIPTION");
        rsFilterMap.put("designation", "RS_C.RS_DESIGNATION");
        rsFilterMap.put("componentStatus", "RS_S.DESCRIPTION");
        rsFilterMap.put(Constants.START_DATE, "RS_C.RS_START_DATE");
        rsFilterMap.put(Constants.END_DATE, "RS_C.RS_END_DATE");
    }

    private void setCFPSearchFilterMap() {
        cfpSearchFilterMap.put("componentId", "CM.CFP_ID");
        cfpSearchFilterMap.put("componentNo", "CM.CFP_NO");
        cfpSearchFilterMap.put("componentName", "CM.CFP_NAME");
        cfpSearchFilterMap.put("componentType", "TYP.DESCRIPTION");
        cfpSearchFilterMap.put("category", "CAT.DESCRIPTION");
        cfpSearchFilterMap.put("designation", "CM.CFP_DESIGNATION");
        cfpSearchFilterMap.put("componentStatus", "ST.DESCRIPTION");
        cfpSearchFilterMap.put("tradeClass", "TC.DESCRIPTION");
        cfpSearchFilterMap.put(Constants.START_DATE, "CM.CFP_START_DATE");
        cfpSearchFilterMap.put(Constants.END_DATE, "CM.CFP_END_DATE");
    }

    private void setIFPSearchFilterMap() {
        ifpSearchFilterMap.put("componentId", "CM.IFP_ID");
        ifpSearchFilterMap.put("componentNo", "CM.IFP_NO");
        ifpSearchFilterMap.put("componentName", "CM.IFP_NAME");
        ifpSearchFilterMap.put("componentType", "TYP.DESCRIPTION");
        ifpSearchFilterMap.put("category", "CAT.DESCRIPTION");
        ifpSearchFilterMap.put("designation", "DESG.DESCRIPTION");
        ifpSearchFilterMap.put("componentStatus", "ST.DESCRIPTION");
        ifpSearchFilterMap.put("tradeClass", "TRADE.DESCRIPTION");
        ifpSearchFilterMap.put(Constants.START_DATE, "CC.IFP_START_DATE");
        ifpSearchFilterMap.put(Constants.END_DATE, "CC.IFP_END_DATE");
    }

    private void setPSSearchFilterMap() {
        psSearchFilterMap.put("componentNo", "PS.PS_NO");
        psSearchFilterMap.put("componentName", "PS.PS_NAME");
        psSearchFilterMap.put("category", "CAT.DESCRIPTION");
        psSearchFilterMap.put("componentType", "TYP.DESCRIPTION");
        psSearchFilterMap.put("designation", "desig.DESCRIPTION");
        psSearchFilterMap.put("parentPsId", "PS.PARENT_PS_ID");
        psSearchFilterMap.put("parentPsName", "PS.PARENT_PS_NAME");
        psSearchFilterMap.put("componentStatus", "ST.DESCRIPTION");
        psSearchFilterMap.put(Constants.START_DATE, "PS.PS_START_DATE");
        psSearchFilterMap.put(Constants.END_DATE, "PS.PS_END_DATE");
        psSearchFilterMap.put("tradeClass", "TC.DESCRIPTION");
    }

    public void setCfpComponentMap() {
        cfpComponentInfoMap = new HashMap<String, String>();
        cfpComponentInfoMap.put("attachedDate", "CFP_C.CFP_CONTRACT_ATTACHED_DATE");
        cfpComponentInfoMap.put("itemNo", "IM.ITEM_NO");
        cfpComponentInfoMap.put("itemName", "IM.ITEM_NAME");
        cfpComponentInfoMap.put(Constants.START_DATE, "IM.ITEM_START_DATE");
        cfpComponentInfoMap.put(Constants.END_DATE, "IM.ITEM_END_DATE");
        cfpComponentInfoMap.put("itemContractNo", "CM.CONTRACT_NO");
        cfpComponentInfoMap.put("tradeClass", "TRAD.DESCRIPTION");
        cfpComponentInfoMap.put("status", "ST.DESCRIPTION");
    }

    public void setIfpComponentMap() {
        ifpComponentInfoMap = new HashMap<String, String>();
        ifpComponentInfoMap.put("attachedDate", "IFP_D.IFP_CONTRACT_ATTACHED_DATE");
        ifpComponentInfoMap.put("itemNo", "IM.ITEM_NO");
        ifpComponentInfoMap.put("itemName", "IM.ITEM_NAME");
        ifpComponentInfoMap.put(Constants.START_DATE, "IFP_D.ITEM_START_DATE");
        ifpComponentInfoMap.put(Constants.END_DATE, "IFP_D.ITEM_END_DATE");
        ifpComponentInfoMap.put("brand", "BR.BRAND_NAME");
        ifpComponentInfoMap.put("status", "ST.DESCRIPTION");
    }

    public void setPsComponentMap() {
        psComponentInfoMap = new HashMap<String, String>();
        psComponentInfoMap.put("itemNo", "IM.ITEM_NO");
        psComponentInfoMap.put("itemName", "IM.ITEM_NAME");
        psComponentInfoMap.put(Constants.START_DATE, "PS_D.CONTRACT_PRICE_START_DATE");
        psComponentInfoMap.put(Constants.END_DATE, "PS_D.CONTRACT_PRICE_END_DATE");
        psComponentInfoMap.put("brand", " BM.BRAND_NAME");
        psComponentInfoMap.put("status", "ST.DESCRIPTION");
        psComponentInfoMap.put("priceType", "IPQ.PRICING_QUALIFIER");
        psComponentInfoMap.put("priceProtectionStatus", "ST.DESCRIPTION");
        psComponentInfoMap.put("priceProtectionStartDate", "PS_D.CONTRACT_PRICE_START_DATE");
        psComponentInfoMap.put("priceProtectionEndDate", "PS_D.PRICE_PROTECTION_END_DATE");
        psComponentInfoMap.put("priceToleranceInterval", "PTI.DESCRIPTION");
        psComponentInfoMap.put("priceToleranceFrequency", "PTF.DESCRIPTION");
        psComponentInfoMap.put("priceToleranceType", " PTT.DESCRIPTION");
        psComponentInfoMap.put("priceTolerance", " PS_D.PRICE_TOLERANCE");
        psComponentInfoMap.put("resetDate", "Getdate()");
        psComponentInfoMap.put("attachedDate", "PS_C.PS_CONTRACT_ATTACHED_DATE");

    }

    public void setRsComponentMap() {
        rsComponentInfoMap = new HashMap<String, String>();
        rsComponentInfoMap.put("itemNo", "IM.ITEM_NO");
        rsComponentInfoMap.put("itemName", "IM.ITEM_NAME");
        rsComponentInfoMap.put(Constants.START_DATE, "RS_DETAILS.ITEM_REBATE_START_DATE");
        rsComponentInfoMap.put(Constants.END_DATE, "RS_DETAILS.ITEM_REBATE_END_DATE");
        rsComponentInfoMap.put("brand", " BR.BRAND_NAME");
        rsComponentInfoMap.put("status", "ST.DESCRIPTION");
        rsComponentInfoMap.put("formulaId", "FORMULA.FORMULA_NO");
        rsComponentInfoMap.put("formulaName", "FORMULA.FORMULA_DESC");
        rsComponentInfoMap.put("rebatePlanId", "RL.REBATE_PLAN_ID");
        rsComponentInfoMap.put("rebatePlanName", "RL.REBATE_PLAN_NAME");
        rsComponentInfoMap.put("rebateAmount", "RS_DETAILS.REBATE_AMOUNT");
        rsComponentInfoMap.put("bundleNo", "RS_DETAILS.BUNDLE_NO");
        rsComponentInfoMap.put("attachedDate", "RS_CONT.RS_CONTRACT_ATTACHED_DATE");
    }
}
