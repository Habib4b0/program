/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnworkflow.logic;

import com.stpl.app.gtnworkflow.dto.InboxDashboardDTO;
import com.stpl.app.gtnworkflow.dto.UserViewDTO;
import com.stpl.app.gtnworkflow.dto.WorkFlowHistoryLookupDTO;
import com.stpl.app.gtnworkflow.util.AbstractFilterLogic;
import com.stpl.app.gtnworkflow.util.CommonUtils;
import com.stpl.app.gtnworkflow.util.HelperListUtil;
import com.stpl.app.gtnworkflow.util.QueryUtils;
import com.stpl.app.gtnworkflow.util.xmlparser.SQlUtil;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.HistWorkflowMaster;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CommonUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.ARMConstants;
import static com.stpl.ifs.util.constants.GlobalConstants.getSelectOne;
import com.stpl.ifs.util.constants.WorkflowConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.gtnworkflow.service.WorkflowImpl;
import com.stpl.app.gtnworkflow.util.ConstantsUtils;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.And;
import com.vaadin.v7.data.util.filter.Between;
import com.vaadin.v7.data.util.filter.Compare;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.ui.ComboBox;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 *
 * @author satheesh
 */
public class WorkflowLogic {

    public static Map<Integer, String> userMap = new ConcurrentHashMap<Integer, String>();
    public static final SimpleDateFormat DBDate = new SimpleDateFormat("yyyy-MM-dd");
    HelperListUtil helperListutil = HelperListUtil.getInstance();
    private static final Logger LOGGER = Logger.getLogger(WorkflowLogic.class);
    final SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
    public static final SimpleDateFormat DB_DATE = new SimpleDateFormat("yyyy-MM-dd");
    private static String bpSystemProperty = System.getProperty("businessProcess");

    public static List<HelperDTO> loadCompany() {

        DynamicQuery companyDynamicQuery = CompanyMasterLocalServiceUtil.dynamicQuery();
        companyDynamicQuery.add(RestrictionsFactoryUtil.ilike("companyType", "manufacturer"));
        ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property("companyMasterSid"));
        projList.add(ProjectionFactoryUtil.property("companyName"));
        companyDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));

        companyDynamicQuery.addOrder(OrderFactoryUtil.asc("companyName"));
        List<Object[]> resultList = new ArrayList<Object[]>();
        List<HelperDTO> results = new ArrayList<HelperDTO>();
        try {
            resultList = CompanyMasterLocalServiceUtil.dynamicQuery(companyDynamicQuery);
        } catch (SystemException e) {
            LOGGER.error(e);
        }
        for (Object[] obj : resultList) {
            HelperDTO dto = new HelperDTO();
            dto.setId((Integer) obj[0]);
            dto.setDescription(String.valueOf(obj[1]));
            results.add(dto);
        }
        return results;

    }

    /**
     * Method for finding the Search Results Using DYNAMIC Query including
     * Filter and Sorting
     *
     * @param inboxdto
     * @param start
     * @param offset
     * @param columns
     * @param isCount
     * @param filterSet
     * @return
     */
    public String getWorkflowSearchQuery(InboxDashboardDTO inboxdto, final int start, final int offset, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, boolean isCount) {
        LOGGER.debug("Inside Search results");
        String workflowId;
        String workflowName;
        String workflowDescription;
        String workflowIdBinder = inboxdto.getWorkflowId();
        String workflowDescriptionBinder = inboxdto.getWorkflowDescription();
        String workflowNameBinder = inboxdto.getWorkflowName();
        String statusBinder = String.valueOf(inboxdto.getStatus() != null ? inboxdto.getStatus().getId() : 0);
        String businessProcessBinder = inboxdto.getBusinessProcess();
        String createdByBinder = String.valueOf(inboxdto.getCreatedById());
        String approvedByBinder = String.valueOf(inboxdto.getApprovedById());
        Date createdFrom = inboxdto.getCreationDateRangeFrom() != null ? (Date) inboxdto.getCreationDateRangeFrom() : null;
        Date createdTo = inboxdto.getCreationDateRangeTo() != null ? (Date) inboxdto.getCreationDateRangeTo() : null;
        Date approvedFrom = inboxdto.getApprovedDateRangeFrom() != null ? (Date) inboxdto.getApprovedDateRangeFrom() : null;
        Date approvedTo = inboxdto.getApprovedDateRangeTo() != null ? (Date) inboxdto.getApprovedDateRangeTo() : null;
        int companyBinder = inboxdto.getCompanyValue();
        int businessUnitBinder = inboxdto.getBusinessUnitValue();

        String contractIdBinder = inboxdto.getContractId();
        String contractNoBinder = inboxdto.getContractNo();
        String contractNameBinder = inboxdto.getContractName();
        String contractTypeBinder = inboxdto.getContractType() != null ? String.valueOf(inboxdto.getContractType().getId()) : StringUtils.EMPTY;
        String companyIdBinder = inboxdto.getCompanyId();
        String companyNoBinder = inboxdto.getCompanyNo();
        String companyNameBinder = inboxdto.getCompanyName();
        String itemIdBinder = inboxdto.getItemId();
        String itemNoBinder = inboxdto.getItemNo();
        String itemNameBinder = inboxdto.getItemName();
        String deductionLevelBinder = inboxdto.getDeductionLevel();
        String deductionvalueBinder = inboxdto.getDeductionValue() != null ? String.valueOf(inboxdto.getDeductionValue()) : StringUtils.EMPTY;
        String projDetailsTable = StringUtils.EMPTY;
        String workflowStatusValueBinder = inboxdto.getWorkflowStatusValue() != null ? String.valueOf(inboxdto.getWorkflowStatusValue().getId()) : StringUtils.EMPTY;
        String brandIdBinder = inboxdto.getBrandId();
        String brandNameBinder = inboxdto.getBrandName();
        Date glDate = inboxdto.getGlDate() != null ? (Date) inboxdto.getGlDate() : null;
        String deductionNoBinder = inboxdto.getDeductionNo();
        String deductionNameBinder = inboxdto.getDeductionName();
        List adjustmentTypeBinder = inboxdto.getAdjustmentType();

        StringBuilder query = new StringBuilder();

        if (isCount) {
            query.append("SELECT count (*) from (");
        }

        query.append("SELECT DISTINCT WM.WORKFLOW_ID AS workflowId, @WFNAME AS workflowName, WM.WORKFLOW_DESCRPTION AS workflowDescription, HT_STATUS.DESCRIPTION AS workFlowStatus, \n"
                + "            WM.CREATED_BY as createdById, WM.CREATED_DATE as creationDate, WM.APPROVED_BY AS approvedById, WM.MODIFIED_DATE AS modifiedDate, \n"
                + "            WM.WORKFLOW_MASTER_SID as workflowMasterSystemId, WM.PROJECTION_MASTER_SID as masterSid,WM.CONTRACT_STRUCTURE as contractStructure,WM.NO_OF_APPROVAL as noOfApprovals,WM.APPROVAL_LEVEL as approvalLevel, wm.APPROVED_DATE as approvedDate  \n");

        if ("ARM".equals(businessProcessBinder)) {
            query.append(" , GL_COMp.COMPANY_NAME as company , BU_COMp.COMPANY_NAME as businessUnit,  ADJ_CONF.TRANSACTION_NAME as adjustmentTypeValue, TRANX_NAME.DESCRIPTION as adjustmentTypeName , CONF_NAME.DESCRIPTION as configurationType \n");
        } else if (!CommonUtils.CONTRACT_MANAGEMENT.equals(businessProcessBinder)) {
            query.append(", GL_COMp.COMPANY_NAME as company , BU_COMp.COMPANY_NAME as businessUnit, "
                    + "    PM.CUSTOMER_HIERARCHY_SID AS customerHierSid,\n"
                    + "    PM.CUSTOMER_HIERARCHY_LEVEL AS customerHierarchyLevel,\n"
                    + "    PM.CUST_RELATIONSHIP_BUILDER_SID AS custRelationshipBuilderSid,\n"
                    + "    PM.PRODUCT_HIERARCHY_LEVEL AS productHierarchyLevel,\n"
                    + "    PM.PROD_RELATIONSHIP_BUILDER_SID AS prodRelationshipBuilderSid");
        }
        query.append(" FROM WORKFLOW_MASTER WM JOIN dbo.HELPER_TABLE HT_STATUS on HT_STATUS.HELPER_TABLE_SID = WM.WORKFLOW_STATUS_ID ");

        if (CommonUtils.CONTRACT_MANAGEMENT.equals(businessProcessBinder)) {
            String sql = query.toString().replace(CommonUtils.WFNAME, "CM.CONTRACT_NAME");
            query = new StringBuilder(sql.toString().replace("WM.PROJECTION_MASTER_SID", "WM.CONTRACT_MASTER_SID"));
            query.append(" JOIN CONTRACT_MASTER CM on WM.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID ");
        } else {
            String sql = StringUtils.EMPTY;
            if ("ARM".equals(businessProcessBinder)) {
                sql = query.toString().replace(CommonUtils.WFNAME, CommonUtils.PM_PROJECTION_DESCRIPTION);
            } else {
                sql = query.toString().replace(CommonUtils.WFNAME, "PM.PROJECTION_NAME");
            }            
            query = new StringBuilder(sql.toString().replace("WM.WORKFLOW_DESCRPTION", CommonUtils.PM_PROJECTION_DESCRIPTION));
            query.append(" JOIN PROJECTION_MASTER PM on WM.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID ");
        }
        if ("ARM".equals(businessProcessBinder)) {
            query.append(" JOIN ARM_ADJUSTMENT_MASTER arm_M on arm_M.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID\n");
            query.append(" JOIN HELPER_TABLE CONF_NAME on CONF_NAME.HELPER_TABLE_SID = arm_M.CONFIGURATION_TYPE\n");
        }
        boolean isContractSearchCriteriaPresent = false;
        boolean isCompanySearchCriteriaPresent = false;
        boolean isItemSearchCriteriaPresent = false;
        boolean isDeductionSearchCriteriaPresent = false;

        StringBuilder additionalJoins = new StringBuilder(StringUtils.EMPTY);

        if (!contractIdBinder.isEmpty() || !contractNoBinder.isEmpty() || !contractNameBinder.isEmpty() || !contractTypeBinder.isEmpty()) {
            additionalJoins.append(" JOIN CONTRACT_MASTER CNM ON CNM.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID");
            isContractSearchCriteriaPresent = true;
        }
        if (!companyIdBinder.isEmpty() || !companyNoBinder.isEmpty() || !companyNameBinder.isEmpty()) {
            additionalJoins.append(" JOIN COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID");
            isCompanySearchCriteriaPresent = true;
        }
        if (!itemIdBinder.isEmpty() || !itemNoBinder.isEmpty() || !itemNameBinder.isEmpty() || "ARM".equals(businessProcessBinder)) {
            additionalJoins.append(" JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID");
            isItemSearchCriteriaPresent = true;
        }
        if (!deductionLevelBinder.isEmpty() && !deductionvalueBinder.isEmpty() && CommonUtils.FORECASTING.equals(businessProcessBinder)) {
            additionalJoins.append(" JOIN NM_DISCOUNT_PROJ_MASTER DP ON DP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID ");
            additionalJoins.append(" JOIN RS_MODEL RS ON RS.RS_MODEL_SID = DP.RS_MODEL_SID");
            isDeductionSearchCriteriaPresent = true;
        }
        if (!deductionLevelBinder.isEmpty() && !deductionvalueBinder.isEmpty() && CommonUtils.ACCRUAL_RATE_PROJECTION.equals(businessProcessBinder)) {
            additionalJoins.append(" join ACCRUAL_PROJ_SELECTION APS on APS.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID ");
            isDeductionSearchCriteriaPresent = true;
        }
        if (!deductionLevelBinder.isEmpty() && !deductionvalueBinder.isEmpty() && "ARM".equals(businessProcessBinder)) {
            additionalJoins.append(" JOIN ARM_ADJUSTMENT_MASTER AAM ON AAM.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID ");
            additionalJoins.append(" JOIN dbo.ARM_DEDUCTION_SELECTION ADS ON PM.PROJECTION_MASTER_SID = ADS.PROJECTION_MASTER_SID ");
            additionalJoins.append(" JOIN RS_CONTRACT RC ON RC.RS_CONTRACT_SID = ADS.RS_CONTRACT_SID ");
            isDeductionSearchCriteriaPresent = true;
        }

        if (!brandIdBinder.isEmpty() || !brandNameBinder.isEmpty()) {
            if (itemIdBinder.isEmpty() && itemNoBinder.isEmpty() && itemNameBinder.isEmpty() && !"ARM".equals(businessProcessBinder)) {
                additionalJoins.append(" JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID");
            }
            if("ARM".equals(businessProcessBinder))
            {
            additionalJoins.append(" JOIN BRAND_MASTER bm on bm.BRAND_MASTER_SID=IM.BRAND_MASTER_SID ");
        }
        }
        
        if ("ARM".equals(businessProcessBinder)) {
            additionalJoins.append(" JOIN RS_CONTRACT  rs on rs.RS_MODEL_SID=PD.RS_MODEL_SID ");
            String listName = "";
            if ((!"0".equals(String.valueOf(deductionLevelBinder))) && (!"Deduction".equals(getHelperTableSidforDescriptpion(String.valueOf(deductionLevelBinder))))) {
                
                switch (getHelperTableSidforDescriptpion(String.valueOf(deductionLevelBinder))) {
                case "Deduction Category":
                    listName = "RS.RS_CATEGORY";
                    break;
                case "Deduction Category 2":
                    listName = "U.UDC2";
                    break;
                case "Deduction Category 3":
                    listName = "U.UDC3";
                    break;
                case "Deduction Category 4":
                    listName = "U.UDC4";
                    break;
                case "Deduction Category 5":
                    listName = "U.UDC5";
                    break;

                case "Deduction Category 6":
                    listName = "U.UDC6";
                    break;
                    
                case "Deduction Program":
                    listName = "RS.REBATE_PROGRAM_TYPE"; 
                    break;
                case "Deduction Type":
                    listName = "RS.RS_TYPE"; 
                    break;
            }
               additionalJoins.append(" LEFT JOIN UDCS U ON U.MASTER_TYPE='RS_CONTRACT' AND U.MASTER_SID=RS.RS_CONTRACT_SID \n"
                        + "JOIN HELPER_TABLE TH \n"
                    + "ON TH.HELPER_TABLE_SID ="+listName);
            }
            isDeductionSearchCriteriaPresent = true;
        }
        if (!CommonUtils.CONTRACT_MANAGEMENT.equals(businessProcessBinder)) {
            query.append(" JOIN COMPANY_MASTER GL_COMp on GL_COMp.COMPANY_MASTER_SID=PM.COMPANY_MASTER_SID \n");
        if ("ARM".equals(businessProcessBinder)) {
                query.append(" JOIN COMPANY_MASTER BU_COMp on BU_COMp.COMPANY_MASTER_SID=arm_M.BU_COMPANY_MASTER_SID \n"
                    + "JOIN ARM_ADJUSTMENT_CONFIG ADJ_CONF on ADJ_CONF.ARM_ADJUSTMENT_CONFIG_SID = arm_M.TRANSACTION_TYPE\n"
                    + "JOIN HELPER_TABLE TRANX_NAME on TRANX_NAME.HELPER_TABLE_SID = ADJ_CONF.METHODOLGY\n");
            } else {
                query.append("JOIN COMPANY_MASTER BU_COMp on BU_COMp.COMPANY_MASTER_SID=PM.BUSINESS_UNIT \n ");
        }

        }

        if (CommonUtils.ACCRUAL_RATE_PROJECTION.equals(businessProcessBinder)) {
            projDetailsTable = "ACCRUAL_PROJ_DETAILS ";
        } else if (CommonUtils.FORECASTING.equals(businessProcessBinder)) {
            projDetailsTable = "PROJECTION_DETAILS ";
        } else if (CommonUtils.RETURNS.equals(businessProcessBinder)) {
            projDetailsTable = "RETURNS_PROJ_DETAILS ";
        } else if ("ARM".equals(businessProcessBinder)) {
            projDetailsTable = "ARM_ADJUSTMENT_DETAILS ";
        }
        if (!CommonUtils.CONTRACT_MANAGEMENT.equals(businessProcessBinder) && (isContractSearchCriteriaPresent || isCompanySearchCriteriaPresent || isItemSearchCriteriaPresent || isDeductionSearchCriteriaPresent)) {
            query.append(" JOIN ").append(projDetailsTable).append("PD ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID ");
            if (!CommonUtils.RETURNS.equals(businessProcessBinder)) {
                query.append(" JOIN CCP_DETAILS CCP ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID ");
            }
            query.append(additionalJoins);
        }

        query.append(" WHERE ");

        if (businessProcessBinder != null && !businessProcessBinder.isEmpty()) {
            String businessProcessKey = StringUtils.EMPTY;
            switch (businessProcessBinder) {
                case CommonUtils.FORECASTING:
                    businessProcessKey = "_F%";
                    break;
                case CommonUtils.ACCRUAL_RATE_PROJECTION:
                    businessProcessKey = "ARF%";
                    break;
                case CommonUtils.CONTRACT_MANAGEMENT:
                    businessProcessKey = "CM%";
                    break;
                case CommonUtils.RETURNS:
                    businessProcessKey = "RE%";
                    break;
                case "ARM":
                    businessProcessKey = "ARM_TRX%";
                    break;
            }

            if (!businessProcessKey.isEmpty()) {
                query.append(" WM.WORKFLOW_ID like '").append(businessProcessKey).append("'");
            }
        }

        if (StringUtils.isNotEmpty(inboxdto.getBusinessUnitName())) {
            query.append(" AND BU_COMp.COMPANY_NAME like '").append(inboxdto.getBusinessUnitName().replace("*", "%")).append("'");
        }

        if (StringUtils.isNotEmpty(inboxdto.getBusinessUnitNo())) {
            query.append(" AND BU_COMp.COMPANY_NO like '").append(inboxdto.getBusinessUnitNo().replace("*", "%")).append("'");
        }

        if (StringUtils.isNotEmpty(inboxdto.getBusinessUnitId())) {
            query.append(" AND BU_COMp.COMPANY_ID like '").append(inboxdto.getBusinessUnitId().replace("*", "%")).append("'");
        }
        
        if ("ARM".equals(businessProcessBinder)) {
            if (companyBinder != 0) {
                query.append(" AND GL_COMp.COMPANY_MASTER_SID like '").append(companyBinder).append("'");
            }

            if (businessUnitBinder != 0) {
                query.append(" AND BU_COMp.COMPANY_MASTER_SID like '").append(businessUnitBinder).append("'");
            }
            if (!brandIdBinder.isEmpty()) {
                brandIdBinder = brandIdBinder.replace("*", "%");
                query.append(" AND bm.BRAND_ID like '").append(brandIdBinder).append("'");
            }
            if (!brandNameBinder.isEmpty()) {
                brandNameBinder = brandNameBinder.replace("*", "%");
                query.append(" AND bm.BRAND_NAME like '").append(brandNameBinder).append("'");
            }
            if (!deductionNoBinder.isEmpty()) {
                deductionNoBinder = deductionNoBinder.replace("*", "%");
                query.append(" AND rs.RS_NO like '").append(deductionNoBinder).append("'");
            }
            if (!deductionNameBinder.isEmpty()) {
                deductionNameBinder = deductionNameBinder.replace("*", "%");
                query.append(" AND rs.RS_NAME like '").append(deductionNameBinder).append("'");
            }
            if (!"".equals(workflowStatusValueBinder)) {
                query.append(" AND WM.WORKFLOW_STATUS_ID = '").append(workflowStatusValueBinder).append("'");
            }
            if (glDate != null) {
                String formattedDate = CommonUtil.getFormattedDate(glDate);
                query.append(" AND CONVERT(DATE, arm_M.GL_IMPACT_DATE) >= '").append(formattedDate).append("'");
            }
            if (adjustmentTypeBinder != null) {
                query.append("AND PM.FORECASTING_TYPE in ('ARM')");
                query.append("AND arm_M.TRANSACTION_TYPE in (");
                for (int i = 0; i < adjustmentTypeBinder.size(); i++) {
                    if (!ARMConstants.getDemandPaymentsRecon().equals(adjustmentTypeBinder.get(i))) {
                        query.append(" '").append(adjustmentTypeBinder.get(i)).append("'").append(",");
                    } else {
                        query.append(" '").append(ARMConstants.getDemandPaymentsRecon()).append("'").append(",");
                    }
                }
                query.replace(query.lastIndexOf(","), query.lastIndexOf(",") + 1, " ");
                query.append(")");
            }
        }
        if (isContractSearchCriteriaPresent) {
            if (!contractIdBinder.isEmpty()) {
                contractIdBinder = contractIdBinder.replace("*", "%");
                query.append(" AND CNM.CONTRACT_ID like '").append(contractIdBinder).append("'");
            }
            if (!contractNoBinder.isEmpty()) {
                contractNoBinder = contractNoBinder.replace("*", "%");
                query.append(" AND CNM.CONTRACT_NO like '").append(contractNoBinder).append("'");
            }
            if (!contractNameBinder.isEmpty()) {
                contractNameBinder = contractNameBinder.replace("*", "%");
                query.append(" AND CNM.CONTRACT_NAME like '").append(contractNameBinder).append("'");
            }
            if (!contractTypeBinder.isEmpty()) {
                query.append(" AND CNM.CONTRACT_TYPE like '").append(contractTypeBinder).append("'");
            }
        }

        if (isCompanySearchCriteriaPresent) {
            if (CommonUtils.CONTRACT_MANAGEMENT.equals(businessProcessBinder)) {
            if (!companyIdBinder.isEmpty()) {
                companyIdBinder = companyIdBinder.replace("*", "%");
                query.append(" AND CM.COMPANY_ID like '").append(companyIdBinder).append("'");
            }
            if (!companyNoBinder.isEmpty()) {
                companyNoBinder = companyNoBinder.replace("*", "%");
                query.append(" AND CM.COMPANY_NO like '").append(companyNoBinder).append("'");
            }
            if (!companyNameBinder.isEmpty()) {
                companyNameBinder = companyNameBinder.replace("*", "%");
                query.append(" AND CM.COMPANY_NAME like '").append(companyNameBinder).append("'");
            }
            } else {
                if (!companyIdBinder.isEmpty()) {
                    companyIdBinder = companyIdBinder.replace("*", "%");
                    query.append(" AND GL_COMp.COMPANY_ID like '").append(companyIdBinder).append("'");
        }
                if (!companyNoBinder.isEmpty()) {
                    companyNoBinder = companyNoBinder.replace("*", "%");
                    query.append(" AND GL_COMp.COMPANY_NO like '").append(companyNoBinder).append("'");
                }
                if (!companyNameBinder.isEmpty()) {
                    companyNameBinder = companyNameBinder.replace("*", "%");
                    query.append(" AND GL_COMp.COMPANY_NAME like '").append(companyNameBinder).append("'");
                }
            }
        }

        if (isItemSearchCriteriaPresent) {
            if (!itemIdBinder.isEmpty()) {
                itemIdBinder = itemIdBinder.replace("*", "%");
                query.append(" AND IM.ITEM_ID like '").append(itemIdBinder).append("'");
            }
            if (!itemNoBinder.isEmpty()) {
                itemNoBinder = itemNoBinder.replace("*", "%");
                query.append(" AND IM.ITEM_NO like '").append(itemNoBinder).append("'");
            }
            if (!itemNameBinder.isEmpty()) {
                itemNameBinder = itemNameBinder.replace("*", "%");
                query.append(" AND IM.ITEM_NAME like '").append(itemNameBinder).append("'");
            }
        }

        if (isDeductionSearchCriteriaPresent && CommonUtils.FORECASTING.equals(businessProcessBinder)) {
            switch (deductionLevelBinder) {
                case CommonUtils.DEDUCTION_CATEGORY:
                    query.append(" AND RS.RS_CATEGORY ='").append(deductionvalueBinder).append("'");
                    break;
                case CommonUtils.DEDUCTION_PROGRAM_TYPE:
                    query.append(" AND RS.REBATE_PROGRAM_TYPE ='").append(deductionvalueBinder).append("'");
                    break;
                case CommonUtils.DEDUCTION_TYPE:
                    query.append(" AND RS.RS_TYPE ='").append(deductionvalueBinder).append("'");
                    break;
            }
        }

        if (isDeductionSearchCriteriaPresent && CommonUtils.ACCRUAL_RATE_PROJECTION.equals(businessProcessBinder)) {
            query.append(" AND APS.FIELD_NAME = '").append(deductionLevelBinder).append("'").append(" AND APS.FIELD_VALUES = '")
                    .append((helperListutil.getIdHelperDTOMap().get(deductionvalueBinder != null ? Integer.valueOf(String.valueOf(deductionvalueBinder)) : 0)).getDescription()).append("'");
        }
        
         if (isDeductionSearchCriteriaPresent && "ARM".equals(businessProcessBinder) && !"0".equals(String.valueOf(deductionLevelBinder)) && !"null".equals(String.valueOf(deductionLevelBinder)) && !"".equals(String.valueOf(deductionvalueBinder))) {
             
             if("Deduction".equals(getHelperTableSidforDescriptpion(String.valueOf(deductionLevelBinder)))){

                query.append(" AND RS.RS_ID = '").append(String.valueOf(deductionvalueBinder)).append("'"); 
             } else{
                 query.append(" AND TH.DESCRIPTION = '").append(String.valueOf(deductionvalueBinder)).append("'");
            }
        }
        
        
        if (workflowIdBinder != null && StringUtils.isNotBlank(workflowIdBinder)) {
            workflowId = workflowIdBinder;
            workflowId = workflowId.replace("*", "%");
            query.append(" AND WM.WORKFLOW_ID like '").append(workflowId).append("'");

        }

        if (workflowNameBinder != null && StringUtils.isNotBlank(workflowNameBinder)) {
            workflowName = workflowNameBinder;
            workflowName = workflowName.replace("*", "%");
            if (!CommonUtils.CONTRACT_MANAGEMENT.equals(businessProcessBinder) && !"ARM".equals(businessProcessBinder)) {
                query.append(" AND PM.PROJECTION_NAME like '").append(workflowName).append("'");
            } else if ("ARM".equals(businessProcessBinder)){
                query.append(" AND PM.PROJECTION_DESCRIPTION like '").append(workflowName).append("'");
            }
            else
            {
                query.append(" AND CM.CONTRACT_NAME like '").append(workflowName).append("'");
            }
        }

        if (workflowDescriptionBinder != null && StringUtils.isNotBlank(workflowDescriptionBinder)) {
            workflowDescription = workflowDescriptionBinder;
            workflowDescription = workflowDescription.replace("*", "%");
            if (!CommonUtils.CONTRACT_MANAGEMENT.equals(businessProcessBinder)) {
                query.append(" AND PM.PROJECTION_DESCRIPTION like '").append(workflowDescription).append("'");
            } else {
                query.append(" AND WM.WORKFLOW_ID like '").append(workflowDescription).append("'");
            }
        }

        if (inboxdto.getStatus() != null && !"0".equals(statusBinder)) {
            query.append(" AND WM.WORKFLOW_STATUS_ID = '").append(statusBinder).append("'");
        }

        if (!"null".equalsIgnoreCase(createdByBinder) && StringUtils.isNotBlank(createdByBinder) && !"0".equalsIgnoreCase(createdByBinder)) {
            query.append(" AND WM.CREATED_BY = '").append(createdByBinder).append("'");
        }

        if (!"null".equalsIgnoreCase(approvedByBinder) && StringUtils.isNotBlank(approvedByBinder) && !"0".equalsIgnoreCase(approvedByBinder)) {
            query.append(" AND WM.APPROVED_BY = '").append(approvedByBinder).append("'");
        }

        if (createdFrom != null) {
            String formattedDate = CommonUtil.getFormattedDate(createdFrom);
            query.append(" AND CONVERT(DATE, WM.CREATED_DATE) >= '").append(formattedDate).append("'");
        }
        if (createdTo != null) {
            String formattedDate = CommonUtil.getFormattedDate(createdTo);
            query.append(" AND CONVERT(DATE, WM.CREATED_DATE)<= '").append(formattedDate).append("'");
        }
        if (approvedFrom != null) {
            String formattedDate = CommonUtil.getFormattedDate(approvedFrom);
            query.append(" AND CONVERT(DATE, WM.APPROVED_DATE) >= '").append(formattedDate).append("'");
        }
        if (approvedTo != null) {
            String formattedDate = CommonUtil.getFormattedDate(approvedTo);
            query.append(" AND CONVERT(DATE, WM.APPROVED_DATE) <= '").append(formattedDate).append("'");
        }

        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    if ("adjustmentTypeValue".equals(stringFilter.getPropertyId())) {
                        String filterString = stringFilter.getFilterString();
                        if (!"null".equals(String.valueOf(stringFilter.getPropertyId())) && !"".equals(filterString)) {
                            query.append(" AND ").append(getDbColumnName(String.valueOf(stringFilter.getPropertyId()), CommonUtils.CONTRACT_MANAGEMENT.equals(businessProcessBinder))).append(" like '").append(filterString).append("'");
                        }
                    } else {
                        String filterString = "%" + stringFilter.getFilterString() + "%";
                        if (!"null".equals(String.valueOf(stringFilter.getPropertyId()))) {
                            query.append(" AND ").append(getDbColumnName(String.valueOf(stringFilter.getPropertyId()), CommonUtils.CONTRACT_MANAGEMENT.equals(businessProcessBinder))).append(" like '").append(filterString).append("'");
                        }

                    }

                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                    Date startValue = betweenFilter.getStartValue() != null ? (Date) betweenFilter.getStartValue() : null;
                    Date endValue = betweenFilter.getEndValue() != null ? (Date) betweenFilter.getEndValue() : null;

                    if (CommonUtils.CREATED_DATE.equals(betweenFilter.getPropertyId())) {
                        if (startValue != null) {
                            query.append(" AND CONVERT(DATE, WM.CREATED_DATE) >= '").append(CommonUtil.getFormattedDate(startValue)).append("'");
                        }
                        if (endValue != null) {
                            query.append(" AND CONVERT(DATE, WM.CREATED_DATE)<= '").append(CommonUtil.getFormattedDate(endValue)).append("'");
                        }

                    } else if (CommonUtils.APPROVED_DATE.equals(betweenFilter.getPropertyId())) {
                        if (startValue != null) {
                            query.append(" AND CONVERT(DATE, WM.MODIFIED_DATE) >= '").append(CommonUtil.getFormattedDate(startValue)).append("'");
                        }
                        if (endValue != null) {
                            query.append(" AND CONVERT(DATE, WM.MODIFIED_DATE) <= '").append(CommonUtil.getFormattedDate(endValue)).append("'");
                        }
                    }
                }
            }
        }

        if (!isCount) {

            if (columns != null && columns.size() > 0) {
                String columnName;
                for (final Iterator<SortByColumn> iterator = columns.iterator(); iterator.hasNext();) {
                    final SortByColumn orderByColumn = (SortByColumn) iterator.next();
                    columnName = orderByColumn.getName();
                    if (columnName.equalsIgnoreCase(CommonUtils.CREATED_BY)) {
                        columnName = "WM.CREATED_BY";
                    }
                    if (columnName.equalsIgnoreCase(CommonUtils.APPROVED_BY)) {
                        columnName = "WM.APPROVED_BY";
                    }
                    if (columnName.equalsIgnoreCase("company")) {
                        columnName = "GL_COMp.COMPANY_NAME";
                    }
                    if (columnName.equalsIgnoreCase(CommonUtils.BUSINESS_UNIT)) {
                        columnName = "BU_COMp.COMPANY_NAME";
                    }
                    
                    query.append(" ORDER BY ").append(columnName).append(" ").append(String.valueOf(orderByColumn.getType()));
                }
            } else {
                query.append(" ORDER BY workflowId ");
            }
            query.append(" OFFSET ").append(start).append(" ROWS FETCH NEXT ").append(offset).append(" ROWS ONLY");
        } else {
            query.append(")OBJ");
        }
        LOGGER.debug("getWorkflowSearchQuery ends");
        return String.valueOf(query);

    }

    public List executeSelectQuery(String query) {
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    /**
     * For getting WORKFLOW_MASTER_SID using business Unit
     *
     * @param businessUnit
     * @return
     */
    public static List getWorkflowIdForBusinessUnit(int businessUnit) {
        LOGGER.debug(" Entering getWorkflowIdForBusinessUnit");
        List resultList = new ArrayList();
        String query = "SELECT distinct WM.WORKFLOW_MASTER_SID\n"
                + " FROM WORKFLOW_MASTER WM \n"
                + " join PROJECTION_MASTER PM on PM.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID\n"
                + " join PROJECTION_DETAILS PD on PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID \n"
                + " join CCP_DETAILS CD on CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID   \n"
                + " join ITEM_MASTER IM on CD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID \n"
                + " join GL_COST_CENTER_MASTER GC on GC.NDC8 = IM.NDC8 \n"
                + " join COMPANY_MASTER CM on CM.COMPANY_ID = GC.COMPANY_CODE and CM.COMPANY_MASTER_SID = " + businessUnit;
        try {
            resultList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("Exiting getWorkflowIdForBusinessUnit");
        return resultList;
    }

    /**
     * To convert String format to Date format
     *
     * @param dateRange
     * @return
     */
    public Date getDateFromString(String dateRange) {
        SimpleDateFormat formatter = new SimpleDateFormat(ConstantsUtils.LONGDATEFORMAT);
        Date date = null;
        if (dateRange != null && !StringUtils.EMPTY.equals(dateRange)) {
            try {
                date = formatter.parse(dateRange);

            } catch (ParseException ex) {

                LOGGER.error(ex);
            }
        }
        return date;
    }

    /**
     * To get user
     *
     * @param userId
     * @return
     */
    public User getUserInfo(long userId) {
        DynamicQuery userSearchDynamicQuery = UserLocalServiceUtil.dynamicQuery();
        userSearchDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USER_ID, userId));
        List<User> resultList;

        try {
            resultList = UserLocalServiceUtil.dynamicQuery(userSearchDynamicQuery);
            if (resultList != null && resultList.size() > 0) {
                return resultList.get(0);
            }
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }

        return null;
    }

    /**
     * To get DB connection
     *
     * @return
     */
    public Connection getConnection() {
        String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";
        Connection connection = null;
        DataSource datasource;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                connection = datasource.getConnection();
            } else {
                LOGGER.error("Failed to lookup datasource in getConnection");
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return connection;
    }

    /**
     * To get status name from id
     *
     * @return
     */
    public HashMap<Integer, String> getStatusNameFromId() {

        List<HelperTable> status = new ArrayList<HelperTable>();
        DynamicQuery ststusDynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
        try {
            status = HelperTableLocalServiceUtil.dynamicQuery(ststusDynamicQuery);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        HashMap<Integer, String> ststusMap = new HashMap<Integer, String>();
        for (HelperTable sts : status) {

            ststusMap.put(sts.getHelperTableSid(), sts.getDescription());

        }
        return ststusMap;
    }

    /**
     * To get user information
     *
     * @return
     */
    public HashMap<Long, String> getUserInfo() {

        List<User> users = new ArrayList<User>();
        DynamicQuery userGroupDynamicQuery = UserLocalServiceUtil.dynamicQuery();
        try {
            users = UserLocalServiceUtil.dynamicQuery(userGroupDynamicQuery);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        HashMap<Long, String> userMap = new HashMap<Long, String>();
        for (User user : users) {
            userMap.put(user.getUserId(), user.getLastName()
                    + " " + user.getFirstName());
        }
        return userMap;
    }

    /**
     * To get fields for security
     *
     * @param moduleName
     * @param tabName
     * @return
     */
    public List<Object> getFieldsForSecurity(String moduleName, String tabName) {
        List<Object> resultList = new ArrayList<Object>();
        try {
            resultList = new WorkflowImpl().fetchFieldsForSecurity(moduleName, tabName, null, null, null);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }

    /**
     * Workflow history search
     *
     * @param workFlowMasterSystemId
     * @param filters
     * @param sortByColumns
     * @return
     */
    public List<WorkFlowHistoryLookupDTO> workFlowHistorySearch(int workFlowMasterSystemId, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) {
        List<WorkFlowHistoryLookupDTO> wFlowHistoryLookupDTOs = new ArrayList<WorkFlowHistoryLookupDTO>();
        String columnName = StringUtils.EMPTY;
        String orderBy = StringUtils.EMPTY;
        Map<String, Object> parameters = new HashMap<String, Object>();
        getParameterList(parameters, filters);
        boolean asc = false;
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                columnName = sortByColumn.getName();
                asc = sortByColumn.getType() == SortByColumn.Type.ASC;
                orderBy = asc ? "ASC" : "DESC";
            }
        }
        String query = SQlUtil.getQuery("loadWorkflowHistory");
        query = query.replace("?", String.valueOf(workFlowMasterSystemId));
        query = query.replace("ORDER BY MODIFIED_DATE asc", StringUtils.EMPTY);
        if (parameters.get(CommonUtils.STATUS) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(CommonUtils.STATUS)))) {
            query += " AND HWM.WORKFLOW_STATUS_ID = " + Integer.valueOf(String.valueOf(parameters.get(CommonUtils.STATUS)));
        }
        if (parameters.get(CommonUtils.MODIFIED_BY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(CommonUtils.MODIFIED_BY)))) {
            query += " AND HWM.MODIFIED_BY = " + Integer.valueOf(String.valueOf(parameters.get(CommonUtils.MODIFIED_BY)));
        }
        if (parameters.get(CommonUtils.NOTES) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(CommonUtils.NOTES)))) {
            query += " AND HWM.NOTES LIKE " + String.valueOf(parameters.get(CommonUtils.NOTES));
        }
        if (parameters.get(CommonUtils.MODIFIED_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(CommonUtils.MODIFIED_DATE_FROM)))) {
            query += (" AND HWM.MODIFIED_DATE >='") + (String.valueOf(parameters.get(CommonUtils.MODIFIED_DATE_FROM))) + ("'");
        }
        if (parameters.get(CommonUtils.MODIFIED_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(CommonUtils.MODIFIED_DATE_TO)))) {
            query += (" AND HWM.MODIFIED_DATE <='") + (String.valueOf(parameters.get(CommonUtils.MODIFIED_DATE_TO))) + ("'");
        } else if (!columnName.isEmpty() && CommonUtils.STATUS.equalsIgnoreCase(columnName)) {
            query += " ORDER BY HWM.WORKFLOW_STATUS_ID " + orderBy;
        } else if (!columnName.isEmpty() && CommonUtils.MODIFIED_BY.equalsIgnoreCase(columnName)) {
            query += " ORDER BY HWM.MODIFIED_BY  " + orderBy;
        } else if (!columnName.isEmpty() && CommonUtils.NOTES.equalsIgnoreCase(columnName)) {
            query += " ORDER BY HWM.NOTES  " + orderBy;
        } else if (!columnName.isEmpty() && "modifiedDate".equalsIgnoreCase(columnName)) {
            query += " ORDER BY HWM.MODIFIED_DATE " + orderBy;
        } else {
            query += " ORDER BY MODIFIED_DATE asc";
        }
        List<HistWorkflowMaster> resultList = null;
        LOGGER.debug("workFlowHistorySearch" + query);
        try {
            resultList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        if (resultList != null) {
            wFlowHistoryLookupDTOs = getWorkFlowHistory(resultList);
        }
        return wFlowHistoryLookupDTOs;
    }

    /**
     * To get workflow history
     *
     * @param resultList
     * @return
     */
    private List<WorkFlowHistoryLookupDTO> getWorkFlowHistory(List resultList) {
        List<WorkFlowHistoryLookupDTO> wfResultList = new ArrayList<WorkFlowHistoryLookupDTO>();
        WorkFlowHistoryLookupDTO workFlowHistoryLookupDTO;
        int size = resultList.size();
        try {
            for (int loop = 0, limit = size; loop < limit; loop++) {
                workFlowHistoryLookupDTO = new WorkFlowHistoryLookupDTO();
                Object objects[] = (Object[]) resultList.get(loop);
                workFlowHistoryLookupDTO.setModifiedDate(objects[0] != null ? parsetDate(String.valueOf(objects[0])) : null);
                if (String.valueOf(objects[1]) != null && !"null".equalsIgnoreCase(String.valueOf(objects[1])) && !"".equalsIgnoreCase(String.valueOf(objects[1]))) {
                    workFlowHistoryLookupDTO.setModifiedBy(CommonUtils.getUserInfo(Long.valueOf(String.valueOf(objects[1]))));
                }
                workFlowHistoryLookupDTO.setStatus(helperListutil.getIdDescMap().get(Integer.parseInt(String.valueOf(objects[NumericConstants.TWO]))));
                workFlowHistoryLookupDTO.setNotes(objects[NumericConstants.THREE] != null ? String.valueOf(objects[NumericConstants.THREE]) : StringUtils.EMPTY);
                workFlowHistoryLookupDTO.setCheckbox((String.valueOf(objects[NumericConstants.FOUR]).isEmpty()) || (String.valueOf(objects[NumericConstants.FOUR]).equals("null")) ? false : true);
                workFlowHistoryLookupDTO.setFileName(objects[NumericConstants.FOUR] != null ? String.valueOf(objects[NumericConstants.FOUR]) : StringUtils.EMPTY);
                wfResultList.add(workFlowHistoryLookupDTO);
            }
        } catch (ParseException pe) {
            LOGGER.error(pe);
        }
        return wfResultList;
    }

    /**
     * To get count for Workflow history
     *
     * @param workFlowMasterSystemId
     * @param filters
     * @return
     */
    public int workFlowHistorySearchCount(int workFlowMasterSystemId, Set<Container.Filter> filters) {
        int count = 0;
        Map<String, Object> parameters = new HashMap<String, Object>();
        getParameterList(parameters, filters);
        String query = SQlUtil.getQuery("loadWorkflowHistory");
        query = query.replace("?", String.valueOf(workFlowMasterSystemId));
        query = query.replace("ORDER BY MODIFIED_DATE asc", StringUtils.EMPTY);
        if (parameters.get(CommonUtils.STATUS) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(CommonUtils.STATUS)))) {
            query += " AND HWM.WORKFLOW_STATUS_ID  = " + Integer.valueOf(String.valueOf(parameters.get(CommonUtils.STATUS)));
        }
        if (parameters.get(CommonUtils.MODIFIED_BY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(CommonUtils.MODIFIED_BY)))) {
            query += " AND HWM.MODIFIED_BY = " + Integer.valueOf(String.valueOf(parameters.get(CommonUtils.MODIFIED_BY)));
        }
        if (parameters.get(CommonUtils.NOTES) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(CommonUtils.NOTES)))) {
            query += " AND HWM.NOTES LIKE " + "'" + String.valueOf(parameters.get(CommonUtils.NOTES) + "' ");
        }
        if (parameters.get(CommonUtils.MODIFIED_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(CommonUtils.MODIFIED_DATE_FROM)))) {
            query += (" AND HWM.MODIFIED_DATE >='") + (String.valueOf(parameters.get(CommonUtils.MODIFIED_DATE_FROM))) + ("'");
        }
        if (parameters.get(CommonUtils.MODIFIED_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(CommonUtils.MODIFIED_DATE_TO)))) {
            query += (" AND HWM.MODIFIED_DATE <='") + (String.valueOf(parameters.get(CommonUtils.MODIFIED_DATE_TO))) + ("'");
        }
        query = query + " ORDER BY MODIFIED_DATE asc";
        List<HistWorkflowMaster> resultList = null;
        try {
            LOGGER.debug("QUERTY" + query);
            resultList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        if (resultList != null) {
            count = resultList.size();
        }
        return count;
    }

    private static Date parsetDate(String value) throws ParseException {
        Date date = null;
        String tempDate;
        SimpleDateFormat parse = new SimpleDateFormat(ConstantsUtils.LONGDATEFORMAT);
        SimpleDateFormat format = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
        if (value != null && !StringUtils.EMPTY.equals(value) && !"null".equals(value)) {
            tempDate = format.format(parse.parse(value));
            date = format.parse(tempDate);
        }
        return date;
    }

    /**
     * To get filter parameters
     *
     * @param parameters
     * @param filters
     */
    private static void getParameterList(Map<String, Object> parameters, Set<Container.Filter> filters) {
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString();
                    parameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();
                    parameters.put(betweenFilter.getPropertyId() + "from", DB_DATE.format(startValue));
                    parameters.put(betweenFilter.getPropertyId() + "to", DB_DATE.format(endValue));
                } else if (filter instanceof And) {
                    And stringFilter = (And) filter;
                    Collection<Container.Filter> value = stringFilter.getFilters();
                    for (Container.Filter filter1 : value) {
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            parameters.put(less.getPropertyId() + "to", String.valueOf(less.getValue()));
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            parameters.put(greater.getPropertyId() + "from", String.valueOf(greater.getValue()));
                        }
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (stringFilter.getValue() instanceof Date) {
                        String filterString = DB_DATE.format(stringFilter.getValue());
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            parameters.put(stringFilter.getPropertyId().toString() + "from", filterString);
                        } else {
                            parameters.put(stringFilter.getPropertyId().toString() + "to", filterString);
                        }
                    }
                }
            }
        }
    }

    /**
     * Search method for UsersLookup using DYNAMIC Query
     *
     * @param binder
     * @return
     */
    public List<UserViewDTO> UserSearch(UserViewDTO userdto) {
        DynamicQuery userSearchDynamicQuery = UserLocalServiceUtil.dynamicQuery();
        List<UserViewDTO> userSearchresults = new ArrayList<UserViewDTO>();
        String firstName;
        String lastName;

        String fullName;
        String firstNameBinder = userdto.getFirstName();
        String lastNameBinder = userdto.getLastName();
        String fullNameBinder = userdto.getFullName();

        if (firstNameBinder != null && StringUtils.isNotEmpty(firstNameBinder)) {
            firstName = firstNameBinder.replace("*", "%");
            userSearchDynamicQuery.add(RestrictionsFactoryUtil.ilike(CommonUtils.FIRST_NAME, firstName));
        }
        if (lastNameBinder != null && StringUtils.isNotEmpty(lastNameBinder)) {
            lastName = lastNameBinder.replace("*", "%");
            userSearchDynamicQuery.add(RestrictionsFactoryUtil.ilike(CommonUtils.LAST_NAME, lastName));
        }
        if (fullNameBinder != null && StringUtils.isNotEmpty(fullNameBinder)) {
            fullName = fullNameBinder.replace("*", "%");
            if(fullName.contains(" ")){
                String s[] = fullName.split(" ");    
                if(!s[0].isEmpty()){
                    userSearchDynamicQuery.add(RestrictionsFactoryUtil.ilike(CommonUtils.FIRST_NAME, s[0]));
                }
                if(!s[1].isEmpty()){
                    userSearchDynamicQuery.add(RestrictionsFactoryUtil.ilike(CommonUtils.LAST_NAME, s[1]));
                }
            } else {
                userSearchDynamicQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.ilike(CommonUtils.FIRST_NAME, fullName),
                        RestrictionsFactoryUtil.ilike(CommonUtils.LAST_NAME, fullName)));
            }
        }
        List<User> resultList;
        try {
            resultList = UserLocalServiceUtil.dynamicQuery(userSearchDynamicQuery);
            userSearchresults = getCustomizedProjectionResults(resultList);
        } catch (SystemException e) {
            LOGGER.error(e);
        }
        return userSearchresults;
    }

    /**
     * Method for setting the Users Lookup results in DTO
     *
     * @param resultList
     * @return
     */
    public List<UserViewDTO> getCustomizedProjectionResults(List<User> resultList) {
        List<UserViewDTO> searchResults = new ArrayList<UserViewDTO>();
        for (User proj : resultList) {
            UserViewDTO userResult = new UserViewDTO();
            if(!proj.getFirstName().isEmpty()){
            userResult.setFirstName(proj.getFirstName());
            userResult.setLastName(proj.getLastName());
            userResult.setFullName(proj.getFullName());
            userResult.setUserId(proj.getUserId());
            searchResults.add(userResult);

            }
        }
        return searchResults;
    }

    /**
     * To get ViewResultsCount
     *
     * @param inboxDashboardDTO
     * @return
     */
    public int ViewResultsCount(final InboxDashboardDTO inboxDashboardDTO, int start, int offset, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, boolean isCount) {
        LOGGER.debug("Entering workflowtablelogic getWorkflowinboxCount");
        List<Object[]> count = QueryUtils.getServiceData(getViewInput(inboxDashboardDTO, start, offset, columns, filterSet, isCount), "wi.viewCount", null);
        return getCount(count);
    }

    /**
     * To loadviewResults
     *
     * @param inboxDashboardDTO
     * @return
     */
    public List loadviewResults(final InboxDashboardDTO inboxDashboardDTO, int start, int offset, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, boolean isCount) {
        LOGGER.debug("Entering workflowtablelogic loadviewResults");
        List<Object[]> searchResultsList = QueryUtils.getServiceData(getViewInput(inboxDashboardDTO, start, offset, columns, filterSet, isCount), "wi.viewSearch", null);
        List<InboxDashboardDTO> resultsList = getCustomizedViewData(searchResultsList);
        return resultsList;
    }

    /**
     * View lookup Insert or update view
     *
     * @param isInsert
     * @param inboxDashboardDTO
     */
    public void viewInsertOrUpdate(boolean isInsert, InboxDashboardDTO inboxDashboardDTO, String businessProcess) {
        if (WorkflowConstants.getBusinessProcessNameArm().equalsIgnoreCase(businessProcess)) {
            if (isInsert) {
                QueryUtils.updateServiceData(getInsertUpdateInputArm(isInsert, inboxDashboardDTO), "wi.insertViewArm");
            } else {
                QueryUtils.updateServiceData(getInsertUpdateInputArm(isInsert, inboxDashboardDTO), "wi.updateViewArm");
            }
        } else if (isInsert) {
            QueryUtils.updateServiceData(getInsertUpdateInput(isInsert, inboxDashboardDTO), "wi.insertView");
        } else {
            QueryUtils.updateServiceData(getInsertUpdateInput(isInsert, inboxDashboardDTO), "wi.updateView");
        }
    }

    /**
     * View lookup delete view
     *
     * @param workfLowInboxSid
     * @return
     */
    public Boolean deleteView(final int workfLowInboxSid) {
        List list = new ArrayList();
        list.add(workfLowInboxSid);
        Boolean isDeleted = QueryUtils.updateServiceData(list, "wi.deleteView");
        return isDeleted;
    }

    /**
     * To get count
     *
     * @param list
     * @return
     */
    public static int getCount(List<Object[]> list) {
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            int count = obj == null ? 0 : (Integer) obj;
            return count;
        }
        return 0;
    }

    /**
     * Load the input for search
     *
     * @param inboxDashboardDTO
     * @return List
     */
    private List getViewInput(InboxDashboardDTO inboxDashboardDTO, int start, int offset, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, boolean isCount) {
        List list = new ArrayList();
        if (inboxDashboardDTO.getViewType() != null && !"null".equals(inboxDashboardDTO.getViewType()) && !StringUtils.EMPTY.equals(inboxDashboardDTO.getViewType())) {
            list.add(inboxDashboardDTO.getViewType());
        } else {
            list.add(CommonUtils.CHAR_PERCENT);
        }
        if (inboxDashboardDTO.getViewName() != null && !StringUtils.EMPTY.equals(inboxDashboardDTO.getViewName())) {
            list.add(inboxDashboardDTO.getViewName().replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT));
        } else {
            list.add(CommonUtils.CHAR_PERCENT);
        }
        StringBuilder filterQString = AbstractFilterLogic.getInstance().filterQueryGenerator(filterSet, getQueryMap());
        LOGGER.debug("filter query" + filterQString);
        if (filterQString != null) {
            list.add(filterQString.toString().replace("where", "AND"));
        } else {
            list.add(" ");
        }
        if (!isCount) {
            StringBuilder orderBy = AbstractFilterLogic.getInstance().orderByQueryGenerator(columns, getQueryMap());
            if (orderBy != null) {
                list.add(orderBy.toString());
            } else {
                list.add(" ");
            }
            list.add(start);
            list.add(offset);
        }
        return list;
    }

    /**
     * To customize query data for view lookup table
     *
     * @param searchResultsList
     * @return
     */
    private List getCustomizedViewData(List<Object[]> searchResultsList) {
        List<InboxDashboardDTO> finalList = new ArrayList<>();
        for (int i = 0; i < searchResultsList.size(); i++) {
            final InboxDashboardDTO searchDTO = new InboxDashboardDTO();
            final Object[] object = (Object[]) searchResultsList.get(i);
            searchDTO.setViewName(checkAndReturnString(object[0]));
            searchDTO.setViewType(checkAndReturnString(object[1]));
            searchDTO.setBusinessProcess(checkAndReturnString(object[NumericConstants.TWO]));
            searchDTO.setWorkflowId(checkAndReturnString(object[NumericConstants.THREE]));
            searchDTO.setWorkflowName(checkAndReturnString(object[NumericConstants.FOUR]));
            searchDTO.setWorkflowDescription(checkAndReturnString(object[NumericConstants.FIVE]));
            searchDTO.setCreationDateRangeFrom(checkAndReturnDate(object[NumericConstants.SIX]));
            searchDTO.setCreationDateRangeTo(checkAndReturnDate(object[NumericConstants.SEVEN]));
            searchDTO.setApprovedDateRangeFrom(checkAndReturnDate(object[NumericConstants.EIGHT]));
            searchDTO.setApprovedDateRangeTo(checkAndReturnDate(object[NumericConstants.NINE]));
            searchDTO.setApprovedBy(CommonUtils.getUserInfo(String.valueOf(object[NumericConstants.ELEVEN])));
            searchDTO.setContractId(checkAndReturnString(object[NumericConstants.TWELVE]));
            searchDTO.setContractNo(checkAndReturnString(object[NumericConstants.THIRTEEN]));
            searchDTO.setContractName(checkAndReturnString(object[NumericConstants.FOURTEEN]));
            searchDTO.setContractType(checkAndReturnHelper(object[NumericConstants.FIFTEEN]));
            searchDTO.setCompanyId(checkAndReturnString(object[NumericConstants.SIXTEEN]));
            searchDTO.setCompanyNo(checkAndReturnString(object[NumericConstants.SEVENTEEN]));
            searchDTO.setCompanyName(checkAndReturnString(object[NumericConstants.EIGHTEEN]));
            searchDTO.setItemId(checkAndReturnString(object[NumericConstants.NINETEEN]));
            searchDTO.setItemNo(checkAndReturnString(object[NumericConstants.TWENTY]));
            searchDTO.setItemName(checkAndReturnString(object[NumericConstants.TWENTY_ONE]));
            searchDTO.setDeductionLevel(checkAndReturnString(object[NumericConstants.TWENTY_TWO]));
            searchDTO.setDeductionValue(checkAndReturnHelper(object[NumericConstants.TWENTY_THREE]));

            searchDTO.setWorkflowInboxSystemId(object[NumericConstants.TWENTY_FOUR] != null ? Integer.valueOf(String.valueOf(object[NumericConstants.TWENTY_FOUR])) : 0);
            searchDTO.setCompanyValue(object[NumericConstants.TWENTY_FIVE] != null ? Integer.valueOf(String.valueOf(object[NumericConstants.TWENTY_FIVE])) : 0);
            searchDTO.setBusinessUnitValue(object[NumericConstants.TWENTY_SIX] != null ? Integer.valueOf(String.valueOf(object[NumericConstants.TWENTY_SIX])) : 0);
            searchDTO.setWorkflowStatusValue(checkAndReturnHelper(object[NumericConstants.TWENTY_SEVEN]));
            searchDTO.setBrandId(checkAndReturnString(object[NumericConstants.TWENTY_EIGHT]));
            searchDTO.setBrandName(checkAndReturnString(object[NumericConstants.TWENTY_NINE]));
            searchDTO.setGlDate(checkAndReturnDate(object[NumericConstants.THIRTY]));
            searchDTO.setDeductionNo(checkAndReturnString(object[NumericConstants.THIRTY_ONE]));
            searchDTO.setDeductionName(checkAndReturnString(object[NumericConstants.THIRTY_TWO]));
            String adjType = String.valueOf(object[NumericConstants.THIRTY_THREE]);
            List list = new ArrayList();
            list.addAll(Arrays.asList(adjType.split(",")));
            searchDTO.setAdjustmentType(list);
            if (object[NumericConstants.THIRTY_FOUR] != null) {
                    Date startDate = (Date) object[NumericConstants.THIRTY_FOUR];
                    DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                try {
                    startDate = df.parse(CommonUtils.convertDateToString(startDate));
                } catch (ParseException ex) {
                    LOGGER.error(ex);
                }
                    searchDTO.setCreationDate(startDate);
                }
            searchDTO.setCreatedBy(CommonUtils.getUserInfo(String.valueOf(object[NumericConstants.THIRTY_FIVE])));
            searchDTO.setCreatedById(String.valueOf(object[NumericConstants.THIRTY_FIVE]));
            if (object[NumericConstants.THIRTY_SIX] != null) {
                    Date startDate = (Date) object[NumericConstants.THIRTY_SIX];
                    DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                try {
                    startDate = df.parse(CommonUtils.convertDateToString(startDate));
                } catch (ParseException ex) {
                    LOGGER.error(ex);
                }
                    searchDTO.setModifiedDate(startDate);
                }
           searchDTO.setModifiedBy(CommonUtils.getUserInfo(String.valueOf(object[NumericConstants.THIRTY_SEVEN])));
           searchDTO.setBusinessUnitId(checkAndReturnString(object[NumericConstants.THIRTY_EIGHT]));
            searchDTO.setBusinessUnitNo(checkAndReturnString(object[NumericConstants.THIRTY_NINE]));
            searchDTO.setBusinessUnitName(checkAndReturnString(object[NumericConstants.FORTY]));
            finalList.add(searchDTO);
        }
        return finalList;
    }

    /**
     * To get the query map
     *
     * @return
     */
    private Map<String, String> getQueryMap() {
        Map<String, String> viewsearchColumn = new HashMap<>();
        viewsearchColumn.put(StringUtils.EMPTY, "WORKFLOW_INBOX_SID");
        viewsearchColumn.put("viewName", "VIEW_NAME");
        viewsearchColumn.put("workflowId", "WORKFLOW_ID");
        viewsearchColumn.put("workflowName", "WORKFLOW_NAME");
        viewsearchColumn.put(CommonUtils.STATUS, "WORKFLOW_STATUS");
        viewsearchColumn.put("businessProcess", "BUSINESS_PROCESS");
        viewsearchColumn.put(CommonUtils.BUSINESS_UNIT, "BUSINESS_UNITS");
        viewsearchColumn.put(CommonUtils.CREATED_BY, "CREATED_BY");
        viewsearchColumn.put(CommonUtils.APPROVED_BY, "APPROVED_BY");
        viewsearchColumn.put("creationDateFrom", "CREATION_FROM_DATE");
        viewsearchColumn.put("creationDateTo", "CREATION_TO_DATE");
        viewsearchColumn.put(CommonUtils.CREATED_DATE, "CREATED_DATE");
        viewsearchColumn.put(CommonUtils.APPROVED_DATE, "APPROVED_DATE_FROM");
        return viewsearchColumn;
    }

    /**
     * To getInsertUpdateInput
     *
     * @param indicator
     * @param inboxDashboardDTO
     * @return
     */
    private List getInsertUpdateInput(boolean indicator, InboxDashboardDTO inboxDashboardDTO) {
        List list = new ArrayList();

        checkAndAdd(inboxDashboardDTO.getViewName(), list);
        checkAndAdd(inboxDashboardDTO.getViewType(), list);
        checkAndAdd(inboxDashboardDTO.getBusinessProcess(), list);
        checkAndAdd(inboxDashboardDTO.getWorkflowId(), list);
        checkAndAdd(inboxDashboardDTO.getWorkflowName(), list);
        checkAndAdd(inboxDashboardDTO.getWorkflowDescription(), list);
        checkAndAdd(inboxDashboardDTO.getCreationDateRangeFrom(), list);
        checkAndAdd(inboxDashboardDTO.getCreationDateRangeTo(), list);
        checkAndAdd(inboxDashboardDTO.getApprovedDateRangeFrom(), list);
        checkAndAdd(inboxDashboardDTO.getApprovedDateRangeTo(), list);
        checkAndAdd(inboxDashboardDTO.getCreatedById(), list);
        checkAndAdd(inboxDashboardDTO.getApprovedById(), list);
        checkAndAdd(inboxDashboardDTO.getContractId(), list);
        checkAndAdd(inboxDashboardDTO.getContractNo(), list);
        checkAndAdd(inboxDashboardDTO.getContractName(), list);
        checkAndAdd(inboxDashboardDTO.getContractType(), list);
        checkAndAdd(inboxDashboardDTO.getCompanyId(), list);
        checkAndAdd(inboxDashboardDTO.getCompanyNo(), list);
        checkAndAdd(inboxDashboardDTO.getCompanyName(), list);
        checkAndAdd(inboxDashboardDTO.getItemId(), list);
        checkAndAdd(inboxDashboardDTO.getItemNo(), list);
        checkAndAdd(inboxDashboardDTO.getItemName(), list);
        checkAndAdd(inboxDashboardDTO.getDeductionLevel(), list);
        checkAndAdd(inboxDashboardDTO.getDeductionValue(), list);
        checkAndAdd(inboxDashboardDTO.getBusinessUnitId(), list);
        checkAndAdd(inboxDashboardDTO.getBusinessUnitNo(), list);
        checkAndAdd(inboxDashboardDTO.getBusinessUnitName(), list);

        if (!indicator) {
            list.add(inboxDashboardDTO.getWorkflowInboxSystemId());
        } else {
            list.add(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        }
        return list;
    }

    /**
     * To getInsertUpdateInput
     *
     * @param indicator
     * @param inboxDashboardDTO
     * @return
     */
    private List getInsertUpdateInputArm(boolean indicator, InboxDashboardDTO inboxDashboardDTO) {
        List list = new ArrayList();
        checkAndAdd(inboxDashboardDTO.getViewName(), list);
        checkAndAdd(inboxDashboardDTO.getViewType(), list);
        checkAndAdd(inboxDashboardDTO.getBusinessProcess(), list);
        checkAndAdd(inboxDashboardDTO.getWorkflowId(), list);
        checkAndAdd(inboxDashboardDTO.getWorkflowName(), list);
        checkAndAdd(inboxDashboardDTO.getWorkflowDescription(), list);
        checkAndAdd(inboxDashboardDTO.getCreationDateRangeFrom(), list);
        checkAndAdd(inboxDashboardDTO.getCreationDateRangeTo(), list);
        checkAndAdd(inboxDashboardDTO.getApprovedDateRangeFrom(), list);
        checkAndAdd(inboxDashboardDTO.getApprovedDateRangeTo(), list);
        checkAndAdd(inboxDashboardDTO.getCreatedById(), list);
        checkAndAdd(inboxDashboardDTO.getApprovedById(), list);
        checkAndAdd(inboxDashboardDTO.getContractId(), list);
        checkAndAdd(inboxDashboardDTO.getContractNo(), list);
        checkAndAdd(inboxDashboardDTO.getContractName(), list);
        checkAndAdd(inboxDashboardDTO.getContractType(), list);
        checkAndAdd(inboxDashboardDTO.getCompanyId(), list);
        checkAndAdd(inboxDashboardDTO.getCompanyNo(), list);
        checkAndAdd(inboxDashboardDTO.getCompanyName(), list);
        checkAndAdd(inboxDashboardDTO.getItemId(), list);
        checkAndAdd(inboxDashboardDTO.getItemNo(), list);
        checkAndAdd(inboxDashboardDTO.getItemName(), list);
        checkAndAdd(inboxDashboardDTO.getDeductionLevel(), list);
        checkAndAdd(inboxDashboardDTO.getDeductionValue(), list);
        checkAndAddCompany(inboxDashboardDTO.getCompanyValue(), list);
        checkAndAdd(inboxDashboardDTO.getBusinessUnitValue(), list);
        checkAndAdd(inboxDashboardDTO.getWorkflowStatusValue(), list);
        checkAndAdd(inboxDashboardDTO.getBrandId(), list);
        checkAndAdd(inboxDashboardDTO.getBrandName(), list);
        checkAndAdd(inboxDashboardDTO.getGlDate(), list);
        checkAndAdd(inboxDashboardDTO.getDeductionNo(), list);
        checkAndAdd(inboxDashboardDTO.getDeductionName(), list);
        String adjType = inboxDashboardDTO.getAdjustmentType().toString().replace("[", "").replace("]", "");
        checkAndAdd(adjType, list);
        checkAndAdd(inboxDashboardDTO.getModifiedBy(), list);

        if (!indicator) {
            list.add(inboxDashboardDTO.getWorkflowInboxSystemId());
        }

        return list;
    }

    private void checkAndAdd(String source, List list) {
        if (source != null && !source.isEmpty()) {
            list.add(source);
        } else {
            list.add(StringUtils.EMPTY);
        }
    }

    private void checkAndAdd(int source, List list) {
        if (source != 0) {
            list.add(source);
        } else {
            list.add(0);
        }
    }

    private void checkAndAddCompany(int source, List list) {
        if (source != 0) {
            list.add(source);
        } else {
            list.add(null);
        }
    }

    private void checkAndAdd(HelperDTO source, List list) {
        if (source != null) {
            list.add(source.getId());
        } else {
            list.add(0);
        }
    }

    private void checkAndAdd(Date source, List list) {
        if (source != null) {
            list.add("'" + CommonUtil.getFormattedDate(source) + "'");
        } else {
            list.add(source);
        }
    }

    private String checkAndReturnString(Object source) {
        if (!"null".equals(source)) {
            return String.valueOf(source);
        } else {
            return StringUtils.EMPTY;
        }
    }

    private HelperDTO checkAndReturnHelper(Object source) {
        HelperDTO helper = new HelperDTO();
        if (!"null".equals(source) && CommonUtil.isInteger(source)) {
            int id = CommonUtil.convertToInteger(source);
            helper.setId(id);
            helper.setDescription(helperListutil.getIdDescMap().get(id));
            return helper;
        } else {
            return new HelperDTO();
        }
    }

    private Date checkAndReturnDate(Object source) {
        if (source != null) {
            return (Date) source;
        } else {
            return null;
        }
    }

    /**
     * Used to find the DB column Names corresponding to the UI table columns
     * (Dependent on the written query. If query changes, this should also be
     * changed)
     *
     * @param columnName
     * @return
     */
    private String getDbColumnName(String columnName, boolean isContract) {
        switch (columnName) {
            case "workflowId":
                return "WM.WORKFLOW_ID";
            case "workflowName":
                return isContract ? "CM.CONTRACT_NAME" : CommonUtils.PM_PROJECTION_DESCRIPTION;
            case "workflowDescription":
                return isContract ? "WM.WORKFLOW_ID" : CommonUtils.PM_PROJECTION_DESCRIPTION;
            case "company":
                return "GL_COMp.COMPANY_NAME";
            case CommonUtils.BUSINESS_UNIT:
                return "BU_COMp.COMPANY_NAME";
            case "workFlowStatus":
                return "WM.WORKFLOW_STATUS_ID";
            case CommonUtils.CREATED_BY:
                return "WM.CREATED_BY";
            case CommonUtils.CREATED_DATE:
                return "WM.CREATION_DATE";
            case CommonUtils.APPROVED_BY:
                return "WM.APPROVED_BY";
            case CommonUtils.APPROVED_DATE:
                return "WM.APPROVED_DATE";
            case "adjustmentTypeValue":
                return "arm_M.TRANSACTION_TYPE";

            default:
                return StringUtils.EMPTY;
        }
    }

    public List<InboxDashboardDTO> getWorkflowSearchResults(InboxDashboardDTO inboxDashboardDTO, Integer start, Integer offset, List<SortByColumn> sortByColumns, Set<Container.Filter> filters, boolean isCount) {
        List<InboxDashboardDTO> resultList = new ArrayList<InboxDashboardDTO>();
        try {
            String query = getWorkflowSearchQuery(inboxDashboardDTO, start, offset, sortByColumns, filters, isCount);
            resultList = executeSelectQuery(query);
        } catch (Exception e) {
            LOGGER.error("Exception at getWorkflowSearchResults" + e);
        }
        return resultList;
    }


    
        public List getDetailsForHistory(int workflowSystemId) {
        String sql = SQlUtil.getQuery("history-popup-others").toString();
        sql = sql.replace("[?WORKFLOW_MASTER_SID]", StringUtils.EMPTY + workflowSystemId);        
        return HelperTableLocalServiceUtil.executeSelectQuery(sql);
    }
    
    public boolean isDuplicateView(String viewName) {
        try {
            List input = new ArrayList();
            input.add(viewName.trim());
            int count = getCount(QueryUtils.getAppData(input, "duplicateViewCheck", null));
            return count != 0 ? Boolean.TRUE : Boolean.FALSE;
        } catch (Exception ex) {
            LOGGER.error("Error in View Name Duplicated" + ex);
            return Boolean.TRUE;
        }
    }
    
    /**
     * Business Process DDLB will be loaded based on the user roles
     * @param select
     * @param userId
     * @throws SystemException 
     */
    public static void getBusinessProcessValues(ComboBox select, String userId) throws SystemException {
        LOGGER.debug(" Inside Loading Business Process Values ");
        List<Role> roles = RoleLocalServiceUtil.getUserRoles(Long.parseLong(userId));
        select.removeAllItems();
        select.setImmediate(true);
        select.addItem(getSelectOne());
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(getSelectOne());
        if (roles != null && !roles.isEmpty() && !StringUtils.isBlank(bpSystemProperty)) {
            for (Role role : roles) {
                if (isValidRole(role.getName(), WorkflowConstants.getLiferayAdministrator())) {
                    select.addItems(WorkflowConstants.getBusinessProcessForAdministrator(bpSystemProperty));
                    return;
                }
            }
        }

        List<UserGroup> userRoles = UserGroupLocalServiceUtil.getUserUserGroups(Long.parseLong(userId));
        if (userRoles != null && !userRoles.isEmpty() && !StringUtils.isBlank(bpSystemProperty)) {
            for (UserGroup r : userRoles) {
                if (isValidRole(r.getName(), WorkflowConstants.getOtherUserGroups())) {
                    select.addItem(WorkflowConstants.getBusinessProcessNameContract());
                    select.addItems(WorkflowConstants.getBusinessProcess(bpSystemProperty));
                    return;
                }
                if (isValidRole(r.getName(), WorkflowConstants.getContractRoles())) {
                    select.addItem(WorkflowConstants.getBusinessProcessNameContract());
                }
                if (isValidRole(r.getName(), WorkflowConstants.getForecastRoles())) {
                    select.addItems(WorkflowConstants.getBusinessProcess(bpSystemProperty));
                }
                }
            }
        LOGGER.debug(" Ending Loading Business Process Values ");
    }

    /**
     * The method will check whether roleName is matching with the roles
     * specified in the property file.
     * @param roleName
     * @param rolesFromProperty
     * @return 
     */
    public static Boolean isValidRole(String roleName, String[] rolesFromProperty) {
        for (String roles : rolesFromProperty) {
            if (roleName.equalsIgnoreCase(roles)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
    
    public String getHelperTableSidforDescriptpion(String hSid) {
        String query = "SELECT DESCRIPTION from HELPER_TABLE WHERE LIST_NAME = 'DEDUCTION_LEVELS' and HELPER_TABLE_SID =" + hSid;
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list == null ? "" : String.valueOf(list.get(0));


    }
}
