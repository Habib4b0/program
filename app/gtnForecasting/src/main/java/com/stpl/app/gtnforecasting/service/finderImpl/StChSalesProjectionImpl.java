/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.service.finderImpl;

import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.Constants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Abishek.Ram
 */
public class StChSalesProjectionImpl  {
      private static final Logger LOGGER = LoggerFactory.getLogger(StChSalesProjectionImpl.class);
      public List executeQuery(Map<String, Object> parameters) {
    
    


        
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        if (parameters.get(Constants.INDICATOR) != null && "getListViewProductLevel".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            String query = SQlUtil.getQuery(getClass(),"getListViewProductLevel");
            Map<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (String key : inputs.keySet()) {
                query = query.replace(key, String.valueOf(inputs.get(key)));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null && "getListViewProductLevelCount".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            String query = SQlUtil.getQuery(getClass(),"getProdViewBrandNdcCount");
            Map<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (String key : inputs.keySet()) {
                query = query.replace(key, String.valueOf(inputs.get(key)));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null && "getListViewCustomerLevel".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            String query = SQlUtil.getQuery(getClass(),"getListViewCustomerLevel");
            Map<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (String key : inputs.keySet()) {
                query = query.replace(key, String.valueOf(inputs.get(key)));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null && "getListViewCustomerLevelCount".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            String query = SQlUtil.getQuery(getClass(),"getListViewCustomerLevelCount");
            Map<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (String key : inputs.keySet()) {
                query = query.replace(key, String.valueOf(inputs.get(key)));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null && "customerUnderProductView".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            String query = SQlUtil.getQuery(getClass(),"customerUnderProductView");
            Map<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (String key : inputs.keySet()) {
                query = query.replace(key, String.valueOf(inputs.get(key)));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null && "customerUnderProductViewCount".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            String query = SQlUtil.getQuery(getClass(),"customerUnderProductViewCount");
            Map<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (String key : inputs.keySet()) {
                query = query.replace(key, String.valueOf(inputs.get(key)));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null && Constant.GENERATE_SALES_PROJECTION.equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            parameters.put(Constants.QUERY_NAME, Constant.GENERATE_SALES_PROJECTION);
            queryString.append(generateQuery(parameters, true));
        } else if (parameters.get(Constants.INDICATOR) != null && Constant.GENERATE_SALES_PROJECTION_COUNT.equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            parameters.put(Constants.QUERY_NAME, Constant.GENERATE_SALES_PROJECTION_COUNT);
            String query = SQlUtil.getQuery(getClass(), Constant.GENERATE_SALES_PROJECTION_COUNT);
            Map<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (String key : inputs.keySet()) {
                query = query.replace(key, String.valueOf(inputs.get(key)));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null && "totalSales".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            String query = SQlUtil.getQuery(getClass(),"totalSales");
            Map<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            Map<String, Object> joinMap = (HashMap<String, Object>) parameters.get(Constants.JOIN_MAP);
            if (joinMap != null) {
                for (String key : joinMap.keySet()) {
                    query = query.replace(key, String.valueOf(joinMap.get(key)));
                }
            }
            for (String key : inputs.keySet()) {
                query = query.replace(key, String.valueOf(inputs.get(key)));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null
                && ("loadLevelForExpandCollapse".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))
                || "excelExport".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR))))) {
            String query = SQlUtil.getQuery(getClass(),String.valueOf(parameters.get("query")));
            Map<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (String key : inputs.keySet()) {
                query = query.replace(key, String.valueOf(inputs.get(key)));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null && "expandCollapseSalesProjection".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            parameters.put(Constants.QUERY_NAME, Constant.GENERATE_SALES_PROJECTION);
            queryString.append(generateQuery(parameters, true));
        } else if (parameters.get(Constants.INDICATOR) != null && Constant.PREPARE_PROCEDURE_CALL.equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            parameters.put(Constants.QUERY_NAME, Constant.PREPARE_PROCEDURE_CALL);
            queryString.append(generateQuery(parameters, true));
        } else if (parameters.get(Constants.INDICATOR) != null && Constant.SAVE_CHECK_RECORD.equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            parameters.put(Constants.QUERY_NAME, Constant.SAVE_CHECK_RECORD);
            queryString.append(generateQuery(parameters, true));
        } else if (parameters.get(Constants.INDICATOR) != null && "gettherapeuticclass".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            String query = SQlUtil.getQuery(getClass(),"gettherapeuticclass");
            String value = (String) parameters.get("input");
            query = query.replace("?", value);
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null && "checkHundredPercentage".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            parameters.put(Constants.QUERY_NAME, "checkHundredPercentage");
            queryString.append(generateQuery(parameters, true));
        } else if (parameters.get(Constants.INDICATOR) != null && Constant.UPDATE_RECORD.equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            parameters.put(Constants.QUERY_NAME, Constant.UPDATE_RECORD);
            queryString.append(generateQuery(parameters, true));
        } else if (parameters.get(Constants.INDICATOR) != null && "getSelectedBaseLine".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            parameters.put(Constants.QUERY_NAME, "getSelectedBaseLine");
            queryString.append(generateQuery(parameters, true));
        } else if (parameters.get(Constants.INDICATOR) != null && "checkSelectAll".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            String query = SQlUtil.getQuery(getClass(),"checkSelectAll");
            Map<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (String key : inputs.keySet()) {
                query = query.replace(key, String.valueOf(inputs.get(key)));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null && "getLevelName".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            String query = SQlUtil.getQuery(getClass(),"getLevelName");
            Map<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (String key : inputs.keySet()) {
                query = query.replace(key, String.valueOf(inputs.get(key)));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null && "isCheckAll".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            String query = SQlUtil.getQuery(getClass(),"isCheckAll");
            Map<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (String key : inputs.keySet()) {
                query = query.replace(key, String.valueOf(inputs.get(key)));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null && "generateDynamicSales".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            parameters.put(Constants.QUERY_NAME, "generateDynamicSales");
            queryString.append(generateQuery(parameters, true));
        } else if (parameters.get(Constants.INDICATOR) != null && "generateDynamicSalesCount".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            parameters.put(Constants.QUERY_NAME, "generateDynamicSalesCount");
            queryString.append(generateQuery(parameters, true));
        } else if (parameters.get(Constants.INDICATOR) != null && "generateCustomView".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            parameters.put(Constants.QUERY_NAME, "generateCustomView");
            queryString.append(generateQuery(parameters, true));
        } else if (parameters.get(Constants.INDICATOR) != null && "generateCustomViewCount".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            parameters.put(Constants.QUERY_NAME, "generateCustomViewCount");
            queryString.append(generateQuery(parameters, true));
        } else if (parameters.get(Constants.INDICATOR) != null && "getNDCLabel".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            parameters.put(Constants.QUERY_NAME, "getNDCLabel");
            queryString.append(generateQuery(parameters, true));
        } else if (parameters.get(Constants.INDICATOR) != null && Constant.UNCHECK_ALL.equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            parameters.put(Constants.QUERY_NAME, Constant.UNCHECK_ALL);
            queryString.append(generateQuery(parameters, true));
        }

        try {


            if (parameters.get(Constants.INDICATOR) != null) {
                if (Constant.SAVE_CHECK_RECORD.equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))
                        || Constant.UNCHECK_ALL.equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))
                        || Constant.PREPARE_PROCEDURE_CALL.equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))
                        || Constant.UPDATE_RECORD.equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
                    HelperTableLocalServiceUtil.executeUpdateQuery(queryString.toString());
                    return null;
                }
            }
            return HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());
        } catch (Exception ex) {
            LOGGER.error("{}, While assembling query for= {} " , ex.getMessage(), parameters.get(Constants.INDICATOR));
            LOGGER.error(queryString.toString());
            return null;
        } 
    }
    
     private String generateQuery(final Map<String, Object> parameters, final boolean joinAllowed) {
        String query = SQlUtil.getQuery(getClass(),String.valueOf(parameters.get(Constants.QUERY_NAME)));
        Map<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
        inputs.put("?MASTER_TABLE?", Constant.TABLE_NAME_BUNDLE.getString(Constants.ST_CH_SALES_PROJECTION_MASTER));
        inputs.put("?PROJECTION_TABLE?", Constant.TABLE_NAME_BUNDLE.getString(Constants.ST_CH_SALES_PROJECTION));
        inputs.put("?ACTUALS_TABLE?", Constant.TABLE_NAME_BUNDLE.getString(Constants.ST_CH_ACTUAL_SALES));
        if (joinAllowed) {
            Map<String, Object> joinMap = (HashMap<String, Object>) parameters.get(Constants.JOIN_MAP);
            if (joinMap != null) {
                for (String key : joinMap.keySet()) {
                    query = query.replace(key, String.valueOf(joinMap.get(key)));
                }
            }
        }
        for (String key : inputs.keySet()) {
            query = query.replace(key, String.valueOf(inputs.get(key)));
        }
        return query;
    }
    
}
