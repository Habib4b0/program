/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.logic;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.dao.CommonDao;
import com.stpl.app.global.dao.impl.CommonDaoImpl;
import com.stpl.app.global.dao.impl.DeductionCalendarDaoImpl;
import com.stpl.app.global.deductioncalendar.dto.DeductionCalendarDTO;
import com.stpl.app.model.DeductionCalendarMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.service.DeductionCalendarMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.Constants;
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
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ItemQualifierLocalServiceUtil;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.Between;
import com.vaadin.v7.data.util.filter.Compare;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.TextField;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sibi
 */
public class DeductionCalendarLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeductionCalendarLogic.class);
    
    private final HelperListUtil helperListUtil = HelperListUtil.getInstance();
    private final NotesTabLogic notesLogic = new NotesTabLogic();
    public static final CommonDao DAO = CommonDaoImpl.getInstance();
    private static int itemQualifierNameCount = 0;

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
            notesLogic.saveUploadedAttachInformation(availableUploadedInformation, "DEDUCTION_CALENDAR_MASTER", result.getDeductionCalendarMasterSid());
            sessionDTO.setSystemId(result.getDeductionCalendarMasterSid());
            sessionDTO.setAdditionalNotes(deductionCalendarMaster.getAdditionalNotes());
            return ConstantsUtils.SUCCESS;
        } catch (SystemException | PortalException ex) {
            LOGGER.error(ex.getMessage());
        }
        return ConstantsUtils.FAIL;
    }

    public Boolean deductionNoAndNameDuplicateCheck(final String value, final Boolean isName, SessionDTO sessionDTO) {
        StringBuilder queryString = new StringBuilder("SELECT COUNT(DEDUCTION_CALENDAR_NO) FROM DEDUCTION_CALENDAR_MASTER WHERE ");
        queryString.append(isName ? "DEDUCTION_CALENDAR_NAME" : "DEDUCTION_CALENDAR_NO");
        queryString.append("='").append(value).append('\'');
        if (ConstantsUtils.EDIT.equalsIgnoreCase(sessionDTO.getMode())) {
            queryString.append(" AND DEDUCTION_CALENDAR_MASTER_SID<>'").append(sessionDTO.getSystemId()).append('\'');
        }
        final List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());
        if (list != null && !list.isEmpty()) {
            return (Integer) list.get(0) != 0;
        }
        return BooleanConstant.getFalseFlag();
    }

    public Boolean itemAndCompanySelectionCheck(final SessionDTO sessionDTO, final Boolean isItem) {
        String query = (isItem ? SQLUtil.getQuery("deduction-item-count") : SQLUtil.getQuery("deduction-company-count"))
                .replace("?UID", sessionDTO.getUserId())
                .replace("?SID", "'" + sessionDTO.getUiSessionId() + "'");
        final List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        if (list != null && !list.isEmpty()) {
            return (Integer) list.get(0) != 0;
        }
        return BooleanConstant.getFalseFlag();
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
        Map<String, Object> filterCriteria = new HashMap<>();
        Map<String, String> searchCriteria = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        searchCriteria.put(ConstantsUtils.DEDUCTION_CAL_NO, checkEmptyDataFromFields(ConstantsUtils.TEXT1, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.TEXT1).getValue().toString()).trim());
        searchCriteria.put(ConstantsUtils.DEDUCTION_CAL_NAME, checkEmptyDataFromFields(ConstantsUtils.TEXT2, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.TEXT2).getValue().toString()).trim());
        searchCriteria.put(ConstantsUtils.DEDUCTION_CAL_DESC, checkEmptyDataFromFields(ConstantsUtils.TEXT3, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.TEXT3).getValue().toString()).trim());
        searchCriteria.put(ConstantsUtils.CATEGORY, checkEmptyDataFromFields(ConstantsUtils.COMBO1, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((com.stpl.app.util.HelperDTO) rebateSchForm.getField(ConstantsUtils.COMBO1).getValue()).getId()));

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
                        Compare.Operation operation = stringFilter.getOperation();
                        Date value = (Date) stringFilter.getValue();
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            filterCriteria.put(String.valueOf(stringFilter.getPropertyId()) + ConstantsUtils.START, format.format(value));
                        } else {
                            filterCriteria.put(String.valueOf(stringFilter.getPropertyId()) + ConstantsUtils.END, format.format(value));
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

                if (ConstantsUtils.DEDUCTION_CAL_NO.equals(columnName)) {
                    column = "DSM.DEDUCTION_CALENDAR_NO";
                } else if (ConstantsUtils.DEDUCTION_CAL_NAME.equals(columnName)) {
                    column = "DSM.DEDUCTION_CALENDAR_NAME";
                } else if (ConstantsUtils.DEDUCTION_CAL_DESC.equals(columnName)) {
                    column = "DSM.DEDUCTION_CALENDAR_DESC";
                } else if (ConstantsUtils.CATEGORY.equals(columnName)) {
                    column = ConstantsUtils.CATEGORY;
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
            return (Integer) resultList.get(0);
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

        if (StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.DEDUCTION_CAL_NO)) && StringUtils.isNotEmpty(searchCriteria.get(ConstantsUtils.DEDUCTION_CAL_NO))) {
                sql += " AND DSM.DEDUCTION_CALENDAR_NO LIKE '" + searchCriteria.get(ConstantsUtils.DEDUCTION_CAL_NO) + "'";
            }

        if (StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.DEDUCTION_CAL_NAME)) && StringUtils.isNotEmpty(searchCriteria.get(ConstantsUtils.DEDUCTION_CAL_NAME))) {
                sql += " AND DSM.DEDUCTION_CALENDAR_NAME LIKE '" + searchCriteria.get(ConstantsUtils.DEDUCTION_CAL_NAME) + "'";

        }
        if (StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.DEDUCTION_CAL_DESC)) && StringUtils.isNotEmpty(searchCriteria.get(ConstantsUtils.DEDUCTION_CAL_DESC))) {
                sql += " AND DSM.DEDUCTION_CALENDAR_DESC LIKE '" + searchCriteria.get(ConstantsUtils.DEDUCTION_CAL_DESC) + "'";
            
        }
        if (searchCriteria.get(ConstantsUtils.CATEGORY) != null && Integer.parseInt(searchCriteria.get(ConstantsUtils.CATEGORY)) != 0) {
            sql += " AND DSM.CATEGORY = " + Integer.valueOf(searchCriteria.get(ConstantsUtils.CATEGORY));

        }

        if (parameters.get(ConstantsUtils.DEDUCTION_CAL_NO) != null) {
            sql += " AND DSM.DEDUCTION_CALENDAR_NO like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.DEDUCTION_CAL_NO)) + "' ";
        }

        if (parameters.get(ConstantsUtils.DEDUCTION_CAL_NAME) != null) {
            sql += " AND DSM.DEDUCTION_CALENDAR_NAME like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.DEDUCTION_CAL_NAME)) + "' ";
        }

        if (parameters.get(ConstantsUtils.DEDUCTION_CAL_DESC) != null) {
            sql += " AND DSM.DEDUCTION_CALENDAR_DESC like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.DEDUCTION_CAL_DESC)) + "' ";
        }

        if (parameters.get(ConstantsUtils.CATEGORY) != null) {
            sql += " AND category like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.CATEGORY)) + "' ";
        }
        if (parameters.get("dcCreatedBy") != null) {
            sql += " AND DSM.CREATED_BY = '";
            sql += String.valueOf(parameters.get("dcCreatedBy")).replace(ConstantsUtils.PERCENCTAGE, StringUtils.EMPTY) + "' ";
        }
        if (parameters.get("dcModifiedBy") != null) {
            sql += " AND DSM.MODIFIED_BY ='";
            sql += String.valueOf(parameters.get("dcModifiedBy")).replace(ConstantsUtils.PERCENCTAGE, StringUtils.EMPTY) + "' ";
        }

        if (parameters.get(ConstantsUtils.DC_CREATION_DATE_START) != null && parameters.get(ConstantsUtils.DC_CREATION_DATE_END) != null) {
            String from = String.valueOf(parameters.get(ConstantsUtils.DC_CREATION_DATE_START));
            String to = String.valueOf(parameters.get(ConstantsUtils.DC_CREATION_DATE_END));
            sql += " AND DSM.CREATED_DATE BETWEEN '" + from + "' AND '" + to + "'";
        }
        if (parameters.get(ConstantsUtils.DC_CREATION_DATE_START) != null && parameters.get(ConstantsUtils.DC_CREATION_DATE_END) == null) {
            String from = String.valueOf(parameters.get(ConstantsUtils.DC_CREATION_DATE_START));
            sql += " AND DSM.CREATED_DATE >= '" + from + "' ";
        }
        if (parameters.get(ConstantsUtils.DC_CREATION_DATE_START) == null && parameters.get(ConstantsUtils.DC_CREATION_DATE_END) != null) {
            String to = String.valueOf(parameters.get(ConstantsUtils.DC_CREATION_DATE_END));
            sql += " AND DSM.CREATED_DATE <= '" + to + "'";
        }

        if (parameters.get(ConstantsUtils.DC_MODIFIED_DATE_START) != null && parameters.get(ConstantsUtils.DC_MODIFIED_DATE_END) != null) {
            String from = String.valueOf(parameters.get(ConstantsUtils.DC_MODIFIED_DATE_START));
            String to = String.valueOf(parameters.get(ConstantsUtils.DC_MODIFIED_DATE_END));
            sql += " AND DSM.MODIFIED_DATE BETWEEN '" + from + "' AND '" + to + "'";
        }
        if (parameters.get(ConstantsUtils.DC_MODIFIED_DATE_START) != null && parameters.get(ConstantsUtils.DC_MODIFIED_DATE_END) == null) {
            String from = String.valueOf(parameters.get(ConstantsUtils.DC_MODIFIED_DATE_START));
            sql += " AND DSM.MODIFIED_DATE >= '" + from + "'";
        }
        if (parameters.get(ConstantsUtils.DC_MODIFIED_DATE_START) == null && parameters.get(ConstantsUtils.DC_MODIFIED_DATE_END) != null) {
            String to = String.valueOf(parameters.get(ConstantsUtils.DC_MODIFIED_DATE_END));
            sql += " AND DSM.MODIFIED_DATE <= '" + to + "'";
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
        List<SearchResultsDTO> resultList = new ArrayList<>();
        try {
            Map<Integer, String> userMap = StplSecurity.getUserName();
            SearchResultsDTO record;
           
                for (Object obj : list) {
                    Object[] result = (Object[]) obj;
                    record = new SearchResultsDTO();
                    record.setSystemID(String.valueOf(result[0]));
                    record.setDeductionCalendarSid(Integer.parseInt(String.valueOf(result[0])));
                    record.setDeductionCalendarno(String.valueOf(result[1]));
                    record.setDeductionCalendarname(String.valueOf(result[NumericConstants.TWO]));
                   if(result[NumericConstants.THREE]!=null)
                   {
                    record.setDeductionCalendardesc(String.valueOf(result[NumericConstants.THREE]));
                   }
                   else{
                	   record.setDeductionCalendardesc(StringUtils.EMPTY );
                   }
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
           
            return resultList;
        } catch (SystemException | NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
            return resultList;
        }
    }

    private String replaceForWildCardSearch(String input) {
        if (StringUtils.isNotBlank(input)) {
            input = input.replace(GlobalConstants.getPercent(), GlobalConstants.getPercentForEscape());
            input = input.replace(CommonUIUtils.CHAR_ASTERISK, CommonUIUtils.CHAR_PERCENT);
        }
        return input;
    }

    public void insertToTempSelectionForCust(String userId, String sessionId, int detailMasterSid) {
        String query = SQLUtil.getQuery("insertToTempForCust");
        query = query.replace("?UID", userId);
        query = query.replace("?SID", "'" + sessionId + "'");
        query = query.replace(ConstantsUtils.DCID_QUESTION, String.valueOf(detailMasterSid));
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public void insertToTempSelectionForProd(String userId, String sessionId, int detailMasterSid) {
        String query = SQLUtil.getQuery("insertToTempForProd");
        query = query.replace("?UID", userId);
        query = query.replace("?SID", "'" + sessionId + "'");
        query = query.replace(ConstantsUtils.DCID_QUESTION, String.valueOf(detailMasterSid));
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public void deleteTempDeductionDetails(SessionDTO session) {
        String query = SQLUtil.getQuery("deleteDeductionDetails");
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query,session.getCurrentTableNames()));
    }

    public void deleteCustomer_TempDeductionDetails(SessionDTO session) {
        String query = SQLUtil.getQuery("deleteCustomer_Selection_Table");
        query = query.replace(ConstantsUtils.USERID_AT, session.getUserId());
        query = query.replace(ConstantsUtils.SESSION_ID_AT, session.getUiSessionId());
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public void deleteItem_TempDeductionDetails(SessionDTO session) {
        String query = SQLUtil.getQuery("deleteItem_Selection_Table");
        query = query.replace(ConstantsUtils.USERID_AT, session.getUserId());
        query = query.replace(ConstantsUtils.SESSION_ID_AT, session.getUiSessionId());
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public void deleteTempSeletionTable(SessionDTO session) {
        String query = SQLUtil.getQuery("deleteSelectionTable");
        query = query.replace(ConstantsUtils.USERID_AT, session.getUserId());
        query = query.replace(ConstantsUtils.SESSION_ID_AT, session.getUiSessionId());
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public void sameToTemp(SessionDTO session, int detailSid) {
        String query = SQLUtil.getQuery("insertToTempForDetails");
        query = query.replace(ConstantsUtils.DCID_QUESTION, String.valueOf(detailSid));
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query,session.getCurrentTableNames()));
    }

    public DeductionCalendarDTO getDeductionCalendarById(int masterSid, DeductionCalendarDTO dto) {
        LOGGER.debug("Entering getDeductionCalendarById with {}" , masterSid);
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
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }
    }

    public String parseDateLogic(Object object) {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(object);
    }

    public void saveDeductionDetails(SessionDTO sessionDTO) {
        String query = SQLUtil.getQuery(("Add".equals(sessionDTO.getMode()) || "Copy".equals(sessionDTO.getMode())) ? "savedeductiondetails" : "updatedeductiondetails");
        query = query.replace(ConstantsUtils.DCID_QUESTION, String.valueOf(sessionDTO.getSystemId()));
        query = query.replace("?UID", sessionDTO.getUserId());
        query = query.replace("?SID", "'" + sessionDTO.getUiSessionId() + "'");
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query,sessionDTO.getCurrentTableNames()));
    }
    
    public DeductionCalendarDTO getDeductionCalendarByIdForCopy(int masterSid, DeductionCalendarDTO dto) {
        LOGGER.debug("Entering getDeductionCalendarByIdForCopy with {}" , masterSid);
        try {
            Map<Integer, HelperDTO> idHelperDTOMap = helperListUtil.getIdHelperDTOMap();
            DeductionCalendarMaster deductionCalendarMaster = DeductionCalendarMasterLocalServiceUtil.getDeductionCalendarMaster(masterSid);
            dto.setDeductionCalendarDesc(deductionCalendarMaster.getDeductionCalendarDesc());
            dto.setCategoryDdlb(idHelperDTOMap.get(deductionCalendarMaster.getCategory()));
            LOGGER.debug("getDeductionCalendarByIdForCopy ends");
            return dto;
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
            return dto;
        }
    }
    /**
     * getting count for Brand
     *
     * @param filterText
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static int getLazyBrandCount(String filterText) throws PortalException {
        filterText = StringUtils.trimToEmpty(filterText) + ConstantsUtils.PERCENCTAGE;
        LOGGER.debug("Entering getLazyBrandCount method with filterText {}" , filterText);
        List<Object[]> qualifierList;
        final DynamicQuery ifpDynamicQuery = BrandMasterLocalServiceUtil.dynamicQuery();
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.BRAND_NAME, filterText));
        ifpDynamicQuery.setProjection(ProjectionFactoryUtil.count(ConstantsUtils.BRAND_NAME));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
        qualifierList = DAO.getBrandList(ifpDynamicQuery);
        int brandCount = Integer.parseInt(String.valueOf(qualifierList.get(0)));
        LOGGER.debug("Ending getLazyBrandCount method : returning count : {}" , brandCount);
        return brandCount;
    }
    
    /**
     * getting results for Brand
     *
     * @param start
     * @param end
     * @param filterText
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static List<com.stpl.app.util.HelperDTO> getLazyBrandResults(final int start, final int end, String filterText, final com.stpl.app.util.HelperDTO brand, boolean isFilter) throws PortalException {
        filterText = StringUtils.trimToEmpty(filterText) + ConstantsUtils.PERCENCTAGE;
        LOGGER.debug("Entering getLazyBrandCount method with filterText {}" , filterText);
        List<Object[]> qualifierList;
        final List<com.stpl.app.util.HelperDTO> list = new ArrayList<>();
        int startValue;
        int endValue;
        if (start == Constants.ZERO) {
            startValue = start;
            endValue = end - 1;
        } else {
            startValue = start - 1;
            endValue = end - 1;
        }

        final DynamicQuery ifpDynamicQuery = BrandMasterLocalServiceUtil.dynamicQuery();
        ifpDynamicQuery.setLimit(startValue, endValue);
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.BRAND_MASTER_SID));
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.BRAND_NAME));
        ifpDynamicQuery.setProjection(projectionList);
        ifpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.BRAND_NAME));
        if (filterText != null) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.BRAND_NAME, filterText));
        }
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
        if (brand != null && brand.getId() != 0) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.BRAND_MASTER_SID, brand.getId()));
        }

        qualifierList = DAO.getBrandList(ifpDynamicQuery);

        com.stpl.app.util.HelperDTO dto;
        if (start == Constants.ZERO) {
            dto = new com.stpl.app.util.HelperDTO(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
            list.add(dto);
            if (brand != null && brand.getId() != 0) {

                list.add(brand);
            }

        }
        for (final Iterator<Object[]> iterator = qualifierList.iterator(); iterator.hasNext();) {
            final Object[] value = iterator.next();
            dto = new com.stpl.app.util.HelperDTO(StringUtils.EMPTY);
            dto.setId(value[0] != null ? Integer.parseInt(value[0].toString()) : 0);
            dto.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
            list.add(dto);
        }
        LOGGER.debug("return Brand size - {}" , list.size());
        return list;
    }

    /**
     * getting count for CompanyQualifierName
     *
     * @param filterText
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static int getLazyItemQualifierNameCount(String filterText, boolean isEditList) throws PortalException {
        filterText = StringUtils.trimToEmpty(filterText) + ConstantsUtils.PERCENCTAGE;
        LOGGER.debug("Entering getLazyCompanyQualifierNameCount method with filterText {}" , filterText);
        final DynamicQuery ifpDynamicQuery = ItemQualifierLocalServiceUtil.dynamicQuery();
        ifpDynamicQuery.setProjection(ProjectionFactoryUtil.count(ConstantsUtils.ITEM_QUAL_NAME));
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_QUALIFIER_SID));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_QUAL_NAME, filterText));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.ITEM_QUAL_NAME, StringUtils.EMPTY)));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.ITEM_QUAL_NAME));
        final List<Object[]> qualifierList = DAO.itemIrtQualifierNameList(ifpDynamicQuery);
        itemQualifierNameCount = Integer.parseInt(String.valueOf(qualifierList.get(0)));
        if (itemQualifierNameCount == 0 && isEditList) {
            itemQualifierNameCount++;
        }
        LOGGER.debug("Ending getLazyPriceTypeCount method : returning count : {}" , itemQualifierNameCount);
        return itemQualifierNameCount;
    }

    /**
     * getting results for CompanyQualifierName
     *
     * @param start
     * @param end
     * @param filterText
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static List<com.stpl.app.util.HelperDTO> getLazyItemQualifierNameResults(final int start, final int end, final String filteredText, final boolean editListFlag) throws PortalException {
        String filterText = StringUtils.trimToEmpty(filteredText) + ConstantsUtils.PERCENCTAGE;
        LOGGER.debug("Entering getLazyCompanyQualifierNameCount method with filterText {}" , filterText);
        final List<com.stpl.app.util.HelperDTO> list = new ArrayList<>();
        int startValue;
        int endValue;
        if (start == Constants.ZERO) {
            startValue = start;
            endValue = end - 1;
        } else {
            startValue = start - 1;
            endValue = end - 1;
        }

        final DynamicQuery ifpDynamicQuery = ItemQualifierLocalServiceUtil.dynamicQuery();
        ifpDynamicQuery.setLimit(startValue, endValue);
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_QUALIFIER_SID));
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_QUAL_NAME));
        ifpDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectionList));
        ifpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.ITEM_QUAL_NAME));
        if (filterText != null) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_QUAL_NAME, filterText));
        }
        ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.ITEM_QUAL_NAME, StringUtils.EMPTY)));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.ITEM_QUAL_NAME));
        final List<Object[]> qualifierList = DAO.itemIrtQualifierNameList(ifpDynamicQuery);

        com.stpl.app.util.HelperDTO dto;
        if (start == Constants.ZERO) {
            dto = new com.stpl.app.util.HelperDTO(ConstantsUtils.SELECT_ONE);
            list.add(dto);
        }
        for (final Iterator<Object[]> iterator = qualifierList.iterator(); iterator.hasNext();) {
            final Object[] value = iterator.next();
            dto = new com.stpl.app.util.HelperDTO(StringUtils.EMPTY);
            dto.setId(value[0] != null ? Integer.parseInt(value[0].toString()) : 0);
            dto.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
            if (!StringUtils.EMPTY.equals(dto.getDescription())) {
                list.add(dto);
            }
        }
        if (editListFlag) {
            filterText = filterText.replace("*", StringUtils.EMPTY);
            filterText = filterText.replace(ConstantsUtils.PERCENCTAGE, StringUtils.EMPTY);

            filterText = filterText.toUpperCase(Locale.ENGLISH);
            if (!StringUtils.EMPTY.equals(filterText) && Constants.EDIT_LIST.startsWith(filterText)) {
                dto = new com.stpl.app.util.HelperDTO(ConstantsUtils.EDITLIST);
                list.add(dto);
            }
            if (itemQualifierNameCount != 0 && itemQualifierNameCount == end - NumericConstants.TWO && StringUtils.EMPTY.equals(filterText)) {
                dto = new com.stpl.app.util.HelperDTO(ConstantsUtils.EDITLIST);
                list.add(dto);
            }
        }
        LOGGER.debug("return CompanyQualifier size - {}" , list.size());
        return list;
    }
    public static int getLazyManufactureIdCount(String filter) throws PortalException {
        filter = StringUtils.trimToEmpty(filter) + ConstantsUtils.PERCENCTAGE;
        LOGGER.debug("Entering getLazyCompanyQualifierNameCount method with filterText : {}" , filter);
        List<Object[]> qualifierList;
        final DynamicQuery cfpDynamicQuery = CompanyMasterLocalServiceUtil.dynamicQuery();
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_ID, filter));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_TYPE, com.stpl.app.util.GeneralCommonUtils.getHelperCode("COMPANY_TYPE", ConstantsUtils.MANUFACTURE)));
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.countDistinct(ConstantsUtils.COMPANY_MASTER_ID));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.COMPANY_ID));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.COMPANY_ID, StringUtils.EMPTY)));
        qualifierList = DAO.getBrandList(cfpDynamicQuery);
        LOGGER.debug("Ending getLazyCompanyQualifierNameCount method with filterText with count : {}" , qualifierList.get(0));
        return Integer.parseInt(String.valueOf(qualifierList.get(0)));
    }

    public static List<com.stpl.app.util.HelperDTO> getLazyManufactureIdResults(final int startIndex, final int end, final String filter, final com.stpl.app.util.HelperDTO manufactureId, final boolean filerGeneraterFlag) throws PortalException {
        final List<com.stpl.app.util.HelperDTO> list = new ArrayList<>();
        int startValue = startIndex;
        int endValue;
        if (startIndex == ConstantsUtils.ZERO_INT) {
            endValue = end - 1;
        } else {
            startValue = startIndex - 1;
            endValue = end - 1;
        }
        LOGGER.debug("Entering getLazyManufactureIdResults method with filterText : {}" , filter);
        final String filterString = StringUtils.trimToEmpty(filter) + ConstantsUtils.PERCENCTAGE;
        final DynamicQuery cfpDynamicQuery = CompanyMasterLocalServiceUtil.dynamicQuery();
        cfpDynamicQuery.setLimit(startValue, endValue);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_TYPE, com.stpl.app.util.GeneralCommonUtils.getHelperCode("COMPANY_TYPE", ConstantsUtils.MANUFACTURE)));
        if (manufactureId != null && manufactureId.getId() != 0) {
            cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.COMPANY_MASTER_ID, manufactureId.getId()));
        }
        // Added to check INBOUND_STATUS is A. ETL Soft delete data should not be included, where INBOUND_STATUS is D
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_MASTER_ID));
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_ID));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.COMPANY_ID, StringUtils.EMPTY)));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.COMPANY_ID));
        if (filter != null) {
            cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_ID, filterString));
        }
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectionList));
        final List<Object[]> returnList = DAO.getBrandList(cfpDynamicQuery);
        com.stpl.app.util.HelperDTO helperTable;
        if (startIndex == ConstantsUtils.ZERO_INT) {
            helperTable = new com.stpl.app.util.HelperDTO();
            if (filerGeneraterFlag) {
                helperTable.setDescription(ConstantsUtils.SHOW_ALL);
            } else {
                helperTable.setDescription(ConstantsUtils.SELECT_ONE);
            }
            list.add(helperTable);
            if (manufactureId != null && manufactureId.getId() != 0) {
                list.add(manufactureId);
            }
        }
        for (final Iterator<Object[]> iterator = returnList.iterator(); iterator.hasNext();) {
            final Object[] value = iterator.next();
            helperTable = new com.stpl.app.util.HelperDTO();
            helperTable.setId(value[0] != null ? Integer.parseInt(value[0].toString()) : 0);
            helperTable.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
            if (!StringUtils.EMPTY.equals(helperTable.getDescription())) {
                list.add(helperTable);
            }
        }
        LOGGER.debug("Ending getLazyManufactureIdResults  return list size : {}" , list.size());
        return list;
    }

}
