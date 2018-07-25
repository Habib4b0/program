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
import com.stpl.gtn.gtn2o.ws.module.adjustmentdetails.constants.GtnWsAdjusmtmentDetailsQueryConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
public class GtnWsAdjustmentTableLoadService {

    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    @Autowired
    private GtnWsAllListConfig gtnWebServiceAllListConfig;

    private Map<String, String> loadFiltermap = new HashMap<>();

    private boolean isReserve = true;

    public String loadResults(GtnUIFrameworkWebserviceRequest gtnWsRequest, boolean isCount) {
        String finalSql = StringUtils.EMPTY;
        try {
            GtnWsGeneralRequest generalRequest = gtnWsRequest.getGtnWsGeneralRequest();
            loadFiltermap = GtnWsAdjusmtmentDetailsQueryConstants.getColumnMap();
            String filter = getFilters(gtnWsRequest);
            String searchSql = isReserve ? gtnWsSqlService.getQuery("searchReserveAdjustmentDetails") : gtnWsSqlService.getQuery("searchGTNAdjustmentDetails");
            String selectSql = isCount ? gtnWsSqlService.getQuery("searchAdjustmentDetailsCount") : gtnWsSqlService.getQuery("searchAdjustmentDetailsData");
            finalSql = searchSql + "  " + selectSql;
            finalSql = finalSql.replace("@USER_ID", generalRequest.getUserId());
            finalSql = finalSql.replace("@SESSION_ID", generalRequest.getSessionId());
            Connection connection = gtnWebServiceAllListConfig.getSysSessionFactory().getSessionFactoryOptions().
                    getServiceRegistry().getService(ConnectionProvider.class).getConnection();
            finalSql = finalSql.replace("$SYS", connection.getCatalog());
            filter = filter + (isCount ? StringUtils.EMPTY : getOrderby(gtnWsRequest) + getOffset(gtnWsRequest));
            finalSql = finalSql + filter;
        } catch (SQLException ex) {
            Logger.getLogger(GtnWsAdjustmentTableLoadService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return finalSql;

    }

    private String getFilters(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
        List<GtnWebServiceSearchCriteria> filterList = gtnWsRequest.getGtnWsSearchRequest()
                .getGtnWebServiceSearchCriteriaList();
        StringBuilder filterBuilder = new StringBuilder(" WHERE ");
        ListIterator<GtnWebServiceSearchCriteria> namesIterator = filterList.listIterator();
        while (namesIterator.hasNext()) {
            GtnWebServiceSearchCriteria searchCriteria = namesIterator.next();
            if (GtnFrameworkCommonConstants.TRANSACTION_LEVEL.equals(searchCriteria.getFieldId())) {
                isReserve = searchCriteria.getFilterValue1().equals("Reserve Details");
                
            } else if (searchCriteria.getFilterValue1() != null && !searchCriteria.getFilterValue1().isEmpty()) {
                filterBuilder.append(" AND ").append(getCriteria(searchCriteria));
            }
        }
        return filterBuilder.toString().replace("WHERE  AND", " WHERE ");
    }

    private String getCriteria(GtnWebServiceSearchCriteria searchCriteria) {
        return new StringBuilder(loadFiltermap.get(searchCriteria.getFieldId())).append(" LIKE '")
                .append(searchCriteria.isFilter() ? "%" + searchCriteria.getFilterValue1() + "%" : searchCriteria.getFilterValue1().replace("*", "%")).append("'").toString();
    }

    private String getOrderby(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
        String appendString = gtnWsRequest.getGtnWsSearchRequest()
                .getGtnWebServiceOrderByCriteriaList().isEmpty() ? StringUtils.EMPTY : "  ORDER BY ";
        StringBuilder criteria = new StringBuilder(appendString);
        for (GtnWebServiceOrderByCriteria searchCriteria : gtnWsRequest.getGtnWsSearchRequest()
                .getGtnWebServiceOrderByCriteriaList()) {
            if (searchCriteria.getOrderByCriteria() != null && !searchCriteria.getOrderByCriteria().isEmpty()) {
                criteria.append(getOrder(searchCriteria)).append(" ").append(searchCriteria.getOrderByCriteria());
            }
        }
        return gtnWsRequest.getGtnWsSearchRequest()
                .getGtnWebServiceOrderByCriteriaList().isEmpty() ? " ORDER BY ADJUSTMENT_TYPE ASC " : criteria.toString();
    }

    private String getOrder(GtnWebServiceOrderByCriteria searchCriteria) {
        return loadFiltermap.get(searchCriteria.getPropertyId());
    }

    private String getOffset(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
        return " OFFSET " + gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart() + " ROWS FETCH NEXT " + gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset() + " ROWS ONLY";
    }

}
