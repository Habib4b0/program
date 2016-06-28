/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.logic;

import com.stpl.app.util.Constants;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.SimpleStringFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author mohamed.hameed
 */
public class AbstractFilter {

    public static final SimpleDateFormat DBDate = new SimpleDateFormat("yyyy-MM-dd");
    private Map<String, String> queryMap = new HashMap<String, String>();
    private Map<String, String> filterCustomerMap = new HashMap<String, String>();
    private Map<String, String> filterProductMap = new HashMap<String, String>();
    private Map<String, String> dsFilterMap = new HashMap<String, String>();
    private Map<String, String> availableFilterMap = new HashMap<String, String>();
    private Map<String, String> selectedCustomerFilterMap = new HashMap<String, String>();
    private Map<String, String> selectedProductFilterMap = new HashMap<String, String>();
    private Map<String, String> availableProductFilterMap = new HashMap<String, String>();
    private static AbstractFilter instance;
    public static final Logger LOGGER = Logger.getLogger(AbstractFilter.class);

    private AbstractFilter() {
        setFilterMap();
        setProdcutFilterMap();
        dsFilter();
        availableFilterMap();
        selectedCustomerFilterMap();
        selectedProductFilterMap();
        availableProductFilterMap();
    }

    public static synchronized AbstractFilter getInstance() {
        if (instance == null) {
            instance = new AbstractFilter();
        }
        return instance;
    }

    private void setFilterMap() {
        filterCustomerMap.put("customerGroupName", "CG.company_Group_Name");
        filterCustomerMap.put("customerGroupNo", "CG.company_Group_No");
        filterCustomerMap.put("customerGroupDesc", "CG.COMPANY_GROUP_DESCRIPTION");
    }

    private void setProdcutFilterMap() {
        filterProductMap.put("productGroupName", "IG.item_Group_Name");
        filterProductMap.put("productGroupNo", "IG.item_Group_No");
        filterProductMap.put("productGroupDesc", "IG.ITEM_GROUP_DESCRIPTION");
        filterProductMap.put("company", "CM.COMPANY_NAME");
    }

    private void dsFilter() {
        dsFilterMap.put("reportName", "ACM.REPORT_NAME");
        dsFilterMap.put("description", "ACM.DESCRIPTION");
        dsFilterMap.put("company", "CM.COMPANY_NAME");
        dsFilterMap.put("marketType", "HT.DESCRIPTION");
        dsFilterMap.put("deductionType", "RS_TYPE.DESCRIPTION");
        dsFilterMap.put("deductionSubType", "RS_CAT.DESCRIPTION");
        dsFilterMap.put("customerGroup", "ACM.COMPANY_GROUP_SID");
        dsFilterMap.put("contractName", "CT.CONTRACT_NAME");
        dsFilterMap.put("productGroup", "ACM.ITEM_GROUP_SID");
        dsFilterMap.put("fromPeriod", "ACM.FROM_DATE");
        dsFilterMap.put("toPeriod", "ACM.TO_DATE");
        dsFilterMap.put("productName", "ACM.ITEM_MASTER_SID");
        dsFilterMap.put("productIdentifier", "ACM.PRODUCT_IDENTIFIER");
        dsFilterMap.put("createdDate", "ACM.CREATED_DATE");
        dsFilterMap.put("modifiedDate", "ACM.MODIFIED_DATE");
        dsFilterMap.put("createdBy", "ACM.CREATED_BY");
        dsFilterMap.put("modifiedBy", "ACM.MODIFIED_BY");
    }

    public StringBuilder getDSFilter(java.util.Set<Container.Filter> filterSet) {
        queryMap.clear();
        queryMap.putAll(dsFilterMap);
        StringBuilder sql = filterQueryGenerator(filterSet);
        if (sql != null && !sql.toString().equals(StringUtils.EMPTY)) {
            sql = sql.replace(0, 5, "AND");
        }
        return sql;
    }

    public StringBuilder gtnBalanceCustomerLookUp(java.util.Set<Container.Filter> filterSet) {
        queryMap.clear();
        queryMap.putAll(filterCustomerMap);
        StringBuilder sql = filterQueryGenerator(filterSet);
        if (sql != null && !sql.toString().equals(StringUtils.EMPTY)) {
            sql = sql.replace(0, 5, "AND");
        }
        return sql;
    }

    public StringBuilder gtnBalanceProductLookUp(java.util.Set<Container.Filter> filterSet) {
        queryMap.clear();
        queryMap.putAll(filterProductMap);
        StringBuilder sql = filterQueryGenerator(filterSet);
        if (sql != null && !sql.toString().equals(StringUtils.EMPTY)) {
            sql = sql.replace(0, 5, "AND");
        }
        return sql;
    }

    public StringBuilder filterQueryGenerator(java.util.Set<Container.Filter> filterSet) {

        StringBuilder str = new StringBuilder("AND ( * LIKE '?' OR * IS NULL )");
        StringBuilder sql = new StringBuilder();
        try {
            if (!filterSet.isEmpty()) {
                for (Container.Filter filter : filterSet) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        if (queryMap.get(stringFilter.getPropertyId().toString()) != null && !queryMap.get(stringFilter.getPropertyId().toString()).isEmpty()) {
                            if (sql.length() == 0) {
                                StringBuilder initial = new StringBuilder("where ( ( * LIKE '?' )");
                                StringBuilder temp = new StringBuilder(initial);
                                String filterStr = StringUtils.EMPTY;
                                if (stringFilter.getFilterString().contains("%")) {
                                    filterStr = "[" + stringFilter.getFilterString() + "]";
                                } else {
                                    filterStr = stringFilter.getFilterString();
                                }
                                String filterString = "%" + filterStr + "%";
                                temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, filterString);
                                sql.append(temp);
                            } else {
                                String filterStr = StringUtils.EMPTY;
                                if (stringFilter.getFilterString().contains("%")) {
                                    filterStr = "[" + stringFilter.getFilterString() + "]";
                                } else {
                                    filterStr = stringFilter.getFilterString();
                                }
                                 String filterString = "%" + filterStr + "%";
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
                        } else {
                        }
                    }

             
                }
                sql.append(")");
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return sql;
    }
    
    private void availableFilterMap() {

        availableFilterMap.put(Constants.CONTRACT_NO, "CN.CONTRACT_NO");
        availableFilterMap.put("contractName", "CN.CONTRACT_NAME");
        availableFilterMap.put("customerNo", "CM.COMPANY_NO");
        availableFilterMap.put("customerName", "CM.COMPANY_NAME");
        availableFilterMap.put("companyMasterSid", "CFPCD.COMPANY_MASTER_SID");
        availableFilterMap.put("contractMasterSid", "CN.CONTRACT_MASTER_SID");
    }
    
     private void selectedCustomerFilterMap() {
//         selectedCustomerFilterMap.put(Constants.CONTRACT_NO, "CONTRACT_MASTER_SID");
//         selectedCustomerFilterMap.put("contractName", "CONTRACT_NO");
//         selectedCustomerFilterMap.put("customerNo", "COMPANY_NO");
//         selectedCustomerFilterMap.put("customerName", "COMPANY_MASTER_SID");
//         selectedCustomerFilterMap.put("companyMasterSid", "COMPANY_NO");
//         selectedCustomerFilterMap.put("contractMasterSid", ", COMPANY_NAME");
         

        selectedCustomerFilterMap.put(Constants.CONTRACT_NO, "CONTRACT_NO");
        selectedCustomerFilterMap.put("contractName", "CONTRACT_NAME");
        selectedCustomerFilterMap.put("customerNo", "COMPANY_NO");
        selectedCustomerFilterMap.put("customerName", "COMPANY_NAME");
        selectedCustomerFilterMap.put("companyMasterSid", "COMPANY_MASTER_SID");
        selectedCustomerFilterMap.put("contractMasterSid", "CONTRACT_MASTER_SID");
    }
     
      private void selectedProductFilterMap() {
          selectedProductFilterMap.put("sub_ledgerCode", "NDC8");
          selectedProductFilterMap.put("productName", "ITEM_NAME");
          selectedProductFilterMap.put("brandName", "BRAND_NAME");
          selectedProductFilterMap.put("costCenter", "COMPANY_COST_CENTER");

    }
      
    private void availableProductFilterMap() {
        availableProductFilterMap.put("sub_ledgerCode", "IM.NDC8");
        availableProductFilterMap.put("productName", "IM.ITEM_NAME");
        availableProductFilterMap.put("costCenter", "GLC.COMPANY_COST_CENTER");
        availableProductFilterMap.put("brandName", "BR.BRAND_NAME");
    }  
    
    public StringBuilder getselectedProductFilter(java.util.Set<Container.Filter> filterSet) {
        queryMap.clear();
        queryMap.putAll(selectedProductFilterMap);
        StringBuilder sql = filterQueryGenerator(filterSet);
        if (sql != null && !sql.toString().equals(StringUtils.EMPTY)) {
            sql = sql.replace(0, 5, "AND");
        }
        return sql;
    }
     public StringBuilder getselectedCustomerFilter(java.util.Set<Container.Filter> filterSet) {
        queryMap.clear();
        queryMap.putAll(selectedCustomerFilterMap);
        StringBuilder sql = filterQueryGenerator(filterSet);
        if (sql != null && !sql.toString().equals(StringUtils.EMPTY)) {
            sql = sql.replace(0, 5, "AND");
        }
        return sql;
    }
     
      public StringBuilder getavailableFilter(java.util.Set<Container.Filter> filterSet) {
        queryMap.clear();
        queryMap.putAll(availableFilterMap);
        StringBuilder sql = filterQueryGenerator(filterSet);
        if (sql != null && !sql.toString().equals(StringUtils.EMPTY)) {
            sql = sql.replace(0, 5, "AND");
        }
        return sql;
    }
      
       public StringBuilder getavailableProductFilter(java.util.Set<Container.Filter> filterSet) {
        queryMap.clear();
        queryMap.putAll(availableProductFilterMap);
        StringBuilder sql = filterQueryGenerator(filterSet);
        if (sql != null && !sql.toString().equals(StringUtils.EMPTY)) {
            sql = sql.replace(0, 5, "AND");
        }
        return sql;
    }
}
