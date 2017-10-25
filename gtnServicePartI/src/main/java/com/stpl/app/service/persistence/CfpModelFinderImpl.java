package com.stpl.app.service.persistence;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.stpl.app.model.CfpModel;
import com.stpl.portal.kernel.dao.orm.SQLQuery;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;
import org.jboss.logging.Logger;

public class CfpModelFinderImpl extends BasePersistenceImpl<CfpModel> implements CfpModelFinder {
    private static final Logger LOGGER = Logger.getLogger(CfpModelFinderImpl.class);

    public List findCfpModelV1(Map<Object, Object> cfp, String orderByColumn, Boolean sortOrder, Object index, Object next, Map<Object, Object> parameters, String operation, Object future, Object future1) {

        Session session = null;
        session = openSession();
        StringBuilder sql = new StringBuilder();
        String andOperator = "";
        try {
            if ("searchResults".equals(operation)) {
                sql.append(CustomSQLUtil.get("cfpsearch"));

            } else if ("count".equals(operation)) {
                sql.append(CustomSQLUtil.get("cfpCount"));
            }
            sql.append(" where ");
            if (cfp.get("companyFamilyPlanId") != null) {
                String cfpId = cfp.get("companyFamilyPlanId").toString();
                sql.append(andOperator).append(" cfp.CFP_ID like '").append(cfpId).append("' ");
                andOperator = " AND ";
            }

            if (cfp.get("companyFamilyPlanNo") != null) {
                String cfpNo = cfp.get("companyFamilyPlanNo").toString();
                sql.append(andOperator).append(" cfp.CFP_NO like '").append(cfpNo).append("' ");
                andOperator = " AND ";

            }

            if (cfp.get("companyFamilyPlanName") != null) {
                String cfpName = cfp.get("companyFamilyPlanName").toString();
                sql.append(andOperator).append(" cfp.CFP_NAME like '").append(cfpName).append("' ");
                andOperator = " AND ";
            }
            if (cfp.get("companyId") != null && StringUtils.isNotBlank(String.valueOf(cfp.get("companyId")))) {
                String companyId = cfp.get("companyId").toString();
                sql.append(andOperator).append(" cm.COMPANY_ID like '").append(companyId).append("' ");
                andOperator = " AND ";
            }

            if (cfp.get("companyNo") != null && StringUtils.isNotBlank(String.valueOf(cfp.get("companyNo")))) {
                String companyNo = cfp.get("companyNo").toString();
                sql.append(andOperator).append(" cm.COMPANY_NO like '").append(companyNo).append("' ");
                andOperator = " AND ";

            }

            if (cfp.get("companyName") != null && StringUtils.isNotBlank(String.valueOf(cfp.get("companyName")))) {
                String companyName = cfp.get("companyName").toString();
                sql.append(andOperator).append(" cm.COMPANY_NAME like '").append(companyName).append("' ");
                andOperator = " AND ";
            }

            if (cfp.get("companyFamilyPlanStatus") != null && StringUtils.isNotBlank((String.valueOf(cfp.get("companyFamilyPlanStatus")))) && !String.valueOf(cfp.get("companyFamilyPlanStatus")).equals("0")) {
                int cfpStatus = Integer.valueOf(String.valueOf(cfp.get("companyFamilyPlanStatus")));
                sql.append(andOperator).append(" cfp.CFP_STATUS=").append(cfpStatus);
                andOperator = " AND ";

            }

            if (parameters.get("modifiedBy") != null && StringUtils.isNotBlank((String.valueOf(parameters.get("modifiedBy")))) && !String.valueOf(parameters.get("modifiedBy")).equals("0")) {
                int cfpStatus = Integer.valueOf(String.valueOf(parameters.get("modifiedBy")));
                sql.append(andOperator).append(" cfp.MODIFIED_BY=").append(cfpStatus);
                andOperator = " AND ";

            }

            if (parameters.get("createdBy") != null && StringUtils.isNotBlank((String.valueOf(parameters.get("createdBy")))) && !String.valueOf(parameters.get("createdBy")).equals("0")) {
                int cfpStatus = Integer.valueOf(String.valueOf(parameters.get("createdBy")));
                sql.append(andOperator).append(" cfp.CREATED_BY=").append(cfpStatus);
                andOperator = " AND ";

            }

            if (cfp.get("companyFamilyPlanType") != null && StringUtils.isNotBlank((String.valueOf(cfp.get("companyFamilyPlanType")))) && !String.valueOf(cfp.get("companyFamilyPlanType")).equals("0")) {
                int cfpType = Integer.valueOf(String.valueOf(cfp.get("companyFamilyPlanType")));
                sql.append(andOperator).append(" cfp.CFP_TYPE=").append(cfpType);
                andOperator = " AND ";

            }

            sql.append(andOperator).append("cfp.INBOUND_STATUS <> 'D' ");
            andOperator = andOperator.equals(" AND ") ? andOperator : andOperator.equals("") ? " AND " : StringUtils.EMPTY;
            if (parameters.get("systemId") != null && StringUtils.isNotBlank(String.valueOf(parameters.get("systemId")))) {
                int cfpNo = Integer.valueOf(String.valueOf(parameters.get("systemId")));
                sql.append(andOperator).append(" cfp.CFP_MODEL_SID =").append(cfpNo);
                andOperator = " AND ";
            }

            if (parameters.get("cfpNo") != null) {
                String cfpNo = parameters.get("cfpNo").toString();
                sql.append(andOperator).append(" cfp.CFP_NO like '").append(cfpNo).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("cfpId") != null) {
                String cfpId = parameters.get("cfpId").toString();
                sql.append(andOperator).append(" cfp.CFP_ID like '").append(cfpId).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("cfpName") != null) {
                String cfpName = parameters.get("cfpName").toString();
                sql.append(andOperator).append(" cfp.CFP_NAME like '").append(cfpName).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("cfpDesignation") != null) {
                String cfpDesignation = parameters.get("cfpDesignation").toString();
                sql.append(andOperator).append(" cfp.CFP_DESIGNATION like '").append(cfpDesignation).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("parentCfpName") != null) {
                String cfpParentName = parameters.get("parentCfpName").toString();
                sql.append(andOperator).append(" parenCfp.CFP_NO like '").append(cfpParentName).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("parentCfpId") != null) {
                String cfpParentId = parameters.get("parentCfpId").toString();
                sql.append(andOperator).append(" parenCfp.CFP_ID like '").append(cfpParentId).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("cfpStatus") != null && !String.valueOf(parameters.get("cfpStatus")).equals("0")) {
                String cfpStatus = parameters.get("cfpStatus").toString();
                sql.append(andOperator).append(" cfp.CFP_STATUS like '").append(cfpStatus).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("cfpType") != null && !String.valueOf(parameters.get("cfpType")).equals("0")) {
                String cfpType = parameters.get("cfpType").toString();
                sql.append(andOperator).append(" cfp.CFP_TYPE like '").append(cfpType).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("cfpTradeClass") != null) {
                String cfpTradeClass = parameters.get("cfpTradeClass").toString();
                sql.append(andOperator).append(" cfp.CFP_TRADE_CLASS like '").append(cfpTradeClass).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("cfpCategory") != null) {
                String cfpCategory = parameters.get("cfpCategory").toString();
                sql.append(andOperator).append(" cfp.CFP_CATEGORY like '").append(cfpCategory).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("cfpStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("cfpStartDatefrom"))))) {
                String startDate = parameters.get("cfpStartDatefrom").toString();
                sql.append(andOperator).append(" cfp.CFP_START_DATE >= '").append(startDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("cfpStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("cfpStartDateto"))))) {
                String startDate = parameters.get("cfpStartDateto").toString();
                sql.append(andOperator).append(" cfp.CFP_START_DATE <= '").append(startDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("cfpEndDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("cfpEndDatefrom"))))) {
                String endDate = parameters.get("cfpEndDatefrom").toString();
                sql.append(andOperator).append(" cfp.CFP_END_DATE >= '").append(endDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("cfpEndDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("cfpEndDateto"))))) {
                String endDate = parameters.get("cfpEndDateto").toString();
                sql.append(andOperator).append(" cfp.CFP_END_DATE <= '").append(endDate).append("' ");
                andOperator = " AND ";
            }

            if ((parameters.get("modifiedDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("modifiedDatefrom"))))) {
                String endDate = parameters.get("modifiedDatefrom").toString();
                sql.append(andOperator).append(" cfp.MODIFIED_DATE >= '").append(endDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("modifiedDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("modifiedDateto"))))) {
                String endDate = parameters.get("modifiedDateto").toString();
                sql.append(andOperator).append(" cfp.MODIFIED_DATE <= '").append(endDate).append("' ");
                andOperator = " AND ";
            }

            if ((parameters.get("createdDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("createdDatefrom"))))) {
                String endDate = parameters.get("createdDatefrom").toString();
                sql.append(andOperator).append(" cfp.CREATED_DATE >= '").append(endDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("createdDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("createdDateto"))))) {
                String endDate = parameters.get("createdDateto").toString();
                sql.append(andOperator).append(" cfp.CREATED_DATE <= '").append(endDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("cfpcreatedBy") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("cfpcreatedBy"))))) {
                String cfpcreatedBy = parameters.get("cfpcreatedBy").toString();
                sql.append(andOperator).append(" cfp.CREATED_BY = ").append(cfpcreatedBy);
                andOperator = " AND ";
            }
            if ((parameters.get("cfpmodifiedBy") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("cfpmodifiedBy"))))) {
                String cfpmodifiedBy = parameters.get("cfpmodifiedBy").toString();
                sql.append(andOperator).append(" cfp.MODIFIED_BY = ").append(cfpmodifiedBy);
                andOperator = " AND ";
            }
           
            if ("searchResults".equals(operation)) {
                if (index != null && next != null) {

                    if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
                        sql.append(" ORDER BY CFP_ID").append((!sortOrder) ? " ASC " : " DESC ");
                    } else {
                        sql.append("ORDER BY ").append(orderByColumn).append((!sortOrder) ? " ASC " : " DESC ");
                    }

                    sql.append(" " + "OFFSET ");
                    sql.append(Integer.valueOf(String.valueOf(index)));
                    sql.append(" ROWS FETCH NEXT ");
                    sql.append(Integer.valueOf(String.valueOf(next)));
                    sql.append(" ROWS ONLY;");
                }
            }
            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(sql.toString());
            return null;
        }finally {
            closeSession(session);
        }
    }

}
