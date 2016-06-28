package com.stpl.app.cff.logic;

import com.stpl.app.bpm.dto.WorkflowRuleDTO;
import com.stpl.app.cff.abstractCff.AbstractFilterLogic;
import com.stpl.app.cff.bpm.logic.VarianceCalculationLogic;
import com.stpl.app.cff.bpm.service.BPMProcessBean;
import com.stpl.app.cff.dao.AdditionalInfoDAO;
import com.stpl.app.cff.dao.CFFDAO;
import com.stpl.app.cff.dao.DataSelectionDAO;
import com.stpl.app.cff.dao.impl.AdditionalInfoDAOImpl;
import com.stpl.app.cff.dao.impl.CFFDAOImpl;
import com.stpl.app.cff.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.cff.dto.ApprovalDetailsDTO;
import com.stpl.app.cff.dto.CFFDTO;
import com.stpl.app.cff.dto.CFFResultsDTO;
import com.stpl.app.cff.dto.CFFSearchDTO;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.queryUtils.CFFQueryUtils;
import com.stpl.app.cff.queryUtils.CommonQueryUtils;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.ui.fileSelection.dto.FileSelectionDTO;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.Converters;
import com.stpl.app.cff.util.HelperListUtil;
import com.stpl.app.cff.util.UiUtils;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.app.model.HelperTable;
import com.stpl.app.parttwo.model.CffApprovalDetails;
import com.stpl.app.parttwo.model.CffCustHierarchy;
import com.stpl.app.parttwo.model.CffDetails;
import com.stpl.app.parttwo.model.CffMaster;
import com.stpl.app.parttwo.model.CffProdHierarchy;
import com.stpl.app.parttwo.model.impl.CffApprovalDetailsImpl;
import com.stpl.app.parttwo.model.impl.CffMasterImpl;
import com.stpl.app.parttwo.service.CffCustHierarchyLocalServiceUtil;
import com.stpl.app.parttwo.service.CffDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.CffProdHierarchyLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.util.Constants;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.forecastds.dto.ViewDTO;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.data.Container;
import com.vaadin.server.VaadinSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Manasa
 */
public class CFFLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LogManager.getLogger(CFFLogic.class);
    public CFFQueryUtils cffQueryUtils = new CFFQueryUtils();
    /**
     * The common utils.
     */
    public CommonUtils commonUtils = new CommonUtils();
    private static final CFFDAO DAO = CFFDAOImpl.getInstance();
    private static final AdditionalInfoDAO ADD_DAO = new AdditionalInfoDAOImpl();
    public static Map<String, String> userMap = new HashMap<String, String>();
    private static HelperListUtil helperListUtil = HelperListUtil.getInstance();
    FileSelectionDTO dto = new FileSelectionDTO();
    DataSelectionDAO dataSelectionDAO = new DataSelectionDAOImpl();

    /**
     * Gets the cff details for add.
     *
     * @return the cff details for add
     */
    public List<CFFResultsDTO> getCffDetailsForAdd() {
        List<CFFResultsDTO> cffResultsDTOs = new ArrayList<CFFResultsDTO>();
        final CFFDTO cffDTO = new CFFDTO();
        List resultsList;
        try {
            resultsList = cffQueryUtils.getCCPCombinationForDisplay(cffDTO);
            cffResultsDTOs = commonUtils.getCustomizedCffResults(resultsList);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return cffResultsDTOs;
    }

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
            return commonUtils.getCustomisedCFF(resultsList, sessionDTO);
        } catch (Exception ex) {
            LOGGER.error(ex);
            return new ArrayList<CFFResultsDTO>();
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
            LOGGER.error(ex);
            return new ArrayList<CFFResultsDTO>();
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
    public static List<HelperDTO> getDropDownList(final String listName) throws SystemException, PortalException, Exception {
        LOGGER.info("Entering getDropDownList p1:" + listName);
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final List<HelperTable> list = DAO.getHelperTableDetailsByListName(listName);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));

            }
        }
        LOGGER.info("return DropDownList :" + helperList.size());
        return helperList;
    }

    /**
     * Gets the drop down list.
     *
     * @param listName the list name
     * @return the drop down list
     */
    public static List<HelperDTO> loadStatusDdlb(final String listName) throws PortalException, SystemException {

        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();

        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(HelperTable.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtil.LIST_NAME, listName));
        List<String> statusList = new ArrayList<String>();
            statusList.add("Withdrawn");
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.in(ConstantsUtil.DESCRIPTION, statusList)));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtil.DESCRIPTION));
        final List<HelperTable> list = DAO.getHelperTableList(cfpDynamicQuery);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));
            }
        }

        LOGGER.info("returns helperList size" + helperList.size());

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
        LOGGER.info("inside save cff method");
        String result = "";
        String cffName = "";
        String cffType = "";
        String cffMasterSid = String.valueOf(VaadinSession.getCurrent().getAttribute("projectionId"));
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

//        if (ConstantsUtils.YES.equals(String.valueOf(valueMap.get("latestEstimate"))) && (ConstantsUtils.OPTION_NO.equals(String.valueOf(valueMap.get("updateCycle")))
//                && ConstantsUtils.SELECT_ONE.equals(String.valueOf(valueMap.get("updateCycle"))))) {
//            cffName = valueMap.get("latestEstimateName").toString();
//            cffType = String.valueOf(CommonUtils.getHelperTableSId(CommonUtils.LATEST_ESTIMATE, CommonUtils.CFF_TYPE));
//        } else if ((ConstantsUtils.OPTION_NO.equals(String.valueOf(valueMap.get("latestEstimate"))) && ConstantsUtils.SELECT_ONE.equals(String.valueOf(valueMap.get("latestEstimate"))))
//                && ConstantsUtils.YES.equals(String.valueOf(valueMap.get("updateCycle")))) {
//            
//            cffName = valueMap.get("updateCycleName").toString();
//            cffType = String.valueOf(CommonUtils.getHelperTableSId(CommonUtils.UPDATE_CYCLE, CommonUtils.CFF_TYPE));
//        } else if (ConstantsUtils.YES.equals(String.valueOf(valueMap.get("latestEstimate"))) && ConstantsUtils.YES.equals(String.valueOf(valueMap.get("updateCycle")))) {
//            cffName = valueMap.get("latestEstimateName").toString() + "-" + valueMap.get("updateCycleName").toString();
//            cffType = String.valueOf(CommonUtils.getHelperTableSId(CommonUtils.BOTH, CommonUtils.CFF_TYPE));
//        }
        String query = "UPDATE dbo.CFF_MASTER\n"
                + "SET CFF_NAME='" + cffName + "',CFF_TYPE='" + cffType + "'\n"
                + "WHERE CFF_MASTER_SID=" + cffMasterSid;

        HelperTableLocalServiceUtil.executeUpdateQuery(query);

        VaadinSession.getCurrent().setAttribute(CommonUtils.CFF_MASTER_SYSTEM_ID_SESSION, cffMasterSystemId);
        int masterId = Integer.valueOf(cffMasterSid);
        // result = saveCffDetails(masterId);        
        String noOfLevel = getNoOfLevelFromJbpm(sessionDTO, cffMasterSid, userId);
        // String workflowId =  submitCffPendingDetails(userId, Integer.valueOf(cffMasterSid));
        result = submitCffPendingDetails(userId, Integer.valueOf(cffMasterSid), noOfLevel, Boolean.FALSE);
        if (result.equals(CommonUtils.FAIL) || noOfLevel.isEmpty()) {
            return CommonUtils.FAIL;
        }
        LOGGER.info("Exits save cff method");
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
            LOGGER.info("inside update cff method");

            CffMaster cffMaster = new CffMasterImpl();
            cffMaster = DAO.getCffMaster(cffMasterSystemId);
            cffMaster.setModifiedBy(Integer.valueOf(userId));
            cffMaster.setModifiedDate(new Date());
            cffMaster.setInboundStatus(ConstantsUtil.INBOUND_STATUS_UPDATE);
            cffMaster = DAO.updateCffMaster(cffMaster);

        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (PortalException ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("Exits update cff method");
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
            LOGGER.error(ex);
        }
        LOGGER.info("Exits update cff method");
        return result;
    }

    /**
     * Gets latest approved CCP Projection
     *
     * @return Projection List
     */
    public CFFResultsDTO getApprovedCFF() {
        List resultsList;
        try {
            resultsList = cffQueryUtils.getApprovedCFF();
            return commonUtils.getCustomisedLatestApprovedCff(resultsList);
        } catch (Exception ex) {
            LOGGER.error(ex);
            return new CFFResultsDTO();
        }
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
            LOGGER.error(ex);
            return "Fail";
        }

        return "Success";
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
            LOGGER.error(ex);
            return "Fail";
        }

        return "Success";
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
            LOGGER.error(ex);
            return null;
        }
        return "Success";
    }

    /**
     * Save cff details.
     *
     * @param cffId the cff id
     * @return the string
     */
    public String saveCffDetails(int cffId, String ccps) {
        List resultsList;
        final CFFDTO cffDTO = new CFFDTO();
        resultsList = cffQueryUtils.getCCPCombinationForSave(cffDTO, ccps);
        final String result = saveCffDetailsFromResult(resultsList, cffId);
         updateInclusionFlag(cffId);
        return result;
    }

    /**
     * Save cff details from result.
     *
     * @param resultsList the results list
     * @param cffId the cff id
     * @return the string
     */
    public String saveCffDetailsFromResult(List<Object[]> resultsList, int cffId) {
        CffDetails cffDetails = CffDetailsLocalServiceUtil.createCffDetails(0);

        String sid;
        try {
            for (final Object[] obj : resultsList) {
                cffDetails.setCffMasterSid(cffId);
                cffDetails.setCcpDetailsSid(Integer.valueOf(String.valueOf(obj[0])));
                sid = String.valueOf(obj[1]);
                sid = sid == null || "".equals(sid) ? "0" : sid;
                cffDetails.setProjectionMasterSid(Integer.parseInt(sid));
                cffDetails.setInboundStatus(ConstantsUtil.INBOUND_STATUS_ADD);
                DAO.addCffDetails(cffDetails);
            }
            return CommonUtils.SUCCESS;
        } catch (SystemException ex) {
            LOGGER.error(ex);
            return CommonUtils.FAIL;
        } catch (NumberFormatException ex) {
            LOGGER.error(ex);
            return CommonUtils.FAIL;
        }

    }

    /**
     * Save cff approve details.
     *
     * @param userId the user id
     * @param cffId the cff id
     * @return the string
     */
    public String submitCffApproveDetails(String userId, int cffId) {
        final DynamicQuery cffDetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(CffApprovalDetails.class);
        final CffApprovalDetails cffApprovalDetails = new CffApprovalDetailsImpl();
        CffApprovalDetails cffApprovalDetailsOld;
        List<CffApprovalDetails> resultsList = new ArrayList<CffApprovalDetails>();
        int approvalSequence;
        try {
            cffApprovalDetails.setApprovedBy(Integer.valueOf(userId));
            cffApprovalDetails.setApprovedDate(new Date());
            cffApprovalDetails.setCffMasterSid(cffId);
            cffApprovalDetails.setInboundStatus(ConstantsUtil.INBOUND_STATUS_ADD);
            cffDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq("cffMasterSid", cffId));
            cffDetailsDynamicQuery.add(RestrictionsFactoryUtil.ne("inboundStatus", "D"));
            cffDetailsDynamicQuery.addOrder(OrderFactoryUtil.desc("approvalSequence"));
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
            LOGGER.error(ex);
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
    public String submitCffPendingDetails(String userId, int cffId, String noOfLevel, boolean isRejected) {
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
            LOGGER.error(ex);
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
    public String approveCffApproveDetails(String userId, int cffId, String status) {
        List input = new ArrayList();
        try {
            List list = new ArrayList();
            int noOfLevel = 0;
            int currentLevel = 0;
            list.add(cffId);
            List<Object[]> listSeq = CommonQueryUtils.getAppData(list, "getCFFLevel", null);
            if (listSeq != null) {
                Object[] obj = (Object[]) listSeq.get(0);
                if (obj[7] != null) {
                    String level = String.valueOf(obj[7]);
                    noOfLevel = Integer.valueOf(level);
                }
            }
            List<Object[]> currentLevelList = CommonQueryUtils.getAppData(list, "getCFFCurrentLevel", null);
            if (currentLevelList != null) {
                Object[] obj = (Object[]) currentLevelList.get(0);
                if (obj[4] != null) {
                    String level = String.valueOf(obj[4]);
                    currentLevel = Integer.valueOf(level);
                }
            }
            input.add(Integer.valueOf(userId));
            input.add(cffId);
            input.add(status.equals(CommonUtils.WORKFLOW_STATUS_APPROVED) ? String.valueOf((currentLevel + 1)) : String.valueOf(currentLevel));
            if (CommonUtils.WORKFLOW_STATUS_APPROVED.equals(status)) {
                if (noOfLevel == currentLevel) {
                    input.add("GETDATE()");
                    input.add(CommonUtils.WORKFLOW_STATUS_APPROVED);
                    updateActiveFromDate(cffId);
                } else {
                    input.add(null);
                    input.add(CommonUtils.WORKFLOW_STATUS_PENDING);
                }
            } else {
                input.add(null);
                input.add(status);
            }
            input.add(cffId);
            CommonQueryUtils.updateAppData(input, "updateapprovaldetails");

        } catch (Exception ex) {
            LOGGER.error(ex);
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
    public String rejectCffApproveDetails(String userId, int cffId) {
        final DynamicQuery cffDetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(CffApprovalDetails.class);
        final CffApprovalDetails cffApprovalDetails = new CffApprovalDetailsImpl();
        CffApprovalDetails cffApprovalDetailsOld;
        List<CffApprovalDetails> resultsList = new ArrayList<CffApprovalDetails>();
        int approvalSequence;
        try {
            cffApprovalDetails.setApprovedBy(Integer.valueOf(userId));
            cffApprovalDetails.setApprovedDate(new Date());
            cffApprovalDetails.setCffMasterSid(cffId);
            cffApprovalDetails.setInboundStatus(ConstantsUtil.INBOUND_STATUS_ADD);
            cffDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq("cffMasterSid", cffId));
            cffDetailsDynamicQuery.addOrder(OrderFactoryUtil.desc("approvalSequence"));
            resultsList = CffDetailsLocalServiceUtil.dynamicQuery(cffDetailsDynamicQuery);

            cffApprovalDetailsOld = resultsList.get(0);
            approvalSequence = cffApprovalDetailsOld.getApprovalSequence() + 1;
            cffApprovalDetails.setApprovalSequence(approvalSequence);
            cffApprovalDetails.setApprovalStatus(CommonUtils.getCodeFromHelperTable(CommonUtils.WORKFLOW_STATUS_REJECTED, CommonUtils.WORKFLOW_STATUS));

            DAO.addCffApprovalDetails(cffApprovalDetails);
        } catch (SystemException ex) {
            LOGGER.error(ex);
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
        final DynamicQuery cffDetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(CffApprovalDetails.class);
        final CffApprovalDetails cffApprovalDetails = new CffApprovalDetailsImpl();
        CffApprovalDetails cffApprovalDetailsOld;
        List<CffApprovalDetails> resultsList = new ArrayList<CffApprovalDetails>();
        int approvalSequence;
        try {
            cffApprovalDetails.setApprovedBy(Integer.valueOf(userId));
            cffApprovalDetails.setApprovedDate(new Date());
            cffApprovalDetails.setCffMasterSid(cffId);
            cffApprovalDetails.setInboundStatus(ConstantsUtil.INBOUND_STATUS_ADD);
            cffDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq("cffMasterSid", cffId));
            cffDetailsDynamicQuery.addOrder(OrderFactoryUtil.desc("approvalSequence"));
            resultsList = CffDetailsLocalServiceUtil.dynamicQuery(cffDetailsDynamicQuery);

            cffApprovalDetailsOld = resultsList.get(0);
            approvalSequence = cffApprovalDetailsOld.getApprovalSequence() + 1;
            cffApprovalDetails.setApprovalSequence(approvalSequence);
            cffApprovalDetails.setApprovalStatus(CommonUtils.getCodeFromHelperTable(CommonUtils.WORKFLOW_STATUS_CANCELLED, CommonUtils.WORKFLOW_STATUS));

            DAO.addCffApprovalDetails(cffApprovalDetails);
        } catch (SystemException ex) {
            LOGGER.error(ex);
            return CommonUtils.FAIL;
        }
        return CommonUtils.SUCCESS;
    }

    public int getSearchCount(final CFFSearchDTO binderDto) throws Exception {
        int count = 0;
        List parameters;
        List resultCountList;
        String filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(binderDto.getFilters(), getFilterMap(false)).toString();
        parameters = getParametersForCffSearch(binderDto);
        filterQuery = filterQuery.replace("where", "AND");
        parameters.add(filterQuery);
        resultCountList = CommonQueryUtils.getAppData(parameters, "getCffSearchCount", null);
        count = getCount(resultCountList);
        LOGGER.info("Count For method" + count);
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
        try {
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
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return input;
    }

    /**
     * Gets the search results.
     *
     * @param columns columns
     * @return the search results
     */
    public List<CFFSearchDTO> getSearchResults(final CFFSearchDTO binderDto) throws Exception {
        LOGGER.info("Inside Search Results");
        Boolean excelFlag = true;
        String filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(binderDto.getFilters(), getFilterMap(false)).toString();
        String orderBy = AbstractFilterLogic.getInstance().orderByQueryGenerator(binderDto.getOrderByColumns(), getFilterMap(true)).toString();
        List<CFFSearchDTO> cffMasterList = new ArrayList<CFFSearchDTO>();
        List parameterslist;
        parameterslist = getParametersForCffSearch(binderDto);
        List<Object[]> resultList;
        if (filterQuery != null) {
            filterQuery = filterQuery.replace("where", "AND");
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
        cffMasterList = commonUtils.getCustomizedSearchResults(resultList, excelFlag);
        return cffMasterList;
    }

    /**
     * Get Approval List for edit
     *
     * @param cffSid
     * @return List of Customised Approval list data for a cff
     */
    public List<ApprovalDetailsDTO> getApprovalDetailsForCff(int cffSid) {
        List<ApprovalDetailsDTO> approvalList = new ArrayList<ApprovalDetailsDTO>();
        try {

            List<CffApprovalDetails> approvaldetails = DAO.getApprovalDetails(cffSid);

            approvalList = commonUtils.getCustomisedApprovalDetails(approvaldetails);

        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(CFFLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return approvalList;
    }

    public static String filterUser(String filter) {
        List<String> keys = new ArrayList<String>();
        String userIds = StringUtils.EMPTY;
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

    public static void mapUsers() throws PortalException, SystemException {
        userMap.clear();
        userMap = getAllUsers();
    }

    public static Map<String, String> getAllUsers() throws PortalException, SystemException {
        List<Object> userList = new ArrayList<Object>();
        Map<String, String> userMap = new HashMap<String, String>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(User.class);
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.USER_ID));
        productProjectionList.add(ProjectionFactoryUtil.property("firstName"));
        productProjectionList.add(ProjectionFactoryUtil.property("lastName"));
        query.setProjection(productProjectionList);
        try {
            userList = UserLocalServiceUtil.dynamicQuery(query);
            for (int loop = 0, limit = userList.size(); loop < limit; loop++) {
                Object array[] = (Object[]) userList.get(loop);
                userMap.put(String.valueOf(array[0]), (String.valueOf(array[2]) + ", " + String.valueOf(array[1])));
            }
        } catch (Exception ex) {
            userList = null;
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
        filterMap.put("statusDesc", "approval_status");
        filterMap.put("finalApprovalDate", "APPROVED_DATE");
        if (isOrder) {
            filterMap.put("approvedBy", "APPROVED_BY");
        } else {
            filterMap.put("approvedBy", " approval_status=(SELECT HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = 'WorkFlowStatus' AND DESCRIPTION = 'Approved') AND APPROVED_BY");
        }
        filterMap.put("activeFromDate", "ACTIVE_FROM_DATE");
        filterMap.put("activeToDate", "ACTIVE_TO_DATE");
        return filterMap;
    }

    public Object getFileName(boolean count, SessionDTO sessionDTO, Set<Container.Filter> filters) {
        LOGGER.info("Inside getFileName");
        List<Object[]> resultList = new ArrayList<Object[]>();
        List<FileSelectionDTO> retList = new ArrayList<FileSelectionDTO>();
        List list = new ArrayList();
        try {
            if (!count) {
                String mode = sessionDTO.getAction();
                if (mode.equals("edit") || mode.equals("view")) {
                    String projId = String.valueOf(VaadinSession.getCurrent().getAttribute("projectionId"));
                    resultList = getFileSelection(projId, filters);
                    if (resultList != null) {
                        for (int i = 0; i < resultList.size(); i++) {
                            final FileSelectionDTO dto = new FileSelectionDTO();
                            final Object[] obj = (Object[]) resultList.get(i);
                            dto.setFileManagementSid(String.valueOf(obj[2]));
                            dto.setFileName(String.valueOf(obj[3]));
                            dto.setVersion(String.valueOf(obj[4]));
                            dto.setFileType(String.valueOf(obj[7]));
                            if (obj[5] != null) {
                                dto.setActiveFromDate((Date) obj[5]);
                            } else {
                                dto.setActiveFromDate(null);
                            }
                            if (obj[6] != null) {
                                dto.setActiveToDate((Date) obj[6]);
                            } else {
                                dto.setActiveToDate(null);
                            }
                            retList.add(dto);
                        }
                        return retList;
                    }
                } else {
                    if (filters != null) {
                        String filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(filters, getFileSelectionMap()).toString();
                        filterQuery = filterQuery.replace("where", " AND ");
                        list.add(filterQuery);
                    } else {
                        list.add(StringUtils.EMPTY);
                    }
                    resultList = CommonQueryUtils.getAppData(list, "fileselectiondistinct", null);
                }
                if (resultList != null) {
                    for (int i = 0; i < resultList.size(); i++) {
                        final FileSelectionDTO dto = new FileSelectionDTO();
                        final Object[] obj = (Object[]) resultList.get(i);
                        dto.setFileManagementSid(String.valueOf(obj[0]));
                        dto.setFileName(String.valueOf(obj[1]));
                        dto.setVersion(String.valueOf(obj[3]));
                        dto.setFileType(String.valueOf(obj[2]));
                        dto.setActiveFromDate((Date) obj[4]);
                        dto.setActiveToDate((Date) obj[5]);
                        dto.setFileTypeId(String.valueOf(obj[6]));
                        dto.setFileChanged(false);
                        retList.add(dto);
                    }
                }
                return retList;
            } else {
                String mode = sessionDTO.getAction();
                if (mode.equals("edit") || mode.equals("view")) {
                    String projId = String.valueOf(VaadinSession.getCurrent().getAttribute("projectionId"));
                    int fileCount = getFileSelectionCount(projId, filters);
                    return fileCount;
                } else {
                    if (filters != null) {
                        String filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(filters, getFileSelectionMap()).toString();
                        filterQuery = filterQuery.replace("where", " AND ");
                        list.add(filterQuery);
                    } else {
                        list.add(StringUtils.EMPTY);
                    }
                    List<Object> countList = CommonQueryUtils.getAppData(list, "fileselectiondistinctcount", null);
                    return countList.get(0);
                }
            }

        } catch (Exception e) {
            LOGGER.error(e);
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
    public int saveCFFMaster(final DataSelectionDTO dataSelectionDTO, String screenName, boolean isUpdate, int projectionIdValue) throws SystemException, Exception {
        int projectionId = 0;
        SimpleDateFormat DBDate = new SimpleDateFormat("yyyy-MM-dd");
        String userId = (String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID);
        String query = "INSERT INTO CFF_MASTER (CFF_TYPE, CFF_NAME, ACTIVE_FROM_DATE, ACTIVE_TO_DATE, CFF_OFFICIAL, CUSTOMER_HIERARCHY_SID, CUSTOMER_HIERARCHY_LEVEL,\n"
                + "    CUSTOMER_HIER_VERSION_NO, COMPANY_GROUP_SID, CUSTOMER_HIERARCHY_INNER_LEVEL, CUST_RELATIONSHIP_BUILDER_SID, COMPANY_MASTER_SID, PRODUCT_HIERARCHY_SID,\n"
                + "    PRODUCT_HIERARCHY_LEVEL, PRODUCT_HIER_VERSION_NO, ITEM_GROUP_SID, PRODUCT_HIERARCHY_INNER_LEVEL, PROD_RELATIONSHIP_BUILDER_SID, INBOUND_STATUS, CREATED_BY,\n"
                + "    CREATED_DATE, MODIFIED_BY, MODIFIED_DATE) VALUES ('@CFF_TYPE','@CFF_NAME',@ACTIVE_FROM_DATE, @ACTIVE_TO_DATE, 0,\n"
                + "    '@CUSTOMER_HIERARCHY_SID', '@CUSTOMER_HIERARCHY_LEVEL', '@CUSTOMER_HIER_VERSION_NO', @COMPANY_GROUP_SID,\n"
                + "    '@CUSTOMER_HIERARCHY_INNER_LEVEL', '@CUST_RELATIONSHIP_BUILDER_SID', '@COMPANY_MASTER_SID', '@PRODUCT_HIERARCHY_SID',\n"
                + "    '@PRODUCT_HIERARCHY_LEVEL', '@PRODUCT_HIER_VERSION_NO', @ITEM_GROUP_SID, '@PRODUCT_HIERARCHY_INNER_LEVEL',\n"
                + "    '@PROD_RELATIONSHIP_BUILDER_SID', 'A', '@CREATED_BY', '@CREATED_DATE', '@MODIFIED_BY', '@MODIFIED_DATE') ";

        if (isUpdate) {
            List l = new ArrayList();
            l.add(projectionIdValue);
            query = CommonQueryUtils.getAppQuery(l, "updateProjection");
        }
        query = query.replace("@CFF_TYPE", "0");
        query = query.replace("@CFF_NAME", "");
        query = query.replace("@ACTIVE_FROM_DATE", "null");
//        query = query.replace("@ACTIVE_FR_DATE", DBDate.format(dataSelectionDTO.getFromDate()!=null ? dataSelectionDTO.getF));
        query = query.replace("@ACTIVE_TO_DATE", "null");

        //  query=query.replace("@CFF_OFFICIAL", "NULL");
        query = query.replace("@CUSTOMER_HIERARCHY_SID", dataSelectionDTO.getCustomerHierSid().equals("0") ? "0" : String.valueOf(dataSelectionDTO.getCustomerHierSid()));
        query = query.replace("@CUSTOMER_HIERARCHY_LEVEL", dataSelectionDTO.getCustomerHierarchyLevel());
        query = query.replace("@CUSTOMER_HIER_VERSION_NO", dataSelectionDTO.getCustomerHierarchyVer());

        query = query.replace("@COMPANY_GROUP_SID", dataSelectionDTO.getCustomerGrpSid().equals("0") ? "null" : "'" + String.valueOf(dataSelectionDTO.getCustomerGrpSid()) + "'");
        query = query.replace("@CUSTOMER_HIERARCHY_INNER_LEVEL", dataSelectionDTO.getCustomerHierarchyInnerLevel());
        query = query.replace("@CUST_RELATIONSHIP_BUILDER_SID", dataSelectionDTO.getCustRelationshipBuilderSid().equals("0") ? "0" : String.valueOf(dataSelectionDTO.getCustRelationshipBuilderSid()));
        query = query.replace("@COMPANY_MASTER_SID", dataSelectionDTO.getCompanySid() == null || "0".equals(dataSelectionDTO.getCompanySid()) ? "null" : String.valueOf(dataSelectionDTO.getCompanySid()));

        query = query.replace("@PRODUCT_HIERARCHY_SID", dataSelectionDTO.getProdHierSid().equals("0") ? "0" : String.valueOf(dataSelectionDTO.getProdHierSid()));
        query = query.replace("@PRODUCT_HIERARCHY_LEVEL", dataSelectionDTO.getProductHierarchyLevel());
        query = query.replace("@PRODUCT_HIER_VERSION_NO", dataSelectionDTO.getProductHierarchyVer());
        query = query.replace("@ITEM_GROUP_SID", dataSelectionDTO.getProdGrpSid().equals("0") ? "null" : "'" + String.valueOf(dataSelectionDTO.getProdGrpSid()) + "'");

        query = query.replace("@PRODUCT_HIERARCHY_INNER_LEVEL", dataSelectionDTO.getProductHierarchyInnerLevel());
        query = query.replace("@PROD_RELATIONSHIP_BUILDER_SID", dataSelectionDTO.getProdRelationshipBuilderSid().equals("0") ? "0" : String.valueOf(dataSelectionDTO.getProdRelationshipBuilderSid()));
        query = query.replace("@CREATED_BY", userId);
        query = query.replace("@MODIFIED_BY", userId);

        query = query.replace("@CREATED_DATE", DBDate.format(new Date()));
        query = query.replace("@MODIFIED_DATE", DBDate.format(new Date()));
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        if (isUpdate) {
            return projectionIdValue;
        } else {
            String cffQuery = "select IDENT_CURRENT( 'cff_master' )";
            List list = HelperTableLocalServiceUtil.executeSelectQuery(cffQuery);
            if (list != null && list.size() > 0) {
                Object obj = list.get(0);
                String ccfMasterId = String.valueOf(obj);
                projectionId = Integer.valueOf(ccfMasterId);
            }
            return projectionId;
        }

    }

    /**
     * Save Product hierarchy logic.
     *
     * @param levelList the level list
     * @throws java.lang.Exception
     */
    public void saveProductHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids, final int projectionId, final List<String> addLevels, final String indicator) throws Exception {
        LOGGER.info("saveProductHierarchyLogic endLevelSids size:" + endLevelSids.size() + " projectionId " + projectionId);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("indicator", "getChildLevelRLSid");
        parameters.put("rlSids", endLevelSids);
        parameters.put("projectionId", projectionId);
        parameters.put("tableName", "CFF_PROD_HIERARCHY");
        parameters.put("module", "cff");
        List<Object> endLevels = null;
        if (endLevelSids != null && !endLevelSids.isEmpty()) {
            endLevels = dataSelectionDAO.executeQuery(parameters);
        }
        CffProdHierarchy cffProdHierarchy = CffProdHierarchyLocalServiceUtil.createCffProdHierarchy(0);
        try {
            if ("update".equals(indicator)) {
                for (String rsId : addLevels) {
                    cffProdHierarchy.setCffMasterSid(projectionId);
                    cffProdHierarchy.setRelationshipLevelSid(UiUtils.parseStringToInteger(String.valueOf(rsId)));
                    dataSelectionDAO.addProjectionProdHierarchy(cffProdHierarchy);
                }
            } else if ("save".equals(indicator)) {
                for (Leveldto dto : levelList) {

                    cffProdHierarchy.setCffMasterSid(projectionId);
                    cffProdHierarchy.setRelationshipLevelSid(dto.getRelationshipLevelSid());
                    dataSelectionDAO.addProjectionProdHierarchy(cffProdHierarchy);
                }
            }
            if (endLevels != null && !endLevels.isEmpty()) {
                for (Object relationshipLevelSid : endLevels) {
                    cffProdHierarchy.setCffMasterSid(projectionId);
                    cffProdHierarchy.setRelationshipLevelSid(UiUtils.parseStringToInteger(String.valueOf(relationshipLevelSid)));
                    dataSelectionDAO.addProjectionProdHierarchy(cffProdHierarchy);
                }
            }
        } catch (Exception e) {
            LOGGER.info(e + " saveProductHierarchyLogic");
        }
    }

    /**
     * Save customer hierarchy logic.
     *
     * @param levelList the level list
     * @param projectionId
     * @throws java.lang.Exception
     */
    public void saveCustomerHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids, final int projectionId, final List<String> addLevels, final String indicator) throws Exception {
        LOGGER.info("saveCustomerHierarchyLogic endLevelSids size:" + endLevelSids.size() + " projectionId " + projectionId);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("indicator", "getChildLevelRLSid");
        parameters.put("projectionId", projectionId);
        parameters.put("rlSids", endLevelSids);
        parameters.put("tableName", "CFF_CUST_HIERARCHY");
        parameters.put("module", "cff");
        List<Object> endLevels = null;
        if (endLevelSids != null && !endLevelSids.isEmpty()) {
            endLevels = dataSelectionDAO.executeQuery(parameters);
        }

        CffCustHierarchy cffCustHierarchy = CffCustHierarchyLocalServiceUtil.createCffCustHierarchy(0);
        try {
            if ("update".equals(indicator)) {
                for (String rsId : addLevels) {
                    cffCustHierarchy.setCffMasterSid(projectionId);
                    cffCustHierarchy.setRelationshipLevelSid(UiUtils.parseStringToInteger(String.valueOf(rsId)));
                    dataSelectionDAO.addProjectionCustHierarchy(cffCustHierarchy);
                }
            } else if ("save".equals(indicator)) {
                for (Leveldto dto : levelList) {
                    cffCustHierarchy.setCffMasterSid(projectionId);
                    cffCustHierarchy.setRelationshipLevelSid(dto.getRelationshipLevelSid());
                    dataSelectionDAO.addProjectionCustHierarchy(cffCustHierarchy);
                }
            }
            if (endLevels != null && !endLevels.isEmpty()) {
                for (Object relationshipLevelSid : endLevels) {
                    cffCustHierarchy.setCffMasterSid(projectionId);
                    cffCustHierarchy.setRelationshipLevelSid(UiUtils.parseStringToInteger(String.valueOf(relationshipLevelSid)));
                    dataSelectionDAO.addProjectionCustHierarchy(cffCustHierarchy);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e + " in saveCustomerHierarchyLogic");
        }
    }

    public void insertToCcpMap(List<String> relationshipBuilderSids, String screenName) throws Exception {
        List<String> relationshipBuilderSidsList = null;
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (relationshipBuilderSids != null && !relationshipBuilderSids.isEmpty()) {
            relationshipBuilderSidsList = new ArrayList<String>(relationshipBuilderSids);
        }
        parameters.put("indicator", "insertToCcpMap");
        parameters.put("relationshipBuilderSids", relationshipBuilderSidsList);
        parameters.put("scrennName", screenName);
        dataSelectionDAO.saveCcp(parameters);

    }

    public void saveCcp(final List<Leveldto> customerEndLevels, final String productHierarchyEndLevelsHierNos, final String indicator, final String projectionId) throws Exception {
        if (customerEndLevels != null && !customerEndLevels.isEmpty()) {
            Map<String, Object> parameters;
            List list = new ArrayList();
            List ccpList = new ArrayList();
            for (Leveldto dto : customerEndLevels) {
                list = new ArrayList();
                list.add(dto.getHierarchyNo() + "%");
                list.add(projectionId);
//                parameters = new HashMap<String, Object>();
//                parameters.put("projectionId", projectionId);
//                parameters.put("indicator", indicator);
//                parameters.put("relationshipLevelSid", dto.getRelationshipLevelSid());
//                parameters.put("hierarchyNo", dto.getHierarchyNo());
//                parameters.put("productHierarchyEndLevelsHierNos", productHierarchyEndLevelsHierNos);
//                parameters.put("indicator", "saveCcp");
                String query = CommonQueryUtils.getAppQuery(list, "getCCPFromGenerate");
                List resultList = HelperTableLocalServiceUtil.executeSelectQuery(query);
                for (int i = 0; i < resultList.size(); i++) {
                    Object obj = resultList.get(i);
                    if (obj != null) {
                        ccpList.add(String.valueOf(obj));
                    }
                }
            }
            String ccpids = CommonUtils.CollectionToString(ccpList, false);
            saveCffDetails(Integer.valueOf(projectionId), ccpids);
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
    public static List<ViewDTO> searhView(final String viewName, final String viewType)
            throws SystemException, PortalException, Exception {
        LOGGER.info("Entering searchView method");
        List list = null;
        List inputList = new ArrayList();
        final String userId = (String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID);
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
        LOGGER.info("End of searchView method");
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
    public static void deleteView(final int viewId) throws SystemException, PortalException, Exception {
        LOGGER.info("Entering deleteView method with viewId " + viewId);
        List ipnput = new ArrayList();
        ipnput.add(viewId);
        CommonQueryUtils.updateAppData(ipnput, "deleteView");
        LOGGER.info("End of deleteView method");
    }

    private String getStatusAndNoOfLevel(int noOfLevel, int currentLevel, String status) {
        if (CommonUtils.WORKFLOW_STATUS_APPROVED.equals(status)) {
            if (noOfLevel == currentLevel) {
                return CommonUtils.WORKFLOW_STATUS_APPROVED;
            } else {
                return CommonUtils.WORKFLOW_STATUS_PENDING;
            }
        } else if (CommonUtils.WORKFLOW_STATUS_REJECTED.equals(status)) {

        } else if (CommonUtils.WORKFLOW_STATUS_CANCELLED.equals(status)) {

        }
        return StringUtils.EMPTY;
    }

    public String getNoOfLevelFromJbpm(SessionDTO sessionDTO, String cffMasterSid, String userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        WorkflowRuleDTO dto = new WorkflowRuleDTO();
        params.put("out_workflowDTO", dto);
        params.put("projectionId", cffMasterSid);
        VarianceCalculationLogic.submitWorkflow(userId, sessionDTO.getProcessId(), params, "submit");
        String noOfLevel = BPMProcessBean.getProcessVariableLog(sessionDTO.getProcessId(), "NoOfUsers");
        return noOfLevel;
    }

    public String getNoOfLevelFromDB(SessionDTO sessionDTO, String cffMasterSid, String userId) {
        List list = new ArrayList();
        String noOfLevel = StringUtils.EMPTY;
        list.add(cffMasterSid);
        List<Object[]> currentLevelList = CommonQueryUtils.getAppData(list, "getCFFCurrentLevel", null);
        if (currentLevelList != null) {
            Object[] obj = (Object[]) currentLevelList.get(0);
            if (obj[7] != null) {
                noOfLevel = String.valueOf(obj[7]);
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
        String recordCount = StringUtils.EMPTY;
        String filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(filters, getFileSelectionFilterMap()).toString();
        String query = SQlUtil.getQuery("getFileSelectionCount");
        query = query.replace("?", cffMasterId);
        filterQuery = filterQuery.replace("where", " AND ");
        query += filterQuery;
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        if (list != null) {
            recordCount = String.valueOf(list.get(0));
            if (!recordCount.equals("null")) {
                count = Integer.valueOf(recordCount);
            }
        }
        return count;

    }

    public List getFileSelection(String cffMasterId, Set<Container.Filter> filters) {
        List list = new ArrayList();
        String filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(filters, getFileSelectionFilterMap()).toString();
        String query = SQlUtil.getQuery("getFileSelection");
        query = query.replace("?", cffMasterId);
        filterQuery = filterQuery.replace("where", " AND ");
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
        filterMap.put("fileType", "HT.DESCRIPTION");
        filterMap.put("version", "A.VERSION");
        filterMap.put("activeFromDate", "A.ACTIVE_FROM");
        filterMap.put("activeToDate", "A.ACTIVE_TO");
        return filterMap;
    }

    private Map<String, String> getFileSelectionMap() {
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("fileName", "FORECAST_NAME");
        filterMap.put("fileType", "FILE_TYPE");
        filterMap.put("version", "VERSION");
        filterMap.put("activeFromDate", "FROM_PERIOD");
        filterMap.put("activeToDate", "TO_PERIOD");
        return filterMap;
    }
    
          public void updateInclusionFlag(int cffMasterId) {
        String query = SQlUtil.getQuery("inclusionFlagUpdate");
        query = query.replace("?", String.valueOf(cffMasterId));
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        
    }
    

}
