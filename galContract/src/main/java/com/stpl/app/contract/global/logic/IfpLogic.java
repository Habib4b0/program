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
import com.stpl.app.service.FormulaDetailsMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.IfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.IfpContractLocalServiceUtil;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.ImtdIfpDetailsLocalServiceUtil;
import com.stpl.app.service.ImtdItemPriceRebateDetailsLocalServiceUtil;
import com.stpl.app.service.ItemPricingQualifierLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.util.ConstantUtil;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.domain.contract.contractdashboard.DashboardDAO;
import com.stpl.domain.contract.contractdashboard.ItemFamilyplanDAO;
import com.stpl.domain.contract.contractdashboard.globalcontract.ItemFamilyplanLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
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
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Map<String, String> map = new HashMap<String, String>();

    DecimalFormat df = new DecimalFormat("#0.00");
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
     * The dao.
     */
    private static final ItemFamilyplanDAO DAO = new IFPLogicDAOImpl();
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
        LOGGER.info("Entering getItemForIFP method");
        final Map<String, String> map = new HashMap<String, String>();
        map.put(Constants.ITEM_NO2, "Item_No");
        map.put(Constants.ITEM_NAME2, "Item_Name");
        map.put(Constants.IFP_NO1, "IFP_NO");
        map.put(Constants.IFP_NAME1, "IFP_NAME");
        map.put("NDC 8", Constants.NDC8);
        map.put("NDC 9", Constants.NDC9);
        map.put("Item Description", Constants.ITEM_DESC1);
        map.put("Form", Constants.FORM);
        map.put("Strength", Constants.STRENGTH);
        map.put("Therapeutic Class", "THERAPEUTIC_CLASS");
        map.put("Brand Name", Constants.BRAND_NAME);
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
                column = "ITEM_NAME";
            } else if ("form".equals(orderByColumn.getName())) {
                column = "hform";
            } else if ("packageSize".equals(orderByColumn.getName())) {
                column = "hpackage";
            } else if (Constants.ITEM_DESC.equals(orderByColumn.getName())) {
                column = Constants.ITEM_DESC1;
            } else if (Constants.ITEM_STATUS.equals(orderByColumn.getName())) {
                column = "status";
            } else if (Constants.STRENGTH.equals(orderByColumn.getName())) {
                column = "strength";
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
            List<Integer> resultList = new ArrayList<Integer>();
            if (Constants.BRAND_NAME.equals(searchVal)) {
                final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);
                dynamicQuery.add(RestrictionsFactoryUtil.like(searchVal, value));
                dynamicQuery.setProjection(ProjectionFactoryUtil.property("brandMasterSid"));
                resultList = BrandMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
            }

            Map<String, Object> filterMap = new HashMap<String, Object>();
            filterMap.put("itemNo", StringUtils.EMPTY);
            filterMap.put("itemName", StringUtils.EMPTY);
            filterMap.put("form", StringUtils.EMPTY);
            filterMap.put("packageSize", StringUtils.EMPTY);

            if (searchCriteria != null) {
                for (Container.Filter filter : searchCriteria) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterText = "%" + stringFilter.getFilterString() + "%";

                        if (stringFilter.getPropertyId().equals("itemNo")) {
                            filterMap.put("itemNo", filterText);
                        } else if (stringFilter.getPropertyId().equals("itemName")) {
                            filterMap.put("itemName", filterText);
                        } else if (stringFilter.getPropertyId().equals("form")) {

                            String form = stringFilter.getFilterString();
                            filterMap.put("form", form);

                        } else if (stringFilter.getPropertyId().equals("packageDesc")) {
                            String packageSize = stringFilter.getFilterString();

                            filterMap.put("packageSize", packageSize);
                        } else if (stringFilter.getPropertyId().equals("itemStatus")) {

                            String status = stringFilter.getFilterString();
                            filterMap.put("itemStatus", status);

                        } else if (stringFilter.getPropertyId().equals("strength")) {
                            String strength = stringFilter.getFilterString();
                            filterMap.put("strength", strength);
                        } else if (stringFilter.getPropertyId().equals("therapeuticClass")) {

                            String therapeutic = stringFilter.getFilterString();
                            filterMap.put("therapeuticClass", therapeutic);

                        } else if (stringFilter.getPropertyId().equals("itemDesc")) {
                            filterMap.put("itemDesc", filterText);
                        } else if (stringFilter.getPropertyId().equals("brand")) {
                            filterMap.put("brand", filterText);
                        }

                    }
                }
            }

            List result = IfpContractDetailsLocalServiceUtil.findIFP(searchVal, value, resultList, filterMap, start, end, column, orderBy, null);
            itemMasterDTOList = getCustomizedItem(result);

        } else {
            itemMasterDTOList = new ArrayList<ItemMasterDTO>();
        }
        LOGGER.info("End of getItemForIFP method");
        return itemMasterDTOList;
    }

    /**
     * Returns the List of ItemMasterDTO based on search field.
     *
     * @param searchField - String contains Item Details.
     * @param value - Value for WildCard
     * @return List of ItemMasterDTO.
     * @throws SystemException
     */
    public List<ItemMasterDTO> getItemIFP(final String searchField, final String val) {
        LOGGER.info("Entering getItemForIFP method");
        final Map<String, String> map = new HashMap<String, String>();
        map.put(Constants.ITEM_NO2, Constants.ITEM_NO);
        map.put(Constants.ITEM_NAME2, Constants.ITEM_NAME);
        List<ItemMasterDTO> itemMasterDTOList = new ArrayList<ItemMasterDTO>();

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
                LOGGER.error(ex.getMessage());
            }

        } else {
            itemMasterDTOList = new ArrayList<ItemMasterDTO>();
        }
        LOGGER.info("End of getItemForIFP method");
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
        LOGGER.info("Entering getItemAdditionCount method");
        final Map<String, String> map = new HashMap<String, String>();
        map.put(Constants.ITEM_NO2, "Item_No");
        map.put(Constants.ITEM_NAME2, "Item_Name");
        map.put(Constants.IFP_NO1, "IFP_NO");
        map.put(Constants.IFP_NAME1, "IFP_NAME");
        map.put("NDC 8", Constants.NDC8);
        map.put("NDC 9", Constants.NDC9);
        map.put("Item Description", Constants.ITEM_DESC1);
        map.put("Form", Constants.FORM);
        map.put("Strength", Constants.STRENGTH);
        map.put("Therapeutic Class", "THERAPEUTIC_CLASS");
        map.put("Brand Name", Constants.BRAND_NAME);
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
            List<Integer> resultList = new ArrayList<Integer>();
            if (Constants.BRAND_NAME.equals(searchVal)) {
                final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);
                dynamicQuery.add(RestrictionsFactoryUtil.like(searchVal, value));
                dynamicQuery.setProjection(ProjectionFactoryUtil.property("brandMasterSid"));
                resultList = BrandMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
            }
            Map<String, Object> filterMap = new HashMap<String, Object>();
            filterMap.put("itemNo", StringUtils.EMPTY);
            filterMap.put("itemName", StringUtils.EMPTY);
            filterMap.put("form", StringUtils.EMPTY);
            filterMap.put("packageSize", StringUtils.EMPTY);

            if (searchCriteria != null) {
                for (Container.Filter filter : searchCriteria) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterText = "%" + stringFilter.getFilterString() + "%";

                        if (stringFilter.getPropertyId().equals("itemNo")) {
                            filterMap.put("itemNo", filterText);
                        } else if (stringFilter.getPropertyId().equals("itemName")) {
                            filterMap.put("itemName", filterText);
                        } else if (stringFilter.getPropertyId().equals("form")) {

                            String form = stringFilter.getFilterString();
                            filterMap.put("form", form);

                        } else if (stringFilter.getPropertyId().equals("packageDesc")) {
                            String packageSize = stringFilter.getFilterString();

                            filterMap.put("packageSize", packageSize);
                        } else if (stringFilter.getPropertyId().equals("itemStatus")) {

                            String status = stringFilter.getFilterString();
                            filterMap.put("itemStatus", status);

                        } else if (stringFilter.getPropertyId().equals("strength")) {
                            String strength = stringFilter.getFilterString();
                            filterMap.put("strength", strength);
                        } else if (stringFilter.getPropertyId().equals("therapeuticClass")) {

                            String therapeutic = stringFilter.getFilterString();
                            filterMap.put("therapeuticClass", therapeutic);

                        } else if (stringFilter.getPropertyId().equals("itemDesc")) {
                            filterMap.put("itemDesc", filterText);
                        } else if (stringFilter.getPropertyId().equals("brand")) {
                            filterMap.put("brand", filterText);
                        }

                    }
                }
            }
           List result = IfpContractDetailsLocalServiceUtil.findIFP(searchVal, value, resultList, filterMap, 0, 0, null, null, null);
            count = result.size();

        } else {
            count = 0;
        }
        LOGGER.info("End of getItemAdditionCount method");
        return count;
    }

    /**
     * Returns the customized list of ItemMasterDTO.
     *
     * @param itemMasterList - List of ItemMaster model.
     * @return List of ItemMasterDTO
     */
    public List<ItemMasterDTO> getCustomizedItemList(final List<ItemMaster> itemMasterList) {
        LOGGER.info("Entering getCustomizedItemList()");
        final List<ItemMasterDTO> itemDTOList = new ArrayList<ItemMasterDTO>();
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
        LOGGER.info("End of getCustomizedItemList()");
        return itemDTOList;
    }

    public static List<ItemMasterDTO> getCustomizedItem(List list) {
        List<ItemMasterDTO> tempList = new ArrayList<ItemMasterDTO>();
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

    public int getLazySelectedItemCount(BeanSearchCriteria searchCriteria) throws PortalException, SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdItemPriceRebateDetails.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.USERS_SID, Integer.parseInt(userId)));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.SESSION_ID, sessionId));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ne("operation", "D"));

        if (searchCriteria != null && searchCriteria.getFilters() != null) {
            for (Container.Filter filter : searchCriteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterText = "%" + stringFilter.getFilterString() + "%";

                    if (stringFilter.getPropertyId().equals("itemNo")) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike("itemNo", filterText));
                    } else if (stringFilter.getPropertyId().equals("itemName")) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike("itemName", filterText));
                    } else if (stringFilter.getPropertyId().equals("packageSize")) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike("packageSize", stringFilter.getFilterString()));
                    } else if (stringFilter.getPropertyId().equals("priceType")) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike("priceType", stringFilter.getFilterString()));
                    } else if (stringFilter.getPropertyId().equals("globalitemstatus")) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq("psStatus", Integer.valueOf(stringFilter.getFilterString())));
                    } else if (stringFilter.getPropertyId().equals("priceToleranceType")) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike("priceToleranceType", stringFilter.getFilterString()));
                    } else if (stringFilter.getPropertyId().equals("priceToleranceInterval")) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq("priceToleranceInterval", Integer.valueOf(stringFilter.getFilterString())));
                    } else if (stringFilter.getPropertyId().equals("priceToleranceFrequency")) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike("priceToleranceFrequency", stringFilter.getFilterString()));
                    } else if (stringFilter.getPropertyId().equals("attachedStatus")) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq("attachedStatus", Integer.valueOf(stringFilter.getFilterString())));
                    } else if (stringFilter.getPropertyId().equals("itemType")) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike("itemType", Integer.valueOf(stringFilter.getFilterString())));
                    } else if (stringFilter.getPropertyId().equals("rebatePlanName")) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq("rebatePlanSystemId", Integer.valueOf(stringFilter.getFilterString())));
                    }

                } else if (filter instanceof Between) {

                    Between betweenFilter = (Between) filter;
                    Date fromDate = (Date) betweenFilter.getStartValue();
                    Date toDate = (Date) betweenFilter.getEndValue();
                    if (betweenFilter.getPropertyId().equals("startDate")) {
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between("startDate", fromDate, toDate));

                    } else if (betweenFilter.getPropertyId().equals("endDate")) {

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between("endDate", fromDate, toDate));

                    } else if (betweenFilter.getPropertyId().equals("cpStartDate")) {

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between("contractPriceStartDate", fromDate, toDate));

                    } else if (betweenFilter.getPropertyId().equals("cpEndDate")) {

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between("contractPriceEndDate", fromDate, toDate));

                    } else if (betweenFilter.getPropertyId().equals("ppStartDate")) {

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between("priceProtectionStartDate", fromDate, toDate));

                    } else if (betweenFilter.getPropertyId().equals("ppEndDate")) {

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between("priceProtectionEndDate", fromDate, toDate));

                    } else if (betweenFilter.getPropertyId().equals("revisionDate")) {

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between("revisionDate", fromDate, toDate));

                    } else if (betweenFilter.getPropertyId().equals("attachedDate")) {

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between("attachedDate", fromDate, toDate));

                    } else if (betweenFilter.getPropertyId().equals("rebateStartDate")) {

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between("itemRebateStartDate", fromDate, toDate));

                    } else if (betweenFilter.getPropertyId().equals("rebateEndDate")) {

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between("itemRebateEndDate", fromDate, toDate));

                    } else if (betweenFilter.getPropertyId().equals("rebateRevisionDate")) {

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.between("rebateRevisionDate", fromDate, toDate));

                    }
                }
            }
        }

        final Long count = ImtdItemPriceRebateDetailsLocalServiceUtil.dynamicQueryCount(cfpDynamicQuery);
        LOGGER.info("selected count :" + count);
        return Integer.parseInt(String.valueOf(count));
    }

    public int getLazySelectedItemsCount(final Set<Container.Filter> searchCriteria, SessionDTO sessionDTO) throws PortalException, SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        String columnName = StringUtils.EMPTY;
        Map<String, Object> parameters = new HashMap<String, Object>();
        final List<IfpItemDTO> itemList = new ArrayList<IfpItemDTO>();

        if (searchCriteria != null) {
            for (Container.Filter filter : searchCriteria) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterText = "%" + stringFilter.getFilterString() + "%";

                    if (stringFilter.getPropertyId().equals("itemNo")) {
                        parameters.put("itemNo~", filterText);

                    } else if (stringFilter.getPropertyId().equals("itemName")) {
                        parameters.put("itemName~", filterText);
                    } else if (stringFilter.getPropertyId().equals("form")) {

                        String form = stringFilter.getFilterString();
                        parameters.put("form", form);

                    } else if (stringFilter.getPropertyId().equals("packageDesc")) {
                        String packageSize = stringFilter.getFilterString();

                        parameters.put("packageSize", packageSize);
                    } else if (stringFilter.getPropertyId().equals("itemStatus")) {

                        String status = stringFilter.getFilterString();
                        parameters.put("itemStatus", status);

                    } else if (stringFilter.getPropertyId().equals("strength")) {
                        String strength = stringFilter.getFilterString();
                        parameters.put("strength", strength);
                    } else if (stringFilter.getPropertyId().equals("therapeuticClass")) {

                        String therapeutic = stringFilter.getFilterString();
                        parameters.put("therapeuticClass", therapeutic);

                    } else if (stringFilter.getPropertyId().equals("itemDesc")) {
                        parameters.put("itemDesc", filterText);
                    } else if (stringFilter.getPropertyId().equals("brand")) {
                        parameters.put("brand", filterText);
                    }

                }
            }
        }

        final List<Object[]> returnList = ImtdItemPriceRebateDetailsLocalServiceUtil.getSelectedItemList(userId, sessionId, 0, 0, null, null, parameters, false, null, null, null);
        LOGGER.info("selected results========== :" + returnList.size());
        return returnList.size();
    }

    public List<IfpItemDTO> getLazySelectedItemDeatils(int start, int end, final List<SortByColumn> orderByColumns, final Set<Container.Filter> searchCriteria, SessionDTO sessionDTO) throws PortalException, SystemException {
        LOGGER.info("Start- ---> " + start + " and End --->> " + end);
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        String columnName = StringUtils.EMPTY;
        String dbColumnName = StringUtils.EMPTY;
        Map<String, Object> parameters = new HashMap<String, Object>();
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdItemPriceRebateDetails.class);
        final List<IfpItemDTO> itemList = new ArrayList<IfpItemDTO>();
        ifpDynamicQuery.setLimit(start, end);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.USERS_SID, Integer.parseInt(userId)));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.SESSION_ID, sessionId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne("operation", "D"));
        ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property("itemMasterSid"));
        projList.add(ProjectionFactoryUtil.property("itemNo"));
        projList.add(ProjectionFactoryUtil.property("itemName"));
        projList.add(ProjectionFactoryUtil.property("psStatus"));
        projList.add(ProjectionFactoryUtil.property("imtdItemPriceRebateSid"));
        ifpDynamicQuery.setProjection(projList);
        boolean asc = false;
        for (final Iterator<SortByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
            final SortByColumn orderByColumn = (SortByColumn) iterator.next();
            columnName = orderByColumn.getName();
            if (Constants.ITEM_NO.equals(columnName)) {
                dbColumnName = Constants.ITEM_NO_CAPS;
            } else if (Constants.ITEM_NAME.equals(columnName)) {
                dbColumnName = "ITEM_NAME";
            } else if ("form".equals(orderByColumn.getName())) {
                dbColumnName = "form";
            } else if ("packageSize".equals(orderByColumn.getName())) {
                dbColumnName = "package";
            } else if (Constants.ITEM_DESC.equals(orderByColumn.getName())) {
                dbColumnName = Constants.ITEM_DESC1;
            } else if (Constants.ITEM_STATUS.equals(orderByColumn.getName())) {
                dbColumnName = "status";
            } else if (Constants.STRENGTH.equals(orderByColumn.getName())) {
                dbColumnName = "strength";
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

                    if (stringFilter.getPropertyId().equals("itemNo")) {
                        parameters.put("itemNo", filterText);
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike("itemNo", filterText));

                    } else if (stringFilter.getPropertyId().equals("itemName")) {
                        parameters.put("itemName", filterText);
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike("itemName", filterText));
                    } else if (stringFilter.getPropertyId().equals("form")) {

                        String form = stringFilter.getFilterString();
                        parameters.put("form", form);

                    } else if (stringFilter.getPropertyId().equals("packageDesc")) {
                        String packageSize = stringFilter.getFilterString();

                        parameters.put("packageSize", packageSize);
                    } else if (stringFilter.getPropertyId().equals("itemStatus")) {

                        String status = stringFilter.getFilterString();
                        parameters.put("itemStatus", status);

                    } else if (stringFilter.getPropertyId().equals("strength")) {
                        String strength = stringFilter.getFilterString();
                        parameters.put("strength", strength);
                    } else if (stringFilter.getPropertyId().equals("therapeuticClass")) {

                        String therapeutic = stringFilter.getFilterString();
                        parameters.put("therapeuticClass", therapeutic);

                    } else if (stringFilter.getPropertyId().equals("itemDesc")) {
                        parameters.put("itemDesc", filterText);
                    } else if (stringFilter.getPropertyId().equals("brand")) {
                        parameters.put("brand", filterText);
                    }

                }
            }
        }

        final List<Object[]> returnList = ImtdItemPriceRebateDetailsLocalServiceUtil.getSelectedItemList(userId, sessionId, start, end, dbColumnName, asc, parameters, true, null, null, null);
        LOGGER.info("selected results :" + returnList.size());
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
                    dto.setItemName(CommonUtils.getString(obj[2]));
                    dto.setPackageSize(CommonUtils.getString(obj[3]));
                    dto.setForm(CommonUtils.getString(obj[4]));
                    dto.setItemDesc(CommonUtils.getString(obj[5]));
                    dto.setItemStatus(CommonUtils.getString(obj[6]));
                    dto.setStrength(CommonUtils.getString(obj[7]));
                    dto.setTherapeuticClass(Constants.SELECT_ONE.equals(String.valueOf(obj[8]))? StringUtils.EMPTY : CommonUtils.getString(obj[8]));
                    dto.setBrand(CommonUtils.getString(obj[9]));
                    itemList.add(dto);

                }
            }

            return itemList;
        } catch (Exception e) {
             LOGGER.error(e.getMessage());
            return null;
        }
    }

    public List<Object[]> getLazyItemPricingDeatils(int start, int end, Set<Container.Filter> searchCriteria, boolean isCount, String record,boolean isIfpItemsTab, boolean isRemove) throws PortalException, SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        StringBuilder sql = new StringBuilder();
        Map<String, Object> parameters = new HashMap<String, Object>();
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
        sql.append(" WHERE USERS_SID ='").append(userId).append("' AND SESSION_ID ='").append(sessionId).append("' AND OPERATION NOT IN ('D','F') ");
       
        if(isRemove){
            sql.append(" AND IMTD.CHECK_RECORD = '1' ");
        }

        if ((parameters.get("itemNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemNo"))))) {
            sql.append(" AND IMTD.ITEM_NO LIKE '%").append(String.valueOf(parameters.get("itemNo"))).append("%' ");
        }
        if ((parameters.get("itemName") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemName"))))) {
            sql.append(" AND IMTD.ITEM_NAME LIKE '%").append(String.valueOf(parameters.get("itemName"))).append("%' ");
        }
        if ((parameters.get("itemId") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemId"))))) {
            sql.append(" AND IMTD.ITEM_ID LIKE '%").append(String.valueOf(parameters.get("itemId"))).append("%' ");
        }
        if ((parameters.get("startDate") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("startDate"))))) {
            sql.append(" AND IMTD.START_DATE >= '").append(String.valueOf(parameters.get("startDate"))).append("' ");
        }
        if ((parameters.get("endDate") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("endDate"))))) {
            sql.append(" AND IMTD.END_DATE <= '").append(String.valueOf(parameters.get("endDate"))).append("' ");
        }
        if ((parameters.get("price") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("price"))))) {
            sql.append(" AND IMTD.PRICE LIKE '%").append(String.valueOf(parameters.get("price"))).append("%' ");
        }
        if ((parameters.get("priceType") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceType"))))) {
            sql.append(" AND IMTD.PRICE_TYPE LIKE '").append(String.valueOf(parameters.get("priceType"))).append("' ");
        }
        if ((parameters.get("contractPrice") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("contractPrice"))))) {
            sql.append(" AND IMTD.CONTRACT_PRICE LIKE '%").append(String.valueOf(parameters.get("contractPrice"))).append("%' ");
        }
        if ((parameters.get("contractPriceStartDate") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("contractPriceStartDate"))))) {
            sql.append(" AND IMTD.CONTRACT_PRICE_START_DATE >= '").append(String.valueOf(parameters.get("contractPriceStartDate"))).append("' ");
        }
        if ((parameters.get("contractPriceEndDate") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("contractPriceEndDate"))))) {
            sql.append(" AND IMTD.CONTRACT_PRICE_END_DATE <= '").append(String.valueOf(parameters.get("contractPriceEndDate"))).append("' ");
        }
        if ((parameters.get("psStatus") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("psStatus"))))) {
            sql.append(" AND IMTD.PS_STATUS LIKE '").append(String.valueOf(parameters.get("psStatus"))).append("' ");
        }

        if ((parameters.get("priceTolerance") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceTolerance"))))) {
            sql.append(" AND IMTD.PRICE_TOLERANCE LIKE '").append(String.valueOf(parameters.get("priceTolerance"))).append("' ");
        }
        if ((parameters.get("priceProtectionStartDate") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceProtectionStartDate"))))) {
            sql.append(" AND IMTD.PRICE_PROTECTION_START_DATE >= '").append(String.valueOf(parameters.get("priceProtectionStartDate"))).append("' ");
        }
        if ((parameters.get("priceProtectionEndDate") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceProtectionEndDate"))))) {
            sql.append(" AND IMTD.PRICE_PROTECTION_END_DATE <= '").append(String.valueOf(parameters.get("priceProtectionEndDate"))).append("' ");
        }
        if ((parameters.get("priceToleranceType") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceToleranceType"))))) {
            sql.append(" AND IMTD.PRICE_TOLERANCE_TYPE LIKE '").append(String.valueOf(parameters.get("priceToleranceType"))).append("' ");
        }
        if ((parameters.get("priceToleranceInterval") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceToleranceInterval"))))) {
            sql.append(" AND IMTD.PRICE_TOLERANCE_INTERVAL LIKE '").append(String.valueOf(parameters.get("priceToleranceInterval"))).append("' ");
        }
        if ((parameters.get("priceToleranceFrequency") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceToleranceFrequency"))))) {
            sql.append(" AND IMTD.PRICE_TOLERANCE_FREQUENCY LIKE '").append(String.valueOf(parameters.get("priceToleranceFrequency"))).append("' ");
        }
        if ((parameters.get("basePrice") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("basePrice"))))) {
            sql.append(" AND IMTD.BASE_PRICE LIKE '").append(String.valueOf(parameters.get("basePrice"))).append("' ");
        }
        if ((parameters.get("itemPriceRevisionDate") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemPriceRevisionDate"))))) {
            sql.append(" AND IMTD.ITEM_PRICE_REVISION_DATE <= '").append(String.valueOf(parameters.get("itemPriceRevisionDate"))).append("' ");
        }
        if ((parameters.get("attachedStatus") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("attachedStatus"))))) {
            sql.append(" AND IMTD.ATTACHED_STATUS LIKE '").append(String.valueOf(parameters.get("attachedStatus"))).append("' ");
        }
        if ((parameters.get("attachedDate") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("attachedDate"))))) {
            sql.append(" AND IMTD.ATTACHED_DATE <= '").append(String.valueOf(parameters.get("attachedDate"))).append("' ");
        }
        if ((parameters.get("rebateRevisionDate") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("rebateRevisionDate"))))) {
            sql.append(" AND IMTD.REBATE_REVISION_DATE <= '").append(String.valueOf(parameters.get("rebateRevisionDate"))).append("' ");
        }
        if ((parameters.get("primaryUom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("primaryUom"))))) {
            sql.append(" AND IMTD.PRIMARY_UOM LIKE '").append(String.valueOf(parameters.get("primaryUom"))).append("' ");
        }
        if ((parameters.get("packageSize") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("packageSize"))))) {
            sql.append(" AND IMTD.PACKAGE_SIZE LIKE '").append(String.valueOf(parameters.get("packageSize"))).append("' ");
        }
        if ((parameters.get("source") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("source"))))) {
            sql.append(" AND \"IMTD.SOURCE\" LIKE '").append(String.valueOf(parameters.get("source"))).append("' ");
        }
        if ((parameters.get("suggestedPrice") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("suggestedPrice"))))) {
            sql.append(" AND IMTD.SUGGESTED_PRICE LIKE '").append(String.valueOf(parameters.get("suggestedPrice"))).append("' ");
        }
        if ((parameters.get("ifpStatus") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("ifpStatus"))))) {
            sql.append(" AND IMTD.ATTACHED_STATUS LIKE '").append(String.valueOf(parameters.get("ifpStatus"))).append("' ");
        }
         if ((parameters.get("strength") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("strength"))))) {
            sql.append(" AND  IM.STRENGTH LIKE '").append(String.valueOf(parameters.get("strength"))).append("' ");
        }
          if ((parameters.get("form") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("form"))))) {
            sql.append(" AND IM.FORM LIKE '").append(String.valueOf(parameters.get("form"))).append("' ");
        }
           if ((parameters.get("brand") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("brand"))))) {
            sql.append(" AND IMTD.BRAND_MASTER_SID LIKE '").append(String.valueOf(parameters.get("brand"))).append("' ");
        }
         if ((parameters.get("therapyClass") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("therapyClass"))))) {
            sql.append(" AND IM.THERAPEUTIC_CLASS LIKE '").append(String.valueOf(parameters.get("therapyClass"))).append("' ");
        }
        if ((parameters.get("itemDesc") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemDesc"))))) {
            sql.append(" AND  IM.ITEM_DESC LIKE '").append(String.valueOf(parameters.get("itemDesc"))).append("' ");
        }
        if ((parameters.get("itemsStatus") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemsStatus"))))) {
            sql.append(" AND IMTD.PS_STATUS LIKE '").append(String.valueOf(parameters.get("itemsStatus"))).append("' ");
        }
         if ((parameters.get("createdBy") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("createdBy")))))  {
            sql.append(" AND  IMTD.CREATED_BY LIKE '").append(String.valueOf(parameters.get("createdBy"))).append("' ");
        }
       if ((parameters.get("modifiedBy") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("modifiedBy"))))) {
            sql.append(" AND IMTD.MODIFIED_BY LIKE '").append(String.valueOf(parameters.get("modifiedBy"))).append("' ");
        }
        if ((parameters.get("createdDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("createdDatefrom"))))) {
            sql.append(" AND IMTD.CREATED_DATE  >= '").append(String.valueOf(parameters.get("createdDatefrom"))).append("' ");
        }
         if ((parameters.get("createdDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("createdDateto"))))) {
            sql.append(" AND IMTD.CREATED_DATE  <= '").append(String.valueOf(parameters.get("createdDateto"))).append("' ");
        }
       if ((parameters.get("modifiedDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("modifiedDatefrom"))))) {
            sql.append(" AND IMTD.MODIFIED_DATE  >= '").append(String.valueOf(parameters.get("modifiedDatefrom"))).append("' ");
        }
         if ((parameters.get("modifiedDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("modifiedDateto"))))) {
            sql.append(" AND IMTD.MODIFIED_DATE  <= '").append(String.valueOf(parameters.get("modifiedDateto"))).append("' ");
        }
          if ((parameters.get("startDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("startDatefrom"))))) {
            sql.append(" AND IMTD.START_DATE  >= '").append(String.valueOf(parameters.get("startDatefrom"))).append("' ");
        }
         if ((parameters.get("startDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("startDateto"))))) {
            sql.append(" AND IMTD.START_DATE  <= '").append(String.valueOf(parameters.get("startDateto"))).append("' ");
        }
        if ((parameters.get("endDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("endDatefrom"))))) {
            sql.append(" AND IMTD.END_DATE  >= '").append(String.valueOf(parameters.get("endDatefrom"))).append("' ");
        }
         if ((parameters.get("endDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("endDateto"))))) {
            sql.append(" AND IMTD.END_DATE  <= '").append(String.valueOf(parameters.get("endDateto"))).append("' ");
        }
        if ((parameters.get("attachedDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("attachedDatefrom"))))) {
            sql.append(" AND IMTD.ATTACHED_DATE  >= '").append(String.valueOf(parameters.get("attachedDatefrom"))).append("' ");
        }
         if ((parameters.get("attachedDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("attachedDateto"))))) {
            sql.append(" AND IMTD.ATTACHED_DATE  <= '").append(String.valueOf(parameters.get("attachedDateto"))).append("' ");
        }
        if (!StringUtils.isBlank(record)) {
            if (record.contains("Current")) {
                parameters.put("Current", CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
            if (record.contains("History")) {
                parameters.put("History", CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
            if (record.contains("Future")) {
                parameters.put("Future", CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
        }
        if (parameters.get("Current") != null && parameters.get("History") != null && parameters.get("Future") != null) {
            sql.append(" AND ( '").append(parameters.get("Current")).append("' BETWEEN IMTD.START_DATE AND ISNULL(IMTD.END_DATE,'").append(parameters.get("Current")).append("') ");
            sql.append(" OR ").append(" END_DATE < '").append(parameters.get("History")).append("' ");
            sql.append(" OR ").append(" START_DATE > '").append(parameters.get("Future")).append("' )");
        } else if ((parameters.get("Current") != null && parameters.get("History") != null) || (parameters.get("History") != null && parameters.get("Current") != null)) {
            sql.append(" AND ( '").append(parameters.get("Current")).append("' BETWEEN IMTD.START_DATE AND ISNULL(IMTD.END_DATE,'").append(parameters.get("Current")).append("') OR IMTD.END_DATE < '").append(parameters.get("History")).append("') ");
        } else if ((parameters.get("History") != null && parameters.get("Future") != null) || (parameters.get("Future") != null && parameters.get("History") != null)) {
            sql.append(" AND (").append(" END_DATE < '").append(parameters.get("History")).append("' OR IMTD.START_DATE > '").append(parameters.get("Future")).append("') ");;
        } else if ((parameters.get("Future") != null && parameters.get("Current") != null) || (parameters.get("Current") != null && parameters.get("Future") != null)) {
            sql.append(" AND ( '").append(parameters.get("Current")).append("' BETWEEN IMTD.START_DATE AND ISNULL(IMTD.END_DATE,'").append(parameters.get("Current")).append("') OR IMTD.START_DATE > '").append(parameters.get("Future")).append("') ");
        } else if (parameters.get("Current") != null) {
            sql.append(" AND '").append(parameters.get("Current")).append("' BETWEEN IMTD.START_DATE AND ISNULL(IMTD.END_DATE,'").append(parameters.get("Current")).append("') ");
        } else if (parameters.get("History") != null) {
            sql.append(" AND ").append(" IMTD.END_DATE < '").append(parameters.get("History")).append("' ");
        } else if (parameters.get("Future") != null) {
            sql.append(" AND ").append(" IMTD.START_DATE > '").append(parameters.get("Future")).append("' ");
        }
        if (!isCount) {
            sql.append(" ORDER BY IMTD.ITEM_MASTER_SID OFFSET ").append(start).append(" ROWS FETCH NEXT ").append(end).append(" ROWS ONLY ");
        }
        List returnList = HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());

        LOGGER.info("selected results :" + returnList.size());
        return returnList;
    }


    public List<TempPricingDTO> getCustomizedPricingDTO(List<Object[]> returnList ,final Map<Integer, String> priceProtectionPriceType,boolean isIfpItemsTab,String Record) {
         DecimalFormat PERCENTFORMAT = new DecimalFormat("###0.00");
        TempPricingDTO itemDTO;
        List<TempPricingDTO> resultList = new ArrayList<TempPricingDTO>();
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
         
          if (isIfpItemsTab) {
              itemDTO.setItemsStatus(DashBoardLogic.loadDescription(String.valueOf(val).equals(Constants.NULL) || StringUtils.isEmpty(String.valueOf(val)) ? 0 : Integer.valueOf(String.valueOf(val))));
           if( itemDTO.getItemsStatus().trim().equalsIgnoreCase(Constants.SELECT_ONE)){
              itemDTO.setItemsStatus(StringUtils.EMPTY); 
           }
//            temp = tempifp[++j];
//            itemDTO.setCreatedBy(temp == null || StringUtils.isBlank(temp.toString()) ? StringUtils.EMPTY : userMap.get(Integer.valueOf(String.valueOf(temp))));
//            temp = tempifp[++j];
//            itemDTO.setCreatedDate((Date) temp);
            temp = tempifp[++j];
            itemDTO.setBrand(String.valueOf(temp));
            if (!StringUtils.isBlank(Record)) {
                if (Record.contains("Current")) {
                    itemDTO.setRecordType("Current");
                }
                else if (Record.contains("History")) {
                    itemDTO.setRecordType("History");
                }
                else if (Record.contains("Future")) {
                    itemDTO.setRecordType("Future");
                } else {
                    itemDTO.setRecordType(StringUtils.EMPTY);
                }
            } else {
               itemDTO.setRecordType(StringUtils.EMPTY); 
            }
            itemDTO.setModifiedBy(temp == null || StringUtils.isBlank(temp.toString()) ? StringUtils.EMPTY : userMap.get(Integer.valueOf(String.valueOf(temp))));
            temp = tempifp[++j];
            itemDTO.setModifiedDate((Date) temp);
            temp = tempifp[++j];
            if (temp!= null) {
                itemDTO.setBrand(idHelperDTOMap.get((int) temp) == null||idHelperDTOMap.get((int)temp).getDescription().trim().equals(Constants.SELECT_ONE) ? StringUtils.EMPTY : idHelperDTOMap.get((int) temp).getDescription());
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
            LOGGER.error(ex.getMessage());
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return dto.getDescription();
    }

    public List<Object[]> getLazyItemRebateDeatils(int start, int end, BeanSearchCriteria searchCriteria, boolean isCount, String record, Boolean isRemove) throws PortalException, SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        
        StringBuilder sql = new StringBuilder();
        Map<String, Object> parameters = new HashMap<String, Object>();
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

        if ((parameters.get("itemNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemNo"))))) {
            sql.append(" AND ITEM_NO LIKE '%").append(String.valueOf(parameters.get("itemNo"))).append("%' ");
        }
        if ((parameters.get("itemName") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemName"))))) {
            sql.append(" AND ITEM_NAME LIKE '%").append(String.valueOf(parameters.get("itemName"))).append("%' ");
        }
        if ((parameters.get("formulaId") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("formulaId"))))) {
            sql.append(" AND FORMULA_ID LIKE '%").append(String.valueOf(parameters.get("formulaId"))).append("%' ");
        }
        if ((parameters.get("formulaName") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("formulaName"))))) {
            sql.append(" AND FF.FORMULA_NAME LIKE '%").append(String.valueOf(parameters.get("formulaName"))).append("%' ");
        }
        if ((parameters.get("itemRebateStartDate") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemRebateStartDate"))))) {
            sql.append(" AND ITEM_REBATE_START_DATE >= '").append(String.valueOf(parameters.get("itemRebateStartDate"))).append("' ");
        }
        if ((parameters.get("itemRebateEndDate") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemRebateEndDate"))))) {
            sql.append(" AND ITEM_REBATE_END_DATE <= '").append(String.valueOf(parameters.get("itemRebateEndDate"))).append("' ");
        }
        if ((parameters.get("rebateRevisionDate") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("rebateRevisionDate"))))) {
            sql.append(" AND REBATE_REVISION_DATE <= '").append(String.valueOf(parameters.get("rebateRevisionDate"))).append("' ");
        }
        if ((parameters.get("bundleNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("bundleNo"))))) {
            sql.append(" AND BUNDLE_NO LIKE '%").append(String.valueOf(parameters.get("bundleNo"))).append("%' ");
        }
        if ((parameters.get("itemType") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemType"))))) {
            sql.append(" AND ITEM_TYPE LIKE '%").append(String.valueOf(parameters.get("itemType"))).append("%' ");
        }
        if ((parameters.get("itemId") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemId"))))) {
            sql.append(" AND ITEM_ID LIKE '%").append(String.valueOf(parameters.get("itemId"))).append("%' ");
        }
        if ((parameters.get("formulaNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("formulaNo"))))) {
            sql.append(" AND FF.FORMULA_NO LIKE '%").append(String.valueOf(parameters.get("formulaNo"))).append("%' ");
        }
        if ((parameters.get("netSalesFormulaNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("netSalesFormulaNo"))))) {
            sql.append(" AND NSFM.NET_SALES_FORMULA_NO LIKE '%").append(String.valueOf(parameters.get("netSalesFormulaNo"))).append("%' ");
        }
        if ((parameters.get("netSalesRule") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("netSalesRule"))))) {
            sql.append(" AND NET.RULE_NAME LIKE '%").append(String.valueOf(parameters.get("netSalesRule"))).append("%' ");
        }
        if ((parameters.get("evaluationRule") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("evaluationRule"))))) {
            sql.append(" AND EVA.RULE_NAME LIKE '%").append(String.valueOf(parameters.get("evaluationRule"))).append("%' ");
        }
        if ((parameters.get("evaluationRuleBundle") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("evaluationRuleBundle"))))) {
            sql.append(" AND IMTD.EVALUATION_RULE_BUNDLE LIKE '%").append(String.valueOf(parameters.get("evaluationRuleBundle"))).append("%' ");
        }
        if ((parameters.get("calculationRule") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("calculationRule"))))) {
            sql.append(" AND CAL.RULE_NAME LIKE '%").append(String.valueOf(parameters.get("calculationRule"))).append("%' ");
        }
        if ((parameters.get("calculationRuleBundle") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("calculationRuleBundle"))))) {
            sql.append(" AND IMTD.CALCULATION_RULE_BUNDLE LIKE '%").append(String.valueOf(parameters.get("calculationRuleBundle"))).append("%' ");
        }
        if ((parameters.get("rebatePlanNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("rebatePlanNo"))))) {
            sql.append(" AND RPM.REBATE_PLAN_NO LIKE '%").append(String.valueOf(parameters.get("rebatePlanNo"))).append("%' ");
        }
        if ((parameters.get("deductionCalendarNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("deductionCalendarNo"))))) {
            sql.append(" AND DC.DEDUCTION_CALENDAR_NO LIKE '%").append(String.valueOf(parameters.get("deductionCalendarNo"))).append("%' ");
        }
        if ((parameters.get("calculationRuleBundle") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("calculationRuleBundle"))))) {
            sql.append(" AND IMTD.CALCULATION_RULE_BUNDLE LIKE '%").append(String.valueOf(parameters.get("calculationRuleBundle"))).append("%' ");
        }
        if ((parameters.get("rebatePlanName") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("rebatePlanName"))))) {
            sql.append(" AND RPM.REBATE_PLAN_NAME LIKE '%").append(String.valueOf(parameters.get("rebatePlanName"))).append("%' ");
        }
        if ((parameters.get("attachedStatus") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("attachedStatus"))))) {
            sql.append(" AND IMTD.RS_ATTACHED_STATUS = ").append(String.valueOf(parameters.get("attachedStatus")));
        }
        if ((parameters.get("rebateStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("rebateStartDatefrom"))))) {
            sql.append(" AND IMTD.ITEM_REBATE_START_DATE >='").append(String.valueOf(parameters.get("rebateStartDatefrom"))).append("'");
        }
        if ((parameters.get("rebateStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("rebateStartDateto"))))) {
            sql.append(" AND IMTD.ITEM_REBATE_START_DATE <='").append(String.valueOf(parameters.get("rebateStartDateto"))).append("'");
        }
        if ((parameters.get("rebateEndDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("rebateEndDatefrom"))))) {
            sql.append(" AND IMTD.ITEM_REBATE_END_DATE >='").append(String.valueOf(parameters.get("rebateEndDatefrom"))).append("'");
        }
        if ((parameters.get("rebateEndDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("rebateEndDateto"))))) {
            sql.append(" AND IMTD.ITEM_REBATE_END_DATE <='").append(String.valueOf(parameters.get("rebateEndDateto"))).append("'");
        }
        if ((parameters.get("deductionCalendarName") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("deductionCalendarName"))))) {
            sql.append(" AND DC.DEDUCTION_CALENDAR_NAME LIKE '%").append(String.valueOf(parameters.get("deductionCalendarName"))).append("%' ");
        }

        if (!StringUtils.isBlank(record)) {
            if (record.contains("Current")) {
                parameters.put("Current", CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
            if (record.contains("History")) {
                parameters.put("History", CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
            if (record.contains("Future")) {
                parameters.put("Future", CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
            }
        }
        if (parameters.get("Current") != null && parameters.get("History") != null && parameters.get("Future") != null) {
            sql.append(" AND ( '").append(parameters.get("Current")).append("' BETWEEN START_DATE AND ISNULL(END_DATE,'").append(parameters.get("Current")).append("') ");
            sql.append(" OR ").append(" END_DATE < '").append(parameters.get("History")).append("' ");
            sql.append(" OR ").append(" START_DATE > '").append(parameters.get("Future")).append("' )");
        } else if ((parameters.get("Current") != null && parameters.get("History") != null) || (parameters.get("History") != null && parameters.get("Current") != null)) {
            sql.append(" AND ( '").append(parameters.get("Current")).append("' BETWEEN START_DATE AND ISNULL(END_DATE,'").append(parameters.get("Current")).append("') OR END_DATE < '").append(parameters.get("History")).append("') ");
        } else if ((parameters.get("History") != null && parameters.get("Future") != null) || (parameters.get("Future") != null && parameters.get("History") != null)) {
            sql.append(" AND (").append(" END_DATE < '").append(parameters.get("History")).append("' OR START_DATE > '").append(parameters.get("Future")).append("') ");;
        } else if ((parameters.get("Future") != null && parameters.get("Current") != null) || (parameters.get("Current") != null && parameters.get("Future") != null)) {
            sql.append(" AND ( '").append(parameters.get("Current")).append("' BETWEEN START_DATE AND ISNULL(END_DATE,'").append(parameters.get("Current")).append("') OR START_DATE > '").append(parameters.get("Future")).append("') ");
        } else if (parameters.get("Current") != null) {
            sql.append(" AND '").append(parameters.get("Current")).append("' BETWEEN START_DATE AND ISNULL(END_DATE,'").append(parameters.get("Current")).append("') ");
        } else if (parameters.get("History") != null) {
            sql.append(" AND ").append(" END_DATE < '").append(parameters.get("History")).append("' ");
        } else if (parameters.get("Future") != null) {
            sql.append(" AND ").append(" START_DATE > '").append(parameters.get("Future")).append("' ");
        }
        if (!isCount) {
            sql.append(" ORDER BY ITEM_MASTER_SID ASC  OFFSET " + start + " ROWS FETCH NEXT " + end + " ROWS ONLY");
        }
        List returnList = HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
        LOGGER.info("selected results :" + returnList.size());
        return returnList;
    }

    public List<TempRebateDTO> getCustomizedRebateDTO(List<Object[]> returnList, String recordType) throws SystemException {
        TempRebateDTO itemDTO;
        HelperDTO NULL_HELPER_DTO = new HelperDTO(Constants.SELECT_ONE);
        HashMap<Integer, String> hm = new HashMap<Integer, String>();
        List<TempRebateDTO> itemList = new ArrayList<TempRebateDTO>();
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanMaster.class);
        ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property("rebatePlanMasterSid"));
        projList.add(ProjectionFactoryUtil.property("rebatePlanName"));
        ifpDynamicQuery.setProjection(projList);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.like("rebatePlanName", "%"));
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
            String formula = StringUtils.EMPTY;
            if (tempifp[++j] != null) {
                formula = (String.valueOf(tempifp[j]));
                itemDTO.setFormulaId(formula);
            }
            int formulaId = StringUtils.isBlank(formula) ? 0 : Integer.parseInt(String.valueOf(formula));

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
            itemDTO.setAttachedDate(new Date());
            
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(FormulaDetailsMaster.class);
            List<Integer> formulaIdList = getImtdFormulaDescList(Integer.parseInt(itemDTO.getItemSystemId()), sessionDTO);

            List<String> newList = new ArrayList<String>(formulaIdList.size());
            for (Integer myInt : formulaIdList) {
                newList.add(String.valueOf(myInt));
            }

            if (formulaIdList != null && !formulaIdList.isEmpty()) {
                query.add(RestrictionsFactoryUtil.in("formulaId", newList));
                query.add(RestrictionsFactoryUtil.eq("itemId", itemDTO.getItemId()));
                query.add(RestrictionsFactoryUtil.le("startDate", new Date()));
                query.addOrder(OrderFactoryUtil.desc("startDate"));
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

    public void loadTempIFP(Integer contractSystemId, Integer cfpContractSId, Integer ifpContractSId, Integer psContractSId, Integer rsContractSId) throws SystemException, PortalException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String tempDate = String.valueOf(sessionDTO.getSessionDate());
        final List<Object> input = new ArrayList<Object>(5);
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
            input.add((psContractSId == 0) ? null : psContractSId);
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
    
    public void loadImtdItemPriceRebateDetails(Integer contractSystemId, Integer cfpContractSId, Integer ifpContractSId, Integer psContractSId, Integer rsContractSId) throws SystemException, PortalException {

        final List<Object> input = new ArrayList<Object>();
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
            } else if (psContractSId != 0) {
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
        } else if (ifpContractSId != 0) {
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
            } else if (psContractSId != 0) {
                loadTempIFP(contractSystemId, cfpContractSId, ifpContractSId, psContractSId, rsContractSId);
            }
        }
    }

    public void addToTempIFP(Object searchField, String searchValue) throws SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final int contractSystemId = (Integer) sessionDTO.getContractSystemId();
        final int cfpSystemId = (Integer) sessionDTO.getCfpSystemId();
        final int ifpSystemId = (Integer) sessionDTO.getIfpSystemId();
        final int psSystemId = (Integer) sessionDTO.getPsSystemId();
        final int rsSystemId = (Integer) sessionDTO.getRsSystemId();
        final String tempDate = String.valueOf(sessionDTO.getSessionDate());
        final Map<String, String> map = new HashMap<String, String>();
        map.put(Constants.ITEM_NO2, Constants.ITEM_NO_CAPS);
        map.put(Constants.ITEM_NAME2, "ITEM_NAME");
        map.put(Constants.IFP_NO1, "IFP_NO");
        map.put(Constants.IFP_NAME1, "IFP_NAME");
        map.put("NDC 8", Constants.NDC8);
        map.put("NDC 9", Constants.NDC9);
        map.put("Item Description", Constants.ITEM_DESC1);
        map.put("Form", Constants.FORM);
        map.put("Strength", Constants.STRENGTH);
        map.put("Therapeutic Class", "THERAPEUTIC_CLASS");
        map.put("Brand Name", "BRAND_NAME");
        final SimpleDateFormat tempFromat = new SimpleDateFormat(CommonUtils.MMDDYYYY);
        final List<Object> input = new ArrayList<Object>(16);
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
            } else if ("Brand Name".equalsIgnoreCase(String.valueOf(searchField))) {
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
            } else if ("Brand Name".equalsIgnoreCase(String.valueOf(searchField))) {
                ImtdItemPriceRebateDetailsLocalServiceUtil.loadTempItemdetails(input, "com.contractDashboard.process.addTempRSForBranch");
            } else {
                ImtdItemPriceRebateDetailsLocalServiceUtil.loadTempItemdetails(input, "com.contractDashboard.process.addTempRS");
            }
        }
    }

    public void clearTempIFP() throws SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final List<Object> input = new ArrayList<Object>(5);
        input.add(userId);
        input.add(sessionId);
        ImtdItemPriceRebateDetailsLocalServiceUtil.deleteAll(input, "Back");
        ImtdItemPriceRebateDetailsLocalServiceUtil.deleteTempRsContractTableRecords(0, 0, userId, sessionId);
    }

    public void populateToTempIFP(Object populateField, Object populateValue, Boolean flag) throws SystemException {
        final SimpleDateFormat tempFromat = new SimpleDateFormat(CommonUtils.MMDDYYYY);
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final Map<String, String> map = new HashMap<String, String>();
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
        map.put("CheckBox", "CHECK_RECORD");
        map.put("Suggested Price", "SUGGESTED_PRICE");
        final List<Object> input = new ArrayList<Object>(5);
        input.add(map.get(populateField.toString()));
        input.add(populateValue);
        input.add(userId);
        input.add(tempFromat.format(new Date()));
        input.add(userId);
        input.add(sessionId);
        if (flag) {
            ImtdItemPriceRebateDetailsLocalServiceUtil.massPopulateAll(input, null);
        } else {
            input.add("Check_Record");
            ImtdItemPriceRebateDetailsLocalServiceUtil.massPopulate(input, null);
        }
    }

    public void populateToTempRebate(Object populateField, Object populateValue, Boolean flag) throws SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final SimpleDateFormat tempFromat = new SimpleDateFormat(CommonUtils.MMDDYYYY);
        final Map<String, String> map = new HashMap<String, String>();
        map.put("Rebate Amount", "Rebate_Amount");
        map.put("Start Date", "Item_Rebate_Start_Date");
        map.put("End Date", "Item_Rebate_End_Date");
        map.put("CheckBox", "CHECK_RECORD");
        map.put("Bundle No", "BUNDLE_NO");
        map.put("Rebate Plan Name", "REBATE_PLAN_SYSTEM_ID");
        map.put("RS Status", "RS_ATTACHED_STATUS");
        map.put("Evaluation Rule Bundle","EVALUATION_RULE_BUNDLE");
        map.put("Calculation Rule Bundle","CALCULATION_RULE_BUNDLE");
        final List<Object> input = new ArrayList<Object>(5);
        input.add(map.get(String.valueOf(populateField)));
        input.add(populateValue);
        input.add(userId);
        input.add(tempFromat.format(new Date()));
        input.add(userId);
        input.add(sessionId);
        if (flag) {
            ImtdItemPriceRebateDetailsLocalServiceUtil.massPopulateAll(input, null);
        } else {
            input.add("Check_Record");
            ImtdItemPriceRebateDetailsLocalServiceUtil.massPopulate(input, null);
        }
    }

    public static Boolean saveToTempIFP(List<TempPricingDTO> saveList) throws PortalException, SystemException {
        List<ImtdItemPriceRebateDetails> saveDetailsList = new ArrayList<ImtdItemPriceRebateDetails>();

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
            tempResult.setAttachedDate(new Date());
            saveDetailsList.add(tempResult);
        }
        List<Object> input = new ArrayList<Object>(1);
        input.add(saveDetailsList);
        return ImtdItemPriceRebateDetailsLocalServiceUtil.saveItem(input, Constants.SAVE_DETAILS);
    }

    public static Boolean saveToTempRebate(List<TempRebateDTO> saveList, Boolean rebateResetFlag) throws PortalException, SystemException {
        List<ImtdItemPriceRebateDetails> saveDetailsList = new ArrayList<ImtdItemPriceRebateDetails>();

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
            tempResult.setAttachedDate(new Date());
            saveDetailsList.add(tempResult);
        }
        List<Object> input = new ArrayList<Object>(1);
        input.add(saveDetailsList);
        return ImtdItemPriceRebateDetailsLocalServiceUtil.saveItem(input, Constants.SAVE_DETAILS);
    }

    public void removeAll() throws SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final List<Object> input = new ArrayList<Object>(5);
        input.add(userId);
        input.add(sessionId);
        ImtdItemPriceRebateDetailsLocalServiceUtil.updateAll(input, "Temp");
    }

    public void removeItem(int tempIfpSystemId) throws SystemException, PortalException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final List<Object> input = new ArrayList<Object>(5);
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

    public Boolean itemNullVerification(String field) throws SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final List<Object> input = new ArrayList<Object>(6);
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

    public Boolean priceVerification(String field) throws SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final List<Object> input = new ArrayList<Object>(6);
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
                        "AND ((PRICE IS NULL OR PRICE =0) OR (PRICE_TYPE is null OR PRICE_TYPE = 0 OR PRICE_TYPE ='' ) );" ;
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return (Integer) list.get(0)>0;
    }
    
    public Boolean dateVerification(String indicator) throws SystemException {
        final Integer userId = Integer.parseInt(String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdItemPriceRebateDetails.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.USERS_SID, userId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.SESSION_ID, sessionId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq("checkRecord", true));
        if ("rebate".equals(indicator)) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.geProperty("itemRebateStartDate", "itemRebateEndDate"));
        } else if ("item".equals(indicator)) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.geProperty("endDate", "startDate"));
        } else if ("contract".equals(indicator)) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.geProperty("contractPriceEndDate", "contractPriceStartDate"));
        } else if ("PP".equals(indicator)) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.geProperty("priceProtectionEndDate", "priceProtectionStartDate"));
        }
        ifpDynamicQuery.setProjection(ProjectionFactoryUtil.count("imtdItemPriceRebateSid"));
        List<?> temp = ImtdItemPriceRebateDetailsLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
        int result = Integer.valueOf(temp.get(0).toString());
        return result <= 0;
    }

    public void saveItemDetails(Object searchField, String searchValue) throws SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final int psSystemId = (Integer) sessionDTO.getPsSystemId();
        final int rsSystemId = (Integer) sessionDTO.getRsSystemId();
        final List<Object> input = new ArrayList<Object>(6);
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
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq("checkRecord", true));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne("operation", "D"));
        ifpDynamicQuery.setProjection(ProjectionFactoryUtil.count("imtdItemPriceRebateSid"));
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
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq("checkRecord", true));
        ifpDynamicQuery.setProjection(ProjectionFactoryUtil.count("imtdItemPriceRebateSid"));
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
    public int getLazyBrandNameCount(String filterText) throws PortalException, SystemException {
        filterText = StringUtils.trimToEmpty(filterText) + "%";
        LOGGER.info("Entering getBrandMasterCount method with filterText" + filterText);
        int count = 0;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.ilike("brandName", filterText));
        count = dao.getBrandMasterCount(dynamicQuery);
        LOGGER.info("Ending getBrandMasterCount method : returning count :" + count);
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
    public List<HelperDTO> getLazyBrandName(String filterText, int start, int end) throws PortalException, SystemException {
        LOGGER.info("Entering getBrandMaster method with filterText" + filterText);
        List<HelperDTO> resultList = new ArrayList<HelperDTO>();
        filterText = StringUtils.trimToEmpty(filterText) + "%";
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.ilike("brandName", filterText));
        dynamicQuery.addOrder(OrderFactoryUtil.asc("brandName"));
        dynamicQuery.setLimit(start, end);
        List<BrandMaster> brandList = dao.getBrandMasterList(dynamicQuery);
        HelperDTO dto = new HelperDTO(0, "-Select One-");
        resultList.add(dto);
        if (brandList != null) {
            for (int i = 0; i < brandList.size(); i++) {
                resultList.add(new HelperDTO(brandList.get(i).getBrandMasterSid(), brandList.get(i).getBrandName()));
            }
        }

        LOGGER.info("Ending getBrandMaster method :" + brandList.size());
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
                        Object propertyId = StringUtils.EMPTY;
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            propertyId = less.getPropertyId();
                            parameters.put(less.getPropertyId() + "to", String.valueOf(less.getValue()));
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            propertyId = greater.getPropertyId();
                            parameters.put(greater.getPropertyId() + "from", String.valueOf(greater.getValue()));
                        }
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (operation.EQUAL.toString().equals(operation.name())) {
                        if ("priceTolerance".equals(String.valueOf(stringFilter.getPropertyId())) || "nep".equals(String.valueOf(stringFilter.getPropertyId()))
                                || "maxIncrementalChange".equals(String.valueOf(stringFilter.getPropertyId()))) {
                            double value = Double.valueOf(String.valueOf(stringFilter.getValue()));
                            parameters.put(String.valueOf(stringFilter.getPropertyId()) + "equal", value);
                        }
                    }
                    if (operation.GREATER.toString().equals(operation.name())) {
                        if ("priceTolerance".equals(String.valueOf(stringFilter.getPropertyId())) || "nep".equals(String.valueOf(stringFilter.getPropertyId()))
                                || "maxIncrementalChange".equals(String.valueOf(stringFilter.getPropertyId()))) {
                            double value = Double.valueOf(String.valueOf(stringFilter.getValue()));
                            parameters.put(String.valueOf(stringFilter.getPropertyId()) + "from", value);

                        }
                    }
                    if (operation.LESS.toString().equals(operation.name())) {

                        if ("priceTolerance".equals(String.valueOf(stringFilter.getPropertyId())) || "nep".equals(String.valueOf(stringFilter.getPropertyId()))
                                || "maxIncrementalChange".equals(String.valueOf(stringFilter.getPropertyId()))) {
                            double value = Double.valueOf(String.valueOf(stringFilter.getValue()));
                            parameters.put(String.valueOf(stringFilter.getPropertyId()) + "to", value);

                        }
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
                        Object propertyId = StringUtils.EMPTY;
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            propertyId = less.getPropertyId();
                            parameters.put(less.getPropertyId() + "to", String.valueOf(less.getValue()));
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            propertyId = greater.getPropertyId();
                            parameters.put(greater.getPropertyId() + "from", String.valueOf(greater.getValue()));
                        }
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (operation.EQUAL.toString().equals(operation.name())) {
                        if ("priceTolerance".equals(String.valueOf(stringFilter.getPropertyId())) || "nep".equals(String.valueOf(stringFilter.getPropertyId()))
                                || "maxIncrementalChange".equals(String.valueOf(stringFilter.getPropertyId()))) {
                            double value = Double.valueOf(String.valueOf(stringFilter.getValue()));
                            parameters.put(String.valueOf(stringFilter.getPropertyId()) + "equal", value);
                        }
                    }
                    if (operation.GREATER.toString().equals(operation.name())) {
                        if ("priceTolerance".equals(String.valueOf(stringFilter.getPropertyId())) || "nep".equals(String.valueOf(stringFilter.getPropertyId()))
                                || "maxIncrementalChange".equals(String.valueOf(stringFilter.getPropertyId()))) {
                            double value = Double.valueOf(String.valueOf(stringFilter.getValue()));
                            parameters.put(String.valueOf(stringFilter.getPropertyId()) + "from", value);

                        }
                    }
                    if (operation.LESS.toString().equals(operation.name())) {

                        if ("priceTolerance".equals(String.valueOf(stringFilter.getPropertyId())) || "nep".equals(String.valueOf(stringFilter.getPropertyId()))
                                || "maxIncrementalChange".equals(String.valueOf(stringFilter.getPropertyId()))) {
                            double value = Double.valueOf(String.valueOf(stringFilter.getValue()));
                            parameters.put(String.valueOf(stringFilter.getPropertyId()) + "to", value);

                        }
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

    public List<Object[]> getLazyPriceProtectionDeatils(int start, int end,  Set<Container.Filter> searchCriteria, boolean isCount, String record, final List<SortByColumn> sortByColumns) throws PortalException, SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        StringBuilder sql = new StringBuilder();
        Map<String, Object> parameters = new HashMap<String, Object>();
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
        if ((parameters.get("itemID") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemId"))))) {
            sql.append(" AND IMTD.ITEM_ID LIKE '%").append(String.valueOf(parameters.get("itemId"))).append("%' ");
        }

        if ((parameters.get("itemNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemNo"))))) {
            sql.append(" AND IMTD.ITEM_NO LIKE '%").append(String.valueOf(parameters.get("itemNo"))).append("%' ");
        }
        if ((parameters.get("itemName") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemName"))))) {
            sql.append(" AND IMTD.ITEM_NAME LIKE '%").append(String.valueOf(parameters.get("itemName"))).append("%' ");
        }

        if ((parameters.get("brand") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("brand"))))) {
            sql.append(" AND BM.BRAND_NAME LIKE '%").append(String.valueOf(parameters.get("brand"))).append("%' ");
        }

        if ((parameters.get("priceProtectionStatus") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceProtectionStatus"))))) {
            sql.append(" AND IMTD.PRICE_PROTECTION_STATUS LIKE '").append(String.valueOf(parameters.get("priceProtectionStatus"))).append("' ");
        }

        if ((parameters.get("priceProtectionStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceProtectionStartDatefrom"))))) {
            sql.append(" AND IMTD.PRICE_PROTECTION_START_DATE >= '").append(String.valueOf(dateFormat.format(parameters.get("priceProtectionStartDatefrom")))).append("' ");
        }
        if ((parameters.get("priceProtectionStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceProtectionStartDateto"))))) {
            sql.append(" AND IMTD.PRICE_PROTECTION_START_DATE <= '").append(String.valueOf(dateFormat.format(parameters.get("priceProtectionStartDateto")))).append("' ");
        }

        if ((parameters.get("priceProtectionEndDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceProtectionEndDatefrom"))))) {
            sql.append(" AND IMTD.PRICE_PROTECTION_END_DATE >= '").append(String.valueOf(dateFormat.format(parameters.get("priceProtectionEndDatefrom")))).append("' ");
        }

        if ((parameters.get("priceProtectionEndDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceProtectionEndDateto"))))) {
            sql.append(" AND IMTD.PRICE_PROTECTION_END_DATE <= '").append(String.valueOf(dateFormat.format(parameters.get("priceProtectionEndDateto")))).append("' ");
        }

        if ((parameters.get("priceProtectionPriceType") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceProtectionPriceType"))))) {
            sql.append(" AND IMTD.PRICE_PROTECTION_PRICE_TYPE LIKE '").append(String.valueOf(parameters.get("priceProtectionPriceType"))).append("' ");
        }

        if ((parameters.get("nepfrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("nepfrom"))))) {
            sql.append(" AND IMTD.NEP >=").append(String.valueOf(parameters.get("nepfrom"))).append(" ");
        }

        if ((parameters.get("nepto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("nepto"))))) {
            sql.append(" AND IMTD.NEP <=").append(String.valueOf(parameters.get("nepto"))).append(" ");
        }
        if ((parameters.get("nepequal") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("nepequal"))))) {
            sql.append(" AND IMTD.NEP =").append(String.valueOf(parameters.get("nepequal"))).append(" ");
        }

        if ((parameters.get("nepFormula") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("nepFormula"))))) {
            sql.append(" AND HT_NEP_FORMULA.NET_SALES_FORMULA_NAME LIKE '%").append(String.valueOf(parameters.get("nepFormula"))).append("%' ");
        }

        if ((parameters.get("basePriceType") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("basePriceType"))))) {
            sql.append(" AND IMTD.BASE_PRICE_TYPE LIKE '").append(String.valueOf(parameters.get("basePriceType"))).append("' ");
        }

        if ((parameters.get("netBasePrice") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("netBasePrice"))))) {
            sql.append(" AND IMTD.NET_BASE_PRICE LIKE '").append(String.valueOf(parameters.get("netBasePrice"))).append("' ");
        }

        if ((parameters.get("netBasePriceFormulaName") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("netBasePriceFormulaName"))))) {
            sql.append(" AND HT_NET_BASE_FORMULA.NET_SALES_FORMULA_NAME LIKE '%").append(String.valueOf(parameters.get("netBasePriceFormulaName"))).append("%' ");
        }

        if ((parameters.get("subsequentPeriodPriceType") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("subsequentPeriodPriceType"))))) {
            sql.append(" AND IMTD." + map.get("subsequentPeriodPriceType") + " LIKE '").append(String.valueOf(parameters.get("subsequentPeriodPriceType"))).append("' ");
        }

        if ((parameters.get("netSubsequentPeriodPrice") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("netSubsequentPeriodPrice"))))) {
            sql.append(" AND IMTD." + map.get("netSubsequentPeriodPrice") + " LIKE '").append(String.valueOf(parameters.get("netSubsequentPeriodPrice"))).append("' ");
        }

        if ((parameters.get("netSubsequentPriceFormulaName") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("netSubsequentPriceFormulaName"))))) {
            sql.append(" AND HT_NET_SUB_FORMULA.NET_SALES_FORMULA_NAME LIKE '%").append(String.valueOf(parameters.get("netSubsequentPriceFormulaName"))).append("%' ");
        }

        if ((parameters.get("ppPriceToleranceInterval") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("ppPriceToleranceInterval"))))) {
            sql.append(" AND IMTD.PRICE_TOLERANCE_INTERVAL LIKE '").append(String.valueOf(parameters.get("ppPriceToleranceInterval"))).append("' ");
        }

        if ((parameters.get("priceToleranceFrequency") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceToleranceFrequency"))))) {
            sql.append(" AND IMTD.PRICE_TOLERANCE_FREQUENCY LIKE '").append(String.valueOf(parameters.get("priceToleranceFrequency"))).append("' ");
        }
        if ((parameters.get("priceToleranceType") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceToleranceType"))))) {
            sql.append(" AND IMTD.PRICE_TOLERANCE_TYPE LIKE '").append(String.valueOf(parameters.get("priceToleranceType"))).append("' ");
        }
        if ((parameters.get("priceTolerancefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceTolerancefrom"))))) {
            sql.append(" AND IMTD.PRICE_TOLERANCE >=").append(String.valueOf(parameters.get("priceTolerancefrom"))).append(" ");
        }
        if ((parameters.get("priceToleranceto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceToleranceto"))))) {
            sql.append(" AND IMTD.PRICE_TOLERANCE <=").append(String.valueOf(parameters.get("priceToleranceto"))).append(" ");
        }
        if ((parameters.get("priceToleranceequal") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priceToleranceequal"))))) {
            sql.append(" AND IMTD.PRICE_TOLERANCE =").append(String.valueOf(parameters.get("priceToleranceequal"))).append(" ");
        }
        if ((parameters.get("maxIncrementalChangefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("maxIncrementalChangefrom"))))) {
            sql.append(" AND IMTD.MAX_INCREMENTAL_CHANGE >=").append(String.valueOf(parameters.get("maxIncrementalChangefrom"))).append(" ");
        }
        if ((parameters.get("maxIncrementalChangeto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("maxIncrementalChangeto"))))) {
            sql.append(" AND IMTD.MAX_INCREMENTAL_CHANGE <=").append(String.valueOf(parameters.get("maxIncrementalChangeto"))).append(" ");
        }
         if ((parameters.get("maxIncrementalChangeequal") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("maxIncrementalChangeequal"))))) {
            sql.append(" AND IMTD.MAX_INCREMENTAL_CHANGE =").append(String.valueOf(parameters.get("maxIncrementalChangeequal"))).append(" ");
        }
        if ((parameters.get("resetEligible") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("resetEligible"))))) {
            sql.append(" AND IMTD.RESET_ELIGIBLE LIKE '").append(String.valueOf(parameters.get("resetEligible"))).append("' ");
        }
        if ((parameters.get("resetType") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("resetType"))))) {
            sql.append(" AND IMTD.RESET_TYPE LIKE '").append(String.valueOf(parameters.get("resetType"))).append("' ");
        }
        if ((parameters.get("resetFrequency") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("resetFrequency"))))) {
            sql.append(" AND IMTD.RESET_FREQUENCY LIKE '").append(String.valueOf(parameters.get("resetFrequency"))).append("' ");
        }
        if ((parameters.get("netResetPriceType") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("netResetPriceType"))))) {
            sql.append(" AND IMTD.NET_RESET_PRICE_TYPE LIKE '").append(String.valueOf(parameters.get("netResetPriceType"))).append("' ");
        }
        if ((parameters.get("netResetPriceFormulaName") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("netResetPriceFormulaName"))))) {
            sql.append(" AND HT_NET_RESET_FORMULA.NET_SALES_FORMULA_NAME LIKE '%").append(String.valueOf(parameters.get("netResetPriceFormulaName"))).append("%' ");
        }
        if ((parameters.get("netPriceType") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("netPriceType"))))) {
            sql.append(" AND IMTD.NET_PRICE_TYPE LIKE '").append(String.valueOf(parameters.get("netPriceType"))).append("' ");
        }

        if ((parameters.get("resetDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("resetDatefrom"))))) {
            sql.append(" AND IMTD.RESET_DATE >= '").append(String.valueOf(dateFormat.format(parameters.get("resetDatefrom")))).append("' ");
        }

        if ((parameters.get("resetDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("resetDateto"))))) {
            sql.append(" AND IMTD.RESET_DATE <= '").append(String.valueOf(dateFormat.format(parameters.get("resetDateto")))).append("' ");
        }
        if ((parameters.get("attachedDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("attachedDatefrom"))))) {
            sql.append(" AND IMTD.ATTACHED_DATE >= '").append(String.valueOf(dateFormat.format(parameters.get("attachedDatefrom")))).append("' ");
        }

        if ((parameters.get("attachedDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("attachedDateto"))))) {
            sql.append(" AND IMTD.ATTACHED_DATE <= '").append(String.valueOf(dateFormat.format(parameters.get("attachedDateto")))).append("' ");
        }

if(!StringUtils.isBlank(record)){
            if(record.contains("Current")){
                    parameters.put("Current", CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
                if(record.contains("History")){
                    parameters.put("History", CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
                if(record.contains("Future")){
                    parameters.put("Future", CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
        }
        if (parameters.get("Current") != null && parameters.get("History") != null && parameters.get("Future") != null) {
                      sql.append(" AND ( '").append(parameters.get("Current")).append("' BETWEEN PRICE_PROTECTION_START_DATE AND ISNULL(PRICE_PROTECTION_END_DATE,'").append(parameters.get("Current")).append("') ");
                      sql.append(" OR ").append(" PRICE_PROTECTION_END_DATE < '").append(parameters.get("History")).append("' ");
                      sql.append(" OR ").append(" PRICE_PROTECTION_START_DATE > '").append(parameters.get("Future")).append("' )");
                  } else if ((parameters.get("Current") != null && parameters.get("History") != null) || (parameters.get("History") != null && parameters.get("Current") != null)) {
                      sql.append(" AND ( '").append(parameters.get("Current")).append("' BETWEEN PRICE_PROTECTION_START_DATE AND ISNULL(PRICE_PROTECTION_END_DATE,'").append(parameters.get("Current")).append("') OR PRICE_PROTECTION_END_DATE < '").append(parameters.get("History")).append("') ");
                  } else if ((parameters.get("History") != null && parameters.get("Future") != null) || (parameters.get("Future") != null && parameters.get("History") != null)) {
                      sql.append(" AND (").append(" PRICE_PROTECTION_END_DATE < '").append(parameters.get("History")).append("' OR PRICE_PROTECTION_START_DATE > '").append(parameters.get("Future")).append("') ");;
                  } else if ((parameters.get("Future") != null && parameters.get("Current") != null) || (parameters.get("Current") != null && parameters.get("Future") != null)) {
                      sql.append(" AND ( '").append(parameters.get("Current")).append("' BETWEEN PRICE_PROTECTION_START_DATE AND ISNULL(PRICE_PROTECTION_END_DATE,'").append(parameters.get("Current")).append("') OR PRICE_PROTECTION_START_DATE > '").append(parameters.get("Future")).append("') ");
                  } else if (parameters.get("Current") != null) {
                      sql.append(" AND '").append(parameters.get("Current")).append("' BETWEEN PRICE_PROTECTION_START_DATE AND ISNULL(PRICE_PROTECTION_END_DATE,'").append(parameters.get("Current")).append("') ");
                  } else if (parameters.get("History") != null) {
                      sql.append(" AND ").append(" PRICE_PROTECTION_END_DATE < '").append(parameters.get("History")).append("' ");
                  } else if (parameters.get("Future") != null) {
                      sql.append(" AND ").append(" PRICE_PROTECTION_START_DATE > '").append(parameters.get("Future")).append("' ");
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
                order = order + " ORDER BY " + orderByColumn + ((!sortOrder) ? " ASC " : " DESC ");
            }
            order = order + " " + "OFFSET ";
            order = order + start;
            order = order + " ROWS FETCH NEXT " + end;
            order = order + " ROWS ONLY;";
            sql=sql.append(order);
        }

        List returnList = HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());

        LOGGER.info("selected results :" + returnList.size());
        return returnList;
    }

    public List<TempPricingDTO> getCustomizedPriceProtectionDTO(List<Object[]> returnList, final Map<Integer, HelperDTO> priceProtectionPriceType, final String record) {
        TempPricingDTO itemDTO;
        List<TempPricingDTO> resultList = new ArrayList<TempPricingDTO>();
        Map<Integer, HelperDTO> idHelperDTOMap = helperListUtil.getIdHelperDTOMap();
        for (Object[] tempifp : returnList) {
            itemDTO = new TempPricingDTO();
            itemDTO.setItemID(tempifp[1] != null ? String.valueOf(tempifp[1]) : StringUtils.EMPTY);
            itemDTO.setItemNo(tempifp[2] != null ? String.valueOf(tempifp[2]) : StringUtils.EMPTY);
            itemDTO.setItemName(tempifp[3] != null ? String.valueOf(tempifp[3]) : StringUtils.EMPTY);
            itemDTO.setPriceProtectionStatus(tempifp[4] != null && StringUtils.isNotBlank(String.valueOf(tempifp[4])) ? idHelperDTOMap.get(Integer.valueOf(tempifp[4].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));
            itemDTO.setPriceProtectionStartDate((Date) tempifp[5]);
            itemDTO.setPriceProtectionEndDate((Date) tempifp[6]);
            itemDTO.setPriceProtectionPriceType(tempifp[7] != null  && StringUtils.isNotBlank(String.valueOf(tempifp[7])) ? priceProtectionPriceType.get(Integer.valueOf(tempifp[7].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));
            itemDTO.setNep(tempifp[8] != null ? String.valueOf(tempifp[8]) : StringUtils.EMPTY);

            itemDTO.setNepFormulaId(tempifp[9] != null && StringUtils.isNotBlank(String.valueOf(tempifp[9]))? Integer.valueOf(String.valueOf(tempifp[9])) : 0);
            itemDTO.setBasePrice(tempifp[10] != null ? String.valueOf(tempifp[10]) : StringUtils.EMPTY);
            itemDTO.setPpPriceToleranceInterval(tempifp[11] != null  && StringUtils.isNotBlank(String.valueOf(tempifp[11])) ? helperListUtil.getHelperDTObyID(Integer.valueOf(String.valueOf(tempifp[11]))) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));

            itemDTO.setPpPriceToleranceFrequency(tempifp[12] != null && StringUtils.isNotBlank(tempifp[12].toString()) ? helperListUtil.getHelperDTObyID(Integer.valueOf(String.valueOf(tempifp[12]))) : idHelperDTOMap.get(Integer.parseInt(Constants.ZEROSTRING)));
            itemDTO.setPpPriceToleranceType(tempifp[13] != null && StringUtils.isNotBlank(tempifp[13].toString()) ? helperListUtil.getHelperDTObyID(Integer.valueOf(String.valueOf(tempifp[13]))) : idHelperDTOMap.get(Integer.parseInt(Constants.ZEROSTRING)));
            itemDTO.setPriceTolerance(tempifp[14] != null ? String.valueOf(df.format(tempifp[14])) : StringUtils.EMPTY);
            itemDTO.setMaxIncrementalChange(tempifp[15] != null ? String.valueOf(df.format(tempifp[15])) : StringUtils.EMPTY);
            itemDTO.setResetEligible(tempifp[16] != null  && StringUtils.isNotBlank(String.valueOf(tempifp[16])) ? idHelperDTOMap.get(Integer.valueOf(tempifp[16].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));
            itemDTO.setResetType(tempifp[17] != null ? idHelperDTOMap.get(Integer.valueOf(tempifp[17].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));
            itemDTO.setResetDate((Date) tempifp[18]);
            itemDTO.setResetInterval(tempifp[19] != null  && StringUtils.isNotBlank(String.valueOf(tempifp[19])) ? idHelperDTOMap.get(Integer.valueOf(tempifp[19].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));
            itemDTO.setResetFrequency(tempifp[20] != null  && StringUtils.isNotBlank(String.valueOf(tempifp[20]))? idHelperDTOMap.get(Integer.valueOf(tempifp[20].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));
            itemDTO.setNetPriceType(tempifp[21] != null  && StringUtils.isNotBlank(String.valueOf(tempifp[21])) ? idHelperDTOMap.get(Integer.valueOf(tempifp[21].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));

            itemDTO.setNetPriceTypeFormulaID(tempifp[22] != null ? String.valueOf(tempifp[22]) : StringUtils.EMPTY);
            itemDTO.setAttachedDate((Date) tempifp[23]);
            itemDTO.setBrand(tempifp[25] != null ? String.valueOf(tempifp[25]) : StringUtils.EMPTY);
            itemDTO.setTempItemPriceRebateSystemId(String.valueOf(tempifp[26]));
            itemDTO.setCheckbox(Boolean.valueOf(String.valueOf(tempifp[27])));
            itemDTO.setBasePriceType(tempifp[28] != null  && StringUtils.isNotBlank(String.valueOf(tempifp[28])) ? idHelperDTOMap.get(Integer.valueOf(tempifp[28].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));
            if (itemDTO.getBasePriceType() != null && !itemDTO.getBasePriceType().getDescription().equals(ConstantUtil.SELECT_ONE)) {
                if (itemDTO.getBasePriceType().getDescription().equals("Manual")) {
                    itemDTO.setBasePriceValue(ContractUtils.checkNullValues(tempifp[30]) ? "0.00" : String.valueOf(df.format(tempifp[30])));
                    itemDTO.setBasePriceEntry(ContractUtils.checkNullValues(tempifp[30]) ? "0.00" : String.valueOf(df.format(tempifp[30])));
                } else if (itemDTO.getBasePriceType().getDescription().equals("Date")) {
                    itemDTO.setBasePriceValue((Date) tempifp[31]);
                    itemDTO.setBasePriceDate((Date) tempifp[31]);
                } else if (itemDTO.getBasePriceType().getDescription().equals("Price Type")) {
                    itemDTO.setBasePriceValue(tempifp[29] != null ? priceProtectionPriceType.get(Integer.valueOf(tempifp[29].toString())) : idHelperDTOMap.get(0));
                    itemDTO.setBasePriceItemPriceType(tempifp[29] != null ? priceProtectionPriceType.get(Integer.valueOf(tempifp[29].toString())) : idHelperDTOMap.get(0));
                }
            } else {
                itemDTO.setBasePriceValue(StringUtils.EMPTY);
            }
            itemDTO.setNetBasePrice(tempifp[32] != null  && StringUtils.isNotBlank(String.valueOf(tempifp[32])) ? idHelperDTOMap.get(Integer.valueOf(tempifp[32].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));
            itemDTO.setNetBasePriceFormulaID(tempifp[33] != null && StringUtils.isNotBlank(String.valueOf(tempifp[33])) ? Integer.valueOf(String.valueOf(tempifp[33])) : 0);
            itemDTO.setSubsequentPeriodPriceType(tempifp[34] != null ? priceProtectionPriceType.get(Integer.valueOf(tempifp[34].toString())) : idHelperDTOMap.get(0));
            itemDTO.setNetSubsequentPeriodPrice(tempifp[35] != null && StringUtils.isNotBlank(String.valueOf(tempifp[35]))? idHelperDTOMap.get(Integer.valueOf(tempifp[35].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));
            itemDTO.setNetSubsequentPriceFormulaID(tempifp[36] != null && StringUtils.isNotBlank(String.valueOf(tempifp[36])) ? Integer.valueOf(String.valueOf(tempifp[36])) : 0);
            itemDTO.setNetResetPriceType(tempifp[37] != null && StringUtils.isNotBlank(String.valueOf(tempifp[37])) ? idHelperDTOMap.get(Integer.valueOf(tempifp[37].toString())) : idHelperDTOMap.get(Integer.valueOf(Constants.ZEROSTRING)));
            itemDTO.setNetResetPriceFormulaID(tempifp[38] != null && StringUtils.isNotBlank(String.valueOf(tempifp[38])) ? Integer.valueOf(String.valueOf(tempifp[38])) : 0);
            itemDTO.setNepFormula(getFormulaName(Integer.valueOf(tempifp[9] != null && StringUtils.isNotBlank(String.valueOf(tempifp[9])) ? String.valueOf(tempifp[9]) : Constants.ZEROSTRING)));
            itemDTO.setNetBasePriceFormula(getFormulaName(Integer.valueOf(tempifp[33] != null && StringUtils.isNotBlank(String.valueOf(tempifp[33]))? String.valueOf(tempifp[33]) : Constants.ZEROSTRING)));
            itemDTO.setNetSubsequentPriceFormula(getFormulaName(Integer.valueOf(tempifp[36] != null && StringUtils.isNotBlank(String.valueOf(tempifp[36]))? String.valueOf(tempifp[36]) : Constants.ZEROSTRING)));
            itemDTO.setNetResetPriceFormula(getFormulaName(Integer.valueOf(tempifp[38] != null && StringUtils.isNotBlank(String.valueOf(tempifp[38]))? String.valueOf(tempifp[38]) : Constants.ZEROSTRING)));
            if(!StringUtils.EMPTY.equals(String.valueOf(tempifp[22]))) {
            itemDTO.setNetPriceTypeFormula(getFormulaName(Integer.valueOf(tempifp[22] != null ? String.valueOf(tempifp[22]) : Constants.ZEROSTRING)));
            }
            itemDTO.setResetPriceType(tempifp[39] != null && StringUtils.isNotBlank(String.valueOf(tempifp[39]))? priceProtectionPriceType.get(Integer.valueOf(tempifp[39].toString())) : priceProtectionPriceType.get(Integer.valueOf(Constants.ZEROSTRING)));            
            if (!StringUtils.isBlank(record)) {
                if (record.contains("Current")) {
                    itemDTO.setRecordType("Current");
                }
                else if (record.contains("History")) {
                    itemDTO.setRecordType("History");
                }
                else if (record.contains("Future")) {
                    itemDTO.setRecordType("Future");
                } else {
                    itemDTO.setRecordType(StringUtils.EMPTY);
                }
            }
            resultList.add(itemDTO);
        }
        setPriceProtectionResultList(resultList);
        return resultList;
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

    public List<NepFormulaLookUpDTO> loadNsfResults(
            final ErrorfulFieldGroup searchFields, final int start, final int end, final List<SortByColumn> columns, final Set<Container.Filter> filterSet) throws ParseException {
        List<NepFormulaLookUpDTO> searchList = new ArrayList<>();
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
        criteria.put("nepFormulaType", "NET_SALES_FORMULA_TYPE");
        criteria.put("nepFormulaID", "NET_SALES_FORMULA_ID");
        criteria.put("nepFormulaNo", "NET_SALES_FORMULA_NO");
        criteria.put("nepFormulaName", "NET_SALES_FORMULA_NAME");
        criteria.put("createdDate", "CREATED_DATE");
        criteria.put("modifiedDate", "MODIFIED_DATE");
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
                if ("nepFormulaType".equalsIgnoreCase(fields)) {
                    queryBuilder.append(" AND ").append(criteria.get(fields)).append(" = ").append(((HelperDTO) searchFields.getField("nepFormulaType").getValue()).getId());
                } else {
                    queryBuilder.append(" AND ").append(criteria.get(fields)).append(" LIKE '").append(searchFields.getField(fields).getValue().toString().trim().replace("*", "%")).append("'");
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
                    searchDTO.setNepFormulaType((helperListUtil.getIdHelperDTOMap().get(object[0] != null ? Integer.valueOf(String.valueOf(object[0])) : 0)));
                    searchDTO.setNepFormulaID(!Constants.NULL.equals(String.valueOf(object[1])) && StringUtils.isNotBlank(String.valueOf(object[1])) ? String.valueOf(object[1]) : StringUtils.EMPTY);
                    searchDTO.setNepFormulaNo(!Constants.NULL.equals(String.valueOf(object[2])) && StringUtils.isNotBlank(String.valueOf(object[2])) ? String.valueOf(object[2]) : StringUtils.EMPTY);
                    searchDTO.setNepFormulaName(!Constants.NULL.equals(String.valueOf(object[3])) && StringUtils.isNotBlank(String.valueOf(object[3])) ? String.valueOf(object[3]) : StringUtils.EMPTY);
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
     *
     * @param rsFormulaDTO
     * @param start
     * @param offset
     * @param isCount
     * @return
     */
    public Object loadRSFormula(final PriceProtectionFormulaDTO rsFormulaDTO, final int start, final int offset, final boolean isCount, final Set<Container.Filter> filterSet, final List<SortByColumn> columns) throws ParseException {
        String query;

        if (isCount) {
            query = CustomSQLUtil.get("com.rsModel.getFormulaCountForRS");
        } else {
            query = CustomSQLUtil.get("com.rsModel.getFormulaForRS");
        }

        query = query.replace("@FORMULA_ID", StringUtils.isBlank(rsFormulaDTO.getFormulaID()) || Constants.NULL.equals(rsFormulaDTO.getFormulaID())
                ? ConstantsUtils.PERCENCTAGE : replaceForWildCardSearch(rsFormulaDTO.getFormulaID()));
        query = query.replace("@FORMULA_NO", StringUtils.isBlank(rsFormulaDTO.getFormulaNo()) || Constants.NULL.equals(rsFormulaDTO.getFormulaNo())
                ? ConstantsUtils.PERCENCTAGE : replaceForWildCardSearch(rsFormulaDTO.getFormulaNo()));
        query = query.replace("@FORMULA_NAME", StringUtils.isBlank(rsFormulaDTO.getFormulaName()) || Constants.NULL.equals(rsFormulaDTO.getFormulaName())
                ? ConstantsUtils.PERCENCTAGE : replaceForWildCardSearch(rsFormulaDTO.getFormulaName()));
        query = query.replace("@FORMULA_TYPE", rsFormulaDTO.getFormulaType() == null || (com.stpl.app.util.Constants.SELECT_ONE).equals(rsFormulaDTO.getFormulaType()) ? ConstantsUtils.PERCENCTAGE : (String.valueOf(rsFormulaDTO.getFormulaType().getId())));

        query = getRsFormulaFilterQuery(filterSet, query, isCount);

        if (!isCount) {
            query = getRsFormulaOrderQuery(query, columns, start, offset);
        }

        query = new String(query.toString().replaceAll("WHERE AND", " WHERE "));
        query = new String(query.toString().endsWith("WHERE") ? query.toString().replace("WHERE", " ") : query);

        List resultList = (List) RsModelLocalServiceUtil.executeSelectQuery(query, null, null);
        if (isCount) {
            int count = (Integer) resultList.get(0);
            return count;
        } else {
            return convertFormulaList(resultList);
        }

    }

    private String getRsFormulaFilterQuery(final Set<Container.Filter> filterSet, String string, final boolean isCount) throws ParseException {

        if (rsFormulaDbMap.isEmpty()) {
            loadRsformulaMap();
        }

        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    string += (" AND ") + (rsFormulaDbMap.get(stringFilter.getPropertyId().toString())) + (" LIKE '%") + (stringFilter.getFilterString()) + ("%'");
                } else if (filter instanceof Between) {

                    Between stringFilter = (Between) filter;
                    String startValue = DB_DATE.format(stringFilter.getStartValue());
                    String endValue = DB_DATE.format(stringFilter.getEndValue());

                    if (startValue != null) {
                        string += (" AND ") + (rsFormulaDbMap.get(stringFilter.getPropertyId().toString())) + (" >= '") + (startValue) + ("' ");
                    }
                    if (endValue != null) {
                        string += (" AND ") + (rsFormulaDbMap.get(stringFilter.getPropertyId().toString())) + (" <= '") + (endValue) + ("' ");
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (stringFilter.getValue() instanceof Date) {
                        String filterString = DB_DATE.format(stringFilter.getValue());
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            string += (" AND ") + (rsFormulaDbMap.get(String.valueOf(stringFilter.getPropertyId()))) + (" >= '") + (filterString) + ("' ");
                        } else {
                            string += (" AND ") + (rsFormulaDbMap.get(String.valueOf(stringFilter.getPropertyId()))) + (" <= '") + (filterString) + ("' ");
                        }
                    }
                }
            }
        }

        return string;
    }

    private void loadRsformulaMap() {
        rsFormulaDbMap.clear();
        rsFormulaDbMap.put("formulaType", "FORMULA_TYPE");
        rsFormulaDbMap.put("formulaID", "FORECASTING_FORMULA_SID");
        rsFormulaDbMap.put("formulaNo", "FORMULA_NO");
        rsFormulaDbMap.put("formulaName", "FORMULA_NAME");
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
            string += (" ORDER BY ") + (orderByColumn) + ((!sortOrder) ? " ASC " : " DESC ");
        }

        string += (" OFFSET ") + (startIndex);
        string += (" ROWS FETCH NEXT ") + (endIndex) + (" ROWS ONLY;");

        return string;
    }

    /**
     * Converts the list of objects to list of PriceProtectionFormulaDTO.
     * @param list List<Object[]>
     * @return List<RebatePlanDTO>
     */
    private List<PriceProtectionFormulaDTO> convertFormulaList(final List<Object[]> list) {
        List<PriceProtectionFormulaDTO> resultList = new ArrayList<PriceProtectionFormulaDTO>();
        for (Object[] object : list) {
            HelperDTO dto = new HelperDTO();
            PriceProtectionFormulaDTO rSFormulaDTO = new PriceProtectionFormulaDTO();
            rSFormulaDTO.setFormulaID(String.valueOf(object[0]));
            if (!Constants.NULL.equals(String.valueOf(object[1]))) {
                rSFormulaDTO.setFormulaNo(String.valueOf(object[1]));
            }
            rSFormulaDTO.setFormulaName(String.valueOf(object[2]));
            if ((Integer) (object[3]) != null && (Integer) (object[3]) != 0 && (String) (object[4]) != null) {
                dto.setId((Integer) (object[3]));
                dto.setDescription((String) (object[4]));
                rSFormulaDTO.setFormulaType(dto);
            }
            resultList.add(rSFormulaDTO);
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
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();

        LOGGER.info("In getHelperDetails P1:listType=" + listType);
        list = dao.getHelperTableDetailsByListName(listType);
        if (list != null) {
            helperList.add(new HelperDTO(0, com.stpl.app.util.Constants.SELECT_ONE));
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
        query = query.replace("@FORMULA_TYPE", rsFormulaDTO.getFormulaType() == null || (com.stpl.app.util.Constants.SELECT_ONE).equals(rsFormulaDTO.getFormulaType()) ? ConstantsUtils.PERCENCTAGE : (String.valueOf(rsFormulaDTO.getFormulaType().getId())));
        query = query.replace("@FORMULA_ID", StringUtils.isBlank(rsFormulaDTO.getFormulaID()) || Constants.NULL.equals(rsFormulaDTO.getFormulaID())
                ? ConstantsUtils.PERCENCTAGE : (rsFormulaDTO.getFormulaID()));
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

    public static Boolean saveToTempTable(List<TempPricingDTO> saveList) throws PortalException, SystemException {
        List<ImtdItemPriceRebateDetails> saveDetailsList = new ArrayList<ImtdItemPriceRebateDetails>();

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
            tempResult.setAttachedDate(new Date());
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
        List<Object> input = new ArrayList<Object>(1);
        input.add(saveDetailsList);
        return ImtdItemPriceRebateDetailsLocalServiceUtil.saveItem(input, Constants.SAVE_DETAILS);
    }

    public void populateToTempIFPforPP(Object populateField, Object populateValue, Boolean flag) throws SystemException {
        LOGGER.info("PopulateField--> " + populateField + " PopulateValue-->> " + populateValue);
        final SimpleDateFormat tempFromat = new SimpleDateFormat(CommonUtils.MMDDYYYY);
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final Map<String, String> map = new HashMap<String, String>();
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
        map.put("CheckBox", "CHECK_RECORD");
        final List<Object> input = new ArrayList<Object>(5);
        input.add(map.get(populateField.toString()));
        input.add(populateValue);
        input.add(userId);
        input.add(tempFromat.format(new Date()));
        input.add(userId);
        input.add(sessionId);
        if (flag) {
            ImtdItemPriceRebateDetailsLocalServiceUtil.massPopulateAll(input, null);
        } else {
            input.add("Check_Record");
            ImtdItemPriceRebateDetailsLocalServiceUtil.massPopulate(input, null);
        }
    }

    public Map<Integer, HelperDTO> loadPriceProtection() {
        final List<HelperDTO> list = new ArrayList<HelperDTO>();
        Map<Integer, HelperDTO> priceProtectionPriceType = new HashMap<Integer, HelperDTO>();
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class);
        DashboardDAO dao = new DashboardLogicDAOImpl();
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_PRICING_QUALIFIER_SID));
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.ITEM_PRICING_QUAL_NAME, StringUtils.EMPTY)));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectionList));
        List<Object[]> returnList = new ArrayList<Object[]>();
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
            queryName="com.rsModel.massPopulateContractRebate";
            if(isPopulateAll)
            queryName="com.rsModel.massPopulateContractRebate populate All";
            sql = CustomSQLUtil.get(queryName);
            sql = sql.replace("@USERS_SID", userId);
            sql = sql.replace("@SESSION_ID", sessionId);
            sql = sql.replace("@REBATE_PLAN_MASTER_SID", map.get("rebatePlanSystemId"));
            sql = sql.replace("@RS_DETAILS_REBATE_PLAN_NAME", map.get("rebatePlanName"));
            if(!isPopulateAll){
            sql = sql.replace("CHECK_RECORD", "CHECK_RECORD");
            }
            
        } else {
             queryName="com.rsModel.massPopulateFormulaForContract";
            if(isPopulateAll)
            queryName="com.rsModel.massPopulateFormulaForContract populate All";
            sql = CustomSQLUtil.get(queryName);
            sql = sql.replace("@USERS_SID", userId);
            sql = sql.replace("@SESSION_ID", sessionId);
            sql = sql.replace("@RS_DETAILS_FORMULA_NO", map.get("formulaNo"));
            sql = sql.replace("@RS_DETAILS_FORMULA_NAME", map.get("formulaName"));
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
    public void massPopulateDeductionLookUp(String populateField, Map<String, String> map, boolean populate) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        String sql;
        sql = CustomSQLUtil.get("com.rsModel.massPopulateDeductionForContract");
        sql = sql.replace("@USERS_SID", userId);
        sql = sql.replace("@SESSION_ID", sessionId);
        sql = sql.replace("@DEDUCTION_CALENDAR_MASTER_SID", map.get("deductionSystemId"));
        sql = sql.replace("@RS_DETAILS_DEDUCTION_CALENDAR_NO", map.get(Constants.DEDUCTION_CALENDAR_NO));
        sql = sql.replace("@RS_DETAILS_DEDUCTION_CALENDAR_NAME", map.get(Constants.DEDUCTION_CALENDAR_NAME));
        if (populate) {
            sql = sql + " AND CHECK_RECORD = 1;";
        }
        RsModelLocalServiceUtil.executeUpdateQuery(sql, null, null);
    }

    /**
     * Mass Update for Formula and Rebate Plan
     *
     * @param populateField
     * @param data
     */
    public void massPopulateNetSalesLookUp(String populateField, Map<String, String> map, boolean populate) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        String sql;
        sql = CustomSQLUtil.get("com.rsModel.massPopulateNetSalesForContract");
        sql = sql.replace("@USERS_SID", userId);
        sql = sql.replace("@SESSION_ID", sessionId);
        sql = sql.replace("@NET_SALES_FORMULA_MASTER_SID", map.get("systemID"));
        sql = sql.replace("@RS_DETAILS_NET_SALES_FORMULA_NO", map.get("netSalesFormulaNo"));
        sql = sql.replace("@RS_DETAILS_NET_SALES_FORMULA_NAME", map.get("netSalesFormulaName"));
        if (populate) {
            sql = sql + " AND CHECK_RECORD = 1;";
        }
        RsModelLocalServiceUtil.executeUpdateQuery(sql, null, null);
    }

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
            sql = sql.replace("@USERS_SID", userId);
            sql = sql.replace("@SESSION_ID", sessionId);
            sql = sql.replace("@NET_SALES_RULE", map.get("systemID"));
        } else if (ContractUtils.CALCULATION_RULE.equals(populateField)) {
            sql = CustomSQLUtil.get("com.rsModel.massPopulateCalculationRuleForContract");
            sql = sql.replace("@USERS_SID", userId);
            sql = sql.replace("@SESSION_ID", sessionId);
            sql = sql.replace("@CALCULATION_RULE", map.get("systemID"));
        } else if (ContractUtils.EVALUATION_RULE.equals(populateField)) {
            sql = CustomSQLUtil.get("com.rsModel.massPopulateEvaluationRuleForContract");
            sql = sql.replace("@USERS_SID", userId);
            sql = sql.replace("@SESSION_ID", sessionId);
            sql = sql.replace("@EVALUATION_RULE", map.get("systemID"));
        }
        if (populate) {
            sql = sql + " AND CHECK_RECORD = 1;";
        }
        RsModelLocalServiceUtil.executeUpdateQuery(sql, null, null);
    }

    public String getIntegerForSelection(String value) {
        List<Object> list = new ArrayList<>();
        String sqlQuery = "SELECT ITEM_PRICING_QUALIFIER_SID FROM ITEM_PRICING_QUALIFIER WHERE ITEM_PRICING_QUALIFIER_NAME LIKE '" + value + "'";
        list = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
        return list.get(0).toString();
    }

    private void loadSortMap(Map<String, String> map) {
        map.put("itemID", "IMTD.ITEM_ID");
        map.put("itemNo", "IMTD.ITEM_NO");
        map.put("itemName", "IMTD.ITEM_NAME");
        map.put("brand", "BM.BRAND_NAME");
        map.put("priceProtectionStatus", "IMTD.PRICE_PROTECTION_STATUS");
        map.put("priceProtectionStartDate", "IMTD.PRICE_PROTECTION_START_DATE");
        map.put("priceProtectionEndDate", "IMTD.PRICE_PROTECTION_END_DATE");
        map.put("priceProtectionPriceType", "IMTD.PRICE_PROTECTION_PRICE_TYPE");
        map.put("nep", "IMTD.NEP");
        map.put("nepFormula", "HT_NEP_FORMULA.NET_SALES_FORMULA_NAME");
        map.put("basePriceType", "IMTD.BASE_PRICE_TYPE");
        map.put("basePriceValue", "IMTD.BASE_PRICE_ENTRY");
        map.put("netBasePrice", "IMTD.NET_BASE_PRICE");
        map.put("netBasePriceFormulaID", "HT_NET_BASE_FORMULA.NET_SALES_FORMULA_NAME");
        map.put("subsequentPeriodPriceType", "IMTD.SUBSEQUENT_PERIOD_PRICE_TYPE");
        map.put("netSubsequentPriceFormulaID", "HT_NET_SUB_FORMULA.NET_SALES_FORMULA_NAME");
        map.put("priceToleranceInterval", "IMTD.PRICE_TOLERANCE_INTERVAL");
        map.put("priceToleranceFrequency", "IMTD.PRICE_TOLERANCE_FREQUENCY");
        map.put("priceToleranceType", "IMTD.PRICE_TOLERANCE_TYPE");
        map.put("priceTolerance", "IMTD.PRICE_TOLERANCE");
        map.put("maxIncrementalChange", "IMTD.MAX_INCREMENTAL_CHANGE");
        map.put("resetEligible", "IMTD.RESET_ELIGIBLE");
        map.put("resetType", "IMTD.RESET_TYPE");
        map.put("resetDate", "IMTD.RESET_DATE");
        map.put("resetInterval", "IMTD.RESET_INTERVAL");
        map.put("resetFrequency", "IMTD.RESET_FREQUENCY");
        map.put("resetPriceType", "IMTD.RESET_PRICE_TYPE");
        map.put("netResetPriceType", "IMTD.NET_RESET_PRICE_TYPE");
        map.put("netResetPriceFormulaID", "HT_NET_RESET_FORMULA.NET_SALES_FORMULA_NAME");
        map.put("netPriceType", "IMTD.NET_PRICE_TYPE");
        map.put("netPriceTypeFormula", "HT_NET_PRICE_FORMULA.NET_SALES_FORMULA_NAME");
        map.put("attachedDate", "IMTD.ATTACHED_DATE");
    }
    
    /**
     * Validation to check the record is checked or not
     * 
     * @param tableName
     * @param sessionId
     * @return 
     */
    public static boolean isCheckedValidation(final String tableName,final String sessionId) {
        LOGGER.info("TableName-->"+tableName+" SessionId-->> "+sessionId);
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
        List <Object> list=new ArrayList<>();
        String sql=StringUtils.EMPTY;
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
            temp.setItemFamilyplanName(item.getIfpName());
            temp.setItemFamilyplanNo(item.getIfpNo());
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
            Map<String, Object> parameters = new HashMap<>();
            if (searchItemForm.getField("itemFamilyplanId").getValue() == null) {
                ifpId = StringUtils.EMPTY;
            } else {
                ifpId = searchItemForm.getField("itemFamilyplanId").getValue()
                        .toString().trim();
            }
           
            if (searchItemForm.getField("itemFamilyplanNo").getValue() == null) {

                ifpNo = StringUtils.EMPTY;
            } else {


                ifpNo = searchItemForm.getField("itemFamilyplanNo").getValue()

                        .toString().trim();
            }

           
            if (searchItemForm.getField("itemFamilyplanName").getValue() == null) {

                ifpName = StringUtils.EMPTY;
            } else {

                ifpName = searchItemForm.getField("itemFamilyplanName").getValue()

                        .toString().trim();
            }

           
            if (searchItemForm.getField("itemFamilyplanType").getValue() == null) {

                ifpType = 0;
            } else {
                ifpType = ((com.stpl.ifs.util.HelperDTO)searchItemForm.getField("itemFamilyplanType").getValue()).getId();
            }

            
            if (searchItemForm.getField("itemFamilyplanStatus").getValue() == null) {

                ifpStatus = 0;
            } else {
                ifpStatus = ((com.stpl.ifs.util.HelperDTO)searchItemForm.getField("itemFamilyplanStatus").getValue()).getId();
            }
            if (searchItemForm.getField("itemId")!=null && searchItemForm.getField("itemId").getValue()!=null) {
            
            itemId = searchItemForm.getField("itemId").getValue()
                    .toString().trim();
            
        } else {
            itemId = StringUtils.EMPTY;
        }

        if (searchItemForm.getField("itemNo")!=null && searchItemForm.getField("itemNo").getValue()!=null) {
            
            itemNo = searchItemForm.getField("itemNo").getValue()
                    .toString().trim();
            
        } else {
            itemNo = StringUtils.EMPTY;
            
        }

        if (searchItemForm.getField("itemName")!=null && searchItemForm.getField("itemName").getValue()!=null) {
            
            itemName = searchItemForm.getField("itemName").getValue()
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
                List<HelperDTO> list = getDropDownList("IFP_TYPE");
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
                       if ("itemFamilyplanSystemId".equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
                           String value = String.valueOf(compare.getValue());
                           if (operation.GREATER.toString().equals(operation.name())) {
                               parameters.put(compare.getPropertyId() + "from", value);
                           } else if (operation.LESS.toString().equals(operation.name())) {
                               parameters.put(compare.getPropertyId() + "to", value);
                           } else if (operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
                               parameters.put(compare.getPropertyId() + "equal", value);
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
                        Object propertyId = StringUtils.EMPTY;
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            propertyId = less.getPropertyId();
                            parameters.put(less.getPropertyId() + "to", String.valueOf(less.getValue()));
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            propertyId = greater.getPropertyId();
                            parameters.put(greater.getPropertyId() + "from", String.valueOf(greater.getValue()));
                        }
                    }
                }
               }
           }
            List resultList =ImtdIfpDetailsLocalServiceUtil.getIFPSearchList(ifpId, ifpNo, ifpName, ifpType, ifpStatus,itemId,  itemNo,  itemName,  0,  0,  null, null,true, parameters);
           
            
            return resultList.size();
       }catch(Exception e)
       {
          LOGGER.error(e.getMessage());
            return 0;
        }
    }
       public List<HelperDTO> getDropDownList(final String listName) throws SystemException, PortalException {
        LOGGER.info("getDropDownList p1: " + listName);

        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();

        final List<HelperTable> list = dao.getHelperTableDetailsByListName(listName);
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
        public int getTotalCount() throws SystemException, PortalException {
        LOGGER.info("getTotalCount --method ");
        List list=new ArrayList();
        list=HelperTableLocalServiceUtil.executeSelectQuery("Select count(*) from IFP_MODEL");
        return (int)(list.get(0));
    }

     @SuppressWarnings("unchecked")
    public List<TempPricingDTO> lookupSearchIFP(
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
      
        if (searchItemForm.getField("itemFamilyplanId").getValue() == null) {
            ifpId = StringUtils.EMPTY;
        } else {
            ifpId = searchItemForm.getField("itemFamilyplanId").getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField("itemFamilyplanNo").getValue() == null) {
            ifpNo = StringUtils.EMPTY;
        } else {

            ifpNo = searchItemForm.getField("itemFamilyplanNo").getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField("itemFamilyplanName").getValue() == null) {
            ifpName = StringUtils.EMPTY;
        } else {
            ifpName = searchItemForm.getField("itemFamilyplanName").getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField("itemFamilyplanType").getValue() == null) {
            ifpType = 0;
        } else {
            ifpType = ((com.stpl.ifs.util.HelperDTO)searchItemForm.getField("itemFamilyplanType").getValue()).getId();
        }

        if (searchItemForm.getField("itemFamilyplanStatus").getValue() == null) {
            ifpStatus = 0;
        } else {
            ifpStatus = ((com.stpl.ifs.util.HelperDTO)searchItemForm.getField("itemFamilyplanStatus").getValue()).getId();
        }
        
        if (searchItemForm.getField("itemId")!=null && searchItemForm.getField("itemId").getValue()!=null) {
            
            itemId = searchItemForm.getField("itemId").getValue()
                    .toString().trim();
            
        } else {
            itemId = StringUtils.EMPTY;
        }

        if (searchItemForm.getField("itemNo")!=null && searchItemForm.getField("itemNo").getValue()!=null) {
            
            itemNo = searchItemForm.getField("itemNo").getValue()
                    .toString().trim();
            
        } else {
            itemNo = StringUtils.EMPTY;
            
        }

        if (searchItemForm.getField("itemName")!=null && searchItemForm.getField("itemName").getValue()!=null) {
            
            itemName = searchItemForm.getField("itemName").getValue()
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
            List<HelperDTO> list = getDropDownList("IFP_TYPE");
            for (HelperDTO list1 : list) {
                if (list1.getId()==ifpType){
                    int sysId = list1.getId();
                }
            }

        }
            if (ifpStatus!=0) {
            List<HelperDTO> list = getDropDownList("STATUS");
            for (HelperDTO list1 : list) {
                if (list1.getId()==ifpStatus){
                    int sysId = list1.getId();
                }
            }

        }
            
        String orderby =Constants.ASC;
        String column = "IFP_MODEL_SID";
        for (final Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator
                .hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
            String columnName = orderByColumn.getName();
            if ("itemFamilyplanSystemId".equals(columnName)) {
                column = "IFP_MODEL_SID";
            } else if ("itemFamilyplanId".equals(columnName)) {
                column = "IFP_ID";
            } else if ("itemFamilyplanpNo".equals(columnName)) {
                column = "IFP_NO";
            } else if ("itemFamilyplanName".equals(columnName)) {
                column = "IFP_NAME";
            } else if ("itemFamilyplanType".equals(columnName)) {
                column = "IFP_TYPE";
            } else if ("itemFamilyplanStatus".equals(columnName)) {
                column = "IFP_STATUS";
            } else if ("itemFamilyplanCategory".equals(columnName)) {
                column = "IFP_CATEGORY";
            } else if ("startDate".equals(columnName)) {
                column = "IFP_START_DATE";
            } else if ("endDate".equals(columnName)) {
                column = "IFP_END_DATE";
            } else if ("itemFamilyplanDesignation".equals(columnName)) {
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
                    if ("itemFamilyplanSystemId".equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
                        String value = String.valueOf(compare.getValue());
                        if (operation.GREATER.toString().equalsIgnoreCase(operation.name())) {
                            parameters.put(compare.getPropertyId() + "from", value);
                        } else if (operation.LESS.toString().equalsIgnoreCase(operation.name())) {
                            parameters.put(compare.getPropertyId() + "to", value);
                        } else if (operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
                            parameters.put(compare.getPropertyId() + "equal", value);
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
                        Object propertyId = StringUtils.EMPTY;
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            propertyId = less.getPropertyId();
                            parameters.put(less.getPropertyId() + "to", String.valueOf(less.getValue()));
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            propertyId = greater.getPropertyId();
                            parameters.put(greater.getPropertyId() + "from", String.valueOf(greater.getValue()));
                        }
                    }
                }
            }
        }
        List result = ImtdIfpDetailsLocalServiceUtil.getIFPSearchList(ifpId, ifpNo, ifpName, ifpType, ifpStatus,itemId,  itemNo,  itemName,  start,  end,  column, orderby,false, parameters);    
        
        
        LOGGER.info("return ifp list ,size = " + ((result==null)?result:result.size()));
        return getLookUpCustomizedObjectModel(result);
    }
     public static List<TempPricingDTO> getLookUpCustomizedObjectModel(final List<Object[]> ifplist) throws PortalException, SystemException {
        final List<TempPricingDTO> ifpDTOlist = new ArrayList<TempPricingDTO>();
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
            if (obj[2] != null) {
                ifpDTO.setItemFamilyplanNo(String.valueOf(obj[2]));
            }
            if (obj[3] != null) {
                ifpDTO.setItemFamilyplanName(String.valueOf(obj[3]));
            }
            if (obj[4] != null && StringUtils.isNotBlank(obj[4].toString()) && (Integer) obj[4] != 0) {
                ifpDTO.setIfpType(hm.get(obj[4]));
            }
            if (obj[5] != null && StringUtils.isNotBlank(obj[5].toString()) && (Integer) obj[5] != 0) {
                ifpDTO.setIfPStatus(hm.get(obj[5]));
            }
            if (obj[6] != null && StringUtils.isNotBlank(obj[6].toString()) && (Integer) obj[6] != 0) {
                ifpDTO.setIfpCategory(hm.get(obj[6]));
            }
            if (obj[7] != null) {
                ifpDTO.setStartDate((Date) obj[7]);
            }
            if (obj[8] != null) {
                ifpDTO.setEndDate((Date) obj[8]);
            }
            if (obj[9] != null && StringUtils.isNotBlank(obj[9].toString()) && (Integer) obj[9] != 0) {
                ifpDTO.setIfpDesignation(hm.get(obj[9]));
            }
            if (obj[10] != null) {
                ifpDTO.setParentItemFamilyplanId(String.valueOf(obj[10]));
            }
            if (obj[11] != null) {
                ifpDTO.setParentItemFamilyplanName(String.valueOf(obj[11]));
            }
            if (obj[16] != null) {
                ifpDTO.setCreatedBy(userMap.get(Integer.valueOf(String.valueOf(obj[16]))));
            }
            if (obj[17] != null) {
                ifpDTO.setCreatedDate((Date) obj[17]);
            }

            ifpDTOlist.add(ifpDTO);
        }
        LOGGER.info("return customized item size " + ifpDTOlist.size());

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
         } catch (Exception ex) { ex.printStackTrace();
             LOGGER.error(ex);
        }
      return StringUtils.EMPTY;
    }
      public void saveIFP(final CustomFieldGroup ifpForm, int systemId) throws SystemException, PortalException, Exception {
        try {
            LOGGER.info("Entering update operation");
            IfpContract item = IfpContractLocalServiceUtil.getIfpContract(systemId);

            item.setIfpCategory(ifpForm.getField("itemFamilyplanCategory").getValue() != null ? ((com.stpl.ifs.util.HelperDTO) ifpForm.getField("itemFamilyplanCategory").getValue()).getId() : 0);
            if (ifpForm.getField("itemFamilyplanType").getValue() != null) {
                item.setIfpType(((com.stpl.ifs.util.HelperDTO) ifpForm.getField("itemFamilyplanType").getValue()).getId());
            } else {
                item.setIfpType(0);
            }
            item.setIfpDesignation(ifpForm.getField("itemFamilyplanDesignation").getValue() != null ? String.valueOf(((com.stpl.ifs.util.HelperDTO) ifpForm.getField("itemFamilyplanDesignation").getValue()).getId()) : StringUtils.EMPTY);
            item.setCreatedBy(Integer.parseInt(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID))));
            item.setModifiedDate(new Date());

            item.setParentIfpId(String.valueOf(ifpForm.getField(
                    "parentItemFamilyplanId").getValue()));
            item.setParentIfpName(String.valueOf(ifpForm.getField(
                    "parentItemFamilyplanName").getValue()).trim());
            item.setIfpStatus(((com.stpl.ifs.util.HelperDTO) ifpForm.getField(ConstantsUtils.IFP_STATUS).getValue()).getId());
            item.setIfpStartDate((Date) ifpForm.getField(
                    "startDate").getValue());
                item.setIfpEndDate((Date) ifpForm.getField(
                        "endDate").getValue());
            item.setModifiedDate(new Date());
            item.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
            IfpContractLocalServiceUtil.updateIfpContract(item);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
          
    public   List<HelperDTO> getBrandDropDown() throws SystemException, PortalException {

        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        DynamicQuery dynamicQuery  = DynamicQueryFactoryUtil
                        .forClass(BrandMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.isNotNull("brandName"));
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
            LOGGER.info("(int)list.get(0) = " + (int)list.get(0));
            return list==null?0:(int)list.get(0);
        }
    
    }
     public  int isEmpty(SessionDTO sessDto,boolean isItem)
     {
         
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
    public List<Object[]> getCheckRecordOnTabChange(int start, int end, BeanSearchCriteria searchCriteria, boolean isCount, String record){
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        String sql = "SELECT CHECK_RECORD FROM IMTD_ITEM_PRICE_REBATE_DETAILS WHERE SESSION_ID = "+sessionId
                +" USER_ID = "+userId;
        List<Object[]> returnList=HelperTableLocalServiceUtil.executeSelectQuery(sql);
        return returnList;
    }
    public List<TempPricingDTO> getCustomizedPriceOnTabChange(List<Object[]> returnList){
        TempPricingDTO itemDTO;
        List<TempPricingDTO> resultList = new ArrayList<TempPricingDTO>();
        for (Object[] tempifp : returnList) {
            itemDTO = new TempPricingDTO();
            itemDTO.setCheckbox((Boolean) tempifp[0]);
            resultList.add(itemDTO);
        }
        return resultList;
    }
    public static void savePricingCheck(List<TempPricingDTO> saveList) throws PortalException, SystemException  {
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
    public static boolean  emptyCheck(SessionDTO dto,boolean isRebate){
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        String tabName="check_record";
         String sql="Select count(*) from dbo.IMTD_ITEM_PRICE_REBATE_DETAILS where USERS_SID like '"+userId+"' and SESSION_ID like '"+dto.getUiSessionId()+"' and "+tabName+"=1";
        List list=HelperTableLocalServiceUtil.executeSelectQuery(sql);
         return list==null||(int)list.get(0)<=0 ?true:false;
        
    }
    public static void  updateTempCheck(SessionDTO dto,boolean isRebate,String id,boolean value){
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
        } catch (Exception ex) { ex.printStackTrace();
           LOGGER.error(ex);
        }
        if (returnList.isEmpty()) {
            return ConstantsUtils.SELECT_ONE;
        } else {
            return String.valueOf(returnList.get(0));
        }

    }
                }
