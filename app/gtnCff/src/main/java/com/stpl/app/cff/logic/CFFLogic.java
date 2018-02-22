package com.stpl.app.cff.logic;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.bpm.dto.WorkflowRuleDTO;
import com.stpl.app.cff.abstractCff.AbstractFilterLogic;
import com.stpl.app.cff.bpm.logic.DSCalculationLogic;
import com.stpl.app.cff.bpm.logic.VarianceCalculationLogic;
import com.stpl.app.cff.dao.CFFDAO;
import com.stpl.app.cff.dao.DataSelectionDAO;
import com.stpl.app.cff.dao.impl.CFFDAOImpl;
import com.stpl.app.cff.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.cff.displayformat.main.RelationshipLevelValuesMasterBean;
import com.stpl.app.cff.dto.ApprovalDetailsDTO;
import com.stpl.app.cff.dto.CFFResultsDTO;
import com.stpl.app.cff.dto.CFFSearchDTO;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.queryUtils.CFFQueryUtils;
import com.stpl.app.cff.queryUtils.CommonQueryUtils;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.ui.fileSelection.dto.FileSelectionDTO;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.Converters;
import com.stpl.app.cff.util.NotificationUtils;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.cff.util.SysDataSourceConnection;
import com.stpl.app.cff.util.UiUtils;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.app.model.HelperTable;
import com.stpl.app.parttwo.model.CffApprovalDetails;
import com.stpl.app.parttwo.model.CffCustHierarchy;
import com.stpl.app.parttwo.model.CffDetails;
import com.stpl.app.parttwo.model.CffMaster;
import com.stpl.app.parttwo.model.CffProdHierarchy;
import com.stpl.app.parttwo.service.CffApprovalDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.CffCustHierarchyLocalServiceUtil;
import com.stpl.app.parttwo.service.CffDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.CffProdHierarchyLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.gtn.gtn2o.ws.response.workflow.GtnWsCommonWorkflowResponse;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.forecastds.dto.ViewDTO;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.QueryUtil;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.data.Container;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Manasa
 */
public class CFFLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CFFLogic.class);
    public CFFQueryUtils cffQueryUtils = new CFFQueryUtils();
    /**
     * The common utils.
     */
    private final CommonUtils commonUtils = new CommonUtils();
    private static final CFFDAO DAO = CFFDAOImpl.getInstance();
    private static Map<String, String> userMap = new HashMap<>();
    private final DataSelectionDAO dataSelectionDAO = new DataSelectionDAOImpl();
    
    /**
     * Gets latest approved CCP Projection
     *
     * @param sessionDTO
     * @return Projection List
     */
    public List<CFFResultsDTO> loadLatestCCP(SessionDTO sessionDTO) {
        List resultsList;
        try {
            resultsList = cffQueryUtils.getLatestCCP(sessionDTO.getProjectionId());
            return commonUtils.getCustomisedCFF(resultsList);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Gets latest approved CCP Projection
     *
     * @return Projection List
     */
    public List<CFFResultsDTO> loadCffDetails(int cffSid) {
        List resultsList;
        try {
            resultsList = cffQueryUtils.getCffDeatils(cffSid);
            return commonUtils.getCustomisedCFFDeatils(resultsList);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Gets the drop down list based on list name.
     *
     * @param listName the list name
     * @return the drop down list
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public static List<HelperDTO> getDropDownList(final String listName) throws PortalException, SystemException {
        LOGGER.debug("Entering getDropDownList p1:" + listName);
        final List<HelperDTO> helperList = new ArrayList<>();
        final List<HelperTable> list = DAO.getHelperTableDetailsByListName(listName);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));

            }
        }
        LOGGER.debug("return DropDownList :" + helperList.size());
        return helperList;
    }

    /**
     * Gets the drop down list.
     *
     * @param listName the list name
     * @return the drop down list
     */
    public static List<HelperDTO> loadStatusDdlb(final String listName) throws PortalException, SystemException {

        final List<HelperDTO> helperList = new ArrayList<>();

        final DynamicQuery cfpDynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtil.LIST_NAME, listName));
        List<String> statusList = new ArrayList<>();
        statusList.add("Withdrawn");
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.in(ConstantsUtil.DESCRIPTION, statusList)));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtil.DESCRIPTION));
        final List<HelperTable> list = DAO.getHelperTableList(cfpDynamicQuery);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));
            }
        }

        LOGGER.debug("returns helperList size" + helperList.size());

        return helperList;

    }

    /**
     * Save cff informations.
     *
     * @param cffMasterSystemId the cff master system id
     * @param userId the user id
     * @param cffResultsDTO
     * @return the string
     */
    public String saveCffInformation(int cffMasterSystemId, final String userId, Map<String, Object> valueMap, SessionDTO sessionDTO) {
        LOGGER.debug("inside save cff method");
        String result = "";
        String cffName = "";
        String cffType = "";
        String cffMasterSid = String.valueOf(VaadinSession.getCurrent().getAttribute(StringConstantsUtil.PROJECTION_ID));
        if (ConstantsUtils.YES.equals(String.valueOf(valueMap.get("latestEstimate")))) {
            cffName = valueMap.get("latestEstimateName").toString();
            cffType = String.valueOf(CommonUtils.getHelperTableSId(CommonUtils.LATEST_ESTIMATE, CommonUtils.CFF_TYPE));
        }
        if (ConstantsUtils.YES.equals(String.valueOf(valueMap.get("updateCycle")))) {
            cffName = valueMap.get("updateCycleName").toString();
            cffType = String.valueOf(CommonUtils.getHelperTableSId(CommonUtils.UPDATE_CYCLE, CommonUtils.CFF_TYPE));
        }
        if (ConstantsUtils.YES.equals(String.valueOf(valueMap.get("latestEstimate"))) && ConstantsUtils.YES.equals(String.valueOf(valueMap.get("updateCycle")))) {
            cffName = valueMap.get("latestEstimateName").toString() + "-" + valueMap.get("updateCycleName").toString();
            cffType = String.valueOf(CommonUtils.getHelperTableSId(CommonUtils.BOTH, CommonUtils.CFF_TYPE));
        }

        String query = "UPDATE dbo.CFF_MASTER\n"
                + "SET CFF_NAME='" + cffName + "',CFF_TYPE='" + cffType + "',\n"
                + " CFF_ELIGIBLE_DATE= '"+String.valueOf(valueMap.get("cffEligiblDate")) + "' WHERE CFF_MASTER_SID = " + cffMasterSid;

        HelperTableLocalServiceUtil.executeUpdateQuery(query);

        VaadinSession.getCurrent().setAttribute(CommonUtils.CFF_MASTER_SYSTEM_ID_SESSION, cffMasterSystemId);
        String noOfLevel = getNoOfLevelFromJbpm(sessionDTO, cffMasterSid, userId);
        result = submitCffPendingDetails(userId, Integer.parseInt(cffMasterSid), noOfLevel);
        if (result.equals(CommonUtils.FAIL) || noOfLevel.isEmpty()) {
            return CommonUtils.FAIL;
        }
        LOGGER.debug("Exits save cff method");
        return result;
    }
    
    /**
     * Update cff information
     *
     * @param cffMasterSystemId
     * @param userId
     * @return
     */
    public String updateCffInformation(int cffMasterSystemId, final String userId) {
        String result = "";
        try {
            LOGGER.debug("inside update cff method");

            CffMaster cffMaster;
            cffMaster = DAO.getCffMaster(cffMasterSystemId);
            cffMaster.setModifiedBy(Integer.parseInt(userId));
            cffMaster.setModifiedDate(new Date());
            cffMaster.setInboundStatus(ConstantsUtil.INBOUND_STATUS_UPDATE);
            DAO.updateCffMaster(cffMaster);

        } catch (SystemException | PortalException ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.debug("Exits update cff method");
        return result;
    }

    /**
     * Update cff information
     *
     * @param cffMasterSystemId
     * @param userId
     * @return
     */
    public String approveCffInformation(int cffMasterSystemId, final String userId) {
        List input = new ArrayList();
        String result = "";
        try {
            //Needed
            input.add(userId);
            input.add(cffMasterSystemId);

            CommonQueryUtils.updateAppData(input, "updatecffdetails");

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.debug("Exits update cff method");
        return result;
    }

    /**
     * Deleting from Cff master table
     *
     * @param cffMasterSystemId
     * @return
     */
    public void deleteCff(int cffMasterSystemId, final String tableName) {
        List input = new ArrayList();
        input.add(tableName);
        input.add(cffMasterSystemId);
        CommonQueryUtils.updateAppData(input, "deleteCff");
    }

    /**
     * Deleting from Cff Approval details
     *
     * @param cffMasterSystemId
     * @return
     */
    public String deleteCffApprovalDetails(int cffMasterSystemId) {
        try {
            List<CffApprovalDetails> approvalList = DAO.getApprovalDetails(cffMasterSystemId);
            if (approvalList != null) {
                CffApprovalDetails approvalDetails;
                for (int i = 0; i < approvalList.size(); i++) {
                    approvalDetails = approvalList.get(i);
                    approvalDetails.setInboundStatus(ConstantsUtil.INBOUND_STATUS_DELETE);
                    DAO.updateCffApprovalDetails(approvalDetails);
                }
            }

        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
            return "Fail";
        }

        return StringConstantsUtil.SUCCESS;
    }
    
    /**
     * Delete from Cff Details Table
     *
     * @param cffMasterSystemId
     * @return
     */
    public String deleteCffDetails(int cffMasterSystemId) {
        try {
            List<CffDetails> cffList = DAO.getCffDetails(cffMasterSystemId);
            if (cffList != null) {
                CffDetails cffDetails;
                for (int i = 0; i < cffList.size(); i++) {
                    cffDetails = cffList.get(i);
                    cffDetails.setInboundStatus(ConstantsUtil.INBOUND_STATUS_DELETE);
                    DAO.updateCffDetails(cffDetails);
                }
            }

        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
            return "Fail";
        }

        return StringConstantsUtil.SUCCESS;
    }

    /**
     * Delete from CffDocDetails table
     *
     * @param cffMasterSystemId
     * @return
     */
    public String deleteCffAdditionalInfo(int cffMasterSystemId) {
        try {
            String sql = "";

            sql = "DELETE FROM CFF_ADDITIONAL_INFO WHERE CFF_MASTER_SID=" + cffMasterSystemId + ";";
            sql += "DELETE FROM CFF_DOC_DETAILS WHERE CFF_MASTER_SID=" + cffMasterSystemId + ";";

            DAO.executeUpdateQuery(sql);

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }
        return StringConstantsUtil.SUCCESS;
    }

    /**
     * Save cff approve details.
     *
     * @param userId the user id
     * @param cffId the cff id
     * @return the string
     */
    public String submitCffApproveDetails(String userId, int cffId) {
        final DynamicQuery cffDetailsDynamicQuery = CffApprovalDetailsLocalServiceUtil.dynamicQuery();
        int create = Long.valueOf(CounterLocalServiceUtil.increment()).intValue();
        final CffApprovalDetails cffApprovalDetails = CffApprovalDetailsLocalServiceUtil.createCffApprovalDetails(create);
        CffApprovalDetails cffApprovalDetailsOld;
        List<CffApprovalDetails> resultsList = new ArrayList<>();
        int approvalSequence;
        try {
            cffApprovalDetails.setApprovedBy(Integer.parseInt(userId));
            cffApprovalDetails.setApprovedDate(new Date());
            cffApprovalDetails.setCffMasterSid(cffId);
            cffApprovalDetails.setInboundStatus(ConstantsUtil.INBOUND_STATUS_ADD);
            cffDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.CFF_MASTER_SID, cffId));
            cffDetailsDynamicQuery.add(RestrictionsFactoryUtil.ne("inboundStatus", "D"));
            cffDetailsDynamicQuery.addOrder(OrderFactoryUtil.desc(StringConstantsUtil.APPROVAL_SEQUENCE));
            resultsList = CffDetailsLocalServiceUtil.dynamicQuery(cffDetailsDynamicQuery);
            if (resultsList == null || resultsList.isEmpty()) {
                cffApprovalDetails.setApprovalSequence(1);
                cffApprovalDetails.setApprovalStatus(CommonUtils.getCodeFromHelperTable(CommonUtils.WORKFLOW_STATUS_PENDING, CommonUtils.WORKFLOW_STATUS));
            } else {
                cffApprovalDetailsOld = resultsList.get(0);
                approvalSequence = cffApprovalDetailsOld.getApprovalSequence() + 1;
                cffApprovalDetails.setApprovalSequence(approvalSequence);
                cffApprovalDetails.setApprovalStatus(CommonUtils.getCodeFromHelperTable(CommonUtils.WORKFLOW_STATUS_PENDING, CommonUtils.WORKFLOW_STATUS));
            }

            DAO.addCffApprovalDetails(cffApprovalDetails);
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
            return CommonUtils.FAIL;
        }
        return CommonUtils.SUCCESS;
    }
    

    /**
     * Save cff approve details.
     *
     * @param userId the user id
     * @param cffId the cff id
     * @return the string
     */
    public String submitCffPendingDetails(String userId, int cffId, String noOfLevel) {
        List list = new ArrayList();
        list.add(cffId);
        List<Object[]> listSeq = CommonQueryUtils.getAppData(list, "selectCFFAPP", null);
        int approveSeq = getCount(listSeq);
        List input = new ArrayList();
        try {
            input.add(Integer.valueOf(userId));
            input.add(cffId);
            String workFlowId = getWorkFlowStatusId("Pending");
            if (approveSeq != 0) {
                input.add(1);
            } else {
                input.add(approveSeq + 1);
            }
            input.add(workFlowId);
            input.add(noOfLevel);
            CommonQueryUtils.updateAppData(input, "updatePendingdetails");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return CommonUtils.FAIL;
        }
        return CommonUtils.SUCCESS;
    }

    /**
     * Approve CFF method
     *
     * @param userId - User information ID
     * @param cffId - CFF Master System generated ID
     * @return Success message
     */
    public List<Object> approveCffApproveDetails(String userId, int cffId, String status) {
        List input = new ArrayList();
        List resultList = new ArrayList();
        Boolean callOutboundPrc = true;
        try {
            List list = new ArrayList();
            int noOfLevel = 0;
            int currentLevel = 0;
            list.add(cffId);
            List<Object[]> listSeq = CommonQueryUtils.getAppData(list, "getCFFLevel", null);
            if (listSeq != null) {
                Object[] obj = listSeq.get(0);
                if (obj[NumericConstants.SEVEN] != null) {
                    String level = String.valueOf(obj[NumericConstants.SEVEN]);
                    noOfLevel = Integer.parseInt(level);
                }
            }
            List<Object[]> currentLevelList = CommonQueryUtils.getAppData(list, "getCFFCurrentLevel", null);
            if (currentLevelList != null) {
                Object[] obj = currentLevelList.get(0);
                if (obj[NumericConstants.FOUR] != null) {
                    String level = String.valueOf(obj[NumericConstants.FOUR]);
                    currentLevel = Integer.parseInt(level);
                }
            }
            input.add(Integer.valueOf(userId));
            input.add(cffId);
            input.add(status.equals(CommonUtils.WORKFLOW_STATUS_APPROVED) ? String.valueOf(currentLevel + 1) : String.valueOf(currentLevel));
            if (CommonUtils.WORKFLOW_STATUS_APPROVED.equals(status)) {
                if (noOfLevel == currentLevel) {
                    callOutboundPrc = true;
                    input.add("GETDATE()");
                    input.add(CommonUtils.WORKFLOW_STATUS_APPROVED);
                    input.add(CommonUtils.WORKFLOW_STATUS_APPROVED);
                    updateActiveFromDate(cffId);
                } else {
                    callOutboundPrc = false;
                    input.add(null);
                    input.add(CommonUtils.WORKFLOW_STATUS_PENDING);
                    input.add(CommonUtils.WORKFLOW_STATUS_PENDING);
                }
            } else {
                input.add(null);
                input.add(status);
                input.add(CommonUtils.WORKFLOW_STATUS_CANCELLED.equals(status) ? "Canceled" : status);
            }
            input.add(cffId);
            CommonQueryUtils.updateAppData(input, "updateapprovaldetails");

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            resultList.add(CommonUtils.FAIL);
            return resultList;
        }
        resultList.add(CommonUtils.SUCCESS);
        resultList.add(callOutboundPrc);
        return resultList;
    }

    /**
     * Reject CFF method
     *
     * @param userId - User information ID
     * @param cffId - CFF Master System generated ID
     * @return Success message
     */
    public String rejectCffApproveDetails(String userId, int cffId) {
        final DynamicQuery cffDetailsDynamicQuery = CffApprovalDetailsLocalServiceUtil.dynamicQuery();
        int create = Long.valueOf(CounterLocalServiceUtil.increment()).intValue();
        final CffApprovalDetails cffApprovalDetails = CffApprovalDetailsLocalServiceUtil.createCffApprovalDetails(create);
        CffApprovalDetails cffApprovalDetailsOld;
        List<CffApprovalDetails> resultsList = new ArrayList<>();
        int approvalSequence;
        try {
            cffApprovalDetails.setApprovedBy(Integer.parseInt(userId));
            cffApprovalDetails.setApprovedDate(new Date());
            cffApprovalDetails.setCffMasterSid(cffId);
            cffApprovalDetails.setInboundStatus(ConstantsUtil.INBOUND_STATUS_ADD);
            cffDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.CFF_MASTER_SID, cffId));
            cffDetailsDynamicQuery.addOrder(OrderFactoryUtil.desc(StringConstantsUtil.APPROVAL_SEQUENCE));
            resultsList = CffDetailsLocalServiceUtil.dynamicQuery(cffDetailsDynamicQuery);

            cffApprovalDetailsOld = resultsList.get(0);
            approvalSequence = cffApprovalDetailsOld.getApprovalSequence() + 1;
            cffApprovalDetails.setApprovalSequence(approvalSequence);
            cffApprovalDetails.setApprovalStatus(CommonUtils.getCodeFromHelperTable(CommonUtils.WORKFLOW_STATUS_REJECTED, CommonUtils.WORKFLOW_STATUS));

            DAO.addCffApprovalDetails(cffApprovalDetails);
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
            return CommonUtils.FAIL;
        }
        return CommonUtils.SUCCESS;
    }

    /**
     * Reject CFF method
     *
     * @param userId - User information ID
     * @param cffId - CFF Master System generated ID
     * @return Success message
     */
    public String cancelCffApproveDetails(String userId, int cffId) {
        final DynamicQuery cffDetailsDynamicQuery = CffApprovalDetailsLocalServiceUtil.dynamicQuery();
        int create = Long.valueOf(CounterLocalServiceUtil.increment()).intValue();
        final CffApprovalDetails cffApprovalDetails = CffApprovalDetailsLocalServiceUtil.createCffApprovalDetails(create);
        CffApprovalDetails cffApprovalDetailsOld;
        List<CffApprovalDetails> resultsList = new ArrayList<>();
        int approvalSequence;
        try {
            cffApprovalDetails.setApprovedBy(Integer.parseInt(userId));
            cffApprovalDetails.setApprovedDate(new Date());
            cffApprovalDetails.setCffMasterSid(cffId);
            cffApprovalDetails.setInboundStatus(ConstantsUtil.INBOUND_STATUS_ADD);
            cffDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.CFF_MASTER_SID, cffId));
            cffDetailsDynamicQuery.addOrder(OrderFactoryUtil.desc(StringConstantsUtil.APPROVAL_SEQUENCE));
            resultsList = CffDetailsLocalServiceUtil.dynamicQuery(cffDetailsDynamicQuery);

            cffApprovalDetailsOld = resultsList.get(0);
            approvalSequence = cffApprovalDetailsOld.getApprovalSequence() + 1;
            cffApprovalDetails.setApprovalSequence(approvalSequence);
            cffApprovalDetails.setApprovalStatus(CommonUtils.getCodeFromHelperTable(CommonUtils.WORKFLOW_STATUS_CANCELLED, CommonUtils.WORKFLOW_STATUS));

            DAO.addCffApprovalDetails(cffApprovalDetails);
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
            return CommonUtils.FAIL;
        }
        return CommonUtils.SUCCESS;
    }

    public int getSearchCount(final CFFSearchDTO binderDto) {
        int count = 0;
        List parameters;
        List resultCountList;
        String filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(binderDto.getFilters(), getFilterMap(false)).toString();
        parameters = getParametersForCffSearch(binderDto);
        filterQuery = filterQuery.replace(StringConstantsUtil.WHERE, "AND");
        parameters.add(filterQuery);
        resultCountList = CommonQueryUtils.getAppData(parameters, "getCffSearchCount", null);
        count = getCount(resultCountList);
        LOGGER.debug("Count For method" + count);
        return count;
    }

    /**
     *
     * @param parameters
     * @return
     */
    public List getParametersForCffSearch(CFFSearchDTO binderDto) {
        List input = new ArrayList();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Connection connection = null;
        try {
            connection = SysDataSourceConnection.getConnection();
            input.add(connection.getCatalog());
            if (CommonUtils.isValidCriteria(binderDto.getFinancialForecastId())) {
                String financialForecastId = binderDto.getFinancialForecastId();
                financialForecastId = financialForecastId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
                input.add(financialForecastId);
            } else {
                input.add(CommonUtils.CHAR_PERCENT);
            }
            if (CommonUtils.isValidCriteria(binderDto.getFinancialForecastName())) {
                String financialForecastName = binderDto.getFinancialForecastName();
                financialForecastName = financialForecastName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
                input.add(financialForecastName);
            } else {
                input.add(CommonUtils.CHAR_PERCENT);
            }
            if (binderDto.getTypeDdlb() != null) {
                input.add(binderDto.getTypeDdlb().getId());
            } else {
                input.add(CommonUtils.CHAR_PERCENT);
            }
            if (binderDto.getStatusDdlb() != null) {
                input.add(binderDto.getStatusDdlb().getId());
            } else {
                input.add(CommonUtils.CHAR_PERCENT);
            }
            if (binderDto.getCreationFromDate() != null) {
                input.add(" AND CREATED_DATE >= '" + sdf.format(binderDto.getCreationFromDate()) + "'");
            } else {
                input.add(" ");
            }
            if (binderDto.getCreationToDate() != null) {
                input.add(" AND CREATED_DATE <= '" + sdf.format(binderDto.getCreationToDate()) + "'");
            } else {
                input.add(" ");
            }
            if (binderDto.getApprovalFromDate() != null) {
                input.add(" AND APPROVED_DATE >= '" + sdf.format(binderDto.getApprovalFromDate()) + "'");
            } else {
                input.add(" ");
            }
            if (binderDto.getApprovalToDate() != null) {
                input.add(" AND APPROVED_DATE <= '" + sdf.format(binderDto.getApprovalToDate()) + "'");
            } else {
                input.add(" ");
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
            }

        }
        return input;
    }

    /**
     * Gets the search results.
     *
     * @param columns columns
     * @return the search results
     */
    public List<CFFSearchDTO> getSearchResults(final CFFSearchDTO binderDto) {
        LOGGER.debug("Inside Search Results");
        String filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(binderDto.getFilters(), getFilterMap(false)).toString();
        String orderBy = AbstractFilterLogic.getInstance().orderByQueryGenerator(binderDto.getOrderByColumns(), getFilterMap(true)).toString();
        List<CFFSearchDTO> cffMasterList;
        List parameterslist;
        parameterslist = getParametersForCffSearch(binderDto);
        List<Object[]> resultList;
        if (filterQuery != null) {
            filterQuery = filterQuery.replace(StringConstantsUtil.WHERE, "AND");
            parameterslist.add(filterQuery);
        } else {
            parameterslist.add(" ");
        }
        if (orderBy != null) {
            parameterslist.add(orderBy);
        }
        parameterslist.add(binderDto.getStartIndex());
        parameterslist.add(binderDto.getEndIndex());
        resultList = CommonQueryUtils.getAppData(parameterslist, "getCffSearchResults", null);
        cffMasterList = commonUtils.getCustomizedSearchResults(resultList);
        return cffMasterList;
    }

    /**
     * Get Approval List for edit
     *
     * @param cffSid
     * @return List of Customised Approval list data for a cff
     */
    public List<ApprovalDetailsDTO> getApprovalDetailsForCff(int cffSid) {
        List<ApprovalDetailsDTO> approvalList = new ArrayList<>();
        try {

            List<CffApprovalDetails> approvaldetails = DAO.getApprovalDetails(cffSid);
            approvalList = commonUtils.getCustomisedApprovalDetails(approvaldetails);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(CFFLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return approvalList;
    }

    public static String filterUser(String filter) {
        List<String> keys = new ArrayList<>();
        String userIds;
        if (userMap != null) {
            for (Map.Entry<String, String> entry : userMap.entrySet()) {
                if ((String.valueOf(entry.getValue()).toLowerCase().trim()).contains(filter.toLowerCase().trim())) {
                    keys.add(String.valueOf(entry.getKey()));
                }
            }
        }
        if (!keys.isEmpty()) {
            userIds = CommonUtils.stringListToString(keys);
        } else {
            userIds = "0";
        }
        return userIds;
    }

    public static void mapUsers() {
        userMap.clear();
        userMap = getAllUsers();
    }

    public static Map<String, String> getAllUsers() {
        List<Object> userList = new ArrayList<>();
        Map<String, String> userMap = new HashMap<>();
        DynamicQuery query = UserLocalServiceUtil.dynamicQuery();
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.USER_ID));
        productProjectionList.add(ProjectionFactoryUtil.property("firstName"));
        productProjectionList.add(ProjectionFactoryUtil.property("lastName"));
        query.setProjection(productProjectionList);
        try {
            userList = UserLocalServiceUtil.dynamicQuery(query);
            for (int loop = 0, limit = userList.size(); loop < limit; loop++) {
                Object[] array = (Object[]) userList.get(loop);
                userMap.put(String.valueOf(array[0]), String.valueOf(array[NumericConstants.TWO]) + ", " + String.valueOf(array[1]));
            }
        } catch (Exception ex) {
            LOGGER.error(ex + " in CommonUtils ~ getAllUsers");
        }
        return userMap;
    }

    public static int getCount(List<Object[]> list) {
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            int count = obj == null ? 0 : (Integer) obj;
            return count;
        }
        return 0;
    }

    private Map<String, String> getFilterMap(boolean isOrder) {
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("financialForecastId", "CFF_MASTER_SID");
        filterMap.put("financialForecastName", "cff_name");
        filterMap.put("typeDesc", "cff_type");

        if (isOrder) {
            filterMap.put("finalApprovalDate", "description ?, APPROVED_DATE");
            filterMap.put("statusDesc", "description");
            filterMap.put("approvedBy", "description ?, firstname");
        } else {
            filterMap.put("finalApprovalDate", "APPROVED_DATE");
            filterMap.put("statusDesc", "approval_status");
            filterMap.put("approvedBy", " approval_status=(SELECT HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = 'WorkFlowStatus' AND DESCRIPTION = 'Approved') AND APPROVED_BY");
        }
        filterMap.put(StringConstantsUtil.ACTIVE_FROM_DATE, "ACTIVE_FROM_DATE");
        filterMap.put(StringConstantsUtil.ACTIVE_TO_DATE, "ACTIVE_TO_DATE");
        return filterMap;
    }
    

    public Object getFileName(boolean count, SessionDTO sessionDTO, Set<Container.Filter> filters, String businessUnit) {
        LOGGER.debug("Inside getFileName");
        List<Object[]> resultList = new ArrayList<>();
        List<FileSelectionDTO> retList = new ArrayList<>();
        List list = new ArrayList();
        try {
            if (!count) {
                String mode = sessionDTO.getAction();
                if (mode.equals("edit") || mode.equals("view")) {
                    String projId = String.valueOf(VaadinSession.getCurrent().getAttribute(StringConstantsUtil.PROJECTION_ID));
                    resultList = getFileSelection(projId, filters);
                    if (resultList != null) {
                        for (int i = 0; i < resultList.size(); i++) {
                            final FileSelectionDTO dto = new FileSelectionDTO();
                            final Object[] obj = resultList.get(i);
                            dto.setFileManagementSid(String.valueOf(obj[NumericConstants.TWO]));
                            dto.setFileName(String.valueOf(obj[NumericConstants.THREE]));
                            dto.setVersion(String.valueOf(obj[NumericConstants.FOUR]));
                            dto.setFileType(obj[NumericConstants.SEVEN] != null ? String.valueOf(obj[NumericConstants.SEVEN]) : StringUtils.EMPTY);
                            if (obj[NumericConstants.FIVE] != null) {
                                dto.setActiveFromDate((Date) obj[NumericConstants.FIVE]);
                            } else {
                                dto.setActiveFromDate(null);
                            }
                            if (obj[NumericConstants.SIX] != null) {
                                dto.setActiveToDate((Date) obj[NumericConstants.SIX]);
                            } else {
                                dto.setActiveToDate(null);
                            }
                            retList.add(dto);
                        }
                        return retList;
                    }
                } else {
                    list.add(businessUnit);
                    list.add(sessionDTO.getCompanySystemId());
                    if (filters != null) {
                        String filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(filters, getFileSelectionMap()).toString();
                        filterQuery = filterQuery.replace(StringConstantsUtil.WHERE, StringConstantsUtil.SPACE_AND_SPACE);
                        list.add(filterQuery);
                    } else {
                        list.add(StringUtils.EMPTY);
                    }
                    resultList = CommonQueryUtils.getAppData(list, "fileselectiondistinct", null);
                }
                if (resultList != null) {
                    for (int i = 0; i < resultList.size(); i++) {
                        final FileSelectionDTO dto = new FileSelectionDTO();
                        final Object[] obj = resultList.get(i);
                        dto.setFileManagementSid(String.valueOf(obj[0]));
                        dto.setFileName(String.valueOf(obj[1]));
                        dto.setVersion(String.valueOf(obj[NumericConstants.THREE]));
                        dto.setFileType(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO]) : StringUtils.EMPTY);
                        dto.setActiveFromDate((Date) obj[NumericConstants.FOUR]);
                        dto.setActiveToDate((Date) obj[NumericConstants.FIVE]);
                        dto.setFileTypeId(String.valueOf(obj[NumericConstants.SIX]));
                        dto.setFileChanged(false);
                        retList.add(dto);
                    }
                }
                return retList;
            } else {
                String mode = sessionDTO.getAction();
                if (mode.equals("edit") || mode.equals("view")) {
                    String projId = String.valueOf(VaadinSession.getCurrent().getAttribute(StringConstantsUtil.PROJECTION_ID));
                    int fileCount = getFileSelectionCount(projId, filters);
                    return fileCount;
                } else {

                    list.add(businessUnit);
                    list.add(sessionDTO.getCompanySystemId());
                    if (filters != null) {
                        String filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(filters, getFileSelectionMap()).toString();
                        filterQuery = filterQuery.replace(StringConstantsUtil.WHERE, StringConstantsUtil.SPACE_AND_SPACE);
                        list.add(filterQuery);
                    } else {
                        list.add(StringUtils.EMPTY);
                    }
                    List<Object> countList = CommonQueryUtils.getAppData(list, "fileselectiondistinctcount", null);
                    return countList.get(0);
                }
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
    

    /**
     * Generates Saves a projection.
     *
     * @param dataSelectionDTO the data selection dto
     * @return the projection ID
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public int saveCFFMaster(final DataSelectionDTO dataSelectionDTO, boolean isUpdate, int projectionIdValue,SessionDTO sessionDTO) {
        int projectionId = 0;
        Object[] dedRelId = deductionRelationBuilderId(dataSelectionDTO.getProdRelationshipBuilderSid().equals("0") ? "0" : String.valueOf(dataSelectionDTO.getProdRelationshipBuilderSid()));
        SimpleDateFormat DBDate = new SimpleDateFormat("yyyy-MM-dd");
        String userId = (String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID);
        String query = "INSERT INTO CFF_MASTER (CFF_ELIGIBLE_DATE,CFF_TYPE, CFF_NAME, ACTIVE_FROM_DATE, ACTIVE_TO_DATE, CFF_OFFICIAL, CUSTOMER_HIERARCHY_SID, CUSTOMER_HIERARCHY_LEVEL,\n"
                + "    CUSTOMER_HIER_VERSION_NO, COMPANY_GROUP_SID, CUSTOMER_HIERARCHY_INNER_LEVEL, CUST_RELATIONSHIP_BUILDER_SID, COMPANY_MASTER_SID, PRODUCT_HIERARCHY_SID,\n"
                + "    PRODUCT_HIERARCHY_LEVEL, PRODUCT_HIER_VERSION_NO, ITEM_GROUP_SID, PRODUCT_HIERARCHY_INNER_LEVEL, PROD_RELATIONSHIP_BUILDER_SID, INBOUND_STATUS, CREATED_BY,\n"
                + "    CREATED_DATE, MODIFIED_BY, MODIFIED_DATE,BUSINESS_UNIT, PROJECTION_CUST_VERSION, PROJECTION_PROD_VERSION @DEDUCTION_ADDITION ) VALUES ('@CFFELIGDATE','@CFF_TYPE','@CFF_NAME',@ACTIVE_FROM_DATE, @ACTIVE_TO_DATE, 0,\n"
                + "    '@CUSTOMER_HIERARCHY_SID', '@CUSTOMER_HIERARCHY_LEVEL', '@CUSTOMER_HIER_VERSION_NO', @COMPANY_GROUP_SID,\n"
                + "    '@CUSTOMER_HIERARCHY_INNER_LEVEL', '@CUST_RELATIONSHIP_BUILDER_SID', '@COMPANY_MASTER_SID', '@PRODUCT_HIERARCHY_SID',\n"
                + "    '@PRODUCT_HIERARCHY_LEVEL', '@PRODUCT_HIER_VERSION_NO', @ITEM_GROUP_SID, '@PRODUCT_HIERARCHY_INNER_LEVEL',\n"
                + "    '@PROD_RELATIONSHIP_BUILDER_SID', 'A', '@CREATED_BY', '@CREATED_DATE', '@MODIFIED_BY', '@MODIFIED_DATE','@BUSINESS_UNIT',@PROJCUSTVERSION, @PROJPRODVERSION @DED_ADD_VALUES ) ";

        if (isUpdate) {
            List l = new ArrayList();
            l.add(projectionIdValue);
            query = CommonQueryUtils.getAppQuery(l, "updateProjection");
        }
        query = query.replace("@CFF_TYPE", "0");
        query = query.replace("@CFFELIGDATE", String.valueOf(DBDate.format(sessionDTO.getCffEligibleDate())));
        query = query.replace("@CFF_NAME", "");
        query = query.replace("@ACTIVE_FROM_DATE", "null");
        query = query.replace("@ACTIVE_TO_DATE", "null");

        query = query.replace("@CUSTOMER_HIERARCHY_SID", dataSelectionDTO.getCustomerHierSid().equals("0") ? "0" : String.valueOf(dataSelectionDTO.getCustomerHierSid()));
        query = query.replace("@CUSTOMER_HIERARCHY_LEVEL", dataSelectionDTO.getCustomerHierarchyLevel());
		query = query.replace("@CUSTOMER_HIER_VERSION_NO", String.valueOf(dataSelectionDTO.getCustomerHierVersionNo()));

        query = query.replace("@COMPANY_GROUP_SID", dataSelectionDTO.getCustomerGrpSid().equals("0") ? "null" : "'" + String.valueOf(dataSelectionDTO.getCustomerGrpSid()) + "'");
        query = query.replace("@CUSTOMER_HIERARCHY_INNER_LEVEL", dataSelectionDTO.getCustomerHierarchyInnerLevel());
        query = query.replace("@CUST_RELATIONSHIP_BUILDER_SID", dataSelectionDTO.getCustRelationshipBuilderSid().equals("0") ? "0" : String.valueOf(dataSelectionDTO.getCustRelationshipBuilderSid()));
        query = query.replace("@COMPANY_MASTER_SID", dataSelectionDTO.getCompanySid() == null || "0".equals(dataSelectionDTO.getCompanySid()) ? "null" : String.valueOf(dataSelectionDTO.getCompanySid()));

        query = query.replace("@PRODUCT_HIERARCHY_SID", dataSelectionDTO.getProdHierSid().equals("0") ? "0" : String.valueOf(dataSelectionDTO.getProdHierSid()));
        query = query.replace("@PRODUCT_HIERARCHY_LEVEL", dataSelectionDTO.getProductHierarchyLevel());
		query = query.replace("@PRODUCT_HIER_VERSION_NO", String.valueOf(dataSelectionDTO.getProductHierVersionNo()));
        query = query.replace("@ITEM_GROUP_SID", dataSelectionDTO.getProdGrpSid().equals("0") ? "null" : "'" + String.valueOf(dataSelectionDTO.getProdGrpSid()) + "'");

        query = query.replace("@PRODUCT_HIERARCHY_INNER_LEVEL", dataSelectionDTO.getProductHierarchyInnerLevel());
        query = query.replace("@PROD_RELATIONSHIP_BUILDER_SID", dataSelectionDTO.getProdRelationshipBuilderSid().equals("0") ? "0" : String.valueOf(dataSelectionDTO.getProdRelationshipBuilderSid()));
        query = query.replace("@CREATED_BY", userId);
        query = query.replace("@MODIFIED_BY", userId);

        query = query.replace("@CREATED_DATE", DBDate.format(new Date()));
        query = query.replace("@MODIFIED_DATE", DBDate.format(new Date()));
        query = query.replace("@BUSINESS_UNIT", dataSelectionDTO.getBusinessUnitSystemId() + "");
        query = query.replace("@PROJCUSTVERSION", dataSelectionDTO.getCustomerRelationShipVersionNo() + StringUtils.EMPTY);
        query = query.replace("@PROJPRODVERSION", dataSelectionDTO.getProductRelationShipVersionNo() + StringUtils.EMPTY);
        
       
        query = query.replace("@DEDUCTION_ADDITION", CommonUtils.isValueEligibleForLoading() ? " ,DEDUCTION_HIERARCHY_SID,DED_RELATIONSHIP_BULDER_SID " : StringUtils.EMPTY);
        query = query.replace("@DED_ADD_VALUES", CommonUtils.isValueEligibleForLoading() ? " ,'@DEDUCTION_HIERARCHY_SID','@DED_RELATIONSHIP_BULDER_SID' " : StringUtils.EMPTY);
        query = query.replace("@DED_RELATIONSHIP_BULDER_SID", dedRelId[0] + "");
        query = query.replace("@DEDUCTION_HIERARCHY_SID", dedRelId[1] + "");
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        if (isUpdate) {
            return projectionIdValue;
        } else {
            String cffQuery = "select IDENT_CURRENT( 'cff_master' )";
            List list = HelperTableLocalServiceUtil.executeSelectQuery(cffQuery);
            if (list != null && !list.isEmpty()) {
                Object obj = list.get(0);
                String ccfMasterId = String.valueOf(obj);
                projectionId = Integer.parseInt(ccfMasterId);
            }
            return projectionId;
        }

    }
    
    public Object[] deductionRelationBuilderId(String prdRelSid){
        List input = new ArrayList<>();
        input.add(prdRelSid);
        String sql = CommonQueryUtils.getAppQuery(input, "DeductionRelationshipId");
        List list = HelperTableLocalServiceUtil.executeSelectQuery(sql);
        return  (Object[])list.get(0);
    }

    /**
     * Save Product hierarchy logic.
     *
     * @param levelList the level list
     * @throws java.lang.Exception
     */
    public void saveProductHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids, final int projectionId, final List<String> addLevels, final String indicator) throws SystemException {
        LOGGER.debug("saveProductHierarchyLogic projectionId" + projectionId);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(StringConstantsUtil.INDICATOR, "getChildLevelRLSid");
        parameters.put("rlSids", endLevelSids);
        parameters.put(StringConstantsUtil.PROJECTION_ID, projectionId);
        parameters.put("tableName", "CFF_PROD_HIERARCHY");
        parameters.put("module", "cff");
        List<Object> endLevels = null;
        if (endLevelSids != null && !endLevelSids.isEmpty()) {
            endLevels = dataSelectionDAO.executeQuery(parameters);
        }
        int create = NumericConstants.ZERO;
        CffProdHierarchy cffProdHierarchy = null;
        try {
            if ("update".equals(indicator)) {
                for (String rsId : addLevels) {
                    create = Long.valueOf(CounterLocalServiceUtil.increment()).intValue();
                    cffProdHierarchy = CffProdHierarchyLocalServiceUtil.createCffProdHierarchy(create);
                    cffProdHierarchy.setCffMasterSid(projectionId);
                    cffProdHierarchy.setRelationshipLevelSid(UiUtils.parseStringToInteger(String.valueOf(rsId)));
                    dataSelectionDAO.addProjectionProdHierarchy(cffProdHierarchy);
                }
            } else if ("save".equals(indicator)) {
                for (Leveldto dto : levelList) {
                    create = Long.valueOf(CounterLocalServiceUtil.increment()).intValue();
                    cffProdHierarchy = CffProdHierarchyLocalServiceUtil.createCffProdHierarchy(create);
                    cffProdHierarchy.setCffMasterSid(projectionId);
                    cffProdHierarchy.setRelationshipLevelSid(dto.getRelationshipLevelSid());
                    dataSelectionDAO.addProjectionProdHierarchy(cffProdHierarchy);
                }
            }
            if (endLevels != null && !endLevels.isEmpty()) {
                for (Object relationshipLevelSid : endLevels) {
                    create = Long.valueOf(CounterLocalServiceUtil.increment()).intValue();
                    cffProdHierarchy = CffProdHierarchyLocalServiceUtil.createCffProdHierarchy(create);
                    cffProdHierarchy.setCffMasterSid(projectionId);
                    cffProdHierarchy.setRelationshipLevelSid(UiUtils.parseStringToInteger(String.valueOf(relationshipLevelSid)));
                    dataSelectionDAO.addProjectionProdHierarchy(cffProdHierarchy);
                }
            }
        } catch (Exception e) {
            LOGGER.debug(e + " saveProductHierarchyLogic");
        }
    }
    

    /**
     * Save customer hierarchy logic.
     *
     * @param levelList the level list
     * @param projectionId
     * @throws java.lang.Exception
     */
    public void saveCustomerHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids, final int projectionId, final List<String> addLevels, final String indicator) throws SystemException {
        LOGGER.debug("saveCustomerHierarchyLogic  projectionId " + projectionId);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(StringConstantsUtil.INDICATOR, "getChildLevelRLSid");
        parameters.put(StringConstantsUtil.PROJECTION_ID, projectionId);
        parameters.put("rlSids", endLevelSids);
        parameters.put("tableName", "CFF_CUST_HIERARCHY");
        parameters.put("module", "cff");
        List<Object> endLevels = null;
        if (endLevelSids != null && !endLevelSids.isEmpty()) {
            endLevels = dataSelectionDAO.executeQuery(parameters);
        }
        int create = NumericConstants.ZERO;
        CffCustHierarchy cffCustHierarchy = null;
        try {
            if ("update".equals(indicator)) {
                for (String rsId : addLevels) {
                    create = Long.valueOf(CounterLocalServiceUtil.increment()).intValue();
                    cffCustHierarchy = CffCustHierarchyLocalServiceUtil.createCffCustHierarchy(create);
                    cffCustHierarchy.setCffMasterSid(projectionId);
                    cffCustHierarchy.setRelationshipLevelSid(UiUtils.parseStringToInteger(String.valueOf(rsId)));
                    dataSelectionDAO.addProjectionCustHierarchy(cffCustHierarchy);
                }
            } else if ("save".equals(indicator)) {
                for (Leveldto levelListDto : levelList) {
                    create = Long.valueOf(CounterLocalServiceUtil.increment()).intValue();
                    cffCustHierarchy = CffCustHierarchyLocalServiceUtil.createCffCustHierarchy(create);
                    cffCustHierarchy.setCffMasterSid(projectionId);
                    cffCustHierarchy.setRelationshipLevelSid(levelListDto.getRelationshipLevelSid());
                    dataSelectionDAO.addProjectionCustHierarchy(cffCustHierarchy);
                }
            }
            if (endLevels != null && !endLevels.isEmpty()) {
                for (Object relationshipLevelSid : endLevels) {
                    create = Long.valueOf(CounterLocalServiceUtil.increment()).intValue();
                    cffCustHierarchy = CffCustHierarchyLocalServiceUtil.createCffCustHierarchy(create);
                    cffCustHierarchy.setCffMasterSid(projectionId);
                    cffCustHierarchy.setRelationshipLevelSid(UiUtils.parseStringToInteger(String.valueOf(relationshipLevelSid)));
                    dataSelectionDAO.addProjectionCustHierarchy(cffCustHierarchy);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e + " in saveCustomerHierarchyLogic");
        }
    }

    public void saveCcp(final List<Leveldto> customerEndLevels, final String projectionId, final GtnSmallHashMap tempTableNames) {
        if (customerEndLevels != null && !customerEndLevels.isEmpty()) {
            List list = new ArrayList();
            list.add(projectionId);
            String query = CommonQueryUtils.getAppQuery(list, "CFFDETAILSINSERT");
            HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, tempTableNames));
            updateInclusionFlag(Integer.parseInt(projectionId));
        }
    }

    /**
     * Searh view.
     *
     * @param viewName the view name
     * @param forecastType the forecast type
     * @param viewType the view type
     * @return the list< view dto>
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public static List<ViewDTO> searhView(final String viewName, final String viewType) throws ParseException, PortalException, SystemException
             {
        LOGGER.debug("Entering searchView method");
        List list = null;
        List inputList = new ArrayList();
        final String userId = (String) VaadinSession.getCurrent().getAttribute(ConstantsUtil.USER_ID);
        if (StringUtils.isNotEmpty(viewType)
                && StringUtils.isNotBlank(viewType)) {
            inputList.add(viewType);
        } else {
            inputList.add(CommonUtils.CHAR_PERCENT);
        }
        if (StringUtils.isNotEmpty(viewName)
                && StringUtils.isNotBlank(viewName)) {
            inputList.add(viewName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT));
        } else {
            inputList.add(CommonUtils.CHAR_PERCENT);
        }
        if (viewType.equalsIgnoreCase("private")) {
            if (StringUtils.isNotEmpty(userId)
                    && StringUtils.isNotBlank(userId)) {
                inputList.add("AND FVM.created_By = " + userId);
            } else {
                inputList.add(" ");
            }
        } else {
            inputList.add(" ");
        }
        list = CommonQueryUtils.getAppData(inputList, "searchView", null);
        LOGGER.debug("End of searchView method");
        return Converters.getCustomizedViews(list);
    }

    /**
     * Delete view.
     *
     * @param viewId the view id
     * @return the string
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public static void deleteView(final int viewId) {
        LOGGER.debug("Entering deleteView method with viewId " + viewId);
        List ipnput = new ArrayList();
        ipnput.add(viewId);
        CommonQueryUtils.updateAppData(ipnput, "deleteView");
        LOGGER.debug("End of deleteView method");
    }


    public String getNoOfLevelFromJbpm(SessionDTO sessionDTO, String cffMasterSid, String userId) {
        Map<String, Object> params = new HashMap<>();
        String noOfLevel = StringUtils.EMPTY;
        WorkflowRuleDTO dto = new WorkflowRuleDTO();
        params.put("out_workflowDTO", dto);
        params.put(StringConstantsUtil.PROJECTION_ID, cffMasterSid);
        if (null == sessionDTO.getWorkflowStatus()) {
            try {
                List<String> roleList = new ArrayList<>();
                GtnWsCommonWorkflowResponse response = DSCalculationLogic.startWorkflow(sessionDTO,userId);
                if (response.isHasPermission()) {
                    Long processInstanceId = Long.valueOf(String.valueOf(response.getProcessInstanceId()));
                    try {
                        GtnWsCommonWorkflowResponse taskSummary = DSCalculationLogic.startAndCompleteTask(sessionDTO,userId);
                        processInstanceId = Long.valueOf(String.valueOf(taskSummary.getProcessInstanceId()));
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage());
                    }
                    VarianceCalculationLogic.submitWorkflow(processInstanceId, sessionDTO,"CFF");
                    noOfLevel = DSCalculationLogic.getProcessVariableLog(processInstanceId, "NoOfUsers");
                } else {
                    StringBuffer notiMsg = new StringBuffer("You dont have permission to submit a projection.");
                    if (!roleList.isEmpty()) {
                        notiMsg.append("\n Only " + roleList + " can submit a projection.");
                    }
                    NotificationUtils.getAlertNotification("Permission Denined", notiMsg.toString());

                }
            }  catch (SystemException ex) {
                java.util.logging.Logger.getLogger(CFFLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (sessionDTO.getWorkflowStatus() != null && sessionDTO.getWorkflowStatus().equals("Rejected")) {
            VarianceCalculationLogic.submitWorkflow(sessionDTO.getProcessId(), sessionDTO, "CFF");
            noOfLevel = DSCalculationLogic.getProcessVariableLog(sessionDTO.getProcessId(), "NoOfUsers");
        }

        return noOfLevel;
    }

    public String getNoOfLevelFromDB(String cffMasterSid) {
        List list = new ArrayList();
        String noOfLevel = StringUtils.EMPTY;
        list.add(cffMasterSid);
        List<Object[]> currentLevelList = CommonQueryUtils.getAppData(list, "getCFFCurrentLevel", null);
        if (currentLevelList != null) {
            Object[] obj = currentLevelList.get(0);
            if (obj[NumericConstants.SEVEN] != null) {
                noOfLevel = String.valueOf(obj[NumericConstants.SEVEN]);
            }
        }
        return noOfLevel;
    }

    public String getWorkFlowStatusId(String status) {
        String workFlowStatusId = "0";
        String query = SQlUtil.getQuery("getWorkFlowStatusId");
        query = query.replace("?", status);
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        if (list != null) {
            workFlowStatusId = String.valueOf(list.get(0));
        }
        return workFlowStatusId;

    }

    public int getFileSelectionCount(String cffMasterId, Set<Container.Filter> filters) {
        int count = 0;
        String recordCount;
        String filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(filters, getFileSelectionFilterMap()).toString();
        String query = SQlUtil.getQuery("getFileSelectionCount");
        query = query.replace("?", cffMasterId);
        filterQuery = filterQuery.replace(StringConstantsUtil.WHERE, StringConstantsUtil.SPACE_AND_SPACE);
        query += filterQuery;
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        if (list != null) {
            recordCount = String.valueOf(list.get(0));
            if (!recordCount.equals("null")) {
                count = Integer.parseInt(recordCount);
            }
        }
        return count;

    }

    public List getFileSelection(String cffMasterId, Set<Container.Filter> filters) {
        List list;
        String filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(filters, getFileSelectionFilterMap()).toString();
        String query = SQlUtil.getQuery("getFileSelection");
        query = query.replace("?", cffMasterId);
        filterQuery = filterQuery.replace(StringConstantsUtil.WHERE, StringConstantsUtil.SPACE_AND_SPACE);
        query += filterQuery;
        list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list;
    }

    public void updateActiveFromDate(int cffMasterId) {
        String query = SQlUtil.getQuery("updateActiveFromDate");
        query = query.replace("?", String.valueOf(cffMasterId));
        HelperTableLocalServiceUtil.executeUpdateQuery(query);

        String updatequery = SQlUtil.getQuery("updateActiveToDate");
        HelperTableLocalServiceUtil.executeUpdateQuery(updatequery);

    }

    private Map<String, String> getFileSelectionFilterMap() {
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("fileName", "A.FILE_NAME");
        filterMap.put("fileType", ConstantsUtil.HT_DESCRIPTION);
        filterMap.put("version", "A.VERSION");
        filterMap.put(StringConstantsUtil.ACTIVE_FROM_DATE, "A.ACTIVE_FROM");
        filterMap.put(StringConstantsUtil.ACTIVE_TO_DATE, "A.ACTIVE_TO");
        return filterMap;
    }

    private Map<String, String> getFileSelectionMap() {
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("fileName", "FORECAST_NAME");
        filterMap.put("fileType", "FILE_TYPE");
        filterMap.put("version", "VERSION");
        filterMap.put(StringConstantsUtil.ACTIVE_FROM_DATE, "FROM_PERIOD");
        filterMap.put(StringConstantsUtil.ACTIVE_TO_DATE, "TO_PERIOD");
        return filterMap;
    }

    public void updateInclusionFlag(int cffMasterId) {
        String query = SQlUtil.getQuery("inclusionFlagUpdate");
        query = query.replace("?", String.valueOf(cffMasterId));
        HelperTableLocalServiceUtil.executeUpdateQuery(query);

    }

    public List getBusinessUnits(int businessUnitId) {

        String query = SQlUtil.getQuery("get-business-units");
        if (businessUnitId == 0) {
            query = query.replace("AND CM.COMPANY_MASTER_SID = @ORGANIZATION_KEY", StringUtils.EMPTY);
        } else {
            query = query.replace("@ORGANIZATION_KEY", StringUtils.EMPTY + businessUnitId);
        }
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);

        return list;
    }

    /**
     * Method return a list of companies.
     *
     * @param companyId
     * @return
     */
    public List getCompanies(int companyId) {

        String query = SQlUtil.getQuery("get-companies");
        if (companyId == 0) {
            query = query.replace("AND CM.COMPANY_MASTER_SID = @GLCOMP", StringUtils.EMPTY);
        } else {
            query = query.replace("@GLCOMP", StringUtils.EMPTY + companyId);
        }
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);

        return list;
    }

    /**
     * To insert the selected customer and product hierarchy in CCP_HIERARCHY
     *
     * @param tempTableNames
     * @param dsDTO
     * @param customerSelection
     * @param productSelection
     * @param isDataSelectionTab
     */
    public void ccpHierarchyInsert(final GtnSmallHashMap tempTableNames, DataSelectionDTO dsDTO, final List<Leveldto> customerSelection, final List<Leveldto> productSelection, final String topLevelName, final boolean isDataSelectionTab) {
        List<Object[]> contractList = new ArrayList<>();
        List<Object[]> customerList = new ArrayList<>();
        List<Object[]> productList = new ArrayList<>();
        List<String> hierarchyNoList = new ArrayList<>();
        CommonLogic logic = new CommonLogic();
        getCustomerSelectionHierarchyNo(contractList, customerList, hierarchyNoList, customerSelection, Integer.parseInt(dsDTO.getCustomerHierarchyLevel()));

        String[] ccpHierarchyQuery = new String[NumericConstants.THREE];

        if (contractList.isEmpty()) {
            ccpHierarchyQuery[0] = getCCPValues(dsDTO.getCustRelationshipBuilderSid(), logic.formInqueryStringValue(hierarchyNoList, StringConstantsUtil.HIERARCHY_NO), "GET_CONTRACT_LEVEL");
        } else {
            ccpHierarchyQuery[0] = formQueryWithUnionAll(contractList);
        }
        if (customerList.isEmpty()) {
            ccpHierarchyQuery[1] = getCCPValues(dsDTO.getCustRelationshipBuilderSid(), logic.formInqueryStringValue(hierarchyNoList, StringConstantsUtil.HIERARCHY_NO), "GET_CUSTOMER_LEVEL");
        } else {
            ccpHierarchyQuery[1] = formQueryWithUnionAll(customerList);
        }

        hierarchyNoList.clear();

        getProductSelectionHierarchyNo(productList, hierarchyNoList, productSelection, Integer.parseInt(dsDTO.getProductHierarchyLevel()));

        if (productList.isEmpty()) {
            ccpHierarchyQuery[NumericConstants.TWO] = getCCPValues(dsDTO.getProdRelationshipBuilderSid(), logic.formInqueryStringValue(hierarchyNoList, StringConstantsUtil.HIERARCHY_NO), "GET_PRODUCT_LEVEL");
        } else {
            ccpHierarchyQuery[NumericConstants.TWO] = formQueryWithUnionAll(productList);
        }
        callCCPHierarchyInsertion(ccpHierarchyQuery, tempTableNames, topLevelName, isDataSelectionTab);
    }
   

    /**
     * Used to get the HIERARCHY_NO, RELATIONSHIP_LEVEL_VALUES,LEVEL_NO for
     * Customer and product level alone from the Customer selection. If the user
     * is not selected in customer or contract level then we will get it based
     * on the higher level.
     *
     * Query Placed in SqlResources "DataSelectionQueries.xml"
     *
     * @param contractList -->> It will have the Object array of HIERARCHY_NO,
     * RELATIONSHIP_LEVEL_VALUES,LEVEL_NO for contract
     * @param customerList -->> It will have the Object array of HIERARCHY_NO,
     * RELATIONSHIP_LEVEL_VALUES,LEVEL_NO for customer
     * @param hierarchyNoList
     */
    private void getCustomerSelectionHierarchyNo(List<Object[]> contractList, List<Object[]> customerList, List<String> hierarchyNoList, List<Leveldto> selectedCustomer, final int customerLevel) {
        for (Leveldto dto : selectedCustomer) {
            switch (dto.getLevel()) {
                case StringConstantsUtil.CONTRACT:
                    contractList.add(new Object[]{dto.getHierarchyNo(), dto.getRelationshipLevelValue(), dto.getLevelNo()});
                    break;
                case "Trading Partner":
                case "Customer":
                    customerList.add(new Object[]{dto.getHierarchyNo(), dto.getRelationshipLevelValue(), dto.getLevelNo()});
                    break;
            }
            if (customerLevel == dto.getLevelNo()) {
                hierarchyNoList.add(dto.getHierarchyNo());
            }
        }
    }
    

    /**
     * Used to form the query from selection container of customer and product
     *
     * @param contractList
     * @return the string having SELECT [HIERARCHY_NO] AS STRING AND
     * RELATIONSHIP_LEVEL_VALUES AS INT EXAMPLE: SELECT '12.1.1.2', 543 UNION
     * ALL SELECT '12.1.1.3', 234
     */
    private String formQueryWithUnionAll(List<Object[]> contractList) {
        StringBuilder queryBuilder = new StringBuilder();
        String unionAll = StringUtils.EMPTY;
        for (Object[] objects : contractList) {
            queryBuilder.append(unionAll).append(" SELECT '").append(objects[0]).append("' as HIERARCHY_NO ,").append(objects[1]).append(" as RELATIONSHIP_LEVEL_VALUES ");
            unionAll = ConstantsUtil.UNION_ALL;
        }
        return queryBuilder.toString();
    }

    /**
     * Used to get the HIERARCHY_NO, RELATIONSHIP_LEVEL_VALUES,LEVEL_NO for
     * product level alone from the Product selection. If the user elected in
     * product level then it will stored in @param productList or else we will
     * get the last level hierarchy no hierarchyNoList.
     *
     * @param productList -->> It will have the Object array of HIERARCHY_NO,
     * RELATIONSHIP_LEVEL_VALUES,LEVEL_NO for product
     * @param hierarchyNoList
     */
    private void getProductSelectionHierarchyNo(List<Object[]> productList, List<String> hierarchyNoList, List<Leveldto> selectedProduct, final int productLevel) {
        for (Leveldto dto : selectedProduct) {
            if ("NDC".equalsIgnoreCase(dto.getLevel()) || "Item".equalsIgnoreCase(dto.getLevel()) || "Product".equalsIgnoreCase(dto.getLevel())) {
                productList.add(new Object[]{dto.getHierarchyNo(), dto.getRelationshipLevelValue(), dto.getLevelNo()});
            }

            if (productLevel == dto.getLevelNo()) {
                hierarchyNoList.add(dto.getHierarchyNo());
            }
        }
            }

    /**
     * To insert the ccp_hierarchy table in edit add and view mode
     *
     * @param ccpHierarchyQuery
     * @param tempTableNames
     * @param topLevelName
     * @param isDataSelectionTab -- It will be true if its called from data
     * selection tab
     */
    
    private void callCCPHierarchyInsertion(String[] ccpHierarchyQuery, final GtnSmallHashMap tempTableNames, final String topLevelName, final boolean isDataSelectionTab) {

        StringBuilder builder = new StringBuilder();
        if (isDataSelectionTab) {
            builder.append(QueryUtil.replaceTableNames(SQlUtil.getQuery("DELETION").replace("@TABLE_NAME", "ST_CCP_HIERARCHY"), tempTableNames));
        }
        builder.append(SQlUtil.getQuery("CCP_HIERARCHY_INSERT"));
        builder.replace(builder.indexOf(StringConstantsUtil.CONTRACT1), StringConstantsUtil.CONTRACT1.length() + builder.lastIndexOf(StringConstantsUtil.CONTRACT1), ccpHierarchyQuery[0]);
        builder.replace(builder.indexOf(StringConstantsUtil.CUSTOMER), StringConstantsUtil.CUSTOMER.length() + builder.lastIndexOf(StringConstantsUtil.CUSTOMER), ccpHierarchyQuery[1]);
        builder.replace(builder.indexOf(StringConstantsUtil.PRODUCT), StringConstantsUtil.PRODUCT.length() + builder.lastIndexOf(StringConstantsUtil.PRODUCT), ccpHierarchyQuery[NumericConstants.TWO]);
        if (StringConstantsUtil.CONTRACT.equalsIgnoreCase(topLevelName)) {
            builder.replace(builder.indexOf(StringConstantsUtil.FILTER), StringConstantsUtil.FILTER.length() + builder.lastIndexOf(StringConstantsUtil.FILTER), "COM.HIERARCHY_NO LIKE C.HIERARCHY_NO");
        } else {
            builder.replace(builder.indexOf(StringConstantsUtil.FILTER), StringConstantsUtil.FILTER.length() + builder.lastIndexOf(StringConstantsUtil.FILTER), "C.HIERARCHY_NO LIKE COM.HIERARCHY_NO");
        }
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(builder.toString(), tempTableNames));
    }
    
    public void callDeductionCCPHierarchyInsertion(SessionDTO session, final GtnSmallHashMap tempTableNames, final boolean isDataSelectionTab) {

        StringBuilder builder = new StringBuilder();
        if (isDataSelectionTab) {
            builder.append(QueryUtil.replaceTableNames(SQlUtil.getQuery("DELETION").replace("@TABLE_NAME", "ST_CCP_DEDUCTION_HIERARCHY"), tempTableNames));
        }
        builder.append(SQlUtil.getQuery("DEDUCTION_HIERARCHY_INSERT"));
        builder.replace(builder.indexOf(StringConstantsUtil.CFFMASTERSID), StringConstantsUtil.CFFMASTERSID.length() + builder.lastIndexOf(StringConstantsUtil.CFFMASTERSID), String.valueOf(session.getProjectionId()));
        builder.replace(builder.indexOf(StringConstantsUtil.RELATIONBUILDERSID), StringConstantsUtil.RELATIONBUILDERSID.length() + builder.lastIndexOf(StringConstantsUtil.RELATIONBUILDERSID), session.getDedRelationshipBuilderSid());
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(builder.toString(), tempTableNames));
    }

    /**
     * Used to build the query to get the CCP values for CCP_HIERARCHY insert
     *
     * @param value
     * @param formInqueryStringValue
     * @param queryName
     * @return
     */
    private String getCCPValues(String value, String formInqueryStringValue, final String queryName) {
        StringBuilder builder = new StringBuilder(SQlUtil.getQuery(queryName));
        builder.replace(builder.indexOf(StringConstantsUtil.RELATION_SID), StringConstantsUtil.RELATION_SID.length() + builder.lastIndexOf(StringConstantsUtil.RELATION_SID), value);
        builder.replace(builder.indexOf(StringConstantsUtil.HIERARCHY_DETAILS), StringConstantsUtil.HIERARCHY_DETAILS.length() + builder.lastIndexOf(StringConstantsUtil.HIERARCHY_DETAILS), formInqueryStringValue);
        return builder.toString();
    }

    public Map<String, List> getLevelValueDetails(SessionDTO sessionDTO, Object relationshipBuilderSID, boolean isCustomerHierarchy) {
        return getRelationshipDetails(sessionDTO, relationshipBuilderSID.toString(), isCustomerHierarchy);
    }

    /**
     *
     * @param relationshipBuilderSid
     * @return
     */
    private Map<String, List> getRelationshipDetails(SessionDTO sessionDTO, String relationshipBuilderSid, boolean isCustomerHierarchy) {

        String customSql = SQlUtil.getQuery("getHierarchyTableDetails");
        customSql = customSql.replace(ConstantsUtil.RS_ID_REPLACE, relationshipBuilderSid);
        customSql = customSql.replace("?RLDV", isCustomerHierarchy ? sessionDTO.getCustomerRelationVersion()+ StringUtils.EMPTY 
                : sessionDTO.getProductRelationVersion()+ StringUtils.EMPTY);
		customSql = customSql.replace("?HLDV", isCustomerHierarchy ? sessionDTO.getCustomerHierarchyVersion()+ StringUtils.EMPTY 
                : sessionDTO.getProductHierarchyVersion()+ StringUtils.EMPTY);
        List tempList = HelperTableLocalServiceUtil.executeSelectQuery(customSql);
        Map<String, List> resultMap = new LinkedHashMap<>();
        String hierarchyNoType = isCustomerHierarchy ? "CUST_HIERARCHY_NO" : "PROD_HIERARCHY_NO";
        RelationshipLevelValuesMasterBean bean = new RelationshipLevelValuesMasterBean(tempList, relationshipBuilderSid, hierarchyNoType, sessionDTO);
        tempList.clear();
        tempList = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(bean.getFinalQuery(), sessionDTO.getCurrentTableNames()));
        for (int j = tempList.size() - 1; j >= 0; j--) {
            Object[] object = (Object[]) tempList.get(j);
            final List detailsList = new ArrayList();
            detailsList.add(object[1]); // Level Value
            detailsList.add(object[NumericConstants.TWO]); // Level No
            detailsList.add(object[NumericConstants.THREE]); // Level Name
            detailsList.add(object[NumericConstants.FOUR]); // RL Level Value - Actual System Id
            detailsList.add(isCustomerHierarchy ? "C" : "P"); // HIERARCHY INDICATOR
            commonUtils.updateRelationShipLevelList(object, detailsList, String.valueOf(object[1]));
            resultMap.put(String.valueOf(object[0]), detailsList);
            if (j == tempList.size() - 1) {
                if (isCustomerHierarchy) {
                    sessionDTO.setCustomerLastLevelNo(Integer.parseInt(object[NumericConstants.THREE].toString()));
                } else {
                    sessionDTO.setProductLastLevelNo(Integer.parseInt(object[NumericConstants.THREE].toString()));
                }
            }
        }
        return resultMap;
    }
    
    
    
    public Map<String, List> getRelationshipDetailsDeduction(SessionDTO sessionDTO, String relationshipBuilderSid) {

        String customSql = SQlUtil.getQuery("getHierarchyTableDetailsDeduction");
        customSql = customSql.replace(ConstantsUtil.RS_ID_REPLACE, relationshipBuilderSid);
        List tempList = HelperTableLocalServiceUtil.executeSelectQuery(customSql);
        Map<String, List> resultMap = new HashMap<>();
        RelationshipLevelValuesMasterBean bean = new RelationshipLevelValuesMasterBean(tempList, relationshipBuilderSid, "D", sessionDTO);
        tempList.clear();
        tempList = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(bean.getDeductionFinalQuery(), sessionDTO.getCurrentTableNames()));
        for (int j = tempList.size() - 1; j >= 0; j--) {
            Object[] object = (Object[]) tempList.get(j);
            final List detailsList = new ArrayList();
            detailsList.add(object[1]); // Level Value
            detailsList.add(object[NumericConstants.TWO]); // Level No
            detailsList.add(object[NumericConstants.THREE]); // Level Name
            detailsList.add(object[NumericConstants.FOUR]); // RL Level Value - Actual System Id
            detailsList.add("D"); // HIERARCHY INDICATOR
            commonUtils.updateRelationShipLevelList(object, detailsList, String.valueOf(object[1]));
            resultMap.put(String.valueOf(object[0]), detailsList);
        }
        return resultMap;
    }

    /**
     * To get the selected cust and prod values. Used in edit mode. For add mode
     * we will get it directly from the ui container
     *
     * @param projectionId
     * @param queryName
     * @return
     */
    public List<Leveldto> getCustandProdSelection(final int projectionId, final String queryName) {
        String sql = SQlUtil.getQuery(queryName).replace("@PROJECTION_MASTER_SID", String.valueOf(projectionId));
       
        List results = HelperTableLocalServiceUtil.executeSelectQuery(sql);
        List<Leveldto> resultList = new ArrayList<>();
        Leveldto dto;
        for (int j = 0; j < results.size(); j++) {
            Object[] object = (Object[]) results.get(j);
            dto = new Leveldto();
            dto.setHierarchyNo(object[0].toString());
            dto.setRelationshipLevelValue(object[1].toString());
            dto.setLevelNo(Integer.valueOf(object[NumericConstants.TWO].toString()));
            dto.setLevel(object[NumericConstants.THREE].toString());
            resultList.add(dto);
        }
        return resultList;
    }

    /**
     * Used to check which level is top in selected customer hierarchy either
     * customer or contract It is used for CCP_HIERARCHY_INSERT query formation
     *
     * @param hierarchySid
     * @return
     */
    public String getTopLevelInHierarchy(final String hierarchySid) {
        List<String> list = (List<String>) HelperTableLocalServiceUtil.executeSelectQuery(SQlUtil.getQuery("GET_TOP_LEVEL_NAME").replace("@HIERARCHY_SID", hierarchySid));
        for (String levelName : list) {
            if ("Customer".equals(levelName) || "Trading Partner".equals(levelName) || "TradingPartner".equals(levelName) || StringConstantsUtil.CONTRACT.equals(levelName)) {
                return levelName;
            }
        }
        return StringUtils.EMPTY;
    }
}

