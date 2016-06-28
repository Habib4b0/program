/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.projectionvariance.logic;

import com.stpl.app.galforecasting.dao.SalesProjectionDAO;
import com.stpl.app.galforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.galforecasting.dto.PVSelectionDTO;
import com.stpl.app.galforecasting.dto.ProjectionVarianceDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.projectionvariance.dto.ComparisonLookupDTO;
import com.stpl.app.galforecasting.projectionvariance.utils.HeaderUtils;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.form.DataSelectionForm;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.SELECT_ONE;
import com.stpl.app.galforecasting.utils.Converters;
import com.stpl.app.model.MProjectionSelection;
import com.stpl.app.service.MProjectionSelectionLocalServiceUtil;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.CommonConstants.DATE_FORMAT;
import static com.stpl.app.utils.Constants.LabelConstants.TOTAL;
import static com.stpl.app.utils.Constants.LabelConstants.TOTAL_DISCOUNT;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.portal.kernel.dao.orm.Criterion;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Compare.Operation;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.ComboBox;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author
 */
public class MProjectionVarianceLogic {

    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(MProjectionVarianceLogic.class);
    private static final String C = Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY;
    private static final String P = "P";
    private static String DASH = "-";
    private static String CURRENT = "Current";
    private static final DecimalFormat RATE = new DecimalFormat("#,##0.0");
    private static final DecimalFormat RATE_PER = new DecimalFormat("#,##0.000");
    private static final DecimalFormat AMOUNT_UNITS = new DecimalFormat("#,##0");
    private static final String ZERO = "0";
    private static final String NULL = "null";
    private static final String VALUE = "Value";
    private static final String VARIANCE = "Variance";
    private static final String DETAIL = "Detail";
    private static final String PERCENT = Constant.PERCENT;
    private static final String CHANGE = "Change";
    List<Object> pivotTotalList = new ArrayList<Object>();
    private CommonLogic commonLogic = new CommonLogic();
    /**
     * The Constant AMOUNT.
     */
    private static final DecimalFormat AMOUNT = new DecimalFormat("$#,##0");
    CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    PVSelectionDTO selectionDTO = new PVSelectionDTO();
    List<List> currentSales = new ArrayList<List>();
    List<List> currentTotal = new ArrayList<List>();
    List<Integer> priorProjIdSalesList = new ArrayList<Integer>();
    List<Integer> priorProjIdTotalList = new ArrayList<Integer>();
    List<List> currentTotalDiscount = new ArrayList<List>();
    List<Integer> priorProjIdDiscountList = new ArrayList<Integer>();
    List<List> currentPivotGTSTotal = new ArrayList<List>();
    List<Integer> pivotPriorProjIdList = new ArrayList<Integer>();
    List<List> currentDiscountPer = new ArrayList<List>();
    List<List> currentDiscount = new ArrayList<List>();
    List<Integer> priorIdTotal = new ArrayList<Integer>();
    Map<String, ProjectionVarianceDTO> programCodeMap = new HashMap<String, ProjectionVarianceDTO>();
    static HashMap<String, String> columnName = new HashMap<String, String>();
    Map<String, List<Object>> discountLevelMap = new HashMap<String, List<Object>>();
    Map<String, List<Object>> discountPcMap = new HashMap<String, List<Object>>();
    Map<String, List<Object>> discountProgramMap = new HashMap<String, List<Object>>();
    List<Object> periodPcNames = new ArrayList<Object>();

    public CustomTableHeaderDTO getRightHeader() {
        return rightHeader;
    }

    public void setRightHeader(CustomTableHeaderDTO rightHeader) {
        this.rightHeader = rightHeader;
    }

    public PVSelectionDTO getSelectionDTO() {
        return selectionDTO;
    }

    public void setSelectionDTO(PVSelectionDTO selectionDTO) {
        this.selectionDTO = selectionDTO;
    }

    public int getProjectionCount(final ComparisonLookupDTO lookUpDTO, final SessionDTO sessionDTO, final String selectedProjectionIds, final Set<Container.Filter> filterValue) throws PortalException, Exception {
        String QUOTES = "'";
        String ASTERIK = "*";
        String PERCENT = Constant.PERCENT;

        String marketTypeVal;
        String brandVal;
        String projNameVal;
        String desc = StringUtils.EMPTY;
        String customer;
        String ndcNoVal;
        String ndcNameVal;
        String contractVal;
        Session session = null;
        boolean isProjectionStatus = false;
        try {
            StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);

            if (Constant.SAVED.equals(lookUpDTO.getWorkflowStatus())) {
                isProjectionStatus = true;
            }
            customSql.append("SELECT distinct Count(CO.CONTRACT_MASTER_SID) FROM   CONTRACT_MASTER CO \n"
                    + "JOIN HELPER_TABLE HT ON CO.CONTRACT_TYPE = HT.HELPER_TABLE_SID \n"
                    + "JOIN CCP_DETAILS CCP ON CCP.CONTRACT_MASTER_SID = CO.CONTRACT_MASTER_SID \n"
                    + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID \n"
                    + " JOIN PROJECTION_DETAILS CUR_PD ON CUR_PD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID \n"
                    + "JOIN PROJECTION_MASTER PM ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID \n");
            if (!isProjectionStatus) {
                customSql.append("       JOIN workflow_master WM \n"
                        + "         ON PM.projection_master_sid = WM.projection_master_sid \n"
                        + "       JOIN helper_table HT1 \n"
                        + "         ON HT1.helper_table_sid = WM.workflow_status_id ");
            }
            customSql.append("JOIN COMPANY_MASTER CM ON CCP.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID \n"
                    + "JOIN ITEM_MASTER IM ON CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID \n"
                    + "LEFT JOIN BRAND_MASTER BM ON BM.BRAND_MASTER_SID = im.BRAND_MASTER_SID WHERE ");

            if (lookUpDTO.getMarketType() == null || lookUpDTO.getMarketType().equals(StringUtils.EMPTY)) {
                marketTypeVal = "'%'";
            } else {
                marketTypeVal = lookUpDTO.getMarketType().replace(ASTERIK, PERCENT);
                marketTypeVal = QUOTES + marketTypeVal + QUOTES;
            }
            customSql.append("( HT.list_name = 'CONTRACT_TYPE' AND HT.DESCRIPTION LIKE ").append(marketTypeVal).append(")");

            if (lookUpDTO.getBrand() == null || lookUpDTO.getBrand().equals(StringUtils.EMPTY)) {
                brandVal = "'%'";
            } else {
                brandVal = lookUpDTO.getBrand().replace(ASTERIK, PERCENT);
                brandVal = QUOTES + brandVal + QUOTES;
            }
            customSql.append("  AND (BM.BRAND_NAME LIKE ").append(brandVal).append(" or BM.BRAND_NAME is null)");

            if (lookUpDTO.getProjectionDescription() == null || lookUpDTO.getProjectionDescription().equals(StringUtils.EMPTY)) {
                desc = "'%'";
            } else {
                desc = lookUpDTO.getProjectionDescription().replace(ASTERIK, PERCENT);
                desc = QUOTES + desc + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_DESCRIPTION LIKE ").append(desc).append(" or PM.PROJECTION_DESCRIPTION is null)");

            if (lookUpDTO.getProjectionName() == null || lookUpDTO.getProjectionName().equals(StringUtils.EMPTY)) {
                projNameVal = "'%'";
            } else {
                projNameVal = lookUpDTO.getProjectionName().replace(ASTERIK, PERCENT);
                projNameVal = QUOTES + projNameVal + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_NAME LIKE ").append(projNameVal).append(" or PM.PROJECTION_NAME is null)");

            if (lookUpDTO.getContract() == null || lookUpDTO.getContract().equals(StringUtils.EMPTY)) {
                contractVal = "'%'";
            } else {
                contractVal = lookUpDTO.getContract().replace(ASTERIK, PERCENT);
                contractVal = QUOTES + contractVal + QUOTES;
            }
            customSql.append("AND (CO.CONTRACT_NO LIKE ").append(contractVal).append(" or CO.CONTRACT_NO is null)");

            if (lookUpDTO.getNdcName() == null || lookUpDTO.getNdcName().equals(StringUtils.EMPTY)) {
                ndcNameVal = "'%'";
            } else {
                ndcNameVal = lookUpDTO.getNdcName().replace(ASTERIK, PERCENT);
                ndcNameVal = QUOTES + ndcNameVal + QUOTES;
            }
            customSql.append("AND (IM.ITEM_NAME LIKE ").append(ndcNameVal).append(" or IM.ITEM_NAME is null)");
            if (lookUpDTO.getNdcNo() == null || lookUpDTO.getNdcNo().equals(StringUtils.EMPTY)) {
                ndcNoVal = "'%'";
            } else {
                ndcNoVal = lookUpDTO.getNdcNo().replace(ASTERIK, PERCENT);
                ndcNoVal = QUOTES + ndcNoVal + QUOTES;
            }
            customSql.append("AND (IM.ITEM_NO LIKE ").append(ndcNoVal).append("or IM.ITEM_NO is null)");
            if (lookUpDTO.getCustomer() == null || lookUpDTO.getCustomer().equals(StringUtils.EMPTY)) {
                customer = "'%'";
            } else {
                customer = lookUpDTO.getCustomer().replace(ASTERIK, PERCENT);
                customer = QUOTES + customer + QUOTES;
            }
            customSql.append("AND (CM.COMPANY_NO LIKE ").append(customer).append("or CM.COMPANY_NO is null)");

            if (!Constant.NULL.equals(lookUpDTO.getCreatedDateFrom()) && lookUpDTO.getCreatedDateFrom() != null
                    && !Constant.NULL.equals(lookUpDTO.getCreatedDateTo()) && !StringUtils.isEmpty(lookUpDTO.getCreatedDateTo())) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE BETWEEN '");
                customSql.append(format2.format(lookUpDTO.getCreatedDateFrom()));
                customSql.append("' AND '");
                customSql.append(format2.format(format2.parse(lookUpDTO.getCreatedDateTo())));
                customSql.append("' ");
            }
            if (isProjectionStatus) {
                customSql.append("and pm.is_approved not in ('Y','C','A','R')");
            } else {
                customSql.append("AND HT1.list_name = 'WorkFlowStatus' and ht1.description = " + QUOTES + lookUpDTO.getWorkflowStatus() + QUOTES);
            }
            customSql.append("AND PM.PROJECTION_MASTER_SID NOT IN (").append(selectedProjectionIds).append(")");
            customSql.append("AND CUR_PD.PROJECTION_MASTER_SID =").append(selectedProjectionIds);
            customSql.append(" AND PM.FORECASTING_TYPE='Mandated'");
            if (filterValue != null) {
                for (Container.Filter filter : filterValue) {

                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        if (StringUtils.isNotBlank(stringFilter.getFilterString()) && !Constant.NULL.equals(stringFilter.getFilterString())) {
                            String filterString = "'%" + stringFilter.getFilterString() + "%'";
                            if (Constant.PROJECTION_NAME.equals(stringFilter.getPropertyId())) {
                                customSql.append("AND PM.PROJECTION_NAME LIKE ").append(filterString);
                            } else if (Constant.PROJECTIONDESCRIPTION.equals(stringFilter.getPropertyId())) {
                                customSql.append("AND PM.PROJECTION_DESCRIPTION LIKE ").append(filterString);
                            } else if ("marketType".equals(stringFilter.getPropertyId())) {
                                customSql.append("AND CO.CONTRACT_TYPE LIKE ").append(filterString);
                            } else if ("customer".equals(stringFilter.getPropertyId())) {
                                customSql.append("AND CM.COMPANY_NO LIKE ").append(filterString);
                            } else if (Constant.CONTRACT.equals(stringFilter.getPropertyId())) {
                                customSql.append("AND CO.CONTRACT_NO LIKE ").append(filterString);
                            } else if (Constant.BRAND.equals(stringFilter.getPropertyId())) {
                                customSql.append("AND BM.BRAND_NAME LIKE ").append(filterString);
                            } else if ("ndcNo".equals(stringFilter.getPropertyId())) {
                                customSql.append("AND IM.ITEM_NO LIKE ").append(filterString);
                            } else if ("ndcName".equals(stringFilter.getPropertyId())) {
                                customSql.append("AND IM.ITEM_NAME LIKE ").append(filterString);
                            } else if ("createdDate".equals(stringFilter.getPropertyId())) {
                                customSql.append("AND PM.CREATED_DATE LIKE ").append(filterString);
                            } else if ("createdBy".equals(stringFilter.getPropertyId())) {
                                List<String> strList = new ArrayList<String>();
                                final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
                                Criterion criterion = RestrictionsFactoryUtil.ilike(Constant.FIRSTNAME, Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT);
                                Criterion criterion1 = RestrictionsFactoryUtil.ilike(Constant.LASTNAME, Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT);
                                dynamicQuery.add(RestrictionsFactoryUtil.or(criterion, criterion1));
                                final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
                                productProjectionList.add(ProjectionFactoryUtil.property(Constant.USER_ID));
                                dynamicQuery.setProjection(productProjectionList);
                                strList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                                String userID = CommonUtils.CollectionToString(strList, false);
                                customSql.append("AND PM.CREATED_BY IN (").append(userID).append(")");
                            }
                        }
                    } else if (filter instanceof Between) {
                        Between betweenFilter = (Between) filter;
                        Date startValue = (Date) betweenFilter.getStartValue();
                        Date endValue = (Date) betweenFilter.getEndValue();
                        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00.0");
                        if ((startValue != null && !StringUtils.EMPTY.equals(String.valueOf(startValue)))) {
                            customSql.append(" and PM.CREATED_DATE >= '").append(outputDateFormat.format(startValue)).append("' ");
                        }
                        if (endValue != null && !StringUtils.EMPTY.equals(String.valueOf(endValue))) {
                            outputDateFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59.999");
                            customSql.append(" and PM.CREATED_DATE <= '").append(outputDateFormat.format(endValue)).append("' ");
                        }
                    } else if (filter instanceof Compare) {
                        Compare compare = (Compare) filter;
                        Date date = (Date) compare.getValue();
                        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00.000");
                        if (Operation.GREATER.equals(compare.getOperation()) || Operation.GREATER_OR_EQUAL.equals(compare.getOperation())) {
                            customSql.append(" and PM.CREATED_DATE >= '").append(outputDateFormat.format(date)).append("' ");
                        }
                        if (Operation.LESS.equals(compare.getOperation()) || Operation.LESS_OR_EQUAL.equals(compare.getOperation())) {
                            outputDateFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59.999");
                            customSql.append(" and PM.CREATED_DATE <= '").append(outputDateFormat.format(date)).append("' ");
                        }
                    }
                }
            }
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            List resultList = (List) salesProjectionDAO.executeSelectQuery(customSql.toString());
            int count = (Integer.valueOf(String.valueOf(resultList.get(0))));
            return count;
        } catch (ParseException e) {
            LOGGER.error(e);
        }
        return 0;
    }

    public List getProjection(boolean isCountNeeded, final ComparisonLookupDTO lookUpDTO, final SessionDTO sessionDTO, final String selectedProjectionIds,
            int startIndex, int offset, List<SortByColumn> list, final Set<Container.Filter> filterValue) throws PortalException, Exception {

        String QUOTES = "'";
        String ASTERIK = "*";
        String PERCENT = Constant.PERCENT;

        String marketTypeVal;
        String brandVal;
        String projNameVal;
        String customer;
        String ndcNoVal;
        String ndcNameVal;

        String contractVal;
        String desc = StringUtils.EMPTY;
        Session session = null;
        boolean isProjectionStatus = false;

        if (Constant.SAVED.equals(lookUpDTO.getWorkflowStatus())) {
            isProjectionStatus = true;
        }
        try {
            StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);

            customSql.append("SELECT distinct PM.PROJECTION_MASTER_SID,PM.PROJECTION_NAME,PM.PROJECTION_DESCRIPTION,HT.DESCRIPTION MARKET_TYPE,CM.COMPANY_NO  Customer,CO.CONTRACT_NO Contract,\n"
                    + "BM.BRAND_NAME  BRAND,IM.ITEM_NO,IM.ITEM_NAME,PM.CREATED_DATE,PM.CREATED_BY FROM   CONTRACT_MASTER CO \n"
                    + "JOIN HELPER_TABLE HT ON CO.CONTRACT_TYPE = HT.HELPER_TABLE_SID \n"
                    + "JOIN CCP_DETAILS CCP ON CCP.CONTRACT_MASTER_SID = CO.CONTRACT_MASTER_SID \n"
                    + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID \n"
                    + " JOIN PROJECTION_DETAILS CUR_PD ON CUR_PD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + "JOIN PROJECTION_MASTER PM ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID \n");
            if (!isProjectionStatus) {
                customSql.append("       JOIN workflow_master WM \n"
                        + "         ON PM.projection_master_sid = WM.projection_master_sid \n"
                        + "       JOIN helper_table HT1 \n"
                        + "         ON HT1.helper_table_sid = WM.workflow_status_id ");
            }
            customSql.append("JOIN COMPANY_MASTER CM ON CCP.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID \n"
                    + "JOIN ITEM_MASTER IM ON CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID \n"
                    + "LEFT JOIN BRAND_MASTER BM ON BM.BRAND_MASTER_SID = im.BRAND_MASTER_SID WHERE ");

            if (lookUpDTO.getMarketType().equals(Constant.NULL) || lookUpDTO.getMarketType().equals(StringUtils.EMPTY)) {
                marketTypeVal = "'%'";
            } else {
                marketTypeVal = lookUpDTO.getMarketType().replace(ASTERIK, PERCENT);
                marketTypeVal = QUOTES + marketTypeVal + QUOTES;
            }
            customSql.append("( HT.list_name = 'CONTRACT_TYPE' AND HT.DESCRIPTION LIKE ").append(marketTypeVal).append(")");

            if (lookUpDTO.getBrand() == null || lookUpDTO.getBrand().equals(StringUtils.EMPTY)) {
                brandVal = "'%'";
            } else {
                brandVal = lookUpDTO.getBrand().replace(ASTERIK, PERCENT);
                brandVal = QUOTES + brandVal + QUOTES;
            }
            customSql.append("  AND (BM.BRAND_NAME LIKE ").append(brandVal).append(" or BM.BRAND_NAME is null)");

            if (lookUpDTO.getProjectionName() == null || lookUpDTO.getProjectionName().equals(StringUtils.EMPTY)) {
                projNameVal = "'%'";
            } else {
                projNameVal = lookUpDTO.getProjectionName().replace(ASTERIK, PERCENT);
                projNameVal = QUOTES + projNameVal + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_NAME LIKE ").append(projNameVal).append(" or PM.PROJECTION_NAME is null)");
            if (lookUpDTO.getContract() == null || lookUpDTO.getContract().equals(StringUtils.EMPTY)) {
                contractVal = "'%'";
            } else {
                contractVal = lookUpDTO.getContract().replace(ASTERIK, PERCENT);
                contractVal = QUOTES + contractVal + QUOTES;
            }
            customSql.append("AND (CO.CONTRACT_NO LIKE ").append(contractVal).append(" or CO.CONTRACT_NO is null)");

            if (lookUpDTO.getProjectionDescription() == null || lookUpDTO.getProjectionDescription().equals(StringUtils.EMPTY)) {
                desc = "'%'";
            } else {
                desc = lookUpDTO.getProjectionDescription().replace(ASTERIK, PERCENT);
                desc = QUOTES + desc + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_DESCRIPTION LIKE ").append(desc).append(" or PM.PROJECTION_DESCRIPTION is null)");

            if (lookUpDTO.getNdcName() == null || lookUpDTO.getNdcName().equals(StringUtils.EMPTY)) {
                ndcNameVal = "'%'";
            } else {
                ndcNameVal = lookUpDTO.getNdcName().replace(ASTERIK, PERCENT);
                ndcNameVal = QUOTES + ndcNameVal + QUOTES;
            }
            customSql.append("AND (IM.ITEM_NAME LIKE ").append(ndcNameVal).append(" or IM.ITEM_NAME is null)");
            if (lookUpDTO.getNdcNo() == null || lookUpDTO.getNdcNo().equals(StringUtils.EMPTY)) {
                ndcNoVal = "'%'";
            } else {
                ndcNoVal = lookUpDTO.getNdcNo().replace(ASTERIK, PERCENT);
                ndcNoVal = QUOTES + ndcNoVal + QUOTES;
            }
            customSql.append("AND (IM.ITEM_NO LIKE ").append(ndcNoVal).append("or IM.ITEM_NO is null)");
            if (lookUpDTO.getCustomer() == null || lookUpDTO.getCustomer().equals(StringUtils.EMPTY)) {
                customer = "'%'";
            } else {
                customer = lookUpDTO.getCustomer().replace(ASTERIK, PERCENT);
                customer = QUOTES + customer + QUOTES;
            }
            customSql.append("AND (CM.COMPANY_NO LIKE ").append(customer).append("or CM.COMPANY_NO is null)");

            if (!Constant.NULL.equals(lookUpDTO.getCreatedDateFrom()) && lookUpDTO.getCreatedDateFrom() != null
                    && !Constant.NULL.equals(lookUpDTO.getCreatedDateTo()) && !StringUtils.isEmpty(lookUpDTO.getCreatedDateTo())) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE BETWEEN '");
                customSql.append(format2.format(lookUpDTO.getCreatedDateFrom()));
                customSql.append("' AND '");
                customSql.append(format2.format(format2.parse(lookUpDTO.getCreatedDateTo())));
                customSql.append("' ");
            }
            if (isProjectionStatus) {
                customSql.append("and pm.is_approved not in ('Y','C','A','R')");
            } else {
                customSql.append("AND HT1.list_name = 'WorkFlowStatus' and ht1.description = " + QUOTES + lookUpDTO.getWorkflowStatus() + QUOTES);
            }
            customSql.append("AND PM.PROJECTION_MASTER_SID NOT IN (").append(selectedProjectionIds).append(")");
            customSql.append("AND CUR_PD.PROJECTION_MASTER_SID =").append(selectedProjectionIds);
            customSql.append(" AND PM.FORECASTING_TYPE='Mandated'");
            String columnName = "PROJECTION_NAME";
            String orderBy = "ASC";

            if (list != null) {
                for (final Iterator<SortByColumn> iterator = list.iterator(); iterator.hasNext();) {
                    final SortByColumn orderByColumn = (SortByColumn) iterator.next();
                    if (orderByColumn.getName().equals(Constant.PROJECTION_NAME)) {
                        columnName = "PROJECTION_NAME";
                    } else if (orderByColumn.getName().equals(Constant.DESCRIPTION)) {
                        columnName = "PROJECTION_DESCRIPTION";
                    } else if (orderByColumn.getName().equals("marketType")) {
                        columnName = "MARKET_TYPE";
                    } else if (orderByColumn.getName().equals("customer")) {
                        columnName = "COMPANY_NO";
                    } else if (orderByColumn.getName().equals(Constant.CONTRACT)) {
                        columnName = "CONTRACT_NO";
                    } else if (orderByColumn.getName().equals(Constant.BRAND)) {
                        columnName = "BRAND_NAME";
                    } else if (orderByColumn.getName().equals("ndc")) {
                        columnName = "ITEM_NO";
                    } else if (orderByColumn.getName().equals("ndcName")) {
                        columnName = "ITEM_NAME";
                    } else if (orderByColumn.getName().equals("createdDate")) {
                        columnName = "CREATED_DATE";
                    } else if (orderByColumn.getName().equals("createdBy")) {
                        columnName = "CREATED_BY";
                    }
                    if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                        orderBy = "ASC";

                    } else {
                        orderBy = "DESC";
                    }
                }
            }
            if (filterValue != null) {
                for (Container.Filter filter : filterValue) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        if (StringUtils.isNotBlank(stringFilter.getFilterString()) && !Constant.NULL.equals(stringFilter.getFilterString())) {
                            String filterString = "'%" + stringFilter.getFilterString() + "%'";
                            if (Constant.PROJECTION_NAME.equals(stringFilter.getPropertyId())) {
                                customSql.append("AND PM.PROJECTION_NAME LIKE ").append(filterString);
                            } else if (Constant.PROJECTIONDESCRIPTION.equals(stringFilter.getPropertyId())) {
                                customSql.append("AND PM.PROJECTION_DESCRIPTION LIKE ").append(filterString);
                            } else if ("marketType".equals(stringFilter.getPropertyId())) {
                                customSql.append("AND CO.CONTRACT_TYPE LIKE ").append(filterString);
                            } else if ("customer".equals(stringFilter.getPropertyId())) {
                                customSql.append("AND CM.COMPANY_NO LIKE ").append(filterString);
                            } else if (Constant.CONTRACT.equals(stringFilter.getPropertyId())) {
                                customSql.append("AND CO.CONTRACT_NO LIKE ").append(filterString);
                            } else if (Constant.BRAND.equals(stringFilter.getPropertyId())) {
                                customSql.append("AND BM.BRAND_NAME LIKE ").append(filterString);
                            } else if ("ndcNo".equals(stringFilter.getPropertyId())) {
                                customSql.append("AND IM.ITEM_NO LIKE ").append(filterString);
                            } else if ("ndcName".equals(stringFilter.getPropertyId())) {
                                customSql.append("AND IM.ITEM_NAME LIKE ").append(filterString);
                            } else if ("createdDate".equals(stringFilter.getPropertyId())) {
                                customSql.append("AND PM.CREATED_DATE LIKE ").append(filterString);
                            } else if ("createdBy".equals(stringFilter.getPropertyId())) {
                                List<String> strList = new ArrayList<String>();
                                final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
                                Criterion criterion = RestrictionsFactoryUtil.ilike(Constant.FIRSTNAME, Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT);
                                Criterion criterion1 = RestrictionsFactoryUtil.ilike(Constant.LASTNAME, Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT);
                                dynamicQuery.add(RestrictionsFactoryUtil.or(criterion, criterion1));
                                final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
                                productProjectionList.add(ProjectionFactoryUtil.property(Constant.USER_ID));
                                dynamicQuery.setProjection(productProjectionList);
                                strList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                                String userID = CommonUtils.CollectionToString(strList, false);
                                customSql.append("AND PM.CREATED_BY IN (").append(userID).append(")");
                            }
                        }
                    } else if (filter instanceof Between) {
                        Between betweenFilter = (Between) filter;
                        Date startValue = (Date) betweenFilter.getStartValue();
                        Date endValue = (Date) betweenFilter.getEndValue();
                        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00.000");
                        if ((startValue != null && !StringUtils.EMPTY.equals(String.valueOf(startValue)))) {
                            customSql.append(" and PM.CREATED_DATE >= '").append(outputDateFormat.format(startValue)).append("' ");
                        }
                        if (endValue != null && !StringUtils.EMPTY.equals(String.valueOf(endValue))) {
                            outputDateFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59.999");
                            customSql.append(" and PM.CREATED_DATE <= '").append(outputDateFormat.format(endValue)).append("' ");
                        }

                    } else if (filter instanceof Compare) {
                        Compare compare = (Compare) filter;
                        Date date = (Date) compare.getValue();
                        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00.000");
                        if (Operation.GREATER.equals(compare.getOperation()) || Operation.GREATER_OR_EQUAL.equals(compare.getOperation())) {
                            customSql.append(" and PM.CREATED_DATE >= '").append(outputDateFormat.format(date)).append("' ");
                        }
                        if (Operation.LESS.equals(compare.getOperation()) || Operation.LESS_OR_EQUAL.equals(compare.getOperation())) {
                            outputDateFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59.999");
                            customSql.append(" and PM.CREATED_DATE <= '").append(outputDateFormat.format(date)).append("' ");
                        }
                    }
                }
            }
            customSql.append(" ORDER BY ").append(columnName).append(" ").append(orderBy);
            customSql.append(" OFFSET ");
            customSql.append(startIndex);
            customSql.append(" ROWS FETCH NEXT ");
            customSql.append(offset);
            customSql.append(" ROWS ONLY;");

            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            List<ComparisonLookupDTO> resultList = convertResultsToLookupDTO((List) salesProjectionDAO.executeSelectQuery(customSql.toString()));
            return resultList;
        } catch (ParseException e) {
            LOGGER.error(e);
        }

        return null;
    }

    public List<ComparisonLookupDTO> convertResultsToLookupDTO(List list) throws ParseException, PortalException, SystemException {
        List<ComparisonLookupDTO> resultList = new ArrayList<ComparisonLookupDTO>();

        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);
            ComparisonLookupDTO lookupDTO = new ComparisonLookupDTO();
            lookupDTO.setProjectionId(Integer.valueOf(String.valueOf(obj[0])));
            lookupDTO.setProjectionName(String.valueOf(obj[1]));
            lookupDTO.setProjectionDescription(String.valueOf(obj[2]));
            lookupDTO.setMarketType(String.valueOf(obj[3]));
            lookupDTO.setCustomer(String.valueOf(obj[4]));
            lookupDTO.setContract(String.valueOf(obj[5]));
            lookupDTO.setBrand(obj[6] != null ? String.valueOf(obj[6]) : StringUtils.EMPTY);
            lookupDTO.setNdcNo(String.valueOf(obj[7]));
            lookupDTO.setNdcName(String.valueOf(obj[8]));
            if (obj[9] == null) {
                lookupDTO.setCreatedDateFrom(null);
            } else {
                Date startDate = (Date) obj[9];
                DateFormat df = new SimpleDateFormat(DATE_FORMAT.getConstant());
                startDate = df.parse(CommonUtils.convertDateToString(startDate));
                lookupDTO.setCreatedDate(startDate);
                lookupDTO.setCreatedDateFrom(startDate);
            }
            if (obj[10] == null) {
                lookupDTO.setCreatedBy(StringUtils.EMPTY);
            } else {
                lookupDTO.setCreatedBy(new Converters().getUserFLName(new Converters().convertNullToEmpty(obj[10].toString())));
            }
            resultList.add(lookupDTO);
        }

        return resultList;
    }

    public void getDateRangeHeaders(final ExtFilterTreeTable rightTable, CustomTableHeaderDTO rightHeader, Object fromDate, Object toDate, String frequency) {
        String fromValue = String.valueOf(fromDate);
        String toValue = String.valueOf(toDate);
        String[] fromArray = fromValue.split(" ");
        String[] toArray = toValue.split(" ");
        List<Object> visibleDoubleCol = rightHeader.getDoubleColumns();
        List<String> visibleDoubleHeader = rightHeader.getDoubleHeaders();

        List<Object> visibleSingleCol = new ArrayList<Object>();
        List<Object> visibleSingleColumn = rightHeader.getSingleColumns();
        List<String> visibleSingleHeader = rightHeader.getSingleHeaders();
        List<String> visibleSingleHead = new ArrayList<String>();

        Map<Object, Object[]> doubleMap = rightHeader.getDoubleHeaderMaps();
        Map<Object, Object[]> doubleFinalMap = new HashMap<Object, Object[]>();
        List<Object> finalVisList = new ArrayList<Object>();
        List<String> finalHeaderList = new ArrayList<String>();
        String from = StringUtils.EMPTY;
        String to = StringUtils.EMPTY;
        for (int i = 0; i < fromArray.length; i++) {
            from = from + fromArray[i];
        }
        for (int i = 0; i < toArray.length; i++) {
            to = to + toArray[i];
        }
        if (frequency.equalsIgnoreCase(Constant.MONTHLY)) {
            from = from;
            to = to;
        }

        int start = visibleDoubleCol.indexOf(from);
        int end = visibleDoubleCol.indexOf(to);

        for (int i = start; i <= end; i++) {

            finalVisList.add(visibleDoubleCol.get(i));
            finalHeaderList.add(visibleDoubleHeader.get(i));
            visibleSingleCol.addAll(Arrays.asList(doubleMap.get(visibleDoubleCol.get(i))));
            doubleFinalMap.put(visibleDoubleCol.get(i), doubleMap.get(visibleDoubleCol.get(i)));

        }

        int startSingle = visibleSingleColumn.indexOf(visibleSingleCol.get(0));
        int endSingle = visibleSingleColumn.indexOf(visibleSingleCol.get(visibleSingleCol.size() - 1));

        for (int i = startSingle; i <= endSingle; i++) {
            visibleSingleHead.add(visibleSingleHeader.get(i));
        }
        rightTable.setVisibleColumns(visibleSingleCol.toArray());
        rightTable.setColumnHeaders(visibleSingleHead.toArray(new String[visibleSingleHead.size()]));
        rightTable.setDoubleHeaderVisibleColumns(finalVisList.toArray());
        rightTable.setDoubleHeaderColumnHeaders(finalHeaderList.toArray(new String[finalHeaderList.size()]));
        rightTable.setDoubleHeaderMap(doubleFinalMap);
    }

    public int getConfiguredProjectionVarianceCount(Object parentId, PVSelectionDTO projSelDTO, boolean isLevelCount) {
        try {
            int count = 0;
            setSelectionDTO(projSelDTO);
            setRightHeader(projSelDTO.getRightHeader());
            count += getProjVarianceCount(projSelDTO, parentId, isLevelCount);
            return count;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return 0;
    }

    public int getProjVarianceCount(PVSelectionDTO selectionDTO, Object parentId, boolean isLevelsCount) throws Exception {
        int count = 0;
        ProjectionVarianceDTO parentDto = null;
        selectionDTO.setIsLevel(false);
        if ((!selectionDTO.isIslevelFiler() && !selectionDTO.isIsCustomerDdlb()) || (parentId instanceof ProjectionVarianceDTO)) {
            selectionDTO.setYear(Constant.All);
            if (selectionDTO.getLevel().equals(Constant.TOTAL)) {
                selectionDTO.setIsLevel(true);
                parentDto = new ProjectionVarianceDTO();
            }
            if (parentId instanceof ProjectionVarianceDTO) {
                parentDto = (ProjectionVarianceDTO) parentId;
                selectionDTO.setLevelNo(parentDto.getLevelNo());
                selectionDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                selectionDTO.setLevelValue(parentDto.getLevelValue());
                selectionDTO.setParentNode(parentDto.getParentNode());
                selectionDTO.setHierarchyNo(parentDto.getHierarchyNo());
                if (parentDto.getHierarchyIndicator().equals(C)) {
                    selectionDTO.setCustomerHierarchyNo(selectionDTO.getHierarchyNo());
                    selectionDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                } else if (parentDto.getHierarchyIndicator().equals(P)) {
                    selectionDTO.setProductHierarchyNo(selectionDTO.getHierarchyNo());
                    selectionDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                }
                selectionDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                selectionDTO.setGroup(parentDto.getGroup());
            } else {
                if (selectionDTO.isIsCustomHierarchy()) {
                    selectionDTO.setLevelNo(0);
                    selectionDTO.setTreeLevelNo(0);
                }else if (C.equals(selectionDTO.getHierarchyIndicator())) {
                    selectionDTO.setLevelNo(selectionDTO.getCustomerLevelNo() - 1);
                    selectionDTO.setTreeLevelNo(selectionDTO.getCustomerLevelNo() - 1);
                } else if (P.equals(selectionDTO.getHierarchyIndicator())) {
                    selectionDTO.setLevelNo(selectionDTO.getProductLevelNo() - 1);
                    selectionDTO.setTreeLevelNo(selectionDTO.getProductLevelNo() - 1);
                } 

                selectionDTO.setGroup(StringUtils.EMPTY);
                selectionDTO.setHierarchyNo(StringUtils.EMPTY);
                selectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
                selectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            }
            if (selectionDTO.getPivotView().equals(Constant.PERIOD)) {
                count += getProjectionResultsCount(selectionDTO, selectionDTO.getParentNode().trim(), selectionDTO.getLevelNo(), parentDto, isLevelsCount);
            } else {
                count += getPivotResultsCount(parentId, selectionDTO, isLevelsCount);
            }
        } else if (isLevelsCount || selectionDTO.isIsCustomerDdlb()) {

            selectionDTO.setLevelNo(selectionDTO.getFilterLevelNo());
            selectionDTO.setTreeLevelNo(selectionDTO.getFilterLevelNo());
            count += configureLevelsCount(selectionDTO);
        }
        return count;

    }

    public int getProjectionResultsCount(PVSelectionDTO pVSelectionDTO, String parentName, int levelNo, ProjectionVarianceDTO parentDto, boolean isLevelsCount) throws Exception {
        int count = 0;
        if (parentDto != null) {
            count += getVarianceSalesCount(pVSelectionDTO, String.valueOf(levelNo));
            count += getVarianceDiscountCount(pVSelectionDTO, parentName, String.valueOf(levelNo), parentDto);
        }

        if (!selectionDTO.isIsCustomerDdlb() && !pVSelectionDTO.isIslevelFiler() && !pVSelectionDTO.isIsLevel() && isLevelsCount) {
            if (parentDto == null || (!parentDto.getGroup().contains(Constant.DISCOUNT_SMALL) && !parentDto.getGroup().contains("RPU"))) {
                if (parentDto == null && pVSelectionDTO.isIsCustomHierarchy()) {
                    pVSelectionDTO.setTreeLevelNo(1);
                } else {
                    pVSelectionDTO.setTreeLevelNo(pVSelectionDTO.getTreeLevelNo() + 1);
                    pVSelectionDTO.setLevelNo(pVSelectionDTO.getTreeLevelNo() + 1);
                }

                count += configureLevelsCount(pVSelectionDTO);
            }
        }
        pVSelectionDTO.setLevelCount(count);
        return count;

    }

    public int getPivotResultsCount(Object parent, PVSelectionDTO pvsdto, boolean isLevelsCount) {
        int count = 0;
        if (parent instanceof ProjectionVarianceDTO) {
            count += pvsdto.getPeriodList().size();
        }

        if (Constant.TOTAL.equalsIgnoreCase(pvsdto.getLevel())) {
            count += pvsdto.getPeriodList().size();
        } else if (!selectionDTO.isIsCustomerDdlb() && !pvsdto.isIsLevel() && isLevelsCount) {
            pvsdto.setTreeLevelNo(pvsdto.getTreeLevelNo() + 1);
            pvsdto.setLevelNo(pvsdto.getTreeLevelNo() + 1);
            count += configureLevelsCount(pvsdto);
        }
        pvsdto.setLevelCount(count);
        return count;
    }

    public int configureLevelsCount(PVSelectionDTO projSelDTO) {

        return getLevelListCount(projSelDTO.getSession().getProjectionId(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIslevelFiler(), projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO);

    }

    public static int getLevelListCount(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isCustom, int customId, PVSelectionDTO projSelDTO) {
        int count = 0;
        List<Object> list = null;
        try {
            if (isCustom) {
                if (customId != 0) {
                    if (levelNo <= projSelDTO.getCustomCount()) {
                        String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, false, true, 0, 0, false, isCustom, customId, projSelDTO);
                        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
                        list = (List<Object>) salesProjectionDAO.executeSelectQuery(query);
                    }
                }
            } else {
                String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, false, true, 0, 0, false, isCustom, customId, projSelDTO);
                SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
                list = (List<Object>) salesProjectionDAO.executeSelectQuery(query);

            }
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                count = Integer.valueOf(String.valueOf(ob));
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return count;
    }

    public static String getLevelListQuery(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCount, int start, int offset, boolean isLimit, boolean isCustom, int customId, PVSelectionDTO projSelDTO) throws PortalException, Exception {
        if (isCustom) {
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            String hierarchyIndicatorQuery = "select HIERARCHY_INDICATOR from dbo.CUSTOM_VIEW_DETAILS where CUSTOM_VIEW_MASTER_SID=" + customId + " and LEVEL_NO=" + levelNo;
            List<Object> list = (List<Object>) salesProjectionDAO.executeSelectQuery(hierarchyIndicatorQuery);
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                hierarchyIndicator = String.valueOf(ob);
            } else {
                hierarchyIndicator = StringUtils.EMPTY;
            }
        }
        String hierarchyNo1 = StringUtils.EMPTY;
        String whereCond = " ";
        if (hierarchyNo != null) {
            if (!hierarchyNo.equals(StringUtils.EMPTY)) {
                if (isExpand) {
                    whereCond = " and HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO='" + hierarchyNo + "' ";
                }
                if (!isFilter) {
                    hierarchyNo1 = hierarchyNo;
                }
            }
        }
        String recordNumber = StringUtils.EMPTY;
        String selectClause = "select ";
        if (isCount) {
            selectClause += " Count(distinct HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES) ";
        } else {
            selectClause += " distinct HLD" + hierarchyIndicator.trim() + ".LEVEL_NO, "
                    + " HLD" + hierarchyIndicator.trim() + ".TREE_LEVEL_NO, "
                    + " '" + hierarchyIndicator + "' as HIERARCHY_INDICATOR,"
                    + " HLD" + hierarchyIndicator.trim() + ".LEVEL_NAME,"
                    + " HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES,"
                    + " HLD" + hierarchyIndicator.trim() + ".PARENT_NODE,"
                    + " HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO ";
            if (isLimit) {
                recordNumber += " ORDER BY HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES ASC OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
            } else {
                selectClause += ", ROW_NUMBER() OVER (ORDER BY HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES ASC) AS TEMP_INDEX ";
            }
        }
        String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid,RLD.level_no,RLD.level_no as TREE_LEVEL_NO," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD.PARENT_NODE ";
        String selectClause2 = " (SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name,RLD1.level_no as TREE_LEVEL_NO," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD1.hierarchy_level_definition_sid,RLD1.PARENT_NODE ";
        String joinQuery1 = " relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid JOIN projection_details PD "
                + "  ON PD.ccp_details_sid = CCP.ccp_details_sid  AND PD.projection_master_sid =" + projectionId + " ) CCPMAP,";

        String joinQuery2 = " relationship_level_definition RLD1 JOIN " + getViewTableName(hierarchyIndicator) + " PCH  ON PCH.relationship_level_sid = RLD1.relationship_level_sid \n"
                + " AND PCH.projection_master_sid =" + projectionId;
        if (projSelDTO.isIsCustomerDdlb()) {
            joinQuery2 += " WHERE  RLD1.hierarchy_no LIKE '" + projSelDTO.getHierarchyNo() + "' AND RLD1.LEVEL_NAME IN (" + projSelDTO.getLevelName() + ")) HLD" + hierarchyIndicator.trim();
        } else {
            joinQuery2 += " WHERE  RLD1.hierarchy_no LIKE '" + hierarchyNo1 + "%' AND RLD1.LEVEL_NO = " + levelNo + ") HLD" + hierarchyIndicator.trim();
        }
        String mainJoin = " WHERE  CCPMAP.hierarchy_no LIKE HLD" + hierarchyIndicator.trim() + ".hierarchy_no + '%'";
        String customSql = selectClause;
        if (isCustom) {

            String customViewQuery = getCustomViewLevelListQuery(projectionId, customId, hierarchyIndicator, levelNo, productHierarchyNo, customerHierarchyNo);
            customSql += " from " + customViewQuery;
        } else {
            customSql += " from " + selectClause1 + " from " + joinQuery1 + " " + selectClause2 + " from " + joinQuery2 + " " + mainJoin
                    + whereCond;
        }
        selectClause1 = null;
        selectClause2 = null;
        joinQuery1 = null;
        joinQuery2 = null;
        mainJoin = null;
        whereCond = null;
        hierarchyNo1 = null;

        customSql += recordNumber;

        return customSql;
    }

    public static String getCustomViewLevelListQuery(int projectionId, int customId, String hierarchyIndicator, int levelNo, String productHierarchyNo, String customerHierarchyNo) {
        customerHierarchyNo += Constant.PERCENT;
        productHierarchyNo += Constant.PERCENT;
        String customerLevelNo = Constant.PERCENT;
        String productLevelNo = Constant.PERCENT;

        if (hierarchyIndicator.equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            customerLevelNo = StringUtils.EMPTY + levelNo;
        } else if (hierarchyIndicator.equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            productLevelNo = StringUtils.EMPTY + levelNo;
        }
        String customViewQuery = "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId
                + " ) CCPMAPC"
                + " JOIN"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId
                + " ) CCPMAPP "
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID"
                + " JOIN "
                + " (SELECT distinct RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO as TREE_LEVEL_NO, RLD2.LEVEL_NO,RLD2.RELATIONSHIP_LEVEL_VALUES,RLD2.PARENT_NODE,RLD2.LEVEL_NAME FROM dbo.CUSTOM_VIEW_DETAILS CVD "
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON "
                + " CVD.CUSTOM_VIEW_MASTER_SID=" + customId + " AND CVD.LEVEL_NO  like '" + customerLevelNo + "'"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID "
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD2.HIERARCHY_NO like '" + customerHierarchyNo + "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'"
                + " JOIN "
                + " (SELECT distinct RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO as TREE_LEVEL_NO, RLD2.LEVEL_NO,RLD2.RELATIONSHIP_LEVEL_VALUES,RLD2.PARENT_NODE,RLD2.LEVEL_NAME FROM dbo.CUSTOM_VIEW_DETAILS CVD "
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON "
                + " CVD.CUSTOM_VIEW_MASTER_SID=" + customId + " AND CVD.LEVEL_NO like '" + productLevelNo + "'"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID "
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD2.HIERARCHY_NO like '" + productHierarchyNo + "') HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'";
        return customViewQuery;
    }

    public static String getViewTableName(String hierarchyIndicator) {
        String viewtable = StringUtils.EMPTY;
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(hierarchyIndicator)) {
            viewtable = "PROJECTION_CUST_HIERARCHY";
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(hierarchyIndicator)) {
            viewtable = "PROJECTION_PROD_HIERARCHY";
        }
        return viewtable;
    }

    public int getVarianceSalesCount(PVSelectionDTO pVSelectionDTO, String levelNo) {
        int count = 0;
        String group = pVSelectionDTO.getGroup();
        boolean flag = false;
        boolean isTotal = false;
        boolean isDiscounts = false;
        boolean discountFlag = false;
        if (!group.contains(Constants.LabelConstants.DISCOUNT_AMOUNT.getConstant()) && !group.contains(Constants.LabelConstants.DISCOUNT_PERC.getConstant()) && !group.contains("RPU")) {
            flag = true;
        }
        if (group.equals(Constant.PROJECTION_TOTAL)) {
            isTotal = true;
        }
        if (group.contains(Constants.LabelConstants.DISCOUNT_AMOUNT.getConstant()) || group.contains(Constants.LabelConstants.DISCOUNT_PERC.getConstant())) {
            discountFlag = true;
        }
        if (selectionDTO.getLevel().equals(Constants.LabelConstants.TOTAL.getConstant()) && discountFlag) {
            isDiscounts = true;
        }

        if (pVSelectionDTO.isVarExFacSales() && flag) {
            if (pVSelectionDTO.isColValue()) {
                count++;
            }
            if (pVSelectionDTO.isColVariance()) {
                count++;
            }
            if (pVSelectionDTO.isColPercentage()) {
                count++;
            }
        }// Demand Sales
        if (pVSelectionDTO.isVarDemandSales() && flag) {
            if (pVSelectionDTO.isColValue()) {
                count++;
            }
            if (pVSelectionDTO.isColVariance()) {
                count++;
            }
            if (pVSelectionDTO.isColPercentage()) {
                count++;
            }
        }// Inventory
        if (pVSelectionDTO.isVarInvSales() && flag) {
            if (pVSelectionDTO.isColValue()) {
                count++;
            }
            if (pVSelectionDTO.isColVariance()) {
                count++;
            }
            if (pVSelectionDTO.isColPercentage()) {
                count++;
            }
        }
        //Percentage Of Ex Factory
        if (pVSelectionDTO.isVarPerExFacSales() && flag) {
            if (pVSelectionDTO.isColValue()) {
                count++;
            }
            if (pVSelectionDTO.isColVariance()) {
                count++;
            }
            if (pVSelectionDTO.isColPercentage()) {
                count++;
            }
        }
        //Percentage Of Demand Sales
        if (pVSelectionDTO.isVarPerDemandSales() && flag) {
            if (pVSelectionDTO.isColValue()) {
                count++;
            }
            if (pVSelectionDTO.isColVariance()) {
                count++;
            }
            if (pVSelectionDTO.isColPercentage()) {
                count++;
            }
        }
        //Percentage Of Inventory
        if (pVSelectionDTO.isVarPerInvSales() && flag) {
            if (pVSelectionDTO.isColValue()) {
                count++;
            }
            if (pVSelectionDTO.isColVariance()) {
                count++;
            }
            if (pVSelectionDTO.isColPercentage()) {
                count++;
            }
        }
        //Contract Sales
        if (pVSelectionDTO.isVarContractsales() && flag) {
            if (pVSelectionDTO.isColValue()) {
                count++;
            }
            if (pVSelectionDTO.isColVariance()) {
                count++;
            }
            if (pVSelectionDTO.isColPercentage()) {
                count++;
            }
        }
        //Contract Units
        if (pVSelectionDTO.isVarContractUnits() && flag) {
            if (pVSelectionDTO.isColValue()) {
                count++;
            }
            if (pVSelectionDTO.isColVariance()) {
                count++;
            }
            if (pVSelectionDTO.isColPercentage()) {
                count++;
            }
        }
        return count;
    }

    public int getVarianceDiscountCount(PVSelectionDTO pVSelectionDTO, String parentName, String levelNo, ProjectionVarianceDTO parentDto) throws Exception {

        int count = 0;
        String group = parentDto.getGroup();
        if (Constants.LabelConstants.TOTAL_DISCOUNT.getConstant().equalsIgnoreCase(selectionDTO.getDiscountLevel())) {
            parentName = StringUtils.EMPTY;
        }
        boolean flag = false;
        if (!group.contains(Constants.LabelConstants.DISCOUNT_AMOUNT.getConstant()) && !group.contains(Constants.LabelConstants.DISCOUNT_PERC.getConstant()) && !group.contains("RPU")) {
            flag = true;
        }

        if (pVSelectionDTO.isVarDisAmount()) {
            if (pVSelectionDTO.isColValue()) {
                count++;
            }
            if (pVSelectionDTO.isColVariance()) {
                count++;
            }
            if (pVSelectionDTO.isColPercentage()) {
                count++;
            }
        }
        //Discount % 
        if (pVSelectionDTO.isVarDisRate()) {
            if (pVSelectionDTO.isColValue()) {
                count++;
            }
            if (pVSelectionDTO.isColVariance()) {
                count++;
            }
            if (pVSelectionDTO.isColPercentage()) {
                count++;
            }
        }
        //RPU
        if (pVSelectionDTO.isVarRPU()) {
            if (pVSelectionDTO.isColValue()) {
                count++;
            }
            if (pVSelectionDTO.isColVariance()) {
                count++;
            }
            if (pVSelectionDTO.isColPercentage()) {
                count++;
            }
        }
        //Net Sales
        if (pVSelectionDTO.isVarNetSales() && flag) {
            if (pVSelectionDTO.isColValue()) {
                count++;
            }
            if (pVSelectionDTO.isColVariance()) {
                count++;
            }
            if (pVSelectionDTO.isColPercentage()) {
                count++;
            }
        }
        //COGC
        if (pVSelectionDTO.isVarCOGC() && flag) {
            if (pVSelectionDTO.isColValue()) {
                count++;
            }
            if (pVSelectionDTO.isColVariance()) {
                count++;
            }
            if (pVSelectionDTO.isColPercentage()) {
                count++;
            }
        }
        //Net PRofit
        if (pVSelectionDTO.isVarNetProfit() && flag) {
            if (pVSelectionDTO.isColValue()) {
                count++;
            }
            if (pVSelectionDTO.isColVariance()) {
                count++;
            }
            if (pVSelectionDTO.isColPercentage()) {
                count++;
            }
        }

        if (pVSelectionDTO.isColValue() && group.contains(Constant.PVVariables.VAR_DIS_AMOUNT.toString().concat(Constant.VALUE)) || pVSelectionDTO.isColVariance() && group.contains(Constant.PVVariables.VAR_DIS_AMOUNT.toString().concat(Constant.VARIANCE))
                || pVSelectionDTO.isColPercentage() && group.contains(Constant.PVVariables.VAR_DIS_AMOUNT.toString().concat(Constant.CHANGE)) || pVSelectionDTO.isColValue() && group.contains(Constant.PVVariables.VAR_DIS_RATE.toString().concat(Constant.VALUE))
                || pVSelectionDTO.isColVariance() && group.contains(Constant.PVVariables.VAR_DIS_RATE.toString().concat(Constant.VARIANCE)) || pVSelectionDTO.isColPercentage() && group.contains(Constant.PVVariables.VAR_DIS_RATE.toString().concat(Constant.CHANGE))
                || pVSelectionDTO.isColValue() && group.contains(Constant.PVVariables.VAR_RPU.toString().concat(Constant.VALUE)) || pVSelectionDTO.isColVariance() && group.contains(Constant.PVVariables.VAR_RPU.toString().concat(Constant.VARIANCE))
                || pVSelectionDTO.isColPercentage() && group.contains(Constant.PVVariables.VAR_RPU.toString().concat(Constant.CHANGE))) {
            count = 2;
        }
        if (group.contains(Constant.MANDATED_DISCOUNT) || group.contains("Supplemental Discount") || group.contains("Mandated RPU") || group.contains("Supplemental RPU")) {
            int pgCount = getProgramCodeCount(pVSelectionDTO, parentDto, parentName, levelNo);
            count = pgCount;
        }
        return count;
    }

    public List<ProjectionVarianceDTO> getConfiguredProjectionVariance(Object parentId, PVSelectionDTO projSelDTO, int start, int offset) {
        try {
            List<ProjectionVarianceDTO> list;
            setSelectionDTO(projSelDTO);
            setRightHeader(projSelDTO.getRightHeader());
            list = getProjVariance(projSelDTO, parentId, start, offset);
            return list;
        } catch (Exception ex) {

            LOGGER.error(ex);
        }
        return null;

    }

    public List<ProjectionVarianceDTO> getProjVariance(PVSelectionDTO selectionDTO, Object parentId, int start, int offset) throws Exception {
        List<ProjectionVarianceDTO> resultList = new ArrayList<ProjectionVarianceDTO>();
        ProjectionVarianceDTO parentDto = null;
        selectionDTO.setIsLevel(false);
        selectionDTO.setStartPeriod(selectionDTO.getProjectionStartPeriod());
        selectionDTO.setEndPeriod(selectionDTO.getForecastEndPeriod());
        selectionDTO.setStartYear(selectionDTO.getProjectionStartYear());
        selectionDTO.setEndYear(selectionDTO.getForecastDTO().getForecastEndYear());
        if ((!selectionDTO.isIslevelFiler() && !selectionDTO.isIsCustomerDdlb()) || (parentId instanceof ProjectionVarianceDTO)) {
            selectionDTO.setYear(Constant.All);
            if (selectionDTO.getLevel().equals(Constant.TOTAL)) {
                selectionDTO.setIsLevel(true);
                parentDto = new ProjectionVarianceDTO();
            }

            if (parentId instanceof ProjectionVarianceDTO) {
                parentDto = (ProjectionVarianceDTO) parentId;
                selectionDTO.setLevelNo(parentDto.getLevelNo());
                selectionDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                selectionDTO.setLevelValue(parentDto.getLevelValue());
                selectionDTO.setParentNode(parentDto.getParentNode());
                selectionDTO.setHierarchyNo(parentDto.getHierarchyNo());
                if (parentDto.getHierarchyIndicator().equals(C)) {
                    selectionDTO.setCustomerHierarchyNo(selectionDTO.getHierarchyNo());
                    selectionDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                } else if (parentDto.getHierarchyIndicator().equals(P)) {
                    selectionDTO.setProductHierarchyNo(selectionDTO.getHierarchyNo());
                    selectionDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                }
                selectionDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                selectionDTO.setGroup(parentDto.getGroup());
            } else {
                if (C.equals(selectionDTO.getHierarchyIndicator())) {
                    selectionDTO.setLevelNo(selectionDTO.getCustomerLevelNo() - 1);
                    selectionDTO.setTreeLevelNo(selectionDTO.getCustomerLevelNo() - 1);
                } else if (P.equals(selectionDTO.getHierarchyIndicator())) {
                    selectionDTO.setLevelNo(selectionDTO.getProductLevelNo() - 1);
                    selectionDTO.setTreeLevelNo(selectionDTO.getProductLevelNo() - 1);
                } else {
                    selectionDTO.setLevelNo(0);
                    selectionDTO.setTreeLevelNo(0);
                }
                selectionDTO.setGroup(StringUtils.EMPTY);
                selectionDTO.setHierarchyNo(StringUtils.EMPTY);
                selectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
                selectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            }
            try {
                if (selectionDTO.getPivotView().equals(Constant.PERIOD)) {
                    resultList = getPeriodResults(selectionDTO, selectionDTO, start, offset, parentDto);
                } else {
                    resultList = getPivotResults(parentId, selectionDTO, start, offset);
                }
            } catch (Exception e) {
                LOGGER.error(e);
            }
        } else {
            selectionDTO.setLevelNo(selectionDTO.getFilterLevelNo());
            selectionDTO.setTreeLevelNo(selectionDTO.getFilterLevelNo());
            int maxRecord = 0;
            maxRecord = - 1;
            resultList = configureLevels(start, offset, selectionDTO, maxRecord);
        }
        return resultList;

    }

    public List<ProjectionVarianceDTO> getPeriodResults(PVSelectionDTO pVSelectionDTO, PVSelectionDTO baseVariables, int start, int offset, ProjectionVarianceDTO parentDto) {

        LOGGER.info(" Inside getPeriodResults ");
        List<ProjectionVarianceDTO> projDTOList = new ArrayList<ProjectionVarianceDTO>();
        try {
            List<Object> dataList = new ArrayList<Object>();
            List< ProjectionVarianceDTO> tobeAddedList = new ArrayList<ProjectionVarianceDTO>();
            int neededRecord = offset;
            int started = start;
            int maxRecord = 0;
            boolean isDiscountExpand = false;
            pVSelectionDTO.setDiscountFlag(false);
            if (!pVSelectionDTO.getDiscountLevel().equalsIgnoreCase(TOTAL_DISCOUNT.getConstant())) {
                pVSelectionDTO.setDiscountFlag(true);
            }
            if (parentDto != null) {

                if (parentDto.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT) || parentDto.getGroup().contains(CommonUtils.VAR_DIS_RATE) || parentDto.getGroup().contains("RPU")) {
                    pVSelectionDTO.setGroup(parentDto.getGroup());
                    isDiscountExpand = true;
                }

                String parentName = parentDto.getParentNode().trim();
                String levelNo = String.valueOf(parentDto.getTreeLevelNo());
                if (pVSelectionDTO.getPivotView().equals(Constants.LabelConstants.PERIOD.getConstant())) {
                    maxRecord += getVarianceSalesCount(pVSelectionDTO, levelNo);
                    maxRecord += getVarianceDiscountCount(pVSelectionDTO, parentName, levelNo, parentDto);
                } else {
                    maxRecord = pVSelectionDTO.getPeriodList().size() + 1;
                }

                if (started < maxRecord) {
                    if (pivotTotalList.isEmpty()) {
                        getTotalPivotVariance(pVSelectionDTO);
                        if (pVSelectionDTO.getProjectionPeriodOrder().equals(Constant.DESCENDING)) {
                            Collections.reverse(pivotTotalList);
                        }
                    }

                    if (isDiscountExpand) {
                        pVSelectionDTO.setIsTotal(false);
                        List<Object> discountDataList = discountLevelMap.get(parentDto.getHierarchyNo());
                        String group = parentDto.getGroup();
                        if (group.startsWith(CommonUtils.VAR_DIS_AMOUNT)) {
                            pVSelectionDTO.setVarIndicator(group.substring(group.lastIndexOf(" ") + 1));

                            if (discountDataList != null) {
                                tobeAddedList.add(getManSuppDiscountAmount(pivotTotalList, discountDataList, pVSelectionDTO, CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED, 10, 7, parentDto));
                                tobeAddedList.add(getManSuppDiscountAmount(pivotTotalList, discountDataList, pVSelectionDTO, Constant.SUPPLEMENTAL, 11, 9, parentDto));
                            } else {
                                tobeAddedList.add(getManSuppDiscountAmount(pivotTotalList, dataList, pVSelectionDTO, CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED, 10, 7, parentDto));
                                tobeAddedList.add(getManSuppDiscountAmount(pivotTotalList, dataList, pVSelectionDTO, Constant.SUPPLEMENTAL, 11, 9, parentDto));
                            }
                        } else if (group.startsWith(CommonUtils.VAR_DIS_RATE)) {
                            pVSelectionDTO.setVarIndicator(group.substring(group.lastIndexOf(" ") + 1));
                            if (discountDataList != null) {
                                tobeAddedList.add(getManSuppDiscountPer(pivotTotalList, discountDataList, pVSelectionDTO, CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED, 12, 8, parentDto));
                                tobeAddedList.add(getManSuppDiscountPer(pivotTotalList, discountDataList, pVSelectionDTO, Constant.SUPPLEMENTAL, 13, 10, parentDto));
                            } else {
                                tobeAddedList.add(getManSuppDiscountPer(pivotTotalList, dataList, pVSelectionDTO, CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED, 12, 8, parentDto));
                                tobeAddedList.add(getManSuppDiscountPer(pivotTotalList, dataList, pVSelectionDTO, Constant.SUPPLEMENTAL, 13, 10, parentDto));
                            }
                        } else if (group.startsWith("RPU")) {
                            pVSelectionDTO.setVarIndicator(group.substring(group.lastIndexOf(" ") + 1));
                            if (discountDataList != null) {
                                tobeAddedList.add(getManSuppRPU(pivotTotalList, discountDataList, pVSelectionDTO, CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED, 17, 12, parentDto));
                                tobeAddedList.add(getManSuppRPU(pivotTotalList, discountDataList, pVSelectionDTO, Constant.SUPPLEMENTAL, 18, 13, parentDto));
                            } else {
                                tobeAddedList.add(getManSuppRPU(pivotTotalList, dataList, pVSelectionDTO, CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED, 17, 12, parentDto));
                                tobeAddedList.add(getManSuppRPU(pivotTotalList, dataList, pVSelectionDTO, Constant.SUPPLEMENTAL, 18, 13, parentDto));
                            }
                        }
                        // For program Code

                        if (group.startsWith(Constant.MANDATED_DISCOUNT) || group.startsWith("Supplemental Discount") || group.startsWith("Mandated RPU") || group.startsWith("Supplemental RPU")) {
                            if (!periodPcNames.isEmpty()) {
                                pVSelectionDTO.setVarIndicator(group.substring(group.lastIndexOf(" ") + 1));

                                List<Object> pcDataList = discountPcMap.get(parentDto.getHierarchyNo());
                                if (pcDataList == null) {
                                    pcDataList = getDetailsPeriodProgramCode(selectionDTO, parentDto);
                                    discountPcMap.put(parentDto.getHierarchyNo(), pcDataList);
                                }
                                int pcSize = periodPcNames.size();
                                for (int i = 0; i < pcSize; i++) {

                                    String discProgramCode = String.valueOf(periodPcNames.get(i));
                                    if (group.startsWith("Mandated Discount $")) {
                                        tobeAddedList.add(getDollarProgramCode(pcDataList, selectionDTO, discProgramCode, 2 + (i * 6), pcSize));
                                    }
                                    if (group.startsWith("Supplemental Discount $")) {
                                        tobeAddedList.add(getDollarProgramCode(pcDataList, selectionDTO, discProgramCode, 4 + (i * 6), pcSize));
                                    }
                                    if (group.startsWith("Mandated Discount %")) {
                                        tobeAddedList.add(getPercentProgramCode(pcDataList, selectionDTO, discProgramCode, 3 + (i * 6), pcSize));
                                    }
                                    if (group.startsWith("Supplemental Discount %")) {
                                        tobeAddedList.add(getPercentProgramCode(pcDataList, selectionDTO, discProgramCode, 5 + (i * 6), pcSize));
                                    }
                                    if (group.startsWith("Mandated RPU")) {
                                        tobeAddedList.add(getDollarProgramCode(pcDataList, selectionDTO, discProgramCode, 6 + (i * 6), pcSize));
                                    }
                                    if (group.startsWith("Supplemental RPU")) {
                                        tobeAddedList.add(getDollarProgramCode(pcDataList, selectionDTO, discProgramCode, 7 + (i * 6), pcSize));
                                    }
                                }
                            }
                        }

                    } else {

                        if (pVSelectionDTO.getLevel().equals(DETAIL)) {
                            dataList = getProjVarianceResults(pVSelectionDTO, parentDto);

                        }
                        List<ProjectionVarianceDTO> allList = getCustPeriodVariance(pivotTotalList, dataList, pVSelectionDTO, parentDto, baseVariables);
                        tobeAddedList.addAll(allList);
                    }

                    for (int i = started; (i < tobeAddedList.size()) && (neededRecord > 0); i++) {
                        projDTOList.add(tobeAddedList.get(i));
                        neededRecord--;
                    }
                }
            }

            if (!pVSelectionDTO.isIsCustomerDdlb() && !pVSelectionDTO.isIslevelFiler() && !pVSelectionDTO.isIsLevel() && neededRecord > 0) {
                if (parentDto == null || !parentDto.getGroup().contains(Constant.DISCOUNT_SMALL) || !parentDto.getGroup().contains("RPU")) {
                    if (parentDto == null && pVSelectionDTO.isIsCustomHierarchy()) {
                        pVSelectionDTO.setTreeLevelNo(1);
                    } else {
                        pVSelectionDTO.setTreeLevelNo(pVSelectionDTO.getTreeLevelNo() + 1);
                        pVSelectionDTO.setLevelNo(pVSelectionDTO.getTreeLevelNo() + 1);
                    }
                    List<ProjectionVarianceDTO> nextLevelList = configureLevels(start, neededRecord, pVSelectionDTO, maxRecord);
                    projDTOList.addAll(nextLevelList);
                    nextLevelList = null;
                }
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }

        LOGGER.info("  getPeriodResults  Ends");
        return projDTOList;

    }

    /**
     * get Customized period variance
     *
     * @return List
     */
    public List<ProjectionVarianceDTO> getCustPeriodVariance(final List<Object> gtsList, final List<Object> dataList, final PVSelectionDTO pvsdto, final ProjectionVarianceDTO parentDto, final PVSelectionDTO baseVariables) {
        List<ProjectionVarianceDTO> projectionVarianceDTO = new ArrayList<>();
        ProjectionVarianceDTO exFacValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO exFacVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO exFacPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO demandValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO demandVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO demandPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO invWithValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO invWithVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO invWithPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO salesValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO salesVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO salesPer = new ProjectionVarianceDTO();
        boolean isDetail = false;
        if (pvsdto.getLevel().equals(DETAIL)) {
            isDetail = true;
        }
        // GTS and Sales for POB starts
        if (isDetail) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE);
                exFacValue = getExFactorySales(gtsList, pvsdto);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE);
                exFacVar = getExFactorySales(gtsList, pvsdto);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE);
                exFacPer = getExFactorySales(gtsList, pvsdto);
            }
            //Ex fac for detail end

            //Demand sale for detail - start
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE);
                demandValue = getVarDemandSales(gtsList, pvsdto);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE);
                demandVar = getVarDemandSales(gtsList, pvsdto);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE);
                demandPer = getVarDemandSales(gtsList, pvsdto);
            }
            //Demand sale for detail - start

            //Inv with drawal sale for detail - start
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE);
                invWithValue = getVarInvSales(gtsList, pvsdto);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE);
                invWithVar = getVarInvSales(gtsList, pvsdto);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE);
                invWithPer = getVarInvSales(gtsList, pvsdto);
            }
            //Sales for POB
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE);
                salesValue = getContractSales(gtsList, dataList, pvsdto);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE);
                salesVar = getContractSales(gtsList, dataList, pvsdto);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE);
                salesPer = getContractSales(gtsList, dataList, pvsdto);
            }
        }
        // GTS and Sales for POB ends
        if (baseVariables.isVarExFacSales()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE);
                if (!isDetail) {
                    ProjectionVarianceDTO gtsData = getExFactorySales(gtsList, pvsdto);
                    projectionVarianceDTO.add(gtsData);
                } else {
                    projectionVarianceDTO.add(exFacValue);
                }
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE);
                if (!isDetail) {
                    ProjectionVarianceDTO gtsData = getExFactorySales(gtsList, pvsdto);
                    projectionVarianceDTO.add(gtsData);
                } else {
                    projectionVarianceDTO.add(exFacVar);
                }
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE);
                if (!isDetail) {
                    ProjectionVarianceDTO gtsData = getExFactorySales(gtsList, pvsdto);
                    projectionVarianceDTO.add(gtsData);
                } else {
                    projectionVarianceDTO.add(exFacPer);
                }
            }
        }
        // Var demand Sales
        if (baseVariables.isVarDemandSales()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getVarDemandSales(gtsList, pvsdto);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(demandValue);
                }
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getVarDemandSales(gtsList, pvsdto);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(demandVar);
                }
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getVarDemandSales(gtsList, pvsdto);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(demandPer);
                }
            }
        }
        // Var Inv Sales
        if (baseVariables.isVarInvSales()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getVarInvSales(gtsList, pvsdto);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(invWithValue);
                }
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getVarInvSales(gtsList, pvsdto);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(invWithVar);
                }
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getVarInvSales(gtsList, pvsdto);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(invWithPer);
                }
            }
        }
        //% Of Ex Factory
        if (baseVariables.isVarPerExFacSales()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE);
                ProjectionVarianceDTO pob = getPERExFactory(gtsList, exFacValue, salesValue, pvsdto);
                projectionVarianceDTO.add(pob);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE);
                ProjectionVarianceDTO pob = getPERExFactory(gtsList, exFacVar, salesVar, pvsdto);
                projectionVarianceDTO.add(pob);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE);
                ProjectionVarianceDTO pob = getPERExFactory(gtsList, exFacPer, salesPer, pvsdto);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Demand Sales
        if (baseVariables.isVarPerDemandSales()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE);
                ProjectionVarianceDTO pob = getPERDemand(gtsList, demandValue, salesValue, pvsdto);
                projectionVarianceDTO.add(pob);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE);
                ProjectionVarianceDTO pob = getPERDemand(gtsList, demandVar, salesVar, pvsdto);
                projectionVarianceDTO.add(pob);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE);
                ProjectionVarianceDTO pob = getPERDemand(gtsList, demandPer, salesPer, pvsdto);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Inv Sales
        if (baseVariables.isVarPerInvSales()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE);
                ProjectionVarianceDTO pob = getPERInvWithdrawal(gtsList, invWithValue, salesValue, pvsdto);
                projectionVarianceDTO.add(pob);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE);
                ProjectionVarianceDTO pob = getPERInvWithdrawal(gtsList, invWithVar, salesVar, pvsdto);
                projectionVarianceDTO.add(pob);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE);
                ProjectionVarianceDTO pob = getPERInvWithdrawal(gtsList, invWithPer, salesPer, pvsdto);
                projectionVarianceDTO.add(pob);
            }
        }
        //Contract Sales
        if (baseVariables.isVarContractsales()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE);
                if (!isDetail) {
                    ProjectionVarianceDTO sales = getContractSales(gtsList, dataList, pvsdto);
                    projectionVarianceDTO.add(sales);
                } else {
                    projectionVarianceDTO.add(salesValue);
                }
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE);
                if (!isDetail) {
                    ProjectionVarianceDTO sales = getContractSales(gtsList, dataList, pvsdto);
                    projectionVarianceDTO.add(sales);
                } else {
                    projectionVarianceDTO.add(salesVar);
                }
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE);
                if (!isDetail) {
                    ProjectionVarianceDTO sales = getContractSales(gtsList, dataList, pvsdto);
                    projectionVarianceDTO.add(sales);
                } else {
                    projectionVarianceDTO.add(salesPer);
                }
            }
        }

        //Contract Units
        if (baseVariables.isVarContractUnits()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE);
                ProjectionVarianceDTO units = getContractUnits(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(units);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE);
                ProjectionVarianceDTO units = getContractUnits(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(units);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE);
                ProjectionVarianceDTO units = getContractUnits(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(units);
            }
        }
        //Discount $ 
        if (baseVariables.isVarDisAmount()) {

            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE);
                ProjectionVarianceDTO discountDol = getDiscountDol(gtsList, dataList, pvsdto, parentDto);
                discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                projectionVarianceDTO.add(discountDol);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE);
                ProjectionVarianceDTO discountDol = getDiscountDol(gtsList, dataList, pvsdto, parentDto);
                discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                projectionVarianceDTO.add(discountDol);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE);
                ProjectionVarianceDTO discountDol = getDiscountDol(gtsList, dataList, pvsdto, parentDto);
                discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                projectionVarianceDTO.add(discountDol);
            }

        }
        //Discount % 
        if (baseVariables.isVarDisRate()) {

            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE);
                ProjectionVarianceDTO discountPer = getDiscountPer(gtsList, dataList, pvsdto, parentDto);
                discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                projectionVarianceDTO.add(discountPer);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE);
                ProjectionVarianceDTO discountPer = getDiscountPer(gtsList, dataList, pvsdto, parentDto);
                discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                projectionVarianceDTO.add(discountPer);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE);
                ProjectionVarianceDTO discountPer = getDiscountPer(gtsList, dataList, pvsdto, parentDto);
                discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                projectionVarianceDTO.add(discountPer);
            }
        }
        if (baseVariables.isVarRPU()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE);
                ProjectionVarianceDTO varRPUValue = getRPU(gtsList, dataList, pvsdto, parentDto);
                varRPUValue = setDataObjects(varRPUValue, parentDto, pvsdto);
                projectionVarianceDTO.add(varRPUValue);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE);
                ProjectionVarianceDTO varRPUVar = getRPU(gtsList, dataList, pvsdto, parentDto);
                varRPUVar = setDataObjects(varRPUVar, parentDto, pvsdto);
                projectionVarianceDTO.add(varRPUVar);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE);
                ProjectionVarianceDTO varRPUPer = getRPU(gtsList, dataList, pvsdto, parentDto);
                varRPUPer = setDataObjects(varRPUPer, parentDto, pvsdto);
                projectionVarianceDTO.add(varRPUPer);
            }
        }
        //NetSales 
        if (baseVariables.isVarNetSales()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE);
                ProjectionVarianceDTO netSalesValue = getNetSales(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE);
                ProjectionVarianceDTO netSalesVar = getNetSales(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE);
                ProjectionVarianceDTO netSalesPer = getNetSales(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(netSalesPer);
            }
        }

        if (baseVariables.isVarCOGC()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE);
                ProjectionVarianceDTO netSalesValue = getCOGC(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE);
                ProjectionVarianceDTO netSalesVar = getCOGC(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE);
                ProjectionVarianceDTO netSalesPer = getCOGC(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        if (baseVariables.isVarNetProfit()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE);
                ProjectionVarianceDTO netSalesValue = getNetProfit(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE);
                ProjectionVarianceDTO netSalesVar = getNetProfit(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE);
                ProjectionVarianceDTO netSalesPer = getNetProfit(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        return projectionVarianceDTO;
    }

    /**
     * get Customized period variance
     *
     * @return List
     */
    public int getCustPeriodVarianceCount(final PVSelectionDTO baseVariables, final PVSelectionDTO pvsdto) {
        int count = 0;
        if (!pvsdto.getLevel().equals(DETAIL)) {
            count++;
        }
        // GTS
        if (baseVariables.isVarGTS()) {
            if (baseVariables.isColValue()) {
                count++;
            }
            if (baseVariables.isColVariance()) {
                count++;
            }
            if (baseVariables.isColPercentage()) {
                count++;
            }
        }
        //Percentage Of Business
        if (baseVariables.isVarPercentage()) {
            if (baseVariables.isColValue()) {
                count++;
            }
            if (baseVariables.isColVariance()) {
                count++;
            }
            if (baseVariables.isColPercentage()) {
                count++;
            }
        }
        //Contract Sales
        if (baseVariables.isVarContractsales()) {
            if (baseVariables.isColValue()) {
                count++;
            }
            if (baseVariables.isColVariance()) {
                count++;
            }
            if (baseVariables.isColPercentage()) {
                count++;
            }
        }
        //Contract Units
        if (baseVariables.isVarContractUnits()) {
            if (baseVariables.isColValue()) {
                count++;
            }
            if (baseVariables.isColVariance()) {
                count++;
            }
            if (baseVariables.isColPercentage()) {
                count++;
            }
        }
        //Discount $ 
        if (baseVariables.isVarDisAmount()) {
            if (baseVariables.isColValue()) {
                count++;
            }
            if (baseVariables.isColVariance()) {
                count++;
            }
            if (baseVariables.isColPercentage()) {
                count++;
            }
        }
        //Discount % 
        if (baseVariables.isVarDisRate()) {
            if (baseVariables.isColValue()) {
                count++;
            }
            if (baseVariables.isColVariance()) {
                count++;
            }
            if (baseVariables.isColPercentage()) {
                count++;
            }
        }
        //Discount % 
        if (baseVariables.isVarNetSales()) {
            if (baseVariables.isColValue()) {
                count++;
            }
            if (baseVariables.isColVariance()) {
                count++;
            }
            if (baseVariables.isColPercentage()) {
                count++;
            }
        }
        return count;
    }

    public List<ProjectionVarianceDTO> configureLevels(int start, int offset, PVSelectionDTO projSelDTO, int maxRecord) {
        int resultStart = start;
        List<ProjectionVarianceDTO> resultList = new ArrayList<ProjectionVarianceDTO>();
        if (offset > 0) {
            if (maxRecord == -1) {
                resultStart = start;
            } else {
                resultStart = (start <= maxRecord) ? 0 : start - maxRecord;
            }

            List<Leveldto> levelList = getConditionalLevelList(projSelDTO.getSession().getProjectionId(), resultStart, offset, projSelDTO.getHierarchyIndicator(), Integer.valueOf(projSelDTO.getTreeLevelNo()), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIslevelFiler(), false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO);
            for (int i = 0; i < levelList.size(); i++) {
                Leveldto levelDto = levelList.get(i);

                ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
                dto.setLevelNo(levelDto.getLevelNo());
                dto.setTreeLevelNo(levelDto.getTreeLevelNo());
                dto.setParentNode(levelDto.getParentNode());
                dto.setLevelValue(levelDto.getRelationshipLevelValue());
                dto.setHierarchyIndicator(levelDto.getHierarchyIndicator());
                dto.setHierarchyNo(levelDto.getHierarchyNo());
               // dto.setRelationshipLevelName(String.valueOf(DataSelectionForm.getRelationLevelValues().get(dto.getHierarchyNo())));
                dto.setRelationshipLevelName(projSelDTO.getSession().getLevelValueDiscription(String.valueOf(dto.getHierarchyNo()), String.valueOf(dto.getHierarchyIndicator()))); 
                dto.setGroup(StringUtils.isNotBlank(levelDto.getRelationshipLevelValue()) && !Constant.NULL.equals(levelDto.getRelationshipLevelValue())
                        ? levelDto.getRelationshipLevelValue() : dto.getRelationshipLevelName());
                if (dto.getHierarchyIndicator().equals(C)) {
                    dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                    dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                } else if (dto.getHierarchyIndicator().equals(P)) {
                    dto.setProductHierarchyNo(dto.getHierarchyNo());
                    dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
                }
                dto.setParent(1);
                resultList.add(dto);
            }
        }
        return resultList;
    }

    public static List<Leveldto> getConditionalLevelList(int projectionId, int start, int offset, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCustom, int customId, PVSelectionDTO projSelDTO) {
        List<Object> list = null;
        List<Leveldto> listValue = new ArrayList<Leveldto>();
        try {
            if (isCustom) {
                if (customId != 0) {
                    if (levelNo <= projSelDTO.getCustomCount()) {
                        String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, isExpand, false, start, offset, true, isCustom, customId, projSelDTO);
                        list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
                    }
                }
            } else {
                String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, isExpand, false, start, offset, true, isCustom, customId, projSelDTO);
                list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
            }
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    final Object[] obj = (Object[]) list1;
                    Leveldto dto = getCustomizedView(obj, false);
                    listValue.add(dto);
                }
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return listValue;
    }

    /**
     * Gets the Customized View
     *
     * @param obj
     * @param levelDef
     * @param hierarchyId
     * @param hierarchyIndicator
     * @return
     */
    public static Leveldto getCustomizedView(Object[] obj, boolean isHierarchy) {
        Leveldto dto = new Leveldto();
        dto.setLevelNo(Integer.valueOf(String.valueOf(obj[0])));
        dto.setTreeLevelNo(Integer.valueOf(String.valueOf(obj[1])));
        dto.setHierarchyIndicator(String.valueOf(obj[2]));
        dto.setLevel(String.valueOf(obj[3]));
        if (isHierarchy) {
            dto.setHierarchyId(Integer.valueOf(String.valueOf(obj[4])));
        } else {
            dto.setRelationshipLevelValue(String.valueOf(obj[4]));
            dto.setParentNode(String.valueOf(obj[5]));
            dto.setHierarchyNo(String.valueOf(obj[6]));
            dto.setRelationshipLevelName(String.valueOf(DataSelectionForm.getRelationLevelValues().get(dto.getHierarchyNo())));
        }
        return dto;
    }

    public String getCCPQuery(PVSelectionDTO projSelDTO, int projectionId, boolean pivotFlag) {
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String ccpQuery = " (SELECT LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from "
                + "(SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from "
                + "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID "
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId
                + "JOIN PROJECTION_MASTER PM  ON PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID"
                + " WHERE PM.PROJECTION_MASTER_SID=" + projectionId + ") CCPMAP,"
                + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1"
                + " JOIN " + viewtable + " PCH "
                + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID"
                + " AND PCH.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo() + "%' ) HLD"
                + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP"
                + " WHERE LCCP.HIERARCHY_NO in"
                + " (SELECT RLD2.HIERARCHY_NO"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2"
                + " JOIN " + viewtable + " PCH2"
                + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID"
                + " AND PCH2.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD2.LEVEL_NO=" + projSelDTO.getLevelNo() + ")) ";
        if (pivotFlag) {
            ccpQuery += "CCP ";
        }

        return ccpQuery;
    }

    private String getPeriodRestrictionQuery(int startYear, int endYear, int startFreq, int endFreq, int frequencyDivision, String year) {
        int startMonth = 1;
        int endMonth = 1;
        int startDay = 1;
        int endDay;

        if (frequencyDivision == 1) {
            startMonth = frequencyDivision;
        } else if (frequencyDivision == 2) {
            if (startFreq == 1) {
                startMonth = 1;
            } else if (startFreq == 2) {
                startMonth = 7;
            }
        } else if (frequencyDivision == 4) {
            if (startFreq == 1) {
                startMonth = 1;
            } else if (startFreq == 2) {
                startMonth = 4;
            } else if (startFreq == 3) {
                startMonth = 7;
            } else if (startFreq == 4) {
                startMonth = 10;
            }
        } else if (frequencyDivision == 12) {
            startMonth = startFreq;
        }

        if (frequencyDivision == 1) {
            endMonth = 12;
        } else if (frequencyDivision == 2) {
            if (endFreq == 1) {
                endMonth = 6;
            } else if (endFreq == 2) {
                endMonth = 12;
            }
        } else if (frequencyDivision == 4) {
            if (endFreq == 1) {
                endMonth = 3;
            } else if (endFreq == 2) {
                endMonth = 6;
            } else if (endFreq == 3) {
                endMonth = 9;
            } else if (endFreq == 4) {
                endMonth = 12;
            }
        } else if (frequencyDivision == 12) {
            endMonth = endFreq;
        }
        Calendar ob = Calendar.getInstance();
        ob.set(endYear, endMonth - 1, 1);
        int daysInMonth = ob.getActualMaximum(Calendar.DAY_OF_MONTH);
        endDay = daysInMonth;

        String endDate = String.format("%04d", endYear) + "-";
        endDate += String.format("%02d", endMonth) + "-";
        endDate += String.format("%02d", endDay);

        String startDate = String.format("%04d", startYear) + "-";
        startDate += String.format("%02d", startMonth) + "-";
        startDate += String.format("%02d", startDay);
        String periodFilter = StringUtils.EMPTY;
        if (!CommonUtils.isInteger(year)) {

            periodFilter = " and PERIOD_DATE BETWEEN CONVERT(DATE, '" + startDate + "') and CONVERT(DATE, '" + endDate + "') ";
        }

        return periodFilter;
    }

    public int getProgramCodeCount(PVSelectionDTO projSelDTO, ProjectionVarianceDTO projectionVarianceDTO, String parentName, String levelNo) throws PortalException, Exception {
        LOGGER.info(" inside getProgramCodeCount method--levelNo->" + levelNo);
        int count = 0;

        List<Object> list = discountProgramMap.get(projectionVarianceDTO.getHierarchyNo());
        if (StringUtils.isNotBlank(levelNo) && list == null) {
            list = getpcNames(projSelDTO, projectionVarianceDTO, parentName, levelNo, Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY, false, true);
            discountProgramMap.put(projectionVarianceDTO.getHierarchyNo(), list);
        }
        if (list != null && !list.isEmpty()) {
            count = list.size();
            periodPcNames = list;
        }
        LOGGER.info(" getProgramCodeCount method ends count" + count);
        return count;
    }

    public List<ProjectionVarianceDTO> getPivotResults(Object parent, PVSelectionDTO pvsdto, int start, int offset) {
        int neededRecord = offset;
        int started = start;
        int maxRecord = pvsdto.getPeriodList().size() + 1;
        ProjectionVarianceDTO parentDto = new ProjectionVarianceDTO();
        List<ProjectionVarianceDTO> projDTOList = new ArrayList<ProjectionVarianceDTO>();
        if (started < maxRecord) {
            if (pivotTotalList.isEmpty()) {
                getTotalPivotVariance(pvsdto);
            }
            if (Constant.TOTAL.equals(pvsdto.getLevel())) {
                if (pvsdto.getProjectionPeriodOrder().equals(Constant.DESCENDING)) {
                    Collections.reverse(pivotTotalList);
                }
                List<ProjectionVarianceDTO> finalList = getCustomizedPivotTotalResults(pivotTotalList, pivotPriorProjIdList, pvsdto, pvsdto);
                for (int i = started; (i < finalList.size()) && (neededRecord > 0); i++) {
                    projDTOList.add(finalList.get(i));
                    neededRecord--;
                }
                finalList = null;
            } else {
                if (parent instanceof ProjectionVarianceDTO) {
                    parentDto = (ProjectionVarianceDTO) parent;
                    List<ProjectionVarianceDTO> dto = getDetailsPivotVariance(pvsdto, parentDto);
                    for (int i = started; (i < dto.size()) && (neededRecord > 0); i++) {
                        projDTOList.add(dto.get(i));
                        neededRecord--;
                    }
                    dto = null;
                }
            }
        }
        pvsdto.setTreeLevelNo(pvsdto.getTreeLevelNo() + 1);
        pvsdto.setLevelNo(pvsdto.getTreeLevelNo() + 1);

        if (!selectionDTO.isIsCustomerDdlb() && !pvsdto.isIslevelFiler() && !pvsdto.isIsLevel() && neededRecord > 0) {
            List<ProjectionVarianceDTO> resultList = configureLevels(start, neededRecord, pvsdto, maxRecord);
            projDTOList.addAll(resultList);
            resultList = null;
        }

        return projDTOList;
    }

    public void getTotalPivotVariance(PVSelectionDTO pvsdto) {
        String frequency = pvsdto.getFrequency();
        List<String> projectionIdList = new ArrayList<String>();
        pivotTotalList = new ArrayList<Object>();
        pivotPriorProjIdList = new ArrayList<Integer>();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = "QUARTERLY";
        } else if (frequency.equals(Constant.SEMI_ANNUALLY)) {
            frequency = "SEMI-ANNUALLY";
        } else if (frequency.equals(Constant.MONTHLY)) {
            frequency = "MONTHLY";
        } else {
            frequency = "ANNUALLY";
        }
        projectionIdList.add(String.valueOf(pvsdto.getCurrentProjectionID()));
        for (Integer projId : new TreeSet<Integer>(pvsdto.getProjectionMap().keySet())) {
            projectionIdList.add(String.valueOf(projId));
            pivotPriorProjIdList.add(projId);
        }
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        Object[] orderedArg = {projectionId, pvsdto.getUserId(), pvsdto.getSessionId(), frequency, "PROJECTION VARIANCE", pvsdto.getStartPeriod(), pvsdto.getStartYear(), "PIVOT"};
        List< Object[]> gtsResult = CommonLogic.callProcedure("PRC_M_PROJECTION_RESULTS", orderedArg);
        pivotTotalList.addAll(gtsResult);
        gtsResult = null;

    }

    public List<ProjectionVarianceDTO> getDetailsPivotVariance(final PVSelectionDTO pvsdto, final ProjectionVarianceDTO parentDto) {
        try {
            List<List> discountLists = new ArrayList<List>();
            pvsdto.setYear("ALL");
            pvsdto.setProjectionId(pvsdto.getCurrentProjectionID());

            String query = getCCPTempTableQuery() + getCCPQuery(pvsdto, pvsdto.getProjectionId(), false) + CommonLogic.getTempCCPQueryForCOGS(pvsdto) + " \n" + getProjectionVarianceQuery(pvsdto);
            List<Object> currentPivotDetails = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
            List<Object> programCodequeryList = new ArrayList<Object>();
            if (Constant.DETAIL.equals(pvsdto.getLevel()) && Constant.COMPONENT.equals(pvsdto.getDiscountLevel()) && (pvsdto.isVarDisAmount() || pvsdto.isVarDisRate())) {
                String programCodequery = getCCPProgramCodeTempTableQuery(pvsdto) + getCCPQueryForProgramCode(pvsdto) + " \n" + getPivotProgramCodeQuery(pvsdto, true);
                if (!pvsdto.getProjIdList().isEmpty()) {
                    programCodequery += getPivotProgramCodeQuery(pvsdto, false);
                }
                programCodequery += getDeclarationQuery();
                programCodequeryList = (List<Object>) CommonLogic.executeSelectQuery(programCodequery, null, null);
            }
            List<Integer> priorProjIdList = new ArrayList<Integer>();
            for (Integer projId : pvsdto.getProjectionMap().keySet()) {
                priorProjIdList.add(projId);
            }
            List<String> listStr = new ArrayList<String>();
            List<ProjectionVarianceDTO> projDTOList = getCustomizedPivotDetailResults(pivotTotalList, currentPivotDetails, discountLists, listStr, parentDto, priorProjIdList, pvsdto, pvsdto, programCodequeryList);
            if (pvsdto.getProjectionPeriodOrder().equals(Constant.DESCENDING)) {
                Collections.reverse(projDTOList);
            }
            return projDTOList;
        } catch (Exception e) {

            LOGGER.error(e);
            return null;
        }
    }

    private static String getCCPTempTableQuery() {
        String tableQuery = "DECLARE @CCP TABLE\n"
                + "  (\n"
                + "     RELATIONSHIP_LEVEL_SID INT,\n"
                + "     CCP_DETAILS_SID        INT,\n"
                + "     HIERARCHY_NO           VARCHAR(50)\n"
                + "  ) \n"
                + " INSERT INTO @CCP\n"
                + "            (RELATIONSHIP_LEVEL_SID,CCP_DETAILS_SID,HIERARCHY_NO) \n";

        return tableQuery;
    }

    /**
     *
     * @param projSelDTO
     * @return
     */
    private String getProjectionVarianceQuery(PVSelectionDTO projSelDTO) {
        String query = CustomSQLUtil.get("projection-variance-select-periods");
        query += CustomSQLUtil.get("gov-projection-variance-select-columns-query");
        query = query.replace("@PROJ_NUM", "C0");
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            query += ", ";
            query += CustomSQLUtil.get("gov-projection-variance-select-columns-query");
            query = query.replace("@PROJ_NUM", Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i);
        }

        query += " FROM " + CustomSQLUtil.get("gov-current-projection-query");
        query = query.replace("@PROJECTION_MASTER_SID", String.valueOf(projSelDTO.getProjectionId()));
        query = query.replace("@PROJ_NUM", "D");
        query = query.replace("@USER_ID", String.valueOf(projSelDTO.getUserId()));
        query = query.replace("@SESSION_ID", String.valueOf(projSelDTO.getSessionId()));

        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            String projectionNumber = Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i;
            query += " LEFT JOIN \n" + CustomSQLUtil.get("gov-prior-projection-query") + projectionNumber + " \n";
            query = query.replace("@PROJECTION_MASTER_SID", String.valueOf(projSelDTO.getProjectionId()));
            query = query.replace("@PROJ_NUM", "D");
            query += " ON C0.YEARS = " + projectionNumber + ".YEARS AND C0.PERIODS = " + projectionNumber + ".PERIODS ";
        }
        if (projSelDTO.getFrequencyDivision() == 1) {
            query = query.replace(",E.@FREQUENCY", StringUtils.EMPTY);
        }
        Map<String, String> periodMap = getPeriods(projSelDTO.getStartYear(), projSelDTO.getEndYear(), projSelDTO.getStartPeriod(), projSelDTO.getEndPeriod(), projSelDTO.getFrequencyDivision(), "ALL");
        query = query.replace("@START_DATE", periodMap.get("startDate"));
        query = query.replace("@END_DATE", periodMap.get("endDate"));
        query = query.replace("E.@FREQUENCY", getFrequency(projSelDTO));
        query += " ORDER BY YEARS, PERIODS;";
        return query;
    }

    private Map<String, String> getPeriods(int startYear, int endYear, int startFreq, int endFreq, int frequencyDivision, String year) {
        int startMonth = 1;
        int endMonth = 1;
        int startDay = 1;
        int endDay;

        if (frequencyDivision == 1) {
            startMonth = frequencyDivision;
        } else if (frequencyDivision == 2) {
            if (startFreq == 1) {
                startMonth = 1;
            } else if (startFreq == 2) {
                startMonth = 7;
            }
        } else if (frequencyDivision == 4) {
            if (startFreq == 1) {
                startMonth = 1;
            } else if (startFreq == 2) {
                startMonth = 4;
            } else if (startFreq == 3) {
                startMonth = 7;
            } else if (startFreq == 4) {
                startMonth = 10;
            }
        } else if (frequencyDivision == 12) {
            startMonth = startFreq;
            if (startFreq == 0) {
                startMonth = 1;
            }
        }

        if (frequencyDivision == 1) {
            endMonth = 12;
        } else if (frequencyDivision == 2) {
            if (endFreq == 1) {
                endMonth = 6;
            } else if (endFreq == 2) {
                endMonth = 12;
            }
        } else if (frequencyDivision == 4) {
            if (endFreq == 1) {
                endMonth = 3;
            } else if (endFreq == 2) {
                endMonth = 6;
            } else if (endFreq == 3) {
                endMonth = 9;
            } else if (endFreq == 4) {
                endMonth = 12;
            }
        } else if (frequencyDivision == 12) {
            endMonth = endFreq;
        }
        Calendar ob = Calendar.getInstance();
        ob.set(endYear, endMonth - 1, 1);
        int daysInMonth = ob.getActualMaximum(Calendar.DAY_OF_MONTH);
        endDay = daysInMonth;

        String endDate = String.format("%04d", endYear) + "-";
        endDate += String.format("%02d", endMonth) + "-";
        endDate += String.format("%02d", endDay);

        String startDate = String.format("%04d", startYear) + "-";
        startDate += String.format("%02d", startMonth) + "-";
        startDate += String.format("%02d", startDay);
        Map<String, String> map = new HashMap();
        if (!CommonUtils.isInteger(year)) {
            map.put("startDate", startDate);
            map.put("endDate", endDate);
        }
        return map;
    }

    /**
     * Returns the frequency based on the frequency division value.
     *
     * @param selectionDTO
     * @return
     */
    private String getFrequency(PVSelectionDTO selectionDTO) {
        String frequency;
        switch (selectionDTO.getFrequencyDivision()) {
            case 1:
                frequency = Constant.DASH;
                break;
            case 2:
                frequency = "SEMI_ANNUAL";
                break;
            case 4:
                frequency = "QUARTER";
                break;
            case 12:
                frequency = "MONTH";
                break;
            default:
                frequency = "QUARTER";
                break;
        }
        return frequency;
    }

    private String getCCPWhereConditionQuery(String relationShipLevelDefination, String projectionDetails, String CCP, String priorOrCurrent) {
        String ccpWhereCond = StringUtils.EMPTY;
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(priorOrCurrent)) {
            ccpWhereCond += " and " + relationShipLevelDefination + ".RELATIONSHIP_LEVEL_SID =" + CCP + ".RELATIONSHIP_LEVEL_SID ";
        }
        ccpWhereCond += "and " + CCP + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID ";
        return ccpWhereCond;
    }

    public List<ProjectionVarianceDTO> getCustomizedPivotDetailResults(final List gtsList, final List results, List<List> sad, List<String> discountName, ProjectionVarianceDTO parentDto, List<Integer> priorProjGtsList, PVSelectionDTO pvsdto, PVSelectionDTO baseVariables, final List programCode) {
        List<String> periodList = new ArrayList<String>(pvsdto.getPeriodList());
        Map<String, String> periodListMap = new HashMap<String, String>(pvsdto.getPeriodListMap());
        List<ProjectionVarianceDTO> projDTOList = new ArrayList<ProjectionVarianceDTO>();
        boolean isGts = false;
        int frequencyDivision = pvsdto.getFrequencyDivision();
        List<String> strList = pvsdto.getColumns();
        if (results != null && !results.isEmpty()) {
            for (int i = 0; i < results.size(); i++) {
                final Object[] row = (Object[]) results.get(i);
                final Object[] gtsRow = gtsList.size() > 0 ? (Object[]) gtsList.get(i) : new Object[0];
                int year = Integer.valueOf(String.valueOf(row[0]));
                int period = Integer.valueOf(String.valueOf(row[1]));
                List<String> common = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, year, period);
                String pcommonColumn = common.get(0);
                String commonHeader = common.get(1);
                String column = StringUtils.EMPTY;
                if (gtsList.size() >= results.size() && gtsRow.length >= row.length) {
                    isGts = true;
                }
                if (periodList.contains(pcommonColumn)) {
                    periodList.remove(pcommonColumn);
                    List<String> columnList = new ArrayList<String>(pvsdto.getColumns());
                    columnList.remove(Constant.GROUP);
                    ProjectionVarianceDTO projDTO = new ProjectionVarianceDTO();
                    projDTO.setGroup(commonHeader);
                    projDTO.setRelationshipLevelName(commonHeader);
                    if (baseVariables.isVarExFacSales()) {
                        if (baseVariables.isColValue()) {
                            column = "ExFacValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentGts = NULL;
                            if (isGts) {
                                currentGts = String.valueOf(gtsRow[2]);
                            }
                            String baseValue = getFormattedValue(AMOUNT, currentGts);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarDemandSales()) {
                        if (baseVariables.isColValue()) {
                            column = "DemandSalesValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentPob = NULL;
                            if (isGts) {
                                currentPob = String.valueOf(gtsRow[3]);
                            }
                            String baseValue = getFormattedValue(AMOUNT, currentPob);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarInvSales()) {
                        if (baseVariables.isColValue()) {
                            column = "InvWithValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentPob = NULL;
                            if (isGts) {
                                currentPob = String.valueOf(gtsRow[4]);
                            }
                            String baseValue = getFormattedValue(AMOUNT, currentPob);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarPerExFacSales()) {
                        if (baseVariables.isColValue()) {
                            column = "PerExFacValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentPob = NULL;
                            if (isGts) {
                                currentPob = String.valueOf(gtsRow[5]);
                            }
                            String baseValue = getFormattedValue(RATE, currentPob);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarPerDemandSales()) {
                        if (baseVariables.isColValue()) {
                            column = "PerDemandSalesValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentPob = NULL;
                            if (isGts) {
                                currentPob = String.valueOf(gtsRow[6]);
                            }
                            String baseValue = getFormattedValue(RATE, currentPob);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarPerInvSales()) {
                        if (baseVariables.isColValue()) {
                            column = "PerInvWithValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentPob = NULL;
                            if (isGts) {
                                currentPob = String.valueOf(gtsRow[7]);
                            }
                            String baseValue = getFormattedValue(RATE, currentPob);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }

                    if (baseVariables.isVarContractsales()) {
                        if (baseVariables.isColValue()) {
                            column = "ContractSalesWACValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSales = StringUtils.EMPTY + row[2];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarContractUnits()) {
                        if (baseVariables.isColValue()) {
                            column = "ContractUnitsValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSales = StringUtils.EMPTY + row[3];
                            String baseValue = getFormattedValue(AMOUNT_UNITS, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarDisRate()) {
                        if (baseVariables.isColValue()) {
                            column = "DiscountSalesValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSales = StringUtils.EMPTY + row[4];
                            String baseValue = getFormattedValue(RATE_PER, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                            if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {
                                column = "mandatedDiscountSalesValue" + CURRENT + pvsdto.getCurrentProjectionID();
                                String currentMSales = StringUtils.EMPTY + row[8];
                                String baseValueForMand = getFormattedValue(RATE_PER, currentMSales);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValueForMand + PERCENT);
                                    columnList.remove(column);
                                }

                                column = "supplementalDiscountSalesValue" + CURRENT + pvsdto.getCurrentProjectionID();
                                String currentSuppSales = StringUtils.EMPTY + row[10];
                                String baseValueForSupp = getFormattedValue(RATE_PER, currentSuppSales);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValueForSupp + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                        }
                    }
                    if (baseVariables.isVarDisAmount()) {
                        if (baseVariables.isColValue()) {
                            column = "DiscountAmountValue" + CURRENT + pvsdto.getCurrentProjectionID();

                            String currentSales = StringUtils.EMPTY + row[5];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }

                            if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {
                                column = "mandatedDiscountAmountValue" + CURRENT + pvsdto.getCurrentProjectionID();
                                String currentMSales = StringUtils.EMPTY + row[7];
                                String baseValueForMand = getFormattedValue(AMOUNT, currentMSales);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValueForMand);
                                    columnList.remove(column);
                                }

                                column = "supplementalDiscountAmountValue" + CURRENT + pvsdto.getCurrentProjectionID();
                                String currentSuppSales = StringUtils.EMPTY + row[9];
                                String baseValueForSupp = getFormattedValue(AMOUNT, currentSuppSales);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValueForSupp);
                                    columnList.remove(column);
                                }
                            }
                        }
                    }
                    if (baseVariables.isVarRPU()) {
                        if (baseVariables.isColValue()) {
                            column = "RPUValue" + CURRENT + pvsdto.getCurrentProjectionID();

                            String currentSales = StringUtils.EMPTY + row[11];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }

                            if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {
                                column = "mandatedDiscountRPUValue" + CURRENT + pvsdto.getCurrentProjectionID();
                                String currentMSales = StringUtils.EMPTY + row[12];
                                String baseValueForMand = getFormattedValue(AMOUNT, currentMSales);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValueForMand);
                                    columnList.remove(column);
                                }

                                column = "supplementalRPUValue" + CURRENT + pvsdto.getCurrentProjectionID();
                                String currentSuppSales = StringUtils.EMPTY + row[13];
                                String baseValueForSupp = getFormattedValue(AMOUNT, currentSuppSales);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValueForSupp);
                                    columnList.remove(column);
                                }
                            }
                        }
                    }
                    if (baseVariables.isVarNetSales()) {
                        if (baseVariables.isColValue()) {
                            column = "NetSalesValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSales = StringUtils.EMPTY + row[6];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarCOGC()) {
                        if (baseVariables.isColValue()) {
                            column = "COGCValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSales = StringUtils.EMPTY + row[14];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarNetProfit()) {
                        if (baseVariables.isColValue()) {
                            column = "NetProfitValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSales = StringUtils.EMPTY + row[15];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }

                    if (programCode != null && !programCode.isEmpty() && i<programCode.size()) {
                        List<String> programCodeName = pvsdto.getProgramCodeNameList();
                        int codeCount = 2;
                        Object[] programCodeNameRow = (Object[]) programCode.get(i);
                        int programCodeNameRowSize = programCodeNameRow.length;
                        int programCodeNameSize = (programCodeNameRowSize - 2) / ((pvsdto.getProjIdList().size() + 1) * 6);
                        // To find number of program codes returned from db
                        for (int j = 0; j < programCodeNameSize; j++) {
                            Object[] programCodeRow = (Object[]) programCode.get(i);
                            column = "mdProgramCodeAmountValue" + programCodeName.get(j) + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentPC = StringUtils.EMPTY + programCodeRow[codeCount++];
                            String baseValueForMandPC = getFormattedValue(AMOUNT, currentPC);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValueForMandPC);
                                columnList.remove(column);
                            }

                            column = "mdProgramCodeSalesValue" + programCodeName.get(j) + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSalesPC = StringUtils.EMPTY + programCodeRow[codeCount++];
                            String baseValueForMandSalesPC = getFormattedValue(RATE_PER, currentSalesPC);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValueForMandSalesPC + PERCENT);
                                columnList.remove(column);
                            }

                            column = "sdProgramCodeAmountValue" + programCodeName.get(j) + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSuppPC = StringUtils.EMPTY + programCodeRow[codeCount++];
                            String baseValueForSuppPC = getFormattedValue(AMOUNT, currentSuppPC);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValueForSuppPC);
                                columnList.remove(column);
                            }

                            column = "sdProgramCodeSalesValue" + programCodeName.get(j) + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSuppSalesPC = StringUtils.EMPTY + programCodeRow[codeCount++];
                            String baseValueForSuppSalesPC = getFormattedValue(RATE, currentSuppSalesPC);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValueForSuppSalesPC + PERCENT);
                                columnList.remove(column);
                            }

                            column = "mdProgramCodeRPUValue" + programCodeName.get(j) + CURRENT + pvsdto.getCurrentProjectionID();
                            String mdProgramCodeRPUValue = StringUtils.EMPTY + programCodeRow[codeCount++];
                            String baseValueForMandRPU = getFormattedValue(AMOUNT, mdProgramCodeRPUValue);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValueForMandRPU);
                                columnList.remove(column);
                            }

                            column = "sdProgramCodeRPUValue" + programCodeName.get(j) + CURRENT + pvsdto.getCurrentProjectionID();
                            String sdProgramCodeRPUValue = StringUtils.EMPTY + programCodeRow[codeCount++];
                            String baseValueForSuppSalesRPU = getFormattedValue(AMOUNT, sdProgramCodeRPUValue);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValueForSuppSalesRPU);
                                columnList.remove(column);
                            }

                        }

                        for (int j = 0; j < priorProjGtsList.size(); j++) {
                            Object[] programCodeRow = (Object[]) programCode.get(i);
                            int programCodeRowSize = programCodeRow.length;

                            int sdProgramCodeSalesCount = 0;
                            int sdProgramCodeAmountper = 0;
                            int mdProgramCodeSalesCount = 0;
                            int mdProgramCodeAmountCount = 0;
                            int mdProgramCodeRPUCount = 0;
                            int sdProgramCodeRPUCount = 0;

                            int programCodeSize = (programCodeRowSize - 2) / ((pvsdto.getProjIdList().size() + 1) * 6);
// To find number of program codes returned from db

                            for (int z = 0; z < programCodeSize; z++) {

                                if (z == 0) {
                                    sdProgramCodeSalesCount = (5 * (z + 1)) + ((j + 1) * (programCodeSize * 6));
                                    //(5 * (z + 1)) + ((j + 1) * (count * 4)); int count = pvsdto.getProgramCodeNameList().size();
                                    mdProgramCodeSalesCount = (3 * (z + 1)) + ((j + 1) * (programCodeSize * 6));
                                    mdProgramCodeAmountCount = (2 * (z + 1)) + ((j + 1) * (programCodeSize * 6));
                                    sdProgramCodeAmountper = (4 * (z + 1)) + ((j + 1) * (programCodeSize * 6));
                                    mdProgramCodeRPUCount = (6 * (z + 1)) + ((j + 1) * (programCodeSize * 6));
                                    sdProgramCodeRPUCount = (7 * (z + 1)) + ((j + 1) * (programCodeSize * 6));
                                } else {
                                    sdProgramCodeSalesCount = sdProgramCodeSalesCount + 6;
                                    mdProgramCodeSalesCount = mdProgramCodeSalesCount + 6;
                                    mdProgramCodeAmountCount = mdProgramCodeAmountCount + 6;
                                    sdProgramCodeAmountper = sdProgramCodeAmountper + 6;
                                    mdProgramCodeRPUCount = mdProgramCodeRPUCount + 6;
                                    sdProgramCodeRPUCount = sdProgramCodeRPUCount + 6;
                                }

                                if (baseVariables.isVarDisRate()) {

                                    if (baseVariables.isColValue()) {
                                        String value1 = StringUtils.EMPTY + programCodeRow[mdProgramCodeSalesCount];
                                        column = "mdProgramCodeSalesValue" + programCodeName.get(z) + priorProjGtsList.get(j);
                                        String baseValue = getFormattedValue(RATE_PER, value1);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue + PERCENT);
                                            columnList.remove(column);
                                        }
                                        String value2 = StringUtils.EMPTY + programCodeRow[sdProgramCodeSalesCount];

                                        column = "sdProgramCodeSalesValue" + programCodeName.get(z) + priorProjGtsList.get(j);
                                        String baseValue1 = getFormattedValue(RATE_PER, value2);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue1 + PERCENT);
                                            columnList.remove(column);
                                        }

                                    }

                                    if (baseVariables.isColVariance()) {
                                        column = "mdProgramCodeSalesVar" + programCodeName.get(z) + priorProjGtsList.get(j);
                                        String priorVal = StringUtils.EMPTY + programCodeRow[mdProgramCodeSalesCount];
                                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + programCodeRow[(3 * (z + 1))])) - Double.valueOf(isNull(priorVal)));
                                        String baseValue = getFormattedValue(RATE_PER, variance);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue + PERCENT);
                                            columnList.remove(column);
                                        }

                                        column = "sdProgramCodeSalesVar" + programCodeName.get(z) + priorProjGtsList.get(j);
                                        String priorVal1 = StringUtils.EMPTY + programCodeRow[sdProgramCodeSalesCount];
                                        String variance1 = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + programCodeRow[(5 * (z + 1))])) - Double.valueOf(isNull(priorVal1)));
                                        String baseValue1 = getFormattedValue(RATE_PER, variance1);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue1 + PERCENT);
                                            columnList.remove(column);
                                        }

                                    }
                                    if (baseVariables.isColPercentage()) {
                                        column = "mdProgramCodeSalesPer" + programCodeName.get(z) + priorProjGtsList.get(j);
                                        String priorVal = StringUtils.EMPTY + programCodeRow[mdProgramCodeSalesCount];
                                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + programCodeRow[(3 * (z + 1))])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                            perChange = 0.0;
                                        }
                                        String change = String.valueOf(perChange);
                                        String baseValue = getFormattedValue(RATE_PER, change);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue + PERCENT);
                                            columnList.remove(column);
                                        }

                                        column = "sdProgramCodeSalesPer" + programCodeName.get(z) + priorProjGtsList.get(j);
                                        String priorVal1 = StringUtils.EMPTY + programCodeRow[sdProgramCodeSalesCount];
                                        Double perChange1 = (Double.valueOf(isNull(StringUtils.EMPTY + programCodeRow[(5 * (z + 1))])) - Double.valueOf(isNull(priorVal1))) / Double.valueOf(isNull(priorVal1));
                                        if (perChange1.isNaN() || perChange1.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange1))) {
                                            perChange1 = 0.0;
                                        }
                                        String change1 = String.valueOf(perChange1);
                                        String baseValue1 = getFormattedValue(RATE_PER, change1);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue1 + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                                }

                                if (baseVariables.isVarDisAmount()) {

                                    if (baseVariables.isColValue()) {
                                        String value1 = StringUtils.EMPTY + programCodeRow[mdProgramCodeAmountCount];
                                        column = "mdProgramCodeAmountValue" + programCodeName.get(z) + priorProjGtsList.get(j);;
                                        String baseValue = getFormattedValue(AMOUNT, value1);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue);
                                            columnList.remove(column);
                                        }

                                        String value2 = StringUtils.EMPTY + programCodeRow[sdProgramCodeAmountper];
                                        column = "sdProgramCodeAmountValue" + programCodeName.get(z) + priorProjGtsList.get(j);;
                                        String baseValue2 = getFormattedValue(AMOUNT, value2);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue2);
                                            columnList.remove(column);
                                        }
                                    }
                                    if (baseVariables.isColVariance()) {
                                        column = "mdProgramCodeAmountVar" + programCodeName.get(z) + priorProjGtsList.get(j);
                                        String priorVal = StringUtils.EMPTY + programCodeRow[mdProgramCodeAmountCount];
                                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + programCodeRow[(2 * (z + 1))])) - Double.valueOf(isNull(priorVal)));
                                        String baseValue = getFormattedValue(AMOUNT, variance);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue);
                                            columnList.remove(column);
                                        }

                                        column = "sdProgramCodeAmountVar" + programCodeName.get(z) + priorProjGtsList.get(j);
                                        String priorVal1 = StringUtils.EMPTY + programCodeRow[sdProgramCodeAmountper];
                                        String variance1 = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + programCodeRow[(4 * (z + 1))])) - Double.valueOf(isNull(priorVal1)));
                                        String baseValue1 = getFormattedValue(AMOUNT, variance1);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue1);
                                            columnList.remove(column);
                                        }
                                    }
                                    if (baseVariables.isColPercentage()) {
                                        column = "mdProgramCodeAmountper" + programCodeName.get(z) + priorProjGtsList.get(j);
                                        String priorVal = StringUtils.EMPTY + programCodeRow[mdProgramCodeAmountCount];
                                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + programCodeRow[(2 * (z + 1))])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                            perChange = 0.0;
                                        }
                                        String change = String.valueOf(perChange);
                                        String baseValue = getFormattedValue(RATE_PER, change);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue + PERCENT);
                                            columnList.remove(column);
                                        }

                                        column = "sdProgramCodeAmountper" + programCodeName.get(z) + priorProjGtsList.get(j);
                                        String priorVal1 = StringUtils.EMPTY + programCodeRow[sdProgramCodeAmountper];
                                        Double perChange1 = (Double.valueOf(isNull(StringUtils.EMPTY + programCodeRow[(2 * (z + 1))])) - Double.valueOf(isNull(priorVal1))) / Double.valueOf(isNull(priorVal1));
                                        if (perChange1.isNaN() || perChange1.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange1))) {
                                            perChange1 = 0.0;
                                        }
                                        String change1 = String.valueOf(perChange1);
                                        String baseValue1 = getFormattedValue(RATE_PER, change1);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue1 + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                                }
                                if (baseVariables.isVarRPU()) {

                                    if (baseVariables.isColValue()) {
                                        String value1 = StringUtils.EMPTY + programCodeRow[mdProgramCodeAmountCount];
                                        column = "mdProgramCodeRPUValue" + programCodeName.get(z) + priorProjGtsList.get(j);;
                                        String baseValue = getFormattedValue(AMOUNT, value1);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue);
                                            columnList.remove(column);
                                        }

                                        String value2 = StringUtils.EMPTY + programCodeRow[sdProgramCodeAmountper];
                                        column = "sdProgramCodeRPUValue" + programCodeName.get(z) + priorProjGtsList.get(j);;
                                        String baseValue2 = getFormattedValue(AMOUNT, value2);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue2);
                                            columnList.remove(column);
                                        }
                                    }
                                    if (baseVariables.isColVariance()) {
                                        column = "mdProgramCodeRPUVar" + programCodeName.get(z) + priorProjGtsList.get(j);
                                        String priorVal = StringUtils.EMPTY + programCodeRow[mdProgramCodeAmountCount];
                                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + programCodeRow[(6 * (z + 1))])) - Double.valueOf(isNull(priorVal)));
                                        String baseValue = getFormattedValue(AMOUNT, variance);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue);
                                            columnList.remove(column);
                                        }

                                        column = "sdProgramCodeRPUVar" + programCodeName.get(z) + priorProjGtsList.get(j);
                                        String priorVal1 = StringUtils.EMPTY + programCodeRow[sdProgramCodeAmountper];
                                        String variance1 = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + programCodeRow[(7 * (z + 1))])) - Double.valueOf(isNull(priorVal1)));
                                        String baseValue1 = getFormattedValue(AMOUNT, variance1);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue1);
                                            columnList.remove(column);
                                        }
                                    }
                                    if (baseVariables.isColPercentage()) {
                                        column = "mdProgramCodeRPUPer" + programCodeName.get(z) + priorProjGtsList.get(j);
                                        String priorVal = StringUtils.EMPTY + programCodeRow[mdProgramCodeAmountCount];
                                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + programCodeRow[(6 * (z + 1))])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                            perChange = 0.0;
                                        }
                                        String change = String.valueOf(perChange);
                                        String baseValue = getFormattedValue(RATE_PER, change);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue + PERCENT);
                                            columnList.remove(column);
                                        }

                                        column = "sdProgramCodeRPUPer" + programCodeName.get(z) + priorProjGtsList.get(j);
                                        String priorVal1 = StringUtils.EMPTY + programCodeRow[sdProgramCodeAmountper];
                                        Double perChange1 = (Double.valueOf(isNull(StringUtils.EMPTY + programCodeRow[(7 * (z + 1))])) - Double.valueOf(isNull(priorVal1))) / Double.valueOf(isNull(priorVal1));
                                        if (perChange1.isNaN() || perChange1.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange1))) {
                                            perChange1 = 0.0;
                                        }
                                        String change1 = String.valueOf(perChange1);
                                        String baseValue1 = getFormattedValue(RATE_PER, change1);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue1 + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                                }

                            }
                        }

                    }

                    for (int j = 0; j < priorProjGtsList.size(); j++) {
                        //Ex Factory Sales
                        if (baseVariables.isVarExFacSales()) {
                            String value1 = NULL;
                            if (isGts) {
                                value1 = String.valueOf(gtsRow[2 + ((j + 1) * 20)]);
                            }
                            if (baseVariables.isColValue()) {
                                column = "ExFacValue" + priorProjGtsList.get(j);

                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "ExFacVariance" + priorProjGtsList.get(j);
                                String priorVal = NULL;
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[2 + ((j + 1) * 20)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[2])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "ExFacPer" + priorProjGtsList.get(j);
                                String priorVal = NULL;
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[2 + ((j + 1) * 20)]);
                                }
                                Double perChange = Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[2])) / (Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[2])) - Double.valueOf(isNull(priorVal)));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue+ PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //Demand Sales
                        if (baseVariables.isVarDemandSales()) {
                            String value1 = NULL;
                            if (isGts) {
                                value1 = String.valueOf(gtsRow[3 + ((j + 1) * 20)]);
                            }
                            if (baseVariables.isColValue()) {
                                column = "DemandSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "DemandSalesVariance" + priorProjGtsList.get(j);
                                String priorVal = NULL;
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[3 + ((j + 1) * 20)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[3])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "DemandSalesPer" + priorProjGtsList.get(j);
                                String priorVal = NULL;
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[3 + ((j + 1) * 20)]);
                                }
                                Double perChange = Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[3])) / (Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[3])) - Double.valueOf(isNull(priorVal)));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //Inventory
                        if (baseVariables.isVarInvSales()) {
                            String value1 = NULL;
                            if (isGts) {
                                value1 = String.valueOf(gtsRow[4 + ((j + 1) * 20)]);
                            }
                            if (baseVariables.isColValue()) {
                                column = "InvWithValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "InvWithVariance" + priorProjGtsList.get(j);
                                String priorVal = NULL;
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[4 + ((j + 1) * 20)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[4])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "InvWithPer" + priorProjGtsList.get(j);
                                String priorVal = NULL;
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[4 + ((j + 1) * 20)]);
                                }
                                Double perChange = Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[4])) / (Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[4])) - Double.valueOf(isNull(priorVal)));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        // % of Ex Factory Sales
                        if (baseVariables.isVarPerExFacSales()) {
                            String value1 = NULL;
                            if (isGts) {
                                value1 = String.valueOf(gtsRow[5 + ((j + 1) * 20)]);
                            }
                            if (baseVariables.isColValue()) {
                                column = "PerExFacValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "PerExFacVariance" + priorProjGtsList.get(j);
                                String priorVal = NULL;
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[5 + ((j + 1) * 20)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[5])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "PerExFacPer" + priorProjGtsList.get(j);
                                String priorVal = NULL;
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[5 + ((j + 1) * 20)]);
                                }
                                Double perChange = Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[5])) / (Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[5])) - Double.valueOf(isNull(priorVal)));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        // % of Demand Sales
                        if (baseVariables.isVarPerDemandSales()) {
                            String value1 = NULL;
                            if (isGts) {
                                value1 = String.valueOf(gtsRow[6 + ((j + 1) * 20)]);
                            }
                            if (baseVariables.isColValue()) {
                                column = "PerDemandSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "PerDemandSalesVariance" + priorProjGtsList.get(j);
                                String priorVal = NULL;
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[6 + ((j + 1) * 20)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[6])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "PerDemandSalesPer" + priorProjGtsList.get(j);
                                String priorVal = NULL;
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[6 + ((j + 1) * 20)]);
                                }
                                Double perChange = Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[6])) / (Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[6])) - Double.valueOf(isNull(priorVal)));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //% of Inventory
                        if (baseVariables.isVarPerInvSales()) {
                            String value1 = NULL;
                            if (isGts) {
                                value1 = String.valueOf(gtsRow[7 + ((j + 1) * 20)]);
                            }
                            if (baseVariables.isColValue()) {
                                column = "PerInvWithValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "PerInvWithVariance" + priorProjGtsList.get(j);
                                String priorVal = NULL;
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[7 + ((j + 1) * 20)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[7])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "PerInvWithPer" + priorProjGtsList.get(j);
                                String priorVal = NULL;
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[7 + ((j + 1) * 20)]);
                                }
                                Double perChange = Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[7])) / (Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[7])) - Double.valueOf(isNull(priorVal)));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //Contract Sales
                        if (baseVariables.isVarContractsales()) {
                            String priorValue = StringUtils.EMPTY + row[2 + ((j + 1) * 14)];
                            if (baseVariables.isColValue()) {
                                column = "ContractSalesWACValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "ContractSalesWACVar" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[2 + ((j + 1) * 14)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[2])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "ContractSalesWACVarPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[2 + ((j + 1) * 14)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[2])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }

                        //Contract Units
                        if (baseVariables.isVarContractUnits()) {
                            String value1 = StringUtils.EMPTY + row[3 + ((j + 1) * 14)];
                            if (baseVariables.isColValue()) {
                                column = "ContractUnitsValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT_UNITS, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "ContractUnitsVar" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[3 + ((j + 1) * 14)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[3])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT_UNITS, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColPercentage()) {
                                column = "ContractUnitsPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[3 + ((j + 1) * 14)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[3])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }

                        //Discount Rate
                        if (baseVariables.isVarDisRate()) {
                            String value1 = StringUtils.EMPTY + row[4 + ((j + 1) * 14)];
                            if (baseVariables.isColValue()) {
                                column = "DiscountSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                                if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {
                                    String valueM1 = StringUtils.EMPTY + row[8 + ((j + 1) * 14)];
                                    column = "mandatedDiscountSalesValue" + priorProjGtsList.get(j);

                                    String baseMValue = getFormattedValue(RATE_PER, valueM1);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseMValue + PERCENT);
                                        columnList.remove(column);
                                    }

                                    String valueS1 = StringUtils.EMPTY + row[10 + ((j + 1) * 14)];
                                    column = "supplementalDiscountSalesValue" + priorProjGtsList.get(j);

                                    String baseSValue = getFormattedValue(RATE_PER, valueS1);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseSValue + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "DiscountSalesVar" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[4 + ((j + 1) * 14)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[4])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE_PER, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                                if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {
                                    String valueM1 = StringUtils.EMPTY + row[8 + ((j + 1) * 14)];
                                    column = "mandatedDiscountSalesVar" + priorProjGtsList.get(j);
                                    String mVariance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[8])) - Double.valueOf(isNull(valueM1)));
                                    String baseMValue = getFormattedValue(RATE_PER, mVariance);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseMValue + PERCENT);
                                        columnList.remove(column);
                                    }

                                    String valueS1 = StringUtils.EMPTY + row[10 + ((j + 1) * 14)];
                                    column = "supplementalDiscountSalesVar" + priorProjGtsList.get(j);
                                    String sVariance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[10])) - Double.valueOf(isNull(valueS1)));
                                    String baseSValue = getFormattedValue(RATE_PER, sVariance);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseSValue + PERCENT);
                                        columnList.remove(column);
                                    }
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "DiscountSalesPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[4 + ((j + 1) * 14)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[4])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE_PER, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                                if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {

                                    column = "mandatedDiscountSalesPer" + priorProjGtsList.get(j);
                                    String priorMVal = StringUtils.EMPTY + row[8 + ((j + 1) * 14)];
                                    Double perMChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[8])) - Double.valueOf(isNull(priorMVal))) / Double.valueOf(isNull(priorMVal));
                                    if (perMChange.isNaN() || perMChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perMChange))) {
                                        perMChange = 0.0;
                                    }
                                    String mChange = String.valueOf(perMChange);
                                    String baseMValue = getFormattedValue(RATE_PER, mChange);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseMValue + PERCENT);
                                        columnList.remove(column);
                                    }

                                    column = "supplementalDiscountSalesPer" + priorProjGtsList.get(j);
                                    String priorSVal = StringUtils.EMPTY + row[10 + ((j + 1) * 14)];
                                    Double perSChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[10])) - Double.valueOf(isNull(priorSVal))) / Double.valueOf(isNull(priorSVal));
                                    if (perSChange.isNaN() || perSChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perSChange))) {
                                        perSChange = 0.0;
                                    }
                                    String sChange = String.valueOf(perSChange);
                                    String baseSValue = getFormattedValue(RATE_PER, sChange);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseSValue + PERCENT);
                                        columnList.remove(column);
                                    }
                                }

                            }

                        }
                        //Discount Amount
                        if (baseVariables.isVarDisAmount()) {
                            String value1 = StringUtils.EMPTY + row[5 + ((j + 1) * 14)];
                            if (baseVariables.isColValue()) {
                                column = "DiscountAmountValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                                if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {
                                    String valueM1 = StringUtils.EMPTY + row[7 + ((j + 1) * 14)];
                                    column = "mandatedDiscountAmountValue" + priorProjGtsList.get(j);

                                    String baseMValue = getFormattedValue(AMOUNT, valueM1);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseMValue);
                                        columnList.remove(column);
                                    }

                                    String valueS1 = StringUtils.EMPTY + row[9 + ((j + 1) * 14)];
                                    column = "supplementalDiscountAmountValue" + priorProjGtsList.get(j);

                                    String baseSValue = getFormattedValue(AMOUNT, valueS1);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseSValue);
                                        columnList.remove(column);
                                    }
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "DiscountAmountVar" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[5 + ((j + 1) * 14)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[5])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                                if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {
                                    column = "mandatedDiscountAmountVar" + priorProjGtsList.get(j);
                                    String priorMVal = StringUtils.EMPTY + row[7 + ((j + 1) * 14)];
                                    String mVariance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[7])) - Double.valueOf(isNull(priorMVal)));
                                    String baseMValue = getFormattedValue(AMOUNT, mVariance);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseMValue);
                                        columnList.remove(column);
                                    }

                                    column = "supplementalDiscountAmountVar" + priorProjGtsList.get(j);
                                    String priorSVal = StringUtils.EMPTY + row[9 + ((j + 1) * 14)];
                                    String sVariance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[9])) - Double.valueOf(isNull(priorSVal)));
                                    String baseSValue = getFormattedValue(AMOUNT, sVariance);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseSValue);
                                        columnList.remove(column);
                                    }
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "DiscountAmountPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[5 + ((j + 1) * 14)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[5])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE_PER, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                                if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {
                                    column = "mandatedDiscountAmountPer" + priorProjGtsList.get(j);
                                    String priorMVal = StringUtils.EMPTY + row[7 + ((j + 1) * 14)];
                                    Double perMChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[7])) - Double.valueOf(isNull(priorMVal))) / Double.valueOf(isNull(priorMVal));
                                    if (perMChange.isNaN() || perMChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perMChange))) {
                                        perMChange = 0.0;
                                    }
                                    String mChange = String.valueOf(perMChange);
                                    String baseMValue = getFormattedValue(RATE_PER, mChange);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseMValue + PERCENT);
                                        columnList.remove(column);
                                    }

                                    column = "supplementalDiscountAmountPer" + priorProjGtsList.get(j);
                                    String priorSVal = StringUtils.EMPTY + row[9 + ((j + 1) * 14)];
                                    Double perSChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[9])) - Double.valueOf(isNull(priorSVal))) / Double.valueOf(isNull(priorSVal));
                                    if (perSChange.isNaN() || perSChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perSChange))) {
                                        perSChange = 0.0;
                                    }
                                    String SChange = String.valueOf(perSChange);
                                    String baseSValue = getFormattedValue(RATE_PER, SChange);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseSValue + PERCENT);
                                        columnList.remove(column);
                                    }

                                }
                            }
                        }
                        //RPU
                        if (baseVariables.isVarRPU()) {

                            String value1 = StringUtils.EMPTY + row[11 + ((j + 1) * 14)];
                            if (baseVariables.isColValue()) {
                                column = "PRUValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                                if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {
                                    String valueM1 = StringUtils.EMPTY + row[12 + ((j + 1) * 14)];
                                    column = "mandatedDiscountRPUValue" + priorProjGtsList.get(j);

                                    String baseMValue = getFormattedValue(AMOUNT, valueM1);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseMValue);
                                        columnList.remove(column);
                                    }

                                    String valueS1 = StringUtils.EMPTY + row[13 + ((j + 1) * 14)];
                                    column = "supplementalRPUValue" + priorProjGtsList.get(j);

                                    String baseSValue = getFormattedValue(RATE_PER, valueS1);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseSValue + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "PRUVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[11 + ((j + 1) * 14)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[11])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                                if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {
                                    String valueM1 = StringUtils.EMPTY + row[12 + ((j + 1) * 14)];
                                    column = "mandatedDiscountRPUVar" + priorProjGtsList.get(j);
                                    String mVariance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[12])) - Double.valueOf(isNull(valueM1)));
                                    String baseMValue = getFormattedValue(AMOUNT, mVariance);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseMValue);
                                        columnList.remove(column);
                                    }

                                    String valueS1 = StringUtils.EMPTY + row[13 + ((j + 1) * 14)];
                                    column = "supplementalRPUVar" + priorProjGtsList.get(j);
                                    String sVariance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[13])) - Double.valueOf(isNull(valueS1)));
                                    String baseSValue = getFormattedValue(AMOUNT, sVariance);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseSValue);
                                        columnList.remove(column);
                                    }
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "PRUPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[11 + ((j + 1) * 14)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[11])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE_PER, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                                if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {

                                    column = "mandatedDiscountRPUPer" + priorProjGtsList.get(j);
                                    String priorMVal = StringUtils.EMPTY + row[12 + ((j + 1) * 14)];
                                    Double perMChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[12])) - Double.valueOf(isNull(priorMVal))) / Double.valueOf(isNull(priorMVal));
                                    if (perMChange.isNaN() || perMChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perMChange))) {
                                        perMChange = 0.0;
                                    }
                                    String mChange = String.valueOf(perMChange);
                                    String baseMValue = getFormattedValue(RATE_PER, mChange);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseMValue + PERCENT);
                                        columnList.remove(column);
                                    }

                                    column = "supplementalRPUPer" + priorProjGtsList.get(j);
                                    String priorSVal = StringUtils.EMPTY + row[13 + ((j + 1) * 14)];
                                    Double perSChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[13])) - Double.valueOf(isNull(priorSVal))) / Double.valueOf(isNull(priorSVal));
                                    if (perSChange.isNaN() || perSChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perSChange))) {
                                        perSChange = 0.0;
                                    }
                                    String sChange = String.valueOf(perSChange);
                                    String baseSValue = getFormattedValue(RATE_PER, sChange);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseSValue + PERCENT);
                                        columnList.remove(column);
                                    }
                                }

                            }

                        }

                        //Netsales
                        if (baseVariables.isVarNetSales()) {
                            String value1 = StringUtils.EMPTY + row[6 + ((j + 1) * 14)];
                            if (baseVariables.isColValue()) {
                                column = "NetSalesValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "NetSalesVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[6 + ((j + 1) * 14)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[6])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColPercentage()) {
                                column = "NetSalesPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[6 + ((j + 1) * 14)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[6])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE_PER, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //    COGC
                        if (baseVariables.isVarCOGC()) {
                            String value1 = StringUtils.EMPTY + row[14 + ((j + 1) * 14)];
                            if (baseVariables.isColValue()) {
                                column = "COGCValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "COGCVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[14 + ((j + 1) * 14)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[14])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColPercentage()) {
                                column = "COGCPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[14 + ((j + 1) * 14)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[14])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE_PER, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //NetProfit
                        if (baseVariables.isVarNetSales()) {
                            String value1 = StringUtils.EMPTY + row[15 + ((j + 1) * 14)];
                            if (baseVariables.isColValue()) {
                                column = "NetProfitValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "NetProfitVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[15 + ((j + 1) * 14)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[15])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColPercentage()) {
                                column = "NetProfitPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[15 + ((j + 1) * 14)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[15])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE_PER, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                    }
                    projDTOList.add(projDTO);
                }
            }
            List<String> columnList = new ArrayList<String>(pvsdto.getColumns());
            columnList.remove(Constant.GROUP);
            for (String ob : periodList) {
                ProjectionVarianceDTO projDTO = new ProjectionVarianceDTO();
                projDTO.setParent(0);
                projDTO.setGroup(periodListMap.get(ob));
                projDTO.setRelationshipLevelName(periodListMap.get(ob));
                for (String columns : columnList) {
                    projDTO.addStringProperties(columns, getFormattedValue(AMOUNT, Constant.NULL));
                }
                projDTOList.add(projDTO);
            }
        }
        return projDTOList;
    }

    public String getFormattedValue(DecimalFormat FORMAT, String value) {
        if (value.contains(NULL)) {
            value = Constant.DASH;
        } else {
            value = FORMAT.format(Double.valueOf(value));
        }
        return value;
    }

    public String isNull(String value) {
        if (value.contains(NULL)) {
            value = ZERO;
        }
        return value;
    }

    public List<ProjectionVarianceDTO> getCustomizedPivotTotalResults(final List<Object> results, List<Integer> priorProjGtsList, PVSelectionDTO pvsdto, PVSelectionDTO baseVariables) {
        List<String> periodList = new ArrayList<String>(pvsdto.getPeriodList());
        Map<String, String> periodListMap = new HashMap<String, String>(pvsdto.getPeriodListMap());
        List<ProjectionVarianceDTO> projDTOList = new ArrayList<ProjectionVarianceDTO>();
        int col = 5;
        int dcol = 2;
        int frequencyDivision = pvsdto.getFrequencyDivision();
        if (frequencyDivision != 1) {
            col = col + 1;
            dcol = dcol + 1;
        }
        if (results != null && !results.isEmpty()) {
            for (int i = 0; i < results.size(); i++) {
                final Object[] row = (Object[]) results.get(i);
                int year = Integer.valueOf(String.valueOf(row[0]));
                int period = 0;
                if (frequencyDivision != 1) {
                    period = Integer.valueOf(String.valueOf(row[1]));
                }
                List<String> common = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, year, period);
                String pcommonColumn = common.get(0);
                String commonHeader = common.get(1);
                String commonDoubleColumn = StringUtils.EMPTY;
                String column = StringUtils.EMPTY;
                if (periodList.contains(pcommonColumn)) {
                    periodList.remove(pcommonColumn);
                    List<String> columnList = new ArrayList<String>(pvsdto.getColumns());
                    columnList.remove(Constant.GROUP);
                    ProjectionVarianceDTO projDTO = new ProjectionVarianceDTO();
                    projDTO.setGroup(commonHeader);
                    projDTO.setRelationshipLevelName(commonHeader);
                    if (baseVariables.isVarExFacSales()) {
                        if (baseVariables.isColValue()) {
                            column = "ExFacValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSales = StringUtils.EMPTY + row[2];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarDemandSales()) {
                        if (baseVariables.isColValue()) {
                            column = "DemandSalesValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSales = StringUtils.EMPTY + row[3];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarInvSales()) {
                        if (baseVariables.isColValue()) {
                            column = "InvWithValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSales = StringUtils.EMPTY + row[4];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarPerExFacSales()) {
                        if (baseVariables.isColValue()) {
                            column = "PerExFacValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSales = StringUtils.EMPTY + row[5];
                            String baseValue = getFormattedValue(RATE, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarPerDemandSales()) {
                        if (baseVariables.isColValue()) {
                            column = "PerDemandSalesValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSales = StringUtils.EMPTY + row[6];
                            String baseValue = getFormattedValue(RATE, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarPerInvSales()) {
                        if (baseVariables.isColValue()) {
                            column = "PerInvWithValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSales = StringUtils.EMPTY + row[7];
                            String baseValue = getFormattedValue(RATE, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarContractsales()) {
                        if (baseVariables.isColValue()) {
                            column = "ContractSalesWACValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSales = StringUtils.EMPTY + row[8];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarContractUnits()) {
                        if (baseVariables.isColValue()) {
                            column = "ContractUnitsValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSales = StringUtils.EMPTY + row[9];
                            String baseValue = getFormattedValue(AMOUNT_UNITS, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarDisRate()) {
                        if (baseVariables.isColValue()) {
                            column = "DiscountSalesValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSales = StringUtils.EMPTY + row[15];
                            String baseValue = getFormattedValue(RATE_PER, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }

                        if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {
                            column = "mandatedDiscountSalesValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentMSales = StringUtils.EMPTY + row[12];
                            String baseValueForMand = getFormattedValue(RATE_PER, currentMSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValueForMand + PERCENT);
                                columnList.remove(column);
                            }

                            column = "supplementalDiscountSalesValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSuppSales = StringUtils.EMPTY + row[13];
                            String baseValueForSupp = getFormattedValue(RATE_PER, currentSuppSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValueForSupp + PERCENT);
                                columnList.remove(column);
                            }

                        }
                    }
                    if (baseVariables.isVarDisAmount()) {
                        if (baseVariables.isColValue()) {
                            column = "DiscountAmountValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSales = StringUtils.EMPTY + row[14];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {
                            column = "mandatedDiscountAmountValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentMSales = StringUtils.EMPTY + row[10];
                            String baseValueForMand = getFormattedValue(AMOUNT, currentMSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValueForMand);
                                columnList.remove(column);
                            }

                            column = "supplementalDiscountAmountValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSuppSales = StringUtils.EMPTY + row[11];
                            String baseValueForSupp = getFormattedValue(AMOUNT, currentSuppSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValueForSupp);
                                columnList.remove(column);
                            }

                        }

                    }
                    if (baseVariables.isVarRPU()) {
                        if (baseVariables.isColValue()) {
                            column = "RPUValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSales = StringUtils.EMPTY + row[19];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {
                            column = "mandatedDiscountRPUValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentMSales = StringUtils.EMPTY + row[17];
                            String baseValueForMand = getFormattedValue(AMOUNT, currentMSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValueForMand);
                                columnList.remove(column);
                            }
                            column = "supplementalRPUValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSuppSales = StringUtils.EMPTY + row[18];
                            String baseValueForSupp = getFormattedValue(AMOUNT, currentSuppSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValueForSupp);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarNetSales()) {
                        if (baseVariables.isColValue()) {
                            column = "NetSalesValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSales = StringUtils.EMPTY + row[16];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarCOGC()) {
                        if (baseVariables.isColValue()) {
                            column = "COGCValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSales = StringUtils.EMPTY + row[20];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }

                    if (baseVariables.isVarNetProfit()) {
                        if (baseVariables.isColValue()) {
                            column = "NetProfitValue" + CURRENT + pvsdto.getCurrentProjectionID();
                            String currentSales = StringUtils.EMPTY + row[21];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    for (int j = 0; j < priorProjGtsList.size(); j++) {
                        // Ex Factory Sales
                        if (baseVariables.isVarExFacSales()) {
                            String value1 = StringUtils.EMPTY + row[2 + ((j + 1) * 20)];
                            if (baseVariables.isColValue()) {
                                column = "ExFacValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "ExFacVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[2 + ((j + 1) * 20)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[2])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "ExFacPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[2 + ((j + 1) * 20)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[2])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //Demand sales
                        if (baseVariables.isVarDemandSales()) {
                            String value1 = StringUtils.EMPTY + row[3 + ((j + 1) * 20)];
                            if (baseVariables.isColValue()) {
                                column = "DemandSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "DemandSalesVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[3 + ((j + 1) * 20)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[3])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "DemandSalesPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[3 + ((j + 1) * 20)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[3])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //Inventory
                        if (baseVariables.isVarInvSales()) {
                            String value1 = StringUtils.EMPTY + row[4 + ((j + 1) * 20)];
                            if (baseVariables.isColValue()) {
                                column = "InvWithValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "InvWithVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[4 + ((j + 1) * 20)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[4])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "InvWithPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[4 + ((j + 1) * 20)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[4])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //Percentage of Ex Fac
                        if (baseVariables.isVarPerExFacSales()) {
                            String value1 = StringUtils.EMPTY + row[5 + ((j + 1) * 20)];
                            if (baseVariables.isColValue()) {
                                column = "PerExFacValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "PerExFacVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[5 + ((j + 1) * 20)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[5])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "PerExFacPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[5 + ((j + 1) * 20)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[5])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //Percentage of Demand
                        if (baseVariables.isVarPerDemandSales()) {
                            String value1 = StringUtils.EMPTY + row[6 + ((j + 1) * 20)];
                            if (baseVariables.isColValue()) {
                                column = "PerDemandSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "PerDemandSalesVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[6 + ((j + 1) * 20)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[6])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "PerDemandSalesPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[6 + ((j + 1) * 20)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[6])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        // Percentange of Inventory
                        if (baseVariables.isVarPerInvSales()) {
                            String value1 = StringUtils.EMPTY + row[7 + ((j + 1) * 20)];
                            if (baseVariables.isColValue()) {
                                column = "PerInvWithValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "PerInvWithVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[7 + ((j + 1) * 20)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[7])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "PerInvWithPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[7 + ((j + 1) * 20)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[7])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //Contract Sales
                        if (baseVariables.isVarContractsales()) {
                            String value1 = StringUtils.EMPTY + row[8 + ((j + 1) * 20)];
                            if (baseVariables.isColValue()) {
                                column = "ContractSalesWACValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "ContractSalesWACVar" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[8 + ((j + 1) * 20)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[8])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                commonDoubleColumn = "ContractSalesWACVarPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[8 + ((j + 1) * 20)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[8])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(commonDoubleColumn)) {
                                    projDTO.addStringProperties(commonDoubleColumn, baseValue + PERCENT);
                                    columnList.remove(commonDoubleColumn);
                                }
                            }
                        }
                        //Contract Units
                        if (baseVariables.isVarContractUnits()) {
                            String value1 = StringUtils.EMPTY + row[9 + ((j + 1) * 20)];
                            if (baseVariables.isColValue()) {
                                column = "ContractUnitsValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT_UNITS, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "ContractUnitsVar" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[9 + ((j + 1) * 20)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[9])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT_UNITS, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "ContractUnitsPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[9 + ((j + 1) * 20)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[9])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }

                        if (baseVariables.isVarDisRate()) {
                            String value1 = StringUtils.EMPTY + row[15 + ((j + 1) * 20)];
                            if (baseVariables.isColValue()) {
                                column = "DiscountSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                                if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {
                                    String valueM1 = StringUtils.EMPTY + row[12 + ((j + 1) * 20)];
                                    column = "mandatedDiscountSalesValue" + priorProjGtsList.get(j);

                                    String baseMValue = getFormattedValue(RATE_PER, valueM1);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseMValue + PERCENT);
                                        columnList.remove(column);
                                    }

                                    String valueS1 = StringUtils.EMPTY + row[13 + ((j + 1) * 20)];
                                    column = "supplementalDiscountSalesValue" + priorProjGtsList.get(j);

                                    String baseSValue = getFormattedValue(RATE, valueS1);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseSValue + PERCENT);
                                        columnList.remove(column);
                                    }
                                }

                            }
                            if (baseVariables.isColVariance()) {
                                column = "DiscountSalesVar" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[15 + ((j + 1) * 20)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[14])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE_PER, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                                if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {
                                    String valueM1 = StringUtils.EMPTY + row[12 + ((j + 1) * 20)];
                                    column = "mandatedDiscountSalesVar" + priorProjGtsList.get(j);
                                    String mVariance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[10])) - Double.valueOf(isNull(valueM1)));
                                    String baseMValue = getFormattedValue(RATE_PER, mVariance);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseMValue + PERCENT);
                                        columnList.remove(column);
                                    }

                                    String valueS1 = StringUtils.EMPTY + row[13 + ((j + 1) * 20)];
                                    column = "supplementalDiscountSalesVar" + priorProjGtsList.get(j);
                                    String sVariance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[11])) - Double.valueOf(isNull(valueS1)));
                                    String baseSValue = getFormattedValue(RATE_PER, sVariance);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseSValue + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                            if (baseVariables.isColPercentage()) {
                                column = "DiscountSalesPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[15 + ((j + 1) * 20)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[14])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE_PER, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                                if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {

                                    column = "mandatedDiscountSalesPer" + priorProjGtsList.get(j);
                                    String priorMVal = StringUtils.EMPTY + row[12 + ((j + 1) * 20)];
                                    Double perMChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[10])) - Double.valueOf(isNull(priorMVal))) / Double.valueOf(isNull(priorMVal));
                                    if (perMChange.isNaN() || perMChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perMChange))) {
                                        perMChange = 0.0;
                                    }
                                    String mChange = String.valueOf(perMChange);
                                    String baseMValue = getFormattedValue(RATE_PER, mChange);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseMValue + PERCENT);
                                        columnList.remove(column);
                                    }

                                    column = "supplementalDiscountSalesPer" + priorProjGtsList.get(j);
                                    String priorSVal = StringUtils.EMPTY + row[13 + ((j + 1) * 20)];
                                    Double perSChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[11])) - Double.valueOf(isNull(priorSVal))) / Double.valueOf(isNull(priorSVal));
                                    if (perSChange.isNaN() || perSChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perSChange))) {
                                        perSChange = 0.0;
                                    }
                                    String sChange = String.valueOf(perSChange);
                                    String baseSValue = getFormattedValue(RATE_PER, sChange);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseSValue + PERCENT);
                                        columnList.remove(column);
                                    }
                                }

                            }
                        }

                        if (baseVariables.isVarDisAmount()) {
                            String value1 = StringUtils.EMPTY + row[14 + ((j + 1) * 20)];
                            if (baseVariables.isColValue()) {
                                column = "DiscountAmountValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                                if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {
                                    String valueM1 = StringUtils.EMPTY + row[11 + ((j + 1) * 20)];
                                    column = "mandatedDiscountAmountValue" + priorProjGtsList.get(j);

                                    String baseMValue = getFormattedValue(AMOUNT, valueM1);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseMValue);
                                        columnList.remove(column);
                                    }

                                    String valueS1 = StringUtils.EMPTY + row[12 + ((j + 1) * 20)];
                                    column = "supplementalDiscountAmountValue" + priorProjGtsList.get(j);

                                    String baseSValue = getFormattedValue(AMOUNT, valueS1);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseSValue);
                                        columnList.remove(column);
                                    }
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "DiscountAmountVar" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[14 + ((j + 1) * 20)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[15])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                                if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {
                                    column = "mandatedDiscountAmountVar" + priorProjGtsList.get(j);
                                    String priorMVal = StringUtils.EMPTY + row[11 + ((j + 1) * 20)];
                                    String mVariance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[12])) - Double.valueOf(isNull(priorMVal)));
                                    String baseMValue = getFormattedValue(AMOUNT, mVariance);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseMValue);
                                        columnList.remove(column);
                                    }

                                    column = "supplementalDiscountAmountVar" + priorProjGtsList.get(j);
                                    String priorSVal = StringUtils.EMPTY + row[12 + ((j + 1) * 20)];
                                    String sVariance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[13])) - Double.valueOf(isNull(priorSVal)));
                                    String baseSValue = getFormattedValue(AMOUNT, sVariance);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseSValue);
                                        columnList.remove(column);
                                    }
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "DiscountAmountPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[14 + ((j + 1) * 20)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[15])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE_PER, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                                if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {
                                    column = "mandatedDiscountAmountPer" + priorProjGtsList.get(j);
                                    String priorMVal = StringUtils.EMPTY + row[11 + ((j + 1) * 20)];
                                    Double perMChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[12])) - Double.valueOf(isNull(priorMVal))) / Double.valueOf(isNull(priorMVal));
                                    if (perMChange.isNaN() || perMChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perMChange))) {
                                        perMChange = 0.0;
                                    }
                                    String mChange = String.valueOf(perMChange);
                                    String baseMValue = getFormattedValue(RATE_PER, mChange);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseMValue + PERCENT);
                                        columnList.remove(column);
                                    }

                                    column = "supplementalDiscountAmountPer" + priorProjGtsList.get(j);
                                    String priorSVal = StringUtils.EMPTY + row[12 + ((j + 1) * 20)];
                                    Double perSChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[13])) - Double.valueOf(isNull(priorSVal))) / Double.valueOf(isNull(priorSVal));
                                    if (perSChange.isNaN() || perSChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perSChange))) {
                                        perSChange = 0.0;
                                    }
                                    String SChange = String.valueOf(perSChange);
                                    String baseSValue = getFormattedValue(RATE_PER, SChange);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseSValue + PERCENT);
                                        columnList.remove(column);
                                    }

                                }
                            }
                        }
                        //RPU
                        if (baseVariables.isVarRPU()) {
                            String value1 = StringUtils.EMPTY + row[19 + ((j + 1) * 20)];
                            if (baseVariables.isColValue()) {
                                column = "RPUValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                                if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {
                                    String valueM1 = StringUtils.EMPTY + row[17 + ((j + 1) * 20)];
                                    column = "mandatedDiscountRPUValue" + priorProjGtsList.get(j);

                                    String baseMValue = getFormattedValue(AMOUNT, valueM1);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseMValue);
                                        columnList.remove(column);
                                    }

                                    String valueS1 = StringUtils.EMPTY + row[18 + ((j + 1) * 20)];
                                    column = "supplementalRPUValue" + priorProjGtsList.get(j);

                                    String baseSValue = getFormattedValue(AMOUNT, valueS1);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseSValue);
                                        columnList.remove(column);
                                    }
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "PRUVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[19 + ((j + 1) * 20)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[19])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                                if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {
                                    String valueM1 = StringUtils.EMPTY + row[17 + ((j + 1) * 20)];
                                    column = "mandatedDiscountRPUVar" + priorProjGtsList.get(j);
                                    String mVariance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[17])) - Double.valueOf(isNull(valueM1)));
                                    String baseMValue = getFormattedValue(AMOUNT, mVariance);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseMValue);
                                        columnList.remove(column);
                                    }

                                    String valueS1 = StringUtils.EMPTY + row[18 + ((j + 1) * 20)];
                                    column = "supplementalRPUVar" + priorProjGtsList.get(j);
                                    String sVariance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[18])) - Double.valueOf(isNull(valueS1)));
                                    String baseSValue = getFormattedValue(AMOUNT, sVariance);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseSValue);
                                        columnList.remove(column);
                                    }
                                }
                            }
                            if (baseVariables.isColPercentage()) {
                                column = "PRUPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[19 + ((j + 1) * 20)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[19])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE_PER, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                                if (Constant.COMPONENT.equals(baseVariables.getDiscountLevel())) {

                                    column = "mandatedDiscountRPUPer" + priorProjGtsList.get(j);
                                    String priorMVal = StringUtils.EMPTY + row[17 + ((j + 1) * 20)];
                                    Double perMChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[17])) - Double.valueOf(isNull(priorMVal))) / Double.valueOf(isNull(priorMVal));
                                    if (perMChange.isNaN() || perMChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perMChange))) {
                                        perMChange = 0.0;
                                    }
                                    String mChange = String.valueOf(perMChange);
                                    String baseMValue = getFormattedValue(RATE_PER, mChange);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseMValue + PERCENT);
                                        columnList.remove(column);
                                    }

                                    column = "supplementalRPUPer" + priorProjGtsList.get(j);
                                    String priorSVal = StringUtils.EMPTY + row[18 + ((j + 1) * 20)];
                                    Double perSChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[18])) - Double.valueOf(isNull(priorSVal))) / Double.valueOf(isNull(priorSVal));
                                    if (perSChange.isNaN() || perSChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perSChange))) {
                                        perSChange = 0.0;
                                    }
                                    String sChange = String.valueOf(perSChange);
                                    String baseSValue = getFormattedValue(RATE_PER, sChange);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseSValue + PERCENT);
                                        columnList.remove(column);
                                    }
                                }

                            }
                        }
                        //Netsales
                        if (baseVariables.isVarNetSales()) {
                            String value1 = StringUtils.EMPTY + row[16 + ((j + 1) * 20)];
                            if (baseVariables.isColValue()) {
                                column = "NetSalesValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "NetSalesVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[16 + ((j + 1) * 20)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[16])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "NetSalesPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[16 + ((j + 1) * 20)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[16])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //COGC
                        if (baseVariables.isVarCOGC()) {
                            String value1 = StringUtils.EMPTY + row[20 + ((j + 1) * 20)];
                            if (baseVariables.isColValue()) {
                                column = "COGCValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "COGCVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[20 + ((j + 1) * 20)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[20])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "COGCPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[20 + ((j + 1) * 20)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[20])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //Net Profit
                        if (baseVariables.isVarNetProfit()) {
                            String value1 = StringUtils.EMPTY + row[21 + ((j + 1) * 20)];
                            if (baseVariables.isColValue()) {
                                column = "NetProfitValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "NetProfitVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[21 + ((j + 1) * 20)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[21])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "NetProfitPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[21 + ((j + 1) * 20)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[21])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                    }
                    projDTOList.add(projDTO);
                }
            }
            List<String> columnList = new ArrayList<String>(pvsdto.getColumns());
            columnList.remove(Constant.GROUP);
            for (String ob : periodList) {
                ProjectionVarianceDTO projDTO = new ProjectionVarianceDTO();
                projDTO.setParent(0);
                projDTO.setGroup(periodListMap.get(ob));
                projDTO.setRelationshipLevelName(periodListMap.get(ob));
                for (String columns : columnList) {
                    projDTO.addStringProperties(columns, getFormattedValue(AMOUNT, Constant.NULL));
                }
                projDTOList.add(projDTO);
            }
        }
        return projDTOList;
    }

    public static List<String> getProgramCodeName(int projectionId) {
        List<String> strList = new ArrayList<String>();
        String customSQL = "SELECT DISTINCT CM.CONTRACT_NO FROM dbo.CONTRACT_MASTER CM \n"
                + "JOIN dbo.CCP_DETAILS CCP ON CCP.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
                + "JOIN dbo.PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID  = CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID = " + projectionId;

        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(customSQL, null, null);
        if (list != null && !list.isEmpty()) {
            strList = CommonUtils.objectListToStringList(list);
        }
        return strList;
    }

    private String getCCPProgramCodeTempTableQuery(final PVSelectionDTO pvsdto) {
        List<String> projectionIdList = new ArrayList<String>();
        projectionIdList.add(String.valueOf(pvsdto.getCurrentProjectionID()));
        for (Integer projId : pvsdto.getProjectionMap().keySet()) {
            projectionIdList.add(String.valueOf(projId));
            pivotPriorProjIdList.add(projId);
        }
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        String query = "DECLARE @PROJECTION_MASTER_SID VARCHAR(500)='" + projectionId + "',\n"
                + "        @TEMP_PROJ_SID         VARCHAR(500),\n"
                + "        @USER_ID               INT=" + pvsdto.getUserId() + ",\n"
                + "        @SESSION_ID            INT=" + pvsdto.getSessionId() + ",\n"
                + "        @SP                    INT,\n"
                + "        @SP_PROJ_SID           INT\n"
                + " IF Object_Id('TEMPDB..#TEMP_PROJECTION_MASTER') IS NOT NULL\n"
                + "  DROP TABLE #TEMP_PROJECTION_MASTER\n"
                + "\n"
                + " CREATE TABLE #TEMP_PROJECTION_MASTER\n"
                + "  (\n"
                + "     ID                    INT IDENTITY(1, 1),\n"
                + "     PROJECTION_MASTER_SID INT\n"
                + "  ) \n"
                + "\n"
                + "\n"
                + " IF Object_Id('TEMPDB..#PIVOT_RESULT') IS NOT NULL\n"
                + "  DROP TABLE #PIVOT_RESULT\n"
                + "\n"
                + " CREATE TABLE #PIVOT_RESULT\n"
                + "  (\n"
                + "     PROJECTION_ID                INT,\n"
                + "     [YEAR]                       INT,\n"
                + "     [PERIOD]                     SMALLINT,\n"
                + "     MANDATED_PROJECTED_SALES     NUMERIC(38, 15),\n"
                + "     MANDATED_PROJECTED_RATE      NUMERIC(38, 15),\n"
                + "      MANDATED_PROJECTED_RPU       NUMERIC(38, 15),\n"
                + "     SUPPLEMENTAL_PROJECTED_SALES NUMERIC(38, 15),\n"
                + "     SUPPLEMENTAL_PROJECTED_RATE  NUMERIC(38, 15),\n"
                + "       SUPPLEMENTAL_PROJECTED_RPU   NUMERIC(38, 15),"
                + "     CONTRACT_NO                  VARCHAR(50)\n"
                + "  ) \n"
                + "\n"
                + "\n"
                + " SET @TEMP_PROJ_SID = Replace(@PROJECTION_MASTER_SID + ',', ',,', ',')\n"
                + "\n"
                + " WHILE PatIndex('%,%', @TEMP_PROJ_SID) <> 0\n"
                + "  BEGIN\n"
                + "      SELECT @SP = PatIndex('%,%', @TEMP_PROJ_SID)\n"
                + "\n"
                + "      SELECT @SP_PROJ_SID = LEFT(@TEMP_PROJ_SID, @SP - 1)\n"
                + "\n"
                + "      SELECT @TEMP_PROJ_SID = Stuff(@TEMP_PROJ_SID, 1, @SP, '')\n"
                + "\n"
                + "      INSERT INTO #TEMP_PROJECTION_MASTER\n"
                + "                  (PROJECTION_MASTER_SID)\n"
                + "      SELECT @SP_PROJ_SID\n"
                + "  END\n"
                + "\n"
                + " DECLARE @FIRST_PROJ_SID INT\n"
                + "\n"
                + " SELECT @FIRST_PROJ_SID = PM.PROJECTION_MASTER_SID\n"
                + " FROM   #TEMP_PROJECTION_MASTER PM\n"
                + " WHERE  ID = 1\n"
                + "\n"
                + " IF Object_Id('TEMPDB..#CCP') IS NOT NULL\n"
                + "  DROP TABLE #CCP \n"
                + "\n"
                + " CREATE TABLE #CCP\n"
                + "  (\n"
                + "     RELATIONSHIP_LEVEL_SID INT,\n"
                + "     CCP_DETAILS_SID        INT,\n"
                + "     HIERARCHY_NO           VARCHAR(50),\n"
                + "     CONTRACT_NO            VARCHAR(50)\n"
                + "  )\n"
                + "\n"
                + " INSERT INTO #CCP\n"
                + "            (RELATIONSHIP_LEVEL_SID,\n"
                + "             CCP_DETAILS_SID,\n"
                + "             HIERARCHY_NO,\n"
                + "             CONTRACT_NO) ";

        return query;
    }

    private String getCCPQueryForProgramCode(PVSelectionDTO projSelDTO) {
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String builderSid = StringUtils.EMPTY;
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
            builderSid = String.valueOf(projSelDTO.getCustomerRelationId());
        } else {
            builderSid = String.valueOf(projSelDTO.getProductRelationId());
        }
        String tableQuery = " SELECT LCCP.RELATIONSHIP_LEVEL_SID,\n"
                + "       LCCP.CCP_DETAILS_SID,\n"
                + "       LCCP.HIERARCHY_NO,\n"
                + "       LCCP.CONTRACT_NO\n"
                + "FROM   (SELECT CCPMAP.CCP_DETAILS_SID,\n"
                + "               HLD.HIERARCHY_NO,\n"
                + "               HLD.RELATIONSHIP_LEVEL_SID,\n"
                + "               CCPMAP.CONTRACT_NO\n"
                + "        FROM   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES,\n"
                + "                       RLD.RELATIONSHIP_LEVEL_SID,\n"
                + "                       RLD.RELATIONSHIP_BUILDER_SID,\n"
                + "                       RLD.HIERARCHY_NO,\n"
                + "                       CCP.CCP_DETAILS_SID,\n"
                + "                       CM.CONTRACT_NO\n"
                + "                FROM   RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                + "                JOIN   CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID\n"
                + "                                  AND RLD.RELATIONSHIP_BUILDER_SID = " + builderSid + "\n"
                + "                JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "                                            AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID\n"
                + "                JOIN   CCP_DETAILS CCPD ON CCPD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "                JOIN   CONTRACT_MASTER CM ON CM.CONTRACT_MASTER_SID = CCPD.CONTRACT_MASTER_SID\n"
                + "                JOIN   ST_M_SALES_PROJECTION_MASTER S ON S.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                + "                WHERE  S.USER_ID = @USER_ID\n"
                + "                   AND S.SESSION_ID = @SESSION_ID) CCPMAP,\n"
                + "               (SELECT RLD1.HIERARCHY_NO,\n"
                + "                       RLD1.RELATIONSHIP_LEVEL_SID\n"
                + "                FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1\n"
                + "                JOIN   " + viewtable + " PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID\n"
                + "                                                    AND PCH.PROJECTION_MASTER_SID = @FIRST_PROJ_SID\n"
                + "                WHERE  RLD1.HIERARCHY_NO LIKE '" + projSelDTO.getHierarchyNo() + "%') HLD\n"
                + "        WHERE  CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO + '%') LCCP\n"
                + "WHERE  LCCP.HIERARCHY_NO IN (SELECT RLD2.HIERARCHY_NO\n"
                + "                             FROM   RELATIONSHIP_LEVEL_DEFINITION RLD2\n"
                + "                             JOIN   " + viewtable + " PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID = RLD2.RELATIONSHIP_LEVEL_SID\n"
                + "                                                                  AND PCH2.PROJECTION_MASTER_SID = @FIRST_PROJ_SID\n"
                + "                             WHERE  RLD2.LEVEL_NO=" + projSelDTO.getLevelNo() + ") "
                + " IF Object_Id('TEMPDB..#TEMP_CONTRACT_MASTER') IS NOT NULL\n"
                + "  DROP TABLE #TEMP_CONTRACT_MASTER\n"
                + "CREATE TABLE #TEMP_CONTRACT_MASTER\n"
                + "  (\n"
                + "     ID               INT IDENTITY(1, 1),\n"
                + "	 CONTRACT_NO      VARCHAR(50),\n"
                + "     TEMP_CONTRACT_NO VARCHAR(50)\n"
                + "  )\n"
                + "INSERT INTO #TEMP_CONTRACT_MASTER\n"
                + "            (TEMP_CONTRACT_NO,\n"
                + "             CONTRACT_NO)\n"
                + "(SELECT DISTINCT\n"
                + "        Replace(CONTRACT_NO, ' ', '_') AS TEMP_CONTRACT_NO,\n"
                + "        CONTRACT_NO\n"
                + " FROM   #CCP) ";

        return tableQuery;
    }

    private String getPivotProgramCodeQuery(PVSelectionDTO projSelDTO, boolean currentProjectionFlag) {
        String insertTable = "INSERT INTO #PIVOT_RESULT\n"
                + "  (PROJECTION_ID,YEAR,PERIOD,MANDATED_PROJECTED_SALES,MANDATED_PROJECTED_RATE,MANDATED_PROJECTED_RPU,SUPPLEMENTAL_PROJECTED_SALES,SUPPLEMENTAL_PROJECTED_RATE,SUPPLEMENTAL_PROJECTED_RPU,\n"
                + "  CONTRACT_NO) \n";

        String selectClause = insertTable + " select TODIS.PROJECTION_MASTER_SID, TODIS.YEARS  AS YEARS,TODIS.PERIODS AS PERIODS,";

        selectClause += getPCPivotSelectClause();

        String totalDiscountQuery = getPCProjectionResultsDiscountsQuery(projSelDTO, currentProjectionFlag);
        String salesPpaQuery = getPCSalesQuery(projSelDTO, currentProjectionFlag);

        String customQuery = selectClause + totalDiscountQuery + " TODIS FULL OUTER JOIN " + salesPpaQuery;

        customQuery += " SALESUPP on TODIS.YEARS=SALESUPP.YEARS AND TODIS.PERIODS=SALESUPP.PERIODS AND TODIS.CONTRACT_NO = SALESUPP.CONTRACT_NO ";

        return customQuery;
    }

    public String getPCPivotSelectClause() {
        String selectClause = "IsNull(TODIS.PROJECTION_SALES, 0)                                                              AS MANDATED_PROJECTED_SALES,\n"
                + " COALESCE(IsNull(TODIS.PROJECTION_SALES, 0) / NULLIF(SALESUPP.SALES_PROJECTION_SALES, 0)*100, 0)    AS MANDATED_PROJECTED_RATE,\n"
                + "  COALESCE(Isnull(TODIS.PROJECTION_SALES, 0) / NULLIF(SALESUPP.PROJECTION_UNITS, 0)*100, 0)                AS MANDATED_PROJECTED_RPU,"
                + " IsNull(SALESUPP.PROJECTION_SALES, 0)                                                           AS SUPPLEMENTAL_PROJECTED_SALES,\n"
                + " COALESCE(IsNull(SALESUPP.PROJECTION_SALES, 0) / NULLIF(SALESUPP.SALES_PROJECTION_SALES, 0)*100, 0) AS SUPPLEMENTAL_PROJECTED_RATE,\n"
                + "COALESCE(Isnull(SALESUPP.PROJECTION_SALES, 0) / NULLIF(SALESUPP.PROJECTION_UNITS, 0)*100, 0)             AS SUPPLEMENTAL_PROJECTED_RPU,"
                + " TODIS.CONTRACT_NO";
        return selectClause;
    }

    public String getPCProjectionResultsDiscountsQuery(PVSelectionDTO projSelDTO, boolean currentProjectionFlag) {
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String selectClause = " from (select \n";
        if (currentProjectionFlag) {
            selectClause += " F.PROJECTION_MASTER_SID,";
        }
        String whereClause = StringUtils.EMPTY;
        String groupBy = StringUtils.EMPTY;
        if (currentProjectionFlag) {
            groupBy += ",H.LEVEL_NAME , I.\"YEAR\"";
        } else {
            groupBy += " I.\"YEAR\"";
        }
        String customQuery = StringUtils.EMPTY;
        String ccpWhereCond = getCCPWhereConditionQuery("H", "E", "CCP", Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);

        String parentNode = projSelDTO.getParentNode();
        whereClause = " and H.PARENT_NODE = '" + parentNode + "'";

        String levelValue = projSelDTO.getLevelValue();
        whereClause += " and H.RELATIONSHIP_LEVEL_VALUES = '" + levelValue + "'";
        if (currentProjectionFlag) {
            groupBy = ", H.RELATIONSHIP_LEVEL_VALUES " + groupBy;
        }

        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }

        // To filter the data according to selected period
        String periodFilter = getPeriodRestrictionQuery(projSelDTO.getStartYear(), projSelDTO.getEndYear(), projSelDTO.getStartPeriod(), projSelDTO.getEndPeriod(), projSelDTO.getFrequencyDivision(), "ALL");

        selectClause += "'Total discounts' as DISCOUNTS, ";

        String customSql = StringUtils.EMPTY;
        if (currentProjectionFlag) {
            customSql += " ST_M_SALES_PROJECTION_MASTER B," + viewtable + " G, RELATIONSHIP_LEVEL_DEFINITION H, #CCP CCP,";
        } else {
            customSql += " M_SALES_PROJECTION_MASTER B,CCP_DETAILS CCP, CONTRACT_MASTER CM, ";
        }

        customSql += "  PROJECTION_DETAILS E , PROJECTION_MASTER F, \"PERIOD\" I ";

        customSql += "where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID ";

        if (currentProjectionFlag) {
            customSql += " AND B.USER_ID = A.USER_ID \n"
                    + " AND B.SESSION_ID = A.SESSION_ID \n"
                    + " AND A.USER_ID = @USER_ID \n"
                    + " AND A.SESSION_ID = @SESSION_ID ";
        } else {
            customSql += " AND A.SAVE_FLAG = 1 ";
        }

        customSql += "and E.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID and ";
        if (currentProjectionFlag) {
            customSql += " F.PROJECTION_MASTER_SID = @FIRST_PROJ_SID and G.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID "
                    + "and G.RELATIONSHIP_LEVEL_SID = H.RELATIONSHIP_LEVEL_SID " + ccpWhereCond;
        } else {
            customSql += "F.PROJECTION_MASTER_SID IN (SELECT PM.PROJECTION_MASTER_SID\n"
                    + " FROM   #TEMP_PROJECTION_MASTER PM\n"
                    + " WHERE  ID <> 1) "
                    + " AND CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID \n"
                    + "                    AND CCP.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID  ";
        }

        customSql += " and A.PERIOD_SID = I.PERIOD_SID " + periodFilter + " \n";
        if (currentProjectionFlag) {

            customSql += " and H.LEVEL_NO = " + projSelDTO.getLevelNo() + " \n"
                    + whereClause + " group by H.LEVEL_NO " + groupBy + " , CCP.CONTRACT_NO , F.PROJECTION_MASTER_SID) \n";
        } else {
            customSql += " GROUP  BY "
                    + groupBy
                    + " , CM.CONTRACT_NO,\n"
                    + " F.PROJECTION_MASTER_SID)";
        }

        String futureQuery = selectClause + " 0 as ACTUAL_SALES, sum(A.PROJECTION_SALES) as PROJECTION_SALES, ";
        if (currentProjectionFlag) {
            futureQuery += " CCP.CONTRACT_NO ";
        } else {
            futureQuery += " CM.CONTRACT_NO, \n"
                    + "                        F.PROJECTION_MASTER_SID ";
        }
        futureQuery += " from M_DISCOUNT_PROJECTION A," + customSql;

        customQuery = futureQuery;
        return customQuery;
    }

    public String getPCSalesQuery(PVSelectionDTO projSelDTO, boolean currentProjectionFlag) {
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String mainMasterSalesTable = "  M_SALES_PROJECTION_MASTER ";
        String tempMasterSalesTable = " ST_M_SALES_PROJECTION_MASTER ";
        String mainSalesTable = " M_SALES_PROJECTION ";
        String tempSalesTable = " ST_M_SALES_PROJECTION ";

        String masterDiscount = "  M_SUPPLEMENTAL_DISC_MASTER ";
        String tempMasterDiscount = " ST_M_SUPPLEMENTAL_DISC_MASTER ";
        String mainDiscount = " M_SUPPLEMENTAL_DISC_PROJ ";
        String tempDiscount = " ST_M_SUPPLEMENTAL_DISC_PROJ ";

        String masterSalesTable = StringUtils.EMPTY;
        String salesTable = StringUtils.EMPTY;
        String masterPPATable = StringUtils.EMPTY;
        String suppTable = StringUtils.EMPTY;

        if (currentProjectionFlag) {
            projSelDTO.setIsPrior(false);
            masterSalesTable = tempMasterSalesTable;
            salesTable = tempSalesTable;
            masterPPATable = tempMasterDiscount;
            suppTable = tempDiscount;
        } else {
            projSelDTO.setIsPrior(true);
            masterSalesTable = mainMasterSalesTable;
            salesTable = mainSalesTable;
            masterPPATable = masterDiscount;
            suppTable = mainDiscount;
        }
        String selectClause = " (select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = StringUtils.EMPTY;
        if (currentProjectionFlag) {
            groupBy += ",H.LEVEL_NAME , "
                    + "I.\"YEAR\"";
        } else {
            groupBy += "I.\"YEAR\"";
        }

        String customQuery = StringUtils.EMPTY;
        String ccpWhereCond = getCCPWhereConditionQuery("H", "E", "CCP", Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        String parentNode = projSelDTO.getParentNode();
        whereClause = " and H.PARENT_NODE = '" + parentNode + "'";
        whereClause += " and H.RELATIONSHIP_LEVEL_VALUES = '" + projSelDTO.getLevelValue() + "'";
        if (currentProjectionFlag) {
            groupBy = ", H.RELATIONSHIP_LEVEL_VALUES " + groupBy;
        }
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }
        // To filter the data according to selected period
        String periodFilter = getPeriodRestrictionQuery(projSelDTO.getStartYear(), projSelDTO.getEndYear(), projSelDTO.getStartPeriod(), projSelDTO.getEndPeriod(), projSelDTO.getFrequencyDivision(), "ALL");

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql = masterPPATable + " B,"
                + masterSalesTable + "  B1,"
                + " PROJECTION_DETAILS E , PROJECTION_MASTER F, ";

        if (currentProjectionFlag) {
            customSql += viewtable + " G, RELATIONSHIP_LEVEL_DEFINITION H, \"PERIOD\" I, #CCP CCP ";
        } else {
            customSql += "\"PERIOD\" I,CCP_DETAILS CCP,CONTRACT_MASTER CM ";
        }
        customSql += " where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID ";
        if (currentProjectionFlag) {
            customSql += " AND B.USER_ID = A.USER_ID\n"
                    + " AND B.SESSION_ID = A.SESSION_ID";
        }
        customSql += " and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID "
                + " and A1.PROJECTION_DETAILS_SID = B1.PROJECTION_DETAILS_SID ";
        if (currentProjectionFlag) {
            customSql += " AND B1.USER_ID = A1.USER_ID \n"
                    + " AND B1.SESSION_ID = A1.SESSION_ID ";
        }
        customSql += " and B1.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID ";
        if (currentProjectionFlag) {
            customSql += " AND A.USER_ID = @USER_ID \n"
                    + " AND A.SESSION_ID = @SESSION_ID \n"
                    + " AND B1.USER_ID = @USER_ID \n"
                    + " AND B1.SESSION_ID = @SESSION_ID \n"
                    + " AND A1.USER_ID = @USER_ID \n"
                    + " AND A1.SESSION_ID = @SESSION_ID ";
        }
        customSql += "and E.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID ";

        if (currentProjectionFlag) {
            customSql += " and F.PROJECTION_MASTER_SID = @FIRST_PROJ_SID "
                    + " and G.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID "
                    + " and G.RELATIONSHIP_LEVEL_SID = H.RELATIONSHIP_LEVEL_SID \n"
                    + ccpWhereCond
                    + " and A.PERIOD_SID = I.PERIOD_SID \n"
                    + " and A1.PERIOD_SID = I.PERIOD_SID \n"
                    + periodFilter + " \n"
                    + " and H.LEVEL_NO = " + projSelDTO.getLevelNo()
                    + whereClause
                    + " group by H.LEVEL_NO " + groupBy + " , CCP.CONTRACT_NO ,F.PROJECTION_MASTER_SID) \n";
        } else {
            customSql += "AND F.PROJECTION_MASTER_SID IN (SELECT PM.PROJECTION_MASTER_SID\n"
                    + "  FROM   #TEMP_PROJECTION_MASTER PM\n"
                    + "  WHERE  ID <> 1)\n"
                    + "  AND CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                    + "  AND A.PERIOD_SID = I.PERIOD_SID\n"
                    + "  AND A1.PERIOD_SID = I.PERIOD_SID\n"
                    + "  AND CM.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID " + periodFilter + "GROUP  BY "
                    + groupBy
                    + "  , CM.CONTRACT_NO,\n"
                    + "  F.PROJECTION_MASTER_SID)";
        }

        String futureQuery = selectClause
                + " 0 as ACTUAL_SALES, sum(A.PROJECTION_SALES) as PROJECTION_SALES, \n"
                + " 0 as SALES_ACTUAL_SALES, sum(A1.PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                + " 0 as ACTUAL_UNITS, sum(A1.PROJECTION_UNITS) as PROJECTION_UNITS, \n";
        if (currentProjectionFlag) {
            futureQuery += " CCP.CONTRACT_NO ";
        } else {
            futureQuery += " CM.CONTRACT_NO, F.PROJECTION_MASTER_SID ";
        }

        futureQuery += " from "
                + suppTable + " A,"
                + salesTable + " A1," + customSql;

        return futureQuery;
    }

    public String getPivotPCSelectQuery(String query1, String query2) {
        String finalWhereCond = " on TODIS.YEARS=SALESUPP.YEARS AND TODIS.PERIODS=SALESUPP.PERIODS AND TODIS.CONTRACT_NO = SALESUPP.CONTRACT_NO";
        String selectClause = " (\n" + query1 + "\n) TODIS FULL OUTER JOIN (\n" + query2 + "\n) SALESUPP \n" + finalWhereCond;
        return selectClause;
    }

    private String getDeclarationQuery() {
        String declarationQuery = " DECLARE @SQL          NVARCHAR(MAX),\n"
                + "        @LOOP_CNTR    INT,\n"
                + "        @CNTR         INT,\n"
                + "        @CONTRACT     VARCHAR(50),\n"
                + "        @MAX_CONTRACT INT,\n"
                + "        @MAX_PROJ     INT \n"
                + "\n"
                + " SET @LOOP_CNTR = 1\n"
                + " SET @CNTR =1\n"
                + " SET @MAX_CONTRACT = (SELECT Max(ID)\n"
                + "				FROM   #TEMP_CONTRACT_MASTER) \n"
                + "\n"
                + " SET @MAX_PROJ = (SELECT Max(ID)\n"
                + "                 FROM   #TEMP_PROJECTION_MASTER) \n"
                + "\n"
                + "\n"
                + "SET @SQL = 'SELECT YEAR, PERIOD, '\n"
                + " WHILE @LOOP_CNTR <= @MAX_PROJ\n"
                + " BEGIN\n"
                + " WHILE(@CNTR<=@MAX_CONTRACT)\n"
                + "	BEGIN\n"
                + "		SET @CONTRACT= (SELECT TEMP_CONTRACT_NO FROM #TEMP_CONTRACT_MASTER WHERE ID=@CNTR)\n"
                + "		SET @SQL+=' P'+CAST(@LOOP_CNTR AS VARCHAR(10))+'_MANDATED_PROJECTED_SALES_'+ @CONTRACT + ' = MAX(CASE WHEN PM.ID = '+ CAST(@LOOP_CNTR AS VARCHAR(10))+ ' AND CM.ID = '+ CAST(@CNTR AS VARCHAR(10))+ ' THEN MANDATED_PROJECTED_SALES END),\n"
                + "					P'+CAST(@LOOP_CNTR AS VARCHAR(10))+'_MANDATED_PROJECTED_RATE_'+  @CONTRACT+ ' = MAX(CASE WHEN PM.ID = '+ CAST(@LOOP_CNTR AS VARCHAR(10))+ ' AND CM.ID = '+ CAST(@CNTR AS VARCHAR(10))+ ' THEN MANDATED_PROJECTED_RATE END),\n"
                + "					P'+CAST(@LOOP_CNTR AS VARCHAR(10))+'_SUPPLEMENTAL_PROJECTED_SALES_'+  @CONTRACT+ ' = MAX(CASE WHEN PM.ID = '+ CAST(@LOOP_CNTR AS VARCHAR(10))+ ' AND CM.ID = '+ CAST(@CNTR AS VARCHAR(10))+ ' THEN SUPPLEMENTAL_PROJECTED_SALES END),\n"
                + "					P'+CAST(@LOOP_CNTR AS VARCHAR(10))+'_SUPPLEMENTAL_PROJECTED_RATE_'+  @CONTRACT+ ' = MAX(CASE WHEN PM.ID = '+ CAST(@LOOP_CNTR AS VARCHAR(10))+ ' AND CM.ID = '+ CAST(@CNTR AS VARCHAR(10))+ ' THEN SUPPLEMENTAL_PROJECTED_RATE END),\n"
                + "                                      P'+ Cast(@LOOP_CNTR AS VARCHAR(10)) + '_MANDATED_PROJECTED_RPU_' + @CONTRACT + ' = MAX(CASE WHEN PM.ID = '+ Cast(@LOOP_CNTR AS VARCHAR(10))+ ' AND CM.ID = '+ Cast(@CNTR AS VARCHAR(10)) + ' THEN MANDATED_PROJECTED_RPU END),\n"
                + "			  	        P'+ Cast(@LOOP_CNTR AS VARCHAR(10))+ '_SUPPLEMENTAL_PROJECTED_RPU_' + @CONTRACT+ ' = MAX(CASE WHEN PM.ID = '+ Cast(@LOOP_CNTR AS VARCHAR(10))+ ' AND CM.ID = '  + Cast(@CNTR AS VARCHAR(10)) + ' THEN SUPPLEMENTAL_PROJECTED_RPU END),'"
                + "		SET @CNTR+=1\n"
                + "	END\n"
                + "SET @CNTR=1\n"
                + "SET @LOOP_CNTR +=1\n"
                + "END\n"
                + "SET @SQL = LEFT(@SQL, LEN(@SQL) - 1)\n"
                + "SET @SQL+=' FROM   #PIVOT_RESULT PR\n"
                + "INNER JOIN #TEMP_PROJECTION_MASTER PM ON PM.PROJECTION_MASTER_SID=PR.PROJECTION_ID\n"
                + "JOIN #TEMP_CONTRACT_MASTER CM ON PR.CONTRACT_NO=CM.CONTRACT_NO\n"
                + "GROUP  BY [YEAR],PERIOD\n"
                + "ORDER BY [YEAR],PERIOD'\n"
                + "\n"
                + "\n"
                + "EXEC SP_EXECUTESQL\n"
                + "@SQL\n"
                + "\n"
                + "\n"
                + "DROP TABLE #TEMP_PROJECTION_MASTER\n"
                + "DROP TABLE #PIVOT_RESULT\n"
                + "DROP TABLE #CCP \n"
                + "DROP TABLE #TEMP_CONTRACT_MASTER";
        return declarationQuery;
    }

    public static ComboBox loadCustomerDdlb(PVSelectionDTO pvSelectionDTO, ComboBox contractType) {
        SalesProjectionDAO dao = new SalesProjectionDAOImpl();
        StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);
        String[] tempTableName = new String[2];
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(pvSelectionDTO.getHierarchyIndicator())) {
            tempTableName[0] = "CUST_RELATIONSHIP_BUILDER_SID";
            tempTableName[1] = "CUSTOMER_HIERARCHY_LEVEL";
        } else {
            tempTableName[0] = "PROD_RELATIONSHIP_BUILDER_SID";
            tempTableName[1] = "PRODUCT_HIERARCHY_LEVEL";
        }
        customSql.append("select RLD.RELATIONSHIP_LEVEL_VALUES,RLD.HIERARCHY_NO from PROJECTION_MASTER PM,RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                + "WHERE RLD.RELATIONSHIP_BUILDER_SID = PM." + tempTableName[0] + " \n"
                + "AND RLD.LEVEL_NAME in (" + pvSelectionDTO.getLevelName() + ") \n"
                + "AND RLD.LEVEL_NO >= PM." + tempTableName[1] + " \n"
                + "AND PM.PROJECTION_MASTER_SID = " + pvSelectionDTO.getProjectionId());
        try {
            List list = (List) dao.executeSelectQuery(customSql.toString());
            contractType.addItem(ZERO);
            contractType.setItemCaption(ZERO, SELECT_ONE);
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    contractType.addItem(obj[1] == null ? StringUtils.EMPTY : obj[1].toString());
                    contractType.setItemCaption(obj[1] == null ? StringUtils.EMPTY : obj[1].toString(), obj[0] == null ? StringUtils.EMPTY : obj[0].toString());
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return contractType;
    }

    public int customCount(int customId) {
        int customCount = 0;
        try {
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            String hierarchyIndicatorQuery = "select count(*) from dbo.CUSTOM_VIEW_DETAILS where CUSTOM_VIEW_MASTER_SID=" + customId; //+ " and LEVEL_NO=" + levelNo;
            List<Object> list = (List<Object>) salesProjectionDAO.executeSelectQuery(hierarchyIndicatorQuery);
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                customCount = Integer.valueOf(String.valueOf(ob));
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return customCount;
    }

    /**
     * GTS
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getGTS(List<Object> dataList, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE)) {
            pvDTO.setGroup("Gross Trade Sales Value");
            pvDTO.setRelationshipLevelName("Gross Trade Sales Value");
        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
            pvDTO.setGroup("Gross Trade Sales Variance");
            pvDTO.setRelationshipLevelName("Gross Trade Sales Variance");
        } else {
            pvDTO.setGroup("Gross Trade Sales % Change");
            pvDTO.setRelationshipLevelName("Gross Trade Sales % Change");
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {
            if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);

                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {

                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2])));
                        String baseValue = getFormattedValue(AMOUNT, value);
//                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2 + ((j + 1) * 11)]))); // need to check here 
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[2 + ((j + 1) * 11)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[2 + ((j + 1) * 11)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[2])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            } else {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2 + ((j + 1) * 11)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[2 + ((j + 1) * 11)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[2 + ((j + 1) * 11)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[2])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        }
        return pvDTO;
    }

    public List<Object> getProjVarianceResults(final PVSelectionDTO pvsdto, final ProjectionVarianceDTO parentDto) {
        pvsdto.setYear("ALL");
        pvsdto.setProjectionId(pvsdto.getSession().getProjectionId());
        String query;
        if (pvsdto.isIsCustomHierarchy()) {
            query = CommonLogic.getCCPQuery(pvsdto,Boolean.FALSE) + CommonLogic.getTempCCPQueryForCOGS(pvsdto) + " \n" + getProjectionVarianceQuery(pvsdto);
        } else {
            query = getCCPTempTableQuery() + getCCPQuery(pvsdto, pvsdto.getProjectionId(), false) + CommonLogic.getTempCCPQueryForCOGS(pvsdto) + " \n" + getProjectionVarianceQuery(pvsdto);
        }
        List<Object> currentPivotDetails = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        pvsdto.setProjectionId(pvsdto.getSession().getProjectionId());
        return currentPivotDetails;
    }

    public ProjectionVarianceDTO getPercentageOfBusiness(final List<Object> gtsList, ProjectionVarianceDTO gtsDto, ProjectionVarianceDTO salesDto, PVSelectionDTO pvsdto, final List<Object> dataList) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE)) {
            pvDTO.setGroup("% of Business Value");
            pvDTO.setRelationshipLevelName("% of Business Value");
        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
            pvDTO.setGroup("% of Business Variance");
            pvDTO.setRelationshipLevelName("% of Business Variance");
        } else {
            pvDTO.setGroup("% of Business % Change");
            pvDTO.setRelationshipLevelName("% of Business % Change");
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = StringUtils.EMPTY + obj[11];
                        String baseValue = getFormattedValue(RATE, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue + PERCENT);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = StringUtils.EMPTY + obj[11 + ((j + 1) * 11)];
                            String val = getFormattedValue(RATE, isNull(priorVal));
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[11 + ((j + 1) * 11)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[11])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(RATE, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[11 + ((j + 1) * 11)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[11])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else {

            if (dataList != null && !dataList.isEmpty()) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    final Object[] gts = (Object[]) gtsList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        Double tempValue = Double.valueOf(isNull(StringUtils.EMPTY + obj[2])) / Double.valueOf(isNull(StringUtils.EMPTY + gts[2]));
                        if (tempValue.isNaN() || tempValue.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(tempValue))) {
                            tempValue = 0.0;
                        }
                        String value = String.valueOf(tempValue);
                        String baseValue = getFormattedValue(RATE, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue + PERCENT);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            Double tempPriorValue = Double.valueOf(isNull(StringUtils.EMPTY + obj[2 + ((j + 1) * 9)])) / Double.valueOf(isNull(StringUtils.EMPTY + gts[2 + ((j + 1) * 11)]));
                            if (tempPriorValue.isNaN() || tempPriorValue.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(tempPriorValue))) {
                                tempPriorValue = 0.0;
                            }
                            String priorVal = String.valueOf(tempPriorValue);
                            String val = getFormattedValue(RATE, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            Double value = Double.valueOf(isNull(StringUtils.EMPTY + obj[2])) / Double.valueOf(isNull(StringUtils.EMPTY + gts[2]));
                            Double priorVal = Double.valueOf(isNull(StringUtils.EMPTY + obj[2 + ((j + 1) * 9)])) / Double.valueOf(isNull(StringUtils.EMPTY + gts[2 + ((j + 1) * 11)]));
                            Double tempVariance = value - priorVal;
                            if (tempVariance.isNaN() || tempVariance.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(tempVariance))) {
                                tempVariance = 0.0;
                            }
                            String val = getFormattedValue(RATE, String.valueOf(tempVariance));
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else {
                            Double value = Double.valueOf(isNull(StringUtils.EMPTY + obj[2])) / Double.valueOf(isNull(StringUtils.EMPTY + gts[2]));
                            Double priorVal = Double.valueOf(isNull(StringUtils.EMPTY + obj[2 + ((j + 1) * 9)])) / Double.valueOf(isNull(StringUtils.EMPTY + gts[2 + ((j + 1) * 11)]));
                            Double perChange = ((value - priorVal) / priorVal);
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                        }
                    }
                }
            }

        }

        return pvDTO;
    }

    public ProjectionVarianceDTO getContractSales(final List<Object> gtsList, final List<Object> dataList, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE)) {
            pvDTO.setGroup(Constant.PVVariables.VAR_CONTRACT_SALES.toString().concat(Constant.VALUE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_CONTRACT_SALES.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
            pvDTO.setGroup(Constant.PVVariables.VAR_CONTRACT_SALES.toString().concat(Constant.VARIANCE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_CONTRACT_SALES.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.VAR_CONTRACT_SALES.toString().concat(Constant.CHANGE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_CONTRACT_SALES.toString().concat(Constant.CHANGE));
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[8])));
                        String baseValue = getFormattedValue(AMOUNT, value);

                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {

                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[8 + ((j + 1) * 20)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[8 + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[8])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[8 + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[8])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else {

            if (dataList != null && !dataList.isEmpty()) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2 + ((j + 1) * 14)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[2 + ((j + 1) * 14)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[2 + ((j + 1) * 14)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[2])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        }

        return pvDTO;
    }

    /**
     * Units
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getContractUnits(final List<Object> gtsList, final List<Object> dataList, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE)) {
            pvDTO.setGroup(Constant.PVVariables.VAR_CONTRACT_UNITS.toString().concat(Constant.VALUE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_CONTRACT_UNITS.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
            pvDTO.setGroup(Constant.PVVariables.VAR_CONTRACT_UNITS.toString().concat(Constant.VARIANCE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_CONTRACT_UNITS.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.VAR_CONTRACT_UNITS.toString().concat(Constant.CHANGE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_CONTRACT_UNITS.toString().concat(Constant.CHANGE));
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[9])));
                        String baseValue = getFormattedValue(AMOUNT_UNITS, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[9 + ((j + 1) * 20)])));
                            String val = getFormattedValue(AMOUNT_UNITS, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[9 + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[9])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT_UNITS, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[9 + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[9])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else {
            if (dataList != null && !dataList.isEmpty()) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[3])));
                        String baseValue = getFormattedValue(AMOUNT_UNITS, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[3 + ((j + 1) * 14)])));
                            String val = getFormattedValue(AMOUNT_UNITS, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[3 + ((j + 1) * 14)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[3])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT_UNITS, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[3 + ((j + 1) * 14)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[3])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        }

        return pvDTO;
    }

    /**
     *
     * @param gtsList
     * @param dataList
     * @param pvsdto
     * @param parentDto
     * @return
     */
    public ProjectionVarianceDTO getDiscountDol(final List<Object> gtsList, final List<Object> dataList, PVSelectionDTO pvsdto, ProjectionVarianceDTO parentDto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE)) {
            pvDTO.setGroup(Constant.PVVariables.VAR_DIS_AMOUNT.toString().concat(Constant.VALUE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_DIS_AMOUNT.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
            pvDTO.setGroup(Constant.PVVariables.VAR_DIS_AMOUNT.toString().concat(Constant.VARIANCE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_DIS_AMOUNT.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.VAR_DIS_AMOUNT.toString().concat(Constant.CHANGE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_DIS_AMOUNT.toString().concat(Constant.CHANGE));
        }
        if (pvsdto.isDiscountFlag()) {
            discountLevelMap.put(parentDto.getHierarchyNo(), dataList);
            pvDTO.setParent(1);
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());

        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[14])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[14 + ((j + 1) * 20)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[14 + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[14])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[14 + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[14])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else {
            if (dataList != null && !dataList.isEmpty()) {

                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5 + ((j + 1) * 14)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[5 + ((j + 1) * 14)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[5 + ((j + 1) * 14)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[5])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        }

        return pvDTO;
    }

    /**
     * Discount Per
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getDiscountPer(final List<Object> gtsList, final List<Object> dataList, PVSelectionDTO pvsdto, ProjectionVarianceDTO parentDto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE)) {
            pvDTO.setGroup(Constant.PVVariables.VAR_DIS_RATE.toString().concat(Constant.VALUE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_DIS_RATE.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
            pvDTO.setGroup(Constant.PVVariables.VAR_DIS_RATE.toString().concat(Constant.VARIANCE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_DIS_RATE.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.VAR_DIS_RATE.toString().concat(Constant.CHANGE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_DIS_RATE.toString().concat(Constant.CHANGE));
        }
        if (pvsdto.isDiscountFlag()) {
            discountLevelMap.put(parentDto.getHierarchyNo(), dataList);
            pvDTO.setParent(1);
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[15])));
                        String baseValue = getFormattedValue(RATE_PER, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue + PERCENT);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[15 + ((j + 1) * 20)])));
                            String val = getFormattedValue(RATE_PER, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[15 + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[15])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(RATE_PER, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[15 + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[15])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else {
            if (dataList != null && !dataList.isEmpty()) {

                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4])));
                        String baseValue = getFormattedValue(RATE_PER, value);

                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue + PERCENT);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4 + ((j + 1) * 14)])));
                            String val = getFormattedValue(RATE_PER, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[4 + ((j + 1) * 14)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(RATE_PER, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[4 + ((j + 1) * 14)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[4])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }

        }
        return pvDTO;
    }

    /**
     * Discount Per
     *
     * @param pvsdto
     * @return
     */
    private ProjectionVarianceDTO getNetSales(final List<Object> gtsList, final List<Object> dataList, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE)) {
            pvDTO.setGroup(Constant.PVVariables.VAR_NETSALES.toString().concat(Constant.VALUE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_NETSALES.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
            pvDTO.setGroup(Constant.PVVariables.VAR_NETSALES.toString().concat(Constant.VARIANCE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_NETSALES.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.VAR_NETSALES.toString().concat(Constant.CHANGE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_NETSALES.toString().concat(Constant.CHANGE));
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[16])));
                        String baseValue = getFormattedValue(AMOUNT, value);

                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[16 + ((j + 1) * 20)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[16 + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[16])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[16 + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[16])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else {
            if (dataList != null && !dataList.isEmpty()) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6])));
                        String baseValue = getFormattedValue(AMOUNT, value);

                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6 + ((j + 1) * 14)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[6 + ((j + 1) * 14)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[6 + ((j + 1) * 14)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[6])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }

        }
        return pvDTO;
    }

    ProjectionVarianceDTO setDataObjects(ProjectionVarianceDTO dto, ProjectionVarianceDTO parentDto, PVSelectionDTO pvsdto) {

        dto.setLevel(parentDto.getLevelValue());

        dto.setLevelNo((parentDto.getLevelNo()));
        dto.setTreeLevelNo(parentDto.getTreeLevelNo());
        dto.setParentNode(parentDto.getParentNode());
        dto.setLevelValue(parentDto.getLevelValue());
        dto.setHierarchyIndicator(parentDto.getHierarchyIndicator());
        dto.setHierarchyNo(parentDto.getHierarchyNo());
        if (dto.getHierarchyIndicator().equals(C)) {
            dto.setCustomerHierarchyNo(dto.getHierarchyNo());
            dto.setProductHierarchyNo(pvsdto.getProductHierarchyNo());
        } else if (dto.getHierarchyIndicator().equals(P)) {
            dto.setProductHierarchyNo(dto.getHierarchyNo());
            dto.setCustomerHierarchyNo(pvsdto.getCustomerHierarchyNo());
        }
        return dto;
    }

    /**
     * Discount Per
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getManSuppDiscountPer(final List<Object> gtsList, final List<Object> dataList, PVSelectionDTO pvsdto, String groupName, int no, int queryColNo, ProjectionVarianceDTO parentDto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE)) {
            pvDTO.setGroup(groupName + " Discount % Value");
            pvDTO.setRelationshipLevelName(groupName + " Discount % Value");
        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
            pvDTO.setGroup(groupName + " Discount % Variance");
            pvDTO.setRelationshipLevelName(groupName + " Discount % Variance");
        } else {
            pvDTO.setGroup(groupName + " Discount % % Change");
            pvDTO.setRelationshipLevelName(groupName + " Discount % % Change");
        }

        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[no])));
                        String baseValue = getFormattedValue(RATE_PER, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue + PERCENT);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[no + ((j + 1) * 11)])));
                            String val = getFormattedValue(RATE_PER, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[no + ((j + 1) * 11)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[no])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(RATE_PER, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[no + ((j + 1) * 11)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[no])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else {
            if (pvsdto.isDiscountFlag()) {
                pvDTO.setParent(1);
            }
            if (dataList != null && !dataList.isEmpty()) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[queryColNo])));
                        String baseValue = getFormattedValue(RATE_PER, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue + PERCENT);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[queryColNo + ((j + 1) * 9)])));
                            String val = getFormattedValue(RATE_PER, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[queryColNo + ((j + 1) * 9)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[queryColNo])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(RATE_PER, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[queryColNo + ((j + 1) * 9)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[queryColNo])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }

        }
        setDataObjects(pvDTO, parentDto, pvsdto);

        return pvDTO;
    }

    /**
     * Discount Per
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getManSuppRPU(final List<Object> gtsList, final List<Object> dataList, PVSelectionDTO pvsdto, String groupName, int no, int queryColNo, ProjectionVarianceDTO parentDto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE)) {
            pvDTO.setGroup(groupName + " RPU Value");
            pvDTO.setRelationshipLevelName(groupName + " RPU Value");
        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
            pvDTO.setGroup(groupName + " RPU Variance");
            pvDTO.setRelationshipLevelName(groupName + " RPU Variance");
        } else {
            pvDTO.setGroup(groupName + " RPU % Change");
            pvDTO.setRelationshipLevelName(groupName + " RPU % Change");
        }

        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[no])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[no + ((j + 1) * 11)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[no + ((j + 1) * 11)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[no])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[no + ((j + 1) * 11)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[no])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else {
            if (pvsdto.isDiscountFlag()) {
                pvDTO.setParent(1);
            }
            if (dataList != null && !dataList.isEmpty()) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[queryColNo])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[queryColNo + ((j + 1) * 9)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[queryColNo + ((j + 1) * 9)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[queryColNo])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[queryColNo + ((j + 1) * 9)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[queryColNo])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }

        }
        setDataObjects(pvDTO, parentDto, pvsdto);

        return pvDTO;
    }

    /**
     *
     * @param gtsList
     * @param dataList
     * @param pvsdto
     * @param groupName
     * @param no
     * @param queryColNo
     * @param parentDto
     * @return
     */
    public ProjectionVarianceDTO getManSuppDiscountAmount(final List<Object> gtsList, final List<Object> dataList, PVSelectionDTO pvsdto, String groupName, int no, int queryColNo, ProjectionVarianceDTO parentDto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE)) {

            pvDTO.setGroup(groupName + " Discount $ Value");
            pvDTO.setRelationshipLevelName(groupName + " Discount $ Value");
        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
            pvDTO.setGroup(groupName + " Discount $ Variance");
            pvDTO.setRelationshipLevelName(groupName + " Discount $ Variance");
        } else {
            pvDTO.setGroup(groupName + " Discount $ % Change");
            pvDTO.setRelationshipLevelName(groupName + " Discount $ % Change");
        }

        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<>(pvsdto.getProjIdList());

        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[no])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[no + ((j + 1) * 20)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[no + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[no])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[no + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[no])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else {
            if (pvsdto.isDiscountFlag()) {
                pvDTO.setParent(1);
            }
            if (dataList != null && !dataList.isEmpty()) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[queryColNo])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[queryColNo + ((j + 1) * 14)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[queryColNo + ((j + 1) * 14)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[queryColNo])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[queryColNo + ((j + 1) * 14)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[queryColNo])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        }
        setDataObjects(pvDTO, parentDto, pvsdto);
        return pvDTO;
    }

    public List<Object> getDetailsPeriodProgramCode(final PVSelectionDTO pvsdto, final ProjectionVarianceDTO parentDto) {
        pvsdto.setYear("ALL");
        pvsdto.setProjectionId(pvsdto.getCurrentProjectionID());
        List<Object> programCodequeryList = new ArrayList<Object>();
        if (Constant.DETAIL.equals(pvsdto.getLevel()) && Constant.COMPONENT.equals(pvsdto.getDiscountLevel()) && (pvsdto.isVarDisAmount() || pvsdto.isVarDisRate() || pvsdto.isVarRPU())) {
            String programCodequery = getCCPProgramCodeTempTableQuery(pvsdto) + getCCPQueryForProgramCode(pvsdto) + " \n" + getPivotProgramCodeQuery(pvsdto, true);
            if (!pvsdto.getProjIdList().isEmpty()) {
                programCodequery += getPivotProgramCodeQuery(pvsdto, false);
            }
            programCodequery += getDeclarationQuery();
            programCodequeryList = (List<Object>) CommonLogic.executeSelectQuery(programCodequery, null, null);
        }
        return programCodequeryList;
    }

    /**
     * Discount Per
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getDollarProgramCode(final List<Object> dataList, PVSelectionDTO pvsdto, String groupName, int queryColNo, int pcSize) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE)) {
            pvDTO.setGroup(groupName);
            pvDTO.setRelationshipLevelName(groupName);
        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
            pvDTO.setGroup(groupName);
            pvDTO.setRelationshipLevelName(groupName);
        } else {
            pvDTO.setGroup(groupName);
            pvDTO.setRelationshipLevelName(groupName);
        }

        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                String commonColumn = StringUtils.EMPTY;
                if (frequencyDivision == 4) {
                    commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 2) {
                    commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 12) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                    commonColumn = monthName + obj[0];
                }
                int code = queryColNo + (pcSize * 6);
                if (pvsdto.getVarIndicator().equals(VALUE)) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[queryColNo])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);

                }

                for (int j = 0; j < priorList.size(); j++) {
                    if (j == 0) {
                        code = queryColNo + (pcSize * 6);
                    } else {
                        code = code + ((pcSize * 6));
                    }

                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[code])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                        String priorVal = StringUtils.EMPTY + obj[code];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[queryColNo])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(AMOUNT, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else {
                        String priorVal = StringUtils.EMPTY + obj[code];
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[queryColNo])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE_PER, change);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                    }
                }
            }
        }

        return pvDTO;
    }

    /**
     * Discount Per
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getPercentProgramCode(final List<Object> dataList, PVSelectionDTO pvsdto, String groupName, int queryColNo, int pcSize) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE)) {
            pvDTO.setGroup(groupName);
            pvDTO.setRelationshipLevelName(groupName);
        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
            pvDTO.setGroup(groupName);
            pvDTO.setRelationshipLevelName(groupName);
        } else {
            pvDTO.setGroup(groupName);
            pvDTO.setRelationshipLevelName(groupName);
        }

        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                String commonColumn = StringUtils.EMPTY;
                if (frequencyDivision == 4) {
                    commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 2) {
                    commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 12) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                    commonColumn = monthName + obj[0];
                }
                if (pvsdto.getVarIndicator().equals(VALUE)) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[queryColNo])));
                    String baseValue = getFormattedValue(RATE_PER, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT, baseValue + PERCENT);

                }
                int code = queryColNo + (pcSize * 6);
                for (int j = 0; j < priorList.size(); j++) {
                    if (j == 0) {
                        code = queryColNo + (pcSize * 6);
                    } else {
                        code = code + ((pcSize * 6));
                    }

                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[code])));
                        String val = getFormattedValue(RATE_PER, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                    } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                        String priorVal = StringUtils.EMPTY + obj[code];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[queryColNo])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(RATE_PER, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                    } else {
                        String priorVal = StringUtils.EMPTY + obj[code];
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[queryColNo])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE_PER, change);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                    }
                }
            }
        }

        return pvDTO;
    }

    public List getpcNames(PVSelectionDTO projectionVarianceSelectionDTO, ProjectionVarianceDTO projectionVarianceDTO, String parentName, String levelNo, String currentOrPrior, boolean isNetSales, boolean isProgramCodeCount) throws PortalException, Exception {
        int projectionId = 0;
        int projectionIdCCP = 0;
        if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(currentOrPrior)) {
            projectionIdCCP = projectionVarianceSelectionDTO.getSession().getProjectionId();
            projectionId = projectionVarianceSelectionDTO.getProjectionId();
        } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(currentOrPrior)) {
            projectionIdCCP = projectionVarianceSelectionDTO.getSession().getProjectionId();
            projectionId = projectionIdCCP;
        }
        String userID = projectionVarianceSelectionDTO.getSession().getUserId();
        String sessionID = projectionVarianceSelectionDTO.getSession().getSessionId();
        String hierarchyNo = projectionVarianceDTO.getHierarchyNo();
        Map<Integer, String> freq = new HashMap<Integer, String>();
        freq.put(1, "\"YEAR\"");
        freq.put(2, "SEMI_ANNUAL");
        freq.put(4, "QUARTER");
        freq.put(12, "\"MONTH\"");
        String frequency = freq.get(projectionVarianceSelectionDTO.getFrequencyDivision());
        String hierarchy = StringUtils.EMPTY;
        String customerLevelNo = StringUtils.EMPTY;
        String prodLevelNo = StringUtils.EMPTY;
        String customerHierarNo = StringUtils.isBlank(String.valueOf(projectionVarianceDTO.getCustomerHierarchyNo())) ? StringUtils.EMPTY : String.valueOf(projectionVarianceDTO.getCustomerHierarchyNo());
        String prodHierarNo = StringUtils.isBlank(String.valueOf(projectionVarianceDTO.getProductHierarchyNo())) ? StringUtils.EMPTY : String.valueOf(projectionVarianceDTO.getProductHierarchyNo());
        String levelValue = parentName;
        int customID = projectionVarianceSelectionDTO.getCustomId();
        int customLevelNo = projectionVarianceDTO.getTreeLevelNo();
        String hierarchyIndicator = projectionVarianceDTO.getHierarchyIndicator().trim();

        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(hierarchyIndicator)) {
            customerLevelNo = StringUtils.isBlank(String.valueOf(customLevelNo)) ? Constant.PERCENT : String.valueOf(customLevelNo);
            prodLevelNo = Constant.PERCENT;
        } else {
            customerLevelNo = Constant.PERCENT;
            prodLevelNo = StringUtils.isBlank(String.valueOf(customLevelNo)) ? Constant.PERCENT : String.valueOf(customLevelNo);
        }
        if (projectionVarianceSelectionDTO.isIsCustomHierarchy()) {
            levelNo = String.valueOf(projectionVarianceSelectionDTO.getLevelNo());
        }
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(hierarchyIndicator)) {
            hierarchy = "PROJECTION_CUST_HIERARCHY";
        } else {
            hierarchy = "PROJECTION_PROD_HIERARCHY";
        }
        String customSQL;
        if (isProgramCodeCount) {
            customSQL = " SELECT DISTINCT CM.CONTRACT_NO \n";

        } else {

            customSQL = "SELECT\n"
                    + "       '" + levelNo + "' AS LEVEL_NO,\n"
                    + "       'LEVEL_NAME' AS LEVEL_NAME,\n";
            if (projectionVarianceSelectionDTO.getGroup().contains(Constant.MANDATED_DISCOUNT) || projectionVarianceSelectionDTO.getGroup().contains("Supplemental Discount")
                    || projectionVarianceSelectionDTO.getGroup().contains("Mandated RPU") || projectionVarianceSelectionDTO.getGroup().contains("Supplemental RPU")) {
                customSQL += "       CM." + projectionVarianceSelectionDTO.getColumnName() + ",\n";
            } else {
                customSQL += "   'Value' AS RELATIONSHIP_LEVEL_VALUES,\n";
            }

            customSQL += " CCP.HIERARCHY_NO,\n"
                    + "p.\"YEAR\",\n"
                    + "       Sum(MAD.PROJECTION_SALES)        AS  Disc_Amt,\n"
                    + "Coalesce(Sum(MAD.PROJECTION_SALES)/Nullif(Sum(m_ac.PROJECTION_SALES),0),0)*100 AS Man_Disc_Rate,"
                    + "       Sum(SPMA.PROJECTION_SALES)        AS  Supp_Amt,\n"
                    + "Coalesce(Sum(SPMA.PROJECTION_SALES)/Nullif(Sum(m_ac.PROJECTION_SALES),0),0)*100 AS Supp_Disc_Rate,"
                    + "       1                            As ActualProj\n";
            if (!"\"YEAR\"".equalsIgnoreCase(frequency)) {
                customSQL += "         , p." + frequency + "\n";
            }
            if (isNetSales) {
                customSQL += ", Sum(m_ac.PROJECTION_SALES) \n";
            }
        }

        customSQL += "FROM   projection_details pd\n";

        if (!projectionVarianceSelectionDTO.isIsCustomHierarchy()) {
            customSQL += "JOIN   (SELECT DISTINCT\n"
                    + "               LCCP.RELATIONSHIP_LEVEL_SID,\n"
                    + "               LCCP.CCP_DETAILS_SID,\n"
                    + "               LCCP.HIERARCHY_NO\n"
                    + "        FROM   (SELECT CCPMAP.CCP_DETAILS_SID,\n"
                    + "                       HLD.HIERARCHY_NO,\n"
                    + "                       HLD.RELATIONSHIP_LEVEL_SID\n"
                    + "                FROM   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES,\n"
                    + "                               RLD.HIERARCHY_NO,\n"
                    + "                               CCP.CCP_DETAILS_SID\n"
                    + "                        FROM   RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                    + "                        JOIN   CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID\n"
                    + "                        JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                    + "                                                    AND PD.PROJECTION_MASTER_SID = " + projectionIdCCP + "\n"
                    + "                        JOIN   PROJECTION_MASTER PM ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                    + "                        WHERE  PM.PROJECTION_MASTER_SID = " + projectionIdCCP + ") CCPMAP,\n"
                    + "                       (SELECT RLD1.HIERARCHY_NO,\n"
                    + "                               RLD1.RELATIONSHIP_LEVEL_SID\n"
                    + "                        FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1\n"
                    + "                        JOIN   " + hierarchy + " PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID\n"
                    + "                                                            AND PCH.PROJECTION_MASTER_SID = " + projectionIdCCP + "\n"
                    + "                        WHERE  RLD1.HIERARCHY_NO LIKE '" + hierarchyNo + "%') HLD\n"
                    + "                WHERE  CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO + '%') LCCP\n"
                    + "        WHERE  LCCP.HIERARCHY_NO IN (SELECT RLD2.HIERARCHY_NO\n"
                    + "                                     FROM   RELATIONSHIP_LEVEL_DEFINITION RLD2\n"
                    + "                                     JOIN   " + hierarchy + " PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID = RLD2.RELATIONSHIP_LEVEL_SID\n"
                    + "                                                                          AND PCH2.PROJECTION_MASTER_SID = " + projectionIdCCP + "\n"
                    + "                                     WHERE  RLD2.LEVEL_NO = " + levelNo + ")) CCP ON CCP.CCP_DETAILS_SID = pd.CCP_DETAILS_SID\n";
        } else {
            customSQL += "JOIN    (SELECT distinct HLD" + hierarchyIndicator + ".RELATIONSHIP_LEVEL_SID,HLD" + hierarchyIndicator + ".HIERARCHY_NO, CCPMAPC.CCP_DETAILS_SID FROM\n"
                    + "  (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                    + "  JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID\n"
                    + "  JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionIdCCP + "\n"
                    + "  ) CCPMAPC\n"
                    + "  JOIN\n"
                    + "  (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                    + "  JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID\n"
                    + "  JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionIdCCP + "\n"
                    + "  ) CCPMAPP ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID\n"
                    + " JOIN  (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD\n"
                    + "  JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVD.CUSTOM_VIEW_MASTER_SID='" + customID + "'  AND CVD.LEVEL_NO  like '" + customerLevelNo + "'\n"
                    + "  JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                    + "  JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID\n"
                    + "  JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionIdCCP + "\n"
                    + "  WHERE RLD2.HIERARCHY_NO like '" + customerHierarNo + "%') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'\n"
                    + " JOIN    (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD\n"
                    + "  JOIN dbo.CUSTOM_VIEW_MASTER CVM ON\n"
                    + "  CVD.CUSTOM_VIEW_MASTER_SID='" + customID + "' AND CVD.LEVEL_NO like '" + prodLevelNo + "'\n"
                    + "  JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                    + "  JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID\n"
                    + "  JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionIdCCP + "\n"
                    + "  WHERE RLD2.HIERARCHY_NO like '" + prodHierarNo + "%' ) HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'\n"
                    + "  )CCP ON CCP.CCP_DETAILS_SID = pd.CCP_DETAILS_SID\n";
        }

        if (projectionVarianceSelectionDTO.getGroup().contains(Constant.MANDATED_DISCOUNT) || projectionVarianceSelectionDTO.getGroup().contains("Supplemental Discount")
                || projectionVarianceSelectionDTO.getGroup().contains("Mandated RPU") || projectionVarianceSelectionDTO.getGroup().contains("Supplemental RPU")) {
            customSQL += "JOIN CCP_DETAILS CCD on CCD.CCP_DETAILS_SID = pd.CCP_DETAILS_SID \n"
                    + "JOIN   CONTRACT_MASTER CM on CM.CONTRACT_MASTER_SID = CCD.CONTRACT_MASTER_SID \n";
        }
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(currentOrPrior)) {
            customSQL += "JOIN   ST_M_SALES_PROJECTION_MASTER m_mas ON pd.PROJECTION_DETAILS_SID = m_mas.PROJECTION_DETAILS_SID\n"
                    + "                                         AND m_mas.user_id = " + userID + "\n"
                    + "                                         AND m_mas.session_id = " + sessionID + "\n"
                    + "JOIN   ST_M_SALES_PROJECTION m_ac ON m_mas.PROJECTION_DETAILS_SID = m_ac.PROJECTION_DETAILS_SID\n"
                    + "                             AND m_ac.user_id = m_mas.user_id\n"
                    + "                             AND m_ac.session_id = m_mas.session_id\n"
                    + "JOIN   M_DISCOUNT_PROJECTION MAD ON MAD.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID\n"
                    + "AND MAD.PERIOD_SID=m_ac.PERIOD_SID\n"
                    + "                            AND MAD.user_id = m_mas.user_id\n"
                    + "                            AND MAD.session_id = m_mas.session_id\n"
                    + "LEFT JOIN   ST_M_SUPPLEMENTAL_DISC_MASTER SPM ON SPM.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID\n"
                    + "\n"
                    + "                                        AND SPM.user_id = m_mas.user_id\n"
                    + "                                        AND SPM.session_id = m_mas.session_id\n";

            customSQL += "LEFT JOIN   ST_M_SUPPLEMENTAL_DISC_PROJ SPMA ON SPMA.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID\n";

            customSQL += "    AND SPMA.PERIOD_SID=m_ac.PERIOD_SID\n";

            customSQL += " AND SPMA.user_id = m_mas.user_id\n"
                    + " AND SPMA.session_id = m_mas.session_id\n";

            customSQL += "JOIN   PERIOD p ON p.period_sid = m_ac.PERIOD_SID\n"
                    + "WHERE  pd.PROJECTION_MASTER_SID = " + projectionId + "\n"
                    + "   AND m_mas.USER_ID = " + userID + "\n"
                    + "   AND m_mas.SESSION_ID = " + sessionID + "\n"
                    + "   AND m_ac.USER_ID = " + userID + "\n"
                    + "   AND m_ac.SESSION_ID = " + sessionID + "\n";
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(currentOrPrior)) {
            customSQL += "JOIN      M_SALES_PROJECTION_MASTER m_mas ON pd.PROJECTION_DETAILS_SID = m_mas.PROJECTION_DETAILS_SID\n"
                    + "JOIN      M_SALES_PROJECTION m_ac ON m_mas.PROJECTION_DETAILS_SID = m_ac.PROJECTION_DETAILS_SID\n"
                    + "LEFT JOIN      M_DISCOUNT_PROJECTION MAD ON MAD.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID\n"
                    + "                                   AND MAD.PERIOD_SID = m_ac.PERIOD_SID\n"
                    + "                                   AND MAD.SAVE_FLAG = 1\n"
                    + "LEFT JOIN M_SUPPLEMENTAL_DISC_MASTER SPM ON SPM.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID\n"
                    + "LEFT JOIN M_SUPPLEMENTAL_DISC_PROJ SPMA ON SPMA.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID\n"
                    + "                                       AND SPMA.PERIOD_SID = m_ac.PERIOD_SID\n"
                    + "JOIN      PERIOD p ON p.period_sid = m_ac.PERIOD_SID\n"
                    + "WHERE     pd.PROJECTION_MASTER_SID = " + projectionId + "\n";

        }

        if (!isProgramCodeCount) {
            customSQL += "GROUP  BY ";
            if (projectionVarianceSelectionDTO.getGroup().contains(Constant.MANDATED_DISCOUNT) || projectionVarianceSelectionDTO.getGroup().contains("Supplemental Discount")
                    || projectionVarianceSelectionDTO.getGroup().contains("Mandated RPU") || projectionVarianceSelectionDTO.getGroup().contains("Supplemental RPU")) {
                customSQL += " CM." + projectionVarianceSelectionDTO.getColumnName() + ",\n";
            }
            customSQL += "CCP.HIERARCHY_NO,\n";
            customSQL += "  p.\"YEAR\"\n";
            if (!"\"YEAR\"".equalsIgnoreCase(frequency)) {
                customSQL += " ,p." + frequency + "\n";
            }

            customSQL += "Order By ";
            if (projectionVarianceSelectionDTO.getGroup().contains(Constant.MANDATED_DISCOUNT) || projectionVarianceSelectionDTO.getGroup().contains("Supplemental Discount")
                    || projectionVarianceSelectionDTO.getGroup().contains("Mandated RPU") || projectionVarianceSelectionDTO.getGroup().contains("Supplemental RPU")) {
                customSQL += " CM." + projectionVarianceSelectionDTO.getColumnName() + ",\n";
            }
            customSQL += "p.\"YEAR\"\n";
            if (!"\"YEAR\"".equalsIgnoreCase(frequency)) {
                customSQL += "          ,p." + frequency + "\n";
            }
        }
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        List<Object> resultList = (List) salesProjectionDAO.executeSelectQuery(customSQL);
        return resultList;
    }

    public void saveMPVSelection(Map map, int projectionID, String screenName) {
        List<MProjectionSelection> list = new ArrayList<MProjectionSelection>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(MProjectionSelection.class);
        query.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionID));
        query.add(RestrictionsFactoryUtil.eq(Constant.SCREEN_NAME, screenName));

        try {
            list = MProjectionSelectionLocalServiceUtil.dynamicQuery(query);

            if (list.isEmpty()) {
                commonLogic.saveSelection(map, projectionID, screenName, Constant.SAVE, "M_PROJECTION_SELECTION");
            } else {
                commonLogic.saveSelection(map, projectionID, screenName, Constant.UPDATE, "M_PROJECTION_SELECTION");
            }
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private ProjectionVarianceDTO getExFactorySales(List<Object> dataList, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE)) {
            pvDTO.setGroup(Constant.PVVariables.EX_FACTORY_SALES.toString().concat(Constant.VALUE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.EX_FACTORY_SALES.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
            pvDTO.setGroup(Constant.PVVariables.EX_FACTORY_SALES.toString().concat(Constant.VARIANCE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.EX_FACTORY_SALES.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.EX_FACTORY_SALES.toString().concat(Constant.CHANGE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.EX_FACTORY_SALES.toString().concat(Constant.CHANGE));
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {
            if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);

                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {

                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2])));
                        String baseValue = getFormattedValue(AMOUNT, value);

                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2 + ((j + 1) * 20)]))); // need to check here 
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[2 + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[2 + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[2])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            } else {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2])));
                        String baseValue = getFormattedValue(AMOUNT, value);

                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2 + ((j + 1) * 20)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[2 + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[2 + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[2])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        }
        return pvDTO;
    }

    private ProjectionVarianceDTO getVarDemandSales(List<Object> dataList, PVSelectionDTO pvsdto) {

        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE)) {
            pvDTO.setGroup(Constant.PVVariables.DEMAND_SALES.toString().concat(Constant.VALUE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.DEMAND_SALES.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
            pvDTO.setGroup(Constant.PVVariables.DEMAND_SALES.toString().concat(Constant.VARIANCE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.DEMAND_SALES.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.DEMAND_SALES.toString().concat(Constant.CHANGE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.DEMAND_SALES.toString().concat(Constant.CHANGE));
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {
            if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);

                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {

                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[3])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[3 + ((j + 1) * 20)]))); // need to check here 
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[3 + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[3])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[3 + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[3])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            } else {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[3])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[3 + ((j + 1) * 20)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[3 + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[3])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[3 + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[3])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        }
        return pvDTO;

    }

    private ProjectionVarianceDTO getVarInvSales(List<Object> dataList, PVSelectionDTO pvsdto) {

        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        switch (pvsdto.getVarIndicator()) {
            case VALUE:
                pvDTO.setGroup(Constant.PVVariables.INVENTORY_SALES.toString().concat(Constant.VALUE));
                pvDTO.setRelationshipLevelName(Constant.PVVariables.INVENTORY_SALES.toString().concat(Constant.VALUE));
                break;
            case VARIANCE:
                pvDTO.setGroup(Constant.PVVariables.INVENTORY_SALES.toString().concat(Constant.VARIANCE));
                pvDTO.setRelationshipLevelName(Constant.PVVariables.INVENTORY_SALES.toString().concat(Constant.VARIANCE));
                break;
            default:
                pvDTO.setGroup(Constant.PVVariables.INVENTORY_SALES.toString().concat(Constant.CHANGE));
                pvDTO.setRelationshipLevelName(Constant.PVVariables.INVENTORY_SALES.toString().concat(Constant.CHANGE));
                break;
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {
            if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);

                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {

                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4 + ((j + 1) * 20)]))); // need to check here 
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[4 + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[4 + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[4])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            } else {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4 + ((j + 1) * 20)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[4 + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[4 + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[4])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        }
        return pvDTO;

    }

    private ProjectionVarianceDTO getNetProfit(List<Object> gtsList, List<Object> dataList, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        switch (pvsdto.getVarIndicator()) {
            case VALUE:
                pvDTO.setGroup(Constant.PVVariables.VAR_NET_PROFITE.toString().concat(Constant.VALUE));
                pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_NET_PROFITE.toString().concat(Constant.VALUE));
                break;
            case VARIANCE:
                pvDTO.setGroup(Constant.PVVariables.VAR_NET_PROFITE.toString().concat(Constant.VARIANCE));
                pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_NET_PROFITE.toString().concat(Constant.VARIANCE));
                break;
            default:
                pvDTO.setGroup(Constant.PVVariables.VAR_NET_PROFITE.toString().concat(Constant.CHANGE));
                pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_NET_PROFITE.toString().concat(Constant.CHANGE));
                break;
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[21])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[21 + ((j + 1) * 20)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[21 + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[21])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[21 + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[21])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else {
            if (dataList != null && !dataList.isEmpty()) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[15])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[15 + ((j + 1) * 14)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[15 + ((j + 1) * 14)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[15])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[15 + ((j + 1) * 14)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[15])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }

        }
        return pvDTO;
    }

    private ProjectionVarianceDTO getCOGC(List<Object> gtsList, List<Object> dataList, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE)) {
            pvDTO.setGroup(Constant.PVVariables.VAR_COGS.toString().concat(Constant.VALUE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_COGS.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
            pvDTO.setGroup(Constant.PVVariables.VAR_COGS.toString().concat(Constant.VARIANCE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_COGS.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.VAR_COGS.toString().concat(Constant.CHANGE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_COGS.toString().concat(Constant.CHANGE));
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[20])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[20 + ((j + 1) * 20)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[20 + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[20])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[20 + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[20])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else {
            if (dataList != null && !dataList.isEmpty()) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[14])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[14 + ((j + 1) * 14)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[14 + ((j + 1) * 14)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[14])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[14 + ((j + 1) * 14)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[14])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }

        }
        return pvDTO;
    }

    private ProjectionVarianceDTO getRPU(List<Object> gtsList, List<Object> dataList, PVSelectionDTO pvsdto, ProjectionVarianceDTO parentDto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE)) {
            pvDTO.setGroup(Constant.PVVariables.VAR_RPU.toString().concat(Constant.VALUE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_RPU.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
            pvDTO.setGroup(Constant.PVVariables.VAR_RPU.toString().concat(Constant.VARIANCE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_RPU.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.VAR_RPU.toString().concat(Constant.CHANGE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.VAR_RPU.toString().concat(Constant.CHANGE));
        }
        if (pvsdto.isDiscountFlag()) {
            discountLevelMap.put(parentDto.getHierarchyNo(), dataList);
            pvDTO.setParent(1);
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[19])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[19 + ((j + 1) * 20)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[19 + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[19])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[19 + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[19])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else {
            if (dataList != null && !dataList.isEmpty()) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[11])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[11 + ((j + 1) * 14)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[11 + ((j + 1) * 14)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[11])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[11 + ((j + 1) * 14)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[11])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }

        }
        return pvDTO;
    }

    public ProjectionVarianceDTO getPERExFactory(final List<Object> dataList, ProjectionVarianceDTO gtsDto, ProjectionVarianceDTO salesDto, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE)) {
            pvDTO.setGroup(Constant.PVVariables.PER_EX_FACTORY.toString().concat(Constant.VALUE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.PER_EX_FACTORY.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
            pvDTO.setGroup(Constant.PVVariables.PER_EX_FACTORY.toString().concat(Constant.VARIANCE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.PER_EX_FACTORY.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.PER_EX_FACTORY.toString().concat(Constant.CHANGE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.PER_EX_FACTORY.toString().concat(Constant.CHANGE));
        }

        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (dataList != null && !dataList.isEmpty()) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])));
                        String baseValue = getFormattedValue(RATE_PER, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue + PERCENT);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5 + ((j + 1) * 20)])));
                            String val = getFormattedValue(RATE_PER, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[5 + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(RATE_PER, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[5 + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[5])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])));
                        String baseValue = getFormattedValue(RATE_PER, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue+ PERCENT);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5 + ((j + 1) * 20)])));
                            String val = getFormattedValue(RATE_PER, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val+ PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[5 + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(RATE_PER, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val+ PERCENT);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[5 + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[5])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
        }
        return pvDTO;
    }

    private ProjectionVarianceDTO getPERDemand(final List<Object> dataList, ProjectionVarianceDTO gtsDto, ProjectionVarianceDTO salesDto, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE)) {
            pvDTO.setGroup(Constant.PVVariables.PER_DEMAND.toString().concat(Constant.VALUE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.PER_DEMAND.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
            pvDTO.setGroup(Constant.PVVariables.PER_DEMAND.toString().concat(Constant.VARIANCE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.PER_DEMAND.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.PER_DEMAND.toString().concat(Constant.CHANGE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.PER_DEMAND.toString().concat(Constant.CHANGE));
        }

        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (dataList != null && !dataList.isEmpty()) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6])));
                        String baseValue = getFormattedValue(RATE_PER, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue + PERCENT);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6 + ((j + 1) * 20)])));
                            String val = getFormattedValue(RATE_PER, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[6 + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(RATE_PER, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[6 + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[6])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6])));
                        String baseValue = getFormattedValue(RATE_PER, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue+ PERCENT);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6 + ((j + 1) * 20)])));
                            String val = getFormattedValue(RATE_PER, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val+ PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[6 + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(RATE_PER, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val+ PERCENT);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[6 + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[6])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
        }
        return pvDTO;
    }

    private ProjectionVarianceDTO getPERInvWithdrawal(final List<Object> dataList, ProjectionVarianceDTO gtsDto, ProjectionVarianceDTO salesDto, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE)) {
            pvDTO.setGroup(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString().concat(Constant.VALUE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
            pvDTO.setGroup(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString().concat(Constant.VARIANCE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString().concat(Constant.CHANGE));
            pvDTO.setRelationshipLevelName(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString().concat(Constant.CHANGE));
        }

        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (dataList != null && !dataList.isEmpty()) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[7])));
                        String baseValue = getFormattedValue(RATE_PER, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue + PERCENT);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[7 + ((j + 1) * 20)])));
                            String val = getFormattedValue(RATE_PER, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[7 + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[7])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(RATE_PER, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[7 + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[7])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[7])));
                        String baseValue = getFormattedValue(RATE_PER, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT, baseValue+ PERCENT);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[7 + ((j + 1) * 20)])));
                            String val = getFormattedValue(RATE_PER, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val+ PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[7 + ((j + 1) * 20)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[7])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(RATE_PER, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val+ PERCENT);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[7 + ((j + 1) * 20)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[7])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
        }
        return pvDTO;
    }
}
