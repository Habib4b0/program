package com.stpl.app.global.cfp.logic;
import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.cfp.dto.CFPCompanyDTO;
import com.stpl.app.global.cfp.dto.CFPSearchDTO;
import com.stpl.app.global.cfp.dto.SearchCFPForm;
import com.stpl.app.global.cfp.util.FieldNameUtils;
import com.stpl.app.global.cfp.util.UIUtils;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.company.dto.CompanyMasterDTO;
import com.stpl.app.global.company.util.CommonUtils;
import com.stpl.app.global.dao.impl.CFPSearchLogicDAOImpl;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.model.CfpDetails;
import com.stpl.app.model.CfpModel;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.CompanyTradeClass;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ImtdCfpDetails;
import com.stpl.app.model.MasterDataFiles;
import com.stpl.app.model.impl.CfpModelImpl;
import com.stpl.app.service.CfpModelLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.CompanyTradeClassLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ImtdCfpDetailsLocalServiceUtil;
import com.stpl.app.service.MasterDataFilesLocalServiceUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.ui.StplR2Exception;
import com.stpl.app.ui.UDCIncrementCheck;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonLazyUtilDTO;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.HelperDTO;
import com.stpl.app.util.NotesTabLogic;
import com.stpl.domain.global.companyfamilyplan.CompanyFamilyplanDAO;
import com.stpl.domain.global.companyfamilyplan.CompanyFamilyplanLogic;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

// TODO: Auto-generated Javadoc
/**
 * This class contains all business logic methods for the Company Family Plan.
 *
 * @author pvinoth
 */
public class CFPSearchLogic extends BeanItemContainer<SearchCFPForm> implements CompanyFamilyplanLogic{

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CFPSearchLogic.class);
    /**
     * The CFPSEARCHLOGICDAO.
     */
    private static final CompanyFamilyplanDAO CFPSEARCHLOGICDAO = new CFPSearchLogicDAOImpl();

    private NotesTabLogic notesLogic = new NotesTabLogic();

    private static SimpleDateFormat sdf = new SimpleDateFormat(ConstantsUtils.YMD_FORMAT);

    private static HelperListUtil helperListUtil=HelperListUtil.getInstance();

    //Java date format
    public static String DEFAULT_JAVA_DATE_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";
    //SQL date format
    public static String DEFAULT_SQL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    private static String searchresults = "searchResults";
    private static String Company_no = "COMPANY_NO";
    private static String Company_Id = "COMPANY_ID";
    private static String Ctype = "ctype";
    private static String Cstatus = "cstatus";
    private static String Category = "category";
    private static String Trade = "trade";
    private static String Cgroup = "cgroup";
    SessionDTO sessionDTO;

    /**
     * Constructor.
     */
    public CFPSearchLogic() {
        super(SearchCFPForm.class);
    }
    public CFPSearchLogic(final SessionDTO sessionDTO) {
        super(SearchCFPForm.class);
        this.sessionDTO=sessionDTO;
    }

    /**
     * Returns searched CFP list .
     *
     * @param companyFamilyplanMaster - binder object holds the value entered in
     * the screen
     * @param start - start limit
     * @param end - end limit
     * @param orderByColumns - columns going to get sorted
     * @param criteria
     * @return list of CFPDTO objects
     * @throws com.stpl.portal.kernel.exception.PortalException
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public List<SearchResultsDTO> searchCFP(final ErrorfulFieldGroup companyFamilyplanMaster, final int start, final int end, final List<OrderByColumn> orderByColumns,final BeanSearchCriteria criteria) throws PortalException, SystemException, Exception {
        LOGGER.info("Entering searchCFP p2:" + start + " p3:" + end + " p4: " + ((orderByColumns == null) ? orderByColumns : orderByColumns.size()));

        //To load column in hashMap
        CommonUtils.loadColumnName();
        String orderby ="asc";
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
         final List cfpList = getList(companyFamilyplanMaster,criteria,dbColumnName, asc, start, end,searchresults);


        final List<SearchResultsDTO> cfpDTO = getCustomizedSearchFormModel(cfpList);
        LOGGER.info("return customized cfp size :" + ((cfpDTO == null) ? cfpDTO : cfpDTO.size()));
        return cfpDTO;
    }

   
    
    public List<Object> getList(final ErrorfulFieldGroup companyFamilyplanMaster,final BeanSearchCriteria searchCriteria, final String dbColumnName,final Boolean asc, final int start, final int end,String results) throws PortalException, SystemException {
        LOGGER.info("Entering getDynamicQuery");
        int cfpType=0;
        int cfpStatus=0;
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


        try{



            if (companyFamilyplanMaster.getField(ConstantsUtils.TEXT1).getValue() == null) {
                cfp.put(ConstantsUtils.CFP_ID, StringUtils.EMPTY);
            } else {
                companyFamilyPlanId = companyFamilyplanMaster.getField(ConstantsUtils.TEXT1).getValue().toString().trim();
            }
            if (companyFamilyplanMaster.getField(ConstantsUtils.TEXT2).getValue() == null) {
                cfp.put(ConstantsUtils.CFP_NO, StringUtils.EMPTY);
            } else {
                companyFamilyPlanNo = companyFamilyplanMaster.getField(ConstantsUtils.TEXT2).getValue().toString().trim();
            }
            if (companyFamilyplanMaster.getField(ConstantsUtils.TEXT3).getValue() == null) {
                cfp.put(ConstantsUtils.CFP_NAME, StringUtils.EMPTY);
            } else {
                companyFamilyPlanName = companyFamilyplanMaster.getField(ConstantsUtils.TEXT3).getValue().toString().trim();
            }

        if (companyFamilyplanMaster.getField(ConstantsUtils.COMBO1).getValue() == null || ConstantsUtils.SELECT_ONE.equals(companyFamilyplanMaster.getField(ConstantsUtils.COMBO1).getValue()) ) {
                cfp.put(ConstantsUtils.CFP_TYPE, 0);
            } else {
                final HelperDTO helperDTO = (HelperDTO) companyFamilyplanMaster.getField(ConstantsUtils.COMBO1).getValue();
                if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

               cfpType  = helperDTO.getId();
             cfp.put(ConstantsUtils.CFP_TYPE, (Integer)cfpType);

                }

            }
            if (companyFamilyplanMaster.getField(ConstantsUtils.COMBO2).getValue() == null || ConstantsUtils.SELECT_ONE.equals(companyFamilyplanMaster.getField(ConstantsUtils.COMBO2).getValue())) {
                cfp.put(ConstantsUtils.CFP_STATUS, 0);
            } else {

                final HelperDTO helperDTO = (HelperDTO) companyFamilyplanMaster.getField(ConstantsUtils.COMBO2).getValue();
                if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                    cfpStatus = helperDTO.getId();

            cfp.put(ConstantsUtils.CFP_STATUS, (Integer)cfpStatus);
                }

            }

            if (companyFamilyplanMaster.getField(ConstantsUtils.TEXT6).getValue() == null) {
                cfp.put(ConstantsUtils.COMPANY_ID, StringUtils.EMPTY);
            } else {
                companyId = companyFamilyplanMaster.getField(ConstantsUtils.TEXT6).getValue().toString().trim();
            }
            if (companyFamilyplanMaster.getField(ConstantsUtils.TEXT7).getValue() == null) {
                cfp.put(ConstantsUtils.COMPANY_NO, StringUtils.EMPTY);
            } else {
                companyNo = companyFamilyplanMaster.getField(ConstantsUtils.TEXT7).getValue().toString().trim();
            }
            if (companyFamilyplanMaster.getField(ConstantsUtils.TEXT8).getValue() == null) {
                cfp.put(ConstantsUtils.COMPANY_NAME, StringUtils.EMPTY);
            } else {
                companyName = companyFamilyplanMaster.getField(ConstantsUtils.TEXT8).getValue().toString().trim();
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
                        String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;
                        if (ConstantsUtils.CFP_SYSTEM_ID.equals(stringFilter.getPropertyId())) {
                            parameters.put(ConstantsUtils.CFP_SYSTEM_ID, stringFilter.getFilterString());
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
                            if (ConstantsUtils.CFP_CREATED_BY_DUP.equals(stringFilter.getPropertyId())) {
                                if (filterValue != 0) {
                                    parameters.put(ConstantsUtils.CFP_CREATED_BY_DUP, filterValue);
                                }
                            }

                            if (ConstantsUtils.CFP_MODIFIED_BY_DUP.equals(stringFilter.getPropertyId())) {
                                if (filterValue != 0) {
                                    parameters.put(ConstantsUtils.CFP_MODIFIED_BY_DUP, filterValue);
                                }
                            }

                        
                        
                        } else if (stringFilter.getValue() instanceof Date) {

                            Date filterString = (Date) stringFilter.getValue();
                            if (ConstantsUtils.CFP_START_DATE.equals(stringFilter.getPropertyId())) {
                                if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                parameters.put(ConstantsUtils.CFP_START_DATE_FROM , String.valueOf(dateFormat.format(filterString)));
                                } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                    parameters.put(ConstantsUtils.CFP_START_DATE_TO, String.valueOf(dateFormat.format(filterString)));
                                }
                            } else if (ConstantsUtils.CFP_END_DATE.equals(stringFilter.getPropertyId())) {
                                if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                    parameters.put(ConstantsUtils.CFP_END_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                                } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                parameters.put(ConstantsUtils.CFP_END_DATE_TO , String.valueOf(dateFormat.format(filterString)));
                                }
                        }else if (ConstantsUtils.CFP_MODIFIED_DATE.equals(stringFilter.getPropertyId())) {
                                if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                    parameters.put(ConstantsUtils.MODIFIED_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                                } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                    parameters.put(ConstantsUtils.MODIFIED_DATE_TO, String.valueOf(dateFormat.format(filterString)));
                                }
                        }else if (ConstantsUtils.CFP_CREATED_DATE.equals(stringFilter.getPropertyId())) {
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
                        parameters.put(ConstantsUtils.CFP_START_DATE_FROM , String.valueOf(dateFormat.format(filterString)));
                            parameters.put(ConstantsUtils.CFP_START_DATE_TO, String.valueOf(dateFormat.format(filterString1)));
                        }

                        if (ConstantsUtils.CFP_END_DATE.equals(stringFilter.getPropertyId())) {
                            parameters.put(ConstantsUtils.CFP_END_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                        parameters.put(ConstantsUtils.CFP_END_DATE_TO , String.valueOf(dateFormat.format(filterString1)));
                        }

                        if (ConstantsUtils.CFP_CREATED_DATE.equals(stringFilter.getPropertyId())) {
                            parameters.put(ConstantsUtils.CFP_END_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                        parameters.put(ConstantsUtils.CFP_END_DATE_TO , String.valueOf(dateFormat.format(filterString1)));

                        }

                        if (ConstantsUtils.CFP_CREATED_DATE.equals(stringFilter.getPropertyId())) {
                            parameters.put(ConstantsUtils.CREATED_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                            parameters.put(ConstantsUtils.CREATED_DATE_TO, String.valueOf(dateFormat.format(filterString1)));

                        }

                        if (ConstantsUtils.CFP_MODIFIED_DATE.equals(stringFilter.getPropertyId())) {
                            parameters.put(ConstantsUtils.MODIFIED_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                            parameters.put(ConstantsUtils.MODIFIED_DATE_TO, String.valueOf(dateFormat.format(filterString1)));

                        }

                    

                    }

                }
            }

            cfpList = CFPSEARCHLOGICDAO.findCfpModelV1(cfp, dbColumnName, asc, start, end, parameters, results, null, null);

        }catch (Exception e)
        {
            LOGGER.error(e);

        }
        LOGGER.info("return DynamicQuery");

        return cfpList;
    }

    /**
     * Returns the result list count.
     *
     * @param dynamicQuerySearch - Dynamic Query of CompanyFamilyplanMaster
     * @return no of rows in table
     * @throws StplR2Exception the stpl r2 exception
     */
    public int getDynamicQueryCount(final DynamicQuery dynamicQuerySearch) throws PortalException, SystemException {
        LOGGER.info("Entring getDynamicQueryCount");
        final int count = (int) CFPSEARCHLOGICDAO.getCompanyFamilyplanMasterQueryCount(dynamicQuerySearch);
        LOGGER.info("return getDynamicQueryCount -" + count);
        return count;
    }

    /**
     * Returns total CFP count.
     *
     * @return total number of rows in CompanyFamilyplanMaster table
     * @throws StplR2Exception the stpl r2 exception
     */
    public int getCFPTotalCount() throws PortalException, SystemException {
        LOGGER.info("Entering getCFPTotalCount");
        final int count = (int) CFPSEARCHLOGICDAO.getCompanyFamilyplanMasterTotalCount();
        LOGGER.info("return getCFPTotalCount -" + count);
        return count;
    }

    /**
     * Returns the exact list to load.
     *
     * @param startIndex - start limit
     * @param endIndex - end limit
     * @return list of CFPDTO object
     * @throws StplR2Exception the stpl r2 exception
     */
    public List<CFPSearchDTO> loadCFP(final int startIndex, final int endIndex) throws PortalException, SystemException {
        LOGGER.info("Entering loadCFP p1:" + startIndex + " p2:" + endIndex);
        final List<CfpModel> list = CFPSEARCHLOGICDAO.getCompanyFamilyplanMasterByLimit(startIndex, endIndex);
        final List<CFPSearchDTO> object = getCustomizedSearchFormFromModel(list);
        LOGGER.info("return cfp size " + ((object == null) ? object : object.size()));
        return object;

    }

    /**
     * Returns the appropriate companies list for CFP.
     *
     * @param searchField - field name
     * @param value - value entered in the field
     * @return list of CompanyMasterDTO object
     * @throws StplR2Exception the stpl r2 exception
     */
    public List<CompanyMasterDTO> getCompaniesForCFP(final String searchField, String value) throws PortalException, SystemException {


        LOGGER.info("Entering getCompaniesForCFP p1:" + searchField);

        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);

        // Added to check INBOUND_STATUS is A. ETL Soft delete data should not be included, where INBOUND_STATUS is D
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));


        if (StringUtils.isNotBlank(value)) {
            value = value.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT) + ConstantsUtils.PERCENCTAGE;

            cfpDynamicQuery.add(RestrictionsFactoryUtil.like(searchField, value));
        }
        final List<CompanyMaster> companyMasterList = CFPSEARCHLOGICDAO.getCompanyMasterList(cfpDynamicQuery);

        final List<CompanyMasterDTO> companyMasterDTOList = getCustomizedCompanyData(companyMasterList);
        LOGGER.info("return customized company size " + ((companyMasterDTOList == null) ? companyMasterDTOList : companyMasterDTOList.size()));
        return companyMasterDTOList;
    }

    /**
     * Returns Customized CompanyMaster Data.
     *
     * @param companyMasterList - list of CompanyMaster object
     * @return list of CompanyMasterDTO object
     * @throws StplR2Exception the stpl r2 exception
     */
    public List<CompanyMasterDTO> getCustomizedCompanyData(final List<CompanyMaster> companyMasterList) {
        final List<CompanyMasterDTO> companyDTO = new ArrayList<CompanyMasterDTO>();
        LOGGER.info("Entering getCustomizedCompanyData p1: " + ((companyMasterList == null) ? companyMasterList : companyMasterList.size()));
        for (int i = 0; i < companyMasterList.size(); i++) {
            final CompanyMaster company = companyMasterList.get(i);
            final Date date = new Date();
            final CompanyMasterDTO companyDTOObj = new CompanyMasterDTO();
            companyDTOObj.setCompanySystemId(String.valueOf(company.getCompanyMasterSid()));
            companyDTOObj.setCompanyId(company.getCompanyId());
            companyDTOObj.setCompanyNo(company.getCompanyNo());
            companyDTOObj.setCompanyName(company.getCompanyName());
            companyDTOObj.setCompanyType(helperListUtil.getIdHelperDTOMap().get(company.getCompanyType()));
            companyDTOObj.setCompanyStatus(helperListUtil.getIdHelperDTOMap().get(company.getCompanyStatus()));
            companyDTOObj.setCompanyStartDate(company.getCompanyStartDate());
            companyDTOObj.setCompanyEndDate(company.getCompanyEndDate());
            companyDTOObj.setCreatedDate(date);
            companyDTO.add(companyDTOObj);
        }
        LOGGER.info("return customized company size " + companyDTO.size());

        return companyDTO;
    }

    /**
     * Returns CompanyFamilyplanMaster for given CompanyFamilyplanMasterId.
     *
     * @param companyFamilyPlanSystemId - primary key
     * @return CompanyFamilyplanMaster modal obejct
     * @throws StplR2Exception the stpl r2 exception
     */
    public CfpModel getCompanyMasterById(final int companyFamilyPlanSystemId) throws SystemException, PortalException, ParseException, Exception {


        final CfpModel cfpMasterDTO = new CfpModelImpl();
        LOGGER.info("Entering getCompanyMasterById p1:" + companyFamilyPlanSystemId);
        final CfpModel cfpMaster = CFPSEARCHLOGICDAO.getCompanyFamilyplanMasterBySystemId(companyFamilyPlanSystemId);
        cfpMasterDTO.setCfpModelSid(cfpMaster.getCfpModelSid());
        cfpMasterDTO.setCfpId(cfpMaster.getCfpId());
        cfpMasterDTO.setCfpNo(cfpMaster.getCfpNo());
        cfpMasterDTO.setCfpName(cfpMaster.getCfpName());
        cfpMasterDTO.setCfpStartDate(convertDateToDate(cfpMaster.getCfpStartDate()));
        if (cfpMaster.getCfpEndDate() != null) {
            cfpMasterDTO.setCfpEndDate(convertDateToDate(cfpMaster.getCfpEndDate()));
        }
        cfpMasterDTO.setCfpStatus(cfpMaster.getCfpStatus());
        cfpMasterDTO.setCfpType(cfpMaster.getCfpType());
        cfpMasterDTO.setCfpDesignation(cfpMaster.getCfpDesignation());
        cfpMasterDTO.setCfpCategory(cfpMaster.getCfpCategory());
        cfpMasterDTO.setParentCfpName(cfpMaster.getParentCfpName());
        cfpMasterDTO.setParentCfpId(cfpMaster.getParentCfpId());
        LOGGER.info("return Cfp detail");
        return cfpMasterDTO;
    }

    /**
     * returns the formatted date.
     *
     * @param date the date
     * @return the date
     * @throws StplR2Exception the stpl r2 exception
     */
    public Date convertDateToDate(final Date date) throws ParseException {
        LOGGER.info("Entering convertDateToDate p1:" + date);
        final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.LONGDATEFORMAT, Locale.getDefault());
        final Date cDate = inputFormat.parse(inputFormat.format(date));
        LOGGER.info("return date -" + cDate);
        return cDate;

    }

    /**
     * method to convert date to string.
     *
     * @param date the date
     * @return the string
     * @throws StplR2Exception the stpl r2 exception
     */
    public String convertDateToString(final Date date) {

        String result;
        LOGGER.info("Entering ConvertDateToString p1:" + date);
        if (date == null) {
            result = StringUtils.EMPTY;
        } else {
            final Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            final String formatedDate = cal.get(Calendar.DATE) + "/"
                    + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
            result = formatedDate;
        }
        LOGGER.info("return date -" + result);
        return result;
    }

    /**
     * method to save CompanyMaster.
     *
     * @param cfpForm the cfp form
     * @param ifpList the ifp list
     * @return the string
     * @throws StplR2Exception the stpl r2 exception
     */
     public String saveCompanyMaster(final ErrorfulFieldGroup cfpForm, final List<NotesDTO> availableUploadedInformation, final String addedNotes,final List<NotesDTO> removedDetailsList) throws PortalException, SystemException {
        try {
            boolean flag = false;
            CfpModel cfp;
            String systemId = cfpForm.getField(ConstantsUtils.CFP_SYSTEM_ID)
                    .getValue() == null && cfpForm
                    .getField(ConstantsUtils.CFP_SYSTEM_ID).getValue()
                    .equals(ConstantsUtils.NULL) ? StringUtils.EMPTY
                    : String.valueOf(cfpForm
                            .getField(ConstantsUtils.CFP_SYSTEM_ID).getValue().toString()).replace(ConstantsUtils.COMMA, StringUtils.EMPTY);
            String cfpId = String.valueOf(cfpForm.getField(ConstantsUtils.CFP_ID).getValue()).trim();
            if (ConstantsUtils.NULL.equals(systemId) || StringUtils.EMPTY.equals(systemId) || ConstantsUtils.ZERO.equals(systemId)) {
                final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CfpModel.class);
                cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.CFP_ID_DUP, cfpId));
                cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
                final List<CfpModel> cfpModelList = CFPSEARCHLOGICDAO.getCompanyFamilyplanMasterList(cfpDynamicQuery);

                if (!cfpModelList.isEmpty()) {
                    for (int i = 0; i < cfpModelList.size(); i++) {
                        systemId = String.valueOf(cfpModelList.get(i).getCfpModelSid());
                    }
                    flag = true;
                }
            }

            if (ConstantsUtils.NULL.equals(systemId) || ConstantsUtils.ZERO.equals(systemId)) {
                cfp= CfpModelLocalServiceUtil.createCfpModel(0);
                cfp.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
                cfp.setSource(ConstantsUtils.GTN);
            } else {
                cfp = CFPSEARCHLOGICDAO.getCompanyFamilyplanMasterBySystemId(Integer.parseInt(systemId));
                cfp.setInboundStatus(ConstantsUtils.INBOUND_STATUS_C);
                cfp.setSource(ConstantsUtils.GTN);
                if (flag) {
                    cfp.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
                    cfp.setSource(ConstantsUtils.GTN);
                    sessionDTO.setSystemId(Integer.valueOf(systemId));
                }
            }

            cfp.setCfpId(cfpForm.getField(ConstantsUtils.CFP_ID).getValue().toString().trim());
            cfp.setCfpNo(cfpForm.getField(ConstantsUtils.CFP_NO).getValue() != null ? cfpForm.getField(ConstantsUtils.CFP_NO).getValue().toString().trim() : StringUtils.EMPTY);
            cfp.setCfpName(cfpForm.getField(ConstantsUtils.CFP_NAME).getValue() != null ? cfpForm.getField(ConstantsUtils.CFP_NAME).getValue().toString().trim() : StringUtils.EMPTY);
            cfp.setCfpType(cfpForm.getField(ConstantsUtils.CFP_TYPE).getValue() != null && !StringUtils.isEmpty(cfpForm.getField(ConstantsUtils.CFP_TYPE).getValue().toString()) ? ((com.stpl.ifs.util.HelperDTO) cfpForm.getField(ConstantsUtils.CFP_TYPE).getValue()).getId() : 0);
            cfp.setCfpStatus(((com.stpl.ifs.util.HelperDTO) cfpForm.getField(ConstantsUtils.CFP_STATUS).getValue()).getId());
            cfp.setCfpStartDate((Date) cfpForm.getField(ConstantsUtils.CFP_START_DATE).getValue());
            cfp.setCfpEndDate((Date) cfpForm.getField(ConstantsUtils.CFP_END_DATE).getValue());
            cfp.setCfpTradeClass(cfpForm.getField(ConstantsUtils.CFP_TRADE_CLASS).getValue() != null && !StringUtils.isEmpty(cfpForm.getField(ConstantsUtils.CFP_TRADE_CLASS).getValue().toString()) ? ((com.stpl.ifs.util.HelperDTO) cfpForm.getField(ConstantsUtils.CFP_TRADE_CLASS).getValue()).getId() : 0);
            cfp.setCfpCategory(cfpForm.getField(ConstantsUtils.CFP_CATEGORY).getValue() != null && !StringUtils.isEmpty(cfpForm.getField(ConstantsUtils.CFP_CATEGORY).getValue().toString()) ? ((com.stpl.ifs.util.HelperDTO) cfpForm.getField(ConstantsUtils.CFP_CATEGORY).getValue()).getId() : 0);
            cfp.setCfpDesignation(cfpForm.getField(ConstantsUtils.CFP_DESIGNATION).getValue() != null && !ConstantsUtils.SELECT_ONE.equals(cfpForm.getField(ConstantsUtils.CFP_DESIGNATION).getValue().toString())  ?  String.valueOf(((com.stpl.ifs.util.HelperDTO)cfpForm.getField(ConstantsUtils.CFP_DESIGNATION).getValue()).getId()) : ConstantsUtils.ZERO);
            cfp.setParentCfpName(cfpForm.getField(ConstantsUtils.PARENT_CFP_NAME).getValue() != null ? cfpForm.getField(ConstantsUtils.PARENT_CFP_NAME).getValue().toString() : StringUtils.EMPTY);
            cfp.setSalesInclusion(((com.stpl.ifs.util.HelperDTO)cfpForm.getField("salesInclusion").getValue()).getId());
            if (cfpForm.getField(ConstantsUtils.PARENT_CFP_ID).getValue() != null && !cfpForm.getField(ConstantsUtils.PARENT_CFP_ID).getValue().toString().equals(StringUtils.EMPTY)) {
                cfp.setParentCfpId(Integer.parseInt(cfpForm.getField("parentFlagSID").getValue().toString().replaceAll(ConstantsUtils.COMMA, StringUtils.EMPTY)));
            }else{
                cfp.setParentCfpId(0);
            }
            cfp.setRecordLockStatus(ConstantsUtils.UNLOCKED_BOOLEAN);
            cfp.setInternalNotes(addedNotes);

            /**
             * Duplicate Check STARTS
             */
            final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CfpModel.class);
            cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.CFP_ID_DUP, cfp.getCfpId()));
            if (!ConstantsUtils.NULL.equals(systemId) && !StringUtils.EMPTY.equals(systemId) && !ConstantsUtils.ZERO.equals(systemId)) {
                cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.CFP_SYSTEM_ID_DUP, Integer.valueOf(systemId)));
            }
            final List<CfpModel> cfpMaster = CFPSEARCHLOGICDAO.getCompanyFamilyplanMasterList(cfpDynamicQuery);
            if (!cfpMaster.isEmpty()) {
                LOGGER.info(ConstantsUtils.RETURN + ConstantsUtils.DUPLICATE);
                return ConstantsUtils.DUPLICATE;

            }

            if (StringUtils.isNotBlank(cfp.getCfpNo())) {
                final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CfpModel.class);
                dynamicQuery.add(RestrictionsFactoryUtil.eq(FieldNameUtils.COMPANYFAMILYPLANNO_SEARCH, cfp.getCfpNo()));
                if (!ConstantsUtils.NULL.equals(systemId) && !StringUtils.EMPTY.equals(systemId) && !ConstantsUtils.ZERO.equals(systemId)) {
                    dynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.CFP_SYSTEM_ID_DUP, Integer.valueOf(systemId)));
                }
                final List<CfpModel> cfpModel = CFPSEARCHLOGICDAO.getCompanyFamilyplanMasterList(dynamicQuery);
                if (!cfpModel.isEmpty()) {
                    LOGGER.info(ConstantsUtils.RETURN + ConstantsUtils.DUPLICATENO);
                    return ConstantsUtils.DUPLICATENO;

                }
            }

            /**
             * Duplicate Check ENDS
             */
            CfpModel result;
            if (ConstantsUtils.NULL.equals(systemId) || StringUtils.EMPTY.equals(systemId) || ConstantsUtils.ZERO.equals(systemId)) {
                LOGGER.info("Entering save operation");
                final String user = VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString();
                cfp.setCreatedDate(new Date());
                cfp.setCreatedBy(Integer.parseInt(user));
                cfp.setModifiedDate(new Date());
                result = CFPSEARCHLOGICDAO.saveCompanyFamilyplanMaster(cfp);
                sessionDTO.setSystemId(result.getCfpModelSid());
                if (String.valueOf(result.getCfpStatus()) != null || result.getCfpStatus() != 0) {
                    UDCIncrementCheck.increment(String.valueOf(result.getCfpStatus()), UIUtils.CFP_STATUS);
                }
                if (String.valueOf(result.getCfpType()) != null || result.getCfpType() != 0) {
                    UDCIncrementCheck.increment(String.valueOf(result.getCfpType()), UIUtils.CFP_TYPE);
                }
                if (String.valueOf(result.getCfpCategory()) != null || result.getCfpCategory() != 0) {
                    UDCIncrementCheck.increment(String.valueOf(result.getCfpCategory()), UIUtils.CFP_CATAGORY);
                }
                if (result.getCfpDesignation() != null) {
                    UDCIncrementCheck.increment(result.getCfpDesignation(), UIUtils.CFP_DESIGNATION);
                }
                if (String.valueOf(result.getCfpTradeClass()) != null || result.getCfpTradeClass() != 0) {
                    UDCIncrementCheck.increment(String.valueOf(result.getCfpTradeClass()), UIUtils.TRADE_CLASS);
                }

                saveCompaniesList(result);

            } else {

                LOGGER.info("Entering update operation");
                systemId = systemId.replace(ConstantsUtils.COMMA, StringUtils.EMPTY);
                cfp.setCfpModelSid(Integer.valueOf(systemId));
                cfp.setModifiedDate(new Date());
                cfp.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                final CfpModel check = CFPSEARCHLOGICDAO.getCompanyFamilyplanMasterBySystemId(cfp.getCfpModelSid());


                if (String.valueOf(check.getCfpStatus()) != null || check.getCfpStatus() != 0) {
                    UDCIncrementCheck.decrement(String.valueOf(check.getCfpStatus()), UIUtils.CFP_STATUS);
                }
                if (String.valueOf(check.getCfpType()) != null || check.getCfpType() != 0) {
                    UDCIncrementCheck.decrement(String.valueOf(check.getCfpType()), UIUtils.CFP_TYPE);
                }
                if (String.valueOf(check.getCfpCategory()) != null || check.getCfpCategory() != 0) {
                    UDCIncrementCheck.decrement(String.valueOf(check.getCfpCategory()), UIUtils.CFP_CATAGORY);
                }
                if (check.getCfpDesignation() != null) {
                    UDCIncrementCheck.decrement(check.getCfpDesignation(), UIUtils.CFP_DESIGNATION);
                }
                if (String.valueOf(check.getCfpTradeClass()) != null || check.getCfpTradeClass() != 0) {
                    UDCIncrementCheck.decrement(String.valueOf(check.getCfpTradeClass()), UIUtils.TRADE_CLASS);
                }
                if (flag) {
                    final Date createdDate = new Date();
                    cfp.setCreatedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                    cfp.setCreatedDate(createdDate);
                    cfp.setModifiedDate(createdDate);

                }
                result = CFPSEARCHLOGICDAO.updateCompanyFamilyplanMaster(cfp);

                if (String.valueOf(result.getCfpStatus()) != null || result.getCfpStatus() != 0) {
                    UDCIncrementCheck.increment(String.valueOf(result.getCfpStatus()), UIUtils.CFP_STATUS);
                }
                if (String.valueOf(result.getCfpType()) != null || result.getCfpType() != 0) {
                    UDCIncrementCheck.increment(String.valueOf(result.getCfpType()), UIUtils.CFP_TYPE);
                }
                if (String.valueOf(result.getCfpCategory()) != null || result.getCfpCategory() != 0) {
                    UDCIncrementCheck.increment(String.valueOf(result.getCfpCategory()), UIUtils.CFP_CATAGORY);
                }
                if (result.getCfpDesignation() != null) {
                    UDCIncrementCheck.increment(result.getCfpDesignation(), UIUtils.CFP_DESIGNATION);
                }
                if (String.valueOf(result.getCfpTradeClass()) != null || result.getCfpTradeClass() != 0) {
                    UDCIncrementCheck.increment(String.valueOf(result.getCfpTradeClass()), UIUtils.TRADE_CLASS);
                }


                String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                String sessionId = sessionDTO.getUiSessionId();
                String tempCreatedDate = sessionDTO.getSessionDate();

                if (flag) {
                    final Date tempDate = new Date();
                    final SimpleDateFormat fmt = new SimpleDateFormat(ConstantsUtils.YMD_FORMAT1);
                    CFPSEARCHLOGICDAO.updateCFPDetails(Integer.valueOf(systemId), userId, sessionId, fmt.format(tempDate), "Flag", null, null, null, null);
                } else {
                    CFPSEARCHLOGICDAO.updateCFPDetails(0, userId, sessionId, null, StringUtils.EMPTY, null, null, null, null);
                }
                CFPSEARCHLOGICDAO.deleteAllTempCfpDetails(userId, sessionId, null, false, tempCreatedDate, null);
            }

            if(removedDetailsList.size()!=0){
             for(int i=0;i<removedDetailsList.size();i++){
            	 NotesDTO dtoValue=removedDetailsList.get(i);
                 if(dtoValue.getDocDetailsId()!=0){
                        notesLogic.deleteUploadedFile(dtoValue.getDocDetailsId(), StringUtils.EMPTY, dtoValue.getDocumentFullPath());

                    }
                }

            }
            notesLogic.saveUploadedInformation(availableUploadedInformation, ConstantsUtils.CFP_MODEL, result.getCfpModelSid());
            LOGGER.info(ConstantsUtils.RETURN + ConstantsUtils.SUCCESS);

        } catch(Exception e) {
            LOGGER.error(e);
        }
        return ConstantsUtils.SUCCESS;
    }

    /**
     * save the companies to the table.
     *
     * @param ifpList the ifp list
     * @param companyFamilyPlanMaster the company family plan master
     * @throws StplR2Exception the stpl r2 exception
     */

    public void saveCompaniesList(final CfpModel companyFamilyPlanMaster) throws PortalException, SystemException {
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        String sessionId = sessionDTO.getUiSessionId();
        String tempCreatedDate = sessionDTO.getSessionDate();
        CFPSEARCHLOGICDAO.updateToCFPDetails(companyFamilyPlanMaster.getCfpModelSid(), userId, sessionId, tempCreatedDate, null, this, this, this, this);
        LOGGER.info("Ending saveCompaniesList");
    }

    /**
     * To delete the CFP Details by SystemID.
     *
     * @param cfpSystemId the cfp system id
     * @return the string
     * @throws com.stpl.portal.kernel.exception.PortalException
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public String deleteCFPDetailsById(final int cfpSystemId) throws PortalException, SystemException {

        LOGGER.info("Entering deleteCFPDetailsById p1:" + cfpSystemId);

        final List<CfpDetails> companyList = CFPSEARCHLOGICDAO.getCompanyFamilyplanDetailsListByCFPSystemId(cfpSystemId);

        for (int i = 0; i < companyList.size(); i++) {
            final CfpDetails companyDetail = companyList.get(i);
            companyDetail.setInboundStatus(ConstantsUtils.INBOUND_STATUS_D);
        }
        return ConstantsUtils.SUCCESS;
    }

    /**
     * To delete the CFP Details in Master Table by SystemID.
     *
     * @param cfpSystemId the cfp system id
     * @return the company familyplan master
     * @throws StplR2Exception the stpl r2 exception
     */
    public CfpModel deleteCFPMasterById(final int cfpSystemId) throws PortalException, SystemException {

        LOGGER.info("Entering deleteCFPMasterById p1:" + cfpSystemId);
        final CfpModel cfp = CFPSEARCHLOGICDAO.getCompanyFamilyplanMasterBySystemId(cfpSystemId);
        cfp.setInboundStatus(ConstantsUtils.INBOUND_STATUS_D);
        CFPSEARCHLOGICDAO.updateCompanyFamilyplanMaster(cfp);
        if (String.valueOf(cfp.getCfpStatus()) != null || cfp.getCfpStatus() != 0) {
            UDCIncrementCheck.decrement(String.valueOf(cfp.getCfpStatus()), UIUtils.CFP_STATUS);
        }
        if (String.valueOf(cfp.getCfpType()) != null || cfp.getCfpType() != 0) {
            UDCIncrementCheck.decrement(String.valueOf(cfp.getCfpType()), UIUtils.CFP_TYPE);
        }
        if (String.valueOf(cfp.getCfpCategory()) != null || cfp.getCfpCategory() != 0) {
            UDCIncrementCheck.decrement(String.valueOf(cfp.getCfpCategory()), UIUtils.CFP_CATAGORY);
        }
        if (cfp.getCfpDesignation() != null) {
            UDCIncrementCheck.decrement(cfp.getCfpDesignation(), UIUtils.CFP_DESIGNATION);
        }
        if (String.valueOf(cfp.getCfpTradeClass()) != null || cfp.getCfpTradeClass() != 0) {
            UDCIncrementCheck.decrement(String.valueOf(cfp.getCfpTradeClass()), UIUtils.TRADE_CLASS);
        }
        CFPSEARCHLOGICDAO.updateOperationCFPDeatils(String.valueOf(cfpSystemId));
        LOGGER.info("return CompanyFamilyplanMaster");
        return cfp;

    }

    /**
     * returns the HelperDTO list by CFP Type.
     *
     * @return the CFP type
     * @throws StplR2Exception the stpl r2 exception
     */
    public List<HelperDTO> getCFPType() throws SystemException, PortalException {
        LOGGER.info("Entering getCFPType");
        final List<HelperTable> list = CFPSEARCHLOGICDAO.getHelperTableDetailsByListName(UIUtils.CFP_TYPE);

        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }
            Collections.sort(helperList);
        }
        LOGGER.info("return customized CFP Type size-" + helperList.size());
        return helperList;
    }

    /**
     * returns the HelperDTO list by TradeClass.
     *
     * @return the trade class
     * @throws StplR2Exception the stpl r2 exception
     */
    public List<HelperDTO> getTradeClass() throws SystemException, PortalException {

        List<HelperTable> list;
        LOGGER.info("Entering getTradeClass");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        list = CFPSEARCHLOGICDAO.getHelperTableDetailsByListName(UIUtils.TRADE_CLASS);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }
            Collections.sort(helperList);
        }
        LOGGER.info("return customized TradeClass size-" + helperList.size());
        return helperList;
    }

    /**
     * returns the HelperDTO list by State.
     *
     * @return the state
     * @throws StplR2Exception the stpl r2 exception
     */
    public List<HelperDTO> getState() throws SystemException, Exception {
        LOGGER.info("Entering getState");
        List<HelperTable> list;
        list = CFPSEARCHLOGICDAO.getHelperTableDetailsByListName(UIUtils.STATE);
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }

        }
        LOGGER.info("return customized State size-" + helperList.size());
        return helperList;
    }

    /**
     * returns the HelperDTO list by Country.
     *
     * @return the country
     * @throws StplR2Exception the stpl r2 exception
     */
    public List<HelperDTO> getCountry() throws SystemException, Exception {
        LOGGER.info("Entering getCountry");
        final List<HelperTable> list = CFPSEARCHLOGICDAO.getHelperTableDetailsByListName(UIUtils.COUNTRY);
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }
        }
        LOGGER.info("return customized country size-" + helperList.size());
        return helperList;
    }

    /**
     * returns the HelperDTO list by Type.
     *
     * @param listType the list type
     * @return the CFP type
     * @throws StplR2Exception the stpl r2 exception
     */
    public List<HelperDTO> getCFPType(final String listType) throws SystemException, Exception {
        LOGGER.info("Entering getCFPType by Type");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final List<HelperTable> list = CFPSEARCHLOGICDAO.getHelperTableDetailsByListName(listType);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));
            }
            Collections.sort(helperList);
        }
        LOGGER.info("return customized CFP Type size-" + helperList.size());
        return helperList;
    }

    /**
     * returns the list to load dropdown.
     *
     * @param listName the list name
     * @return the drop down list
     */
    public List<HelperDTO> getDropDownList(final String listName) throws SystemException, PortalException {
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        LOGGER.info("Entering getDropDownList by Name");
        final List<HelperTable> list = CFPSEARCHLOGICDAO.getHelperTableDetailsByListName(listName);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));
            }
            Collections.sort(helperList);
        }
        LOGGER.info("retrns customized Dropdown size-" + helperList.size());
        return helperList;
    }

    public static int getParentCfpCount() throws PortalException, SystemException {
        LOGGER.info("Entering getLazyCompanyQualifierNameCount method with filterText :" );
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CfpModel.class);
        long count =   CfpModelLocalServiceUtil.dynamicQueryCount(cfpDynamicQuery);
        LOGGER.info("Ending getLazyCompanyQualifierNameCount method with filterText with count :" + count);
        return Integer.parseInt(String.valueOf(count));
    }

    public static List<HelperDTO> getParentCfpResult(final int startIndex, final int end) throws PortalException, SystemException {
        final List<HelperDTO> list = new ArrayList<HelperDTO>();
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CfpModel.class);
        cfpDynamicQuery.setLimit(startIndex, end);
        List<CfpModel> result = CfpModelLocalServiceUtil.dynamicQuery(cfpDynamicQuery);
        HelperDTO dto;

        if(startIndex==0){
            list.add(new HelperDTO(0,"Show All"));
        }
        for(CfpModel model :result ){

            list.add(new HelperDTO(model.getCfpModelSid(),model.getCfpId()));

        }
        LOGGER.info("Ending getLazyManufactureIdResults  return list size :" + +list.size());
        return list;
    }
    /**
     * returns the CompanyFamilyplanMaster by cfpId.
     *
     * @param cfpId the cfp id
     * @return the CFP by id
     * @throws StplR2Exception the stpl r2 exception
     */
    public CFPCompanyDTO getCFPById(final int cfpId) throws SystemException, Exception {
        LOGGER.info("Entering getCFPById p1:" + cfpId);
        Map<Integer, String> userMap= StplSecurity.getUserName();
        final CfpModel companyFamilyplanMaster = CFPSEARCHLOGICDAO.getCompanyFamilyplanMasterBySystemId(cfpId);
        CFPCompanyDTO companyDto = new CFPCompanyDTO();
        companyDto.setCompanyFamilyPlanCategory(helperListUtil.getIdHelperDTOMap().get(companyFamilyplanMaster.getCfpCategory()));
        companyDto.setCompanyFamilyPlanSystemId(companyFamilyplanMaster.getCfpModelSid());
        companyDto.setCompanyFamilyPlanStatus(helperListUtil.getIdHelperDTOMap().get(companyFamilyplanMaster.getCfpStatus()));
        companyDto.setCompanyFamilyPlanTradeClass(helperListUtil.getIdHelperDTOMap().get(companyFamilyplanMaster.getCfpTradeClass()));
        companyDto.setCompanyFamilyPlanType(helperListUtil.getIdHelperDTOMap().get(companyFamilyplanMaster.getCfpType()));
        if(companyFamilyplanMaster.getCreatedBy()!=0){
        companyDto.setCreatedBy(userMap.get(companyFamilyplanMaster.getCreatedBy())==null ? StringUtils.EMPTY : userMap.get(companyFamilyplanMaster.getCreatedBy()));
        }
        if(companyFamilyplanMaster.getModifiedBy()!=0){
             companyDto.setModifiedBy(userMap.get(companyFamilyplanMaster.getModifiedBy())==null ? StringUtils.EMPTY : userMap.get(companyFamilyplanMaster.getModifiedBy()));       
        }
        if(companyFamilyplanMaster.getCfpDesignation()!=null && !StringUtils.EMPTY.equals(companyFamilyplanMaster.getCfpDesignation())){
            companyDto.setCompanyFamilyPlanDesignation(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(companyFamilyplanMaster.getCfpDesignation())));
        }
        companyDto.setCompanyFamilyPlanEndDate(companyFamilyplanMaster.getCfpEndDate());
        companyDto.setCompanyFamilyPlanId(companyFamilyplanMaster.getCfpId());
        companyDto.setCompanyFamilyPlanName(companyFamilyplanMaster.getCfpName());
        companyDto.setCompanyFamilyPlanNo(companyFamilyplanMaster.getCfpNo());
        companyDto.setRecordLockStatus(companyFamilyplanMaster.getRecordLockStatus());
        companyDto.setParentFlagSID(companyFamilyplanMaster.getParentCfpId());
        if(companyDto.getParentFlagSID()!=0){
            final CfpModel parentCFPMaster = CFPSEARCHLOGICDAO.getCompanyFamilyplanMasterBySystemId(companyDto.getParentFlagSID());
            companyDto.setParentCompanyFamilyPlanId(parentCFPMaster.getCfpId());
            companyDto.setParentCompanyFamilyPlanName(parentCFPMaster.getCfpName());
        }
        companyDto.setModifiedDate(companyFamilyplanMaster.getModifiedDate());
        companyDto.setParentCompanyFamilyPlanName(companyFamilyplanMaster.getParentCfpName());
        companyDto.setInboundStatus(companyFamilyplanMaster.getInboundStatus());
        companyDto.setCompanyFamilyPlanStartDate(companyFamilyplanMaster.getCfpStartDate());
        companyDto.setCreatedDate(companyFamilyplanMaster.getCreatedDate());
        companyDto.setSalesInclusion(helperListUtil.getIdHelperDTOMap().get(companyFamilyplanMaster.getSalesInclusion()));
        companyDto.setInternalNotes(companyFamilyplanMaster.getInternalNotes());
        LOGGER.info("return CompanyFamilyplanMaster");
        return companyDto;

    }

    /**
     * returns list contains Selected Companies.
     *
     * @param cfpId the cfp id
     * @return the selected company list
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    public List<CompanyMasterDTO> getSelectedCompanyList(final int cfpId) throws PortalException, SystemException {

        List<CompanyMasterDTO> companyListDTO = new ArrayList<CompanyMasterDTO>();
        LOGGER.info("Entering getSelectedCompanyList p1:" + cfpId);
        final List<CfpDetails> list = CFPSEARCHLOGICDAO.getCompanyFamilyplanDetailsListByCFPSystemId(cfpId);
        if (list != null) {
            final List<CompanyMaster> companyList = new ArrayList<CompanyMaster>();
            for (int i = 0; i < list.size(); i++) {
                final CfpDetails details = list.get(i);

                companyList.add(CFPSEARCHLOGICDAO.getCompanyMasterBySystemId(details
                        .getCompanyMasterSid()));

            }
            companyListDTO = getCustomizedCompanyData(companyList);
        }
        LOGGER.info("return Company List size-" + ((companyListDTO == null) ? companyListDTO : companyListDTO.size()));
        return companyListDTO;
    }

 
    /**
     * Gets the customized search form from model.
     *
     * @param cfpMasterList the cfp master list
     * @return the customized search form from model
     * @throws StplR2Exception the stpl r2 exception
     */
    private List<CFPSearchDTO> getCustomizedSearchFormFromModel(final List<CfpModel> cfpMasterList) throws PortalException, SystemException {

        final List<CFPSearchDTO> resultList = new ArrayList<CFPSearchDTO>();
        Map<Integer, String> hm = com.stpl.app.util.GeneralCommonUtils.getCodeDescription();
        LOGGER.info("Entering getCustomizedSearchFormFromModel p1: size " + ((cfpMasterList == null) ? cfpMasterList : cfpMasterList.size()));
        CFPSearchDTO cFPDTO;
        for (int i = 0; i < cfpMasterList.size(); i++) {
            final CfpModel companyFamilyplanMaster = cfpMasterList.get(i);
            cFPDTO = new CFPSearchDTO();
            cFPDTO.setParentCompanyFamilyPlanName(companyFamilyplanMaster.getParentCfpName());
            cFPDTO.setInternalNotes(companyFamilyplanMaster.getInternalNotes());
            cFPDTO.setRecordLockStatus(companyFamilyplanMaster.getRecordLockStatus());
            if(companyFamilyplanMaster.getCfpTradeClass()!=0) {
                cFPDTO.setCompanyFamilyPlanTradeClass(hm.get(companyFamilyplanMaster.getCfpTradeClass()));
            }
            cFPDTO.setCreatedBy(String.valueOf(companyFamilyplanMaster.getCreatedBy()));
            cFPDTO.setCompanyFamilyPlanId(companyFamilyplanMaster.getCfpId());
            cFPDTO.setCompanyFamilyPlanName(companyFamilyplanMaster.getCfpName());
            if(companyFamilyplanMaster.getCfpCategory()!=0) {
                cFPDTO.setCompanyFamilyPlanCategory(hm.get(companyFamilyplanMaster.getCfpCategory()));
            }
            cFPDTO.setBatchId(companyFamilyplanMaster.getBatchId());
            if(companyFamilyplanMaster.getCfpType()!=0) {
                cFPDTO.setCompanyFamilyPlanType(hm.get(companyFamilyplanMaster.getCfpType()));
            }
            cFPDTO.setInboundStatus(companyFamilyplanMaster.getInboundStatus());
            cFPDTO.setModifiedBy(String.valueOf(companyFamilyplanMaster.getModifiedBy()));
            cFPDTO.setCompanyFamilyPlanDesignation(companyFamilyplanMaster.getCfpDesignation());
            cFPDTO.setCompanyFamilyPlanNo(companyFamilyplanMaster.getCfpNo());
            if(companyFamilyplanMaster.getCfpStatus()!=0) {
                cFPDTO.setCompanyFamilyPlanStatus(hm.get(companyFamilyplanMaster.getCfpStatus()));
            }
            cFPDTO.setCompanyFamilyPlanStartDate(companyFamilyplanMaster.getCfpStartDate());
            cFPDTO.setCompanyFamilyPlanEndDate(companyFamilyplanMaster.getCfpEndDate());
            cFPDTO.setModifiedDate(companyFamilyplanMaster.getModifiedDate());
            cFPDTO.setCreatedDate(companyFamilyplanMaster.getCreatedDate());
            if(companyFamilyplanMaster.getParentCfpId()!=0){
                CfpModel parentId = CfpModelLocalServiceUtil.getCfpModel(companyFamilyplanMaster.getParentCfpId());
                cFPDTO.setParentCompanyFamilyPlanId(parentId.getCfpId());
                cFPDTO.setParentCFPId(companyFamilyplanMaster.getParentCfpId());
            }
            cFPDTO.setCompanyFamilyPlanSystemId(companyFamilyplanMaster.getCfpModelSid());
            resultList.add(cFPDTO);
        }
        LOGGER.info("return CFP size-" + resultList.size());

        return resultList;
    }

    public static List<SearchResultsDTO> getCustomizedSearchFormModel(final List<Object[]> cfpMasterList) throws PortalException, SystemException, Exception {
        final List<SearchResultsDTO> companyDTO = new ArrayList<SearchResultsDTO>();
        Map<Integer, String> hm = com.stpl.app.util.GeneralCommonUtils.getCodeDescription();
        Map<Integer, String> userMap= StplSecurity.getUserName();
        for (Object[] obj : cfpMasterList) {
            final SearchResultsDTO cfpDTOObj = new SearchResultsDTO();
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
                Date startDate = (Date) obj[5];
                DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                startDate = df.parse(CommonUtils.convertDateToString(startDate));
                cfpDTOObj.setCompanyFamilyPlanStartDate(startDate);
            }
            if (obj[6] != null) {
                Date endDate = (Date) obj[6];
                DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                endDate = df.parse(CommonUtils.convertDateToString(endDate));
                cfpDTOObj.setCompanyFamilyPlanEndDate(endDate);
            }
            if (obj[7] != null && StringUtils.isNotBlank(obj[7].toString()) && (Integer) obj[7] != 0) {
                cfpDTOObj.setCompanyFamilyPlanTradeClass(hm.get(obj[7]));
            }
            if (obj[8] != null && StringUtils.isNotBlank(obj[8].toString()) && (Integer) obj[8] != 0) {
                cfpDTOObj.setCompanyFamilyPlanCategory(hm.get(obj[8]));
            }
            if (obj[9] != null && !StringUtils.isBlank(String.valueOf(obj[9]))) {
                String CHILD="Child";
                String PARENT="Parent";
                if(CHILD.equals(String.valueOf(obj[9]))){
                    cfpDTOObj.setCompanyFamilyPlanDesignation(CHILD);
            }else if(PARENT.equals(String.valueOf(obj[9]))){
                    cfpDTOObj.setCompanyFamilyPlanDesignation(PARENT);
            }else{
                    cfpDTOObj.setCompanyFamilyPlanDesignation(String.valueOf(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(obj[9])))));
                }
            }
            if (obj[10] != null) {
                cfpDTOObj.setParentCompanyFamilyPlanName(String.valueOf(obj[10]));
            }
            if (obj[11] != null) {
                cfpDTOObj.setParentCompanyFamilyPlanId(String.valueOf(obj[11]));
            }
            if (obj[16] !=null){
                cfpDTOObj.setCompanyFamilyPlanSystemId(Integer.valueOf(String.valueOf(obj[16])));
                cfpDTOObj.setSystemID(String.valueOf(cfpDTOObj.getCompanyFamilyPlanSystemId()));
            }
            if (obj[17] !=null){
                Date modifiedDate = (Date) obj[17];
                DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                modifiedDate = df.parse(CommonUtils.convertDateToString(modifiedDate));
                cfpDTOObj.setCfpmodifiedDate(modifiedDate);
            }
            if (obj[18] !=null){
                cfpDTOObj.setCfpmodifiedBy(userMap.get(Integer.valueOf(String.valueOf(obj[18]))));
            }
            if (obj[19] !=null){
                Date createdDate = (Date) obj[19];
                DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                createdDate = df.parse(CommonUtils.convertDateToString(createdDate));
                cfpDTOObj.setCfpcreatedDate(createdDate);
            }

            if (obj[20] !=null){
                cfpDTOObj.setCfpcreatedBy(userMap.get(Integer.valueOf(String.valueOf(obj[20]))));
            }
            if (obj[21] !=null){
                cfpDTOObj.setRecordLockStatus(Boolean.parseBoolean(String.valueOf(obj[21])));
            }
            companyDTO.add(cfpDTOObj);
        }
        LOGGER.info("return customized company size " + companyDTO.size());

        return companyDTO;
    }

    /**
     * returns the search count.
     *
     * @param cfpSearchLogic the cfp search logic
     * @param binder the binder
     * @return the record count
     */
    public static long getRecordCount(final CFPSearchLogic cfpSearchLogic, final ErrorfulFieldGroup binder) throws PortalException, SystemException {
        LOGGER.info("Entering getRecordCount");
        final int count = Integer.valueOf(((List)cfpSearchLogic.getList(binder,null,null,false,0,0,"count")).get(0).toString());
        LOGGER.info("return record count -" + count);
        return count;
    }

    /**
     * getting count for CompanyQualifierName.
     *
     * @param filterText the filter text
     * @return the lazy company qualifier name count
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public static int getLazyAvailableCompaniesCount(CommonLazyUtilDTO dto,final BeanSearchCriteria searchCriteria) throws PortalException, SystemException, Exception {
        LOGGER.info("Entering getLazyAvailableCompaniesCount");
        dto.setSearchValue(dto.getSearchValue().replace("*", ConstantsUtils.PERCENCTAGE));
        final int count = Integer.valueOf(String.valueOf(CFPSEARCHLOGICDAO.getCompanyLazyList(0, 0, new Object[]{dto.getUserId(), dto.getSessionId()}, "count", new Object[]{dto.getSearchFields(), dto.getSearchValue()},null,null,getFilterMapForAvailableCompanies(searchCriteria)).get(0)));
        LOGGER.info("available count :" + count);
        return count;
    }

    /**
     * getting results for CompanyQualifierName.
     *
     * @param start the start
     * @param end the end
     * @param filterText the filter text
     * @param editListFlag the edit list flag
     * @return the lazy company qualifier name results
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public static List<CompanyMasterDTO> getLazyAvailableCompaniesResults(CommonLazyUtilDTO dto, final int start, final int offset,final List<OrderByColumn> list,final BeanSearchCriteria searchCriteria) throws PortalException, SystemException, Exception {

        dto.setSearchValue(dto.getSearchValue().replace("*", ConstantsUtils.PERCENCTAGE));
        getFilterMapForAvailableCompanies(searchCriteria);
            String columnName=ConstantsUtils.COMPANYNAMES;
            String orberBy=ConstantsUtils.ASC;
        if(list!=null){
            for (final Iterator<OrderByColumn> iterator = list.iterator(); iterator.hasNext();) {
                final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();

                if(orderByColumn.getName().equals(ConstantsUtils.COMPANY_NO)){
                    columnName=Company_no;
                }else if(orderByColumn.getName().equals(ConstantsUtils.COMPANY_NAME)){
                    columnName=ConstantsUtils.COMPANYNAMES;
                }else if(orderByColumn.getName().equals(ConstantsUtils.COMPANY_ID)){
                    columnName=Company_Id;
                }else if(orderByColumn.getName().equals(ConstantsUtils.COMPANY_TYPE_VALUE)){
                    columnName=Ctype;
                }else if(orderByColumn.getName().equals(ConstantsUtils.COMPANY_STATUS_VALUE)){
                    columnName=Cstatus;
                }else if(orderByColumn.getName().equals(ConstantsUtils.COMPANY_CATEGORY_VALUE)){
                    columnName=Category;
                }else if(orderByColumn.getName().equals(ConstantsUtils.TRADE_CLASS_VALUE)){
                    columnName=Trade;
                }else if(orderByColumn.getName().equals(ConstantsUtils.COMPANY_GROUP_VALUE)){
                    columnName=Cgroup;
                }
                if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                        orberBy=ConstantsUtils.ASC;

                } else {
                        orberBy=ConstantsUtils.DESC;
                }

            }}

        final List<Object[]> companyMasterList = CFPSEARCHLOGICDAO.getCompanyLazyList(start, offset,
                new Object[]{dto.getUserId(), dto.getSessionId()}, searchresults, new Object[]{dto.getSearchFields(), dto.getSearchValue()},columnName,orberBy,getFilterMapForAvailableCompanies(searchCriteria));
        final List<CompanyMasterDTO> companyMasterDTOList = getCustomizedCompanyLazyData(companyMasterList);
        LOGGER.info("available results --" + companyMasterDTOList.size());
        return companyMasterDTOList;
    }

    public static List<CompanyMasterDTO> getCustomizedCompanyLazyData(final List<Object[]> companyMasterList) throws PortalException, SystemException {
        final List<CompanyMasterDTO> companyDTO = new ArrayList<CompanyMasterDTO>();
        Map<Integer, String> hm = com.stpl.app.util.GeneralCommonUtils.getCodeDescription();
        for (Object[] obj : companyMasterList) {
            final CompanyMasterDTO companyDTOObj = new CompanyMasterDTO();
            companyDTOObj.setCompanySystemId(String.valueOf(obj[0]));
            companyDTOObj.setCompanyId(String.valueOf(obj[1]));
            companyDTOObj.setCompanyNo(String.valueOf(obj[2]));
            companyDTOObj.setCompanyName(String.valueOf(obj[3]));
            companyDTOObj.setCompanyTypeValue(hm.get(obj[4]));
            companyDTOObj.setCompanyStatusValue(hm.get(obj[5]));
            companyDTOObj.setCompanyStartDate((Date) obj[6]);
            companyDTOObj.setCompanyEndDate((Date) obj[7]);
            if(obj[10]!=null && StringUtils.isNotBlank(String.valueOf(obj[10])) && !String.valueOf(obj[10]).equals(ConstantsUtils.SELECT_ONE)){
                companyDTOObj.setCompanyCategoryValue(String.valueOf(obj[10]));
            }

            if(obj[11]!=null && StringUtils.isNotBlank(String.valueOf(obj[11])) && !String.valueOf(obj[11]).equals(ConstantsUtils.SELECT_ONE)){
                companyDTOObj.setCompanyGroupValue(String.valueOf(obj[11]));
            }
            if(obj[11]!=null && !ConstantsUtils.NULL.equals(String.valueOf(obj[12])) && StringUtils.isNotBlank(String.valueOf(obj[12])) && !String.valueOf(obj[12]).equals(ConstantsUtils.SELECT_ONE)){
                companyDTOObj.setTradeClassValue(String.valueOf(obj[12]));
            }
            companyDTO.add(companyDTOObj);
        }
        LOGGER.info("return customized company size " + companyDTO.size());

        return companyDTO;
    }

    public static List<CFPCompanyDTO> getLazyCompanyDetailsResults(final int start, final int end) throws PortalException, SystemException {
        LOGGER.info("Entering getLazyCompanyDetailsResults method with filterText  start----" + start + " --- end ----" + end);
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        // Added to check INBOUND_STATUS is A. ETL Soft delete data should not be included, where INBOUND_STATUS is D
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));
        cfpDynamicQuery.setLimit(start, end);
        final List<CompanyMaster> companyMasterList = CFPSEARCHLOGICDAO.getCompanyMasterList(cfpDynamicQuery);
        final List<CFPCompanyDTO> companyMasterDTOList = getCustomizedCompanyDetailsData(companyMasterList);
        LOGGER.info("return customized company size " + ((companyMasterDTOList == null) ? companyMasterDTOList : companyMasterDTOList.size()));
        return companyMasterDTOList;
    }

    public static List<CFPCompanyDTO> getCustomizedCompanyDetailsData(final List<CompanyMaster> companyMasterList) {
        final List<CFPCompanyDTO> companyDTO = new ArrayList<CFPCompanyDTO>();
        LOGGER.info("Entering getCustomizedCompanyData p1: " + ((companyMasterList == null) ? companyMasterList : companyMasterList.size()));
        for (int i = 0; i < companyMasterList.size(); i++) {
            final CompanyMaster company = companyMasterList.get(i);
            final CFPCompanyDTO companyDTOObj = new CFPCompanyDTO();
            companyDTOObj.setCompanyFamilyPlanSystemId(company.getCompanyMasterSid());
            companyDTOObj.setCompanyId(company.getCompanyId());
            companyDTOObj.setCompanyNo(company.getCompanyNo());
            companyDTOObj.setCompanyName(company.getCompanyName());
            companyDTOObj.setCompanyType(String.valueOf(company.getCompanyType()));
            companyDTOObj.setCompanyStatus(company.getCompanyStatus());
            companyDTO.add(companyDTOObj);
        }
        LOGGER.info("return customized company size " + companyDTO.size());

        return companyDTO;
    }

    public static List<CFPCompanyDTO> getCompanyDetailsResults() throws PortalException, SystemException {

        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));
        final List<CompanyMaster> companyMasterList = CFPSEARCHLOGICDAO.getCompanyMasterList(cfpDynamicQuery);
        final List<CFPCompanyDTO> companyMasterDTOList = getCustomizedCompanyDetailsData(companyMasterList);
        LOGGER.info("return customized company size " + ((companyMasterDTOList == null) ? companyMasterDTOList : companyMasterDTOList.size()));
        return companyMasterDTOList;
    }

    public static Boolean saveToTempTable(List<CFPCompanyDTO> saveList) throws SystemException, PortalException {
        for (CFPCompanyDTO cFPCompanyDTO : saveList) {
            CFPSEARCHLOGICDAO.updateTempCfpDetails(getCustomizedTempCfpDetails(cFPCompanyDTO));
        }
        return true;
    }

    public boolean moveToRightDuplicateValidation(final CompanyMasterDTO company, final CommonLazyUtilDTO dto) throws SystemException, PortalException {
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdCfpDetails.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_MASTER_ID, Integer.valueOf(company.getCompanySystemId())));
        dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USERS_ID, dto.getUserId()));
        dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, dto.getSessionId()));
        dynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.D));
        dynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.F));
        final List<ImtdCfpDetails> list = ImtdCfpDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
        return (list!=null && !list.isEmpty());
    }

    public static String getDescription(int code){

        try {
            HelperTable table =HelperTableLocalServiceUtil.getHelperTable(code);
            return table.getDescription();
        } catch (PortalException | SystemException ex) {
            java.util.logging.Logger.getLogger(CFPSearchLogic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void moveAndSaveToTempTable(final CompanyMasterDTO company, final CommonLazyUtilDTO dto) throws SystemException, PortalException {
        if (company != null) {
            final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdCfpDetails.class);
            dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_MASTER_ID, Integer.valueOf(company.getCompanySystemId())));
            dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USERS_ID, dto.getUserId()));
            dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, dto.getSessionId()));
            final List<ImtdCfpDetails> list = ImtdCfpDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
            LOGGER.info("--------list.size()->>>>>>>>>" + list.size());
            if (list.size() > 0) {
                ImtdCfpDetails tempCfpDetails = list.get(0);
                tempCfpDetails.setCfpDetailsSid(0);
                tempCfpDetails.setCfpDetailsStartDate(null);
                tempCfpDetails.setCfpDetailsEndDate(null);
                tempCfpDetails.setCfpDetailsAttachedDate(new Date());
                tempCfpDetails.setCfpDetailsAttachedStatus(0);
                tempCfpDetails.setCfpDetailsCreatedDate(new Date());
                tempCfpDetails.setCfpDetailsCreatedBy(dto.getUserId());
                tempCfpDetails.setCfpDetailsModifiedDate(null);
                tempCfpDetails.setCfpDetailsModifiedBy(null);
                tempCfpDetails.setOperation(ConstantsUtils.A);
                ImtdCfpDetailsLocalServiceUtil.updateImtdCfpDetails(tempCfpDetails);
            } else {
                saveToTempTable(company, dto);
            }
        }
    }

    public void addToTempTable(final CommonLazyUtilDTO dto) throws SystemException, PortalException {
        final String sysId = String.valueOf(sessionDTO.getSystemId());
        String searchValue="COMPANY_STATUS".equals(dto.getSearchFields())?dto.getHelperTableSid():dto.getSearchValue();
        CFPSEARCHLOGICDAO.insertTempCfpDetailsInADD(dto.getUserId(), dto.getSessionId(), dto.getDate(), CommonUtils.getDateTime(ConstantsUtils.YMD_FORMAT, new Date()), new Object[]{dto.getSearchFields(), searchValue}, null, null, sysId);
    }

    private void saveToTempTable(CompanyMasterDTO company, final CommonLazyUtilDTO dto) throws SystemException {
        try {
            final String sysId = String.valueOf(sessionDTO.getSystemId());

            ImtdCfpDetails tempCFP = ImtdCfpDetailsLocalServiceUtil.createImtdCfpDetails(0);
            tempCFP.setCompanyMasterSid(Integer.valueOf(company.getCompanySystemId()));
            if (sysId != null && !sysId.equals(ConstantsUtils.NULL)) {
                tempCFP.setCfpModelSid(Integer.valueOf(sysId));
            }
            tempCFP.setCompanyId(company.getCompanyId());
            tempCFP.setCompanyNo(company.getCompanyNo());
            tempCFP.setCompanyName(company.getCompanyName());
            DynamicQuery query1 = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            query1.add(RestrictionsFactoryUtil.eq(ConstantsUtils.DESCRIPTION, company.getCompanyTypeValue()));
            List<HelperTable> list1 = HelperTableLocalServiceUtil.dynamicQuery(query1);
            HelperTable obj1 = HelperTableLocalServiceUtil.createHelperTable(0);
            for(int i=0;i<list1.size();i++) {
                obj1 = list1.get(i);
            }
            tempCFP.setCompanyType(String.valueOf(obj1.getHelperTableSid()));
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            query.add(RestrictionsFactoryUtil.eq(ConstantsUtils.DESCRIPTION, company.getCompanyStatusValue()));
            List<HelperTable> list = HelperTableLocalServiceUtil.dynamicQuery(query);
            HelperTable obj = HelperTableLocalServiceUtil.createHelperTable(0);
            for(int i=0;i<list.size();i++) {
                obj = list.get(i);
            }
            tempCFP.setCompanyStatus(obj.getHelperTableSid());
            tempCFP.setCompanyStartDate(company.getCompanyStartDate());
            tempCFP.setCompanyEndDate(company.getCompanyEndDate());
            tempCFP.setCfpDetailsAttachedDate(new Date());
            tempCFP.setUsersSid(dto.getUserId());
            tempCFP.setSessionId(dto.getSessionId());
            tempCFP.setImtdCreatedDate(CommonUtils.getDateTime());
            tempCFP.setCfpDetailsCreatedDate(new Date());
            tempCFP.setCfpDetailsCreatedBy(dto.getUserId());
            tempCFP.setOperation(ConstantsUtils.A);
            tempCFP.setCreatedDate(new Date());
            tempCFP.setCreatedBy(Integer.valueOf(dto.getUserId()));
            tempCFP.setModifiedDate(new Date());
            tempCFP.setModifiedBy(Integer.valueOf(dto.getUserId()));
            CFPSEARCHLOGICDAO.saveTempCfpDetails(tempCFP);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private static ImtdCfpDetails getCustomizedTempCfpDetails(CFPCompanyDTO company) throws SystemException, PortalException {
        LOGGER.info("Entering into  custom temp cfp details");
        ImtdCfpDetails tempCFP = null;
        if(company.getTempCfpDetailsSystemId()==0){
            tempCFP = ImtdCfpDetailsLocalServiceUtil.createImtdCfpDetails(0);
        }else{
            tempCFP = ImtdCfpDetailsLocalServiceUtil.getImtdCfpDetails(company.getTempCfpDetailsSystemId());
        }
        tempCFP.setCompanyMasterSid(Integer.valueOf(company.getCompanySystemId()));
        tempCFP.setCompanyId(company.getCompanyId());
        tempCFP.setCompanyNo(company.getCompanyNo());
        tempCFP.setCompanyName(company.getCompanyName());
        DynamicQuery query1 = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        query1.add(RestrictionsFactoryUtil.eq(ConstantsUtils.DESCRIPTION, company.getCompanyTypeValue()));
        List<HelperTable> list1 = HelperTableLocalServiceUtil.dynamicQuery(query1);
        HelperTable obj1 = HelperTableLocalServiceUtil.createHelperTable(0);
            for(int i=0;i<list1.size();i++) {
            obj1 = list1.get(i);
        }
        tempCFP.setCompanyType(String.valueOf(obj1.getHelperTableSid()));
        DynamicQuery query2 = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        query2.add(RestrictionsFactoryUtil.eq(ConstantsUtils.DESCRIPTION, String.valueOf(company.getCompanyStatusValue())));
        List<HelperTable> list2 = HelperTableLocalServiceUtil.dynamicQuery(query2);
        HelperTable obj2 = HelperTableLocalServiceUtil.createHelperTable(0);
            for(int i=0;i<list2.size();i++) {
            obj2 = list2.get(i);
        }
        tempCFP.setCompanyStatus(obj2.getHelperTableSid());
        tempCFP.setCompanyStartDate(com.stpl.app.util.GeneralCommonUtils.convertStringToDate(company.getCompanyStartDate()));
        tempCFP.setCompanyEndDate(com.stpl.app.util.GeneralCommonUtils.convertStringToDate(company.getCompanyEndDate()));
        tempCFP.setUsersSid(company.getUserID());
        tempCFP.setSessionId(company.getSessionID());
        tempCFP.setImtdCreatedDate(CommonUtils.getDateTime());
        tempCFP.setCfpDetailsCreatedDate(company.getCreatedDate());
        tempCFP.setCfpDetailsCreatedBy(company.getUserID());
        tempCFP.setCreatedDate(new Date());
        tempCFP.setCreatedBy(Integer.valueOf(company.getUserID()));
        tempCFP.setModifiedDate(new Date());
        tempCFP.setModifiedBy(Integer.valueOf(company.getUserID()));

        tempCFP.setCfpModelSid(company.getCompanyFamilyPlanSystemId());
        tempCFP.setCfpDetailsSid(company.getCfpDetailsSystemId());
        tempCFP.setCfpDetailsStartDate(company.getCompanyFamilyPlanStartDate());
        tempCFP.setCfpDetailsEndDate(company.getCompanyFamilyPlanEndDate());
        tempCFP.setCfpDetailsAttachedDate(new Date());
        tempCFP.setCfpDetailsAttachedStatus(company.getCompanyFamilyPlanStatus().getId());
        tempCFP.setCheckRecord(company.getCheckbox());
        tempCFP.setTradingPartnerContractNo(company.getTradingPartnerContractNo());

        if (tempCFP.getCfpDetailsSid() == 0) {
            tempCFP.setOperation(ConstantsUtils.A);
            tempCFP.setCfpDetailsModifiedDate(null);
            tempCFP.setCfpDetailsModifiedBy(null);
        } else {
            if (!company.isCheckFlag()) {
                tempCFP.setOperation(ConstantsUtils.A);

            } else {
                tempCFP.setOperation(ConstantsUtils.U);
                tempCFP.setCfpDetailsModifiedDate(new Date());
                tempCFP.setCfpDetailsModifiedBy(company.getUserID());
            }
        }
        LOGGER.info("End of custom temp cfp details");
        return tempCFP;
    }

    public void deleteTempCFPDetails(CompanyMasterDTO deleteCompany, final CommonLazyUtilDTO dto) throws PortalException, SystemException {
        LOGGER.info("Entering delete temp cfp details");
        try {
            if (deleteCompany != null) {
                final String sysId = String.valueOf(sessionDTO.getSystemId());
                final int systemId = (ConstantsUtils.NULL.equals(sysId)||StringUtils.EMPTY.equals(sysId.trim()))?0:Integer.valueOf(sysId);
                final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdCfpDetails.class);
                dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.CFP_SYSTEM_ID_DUP, systemId));
                dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_MASTER_ID, Integer.valueOf(deleteCompany.getCompanySystemId())));
                dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USERS_ID, dto.getUserId()));

                dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, dto.getSessionId()));
                final List<ImtdCfpDetails> list = ImtdCfpDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
                if (list.size() > 0) {
                    ImtdCfpDetails tempCfpDetails = list.get(0);
                    tempCfpDetails.setOperation(ConstantsUtils.D);
                    tempCfpDetails.setCheckRecord(false);
                    ImtdCfpDetailsLocalServiceUtil.updateImtdCfpDetails(tempCfpDetails);
                }
            }
            LOGGER.info("End of delete temp cfp details");
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public Object deleteAllTempCFPDetails(final CommonLazyUtilDTO dto) throws PortalException, SystemException {
        final String sysId = String.valueOf(sessionDTO.getSystemId());
        return ImtdCfpDetailsLocalServiceUtil.updateAll(dto.getUserId(), dto.getSessionId(), null, null, sysId, null, null, null);
    }

    public Object deleteTempDetails(final CommonLazyUtilDTO dto) throws PortalException, SystemException {
        return CFPSEARCHLOGICDAO.deleteAllTempCfpDetails(dto.getUserId(), dto.getSessionId(), null, Boolean.FALSE, dto.getDate(), null);
    }

    public Object deleteAllTempCFPDetailsInEdit(final CommonLazyUtilDTO dto) throws PortalException, SystemException {
        return CFPSEARCHLOGICDAO.deleteAllTempCfpDetails(dto.getUserId(), dto.getSessionId(), null, Boolean.TRUE, dto.getDate(), null);
    }

    public List<Object> getCompanyListFromTempCfpDetails(final CommonLazyUtilDTO dto) throws PortalException, SystemException {
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdCfpDetails.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USERS_ID, dto.getUserId()));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, dto.getSessionId()));
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_MASTER_ID));
        final List<Object> companyList = CFPSEARCHLOGICDAO.getTempCfpDetails(cfpDynamicQuery);
        return companyList;
    }

    /**
     * getting count for CompanyQualifierName.
     *
     * @param filterText the filter text
     * @return the lazy company qualifier name count
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public static int getLazySelectedCompaniesCount(final CommonLazyUtilDTO dto,final BeanSearchCriteria searchCriteria) throws PortalException, SystemException {

        List<Object[]> returnList = new ArrayList<>();
        returnList = CFPSEARCHLOGICDAO.getSelectedCompanies(0, 0, dto.getUserId(), dto.getSessionId(), null, null, null, "count");
        if (returnList != null && !returnList.isEmpty()) {
            LOGGER.info("selected count :" + Integer.valueOf(String.valueOf(returnList.get(0))));
            return Integer.valueOf(String.valueOf(returnList.get(0)));
        } else {
            LOGGER.info("selected count : 0");
            return 0;
        }

       
    }

    /**
     * getting results for selected companies table.
     *
     * @param filterText the filter text
     * @return the lazy company qualifier name count
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public static List<CompanyMasterDTO> getLazySelectedCompaniesDeatils(final CommonLazyUtilDTO dto, int start, int end) throws PortalException, SystemException {

        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdCfpDetails.class);
        List<CompanyMasterDTO> companyList = new ArrayList<CompanyMasterDTO>();
        cfpDynamicQuery.setLimit(start, end);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USERS_ID, dto.getUserId()));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, dto.getSessionId()));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.D));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.F));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.COMPANY_MASTER_ID));
        final List<ImtdCfpDetails> returnList = CFPSEARCHLOGICDAO.getTempCfpDetails(cfpDynamicQuery);
        LOGGER.info("selected results :" + returnList.size());
        return getCustomizedCompanyMasterDTO(returnList, companyList);

    }

    public static List<CompanyMasterDTO> getSelectedCompaniesDeatils(final CommonLazyUtilDTO dto, int start, int end,final List<OrderByColumn> list,final BeanSearchCriteria searchCriteria,String operation ) throws PortalException, SystemException {
         String columnName=ConstantsUtils.COMPANYNAMES;
            String orberBy=ConstantsUtils.ASC;

            if(list!=null){
            for (final Iterator<OrderByColumn> iterator = list.iterator(); iterator.hasNext();) {
                final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();

                if(orderByColumn.getName().equals(ConstantsUtils.COMPANY_NO)){
                    columnName=Company_no;
                }else if(orderByColumn.getName().equals(ConstantsUtils.COMPANY_NAME)){
                    columnName=ConstantsUtils.COMPANYNAMES;
                }else if(orderByColumn.getName().equals(ConstantsUtils.COMPANY_ID)){
                    columnName=Company_Id;
                }else if(orderByColumn.getName().equals(ConstantsUtils.COMPANY_TYPE_VALUE)){
                    columnName=Ctype;
                }else if(orderByColumn.getName().equals(ConstantsUtils.COMPANY_STATUS_VALUE)){
                    columnName=Cstatus;
                }else if(orderByColumn.getName().equals(ConstantsUtils.TRADE_CLASS_VALUE)){
                    columnName=Trade;
                }else if(orderByColumn.getName().equals(ConstantsUtils.COMPANY_CATEGORY_VALUE)){
                    columnName=Category;
                }else if(orderByColumn.getName().equals(ConstantsUtils.COMPANY_GROUP_VALUE)){
                    columnName=Cgroup;
                }
                if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                        orberBy=ConstantsUtils.ASC;

                } else {
                        orberBy=ConstantsUtils.DESC;
                }

            }}

        
        List<Object[]> returnList = new ArrayList<>();
        returnList = CFPSEARCHLOGICDAO.getSelectedCompanies(start, end, dto.getUserId(), dto.getSessionId(), columnName, orberBy, getFilterMapForAvailableCompanies(searchCriteria), operation);
        LOGGER.info("selected results :" + returnList.size());
        return getCustomizedSelectedCompanies(returnList);

    }

    private static List<CompanyMasterDTO> getCustomizedCompanyMasterDTO(List<ImtdCfpDetails> returnList, List<CompanyMasterDTO> companyList) throws PortalException, SystemException {
        CompanyMasterDTO companyDTO;
        Map<Integer, String> hm = com.stpl.app.util.GeneralCommonUtils.getCodeDescription();
        for (ImtdCfpDetails tempCfp : returnList) {
            companyDTO = new CompanyMasterDTO();
            companyDTO.setCompanySystemId(String.valueOf(tempCfp.getCompanyMasterSid()));
            companyDTO.setCompanyTypeValue(hm.get(Integer.parseInt(tempCfp.getCompanyType().isEmpty()?ConstantsUtils.ZERO:tempCfp.getCompanyType())));
            companyDTO.setCompanyNo(tempCfp.getCompanyNo());
            companyDTO.setCompanyName(tempCfp.getCompanyName());
            companyDTO.setCompanyStatusValue(hm.get(tempCfp.getCompanyStatus()));
            companyDTO.setTempCfpDetailsSystemId(tempCfp.getImtdCfpDetailsSid());
            companyList.add(companyDTO);
        }
        return companyList;
    }

    private static List<CompanyMasterDTO> getCustomizedSelectedCompanies(List<Object[]> returnList) throws PortalException, SystemException {
        CompanyMasterDTO companyDTO;
        List<CompanyMasterDTO> companyList = new ArrayList<CompanyMasterDTO>();
        Map<Integer, String> hm = com.stpl.app.util.GeneralCommonUtils.getCodeDescription();
        for (Object[] tempCfp : returnList) {
            companyDTO = new CompanyMasterDTO();
            companyDTO.setCompanySystemId(String.valueOf(tempCfp[0]));
            CompanyMaster master = CompanyMasterLocalServiceUtil.getCompanyMaster(Integer.valueOf(companyDTO.getCompanySystemId()));
            if(tempCfp[3]!=null && !ConstantsUtils.NULL.equals(String.valueOf(tempCfp[3])) && Integer.parseInt(String.valueOf(tempCfp[3]))!=0){
            companyDTO.setCompanyTypeValue(hm.get(Integer.parseInt(tempCfp[3]==null || StringUtils.EMPTY.equals(String.valueOf(tempCfp[3]))?ConstantsUtils.ZERO:String.valueOf(tempCfp[3]))));
            }else{
                companyDTO.setCompanyTypeValue(StringUtils.EMPTY);
            }
            companyDTO.setCompanyNo(String.valueOf(tempCfp[1]));
            companyDTO.setCompanyName(String.valueOf(tempCfp[2]));
            if(tempCfp[4]!=null && !ConstantsUtils.NULL.equals(String.valueOf(tempCfp[4])) && Integer.parseInt(String.valueOf(tempCfp[4]))!=0){
            companyDTO.setCompanyStatusValue(hm.get((Integer)tempCfp[4]));
            }else{
                companyDTO.setCompanyStatusValue(StringUtils.EMPTY);
            }
            companyDTO.setTempCfpDetailsSystemId((Integer)tempCfp[5]);
           if(master!=null){
            if(master.getCompanyCategory()!=0){
                    companyDTO.setCompanyCategoryValue(hm.get(master.getCompanyCategory()));
                }
           if(StringUtils.isNotBlank(master.getCompanyGroup())&& !master.getCompanyGroup().equals(ConstantsUtils.ZERO)){
                    companyDTO.setCompanyGroupValue(hm.get(Integer.valueOf(master.getCompanyGroup())));
                }

            }
           if(tempCfp[10]!=null){
                companyDTO.setTradeClassValue(String.valueOf(tempCfp[10]));
            }
           if(tempCfp[11]!=null){
                companyDTO.setCompanyId(String.valueOf(tempCfp[11]));
            }
            companyList.add(companyDTO);
        }
        return companyList;
    }

    /**
     * returns list contains Selected Companies.
     *
     * @param cfpId the cfp id
     * @return the selected company list
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    public void addToTempCfpDetailsEdit(final int cfpId) throws PortalException, SystemException {

        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        String sessionId = sessionDTO.getUiSessionId();
        String tempCreatedDate = sessionDTO.getSessionDate();
        CFPSEARCHLOGICDAO.insertTempCfpDetailsInEdit(userId, sessionId, tempCreatedDate, String.valueOf(cfpId), null, null, null, null);
    }

    public static int getLazyTempCfpDetailsCount(final CommonLazyUtilDTO dto,final BeanSearchCriteria searchCriteria) throws PortalException, SystemException, Exception {
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdCfpDetails.class);

        
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USERS_ID, dto.getUserId()));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, dto.getSessionId()));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.D));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.F));

        if (searchCriteria != null && searchCriteria.getFilters() != null) {
            for (Container.Filter filter : searchCriteria.getFilters()) {

                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;
                    if(StringUtils.EMPTY.equals(filterString)){
                        filterString = ConstantsUtils.PERCENCTAGE;
                    }

                    if (ConstantsUtils.COMPANY_ID.equals(stringFilter.getPropertyId())) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.COMPANY_ID, filterString));
                    }
                    if (ConstantsUtils.COMPANY_NO.equals(stringFilter.getPropertyId())) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.COMPANY_NO, filterString));
                    }
                    if (ConstantsUtils.COMPANY_NAME.equals(stringFilter.getPropertyId())) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.COMPANY_NAME, filterString));
                    }

                    if (ConstantsUtils.TRADING_PARTNER_CONTRACT_NO .equals(stringFilter.getPropertyId())) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.TRADING_PARTNER_CONTRACT_NO , filterString));
                    }

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;

                    if (stringFilter.getValue() instanceof Integer) {

                        Integer filterValue = (Integer) stringFilter.getValue();

                        if (ConstantsUtils.CFP_STATUS.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                cfpDynamicQuery.add(RestrictionsFactoryUtil.eq("cfpDetailsAttachedStatus", filterValue));
                            }
                        }
                        if (ConstantsUtils.COMPANY_STATUS_VALUE.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                cfpDynamicQuery.add(RestrictionsFactoryUtil.eq("companyStatus", filterValue));
                            }
                        }
                        if (ConstantsUtils.COMPANY_TYPE_VALUE.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                cfpDynamicQuery.add(RestrictionsFactoryUtil.eq("companyType", String.valueOf(filterValue)));
                            }
                        }
                    } else if (stringFilter.getValue() instanceof Date) {

                        Date filterString = (Date) stringFilter.getValue();
                        if (ConstantsUtils.CFP_START_DATE.equals(stringFilter.getPropertyId())) {
                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                cfpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.Cfp_Start_Date, filterString));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                cfpDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.Cfp_Start_Date, filterString));
                            }
                        } else if (ConstantsUtils.CFP_END_DATE.equals(stringFilter.getPropertyId())) {
                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                cfpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.Cfp_End_Date, filterString));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                cfpDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.Cfp_End_Date, filterString));
                            }
                        }else if (ConstantsUtils.Cfp_Company_Start_Date.equals(stringFilter.getPropertyId())) {
                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                cfpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.Company_Start_Date, filterString));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                cfpDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.Company_Start_Date, filterString));
                            }
                        }else if (ConstantsUtils.Cfp_Company_End_Date.equals(stringFilter.getPropertyId())) {
                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                cfpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.Company_End_Date, filterString));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                cfpDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.Company_End_Date, filterString));
                            }
                        }else if (ConstantsUtils.Cfp_Attached_Date.equals(stringFilter.getPropertyId())) {
                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                cfpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.Cfp_Details_Attached_Date, filterString));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                cfpDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.Cfp_Details_Attached_Date, filterString));
                            }
                        }
                    } else if (stringFilter.getValue() instanceof String) {

                        String filterValue = (String) stringFilter.getValue();

                       
                        if (ConstantsUtils.COMPANY_TYPE_VALUE.equals(stringFilter.getPropertyId())) {

                            if (StringUtils.EMPTY.equals(filterValue) || ConstantsUtils.NULL.equals(filterValue)) {

                                if (ConstantsUtils.COMPANY_TYPE_VALUE.equals(stringFilter.getPropertyId())) {
                                    cfpDynamicQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.like(ConstantsUtils.COMPANY_TYPE_VALUE, ConstantsUtils.PERCENCTAGE),RestrictionsFactoryUtil.isNull(ConstantsUtils.COMPANY_TYPE_VALUE)));
                                }

                            } else {
                                if (ConstantsUtils.COMPANY_TYPE_VALUE.equals(stringFilter.getPropertyId())) {
                                    cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_TYPE_VALUE, filterValue));
                                }
                            }

                        }

                    }
                } else if (filter instanceof Between) {

                    Between stringFilter = (Between) filter;
                    Date filterString = (Date) stringFilter.getStartValue();
                    Date filterString1 = (Date) stringFilter.getEndValue();

                    if (ConstantsUtils.CFP_START_DATE.equals(stringFilter.getPropertyId())) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.Cfp_Start_Date, filterString));
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.Cfp_Start_Date, filterString1));
                    }

                    if (ConstantsUtils.CFP_END_DATE.equals(stringFilter.getPropertyId())) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.Cfp_End_Date, filterString));
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.Cfp_End_Date, filterString1));
                    }

                    if (ConstantsUtils.Cfp_Company_Start_Date.equals(stringFilter.getPropertyId())) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.Company_Start_Date, filterString));
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.Company_Start_Date, filterString1));
                    }

                    if (ConstantsUtils.Cfp_Company_End_Date.equals(stringFilter.getPropertyId())) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.Company_End_Date, filterString));
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.Company_End_Date, filterString1));
                    }

                    if (ConstantsUtils.Cfp_Attached_Date.equals(stringFilter.getPropertyId())) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.Cfp_Details_Attached_Date, filterString));
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.Cfp_Details_Attached_Date, filterString1));
                    }

                }

            }
        }

        final int count = CFPSEARCHLOGICDAO.getTempCfpDetailsCount(cfpDynamicQuery);
        LOGGER.info("temp companies count :" + count);
        return count;
    }

    public static List<CFPCompanyDTO> getLazyTempCfpDetailsResults(final int start, final int end, final CommonLazyUtilDTO dto) throws PortalException, SystemException {
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdCfpDetails.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USERS_ID, dto.getUserId()));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, dto.getSessionId()));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.D));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.F));
        cfpDynamicQuery.setLimit(start, end);
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.COMPANY_MASTER_ID));
        final List<ImtdCfpDetails> modelList = CFPSEARCHLOGICDAO.getTempCfpDetails(cfpDynamicQuery);
        List<CFPCompanyDTO> finalList = new ArrayList<CFPCompanyDTO>();
        final List<CFPCompanyDTO> tempCfpList = getCustomizedCFPCompanyDTO(modelList, finalList, dto);
        LOGGER.info("return results on tempCfpDetails size " + ((tempCfpList == null) ? tempCfpList : tempCfpList.size()));
        return tempCfpList;
    }

    private static List<CFPCompanyDTO> getCustomizedCFPCompanyDTO(List<ImtdCfpDetails> modelList, List<CFPCompanyDTO> finalList, final CommonLazyUtilDTO utilDTO) throws PortalException, SystemException {
        CFPCompanyDTO dto;

        Map<Integer, String> hm = com.stpl.app.util.GeneralCommonUtils.getCodeDescription();
        for (ImtdCfpDetails model : modelList) {
            dto = new CFPCompanyDTO();
            dto.setCheckbox(model.isCheckRecord());
            dto.setCompanyId(model.getCompanyId());
            dto.setCompanyNo(model.getCompanyNo());
            dto.setCompanyName(model.getCompanyName());
            dto.setCompanyTypeValue(hm.get(Integer.parseInt(model.getCompanyType().isEmpty()?ConstantsUtils.ZERO:model.getCompanyType())));
            dto.setCompanyStatusValue(hm.get(model.getCompanyStatus()));
            dto.setCompanySystemId(String.valueOf(model.getCompanyMasterSid()));
            dto.setAttachedDate(CommonUtils.getDateTime(ConstantsUtils.DATE_FORMAT, model.getCfpDetailsAttachedDate()));
            dto.setCompanyStartDate(CommonUtils.getDateTime(ConstantsUtils.DATE_FORMAT, model.getCompanyStartDate()));
            dto.setCompanyEndDate(CommonUtils.getDateTime(ConstantsUtils.DATE_FORMAT, model.getCompanyEndDate()));
            dto.setCompanyFamilyPlanStartDate(model.getCfpDetailsStartDate());
            dto.setCompanyFamilyPlanEndDate(model.getCfpDetailsEndDate());
            dto.setCompanyFamilyPlanStatus(helperListUtil.getIdHelperDTOMap().get(model.getCfpDetailsAttachedStatus()));
            dto.setCompanyFamilyPlanSystemId(model.getCfpModelSid());
            dto.setTradingPartnerContractNo(model.getTradingPartnerContractNo());
            dto.setCfpDetailsSystemId(model.getCfpDetailsSid());
            dto.setOperationStatus(model.getOperation());
            dto.setCreatedDate(model.getCfpDetailsCreatedDate());
            dto.setUserID(model.getUsersSid());
            dto.setSessionID(model.getSessionId());
            dto.setTempCfpDetailsSystemId(model.getImtdCfpDetailsSid());
            dto.setCompanyFamilyPlanNo(utilDTO.getCfpNo());
            dto.setCompanyFamilyPlanName(utilDTO.getCfpName());
            finalList.add(dto);
        }
        return finalList;
    }

    public void populateCheckedRecords(String userId, String sessionId, String createdDate, Object massField, Object value, Date date) throws PortalException, SystemException {
        int sysId = 0;
        if (!StringUtils.EMPTY.equals(value)) {

            List<HelperDTO> list = getDropDownList(UIUtils.CFP_STATUS);

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDescription().equals(value)) {
                    sysId = list.get(i).getSystemId();
                }
            }

        }
        CFPSEARCHLOGICDAO.updateForPopulate(userId, sessionId, createdDate, null, massField, sysId, CommonUtils.getDateTime(ConstantsUtils.YMD_FORMAT, date), null);
    }

    public void populateAll(String userId, String sessionId, String createdDate, Object massField, Object value, Date date, String clickEvent) throws PortalException, SystemException {
        int sysId = 0;
        if (!StringUtils.EMPTY.equals(value)) {

            List<HelperDTO> list = getDropDownList(UIUtils.CFP_STATUS);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDescription().equals(value)) {
                    sysId = list.get(i).getSystemId();
                }
            }

        }
        if ("populate".equalsIgnoreCase(clickEvent)) {
            CFPSEARCHLOGICDAO.updateForPopulateAll(userId, sessionId, createdDate, null, massField, Integer.valueOf(String.valueOf(sysId)), CommonUtils.getDateTime(ConstantsUtils.YMD_FORMAT, date), null);
        } else if ("check".equalsIgnoreCase(clickEvent)) {
            CFPSEARCHLOGICDAO.updateForPopulateAll(userId, sessionId, createdDate, null, massField, Integer.valueOf(String.valueOf(sysId)), CommonUtils.getDateTime(ConstantsUtils.YMD_FORMAT, date), clickEvent);
        } else if ("uncheck".equalsIgnoreCase(clickEvent)) {
            CFPSEARCHLOGICDAO.updateForPopulateAll(userId, sessionId, createdDate, null, massField, Integer.valueOf(String.valueOf(sysId)), CommonUtils.getDateTime(ConstantsUtils.YMD_FORMAT, date), clickEvent);
        }

    }

    public List<Object> validateNull(String userId, String sessionId, String tempCreatedDate, String process) {

        return (List<Object>) CFPSEARCHLOGICDAO.validateTempCFPDeatils(userId, sessionId, tempCreatedDate, process);
    }

    public static int getLazyTempCfpDetailsCount(final String cfpMasterSystemId,final BeanSearchCriteria searchCriteria,String record) throws PortalException, SystemException, Exception {
        Map<Object, Object> filterMap = getFilterMapForCompanyDetails(searchCriteria);
            if(!StringUtils.isBlank(record)){
            if(record.contains(ConstantsUtils.CURRENT)){
                filterMap.put(ConstantsUtils.CURRENT, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
                if(record.contains(ConstantsUtils.HISTORY)){
                filterMap.put(ConstantsUtils.HISTORY, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
                if(record.contains(ConstantsUtils.FUTURE)){
                filterMap.put(ConstantsUtils.FUTURE, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
        }
        final int count = Integer.valueOf(((List) CFPSEARCHLOGICDAO.getTempCfpDetailsCount(cfpMasterSystemId, "count",filterMap)).get(0).toString());
        LOGGER.info("available count in view mode :" + count);
        return count;
    }

    public static List<CFPCompanyDTO> getLazyTempCfpDetailsResults(final int start, final int end, final String cfpMasterSystemId, String[] cfpdetails,final List<OrderByColumn> orderByColumns,final BeanSearchCriteria searchCriteria,String record) throws PortalException, SystemException {

        CommonUtils.loadTempViewColumnName();
        String column = ConstantsUtils.COMPANYNAMES;
        String orderBy = ConstantsUtils.ASC;

        for (final Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
            column=CommonUtils.getDBTempViewColumnName(orderByColumn.getName());            
            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
               orderBy=ConstantsUtils.ASC;
            } else {
               orderBy=ConstantsUtils.DESC;
            }
        }
           if(StringUtils.isBlank(column)){
            column = ConstantsUtils.COMPANYNAMES;
        }

        Map<Object, Object> filterMap = getFilterMapForCompanyDetails(searchCriteria);
            if(!StringUtils.isBlank(record)){
            if(record.contains(ConstantsUtils.CURRENT)){
                filterMap.put(ConstantsUtils.CURRENT, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
                if(record.contains(ConstantsUtils.HISTORY)){
                filterMap.put(ConstantsUtils.HISTORY, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
                if(record.contains(ConstantsUtils.FUTURE)){
                filterMap.put(ConstantsUtils.FUTURE, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
        }
        final List<Object[]> modelList = CFPSEARCHLOGICDAO.getTempCfpDetails(String.valueOf(start), String.valueOf(end), cfpMasterSystemId, searchresults,column,orderBy,getFilterMapForCompanyDetails(searchCriteria));
        List<CFPCompanyDTO> finalList = new ArrayList<CFPCompanyDTO>();
        final List<CFPCompanyDTO> tempCfpList = getCustomizedCFPCompanyDTO(modelList, finalList, cfpdetails);
        LOGGER.info("available results in view mode :" + ((tempCfpList == null) ? tempCfpList : tempCfpList.size()));
        return tempCfpList;
    }

    private static List<CFPCompanyDTO> getCustomizedCFPCompanyDTO(List<Object[]> modelList, List<CFPCompanyDTO> finalList, String[] cfpdetails) throws SystemException, PortalException {
        LOGGER.info("Inside getCustomizedCFPCompanyDTO");
        CFPCompanyDTO dto;
        SimpleDateFormat sdf = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
        Map<Integer, String> hm = com.stpl.app.util.GeneralCommonUtils.getCodeDescription();
        Map<Integer, String> userMap= StplSecurity.getUserName();
        for (Object[] value : modelList) {
            dto = new CFPCompanyDTO();
            dto.setCompanySystemId(value[0] != null ? value[0].toString() : StringUtils.EMPTY);
            dto.setCompanyId(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
            dto.setCompanyNo(value[2] != null ? value[2].toString() : StringUtils.EMPTY);
            dto.setCompanyName(value[3] != null ? value[3].toString() : StringUtils.EMPTY);
            dto.setCompanyTypeValue(hm.get(Integer.parseInt(value[4] != null ? value[4].toString() : StringUtils.EMPTY)));
            dto.setCompanyStartDate(CommonUtils.formateDateString(CommonUtils.MMDDYYYY, value[5] != null ? (Date) value[5] : null));
            dto.setCompanyEndDate(CommonUtils.formateDateString(CommonUtils.MMDDYYYY, value[6] != null ? (Date) value[6] : null));

            
            
            dto.setCompanyStatusValue(value[7] != null ? hm.get((Integer)value[7]) : StringUtils.EMPTY);
            dto.setTradingPartnerContractNo(value[8] != null ? value[8].toString() : StringUtils.EMPTY);
            dto.setAttachedDate(CommonUtils.formateDateString(CommonUtils.MMDDYYYY, value[9] != null ? (Date) value[9] : null));
            try{
            if(value[5] != null){
                    dto.setCfpCompanyStartDate(sdf.parse(dto.getCompanyStartDate()));
                }
            if(value[6] != null){
                    dto.setCfpCompanyEndDate(sdf.parse(dto.getCompanyEndDate()));
                }

             if(value[9] != null){
                    dto.setCfpAttachedDate(sdf.parse(dto.getAttachedDate()));
                }
            }catch(ParseException ex){
                LOGGER.error(ex);;
            }
            dto.setCompanyFamilyPlanStartDate(value[10] != null ? (Date) value[10] : null);
            dto.setCompanyFamilyPlanEndDate(value[11] != null ? (Date) value[11] : null);
            dto.setCompanyFamilyPlanStatusValue(value[12] != null ? hm.get((Integer)value[12]) : StringUtils.EMPTY);
            dto.setCompanyFamilyPlanNo(cfpdetails[0]);
            dto.setCompanyFamilyPlanName(cfpdetails[1]);

            int sysId = (Integer) value[0];

            CompanyMaster master = CompanyMasterLocalServiceUtil.getCompanyMaster(sysId);
                    if(master.getCompanyCategory()!=0){
                dto.setCompanyCategory(getDescription(master.getCompanyCategory()));
            }
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(CompanyTradeClass.class);
            final SimpleDateFormat fmt = new SimpleDateFormat(ConstantsUtils.YMD_FORMAT1);
            String todayDate = fmt.format(new Date());
            query.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_MASTER_ID, sysId));
            query.add(RestrictionsFactoryUtil.le(ConstantsUtils.TRADE_CLASS_START_DATE, new Date()));
            query.addOrder(OrderFactoryUtil.desc(ConstantsUtils.TRADE_CLASS_START_DATE));
                    List<CompanyTradeClass> companyTradeClass=CompanyTradeClassLocalServiceUtil.dynamicQuery(query);
                    if(companyTradeClass!=null && companyTradeClass.size()!=0 && companyTradeClass.get(0).getCompanyTradeClass()!=0){
                String tradeClass = getDescription(companyTradeClass.get(0).getCompanyTradeClass());
                dto.setTradeClass(tradeClass);
            }
                    if(value[16]!=null){
                    dto.setModifiedDate((Date)value[16]);
            }

                    dto.setModifiedBy(StringUtils.isBlank(String.valueOf(value[17])) ? StringUtils.EMPTY :userMap.get(Integer.valueOf(String.valueOf(value[17]))));

            if (value[18] != null) {
                dto.setCreatedDate((Date) value[18]);
            }

            dto.setCreatedBy(StringUtils.isBlank(String.valueOf(value[19])) ? StringUtils.EMPTY : userMap.get(Integer.valueOf(String.valueOf(value[19]))));

            finalList.add(dto);
            LOGGER.info("Ending getCustomizedCFPCompanyDTO");
        }
        return finalList;
    }
    public void deleteNotesTabAttachment(final int systemId){
        try {
            final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MasterDataFiles.class);
            dynamicQuery.add(RestrictionsFactoryUtil.eq("masterTableSid", systemId));
            dynamicQuery.add(RestrictionsFactoryUtil.like("masterTableName", ConstantsUtils.CFP_MODEL));
            final List<MasterDataFiles> masterDataFiles = MasterDataFilesLocalServiceUtil.dynamicQuery(dynamicQuery);
            if (!masterDataFiles.isEmpty()) {
                for (MasterDataFiles masterDataFile : masterDataFiles) {
                    MasterDataFilesLocalServiceUtil.deleteMasterDataFiles(masterDataFile.getMasterDataFilesSid());
                }
            }
        } catch (SystemException | PortalException e) {
            LOGGER.error(e);
        }
    }
    public static List<Object[]> searchCompanyHelperTableSort(final int start, final int end, final CommonLazyUtilDTO dto, final List<OrderByColumn> orderByColumns,final BeanSearchCriteria searchCriteria,String record,Boolean iscount) throws PortalException, SystemException {
        LOGGER.info("Entering searchCompany()");
        try {

            CommonUtils.loadTempColumnName();
            String column = ConstantsUtils.COMPANYNAMES;
            String orderBy = ConstantsUtils.ASC;

            for (final Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
                final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
                column = CommonUtils.getDBTempColumnName(orderByColumn.getName());
                if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                    orderBy = ConstantsUtils.ASC;
                } else {
                    orderBy = ConstantsUtils.DESC;
                }
            }

            if (StringUtils.isBlank(column)) {
                column = ConstantsUtils.COMPANYNAMES;
            }

            
            Map<Object, Object> filterMap = getFilterMapForCompanyDetails(searchCriteria);
            if(!StringUtils.isBlank(record)){
            if(record.contains(ConstantsUtils.CURRENT)){
                    filterMap.put(ConstantsUtils.CURRENT, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
                if(record.contains(ConstantsUtils.HISTORY)){
                    filterMap.put(ConstantsUtils.HISTORY, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
                if(record.contains(ConstantsUtils.FUTURE)){
                    filterMap.put(ConstantsUtils.FUTURE, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
            }
            if(iscount){
                filterMap.put("Count", "true");
            }else{
                filterMap.put("Count", "false");
            }

            List list = ImtdCfpDetailsLocalServiceUtil.getTempCfpDetails(dto.getUserId(), dto.getSessionId(), start, end, column, orderBy,filterMap);

            LOGGER.info("return results on tempCfpDetails size " + ((list == null) ? list : list.size()));
            return list;
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    public static List<CFPCompanyDTO> getCustomizedTempCFPCompanyDTO(List modelList, List<CFPCompanyDTO> finalList, final CommonLazyUtilDTO utilDTO) {
        try {
            Map<Integer, String> hm = com.stpl.app.util.GeneralCommonUtils.getCodeDescription();
        Map<Integer, String> userMap =StplSecurity.getUserName();
            if (modelList != null) {
                for (int i = 0; i < modelList.size(); i++) {
                    final CFPCompanyDTO searchCompanyForm = new CFPCompanyDTO();

                    final Object[] obj = (Object[]) modelList.get(i);
                    searchCompanyForm.setCheckbox((Boolean) obj[0]);
                    searchCompanyForm.setCompanyFamilyPlanNo(utilDTO.getCfpNo());
                    searchCompanyForm.setCompanyFamilyPlanName(utilDTO.getCfpName());

                    if(obj[1]!=null){
                    searchCompanyForm.setCompanyFamilyPlanStatus(helperListUtil.getIdHelperDTOMap().get((Integer)obj[1]));
                    }
                    if (obj[3] != null) {
                        searchCompanyForm.setCompanyFamilyPlanStartDate((Date) obj[3]);
                    }
                    if (obj[4] != null) {
                        searchCompanyForm.setCompanyFamilyPlanEndDate((Date) obj[4]);
                    }
                    searchCompanyForm.setCompanyId(StringUtils.isBlank(String.valueOf(obj[5])) ? StringUtils.EMPTY : String.valueOf(obj[5]));
                    searchCompanyForm.setCompanyNo(StringUtils.isBlank(String.valueOf(obj[6])) ? StringUtils.EMPTY : String.valueOf(obj[6]));
                    searchCompanyForm.setCompanyName(StringUtils.isBlank(String.valueOf(obj[7])) ? StringUtils.EMPTY : String.valueOf(obj[7]));
                    if (!String.valueOf(obj[8]).equalsIgnoreCase(ConstantsUtils.NULL) && !String.valueOf(obj[8]).isEmpty()) {
                        searchCompanyForm.setCompanyStartDate(CommonUtils.getDateTime(ConstantsUtils.DATE_FORMAT, (Date) obj[8]));
                        searchCompanyForm.setCfpCompanyStartDate((Date)obj[8]);
                    }
                    if (!String.valueOf(obj[9]).equalsIgnoreCase(ConstantsUtils.NULL) && !String.valueOf(obj[9]).isEmpty()) {
                        searchCompanyForm.setCompanyEndDate(CommonUtils.getDateTime(ConstantsUtils.DATE_FORMAT, (Date) obj[9]));
                        searchCompanyForm.setCfpCompanyEndDate((Date)obj[9]);
                    } else {

                    }
                    searchCompanyForm.setCompanyStatusValue(StringUtils.isBlank(String.valueOf(obj[11])) || (ConstantsUtils.SELECT_ONE).equalsIgnoreCase(String.valueOf(obj[11])) ? StringUtils.EMPTY : String.valueOf(obj[11]));
                    searchCompanyForm.setCompanyTypeValue(StringUtils.isBlank(String.valueOf(obj[13])) || (ConstantsUtils.SELECT_ONE).equalsIgnoreCase(String.valueOf(obj[13])) ? StringUtils.EMPTY : String.valueOf(obj[13]));
                    searchCompanyForm.setTradingPartnerContractNo(obj[14] == null ? StringUtils.EMPTY : String.valueOf(obj[14]));
                    String date = CommonUtils.getDateTime(ConstantsUtils.DATE_FORMAT,(Date)obj[15]);
                    searchCompanyForm.setAttachedDate(obj[15] == null ? StringUtils.EMPTY : date);
                    if(obj[15] != null){
                        searchCompanyForm.setCfpAttachedDate((Date)obj[15]);
                    }
                    searchCompanyForm.setCompanySystemId(StringUtils.isBlank(String.valueOf(obj[16])) ? StringUtils.EMPTY : String.valueOf(obj[16]));
                    searchCompanyForm.setCompanyFamilyPlanSystemId(obj[17] == null ? 0 : (Integer) obj[17]);
                    searchCompanyForm.setCfpDetailsSystemId(obj[18] == null ? 0 : (Integer) obj[18]);
                    searchCompanyForm.setOperationStatus(StringUtils.isBlank(String.valueOf(obj[19])) ? StringUtils.EMPTY : String.valueOf(obj[19]));
                    if (obj[20] != null) {
                        searchCompanyForm.setCreatedDate((Date) obj[20]);
                    }
                    searchCompanyForm.setUserID(String.valueOf(obj[21]));
                    searchCompanyForm.setSessionID(String.valueOf(obj[22]));
                    searchCompanyForm.setTempCfpDetailsSystemId(obj[23] == null ? 0 : (Integer) obj[23]);
                    int sysId = (Integer) obj[24];

                    CompanyMaster master = CompanyMasterLocalServiceUtil.getCompanyMaster(sysId);
                    if(master.getCompanyCategory()!=0){
                        searchCompanyForm.setCompanyCategory(getDescription(master.getCompanyCategory()));
                    }
                    DynamicQuery query = DynamicQueryFactoryUtil.forClass(CompanyTradeClass.class);
                    final SimpleDateFormat fmt = new SimpleDateFormat(ConstantsUtils.YMD_FORMAT1);
                    String todayDate = fmt.format(new Date());
                    query.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_MASTER_ID, sysId));
                    query.add(RestrictionsFactoryUtil.ge(ConstantsUtils.TRADE_CLASS_START_DATE, new Date()));
                    query.addOrder(OrderFactoryUtil.desc(ConstantsUtils.TRADE_CLASS_START_DATE));
                    List<CompanyTradeClass> companyTradeClass=CompanyTradeClassLocalServiceUtil.dynamicQuery(query);
                     if(companyTradeClass!=null && !(companyTradeClass.size()==0)){
                        String tradeClass = getDescription(companyTradeClass.get(0).getCompanyTradeClass());
                        searchCompanyForm.setTradeClass(tradeClass);
                    }
                    searchCompanyForm.setCompanySystemId(obj[24] == null ? ConstantsUtils.ZERO : String.valueOf(sysId));
                    searchCompanyForm.setCreatedBy(StringUtils.isBlank(String.valueOf(obj[25])) ? StringUtils.EMPTY : userMap.get(Integer.valueOf(String.valueOf(obj[25]))));
                    if (obj[26] != null) {
                        searchCompanyForm.setModifiedDate((Date) obj[26]);
                    }
                    if (obj[27] != null) {
                        searchCompanyForm.setModifiedBy(StringUtils.isBlank(String.valueOf(obj[27])) ? StringUtils.EMPTY : userMap.get(Integer.valueOf(String.valueOf(obj[27]))));
                    }
                    finalList.add(searchCompanyForm);

                }
            }
        }catch (PortalException | SystemException | NumberFormatException e) {
            LOGGER.error(e);
        }
        return finalList;
    }

    public static Map getFilterMapForAvailableCompanies(final BeanSearchCriteria searchCriteria) {

        Map<Object, Object> filterMap = new HashMap<Object, Object>();

        if (searchCriteria != null && searchCriteria.getFilters() != null) {
            for (Container.Filter filter : searchCriteria.getFilters()) {

                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;

                    if (StringUtils.EMPTY.equals(filterString)) {
                        filterString = StringUtils.EMPTY;
                    }

                    if (ConstantsUtils.COMPANY_ID.equals(stringFilter.getPropertyId())) {
                        filterMap.put(ConstantsUtils.COMPANY_ID, filterString);
                    }

                    if (ConstantsUtils.COMPANY_NO.equals(stringFilter.getPropertyId())) {
                        filterMap.put(ConstantsUtils.COMPANY_NO, filterString);
                    }
                    if (ConstantsUtils.COMPANY_NAME.equals(stringFilter.getPropertyId())) {
                        filterMap.put(ConstantsUtils.COMPANY_NAME, filterString);
                    }
                    if (ConstantsUtils.COMPANY_TYPE_VALUE.equals(stringFilter.getPropertyId())) {
                        filterMap.put(ConstantsUtils.COMPANY_TYPE_VALUE, filterString);
                    }
                    if (ConstantsUtils.COMPANY_STATUS_VALUE.equals(stringFilter.getPropertyId())) {
                        filterMap.put(ConstantsUtils.COMPANY_STATUS_VALUE, filterString);
                    }

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;

                    Integer filterValue = (Integer) stringFilter.getValue();
                    if (ConstantsUtils.COMPANY_STATUS_VALUE.equals(stringFilter.getPropertyId())) {

                        filterMap.put(ConstantsUtils.COMPANY_STATUS_VALUE, filterValue);

                    }
                    if (ConstantsUtils.COMPANY_TYPE_VALUE.equals(stringFilter.getPropertyId())) {

                        filterMap.put(ConstantsUtils.COMPANY_TYPE_VALUE, filterValue);

                    }

                    if (ConstantsUtils.COMPANY_CATEGORY_VALUE.equals(stringFilter.getPropertyId())) {
                        filterMap.put("companyCategory", filterValue);
                    }

                    if (ConstantsUtils.COMPANY_GROUP_VALUE.equals(stringFilter.getPropertyId())) {
                        filterMap.put("companyGroup", filterValue);
                    }
                    if (ConstantsUtils.TRADE_CLASS_VALUE.equals(stringFilter.getPropertyId())) {
                        filterMap.put("tradeClass", filterValue);
                    }

                }

            }
        }

        return filterMap;
    }

    
    public static Map getFilterMapForCompanyDetails(final BeanSearchCriteria searchCriteria) {

        Map<Object, Object> filterMap = new HashMap<Object, Object>();

        if (searchCriteria != null && searchCriteria.getFilters() != null) {
            for (Container.Filter filter : searchCriteria.getFilters()) {

                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;
                    if (StringUtils.EMPTY.equals(filterString)) {
                        filterString = ConstantsUtils.PERCENCTAGE;
                    }

                    if (ConstantsUtils.COMPANY_ID.equals(stringFilter.getPropertyId())) {
                        filterMap.put(ConstantsUtils.COMPANY_ID, filterString);
                    }
                    if (ConstantsUtils.COMPANY_NO.equals(stringFilter.getPropertyId())) {
                        filterMap.put(ConstantsUtils.COMPANY_NO, filterString);
                    }
                    if (ConstantsUtils.COMPANY_NAME.equals(stringFilter.getPropertyId())) {
                        filterMap.put(ConstantsUtils.COMPANY_NAME, filterString);
                    }

                    if (ConstantsUtils.TRADING_PARTNER_CONTRACT_NO.equals(stringFilter.getPropertyId())) {
                        filterMap.put(ConstantsUtils.TRADING_PARTNER_CONTRACT_NO, filterString);
                    }
                    String temp = filterString.replace("%", "");
                     if (ConstantsUtils.CREATEDBY.equals(stringFilter.getPropertyId())) {
                            if (Integer.valueOf(temp) != 0) {
                                filterMap.put(ConstantsUtils.CREATEDBY, stringFilter.getFilterString());
                            }
                        }
                        if (ConstantsUtils.MODIFIEDBY.equals(stringFilter.getPropertyId())) {
                            if (Integer.valueOf(temp) != 0) {
                                filterMap.put(ConstantsUtils.MODIFIEDBY, stringFilter.getFilterString());
                            }
                        }
                        
                        if (ConstantsUtils.CFP_CREATED_BY_DUP.equals(stringFilter.getPropertyId())) {
                            if (Integer.valueOf(temp) != 0) {
                                filterMap.put(ConstantsUtils.CFP_CREATED_BY_DUP, stringFilter.getFilterString());
                            }
                        }
                        if (ConstantsUtils.CFP_MODIFIED_BY_DUP.equals(stringFilter.getPropertyId())) {
                            if (Integer.valueOf(temp) != 0) {
                                filterMap.put(ConstantsUtils.CFP_MODIFIED_BY_DUP, stringFilter.getFilterString());
                            }
                        }

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;


                    if (stringFilter.getValue() instanceof Integer) {

                        Integer filterValue = (Integer) stringFilter.getValue();

                        if (ConstantsUtils.CFP_STATUS.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                filterMap.put(ConstantsUtils.CFP_STATUS, filterValue);
                            }
                        }
                        if (ConstantsUtils.COMPANY_STATUS_VALUE.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                filterMap.put(ConstantsUtils.COMPANY_STATUS_VALUE, filterValue);
                            }
                        }

                        if (ConstantsUtils.COMPANY_TYPE_VALUE.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                filterMap.put(ConstantsUtils.COMPANY_TYPE_VALUE, filterValue);
                            }
                        }

                    } else if (stringFilter.getValue() instanceof Date) {

                        String formattedDate = StringUtils.EMPTY;
                        Date filterDate = (Date) stringFilter.getValue();
                        if (filterDate != null) {
                            formattedDate = sdf.format(filterDate);
                        }

                        if (ConstantsUtils.CFP_START_DATE.equals(stringFilter.getPropertyId()) || ConstantsUtils.CFP_END_DATE.equals(stringFilter.getPropertyId())
                                || ConstantsUtils.Cfp_Company_Start_Date.equals(stringFilter.getPropertyId()) || ConstantsUtils.Cfp_Company_End_Date.equals(stringFilter.getPropertyId())
                                || ConstantsUtils.Cfp_Attached_Date.equals(stringFilter.getPropertyId())) {

                            String property = String.valueOf(stringFilter.getPropertyId());
                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                filterMap.put(property + "-From", formattedDate);
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                filterMap.put(property + "-To", formattedDate);
                            }

                        }

                    }
                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;

                    String formattedFromDate = StringUtils.EMPTY;
                    String formattedToDate = StringUtils.EMPTY;
                    Date filterFromDate = (Date) stringFilter.getStartValue();
                    Date filterToDate = (Date) stringFilter.getEndValue();
                    if (filterFromDate != null && filterToDate != null) {
                        formattedFromDate = sdf.format(filterFromDate);
                        formattedToDate = sdf.format(filterToDate);
                    }

                    if (ConstantsUtils.CFP_START_DATE.equals(stringFilter.getPropertyId()) || ConstantsUtils.CFP_END_DATE.equals(stringFilter.getPropertyId())
                            || ConstantsUtils.Cfp_Company_Start_Date.equals(stringFilter.getPropertyId()) || ConstantsUtils.Cfp_Company_End_Date.equals(stringFilter.getPropertyId())
                            || ConstantsUtils.Cfp_Attached_Date.equals(stringFilter.getPropertyId())) {

                        String property = String.valueOf(stringFilter.getPropertyId());

                        filterMap.put(property + "-From", formattedFromDate);
                        filterMap.put(property + "-To", formattedToDate);

                    }

                }

            }
        }

        return filterMap;
    }
    public static String getUseName() {
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        try {

            User createdUser = UserLocalServiceUtil.getUser(Long.valueOf(userId));

            return createdUser.getFullName();
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex);
        }
        return StringUtils.EMPTY;
    }

     public List<Object> getListForParentLookUp(final ErrorfulFieldGroup companyFamilyplanMaster,final BeanSearchCriteria searchCriteria, final String dbColumnName,final Boolean asc, final int start, final int end,String results) throws PortalException, SystemException {
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
                    String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;
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
                    if (ConstantsUtils.CREATEDBY.equals(stringFilter.getPropertyId())) {
                        if (Integer.valueOf(stringFilter.getFilterString()) != 0) {
                            parameters.put(ConstantsUtils.CREATEDBY, stringFilter.getFilterString());
                        }
                    }

                    if (ConstantsUtils.MODIFIEDBY.equals(stringFilter.getPropertyId())) {

                        if (Integer.valueOf(stringFilter.getFilterString()) != 0) {
                            parameters.put(ConstantsUtils.MODIFIEDBY, stringFilter.getFilterString());
                        }
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
                                System.out.println("filterValue in 2660 ====================================>> " + filterValue);
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

        cfpList = CFPSEARCHLOGICDAO.findCfpModelV1(cfp, dbColumnName, asc, start, end, parameters, results, null, null);

        LOGGER.info("return DynamicQuery");

        return cfpList;
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
    public List<CFPSearchDTO> searchCFPForParentLookUp(final ErrorfulFieldGroup companyFamilyplanMaster, final int start, final int end, final List<OrderByColumn> orderByColumns,final BeanSearchCriteria criteria) throws PortalException, SystemException {
        LOGGER.info("Entering searchCFP p2:" + start + " p3:" + end + " p4: " + ((orderByColumns == null) ? orderByColumns : orderByColumns.size()));

        //To load column in hashMap
        CommonUtils.loadColumnName();
        String orderby ="asc";
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
         final List cfpList = getListForParentLookUp(companyFamilyplanMaster,criteria,dbColumnName, asc, start, end,searchresults);


        final List<CFPSearchDTO> cfpDTO = getCustomizedSearchFormModelForParentLookUp(cfpList);
        LOGGER.info("return customized cfp size :" + ((cfpDTO == null) ? cfpDTO : cfpDTO.size()));
        return cfpDTO;
    }
    

    public static List<CFPSearchDTO> getCustomizedSearchFormModelForParentLookUp(final List<Object[]> cfpMasterList) throws PortalException, SystemException {
        final List<CFPSearchDTO> companyDTO = new ArrayList<CFPSearchDTO>();
        Map<Integer, String> hm = com.stpl.app.util.GeneralCommonUtils.getCodeDescription();
        Map<Integer, String> userMap= StplSecurity.getUserName();
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
            if (obj[16] !=null){
                cfpDTOObj.setCompanyFamilyPlanSystemId(Integer.valueOf(String.valueOf(obj[16])));
                cfpDTOObj.setSystemId(String.valueOf(obj[16]));
            }
            if (obj[17] !=null){
              cfpDTOObj.setModifiedDate((Date)obj[17]);
            }
            if (obj[18] !=null){
                cfpDTOObj.setModifiedBy(userMap.get(Integer.valueOf(String.valueOf(obj[18]))));
            }
            if (obj[19] !=null){
              cfpDTOObj.setCreatedDate((Date)obj[19]);
            }

            if (obj[20] !=null){
                cfpDTOObj.setCreatedBy(userMap.get(Integer.valueOf(String.valueOf(obj[20]))));
            }
            if (obj[21] !=null){
              cfpDTOObj.setRecordLockStatus((Boolean)obj[21]);
            }
            companyDTO.add(cfpDTOObj);
        }
        LOGGER.info("return customized company size " + companyDTO.size());

        return companyDTO;
    }

     
     public List<Object> getCFPResults(final ErrorfulFieldGroup companyFamilyplanMaster,final Set<Container.Filter> filterSet, final String dbColumnName,final Boolean asc, final int start, final int end,String results) throws PortalException, SystemException {
        LOGGER.info("Entering getDynamicQuery");
        int cfpType=0;
        int cfpStatus=0;
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


        try{

            if (companyFamilyplanMaster.getField(ConstantsUtils.TEXT1).getValue() == null) {
                cfp.put(ConstantsUtils.CFP_ID, StringUtils.EMPTY);
            } else {
                companyFamilyPlanId = companyFamilyplanMaster.getField(ConstantsUtils.TEXT1).getValue().toString().trim();
            }
            if (companyFamilyplanMaster.getField(ConstantsUtils.TEXT2).getValue() == null) {
                cfp.put(ConstantsUtils.CFP_NO, StringUtils.EMPTY);
            } else {
                companyFamilyPlanNo = companyFamilyplanMaster.getField(ConstantsUtils.TEXT2).getValue().toString().trim();
            }
            if (companyFamilyplanMaster.getField(ConstantsUtils.TEXT3).getValue() == null) {
                cfp.put(ConstantsUtils.CFP_NAME, StringUtils.EMPTY);
            } else {
                companyFamilyPlanName = companyFamilyplanMaster.getField(ConstantsUtils.TEXT3).getValue().toString().trim();
            }

        if (companyFamilyplanMaster.getField(ConstantsUtils.COMBO1).getValue() == null || ConstantsUtils.SELECT_ONE.equals(companyFamilyplanMaster.getField(ConstantsUtils.COMBO1).getValue()) ) {
                cfp.put(ConstantsUtils.CFP_TYPE, 0);
            } else {
                final HelperDTO helperDTO = (HelperDTO) companyFamilyplanMaster.getField(ConstantsUtils.COMBO1).getValue();
                if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

               cfpType  = helperDTO.getId();
             cfp.put(ConstantsUtils.CFP_TYPE, (Integer)cfpType);

                }

            }
            if (companyFamilyplanMaster.getField(ConstantsUtils.COMBO2).getValue() == null || ConstantsUtils.SELECT_ONE.equals(companyFamilyplanMaster.getField(ConstantsUtils.COMBO2).getValue())) {
                cfp.put(ConstantsUtils.CFP_STATUS, 0);
            } else {

                final HelperDTO helperDTO = (HelperDTO) companyFamilyplanMaster.getField(ConstantsUtils.COMBO2).getValue();
                if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                    cfpStatus = helperDTO.getId();
            cfp.put(ConstantsUtils.CFP_STATUS, (Integer)cfpStatus);
                }

            }

            if (companyFamilyplanMaster.getField(ConstantsUtils.TEXT6).getValue() == null) {
                cfp.put(ConstantsUtils.COMPANY_ID, StringUtils.EMPTY);
            } else {
                companyId = companyFamilyplanMaster.getField(ConstantsUtils.TEXT6).getValue().toString().trim();
            }
            if (companyFamilyplanMaster.getField(ConstantsUtils.TEXT7).getValue() == null) {
                cfp.put(ConstantsUtils.COMPANY_NO, StringUtils.EMPTY);
            } else {
                companyNo = companyFamilyplanMaster.getField(ConstantsUtils.TEXT7).getValue().toString().trim();
            }
            if (companyFamilyplanMaster.getField(ConstantsUtils.TEXT8).getValue() == null) {
                cfp.put(ConstantsUtils.COMPANY_NAME, StringUtils.EMPTY);
            } else {
                companyName = companyFamilyplanMaster.getField(ConstantsUtils.TEXT8).getValue().toString().trim();
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

            if (filterSet != null) {
                for (Container.Filter filter : filterSet) {

                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;
                        if (ConstantsUtils.CFP_SYSTEM_ID.equals(stringFilter.getPropertyId())) {
                            parameters.put(ConstantsUtils.CFP_SYSTEM_ID, stringFilter.getFilterString());
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
                        if (ConstantsUtils.PARENT_CFP_NAME.equals(stringFilter.getPropertyId())) {
                            parameters.put(ConstantsUtils.PARENT_CFP_NAME_DUP, filterString);
                        }
                        if (ConstantsUtils.PARENT_CFP_ID.equals(stringFilter.getPropertyId())) {
                            parameters.put(ConstantsUtils.PARENT_CFP_ID_DUP, filterString);
                        }
                        if (ConstantsUtils.CFP_CREATED_BY_DUP.equals(stringFilter.getPropertyId())) {
                            if (Integer.valueOf( stringFilter.getFilterString()) != 0) {
                                parameters.put(ConstantsUtils.CFP_CREATED_BY_DUP,  stringFilter.getFilterString());
                            }
                        }
                        if (ConstantsUtils.CFP_MODIFIED_BY_DUP.equals(stringFilter.getPropertyId())) {
                            if (Integer.valueOf( stringFilter.getFilterString()) != 0) {
                                parameters.put(ConstantsUtils.CFP_MODIFIED_BY_DUP,  stringFilter.getFilterString());
                            }
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
                            if (ConstantsUtils.CFP_CREATED_BY_DUP.equals(stringFilter.getPropertyId())) {
                                if (filterValue != 0) {
                                    parameters.put(ConstantsUtils.CFP_CREATED_BY_DUP, filterValue);
                                }
                            }

                            if (ConstantsUtils.CFP_MODIFIED_BY_DUP.equals(stringFilter.getPropertyId())) {
                                if (filterValue != 0) {
                                    parameters.put(ConstantsUtils.CFP_MODIFIED_BY_DUP, filterValue);
                                }
                            }
                            if (ConstantsUtils.CFP_DESIGNATION.equals(stringFilter.getPropertyId())) {
                                if (filterValue != 0) {
                                    parameters.put(ConstantsUtils.CFP_DESIGNATION_DUP, filterValue);
                                }
                            }

                        
                        } else if (stringFilter.getValue() instanceof Date) {

                            Date filterString = (Date) stringFilter.getValue();
                            if (ConstantsUtils.CFP_START_DATE.equals(stringFilter.getPropertyId())) {
                                if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                parameters.put(ConstantsUtils.CFP_START_DATE_FROM , String.valueOf(dateFormat.format(filterString)));
                                } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                    parameters.put(ConstantsUtils.CFP_START_DATE_TO, String.valueOf(dateFormat.format(filterString)));
                                }
                            } else if (ConstantsUtils.CFP_END_DATE.equals(stringFilter.getPropertyId())) {
                                if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                    parameters.put(ConstantsUtils.CFP_END_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                                } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                parameters.put(ConstantsUtils.CFP_END_DATE_TO , String.valueOf(dateFormat.format(filterString)));
                                }
                        }else if (ConstantsUtils.CFP_MODIFIED_DATE.equals(stringFilter.getPropertyId())) {
                                if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                    parameters.put(ConstantsUtils.MODIFIED_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                                } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                    parameters.put(ConstantsUtils.MODIFIED_DATE_TO, String.valueOf(dateFormat.format(filterString)));
                                }
                        }else if (ConstantsUtils.CFP_CREATED_DATE.equals(stringFilter.getPropertyId())) {
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
                        parameters.put(ConstantsUtils.CFP_START_DATE_FROM , String.valueOf(dateFormat.format(filterString)));
                            parameters.put(ConstantsUtils.CFP_START_DATE_TO, String.valueOf(dateFormat.format(filterString1)));
                        }

                        if (ConstantsUtils.CFP_END_DATE.equals(stringFilter.getPropertyId())) {
                            parameters.put(ConstantsUtils.CFP_END_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                        parameters.put(ConstantsUtils.CFP_END_DATE_TO , String.valueOf(dateFormat.format(filterString1)));
                        }

                        if (ConstantsUtils.CFP_CREATED_DATE.equals(stringFilter.getPropertyId())) {
                            parameters.put(ConstantsUtils.CFP_END_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                        parameters.put(ConstantsUtils.CFP_END_DATE_TO , String.valueOf(dateFormat.format(filterString1)));
                        }

                        if (ConstantsUtils.CFP_CREATED_DATE.equals(stringFilter.getPropertyId())) {
                            parameters.put(ConstantsUtils.CREATED_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                            parameters.put(ConstantsUtils.CREATED_DATE_TO, String.valueOf(dateFormat.format(filterString1)));
                        }

                        if (ConstantsUtils.CFP_MODIFIED_DATE.equals(stringFilter.getPropertyId())) {
                            parameters.put(ConstantsUtils.MODIFIED_DATE_FROM, String.valueOf(dateFormat.format(filterString)));
                            parameters.put(ConstantsUtils.MODIFIED_DATE_TO, String.valueOf(dateFormat.format(filterString1)));
                        }
                    }
                }
            }

            cfpList = CFPSEARCHLOGICDAO.findCfpModelV1(cfp, dbColumnName, asc, start, end, parameters, results, null, null);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("return DynamicQuery");
        return cfpList;
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
    public List<SearchResultsDTO> getResultsForCFP(final ErrorfulFieldGroup companyFamilyplanMaster, final int start, final int end, final List<SortByColumn> columns,final Set<Container.Filter> filterSet) throws PortalException, SystemException, Exception {
        LOGGER.info("Entering searchCFP p2:" + start + " p3:" + end + " p4: " + ((columns == null) ? columns : columns.size()));

        //To load column in hashMap
        CommonUtils.loadColumnName();
        String orderby ="asc";
        String column = ConstantsUtils.CFP_MODEL_SID;
        boolean asc = false;
        String columnName = StringUtils.EMPTY;
        String dbColumnName = StringUtils.EMPTY;

        CommonUtils.loadColumnNames();
        for (final Iterator<SortByColumn> iterator = columns.iterator(); iterator.hasNext();) {
            final SortByColumn orderByColumn = (SortByColumn) iterator.next();
            columnName = orderByColumn.getName();
            dbColumnName = CommonUtils.getDbColumnNames(columnName);
            if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                asc = false;
            } else {
                asc = true;
            }
        }
         final List cfpList = getCFPResults(companyFamilyplanMaster,filterSet,dbColumnName, asc, start, end,searchresults);



        final List<SearchResultsDTO> cfpDTO = getCustomizedSearchFormModel(cfpList);
        LOGGER.info("return customized cfp size :" + ((cfpDTO == null) ? cfpDTO : cfpDTO.size()));
        return cfpDTO;
    }

     
}
