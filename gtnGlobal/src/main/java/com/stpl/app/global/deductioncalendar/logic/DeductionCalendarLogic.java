/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.logic;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.dao.impl.DeductionCalendarDaoImpl;
import com.stpl.app.global.deductioncalendar.dto.DeductionCalendarDTO;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.model.DeductionCalendarMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.service.DeductionCalendarMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.NotesTabLogic;
import com.stpl.app.util.xmlparser.SQLUtil;
import com.stpl.domain.global.DeductionCalendar.DeductionCalendarDao;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CommonUtil;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 *
 * @author sibi
 */
public class DeductionCalendarLogic {

    private static final Logger LOGGER = Logger.getLogger(DeductionCalendarLogic.class);
    private final HelperListUtil helperListUtil = HelperListUtil.getInstance();
    static HashMap<String, String> criteria = new HashMap<String, String>();
    NotesTabLogic notesLogic = new NotesTabLogic();

    public static CustomTableHeaderDTO getCalculatedSalesAllocationRight(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("2013", "2013", String.class);
        fullHeaderDTO.addSingleColumn("2013", "2013", String.class);
        tableHeaderDTO.addSingleColumn("2014", "2014", String.class);
        fullHeaderDTO.addSingleColumn("2014", "2014", String.class);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getCalculatedSalesAllocationLeft(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("check", " ", Boolean.class);
        fullHeaderDTO.addSingleColumn("check", " ", Boolean.class);
        tableHeaderDTO.addSingleColumn("group", "Level Name", String.class);
        fullHeaderDTO.addSingleColumn("group", "Level Name", String.class);
        return tableHeaderDTO;
    }

    public String saveDeductionCalendarMaster(final DeductionCalendarDTO deductionCalendarForm, final SessionDTO sessionDTO, final List<NotesDTO> availableUploadedInformation, final String addedNotes, final List<NotesDTO> removeDetailsList) {
        try {
            DeductionCalendarDao dao = new DeductionCalendarDaoImpl();
            DeductionCalendarMaster deductionCalendarMaster;
            DeductionCalendarMaster result;
            final Date createdDate = new Date();

            if (sessionDTO.getSystemId() == 0 || sessionDTO.getMode().equals(ConstantsUtils.COPY)) {
                deductionCalendarMaster = DeductionCalendarMasterLocalServiceUtil.createDeductionCalendarMaster(0);
                deductionCalendarMaster.setCreatedBy(Integer.parseInt(sessionDTO.getUserId()));
                deductionCalendarMaster.setCreatedDate(createdDate);
                deductionCalendarMaster.setModifiedDate(createdDate);
                deductionCalendarMaster.setModifiedBy(Integer.parseInt(sessionDTO.getUserId()));
                deductionCalendarMaster.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
            } else {
                deductionCalendarMaster = DeductionCalendarMasterLocalServiceUtil.getDeductionCalendarMaster(sessionDTO.getSystemId());
                deductionCalendarMaster.setInboundStatus(ConstantsUtils.INBOUND_STATUS_C);
                deductionCalendarMaster.setCreatedBy(Integer.parseInt(sessionDTO.getUserId()));
                deductionCalendarMaster.setCreatedDate(createdDate);
                deductionCalendarMaster.setModifiedDate(createdDate);
                deductionCalendarMaster.setModifiedBy(Integer.parseInt(sessionDTO.getUserId()));
            }
            if (deductionCalendarForm.getDeductionCalendarDesc() != null && !StringUtils.EMPTY.equals(deductionCalendarForm.getDeductionCalendarDesc())
                    && !"null".equals(deductionCalendarForm.getDeductionCalendarDesc())) {
                deductionCalendarMaster.setDeductionCalendarDesc(deductionCalendarForm.getDeductionCalendarDesc());
            }
            deductionCalendarMaster.setDeductionCalendarNo(deductionCalendarForm.getDeductionCalendarNo());
            deductionCalendarMaster.setDeductionCalendarName(deductionCalendarForm.getDeductionCalendarName());
            deductionCalendarMaster.setCategory(deductionCalendarForm.getCategoryDdlb() == null ? 0 : deductionCalendarForm.getCategoryDdlb().getId());
            deductionCalendarMaster.setAdditionalNotes(addedNotes);
            deductionCalendarMaster.setUserId(Integer.parseInt(sessionDTO.getUserId()));

            if (sessionDTO.getSystemId() == 0 || sessionDTO.getMode().equals(ConstantsUtils.COPY)) {
                result = dao.saveDeductionCalendarMaster(deductionCalendarMaster);
                if (sessionDTO.getMode().equals(ConstantsUtils.COPY)) {
                    sessionDTO.setSystemId(result.getDeductionCalendarMasterSid());
                }
            } else {
                result = DeductionCalendarMasterLocalServiceUtil.updateDeductionCalendarMaster(deductionCalendarMaster);
            }
            if (!removeDetailsList.isEmpty()) {
                for (NotesDTO dtoValue : removeDetailsList) {
                    if (dtoValue.getDocDetailsId() != 0) {
                        notesLogic.deleteUploadedFile(dtoValue.getDocDetailsId(), dtoValue.getDocumentFullPath());
                    }
                }
            }
            notesLogic.saveUploadedInformation(availableUploadedInformation, "DEDUCTION_CALENDAR_MASTER", result.getDeductionCalendarMasterSid());
            sessionDTO.setSystemId(result.getDeductionCalendarMasterSid());
            sessionDTO.setAdditionalNotes(deductionCalendarMaster.getAdditionalNotes());
            return ConstantsUtils.SUCCESS;
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (PortalException ex) {
            LOGGER.error(ex);
        }
        return ConstantsUtils.FAIL;
    }

    public Boolean deductionNoAndNameDuplicateCheck(final String value, final Boolean isName, SessionDTO sessionDTO) {
        StringBuilder queryString = new StringBuilder("SELECT COUNT(DEDUCTION_CALENDAR_NO) FROM DEDUCTION_CALENDAR_MASTER WHERE ");
        queryString.append(isName ? "DEDUCTION_CALENDAR_NAME" : "DEDUCTION_CALENDAR_NO");
        queryString.append("='").append(value).append("'");
        if (ConstantsUtils.EDIT.equalsIgnoreCase(sessionDTO.getMode())) {
            queryString.append(" AND DEDUCTION_CALENDAR_MASTER_SID<>'").append(sessionDTO.getSystemId()).append("'");
        }
        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryString.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        if (list != null && !list.isEmpty()) {
            return (Integer) list.get(0) != 0;
        }
        return false;
    }

    public Boolean itemAndCompanySelectionCheck(final SessionDTO sessionDTO, final Boolean isItem) {
        String query = (isItem ? CustomSQLUtil.get("deduction-item-count") : CustomSQLUtil.get("deduction-company-count"))
                .replace("?UID", sessionDTO.getUserId())
                .replace("?SID", "'" + sessionDTO.getUiSessionId() + "'");
        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(query, StringUtils.EMPTY, StringUtils.EMPTY);
        if (list != null && !list.isEmpty()) {
            return (Integer) list.get(0) != 0;
        }
        return false;
    }

    /**
     * Method to get the count of the rebate schedule search.
     *
     * @param rebateSchForm
     * @param start
     * @param offset
     * @param columns
     * @param fieldFlag
     * @param criteria
     * @param isCount
     * @return
     * @throws SystemException
     * @throws Exception
     */
    public Object getCountAndResultsForDeductionCalendar(final ErrorfulFieldGroup rebateSchForm, int start, int offset, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, boolean isCount)
            throws SystemException {
        LOGGER.debug("In getSearchCount P1:rebateScheduleForm");
        Map<String, Object> filterCriteria = new HashMap<String, Object>();
        Map<String, String> searchCriteria = new HashMap<String, String>();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        searchCriteria.put("deductionCalendarno", checkEmptyDataFromFields(ConstantsUtils.TEXT1, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.TEXT1).getValue().toString()).trim());
        searchCriteria.put("deductionCalendarname", checkEmptyDataFromFields(ConstantsUtils.TEXT2, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.TEXT2).getValue().toString()).trim());
        searchCriteria.put("deductionCalendardesc", checkEmptyDataFromFields(ConstantsUtils.TEXT3, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.TEXT3).getValue().toString()).trim());
        searchCriteria.put("category", checkEmptyDataFromFields(ConstantsUtils.COMBO1, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((com.stpl.app.util.HelperDTO) rebateSchForm.getField(ConstantsUtils.COMBO1).getValue()).getId()));

        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = CommonUtil.buildFilterCriteria(stringFilter.getFilterString());
                    filterCriteria.put(String.valueOf(stringFilter.getPropertyId()), filterString);

                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    Date filterString = (Date) stringFilter.getStartValue();
                    Date filterString1 = (Date) stringFilter.getEndValue();
                    filterCriteria.put(String.valueOf(stringFilter.getPropertyId()) + ConstantsUtils.START, format.format(filterString));
                    filterCriteria.put(String.valueOf(stringFilter.getPropertyId()) + ConstantsUtils.END, format.format(filterString1));
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    if (stringFilter.getValue() instanceof Integer && stringFilter.getOperation().equals(stringFilter.getOperation().EQUAL) && ((Integer) stringFilter.getValue()) != 0) {
                                     int value = (Integer) stringFilter.getValue();
                                filterCriteria.put(String.valueOf(stringFilter.getPropertyId()), value);
                    }
                    if (stringFilter.getValue() instanceof Date) {
                        Compare.Operation operation = stringFilter.getOperation();
                        Date value = (Date) stringFilter.getValue();
                        if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            filterCriteria.put(String.valueOf(stringFilter.getPropertyId()) + ConstantsUtils.START, format.format(value));
                        } else {
                            filterCriteria.put(String.valueOf(stringFilter.getPropertyId()) + ConstantsUtils.END, format.format(value));
                        }
                    }
                }
            }
        }
        String column = "DSM.DEDUCTION_CALENDAR_MASTER_SID";
        String orderBy = "ASC";
        if (columns != null) {
            for (final Iterator<SortByColumn> iterator = columns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                String columnName = sortByColumn.getName();

                if ("deductionCalendarno".equals(columnName)) {
                    column = "DSM.DEDUCTION_CALENDAR_NO";
                } else if ("deductionCalendarname".equals(columnName)) {
                    column = "DSM.DEDUCTION_CALENDAR_NAME";
                } else if ("deductionCalendardesc".equals(columnName)) {
                    column = "DSM.DEDUCTION_CALENDAR_DESC";
                } else if ("category".equals(columnName)) {
                    column = "category";
                } else if ("dcCreationDate".equals(columnName)) {
                    column = "DSM.CREATED_DATE";
                } else if ("dcModifiedDate".equals(columnName)) {
                    column = "DSM.MODIFIED_DATE";
                }

                if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                    orderBy = "ASC";
                } else {
                    orderBy = "DESC";
                }
            }
        }
        String query = getDeductionCalendarQuery(searchCriteria, start, offset, column, orderBy, filterCriteria, isCount);
        List resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);

        if (isCount) {
            int count = (Integer) resultList.get(0);
            return count;
        } else {

            return getCustomizedDeductionResults(resultList);

        }

    }

    public String getDeductionCalendarQuery(Map<String, String> searchCriteria, int start, int offset, String column, String orderBy, Map<String, Object> parameters, boolean isCount) {

        if (orderBy == null) {
            orderBy = "ASC";
        }
        String sql = "";

        if (isCount) {
            sql += "SELECT COUNT(*)";
        } else {
            sql += "SELECT DSM.DEDUCTION_CALENDAR_MASTER_SID,DSM.DEDUCTION_CALENDAR_NO,DSM.DEDUCTION_CALENDAR_NAME,DSM.DEDUCTION_CALENDAR_DESC,"
                    + "catHelper.DESCRIPTION as category,DSM.CREATED_BY AS CREATED,DSM.CREATED_DATE,DSM.MODIFIED_BY AS MODIFIED,DSM.MODIFIED_DATE";
        }
        sql += " FROM DEDUCTION_CALENDAR_MASTER DSM LEFT JOIN HELPER_TABLE catHelper on catHelper.HELPER_TABLE_SID=DSM.CATEGORY WHERE DSM.INBOUND_STATUS <> 'D'";

        if (StringUtils.isNotBlank(searchCriteria.get("deductionCalendarno")) && StringUtils.isNotEmpty(searchCriteria.get("deductionCalendarno"))) {
                sql += " AND DSM.DEDUCTION_CALENDAR_NO LIKE '" + searchCriteria.get("deductionCalendarno") + "'";
            }

        if (StringUtils.isNotBlank(searchCriteria.get("deductionCalendarname")) && StringUtils.isNotEmpty(searchCriteria.get("deductionCalendarname"))) {
                sql += " AND DSM.DEDUCTION_CALENDAR_NAME LIKE '" + searchCriteria.get("deductionCalendarname") + "'";

        }
        if (StringUtils.isNotBlank(searchCriteria.get("deductionCalendardesc")) && StringUtils.isNotEmpty(searchCriteria.get("deductionCalendardesc"))) {
                sql += " AND DSM.DEDUCTION_CALENDAR_DESC LIKE '" + searchCriteria.get("deductionCalendardesc") + "'";
            
        }
        if (searchCriteria.get("category") != null && Integer.valueOf(searchCriteria.get("category")) != 0) {
            sql += " AND DSM.CATEGORY = " + Integer.valueOf(searchCriteria.get("category"));

        }

        if (parameters.get("deductionCalendarno") != null) {
            sql += " AND DSM.DEDUCTION_CALENDAR_NO like '";
            sql += String.valueOf(parameters.get("deductionCalendarno")) + "' ";
        }

        if (parameters.get("deductionCalendarname") != null) {
            sql += " AND DSM.DEDUCTION_CALENDAR_NAME like '";
            sql += String.valueOf(parameters.get("deductionCalendarname")) + "' ";
        }

        if (parameters.get("deductionCalendardesc") != null) {
            sql += " AND DSM.DEDUCTION_CALENDAR_DESC like '";
            sql += String.valueOf(parameters.get("deductionCalendardesc")) + "' ";
        }

        if (parameters.get("category") != null) {
            sql += " AND category like '";
            sql += String.valueOf(parameters.get("category")) + "' ";
        }
        if (parameters.get("dcCreatedBy") != null) {
            sql += " AND DSM.CREATED_BY = '";
            sql += String.valueOf(parameters.get("dcCreatedBy")).replace(ConstantsUtils.PERCENCTAGE, StringUtils.EMPTY) + "' ";
        }
        if (parameters.get("dcModifiedBy") != null) {
            sql += " AND DSM.MODIFIED_BY ='";
            sql += String.valueOf(parameters.get("dcModifiedBy")).replace(ConstantsUtils.PERCENCTAGE, StringUtils.EMPTY) + "' ";
        }

        if (parameters.get("dcCreationDatestart") != null && parameters.get("dcCreationDateend") != null) {
            String from = String.valueOf(parameters.get("dcCreationDatestart"));
            String to = String.valueOf(parameters.get("dcCreationDateend"));
            sql += " AND DSM.CREATED_DATE BETWEEN '" + from + "' AND '" + to + "'";
        }

        if (parameters.get("dcModifiedDatestart") != null && parameters.get("dcModifiedDateend") != null) {
            String from = String.valueOf(parameters.get("dcModifiedDatestart"));
            String to = String.valueOf(parameters.get("dcModifiedDateend"));
            sql += " AND DSM.MODIFIED_DATE BETWEEN '" + from + "' AND '" + to + "'";
        }

        if (!isCount) {
            sql += " ORDER BY " + column + " " + orderBy + " OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
        }
        return sql;

    }

    /**
     *
     * @param fieldName
     * @param binder
     * @return
     */
    private boolean checkEmptyDataFromFields(String fieldName, ErrorfulFieldGroup binder) {
        boolean isEmpty = false;

        if (binder.getField(fieldName) instanceof TextField) {
            TextField textField = (TextField) binder.getField(fieldName);
            isEmpty = StringUtils.isBlank(textField.getValue()) || ConstantsUtils.NULL.equals(textField.getValue());
        }
        if (binder.getField(fieldName) instanceof ComboBox) {

            ComboBox comboBox = (ComboBox) binder.getField(fieldName);
            if (comboBox.getValue() instanceof com.stpl.app.util.HelperDTO) {
                isEmpty = comboBox.getValue() == null || ConstantsUtils.SELECT_ONE.equals(((com.stpl.app.util.HelperDTO) comboBox.getValue()).getDescription());
            } else if (comboBox.getValue() instanceof HelperDTO) {
                isEmpty = comboBox.getValue() == null || ConstantsUtils.SELECT_ONE.equals(((HelperDTO) comboBox.getValue()).getDescription());
            } else if (comboBox.getValue() == null) {
                isEmpty = true;
            }

        }
        return isEmpty;
    }

    public List<SearchResultsDTO> getCustomizedDeductionResults(List list) {
        List<SearchResultsDTO> resultList = new ArrayList<SearchResultsDTO>();
        try {
            Map<Integer, String> userMap = StplSecurity.getUserName();
            SearchResultsDTO record;
            try {
                for (Object obj : list) {
                    Object[] result = (Object[]) obj;
                    record = new SearchResultsDTO();
                    record.setSystemID(String.valueOf(result[0]));
                    record.setDeductionCalendarSid(Integer.valueOf(String.valueOf(result[0])));
                    record.setDeductionCalendarno(String.valueOf(result[1]));
                    record.setDeductionCalendarname(String.valueOf(result[NumericConstants.TWO]));
                    record.setDeductionCalendardesc(String.valueOf(result[NumericConstants.THREE]));
                    if (result[NumericConstants.FOUR] != null) {
                        record.setCategory((ConstantsUtils.SELECT_ONE).equals(String.valueOf(result[NumericConstants.FOUR])) ? StringUtils.EMPTY : String.valueOf(result[NumericConstants.FOUR]));
                    } else {
                        record.setCategory(String.valueOf(result[NumericConstants.FOUR]));
                    }
                    record.setDcCreatedBy(userMap.get(Integer.valueOf(String.valueOf(result[NumericConstants.FIVE]))));
                    if(result[NumericConstants.SIX]!=null) {
                        record.setDcCreationDate((Date) result[NumericConstants.SIX]);
                    }                    
                    record.setDcModifiedBy(userMap.get(Integer.valueOf(String.valueOf(result[NumericConstants.SEVEN]))));
                    if(result[NumericConstants.EIGHT]!=null) {
                        record.setDcModifiedDate((Date) result[NumericConstants.EIGHT]);
                    } 
                    resultList.add(record);
                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return resultList;
        }
    }

    private String replaceForWildCardSearch(String input) {
        if (StringUtils.isNotBlank(input)) {
            input = input.replace(GlobalConstants.getPercent(), GlobalConstants.getPercentForEscape());
            input = input.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        return input;
    }

    public void insertToTempSelectionForCust(String userId, String sessionId, int detailMasterSid) {
        String query = CustomSQLUtil.get("insertToTempForCust");
        query = query.replace("?UID", userId);
        query = query.replace("?SID", "'" + sessionId + "'");
        query = query.replace("?DCID", String.valueOf(detailMasterSid));
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public void insertToTempSelectionForProd(String userId, String sessionId, int detailMasterSid) {
        String query = CustomSQLUtil.get("insertToTempForProd");
        query = query.replace("?UID", userId);
        query = query.replace("?SID", "'" + sessionId + "'");
        query = query.replace("?DCID", String.valueOf(detailMasterSid));
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public void deleteTempDeductionDetails(SessionDTO session) {
        String query = SQLUtil.getQuery("deleteDeductionDetails");
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query,session.getCurrentTableNames()));
    }

    public void deleteCustomer_TempDeductionDetails(SessionDTO session) {
        String query = CustomSQLUtil.get("deleteCustomer_Selection_Table");
        query = query.replace(ConstantsUtils.USERID_AT, session.getUserId());
        query = query.replace(ConstantsUtils.SESSION_ID_AT, session.getUiSessionId());
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public void deleteItem_TempDeductionDetails(SessionDTO session) {
        String query = CustomSQLUtil.get("deleteItem_Selection_Table");
        query = query.replace(ConstantsUtils.USERID_AT, session.getUserId());
        query = query.replace(ConstantsUtils.SESSION_ID_AT, session.getUiSessionId());
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public void deleteTempSeletionTable(SessionDTO session) {
        String query = CustomSQLUtil.get("deleteSelectionTable");
        query = query.replace(ConstantsUtils.USERID_AT, session.getUserId());
        query = query.replace(ConstantsUtils.SESSION_ID_AT, session.getUiSessionId());
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public void sameToTemp(SessionDTO session, int detailSid) {
        String query = SQLUtil.getQuery("insertToTempForDetails");
        query = query.replace("?DCID", String.valueOf(detailSid));
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query,session.getCurrentTableNames()));
    }

    public DeductionCalendarDTO getDeductionCalendarById(int masterSid, DeductionCalendarDTO dto) {
        LOGGER.debug("Entering getDeductionCalendarById with " + masterSid);
        try {
            Map<Integer, HelperDTO> idHelperDTOMap = helperListUtil.getIdHelperDTOMap();
            DeductionCalendarMaster deductionCalendarMaster = DeductionCalendarMasterLocalServiceUtil.getDeductionCalendarMaster(masterSid);
            dto.setDeductionCalendarNo(deductionCalendarMaster.getDeductionCalendarNo());
            dto.setDeductionCalendarName(deductionCalendarMaster.getDeductionCalendarName());
            dto.setDeductionCalendarDesc(deductionCalendarMaster.getDeductionCalendarDesc());
            dto.setCategoryDdlb(idHelperDTOMap.get(deductionCalendarMaster.getCategory()));
            dto.setInternalNotes(deductionCalendarMaster.getAdditionalNotes());
            dto.setMasterTableSid(masterSid);
            return dto;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    public String parseDateLogic(Object object) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String date = formatter.format(object);
        return date;
    }

    public void saveDeductionDetails(SessionDTO sessionDTO) {
        String query = SQLUtil.getQuery(("Add".equals(sessionDTO.getMode()) || "Copy".equals(sessionDTO.getMode())) ? "savedeductiondetails" : "updatedeductiondetails");
        query = query.replace("?DCID", String.valueOf(sessionDTO.getSystemId()));
        query = query.replace("?UID", sessionDTO.getUserId());
        query = query.replace("?SID", "'" + sessionDTO.getUiSessionId() + "'");
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query,sessionDTO.getCurrentTableNames()));
    }
    
    public DeductionCalendarDTO getDeductionCalendarByIdForCopy(int masterSid, DeductionCalendarDTO dto) {
        LOGGER.debug("Entering getDeductionCalendarByIdForCopy with " + masterSid);
        try {
            Map<Integer, HelperDTO> idHelperDTOMap = helperListUtil.getIdHelperDTOMap();
            DeductionCalendarMaster deductionCalendarMaster = DeductionCalendarMasterLocalServiceUtil.getDeductionCalendarMaster(masterSid);
            dto.setDeductionCalendarDesc(deductionCalendarMaster.getDeductionCalendarDesc());
            dto.setCategoryDdlb(idHelperDTOMap.get(deductionCalendarMaster.getCategory()));
            LOGGER.debug("getDeductionCalendarByIdForCopy ends");
            return dto;
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex);
            return dto;
        }
    }
}
