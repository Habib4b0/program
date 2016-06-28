package com.stpl.app.cff.util;

import com.stpl.app.cff.dao.DataSelectionDAO;
import com.stpl.app.cff.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.cff.dto.ApprovalDetailsDTO;
import com.stpl.app.cff.dto.CFFDTO;
import com.stpl.app.cff.dto.CFFResultsDTO;
import com.stpl.app.cff.dto.CFFSearchDTO;
import com.stpl.app.cff.dto.ProjectionSelectionDTO;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.queryUtils.CFFQueryUtils;
import com.stpl.app.cff.security.StplSecurity;
import com.stpl.app.model.HelperTable;
import com.stpl.app.parttwo.model.CffApprovalDetails;
import com.stpl.app.parttwo.service.CffApprovalDetailsLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.NoSuchUserException;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static com.stpl.app.cff.util.Constants.FrequencyConstants.*;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.FORECAST_END_DAY;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.FORECAST_END_PERIOD;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.FORECAST_START_DAY;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.FORECAST_START_PERIOD;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.FREQUENCY_DIVISION;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.HISTORY_END_DAY;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.HISTORY_END_MONTH;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.HISTORY_END_PERIOD;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.HISTORY_END_YEAR;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.HISTORY_START_DAY;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.HISTORY_START_MONTH;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.HISTORY_START_MONTH_DDLB;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.HISTORY_START_PERIOD;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.HISTORY_START_PERIOD_DDLB;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.HISTORY_START_YEAR;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.HISTORY_START_YEAR_DDLB;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.PROJECTION_END_DAY;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.PROJECTION_END_PERIOD;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.PROJECTION_START_DAY;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.PROJECTION_START_MONTH;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.PROJECTION_START_MONTH_DDLB;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.PROJECTION_START_PERIOD;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.PROJECTION_START_YEAR;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.PROJECTION_START_YEAR_DDLB;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

/**
 *
 * @author Manasa
 */
public class CommonUtils {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LogManager.getLogger(CommonUtils.class);
    /**
     * The Constant CFF_MASTER_SYSTEM_ID_SESSION.
     */
    public static final String CFF_MASTER_SYSTEM_ID_SESSION = "cffMasterSystemId";
    /**
     * The Constant WORKFLOW_STATUS_APPROVED.
     */
    public static final String WORKFLOW_STATUS_APPROVED = "Approved";
    /**
     * The Constant WORKFLOW_STATUS_REJECTED
     */
    public static final String WORKFLOW_STATUS_REJECTED = "Rejected";
    public static final String BUSINESS_PROCESS_TYPE = "BUSINESS_PROCESS_TYPE";
    /**
     * The Constant WORKFLOW_STATUS_CANCELLED
     */
    public static final String WORKFLOW_STATUS_CANCELLED = "Cancelled";
    /**
     * The Constant USERID_SESSION.
     */
    public static final String USERID_SESSION = "userId";
    /**
     * The Constant SUCCESS.
     */
    public static final String SUCCESS = "Success";
    /**
     * The Constant FAIL.
     */
    public static final String FAIL = "Fail";
    /**
     * The Constant COL_CURRENT.
     */
    public static final String COL_VALUE = "Value";
    /**
     * The Constant COL_PRIOR.
     */
    public static final String COL_PRIOR = "Prior";
    /**
     * The Constant COL_VARIANCE.
     */
    public static final String COL_VARIANCE = "Variance";
    /**
     * The Constant COL_PERCENTAGE.
     */
    public static final String COL_PERCENTAGE = "%Change";
    /**
     * The Constant MODULE_NAME.
     */
    public static final String MODULE_NAME = "CFF";
    /**
     * The Constant WORKFLOW_STATUS_PENDING.
     */
    public static final String WORKFLOW_STATUS_PENDING = "Pending";
    /**
     * The WorkFlowStatus list name.
     */
    public final static String WORKFLOW_STATUS = "WorkFlowStatus";
    /**
     * The Constant YES.
     */
    public static final String YES = "Yes";
    /**
     * The Constant NO.
     */
    public static final String NO = "No";
    /**
     * The Constant LOCKED_STATUS.
     */
    public static final String LOCKED_STATUS = "LOCKED_STATUS";
    /**
     * The Constant CFF_TYPE.
     */
    public static final String CFF_TYPE = "CFF_TYPE";
    /**
     * The Constant BOTH.
     */
    public static final String BOTH = "Both";
    /**
     * The Constant CHAR_PERCENT.
     */
    public static final char CHAR_PERCENT = '%';
    /**
     * The Constant CHAR_ASTERISK.
     */
    public static final char CHAR_ASTERISK = '*';
    /**
     * The Constant LATEST_ESTIMATE.
     */
    public static final String LATEST_ESTIMATE = "Latest Estimate";
    /**
     * The Constant UPDATE_CYCLE.
     */
    public static final String UPDATE_CYCLE = "Update Cycle";
    public static final String SPECIAL_CHAR = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*";

    public static final String STRING_NULL = "null";
    public CFFQueryUtils cffQueryUtils = new CFFQueryUtils();
    /**
     * The helper list util.
     */
    HelperListUtil helperListUtil = HelperListUtil.getInstance();

    public static final String VAR_GTS = "Gross Trade Sales";
    /**
     * The Constant VAR_CONTRACT_SALES.
     */
    public static final String VAR_CONTRACT_SALES = "Contract Sales @ WAC";
    /**
     * The Constant VAR_CONTRACT_UNITS.
     */
    public static final String VAR_CONTRACT_UNITS = "Contract Units";
    /**
     * The Constant VAR_PERCENTAGE.
     */
    public static final String VAR_PERCENTAGE = "% of Business";
    /**
     * The Constant VAR_DIS_AMOUNT.
     */
    public static final String VAR_DIS_AMOUNT = "Discount $";
    /**
     * The Constant VAR_DIS_RATE.
     */
    public static final String VAR_DIS_RATE = "Discount %";
    /**
     * The Constant VAR_NETSALES.
     */
    public static final String VAR_NETSALES = "Net Sales";

    static HashMap<String, String> cffColumnName = new HashMap<String, String>();
    public static final String BUSINESS_PROCESS_TYPE_NONMANDATED = "Non Mandated";
    public static final String BUSINESS_PROCESS_TYPE_MANDATED = "Mandated";
    public static final String BUSINESS_PROCESS_TYPE_CHANNELS = "Channel";
    public static final String BUSINESS_PROCESS_TYPE_RETURNS = "Returns";
    public static final Object[] visibleColumnItemSearch = {"fileName", "fileType", "version", "activeFromDate", "activeToDate"};
    public static final String[] columnHeaderItemSearch = {"File", "Type", "Version", "Active From", "Active To"};
    public static final String FILTERCOMBOBOX = "filterComboBox";
    private static final Date NULLOBJECT = null;
    public static final String EMPTY = ConstantsUtil.EMPTY;
    public static final String MMDDYYYY = "MM/dd/yyyy";
    private static CommonUtils object;

    /**
     * Gets the user info.
     *
     * @param userId the user id
     * @return the user info
     */
    public static User getUserInfo(final long userId) {
        DynamicQuery userSearchDynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
        userSearchDynamicQuery.add(RestrictionsFactoryUtil.eq(com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils.USER_ID, userId));
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

    public static int getHelperCode(String listName, String description) throws PortalException, SystemException {
        final DataSelectionDAO DAO = new DataSelectionDAOImpl();
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

    /**
     * Gets the customized cff results.
     *
     * @param resultsList the results list
     * @return the customized cff results
     */
    public List<CFFResultsDTO> getCustomizedCffResults(List<Object[]> resultsList) {
        List<CFFResultsDTO> cffResultsDTOs = new ArrayList<CFFResultsDTO>();
        CFFResultsDTO cffResultsDTO;
        String s = "";
        HashMap<Long, String> hm = getUserInfo();
        for (final Object[] obj : resultsList) {
            cffResultsDTO = new CFFResultsDTO();
            cffResultsDTO.setWorkflowId(String.valueOf(obj[0]));
            cffResultsDTO.setProjectionName(String.valueOf(obj[1]));

            s = String.valueOf(obj[3] == null ? "" : obj[3]);
            s = hm.get(Long.parseLong(s.trim().equals("") ? "0" : s));
            cffResultsDTO.setCreatedBy(s == null ? "" : s);

            s = String.valueOf(obj[4] == null ? "" : obj[4]);
            s = hm.get(Long.parseLong(s.trim().equals("") ? "0" : s));
            cffResultsDTO.setApprovedBy(s == null ? "" : s);

            s = String.valueOf(obj[6] == null ? "" : obj[6]);
            cffResultsDTO.setPriorLatestEstimate(s);
            s = String.valueOf(obj[7] == null ? "" : obj[7]);
            cffResultsDTO.setPriorUpdateCycle(s);
            cffResultsDTO.setProjectionId(Integer.valueOf(String.valueOf(obj[8])));
            cffResultsDTO.setWorkflowMasterSystemID(Integer.valueOf(String.valueOf(obj[9])));
            cffResultsDTOs.add(cffResultsDTO);

        }
        return cffResultsDTOs;
    }

    public static String[] objectListToStringArray(List<Object> objectList) {
        String[] stringArray = {};
        if (objectList != null) {
            stringArray = new String[objectList.size()];
            stringArray = objectList.toArray(stringArray);
        }
        return stringArray;
    }

    public static List<String> objectListToStringList(List<Object> objectList) {
        return Arrays.asList(objectListToStringArray(objectList));
    }

    public List<CFFResultsDTO> getCustomisedCFF(List<Object[]> list, SessionDTO sessionDTO) {
        List<CFFResultsDTO> resultList = new ArrayList<CFFResultsDTO>();
        CFFResultsDTO cffResultsDTO;
        final CFFDTO cffDTO = new CFFDTO();
        HashMap<Long, String> hm = getUserInfo();
        String userName = "";
        int projectionId = 0;
        for (final Object[] obj : list) {

            cffResultsDTO = new CFFResultsDTO();

            if (obj[0] != null) {
                cffResultsDTO.setWorkflowMasterSystemID(Integer.valueOf(String.valueOf(obj[0])));
            }

            if (obj[1] != null) {
                cffResultsDTO.setWorkflowId(String.valueOf(obj[1]));
            }
            if (obj[2] != null) {
                projectionId = Integer.valueOf(String.valueOf(obj[2]));
                cffResultsDTO.setProjectionMasterSid(projectionId);
            }
            if (obj[3] != null) {
                cffResultsDTO.setProjectionName(String.valueOf(obj[3]));
            }
            if (obj[4] != null) {
                cffResultsDTO.setProjectionType(String.valueOf(obj[4]));
            }
            if (obj[5] != null) {
                cffResultsDTO.setWorkflowStatus(String.valueOf(obj[5]));
            }

            userName = String.valueOf(obj[6] == null ? "" : obj[6]);
            userName = hm.get(Long.parseLong(userName.trim().equals("") ? "0" : userName));
            cffResultsDTO.setApprovedBy(userName);

            if (obj[7] != null) {
                cffResultsDTO.setApprovalDate((Date) obj[7]);

            }

            userName = String.valueOf(obj[8] == null ? "" : obj[8]);
            userName = hm.get(Long.parseLong(userName.trim().equals("") ? "0" : userName));
            cffResultsDTO.setCreatedBy(userName);

            if (obj[9] != null) {
                cffResultsDTO.setCreatedDate((Date) obj[9]);
            }

            if (obj[10] != null) {
                String cffName = String.valueOf(obj[10]);
                String[] latestEstimet = cffName.split("-");
                int count = latestEstimet.length;
                if (count > 0) {
                    cffResultsDTO.setPriorLatestEstimate(latestEstimet[0]);
                }
                if (count > 1) {
                    cffResultsDTO.setPriorUpdateCycle(latestEstimet[1]);
                }

            }

            List<Object[]> priorNameList = cffQueryUtils.getPriorName(projectionId, sessionDTO);
            for (final Object[] name : priorNameList) {
                try {
                    String desription = CommonUtils.getHelperDescription(Integer.valueOf(name[1].toString()));

                    if ("Latest Estimate".equals(desription)) {
                        cffResultsDTO.setPriorLatestEstimate(name[2].toString());
                    }
                    if ("Update Cycle".equals(desription)) {
                        cffResultsDTO.setPriorUpdateCycle(name[2].toString());
                    }
                } catch (PortalException ex) {
                    LOGGER.error(ex);
                } catch (SystemException ex) {
                    LOGGER.error(ex);
                }
            }
            resultList.add(cffResultsDTO);
        }
        return resultList;
    }

    public CFFResultsDTO getCustomisedLatestApprovedCff(List<Object[]> list) {
        CFFResultsDTO cffResultsDTO;
        Object[] obj = (Object[]) list.get(0);

        cffResultsDTO = new CFFResultsDTO();

        if (obj[0] != null) {
            cffResultsDTO.setWorkflowMasterSystemID(Integer.valueOf(String.valueOf(obj[0])));
        }

        if (obj[1] != null) {

            cffResultsDTO.setApprovalDate((Date) obj[1]);
        }

        return cffResultsDTO;
    }

    public List<CFFResultsDTO> getCustomisedCFFDeatils(List<Object[]> list) {
        List<CFFResultsDTO> resultList = new ArrayList<CFFResultsDTO>();
        CFFResultsDTO cffResultsDTO;
        HashMap<Long, String> hm = getUserInfo();
        String userName = "";
        for (final Object[] obj : list) {

            cffResultsDTO = new CFFResultsDTO();

            if (obj[0] != null) {
                cffResultsDTO.setWorkflowMasterSystemID(Integer.valueOf(String.valueOf(obj[0])));
            }

            if (obj[1] != null) {
                cffResultsDTO.setWorkflowId(String.valueOf(obj[1]));
            }
            if (obj[2] != null) {
                cffResultsDTO.setProjectionMasterSid(Integer.valueOf(String.valueOf(obj[2])));
            }
            if (obj[3] != null) {
                cffResultsDTO.setProjectionName(String.valueOf(obj[3]));
            }
            if (obj[4] != null) {
                cffResultsDTO.setProjectionType(String.valueOf(obj[4]));
            }
            if (obj[5] != null) {
                cffResultsDTO.setWorkflowStatus(String.valueOf(obj[4]));
            }

            userName = String.valueOf(obj[6] == null ? "" : obj[6]);
            userName = hm.get(Long.parseLong(userName.trim().equals("") ? "0" : userName));
            cffResultsDTO.setApprovedBy(userName);

            if (obj[7] != null) {
                cffResultsDTO.setApprovalDate((Date) obj[7]);
            }

            userName = String.valueOf(obj[8] == null ? "" : obj[8]);
            userName = hm.get(Long.parseLong(userName.trim().equals("") ? "0" : userName));
            cffResultsDTO.setCreatedBy(userName);

            if (obj[9] != null) {
                cffResultsDTO.setCreatedDate((Date) obj[9]);
            }
            resultList.add(cffResultsDTO);
        }
        return resultList;
    }

    /**
     * Gets the user info.
     *
     * @return the user info
     */
    public HashMap<Long, String> getUserInfo() {

        List<User> users = new ArrayList<User>();
        DynamicQuery userGroupDynamicQuery = DynamicQueryFactoryUtil
                .forClass(User.class);
        try {
            users = UserLocalServiceUtil.dynamicQuery(userGroupDynamicQuery);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        HashMap<Long, String> userMap = new HashMap<Long, String>();
        for (User user : users) {
            userMap.put(user.getUserId(), user.getFullName());
        }
        return userMap;
    }

    /**
     * To get the company native select.
     *
     * @param select the select
     * @param helperList the helper list
     * @param selectedValue
     * @return the native select
     * @throws java.lang.Exception
     */
    public ComboBox getNativeSelect(final ComboBox select, final List<HelperDTO> helperList, final String selectedValue) throws Exception {
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO);
            if (!StringUtils.EMPTY.equals(selectedValue) && selectedValue.equals(helperDTO.getDescription())) {
                select.select(helperDTO);
            }
        }

        return select;
    }

    /**
     * Gets the code from helper table.
     *
     * @param description the description
     * @param listName the list name
     * @return the code from helper table
     */
    @SuppressWarnings("unchecked")
    public static int getCodeFromHelperTable(String description, String listName) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil
                .forClass(HelperTable.class);
        int helperTableId = 0;
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtil.DESCRIPTION,
                description));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtil.LIST_NAME,
                listName));

        List<HelperTable> resultList = new ArrayList<HelperTable>();
        try {
            resultList = HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);

            if (resultList.size() > ConstantsUtil.ZERO_NUM) {
                HelperTable helperTable = resultList.get(0);
                helperTableId = helperTable.getHelperTableSid();
            }
        } catch (SystemException e) {
            LOGGER.error(e);
        }
        return helperTableId;
    }

    public static int getHelperTableSId(String description, String listName) {
        int id = 0;
        try {
            if (description != null && !description.equals(StringUtils.EMPTY)) {
                List<HelperTable> helperTable = HelperTableLocalServiceUtil.findByHelperTableDetails(listName);
                for (HelperTable helperTable1 : helperTable) {
                    if (helperTable1.getDescription().equalsIgnoreCase(description)) {
                        id = helperTable1.getHelperTableSid();
                        break;
                    }
                }
            }
            return description == null || StringUtils.EMPTY.equals(description) ? 0 : id;
        } catch (SystemException ex) {
            LOGGER.error(ex);
            return 0;
        }

    }

    /**
     * Gets the customized search results.
     *
     * @param resultList the result list
     * @param excelFlag
     * @return the customized search results
     * @throws java.lang.Exception
     */
    public List<CFFSearchDTO> getCustomizedSearchResults(List<Object[]> resultList, Boolean excelFlag) throws Exception {
        List<CFFSearchDTO> cffSearchDTOs = new ArrayList<CFFSearchDTO>();
        CFFSearchDTO cffSearchDTOLoop;
        SimpleDateFormat form = new SimpleDateFormat("MM/dd/YYYY");
        Map<Integer, String> userInfo = StplSecurity.userMap;
        for (Object[] obj : resultList) {
            cffSearchDTOLoop = new CFFSearchDTO();
            cffSearchDTOLoop.setFinancialForecastId(String.valueOf(obj[0]));
            if (obj[1] != null) {
                cffSearchDTOLoop.setFinancialForecastName(String.valueOf(obj[1]));
            } else {
                cffSearchDTOLoop.setFinancialForecastName(StringUtils.EMPTY);
            }
            if (obj[2] != null) {
                HelperDTO helper = HelperListUtil.getInstance().getHelperDTObyID(Integer.valueOf(String.valueOf(obj[2])));
                String type = ((HelperDTO) helper).getDescription();
                if (type.equals("-Select One-")) {
                    cffSearchDTOLoop.setTypeDesc("");
                } else {
                    cffSearchDTOLoop.setTypeDesc(type);
                }
//                cffSearchDTOLoop.setTypeDesc(((HelperDTO) helper).getDescription());
//                if (String.valueOf(obj[2]).equals("-Select One-")) {
//                    cffSearchDTOLoop.setTypeDesc("");
//                }
            }
            if (obj[3] != null) {
                HelperDTO statusdto = HelperListUtil.getInstance().getHelperDTObyID(Integer.valueOf(String.valueOf(obj[3])));
                cffSearchDTOLoop.setStatusDesc(statusdto == null ? StringUtils.EMPTY : ((HelperDTO) statusdto).getDescription());
                if (String.valueOf(obj[3]).equals("-Select One-")) {
                    cffSearchDTOLoop.setStatusDesc("");
                }
            }

//            cffSearchDTOLoop.setFinalApprovalDate((Date) obj[4]);
//            cffSearchDTOLoop.setFinalApprovalDateExcel(form.format((Date) obj[4]));
//                 if(obj[4]!=null){
//            cffSearchDTOLoop.setFinalApprovalDateExcel(form.format((Date) obj[4]));
//            }else{
//                cffSearchDTOLoop.setFinalApprovalDateExcel(null);
//                 }

            if (obj[4] != null) {
                cffSearchDTOLoop.setFinalApprovalDate((Date) obj[4]);
                cffSearchDTOLoop.setFinalApprovalDateExcel(form.format((Date) obj[4]));

            }

            if (obj[5] != null) {
                if (cffSearchDTOLoop.getStatusDesc().equals("Approved")) {
                    cffSearchDTOLoop.setApprovedBy(String.valueOf(userInfo.get(obj[5])));
                } else {
                    cffSearchDTOLoop.setApprovedBy("");
                }
            } else {
                cffSearchDTOLoop.setApprovedBy("");
            }
            if (obj[6] != null) {
                cffSearchDTOLoop.setActiveFromDateExcel(form.format((Date) obj[6]));
                cffSearchDTOLoop.setActiveFromDate((Date) obj[6]);
            }
            if (obj[7] != null) {
                cffSearchDTOLoop.setActiveToDateExcel(form.format((Date) obj[7]));
                cffSearchDTOLoop.setActiveToDate((Date) obj[7]);
            }

            if (obj[9] != null) {
                cffSearchDTOLoop.setCreationFromDate((Date) obj[9]);
            }
            if (obj[10] != null) {
                cffSearchDTOLoop.setCreatedUser(Integer.valueOf(String.valueOf(obj[10])));
            }
            cffSearchDTOLoop.setCffMasterSid(Integer.valueOf(String.valueOf(obj[0])));
            cffSearchDTOs.add(cffSearchDTOLoop);
        }
        return cffSearchDTOs;
    }

    public List<ApprovalDetailsDTO> getCustomisedApprovalDetails(List<CffApprovalDetails> approvalDetails) {
        List<ApprovalDetailsDTO> custoList = new ArrayList<ApprovalDetailsDTO>();
        ApprovalDetailsDTO dto;
        HashMap<Long, String> userInfo = getUserInfo();
        try {
            if (approvalDetails != null) {

                for (CffApprovalDetails details : approvalDetails) {

                    dto = new ApprovalDetailsDTO();
                    dto.setApprovedBy(userInfo.get(Long.parseLong(String.valueOf(details.getApprovedBy()))));
                    dto.setApprovalSequence(String.valueOf(details.getApprovalSequence()));
                    dto.setApprovedDate(details.getApprovedDate());
                    dto.setApprovalStatus(getHelperDescription(details.getApprovalStatus()));
                    custoList.add(dto);
                }

            }
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return custoList;
    }

    /**
     * Gets the approval details.
     *
     * @return the approval details
     */
    public HashMap<Integer, String> getApprovalDetails() {

        DynamicQuery cffApprovalDetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(CffApprovalDetails.class);
        List<CffApprovalDetails> resultsList = new ArrayList<CffApprovalDetails>();
        String s = "";
        HashMap<Long, String> userInfo = getUserInfo();
        int cffMasterId = 0;
        try {
            resultsList = CffApprovalDetailsLocalServiceUtil.dynamicQuery(cffApprovalDetailsDynamicQuery);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        HashMap<Integer, String> approvalDetails = new HashMap<Integer, String>();
        for (CffApprovalDetails cffApprovalDetails : resultsList) {
            cffMasterId = cffApprovalDetails.getCffMasterSid();
            if (approvalDetails.get(cffMasterId) == null) {
                s = userInfo.get(Long.parseLong(String.valueOf(cffApprovalDetails.getApprovedBy())));
                approvalDetails.put(cffApprovalDetails.getCffMasterSid(), s);
            } else {
                s = approvalDetails.get(cffMasterId);
                s = s + "," + userInfo.get(Long.parseLong(String.valueOf(cffApprovalDetails.getApprovedBy())));
                approvalDetails.put(cffApprovalDetails.getCffMasterSid(), s);
            }

        }
        return approvalDetails;
    }

    /**
     * Criteria check
     *
     * @param value
     * @return
     */
    public static boolean isValidCriteria(String value) {
        boolean isValid = false;
        if (value != null && !"null".equals(String.valueOf(value)) && !StringUtils.EMPTY.equals(String.valueOf(value))
                && !StringUtils.isEmpty(String.valueOf(value)) && !StringUtils.isBlank(String.valueOf(value))) {
            isValid = true;
        } else {
            isValid = false;
        }
        return isValid;
    }

    /**
     * To get the description using id from helper table
     *
     * @param code
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static String getHelperDescription(int code) throws PortalException, SystemException {
        HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(code);
        return helperTable.getDescription();
    }

    /**
     * Converting String list to String array
     *
     * @param stringList
     * @return
     */
    public static String stringListToString(List<String> stringList) {
        StringBuilder builder = new StringBuilder(StringUtils.EMPTY);
        if (stringList != null && !stringList.isEmpty()) {
            for (int loop = 0, limit = stringList.size(); loop < limit; loop++) {
                builder.append("'");
                builder.append(stringList.get(loop));
                builder.append("'");
                if (loop != (limit - 1)) {
                    builder.append(", ");
                }
            }
        }
        return builder.toString();
    }

    /**
     * To get the full name of user using user id
     *
     * @param userId
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static String getUserFLName(String userId) throws PortalException, SystemException {
        String name = StringUtils.EMPTY;
        User userInfo = getUser(userId);
        if (userInfo != null) {
            name = userInfo.getLastName() + ", " + userInfo.getFirstName();
        }
        return name;
    }

    /**
     * Convert null to Empty
     *
     * @param value
     * @return
     */
    public static String convertNullToEmpty(Object value) {
        String returnValue;
        if (value == null || "null".equals(String.valueOf(value))) {
            returnValue = StringUtils.EMPTY;
        } else {
            returnValue = String.valueOf(value);
        }
        return returnValue;
    }

    public static User getUser(final String userId) throws PortalException, SystemException {
        User loggedUserDetails = null;

        try {
            loggedUserDetails = UserLocalServiceUtil.getUser(Long.valueOf(userId));
        } catch (NoSuchUserException noSuchUserException) {
            loggedUserDetails = null;
            LOGGER.error(noSuchUserException.getMessage());
        }

        return loggedUserDetails;
    }

    public static String getDBColumnName(String visibleColumnName) {
        return cffColumnName.get(visibleColumnName);

    }

    public static HashMap<String, String> loadDbColumnName() {
        cffColumnName.put("financialForecastId", "CFF_MASTER_SID");
        cffColumnName.put("financialForecastName", "CFF_NAME");
        cffColumnName.put("typeDesc", "CFF_TYPE");
        cffColumnName.put("statusDesc", "APPROVAL_STATUS");
        cffColumnName.put("finalApprovalDate", "APPROVED_DATE");
        cffColumnName.put("approvedBy", "APPROVED_BY");
        cffColumnName.put("activeFromDate", "ACTIVE_FROM_DATE");
        cffColumnName.put("activeToDate", "ACTIVE_TO_DATE");
        return cffColumnName;
    }

    public ComboBox loadComboBox(final ComboBox select,
            String listName, boolean isFilter) throws Exception {
        select.removeAllItems();
        final HelperDTO defaultValue = new HelperDTO(0, isFilter ? ConstantsUtil.SHOW_ALL : ConstantsUtil.SELECT_ONE);
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setItemCaptionPropertyId(ConstantsUtil.DESCRIPTION);
        select.setData(listName);
        List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        helperList.add(defaultValue);
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<HelperDTO>(HelperDTO.class);
        if (helperListUtil.getListNameMap().get(listName) != null) {
            helperList.addAll(helperListUtil.getListNameMap().get(listName));
        }
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == null ? ConstantsUtil.SELECT_ONE : select.getValue()));
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtil.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription((String) (select.getValue() == null ? ConstantsUtil.SELECT_ONE : ((HelperDTO) select.getValue()).getDescription()));
            }
        });
        return select;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static void getHistoryAndProjectionDetails(ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = 1;
        int historyStartPeriod = 1;
        int historyEndPeriod = 1;
        int historyEndMonth = 1;
        int historyEndYear = 1;
        int forecastStartPeriod = 1;
        int forecastEndPeriod = 1;
        int projectionStartPeriod = 1;
        int projectionEndPeriod = 1;
        String frequency = projSelDTO.getFrequency();

        if (frequency.equals(QUARTERLY.getConstant())) {
            frequencyDivision = 4;
            int a[] = getQSPeriodDetails(projSelDTO, frequencyDivision);
            historyStartPeriod = a[1];
            historyEndPeriod = a[2];
            forecastStartPeriod = a[3];
            forecastEndPeriod = a[4];
            projectionStartPeriod = a[5];
            projectionEndPeriod = a[6];
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant()) || frequency.equals(SEMI_ANNUAL.getConstant())) {
            frequencyDivision = 2;
            int a[] = getQSPeriodDetails(projSelDTO, frequencyDivision);
            historyStartPeriod = a[1];
            historyEndPeriod = a[2];
            forecastStartPeriod = a[3];
            forecastEndPeriod = a[4];
            projectionStartPeriod = a[5];
            projectionEndPeriod = a[6];
        } else if (frequency.equals(MONTHLY.getConstant())) {
            frequencyDivision = 12;
            historyStartPeriod = projSelDTO.getForecastDTO().getHistoryStartMonth();
            historyEndPeriod = projSelDTO.getForecastDTO().getHistoryEndMonth();
            forecastStartPeriod = projSelDTO.getForecastDTO().getForecastStartMonth();
            forecastEndPeriod = projSelDTO.getForecastDTO().getForecastEndMonth();
            projectionStartPeriod = projSelDTO.getForecastDTO().getProjectionStartMonth();
            projectionEndPeriod = projSelDTO.getForecastDTO().getProjectionEndMonth();
        } else if (frequency.equals(ANNUALLY.getConstant()) || frequency.equals(ANNUAL.getConstant())) {
            frequencyDivision = 1;
            historyStartPeriod = projSelDTO.getForecastDTO().getHistoryStartYear();
            historyEndPeriod = projSelDTO.getForecastDTO().getHistoryEndYear();
            forecastStartPeriod = projSelDTO.getForecastDTO().getForecastStartYear();
            forecastEndPeriod = projSelDTO.getForecastDTO().getForecastEndYear();
            projectionStartPeriod = projSelDTO.getForecastDTO().getProjectionStartYear();
            projectionEndPeriod = projSelDTO.getForecastDTO().getProjectionEndYear();
        }
        historyEndMonth = projSelDTO.getForecastDTO().getHistoryEndMonth();
        historyEndYear = projSelDTO.getForecastDTO().getHistoryEndYear();
        if (historyEndPeriod == forecastStartPeriod && historyEndYear == projSelDTO.getForecastDTO().getForecastStartYear()) {
            historyEndPeriod--;
            if (frequencyDivision == 1) {
                historyEndYear = historyEndYear - 1;
                historyEndMonth = 12;
            } else if (historyEndPeriod < 1) {
                historyEndPeriod = frequencyDivision;
                historyEndMonth = 12;
                historyEndYear = historyEndYear - 1;
            } else {
                historyEndMonth = (12 / frequencyDivision) * historyEndPeriod;
            }
        }

        projSelDTO.setFrequencyDivision(frequencyDivision);
        projSelDTO.addProjectionDetails(FREQUENCY_DIVISION.getConstant(), frequencyDivision);
        projSelDTO.addProjectionDetails(HISTORY_END_YEAR.getConstant(), historyEndYear);
        projSelDTO.addProjectionDetails(HISTORY_END_MONTH.getConstant(), historyEndMonth);
        projSelDTO.addProjectionDetails(HISTORY_START_YEAR.getConstant(), projSelDTO.getForecastDTO().getHistoryStartYear());
        projSelDTO.addProjectionDetails(HISTORY_START_MONTH.getConstant(), projSelDTO.getForecastDTO().getHistoryStartMonth());
        projSelDTO.addProjectionDetails(HISTORY_START_PERIOD.getConstant(), historyStartPeriod);
        projSelDTO.addProjectionDetails(HISTORY_END_PERIOD.getConstant(), historyEndPeriod);
        projSelDTO.addProjectionDetails(FORECAST_START_PERIOD.getConstant(), forecastStartPeriod);
        projSelDTO.addProjectionDetails(FORECAST_END_PERIOD.getConstant(), forecastEndPeriod);
        projSelDTO.addProjectionDetails(PROJECTION_START_PERIOD.getConstant(), projectionStartPeriod);
        projSelDTO.addProjectionDetails(PROJECTION_END_PERIOD.getConstant(), projectionEndPeriod);
        projSelDTO.addProjectionDetails(PROJECTION_START_YEAR.getConstant(), projSelDTO.getForecastDTO().getProjectionStartYear());
        projSelDTO.addProjectionDetails(PROJECTION_START_MONTH.getConstant(), projSelDTO.getForecastDTO().getProjectionStartMonth());
        projSelDTO.addProjectionDetails(PROJECTION_START_YEAR_DDLB.getConstant(), projSelDTO.getForecastDTO().getProjectionStartYear());
        projSelDTO.addProjectionDetails(PROJECTION_START_MONTH_DDLB.getConstant(), projSelDTO.getForecastDTO().getProjectionStartMonth());
        projSelDTO.addProjectionDetails(HISTORY_START_DAY.getConstant(), 1);
        projSelDTO.addProjectionDetails(FORECAST_START_DAY.getConstant(), 1);
        projSelDTO.addProjectionDetails(PROJECTION_START_DAY.getConstant(), 1);
        projSelDTO.addProjectionDetails(HISTORY_END_DAY.getConstant(), getEndDay(historyEndMonth, historyEndYear));
        projSelDTO.addProjectionDetails(FORECAST_END_DAY.getConstant(), getEndDay(projSelDTO.getForecastDTO().getForecastEndMonth(), projSelDTO.getForecastDTO().getForecastEndYear()));
        projSelDTO.addProjectionDetails(PROJECTION_END_DAY.getConstant(), getEndDay(projSelDTO.getForecastDTO().getProjectionEndMonth(), projSelDTO.getForecastDTO().getProjectionEndYear()));

        projSelDTO.setProjectionStartYear(projSelDTO.getForecastDTO().getProjectionStartYear());
        projSelDTO.setProjectionStartMonth(projSelDTO.getForecastDTO().getProjectionStartMonth());
        projSelDTO.setHistoryEndYear(historyEndYear);
        projSelDTO.setHistoryEndMonth(historyEndMonth);
        projSelDTO.setHistoryStartPeriod(historyStartPeriod);
        projSelDTO.setHistoryEndPeriod(historyEndPeriod);
        projSelDTO.setForecastStartPeriod(forecastStartPeriod);
        projSelDTO.setForecastEndPeriod(forecastEndPeriod);
        projSelDTO.setProjectionStartPeriod(projectionStartPeriod);
        projSelDTO.setProjectionEndPeriod(projectionEndPeriod);
        projSelDTO.setHistoryEndDay(getEndDay(projSelDTO.getForecastDTO().getHistoryEndMonth(), projSelDTO.getForecastDTO().getHistoryEndYear()));
        projSelDTO.setForecastStartDay(1);
        projSelDTO.setForecastEndDay(getEndDay(projSelDTO.getForecastDTO().getForecastEndMonth(), projSelDTO.getForecastDTO().getForecastEndYear()));
        projSelDTO.setProjectionStartDay(1);
        projSelDTO.setProjectionEndDay(getEndDay(projSelDTO.getForecastDTO().getProjectionEndMonth(), projSelDTO.getForecastDTO().getProjectionEndYear()));
        
        getHistoryDetail(projSelDTO);
        
    }

    static int getPeriod(int monthNo, int division) {
        return ((monthNo - 1) / division) + 1;
    }

    static int getEndDay(int monthNo, int year) {
        Calendar ob = Calendar.getInstance();
        ob.set(year, monthNo - 1, 1);
        int daysInMonth = ob.getActualMaximum(Calendar.DAY_OF_MONTH);
        return daysInMonth;
    }

    public static int[] getQSPeriodDetails(ProjectionSelectionDTO projSelDTO, int freqDiv) {
        int[] ret = new int[7];
        ret[0] = freqDiv;
        ret[1] = getPeriod(projSelDTO.getHistoryStartMonth(), 12 / freqDiv);
        ret[2] = getPeriod(projSelDTO.getForecastDTO().getHistoryEndMonth(), 12 / freqDiv);
        ret[3] = getPeriod(projSelDTO.getForecastDTO().getForecastStartMonth(), 12 / freqDiv);
        ret[4] = getPeriod(projSelDTO.getForecastDTO().getForecastEndMonth(), 12 / freqDiv);
        ret[5] = getPeriod(projSelDTO.getForecastDTO().getProjectionStartMonth(), 12 / freqDiv);
        ret[6] = getPeriod(projSelDTO.getForecastDTO().getProjectionEndMonth(), 12 / freqDiv);
        return ret;
    }

    /**
     * To get Start year, Start period and Start month
     *
     * @param projSelDTO
     * @return
     */
    static void getHistoryDetail(ProjectionSelectionDTO projSelDTO) {
        int historyNum = projSelDTO.getHistoryNum();
        int frequencyDivision = projSelDTO.getProjectionDetails(FREQUENCY_DIVISION.getConstant());

        int historyStartYear = projSelDTO.getProjectionDetails(HISTORY_END_YEAR.getConstant());
        int historyEndPeriod = projSelDTO.getProjectionDetails(HISTORY_END_PERIOD.getConstant());
        int historyStartFreq = historyEndPeriod + 1;
        if (frequencyDivision == 1) {
            historyStartYear = historyStartYear - historyNum + 1;
            historyStartFreq = historyStartYear;
        } else {
            int historyTempFreq = historyNum - historyEndPeriod;
            if (historyTempFreq > 0) {
                historyStartYear = historyStartYear - historyTempFreq / frequencyDivision;
                historyStartFreq = 1;
                if (historyTempFreq % frequencyDivision > 0) {
                    historyStartYear = historyStartYear - 1;
                    historyStartFreq = frequencyDivision - (historyTempFreq % frequencyDivision) + 1;

                }
            } else {
                historyStartFreq = historyStartFreq - historyNum;
            }
        }
        if (frequencyDivision == 1) {
            projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), frequencyDivision);
        } else if (frequencyDivision == 2) {
            if (historyStartFreq == 1) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), 1);
            } else if (historyStartFreq == 2) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), 7);
            }
        } else if (frequencyDivision == 4) {
            if (historyStartFreq == 1) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), 1);
            } else if (historyStartFreq == 2) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), 4);
            } else if (historyStartFreq == 3) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), 7);
            } else if (historyStartFreq == 4) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), 10);
            }
        } else if (frequencyDivision == 12) {
            projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), 12);
        }
        if (projSelDTO.getProjectionDetails(HISTORY_START_PERIOD.getConstant()) == historyStartFreq && projSelDTO.getProjectionDetails(HISTORY_START_YEAR.getConstant()) == historyStartYear) {
            projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), projSelDTO.getProjectionDetails(HISTORY_START_MONTH.getConstant()));
        }
        projSelDTO.addProjectionDetails(HISTORY_START_PERIOD_DDLB.getConstant(), historyStartFreq);
        projSelDTO.addProjectionDetails(HISTORY_START_YEAR_DDLB.getConstant(), historyStartYear);
        projSelDTO.setHistoryStartPeriod(historyStartFreq);
        projSelDTO.setHistoryStartYear(historyStartYear);
        projSelDTO.setHistoryStartMonth(1);
        projSelDTO.setHistoryStartDay(1);
        boolean modified = false;
        if (projSelDTO.getProjectionDetails(PROJECTION_START_YEAR.getConstant()) < historyStartYear) {
            projSelDTO.setProjectionStartYear(projSelDTO.getHistoryStartYear());
            projSelDTO.addProjectionDetails(PROJECTION_START_YEAR_DDLB.getConstant(), projSelDTO.getHistoryStartYear());
            modified = true;
        }
        if (projSelDTO.getProjectionStartYear() == projSelDTO.getHistoryStartYear()) {
            if (projSelDTO.getProjectionDetails(PROJECTION_START_MONTH_DDLB.getConstant()) < projSelDTO.getHistoryStartMonth() || (modified && projSelDTO.getProjectionDetails(PROJECTION_START_MONTH_DDLB.getConstant()) > projSelDTO.getHistoryStartMonth())) {
                projSelDTO.setProjectionStartMonth(projSelDTO.getHistoryStartMonth());
                projSelDTO.addProjectionDetails(PROJECTION_START_MONTH_DDLB.getConstant(), projSelDTO.getHistoryStartMonth());
                projSelDTO.setProjectionStartPeriod(historyStartFreq);
                projSelDTO.addProjectionDetails(PROJECTION_START_PERIOD.getConstant(), historyStartFreq);
            }
        }
        projSelDTO.setEndYear(projSelDTO.getForecastDTO().getForecastEndYear());
        projSelDTO.setEndPeriod(projSelDTO.getForecastEndPeriod());
        projSelDTO.setEndMonth(projSelDTO.getForecastDTO().getForecastEndMonth());
        projSelDTO.setEndDay(projSelDTO.getForecastEndDay());
        projSelDTO.setStartYear(projSelDTO.getHistoryStartYear());
        projSelDTO.setStartPeriod(projSelDTO.getHistoryStartPeriod());
        projSelDTO.setStartMonth(projSelDTO.getHistoryStartMonth());
        projSelDTO.setStartDay(projSelDTO.getHistoryStartDay());

    }

    public static Integer getProjectionNumber(String frequency, SessionDTO session) {
        LOGGER.info("Entering getProjectionNumber method");
        Integer period = session.getProjectionPeriod(frequency);
        if (period == null || period.equals(0)) {
            period = CommonUtils.getProjections(session.getForecastDTO().getForecastStartDate(), session.getForecastDTO().getForecastEndDate(), frequency);
            session.addProjectionPeriod(frequency, period);
        }
        LOGGER.info("End of getProjectionNumber method");
        return period;
    }

    public static int getProjections(Date startDate, Date endDate, String frequency) {
        if (endDate.after(startDate)) {
            if (frequency.equals(ANNUALLY.getConstant())) {
                return endDate.getYear() - startDate.getYear();
            } else {
                Calendar startCalendar = Calendar.getInstance();
                startCalendar.setTime(startDate);
                Calendar endCalendar = Calendar.getInstance();
                endCalendar.setTime(endDate);
                int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
                int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
                if (frequency.equals(QUARTERLY.getConstant())) {
                    if (diffMonth % 3 == 0) {
                        return diffMonth / 3;
                    } else {
                        return (diffMonth / 3) + 1;
                    }

                } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                    if (diffMonth % 6 == 0) {
                        return diffMonth / 6;
                    } else {
                        return (diffMonth / 6) + 1;
                    }
                } else if (frequency.equals(MONTHLY.getConstant())) {
                    return diffMonth;
                }
                return 0;

            }
        }
        return 0;
    }

    public static int getHistoryProjectionNum(String frequency, SessionDTO session) {
        Map<String, Integer> historyEndDetails = getHistoryEndDetails(session, frequency);
        Date dt = getDate(historyEndDetails.get(HISTORY_END_MONTH.getConstant()), historyEndDetails.get(HISTORY_END_YEAR.getConstant()));
        int endValue = getProjections(session.getForecastDTO().getProjectionStartDate(), dt, frequency);
        return endValue;
    }

    public static Date getDate(int month, int year) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(year, month, 1);
        return startCalendar.getTime();
    }

    public static Map<String, Integer> getHistoryEndDetails(SessionDTO session, String frequency) {
        Map<String, Integer> history = new HashMap<String, Integer>();
        history = session.getHistoryEndDetails(frequency);
        if (history == null || history.isEmpty()) {
            history = getHistoryEndDetail(session, frequency);
            session.addHistoryEndDetails(frequency, history);
        }
        return history;
    }

    private static Map<String, Integer> getHistoryEndDetail(SessionDTO session, String frequency) {
        Map<String, Integer> history = new HashMap<String, Integer>();
        int frequencyDivision = 1;
        int historyEndPeriod = 1;
        int historyEndMonth = 1;
        int historyEndYear = 1;
        int forecastStartPeriod = 1;
        if (frequency.equals(QUARTERLY.getConstant())) {
            frequencyDivision = 4;
            historyEndPeriod = getPeriod(session.getForecastDTO().getHistoryEndMonth(), 3);
            forecastStartPeriod = getPeriod(session.getForecastDTO().getForecastStartMonth(), 3);
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequencyDivision = 2;
            historyEndPeriod = getPeriod(session.getForecastDTO().getHistoryEndMonth(), 6);
            forecastStartPeriod = getPeriod(session.getForecastDTO().getForecastStartMonth(), 6);
        } else if (frequency.equals(MONTHLY.getConstant())) {
            frequencyDivision = 12;
            historyEndPeriod = session.getForecastDTO().getHistoryEndMonth();
            forecastStartPeriod = session.getForecastDTO().getForecastStartMonth();
        } else if (frequency.equals(ANNUALLY.getConstant())) {
            frequencyDivision = 1;
            historyEndPeriod = session.getForecastDTO().getHistoryEndYear();
            forecastStartPeriod = session.getForecastDTO().getForecastStartYear();
        }
        historyEndMonth = session.getForecastDTO().getHistoryEndMonth();
        historyEndYear = session.getForecastDTO().getHistoryEndYear();
        if (historyEndPeriod == forecastStartPeriod && historyEndYear == session.getForecastDTO().getForecastStartYear()) {
            historyEndPeriod--;
            if (frequencyDivision == 1) {
                historyEndYear = historyEndYear - 1;
                historyEndMonth = 12;
            } else if (historyEndPeriod < 1) {
                historyEndPeriod = frequencyDivision;
                historyEndMonth = 12;
                historyEndYear = historyEndYear - 1;
            } else {
                historyEndMonth = (12 / frequencyDivision) * historyEndPeriod;
            }
        }
        history.put(FREQUENCY_DIVISION.getConstant(), frequencyDivision);
        history.put(HISTORY_END_YEAR.getConstant(), historyEndYear);
        history.put(HISTORY_END_MONTH.getConstant(), historyEndMonth);
        history.put(HISTORY_END_PERIOD.getConstant(), historyEndPeriod);
        history.put(HISTORY_END_DAY.getConstant(), getEndDay(historyEndMonth, historyEndYear));
        return history;
    }

    public static Date convert2DigitTo4DigitYearFormat(final Date enteredDate) throws ParseException {
        Date temp;
        if (enteredDate == null || EMPTY.equals(enteredDate)) {
            temp = enteredDate;
        } else {
            LOGGER.info("entering convert2DigitTo4DigitYearFormat with P1:Date enteredDate" + enteredDate);
            final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
            final SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
            final Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, -14);
            sdf.set2DigitYearStart(cal.getTime());
            final String datesVal = sdf.format(enteredDate);
            temp = CommonUtils.convertStringToDate(fmt.format(sdf.parse(datesVal)));
            LOGGER.info("convert2DigitTo4DigitYearFormat return enteredDate" + enteredDate);
        }
        return temp;
    }

    public static Date convertStringToDate(final String strDate) throws ParseException {
        Date aDate;
        LOGGER.info("Entering convertStringToDate with P1:String strDate=" + strDate);
        if (strDate == null || strDate.equals(EMPTY) || strDate.equals(STRING_NULL)) {
            LOGGER.info("convertStringToDate return null");
            aDate = NULLOBJECT;
        } else {
            aDate = convertStringToDate(MMDDYYYY, strDate);
            LOGGER.info("convertStringToDate return aDate" + aDate);
        }

        return aDate;
    }

    public static Date convertStringToDate(final String aMask, final String strDate) throws ParseException {
        LOGGER.info("Entering convertStringToDate Started with p1:aMask =" + aMask + ", p2:strDate = " + strDate);
        final SimpleDateFormat dateFormat = new SimpleDateFormat(aMask);
        final Date date = dateFormat.parse(strDate);
        LOGGER.info("Return converted String Date " + date);
        return date;
    }

    public static CommonUtils getInstance() {
        if (object == null) {
            object = new CommonUtils();
        }
        return object;
    }

    public static String CollectionToString(Collection<?> collectionOfString, boolean toAddQuote) {
        return CollectionToString(collectionOfString, toAddQuote, false);
    }

    public static String CollectionToString(Collection<?> collectionOfString, boolean toAddQuote, boolean toRemoveSpace) {

        String framedString = "";
        if (collectionOfString != null && !collectionOfString.isEmpty()) {
            if (toAddQuote) {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", "'").replace("]", "'").replace(", ", "','");
            } else {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", "").replace("]", "");
            }

            if (toRemoveSpace) {
                framedString.replace(", ", "");
            }
        }
        return framedString;
    }

    public static String[] stringListToStringArray(List<String> stringList) {
        String[] stringArray = {};
        if (stringList != null) {
            stringArray = new String[stringList.size()];
            stringArray = stringList.toArray(stringArray);
        }
        return stringArray;
    }

    public static List<String> loadHistory(String frequency, String period, SessionDTO session) {
        LOGGER.info("Entering loadHistory method");
        List<String> history = new ArrayList<String>();
        history = session.getFrequencyAndQuaterValue(frequency);
        Integer endValue = 0;
        if (history == null || history.isEmpty()) {
            Map<String, Integer> historyEndDetails = getHistoryEndDetails(session, frequency);
            endValue = getProjections(session.getForecastDTO().getHistoryStartDate(), getDate(historyEndDetails.get(HISTORY_END_MONTH.getConstant()), historyEndDetails.get(HISTORY_END_YEAR.getConstant())), frequency);

            history = CommonUtils.getHistoryDdlbList(endValue, frequency, period);
            session.addFrequencyAndQuater(frequency, history);
        }
        LOGGER.info("End of loadHistory method");
        return history;
    }

    public static List getHistoryDdlbList(int endValue, String frequency, String period) {
        List history = new ArrayList();
        if (period.equals("Year")) {
            period = "Years";
        } else if ((period.equals(SEMI_ANNUAL.getConstant()))) {
            period = "Semi-Annual Periods";
        }
        for (int i = 1; i <= endValue; i++) {
            if ((i == 1)
                    && (QUARTERS.getConstant().equals(period) || MONTHS
                    .getConstant().equals(period) || "Years".equals(period) || "Semi-Annual Periods".equals(period))) {
                String freq = period.replace("s", "");
                history.add(String.valueOf(i) + " " + freq);
            } else {
                history.add(i + " " + period);
            }
        }
        return history;
    }
    
    public ComboBox loadComboBoxforstatusddlb(final ComboBox select,
            String listName, boolean isFilter) throws Exception {
        select.removeAllItems();
        final HelperDTO defaultValue = new HelperDTO(0, isFilter ? ConstantsUtil.SHOW_ALL : ConstantsUtil.SELECT_ONE);
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setItemCaptionPropertyId(ConstantsUtil.DESCRIPTION);
        select.setData(listName);
        List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        helperList.add(defaultValue);
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<HelperDTO>(HelperDTO.class);
        if (helperListUtil.getListNameMap().get(listName) != null) {
            helperList.addAll(helperListUtil.getListNameMap().get(listName));
}
       // helperList.remove(5);
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == null ? ConstantsUtil.SELECT_ONE : select.getValue()));
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtil.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription((String) (select.getValue() == null ? ConstantsUtil.SELECT_ONE : ((HelperDTO) select.getValue()).getDescription()));
            }
        });
        return select;
    }

    public ComboBox loadComboBoxforworkflowstatusddlb(final ComboBox select,
            String listName, boolean isFilter) throws Exception {
        select.removeAllItems();
        final HelperDTO defaultValue = new HelperDTO(0, isFilter ? ConstantsUtil.SHOW_ALL : ConstantsUtil.SELECT_ONE);
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setItemCaptionPropertyId(ConstantsUtil.DESCRIPTION);
        select.setData(listName);
        List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        helperList.add(defaultValue);
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<HelperDTO>(HelperDTO.class);
        if (helperListUtil.getListNameMap().get(listName) != null) {
            helperList.addAll(helperListUtil.getListNameMap().get(listName));
        }
       // helperList.remove(5);
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == null ? ConstantsUtil.SELECT_ONE : select.getValue()));
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtil.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription((String) (select.getValue() == null ? ConstantsUtil.SELECT_ONE : ((HelperDTO) select.getValue()).getDescription()));
            }
        });
        return select;
    }
}
