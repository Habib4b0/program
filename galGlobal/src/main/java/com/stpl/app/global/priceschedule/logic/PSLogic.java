package com.stpl.app.global.priceschedule.logic;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.dao.impl.PSLogicDAOImpl;
import com.stpl.app.global.item.util.CommonUtils;
import static com.stpl.app.global.netsalesformula.logic.NsfLogic.constantProperties;
import com.stpl.app.global.priceschedule.dto.PSDTO;
import com.stpl.app.global.priceschedule.dto.PSIFPDTO;
import com.stpl.app.global.priceschedule.dto.PSNepFormulaDTO;
import com.stpl.app.global.priceschedule.dto.SearchPSIFPDTO;
import com.stpl.app.global.priceschedule.dto.SearchPriceScheduleDTO;
import com.stpl.app.global.priceschedule.util.PsUtils;
import com.stpl.app.global.priceschedule.util.QueryUtil;
import com.stpl.app.global.priceschedule.util.UIUtils;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.IfpDetails;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.ImtdPsDetails;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.ItemPricingQualifier;
import com.stpl.app.model.MasterDataFiles;
import com.stpl.app.model.PsContract;
import com.stpl.app.model.PsDetails;
import com.stpl.app.model.PsModel;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.ImtdPsDetailsLocalServiceUtil;
import com.stpl.app.service.ItemPricingQualifierLocalServiceUtil;
import com.stpl.app.service.MasterDataFilesLocalServiceUtil;
import com.stpl.app.service.NetSalesFormulaMasterLocalServiceUtil;
import com.stpl.app.service.PsContractLocalServiceUtil;
import com.stpl.app.service.PsDetailsLocalServiceUtil;
import com.stpl.app.service.PsModelLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.ui.UDCIncrementCheck;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.HelperUtils;
import com.stpl.app.util.NotesTabLogic;
import com.stpl.domain.global.priceschedule.PriceScheduleDAO;
import com.stpl.domain.global.priceschedule.PriceScheduleLogic;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 * The Class PsLogic.
 */
public class PSLogic implements PriceScheduleLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(PSLogic.class);
    SessionDTO sessionDTO;
    public static final SimpleDateFormat DB_DATE = new SimpleDateFormat("yyyy-MM-dd");
    static HashMap<String, String> criteria = new HashMap<>();
    
    public PSLogic(){}
    public PSLogic(final SessionDTO sessionDTO){
    this.sessionDTO=sessionDTO;
    }
    
    /**
     * The dao.
     */
    private static final PriceScheduleDAO DAO = new PSLogicDAOImpl();
    /**
     * The Constant ADD.
     */
    private static final String ADD = "add";
    /**
     * The Constant UPDATE.
     */
    private static final String UPDATE = "update";
    /**
     * The format double.
     */
    private final static DecimalFormat FORMATDECIMAL = new DecimalFormat("###,##0.00");
    /**
     * The format.
     */
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat(ConstantsUtils.ZERO_DECIMAL);
    //Java date format
    public static String DEFAULT_JAVA_DATE_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";
    //SQL date format
    public static String DEFAULT_SQL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    
    private final String user = VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString();

      private NotesTabLogic rsLogic = new NotesTabLogic();
      
    private static HelperListUtil helperListUtil=HelperListUtil.getInstance();
    
    private CommonUtil commonUtil=CommonUtil.getInstance();
    
    final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
    /**
     * Gets the item master.
     *
     * @param systemId the id
     * @return the item master
     */
    public List<ItemMaster> getItemMaster(final int systemId) throws SystemException, PortalException {
        LOGGER.debug("Entering getItemMaster " + systemId);

        final List<ItemMaster> itemList = new ArrayList<ItemMaster>();

        LOGGER.debug(" Entering getItemFamilyplanDetailsListByItemFamilyplanSystemId " + systemId);
        final List<IfpDetails> details = DAO.getItemFamilyplanDetailsListByItemFamilyplanSystemId(systemId);
        LOGGER.debug(" After getItemFamilyplanDetailsListByItemFamilyplanSystemId ItemFamilyplanDetails size -" + ((details == null) ? details : details.size()));
        for (int i = 0; i < details.size(); i++) {
            final IfpDetails ifpDetails = details.get(i);
            final ItemMaster itemMaster = DAO.getitemMasterBysystemId(ifpDetails
                    .getItemMasterSid());
            itemList.add(itemMaster);
        }

        LOGGER.debug(" Ending getItemMaster with itemList -" + ((itemList == null) ? itemList : itemList.size()));
        return itemList;
    }

    /**
     * Gets the ifp for ps.
     *
     * @param searchField the search field
     * @param value the value
     * @return the ifp for ps
     */
    public List<IfpModel> getIfpForPS(final String searchField, final String value) throws SystemException {
        String replacedValue = value;
        LOGGER.debug(" Entering getIfpForPS  searchField=" + searchField);
        final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
        map.put("IFP No", ConstantsUtils.IFP_NO);
        map.put("IFP Name", "itemFamilyplanName");

        if (StringUtils.isNotBlank(replacedValue)) {
            replacedValue = replacedValue.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(IfpModel.class);

            // Added to check INBOUND_STATUS is A. ETL Soft delete data should not be included, where INBOUND_STATUS is D
            ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));

            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(
                    map.get(searchField), replacedValue));
            final List<IfpModel> list = DAO.getItemFamilyplanMasterList(ifpDynamicQuery);
            LOGGER.debug("Ending of  getIfpForPS with list -" + ((list == null) ? list : list.size()));
            return list;
        }

        return new ArrayList<IfpModel>();
    }

    /**
     * Method used to get Price Schedule Category.
     *
     * @return
     */
    public List<HelperDTO> getPriceScheduleCategory() throws SystemException {
        LOGGER.debug(" getPriceScheduleCategory()");

        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();

        List<HelperTable> list;
        LOGGER.debug(" Entering getPriceScheduleCategory(" + UIUtils.PS_CATEGORY + ConstantsUtils.CLOSE_PARENTHESIS);
        list = DAO.getHelperTableDetailsByListName(UIUtils.PS_CATEGORY);

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }
        }
        LOGGER.debug("Ends of getPriceScheduleCategory with helperList .." + helperList.size());

        return helperList;
    }

    /**
     * Method used to get Price Schedule Designation.
     *
     * @return
     */
    public List<HelperDTO> getPriceScheduleDesignation() throws SystemException {
        LOGGER.debug(" Enters getPriceScheduleDesignation()");

        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();

        final List<HelperTable> list = DAO.getHelperTableDetailsByListName(UIUtils.PS_DESIGNATION);

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));
            }
        }
        LOGGER.debug(" End of getPriceScheduleDesignation --" + helperList.size());

        return helperList;
    }

    



    /**
     * Gets the price tolerance type.
     *
     * @return the price tolerance type
     */
    public List<HelperDTO> getPriceToleranceType() throws SystemException, Exception {
        LOGGER.debug("Enter getPriceToleranceType()");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();

        LOGGER.debug("getHelperTableDetailsByListName(" + UIUtils.PRICE_TOLERANCE_TYPE + ConstantsUtils.CLOSE_PARENTHESIS);
        final List<HelperTable> list = DAO.getHelperTableDetailsByListName(UIUtils.PRICE_TOLERANCE_TYPE);
        LOGGER.debug("List for PRICE_TOLERANCE_TYPE " + list.size());

        HelperTable helperTable;
        
        helperList.add(new HelperDTO(0, ConstantsUtils.SELECT_ONE));
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));
            }
        }
        LOGGER.debug("Ends of getPriceToleranceType with HelperTable list" + helperList.size());

        return helperList;
    }

    /**
     * Gets the price tolerance interval.
     *
     * @return the price tolerance interval
     */
    public List<HelperDTO> getPriceToleranceInterval() throws SystemException, Exception {
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        LOGGER.debug(" getHelperTableDetailsByListName (" + UIUtils.PRICE_TOLERANCE_INTERVAL + ConstantsUtils.CLOSE_PARENTHESIS);
        final List<HelperTable> list = DAO.getHelperTableDetailsByListName(UIUtils.PRICE_TOLERANCE_INTERVAL);
        
        helperList.add(new HelperDTO(0, ConstantsUtils.SELECT_ONE));
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }
        }
        LOGGER.debug("Ends  getPriceToleranceInterval with  List  <HelperTable>" + helperList.size());
        return helperList;
    }

    /**
     * Gets the price tolerance frequency.
     *
     * @return the price tolerance frequency
     */
    public List<HelperDTO> getPriceToleranceFrequency() throws SystemException, Exception {
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        LOGGER.debug("getHelperTableDetailsByListName(" + UIUtils.PRICE_TOLERANCE_FRERQUENCY + ConstantsUtils.CLOSE_PARENTHESIS);
        final List<HelperTable> list = DAO.getHelperTableDetailsByListName(UIUtils.PRICE_TOLERANCE_FRERQUENCY);
          helperList.add(new HelperDTO(0, ConstantsUtils.SELECT_ONE));
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));
            }
        }
        LOGGER.debug("Ends getPriceToleranceFrequency with List<HelperTable>" + helperList.size());

        return helperList;
    }

    /**
     * Gets the customized ifp search form from model.
     *
     * @param list the list
     * @return the customized ifp search form from model
     */
    private List<SearchPSIFPDTO> getCustomizedIfpSearchFormFromModel(
            final List<IfpModel> list) throws Exception {
        LOGGER.debug(" Enters getCustomizedIfpSearchFormFromModel()");
        final List<SearchPSIFPDTO> searchItemList = new ArrayList<SearchPSIFPDTO>();

        if (list != null) {
            SearchPSIFPDTO searchActualsDTO;
            for (int i = 0; i < list.size(); i++) {
                searchActualsDTO = new SearchPSIFPDTO();
                final IfpModel formulaDetails = (IfpModel) list
                        .get(i);
                searchActualsDTO.setItemFamilyplanNo(formulaDetails
                        .getIfpNo());
                searchActualsDTO.setItemFamilyplanName(formulaDetails
                        .getIfpName());

                searchItemList.add(searchActualsDTO);

            }
        }

        LOGGER.debug("Ends  getCustomizedIfpSearchFormFromModel with List size" + list.size());
        return searchItemList;
    }

    /**
     * Gets the dynamic ifp query search.
     *
     * @param searchField the search field
     * @param value the value
     * @return the dynamic ifp query search
     * @throws Exception the system exception
     * @throws PortalException the portal exception
     */
    public DynamicQuery getDynamicIfpQuerySearch(final String searchField,
            final String value) {
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(IfpModel.class);
        String replaceValue = value;

        final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
        map.put("IFP No", ConstantsUtils.IFP_NO);
        map.put("IFP Name", "itemFamilyplanName");
        if (StringUtils.isNotBlank(replaceValue)) {
            replaceValue = replaceValue.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(
                    map.get(searchField), replaceValue));
        }
        LOGGER.debug("returns  ifpDynamicQuery");

        return ifpDynamicQuery;

    }

    /**
     * Gets the dynamic query search.
     *
     * @param searchItemForm the search item form
     * @return the dynamic query search
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public int getDynamicQuerySearch(final ErrorfulFieldGroup searchItemForm,final BeanSearchCriteria search)
            throws SystemException, PortalException, Exception {

        LOGGER.debug(" getCustomizedIfpSearchFormFromModel(ErrorfulFieldGroup searchItemForm)");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String psId;
        String psNo;
        String psName;
        int count=0;
        boolean excelFlag=false;
        int psTypeID = 0;

        int psStatusID = 0;

        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(PsModel.class);
        // Added to check INBOUND_STATUS is A. ETL Soft delete data should not be included, where INBOUND_STATUS is D
        ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));

        LOGGER.debug("priceScheduleId=" + searchItemForm.getField(ConstantsUtils.PS_ID).getValue());
        if (searchItemForm.getField(Constants.PS_ID).getValue() == null || searchItemForm.getField(Constants.PS_ID).getValue().toString() == StringUtils.EMPTY) {
            psId = StringUtils.EMPTY;
        } else {
            psId = searchItemForm.getField(Constants.PS_ID).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(Constants.PS_NO).getValue() == null) {
            psNo = StringUtils.EMPTY;
        } else {
            psNo = searchItemForm.getField(Constants.PS_NO).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(Constants.PS_NAME).getValue() == null) {
            psName = StringUtils.EMPTY;
        } else {
            psName = searchItemForm.getField(Constants.PS_NAME).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(Constants.PS_TYPE).getValue() == null) {
            psTypeID = 0;
        } else {
            psTypeID = (Integer) searchItemForm.getField(Constants.PS_TYPE).getValue();
        }
        if (searchItemForm.getField(Constants.PS_STATUS).getValue() == null) {
            psStatusID = 0;
        } else {
            psStatusID = (Integer) searchItemForm.getField(Constants.PS_STATUS).getValue();
        }
        
        
        
         if (StringUtils.isNotBlank(psId)) {
            psId = psId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.PS_ID_SEARCH,
                    psId));
            excelFlag=true;
        }
        if (StringUtils.isNotBlank(psNo)) {
            psNo = psNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.PS_NO_SEARCH,
                    psNo));
            excelFlag=true;
        }
        if (StringUtils.isNotBlank(psName)) {
            psName = psName.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(
                    Constants.PS_NAME_SEARCH, psName));
            excelFlag=true;
        }
        if (psTypeID != 0) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.PS_TYPE_SEARCH, psTypeID));
            excelFlag=true;
        }

        if (psStatusID != 0) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.PS_STATUS_SEARCH, psStatusID));
            excelFlag=true;
        }
        if (search != null && search.getFilters() != null) {
                for (Container.Filter filter : search.getFilters()) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.like(PsUtils.getDBColumnName(stringFilter.getPropertyId().toString()), filterString));
                    } else if (filter instanceof Between) {
                        Between stringFilter = (Between) filter;
                        Date filterString = (Date) stringFilter.getStartValue();
                        Date filterString1 = (Date) stringFilter.getEndValue();
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(PsUtils.getDBColumnName(stringFilter.getPropertyId().toString()), format.format(filterString)));
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.lt(PsUtils.getDBColumnName(stringFilter.getPropertyId().toString()), format.format(filterString1)));
                    } else if(filter instanceof Compare) {
                        Compare stringFilter = (Compare)filter;
                        if(stringFilter.getValue()instanceof Integer) {
                        
                        if(stringFilter.getOperation().equals(stringFilter.getOperation().EQUAL)) {
                            if (((Integer) stringFilter.getValue()) == 0) {
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.isNull(PsUtils.getDBColumnName(String.valueOf(stringFilter.getPropertyId()))));
                            } else {
                            int value = (Integer)stringFilter.getValue();
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(PsUtils.getDBColumnName(String.valueOf(stringFilter.getPropertyId())), value));
                            }
                        }                          
                        }
                    }
                }
        }        
        if(excelFlag){
            count = (int) DAO.getPriceScheduleMasterQueryCount(ifpDynamicQuery);
        }  else{
            count = 0;
        }
        
         return count;
    }
    
    public int getSearchPsCount(final ErrorfulFieldGroup searchItemForm,final BeanSearchCriteria search)
            {

        LOGGER.debug(" getCustomizedIfpSearchFormFromModel(ErrorfulFieldGroup searchItemForm)");
       SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String psId;
        String psNo;
        String psName;
        String itemId;
        String itemNo;
        String itemName;
        int count=0;
        int psTypeID = 0;

        int psStatusID = 0;

       
        LOGGER.info("priceScheduleId=");
        if (searchItemForm.getField(ConstantsUtils.TEXT1).getValue() == null || searchItemForm.getField(ConstantsUtils.TEXT1).getValue().toString() == StringUtils.EMPTY) {
            psId = StringUtils.EMPTY;
        } else {
            psId = searchItemForm.getField(ConstantsUtils.TEXT1).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantsUtils.TEXT2).getValue() == null) {
            psNo = StringUtils.EMPTY;
        } else {
            psNo = searchItemForm.getField(ConstantsUtils.TEXT2).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantsUtils.TEXT3).getValue() == null) {
            psName = StringUtils.EMPTY;
        } else {
            psName = searchItemForm.getField(ConstantsUtils.TEXT3).getValue()
                    .toString().trim();
        }
        
        if (searchItemForm.getField(ConstantsUtils.TEXT6).getValue() == null) {
            itemId = StringUtils.EMPTY;
        } else {
            itemId = searchItemForm.getField(ConstantsUtils.TEXT6).getValue()
                    .toString().trim();
        }
        
        if (searchItemForm.getField(ConstantsUtils.TEXT7).getValue() == null) {
            itemNo = StringUtils.EMPTY;
        } else {
            itemNo = searchItemForm.getField(ConstantsUtils.TEXT7).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantsUtils.TEXT8).getValue() == null) {
            itemName = StringUtils.EMPTY;
        } else {
            itemName = searchItemForm.getField(ConstantsUtils.TEXT8).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantsUtils.COMBO2).getValue() == null ||ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO2).getValue())) {
            psTypeID = 0;
        } else {
            
               final HelperDTO helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO2).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

            psTypeID = (Integer) (helperDTO.getId());
        }
        }
        if (searchItemForm.getField(ConstantsUtils.COMBO1).getValue() == null ||ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO1).getValue())) {
            psStatusID = 0;
        } else {
            
              final HelperDTO helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO1).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

            psStatusID = (Integer) (helperDTO.getId());
            
        
        }
        
        }
        
         if (StringUtils.isNotBlank(psId)) {
            psId = psId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(psNo)) {
            psNo = psNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(psName)) {
            psName = psName.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        
        if (StringUtils.isNotBlank(itemId)) {
            itemId = itemId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        
        if (StringUtils.isNotBlank(itemNo)) {
            itemNo = itemNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        
        if (StringUtils.isNotBlank(itemName)) {
            itemName = itemName.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        
        
        
        Map<String,Object> filterMap = new HashMap<String,Object>();
        
        if (search != null && search.getFilters() != null) {
                for (Container.Filter filter : search.getFilters()) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;
                        filterMap.put(String.valueOf(stringFilter.getPropertyId()),filterString);
                        
                    } else if (filter instanceof Between) {
                        Between stringFilter = (Between) filter;
                        Date filterString = (Date) stringFilter.getStartValue();
                        Date filterString1 = (Date) stringFilter.getEndValue();
                        filterMap.put(String.valueOf(stringFilter.getPropertyId())+ConstantsUtils.START,format.format(filterString));
                        filterMap.put(String.valueOf(stringFilter.getPropertyId())+ConstantsUtils.END,format.format(filterString1));
                     } else if(filter instanceof Compare) {
                        Compare stringFilter = (Compare)filter;
                        if(stringFilter.getValue()instanceof Integer) {
                        
                        if(stringFilter.getOperation().equals(stringFilter.getOperation().EQUAL)) {
                            if (((Integer) stringFilter.getValue()) == 0) {
                               
                            } else {
                            int value = (Integer)stringFilter.getValue();
                             filterMap.put(String.valueOf(stringFilter.getPropertyId()),value);
                           
                            }
                        }                          
                        }
                    }
                }
        }        
        List psSearchList = PsDetailsLocalServiceUtil.getPsSearchList(psId, psNo, psName, psStatusID, psTypeID, itemId, itemNo, itemName, filterMap, 0, 0, null, null, true);
            if(psSearchList!=null){
               return psSearchList.size();
            }else{
                return 0;
            }
         
    }
    
    
    public List<SearchResultsDTO> getSearchPsList(final ErrorfulFieldGroup searchItemForm,int start,int end, final List<OrderByColumn> orderByColumns,final BeanSearchCriteria search) throws Exception
            {

        LOGGER.debug(" getCustomizedIfpSearchFormFromModel(ErrorfulFieldGroup searchItemForm)");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String psId;
        String psNo;
        String psName;
        String itemId;
        String itemNo;
        String itemName;
        int count=0;
        int psTypeID = 0;
        int psStatusID = 0;

       
             if (searchItemForm.getField(ConstantsUtils.TEXT1).getValue() == null || searchItemForm.getField(ConstantsUtils.TEXT1).getValue().toString() == StringUtils.EMPTY) {
            psId = StringUtils.EMPTY;
        } else {
            psId = searchItemForm.getField(ConstantsUtils.TEXT1).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantsUtils.TEXT2).getValue() == null) {
            psNo = StringUtils.EMPTY;
        } else {
            psNo = searchItemForm.getField(ConstantsUtils.TEXT2).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantsUtils.TEXT3).getValue() == null) {
            psName = StringUtils.EMPTY;
        } else {
            psName = searchItemForm.getField(ConstantsUtils.TEXT3).getValue()
                    .toString().trim();
        }
        
        if (searchItemForm.getField(ConstantsUtils.TEXT6).getValue() == null) {
            itemId = StringUtils.EMPTY;
        } else {
            itemId = searchItemForm.getField(ConstantsUtils.TEXT6).getValue()
                    .toString().trim();
        }
        
        if (searchItemForm.getField(ConstantsUtils.TEXT7).getValue() == null) {
            itemNo = StringUtils.EMPTY;
        } else {
            itemNo = searchItemForm.getField(ConstantsUtils.TEXT7).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantsUtils.TEXT8).getValue() == null) {
            itemName = StringUtils.EMPTY;
        } else {
            itemName = searchItemForm.getField(ConstantsUtils.TEXT8).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantsUtils.COMBO2).getValue() == null ||ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO2).getValue())) {
            psTypeID = 0;
        } else {
            
               final HelperDTO helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO2).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

            psTypeID = (Integer) (helperDTO.getId());
        }
        }
        if (searchItemForm.getField(ConstantsUtils.COMBO1).getValue() == null ||ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO1).getValue())) {
            psStatusID = 0;
        } else {
            
              final HelperDTO helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO1).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

            psStatusID = (Integer) (helperDTO.getId());
            
        
        }
        
        }
        
        
         if (StringUtils.isNotBlank(psId)) {
            psId = psId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(psNo)) {
            psNo = psNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(psName)) {
            psName = psName.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        
        if (StringUtils.isNotBlank(itemId)) {
            itemId = itemId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        
        if (StringUtils.isNotBlank(itemNo)) {
            itemNo = itemNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        
        if (StringUtils.isNotBlank(itemName)) {
            itemName = itemName.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        
        
        
        Map<String,Object> filterMap = new HashMap<String,Object>();
        
        if (search != null && search.getFilters() != null) {
                for (Container.Filter filter : search.getFilters()) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;
                        filterMap.put(String.valueOf(stringFilter.getPropertyId()),filterString);
                        
                    } else if (filter instanceof Between) {
                        Between stringFilter = (Between) filter;
                        Date filterString = (Date) stringFilter.getStartValue();
                        Date filterString1 = (Date) stringFilter.getEndValue();
                        filterMap.put(String.valueOf(stringFilter.getPropertyId())+ConstantsUtils.START,format.format(filterString));
                        filterMap.put(String.valueOf(stringFilter.getPropertyId())+ConstantsUtils.END,format.format(filterString1));
                     } else if(filter instanceof Compare) {
                        Compare stringFilter = (Compare)filter;
                        if(stringFilter.getValue()instanceof Integer) {
                        
                        if(stringFilter.getOperation().equals(stringFilter.getOperation().EQUAL)) {
                            if (((Integer) stringFilter.getValue()) == 0) {
                               
                            } else {
                            int value = (Integer)stringFilter.getValue();
                             filterMap.put(String.valueOf(stringFilter.getPropertyId()),value);
                           
                            }
                        }                          
                        }
                    }
                }
        }
        
        String columnName = "ps.PS_MODEL_SID";
        String orderBy = "ASC";
        for (final Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator
                .hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
            
                        if(ConstantsUtils.PS_SID.equals(orderByColumn.getName())){
                            columnName="ps.PS_MODEL_SID";
                        }else if(ConstantsUtils.PS_ID.equals(orderByColumn.getName())){
                            columnName="ps.PS_ID";
                        }else if(ConstantsUtils.PS_NO.equals(orderByColumn.getName())){
                            columnName="ps.PS_NO";
                        }else if(ConstantsUtils.PS_NAME.equals(orderByColumn.getName())){
                            columnName="ps.PS_NAME";
                        }else if(ConstantsUtils.PS_STATUS.equals(orderByColumn.getName())){
                            columnName="status";
                        }else if(ConstantsUtils.PS_TYPE.equals(orderByColumn.getName())){
                            columnName="type";
                        }else if(ConstantsUtils.PS_CATEGORY.equals(orderByColumn.getName())){
                            columnName="category";
                        }else if(ConstantsUtils.PS_START_DATE.equals(orderByColumn.getName())){
                            columnName="ps.PS_START_DATE";
                        }else if(ConstantsUtils.PS_END_DATE.equals(orderByColumn.getName())){
                            columnName="ps.PS_END_DATE";
                        }else if(ConstantsUtils.PS_DESIGNATION.equals(orderByColumn.getName())){
                            columnName="designation";
                        }else if("parentId".equals(orderByColumn.getName())){
                            columnName="ps.PARENT_PS_ID";
                        }else if("parentName".equals(orderByColumn.getName())){
                            columnName="ps.PARENT_PS_NAME";
                        }else if(ConstantsUtils.TRADE_CLASS.equals(orderByColumn.getName())){
                            columnName="trade";
                        }
            
            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
               orderBy = "ASC";
            } else {
                orderBy = "DESC";
            }
        }
        
        
        List psSearchList = PsDetailsLocalServiceUtil.getPsSearchList(psId, psNo, psName, psStatusID, psTypeID, itemId, itemNo, itemName, filterMap, start,end, columnName, orderBy, false);
            
        
        return getCustomizedSearchPsList(psSearchList);
    }
    
    
    List<SearchResultsDTO> getCustomizedSearchPsList(List list) throws Exception {
        List<SearchResultsDTO> result = new ArrayList<SearchResultsDTO>();
        SearchResultsDTO dto;
        if(list !=null){
        for(int i=0;i<list.size();i++){
            dto= new SearchResultsDTO();
          Object[] obj = (Object[])list.get(i);
          if(obj[0]!=null)
          {
              dto.setPriceScheduleSystemId(Integer.valueOf(String.valueOf(obj[0])));
              dto.setPsSystemId(String.valueOf(obj[0]));
              dto.setSystemID(dto.getPsSystemId());
          }
          
          if(obj[1]!=null){
              dto.setPriceScheduleId(String.valueOf(obj[1]));
          }
            
          if(obj[2]!=null){
              dto.setPriceScheduleNo(String.valueOf(obj[2]));
          }
          if(obj[3]!=null){
              dto.setPriceScheduleName(String.valueOf(obj[3]));
          }
          
          if(obj[7]!=null){
            Date startDate = (Date) obj[7];
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            startDate = df.parse(CommonUtils.convertDateToString(startDate));   
            dto.setPriceScheduleStartDate(startDate);
          }
          
          if(obj[8]!=null){
            Date endDate = (Date) obj[8];
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            endDate = df.parse(CommonUtils.convertDateToString(endDate));   
            dto.setPriceScheduleEndDate(endDate);
          }
          
          if(obj[10]!=null && StringUtils.isNotBlank(String.valueOf(obj[10]))){
               
                    String psId = String.valueOf(obj[10]);
                   dto.setParentId(psId);
               
          }
          
          if(obj[11]!=null){
            dto.setParentName(String.valueOf(obj[11]));
          }
          
          if(obj[13]!=null && !String.valueOf(obj[13]).equals(ConstantsUtils.SELECT_ONE)){
              dto.setPriceScheduleType(String.valueOf(obj[13]));
          }
          if(obj[14]!=null && !String.valueOf(obj[14]).equals(ConstantsUtils.SELECT_ONE)){
              dto.setPriceScheduleStatus(String.valueOf(obj[14]));
          }
          
          
          if(obj[15]!=null && !String.valueOf(obj[15]).equals(ConstantsUtils.SELECT_ONE)){
              dto.setPriceScheduleCategory(String.valueOf(obj[15]));
          }
          if(obj[16]!=null && !String.valueOf(obj[16]).equals(ConstantsUtils.SELECT_ONE)){
              dto.setPriceScheduleDesignation(String.valueOf(obj[16]));
          }
          if(obj[17]!=null && !String.valueOf(obj[17]).equals(ConstantsUtils.SELECT_ONE)){
              dto.setTradeClass(String.valueOf(obj[17]));
          }
          
          if(obj[18]!=null){
              dto.setRecordLockStatus(Boolean.parseBoolean(String.valueOf(obj[18])));
          }
          
         result.add(dto);
          
        }
    }
        return result;
    }
    
    

    /**
     * Search ps.
     *
     * @param searchItemForm the search item form
     * @param start the start
     * @param start the start
     * @param end the end
     * @param orderByColumns the order by columns
     * @return the list< search price schedule dt o>
     * @throws Exception the exception
     */
    public List<SearchResultsDTO> searchPs(
            final ErrorfulFieldGroup searchItemForm, final int start, final int end,
            final List<OrderByColumn> orderByColumns,final BeanSearchCriteria criteria) throws SystemException, Exception {
        LOGGER.debug("Entering searchPs P2: " + start + " P3: " + end + " P4: " + ((orderByColumns == null) ? orderByColumns : orderByColumns.size()));
        List<SearchResultsDTO> searchList = new ArrayList<SearchResultsDTO>();

        String psId;
        String psNo;
        String psName;

        int psTypeID = 0;

        int psStatusID = 0;

       
        if (searchItemForm.getField(ConstantsUtils.TEXT1).getValue() == null) {
            psId = StringUtils.EMPTY;
        } else {
            psId = searchItemForm.getField(ConstantsUtils.TEXT1).getValue()
                    .toString();
        }
        if (searchItemForm.getField(ConstantsUtils.TEXT2).getValue() == null) {
            psNo = StringUtils.EMPTY;

        } else {
            psNo = searchItemForm.getField(ConstantsUtils.TEXT2).getValue()
                    .toString();
        }
        if (searchItemForm.getField(ConstantsUtils.TEXT3).getValue() == null) {
            psName = StringUtils.EMPTY;
        } else {
            psName = searchItemForm.getField(ConstantsUtils.TEXT3).getValue()
                    .toString();
        }
        if (searchItemForm.getField(ConstantsUtils.COMBO2).getValue() == null || ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO2).getValue())) {
            psTypeID = 0;
        } else {
            
                final HelperDTO helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO2).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

               psTypeID  = Integer.valueOf(helperDTO.getId());
            
        }
        }
        if (searchItemForm.getField(ConstantsUtils.COMBO1).getValue() == null || ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO1).getValue())) {
            psStatusID = 0;
        } else {
            
                final HelperDTO helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO1).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

               psStatusID  = Integer.valueOf(helperDTO.getId());
        }
        }
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(PsModel.class);

        // Added to check INBOUND_STATUS is A. ETL Soft delete data should not be included, where INBOUND_STATUS is D
        ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));

        if (StringUtils.isNotBlank(psId)) {
            psId = psId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.PS_ID_SEARCH,
                    psId));
        }
        if (StringUtils.isNotBlank(psNo)) {
            psNo = psNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.PS_NO_SEARCH,
                    psNo));
        }
        if (StringUtils.isNotBlank(psName)) {
            psName = psName.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(
                    Constants.PS_NAME_SEARCH, psName));
        }
        if (psTypeID != 0) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.PS_TYPE_SEARCH, psTypeID));

        }

        if (psStatusID != 0) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.PS_STATUS_SEARCH, psStatusID));

        }
        if (criteria != null && criteria.getFilters() != null) {
                for (Container.Filter filter : criteria.getFilters()) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.like(PsUtils.getDBColumnName(stringFilter.getPropertyId().toString()), filterString));
                    } else if (filter instanceof Between) {
                        Between stringFilter = (Between) filter;
                        Date filterString = (Date) stringFilter.getStartValue();
                        Date filterString1 = (Date) stringFilter.getEndValue();
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(PsUtils.getDBColumnName(stringFilter.getPropertyId().toString()), filterString));
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.lt(PsUtils.getDBColumnName(stringFilter.getPropertyId().toString()), filterString1));
                    }else if(filter instanceof Compare) {
                        Compare stringFilter = (Compare)filter;
                        if(stringFilter.getValue()instanceof Integer) {
                        
                        if(stringFilter.getOperation().equals(stringFilter.getOperation().EQUAL)) {
                            if (((Integer) stringFilter.getValue()) == 0) {
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.isNull(PsUtils.getDBColumnName(String.valueOf(stringFilter.getPropertyId()))));
                            } else {
                            int value = (Integer)stringFilter.getValue();
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(PsUtils.getDBColumnName(String.valueOf(stringFilter.getPropertyId())), value));
                            }
                        }                          
                        }
                    }
                }
        }
        PsUtils.loadColumnName();
        for (final Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator
                .hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                ifpDynamicQuery.addOrder(OrderFactoryUtil.asc(PsUtils.getDBColumnName(orderByColumn
                        .getName())));
            } else {
                ifpDynamicQuery.addOrder(OrderFactoryUtil.desc(PsUtils.getDBColumnName(orderByColumn
                        .getName())));
            }
        }
        final List<PsModel> list = DAO
                .getPriceScheduleMasterList(ifpDynamicQuery);
        searchList = getCustomizedSearchFormFromModel(list);
        LOGGER.debug("return PriceScheduleMaster size -" + ((searchList == null) ? searchList : searchList.size()));

        return searchList;

    }

    /**
     * Gets the customized search form from model.
     *
     * @param list the list
     * @return the customized search form from model
     */
    private List<SearchResultsDTO> getCustomizedSearchFormFromModel(
            final List<PsModel> list) throws Exception {
        LOGGER.debug(" getCustomizedSearchFormFromModel( List<PriceScheduleMaster> list)");
        final List<SearchResultsDTO> searchItemList = new ArrayList<SearchResultsDTO>();

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final SearchResultsDTO searchDTO = new SearchResultsDTO();
                final PsModel psMaster = (PsModel) list.get(i);
                searchDTO.setPriceScheduleSystemId(psMaster.getPsModelSid());
                searchDTO.setPriceScheduleSystemId(psMaster.getPsModelSid());
                searchDTO.setParentPriceScheduleId(psMaster.getParentPsId());
                searchDTO.setParentPriceScheduleName(psMaster.getParentPsName());
                searchDTO.setPriceScheduleNo(psMaster.getPsNo());
                searchDTO.setPriceScheduleName(psMaster.getPsName());
                searchDTO.setPriceScheduleStartDate(psMaster.getPsStartDate());
                searchDTO.setPriceScheduleEndDate(psMaster.getPsEndDate());
                searchDTO.setPriceScheduleId(psMaster.getPsId());
                if (psMaster.getPsType() != 0) {
                    searchDTO.setPriceScheduleType(PSLogic.getdropDownValues(psMaster.getPsType()));
                }
                if (StringUtils.isNotBlank(psMaster.getPsDesignation()) && !ConstantsUtils.ZERO.equals(psMaster.getPsDesignation())) {
                    searchDTO.setPriceScheduleDesignation(PSLogic.getdropDownValues(Integer.valueOf(psMaster.getPsDesignation())));
                }
                if (psMaster.getPsStatus() != 0) {
                    searchDTO.setPriceScheduleStatus(PSLogic.getdropDownValues(psMaster.getPsStatus()));
                }
                if (psMaster.getPsCategory() != 0) {
                    searchDTO.setPriceScheduleCategory(PSLogic.getdropDownValues(psMaster.getPsCategory()));
                }
                if (psMaster.getPsTradeClass() != 0) {
                    searchDTO.setTradeClass(PSLogic.getdropDownValues(psMaster.getPsTradeClass()));
                }
                searchDTO.setRecordLockStatus(Boolean.parseBoolean(String.valueOf(psMaster.getRecordLockStatus())));
                searchItemList.add(searchDTO);
            }
        }

        LOGGER.debug("returns  List<SearchPriceScheduleDTO> " + searchItemList.size());
        return searchItemList;
    }

    /**
     * Save ps.
     *
     * @param psForm the ps form
     * @param psList the ps list
     * @return the string
     */
    public String savePS(final ErrorfulFieldGroup psForm, final List<NotesDTO> availableUploadedInformation, final String addedNotes) throws SystemException, PortalException, Exception {
        LOGGER.debug("Entering savePS ");
        String returnString = null;
        PsModel item;

        try {
            String systemId;

            if (psForm.getField(ConstantsUtils.PRICE_SCHEDULE_SYS_ID).getValue() == null || psForm.getField(ConstantsUtils.PRICE_SCHEDULE_SYS_ID).getValue().equals(ConstantsUtils.NULL)) {
                systemId = StringUtils.EMPTY;
            } else {
                systemId = String.valueOf(psForm.getField(ConstantsUtils.PRICE_SCHEDULE_SYS_ID).getValue());
            }
            systemId = systemId.replace(",", StringUtils.EMPTY);

            LOGGER.debug("priceScheduleSystemId=" + psForm.getField(ConstantsUtils.PRICE_SCHEDULE_SYS_ID).getValue());
            if (ConstantsUtils.NULL.equals(systemId) || ConstantsUtils.ZERO.equals(systemId)) {
                item= PsModelLocalServiceUtil.createPsModel(0);
                item.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
            } else {
                LOGGER.debug(" getPriceScheduleMasterBySystemId " + systemId + ConstantsUtils.CLOSE_PARENTHESIS);
                item = DAO.getPriceScheduleMasterBySystemId(Integer.parseInt(systemId));
                item.setInboundStatus(ConstantsUtils.INBOUND_STATUS_C);

            }
            item.setPsId(String.valueOf(psForm.getField(Constants.PS_ID).getValue()).trim());
            item.setPsNo(String.valueOf(psForm.getField(Constants.PS_NO).getValue()).trim());
            item.setPsName(String.valueOf(psForm.getField(Constants.PS_NAME).getValue()).trim());
            item.setPsStatus(Integer.parseInt(String.valueOf(psForm.getField(Constants.PS_STATUS).getValue())));
            if (psForm.getField(Constants.PS_TYPE).getValue() != null) {
                item.setPsType(Integer.parseInt(String.valueOf(psForm.getField(Constants.PS_TYPE).getValue())));
            }
            item.setPsDesignation(String.valueOf(psForm.getField(ConstantsUtils.PS_DESIGNATION).getValue()).trim());
            item.setPsCategory(Integer.parseInt(String.valueOf(psForm.getField(ConstantsUtils.PS_CATEGORY).getValue())));
            item.setPsStartDate((Date) psForm.getField(ConstantsUtils.PS_START_DATE).getValue());
            item.setPsEndDate((Date) psForm.getField(ConstantsUtils.PS_END_DATE).getValue());
            item.setPsTradeClass(Integer.parseInt(String.valueOf(psForm.getField(ConstantsUtils.TRADE_CLASS).getValue()).trim()));
            item.setParentPsName(String.valueOf(psForm.getField(ConstantsUtils.PARENT_SCHEDULE_NAME).getValue()).trim());
            item.setInternalNotes(addedNotes);
            item.setRecordLockStatus(false);
            if (psForm.getField(ConstantsUtils.PARENT_PS_ID).getValue() != null) {
                item.setParentPsId(String.valueOf(psForm.getField(ConstantsUtils.PARENT_PS_ID).getValue()).trim());
            }

            if (ConstantsUtils.NULL.equals(systemId) || ConstantsUtils.ZERO.equals(systemId)) {
                final int user = Integer.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString());
                item.setCreatedDate(new Date());
                item.setCreatedBy(user);
                item.setModifiedDate(com.stpl.app.global.company.util.CommonUtils.getDateTime());
                final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsModel.class);

                psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.PS_ID_SEARCH, item.getPsId()));
                final List<PsModel> list = DAO.getPriceScheduleMasterList(psDynamicQuery);
                LOGGER.debug("PriceScheduleMaster size -" + ((list == null) ? list : list.size()));
                if (list.size() < CommonUtils.ONE) {

                    final PsModel result = DAO.savePriceScheduleMaster(item);
                    sessionDTO.setSystemId(result.getPsModelSid());
                    LOGGER.debug("received PriceScheduleId" + result.getPsId());
                    if (result.getPsStatus() != 0) {
                        UDCIncrementCheck.increment(result.getPsStatus(), UIUtils.PS_STATUS);
                    }
                    if (result.getPsType() != 0) {
                        UDCIncrementCheck.increment(result.getPsType(), UIUtils.PS_TYPE);
                    } 
                    
                    if (result.getPsDesignation() != null) {
                        UDCIncrementCheck.increment(result.getPsDesignation(), UIUtils.PS_DESIGNATION);
                    }
                    
                    if (result.getPsCategory() != 0) {
                        UDCIncrementCheck.increment(result.getPsCategory(), UIUtils.PS_CATEGORY);
                    }
                    saveDetailsList(result, "save");
                      rsLogic.saveUploadedInformation(availableUploadedInformation, "PS_MODEL", result.getPsModelSid());
                } else {
                    returnString = "duplicate";
                }
            } else {
                final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsModel.class);
                psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.PS_ID_Column, item.getPsId()));
                @SuppressWarnings("unchecked")
                final List<PsModel> priceScheduleList = DAO.getPriceScheduleMasterList(psDynamicQuery);
                LOGGER.debug("PriceScheduleMaster size -" + ((priceScheduleList == null) ? priceScheduleList : priceScheduleList.size()));
                int count = 0;
                for (int i = 0; i < priceScheduleList.size(); i++) {
                    if (Integer.parseInt(systemId.replace(",", StringUtils.EMPTY)) == priceScheduleList.get(i).getPsModelSid()) {
                    } else {
                        count++;
                    }
                }
                if (count < CommonUtils.ONE) {
                    item.setModifiedDate(new Date());
                    item.setModifiedBy(Integer.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                    LOGGER.debug("In Item PriceScheduleSystemId " + item.getPsModelSid());
                    final PsModel check = DAO.getPriceScheduleMasterBySystemId(item.getPsModelSid());
                    LOGGER.debug("Checking PriceScheduleSystemId " + check.getPsId());

                    if (check.getPsStatus() != 0) {
                        UDCIncrementCheck.decrement(check.getPsStatus(), UIUtils.PS_STATUS);
                    }
                    if (check.getPsType() != 0) {
                        UDCIncrementCheck.decrement(check.getPsType(), UIUtils.PS_TYPE);
                    }
                    if (check.getPsDesignation() != null) {
                        UDCIncrementCheck.decrement(check.getPsDesignation(), UIUtils.PS_DESIGNATION);
                    }
                    if (check.getPsCategory() != 0) {
                        UDCIncrementCheck.decrement(check.getPsCategory(), UIUtils.PS_CATEGORY);
                    }
                    LOGGER.debug("UDC check PriceScheduleSystemId " + item.getPsModelSid());
                    final PsModel result = DAO.updatePriceScheduleMaster(item);
                    LOGGER.debug("Result checking PriceScheduleSystemId " + result.getPsId());
                    if (result.getPsStatus() != 0) {
                        UDCIncrementCheck.increment(result.getPsStatus(), UIUtils.PS_STATUS);
                    }
                    if (result.getPsType() != 0) {
                        UDCIncrementCheck.increment(result.getPsType(), UIUtils.PS_TYPE);
                    }
                    if (result.getPsDesignation() != null) {
                        UDCIncrementCheck.increment(result.getPsDesignation(), UIUtils.PS_DESIGNATION);
                    }
                    if (result.getPsCategory() != 0) {
                        UDCIncrementCheck.increment(result.getPsCategory(), UIUtils.PS_CATEGORY);
                    }

                    saveDetailsList(result, "update");
                    rsLogic.saveUploadedInformation(availableUploadedInformation, "PS_MODEL", result.getPsModelSid());
                } else {
                    LOGGER.debug(" returns  duplicate");
                    returnString = "duplicate";
                }
            }

            LOGGER.debug(" returns  success");
            returnString = ConstantsUtils.SUCCESS;

        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("return -" + returnString);
        return returnString;
    }

    public void saveDetailsList(final PsModel psMaster, String saveOrUpdate) throws SystemException, PortalException, Exception {
        LOGGER.debug("Entering saveDetailsList p1:" + psMaster.getPsModelSid());
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String createdDate = String.valueOf(sessionDTO.getSessionDate());
        ImtdPsDetailsLocalServiceUtil.updateToPsDetails(psMaster.getPsModelSid(), userId, sessionId, createdDate, user, user, user, user, saveOrUpdate);
        LOGGER.debug("Ending saveDetailsList");
    }

    /**
     * Save details list.
     *
     * @param ifpList the ifp list
     * @param psMaster the ps master
     * @param check the check
     */
    public void saveDetailsList(final List<PSIFPDTO> ifpList,
            final PsModel psMaster, final String check) throws SystemException, Exception {
        LOGGER.debug("Entering saveDetailsList P1: " + ((ifpList == null) ? ifpList : ifpList.size()) + " p3: " + check);

        if (ifpList != null) {

            for (int i = 0; i < ifpList.size(); i++) {
                final PSIFPDTO psIfpForm = (PSIFPDTO) ifpList.get(i);
                final PsDetails ifpDetailsForm = PsDetailsLocalServiceUtil.createPsDetails(0);
                ifpDetailsForm.setItemMasterSid(Integer.valueOf(psIfpForm.getItemSystemId()));
                ifpDetailsForm.setPriceProtectionStartDate(psIfpForm.getPriceProtectionStartDate());
                ifpDetailsForm.setPriceProtectionEndDate(psIfpForm.getPriceProtectionEndDate());
                ifpDetailsForm.setContractPriceStartDate(psIfpForm.getContractPriceStartDate());
                ifpDetailsForm.setContractPriceEndDate(psIfpForm.getContractPriceEndDate());
                if (psIfpForm.getPrice() != null && !StringUtils.EMPTY.equals(psIfpForm.getPrice()) && !StringUtils.EMPTY.equals(psIfpForm.getPrice())) {
                    if (CommonUtils.isNumeric(String.valueOf(psIfpForm.getPrice()))) {
                        ifpDetailsForm.setPrice(Double.valueOf(psIfpForm.getPrice()));
                    }
                }
                if (psIfpForm.getPriceType() == null) {
                    ifpDetailsForm.setItemPricingQualifierSid(0);
                } else {
                    ifpDetailsForm.setItemPricingQualifierSid(psIfpForm.getPriceType().getId());
                }
                ifpDetailsForm.setPriceTolerance(Double.valueOf(psIfpForm.getPriceTolerance()));
                ifpDetailsForm.setPriceToleranceType(psIfpForm.getPriceToleranceType().getId());
                ifpDetailsForm.setPriceToleranceInterval(psIfpForm.getPriceToleranceInterval().getId());
                ifpDetailsForm.setPriceToleranceFrequency(psIfpForm.getPriceToleranceFrequency().getId());
                ifpDetailsForm.setIfpModelSid(Integer.valueOf(psIfpForm.getItemFamilyplanSystemId()));
                if (psIfpForm.getBasePrice() != null && !StringUtils.EMPTY.equals(psIfpForm.getBasePrice()) && !StringUtils.EMPTY.equals(psIfpForm.getBasePrice())) {
                    if (CommonUtils.isNumeric(String.valueOf(psIfpForm.getBasePrice()))) {
                        ifpDetailsForm.setBasePrice(Double.valueOf(psIfpForm.getBasePrice()));
                    }
                }
                if (psIfpForm.getContractPrice() != null && !StringUtils.EMPTY.equals(psIfpForm.getContractPrice()) && !StringUtils.EMPTY.equals(psIfpForm.getContractPrice())) {
                    if (CommonUtils.isNumeric(String.valueOf(psIfpForm.getContractPrice()))) {
                        ifpDetailsForm.setContractPrice(Double.valueOf(psIfpForm.getContractPrice()));
                    } else {
                        ifpDetailsForm.setContractPrice(Double.valueOf(ConstantsUtils.ZERO_DECIMAL));
                    }
                }
                ifpDetailsForm.setPsModelSid(psMaster.getPsModelSid());
                ifpDetailsForm.setRevisionDate(psIfpForm.getRevisionDate());
                ifpDetailsForm.setItemPsAttachedDate(psIfpForm.getAttachedDate());
                ifpDetailsForm.setRecordLockStatus(true);
                if (check.endsWith(ADD)) {
                    ifpDetailsForm.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
                    final PsDetails details = DAO.savePriceScheduleDetails(ifpDetailsForm);
                    if (details.getPriceToleranceType() != 0) {
                        UDCIncrementCheck.increment(details.getPriceToleranceType(), UIUtils.PRICE_TOLERANCE_TYPE);
                    }
                    if (details.getPriceToleranceFrequency() != 0) {
                        UDCIncrementCheck.increment(details.getPriceToleranceFrequency(), UIUtils.PRICE_TOLERANCE_FRERQUENCY);
                    }
                    if (details.getPriceToleranceInterval() != CommonUtils.ZERO) {
                        UDCIncrementCheck.increment(String.valueOf(details.getPriceToleranceInterval()), UIUtils.PRICE_TOLERANCE_INTERVAL);
                    }
                } else {
                    ifpDetailsForm.setInboundStatus(ConstantsUtils.INBOUND_STATUS_C);
                    final PsDetails details = DAO.updatePriceScheduleDetails(ifpDetailsForm);
                    if (details.getPriceToleranceType() != 0) {
                        UDCIncrementCheck.increment(details.getPriceToleranceType(), UIUtils.PRICE_TOLERANCE_TYPE);
                    }
                    if (details.getPriceToleranceFrequency() != 0) {
                        UDCIncrementCheck.increment(details.getPriceToleranceFrequency(), UIUtils.PRICE_TOLERANCE_FRERQUENCY);
                    }
                    if (details.getPriceToleranceInterval() != CommonUtils.ZERO) {
                        UDCIncrementCheck.increment(String.valueOf(details.getPriceToleranceInterval()), UIUtils.PRICE_TOLERANCE_INTERVAL);
                    }

                }
            }
        }
    }

    /**
     * Gets the priceschedules by id.
     *
     * @param systemId the id
     * @return the priceschedules by id
     */
    public PSDTO getPriceschedulesById(final int systemId) throws SystemException, PortalException {
        LOGGER.debug("getPriceschedulesById(PriceScheduleMasterBySystemId id=" + systemId + ConstantsUtils.CLOSE_PARENTHESIS);
        final PSDTO priceScheduleDto = new PSDTO();
        Map<Integer, String> userMap= StplSecurity.getUserName();
        LOGGER.debug("getPriceScheduleMasterBySystemId(PriceScheduleMasterBySystemId id=" + systemId + ConstantsUtils.CLOSE_PARENTHESIS);
        final PsModel psMaster = DAO.getPriceScheduleMasterBySystemId(systemId);
        LOGGER.debug("returns PriceScheduleMaster" + psMaster.getPsId());

        priceScheduleDto.setPriceScheduleSystemId(psMaster.getPsModelSid());
        priceScheduleDto.setPriceScheduleId(psMaster.getPsId());
        priceScheduleDto.setPriceScheduleName(psMaster.getPsName());
        priceScheduleDto.setPriceScheduleNo(psMaster.getPsNo());
        priceScheduleDto.setPriceScheduleDesignation(StringUtils.isBlank(psMaster.getPsDesignation())  ? helperListUtil.getIdHelperDTOMap().get(0) : helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(psMaster.getPsDesignation())));
        priceScheduleDto.setPriceScheduleStatus(helperListUtil.getIdHelperDTOMap().get(psMaster.getPsStatus()));
        priceScheduleDto.setPriceScheduleType(helperListUtil.getIdHelperDTOMap().get(psMaster.getPsType()));
        priceScheduleDto.setPriceScheduleStartDate(psMaster.getPsStartDate());
        priceScheduleDto.setPriceScheduleEndDate(psMaster.getPsEndDate());
        priceScheduleDto.setPriceScheduleCategory(helperListUtil.getIdHelperDTOMap().get(psMaster.getPsCategory()));
        priceScheduleDto.setParentPriceScheduleId(psMaster.getParentPsId());
        priceScheduleDto.setParentPriceScheduleName(psMaster.getParentPsName());
        priceScheduleDto.setCreatedBy(userMap.get(psMaster.getCreatedBy())==null ? StringUtils.EMPTY : userMap.get(psMaster.getCreatedBy()));
        priceScheduleDto.setCreatedDate(psMaster.getCreatedDate());
        priceScheduleDto.setModifiedDate(psMaster.getModifiedDate());
        if(psMaster.getModifiedBy()!=0){
        priceScheduleDto.setModifiedBy(userMap.get(psMaster.getModifiedBy())==null ? StringUtils.EMPTY : userMap.get(psMaster.getModifiedBy()));
        }
        priceScheduleDto.setTradeClass(helperListUtil.getIdHelperDTOMap().get(psMaster.getPsTradeClass()));
        priceScheduleDto.setParentPriceScheduleName(psMaster.getParentPsName());
        priceScheduleDto.setRecordLockStatus(String.valueOf(psMaster.getRecordLockStatus()));
        priceScheduleDto.setInternalNotes(psMaster.getInternalNotes());
        priceScheduleDto.setSource(psMaster.getSource());
        LOGGER.debug("returns PsDTO" + priceScheduleDto.getPriceScheduleId());

        return priceScheduleDto;
    }

    /**
     * Gets the selected item list.
     *
     * @param ifpId the ifp id
     * @return the selected item list
     */
    public List<IfpModel> getSelectedIFPItemList(final int ifpId) throws SystemException, PortalException, Exception {
        LOGGER.debug("getSelectedItemList(ItemFamilyPlanId ifpId=" + ifpId + ConstantsUtils.CLOSE_PARENTHESIS);

        final List<IfpModel> ifpList = new ArrayList<IfpModel>();

        LOGGER.debug("getPriceScheduleDetailsByPriceScheduleMasterSystemId(ItemFamilyPlanId ifpId=" + ifpId + ConstantsUtils.CLOSE_PARENTHESIS);
        final List<PsDetails> list = DAO.getPriceScheduleDetailsByPriceScheduleMasterSystemId(ifpId);
        LOGGER.debug("returns psDetailsList" + list.size());
        if (list != null && !list.isEmpty()) {
            final PsDetails details = list.get(0);
            ifpList.add(DAO.getItemFamilyplanMasterBySystemId(details.getIfpModelSid()));
        }

        return ifpList;
    }

    /**
     * Gets the selected item list.
     *
     * @param ifpId the ifp id
     * @return the selected item list
     */
    public List<PSIFPDTO> getSelectedItemList(final int ifpId) throws SystemException, PortalException, Exception {
        LOGGER.debug("getSelectedItemLis(ItemFamilyPlanId ifpId=" + ifpId + ConstantsUtils.CLOSE_PARENTHESIS);

        List<IfpModel> ifpList = new ArrayList<IfpModel>();

        LOGGER.debug("getPriceScheduleDetailsByPriceScheduleMasterSystemId(ItemFamilyPlanId ifpId=" + ifpId + ConstantsUtils.CLOSE_PARENTHESIS);                                
        final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsDetails.class);

        psDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PS_MODEL_SID, ifpId));
        psDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
        psDynamicQuery.setProjection(ProjectionFactoryUtil.property(ConstantsUtils.IFP_MODEL_SID));

        List<Integer> resultList = PsDetailsLocalServiceUtil.dynamicQuery(psDynamicQuery);
           LOGGER.info("resultList = " + resultList);
        if (resultList != null && !resultList.isEmpty()) {
            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpModel.class);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.IFP_MODEL_SID, resultList.get(0)));

            ifpList = (IfpModelLocalServiceUtil.dynamicQuery(ifpDynamicQuery));
        }
        
        return getCustomizedPSIFPDTO(ifpList);
    }

    private List<PSIFPDTO> getCustomizedPSIFPDTO(List<IfpModel> returnList) {
        final List<PSIFPDTO> dtoList = new ArrayList<PSIFPDTO>();
        for (int i = 0; i < returnList.size(); i++) {
            final IfpModel modelDTO = returnList.get(i);
            final PSIFPDTO dto = new PSIFPDTO();
            dto.setItemFamilyPlanId(modelDTO.getIfpId());
            dto.setItemFamilyplanNo(modelDTO.getIfpNo());
            dto.setItemFamilyplanName(modelDTO.getIfpName());
            dto.setItemFamilyplanSystemId(String.valueOf(modelDTO.getIfpModelSid()));
            if(modelDTO.getIfpStatus()!=0){
                try {
                    HelperTable helper= HelperTableLocalServiceUtil.getHelperTable(modelDTO.getIfpStatus());
                    if(!ConstantsUtils.SELECT_ONE.equals(helper.getDescription())){
                      dto.setItemFamilyPlanStatus(helper.getDescription());
            }
                    
                } catch (PortalException ex) {
                    java.util.logging.Logger.getLogger(PSLogic.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SystemException ex) {
                    java.util.logging.Logger.getLogger(PSLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(modelDTO.getIfpType()!=0){
                try {
                    HelperTable helper= HelperTableLocalServiceUtil.getHelperTable(modelDTO.getIfpType());
                    if(!ConstantsUtils.SELECT_ONE.equals(helper.getDescription())){
                      dto.setIfpType(helper.getDescription());
                    }
                    
                } catch (PortalException ex) {
                    java.util.logging.Logger.getLogger(PSLogic.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SystemException ex) {
                    java.util.logging.Logger.getLogger(PSLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(modelDTO.getIfpCategory()!=0){
                try {
                    HelperTable helper= HelperTableLocalServiceUtil.getHelperTable(modelDTO.getIfpCategory());
                    if(!ConstantsUtils.SELECT_ONE.equals(helper.getDescription())){
                      dto.setIfpCategory(helper.getDescription());
                    }
                    
                } catch (PortalException ex) {
                    java.util.logging.Logger.getLogger(PSLogic.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SystemException ex) {
                    java.util.logging.Logger.getLogger(PSLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                  dto.setIfpStartDate(modelDTO.getIfpStartDate());
              
                  dto.setIfpEndDate(modelDTO.getIfpEndDate());
              
            }
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * Gets the ifp details list.
     *
     * @param ifpId the ifp id
     * @return the ifp details list
     */
    @SuppressWarnings("unchecked")
    public List<PSIFPDTO> getIfpDetailsList(final int ifpId) throws SystemException, ParseException, Exception {
        LOGGER.debug("getPriceschedulesById(ItemFamilyPlanId ifpId=" + ifpId + ConstantsUtils.CLOSE_PARENTHESIS);

        LOGGER.debug("getItemAndPricingForPs(ItemFamilyPlanId ifpId=" + ifpId + ConstantsUtils.CLOSE_PARENTHESIS);
        final List<Object[]> psDetailsList = DAO.getItemAndPricingForPs(ifpId);
        LOGGER.debug("returns psDetailsList" + psDetailsList.size());
        final List<PSIFPDTO> list = getCustomizedSearchFormFromObject(psDetailsList);
        LOGGER.debug("returns  List<PsIfpDTO>" + list.size());
        return list;

    }

    /**
     * Gets the customized search form from object.
     *
     * @param list the list
     * @return the customized search form from object
     * @throws ParseException the parse exception
     */
    @SuppressWarnings("rawtypes")
    public List<PSIFPDTO> getCustomizedSearchFormFromObject(final List list)
            throws ParseException, Exception {
        final List<PSIFPDTO> psIfpList = new ArrayList<PSIFPDTO>();
        if (list != null) {

            PSIFPDTO psIfpDTO;
            for (int i = 0; i < list.size(); i++) {
                psIfpDTO = new PSIFPDTO();
                HelperDTO helperDTO=new HelperDTO(0, Constants.SELECT_ONE);
                final Object[] obj = (Object[]) list.get(i);
                psIfpDTO.setItemFamilyplanNo(HelperUtils.getString(obj[0]));
                psIfpDTO.setItemSystemId(HelperUtils.getString(obj[1]));
                final HelperDTO helper = new HelperDTO(Integer.valueOf(StringUtils.EMPTY.equals(HelperUtils.getString(obj[2])) ? ConstantsUtils.ZERO : HelperUtils.getString(obj[2])), getPriceTypeNameByID(HelperUtils.getString(obj[2])));
                psIfpDTO.setPriceType(helper);
                psIfpDTO.setPriceTolerance(HelperUtils.getString(obj[3]));
                psIfpDTO.setPriceToleranceType(obj[4]==null || StringUtils.isEmpty(String.valueOf(obj[4])) ? helperListUtil.getIdHelperDTOMap().get(0) 
                        : helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(obj[4].toString())));
                psIfpDTO.setPriceToleranceInterval(obj[5]==null || StringUtils.isEmpty(String.valueOf(obj[5])) ? helperListUtil.getIdHelperDTOMap().get(0) 
                        : helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(obj[5].toString())));
                psIfpDTO.setPriceToleranceFrequency(obj[6]==null || StringUtils.isEmpty(String.valueOf(obj[6])) ? helperListUtil.getIdHelperDTOMap().get(0) 
                        : helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(obj[6].toString())));
                if (obj[7] != null) {
                    psIfpDTO.setPriceProtectionStartDate((Date) (obj[7]));
                }
                if (obj[8] != null) {
                    psIfpDTO.setPriceProtectionEndDate((Date) (obj[8]));
                }
                psIfpDTO.setBasePrice(getDoubleAsString(HelperUtils
                        .getString(obj[9])));
                psIfpDTO.setPriceScheduleSystemId(HelperUtils
                        .getString(obj[10]));
                psIfpDTO.setItemFamilyplanSystemId(HelperUtils
                        .getString(obj[11]));
                psIfpDTO.setContractPrice(getDoubleAsString(HelperUtils
                        .getString(obj[12])));
                psIfpDTO.setContractPriceStartDate((Date) (obj[13]));
                if (obj[14] != null) {
                    psIfpDTO.setContractPriceEndDate((Date) (obj[14]));
                }
                psIfpDTO.setItemNo(HelperUtils.getString(obj[15]));
                psIfpDTO.setItemName(HelperUtils.getString(obj[16]));
                psIfpDTO.setAttachedDate((Date) obj[17]);
                if (obj[19] == null) {
                    psIfpDTO.setPrice(DECIMAL_FORMAT.format(obj[19]));

                } else {
                    psIfpDTO.setPrice(FORMATDECIMAL.format(obj[19]));
                }
                if (obj[20] == null) {
                } else {
                    psIfpDTO.setRevisionDate((Date) obj[20]);
                }

                psIfpDTO.setCheckRecord(Boolean.TRUE);
                psIfpList.add(psIfpDTO);
            }

        }
        return psIfpList;
    }

    /**
     * Gets the double as string.
     *
     * @param value the value
     * @return the double as string
     */
    public String getDoubleAsString(final String value) throws Exception {
        String replacedValue = value;

        if (StringUtils.isNotEmpty(replacedValue)) {
            LOGGER.debug("getDoubleAsString(String value=" + replacedValue + ConstantsUtils.CLOSE_PARENTHESIS);
            final DecimalFormat format = new DecimalFormat("#.##");
            format.setRoundingMode(RoundingMode.FLOOR);
            replacedValue = format.format(Double.valueOf(replacedValue));
        }
        LOGGER.debug("returns  value=" + replacedValue);

        return replacedValue;
    }

    /**
     * Convert date to date.
     *
     * @param date the date
     * @return the date
     * @throws ParseException the parse exception
     */
    public Date convertDateToDate(final Date date) throws ParseException {

        LOGGER.debug("convertDateToDate(Date date=" + date + ConstantsUtils.CLOSE_PARENTHESIS);
        final DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");

        LOGGER.debug("returns  date");
        return inputFormat.parse(inputFormat.format(date));

    }

    /**
     * Delet ps by id.
     *
     * @param psSystemId the ps system id
     * @return the price schedule master
     */
    public PsModel deletPSById(final int psSystemId) throws SystemException, PortalException, Exception {
        LOGGER.debug("Entering deletPSById P1:" + psSystemId);

        final PsModel psMaster = DAO.getPriceScheduleMasterBySystemId(psSystemId);
        psMaster.setInboundStatus(ConstantsUtils.INBOUND_STATUS_D);
        DAO.updatePriceScheduleMaster(psMaster);
        LOGGER.info("received PriceScheduleId " + psMaster.getPsId());
        String query =new QueryUtil().getPsDetailsSidForDelete(psMaster.getPsModelSid());
        final List<Object> detailsList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.info("detailsList"+detailsList.size());
        for (int i = 0; i < detailsList.size(); i++) {
            final int priceScheduleDetails = Integer.valueOf(String.valueOf(detailsList.get(i)));
            HelperTableLocalServiceUtil.executeUpdateQuery("UPDATE PS_DETAILS SET INBOUND_STATUS='D' WHERE PS_DETAILS_SID="+priceScheduleDetails);
        }
        LOGGER.debug("returns  PriceScheduleMaster");
        return psMaster;

    }

    /**
     * Gets the item type.
     *
     * @param listType the list type
     * @return the item type
     */
    public List<HelperDTO> getItemType(final String listType) throws SystemException, Exception {
        LOGGER.debug("getItemType(String listType=" + listType);

        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();

        List<HelperTable> list;
        LOGGER.debug("getHelperTableDetailsByListName((String listType=" + listType);
        list = DAO.getHelperTableDetailsByListName(listType);
        LOGGER.debug("returns    List<HelperTable>" + list.size());
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));
            }
        }
        LOGGER.debug("returns     List<HelperDTO>" + helperList.size());

        return helperList;
    }

    /**
     * Convert date to string.
     *
     * @param date the date
     * @return the string
     */
    public String convertDateToString(final Date date) {

        LOGGER.debug("convertDateToString(Date date=" + date + ConstantsUtils.CLOSE_PARENTHESIS);
        final DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return outputFormat.format(date);

    }

    /**
     * String to date.
     *
     * @param dateString the date string
     * @return the date
     */
    public Date stringToDate(final String dateString) throws ParseException {
        final DateFormat inputFormat = new SimpleDateFormat(
                "E MMM dd HH:mm:ss Z yyyy");

        final Date date = inputFormat.parse(dateString);
        LOGGER.debug("returns date=" + date);
        return date;

    }

    /**
     * String to date for iden.
     *
     * @param dateString the date string
     * @return the date
     */
    public Date stringToDateForIden(final String dateString) throws ParseException {
        final DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyyy");
        LOGGER.debug("StringToDateForIden(String dateString=" + dateString + ConstantsUtils.CLOSE_PARENTHESIS);

        final Date date = inputFormat.parse(dateString);
        LOGGER.debug("returns date=" + date);
        return date;

    }

    /**
     * Convert date.
     *
     * @param dateString the date string
     * @return the date
     */
    public Date convertDate(final String dateString) throws ParseException {
        LOGGER.debug("convertDate(String dateString)" + dateString + ConstantsUtils.CLOSE_PARENTHESIS);
        final DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");

        final Date date = inputFormat.parse(dateString);
        LOGGER.debug("returns date=" + date);
        return date;

    }

    /**
     * Convert date to string.
     *
     * @param date the date
     * @return the string
     */
    public String convertDateToString(final String date) throws ParseException {
        final DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");

        final Date sDate = (Date) formatter.parse(date);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(sDate);
        final String formatedDate = cal.get(Calendar.DATE) + "/"
                + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
        LOGGER.debug("returns formatedDate=" + formatedDate);
        return formatedDate;

    }

    /**
     * Gets the dynamic query count.
     *
     * @param searchQuery the dynamic query search
     * @return the dynamic query count
     */
    public int getDynamicQueryCount(final DynamicQuery searchQuery) throws SystemException {
        LOGGER.debug("Entering getDynamicQueryCount");

        final int count = (int) DAO.getPriceScheduleMasterQueryCount(searchQuery);
        LOGGER.debug("return Price Schedule count -" + count);
        return count;

    }

    /**
     * Gets the actuals total count.
     *
     * @return the actuals total count
     */
    public int getActualsTotalCount() throws SystemException {
        LOGGER.debug("Entering getActualsTotalCount");
        final int count = DAO.getPriceScheduleMasterTotalCount();
        LOGGER.debug("return Price Schedule count -" + count);
        return count;

    }

    /**
     * Load all actualss.
     *
     * @param startIndex the start index
     * @param endIndex the end index
     * @return the list< search price schedule dt o>
     */
    public List<SearchResultsDTO> loadAllActualss(final int startIndex,
            final int endIndex) throws SystemException, Exception {

        LOGGER.debug("loadAllActualss(int startIndex,int endIndex)");
        LOGGER.debug("getPriceScheduleMasterListByLimit(startIndex, endIndex)");
        final List<PsModel> list = DAO.getPriceScheduleMasterListByLimit(startIndex, endIndex);
        LOGGER.debug("returns  List<PriceScheduleMaster>=" + list.size());
        return getCustomizedSearchFormFromModel(list);

    }

    /**
     * Gets the dynamic query count ifp.
     *
     * @param searchQuery the dynamic query search
     * @return the dynamic query count ifp
     */
    public int getDynamicQueryCountIfp(final DynamicQuery searchQuery) throws SystemException {
        LOGGER.debug("getDynamicQueryCountIfp(DynamicQuery dynamicQuerySearch)");

        LOGGER.debug("getItemFamilyplanMasterQueryCount(dynamicQuerySearch)");
        return DAO.getItemFamilyplanMasterQueryCount(searchQuery);

    }

    /**
     * Gets the ifp total count.
     *
     * @return the IFP total count
     */
    public int getIFPTotalCount() throws SystemException {
        LOGGER.debug("getIFPTotalCount()");

        return DAO.getItemFamilyplanMasterTotalCount();

    }

    /**
     * Load all if ps.
     *
     * @param startIndex the start index
     * @param endIndex the end index
     * @return the list< search ps ifp dt o>
     */
    public List<SearchPSIFPDTO> loadAllIFPs(final int startIndex, final int endIndex) throws SystemException, Exception {
        LOGGER.debug("loadAllIFPs(int startIndex, int endIndex)");

        LOGGER.debug("getItemFamilyplanMasterByLimit(startIndex, endIndex)");
        final List<IfpModel> list = DAO.getItemFamilyplanMasterByLimit(startIndex, endIndex);
        LOGGER.debug("returns  List<IfpModel> =" + list.size());
        return getCustomizedIfpSearchFormFromModel(list);

    }

    /**
     * Gets the record count.
     *
     * @param psLogic the ps logic
     * @param binder the binder
     * @return the record count
     */
    public long getRecordCount(final PSLogic psLogic, final ErrorfulFieldGroup binder) throws SystemException, PortalException, Exception {
        LOGGER.debug("loadAllIFPs(int startIndex, int endIndex)");
        return psLogic.getDynamicQuerySearch(binder,null);
        

    }

    public static int getPriceTypeCount(final String filterText, final HelperDTO priceType) throws PortalException, SystemException {
        final String filter = StringUtils.trimToEmpty(filterText) + ConstantsUtils.PERCENCTAGE;
        LOGGER.debug("Entering getLazyPriceTypeCount method with filterText :" + filterText);
        List<Object[]> qualifierList;
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class);
        if (priceType != null && priceType.getId() != 0) {
            cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.ITEM_PRICING_QUALIFIER_SID, priceType.getId()));
        }
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_PRICING_QUAL_NAME, filter));
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.countDistinct(ConstantsUtils.ITEM_PRICING_QUALIFIER_SID));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.ITEM_PRICING_QUAL_NAME, StringUtils.EMPTY)));
        qualifierList = DAO.getItemPricingTypeList(cfpDynamicQuery);
        LOGGER.debug("Ending getLazyPriceTypeCount method with filterText with count :" + qualifierList.get(0));
        return Integer.parseInt(String.valueOf(qualifierList.get(0)));
    }
    
    
    
    public static List<HelperDTO> getPriceTypeResults(final int startIndex, final int end, final String filter, final HelperDTO priceType) throws PortalException, SystemException {
        final List<HelperDTO> list = new ArrayList<HelperDTO>();
        LOGGER.debug("Entering getLazyPriceTypeResults method with filterText :" + filter);
        final String filterString = StringUtils.trimToEmpty(filter) + ConstantsUtils.PERCENCTAGE;
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class);
        cfpDynamicQuery.setLimit(startIndex, end);
        if (priceType != null && priceType.getId() != 0) {
            cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.ITEM_PRICING_QUALIFIER_SID, priceType.getId()));
        }
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_PRICING_QUALIFIER_SID));
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.ITEM_PRICING_QUAL_NAME, StringUtils.EMPTY)));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        if(filter != null){
            cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_PRICING_QUAL_NAME, filterString));
        }
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectionList));
        final List<Object[]> returnList = DAO.getItemPricingTypeList(cfpDynamicQuery);
        
        HelperDTO helperTable;
        if (startIndex == ConstantsUtils.ZERO_INT) {
              helperTable = new HelperDTO();
              helperTable.setId(0);
            helperTable.setDescription(ConstantsUtils.SELECT_ONE);
            list.add(helperTable);
            if (priceType != null && priceType.getId() != 0 && filter == null) {
                list.add(priceType);
            }
        }
        for (final Iterator<Object[]> iterator = returnList.iterator(); iterator.hasNext();) {
            final Object[] value = iterator.next();
            helperTable = new HelperDTO();
            helperTable.setId(value[0] != null ? Integer.valueOf(value[0].toString()) : 0);
            helperTable.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
            if (!StringUtils.EMPTY.equals(helperTable.getDescription())) {
                list.add(helperTable);
            }
        }
        LOGGER.debug("Ending getLazyPriceTypeResults  return list size :" + +list.size());
        return list;
    }
    
    


    public static String getPriceTypeNameByID(final String priceType) throws SystemException {
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class);
        if (priceType != null && !StringUtils.EMPTY.equals(priceType) && priceType!=ConstantsUtils.NULL) {
            cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.ITEM_PRICING_QUALIFIER_SID, Integer.valueOf(priceType)));
        }
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        final List<String> returnList = DAO.getItemPricingTypeList(cfpDynamicQuery);
        if (returnList.isEmpty()) {
            return ConstantsUtils.SELECT_ONE;
        } else {
            return String.valueOf(returnList.get(0));
        }

    }

    public static int getLazyAvailableIFPCount(final String searchField, String value, final Integer[] systemIdList,final BeanSearchCriteria criteria) throws PortalException, SystemException {
        LOGGER.debug("Entering getLazyAvailableIFPResults method with filterText" + value);

        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpModel.class);
        // Added to check INBOUND_STATUS is A. ETL Soft delete data should not be included, where INBOUND_STATUS is D
        ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));
        if (StringUtils.isNotBlank(value)) {
            value = value.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(searchField, value));
        }
        ProjectionList projectonList = ProjectionFactoryUtil.projectionList();
        projectonList.add(ProjectionFactoryUtil.property(ConstantsUtils.IFP_MODEL_SID));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.IFPNO, StringUtils.EMPTY));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.IFPNAME, StringUtils.EMPTY));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.IFPNO));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.IFPNAME));

        ifpDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectonList));
        
        if (criteria != null && criteria.getFilters() != null) {
                for (Container.Filter filter : criteria.getFilters()) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;
                        if(ConstantsUtils.IFP_STATUS1.equals(stringFilter.getPropertyId())){
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.IFP_STATUS_SMALL,Integer.valueOf(stringFilter.getFilterString())));
                        }else if(ConstantsUtils.IFP_MODEL_TYPE.equals(stringFilter.getPropertyId())){
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.IFP_MODEL_TYPE,Integer.valueOf(stringFilter.getFilterString())));
                        }else if(ConstantsUtils.IFP_CATEGORY1.equals(stringFilter.getPropertyId())){
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.IFP_CATEGORY1,Integer.valueOf(stringFilter.getFilterString())));
                        }else{
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.like(PsUtils.getDBColumnName(String.valueOf(stringFilter.getPropertyId())), filterString));
                        }
                    } else if (filter instanceof Between) {
                         Between stringFilter = (Between) filter;
                         if(ConstantsUtils.IFP_MODEL_START_DATE.equals(stringFilter.getPropertyId())){
                             Date filterString = (Date) stringFilter.getStartValue();
                             Date filterString1 = (Date) stringFilter.getEndValue();
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantsUtils.IFP_MODEL_START_DATE, filterString, filterString1));
                           }
                         if(ConstantsUtils.IFP_MODEL_END_DATE.equals(stringFilter.getPropertyId())){
                             Date filterString = (Date) stringFilter.getStartValue();
                             Date filterString1 = (Date) stringFilter.getEndValue();
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantsUtils.IFP_MODEL_END_DATE, filterString, filterString1));
                           }
                        
                    }
                }
        }
        final int count = DAO.getItemFamilyplanMasterCount(ifpDynamicQuery);
        LOGGER.debug("Ending of  getLazyAvailableIFPCount with count -" + count);
        return count;
    }

    /**
     * getting results for CompanyQualifierName.
     *
     * @param start the start
     * @param end the end
     * @param searchField
     * @param systemIdList
     * @param value
     * @param orderByColumns
     * @param criteria
     * @return the lazy company qualifier name results
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public static List<PSIFPDTO> getLazyAvailableIFPResults(final int start, final int end, String searchField,
            String value, final Integer[] systemIdList, final List<OrderByColumn> orderByColumns,final BeanSearchCriteria criteria) throws PortalException, SystemException {
        LOGGER.debug("Entering getLazyAvailableIFPResults method with filterText" + value);
        List<PSIFPDTO> list = new ArrayList<PSIFPDTO>();
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpModel.class);
        // Added to check INBOUND_STATUS is A. ETL Soft delete data should not be included, where INBOUND_STATUS is D
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.IFPNO, StringUtils.EMPTY));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.IFPNAME, StringUtils.EMPTY));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.IFPNO));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.IFPNAME));
        ProjectionList projectonList = ProjectionFactoryUtil.projectionList();
        projectonList.add(ProjectionFactoryUtil.property(ConstantsUtils.IFPNO));
        projectonList.add(ProjectionFactoryUtil.property(ConstantsUtils.IFPNAME));
        projectonList.add(ProjectionFactoryUtil.property(ConstantsUtils.IFP_MODEL_SID));
        projectonList.add(ProjectionFactoryUtil.property("ifpId"));
        projectonList.add(ProjectionFactoryUtil.property(ConstantsUtils.IFP_STATUS_SMALL));
        projectonList.add(ProjectionFactoryUtil.property(ConstantsUtils.IFP_MODEL_START_DATE));
        projectonList.add(ProjectionFactoryUtil.property(ConstantsUtils.IFP_MODEL_END_DATE));
        projectonList.add(ProjectionFactoryUtil.property(ConstantsUtils.IFP_MODEL_TYPE));
        projectonList.add(ProjectionFactoryUtil.property(ConstantsUtils.IFP_CATEGORY1));
        ifpDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectonList));

        ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));
        if (StringUtils.isNotBlank(value)) {
            value = value.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(searchField, value));
        }
        PSIFPDTO dto;
          //loadColumnName is used to load the Hashmap for sorting purpose
        PsUtils.loadAvailableColumnName();        
        if (criteria != null && criteria.getFilters() != null) {
                for (Container.Filter filter : criteria.getFilters()) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;
                        if(ConstantsUtils.IFP_STATUS1.equals(stringFilter.getPropertyId())){
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.IFP_STATUS_SMALL,Integer.valueOf(stringFilter.getFilterString())));
                        }else if(ConstantsUtils.IFP_MODEL_TYPE.equals(stringFilter.getPropertyId())){
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.IFP_MODEL_TYPE,Integer.valueOf(stringFilter.getFilterString())));
                        }else if(ConstantsUtils.IFP_CATEGORY1.equals(stringFilter.getPropertyId())){
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.IFP_CATEGORY1,Integer.valueOf(stringFilter.getFilterString())));
                        }else{
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.like(PsUtils.getDBColumnName(String.valueOf(stringFilter.getPropertyId())), filterString));
                        }
                    } else if (filter instanceof Between) {
                         Between stringFilter = (Between) filter;
                         if(ConstantsUtils.IFP_MODEL_START_DATE.equals(stringFilter.getPropertyId())){
                             Date filterString = (Date) stringFilter.getStartValue();
                             Date filterString1 = (Date) stringFilter.getEndValue();
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantsUtils.IFP_MODEL_START_DATE, filterString, filterString1));
                           }
                         if(ConstantsUtils.IFP_MODEL_END_DATE.equals(stringFilter.getPropertyId())){
                             Date filterString = (Date) stringFilter.getStartValue();
                             Date filterString1 = (Date) stringFilter.getEndValue();
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantsUtils.IFP_MODEL_END_DATE, filterString, filterString1));
                           }
                        
                    }
                }
        }
        boolean sortFlag = false;
        String columnName = StringUtils.EMPTY;
        for (final Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                sortFlag = true;
                columnName = orderByColumn.getName();
                ifpDynamicQuery.addOrder(OrderFactoryUtil.asc(PsUtils.getDBColumnName(orderByColumn.getName())));
            } else {
                sortFlag = false;
                columnName = orderByColumn.getName();
                ifpDynamicQuery.addOrder(OrderFactoryUtil.desc(PsUtils.getDBColumnName(orderByColumn.getName())));
            }
        }
        final List<Object[]> returnList = DAO.getIFPList(ifpDynamicQuery);
        for (int i = 0; i < returnList.size(); i++) {
            Object[] obj = returnList.get(i);
            dto = new PSIFPDTO();
            dto.setItemFamilyplanNo(obj[0] != null ? obj[0].toString() : StringUtils.EMPTY);
            dto.setItemFamilyplanName(obj[1] != null ? obj[1].toString() : StringUtils.EMPTY);
            dto.setItemFamilyplanSystemId(obj[2] != null ? obj[2].toString() : StringUtils.EMPTY);
            dto.setItemFamilyPlanId(obj[3] != null ? obj[3].toString() : StringUtils.EMPTY);
            
            if(obj[4]!=null){
                int status = Integer.valueOf(String.valueOf(obj[4]));
                
                HelperTable helper = HelperTableLocalServiceUtil.getHelperTable(status);
                if(!helper.getDescription().equals(ConstantsUtils.SELECT_ONE)){
                dto.setItemFamilyPlanStatus(helper.getDescription());
                }
                
            }
            
            if(obj[5]!=null){
                Date startDate = (Date)obj[5];
                dto.setIfpStartDate(startDate);
                
            }
            
            if(obj[6]!=null){
                Date endDate = (Date)obj[6];
                dto.setIfpEndDate(endDate);
                
            }
            
             if(obj[7]!=null){
                int status = Integer.valueOf(String.valueOf(obj[7]));
                
                HelperTable helper = HelperTableLocalServiceUtil.getHelperTable(status);
                if(!helper.getDescription().equals(ConstantsUtils.SELECT_ONE)){
                dto.setIfpType(helper.getDescription());
                }
                
            }
             
              if(obj[8]!=null){
                int status = Integer.valueOf(String.valueOf(obj[8]));
                
                HelperTable helper = HelperTableLocalServiceUtil.getHelperTable(status);
                if(!helper.getDescription().equals(ConstantsUtils.SELECT_ONE)){
                dto.setIfpCategory(helper.getDescription());
                }
            }
            
            
            list.add(dto);
        }
        
         sortBy(columnName,sortFlag,list);
         columnName = StringUtils.EMPTY;
        LOGGER.debug("Ending of  getLazyAvailableIFPResults with list -" + ((list == null) ? list : list.size()));
        return list;
    }

    /**
     *
     * @param criteria
     * @return @throws SystemException
     */
    public int getResultTableCount(final Set<Container.Filter> filterSet) throws SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(ImtdPsDetails.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USER_SID, userId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, sessionId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.D));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, "F"));
        PsUtils.loadItemDetailsColumnName();
        if (filterSet!=null) {
        for (Container.Filter filter : filterSet) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;
                        if(ConstantsUtils.PRICE.equals(stringFilter.getPropertyId()) || ConstantsUtils.IFP_MODEL_END_DATE.equals(stringFilter.getPropertyId()) 
                                || ConstantsUtils.PRICE_TOLERANCE.equals(stringFilter.getPropertyId()) || ConstantsUtils.BASE_PRICE.equals(stringFilter.getPropertyId()) ){
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(PsUtils.getDBColumnName(String.valueOf(stringFilter.getPropertyId())), Double.valueOf(stringFilter.getFilterString())));
                        } else {
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(PsUtils.getDBColumnName(String.valueOf(stringFilter.getPropertyId())), filterString));
                    }
                    } else if (filter instanceof Between) {
                        Between stringFilter = (Between) filter;
                        Date filterString = (Date) stringFilter.getStartValue();
                        Date filterString1 = (Date) stringFilter.getEndValue();
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(PsUtils.getDBColumnName(stringFilter.getPropertyId().toString()), filterString));
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.lt(PsUtils.getDBColumnName(stringFilter.getPropertyId().toString()), filterString1));
                    } else if(filter instanceof Compare) {
                        Compare stringFilter = (Compare)filter;
                        if(stringFilter.getValue() instanceof Integer) {
                        if(stringFilter.getOperation().equals(stringFilter.getOperation().EQUAL)) {
                            if(ConstantsUtils.PRICE_TOL_TYPE.equals(stringFilter.getPropertyId()) || ConstantsUtils.PRICE_TOLERANCE_FREQ.equals(stringFilter.getPropertyId())){
                                
                                HelperDTO dto =(HelperDTO)stringFilter.getValue();
                                
                                 ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(PsUtils.getDBColumnName(String.valueOf(stringFilter.getPropertyId())), dto.getId()));
                            } else if(ConstantsUtils.PRICE_TOLERANCE_INTERVAL.equals(stringFilter.getPropertyId())){
                                HelperDTO dto =(HelperDTO)stringFilter.getValue();
                            if (dto.getId() == 0) {
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.isNull(PsUtils.getDBColumnName(String.valueOf(stringFilter.getPropertyId()))));
                            }else {
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(PsUtils.getDBColumnName(String.valueOf(stringFilter.getPropertyId())), dto.getId()));
                            }
                            }else{
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(PsUtils.getDBColumnName(String.valueOf(stringFilter.getPropertyId())),stringFilter.getValue()));
                            }
                        }                          
                        }
                        if(stringFilter.getValue() instanceof HelperDTO) {
                        if(stringFilter.getOperation().equals(stringFilter.getOperation().EQUAL)) {
                            if (((HelperDTO) stringFilter.getValue()) == null) {
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.isNull(PsUtils.getDBColumnName(String.valueOf(stringFilter.getPropertyId()))));
                            } else {
                            HelperDTO value = (HelperDTO)stringFilter.getValue();
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(PsUtils.getDBColumnName(String.valueOf(stringFilter.getPropertyId())), String.valueOf(value.getId())));
                            }
                        }                          
                        }
                        
                        if(ConstantsUtils.PRICE.equals(String.valueOf(stringFilter.getPropertyId()))){ 
                            if(stringFilter.getOperation().name().equals(stringFilter.getOperation().EQUAL.toString())){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PS_DETAILS_PRICE,value));
                            }else if(stringFilter.getOperation().name().equals(stringFilter.getOperation().LESS.toString())){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.PS_DETAILS_PRICE,value));
                            }else if(stringFilter.getOperation().name().equals(stringFilter.getOperation().GREATER.toString())){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantsUtils.PS_DETAILS_PRICE,value));
                            }
                          }
                        
                        if(ConstantsUtils.NEP.equals(String.valueOf(stringFilter.getPropertyId())) || ConstantsUtils.MAC_INC_CHANGE.equals(String.valueOf(stringFilter.getPropertyId()))){ 
                            if(stringFilter.getOperation().name().equals(stringFilter.getOperation().EQUAL.toString())){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(PsUtils.getDBColumnName(String.valueOf(stringFilter.getPropertyId())),value));
                            }else if(stringFilter.getOperation().name().equals(stringFilter.getOperation().LESS.toString())){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.lt(PsUtils.getDBColumnName(String.valueOf(stringFilter.getPropertyId())),value));
                            }else if(stringFilter.getOperation().name().equals(stringFilter.getOperation().GREATER.toString())){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.gt(PsUtils.getDBColumnName(String.valueOf(stringFilter.getPropertyId())),value));
                            }
                          }
                        
                        if(ConstantsUtils.IFP_MODEL_END_DATE.equals(String.valueOf(stringFilter.getPropertyId()))){
                            if(stringFilter.getOperation().name().equals(stringFilter.getOperation().EQUAL.toString())){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PS_DETAILS_CONTRACT_PRICE,value));
                            }else if(stringFilter.getOperation().name().equals(stringFilter.getOperation().LESS.toString())){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.PS_DETAILS_CONTRACT_PRICE,value));
                            }else if(stringFilter.getOperation().name().equals(stringFilter.getOperation().GREATER.toString())){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantsUtils.PS_DETAILS_CONTRACT_PRICE,value));
                            }
                          }
                        
                        if(ConstantsUtils.BASE_PRICE.equals(String.valueOf(stringFilter.getPropertyId()))){
                            if(stringFilter.getOperation().name().equals(stringFilter.getOperation().EQUAL.toString())){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PS_DETAILS_BASE_PRICE,value));
                            }else if(stringFilter.getOperation().name().equals(stringFilter.getOperation().LESS.toString())){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.PS_DETAILS_BASE_PRICE,value));
                            }else if(stringFilter.getOperation().name().equals(stringFilter.getOperation().GREATER.toString())){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantsUtils.PS_DETAILS_BASE_PRICE,value));
                            }
                          }
                        if(ConstantsUtils.PRICE_TOLERANCE.equals(String.valueOf(stringFilter.getPropertyId()))){
                            if(stringFilter.getOperation().name().equals(stringFilter.getOperation().EQUAL.toString())){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PS_PRICE_TOLERANCE,value));
                            }else if(stringFilter.getOperation().name().equals(stringFilter.getOperation().LESS.toString())){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.PS_PRICE_TOLERANCE,value));
                            }else if(stringFilter.getOperation().name().equals(stringFilter.getOperation().GREATER.toString())){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantsUtils.PS_PRICE_TOLERANCE,value));
                            }
                          }
                        } else if (filter instanceof And) {
                            And stringFilter = (And) filter;
                        Collection<Container.Filter> value = stringFilter.getFilters();
                        for (Container.Filter filter1 : value) {
                        Object propertyId = StringUtils.EMPTY;
                        if (filter1 instanceof Compare.Less) {
                            Compare.Less less = (Compare.Less) filter1;
                            propertyId = less.getPropertyId();
                            if(ConstantsUtils.PRICE.equals(propertyId)){
                                double lessValue =Double.valueOf(String.valueOf(less.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.PS_DETAILS_PRICE, lessValue));
                            }else if(ConstantsUtils.IFP_MODEL_END_DATE.equals(propertyId)){
                                double lessValue =Double.valueOf(String.valueOf(less.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.PS_DETAILS_CONTRACT_PRICE, lessValue));
                            }else if(ConstantsUtils.BASE_PRICE.equals(propertyId)){
                                double lessValue =Double.valueOf(String.valueOf(less.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.PS_DETAILS_BASE_PRICE, lessValue));
                            }else if(ConstantsUtils.PRICE_TOLERANCE.equals(propertyId)){
                                double lessValue =Double.valueOf(String.valueOf(less.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.PS_PRICE_TOLERANCE, lessValue));
                            }else{
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.lt(String.valueOf(propertyId), less.getValue()));
                            }
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            propertyId = greater.getPropertyId();
                            if(ConstantsUtils.PRICE.equals(propertyId)){
                                double greaterValue =Double.valueOf(String.valueOf(greater.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantsUtils.PS_DETAILS_PRICE, greaterValue));
                            }else if(ConstantsUtils.IFP_MODEL_END_DATE.equals(propertyId)){
                                double greaterValue =Double.valueOf(String.valueOf(greater.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantsUtils.PS_DETAILS_CONTRACT_PRICE, greaterValue));
                            }else if(ConstantsUtils.BASE_PRICE.equals(propertyId)){
                                double greaterValue =Double.valueOf(String.valueOf(greater.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantsUtils.PS_DETAILS_BASE_PRICE, greaterValue));
                            }else if(ConstantsUtils.PRICE_TOLERANCE.equals(propertyId)){
                                double greaterValue =Double.valueOf(String.valueOf(greater.getValue()));
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantsUtils.PS_PRICE_TOLERANCE, greaterValue));
                            }else{
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.gt(String.valueOf(propertyId), greater.getValue()));
                            }
                        }
                    }
                }
        }
    }
        int count = Integer.parseInt(String.valueOf(ImtdPsDetailsLocalServiceUtil.dynamicQueryCount(ifpDynamicQuery)));
        return count;
    }

    public List<PSIFPDTO> getResultTableResult(int start, int end, final ErrorfulFieldGroup binder) throws SystemException, PortalException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(ImtdPsDetails.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USER_SID, userId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, sessionId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.D));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, "F"));
        ifpDynamicQuery.setLimit(start, end);
        List<ImtdPsDetails> resultList = ImtdPsDetailsLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
        return getCustomizedResultDTO(resultList, binder);
    }
    
    public List<Object[]> getItemPriceDetails(final Set<Container.Filter> filterSet,int start, int end, final ErrorfulFieldGroup binder,List<SortByColumn> orderByColumns, String mode, PSDTO psMaster, Boolean iscount) throws SystemException, PortalException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        Map<String, Object> parameters = new HashMap<String, Object>();
        String columnName="ITEM_NAME";
        String orderBy="ASC";
        
        if(iscount){
            parameters.put("Count", "true");
        }else{
            parameters.put("Count", "false");
        }
               
        if(!StringUtils.isBlank(psMaster.getRecord())){
            if(psMaster.getRecord().contains(ConstantsUtils.CURRENT)){
                    parameters.put(ConstantsUtils.CURRENT, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
                if(psMaster.getRecord().contains(ConstantsUtils.HISTORY)){
                    parameters.put(ConstantsUtils.HISTORY, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
                if(psMaster.getRecord().contains(ConstantsUtils.FUTURE)){
                    parameters.put(ConstantsUtils.FUTURE, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
        }
        if(orderByColumns!=null){
            for (final Iterator<SortByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn orderByColumn = (SortByColumn) iterator.next();
                if(orderByColumn.getName().equals(ConstantsUtils.IFP_NO)){
                    columnName="IFP_NO";
                }else if(orderByColumn.getName().equals(ConstantsUtils.ITEM_NO)){
                    columnName="ITEM_NO";
                }else if(orderByColumn.getName().equals(ConstantsUtils.ITEM_NAME)){
                    columnName="ITEM_NAME";
                }else if(orderByColumn.getName().equals(ConstantsUtils.PRICE)){
                    columnName="PS_DETAILS_PRICE";
                }else if(orderByColumn.getName().equals(ConstantsUtils.PRICE_TYPE)){
                    columnName="qualifiername";
                }else if(orderByColumn.getName().equals(ConstantsUtils.IFP_MODEL_END_DATE)){
                    columnName="PS_DETAILS_CONTRACT_PRICE";
                }else if(orderByColumn.getName().equals("contractPriceStartDate")){
                    columnName="PS_DTLS_CONT_PRICE_STARTDATE";
                }else if(orderByColumn.getName().equals("contractPriceEndDate")){
                    columnName="PS_DTLS_CONT_PRICE_ENDDATE";
                }else if(orderByColumn.getName().equals(ConstantsUtils.PRICE_TOLERANCE)){
                    columnName="PS_DETAILS_PRICE_TOLERANCE";
                }else if(orderByColumn.getName().equals("priceProtectionStartDate")){
                    columnName="PS_DETAILS_PRIC_PRTCN_STDATE";
                }else if(orderByColumn.getName().equals("priceProtectionEndDate")){
                    columnName="PS_DETAILS_PRIC_PRTCN_EDDATE";
                }else if(orderByColumn.getName().equals(ConstantsUtils.PRICE_TOL_TYPE)){
                    columnName="toltype";
                }else if(orderByColumn.getName().equals(ConstantsUtils.PRICE_TOLERANCE_INTERVAL)){
                    columnName="tolinterval";
                }else if(orderByColumn.getName().equals(ConstantsUtils.PRICE_TOLERANCE_FREQ)){
                    columnName="tolfrequency";
                }else if(orderByColumn.getName().equals(ConstantsUtils.BASE_PRICE)){
                    columnName="PS_DETAILS_BASE_PRICE";
                }else if(orderByColumn.getName().equals("revisionDate")){
                    columnName="PS_DETAILS_REVISION_DATE";
                }else if(orderByColumn.getName().equals("attachedDate")){
                    columnName = "PS_DETAILS_ATTACHED_DATE";
                } else if (orderByColumn.getName().equals("createdUserName")) {
                    columnName = "PS_DETAILS_CREATED_BY";
                } else if (orderByColumn.getName().equals("modifiedBy")) {
                    columnName = "PS_DETAILS_MODIFIED_BY";
                } else if (orderByColumn.getName().equals("itemID")) {
                    columnName = "ITEM_ID";
                }else if(orderByColumn.getName().equals("itemStatus")){
                    columnName="Status";
                }else if(orderByColumn.getName().equals("priceProtectionStatus")){
                    columnName="PRICE_PROTECTION_STATUS";
                }else if(orderByColumn.getName().equals("priceProtectionPriceType")){
                    columnName="PRICE_PROTECTION_PRICE_TYPE";
                }else if(orderByColumn.getName().equals(ConstantsUtils.NEP)){
                    columnName=ConstantsUtils.NEP;
                }else if(orderByColumn.getName().equals(ConstantsUtils.MAC_INC_CHANGE)){
                    columnName="MAX_INCREMENTAL_CHANGE";
                }else if(orderByColumn.getName().equals("resetType")){
                    columnName="RESET_TYPE";
                }else if(orderByColumn.getName().equals("resetDate")){
                    columnName="RESET_DATE";
                }else if(orderByColumn.getName().equals("resetInterval")){
                    columnName="RESET_INTERVAL";
                }else if(orderByColumn.getName().equals("resetFrequency")){
                    columnName="RESET_FREQUENCY";
                }else if(orderByColumn.getName().equals("netPriceType")){
                    columnName="NET_PRICE_TYPE";
                }
                 if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                        orderBy="ASC";
                    
                } else {
                        orderBy="DESC";
                }
            }}
                PsUtils.loadItemDetailsColumnName();
                if (filterSet!=null) {
                  for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
        
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    if (ConstantsUtils.PRICE_TYPE.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.PRICE_TYPE, Integer.valueOf(PsUtils.getDBColumnName(stringFilter.getFilterString())));
                    }else if (ConstantsUtils.IFP_MODEL_END_DATE.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.IFP_MODEL_END_DATE, Integer.valueOf(PsUtils.getDBColumnName(stringFilter.getFilterString())));
                    }else if (ConstantsUtils.PRICE_TOLERANCE.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.PRICE_TOLERANCE, Integer.valueOf(PsUtils.getDBColumnName(stringFilter.getFilterString())));
                    }else if (ConstantsUtils.PRICE_TOL_TYPE.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.PRICE_TOL_TYPE, Integer.valueOf(PsUtils.getDBColumnName(stringFilter.getFilterString())));
                    }else if (ConstantsUtils.PRICE_TOLERANCE_INTERVAL.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.PRICE_TOLERANCE_INTERVAL, Integer.valueOf(PsUtils.getDBColumnName(stringFilter.getFilterString())));
                    }else if (ConstantsUtils.PRICE_TOLERANCE_FREQ.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.PRICE_TOLERANCE_FREQ, Integer.valueOf(PsUtils.getDBColumnName(stringFilter.getFilterString())));
                    }else if ("netBasePrice".equals(stringFilter.getPropertyId()) || "netSubsequentPeriodPrice".equals(stringFilter.getPropertyId())
                            || "netResetPriceType".equals(stringFilter.getPropertyId()) || "basePriceType".equals(stringFilter.getPropertyId())) {
                        parameters.put(StringUtils.EMPTY+stringFilter.getPropertyId(), stringFilter.getFilterString());
                    }else if (ConstantsUtils.BASE_PRICE.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.BASE_PRICE, Integer.valueOf(PsUtils.getDBColumnName(stringFilter.getFilterString())));
                    }else {
                        String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;
                        parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId(), filterString);
                    }
                }else if (filter instanceof Compare) {
                    
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (operation.EQUAL.toString().equals(operation.name())) {
                        
                        if(ConstantsUtils.PRICE.equals(String.valueOf(stringFilter.getPropertyId())) || ConstantsUtils.NEP.equals(String.valueOf(stringFilter.getPropertyId()))
                                || ConstantsUtils.MAC_INC_CHANGE.equals(String.valueOf(stringFilter.getPropertyId()))){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                parameters.put(String.valueOf(stringFilter.getPropertyId())+"-equal", value);
                               
                        }else if(ConstantsUtils.IFP_MODEL_END_DATE.equals(String.valueOf(stringFilter.getPropertyId()))){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                parameters.put("contractPrice-equal", value);
                               
                        }else if(ConstantsUtils.BASE_PRICE.equals(String.valueOf(stringFilter.getPropertyId()))){
                                 double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                parameters.put("basePrice-equal", value);
                               
                        }else if(ConstantsUtils.PRICE_TOLERANCE.equals(String.valueOf(stringFilter.getPropertyId()))){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                parameters.put("priceTolerance-equal", value);
                               
                        }else if ( stringFilter.getValue() == null) {
                        } else {
                            int val = (Integer) stringFilter.getValue();
                            parameters.put(String.valueOf(stringFilter.getPropertyId()), String.valueOf(val));
                        }
                        }
                    if (operation.GREATER.toString().equals(operation.name())) {
                        if(ConstantsUtils.PRICE.equals(String.valueOf(stringFilter.getPropertyId())) || ConstantsUtils.NEP.equals(String.valueOf(stringFilter.getPropertyId()))
                                || ConstantsUtils.MAC_INC_CHANGE.equals(String.valueOf(stringFilter.getPropertyId()))){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                parameters.put(String.valueOf(stringFilter.getPropertyId())+"-greater", value);
                               
                        }else if(ConstantsUtils.IFP_MODEL_END_DATE.equals(String.valueOf(stringFilter.getPropertyId()))){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                parameters.put("contractPrice-greater", value);
                               
                        }else if(ConstantsUtils.BASE_PRICE.equals(String.valueOf(stringFilter.getPropertyId()))){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                parameters.put("basePrice-greater", value);
                               
                        }else if(ConstantsUtils.PRICE_TOLERANCE.equals(String.valueOf(stringFilter.getPropertyId()))){
                                 double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                parameters.put("priceTolerance-greater", value);
                               
                        }else{
                        
                            int val = (Integer) stringFilter.getValue();
                            if (val < 0) {
                                parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~>", String.valueOf(val) + "--" + ">0");
                            } else {
                                parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~>", String.valueOf(val) + "--" + ">");
                            }
                        }
                    }
                    if (operation.LESS.toString().equals(operation.name())) {
                        
                        if(ConstantsUtils.PRICE.equals(String.valueOf(stringFilter.getPropertyId())) || ConstantsUtils.NEP.equals(String.valueOf(stringFilter.getPropertyId()))
                                || ConstantsUtils.MAC_INC_CHANGE.equals(String.valueOf(stringFilter.getPropertyId()))){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                parameters.put(String.valueOf(stringFilter.getPropertyId())+"-less", value);
                               
                        }else if(ConstantsUtils.IFP_MODEL_END_DATE.equals(String.valueOf(stringFilter.getPropertyId()))){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                parameters.put("contractPrice-less", value);
                               
                        }else if(ConstantsUtils.BASE_PRICE.equals(String.valueOf(stringFilter.getPropertyId()))){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                parameters.put("basePrice-less", value);
                               
                        }else if(ConstantsUtils.PRICE_TOLERANCE.equals(String.valueOf(stringFilter.getPropertyId()))){
                                double value =Double.valueOf(String.valueOf(stringFilter.getValue()));
                                parameters.put("priceTolerance-less", value);
                               
                        }else{
                        int val = (Integer) stringFilter.getValue();
                        if (val > 0) {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~<", String.valueOf(val) + "--" + "<0");
                        } else {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~<", String.valueOf(val) + "--" + "<");
                        }
                        }
                    }                   
                } else if (filter instanceof And) {
                    And stringFilter = (And) filter;
                    Collection<Container.Filter> value = stringFilter.getFilters();
                    for (Container.Filter filter1 : value) {
                        Object propertyId = StringUtils.EMPTY;
                        if (filter1 instanceof Compare.Less) {
                            
                            Compare.Less less = (Compare.Less) filter1;
                            propertyId = less.getPropertyId();
                            if(ConstantsUtils.PRICE.equals(propertyId) || ConstantsUtils.NEP.equals(propertyId) || ConstantsUtils.MAC_INC_CHANGE.equals(propertyId)){
                                parameters.put(propertyId+"andless",String.valueOf(less.getValue()));
                            }else if(ConstantsUtils.IFP_MODEL_END_DATE.equals(propertyId)){
                                parameters.put("contractPriceandless",String.valueOf(less.getValue()));
                            }else if(ConstantsUtils.BASE_PRICE.equals(propertyId)){
                                parameters.put("basePriceandless",String.valueOf(less.getValue()));
                            }else if(ConstantsUtils.PRICE_TOLERANCE.equals(propertyId)){
                                parameters.put("priceToleranceandless",String.valueOf(less.getValue()));
                            }else{
                            parameters.put(ConstantsUtils.FILTER + less.getPropertyId() + "~<<", String.valueOf(less.getValue()) + "--" + "<<");
                            }
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            propertyId = greater.getPropertyId();
                            if(ConstantsUtils.PRICE.equals(propertyId) || ConstantsUtils.NEP.equals(propertyId) || ConstantsUtils.MAC_INC_CHANGE.equals(propertyId)){
                                parameters.put(propertyId+"andgreater",String.valueOf(greater.getValue()));
                            }else if(ConstantsUtils.IFP_MODEL_END_DATE.equals(propertyId)){
                                parameters.put("contractPriceandgreater",String.valueOf(greater.getValue()));
                            }else if(ConstantsUtils.BASE_PRICE.equals(propertyId)){
                                parameters.put("basePriceandgreater",String.valueOf(greater.getValue()));
                            }else if(ConstantsUtils.PRICE_TOLERANCE.equals(propertyId)){
                                parameters.put("priceToleranceandgreater",String.valueOf(greater.getValue()));
                            }else{
                            parameters.put(ConstantsUtils.FILTER + greater.getPropertyId() + "~>>", String.valueOf(greater.getValue()) + "--" + ">>");
                            }
                        }
                    }
            }
        }
                
    }       
            
            
        List<Object[]> resultList = ImtdPsDetailsLocalServiceUtil.getItemPriceDetails(start, end, userId, sessionId, columnName, orderBy,parameters);
        return  resultList ;
    }
    
    
    public static List<PSIFPDTO> getCustomizedItemPriceDTO(List<Object[]> list, final ErrorfulFieldGroup binder, String mode, PSDTO psMaster) throws PortalException, SystemException {
        Map<Integer, String> hm = com.stpl.app.util.GeneralCommonUtils.getCodeDescription();
        List<PSIFPDTO> resultList = new ArrayList<PSIFPDTO>();
        Map<Integer, String> userMap= StplSecurity.getUserName();
       if(list!=null){
        for (Object[] temp : list) {
            PSIFPDTO resultDTO = new PSIFPDTO();
            com.stpl.ifs.util.HelperDTO helper = null;
            resultDTO.setTempPsDetailsSystemId((Integer)temp[0]);
            resultDTO.setCheckRecord((Boolean)temp[1]);
            resultDTO.setItemFamilyplanNo(String.valueOf(temp[2]));
            resultDTO.setItemName(String.valueOf(temp[3]));
            resultDTO.setItemNo(String.valueOf(temp[4]));
            resultDTO.setItemSystemId(String.valueOf(temp[5]));
            resultDTO.setAttachedDate((Date)temp[6]);
            resultDTO.setAttachedStatus(String.valueOf(temp[7]));
            if(temp[8]==null){
                temp[8]=ConstantsUtils.ZERO;
            }
            resultDTO.setBasePrice(CommonUtils.convertWith2Decimal(String.valueOf(temp[8])));
            if(temp[9]==null){
               temp[9]=ConstantsUtils.ZERO;
            }
            resultDTO.setContractPrice(CommonUtils.convertWith2Decimal(String.valueOf(temp[9])));
            if(temp[10]!=null){
            resultDTO.setContractPriceEndDate((Date)temp[10]);
            }
            resultDTO.setContractPriceStartDate((Date)temp[11]);
            resultDTO.setItemFamilyplanSystemId(String.valueOf(temp[12]));
            if(temp[13]==null){
                temp[13]=ConstantsUtils.ZERO;
            }
            resultDTO.setPrice(CommonUtils.convertWith2Decimal(String.valueOf(temp[13])));
            if(temp[14]!=null){
            resultDTO.setPriceProtectionEndDate((Date)temp[14]);
            }
            if(temp[15]!=null){
            resultDTO.setPriceProtectionStartDate((Date)temp[15]);
            }
            resultDTO.setPriceScheduleSystemId(String.valueOf(temp[16]));
            if(temp[17]==null){
                temp[17]=ConstantsUtils.ZERO;
            }
            resultDTO.setPriceTolerance(CommonUtils.convertWith2Decimal(String.valueOf(temp[17])));

            resultDTO.setPriceToleranceFrequency(temp[18]==null || String.valueOf(temp[18]).isEmpty() ? helperListUtil.getIdHelperDTOMap().get(0) : helperListUtil.getIdHelperDTOMap().get(Integer.parseInt(String.valueOf(temp[18]))));
            resultDTO.setPriceToleranceFrequencyValue(temp[18]==null  || String.valueOf(temp[18]).isEmpty()|| Integer.parseInt(String.valueOf(temp[18]))==0 ? StringUtils.EMPTY : hm.get(Integer.parseInt(String.valueOf(temp[18]))));
            helper = temp[19]==null || StringUtils.isEmpty(String.valueOf(temp[19])) || ConstantsUtils.ZERO.equals(String.valueOf(temp[19]))
                    ? new com.stpl.ifs.util.HelperDTO(0,Constants.SELECT_ONE) : new com.stpl.ifs.util.HelperDTO(Integer.valueOf(String.valueOf(temp[19])), CommonUtils.getHelperDescription(Integer.valueOf(String.valueOf(temp[19]))));
            resultDTO.setPriceToleranceInterval(helper);
            resultDTO.setPriceToleranceType(temp[20]==null  || String.valueOf(temp[20]).isEmpty()? helperListUtil.getIdHelperDTOMap().get(0) : helperListUtil.getIdHelperDTOMap().get(Integer.parseInt(String.valueOf(temp[20]))));
            resultDTO.setPriceToleranceTypeValue((temp[20]==null || Integer.parseInt(String.valueOf(temp[20]))==0)?StringUtils.EMPTY:hm.get(Integer.parseInt(String.valueOf(temp[20]))));
            if(temp[21]!=null){
            resultDTO.setRevisionDate((Date)temp[21]);
            }
            resultDTO.setUserID(Integer.parseInt(String.valueOf(temp[22])));
             resultDTO.setSessionID(String.valueOf(temp[23]));
            resultDTO.setCreatedBy(Integer.parseInt(String.valueOf(temp[24])));
            if (temp[24]!=null) resultDTO.setCreatedUserName(userMap.get(Integer.valueOf(temp[24].toString()))==null ? StringUtils.EMPTY : userMap.get(Integer.valueOf(temp[24].toString())));
            if(temp[25]!=null){
            resultDTO.setCreatedDate((Date)temp[25]);
            }
             resultDTO.setModifiedBy(String.valueOf(temp[26]));
            if(temp[27]!=null){
            resultDTO.setModifiedDate((Date)temp[27]);
            }
            resultDTO.setPriceScheduleDetailsSystemId(String.valueOf(temp[28]));
            resultDTO.setPriceScheduleSystemId(String.valueOf(temp[29]));
            resultDTO.setOperation(String.valueOf(temp[30]));

            HelperDTO helper1 = null ;
            try {
                 helper1 = new HelperDTO(temp[31]==null ? 0 : Integer.parseInt(String.valueOf(temp[31])), getPriceTypeNameByID(String.valueOf(temp[31])));
            } catch (SystemException ex) {
                java.util.logging.Logger.getLogger(PSLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            resultDTO.setPriceType(helper1);
            resultDTO.setItemID(String.valueOf(temp[36]));
            resultDTO.setBrandMasterSID(String.valueOf(temp[37]));
            resultDTO.setBrand(temp[37]!=null && StringUtils.isNotEmpty(String.valueOf(temp[37])) && !ConstantsUtils.ZERO.equals(String.valueOf(temp[37])) ? BrandMasterLocalServiceUtil.getBrandMaster(Integer.valueOf(String.valueOf(temp[37]))).getBrandName() : StringUtils.EMPTY);
            helper = temp[38]==null || StringUtils.isEmpty(String.valueOf(temp[38])) || ConstantsUtils.ZERO.equals(String.valueOf(temp[38]))
                    ? new com.stpl.ifs.util.HelperDTO(0,Constants.SELECT_ONE) : new com.stpl.ifs.util.HelperDTO(Integer.valueOf(String.valueOf(temp[38])), CommonUtils.getHelperDescription(Integer.valueOf(String.valueOf(temp[38]))));
            resultDTO.setPriceProtectionStatus(helper);
            resultDTO.setNep(temp[39]!=null && StringUtils.isNotEmpty(String.valueOf(temp[39])) ? String.valueOf(temp[39]) : StringUtils.EMPTY );
            resultDTO.setItemStatus(temp[40]!=null && StringUtils.isNotEmpty(String.valueOf(temp[40])) ? helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(temp[40].toString())) : helperListUtil.getIdHelperDTOMap().get(0));
            resultDTO.setMaxIncrementalChange(temp[41]!=null ?String.valueOf(temp[41]):StringUtils.EMPTY);           
            resultDTO.setResetEligible(helperListUtil.getIdHelperDTOMap().get(temp[42] != null && !String.valueOf(temp[42]).isEmpty() ? Integer.valueOf(String.valueOf(temp[42])) : 0));
            resultDTO.setResetType(temp[43]!=null && StringUtils.isNotEmpty(String.valueOf(temp[43])) ? helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(temp[43].toString())) : helperListUtil.getIdHelperDTOMap().get(0));
            if(temp[44]!=null) resultDTO.setResetDate((Date)temp[44]);
            resultDTO.setResetInterval(temp[45]!=null && StringUtils.isNotEmpty(String.valueOf(temp[45])) ? helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(temp[45].toString())) : helperListUtil.getIdHelperDTOMap().get(0));
            resultDTO.setResetFrequency(temp[46]!=null && StringUtils.isNotEmpty(String.valueOf(temp[46])) ? helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(temp[46].toString())) : helperListUtil.getIdHelperDTOMap().get(0));
           
            resultDTO.setNetPriceType(helperListUtil.getIdHelperDTOMap().get(temp[47] != null  && !String.valueOf(temp[47]).isEmpty()? Integer.valueOf(String.valueOf(temp[47])) : 0));
            if(temp[48]!=null)resultDTO.setNetPriceTypeFormula(String.valueOf(temp[48]));
             if (temp[48] != null && !ConstantsUtils.ZERO.equals(String.valueOf(temp[48])) && !ConstantsUtils.NULL.equals(String.valueOf(temp[48])) && !String.valueOf(temp[48]).isEmpty()) {
                  String formula = StringUtils.EMPTY;

                 try {
                     resultDTO.setPriceTypeFormulaSid(String.valueOf(temp[48]));

                     formula = NetSalesFormulaMasterLocalServiceUtil.getNetSalesFormulaMaster(Integer.valueOf(String.valueOf(temp[48]))).getNetSalesFormulaNo();

                 } catch (Exception e) {
                     LOGGER.error(e);
                 }
                 resultDTO.setNetPriceTypeFormulaName(formula);
            }
            try {
                 helper1 = new HelperDTO(temp[49]==null ? 0 : Integer.parseInt(String.valueOf(temp[49])), getPriceTypeNameByID(String.valueOf(temp[49])));
            } catch (SystemException ex) {
                java.util.logging.Logger.getLogger(PSLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            resultDTO.setPriceProtectionPriceType(helper1);
            if(temp[50]!=null)resultDTO.setSuggestedPrice(String.valueOf(temp[50]));
            if (ConstantsUtils.ADD.equals(mode)) {
                resultDTO.setSource(ConstantsUtils.GTN);
            }else{
                resultDTO.setSource(psMaster.getSource());
            }
            if (temp[51] != null && !ConstantsUtils.ZERO.equals(String.valueOf(temp[51])) && !ConstantsUtils.NULL.equals(String.valueOf(temp[51])) && !String.valueOf(temp[51]).isEmpty()) 
            {
                resultDTO.setNepFormulaId(String.valueOf(temp[51]));
                resultDTO.setNepFormulaSid(Integer.parseInt(String.valueOf(temp[51])));
                String formula = StringUtils.EMPTY;
                try {
                    formula = NetSalesFormulaMasterLocalServiceUtil.getNetSalesFormulaMaster(Integer.valueOf(String.valueOf(temp[51]))).getNetSalesFormulaNo();//NET_SALES_FORMULA_MASTER
                    
                } catch (Exception e) {
                    LOGGER.error(e);
                }
                resultDTO.setNepFormula(formula);
            }
     
                resultDTO.setBasePriceType(helperListUtil.getIdHelperDTOMap().get(temp[52] != null  && !String.valueOf(temp[52]).isEmpty()? Integer.valueOf(String.valueOf(temp[52])) : 0));
                resultDTO.setBasePriceEntry(PsUtils.checkNullValues(temp[53]) ? 0.00 :  ((BigDecimal)temp[53]).intValue());
                resultDTO.setBasePriceDate(PsUtils.checkNullValues(temp[54]) ? null : (Date) temp[54]);
                 resultDTO.setNetBasePrice(helperListUtil.getIdHelperDTOMap().get(temp[55] != null  && !String.valueOf(temp[55]).isEmpty()? Integer.valueOf(String.valueOf(temp[55])) : 0));
                if (!PsUtils.checkNullValues(temp[56])) {
                    HelperDTO helperDTO = new HelperDTO((Integer) temp[56], psMaster.getItemPricingQualifierMap().get((Integer) temp[56]));
                    resultDTO.setBasePriceItemPriceType(helperDTO);
                }
                if (Constants.MANUAL.equals(resultDTO.getBasePriceType().getDescription())) {
                    resultDTO.setBasePriceValue(FORMATDECIMAL.format(resultDTO.getBasePriceEntry()));
               } else if (Constants.DATE.equals(resultDTO.getBasePriceType().getDescription())) {
                   resultDTO.setBasePriceValue(resultDTO.getBasePriceDate());
               } else if (Constants.PRICE_TYPE.equals(resultDTO.getBasePriceType().getDescription())) {  
                   resultDTO.setBasePriceValue(resultDTO.getBasePriceItemPriceType());
               }
                if (!PsUtils.checkNullValues(temp[57])) {
                    HelperDTO helperDTO = new HelperDTO((Integer) temp[57], psMaster.getItemPricingQualifierMap().get((Integer) temp[57]));
                    resultDTO.setSubsequentPeriodPriceType(helperDTO);
                }
                 resultDTO.setNetSubsequentPeriodPrice(helperListUtil.getIdHelperDTOMap().get(temp[58] != null && !String.valueOf(temp[58]).isEmpty() ? Integer.valueOf(String.valueOf(temp[58])) : 0));
                resultDTO.setNetSubsequentPriceFormulaId(PsUtils.checkNullValues(temp[59]) ? 0 : (Integer) temp[59]);
                resultDTO.setNetSubsequentPriceFormulaName(PsUtils.checkNullValues(temp[60]) ? StringUtils.EMPTY : String.valueOf(temp[60]));
                if (!PsUtils.checkNullValues(temp[61])) {
                    HelperDTO helperDTO = new HelperDTO((Integer) temp[61], psMaster.getItemPricingQualifierMap().get((Integer) temp[61]));
                    resultDTO.setResetPriceType(helperDTO);
                }
                resultDTO.setNetResetPriceType(helperListUtil.getIdHelperDTOMap().get(temp[62] != null  && !String.valueOf(temp[62]).isEmpty()? Integer.valueOf(String.valueOf(temp[62])) : 0));
                resultDTO.setNetResetPriceFormulaId(PsUtils.checkNullValues(temp[63]) ? 0 : (Integer) temp[63]);
                resultDTO.setNetResetPriceFormulaName(PsUtils.checkNullValues(temp[64]) ? StringUtils.EMPTY : String.valueOf(temp[64]));
                resultDTO.setNetBasePriceFormulaId(PsUtils.checkNullValues(temp[65]) ? 0 : (Integer) temp[65]);
                resultDTO.setNetBasePriceFormulaName(PsUtils.checkNullValues(temp[66]) ? StringUtils.EMPTY : String.valueOf(temp[66]));

                resultList.add(resultDTO);
            }
        }
        return resultList;
    }
    

    public static List<PSIFPDTO> getCustomizedResultDTO(List<ImtdPsDetails> list, final ErrorfulFieldGroup binder) throws PortalException, SystemException {
        Map<Integer, String> hm = com.stpl.app.util.GeneralCommonUtils.getCodeDescription();
        List<PSIFPDTO> resultList = new ArrayList<PSIFPDTO>();
        for (Iterator<ImtdPsDetails> temp = list.iterator(); temp.hasNext();) {
            ImtdPsDetails item = temp.next();
            PSIFPDTO resultDTO = new PSIFPDTO();
            resultDTO.setTempPsDetailsSystemId(item.getImtdPsDetailsSid());

            resultDTO.setCheckRecord(item.getCheckRecord());
            resultDTO.setItemFamilyplanNo(item.getIfpNo());
            resultDTO.setItemName(item.getItemName());
            resultDTO.setItemNo(item.getItemNo());
            resultDTO.setItemSystemId(String.valueOf(item.getItemMasterSid()));

            resultDTO.setAttachedDate(item.getPsDetailsAttachedDate());
            resultDTO.setAttachedStatus(String.valueOf(item.getPsDetailsAttachedStatus()));
            resultDTO.setBasePrice(CommonUtils.convertBigDecimal(String.valueOf(item.getPsDetailsBasePrice())));
            resultDTO.setContractPrice(CommonUtils.convertBigDecimal(String.valueOf(item.getPsDetailsContractPrice())));

            resultDTO.setContractPriceEndDate(item.getPsDtlsContPriceEnddate());
            resultDTO.setContractPriceStartDate(item.getPsDtlsContPriceStartdate());
            resultDTO.setItemFamilyplanSystemId(String.valueOf(item.getIfpModelSid()));
            resultDTO.setPrice(CommonUtils.convertBigDecimal(String.valueOf(item.getPsDetailsPrice())));
            resultDTO.setPriceProtectionEndDate(item.getPsDetailsPricPrtcnEddate());
            resultDTO.setPriceProtectionStartDate(item.getPsDetailsPricPrtcnStdate());
            resultDTO.setPriceScheduleSystemId(String.valueOf(item.getPsModelSid()));
            resultDTO.setPriceTolerance(CommonUtils.convertBigDecimal(String.valueOf(item.getPsDetailsPriceTolerance())));
            com.stpl.ifs.util.HelperDTO helper = null;
            resultDTO.setPriceToleranceFrequency(item.getPsDtlsPriceToleranceFreq()==0 ? helperListUtil.getIdHelperDTOMap().get(0) : helperListUtil.getIdHelperDTOMap().get(item.getPsDtlsPriceToleranceFreq()));
            resultDTO.setPriceToleranceFrequencyValue(hm.get(item.getPsDtlsPriceToleranceFreq()==0 ? 0 : item.getPsDtlsPriceToleranceFreq()));
            helper = item.getPsDtlsPriceToleranceIntrvl()==0 || StringUtils.isEmpty(String.valueOf(item.getPsDtlsPriceToleranceIntrvl())) || ConstantsUtils.ZERO.equals(String.valueOf(item.getPsDtlsPriceToleranceIntrvl()))
                    ? new com.stpl.ifs.util.HelperDTO(0,Constants.SELECT_ONE) : new com.stpl.ifs.util.HelperDTO(Integer.valueOf(String.valueOf(item.getPsDtlsPriceToleranceIntrvl())), CommonUtils.getHelperDescription(Integer.valueOf(String.valueOf(item.getPsDtlsPriceToleranceIntrvl()))));
            resultDTO.setPriceToleranceInterval(helper);
            resultDTO.setPriceToleranceIntervalValue(hm.get(item.getPsDtlsPriceToleranceIntrvl()));
            resultDTO.setPriceToleranceType(item.getPsDtlsPriceToleranceType()==0 ? helperListUtil.getIdHelperDTOMap().get(0) : helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(item.getPsDtlsPriceToleranceType())));
            resultDTO.setPriceToleranceTypeValue(hm.get(item.getPsDtlsPriceToleranceType()==0 ? 0 : Integer.valueOf(item.getPsDtlsPriceToleranceType())));
            resultDTO.setRevisionDate(item.getPsDetailsRevisionDate());

            resultDTO.setUserID(item.getUsersSid());
            resultDTO.setSessionID(item.getSessionId());

            resultDTO.setCreatedBy(item.getPsDetailsCreatedBy());
            resultDTO.setCreatedDate(item.getPsDetailsCreatedDate());
            resultDTO.setModifiedBy(String.valueOf(item.getPsDetailsModifiedBy()));
            resultDTO.setModifiedDate(item.getPsDetailsModifiedDate());
            resultDTO.setPriceScheduleDetailsSystemId(String.valueOf(item.getPsDetailsSid()));
            resultDTO.setPriceScheduleSystemId(String.valueOf(item.getPsModelSid()));
            resultDTO.setOperation(item.getOperation());

            HelperDTO helper1 = null;
            try {
                helper1 = new HelperDTO(item.getPsDetailsPricetype()==0 ? 0 : item.getPsDetailsPricetype(), getPriceTypeNameByID(String.valueOf(item.getPsDetailsPricetype())));
            } catch (SystemException ex) {
                java.util.logging.Logger.getLogger(PSLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            resultDTO.setPriceType(helper1);
            resultList.add(resultDTO);
        }
        return resultList;
    }

    public int psViewCount() throws SystemException {

        final String psSystemId = String.valueOf(sessionDTO.getSystemId());
        final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsDetails.class);
        try {
            psDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PS_MODEL_SID, Integer.parseInt(psSystemId)));
            psDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
            psDynamicQuery.setProjection(ProjectionFactoryUtil.count(ConstantsUtils.IFP_MODEL_SID));
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return Integer.parseInt(String.valueOf(PsDetailsLocalServiceUtil.dynamicQuery(psDynamicQuery).get(0)));
    }

    /**
     *
     * @param start
     * @param end
     * @param psDetails
     * @return
     * @throws SystemException
     */
    public List<PSIFPDTO> getViewTableResult(int start, int end, String[] psDetails) throws SystemException {
        final String psSystemId = String.valueOf(sessionDTO.getSystemId());
        List<Object[]> result = (List<Object[]>) ImtdPsDetailsLocalServiceUtil.getTempCFPLazyList(psSystemId, null, null, StringUtils.EMPTY, start, end, null, null);
        return getCoustomizedViewDTO(result, psDetails);
    }

    /**
     *
     * @param list
     * @param psDetails
     * @return
     * @throws SystemException
     */
    public static List<PSIFPDTO> getCoustomizedViewDTO(List<Object[]> list, String[] psDetails) throws SystemException {
        List<PSIFPDTO> resultList = new ArrayList<PSIFPDTO>();
        try {
            for (Iterator<Object[]> temp = list.iterator(); temp.hasNext();) {
                Object[] item = temp.next();
                PSIFPDTO resultDTO = new PSIFPDTO();
                int i = 0;
                resultDTO.setItemFamilyplanNo(HelperUtils.getString(psDetails[0]));
                resultDTO.setItemFamilyplanName(HelperUtils.getString(psDetails[1]));

                resultDTO.setItemNo(HelperUtils.getString(item[i++]));
                resultDTO.setItemName(HelperUtils.getString(item[i++]));
                resultDTO.setPrice(HelperUtils.getString(item[i++]));
                String priceType = HelperUtils.getString(item[i++]);
                if (priceType.matches("\\d*")) {
                    final HelperDTO helper = new HelperDTO(
                            Integer.valueOf(StringUtils.EMPTY.equals(priceType) ? ConstantsUtils.ZERO : priceType), getPriceTypeNameByID(priceType));
                    resultDTO.setPriceType(helper);
                }
                resultDTO.setContractPrice(HelperUtils.getString(item[i++]));
                resultDTO.setContractPriceStartDate((Date) (item[i++]));
                resultDTO.setContractPriceEndDate((Date) (item[i++]));
                resultDTO.setPriceTolerance(HelperUtils.getString(item[i++]));
                resultDTO.setPriceProtectionStartDate((Date) (item[i++]));
                resultDTO.setPriceProtectionEndDate((Date) (item[i++]));
                String str = HelperUtils.getString(item[i++]);
                String str1 = HelperUtils.getString(item[i++]);
                resultDTO.setPriceToleranceFrequency(StringUtils.isEmpty(str1) ? helperListUtil.getIdHelperDTOMap().get(0) : helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(str1)));
                resultDTO.setBasePrice(HelperUtils.getString(item[i++]));
                resultDTO.setRevisionDate((Date) (item[i++]));
                resultDTO.setAttachedDate((Date) item[i++]);
                resultList.add(resultDTO);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return resultList;
    }

    /**
     *
     * @param list
     * @param binder
     * @throws PortalException
     * @throws SystemException
     */
    public void saveToTemp(final List<PSIFPDTO> list, final ErrorfulFieldGroup binder) throws PortalException, SystemException {
        for (PSIFPDTO psDTO : list) {
            try {
                if (StringUtils.isEmpty(psDTO.getPrice()) || psDTO.getPrice().equals(ConstantsUtils.NULL)) {
                    psDTO.setPrice(ConstantsUtils.ZERO_DECIMAL);
                }
                if (CommonUtils.isNumeric(psDTO.getPrice().trim())) {
                    binder.getErrorDisplay().setError("Price should be numeric for Item No " + psDTO.getItemNo());
                    return;
                } else if(CommonUtils.isValidlength(psDTO.getPrice().trim())){
                    binder.getErrorDisplay().setError("Price should be less than 15 digits and not more than 2 decimal places for Item No " + psDTO.getItemNo());
                    return;
                }
                if (CommonUtils.isNumeric(psDTO.getContractPrice().trim())) {
                    binder.getErrorDisplay().setError("Contract Price should be numeric  for Item No " + psDTO.getItemNo());
                    return;
                } else if(CommonUtils.isValidlength(psDTO.getContractPrice().trim())){
                    binder.getErrorDisplay().setError("Contract Price should be less than 15 digits and not more than 2 decimal places for Item No " + psDTO.getItemNo());
                    return;
                }
                if (CommonUtils.isNumeric(psDTO.getBasePrice().trim())) {
                    binder.getErrorDisplay().setError("Base Price should be numeric  for Item No " + psDTO.getItemNo());
                    return;
                } else if(CommonUtils.isValidlength(psDTO.getBasePrice().trim())){
                    binder.getErrorDisplay().setError("Base Price should be less than 15 digits and not more than 2 decimal places for Item No " + psDTO.getItemNo());
                    return;
                }
                if (CommonUtils.isNumeric(psDTO.getPriceTolerance())) {
                    binder.getErrorDisplay().setError("Price Tolerance should be numeric  for Item No " + psDTO.getItemNo());
                     psDTO.setPriceTolerance(ConstantsUtils.ZERO_DECIMAL);
                    return;
                } else if(CommonUtils.isValidlength(psDTO.getPriceTolerance().trim())){
                    binder.getErrorDisplay().setError("Price Tolerance should be less than 15 digits and not more than 2 decimal places for Item No " + psDTO.getItemNo());
                    return;
                }
                if (StringUtils.isEmpty(psDTO.getSuggestedPrice()) || psDTO.getSuggestedPrice().equals(ConstantsUtils.NULL)) {
                    psDTO.setSuggestedPrice(ConstantsUtils.ZERO_DECIMAL);
                }
                if (StringUtils.isEmpty(psDTO.getMaxIncrementalChange()) || psDTO.getMaxIncrementalChange().equals(ConstantsUtils.NULL)) {
                    psDTO.setMaxIncrementalChange(ConstantsUtils.ZERO_DECIMAL);
                }
                ImtdPsDetailsLocalServiceUtil.updateImtdPsDetails(getCustomizedTempPSDetails(psDTO));
            } catch (Exception ex) {
                LOGGER.error(ex);
                java.util.logging.Logger.getLogger(PSLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param psDTO
     * @return
     * @throws SystemException
     */
    private static ImtdPsDetails getCustomizedTempPSDetails(PSIFPDTO psDTO) throws SystemException, Exception{
        ImtdPsDetails tempPS=null;
        LOGGER.info("SessionID()..-->>>>"+psDTO.getSessionID());
        LOGGER.info("getUserID().---.-->>>>"+psDTO.getUserID());
        if(psDTO.getTempPsDetailsSystemId()==0){
            tempPS = ImtdPsDetailsLocalServiceUtil.createImtdPsDetails(0);
        }else{
            tempPS = ImtdPsDetailsLocalServiceUtil.getImtdPsDetails(psDTO.getTempPsDetailsSystemId());
        }
        tempPS.setImtdPsDetailsSid(psDTO.getTempPsDetailsSystemId());
            tempPS.setUsersSid(psDTO.getUserID());
            tempPS.setSessionId(psDTO.getSessionID());
            tempPS.setImtdCreatedDate(com.stpl.app.global.company.util.CommonUtils.getDateTime());
            tempPS.setCheckRecord(psDTO.getCheckRecord());
            tempPS.setIfpModelSid(Integer.valueOf(psDTO.getItemFamilyplanSystemId()));
            tempPS.setIfpNo(String.valueOf(psDTO.getItemFamilyplanNo()).trim());
            tempPS.setItemNo(String.valueOf(psDTO.getItemNo()).trim());
            tempPS.setItemName(String.valueOf(psDTO.getItemName()).trim());
            tempPS.setItemId(psDTO.getItemID().trim());
        if (StringUtils.isNotBlank(psDTO.getBrandMasterSID()) && !ConstantsUtils.NULL.equals(psDTO.getBrandMasterSID())
                && !ConstantsUtils.ZERO.equals(psDTO.getBrandMasterSID())) {
            tempPS.setBrandMasterSid(Integer.valueOf(psDTO.getBrandMasterSID()));
        }
            tempPS.setItemMasterSid(Integer.valueOf(psDTO.getItemSystemId()));
             if(StringUtils.isNotBlank(psDTO.getPriceScheduleSystemId()) && !ConstantsUtils.NULL.equals(psDTO.getPriceScheduleSystemId())){
            tempPS.setPsModelSid(Integer.valueOf(psDTO.getPriceScheduleSystemId()));
             }
            if(psDTO.getPriceScheduleDetailsSystemId()!=null && psDTO.getPriceScheduleDetailsSystemId()!=ConstantsUtils.NULL){
            tempPS.setPsDetailsSid(Integer.valueOf(psDTO.getPriceScheduleDetailsSystemId()));
            }
            tempPS.setPsDetailsAttachedDate(psDTO.getAttachedDate());
            if(psDTO.getAttachedStatus()!=null && psDTO.getAttachedStatus()!=ConstantsUtils.NULL){
            tempPS.setPsDetailsAttachedStatus(Integer.valueOf(psDTO.getAttachedStatus()));
            }
            tempPS.setPsDetailsBasePrice(Double.valueOf(psDTO.getBasePrice()));
            tempPS.setPsDetailsContractPrice(Double.valueOf(psDTO.getContractPrice()));
            tempPS.setPsDtlsContPriceEnddate(psDTO.getContractPriceEndDate());
            tempPS.setPsDtlsContPriceStartdate(psDTO.getContractPriceStartDate());
            tempPS.setPsDetailsPrice(Double.valueOf(psDTO.getPrice()));

            tempPS.setPsDetailsPricPrtcnEddate(psDTO.getPriceProtectionEndDate());
            tempPS.setPsDetailsPricPrtcnStdate(psDTO.getPriceProtectionStartDate());

            tempPS.setPsDetailsPriceTolerance(StringUtils.isEmpty(psDTO.getPriceTolerance()) ? 0.00 : Double.valueOf(psDTO.getPriceTolerance()));
            tempPS.setPsDtlsPriceToleranceFreq(psDTO.getPriceToleranceFrequency()==null? 0:psDTO.getPriceToleranceFrequency().getId());
            tempPS.setPsDtlsPriceToleranceIntrvl(psDTO.getPriceToleranceInterval()==null?0:psDTO.getPriceToleranceInterval().getId());
                
            tempPS.setPsDtlsPriceToleranceType(psDTO.getPriceToleranceType()==null?0:psDTO.getPriceToleranceType().getId());
            tempPS.setPsDetailsPricetype(psDTO.getPriceType()==null?0:psDTO.getPriceType().getId());    

            tempPS.setPsDetailsCreatedBy(psDTO.getCreatedBy());
            tempPS.setPsDetailsCreatedDate(psDTO.getCreatedDate());
            tempPS.setPsDetailsModifiedBy(psDTO.getModifiedBy() != null && !ConstantsUtils.NULL.equals(psDTO.getModifiedBy())? Integer.parseInt(psDTO.getModifiedBy()) : 0);
            if(psDTO.getCreatedDate()!= null){
            tempPS.setPsDetailsModifiedDate(psDTO.getCreatedDate());
            }else{
                tempPS.setPsDetailsModifiedDate(new Date());
            }
            tempPS.setPsDetailsRevisionDate(psDTO.getRevisionDate());
            tempPS.setOperation(psDTO.getOperation());
            tempPS.setCreatedDate(com.stpl.app.global.company.util.CommonUtils.getDateTime());
            tempPS.setModifiedDate(com.stpl.app.global.company.util.CommonUtils.getDateTime());
            tempPS.setPriceProtectionStatus(psDTO.getPriceProtectionStatus()==null?0:psDTO.getPriceProtectionStatus().getId());
            tempPS.setPriceProtectionPriceType(psDTO.getPriceProtectionPriceType()==null?0:psDTO.getPriceProtectionPriceType().getId());
            tempPS.setNep(Double.valueOf(StringUtils.isNotBlank(psDTO.getNep()) ? psDTO.getNep() : ConstantsUtils.ZERO));
            tempPS.setNepFormula(psDTO.getNepFormulaSid());
            if(psDTO.getItemStatus() != null ){
            tempPS.setStatus(psDTO.getItemStatus()==null?0:psDTO.getItemStatus().getId());
            }else{
            tempPS.setStatus(0);
            }
//          
            tempPS.setMaxIncrementalChange(Double.valueOf(StringUtils.isNotBlank(psDTO.getMaxIncrementalChange()) 
                    && !ConstantsUtils.NULL.equalsIgnoreCase(psDTO.getMaxIncrementalChange()) ? psDTO.getMaxIncrementalChange() :  ConstantsUtils.ZERO));
            tempPS.setResetEligible(psDTO.getResetEligible()==null?0:psDTO.getResetEligible().getId());
            tempPS.setResetType(psDTO.getResetType()==null?0:psDTO.getResetType().getId());
            tempPS.setResetDate(psDTO.getResetDate());
            tempPS.setResetInterval(psDTO.getResetInterval()==null?0:psDTO.getResetInterval().getId());
            tempPS.setResetFrequency(psDTO.getResetFrequency()==null?0:psDTO.getResetFrequency().getId());
            tempPS.setNetPriceType(psDTO.getNetPriceType()==null?0: psDTO.getNetPriceType().getId());
            
            tempPS.setPsDetailsSuggestedPrice(Double.valueOf(psDTO.getSuggestedPrice()));
            tempPS.setBasePriceType(psDTO.getBasePriceType()==null?0:psDTO.getBasePriceType().getId());
            tempPS.setNetPriceTypeFormula(psDTO.getNetPriceTypeFormula());
//           
            if(Constants.MANUAL.equalsIgnoreCase(psDTO.getBasePriceType().getDescription())){
            tempPS.setBasePriceEntry(Double.valueOf(psDTO.getBasePriceEntry()));
            }
          else {
            tempPS.setBasePriceEntry(0);
             }
            tempPS.setBasePriceDate(psDTO.getBasePriceDate());
            tempPS.setNetBasePrice(psDTO.getNetBasePrice()!=null?psDTO.getNetBasePrice().getId():0);
            tempPS.setBasePriceDdlb(psDTO.getBasePriceItemPriceType()!=null?psDTO.getBasePriceItemPriceType().getId():0);
            tempPS.setSubsequentPeriodPriceType(psDTO.getSubsequentPeriodPriceType()==null ?0:psDTO.getSubsequentPeriodPriceType().getId());
            tempPS.setNetSubsequentPeriodPrice(psDTO.getNetSubsequentPeriodPrice()!=null?psDTO.getNetSubsequentPeriodPrice().getId():0);
            tempPS.setNetSubsequentPriceFormulaId(psDTO.getNetSubsequentPriceFormulaId());
            tempPS.setNetSubsequentPriceFormulaName(psDTO.getNetSubsequentPriceFormulaName());            
            tempPS.setResetPriceType(psDTO.getResetPriceType()==null ?0:psDTO.getResetPriceType().getId());
            tempPS.setNetResetPriceType(psDTO.getNetResetPriceType()!=null?psDTO.getNetResetPriceType().getId():0);
            tempPS.setNetResetPriceFormulaId(psDTO.getNetResetPriceFormulaId());
            tempPS.setNetResetPriceFormulaName(psDTO.getNetResetPriceFormulaName());
            tempPS.setNetBasePriceFormulaId(psDTO.getNetBasePriceFormulaId());
            tempPS.setNetBasePriceFormulaName(psDTO.getNetBasePriceFormulaName());
            
        return tempPS;
    }

    /**
     *
     * @param userId
     * @param sessionId
     * @param createdDate
     * @param populateField
     * @param PopulateValue
     * @param populateAll
     */
    public void populateLogic(Object... receivedObj) {
        List convertedList = Arrays.asList(receivedObj);
        final String userId = (String) convertedList.get(0);
        final String sessionId = (String) convertedList.get(1);
        final String createdDate = (String) convertedList.get(2);
        final String populateField = (String) convertedList.get(3);
        final String populateValue = (String) convertedList.get(4);
        final Boolean populateAll = (Boolean) convertedList.get(5);
        final String mode = (String) convertedList.get(6);
        final int psMasterId = (Integer) convertedList.get(7);
        final String operation = (String) convertedList.get(8);
        if (populateAll) {
            ImtdPsDetailsLocalServiceUtil.updateForPopulateAll(userId, sessionId, createdDate, operation, populateField, populateValue, mode, psMasterId);
        } else {
            ImtdPsDetailsLocalServiceUtil.updateForPopulate(userId, sessionId, createdDate, null, populateField, populateValue, mode, psMasterId);
        }
    }

    /**
     *
     * @param type
     * @return
     */
    public Boolean saveValidation(String type) {
        Boolean retFlag;
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        Integer result = (Integer) ImtdPsDetailsLocalServiceUtil.validateTempPSDeatils(userId, sessionId, null, type, null, null, null, null);
        if ("AtleastOne".equals(type)) {
            retFlag = result > 0;
        } else {
            retFlag = result <= 0;
        }
        return retFlag;
    }

    /**
     *
     * @param userId
     * @param sessionId
     * @param createdDate
     * @param massField
     * @param value
     * @param date
     * @throws PortalException
     * @throws SystemException
     */
    public void populateCheckedRecords(String userId, String sessionId, String createdDate, Object massField, Object value, Date date) throws PortalException, SystemException {

        ImtdPsDetailsLocalServiceUtil.updateForPopulate(userId, sessionId, createdDate, null, massField, value, CommonUtils.getDateTime("yyyy/MM/dd", date), null);
    }

    /**
     *
     * @param userId
     * @param sessionId
     * @param createdDate
     * @param massField
     * @param value
     * @param date
     * @throws PortalException
     * @throws SystemException
     */
    public void populateAll(String userId, String sessionId, String createdDate, Object massField, Object value, Date date) throws PortalException, SystemException {

        ImtdPsDetailsLocalServiceUtil.updateForPopulateAll(userId, sessionId, createdDate, null, massField, value, CommonUtils.getDateTime("yyyy/MM/dd", date), null);
    }

    /**
     *
     * @param userId
     * @param sessionId
     * @param tempCreatedDate
     * @param process
     * @return
     * @throws SystemException
     */
    public List<Object> validateNull(String userId, String sessionId, String tempCreatedDate, String process) throws SystemException {
        return (List<Object>) ImtdPsDetailsLocalServiceUtil.validateTempPSDeatils(userId, sessionId, tempCreatedDate, process, null, null, null, null);
    }

    /**
     *
     * @param priceScheduleSystemId
     */
    public void addToTempPSDetailsEdit(int priceScheduleSystemId) {
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        String tempCreatedDate = String.valueOf(sessionDTO.getSessionDate());
        ImtdPsDetailsLocalServiceUtil.insertTempPsDetailsInEdit(userId, sessionId, tempCreatedDate, String.valueOf(priceScheduleSystemId), null, null, null, null);
    }

    /**
     * Remove all the records from the Temp Table.
     *
     * @param itemIds
     * @param removeAllFlag
     * @throws SystemException
     */
    public void removeAllFromTempTable(Boolean removeAllFlag) throws SystemException, PortalException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        String tempCreatedDate = String.valueOf(sessionDTO.getSessionDate());
        ImtdPsDetailsLocalServiceUtil.deleteAll(userId, sessionId, tempCreatedDate, null, "Delete", null, null, null);
    }

    public void softDeleteAllFromTempTable() throws SystemException, PortalException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        String tempCreatedDate = String.valueOf(sessionDTO.getSessionDate());
        final int systemId = (Integer) sessionDTO.getSystemId();
        ImtdPsDetailsLocalServiceUtil.deleteAll(userId, sessionId, tempCreatedDate, null, "SoftDelete", systemId, null, null);
    }

    public static String getdropDownValues(int valueId) {
        String description = StringUtils.EMPTY;
        try {
            HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(valueId);
            description = helperTable.getDescription();
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return description;
    }
    
    private static String getPriceToleranceType(String priceToleranceType, String listName) {
        String priceToleranceTypeId = StringUtils.EMPTY;
        try {
            final DynamicQuery query = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            query.add(RestrictionsFactoryUtil.eq(ConstantsUtils.LIST_NAME, listName));
            query.add(RestrictionsFactoryUtil.eq(ConstantsUtils.DESCRIPTION, priceToleranceType));
            ProjectionList projectonList = ProjectionFactoryUtil.projectionList();
            projectonList.add(ProjectionFactoryUtil.property(ConstantsUtils.HELPER_TABLE_SID));
            query.setProjection(ProjectionFactoryUtil.distinct(projectonList));
            List<Integer> listInt = HelperTableLocalServiceUtil.dynamicQuery(query);
            if (!listInt.isEmpty()) {
                for (int i = 0; i < listInt.size(); i++) {

                    priceToleranceTypeId = String.valueOf(listInt.get(0));
                }

            }
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return priceToleranceTypeId;
    }

    public static String loadDescription(int rsStatus) {
        String descValue = StringUtils.EMPTY;
        try {
            HelperTable description = HelperTableLocalServiceUtil.getHelperTable(rsStatus);
            descValue = description.getDescription();

        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(PSLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(PSLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return descValue;
    }

	/**
	 * Save ps.
	 *
	 * @param psForm
	 *            the ps form
	 * @param psList
	 *            the ps list
	 * @return the string
	 */
	public String savePS(final ErrorfulFieldGroup psForm,
			final List<NotesDTO> availableUploadedInformation,
			final String addedNotes,final List<NotesDTO> removedDetailsList) throws SystemException, PortalException,
			Exception {
		LOGGER.debug("Entering savePS ");
		String returnString = null;
		PsModel item;

		try {
			String systemId;

			if (psForm.getField(ConstantsUtils.PRICE_SCHEDULE_SYS_ID)
					.getValue() == null
					|| psForm.getField(ConstantsUtils.PRICE_SCHEDULE_SYS_ID)
							.getValue().equals(ConstantsUtils.NULL)) {
				systemId = StringUtils.EMPTY;
			} else {
				systemId = String.valueOf(psForm.getField(
						ConstantsUtils.PRICE_SCHEDULE_SYS_ID).getValue());
			}
			systemId = systemId.replace(",", StringUtils.EMPTY);

			LOGGER.debug("priceScheduleSystemId="
					+ psForm.getField(ConstantsUtils.PRICE_SCHEDULE_SYS_ID)
							.getValue());
			if (ConstantsUtils.NULL.equals(systemId)
					|| ConstantsUtils.ZERO.equals(systemId)) {
                                item = PsModelLocalServiceUtil.createPsModel(0);
				item.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
			} else {
				LOGGER.debug(" getPriceScheduleMasterBySystemId " + systemId
						+ ConstantsUtils.CLOSE_PARENTHESIS);
				item = DAO.getPriceScheduleMasterBySystemId(Integer
						.parseInt(systemId));
				item.setInboundStatus(ConstantsUtils.INBOUND_STATUS_C);

			}
			item.setPsId(String.valueOf(
					psForm.getField(Constants.PS_ID).getValue()).trim());
			item.setPsNo(String.valueOf(
					psForm.getField(Constants.PS_NO).getValue()).trim());
			item.setPsName(String.valueOf(
					psForm.getField(Constants.PS_NAME).getValue()).trim());
			item.setPsStatus(((HelperDTO)psForm.getField(
					Constants.PS_STATUS).getValue()).getId());
			if (psForm.getField(Constants.PS_TYPE).getValue() != null) {
				item.setPsType(((HelperDTO)psForm.getField(
					Constants.PS_TYPE).getValue()).getId());
			}else{
                            item.setPsType(0);
                        }
			item.setPsDesignation((HelperDTO)psForm.getField(
					ConstantsUtils.PS_DESIGNATION).getValue()==null ? ConstantsUtils.ZERO :String.valueOf(((HelperDTO)psForm.getField(
					ConstantsUtils.PS_DESIGNATION).getValue()).getId()));
			item.setPsCategory((HelperDTO)psForm.getField(
					ConstantsUtils.PS_CATEGORY).getValue()==null ? 0 : ((HelperDTO)psForm.getField(
					ConstantsUtils.PS_CATEGORY).getValue()).getId());
			item.setPsStartDate((Date) psForm
					.getField(ConstantsUtils.PS_START_DATE).getValue());
			item.setPsEndDate((Date) psForm.getField(ConstantsUtils.PS_END_DATE)
					.getValue());
                        item.setPsTradeClass((HelperDTO)psForm.getField(
					ConstantsUtils.TRADE_CLASS).getValue()==null ? 0 : ((HelperDTO)psForm.getField(
					ConstantsUtils.TRADE_CLASS).getValue()).getId());
			item.setParentPsName(String.valueOf(
					psForm.getField(ConstantsUtils.PARENT_SCHEDULE_NAME).getValue())
					.trim());
			item.setInternalNotes(addedNotes);
			item.setRecordLockStatus(false);
			if (psForm.getField(ConstantsUtils.PARENT_PS_ID).getValue() != null) {
				item.setParentPsId(String.valueOf(
						psForm.getField(ConstantsUtils.PARENT_PS_ID).getValue())
						.trim());
			}

			if (ConstantsUtils.NULL.equals(systemId)|| ConstantsUtils.ZERO.equals(systemId)) {
				final int user = Integer.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString());
				item.setCreatedDate(new Date());
				item.setCreatedBy(user);
				item.setModifiedBy(user);
				item.setModifiedDate(com.stpl.app.global.company.util.CommonUtils.getDateTime());
                                item.setSource(ConstantsUtils.GTN);
				final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsModel.class);

				psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.PS_ID_SEARCH, item.getPsId()));
				psDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS,ConstantsUtils.INBOUND_STATUS_D));
				final List<PsModel> list = DAO.getPriceScheduleMasterList(psDynamicQuery);
				
				LOGGER.debug("PriceScheduleMaster size -" + ((list == null) ? list : list.size()));
				if (list.size() < CommonUtils.ONE) {

				final DynamicQuery query = DynamicQueryFactoryUtil.forClass(PsModel.class);

					query.add(RestrictionsFactoryUtil.eq(Constants.PS_ID_SEARCH, item.getPsId()));
					query.add(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS,ConstantsUtils.INBOUND_STATUS_D));
					final List<PsModel> dList = DAO.getPriceScheduleMasterList(query);

					if (dList.size() < CommonUtils.ONE) {

						final PsModel result = DAO.savePriceScheduleMaster(item);
						
						sessionDTO.setSystemId(result.getPsModelSid());
						LOGGER.debug("received PriceScheduleId"+ result.getPsId());
						
						if (result.getPsStatus() != 0) {
							UDCIncrementCheck.increment(result.getPsStatus(),
									UIUtils.PS_STATUS);
						}
						if (result.getPsType() != 0) {
							UDCIncrementCheck.increment(result.getPsType(),
									UIUtils.PS_TYPE);
						}

						if (result.getPsDesignation() != null) {
							UDCIncrementCheck.increment(
									result.getPsDesignation(),
									UIUtils.PS_DESIGNATION);
						}

						if (result.getPsCategory() != 0) {
							UDCIncrementCheck.increment(result.getPsCategory(),
									UIUtils.PS_CATEGORY);
						}
						saveDetailsList(result, "save");
						rsLogic.saveUploadedInformation(
								availableUploadedInformation, "PS_MODEL",
								result.getPsModelSid());
                                                returnString = ConstantsUtils.SUCCESS;
						
					} else {
							
						PsModel model =DAO.getPriceScheduleMasterBySystemId(dList.get(0).getPsModelSid());
						item.setPsModelSid(model.getPsModelSid());
						item.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
                                                
//                                                model.setPsId(item.getPsId());
                                                model.setPsNo(item.getPsNo());
                                                model.setPsName(item.getPsName());
                                                model.setPsStatus(item.getPsStatus());
                                                model.setPsType(item.getPsType());
                                                model.setPsDesignation(item.getPsDesignation());
                                                model.setPsCategory(item.getPsCategory());
                                                model.setPsStartDate(item.getPsStartDate());
                                                model.setPsEndDate(item.getPsEndDate());
                                                model.setPsTradeClass(item.getPsTradeClass());
                                                model.setParentPsName(item.getParentPsName());
                                                model.setInternalNotes(item.getInternalNotes());
                                                model.setRecordLockStatus(item.getRecordLockStatus());
                                                model.setParentPsId(item.getParentPsId());
                                                model.setCreatedDate(item.getCreatedDate());
                                                model.setCreatedBy(item.getCreatedBy());
                                                model.setModifiedBy(item.getModifiedBy());
                                                model.setModifiedDate(item.getModifiedDate());
                                                model.setSource(item.getSource());
                                                model.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
			        
						final PsModel result = DAO.updatePriceScheduleMaster(model);
						sessionDTO.setSystemId(result.getPsModelSid());
						LOGGER.debug("D received PriceScheduleId"+ result.getPsId());
						
						if (result.getPsStatus() != 0) {
							UDCIncrementCheck.increment(result.getPsStatus(),
									UIUtils.PS_STATUS);
						}
						if (result.getPsType() != 0) {
							UDCIncrementCheck.increment(result.getPsType(),
									UIUtils.PS_TYPE);
						}

						if (result.getPsDesignation() != null) {
							UDCIncrementCheck.increment(
									result.getPsDesignation(),
									UIUtils.PS_DESIGNATION);
						}

						if (result.getPsCategory() != 0) {
							UDCIncrementCheck.increment(result.getPsCategory(),
									UIUtils.PS_CATEGORY);
						}
						saveDetailsList(result, "soft");
						rsLogic.saveUploadedInformation(
								availableUploadedInformation, "PS_MODEL",
								result.getPsModelSid());
                                                returnString = ConstantsUtils.SUCCESS;
					}
				} else {
					returnString = "duplicate";
					return returnString;
				}
			} else {
				final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil
						.forClass(PsModel.class);
				psDynamicQuery.add(RestrictionsFactoryUtil.eq(
						Constants.PS_ID_Column, item.getPsId()));
				@SuppressWarnings("unchecked")
				final List<PsModel> priceScheduleList = DAO
						.getPriceScheduleMasterList(psDynamicQuery);
				LOGGER.debug("PriceScheduleMaster size -"
						+ ((priceScheduleList == null) ? priceScheduleList
								: priceScheduleList.size()));
				int count = 0;
				for (int i = 0; i < priceScheduleList.size(); i++) {
					if (Integer.parseInt(systemId.replace(",", StringUtils.EMPTY)) == priceScheduleList
							.get(i).getPsModelSid()) {
					} else {
						count++;
					}
				}
				if (count < CommonUtils.ONE) {
					item.setModifiedDate(new Date());
					item.setModifiedBy(Integer.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
					LOGGER.debug("In Item PriceScheduleSystemId "
							+ item.getPsModelSid());
					final PsModel check = DAO
							.getPriceScheduleMasterBySystemId(item
									.getPsModelSid());
					LOGGER.debug("Checking PriceScheduleSystemId "
							+ check.getPsId());

					if (check.getPsStatus() != 0) {
						UDCIncrementCheck.decrement(check.getPsStatus(),
								UIUtils.PS_STATUS);
					}
					if (check.getPsType() != 0) {
						UDCIncrementCheck.decrement(check.getPsType(),
								UIUtils.PS_TYPE);
					}
					if (check.getPsDesignation() != null) {
						UDCIncrementCheck.decrement(check.getPsDesignation(),
								UIUtils.PS_DESIGNATION);
					}
					if (check.getPsCategory() != 0) {
						UDCIncrementCheck.decrement(check.getPsCategory(),
								UIUtils.PS_CATEGORY);
					}
					LOGGER.debug("UDC check PriceScheduleSystemId "
							+ item.getPsModelSid());
					final PsModel result = DAO.updatePriceScheduleMaster(item);
					LOGGER.debug("Result checking PriceScheduleSystemId "
							+ result.getPsId());
					if (result.getPsStatus() != 0) {
						UDCIncrementCheck.increment(result.getPsStatus(),
								UIUtils.PS_STATUS);
					}
					if (result.getPsType() != 0) {
						UDCIncrementCheck.increment(result.getPsType(),
								UIUtils.PS_TYPE);
					}
					if (result.getPsDesignation() != null) {
						UDCIncrementCheck.increment(result.getPsDesignation(),
								UIUtils.PS_DESIGNATION);
					}
					if (result.getPsCategory() != 0) {
						UDCIncrementCheck.increment(result.getPsCategory(),
								UIUtils.PS_CATEGORY);
					}

					saveDetailsList(result, "update");
                                        if (!removedDetailsList.isEmpty()) {
                                        
                                            for (int i = 0; i < removedDetailsList.size(); i++) {
                                                NotesDTO dtoValue = removedDetailsList.get(i);
                                          
                                                if (dtoValue.getDocDetailsId() != 0) {

                                                    rsLogic.deleteUploadedFile(dtoValue.getDocDetailsId(), StringUtils.EMPTY, dtoValue.getDocumentFullPath());
                                                }
                                            }
                                        }
					rsLogic.saveUploadedInformation(
							availableUploadedInformation, "PS_MODEL",
							result.getPsModelSid());
                                        returnString = ConstantsUtils.SUCCESS;
				} else {
					LOGGER.debug(" returns  duplicate");
					returnString = "duplicate";
				}
			}

			LOGGER.debug(" returns  success");

		} catch (Exception e) {
			LOGGER.error(e);
		}
		LOGGER.debug("return -" + returnString);
		return returnString;
	}
        
       
     public void deleteNotesTabAttachment(final int systemId){
        try {
            final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MasterDataFiles.class);
            dynamicQuery.add(RestrictionsFactoryUtil.eq("masterTableSid", systemId));
            dynamicQuery.add(RestrictionsFactoryUtil.like("masterTableName", "PS_MODEL"));
            final List<MasterDataFiles> masterDataFiles = MasterDataFilesLocalServiceUtil.dynamicQuery(dynamicQuery);
            if (!masterDataFiles.isEmpty()) {
                for (MasterDataFiles masterDataFile : masterDataFiles) {
                        MasterDataFilesLocalServiceUtil.deleteMasterDataFiles(masterDataFile.getMasterDataFilesSid());
                }
            }
        } catch (SystemException e) {
            LOGGER.error(e);
        } catch (PortalException e) {
            LOGGER.error(e);
        }
    }
     
     public int getSearchPsCountParentLookup(final ErrorfulFieldGroup searchItemForm,final BeanSearchCriteria search)
            {

        LOGGER.info(" getCustomizedIfpSearchFormFromModel(ErrorfulFieldGroup searchItemForm)");
       SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String psId;
        String psNo;
        String psName;
        String itemId;
        String itemNo;
        String itemName;
        int count=0;
        int psTypeID = 0;

        int psStatusID = 0;

       
        LOGGER.info("priceScheduleId=" + searchItemForm.getField(ConstantsUtils.PS_ID).getValue());
        if (searchItemForm.getField(Constants.PS_ID).getValue() == null || searchItemForm.getField(Constants.PS_ID).getValue().toString() == StringUtils.EMPTY) {
            psId = StringUtils.EMPTY;
        } else {
            psId = searchItemForm.getField(Constants.PS_ID).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(Constants.PS_NO).getValue() == null) {
            psNo = StringUtils.EMPTY;
        } else {
            psNo = searchItemForm.getField(Constants.PS_NO).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(Constants.PS_NAME).getValue() == null) {
            psName = StringUtils.EMPTY;
        } else {
            psName = searchItemForm.getField(Constants.PS_NAME).getValue()
                    .toString().trim();
        }
        
        if (searchItemForm.getField(ConstantsUtils.ITEM_ID).getValue() == null) {
            itemId = StringUtils.EMPTY;
        } else {
            itemId = searchItemForm.getField(ConstantsUtils.ITEM_ID).getValue()
                    .toString().trim();
        }
        
        if (searchItemForm.getField(ConstantsUtils.ITEM_NO).getValue() == null) {
            itemNo = StringUtils.EMPTY;
        } else {
            itemNo = searchItemForm.getField(ConstantsUtils.ITEM_NO).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantsUtils.ITEM_NAME).getValue() == null) {
            itemName = StringUtils.EMPTY;
        } else {
            itemName = searchItemForm.getField(ConstantsUtils.ITEM_NAME).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(Constants.PS_TYPE).getValue() == null) {
            psTypeID = 0;
        } else {
            psTypeID = ((HelperDTO) searchItemForm.getField(Constants.PS_TYPE).getValue()).getId();
        }
        if (searchItemForm.getField(Constants.PS_STATUS).getValue() == null) {
            psStatusID = 0;
        } else {
            psStatusID = ((HelperDTO) searchItemForm.getField(Constants.PS_STATUS).getValue()).getId();
        }
        
        
        
         if (StringUtils.isNotBlank(psId)) {
            psId = psId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(psNo)) {
            psNo = psNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(psName)) {
            psName = psName.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        
        if (StringUtils.isNotBlank(itemId)) {
            itemId = itemId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        
        if (StringUtils.isNotBlank(itemNo)) {
            itemNo = itemNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        
        if (StringUtils.isNotBlank(itemName)) {
            itemName = itemName.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        
        
        
        Map<String,Object> filterMap = new HashMap<String,Object>();
        
        if (search != null && search.getFilters() != null) {
                for (Container.Filter filter : search.getFilters()) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;
                        filterMap.put(String.valueOf(stringFilter.getPropertyId()),filterString);
                        
                    } else if (filter instanceof Between) {
                        Between stringFilter = (Between) filter;
                        Date filterString = (Date) stringFilter.getStartValue();
                        Date filterString1 = (Date) stringFilter.getEndValue();
                        filterMap.put(String.valueOf(stringFilter.getPropertyId())+ConstantsUtils.START,format.format(filterString));
                        filterMap.put(String.valueOf(stringFilter.getPropertyId())+ConstantsUtils.END,format.format(filterString1));
                     } else if(filter instanceof Compare) {
                        Compare stringFilter = (Compare)filter;
                        if(stringFilter.getValue()instanceof Integer) {
                        
                        if(stringFilter.getOperation().equals(stringFilter.getOperation().EQUAL)) {
                            if (((Integer) stringFilter.getValue()) == 0) {
                               
                            } else {
                            int value = (Integer)stringFilter.getValue();
                             filterMap.put(String.valueOf(stringFilter.getPropertyId()),value);
                           
                            }
                        }                          
                        }
                    }
                }
        }        
        List psSearchList = PsDetailsLocalServiceUtil.getPsSearchList(psId, psNo, psName, psStatusID, psTypeID, itemId, itemNo, itemName, filterMap, 0, 0, null, null, true);
            if(psSearchList!=null){
               return psSearchList.size();
            }else{
                return 0;
            }
         
    }
    
    
    public List<SearchPriceScheduleDTO> getSearchPsListParentLookup(final ErrorfulFieldGroup searchItemForm,int start,int end, final List<OrderByColumn> orderByColumns,final BeanSearchCriteria search)
            {

        LOGGER.info(" getCustomizedIfpSearchFormFromModel(ErrorfulFieldGroup searchItemForm)");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String psId;
        String psNo;
        String psName;
        String itemId;
        String itemNo;
        String itemName;
        int count=0;
        int psTypeID = 0;
        int psStatusID = 0;

       
        LOGGER.info("priceScheduleId=" + searchItemForm.getField(ConstantsUtils.PS_ID).getValue());
        if (searchItemForm.getField(Constants.PS_ID).getValue() == null || searchItemForm.getField(Constants.PS_ID).getValue().toString() == StringUtils.EMPTY) {
            psId = StringUtils.EMPTY;
        } else {
            psId = searchItemForm.getField(Constants.PS_ID).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(Constants.PS_NO).getValue() == null) {
            psNo = StringUtils.EMPTY;
        } else {
            psNo = searchItemForm.getField(Constants.PS_NO).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(Constants.PS_NAME).getValue() == null) {
            psName = StringUtils.EMPTY;
        } else {
            psName = searchItemForm.getField(Constants.PS_NAME).getValue()
                    .toString().trim();
        }
        
        if (searchItemForm.getField(ConstantsUtils.ITEM_ID).getValue() == null) {
            itemId = StringUtils.EMPTY;
        } else {
            itemId = searchItemForm.getField(ConstantsUtils.ITEM_ID).getValue()
                    .toString().trim();
        }
        
        if (searchItemForm.getField(ConstantsUtils.ITEM_NO).getValue() == null) {
            itemNo = StringUtils.EMPTY;
        } else {
            itemNo = searchItemForm.getField(ConstantsUtils.ITEM_NO).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantsUtils.ITEM_NAME).getValue() == null) {
            itemName = StringUtils.EMPTY;
        } else {
            itemName = searchItemForm.getField(ConstantsUtils.ITEM_NAME).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(Constants.PS_TYPE).getValue() == null) {
            psTypeID = 0;
        } else {
            psTypeID = ((HelperDTO) searchItemForm.getField(Constants.PS_TYPE).getValue()).getId();
        }
        if (searchItemForm.getField(Constants.PS_STATUS).getValue() == null) {
            psStatusID = 0;
        } else {
            psStatusID = ((HelperDTO) searchItemForm.getField(Constants.PS_STATUS).getValue()).getId();
        }
        
        
        
         if (StringUtils.isNotBlank(psId)) {
            psId = psId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(psNo)) {
            psNo = psNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(psName)) {
            psName = psName.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        
        if (StringUtils.isNotBlank(itemId)) {
            itemId = itemId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        
        if (StringUtils.isNotBlank(itemNo)) {
            itemNo = itemNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        
        if (StringUtils.isNotBlank(itemName)) {
            itemName = itemName.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        
        
        
        Map<String,Object> filterMap = new HashMap<String,Object>();
        
        if (search != null && search.getFilters() != null) {
                for (Container.Filter filter : search.getFilters()) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;
                        filterMap.put(String.valueOf(stringFilter.getPropertyId()),filterString);
                        
                    } else if (filter instanceof Between) {
                        Between stringFilter = (Between) filter;
                        Date filterString = (Date) stringFilter.getStartValue();
                        Date filterString1 = (Date) stringFilter.getEndValue();
                        filterMap.put(String.valueOf(stringFilter.getPropertyId())+ConstantsUtils.START,format.format(filterString));
                        filterMap.put(String.valueOf(stringFilter.getPropertyId())+ConstantsUtils.END,format.format(filterString1));
                     } else if(filter instanceof Compare) {
                        Compare stringFilter = (Compare)filter;
                        if(stringFilter.getValue()instanceof Integer) {
                        
                        if(stringFilter.getOperation().equals(stringFilter.getOperation().EQUAL)) {
                            if (((Integer) stringFilter.getValue()) == 0) {
                               
                            } else {
                            int value = (Integer)stringFilter.getValue();
                             filterMap.put(String.valueOf(stringFilter.getPropertyId()),value);
                           
                            }
                        }                          
                        }
                    }
                }
        }
        
        String columnName = "ps.PS_MODEL_SID";
        String orderBy = "ASC";
        for (final Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator
                .hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
            
                        if(ConstantsUtils.PS_SID.equals(orderByColumn.getName())){
                            columnName="ps.PS_MODEL_SID";
                        }else if(ConstantsUtils.PS_ID.equals(orderByColumn.getName())){
                            columnName="ps.PS_ID";
                        }else if(ConstantsUtils.PS_NO.equals(orderByColumn.getName())){
                            columnName="ps.PS_NO";
                        }else if(ConstantsUtils.PS_NAME.equals(orderByColumn.getName())){
                            columnName="ps.PS_NAME";
                        }else if(ConstantsUtils.PS_STATUS.equals(orderByColumn.getName())){
                            columnName="status";
                        }else if(ConstantsUtils.PS_TYPE.equals(orderByColumn.getName())){
                            columnName="type";
                        }else if(ConstantsUtils.PS_CATEGORY.equals(orderByColumn.getName())){
                            columnName="category";
                        }else if(ConstantsUtils.PS_START_DATE.equals(orderByColumn.getName())){
                            columnName="ps.PS_START_DATE";
                        }else if(ConstantsUtils.PS_END_DATE.equals(orderByColumn.getName())){
                            columnName="ps.PS_END_DATE";
                        }else if(ConstantsUtils.PS_DESIGNATION.equals(orderByColumn.getName())){
                            columnName="designation";
                        }else if("parentId".equals(orderByColumn.getName())){
                            columnName="ps.PARENT_PS_ID";
                        }else if("parentName".equals(orderByColumn.getName())){
                            columnName="ps.PARENT_PS_NAME";
                        }else if(ConstantsUtils.TRADE_CLASS.equals(orderByColumn.getName())){
                            columnName="trade";
                        }
            
            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
               orderBy = "ASC";
            } else {
                orderBy = "DESC";
            }
        }
        
        
        List psSearchList = PsDetailsLocalServiceUtil.getPsSearchList(psId, psNo, psName, psStatusID, psTypeID, itemId, itemNo, itemName, filterMap, start,end, columnName, orderBy, false);
            
        
        return getCustomizedSearchPsListParentLookup(psSearchList);
    }
    
    
    List<SearchPriceScheduleDTO> getCustomizedSearchPsListParentLookup(List list){
        List<SearchPriceScheduleDTO> result = new ArrayList<SearchPriceScheduleDTO>();
        SearchPriceScheduleDTO dto;
        for(int i=0;i<list.size();i++){
            dto= new SearchPriceScheduleDTO();
          Object[] obj = (Object[])list.get(i);
          if(obj[0]!=null)
          {
              dto.setPriceScheduleSystemId(Integer.valueOf(String.valueOf(obj[0])));
              dto.setPsSystemId(String.valueOf(obj[0]));
          }
          
          if(obj[1]!=null){
              dto.setPriceScheduleId(String.valueOf(obj[1]));
          }
            
          if(obj[2]!=null){
              dto.setPriceScheduleNo(String.valueOf(obj[2]));
          }
          if(obj[3]!=null){
              dto.setPriceScheduleName(String.valueOf(obj[3]));
          }
          
          if(obj[7]!=null){
            dto.setPriceScheduleStartDate((Date)obj[7]);
          }
          
          if(obj[8]!=null){
            dto.setPriceScheduleEndDate((Date)obj[8]);
          }
          
          if(obj[10]!=null && StringUtils.isNotBlank(String.valueOf(obj[10]))){
               
                    String psId = String.valueOf(obj[10]);
                   dto.setParentId(psId);
               
          }
          
          if(obj[11]!=null){
            dto.setParentName(String.valueOf(obj[11]));
          }
          
          if(obj[13]!=null && !String.valueOf(obj[13]).equals(ConstantsUtils.SELECT_ONE)){
              dto.setPriceScheduleType(String.valueOf(obj[13]));
          }
          if(obj[14]!=null && !String.valueOf(obj[14]).equals(ConstantsUtils.SELECT_ONE)){
              dto.setPriceScheduleStatus(String.valueOf(obj[14]));
          }
          
          
          if(obj[15]!=null && !String.valueOf(obj[15]).equals(ConstantsUtils.SELECT_ONE)){
              dto.setPriceScheduleCategory(String.valueOf(obj[15]));
          }
          if(obj[16]!=null && !String.valueOf(obj[16]).equals(ConstantsUtils.SELECT_ONE)){
              dto.setPriceScheduleDesignation(String.valueOf(obj[16]));
          }
          if(obj[17]!=null && !String.valueOf(obj[17]).equals(ConstantsUtils.SELECT_ONE)){
              dto.setTradeClass(String.valueOf(obj[17]));
          }
          
          if(obj[18]!=null){
              dto.setRecordLockStatus(String.valueOf(obj[18]));
          }
          
         result.add(dto);
          
        }
        
        return result;
    }

    public int getCountForPS(final ErrorfulFieldGroup searchItemForm, final Set<Container.Filter> filterSet) {

        LOGGER.debug(" getCustomizedIfpSearchFormFromModel(ErrorfulFieldGroup searchItemForm)");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String psId;
        String psNo;
        String psName;
        String itemId;
        String itemNo;
        String itemName;
        int count = 0;
        int psTypeID = 0;

        int psStatusID = 0;

        LOGGER.info("priceScheduleId=");
        if (searchItemForm.getField(ConstantsUtils.TEXT1).getValue() == null || searchItemForm.getField(ConstantsUtils.TEXT1).getValue().toString() == StringUtils.EMPTY) {
            psId = StringUtils.EMPTY;
        } else {
            psId = searchItemForm.getField(ConstantsUtils.TEXT1).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantsUtils.TEXT2).getValue() == null) {
            psNo = StringUtils.EMPTY;
        } else {
            psNo = searchItemForm.getField(ConstantsUtils.TEXT2).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantsUtils.TEXT3).getValue() == null) {
            psName = StringUtils.EMPTY;
        } else {
            psName = searchItemForm.getField(ConstantsUtils.TEXT3).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantsUtils.TEXT6).getValue() == null) {
            itemId = StringUtils.EMPTY;
        } else {
            itemId = searchItemForm.getField(ConstantsUtils.TEXT6).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantsUtils.TEXT7).getValue() == null) {
            itemNo = StringUtils.EMPTY;
        } else {
            itemNo = searchItemForm.getField(ConstantsUtils.TEXT7).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantsUtils.TEXT8).getValue() == null) {
            itemName = StringUtils.EMPTY;
        } else {
            itemName = searchItemForm.getField(ConstantsUtils.TEXT8).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantsUtils.COMBO2).getValue() == null || ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO2).getValue())) {
            psTypeID = 0;
        } else {

            final com.stpl.app.util.HelperDTO helperDTO = (com.stpl.app.util.HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO2).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                psTypeID = (Integer) (helperDTO.getId());
            }
        }
        if (searchItemForm.getField(ConstantsUtils.COMBO1).getValue() == null || ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO1).getValue())) {
            psStatusID = 0;
        } else {

            final com.stpl.app.util.HelperDTO helperDTO = (com.stpl.app.util.HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO1).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                psStatusID = (Integer) (helperDTO.getId());

            }

        }

        if (StringUtils.isNotBlank(psId)) {
            psId = psId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(psNo)) {
            psNo = psNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(psName)) {
            psName = psName.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }

        if (StringUtils.isNotBlank(itemId)) {
            itemId = itemId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }

        if (StringUtils.isNotBlank(itemNo)) {
            itemNo = itemNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }

        if (StringUtils.isNotBlank(itemName)) {
            itemName = itemName.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }

        Map<String, Object> filterMap = new HashMap<String, Object>();

        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;
                    filterMap.put(String.valueOf(stringFilter.getPropertyId()), filterString);

                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    Date filterString = (Date) stringFilter.getStartValue();
                    Date filterString1 = (Date) stringFilter.getEndValue();
                    filterMap.put(String.valueOf(stringFilter.getPropertyId()) + ConstantsUtils.START, format.format(filterString));
                    filterMap.put(String.valueOf(stringFilter.getPropertyId()) + ConstantsUtils.END, format.format(filterString1));
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    if (stringFilter.getValue() instanceof Integer) {

                        if (stringFilter.getOperation().equals(stringFilter.getOperation().EQUAL)) {
                            if (((Integer) stringFilter.getValue()) == 0) {

                            } else {
                                int value = (Integer) stringFilter.getValue();
                                filterMap.put(String.valueOf(stringFilter.getPropertyId()), value);

                            }
                        }
                    }
                }
            }
        }
        getPsSearchList(psId, psNo, psName, psStatusID, psTypeID, itemId, itemNo, itemName, filterMap, 0, 0, null, null, true);
        List psSearchList = PsDetailsLocalServiceUtil.getPsSearchList(psId, psNo, psName, psStatusID, psTypeID, itemId, itemNo, itemName, filterMap, 0, 0, null, null, true);
        if (psSearchList != null) {
            return psSearchList.size();
        } else {
            return 0;
        }

    }

    public List<SearchResultsDTO> getResultsForPS(final ErrorfulFieldGroup searchItemForm, int start, int end, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet) throws Exception {

        LOGGER.debug(" getCustomizedIfpSearchFormFromModel(ErrorfulFieldGroup searchItemForm)");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String psId;
        String psNo;
        String psName;
        String itemId;
        String itemNo;
        String itemName;
        int count = 0;
        int psTypeID = 0;
        int psStatusID = 0;

        if (searchItemForm.getField(ConstantsUtils.TEXT1).getValue() == null || searchItemForm.getField(ConstantsUtils.TEXT1).getValue().toString() == StringUtils.EMPTY) {
            psId = StringUtils.EMPTY;
        } else {
            psId = searchItemForm.getField(ConstantsUtils.TEXT1).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantsUtils.TEXT2).getValue() == null) {
            psNo = StringUtils.EMPTY;
        } else {
            psNo = searchItemForm.getField(ConstantsUtils.TEXT2).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantsUtils.TEXT3).getValue() == null) {
            psName = StringUtils.EMPTY;
        } else {
            psName = searchItemForm.getField(ConstantsUtils.TEXT3).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantsUtils.TEXT6).getValue() == null) {
            itemId = StringUtils.EMPTY;
        } else {
            itemId = searchItemForm.getField(ConstantsUtils.TEXT6).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantsUtils.TEXT7).getValue() == null) {
            itemNo = StringUtils.EMPTY;
        } else {
            itemNo = searchItemForm.getField(ConstantsUtils.TEXT7).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantsUtils.TEXT8).getValue() == null) {
            itemName = StringUtils.EMPTY;
        } else {
            itemName = searchItemForm.getField(ConstantsUtils.TEXT8).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantsUtils.COMBO2).getValue() == null || ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO2).getValue())) {
            psTypeID = 0;
        } else {

            final com.stpl.app.util.HelperDTO helperDTO = (com.stpl.app.util.HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO2).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                psTypeID = (Integer) (helperDTO.getId());
            }
        }
        if (searchItemForm.getField(ConstantsUtils.COMBO1).getValue() == null || ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO1).getValue())) {
            psStatusID = 0;
        } else {

            final com.stpl.app.util.HelperDTO helperDTO = (com.stpl.app.util.HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO1).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                psStatusID = (Integer) (helperDTO.getId());

            }

        }

        if (StringUtils.isNotBlank(psId)) {
            psId = psId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(psNo)) {
            psNo = psNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(psName)) {
            psName = psName.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }

        if (StringUtils.isNotBlank(itemId)) {
            itemId = itemId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }

        if (StringUtils.isNotBlank(itemNo)) {
            itemNo = itemNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }

        if (StringUtils.isNotBlank(itemName)) {
            itemName = itemName.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }

        Map<String, Object> filterMap = new HashMap<String, Object>();

        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;
                    filterMap.put(String.valueOf(stringFilter.getPropertyId()), filterString);

                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    Date filterString = (Date) stringFilter.getStartValue();
                    Date filterString1 = (Date) stringFilter.getEndValue();
                    filterMap.put(String.valueOf(stringFilter.getPropertyId()) + ConstantsUtils.START, format.format(filterString));
                    filterMap.put(String.valueOf(stringFilter.getPropertyId()) + ConstantsUtils.END, format.format(filterString1));
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    if (stringFilter.getValue() instanceof Integer) {

                        if (stringFilter.getOperation().equals(stringFilter.getOperation().EQUAL)) {
                            if (((Integer) stringFilter.getValue()) == 0) {

                            } else {
                                int value = (Integer) stringFilter.getValue();
                                filterMap.put(String.valueOf(stringFilter.getPropertyId()), value);

                            }
                        }
                    }
                    if (stringFilter.getValue() instanceof Date) {
                    Compare.Operation operation = stringFilter.getOperation();
                    Date value = (Date) stringFilter.getValue();
                    if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                        filterMap.put(String.valueOf(stringFilter.getPropertyId()) + ConstantsUtils.START, format.format(value));
                    } else {
                        filterMap.put(String.valueOf(stringFilter.getPropertyId()) + ConstantsUtils.END, format.format(value));
                    }
                    }
                }
            }
        }

        String columnName = "ps.PS_MODEL_SID";
        String orderBy = "ASC";
        for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator
                .hasNext();) {
            final SortByColumn sortByColumn = (SortByColumn) iterator.next();

            if (ConstantsUtils.PS_SID.equals(sortByColumn.getName())) {
                columnName = "ps.PS_MODEL_SID";
            } else if (ConstantsUtils.PS_ID.equals(sortByColumn.getName())) {
                columnName = "ps.PS_ID";
            } else if (ConstantsUtils.PS_NO.equals(sortByColumn.getName())) {
                columnName = "ps.PS_NO";
            } else if (ConstantsUtils.PS_NAME.equals(sortByColumn.getName())) {
                columnName = "ps.PS_NAME";
            } else if (ConstantsUtils.PS_STATUS.equals(sortByColumn.getName())) {
                columnName = "status";
            } else if (ConstantsUtils.PS_TYPE.equals(sortByColumn.getName())) {
                columnName = "type";
            } else if (ConstantsUtils.PS_CATEGORY.equals(sortByColumn.getName())) {
                columnName = "category";
            } else if (ConstantsUtils.PS_START_DATE.equals(sortByColumn.getName())) {
                columnName = "ps.PS_START_DATE";
            } else if (ConstantsUtils.PS_END_DATE.equals(sortByColumn.getName())) {
                columnName = "ps.PS_END_DATE";
            } else if (ConstantsUtils.PS_DESIGNATION.equals(sortByColumn.getName())) {
                columnName = "designation";
            } else if ("parentId".equals(sortByColumn.getName())) {
                columnName = "ps.PARENT_PS_ID";
            } else if ("parentName".equals(sortByColumn.getName())) {
                columnName = "ps.PARENT_PS_NAME";
            } else if (ConstantsUtils.TRADE_CLASS.equals(sortByColumn.getName())) {
                columnName = "trade";
            }

            if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                orderBy = "ASC";
            } else {
                orderBy = "DESC";
            }
        }
        getPsSearchList(psId, psNo, psName, psStatusID, psTypeID, itemId, itemNo, itemName, filterMap, start, end, columnName, orderBy, false);
        List psSearchList = PsDetailsLocalServiceUtil.getPsSearchList(psId, psNo, psName, psStatusID, psTypeID, itemId, itemNo, itemName, filterMap, start, end, columnName, orderBy, false);

        return getCustomizedSearchPsList(psSearchList);
    }

    public void resetPricingTab(boolean isEditMode, String psModelSID) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        String sql ;
        if (isEditMode) {
            sql = CustomSQLUtil.get("resetpricingtabedit");
            sql=sql.replaceFirst(ConstantsUtils.QUESTION_MARK, psModelSID);
        }else{
            sql = CustomSQLUtil.get("resetpricingtab");
        }
        sql=sql.replaceFirst(ConstantsUtils.QUESTION_MARK, userId);
        sql=sql.replaceFirst(ConstantsUtils.QUESTION_MARK, sessionId);
        ImtdPsDetailsLocalServiceUtil.executeUpdateQuery(sql, null, null);
        
    }

    public void resetPriceProtectionTab(boolean isEditMode, String psModelSID) {
        
        String sql;
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        if (isEditMode) {
            sql = CustomSQLUtil.get("resetpriceprotectiontabedit");
            sql=sql.replaceFirst(ConstantsUtils.QUESTION_MARK, psModelSID);
        }else{
            sql = CustomSQLUtil.get("resetpriceprotectiontab");
        }
        
        sql=sql.replaceFirst(ConstantsUtils.QUESTION_MARK, userId);
        sql=sql.replaceFirst(ConstantsUtils.QUESTION_MARK, sessionId);
        ImtdPsDetailsLocalServiceUtil.executeUpdateQuery(sql, null, null);
    }

    public int getExcelCount(boolean isView) {
        int count=0;
        String sql;
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        if (isView) {
            sql = CustomSQLUtil.get("excelcountview");
        }else{
            sql = CustomSQLUtil.get("excelcountedit");
        }
        sql=sql.replaceFirst(ConstantsUtils.QUESTION_MARK, userId);
        sql=sql.replaceFirst(ConstantsUtils.QUESTION_MARK, sessionId);
        List resultList = (List)RsModelLocalServiceUtil.executeSelectQuery(sql, null, null);
        count = (Integer) resultList.get(0);
        return count;
    }
    
    
     public static void sortBy(final String columnName, final boolean asc, final List<PSIFPDTO> searchList) {
        if (asc) {                                     
            if(ConstantsUtils.IFP_STATUS1.equals(columnName)){
                Collections.sort(searchList, PSIFPDTO.psIdAsc);
            } else if(ConstantsUtils.IFP_CATEGORY1.equals(columnName)){
                Collections.sort(searchList, PSIFPDTO.ifpCategoryAsc);
            } else if(ConstantsUtils.IFP_MODEL_TYPE.equals(columnName)){
                Collections.sort(searchList, PSIFPDTO.ifpTypeAsc);
            }
        } else {
            if(ConstantsUtils.IFP_CATEGORY1.equals(columnName)){
                Collections.sort(searchList, PSIFPDTO.psIdDsc);
            } else if(ConstantsUtils.IFP_CATEGORY1.equals(columnName)){
                Collections.sort(searchList, PSIFPDTO.ifpCategoryDsc);
            } else if(ConstantsUtils.IFP_MODEL_TYPE.equals(columnName)){
                Collections.sort(searchList, PSIFPDTO.ifpTypeDsc);
            }
        }
    }   
    public List getPsContractList(int systemId) {
        List psContractList = new ArrayList<>();
        try {
            final DynamicQuery contractDynamicQuery = DynamicQueryFactoryUtil.forClass(PsContract.class);
            contractDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PS_MODEL_SID, systemId));
            contractDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.D));
            psContractList = PsContractLocalServiceUtil.dynamicQuery(contractDynamicQuery);
          } catch (SystemException e) {
            LOGGER.error(e);
        }
        return psContractList;
    }
    public int getNsfCount(final ErrorfulFieldGroup searchFields, final Set<Container.Filter> filterSet) throws Exception {
        int count = 0;
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder = buildSearchQuery(searchFields, true);
        queryBuilder = getFilterQuery(filterSet, queryBuilder);


        List<Object> masterData = (List<Object>) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        if (masterData != null && !masterData.isEmpty()) {
            Object ob = masterData.get(0);
            count += Integer.valueOf(String.valueOf(ob));
        }
        LOGGER.info(" nsfCount count=" + count);
        return count;
    }

    public List<PSNepFormulaDTO> loadNsfResults(
            final ErrorfulFieldGroup searchFields, final int start, final int end, final List<SortByColumn> columns, final Set<Container.Filter> filterSet) throws ParseException {
        List<PSNepFormulaDTO> searchList = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder = buildSearchQuery(searchFields, false);
        queryBuilder = getFilterQuery(filterSet, queryBuilder);
        queryBuilder = getOrderQuery(queryBuilder, columns, start, end);
       
        queryBuilder = new StringBuilder(queryBuilder.toString().replaceAll("WHERE AND", " WHERE "));
        queryBuilder = new StringBuilder(queryBuilder.toString().endsWith("WHERE") ? queryBuilder.toString().replace("WHERE", " ") : queryBuilder);

        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        searchList = getCustomizedSearchFormToDTO(list);
        return searchList;
    }

    private void loadCriteriaInMap() {
        criteria.clear();
        criteria.put(ConstantsUtils.NET_FORMULA_TYPE, "NET_SALES_FORMULA_TYPE");
        criteria.put(ConstantsUtils.NEP_FORMULA_ID, "NET_SALES_FORMULA_ID");
        criteria.put(ConstantsUtils.NEP_FORMULA_NO, "NET_SALES_FORMULA_NO");
        criteria.put(ConstantsUtils.NEP_FORMULA_NAME, "NET_SALES_FORMULA_NAME");
        criteria.put(ConstantsUtils.CREATEDDATE, "CREATED_DATE");
        criteria.put(ConstantsUtils.MODIFIEDDATE, "MODIFIED_DATE");
    }
    private StringBuilder getFilterQuery(final Set<Container.Filter> filterSet, final StringBuilder stringBuilder) throws ParseException {
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    if (!(ConstantsUtils.CREATEDBY.equals(stringFilter.getPropertyId()) || ConstantsUtils.MODIFIEDBY.equals(stringFilter.getPropertyId()))) {
                    stringBuilder.append(" AND ").append(criteria.get(stringFilter.getPropertyId().toString())).append(" LIKE '%").append(stringFilter.getFilterString()).append("%'");
                    }
                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    String startValue = DB_DATE.format(stringFilter.getStartValue());
                    String endValue = DB_DATE.format(stringFilter.getEndValue());
                    if (startValue != null) {
                        stringBuilder.append(" AND ").append(criteria.get(stringFilter.getPropertyId().toString())).append(" >= '").append(startValue).append("' ");
                    } 
                    if (endValue != null) {
                        stringBuilder.append(" AND ").append(criteria.get(stringFilter.getPropertyId().toString())).append(" <= '").append(endValue).append("' ");
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (stringFilter.getValue() instanceof Date) {
                        String filterString = DB_DATE.format(stringFilter.getValue());
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            stringBuilder.append(" AND ").append(criteria.get(String.valueOf(stringFilter.getPropertyId()))).append(" >= '").append(filterString).append("' ");
                        } else {
                            stringBuilder.append(" AND ").append(criteria.get(String.valueOf(stringFilter.getPropertyId()))).append(" <= '").append(filterString).append("' ");
                        }
                    }
                }
            }
        }
        return stringBuilder;
    }

    private StringBuilder buildSearchQuery(ErrorfulFieldGroup searchFields, boolean isCount) {
        StringBuilder queryBuilder = new StringBuilder();
        String query = isCount ? "COUNT( * )" : constantProperties.getString("netSalesFormula");
        queryBuilder.append(" SELECT ").append(query).append(" FROM NET_SALES_FORMULA_MASTER WHERE INBOUND_STATUS <> 'D' ");
        if (criteria.isEmpty()) {
            loadCriteriaInMap();
        }
        Set<String> keys = criteria.keySet();
        for (String fields : keys) {

            if (searchFields.getField(fields).getValue() != null && !ConstantUtil.SELECT_ONE.equals(searchFields.getField(fields).getValue()) && !searchFields.getField(fields).getValue().toString().trim().isEmpty()) {
                if (ConstantsUtils.NET_FORMULA_TYPE.equalsIgnoreCase(fields)) {
                    queryBuilder.append(" AND ").append(criteria.get(fields)).append(" = ").append(((HelperDTO) searchFields.getField(ConstantsUtils.NET_FORMULA_TYPE).getValue()).getId());
                } else {
                    queryBuilder.append(" AND ").append(criteria.get(fields)).append(" LIKE '").append(searchFields.getField(fields).getValue().toString().trim().replace("*", ConstantsUtils.PERCENCTAGE)).append("'");
                }
            }
        }
        return queryBuilder;
    }
    private StringBuilder getOrderQuery(final StringBuilder stringBuilder, final List<SortByColumn> sortByColumns, final int startIndex, final int endIndex) {
        boolean sortOrder = false;
        String orderByColumn = null;
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                orderByColumn = criteria.get(sortByColumn.getName());
                sortOrder = sortByColumn.getType() != SortByColumn.Type.ASC;
            }
        }
        if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
            stringBuilder.append(" ORDER BY CREATED_DATE ");
        } else {
            stringBuilder.append(" ORDER BY ").append(orderByColumn).append((!sortOrder) ? " ASC " : " DESC ");
        }
        stringBuilder.append(" OFFSET ").append(startIndex);
        stringBuilder.append(" ROWS FETCH NEXT ").append(endIndex).append(" ROWS ONLY;");
        return stringBuilder;
    }

    private List<PSNepFormulaDTO> getCustomizedSearchFormToDTO(List list) {
        final List<PSNepFormulaDTO> searchResultsList = new ArrayList<>();
        try {
            if (list != null) {
                Map<Integer, String> userMap = StplSecurity.userMap;
                if (userMap.isEmpty()) {
                    userMap = StplSecurity.getUserName();
                }
                for (int i = 0; i < list.size(); i++) {
                    final PSNepFormulaDTO searchDTO = new PSNepFormulaDTO();
                    final Object[] object = (Object[]) list.get(i);
                    searchDTO.setNepFormulaTypeDesc((helperListUtil.getIdHelperDTOMap().get(object[0] != null ? Integer.valueOf(String.valueOf(object[0])) : 0)).getDescription());
                    searchDTO.setNepFormulaType((helperListUtil.getIdHelperDTOMap().get(object[0] != null ? Integer.valueOf(String.valueOf(object[0])) : 0)));
                    searchDTO.setNepFormulaID(!ConstantsUtils.NULL.equals(String.valueOf(object[1])) && StringUtils.isNotBlank(String.valueOf(object[1])) ? String.valueOf(object[1]) : StringUtils.EMPTY);
                    searchDTO.setNepFormulaNo(!ConstantsUtils.NULL.equals(String.valueOf(object[2])) && StringUtils.isNotBlank(String.valueOf(object[2])) ? String.valueOf(object[2]) : StringUtils.EMPTY);
                    searchDTO.setNepFormulaName(!ConstantsUtils.NULL.equals(String.valueOf(object[3])) && StringUtils.isNotBlank(String.valueOf(object[3])) ? String.valueOf(object[3]) : StringUtils.EMPTY);
                    searchDTO.setCreatedDate((Date) object[4]);
                    searchDTO.setModifiedDate((Date) object[6]);
                    searchDTO.setCreatedBy(userMap.get(Integer.valueOf(String.valueOf(object[5]))));
                    searchDTO.setModifiedBy(userMap.get(Integer.valueOf(String.valueOf(object[7]))));
                    searchDTO.setNepFormulaSystemID(Integer.valueOf(String.valueOf(object[8])));
                    searchResultsList.add(searchDTO);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return searchResultsList;
    }

    /**     
     * Method configures the price types in a list of HelperDTO and returns the
     * list.
     * 
     * @param priceTypeMap
     * @param isFilter
     * @return 
     */
    public List<HelperDTO> getConfiguredPriceTypes(Map<Integer, String> priceTypeMap,boolean isFilter) {
        HelperDTO helperDTO = new HelperDTO(0, isFilter ?  Constants.SHOW_ALL:ConstantsUtils.SELECT_ONE);
        List priceTypeList = new ArrayList();
        priceTypeList.add(helperDTO);
        for (Map.Entry<Integer, String> entry : priceTypeMap.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                helperDTO = new HelperDTO(entry.getKey(), entry.getValue());
                priceTypeList.add(helperDTO);
            }
        }
        return priceTypeList;
    }
    
    public ComboBox getConfiguredPriceType(Map<Integer, String> priceTypeMap, ComboBox comboBox) {
        comboBox.addItem(0);
        comboBox.setItemCaption(0, ConstantUtil.SELECT_ONE);
        for (Map.Entry<Integer, String> entry : priceTypeMap.entrySet()) {
            comboBox.addItem(String.valueOf(entry.getKey()));
            comboBox.setItemCaption(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }
        return comboBox;
    }

    /**
     * Method returns the map of price types.
     *
     * @return
     * @throws SystemException
     */
    public Map<Integer, String> getItemPricingQualifiers() throws SystemException {
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class);
        Map<Integer, String> qualifierMap = new HashMap<>();
        List<ItemPricingQualifier> list = ItemPricingQualifierLocalServiceUtil.dynamicQuery(dynamicQuery);
        for (ItemPricingQualifier itemPricingQualifier : list) {
            qualifierMap.put(itemPricingQualifier.getItemPricingQualifierSid(), itemPricingQualifier.getItemPricingQualifierName());
        }
        return qualifierMap;
    }
    public void getPsSearchList(String psId,String psNo,String psName,int psStatus,int psType,String itemId,String itemNo,
            String itemName,Map<String,Object> filterMap,int start,int end,String column,String orderBy,boolean isCount){
        String sql = StringUtils.EMPTY;
       
        
        try {
           sql = "SELECT DISTINCT ps.PS_MODEL_SID,ps.PS_ID,ps.PS_NO,ps.PS_NAME,ps.PS_TYPE,ps.PS_STATUS,ps.PS_CATEGORY,ps.PS_START_DATE,ps.PS_END_DATE,"
                   + "ps.PS_DESIGNATION,ps.PARENT_PS_ID,ps.PARENT_PS_NAME,ps.PS_TRADE_CLASS,htype.DESCRIPTION as type,hstatus.DESCRIPTION as status,"
                   + "hcategory.DESCRIPTION as category,hdesign.DESCRIPTION as designation,htrade.DESCRIPTION as trade,ps.RECORD_LOCK_STATUS  FROM PS_MODEL ps"
                   + " JOIN PS_DETAILS psd on ps.PS_MODEL_SID=psd.PS_MODEL_SID"
                   + " JOIN ITEM_MASTER im on psd.ITEM_MASTER_SID=im.ITEM_MASTER_SID"
                   + " LEFT JOIN HELPER_TABLE htype on htype.HELPER_TABLE_SID=ps.PS_TYPE"
                   + " LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID=ps.PS_STATUS"
                   + " LEFT JOIN HELPER_TABLE hcategory on hcategory.HELPER_TABLE_SID=ps.PS_CATEGORY"
                   + " LEFT JOIN HELPER_TABLE hdesign on hdesign.HELPER_TABLE_SID=ps.PS_DESIGNATION"
                   + " LEFT JOIN HELPER_TABLE htrade on htrade.HELPER_TABLE_SID=ps.PS_TRADE_CLASS"
                   + " WHERE ps.INBOUND_STATUS <> 'D'";
           
           if(StringUtils.isNotBlank(psId)){
               sql+=" AND ps.PS_ID LIKE '"+psId+"'";
           }
           
           if(StringUtils.isNotBlank(psNo)){
               sql+=" AND ps.PS_NO LIKE '"+psNo+"'";
           } 
           
           if(StringUtils.isNotBlank(psName)){
               sql+=" AND ps.PS_NAME LIKE '"+psName+"'";
           }
           
           if(psStatus!=0){
               sql+=" AND ps.PS_STATUS ="+psStatus;
           }
           
           if(psType!=0){
               sql+=" AND ps.PS_TYPE ="+psType;
           }
           
           if(StringUtils.isNotBlank(itemId)){
               sql+=" AND im.ITEM_ID LIKE '"+itemId+"'";
           }
           
           if(StringUtils.isNotBlank(itemNo)){
               sql+=" AND im.ITEM_NO LIKE '"+itemNo+"'";
           }
           
           if(StringUtils.isNotBlank(itemName)){
               sql+=" AND im.ITEM_NAME LIKE '"+itemName+"'";
           }
           
           if(filterMap.get("psSystemId")!=null && StringUtils.isNotBlank(String.valueOf(filterMap.get("psSystemId")))){
               sql+=" AND ps.PS_MODEL_SID LIKE '"+String.valueOf(filterMap.get("psSystemId"))+"'";
           }
           
           if(filterMap.get("priceScheduleId")!=null && StringUtils.isNotBlank(String.valueOf(filterMap.get("priceScheduleId")))){
               sql+=" AND ps.PS_ID LIKE '"+String.valueOf(filterMap.get("priceScheduleId"))+"'";
           }
           
           if(filterMap.get("priceScheduleNo")!=null && StringUtils.isNotBlank(String.valueOf(filterMap.get("priceScheduleNo")))){
               sql+=" AND ps.PS_NO LIKE '"+String.valueOf(filterMap.get("priceScheduleNo"))+"'";
           }
           
           if(filterMap.get("priceScheduleName")!=null && StringUtils.isNotBlank(String.valueOf(filterMap.get("priceScheduleName")))){
               sql+=" AND ps.PS_NAME LIKE '"+String.valueOf(filterMap.get("priceScheduleName"))+"'";
           }
           
           if(filterMap.get("priceScheduleStatus")!=null && Integer.valueOf(String.valueOf(filterMap.get("priceScheduleStatus")))!=0){
               sql+=" AND ps.PS_STATUS = "+Integer.valueOf(String.valueOf(filterMap.get("priceScheduleStatus")));
           }
           
           if(filterMap.get("priceScheduleType")!=null && Integer.valueOf(String.valueOf(filterMap.get("priceScheduleType")))!=0){
               sql+=" AND ps.PS_TYPE = "+Integer.valueOf(String.valueOf(filterMap.get("priceScheduleType")));
           }
           
           if(filterMap.get("priceScheduleCategory")!=null && Integer.valueOf(String.valueOf(filterMap.get("priceScheduleCategory")))!=0){
               sql+=" AND ps.PS_CATEGORY = "+Integer.valueOf(String.valueOf(filterMap.get("priceScheduleCategory")));
           }
           
           if(filterMap.get("priceScheduleDesignation")!=null && Integer.valueOf(String.valueOf(filterMap.get("priceScheduleDesignation")))!=0){
               sql+=" AND ps.PS_DESIGNATION = "+Integer.valueOf(String.valueOf(filterMap.get("priceScheduleDesignation")));
           }
           if ((filterMap.get("priceScheduleStartDatestart") != null && !StringUtils.EMPTY.equals(String.valueOf(filterMap.get("priceScheduleStartDatestart"))))) { 
                String startDate = filterMap.get("priceScheduleStartDatestart").toString();
                sql+=" AND ps.PS_START_DATE >= '" +startDate + "' ";
            }
            if ((filterMap.get("priceScheduleStartDateend") != null && !StringUtils.EMPTY.equals(String.valueOf(filterMap.get("priceScheduleStartDateend"))))) {
                String endDate = filterMap.get("priceScheduleStartDateend").toString();
                sql+=" AND ps.PS_START_DATE <= '" + endDate + "' ";
            }
            if ((filterMap.get("priceScheduleEndDatestart") != null && !StringUtils.EMPTY.equals(String.valueOf(filterMap.get("priceScheduleEndDatestart"))))) { 
                String startDate = filterMap.get("priceScheduleEndDatestart").toString();
                sql+=" AND ps.PS_END_DATE >= '" + startDate + "' ";
            }
            if ((filterMap.get("priceScheduleEndDateend") != null && !StringUtils.EMPTY.equals(String.valueOf(filterMap.get("priceScheduleEndDateend"))))) {
                String endDate = filterMap.get("priceScheduleEndDateend").toString();
                sql+=" AND ps.PS_END_DATE <= '" + endDate + "' ";
            }
           if(filterMap.get("priceScheduleStartDatestart")!=null && filterMap.get("priceScheduleStartDateend")!=null 
                   && StringUtils.isNotBlank(String.valueOf(filterMap.get("priceScheduleStartDatestart"))) && StringUtils.isNotBlank(String.valueOf(filterMap.get("priceScheduleStartDateend")))){
               
               String startDate = String.valueOf(filterMap.get("priceScheduleStartDatestart"));
               String endDate = String.valueOf(filterMap.get("priceScheduleStartDateend"));
               
               sql+=" AND ps.PS_START_DATE BETWEEN '"+startDate+"' AND '"+endDate+"'";
           }
           
           if(filterMap.get("priceScheduleEndDatestart")!=null && filterMap.get("priceScheduleEndDateend")!=null 
                   && StringUtils.isNotBlank(String.valueOf(filterMap.get("priceScheduleEndDatestart"))) && StringUtils.isNotBlank(String.valueOf(filterMap.get("priceScheduleEndDateend")))){
               
               String startDate = String.valueOf(filterMap.get("priceScheduleEndDatestart"));
               String endDate = String.valueOf(filterMap.get("priceScheduleEndDateend"));
               
               sql+=" AND ps.PS_END_DATE BETWEEN '"+startDate+"' AND '"+endDate+"'";
           }
           
           if(filterMap.get("parentId")!=null && StringUtils.isNotBlank(String.valueOf(filterMap.get("parentId")))){
               sql+=" AND ps.PS_ID LIKE '"+String.valueOf(filterMap.get("parentId"))+"'";
           }
           
           if(filterMap.get("parentName")!=null && StringUtils.isNotBlank(String.valueOf(filterMap.get("parentName")))){
               sql+=" AND ps.PARENT_PS_NAME LIKE '"+String.valueOf(filterMap.get("parentName"))+"'";
           }
           
            if(filterMap.get("tradeClass")!=null && Integer.valueOf(String.valueOf(filterMap.get("tradeClass")))!=0){
               sql+=" AND ps.PS_TRADE_CLASS = "+Integer.valueOf(String.valueOf(filterMap.get("tradeClass")));
           }
           
           if(!isCount){  
              sql+= "ORDER BY " + column + " " + orderBy + " OFFSET " + start + " ROWS FETCH NEXT " + end + " ROWS ONLY";
            } 

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        } 

    }
    
    }

