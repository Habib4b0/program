/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.queryutils;

import com.stpl.app.gcm.common.dao.CommonDao;
import com.stpl.app.gcm.common.dao.impl.CommonImpl;
import com.stpl.app.gcm.copycontract.dao.ContractHeaderDAO;
import com.stpl.app.gcm.copycontract.impl.ContractHeaderLogicDAOImpl;
import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.xmlparser.SQlUtil;
import com.stpl.app.model.HelperTable;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author srithar
 */
public class ItemQueries {

    private static final Logger LOGGER = Logger.getLogger(ItemQueries.class);
    final static CommonDao ITEMDAO = CommonImpl.getInstance();
    final static SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);

    public static List getGroupList() {
        return new ArrayList();
    }

    public static List getItemData(List input, String queryName, String quaryName2) {
        LOGGER.debug("Inside item get data");
        List list = new ArrayList();
        StringBuilder sql = new StringBuilder();
        if (queryName != null && !queryName.isEmpty()) {
            try {
                sql = new StringBuilder(CustomSQLUtil.get(queryName));
                if (quaryName2 != null && !quaryName2.equals(StringUtils.EMPTY)) {
                    sql.append(" ");
                    sql.append(CustomSQLUtil.get(quaryName2));
                }
                if (queryName.equals("CFP Component Search For Table") || queryName.contains("IFP Component Search For Table")
                        || queryName.contains("PS Component Search For Table") || queryName.contains("RS Component Search For Table")) {
                    sql.append(" OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;");
                }
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                list = (List<Object[]>) ITEMDAO.executeSelect(sql.toString());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }

        LOGGER.debug("End of item get Data");
        return list;
    }

    public static Boolean itemUpdate(List input, String queryName) {
        LOGGER.debug("Inside Item Update");
        StringBuilder sql = new StringBuilder();
        try {
            sql = new StringBuilder(CustomSQLUtil.get(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }

            Integer count = (Integer) ITEMDAO.executeUpdate(sql.toString());
            if (count > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("End of Item Update");
        return Boolean.FALSE;
    }
    
    public static String getQuery(List input, String queryName) {
        StringBuilder sql = null;
        try {
            sql = new StringBuilder();
            sql = new StringBuilder(CustomSQLUtil.get(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return sql.toString();
    }

    public static List getDataByDTO(String queryName, Object dto) {
        StringBuilder sql = new StringBuilder(CustomSQLUtil.get(queryName));
        List list = new ArrayList();
        try {
            String query = getQuery(sql, dto);
            list = (List<Object[]>) ITEMDAO.executeSelect(query);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return list;
    }

    public static boolean updateDataByDTO(String queryName, Object dto) {
        StringBuilder sql = new StringBuilder(CustomSQLUtil.get(queryName));
        try {
            String query = getQuery(sql, dto);
            Integer count = (Integer) ITEMDAO.executeUpdate(query.toString());
            if (count > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return false;
    }

    private static String getQuery(StringBuilder query, Object myDTO) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        while (true) {
            int index = query.indexOf("${");
            if (index != -1) {
                String variable = query.substring(index + NumericConstants.TWO, query.indexOf("}"));
                Object value = getFieldValue(myDTO, variable);
                query.replace(index, query.indexOf("}") + 1, String.valueOf(value));
                continue;
            }
            break;
        }
        return query.toString();
    }

    private static Object getFieldValue(Object myDTO, String variable) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field field = myDTO.getClass().getDeclaredField(variable);
        field.setAccessible(true);
        Object value = field.get(myDTO);
        return value;
    }

    public static List getItemData(List input, String queryName, String quaryName2, Set<Container.Filter> filters) {
        LOGGER.debug("Inside item get data");
        List list = new ArrayList();
        StringBuilder sql = new StringBuilder();
        if (queryName != null && !queryName.isEmpty()) {

            try {
                sql = new StringBuilder(CustomSQLUtil.get(queryName));
                if (queryName.contains("CFP Component Search")) {
                    sql.append(getCFPFilterQuery(filters));
                }
                if (queryName.contains("IFP Component Search")) {
                    sql.append(getIFPFilterQuery(filters));
                }
                if (queryName.contains("PS Component Search")) {
                    sql.append(getPSFilterQuery(filters));
                }
                if (queryName.contains("RS Component Search")) {
                    sql.append(getRSFilterQuery(filters));
                }
                if (quaryName2 != null && !quaryName2.equals(StringUtils.EMPTY)) {
                    sql.append(" ");
                    sql.append(CustomSQLUtil.get(quaryName2));
                }
                if (queryName.equals("CFP Component Search For Table")) {
                    sql.append(" ORDER BY CM.COMPANY_NO OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;");
                }
                if (queryName.equals("IFP Component Search For Table")) {
                    sql.append(" ORDER BY IFP.IFP_CONTRACT_SID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;");
                }
                if (queryName.equals("PS Component Search For Table")) {
                    sql.append(" ORDER BY PS.PS_CONTRACT_SID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;");
                }
                if (queryName.equals("RS Component Search For Table")) {
                    sql.append(" ORDER BY RS.RS_CONTRACT_SID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;");
                }
                if (queryName.equals("get Submitted Records")) {
                    sql.append(getSubmittedRecordsFilterQuery(filters));
                    sql.append(" ORDER  BY CFP_CON.CFP_CONTRACT_SID, RS_CON.RS_CONTRACT_SID, IFP_CON.IFP_CONTRACT_SID, PS_CON.PS_CONTRACT_SID ;");
                }
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                list = (List<Object[]>) ITEMDAO.executeSelect(sql.toString());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }

        LOGGER.debug("End of item get Data");
        return list;
    }

    private static StringBuilder getCFPFilterQuery(Set<Container.Filter> filters) {
        StringBuilder query = new StringBuilder();
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString();
                    parameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    String filterValue = String.valueOf(stringFilter.getValue());
                    parameters.put(stringFilter.getPropertyId().toString(), filterValue);
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();

                    parameters.put(betweenFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(startValue)));

                    parameters.put(betweenFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(endValue)));

                }
            }
            if (parameters.get("companyNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("companyNo")))) {
                query.append(" AND CM.COMPANY_NO LIKE '%").append(String.valueOf(parameters.get("companyNo"))).append("%'");
            }
            if (parameters.get("companyName") != null && !StringUtils.isBlank(String.valueOf(parameters.get("companyStatus")))) {
                String companyName = parameters.get("companyName").toString();
                query.append(" AND CM.COMPANY_NAME like '%").append(companyName).append("%' ");
            }
            if (parameters.get("companyStatus") != null && !String.valueOf(parameters.get("companyStatus")).equals(Constants.ZEROSTRING)) {
                query.append(" AND HT_CS.HELPER_TABLE_SID = ").append(String.valueOf(parameters.get("companyStatus"))).append(StringUtils.EMPTY);
            }
            if (parameters.get("status") != null && !String.valueOf(parameters.get("status")).equals(Constants.ZEROSTRING)) {
                query.append(" AND HT.HELPER_TABLE_SID = ").append(String.valueOf(parameters.get("status"))).append(StringUtils.EMPTY);
            }
            if (parameters.get("tradeClass") != null && !String.valueOf(parameters.get("tradeClass")).equals(Constants.ZEROSTRING)) {
                query.append(" AND HT_CFP.HELPER_TABLE_SID = ").append(String.valueOf(parameters.get("tradeClass"))).append(StringUtils.EMPTY);
            }
            if (parameters.get("startDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("startDatefrom")))) {
                query.append(" AND CFP.CFP_START_DATE >='").append(String.valueOf(parameters.get("startDatefrom"))).append("'");
            }
            if (parameters.get("startDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("startDateto")))) {
                query.append(" AND CFP.CFP_START_DATE <='").append(String.valueOf(parameters.get("startDateto"))).append("'");
            }
            if (parameters.get("endDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("endDatefrom")))) {
                query.append(" AND CFP.CFP_END_DATE >='").append(String.valueOf(parameters.get("endDatefrom"))).append("'");
            }
            if (parameters.get("endDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("endDateto")))) {
                query.append(" AND CFP.CFP_END_DATE <='").append(String.valueOf(parameters.get("endDateto"))).append("'");
            }
            if (parameters.get("attachedDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("attachedDatefrom")))) {
                query.append(" AND CFP.CFP_CONTRACT_ATTACHED_DATE >='").append(String.valueOf(parameters.get("attachedDatefrom"))).append("'");
            }
            if (parameters.get("attachedDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("attachedDateto")))) {
                query.append(" AND CFP.CFP_CONTRACT_ATTACHED_DATE <='").append(String.valueOf(parameters.get("attachedDateto"))).append("'");
            }
        }
        return query;
    }

    private static StringBuilder getIFPFilterQuery(Set<Container.Filter> filters) {
        StringBuilder query = new StringBuilder();
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString();
                    parameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    String filterValue = String.valueOf(stringFilter.getValue());
                    parameters.put(stringFilter.getPropertyId().toString(), filterValue);
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;

                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();

                    parameters.put(betweenFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(startValue)));

                    parameters.put(betweenFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(endValue)));

                }
            }
            if (parameters.get("itemNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemNo")))) {
                query.append(" AND IM.ITEM_NO LIKE '%").append(String.valueOf(parameters.get("itemNo"))).append("%'");
            }
            if (parameters.get("itemName") != null && !StringUtils.isBlank(String.valueOf(parameters.get("itemName")))) {
                String companyName = parameters.get("itemName").toString();
                query.append(" AND IM.ITEM_NAME like '%").append(companyName).append("%' ");
            }
            if (parameters.get("brand") != null && !StringUtils.isBlank(String.valueOf(parameters.get("brand")))) {
                query.append(" AND B.BRAND_NAME like '%").append(String.valueOf(parameters.get("brand"))).append("%'");
            }
            if (parameters.get("status") != null && !String.valueOf(parameters.get("status")).equals(Constants.ZEROSTRING)) {
                query.append(" AND HT.HELPER_TABLE_SID = ").append(String.valueOf(parameters.get("status"))).append(StringUtils.EMPTY);
            }
            if (parameters.get("startDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("startDatefrom")))) {
                query.append(" AND IFP.IFP_START_DATE >='").append(String.valueOf(parameters.get("startDatefrom"))).append("'");
            }
            if (parameters.get("startDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("startDateto")))) {
                query.append(" AND IFP.IFP_START_DATE <='").append(String.valueOf(parameters.get("startDateto"))).append("'");
            }
            if (parameters.get("endDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("endDatefrom")))) {
                query.append(" AND IFP.IFP_END_DATE >='").append(String.valueOf(parameters.get("endDatefrom"))).append("'");
            }
            if (parameters.get("endDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("endDateto")))) {
                query.append(" AND IFP.IFP_END_DATE <='").append(String.valueOf(parameters.get("endDateto"))).append("'");
            }
            if (parameters.get("attachedDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("attachedDatefrom")))) {
                query.append(" AND IFP.IFP_CONTRACT_ATTACHED_DATE >='").append(String.valueOf(parameters.get("attachedDatefrom"))).append("'");
            }
            if (parameters.get("attachedDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("attachedDateto")))) {
                query.append(" AND IFP.IFP_CONTRACT_ATTACHED_DATE <='").append(String.valueOf(parameters.get("attachedDateto"))).append("'");
            }
        }
        return query;
    }

    private static StringBuilder getPSFilterQuery(Set<Container.Filter> filters) {
        StringBuilder query = new StringBuilder();
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString();
                    parameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    String filterValue = String.valueOf(stringFilter.getValue());
                    parameters.put(stringFilter.getPropertyId().toString(), filterValue);
                    if ("priceType".equals(stringFilter.getPropertyId())) {
                        parameters.put("priceType", filterValue);
                    }
                    if ("priceProtectionPriceType".equals(stringFilter.getPropertyId())) {
                        parameters.put("priceProtectionPriceType", filterValue);
                    }
                    if ("priceToleranceInterval".equals(stringFilter.getPropertyId())) {
                        parameters.put("priceToleranceInterval", filterValue);
                    }
                    if ("priceToleranceFrequency".equals(stringFilter.getPropertyId())) {
                        parameters.put("priceToleranceFrequency", filterValue);
                    }
                    if ("priceToleranceType".equals(stringFilter.getPropertyId())) {
                        parameters.put("priceToleranceType", filterValue);
                    }
                    if ("resetType".equals(stringFilter.getPropertyId())) {
                        parameters.put("resetType", filterValue);
                    }
                    if ("resetInterval".equals(stringFilter.getPropertyId())) {
                        parameters.put("resetInterval", filterValue);
                    }
                    if ("resetFrequency".equals(stringFilter.getPropertyId())) {
                        parameters.put("resetFrequency", filterValue);
                    }

                } 
            }

            if (parameters.get("itemNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemNo")))) {
                query.append(" AND IM.ITEM_NO LIKE '%").append(String.valueOf(parameters.get("itemNo"))).append("%'");
            }
            if (parameters.get("itemName") != null && !StringUtils.isBlank(String.valueOf(parameters.get("itemName")))) {
                query.append(" AND IM.ITEM_NAME like '%").append(String.valueOf(parameters.get("itemName"))).append("%'");
            }
            if (parameters.get("brand") != null && !String.valueOf(parameters.get("brand")).equals(Constants.ZEROSTRING)) {
                query.append(" AND B.BRAND_NAME like '%").append(String.valueOf(parameters.get("brand"))).append("%'");
            }
            if (parameters.get("status") != null && !String.valueOf(parameters.get("status")).equals(Constants.ZEROSTRING)) {
                query.append(" AND HT.HELPER_TABLE_SID = ").append(String.valueOf(parameters.get("status"))).append(StringUtils.EMPTY);
            }
            if (parameters.get("startDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("startDatefrom")))) {
                query.append(" AND PS.PS_START_DATE >='").append(String.valueOf(parameters.get("startDatefrom"))).append("'");
            }
            if (parameters.get("startDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("startDateto")))) {
                query.append(" AND PS.PS_START_DATE <='").append(String.valueOf(parameters.get("startDateto"))).append("'");
            }
            if (parameters.get("endDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("endDatefrom")))) {
                query.append(" AND PS.PS_END_DATE >='").append(String.valueOf(parameters.get("endDatefrom"))).append("'");
            }
            if (parameters.get("endDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("endDateto")))) {
                query.append(" AND PS.PS_END_DATE <='").append(String.valueOf(parameters.get("endDateto"))).append("'");
            }
            if (parameters.get("priceType") != null && !String.valueOf(parameters.get("priceType")).equals(Constants.ZEROSTRING)) {
                query.append(" AND HT_PTP.HELPER_TABLE_SID = ").append(String.valueOf(parameters.get("priceType"))).append(StringUtils.EMPTY);
            }
            if (parameters.get("priceProtectionStatus") != null && !String.valueOf(parameters.get("priceProtectionStatus")).equals(Constants.ZEROSTRING)) {
                query.append(" AND PSD.PRICE_PROTECTION_STATUS = ").append(String.valueOf(parameters.get("priceProtectionStatus"))).append(StringUtils.EMPTY);
            }
            if (parameters.get("priceProtectionStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceProtectionStartDatefrom")))) {
                query.append(" AND PSD.PRICE_PROTECTION_START_DATE >='").append(String.valueOf(parameters.get("priceProtectionStartDatefrom"))).append("'");
            }
            if (parameters.get("priceProtectionStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceProtectionStartDateto")))) {
                query.append(" AND PSD.PRICE_PROTECTION_START_DATE <='").append(String.valueOf(parameters.get("priceProtectionStartDateto"))).append("'");
            }
            if (parameters.get("priceProtectionEndDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceProtectionEndDatefrom")))) {
                query.append(" AND PSD.PRICE_PROTECTION_END_DATE >='").append(String.valueOf(parameters.get("priceProtectionEndDatefrom"))).append("'");
            }
            if (parameters.get("priceProtectionEndDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceProtectionEndDateto")))) {
                query.append(" AND PSD.PRICE_PROTECTION_END_DATE <='").append(String.valueOf(parameters.get("priceProtectionEndDateto"))).append("'");
            }
            if (parameters.get("priceProtectionPriceType") != null && !String.valueOf(parameters.get("priceProtectionPriceType")).equals(Constants.ZEROSTRING)) {
                query.append(" AND HT_PTY.HELPER_TABLE_SID = ").append(String.valueOf(parameters.get("priceProtectionPriceType"))).append(StringUtils.EMPTY);
            }
            if (parameters.get("priceToleranceInterval") != null && !String.valueOf(parameters.get("priceToleranceInterval")).equals(Constants.ZEROSTRING)) {
                query.append(" AND HT_PTI.HELPER_TABLE_SID = ").append(String.valueOf(parameters.get("priceToleranceInterval"))).append(StringUtils.EMPTY);
            }
            if (parameters.get("priceToleranceFrequency") != null && !String.valueOf(parameters.get("priceToleranceFrequency")).equals(Constants.ZEROSTRING)) {
                query.append(" AND HT_PTF.HELPER_TABLE_SID = ").append(String.valueOf(parameters.get("priceToleranceFrequency"))).append(StringUtils.EMPTY);
            }
            if (parameters.get("priceToleranceType") != null && !String.valueOf(parameters.get("priceToleranceType")).equals(Constants.ZEROSTRING)) {
                query.append(" AND HT_PTT.HELPER_TABLE_SID = ").append(String.valueOf(parameters.get("priceToleranceType"))).append(StringUtils.EMPTY);
            }
            if (parameters.get("maxIncrementalChange") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("maxIncrementalChange")))) {
                query.append(" AND PSD.MAX_INCREMENTAL_CHANGE LIKE '%").append(String.valueOf(parameters.get("maxIncrementalChange"))).append("%'");
            }
            if (parameters.get("priceTolerance") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceTolerance")))) {
                query.append(" AND PSD.PRICE_TOLERANCE LIKE '%").append(String.valueOf(parameters.get("priceTolerance"))).append("%'");
            }
            if (parameters.get("resetEligible") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("resetEligible")))) {
                query.append(" AND PSD.RESET_ELIGIBLE LIKE '%").append(String.valueOf(parameters.get("resetEligible"))).append("%'");
            }
            if (parameters.get("resetType") != null && !String.valueOf(parameters.get("resetType")).equals(Constants.ZEROSTRING)) {
                query.append(" AND HT_RT.HELPER_TABLE_SID = ").append(String.valueOf(parameters.get("resetType"))).append(StringUtils.EMPTY);
            }
            if (parameters.get("resetDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("resetDatefrom")))) {
                query.append(" AND PSD.RESET_DATE >='").append(String.valueOf(parameters.get("resetDatefrom"))).append("'");
            }
            if (parameters.get("resetDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("resetDateto")))) {
                query.append(" AND PSD.RESET_DATE <='").append(String.valueOf(parameters.get("resetDateto"))).append("'");
            }
            if (parameters.get("resetInterval") != null && !String.valueOf(parameters.get("resetInterval")).equals(Constants.ZEROSTRING)) {
                query.append(" AND HT_RI.HELPER_TABLE_SID = ").append(String.valueOf(parameters.get("resetInterval"))).append(StringUtils.EMPTY);
            }
            if (parameters.get("resetFrequency") != null && !String.valueOf(parameters.get("resetFrequency")).equals(Constants.ZEROSTRING)) {
                query.append(" AND HT_RF.HELPER_TABLE_SID = ").append(String.valueOf(parameters.get("resetFrequency"))).append(StringUtils.EMPTY);
            }
            if (parameters.get("attachedDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("attachedDatefrom")))) {
                query.append(" AND PS.PS_CONTRACT_ATTACHED_DATE >='").append(String.valueOf(parameters.get("attachedDatefrom"))).append("'");
            }
            if (parameters.get("attachedDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("attachedDateto")))) {
                query.append(" AND PS.PS_CONTRACT_ATTACHED_DATE <='").append(String.valueOf(parameters.get("attachedDateto"))).append("'");
            }
        }
        return query;
    }

    private static StringBuilder getRSFilterQuery(Set<Container.Filter> filters) {
        StringBuilder query = new StringBuilder();
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString();
                    parameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    String filterValue = String.valueOf(stringFilter.getValue());
                    parameters.put(stringFilter.getPropertyId().toString(), filterValue);
                    if ("formulaType".equals(stringFilter.getPropertyId())) {
                        parameters.put("formulaType", filterValue);
                    }
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;

                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();

                    parameters.put(betweenFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(startValue)));

                    parameters.put(betweenFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(endValue)));

                }
            }

            if (parameters.get("itemNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemNo")))) {
                query.append(" AND IM.ITEM_NO LIKE '%").append(String.valueOf(parameters.get("itemNo"))).append("%'");
            }
            if (parameters.get("itemName") != null && !StringUtils.isBlank(String.valueOf(parameters.get("itemName")))) {
                query.append(" AND IM.ITEM_NAME like '%").append(String.valueOf(parameters.get("itemName"))).append("%'");
            }
            if (parameters.get("brand") != null && !String.valueOf(parameters.get("brand")).equals(Constants.ZEROSTRING)) {
                query.append(" AND B.BRAND_NAME like '%").append(String.valueOf(parameters.get("brand"))).append("%'");
            }
            if (parameters.get("status") != null && !String.valueOf(parameters.get("status")).equals(Constants.ZEROSTRING)) {
                query.append(" AND HT.HELPER_TABLE_SID = ").append(String.valueOf(parameters.get("status"))).append(StringUtils.EMPTY);
            }
            if (parameters.get("startDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("startDatefrom")))) {
                query.append(" AND RSD.ITEM_REBATE_START_DATE >='").append(String.valueOf(parameters.get("startDatefrom"))).append("'");
            }
            if (parameters.get("startDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("startDateto")))) {
                query.append(" AND RSD.ITEM_REBATE_START_DATE <='").append(String.valueOf(parameters.get("startDateto"))).append("'");
            }
            if (parameters.get("endDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("endDatefrom")))) {
                query.append(" AND RSD.ITEM_REBATE_END_DATE >='").append(String.valueOf(parameters.get("endDatefrom"))).append("'");
            }
            if (parameters.get("endDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("endDateto")))) {
                query.append(" AND RSD.ITEM_REBATE_END_DATE <='").append(String.valueOf(parameters.get("endDateto"))).append("'");
            }
            if (parameters.get("formulaType") != null && !String.valueOf(parameters.get("formulaType")).equals(Constants.ZEROSTRING)) {
                query.append(" AND RSD.FORMULA_TYPE = ").append(String.valueOf(parameters.get("formulaType"))).append(StringUtils.EMPTY);
            }
            if (parameters.get("formulaId") != null && !StringUtils.isBlank(String.valueOf(parameters.get("formulaId")))) {
                query.append(" AND FDM.FORMULA_ID like '%").append(String.valueOf(parameters.get("formulaId"))).append("%'");
            }
            if (parameters.get("formulaName") != null && !StringUtils.isBlank(String.valueOf(parameters.get("formulaName")))) {
                query.append(" AND FM.FORMULA_NAME like '%").append(String.valueOf(parameters.get("formulaName"))).append("%'");
            }
            if (parameters.get("rebatePlanId") != null && !StringUtils.isBlank(String.valueOf(parameters.get("rebatePlanId")))) {
                query.append(" AND RP.REBATE_PLAN_ID like '%").append(String.valueOf(parameters.get("rebatePlanId"))).append("%'");
            }
            if (parameters.get("rebatePlanName") != null && !StringUtils.isBlank(String.valueOf(parameters.get("rebatePlanName")))) {
                query.append(" AND RP.REBATE_PLAN_NAME like '%").append(String.valueOf(parameters.get("rebatePlanName"))).append("%'");
            }
            if (parameters.get("rebateAmount") != null && !StringUtils.isBlank(String.valueOf(parameters.get("rebateAmount")))) {
                query.append(" AND RSD.REBATE_AMOUNT like '%").append(String.valueOf(parameters.get("rebateAmount"))).append("%'");
            }
            if (parameters.get("bundleNo") != null && !StringUtils.isBlank(String.valueOf(parameters.get("bundleNo")))) {
                query.append(" AND RSD.BUNDLE_NO like '%").append(String.valueOf(parameters.get("bundleNo"))).append("%'");
            }
            if (parameters.get("attachedDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("attachedDatefrom")))) {
                query.append(" AND RSD.RS_CONTRACT_ATTACHED_DATE >='").append(String.valueOf(parameters.get("attachedDatefrom"))).append("'");
            }
            if (parameters.get("attachedDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("attachedDateto")))) {
                query.append(" AND RSD.RS_CONTRACT_ATTACHED_DATE <='").append(String.valueOf(parameters.get("attachedDateto"))).append("'");
            }
        }
        return query;
    }

    private static StringBuilder getSubmittedRecordsFilterQuery(Set<Container.Filter> filters) {
        StringBuilder query = new StringBuilder();
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString();
                    parameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    String filterValue = String.valueOf(stringFilter.getValue());
                    if ("statusDescription".equals(stringFilter.getPropertyId()) && filterValue != null && !filterValue.equals(Constants.NULL)) {
                        try {
                            int helperId = getHelperCode("STATUS", filterValue);
                            parameters.put("status", helperId);
                        } catch (PortalException ex) {
                            LOGGER.error(ex);
                        } catch (SystemException ex) {
                            LOGGER.error(ex);
                        }
                    }
                    if ("contractType".equals(stringFilter.getPropertyId()) && filterValue != null && !filterValue.equals(Constants.NULL)) {
                        try {
                            int helperId = getHelperCode("CONTRACT_TYPE", filterValue);
                            parameters.put("contractType", helperId);
                        } catch (PortalException ex) {
                            LOGGER.error(ex);
                        } catch (SystemException ex) {
                            LOGGER.error(ex);
                        }
                    }
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;

                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();

                    parameters.put(betweenFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(startValue)));

                    parameters.put(betweenFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(endValue)));

                }
            }
            String andOperator = StringUtils.EMPTY;
            query.append(" where ");
            if (parameters.get(Constants.CONTRACT_HOLDER) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(Constants.CONTRACT_HOLDER)))) {
                query.append(" CM.COMPANY_NAME LIKE '%").append(String.valueOf(parameters.get(Constants.CONTRACT_HOLDER))).append("%'");
                andOperator = " AND ";
            }
            if (parameters.get(Constants.CONTRACT_NO) != null && !StringUtils.isBlank(String.valueOf(parameters.get(Constants.CONTRACT_NO)))) {
                query.append(andOperator + " CON.CONTRACT_NO LIKE '%").append(String.valueOf(parameters.get(Constants.CONTRACT_NO))).append("%'");
                andOperator = " AND ";
            }
            if (parameters.get(Constants.CONTRACT_NAME) != null && !StringUtils.isBlank(String.valueOf(parameters.get(Constants.CONTRACT_NAME)))) {
                query.append(andOperator + " CON.CONTRACT_NAME LIKE '%").append(String.valueOf(parameters.get(Constants.CONTRACT_NAME))).append("%'");
                andOperator = " AND ";
            }
            if (parameters.get("contractType") != null && !String.valueOf(parameters.get("contractType")).equals(Constants.ZEROSTRING)) {
                query.append(andOperator + " HEL.HELPER_TABLE_SID = ").append(String.valueOf(parameters.get("contractType"))).append(StringUtils.EMPTY);
                andOperator = " AND ";
            }
            if (parameters.get("statusDescription") != null && !String.valueOf(parameters.get("statusDescription")).equals(Constants.ZEROSTRING)) {
                query.append(andOperator + " HEL_TAB1.HELPER_TABLE_SID = ").append(String.valueOf(parameters.get("statusDescription"))).append(StringUtils.EMPTY);
                andOperator = " AND ";
            }
            if (parameters.get("contStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("contStartDatefrom")))) {
                query.append(andOperator + " CON.START_DATE >='").append(String.valueOf(parameters.get("contStartDatefrom"))).append("'");
                andOperator = " AND ";
            }
            if (parameters.get("contStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("contStartDateto")))) {
                query.append(andOperator + " CON.START_DATE <='").append(String.valueOf(parameters.get("contStartDateto"))).append("'");
                andOperator = " AND ";
            }
            if (parameters.get("contEndDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("contEndDatefrom")))) {
                query.append(andOperator + " CON.END_DATE >='").append(String.valueOf(parameters.get("contEndDatefrom"))).append("'");
                andOperator = " AND ";
            }
            if (parameters.get("contEndDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("contEndDateto")))) {
                query.append(andOperator + " CON.END_DATE <='").append(String.valueOf(parameters.get("contEndDateto"))).append("'");
                andOperator = " AND ";
            }
            if (parameters.get(Constants.CFP_NAME) != null && !StringUtils.isBlank(String.valueOf(parameters.get(Constants.CFP_NAME)))) {
                query.append(andOperator + " CON.CONTRACT_NAME LIKE '%").append(String.valueOf(parameters.get(Constants.CFP_NAME))).append("%'");
                andOperator = " AND ";
            }
            if (parameters.get(Constants.IFPNAME) != null && !StringUtils.isBlank(String.valueOf(parameters.get(Constants.IFPNAME)))) {
                query.append(andOperator + " CON.CONTRACT_NAME LIKE '%").append(String.valueOf(parameters.get(Constants.IFPNAME))).append("%'");
                andOperator = " AND ";
            }
            if (parameters.get("pSName") != null && !StringUtils.isBlank(String.valueOf(parameters.get("pSName")))) {
                query.append(andOperator + " CON.CONTRACT_NAME LIKE '%").append(String.valueOf(parameters.get("pSName"))).append("%'");
                andOperator = " AND ";
            }
            if (parameters.get("rSName") != null && !StringUtils.isBlank(String.valueOf(parameters.get("rSName")))) {
                query.append(andOperator + " CON.CONTRACT_NAME LIKE '%").append(String.valueOf(parameters.get("rSName"))).append("%'");
                andOperator = " AND ";
            }
            if (parameters.get("compStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("compStartDatefrom")))) {
                query.append(andOperator + " IFP.IFP_CONTRACT_ATTACHED_DATE >='").append(String.valueOf(parameters.get("compStartDatefrom"))).append("'");
                andOperator = " AND ";
            }
            if (parameters.get("compStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("compStartDateto")))) {
                query.append(andOperator + " IFP.IFP_CONTRACT_ATTACHED_DATE <='").append(String.valueOf(parameters.get("compStartDateto"))).append("'");
                andOperator = " AND ";
            }
            if (parameters.get("compEndDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("compEndDatefrom")))) {
                query.append(andOperator + " IFP.IFP_CONTRACT_ATTACHED_DATE >='").append(String.valueOf(parameters.get("compEndDatefrom"))).append("'");
                andOperator = " AND ";
            }
            if (parameters.get("compEndDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("compEndDateto")))) {
                query.append(andOperator + " IFP.IFP_CONTRACT_ATTACHED_DATE <='").append(String.valueOf(parameters.get("compEndDateto"))).append("'");
                andOperator = " AND ";
            }
        }
        return query;
    }

    public static int getHelperCode(String listName, String description) throws PortalException, SystemException {
        ContractHeaderDAO DAO = new ContractHeaderLogicDAOImpl();
        int code = 0;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.LIST_NAME, listName));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.DESCRIPTION, description));
        dynamicQuery.setProjection(ProjectionFactoryUtil.property(ConstantsUtils.HELPER_TABLE_SID));
        List result = DAO.getHelperTableList(dynamicQuery);
        if (result != null && !result.isEmpty()) {
            code = Integer.valueOf(result.get(0).toString());
        }
        return code;
    }
    public static boolean updateDataByDTOforRemoveDiscount(String queryName, RemoveDiscountDto dto) {
        StringBuilder sql = new StringBuilder(SQlUtil.getQuery(queryName));
        try {
            String query = getQuery(sql, dto);
            query = query.replace("@CHECK_RECORD", (dto.getCheckRecord() ? 1 : 0) + StringUtils.EMPTY);
            Integer count = (Integer) ITEMDAO.executeUpdate(query);
            if (count > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
            LOGGER.error(ex);
        }
        return false;
    }
}
