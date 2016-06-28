package com.stpl.app.global.ifp.logic;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.dao.impl.CompanySearchLogicDAOImpl;
import com.stpl.app.global.dao.impl.IFPLogicDAOImpl;
import com.stpl.app.global.dao.impl.StplSecurityDAOImpl;
import com.stpl.app.global.ifp.dto.IFPItemDTO;
import com.stpl.app.global.ifp.dto.ItemFamilyplanMasterDTO;
import com.stpl.app.global.ifp.dto.ItemFamilyplanSearchDTO;
import com.stpl.app.global.ifp.dto.TempItemDTO;
import com.stpl.app.global.ifp.util.UIUtils;
import com.stpl.app.global.item.dto.ItemMasterDTO;
import com.stpl.app.global.item.dto.SearchItemForm;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.IfpDetails;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.ImtdIfpDetails;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.MasterDataFiles;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.IfpDetailsLocalServiceUtil;
import com.stpl.app.service.ImtdIfpDetailsLocalServiceUtil;
import com.stpl.app.service.MasterDataFilesLocalServiceUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.ui.UDCIncrementCheck;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.HelperDTO;
import com.stpl.app.util.NotesTabLogic;
import com.stpl.app.util.ValidationUtils;
import com.stpl.domain.global.company.CompanyMasterDAO;
import com.stpl.domain.global.itemfamilyplan.ItemFamilyplanDAO;
import com.stpl.domain.global.itemfamilyplan.ItemFamilyplanLogic;
import com.stpl.domain.global.security.StplSecurityDAO;
import com.stpl.ifs.ui.NotesDTO;
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
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.DateField;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

// TODO: Auto-generated Javadoc
/**
 * The Class IFPLogic.
 */
public class IFPLogic extends BeanItemContainer<SearchItemForm> implements ItemFamilyplanLogic{

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(IFPLogic.class);
    CompanyMasterDAO companyMasterDAO = new CompanySearchLogicDAOImpl();
    private static HelperListUtil helperListUtil=HelperListUtil.getInstance();
    
    //Java date format
    public static String DEFAULT_JAVA_DATE_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";
    //SQL date format
    public static String DEFAULT_SQL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    SessionDTO sessionDTO;
/**
 * Parameter List
 * 
 * @param parameters
 * @param sc 
 */
    private static void getParameterList(Map<String, Object> parameters, BeanSearchCriteria sc) {
        if (sc != null && sc.getFilters() != null) {
            for (Container.Filter filter : sc.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString();
                    parameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;

                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.FROM, startValue);
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.TO, endValue);
                } else if (filter instanceof Compare) {

                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    if (ConstantsUtils.IFP_SYSTEM_ID.equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
                        String value = String.valueOf(compare.getValue());
                        if (operation.GREATER.toString().equalsIgnoreCase(operation.name())) {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.FROM, value);
                        } else if (operation.LESS.toString().equalsIgnoreCase(operation.name())) {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.TO, value);
                        } else if (operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.EQUAL, value);
                        }
                    } else {
                        Date value = (Date) compare.getValue();
                        if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.FROM, value);
                        } else {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.TO, value);
                        }
                    }

                } else if (filter instanceof And) {
                    And stringFilter = (And) filter;
                    Collection<Filter> value = stringFilter.getFilters();
                    for (Filter filter1 : value) {
                        Object propertyId = StringUtils.EMPTY;
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            propertyId = less.getPropertyId();
                            parameters.put(less.getPropertyId() + ConstantsUtils.TO, String.valueOf(less.getValue()));
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            propertyId = greater.getPropertyId();
                            parameters.put(greater.getPropertyId() + ConstantsUtils.FROM, String.valueOf(greater.getValue()));
                        }
                    }
                }
            }
        }
    }

    private NotesTabLogic notesLogic = new NotesTabLogic();
    /**
     * The user.
     */
    private final String user = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
    /**
     * The dao.
     */
    private static final ItemFamilyplanDAO DAO = new IFPLogicDAOImpl();

    private static StplSecurityDAO securityDao = new StplSecurityDAOImpl();

    /**
     * Instantiates a new IFP logic.
     */
    public IFPLogic() {
        super(SearchItemForm.class);

    }
    public IFPLogic(final SessionDTO sessionDTO) {
        super(SearchItemForm.class);
        this.sessionDTO=sessionDTO;
    }

    /**
     * Gets the item for ifp.
     *
     * @param searchField the search field
     * @param value the value
     * @return the item for ifp
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @SuppressWarnings({"unchecked", ConstantsUtils.NULL})
    public List<ItemMasterDTO> getItemForIFP(final String searchField,  final String value) throws SystemException, PortalException, Exception {
    	final  ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
        LOGGER.info("Entering getItemForIFP ");
        map.put(ConstantsUtils.ITEMS_ID, ConstantsUtils.ITEM_ID);
        map.put(ConstantsUtils.ITEMS_NO, ConstantsUtils.ITEM_NO);
        map.put(ConstantsUtils.ITEM_NAMES, ConstantsUtils.ITEM_NAME);
        map.put(ConstantsUtils.ITEMSTATUS, ConstantsUtils.ITEM_STATUS);
        map.put(ConstantsUtils.UOM, ConstantsUtils.PRIMARY_UOM);
        map.put(ConstantsUtils.THERAPEUTIC_CLASS1, ConstantsUtils.THERAPEUTIC_CLASS);
        map.put(ConstantsUtils.BRAND1, ConstantsUtils.BRAND);
        map.put(ConstantsUtils.FORM1, ConstantsUtils.FORM1);
        map.put(ConstantsUtils.STRENGTH, ConstantsUtils.STRENGTH);
        List<ItemMaster> itemMasterList;


        
        if (StringUtils.isNotBlank(value)) {
            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(ItemMaster.class);
            final String query = value.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(
                    map.get(searchField), query));

            ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));


            itemMasterList = DAO.getItemMasterList(ifpDynamicQuery);
                final  List<ItemMasterDTO> itemMasterDTOList = getCustomizedItemData(itemMasterList);
                LOGGER.info("return ItemMaster size -"+((itemMasterDTOList==null)?itemMasterDTOList:itemMasterDTOList.size()));
            return itemMasterDTOList;
        }
        return new ArrayList<ItemMasterDTO>();
    }

    /**
     * Gets the customized item data.
     *
     * @param itemMasterList the item master list
     * @return the customized item data
     * @throws Exception the exception
     */
    public static List<ItemMasterDTO> getCustomizedItemData(final List<ItemMaster> itemMasterList) throws Exception {
        final List<ItemMasterDTO> itemDTO = new ArrayList<ItemMasterDTO>();
        LOGGER.info("Entering getCustomizedItemData p1: size -"+((itemMasterList==null)?itemMasterList:itemMasterList.size()));

        for (int i = 0; i < itemMasterList.size(); i++) {
            final ItemMaster item = itemMasterList.get(i);
            final  ItemMasterDTO itemDTOObj = new ItemMasterDTO();
            itemDTOObj.setItemSystemId(String.valueOf(item.getItemMasterSid()));
            itemDTOObj.setItemId(item.getItemId());
            itemDTOObj.setItemNo(item.getItemNo());
            itemDTOObj.setItemName(item.getItemName());
            itemDTOObj.setItemType(helperListUtil.getIdHelperDTOMap().get(item.getItemType()));
            itemDTOObj.setItemStatus(helperListUtil.getIdHelperDTOMap().get(item.getItemStatus()));
            itemDTOObj.setItemStartDate(item.getItemStartDate());
            itemDTOObj.setItemEndDate(item.getItemEndDate());
            itemDTOObj.setItemCategory(helperListUtil.getIdHelperDTOMap().get(item.getItemCategory()));
            itemDTOObj.setItemDesc(item.getItemDesc());
            itemDTOObj.setForm(helperListUtil.getIdHelperDTOMap().get(item.getForm()));
            itemDTOObj.setStrength(helperListUtil.getIdHelperDTOMap().get(item.getStrength()));
            itemDTOObj.setTherapeuticClass(helperListUtil.getIdHelperDTOMap().get(item.getTherapeuticClass()));
            itemDTOObj.setBrand(String.valueOf(item.getBrandMasterSid()));
            itemDTOObj.setPrimaryUom(helperListUtil.getIdHelperDTOMap().get(item.getPrimaryUom()));
             if(!String.valueOf(item.getPackageSize()).isEmpty() &&  !String.valueOf(item.getPackageSize()).equalsIgnoreCase(ConstantsUtils.NULL)){
                itemDTOObj.setPackageSize(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(item.getPackageSize())));
            }
            itemDTOObj.setCreatedDate(item.getCreatedDate());
            itemDTOObj.setUniqueDate(String.valueOf(new Date() + "`" + i));
            itemDTO.add(itemDTOObj);
        }
        LOGGER.info("Return List of itemDTO " + itemDTO.size());
        return itemDTO;
    }

    /**
     * To search the results from search criteria.
     *
     * @param searchItemForm the search item form
     * @param start the start
     * @param end the end
     * @param orderByColumns the order by columns
     * @return the list
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @SuppressWarnings("unchecked")
    public List<SearchResultsDTO> searchIFP(
            final ErrorfulFieldGroup searchItemForm, final int start, final int end,
            final List<OrderByColumn> orderByColumns, final BeanSearchCriteria sc) throws SystemException,
            PortalException, Exception {
        
        LOGGER.info("Entering searchIFP , p1:" + start + ", p2:" + end + ", p3: " + ((orderByColumns==null)?orderByColumns:orderByColumns.size()));
        String ifpId;
        String ifpNo;
        String ifpName;
        String itemId;
        String itemNo;
        String itemName;
        int ifpType=0;
        int ifpStatus=0;
        Map<String, Object> parameters = new HashMap<String, Object>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
      
        if (searchItemForm.getField(ConstantsUtils.TEXT1).getValue() == null) {
            ifpId = StringUtils.EMPTY;
        } else {
            ifpId = searchItemForm.getField(ConstantsUtils.TEXT1).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantsUtils.TEXT2).getValue() == null) {
            ifpNo = StringUtils.EMPTY;
        } else {

            ifpNo = searchItemForm.getField(ConstantsUtils.TEXT2).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantsUtils.TEXT3).getValue() == null) {
            ifpName = StringUtils.EMPTY;
        } else {
            ifpName = searchItemForm.getField(ConstantsUtils.TEXT3).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantsUtils.COMBO2).getValue() == null || ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO2).getValue())) {
            ifpType = 0;
        } else {
            
             final HelperDTO helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO2).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

               ifpType  = Integer.valueOf(helperDTO.getId());
            }
            
          
        }

        if (searchItemForm.getField(ConstantsUtils.COMBO1).getValue() == null|| ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO1).getValue())) {
            ifpStatus = 0;
        } else {
            
                 final HelperDTO helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO1).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

               ifpStatus  = Integer.valueOf(helperDTO.getId());
            }
        }
        
        if (searchItemForm.getField(ConstantsUtils.TEXT6)!=null && searchItemForm.getField(ConstantsUtils.TEXT6).getValue()!=null) {
            
            itemId = searchItemForm.getField(ConstantsUtils.TEXT6).getValue()
                    .toString().trim();
            
        } else {
            itemId = StringUtils.EMPTY;
        }

        if (searchItemForm.getField(ConstantsUtils.TEXT7)!=null && searchItemForm.getField(ConstantsUtils.TEXT7).getValue()!=null) {
            
            itemNo = searchItemForm.getField(ConstantsUtils.TEXT7).getValue()
                    .toString().trim();
            
        } else {
            itemNo = StringUtils.EMPTY;
            
        }

        if (searchItemForm.getField(ConstantsUtils.TEXT8)!=null && searchItemForm.getField(ConstantsUtils.TEXT8).getValue()!=null) {
            
            itemName = searchItemForm.getField(ConstantsUtils.TEXT8).getValue()
                    .toString().trim();
            
        } else {
            itemName = StringUtils.EMPTY;
        }


        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(IfpModel.class);


        if (StringUtils.isNotBlank(ifpId)) {
            ifpId = ifpId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
            
        }
        if (StringUtils.isNotBlank(ifpNo)) {
            ifpNo = ifpNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
           
        }
        if (StringUtils.isNotBlank(ifpName)) {
            ifpName = ifpName.replace(CommonUtils.CHAR_ASTERISK,
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
            if (ifpType!=0) {
            List<HelperDTO> list = getDropDownList(UIUtils.IFP_TYPE);
            for (HelperDTO list1 : list) {
                if (list1.getId()==ifpType){
                    int sysId = list1.getSystemId();
                    ifpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.IFP_MODEL_TYPE, sysId));
                }
            }

        }
            if (ifpStatus!=0) {
            List<HelperDTO> list = getDropDownList(UIUtils.STATUS);
            for (HelperDTO list1 : list) {
                if (list1.getId()==ifpStatus){
                    int sysId = list1.getSystemId();
                    ifpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.IFP_MODEL_STATUS, sysId));
                }
            }

        }
            
            
        String orderby ="asc";
        String column = ConstantsUtils.IFP_MODEL_SID_DB;
        for (final Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator
                .hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();

            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                ifpDynamicQuery.addOrder(OrderFactoryUtil.asc(orderByColumn
                        .getName()));
            } else {
                ifpDynamicQuery.addOrder(OrderFactoryUtil.desc(orderByColumn
                        .getName()));
            }
        }
        ifpDynamicQuery.setLimit(start, end);
        
        if (sc != null && sc.getFilters() != null) {
            for (Container.Filter filter : sc.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString();
                    parameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                   
                        Date startValue = (Date) betweenFilter.getStartValue();
                        Date endValue = (Date) betweenFilter.getEndValue();
                        parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(startValue)));
                        parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(endValue)));
                } else if (filter instanceof Compare) {

                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    if (ConstantsUtils.IFP_SYSTEM_ID.equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
                        String value = String.valueOf(compare.getValue());
                        if (operation.GREATER.toString().equalsIgnoreCase(operation.name())) {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.FROM, value);
                        } else if (operation.LESS.toString().equalsIgnoreCase(operation.name())) {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.TO, value);
                        } else if (operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.EQUAL, value);
                        }
                    } else {
                        Date value = (Date) compare.getValue();
                        if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(value)));
                        } else {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(value)));
                        }
                    }

                } else if (filter instanceof And) {
                    And stringFilter = (And) filter;
                    Collection<Filter> value = stringFilter.getFilters();
                    for (Filter filter1 : value) {
                        Object propertyId = StringUtils.EMPTY;
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            propertyId = less.getPropertyId();
                            parameters.put(less.getPropertyId() + ConstantsUtils.TO, String.valueOf(less.getValue()));
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            propertyId = greater.getPropertyId();
                            parameters.put(greater.getPropertyId() + ConstantsUtils.FROM, String.valueOf(greater.getValue()));
                        }
                    }
                }
            }
        }
        List result = ImtdIfpDetailsLocalServiceUtil.getIFPSearchList(ifpId, ifpNo, ifpName, ifpType, ifpStatus,itemId,  itemNo,  itemName,  start,  end,  column, orderby,false, parameters);    
        
        
        LOGGER.info("return ifp list ,size = " + ((result==null)?result:result.size()));
        return getCustomizedObjectModel(result);
    }

    /**
     * Gets the customized model from the respective DTO.
     *
     * @param list the list
     * @return the customized model
     */
    private List<ItemFamilyplanSearchDTO> getCustomizedModel(
            final List<IfpModel> list) throws Exception {

        Map<Integer, String> hm = com.stpl.app.util.GeneralCommonUtils.getCodeDescription();
        LOGGER.info("Entering getCustomizedModel p1: "+((list==null)?list:list.size()));
        if (list == null) {
            return new ArrayList<ItemFamilyplanSearchDTO>();
        } else {
            final List<ItemFamilyplanSearchDTO> ifpDTOlist = new ArrayList<ItemFamilyplanSearchDTO>();
            for (int i = 0; i < list.size(); i++) {
                final IfpModel itemFamilyplanMaster = list.get(i);
                final ItemFamilyplanSearchDTO ifpDTO = new ItemFamilyplanSearchDTO();
                ifpDTO.setItemFamilyplanSystemId(itemFamilyplanMaster.getIfpModelSid());
                ifpDTO.setIfpId(itemFamilyplanMaster.getIfpId());
                ifpDTO.setModifiedDate(itemFamilyplanMaster.getModifiedDate());
                ifpDTO.setIfpName(itemFamilyplanMaster.getIfpName());
                ifpDTO.setCreatedBy(String.valueOf(itemFamilyplanMaster.getCreatedBy()));
                ifpDTO.setCreatedDate(itemFamilyplanMaster.getCreatedDate());
                Date date= itemFamilyplanMaster.getIfpEndDate();
                Date startDate = itemFamilyplanMaster.getIfpStartDate();
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                if(date != null) {
                    ifpDTO.setIfpEndDate(dateFormat.format(date));
                }
                ifpDTO.setParentItemFamilyplanName(itemFamilyplanMaster.getParentIfpName());
                ifpDTO.setParentItemFamilyplanId(itemFamilyplanMaster.getParentIfpId());
                ifpDTO.setModifiedBy(String.valueOf(itemFamilyplanMaster.getModifiedBy()));
                ifpDTO.setInboundStatus(itemFamilyplanMaster.getInboundStatus());
                ifpDTO.setIfpNo(itemFamilyplanMaster.getIfpNo());
                if(itemFamilyplanMaster.getIfpStatus()!=0) {
                    ifpDTO.setIfpStatus(hm.get(itemFamilyplanMaster.getIfpStatus()));
                }
                if(itemFamilyplanMaster.getIfpCategory()!=0) {
                    ifpDTO.setIfpCategory(hm.get(itemFamilyplanMaster.getIfpCategory()));
                }
                if(startDate != null) {
                    ifpDTO.setIfpStartDate(dateFormat.format(startDate));
                }

                ifpDTO.setRecordLockStatus(itemFamilyplanMaster.getRecordLockStatus());
                ifpDTO.setBatchId(itemFamilyplanMaster.getBatchId());
                if(itemFamilyplanMaster.getIfpType()!=0) {
                    ifpDTO.setIfpType(hm.get(itemFamilyplanMaster.getIfpType()));
                }
                if(StringUtils.isNotBlank(itemFamilyplanMaster.getIfpDesignation()) && !ConstantsUtils.ZERO.equals(itemFamilyplanMaster.getIfpDesignation())) {
                    ifpDTO.setIfpDesignation(hm.get(Integer.valueOf(itemFamilyplanMaster.getIfpDesignation())));
                }
                ifpDTO.setCommitmentPeriod(itemFamilyplanMaster.getCommitmentPeriod());
                ifpDTO.setTotalDollarCommitment(itemFamilyplanMaster.getTotalDollarCommitment());
                ifpDTO.setTotalVolumeCommitment(itemFamilyplanMaster.getTotalVolumeCommitment());
                ifpDTOlist.add(ifpDTO);
            }
            LOGGER.info("return ifpDTOlist,size = " + ((ifpDTOlist==null)?ifpDTOlist:ifpDTOlist.size()));
            return ifpDTOlist;
        }
    }

    private List<SearchResultsDTO> getCustomizedObjectModel(
            final List list) throws Exception {

        Map<Integer, String> hm = com.stpl.app.util.GeneralCommonUtils.getCodeDescription();
        LOGGER.info("Entering getCustomizedModel p1: "+((list==null)?list:list.size()));
       Map<Integer, String> userMap= StplSecurity.getUserName();
        if (list == null) {
            return new ArrayList<SearchResultsDTO>();
        } else {
         
            final List<SearchResultsDTO> ifpDTOlist = new ArrayList<SearchResultsDTO>();
           try{
            for (int i = 0; i < list.size(); i++) {
                final Object[] itemFamilyplanMaster = (Object[])list.get(i);
                int j=0;
                final SearchResultsDTO ifpDTO = new SearchResultsDTO();
                ifpDTO.setItemFamilyplanSystemId(Integer.valueOf(String.valueOf(itemFamilyplanMaster[j])));
                ifpDTO.setSystemID(String.valueOf(ifpDTO.getItemFamilyplanSystemId()));
                ifpDTO.setIfpId(String.valueOf(itemFamilyplanMaster[++j]));
                ifpDTO.setIfpNo(String.valueOf(itemFamilyplanMaster[++j]));
                ifpDTO.setIfpName(String.valueOf(itemFamilyplanMaster[++j]));
                ++j;
                ++j;
                ++j;
                Object sDate = itemFamilyplanMaster[++j];
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                if(sDate!=null){
                    ifpDTO.setItemFamilyplanStartDate((Date)sDate);
                    ifpDTO.setIfpStartDate(dateFormat.format((Date)sDate));
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    sDate = df.parse(ifpDTO.getIfpStartDate());
                    ifpDTO.setItemFamilyplanStartDate((Date)sDate);
                }
                Object eDate = itemFamilyplanMaster[++j];
                if(eDate!=null){
                    ifpDTO.setItemFamilyplanEndDate((Date)eDate);
                    ifpDTO.setIfpEndDate(dateFormat.format((Date)eDate));
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    eDate = df.parse(ifpDTO.getIfpEndDate());
                    ifpDTO.setItemFamilyplanEndDate((Date)eDate);
                }
                ++j;
                Object parentId=itemFamilyplanMaster[++j];
                if(parentId!=null){
                    ifpDTO.setParentItemFamilyplanId(String.valueOf(parentId));
                }
                Object parentName=itemFamilyplanMaster[++j];
                if(parentName!=null){
                    ifpDTO.setParentItemFamilyplanName(String.valueOf(parentName));
                }
                
                Object totComDol=itemFamilyplanMaster[++j];
                 if(totComDol!=null){
                    ifpDTO.setTotalDollarCommitment(String.valueOf(totComDol));
                }
                Object comPer=itemFamilyplanMaster[++j];
                 if(comPer!=null){
                    ifpDTO.setCommitmentPeriod(String.valueOf(comPer));
                }
                Object totVolCom=itemFamilyplanMaster[++j];
                 if(totVolCom!=null){
                    ifpDTO.setTotalVolumeCommitment(String.valueOf(totVolCom));
                }
                 
                 Object market=itemFamilyplanMaster[++j];
                 if(market!=null){
                    ifpDTO.setTotalMarketshareCommitment(String.valueOf(market));
                }
                Object createdBy=itemFamilyplanMaster[++j];
                ifpDTO.setIfpcreatedBy(userMap.get(Integer.valueOf(String.valueOf(createdBy))));
                Object createdDate=itemFamilyplanMaster[++j];
                ifpDTO.setIfpcreatedDate((Date)createdDate);
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                createdDate = df.parse(CommonUtils.convertDateToString((Date)createdDate));      
                ifpDTO.setIfpcreatedDate((Date)createdDate);
                String ifpType = String.valueOf(itemFamilyplanMaster[++j]);
                if(!ifpType.equals(ConstantsUtils.NULL) && !ifpType.equals(ConstantsUtils.SELECT_ONE)){
                    ifpDTO.setIfpType(ifpType);
                }
                
                ifpDTO.setIfpStatus(String.valueOf(itemFamilyplanMaster[++j]));
                String category= String.valueOf(itemFamilyplanMaster[++j]);
                if(  !category.equals(ConstantsUtils.NULL) && !category.equals(ConstantsUtils.SELECT_ONE)){
                    
                    ifpDTO.setIfpCategory(category );
                }
                String designation= String.valueOf(itemFamilyplanMaster[++j]);
                if( !designation.equals(ConstantsUtils.NULL) && !designation.equals(ConstantsUtils.SELECT_ONE)){
                    ifpDTO.setIfpDesignation(designation);
                }
                Object rstatus = itemFamilyplanMaster[++j];
                if(rstatus!=null){
                     ifpDTO.setRecordLockStatus((Boolean.valueOf(String.valueOf(rstatus))));
                }
                ifpDTOlist.add(ifpDTO);
            }
            }catch(Exception e){
                    LOGGER.error(e);
                    }
            LOGGER.info("return ifpDTOlist,size = " + ((ifpDTOlist==null)?ifpDTOlist:ifpDTOlist.size()));
            return ifpDTOlist;
        }
    }
    
    
     /**
     * Gets the search count by passing the search criteria.
     *
     * @param searchItemForm the search item form
     * @param search
     * @return the search count
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public int getLookUpSearchCount(final ErrorfulFieldGroup searchItemForm, final BeanSearchCriteria sc) throws SystemException, PortalException {
        LOGGER.info("Entering getSearchCount");
       
        String ifpId;
        String ifpNo;
        String ifpName;
        int ifpType=0;
        int ifpStatus=0;
        String itemId;
        String itemNo;
        String itemName;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

       try{
            Map<String, Object> parameters = new HashMap<String, Object>();
            if (searchItemForm.getField(ConstantsUtils.IFP_ID).getValue() == null) {

                ifpId = StringUtils.EMPTY;
            } else {

                ifpId = searchItemForm.getField(ConstantsUtils.IFP_ID).getValue()

                        .toString().trim();
            }
                
           
            if (searchItemForm.getField(ConstantsUtils.IFP_NO).getValue() == null) {

                ifpNo = StringUtils.EMPTY;
            } else {


                ifpNo = searchItemForm.getField(ConstantsUtils.IFP_NO).getValue()

                        .toString().trim();
            }

           
            if (searchItemForm.getField(ConstantsUtils.IFP_NAME).getValue() == null) {

                ifpName = StringUtils.EMPTY;
            } else {

                ifpName = searchItemForm.getField(ConstantsUtils.IFP_NAME).getValue()

                        .toString().trim();
            }

           
            if (searchItemForm.getField(ConstantsUtils.IFP_TYPE).getValue() == null) {

                ifpType = 0;
            } else {
                ifpType = ((com.stpl.ifs.util.HelperDTO)searchItemForm.getField(ConstantsUtils.IFP_TYPE).getValue()).getId();
            }

            
            if (searchItemForm.getField(ConstantsUtils.IFP_STATUS).getValue() == null) {

                ifpStatus = 0;
            } else {
                ifpStatus = ((com.stpl.ifs.util.HelperDTO)searchItemForm.getField(ConstantsUtils.IFP_STATUS).getValue()).getId();
            }
            if (searchItemForm.getField(ConstantsUtils.ITEM_ID)!=null && searchItemForm.getField(ConstantsUtils.ITEM_ID).getValue()!=null) {
            
            itemId = searchItemForm.getField(ConstantsUtils.ITEM_ID).getValue()
                    .toString().trim();
            
        } else {
            itemId = StringUtils.EMPTY;
        }

        if (searchItemForm.getField(ConstantsUtils.ITEM_NO)!=null && searchItemForm.getField(ConstantsUtils.ITEM_NO).getValue()!=null) {
            
            itemNo = searchItemForm.getField(ConstantsUtils.ITEM_NO).getValue()
                    .toString().trim();
            
        } else {
            itemNo = StringUtils.EMPTY;
            
        }

        if (searchItemForm.getField(ConstantsUtils.ITEM_NAME)!=null && searchItemForm.getField(ConstantsUtils.ITEM_NAME).getValue()!=null) {
            
            itemName = searchItemForm.getField(ConstantsUtils.ITEM_NAME).getValue()
                    .toString().trim();
            
        } else {
            itemName = StringUtils.EMPTY;
        }
            
            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(IfpModel.class);

            // Added to check INBOUND_STATUS is A. ETL Soft delete data should not be included, where INBOUND_STATUS is D
            ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));
           
            if (StringUtils.isNotBlank(ifpId)) {
                ifpId = ifpId.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
              
                ifpDynamicQuery.add(RestrictionsFactoryUtil.like(
                        ConstantsUtils.IFP_MODEL_ID, ifpId));
            }
            if (StringUtils.isNotBlank(ifpNo)) {
                ifpNo = ifpNo.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
                ifpDynamicQuery.add(RestrictionsFactoryUtil.like(
                        ConstantsUtils.IFP_MODEL_NO, ifpNo));
            }
            if (StringUtils.isNotBlank(ifpName)) {
                ifpName = ifpName.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
                ifpDynamicQuery.add(RestrictionsFactoryUtil.like(
                        ConstantsUtils.IFP_MODEL_NAME, ifpName));
            }
            if (StringUtils.isNotBlank(itemId)) {
                itemId = itemId.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
             
            }
            if (StringUtils.isNotBlank(itemNo)) {
                itemNo = ifpName.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
                
            }
            if (StringUtils.isNotBlank(itemName)) {
                itemName = itemName.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
                
            }
            if (ifpType!=0) {
                List<HelperDTO> list = getDropDownList(UIUtils.IFP_TYPE);
                for (HelperDTO list1 : list) {
                if (list1.getId()==ifpType){
                        int sysId = list1.getSystemId();
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.IFP_MODEL_TYPE, sysId));
                    }
                }

            }

           if (ifpStatus!=0) {
                List<HelperDTO> list = getDropDownList(UIUtils.STATUS);
                for (HelperDTO list1 : list) {
                if (list1.getId()==ifpStatus){
                        int sysId = list1.getSystemId();
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.IFP_MODEL_STATUS, sysId));
                    }
                }

            }
           
           if (sc != null && sc.getFilters() != null) {
               for (Container.Filter filter : sc.getFilters()) {
                   if (filter instanceof SimpleStringFilter) {
                       SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                       String filterString = stringFilter.getFilterString();

                       parameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);
                        } else if (filter instanceof Between) {
                       Between betweenFilter = (Between) filter;
                       
                           Date startValue = (Date) betweenFilter.getStartValue();
                           Date endValue = (Date) betweenFilter.getEndValue();
                           
                           parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(startValue)));
                           
                           parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(endValue)));
                      

                   } else if (filter instanceof Compare) {

                       Compare compare = (Compare) filter;
                       
                       Compare.Operation operation = compare.getOperation();
                       if (ConstantsUtils.IFP_SYSTEM_ID.equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
                           String value = String.valueOf(compare.getValue());
                           if (operation.GREATER.toString().equals(operation.name())) {
                               parameters.put(compare.getPropertyId() + ConstantsUtils.FROM, value);
                           } else if (operation.LESS.toString().equals(operation.name())) {
                               parameters.put(compare.getPropertyId() + ConstantsUtils.TO, value);
                           } else if (operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
                               parameters.put(compare.getPropertyId() + ConstantsUtils.EQUAL, value);
                           }
                       } else {
                           Date value = (Date) compare.getValue();
                           if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                               parameters.put(compare.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(value)));
                           } else {
                               parameters.put(compare.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(value)));
                           }
                       }

                   } else if (filter instanceof And) {
                       
                    And stringFilter = (And) filter;
                    
                    Collection<Filter> value = stringFilter.getFilters();
                    for (Filter filter1 : value) {
                        Object propertyId = StringUtils.EMPTY;
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            propertyId = less.getPropertyId();
                            parameters.put(less.getPropertyId() + ConstantsUtils.TO, String.valueOf(less.getValue()));
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            propertyId = greater.getPropertyId();
                            parameters.put(greater.getPropertyId() + ConstantsUtils.FROM, String.valueOf(greater.getValue()));
                        }
                    }
                }
               }
           }
            List resultList =ImtdIfpDetailsLocalServiceUtil.getIFPSearchList(ifpId, ifpNo, ifpName, ifpType, ifpStatus,itemId,  itemNo,  itemName,  0,  0,  null, null,true, parameters);
           
            
            return resultList.size();
       }catch(Exception e)
       {

            return 0;
        }
    }
    
    
    /**
     * To search the results from search criteria.
     *
     * @param searchItemForm the search item form
     * @param start the start
     * @param end the end
     * @param orderByColumns the order by columns
     * @return the list
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @SuppressWarnings("unchecked")
    public List<ItemFamilyplanSearchDTO> lookupSearchIFP(
            final ErrorfulFieldGroup searchItemForm, final int start, final int end,
            final List<OrderByColumn> orderByColumns, final BeanSearchCriteria sc) throws SystemException,
            PortalException, Exception {
        
        LOGGER.info("Entering searchIFP , p1:" + start + ", p2:" + end + ", p3: " + ((orderByColumns==null)?orderByColumns:orderByColumns.size()));
        String ifpId;
        String ifpNo;
        String ifpName;
        String itemId;
        String itemNo;
        String itemName;
        int ifpType;
        int ifpStatus;
        Map<String, Object> parameters = new HashMap<String, Object>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
      
        if (searchItemForm.getField(ConstantsUtils.IFP_ID).getValue() == null) {
            ifpId = StringUtils.EMPTY;
        } else {
            ifpId = searchItemForm.getField(ConstantsUtils.IFP_ID).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantsUtils.IFP_NO).getValue() == null) {
            ifpNo = StringUtils.EMPTY;
        } else {

            ifpNo = searchItemForm.getField(ConstantsUtils.IFP_NO).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantsUtils.IFP_NAME).getValue() == null) {
            ifpName = StringUtils.EMPTY;
        } else {
            ifpName = searchItemForm.getField(ConstantsUtils.IFP_NAME).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantsUtils.IFP_TYPE).getValue() == null) {
            ifpType = 0;
        } else {
            ifpType = ((com.stpl.ifs.util.HelperDTO)searchItemForm.getField(ConstantsUtils.IFP_TYPE).getValue()).getId();
        }

        if (searchItemForm.getField(ConstantsUtils.IFP_STATUS).getValue() == null) {
            ifpStatus = 0;
        } else {
            ifpStatus = ((com.stpl.ifs.util.HelperDTO)searchItemForm.getField(ConstantsUtils.IFP_STATUS).getValue()).getId();
        }
        
        if (searchItemForm.getField(ConstantsUtils.ITEM_ID)!=null && searchItemForm.getField(ConstantsUtils.ITEM_ID).getValue()!=null) {
            
            itemId = searchItemForm.getField(ConstantsUtils.ITEM_ID).getValue()
                    .toString().trim();
            
        } else {
            itemId = StringUtils.EMPTY;
        }

        if (searchItemForm.getField(ConstantsUtils.ITEM_NO)!=null && searchItemForm.getField(ConstantsUtils.ITEM_NO).getValue()!=null) {
            
            itemNo = searchItemForm.getField(ConstantsUtils.ITEM_NO).getValue()
                    .toString().trim();
            
        } else {
            itemNo = StringUtils.EMPTY;
            
        }

        if (searchItemForm.getField(ConstantsUtils.ITEM_NAME)!=null && searchItemForm.getField(ConstantsUtils.ITEM_NAME).getValue()!=null) {
            
            itemName = searchItemForm.getField(ConstantsUtils.ITEM_NAME).getValue()
                    .toString().trim();
            
        } else {
            itemName = StringUtils.EMPTY;
        }



        if (StringUtils.isNotBlank(ifpId)) {
            ifpId = ifpId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
            
        }
        if (StringUtils.isNotBlank(ifpNo)) {
            ifpNo = ifpNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
           
        }
        if (StringUtils.isNotBlank(ifpName)) {
            ifpName = ifpName.replace(CommonUtils.CHAR_ASTERISK,
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
            if (ifpType!=0) {
            List<HelperDTO> list = getDropDownList(UIUtils.IFP_TYPE);
            for (HelperDTO list1 : list) {
                if (list1.getId()==ifpType){
                    int sysId = list1.getSystemId();
                }
            }

        }
            if (ifpStatus!=0) {
            List<HelperDTO> list = getDropDownList(UIUtils.STATUS);
            for (HelperDTO list1 : list) {
                if (list1.getId()==ifpStatus){
                    int sysId = list1.getSystemId();
                }
            }

        }
            
        String orderby =ConstantsUtils.ASC;
        String column = ConstantsUtils.IFP_MODEL_SID_DB;
        for (final Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator
                .hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
            String columnName = orderByColumn.getName();
            if (ConstantsUtils.IFP_SYSTEM_ID.equals(columnName)) {
                column = ConstantsUtils.IFP_MODEL_SID_DB;
            } else if (ConstantsUtils.IFP_SYS_ID.equals(columnName)) {
                column = "IFP_ID";
            } else if (ConstantsUtils.IFPNO.equals(columnName)) {
                column = "IFP_NO";
            } else if ("ifpName".equals(columnName)) {
                column = "IFP_NAME";
            } else if ("ifpType".equals(columnName)) {
                column = "IFP_TYPE";
            } else if ("ifpStatus".equals(columnName)) {
                column = "IFP_STATUS";
            } else if ("ifpCategory".equals(columnName)) {
                column = "IFP_CATEGORY";
            } else if (ConstantsUtils.IFP_START_DATE.equals(columnName)) {
                column = "IFP_START_DATE";
            } else if (ConstantsUtils.IFP_END_DATE.equals(columnName)) {
                column = "IFP_END_DATE";
            } else if ("ifpDesignation".equals(columnName)) {
                column = "IFP_DESIGNATION";
            } else if (ConstantsUtils.PARENT_IFP_ID.equals(columnName)) {
                column = "PARENT_IFP_ID";
            } else if (ConstantsUtils.PARENT_IFP_NAME.equals(columnName)) {
                column = "PARENT_IFP_NAME";
            }

            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                orderby = ConstantsUtils.ASC;
            } else {
                orderby = ConstantsUtils.DESC ;
            }
        }
        
        if (sc != null && sc.getFilters() != null) {
            for (Container.Filter filter : sc.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString();
                    parameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                   
                        Date startValue = (Date) betweenFilter.getStartValue();
                        Date endValue = (Date) betweenFilter.getEndValue();
                        parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(startValue)));
                        parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(endValue)));
                } else if (filter instanceof Compare) {

                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    if (ConstantsUtils.IFP_SYSTEM_ID.equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
                        String value = String.valueOf(compare.getValue());
                        if (operation.GREATER.toString().equalsIgnoreCase(operation.name())) {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.FROM, value);
                        } else if (operation.LESS.toString().equalsIgnoreCase(operation.name())) {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.TO, value);
                        } else if (operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.EQUAL, value);
                        }
                    } else {
                        Date value = (Date) compare.getValue();
                        if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(value)));
                        } else {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(value)));
                        }
                    }

                } else if (filter instanceof And) {
                    And stringFilter = (And) filter;
                    Collection<Filter> value = stringFilter.getFilters();
                    for (Filter filter1 : value) {
                        Object propertyId = StringUtils.EMPTY;
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            propertyId = less.getPropertyId();
                            parameters.put(less.getPropertyId() + ConstantsUtils.TO, String.valueOf(less.getValue()));
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            propertyId = greater.getPropertyId();
                            parameters.put(greater.getPropertyId() + ConstantsUtils.FROM, String.valueOf(greater.getValue()));
                        }
                    }
                }
            }
        }
        List result = ImtdIfpDetailsLocalServiceUtil.getIFPSearchList(ifpId, ifpNo, ifpName, ifpType, ifpStatus,itemId,  itemNo,  itemName,  start,  end,  column, orderby,false, parameters);    
        
        
        LOGGER.info("return ifp list ,size = " + ((result==null)?result:result.size()));
        return getLookUpCustomizedObjectModel(result);
    }
    
    
    private List<ItemFamilyplanSearchDTO> getLookUpCustomizedObjectModel(
            final List list) throws Exception {

        Map<Integer, String> hm = com.stpl.app.util.GeneralCommonUtils.getCodeDescription();
        LOGGER.info("Entering getCustomizedModel p1: "+((list==null)?list:list.size()));
       Map<Integer, String> userMap= StplSecurity.getUserName();
        if (list == null) {
            return new ArrayList<ItemFamilyplanSearchDTO>();
        } else {
         
            final List<ItemFamilyplanSearchDTO> ifpDTOlist = new ArrayList<ItemFamilyplanSearchDTO>();
           try{
            for (int i = 0; i < list.size(); i++) {
                final Object[] itemFamilyplanMaster = (Object[])list.get(i);
                int j=0;
                final ItemFamilyplanSearchDTO ifpDTO = new ItemFamilyplanSearchDTO();
                ifpDTO.setItemFamilyplanSystemId(Integer.valueOf(String.valueOf(itemFamilyplanMaster[j])));
                ifpDTO.setIfpId(String.valueOf(itemFamilyplanMaster[++j]));
                ifpDTO.setIfpNo(String.valueOf(itemFamilyplanMaster[++j]));
                ifpDTO.setIfpName(String.valueOf(itemFamilyplanMaster[++j]));
                ++j;
                ++j;
                ++j;
                Object sDate = itemFamilyplanMaster[++j];
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                if(sDate!=null){
                    ifpDTO.setItemFamilyplanStartDate((Date)sDate);
                    ifpDTO.setIfpStartDate(dateFormat.format((Date)sDate));
                }
                Object eDate = itemFamilyplanMaster[++j];
                if(eDate!=null){
                    ifpDTO.setItemFamilyplanEndDate((Date)eDate);
                    ifpDTO.setIfpEndDate(dateFormat.format((Date)eDate));
                }
                ++j;
                Object parentId=itemFamilyplanMaster[++j];
                if(parentId!=null){
                    ifpDTO.setParentItemFamilyplanId(String.valueOf(parentId));
                }
                Object parentName=itemFamilyplanMaster[++j];
                if(parentName!=null){
                    ifpDTO.setParentItemFamilyplanName(String.valueOf(parentName));
                }
                
                Object totComDol=itemFamilyplanMaster[++j];
                 if(totComDol!=null){
                    ifpDTO.setTotalDollarCommitment(String.valueOf(totComDol));
                }
                Object comPer=itemFamilyplanMaster[++j];
                 if(comPer!=null){
                    ifpDTO.setCommitmentPeriod(String.valueOf(comPer));
                }
                Object totVolCom=itemFamilyplanMaster[++j];
                 if(totVolCom!=null){
                    ifpDTO.setTotalVolumeCommitment(String.valueOf(totVolCom));
                }
                 
                 Object market=itemFamilyplanMaster[++j];
                 if(market!=null){
                    ifpDTO.setTotalMarketshareCommitment(String.valueOf(market));
                }
                Object createdBy=itemFamilyplanMaster[++j];
                ifpDTO.setCreatedBy(userMap.get(Integer.valueOf(String.valueOf(createdBy))));
                Object createdDate=itemFamilyplanMaster[++j];
                ifpDTO.setCreatedDate((Date)createdDate);
                String ifpType = String.valueOf(itemFamilyplanMaster[++j]);
                if(!ifpType.equals(ConstantsUtils.NULL) && !ifpType.equals(ConstantsUtils.SELECT_ONE)){
                    ifpDTO.setIfpType(ifpType);
                }
                
                ifpDTO.setIfpStatus(String.valueOf(itemFamilyplanMaster[++j]));
                String category= String.valueOf(itemFamilyplanMaster[++j]);
                if(  !category.equals(ConstantsUtils.NULL) && !category.equals(ConstantsUtils.SELECT_ONE)){
                    
                    ifpDTO.setIfpCategory(category );
                }
                String designation= String.valueOf(itemFamilyplanMaster[++j]);
                if( !designation.equals(ConstantsUtils.NULL) && !designation.equals(ConstantsUtils.SELECT_ONE)){
                    ifpDTO.setIfpDesignation(designation);
                }
                Object rstatus = itemFamilyplanMaster[++j];
                if(rstatus!=null){
                     ifpDTO.setRecordLockStatus((Boolean.valueOf(String.valueOf(rstatus))));
                }
                ifpDTOlist.add(ifpDTO);
            }
            }catch(Exception e){
                    LOGGER.error(e);
                    }
            LOGGER.info("return ifpDTOlist,size = " + ((ifpDTOlist==null)?ifpDTOlist:ifpDTOlist.size()));
            return ifpDTOlist;
        }
    }
    /**
     * Save ifp fields when add /save button is triggered.
     *
     * @param ifpForm the ifp form
     * @param ifpList the ifp list
     * @return the string
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public String saveIFP(final ErrorfulFieldGroup ifpForm, final List<IFPItemDTO> ifpList, final List<NotesDTO> availableUploadedInformation, final String addedNotes,final List<NotesDTO> removedDetailsList) throws SystemException, PortalException, Exception {
        LOGGER.info("Entering saveIFP p2 : " + ((ifpList == null) ? ifpList : ifpList.size()));
        boolean flag = false;
        int listValue = 0;
         DateField date=new DateField();
        date.setValue(new Date());
        date.setDateFormat("mm-dd-yy");
        try {

            String systemId = ifpForm.getField(ConstantsUtils.IFP_SYSTEM_ID)
                    .getValue() == null && ConstantsUtils.NULL.equals(ifpForm
                            .getField(ConstantsUtils.IFP_SYSTEM_ID).getValue()) ? StringUtils.EMPTY : String
                    .valueOf(ifpForm.getField(ConstantsUtils.IFP_SYSTEM_ID).getValue()).replace(ConstantsUtils.COMMA, StringUtils.EMPTY);
            if (ConstantsUtils.NULL.equals(systemId) || ConstantsUtils.ZERO.equals(systemId)) {
                final DynamicQuery query = DynamicQueryFactoryUtil.forClass(IfpModel.class);
                ifpForm.commit();

                String tempItemId = String.valueOf(ifpForm.getField(ConstantsUtils.IFP_ID).getValue()).trim();

                query.add(RestrictionsFactoryUtil.eq(ConstantsUtils.IFP_SYS_ID, tempItemId));
                query.add(RestrictionsFactoryUtil.eq("inboundStatus", ConstantsUtils.INBOUND_STATUS_D));
                final List<IfpModel> value = DAO.getItemFamilyplanMasterList(query);

                if (!value.isEmpty()) {
                    for (int i = 0; i < value.size(); i++) {

                        listValue = value.get(i).getIfpModelSid();

                        systemId = String.valueOf(listValue);
                    }

                    flag = true;

                }
            }
            IfpModel item;
            if (ConstantsUtils.NULL.equals(systemId) || ConstantsUtils.ZERO.equals(systemId)) {
                item = IfpModelLocalServiceUtil.createIfpModel(0);
                item.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
                item.setSource(ConstantsUtils.GTN);
            } else {
                item = DAO.getItemFamilyplanMasterBySystemId(Integer.parseInt(systemId));
                item.setInboundStatus(ConstantsUtils.INBOUND_STATUS_C);
                item.setSource(ConstantsUtils.GTN);
                if (flag) {
                    item.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
                    item.setSource(ConstantsUtils.GTN);
                    sessionDTO.setSystemId(Integer.parseInt(systemId));
                }
            }

         

            item.setIfpCategory(ifpForm.getField("itemFamilyplanCategory").getValue()!=null ? ((com.stpl.ifs.util.HelperDTO)ifpForm.getField("itemFamilyplanCategory").getValue()).getId() : 0);
            if(ifpForm.getField(ConstantsUtils.IFP_TYPE).getValue()!=null){
                item.setIfpType(((com.stpl.ifs.util.HelperDTO)ifpForm.getField(ConstantsUtils.IFP_TYPE).getValue()).getId());
            }else{
                item.setIfpType(0);
            }

            item.setIfpDesignation(ifpForm.getField("itemFamilyplanDesignation").getValue()!=null ? String.valueOf(((com.stpl.ifs.util.HelperDTO)ifpForm.getField("itemFamilyplanDesignation").getValue()).getId()) : StringUtils.EMPTY);

            if (ConstantsUtils.NULL.equals(systemId) || ConstantsUtils.ZERO.equals(systemId)) {
                LOGGER.info("Entering Save operation");

                item.setIfpId(String.valueOf(ifpForm.getField(ConstantsUtils.IFP_ID).getValue()).trim());
                item.setIfpNo(String.valueOf(ifpForm.getField(ConstantsUtils.IFP_NO).getValue()).trim());
                item.setIfpName(String.valueOf(ifpForm.getField(ConstantsUtils.IFP_NAME).getValue()).trim());
                item.setParentIfpId(String.valueOf(ifpForm.getField(ConstantsUtils.PARENT_IFP_ID).getValue()));
                item.setParentIfpName(String.valueOf(ifpForm.getField(ConstantsUtils.PARENT_IFP_NAME).getValue()).trim());
                item.setRecordLockStatus(ConstantsUtils.UNLOCKED);
                item.setIfpStatus(((com.stpl.ifs.util.HelperDTO)ifpForm.getField(ConstantsUtils.IFP_STATUS).getValue()).getId());
                item.setCreatedBy(Integer.parseInt(user));
                item.setIfpStartDate((Date) ifpForm.getField(ConstantsUtils.IFP_START_DATE).getValue());
                item.setIfpEndDate((Date) ifpForm.getField(ConstantsUtils.IFP_END_DATE).getValue());
                final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpModel.class);
                item.setModifiedDate(new Date());
                item.setCreatedDate(new Date());
                item.setInternalNotes(addedNotes);
                ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.IFP_MODEL_ID, item.getIfpId()));

                final List<IfpModel> list = DAO.getItemFamilyplanMasterList(ifpDynamicQuery);

                if (list.size() < Constants.ONE) {

                    final IfpModel result = DAO.saveItemFamilyplanMaster(item);
                    sessionDTO.setSystemId(result.getIfpModelSid());

                    if (result.getIfpStatus() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(result.getIfpStatus()), UIUtils.IFP_STATUS);
                    }

                    if (result.getIfpType() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(result.getIfpType()), UIUtils.IFP_TYPE);
                    }

                    if (result.getIfpCategory() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(result.getIfpCategory()), UIUtils.IFP_CATEGORY);
                    }

                    if (result.getIfpDesignation() != null) {
                        UDCIncrementCheck.increment(result.getIfpDesignation(), UIUtils.IFP_DESIGNATION);
                    }

                    saveDetailsList(ifpList, result);
                    notesLogic.saveUploadedInformation(availableUploadedInformation, "IFP_MODEL", result.getIfpModelSid());
                } else {
                    ifpForm.getErrorDisplay().setError("Please enter different IFP Id.Since selected Item Id and Start Date Combination already exists");
                    LOGGER.info(ConstantsUtils.DUPLICATE);
                    return ConstantsUtils.DUPLICATE;

                }

            } else {
                LOGGER.info("Entering update operation");

                   if(flag){
                    item.setCreatedBy(Integer.parseInt(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID))));
                    item.setModifiedDate(date.getValue());
                    item.setCreatedDate(date.getValue());

                }

                item.setIfpId(String.valueOf(ifpForm.getField(
                        ConstantsUtils.IFP_ID).getValue()).trim());
                item.setIfpNo(String.valueOf(ifpForm.getField(
                        ConstantsUtils.IFP_NO).getValue()).trim());
                item.setIfpName(String.valueOf(ifpForm.getField(
                        ConstantsUtils.IFP_NAME).getValue()).trim());
                item.setParentIfpId(String.valueOf(ifpForm.getField(
                        ConstantsUtils.PARENT_IFP_ID).getValue()));
                item.setParentIfpName(String.valueOf(ifpForm.getField(
                        ConstantsUtils.PARENT_IFP_NAME).getValue()).trim());
                item.setIfpStatus(((com.stpl.ifs.util.HelperDTO)ifpForm.getField(ConstantsUtils.IFP_STATUS).getValue()).getId());
                item.setRecordLockStatus(ConstantsUtils.UNLOCKED);

                if(ifpForm.getField(ConstantsUtils.IFP_NO)==null)
              {

                }

                item.setIfpStartDate((Date) ifpForm.getField(
                        ConstantsUtils.IFP_START_DATE).getValue());
                item.setIfpEndDate((Date) ifpForm.getField(
                        ConstantsUtils.IFP_END_DATE).getValue());
                
                final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                        .forClass(IfpModel.class);

                ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(
                        ConstantsUtils.IFP_MODEL_ID, item.getIfpId()));

                @SuppressWarnings("unchecked")
                final List<IfpModel> list = DAO.getItemFamilyplanMasterList(ifpDynamicQuery);
                int count = 0;

                for (int i = 0; i < list.size(); i++) {
                    if (Integer.parseInt(systemId.replace(ConstantsUtils.COMMA, StringUtils.EMPTY)) != list.get(i).getIfpModelSid()) {
                        count++;
                    }
                }

                if (count < Constants.ONE) {

                    item.setIfpModelSid(Integer.valueOf(systemId));

                    item.setModifiedDate(new Date());
                    item.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                    item.setInternalNotes(addedNotes);
                    final IfpModel ifp = DAO.getItemFamilyplanMasterBySystemId(item.getIfpModelSid());

                    if (ifp.getIfpStatus() > 0) {
                        UDCIncrementCheck.decrement(String.valueOf(ifp.getIfpStatus()), UIUtils.IFP_STATUS);
                    }

                    if (ifp.getIfpType() > 0) {
                        UDCIncrementCheck.decrement(String.valueOf(ifp.getIfpType()), UIUtils.IFP_TYPE);
                    }

                    if (ifp.getIfpCategory() > 0) {
                        UDCIncrementCheck.decrement(String.valueOf(ifp.getIfpCategory()), UIUtils.IFP_CATEGORY);
                    }

                    if (ifp.getIfpDesignation() != null) {
                        UDCIncrementCheck.decrement(ifp.getIfpDesignation(), UIUtils.IFP_DESIGNATION);
                    }

                    final IfpModel result = DAO.updateItemfamilyplanMaster(item);

                    if (result.getIfpStatus() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(result.getIfpStatus()), UIUtils.STATUS);
                    }

                    if (result.getIfpType() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(result.getIfpType()), UIUtils.IFP_TYPE);
                    }

                    if (result.getIfpCategory() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(result.getIfpCategory()), UIUtils.IFP_CATEGORY);
                    }

                    if (result.getIfpDesignation() != null) {
                        UDCIncrementCheck.increment(result.getIfpDesignation(), UIUtils.IFP_DESIGNATION);
                    }


                    if(flag){
                 
                        updateFlagDetailsList(ifpList, result);

                    }
                    else {

                        updateDetailsList(ifpList, result);

                    }

                    if (!removedDetailsList.isEmpty()) {

                        for (int i = 0; i < removedDetailsList.size(); i++) {
                            NotesDTO dtoValue = removedDetailsList.get(i);

                            if (dtoValue.getDocDetailsId() != 0) {

                                notesLogic.deleteUploadedFile(dtoValue.getDocDetailsId(), StringUtils.EMPTY, dtoValue.getDocumentFullPath());
                            }
                        }
                    }

                    notesLogic.saveUploadedInformation(availableUploadedInformation, "IFP_MODEL", result.getIfpModelSid());

                } else {
                    LOGGER.info(ConstantsUtils.DUPLICATE);
                    return ConstantsUtils.DUPLICATE;
                }
            }
            LOGGER.info("Return success");
            return ConstantsUtils.SUCCESS;
        } catch (Exception e) {

            LOGGER.error(e);
            return null;
        }
    }

    /**
     * Method to Save details list when the Save IFP is called.
     *
     * @param ifpList the ifp list
     * @param itemFamilyPlanMaster the item family plan master
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public void saveDetailsList(final List<IFPItemDTO> ifpList,
            final IfpModel itemFamilyPlanMaster) throws SystemException, PortalException,Exception {
        LOGGER.info("Entering saveDetailsList p1:"+((ifpList==null)?ifpList:ifpList.size()));
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String createdDate = String.valueOf(sessionDTO.getSessionDate());
        ImtdIfpDetailsLocalServiceUtil.updateToIFPDetails(itemFamilyPlanMaster.getIfpModelSid(), userId, sessionId, createdDate, user, user, user, user, user);
        LOGGER.info("Ending saveDetailsList");
    }

    public void updateDetailsList(final List<IFPItemDTO> ifpList,
            final IfpModel itemFamilyPlanMaster) throws SystemException, PortalException{
        LOGGER.info("Entering updateDetailsList p1:"+((ifpList==null)?ifpList:ifpList.size()));
        try{
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
            final String createdDate = String.valueOf(sessionDTO.getSessionDate());
            ImtdIfpDetailsLocalServiceUtil.updateIFPDetails(itemFamilyPlanMaster.getIfpModelSid(), userId, sessionId, createdDate, StringUtils.EMPTY, null, null, null, null);
        }catch(Exception e){
            LOGGER.error(e);
        }
        LOGGER.info("Ending updateDetailsList");
    }
 private void updateFlagDetailsList(List<IFPItemDTO> ifpList,   final IfpModel itemFamilyPlanMaster) throws SystemException, PortalException{
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String createdDate = String.valueOf(sessionDTO.getSessionDate());
        ImtdIfpDetailsLocalServiceUtil.updateIFPDetails(itemFamilyPlanMaster.getIfpModelSid(), userId, sessionId,createdDate,"Flag", null, null, null, null);
    }
    /**
     * Method to format date - Date to date convert.
     *
     * @param date the date
     * @return the date
     * @throws ParseException the parse exception
     * @throws Exception the exception
     */
    public Date dateToDateConvert(final Date date) throws ParseException,Exception {

        LOGGER.info("convertDateToDate p1:date " + date);

        if(date==null){
            return null;
        }else{        
            final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
            LOGGER.info("return formatDate" + inputFormat.parse(inputFormat.format(date)));
            return inputFormat.parse(inputFormat.format(date));
        }
    }

    /**
     * Gets the IFP by cfpId.
     *
     * @param cfpId the cfpId
     * @return the IFP by cfpId
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public ItemFamilyplanMasterDTO getIFPById(final int cfpId,ItemFamilyplanMasterDTO itemDto) {
        try{
        LOGGER.info("getIFPById p1:id " + cfpId);
        Map<Integer, String> userMap= StplSecurity.getUserName();
        final IfpModel ifpMaster = DAO.getItemFamilyplanMasterBySystemId(cfpId);
        itemDto.setItemFamilyplanCategory(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(ifpMaster.getIfpCategory())));
        itemDto.setItemFamilyplanSystemId(ifpMaster.getIfpModelSid());
        itemDto.setItemFamilyplanStatus(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(ifpMaster.getIfpStatus())));
        itemDto.setItemFamilyplanType(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(ifpMaster.getIfpType())));
        itemDto.setCreatedBy(userMap.get(ifpMaster.getCreatedBy())==null ? StringUtils.EMPTY : userMap.get(ifpMaster.getCreatedBy()));
        itemDto.setModifiedBy(userMap.get(ifpMaster.getModifiedBy())==null ? StringUtils.EMPTY : userMap.get(ifpMaster.getModifiedBy()));
        if(!ifpMaster.getIfpDesignation().equals(StringUtils.EMPTY)&&!ifpMaster.getIfpDesignation().equals(null)){
            itemDto.setItemFamilyplanDesignation(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(ifpMaster.getIfpDesignation())));
        }
        itemDto.setItemFamilyplanEndDate(ifpMaster.getIfpEndDate());
        itemDto.setItemFamilyplanId(ifpMaster.getIfpId());
        itemDto.setItemFamilyplanName(ifpMaster.getIfpName());
        itemDto.setItemFamilyplanNo(ifpMaster.getIfpNo());
        itemDto.setRecordLockStatus(ifpMaster.getRecordLockStatus());
        itemDto.setParentItemFamilyplanId(ifpMaster.getParentIfpId());
        itemDto.setModifiedDate(ifpMaster.getModifiedDate());
        itemDto.setParentItemFamilyplanName(ifpMaster.getParentIfpName());
        itemDto.setInboundStatus(ifpMaster.getInboundStatus());
        itemDto.setItemFamilyplanStartDate(ifpMaster.getIfpStartDate());
        itemDto.setCreatedDate(ifpMaster.getCreatedDate());
      
        itemDto.setInternalNotes(ifpMaster.getInternalNotes());
        LOGGER.info("Ending getIFPById");
        return itemDto;
        }catch(Exception ex){
            LOGGER.error(ex);
            return null;
        }
    }


    /**
     * Gets the selected item list by passing id.
     *
     * @param ifpId the ifp id
     * @return the selected item list
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public IFPItemDTO getSelectedItemList(final int ifpId) throws PortalException, SystemException, Exception {
        LOGGER.info("getSelectedItemList method , p1:ifpId " + ifpId);
        final IFPItemDTO ifpDetails = new IFPItemDTO();
        final List<IfpDetails> list = DAO.getItemFamilyplanDetailsListByItemFamilyplanSystemId(ifpId);
        if (list != null) {
            final List<IFPItemDTO> itemDetailsList = new ArrayList<IFPItemDTO>();
            final List<ItemMasterDTO> itemListDTO = new ArrayList<ItemMasterDTO>();
            final IfpModel itemFamilyplanMaster = DAO.getItemFamilyplanMasterBySystemId(ifpId);

            for (int i = 0; i < list.size(); i++) {
                	final  IfpDetails details = list.get(i);

                final ItemMaster item = DAO.getItemMasterBysystemId(details.getItemMasterSid());
                final ItemMasterDTO itemDTOObj = new ItemMasterDTO();
                itemDTOObj.setItemSystemId(String.valueOf(item.getItemMasterSid()));
                itemDTOObj.setItemId(item.getItemId());
                itemDTOObj.setItemNo(item.getItemNo());
                itemDTOObj.setItemName(item.getItemName());
                itemDTOObj.setItemType(helperListUtil.getIdHelperDTOMap().get(item.getItemType()));
                itemDTOObj.setItemStatus(helperListUtil.getIdHelperDTOMap().get(item.getItemStatus()));
                itemDTOObj.setItemStartDate(item.getItemStartDate());
                itemDTOObj.setItemEndDate(item.getItemEndDate());
                itemDTOObj.setItemCategory(helperListUtil.getIdHelperDTOMap().get(item.getItemCategory()));
                itemDTOObj.setItemDesc(item.getItemDesc());
                itemDTOObj.setUniqueDate(String.valueOf(new Date() + "`" + i));
                        final  IFPItemDTO ifp=new IFPItemDTO(details.getIfpDetailsSid(), String.valueOf(details.getItemMasterSid()),
                        String.valueOf(itemFamilyplanMaster.getIfpId()),
                        String.valueOf(itemFamilyplanMaster
                                .getIfpModelSid()), String
                        .valueOf(itemFamilyplanMaster
                                .getIfpName()),
                        String.valueOf(itemFamilyplanMaster.getIfpNo()), itemDTOObj.getItemId(),
                        itemDTOObj.getItemNo(), itemDTOObj.getItemName(),
                        String.valueOf(itemDTOObj.getItemStatus()), String.valueOf(item
                                .getPrimaryUom()), item
                        .getPackageSize(), itemDTOObj.getItemStartDate(),
                        itemDTOObj.getItemEndDate(),
                        helperListUtil.getIdHelperDTOMap().get(details.getItemIfpAttachedStatus()),
                        details
                        .getStartDate(), details.getEndDate(), String.valueOf(item
                                .getTherapeuticClass()), String.valueOf(item.getBrandMasterSid()),
                                String.valueOf(item.getForm()),CommonUtils.getDescription( item.getStrength()), details
                        .getItemIfpAttachedDate(), true, dateToDateConvert(details.getCreatedDate()), String.valueOf(details.getCreatedBy()),
                        itemDTOObj.getUniqueDate());
                itemDetailsList.add(ifp);
                itemListDTO.add(itemDTOObj);
            }


            ifpDetails.setItemDetailsList(itemDetailsList);
            ifpDetails.setItemsList(itemListDTO);
        }
        LOGGER.info("Return IfpItemDTO");
        return ifpDetails;
    }

    /**
     * Delete ifp master by id.
     *
     * @param itemSystemId the item system id
     * @return the item familyplan master
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public IfpModel deleteIFPMasterById(final int itemSystemId) throws SystemException, PortalException {

        IfpModel item ;

       
        LOGGER.info("Entering deleteIFPMasterById with systemid = " + itemSystemId);
        item = DAO.getItemFamilyplanMasterBySystemId(itemSystemId);
        item.setInboundStatus(ConstantsUtils.INBOUND_STATUS_D);
        DAO.updateItemfamilyplanMaster(item);
        if (item.getIfpStatus() > 0) {
            UDCIncrementCheck.decrement(String.valueOf(item.getIfpStatus()), UIUtils.IFP_STATUS);
        }

        if (item.getIfpType() > 0) {
            UDCIncrementCheck.decrement(String.valueOf(item.getIfpType()), UIUtils.IFP_TYPE);
        }

        if (item.getIfpCategory() > 0) {
            UDCIncrementCheck.decrement(String.valueOf(item.getIfpCategory()), UIUtils.IFP_CATEGORY);
        }

        if (item.getIfpDesignation() != null) {
            UDCIncrementCheck.decrement(item.getIfpDesignation(), UIUtils.IFP_DESIGNATION);
        }
        deleteIFPDetails(itemSystemId);

        LOGGER.info("return IfpModel");
        return item;

        
     
    }

    /**
     * Gets the item type from Helper DTO.
     *
     * @param listType the list type
     * @return the item type
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public List<HelperDTO> getItemType(final String listType) throws SystemException, PortalException {

        
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();

        LOGGER.info("Entering getItemType p1: " + listType);
        final List<HelperTable> list = DAO.getHelperTableDetailsByListName(listType);


        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                	final   HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));
            }
        }
        LOGGER.info("return List ,size -" + helperList.size());
        Collections.sort(helperList);

        return helperList;
    }

    /**
     * Method to Convert date to string.
     *
     * @param date the date
     * @return the string
     * @throws ParseException the parse exception
     */
    public String convertDateToString(final String date) throws ParseException {
        LOGGER.info("ConvertDateToString p1:date " + date);
        final DateFormat formatter = new SimpleDateFormat(ConstantsUtils.SIMPLE_DATE_FORMAT, Locale.getDefault());

      
        final Date sDate = (Date) formatter.parse(date);

        final Calendar cal = Calendar.getInstance();
        cal.setTime(sDate);
            final  String formatedDate = cal.get(Calendar.DATE) + "/"
                + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);

        LOGGER.info("Return formatedDate " + formatedDate);
        return formatedDate;

        
    }

    /**
     * Formate the date - Convert date to date.
     *
     * @param date the date
     * @return the date
     * @throws ParseException the parse exception
     */
    public Date convertDateToDate(final Date date) throws ParseException {

        LOGGER.info("convertDateToDate p1:date " + date);
        final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT, Locale.getDefault());
        LOGGER.info("return formatDate" + inputFormat.parse(inputFormat.format(date)));
        return inputFormat.parse(inputFormat.format(date));

       

    }

    /**
     * Format the date -Convert date to date1.
     *
     * @param date the date
     * @return the date
     * @throws ParseException the parse exception
     */
    public Date convertDateToDate1(final Date date) throws ParseException {
        LOGGER.info("convertDateToDate1 p1:date " + date);

        final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT, Locale.getDefault());
        LOGGER.info("return formatDate" + inputFormat.parse(inputFormat.format(date)));
        return inputFormat.parse(inputFormat.format(date));

        

    }

    /**
     * Convert the String to date.
     *
     * @param dateString the date string
     * @return the date
     * @throws ParseException the parse exception
     */
    public Date stringToDateFormatter(final String dateString) throws ParseException {
        LOGGER.info("StringToDate p1:date " + dateString);
        final DateFormat inputFormat = new SimpleDateFormat(
                ConstantsUtils.SIMPLE_DATE_FORMAT, Locale.getDefault());

        
        final Date date = inputFormat.parse(dateString);

        LOGGER.info("return inputFormat" + date);
        return date;

        

    }

    /**
     * Convert the String date into date format.
     *
     * @param dateString the date string
     * @return the date
     * @throws ParseException the parse exception
     */
    public Date stringToDateFormatter2(final String dateString) throws ParseException {
        LOGGER.info("StringToDateForIden p1:date " + dateString);
        final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.DMY_FORMAT, Locale.getDefault());

        	final    Date date = inputFormat.parse(dateString);
        LOGGER.info("return inputFormat" + date);
        return date;


    }

    /**
     * Convert date to string.
     *
     * @param date the date
     * @return the string
     */
    public String convertDateToString(final Date date) {

        LOGGER.info("convertDateToString date " + date);
        final DateFormat outputFormat = new SimpleDateFormat(ConstantsUtils.DMYHMS_FORMAT, Locale.getDefault());
        final    String outputString = outputFormat.format(date);
        LOGGER.info("return outputString" + outputString);
        return outputString;

    }

    /**
     * Gets the drop down list in form.
     *
     * @param listName the list name
     * @return the drop down list
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public List<HelperDTO> getDropDownList(final String listName) throws SystemException, PortalException {
        LOGGER.info("getDropDownList p1: " + listName);

        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();

        final List<HelperTable> list = DAO.getHelperTableDetailsByListName(listName);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));

            }
        }

        LOGGER.info("return helperList " + helperList.size());
        Collections.sort(helperList);
        return helperList;
    }
    
    public List<HelperDTO> getBrandDropDown() throws SystemException, PortalException {

        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        DynamicQuery dynamicQuery  = DynamicQueryFactoryUtil
                        .forClass(BrandMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.BRAND_NAME));
        final List<BrandMaster> list = BrandMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final BrandMaster brandMaster = (BrandMaster) list.get(i);
                helperList.add(new HelperDTO(brandMaster.getBrandMasterSid(), brandMaster
                        .getBrandName()));

            }
        }

        LOGGER.info("return helperList " + helperList.size());
        Collections.sort(helperList);
        return helperList;
    }

    /**
     * Gets the search count by passing the search criteria.
     *
     * @param searchItemForm the search item form
     * @param search
     * @return the search count
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public int getSearchCount(final ErrorfulFieldGroup searchItemForm, final BeanSearchCriteria sc) throws SystemException, PortalException {
        LOGGER.info("Entering getSearchCount");
       
        String ifpId;
        String ifpNo;
        String ifpName;
        int ifpType=0;
        int ifpStatus=0;
        String itemId;
        String itemNo;
        String itemName;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

       try{
            Map<String, Object> parameters = new HashMap<String, Object>();
            if (searchItemForm.getField(ConstantsUtils.TEXT1).getValue() == null) {

                ifpId = StringUtils.EMPTY;
            } else {

                ifpId = searchItemForm.getField(ConstantsUtils.TEXT1).getValue().toString().trim();
            }
                
           
            if (searchItemForm.getField(ConstantsUtils.TEXT2).getValue() == null) {

                ifpNo = StringUtils.EMPTY;
            } else {


                ifpNo = searchItemForm.getField(ConstantsUtils.TEXT2).getValue().toString().trim();
            }

           
            if (searchItemForm.getField(ConstantsUtils.TEXT3).getValue() == null) {

                ifpName = StringUtils.EMPTY;
            } else {

                ifpName = searchItemForm.getField(ConstantsUtils.TEXT3).getValue().toString().trim();
            }

           
            if (searchItemForm.getField(ConstantsUtils.COMBO2).getValue() == null || ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO2).getValue())) {

                ifpType = 0;
            } else {
                 final HelperDTO helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO2).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

               ifpType  =  Integer.valueOf(helperDTO.getId());
             
            }
            }

            
            if (searchItemForm.getField(ConstantsUtils.COMBO1).getValue() == null || ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO2).getValue())) {

                ifpStatus = 0;
            } else {
                
                final HelperDTO helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO1).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

               ifpStatus  =  Integer.valueOf(helperDTO.getId());
            }
            }
            
            
            if (searchItemForm.getField(ConstantsUtils.TEXT6)!=null && searchItemForm.getField(ConstantsUtils.TEXT6).getValue()!=null) {
            
            itemId = searchItemForm.getField(ConstantsUtils.TEXT6).getValue()
                    .toString().trim();
            
        } else {
            itemId = StringUtils.EMPTY;
        }

        if (searchItemForm.getField(ConstantsUtils.TEXT7)!=null && searchItemForm.getField(ConstantsUtils.TEXT7).getValue()!=null) {
            
            itemNo = searchItemForm.getField(ConstantsUtils.TEXT7).getValue()
                    .toString().trim();
            
        } else {
            itemNo = StringUtils.EMPTY;
            
        }

        if (searchItemForm.getField(ConstantsUtils.TEXT8)!=null && searchItemForm.getField(ConstantsUtils.TEXT8).getValue()!=null) {
            
            itemName = searchItemForm.getField(ConstantsUtils.TEXT8).getValue()
                    .toString().trim();
            
        } else {
            itemName = StringUtils.EMPTY;
        }
            
            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(IfpModel.class);

            // Added to check INBOUND_STATUS is A. ETL Soft delete data should not be included, where INBOUND_STATUS is D
            ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));
           
            if (StringUtils.isNotBlank(ifpId)) {
                ifpId = ifpId.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
              
                ifpDynamicQuery.add(RestrictionsFactoryUtil.like(
                        ConstantsUtils.IFP_MODEL_ID, ifpId));
            }
            if (StringUtils.isNotBlank(ifpNo)) {
                ifpNo = ifpNo.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
                ifpDynamicQuery.add(RestrictionsFactoryUtil.like(
                        ConstantsUtils.IFP_MODEL_NO, ifpNo));
            }
            if (StringUtils.isNotBlank(ifpName)) {
                ifpName = ifpName.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
                ifpDynamicQuery.add(RestrictionsFactoryUtil.like(
                        ConstantsUtils.IFP_MODEL_NAME, ifpName));
            }
            if (StringUtils.isNotBlank(itemId)) {
                itemId = itemId.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
             
            }
            if (StringUtils.isNotBlank(itemNo)) {
                itemNo = ifpName.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
                
            }
            if (StringUtils.isNotBlank(itemName)) {
                itemName = itemName.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
                
            }
            if (ifpType!=0) {
                List<HelperDTO> list = getDropDownList(UIUtils.IFP_TYPE);
                for (HelperDTO list1 : list) {
                if (list1.getId()==ifpType){
                        int sysId = list1.getSystemId();
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.IFP_MODEL_TYPE, sysId));
                    }
                }

            }

           if (ifpStatus!=0) {
                List<HelperDTO> list = getDropDownList(UIUtils.STATUS);
                for (HelperDTO list1 : list) {
                if (list1.getId()==ifpStatus){
                        int sysId = list1.getSystemId();
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.IFP_MODEL_STATUS, sysId));
                    }
                }

            }
           
           if (sc != null && sc.getFilters() != null) {
               for (Container.Filter filter : sc.getFilters()) {
                   if (filter instanceof SimpleStringFilter) {
                       SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                       String filterString = stringFilter.getFilterString();
                       parameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);
                   } else if (filter instanceof Between) {
                       Between betweenFilter = (Between) filter;
                       
                           Date startValue = (Date) betweenFilter.getStartValue();
                           Date endValue = (Date) betweenFilter.getEndValue();
                           
                           parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(startValue)));
                           
                           parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(endValue)));
                      

                   } else if (filter instanceof Compare) {

                       Compare compare = (Compare) filter;
                       
                       Compare.Operation operation = compare.getOperation();
                       if (ConstantsUtils.IFP_SYSTEM_ID.equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
                           String value = String.valueOf(compare.getValue());
                           if (operation.GREATER.toString().equals(operation.name())) {
                               parameters.put(compare.getPropertyId() + ConstantsUtils.FROM, value);
                           } else if (operation.LESS.toString().equals(operation.name())) {
                               parameters.put(compare.getPropertyId() + ConstantsUtils.TO, value);
                           } else if (operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
                               parameters.put(compare.getPropertyId() + ConstantsUtils.EQUAL, value);
                           }
                       } else {
                           Date value = (Date) compare.getValue();
                           if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                               parameters.put(compare.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(value)));
                           } else {
                               parameters.put(compare.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(value)));
                           }
                       }

                   } else if (filter instanceof And) {
                       
                    And stringFilter = (And) filter;
                    
                    Collection<Filter> value = stringFilter.getFilters();
                    for (Filter filter1 : value) {
                        Object propertyId = StringUtils.EMPTY;
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            propertyId = less.getPropertyId();
                            parameters.put(less.getPropertyId() + ConstantsUtils.TO, String.valueOf(less.getValue()));
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            propertyId = greater.getPropertyId();
                            parameters.put(greater.getPropertyId() + ConstantsUtils.FROM, String.valueOf(greater.getValue()));
                        }
                    }
                }
               }
           }
            List resultList =ImtdIfpDetailsLocalServiceUtil.getIFPSearchList(ifpId, ifpNo, ifpName, ifpType, ifpStatus,itemId,  itemNo,  itemName,  0,  0,  null, null,true, parameters);
           
            
           return resultList.size();
       }catch(Exception e)
       {
LOGGER.error(e);
            return 0;
        }
    }

    /**
     * Gets the m count when the count method is .
     *
     * @return the total count
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public int getTotalCount() throws SystemException, PortalException {

       
        LOGGER.info("getTotalCount --method ");
        return DAO.getItemFamilyplanMasterTotalCount();

    }

    
    public static String getUseName(){
      String userId =  String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
      try {     
      
        
         User createdUser    = UserLocalServiceUtil.getUser(Long.valueOf(userId));
        
             return createdUser.getFullName();
         } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(IFPLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(IFPLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
      return StringUtils.EMPTY;
    }
    
    
    
    
    /**
     * Gets the all items.
     *
     * @param start the start
     * @param end the end
     * @return the all items
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public List<ItemFamilyplanSearchDTO> getAllItems(final int start, final int end) throws SystemException, PortalException, Exception {

        LOGGER.info("getAllItems method ");
        return getCustomizedModel(DAO.getItemFamilyplanMasterByLimit(start, end));

    }


    /**
     * Gets the dao.
     *
     * @return the dao
     */
    public ItemFamilyplanDAO getDao() {
        return DAO;
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * Gets the item for ifp.
     *
     * @param searchField the search field
     * @param value the value
     * @return the item for ifp
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @SuppressWarnings({"unchecked", ConstantsUtils.NULL})
    public static int getItemForIFPCount(final String searchField,  final String value, final BeanSearchCriteria sc, final SessionDTO sessionDTO){
        if (searchField!=null && value !=null) {
        final  ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
            List<Integer> resultList = new ArrayList<Integer>();
            LOGGER.info("Entering getItemForIFPCount ");
            map.put(ConstantsUtils.ITEMS_ID, ConstantsUtils.ITEM_ID_CAPS);
            map.put(ConstantsUtils.ITEMS_NO, ConstantsUtils.ITEM_NO_CAPS);
            map.put(ConstantsUtils.ITEM_NAMES, ConstantsUtils.ITEM_NAME_CAPS);
            map.put(ConstantsUtils.ITEMSTATUS, ConstantsUtils.ITEM_STATUS_CAPS);
            map.put(ConstantsUtils.UOM, ConstantsUtils.PRIMARY_UOM);
            map.put(ConstantsUtils.THERAPEUTIC_CLASS1, ConstantsUtils.THERAPEUTIC_CLASS_CAPS);
            map.put(ConstantsUtils.BRAND1, ConstantsUtils.BRAND_MASTER_SID_CAPS);
            map.put(ConstantsUtils.FORM1, ConstantsUtils.FORM_CAPS);
            map.put(ConstantsUtils.STRENGTH, ConstantsUtils.STRENGTH_UPPERCASE);

            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
            final String query = value.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
            if (ConstantsUtils.BRAND_MASTER_SID_CAPS.equals(map.get(searchField)) && !ConstantsUtils.PERCENCTAGE.equals(query)) {

                try {
                    final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);
                    dynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.BRAND_NAME, query));
                    dynamicQuery.setProjection(ProjectionFactoryUtil.property("brandMasterSid"));
                    resultList = BrandMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
                } catch (SystemException ex) {
                    java.util.logging.Logger.getLogger(IFPLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        Map<String, Object> parameters = new HashMap<String, Object>();    
        getParameterList(parameters, sc);    
        Object object=ImtdIfpDetailsLocalServiceUtil.updateOperation(userId, sessionId, userId, sessionId, map.get(searchField), query, resultList, userId, parameters);
            return Integer.parseInt(String.valueOf(object));
        }else{
            return 0;
        }

    }

    @SuppressWarnings({"unchecked", ConstantsUtils.NULL})
    public static List<ItemMasterDTO> getItemForIFPResults(final int start, final int end, final String searchField, final String value, final List<OrderByColumn> orderByColumns, final BeanSearchCriteria sc, final SessionDTO sessionDTO) throws Exception {
        final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
        List<Integer> resultList = new ArrayList<Integer>();
        LOGGER.info("Entering getItemForIFP ");
        map.put(ConstantsUtils.ITEMS_ID, ConstantsUtils.ITEM_ID_CAPS);
        map.put(ConstantsUtils.ITEMS_NO, ConstantsUtils.ITEM_NO_CAPS);
        map.put(ConstantsUtils.ITEM_NAMES, ConstantsUtils.ITEM_NAME_CAPS);
        map.put(ConstantsUtils.ITEMSTATUS, ConstantsUtils.ITEM_STATUS_CAPS);
        map.put(ConstantsUtils.UOM, ConstantsUtils.PRIMARY_UOM);
        map.put(ConstantsUtils.THERAPEUTIC_CLASS1, ConstantsUtils.THERAPEUTIC_CLASS_CAPS);
        map.put(ConstantsUtils.BRAND1, ConstantsUtils.BRAND_MASTER_SID_CAPS);
        map.put(ConstantsUtils.FORM1, ConstantsUtils.FORM_CAPS);
        map.put(ConstantsUtils.STRENGTH, ConstantsUtils.STRENGTH_UPPERCASE);
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        List<Object[]> itemMasterList;
        List<String> list=new ArrayList<String>(2);
        list.add(userId);
        list.add(sessionId);
        final String query = value.replace(CommonUtils.CHAR_ASTERISK,
                CommonUtils.CHAR_PERCENT);
        if (ConstantsUtils.BRAND_MASTER_SID_CAPS.equals(map.get(searchField)) && !ConstantsUtils.PERCENCTAGE.equals(query)) {

            try {
                final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);
                dynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.BRAND_NAME, query));
                dynamicQuery.setProjection(ProjectionFactoryUtil.property("brandMasterSid"));
                resultList = BrandMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
            } catch (SystemException ex) {
                java.util.logging.Logger.getLogger(IFPLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String column = ConstantsUtils.ITEM_NO_CAPS;
        String orderBy = ConstantsUtils.ASC;

        for (final Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
            if (ConstantsUtils.ITEM_ID.equals(orderByColumn.getName())) {
                column = ConstantsUtils.ITEM_ID_CAPS;
            }
           
           else if (ConstantsUtils.ITEM_NO.equals(orderByColumn.getName())) {
                column = ConstantsUtils.ITEM_NO_CAPS;
            } else if (ConstantsUtils.ITEM_NAME.equals(orderByColumn.getName())) {
                column = ConstantsUtils.ITEM_NAME_CAPS;
            } else if ("displayItemStatus".equals(orderByColumn.getName())) {
                column = ConstantsUtils.ITEM_STATUS_CAPS;
            } else if ("displayForm".equals(orderByColumn.getName())) {
                column = ConstantsUtils.FORM_CAPS;
            } else if ("displayPackageSize".equals(orderByColumn.getName())) {
                column = "PACKAGE_SIZE";
            }
            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                orderBy = ConstantsUtils.ASC;
            } else {
                orderBy = ConstantsUtils.DESC;
            }
        }
        Map<String, Object> parameters = new HashMap<String, Object>();    
        getParameterList(parameters, sc);    
        itemMasterList = ImtdIfpDetailsLocalServiceUtil.getItemLazyList(start, end, list, map.get(searchField), query, resultList, column, orderBy, null, null, parameters);
        return getCustomizedData(itemMasterList);

    }

    /**
     * Load the temp table with selected item list by passing id.
     *
     * @param ifpId the ifp id
     * @return the selected item list
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void loadTempTable(final int ifpId) throws PortalException, SystemException, Exception {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String tempCreatedDate = String.valueOf(sessionDTO.getSessionDate());
        ImtdIfpDetailsLocalServiceUtil.insertTempIfpDetailsInEdit(userId, sessionId, tempCreatedDate, null, String.valueOf(ifpId), null, null, null);
    }

    /**
     * Load the temp table with selected item list by passing id.
     *
     * @param searchField
     * @param value
     * @param ifpId the ifp id
     */
    public void addAllToTempTable(final String searchField, final String value){
        final String sysId = String.valueOf(sessionDTO.getSystemId());
        final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
        LOGGER.info("Entering getItemForIFP ");
        map.put(ConstantsUtils.ITEMS_ID, ConstantsUtils.ITEM_ID_CAPS);
        map.put(ConstantsUtils.ITEMS_NO, ConstantsUtils.ITEM_NO_CAPS);
        map.put(ConstantsUtils.ITEM_NAMES, ConstantsUtils.ITEM_NAME_CAPS);
        map.put(ConstantsUtils.ITEMSTATUS, ConstantsUtils.ITEM_STATUS_CAPS);
        map.put(ConstantsUtils.UOM, ConstantsUtils.PRIMARY_UOM);
        map.put(ConstantsUtils.THERAPEUTIC_CLASS1, ConstantsUtils.THERAPEUTIC_CLASS_CAPS);
        map.put(ConstantsUtils.BRAND1, ConstantsUtils.BRAND_NAME_DB);
        map.put(ConstantsUtils.FORM1, ConstantsUtils.FORM_CAPS);
        map.put(ConstantsUtils.STRENGTH, ConstantsUtils.STRENGTH_UPPERCASE);
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String tempCreatedDate = String.valueOf(sessionDTO.getSessionDate());
        final String searchValue = value.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        ImtdIfpDetailsLocalServiceUtil.insertTempIfpDetailsInADD(userId, sessionId, tempCreatedDate, null, map.get(searchField), searchValue, null, sysId);
    }

    /**
     * To get the result for lazy load
     * @param itemId
     * @return
     * @throws SystemException
     */
    public int tempTableItemValidation(final Integer itemsystemid) throws SystemException{
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        int user=Integer.valueOf(userId);
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(ImtdIfpDetails.class);

        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USER_SID, user));

        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, sessionId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_MASTER_SID, itemsystemid));
        return Integer.parseInt(String.valueOf(ImtdIfpDetailsLocalServiceUtil.dynamicQueryCount(ifpDynamicQuery)));
    }

    /**
     * To get the result for lazy load
     * @param itemId
     * @return
     * @throws SystemException
     */
    public ImtdIfpDetails tempTableItem(final Integer itemsystemid) throws SystemException{
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
       int user=Integer.valueOf(userId);
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(ImtdIfpDetails.class);

        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USER_SID, user));

        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, sessionId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_MASTER_SID, itemsystemid));
        return (ImtdIfpDetails)ImtdIfpDetailsLocalServiceUtil.dynamicQuery(ifpDynamicQuery).get(0);
    }

    /**
     * Load the temp table with selected item list by passing id.
     *
     * @param itemId
     * @param ifpId the ifp id
     * @param itemIds
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void addToTempTable(final ItemMasterDTO itemId, final int ifpId) throws PortalException, SystemException, Exception {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        if (tempTableItemValidation(Integer.parseInt(itemId.getItemSystemId())) == ConstantsUtils.ZERO_INT) {
           
            final ImtdIfpDetails itemDTOObj = ImtdIfpDetailsLocalServiceUtil.createImtdIfpDetails(0);
            itemDTOObj.setItemMasterSid(Integer.parseInt(itemId.getItemSystemId()));
            itemDTOObj.setItemId(itemId.getItemId());
            itemDTOObj.setItemNo(itemId.getItemNo());
            itemDTOObj.setItemName(itemId.getItemName());
            itemDTOObj.setItemStatus(((com.stpl.ifs.util.HelperDTO)itemId.getItemStatus()).getId());
            itemDTOObj.setItemStartDate(itemId.getItemStartDate());
            itemDTOObj.setItemEndDate(itemId.getItemEndDate());
            itemDTOObj.setItemForm(String.valueOf(itemId.getForm().getId()));
            if(String.valueOf(itemId.getPackageSize())!=ConstantsUtils.ZERO&&String.valueOf(itemId.getPackageSize())!=ConstantsUtils.SELECT_ONE ){
            itemDTOObj.setItemPackageSize(String.valueOf(itemId.getPackageSize()));
            }
            itemDTOObj.setItemPrimaryUom(String.valueOf(itemId.getPrimaryUom()));
            itemDTOObj.setItemTherapeuticClass(String.valueOf(itemId.getTherapeuticClass().getId()));
            itemDTOObj.setItemStrength(String.valueOf(itemId.getStrength().getId()));
            itemDTOObj.setItemBrand(itemId.getBrandId());
            itemDTOObj.setIfpModelSid(ifpId);
            itemDTOObj.setCheckBox(false);
            itemDTOObj.setIfpDetailsSid(0);
            itemDTOObj.setItemDesc(itemId.getItemDesc());
            itemDTOObj.setUsersSid(Integer.parseInt(userId));
            itemDTOObj.setSessionId(sessionId);
            itemDTOObj.setImtdCreateddate(new Date());
            itemDTOObj.setIfpDetailsCreatedBy(userId);
            itemDTOObj.setIfpDetailsCreatedDate(new Date());
            itemDTOObj.setOperation("A");
            itemDTOObj.setModifiedDate(new Date());
            itemDTOObj.setCreatedDate(new Date());
            ImtdIfpDetailsLocalServiceUtil.addImtdIfpDetails(itemDTOObj);
        } else {
            final ImtdIfpDetails tempdtls=tempTableItem(Integer.parseInt(itemId.getItemSystemId()));
            tempdtls.setIfpDetailsSid(0);
            tempdtls.setIfpDetailsCreatedBy(userId);
            tempdtls.setIfpDetailsCreatedDate(new Date());
            tempdtls.setIfpDetailsModifiedBy(userId);
            tempdtls.setIfpDetailsModifiedDate(new Date());
            tempdtls.setIfpDetailsEndDate(null);
            tempdtls.setIfpDetailsStartDate(null);
            tempdtls.setIfpDetailsAttachedStatus(0);
            tempdtls.setCheckBox(false);
            tempdtls.setOperation("A");
            ImtdIfpDetailsLocalServiceUtil.updateImtdIfpDetails(tempdtls);

        }
    }

    /**
     * Remove all the records from the Temp Table.
     *
     * @param itemIds
     * @param removeAllFlag
     * @throws SystemException
     */
    public void removeAllFromTempTable(Boolean removeAllFlag) throws SystemException, PortalException{
        LOGGER.info("Entering removeAllFromTempTable()");
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String tempCreatedDate = String.valueOf(sessionDTO.getSessionDate());
        if(removeAllFlag){
            ImtdIfpDetailsLocalServiceUtil.deleteAll(userId, sessionId, tempCreatedDate, "All", null, null, null, null);
        }else{
            ImtdIfpDetailsLocalServiceUtil.deleteAll(userId, sessionId, tempCreatedDate, null, null, null, null, null);
        }
        LOGGER.info("Ending removeAllFromTempTable()");
    }

    /**
     * Remove the record from the Temp Table.
     *
     * @param itemId
     * @param itemIds
     * @throws SystemException
     */
    public void removeFromTempTable(final TempItemDTO itemId) throws SystemException, PortalException{
         ImtdIfpDetails item=ImtdIfpDetailsLocalServiceUtil.getImtdIfpDetails(Integer.parseInt(itemId.getTempItemSystemId()));
        item.setOperation(ConstantsUtils.D);
        item.setCheckBox(false);
        ImtdIfpDetailsLocalServiceUtil.updateImtdIfpDetails(item);

    }

    /**
     * To get the count of temp table.
     *
     * @param sc
     * @return
     * @throws SystemException
     */
    public int tempTableCount(BeanSearchCriteria sc) throws SystemException{
       try{
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
           int user=Integer.valueOf(userId);
            final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(ImtdIfpDetails.class);

            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USER_SID, user));

            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, sessionId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.D));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.F));
            Map<String, Object> parameters = new HashMap<String, Object>();    
            getParameterList(parameters, sc);
              if ((parameters.get(ConstantsUtils.ITEM_ID) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_ID))))) {
               ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_ID,ConstantsUtils.PERCENCTAGE+String.valueOf(parameters.get(ConstantsUtils.ITEM_ID))+ConstantsUtils.PERCENCTAGE)); 
            }
            if ((parameters.get(ConstantsUtils.ITEM_NO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_NO))))) {
               ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_NO,ConstantsUtils.PERCENCTAGE+String.valueOf(parameters.get(ConstantsUtils.ITEM_NO))+ConstantsUtils.PERCENCTAGE)); 
            }
            if ((parameters.get(ConstantsUtils.ITEM_NAME) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_NAME))))) {
               ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_NAME,ConstantsUtils.PERCENCTAGE+String.valueOf(parameters.get(ConstantsUtils.ITEM_NAME))+ConstantsUtils.PERCENCTAGE)); 
            }
            if ((parameters.get(ConstantsUtils.ITEM_DESC) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_DESC))))) {
               ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_DESC,ConstantsUtils.PERCENCTAGE+String.valueOf(parameters.get(ConstantsUtils.ITEM_DESC))+ConstantsUtils.PERCENCTAGE)); 
            }
            if ((parameters.get(ConstantsUtils.ITEM_STATUS) != null && !StringUtils.isBlank(String.valueOf(parameters.get(ConstantsUtils.ITEM_STATUS))))) {
               ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_STATUS, Integer.valueOf(String.valueOf(parameters.get(ConstantsUtils.ITEM_STATUS)))));
            }
            if ((parameters.get(ConstantsUtils.FORM1) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.FORM1))))) {
               ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_FORM, String.valueOf(parameters.get(ConstantsUtils.FORM1))));
            }
            if ((parameters.get(ConstantsUtils.STRENGTH) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.STRENGTH))))) {
               ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_STRENGTH, String.valueOf(parameters.get(ConstantsUtils.STRENGTH))));
            }
            if ((parameters.get("therepeuticClass") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("therepeuticClass"))))) {
               ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_THERAPAUTICCLASS, String.valueOf(parameters.get("therepeuticClass"))));
            }
            if ((parameters.get(ConstantsUtils.BRAND) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.BRAND))))) {
               ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_BRAND,String.valueOf(parameters.get(ConstantsUtils.BRAND)))); 
            }
           int tempCount=Integer.parseInt(String.valueOf(ImtdIfpDetailsLocalServiceUtil.dynamicQueryCount(ifpDynamicQuery)));
           return tempCount;
    }catch(Exception e)
    {

            return 0;
        }
    }
    /**
     * To get the result for lazy load
     * @param start
     * @param end
     * @return
     * @throws SystemException
     */
    public List<TempItemDTO> tempTableResults(int start, int end, List<OrderByColumn> list, BeanSearchCriteria sc) throws SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        int user = Integer.valueOf(userId);
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(ImtdIfpDetails.class);

        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USER_SID, user));

        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, sessionId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.D));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.F));
        ifpDynamicQuery.setLimit(start, end);
        ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_NO));
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_NAME));
        projList.add(ProjectionFactoryUtil.property("imtdIfpDetailsSid"));
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_STATUS));
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_FORM));
        projList.add(ProjectionFactoryUtil.property("itemPackageSize"));
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_STRENGTH));
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_THERAPAUTICCLASS));
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_BRAND));
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_DESC));
        ifpDynamicQuery.setProjection(projList);
        Map<String, Object> parameters = new HashMap<String, Object>();    
            getParameterList(parameters, sc);
              if ((parameters.get(ConstantsUtils.ITEM_ID) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_ID))))) {
               ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_ID,ConstantsUtils.PERCENCTAGE+String.valueOf(parameters.get(ConstantsUtils.ITEM_ID))+ConstantsUtils.PERCENCTAGE)); 
            }
            if ((parameters.get(ConstantsUtils.ITEM_NO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_NO))))) {
               ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_NO,ConstantsUtils.PERCENCTAGE+String.valueOf(parameters.get(ConstantsUtils.ITEM_NO))+ConstantsUtils.PERCENCTAGE)); 
            }
            if ((parameters.get(ConstantsUtils.ITEM_NAME) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_NAME))))) {
               ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_NAME,ConstantsUtils.PERCENCTAGE+String.valueOf(parameters.get(ConstantsUtils.ITEM_NAME))+ConstantsUtils.PERCENCTAGE)); 
            }
            if ((parameters.get(ConstantsUtils.ITEM_DESC) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_DESC))))) {
               ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_DESC,ConstantsUtils.PERCENCTAGE+String.valueOf(parameters.get(ConstantsUtils.ITEM_DESC))+ConstantsUtils.PERCENCTAGE)); 
            }
            if ((parameters.get(ConstantsUtils.ITEM_STATUS) != null && !StringUtils.isBlank(String.valueOf(parameters.get(ConstantsUtils.ITEM_STATUS))))) {
               ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_STATUS, Integer.valueOf(String.valueOf(parameters.get(ConstantsUtils.ITEM_STATUS)))));
            }
            if ((parameters.get(ConstantsUtils.FORM1) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.FORM1))))) {
               ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_FORM, String.valueOf(parameters.get(ConstantsUtils.FORM1))));
            }
            if ((parameters.get(ConstantsUtils.STRENGTH) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.STRENGTH))))) {
                ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_STRENGTH, String.valueOf(parameters.get(ConstantsUtils.STRENGTH))));
            }
            if ((parameters.get("therepeuticClass") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("therepeuticClass"))))) {
               ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_THERAPAUTICCLASS, String.valueOf(parameters.get("therepeuticClass"))));
            }
            if ((parameters.get(ConstantsUtils.BRAND) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.BRAND))))) {
               ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_BRAND,String.valueOf(parameters.get(ConstantsUtils.BRAND)))); 
            }
        for (final Iterator<OrderByColumn> iterator = list.iterator(); iterator
                .hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();

            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                ifpDynamicQuery.addOrder(OrderFactoryUtil.asc(orderByColumn
                        .getName()));
            } else {
                ifpDynamicQuery.addOrder(OrderFactoryUtil.desc(orderByColumn
                        .getName()));
            }
        }
        return getCustomizedDTO(ImtdIfpDetailsLocalServiceUtil.dynamicQuery(ifpDynamicQuery),"selected");

    }

    /**
     * To get the CustomizedDTO
     * @param result
     * @return
     */
    public static List<TempItemDTO> getCustomizedDTO(List<Object[]> result,String selected)  {
        List<TempItemDTO> resultlist =new ArrayList<TempItemDTO>();
        try{
        for (Iterator<Object[]> temp = result.iterator(); temp.hasNext();) {
            Object[] item = temp.next();
                TempItemDTO dto=new TempItemDTO();
            dto.setItemNo(String.valueOf(item[0]));
            dto.setItemName(String.valueOf(item[1]));
            dto.setTempItemSystemId(String.valueOf(item[2]));
            if(selected!=null){
            dto.setItemStatus(getDescription((Integer)item[3]));
            dto.setForm(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(item[4]))));
            dto.setDisplayForm(dto.getForm().getDescription());
            if(item[5]!=null && StringUtils.isNotBlank(String.valueOf(item[5]))&&!ConstantsUtils.SELECT_ONE.equals(String.valueOf(item[5]))&& !ConstantsUtils.ZERO.equals(String.valueOf(item[5]))){
            dto.setPackageSize(String.valueOf(item[5]));
            }
            dto.setStrengthID(Integer.valueOf(String.valueOf(item[6])));
            dto.setStrength(String.valueOf(helperListUtil.getIdHelperDTOMap().get(dto.getStrengthID())));
            if(item[7]!=null && StringUtils.isNotBlank(String.valueOf(item[7]))&&!ConstantsUtils.SELECT_ONE.equals(String.valueOf(item[7]))&& !ConstantsUtils.ZERO.equals(String.valueOf(item[7])) && !ConstantsUtils.NULL.equals(String.valueOf(item[7]))){
            dto.setTherapeuticClass(String.valueOf(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(item[7])))));
            dto.setTherapeuticClassId(Integer.valueOf(String.valueOf(item[7])));
            }
            dto.setBrand(item[8] == null || String.valueOf(item[8]).equals(ConstantsUtils.NULL) || StringUtils.isEmpty(String.valueOf(item[8])) ? StringUtils.EMPTY : getBrandName(item[8]));
                if (item[9] != null) {
            dto.setItemDesc(String.valueOf(item[9]));
            }
            
            } else {
            dto.setItemStatus(getDescription((Integer)item[3]));
            dto.setForm(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(item[4]))));
            dto.setDisplayForm(dto.getForm().getDescription());
            dto.setStrengthID(Integer.valueOf(String.valueOf(item[5])));
            dto.setStrength(String.valueOf(helperListUtil.getIdHelperDTOMap().get(dto.getStrengthID())));
            if(item[6]!=null && StringUtils.isNotBlank(String.valueOf(item[6]))&&!ConstantsUtils.SELECT_ONE.equals(String.valueOf(item[6]))&& !ConstantsUtils.ZERO.equals(String.valueOf(item[6])) && !ConstantsUtils.NULL.equals(String.valueOf(item[6]))){
            dto.setTherapeuticClass(String.valueOf(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(item[6])))));
            dto.setTherapeuticClassId(Integer.valueOf(String.valueOf(item[6])));
            }
            dto.setBrand( StringUtils.isEmpty(String.valueOf(item[7])) ? StringUtils.EMPTY : BrandMasterLocalServiceUtil.getBrandMaster(Integer.valueOf(String.valueOf(item[7]))).getBrandName());
            if(item[8]!=null){
            dto.setItemDesc(String.valueOf(item[8]));
            }
            }
            resultlist.add(dto);
        }
        }catch(PortalException e){
        
        }catch(SystemException e){

        }
            return resultlist;
        }

    public static String getDescription(final int code){
        try {
            HelperTable dto = HelperTableLocalServiceUtil.getHelperTable(code);
            return dto.getDescription();
        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(IFPLogic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(IFPLogic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    /**
     * To get the result for lazy load
     * @param itemId
     * @return
     * @throws SystemException
     */
    public List<Object> getTempTableItemIds() throws SystemException{
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(ImtdIfpDetails.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USER_SID, Integer.valueOf(userId)));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, sessionId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.D));
        ifpDynamicQuery.setProjection(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_MASTER_SID));
        return (List<Object>)ImtdIfpDetailsLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
    }

    /**
     * Gets the customized item data.
     *
     * @param itemMasterList the item master list
     * @return the customized item data
     * @throws Exception the exception
     */
    public static List<ItemMasterDTO> getCustomizedData(final List<Object[]> itemMasterList) {
        try{
        final List<ItemMasterDTO> itemDTO = new ArrayList<ItemMasterDTO>();
        LOGGER.info("Entering getCustomizedItemData p1: size -"+((itemMasterList==null)?itemMasterList:itemMasterList.size()));
        for (int i = 0; i < itemMasterList.size(); i++) {
            final Object[] item = itemMasterList.get(i);
            final  ItemMasterDTO itemDTOObj = new ItemMasterDTO();
            itemDTOObj.setItemSystemId(String.valueOf(item[0]));
            itemDTOObj.setItemId(String.valueOf(item[1]));
            itemDTOObj.setItemNo(String.valueOf(item[2]));
            itemDTOObj.setItemName(String.valueOf(item[3]));
            itemDTOObj.setItemStartDate((Date) item[4]);
            itemDTOObj.setItemEndDate((Date) item[5]);
            itemDTOObj.setDisplayPackageSize(item[6]==null || ConstantsUtils.NULL.equals(String.valueOf(item[6])) || Integer.valueOf(String.valueOf(item[6]))== 0 ? StringUtils.EMPTY : String.valueOf(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(item[6])))));
            itemDTOObj.setPrimaryUom(item[7]==null || StringUtils.isBlank(item[7].toString()) ? new com.stpl.ifs.util.HelperDTO(StringUtils.EMPTY) : helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(item[7]))));
            itemDTOObj.setTherapeuticClass(item[8]==null || ConstantsUtils.NULL.equals(String.valueOf(item[6])) || StringUtils.isBlank(item[8].toString()) ? new com.stpl.ifs.util.HelperDTO(StringUtils.EMPTY) : helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(item[8]))));
            itemDTOObj.setStrength(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(item[9]))));
            itemDTOObj.setForm(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(item[10]))));
            itemDTOObj.setDisplayForm(itemDTOObj.getForm().getDescription());
            itemDTOObj.setItemStatus(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(item[11]))));
            itemDTOObj.setDisplayItemStatus(itemDTOObj.getDisplayItemStatus());
            itemDTOObj.setBrandId(String.valueOf(item[12]));
            if(item[13]!=null){
            itemDTOObj.setItemDesc(String.valueOf(item[13]));
            }
            itemDTOObj.setBrand(item[14] != null ?String.valueOf(item[14]) :StringUtils.EMPTY);
            itemDTOObj.setUniqueDate(String.valueOf(new Date() + "`" + i));
            itemDTO.add(itemDTOObj);
        }
        LOGGER.info("Return List of itemDTO " + itemDTO.size());
        return itemDTO;
        }catch(Exception ex){
            LOGGER.error(ex);
            return null;
        }
    }

    /**
     *
     * @return
     * @throws SystemException 
     */
    public int getResultTableCount(BeanSearchCriteria sc) throws SystemException{
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
       int user=Integer.valueOf(userId);
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(ImtdIfpDetails.class);

        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USER_SID, user));

        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, sessionId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.D));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.F));
        
        ifpDynamicQuery.setProjection(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_MASTER_SID));
        Map<String, Object> parameters = new HashMap<String, Object>();
        getParameterList(parameters, sc);
         if ((parameters.get(ConstantsUtils.ITEM_ID) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_ID))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_ID, ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.ITEM_NO)) + ConstantsUtils.PERCENCTAGE));
        }
        if ((parameters.get(ConstantsUtils.ITEM_NO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_NO))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_NO, ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.ITEM_NO)) + ConstantsUtils.PERCENCTAGE));
        }
        if ((parameters.get(ConstantsUtils.ITEM_NAME) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_NAME))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_NAME, ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.ITEM_NAME)) + ConstantsUtils.PERCENCTAGE));
        }
        if ((parameters.get(ConstantsUtils.ITEM_DESC) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_DESC))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_DESC, ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.ITEM_DESC)) + ConstantsUtils.PERCENCTAGE));
        }
        if ((parameters.get(ConstantsUtils.IFP_STATUS) != null && !StringUtils.isBlank(String.valueOf(parameters.get(ConstantsUtils.IFP_STATUS))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq("ifpDetailsAttachedStatus", Integer.valueOf(String.valueOf(parameters.get(ConstantsUtils.IFP_STATUS)))));
        }
        if ((parameters.get(ConstantsUtils.IFP_STARTDATE_FORM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.IFP_STARTDATE_FORM))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.IFP_DETAILS_SART_DATE,parameters.get(ConstantsUtils.IFP_STARTDATE_FORM)));
        }
        if ((parameters.get(ConstantsUtils.IFP_STARTDATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.IFP_STARTDATE_TO))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.IFP_DETAILS_SART_DATE,parameters.get(ConstantsUtils.IFP_STARTDATE_TO)));
        }
        if ((parameters.get(ConstantsUtils.IFP_STARTDATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.IFP_STARTDATE_FROM))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.IFP_DETAILS_ENDDATE,parameters.get(ConstantsUtils.IFP_STARTDATE_FROM)));
        }
        if ((parameters.get(ConstantsUtils.IFP_ENDDATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.IFP_ENDDATE_TO))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.IFP_DETAILS_ENDDATE,parameters.get(ConstantsUtils.IFP_ENDDATE_TO)));
        }
        if ((parameters.get(ConstantsUtils.ITEM_STATUS) != null && !StringUtils.isBlank(String.valueOf(parameters.get(ConstantsUtils.ITEM_STATUS))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_STATUS, Integer.valueOf(String.valueOf(parameters.get(ConstantsUtils.ITEM_STATUS)))));
        }
        if ((parameters.get(ConstantsUtils.FORM1) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.FORM1))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_FORM, String.valueOf(parameters.get(ConstantsUtils.FORM1))));
        }
        if ((parameters.get(ConstantsUtils.STRENGTH) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.STRENGTH))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_STRENGTH, String.valueOf(parameters.get(ConstantsUtils.STRENGTH))));
        }
        if ((parameters.get(ConstantsUtils.THERAPEUTIC_CLASS) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.THERAPEUTIC_CLASS))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_THERAPAUTICCLASS, String.valueOf(parameters.get(ConstantsUtils.THERAPEUTIC_CLASS))));
        }
        if ((parameters.get(ConstantsUtils.BRAND) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.BRAND))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_BRAND, String.valueOf(parameters.get(ConstantsUtils.BRAND))));
        }
        if ((parameters.get(ConstantsUtils.ATTACHED_DATE_FORM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ATTACHED_DATE_FORM))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ge("ifpDetailsAttachedDate",parameters.get(ConstantsUtils.ATTACHED_DATE_FORM)));
        }
        if ((parameters.get(ConstantsUtils.ATTACHED_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ATTACHED_DATE_TO))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.le("ifpDetailsAttachedDate",parameters.get(ConstantsUtils.ATTACHED_DATE_TO)));
        }
        if ((parameters.get(ConstantsUtils.MODIFIED_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.MODIFIED_DATE_FROM))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.MODIFIEDDATE,parameters.get(ConstantsUtils.MODIFIED_DATE_FROM)));
        }
        if ((parameters.get(ConstantsUtils.MODIFIED_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.MODIFIED_DATE_TO))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.MODIFIEDDATE,parameters.get(ConstantsUtils.MODIFIED_DATE_TO)));
        }
        if ((parameters.get(ConstantsUtils.CREATED_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CREATED_DATE_FROM))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.CREATEDDATE,parameters.get(ConstantsUtils.CREATED_DATE_FROM)));
        }
        if ((parameters.get(ConstantsUtils.CREATED_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CREATED_DATE_TO))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.CREATEDDATE,parameters.get(ConstantsUtils.CREATED_DATE_TO)));
        }
        if ((parameters.get(ConstantsUtils.CREATEDBY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CREATEDBY))))) {
            List<String> userList = new ArrayList<String>();
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
            List<User> result = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
            if (result != null && !result.isEmpty()) {
                for (User user1 : result) {
                    String name = user1.getFullName();
                    if (name.toLowerCase().contains(String.valueOf(parameters.get(ConstantsUtils.CREATEDBY)).toLowerCase())) {
                        userList.add(String.valueOf(user1.getUserId()));
                    }
                }
                if (!userList.isEmpty()) {
                    ifpDynamicQuery.add(RestrictionsFactoryUtil.in("ifpDetailsCreatedBy", userList));
                }
            }
        }
        if ((parameters.get(ConstantsUtils.MODIFIEDBY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.MODIFIEDBY))))) {
            List<String> userList = new ArrayList<String>();
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
            List<User> result = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
            if (result != null && !result.isEmpty()) {
                for (User user1 : result) {
                    String name = user1.getFullName();
                    if (name.toLowerCase().contains(String.valueOf(parameters.get(ConstantsUtils.MODIFIEDBY)).toLowerCase())) {
                        userList.add(String.valueOf(user1.getUserId()));
                    }
                }
                if (!userList.isEmpty()) {
                    ifpDynamicQuery.add(RestrictionsFactoryUtil.in("ifpDetailsModifiedBy", userList));
                }
            }
        }
        int count=Integer.parseInt(String.valueOf(ImtdIfpDetailsLocalServiceUtil.dynamicQueryCount(ifpDynamicQuery)));
        return count;
    }

    public List<Object[]> getResultTableResult(int start, int end, final ErrorfulFieldGroup binder, List<OrderByColumn> list, BeanSearchCriteria sc,Boolean isCount,String record) throws SystemException {      
        LOGGER.info("Entering getResultTableResult");
        try{
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        int user = Integer.valueOf(userId);
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(ImtdIfpDetails.class);

        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USER_SID, user));

        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, sessionId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.D));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.F));
        ifpDynamicQuery.setLimit(start, end);
        String coulmnName = ConstantsUtils.ITEM_NO_CAPS;
        String orderBy =ConstantsUtils.ASC;
        
        Map<String, Object> parameters = new HashMap<String, Object>();
        getParameterList(parameters, sc);
        if ((parameters.get(ConstantsUtils.ITEM_ID) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_ID))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_ID, ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.ITEM_ID)) + ConstantsUtils.PERCENCTAGE));
        }
        if ((parameters.get(ConstantsUtils.ITEM_NO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_NO))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_NO, ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.ITEM_NO)) + ConstantsUtils.PERCENCTAGE));
        }
        if ((parameters.get(ConstantsUtils.ITEM_NAME) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_NAME))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_NAME, ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.ITEM_NAME)) + ConstantsUtils.PERCENCTAGE));
        }
        if ((parameters.get(ConstantsUtils.ITEM_DESC) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_DESC))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_DESC, ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.ITEM_DESC)) + ConstantsUtils.PERCENCTAGE));
        }
        if ((parameters.get(ConstantsUtils.IFP_STATUS) != null && !StringUtils.isBlank(String.valueOf(parameters.get(ConstantsUtils.IFP_STATUS))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq("ifpDetailsAttachedStatus", Integer.valueOf(String.valueOf(parameters.get(ConstantsUtils.IFP_STATUS)))));
        }
        if ((parameters.get(ConstantsUtils.IFP_STARTDATE_FORM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.IFP_STARTDATE_FORM))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.IFP_DETAILS_SART_DATE,parameters.get(ConstantsUtils.IFP_STARTDATE_FORM)));
        }
        if ((parameters.get(ConstantsUtils.IFP_STARTDATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.IFP_STARTDATE_TO))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.IFP_DETAILS_SART_DATE,parameters.get(ConstantsUtils.IFP_STARTDATE_TO)));
        }
        if ((parameters.get(ConstantsUtils.IFP_STARTDATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.IFP_STARTDATE_FROM))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.IFP_DETAILS_ENDDATE,parameters.get(ConstantsUtils.IFP_STARTDATE_FROM)));
        }
        if ((parameters.get(ConstantsUtils.IFP_ENDDATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.IFP_ENDDATE_TO))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.IFP_DETAILS_ENDDATE,parameters.get(ConstantsUtils.IFP_ENDDATE_TO)));
        }
        if ((parameters.get(ConstantsUtils.ITEM_STATUS) != null && !StringUtils.isBlank(String.valueOf(parameters.get(ConstantsUtils.ITEM_STATUS))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_STATUS, Integer.valueOf(String.valueOf(parameters.get(ConstantsUtils.ITEM_STATUS)))));
        }
        if ((parameters.get(ConstantsUtils.FORM1) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.FORM1))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_FORM, String.valueOf(parameters.get(ConstantsUtils.FORM1))));
        }
        if ((parameters.get(ConstantsUtils.STRENGTH) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.STRENGTH))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_STRENGTH, String.valueOf(parameters.get(ConstantsUtils.STRENGTH))));
        }
        if ((parameters.get(ConstantsUtils.THERAPEUTIC_CLASS) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.THERAPEUTIC_CLASS))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_THERAPAUTICCLASS, String.valueOf(parameters.get(ConstantsUtils.THERAPEUTIC_CLASS))));
        }
        if ((parameters.get(ConstantsUtils.BRAND) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.BRAND))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_BRAND, String.valueOf(parameters.get(ConstantsUtils.BRAND))));
        }
        if ((parameters.get(ConstantsUtils.ATTACHED_DATE_FORM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ATTACHED_DATE_FORM))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ge("ifpDetailsAttachedDate",parameters.get(ConstantsUtils.ATTACHED_DATE_FORM)));
        }
        if ((parameters.get(ConstantsUtils.ATTACHED_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ATTACHED_DATE_TO))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.le("ifpDetailsAttachedDate",parameters.get(ConstantsUtils.ATTACHED_DATE_TO)));
        }
        if ((parameters.get(ConstantsUtils.MODIFIED_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.MODIFIED_DATE_FROM))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.MODIFIEDDATE,parameters.get(ConstantsUtils.MODIFIED_DATE_FROM)));
        }
        if ((parameters.get(ConstantsUtils.MODIFIED_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.MODIFIED_DATE_TO))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.MODIFIEDDATE,parameters.get(ConstantsUtils.MODIFIED_DATE_TO)));
        }
        if ((parameters.get(ConstantsUtils.CREATED_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CREATED_DATE_FROM))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.CREATEDDATE,parameters.get(ConstantsUtils.CREATED_DATE_FROM)));
        }
        if ((parameters.get(ConstantsUtils.CREATED_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CREATED_DATE_TO))))) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.CREATEDDATE,parameters.get(ConstantsUtils.CREATED_DATE_TO)));
        }
         if ((parameters.get(ConstantsUtils.CREATEDBY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CREATEDBY))))) {
            List<String> userList = new ArrayList<String>();
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
            List<User> result = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
            if (result != null && !result.isEmpty()) {
                for (User user1 : result) {
                    String name = user1.getFullName();
                    if (name.toLowerCase().contains(String.valueOf(parameters.get(ConstantsUtils.CREATEDBY)).toLowerCase())) {
                        userList.add(String.valueOf(user1.getUserId()));
                }
            }
                if (!userList.isEmpty()) {
                    ifpDynamicQuery.add(RestrictionsFactoryUtil.in("ifpDetailsCreatedBy", userList));
        }
            }
        }
        if ((parameters.get(ConstantsUtils.MODIFIEDBY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.MODIFIEDBY))))) {
            List<String> userList = new ArrayList<String>();
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
            List<User> result = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
            if (result != null && !result.isEmpty()) {
                for (User user1 : result) {
                    String name = user1.getFullName();
                    if (name.toLowerCase().contains(String.valueOf(parameters.get(ConstantsUtils.MODIFIEDBY)).toLowerCase())) {
                        userList.add(String.valueOf(user1.getUserId()));
                    }
                }
                if (!userList.isEmpty()) {
                    ifpDynamicQuery.add(RestrictionsFactoryUtil.in("ifpDetailsModifiedBy", userList));
                }
            }
        }
        if ((parameters.get("ifpcreatedBy") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("ifpcreatedBy"))))) {
            List<String> userList = new ArrayList<String>();
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
            List<User> result = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
            if (result != null && !result.isEmpty()) {
                for (User user1 : result) {
                    String name = user1.getFullName();
                    if (name.toLowerCase().contains(String.valueOf(parameters.get("ifpcreatedBy")).toLowerCase())) {
                        userList.add(String.valueOf(user1.getUserId()));
                    }
                }
                if (!userList.isEmpty()) {
                    ifpDynamicQuery.add(RestrictionsFactoryUtil.in("ifpDetailsModifiedBy", userList));
                }
            }
        }
         
        Map<String, String> columnMap = new HashMap<String, String>();
        columnMap.put(ConstantsUtils.IFP_STATUS, "IFP_DETAILS_ATTACHED_STATUS");
        columnMap.put(ConstantsUtils.IFP_START_DATE, "IFP_DETAILS_START_DATE");
        columnMap.put(ConstantsUtils.IFP_END_DATE, "IFP_DETAILS_END_DATE");
        columnMap.put(ConstantsUtils.ITEM_ID, ConstantsUtils.ITEM_ID_CAPS);       
        columnMap.put(ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NO_CAPS);   
        columnMap.put(ConstantsUtils.ITEM_NAME, ConstantsUtils.ITEM_NAME_CAPS);
        columnMap.put(ConstantsUtils.ITEM_STATUS, ConstantsUtils.ITEM_STATUS_CAPS);
        columnMap.put("itemStartDate", "ITEM_START_DATE");
        columnMap.put("itemEndDate", "ITEM_END_DATE");
        columnMap.put("primaryUom", "ITEM_PRIMARY_UOM");
        columnMap.put(ConstantsUtils.THERAPEUTIC_CLASS, "ITEM_THERAPEUTIC_CLASS");
        columnMap.put(ConstantsUtils.BRAND, "ITEM_BRAND");
        columnMap.put(ConstantsUtils.FORM1, "ITEM_FORM");
        columnMap.put(ConstantsUtils.STRENGTH, "ITEM_STRENGTH");
        columnMap.put(ConstantsUtils.PACKAGE_SIZE, "ITEM_PACKAGE_SIZE");
        columnMap.put(ConstantsUtils.CREATEDBY, "IFP_DETAILS_CREATED_BY");
        columnMap.put(ConstantsUtils.CREATEDDATE, "IFP_DETAILS_CREATED_DATE");
        columnMap.put(ConstantsUtils.MODIFIEDBY, "IFP_DETAILS_MODIFIED_BY");
        columnMap.put(ConstantsUtils.MODIFIEDDATE, "IFP_DETAILS_MODIFIED_DATE");
        columnMap.put("attachedDate", "IFP_DETAILS_ATTACHED_DATE");   
        columnMap.put("ifpcreatedBy", "CREATED_BY");

        if(list!=null){
            for (final Iterator<OrderByColumn> iterator = list.iterator(); iterator
                    .hasNext();) {
                final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
                coulmnName = columnMap.get(orderByColumn.getName());

                //  order by asc desc
                if (coulmnName != null) {
                    if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                        ifpDynamicQuery.addOrder(OrderFactoryUtil.asc(coulmnName));
                        orderBy=ConstantsUtils.ASC;
                    } else {
                        ifpDynamicQuery.addOrder(OrderFactoryUtil.desc(coulmnName));
                        orderBy=ConstantsUtils.DESC;
                    }
                }
            }
        }
        if(!StringUtils.isBlank(record)){
            if(record.contains(ConstantsUtils.CURRENT)){
                    parameters.put(ConstantsUtils.CURRENT, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
                if(record.contains(ConstantsUtils.HISTORY)){
                    parameters.put(ConstantsUtils.HISTORY, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
                if(record.contains(ConstantsUtils.FUTURE)){
                    parameters.put(ConstantsUtils.FUTURE, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
        }
        
        StringBuilder sql = new StringBuilder();
        
        if(isCount){
            sql.append(CustomSQLUtil.get("ifpSelectTempTableCount"));
        }else{
            sql.append(CustomSQLUtil.get("ifpSelectTempTableData"));
        }
        
        sql.append(" AND USERS_SID ='"+userId+"' AND SESSION_ID ='"+sessionId+"' ");
        
        if ((parameters.get(ConstantsUtils.ITEM_ID) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_ID))))) {
            sql.append(" AND ITEM_ID LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.ITEM_ID))).append("%' ");
        }
        if ((parameters.get(ConstantsUtils.ITEM_NO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_NO))))) {
            sql.append(" AND ITEM_NO LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.ITEM_NO))).append("%' ");
        }
        if ((parameters.get(ConstantsUtils.ITEM_NAME) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_NAME))))) {
            sql.append(" AND ITEM_NAME LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.ITEM_NAME))).append("%' ");
        }
        if ((parameters.get(ConstantsUtils.ITEM_DESC) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_DESC))))) {
            sql.append(" AND ITEM_DESC LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.ITEM_DESC))).append("%' ");
        }
        if ((parameters.get(ConstantsUtils.IFP_STATUS) != null && !StringUtils.isBlank(String.valueOf(parameters.get(ConstantsUtils.IFP_STATUS))))) {
            sql.append(" AND IFP_DETAILS_ATTACHED_STATUS LIKE '").append(String.valueOf(parameters.get(ConstantsUtils.IFP_STATUS))).append("' ");
        }
        if ((parameters.get(ConstantsUtils.IFP_STARTDATE_FORM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.IFP_STARTDATE_FORM))))) {
            sql.append(" AND IFP_DETAILS_START_DATE >= '").append(String.valueOf(parameters.get(ConstantsUtils.IFP_STARTDATE_FORM))).append("' ");
        }
        if ((parameters.get(ConstantsUtils.IFP_STARTDATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.IFP_STARTDATE_TO))))) {
            sql.append(" AND IFP_DETAILS_START_DATE <= '").append(String.valueOf(parameters.get(ConstantsUtils.IFP_STARTDATE_TO))).append("' ");
        }
        if ((parameters.get(ConstantsUtils.IFP_STARTDATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.IFP_STARTDATE_FROM))))) {
            sql.append(" AND IFP_DETAILS_END_DATE >= '").append(String.valueOf(parameters.get(ConstantsUtils.IFP_STARTDATE_FROM))).append("' ");
        }
        if ((parameters.get(ConstantsUtils.IFP_ENDDATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.IFP_ENDDATE_TO))))) {
            sql.append(" AND IFP_DETAILS_END_DATE <= '").append(String.valueOf(parameters.get(ConstantsUtils.IFP_STARTDATE_FROM))).append("' ");
        }
        if ((parameters.get(ConstantsUtils.ITEM_STATUS) != null && !StringUtils.isBlank(String.valueOf(parameters.get(ConstantsUtils.ITEM_STATUS))))) {
            sql.append(" AND ITEM_STATUS LIKE '").append(String.valueOf(parameters.get(ConstantsUtils.ITEM_STATUS))).append("' ");
        }
        if ((parameters.get(ConstantsUtils.FORM1) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.FORM1))))) {
            sql.append(" AND ITEM_FORM LIKE '").append(String.valueOf(parameters.get(ConstantsUtils.FORM1))).append("' ");
        }
        if ((parameters.get(ConstantsUtils.STRENGTH) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.STRENGTH))))) {
            sql.append(" AND ITEM_STRENGTH LIKE '").append(String.valueOf(parameters.get(ConstantsUtils.STRENGTH))).append("' ");
        }
        if ((parameters.get(ConstantsUtils.THERAPEUTIC_CLASS) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.THERAPEUTIC_CLASS))))) {
            sql.append(" AND ITEM_THERAPEUTIC_CLASS LIKE '").append(String.valueOf(parameters.get(ConstantsUtils.THERAPEUTIC_CLASS))).append("' ");
        }
        if ((parameters.get(ConstantsUtils.BRAND) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.BRAND))))) {
            sql.append(" AND ITEM_BRAND LIKE '").append(String.valueOf(parameters.get(ConstantsUtils.BRAND))).append("' ");
        }
        if ((parameters.get(ConstantsUtils.ATTACHED_DATE_FORM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ATTACHED_DATE_FORM))))) {
            sql.append(" AND IFP_DETAILS_ATTACHED_DATE >= '").append(String.valueOf(parameters.get(ConstantsUtils.ATTACHED_DATE_FORM))).append("' ");
        }
        if ((parameters.get(ConstantsUtils.ATTACHED_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ATTACHED_DATE_TO))))) {
            sql.append(" AND IFP_DETAILS_ATTACHED_DATE <= '").append(String.valueOf(parameters.get(ConstantsUtils.ATTACHED_DATE_TO))).append("' ");
        }
        if ((parameters.get(ConstantsUtils.MODIFIED_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.MODIFIED_DATE_FROM))))) {
            sql.append(" AND IFP_DETAILS_MODIFIED_DATE >= '").append(String.valueOf(parameters.get(ConstantsUtils.MODIFIED_DATE_FROM))).append("' ");
        }
        if ((parameters.get(ConstantsUtils.MODIFIED_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.MODIFIED_DATE_TO))))) {
            sql.append(" AND IFP_DETAILS_MODIFIED_DATE <= '").append(String.valueOf(parameters.get(ConstantsUtils.MODIFIED_DATE_TO))).append("' ");
        }
        if ((parameters.get(ConstantsUtils.CREATED_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CREATED_DATE_FROM))))) {
            sql.append(" AND IFP_DETAILS_CREATED_DATE >= '").append(String.valueOf(parameters.get(ConstantsUtils.CREATED_DATE_FROM))).append("' ");
        }
        if ((parameters.get(ConstantsUtils.CREATED_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CREATED_DATE_TO))))) {
            sql.append(" AND IFP_DETAILS_CREATED_DATE <= '").append(String.valueOf(parameters.get(ConstantsUtils.CREATED_DATE_TO))).append("' ");
        }
        if ((parameters.get(ConstantsUtils.CREATEDBY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CREATEDBY))))) {
            sql.append(" AND IFP_DETAILS_CREATED_BY = ").append(String.valueOf(parameters.get(ConstantsUtils.CREATEDBY)));
        }
        if ((parameters.get(ConstantsUtils.MODIFIEDBY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.MODIFIEDBY))))) {
            List<String> userList = new ArrayList<String>();
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
            List<User> result = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
            if (result != null && !result.isEmpty()) {
                for (User user1 : result) {
                    String name = user1.getFullName();
                    if (name.toLowerCase().contains(String.valueOf(parameters.get(ConstantsUtils.MODIFIEDBY)).toLowerCase())) {
                        userList.add(String.valueOf(user1.getUserId()));
                    }
                }
                if (!userList.isEmpty()) {
                    sql.append(" AND IFP_DETAILS_MODIFIED_BY IN (");
                    for (int i=0;i<userList.size();i++) {
                        if(i==0){
                            sql.append(" '").append(userList.get(i)).append(ConstantsUtils.SINGLE_QUOTE);
                        }else{
                            sql.append(", '").append(userList.get(i)).append(ConstantsUtils.SINGLE_QUOTE);
                        }
                    }
                    sql.append(") ");
                }
            }
        }
        
        if (parameters.get(ConstantsUtils.CURRENT) != null && parameters.get(ConstantsUtils.HISTORY) != null && parameters.get(ConstantsUtils.FUTURE) != null) {
                      sql.append(" AND ( '").append(parameters.get(ConstantsUtils.CURRENT)).append("' BETWEEN IFP_DETAILS_START_DATE AND ISNULL(IFP_DETAILS_END_DATE,'").append(parameters.get(ConstantsUtils.CURRENT)).append("') ");
                      sql.append(" OR ").append(" IFP_DETAILS_END_DATE < '").append(parameters.get(ConstantsUtils.HISTORY)).append("' ");
                      sql.append(" OR ").append(" IFP_DETAILS_START_DATE > '").append(parameters.get(ConstantsUtils.FUTURE)).append("' )");
                  } else if ((parameters.get(ConstantsUtils.CURRENT) != null && parameters.get(ConstantsUtils.HISTORY) != null) || (parameters.get(ConstantsUtils.HISTORY) != null && parameters.get(ConstantsUtils.CURRENT) != null)) {
                      sql.append(" AND ( '").append(parameters.get(ConstantsUtils.CURRENT)).append("' BETWEEN IFP_DETAILS_START_DATE AND ISNULL(IFP_DETAILS_END_DATE,'").append(parameters.get(ConstantsUtils.CURRENT)).append("') OR IFP_DETAILS_END_DATE < '").append(parameters.get(ConstantsUtils.HISTORY)).append("') ");
                  } else if ((parameters.get(ConstantsUtils.HISTORY) != null && parameters.get(ConstantsUtils.FUTURE) != null) || (parameters.get(ConstantsUtils.FUTURE) != null && parameters.get(ConstantsUtils.HISTORY) != null)) {
                      sql.append(" AND (").append(" IFP_DETAILS_END_DATE < '").append(parameters.get(ConstantsUtils.HISTORY)).append("' OR IFP_DETAILS_START_DATE > '").append(parameters.get(ConstantsUtils.FUTURE)).append("') ");;
                  } else if ((parameters.get(ConstantsUtils.FUTURE) != null && parameters.get(ConstantsUtils.CURRENT) != null) || (parameters.get(ConstantsUtils.CURRENT) != null && parameters.get(ConstantsUtils.FUTURE) != null)) {
                      sql.append(" AND ( '").append(parameters.get(ConstantsUtils.CURRENT)).append("' BETWEEN IFP_DETAILS_START_DATE AND ISNULL(IFP_DETAILS_END_DATE,'").append(parameters.get(ConstantsUtils.CURRENT)).append("') OR IFP_DETAILS_START_DATE > '").append(parameters.get(ConstantsUtils.FUTURE)).append("') ");
                  } else if (parameters.get(ConstantsUtils.CURRENT) != null) {
                      sql.append(" AND '").append(parameters.get(ConstantsUtils.CURRENT)).append("' BETWEEN IFP_DETAILS_START_DATE AND ISNULL(IFP_DETAILS_END_DATE,'").append(parameters.get(ConstantsUtils.CURRENT)).append("') ");
                  } else if (parameters.get(ConstantsUtils.HISTORY) != null) {
                      sql.append(" AND ").append(" IFP_DETAILS_END_DATE < '").append(parameters.get(ConstantsUtils.HISTORY)).append("' ");
                  } else if (parameters.get(ConstantsUtils.FUTURE) != null) {
                      sql.append(" AND ").append(" IFP_DETAILS_START_DATE > '").append(parameters.get(ConstantsUtils.FUTURE)).append("' ");
                  }
        
        
        if(!isCount){
            sql.append(" ORDER BY ").append(coulmnName).append(" "+ orderBy +" ").append(" OFFSET "+ start + " ROWS FETCH NEXT " + end + " ROWS ONLY");
        }
        List resultList = HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
        
        LOGGER.info("Ending getResultTableResult");
        return resultList;
        }catch(Exception ex){
            LOGGER.error(ex);
            return null;
        }
    }
    
    public static List<IFPItemDTO> getCoustomizedResult(List<Object[]> list, final ErrorfulFieldGroup binder){
        try {
            
            List<IFPItemDTO> resultList = new ArrayList<IFPItemDTO>();
            for (Iterator<Object[]> temp = (Iterator<Object[]>) list.iterator(); temp.hasNext();) {
                Object[] item = temp.next();
                IFPItemDTO resultDTO = new IFPItemDTO();
                resultDTO.setCheckbox((Boolean)item[0]);
                resultDTO.setItemFamilyplanNo(binder.getField(ConstantsUtils.IFP_NO) == null ? StringUtils.EMPTY : binder.getField(ConstantsUtils.IFP_NO).toString());
                resultDTO.setItemFamilyplanName(binder.getField(ConstantsUtils.IFP_NAME) == null ? StringUtils.EMPTY : binder.getField(ConstantsUtils.IFP_NAME).toString());
                if(item[1]!=null && !StringUtils.isBlank(String.valueOf(item[1]))){
                resultDTO.setItemFamilyplanStatus(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(item[1]))));
                }
                if(item[2]!=null){
                resultDTO.setItemFamilyplanStartDate((Date)item[2]);
                }
                if(item[3]!=null){
                resultDTO.setItemFamilyplanEndDate((Date)item[3]);
                }
                resultDTO.setItemId(String.valueOf(item[4]));
                resultDTO.setItemNo(String.valueOf(item[5]));
                resultDTO.setItemName(String.valueOf(item[6]));
                if(item[7]!=null && !StringUtils.isBlank(String.valueOf(item[7]))){
                resultDTO.setItemStatus(StringUtils.isBlank(String.valueOf(item[7])) || Integer.valueOf(String.valueOf(item[7])) == 0 ? StringUtils.EMPTY : CommonUtils.loadDescription(Integer.valueOf(String.valueOf(item[7]))));
                }
                resultDTO.setItemDesc(StringUtils.isBlank(String.valueOf(item[8])) || ConstantsUtils.NULL.equals(String.valueOf(item[8]))?StringUtils.EMPTY:String.valueOf(item[8]));
                resultDTO.setItemStartDate((Date)item[9]);
                resultDTO.setItemEndDate((Date)item[10]);
                if(item[11]!=null && !StringUtils.isBlank(String.valueOf(item[11]))){
                resultDTO.setPrimaryUom(ConstantsUtils.ZERO.equals(String.valueOf(item[11])) || StringUtils.isBlank(String.valueOf(item[8])) ? StringUtils.EMPTY : CommonUtils.loadDescription(CommonUtils.getHelperSID(String.valueOf(item[11]))));
                }
                if(item[12]!=null && !StringUtils.isBlank(String.valueOf(item[12]))&& !ConstantsUtils.NULL.equals(String.valueOf(item[12]))){
                resultDTO.setPackageSize(ConstantsUtils.ZERO.equals(String.valueOf(item[12])) || StringUtils.isBlank(String.valueOf(item[12])) ? StringUtils.EMPTY : CommonUtils.loadDescription(Integer.valueOf(String.valueOf(item[12]))));
                }
                if(String.valueOf(item[13]) != null && !ConstantsUtils.NULL.equals(String.valueOf(item[13])) && StringUtils.isNotBlank(String.valueOf(item[13]))){
                resultDTO.setBrand(StringUtils.isBlank(String.valueOf(item[13])) || ConstantsUtils.ZERO.equals(String.valueOf(item[13])) ? StringUtils.EMPTY : BrandMasterLocalServiceUtil.getBrandMaster(Integer.valueOf(String.valueOf(item[13]))).getBrandName());
                }
                if(String.valueOf(item[14]) != null && !ConstantsUtils.NULL.equals(String.valueOf(item[14])) && StringUtils.isNotBlank(String.valueOf(item[14]))){
                    resultDTO.setForm(CommonUtils.loadDescription(Integer.valueOf(String.valueOf(item[14]))));
                }
                resultDTO.setTherapeuticClass(ConstantsUtils.ZERO.equals(String.valueOf(item[15])) || StringUtils.isBlank(String.valueOf(item[15])) || ConstantsUtils.NULL.equals(String.valueOf(item[15])) ? StringUtils.EMPTY : CommonUtils.loadDescription(Integer.parseInt(String.valueOf(item[15]))));
                resultDTO.setStrength(ConstantsUtils.ZERO.equals(String.valueOf(item[16])) || StringUtils.isBlank(String.valueOf(item[16])) ? StringUtils.EMPTY : String.valueOf(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(item[16])))));
                if (StringUtils.isNotBlank(String.valueOf(item[17])) && String.valueOf(String.valueOf(item[17])).matches(ValidationUtils.NUM_VALIDATION)) {
                    String createdBy = StplSecurity.userMap.get(Integer.valueOf(String.valueOf(item[17])));
                    resultDTO.setCreatedBy(createdBy == null ? StringUtils.EMPTY : createdBy);
                }
                
                if (StringUtils.isNotBlank(String.valueOf(item[18])) && String.valueOf(String.valueOf(item[18])).matches(ValidationUtils.NUM_VALIDATION)) {
                    String modifiedBy = StplSecurity.userMap.get(Integer.valueOf(String.valueOf(item[18])));
                    resultDTO.setModifiedBy(modifiedBy == null ? StringUtils.EMPTY : modifiedBy);
                } else {
                    resultDTO.setModifiedBy(StringUtils.EMPTY);
                }
                resultDTO.setCreatedById(String.valueOf(item[17]));
                resultDTO.setCreatedDate((Date)item[19]);
                resultDTO.setModifiedDate((Date)item[20]);
                resultDTO.setAttachedDate((Date)item[21]);
                resultDTO.setTempIFPDetailsSystemId(Integer.valueOf(String.valueOf(item[22])));
                resultList.add(resultDTO);
            }
            return resultList;
        } catch (Exception e) {
            LOGGER.error(e);    
            return null;
        }
    }
    

    public static List<IFPItemDTO> getCoustomizedResultDTO(List<ImtdIfpDetails> list, final ErrorfulFieldGroup binder) {
        try {
            List<IFPItemDTO> resultList = new ArrayList<IFPItemDTO>();
            for (Iterator<ImtdIfpDetails> temp = (Iterator<ImtdIfpDetails>) list.iterator(); temp.hasNext();) {
                ImtdIfpDetails item = temp.next();
                IFPItemDTO resultDTO = new IFPItemDTO();
                resultDTO.setCheckbox(item.getCheckBox());
                resultDTO.setItemFamilyplanNo(binder.getField(ConstantsUtils.IFP_NO) == null ? StringUtils.EMPTY : binder.getField(ConstantsUtils.IFP_NO).toString());
                resultDTO.setItemFamilyplanName(binder.getField(ConstantsUtils.IFP_NAME) == null ? StringUtils.EMPTY : binder.getField(ConstantsUtils.IFP_NAME).toString());
                resultDTO.setItemFamilyplanStatus(helperListUtil.getIdHelperDTOMap().get(item.getIfpDetailsAttachedStatus()));
                resultDTO.setItemFamilyplanStartDate(item.getIfpDetailsStartDate());
                resultDTO.setItemFamilyplanEndDate(item.getIfpDetailsEndDate());
                resultDTO.setItemId(item.getItemId());
                resultDTO.setItemNo(item.getItemNo());
                resultDTO.setItemName(item.getItemName());
                resultDTO.setItemStatus(StringUtils.isBlank(String.valueOf(item.getItemStatus())) || item.getItemStatus() == 0 ? StringUtils.EMPTY : CommonUtils.loadDescription(item.getItemStatus()));
                resultDTO.setItemDesc(item.getItemDesc());
                resultDTO.setItemStartDate(item.getItemStartDate());
                resultDTO.setItemEndDate(item.getItemEndDate());
                resultDTO.setPrimaryUom(ConstantsUtils.ZERO.equals(item.getItemPrimaryUom()) || StringUtils.isBlank(item.getItemPrimaryUom()) ? StringUtils.EMPTY : CommonUtils.loadDescription(CommonUtils.getHelperSID(item.getItemPrimaryUom())));
                resultDTO.setPackageSize(ConstantsUtils.ZERO.equals(item.getItemPackageSize()) || StringUtils.isBlank(item.getItemPackageSize()) ? StringUtils.EMPTY : CommonUtils.loadDescription(Integer.valueOf(item.getItemPackageSize())));
                if(item.getItemBrand() != null && !ConstantsUtils.NULL.equals(item.getItemBrand()) && StringUtils.isNotBlank(item.getItemBrand())){
                resultDTO.setBrand(StringUtils.isBlank(item.getItemBrand()) || ConstantsUtils.ZERO.equals(item.getItemBrand()) ? StringUtils.EMPTY : BrandMasterLocalServiceUtil.getBrandMaster(Integer.valueOf(item.getItemBrand())).getBrandName());
                }
                resultDTO.setForm(CommonUtils.loadDescription(Integer.valueOf(item.getItemForm())));

                resultDTO.setTherapeuticClass(ConstantsUtils.ZERO.equals(item.getItemTherapeuticClass()) || StringUtils.isBlank(item.getItemTherapeuticClass()) || ConstantsUtils.NULL.equals(item.getItemTherapeuticClass()) ? StringUtils.EMPTY : CommonUtils.loadDescription(Integer.parseInt(item.getItemTherapeuticClass())));
                resultDTO.setStrength(ConstantsUtils.ZERO.equals(item.getItemStrength()) || StringUtils.isBlank(item.getItemStrength()) ? StringUtils.EMPTY : String.valueOf(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(item.getItemStrength()))));
                if (StringUtils.isNotBlank(item.getIfpDetailsCreatedBy()) && String.valueOf(item.getIfpDetailsCreatedBy()).matches(ValidationUtils.NUM_VALIDATION)) {
                    String createdBy = StplSecurity.userMap.get(Integer.valueOf(item.getIfpDetailsCreatedBy()));
                    resultDTO.setCreatedBy(createdBy == null ? StringUtils.EMPTY : createdBy);
                }
                
                if (StringUtils.isNotBlank(item.getIfpDetailsModifiedBy()) && String.valueOf(item.getIfpDetailsModifiedBy()).matches(ValidationUtils.NUM_VALIDATION)) {
                    String modifiedBy = StplSecurity.userMap.get(Integer.valueOf(item.getIfpDetailsModifiedBy()));
                    resultDTO.setModifiedBy(modifiedBy == null ? StringUtils.EMPTY : modifiedBy);
                } else {
                    resultDTO.setModifiedBy(StringUtils.EMPTY);
                }
                resultDTO.setCreatedById(item.getIfpDetailsCreatedBy());
                resultDTO.setCreatedDate(item.getIfpDetailsCreatedDate());
                resultDTO.setModifiedDate(item.getIfpDetailsModifiedDate());
                resultDTO.setAttachedDate(item.getIfpDetailsAttachedDate());
                resultDTO.setTempIFPDetailsSystemId(item.getImtdIfpDetailsSid());
                resultList.add(resultDTO);
            }
            return resultList;
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    public static void saveToTemp(final List<IFPItemDTO> list) throws PortalException, SystemException {
        try {
            for (Iterator<IFPItemDTO> temp = list.iterator(); temp.hasNext();) {
                final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                IFPItemDTO item = temp.next();
                ImtdIfpDetails tempModel=null;
                if(item.getTempIFPDetailsSystemId()==0){
                    tempModel = ImtdIfpDetailsLocalServiceUtil.createImtdIfpDetails(0);
                }else{
                    tempModel=ImtdIfpDetailsLocalServiceUtil.getImtdIfpDetails(item.getTempIFPDetailsSystemId());
                }
                tempModel.setCheckBox(item.getCheckbox());
                int status=item.getItemFamilyplanStatus().getId();
                tempModel.setIfpDetailsAttachedStatus(status);

                tempModel.setIfpDetailsAttachedDate(new Date());
                
                tempModel.setIfpDetailsCreatedBy(item.getCreatedById());
                tempModel.setIfpDetailsCreatedDate(item.getCreatedDate());
                tempModel.setIfpDetailsStartDate(item.getItemFamilyplanStartDate());
                tempModel.setIfpDetailsEndDate(item.getItemFamilyplanEndDate());
                tempModel.setIfpDetailsSid(item.getIfpDetailsSystemId());
                if (tempModel.getIfpDetailsSid() == 0) {
                    tempModel.setOperation(ConstantsUtils.A);
                    tempModel.setIfpDetailsModifiedBy(userId);
                    tempModel.setIfpDetailsModifiedDate(new Date());
                } else {
                    if (!item.isCheckFlag()) {
                        tempModel.setOperation(ConstantsUtils.A);
                        tempModel.setIfpDetailsModifiedBy(userId);
                        tempModel.setIfpDetailsModifiedDate(new Date());
                    } else {
                        tempModel.setOperation(ConstantsUtils.U);
                        tempModel.setIfpDetailsModifiedBy(userId);
                        tempModel.setIfpDetailsModifiedDate(new Date());
                    }
                }
                ImtdIfpDetailsLocalServiceUtil.updateImtdIfpDetails(tempModel);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void populateLogic(String populateField,String PopulateValue, Boolean populateAll){
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String createdDate = String.valueOf(sessionDTO.getSessionDate());
        if(populateAll){
            ImtdIfpDetailsLocalServiceUtil.updateForPopulateAll(userId, sessionId, createdDate, null, populateField, PopulateValue, null, null);
        }else{
            ImtdIfpDetailsLocalServiceUtil.updateForPopulate(userId, sessionId, createdDate, null, populateField, PopulateValue, null, null);
        }
    }

    public Boolean saveValidation(String type) {
        Boolean retFlag;
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        LOGGER.info("sessionId = = = =  =>"+sessionId);
        Integer result = (Integer) ImtdIfpDetailsLocalServiceUtil.validateTempIFPDeatils(userId, sessionId, null, type, null, null, null, null);
        if ("AtleastOne".equals(type) || ConstantsUtils.CHECK_RECORD.equals(type)) {
            retFlag = result > 0;
        }else{
            retFlag = result <= 0;
        }
        return retFlag;
    }

    public void deleteIFPDetails(final Integer ifpSystemId){
        ImtdIfpDetailsLocalServiceUtil.deleteAll(null, null, null, "IFPDetails", ifpSystemId, null, null, null);
    }

    public int ifpViewCount(String record, Boolean selected) throws SystemException {
       
        final String ifpSystemId = String.valueOf(sessionDTO.getSystemId());
        int temp = 0;
         try{
        if (selected) {
            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpDetails.class);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq("ifpModelSid", Integer.parseInt(ifpSystemId)));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.INBOUND_STATUS));
            ifpDynamicQuery.setProjection(ProjectionFactoryUtil.count(ConstantsUtils.ITEM_MASTER_SID));
            temp = Integer.parseInt(String.valueOf(IfpDetailsLocalServiceUtil.dynamicQuery(ifpDynamicQuery).get(0)));
        } else {
            Map<String, Object> parameters = new HashMap<String, Object>();
            if (!StringUtils.isBlank(record)) {
                if (record.contains(ConstantsUtils.CURRENT)) {
                    parameters.put(ConstantsUtils.CURRENT, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
                if (record.contains(ConstantsUtils.HISTORY)) {
                    parameters.put(ConstantsUtils.HISTORY, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
                if (record.contains(ConstantsUtils.FUTURE)) {
                    parameters.put(ConstantsUtils.FUTURE, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
            }
            StringBuilder sql = new StringBuilder();
            sql.append(CustomSQLUtil.get("ifpViewTempTableCount"));
            sql.append(" AND IFP_MODEL_SID ='").append(ifpSystemId).append("' ");
            if(!parameters.isEmpty()){
            if (parameters.get(ConstantsUtils.CURRENT) != null && parameters.get(ConstantsUtils.HISTORY) != null && parameters.get(ConstantsUtils.FUTURE) != null) {
                sql.append(" AND ( '").append(parameters.get(ConstantsUtils.CURRENT)).append("' BETWEEN START_DATE AND ISNULL(END_DATE,'").append(parameters.get(ConstantsUtils.CURRENT)).append("') ");
                sql.append(" OR ").append(" END_DATE < '").append(parameters.get(ConstantsUtils.HISTORY)).append("' ");
                sql.append(" OR ").append(" START_DATE > '").append(parameters.get(ConstantsUtils.FUTURE)).append("' )");
            } else if ((parameters.get(ConstantsUtils.CURRENT) != null && parameters.get(ConstantsUtils.HISTORY) != null) || (parameters.get(ConstantsUtils.HISTORY) != null && parameters.get(ConstantsUtils.CURRENT) != null)) {
                sql.append(" AND ( '").append(parameters.get(ConstantsUtils.CURRENT)).append("' BETWEEN START_DATE AND ISNULL(END_DATE,'").append(parameters.get(ConstantsUtils.CURRENT)).append("') OR END_DATE < '").append(parameters.get(ConstantsUtils.HISTORY)).append("') ");
            } else if ((parameters.get(ConstantsUtils.HISTORY) != null && parameters.get(ConstantsUtils.FUTURE) != null) || (parameters.get(ConstantsUtils.FUTURE) != null && parameters.get(ConstantsUtils.HISTORY) != null)) {
                sql.append(" AND (").append(" END_DATE < '").append(parameters.get(ConstantsUtils.HISTORY)).append("' OR START_DATE > '").append(parameters.get(ConstantsUtils.FUTURE)).append("') ");;
            } else if ((parameters.get(ConstantsUtils.FUTURE) != null && parameters.get(ConstantsUtils.CURRENT) != null) || (parameters.get(ConstantsUtils.CURRENT) != null && parameters.get(ConstantsUtils.FUTURE) != null)) {
                sql.append(" AND ( '").append(parameters.get(ConstantsUtils.CURRENT)).append("' BETWEEN START_DATE AND ISNULL(END_DATE,'").append(parameters.get(ConstantsUtils.CURRENT)).append("') OR START_DATE > '").append(parameters.get(ConstantsUtils.FUTURE)).append("') ");
            } else if (parameters.get(ConstantsUtils.CURRENT) != null) {
                sql.append(" AND '").append(parameters.get(ConstantsUtils.CURRENT)).append("' BETWEEN START_DATE AND ISNULL(END_DATE,'").append(parameters.get(ConstantsUtils.CURRENT)).append("') ");
            } else if (parameters.get(ConstantsUtils.HISTORY) != null) {
                sql.append(" AND ").append(" END_DATE < '").append(parameters.get(ConstantsUtils.HISTORY)).append("' ");
            } else if (parameters.get(ConstantsUtils.FUTURE) != null) {
                sql.append(" AND ").append(" START_DATE > '").append(parameters.get(ConstantsUtils.FUTURE)).append("' ");
            }}

            List list = HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
            temp = Integer.valueOf(String.valueOf(list.get(0)));
        }
        }catch(Exception ex){
            LOGGER.error(ex);
        }
        return temp;
    }

    public List<IFPItemDTO> getViewTableResult(int start,int end, final ErrorfulFieldGroup binder,List<OrderByColumn> list, String record) throws SystemException{
        final String ifpSystemId = String.valueOf(sessionDTO.getSystemId());

        Map<String, String> columnMap = new HashMap<String, String>();
        columnMap.put("displayIFPStatus", "ITEM_IFP_ATTACHED_STATUS");
        columnMap.put(ConstantsUtils.IFP_START_DATE, "START_DATE");
        columnMap.put(ConstantsUtils.IFP_END_DATE, "END_DATE");
        columnMap.put(ConstantsUtils.ITEM_ID, ConstantsUtils.ITEM_ID_CAPS);       
        columnMap.put(ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NO_CAPS);   
        columnMap.put(ConstantsUtils.ITEM_NAME, ConstantsUtils.ITEM_NAME_CAPS);
        columnMap.put(ConstantsUtils.ITEM_STATUS, ConstantsUtils.ITEM_STATUS_CAPS);
        columnMap.put("itemStartDate", "ITEM_START_DATE");
        columnMap.put("itemEndDate", "ITEM_END_DATE");
        columnMap.put("primaryUom", ConstantsUtils.PRIMARY_UOM);
        columnMap.put(ConstantsUtils.THERAPEUTIC_CLASS, ConstantsUtils.THERAPEUTIC_CLASS_CAPS);
        columnMap.put(ConstantsUtils.BRAND, ConstantsUtils.BRAND_MASTER_SID_CAPS);
        columnMap.put(ConstantsUtils.FORM1, ConstantsUtils.FORM_CAPS);
        columnMap.put(ConstantsUtils.STRENGTH, ConstantsUtils.STRENGTH_UPPERCASE);
        columnMap.put(ConstantsUtils.PACKAGE_SIZE, "PACKAGE_SIZE");
        columnMap.put(ConstantsUtils.CREATEDBY, "CREATED_BY");
        columnMap.put(ConstantsUtils.CREATEDDATE, "CREATED_DATE");
        columnMap.put(ConstantsUtils.MODIFIEDBY, "MODIFIED_BY");
        columnMap.put(ConstantsUtils.MODIFIEDDATE, "MODIFIED_DATE");
        columnMap.put("attachedDate", "ITEM_IFP_ATTACHED_DATE");  
        
        String coulmnName = ConstantsUtils.ITEM_NO_CAPS;
        String orderBy = ConstantsUtils.ASC;

        for (final Iterator<OrderByColumn> iterator = list.iterator(); iterator
                .hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
            coulmnName = columnMap.get(orderByColumn.getName());
        if (coulmnName != null) {
        if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                orderBy = ConstantsUtils.ASC;
            } else {
                orderBy = ConstantsUtils.DESC;
            }
        }
        }

        if(!StringUtils.isBlank(record)){
            if(record.contains(ConstantsUtils.CURRENT)){
                    columnMap.put(ConstantsUtils.CURRENT, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
                if(record.contains(ConstantsUtils.HISTORY)){
                    columnMap.put(ConstantsUtils.HISTORY, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
                if(record.contains(ConstantsUtils.FUTURE)){
                    columnMap.put(ConstantsUtils.FUTURE, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
        }
        
        StringBuilder sql = new StringBuilder();
        
        sql.append(CustomSQLUtil.get("ifpViewTempTableData"));
        sql.append(" WHERE IFD.IFP_MODEL_SID=");
        sql.append(ifpSystemId);
        sql.append(" and IFD.ITEM_MASTER_SID=IM.ITEM_MASTER_SID and IFD.INBOUND_STATUS != 'D'");
        if (columnMap.get(ConstantsUtils.CURRENT) != null && columnMap.get(ConstantsUtils.HISTORY) != null && columnMap.get(ConstantsUtils.FUTURE) != null) {
            sql.append(" AND ( '").append(columnMap.get(ConstantsUtils.CURRENT)).append("' BETWEEN IFD.START_DATE AND ISNULL(IFD.END_DATE,'").append(columnMap.get(ConstantsUtils.CURRENT)).append("') ");
            sql.append(" OR ").append(" IFD.END_DATE < '").append(columnMap.get(ConstantsUtils.HISTORY)).append("' ");
            sql.append(" OR ").append(" IFD.START_DATE > '").append(columnMap.get(ConstantsUtils.FUTURE)).append("' )");
        } else if ((columnMap.get(ConstantsUtils.CURRENT) != null && columnMap.get(ConstantsUtils.HISTORY) != null) || (columnMap.get(ConstantsUtils.HISTORY) != null && columnMap.get(ConstantsUtils.CURRENT) != null)) {
            sql.append(" AND ( '").append(columnMap.get(ConstantsUtils.CURRENT)).append("' BETWEEN IFD.START_DATE AND ISNULL(IFD.END_DATE,'").append(columnMap.get(ConstantsUtils.CURRENT)).append("') OR IFD.END_DATE < '").append(columnMap.get(ConstantsUtils.HISTORY)).append("') ");
        } else if ((columnMap.get(ConstantsUtils.HISTORY) != null && columnMap.get(ConstantsUtils.FUTURE) != null) || (columnMap.get(ConstantsUtils.FUTURE) != null && columnMap.get(ConstantsUtils.HISTORY) != null)) {
            sql.append(" AND (").append(" IFD.END_DATE < '").append(columnMap.get(ConstantsUtils.HISTORY)).append("' OR IFD.START_DATE > '").append(columnMap.get(ConstantsUtils.FUTURE)).append("') ");;
        } else if ((columnMap.get(ConstantsUtils.FUTURE) != null && columnMap.get(ConstantsUtils.CURRENT) != null) || (columnMap.get(ConstantsUtils.CURRENT) != null && columnMap.get(ConstantsUtils.FUTURE) != null)) {
            sql.append(" AND ( '").append(columnMap.get(ConstantsUtils.CURRENT)).append("' BETWEEN IFD.START_DATE AND ISNULL(IFD.END_DATE,'").append(columnMap.get(ConstantsUtils.CURRENT)).append("') OR IFD.START_DATE > '").append(columnMap.get(ConstantsUtils.FUTURE)).append("') ");
        } else if (columnMap.get(ConstantsUtils.CURRENT) != null) {
            sql.append(" AND '").append(columnMap.get(ConstantsUtils.CURRENT)).append("' BETWEEN IFD.START_DATE AND ISNULL(IFD.END_DATE,'").append(columnMap.get(ConstantsUtils.CURRENT)).append("') ");
        } else if (columnMap.get(ConstantsUtils.HISTORY) != null) {
            sql.append(" AND ").append(" IFD.END_DATE < '").append(columnMap.get(ConstantsUtils.HISTORY)).append("' ");
        } else if (columnMap.get(ConstantsUtils.FUTURE) != null) {
            sql.append(" AND ").append(" IFD.START_DATE > '").append(columnMap.get(ConstantsUtils.FUTURE)).append("' ");
        }

        if (!coulmnName.toString().equals(StringUtils.EMPTY) && !orderBy.toString().equals(StringUtils.EMPTY)) {
            sql.append(" ORDER BY ").append(coulmnName).append(" ").append(orderBy).append(" " + "OFFSET ");
        }
        sql.append(start);
        sql.append(" ROWS FETCH NEXT ");
        sql.append(end);
        sql.append(" ROWS ONLY;");
        
        List<Object[]> result = HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
        return getCoustomizedViewDTO(result, binder);
    }

    public static List<IFPItemDTO> getCoustomizedViewDTO(List<Object[]> list, final ErrorfulFieldGroup binder){
     try{
        List<IFPItemDTO> resultList=new ArrayList<IFPItemDTO>();
        Map<Integer, String> userMap= StplSecurity.getUserName();
            for (Iterator<Object[]> temp = list.iterator(); temp.hasNext();) {
                Object[] item = temp.next();
                IFPItemDTO resultDTO=new IFPItemDTO();
                int i=0;
                resultDTO.setItemFamilyplanNo(binder.getField(ConstantsUtils.IFP_NO) == null ? StringUtils.EMPTY : binder.getField(ConstantsUtils.IFP_NO).toString());
                resultDTO.setItemFamilyplanName(binder.getField(ConstantsUtils.IFP_NAME) == null ? StringUtils.EMPTY : binder.getField(ConstantsUtils.IFP_NAME).toString());

                resultDTO.setForm(CommonUtils.loadDescription(Integer.valueOf(item[0].toString())));
                /* you will get brand system id, using that get barand and test */
                resultDTO.setBrand(StringUtils.isBlank(item[1].toString()) || ConstantsUtils.ZERO.equals(item[1].toString()) ? StringUtils.EMPTY : BrandMasterLocalServiceUtil.getBrandMaster(Integer.valueOf(item[1].toString())).getBrandName());
                resultDTO.setItemId(String.valueOf(item[2]));
                resultDTO.setItemNo(String.valueOf(item[3]));
                resultDTO.setItemName(String.valueOf(item[4]));
                resultDTO.setPackageSize(item[5] == null || ConstantsUtils.NULL.equals(String.valueOf(item[5])) || ConstantsUtils.ZERO.equals(String.valueOf(item[5])) || StringUtils.isBlank(String.valueOf(item[5]))  ? StringUtils.EMPTY : CommonUtils.loadDescription(Integer.valueOf(String.valueOf(item[5]))));
                resultDTO.setPrimaryUom(item[6] == null ? StringUtils.EMPTY : CommonUtils.loadDescription(Integer.valueOf(String.valueOf(item[6]))));
                resultDTO.setStrength(ConstantsUtils.ZERO.equals(item[7].toString()) || StringUtils.isBlank(item[7].toString()) ? StringUtils.EMPTY : CommonUtils.loadDescription(Integer.valueOf(item[7].toString())));
                resultDTO.setTherapeuticClass(item[8] == null || StringUtils.isBlank(String.valueOf(item[8])) || Integer.valueOf(String.valueOf(item[8])) == 0 ? StringUtils.EMPTY : CommonUtils.loadDescription(Integer.parseInt(String.valueOf(item[8]))));
                resultDTO.setItemFamilyplanStatus(String.valueOf(item[9])==ConstantsUtils.NULL?helperListUtil.getIdHelperDTOMap().get(0):helperListUtil.getIdHelperDTOMap().get(Integer.parseInt(String.valueOf(item[9]))));
                resultDTO.setDisplayIFPStatus(resultDTO.getItemFamilyplanStatus().getId()==0 ? StringUtils.EMPTY  : resultDTO.getItemFamilyplanStatus().getDescription());
                if(item[10]!=null){
                resultDTO.setItemFamilyplanStartDate((Date)item[10]);
                }
                 if(item[11]!=null){
                resultDTO.setItemFamilyplanEndDate((Date)item[11]);
                }
                resultDTO.setCreatedBy(userMap.get(Integer.valueOf(String.valueOf(item[12]))));
                resultDTO.setCreatedDate((Date)item[13]);
                resultDTO.setModifiedBy(userMap.get(Integer.valueOf(String.valueOf(item[14]))));
                resultDTO.setModifiedDate((Date)item[15]);
                resultDTO.setAttachedDate((Date)item[16]);
                if(item[17]!=null){
                resultDTO.setItemStartDate((Date)item[17]);
                }
                 if(item[18]!=null){
                resultDTO.setItemEndDate((Date)item[18]);
                }
                resultDTO.setItemStatus(StringUtils.isBlank(String.valueOf(item[19])) || Integer.valueOf(item[19].toString())==0 ? StringUtils.EMPTY: CommonUtils.loadDescription(Integer.valueOf(item[19].toString())));
                resultDTO.setItemDesc(item[20] == null || StringUtils.isBlank(String.valueOf(item[20])) ? StringUtils.EMPTY: String.valueOf(item[20]));
                resultList.add(resultDTO);
            }
            return resultList;
     }catch(Exception e)
     {
            LOGGER.error(e);

            return null;
        }
    }

    public List<TempItemDTO> getSelectedTableResult(int start,int end,List<OrderByColumn> list) throws SystemException {
        final String ifpSystemId = String.valueOf(sessionDTO.getSystemId());
        String column = ConstantsUtils.ITEM_NO_CAPS;
        String orderBy = ConstantsUtils.ASC;

        for (final Iterator<OrderByColumn> iterator = list.iterator(); iterator.hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
            if (ConstantsUtils.ITEM_NO.equals(orderByColumn.getName())) {
                column = ConstantsUtils.ITEM_NO_CAPS;
            } else if (ConstantsUtils.ITEM_NAME.equals(orderByColumn.getName())) {
                column = ConstantsUtils.ITEM_NAME_CAPS;
            }
            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                orderBy = ConstantsUtils.ASC;
            } else {
                orderBy = ConstantsUtils.DESC;
            }
        }
        final List<Object[]> result=(List<Object[]>)ImtdIfpDetailsLocalServiceUtil.getTempCFPLazyList(ifpSystemId, null, null, "Selected", start, end, column, orderBy);
        return getCustomizedDTO(result,null);
    }

    public void checkAllLogic(int value) {
        LOGGER.info("Entering checkAllLogic");
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String createdDate = String.valueOf(sessionDTO.getSessionDate());
        ImtdIfpDetailsLocalServiceUtil.checkAll(userId, sessionId, createdDate, value, null, null, null, null);
        LOGGER.info("Entering checkAllLogic");
    }

    public void updateAll() {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String sysId = String.valueOf(sessionDTO.getSessionDate());
        ImtdIfpDetailsLocalServiceUtil.updateAll(userId, sessionId, null, null, sysId, null, null, null);
    }

    
    public static int getIdByDescription(final String desc)  {
        try {
            final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
            LOGGER.info("Entering getDropDownList by Name");
            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(HelperTable.class);
             ifpDynamicQuery.add(RestrictionsFactoryUtil.like("description",desc));
            final List<HelperTable> list = HelperTableLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    final HelperTable helperTable = (HelperTable) list.get(i);
                    helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                            .getDescription()));
                }
                int systemId=list.get(0).getHelperTableSid();
                return systemId;
            }
            LOGGER.info("retrns customized Dropdown size-" + helperList.size());

        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return 0;
    }
     public static void saveToTempEdit(final List<IFPItemDTO> list) throws PortalException, SystemException{

        for (Iterator<IFPItemDTO> temp = list.iterator(); temp.hasNext();) {
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            IFPItemDTO item = temp.next();
            ImtdIfpDetails tempModel = null;
            if(item.getTempIFPDetailsSystemId()==0){
                tempModel = ImtdIfpDetailsLocalServiceUtil.createImtdIfpDetails(0);
            }else{
            tempModel = ImtdIfpDetailsLocalServiceUtil.getImtdIfpDetails(item.getTempIFPDetailsSystemId());
            }
            tempModel.setCheckBox(item.getCheckbox());
            int status=item.getItemFamilyplanStatus().getId();
            tempModel.setIfpDetailsAttachedStatus(status);
            tempModel.setIfpDetailsAttachedDate(new Date());
            tempModel.setIfpDetailsCreatedBy(item.getCreatedById());
            tempModel.setIfpDetailsCreatedDate(item.getCreatedDate());
            tempModel.setIfpDetailsStartDate(item.getItemFamilyplanStartDate());
            tempModel.setIfpDetailsEndDate(item.getItemFamilyplanEndDate());
            tempModel.setIfpDetailsSid(item.getIfpDetailsSystemId());
            if (tempModel.getIfpDetailsSid() == 0) {
                tempModel.setOperation(ConstantsUtils.A);
                tempModel.setIfpDetailsModifiedBy(userId);
                tempModel.setIfpDetailsModifiedDate(new Date());
            } else {
                if (!item.isCheckFlag()) {
                    tempModel.setOperation(ConstantsUtils.A);
                    tempModel.setIfpDetailsModifiedBy(userId);
                    tempModel.setIfpDetailsModifiedDate(new Date());
                } else {
                    tempModel.setOperation(ConstantsUtils.U);
                    tempModel.setIfpDetailsModifiedBy(userId);
                    tempModel.setIfpDetailsModifiedDate(new Date());
                }
            }
            ImtdIfpDetailsLocalServiceUtil.updateImtdIfpDetails(tempModel);
        }
    }
    /**
     * to validate startDate & status in Items Tab
     * @param type
     * @return
     */
    public List<Object> validateNull(String type) {

        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        return (List<Object>) ImtdIfpDetailsLocalServiceUtil.validateTempIFPDeatils(userId, sessionId, null, type, null, null, null, null);
    }

    public void deleteNotesTabAttachment(int ifpSystemId) {
        try {
            final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MasterDataFiles.class);
            dynamicQuery.add(RestrictionsFactoryUtil.eq("masterTableSid", ifpSystemId));
            dynamicQuery.add(RestrictionsFactoryUtil.like("masterTableName",ConstantsUtils.IFP_MODEL_SID_DB));
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
    
    private String getUserFilter(String lastName, String firstName) throws PortalException, SystemException {
        List<Object> loggedUserDetails = null;
        String userIds = ConstantsUtils.PERCENCTAGE;
        DynamicQuery userQuery = DynamicQueryFactoryUtil.forClass(User.class);
        userQuery.add(RestrictionsFactoryUtil.like("lastName", lastName));
        if (!StringUtils.isBlank(firstName) && !ConstantsUtils.NULL.equalsIgnoreCase(firstName)) {
            userQuery.add(RestrictionsFactoryUtil.like("firstName", firstName));
        }
        userQuery.add(RestrictionsFactoryUtil.like("lastName", lastName));
        try {
            ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
            projectionList.add(ProjectionFactoryUtil.distinct((ProjectionFactoryUtil.property("userId"))));
            userQuery.setProjection(projectionList);
            loggedUserDetails = UserLocalServiceUtil.dynamicQuery(userQuery);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        if (loggedUserDetails != null) {
            userIds = CommonUtils.stringListToString(CommonUtils.objectListToStringList(loggedUserDetails));
        }
        return userIds;
    }
    public List<Object> getFieldsForSecurity(String moduleName, String tabName){
        List<Object> resultList = new ArrayList<Object>();
        try {
            resultList = ImtdIfpDetailsLocalServiceUtil.fetchFieldsForSecurity(moduleName,tabName,null,null,null);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }

    /**
     * 
     * @param searchItemForm
     * @param filterSet
     * @return
     * @throws SystemException
     * @throws PortalException 
     */
    public int getIFPCountForSearch(final ErrorfulFieldGroup searchItemForm, final Set<Container.Filter> filterSet) throws SystemException, PortalException {
        LOGGER.info("Entering getSearchCount");
       
        String ifpId;
        String ifpNo;
        String ifpName;
        int ifpType=0;
        int ifpStatus=0;
        String itemId;
        String itemNo;
        String itemName;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

       try{
            Map<String, Object> parameters = new HashMap<String, Object>();
            if (searchItemForm.getField(ConstantsUtils.TEXT1).getValue() == null) {

                ifpId = StringUtils.EMPTY;
            } else {

                ifpId = searchItemForm.getField(ConstantsUtils.TEXT1).getValue().toString().trim();
            }
                
           
            if (searchItemForm.getField(ConstantsUtils.TEXT2).getValue() == null) {

                ifpNo = StringUtils.EMPTY;
            } else {


                ifpNo = searchItemForm.getField(ConstantsUtils.TEXT2).getValue().toString().trim();
            }

           
            if (searchItemForm.getField(ConstantsUtils.TEXT3).getValue() == null) {

                ifpName = StringUtils.EMPTY;
            } else {

                ifpName = searchItemForm.getField(ConstantsUtils.TEXT3).getValue().toString().trim();
            }

           
            if (searchItemForm.getField(ConstantsUtils.COMBO2).getValue() == null || ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO2).getValue())) {

                ifpType = 0;
            } else {
                 final HelperDTO helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO2).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

               ifpType  =  Integer.valueOf(helperDTO.getId());
             
            }
            }

            
            if (searchItemForm.getField(ConstantsUtils.COMBO1).getValue() == null || ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO2).getValue())) {

                ifpStatus = 0;
            } else {
                
                final HelperDTO helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO1).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

               ifpStatus  =  Integer.valueOf(helperDTO.getId());
            }
            }
            
            
            if (searchItemForm.getField(ConstantsUtils.TEXT6)!=null && searchItemForm.getField(ConstantsUtils.TEXT6).getValue()!=null) {
            
            itemId = searchItemForm.getField(ConstantsUtils.TEXT6).getValue()
                    .toString().trim();
            
        } else {
            itemId = StringUtils.EMPTY;
        }

        if (searchItemForm.getField(ConstantsUtils.TEXT7)!=null && searchItemForm.getField(ConstantsUtils.TEXT7).getValue()!=null) {
            
            itemNo = searchItemForm.getField(ConstantsUtils.TEXT7).getValue()
                    .toString().trim();
            
        } else {
            itemNo = StringUtils.EMPTY;
            
        }

        if (searchItemForm.getField(ConstantsUtils.TEXT8)!=null && searchItemForm.getField(ConstantsUtils.TEXT8).getValue()!=null) {
            
            itemName = searchItemForm.getField(ConstantsUtils.TEXT8).getValue()
                    .toString().trim();
            
        } else {
            itemName = StringUtils.EMPTY;
        }
            
            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(IfpModel.class);

            // Added to check INBOUND_STATUS is A. ETL Soft delete data should not be included, where INBOUND_STATUS is D
            ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));
           
            if (StringUtils.isNotBlank(ifpId)) {
                ifpId = ifpId.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
              
                ifpDynamicQuery.add(RestrictionsFactoryUtil.like(
                        ConstantsUtils.IFP_MODEL_ID, ifpId));
            }
            if (StringUtils.isNotBlank(ifpNo)) {
                ifpNo = ifpNo.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
                ifpDynamicQuery.add(RestrictionsFactoryUtil.like(
                        ConstantsUtils.IFP_MODEL_NO, ifpNo));
            }
            if (StringUtils.isNotBlank(ifpName)) {
                ifpName = ifpName.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
                ifpDynamicQuery.add(RestrictionsFactoryUtil.like(
                        ConstantsUtils.IFP_MODEL_NAME, ifpName));
            }
            if (StringUtils.isNotBlank(itemId)) {
                itemId = itemId.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
             
            }
            if (StringUtils.isNotBlank(itemNo)) {
                itemNo = ifpName.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
                
            }
            if (StringUtils.isNotBlank(itemName)) {
                itemName = itemName.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
                
            }
            if (ifpType!=0) {
                List<HelperDTO> list = getDropDownList(UIUtils.IFP_TYPE);
                for (HelperDTO list1 : list) {
                if (list1.getId()==ifpType){
                        int sysId = list1.getSystemId();
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.IFP_MODEL_TYPE, sysId));
                    }
                }

            }

           if (ifpStatus!=0) {
                List<HelperDTO> list = getDropDownList(UIUtils.STATUS);
                for (HelperDTO list1 : list) {
                if (list1.getId()==ifpStatus){
                        int sysId = list1.getSystemId();
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.IFP_MODEL_STATUS, sysId));
                    }
                }

            }
           
           if (filterSet != null) {
               for (Container.Filter filter : filterSet) {
                   if (filter instanceof SimpleStringFilter) {
                       SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                       String filterString = stringFilter.getFilterString();
                                       parameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);
                   } else if (filter instanceof Between) {
                       Between betweenFilter = (Between) filter;
                       
                           Date startValue = (Date) betweenFilter.getStartValue();
                           Date endValue = (Date) betweenFilter.getEndValue();
                           
                           parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(startValue)));
                           
                           parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(endValue)));
                      

                   } else if (filter instanceof Compare) {

                       Compare compare = (Compare) filter;
                       
                       Compare.Operation operation = compare.getOperation();
                       if (ConstantsUtils.IFP_SYSTEM_ID.equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
                           String value = String.valueOf(compare.getValue());
                           if (operation.GREATER.toString().equals(operation.name())) {
                               parameters.put(compare.getPropertyId() + ConstantsUtils.FROM, value);
                           } else if (operation.LESS.toString().equals(operation.name())) {
                               parameters.put(compare.getPropertyId() + ConstantsUtils.TO, value);
                           } else if (operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
                               parameters.put(compare.getPropertyId() + ConstantsUtils.EQUAL, value);
                           }
                       } else {
                           Date value = (Date) compare.getValue();
                           if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                               parameters.put(compare.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(value)));
                           } else {
                               parameters.put(compare.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(value)));
                           }
                       }

                   } else if (filter instanceof And) {
                       
                    And stringFilter = (And) filter;
                    
                    Collection<Filter> value = stringFilter.getFilters();
                    for (Filter filter1 : value) {
                        Object propertyId = StringUtils.EMPTY;
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            propertyId = less.getPropertyId();
                            parameters.put(less.getPropertyId() + ConstantsUtils.TO, String.valueOf(less.getValue()));
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            propertyId = greater.getPropertyId();
                            parameters.put(greater.getPropertyId() + ConstantsUtils.FROM, String.valueOf(greater.getValue()));
                        }
                    }
                }
               }
           }
            List resultList =ImtdIfpDetailsLocalServiceUtil.getIFPSearchList(ifpId, ifpNo, ifpName, ifpType, ifpStatus,itemId,  itemNo,  itemName,  0,  0,  null, null,true, parameters);
           
            
            return resultList.size();
       }catch(Exception e)
       {
LOGGER.error(e);
            return 0;
        }
    }
    

    /**
     * 
     * @param searchItemForm
     * @param start
     * @param end
     * @param sortByColumns
     * @param filterSet
     * @return
     * @throws SystemException
     * @throws PortalException
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    public List<SearchResultsDTO> getResultsForIFP(
            final ErrorfulFieldGroup searchItemForm, final int start, final int end,
            final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet) throws SystemException,
            PortalException, Exception {
        
        LOGGER.info("Entering searchIFP , p1:" + start + ", p2:" + end + ", p3: " + ((sortByColumns==null)?sortByColumns:sortByColumns.size()));
        String ifpId;
        String ifpNo;
        String ifpName;
        String itemId;
        String itemNo;
        String itemName;
        int ifpType=0;
        int ifpcreatedBy=0;
        int createdBy=0;
        int modifiedBy=0;
        int ifpStatus=0;
        String orderBy =ConstantsUtils.ASC;
        String column = ConstantsUtils.IFP_MODEL_SID_DB;
        Map<String, Object> parameters = new HashMap<String, Object>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
      
        if (searchItemForm.getField(ConstantsUtils.TEXT1).getValue() == null) {
            ifpId = StringUtils.EMPTY;
        } else {
            ifpId = searchItemForm.getField(ConstantsUtils.TEXT1).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantsUtils.TEXT2).getValue() == null) {
            ifpNo = StringUtils.EMPTY;
        } else {

            ifpNo = searchItemForm.getField(ConstantsUtils.TEXT2).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantsUtils.TEXT3).getValue() == null) {
            ifpName = StringUtils.EMPTY;
        } else {
            ifpName = searchItemForm.getField(ConstantsUtils.TEXT3).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantsUtils.COMBO2).getValue() == null || ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO2).getValue())) {
            ifpType = 0;
        } else {
            
             final HelperDTO helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO2).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

               ifpType  = Integer.valueOf(helperDTO.getId());
            }
            
          
        }
        if (searchItemForm.getField(ConstantsUtils.COMBO2).getValue() == null || ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO2).getValue())) {
            ifpcreatedBy = 0;
        } else {
            
             final HelperDTO helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO2).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

               ifpcreatedBy  = Integer.valueOf(helperDTO.getId());
            }
            
          
        }
         if (searchItemForm.getField(ConstantsUtils.COMBO2).getValue() == null || ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO2).getValue())) {
            createdBy = 0;
        } else {
            
             final HelperDTO helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO2).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

               createdBy  = Integer.valueOf(helperDTO.getId());
            }
            
          
        }
          if (searchItemForm.getField(ConstantsUtils.COMBO2).getValue() == null || ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO2).getValue())) {
            modifiedBy = 0;
        } else {
            
             final HelperDTO helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO2).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

               modifiedBy  = Integer.valueOf(helperDTO.getId());
            }
            
          
        }

        if (searchItemForm.getField(ConstantsUtils.COMBO1).getValue() == null|| ConstantsUtils.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO1).getValue())) {
            ifpStatus = 0;
        } else {
            
                 final HelperDTO helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO1).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

               ifpStatus  = Integer.valueOf(helperDTO.getId());
            }
        }
        
        if (searchItemForm.getField(ConstantsUtils.TEXT6)!=null && searchItemForm.getField(ConstantsUtils.TEXT6).getValue()!=null) {
            
            itemId = searchItemForm.getField(ConstantsUtils.TEXT6).getValue()
                    .toString().trim();
            
        } else {
            itemId = StringUtils.EMPTY;
        }

        if (searchItemForm.getField(ConstantsUtils.TEXT7)!=null && searchItemForm.getField(ConstantsUtils.TEXT7).getValue()!=null) {
            
            itemNo = searchItemForm.getField(ConstantsUtils.TEXT7).getValue()
                    .toString().trim();
            
        } else {
            itemNo = StringUtils.EMPTY;
            
        }

        if (searchItemForm.getField(ConstantsUtils.TEXT8)!=null && searchItemForm.getField(ConstantsUtils.TEXT8).getValue()!=null) {
            
            itemName = searchItemForm.getField(ConstantsUtils.TEXT8).getValue()
                    .toString().trim();
            
        } else {
            itemName = StringUtils.EMPTY;
        }



        if (StringUtils.isNotBlank(ifpId)) {
            ifpId = ifpId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
            
        }
        if (StringUtils.isNotBlank(ifpNo)) {
            ifpNo = ifpNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
           
        }
        if (StringUtils.isNotBlank(ifpName)) {
            ifpName = ifpName.replace(CommonUtils.CHAR_ASTERISK,
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
            if (ifpType!=0) {
            List<HelperDTO> list = getDropDownList(UIUtils.IFP_TYPE);
            for (HelperDTO list1 : list) {
                if (list1.getId()==ifpType){
                    int sysId = list1.getSystemId();
                }
            }

        }
            if (ifpStatus!=0) {
            List<HelperDTO> list = getDropDownList(UIUtils.STATUS);
            for (HelperDTO list1 : list) {
                if (list1.getId()==ifpStatus){
                    int sysId = list1.getSystemId();
                }
            }

        }
            
            
        for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator
                .hasNext();) {
            final SortByColumn sortByColumn = (SortByColumn) iterator.next();
            String columnName = sortByColumn.getName();

            if (ConstantsUtils.IFP_SYSTEM_ID.equals(columnName)) {
                column = ConstantsUtils.IFP_MODEL_SID_DB;
            } else if (ConstantsUtils.IFP_SYS_ID.equals(columnName)) {
                column = "IFP_ID";
            } else if (ConstantsUtils.IFPNO.equals(columnName)) {
                column = "IFP_NO";
            } else if ("ifpName".equals(columnName)) {
                column = "IFP_NAME";
            } else if ("ifpType".equals(columnName)) {
                column = "IFP_TYPE";
            } else if ("ifpStatus".equals(columnName)) {
                column = "IFP_STATUS";
            } else if ("ifpCategory".equals(columnName)) {
                column = "IFP_CATEGORY";
            } else if (ConstantsUtils.IFP_START_DATE.equals(columnName)) {
                column = "IFP_START_DATE";
            } else if (ConstantsUtils.IFP_END_DATE.equals(columnName)) {
                column = "IFP_END_DATE";
            } else if ("ifpDesignation".equals(columnName)) {
                column = "IFP_DESIGNATION";
            } else if (ConstantsUtils.PARENT_IFP_ID.equals(columnName)) {
                column = "PARENT_IFP_ID";
            } else if (ConstantsUtils.PARENT_IFP_NAME.equals(columnName)) {
                column = "PARENT_IFP_NAME";
            }
            
            if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                    orderBy = ConstantsUtils.ASC;
                } else {
                    orderBy = ConstantsUtils.DESC;
                }
        }
        
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString();
                    parameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                   
                        Date startValue = (Date) betweenFilter.getStartValue();
                        Date endValue = (Date) betweenFilter.getEndValue();
                        parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(startValue)));
                        parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(endValue)));
                } else if (filter instanceof Compare) {

                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    if (ConstantsUtils.IFP_SYSTEM_ID.equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
                        String value = String.valueOf(compare.getValue());
                        if (operation.GREATER.toString().equalsIgnoreCase(operation.name())) {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.FROM, value);
                        } else if (operation.LESS.toString().equalsIgnoreCase(operation.name())) {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.TO, value);
                        } else if (operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.EQUAL, value);
                        }
                    } 
                    else if (compare.getValue() instanceof Date) {
                        Date value = (Date) compare.getValue();
                        if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(value)));
                        } else {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(value)));
                        }
                    }

                } else if (filter instanceof And) {
                    And stringFilter = (And) filter;
                    Collection<Filter> value = stringFilter.getFilters();
                    for (Filter filter1 : value) {
                        Object propertyId = StringUtils.EMPTY;
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            propertyId = less.getPropertyId();
                            parameters.put(less.getPropertyId() + ConstantsUtils.TO, String.valueOf(less.getValue()));
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            propertyId = greater.getPropertyId();
                            parameters.put(greater.getPropertyId() + ConstantsUtils.FROM, String.valueOf(greater.getValue()));
                        }
                    }
                }
            }
        }
        List result = ImtdIfpDetailsLocalServiceUtil.getIFPSearchList(ifpId, ifpNo, ifpName, ifpType, ifpStatus,itemId,  itemNo,  itemName,  start,  end,  column, orderBy,false, parameters);    
        
        
        LOGGER.info("return ifp list ,size = " + ((result==null)?result:result.size()));
        return getCustomizedObjectModel(result);
    }
    
    public long startDateValidation(int userId,String sessionId) throws SystemException {
        DynamicQuery rebateSchQuery = DynamicQueryFactoryUtil.forClass(ImtdIfpDetails.class);
        rebateSchQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USER_SID, userId));
        rebateSchQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, sessionId));
        rebateSchQuery.add(RestrictionsFactoryUtil.eq("checkBox", new Boolean(true)));
        rebateSchQuery.add(RestrictionsFactoryUtil.gtProperty(ConstantsUtils.IFP_DETAILS_SART_DATE,ConstantsUtils.IFP_DETAILS_ENDDATE));
        long var = ImtdIfpDetailsLocalServiceUtil.dynamicQueryCount(rebateSchQuery);
        return var;
    }
    public static String getBrandName(Object obj) {
        List<Object> list = new ArrayList<>();
        String sql = "SELECT BRAND_NAME FROM BRAND_MASTER WHERE BRAND_MASTER_SID = " + String.valueOf(obj);
        list = HelperTableLocalServiceUtil.executeSelectQuery(sql);
        String brandName = String.valueOf(list.get(0));
        if (brandName == null || ConstantsUtils.NULL.equals(brandName.trim())) {
            return StringUtils.EMPTY;
        } else {
            return brandName;
        }
    }
            }
