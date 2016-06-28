package com.stpl.app.contract.global.logic;

import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.common.util.HelperListUtil;
import com.stpl.app.contract.contractheader.dto.CompanyResultsDTO;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.dao.impl.CFPSearchLogicDAOImpl;
import com.stpl.app.contract.dao.impl.ContractHeaderLogicDAOImpl;
import com.stpl.app.contract.dashboard.dto.CFPSearchDTO;
import com.stpl.app.contract.dashboard.logic.DashBoardLogic;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.global.dto.CFPCompanyDTO;
import com.stpl.app.contract.global.dto.CompanyMasterDTO;
import com.stpl.app.contract.global.dto.CompanySearchDto;
import com.stpl.app.contract.global.dto.SearchCFPForm;
import com.stpl.app.contract.global.util.CommonUtils;
import com.stpl.app.contract.ui.ErrorfulFieldGroup;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.FieldNameUtils;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ImtdCfpDetails;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.service.CfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ImtdCfpDetailsLocalServiceUtil;
import com.stpl.app.util.ConstantUtil;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.domain.contract.contractdashboard.CompanyFamilyplanDAO;
import com.stpl.domain.contract.contractdashboard.globalcontract.CompanyFamilyplanLogic;
import com.stpl.domain.contract.contractheader.ContractHeaderDAO;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.Criterion;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 * Logic for Company Family Plan.
 *
 * @author
 */
public class CFPSearchLogic extends BeanItemContainer<SearchCFPForm> implements CompanyFamilyplanLogic {

    /**
     * The dao.
     */
    private static final CompanyFamilyplanDAO dao = new CFPSearchLogicDAOImpl();
    SessionDTO sessionDTO;

    private final ContractHeaderDAO companyTypeLogic = new ContractHeaderLogicDAOImpl();

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CFPSearchLogic.class);
    private static HelperListUtil helperListUtil = HelperListUtil.getInstance();

    //Java date format
    public static String DEFAULT_JAVA_DATE_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";
    //SQL date format
    public static String DEFAULT_SQL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private static String searchresults = "searchResults";

    /**
     * Default Constructor.
     */
    public CFPSearchLogic() {
        super(SearchCFPForm.class);
    }

    public CFPSearchLogic(final SessionDTO sessionDTO) {
        super(SearchCFPForm.class);
        this.sessionDTO = sessionDTO;
    }

    /**
     * Returns the List of Company Master DTO with the given search field and
     * Value.
     *
     * @param searchField - String contains Company Details
     * @param value - Value for wild card
     * @return List of CompanyMasterDTO.
     * @throws SystemException
     */
    public List<CompanyMasterDTO> getCompaniesForCFP(final String searchField, final String val, int start, int end, List<OrderByColumn> columns, final BeanSearchCriteria searchCriteria) throws SystemException {
        LOGGER.info("Entering CFPSearchLogic getCompaniesForCFP");
        final Map<String, String> map = new HashMap<String, String>();
        map.put("Company ID", "COMPANY_ID");
        map.put("Company No", Constants.COMPANY_NO_CAPS);
        map.put("Company Name", Constants.COMPANY_NAME_CAPS);
        map.put("Company Type", Constants.COMP_TYPE);
        map.put("Company Status", Constants.COMPANY_STATUS_CAPS);

        String column = Constants.COMPANY_NO_CAPS;
        String orderBy = Constants.ASC;

        for (final Iterator<OrderByColumn> iterator = columns.iterator(); iterator.hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();

            if (Constants.COMPANY_ID.equals(orderByColumn.getName())) {
                column = "COMPANY_ID";
            } else if (Constants.COMPANY_NO.equals(orderByColumn.getName())) {
                column = Constants.COMPANY_NO_CAPS;
            } else if (Constants.COMPANY_NAME.equals(orderByColumn.getName())) {
                column = Constants.COMPANY_NAME_CAPS;
            } else if (Constants.DISPLAY_COM_STATUS.equals(orderByColumn.getName())) {
                column = "cstatus";
            } else if ("displayCompanyType".equals(orderByColumn.getName())) {
                column = "ctype";
            }

            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                orderBy = Constants.ASC;
            } else {
                orderBy = "DESC";
            }
        }
        String value = Constants.PERCENT;
        if (StringUtils.isNotBlank(val)) {
            value = val.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        List<String> returnList = null;
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put(Constants.COMPANY_ID, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_NO, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_NAME, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_STATUS, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_TYPE, StringUtils.EMPTY);
        filterMap.put("companyGroup", StringUtils.EMPTY);
        filterMap.put("", StringUtils.EMPTY);

        if (searchCriteria != null && searchCriteria.getFilters() != null) {
            for (Container.Filter filter : searchCriteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;

                    if (stringFilter.getPropertyId().equals(Constants.COMPANY_NO)) {

                        filterMap.put(Constants.COMPANY_NO, filterText);

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_ID)) {

                        filterMap.put(Constants.COMPANY_ID, filterText);

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_NAME)) {

                        filterMap.put(Constants.COMPANY_NAME, filterText);

                    } else if (stringFilter.getPropertyId().equals("displayCompanyType")) {

                        filterMap.put(Constants.COMPANY_TYPE, filterText);

                    } else if (stringFilter.getPropertyId().equals(Constants.DISPLAY_COM_STATUS)) {

                        filterMap.put(Constants.COMPANY_STATUS, filterText);

                    }
                }

            }
        }

        List list = CfpContractDetailsLocalServiceUtil.getCompaniesList(map.get(searchField), value, filterMap, start, end, column, orderBy, returnList, null);
        final List<CompanyMasterDTO> companyMasterDTOList = getCustomizedCompanyData(list);
        LOGGER.info("End of companyMasterList()");
        return companyMasterDTOList;
    }

    /**
     * Returns the List of Company Master DTO with the given search field and
     * Value.
     *
     * @param searchField - String contains Company Details
     * @param value - Value for wild card
     * @return List of CompanyMasterDTO.
     * @throws SystemException
     */
    public List<CompanyMasterDTO> getCompaniesCFP(final String searchField, final String val) throws SystemException {
        LOGGER.info("Entering CFPSearchLogic getCompaniesForCFP");
        final Map<String, String> map = new HashMap<String, String>();
        map.put("Company No", Constants.COMPANY_NO);
        map.put("Company Name", Constants.COMPANY_NAME);
        map.put("Company Type", Constants.COMPANY_TYPE);
        map.put("Company Status", Constants.COMPANY_STATUS);

        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        if (StringUtils.isNotBlank(val)) {
            final String value = val.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            cfpDynamicQuery.add(RestrictionsFactoryUtil.like(map.get(searchField), value));
        }
        final List<CompanyMaster> companyMasterList = dao.getCompanyMasterList(cfpDynamicQuery);
        final List<CompanyMasterDTO> companyMasterDTOList = getCustomizedCompanyData(companyMasterList);
        LOGGER.info("End of getCompaniesForCFP()");
        return companyMasterDTOList;
    }

    /**
     * Returns the List of Company Master DTO with the given search field and
     * Value.
     *
     * @param searchField - String contains Company Details
     * @param value - Value for wild card
     * @return List of CompanyMasterDTO.
     * @throws SystemException
     */
    public int getCompanyAddtionCount(final String searchField, final String val, final BeanSearchCriteria searchCriteria) throws SystemException {
        LOGGER.info("Entering CFPSearchLogic getCompanyAddtionCount");
        final Map<String, String> map = new HashMap<String, String>();
        map.put("Company ID", "COMPANY_ID");
        map.put("Company No", Constants.COMPANY_NO_CAPS);
        map.put("Company Name", Constants.COMPANY_NAME_CAPS);
        map.put("Company Type", Constants.COMP_TYPE);
        map.put("Company Status", Constants.COMPANY_STATUS_CAPS);
        Map<String, Object> filterMap = new HashMap<String, Object>();
        String value = Constants.PERCENT;
        if (StringUtils.isNotBlank(val)) {
            value = val.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        List<Integer> returnList = new ArrayList<Integer>();
        filterMap.put(Constants.COMPANY_ID, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_NO, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_NAME, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_STATUS, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_TYPE, StringUtils.EMPTY);
        if (searchCriteria != null && searchCriteria.getFilters() != null) {
            for (Container.Filter filter : searchCriteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;

                    if (stringFilter.getPropertyId().equals(Constants.COMPANY_ID)) {

                        filterMap.put(Constants.COMPANY_ID, filterText);

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_NO)) {

                        filterMap.put(Constants.COMPANY_NO, filterText);

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_NAME)) {

                        filterMap.put(Constants.COMPANY_NAME, filterText);

                    } else if (stringFilter.getPropertyId().equals("displayCompanyType")) {

                        filterMap.put(Constants.COMPANY_TYPE, filterText);

                    } else if (stringFilter.getPropertyId().equals(Constants.DISPLAY_COM_STATUS)) {
                        filterMap.put(Constants.COMPANY_STATUS, filterText);
                    }
                }
            }
        }
        List list = CfpContractDetailsLocalServiceUtil.getCompaniesList(map.get(searchField), value, filterMap, 0, 0, null, null, returnList, null);
        final int count = list != null ? list.size() : 0;
        LOGGER.info("End of getCompaniesForCFP()");
        return count;
    }

    /**
     * Returns the customized ComapnyMasterDTO based on CompanyMaster List.
     *
     * @param companyMasterList - List of CompanyMaster Model.
     * @return List of CompanyMasterDTO
     */
    public List<CompanyMasterDTO> getCustomizedCompanyData(final List companyMasterList) {
        LOGGER.info("Entering getCustomizedCompanyData()");
        final List<CompanyMasterDTO> companyDTO = new ArrayList<CompanyMasterDTO>();
        try {

            for (int i = 0; i < companyMasterList.size(); i++) {
                final Object[] obj = (Object[]) companyMasterList.get(i);
                final CompanyMasterDTO companyDTOObj = new CompanyMasterDTO();
                companyDTOObj.setCompanySystemId(String.valueOf(obj[0]));
                companyDTOObj.setCompanyId(obj[1].toString());
                companyDTOObj.setCompanyNo(obj[2].toString());
                companyDTOObj.setCompanyName(obj[3].toString());
                if (obj[4] != null) {
                    companyDTOObj.setCompanyType(obj[4].toString());
                }
                companyDTOObj.setCompanyStatus(obj[5].toString());
                companyDTOObj.setCompanyStartDate(CommonUtils.convertDateToString((Date) obj[6]));
                if (obj[7] != null) {
                    companyDTOObj.setCompanyEndDate(CommonUtils.convertDateToString((Date) obj[7]));
                }
                if (obj[8] != null) {
                    companyDTOObj.setDisplayCompanyType(obj[8].toString());
                }
                if (obj[9] != null) {
                    companyDTOObj.setDisplayCompanyStatus(obj[9].toString());
                }
                if (obj[12] != null && !ConstantUtil.SELECT_ONE.equals(String.valueOf(obj[12]))) {
                    companyDTOObj.setTradeClass(obj[12].toString());
                } else {
                    companyDTOObj.setTradeClass(StringUtils.EMPTY);
                }
                if (obj[10] != null && !ConstantUtil.SELECT_ONE.equals(String.valueOf(obj[10]))) {
                    companyDTOObj.setCompanyCategory(obj[10].toString());
                } else {
                    companyDTOObj.setCompanyCategory(StringUtils.EMPTY);
                }
                if (obj[11] != null && !ConstantUtil.SELECT_ONE.equals(String.valueOf(obj[11]))) {
                    companyDTOObj.setCompanyGroup(obj[11].toString());
                } else {
                    companyDTOObj.setCompanyGroup(StringUtils.EMPTY);
                }
                companyDTOObj.setCreatedDate(String.valueOf(new Date()));
                companyDTO.add(companyDTOObj);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        LOGGER.info("End of getCustomizedCompanyData()");
        return companyDTO;
    }

    /**
     * Returns the List of HelperDTO based on the CompanyType.
     *
     * @return List of HelperDTO
     */
    public List<HelperDTO> getDropDownList() throws SystemException {
        LOGGER.info("Entering getDropDownList()");
        List<CompanyMaster> list;
        List<HelperTable> helperTableList;
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        HelperDTO helperTable;

        /*
         final DynamicQuery htDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
         htDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.LIST_NAME, "COMP_TYPE"));
         htDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.DESCRIPTION, Constants.MANUFACTURER));
         helperTableList = dao.getHelperTable(htDynamicQuery);*/
        helperTableList = getHelperTableByListTypeAndDescription(Constants.COMP_TYPE, Constants.MANUFACTURER.toUpperCase());
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        int manufacturerCode = helperTableList.get(0).getHelperTableSid();
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.COMPANY_TYPE, manufacturerCode));
        LOGGER.info("getCompanyMasterList(cfpDynamicQuery)");
        list = dao.getCompanyMasterList(cfpDynamicQuery);
        LOGGER.info("returns List<CompanyMaster> size=" + list.size());

        if (list != null) {

            for (int i = 0; i < list.size(); i++) {
                helperTable = new HelperDTO(list.get(i).getCompanyMasterSid(), list.get(i).getCompanyId());
                helperList.add(helperTable);
            }

        }
        LOGGER.info("End of getDropDownList()");
        return helperList;
    }

    /**
     * Returns the record from Database based on the Company type.
     *
     * @return List of HelperDTO
     */
    public List<HelperDTO> getCompanyType() throws SystemException {
        LOGGER.info("Entering getCompanyType()");
        List<HelperTable> list;
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        LOGGER.info("findByHelperTableDetails(CompanyType)");
        list = dao.findByHelperTableDetails(UIUtils.COMPANY_TYPE);
        LOGGER.info("returns  List<HelperTable> size=" + list.size());

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }
        }
        LOGGER.info("End of getCompanyType()");
        return helperList;
    }

    /**
     * Returns the count of records based on SearchCompanyForm fields..
     *
     * @param searchCompanyForm - FieldGroup.
     * @return int - Count of records.
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public int getSearchCount(final ErrorfulFieldGroup searchCompanyForm, BeanSearchCriteria searchCriteria) throws SystemException {
        LOGGER.info("Entering getSearchCount()");

        String companyId;
        String companyNo;
        String companyName;
        int companyType;
        int companyStatus;
        if (searchCompanyForm.getField(Constants.COMPANY_ID).getValue() == null) {
            companyId = StringUtils.EMPTY;
        } else {
            companyId = searchCompanyForm.getField(Constants.COMPANY_ID).getValue().toString().trim();
        }
        if (searchCompanyForm.getField(Constants.COMPANY_NO).getValue() == null) {
            companyNo = StringUtils.EMPTY;
        } else {

            companyNo = searchCompanyForm.getField(Constants.COMPANY_NO).getValue().toString().trim();
        }
        if (searchCompanyForm.getField(Constants.COMPANY_NAME).getValue() == null) {
            companyName = StringUtils.EMPTY;
        } else {
            companyName = searchCompanyForm.getField(Constants.COMPANY_NAME).getValue().toString().trim();
        }

        if (searchCompanyForm.getField(Constants.COMPANY_TYPE).getValue() == null) {
            companyType = 0;
        } else {
            companyType = ((HelperDTO) (searchCompanyForm.getField(Constants.COMPANY_TYPE).getValue())).getId();
        }

        if (searchCompanyForm.getField(Constants.COMPANY_STATUS).getValue() == null) {
            companyStatus = 0;
        } else {
            companyStatus = ((HelperDTO) (searchCompanyForm.getField(Constants.COMPANY_STATUS).getValue())).getId();
        }

        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);

        if (StringUtils.isNotBlank(companyId)) {
            companyId = companyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            companyDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.COMPANY_ID, companyId));
        }
        if (StringUtils.isNotBlank(companyNo)) {
            companyNo = companyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            companyDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.COMPANY_NO, companyNo));
        }
        if (StringUtils.isNotBlank(companyName)) {
            companyName = companyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            companyDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.COMPANY_NAME, companyName));
        }

        if (companyType != 0) {
            companyDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.COMPANY_TYPE, companyType));
        }

        companyDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq("inboundStatus", "D")));

        if (companyStatus != 0) {
            companyDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.COMPANY_STATUS, companyStatus));
        }
        List<HelperTable> helperTableList = getHelperTableByListTypeAndDescription(Constants.COMP_TYPE, Constants.MANUFACTURER.toUpperCase());
        if (!helperTableList.isEmpty()) {
            companyDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.COMPANY_TYPE, helperTableList.get(0).getHelperTableSid()));
        }
        helperTableList = getHelperTableByListTypeAndDescription(Constants.COMP_TYPE, Constants.BUNIT);
        if (!helperTableList.isEmpty()) {
            companyDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.COMPANY_TYPE, helperTableList.get(0).getHelperTableSid()));
        }

        if (searchCriteria != null && searchCriteria.getFilters() != null) {
            for (Container.Filter filter : searchCriteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;

                    if (stringFilter.getPropertyId().equals(Constants.COMPANY_ID)) {
                        companyDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.COMPANY_ID, filterText));
                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_NO)) {
                        companyDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.COMPANY_NO, filterText));
                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_NAME)) {
                        companyDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.COMPANY_NAME, filterText));
                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_STATUS)) {
                        companyDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.COMPANY_STATUS, Integer.valueOf(stringFilter.getFilterString())));
                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_TYPE)) {
                        companyDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.COMPANY_TYPE, Integer.valueOf(stringFilter.getFilterString())));
                    }
                }
            }
        }

        final int searchCount = (int) dao.dynamicQueryCount(companyDynamicQuery);

        LOGGER.info("End of getSearchCount method");
        return searchCount;
    }

    /**
     * Returns the CompanySearchDTO based on the SearchCompanyForm fields.
     *
     * @param searchCompanyForm - FieldGroup.
     * @param start - Starting index
     * @param end - Ending index
     * @param orderByColumns - columnOrder.
     * @return List of CompanySearchDTO.
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public List<CompanySearchDto> searchCompany(final ErrorfulFieldGroup searchCompanyForm, final int start, final int end, final List<OrderByColumn> orderByColumns) throws SystemException {
        LOGGER.info("Entering searchCompany()");
        String companyId;
        String companyNo;
        String companyName;
        int companyType;
        int companyStatus;

        if (searchCompanyForm.getField(Constants.COMPANY_ID).getValue() == null) {
            companyId = StringUtils.EMPTY;
        } else {
            companyId = searchCompanyForm.getField(Constants.COMPANY_ID).getValue().toString().trim();
        }
        if (searchCompanyForm.getField(Constants.COMPANY_NO).getValue() == null) {
            companyNo = StringUtils.EMPTY;
        } else {

            companyNo = searchCompanyForm.getField(Constants.COMPANY_NO).getValue().toString().trim();
        }
        if (searchCompanyForm.getField(Constants.COMPANY_NAME).getValue() == null) {
            companyName = StringUtils.EMPTY;
        } else {
            companyName = searchCompanyForm.getField(Constants.COMPANY_NAME).getValue().toString().trim();
        }

        if (searchCompanyForm.getField(Constants.COMPANY_TYPE).getValue() == null || ((HelperDTO) searchCompanyForm.getField(Constants.COMPANY_TYPE).getValue()).getId() == 0) {
            companyType = 0;
        } else {
            companyType = ((HelperDTO) searchCompanyForm.getField(Constants.COMPANY_TYPE).getValue()).getId();
        }

        if (searchCompanyForm.getField(Constants.COMPANY_STATUS).getValue() == null || ((HelperDTO) searchCompanyForm.getField(Constants.COMPANY_STATUS).getValue()).getId() == 0) {
            companyStatus = 0;
        } else {
            companyStatus = ((HelperDTO) (searchCompanyForm.getField(Constants.COMPANY_STATUS).getValue())).getId();
        }

        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        if (StringUtils.isNotBlank(companyId)) {
            companyId = companyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            companyDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.COMPANY_ID, companyId));
        }
        if (StringUtils.isNotBlank(companyNo)) {
            companyNo = companyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            companyDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.COMPANY_NO, companyNo));
        }
        if (StringUtils.isNotBlank(companyName)) {
            companyName = companyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            companyDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.COMPANY_NAME, companyName));
        }

        if (companyType != 0) {
            companyDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.COMPANY_TYPE, companyType));
        }

        if (companyStatus != 0) {
            companyDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.COMPANY_STATUS, companyStatus));
        }
        List<HelperTable> helperTableList = getHelperTableByListTypeAndDescription(Constants.COMP_TYPE, Constants.MANUFACTURER.toUpperCase());
        if (!helperTableList.isEmpty()) {
            companyDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.COMPANY_TYPE, helperTableList.get(0).getHelperTableSid()));
        }
        helperTableList = getHelperTableByListTypeAndDescription(Constants.COMP_TYPE, Constants.BUNIT);
        if (!helperTableList.isEmpty()) {
            companyDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.COMPANY_TYPE, helperTableList.get(0).getHelperTableSid()));
        }

        for (final Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();

            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {

                companyDynamicQuery.addOrder(OrderFactoryUtil.asc(orderByColumn.getName()));
            } else {
                companyDynamicQuery.addOrder(OrderFactoryUtil.desc(orderByColumn.getName()));
            }
        }

        companyDynamicQuery.setLimit(start, end);
        LOGGER.info("getCompanyMasterList(companyDynamicQuery)");
        final List<CompanyMaster> list = dao.getCompanyMasterList(companyDynamicQuery);
        LOGGER.info("returns  List<CompanyMaster> size=" + list.size());
        final List<CompanySearchDto> searchList = getCustomizedSearchFormFromModel(list);
        LOGGER.info("End of searchCompany() with searchList size=" + searchList.size());
        return searchList;
    }

    public List<CompanyResultsDTO> searchCompanyHelperTableSort(final ErrorfulFieldGroup searchCompanyForm, BeanSearchCriteria searchCriteria, final int start, final int end, final List<OrderByColumn> orderByColumns) throws SystemException {
        LOGGER.info("Entering searchCompany()");
        String companyId;
        String companyNo;
        String companyName;
        int companyType;
        int companyStatus;

        if (searchCompanyForm.getField(Constants.COMPANY_ID).getValue() == null) {
            companyId = StringUtils.EMPTY;
        } else {
            companyId = searchCompanyForm.getField(Constants.COMPANY_ID).getValue().toString().trim();
        }
        if (searchCompanyForm.getField(Constants.COMPANY_NO).getValue() == null) {
            companyNo = StringUtils.EMPTY;
        } else {

            companyNo = searchCompanyForm.getField(Constants.COMPANY_NO).getValue().toString().trim();
        }
        if (searchCompanyForm.getField(Constants.COMPANY_NAME).getValue() == null) {
            companyName = StringUtils.EMPTY;
        } else {
            companyName = searchCompanyForm.getField(Constants.COMPANY_NAME).getValue().toString().trim();
        }

        if (searchCompanyForm.getField(Constants.COMPANY_TYPE).getValue() == null || StringUtils.isBlank((String.valueOf(searchCompanyForm.getField(Constants.COMPANY_TYPE).getValue())))) {
            companyType = 0;
        } else {
            companyType = ((HelperDTO) (searchCompanyForm.getField(Constants.COMPANY_TYPE).getValue())).getId();
        }

        if (searchCompanyForm.getField(Constants.COMPANY_STATUS).getValue() == null) {
            companyStatus = 0;
        } else {
            companyStatus = ((HelperDTO) (searchCompanyForm.getField(Constants.COMPANY_STATUS).getValue())).getId();
        }

        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        if (StringUtils.isNotBlank(companyId)) {
            companyId = companyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(companyNo)) {
            companyNo = companyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);

        }
        if (StringUtils.isNotBlank(companyName)) {
            companyName = companyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);

        }

        List<HelperTable> helperTableList = getHelperTableByListTypeAndDescription(Constants.COMP_TYPE, Constants.MANUFACTURER.toUpperCase());
        if (!helperTableList.isEmpty()) {
            companyDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.COMPANY_TYPE, helperTableList.get(0).getHelperTableSid()));
        }
        helperTableList = getHelperTableByListTypeAndDescription(Constants.COMP_TYPE, Constants.BUNIT);
        if (!helperTableList.isEmpty()) {
            companyDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.COMPANY_TYPE, helperTableList.get(0).getHelperTableSid()));
        }

        String column = "COMPANY_ID";
        String orderBy = Constants.ASC;

        for (final Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();

            if (orderByColumn.getName() == Constants.COMPANY_NO) {
                column = Constants.COMPANY_NO_CAPS;
            } else if (orderByColumn.getName() == Constants.COMPANY_ID) {
                column = "COMPANY_ID";
            } else if (orderByColumn.getName() == Constants.COMPANY_NAME) {
                column = Constants.COMPANY_NAME_CAPS;
            } else if (orderByColumn.getName() == Constants.COMPANY_STATUS) {
                column = "cstatus";
            } else if (orderByColumn.getName() == Constants.COMPANY_TYPE) {
                column = "ctype";
            }

            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                orderBy = Constants.ASC;
            } else {
                orderBy = "DESC";
            }
        }
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put(Constants.COMPANY_ID, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_NO, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_NAME, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_STATUS, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_TYPE, StringUtils.EMPTY);

        if (searchCriteria != null && searchCriteria.getFilters() != null) {
            for (Container.Filter filter : searchCriteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;

                    if (stringFilter.getPropertyId().equals(Constants.COMPANY_NO)) {

                        filterMap.put(Constants.COMPANY_NO, filterText);

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_ID)) {

                        filterMap.put(Constants.COMPANY_ID, filterText);

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_NAME)) {

                        filterMap.put(Constants.COMPANY_NAME, filterText);

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_TYPE)) {

                        filterMap.put(Constants.COMPANY_TYPE, stringFilter.getFilterString());

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_STATUS)) {

                        filterMap.put(Constants.COMPANY_STATUS, stringFilter.getFilterString());

                    }
                }

            }
        }

        List list = ContractMasterLocalServiceUtil.getTradingPartnerList(companyId, companyNo, companyName, companyStatus, companyType, filterMap, start, end, column, orderBy);

        LOGGER.info("getCompanyMasterList(companyDynamicQuery)");
        LOGGER.info("returns  List<CompanyMaster> size=" + list.size());
        final List<CompanyResultsDTO> searchList = getCustomizedSearchForHelper(list);
        LOGGER.info("End of searchCompany() with searchList size=" + searchList.size());
        return searchList;
    }

    /**
     * Returns the count of record that matches the SearchCompanyForm.
     *
     * @param searchCompanyForm - FieldGroup
     * @return int - count of records.
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public int getCompanySearchCount(final ErrorfulFieldGroup searchCompanyForm, final BeanSearchCriteria searchCriteria) throws SystemException {
        LOGGER.info("Entering getCompanySearchCount()");

        String companyId;
        String companyNo;
        String companyName;
        int companyStatus;
        int companyType;

        if (searchCompanyForm.getField(Constants.COMPANY_ID).getValue() == null) {
            companyId = StringUtils.EMPTY;
        } else {
            companyId = searchCompanyForm.getField(Constants.COMPANY_ID).getValue().toString().trim();
        }
        if (searchCompanyForm.getField(Constants.COMPANY_NO).getValue() == null) {
            companyNo = StringUtils.EMPTY;
        } else {

            companyNo = searchCompanyForm.getField(Constants.COMPANY_NO).getValue().toString().trim();
        }
        if (searchCompanyForm.getField(Constants.COMPANY_NAME).getValue() == null) {
            companyName = StringUtils.EMPTY;
        } else {
            companyName = searchCompanyForm.getField(Constants.COMPANY_NAME).getValue().toString().trim();
        }
        if (searchCompanyForm.getField(Constants.COMPANY_STATUS).getValue() == null) {
            companyStatus = 0;
        } else {
            companyStatus = ((HelperDTO) (searchCompanyForm.getField(Constants.COMPANY_STATUS).getValue())).getId();
        }
        if (searchCompanyForm.getField(Constants.COMPANY_TYPE).getValue() == null) {
            companyType = 0;
        } else {
            companyType = ((HelperDTO) (searchCompanyForm.getField(Constants.COMPANY_TYPE).getValue())).getId();
        }
        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);

        if (StringUtils.isNotBlank(companyId)) {
            companyId = companyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            companyDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.COMPANY_ID, companyId));
        }
        if (StringUtils.isNotBlank(companyNo)) {
            companyNo = companyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            companyDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.COMPANY_NO, companyNo));
        }
        if (StringUtils.isNotBlank(companyName)) {
            companyName = companyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            companyDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.COMPANY_NAME, companyName));
        }

        if (companyStatus != 0) {

            companyDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.COMPANY_STATUS, companyStatus));
        }

        if (companyType != 0) {

            companyDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.COMPANY_TYPE, companyType));
        }
        companyDynamicQuery.add(RestrictionsFactoryUtil.ne("inboundStatus", "D"));
        if (searchCriteria != null && searchCriteria.getFilters() != null) {
            for (Container.Filter filter : searchCriteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;

                    if (stringFilter.getPropertyId().equals(Constants.COMPANY_ID)) {
                        companyDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.COMPANY_ID, filterText));
                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_NO)) {
                        companyDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.COMPANY_NO, filterText));
                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_NAME)) {
                        companyDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.COMPANY_NAME, filterText));
                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_STATUS)) {
                        companyDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.COMPANY_STATUS, Integer.valueOf(stringFilter.getFilterString())));
                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_TYPE)) {
                        companyDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.COMPANY_TYPE, Integer.valueOf(stringFilter.getFilterString())));
                    }
                }
            }
        }
        final int searchCount = (int) dao.dynamicQueryCount(companyDynamicQuery);

        LOGGER.info("End of getCompanySearchCount method");
        return searchCount;

    }

    /**
     * Returns the List of CompanySearchDTO based on SearchCompanyForm.
     *
     * @param searchCompanyForm - FieldGroup.
     * @param start - Starting Index
     * @param end - Ending Index
     * @param orderByColumns - Order
     * @return the list
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public List<CompanySearchDto> searchCompanyName(final ErrorfulFieldGroup searchCompanyForm, BeanSearchCriteria searchCriteria, final int start, final int end, final List<OrderByColumn> orderByColumns) throws SystemException {
        LOGGER.info("Entering searchCompanyName()");
        String companyId;
        String companyNo;
        String companyName;
        int companyStatus;
        int companyType;
        if (searchCompanyForm.getField(Constants.COMPANY_ID).getValue() == null) {
            companyId = StringUtils.EMPTY;
        } else {
            companyId = searchCompanyForm.getField(Constants.COMPANY_ID).getValue().toString().trim();
        }
        if (searchCompanyForm.getField(Constants.COMPANY_NO).getValue() == null) {
            companyNo = StringUtils.EMPTY;
        } else {

            companyNo = searchCompanyForm.getField(Constants.COMPANY_NO).getValue().toString().trim();
        }
        if (searchCompanyForm.getField(Constants.COMPANY_NAME).getValue() == null) {
            companyName = StringUtils.EMPTY;
        } else {
            companyName = searchCompanyForm.getField(Constants.COMPANY_NAME).getValue().toString().trim();
        }

        if (searchCompanyForm.getField(Constants.COMPANY_STATUS).getValue() == null) {
            companyStatus = 0;
        } else {
            companyStatus = ((HelperDTO) (searchCompanyForm.getField(Constants.COMPANY_STATUS).getValue())).getId();
        }
        if (searchCompanyForm.getField(Constants.COMPANY_TYPE).getValue() == null) {
            companyType = 0;
        } else {
            companyType = ((HelperDTO) (searchCompanyForm.getField(Constants.COMPANY_TYPE).getValue())).getId();
        }

        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        if (StringUtils.isNotBlank(companyId)) {
            companyId = companyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            companyDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.COMPANY_ID, companyId));
        }
        if (StringUtils.isNotBlank(companyNo)) {
            companyNo = companyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            companyDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.COMPANY_NO, companyNo));
        }
        if (StringUtils.isNotBlank(companyName)) {
            companyName = companyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            companyDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.COMPANY_NAME, companyName));
        }

        if (companyStatus != 0) {

            companyDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.COMPANY_STATUS, companyStatus));
        }
        if (companyType != 0) {

            companyDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.COMPANY_TYPE, companyType));
        }
        companyDynamicQuery.add(RestrictionsFactoryUtil.ne("inboundStatus", "D"));
        for (final Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();

            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                companyDynamicQuery.addOrder(OrderFactoryUtil.asc(orderByColumn.getName()));
            } else {
                companyDynamicQuery.addOrder(OrderFactoryUtil.desc(orderByColumn.getName()));
            }
        }

        if (searchCriteria != null && searchCriteria.getFilters() != null) {
            for (Container.Filter filter : searchCriteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;

                    if (stringFilter.getPropertyId().equals(Constants.COMPANY_ID)) {
                        companyDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.COMPANY_ID, filterText));
                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_NO)) {
                        companyDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.COMPANY_NO, filterText));
                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_NAME)) {
                        companyDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.COMPANY_NAME, filterText));
                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_STATUS)) {
                        companyDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.COMPANY_STATUS, Integer.valueOf(stringFilter.getFilterString())));
                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_TYPE)) {
                        companyDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.COMPANY_TYPE, Integer.valueOf(stringFilter.getFilterString())));
                    }
                }
            }
        }

        companyDynamicQuery.setLimit(start, end);
        LOGGER.info("getCompanyMasterList(companyDynamicQuery)");
        final List<CompanyMaster> list = dao.getCompanyMasterList(companyDynamicQuery);
        LOGGER.info("returns  List<CompanyMaster> size=" + list.size());
        final List<CompanySearchDto> searchList = getCustomizedSearchFormFromModel(list);
        LOGGER.info("End of searchCompanyName method");
        return searchList;
    }

    /**
     * Returns the list of customized CompanySearch DTO.
     *
     * @param list - CompanyMaster Model.
     * @return List of CompanySearchDTO
     */
    public List<CompanySearchDto> getCustomizedSearchFormFromModel(final List<CompanyMaster> list) {
        LOGGER.info("Entering getCustomizedSearchFormFromModel()");
        final List<CompanySearchDto> searchItemList = new ArrayList<CompanySearchDto>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final CompanySearchDto searchCompanyForm = new CompanySearchDto();

                final CompanyMaster obj = (CompanyMaster) list.get(i);
                searchCompanyForm.setCompanySystemId(obj.getCompanyMasterSid());
                searchCompanyForm.setCompanyId(obj.getCompanyId());
                searchCompanyForm.setCompanyNo(obj.getCompanyNo());
                searchCompanyForm.setCompanyName(obj.getCompanyName());
                searchCompanyForm.setCompanyStatus(helperListUtil.getIdHelperDTOMap().get(obj.getCompanyStatus()));
                searchCompanyForm.setCompanyType(helperListUtil.getIdHelperDTOMap().get(obj.getCompanyType()));

                searchItemList.add(searchCompanyForm);

            }
        }
        LOGGER.info("End of getCustomizedSearchFormFromModel method");
        return searchItemList;
    }

    public List<CompanyResultsDTO> getCustomizedSearchForHelper(final List list) {
        LOGGER.info("Entering getCustomizedSearchForHelper()");
        final List<CompanyResultsDTO> searchItemList = new ArrayList<CompanyResultsDTO>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final CompanyResultsDTO searchCompanyForm = new CompanyResultsDTO();

                final Object[] obj = (Object[]) list.get(i);
                searchCompanyForm.setCompanySystemId((Integer) obj[0]);
                searchCompanyForm.setCompanyId(String.valueOf(obj[1]));
                if (obj[2] != null) {
                    searchCompanyForm.setCompanyNo(String.valueOf(obj[2]));
                }
                if (obj[3] != null) {
                    searchCompanyForm.setCompanyName(String.valueOf(obj[3]));
                }
                HelperDTO helper = new HelperDTO();
                if (obj[5] != null) {
                    int id = (Integer) obj[5];
                    helper.setId(id);
                    helper.setDescription(String.valueOf(obj[6]));
                    searchCompanyForm.setCompanyType(String.valueOf(obj[6]));
                }
                if (obj[4] != null) {
                    int id = (Integer) obj[4];
                    searchCompanyForm.setCompanyStatus(helperListUtil.getIdHelperDTOMap().get(id));
                }
                searchItemList.add(searchCompanyForm);

            }
        }
        LOGGER.info("End of getCustomizedSearchFormFromModel method");
        return searchItemList;
    }

    /**
     * Returns the Total Count of records.
     *
     * @return int - totalCount of records.
     * @throws SystemException
     */
    public int getTotalCount() throws SystemException {
        int totalCount;
        LOGGER.info("entering getTotalCount()");
        totalCount = dao.getCompanyMasterCount();
        LOGGER.info("End of getTotalCount method");
        return totalCount;
    }

    /**
     *
     * @return @throws SystemException
     */
    public List<HelperDTO> getTradingCompanyType() throws SystemException {
        LOGGER.info("Entering getTradingCompanyType()");
        List<CompanyMaster> list;
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.COMPANY_TYPE, Constants.MANUFACTURER));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.COMPANY_TYPE, Constants.BUNIT));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(Constants.COMPANY_TYPE));
        LOGGER.info("getCompanyMasterList(companyDynamicQuery)");
        list = dao.getCompanyMasterList(cfpDynamicQuery);
        LOGGER.info("returns  List<CompanyMaster> size=" + list.size());

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final CompanyMaster companyMaster = list.get(i);
                helperList.add(new HelperDTO(companyMaster.getCompanyMasterSid(), String.valueOf(companyMaster.getCompanyType())));
            }
        }
        LOGGER.info("End of getTradingCompanyType method");
        return helperList;
    }

    /**
     * Gets the dao.
     *
     * @return the dao
     */
    public CompanyFamilyplanDAO getDao() {
        return dao;
    }

    /**
     * getting count for Company.
     *
     * @param filterText the filter text
     * @return the lazy company count
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public int getLazyCompanyTypeCount(String filterText) throws PortalException, SystemException {
        filterText = StringUtils.trimToEmpty(filterText) + Constants.PERCENT;
        LOGGER.info("Entering getLazyManufacturerCount method with filterText" + filterText);
        long count = 0;
        final DynamicQuery helperTableDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        final Criterion criterion = RestrictionsFactoryUtil.ilike(Constants.DESCRIPTION, Constants.MANUFACTURER);
        helperTableDynamicQuery.add(RestrictionsFactoryUtil.not(criterion));
        final Criterion bUnitCriterion = RestrictionsFactoryUtil.ilike(Constants.DESCRIPTION, Constants.BUNIT);
        helperTableDynamicQuery.add(RestrictionsFactoryUtil.not(bUnitCriterion));
        helperTableDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.LIST_NAME, Constants.COMP_TYPE));
        helperTableDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.LIST_NAME, filterText));
        count = dao.companyTypeQueryCount(helperTableDynamicQuery);
        LOGGER.info("Ending getLazyManufacturerCount method : returning count :" + count);
        return (int) count;
    }

    /**
     * getting results for Company.
     *
     * @param start the start
     * @param end the end
     * @param filterText the filter text
     * @param manufactureId
     * @return the lazy company qualifier name results
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public List<HelperDTO> getLazyCompanyTypeResults(final int start, final int end, String filterText, HelperDTO companyId) throws PortalException, SystemException {
        filterText = StringUtils.trimToEmpty(filterText) + Constants.PERCENT;
        LOGGER.info("Entering getLazyManufacturerResults method with start= " + start + " , end= " + end + " and filterText" + filterText);
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final DynamicQuery helperTableDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        helperTableDynamicQuery.setLimit(start, end);

        final Criterion criterion = RestrictionsFactoryUtil.ilike(Constants.DESCRIPTION, Constants.MANUFACTURER);
        helperTableDynamicQuery.add(RestrictionsFactoryUtil.not(criterion));
        final Criterion bUnitCriterion = RestrictionsFactoryUtil.ilike(Constants.DESCRIPTION, Constants.BUNIT);
        helperTableDynamicQuery.add(RestrictionsFactoryUtil.not(bUnitCriterion));
        helperTableDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.LIST_NAME, Constants.COMP_TYPE));
        helperTableDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.LIST_NAME, filterText));
        final List<HelperTable> list = companyTypeLogic.getHelperTableList(helperTableDynamicQuery);
        HelperDTO helperTable;
        if (start == Constants.ZERO) {
            helperTable = new HelperDTO(Constants.SELECT_ONE);
            helperList.add(helperTable);

        }
        if (list != null) {

            for (int i = 0; i < list.size(); i++) {
                helperTable = new HelperDTO(list.get(i).getHelperTableSid(), list.get(i).getDescription());
                helperList.add(helperTable);
            }

        }
        LOGGER.info("Ending getLazyManufacturerResults method : returning CompanyQualifier size :" + list.size());
        return helperList;
    }

    public DynamicQuery getManufacturerDynamicQuery(String filterText) throws SystemException {
        List<HelperTable> helperTableList = getHelperTableByListTypeAndDescription(Constants.COMP_TYPE, Constants.MANUFACTURER.toUpperCase());
        int manufacturerCode = 0;
        if (helperTableList != null && !helperTableList.isEmpty()) {
            manufacturerCode = helperTableList.get(0).getHelperTableSid();
        }
        final DynamicQuery manufacturerDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        manufacturerDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.COMPANY_TYPE, manufacturerCode));
        manufacturerDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.COMPANY_ID, filterText));

        return manufacturerDynamicQuery;
    }

    /**
     * getting count for Company.
     *
     * @param filterText the filter text
     * @return the lazy company count
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public int getLazyManufacturerCount(String filterText) throws PortalException, SystemException {
        filterText = StringUtils.trimToEmpty(filterText) + Constants.PERCENT;
        LOGGER.info("Entering getLazyManufacturerCount method with filterText" + filterText);
        final DynamicQuery manufacturerDynamicQuery = getManufacturerDynamicQuery(filterText);
        manufacturerDynamicQuery.add(RestrictionsFactoryUtil.ne("inboundStatus", "D"));
        manufacturerDynamicQuery.setProjection(ProjectionFactoryUtil.count(Constants.COMPANY_MASTER_SID));
        List<CompanyMaster> list = dao.getCompanyMasterList(manufacturerDynamicQuery);
        LOGGER.info("Ending getLazyManufacturerCount method : returning count :" + String.valueOf(list.get(0)));
        return Integer.parseInt(String.valueOf(list.get(0)));
    }

    /**
     * getting results for Company.
     *
     * @param start the start
     * @param end the end
     * @param filterText the filter text
     * @param manufactureId
     * @return the lazy company qualifier name results
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public List<HelperDTO> getLazyManufacturerResults(final int start, final int end, String filterText, HelperDTO manufactureId) throws PortalException, SystemException {
        filterText = StringUtils.trimToEmpty(filterText) + Constants.PERCENT;
        LOGGER.info("Entering getLazyManufacturerResults method with start= " + start + " , end= " + end + " and filterText" + filterText);
        List<CompanyMaster> list;
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        int startValue = start;
        int endValue = end;
        if (start == Constants.ZERO) {
            endValue = end - 1;
        } else {
            startValue = start - 1;
            endValue = end - 1;
        }
        /*final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        
         dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.COMPANY_TYPE, Constants.MANUFACTURER));
         dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.COMPANY_ID, filterText));
         */
        final DynamicQuery manufacturerDynamicQuery = getManufacturerDynamicQuery(filterText);
        manufacturerDynamicQuery.setLimit(startValue, endValue);
        manufacturerDynamicQuery.add(RestrictionsFactoryUtil.ne("inboundStatus", "D"));
        manufacturerDynamicQuery.addOrder(OrderFactoryUtil.asc(Constants.COMPANY_ID));
        if (manufactureId != null && manufactureId.getId() != 0) {
            manufacturerDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.COMPANY_MASTER_SID, manufactureId.getId()));
        }

        list = dao.getCompanyMasterList(manufacturerDynamicQuery);

        HelperDTO helperTable;
        if (start == Constants.ZERO) {
            helperTable = new HelperDTO(Constants.SELECT_ONE);
            helperList.add(helperTable);

        }
        if (list != null) {
            if (manufactureId != null && manufactureId.getId() != 0) {
                helperList.add(manufactureId);
            }
            for (int i = 0; i < list.size(); i++) {
                helperTable = new HelperDTO(list.get(i).getCompanyMasterSid(), list.get(i).getCompanyId());
                helperList.add(helperTable);
            }

        }
        LOGGER.info("Ending getLazyManufacturerResults method : returning CompanyQualifier size :" + list.size());
        return helperList;
    }

    public HelperDTO getCompanyId(int companySysId) throws SystemException {
        LOGGER.info("Entering getDropDownList()");
        List<CompanyMaster> list;

        HelperDTO helperTable = new HelperDTO();
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.COMPANY_MASTER_SID, companySysId));
        LOGGER.info("getCompanyMasterList(cfpDynamicQuery)");
        list = dao.getCompanyMasterList(cfpDynamicQuery);
        LOGGER.info("returns List<CompanyMaster> size=" + list.size());

        if (list != null) {

            if (list.size() > 0) {
                helperTable = new HelperDTO(list.get(0).getCompanyMasterSid(), list.get(0).getCompanyId());

            }

        }
        LOGGER.info("End of getDropDownList()");
        return helperTable;
    }

    public int getLazySelectedCompaniesCount(final BeanSearchCriteria searchCriteria, String record) throws PortalException, SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());

        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put(Constants.COMPANY_NO, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_NAME, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_STATUS, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_TYPE, StringUtils.EMPTY);
        filterMap.put(Constants.CFP_STATUS, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_ID, StringUtils.EMPTY);
        filterMap.put(Constants.TRADE_CLASS, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_CATEGORY, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_GROUP, StringUtils.EMPTY);

        if (searchCriteria != null && searchCriteria.getFilters() != null) {
            for (Container.Filter filter : searchCriteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;

                    if (stringFilter.getPropertyId().equals(Constants.COMPANY_NO)) {

                        filterMap.put(Constants.COMPANY_NO, filterText);

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_NAME)) {

                        filterMap.put(Constants.COMPANY_NAME, filterText);

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_TYPE)) {
                        String type = stringFilter.getFilterString();
                        filterMap.put(Constants.COMPANY_TYPE, type);

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_STATUS)) {
                        String status = stringFilter.getFilterString();
                        filterMap.put(Constants.COMPANY_STATUS, status);

                    } else if (stringFilter.getPropertyId().equals(Constants.CFP_STATUS)) {
                        String cfpstatus = stringFilter.getFilterString();
                        filterMap.put(Constants.CFP_STATUS, cfpstatus);

                    } else if (stringFilter.getPropertyId().equals(Constants.TRADE_CLASS)) {
                        String tradeClass = stringFilter.getFilterString();
                        filterMap.put(Constants.TRADE_CLASS, tradeClass);

                    } else if (stringFilter.getPropertyId().equals("companyCategory")) {
                        String category = stringFilter.getFilterString();
                        filterMap.put("companyCategory", category);

                    } else if (stringFilter.getPropertyId().equals("companyGroup")) {
                        String group = stringFilter.getFilterString();
                        filterMap.put("companyGroup", group);
                    } else {
                        filterMap.put(stringFilter.getPropertyId().toString(), filterText);
                    }
                } else if (filter instanceof Between) {

                    Between betweenFilter = (Between) filter;
                    Date fromDate = (Date) betweenFilter.getStartValue();
                    Date toDate = (Date) betweenFilter.getEndValue();
                    if (betweenFilter.getPropertyId().equals("companyStartDate")) {
                        filterMap.put("companyStartDate-from", fromDate);
                        filterMap.put("companyStartDate-to", toDate);

                    } else if (betweenFilter.getPropertyId().equals("companyEndDate")) {

                        filterMap.put("companyEndDate-from", fromDate);
                        filterMap.put("companyEndDate-to", toDate);

                    } else if (betweenFilter.getPropertyId().equals("companyFamilyPlanStartDate")) {

                        filterMap.put("companyFamilyPlanStartDate-from", fromDate);
                        filterMap.put("companyFamilyPlanStartDate-to", toDate);

                    } else if (betweenFilter.getPropertyId().equals("companyFamilyPlanEndDate")) {

                        filterMap.put("companyFamilyPlanEndDate-from", fromDate);
                        filterMap.put("companyFamilyPlanEndDate-to", toDate);

                    } else if (betweenFilter.getPropertyId().equals("attachedDate")) {

                        filterMap.put("attachedDate-from", fromDate);
                        filterMap.put("attachedDate-to", toDate);

                    }
                }

            }
        }
        if (!StringUtils.isBlank(record)) {
            if (record.contains("Current")) {
                filterMap.put("Current", ContractUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
            if (record.contains("History")) {
                filterMap.put("History", ContractUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
            if (record.contains("Future")) {
                filterMap.put("Future", ContractUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
        }

        final List<Object> returnList = ImtdCfpDetailsLocalServiceUtil.getSelectedCompanies(userId, sessionId, 0, 0, null, null, true, null, filterMap, true);
        return Integer.valueOf(String.valueOf(returnList.get(0)));
    }

    public List<CFPCompanyDTO> getLazySelectedCompaniesDeatils(int start, int end, Boolean flag, final List<OrderByColumn> list, final BeanSearchCriteria searchCriteria, boolean isCount, String record, boolean remove) throws PortalException, SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());

        String column = Constants.COMPANY_NO_CAPS;
        String orderBy = Constants.ASC;

        if (list != null) {
            for (final Iterator<OrderByColumn> iterator = list.iterator(); iterator.hasNext();) {
                final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();

                if (Constants.COMPANY_NO.equals(orderByColumn.getName())) {
                    column = Constants.COMPANY_NO_CAPS;
                } else if (Constants.COMPANY_NAME.equals(orderByColumn.getName())) {
                    column = Constants.COMPANY_NAME_CAPS;
                } else if (Constants.COMPANY_STATUS.equals(orderByColumn.getName())) {
                    column = "cstatus";
                } else if (Constants.COMPANY_TYPE.equals(orderByColumn.getName())) {
                    column = "ctype";
                } else if (Constants.COMPANY_ID.equals(orderByColumn.getName())) {
                    column = "COMPANY_ID";
                } else if (Constants.TRADE_CLASS.equals(orderByColumn.getName())) {
                    column = "ctrade";
                } else if ("companyCategory".equals(orderByColumn.getName())) {
                    column = "ccategory";
                } else if ("companyGroup".equals(orderByColumn.getName())) {
                    column = "cgroup";
                }

                if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                    orderBy = Constants.ASC;
                } else {
                    orderBy = "DESC";
                }
            }

        }

        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put(Constants.COMPANY_NO, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_NAME, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_STATUS, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_TYPE, StringUtils.EMPTY);
        filterMap.put(Constants.CFP_STATUS, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_ID, StringUtils.EMPTY);
        filterMap.put(Constants.TRADE_CLASS, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_CATEGORY, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_GROUP, StringUtils.EMPTY);

        if (searchCriteria != null && searchCriteria.getFilters() != null) {
            for (Container.Filter filter : searchCriteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;

                    if (stringFilter.getPropertyId().equals(Constants.COMPANY_NO)) {

                        filterMap.put(Constants.COMPANY_NO, filterText);

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_NAME)) {

                        filterMap.put(Constants.COMPANY_NAME, filterText);

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_TYPE)) {
                        String type = stringFilter.getFilterString();
                        filterMap.put(Constants.COMPANY_TYPE, type);

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_STATUS)) {
                        String status = stringFilter.getFilterString();
                        filterMap.put(Constants.COMPANY_STATUS, status);

                    } else if (stringFilter.getPropertyId().equals(Constants.CFP_STATUS)) {
                        String cfpstatus = stringFilter.getFilterString();
                        filterMap.put(Constants.CFP_STATUS, cfpstatus);

                    } else if (stringFilter.getPropertyId().equals(Constants.TRADE_CLASS)) {
                        String tradeClass = stringFilter.getFilterString();
                        filterMap.put(Constants.TRADE_CLASS, tradeClass);

                    } else if (stringFilter.getPropertyId().equals("companyCategory")) {
                        String category = stringFilter.getFilterString();
                        filterMap.put("companyCategory", category);

                    } else if (stringFilter.getPropertyId().equals("companyGroup")) {
                        String group = stringFilter.getFilterString();
                        filterMap.put("companyGroup", group);
                    } else {
                        filterMap.put(stringFilter.getPropertyId().toString(), filterText);
                    }
                } else if (filter instanceof Between) {

                    Between betweenFilter = (Between) filter;
                    Date fromDate = (Date) betweenFilter.getStartValue();
                    Date toDate = (Date) betweenFilter.getEndValue();
                    if (betweenFilter.getPropertyId().equals("companyStartDate")) {
                        filterMap.put("companyStartDate-from", fromDate);
                        filterMap.put("companyStartDate-to", toDate);

                    } else if (betweenFilter.getPropertyId().equals("companyEndDate")) {

                        filterMap.put("companyEndDate-from", fromDate);
                        filterMap.put("companyEndDate-to", toDate);

                    } else if (betweenFilter.getPropertyId().equals("companyFamilyPlanStartDate")) {

                        filterMap.put("companyFamilyPlanStartDate-from", fromDate);
                        filterMap.put("companyFamilyPlanStartDate-to", toDate);

                    } else if (betweenFilter.getPropertyId().equals("companyFamilyPlanEndDate")) {

                        filterMap.put("companyFamilyPlanEndDate-from", fromDate);
                        filterMap.put("companyFamilyPlanEndDate-to", toDate);

                    } else if (betweenFilter.getPropertyId().equals("attachedDate")) {

                        filterMap.put("attachedDate-from", fromDate);
                        filterMap.put("attachedDate-to", toDate);

                    }
                }

            }
        }
        if (!StringUtils.isBlank(record)) {
            if (record.contains("Current")) {
                filterMap.put("Current", ContractUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
            if (record.contains("History")) {
                filterMap.put("History", ContractUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
            if (record.contains("Future")) {
                filterMap.put("Future", ContractUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
        }
        if (remove) {
            filterMap.put("Remove", "remove");
        }
        final List<Object[]> returnList = ImtdCfpDetailsLocalServiceUtil.getSelectedCompanies(userId, sessionId, start, end, column, orderBy, flag, null, filterMap, isCount);

        List<CFPCompanyDTO> companyList = new ArrayList<CFPCompanyDTO>();
        LOGGER.info("selected results :" + returnList.size());
        return getCustomizedCompanyMasterDTO(returnList, companyList, flag, record);
    }

   public static List<CFPCompanyDTO> getCustomizedCompanyMasterDTO(List<Object[]> returnList, List<CFPCompanyDTO> companyList, Boolean flag, String record) {
        CFPCompanyDTO companyDTO = new CFPCompanyDTO();
        try {
            final SimpleDateFormat tempFromat = new SimpleDateFormat(CommonUtils.MMDDYYYY);
            Map<Integer, String> userMap = StplSecurity.getUserName();
            for (Object[] tempCfp : returnList) {
                companyDTO = new CFPCompanyDTO();
                int j = 0;
                companyDTO.setCompanySystemId(String.valueOf(tempCfp[j++]));
                companyDTO.setCompanyId(String.valueOf(tempCfp[j++]));
                companyDTO.setCompanyType(DashBoardLogic.loadDescription(Integer.valueOf(String.valueOf(tempCfp[j++]))));
                companyDTO.setCompanyNo(String.valueOf(tempCfp[j++]));
                companyDTO.setCompanyName(String.valueOf(tempCfp[j++]));
                companyDTO.setCompanyStatus(DashBoardLogic.loadDescription(Integer.valueOf(String.valueOf(tempCfp[j++]))));
                companyDTO.setTempCFPSystemID(String.valueOf(tempCfp[j++]));
                String category = String.valueOf(tempCfp[j++]==null?StringUtils.EMPTY:tempCfp[j-1]);
                if (!ConstantUtil.SELECT_ONE.equals(category)&&category!=null) {
                    companyDTO.setCompanyCategory(category);
                } else {
                    companyDTO.setCompanyCategory(StringUtils.EMPTY);
                }
                String group = String.valueOf(tempCfp[j++]==null?StringUtils.EMPTY:tempCfp[j-1]);
                if (!ConstantUtil.SELECT_ONE.equals(group)&&group!=null) {
                    companyDTO.setCompanyGroup(group);
                } else {
                    companyDTO.setCompanyGroup(StringUtils.EMPTY);
                }
                String tradeClass = String.valueOf(tempCfp[j++]==null?StringUtils.EMPTY:tempCfp[j-1]);
                if (!ConstantUtil.SELECT_ONE.equals(tradeClass)&&tradeClass!=null) {
                    companyDTO.setTradeClass(tradeClass);
                } else {
                    companyDTO.setTradeClass(StringUtils.EMPTY);
                }
                if (!ConstantUtil.SELECT_ONE.equals(tradeClass)&&tradeClass!=null) {
                    companyDTO.setCfpDetailsTradeClass(tradeClass);
                } else {
                    companyDTO.setCfpDetailsTradeClass(StringUtils.EMPTY);
                }
                companyDTO.setCreatedDate(tempFromat.format(tempCfp[j++]));
                companyDTO.setCreatedBy(userMap.get(Integer.valueOf(String.valueOf(tempCfp[j++]))));
                companyDTO.setModifiedDate(tempFromat.format(tempCfp[j++]));
                companyDTO.setModifiedBy(userMap.get(Integer.valueOf(String.valueOf(tempCfp[j++]))));
                companyDTO.setCfpDetailsModifiedBy(userMap.get(Integer.valueOf(String.valueOf(tempCfp[j-1]))));
                if (flag) {
                    Object startDate = tempCfp[j++];
                    if (startDate != null) {
                        companyDTO.setCompanyStartDate((Date) startDate);
                    }
                    Object ob = tempCfp[j++];
                    if (ob != null) {
                        companyDTO.setCompanyEndDate((Date) ob);
                    }
                    final Object date = tempCfp[j++];
                    if (date != null) {
                        companyDTO.setCompanyFamilyPlanStartDate((Date) date);
                    }
                    final Object EndDate = tempCfp[j++];
                    if (EndDate != null) {
                        companyDTO.setCompanyFamilyPlanEndDate((Date) EndDate);
                    }
                    Object status = tempCfp[j++];
                    if (status != null) {
                        String str = String.valueOf(status);
                        companyDTO.setCompanyFamilyPlanStatus(DashBoardLogic.loadDescription(Integer.valueOf(str)));
                    }
                    Object attached = tempCfp[j++];
                    if (attached != null) {
                        companyDTO.setAttachedDate((Date) attached);
                    }
                    companyDTO.setCheckbox((Boolean) tempCfp[j++]);
                }
                if (!StringUtils.isBlank(record)) {
                    if (record.contains("Current")) {
                        companyDTO.setRecordType("Current");
                    } else if (record.contains("History")) {
                        companyDTO.setRecordType("History");
                    } else if (record.contains("Future")) {
                        companyDTO.setRecordType("Future");
                    } else {
                        companyDTO.setRecordType(StringUtils.EMPTY);
                    }
                } else {
                    companyDTO.setRecordType(StringUtils.EMPTY);
                }
                companyList.add(companyDTO);
            }
        } catch (Exception e) {
            LOGGER.error(e);;
        }
        return companyList;
    } 

    public void loadTempCFP(Object cfpModelSId, Object contractSystemId) throws SystemException {
        final VaadinSession current = VaadinSession.getCurrent();
        final String userId = String.valueOf(current.getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String tempDate = String.valueOf(sessionDTO.getSessionDate());
        final List<Object> input = new ArrayList<Object>(5);
        input.add(userId);
        input.add(sessionId);
        input.add(tempDate);
        input.add(cfpModelSId);
        input.add(contractSystemId);
        ImtdCfpDetailsLocalServiceUtil.deleteAll(input, "Load");
        ImtdCfpDetailsLocalServiceUtil.loadTempCompanydetails(input, StringUtils.EMPTY);
    }

    public void addToTempCFP(Object searchField, String searchValue) throws SystemException {
        final VaadinSession current = VaadinSession.getCurrent();
        final String userId = String.valueOf(current.getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String cfpSystemId = String.valueOf(sessionDTO.getCfpSystemId());
        final String contractSystemId = String.valueOf(sessionDTO.getContractSystemId());
        final String tempDate = String.valueOf(sessionDTO.getSessionDate());
        final SimpleDateFormat tempFromat = new SimpleDateFormat(CommonUtils.MMDDYYYY);
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdCfpDetails.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq("usersSid", userId));
        dynamicQuery.add(RestrictionsFactoryUtil.eq("sessionId", sessionId));
        dynamicQuery.add(RestrictionsFactoryUtil.ne("operation", "D"));
        dynamicQuery.setProjection(ProjectionFactoryUtil.property("companyMasterSid"));
        final List<Integer> list = ImtdCfpDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
        final Map<String, String> map = new HashMap<String, String>();
        map.put("Company ID", Constants.COMPANY_ID_CAPS);
        map.put("Company No", Constants.COMPANY_NO_CAPS);
        map.put("Company Name", Constants.COMPANY_NAME_CAPS);
        map.put("Company Type", Constants.COMP_TYPE);
        map.put("Company Status", Constants.COMPANY_STATUS_CAPS);
        final List<Object> input = new ArrayList<Object>(13);
        input.add(String.valueOf(map.get(searchField)));
        input.add(searchValue.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT));
        input.add(cfpSystemId);
        input.add(contractSystemId);
        input.add(tempDate);
        input.add(userId);
        input.add(sessionId);
        input.add(tempDate);
        input.add(userId);
        input.add(tempFromat.format(new Date()));
        input.add(userId);
        input.add(tempFromat.format(new Date()));
        ImtdCfpDetailsLocalServiceUtil.loadTempCompanydetails(input, "Add");
    }

    public void clearTempCFP() throws SystemException {
        final VaadinSession current = VaadinSession.getCurrent();
        final String userId = String.valueOf(current.getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final List<Object> input = new ArrayList<Object>(5);
        input.add(userId);
        input.add(sessionId);
        ImtdCfpDetailsLocalServiceUtil.deleteAll(input, "Back");
    }

    public void populateToTempCFP(Object populateField, Object populateValue, Boolean flag) throws SystemException {
        final VaadinSession current = VaadinSession.getCurrent();
        final String userId = String.valueOf(current.getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final SimpleDateFormat tempFormat = new SimpleDateFormat(CommonUtils.MMDDYYYY);
        final Map<String, String> map = new HashMap<String, String>();
        map.put("CFP Start Date", "CFP_DETAILS_START_DATE");
        map.put("CFP End Date", "CFP_DETAILS_END_DATE");
        map.put("CFP Status", "CFP_DETAILS_ATTACHED_STATUS");
        map.put("CheckBox", "CHECK_RECORD");
        final List<Object> input = new ArrayList<Object>(5);
        input.add(map.get(populateField.toString()));
        input.add(populateValue);
        input.add(userId);
        input.add(tempFormat.format(new Date()));
        input.add(userId);
        input.add(sessionId);
        if (flag) {
            ImtdCfpDetailsLocalServiceUtil.massPopulateAll(input, null);
        } else {
            ImtdCfpDetailsLocalServiceUtil.massPopulate(input, null);
        }
    }

    public static Boolean saveToTempCFP(List<CFPCompanyDTO> saveList) throws PortalException, SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        List<ImtdCfpDetails> saveDetailsList = new ArrayList<ImtdCfpDetails>();

        for (CFPCompanyDTO temp : saveList) {
            ImtdCfpDetails tempResult = ImtdCfpDetailsLocalServiceUtil.getImtdCfpDetails(Integer.parseInt(temp.getTempCFPSystemID()));
            tempResult.setCfpDetailsAttachedStatus(0);
            tempResult.setCfpDetailsStartDate(temp.getCompanyFamilyPlanStartDate());
            tempResult.setCfpDetailsEndDate(temp.getCompanyFamilyPlanEndDate());
            tempResult.setCfpDetailsAttachedDate(temp.getAttachedDate());
            tempResult.setCfpDetailsAttachedStatus(DashBoardLogic.getHelperCode(Constants.STATUS, temp.getCompanyFamilyPlanStatus()));
            tempResult.setCheckRecord(temp.getCheckbox());
            tempResult.setModifiedBy(Integer.valueOf(userId));
            tempResult.setModifiedDate(new Date());
            saveDetailsList.add(tempResult);
        }
        List<Object> input = new ArrayList<Object>(1);
        input.add(saveDetailsList);
        return ImtdCfpDetailsLocalServiceUtil.saveCompany(input, "SaveDetails");
    }

    public void removeAll() throws SystemException {
        final VaadinSession current = VaadinSession.getCurrent();
        final String userId = String.valueOf(current.getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final List<Object> input = new ArrayList<Object>(5);
        input.add(userId);
        input.add(sessionId);
        ImtdCfpDetailsLocalServiceUtil.updateAll(input, "Temp");
    }

    public void removeCompany(int tempCfpSystemId) throws SystemException, PortalException {
        final VaadinSession current = VaadinSession.getCurrent();
        final String userId = String.valueOf(current.getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final List<Object> input = new ArrayList<Object>(5);
        input.add(userId);
        input.add(sessionId);
        ImtdCfpDetails temp = ImtdCfpDetailsLocalServiceUtil.getImtdCfpDetails(tempCfpSystemId);
        if ("U".equals(temp.getOperation())) {
            temp.setOperation("D");
            ImtdCfpDetailsLocalServiceUtil.updateImtdCfpDetails(temp);
        } else {
            ImtdCfpDetailsLocalServiceUtil.deleteImtdCfpDetails(tempCfpSystemId);
        }
    }

    public void addCompany(CompanyMasterDTO companyDto) throws SystemException, ParseException {
        if (companyDto != null) {
            final VaadinSession current = VaadinSession.getCurrent();
            final String userId = String.valueOf(current.getAttribute(Constants.USER_ID));
            final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
            final String cfpSystemId = String.valueOf(sessionDTO.getCfpSystemId());
            final String contractSystemId = String.valueOf(sessionDTO.getContractSystemId());
            final SimpleDateFormat tempFromat = new SimpleDateFormat(CommonUtils.MMDDYYYY);

            final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdCfpDetails.class);
            dynamicQuery.add(RestrictionsFactoryUtil.eq("companyMasterSid", Integer.valueOf(companyDto.getCompanySystemId())));
            dynamicQuery.add(RestrictionsFactoryUtil.eq("usersSid", userId));
            dynamicQuery.add(RestrictionsFactoryUtil.eq("sessionId", sessionId));
            dynamicQuery.add(RestrictionsFactoryUtil.ne("operation", "D"));                       
            final List<ImtdCfpDetails> list = ImtdCfpDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
            if (list.isEmpty()) {
                ImtdCfpDetails temp = ImtdCfpDetailsLocalServiceUtil.createImtdCfpDetails(0);
                temp.setCfpDetailsAttachedDate(new Date());
                temp.setCfpModelSid(Integer.parseInt(cfpSystemId));
                temp.setCompanyId(companyDto.getCompanyId());
                temp.setCompanyNo(companyDto.getCompanyNo());
                temp.setCompanyType(companyDto.getCompanyType());
                temp.setCompanyName(companyDto.getCompanyName());
                temp.setCompanyStatus(Integer.valueOf(companyDto.getCompanyStatus()));
                if (StringUtils.isNotEmpty(companyDto.getCompanyStartDate())) {
                    temp.setCompanyStartDate(tempFromat.parse(companyDto.getCompanyStartDate()));
                } else {
                    companyDto.setCompanyStartDate(tempFromat.format(new Date()));
                    temp.setCompanyStartDate(tempFromat.parse(companyDto.getCompanyStartDate()));//Need to change
                }
                if (StringUtils.isNotEmpty(companyDto.getCompanyEndDate())) {
                    temp.setCompanyEndDate(tempFromat.parse(companyDto.getCompanyEndDate()));
                } else {
                    companyDto.setCompanyEndDate(tempFromat.format(new Date()));
                    temp.setCompanyEndDate(tempFromat.parse(companyDto.getCompanyEndDate()));//Need to change
                }
                temp.setCompanyMasterSid(Integer.parseInt(companyDto.getCompanySystemId()));
                temp.setOperation("A");
                temp.setSessionId(sessionId);
                temp.setUsersSid(userId);
                temp.setCreatedBy(Integer.valueOf(userId));
                temp.setCreatedDate(new Date());
                temp.setModifiedBy(Integer.valueOf(userId));
                temp.setModifiedDate(new Date());
                temp.setImtdCreatedDate(new Date());
                 temp.setTradingPartnerContractNo(String.valueOf(getTradClassHelperId(companyDto.getTradeClass())));
                 ImtdCfpDetailsLocalServiceUtil.addImtdCfpDetails(temp);
            }
        }
    }

    public Boolean companyNullVerification(String field) throws SystemException {
        final VaadinSession current = VaadinSession.getCurrent();
        final String userId = String.valueOf(current.getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final List<Object> input = new ArrayList<Object>(6);
        input.add(userId);
        input.add(sessionId);
        input.add(field);
        input.add(field);
        return ImtdCfpDetailsLocalServiceUtil.saveCompany(input, "Validation");
    }

    public Boolean dateVerification(String indicator) throws SystemException {
        final VaadinSession current = VaadinSession.getCurrent();
        final String userId = String.valueOf(current.getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdCfpDetails.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.USERS_SID, userId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.SESSION_ID, sessionId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CHECK_RECORD, true));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.geProperty("cfpDetailsEndDate", "cfpDetailsStartDate"));
        ifpDynamicQuery.setProjection(ProjectionFactoryUtil.count("imtdCfpDetailsSid"));
        List<?> temp = ImtdCfpDetailsLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
        return (Integer) temp.get(0) <= 0;
    }

    public void saveCompanyDetails() throws SystemException {
        final VaadinSession current = VaadinSession.getCurrent();
        final String userId = String.valueOf(current.getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final List<Object> input = new ArrayList<Object>(6);
        input.add(userId);
        input.add(sessionId);
        input.add(userId);
        input.add(sessionId);
        input.add(userId);
        input.add(sessionId);
        ImtdCfpDetailsLocalServiceUtil.saveCompany(input, "com.contractDashboard.process.saveCFP");
    }

    public Boolean cfpValidation() throws SystemException {
        final VaadinSession current = VaadinSession.getCurrent();
        final String userId = String.valueOf(current.getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdCfpDetails.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.USERS_SID, userId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.SESSION_ID, sessionId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CHECK_RECORD, true));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne("operation", "D"));
        ifpDynamicQuery.setProjection(ProjectionFactoryUtil.count("imtdCfpDetailsSid"));
        List<?> temp = ImtdCfpDetailsLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
        int imtdCfpDetailsSid = Integer.valueOf(temp.get(0).toString());
        return imtdCfpDetailsSid <= 0;
    }

    public List<HelperTable> getHelperTableByListTypeAndDescription(final String listName, final String description) throws SystemException {
        final DynamicQuery htDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        htDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.LIST_NAME, listName));
        if (description.contains(Constants.PERCENT)) {
            htDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.DESCRIPTION, description));
        } else {
            htDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.DESCRIPTION, description));
        }
        return dao.getHelperTable(htDynamicQuery);
    }

    public Map<Integer, String> getCodeDescription(final String listName) throws PortalException, SystemException {
        Map<Integer, String> helperTableMap = new HashMap<Integer, String>();
        final List<HelperTable> list = dao.findByHelperTableDetails(listName);
        for (HelperTable helperTable : list) {
            helperTableMap.put(helperTable.getHelperTableSid(), helperTable.getDescription());
        }
        return helperTableMap;
    }

    public static Map<Integer, String> getCodeDescription() throws PortalException, SystemException {
        Map<Integer, String> helperTableMap = new HashMap<Integer, String>();
        final List<HelperTable> list = dao.getHelperTableDetailsByListName();
        for (HelperTable helperTable : list) {
            helperTableMap.put(helperTable.getHelperTableSid(), helperTable.getDescription());
        }
        return helperTableMap;
    }

    public int getLazySelectedCompaniesDetailsCount(int start, int end, Boolean flag, final List<OrderByColumn> list, final BeanSearchCriteria searchCriteria, boolean isCount) throws PortalException, SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());

        String column = Constants.COMPANY_NO_CAPS;
        String orderBy = Constants.ASC;

        if (list != null) {
            for (final Iterator<OrderByColumn> iterator = list.iterator(); iterator.hasNext();) {
                final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();

                if (Constants.COMPANY_NO.equals(orderByColumn.getName())) {
                    column = Constants.COMPANY_NO_CAPS;
                } else if (Constants.COMPANY_NAME.equals(orderByColumn.getName())) {
                    column = Constants.COMPANY_NAME_CAPS;
                } else if (Constants.COMPANY_STATUS.equals(orderByColumn.getName())) {
                    column = "cstatus";
                } else if (Constants.COMPANY_TYPE.equals(orderByColumn.getName())) {
                    column = "ctype";
                }

                if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                    orderBy = Constants.ASC;
                } else {
                    orderBy = "DESC";
                }
            }

        }

        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put(Constants.COMPANY_NO, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_NAME, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_STATUS, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_TYPE, StringUtils.EMPTY);
        filterMap.put(Constants.CFP_STATUS, StringUtils.EMPTY);
        if (searchCriteria != null && searchCriteria.getFilters() != null) {
            for (Container.Filter filter : searchCriteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;

                    if (stringFilter.getPropertyId().equals(Constants.COMPANY_NO)) {

                        filterMap.put(Constants.COMPANY_NO, filterText);

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_NAME)) {

                        filterMap.put(Constants.COMPANY_NAME, filterText);

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_TYPE)) {
                        String type = stringFilter.getFilterString();
                        filterMap.put(Constants.COMPANY_TYPE, type);

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_STATUS)) {
                        String status = stringFilter.getFilterString();
                        filterMap.put(Constants.COMPANY_STATUS, status);

                    } else if (stringFilter.getPropertyId().equals(Constants.CFP_STATUS)) {
                        String cfpstatus = stringFilter.getFilterString();
                        filterMap.put(Constants.CFP_STATUS, cfpstatus);

                    }
                } else if (filter instanceof Between) {

                    Between betweenFilter = (Between) filter;
                    Date fromDate = (Date) betweenFilter.getStartValue();
                    Date toDate = (Date) betweenFilter.getEndValue();
                    if (betweenFilter.getPropertyId().equals("companyStartDate")) {
                        filterMap.put("companyStartDate-from", fromDate);
                        filterMap.put("companyStartDate-to", toDate);

                    } else if (betweenFilter.getPropertyId().equals("companyEndDate")) {

                        filterMap.put("companyEndDate-from", fromDate);
                        filterMap.put("companyEndDate-to", toDate);

                    } else if (betweenFilter.getPropertyId().equals("companyFamilyPlanStartDate")) {

                        filterMap.put("companyFamilyPlanStartDate-from", fromDate);
                        filterMap.put("companyFamilyPlanStartDate-to", toDate);

                    } else if (betweenFilter.getPropertyId().equals("companyFamilyPlanEndDate")) {

                        filterMap.put("companyFamilyPlanEndDate-from", fromDate);
                        filterMap.put("companyFamilyPlanEndDate-to", toDate);

                    } else if (betweenFilter.getPropertyId().equals("attachedDate")) {

                        filterMap.put("attachedDate-from", fromDate);
                        filterMap.put("attachedDate-to", toDate);

                    }
                }

            }
        }

        final List<Object[]> returnList = ImtdCfpDetailsLocalServiceUtil.getSelectedCompanies(userId, sessionId, start, end, column, orderBy, flag, null, filterMap, isCount);
        Object obj = returnList.get(0);
        LOGGER.info("selected results :" + returnList.size());
        return Integer.valueOf(String.valueOf(obj));
    }

    public List<Object> getListForParentLookUp(final ErrorfulFieldGroup companyFamilyplanMaster, final BeanSearchCriteria searchCriteria, final String dbColumnName, final Boolean asc, final int start, final int end, String results) throws PortalException, SystemException {
        LOGGER.info("Entering getDynamicQuery");
        String companyFamilyPlanId = StringUtils.EMPTY;
        String companyFamilyPlanNo = StringUtils.EMPTY;
        String companyFamilyPlanName = StringUtils.EMPTY;
        String companyId = StringUtils.EMPTY;
        String companyNo = StringUtils.EMPTY;
        String companyName = StringUtils.EMPTY;
        int companyFamilyPlanType;
        int companyFamilyPlanStatus;
        Map<Object, Object> parameters = new HashMap<Object, Object>();
        List cfpList = null;
        Map<Object, Object> cfp = new HashMap<Object, Object>();
        final SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
        if (companyFamilyplanMaster.getField(FieldNameUtils.COMPANYFAMILYPLANID).getValue() == null) {
            cfp.put(ConstantsUtils.CFP_ID, StringUtils.EMPTY);
        } else {
            companyFamilyPlanId = companyFamilyplanMaster.getField(FieldNameUtils.COMPANYFAMILYPLANID).getValue().toString().trim();
        }
        if (companyFamilyplanMaster.getField(FieldNameUtils.COMPANYFAMILYPLANNO).getValue() == null) {
            cfp.put(ConstantsUtils.CFP_NO, StringUtils.EMPTY);
        } else {
            companyFamilyPlanNo = companyFamilyplanMaster.getField(FieldNameUtils.COMPANYFAMILYPLANNO).getValue().toString().trim();
        }
        if (companyFamilyplanMaster.getField(FieldNameUtils.COMPANYFAMILYPLANNAME).getValue() == null) {
            cfp.put(ConstantsUtils.CFP_NAME, StringUtils.EMPTY);
        } else {
            companyFamilyPlanName = companyFamilyplanMaster.getField(FieldNameUtils.COMPANYFAMILYPLANNAME).getValue().toString().trim();
        }

        if (companyFamilyplanMaster.getField(FieldNameUtils.COMPANYFAMILYPLANTYPE).getValue() == null) {
            cfp.put(ConstantsUtils.CFP_TYPE, 0);
        } else {
            cfp.put(ConstantsUtils.CFP_TYPE, ((com.stpl.ifs.util.HelperDTO) companyFamilyplanMaster.getField(FieldNameUtils.COMPANYFAMILYPLANTYPE).getValue()).getId());
        }
        if (companyFamilyplanMaster.getField(FieldNameUtils.COMPANYFAMILYPLANSTATUS).getValue() == null) {
            cfp.put(ConstantsUtils.CFP_STATUS, 0);
        } else {
            cfp.put(ConstantsUtils.CFP_STATUS, ((com.stpl.ifs.util.HelperDTO) companyFamilyplanMaster.getField(FieldNameUtils.COMPANYFAMILYPLANSTATUS).getValue()).getId());
        }

        if (companyFamilyplanMaster.getField(ConstantsUtils.COMPANY_ID).getValue() == null) {
            cfp.put(ConstantsUtils.COMPANY_ID, StringUtils.EMPTY);
        } else {
            companyId = companyFamilyplanMaster.getField(ConstantsUtils.COMPANY_ID).getValue().toString().trim();
        }
        if (companyFamilyplanMaster.getField(ConstantsUtils.COMPANY_NO).getValue() == null) {
            cfp.put(ConstantsUtils.COMPANY_NO, StringUtils.EMPTY);
        } else {
            companyNo = companyFamilyplanMaster.getField(ConstantsUtils.COMPANY_NO).getValue().toString().trim();
        }
        if (companyFamilyplanMaster.getField(ConstantsUtils.COMPANY_NAME).getValue() == null) {
            cfp.put(ConstantsUtils.COMPANY_NAME, StringUtils.EMPTY);
        } else {
            companyName = companyFamilyplanMaster.getField(ConstantsUtils.COMPANY_NAME).getValue().toString().trim();
        }

        if (StringUtils.isNotBlank(companyFamilyPlanId)) {
            cfp.put(ConstantsUtils.CFP_ID, companyFamilyPlanId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT));
        }
        if (StringUtils.isNotBlank(companyFamilyPlanNo)) {
            cfp.put(ConstantsUtils.CFP_NO, companyFamilyPlanNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT));
        }
        if (StringUtils.isNotBlank(companyFamilyPlanName)) {
            cfp.put(ConstantsUtils.CFP_NAME, companyFamilyPlanName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT));
        }
        if (StringUtils.isNotBlank(companyId)) {
            cfp.put(ConstantsUtils.COMPANY_ID, companyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT));
        }
        if (StringUtils.isNotBlank(companyNo)) {
            cfp.put(ConstantsUtils.COMPANY_NO, companyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT));
        }
        if (StringUtils.isNotBlank(companyName)) {
            cfp.put(ConstantsUtils.COMPANY_NAME, companyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT));
        }

        if (searchCriteria != null && searchCriteria.getFilters() != null) {
            for (Container.Filter filter : searchCriteria.getFilters()) {

                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                    if (ConstantsUtils.SYSTEM_ID.equals(stringFilter.getPropertyId())) {
                        parameters.put(ConstantsUtils.SYSTEM_ID, stringFilter.getFilterString());
                    }
                    if (ConstantsUtils.CFP_NO.equals(stringFilter.getPropertyId())) {
                        parameters.put(FieldNameUtils.COMPANYFAMILYPLANNO_SEARCH, filterString);
                    }
                    if (ConstantsUtils.CFP_ID.equals(stringFilter.getPropertyId())) {
                        parameters.put(FieldNameUtils.COMPANYFAMILYPLANID_SEARCH, filterString);
                    }
                    if (ConstantsUtils.CFP_NAME.equals(stringFilter.getPropertyId())) {
                        parameters.put(FieldNameUtils.COMPANYFAMILYPLANNAME_SEARCH, filterString);
                    }
                    if (ConstantsUtils.CFP_DESIGNATION.equals(stringFilter.getPropertyId())) {
                        parameters.put(ConstantsUtils.CFP_DESIGNATION_DUP, filterString);
                    }
                    if (ConstantsUtils.PARENT_CFP_NAME.equals(stringFilter.getPropertyId())) {
                        parameters.put(ConstantsUtils.PARENT_CFP_NAME_DUP, filterString);
                    }
                    if (ConstantsUtils.PARENT_CFP_ID.equals(stringFilter.getPropertyId())) {
                        parameters.put(ConstantsUtils.PARENT_CFP_ID_DUP, filterString);
                    }

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();

                    if (stringFilter.getValue() instanceof Integer) {
                        Integer filterValue = (Integer) stringFilter.getValue();
                        if (ConstantsUtils.CFP_STATUS.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                parameters.put(FieldNameUtils.COMPANYFAMILYPLANSTATUS_SEARCH, filterValue);
                            }
                        }
                        if (ConstantsUtils.CFP_TYPE.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                parameters.put(FieldNameUtils.COMPANYFAMILYPLANTYPE_SEARCH, filterValue);
                            }
                        }
                        if (ConstantsUtils.CFP_TRADE_CLASS.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                parameters.put(ConstantsUtils.CFP_TRADE_CLASS_DUP, filterValue);
                            }
                        }
                        if (ConstantsUtils.CFP_CATEGORY.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                parameters.put(ConstantsUtils.CFP_CATEGORY_DUP, filterValue);
                            }
                        }
                        if (ConstantsUtils.CREATEDBY.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                parameters.put(ConstantsUtils.CREATEDBY, filterValue);
                            }
                        }

                        if (ConstantsUtils.MODIFIEDBY.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                parameters.put(ConstantsUtils.MODIFIEDBY, filterValue);
                            }
                        }
                    } else if (stringFilter.getValue() instanceof Date) {
                        Date filterString = (Date) stringFilter.getValue();
                        if (ConstantsUtils.CFP_START_DATE.equals(stringFilter.getPropertyId())) {
                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                parameters.put(ConstantsUtils.CFP_START_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                parameters.put(ConstantsUtils.CFP_START_DATE_TO, String.valueOf(dateFormat.format(filterString)));
                            }
                        } else if (ConstantsUtils.CFP_END_DATE.equals(stringFilter.getPropertyId())) {
                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                parameters.put(ConstantsUtils.CFP_END_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                parameters.put(ConstantsUtils.CFP_END_DATE_TO, String.valueOf(dateFormat.format(filterString)));
                            }
                        } else if (ConstantsUtils.MODIFIEDDATE.equals(stringFilter.getPropertyId())) {
                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                parameters.put(ConstantsUtils.MODIFIED_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                parameters.put(ConstantsUtils.MODIFIED_DATE_TO, String.valueOf(dateFormat.format(filterString)));
                            }
                        } else if (ConstantsUtils.CREATEDDATE.equals(stringFilter.getPropertyId())) {
                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                parameters.put(ConstantsUtils.CREATED_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                parameters.put(ConstantsUtils.CREATED_DATE_TO, String.valueOf(dateFormat.format(filterString)));
                            }
                        }
                    }
                } else if (filter instanceof Between) {

                    Between stringFilter = (Between) filter;
                    Date filterString = (Date) stringFilter.getStartValue();
                    Date filterString1 = (Date) stringFilter.getEndValue();
                    if (ConstantsUtils.CFP_START_DATE.equals(stringFilter.getPropertyId())) {
                        parameters.put(ConstantsUtils.CFP_START_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                        parameters.put(ConstantsUtils.CFP_START_DATE_TO, String.valueOf(dateFormat.format(filterString1)));
                    }

                    if (ConstantsUtils.CFP_END_DATE.equals(stringFilter.getPropertyId())) {
                        parameters.put(ConstantsUtils.CFP_END_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                        parameters.put(ConstantsUtils.CFP_END_DATE_TO, String.valueOf(dateFormat.format(filterString1)));
                    }

                    if (ConstantsUtils.CREATEDDATE.equals(stringFilter.getPropertyId())) {
                        parameters.put(ConstantsUtils.CFP_END_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                        parameters.put(ConstantsUtils.CFP_END_DATE_TO, String.valueOf(dateFormat.format(filterString1)));
                    }

                    if (ConstantsUtils.CREATEDDATE.equals(stringFilter.getPropertyId())) {
                        parameters.put(ConstantsUtils.CREATED_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                        parameters.put(ConstantsUtils.CREATED_DATE_TO, String.valueOf(dateFormat.format(filterString1)));
                    }

                    if (ConstantsUtils.MODIFIEDDATE.equals(stringFilter.getPropertyId())) {
                        parameters.put(ConstantsUtils.MODIFIED_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                        parameters.put(ConstantsUtils.MODIFIED_DATE_TO, String.valueOf(dateFormat.format(filterString1)));
                    }
                }
            }
        }

        cfpList = dao.findCfpModelV1(cfp, dbColumnName, asc, start, end, parameters, results, null, null);

        LOGGER.info("return DynamicQuery");

        return cfpList;
    }

    /**
     * Returns total CFP count.
     *
     * @return total number of rows in CompanyFamilyplanMaster table
     * @throws StplR2Exception the stpl r2 exception
     */
    public int getCFPTotalCount() throws PortalException, SystemException {
        LOGGER.info("Entering getCFPTotalCount");
        final int count = (int) dao.getCompanyFamilyplanMasterTotalCount();
        LOGGER.info("return getCFPTotalCount -" + count);
        return count;
    }

    /**
     * Returns searched CFP list .
     *
     * @param companyFamilyplanMaster - binder object holds the value entered in
     * the screen
     * @param start - start limit
     * @param end - end limit
     * @param orderByColumns - columns going to get sorted
     * @return list of CFPDTO objects
     */
    public List<CFPSearchDTO> searchCFPForParentLookUp(final ErrorfulFieldGroup companyFamilyplanMaster, final int start, final int end, final List<OrderByColumn> orderByColumns, final BeanSearchCriteria criteria) throws PortalException, SystemException {
        LOGGER.info("Entering searchCFP p2:" + start + " p3:" + end + " p4: " + ((orderByColumns == null) ? orderByColumns : orderByColumns.size()));

        //To load column in hashMap
        CommonUtils.loadColumnName();
        String orderby = Constants.ASC;
        String column = ConstantsUtils.CFP_MODEL_SID;
        boolean asc = false;
        String columnName = StringUtils.EMPTY;
        String dbColumnName = StringUtils.EMPTY;

        CommonUtils.loadColumnNames();
        for (final Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
            columnName = orderByColumn.getName();
            dbColumnName = CommonUtils.getDbColumnNames(columnName);
            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                asc = false;
            } else {
                asc = true;
            }
        }
        final List cfpList = getListForParentLookUp(companyFamilyplanMaster, criteria, dbColumnName, asc, start, end, searchresults);

        final List<CFPSearchDTO> cfpDTO = getCustomizedSearchFormModelForParentLookUp(cfpList);
        LOGGER.info("return customized cfp size :" + ((cfpDTO == null) ? cfpDTO : cfpDTO.size()));
        return cfpDTO;
    }

    public static List<CFPSearchDTO> getCustomizedSearchFormModelForParentLookUp(final List<Object[]> cfpMasterList) throws PortalException, SystemException {
        final List<CFPSearchDTO> companyDTO = new ArrayList<CFPSearchDTO>();
        Map<Integer, String> hm = CommonUtils.getCodeDescription();
        Map<Integer, String> userMap = StplSecurity.getUserName();
        for (Object[] obj : cfpMasterList) {
            final CFPSearchDTO cfpDTOObj = new CFPSearchDTO();
            if (obj[0] != null) {
                cfpDTOObj.setCompanyFamilyPlanId(String.valueOf(obj[0]));
            }
            if (obj[1] != null) {
                cfpDTOObj.setCompanyFamilyPlanNo(String.valueOf(obj[1]));
            }
            if (obj[2] != null) {
                cfpDTOObj.setCompanyFamilyPlanName(String.valueOf(obj[2]));
            }
            if (obj[3] != null && StringUtils.isNotBlank(obj[3].toString()) && (Integer) obj[3] != 0) {
                cfpDTOObj.setCompanyFamilyPlanType(hm.get(obj[3]));
            }
            if (obj[4] != null && StringUtils.isNotBlank(obj[4].toString()) && (Integer) obj[4] != 0) {
                cfpDTOObj.setCompanyFamilyPlanStatus(hm.get(obj[4]));
            }
            if (obj[5] != null) {
                cfpDTOObj.setCompanyFamilyPlanStartDate((Date) obj[5]);
            }
            if (obj[6] != null) {
                cfpDTOObj.setCompanyFamilyPlanEndDate((Date) obj[6]);
            }
            if (obj[7] != null && StringUtils.isNotBlank(obj[7].toString()) && (Integer) obj[7] != 0) {
                cfpDTOObj.setCompanyFamilyPlanTradeClass(hm.get(obj[7]));
            }
            if (obj[8] != null && StringUtils.isNotBlank(obj[8].toString()) && (Integer) obj[8] != 0) {
                cfpDTOObj.setCompanyFamilyPlanCategory(hm.get(obj[8]));
            }
            if (obj[9] != null) {
                cfpDTOObj.setCompanyFamilyPlanDesignation(String.valueOf(obj[9]));
            }
            if (obj[10] != null) {
                cfpDTOObj.setParentCompanyFamilyPlanName(String.valueOf(obj[10]));
            }
            if (obj[11] != null) {
                cfpDTOObj.setParentCompanyFamilyPlanId(String.valueOf(obj[11]));
            }
            if (obj[16] != null) {
                cfpDTOObj.setCompanyFamilyPlanSystemId(Integer.valueOf(String.valueOf(obj[16])));
                cfpDTOObj.setSystemId(String.valueOf(obj[16]));
            }
            if (obj[17] != null) {
                cfpDTOObj.setModifiedDate((Date) obj[17]);
            }
            if (obj[18] != null) {
                cfpDTOObj.setModifiedBy(userMap.get(Integer.valueOf(String.valueOf(obj[18]))));
            }
            if (obj[19] != null) {
                cfpDTOObj.setCreatedDate((Date) obj[19]);
            }

            if (obj[20] != null) {
                cfpDTOObj.setCreatedBy(userMap.get(Integer.valueOf(String.valueOf(obj[20]))));
            }
            if (obj[21] != null) {
                cfpDTOObj.setRecordLockStatus((Boolean) obj[21]);
            }
            companyDTO.add(cfpDTOObj);
        }
        LOGGER.info("return customized company size " + companyDTO.size());

        return companyDTO;
    }

    public int isEmpty(SessionDTO sessDto) {

        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        int user = Integer.valueOf(userId);
        String sql = "select count(*) from dbo.IMTD_CFP_DETAILS ";
        sql += " WHERE USERS_SID = " + user;

        sql += " AND CHECK_RECORD = 1";
        sql += " AND SESSION_ID like '" + sessDto.getUiSessionId() + "' AND \"OPERATION\" <> 'D' ";
        List resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(sql);
        int val = resultList == null ? 0 : Integer.valueOf(String.valueOf(resultList.get(0)));
        return val;
    }
    /**
     * To get Trade class Helper Id By Its Description
     * @param Desc
     * @return 
     */
    int getTradClassHelperId(String desc) {
        if (desc == null && desc.isEmpty()) {
            return 0;
        }
        String sql = "select HELPER_TABLE_SID from dbo.HELPER_TABLE where LIST_NAME like 'COMPANY_TRADE_CLASS' \n"
                + "And description like '%" + desc + "%'";
        List resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(sql);
        return resultList == null ? 0 : Integer.valueOf(String.valueOf(resultList.get(0)));
    }

}
