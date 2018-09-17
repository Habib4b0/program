/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.service.finderImpl;

import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.CommonUtils;
import com.stpl.app.serviceUtils.Constants;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.util.converters.DataTypeConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Abishek.Ram
 */
public class ProjectionMasterImpl {
public static final Logger LOGGER = LoggerFactory.getLogger(ProjectionMasterImpl.class);
    public List searchDsProjection(Map<String, Object> parameters) {
        String accProjJoin = "@ACCRUALPROJJOIN";
        String accFieldValue = "@ACCFIELDVALUE";
        String filterValue = "@WHEREFILTERVALUE";
        StringBuilder queryString = new StringBuilder();
        try {
            LOGGER.debug("Entering searchDsProjection method");
            if ("Returns".equals(parameters.get(Constant.MODULE_NAME))) {
                if (parameters.get(Constant.LAZY_LOAD_RESULTS) != null && Constant.LAZY_LOAD_RESULTS.equalsIgnoreCase(String.valueOf(parameters.get(Constant.LAZY_LOAD_RESULTS)))) {
                    queryString.append(SQlUtil.getQuery(getClass(),"searchProjectionForReturns"));
                    queryString.append(Constant.AND_PMFORECASTING_TYPE_LIKE ).append( String.valueOf(parameters.get(Constant.MODULE_NAME)) ).append( '\'');
                } else {
                    queryString.append(SQlUtil.getQuery(getClass(),"searchProjectionCountForReturns"));
                    queryString.append(Constant.AND_PMFORECASTING_TYPE_LIKE ).append( String.valueOf(parameters.get(Constant.MODULE_NAME)) ).append( '\'');
                }
            } else if (parameters.get(Constant.LAZY_LOAD_RESULTS) != null && Constant.LAZY_LOAD_RESULTS.equalsIgnoreCase(String.valueOf(parameters.get(Constant.LAZY_LOAD_RESULTS)))) {
                queryString.append(SQlUtil.getQuery(getClass(),"searchProjection"));
                queryString.replace(queryString.indexOf(accFieldValue), queryString.indexOf(accFieldValue) + accFieldValue.length(), String.valueOf(parameters.get("selectValue")));
                queryString.replace(queryString.indexOf(accProjJoin), queryString.indexOf(accProjJoin) + accProjJoin.length(), String.valueOf(parameters.get("leftJoinValue")));
                queryString.replace(queryString.indexOf(filterValue), queryString.indexOf(filterValue) + filterValue.length(), String.valueOf(parameters.get("whereFilterValue")));
                queryString.append(Constant.AND_PMFORECASTING_TYPE_LIKE ).append( String.valueOf(parameters.get(Constant.MODULE_NAME)) ).append( '\'');

            } else {
                queryString.append(SQlUtil.getQuery(HelperTableLocalServiceUtil.class,"searchProjectionCount"));
                queryString.replace(queryString.indexOf(accProjJoin), queryString.indexOf(accProjJoin) + accProjJoin.length(), String.valueOf(parameters.get("leftJoinValue")));
                queryString.replace(queryString.indexOf(filterValue), queryString.indexOf(filterValue) + filterValue.length(), String.valueOf(parameters.get("whereFilterValue")));
                queryString.append(Constant.AND_PMFORECASTING_TYPE_LIKE ).append( String.valueOf(parameters.get(Constant.MODULE_NAME)) ).append( '\'');
            }

            if (parameters.get("projectionDescription") != null) {
                queryString.append(Constant.AND_PMPROJECTION_DESCRIPTION_LIKE);
                queryString.append(String.valueOf(parameters.get("projectionDescription")));
                queryString.append("' ");
            }
            if (parameters.get("projectionName") != null) {
                queryString.append(" AND PM.PROJECTION_NAME like '");
                queryString.append(String.valueOf(parameters.get("projectionName")));
                queryString.append("' ");
            }
            if (parameters.get(Constant.CUSTOMER_HIERARCHY_SID) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.CUSTOMER_HIERARCHY_SID)))) {
                queryString.append(" AND PM.Constant.CUSTOMER_HIERARCHY_SID like '");
                queryString.append(String.valueOf(parameters.get(Constant.CUSTOMER_HIERARCHY_SID)).trim());
                queryString.append("' ");
            }
            if (parameters.get(Constant.PROD_HIERARCHY_SID) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.PROD_HIERARCHY_SID)))) {
                queryString.append(" AND PM.PRODUCT_HIERARCHY_SID like '");
                queryString.append(String.valueOf(parameters.get(Constant.PROD_HIERARCHY_SID)));
                queryString.append("' ");
            }
            if (parameters.get(Constant.CUSTOMER_GROUP_SID) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.CUSTOMER_GROUP_SID)))) {
                queryString.append(" AND PM.COMPANY_GROUP_SID like '");
                queryString.append(String.valueOf(parameters.get(Constant.CUSTOMER_GROUP_SID)));
                queryString.append("' ");
            }
            if (parameters.get(Constant.PROD_GROUP_SID) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.PROD_GROUP_SID)))) {
                queryString.append(" AND PM.ITEM_GROUP_SID like '");
                queryString.append(String.valueOf(parameters.get(Constant.PROD_GROUP_SID)));
                queryString.append("' ");
            }
            if (parameters.get(Constant.COMPANY_MASTER_SID) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.COMPANY_MASTER_SID)))) {
                queryString.append(" AND PM.COMPANY_MASTER_SID like '");
                queryString.append(String.valueOf(parameters.get(Constant.COMPANY_MASTER_SID)));
                queryString.append("' ");
            }
            if (parameters.get(Constant.BUSINESS_UNIT) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.BUSINESS_UNIT))) && !ConstantsUtils.ZERO.equals(String.valueOf(parameters.get(Constant.BUSINESS_UNIT)))) {
                queryString.append(" AND PM.BUSINESS_UNIT = ");
                queryString.append(String.valueOf(parameters.get(Constant.BUSINESS_UNIT)));
                queryString.append(' ');
            }
            if (parameters.get(Constant.COMPANY_SID) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.COMPANY_SID))) && !ConstantsUtils.ZERO.equals(String.valueOf(parameters.get(Constant.COMPANY_SID)))) {
                queryString.append(" AND PM.COMPANY_MASTER_SID = ");
                queryString.append(String.valueOf(parameters.get(Constant.COMPANY_SID)));
                queryString.append(' ');
            }
            if (Constant.ACCRUAL_RATE_PROJECTION.equals(parameters.get(Constant.MODULE_NAME))) {

                if (parameters.get(Constant.DEDUCTION_LEVEL) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.DEDUCTION_LEVEL)))) {
                    queryString.append(" AND APS.FIELD_NAME like '");
                    queryString.append(String.valueOf(parameters.get(Constant.DEDUCTION_LEVEL)));
                    queryString.append("' ");
                }
                if (parameters.get(Constant.DEDUCTION_VALUE) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.DEDUCTION_VALUE)))) {
                    queryString.append(" AND APS.FIELD_VALUES like '");
                    queryString.append(String.valueOf(parameters.get(Constant.DEDUCTION_VALUE)));
                    queryString.append("' ");
                }

            }

            if (!"Returns".equals(parameters.get(Constant.MODULE_NAME))) {
                if (parameters.get("selectedCustomerRelationSid") != null) {
                    String replaceMe = "JOINPROJECTIONCUSTHIERARCHY";
                    ArrayList<String> selectedCustomerRelationSid = (ArrayList<String>) parameters.get("selectedCustomerRelationSid");
                    if (!selectedCustomerRelationSid.isEmpty()) {
                        queryString.replace(queryString.indexOf(replaceMe), queryString.indexOf(replaceMe) + replaceMe.length(), " JOIN PROJECTION_CUST_HIERARCHY PCH ON PCH.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID ");
                        queryString.append(" AND PCH.RELATIONSHIP_LEVEL_SID in (");

                        for (int loop = 0, limit = selectedCustomerRelationSid.size(); loop < limit; loop++) {
                            queryString.append('\'');
                            queryString.append(selectedCustomerRelationSid.get(loop));
                            queryString.append('\'');
                            if (loop != (limit - 1)) {
                                queryString.append(", ");
                            }
                        }
                        queryString.append(") ");

                    } else {
                        queryString.replace(queryString.indexOf(replaceMe), queryString.indexOf(replaceMe) + replaceMe.length(), StringUtils.EMPTY);
                    }
                } else {
                    String replaceMe = "JOINPROJECTIONCUSTHIERARCHY";
                    queryString.replace(queryString.indexOf(replaceMe), queryString.indexOf(replaceMe) + replaceMe.length(), StringUtils.EMPTY);
                }
            }

            if (parameters.get("selectedProductRelationSid") != null) {
                String replaceMe = "JOINPROJECTIONPRODHIERARCHY";
                ArrayList<String> selectedProductRelationSid = (ArrayList<String>) parameters.get("selectedProductRelationSid");
                if (!selectedProductRelationSid.isEmpty()) {
                    queryString.replace(queryString.indexOf(replaceMe), queryString.indexOf(replaceMe) + replaceMe.length(), " JOIN PROJECTION_PROD_HIERARCHY PPH ON PPH.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID ");
                    queryString.append(" AND PPH.RELATIONSHIP_LEVEL_SID in (");

                    for (int loop = 0, limit = selectedProductRelationSid.size(); loop < limit; loop++) {
                        queryString.append('\'');
                        queryString.append(selectedProductRelationSid.get(loop));
                        queryString.append('\'');
                        if (loop != (limit - 1)) {
                            queryString.append(", ");
                        }
                    }
                    queryString.append(") ");

                } else {
                    queryString.replace(queryString.indexOf(replaceMe), queryString.indexOf(replaceMe) + replaceMe.length(), StringUtils.EMPTY);
                }
            } else {
                String replaceMe = "JOINPROJECTIONPRODHIERARCHY";
                queryString.replace(queryString.indexOf(replaceMe), queryString.indexOf(replaceMe) + replaceMe.length(), StringUtils.EMPTY);
            }
            if (parameters.get(Constant.DISCOUNT_SID) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.DISCOUNT_SID)))) {
                queryString.append(" AND PM.DISCOUNT_TYPE like '");
                queryString.append(String.valueOf(parameters.get(Constant.DISCOUNT_SID)));
                queryString.append("' ");
            }
            if ((parameters.get(Constant.FROM_DATE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(Constant.FROM_DATE))) && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FROM_DATE))))
                    && (parameters.get(Constant.TO_DATE) == null || StringUtils.EMPTY.equals(String.valueOf(parameters.get(Constant.TO_DATE))) || ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.TO_DATE))))) {
                SimpleDateFormat format2 = new SimpleDateFormat(Constant.MM_DD_YYYY);
                queryString.append(" AND PM.CREATED_DATE >= '");
                queryString.append(format2.format(format2.parse(String.valueOf(parameters.get(Constant.FROM_DATE)))));
                queryString.append("' ");
            } else if ((parameters.get(Constant.TO_DATE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(Constant.TO_DATE))) && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.TO_DATE))))
                    && (parameters.get(Constant.FROM_DATE) == null || StringUtils.EMPTY.equals(String.valueOf(parameters.get(Constant.FROM_DATE))) || ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FROM_DATE))))) {
                SimpleDateFormat format2 = new SimpleDateFormat(Constant.MM_DD_YYYY);
                queryString.append(" AND PM.CREATED_DATE <= '");
                queryString.append(format2.format(format2.parse(String.valueOf(parameters.get(Constant.TO_DATE)))));
                queryString.append("' ");
            } else if ((parameters.get(Constant.FROM_DATE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(Constant.FROM_DATE))) && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FROM_DATE))))
                    && (parameters.get(Constant.TO_DATE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(Constant.TO_DATE))) && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.TO_DATE))))) {
                SimpleDateFormat format2 = new SimpleDateFormat(Constant.MM_DD_YYYY);
                queryString.append(Constant.AND_PMCREATED_DATE_BETWEEN);
                queryString.append(format2.format(format2.parse(String.valueOf(parameters.get(Constant.FROM_DATE)))));
                queryString.append(Constant.UNDERSCORE_AND_UNDERSCORE);
                queryString.append(format2.format(format2.parse(String.valueOf(parameters.get(Constant.TO_DATE)))));
                queryString.append("' ");
            }
            if (parameters.get(Constant.FILTER_PROJECTION_NAME) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_PROJECTION_NAME))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_PROJECTION_NAME)))) {
                queryString.append(" AND PM.PROJECTION_NAME like '");
                queryString.append(String.valueOf(parameters.get(Constant.FILTER_PROJECTION_NAME)));
                queryString.append("' ");
            }

            if (parameters.get(Constant.FILTER_DESCRIPTION) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_DESCRIPTION))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_DESCRIPTION)))) {
                queryString.append(Constant.AND_PMPROJECTION_DESCRIPTION_LIKE);
                queryString.append(String.valueOf(parameters.get(Constant.FILTER_DESCRIPTION)));
                queryString.append("' ");
            }

            if (parameters.get(Constant.FILTER_CUSTOMER_HIERARCHY_LEVEL) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_HIERARCHY_LEVEL))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_HIERARCHY_LEVEL)))) {
                queryString.append(" AND PM.CUSTOMER_HIERARCHY_LEVEL like '");
                queryString.append(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_HIERARCHY_LEVEL)));
                queryString.append("' ");
            }

            if (parameters.get(Constant.FILTER_CUSTOMER_HIERARCHY) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_HIERARCHY))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_HIERARCHY)))) {
                queryString.append(" AND HDC.HIERARCHY_NAME like '");
                queryString.append(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_HIERARCHY)));
                queryString.append("' ");
            }

            if (parameters.get(Constant.FILTER_PRODUCT_HIERARCHY) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_HIERARCHY))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_HIERARCHY)))) {
                queryString.append(" AND HDP.HIERARCHY_NAME like '");
                queryString.append(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_HIERARCHY)));
                queryString.append("' ");
            }

            if (parameters.get(Constant.FILTER_PRODUCT_HIERARCHY_LEVEL) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_HIERARCHY_LEVEL))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_HIERARCHY_LEVEL)))) {
                queryString.append(" AND PM.PRODUCT_HIERARCHY_LEVEL like '");
                queryString.append(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_HIERARCHY_LEVEL)));
                queryString.append("' ");
            }

            if (parameters.get(Constant.FILTER_CREATED_BY) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CREATED_BY))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CREATED_BY)))) {
                queryString.append(" AND PM.CREATED_BY in (");
                queryString.append(String.valueOf(parameters.get(Constant.FILTER_CREATED_BY)));
                queryString.append(") ");
            }
            if (Constant.ACCRUAL_RATE_PROJECTION.equals(parameters.get(Constant.MODULE_NAME))) {
                if (parameters.get(Constant.FILTER_DEDUCTION_LEVEL) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_DEDUCTION_LEVEL))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_DEDUCTION_LEVEL)))) {
                    queryString.append(" AND APS.FIELD_NAME  like '");
                    queryString.append(String.valueOf(parameters.get(Constant.FILTER_DEDUCTION_LEVEL)));
                    queryString.append("' ");
                }

                if (parameters.get(Constant.FILTER_DEDUCTION_VALUE) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_DEDUCTION_VALUE))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_DEDUCTION_VALUE)))) {
                    queryString.append(" AND APS.FIELD_VALUES in (");
                    queryString.append(String.valueOf(parameters.get(Constant.FILTER_DEDUCTION_VALUE)));
                    queryString.append(") ");
                }
            }
            if ((parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM))))
                    && (parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO))))) {
                queryString.append(Constant.AND_PMCREATED_DATE_BETWEEN);
                SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                SimpleDateFormat format = new SimpleDateFormat(Constant.MM_DD_YYYY);
                String from = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM))));
                queryString.append(from);
                queryString.append(Constant.UNDERSCORE_AND_UNDERSCORE);
                String to = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO))));
                queryString.append(to);
                queryString.append("' ");
            } else if ((parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM) == null || ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM)))
                    || StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM))))
                    && (parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO))))) {
                queryString.append(" AND PM.CREATED_DATE < '");
                SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                SimpleDateFormat format = new SimpleDateFormat(Constant.MM_DD_YYYY);
                String to = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO))));
                queryString.append(to);
                queryString.append("' ");
            } else if ((parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM))))
                    && (parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO) == null || ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO)))
                    || StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO))))) {
                queryString.append(" AND PM.CREATED_DATE > '");
                SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                SimpleDateFormat format = new SimpleDateFormat(Constant.MM_DD_YYYY);
                String from = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM))));
                queryString.append(from);
                queryString.append("' ");
            }

            if (parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM)))
                    && parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO)))) {
                queryString.append(" AND PM.MODIFIED_DATE BETWEEN '");
                SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                SimpleDateFormat format = new SimpleDateFormat(Constant.MM_DD_YYYY);
                String from = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM))));
                String to = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO))));
                queryString.append(from);
                queryString.append(Constant.UNDERSCORE_AND_UNDERSCORE);
                queryString.append(to);
                queryString.append("' ");
            } else if ((parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM) == null || ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM)))
                    || StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM))))
                    && (parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO))))) {
                queryString.append(" AND PM.MODIFIED_DATE < '");
                SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                SimpleDateFormat format = new SimpleDateFormat(Constant.MM_DD_YYYY);
                String to = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO))));
                queryString.append(to);
                queryString.append("' ");
            } else if ((parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM))))
                    && (parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO) == null || ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO)))
                    || StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO))))) {
                queryString.append(" AND PM.MODIFIED_DATE > '");
                SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                SimpleDateFormat format = new SimpleDateFormat(Constant.MM_DD_YYYY);
                String from = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM))));
                queryString.append(from);
                queryString.append("' ");
            }
            if (parameters.get(Constant.FILTER_DISCOUNT) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_DISCOUNT)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_DISCOUNT)))) {
                queryString.append(" AND PM.DISCOUNT_TYPE in (");
                queryString.append(String.valueOf(parameters.get(Constant.FILTER_DISCOUNT)));
                queryString.append(')');
            }
            if (parameters.get(Constant.FILTER_BUSINESS_UNIT_NAME) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_BUSINESS_UNIT_NAME)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_BUSINESS_UNIT_NAME)))) {
                queryString.append("  AND   CM1.COMPANY_NAME  LIKE '%");
                queryString.append(String.valueOf(parameters.get(Constant.FILTER_BUSINESS_UNIT_NAME)));
                queryString.append("%'");
            }
            if (parameters.get(Constant.FILTER_COMPANY_NAME) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_COMPANY_NAME)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_COMPANY_NAME)))) {
                queryString.append("  AND   CM.COMPANY_NAME  LIKE '%");
                queryString.append(String.valueOf(parameters.get(Constant.FILTER_COMPANY_NAME)));
                queryString.append("%'");
            }

            if ((parameters.get(Constant.IS_ORDERED) == null || Constant.FALSE.equalsIgnoreCase(String.valueOf(parameters.get(Constant.IS_ORDERED)))) && (parameters.get(Constant.LAZY_LOAD_RESULTS) != null)) {
                queryString.append(" ORDER BY PM.CREATED_DATE DESC ");
            } else if (parameters.get(Constant.IS_ORDERED) != null && "true".equalsIgnoreCase(String.valueOf(parameters.get(Constant.IS_ORDERED)))) {
                if (parameters.get(Constant.ORDER_BY_PROJECTION_NAME) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_PROJECTION_NAME))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_PROJECTION_NAME)))) {
                    queryString.append(" ORDER BY PM.PROJECTION_NAME ");
                    queryString.append(String.valueOf(parameters.get(Constant.ORDER_BY_PROJECTION_NAME)));
                }

                if (parameters.get(Constant.ORDER_BY_DESCRIPTION) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_DESCRIPTION))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_DESCRIPTION)))) {
                    queryString.append(" ORDER BY PM.PROJECTION_DESCRIPTION ");
                    queryString.append(String.valueOf(parameters.get(Constant.ORDER_BY_DESCRIPTION)));
                }

                if (parameters.get(Constant.ORDER_BY_CUSTOMER_HIERARCHY_LEVEL) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_HIERARCHY_LEVEL))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_HIERARCHY_LEVEL)))) {
                    queryString.append(" ORDER BY PM.CUSTOMER_HIERARCHY_LEVEL ");
                    queryString.append(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_HIERARCHY_LEVEL)));
                }

                if (parameters.get(Constant.ORDER_BY_CUSTOMER_HIERARCHY) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_HIERARCHY))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_HIERARCHY)))) {
                    queryString.append(" ORDER BY HDC.HIERARCHY_NAME ");
                    queryString.append(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_HIERARCHY)));
                }

                if (parameters.get(Constant.ORDER_BY_PRODUCT_HIERARCHY) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_HIERARCHY))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_HIERARCHY)))) {
                    queryString.append(" ORDER BY HDP.HIERARCHY_NAME ");
                    queryString.append(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_HIERARCHY)));
                }

                if (parameters.get(Constant.ORDER_BY_PRODUCT_HIERARCHY_LEVEL) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_HIERARCHY_LEVEL))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_HIERARCHY_LEVEL)))) {
                    queryString.append(" ORDER BY PM.PRODUCT_HIERARCHY_LEVEL ");
                    queryString.append(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_HIERARCHY_LEVEL)));
                }

                if (parameters.get(Constant.ORDER_BY_CREATED_BY) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_CREATED_BY))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_CREATED_BY)))) {
                    queryString.append(" ORDER BY PM.CREATED_BY ");
                    queryString.append(String.valueOf(parameters.get(Constant.ORDER_BY_CREATED_BY)));
                }

                if (parameters.get(Constant.ORDER_BY_CREATED_DATE_SEARCH) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_CREATED_DATE_SEARCH))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_CREATED_DATE_SEARCH)))) {
                    queryString.append(Constant.ORDER_BY_PM_CREATED_DATE);
                    queryString.append(String.valueOf(parameters.get(Constant.ORDER_BY_CREATED_DATE_SEARCH)));
                }

                if (parameters.get(Constant.ORDER_BY_MODIFIED_DATE_SEARCH) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_MODIFIED_DATE_SEARCH))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_MODIFIED_DATE_SEARCH)))) {
                    queryString.append(" ORDER BY PM.MODIFIED_DATE ");
                    queryString.append(String.valueOf(parameters.get(Constant.ORDER_BY_MODIFIED_DATE_SEARCH)));
                }
                if (parameters.get(Constant.ORDER_BY_BUSINESS_UNIT_NAME) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_BUSINESS_UNIT_NAME))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_BUSINESS_UNIT_NAME)))) {
                    queryString.append(" ORDER BY PM.BUSINESS_UNIT ");
                    queryString.append(String.valueOf(parameters.get(Constant.ORDER_BY_BUSINESS_UNIT_NAME)));
                }
                if (parameters.get(Constant.ORDER_BY_COMPANY_NAME) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_COMPANY_NAME))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_COMPANY_NAME)))) {
                    queryString.append(" ORDER BY PM.COMPANY_MASTER_SID ");
                    queryString.append(String.valueOf(parameters.get(Constant.ORDER_BY_COMPANY_NAME)));
                }

                if (Constant.ACCRUAL_RATE_PROJECTION.equals(parameters.get(Constant.MODULE_NAME))) {
                    if (parameters.get(Constant.ORDER_BY_DEDUCTION_LEVEL) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_DEDUCTION_LEVEL))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_DEDUCTION_LEVEL)))) {
                        queryString.append(" ORDER BY APS.FIELD_NAME ");
                        queryString.append(String.valueOf(parameters.get(Constant.ORDER_BY_DEDUCTION_LEVEL)));
                    }

                    if (parameters.get(Constant.ORDER_BY_DEDUCTION_VALUE) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_DEDUCTION_VALUE))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_DEDUCTION_VALUE)))) {
                        queryString.append(" ORDER BY APS.FIELD_VALUES ");
                        queryString.append(String.valueOf(parameters.get(Constant.ORDER_BY_DEDUCTION_VALUE)));
                    }
                }
            }

            if (parameters.get(Constant.LAZY_LOAD_RESULTS) != null) {

                if (parameters.get("startIndex") != null && parameters.get(Constant.OFFSET) != null) {
                    int startIndex = Integer.parseInt(String.valueOf(parameters.get("startIndex")));
                    int offset = Integer.parseInt(String.valueOf(parameters.get(Constant.OFFSET)));
                    queryString.append(Constant.OFFSET_WITH_CAPS);
                    queryString.append(startIndex);
                    queryString.append(Constant.ROWS_FETCH_NEXT);
                    queryString.append(offset);
                    queryString.append(Constant.ROWS_ONLY);

                }
            }
            LOGGER.debug("Final Search Query= {} " , queryString.toString());
            return HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());

        } catch (NumberFormatException | ParseException e) {
            LOGGER.error("In searchDsProjection ->{}", e.getMessage());
            LOGGER.error(queryString.toString());
            return Collections.emptyList();
        }
    }


    public List getRelationshipHierarchy(final Map<String, Object> parameters) {

        StringBuilder customSql = new StringBuilder(SQlUtil.getQuery(getClass(),"getRelationForCCP1"));
        try {
            LOGGER.debug("Entering getRelationshipHierarchy method");

            if (parameters.get("projectionCustHierarchy") != null) {
                customSql.append(" ProjectionCustHierarchy PH where PH.projectionMasterSid='");
            } else if (parameters.get("projectionProdHierarchy") != null) {
                customSql.append(" ProjectionProdHierarchy PH where PH.projectionMasterSid='");
            }
            customSql.append(String.valueOf(parameters.get(Constant.PROJECTION_ID)));
            customSql.append('\'');
            customSql.append(SQlUtil.getQuery(getClass(),"getRelationForCCP2"));
            if (parameters.get("projectionCustHierarchy") != null) {
                customSql.append(" ProjectionCustHierarchy PHT where PHT.projectionMasterSid='");
            } else if (parameters.get("projectionProdHierarchy") != null) {
                customSql.append(" ProjectionProdHierarchy PHT where PHT.projectionMasterSid='");
            }
            customSql.append(String.valueOf(parameters.get(Constant.PROJECTION_ID)));
            customSql.append('\'');
            customSql.append(SQlUtil.getQuery(getClass(),"getRelationForCCP3"));

            LOGGER.debug("End of getRelationshipHierarchy method");
            return HelperTableLocalServiceUtil.executeSelectQuery(customSql.toString());
        } catch (Exception e) {
            LOGGER.error("In getRelationshipHierarchy= {} " , e.getMessage());
            LOGGER.error(customSql.toString());
            return Collections.emptyList();
        }
    }
    

    /**
     * Gets the ccp details value from CCP_DETAILS
     *
     * @param parameters contains all the parameters
     * @return result list
     */
    public List getCcpDetails(final Map<String, Object> parameters) {

        try {
            LOGGER.debug("Entering getCcpDetails method");
            return HelperTableLocalServiceUtil.executeSelectQuery(String.valueOf(parameters.get("ccpQuery")));
        } catch (Exception e) {
            LOGGER.error("In getCcpDetails= {}  " , e.getMessage());
            LOGGER.error(String.valueOf(parameters.get("ccpQuery")));
            return Collections.emptyList();
        }
    }

    public List getCustomerProductGroup(final Map<String, Object> parameters) {

        StringBuilder queryString = new StringBuilder();
        try {
            LOGGER.debug("Entering getCustomerProductGroup method");
            if (parameters.get(Constant.INDICATOR) != null && "CustomerGroup".equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
                queryString.append(SQlUtil.getQuery(getClass(),"getCustomerGroup"));
                if (parameters.get(Constant.CUSTOMER_NO) != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get(Constant.CUSTOMER_NO)))
                        && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.CUSTOMER_NO)))) {
                    queryString.append(" AND CG.companyGroupNo LIKE '");
                    queryString.append(String.valueOf(parameters.get(Constant.CUSTOMER_NO)));
                    queryString.append('\'');
                }
                if (parameters.get(Constant.CUSTOMER_NAME) != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get(Constant.CUSTOMER_NAME)))
                        && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.CUSTOMER_NAME)))) {
                    queryString.append(" AND CG.companyGroupName LIKE '");
                    queryString.append(String.valueOf(parameters.get(Constant.CUSTOMER_NAME)));
                    queryString.append('\'');
                }
                if (parameters.get("companySids") != null) {
                    ArrayList<String> companySids = (ArrayList<String>) (parameters.get("companySids"));
                    StringBuilder companiesList = new StringBuilder();
                    if (companySids != null && !companySids.isEmpty()) {
                        for (int loop = 0, limit = companySids.size(); loop < limit; loop++) {
                            companiesList.append('\'');
                            companiesList.append(companySids.get(loop));
                            companiesList.append('\'');
                            if (loop != (limit - 1)) {
                                companiesList.append(", ");
                            }
                        }
                    }
                    queryString.append(" AND CGD.companyMasterSid in ( ");
                    queryString.append(companiesList.toString());
                    queryString.append(" )");
                }

            } else if (parameters.get(Constant.INDICATOR) != null && "ProductGroup".equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
                queryString.append(SQlUtil.getQuery(getClass(),"getProductGroup"));
                if (parameters.get(Constant.PRODUCT_NO) != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get(Constant.PRODUCT_NO)))
                        && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.PRODUCT_NO)))) {
                    queryString.append(" AND IG.itemGroupNo LIKE '");
                    queryString.append(String.valueOf(parameters.get(Constant.PRODUCT_NO)));
                    queryString.append("' ");
                }
                if (parameters.get(Constant.PRODUCT_NAME) != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get(Constant.PRODUCT_NAME)))
                        && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.PRODUCT_NAME)))) {
                    queryString.append(" AND IG.itemGroupName LIKE '");
                    queryString.append(String.valueOf(parameters.get(Constant.PRODUCT_NAME)));
                    queryString.append('\'');
                }

                if (parameters.get("itemSids") != null) {
                    ArrayList<String> itemSids = (ArrayList<String>) (parameters.get("itemSids"));
                    if (itemSids != null) {
                        StringBuilder itemsList = new StringBuilder();
                        if (itemSids != null && !itemSids.isEmpty()) {
                            for (int loop = 0, limit = itemSids.size(); loop < limit; loop++) {
                                itemsList.append('\'');
                                itemsList.append(itemSids.get(loop));
                                itemsList.append('\'');
                                if (loop != (limit - 1)) {
                                    itemsList.append(", ");
                                }
                            }
                        }
                        queryString.append(" AND IGD.itemMasterSid in ( ");
                        queryString.append(itemsList.toString());
                        queryString.append(" )");
                    }
                }
            } else {
                return Collections.emptyList();
            }
            return HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());
        } catch (Exception e) {
            LOGGER.error("In getCustomerProductGroup= {}  " , e.getMessage());
            LOGGER.error(queryString.toString());
            return Collections.emptyList();
        }
    }
    

    public List getProjection(final int projectionId) {

        StringBuilder queryString = new StringBuilder();
        try {
            LOGGER.debug("Entering getProjection method");
            queryString.append(SQlUtil.getQuery(getClass(),"getProjectionJoin"));
            queryString.append(" where PM.projection_Master_Sid = ");
            queryString.append(projectionId);
            return HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());
        } catch (Exception e) {
            LOGGER.error("In getProjection ->= {}" , e.getMessage());
            LOGGER.error(queryString.toString());
            return Collections.emptyList();
        }
    }

    public List getRelationShipValues(final Map<String, Object> parameters) {

        StringBuilder queryString = new StringBuilder();
        try {
            LOGGER.debug("Entering getRelationShipValues method");
            if ("forecastDate".equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
                queryString.append(SQlUtil.getQuery(getClass(),String.valueOf(parameters.get("Query"))));
                for (Object input : (List<Object>) parameters.get("input")) {
                    queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(input));
                }
            } else {
                queryString.append(SQlUtil.getQuery(getClass(),"getRelationshipValues"));
                if ("customer".equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
                    queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, " Projection_Cust_Hierarchy ");
                } else if ("product".equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
                    queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, " Projection_Prod_Hierarchy ");
                }
                queryString.append(" WHERE PH.PROJECTION_MASTER_SID='");
                queryString.append(String.valueOf(parameters.get(Constant.PROJECTION_ID)));
                if (parameters.get("restrictLevelNumber") != null && "true".equalsIgnoreCase(String.valueOf(parameters.get("restrictLevelNumber")))) {
                    queryString.append("' AND RLD.LEVEL_NO <= '");
                    queryString.append(String.valueOf(parameters.get("levelNo")));
                }
                queryString.append("' ORDER by RLD.level_No");
            }

            return HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());
        } catch (Exception e) {
            LOGGER.error("getRelationShipValues  -->= {}" , e.getMessage());
            LOGGER.error(queryString.toString());
            return Collections.emptyList();
        }
    }

    public String deleteProjection(String screenName, int projectionID) {
        String foreignKeyTableName;
        if (screenName.equals("Non Mandated")) {
            foreignKeyTableName = SQlUtil.getQuery(getClass(),"NM_FK_TableNamesFordelete");
        } else if (screenName.equals("Mandated")) {
            foreignKeyTableName = SQlUtil.getQuery(getClass(),"M_FK_TableNamesFordelete");
        } else {
            foreignKeyTableName = SQlUtil.getQuery(getClass(),"R_FK_TableNamesFordelete");
        }
        String primarykeyTableName = SQlUtil.getQuery(getClass(),"TableNamesFordelete");
        String sql1 = StringUtils.EMPTY;
        StringBuilder sqlBuilder = new StringBuilder();
        String sql = StringUtils.EMPTY;
        StringBuilder sql2Builder = new StringBuilder();
        try {

            sql = "select PROJECTION_DETAILS_SID from PROJECTION_DETAILS where PROJECTION_MASTER_SID = " + projectionID + " ";

            List<Integer> idList = HelperTableLocalServiceUtil.executeSelectQuery(sql);

            if (idList != null && !idList.isEmpty()) {
                String fkTbName[] = foreignKeyTableName.split(",");
                int pksize = fkTbName.length;
                for (int i = 0; i < pksize; i++) {
                   sqlBuilder.append("delete from ");
                    if (fkTbName[i] != null && !StringUtils.EMPTY.equals(fkTbName[i]) && !ConstantsUtils.NULL.equals(fkTbName[i])) {
                        sqlBuilder.append(fkTbName[i]);
                    }
                    String projectionDetailsSid = CommonUtils.collectionToStringCommon(idList, false);

                    sqlBuilder.append(" where PROJECTION_DETAILS_SID IN (" ).append( projectionDetailsSid ).append( ");");

                }
                sql1 = sqlBuilder.toString();
                HelperTableLocalServiceUtil.executeUpdateQuery(sql1);
            }

            String pkTbName[] = primarykeyTableName.split(",");

            if (screenName.equals("Non Mandated") || screenName.equals("Mandated")) {
                sql2Builder.append("delete from WORKFLOW_PROCESS_INFO where PROJECTION_MASTER_SID=" ).append( projectionID ).append( "; ");
                sql2Builder.append("delete from WORKFLOW_MASTER where PROJECTION_MASTER_SID=" ).append( projectionID ).append( "; ");
            }

            int size = pkTbName.length;
            for (int i = 0; i < size; i++) {
                sql2Builder.append("delete from ");
                if (pkTbName[i] != null && !StringUtils.EMPTY.equals(pkTbName[i]) && !ConstantsUtils.NULL.equals(pkTbName[i])) {
                    sql2Builder.append(pkTbName[i]);
                }
                if (projectionID != 0) {
                    sql2Builder.append(" where PROJECTION_MASTER_SID=" ).append( projectionID ).append( ';');
                }
            }

            HelperTableLocalServiceUtil.executeUpdateQuery(sql2Builder.toString());

            return "Success";
        } catch (Exception ex) {
            LOGGER.error("In deleteProjection ->= {}" , ex.getMessage());
            LOGGER.error(sql);
            LOGGER.error(sql1);
            LOGGER.error(sql2Builder.toString());
            return "fail";
        }
    }

    public List getParentLevels(final int relationshipLevelSid, final Map<String, Object> parameters) {
        StringBuilder queryBuilder = new StringBuilder();

        try {
            if (parameters.get(Constant.INDICATOR) != null && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.INDICATOR)))
                    && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.INDICATOR))) && "getParentLevelsWithHierarchyNo".equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
                queryBuilder.append(SQlUtil.getQuery(getClass(),"getParentLevelsWithHierarchyNo"));
                queryBuilder.replace(queryBuilder.indexOf("?"), queryBuilder.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyNos")));
            } else {
                queryBuilder.append(SQlUtil.getQuery(getClass(),"getParentLevels"));
                queryBuilder.append(" RBLD.relationshipLevelSid = '");
                queryBuilder.append(relationshipLevelSid);
                queryBuilder.append("')");
                if (parameters.get("parent") != null) {
                    queryBuilder.append(" AND RLD.relationshipLevelValues = '");
                    queryBuilder.append(String.valueOf(parameters.get("parent")));
                    queryBuilder.append("' ");
                }
                queryBuilder.append(" ORDER by RLD.levelNo desc");
            }
            return HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString());
        } catch (Exception ex) {
            LOGGER.error("In getParentLevels ->= {}" , ex.getMessage());
            LOGGER.error(queryBuilder.toString());
            return Collections.emptyList();
        }
    }

    public List executeQuery(final Map<String, Object> parameters) {
        LOGGER.debug("----inside executeQuery in finder Impl-----------");
        StringBuilder queryString = new StringBuilder();
        if (parameters.get(Constant.INDICATOR) != null && Constant.HAS_TRADING_PARTNER.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
            queryString.append(SQlUtil.getQuery(getClass(), Constant.HAS_TRADING_PARTNER));
            queryString.append('\'');
            queryString.append(String.valueOf(parameters.get(Constant.PROJECTION_ID)));
            queryString.append('\'');
        } else if (parameters.get(Constant.INDICATOR) != null && Constant.UNSAVED_PROJECTION_IDS.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
            queryString.append(SQlUtil.getQuery(getClass(), Constant.UNSAVED_PROJECTION_IDS));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("deleteDate")));
        } else if (parameters.get(Constant.INDICATOR) != null && Constant.CHILD_LEVEL_RL_SID.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {

            if ("PROJECTION_PROD_HIERARCHY".equals(parameters.get(Constant.TABLE_NAME))) {
                ArrayList<String> rlSids = (ArrayList<String>) parameters.get(Constant.RL_SIDS);
                StringBuilder hierarchyInclusion = new StringBuilder();
                for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
                    hierarchyInclusion.append(Constant.HIERARCHY_NO_LIKE);
                    hierarchyInclusion.append(rlSids.get(loop));
                    hierarchyInclusion.append("%'");
                    if (loop != (limit - 1)) {
                        hierarchyInclusion.append(" OR ");
                    }
                }
                String hierarchyExclusion = CommonUtils.stringListToString(rlSids);
                String query = SQlUtil.getQuery(getClass(),"get-lower-levels-based-on-hierarchy-no");
                query = query.replace("[?BU_COMPANY_MASTER_SID]", StringUtils.EMPTY + parameters.get(Constant.BUSINESS_UNIT));
                query = query.replace("[?PROJECTION_MASTER_SID]", StringUtils.EMPTY + parameters.get(Constant.PROJECTION_ID));
                query = query.replace("[?HIERARCHY_INCLUDE]", hierarchyInclusion);
                query = query.replace("[?HIERARCHY_EXCLUDE]", hierarchyExclusion);

                if (parameters.get(Constant.MODULE) != null && "cff".equalsIgnoreCase(String.valueOf(parameters.get(Constant.MODULE)))) {
                    query = query.replace("[?HIERARCHY_TABLE]", "CFF_PROD_HIERARCHY");
                    query = query.replace("[?MASTER_TABLE_SID_COLUMN]", "CFF_MASTER_SID");
                } else {
                    query = query.replace("[?HIERARCHY_TABLE]", "PROJECTION_PROD_HIERARCHY");
                    query = query.replace("[?MASTER_TABLE_SID_COLUMN]", "PROJECTION_MASTER_SID");
                }
                queryString.append(query);
            } else if (parameters.get(Constant.RL_SIDS) != null) {
                ArrayList<String> rlSids = (ArrayList<String>) parameters.get(Constant.RL_SIDS);
                if (rlSids != null && !rlSids.isEmpty()) {
                    queryString.append(SQlUtil.getQuery(getClass(),"getChildLevelRLSidRestricted"));
                    queryString.append(" WHERE (");
                    for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
                        queryString.append(Constant.HIERARCHY_NO_LIKE);
                        queryString.append(rlSids.get(loop));
                        queryString.append("%'");
                        if (loop != (limit - 1)) {
                            queryString.append(" OR ");
                        }
                    }
                    queryString.append(") AND HIERARCHY_NO NOT IN (");
                    queryString.append(CommonUtils.stringListToString(rlSids));
                    queryString.append(')');
                    queryString.append(" AND RLD.RELATIONSHIP_LEVEL_SID not in (SELECT PH.RELATIONSHIP_LEVEL_SID FROM ");
                    queryString.append(String.valueOf(parameters.get(Constant.TABLE_NAME)));

                    if (parameters.get(Constant.MODULE) != null && "cff".equalsIgnoreCase(String.valueOf(parameters.get(Constant.MODULE)))) {
                        queryString.append(" PH WHERE PH.CFF_MASTER_SID = ");
                    } else {
                        queryString.append(" PH WHERE PH.PROJECTION_MASTER_SID = ");
                    }
                    queryString.append(String.valueOf(parameters.get(Constant.PROJECTION_ID)));
                    queryString.append(')');
                }
            }
        } else if (parameters.get(Constant.INDICATOR) != null && Constant.CHILD_LEVEL_RL.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
            ArrayList<String> rlSids = (ArrayList<String>) parameters.get(Constant.RL_SIDS);
            if (rlSids != null && !rlSids.isEmpty()) {
                queryString.append(SQlUtil.getQuery(getClass(), Constant.CHILD_LEVEL_RL));
                queryString.append(" and (");
                for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
                    queryString.append("HIERARCHY_NO like '");
                    queryString.append(rlSids.get(loop));
                    queryString.append("%'");
                    if (loop != (limit - 1)) {
                        queryString.append(" or ");
                    }
                }
                queryString.append(") and HIERARCHY_NO not in (");
                queryString.append(CommonUtils.stringListToString(rlSids));
                if (parameters.get(Constant.AVAILABLE_HIER_NO) != null) {
                    ArrayList<String> availableHierNo = (ArrayList<String>) parameters.get(Constant.AVAILABLE_HIER_NO);
                    queryString.append(", ");
                    queryString.append(CommonUtils.stringListToString(availableHierNo));
                }
                queryString.append(") ");
            }
        } else if (parameters.get(Constant.INDICATOR) != null && Constant.GET_REMOVABLE_CHILDREN.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
            queryString.append(SQlUtil.getQuery(getClass(), Constant.GET_REMOVABLE_CHILDREN));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get(Constant.REMOVE_LEVELS)));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get(Constant.REMOVE_LEVELS)));
        } else if (parameters.get(Constant.INDICATOR) != null && Constant.DELETE_TEMP_ON_UPDATE.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
            String hNos = String.valueOf(parameters.get("hNos"));
            String projectionId = String.valueOf(parameters.get(Constant.PROJECTION_ID));
            String projectionHierarchyTable = String.valueOf(parameters.get("projectionHierarchyTable"));
            for (String table : CommonUtils.getTempTableList()) {
                queryString.append(SQlUtil.getQuery(getClass(), Constant.DELETE_TEMP_ON_UPDATE));
                queryString.replace(queryString.indexOf(Constant.DTBL), queryString.indexOf(Constant.DTBL) + 5, table);
                queryString.replace(queryString.indexOf(Constant.PID), queryString.indexOf(Constant.PID) + 4, projectionId);
                queryString.replace(queryString.indexOf(Constant.HTBL), queryString.indexOf(Constant.HTBL) + 5, projectionHierarchyTable);
                queryString.replace(queryString.indexOf(Constant.PID), queryString.indexOf(Constant.PID) + 4, projectionId);
                queryString.replace(queryString.indexOf(Constant.HTBL), queryString.indexOf(Constant.HTBL) + 5, projectionHierarchyTable);
                queryString.replace(queryString.indexOf(Constant.PID), queryString.indexOf(Constant.PID) + 4, projectionId);
                queryString.replace(queryString.indexOf(Constant.HNO), queryString.indexOf(Constant.HNO) + 4, hNos);
                queryString.replace(queryString.indexOf(Constant.PID), queryString.indexOf(Constant.PID) + 4, projectionId);
            }
        } else if (parameters.get(Constant.INDICATOR) != null && Constant.GET_FS_VALUE.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
            queryString.append(SQlUtil.getQuery(getClass(), Constant.GET_FS_VALUE));
            queryString.replace(queryString.indexOf(Constant.RLC), queryString.indexOf(Constant.RLC) + 5, String.valueOf(parameters.get("relationshipLevelValue")));
        } else if (parameters.get(Constant.INDICATOR) != null && Constant.COMPANY_FILTER.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
            queryString.append(SQlUtil.getQuery(getClass(), Constant.COMPANY_FILTER));
            queryString.append('\'');
            queryString.append(String.valueOf(parameters.get(Constant.COMPANY_SID)));
            queryString.append('\'');
        } else {
            queryString.append(String.valueOf(parameters.get("query")));
        }
        try {
            if (parameters.get(Constant.INDICATOR) != null
                    && (Constant.DELETE_TEMP_ON_UPDATE.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR))))) {
                List<Integer> list = new ArrayList<>();
                int returnValue = HelperTableLocalServiceUtil.executeUpdateQueryCount(queryString.toString());
                list.add(returnValue);
                return list;
            } else {
                return HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());
            }
        } catch (Exception ex) {
            LOGGER.error("In executeQuery  ->= {}" , ex.getMessage());
            LOGGER.error(queryString.toString());
            return Collections.emptyList();
        }
    }

    public List getItemsFromBrand(Map<String, Object> parameters) {

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SQlUtil.getQuery(getClass(),"getItemsFromBrand"));
        try {
            ArrayList<Integer> itemMasterSids = (ArrayList<Integer>) parameters.get("itemMasterSids");
            if (itemMasterSids != null && !itemMasterSids.isEmpty()) {
                queryBuilder.append(" and IM.itemMasterSid in (");
                for (int loop = 0, limit = itemMasterSids.size(); loop < limit; loop++) {
                    queryBuilder.append('\'');
                    queryBuilder.append(itemMasterSids.get(loop));
                    queryBuilder.append('\'');
                    if (loop != (limit - 1)) {
                        queryBuilder.append(", ");
                    }
                }
                queryBuilder.append(") ");
            }

            return HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString());
        } catch (Exception ex) {
            LOGGER.error("in getItemsFromBrand= {}", ex.getMessage() );
            LOGGER.error(queryBuilder.toString());
            return Collections.emptyList();
        }
    }

    public List getInnerLevel(Map<String, Object> parameters) {
        StringBuilder queryBuilder = new StringBuilder();

        try {
            if ((parameters.get(Constant.IS_NDC) != null && "true".equals(String.valueOf(parameters.get(Constant.IS_NDC))))
                    || (!Constants.BUSINESS_PROCESS_TYPE_CHENNALS.equals(parameters.get(Constant.SCREEN_NAME)) && parameters.get(Constant.LEVEL_LOWER_CASE) != null && ("Ndc".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_LOWER_CASE))) || "Item".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_LOWER_CASE))) || Constant.PRODUCT_LABEL.equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_LOWER_CASE)))))) {

                queryBuilder.append(SQlUtil.getQuery(getClass(),"get-inner-level-products"));
                if (Constant.PRODUCT_LABEL.equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME)))) {
                    queryBuilder.append(" AND ( RLD.LEVEL_NAME='PRODUCT') ");
                } else {
                    queryBuilder.append(" AND ( RLD.LEVEL_NAME='NDC') ");
                }

                queryBuilder.append(" AND CM.COMPANY_MASTER_SID = ");
                queryBuilder.append(String.valueOf(parameters.get("glCompId")));
                queryBuilder.append(" AND GLC.COMPANY_CODE = CM.COMPANY_ID AND IM.NDC8 = GLC.NDC8 ");
                queryBuilder.append(" AND IM.ORGANIZATION_KEY = ");
                queryBuilder.append(String.valueOf(parameters.get(Constant.BUSINESS_UNIT))).append(' ');

            } else if (parameters.get(Constant.LEVEL_NAME) != null && !"true".equalsIgnoreCase(String.valueOf(parameters.get(Constant.IS_NDC))) && "company".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME)))) {

                queryBuilder.append(SQlUtil.getQuery(getClass(),"get-inner-level-companies"));

            } else if (parameters.get(Constant.LEVEL_NAME) != null && !"true".equalsIgnoreCase(String.valueOf(parameters.get(Constant.IS_NDC)))
                    && ("therapeutic class".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME))) || "brand".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME))))) {

                queryBuilder.append(SQlUtil.getQuery(getClass(),"get-inner-level-brand-therapeutic-class"));

            } else if (parameters.get(Constant.LEVEL_LOWER_CASE) != null && Constants.INDICATOR_LEVEL_CUSTOMER.equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_LOWER_CASE)))) {
                queryBuilder.append(SQlUtil.getQuery(getClass(),"getInnerLevelCustomer"));

            } else if (parameters.get(Constant.LEVEL_LOWER_CASE) != null && Constants.INDICATOR_LEVEL_CONTRACT.equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_LOWER_CASE)))) {
                queryBuilder.append(SQlUtil.getQuery(getClass(),"getInnerLevelContract"));

            } else {
                queryBuilder.append(SQlUtil.getQuery(getClass(),"getInnerLevel"));
            }

            if (parameters.get(Constant.HIERARCHY_DEFINITION_SID) != null) {
                queryBuilder.append(" and RB.hierarchy_Definition_Sid = ");
                queryBuilder.append(String.valueOf(parameters.get(Constant.HIERARCHY_DEFINITION_SID)));
            }

            if (parameters.get(Constant.LEVEL_NAME) != null && !"true".equalsIgnoreCase(String.valueOf(parameters.get(Constant.IS_NDC)))) {
                queryBuilder.append(" and RLD.level_Name = '");
                queryBuilder.append(String.valueOf(parameters.get(Constant.LEVEL_NAME)));
                queryBuilder.append('\'');
            }

            if (parameters.get(Constant.RELATIONSHIP_SID) != null && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.RELATIONSHIP_SID)))
                    && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(Constant.RELATIONSHIP_SID))) && !"0".equals(String.valueOf(parameters.get(Constant.RELATIONSHIP_SID)))) {
                queryBuilder.append(" and RLD.RELATIONSHIP_BUILDER_SID = '");
                queryBuilder.append(String.valueOf(parameters.get(Constant.RELATIONSHIP_SID)));
                queryBuilder.append('\'');
            }
            if (Constant.ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(String.valueOf(parameters.get(Constant.SCREEN_NAME))) && (!"Segment Group".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME))) && !"Segment".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME)))) && !"Segments".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME))) && !"Company".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME))) && !"GL Company".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME))) && !"Business Unit".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME)))) {
                queryBuilder.append("and RLD.relationship_Level_Values IN (");

                if (parameters.get(Constant.LEVEL_NAME) != null && ("Market Type".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME))) || "MarketType".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME))))) {
                    queryBuilder.append("Select Distinct  CM.CONTRACT_TYPE from Constant.CONTRACT_MASTER CM \n"
                            ).append( Constant.JOIN_RS_CONTRACT_RS_C_TYPE_ON_RS_C_TYPE
                            ).append( Constant.RS_C_TYPE ).append( String.valueOf(parameters.get(Constant.DEDUCTION_LEVEL)) ).append( Constant.IN_SELECT_HELPER_TABLE_SID_FROM_HELPER_TA ).append( String.valueOf(parameters.get(Constant.DEDUCTION_VALUE)) ).append( Constant.AND_LIST_NAME ).append( String.valueOf(parameters.get(Constant.DEDUCTION_LEVEL)) ).append( "'  )");
                } else if (parameters.get(Constant.LEVEL_NAME) != null && "Contract".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME)))) {
                    queryBuilder.append("Select Distinct  RS_C_TYPE.CONTRACT_MASTER_SID from Constant.CONTRACT_MASTER CM \n"
                            ).append( Constant.JOIN_RS_CONTRACT_RS_C_TYPE_ON_RS_C_TYPE
                            ).append( Constant.RS_C_TYPE ).append( String.valueOf(parameters.get(Constant.DEDUCTION_LEVEL)) ).append( Constant.IN_SELECT_HELPER_TABLE_SID_FROM_HELPER_TA ).append( String.valueOf(parameters.get(Constant.DEDUCTION_VALUE)) ).append( Constant.AND_LIST_NAME ).append( String.valueOf(parameters.get(Constant.DEDUCTION_LEVEL)) ).append( "'    )");

                } else if (parameters.get(Constant.LEVEL_NAME) != null && "Contract Holder".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME)))) {
                    queryBuilder.append(" Select Distinct  CM.CONT_HOLD_COMPANY_MASTER_SID from Constant.CONTRACT_MASTER CM \n"
                            ).append( Constant.JOIN_RS_CONTRACT_RS_C_TYPE_ON_RS_C_TYPE
                            ).append( Constant.RS_C_TYPE ).append( String.valueOf(parameters.get(Constant.DEDUCTION_LEVEL)) ).append( Constant.IN_SELECT_HELPER_TABLE_SID_FROM_HELPER_TA ).append( String.valueOf(parameters.get(Constant.DEDUCTION_VALUE)) ).append( Constant.AND_LIST_NAME ).append( String.valueOf(parameters.get(Constant.DEDUCTION_LEVEL)) ).append( "'    )");

                } else if (parameters.get(Constant.LEVEL_NAME) != null && ("Trading Partner".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME))) || "Customer".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME))))) {
                    queryBuilder.append("Select Distinct  CFP_CD_SID.COMPANY_MASTER_SID from Constant.CONTRACT_MASTER CM \n"
                            ).append( "Join CFP_CONTRACT CFP_SID ON CM.CONTRACT_MASTER_SID = CFP_SID.CONTRACT_MASTER_SID\n"
                            ).append( "Join  CFP_CONTRACT_DETAILS CFP_CD_SID  ON  CFP_CD_SID.CFP_CONTRACT_SID=CFP_SID.CFP_CONTRACT_SID\n"
                            ).append( Constant.AND_NEW_LINE
                            ).append( "CFP_SID.CONTRACT_MASTER_SID IN (Select Distinct CONTRACT_MASTER_SID from RS_CONTRACT\n"
                            ).append( Constant.WHERE_SPACE ).append( String.valueOf(parameters.get(Constant.DEDUCTION_LEVEL)) ).append( " IN(Select HELPER_TABLE_SID from HELPER_TABLE where \n"
                            ).append( "-- LIST_NAME like'" ).append( String.valueOf(parameters.get(Constant.DEDUCTION_LEVEL)) ).append( "'\n"
                            ).append( "DESCRIPTION like'" ).append( String.valueOf(parameters.get(Constant.DEDUCTION_VALUE)) ).append( Constant.AND_LIST_NAME ).append( String.valueOf(parameters.get(Constant.DEDUCTION_LEVEL)) ).append( "' \n"
                            ).append( "))");
                } else if (parameters.get(Constant.LEVEL_NAME) != null && ("Company".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME))))) {
                    queryBuilder.append('\n'
                            ).append( "Select Distinct  CFP_CD_SID.COMPANY_MASTER_SID from Constant.CONTRACT_MASTER CM \n"
                            ).append( "Join CFP_CONTRACT CFP_SID ON CM.CONTRACT_MASTER_SID = CFP_SID.CONTRACT_MASTER_SID\n"
                            ).append( "Join  CFP_CONTRACT_DETAILS CFP_CD_SID  ON  CFP_CD_SID.CFP_CONTRACT_SID=CFP_SID.CFP_CONTRACT_SID\n"
                            ).append( Constant.AND_NEW_LINE
                            ).append( "CFP_SID.CONTRACT_MASTER_SID IN (Select Distinct CONTRACT_MASTER_SID from RS_CONTRACT\n"
                            ).append( Constant.WHERE_SPACE ).append( String.valueOf(parameters.get(Constant.DEDUCTION_LEVEL)) ).append( " IN(Select HELPER_TABLE_SID from HELPER_TABLE where \n"
                            ).append( "-- LIST_NAME like'" ).append( String.valueOf(parameters.get(Constant.DEDUCTION_LEVEL)) ).append( "'\n"
                            ).append( "DESCRIPTION like'" ).append( String.valueOf(parameters.get(Constant.DEDUCTION_VALUE)) ).append( Constant.AND_LIST_NAME ).append( String.valueOf(parameters.get(Constant.DEDUCTION_LEVEL)) ).append( "' \n"
                            ).append( "))  JOIN COMPANY_MASTER COM_MAS ON COM_MAS.COMPANY_MASTER_SID=CFP_CD_SID.COMPANY_MASTER_SID\n"
                            ).append( "                            AND COM_MAS.COMPANY_TYPE IN(Select HELPER_TABLE_SID from HELPER_TABLE where \n"
                            ).append( "                            DESCRIPTION like'GLCOMP' AND LIST_NAME='COMPANY_TYPE')");
                } else if (parameters.get(Constant.LEVEL_NAME) != null && ("Item".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME))) || Constant.PRODUCT_LABEL.equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME))) || "Ndc".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME))))) {
                    queryBuilder.append('\n'
                            ).append( Constant.SELECT_DISTINCT_IFP_CD_SID_ITEM_MASTER_SID
                            ).append( Constant.JOIN_IFP_CONTRACT_IFP_SID_ON_CM_CONTRACT_M
                            ).append( Constant.JOIN_IFP_CONTRACT_DETAILS_IFP_CD_SID_ON_I
                            ).append( Constant.AND_NEW_LINE
                            ).append( Constant.IFP_SID_CONTRACT_MASTER_SID_IN_SELECT_CONT
                            ).append( Constant.WHERE_SPACE ).append( String.valueOf(parameters.get(Constant.DEDUCTION_LEVEL)) ).append( Constant.IN_SELECT_HELPER_TABLE_SID_FROM_HELPER_TABLE
                            ).append( Constant.DESCRIPTION_LIKE ).append( String.valueOf(parameters.get(Constant.DEDUCTION_VALUE)) ).append( Constant.AND_LIST_NAME ).append( String.valueOf(parameters.get(Constant.DEDUCTION_LEVEL)) ).append( "' )) \n");
                } else if (parameters.get(Constant.LEVEL_NAME) != null && ("Brand".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME))))) {
                    queryBuilder.append(Constant.SELECT_DISTINCT_IM_BRAND_MASTER_SID_FROM_C
                            ).append( Constant.JOIN_IFP_CONTRACT_IFP_SID_ON_CM_CONTRACT_M
                            ).append( Constant.JOIN_IFP_CONTRACT_DETAILS_IFP_CD_SID_ON
                            ).append( Constant.JOIN_ITEM_MASTER_IM_ON_IM_ITEM_MASTER_SID
                            ).append( Constant.IFP_SID_CONTRACT_MASTER_SID_IN_SELECT_CONT
                            ).append( Constant.WHERE_SPACE ).append( String.valueOf(parameters.get(Constant.DEDUCTION_LEVEL)) ).append( Constant.IN_SELECT_HELPER_TABLE_SID_FROM_HELPER_TABLE
                            ).append( Constant.DESCRIPTION_LIKE ).append( String.valueOf(parameters.get(Constant.DEDUCTION_VALUE)) ).append( Constant.AND_LIST_NAME ).append( String.valueOf(parameters.get(Constant.DEDUCTION_LEVEL)) ).append( "' ))");
                } else if (parameters.get(Constant.LEVEL_NAME) != null && ("Therapeutic Class".equalsIgnoreCase(String.valueOf(parameters.get(Constant.LEVEL_NAME))))) {
                    queryBuilder.append(Constant.SELECT_DISTINCT_IM_THERAPEUTIC_CLASS_FROM
                            ).append( Constant.JOIN_IFP_CONTRACT_IFP_SID_ON_CM_CONTRACT_M
                            ).append( Constant.JOIN_IFP_CONTRACT_DETAILS_IFP_CD_SID_ON_CNT
                            ).append( Constant.JOIN_ITEM_MASTER_IM_ON_IM_ITEM_MASTER_SID
                            ).append( Constant.IFP_SID_CONTRACT_MASTER_SID_IN_SELECT_CONT
                            ).append( Constant.WHERE_SPACE ).append( String.valueOf(parameters.get(Constant.DEDUCTION_LEVEL)) ).append( Constant.IN_SELECT_HELPER_TABLE_SID_FROM_HELPER_TABLE
                            ).append( Constant.DESCRIPTION_LIKE ).append( String.valueOf(parameters.get(Constant.DEDUCTION_VALUE)) ).append( Constant.AND_LIST_NAME ).append( String.valueOf(parameters.get(Constant.DEDUCTION_LEVEL)) ).append( "' ))");
                }

                queryBuilder.append(')');
            }
            return HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString());
        } catch (Exception ex) {
            LOGGER.error("in getInnerLevel()= {}", ex.getMessage());
            LOGGER.error(queryBuilder.toString());
            return Collections.emptyList();
        }
    }

    public List getCcpMap(final Map<String, Object> parameters) {

        StringBuilder customSql = new StringBuilder();
        try {
            LOGGER.debug("Entering getCcpMap method ");
            if (parameters.get(Constant.INDICATOR) != null && "getRbId".equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
                customSql.append(SQlUtil.getQuery(getClass(),"getRbIdFromHier"));
                if (parameters.get(Constant.HIERARCHY_DEFINITION_SID) != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get(Constant.HIERARCHY_DEFINITION_SID)));
                }

            } else if (parameters.get(Constant.INDICATOR) != null && Constant.SAVE_CCP.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
                customSql.append(SQlUtil.getQuery(getClass(),"saveCcpMerge"));

                if (parameters.get(Constant.PROJECTION_ID) != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get(Constant.PROJECTION_ID)));
                }

                if (parameters.get(Constant.HIERARACHY_NO) != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get(Constant.HIERARACHY_NO)));
                }

                if (parameters.get(Constant.PROJECTION_ID) != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get(Constant.PROJECTION_ID)));
                }

            }

            if (parameters.get(Constant.INDICATOR) != null && Constant.SAVE_CCP.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
                HelperTableLocalServiceUtil.executeUpdateQuery(customSql.toString());
                return Collections.emptyList();
            } else {
                return HelperTableLocalServiceUtil.executeSelectQuery(customSql.toString());
            }
        } catch (Exception e) {
            LOGGER.error("in getCcpMap= {}", e.getMessage());
            LOGGER.error(customSql.toString());
            return Collections.emptyList();
        }
    }

    public void saveCcp(final Map<String, Object> parameters) {

        String hierarchyQuery = StringUtils.EMPTY;
        String levelQuery = StringUtils.EMPTY;
        StringBuilder ccpQueryList = new StringBuilder();
        try {
            LOGGER.debug("Entering saveCcp method ");

            if (parameters.get(Constant.INDICATOR) != null && Constant.INDICATOR.equalsIgnoreCase(String.valueOf(parameters.get(Constant.SAVE_CCP)))) {
                StringBuilder customSql = new StringBuilder();

                customSql.append(SQlUtil.getQuery(getClass(), Constant.SAVE_CCP));

                if (parameters.get(Constant.PROJECTION_ID) != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get(Constant.PROJECTION_ID)));
                }

                if (parameters.get(Constant.HIERARACHY_NO) != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get(Constant.HIERARACHY_NO)));
                }

                if (parameters.get(Constant.PROJECTION_ID) != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get(Constant.PROJECTION_ID)));
                }

                LOGGER.debug("End of saveCcp method");
            } else {
                List list = null;
                    List<String> relationshipBuilderSids = (List<String>) parameters.get("relationshipBuilderSids");
                    List<StringBuilder> logic = new ArrayList<>();
                    List<String> condition = new ArrayList<>();
                    if (relationshipBuilderSids != null && !relationshipBuilderSids.isEmpty()) {
                        for (Object temp : relationshipBuilderSids) {
                            Integer level;
                            hierarchyQuery = SQlUtil.getQuery(getClass(),"getHierarchyMapQuery").replace("?", String.valueOf(temp));
                            levelQuery = "SELECT max(RLD.LEVEL_NO)\n"
                                    + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD, \n"
                                    + "HIERARCHY_LEVEL_DEFINITION HLD \n"
                                    + "where HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                                    + "AND RLD.RELATIONSHIP_BUILDER_SID=" + temp + ";";

                            list = HelperTableLocalServiceUtil.executeSelectQuery(hierarchyQuery);
                            level = DataTypeConverter.convertObjectToInt(HelperTableLocalServiceUtil.executeSelectQuery(levelQuery).get(0));
                            
                            StringBuilder ccpQuery = new StringBuilder("MERGE CCP_MAP AS TARGET USING ( "
                                    + "SELECT distinct ? RELATIONSHIP_LEVEL_SID ,CCP.CCP_DETAILS_SID FROM CCP_DETAILS CCP ");
                            Integer prevNo = 0;
                            Integer count = 0;
                            String nextHierarchyNo = StringUtils.EMPTY;
                            for (int i = 0; i < list.size(); i++) {
                                Object[] tempRow = (Object[]) list.get(i);
                                Integer currNo = DataTypeConverter.convertObjectToInt(tempRow[2]);

                                if (i < list.size() - 1) {
                                    Object[] tempNextRow = (Object[]) list.get(i + 1);
                                    nextHierarchyNo = String.valueOf(tempNextRow[4]);
                                }
                                if (prevNo >= currNo) {
                                    while (currNo <= prevNo) {
                                        if (logic.size() > 0) {
                                            logic.remove(logic.get(logic.size() - 1));
                                        }
                                        if (condition.size() > 0) {
                                            condition.remove(condition.get(condition.size() - 1));
                                        }
                                        prevNo--;
                                    }
                                }
                                //ly nm
                                if ("Contract Holder".equals(tempRow[3]) && parameters.get(Constant.PROJECTION_ID) != null && Constants.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(parameters.get(Constant.SCREEN_NAME))) {
                                    StringBuilder tempLogic = new StringBuilder(Constant.SELECT_CMCONTRACT_MASTER_SID_FROM_CONTRACT);
                                    if (Constant.CONTRACT_MASTER.equals(tempRow[5])) {
                                        tempLogic.append(" WHERE CM.CONT_HOLD_COMPANY_MASTER_SID=");
                                        tempLogic.append(tempRow[1]);
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(Constant.J1_WHERE_J1);
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND CM.CONT_HOLD_COMPANY_MASTER_SID=J1.COMPANY_MASTER_SID");
                                    }
                                    tempLogic.append(") CH");
                                    logic.add(tempLogic);
                                    condition.add("CCP.CONTRACT_MASTER_SID=CH.CONTRACT_MASTER_SID");
                                } else if ("Market Type".equals(tempRow[3])) {
                                    StringBuilder tempLogic = new StringBuilder(Constant.SELECT_CMCONTRACT_MASTER_SID_FROM_CONTRACT);
                                    if (Constant.CONTRACT_MASTER.equals(tempRow[5])) {
                                        tempLogic.append(" WHERE CM.CONTRACT_TYPE=");
                                        tempLogic.append(tempRow[1]);
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(Constant.J1_WHERE_J1);
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND CM.CONTRACT_TYPE=J1.HELPER_TABLE_SID");
                                    }
                                    tempLogic.append(") MT");
                                    logic.add(tempLogic);
                                    condition.add("CCP.CONTRACT_MASTER_SID=MT.CONTRACT_MASTER_SID");
                                } else if ("Contract".equals(tempRow[3])) {
                                    StringBuilder tempLogic = new StringBuilder(Constant.SELECT_CMCONTRACT_MASTER_SID_FROM_CONTRACT);
                                    if (Constant.CONTRACT_MASTER.equals(tempRow[5])) {
                                        tempLogic.append(" WHERE CM.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append('\'');
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(Constant.J1_WHERE_J1);
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("=' ");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND CM.CONTRACT_MASTER_SID=J1.CONTRACT_MASTER_SID");
                                    }
                                    tempLogic.append(") CT");
                                    logic.add(tempLogic);
                                    condition.add("CCP.CONTRACT_MASTER_SID=CT.CONTRACT_MASTER_SID");
                                } else if (("Trading Partner".equals(tempRow[3]) || "Customer".equals(tempRow[3])) && "Customer Hierarchy".equals(tempRow[7])) {
                                    StringBuilder tempLogic = new StringBuilder(", (SELECT CM.COMPANY_MASTER_SID FROM COMPANY_MASTER CM");
                                    if ("COMPANY_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(" WHERE CM.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append('\'');
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(Constant.J1_WHERE_J1);
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND CM.COMPANY_MASTER_SID=J1.COMPANY_MASTER_SID");
                                    }
                                    tempLogic.append(") TP");
                                    logic.add(tempLogic);
                                    condition.add("CCP.COMPANY_MASTER_SID=TP.COMPANY_MASTER_SID");
                                } 
                                else if ("Brand".equals(tempRow[3])) {
                                    StringBuilder tempLogic = new StringBuilder(Constant.SELECT_IM_ITEM_MASTER_SID_FROM_ITEM_MASTER);
                                    if ("BRAND_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(" BM WHERE BM.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND IM.BRAND_MASTER_SID=BM.BRAND_MASTER_SID ");
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(Constant.J1_WHERE_J1);
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND IM.BRAND_MASTER_SID=J1.BRAND_MASTER_SID");
                                    }
                                    tempLogic.append(") BRAND");
                                    logic.add(tempLogic);
                                    condition.add("CCP.ITEM_MASTER_SID=BRAND.ITEM_MASTER_SID");
                                } else if ("Therapeutic Class".equals(tempRow[3])) {
                                    StringBuilder tempLogic = new StringBuilder(Constant.SELECT_IM_ITEM_MASTER_SID_FROM_ITEM_MASTER);
                                    if ("ITEM_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(" WHERE IM.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' ");
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(Constant.J1_WHERE_J1);
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND IM.THERAPEUTIC_CLASS=J1.");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append("_SID");
                                    }
                                    tempLogic.append(") TPC");
                                    logic.add(tempLogic);
                                    condition.add("CCP.ITEM_MASTER_SID=TPC.ITEM_MASTER_SID");
                                } else if ("Item".equals(tempRow[3]) || "NDC".equals(tempRow[3]) || Constant.PRODUCT_LABEL.equals(tempRow[3])) {
                                    StringBuilder tempLogic = new StringBuilder(Constant.SELECT_IM_ITEM_MASTER_SID_FROM_ITEM_MASTER);
                                    if ("ITEM_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(" WHERE IM.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append('\'');
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(Constant.J1_WHERE_J1);
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND IM.ITEM_MASTER_SID=J1.ITEM_MASTER_SID");
                                    }
                                    tempLogic.append(") ITEM");
                                    logic.add(tempLogic);
                                    condition.add("CCP.ITEM_MASTER_SID=ITEM.ITEM_MASTER_SID");
                                }
                                prevNo = currNo;
                                if (Objects.equals(level, currNo) || !nextHierarchyNo.contains(String.valueOf(tempRow[4])) || list.size() == i + 1) {
                                    ccpQuery.replace(ccpQuery.indexOf("?"), ccpQuery.indexOf("?") + 1, String.valueOf(tempRow[0]));
                                    for (int j = logic.size() - 1; j >= 0; j--) {
                                        ccpQuery.append(logic.get(j));
                                    }
                                    ccpQuery.append(" WHERE ");
                                    for (int j = condition.size() - 1; j >= 0; j--) {
                                        ccpQuery.append(condition.get(j));
                                        if (j != 0) {
                                            ccpQuery.append(Constant.SPACE_AND_SPACE);
                                        }
                                    }
                                    ccpQuery.append(") AS SOURCE ON (TARGET.RELATIONSHIP_LEVEL_SID=SOURCE.RELATIONSHIP_LEVEL_SID AND "
                                            + "TARGET.CCP_DETAILS_SID=SOURCE.CCP_DETAILS_SID) WHEN NOT MATCHED THEN INSERT "
                                            + "(RELATIONSHIP_LEVEL_SID, CCP_DETAILS_SID) VALUES ( SOURCE.RELATIONSHIP_LEVEL_SID, SOURCE.CCP_DETAILS_SID);");

                                    ccpQueryList.append(ccpQuery);
                                    count++;
                                    if (count == 10 || list.size() == i + 1) {
                                        HelperTableLocalServiceUtil.executeUpdateQuery(ccpQueryList.toString());
                                        ccpQueryList = new StringBuilder();
                                        count = 0;
                                    }
                                    ccpQuery = new StringBuilder("MERGE CCP_MAP AS TARGET USING ( "
                                            + "SELECT distinct ? RELATIONSHIP_LEVEL_SID ,CCP.CCP_DETAILS_SID FROM CCP_DETAILS CCP ");
                                }

                            }
                        }
                    }

            }
        } catch (Exception e) {
            LOGGER.error("In saveCcp ->= {}" , e.getMessage());
            LOGGER.error(hierarchyQuery);
            LOGGER.error(levelQuery);
            LOGGER.error(ccpQueryList.toString());
        }
    }

    public Object tempOperation(final Map<String, Object> input, final String queryName) {
        String customSql = SQlUtil.getQuery(getClass(),queryName);
        String finalQuery = StringUtils.EMPTY;
        StringBuilder finalQueryBuilder = new StringBuilder();
        try {
            Object temp;
            if ("getHierarchyTableDetails".equals(queryName)) {
                for (Map.Entry<String, Object> key : input.entrySet()) {
                    customSql = customSql.replace(key.getKey(), String.valueOf(key.getValue()));
                }
                List tempList = HelperTableLocalServiceUtil.executeSelectQuery(customSql);
                Map<String, String> valueList = new HashMap<>();

                for (int i = tempList.size() - 1; i >= 0; i--) {
                    customSql = SQlUtil.getQuery(getClass(),"getRelationshipLevelValues");
                    Object[] tempListObject = (Object[]) tempList.get(i);
                    customSql = customSql.replace("?FIELD", String.valueOf(tempListObject[0]));
                    customSql = customSql.replace("?TABLE", String.valueOf(tempListObject[1]));
                    customSql = customSql.replace("?IDCOL", String.valueOf(tempListObject[2]));
                    customSql = customSql.replace("?LNO", String.valueOf(tempListObject[3]));
                    customSql = customSql.replace("?RBSID", String.valueOf(input.get("?RBSID")));
                    if (i != 0) {
                        customSql = customSql.concat(" UNION ALL ");
                    }
                    finalQueryBuilder.append(customSql);
                    
                }
                finalQuery = finalQueryBuilder.toString();
                List tempValueList = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
                for (int j = tempValueList.size() - 1; j >= 0; j--) {
                    Object[] tempObject = (Object[]) tempValueList.get(j);
                    valueList.put(String.valueOf(tempObject[0]), String.valueOf(tempObject[1]));
                }
                temp = valueList;
            } else {
                for (Map.Entry<String, Object> key : input.entrySet()) {
                    if (customSql.contains(key.getKey())) {
                        customSql = customSql.replace(key.getKey(), String.valueOf(key.getValue()));
                    }
                }
                if ("ds.singleBrand".equals(queryName)) {
                    temp = HelperTableLocalServiceUtil.executeSelectQuery(customSql);
                } else {
                    temp = HelperTableLocalServiceUtil.executeUpdateQueryCount(customSql);
                }

            }
            return temp;
        } catch (Exception e) {
            LOGGER.error("In tempOperation ->= {}" , e.getMessage());
            LOGGER.error(customSql);
            return Collections.emptyList();
        }
    }

    public List getChildLevels(final Map<String, Object> parameters) {
        StringBuilder queryBuilder = new StringBuilder();

        try {
            String finalQuery;
            if (parameters.containsKey(Constant.BUSINESS_UNIT)) {
                finalQuery = SQlUtil.getQuery(getClass(),"getChildLevelsBasedonBU");
                finalQuery = finalQuery.replace("@HIERNO1", String.valueOf(parameters.get(Constant.HIERARACHY_NO)));
                finalQuery = finalQuery.replace("@HIERNO2", String.valueOf(parameters.get(Constant.HIERARACHY_NO)));
                finalQuery = finalQuery.replace("@LVLNO", String.valueOf(parameters.get("lowestLevelNo")));
                finalQuery = finalQuery.replace("@BU_COMPANY_MASTER_SID", String.valueOf(parameters.get(Constant.BUSINESS_UNIT)));

            } else {
                queryBuilder.append(SQlUtil.getQuery(getClass(),"getChildLevelsWithHierarchyNo"));
                queryBuilder.replace(queryBuilder.indexOf("?"), queryBuilder.indexOf("?") + 1, String.valueOf(parameters.get(Constant.HIERARACHY_NO)));
                queryBuilder.replace(queryBuilder.indexOf("?"), queryBuilder.indexOf("?") + 1, String.valueOf(parameters.get(Constant.HIERARACHY_NO)));
                queryBuilder.replace(queryBuilder.indexOf("?"), queryBuilder.indexOf("?") + 1, String.valueOf(parameters.get("lowestLevelNo")));
                finalQuery = queryBuilder.toString();
            }
            return HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
        } catch (Exception ex) {
            LOGGER.error("In getChildLevels ->= {}" , ex.getMessage());
            LOGGER.error(queryBuilder.toString());
            return Collections.emptyList();
        }
    }

    public List executeQueryforchannel(final Map<String, Object> parameters) {
        StringBuilder queryString = new StringBuilder();
        if (parameters.get(Constant.INDICATOR) != null && Constant.HAS_TRADING_PARTNER.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
            queryString.append(SQlUtil.getQuery(getClass(), Constant.HAS_TRADING_PARTNER));
            queryString.append('\'');
            queryString.append(String.valueOf(parameters.get(Constant.PROJECTION_ID)));
            queryString.append('\'');
        } else if (parameters.get(Constant.INDICATOR) != null && Constant.UNSAVED_PROJECTION_IDS.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
            queryString.append(SQlUtil.getQuery(getClass(), Constant.UNSAVED_PROJECTION_IDS));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("deleteDate")));
        } else if (parameters.get(Constant.INDICATOR) != null && Constant.CHILD_LEVEL_RL_SID.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
            if (parameters.get(Constant.RL_SIDS) != null) {
                ArrayList<String> rlSids = (ArrayList<String>) parameters.get(Constant.RL_SIDS);
                if (rlSids != null && !rlSids.isEmpty()) {
                    queryString.append(SQlUtil.getQuery(getClass(),"getChildLevelRLSidRestricted"));
                    queryString.append(" WHERE (");
                    for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
                        queryString.append(Constant.HIERARCHY_NO_LIKE);
                        queryString.append(rlSids.get(loop));
                        queryString.append("%'");
                        if (loop != (limit - 1)) {
                            queryString.append(" OR ");
                        }
                    }
                    queryString.append(") AND HIERARCHY_NO NOT IN (");
                    queryString.append(CommonUtils.stringListToString(rlSids));
                    queryString.append(')');
                    queryString.append(" AND RLD.RELATIONSHIP_LEVEL_SID not in (SELECT PH.RELATIONSHIP_LEVEL_SID FROM ");
                    queryString.append(String.valueOf(parameters.get(Constant.TABLE_NAME)));
                    queryString.append(" PH WHERE PH.PROJECTION_MASTER_SID = ");
                    queryString.append(String.valueOf(parameters.get(Constant.PROJECTION_ID)));
                    queryString.append(')');
                }
            }
        } else if (parameters.get(Constant.INDICATOR) != null && Constant.CHILD_LEVEL_RL.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
            ArrayList<String> rlSids = (ArrayList<String>) parameters.get(Constant.RL_SIDS);
            if (rlSids != null && !rlSids.isEmpty()) {
                queryString.append(SQlUtil.getQuery(getClass(), Constant.CHILD_LEVEL_RL));
                queryString.append(" and (");
                for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
                    queryString.append("HIERARCHY_NO like '");
                    queryString.append(rlSids.get(loop));
                    queryString.append("%'");
                    if (loop != (limit - 1)) {
                        queryString.append(" or ");
                    }
                }
                queryString.append(") and HIERARCHY_NO not in (");
                queryString.append(CommonUtils.stringListToString(rlSids));
                if (parameters.get(Constant.AVAILABLE_HIER_NO) != null) {
                    ArrayList<String> availableHierNo = (ArrayList<String>) parameters.get(Constant.AVAILABLE_HIER_NO);
                    queryString.append(", ");
                    queryString.append(CommonUtils.stringListToString(availableHierNo));
                }
                queryString.append(") ");
            }
        } else if (parameters.get(Constant.INDICATOR) != null && Constant.GET_REMOVABLE_CHILDREN.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
            queryString.append(SQlUtil.getQuery(getClass(), Constant.GET_REMOVABLE_CHILDREN));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get(Constant.REMOVE_LEVELS)));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get(Constant.REMOVE_LEVELS)));
        } else if (parameters.get(Constant.INDICATOR) != null && Constant.DELETE_TEMP_ON_UPDATE.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
            String hNos = String.valueOf(parameters.get("hNos"));
            String projectionId = String.valueOf(parameters.get(Constant.PROJECTION_ID));
            String projectionHierarchyTable = String.valueOf(parameters.get("projectionHierarchyTable"));
            for (String table : CommonUtils.getTempTableList()) {
                queryString.append(SQlUtil.getQuery(getClass(), Constant.DELETE_TEMP_ON_UPDATE));
                queryString.replace(queryString.indexOf(Constant.DTBL), queryString.indexOf(Constant.DTBL) + 5, table);
                queryString.replace(queryString.indexOf(Constant.PID), queryString.indexOf(Constant.PID) + 4, projectionId);
                queryString.replace(queryString.indexOf(Constant.HTBL), queryString.indexOf(Constant.HTBL) + 5, projectionHierarchyTable);
                queryString.replace(queryString.indexOf(Constant.PID), queryString.indexOf(Constant.PID) + 4, projectionId);
                queryString.replace(queryString.indexOf(Constant.HTBL), queryString.indexOf(Constant.HTBL) + 5, projectionHierarchyTable);
                queryString.replace(queryString.indexOf(Constant.PID), queryString.indexOf(Constant.PID) + 4, projectionId);
                queryString.replace(queryString.indexOf(Constant.HNO), queryString.indexOf(Constant.HNO) + 4, hNos);
                queryString.replace(queryString.indexOf(Constant.PID), queryString.indexOf(Constant.PID) + 4, projectionId);
            }
        } else if (parameters.get(Constant.INDICATOR) != null && Constant.GET_FS_VALUE.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
            queryString.append(SQlUtil.getQuery(getClass(), Constant.GET_FS_VALUE));
            queryString.replace(queryString.indexOf(Constant.RLC), queryString.indexOf(Constant.RLC) + 5, String.valueOf(parameters.get("relationshipLevelValue")));
        } else if (parameters.get(Constant.INDICATOR) != null && Constant.COMPANY_FILTER.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
            queryString.append(SQlUtil.getQuery(getClass(), Constant.COMPANY_FILTER));
            queryString.append('\'');
            queryString.append(String.valueOf(parameters.get(Constant.COMPANY_SID)));
            queryString.append('\'');
        } else if (parameters.get(Constant.INDICATOR) != null
                && (Constant.GET_HIERARCHY_GROUP.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))
                || "getHierarchyGroupCount".equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR))))) { //searchGroup
            String query = SQlUtil.getQuery(getClass(), Constant.GET_HIERARCHY_GROUP);

            if (parameters.get(Constant.ACTION) != null && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ACTION)))
                    && !Constant.COUNT.equals(String.valueOf(parameters.get(Constant.ACTION)))) {
                query = query.replace(Constant.SELECTION, " C.HIERARCHY_DEFINITION_SID AS SID, C.HIERARCHY_NAME AS NAME, A.LEVEL_NAME AS LEVEL, "
                        + "A.LEVEL_NO AS HIGH_LEVEL, B.LEVEL_NO AS LOW_LEVEL, C.CREATED_DATE AS CREATED_DATE, C.MODIFIED_DATE AS MODIFIED_DATE, C.VERSION_NO AS VERSION ");
            } else {
                query = query.replace(Constant.SELECTION, " COUNT(C.HIERARCHY_DEFINITION_SID) AS SID_COUNT ");
            }

            if (!StringUtils.isBlank(String.valueOf(parameters.get("customerOrProduct"))) && "Customer Hierachy".equals(String.valueOf(parameters.get("customerOrProduct")))) {
                query = query.replace("?HIERARCHY_TYPE?", "Customer Hierarchy");
            } else {
                query = query.replace("?HIERARCHY_TYPE?", "Product Hierarchy");
            }

            if (parameters.get(Constant.HIERARCHY_NAME) != null && !"null".equals(String.valueOf(parameters.get(Constant.HIERARCHY_NAME))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.HIERARCHY_NAME)))) {
                query = query.replace("?HIERARCHY_NAME_CONDITION?", " AND C.HIERARCHY_NAME like '" + String.valueOf(parameters.get(Constant.HIERARCHY_NAME)) + "' ");
            } else {
                query = query.replace("?HIERARCHY_NAME_CONDITION?", StringUtils.EMPTY);
            }
            if (parameters.get(Constant.HIERARCHY_TYPE) != null && !"null".equals(String.valueOf(parameters.get(Constant.HIERARCHY_TYPE))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.HIERARCHY_TYPE)))) {
                query = query.replace("?HIERARCHY_TYPE_CONDITION?", " AND C.HIERARCHY_TYPE in (SELECT HT.HELPER_TABLE_SID FROM HELPER_TABLE HT where HT.DESCRIPTION LIKE '"
                        + String.valueOf(parameters.get(Constant.HIERARCHY_TYPE)) + "') ");
            } else {
                query = query.replace("?HIERARCHY_TYPE_CONDITION?", StringUtils.EMPTY);
            }

            try {
                if (parameters.get(Constant.ACTION) != null && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ACTION)))
                        && !Constant.COUNT.equals(String.valueOf(parameters.get(Constant.ACTION)))) {
                    if (String.valueOf(parameters.get(Constant.IS_FILTERED)).equals("true")) {
                        StringBuilder filterAppender = new StringBuilder();
                        if ((parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM))))
                                && (parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO))))) {
                            filterAppender.append(" AND C.CREATED_DATE BETWEEN '");
                            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(Constant.MM_DD_YYYY);
                            String from = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM))));
                            filterAppender.append(from);
                            filterAppender.append(Constant.UNDERSCORE_AND_UNDERSCORE);
                            String to = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM) == null || Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM)))
                                || StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM))))
                                && (parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO))))) {
                            filterAppender.append(" AND C.CREATED_DATE < '");
                            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(Constant.MM_DD_YYYY);
                            String to = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM))))
                                && (parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO) == null || Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO)))
                                || StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO))))) {
                            filterAppender.append(" AND C.CREATED_DATE > '");
                            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(Constant.MM_DD_YYYY);
                            String from = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM))));
                            filterAppender.append(from);
                            filterAppender.append("' ");
                        }

                        if (parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM)))
                                && parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO)))) {
                            filterAppender.append(" AND C.MODIFIED_DATE BETWEEN '");
                            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(Constant.MM_DD_YYYY);
                            String from = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM))));
                            String to = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO))));
                            filterAppender.append(from);
                            filterAppender.append(Constant.UNDERSCORE_AND_UNDERSCORE);
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM) == null || Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM)))
                                || StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM))))
                                && (parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO))))) {
                            filterAppender.append(" AND C.MODIFIED_DATE < '");
                            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(Constant.MM_DD_YYYY);
                            String to = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM))))
                                && (parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO) == null || Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO)))
                                || StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO))))) {
                            filterAppender.append(" AND C.MODIFIED_DATE > '");
                            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(Constant.MM_DD_YYYY);
                            String from = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM))));
                            filterAppender.append(from);
                            filterAppender.append("' ");
                        }
                        if (parameters.get(Constant.FILTER_HIERARCHY_NAME) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_HIERARCHY_NAME)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_HIERARCHY_NAME)))) {
                            filterAppender.append(" AND C.HIERARCHY_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_HIERARCHY_NAME)));
                            filterAppender.append("' ");
                        }

                        if (parameters.get(Constant.FILTER_HIGHEST_LEVEL) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_HIGHEST_LEVEL)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_HIGHEST_LEVEL)))) {
                            filterAppender.append(" AND A.LEVEL_NO like '");
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_HIGHEST_LEVEL)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(Constant.FILTER_LOWEST_LEVEL) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_LOWEST_LEVEL)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_LOWEST_LEVEL)))) {
                            filterAppender.append(" AND B.LEVEL_NO like '");
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_LOWEST_LEVEL)));
                            filterAppender.append("' ");
                        }
                        query = query.replace(Constant.QUESTION_FILTER_QUESTION, filterAppender.toString());
                    } else {
                        query = query.replace(Constant.QUESTION_FILTER_QUESTION, StringUtils.EMPTY);
                    }
                    if ((parameters.get(Constant.IS_ORDERED) == null || Constant.FALSE.equalsIgnoreCase(String.valueOf(parameters.get(Constant.IS_ORDERED))))) {
                        query = query.replace(Constant.ORDER_BY, " ORDER BY C.CREATED_DATE ");
                    } else if (parameters.get(Constant.IS_ORDERED) != null && "true".equalsIgnoreCase(String.valueOf(parameters.get(Constant.IS_ORDERED)))) {
                        StringBuilder orderByAppender = new StringBuilder();
                        if (parameters.get(Constant.ORDER_BY_HIERARCHY_NAME) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_HIERARCHY_NAME)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_HIERARCHY_NAME)))) {
                            orderByAppender.append(" ORDER BY C.HIERARCHY_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_HIERARCHY_NAME)));
                        }

                        if (parameters.get(Constant.ORDER_BY_HIGHEST_LEVEL) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_HIGHEST_LEVEL)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_HIGHEST_LEVEL)))) {
                            orderByAppender.append(" ORDER BY A.LEVEL_NO ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_HIGHEST_LEVEL)));
                        }

                        if (parameters.get(Constant.ORDER_BY_LOWEST_LEVEL) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_LOWEST_LEVEL)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_LOWEST_LEVEL)))) {
                            orderByAppender.append(" ORDER BY B.LEVEL_NO ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_LOWEST_LEVEL)));
                        }

                        if (parameters.get(Constant.ORDER_BY_CREATED_DATE_SEARCH) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_CREATED_DATE_SEARCH)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_CREATED_DATE_SEARCH)))) {
                            orderByAppender.append(" ORDER BY C.CREATED_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_CREATED_DATE_SEARCH)));
                        }

                        if (parameters.get(Constant.ORDER_BY_MODIFIED_DATE_SEARCH) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_MODIFIED_DATE_SEARCH)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_MODIFIED_DATE_SEARCH)))) {
                            orderByAppender.append(" ORDER BY C.MODIFIED_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_MODIFIED_DATE_SEARCH)));
                        }
                        query = query.replace(Constant.ORDER_BY, orderByAppender.toString());
                    }
                    if (parameters.get(Constant.START) != null && parameters.get(Constant.OFFSET) != null) {
                        int startIndex = Integer.parseInt(String.valueOf(parameters.get(Constant.START)));
                        int offset = Integer.parseInt(String.valueOf(parameters.get(Constant.OFFSET)));
                        query = query + Constant.OFFSET_WITH_CAPS + startIndex + Constant.ROWS_FETCH_NEXT + offset + Constant.SPACE_ROWS_ONLY_SPACE;
                    }

                } else {
                    query = query.replace(Constant.ORDER_BY, StringUtils.EMPTY);
                    query = query.replace(Constant.QUESTION_FILTER_QUESTION, StringUtils.EMPTY);
                }

            } catch (NumberFormatException | ParseException ex) {
                LOGGER.error("executeQueryforchannel= {}", ex.getMessage());
            }
            queryString.append(query);
            queryString.append(';');
        } else if (parameters.get(Constant.INDICATOR) != null
                && ("searchGroup".equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))
                || "searchGroupCount".equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR))))) {
            queryString.append(prepareSearchGroupQuery(parameters));
        } else if (parameters.get(Constant.INDICATOR) != null
                && ("searchView".equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))
                || "searchViewCount".equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR))))) {
            queryString.append(prepareSearchViewQuery(parameters));
        } else {
            queryString.append(String.valueOf(parameters.get("query")));
        }
        try {
            if (parameters.get(Constant.INDICATOR) != null
                    && ((Constant.DELETE_TEMP_ON_UPDATE.equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))))) {
                List<Integer> list = new ArrayList<>();
                int returnValue = HelperTableLocalServiceUtil.executeUpdateQueryCount(queryString.toString()) ;
                list.add(returnValue);
                return list;
            } else {
                return HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());
            }
        } catch (Exception ex) {
            LOGGER.error("in execute query channel= {}", ex.getMessage());
            LOGGER.error(queryString.toString());
            return Collections.emptyList();
        } 
    }
    

    private String prepareSearchGroupQuery(final Map<String, Object> parameters) {
        LOGGER.debug("Entering prepareSearchGroupQuery method");
        String query = StringUtils.EMPTY;
        try {
            String name = null;
            String no = null;
            String desc = null;
            String sids = null;
            StringBuilder queryString = new StringBuilder();
            if (parameters.get(Constant.GROUP_IDENTIFIER) != null && Constants.INDICATOR_CUSTOMER_GROUP.equalsIgnoreCase(String.valueOf(parameters.get(Constant.GROUP_IDENTIFIER)))) {
                queryString.append(SQlUtil.getQuery(getClass(),"getCustomerGroupPaged"));
                name = " CG.COMPANY_GROUP_NAME ";
                no = " CG.COMPANY_GROUP_NO ";
                desc = " CG.COMPANY_GROUP_DESCRIPTION ";
                sids = " CGD.COMPANY_MASTER_SID ";
            } else {
                queryString.append(SQlUtil.getQuery(getClass(),"getProductGroupPaged"));
                no = " IG.ITEM_GROUP_NO ";
                name = " IG.ITEM_GROUP_NAME ";
                desc = " IG.ITEM_GROUP_DESCRIPTION ";
                sids = " IGD.ITEM_MASTER_SID ";
            }
            if (parameters.get("no") != null
                    && !StringUtils.isEmpty(String.valueOf(parameters.get("no")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("no")))) {
                queryString.append(Constant.SPACE_AND_SPACE);
                queryString.append(no);
                queryString.append(" LIKE '");
                queryString.append(String.valueOf(parameters.get("no")));
                queryString.append('\'');
            }
            if (parameters.get("name") != null
                    && !StringUtils.isEmpty(String.valueOf(parameters.get("name")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("name")))) {
                queryString.append(Constant.SPACE_AND_SPACE);
                queryString.append(name);
                queryString.append(" LIKE '");
                queryString.append(String.valueOf(parameters.get("name")));
                queryString.append('\'');
            }
            if (parameters.get("sids") != null && !"null".equals(String.valueOf(parameters.get("sids")))) {
                queryString.append(Constant.SPACE_AND_SPACE);
                queryString.append(sids);
                queryString.append(" in (");
                queryString.append(String.valueOf(parameters.get("sids")));
                queryString.append(')');
            }

            queryString.append(Constant.QUESTION_FILTER_QUESTION);
            queryString.append(Constant.ORDER_BY);

            query = queryString.toString();
                if (parameters.get(Constant.ACTION) != null && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ACTION)))
                        && !Constant.COUNT.equals(String.valueOf(parameters.get(Constant.ACTION)))) {
                    if (parameters.get(Constant.GROUP_IDENTIFIER) != null && Constants.INDICATOR_CUSTOMER_GROUP.equalsIgnoreCase(String.valueOf(parameters.get(Constant.GROUP_IDENTIFIER)))) {
                        query = query.replace(Constant.SELECTION, " DISTINCT CG.COMPANY_GROUP_SID, CG.COMPANY_GROUP_NO, CG.COMPANY_GROUP_NAME, CG.VERSION_NO, CG.COMPANY_GROUP_DESCRIPTION ");
                    } else {
                        query = query.replace(Constant.SELECTION, " DISTINCT IG.ITEM_GROUP_SID, IG.ITEM_GROUP_NO, IG.ITEM_GROUP_NAME, CM.COMPANY_NAME, IG.VERSION_NO, IG.ITEM_GROUP_DESCRIPTION ");
                    }
                    if (String.valueOf(parameters.get(Constant.IS_FILTERED)).equals("true")) {
                        StringBuilder filterAppender = new StringBuilder();
                        if (parameters.get(Constant.FILTER_CUSTOMER_GROUP_NAME) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_GROUP_NAME)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_GROUP_NAME)))) {
                            filterAppender.append(Constant.SPACE_AND_SPACE);
                            filterAppender.append(name);
                            filterAppender.append(Constant.SPACE_LIKE_SPACE);
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_GROUP_NAME)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(Constant.FILTER_PRODUCT_GROUP_NAME) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_GROUP_NAME)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_GROUP_NAME)))) {
                            filterAppender.append(Constant.SPACE_AND_SPACE);
                            filterAppender.append(name);
                            filterAppender.append(Constant.SPACE_LIKE_SPACE);
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_GROUP_NAME)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(Constant.FILTER_CUSTOMER_GROUP_NO) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_GROUP_NO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_GROUP_NO)))) {
                            filterAppender.append(Constant.SPACE_AND_SPACE);
                            filterAppender.append(no);
                            filterAppender.append(Constant.SPACE_LIKE_SPACE);
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_GROUP_NO)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(Constant.FILTER_PRODUCT_GROUP_NO) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_GROUP_NO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_GROUP_NO)))) {
                            filterAppender.append(Constant.SPACE_AND_SPACE);
                            filterAppender.append(no);
                            filterAppender.append(Constant.SPACE_LIKE_SPACE);
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_GROUP_NO)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(Constant.FILTER_CUSTOMER_GROUP_DESCRIPTION) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_GROUP_DESCRIPTION)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_GROUP_DESCRIPTION)))) {
                            filterAppender.append(Constant.SPACE_AND_SPACE);
                            filterAppender.append(desc);
                            filterAppender.append(Constant.SPACE_LIKE_SPACE);
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_GROUP_DESCRIPTION)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(Constant.FILTER_PRODUCT_GROUP_DESCRIPTION) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_GROUP_DESCRIPTION)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_GROUP_DESCRIPTION)))) {
                            filterAppender.append(Constant.SPACE_AND_SPACE);
                            filterAppender.append(desc);
                            filterAppender.append(Constant.SPACE_LIKE_SPACE);
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_GROUP_DESCRIPTION)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(Constant.FILTER_COMPANY) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_COMPANY)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_COMPANY)))) {
                            filterAppender.append(" AND CM.COMPANY_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_COMPANY)));
                            filterAppender.append("' ");
                        }
                        query = query.replace(Constant.QUESTION_FILTER_QUESTION, filterAppender.toString());
                    } else {
                        query = query.replace(Constant.QUESTION_FILTER_QUESTION, StringUtils.EMPTY);
                    }
                    if ((parameters.get(Constant.IS_ORDERED) == null || Constant.FALSE.equalsIgnoreCase(String.valueOf(parameters.get(Constant.IS_ORDERED))))) {
                        if (parameters.get(Constant.GROUP_IDENTIFIER) != null && Constants.INDICATOR_CUSTOMER_GROUP.equalsIgnoreCase(String.valueOf(parameters.get(Constant.GROUP_IDENTIFIER)))) {
                            query = query.replace(Constant.ORDER_BY, " ORDER BY CG.COMPANY_GROUP_SID ");
                        } else {
                            query = query.replace(Constant.ORDER_BY, " ORDER BY IG.ITEM_GROUP_SID ");
                        }
                    } else if (parameters.get(Constant.IS_ORDERED) != null && "true".equalsIgnoreCase(String.valueOf(parameters.get(Constant.IS_ORDERED)))) {
                        StringBuilder orderByAppender = new StringBuilder();
                        if (parameters.get(Constant.ORDER_BY_CUSTOMER_GROUP_NAME) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_GROUP_NAME)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_GROUP_NAME)))) {
                            orderByAppender.append(" ORDER BY CG.COMPANY_GROUP_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_GROUP_NAME)));
                        }
                        if (parameters.get(Constant.ORDER_BY_CUSTOMER_GROUP_NO) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_GROUP_NO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_GROUP_NO)))) {
                            orderByAppender.append(" ORDER BY CG.COMPANY_GROUP_NO ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_GROUP_NO)));
                        }
                        if (parameters.get(Constant.ORDER_BY_CUSTOMER_GROUP_DESCRIPTION) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_GROUP_DESCRIPTION)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_GROUP_DESCRIPTION)))) {
                            orderByAppender.append(" ORDER BY CG.COMPANY_GROUP_DESCRIPTION ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_GROUP_DESCRIPTION)));
                        }
                        if (parameters.get(Constant.ORDER_BY_PRODUCT_GROUP_NAME) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_GROUP_NAME)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_GROUP_NAME)))) {
                            orderByAppender.append(" ORDER BY IG.ITEM_GROUP_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_GROUP_NAME)));
                        }
                        if (parameters.get(Constant.ORDER_BY_PRODUCT_GROUP_NO) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_GROUP_NO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_GROUP_NO)))) {
                            orderByAppender.append(" ORDER BY IG.ITEM_GROUP_NO ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_GROUP_NO)));
                        }
                        if (parameters.get(Constant.ORDER_BY_PRODUCT_GROUP_DESCRIPTION) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_GROUP_DESCRIPTION)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_GROUP_DESCRIPTION)))) {
                            orderByAppender.append(" ORDER BY IG.ITEM_GROUP_DESCRIPTION ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_GROUP_DESCRIPTION)));
                        }
                        if (parameters.get(Constant.ORDER_BYCOMPANY) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BYCOMPANY)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BYCOMPANY)))) {
                            orderByAppender.append(" ORDER BY CM.COMPANY_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BYCOMPANY)));
                        }
                        query = query.replace(Constant.ORDER_BY, orderByAppender.toString());
                    }
                    if (parameters.get(Constant.START) != null && parameters.get(Constant.OFFSET) != null) {
                        int startIndex = Integer.parseInt(String.valueOf(parameters.get(Constant.START)));
                        int offset = Integer.parseInt(String.valueOf(parameters.get(Constant.OFFSET)));
                        query = query + Constant.OFFSET_WITH_CAPS + startIndex + Constant.ROWS_FETCH_NEXT + offset + Constant.SPACE_ROWS_ONLY_SPACE;
                    }
                } else {
                    if (parameters.get(Constant.GROUP_IDENTIFIER) != null && Constants.INDICATOR_CUSTOMER_GROUP.equalsIgnoreCase(String.valueOf(parameters.get(Constant.GROUP_IDENTIFIER)))) {
                        query = query.replace(Constant.SELECTION, " COUNT (DISTINCT CG.COMPANY_GROUP_SID) ");
                    } else {
                        query = query.replace(Constant.SELECTION, " COUNT (DISTINCT IG.ITEM_GROUP_SID) ");
                    }
                    query = query.replace(Constant.ORDER_BY, StringUtils.EMPTY);
                    query = query.replace(Constant.QUESTION_FILTER_QUESTION, StringUtils.EMPTY);
                }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        }
        return query;

    }

    private String prepareSearchViewQuery(final Map<String, Object> parameters) {
        String query = StringUtils.EMPTY;
        try {
            StringBuilder queryString = new StringBuilder(SQlUtil.getQuery(getClass(),"findViewByNameJoin"));
            if (parameters.get(Constant.VIEW_TYPE) != null
                    && !StringUtils.isEmpty(String.valueOf(parameters.get(Constant.VIEW_TYPE)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.VIEW_TYPE)))) {
                queryString.append(" FVM.VIEW_TYPE LIKE '");
                queryString.append(String.valueOf(parameters.get(Constant.VIEW_TYPE)));
                queryString.append("' ");

                if (String.valueOf(parameters.get(Constant.VIEW_TYPE)).equalsIgnoreCase("private")
                        && parameters.get(Constant.USER_ID) != null
                        && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.USER_ID)))) {
                    queryString.append(" AND FVM.CREATED_BY = ");
                    queryString.append(String.valueOf(parameters.get(Constant.USER_ID)));
                }

            }
            if (parameters.get(Constant.VIEW_NAME) != null
                    && !StringUtils.isEmpty(String.valueOf(parameters.get(Constant.VIEW_NAME)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.VIEW_NAME)))) {
                queryString.append(" AND FVM.VIEW_NAME LIKE '");
                queryString.append(String.valueOf(parameters.get(Constant.VIEW_NAME)));
                queryString.append("' ");
            }

            if (parameters.get(Constant.FORECAST_TYPE) != null
                    && !StringUtils.isEmpty(String.valueOf(parameters.get(Constant.FORECAST_TYPE)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FORECAST_TYPE)))) {
                queryString.append(" AND PM.FORECASTING_TYPE ='");
                queryString.append(String.valueOf(parameters.get(Constant.FORECAST_TYPE)));
                queryString.append("' ");
            }

            queryString.append(Constant.QUESTION_FILTER_QUESTION);
            queryString.append(Constant.ORDER_BY);
            query = queryString.toString();

                if (parameters.get(Constant.ACTION) != null && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ACTION)))
                        && !Constant.COUNT.equals(String.valueOf(parameters.get(Constant.ACTION)))) {
                    query = query.replace(Constant.SELECTION, SQlUtil.getQuery(getClass(),"searchViewFindSelection"));
                    if (String.valueOf(parameters.get(Constant.IS_FILTERED)).equals("true")) {
                        StringBuilder filterAppender = new StringBuilder();
                        if ((parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM))))
                                && (parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO))))) {
                            filterAppender.append(Constant.AND_PMCREATED_DATE_BETWEEN);
                            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(Constant.MM_DD_YYYY);
                            String from = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM))));
                            filterAppender.append(from);
                            filterAppender.append(Constant.UNDERSCORE_AND_UNDERSCORE);
                            String to = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM) == null || Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM)))
                                || StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM))))
                                && (parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO))))) {
                            filterAppender.append(" AND PM.CREATED_DATE < '");
                            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(Constant.MM_DD_YYYY);
                            String to = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM))))
                                && (parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO) == null || Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO)))
                                || StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_TO))))) {
                            filterAppender.append(" AND PM.CREATED_DATE > '");
                            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(Constant.MM_DD_YYYY);
                            String from = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_CREATED_DATE_SEARCH_FROM))));
                            filterAppender.append(from);
                            filterAppender.append("' ");
                        }

                        if (parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM)))
                                && parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO)))) {
                            filterAppender.append(" AND PM.MODIFIED_DATE BETWEEN '");
                            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(Constant.MM_DD_YYYY);
                            String from = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM))));
                            String to = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO))));
                            filterAppender.append(from);
                            filterAppender.append(Constant.UNDERSCORE_AND_UNDERSCORE);
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM) == null || Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM)))
                                || StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM))))
                                && (parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO))))) {
                            filterAppender.append(" AND PM.MODIFIED_DATE < '");
                            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(Constant.MM_DD_YYYY);
                            String to = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM))))
                                && (parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO) == null || Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO)))
                                || StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_TO))))) {
                            filterAppender.append(" AND PM.MODIFIED_DATE > '");
                            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(Constant.MM_DD_YYYY);
                            String from = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTER_MODIFIED_DATE_SEARCH_FROM))));
                            filterAppender.append(from);
                            filterAppender.append("' ");
                        }
                        if (parameters.get(Constant.FILTER_VIEW_NAME) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_VIEW_NAME)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_VIEW_NAME)))) {
                            filterAppender.append(" AND FVM.VIEW_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_VIEW_NAME)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(Constant.FILTER_VIEW_NAME) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_DESCRIPTION)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_DESCRIPTION)))) {
                            filterAppender.append(Constant.AND_PMPROJECTION_DESCRIPTION_LIKE);
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_DESCRIPTION)));
                            filterAppender.append("' ");
                        }

                        if (parameters.get(Constant.FILTER_CUSTOMER_HIERARCHY) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_HIERARCHY)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_HIERARCHY)))) {
                            filterAppender.append(" AND CUST_HD.HIERARCHY_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_HIERARCHY)));
                            filterAppender.append("' ");
                        }

                        if (parameters.get(Constant.FILTER_CUSTOMER_LEVEL) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_LEVEL)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_LEVEL)))) {
                            filterAppender.append(" AND PM.CUSTOMER_HIERARCHY_LEVEL like '");
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_LEVEL)));
                            filterAppender.append("' ");
                        }

                        if (parameters.get(Constant.FILTER_CUSTOMER_GROUP) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_GROUP)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_GROUP)))) {
                            filterAppender.append(" AND CMG.COMPANY_GROUP_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_CUSTOMER_GROUP)));
                            filterAppender.append("' ");
                        }

                        if (parameters.get(Constant.FILTER_COMPANY) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_COMPANY)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_COMPANY)))) {
                            filterAppender.append(" AND CM.COMPANY_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_COMPANY)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(Constant.FILTER_BRAND_TYPE) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_BRAND_TYPE)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_BRAND_TYPE)))) {
                            filterAppender.append(" AND PM.BRAND_TYPE like '");
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_BRAND_TYPE)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(Constant.FILTER_PRODUCT_HIERARCHY) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_HIERARCHY)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_HIERARCHY)))) {
                            filterAppender.append(" AND PROD_HD.HIERARCHY_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_HIERARCHY)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(Constant.FILTER_PRODUCT_LEVEL) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_LEVEL)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_LEVEL)))) {
                            filterAppender.append(" AND PM.PRODUCT_HIERARCHY_LEVEL like '");
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_LEVEL)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(Constant.FILTER_PRODUCT_GROUP) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_GROUP)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_GROUP)))) {
                            filterAppender.append(" AND IG.ITEM_GROUP_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_GROUP)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(Constant.FILTER_CREATED_BY) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTER_CREATED_BY)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTER_CREATED_BY)))) {
                            filterAppender.append(" AND PM.CREATED_BY in (");
                            filterAppender.append(String.valueOf(parameters.get(Constant.FILTER_CREATED_BY)));
                            filterAppender.append(") ");
                        }

                        query = query.replace(Constant.QUESTION_FILTER_QUESTION, filterAppender.toString());
                    } else {
                        query = query.replace(Constant.QUESTION_FILTER_QUESTION, StringUtils.EMPTY);
                    }
                    if ((parameters.get(Constant.IS_ORDERED) == null || Constant.FALSE.equalsIgnoreCase(String.valueOf(parameters.get(Constant.IS_ORDERED))))) {
                        query = query.replace(Constant.ORDER_BY, Constant.ORDER_BY_PM_CREATED_DATE);
                    } else if (parameters.get(Constant.IS_ORDERED) != null && "true".equalsIgnoreCase(String.valueOf(parameters.get(Constant.IS_ORDERED)))) {
                        StringBuilder orderByAppender = new StringBuilder();
                        if (parameters.get(Constant.ORDER_BY_VIEW_NAME) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_VIEW_NAME)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_VIEW_NAME)))) {
                            orderByAppender.append(" ORDER BY FVM.VIEW_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_VIEW_NAME)));
                        }

                        if (parameters.get(Constant.ORDER_BY_DESCRIPTION) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_DESCRIPTION)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_DESCRIPTION)))) {
                            orderByAppender.append(" ORDER BY PM.PROJECTION_DESCRIPTION ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_DESCRIPTION)));
                        }

                        if (parameters.get(Constant.ORDER_BY_FROM) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_FROM)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_FROM)))) {
                            orderByAppender.append(" ORDER BY PM.Constant.FROM_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_FROM)));
                        }

                        if (parameters.get(Constant.ORDER_BY_TO) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_TO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_TO)))) {
                            orderByAppender.append(" ORDER BY PM.TO_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_TO)));
                        }

                        if (parameters.get(Constant.ORDER_BY_CUSTOMER_HIERARCHY) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_HIERARCHY)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_HIERARCHY)))) {
                            orderByAppender.append(" ORDER BY CUST_HD.HIERARCHY_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_HIERARCHY)));
                        }

                        if (parameters.get(Constant.ORDER_BY_CUSTOMER_LEVEL) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_LEVEL)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_LEVEL)))) {
                            orderByAppender.append(" ORDER BY PM.CUSTOMER_HIERARCHY_LEVEL ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_LEVEL)));
                        }

                        if (parameters.get(Constant.ORDER_BY_CUSTOMER_GROUP) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_GROUP)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_GROUP)))) {
                            orderByAppender.append(" ORDER BY CMG.COMPANY_GROUP_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_CUSTOMER_GROUP)));
                        }

                        if (parameters.get(Constant.ORDER_BYCOMPANY) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BYCOMPANY)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BYCOMPANY)))) {
                            orderByAppender.append(" ORDER BY CM.COMPANY_NAME  ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BYCOMPANY)));
                        }

                        if (parameters.get(Constant.ORDER_BY_BRAND_TYPE) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_BRAND_TYPE)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_BRAND_TYPE)))) {
                            orderByAppender.append(" ORDER BY PM.BRAND_TYPE  ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_BRAND_TYPE)));
                        }

                        if (parameters.get(Constant.ORDER_BY_PRODUCT_HIERARCHY) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_HIERARCHY)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_HIERARCHY)))) {
                            orderByAppender.append(" ORDER BY PROD_HD.HIERARCHY_NAME  ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_HIERARCHY)));
                        }

                        if (parameters.get(Constant.ORDER_BY_PRODUCT_LEVEL) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_LEVEL)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_LEVEL)))) {
                            orderByAppender.append(" ORDER BY PM.PRODUCT_HIERARCHY_LEVEL  ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_LEVEL)));
                        }

                        if (parameters.get(Constant.ORDER_BY_PRODUCT_GROUP) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_GROUP)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_GROUP)))) {
                            orderByAppender.append(" ORDER BY IG.ITEM_GROUP_NAME  ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_PRODUCT_GROUP)));
                        }
                        if (parameters.get(Constant.ORDER_BY_CREATED_DATE_SEARCH) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_CREATED_DATE_SEARCH)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_CREATED_DATE_SEARCH)))) {
                            orderByAppender.append(Constant.ORDER_BY_PM_CREATED_DATE);
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_CREATED_DATE_SEARCH)));
                        }

                        if (parameters.get(Constant.ORDER_BY_MODIFIED_DATE_SEARCH) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_MODIFIED_DATE_SEARCH)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_MODIFIED_DATE_SEARCH)))) {
                            orderByAppender.append(" ORDER BY PM.MODIFIED_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_MODIFIED_DATE_SEARCH)));
                        }
                        if (parameters.get(Constant.ORDER_BY_CREATED_BY) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BY_CREATED_BY)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BY_CREATED_BY)))) {
                            orderByAppender.append(" ORDER BY PM.CREATED_BY ");
                            orderByAppender.append(String.valueOf(parameters.get(Constant.ORDER_BY_CREATED_BY)));
                        }
                        query = query.replace(Constant.ORDER_BY, orderByAppender.toString());
                    }
                    if (parameters.get(Constant.START) != null && parameters.get(Constant.OFFSET) != null) {
                        int startIndex = Integer.parseInt(String.valueOf(parameters.get(Constant.START)));
                        int offset = Integer.parseInt(String.valueOf(parameters.get(Constant.OFFSET)));
                        query = query + Constant.OFFSET_WITH_CAPS + startIndex + Constant.ROWS_FETCH_NEXT + offset + Constant.SPACE_ROWS_ONLY_SPACE;
                    }
                } else {
                    query = query.replace(Constant.SELECTION, " COUNT (DISTINCT FVM.VIEW_ID) ");
                    query = query.replace(Constant.ORDER_BY, StringUtils.EMPTY);
                    query = query.replace(Constant.QUESTION_FILTER_QUESTION, StringUtils.EMPTY);
                }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        }
        return query;
    }
    
}
