/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.service.finderimpl;

import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceutils.Constants;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Abishek.Ram
 */
public class StChSalesProjectionImpl  {
      private static final Logger LOGGER = LoggerFactory.getLogger(StChSalesProjectionImpl.class);
      public List executeQuery(Map<String, Object> parameters) {
    
    


        
        StringBuilder queryString = new StringBuilder();
        if (parameters.get(Constants.INDICATOR) != null && "getListViewProductLevel".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            String query = SQlUtil.getQuery(getClass(),"getListViewProductLevel");
            HashMap<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (Map.Entry<String, Object> key : inputs.entrySet()) {
                query = query.replace(key.getKey(), String.valueOf(key.getValue()));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null && "getListViewProductLevelCount".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            String query = SQlUtil.getQuery(getClass(),"getProdViewBrandNdcCount");
            HashMap<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (Map.Entry<String, Object> key : inputs.entrySet()) {
                query = query.replace(key.getKey(), String.valueOf(key.getValue()));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null && "getListViewCustomerLevel".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            String query = SQlUtil.getQuery(getClass(),"getListViewCustomerLevel");
            HashMap<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (Map.Entry<String, Object> key : inputs.entrySet()) {
                query = query.replace(key.getKey(), String.valueOf(key.getValue()));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null && "getListViewCustomerLevelCount".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            String query = SQlUtil.getQuery(getClass(),"getListViewCustomerLevelCount");
            HashMap<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (Map.Entry<String, Object> key : inputs.entrySet()) {
                query = query.replace(key.getKey(), String.valueOf(key.getValue()));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null && "customerUnderProductView".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            String query = SQlUtil.getQuery(getClass(),"customerUnderProductView");
            HashMap<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (Map.Entry<String, Object> key : inputs.entrySet()) {
                query = query.replace(key.getKey(), String.valueOf(key.getValue()));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null && "customerUnderProductViewCount".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            String query = SQlUtil.getQuery(getClass(),"customerUnderProductViewCount");
            HashMap<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (Map.Entry<String, Object> key : inputs.entrySet()) {
                query = query.replace(key.getKey(), String.valueOf(key.getValue()));
            }
            queryString.append(query);
        } else if ((parameters.get(Constants.INDICATOR) != null && Constant.GENERATE_SALES_PROJECTION.equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR))))
                || (parameters.get(Constants.INDICATOR) != null && "expandCollapseSalesProjection".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR))))) {
            parameters.put(Constants.QUERY_NAME, Constant.GENERATE_SALES_PROJECTION);
            queryString.append(generateQuery(parameters, true));
        } else if (parameters.get(Constants.INDICATOR) != null && Constant.GENERATE_SALES_PROJECTION_COUNT.equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            parameters.put(Constants.QUERY_NAME, Constant.GENERATE_SALES_PROJECTION_COUNT);
            String query = SQlUtil.getQuery(getClass(), Constant.GENERATE_SALES_PROJECTION_COUNT);
            HashMap<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (Map.Entry<String, Object> key : inputs.entrySet()) {
                query = query.replace(key.getKey(), String.valueOf(key.getValue()));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null && "totalSales".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            String query = SQlUtil.getQuery(getClass(),"totalSales");
            HashMap<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            HashMap<String, Object> joinMap = (HashMap<String, Object>) parameters.get(Constants.JOIN_MAP);
            if (joinMap != null) {
                for (Map.Entry<String, Object> key : joinMap.entrySet()) {
                    query = query.replace(key.getKey(), String.valueOf(key.getValue()));
                }
            }
            for (Map.Entry<String, Object> key : inputs.entrySet()) {
                query = query.replace(key.getKey(), String.valueOf(key.getValue()));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null
                && ("loadLevelForExpandCollapse".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))
                || "excelExport".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR))))) {
            String query = SQlUtil.getQuery(getClass(),String.valueOf(parameters.get("query")));
            HashMap<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (Map.Entry<String, Object> key : inputs.entrySet()) {
                query = query.replace(key.getKey(), String.valueOf(key.getValue()));
            }
            queryString.append(query);
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
            HashMap<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (Map.Entry<String, Object> key : inputs.entrySet()) {
                query = query.replace(key.getKey(), String.valueOf(key.getValue()));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null && "getLevelName".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            String query = SQlUtil.getQuery(getClass(),"getLevelName");
            HashMap<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (Map.Entry<String, Object> key : inputs.entrySet()) {
                query = query.replace(key.getKey(), String.valueOf(key.getValue()));
            }
            queryString.append(query);
        } else if (parameters.get(Constants.INDICATOR) != null && "isCheckAll".equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))) {
            String query = SQlUtil.getQuery(getClass(),"isCheckAll");
            HashMap<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
            for (Map.Entry<String, Object> key : inputs.entrySet()) {
                query = query.replace(key.getKey(), String.valueOf(key.getValue()));
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


                if (parameters.get(Constants.INDICATOR) != null && (Constant.SAVE_CHECK_RECORD.equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))
                        || Constant.UNCHECK_ALL.equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))
                        || Constant.PREPARE_PROCEDURE_CALL.equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR)))
                        || Constant.UPDATE_RECORD.equalsIgnoreCase(String.valueOf(parameters.get(Constants.INDICATOR))))) {
                    HelperTableLocalServiceUtil.executeUpdateQuery(queryString.toString());
                    return Collections.emptyList();
                }
            return HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());
        } catch (Exception ex) {
            LOGGER.error("{}, While assembling query for= {} " , ex.getMessage(), parameters.get(Constants.INDICATOR));
            LOGGER.error(queryString.toString());
            return Collections.emptyList();
        } 
    }
    
     private String generateQuery(final Map<String, Object> parameters, final boolean joinAllowed) {
        String query = SQlUtil.getQuery(getClass(),String.valueOf(parameters.get(Constants.QUERY_NAME)));
        HashMap<String, Object> inputs = (HashMap<String, Object>) parameters.get(Constants.INPUT_MAP);
        inputs.put("?MASTER_TABLE?", Constant.TABLE_NAME_BUNDLE.getString(Constants.ST_CH_SALES_PROJECTION_MASTER));
        inputs.put("?PROJECTION_TABLE?", Constant.TABLE_NAME_BUNDLE.getString(Constants.ST_CH_SALES_PROJECTION));
        inputs.put("?ACTUALS_TABLE?", Constant.TABLE_NAME_BUNDLE.getString(Constants.ST_CH_ACTUAL_SALES));
        if (joinAllowed) {
            HashMap<String, Object> joinMap = (HashMap<String, Object>) parameters.get(Constants.JOIN_MAP);
            if (joinMap != null) {
                for (Map.Entry<String, Object> key : joinMap.entrySet()) {
                    query = query.replace(key.getKey(), String.valueOf(key.getValue()));
                }
            }
        }
        for (Map.Entry<String, Object> key : inputs.entrySet()) {
            query = query.replace(key.getKey(), String.valueOf(key.getValue()));
        }
        return query;
    }
    
}
