/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.itemgroup.logic;

import com.stpl.app.adminconsole.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.dao.ItemGroupLogicDAO;
import com.stpl.app.adminconsole.dao.impl.ItemGroupLogicDAOImpl;
import com.stpl.app.adminconsole.itemgroup.dto.ItemDetailsDTO;
import com.stpl.app.adminconsole.itemgroup.dto.ItemGroupDTO;
import com.stpl.app.adminconsole.itemgroup.util.CommonUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.HistItemGroup;
import com.stpl.app.model.HistItemGroupDetails;
import com.stpl.app.model.ItemGroup;
import com.stpl.app.model.ItemGroupDetails;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.model.Udcs;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.HistItemGroupLocalServiceUtil;
import com.stpl.app.service.ItemGroupDetailsLocalServiceUtil;
import com.stpl.app.service.ItemGroupLocalServiceUtil;
import com.stpl.app.service.UdcsLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantUtil;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
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
 * The Class ItemGroupLogic.
 *
 * @author vishalakshi
 */
public class ItemGroupLogic {

    private static final Logger LOGGER = Logger.getLogger(ItemGroupLogic.class);

    private ItemGroupLogicDAO dao = new ItemGroupLogicDAOImpl();

    static HashMap<String, String> columnNames = new HashMap<String, String>();
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    public ItemGroupLogicDAO getDao() {
        return dao;
    }

    public void setDao(final ItemGroupLogicDAO dao) {
        this.dao = dao;
    }

    SessionDTO sessionDTO;

    public ItemGroupLogic(SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
    }

    public ItemGroupLogic() {

    }

    /**
     * Gets the search results.
     *
     * @param itemGroupForm the item group form
     * @return the search results
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
     * @throws java.lang.Exception
     */
    public Object getSearchResults(final ErrorfulFieldGroup itemGroupForm, final String version, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) throws SystemException, PortalException {
        LOGGER.info("getSearchResults started with P1:CustomFieldGroup itemGroupForm");

        DynamicQuery itemgroupDynamicQuery = null;
        if (version.equalsIgnoreCase(ConstantsUtils.VERSION_CURRENT)) {
            itemgroupDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemGroup.class);
        } else if (version.equalsIgnoreCase(ConstantsUtils.VERSION_HIST)) {
            itemgroupDynamicQuery = DynamicQueryFactoryUtil.forClass(HistItemGroup.class);
        }

        if (itemGroupForm.getField("text1").getValue() != null && StringUtils.isNotEmpty(itemGroupForm.getField("text1").getValue().toString())) {
            final String itemGroupName = itemGroupForm.getField("text1").getValue().toString();
            final String itemGroupName1 = itemGroupName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_GROUP_NAME, itemGroupName1));
        }
        if (itemGroupForm.getField("text2").getValue() != null && StringUtils.isNotEmpty(itemGroupForm.getField("text2").getValue().toString())) {
            final String itemGroupNo = itemGroupForm.getField("text2").getValue().toString();
            final String itemGroupNo1 = itemGroupNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_GROUP_NO, itemGroupNo1));
        }
        if (itemGroupForm.getField("text3").getValue() != null && StringUtils.isNotEmpty(itemGroupForm.getField("text3").getValue().toString())) {
            final String itemGroupDesc = itemGroupForm.getField("text3").getValue().toString();
            final String itemGroupDesc1 = itemGroupDesc.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_GROUP_DESCRIPTION, itemGroupDesc1));
        }
        if (itemGroupForm.getField("combo1").getValue() != null && !(StringUtils.isBlank(itemGroupForm.getField("combo1").getValue().toString()))
                && ((HelperDTO) itemGroupForm.getField("combo1").getValue()).getId() != 0) {
            itemgroupDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_SYS_ID, ((HelperDTO) itemGroupForm.getField("combo1").getValue()).getId()));
        }
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    if (ConstantsUtils.ITEM_GROUP_NAME.equals(stringFilter.getPropertyId())) {
                        itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_GROUP_NAME, filterString));
                    }
                    if (ConstantsUtils.ITEM_GROUP_NO.equals(stringFilter.getPropertyId())) {
                        itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_GROUP_NO, filterString));
                    }
                    if (ConstantsUtils.ITEM_GROUP_DESC.equals(stringFilter.getPropertyId())) {
                        itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_GROUP_DESCRIPTION, filterString));
                    }
//                    if (ConstantsUtils.COMPANY_DDLB.equals(stringFilter.getPropertyId())) {
//                        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
//                        companyDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_NAME, filterString));
//                        companyDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_TYPE, 25));
//                        companyDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("companyMasterSid")));
//                        List resultList = dao.getCompanyMasterList(companyDynamicQuery);
//                        if (!resultList.isEmpty()) {
//                            itemgroupDynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.COMPANY_SYS_ID, resultList));
//                    }
//                    }
                    /**
                     * if
                     * (ConstantsUtils.CREATED_BY.equals(stringFilter.getPropertyId()))
                     * {
                     * itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.CREATED_BY,
                     * filterString)); }
                     */

                } else if (filter instanceof Compare) {
                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    if (ConstantsUtils.VERSION_NO.equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
                        int value = Integer.valueOf(String.valueOf(compare.getValue()));
                        if (operation.GREATER.toString().equalsIgnoreCase(operation.name())) {
                            itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.VERSION_NO, value));
                        } else if (operation.LESS.toString().equalsIgnoreCase(operation.name())) {
                            itemgroupDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.VERSION_NO, value));
                        } else if (operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
                            itemgroupDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.VERSION_NO, value));
                        }
                    } else if (compare.getValue() instanceof Date) {
                        Date filterString = (Date) compare.getValue();
                        if (compare.getOperation().equals(compare.getOperation().GREATER_OR_EQUAL)) {
                            itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ge(String.valueOf(compare.getPropertyId()), filterString));
                        } else if (compare.getOperation().equals(compare.getOperation().LESS_OR_EQUAL)) {
                            itemgroupDynamicQuery.add(RestrictionsFactoryUtil.le(String.valueOf(compare.getPropertyId()), filterString));
                        }
                    } else if (compare.getValue() instanceof HelperDTO) {
                        int filterValue = 0;
                        try {
                            filterValue = ((HelperDTO) compare.getValue()).getId();
                        } catch (Exception ex) {
                            filterValue = 0;
                        }
                        if (filterValue!=0 && ConstantsUtils.COMPANY_DDLB.equals(String.valueOf(compare.getPropertyId()))) {
                            itemgroupDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_SYS_ID, filterValue));
                        }
                    }

                } else if (filter instanceof Between) {

                    Between stringFilter = (Between) filter;
                    Date filterString = (Date) stringFilter.getStartValue();
                    Date filterString1 = (Date) stringFilter.getEndValue();

                    if (ConstantsUtils.CREATED_DATE.equals(stringFilter.getPropertyId())) {
                        itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.CREATED_DATE, filterString));
                        itemgroupDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.CREATED_DATE, filterString1));
                    }
                    if (ConstantsUtils.MODIFIED_DATE.equals(stringFilter.getPropertyId())) {
                        itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.MODIFIED_DATE, filterString));
                        itemgroupDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.MODIFIED_DATE, filterString1));
                    }

                }
            }
        }
        loadColumnName();
        if (!isCount) {
            itemgroupDynamicQuery.setLimit(startIndex, endIndex);
        }
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn orderByColumn = (SortByColumn) iterator.next();

                if (orderByColumn.getType() == SortByColumn.Type.ASC) {

                    itemgroupDynamicQuery.addOrder(OrderFactoryUtil.asc(getDBColumnName(orderByColumn.getName())));
                } else {
                    itemgroupDynamicQuery.addOrder(OrderFactoryUtil.desc(getDBColumnName(orderByColumn.getName())));
                }
            }
        }
        List<ItemGroup> resultList = new ArrayList<ItemGroup>();
        List<HistItemGroup> historyList = new ArrayList<HistItemGroup>();
        List<SearchResultsDTO> itemGroupList = new ArrayList<SearchResultsDTO>();
        Object object = new Object();
        if (version.equalsIgnoreCase(ConstantsUtils.VERSION_CURRENT)) {
            if (isCount) {
                object = (Integer) dao.getItemGroupsListCount(itemgroupDynamicQuery);
            } else {
                try {
                    resultList = dao.getItemGroupsList(itemgroupDynamicQuery);
                    object = getCustomizedResults(resultList);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

        } else if (version.equalsIgnoreCase(ConstantsUtils.VERSION_HIST)) {
            if (isCount) {
                object = (Integer) dao.getItemGroupsHistoryListCount(itemgroupDynamicQuery);
            } else {
                try {
                    historyList = dao.getItemGroupsHistoryList(itemgroupDynamicQuery);
                    object = getCustomizedHistoryResults(historyList);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }

        LOGGER.info("getSearchResults return itemGroupList=" + itemGroupList.size());
        LOGGER.info("getSearchResults Ended  ");

        return object;
    }

    public static String getDBColumnName(String visibleColumnName) {
        return columnNames.get(visibleColumnName);
    }

    public static HashMap<String, String> loadColumnName() {

        columnNames.put("itemGroupName", "itemGroupName");
        columnNames.put("itemGroupDesc", "itemGroupDescription");
        columnNames.put("itemGroupNo", "itemGroupNo");
        columnNames.put("companyDdlb", "companyName");
        columnNames.put("versionNo", "versionNo");
        columnNames.put("createdDate", "createdDate");
        columnNames.put("modifiedDate", "modifiedDate");
        columnNames.put("createdBy", "createdBy");

        return columnNames;

    }

    /**
     * Gets the search results.
     *
     * @param itemGroupForm the item group form
     * @return the search results
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
     * @throws java.lang.Exception
     */
    public List<SearchResultsDTO> getLazySearchResults(final BeanSearchCriteria criteria, final CustomFieldGroup itemGroupForm, final String flag, final int startIndex, final int endIndex, final List<OrderByColumn> columns) throws SystemException, PortalException, Exception {
        LOGGER.info("getSearchResults started with P1:CustomFieldGroup itemGroupForm");
        final DynamicQuery itemgroupHistoryDynamicQuery = DynamicQueryFactoryUtil.forClass(HistItemGroup.class);
        final DynamicQuery itemgroupDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemGroup.class);
        if (itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_NAME).getValue() != null && StringUtils.isNotEmpty(itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_NAME).getValue().toString())) {
            final String itemGroupName = itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_NAME).getValue().toString();
            final String itemGroupName1 = itemGroupName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            if (ConstantsUtils.SEARCH.equals(flag)) {
                itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_GROUP_NAME, itemGroupName1));
            } else {
                itemgroupHistoryDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_GROUP_NAME, itemGroupName1));
            }
        }
        if (itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_NO).getValue() != null && StringUtils.isNotEmpty(itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_NO).getValue().toString())) {
            final String itemGroupNo = itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_NO).getValue().toString();
            final String itemGroupNo1 = itemGroupNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            if (ConstantsUtils.SEARCH.equals(flag)) {
                itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_GROUP_NO, itemGroupNo1));
            } else {
                itemgroupHistoryDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_GROUP_NO, itemGroupNo1));
            }
        }
        if (itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_DESC).getValue() != null && StringUtils.isNotEmpty(itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_DESC).getValue().toString())) {
            final String itemGroupDesc = itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_DESC).getValue().toString();
            final String itemGroupDesc1 = itemGroupDesc.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            if (ConstantsUtils.SEARCH.equals(flag)) {
                itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_GROUP_DESCRIPTION, itemGroupDesc1));
            } else {
                itemgroupHistoryDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_GROUP_DESCRIPTION, itemGroupDesc1));
            }
        }
        if (itemGroupForm.getField(ConstantsUtils.COMPANY).getValue() != null && !(StringUtils.isBlank(itemGroupForm.getField(ConstantsUtils.COMPANY).getValue().toString()))
                && !itemGroupForm.getField(ConstantsUtils.COMPANY).getValue().toString().equals("0")) {

            if (ConstantsUtils.SEARCH.equals(flag)) {
                itemgroupDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_SYS_ID, Integer.valueOf(itemGroupForm.getField(ConstantsUtils.COMPANY).getValue().toString())));
            } else {
                itemgroupHistoryDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_SYS_ID, Integer.valueOf(itemGroupForm.getField(ConstantsUtils.COMPANY).getValue().toString())));
            }

        }
        if (ConstantsUtils.SEARCH.equals(flag)) {
            itemgroupDynamicQuery.setLimit(startIndex, endIndex);
        } else {
            itemgroupHistoryDynamicQuery.setLimit(startIndex, endIndex);
        }
        if (ConstantsUtils.SEARCH.equals(flag)) {
            List<ItemGroup> resultList = new ArrayList<ItemGroup>();
            resultList = dao.getItemGroupsList(itemgroupDynamicQuery);
            final List<SearchResultsDTO> itemGroupList = getCustomizedResults(resultList);
            return itemGroupList;
        } else {

            List<HistItemGroup> historyList = new ArrayList<HistItemGroup>();
            historyList = dao.getItemGroupsHistoryList(itemgroupHistoryDynamicQuery);
            final List<SearchResultsDTO> itemGroupList = getCustomizedHistoryResults(historyList);
            return itemGroupList;
        }

    }

    /**
     * Gets the search results count.
     *
     * @param itemGroupForm the item group form
     * @param flag the flag
     * @return the search results
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    /**
     * Gets the search results.
     *
     * @param itemGroupForm the item group form
     * @return the search results
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
     * @throws java.lang.Exception
     */
    public int getLazySearchCount(final CustomFieldGroup itemGroupForm, final String flag, final BeanSearchCriteria criteria) throws SystemException, PortalException, Exception {
        LOGGER.info("getSearchResults started with P1:CustomFieldGroup itemGroupForm");
        final DynamicQuery itemgroupHistoryDynamicQuery = DynamicQueryFactoryUtil.forClass(HistItemGroup.class);
        final DynamicQuery itemgroupDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemGroup.class);
        int count;
        if (itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_NAME).getValue() != null && StringUtils.isNotEmpty(itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_NAME).getValue().toString())) {
            final String itemGroupName = itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_NAME).getValue().toString();
            final String itemGroupName1 = itemGroupName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            if (ConstantsUtils.SEARCH.equals(flag)) {
                itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_GROUP_NAME, itemGroupName1));
            } else {
                itemgroupHistoryDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_GROUP_NAME, itemGroupName1));
            }
        }
        if (itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_NO).getValue() != null && StringUtils.isNotEmpty(itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_NO).getValue().toString())) {
            final String itemGroupNo = itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_NO).getValue().toString();
            final String itemGroupNo1 = itemGroupNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            if (ConstantsUtils.SEARCH.equals(flag)) {
                itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_GROUP_NO, itemGroupNo1));
            } else {
                itemgroupHistoryDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_GROUP_NO, itemGroupNo1));
            }
        }
        if (itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_DESC).getValue() != null && StringUtils.isNotEmpty(itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_DESC).getValue().toString())) {
            final String itemGroupDesc = itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_DESC).getValue().toString();
            final String itemGroupDesc1 = itemGroupDesc.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            if (ConstantsUtils.SEARCH.equals(flag)) {
                itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_GROUP_DESCRIPTION, itemGroupDesc1));
            } else {
                itemgroupHistoryDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_GROUP_DESCRIPTION, itemGroupDesc1));
            }
        }
        if (itemGroupForm.getField(ConstantsUtils.COMPANY).getValue() != null && !(StringUtils.isBlank(itemGroupForm.getField(ConstantsUtils.COMPANY).getValue().toString()))
                && !itemGroupForm.getField(ConstantsUtils.COMPANY).getValue().toString().equals("0")) {

            if (ConstantsUtils.SEARCH.equals(flag)) {
                itemgroupDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_SYS_ID, Integer.valueOf(itemGroupForm.getField(ConstantsUtils.COMPANY).getValue().toString())));
            } else {
                itemgroupHistoryDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_SYS_ID, Integer.valueOf(itemGroupForm.getField(ConstantsUtils.COMPANY).getValue().toString())));
            }

        }

        if (criteria.getFilters() != null) {
            for (Container.Filter filter : criteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString() + "%";
                    String filterObject = String.valueOf(stringFilter.getPropertyId());

                    if (filterObject.equals(String.valueOf(ConstantsUtils.ITEM_GROUP_DESC))) {
                        filterObject = ConstantsUtils.ITEM_GROUP_DESCRIPTION;
                    }
                    if (filterObject.equals(String.valueOf(ConstantsUtils.COMPANY)) || filterObject.equals(String.valueOf(ConstantsUtils.COMPANY_DDLB))) {

                        filterObject = ConstantsUtils.COMPANY_SYS_ID;
                    }
                    if (ConstantsUtils.SEARCH.equals(flag)) {
                        itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(filterObject, filterString));
                    } else {
                        itemgroupHistoryDynamicQuery.add(RestrictionsFactoryUtil.ilike(filterObject, filterString));
                    }
                }
            }
        }

        if (criteria.getFilters() != null) {

            for (Container.Filter filter : criteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString() + "%";
                    String filterObject = String.valueOf(stringFilter.getPropertyId());
                    if (filterObject.equals(String.valueOf(ConstantsUtils.ITEM_GROUP_DESC))) {
                        filterObject = ConstantsUtils.ITEM_GROUP_DESCRIPTION;
                    }
                    if (filterObject.equals(String.valueOf(ConstantsUtils.COMPANY)) || filterObject.equals(String.valueOf(ConstantsUtils.COMPANY_DDLB))) {
                        filterObject = ConstantsUtils.ITEM_GROUP_DESCRIPTION;

                    }
                    if (ConstantsUtils.SEARCH.equals(flag)) {
                        itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(filterObject, filterString));
                    } else {
                        itemgroupHistoryDynamicQuery.add(RestrictionsFactoryUtil.ilike(filterObject, filterString));
                    }
                }

                if (filter instanceof Between) {
                    Between dateFilter = (Between) filter;
                    Date filterStart = (Date) dateFilter.getStartValue();
                    Date filterEnd = (Date) dateFilter.getEndValue();
                    if (ConstantsUtils.SEARCH.equals(flag)) {
                        itemgroupDynamicQuery.add(RestrictionsFactoryUtil.between(String.valueOf(dateFilter.getPropertyId()), filterStart, filterEnd));
                    } else {
                        itemgroupHistoryDynamicQuery.add(RestrictionsFactoryUtil.between(String.valueOf(dateFilter.getPropertyId()), filterStart, filterEnd));
                    }
                }
            }
        }
        if (ConstantsUtils.SEARCH.equals(flag)) {
            count = dao.getItemGroupsListCount(itemgroupDynamicQuery);

        } else {
            count = dao.getItemGroupsHistoryListCount(itemgroupHistoryDynamicQuery);
        }
        return count;
    }

    /**
     * Gets the customized results.
     *
     * @param resultList the result list
     * @return the customized results
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private List<SearchResultsDTO> getCustomizedResults(final List<ItemGroup> resultList) throws SystemException, PortalException, Exception {
        LOGGER.info("getCustomizedResults started with P1:List<ItemGroup> resultList size:" + resultList.size());
        final List<SearchResultsDTO> itemGroupsList = new ArrayList<SearchResultsDTO>();
        final Map companyInfo = CommonUtils.getCompanyInformation();
        final Map<String, String> userInfoMap = CommonUtil.getCreatedByUser();
        String companyNoName;
        String companyNo;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        try {
            for (final ItemGroup itemGroup : resultList) {
                final SearchResultsDTO itemGroupDto = new SearchResultsDTO();
                final HelperDTO helperDTO = new HelperDTO();
                itemGroupDto.setItemGroupNo(itemGroup.getItemGroupNo());
                itemGroupDto.setItemGroupName(itemGroup.getItemGroupName());
                itemGroupDto.setItemGroupDesc(itemGroup.getItemGroupDescription());
                companyNoName = String.valueOf(companyInfo.get(String.valueOf(itemGroup.getCompanyMasterSid())));
                helperDTO.setId(itemGroup.getCompanyMasterSid());

                if (companyNoName != null && !companyNoName.equals(ConstantsUtils.NULL)) {

                    helperDTO.setDescription(companyNoName);
                    itemGroupDto.setCompanyDdlb(helperDTO);
                }
                itemGroupDto.setVersionNo(itemGroup.getVersionNo());
                if (userInfoMap.get(String.valueOf(itemGroup.getCreatedBy())) != null) {
                    itemGroupDto.setCreatedBy(String.valueOf(userInfoMap.get(String.valueOf(itemGroup.getCreatedBy()))));
                }
                if (itemGroup.getCreatedDate() != null) {
                    Date createdDate = df.parse(CommonUtils.convertDateToString(itemGroup.getCreatedDate()));
                    itemGroupDto.setCreatedDate(createdDate);
                }
                if (itemGroup.getModifiedDate() != null) {
                    Date modifiedDate = df.parse(CommonUtils.convertDateToString(itemGroup.getModifiedDate()));
                    itemGroupDto.setModifiedDate(modifiedDate);
                }
                itemGroupDto.setItemGroupSystemId(itemGroup.getItemGroupSid());
                itemGroupDto.setSystemID(String.valueOf(itemGroup.getItemGroupSid()));
                itemGroupsList.add(itemGroupDto);
            }

            LOGGER.info("getCustomizedResults return List<ItemGroupDTO> itemGroupsList=" + itemGroupsList.size());
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return itemGroupsList;
    }

    /**
     * Gets the customized history results.
     *
     * @param resultList the result list
     * @return the customized history results
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private List<SearchResultsDTO> getCustomizedHistoryResults(final List<HistItemGroup> resultList) throws SystemException, PortalException, Exception {
        LOGGER.info("getCustomizedResults started with P1:List<ItemGroup> resultList size:" + resultList.size());
        final List<SearchResultsDTO> itemGroupsList = new ArrayList<SearchResultsDTO>();
        final Map companyInfo = CommonUtils.getCompanyInformation();
        final Map userInfoMap = CommonUtil.getCreatedByUser();
        String companyNoName;
        String companyNo;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        try {
            for (final HistItemGroup itemGroup : resultList) {
                final SearchResultsDTO itemGroupDto = new SearchResultsDTO();
                final HelperDTO helperDTO = new HelperDTO();
                itemGroupDto.setItemGroupNo(itemGroup.getItemGroupNo());
                itemGroupDto.setItemGroupName(itemGroup.getItemGroupName());
                itemGroupDto.setItemGroupDesc(itemGroup.getItemGroupDescription());
                companyNoName = String.valueOf(companyInfo.get(String.valueOf(itemGroup.getCompanyMasterSid())));
                if (companyNoName != null && !companyNoName.equals(ConstantsUtils.NULL)) {
                    helperDTO.setId(itemGroup.getCompanyMasterSid());
                    helperDTO.setDescription(companyNoName);
                    itemGroupDto.setCompanyDdlb(helperDTO);
                }
                itemGroupDto.setVersionNo(itemGroup.getVersionNo());
                if (userInfoMap.get(itemGroup.getCreatedBy()) != null) {
                    itemGroupDto.setCreatedBy(String.valueOf(userInfoMap.get(itemGroup.getCreatedBy())));
                }
                if (itemGroup.getCreatedDate() != null) {
                    Date createdDate = df.parse(CommonUtils.convertDateToString(itemGroup.getCreatedDate()));
                    itemGroupDto.setCreatedDate(createdDate);
                }
                if (itemGroup.getModifiedDate() != null) {
                    Date modifiedDate = df.parse(CommonUtils.convertDateToString(itemGroup.getModifiedDate()));
                    itemGroupDto.setModifiedDate(modifiedDate);
                }
                itemGroupDto.setItemGroupSystemId(itemGroup.getItemGroupSid());
                itemGroupDto.setSystemID(String.valueOf(itemGroup.getItemGroupSid()));
                itemGroupsList.add(itemGroupDto);
            }

            LOGGER.info("getCustomizedResults return List<ItemGroupDTO> itemGroupsList=" + itemGroupsList.size());
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return itemGroupsList;
    }

    /**
     * Gets the item search results.
     *
     * @param itemForm the item form
     * @return the item search results
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<ItemDetailsDTO> getItemSearchResults(final ErrorfulFieldGroup itemForm) throws SystemException, PortalException, Exception {
        LOGGER.info("getItemSearchResults started with P1:CustomFieldGroup itemForm");
        List<ItemDetailsDTO> itemsList;
        List<String> criteria = getSearchCriteria(itemForm, 0);
        final DynamicQuery projectionDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);

        if (itemForm.getField(ConstantsUtils.ITEM_TYPE).getValue() != null && StringUtils.isNotEmpty(itemForm.getField(ConstantsUtils.ITEM_TYPE).getValue().toString())) {
            projectionDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_TYPE, Integer.valueOf(String.valueOf(itemForm.getField(ConstantsUtils.ITEM_TYPE).getValue()))));
        }
        if (itemForm.getField(ConstantsUtils.ITEM_DESC).getValue() != null && StringUtils.isNotEmpty(itemForm.getField(ConstantsUtils.ITEM_DESC).getValue().toString())) {
            final String itemDesc = itemForm.getField(ConstantsUtils.ITEM_DESC).getValue().toString();
            final String itemDesc1 = itemDesc.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_DESC, itemDesc1));
        }
        if (itemForm.getField(ConstantsUtils.BRAND).getValue() != null && StringUtils.isNotEmpty(itemForm.getField(ConstantsUtils.BRAND).getValue().toString())) {
            final String brand = itemForm.getField(ConstantsUtils.BRAND).getValue().toString();
            final String brand1 = brand.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.BRAND, brand1));
        }
        if (itemForm.getField(ConstantsUtils.STENGTH).getValue() != null && StringUtils.isNotEmpty(itemForm.getField(ConstantsUtils.STENGTH).getValue().toString())) {
            final String strength = itemForm.getField(ConstantsUtils.STENGTH).getValue().toString();
            final String strength1 = strength.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.STENGTH, strength1));
        }
        if (itemForm.getField(ConstantsUtils.ITEM_NO).getValue() != null && StringUtils.isNotEmpty(itemForm.getField(ConstantsUtils.ITEM_NO).getValue().toString())) {
            final String itemNo = itemForm.getField(ConstantsUtils.ITEM_NO).getValue().toString();
            final String itemNo1 = itemNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_NO, itemNo1));
        }
        if (itemForm.getField(ConstantsUtils.THERAPEUTIC_CLASS).getValue() != null && StringUtils.isNotEmpty(itemForm.getField(ConstantsUtils.THERAPEUTIC_CLASS).getValue().toString())) {
            projectionDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.THERAPEUTIC_CLASS, Integer.valueOf(String.valueOf(itemForm.getField(ConstantsUtils.THERAPEUTIC_CLASS).getValue()))));
        }
        if (itemForm.getField(ConstantsUtils.FORM).getValue() != null && StringUtils.isNotEmpty(itemForm.getField(ConstantsUtils.FORM).getValue().toString())) {
            final String form = itemForm.getField(ConstantsUtils.FORM).getValue().toString();
            final String form1 = form.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.FORM, Integer.valueOf(form1)));
        }
        projectionDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));
        List<ItemMaster> resultList;
        final HashMap resultList1 = new HashMap();
        resultList = dao.getItemsList(projectionDynamicQuery);

        itemsList = getCustomizedItemResults(resultList, resultList1);
        LOGGER.info("getItemSearchResults return List<ItemDetailsDTO> itemsList=" + itemsList.size());
        return itemsList;
    }

    public List<String> getSearchCriteria(final ErrorfulFieldGroup itemForm, final int count) throws SystemException, PortalException, Exception {
        final List<String> criteria = new ArrayList<String>();
        String itemTypeCriteria = ConstantsUtils.EMPTY;
        String itemDescription = ConstantsUtils.EMPTY;
        String brandCriteria = ConstantsUtils.EMPTY;
        String strengthCriteria = ConstantsUtils.EMPTY;
        String itemNoCriteria = ConstantsUtils.EMPTY;
        String therapeuticCriteria = ConstantsUtils.EMPTY;
        String formCriteria = ConstantsUtils.EMPTY;
        String searchCriteria = ConstantsUtils.EMPTY;
        boolean flagCount = true;
        boolean flag = false;
        if (count > 1) {
            searchCriteria += " AND ";
        }
        if (null != itemForm.getField(ConstantsUtils.ITEM_TYPE_DDLB).getValue() && StringUtils.isNotEmpty(itemForm.getField(ConstantsUtils.ITEM_TYPE_DDLB).getValue().toString())
                && !ConstantsUtils.SELECT_ONE.equalsIgnoreCase(itemForm.getField(ConstantsUtils.ITEM_TYPE_DDLB).getValue().toString())) {
            itemTypeCriteria = itemForm.getField(ConstantsUtils.ITEM_TYPE_DDLB).getValue().toString();
            if (flag) {
                searchCriteria += " AND ";
            }
            flag = true;
            searchCriteria += " HT_IT.description = '" + itemTypeCriteria + "' " + " AND IM.ITEM_TYPE = HT_IT.HELPER_TABLE_SID ";
            flagCount = false;
        }
        if (itemForm.getField(ConstantsUtils.ITEM_DESC).getValue() != null && StringUtils.isNotEmpty(itemForm.getField(ConstantsUtils.ITEM_DESC).getValue().toString())) {
            final String itemDesc = itemForm.getField(ConstantsUtils.ITEM_DESC).getValue().toString();
            itemDescription = itemDesc.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            if (flag) {
                searchCriteria += " AND ";
            }
            flag = true;
            searchCriteria += " IM.item_Desc like '" + itemDescription + "'";
            flagCount = false;
        }
        if (itemForm.getField(ConstantsUtils.BRAND_DDLB).getValue() != null && StringUtils.isNotEmpty(itemForm.getField(ConstantsUtils.BRAND_DDLB).getValue().toString())
                && !ConstantsUtils.SELECT_ONE.equalsIgnoreCase(itemForm.getField(ConstantsUtils.BRAND_DDLB).getValue().toString())) {
            final HelperDTO brandDto = (HelperDTO) itemForm.getField(ConstantsUtils.BRAND_DDLB).getValue();
            brandCriteria = itemForm.getField(ConstantsUtils.BRAND_DDLB).getValue().toString();

            if (flag) {
                searchCriteria += " AND ";
            }
            flag = true;
            searchCriteria += " BM.brand_Name = '" + brandCriteria + "'" + " AND IM.BRAND_MASTER_SID = BM.BRAND_MASTER_SID";
            flagCount = false;
        }
        if (itemForm.getField(ConstantsUtils.STENGTH).getValue() != null && StringUtils.isNotEmpty(itemForm.getField(ConstantsUtils.STENGTH).getValue().toString())) {
            final String strength = itemForm.getField(ConstantsUtils.STENGTH).getValue().toString();
            strengthCriteria = strength.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            if (flag) {
                searchCriteria += " AND ";
            }
            flag = true;
            searchCriteria += " IM.strength like '" + strengthCriteria + "'";
            flagCount = false;
        }
        if (itemForm.getField(ConstantsUtils.ITEM_NO).getValue() != null && StringUtils.isNotEmpty(itemForm.getField(ConstantsUtils.ITEM_NO).getValue().toString())) {
            final String itemNo = itemForm.getField(ConstantsUtils.ITEM_NO).getValue().toString();
            itemNoCriteria = itemNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            if (flag) {
                searchCriteria += " AND ";
            }
            flag = true;
            searchCriteria += " IM.item_No like '" + itemNoCriteria + "'";
            flagCount = false;
        }
        if (itemForm.getField(ConstantsUtils.THERAPEUTIC_CLASS_DDLB).getValue() != null && StringUtils.isNotEmpty(itemForm.getField(ConstantsUtils.THERAPEUTIC_CLASS_DDLB).getValue().toString())
                && !ConstantsUtils.SELECT_ONE.equalsIgnoreCase(itemForm.getField(ConstantsUtils.THERAPEUTIC_CLASS_DDLB).getValue().toString())) {
            therapeuticCriteria = itemForm.getField(ConstantsUtils.THERAPEUTIC_CLASS_DDLB).getValue().toString();
            if (flag) {
                searchCriteria += " AND ";
            }
            flag = true;
            searchCriteria += " HT_TC.description ='" + therapeuticCriteria + "'" + " AND IM.THERAPEUTIC_CLASS = HT_TC.HELPER_TABLE_SID";
            flagCount = false;

        }
        if (itemForm.getField(ConstantsUtils.FORM_DDLB).getValue() != null && StringUtils.isNotEmpty(itemForm.getField(ConstantsUtils.FORM_DDLB).getValue().toString())
                && !ConstantsUtils.SELECT_ONE.equalsIgnoreCase(itemForm.getField(ConstantsUtils.FORM_DDLB).getValue().toString())) {
            formCriteria = itemForm.getField(ConstantsUtils.FORM_DDLB).getValue().toString();
            if (flag) {
                searchCriteria += " AND ";
            }
            flag = true;
            searchCriteria += " HT_F.description = '" + formCriteria + "'" + " AND IM.FORM = HT_F.HELPER_TABLE_SID";
            flagCount = false;

        }

        if (flagCount) {
            searchCriteria += "Wildcard";
        }
        criteria.add(itemTypeCriteria);
        criteria.add(itemDescription);
        criteria.add(brandCriteria);
        criteria.add(strengthCriteria);
        criteria.add(itemNoCriteria);
        criteria.add(therapeuticCriteria);
        criteria.add(formCriteria);
        criteria.add(searchCriteria);

        return criteria;
    }

    /**
     * Gets the item search results.
     *
     * @param itemForm the item form
     * @return the item search results
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<ItemDetailsDTO> getItemSearch(final ErrorfulFieldGroup itemForm, final ItemDetailsDTO itemDetailsDTO, int count) throws SystemException, PortalException, Exception {
        LOGGER.info("getItemSearchResults started with P1:CustomFieldGroup itemForm");
        List<ItemDetailsDTO> itemsList = new ArrayList<ItemDetailsDTO>();
        List<String> criteria = getSearchCriteria(itemForm, count);
        List resultList = ItemGroupLocalServiceUtil.getItemGroupDetails(criteria.get(0), criteria.get(1), criteria.get(2), criteria.get(3), criteria.get(4), criteria.get(5), criteria.get(6));
        final HashMap resultList1 = new HashMap();
        itemsList = getCustomizedItemResults(resultList, resultList1);
        LOGGER.info("getItemSearchResults return List<ItemDetailsDTO> itemsList=" + itemsList.size());
        return itemsList;
    }

    /**
     * Gets the customized item results.
     *
     * @param resultList the result list
     * @param resultList1 the result list1
     * @return the customized item results
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<ItemDetailsDTO> getCustomizedItemResults(final List resultList, final Map resultList1) throws SystemException, PortalException, Exception {
        LOGGER.info("getCustomizedItemResults started with P1:List<ItemMaster> resultList" + resultList.size() + "and P2:HashMap resultList1 size");

        final List<ItemDetailsDTO> itemDetailsList = new ArrayList<ItemDetailsDTO>();
        final Map companyInfo = CommonUtils.getCompanyInfo();
        if (!resultList.isEmpty()) {
            for (int i = 0; i < resultList.size(); i++) {

                final Object[] obj = (Object[]) resultList.get(i);
                final ItemDetailsDTO itemDetailsDto = new ItemDetailsDTO();
                String companyNoName;
                String companyNo;
                String companyName;
                String companyId;
                if (obj[0] == null) {
                    itemDetailsDto.setItemId(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setItemId(obj[0].toString());

                }
                if (obj[1] == null) {
                    itemDetailsDto.setItemNo(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setItemNo(obj[1].toString());
                }
                if (obj[2] == null) {
                    itemDetailsDto.setItemCode(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setItemCode(obj[2].toString());
                }
                if (obj[3] == null) {
                    itemDetailsDto.setItemName(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setItemName(obj[3].toString());
                }
                if (obj[4] == null) {
                    itemDetailsDto.setItemDesc(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setItemDesc(obj[4].toString());
                }
                if (obj[5] != null) {
                    Date startDate = (Date) obj[5];
                    Date itemStartDate = df.parse(CommonUtils.convertDateToString(startDate));
                    itemDetailsDto.setItemStartDate(itemStartDate);
                }
                if (obj[6] != null) {
                    Date endDate = (Date) obj[6];
                    Date itemEndDate = df.parse(CommonUtils.convertDateToString(endDate));
                    itemDetailsDto.setItemEndDate(itemEndDate);
                }
                if (obj[7] != null && !ConstantsUtils.SELECTONE.equalsIgnoreCase(String.valueOf(obj[7]))) {
                    itemDetailsDto.setItemStatus(obj[7].toString());
                } else {
                    itemDetailsDto.setItemStatus(ConstantsUtils.EMPTY);
                }
                if (obj[8] != null && !ConstantsUtils.SELECTONE.equalsIgnoreCase(String.valueOf(obj[8]))) {
                    itemDetailsDto.setTherapeuticClass(obj[8].toString());
                } else {
                    itemDetailsDto.setTherapeuticClass(ConstantsUtils.EMPTY);
                }

                if (obj[9] == null) {
                    itemDetailsDto.setBrand(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setBrand(obj[9].toString());
                }
                if (obj[10] != null && !ConstantsUtils.SELECTONE.equalsIgnoreCase(String.valueOf(obj[10]))) {
                    itemDetailsDto.setForm(obj[10].toString());
                } else {
                    itemDetailsDto.setForm(ConstantsUtils.EMPTY);
                }
                if (obj[11] == null) {
                    itemDetailsDto.setStrength(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setStrength(obj[11].toString());
                }
                if (obj[12] == null) {
                    itemDetailsDto.setPackageSizeCode(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setPackageSizeCode(obj[12].toString());
                }
                if (obj[13] != null) {
                    Date packageIntroDate = (Date) obj[13];
                    Date packageSizeIntroDate = df.parse(CommonUtils.convertDateToString(packageIntroDate));
                    itemDetailsDto.setPackageSizeIntroDate(packageSizeIntroDate);
                }
                if (obj[14] == null) {
                    itemDetailsDto.setUpps(0.0);
                } else {
                    itemDetailsDto.setUpps(Double.valueOf(obj[14].toString()));
                }
                if (obj[15] == null) {
                    itemDetailsDto.setLabelerCode(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setLabelerCode(obj[15].toString());
                }
                if (obj[16] == null) {
                    itemDetailsDto.setOrganizationKey(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setOrganizationKey(obj[16].toString());
                }
                if (obj[17] != null) {
                    Date acquisitionDate = (Date) obj[17];
                    Date acqDate = df.parse(CommonUtils.convertDateToString(acquisitionDate));
                    itemDetailsDto.setAcquisitionDate(acqDate);
                }
                if (obj[18] == null) {
                    itemDetailsDto.setAuthorizedGeneric(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setAuthorizedGeneric(obj[18].toString());
                }
                if (obj[19] != null) {
                    Date genericStartDate = (Date) obj[19];
                    Date genericSDate = df.parse(CommonUtils.convertDateToString(genericStartDate));
                    itemDetailsDto.setAuthorizedGenericStartDate(genericSDate);
                }
                if (obj[20] != null) {
                    Date genericEndDate = (Date) obj[20];
                    Date genericEDate = df.parse(CommonUtils.convertDateToString(genericEndDate));
                    itemDetailsDto.setAuthorizedGenericEndDate(genericEDate);
                }
                if (obj[21] != null) {
                    Date saleDate = (Date) obj[21];
                    Date FirstSaleDate = df.parse(CommonUtils.convertDateToString(saleDate));
                    itemDetailsDto.setFirstSaleDate(FirstSaleDate);
                }
                if (obj[22] == null) {
                    itemDetailsDto.setItemTypeIndicator(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setItemTypeIndicator(obj[22].toString());
                }

                if (obj[23] != null && !ConstantsUtils.SELECTONE.equalsIgnoreCase(String.valueOf(obj[23]))) {
                    itemDetailsDto.setItemClass(obj[23].toString());
                } else {
                    itemDetailsDto.setItemClass(ConstantsUtils.EMPTY);
                }
                if (obj[24] != null && !ConstantsUtils.SELECTONE.equalsIgnoreCase(String.valueOf(obj[24]))) {
                    itemDetailsDto.setItemType(obj[24].toString());
                } else {
                    itemDetailsDto.setItemType(ConstantsUtils.EMPTY);
                }
                if (obj[25] != null) {
                    Date terminationDate = (Date) obj[25];
                    Date marketTerminationDate = df.parse(CommonUtils.convertDateToString(terminationDate));
                    itemDetailsDto.setMarketTerminationDate(marketTerminationDate);
                }
                if (obj[26] == null) {
                    itemDetailsDto.setNewFormulationIndicator(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setNewFormulationIndicator(obj[26].toString());
                }
                if (obj[27] == null) {
                    itemDetailsDto.setNewFormulation(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setNewFormulation(obj[27].toString());
                }
                if (obj[28] != null) {
                    Date newFormulation = (Date) obj[28];
                    Date newFormulationSDate = df.parse(CommonUtils.convertDateToString(newFormulation));
                    itemDetailsDto.setNewFormulationStartDate(newFormulationSDate);
                }
                if (obj[29] != null) {
                    Date newFormulation = (Date) obj[29];
                    Date newFormulationEDate = df.parse(CommonUtils.convertDateToString(newFormulation));
                    itemDetailsDto.setNewFormulationEndDate(newFormulationEDate);
                }
                if (obj[30] == null) {
                    itemDetailsDto.setPediatricExclusiveIndicator(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setPediatricExclusiveIndicator(obj[30].toString());
                }
                if (obj[31] != null) {
                    Date pediatricSDate = (Date) obj[31];
                    Date pediatricStartDate = df.parse(CommonUtils.convertDateToString(pediatricSDate));
                    itemDetailsDto.setPediatricExclusiveStartDate(pediatricStartDate);

                    if (obj[32] != null) {
                        Date pediatricEDate = (Date) obj[32];
                        Date pediatricEndDate = df.parse(CommonUtils.convertDateToString(pediatricEDate));
                        itemDetailsDto.setPediatricExclusiveEndDate(pediatricEndDate);
                    }
                }
                if (obj[33] == null) {
                    itemDetailsDto.setClottingFactorIndicator(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setClottingFactorIndicator(obj[33].toString());
                }
                if (obj[34] != null) {
                    Date clottingSDate = (Date) obj[34];
                    Date clottingStartDate = df.parse(CommonUtils.convertDateToString(clottingSDate));
                    itemDetailsDto.setClottingFactorStartDate(clottingStartDate);
                }
                if (obj[35] != null) {
                    Date clottingEDate = (Date) obj[35];
                    Date clottingEndDate = df.parse(CommonUtils.convertDateToString(clottingEDate));
                    itemDetailsDto.setClottingFactorEndDate(clottingEndDate);
                }

                if (obj[36] != null && !ConstantsUtils.SELECTONE.equalsIgnoreCase(String.valueOf(obj[36]))) {
                    itemDetailsDto.setPrimaryUom(obj[36].toString());
                } else {
                    itemDetailsDto.setPrimaryUom(ConstantsUtils.EMPTY);
                }
                if (obj[37] != null && !ConstantsUtils.SELECTONE.equalsIgnoreCase(String.valueOf(obj[37]))) {
                    itemDetailsDto.setSecondaryUom(obj[37].toString());
                } else {
                    itemDetailsDto.setSecondaryUom(ConstantsUtils.EMPTY);
                }
                if (obj[38] == null) {
                    itemDetailsDto.setShelfLife(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setShelfLife(obj[38].toString());
                }
                if (obj[39] != null && !ConstantsUtils.SELECTONE.equalsIgnoreCase(String.valueOf(obj[39]))) {
                    itemDetailsDto.setShelfLifeType(obj[39].toString());
                } else {
                    itemDetailsDto.setShelfLifeType(ConstantsUtils.EMPTY);
                }
                if (obj[40] == null) {
                    itemDetailsDto.setDualPricingIndicator(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setDualPricingIndicator(obj[40].toString());
                }
                if (obj[41] == null) {
                    itemDetailsDto.setItemFamilyId(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setItemFamilyId(obj[41].toString());
                }
                if (obj[42] != null && !ConstantsUtils.SELECTONE.equalsIgnoreCase(String.valueOf(obj[42]))) {
                    itemDetailsDto.setUdc1(obj[42].toString());
                } else {
                    itemDetailsDto.setUdc1(ConstantsUtils.EMPTY);
                }
                if (obj[43] != null && !ConstantsUtils.SELECTONE.equalsIgnoreCase(String.valueOf(obj[43]))) {
                    itemDetailsDto.setUdc2(obj[43].toString());
                } else {
                    itemDetailsDto.setUdc2(ConstantsUtils.EMPTY);
                }
                if (obj[44] != null && !ConstantsUtils.SELECTONE.equalsIgnoreCase(String.valueOf(obj[44]))) {
                    itemDetailsDto.setUdc3(obj[44].toString());
                } else {
                    itemDetailsDto.setUdc3(ConstantsUtils.EMPTY);
                }
                if (obj[45] != null && !ConstantsUtils.SELECTONE.equalsIgnoreCase(String.valueOf(obj[45]))) {
                    itemDetailsDto.setUdc4(obj[45].toString());
                } else {
                    itemDetailsDto.setUdc4(ConstantsUtils.EMPTY);
                }
                if (obj[46] != null && !ConstantsUtils.SELECTONE.equalsIgnoreCase(String.valueOf(obj[46]))) {
                    itemDetailsDto.setUdc5(obj[46].toString());
                } else {
                    itemDetailsDto.setUdc5(ConstantsUtils.EMPTY);
                }
                if (obj[47] != null && !ConstantsUtils.SELECTONE.equalsIgnoreCase(String.valueOf(obj[47]))) {
                    itemDetailsDto.setUdc6(obj[47].toString());
                } else {
                    itemDetailsDto.setUdc6(ConstantsUtils.EMPTY);
                }
                if (obj[48] == null) {
                    itemDetailsDto.setAcquiredAmp(0.0);
                } else {
                    itemDetailsDto.setAcquiredAmp(Double.valueOf(obj[48].toString()));
                }
                if (obj[49] == null) {
                    itemDetailsDto.setAcquiredBamp(0.0);
                } else {
                    itemDetailsDto.setAcquiredBamp(Double.valueOf(obj[49].toString()));
                }
                if (obj[50] == null) {
                    itemDetailsDto.setObraBamp(0.0);
                } else {
                    itemDetailsDto.setObraBamp(Double.valueOf(obj[50].toString()));
                }
                if (obj[51] == null) {
                    itemDetailsDto.setDra(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setDra(obj[51].toString());
                }
                if (obj[52] == null) {
                    itemDetailsDto.setDosesPerUnit(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setDosesPerUnit(obj[52].toString());
                }
                if (obj[53] == null) {
                    itemDetailsDto.setDiscontinuationDate(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setDiscontinuationDate(obj[53].toString());
                }
                if (obj[54] == null) {
                    itemDetailsDto.setLastLotExpirationDate(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setLastLotExpirationDate(obj[54].toString());
                }
                if (obj[55] == null) {
                    itemDetailsDto.setNdc9(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setNdc9(obj[55].toString());
                }
                if (obj[56] == null) {
                    itemDetailsDto.setNdc8(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setNdc8(obj[56].toString());
                }
                if (obj[57] == null) {
                    itemDetailsDto.setDisplayBrand(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setDisplayBrand(obj[57].toString());
                }
                if (obj[58] == null) {
                    itemDetailsDto.setBaselineAmp(0.0);
                } else {
                    itemDetailsDto.setBaselineAmp(Double.valueOf(obj[58].toString()));
                }
                if (obj[59] == null) {
                    itemDetailsDto.setBaseYearCpi(0.0);
                } else {
                    itemDetailsDto.setBaseYearCpi(Double.valueOf(obj[59].toString()));
                }
                if (obj[60] == null) {
                    itemDetailsDto.setManufacturerId((ConstantsUtils.EMPTY));
                } else {
                    itemDetailsDto.setManufacturerId((obj[60].toString()));
                    companyNoName = String.valueOf(companyInfo.get(itemDetailsDto.getManufacturerId()));
                    if (companyNoName != null && !companyNoName.equals(ConstantsUtils.NULL)) {
                        companyNo = companyNoName.substring(0, companyNoName.indexOf(ConstantsUtils.TILTE));
                        companyName = companyNoName.substring(companyNoName.indexOf(ConstantsUtils.TILTE) + 1, companyNoName.indexOf(ConstantsUtils.APOSTROPE));
                        companyId = companyNoName.substring(companyNoName.indexOf(ConstantsUtils.APOSTROPE) + 1, companyNoName.length());
                        itemDetailsDto.setManufacturerNo(companyNo);
                        itemDetailsDto.setManufacturerName(companyName);
                        itemDetailsDto.setManufacturerId(companyId);
                    }
                }
                if (obj[61] == null) {
                    itemDetailsDto.setItemSystemId(0);
                } else {
                    itemDetailsDto.setItemSystemId(Integer.valueOf(obj[61].toString()));
                }
                itemDetailsList.add(itemDetailsDto);

            }
        }
        return itemDetailsList;
    }

    /**
     * Gets the customized item results.
     *
     * @param resultList the result list
     * @param resultList1 the result list1
     * @return the customized item results
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private List<ItemDetailsDTO> getHistoryCustomizedItemResults(final List resultList, final Map resultList1) throws SystemException, PortalException, Exception {
        LOGGER.info("getCustomizedItemResults started with P1:List<ItemMaster> resultList" + resultList.size() + "and P2:HashMap resultList1 size" + resultList1.size());
        final List<ItemDetailsDTO> itemDetailsList = new ArrayList<ItemDetailsDTO>();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        final Map companyInfo = CommonUtils.getCompanyInfo();
        if (!resultList.isEmpty()) {
            for (int i = 0; i < resultList.size(); i++) {
                final DynamicQuery udcDynamicQuery = DynamicQueryFactoryUtil.forClass(Udcs.class);
                final ItemDetailsDTO itemDetailsDto = new ItemDetailsDTO();
                final Object[] item = (Object[]) resultList.get(i);
                String companyNoName;
                String companyNo;
                String companyName;
                String companyId;
                BrandMaster brandMaster = BrandMasterLocalServiceUtil.createBrandMaster(0);
                if (!String.valueOf(ConstantsUtils.ZERO_NUM).equals(String.valueOf(item[5]))) {
                    brandMaster = BrandMasterLocalServiceUtil.getBrandMaster(Integer.valueOf(String.valueOf(item[5])));
                }
                final int masterSid = Integer.valueOf(String.valueOf(item[0]));
                udcDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.MASTER_SID, masterSid));
                final List<Udcs> udcResultList = UdcsLocalServiceUtil.dynamicQuery(udcDynamicQuery);
                if (!udcResultList.isEmpty()) {
                    final Udcs udcItem = udcResultList.get(0);
                    itemDetailsDto.setUdc1(CommonUtil.getDescriptionFromHelper(udcItem.getUdc2()));
                    itemDetailsDto.setUdc2(CommonUtil.getDescriptionFromHelper(udcItem.getUdc2()));
                    itemDetailsDto.setUdc3(CommonUtil.getDescriptionFromHelper(udcItem.getUdc3()));
                    itemDetailsDto.setUdc4(CommonUtil.getDescriptionFromHelper(udcItem.getUdc4()));
                    itemDetailsDto.setUdc5(CommonUtil.getDescriptionFromHelper(udcItem.getUdc5()));
                    itemDetailsDto.setUdc6(CommonUtil.getDescriptionFromHelper(udcItem.getUdc6()));
                }

                itemDetailsDto.setItemId(String.valueOf(item[1]));
                itemDetailsDto.setItemNo(String.valueOf(item[2]));
                itemDetailsDto.setItemCode(String.valueOf(item[3]));
                itemDetailsDto.setItemName(String.valueOf(item[4]));
                itemDetailsDto.setItemDesc(item[6] == null ? ConstantsUtils.EMPTY : String.valueOf(item[6]));

                if (item[10] != null && !"null".equals(String.valueOf(item[10])) && !ConstantsUtils.EMPTY.equals(String.valueOf(item[10]))) {
                    Date itemStartDate = df.parse(CommonUtils.convertDateToString((Date) (item[10])));
                    itemDetailsDto.setItemStartDate(itemStartDate);
                }
                if (item[11] != null) {
                    Date itemEndDate = df.parse(CommonUtils.convertDateToString((Date) item[11]));
                    itemDetailsDto.setItemEndDate(itemEndDate);
                }
                if (item[12] != null) {
                    final String itemStatus = CommonUtil.getDescriptionFromHelper(Integer.valueOf(String.valueOf(item[12])));

                    itemDetailsDto.setItemStatus(itemStatus);
                }
                if (item[15] != null) {
                    final String therapeuticClass = CommonUtil.getDescriptionFromHelper(Integer.valueOf(String.valueOf(item[15])));
                    final HelperDTO therapeuticHelper = new HelperDTO();
                    therapeuticHelper.setId(Integer.valueOf(String.valueOf(item[12])));
                    therapeuticHelper.setDescription(therapeuticClass);
                    itemDetailsDto.setTherapeuticClassDdlb(therapeuticHelper);
                }
                itemDetailsDto.setBrand(brandMaster.getBrandName());
                if (item[13] != null) {
                    final String form = CommonUtil.getDescriptionFromHelper(Integer.valueOf(String.valueOf(item[13])));
                    final HelperDTO formHelper = new HelperDTO();
                    formHelper.setId(Integer.valueOf(String.valueOf(item[13])));
                    formHelper.setDescription(form);
                    itemDetailsDto.setFormDdlb(formHelper);
                }
                itemDetailsDto.setStrength(item[14] == null ? ConstantsUtils.EMPTY : String.valueOf(item[14]));
                itemDetailsDto.setPackageSizeCode(item[59] == null ? ConstantsUtils.EMPTY : String.valueOf(item[59]));
                if (item[8] != null) {
                    Date packageDate = df.parse(CommonUtils.convertDateToString((Date) item[8]));
                    itemDetailsDto.setPackageSizeIntroDate(packageDate);
                }
                itemDetailsDto.setUpps(Double.valueOf("null".equals(String.valueOf(item[9])) ? "0.0" : String.valueOf(item[9])));
                itemDetailsDto.setManufacturerId(String.valueOf(item[16] == null ? ConstantsUtils.EMPTY : item[16]));
                companyNoName = String.valueOf(companyInfo.get(String.valueOf(item[16])));
                if (companyNoName != null && !companyNoName.equals(ConstantsUtils.NULL)) {
                    companyNo = companyNoName.substring(0, companyNoName.indexOf(ConstantsUtils.TILTE));
                    companyName = companyNoName.substring(companyNoName.indexOf(ConstantsUtils.TILTE) + 1, companyNoName.indexOf(ConstantsUtils.APOSTROPE));
                    companyId = companyNoName.substring(companyNoName.indexOf(ConstantsUtils.APOSTROPE) + 1, companyNoName.length());
                    itemDetailsDto.setManufacturerNo(companyNo);
                    itemDetailsDto.setManufacturerName(companyName);
                    itemDetailsDto.setManufacturerId(companyId);
                }
                itemDetailsDto.setLabelerCode(String.valueOf(item[17] == null ? ConstantsUtils.EMPTY : item[17]));
                itemDetailsDto.setOrganizationKey(item[18] == null ? ConstantsUtils.EMPTY : String.valueOf(item[18]));
                if (item[19] != null) {
                    Date acquisitionDate = df.parse(CommonUtils.convertDateToString((Date) item[19]));
                    itemDetailsDto.setAcquisitionDate(acquisitionDate);
                }

                itemDetailsDto.setAuthorizedGeneric(item[20] == null ? ConstantsUtils.EMPTY : String.valueOf(item[20]));
                if (item[21] != null) {
                    Date authorizedSDate = df.parse(CommonUtils.convertDateToString((Date) item[21]));
                    itemDetailsDto.setAuthorizedGenericStartDate(authorizedSDate);
                }
                if (item[22] != null) {
                    Date authorizedEDate = df.parse(CommonUtils.convertDateToString((Date) item[22]));
                    itemDetailsDto.setAuthorizedGenericEndDate(authorizedEDate);
                }
                if (item[23] != null) {
                    Date saleDate = df.parse(CommonUtils.convertDateToString((Date) item[23]));
                    itemDetailsDto.setFirstSaleDate(saleDate);
                }
                itemDetailsDto.setItemTypeIndicator(item[24] == null ? ConstantsUtils.EMPTY : String.valueOf(item[24]));
                itemDetailsDto.setItemClass(item[25] == null ? ConstantsUtils.EMPTY : String.valueOf(item[25]));
                final HelperDTO itemTypeHelper = new HelperDTO();
                if (item[26] != null) {
                    final String itemTypeValue = CommonUtil.getDescriptionFromHelper(Integer.valueOf(String.valueOf(item[26])));

                    itemTypeHelper.setId(Integer.valueOf(String.valueOf(item[26])));
                    itemTypeHelper.setDescription(itemTypeValue);
                }
                itemDetailsDto.setItemTypeDdlb(itemTypeHelper);
                if (item[27] != null) {
                    Date terDate = df.parse(CommonUtils.convertDateToString((Date) item[27]));
                    itemDetailsDto.setMarketTerminationDate(terDate);
                }
                itemDetailsDto.setNewFormulationIndicator(item[28] == null ? ConstantsUtils.EMPTY : String.valueOf(item[28]));
                itemDetailsDto.setNewFormulation(item[29] == null ? ConstantsUtils.EMPTY : String.valueOf(item[29]));
                if (item[30] != null) {
                    Date formSDate = df.parse(CommonUtils.convertDateToString((Date) item[30]));
                    itemDetailsDto.setNewFormulationStartDate(formSDate);
                }
                if (item[31] != null) {
                    Date formEDate = df.parse(CommonUtils.convertDateToString((Date) item[31]));
                    itemDetailsDto.setNewFormulationEndDate(formEDate);
                }
                itemDetailsDto.setPediatricExclusiveIndicator(item[32] == null ? ConstantsUtils.EMPTY : String.valueOf(item[32]));
                if (item[33] != null) {
                    Date pediatricSDate = df.parse(CommonUtils.convertDateToString((Date) item[33]));
                    itemDetailsDto.setPediatricExclusiveStartDate(pediatricSDate);
                }
                if (item[34] != null) {
                    Date pediatricEDate = df.parse(CommonUtils.convertDateToString((Date) item[34]));
                    itemDetailsDto.setPediatricExclusiveEndDate(pediatricEDate);
                }
                itemDetailsDto.setClottingFactorIndicator(item[35] == null ? ConstantsUtils.EMPTY : String.valueOf(item[35]));
                if (item[36] != null) {
                    Date clottingSDate = df.parse(CommonUtils.convertDateToString((Date) item[36]));
                    itemDetailsDto.setClottingFactorStartDate(clottingSDate);
                }
                if (item[37] != null) {
                    Date clottingEDate = df.parse(CommonUtils.convertDateToString((Date) item[37]));
                    itemDetailsDto.setClottingFactorEndDate(clottingEDate);
                }
                if (item[38] != null) {
                    final String primaryUom = CommonUtil.getDescriptionFromHelper(Integer.valueOf(String.valueOf(item[38])));

                    itemDetailsDto.setPrimaryUom(primaryUom);
                }
                if (item[39] != null) {
                    final String secondaryUom = CommonUtil.getDescriptionFromHelper(Integer.valueOf(String.valueOf(item[39])));

                    itemDetailsDto.setSecondaryUom(secondaryUom);
                }
                itemDetailsDto.setShelfLife(item[40] == null ? ConstantsUtils.EMPTY : String.valueOf(item[40]));
                if (item[41] != null) {
                    final String shelfLifeType = CommonUtil.getDescriptionFromHelper(Integer.valueOf(String.valueOf(item[41])));

                    itemDetailsDto.setShelfLifeType(shelfLifeType);
                }
                itemDetailsDto.setDualPricingIndicator(item[42] == null ? ConstantsUtils.EMPTY : String.valueOf(item[42]));
                itemDetailsDto.setItemFamilyId(item[43] == null ? ConstantsUtils.EMPTY : String.valueOf(item[43]));
                itemDetailsDto.setAcquiredAmp(Double.valueOf(item[44] == null ? "0.0" : String.valueOf(item[44])));
                itemDetailsDto.setAcquiredBamp(Double.valueOf(item[45] == null ? "0.0" : String.valueOf(item[45])));
                itemDetailsDto.setObraBamp(Double.valueOf(item[46] == null ? "0.0" : String.valueOf(item[46])));
                itemDetailsDto.setDra(item[47] == null ? ConstantsUtils.EMPTY : String.valueOf(item[47]));
                itemDetailsDto.setDosesPerUnit(item[48] == null ? ConstantsUtils.EMPTY : String.valueOf(item[48]));
                itemDetailsDto.setDiscontinuationDate(CommonUtils.convertDateToString((Date) item[49]));
                itemDetailsDto.setLastLotExpirationDate(CommonUtils.convertDateToString((Date) item[50]));
                itemDetailsDto.setNdc9(item[53] == null ? ConstantsUtils.EMPTY : String.valueOf(item[53]));
                itemDetailsDto.setNdc8(item[54] == null ? ConstantsUtils.EMPTY : String.valueOf(item[54]));
                itemDetailsDto.setDisplayBrand(brandMaster.getDisplayBrand());
                if (item[56] != null) {
                    itemDetailsDto.setBaselineAmp(Double.valueOf(String.valueOf(item[56])));
                }
                if (item[58] != null) {
                    itemDetailsDto.setBaseYearCpi(Double.valueOf(item[58] == null ? "0.0" : String.valueOf(item[58])));
                }
                itemDetailsDto.setItemSystemId(Integer.valueOf(String.valueOf(item[0])));
                if (!resultList1.isEmpty()) {
                    final HistItemGroupDetails itemGroup = (HistItemGroupDetails) resultList1.get(Integer.valueOf(String.valueOf(item[0])));
                    itemDetailsDto.setCreatedBy(itemGroup.getCreatedBy());
                    itemDetailsDto.setModifiedBy(itemGroup.getModifiedBy());
                    Date createdDate = df.parse(CommonUtils.convertDateToString(itemGroup.getCreatedDate()));
                    itemDetailsDto.setCreatedDate(createdDate);
                    Date modifiedDate = df.parse(CommonUtils.convertDateToString(itemGroup.getModifiedDate()));
                    itemDetailsDto.setModifiedDate(modifiedDate);
                }

                itemDetailsList.add(itemDetailsDto);

            }
        }
        LOGGER.info("getCustomizedItemResults return List<ItemDetailsDTO> itemDetailsList=" + itemDetailsList.size());

        return itemDetailsList;
    }

    /**
     * Save item group.
     *
     * @param itemGroupForm the item group form
     * @param selectedItems the selected items
     * @param version the version
     * @param searchCriteria
     * @param sessionDTO
     * @return the string
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<Integer> saveItemGroup(final ErrorfulFieldGroup itemGroupForm, final List<ItemDetailsDTO> selectedItems, final int version, final String searchCriteria, final SessionDTO sessionDTO) throws SystemException, PortalException, Exception {
        List<Integer> sysIdList = new ArrayList<Integer>();
        LOGGER.info("saveItemGroup started with P1:CustomFieldGroup itemGroupForm and P2:List<ItemDetailsDTO> selectedItems size:" + selectedItems.size());
        final int userId = Integer.valueOf(sessionDTO.getUserId());
        final int itemGroupSystemId = sessionDTO.getSystemId();
        Boolean changeFlag = true;
        if (itemGroupSystemId != 0) {
            final DynamicQuery itemDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemGroupDetails.class);
            itemDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_GROUP_SYS_ID, itemGroupSystemId));
            final List<ItemGroupDetails> itemToRemove = dao.getItemGroupDetailsList(itemDynamicQuery);
            List<Integer> existingSystemIds = new ArrayList();
            List<Integer> currentSystemIds = new ArrayList();
            for (int i = 0; i < selectedItems.size(); i++) {
                currentSystemIds.add(selectedItems.get(i).getItemSystemId());
            }
            for (int i = 0; i < itemToRemove.size(); i++) {
                existingSystemIds.add(itemToRemove.get(i).getItemMasterSid());
            }
            existingSystemIds = new ArrayList<Integer>(new LinkedHashSet<Integer>(existingSystemIds));
            currentSystemIds = new ArrayList<Integer>(new LinkedHashSet<Integer>(currentSystemIds));
            Collections.sort(currentSystemIds);
            Collections.sort(existingSystemIds);
            if (currentSystemIds.equals(existingSystemIds)) {
                changeFlag = false;
            }
        }
        if (changeFlag) {

            int versionNo = ConstantsUtils.ZERO_NUM;
            ItemGroup itemGroup;
            HelperDTO helperDTO;
            if (itemGroupSystemId == ConstantsUtils.ZERO_NUM) {
                itemGroup = ItemGroupLocalServiceUtil.createItemGroup(0);
                itemGroup.setItemGroupNo(String.valueOf(itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_NO).getValue()).trim());
                itemGroup.setItemGroupName(String.valueOf(itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_NAME).getValue()).trim());
                itemGroup.setItemGroupDescription(String.valueOf(itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_DESC).getValue()).trim());
                helperDTO = (HelperDTO) itemGroupForm.getField(ConstantsUtils.COMPANY_DDLB).getValue();
                itemGroup.setCompanyMasterSid(helperDTO.getId());
                itemGroup.setCreatedDate(new Date());
                itemGroup.setModifiedDate(new Date());
                itemGroup.setCreatedBy(userId);
                itemGroup.setVersionNo(versionNo + 1);
                itemGroup.setItemFilter(searchCriteria);
                itemGroup = dao.addItemGroup(itemGroup);
            } else {
                itemGroup = dao.getItemGroup(itemGroupSystemId);
                versionNo = getExistingVersion(itemGroupSystemId);
                itemGroup.setItemGroupNo(String.valueOf(itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_NO).getValue()).trim());
                itemGroup.setItemGroupName(String.valueOf(itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_NAME).getValue()).trim());
                itemGroup.setItemGroupDescription(String.valueOf(itemGroupForm.getField(ConstantsUtils.ITEM_GROUP_DESC).getValue()).trim());
                helperDTO = (HelperDTO) itemGroupForm.getField(ConstantsUtils.COMPANY_DDLB).getValue();
                itemGroup.setCompanyMasterSid(helperDTO.getId());
                itemGroup.setVersionNo(versionNo + 1);
                itemGroup.setItemFilter(searchCriteria);
                itemGroup.setModifiedDate(new Date());
                itemGroup.setModifiedBy(userId);
                itemGroup = dao.updateItemGroup(itemGroup);
            }
            saveItemGroupDetails(itemGroup, selectedItems, sessionDTO);
            sysIdList.add(itemGroup.getItemGroupSid());
            sysIdList.add(itemGroup.getVersionNo());
            LOGGER.info("saveItemGroup return success");
            return sysIdList;

        }
        return new ArrayList<Integer>();

    }

    /**
     * Gets the item group info.
     *
     * @return the item group info
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public ItemGroupDTO getItemGroupInfo(final SessionDTO sessionDTO) throws SystemException, PortalException, Exception {
        LOGGER.info("getItemGroupInfo started");
        final int itemGroupSystemId = sessionDTO.getSystemId();
        final ItemGroupDTO itemGroupDTO = new ItemGroupDTO();
        final Map companyInfo = CommonUtils.getCompanyInfo();
        ItemGroup itemGroup;
        final HelperDTO helperDTO = new HelperDTO();
        itemGroup = dao.getItemGroup(itemGroupSystemId);
        itemGroupDTO.setItemGroupNo(itemGroup.getItemGroupNo());
        itemGroupDTO.setItemGroupName(itemGroup.getItemGroupName());
        itemGroupDTO.setItemGroupDesc(itemGroup.getItemGroupDescription());
        final String companyNoName = String.valueOf(companyInfo.get(String.valueOf(itemGroup.getCompanyMasterSid())));
        if (companyNoName != null && !companyNoName.equals(ConstantsUtils.NULL)) {
            final String companyNo = companyNoName.substring(0, companyNoName.indexOf(ConstantsUtils.TILTE));
            helperDTO.setId(itemGroup.getCompanyMasterSid());
            helperDTO.setDescription(companyNo);
            itemGroupDTO.setCompanyDdlb(helperDTO);
        }
        itemGroupDTO.setItemGroupSystemId(itemGroupSystemId);
        final String createdBy = String.valueOf(itemGroup.getCreatedBy());
        itemGroupDTO.setCreatedBy(createdBy);
        itemGroupDTO.setCreatedDate(df.parse(CommonUtils.convertDateToString(itemGroup.getCreatedDate())));
        final String modifiedBy = String.valueOf(itemGroup.getModifiedBy());
        itemGroupDTO.setModifiedBy(modifiedBy);
        itemGroupDTO.setModifiedDate(df.parse(CommonUtils.convertDateToString(itemGroup.getModifiedDate())));
        itemGroupDTO.setItemFilter(itemGroup.getItemFilter());
        LOGGER.info("getItemGroupInfo return ItemGroupDTO itemGroupDTO");

        return itemGroupDTO;
    }

    /**
     * Gets the history item group info.
     *
     * @param versionNo the version no
     * @return the history item group info
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public ItemGroupDTO getHistoryItemGroupInfo(final int versionNo, final SessionDTO sessionDTO) throws SystemException, PortalException, Exception {
        LOGGER.info("getItemGroupInfo started");
        final int itemGroupSystemId = sessionDTO.getSystemId();
        final ItemGroupDTO itemGroupDTO = new ItemGroupDTO();
        final Map companyInfo = CommonUtils.getCompanyInformation();

        HistItemGroup itemGroup;
        final DynamicQuery itemgroupDynamicQuery = DynamicQueryFactoryUtil.forClass(HistItemGroup.class);
        itemgroupDynamicQuery.add(RestrictionsFactoryUtil.eq("primaryKey.itemGroupSid", itemGroupSystemId));
        itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ne("primaryKey.actionFlag", "D"));
        itemgroupDynamicQuery.add(RestrictionsFactoryUtil.eq("primaryKey.versionNo", versionNo));
        final List<HistItemGroup> resultsList = HistItemGroupLocalServiceUtil.dynamicQuery(itemgroupDynamicQuery);
        HelperDTO helperDto;
        for (int i = 0; i < resultsList.size(); i++) {
            helperDto = new HelperDTO();
            itemGroup = resultsList.get(i);
            itemGroupDTO.setItemGroupNo(itemGroup.getItemGroupNo());
            itemGroupDTO.setItemGroupName(itemGroup.getItemGroupName());
            itemGroupDTO.setItemGroupDesc(itemGroup.getItemGroupDescription());

            final String companyNoName = String.valueOf(companyInfo.get(String.valueOf(itemGroup.getCompanyMasterSid())));
            if (companyNoName != null && !companyNoName.equals(ConstantsUtils.NULL)) {

                helperDto.setId(itemGroup.getCompanyMasterSid());
                helperDto.setDescription(companyNoName);

                itemGroupDTO.setCompanyDdlb(helperDto);
            }
            itemGroupDTO.setItemGroupSystemId(itemGroupSystemId);
            final String createdBy = String.valueOf(itemGroup.getCreatedBy());
            itemGroupDTO.setCreatedBy(createdBy);
            itemGroupDTO.setCreatedDate(df.parse(CommonUtils.convertDateToString(itemGroup.getCreatedDate())));
            final String modifiedBy = String.valueOf(itemGroup.getModifiedBy());
            itemGroupDTO.setModifiedBy(modifiedBy);
            itemGroupDTO.setModifiedDate(df.parse(CommonUtils.convertDateToString(itemGroup.getModifiedDate())));
            final DynamicQuery itemGroupQuery = DynamicQueryFactoryUtil.forClass(ItemGroup.class);
            itemGroupQuery.add(RestrictionsFactoryUtil.eq("itemGroupSid", itemGroupSystemId));
            itemGroupQuery.setProjection(ProjectionFactoryUtil.property("itemFilter"));
            List<String> itemFilter = ItemGroupLocalServiceUtil.dynamicQuery(itemGroupQuery);
            for (String string : itemFilter) {
                itemGroupDTO.setItemFilter(string);
            }
        }

        LOGGER.info("getItemGroupInfo return ItemGroupDTO itemGroupDTO");
        return itemGroupDTO;
    }

    /**
     * Gets the saved item details.
     *
     * @param itemGroupSystemId the item group system id
     * @return the saved item details
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<ItemDetailsDTO> getSavedItemDetails(final int itemGroupSystemId) throws SystemException, PortalException, Exception {
        List<ItemDetailsDTO> itemDetailsList;
        LOGGER.info("saveItemGroupDetails started with P1:int itemGroupSystemId=" + itemGroupSystemId);
        List<ItemGroupDetails> resultList;
        List<ItemMaster> itemsList;
        final List items = new ArrayList();
        final HashMap itemsMap = new HashMap();
        final DynamicQuery itemgroupDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemGroupDetails.class);
        itemgroupDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_GROUP_SYS_ID, itemGroupSystemId));
        resultList = dao.getItemGroupDetailsList(itemgroupDynamicQuery);
        for (int i = 0; i < resultList.size(); i++) {
            final ItemGroupDetails itemGroupDetail = resultList.get(i);
            itemsMap.put(itemGroupDetail.getItemMasterSid(), itemGroupDetail);
            items.add(itemGroupDetail.getItemMasterSid());
        }
        final DynamicQuery itemDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
        itemDynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.ITEM_MASTER_SID, items));
        itemsList = dao.getItemsList(itemDynamicQuery);
        itemDetailsList = getCustomizedItemResultsOnEdit(itemsList, itemsMap);
        LOGGER.info("saveItemGroupDetails return List<ItemDetailsDTO> itemDetailsList=" + itemDetailsList.size());
        return itemDetailsList;
    }

    /**
     * Gets the saved history item details.
     *
     * @param itemGroupSystemId the item group system id
     * @param version the version
     * @return the saved history item details
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<ItemDetailsDTO> getSavedHistoryItemDetails(final int itemGroupSystemId, final int version) throws SystemException, PortalException, Exception {
        List<ItemDetailsDTO> itemDetailsList = null;
        try {
            LOGGER.info("saveItemGroupDetails started with P1:int itemGroupSystemId=" + itemGroupSystemId);
            List<HistItemGroupDetails> resultList;
            final List items = new ArrayList();
            final HashMap itemsMap = new HashMap();

            final DynamicQuery itemgroupDynamicQuery = DynamicQueryFactoryUtil.forClass(HistItemGroupDetails.class);

            itemgroupDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_GROUP_SYS_ID, itemGroupSystemId));
            itemgroupDynamicQuery.add(RestrictionsFactoryUtil.eq("primaryKey.versionNo", version));
            itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ne("primaryKey.actionFlag", "D"));
            resultList = dao.getItemGroupsDetailsHistoryList(itemgroupDynamicQuery);

            for (int i = 0; i < resultList.size(); i++) {
                final HistItemGroupDetails itemGroupDetail = resultList.get(i);
                itemsMap.put(itemGroupDetail.getItemMasterSid(), itemGroupDetail);
                items.add(itemGroupDetail.getItemMasterSid());
            }

            String params[] = {String.valueOf(itemGroupSystemId), String.valueOf(version)};
            List list = dao.getItemMasterRecords(params);
            itemDetailsList = getHistoryCustomizedItemResults(list, itemsMap);
            LOGGER.info("saveItemGroupDetails return List<ItemDetailsDTO> itemDetailsList=" + itemDetailsList.size());
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return itemDetailsList;
    }

    /**
     * Delete item group.
     *
     * @param itemGroupSystemId the item group system id
     * @return the string
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public String deleteItemGroup(final int itemGroupSystemId) throws SystemException, PortalException, Exception {
        LOGGER.info("deleteItemGroup started with P1:int itemGroupSystemId=" + itemGroupSystemId);
        ItemGroup itemGroup;
        String deletedItemGroupName;
        List<ItemGroupDetails> itemsList;
        int count;
        final DynamicQuery projectionMasterQuery = DynamicQueryFactoryUtil.forClass(ProjectionMaster.class);

        projectionMasterQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_GROUP_SYS_ID, String.valueOf(itemGroupSystemId)));
        count = dao.getProjectionCount(projectionMasterQuery);
        // delete item details
        if (count > 0) {
            MessageBox.showPlain(Icon.ERROR, "Delete", "Cannot Delete the Item group which are already associated with existing projection", ButtonId.OK);
            return StringUtils.EMPTY;
        } else {
            final DynamicQuery itemDetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemGroupDetails.class);
            itemDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_GROUP_SYS_ID, itemGroupSystemId));
            itemsList = dao.getItemGroupDetailsList(itemDetailsDynamicQuery);
            if (itemsList != null && !itemsList.isEmpty()) {
                for (int i = 0; i < itemsList.size(); i++) {
                    final ItemGroupDetails itemDetails = itemsList.get(i);
                    dao.deleteItemGroupDetails(itemDetails);
                }
            }
            itemGroup = dao.deleteItemGroup(itemGroupSystemId);
            deletedItemGroupName = itemGroup.getItemGroupName();
            LOGGER.info("deleteItemGroup return String deletedItemGroupName=" + deletedItemGroupName);
            return deletedItemGroupName;
        }
    }

    /**
     * Gets the existing itemgroup names.
     *
     * @return the existing itemgroup names
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public Map getExistingItemgroupNames() throws SystemException, PortalException, Exception {
        LOGGER.info("getExistingItemgroupNames started");
        final HashMap results = new HashMap();
        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemGroup.class);
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_GROUP_SYS_ID));
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_GROUP_NAME));
        companyDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
        List<Object[]> resultList;

        resultList = dao.getItemGroupsList(companyDynamicQuery);
        for (int i = 0; i < resultList.size(); i++) {
            final Object[] obj = resultList.get(i);
            results.put(String.valueOf(obj[0]), String.valueOf(obj[1]));
        }
        LOGGER.info("getExistingItemgroupNames return HashMap results");
        return results;

    }

    /**
     * Gets the existing version.
     *
     * @param itemGroupSystemId the item group system id
     * @return the existing version
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public int getExistingVersion(final int itemGroupSystemId) throws SystemException, PortalException, Exception {
        LOGGER.info("getExistingVersion started");
        int version = ConstantsUtils.ZERO_NUM;
        final DynamicQuery itemGroupHistoryDynamicQuery = DynamicQueryFactoryUtil.forClass(HistItemGroup.class);
        itemGroupHistoryDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.ITEM_GROUP_SYS_ID, itemGroupSystemId));
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.VERSION_NO));
        itemGroupHistoryDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));

        final List<Integer> finalList = new ArrayList<Integer>();
        final List<Integer> historyList = dao.getItemGroupsHistoryList(itemGroupHistoryDynamicQuery);

        finalList.addAll(historyList);
        final int size = finalList.size();
        if (size > ConstantsUtils.ZERO_NUM) {
            Collections.sort(finalList);
            version = finalList.get(size - 1);
        }
        LOGGER.info("getExistingCompanyGroupNames return ");
        return version;
    }

    /**
     * Gets the company master list.
     *
     * @param query the query
     * @return the company master list
     * @throws SystemException the system exception
     */
    public List<String> getCompanyMasterList(final DynamicQuery query) throws SystemException {
        return dao.getCompanyMasterList(query);
    }

    /**
     * Save Item Group Details.
     *
     * @param itemGroup the item group
     * @param selectedItems the selected items
     * @return the string
     */
    private String saveItemGroupDetails(final ItemGroup itemGroup, final List<ItemDetailsDTO> selectedItems, final SessionDTO sessionDTO) {
        try {
            LOGGER.info("saveItemGroupDetails started with P1:ItemGroup itemGroup and P2:List<ItemDetailsDTO> selectedItems size:" + selectedItems.size());
            final Date date = new Date();
            final int itemGroupSystemId = itemGroup.getItemGroupSid();
            final int itemGroupSessionId = sessionDTO.getSystemId();
            final int userId = Integer.valueOf(sessionDTO.getUserId());
            final DynamicQuery itemDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemGroupDetails.class);
            itemDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_GROUP_SYS_ID, itemGroupSessionId));
            final List<ItemGroupDetails> itemToRemove = dao.getItemGroupDetailsList(itemDynamicQuery);
            final HashMap savedItemsMap = new HashMap();
            for (final ItemGroupDetails itemDetail : itemToRemove) {
                savedItemsMap.put(itemDetail.getItemMasterSid(), itemDetail);
            }
            for (final ItemDetailsDTO item : selectedItems) {
                ItemGroupDetails itemGroupDetails = ItemGroupDetailsLocalServiceUtil.createItemGroupDetails(0);
                itemGroupDetails.setItemGroupSid(itemGroupSystemId);
                itemGroupDetails.setItemMasterSid(item.getItemSystemId());
                itemGroupDetails.setVersionNo(itemGroup.getVersionNo());
                if (itemGroupSessionId == ConstantsUtils.ZERO_NUM) {// to ensure that
                    itemGroupDetails.setCreatedBy(userId);
                    itemGroupDetails.setCreatedDate(date);
                    itemGroupDetails.setModifiedDate(date);
                    itemGroupDetails = dao.addItemGroupDetails(itemGroupDetails);
                } else {
                    // Group ADD or EDIT
                    if (savedItemsMap.containsKey(item.getItemSystemId())) {
                        itemGroupDetails = (ItemGroupDetails) savedItemsMap.get(item.getItemSystemId());
                        itemGroupDetails.setModifiedDate(date);
                        itemGroupDetails.setModifiedBy(userId);
                        itemGroupDetails.setVersionNo(itemGroup.getVersionNo());
                        itemGroupDetails = dao.updateItemGroupDetails(itemGroupDetails);
                        savedItemsMap.remove(item.getItemSystemId());
                    } else {// to save new Item in existing item group
                        itemGroupDetails.setCreatedBy(userId);
                        itemGroupDetails.setCreatedDate(date);
                        itemGroupDetails.setModifiedDate(date);
                        itemGroupDetails.setVersionNo(itemGroup.getVersionNo());
                        itemGroupDetails = dao.addItemGroupDetails(itemGroupDetails);
                    }
                }
            }
            if (!savedItemsMap.isEmpty()) {
                final List<ItemGroupDetails> itemListToDelete = new ArrayList<ItemGroupDetails>(savedItemsMap.values());
                for (final ItemGroupDetails itemToDelete : itemListToDelete) {
                    dao.deleteItemGroupDetails(itemToDelete);
                }
            }
            LOGGER.info("saveItemGroupDetails return success");
        } catch (Exception ex) {

            LOGGER.error(ex);
        }
        return ConstantsUtils.SUCCESS;
    }

    public String getFromHelperTable(int id) {
        String desc = StringUtils.EMPTY;
        try {
            HelperTable table = HelperTableLocalServiceUtil.getHelperTable(id);
            desc = table.getDescription();
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return desc;
    }

    public Udcs getFromUDC(int id) {
        Udcs udc = UdcsLocalServiceUtil.createUdcs(0);
        try {
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(Udcs.class);
            query.add(RestrictionsFactoryUtil.eq("masterSid", id));
            query.add(RestrictionsFactoryUtil.eq("masterType", "ITEM_MASTER"));
            udc = UdcsLocalServiceUtil.getUdcs(id);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return udc;
    }

    public List<ItemDetailsDTO> getCustomizedItemResultsOnEdit(final List<ItemMaster> resultList, final Map resultList1) throws SystemException, PortalException, Exception {
        LOGGER.info("getCustomizedItemResultsOnEdit started with P1:List<ItemMaster> resultList" + resultList.size() + "and P2:HashMap resultList1 size");

        final List<ItemDetailsDTO> itemDetailsList = new ArrayList<ItemDetailsDTO>();
        final Map companyInfo = CommonUtils.getCompanyInfo();
        if (!resultList.isEmpty()) {
            for (int i = 0; i < resultList.size(); i++) {
                ItemMaster master = resultList.get(i);
                final ItemDetailsDTO itemDetailsDto = new ItemDetailsDTO();
                String companyNoName;
                String companyNo;
                String companyName;
                String companyId;
                if (master.getItemId() == null) {
                    itemDetailsDto.setItemId(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setItemId(master.getItemId());
                }
                if (master.getItemNo() == null) {
                    itemDetailsDto.setItemNo(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setItemNo(master.getItemNo());
                }
                if (master.getItemCode() == null) {
                    itemDetailsDto.setItemCode(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setItemCode(master.getItemCode());
                }
                if (master.getItemName() == null) {
                    itemDetailsDto.setItemName(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setItemName(master.getItemName());
                }
                if (master.getItemDesc() == null) {
                    itemDetailsDto.setItemDesc(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setItemDesc(master.getItemDesc());
                }
                if (master.getItemStartDate() != null) {
                    itemDetailsDto.setItemStartDate(df.parse(CommonUtils.convertDateToString(master.getItemStartDate())));
                }
                if (master.getItemEndDate() != null) {
                    itemDetailsDto.setItemEndDate(df.parse(CommonUtils.convertDateToString(master.getItemEndDate())));
                }
                if (getFromHelperTable(master.getItemStatus()) == null || master.getItemStatus() == 0) {
                    itemDetailsDto.setItemStatus(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setItemStatus(String.valueOf(master.getItemStatus()));
                }
                if (getFromHelperTable(master.getTherapeuticClass()) == null || master.getTherapeuticClass() == 0) {
                    itemDetailsDto.setTherapeuticClass(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setTherapeuticClass(getFromHelperTable(master.getTherapeuticClass()));
                }

                DynamicQuery brandQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);
                brandQuery.add(RestrictionsFactoryUtil.eq("brandMasterSid", master.getBrandMasterSid()));
                List<BrandMaster> bm = BrandMasterLocalServiceUtil.dynamicQuery(brandQuery);
                BrandMaster brandMaster = bm.get(0);
                if (brandMaster.getBrandName() == null) {
                    itemDetailsDto.setBrand(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setBrand(brandMaster.getBrandName());
                }
                if (getFromHelperTable(master.getForm()) == null || master.getForm() == 0) {
                    itemDetailsDto.setForm(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setForm(getFromHelperTable(master.getForm()));
                }
                if (master.getStrength() == 0) {
                    itemDetailsDto.setStrength(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setStrength(String.valueOf(master.getStrength()));
                }
                if (master.getPackageSizeCode() == null) {
                    itemDetailsDto.setPackageSizeCode(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setPackageSizeCode(master.getPackageSizeCode());
                }
                if (master.getPackageSizeIntroDate() != null) {
                    itemDetailsDto.setPackageSizeIntroDate(master.getPackageSizeIntroDate());
                }

                itemDetailsDto.setUpps(master.getUpps());

                if (master.getLabelerCode() == null) {
                    itemDetailsDto.setLabelerCode(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setLabelerCode(master.getLabelerCode());
                }
                if (master.getOrganizationKey() == null) {
                    itemDetailsDto.setOrganizationKey(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setOrganizationKey(master.getOrganizationKey());
                }
                if (master.getAcquisitionDate() != null) {
                    itemDetailsDto.setAcquisitionDate(df.parse(CommonUtils.convertDateToString(master.getAcquisitionDate())));
                }
                if (master.getAuthorizedGeneric() == null) {
                    itemDetailsDto.setAuthorizedGeneric(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setAuthorizedGeneric(master.getAuthorizedGeneric());
                }
                if (master.getAuthorizedGenericStartDate() != null) {
                    itemDetailsDto.setAuthorizedGenericStartDate(df.parse(CommonUtils.convertDateToString(master.getAuthorizedGenericStartDate())));
                }
                if (master.getAuthorizedGenericEndDate() != null) {
                    itemDetailsDto.setAuthorizedGenericEndDate(df.parse(CommonUtils.convertDateToString(master.getAuthorizedGenericEndDate())));
                }
                if (master.getFirstSaleDate() != null) {
                    itemDetailsDto.setFirstSaleDate(df.parse(CommonUtils.convertDateToString(master.getFirstSaleDate())));
                }
                if (master.getItemTypeIndication() == null) {
                    itemDetailsDto.setItemTypeIndicator(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setItemTypeIndicator(master.getItemTypeIndication());
                }

                if (getFromHelperTable(master.getItemClass()) == null || master.getItemClass() == 0) {
                    itemDetailsDto.setItemClass(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setItemClass(getFromHelperTable(master.getItemClass()));
                }
                if (getFromHelperTable(master.getItemType()) == null || master.getItemType() == 0) {
                    itemDetailsDto.setItemType(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setItemType(getFromHelperTable(master.getItemType()));
                }
                if (master.getMarketTerminationDate() != null) {
                    itemDetailsDto.setMarketTerminationDate(df.parse(CommonUtils.convertDateToString(master.getMarketTerminationDate())));
                }
                if (master.getNewFormulationIndicator() == null) {
                    itemDetailsDto.setNewFormulationIndicator(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setNewFormulationIndicator(master.getNewFormulationIndicator());
                }
                if (master.getNewFormulation() == null) {
                    itemDetailsDto.setNewFormulation(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setNewFormulation(master.getNewFormulation());
                }
                if (master.getNewFormulationStartDate() != null) {
                    itemDetailsDto.setNewFormulationStartDate(df.parse(CommonUtils.convertDateToString(master.getNewFormulationStartDate())));
                }
                if (master.getNewFormulationEndDate() != null) {
                    itemDetailsDto.setNewFormulationEndDate(df.parse(CommonUtils.convertDateToString(master.getNewFormulationEndDate())));
                }
                if (master.getPediatricExclusiveIndicator() == null) {
                    itemDetailsDto.setPediatricExclusiveIndicator(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setPediatricExclusiveIndicator(master.getPediatricExclusiveIndicator());
                }
                if (master.getPediatricExclusiveStartDate() != null) {
                    itemDetailsDto.setPediatricExclusiveStartDate(df.parse(CommonUtils.convertDateToString(master.getPediatricExclusiveStartDate())));

                    if (master.getPediatricExclusiveEndDate() != null) {
                        itemDetailsDto.setPediatricExclusiveEndDate(df.parse(CommonUtils.convertDateToString(master.getPediatricExclusiveEndDate())));
                    }
                }
                if (master.getClottingFactorIndicator() == null) {
                    itemDetailsDto.setClottingFactorIndicator(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setClottingFactorIndicator(master.getClottingFactorIndicator());
                }
                if (master.getClottingFactorStartDate() != null) {
                    itemDetailsDto.setClottingFactorStartDate(df.parse(CommonUtils.convertDateToString(master.getClottingFactorStartDate())));
                }
                if (master.getClottingFactorEndDate() != null) {
                    itemDetailsDto.setClottingFactorEndDate(df.parse(CommonUtils.convertDateToString(master.getClottingFactorEndDate())));
                }

                if (getFromHelperTable(master.getPrimaryUom()) == null || master.getPrimaryUom() == 0) {
                    itemDetailsDto.setPrimaryUom(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setPrimaryUom(getFromHelperTable(master.getPrimaryUom()));
                }
                if (getFromHelperTable(master.getSecondaryUom()) == null || master.getSecondaryUom() == 0) {
                    itemDetailsDto.setSecondaryUom(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setSecondaryUom(getFromHelperTable(master.getSecondaryUom()));
                }
                if (master.getShelfLife() == null) {
                    itemDetailsDto.setShelfLife(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setShelfLife(master.getShelfLife());
                }
                if (getFromHelperTable(master.getShelfLifeType()) == null || master.getShelfLifeType() == 0) {
                    itemDetailsDto.setShelfLifeType(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setShelfLifeType(getFromHelperTable(master.getShelfLifeType()));
                }
                if (master.getDualPricingIndicator() == null) {
                    itemDetailsDto.setDualPricingIndicator(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setDualPricingIndicator(master.getDualPricingIndicator());
                }
                if (master.getItemFamilyId() == null) {
                    itemDetailsDto.setItemFamilyId(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setItemFamilyId(master.getItemFamilyId());
                }
                Udcs udc = getFromUDC(master.getItemMasterSid());
                if (getFromHelperTable(udc.getUdc1()) == null || udc.getUdc1() == 0) {
                    itemDetailsDto.setUdc1(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setUdc1(getFromHelperTable(udc.getUdc1()));
                }
                if (getFromHelperTable(udc.getUdc2()) == null || udc.getUdc2() == 0) {
                    itemDetailsDto.setUdc2(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setUdc2(getFromHelperTable(udc.getUdc2()));
                }
                if (getFromHelperTable(udc.getUdc3()) == null || udc.getUdc3() == 0) {
                    itemDetailsDto.setUdc3(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setUdc3(getFromHelperTable(udc.getUdc3()));
                }
                if (getFromHelperTable(udc.getUdc4()) == null || udc.getUdc4() == 0) {
                    itemDetailsDto.setUdc4(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setUdc4(getFromHelperTable(udc.getUdc4()));
                }
                if (getFromHelperTable(udc.getUdc5()) == null || udc.getUdc5() == 0) {
                    itemDetailsDto.setUdc5(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setUdc5(getFromHelperTable(udc.getUdc5()));
                }
                if (getFromHelperTable(udc.getUdc6()) == null || udc.getUdc6() == 0) {
                    itemDetailsDto.setUdc6(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setUdc6(getFromHelperTable(udc.getUdc6()));
                }
                if (String.valueOf(master.getAcquiredAmp()) == null) {
                    itemDetailsDto.setAcquiredAmp(0.0);
                } else {
                    itemDetailsDto.setAcquiredAmp(master.getAcquiredAmp());
                }
                if (String.valueOf(master.getAcquiredBamp()) == null) {
                    itemDetailsDto.setAcquiredBamp(0.0);
                } else {
                    itemDetailsDto.setAcquiredBamp(master.getAcquiredBamp());
                }
                if (String.valueOf(master.getObraBamp()) == null) {
                    itemDetailsDto.setObraBamp(0.0);
                } else {
                    itemDetailsDto.setObraBamp(master.getObraBamp());
                }
                if (String.valueOf(master.getDra()) == null) {
                    itemDetailsDto.setDra(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setDra(String.valueOf(master.getDra()));
                }
                if (master.getDosesPerUnit() == null) {
                    itemDetailsDto.setDosesPerUnit(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setDosesPerUnit(master.getDosesPerUnit());
                }
                if (master.getDiscontinuationDate() == null) {
                    itemDetailsDto.setDiscontinuationDate(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setDiscontinuationDate(CommonUtils.convertDateToString(master.getDiscontinuationDate()));
                }
                if (master.getLastLotExpirationDate() == null) {
                    itemDetailsDto.setLastLotExpirationDate(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setLastLotExpirationDate(CommonUtils.convertDateToString(master.getLastLotExpirationDate()));
                }
                if (master.getNdc9() == null) {
                    itemDetailsDto.setNdc9(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setNdc9(master.getNdc9());
                }
                if (master.getNdc8() == null) {
                    itemDetailsDto.setNdc8(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setNdc8(master.getNdc8());
                }
                if (brandMaster.getDisplayBrand() == null) {
                    itemDetailsDto.setDisplayBrand(ConstantsUtils.EMPTY);
                } else {
                    itemDetailsDto.setDisplayBrand(brandMaster.getDisplayBrand());
                }

                itemDetailsDto.setBaselineAmp(master.getBaselineAmp());

                itemDetailsDto.setBaseYearCpi(master.getBaseCpi());

                if (master.getManufacturerId() == null) {
                    itemDetailsDto.setManufacturerId((ConstantsUtils.EMPTY));
                } else {
                    itemDetailsDto.setManufacturerId(master.getManufacturerId());
                    companyNoName = String.valueOf(companyInfo.get(itemDetailsDto.getManufacturerId()));
                    if (companyNoName != null && !companyNoName.equals(ConstantsUtils.NULL)) {
                        companyNo = companyNoName.substring(0, companyNoName.indexOf(ConstantsUtils.TILTE));
                        companyName = companyNoName.substring(companyNoName.indexOf(ConstantsUtils.TILTE) + 1, companyNoName.indexOf(ConstantsUtils.APOSTROPE));
                        companyId = companyNoName.substring(companyNoName.indexOf(ConstantsUtils.APOSTROPE) + 1, companyNoName.length());
                        itemDetailsDto.setManufacturerNo(companyNo);
                        itemDetailsDto.setManufacturerName(companyName);
                        itemDetailsDto.setManufacturerId(companyId);
                    }
                }

                itemDetailsDto.setItemSystemId(master.getItemMasterSid());
                itemDetailsList.add(itemDetailsDto);

                LOGGER.info("getCustomizedItemResultsOnEdit return List<ItemDetailsDTO> itemDetailsList=" + itemDetailsList.size());
            }
        }
        return itemDetailsList;
    }

    public boolean checkSearchCriteria(final ErrorfulFieldGroup binder) {
        boolean isvalid = false;
        for (Object object : binder.getFields()) {
            if (object != null && object instanceof TextField && ((TextField) object).isVisible()) {
                if (StringUtils.isNotBlank(((TextField) object).getValue())) {
                    isvalid = true;
                    break;
                }
            } else if (object != null && object instanceof ComboBox && ((ComboBox) object).isVisible()) {

                if (!ConstantUtil.SELECT_ONE.equals(((ComboBox) object).getValue().toString())) {
                    isvalid = true;
                    break;
                }
            }
        }
        return isvalid;
    }

}
