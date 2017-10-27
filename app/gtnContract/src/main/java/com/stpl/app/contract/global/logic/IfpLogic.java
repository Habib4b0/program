package com.stpl.app.contract.global.logic;

import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.common.util.HelperListUtil;
import com.stpl.app.contract.dao.impl.DashboardLogicDAOImpl;
import static com.stpl.app.contract.global.logic.RebateScheduleLogic.getImtdFormulaDescList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

import com.stpl.app.contract.dao.impl.IFPLogicDAOImpl;
import com.stpl.app.contract.dashboard.dto.NepFormulaLookUpDTO;
import com.stpl.app.contract.dashboard.dto.PriceProtectionFormulaDTO;
import com.stpl.app.contract.dashboard.dto.TempPricingDTO;
import com.stpl.app.contract.dashboard.dto.TempRebateDTO;
import static com.stpl.app.contract.dashboard.logic.ContractDashboardLogic.constantProperties;
import com.stpl.app.contract.dashboard.logic.DashBoardLogic;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.global.dto.IfpItemDTO;
import com.stpl.app.contract.global.dto.ItemMasterDTO;
import com.stpl.app.contract.global.util.CommonUtils;
import com.stpl.app.contract.ui.ErrorfulFieldGroup;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.FormulaDetailsMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.IfpContract;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.ImtdItemPriceRebateDetails;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.ItemPricingQualifier;
import com.stpl.app.model.RebatePlanMaster;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.FormulaDetailsMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.IfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.IfpContractLocalServiceUtil;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.ImtdIfpDetailsLocalServiceUtil;
import com.stpl.app.service.ImtdItemPriceRebateDetailsLocalServiceUtil;
import com.stpl.app.service.ItemPricingQualifierLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.domain.contract.contractdashboard.DashboardDAO;
import com.stpl.domain.contract.contractdashboard.ItemFamilyplanDAO;
import com.stpl.domain.contract.contractdashboard.globalcontract.ItemFamilyplanLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.HelperUtils;
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
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 * Logic for ItemFamilyPlan.
 *
 * @author
 */
public class IfpLogic implements ItemFamilyplanLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(IfpLogic.class);
    /**
     * The dao.
     */
    private final ItemFamilyplanDAO dao = new IFPLogicDAOImpl();
    //Java date format
    public static String DEFAULT_JAVA_DATE_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";
    //SQL date format
    public static String DEFAULT_SQL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    private final HelperListUtil helperListUtil = HelperListUtil.getInstance();
    static HashMap<String, String> criteria = new HashMap<>();
    public static final SimpleDateFormat DB_DATE = new SimpleDateFormat("yyyy-MM-dd");
    static HashMap<String, String> rsFormulaDbMap = new HashMap<>();
    public static final String YYYY_M_MDD = "yyyy/MM/dd";
    DateFormat dateFormat = new SimpleDateFormat(YYYY_M_MDD);
    Map<String, String> map = new HashMap<>();
    public static final String ATTACHED_DATETO = "attachedDateto";
    DecimalFormat df = new DecimalFormat("#0.00");
    
    public static final String ATTACHED_DATEFROM = "attachedDatefrom";
    public static final String END_DATETO = "endDateto";
    public static final String END_DATEFROM = "endDatefrom";
    
    public static final String START_DATETO = "startDateto";
    public static final String START_DATEFROM = "startDatefrom";
    SessionDTO sessionDTO;
    List<TempPricingDTO> pricingResultList=new ArrayList<>();
    List<TempPricingDTO> priceProtectionResultList=new ArrayList<>();
    List<TempRebateDTO> rebateResultList=new ArrayList<>();
    public boolean excel=false;

    public boolean isExcel() {
        return excel;
    }

    public void setExcel(boolean excel) {
        this.excel = excel;
    }
    
    /**
     * Default Constructor.
     */
    public IfpLogic() {
    }
    public IfpLogic(final SessionDTO sessionDTO) {
        this.sessionDTO=sessionDTO;
    }

    /**
     * Returns the List of ItemMasterDTO based on search field.
     *
     * @param searchField - String contains Item Details.
     * @param value - Value for WildCard
     * @return List of ItemMasterDTO.
     * @throws SystemException
     */
    public List<ItemMasterDTO> getItemForIFP(final String searchField, final String val, int start, int end, final List<SortByColumn> columns, final Set<Container.Filter> searchCriteria) throws SystemException {
        LOGGER.debug("Entering getItemForIFP method");
        final Map<String, String> map = new HashMap<>();
        map.put(Constants.ITEM_NO2, "Item_No");
        map.put(Constants.ITEM_NAME2, "Item_Name");
        map.put(Constants.IFP_NO1, com.stpl.app.contract.abstractsearch.util.ConstantUtil.IFP_NO_LABEL);
        map.put(Constants.IFP_NAME1, com.stpl.app.contract.abstractsearch.util.ConstantUtil.IFP_NAME_LIST);
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NDC_8, Constants.NDC8);
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NDC_9, Constants.NDC9);
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_DESCRIPTION, Constants.ITEM_DESC1);
        map.put("Form", Constants.FORM);
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.STRENGTH, Constants.STRENGTH);
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.THERAPEUTIC_CLASS_PROPERTY, THERAPEUTIC_CLASS);
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BRAND_NAME, Constants.BRAND_NAME);
        map.put("Item Desc", Constants.ITEM_DESC1);
        map.put("Item Status", "ITEM_STATUS");
        List<ItemMasterDTO> itemMasterDTOList;
        String searchVal = map.get(searchField);

        String column = Constants.ITEM_NO_CAPS;
        String orderBy = Constants.ASC;

        for (final Iterator<SortByColumn> iterator = columns.iterator(); iterator.hasNext();) {
            final SortByColumn orderByColumn = (SortByColumn) iterator.next();

            if (Constants.ITEM_NO.equals(orderByColumn.getName())) {
                column = Constants.ITEM_NO_CAPS;
            } else if (Constants.ITEM_NAME.equals(orderByColumn.getName())) {
                column = com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_NAME_LIST;
            } else if ("form".equals(orderByColumn.getName())) {
                column = "hform";
            } else if (com.stpl.app.contract.abstractsearch.util.ConstantUtil.PACKAGE_SIZE.equals(orderByColumn.getName())) {
                column = "hpackage";
            } else if (Constants.ITEM_DESC.equals(orderByColumn.getName())) {
                column = Constants.ITEM_DESC1;
            } else if (Constants.ITEM_STATUS.equals(orderByColumn.getName())) {
                column = "status";
            } else if (Constants.STRENGTH.equals(orderByColumn.getName())) {
                column = Constants.STRENGTH;
            } else if (Constants.THERAPEUTIC_CLASS.equals(orderByColumn.getName())) {
                column = "therapeutic";
            } else if (Constants.BRAND_NAME.equals(orderByColumn.getName())) {
                column = "BRAND";
            }

            if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                orderBy = Constants.ASC;
            } else {
                orderBy = "DESC";
            }
        }

        if (StringUtils.isNotBlank(val)) {
            String value = val.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
             if(value.contains("'")){
                value=value.replace("'", "''");
            }
          if(value.contains("[")||value.contains("]")){
               if(value.contains("[")){
                value=value.replace("[", "~[");
               }if(value.contains("]")){
                value=value.replace("]", "~]");
               }
                value=value+"' escape '~";
            }
            List<Integer> resultList = new ArrayList<>();
            if (Constants.BRAND_NAME.equals(searchVal)) {
                final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);
                dynamicQuery.add(RestrictionsFactoryUtil.like(searchVal, value));
                dynamicQuery.setProjection(ProjectionFactoryUtil.property("brandMasterSid"));
                resultList = BrandMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
            }

            Map<String, Object> filterMap = new HashMap<>();
            filterMap.put(Constants.ITEM_NO, StringUtils.EMPTY);
            filterMap.put(Constants.ITEM_NAME, StringUtils.EMPTY);
            filterMap.put("form", StringUtils.EMPTY);
            filterMap.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PACKAGE_SIZE, StringUtils.EMPTY);

            if (searchCriteria != null) {
                for (Container.Filter filter : searchCriteria) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterText = "%" + stringFilter.getFilterString() + "%";

                        if (stringFilter.getPropertyId().equals(Constants.ITEM_NO)) {
                            filterMap.put(Constants.ITEM_NO, filterText);
                        } else if (stringFilter.getPropertyId().equals(Constants.ITEM_NAME)) {
                            filterMap.put(Constants.ITEM_NAME, filterText);
                        } else if (stringFilter.getPropertyId().equals("form")) {

                            String form = stringFilter.getFilterString();
                            filterMap.put("form", form);

                        } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PACKAGE_DESC)) {
                            String packageSize = stringFilter.getFilterString();

                            filterMap.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PACKAGE_SIZE, packageSize);
                        } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_STATUS)) {

                            String status = stringFilter.getFilterString();
                            filterMap.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_STATUS, status);

                        } else if (stringFilter.getPropertyId().equals(ConstantUtil.STRENGTH)) {
                            String strength = stringFilter.getFilterString();
                            filterMap.put(ConstantUtil.STRENGTH, strength);
                        } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.THERAPEUTIC_CLASS)) {

                            String therapeutic = stringFilter.getFilterString();
                            filterMap.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.THERAPEUTIC_CLASS, therapeutic);

                        } else if (stringFilter.getPropertyId().equals(Constants.ITEM_DESC)) {
                            filterMap.put(Constants.ITEM_DESC, filterText);
                        } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BRAND)) {
                            filterMap.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BRAND, filterText);
                        }

                    }
                }
            }

            List result = IfpContractDetailsLocalServiceUtil.findIFP(searchVal, value, resultList, filterMap, start, end, column, orderBy, null);
            itemMasterDTOList = getCustomizedItem(result);

        } else {
            itemMasterDTOList = new ArrayList<>();
        }
        LOGGER.debug("End of getItemForIFP method");
        return itemMasterDTOList;
    }
    public static final String THERAPEUTIC_CLASS = "THERAPEUTIC_CLASS";

    /**
     * Returns the List of ItemMasterDTO based on search field.
     *
     * @param searchField - String contains Item Details.
     * @param value - Value for WildCard
     * @return List of ItemMasterDTO.
     * @throws SystemException
     */
    public List<ItemMasterDTO> getItemIFP(final String searchField, final String val) {
        LOGGER.debug("Entering getItemForIFP method");
        final Map<String, String> map = new HashMap<>();
        map.put(Constants.ITEM_NO2, Constants.ITEM_NO);
        map.put(Constants.ITEM_NAME2, Constants.ITEM_NAME);
        List<ItemMasterDTO> itemMasterDTOList = new ArrayList<>();

        if (StringUtils.isNotBlank(val)) {
            final String value = val.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(map.get(searchField), value));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ne("inboundStatus", "D"));
            final List<ItemMaster> itemMasterList;
            try {
                itemMasterList = dao.dynamicQuery(ifpDynamicQuery);
                itemMasterDTOList = getCustomizedItemList(itemMasterList);
            } catch (SystemException ex) {
                LOGGER.error(ex);
            }

        } else {
            itemMasterDTOList = new ArrayList<>();
        }
        LOGGER.debug("End of getItemForIFP method");
        return itemMasterDTOList;
    }

    /**
     * Returns the count of ItemMasterDTO based on search field.
     *
     * @param searchField - String contains Item Details.
     * @param val
     * @return List of ItemMasterDTO.
     * @throws SystemException
     */
    public int getItemAdditionCount(final String searchField, final String val, final Set<Container.Filter> searchCriteria) throws SystemException {
        LOGGER.debug("Entering getItemAdditionCount method");
        final Map<String, String> map = new HashMap<>();
        map.put(Constants.ITEM_NO2, "Item_No");
        map.put(Constants.ITEM_NAME2, "Item_Name");
        map.put(Constants.IFP_NO1, com.stpl.app.contract.abstractsearch.util.ConstantUtil.IFP_NO_LABEL);
        map.put(Constants.IFP_NAME1, com.stpl.app.contract.abstractsearch.util.ConstantUtil.IFP_NAME_LIST);
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NDC_8, Constants.NDC8);
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NDC_9, Constants.NDC9);
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_DESCRIPTION, Constants.ITEM_DESC1);
        map.put("Form", Constants.FORM);
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.STRENGTH, Constants.STRENGTH);
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.THERAPEUTIC_CLASS_PROPERTY, THERAPEUTIC_CLASS);
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BRAND_NAME, Constants.BRAND_NAME);
        map.put("Item Desc", Constants.ITEM_DESC1);
        map.put("Item Status", "ITEM_STATUS");
        int count = 0;
        String searchVal = map.get(searchField);
        if (StringUtils.isNotBlank(val)) {
            String value = val.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
           if(value.contains("'")){
                value=value.replace("'", "''");
            } 
            if(value.contains("[")){
               if(value.contains("[")){
                value=value.replace("[", "~[");
               }
                value=value+"' escape '~";
            }
            List<Integer> resultList = new ArrayList<>();
            if (Constants.BRAND_NAME.equals(searchVal)) {
                final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);
                dynamicQuery.add(RestrictionsFactoryUtil.like(searchVal, value));
                dynamicQuery.setProjection(ProjectionFactoryUtil.property("brandMasterSid"));
                resultList = BrandMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
            }
            Map<String, Object> filterMap = new HashMap<>();
            filterMap.put(Constants.ITEM_NO, StringUtils.EMPTY);
            filterMap.put(Constants.ITEM_NAME, StringUtils.EMPTY);
            filterMap.put("form", StringUtils.EMPTY);
            filterMap.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PACKAGE_SIZE, StringUtils.EMPTY);

            if (searchCriteria != null) {
                for (Container.Filter filter : searchCriteria) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterText = "%" + stringFilter.getFilterString() + "%";

                        if (stringFilter.getPropertyId().equals(Constants.ITEM_NO)) {
                            filterMap.put(Constants.ITEM_NO, filterText);
                        } else if (stringFilter.getPropertyId().equals(Constants.ITEM_NAME)) {
                            filterMap.put(Constants.ITEM_NAME, filterText);
                        } else if (stringFilter.getPropertyId().equals("form")) {

                            String form = stringFilter.getFilterString();
                            filterMap.put("form", form);

                        } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PACKAGE_DESC)) {
                            String packageSize = stringFilter.getFilterString();

                            filterMap.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PACKAGE_SIZE, packageSize);
                        } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_STATUS)) {

                            String status = stringFilter.getFilterString();
                            filterMap.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_STATUS, status);

                        } else if (stringFilter.getPropertyId().equals(ConstantUtil.STRENGTH)) {
                            String strength = stringFilter.getFilterString();
                            filterMap.put(ConstantUtil.STRENGTH, strength);
                        } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.THERAPEUTIC_CLASS)) {

                            String therapeutic = stringFilter.getFilterString();
                            filterMap.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.THERAPEUTIC_CLASS, therapeutic);

                        } else if (stringFilter.getPropertyId().equals(Constants.ITEM_DESC)) {
                            filterMap.put(Constants.ITEM_DESC, filterText);
                        } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BRAND)) {
                            filterMap.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BRAND, filterText);
                        }

                    }
                }
            }
           List result = IfpContractDetailsLocalServiceUtil.findIFP(searchVal, value, resultList, filterMap, 0, 0, null, null, null);
            count = result.size();

        } else {
            count = 0;
        }
        LOGGER.debug("End of getItemAdditionCount method");
        return count;
    }

    /**
     * Returns the customized list of ItemMasterDTO.
     *
     * @param itemMasterList - List of ItemMaster model.
     * @return List of ItemMasterDTO
     */
    public List<ItemMasterDTO> getCustomizedItemList(final List<ItemMaster> itemMasterList) {
        LOGGER.debug("Entering getCustomizedItemList()");
        final List<ItemMasterDTO> itemDTOList = new ArrayList<>();
        for (int i = 0; i < itemMasterList.size(); i++) {
            final ItemMaster item = itemMasterList.get(i);
            final ItemMasterDTO itemDTOObj = new ItemMasterDTO();

            itemDTOObj.setItemSystemId(String.valueOf(item.getItemMasterSid()));
            itemDTOObj.setItemId(item.getItemId());
            itemDTOObj.setItemNo(item.getItemNo());
            itemDTOObj.setItemName(item.getItemName());
            itemDTOObj.setItemStatus(String.valueOf(item.getItemStatus()));
            itemDTOObj.setPackageSize(item.getPackageSize());
            itemDTOObj.setPrimaryUom(String.valueOf(item.getPrimaryUom()));
            itemDTOObj.setRevisionDate(new Date());
            itemDTOObj.setUniqueDate(String.valueOf(new Date() + "`" + i));
            itemDTOList.add(itemDTOObj);
        }
        LOGGER.debug("End of getCustomizedItemList()");
        return itemDTOList;
    }

    public static List<ItemMasterDTO> getCustomizedItem(List list) {
        List<ItemMasterDTO> tempList = new ArrayList<>();
        if (list != null) {

            for (int i = 0; i < list.size(); i++) {
                int j = 0;
                final Object[] obj = (Object[]) list.get(i);
                ItemMasterDTO tempDTO = new ItemMasterDTO();
                Object value;
                tempDTO.setItemSystemId(obj[j++].toString());
                value = obj[j++];
                tempDTO.setItemId(value != null ? value.toString() : StringUtils.EMPTY);
                value = obj[j++];
                tempDTO.setItemNo(value != null ? value.toString() : StringUtils.EMPTY);
                value = obj[j++];
                tempDTO.setItemName(value != null ? value.toString() : StringUtils.EMPTY);
                value = obj[j++];
                tempDTO.setItemStatusValue(value != null ? value.toString() : StringUtils.EMPTY);
                value = obj[j++];
                tempDTO.setItemStatus(value != null && StringUtils.isNotBlank(value.toString()) ? value.toString() : StringUtils.EMPTY);
                value = obj[j++];
                tempDTO.setPackageSize(value != null && !Constants.ZEROSTRING.equals(String.valueOf(value)) ? value.toString() : StringUtils.EMPTY);
                value = obj[j++];
                tempDTO.setPrimaryUom(value != null ? value.toString() : StringUtils.EMPTY);
                value = obj[j++];
                tempDTO.setForm(value != null && StringUtils.isNotBlank(value.toString()) ? value.toString() : StringUtils.EMPTY);
                value = obj[j++];
                tempDTO.setPackageDesc(value != null && StringUtils.isNotBlank(value.toString()) ? value.toString() : StringUtils.EMPTY);
                value = obj[j++];
                tempDTO.setItemDesc(value != null ? value.toString() : StringUtils.EMPTY);
                value = obj[j++];
                tempDTO.setStrength(value != null && StringUtils.isNotBlank(value.toString()) ? value.toString() : StringUtils.EMPTY);
                value = obj[j++];
                tempDTO.setTherapeuticClass(value != null && StringUtils.isNotBlank(value.toString()) && !Constants.SELECT_ONE.equals(String.valueOf(value))  ? value.toString() : StringUtils.EMPTY);
                value = obj[j++];
                tempDTO.setBrand(value != null && StringUtils.isNotBlank(value.toString()) ? value.toString() : StringUtils.EMPTY);
                tempDTO.setRevisionDate(new Date());

                tempDTO.setUniqueDate(String.valueOf(new Date() + "`" + i));
                 value = obj[j++];
                tempDTO.setSource(value != null && StringUtils.isNotBlank(value.toString()) ? value.toString() : StringUtils.EMPTY);
                value = obj[j++];
                tempDTO.setBrandId(value != null && StringUtils.isNotBlank(value.toString()) ? value.toString() : StringUtils.EMPTY);
                tempList.add(tempDTO);
            }
        }
        return tempList;
    }

    /**
     * Gets the dao.
     *
     * @return the dao
     */
    public ItemFamilyplanDAO getDao() {
        return dao;
    }

    public int getLazySelectedItemCount(BeanSearchCriteria searchCriteria) throws  SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdItemPriceRebateDetails.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.USERS_SID, Integer.parseInt(userId)));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.SESSION_ID, sessionId));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.OPERATION, "D"));

        if (searchCriteria != null && searchCriteria.getFilters() != null) {
            for (Container.Filter filter : searchCriteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterText = "%" + stringFilter.getFilterString() + "%";

                    if (stringFilter.getPropertyId().equals(Constants.ITEM_NO)) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.ITEM_NO, filterText));
                    } else if (stringFilter.getPropertyId().equals(Constants.ITEM_NAME)) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.ITEM_NAME, filterText));
                    } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PACKAGE_SIZE)) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PACKAGE_SIZE, stringFilter.getFilterString()));
                    } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TYPE)) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TYPE, stringFilter.getFilterString()));
                    } else if (stringFilter.getPropertyId().equals("globalitemstatus")) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PS_STATUS, Integer.valueOf(stringFilter.getFilterString())));
                    } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_TYPE)) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_TYPE, stringFilter.getFilterString()));
                    } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_INTERVAL)) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_INTERVAL, Integer.valueOf(stringFilter.getFilterString())));
                    } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_FREQUENCY)) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_FREQUENCY, stringFilter.getFilterString()));
                    } else if (stringFilter.getPropertyId().equals(ATTACHED_STATUS)) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(ATTACHED_STATUS, Integer.valueOf(stringFilter.getFilterString())));
                    } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_TYPE)) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_TYPE, Integer.valueOf(stringFilter.getFilterString())));
                    } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_PLAN_NAME)) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq("rebatePlanSystemId", Integer.valueOf(stringFilter.getFilterString())));
                    }

                } else if (filter instanceof Between) {

                    Between betweenFilter = (Between) filter;
                    Date fromDate = (Date) betweenFilter.getStartValue();
                    Date toDate = (Date) betweenFilter.getEndValue();
                    if (betweenFilter.getPropertyId().equals(ConstantUtil.START_DATE)) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantUtil.START_DATE, fromDate, toDate));

                    } else if (betweenFilter.getPropertyId().equals(ConstantUtil.END_DATE)) {

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantUtil.END_DATE, fromDate, toDate));

                    } else if (betweenFilter.getPropertyId().equals("cpStartDate")) {

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CONTRACT_PRICE_START_DATE, fromDate, toDate));

                    } else if (betweenFilter.getPropertyId().equals("cpEndDate")) {

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CONTRACT_PRICE_END_DATE, fromDate, toDate));

                    } else if (betweenFilter.getPropertyId().equals("ppStartDate")) {

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_START_DATE, fromDate, toDate));

                    } else if (betweenFilter.getPropertyId().equals("ppEndDate")) {

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_END_DATE, fromDate, toDate));

                    } else if (betweenFilter.getPropertyId().equals("revisionDate")) {

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between("revisionDate", fromDate, toDate));

                    } else if (betweenFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ATTACHED_DATE)) {

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ATTACHED_DATE, fromDate, toDate));

                    } else if (betweenFilter.getPropertyId().equals("rebateStartDate")) {

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_REBATE_START_DATE, fromDate, toDate));

                    } else if (betweenFilter.getPropertyId().equals("rebateEndDate")) {

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_REBATE_END_DATE, fromDate, toDate));

                    } else if (betweenFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_REVISION_DATE)) {

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_REVISION_DATE, fromDate, toDate));

                    }
                }
            }
        }

        final Long count = ImtdItemPriceRebateDetailsLocalServiceUtil.dynamicQueryCount(cfpDynamicQuery);
        LOGGER.debug("selected count :" + count);
        return Integer.parseInt(String.valueOf(count));
    }
    public static final String ATTACHED_STATUS = "attachedStatus";

    public int getLazySelectedItemsCount(final Set<Container.Filter> searchCriteria, SessionDTO sessionDTO) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        Map<String, Object> parameters = new HashMap<>();

        if (searchCriteria != null) {
            for (Container.Filter filter : searchCriteria) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterText = "%" + stringFilter.getFilterString() + "%";

                    if (stringFilter.getPropertyId().equals(Constants.ITEM_NO)) {
                        parameters.put("itemNo~", filterText);

                    } else if (stringFilter.getPropertyId().equals(Constants.ITEM_NAME)) {
                        parameters.put("itemName~", filterText);
                    } else if (stringFilter.getPropertyId().equals("form")) {

                        String form = stringFilter.getFilterString();
                        parameters.put("form", form);

                    } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PACKAGE_DESC)) {
                        String packageSize = stringFilter.getFilterString();

                        parameters.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PACKAGE_SIZE, packageSize);
                    } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_STATUS)) {

                        String status = stringFilter.getFilterString();
                        parameters.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_STATUS, status);

                    } else if (stringFilter.getPropertyId().equals(ConstantUtil.STRENGTH)) {
                        String strength = stringFilter.getFilterString();
                        parameters.put(ConstantUtil.STRENGTH, strength);
                    } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.THERAPEUTIC_CLASS)) {

                        String therapeutic = stringFilter.getFilterString();
                        parameters.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.THERAPEUTIC_CLASS, therapeutic);

                    } else if (stringFilter.getPropertyId().equals(Constants.ITEM_DESC)) {
                        parameters.put(Constants.ITEM_DESC, filterText);
                    } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BRAND)) {
                        parameters.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BRAND, filterText);
                    }

                }
            }
        }

        final List<Object[]> returnList = ImtdItemPriceRebateDetailsLocalServiceUtil.getSelectedItemList(userId, sessionId, 0, 0, null, null, parameters, false, null, null, null);
        LOGGER.debug("selected results========== :" + returnList.size());
        return returnList.size();
    }

    public List<IfpItemDTO> getLazySelectedItemDeatils(int start, int end, final List<SortByColumn> orderByColumns, final Set<Container.Filter> searchCriteria, SessionDTO sessionDTO) {
        LOGGER.debug("Start- ---> " + start + " and End --->> " + end);
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        String columnName;
        String dbColumnName = StringUtils.EMPTY;
        Map<String, Object> parameters = new HashMap<>();
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdItemPriceRebateDetails.class);
        final List<IfpItemDTO> itemList = new ArrayList<>();
        ifpDynamicQuery.setLimit(start, end);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.USERS_SID, Integer.parseInt(userId)));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.SESSION_ID, sessionId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.OPERATION, "D"));
        ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property("itemMasterSid"));
        projList.add(ProjectionFactoryUtil.property(Constants.ITEM_NO));
        projList.add(ProjectionFactoryUtil.property(Constants.ITEM_NAME));
        projList.add(ProjectionFactoryUtil.property(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PS_STATUS));
        projList.add(ProjectionFactoryUtil.property(com.stpl.app.contract.abstractsearch.util.ConstantUtil.IMTD_ITEM_PRICE_REBATE_SID));
        ifpDynamicQuery.setProjection(projList);
        boolean asc = false;
        for (final Iterator<SortByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
            final SortByColumn orderByColumn = (SortByColumn) iterator.next();
            columnName = orderByColumn.getName();
            if (Constants.ITEM_NO.equals(columnName)) {
                dbColumnName = Constants.ITEM_NO_CAPS;
            } else if (Constants.ITEM_NAME.equals(columnName)) {
                dbColumnName = com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_NAME_LIST;
            } else if ("form".equals(orderByColumn.getName())) {
                dbColumnName = "form";
            } else if (com.stpl.app.contract.abstractsearch.util.ConstantUtil.PACKAGE_SIZE.equals(orderByColumn.getName())) {
                dbColumnName = "package";
            } else if (Constants.ITEM_DESC.equals(orderByColumn.getName())) {
                dbColumnName = Constants.ITEM_DESC1;
            } else if (Constants.ITEM_STATUS.equals(orderByColumn.getName())) {
                dbColumnName = "status";
            } else if (Constants.STRENGTH.equals(orderByColumn.getName())) {
                dbColumnName = ConstantUtil.STRENGTH;
            } else if (Constants.THERAPEUTIC_CLASS.equals(orderByColumn.getName())) {
                dbColumnName = "therapeutic";
            } else if (Constants.BRAND_NAME.equals(orderByColumn.getName())) {
                dbColumnName = "BRAND";
            }
            if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                asc = true;
                if (dbColumnName != null && !StringUtils.isEmpty(dbColumnName)) {
                    ifpDynamicQuery.addOrder(OrderFactoryUtil.asc(dbColumnName));
                }
            } else {
                asc = false;
                if (dbColumnName != null && !StringUtils.isEmpty(dbColumnName)) {
                    ifpDynamicQuery.addOrder(OrderFactoryUtil.desc(dbColumnName));
                }
            }
        }

        if (searchCriteria != null) {
            for (Container.Filter filter : searchCriteria) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterText = "%" + stringFilter.getFilterString() + "%";

                    if (stringFilter.getPropertyId().equals(Constants.ITEM_NO)) {
                        parameters.put(Constants.ITEM_NO, filterText);
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.ITEM_NO, filterText));

                    } else if (stringFilter.getPropertyId().equals(Constants.ITEM_NAME)) {
                        parameters.put(Constants.ITEM_NAME, filterText);
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.ITEM_NAME, filterText));
                    } else if (stringFilter.getPropertyId().equals("form")) {

                        String form = stringFilter.getFilterString();
                        parameters.put("form", form);

                    } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PACKAGE_DESC)) {
                        String packageSize = stringFilter.getFilterString();

                        parameters.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PACKAGE_SIZE, packageSize);
                    } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_STATUS)) {

                        String status = stringFilter.getFilterString();
                        parameters.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_STATUS, status);

                    } else if (stringFilter.getPropertyId().equals(ConstantUtil.STRENGTH)) {
                        String strength = stringFilter.getFilterString();
                        parameters.put(ConstantUtil.STRENGTH, strength);
                    } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.THERAPEUTIC_CLASS)) {

                        String therapeutic = stringFilter.getFilterString();
                        parameters.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.THERAPEUTIC_CLASS, therapeutic);

                    } else if (stringFilter.getPropertyId().equals(Constants.ITEM_DESC)) {
                        parameters.put(Constants.ITEM_DESC, filterText);
                    } else if (stringFilter.getPropertyId().equals(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BRAND)) {
                        parameters.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BRAND, filterText);
                    }

                }
            }
        }

        final List<Object[]> returnList = ImtdItemPriceRebateDetailsLocalServiceUtil.getSelectedItemList(userId, sessionId, start, end, dbColumnName, asc, parameters, true, null, null, null);
        LOGGER.debug("selected results : " + returnList.size());
        return getCustomizedSelectedItemDTO(returnList, itemList);
    }

    public static List<IfpItemDTO> getCustomizedSelectedItemDTO(List<Object[]> returnList, List<IfpItemDTO> itemList) {
        try {

            if (returnList != null) {
                for (int i = 0; i < returnList.size(); i++) {
                    final IfpItemDTO dto = new IfpItemDTO();
                    final Object[] obj = (Object[]) returnList.get(i);
                    dto.setTempItemPriceRebateSystemId(CommonUtils.getString(obj[0]));
                    dto.setItemNo(CommonUtils.getString(obj[1]));
                    dto.setItemName(CommonUtils.getString(obj[NumericConstants.TWO]));
                    dto.setPackageSize(CommonUtils.getString(obj[NumericConstants.THREE]));
                    dto.setForm(CommonUtils.getString(obj[NumericConstants.FOUR]));
                    dto.setItemDesc(CommonUtils.getString(obj[NumericConstants.FIVE]));
                    dto.setItemStatus(CommonUtils.getString(obj[NumericConstants.SIX]));
                    dto.setStrength(CommonUtils.getString(obj[NumericConstants.SEVEN]));
                    dto.setTherapeuticClass(Constants.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.EIGHT]))? StringUtils.EMPTY : CommonUtils.getString(obj[NumericConstants.EIGHT]));
                    dto.setBrand(CommonUtils.getString(obj[NumericConstants.NINE]));
                    itemList.add(dto);

                }
            }

            return itemList;
        } catch (Exception e) {
             LOGGER.error(e);
            return  Collections.emptyList();
        }
    }

    public List<Object[]> getLazyItemPricingDeatils(int start, int end, Set<Container.Filter> searchCriteria, boolean isCount, String record,boolean isIfpItemsTab, boolean isRemove,List<SortByColumn> sortByColumns) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        StringBuilder sql = new StringBuilder();
        Map<String, Object> parameters = new HashMap<>();
        getParameterList(parameters, searchCriteria);
        if (isCount) {
             if (isIfpItemsTab) {
                sql.append(CustomSQLUtil.get("contractIFPItemSelectTempTableCount"));
            } else {
                sql.append(CustomSQLUtil.get("contractItemPricingSelectTempTableCount"));
            }
        } else {
            if (isIfpItemsTab) {
                sql.append(CustomSQLUtil.get("contractIfpItemSelectTempTableData"));
            } else {
                sql.append(CustomSQLUtil.get("contractItemPricingSelectTempTableData"));
            }
        }
        sql.append(" WHERE USERS_SID = '").append(userId).append("'  AND SESSION_ID ='").append(sessionId).append("' AND OPERATION NOT IN ('D','F')  ");
       
        if(isRemove){
            sql.append(" AND IMTD.CHECK_RECORD = '1' ");
        }

        if (parameters.get(Constants.ITEM_NO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(Constants.ITEM_NO)))) {
            sql.append(" AND IMTD.ITEM_NO LIKE '%").append(String.valueOf(parameters.get(Constants.ITEM_NO))).append("%' ");
        }
        if (parameters.get(Constants.ITEM_NAME) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(Constants.ITEM_NAME)))) {
            sql.append(" AND IMTD.ITEM_NAME LIKE '%").append(String.valueOf(parameters.get(Constants.ITEM_NAME))).append("%' ");
        }
        if (parameters.get(ConstantUtil.ITEM_ID_M) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantUtil.ITEM_ID_M)))) {
            sql.append(" AND IMTD.ITEM_ID LIKE '%").append(String.valueOf(parameters.get(ConstantUtil.ITEM_ID_M))).append("%' ");
        }
        if (parameters.get(ConstantUtil.START_DATE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantUtil.START_DATE)))) {
            sql.append(" AND IMTD.START_DATE >= '").append(String.valueOf(parameters.get(ConstantUtil.START_DATE))).append("' ");
        }
        if (parameters.get(ConstantUtil.END_DATE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantUtil.END_DATE)))) {
            sql.append(" AND IMTD.END_DATE <= '").append(String.valueOf(parameters.get(ConstantUtil.END_DATE))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE)))) {
            sql.append(" AND IMTD.PRICE LIKE '%").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE))).append("%' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TYPE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TYPE)))) {
            sql.append(" AND IMTD.PRICE_TYPE LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TYPE))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CONTRACT_PRICE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CONTRACT_PRICE)))) {
            sql.append(" AND IMTD.CONTRACT_PRICE LIKE '%").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CONTRACT_PRICE))).append("%' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CP_START_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CP_START_DATE_FROM)))) {
            sql.append(" AND IMTD.CONTRACT_PRICE_START_DATE >= '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CP_START_DATE_FROM))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CP_START_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CP_START_DATE_TO)))) {
            sql.append(" AND CAST(IMTD.CONTRACT_PRICE_START_DATE AS DATE) <= '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CP_START_DATE_TO))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CP_END_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CP_END_DATE_FROM)))) {
            sql.append(" AND IMTD.CONTRACT_PRICE_END_DATE >= '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CP_END_DATE_FROM))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CP_END_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CP_END_DATE_TO)))) {
            sql.append(" AND CAST(IMTD.CONTRACT_PRICE_END_DATE AS DATE) <= '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CP_END_DATE_TO))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PS_STATUS) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PS_STATUS)))) {
            sql.append(" AND IMTD.PS_STATUS LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PS_STATUS))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_STATUS_GLOBAL) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_STATUS_GLOBAL)))) {
            sql.append(" AND IMTD.PS_STATUS LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_STATUS_GLOBAL))).append("' ");
        }

        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE)))) {
            sql.append(" AND IMTD.PRICE_TOLERANCE LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_START_DATE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_START_DATE)))) {
            sql.append(" AND IMTD.PRICE_PROTECTION_START_DATE >= '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_START_DATE))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_END_DATE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_END_DATE)))) {
            sql.append(" AND IMTD.PRICE_PROTECTION_END_DATE <= '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_END_DATE))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_TYPE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_TYPE)))) {
            sql.append(" AND IMTD.PRICE_TOLERANCE_TYPE LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_TYPE))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_INTERVAL) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_INTERVAL)))) {
            sql.append(" AND IMTD.PRICE_TOLERANCE_INTERVAL LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_INTERVAL))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_FREQUENCY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_FREQUENCY)))) {
            sql.append(" AND IMTD.PRICE_TOLERANCE_FREQUENCY LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_FREQUENCY))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BASE_PRICE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BASE_PRICE)))) {
            sql.append(" AND IMTD.BASE_PRICE LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BASE_PRICE))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_PRICE_REVISION_DATE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_PRICE_REVISION_DATE)))) {
            sql.append(" AND IMTD.ITEM_PRICE_REVISION_DATE <= '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_PRICE_REVISION_DATE))).append("' ");
        }
        if (parameters.get(ATTACHED_STATUS) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ATTACHED_STATUS)))) {
            sql.append(" AND IMTD.ATTACHED_STATUS LIKE '").append(String.valueOf(parameters.get(ATTACHED_STATUS))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ATTACHED_DATE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ATTACHED_DATE)))) {
            sql.append(" AND IMTD.ATTACHED_DATE <= '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ATTACHED_DATE))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_REVISION_DATE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_REVISION_DATE)))) {
            sql.append(" AND IMTD.REBATE_REVISION_DATE <= '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_REVISION_DATE))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRIMARY_UOM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRIMARY_UOM)))) {
            sql.append(" AND IMTD.PRIMARY_UOM LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRIMARY_UOM))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PACKAGE_SIZE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PACKAGE_SIZE)))) {
            sql.append(" AND IMTD.PACKAGE_SIZE LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PACKAGE_SIZE))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.SOURCE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.SOURCE)))) {
            sql.append(" AND IMTD.SOURCE LIKE '%").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.SOURCE))).append("%' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.SUGGESTED_PRICE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.SUGGESTED_PRICE)))) {
            sql.append(" AND IMTD.SUGGESTED_PRICE LIKE '%").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.SUGGESTED_PRICE))).append("%' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.IFP_STATUS) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.IFP_STATUS)))) {
            sql.append(" AND IMTD.ATTACHED_STATUS LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.IFP_STATUS))).append("' ");
        }
         if (parameters.get(ConstantUtil.STRENGTH) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantUtil.STRENGTH)))) {
            sql.append(" AND  IM.STRENGTH LIKE '").append(String.valueOf(parameters.get(ConstantUtil.STRENGTH))).append("' ");
        }
          if (parameters.get("form") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("form")))) {
            sql.append(" AND IM.FORM LIKE '").append(String.valueOf(parameters.get("form"))).append("' ");
        }
           if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BRAND) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BRAND)))) {
            sql.append(" AND BM.BRAND_NAME LIKE '%").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BRAND))).append("%' ");
        }
         if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.THERAPY_CLASS) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.THERAPY_CLASS)))) {
            sql.append(" AND IM.THERAPEUTIC_CLASS LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.THERAPY_CLASS))).append("' ");
        }
        if (parameters.get(Constants.ITEM_DESC) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(Constants.ITEM_DESC)))) {
            sql.append(" AND  IM.ITEM_DESC LIKE '").append(String.valueOf(parameters.get(Constants.ITEM_DESC))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEMS_STATUS) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEMS_STATUS)))) {
            sql.append(" AND IMTD.PS_STATUS LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEMS_STATUS))).append("' ");
        }
         if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CREATED_BY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CREATED_BY))))  {
            sql.append(" AND  IMTD.CREATED_BY LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CREATED_BY))).append("' ");
        }
       if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.MODIFIED_BY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.MODIFIED_BY)))) {
            sql.append(" AND IMTD.MODIFIED_BY LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.MODIFIED_BY))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CREATED_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CREATED_DATEFROM)))) {
            sql.append(" AND IMTD.CREATED_DATE  >= '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CREATED_DATEFROM))).append("' ");
        }
         if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CREATED_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CREATED_DATETO)))) {
            sql.append(" AND CAST(IMTD.CREATED_DATE AS DATE)  <= '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CREATED_DATETO))).append("' ");
        }
       if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.MODIFIED_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.MODIFIED_DATEFROM)))) {
            sql.append(" AND IMTD.MODIFIED_DATE  >= '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.MODIFIED_DATEFROM))).append("' ");
        }
         if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.MODIFIED_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.MODIFIED_DATETO)))) {
            sql.append(" AND CAST(IMTD.MODIFIED_DATE AS DATE) <= '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.MODIFIED_DATETO))).append("' ");
        }
          if (parameters.get(START_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(START_DATEFROM)))) {
            sql.append(" AND IMTD.START_DATE  >= '").append(String.valueOf(parameters.get(START_DATEFROM))).append("' ");
        }
         if (parameters.get(START_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(START_DATETO)))) {
            sql.append(" AND CAST(IMTD.START_DATE AS DATE)  <= '").append(String.valueOf(parameters.get(START_DATETO))).append("' ");
        }
        if (parameters.get(END_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(END_DATEFROM)))) {
            sql.append(" AND IMTD.END_DATE  >= '").append(String.valueOf(parameters.get(END_DATEFROM))).append("' ");
        }
         if (parameters.get(END_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(END_DATETO)))) {
            sql.append(" AND CAST(IMTD.END_DATE AS DATE)  <= '").append(String.valueOf(parameters.get(END_DATETO))).append("' ");
        }
        if (parameters.get(ATTACHED_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ATTACHED_DATEFROM)))) {
            sql.append(" AND IMTD.ATTACHED_DATE  >= '").append(String.valueOf(parameters.get(ATTACHED_DATEFROM))).append("' ");
        }
         if (parameters.get(ATTACHED_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ATTACHED_DATETO)))) {
            sql.append(" AND CAST(IMTD.ATTACHED_DATE AS DATE)  <= '").append(String.valueOf(parameters.get(ATTACHED_DATETO))).append("' ");
        }
        if (!StringUtils.isBlank(record)) {
            if (record.contains(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)) {
                parameters.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
            if (record.contains(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY)) {
                parameters.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
            if (record.contains(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE)) {
                parameters.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE) != null) {
            sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_BRACKET).append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("' BETWEEN IMTD.START_DATE  AND ISNULL(IMTD.END_DATE,'").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("') ");
            sql.append(" OR ").append(" END_DATE  < '").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY)).append("' ");
            sql.append(" OR ").append(" START_DATE >  '").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE)).append("' )");
        } else if ((parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY) != null) || (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT) != null)) {
            sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_BRACKET).append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("' BETWEEN IMTD.START_DATE  AND ISNULL(IMTD.END_DATE,'").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("') OR IMTD.END_DATE < '").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY)).append("') ");
        } else if ((parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE) != null) || (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY) != null)) {
            sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_OPEN).append(" END_DATE < '").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY)).append("' OR IMTD.START_DATE > '").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE)).append("') ");
        } else if ((parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT) != null) || (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE) != null)) {
            sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_BRACKET).append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("' BETWEEN IMTD.START_DATE AND ISNULL(IMTD.END_DATE,'").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("') OR IMTD.START_DATE > '").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE)).append("') ");
        } else if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT) != null) {
            sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_QUOTE).append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("' BETWEEN IMTD.START_DATE AND ISNULL(IMTD.END_DATE,'").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("') ");
        } else if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY) != null) {
            sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_SPACE).append(" IMTD.END_DATE < '").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY)).append("' ");
        } else if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE) != null) {
            sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_SPACE).append(" IMTD.START_DATE > '").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE)).append("' ");
        }
        
         if (!isCount) {
            boolean sortOrder = false;
            String columnName = null;
            String orderByColumn = null;
            if (sortByColumns != null) {
                for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                    final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                    Map<String,String> coumnMap=loadColumnMap();
                    columnName = sortByColumn.getName();
                    orderByColumn = coumnMap.get(columnName);

                    if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                        sortOrder = false;
                    } else {
                        sortOrder = true;
                    }
                }
            }
            if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
                sql.append(" ORDER BY IMTD.ITEM_MASTER_SID ASC ");
            } else {
                sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ORDER_BY).append(orderByColumn).append(((!sortOrder) ? com.stpl.app.contract.abstractsearch.util.ConstantUtil.ASC_SPACE : com.stpl.app.contract.abstractsearch.util.ConstantUtil.DESC_SPACE));
            }
            sql.append(" OFFSET ").append(start).append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ROWS_FETCH_NEXT).append(end).append(" ROWS ONLY ");
        }
        
        
        LOGGER.debug("==========items sql========" +sql);
        List returnList = HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());

        LOGGER.debug("selected results : " + returnList.size());
        return returnList;
    }
    

    public List<TempPricingDTO> getCustomizedPricingDTO(List<Object[]> returnList,boolean isIfpItemsTab,String Record) {
         DecimalFormat PERCENTFORMAT = new DecimalFormat("###0.00");
        TempPricingDTO itemDTO;
        List<TempPricingDTO> resultList = new ArrayList<>();
        Map<Integer, HelperDTO> idHelperDTOMap = helperListUtil.getIdHelperDTOMap();
        for (Object[] tempifp : returnList) {
            itemDTO = new TempPricingDTO();
            int j = -1;
            itemDTO.setItemSystemId(String.valueOf(tempifp[++j]).equals(Constants.NULL) ? StringUtils.EMPTY : String.valueOf(tempifp[j]));
            itemDTO.setItemNo(String.valueOf(tempifp[++j]).equals(Constants.NULL) ? StringUtils.EMPTY : String.valueOf(tempifp[j]));
            itemDTO.setItemName(String.valueOf(tempifp[++j]).equals(Constants.NULL) ? StringUtils.EMPTY : String.valueOf(tempifp[j]));
            itemDTO.setTempItemPriceRebateSystemId(String.valueOf(tempifp[++j]).equals(Constants.NULL) ? StringUtils.EMPTY : String.valueOf(tempifp[j]));
            itemDTO.setItemId(String.valueOf(tempifp[++j]).equals(Constants.NULL) ? StringUtils.EMPTY : String.valueOf(tempifp[j]));
            itemDTO.setStartDate((Date) tempifp[++j]);
            itemDTO.setEndDate((Date) tempifp[++j]);
            itemDTO.setPrice(String.valueOf(tempifp[++j]).equals(Constants.NULL) ? StringUtils.EMPTY : String.valueOf(PERCENTFORMAT.format(tempifp[j])));
            String priceType = HelperUtils.getString(tempifp[++j]);
            if (priceType.matches("\\d*")) {
                 final HelperDTO helper = new HelperDTO(
                            Integer.valueOf(StringUtils.EMPTY.equals(priceType) ? "0" : priceType), getPriceTypeNameByID(priceType));
                itemDTO.setPriceType(helper);
            }
            itemDTO.setContractprice(String.valueOf(tempifp[++j]).equals(Constants.NULL) ? StringUtils.EMPTY : String.valueOf(tempifp[j]));
            itemDTO.setCpStartDate((Date) tempifp[++j]);
            itemDTO.setCpEndDate((Date) tempifp[++j]);
            Object val = tempifp[++j];
            if ("Y".equals(sessionDTO.getEdit())) {
                itemDTO.setGlobalitemstatus(val == null || StringUtils.isBlank(val.toString()) ? Constants.ZEROSTRING : String.valueOf(val));
             if(isExcel()){
                    itemDTO.setGlobalitemstatus(val == null || StringUtils.isBlank(val.toString()) ? StringUtils.EMPTY  : (idHelperDTOMap.get(Integer.parseInt(String.valueOf(val)))).getDescription());
                }
            } else {
                itemDTO.setGlobalitemstatus(DashBoardLogic.loadDescription(String.valueOf(val).equals(Constants.NULL) || StringUtils.isEmpty(String.valueOf(val)) ? 0 : Integer.valueOf(String.valueOf(val))));
            }
            itemDTO.setPriceTolerance(String.valueOf(tempifp[++j]).equals(Constants.NULL) ? StringUtils.EMPTY : String.valueOf(tempifp[j]));
            itemDTO.setPpStartDate((Date) tempifp[++j]);
            itemDTO.setPpEndDate((Date) tempifp[++j]);
            String str1 = String.valueOf(tempifp[++j]);

            itemDTO.setPriceToleranceType(DashBoardLogic.loadDescription(str1.equals(Constants.NULL) || StringUtils.isEmpty(str1) ? 0 : Integer.valueOf(String.valueOf(tempifp[j]))));

            String str = String.valueOf(tempifp[++j]).equals(Constants.NULL) ? StringUtils.EMPTY : String.valueOf(tempifp[j]);
            itemDTO.setPriceToleranceInterval(str.equals(Constants.NULL) ? Constants.ZEROSTRING : String.valueOf(tempifp[j]));
            String str2 = String.valueOf(tempifp[++j]);

            itemDTO.setPriceToleranceFrequency(str2.equals(Constants.NULL) || StringUtils.isEmpty(str2) ? Constants.ZEROSTRING : String.valueOf(tempifp[j]));

            itemDTO.setBasePrice(String.valueOf(tempifp[++j]).equals(Constants.NULL) ? StringUtils.EMPTY : String.valueOf(tempifp[j]));
            itemDTO.setRevisionDate((Date) tempifp[++j]);
            itemDTO.setAttachedStatus(Constants.NULL.equals(String.valueOf(tempifp[++j])) ? Constants.ZEROSTRING : tempifp[j].toString());
            itemDTO.setIfpStatus(tempifp[j]==null?new HelperDTO(0,ConstantUtil.SELECT_ONE):helperListUtil.getHelperDTObyID((int)tempifp[j]));
            itemDTO.setAttachedDate((Date) tempifp[++j]);
            itemDTO.setCheckbox((Boolean) tempifp[++j]);
            ++j;
            Object primaryUom = tempifp[++j];
            if (primaryUom != null && (Integer) primaryUom != 0) {
                itemDTO.setPrimaryUom(getDescription((Integer) primaryUom));
            }
            Object packageSize = tempifp[++j];
            if (packageSize != null && StringUtils.isNotBlank(String.valueOf(packageSize)) && !Constants.ZEROSTRING.equals(String.valueOf(packageSize)) && !"-Select One-".equals(String.valueOf(packageSize))) {
                itemDTO.setPackageSize(getDescription(Integer.valueOf(String.valueOf(packageSize))));
            }
            Object temp = tempifp[++j];
            itemDTO.setSource(temp == null ? StringUtils.EMPTY : temp.toString());
            temp = tempifp[++j];
            itemDTO.setSuggestedPrice(temp == null || StringUtils.isBlank(temp.toString()) ? Constants.ZEROSTRING : PERCENTFORMAT.format(temp));
            Map<Integer, String> userMap = StplSecurity.userMap;
            try{
            if (userMap.isEmpty()) {
                userMap = StplSecurity.getUserName();
            }
            temp = tempifp[++j];
            itemDTO.setCreatedBy(temp == null || StringUtils.isBlank(temp.toString()) ? StringUtils.EMPTY : userMap.get(Integer.valueOf(String.valueOf(temp))));
            temp = tempifp[++j];
            itemDTO.setCreatedDate((Date) temp);
            }catch(Exception e){
                LOGGER.error(e);
            }
            if (!isIfpItemsTab) {
                temp = tempifp[++j];
                itemDTO.setBrand(String.valueOf(temp));
                
            }
          if (isIfpItemsTab) {
              itemDTO.setItemsStatus(DashBoardLogic.loadDescription(String.valueOf(val).equals(Constants.NULL) || StringUtils.isEmpty(String.valueOf(val)) ? 0 : Integer.valueOf(String.valueOf(val))));
           if( itemDTO.getItemsStatus().trim().equalsIgnoreCase(Constants.SELECT_ONE)){
              itemDTO.setItemsStatus(StringUtils.EMPTY); 
           }
            ++j;
            if (!StringUtils.isBlank(Record)) {
                if (Record.contains(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)) {
                    itemDTO.setRecordType(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT);
                }
                else if (Record.contains(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY)) {
                    itemDTO.setRecordType(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY);
                }
                else if (Record.contains(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE)) {
                    itemDTO.setRecordType(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE);
                } else {
                    itemDTO.setRecordType(StringUtils.EMPTY);
                }
            } else {
               itemDTO.setRecordType(StringUtils.EMPTY); 
            }
             itemDTO.setModifiedBy(userMap.get(Integer.valueOf(sessionDTO.getUserId())));
            temp = tempifp[++j];
            itemDTO.setModifiedDate((Date) temp);
            temp = tempifp[++j];
            if (temp!= null) {
                itemDTO.setBrand(String.valueOf(temp));
            }
             temp = tempifp[++j];
            if (temp != null) {
                itemDTO.setTherapyClass(idHelperDTOMap.get((int)temp) == null||idHelperDTOMap.get((int)temp).getDescription().trim().equals(Constants.SELECT_ONE) ? StringUtils.EMPTY : idHelperDTOMap.get((int) temp).getDescription());
            }
              temp = tempifp[++j];
            if (temp != null) {
                itemDTO.setStrength(idHelperDTOMap.get((int) temp) == null||idHelperDTOMap.get((int)temp).getDescription().trim().equals(Constants.SELECT_ONE) ? StringUtils.EMPTY : idHelperDTOMap.get((int) temp).getDescription());
            }
             temp = tempifp[++j];
            if (temp != null) {
                itemDTO.setForm(idHelperDTOMap.get((int) temp) == null ||idHelperDTOMap.get((int)temp).getDescription().trim().equals(Constants.SELECT_ONE)? StringUtils.EMPTY : idHelperDTOMap.get((int) temp).getDescription());
            }
              temp = tempifp[++j];
                itemDTO.setItemDesc(temp== null ? StringUtils.EMPTY : String.valueOf(temp));
                itemDTO.setRecordType(Record!=null && ! String.valueOf(Record).isEmpty() ? 
                        String.valueOf(Record).replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY):StringUtils.EMPTY);

             }
            resultList.add(itemDTO);
        }
        setPricingResultList(resultList);
        return resultList;
    }

    public static String getDescription(int code) {
        HelperTable dto = HelperTableLocalServiceUtil.createHelperTable(0);
        try {
            dto = HelperTableLocalServiceUtil.getHelperTable(code);
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return dto.getDescription();
    }

    public List<Object[]> getLazyItemRebateDeatils(int start, int end, BeanSearchCriteria searchCriteria, boolean isCount, String record, Boolean isRemove, List<OrderByColumn> list) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        
        StringBuilder sql = new StringBuilder();
        Map<String, Object> parameters = new HashMap<>();
        getParameterList(parameters, searchCriteria);
        if (isCount) {
            sql.append(CustomSQLUtil.get("contractRebateSelectTempTableCount"));
        } else {
            sql.append(CustomSQLUtil.get("contractRebateSelectTempTableData"));
        }
        sql.append(" WHERE USERS_SID ='" + userId + "' AND SESSION_ID ='" + sessionId + "' AND OPERATION NOT IN ('D','F') ");
        
        if(isRemove){
            sql.append(" AND CHECK_RECORD = '1' " );
        }

        if (parameters.get(Constants.ITEM_NO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(Constants.ITEM_NO)))) {
            sql.append(" AND ITEM_NO LIKE '%").append(String.valueOf(parameters.get(Constants.ITEM_NO))).append("%' ");
        }
        if (parameters.get(Constants.ITEM_NAME) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(Constants.ITEM_NAME)))) {
            sql.append(" AND ITEM_NAME LIKE '%").append(String.valueOf(parameters.get(Constants.ITEM_NAME))).append("%' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FORMULA_ID_PROPERTY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FORMULA_ID_PROPERTY)))) {
            sql.append(" AND FORMULA_ID LIKE '%").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FORMULA_ID_PROPERTY))).append("%' ");
        }
        if (parameters.get(ContractUtils.FORMULA_NAME) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ContractUtils.FORMULA_NAME)))) {
            sql.append(" AND FF.FORMULA_NAME LIKE '%").append(String.valueOf(parameters.get(ContractUtils.FORMULA_NAME))).append("%' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_REBATE_START_DATE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_REBATE_START_DATE)))) {
            sql.append(" AND ITEM_REBATE_START_DATE >= '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_REBATE_START_DATE))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_REBATE_END_DATE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_REBATE_END_DATE)))) {
            sql.append(" AND ITEM_REBATE_END_DATE <= '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_REBATE_END_DATE))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_REVISION_DATE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_REVISION_DATE)))) {
            sql.append(" AND REBATE_REVISION_DATE <= '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_REVISION_DATE))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BUNDLE_NO_PROPERTY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BUNDLE_NO_PROPERTY)))) {
            sql.append(" AND BUNDLE_NO LIKE '%").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BUNDLE_NO_PROPERTY))).append("%' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_TYPE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_TYPE)))) {
            sql.append(" AND ITEM_TYPE LIKE '%").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_TYPE))).append("%' ");
        }
        if (parameters.get(ConstantUtil.ITEM_ID_M) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantUtil.ITEM_ID_M)))) {
            sql.append(" AND ITEM_ID LIKE '%").append(String.valueOf(parameters.get(ConstantUtil.ITEM_ID_M))).append("%' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FORMULA_NO_PROPERTY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FORMULA_NO_PROPERTY)))) {
            sql.append(" AND FF.FORMULA_NO LIKE '%").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FORMULA_NO_PROPERTY))).append("%' ");
        }
        if (parameters.get(ContractUtils.NET_SALES_FORMULA_NO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ContractUtils.NET_SALES_FORMULA_NO)))) {
            sql.append(" AND NSFM.NET_SALES_FORMULA_NO LIKE '%").append(String.valueOf(parameters.get(ContractUtils.NET_SALES_FORMULA_NO))).append("%' ");
        }
        if (parameters.get(ContractUtils.NET_SALES_RULE_PROPERTY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ContractUtils.NET_SALES_RULE_PROPERTY)))) {
            sql.append(" AND NET.RULE_NAME LIKE '%").append(String.valueOf(parameters.get(ContractUtils.NET_SALES_RULE_PROPERTY))).append("%' ");
        }
        if (parameters.get(ContractUtils.EVALUATION_RULE_PROPERTY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ContractUtils.EVALUATION_RULE_PROPERTY)))) {
            sql.append(" AND EVA.RULE_NAME LIKE '%").append(String.valueOf(parameters.get(ContractUtils.EVALUATION_RULE_PROPERTY))).append("%' ");
        }
        if (parameters.get(ContractUtils.EVALUATION_RULE_BUNDLE_PROPERTY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ContractUtils.EVALUATION_RULE_BUNDLE_PROPERTY)))) {
            sql.append(" AND IMTD.EVALUATION_RULE_BUNDLE LIKE '%").append(String.valueOf(parameters.get(ContractUtils.EVALUATION_RULE_BUNDLE_PROPERTY))).append("%' ");
        }
        if (parameters.get(ContractUtils.CALCULATION_RULE_PROPERTY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ContractUtils.CALCULATION_RULE_PROPERTY)))) {
            sql.append(" AND CAL.RULE_NAME LIKE '%").append(String.valueOf(parameters.get(ContractUtils.CALCULATION_RULE_PROPERTY))).append("%' ");
        }
        if (parameters.get(ContractUtils.CALCULATION_RULE_BUNDLE_PROPERTY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ContractUtils.CALCULATION_RULE_BUNDLE_PROPERTY)))) {
            sql.append(" AND IMTD.CALCULATION_RULE_BUNDLE LIKE '%").append(String.valueOf(parameters.get(ContractUtils.CALCULATION_RULE_BUNDLE_PROPERTY))).append("%' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_PLAN_NO_PROPERTY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_PLAN_NO_PROPERTY)))) {
            sql.append(" AND RPM.REBATE_PLAN_NO LIKE '%").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_PLAN_NO_PROPERTY))).append("%' ");
        }
        if (parameters.get(Constants.DEDUCTION_CALENDAR_NO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(Constants.DEDUCTION_CALENDAR_NO)))) {
            sql.append(" AND DC.DEDUCTION_CALENDAR_NO LIKE '%").append(String.valueOf(parameters.get(Constants.DEDUCTION_CALENDAR_NO))).append("%' ");
        }
        if (parameters.get(ContractUtils.CALCULATION_RULE_BUNDLE_PROPERTY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ContractUtils.CALCULATION_RULE_BUNDLE_PROPERTY)))) {
            sql.append(" AND IMTD.CALCULATION_RULE_BUNDLE LIKE '%").append(String.valueOf(parameters.get(ContractUtils.CALCULATION_RULE_BUNDLE_PROPERTY))).append("%' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_PLAN_NAME) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_PLAN_NAME)))) {
            sql.append(" AND RPM.REBATE_PLAN_NAME LIKE '%").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_PLAN_NAME))).append("%' ");
        }
        if (parameters.get(ATTACHED_STATUS) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ATTACHED_STATUS)))) {
            sql.append(" AND IMTD.RS_ATTACHED_STATUS = ").append(String.valueOf(parameters.get(ATTACHED_STATUS)));
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_START_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_START_DATEFROM)))) {
            sql.append(" AND IMTD.ITEM_REBATE_START_DATE >='").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_START_DATEFROM))).append("'");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_START_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_START_DATETO)))) {
            sql.append(" AND IMTD.ITEM_REBATE_START_DATE <='").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_START_DATETO))).append("'");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_END_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_END_DATEFROM)))) {
            sql.append(" AND IMTD.ITEM_REBATE_END_DATE >='").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_END_DATEFROM))).append("'");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_END_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_END_DATETO)))) {
            sql.append(" AND IMTD.ITEM_REBATE_END_DATE <='").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_END_DATETO))).append("'");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.DEDUCTION_CALENDAR_NAME) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.DEDUCTION_CALENDAR_NAME)))){
            sql.append(" AND DC.DEDUCTION_CALENDAR_NAME LIKE '%").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.DEDUCTION_CALENDAR_NAME))).append("%' ");
        }

        if (!StringUtils.isBlank(record)) {
            if (record.contains(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)) {
                parameters.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
            if (record.contains(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY)) {
                parameters.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
            if (record.contains(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE)) {
                parameters.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE) != null) {
            sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_BRACKET).append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("' BETWEEN START_DATE AND  ISNULL(END_DATE,'").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("') ");
            sql.append(" OR ").append("  END_DATE  < '").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY)).append("' ");
            sql.append(" OR ").append(" START_DATE > '").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE)).append("' )");
        } else if ((parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY) != null) || (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT) != null)) {
            sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_BRACKET).append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("' BETWEEN START_DATE  AND ISNULL(END_DATE,'").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("') OR END_DATE < '").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY)).append("') ");
        } else if ((parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE) != null) || (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY) != null)) {
            sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_OPEN).append("  END_DATE < '").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY)).append("' OR START_DATE > '").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE)).append("') ");
        } else if ((parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT) != null) || (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE) != null)) {
            sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_BRACKET).append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("' BETWEEN START_DATE AND ISNULL(END_DATE,'").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("') OR START_DATE > '").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE)).append("') ");
        } else if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT) != null) {
            sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_QUOTE).append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("' BETWEEN START_DATE AND ISNULL(END_DATE,'").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("') ");
        } else if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY) != null) {
            sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_SPACE).append(" END_DATE < '").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY)).append("' ");
        } else if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE) != null) {
            sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_SPACE).append(" START_DATE > '").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE)).append("' ");
        }
        
         if (!isCount) {
            boolean sortOrder = false;
            String columnName = null;
            String orderByColumn = null;
            if (list != null) {
                for (final Iterator<OrderByColumn> iterator = list.iterator(); iterator.hasNext();) {
                    final OrderByColumn sortByColumn = (OrderByColumn) iterator.next();
                    Map<String,String> coumnMap = loadItemRebateColumnMap();
                    columnName = sortByColumn.getName();
                    orderByColumn = coumnMap.get(columnName);

                    if (sortByColumn.getType() == OrderByColumn.Type.ASC) {
                        sortOrder = false;
                    } else {
                        sortOrder = true;
                    }
                }
            }
            if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
                sql.append(" ORDER BY ITEM_MASTER_SID ASC ");
            } else {
                sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ORDER_BY).append(orderByColumn).append(((!sortOrder) ? com.stpl.app.contract.abstractsearch.util.ConstantUtil.ASC_SPACE : com.stpl.app.contract.abstractsearch.util.ConstantUtil.DESC_SPACE));
            }
            sql.append(" OFFSET ").append(start).append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ROWS_FETCH_NEXT).append(end).append(" ROWS ONLY ");
        }
        
        List returnList = HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
        LOGGER.debug("selected results :" + returnList.size());
        return returnList;
    }

    public List<TempRebateDTO> getCustomizedRebateDTO(List<Object[]> returnList, String recordType) throws SystemException {
        TempRebateDTO itemDTO;
        HashMap<Integer, String> hm = new HashMap<>();
        List<TempRebateDTO> itemList = new ArrayList<>();
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanMaster.class);
        ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property("rebatePlanMasterSid"));
        projList.add(ProjectionFactoryUtil.property(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_PLAN_NAME));
        ifpDynamicQuery.setProjection(projList);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.like(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_PLAN_NAME, "%"));
        final List<Object[]> rpList = ImtdItemPriceRebateDetailsLocalServiceUtil.dynamicQuery(ifpDynamicQuery);

        for (Object[] rpObjects : rpList) {
            hm.put((Integer) rpObjects[0], (String) (rpObjects[1] == null ? StringUtils.EMPTY : rpObjects[1]));
        }
        for (Object[] tempifp : returnList) {
            itemDTO = new TempRebateDTO();
            int j = -1;
            itemDTO.setRecordType(StringUtils.isBlank(recordType) ? StringUtils.EMPTY : recordType.replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY));
            itemDTO.setItemSystemId(String.valueOf(tempifp[++j]).equals(Constants.NULL) ? StringUtils.EMPTY : String.valueOf(tempifp[j]));
            itemDTO.setItemNo(String.valueOf(tempifp[++j]).equals(Constants.NULL) ? StringUtils.EMPTY : String.valueOf(tempifp[j]));
            itemDTO.setItemName(String.valueOf(tempifp[++j]).equals(Constants.NULL) ? StringUtils.EMPTY : String.valueOf(tempifp[j]));
            itemDTO.setTempItemPriceRebateSystemId(String.valueOf(tempifp[++j]).equals(Constants.NULL) ? StringUtils.EMPTY : String.valueOf(tempifp[j]));
            String formula;
            if (tempifp[++j] != null) {
                formula = String.valueOf(tempifp[j]);
                itemDTO.setFormulaId(formula);
            }

            itemDTO.setFormulaName(String.valueOf(tempifp[++j]).equals(Constants.NULL) ? StringUtils.EMPTY : String.valueOf(tempifp[j]));
            itemDTO.setRebateAmount(String.valueOf(tempifp[++j]).equals(Constants.NULL) ? StringUtils.EMPTY : String.valueOf(tempifp[j]));
            itemDTO.setRebateStartDate((Date) tempifp[++j]);
            itemDTO.setRebateEndDate((Date) tempifp[++j]);
            Object value = tempifp[++j];
            if (value != null) {
                itemDTO.setRebateRevisionDate((Date) value);
            }
            itemDTO.setBundleNo(String.valueOf(tempifp[++j]).equals(Constants.NULL) ? StringUtils.EMPTY : String.valueOf(tempifp[j]));
            int rp = String.valueOf(tempifp[++j]).equals(Constants.NULL) ? 0 : Integer.valueOf(tempifp[j].toString());
            itemDTO.setRebatePlanName(hm.get(rp));
            itemDTO.setCheckbox((Boolean) tempifp[++j]);
            itemDTO.setItemType(String.valueOf(tempifp[++j]).equals(Constants.NULL) ? StringUtils.EMPTY : String.valueOf(tempifp[j]));
            if (!String.valueOf(itemDTO.getItemType()).isEmpty() && !Constants.ZEROSTRING.equalsIgnoreCase(itemDTO.getItemType()) && String.valueOf(itemDTO.getItemType()).matches(Constants.NUM_VALIDATION)) {
                itemDTO.setItemTypeId(Integer.valueOf(itemDTO.getItemType()));
                itemDTO.setItemType(com.stpl.app.contract.contractheader.util.CommonUtils.loadDescription(Integer.valueOf(itemDTO.getItemType())));
            } else {
                itemDTO.setItemType(StringUtils.EMPTY);
            }
            itemDTO.setItemId(String.valueOf(tempifp[++j]));
            if (!Constants.NULL.equalsIgnoreCase(String.valueOf(tempifp[++j])) && StringUtils.isNotBlank(String.valueOf(tempifp[j]))) {
                itemDTO.setAttachedStatus(helperListUtil.getIdHelperDTOMap().get(Integer.parseInt(String.valueOf(tempifp[j]))));
            }
            if (!Constants.NULL.equalsIgnoreCase(String.valueOf(tempifp[++j])) && StringUtils.isNotBlank(String.valueOf(tempifp[j]))) {
                itemDTO.setFormulaNo(String.valueOf(tempifp[j]));
            }
            if (!Constants.NULL.equalsIgnoreCase(String.valueOf(tempifp[++j])) && StringUtils.isNotBlank(String.valueOf(tempifp[j]))) {
                itemDTO.setNetSalesFormulaNo(String.valueOf(tempifp[j]));
            }
            if (!Constants.NULL.equalsIgnoreCase(String.valueOf(tempifp[++j])) && StringUtils.isNotBlank(String.valueOf(tempifp[j]))) {
                itemDTO.setNetSalesRule(String.valueOf(tempifp[j]));
            }
            if (!Constants.NULL.equalsIgnoreCase(String.valueOf(tempifp[++j])) && StringUtils.isNotBlank(String.valueOf(tempifp[j]))) {
                itemDTO.setEvaluationRule(String.valueOf(tempifp[j]));
            }
            if (!Constants.NULL.equalsIgnoreCase(String.valueOf(tempifp[++j])) && StringUtils.isNotBlank(String.valueOf(tempifp[j]))) {
                itemDTO.setEvaluationRuleBundle(String.valueOf(tempifp[j]));
            }
            if (!Constants.NULL.equalsIgnoreCase(String.valueOf(tempifp[++j])) && StringUtils.isNotBlank(String.valueOf(tempifp[j]))) {
                itemDTO.setCalculationRule(String.valueOf(tempifp[j]));
            }
            if (!Constants.NULL.equalsIgnoreCase(String.valueOf(tempifp[++j])) && StringUtils.isNotBlank(String.valueOf(tempifp[j]))) {
                itemDTO.setCalculationRuleBundle(String.valueOf(tempifp[j]));
            }
            if (!Constants.NULL.equalsIgnoreCase(String.valueOf(tempifp[++j])) && StringUtils.isNotBlank(String.valueOf(tempifp[j]))) {
                itemDTO.setRebatePlanNo(String.valueOf(tempifp[j]));
            }
            if (!Constants.NULL.equalsIgnoreCase(String.valueOf(tempifp[++j])) && StringUtils.isNotBlank(String.valueOf(tempifp[j]))) {
                itemDTO.setDeductionCalendarNo(String.valueOf(tempifp[j]));
            }
            if (!Constants.NULL.equalsIgnoreCase(String.valueOf(tempifp[++j])) && StringUtils.isNotBlank(String.valueOf(tempifp[j]))) {
                itemDTO.setDeductionCalendarName(String.valueOf(tempifp[j]));
            }
            if (!Constants.NULL.equalsIgnoreCase(String.valueOf(tempifp[++j])) && StringUtils.isNotBlank(String.valueOf(tempifp[j]))) {
                itemDTO.setAttachedDate((Date) tempifp[j]);
            }

            DynamicQuery query = DynamicQueryFactoryUtil.forClass(FormulaDetailsMaster.class);
            List<Integer> formulaIdList = getImtdFormulaDescList(Integer.parseInt(itemDTO.getItemSystemId()), sessionDTO);

            List<String> newList = new ArrayList<>(formulaIdList.size());
            for (Integer myInt : formulaIdList) {
                newList.add(String.valueOf(myInt));
            }

            if (formulaIdList != null && !formulaIdList.isEmpty()) {
                query.add(RestrictionsFactoryUtil.in(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FORMULA_ID_PROPERTY, newList));
                query.add(RestrictionsFactoryUtil.eq(ConstantUtil.ITEM_ID_M, itemDTO.getItemId()));
                query.add(RestrictionsFactoryUtil.le(ConstantUtil.START_DATE, new Date()));
                query.addOrder(OrderFactoryUtil.desc(ConstantUtil.START_DATE));
                List<FormulaDetailsMaster> companyTradeClass = FormulaDetailsMasterLocalServiceUtil.dynamicQuery(query);

                if (companyTradeClass != null && !companyTradeClass.isEmpty()) {

                    String formulaNo = companyTradeClass.get(0).getFormulaNo();
                    itemDTO.setFormulaId(String.valueOf(companyTradeClass.get(0).getFormulaId()));
                    itemDTO.setFormulaNo(formulaNo);
                    itemDTO.setFormulaName(companyTradeClass.get(0).getFormulaDesc());
                }
            }

            itemList.add(itemDTO);
        }
        setRebateResultList(itemList);
        return itemList;
    }

    public void loadTempIFP(Integer contractSystemId, Integer cfpContractSId, Integer ifpContractSId, Integer psContractSId, Integer rsContractSId) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String tempDate = String.valueOf(sessionDTO.getSessionDate());
        final List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(userId);
        input.add(sessionId);
        input.add(tempDate);
        ImtdItemPriceRebateDetailsLocalServiceUtil.deleteAll(input, "Load");
        if (rsContractSId == 0 && psContractSId == 0) {
            input.add(contractSystemId);
            input.add((cfpContractSId == 0) ? null : cfpContractSId);
            input.add((ifpContractSId == 0) ? null : ifpContractSId);
            ImtdItemPriceRebateDetailsLocalServiceUtil.loadTempItemdetails(input, "com.contractDashboard.process.loadTempIFP");
        } else if (rsContractSId == 0) {
            input.add(contractSystemId);
            input.add((cfpContractSId == 0) ? null : cfpContractSId);
            input.add((ifpContractSId == 0) ? null : ifpContractSId);
            input.add(psContractSId);
            input.add((cfpContractSId == 0) ? null : cfpContractSId);

            ImtdItemPriceRebateDetailsLocalServiceUtil.loadTempItemdetails(input, "com.contractDashboard.process.loadTempPS");
        } else {
            input.add(contractSystemId);
            input.add((cfpContractSId == 0) ? null : cfpContractSId);
            input.add((ifpContractSId == 0) ? null : ifpContractSId);
            input.add((psContractSId == 0) ? null : psContractSId);
            input.add((rsContractSId == 0) ? null : rsContractSId);

            input.add((psContractSId == 0) ? null : psContractSId);
            input.add((ifpContractSId == 0) ? null : ifpContractSId);

            ImtdItemPriceRebateDetailsLocalServiceUtil.loadTempItemdetails(input, "com.contractDashboard.process.loadTempRS");
        }
    }
    
    public void loadImtdItemPriceRebateDetails(Integer contractSystemId, Integer cfpContractSId, Integer ifpContractSId, Integer psContractSId, Integer rsContractSId) {

        final List<Object> input = new ArrayList<>();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String tempDate = String.valueOf(sessionDTO.getSessionDate());
        input.add(userId);
        input.add(sessionId);
        input.add(tempDate);
        input.add(contractSystemId);
        ImtdItemPriceRebateDetailsLocalServiceUtil.deleteAll(input, "Load");
        if (ifpContractSId == 0) {
            if (psContractSId == 0) {
                if (rsContractSId != 0) {
                    input.add((cfpContractSId == 0) ? null : cfpContractSId);
                    input.add(rsContractSId);
                    ImtdItemPriceRebateDetailsLocalServiceUtil.loadTempItemdetails(input, "com.contractDashboard.process.loadImtdCRS");
                }
            } else {
                if (rsContractSId == 0) {
                    input.add((cfpContractSId == 0) ? null : cfpContractSId);
                    input.add(psContractSId);
                    ImtdItemPriceRebateDetailsLocalServiceUtil.loadTempItemdetails(input, "com.contractDashboard.process.loadImtdCPS");
                } else {
                    input.add((cfpContractSId == 0) ? null : cfpContractSId);
                    input.add(psContractSId);
                    input.add(rsContractSId);
                    input.add(psContractSId);
                    ImtdItemPriceRebateDetailsLocalServiceUtil.loadTempItemdetails(input, "com.contractDashboard.process.loadImtdCPSRS");
                }
            }
        } else {
            if (psContractSId == 0) {
                if (rsContractSId == 0) {
                    loadTempIFP(contractSystemId, cfpContractSId, ifpContractSId, psContractSId, rsContractSId);
                } else {
                    input.add((cfpContractSId == 0) ? null : cfpContractSId);
                    input.add(ifpContractSId);
                    input.add(rsContractSId);
                    input.add(ifpContractSId);
                    ImtdItemPriceRebateDetailsLocalServiceUtil.loadTempItemdetails(input, "com.contractDashboard.process.loadImtdCIFPRS");
                }
            } else {
                loadTempIFP(contractSystemId, cfpContractSId, ifpContractSId, psContractSId, rsContractSId);
            }
        }
    }

    public void addToTempIFP(Object searchField, String searchValue) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final int contractSystemId = (Integer) sessionDTO.getContractSystemId();
        final int cfpSystemId = (Integer) sessionDTO.getCfpSystemId();
        final int ifpSystemId = (Integer) sessionDTO.getIfpSystemId();
        final int psSystemId = (Integer) sessionDTO.getPsSystemId();
        final int rsSystemId = (Integer) sessionDTO.getRsSystemId();
        final String tempDate = String.valueOf(sessionDTO.getSessionDate());
        final Map<String, String> map = new HashMap<>();
        map.put(Constants.ITEM_NO2, Constants.ITEM_NO_CAPS);
        map.put(Constants.ITEM_NAME2, com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_NAME_LIST);
        map.put(Constants.IFP_NO1, com.stpl.app.contract.abstractsearch.util.ConstantUtil.IFP_NO_LABEL);
        map.put(Constants.IFP_NAME1, com.stpl.app.contract.abstractsearch.util.ConstantUtil.IFP_NAME_LIST);
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NDC_8, Constants.NDC8);
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NDC_9, Constants.NDC9);
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_DESCRIPTION, Constants.ITEM_DESC1);
        map.put("Form", Constants.FORM);
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.STRENGTH, Constants.STRENGTH);
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.THERAPEUTIC_CLASS_PROPERTY, THERAPEUTIC_CLASS);
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BRAND_NAME, "BRAND_NAME");
        final SimpleDateFormat tempFromat = new SimpleDateFormat(CommonUtils.MMDDYYYY);
        final List<Object> input = new ArrayList<>(NumericConstants.SIXTEEN);
        input.add(userId);
        input.add(sessionId);
        input.add(map.get(searchField));
        input.add(searchValue.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT));
        input.add(contractSystemId);
        input.add(cfpSystemId);
        input.add(ifpSystemId);
        if (rsSystemId == 0 && psSystemId == 0) {
            input.add(userId);
            input.add(sessionId);
            input.add(tempDate);
            input.add(userId);
            input.add(tempDate);
            input.add(userId);
            input.add(tempFromat.format(new Date()));
            input.add(map.get(searchField));
            input.add(searchValue.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT));
            input.add(userId);
            input.add(sessionId);
            input.add(userId);
            input.add(sessionId);
            ImtdItemPriceRebateDetailsLocalServiceUtil.loadTempItemdetails(input, "com.contractDashboard.process.addTempIFP");
        } else if (rsSystemId == 0) {
            input.add(psSystemId);
            input.add(userId);
            input.add(sessionId);
            input.add(tempDate);
            input.add(userId);
            input.add(tempDate);
            input.add(userId);
            input.add(tempFromat.format(new Date()));
            input.add(map.get(searchField));
            input.add(searchValue.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT));
            input.add(userId);
            input.add(sessionId);
            if (Constants.IFP_NO1.equalsIgnoreCase(String.valueOf(searchField)) || Constants.IFP_NAME1.equalsIgnoreCase(String.valueOf(searchField))) {
                ImtdItemPriceRebateDetailsLocalServiceUtil.loadTempItemdetails(input, "com.contractDashboard.process.addTempPSForIfp");
            } else if (com.stpl.app.contract.abstractsearch.util.ConstantUtil.BRAND_NAME.equalsIgnoreCase(String.valueOf(searchField))) {
                ImtdItemPriceRebateDetailsLocalServiceUtil.loadTempItemdetails(input, "com.contractDashboard.process.addTempPSForBranch");
            } else {
                ImtdItemPriceRebateDetailsLocalServiceUtil.loadTempItemdetails(input, "com.contractDashboard.process.addTempPS");
            }   
        } else {
            input.add(psSystemId);
            input.add(rsSystemId);
            input.add(userId);
            input.add(sessionId);
            input.add(tempDate);
            input.add(userId);
            input.add(tempDate);
            input.add(map.get(searchField));
            input.add(searchValue.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT));
            input.add(userId);
            input.add(sessionId);
            if (Constants.IFP_NO1.equalsIgnoreCase(String.valueOf(searchField)) || Constants.IFP_NAME1.equalsIgnoreCase(String.valueOf(searchField))) {
                ImtdItemPriceRebateDetailsLocalServiceUtil.loadTempItemdetails(input, "com.contractDashboard.process.addTempRSForIfp");
            } else if (com.stpl.app.contract.abstractsearch.util.ConstantUtil.BRAND_NAME.equalsIgnoreCase(String.valueOf(searchField))) {
                ImtdItemPriceRebateDetailsLocalServiceUtil.loadTempItemdetails(input, "com.contractDashboard.process.addTempRSForBranch");
            } else {
                ImtdItemPriceRebateDetailsLocalServiceUtil.loadTempItemdetails(input, "com.contractDashboard.process.addTempRS");
            }
        }
    }

    public void clearTempIFP() {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(userId);
        input.add(sessionId);
        ImtdItemPriceRebateDetailsLocalServiceUtil.deleteAll(input, "Back");
        ImtdItemPriceRebateDetailsLocalServiceUtil.deleteTempRsContractTableRecords(0, 0, userId, sessionId);
    }

    public void populateToTempIFP(Object populateField, Object populateValue, Boolean flag) {
        final SimpleDateFormat tempFromat = new SimpleDateFormat(CommonUtils.MMDDYYYY);
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final Map<String, String> map = new HashMap<>();
        map.put("Status", "Attached_Status");
        map.put("IFP Status", "Attached_Status");
        map.put("IFP Start Date", "Start_Date");
        map.put("IFP End Date", "End_Date");
        map.put("Price Protection Start Date", "Price_Protection_Start_Date");
        map.put("Price Protection End Date", "Price_Protection_End_Date");
        map.put("Base Price", "Base_Price");
        map.put("Price Tolerance", "Price_Tolerance");
        map.put("Contract Price", "Contract_Price");
        map.put("CP Start Date", "Contract_Price_Start_Date");
        map.put("CP End Date", "Contract_Price_End_Date");
        map.put("Start Date", "Start_Date");
        map.put("End Date", "End_Date");
        map.put("Attached Status", "ATTACHED_STATUS");
        map.put(Constants.ITEM_STATUS1, "PS_STATUS");
        map.put("Price", "Price");
        map.put("Price Type", "Price_Type");
        map.put("Price Tolerance Type", "Price_Tolerance_Type");
        map.put("Price Tolerance Interval", "Price_Tolerance_Interval");
        map.put("Price Tolerance Frequency", "Price_Tolerance_Frequency");
        map.put(Constants.CHECK_BOX1, com.stpl.app.contract.abstractsearch.util.ConstantUtil.CHECK_RECORD_LIST_NAME);
        map.put("Suggested Price", "SUGGESTED_PRICE");
        final List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(map.get(populateField.toString()));
        input.add(populateValue);
        input.add(userId);
        input.add(tempFromat.format(new Date()));
        input.add(userId);
        input.add(sessionId);
        if (flag) {
            ImtdItemPriceRebateDetailsLocalServiceUtil.massPopulateAll(input, null);
        } else {
            input.add(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CHECK_RECORD_CAPTION);
            ImtdItemPriceRebateDetailsLocalServiceUtil.massPopulate(input, null);
        }
    }

    public void populateToTempRebate(Object populateField, Object populateValue, Boolean flag) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final SimpleDateFormat tempFromat = new SimpleDateFormat(CommonUtils.MMDDYYYY);
        final Map<String, String> map = new HashMap<>();
        map.put("Rebate Amount", "Rebate_Amount");
        map.put("Start Date", "Item_Rebate_Start_Date");
        map.put("End Date", "Item_Rebate_End_Date");
        map.put(Constants.CHECK_BOX1, com.stpl.app.contract.abstractsearch.util.ConstantUtil.CHECK_RECORD_LIST_NAME);
        map.put("Bundle No", "BUNDLE_NO");
        map.put("Rebate Plan Name", "REBATE_PLAN_SYSTEM_ID");
        map.put("RS Status", "RS_ATTACHED_STATUS");
        map.put("Evaluation Rule Bundle","EVALUATION_RULE_BUNDLE");
        map.put("Calculation Rule Bundle","CALCULATION_RULE_BUNDLE");
        final List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(map.get(String.valueOf(populateField)));
        input.add(populateValue);
        input.add(userId);
        input.add(tempFromat.format(new Date()));
        input.add(userId);
        input.add(sessionId);
        if (flag) {
            ImtdItemPriceRebateDetailsLocalServiceUtil.massPopulateAll(input, null);
        } else {
            input.add(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CHECK_RECORD_CAPTION);
            ImtdItemPriceRebateDetailsLocalServiceUtil.massPopulate(input, null);
        }
    }

    public static Boolean saveToTempIFP(List<TempPricingDTO> saveList, boolean isEdit) throws PortalException, SystemException {
        List<ImtdItemPriceRebateDetails> saveDetailsList = new ArrayList<>();

        for (TempPricingDTO temp : saveList) {
            ImtdItemPriceRebateDetails tempResult = ImtdItemPriceRebateDetailsLocalServiceUtil.getImtdItemPriceRebateDetails(Integer.parseInt(temp.getTempItemPriceRebateSystemId()));
            tempResult.setStartDate(temp.getStartDate());
            tempResult.setEndDate(temp.getEndDate());
            tempResult.setCheckRecord(temp.getCheckbox());
            tempResult.setAttachedStatus(temp.getIfpStatus()==null? 0 :temp.getIfpStatus().getId());
            tempResult.setPrice(Double.valueOf(temp.getPrice().isEmpty()?Constants.ZEROSTRING:temp.getPrice()));
            tempResult.setBasePrice(StringUtils.isBlank(temp.getBasePrice()) ? 0 : Double.valueOf(temp.getBasePrice()));
            tempResult.setContractPrice(StringUtils.isBlank(temp.getContractprice()) ? 0 : Double.valueOf(temp.getContractprice()));
            tempResult.setContractPriceStartDate(temp.getCpStartDate());
            tempResult.setContractPriceEndDate(temp.getCpEndDate());
            tempResult.setPriceProtectionStartDate(temp.getPpStartDate());
            tempResult.setPriceProtectionEndDate(temp.getPpEndDate());
            tempResult.setPsStatus(Integer.valueOf(temp.getGlobalitemstatus()!=null ? temp.getGlobalitemstatus() : Constants.ZEROSTRING));
            tempResult.setPriceTolerance(StringUtils.isBlank(temp.getPriceTolerance()) ? 0 : Double.valueOf(temp.getPriceTolerance()));
            tempResult.setPriceToleranceFrequency(temp.getPriceToleranceFrequency());
            tempResult.setPriceToleranceInterval(temp.getPriceToleranceInterval()==null || temp.getPriceToleranceInterval().equals(Constants.NULL) || StringUtils.isBlank(temp.getPriceToleranceInterval()) ? 0 : Integer.valueOf(temp.getPriceToleranceInterval()));
            tempResult.setPriceToleranceType(String.valueOf(DashBoardLogic.getHelperCode("PRICE_TOLERANCE_TYPE", temp.getPriceToleranceType())));
            tempResult.setPriceType(temp.getPriceType() == null ? 0 : temp.getPriceType().getId());
            tempResult.setRebateRevisionDate(temp.getRevisionDate());
            if (StringUtils.isNotBlank(temp.getBrandMasterSID()) && !Constants.NULL.equals(temp.getBrandMasterSID())
                    && !Constants.ZEROSTRING.equals(temp.getBrandMasterSID())) {
                tempResult.setBrandMasterSid(String.valueOf(temp.getBrandMasterSID()));
            }
            tempResult.setSource(temp.getSource());
            tempResult.setSuggestedPrice(StringUtils.isNotBlank(temp.getSuggestedPrice())?Double.valueOf(temp.getSuggestedPrice()):0);
            tempResult.setAttachedDate(temp.getAttachedDate());
            tempResult.setModifiedDate(isEdit ? new Date() : temp.getModifiedDate());
            saveDetailsList.add(tempResult);
        }
        List<Object> input = new ArrayList<>(1);
        input.add(saveDetailsList);
        return ImtdItemPriceRebateDetailsLocalServiceUtil.saveItem(input, Constants.SAVE_DETAILS);
    }

    public static Boolean saveToTempRebate(List<TempRebateDTO> saveList, Boolean rebateResetFlag) throws PortalException, SystemException {
        List<ImtdItemPriceRebateDetails> saveDetailsList = new ArrayList<>();

        for (TempRebateDTO temp : saveList) {
            ImtdItemPriceRebateDetails tempResult = ImtdItemPriceRebateDetailsLocalServiceUtil.getImtdItemPriceRebateDetails(Integer.parseInt(temp.getTempItemPriceRebateSystemId()));
            tempResult.setItemRebateStartDate(temp.getRebateStartDate());
            tempResult.setItemRebateEndDate(temp.getRebateEndDate());
            tempResult.setRebateAmount(StringUtils.isBlank(temp.getRebateAmount()) ? 0 : Double.parseDouble(temp.getRebateAmount()));
            if(StringUtils.isNotBlank(temp.getRebatePlanSystemId())){
                tempResult.setRebatePlanSystemId(temp.getRebatePlanSystemId());         
            }
            tempResult.setItemType(temp.getItemTypeId());
            tempResult.setBundleNo(temp.getBundleNo());
            tempResult.setRsCheckRecord(temp.getCheckbox());

            if (rebateResetFlag) {
                tempResult.setRebateAmount(0);
                tempResult.setRebatePlanSystemId(null);
                tempResult.setBundleNo(null);
            }
            if (temp.getAttachedStatus() != null && temp.getAttachedStatus().getId() != 0) {
                tempResult.setRsAttachedStatus(temp.getAttachedStatus().getId());
            }
            if (temp.getDeductionSystemId() != null && StringUtils.isNotBlank(temp.getDeductionSystemId())) {
                tempResult.setDeductionCalendarMasterSid(String.valueOf(temp.getDeductionSystemId()));
            }
            if (temp.getDeductionCalendarName() != null && StringUtils.isNotBlank(temp.getDeductionCalendarName())) {
                tempResult.setRsContractDetailsDeductionCalendarName(temp.getDeductionCalendarName());
            }
            if (temp.getDeductionCalendarNo() != null && StringUtils.isNotBlank(temp.getDeductionCalendarNo())) {
                tempResult.setRsContractDetailsDeductionCalendarNo(temp.getDeductionCalendarNo());
            }
            if (temp.getEvaluationSystemId() != null && StringUtils.isNotBlank(temp.getEvaluationSystemId())) {
                tempResult.setEvaluationRule(Integer.parseInt(temp.getEvaluationSystemId()));
            }
            if (temp.getEvaluationRuleBundle() != null && StringUtils.isNotBlank(temp.getEvaluationRuleBundle())) {
                tempResult.setEvaluationRuleBundle(String.valueOf(temp.getEvaluationRuleBundle()));
            } else {
                tempResult.setEvaluationRuleBundle(null);
            }
            if (temp.getCalculationSystemId() != null && StringUtils.isNotBlank(temp.getCalculationSystemId())) {
                tempResult.setCalculationRule(Integer.parseInt(temp.getCalculationSystemId()));
            }
            if (temp.getCalculationRuleBundle() != null && StringUtils.isNotBlank(temp.getCalculationRuleBundle())) {
                tempResult.setCalculationRuleBundle(String.valueOf(temp.getCalculationRuleBundle()));
            } else {
                tempResult.setCalculationRuleBundle(null);
            }
            if (temp.getSystemID() != null && StringUtils.isNotBlank(temp.getSystemID())) {
                tempResult.setNetSalesFormulaMasterSid(String.valueOf(temp.getSystemID()));
            }
            if (temp.getNetSalesFormulaName() != null && StringUtils.isNotBlank(temp.getNetSalesFormulaName())) {
                tempResult.setRsContractDetailsNetSalesFormulaName(temp.getNetSalesFormulaName());
            }
            if (temp.getNetSalesFormulaNo() != null && StringUtils.isNotBlank(temp.getNetSalesFormulaNo())) {
                tempResult.setRsContractDetailsNetSalesFormulaNo(temp.getNetSalesFormulaNo());
            }
            if (temp.getFormulaSystemId() != null && StringUtils.isNotBlank(temp.getFormulaSystemId())) {
                tempResult.setFormulaId(temp.getFormulaSystemId());
            }
            if (temp.getFormulaName() != null && StringUtils.isNotBlank(temp.getFormulaName())) {
                tempResult.setFormulaName(temp.getFormulaName());
            }
            if (temp.getFormulaNo() != null && StringUtils.isNotBlank(temp.getFormulaNo())) {
                tempResult.setRsContractDetailsFormulaNo(temp.getFormulaNo());
            }
            if (temp.getNetSalesSystemId() != null && StringUtils.isNotBlank(temp.getNetSalesSystemId())) {
                tempResult.setNetSalesRule(Integer.parseInt(temp.getNetSalesSystemId()));
            }
            saveDetailsList.add(tempResult);
        }
        List<Object> input = new ArrayList<>(1);
        input.add(saveDetailsList);
        return ImtdItemPriceRebateDetailsLocalServiceUtil.saveItem(input, Constants.SAVE_DETAILS);
    }

    public void removeAll() {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(userId);
        input.add(sessionId);
        ImtdItemPriceRebateDetailsLocalServiceUtil.updateAll(input, "Temp");
    }

    public void removeItem(int tempIfpSystemId) throws SystemException, PortalException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(userId);
        input.add(sessionId);
        ImtdItemPriceRebateDetails temp = ImtdItemPriceRebateDetailsLocalServiceUtil.getImtdItemPriceRebateDetails(tempIfpSystemId);
        if ("U".equals(temp.getOperation())) {
            temp.setOperation("D");
            ImtdItemPriceRebateDetailsLocalServiceUtil.updateImtdItemPriceRebateDetails(temp);
        } else {
            ImtdItemPriceRebateDetailsLocalServiceUtil.deleteImtdItemPriceRebateDetails(tempIfpSystemId);
        }
    }

    public void addItem(ItemMasterDTO itemDto) throws SystemException {
        final Integer userId = Integer.parseInt(String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final int contractSystemId = (Integer) sessionDTO.getContractSystemId();
        final int cfpSystemId = (Integer) sessionDTO.getCfpSystemId();
        final int ifpSystemId = (Integer) sessionDTO.getIfpSystemId();
        final int psSystemId = (Integer) sessionDTO.getPsSystemId();
        final int rsSystemId = (Integer) sessionDTO.getRsSystemId();
        ImtdItemPriceRebateDetails temp = ImtdItemPriceRebateDetailsLocalServiceUtil.createImtdItemPriceRebateDetails(0);
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdItemPriceRebateDetails.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.USERS_SID, userId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.SESSION_ID, sessionId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq("itemMasterSid", Integer.parseInt(itemDto.getItemSystemId())));
        List<ImtdItemPriceRebateDetails> result = ImtdItemPriceRebateDetailsLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
        if (result.size() > 0) {
            temp = result.get(0);
            if ("D".equals(temp.getOperation())) {
                temp.setSource(itemDto.getSource());
                temp.setOperation("U");
                ImtdItemPriceRebateDetailsLocalServiceUtil.updateImtdItemPriceRebateDetails(temp);
            }
        } else {
            temp.setAttachedDate(new Date());
            temp.setItemId(itemDto.getItemId());
            temp.setItemName(itemDto.getItemName());
            temp.setItemNo(itemDto.getItemNo());
            temp.setPackageSize(itemDto.getPackageSize());
            if (StringUtils.isNotBlank(String.valueOf(itemDto.getPrimaryUom())) && !Constants.ZEROSTRING.equals(String.valueOf(itemDto.getPrimaryUom()))) {
                temp.setPrimaryUom(Integer.valueOf(itemDto.getPrimaryUom()));
            }
            temp.setPsStatus(StringUtils.isBlank(itemDto.getItemStatusValue()) ? 0 : Integer.valueOf(itemDto.getItemStatusValue()));
            temp.setItemMasterSid(Integer.parseInt(itemDto.getItemSystemId()));
            temp.setContractMasterSid(contractSystemId);
            temp.setCfpModelSid(cfpSystemId);
            temp.setIfpModelSid(ifpSystemId);
            temp.setPsModelSid(psSystemId);
            temp.setRsModelSid(rsSystemId);
            temp.setUsersSid(userId);
            temp.setSessionId(sessionId);
            temp.setOperation("A");
            temp.setImtdCreatedDate(new Date());
            temp.setCreatedBy(userId);
            temp.setCreatedDate(new Date());
            temp.setModifiedBy(userId);
            temp.setModifiedDate(new Date());
            temp.setSource(itemDto.getSource());
            temp.setBrandMasterSid(itemDto.getBrandId());
            ImtdItemPriceRebateDetailsLocalServiceUtil.addImtdItemPriceRebateDetails(temp);
        }
    }

    public Boolean itemNullVerification(String field) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final List<Object> input = new ArrayList<>(NumericConstants.SIX);
        input.add(userId);
        input.add(sessionId);
        input.add(field);
        input.add(field);
        return ImtdItemPriceRebateDetailsLocalServiceUtil.saveItem(input, "Validation");
    }

    public List saveProductBundleValidation(String type) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        List result = (List) ImtdItemPriceRebateDetailsLocalServiceUtil.validateTempRSDeatils(userId, sessionId, null, type, null, null, null, null);
        return result;
    }

    public Boolean priceVerification(String field) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final List<Object> input = new ArrayList<>(NumericConstants.SIX);
        input.add(userId);
        input.add(sessionId);
        input.add(field);
        input.add(field);
        input.add(field);
        return ImtdItemPriceRebateDetailsLocalServiceUtil.saveItem(input, "AlphaNumericValidation");
    }
    
    public Boolean verifyPriceOrPriceType(){
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        String query = "SELECT count(IMTD_ITEM_PRICE_REBATE_SID) from IMTD_ITEM_PRICE_REBATE_DETAILS where SESSION_ID = '"+sessionId+"' AND CHECK_RECORD = 1 AND USERS_SID ="+userId+" AND \"OPERATION\" <> 'D' \n" +
                        "AND ((PRICE_TYPE is null OR PRICE_TYPE = 0 OR PRICE_TYPE ='' ) );" ;
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return (Integer) list.get(0)>0;
    }
    
    public Boolean dateVerification(String indicator) throws SystemException {
        final Integer userId = Integer.parseInt(String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdItemPriceRebateDetails.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.USERS_SID, userId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.SESSION_ID, sessionId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CHECK_RECORD, true));
        if ("rebate".equals(indicator)) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.geProperty(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_REBATE_START_DATE, com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_REBATE_END_DATE));
        } else if ("item".equals(indicator)) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.geProperty(ConstantUtil.END_DATE, ConstantUtil.START_DATE));
        } else if ("contract".equals(indicator)) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.geProperty(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CONTRACT_PRICE_END_DATE, com.stpl.app.contract.abstractsearch.util.ConstantUtil.CONTRACT_PRICE_START_DATE));
        } else if ("PP".equals(indicator)) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.geProperty(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_END_DATE, com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_START_DATE));
        }
        ifpDynamicQuery.setProjection(ProjectionFactoryUtil.count(com.stpl.app.contract.abstractsearch.util.ConstantUtil.IMTD_ITEM_PRICE_REBATE_SID));
        List<?> temp = ImtdItemPriceRebateDetailsLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
        int result = Integer.valueOf(temp.get(0).toString());
        return result <= 0;
    }

    public void saveItemDetails(Object searchField, String searchValue) throws SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final int psSystemId = (Integer) sessionDTO.getPsSystemId();
        final int rsSystemId = (Integer) sessionDTO.getRsSystemId();
        final List<Object> input = new ArrayList<>(NumericConstants.SIX);
        input.add(userId);
        input.add(sessionId);
        input.add(userId);
        input.add(sessionId);
        input.add(userId);
        input.add(sessionId);
        ImtdItemPriceRebateDetailsLocalServiceUtil.saveItem(input, "com.contractDashboard.process.saveIFP");
        if (psSystemId != 0) {
            ImtdItemPriceRebateDetailsLocalServiceUtil.saveItem(input, "com.contractDashboard.process.savePS");
        }
        if (rsSystemId != 0) {
            ImtdItemPriceRebateDetailsLocalServiceUtil.saveItem(input, "com.contractDashboard.process.saveRS");
        }
    }

    public Boolean ifpValidation() throws SystemException {
        final Integer userId = Integer.parseInt(String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdItemPriceRebateDetails.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.USERS_SID, userId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.SESSION_ID, sessionId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CHECK_RECORD, true));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.OPERATION, "D"));
        ifpDynamicQuery.setProjection(ProjectionFactoryUtil.count(com.stpl.app.contract.abstractsearch.util.ConstantUtil.IMTD_ITEM_PRICE_REBATE_SID));
        List<?> temp = ImtdItemPriceRebateDetailsLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
        int imtdItemPriceRebateSid = Integer.valueOf(temp.get(0).toString());
        return imtdItemPriceRebateSid <= 0;
    }
    
    public Boolean rsValidation() throws SystemException {
        final Integer userId = Integer.parseInt(String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdItemPriceRebateDetails.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.USERS_SID, userId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.SESSION_ID, sessionId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CHECK_RECORD, true));
        ifpDynamicQuery.setProjection(ProjectionFactoryUtil.count(com.stpl.app.contract.abstractsearch.util.ConstantUtil.IMTD_ITEM_PRICE_REBATE_SID));
        List<?> temp = ImtdItemPriceRebateDetailsLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
        int imtdItemPriceRebateSid = Integer.valueOf(temp.get(0).toString());
        return imtdItemPriceRebateSid <= 0;
    }

    /**
     * getting count for Brand Name.
     *
     * @param filterText the filter text
     * @return the lazy Brand count
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public int getLazyBrandNameCount(String filterText) throws  SystemException {
        filterText = StringUtils.trimToEmpty(filterText) + "%";
        LOGGER.debug("Entering getBrandMasterCount method with filterText" + filterText);
        int count = 0;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.BRAND_NAME, filterText));
        count = dao.getBrandMasterCount(dynamicQuery);
        LOGGER.debug("Ending getBrandMasterCount method : returning count :" + count);
        return count;
    }

    /**
     * getting Brand Name.
     *
     * @param filterText the filter text
     * @return the lazy brand
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public List<HelperDTO> getLazyBrandName(String filterText, int start, int end) throws SystemException {
        LOGGER.debug("Entering getBrandMaster method with filterText" + filterText);
        List<HelperDTO> resultList = new ArrayList<>();
        filterText = StringUtils.trimToEmpty(filterText) + "%";
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.BRAND_NAME, filterText));
        dynamicQuery.addOrder(OrderFactoryUtil.asc(Constants.BRAND_NAME));
        dynamicQuery.setLimit(start, end);
        List<BrandMaster> brandList = dao.getBrandMasterList(dynamicQuery);
        HelperDTO dto = new HelperDTO(0, "-Select One-");
        resultList.add(dto);
        if (brandList != null) {
            for (int i = 0; i < brandList.size(); i++) {
                resultList.add(new HelperDTO(brandList.get(i).getBrandMasterSid(), brandList.get(i).getBrandName()));
            }
        }

        LOGGER.debug("Ending getBrandMaster method :" + brandList.size());
        return resultList;
    }

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
                    parameters.put(betweenFilter.getPropertyId() + "from", DB_DATE.format(startValue));
                    parameters.put(betweenFilter.getPropertyId() + "to", DB_DATE.format(endValue));
                } else if (filter instanceof And) {
                    And stringFilter = (And) filter;
                    Collection<Container.Filter> value = stringFilter.getFilters();
                    for (Container.Filter filter1 : value) {
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            parameters.put(less.getPropertyId() + "to", String.valueOf(less.getValue()));
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            parameters.put(greater.getPropertyId() + "from", String.valueOf(greater.getValue()));
                        }
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (operation.EQUAL.toString().equals(operation.name()) && 
                            (com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE.equals(String.valueOf(stringFilter.getPropertyId())) || "nep".equals(String.valueOf(stringFilter.getPropertyId()))
                                || com.stpl.app.contract.abstractsearch.util.ConstantUtil.MAX_INCREMENTAL_CHANGE.equals(String.valueOf(stringFilter.getPropertyId())))) {
                            double value = Double.valueOf(String.valueOf(stringFilter.getValue()));
                            parameters.put(String.valueOf(stringFilter.getPropertyId()) + com.stpl.app.contract.abstractsearch.util.ConstantUtil.EQUAL, value);
                    }
                    if (operation.GREATER.toString().equals(operation.name()) && 
                            (com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE.equals(String.valueOf(stringFilter.getPropertyId())) || "nep".equals(String.valueOf(stringFilter.getPropertyId()))
                                || com.stpl.app.contract.abstractsearch.util.ConstantUtil.MAX_INCREMENTAL_CHANGE.equals(String.valueOf(stringFilter.getPropertyId())))) {
                            double value = Double.valueOf(String.valueOf(stringFilter.getValue()));
                            parameters.put(String.valueOf(stringFilter.getPropertyId()) + "from", value);

                    }
                    if (operation.LESS.toString().equals(operation.name()) && 
                            (com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE.equals(String.valueOf(stringFilter.getPropertyId())) || "nep".equals(String.valueOf(stringFilter.getPropertyId()))
                                || com.stpl.app.contract.abstractsearch.util.ConstantUtil.MAX_INCREMENTAL_CHANGE.equals(String.valueOf(stringFilter.getPropertyId())))) {

                            double value = Double.valueOf(String.valueOf(stringFilter.getValue()));
                            parameters.put(String.valueOf(stringFilter.getPropertyId()) + "to", value);

                    }
                    if (stringFilter.getValue() instanceof Date) {
                        String filterString = DB_DATE.format(stringFilter.getValue());
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            parameters.put(stringFilter.getPropertyId().toString()+ "from", filterString);
                        } else {
                            parameters.put(stringFilter.getPropertyId().toString() + "to", filterString);
                        }
                    }
                }
            }
        }
    }
    
    private static void getParameterList(Map<String, Object> parameters, Set<Container.Filter> sc) {
        if (sc != null) {
            for (Container.Filter filter : sc) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString();
                    parameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;

                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();
                    parameters.put(betweenFilter.getPropertyId() + "from", DB_DATE.format(startValue));
                    parameters.put(betweenFilter.getPropertyId() + "to", DB_DATE.format(endValue));
                } else if (filter instanceof And) {
                    And stringFilter = (And) filter;
                    Collection<Container.Filter> value = stringFilter.getFilters();
                    for (Container.Filter filter1 : value) {
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            parameters.put(less.getPropertyId() + "to", String.valueOf(less.getValue()));
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            parameters.put(greater.getPropertyId() + "from", String.valueOf(greater.getValue()));
                        }
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (operation.EQUAL.toString().equals(operation.name()) && 
                            (com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE.equals(String.valueOf(stringFilter.getPropertyId())) || "nep".equals(String.valueOf(stringFilter.getPropertyId()))
                                || com.stpl.app.contract.abstractsearch.util.ConstantUtil.MAX_INCREMENTAL_CHANGE.equals(String.valueOf(stringFilter.getPropertyId())))) {
                            double value = Double.valueOf(String.valueOf(stringFilter.getValue()));
                            parameters.put(String.valueOf(stringFilter.getPropertyId()) + com.stpl.app.contract.abstractsearch.util.ConstantUtil.EQUAL, value);
                    }
                    if (operation.GREATER.toString().equals(operation.name()) && 
                            (com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE.equals(String.valueOf(stringFilter.getPropertyId())) || "nep".equals(String.valueOf(stringFilter.getPropertyId()))
                                || com.stpl.app.contract.abstractsearch.util.ConstantUtil.MAX_INCREMENTAL_CHANGE.equals(String.valueOf(stringFilter.getPropertyId())))) {
                            double value = Double.valueOf(String.valueOf(stringFilter.getValue()));
                            parameters.put(String.valueOf(stringFilter.getPropertyId()) + "from", value);

                    }
                    if (operation.LESS.toString().equals(operation.name()) && 
                            (com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE.equals(String.valueOf(stringFilter.getPropertyId())) || "nep".equals(String.valueOf(stringFilter.getPropertyId()))
                                || com.stpl.app.contract.abstractsearch.util.ConstantUtil.MAX_INCREMENTAL_CHANGE.equals(String.valueOf(stringFilter.getPropertyId())))) {

                            double value = Double.valueOf(String.valueOf(stringFilter.getValue()));
                            parameters.put(String.valueOf(stringFilter.getPropertyId()) + "to", value);

                    }
                    if (stringFilter.getValue() instanceof Date) {
                        String filterString = DB_DATE.format(stringFilter.getValue());
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            parameters.put(stringFilter.getPropertyId().toString()+ "from", filterString);
                        } else {
                            parameters.put(stringFilter.getPropertyId().toString() + "to", filterString);
                        }
                    }
                }
            }
        }
    }

    public List<Object[]> getLazyPriceProtectionDeatils(int start, int end,  Set<Container.Filter> searchCriteria, boolean isCount, String record, final List<SortByColumn> sortByColumns) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        StringBuilder sql = new StringBuilder();
        Map<String, Object> parameters = new HashMap<>();
        getParameterList(parameters, searchCriteria);
        if (isCount) {
            sql.append(CustomSQLUtil.get("contractPriceProtectionSelectTempTableCount"));
        } else {
            sql.append("            SELECT IMTD.ITEM_MASTER_SID, IMTD.ITEM_ID, IMTD.ITEM_NO, IMTD.ITEM_NAME, IMTD.PRICE_PROTECTION_STATUS,\n" +
"            IMTD.PRICE_PROTECTION_START_DATE, IMTD.PRICE_PROTECTION_END_DATE, IMTD.PRICE_PROTECTION_PRICE_TYPE,\n" +
"            IMTD.NEP, IMTD.NEP_FORMULA, IMTD.BASE_PRICE, IMTD.PRICE_TOLERANCE_INTERVAL, IMTD.PRICE_TOLERANCE_FREQUENCY, IMTD.PRICE_TOLERANCE_TYPE,\n" +
"            IMTD.PRICE_TOLERANCE, IMTD.MAX_INCREMENTAL_CHANGE, IMTD.RESET_ELIGIBLE, IMTD.RESET_TYPE, IMTD.RESET_DATE,\n" +
"            IMTD.RESET_INTERVAL, IMTD.RESET_FREQUENCY, IMTD.NET_PRICE_TYPE, IMTD.NET_PRICE_TYPE_FORMULA, IMTD.ATTACHED_DATE,\n" +
"            IMTD.BRAND_MASTER_SID,\n" +
"            BM.BRAND_NAME, IMTD.IMTD_ITEM_PRICE_REBATE_SID ,IMTD.CHECK_RECORD,\n" +
"            IMTD.BASE_PRICE_TYPE,IMTD.BASE_PRICE_DDLB, IMTD.BASE_PRICE_ENTRY, IMTD.BASE_PRICE_DATE,\n" +
"            IMTD.NET_BASE_PRICE, IMTD.NET_BASE_PRICE_FORMULA_ID, IMTD.SUBSEQUENT_PERIOD_PRICE_TYPE,\n" +
"            IMTD.NET_SUBSEQUENT_PERIOD_PRICE, IMTD.NET_SUBSEQUENT_PRICE_FORMULA_ID, IMTD.NET_RESET_PRICE_TYPE,\n" +
"            IMTD.NET_RESET_PRICE_FORMULA_ID,IMTD.RESET_PRICE_TYPE  FROM IMTD_ITEM_PRICE_REBATE_DETAILS IMTD \n" +
"            LEFT JOIN BRAND_MASTER BM ON IMTD.BRAND_MASTER_SID=BM.BRAND_MASTER_SID  ");
        }
        sql.append(" WHERE USERS_SID ='").append(userId).append("' AND SESSION_ID ='").append(sessionId).append("' AND OPERATION NOT IN ('D','F') ");
        if (map.isEmpty()) {
            loadSortMap(map);
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_ID1) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_ID1)))) {
            sql.append(" AND IMTD.ITEM_ID LIKE '%").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_ID1))).append("%' ");       
        }

        if (parameters.get(Constants.ITEM_NO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(Constants.ITEM_NO)))) {
            sql.append(" AND IMTD.ITEM_NO LIKE '%").append(String.valueOf(parameters.get(Constants.ITEM_NO))).append("%' ");
        }
        if (parameters.get(Constants.ITEM_NAME) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(Constants.ITEM_NAME)))) {
            sql.append(" AND IMTD.ITEM_NAME LIKE '%").append(String.valueOf(parameters.get(Constants.ITEM_NAME))).append("%' ");
        }

        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BRAND) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BRAND)))) {
            sql.append(" AND BM.BRAND_NAME LIKE '%").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BRAND))).append("%' ");
        }

        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_STATUS) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_STATUS)))) {
            sql.append(" AND IMTD.PRICE_PROTECTION_STATUS LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_STATUS))).append("' ");
        }

        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_START_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_START_DATEFROM)))) {
            sql.append(" AND IMTD.PRICE_PROTECTION_START_DATE >= '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_START_DATEFROM))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_START_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_START_DATETO)))) {
            sql.append(" AND IMTD.PRICE_PROTECTION_START_DATE <= '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_START_DATETO))).append("' ");
        }

        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_END_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_END_DATEFROM)))) {
            sql.append(" AND IMTD.PRICE_PROTECTION_END_DATE >= '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_END_DATEFROM))).append("' ");
        }

        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_END_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_END_DATETO)))) {
            sql.append(" AND IMTD.PRICE_PROTECTION_END_DATE <= '").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_END_DATETO)).append("' ");
        }

        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_PRICE_TYPE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_PRICE_TYPE)))) {
            sql.append(" AND IMTD.PRICE_PROTECTION_PRICE_TYPE LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_PRICE_TYPE))).append("' ");
        }

        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NEPFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NEPFROM)))) {
            sql.append(" AND IMTD.NEP >=").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NEPFROM))).append(" ");
        }

        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NEPTO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NEPTO)))) {
            sql.append(" AND IMTD.NEP <=").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NEPTO))).append(" ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NEPEQUAL) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NEPEQUAL)))) {
            sql.append(" AND IMTD.NEP =").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NEPEQUAL))).append(" ");
        }

        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NEP_FORMULA) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NEP_FORMULA)))) {
            sql.append(" AND HT_NEP_FORMULA.NET_SALES_FORMULA_NAME LIKE '%").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NEP_FORMULA))).append("%' ");
        }

        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BASE_PRICE_TYPE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BASE_PRICE_TYPE)))) {
            sql.append(" AND IMTD.BASE_PRICE_TYPE LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BASE_PRICE_TYPE))).append("' ");
        }

        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_BASE_PRICE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_BASE_PRICE)))) {
            sql.append(" AND IMTD.NET_BASE_PRICE LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_BASE_PRICE))).append("' ");
        }

        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_BASE_PRICE_FORMULA_NAME) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_BASE_PRICE_FORMULA_NAME)))) {
            sql.append(" AND HT_NET_BASE_FORMULA.NET_SALES_FORMULA_NAME LIKE '%").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_BASE_PRICE_FORMULA_NAME))).append("%' ");
        }

        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.SUBSEQUENT_PERIOD_PRICE_TYPE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.SUBSEQUENT_PERIOD_PRICE_TYPE)))) {
            sql.append(" AND IMTD." + map.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.SUBSEQUENT_PERIOD_PRICE_TYPE) + " LIKE  '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.SUBSEQUENT_PERIOD_PRICE_TYPE))).append("' ");
        }

        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_SUBSEQUENT_PERIOD_PRICE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_SUBSEQUENT_PERIOD_PRICE)))) {
            sql.append(" AND IMTD." + map.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_SUBSEQUENT_PERIOD_PRICE) + " LIKE  '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_SUBSEQUENT_PERIOD_PRICE))).append("' ");
        }

        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_SUBSEQUENT_PRICE_FORMULA_NAME) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_SUBSEQUENT_PRICE_FORMULA_NAME)))) {
            sql.append(" AND HT_NET_SUB_FORMULA.NET_SALES_FORMULA_NAME LIKE '%").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_SUBSEQUENT_PRICE_FORMULA_NAME))).append("%' ");
        }

        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PP_PRICE_TOLERANCE_INTERVAL) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PP_PRICE_TOLERANCE_INTERVAL)))) {
            sql.append(" AND IMTD.PRICE_TOLERANCE_INTERVAL LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PP_PRICE_TOLERANCE_INTERVAL))).append("' ");
        }

        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_FREQUENCY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_FREQUENCY)))) {
            sql.append(" AND IMTD.PRICE_TOLERANCE_FREQUENCY LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_FREQUENCY))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_TYPE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_TYPE)))) {
            sql.append(" AND IMTD.PRICE_TOLERANCE_TYPE LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_TYPE))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCEFROM)))) {
            sql.append(" AND IMTD.PRICE_TOLERANCE >=").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCEFROM))).append(" ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCETO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCETO)))) {
            sql.append(" AND IMTD.PRICE_TOLERANCE <=").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCETO))).append(" ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCEEQUAL) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCEEQUAL)))) {
            sql.append(" AND IMTD.PRICE_TOLERANCE =").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCEEQUAL))).append(" ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.MAX_INCREMENTAL_CHANGEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.MAX_INCREMENTAL_CHANGEFROM)))) {
            sql.append(" AND IMTD.MAX_INCREMENTAL_CHANGE >=").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.MAX_INCREMENTAL_CHANGEFROM))).append(" ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.MAX_INCREMENTAL_CHANGETO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.MAX_INCREMENTAL_CHANGETO)))) {
            sql.append(" AND IMTD.MAX_INCREMENTAL_CHANGE <=").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.MAX_INCREMENTAL_CHANGETO))).append(" ");
        }
         if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.MAX_INCREMENTAL_CHANGEEQUAL) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.MAX_INCREMENTAL_CHANGEEQUAL)))) {
            sql.append(" AND IMTD.MAX_INCREMENTAL_CHANGE =").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.MAX_INCREMENTAL_CHANGEEQUAL))).append(" ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.RESET_ELIGIBLE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.RESET_ELIGIBLE)))) {
            sql.append(" AND IMTD.RESET_ELIGIBLE LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.RESET_ELIGIBLE))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.RESET_TYPE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.RESET_TYPE)))) {
            sql.append(" AND IMTD.RESET_TYPE LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.RESET_TYPE))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.RESET_FREQUENCY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.RESET_FREQUENCY)))) {
            sql.append(" AND IMTD.RESET_FREQUENCY LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.RESET_FREQUENCY))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_RESET_PRICE_TYPE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_RESET_PRICE_TYPE)))) {
            sql.append(" AND IMTD.NET_RESET_PRICE_TYPE LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_RESET_PRICE_TYPE))).append("' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_RESET_PRICE_FORMULA_NAME) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_RESET_PRICE_FORMULA_NAME)))) {
            sql.append(" AND HT_NET_RESET_FORMULA.NET_SALES_FORMULA_NAME LIKE '%").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_RESET_PRICE_FORMULA_NAME))).append("%' ");
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_PRICE_TYPE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_PRICE_TYPE)))) {
            sql.append(" AND IMTD.NET_PRICE_TYPE LIKE '").append(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_PRICE_TYPE))).append("' ");
        }

        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.RESET_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.RESET_DATEFROM)))) {
            sql.append(" AND IMTD.RESET_DATE >= '").append(String.valueOf(dateFormat.format(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.RESET_DATEFROM)))).append("' ");
        }

        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.RESET_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.RESET_DATETO)))) {
            sql.append(" AND IMTD.RESET_DATE <= '").append(String.valueOf(dateFormat.format(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.RESET_DATETO)))).append("' ");
        }
        if (parameters.get(ATTACHED_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ATTACHED_DATEFROM)))) {
            sql.append(" AND IMTD.ATTACHED_DATE >= '").append(String.valueOf(dateFormat.format(parameters.get(ATTACHED_DATEFROM)))).append("' ");
        }

        if (parameters.get(ATTACHED_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ATTACHED_DATETO)))) {
            sql.append(" AND IMTD.ATTACHED_DATE <= '").append(String.valueOf(dateFormat.format(parameters.get(ATTACHED_DATETO)))).append("' ");
        }

if(!StringUtils.isBlank(record)){
            if(record.contains(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)){
                    parameters.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
                if(record.contains(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY)){
                    parameters.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
                if(record.contains(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE)){
                    parameters.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
        }
        if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE) != null) {
                      sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_BRACKET).append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("' BETWEEN PRICE_PROTECTION_START_DATE AND ISNULL(PRICE_PROTECTION_END_DATE ,'").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("') ");
                      sql.append(" OR ").append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_END_DATE_GREATER).append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY)).append("' ");
                      sql.append(" OR ").append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_START_DATE_LESS_THAN).append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE)).append("' )");
                  } else if ((parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY) != null) || (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT) != null)) {
                      sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_BRACKET).append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("' BETWEEN PRICE_PROTECTION_START_DATE AND ISNULL(PRICE_PROTECTION_END_DATE ,'").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("') OR PRICE_PROTECTION_END_DATE < '").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY)).append("') ");
                  } else if ((parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE) != null) || (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY) != null)) {
                      sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_OPEN).append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_END_DATE_GREATER).append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY)).append("' OR PRICE_PROTECTION_START_DATE > '").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE)).append("') ");
                  } else if ((parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT) != null) || (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT) != null && parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE) != null)) {
                      sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_BRACKET).append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("' BETWEEN PRICE_PROTECTION_START_DATE AND ISNULL(PRICE_PROTECTION_END_DATE,'").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("') OR PRICE_PROTECTION_START_DATE > '").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE)).append("') ");
                  } else if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT) != null) {
                      sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_QUOTE).append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("' BETWEEN PRICE_PROTECTION_START_DATE AND ISNULL(PRICE_PROTECTION_END_DATE,'").append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)).append("') ");
                  } else if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY) != null) {
                      sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_SPACE).append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_END_DATE_GREATER).append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY)).append("' ");
                  } else if (parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE) != null) {
                      sql.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_SPACE).append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_START_DATE_LESS_THAN).append(parameters.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE)).append("' ");
                  }
        String order = StringUtils.EMPTY;
        if (!isCount) {
            boolean sortOrder = false;
            String columnName = null;
            String orderByColumn = null;
            if (sortByColumns != null) {
                for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                    final SortByColumn sortByColumn = (SortByColumn) iterator.next();

                    columnName = sortByColumn.getName();
                    orderByColumn = map.get(columnName);

                    if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                        sortOrder = false;
                    } else {
                        sortOrder = true;
                    }
                }
            }
            if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
                order = order + " ORDER BY IMTD.ITEM_MASTER_SID ASC ";
            } else {
                order = order + com.stpl.app.contract.abstractsearch.util.ConstantUtil.ORDER_BY + orderByColumn + ((!sortOrder) ? com.stpl.app.contract.abstractsearch.util.ConstantUtil.ASC_SPACE : com.stpl.app.contract.abstractsearch.util.ConstantUtil.DESC_SPACE);
            }
            order = order + " " + "OFFSET ";
            order = order + start;
            order = order + com.stpl.app.contract.abstractsearch.util.ConstantUtil.ROWS_FETCH_NEXT + end;
            order = order + com.stpl.app.contract.abstractsearch.util.ConstantUtil.ROWS_ONLY_SEMICOLON;
            sql=sql.append(order);
        }

        List returnList = HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());

        LOGGER.debug("selected results :" + returnList.size());
        return returnList;
    }

    public List<TempPricingDTO> getCustomizedPriceProtectionDTO(List<Object[]> returnList, final Map<Integer, HelperDTO> priceProtectionPriceType, final String record) {
        TempPricingDTO itemDTO;
        List<TempPricingDTO> resultList = new ArrayList<>();
        Map<Integer, HelperDTO> idHelperDTOMap = helperListUtil.getIdHelperDTOMap();
        for (Object[] tempifp : returnList) {
            itemDTO = new TempPricingDTO();
            itemDTO.setItemID(tempifp[1] != null ? String.valueOf(tempifp[1]) : StringUtils.EMPTY);
            itemDTO.setItemNo(tempifp[NumericConstants.TWO] != null ? String.valueOf(tempifp[NumericConstants.TWO]) : StringUtils.EMPTY);
            itemDTO.setItemName(tempifp[NumericConstants.THREE] != null ? String.valueOf(tempifp[NumericConstants.THREE]) : StringUtils.EMPTY);
            itemDTO.setPriceProtectionStatus(tempifp[NumericConstants.FOUR] != null && StringUtils.isNotBlank(String.valueOf(tempifp[NumericConstants.FOUR])) ? idHelperDTOMap.get(Integer.valueOf(tempifp[NumericConstants.FOUR].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));
            itemDTO.setPriceProtectionStartDate((Date) tempifp[NumericConstants.FIVE]);
            itemDTO.setPriceProtectionEndDate((Date) tempifp[NumericConstants.SIX]);
            itemDTO.setPriceProtectionPriceType(tempifp[NumericConstants.SEVEN] != null  && StringUtils.isNotBlank(String.valueOf(tempifp[NumericConstants.SEVEN])) ? priceProtectionPriceType.get(Integer.valueOf(tempifp[NumericConstants.SEVEN].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));
            itemDTO.setNep(tempifp[NumericConstants.EIGHT] != null ? String.valueOf(tempifp[NumericConstants.EIGHT]) : StringUtils.EMPTY);

            itemDTO.setNepFormulaId(tempifp[NumericConstants.NINE] != null && StringUtils.isNotBlank(String.valueOf(tempifp[NumericConstants.NINE]))? Integer.valueOf(String.valueOf(tempifp[NumericConstants.NINE])) : 0);
            itemDTO.setBasePrice(tempifp[NumericConstants.TEN] != null ? String.valueOf(tempifp[NumericConstants.TEN]) : StringUtils.EMPTY);
            itemDTO.setPpPriceToleranceInterval(tempifp[NumericConstants.ELEVEN] != null  && StringUtils.isNotBlank(String.valueOf(tempifp[NumericConstants.ELEVEN])) ? helperListUtil.getHelperDTObyID(Integer.valueOf(String.valueOf(tempifp[NumericConstants.ELEVEN]))) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));

            itemDTO.setPpPriceToleranceFrequency(tempifp[NumericConstants.TWELVE] != null && StringUtils.isNotBlank(tempifp[NumericConstants.TWELVE].toString()) ? helperListUtil.getHelperDTObyID(Integer.valueOf(String.valueOf(tempifp[NumericConstants.TWELVE]))) : idHelperDTOMap.get(Integer.parseInt(Constants.ZEROSTRING)));
            itemDTO.setPpPriceToleranceType(tempifp[NumericConstants.THIRTEEN] != null && StringUtils.isNotBlank(tempifp[NumericConstants.THIRTEEN].toString()) ? helperListUtil.getHelperDTObyID(Integer.valueOf(String.valueOf(tempifp[NumericConstants.THIRTEEN]))) : idHelperDTOMap.get(Integer.parseInt(Constants.ZEROSTRING)));
            itemDTO.setPriceTolerance(tempifp[NumericConstants.FOURTEEN] != null ? String.valueOf(df.format(tempifp[NumericConstants.FOURTEEN])) : StringUtils.EMPTY);
            itemDTO.setMaxIncrementalChange(tempifp[NumericConstants.FIFTEEN] != null ? String.valueOf(df.format(tempifp[NumericConstants.FIFTEEN])) : StringUtils.EMPTY);
            itemDTO.setResetEligible(tempifp[NumericConstants.SIXTEEN] != null  && StringUtils.isNotBlank(String.valueOf(tempifp[NumericConstants.SIXTEEN])) ? idHelperDTOMap.get(Integer.valueOf(tempifp[NumericConstants.SIXTEEN].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));
            itemDTO.setResetType(tempifp[NumericConstants.SEVENTEEN] != null ? idHelperDTOMap.get(Integer.valueOf(tempifp[NumericConstants.SEVENTEEN].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));
            itemDTO.setResetDate((Date) tempifp[NumericConstants.EIGHTEEN]);
            itemDTO.setResetInterval(tempifp[NumericConstants.NINETEEN] != null  && StringUtils.isNotBlank(String.valueOf(tempifp[NumericConstants.NINETEEN])) ? idHelperDTOMap.get(Integer.valueOf(tempifp[NumericConstants.NINETEEN].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));
            itemDTO.setResetFrequency(tempifp[NumericConstants.TWENTY] != null  && StringUtils.isNotBlank(String.valueOf(tempifp[NumericConstants.TWENTY]))? idHelperDTOMap.get(Integer.valueOf(tempifp[NumericConstants.TWENTY].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));
            itemDTO.setNetPriceType(tempifp[NumericConstants.TWENTY_ONE] != null  && StringUtils.isNotBlank(String.valueOf(tempifp[NumericConstants.TWENTY_ONE])) ? idHelperDTOMap.get(Integer.valueOf(tempifp[NumericConstants.TWENTY_ONE].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));

            itemDTO.setNetPriceTypeFormulaID(tempifp[NumericConstants.TWENTY_TWO] != null ? String.valueOf(tempifp[NumericConstants.TWENTY_TWO]) : StringUtils.EMPTY);
            itemDTO.setAttachedDate((Date) tempifp[NumericConstants.TWENTY_THREE]);
            itemDTO.setBrand(tempifp[NumericConstants.TWENTY_FIVE] != null ? String.valueOf(tempifp[NumericConstants.TWENTY_FIVE]) : StringUtils.EMPTY);
            itemDTO.setTempItemPriceRebateSystemId(String.valueOf(tempifp[NumericConstants.TWENTY_SIX]));
            itemDTO.setCheckbox(Boolean.valueOf(String.valueOf(tempifp[NumericConstants.TWENTY_SEVEN])));
            itemDTO.setBasePriceType(tempifp[NumericConstants.TWENTY_EIGHT] != null  && StringUtils.isNotBlank(String.valueOf(tempifp[NumericConstants.TWENTY_EIGHT])) ? idHelperDTOMap.get(Integer.valueOf(tempifp[NumericConstants.TWENTY_EIGHT].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));
            if (itemDTO.getBasePriceType() != null && !itemDTO.getBasePriceType().getDescription().equals(ConstantUtil.SELECT_ONE)) {
                if (itemDTO.getBasePriceType().getDescription().equals("Manual")) {
                    itemDTO.setBasePriceValue(ContractUtils.checkNullValues(tempifp[NumericConstants.THIRTY]) ? "0.00" : String.valueOf(df.format(tempifp[NumericConstants.THIRTY])));
                    itemDTO.setBasePriceEntry(ContractUtils.checkNullValues(tempifp[NumericConstants.THIRTY]) ? "0.00" : String.valueOf(df.format(tempifp[NumericConstants.THIRTY])));
                } else if (itemDTO.getBasePriceType().getDescription().equals("Date")) {
                    itemDTO.setBasePriceValue((Date) tempifp[NumericConstants.THIRTY_ONE]);
                    itemDTO.setBasePriceDate((Date) tempifp[NumericConstants.THIRTY_ONE]);
                } else if (itemDTO.getBasePriceType().getDescription().equals("Price Type")) {
                    itemDTO.setBasePriceValue(tempifp[NumericConstants.TWENTY_NINE] != null ? priceProtectionPriceType.get(Integer.valueOf(tempifp[NumericConstants.TWENTY_NINE].toString())) : idHelperDTOMap.get(0));
                    itemDTO.setBasePriceItemPriceType(tempifp[NumericConstants.TWENTY_NINE] != null ? priceProtectionPriceType.get(Integer.valueOf(tempifp[NumericConstants.TWENTY_NINE].toString())) : idHelperDTOMap.get(0));
                }
            } else {
                itemDTO.setBasePriceValue(StringUtils.EMPTY);
            }
            itemDTO.setNetBasePrice(tempifp[NumericConstants.THIRTY_TWO] != null  && StringUtils.isNotBlank(String.valueOf(tempifp[NumericConstants.THIRTY_TWO])) ? idHelperDTOMap.get(Integer.valueOf(tempifp[NumericConstants.THIRTY_TWO].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));
            itemDTO.setNetBasePriceFormulaID(tempifp[NumericConstants.THIRTY_THREE] != null && StringUtils.isNotBlank(String.valueOf(tempifp[NumericConstants.THIRTY_THREE])) ? Integer.valueOf(String.valueOf(tempifp[NumericConstants.THIRTY_THREE])) : 0);
            itemDTO.setSubsequentPeriodPriceType(tempifp[NumericConstants.THIRTY_FOUR] != null ? priceProtectionPriceType.get(Integer.valueOf(tempifp[NumericConstants.THIRTY_FOUR].toString())) : idHelperDTOMap.get(0));
            itemDTO.setNetSubsequentPeriodPrice(tempifp[NumericConstants.THIRTY_FIVE] != null && StringUtils.isNotBlank(String.valueOf(tempifp[NumericConstants.THIRTY_FIVE]))? idHelperDTOMap.get(Integer.valueOf(tempifp[NumericConstants.THIRTY_FIVE].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));
            itemDTO.setNetSubsequentPriceFormulaID(tempifp[NumericConstants.THIRTY_SIX] != null && StringUtils.isNotBlank(String.valueOf(tempifp[NumericConstants.THIRTY_SIX])) ? Integer.valueOf(String.valueOf(tempifp[NumericConstants.THIRTY_SIX])) : 0);
            itemDTO.setNetResetPriceType(tempifp[NumericConstants.THIRTY_SEVEN] != null && StringUtils.isNotBlank(String.valueOf(tempifp[NumericConstants.THIRTY_SEVEN])) ? idHelperDTOMap.get(Integer.valueOf(tempifp[NumericConstants.THIRTY_SEVEN].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));
            itemDTO.setNetResetPriceFormulaID(tempifp[NumericConstants.THIRTY_EIGHT] != null && StringUtils.isNotBlank(String.valueOf(tempifp[NumericConstants.THIRTY_EIGHT])) ? Integer.valueOf(String.valueOf(tempifp[NumericConstants.THIRTY_EIGHT])) : 0);
            itemDTO.setNepFormula(getFormulaName(Integer.valueOf(tempifp[NumericConstants.NINE] != null && StringUtils.isNotBlank(String.valueOf(tempifp[NumericConstants.NINE])) ? String.valueOf(tempifp[NumericConstants.NINE]) : Constants.ZEROSTRING)));
            itemDTO.setNetBasePriceFormula(getFormulaName(Integer.valueOf(tempifp[NumericConstants.THIRTY_THREE] != null && StringUtils.isNotBlank(String.valueOf(tempifp[NumericConstants.THIRTY_THREE]))? String.valueOf(tempifp[NumericConstants.THIRTY_THREE]) : Constants.ZEROSTRING)));
            itemDTO.setNetSubsequentPriceFormula(getFormulaName(Integer.valueOf(tempifp[NumericConstants.THIRTY_SIX] != null && StringUtils.isNotBlank(String.valueOf(tempifp[NumericConstants.THIRTY_SIX]))? String.valueOf(tempifp[NumericConstants.THIRTY_SIX]) : Constants.ZEROSTRING)));
            itemDTO.setNetResetPriceFormula(getFormulaName(Integer.valueOf(tempifp[NumericConstants.THIRTY_EIGHT] != null && StringUtils.isNotBlank(String.valueOf(tempifp[NumericConstants.THIRTY_EIGHT]))? String.valueOf(tempifp[NumericConstants.THIRTY_EIGHT]) : Constants.ZEROSTRING)));
            if(!StringUtils.EMPTY.equals(String.valueOf(tempifp[NumericConstants.TWENTY_TWO]))) {
            itemDTO.setNetPriceTypeFormula(getFormulaName(Integer.valueOf(tempifp[NumericConstants.TWENTY_TWO] != null ? String.valueOf(tempifp[NumericConstants.TWENTY_TWO]) : Constants.ZEROSTRING)));
            }
            itemDTO.setResetPriceType(tempifp[NumericConstants.THIRTY_NINE] != null && StringUtils.isNotBlank(String.valueOf(tempifp[NumericConstants.THIRTY_NINE]))? priceProtectionPriceType.get(Integer.valueOf(tempifp[NumericConstants.THIRTY_NINE].toString())) : priceProtectionPriceType.get(Integer.valueOf(Constants.ZEROSTRING)));            
            if (!StringUtils.isBlank(record)) {
                if (record.contains(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT)) {
                    itemDTO.setRecordType(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CURRENT);
                }
                else if (record.contains(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY)) {
                    itemDTO.setRecordType(com.stpl.app.contract.abstractsearch.util.ConstantUtil.HISTORY);
                }
                else if (record.contains(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE)) {
                    itemDTO.setRecordType(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FUTURE);
                } else {
                    itemDTO.setRecordType(StringUtils.EMPTY);
                }
            }
            resultList.add(itemDTO);
        }
        setPriceProtectionResultList(resultList);
        return resultList;
    }
    
    public int getNsfCount(final ErrorfulFieldGroup searchFields, final Set<Container.Filter> filterSet) {
        int count = 0;
        StringBuilder queryBuilder;
        queryBuilder = buildSearchQuery(searchFields, true);
        queryBuilder = getFilterQuery(filterSet, queryBuilder);

        List<Object> masterData = (List<Object>) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        if (masterData != null && !masterData.isEmpty()) {
            Object ob = masterData.get(0);
            count += Integer.valueOf(String.valueOf(ob));
        }
        LOGGER.debug(" nsfCount count=" + count);
        return count;
    }

    public List<NepFormulaLookUpDTO> loadNsfResults(
            final ErrorfulFieldGroup searchFields, final int start, final int end, final List<SortByColumn> columns, final Set<Container.Filter> filterSet) {
        List<NepFormulaLookUpDTO> searchList;
        StringBuilder queryBuilder;
        queryBuilder = buildSearchQuery(searchFields, false);
        queryBuilder = getFilterQuery(filterSet, queryBuilder);
        queryBuilder = getOrderQuery(queryBuilder, columns, start, end);

        queryBuilder = new StringBuilder(queryBuilder.toString().replaceAll("WHERE AND", " WHERE "));
        queryBuilder = new StringBuilder(queryBuilder.toString().endsWith(com.stpl.app.contract.abstractsearch.util.ConstantUtil.WHERE) ? queryBuilder.toString().replace(com.stpl.app.contract.abstractsearch.util.ConstantUtil.WHERE, " ") : queryBuilder);

        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        searchList = getCustomizedSearchFormToDTO(list);
        return searchList;
    }

    private void loadCriteriaInMap() {
        criteria.clear();
        criteria.put(NEP_FORMULA_TYPE, "NET_SALES_FORMULA_TYPE");
        criteria.put("nepFormulaID", "NET_SALES_FORMULA_ID");
        criteria.put("nepFormulaNo", "NET_SALES_FORMULA_NO");
        criteria.put("nepFormulaName", "NET_SALES_FORMULA_NAME");
        criteria.put("createdDate", "CREATED_DATE");
        criteria.put("modifiedDate", "MODIFIED_DATE");
    }
    
    private Map userPropertyMap() {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("createdBy", "CREATED_BY");
        userMap.put("modifiedBy", "MODIFIED_BY");
        return userMap;
    }
    
    public static final String NEP_FORMULA_TYPE = "nepFormulaType";
    private StringBuilder getFilterQuery(final Set<Container.Filter> filterSet, final StringBuilder stringBuilder) {
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    if (!(ConstantsUtils.CREATEDBY.equals(stringFilter.getPropertyId()) || ConstantsUtils.MODIFIEDBY.equals(stringFilter.getPropertyId()))) {
                        stringBuilder.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_SPACE).append(criteria.get(stringFilter.getPropertyId().toString())).append(" LIKE '%").append(stringFilter.getFilterString()).append("%'");
                    }else{
                        stringBuilder.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_SPACE).append(userPropertyMap().get(stringFilter.getPropertyId().toString())).append(" = ").append(stringFilter.getFilterString());
                    }
                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    String startValue = DB_DATE.format(stringFilter.getStartValue());
                    String endValue = DB_DATE.format(stringFilter.getEndValue());
                    if (startValue != null) {
                        stringBuilder.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_SPACE).append(criteria.get(stringFilter.getPropertyId().toString())).append(GREATER_THAN_EQUAL).append(startValue).append("' ");
                    }
                    if (endValue != null) {
                        stringBuilder.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_SPACE).append(" cast ( ").append(criteria.get(stringFilter.getPropertyId().toString())).append(" as DATE) ").append(LESS_THAN_EQUAL).append(endValue).append("' ");
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (stringFilter.getValue() instanceof Date) {
                        String filterString = DB_DATE.format(stringFilter.getValue());
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            stringBuilder.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_SPACE).append(criteria.get(String.valueOf(stringFilter.getPropertyId()))).append(GREATER_THAN_EQUAL).append(filterString).append("' ");
                        } else {
                            stringBuilder.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_SPACE).append(" cast ( ").append(criteria.get(String.valueOf(stringFilter.getPropertyId()))).append(" as DATE) ").append(LESS_THAN_EQUAL).append(filterString).append("' ");
                        }
                    }
                }
            }
        }
        return stringBuilder;
    }
    public static final String LESS_THAN_EQUAL = " <= '";
    public static final String GREATER_THAN_EQUAL = " >= '";

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
                if (NEP_FORMULA_TYPE.equalsIgnoreCase(fields)) {
                    queryBuilder.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_SPACE).append(criteria.get(fields)).append(" = ").append(((HelperDTO) searchFields.getField(NEP_FORMULA_TYPE).getValue()).getId());
                } else {
                    queryBuilder.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_SPACE).append(criteria.get(fields)).append(" LIKE '").append(searchFields.getField(fields).getValue().toString().trim().replace("*", "%")).append("'");
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
                if (!(ConstantsUtils.CREATEDBY.equals(sortByColumn.getName()) || ConstantsUtils.MODIFIEDBY.equals(sortByColumn.getName()))) {
                    orderByColumn = criteria.get(sortByColumn.getName());
                    sortOrder = sortByColumn.getType() != SortByColumn.Type.ASC;
                } else {
                    orderByColumn = String.valueOf(userPropertyMap().get(sortByColumn.getName()));
                    sortOrder = sortByColumn.getType() != SortByColumn.Type.ASC;
                }
            }
        }
        if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
            stringBuilder.append(" ORDER BY CREATED_DATE ");
        } else {
            stringBuilder.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ORDER_BY ).append(orderByColumn).append((!sortOrder) ? com.stpl.app.contract.abstractsearch.util.ConstantUtil.ASC_SPACE : com.stpl.app.contract.abstractsearch.util.ConstantUtil.DESC_SPACE);
        }
        stringBuilder.append(" OFFSET ").append(startIndex);
        stringBuilder.append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ROWS_FETCH_NEXT).append(endIndex).append(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ROWS_ONLY_SEMICOLON);
        return stringBuilder;
    }

    private List<NepFormulaLookUpDTO> getCustomizedSearchFormToDTO(List list) {
        final List<NepFormulaLookUpDTO> searchResultsList = new ArrayList<>();
        try {
            if (list != null) {
                Map<Integer, String> userMap = StplSecurity.userMap;
                if (userMap.isEmpty()) {
                    userMap = StplSecurity.getUserName();
                }
                for (int i = 0; i < list.size(); i++) {
                    final NepFormulaLookUpDTO searchDTO = new NepFormulaLookUpDTO();
                    final Object[] object = (Object[]) list.get(i);
                    searchDTO.setNepFormulaTypeDesc((helperListUtil.getIdHelperDTOMap().get(object[0] != null ? Integer.valueOf(String.valueOf(object[0])) : 0)).getDescription());
                    searchDTO.setNepFormulaType(helperListUtil.getIdHelperDTOMap().get(object[0] != null ? Integer.valueOf(String.valueOf(object[0])) : 0));
                    searchDTO.setNepFormulaID(!Constants.NULL.equals(String.valueOf(object[1])) && StringUtils.isNotBlank(String.valueOf(object[1])) ? String.valueOf(object[1]) : StringUtils.EMPTY);
                    searchDTO.setNepFormulaNo(!Constants.NULL.equals(String.valueOf(object[NumericConstants.TWO])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.TWO])) ? String.valueOf(object[NumericConstants.TWO]) : StringUtils.EMPTY);
                    searchDTO.setNepFormulaName(!Constants.NULL.equals(String.valueOf(object[NumericConstants.THREE])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.THREE])) ? String.valueOf(object[NumericConstants.THREE]) : StringUtils.EMPTY);
                    searchDTO.setCreatedDate((Date) object[NumericConstants.FOUR]);
                    searchDTO.setModifiedDate((Date) object[NumericConstants.SIX]);
                    searchDTO.setCreatedBy(userMap.get(Integer.valueOf(String.valueOf(object[NumericConstants.FIVE]))));
                    searchDTO.setModifiedBy(userMap.get(Integer.valueOf(String.valueOf(object[NumericConstants.SEVEN]))));
                    searchDTO.setNepFormulaSystemID(Integer.valueOf(String.valueOf(object[NumericConstants.EIGHT])));
                    searchResultsList.add(searchDTO);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return searchResultsList;
    }

    /**
     *
     * @param rsFormulaDTO
     * @param start
     * @param offset
     * @param isCount
     * @return
     */
    public Object loadRSFormula(final PriceProtectionFormulaDTO rsFormulaDTO, final int start, final int offset, final boolean isCount, final Set<Container.Filter> filterSet, final List<SortByColumn> columns) {
        String query;

        if (isCount) {
            query = "SELECT COUNT (Distinct FORMULA_ID)\n"
                    + " FROM   FORMULA_DETAILS_MASTER \n"
                    + " WHERE  FORMULA_ID LIKE '@FORMULA_ID' AND FORMULA_NO LIKE '@FORMULA_NO'\n"
                    + " AND FORMULA_DESC LIKE '@FORMULA_NAME'   AND ITEM_ID LIKE '@ITEM_ID';";
        } else {
            query = "SELECT Distinct FORMULA_ID,FORMULA_NO,FORMULA_DESC,ITEM_ID \n"
                   + " FROM FORMULA_DETAILS_MASTER WHERE \n"
                   + " FORMULA_ID LIKE '@FORMULA_ID' AND FORMULA_NO LIKE '@FORMULA_NO'  AND FORMULA_DESC LIKE '@FORMULA_NAME' AND ITEM_ID LIKE '@ITEM_ID'  ";
        }

        query = query.replace("@FORMULA_ID", StringUtils.isBlank(rsFormulaDTO.getFormulaID()) || Constants.NULL.equals(rsFormulaDTO.getFormulaID())
                ? ConstantsUtils.PERCENCTAGE : replaceForWildCardSearch(rsFormulaDTO.getFormulaID()));
        query = query.replace("@FORMULA_NO", StringUtils.isBlank(rsFormulaDTO.getFormulaNo()) || Constants.NULL.equals(rsFormulaDTO.getFormulaNo())
                ? ConstantsUtils.PERCENCTAGE : replaceForWildCardSearch(rsFormulaDTO.getFormulaNo()));
        query = query.replace("@FORMULA_NAME", StringUtils.isBlank(rsFormulaDTO.getFormulaName()) || Constants.NULL.equals(rsFormulaDTO.getFormulaName())
                ? ConstantsUtils.PERCENCTAGE : replaceForWildCardSearch(rsFormulaDTO.getFormulaName()));
        query = query.replace("@FORMULA_TYPE", rsFormulaDTO.getFormulaType() == null || (com.stpl.app.serviceUtils.Constants.SELECT_ONE).equals(rsFormulaDTO.getFormulaType()) ? ConstantsUtils.PERCENCTAGE : String.valueOf(rsFormulaDTO.getFormulaType().getId()));
        query = query.replace("@ITEM_ID", rsFormulaDTO.getItemId() == null || (com.stpl.app.serviceUtils.Constants.SELECT_ONE).equals(rsFormulaDTO.getItemId()) ? ConstantsUtils.PERCENCTAGE : String.valueOf(rsFormulaDTO.getItemId()));
        query = getRsFormulaFilterQuery(filterSet, query);

        if (!isCount) {
            query = getRsFormulaOrderQuery(query, columns, start, offset);
        }

        query = new String(query.replaceAll("WHERE AND", " WHERE "));
        query = new String(query.endsWith(com.stpl.app.contract.abstractsearch.util.ConstantUtil.WHERE) ? query.toString().replace(com.stpl.app.contract.abstractsearch.util.ConstantUtil.WHERE, " ") : query);

        List resultList = (List) RsModelLocalServiceUtil.executeSelectQuery(query, null, null);
        if (isCount) {
            int count = (Integer) resultList.get(0);
            return count;
        } else {
            return convertFormulaList(resultList);
        }

    }

    private String getRsFormulaFilterQuery(final Set<Container.Filter> filterSet, String string) {
        String condition=string;
        if (rsFormulaDbMap.isEmpty()) {
            loadRsformulaMap();
        }

        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    condition = condition +(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_SPACE) + (rsFormulaDbMap.get(stringFilter.getPropertyId().toString())) + (" LIKE '%") + (stringFilter.getFilterString()) + ("%'");
                } else if (filter instanceof Between) {

                    Between stringFilter = (Between) filter;
                    String startValue = DB_DATE.format(stringFilter.getStartValue());
                    String endValue = DB_DATE.format(stringFilter.getEndValue());

                    if (startValue != null) {
                        condition = condition +(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_SPACE) + (rsFormulaDbMap.get(stringFilter.getPropertyId().toString())) + (GREATER_THAN_EQUAL) + (startValue) + ("' ");
                    }
                    if (endValue != null) {
                        condition = condition +(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_SPACE) + (rsFormulaDbMap.get(stringFilter.getPropertyId().toString())) + (LESS_THAN_EQUAL) + (endValue) + ("' ");
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (stringFilter.getValue() instanceof Date) {
                        String filterString = DB_DATE.format(stringFilter.getValue());
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            condition = condition +(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_SPACE) + (rsFormulaDbMap.get(String.valueOf(stringFilter.getPropertyId()))) + (GREATER_THAN_EQUAL) + (filterString) + ("' ");
                        } else {
                            condition = condition +(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AND_SPACE) + (rsFormulaDbMap.get(String.valueOf(stringFilter.getPropertyId()))) + (LESS_THAN_EQUAL) + (filterString) + ("' ");
                        }
                    }
                }
            }
        }

        return condition;
    }

    private void loadRsformulaMap() {
        rsFormulaDbMap.clear();
        rsFormulaDbMap.put("formulaType", "FORMULA_TYPE");
        rsFormulaDbMap.put("formulaID", "FORECASTING_FORMULA_SID");
        rsFormulaDbMap.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FORMULA_NO_PROPERTY, "FORMULA_NO");
        rsFormulaDbMap.put(ContractUtils.FORMULA_NAME, "FORMULA_NAME");
    }

    private String getRsFormulaOrderQuery(String string, final List<SortByColumn> sortByColumns, final int startIndex, final int endIndex) {
        boolean sortOrder = false;
        String orderByColumn = null;
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                orderByColumn = rsFormulaDbMap.get(sortByColumn.getName());
                sortOrder = sortByColumn.getType() != SortByColumn.Type.ASC;
            }
        }
        if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
            string += (" ORDER BY FORMULA_ID ");
        } else {
            string += (com.stpl.app.contract.abstractsearch.util.ConstantUtil.ORDER_BY ) + (orderByColumn) + ((!sortOrder) ? com.stpl.app.contract.abstractsearch.util.ConstantUtil.ASC_SPACE : com.stpl.app.contract.abstractsearch.util.ConstantUtil.DESC_SPACE);
        }

        string += (" OFFSET ") + (startIndex);
        string += (com.stpl.app.contract.abstractsearch.util.ConstantUtil.ROWS_FETCH_NEXT) + (endIndex) + (com.stpl.app.contract.abstractsearch.util.ConstantUtil.ROWS_ONLY_SEMICOLON);

        return string;
    }

    /**
     * Converts the list of objects to list of PriceProtectionFormulaDTO.
     * @param list List<Object[]>
     * @return List<RebatePlanDTO>
     */
    private List<PriceProtectionFormulaDTO> convertFormulaList(final List<Object[]> list) {
        List<PriceProtectionFormulaDTO> resultList = new ArrayList<>();
        try{
        for (Object[] object : list) {
            PriceProtectionFormulaDTO rSFormulaDTO = new PriceProtectionFormulaDTO();
            rSFormulaDTO.setFormulaID(String.valueOf(object[0]));
            if (!Constants.NULL.equals(String.valueOf(object[1]))) {
                rSFormulaDTO.setFormulaNo(String.valueOf(object[1]));
            }
            rSFormulaDTO.setFormulaName(String.valueOf(object[NumericConstants.TWO]));
           
            resultList.add(rSFormulaDTO);
        }
        }catch(Exception e){
        LOGGER.error(e);
        }
        return resultList;
    }

    private String replaceForWildCardSearch(String input) {
        if (StringUtils.isNotBlank(input)) {
            input = input.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        return input;
    }

    /**
     * Method to add description and details in a list from HelperTable .
     *
     * @param listType the list type
     * @return the helper details
     */
    public List<HelperDTO> getHelperIdDetails(final String listType) throws SystemException {
        List<HelperTable> list = null;
        final List<HelperDTO> helperList = new ArrayList<>();

        LOGGER.debug("In getHelperDetails P1:listType=" + listType);
        list = dao.getHelperTableDetailsByListName(listType);
        if (list != null) {
            helperList.add(new HelperDTO(0, com.stpl.app.serviceUtils.Constants.SELECT_ONE));
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));
            }
        }
        return helperList;
    }

    public List<PriceProtectionFormulaDTO> loadRSDetails(final PriceProtectionFormulaDTO rsFormulaDTO) {
        String query;
        query = CustomSQLUtil.get("loadFormulaDetails");

        query = query.replace("@FORECASTING_FORMULA_SID", StringUtils.isBlank(rsFormulaDTO.getFormulaID()) || Constants.NULL.equals(rsFormulaDTO.getFormulaID())
                ? ConstantsUtils.PERCENCTAGE : replaceForWildCardSearch(rsFormulaDTO.getFormulaID()));
        query = query.replace("@FORMULA_NO", StringUtils.isBlank(rsFormulaDTO.getFormulaNo()) || Constants.NULL.equals(rsFormulaDTO.getFormulaNo())
                ? ConstantsUtils.PERCENCTAGE : replaceForWildCardSearch(rsFormulaDTO.getFormulaNo()));
        query = query.replace("@FORMULA_NAME", StringUtils.isBlank(rsFormulaDTO.getFormulaName()) || Constants.NULL.equals(rsFormulaDTO.getFormulaName())
                ? ConstantsUtils.PERCENCTAGE : replaceForWildCardSearch(rsFormulaDTO.getFormulaName()));
        query = query.replace("@FORMULA_TYPE", rsFormulaDTO.getFormulaType() == null || (com.stpl.app.serviceUtils.Constants.SELECT_ONE).equals(rsFormulaDTO.getFormulaType()) ? ConstantsUtils.PERCENCTAGE : String.valueOf(rsFormulaDTO.getFormulaType().getId()));
        query = query.replace("@FORMULA_ID", StringUtils.isBlank(rsFormulaDTO.getFormulaID()) || Constants.NULL.equals(rsFormulaDTO.getFormulaID())
                ? ConstantsUtils.PERCENCTAGE : rsFormulaDTO.getFormulaID());
        List resultList = (List) RsModelLocalServiceUtil.executeSelectQuery(query, null, null);
        List<PriceProtectionFormulaDTO> list = getCustomizedRSDetails(resultList);
        return list;
    }

    private List<PriceProtectionFormulaDTO> getCustomizedRSDetails(List list) {
        final List<PriceProtectionFormulaDTO> resultList = new ArrayList<>();
        if (list != null) {
            for (Object formulaList : list) {
                PriceProtectionFormulaDTO formulaDto = new PriceProtectionFormulaDTO();
                final String formula = (String) formulaList;
                formulaDto.setFormula(formula);
                resultList.add(formulaDto);
            }
        }
        return resultList;
    }

    public static Boolean saveToTempTable(List<TempPricingDTO> saveList, boolean isEdit,SessionDTO sessionDTO) throws PortalException, SystemException {
        List<ImtdItemPriceRebateDetails> saveDetailsList = new ArrayList<>();

        for (TempPricingDTO temp : saveList) {
            ImtdItemPriceRebateDetails tempResult = ImtdItemPriceRebateDetailsLocalServiceUtil.getImtdItemPriceRebateDetails(Integer.parseInt(temp.getTempItemPriceRebateSystemId()));
            tempResult.setCheckRecord(temp.getCheckbox());
            tempResult.setBasePrice(StringUtils.isBlank(temp.getBasePrice()) ? 0 : Double.valueOf(temp.getBasePrice()));
            tempResult.setBasePriceEntry(StringUtils.isBlank(temp.getBasePriceEntry()) ? 0 : Double.valueOf(temp.getBasePriceEntry()));
            tempResult.setBasePriceDdlb(temp.getBasePriceItemPriceType() == null || temp.getBasePriceItemPriceType().getDescription().equals(Constants.NULL) || StringUtils.isBlank(temp.getBasePriceItemPriceType().getDescription()) ? 0 : Integer.valueOf(temp.getBasePriceItemPriceType().getId()));
            tempResult.setBasePriceType(temp.getBasePriceType().getId());
            tempResult.setBasePriceDate(temp.getBasePriceDate());
            tempResult.setPriceProtectionStartDate(temp.getPriceProtectionStartDate());
            tempResult.setPriceProtectionEndDate(temp.getPriceProtectionEndDate());
            tempResult.setPriceProtectionStatus(temp.getPriceProtectionStatus() == null || temp.getPriceProtectionStatus().getDescription().equals(Constants.NULL) || StringUtils.isBlank(temp.getPriceProtectionStatus().getDescription()) ? 0 : Integer.valueOf(temp.getPriceProtectionStatus().getId()));
            tempResult.setPriceTolerance(StringUtils.isBlank(temp.getPriceTolerance()) ? 0 : Double.valueOf(temp.getPriceTolerance()));
            tempResult.setPriceToleranceFrequency(temp.getPpPriceToleranceFrequency()== null || temp.getPpPriceToleranceFrequency().getDescription().equals("null") || StringUtils.isBlank(temp.getPpPriceToleranceFrequency().getDescription()) ? "0" : String.valueOf(temp.getPpPriceToleranceFrequency().getId()));
            tempResult.setPriceToleranceInterval(temp.getPpPriceToleranceInterval() == null || temp.getPpPriceToleranceInterval().getDescription().equals("null") || StringUtils.isBlank(temp.getPpPriceToleranceInterval().getDescription()) ? 0 : Integer.valueOf(temp.getPpPriceToleranceInterval().getId()));
            tempResult.setPriceToleranceType(temp.getPpPriceToleranceType()== null || temp.getPpPriceToleranceType().getDescription().equals("null") || StringUtils.isBlank(temp.getPpPriceToleranceType().getDescription()) ? "0" : String.valueOf(temp.getPpPriceToleranceType().getId()));
            tempResult.setPriceProtectionPriceType(temp.getPriceProtectionPriceType() == null || temp.getPriceProtectionPriceType().getDescription().equals(Constants.NULL) || StringUtils.isBlank(temp.getPriceProtectionPriceType().getDescription()) ? 0 : Integer.valueOf(temp.getPriceProtectionPriceType().getId()));
            tempResult.setNep(StringUtils.isBlank(temp.getNep()) ? 0 : Double.valueOf(temp.getNep()));
            tempResult.setNepFormula(temp.getNepFormulaId());
            tempResult.setBasePrice(StringUtils.isBlank(temp.getBasePrice()) ? 0 : Double.valueOf(temp.getBasePrice()));
            tempResult.setMaxIncrementalChange(StringUtils.isBlank(temp.getMaxIncrementalChange()) ? 0 : Double.valueOf(temp.getMaxIncrementalChange()));
            tempResult.setResetEligible(temp.getResetEligible()==null ?0 :temp.getResetEligible().getId());
            tempResult.setResetType(temp.getResetType() == null? 0 : temp.getResetType().getId());
            tempResult.setResetFrequency(temp.getResetFrequency() == null? 0 : temp.getResetFrequency().getId());
            tempResult.setResetInterval(temp.getResetInterval() == null? 0 : temp.getResetInterval().getId());
            tempResult.setNetPriceType(temp.getNetPriceType()== null? 0 : temp.getNetPriceType().getId());
            tempResult.setResetDate(temp.getResetDate());
            tempResult.setNetPriceTypeFormula(temp.getNetPriceTypeFormulaID() != null ? temp.getNetPriceTypeFormulaID() : StringUtils.EMPTY );
            tempResult.setAttachedDate(temp.getAttachedDate());
            tempResult.setModifiedDate(isEdit ? new Date() : temp.getModifiedDate());
            tempResult.setModifiedBy(sessionDTO != null ? Integer.parseInt(sessionDTO.getUserId()) : 1);
            tempResult.setNetBasePrice(temp.getNetBasePrice() == null || temp.getNetBasePrice().getDescription().equals(Constants.NULL) || StringUtils.isBlank(temp.getNetBasePrice().getDescription()) ? 0 : Integer.valueOf(temp.getNetBasePrice().getId()));
            tempResult.setNetBasePriceFormulaId(temp.getNetBasePriceFormulaID());
            tempResult.setSubsequentPeriodPriceType(temp.getSubsequentPeriodPriceType() == null || temp.getSubsequentPeriodPriceType().getDescription().equals(Constants.NULL) || StringUtils.isBlank(temp.getSubsequentPeriodPriceType().getDescription()) ? 0 : Integer.valueOf(temp.getSubsequentPeriodPriceType().getId()));
            tempResult.setNetSubsequentPeriodPrice(temp.getNetSubsequentPeriodPrice() == null || temp.getNetSubsequentPeriodPrice().getDescription().equals(Constants.NULL) || StringUtils.isBlank(temp.getNetSubsequentPeriodPrice().getDescription()) ? 0 : Integer.valueOf(temp.getNetSubsequentPeriodPrice().getId()));
            tempResult.setNetSubsequentPriceFormulaId(temp.getNetSubsequentPriceFormulaID());
            tempResult.setResetPriceType(temp.getResetPriceType() == null || temp.getResetPriceType().getDescription().equals("null") || StringUtils.isBlank(temp.getResetPriceType().getDescription()) ? 0 : temp.getResetPriceType().getId());
            tempResult.setNetResetPriceType(temp.getNetResetPriceType() == null || temp.getNetResetPriceType().getDescription().equals(Constants.NULL) || StringUtils.isBlank(temp.getNetResetPriceType().getDescription()) ? 0 : temp.getNetResetPriceType().getId());
            tempResult.setNetResetPriceFormulaId(temp.getNetResetPriceFormulaID());
            saveDetailsList.add(tempResult);
        }
        List<Object> input = new ArrayList<>(1);
        input.add(saveDetailsList);
        return ImtdItemPriceRebateDetailsLocalServiceUtil.saveItem(input, Constants.SAVE_DETAILS);
    }

    public void populateToTempIFPforPP(Object populateField, Object populateValue, Boolean flag) {
        LOGGER.debug("PopulateField--> " + populateField + " PopulateValue-->> " + populateValue);
        final SimpleDateFormat tempFromat = new SimpleDateFormat(CommonUtils.MMDDYYYY);
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final Map<String, String> map = new HashMap<>();
        map.put("Price Protection Status", "Price_Protection_Status");
        map.put("Price Protection Start Date", "Price_Protection_Start_Date");
        map.put("Price Protection End Date", "Price_Protection_End_Date");
        map.put("Price Protection Price Type", "Price_Protection_Price_Type");
        map.put("NEP", "NEP");
        map.put("NEP Formula", "NEP_Formula");
        map.put("Base Price", "Base_Price");
        map.put("Price Tolerance Interval", "Price_Tolerance_Interval");
        map.put("Price Tolerance Frequency", "Price_Tolerance_Frequency");
        map.put("Price Tolerance Type", "Price_Tolerance_Type");
        map.put("Price Tolerance", "Price_Tolerance");
        map.put("Max Incremental Change", "Max_Incremental_Change");
        map.put("Reset Eligible", "Reset_Eligible");
        map.put("Reset Type", "Reset_Type");
        map.put("Reset Date", "Reset_Date");
        map.put("Reset Frequency", "Reset_Frequency");
        map.put("Reset Interval", "Reset_Interval");
        map.put("Net Price Type", "Net_Price_Type");
        map.put("Net Price Type Formula", "Net_Price_Type_Formula");
        map.put(Constants.CHECK_BOX1, com.stpl.app.contract.abstractsearch.util.ConstantUtil.CHECK_RECORD_LIST_NAME);
        final List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        if (populateValue.toString().contains(ContractUtils.DELIMITER)) {
            massPopulateForBasePrice(populateValue,input,flag);
        } else {
            input.add(map.get(populateField.toString()));
            input.add(populateValue);
            input.add(userId);
            input.add(tempFromat.format(new Date()));
            input.add(userId);
            input.add(sessionId);

            if (flag) {
                ImtdItemPriceRebateDetailsLocalServiceUtil.massPopulateAll(input, null);
            } else {
                input.add(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CHECK_RECORD_CAPTION);
                ImtdItemPriceRebateDetailsLocalServiceUtil.massPopulate(input, null);
            }
        }
    }

    public Map<Integer, HelperDTO> loadPriceProtection() {
        final List<HelperDTO> list = new ArrayList<>();
        Map<Integer, HelperDTO> priceProtectionPriceType = new HashMap<>();
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class);
        DashboardDAO dao = new DashboardLogicDAOImpl();
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_PRICING_QUALIFIER_SID));
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.ITEM_PRICING_QUAL_NAME, StringUtils.EMPTY)));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectionList));
        List<Object[]> returnList = new ArrayList<>();
        try {
            returnList = dao.getItemPricingTypeList(cfpDynamicQuery);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }

        HelperDTO helperTable;
        helperTable = new HelperDTO();
        helperTable.setId(0);
        helperTable.setDescription(ConstantsUtils.SELECT_ONE);
        list.add(helperTable);
        priceProtectionPriceType.put(0, helperTable);
        for (final Iterator<Object[]> iterator = returnList.iterator(); iterator.hasNext();) {
            final Object[] value = iterator.next();
            helperTable = new HelperDTO();
            helperTable.setId(value[0] != null ? Integer.valueOf(value[0].toString()) : 0);
            helperTable.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
            if (!StringUtils.EMPTY.equals(helperTable.getDescription())) {
                priceProtectionPriceType.put(helperTable.getId(), helperTable);
            }
        }
        LOGGER.debug("Ending getLazyPriceTypeResults  return list size :" + +list.size());
        return priceProtectionPriceType;
    }

    /**
     * Mass Update for Formula and Rebate Plan
     *
     * @param populateField
     * @param data
     */
    public void massPopulateForLookUp(String populateField, Object data,boolean isPopulateAll) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        Map<String, String> map = (Map) data;
        String sql,queryName;
        if ("Rebate Plan No".equals(populateField)) {
            queryName = "com.rsModel.massPopulateContractRebate";
            if (isPopulateAll) {
                queryName = "com.rsModel.massPopulateContractRebate populate All";
            }
            sql = CustomSQLUtil.get(queryName);
            sql = sql.replace(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AT_USERS_SID, userId);
            sql = sql.replace(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AT_SESSION_ID, sessionId);
            sql = sql.replace("@REBATE_PLAN_MASTER_SID", map.get("rebatePlanSystemId"));
            sql = sql.replace("@RS_DETAILS_REBATE_PLAN_NAME", map.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.REBATE_PLAN_NAME));
            if (!isPopulateAll) {
                sql = sql.replace(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CHECK_RECORD_LIST_NAME, com.stpl.app.contract.abstractsearch.util.ConstantUtil.CHECK_RECORD_LIST_NAME);
            }

        } else {
            queryName = "com.rsModel.massPopulateFormulaForContract";
            if (isPopulateAll) {
                queryName = "com.rsModel.massPopulateFormulaForContract populate All";
            }
            sql = CustomSQLUtil.get(queryName);
            sql = sql.replace(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AT_USERS_SID, userId);
            sql = sql.replace(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AT_SESSION_ID, sessionId);
            sql = sql.replace("@RS_DETAILS_FORMULA_NO", map.get(com.stpl.app.contract.abstractsearch.util.ConstantUtil.FORMULA_NO_PROPERTY));
            sql = sql.replace("@RS_DETAILS_FORMULA_NAME", map.get(ContractUtils.FORMULA_NAME));
            sql = sql.replace("@RS_DETAILS_FORMULA_ID", map.get("formulaID"));
        }
        RsModelLocalServiceUtil.executeUpdateQuery(sql, null, null);
    }

    /**
     * Mass Update for Formula and Rebate Plan
     *
     * @param populateField
     * @param data
     */
    public void massPopulateDeductionLookUp(Map<String, String> map, boolean populate) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        String sql;
        sql = CustomSQLUtil.get("com.rsModel.massPopulateDeductionForContract");
        sql = sql.replace(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AT_USERS_SID, userId);
        sql = sql.replace(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AT_SESSION_ID, sessionId);
        sql = sql.replace("@DEDUCTION_CALENDAR_MASTER_SID", map.get("deductionSystemId"));
        sql = sql.replace("@RS_DETAILS_DEDUCTION_CALENDAR_NO", map.get(Constants.DEDUCTION_CALENDAR_NO));
        sql = sql.replace("@RS_DETAILS_DEDUCTION_CALENDAR_NAME", map.get(Constants.DEDUCTION_CALENDAR_NAME));
        if (populate) {
            sql = sql + AND_CHECK_RECORD_1;
        }
        RsModelLocalServiceUtil.executeUpdateQuery(sql, null, null);
    }
    public static final String AND_CHECK_RECORD_1 = " AND CHECK_RECORD = 1;";

    /**
     * Mass Update for Formula and Rebate Plan
     *
     * @param populateField
     * @param data
     */
    public void massPopulateNetSalesLookUp(Map<String, String> map, boolean populate) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        String sql;
        sql = CustomSQLUtil.get("com.rsModel.massPopulateNetSalesForContract");
        sql = sql.replace(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AT_USERS_SID, userId);
        sql = sql.replace(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AT_SESSION_ID, sessionId);
        sql = sql.replace("@NET_SALES_FORMULA_MASTER_SID", map.get(SYSTEM_ID));
        sql = sql.replace("@RS_DETAILS_NET_SALES_FORMULA_NO", map.get(ContractUtils.NET_SALES_FORMULA_NO));
        sql = sql.replace("@RS_DETAILS_NET_SALES_FORMULA_NAME", map.get("netSalesFormulaName"));
        if (populate) {
            sql = sql + AND_CHECK_RECORD_1;
        }
        RsModelLocalServiceUtil.executeUpdateQuery(sql, null, null);
    }
    public static final String SYSTEM_ID = "systemID";

    /**
     * Mass Update for Formula and Rebate Plan
     *
     * @param populateField
     * @param data
     */
    public void massPopulateRuleLookUps(String populateField, Map<String, String> map, boolean populate) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        String sql = StringUtils.EMPTY;
        if (ContractUtils.NET_SALES_RULE.equals(populateField)) {
            sql = CustomSQLUtil.get("com.rsModel.massPopulateNetSalesRuleForContract");
            sql = sql.replace(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AT_USERS_SID, userId);
            sql = sql.replace(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AT_SESSION_ID, sessionId);
            sql = sql.replace("@NET_SALES_RULE", map.get(SYSTEM_ID));
        } else if (ContractUtils.CALCULATION_RULE.equals(populateField)) {
            sql = CustomSQLUtil.get("com.rsModel.massPopulateCalculationRuleForContract");
            sql = sql.replace(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AT_USERS_SID, userId);
            sql = sql.replace(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AT_SESSION_ID, sessionId);
            sql = sql.replace("@CALCULATION_RULE", map.get(SYSTEM_ID));
        } else if (ContractUtils.EVALUATION_RULE.equals(populateField)) {
            sql = CustomSQLUtil.get("com.rsModel.massPopulateEvaluationRuleForContract");
            sql = sql.replace(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AT_USERS_SID, userId);
            sql = sql.replace(com.stpl.app.contract.abstractsearch.util.ConstantUtil.AT_SESSION_ID, sessionId);
            sql = sql.replace("@EVALUATION_RULE", map.get(SYSTEM_ID));
        }
        if (populate) {
            sql = sql + AND_CHECK_RECORD_1;
        }
        RsModelLocalServiceUtil.executeUpdateQuery(sql, null, null);
    }

    public String getIntegerForSelection(String value) {
        List<Object> list;
        String sqlQuery = "SELECT ITEM_PRICING_QUALIFIER_SID FROM ITEM_PRICING_QUALIFIER WHERE ITEM_PRICING_QUALIFIER_NAME LIKE '" + value + "'";
        list = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
        return list.get(0).toString();
    }

    private void loadSortMap(Map<String, String> map) {
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_ID1, "IMTD.ITEM_ID");
        map.put(Constants.ITEM_NO, "IMTD.ITEM_NO");
        map.put(Constants.ITEM_NAME, "IMTD.ITEM_NAME");
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BRAND, "BM.BRAND_NAME");
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_STATUS, "IMTD.PRICE_PROTECTION_STATUS");
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_START_DATE, "IMTD.PRICE_PROTECTION_START_DATE");
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_END_DATE, "IMTD.PRICE_PROTECTION_END_DATE");
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_PROTECTION_PRICE_TYPE, "IMTD.PRICE_PROTECTION_PRICE_TYPE");
        map.put("nep", "IMTD.NEP");
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NEP_FORMULA, "IMTD.NEP_FORMULA");
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.BASE_PRICE_TYPE, "IMTD.BASE_PRICE_TYPE");
        map.put("basePriceValue", "IMTD.BASE_PRICE_ENTRY");
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_BASE_PRICE, "IMTD.NET_BASE_PRICE");
        map.put("netBasePriceFormulaID", "HT_NET_BASE_FORMULA.NET_SALES_FORMULA_NAME");
        map.put("netBasePriceFormula", "IMTD.NEP_FORMULA");
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.SUBSEQUENT_PERIOD_PRICE_TYPE, "IMTD.SUBSEQUENT_PERIOD_PRICE_TYPE");
        map.put("netSubsequentPriceFormulaID", "HT_NET_SUB_FORMULA.NET_SALES_FORMULA_NAME");
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_INTERVAL, "IMTD.PRICE_TOLERANCE_INTERVAL");
        map.put("ppPriceToleranceInterval", "IMTD.PRICE_TOLERANCE_INTERVAL");
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_FREQUENCY, "IMTD.PRICE_TOLERANCE_FREQUENCY");
        map.put("ppPriceToleranceFrequency", "IMTD.PRICE_TOLERANCE_FREQUENCY");
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE_TYPE, "IMTD.PRICE_TOLERANCE_TYPE");
        map.put("ppPriceToleranceType", "IMTD.PRICE_TOLERANCE_TYPE");
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.PRICE_TOLERANCE, "IMTD.PRICE_TOLERANCE");
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.MAX_INCREMENTAL_CHANGE, "IMTD.MAX_INCREMENTAL_CHANGE");
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.RESET_ELIGIBLE, "IMTD.RESET_ELIGIBLE");
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.RESET_TYPE, "IMTD.RESET_TYPE");
        map.put("resetDate", "IMTD.RESET_DATE");
        map.put("resetInterval", "IMTD.RESET_INTERVAL");
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.RESET_FREQUENCY, "IMTD.RESET_FREQUENCY");
        map.put("resetPriceType", "IMTD.RESET_PRICE_TYPE");
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_RESET_PRICE_TYPE, "IMTD.NET_RESET_PRICE_TYPE");
        map.put("netResetPriceFormulaID", "HT_NET_RESET_FORMULA.NET_SALES_FORMULA_NAME");
        map.put("netResetPriceFormula", "IMTD.NET_RESET_PRICE_FORMULA_ID");
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.NET_PRICE_TYPE, "IMTD.NET_PRICE_TYPE");
        map.put("netPriceTypeFormula", "IMTD.NET_PRICE_TYPE_FORMULA");
        map.put(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ATTACHED_DATE, "IMTD.ATTACHED_DATE");
    }
    
    /**
     * Validation to check the record is checked or not
     * 
     * @param tableName
     * @param sessionId
     * @return 
     */
    public static boolean isCheckedValidation(final String tableName,final String sessionId) {
        LOGGER.debug("TableName-->"+tableName+" SessionId-->> "+sessionId);
        final VaadinSession current = VaadinSession.getCurrent();
        final String userId = String.valueOf(current.getAttribute(Constants.USER_ID));
        String sql = "SELECT COUNT(CHECK_RECORD) from "+tableName+" WHERE SESSION_ID = '"+sessionId+"' AND USERS_SID = "+userId+" AND  CHECK_RECORD = 1 ";
        List resultList = (List) RsModelLocalServiceUtil.executeSelectQuery(sql, null, null); 
        return resultList.get(0)!=null && Integer.valueOf(resultList.get(0).toString())==0;
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
    
    public String getFormulaName(int value) {
        List <Object> list;
        String sql;
        if(value != 0){
        sql="SELECT NET_SALES_FORMULA_NAME FROM NET_SALES_FORMULA_MASTER where NET_SALES_FORMULA_MASTER_SID = "+value;
        }else {
            return StringUtils.EMPTY;
        }
        list=HelperTableLocalServiceUtil.executeSelectQuery(sql);
        return String.valueOf(list.get(0));
    }
   public  TempPricingDTO getIFPById(int systemId){
        try {
            Map<Integer, HelperDTO> idHelperDTOMap = helperListUtil.getIdHelperDTOMap();
            
          if ((Integer) systemId != ContractUtils.ZERO) {
            IfpContract dto = IfpContractLocalServiceUtil.getIfpContract(systemId);
            IfpModel item = IfpModelLocalServiceUtil.getIfpModel(dto.getIfpModelSid());
            TempPricingDTO temp = new TempPricingDTO();
            temp.setItemFamilyplanId(item.getIfpId());
            temp.setItemFamilyplanName(dto.getIfpName());
            temp.setItemFamilyplanNo(dto.getIfpNo());
            temp.setItemFamilyplanStatus(idHelperDTOMap.get(dto.getIfpStatus()));
            temp.setStartDate(dto.getIfpStartDate());
            temp.setEndDate(dto.getIfpEndDate());
            temp.setItemFamilyplanDesignation(dto.getIfpDesignation() == null || dto.getIfpDesignation().isEmpty() ? new HelperDTO(0,ConstantUtil.SELECT_ONE) : idHelperDTOMap.get(Integer.valueOf(dto.getIfpDesignation())));
            temp.setParentItemFamilyplanId(dto.getParentIfpId() == null ? StringUtils.EMPTY : dto.getParentIfpId());
            temp.setParentItemFamilyplanName(dto.getParentIfpName() == null ? StringUtils.EMPTY : dto.getParentIfpName());
            temp.setItemFamilyplanType(idHelperDTOMap.get(dto.getIfpType()));
            temp.setCreatedBy(DEFAULT_SQL_DATE_FORMAT);
            temp.setCreatedDate(dto.getCreatedDate());
            temp.setModifiedBy(String.valueOf(dto.getModifiedBy()));
            temp.setModifiedDate(dto.getModifiedDate());
            temp.setItemFamilyplanCategory(idHelperDTOMap.get(dto.getIfpCategory()));
                return temp;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);

        }
        return new TempPricingDTO();
    }
      public int getLookUpSearchCount(final ErrorfulFieldGroup searchItemForm, final BeanSearchCriteria sc) throws SystemException, PortalException {
        LOGGER.debug("Entering getSearchCount");
       
        String ifpId;
        String ifpNo;
        String ifpName;
        int ifpType=0;
        int ifpStatus=0;
        String itemId;
        String itemNo;
        String itemName;
        DateFormat dateFormat = new SimpleDateFormat(YYYY_M_MDD);

       try{
            Map<String, Object> parameters = new HashMap<>();
            if (searchItemForm.getField(ITEM_FAMILYPLAN_ID).getValue() == null) {
                ifpId = StringUtils.EMPTY;
            } else {
                ifpId = searchItemForm.getField(ITEM_FAMILYPLAN_ID).getValue()
                        .toString().trim();
            }
           
            if (searchItemForm.getField(ITEM_FAMILYPLAN_NO).getValue() == null) {

                ifpNo = StringUtils.EMPTY;
            } else {


                ifpNo = searchItemForm.getField(ITEM_FAMILYPLAN_NO).getValue()

                        .toString().trim();
            }

           
            if (searchItemForm.getField(ITEM_FAMILYPLAN_NAME).getValue() == null) {

                ifpName = StringUtils.EMPTY;
            } else {

                ifpName = searchItemForm.getField(ITEM_FAMILYPLAN_NAME).getValue()

                        .toString().trim();
            }

           
            if (searchItemForm.getField(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_FAMILYPLAN_TYPE).getValue() == null) {

                ifpType = 0;
            } else {
                ifpType = ((com.stpl.ifs.util.HelperDTO)searchItemForm.getField(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_FAMILYPLAN_TYPE).getValue()).getId();
            }

            
            if (searchItemForm.getField(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_FAMILYPLAN_STATUS).getValue() == null) {

                ifpStatus = 0;
            } else {
                ifpStatus = ((com.stpl.ifs.util.HelperDTO)searchItemForm.getField(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_FAMILYPLAN_STATUS).getValue()).getId();
            }
            if (searchItemForm.getField(ConstantUtil.ITEM_ID_M)!=null && searchItemForm.getField(ConstantUtil.ITEM_ID_M).getValue()!=null) {
            
            itemId = searchItemForm.getField(ConstantUtil.ITEM_ID_M).getValue()
                    .toString().trim();
            
        } else {
            itemId = StringUtils.EMPTY;
        }

        if (searchItemForm.getField(Constants.ITEM_NO)!=null && searchItemForm.getField(Constants.ITEM_NO).getValue()!=null) {
            
            itemNo = searchItemForm.getField(Constants.ITEM_NO).getValue()
                    .toString().trim();
            
        } else {
            itemNo = StringUtils.EMPTY;
            
        }

        if (searchItemForm.getField(Constants.ITEM_NAME)!=null && searchItemForm.getField(Constants.ITEM_NAME).getValue()!=null) {
            
            itemName = searchItemForm.getField(Constants.ITEM_NAME).getValue()
                    .toString().trim();
            
        } else {
            itemName = StringUtils.EMPTY;
        }
            
            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(IfpModel.class);

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
                List<HelperDTO> list = getDropDownList(IFP_TYPE_LIST);
                for (HelperDTO list1 : list) {
                if (list1.getId()==ifpType){
                        int sysId = list1.getId();
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.IFP_MODEL_TYPE, sysId));
                    }
                }

            }

           if (ifpStatus!=0) {
                List<HelperDTO> list = getDropDownList("STATUS");
                for (HelperDTO list1 : list) {
                if (list1.getId()==ifpStatus){
                        int sysId = list1.getId();
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
                           
                           parameters.put(betweenFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(startValue)));
                           
                           parameters.put(betweenFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(endValue)));
                      

                   } else if (filter instanceof Compare) {

                       Compare compare = (Compare) filter;
                       
                       Compare.Operation operation = compare.getOperation();
                       if (ConstantsUtils.IFP_SYSTEM_ID.equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
                           String value = String.valueOf(compare.getValue());
                           if (operation.GREATER.toString().equals(operation.name())) {
                               parameters.put(compare.getPropertyId() + "from", value);
                           } else if (operation.LESS.toString().equals(operation.name())) {
                               parameters.put(compare.getPropertyId() + "to", value);
                           } else if (operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
                               parameters.put(compare.getPropertyId() + com.stpl.app.contract.abstractsearch.util.ConstantUtil.EQUAL, value);
                           }
                       } else {
                           Date value = (Date) compare.getValue();
                           if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                               parameters.put(compare.getPropertyId() + "from", String.valueOf(dateFormat.format(value)));
                           } else {
                               parameters.put(compare.getPropertyId() + "to", String.valueOf(dateFormat.format(value)));
                           }
                       }

                   } else if (filter instanceof And) {
                       
                    And stringFilter = (And) filter;
                    
                    Collection<Container.Filter> value = stringFilter.getFilters();
                    for (Container.Filter filter1 : value) {
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            parameters.put(less.getPropertyId() + "to", String.valueOf(less.getValue()));
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            parameters.put(greater.getPropertyId() + "from", String.valueOf(greater.getValue()));
                        }
                    }
                }
               }
           }
            List resultList =ImtdIfpDetailsLocalServiceUtil.getIFPSearchList(ifpId, ifpNo, ifpName, ifpType, ifpStatus,itemId,  itemNo,  itemName,  0,  0,  null, null,true, parameters);
           
            
            return resultList.size();
       }catch(Exception e){
          LOGGER.error(e);
            return 0;
        }
    }
    public static final String ITEM_FAMILYPLAN_NAME = "itemFamilyplanName";
    public static final String ITEM_FAMILYPLAN_NO = "itemFamilyplanNo";
    public static final String ITEM_FAMILYPLAN_ID = "itemFamilyplanId";
    public static final String IFP_TYPE_LIST = "IFP_TYPE";
       public List<HelperDTO> getDropDownList(final String listName) throws SystemException {
        LOGGER.debug("getDropDownList p1: " + listName);

        final List<HelperDTO> helperList = new ArrayList<>();

        final List<HelperTable> list = dao.getHelperTableDetailsByListName(listName);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));

            }
        }

        LOGGER.debug("return helperList " + helperList.size());
        Collections.sort(helperList);
        return helperList;
    }
        public int getTotalCount() {
        LOGGER.debug("getTotalCount --method ");
        List list;
        list=HelperTableLocalServiceUtil.executeSelectQuery("Select count(*) from IFP_MODEL");
        return (int)(list.get(0));
    }

     @SuppressWarnings("unchecked")
    public List<TempPricingDTO> lookupSearchIFP(
            final ErrorfulFieldGroup searchItemForm, final int start, final int end,
            final List<OrderByColumn> orderByColumns, final BeanSearchCriteria sc) throws SystemException,
            PortalException {
        
        LOGGER.debug("Entering searchIFP , p1:" + start + ", p2:" + end + ", p3: " + ((orderByColumns==null)?orderByColumns:orderByColumns.size()));
        String ifpId;
        String ifpNo;
        String ifpName;
        String itemId;
        String itemNo;
        String itemName;
        int ifpType;
        int ifpStatus;
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormat = new SimpleDateFormat(YYYY_M_MDD);
      
        if (searchItemForm.getField(ITEM_FAMILYPLAN_ID).getValue() == null) {
            ifpId = StringUtils.EMPTY;
        } else {
            ifpId = searchItemForm.getField(ITEM_FAMILYPLAN_ID).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ITEM_FAMILYPLAN_NO).getValue() == null) {
            ifpNo = StringUtils.EMPTY;
        } else {

            ifpNo = searchItemForm.getField(ITEM_FAMILYPLAN_NO).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ITEM_FAMILYPLAN_NAME).getValue() == null) {
            ifpName = StringUtils.EMPTY;
        } else {
            ifpName = searchItemForm.getField(ITEM_FAMILYPLAN_NAME).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_FAMILYPLAN_TYPE).getValue() == null) {
            ifpType = 0;
        } else {
            ifpType = ((com.stpl.ifs.util.HelperDTO)searchItemForm.getField(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_FAMILYPLAN_TYPE).getValue()).getId();
        }

        if (searchItemForm.getField(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_FAMILYPLAN_STATUS).getValue() == null) {
            ifpStatus = 0;
        } else {
            ifpStatus = ((com.stpl.ifs.util.HelperDTO)searchItemForm.getField(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_FAMILYPLAN_STATUS).getValue()).getId();
        }
        
        if (searchItemForm.getField(ConstantUtil.ITEM_ID_M)!=null && searchItemForm.getField(ConstantUtil.ITEM_ID_M).getValue()!=null) {
            
            itemId = searchItemForm.getField(ConstantUtil.ITEM_ID_M).getValue()
                    .toString().trim();
            
        } else {
            itemId = StringUtils.EMPTY;
        }

        if (searchItemForm.getField(Constants.ITEM_NO)!=null && searchItemForm.getField(Constants.ITEM_NO).getValue()!=null) {
            
            itemNo = searchItemForm.getField(Constants.ITEM_NO).getValue()
                    .toString().trim();
            
        } else {
            itemNo = StringUtils.EMPTY;
            
        }

        if (searchItemForm.getField(Constants.ITEM_NAME)!=null && searchItemForm.getField(Constants.ITEM_NAME).getValue()!=null) {
            
            itemName = searchItemForm.getField(Constants.ITEM_NAME).getValue()
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
            List<HelperDTO> list = getDropDownList(IFP_TYPE_LIST);
            for (HelperDTO list1 : list) {
                if (list1.getId()==ifpType){
                }
            }

        }
            if (ifpStatus!=0) {
            List<HelperDTO> list = getDropDownList("STATUS");
            for (HelperDTO list1 : list) {
                if (list1.getId()==ifpStatus){
                }
            }

        }
            
        String orderby =Constants.ASC;
        String column = "IFP_MODEL_SID";
        for (final Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator
                .hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
            String columnName = orderByColumn.getName();
            if (ConstantsUtils.IFP_SYSTEM_ID.equals(columnName)) {
                column = "IFP_MODEL_SID";
            } else if (ITEM_FAMILYPLAN_ID.equals(columnName)) {
                column = "IFP_ID";
            } else if ("itemFamilyplanpNo".equals(columnName)) {
                column = com.stpl.app.contract.abstractsearch.util.ConstantUtil.IFP_NO_LABEL;
            } else if (ITEM_FAMILYPLAN_NAME.equals(columnName)) {
                column = com.stpl.app.contract.abstractsearch.util.ConstantUtil.IFP_NAME_LIST;
            } else if (com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_FAMILYPLAN_TYPE.equals(columnName)) {
                column = IFP_TYPE_LIST;
            } else if (com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_FAMILYPLAN_STATUS.equals(columnName)) {
                column = "IFP_STATUS";
            } else if (ITEM_FAMILYPLAN_CATEGORY.equals(columnName)) {
                column = "IFP_CATEGORY";
            } else if (ConstantUtil.START_DATE.equals(columnName)) {
                column = "IFP_START_DATE";
            } else if (ConstantUtil.END_DATE.equals(columnName)) {
                column = "IFP_END_DATE";
            } else if (ITEM_FAMILYPLAN_DESIGNATION.equals(columnName)) {
                column = "IFP_DESIGNATION";
            } else if ("parentItemFamilyplanId".equals(columnName)) {
                column = "PARENT_IFP_ID";
            } else if ("parentItemFamilyplanName".equals(columnName)) {
                column = "PARENT_IFP_NAME";
            }

            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                orderby = Constants.ASC;
            } else {
                orderby = "DESC" ;
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
                        parameters.put(betweenFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(startValue)));
                        parameters.put(betweenFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(endValue)));
                } else if (filter instanceof Compare) {

                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    if (ConstantsUtils.IFP_SYSTEM_ID.equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
                        String value = String.valueOf(compare.getValue());
                        if (operation.GREATER.toString().equalsIgnoreCase(operation.name())) {
                            parameters.put(compare.getPropertyId() + "from", value);
                        } else if (operation.LESS.toString().equalsIgnoreCase(operation.name())) {
                            parameters.put(compare.getPropertyId() + "to", value);
                        } else if (operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
                            parameters.put(compare.getPropertyId() + com.stpl.app.contract.abstractsearch.util.ConstantUtil.EQUAL, value);
                        }
                    } else {
                        Date value = (Date) compare.getValue();
                        if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            parameters.put(compare.getPropertyId() + "from", String.valueOf(dateFormat.format(value)));
                        } else {
                            parameters.put(compare.getPropertyId() + "to", String.valueOf(dateFormat.format(value)));
                        }
                    }

                } else if (filter instanceof And) {
                    And stringFilter = (And) filter;
                    Collection<Container.Filter> value = stringFilter.getFilters();
                    for (Container.Filter filter1 : value) {
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            parameters.put(less.getPropertyId() + "to", String.valueOf(less.getValue()));
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            parameters.put(greater.getPropertyId() + "from", String.valueOf(greater.getValue()));
                        }
                    }
                }
            }
        }
        List result = ImtdIfpDetailsLocalServiceUtil.getIFPSearchList(ifpId, ifpNo, ifpName, ifpType, ifpStatus,itemId,  itemNo,  itemName,  start,  end,  column, orderby,false, parameters);    
        
        
        LOGGER.debug("return ifp list ,size = " + ((result==null)?result:result.size()));
        return getLookUpCustomizedObjectModel(result);
    }
    public static final String ITEM_FAMILYPLAN_DESIGNATION = "itemFamilyplanDesignation";
    public static final String ITEM_FAMILYPLAN_CATEGORY = "itemFamilyplanCategory";
     public static List<TempPricingDTO> getLookUpCustomizedObjectModel(final List<Object[]> ifplist) throws  SystemException {
        final List<TempPricingDTO> ifpDTOlist = new ArrayList<>();
        Map<Integer, String> hm = CommonUtils.getCodeDescription();
        Map<Integer, String> userMap = StplSecurity.getUserName();
        for (Object[] obj : ifplist) {
            final TempPricingDTO ifpDTO = new TempPricingDTO();
            if (obj[0] != null) {
                ifpDTO.setItemFamilyplanSystemId(String.valueOf(obj[0]));
            }
            if (obj[1] != null) {
                ifpDTO.setItemFamilyplanId(String.valueOf(obj[1]));
            }
            if (obj[NumericConstants.TWO] != null) {
                ifpDTO.setItemFamilyplanNo(String.valueOf(obj[NumericConstants.TWO]));
            }
            if (obj[NumericConstants.THREE] != null) {
                ifpDTO.setItemFamilyplanName(String.valueOf(obj[NumericConstants.THREE]));
            }
            if (obj[NumericConstants.FOUR] != null && StringUtils.isNotBlank(obj[NumericConstants.FOUR].toString()) && (Integer) obj[NumericConstants.FOUR] != 0) {
                ifpDTO.setIfPStatus(hm.get(obj[NumericConstants.FOUR]));
            }
            if (obj[NumericConstants.FIVE] != null && StringUtils.isNotBlank(obj[NumericConstants.FIVE].toString()) && (Integer) obj[NumericConstants.FIVE] != 0) {
                ifpDTO.setIfpType(hm.get(obj[NumericConstants.FIVE]));
            }
            if (obj[NumericConstants.SIX] != null && StringUtils.isNotBlank(obj[NumericConstants.SIX].toString()) && (Integer) obj[NumericConstants.SIX] != 0) {
                ifpDTO.setIfpCategory(hm.get(obj[NumericConstants.SIX]));
            }
            if (obj[NumericConstants.SEVEN] != null) {
                ifpDTO.setStartDate((Date) obj[NumericConstants.SEVEN]);
            }
            if (obj[NumericConstants.EIGHT] != null) {
                ifpDTO.setEndDate((Date) obj[NumericConstants.EIGHT]);
            }
            if (obj[NumericConstants.NINE] != null && StringUtils.isNotBlank(obj[NumericConstants.NINE].toString()) && (Integer) obj[NumericConstants.NINE] != 0) {
                ifpDTO.setIfpDesignation(hm.get(obj[NumericConstants.NINE]));
            }
            if (obj[NumericConstants.TEN] != null) {
                ifpDTO.setParentItemFamilyplanId(String.valueOf(obj[NumericConstants.TEN]));
            }
            if (obj[NumericConstants.ELEVEN] != null) {
                ifpDTO.setParentItemFamilyplanName(String.valueOf(obj[NumericConstants.ELEVEN]));
            }
            if (obj[NumericConstants.SIXTEEN] != null) {
                ifpDTO.setCreatedBy(userMap.get(Integer.valueOf(String.valueOf(obj[NumericConstants.SIXTEEN]))));
            }
            if (obj[NumericConstants.SEVENTEEN] != null) {
                ifpDTO.setCreatedDate((Date) obj[NumericConstants.SEVENTEEN]);
            }

            ifpDTOlist.add(ifpDTO);
        }
        LOGGER.debug("return customized item size " + ifpDTOlist.size());

        return ifpDTOlist;
    }
       public static String getUseName(String userId){
  
      try {    
          if (userId != null && !userId.isEmpty() && Integer.valueOf(userId)!=1) {
              User createdUser = UserLocalServiceUtil.getUser(Long.valueOf(userId));
              return createdUser.getFullName();
          } else {
              return StringUtils.EMPTY;
          }
         } catch (Exception ex) { 
             LOGGER.error(ex);
        }
      return StringUtils.EMPTY;
    }
      public void saveIFP(final CustomFieldGroup ifpForm, int systemId)  {
        try {
            LOGGER.debug("Entering update operation");
            IfpContract item = IfpContractLocalServiceUtil.getIfpContract(systemId);

            item.setIfpCategory(ifpForm.getField(ITEM_FAMILYPLAN_CATEGORY).getValue() != null ? ((com.stpl.ifs.util.HelperDTO) ifpForm.getField(ITEM_FAMILYPLAN_CATEGORY).getValue()).getId() : 0);
            if (ifpForm.getField(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_FAMILYPLAN_TYPE).getValue() != null) {
                item.setIfpType(((com.stpl.ifs.util.HelperDTO) ifpForm.getField(com.stpl.app.contract.abstractsearch.util.ConstantUtil.ITEM_FAMILYPLAN_TYPE).getValue()).getId());
            } else {
                item.setIfpType(0);
            }
            item.setIfpDesignation(ifpForm.getField(ITEM_FAMILYPLAN_DESIGNATION).getValue() != null ? String.valueOf(((com.stpl.ifs.util.HelperDTO) ifpForm.getField(ITEM_FAMILYPLAN_DESIGNATION).getValue()).getId()) : StringUtils.EMPTY);
            item.setCreatedBy(Integer.parseInt(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID))));
            item.setModifiedDate(new Date());
            item.setIfpName(String.valueOf(ifpForm.getField(ITEM_FAMILYPLAN_NAME).getValue()));
            item.setIfpNo(String.valueOf(ifpForm.getField(ITEM_FAMILYPLAN_NO).getValue()));
            item.setParentIfpId(String.valueOf(ifpForm.getField(
                    "parentItemFamilyplanId").getValue()));
            item.setParentIfpName(String.valueOf(ifpForm.getField(
                    "parentItemFamilyplanName").getValue()).trim());
            item.setIfpStatus(((com.stpl.ifs.util.HelperDTO) ifpForm.getField(ConstantsUtils.IFP_STATUS).getValue()).getId());
            item.setIfpStartDate((Date) ifpForm.getField(
                    ConstantUtil.START_DATE).getValue());
                item.setIfpEndDate((Date) ifpForm.getField(
                        ConstantUtil.END_DATE).getValue());
            item.setModifiedDate(new Date());
            item.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
            IfpContractLocalServiceUtil.updateIfpContract(item);
        } catch (SystemException e) {
            LOGGER.error(e);
        } catch (PortalException e) {
            LOGGER.error(e);
        }
    }
          
    public   List<HelperDTO> getBrandDropDown() throws SystemException {

        final List<HelperDTO> helperList = new ArrayList<>();
        DynamicQuery dynamicQuery  = DynamicQueryFactoryUtil
                        .forClass(BrandMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.isNotNull(Constants.BRAND_NAME));
        final List<BrandMaster> list = BrandMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final BrandMaster brandMaster = (BrandMaster) list.get(i);
                helperList.add(new HelperDTO(brandMaster.getBrandMasterSid(), brandMaster
                        .getBrandName()));

            }
        }

        LOGGER.debug("return helperList " + helperList.size());
        Collections.sort(helperList);
        return helperList;
    }
    public int validateCCPActuals(String itemSid){
        final int contractSystemId = (Integer) (sessionDTO.getContractSystemId());
        final int cfpContractSId = (Integer) (sessionDTO.getCfpSystemId());
        final int ifpSystemId = (Integer) (sessionDTO.getIfpSystemId());
        if (contractSystemId == 0 || cfpContractSId == 0 || ifpSystemId == 0 || itemSid == null || itemSid.isEmpty() || Integer.valueOf(itemSid) == 0) {
            return 0;
        } else {
            String query = CustomSQLUtil.get("validateCCPActuals");
            query = query.replace("@CONSID", StringUtils.EMPTY + contractSystemId);
            query = query.replace("@CFPSID", StringUtils.EMPTY + cfpContractSId);
            query = query.replace("@IFPSID", StringUtils.EMPTY + ifpSystemId);
            query = query.replace("@ITEMSID", itemSid);
            List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
            LOGGER.debug("(int)list.get(0) = " + (int)list.get(0));
            return list==null?0:(int)list.get(0);
        }
    
    }
     public  int isEmpty(SessionDTO sessDto,boolean isItem){
         
         final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
         int user = Integer.valueOf(userId);
         String sql = "select count(*) from dbo.IMTD_ITEM_PRICE_REBATE_DETAILS ";
         sql += " WHERE USERS_SID = " + user;
         if(isItem){
         sql += " AND CHECK_RECORD = 1";
         } else {
             sql += " AND CHECK_RECORD = 1";
         }
         sql += " AND SESSION_ID like '" + sessDto.getUiSessionId() + "' AND \"OPERATION\" <> 'D' ";
         List resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(sql);
       int val=resultList == null ? 0 : Integer.valueOf(String.valueOf(resultList.get(0)));
       return val;
     }
     
      public List<TempPricingDTO> getPricingResultList() {
        return pricingResultList;
    }

    public void setPricingResultList(List<TempPricingDTO> pricingResultList) {
        this.pricingResultList = pricingResultList;
    }

    public List<TempPricingDTO> getPriceProtectionResultList() {
        return priceProtectionResultList;
    }

    public void setPriceProtectionResultList(List<TempPricingDTO> priceProtectionResultList) {
        this.priceProtectionResultList = priceProtectionResultList;
    }
    
    public List<TempRebateDTO> getRebateResultList() {
        return rebateResultList;
    }

    public void setRebateResultList(List<TempRebateDTO> rebateResultList) {
        this.rebateResultList = rebateResultList;
    }
    public List<Object[]> getCheckRecordOnTabChange(){
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        String sql = "SELECT CHECK_RECORD FROM IMTD_ITEM_PRICE_REBATE_DETAILS WHERE SESSION_ID = "+sessionId
                +" USER_ID = "+userId;
        List<Object[]> returnList=HelperTableLocalServiceUtil.executeSelectQuery(sql);
        return returnList;
    }
    public List<TempPricingDTO> getCustomizedPriceOnTabChange(List<Object[]> returnList){
        TempPricingDTO itemDTO;
        List<TempPricingDTO> resultList = new ArrayList<>();
        for (Object[] tempifp : returnList) {
            itemDTO = new TempPricingDTO();
            itemDTO.setCheckbox((Boolean) tempifp[0]);
            resultList.add(itemDTO);
        }
        return resultList;
    }
    public static void savePricingCheck(List<TempPricingDTO> saveList) {
        String sql="";
        for (TempPricingDTO temp : saveList) {
            if(temp.getCheckbox()){
                sql= "update IMTD_ITEM_PRICE_REBATE_DETAILS set check_record = "+1;
            }else{
                sql= "update IMTD_ITEM_PRICE_REBATE_DETAILS set check_record = "+0;
            }
            sql = sql+" where IMTD_ITEM_PRICE_REBATE_SID = " +temp.getTempItemPriceRebateSystemId();
            HelperTableLocalServiceUtil.executeUpdateQuery(sql);
        }
    }
    public static boolean  emptyCheck(SessionDTO dto){
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        String tabName="check_record";
         String sql="Select count(*) from dbo.IMTD_ITEM_PRICE_REBATE_DETAILS where USERS_SID like '"+userId+"' and SESSION_ID like '"+dto.getUiSessionId()+"' and "+tabName+"=1";
        List list=HelperTableLocalServiceUtil.executeSelectQuery(sql);
         return list==null||(int)list.get(0)<=0 ?true:false;
        
    }
    public static void  updateTempCheck(SessionDTO dto,String id,boolean value){
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        String tabName="check_record";
        int val=value==false?0:1;
         String sql="Update  dbo.IMTD_ITEM_PRICE_REBATE_DETAILS Set  "+tabName+"="+val+" where USERS_SID like '"+userId+"' and SESSION_ID like '"+dto.getUiSessionId()+"' and  IMTD_ITEM_PRICE_REBATE_SID="+id;
        HelperTableLocalServiceUtil.executeUpdateQuery(sql);
        
    }
    public static String getPriceTypeNameByID(final String priceType) {
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class);
        if (priceType != null && !StringUtils.EMPTY.equals(priceType) && priceType != "null") {
            cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.ITEM_PRICING_QUALIFIER_SID, Integer.valueOf(priceType)));
        }
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        List<String> returnList = null;
        try {
            returnList = ItemPricingQualifierLocalServiceUtil.dynamicQuery(cfpDynamicQuery);
        } catch (Exception ex) { 
           LOGGER.error(ex);
        }
        if (returnList.isEmpty()) {
            return ConstantsUtils.SELECT_ONE;
        } else {
            return String.valueOf(returnList.get(0));
        }

    }
    
      public static List<HelperDTO> getCPPriceTypeResults() {
        final List<HelperDTO> list = new ArrayList<>();
        LOGGER.debug("Entering getLazyPriceTypeResults method with filterText :");
        String query = "select ITEM_PRICING_QUALIFIER_SID,ITEM_PRICING_QUALIFIER_NAME from ITEM_PRICING_QUALIFIER where ITEM_PRICING_QUALIFIER_NAME like 'contract%'\n";
        List returnList = (List) CompanyMasterLocalServiceUtil.executeSelectQuery(query, null, null);
        HelperDTO helperTable;
        for (int i = 0; i < returnList.size(); i++) {
            final Object[] obj = (Object[]) returnList.get(i);
            helperTable = new HelperDTO();
            helperTable.setId(obj[0] != null ? Integer.valueOf(obj[0].toString()) : 0);
            helperTable.setDescription(obj[1] != null ? obj[1].toString() : StringUtils.EMPTY);
            if (!StringUtils.EMPTY.equals(helperTable.getDescription())) {
                list.add(helperTable);
            }
        }
        LOGGER.debug("Ending getCPPriceTypeResults  return list size :" + +list.size());
        return list;
    }
      
      public Boolean verifyPrice(){
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        String query = "SELECT count(IMTD_ITEM_PRICE_REBATE_SID) from IMTD_ITEM_PRICE_REBATE_DETAILS where SESSION_ID = '"+sessionId+"' AND CHECK_RECORD = 1 AND USERS_SID ="+userId+" AND \"OPERATION\" <> 'D' \n" +
                        " AND (PRICE IS NULL OR PRICE =0) AND PRICE_TYPE IN (select ITEM_PRICING_QUALIFIER_SID from ITEM_PRICING_QUALIFIER"
                        + " where ITEM_PRICING_QUALIFIER_NAME like 'contract%')";

        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return (Integer) list.get(0)>0;
    }

      public static boolean checkTempCheckedAll(SessionDTO dto){
          boolean checkedAll = false;
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        String sql = "SELECT DISTINCT CHECK_RECORD FROM DBO.IMTD_ITEM_PRICE_REBATE_DETAILS WHERE USERS_SID LIKE  '"+userId+"' AND SESSION_ID LIKE '"+dto.getUiSessionId()+"';";
        List checkAllTest = HelperTableLocalServiceUtil.executeSelectQuery(sql);
        if(checkAllTest != null && !checkAllTest.isEmpty()) {
            if(checkAllTest.size() == NumericConstants.TWO) {
                checkedAll = false;
            } else if(checkAllTest.size() == 1){
                checkedAll = Boolean.parseBoolean(String.valueOf(checkAllTest.get(0)));
                }
        }
        return checkedAll;
    }

    private void massPopulateForBasePrice(Object populateValue, List<Object> input, boolean flag) {
        final SimpleDateFormat tempFromat = new SimpleDateFormat(CommonUtils.MMDDYYYY);
        String[] val = populateValue.toString().split(ContractUtils.DELIMITER);
        String[] head = {"base_Price_Entry", "base_Price_Date", "base_Price_Ddlb"};
        input.add("base_Price_Type");
        input.add(val[1]);
        input.add(String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        input.add(tempFromat.format(new Date()));
        input.add(String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        input.add(String.valueOf(sessionDTO.getUiSessionId()));
        populateQuery(input, flag);
        input.clear();
        switch (val[0]) {
            case com.stpl.app.serviceUtils.Constants.MANUAL:
                input.add(head[0]);
                break;
            case com.stpl.app.serviceUtils.Constants.DATE:
                input.add(head[1]);
                break;
            case ContractUtils.FIELD_PRICE_TYPE:
                input.add(head[2]);
                break;
        }
        input.add(val[2]);
        input.add(String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        input.add(tempFromat.format(new Date()));
        input.add(String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        input.add(String.valueOf(sessionDTO.getUiSessionId()));
        populateQuery(input, flag);
    }

    public void populateQuery(List<Object> input, boolean flag) {
        if (flag) {
            ImtdItemPriceRebateDetailsLocalServiceUtil.massPopulateAll(input, null);
        } else {
            input.add(com.stpl.app.contract.abstractsearch.util.ConstantUtil.CHECK_RECORD_CAPTION);
            ImtdItemPriceRebateDetailsLocalServiceUtil.massPopulate(input, null);
        }
    } 

    private Map loadColumnMap() {
        Map<String,String> columnMap=new HashMap<>();
        columnMap.put("itemNo", "IMTD.ITEM_NO");
        columnMap.put("itemName", "IMTD.ITEM_NAME");
        columnMap.put("itemDesc", "IM.ITEM_DESC");
        columnMap.put("ifpStatus", "IMTD.ATTACHED_STATUS");
        columnMap.put("startDate", "IMTD.START_DATE");
        columnMap.put("endDate", "IMTD.END_DATE");
        columnMap.put("itemsStatus", "IMTD.ATTACHED_STATUS");
        columnMap.put("form", "IM.FORM");
        columnMap.put("strength", "IM.STRENGTH");
        columnMap.put("therapyClass", "IM.THERAPEUTIC_CLASS");
        columnMap.put("brand", "IMTD.BRAND_MASTER_SID");
        columnMap.put("attachedDate", "IMTD.ATTACHED_DATE");
        columnMap.put("modifiedDate", "IMTD.MODIFIED_DATE");
        columnMap.put("modifiedBy", "IMTD.MODIFIED_BY");
        columnMap.put("createdDate", "IMTD.CREATED_DATE");
        columnMap.put("createdBy", "IMTD.CREATED_BY");
        columnMap.put("checkbox", "IMTD.CHECK_RECORD");
        columnMap.put("itemId", "IMTD.ITEM_ID");
        columnMap.put("globalitemstatus", "IMTD.ATTACHED_STATUS");
        columnMap.put("cpStartDate", "IMTD.CONTRACT_PRICE_START_DATE");
        columnMap.put("cpEndDate", "IMTD.CONTRACT_PRICE_END_DATE");
        columnMap.put("priceType", "IMTD.PRICE_TYPE");
        columnMap.put("price", "IMTD.PRICE");
        columnMap.put("suggestedPrice", "IMTD.SUGGESTED_PRICE");
        columnMap.put("source", "IMTD.\"SOURCE\"");
        return columnMap;
     }

    private Map<String, String> loadItemRebateColumnMap() {
           Map<String,String> itemRebateColumnMap=new HashMap<>();
           itemRebateColumnMap.put("checkbox", "CHECK_RECORD");
           itemRebateColumnMap.put("itemNo", "ITEM_NO");
           itemRebateColumnMap.put("itemName", "ITEM_NAME");
           itemRebateColumnMap.put("attachedStatus", "IMTD.RS_ATTACHED_STATUS");
           itemRebateColumnMap.put("rebateStartDate", "ITEM_REBATE_START_DATE");
           itemRebateColumnMap.put("rebateEndDate", "ITEM_REBATE_END_DATE");
           itemRebateColumnMap.put("deductionCalendarNo", "DEDUCTION_CALENDAR_NO");
           itemRebateColumnMap.put("deductionCalendarName", "DEDUCTION_CALENDAR_NAME");
           itemRebateColumnMap.put("evaluationRule", "EVA.RULE_NAME");
           itemRebateColumnMap.put("evaluationRuleBundle", "IMTD.EVALUATION_RULE_BUNDLE");
           itemRebateColumnMap.put("calculationRuleBundle", "IMTD.CALCULATION_RULE_BUNDLE");
           itemRebateColumnMap.put("attachedDate", "IMTD.ATTACHED_DATE");
           itemRebateColumnMap.put("formulaType", "IMTD.ITEM_TYPE");
           itemRebateColumnMap.put("formulaNo", "FF.FORMULA_NO");
           itemRebateColumnMap.put("formulaName", "FF.FORMULA_NAME");
           itemRebateColumnMap.put("netSalesFormulaNo", "NSFM.NET_SALES_FORMULA_NO");
           itemRebateColumnMap.put("netSalesRule", "NET.RULE_NAME");
           itemRebateColumnMap.put("rebatePlanNo", "RPM.REBATE_PLAN_NO");
           itemRebateColumnMap.put("rebatePlanName", "RPM.REBATE_PLAN_NAME");
           return itemRebateColumnMap;
    }
}
