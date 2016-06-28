/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.logic;

import com.stpl.app.model.CompanyQualifier;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.gcm.tp.dao.TradingPartnerDAO;
import com.stpl.app.gcm.tp.dao.impl.TradingPartnerDAOImpl;
import com.stpl.app.gcm.tp.dto.CompanyLinkDTO;
import com.stpl.app.gcm.tp.dto.IdDescriptionDTO;
import com.stpl.app.gcm.tp.dto.TradingPartnerDTO;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.Converters;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.ComboBox;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author lokeshwari
 */
public class CompanySearchLogic {

    TradingPartnerDAO tpDao = new TradingPartnerDAOImpl();
    Converters converters=new Converters();
    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(CompanySearchLogic.class);

    public int companySearchCount(TradingPartnerDTO tpDto, String parentCompanyNo,String parentCompanyName, Set<Container.Filter> filters, String recordLockStatus, String searchSessionId) throws PortalException, SystemException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        List resultList;
        parameters.put("lazyLoadResults", null);
        parameters.put("recordLockStatus", recordLockStatus);

        //Used to load the companies for Customer Selection tab in Transfer projection Details module
        if (tpDto.getCompanyMasterSids().size() > 0) {
            parameters.put("companyMasterSids", CommonUtils.CollectionToString(tpDto.getCompanyMasterSids(), true));
        }
        //Used to restrict the companies for Customer Selection tab in Transfer projection Details module
        if (!tpDto.getCompanyRestrictionSessionId().isEmpty()) {
            parameters.put("pdt.companyRestrictionSessionId", tpDto.getCompanyRestrictionSessionId());
        }

        if (isValidCriteria(tpDto.getCompanyId())) {
            String companyId = tpDto.getCompanyId();
            companyId = companyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyId", companyId);
        }
        if (isValidCriteria(tpDto.getCompanyName())) {
            String companyName = tpDto.getCompanyName();
            companyName = companyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyName", companyName);
        }
        if (isValidCriteria(tpDto.getCompanyNo())) {
            String companyNo = tpDto.getCompanyNo();
            companyNo = companyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyNo", companyNo);
        }
        if (isValidSid(tpDto.getCompanyType())) {
            String companyType = tpDto.getCompanyType();
            companyType = companyType.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyType", companyType);
        }
        if (isValidSid(tpDto.getCompanyCategory())) {
            String companyCategory = tpDto.getCompanyCategory();
            companyCategory = companyCategory.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyCategory", companyCategory);
        }
        if (isValidSid(tpDto.getTradeClass())) {
            String tradeClass = tpDto.getTradeClass();
            tradeClass = tradeClass.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyTradeClass", tradeClass);
        }
        if (isValidCriteria(tpDto.getIdentifier())) {
            String identifier = tpDto.getIdentifier();
            identifier = identifier.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("identifier", identifier);
        }
        if (isValidSid(tpDto.getIdentifierType())) {
            String identifierType = tpDto.getIdentifierType();
            identifierType = identifierType.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("identifierType", identifierType);
        }
        if (isValidSid(tpDto.getCompanyStatus())) {
            String companyStatus = tpDto.getCompanyStatus();
            companyStatus = companyStatus.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyStatus", companyStatus);
        }
        if (isValidCriteria(parentCompanyNo)) {
            String parentCompanyId = parentCompanyNo;
            parentCompanyId = parentCompanyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("parentCompanyNo", parentCompanyId);
        }
        if (isValidCriteria(parentCompanyName)) {
            String parentCompanyId = parentCompanyName;
            parentCompanyId = parentCompanyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("parentCompanyName", parentCompanyId);
        }
        if (isValidSid(searchSessionId)) {
            parameters.put("searchSessionId", searchSessionId);
        }
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    parameters.put("filter~" + stringFilter.getPropertyId(), filterString);
                }
            }
        }
        resultList = tpDao.searchCompanies(parameters);
        return Integer.parseInt(String.valueOf(resultList.get(0)));
    }

    public List<TradingPartnerDTO> searchCompaniesLazy(final TradingPartnerDTO tpDTO, int startIndex, int offset, List<SortByColumn> sortByColumns, String parentCompanyNo,String parentCompanyName, Set<Container.Filter> filters, String recordLockStatus, String searchSessionId,boolean isProjectionSelected) throws PortalException, Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        List resultList;
        List<TradingPartnerDTO> returnList = null;
        String columnName = StringUtils.EMPTY;
        String dbColumnName = StringUtils.EMPTY;
        parameters.put("startIndex", startIndex);
        parameters.put("offset", offset);
        parameters.put("lazyLoadResults", "lazyLoadResults");
        parameters.put("recordLockStatus", recordLockStatus);
        if (isProjectionSelected) {
            parameters.put("checkRecord", 1);
        } else {
            parameters.put("checkRecord", null);
        }
        if (tpDTO.getCompanyMasterSids().size() > 0) {
            parameters.put("companyMasterSids", CommonUtils.CollectionToString(tpDTO.getCompanyMasterSids(), true));
        }

        if (!tpDTO.getCompanyRestrictionSessionId().isEmpty()) {
            parameters.put("pdt.companyRestrictionSessionId", tpDTO.getCompanyRestrictionSessionId());
        }

        if (isValidCriteria(tpDTO.getCompanyId())) {
            String companyId = tpDTO.getCompanyId();
            companyId = companyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyId", companyId);
        }
        if (isValidCriteria(tpDTO.getCompanyName())) {
            String companyName = tpDTO.getCompanyName();
            companyName = companyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyName", companyName);
        }
        if (isValidCriteria(tpDTO.getCompanyNo())) {
            String companyNo = tpDTO.getCompanyNo();
            companyNo = companyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyNo", companyNo);
        }
        if (isValidSid(tpDTO.getCompanyType())) {
            String companyType = tpDTO.getCompanyType();
            companyType = companyType.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyType", companyType);
        }
        if (isValidSid(tpDTO.getCompanyCategory())) {
            String companyCategory = tpDTO.getCompanyCategory();
            companyCategory = companyCategory.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyCategory", companyCategory);
        }
        if (isValidSid(tpDTO.getTradeClass())) {
            String tradeClass = tpDTO.getTradeClass();
            tradeClass = tradeClass.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyTradeClass", tradeClass);
        }
        if (isValidCriteria(tpDTO.getIdentifier())) {
            String identifier = tpDTO.getIdentifier();
            identifier = identifier.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("identifier", identifier);
        }
        if (isValidSid(tpDTO.getIdentifierType())) {
            String identifierType = tpDTO.getIdentifierType();
            identifierType = identifierType.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("identifierType", identifierType);
        }
        if (isValidSid(tpDTO.getCompanyStatus())) {
            String companyStatus = tpDTO.getCompanyStatus();
            companyStatus = companyStatus.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyStatus", companyStatus);
        }
        if (isValidCriteria(parentCompanyNo)) {
            String parentCompanyId = parentCompanyNo;
            parentCompanyId = parentCompanyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("parentCompanyNo", parentCompanyId);
        }
        if (isValidCriteria(parentCompanyName)) {
            String parentCompanyId = parentCompanyName;
            parentCompanyId = parentCompanyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("parentCompanyName", parentCompanyId);
        }
        if (isValidSid(searchSessionId)) {
            parameters.put("searchSessionId", searchSessionId);
        }
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    parameters.put("filter~" + stringFilter.getPropertyId(), filterString);
                }
            }
        }
        boolean asc = false;
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();

                columnName = sortByColumn.getName();
                asc = sortByColumn.getType() == SortByColumn.Type.ASC;
            }
            parameters.put(Constants.IS_ORDERED, Constants.TRUE);
            parameters.put("orderBy~companyName", columnName);
            parameters.put("orderBy", asc ? "ASC" : "DESC");
        }
        resultList = tpDao.searchCompanies(parameters);
        returnList = converters.searchCompany(resultList);
        return returnList;
    }

    private boolean isValidCriteria(String value) {
        boolean isValid = false;
        if (value != null && !Constants.NULL.equals(String.valueOf(value)) && !StringUtils.EMPTY.equals(String.valueOf(value))
                && !StringUtils.isEmpty(String.valueOf(value)) && !StringUtils.isBlank(String.valueOf(value))) {
            isValid = true;
        } else {
            isValid = false;
        }
        return isValid;
    }

    public List<IdDescriptionDTO> loadDdlbs(String fieldName) {
        List list = new ArrayList();
        List<IdDescriptionDTO> resultList = new ArrayList<IdDescriptionDTO>();
        IdDescriptionDTO idDescription = null;
        try {
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
            productProjectionList.add(ProjectionFactoryUtil.property("helperTableSid"));
            productProjectionList.add(ProjectionFactoryUtil.property("description"));
            dynamicQuery.add(RestrictionsFactoryUtil.eq("listName", fieldName));
            dynamicQuery.setProjection(productProjectionList);
            list = HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);

            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Object obj[] = (Object[]) list.get(i);
                    idDescription = new IdDescriptionDTO(Integer.parseInt(String.valueOf(obj[0])), String.valueOf(obj[1]));
                    resultList.add(idDescription);
                }
            }
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return resultList;
    }

    public void setIdDescription(List<IdDescriptionDTO> idDesList, ComboBox ddlb) {
        ddlb.removeAllItems();
        ddlb.addItem(Constants.SELECT_ONE);
        ddlb.setNullSelectionItemId(Constants.SELECT_ONE);
        for (IdDescriptionDTO dto : idDesList) {
            ddlb.addItem(String.valueOf(dto.getId()));
            ddlb.setItemCaption(String.valueOf(dto.getId()), dto.getDescription());
        }
    }

    public List<IdDescriptionDTO> loadIdentifierTypeDdlb() {
        List list = new ArrayList();
        List<IdDescriptionDTO> resultList = new ArrayList<IdDescriptionDTO>();
        IdDescriptionDTO idDescription = null;
        try {
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyQualifier.class);
            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
            productProjectionList.add(ProjectionFactoryUtil.property("companyQualifierSid"));
            productProjectionList.add(ProjectionFactoryUtil.property("companyQualifierName"));
            dynamicQuery.setProjection(productProjectionList);
            list = HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);

            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Object obj[] = (Object[]) list.get(i);
                    idDescription = new IdDescriptionDTO(Integer.parseInt(String.valueOf(obj[0])), String.valueOf(obj[1]));
                    resultList.add(idDescription);
                }
            }
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return resultList;
    }

    private boolean isValidSid(String sId) {
        boolean isValid = false;
        if (isValidCriteria(String.valueOf(sId))) {
            if (!Constants.ZEROSTRING.equals(String.valueOf(sId))) {
                isValid = true;
            }
        }
        return isValid;
    }

    public void clearTempTable(String sessionId) {
        String query = "Delete from GCM_COMPANY_DETAILS WHERE SESSION_ID = '" + sessionId + "'";
        CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
    }

    public static int getCompanyCount(String searchSessionId) {
        int count = 0;
        try{
        String query = "Select Count(COMPANY_MASTER_SID) from GCM_COMPANY_DETAILS where CHECK_RECORD = '1' AND SESSION_ID = '" + searchSessionId + "'";
        count = (Integer) CompanyMasterLocalServiceUtil.executeQuery(query).get(0);
        }catch(Exception e){
           LOGGER.error(e);
        }
        return count;
    }

    public List<TradingPartnerDTO> loadAllCustomers(List<String> companyMasterSids) throws ParseException, PortalException, SystemException {
        StringBuilder query = new StringBuilder(StringUtils.EMPTY);
        List resultList;
        List<TradingPartnerDTO> returnList;
        query.append(CustomSQLUtil.get("getCompaniesFromMainTable"));
        query.append(" AND cm.COMPANY_MASTER_SID in (" + CommonUtils.CollectionToString(companyMasterSids, true) + ")");
        resultList = CompanyMasterLocalServiceUtil.executeQuery(query.toString());
        returnList = converters.searchCompany(resultList);
        return returnList;
    }

    public void insertIntoTempTable(String searchSessionId, String recordLockStatus, String updateType) {
        StringBuilder query = new StringBuilder(StringUtils.EMPTY);
        query.append("With TEMP as (");
        query.append(CustomSQLUtil.get("getCompaniesFromMainTable"));
        if (!Constants.NULL.equals(recordLockStatus) && !recordLockStatus.isEmpty()) {
            query.append(" and cm.RECORD_LOCK_STATUS = ").append(recordLockStatus);
        }
        query.append(") INSERT into GCM_COMPANY_DETAILS(CHECK_RECORD, COMPANY_MASTER_SID,COMPANY_NO,COMPANY_NAME,SESSION_ID,SUB_MODULE_NAME,Created_Date)");
        query.append("SELECT '0', companyMasterSid,companyNo,companyName,'").append(searchSessionId).append("','").append(updateType).append("'");
        query.append(" ,getdate()");
        query.append(" FROM TEMP;");
        query.append(" delete  FROM GCM_COMPANY_DETAILS where getdate()-1>CREATED_DATE");
                CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());
    }

    public int updateCheckRecord(String fromCompanyMasterSid, String toCompanyMasterSid, boolean checkvalue, String sessionId) {
        LOGGER.info("Entering updateCheckRecord");
        return tpDao.updateCheckRecord(fromCompanyMasterSid, toCompanyMasterSid, checkvalue, sessionId);
    }

    public int updateCustomer(String companyMasterSid, String linkId, String sessionId) {
        LOGGER.info("Entering updateCustomer");
        return tpDao.updateCustomer(companyMasterSid, linkId, sessionId);
    }

    public int insertCustomer(String companyMasterSid, String companyId, String companyNumber, String companyName, String linkId, String sessionId) {
        LOGGER.info("Entering insertCustomer");
        return tpDao.insertCustomer(companyMasterSid, companyId, companyNumber, companyName, linkId, sessionId);
    }

    public int getLinkedCompaniesCount(final CompanyLinkDTO tpDTO, Set<Container.Filter> filters, String searchSessionId) {
        LOGGER.info("Entering getLinkedCompaniesCount");
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("queryType", "count");
        parameters.put("searchSessionId", searchSessionId);
        if (isValidCriteria(tpDTO.getFromCompanyId())) {
            String fromCompanyId = tpDTO.getFromCompanyId();
            fromCompanyId = fromCompanyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("fromCompanyId", fromCompanyId);
        }
        if (isValidCriteria(tpDTO.getFromCompanyNo())) {
            String fromCompanyNo = tpDTO.getFromCompanyNo();
            fromCompanyNo = fromCompanyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("fromCompanyNo", fromCompanyNo);
        }
        if (isValidCriteria(tpDTO.getFromCompanyName())) {
            String fromCompanyName = tpDTO.getFromCompanyName();
            fromCompanyName = fromCompanyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("fromCompanyName", fromCompanyName);
        }
        if (isValidCriteria(tpDTO.getToCompanyId())) {
            String toCompanyId = tpDTO.getToCompanyId();
            toCompanyId = toCompanyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("toCompanyId", toCompanyId);
        }
        if (isValidCriteria(tpDTO.getToCompanyNo())) {
            String toCompanyNo = tpDTO.getToCompanyNo();
            toCompanyNo = toCompanyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("toCompanyNo", toCompanyNo);
        }
        if (isValidCriteria(tpDTO.getToCompanyName())) {
            String toCompanyName = tpDTO.getToCompanyName();
            toCompanyName = toCompanyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("toCompanyName", toCompanyName);
        }

        addFilterParameters(filters, parameters);
        List resultList = tpDao.searchLinkedCompanies(parameters);
        LOGGER.info("Exiting getLinkedCompaniesCount");
        return Integer.parseInt(String.valueOf(resultList.get(0)));
    }

    public List getLinkedCompanies(final CompanyLinkDTO tpDTO, int start, int offset, Set<Container.Filter> filters, String searchSessionId) {
        LOGGER.info("Entering getLinkedCompanies");
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("queryType", "data");
        parameters.put("start", start);
        parameters.put("offset", offset);
        parameters.put("searchSessionId", searchSessionId);
        if (isValidCriteria(tpDTO.getFromCompanyId())) {
            String fromCompanyId = tpDTO.getFromCompanyId();
            fromCompanyId = fromCompanyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("fromCompanyId", fromCompanyId);
        }
        if (isValidCriteria(tpDTO.getFromCompanyNo())) {
            String fromCompanyNo = tpDTO.getFromCompanyNo();
            fromCompanyNo = fromCompanyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("fromCompanyNo", fromCompanyNo);
        }
        if (isValidCriteria(tpDTO.getFromCompanyName())) {
            String fromCompanyName = tpDTO.getFromCompanyName();
            fromCompanyName = fromCompanyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("fromCompanyName", fromCompanyName);
        }
        if (isValidCriteria(tpDTO.getToCompanyId())) {
            String toCompanyId = tpDTO.getToCompanyId();
            toCompanyId = toCompanyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("toCompanyId", toCompanyId);
        }
        if (isValidCriteria(tpDTO.getToCompanyNo())) {
            String toCompanyNo = tpDTO.getToCompanyNo();
            toCompanyNo = toCompanyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("toCompanyNo", toCompanyNo);
        }
        if (isValidCriteria(tpDTO.getToCompanyName())) {
            String toCompanyName = tpDTO.getToCompanyName();
            toCompanyName = toCompanyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("toCompanyName", toCompanyName);
        }
        addFilterParameters(filters, parameters);
        List resultList = convertObjectToList(tpDao.searchLinkedCompanies(parameters));
        LOGGER.info("Exiting getLinkedCompanies");
        return resultList;
    }

    private void addFilterParameters(Set<Container.Filter> filters, Map<String, Object> parameters) {
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    parameters.put("filter~" + stringFilter.getPropertyId(), filterString);
                }
            }
        }
    }

    /**
     * Set the Values to respective fields for Company Search
     *
     * @param resultList
     * @return
     */
    private List convertObjectToList(List searchLinkedCompanies) {
        List<CompanyLinkDTO> linkedCompaniesList = new ArrayList<CompanyLinkDTO>();
        CompanyLinkDTO clDto;
        int size = searchLinkedCompanies.size();
        for (int loop = 0, limit = size; loop < limit; loop++) {
            clDto = new CompanyLinkDTO();
            Object objects[] = (Object[]) searchLinkedCompanies.get(loop);
            clDto.setFromCompanyMasterSid(String.valueOf(objects[0]));
            clDto.setFromCompanyId(String.valueOf(objects[1]));
            clDto.setFromCompanyNo(String.valueOf(objects[2]));
            clDto.setFromCompanyName(String.valueOf(objects[3]));
            clDto.setToCompanyMasterSid(String.valueOf(objects[4]));
            clDto.setToCompanyId(String.valueOf(objects[5]));
            clDto.setToCompanyNo(String.valueOf(objects[6]));
            clDto.setToCompanyName(String.valueOf(objects[7]));
            linkedCompaniesList.add(clDto);
        }

        return linkedCompaniesList;
    }

    public int removeCustomerLink(String linkedCustomersSessionId) {
        LOGGER.info("Entering removeCustomerLink");
        return tpDao.removeCustomerLink(linkedCustomersSessionId);
    }

    public List<String> getLinkedCompaniesList(String linkedCustomersSessionId, boolean isFromCompany) {
        LOGGER.info("Entering getLinkedCompaniesList");
        return tpDao.getLinkedCompaniesList(linkedCustomersSessionId, isFromCompany);
    }

    public boolean isAnyRecordSelectedToRemove(String linkedCustomersSessionId) {
        int count = 0;

        try {
            count = Integer.valueOf(String.valueOf(tpDao.getLinkedCustomersCheckedRecordCount(linkedCustomersSessionId).get(0)));
        } catch (Exception e) {
          LOGGER.error(e.getMessage());
        }
        return count > 0;
    }
}
