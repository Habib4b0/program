/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.adjustmentdetails.service;

import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sathya.Seelan
 */
@Component
@Scope(value = "singleton")
public class GtnWsAdjustmentTableLoadService {

    private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsAdjustmentTableLoadService.class);
    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    @Autowired
    private GtnWsAllListConfig gtnWebServiceAllListConfig;

    private Map<String, String> loadFiltermap = new HashMap<>();

    public String loadResults(GtnUIFrameworkWebserviceRequest gtnWsRequest, boolean isCount) {
        String finalSql = StringUtils.EMPTY;
        try {
            GtnWsGeneralRequest generalRequest = gtnWsRequest.getGtnWsGeneralRequest();
            String searchSql = gtnWsSqlService.getQuery("searchAdjustmentDetails");
            String selectSql = isCount ? gtnWsSqlService.getQuery("searchAdjustmentDetailsCount") : gtnWsSqlService.getQuery("searchAdjustmentDetailsData");
            finalSql = searchSql + "  " + selectSql;
            finalSql = finalSql.replace("@USER_ID", generalRequest.getUserId());
            finalSql = finalSql.replace("@SESSION_ID", generalRequest.getSessionId());
            Connection connection = gtnWebServiceAllListConfig.getSysSessionFactory().getSessionFactoryOptions().
                    getServiceRegistry().getService(ConnectionProvider.class).getConnection();
            finalSql = finalSql.replace("$SYS", connection.getCatalog());
            getColumnMap();
            String filter = getFilters(gtnWsRequest) + getOrderby(gtnWsRequest) + getOffset(gtnWsRequest);
            finalSql = finalSql + filter;
        } catch (SQLException ex) {
            Logger.getLogger(GtnWsAdjustmentTableLoadService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return finalSql;

    }

    private String getFilters(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
        String appendString = gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty() ? StringUtils.EMPTY : " WHERE ";
        StringBuilder filterBuilder = new StringBuilder(appendString);
        for (GtnWebServiceSearchCriteria searchCriteria : gtnWsRequest.getGtnWsSearchRequest()
                .getGtnWebServiceSearchCriteriaList()) {
            if (searchCriteria.getFilterValue1() != null && !searchCriteria.getFilterValue1().isEmpty()) {
                filterBuilder.append(" AND ").append(getCriteria(searchCriteria));
            }
        }
        return filterBuilder.toString().replace("WHERE  AND", " WHERE ");
    }

    public void getColumnMap() {
        if (loadFiltermap.isEmpty()) {
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_COMPANY, "COMPANY");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_DIVISION, "DIVISON");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_COST_CENTER, "COST_CENTER");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_ACCOUNT, "ACCOUNT");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_BRAND, "BRAND");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_PROJECT, "PROJECT");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_FUTURE_1, "FUTURE_1");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_FUTURE_2, "FUTURE_2");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_DEBIT, "DEBIT");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_CREDIT, "CREDIT");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_LINE_DESCRIPTION, "LINE_DESCRIPTION");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_BALANCE_TYPE, "BALANCE_TYPE");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_DATABASE, "ARD_DB");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_DATA_ACCESS_SET, "DATA_ACCESS_SET");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_CHART_OF_ACCOUNTS, "CHART_OF_ACCOUNTS");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_LEDGER, "LEDGER");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_CATEGORY, "CATEGORY");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_SOURCE, "SOURCE");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_CURRENCY, "CURRENCY");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_ACCOUNTING_DATE, "ACCOUNTING_DATE");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_BATCH_NAME, "BATCH_NAME");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_JOURNAL_NAME, "JOURNAL_NAME");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_JOURNAL_DESCRIPTION, "JOURNAL_DESCRIPTION");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_REVERSE_JOURNAL, "REVERSE_JOURNAL");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_REVERSAL_PERIOD_DATE, "REVERSAL_PERIOD_DATE");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_BUSINESS_UNIT, "BUSINESS_UNIT");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_ADJUSTMENT_TYPE, "ADJUSTMENT_TYPE");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_ADJUSTMENT_LEVEL, "ADJUSTMENT_LEVEL");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_ACCOUNT_CATEGORY, "ACCOUNT_CATEGORY");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_ACCOUNT_TYPE, "ACCOUNT_TYPE");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_ACCOUNT_DESCRIPTION, "ACCOUNT_DESCRIPTION");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_ACCOUNT_INDICATOR, "ACCOUNT_INDICATOR");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_UDC_1, "UDC_1");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_UDC_2, "UDC_2");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_UDC_3, "UDC_3");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_UDC_4, "UDC_4");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_UDC_5, "UDC_5");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_UDC_6, "UDC_6");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_REDEMPTION_PERIOD, "REDEMPTION_PERIOD");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_CALCULATION_PERIOD, "CALCULATION_PERIOD");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_POSTING_INDICATOR, "POSTING_INDICATOR");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_WORKFLOW_ID, "WORKFLOW_ID");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_WORKFLOW_NAME, "WORKFLOW_NAME");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_WORKFLOW_CREATED_BY, "WORKFLOW_CREATED_BY");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_WORKFLOW_CREATED_DATE, "WORKFLOW_CREATED_DATE");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_WORKFLOW_APPROVED_BY, "WORKFLOW_APPROVED_BY");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_WORKFLOW_APPROVED__DATE, "WORKFLOW_APPROVED_DATE");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_BATCH_ID, "BATCH_ID");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_ORIGINAL_BATCH_ID, "ORIGINAL_BATCH_ID");
            loadFiltermap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_ADJUSTMENT_RESERVE_DETAIL_SID, "ADJUSTMENT_RESERVE_DETAIL_SID");
        }
    }

    private String getCriteria(GtnWebServiceSearchCriteria searchCriteria) {
        return new StringBuilder(loadFiltermap.get(searchCriteria.getFieldId())).append(" LIKE '")
                .append(searchCriteria.isFilter() ? "%" + searchCriteria.getFilterValue1() + "%" : searchCriteria.getFilterValue1().replace("*", "%")).append("'").toString();
    }

    private String getOrderby(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
        String appendString = " ORDER BY ";
        StringBuilder criteria = new StringBuilder(appendString);
        for (GtnWebServiceOrderByCriteria searchCriteria : gtnWsRequest.getGtnWsSearchRequest()
                .getGtnWebServiceOrderByCriteriaList()) {
            if (searchCriteria.getOrderByCriteria() != null && !searchCriteria.getOrderByCriteria().isEmpty()) {
                criteria.append(" , ").append(getOrder(searchCriteria));
            }
        }
        return criteria.toString();
    }

    private String getOrder(GtnWebServiceOrderByCriteria searchCriteria) {
        return "";
    }

    private String getOffset(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
        return "";
    }

}
