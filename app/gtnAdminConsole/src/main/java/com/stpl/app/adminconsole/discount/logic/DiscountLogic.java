package com.stpl.app.adminconsole.discount.logic;

import com.stpl.app.adminconsole.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.dao.DiscountLogicDAO;
import com.stpl.app.adminconsole.dao.FileManagementLogicDAO;
import com.stpl.app.adminconsole.dao.impl.DiscountLogicDAOImpl;
import com.stpl.app.adminconsole.dao.impl.FileManagementLogicDAOImpl;
import java.util.ArrayList;
import java.util.List;
import com.stpl.app.adminconsole.discount.dto.DiscountSearchDTO;
import com.stpl.app.model.DeductionGroup;
import com.stpl.app.model.DeductionGroupDetails;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.RsModel;
import com.stpl.app.service.DeductionGroupLocalServiceUtil;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.service.DeductionGroupDetailsLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class DiscountLogic.
 */
public class DiscountLogic {

    private DiscountLogicDAO DAO = new DiscountLogicDAOImpl();
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DiscountLogic.class);
    /**
     * The dao.
     */
    final private static FileManagementLogicDAO FMDAO = new FileManagementLogicDAOImpl();

    static HashMap<String, String> columnNames = new HashMap<String, String>();

    SessionDTO sessionDTO;

    public DiscountLogic(SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
    }

    public DiscountLogic() {

    }

    public List<DiscountSearchDTO> getSearchResults(final DiscountSearchDTO searchDto) throws SystemException {
        List<DiscountSearchDTO> searchList = new ArrayList<DiscountSearchDTO>();
        String deductionQuery = getDeductionQuery(searchDto);
        List<Object[]> resulList = (List<Object[]>) FMDAO.executeSelectQuery(deductionQuery, null, null);
        if (!resulList.isEmpty()) {
            for (int i = 0; i < resulList.size(); i++) {
                Object[] obj = resulList.get(i);
                DiscountSearchDTO dto = new DiscountSearchDTO();
                dto.setRebateScheduleSid(obj[NumericConstants.SEVEN] != null ? Integer.parseInt(String.valueOf(obj[NumericConstants.SEVEN])) : 0);
                dto.setRebateName(obj[1] != null ? String.valueOf(obj[1]) : ConstantsUtils.EMPTY);
                dto.setRebateNo(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO]) : ConstantsUtils.EMPTY);

                dto.setRebateScheduleType(obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE]) : ConstantsUtils.EMPTY);

                dto.setRebateProgramType(obj[NumericConstants.FOUR] != null ? String.valueOf(obj[NumericConstants.FOUR]) : ConstantsUtils.EMPTY);

                dto.setRebateScheduleCategory(obj[NumericConstants.FIVE] == null || "-Select One-".equals(obj[NumericConstants.FIVE]) ? ConstantsUtils.EMPTY : String.valueOf(obj[NumericConstants.FIVE]));

                dto.setRebatePlanLevel(obj[NumericConstants.SIX] == null || "-Select One-".equals(obj[NumericConstants.SIX]) ? ConstantsUtils.EMPTY : String.valueOf(obj[NumericConstants.SIX]));
                searchList.add(dto);
            }
        }
        return searchList;
    }

    /**
     * Gets the search results.
     *
     * @param Dto the discount search dto
     * @return the search results
     * @throws SystemException the system exception
     */
    public List<SearchResultsDTO> getDeductionGroup(final ErrorfulFieldGroup binder, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet) throws SystemException {
        List<DeductionGroup> list = new ArrayList<DeductionGroup>();
        List<SearchResultsDTO> searchList = new ArrayList<SearchResultsDTO>();

        final DynamicQuery discountgroupDynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionGroup.class);
        if (binder.getField(ConstantsUtils.TEXT1).getValue() != null && StringUtils.isNotEmpty(binder.getField(ConstantsUtils.TEXT1).getValue().toString())) {
            final String discountName = String.valueOf(binder.getField(ConstantsUtils.TEXT1).getValue());
            final String deductionGroupName = discountName.replace("*", "%");
            discountgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(CommonUtil.DEDUCTION_GROUP_NAME, deductionGroupName));
        }
        if (binder.getField(ConstantsUtils.TEXT2).getValue() != null && StringUtils.isNotEmpty(binder.getField(ConstantsUtils.TEXT2).getValue().toString())) {
            final String discountNo = String.valueOf(binder.getField(ConstantsUtils.TEXT2).getValue());
            final String deductionGroupNo = discountNo.replace("*", "%");
            discountgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(CommonUtil.DEDUCTION_GROUP_NO, deductionGroupNo));
        }
        if (binder.getField(ConstantsUtils.TEXT3).getValue() != null && StringUtils.isNotEmpty(binder.getField(ConstantsUtils.TEXT3).getValue().toString())) {
            final String discountDesc = String.valueOf(binder.getField(ConstantsUtils.TEXT3).getValue());
            final String deductionGroupDesc = discountDesc.replace("*", "%");
            discountgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(CommonUtil.DEDUCTION_GROUP_DESCRIPTION, deductionGroupDesc));
        }

        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    if (CommonUtil.DISCOUNT_NAME.equals(stringFilter.getPropertyId())) {
                        discountgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(CommonUtil.DEDUCTION_GROUP_NAME, filterString));
                    }
                    if (CommonUtil.DISCOUNT_NO.equals(stringFilter.getPropertyId())) {
                        discountgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(CommonUtil.DEDUCTION_GROUP_NO, filterString));
                    }
                    if (CommonUtil.DISCOUNT_DESC.equals(stringFilter.getPropertyId())) {
                        discountgroupDynamicQuery.add(RestrictionsFactoryUtil.ilike(CommonUtil.DEDUCTION_GROUP_DESCRIPTION, filterString));
                    }

                } else if (filter instanceof Compare) {
                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    if (ConstantsUtils.VERSION_NO.equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
                        int value = Integer.valueOf(String.valueOf(compare.getValue()));
                        if (operation.GREATER.toString().equalsIgnoreCase(operation.name())) {
                            discountgroupDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.VERSION_NO, value));
                        } else if (operation.LESS.toString().equalsIgnoreCase(operation.name())) {
                            discountgroupDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.VERSION_NO, value));
                        } else if (operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
                            discountgroupDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.VERSION_NO, value));
                        }
                    }
                } else if (filter instanceof Between) {

                    Between stringFilter = (Between) filter;
                    Date filterString = (Date) stringFilter.getStartValue();
                    Date filterString1 = (Date) stringFilter.getEndValue();

                    if (ConstantsUtils.CREATED_DATE.equals(stringFilter.getPropertyId())) {
                        discountgroupDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.CREATED_DATE, filterString));
                        discountgroupDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.CREATED_DATE, filterString1));
                    }
                }
            }
        }
        loadColumnName();
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn orderByColumn = (SortByColumn) iterator.next();

                if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                    discountgroupDynamicQuery.addOrder(OrderFactoryUtil.asc(getDBColumnName(orderByColumn.getName())));
                } else {
                    discountgroupDynamicQuery.addOrder(OrderFactoryUtil.desc(getDBColumnName(orderByColumn.getName())));
                }
            }
        }

        list = DeductionGroupLocalServiceUtil.dynamicQuery(discountgroupDynamicQuery);
        searchList = getcustomizedResults(list);
        return searchList;
    }

    public static String getDBColumnName(String visibleColumnName) {
        return columnNames.get(visibleColumnName);
    }

    public static HashMap<String, String> loadColumnName() {

        columnNames.put(CommonUtil.DISCOUNT_NAME, CommonUtil.DEDUCTION_GROUP_NAME);
        columnNames.put(CommonUtil.DISCOUNT_NO, CommonUtil.DEDUCTION_GROUP_NO);
        columnNames.put(CommonUtil.DISCOUNT_DESC, CommonUtil.DEDUCTION_GROUP_DESCRIPTION);
        columnNames.put(CommonUtil.VERSION_NO, CommonUtil.VERSION_NO);
        columnNames.put("createdBy", "createdBy");
        columnNames.put("createdDate", "createdDate");

        return columnNames;
    }

    /**
     * Gets the search results.
     *
     * @param Dto the discount search dto
     * @return the search results
     * @throws SystemException the system exception
     */
    public List<DiscountSearchDTO> getDeductionGroupDetails(final int deductionGroupSid) throws SystemException, PortalException {
        List<DiscountSearchDTO> itemDetailsList;
        LOGGER.debug("saveItemGroupDetails started with P1:int deductionGroupSid=" + deductionGroupSid);
        List<DeductionGroupDetails> resultList;
        List<RsModel> itemsList;
        final List items = new ArrayList();
        final HashMap itemsMap = new HashMap();
        final DynamicQuery deductiongroupDynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionGroupDetails.class);
        deductiongroupDynamicQuery.add(RestrictionsFactoryUtil.eq(CommonUtil.DEDUCTION_GROUP_SID, deductionGroupSid));
        resultList = DAO.getDeductionGroupsDetailsList(deductiongroupDynamicQuery);

        for (int i = 0; i < resultList.size(); i++) {
            final DeductionGroupDetails deductionGroupDetail = resultList.get(i);
            itemsMap.put(deductionGroupDetail.getRsModelSid(), deductionGroupDetail);
            items.add(deductionGroupDetail.getRsModelSid());
        }
        final DynamicQuery rsModelDynamicQuery = DynamicQueryFactoryUtil.forClass(RsModel.class);
        if (!items.isEmpty()) {
            rsModelDynamicQuery.add(RestrictionsFactoryUtil.in("rsModelSid", items));
        }
        itemsList = DAO.getRebateScheduleList(rsModelDynamicQuery);
        itemDetailsList = getCustomizedDetailsResults(itemsList, itemsMap);
        LOGGER.debug("saveDeductionGroupDetails return List<ItemDetailsDTO> deductionDetailsList=" + itemDetailsList.size());
        return itemDetailsList;
    }

    /**
     * Gets the search results.
     *
     * @param discountSearchDTO the discount search dto
     * @return the search results
     * @throws SystemException the system exception
     */
    public List<DiscountSearchDTO> getSearchcustomizedResults(final List<RsModel> resultsList) throws SystemException {
        LOGGER.debug("Entering insdie getSearchcustomizedResults method with resultList");
        final List<DiscountSearchDTO> list = new ArrayList<DiscountSearchDTO>();
        for (int i = 0; i < resultsList.size(); i++) {
            try {
                DiscountSearchDTO dto = new DiscountSearchDTO();
                final RsModel rebate = resultsList.get(i);
                dto.setRebateScheduleSid(rebate.getRsModelSid());
                dto.setRebateName(rebate.getRsName());
                dto.setRebateNo(rebate.getRsNo());
                final String rsType = CommonUtil.getDescriptionFromHelper(rebate.getRsType());
                dto.setRebateScheduleType(rsType);
                final String rsProgramType = CommonUtil.getDescriptionFromHelper(rebate.getRebateProgramType());
                dto.setRebateProgramType(rsProgramType);
                final String rscategory = CommonUtil.getDescriptionFromHelper(rebate.getRsCategory());
                dto.setRebateScheduleCategory(rscategory);
                if (rebate.getRebatePlanLevel()!=null&&!rebate.getRebatePlanLevel().equals(ConstantsUtils.EMPTY)) {
                    final String level = CommonUtil.getDescriptionFromHelper(Integer.valueOf(rebate.getRebatePlanLevel()));
                    dto.setRebatePlanLevel(level);
                } else {
                    dto.setRebatePlanLevel(ConstantsUtils.EMPTY);
                }
                list.add(dto);
            } catch (PortalException ex) {
                LOGGER.error(ex);
            }
        }
        LOGGER.debug("getSearchcustomizedResults method Ends");
        return list;
    }

    /**
     * Gets the search results.
     *
     * @param discountSearchDTO the discount search dto
     * @return the search results
     * @throws SystemException the system exception
     */
    public List<SearchResultsDTO> getcustomizedResults(final List<DeductionGroup> resultsList) throws SystemException {
        LOGGER.debug("Entering insdie getcustomizedResults method with resultList");
        final List<SearchResultsDTO> list = new ArrayList<SearchResultsDTO>();
        final Map<String, String> userInfoMap = CommonUtil.getCreatedByUser();
        for (int i = 0; i < resultsList.size(); i++) {
            SearchResultsDTO dto = new SearchResultsDTO();
            final DeductionGroup results = resultsList.get(i);
            dto.setSystemID(String.valueOf(results.getDeductionGroupSid()));
            dto.setDeductionGroupSid(results.getDeductionGroupSid());
            dto.setDiscountName(results.getDeductionGroupName());
            dto.setDiscountNo(results.getDeductionGroupNo());
            dto.setDiscountDesc(results.getDeductionGroupDescription());
            dto.setVersion(results.getVersionNo());
            dto.setCreatedBy(userInfoMap.get(String.valueOf(results.getCreatedBy())));
            dto.setCreatedDate(results.getCreatedDate());
            list.add(dto);
        }
        LOGGER.debug("getcustomizedResults method Ends");
        return list;
    }

    /**
     * Save discount.
     *
     * @param discountSearchDTO the discount search dto
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<Integer> saveDiscount(final CustomFieldGroup binder, final List<DiscountSearchDTO> selectedRebates, final int version, final SessionDTO sessionDTO) throws SystemException, PortalException {
        final List<Integer> idList = new ArrayList<Integer>();
        final int userId = Integer.valueOf(sessionDTO.getUserId());
        int versionNo = version + 1;
        final int deductionGroupSystemId = sessionDTO.getSystemId();
        DeductionGroup deductionGroup;
        if (deductionGroupSystemId == ConstantsUtils.ZERO_NUM) {
            deductionGroup = DeductionGroupLocalServiceUtil.createDeductionGroup(0);
            deductionGroup.setDeductionGroupName(binder.getField(CommonUtil.DISCOUNT_NAME).getValue().toString().trim());
            deductionGroup.setDeductionGroupNo(binder.getField(CommonUtil.DISCOUNT_NO).getValue().toString().trim());
            deductionGroup.setDeductionGroupDescription(binder.getField(CommonUtil.DISCOUNT_DESC).getValue().toString().trim());
            deductionGroup.setCreatedDate(new Date());
            deductionGroup.setModifiedDate(new Date());
            deductionGroup.setCreatedBy(userId);
            deductionGroup.setVersionNo(versionNo);
            deductionGroup.setDeductionFilter(ConstantsUtils.EMPTY);
            deductionGroup = DAO.addDeductionGroup(deductionGroup);

        } else {
            deductionGroup = DAO.getDeductionGroup(deductionGroupSystemId);
            int versionNumber = getExistingVersion(deductionGroupSystemId);
            deductionGroup.setDeductionGroupName(binder.getField(CommonUtil.DISCOUNT_NAME).getValue().toString().trim());
            deductionGroup.setDeductionGroupNo(binder.getField(CommonUtil.DISCOUNT_NO).getValue().toString().trim());
            deductionGroup.setDeductionGroupDescription(binder.getField(CommonUtil.DISCOUNT_DESC).getValue().toString().trim());
            deductionGroup.setVersionNo(versionNumber + 1);
            deductionGroup.setDeductionFilter(ConstantsUtils.EMPTY);
            deductionGroup.setModifiedDate(new Date());
            deductionGroup.setModifiedBy(userId);
            deductionGroup = DAO.updateDeductionGroup(deductionGroup);
        }
        idList.add(deductionGroup.getDeductionGroupSid());
        idList.add(deductionGroup.getVersionNo());
        saveDeductionGroupDetails(deductionGroup, selectedRebates, sessionDTO);
        return idList;
    }

    /**
     * Method to retrieve the values from Helper Table based on the listName.
     *
     * @param listName the list name
     * @return the drop down list
     */
    public List<HelperDTO> getDropDownList(final String listName) throws SystemException {
        List<HelperTable> list = null;
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        LOGGER.debug("getDropDownList listName=" + listName);
        list = DAO.getHelperTableDetailsByListName(listName);
        LOGGER.debug("getDropDownList listSize=" + list.size());
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));
            }
        }

        return helperList;
    }

    /**
     * Save deduction Group Details.
     *
     * @param itemGroup the item group
     * @param selectedItems the selected items
     * @return the string
     */
    private void saveDeductionGroupDetails(final DeductionGroup deductionGroup, final List<DiscountSearchDTO> selectedRebate, final SessionDTO sessionDTO) {
        try {
            final Date date = new Date();
            final int deductionGroupSystemId = deductionGroup.getDeductionGroupSid();
            final int deductionGroupSessionId = sessionDTO.getSystemId();
            final DynamicQuery deductionGroupDetailsQuery = DynamicQueryFactoryUtil.forClass(DeductionGroupDetails.class);
            deductionGroupDetailsQuery.add(RestrictionsFactoryUtil.eq(CommonUtil.DEDUCTION_GROUP_SID, deductionGroupSessionId));
            final List<DeductionGroupDetails> itemToRemove = DAO.getDeductionGroupDetailsList(deductionGroupDetailsQuery);
            final HashMap savedItemsMap = new HashMap();
            for (final DeductionGroupDetails deductionDetail : itemToRemove) {
                savedItemsMap.put(deductionDetail.getRsModelSid(), deductionDetail);
            }
            for (final DiscountSearchDTO rebate : selectedRebate) {
                int version = getExistingVersion(deductionGroupSystemId);
                DeductionGroupDetails deductionGroupDetails = DeductionGroupDetailsLocalServiceUtil.createDeductionGroupDetails(0);
                deductionGroupDetails.setDeductionGroupSid(deductionGroupSystemId);
                deductionGroupDetails.setRsModelSid(rebate.getRebateScheduleSid());
                deductionGroupDetails.setVersionNo(version);
                if (deductionGroupSessionId == ConstantsUtils.ZERO_NUM) {// to ensure that
                    deductionGroupDetails.setCreatedBy(NumericConstants.TWO_NINE_FIVE);
                    deductionGroupDetails.setCreatedDate(date);
                    deductionGroupDetails.setModifiedDate(date);
                    deductionGroupDetails = DAO.addDeductionGroupDetails(deductionGroupDetails);
                } else {
                    // Group ADD or EDIT
                    if (savedItemsMap.containsKey(rebate.getRebateScheduleSid())) {
                        deductionGroupDetails = (DeductionGroupDetails) savedItemsMap.get(rebate.getRebateScheduleSid());
                        deductionGroupDetails.setModifiedDate(date);
                        deductionGroupDetails.setModifiedBy(NumericConstants.TWO_NINE_FIVE);
                        deductionGroupDetails.setVersionNo(version);
                        deductionGroupDetails = DAO.updateDeductionGroupDetails(deductionGroupDetails);
                        savedItemsMap.remove(rebate.getRebateScheduleSid());
                    } else {// to save new rebate in existing item group
                        deductionGroupDetails.setCreatedBy(NumericConstants.TWO_NINE_FIVE);
                        deductionGroupDetails.setCreatedDate(date);
                        deductionGroupDetails.setModifiedDate(date);
                        deductionGroupDetails.setVersionNo(version);
                        deductionGroupDetails = DAO.addDeductionGroupDetails(deductionGroupDetails);
                    }
                }
            }
            if (!savedItemsMap.isEmpty()) {
                final List<DeductionGroupDetails> itemListToDelete = new ArrayList<DeductionGroupDetails>(savedItemsMap.values());
                for (final DeductionGroupDetails itemToDelete : itemListToDelete) {
                    DAO.deleteDeductionGroupDetails(itemToDelete);
                }
            }
            LOGGER.debug("saveDeductionGroupDetails return success");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

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
    private List<DiscountSearchDTO> getCustomizedDetailsResults(final List<RsModel> resultList, final Map resultList1) throws SystemException, PortalException {
        LOGGER.debug("getCustomizedItemResults started with P1:List<ItemMaster> resultList" + resultList.size() + "and P2:HashMap resultList1 size" + resultList1.size());
        final List<DiscountSearchDTO> rebateDetailsList = new ArrayList<DiscountSearchDTO>();
        if (!resultList.isEmpty()) {
            for (int i = 0; i < resultList.size(); i++) {
                final DiscountSearchDTO rebateDTO = new DiscountSearchDTO();
                final RsModel rebate = resultList.get(i);
                rebateDTO.setRebateScheduleSid(rebate.getRsModelSid());
                rebateDTO.setRebateName(rebate.getRsName());
                rebateDTO.setRebateNo(rebate.getRsNo());
                final String rsType = CommonUtil.getDescriptionFromHelper(rebate.getRsType());
                rebateDTO.setRebateScheduleType(rsType);
                final String rsProgramType = CommonUtil.getDescriptionFromHelper(rebate.getRebateProgramType());
                rebateDTO.setRebateProgramType(rsProgramType);
                final String rscategory = CommonUtil.getDescriptionFromHelper(rebate.getRsCategory());
                rebateDTO.setRebateScheduleCategory(rscategory);
                rebateDTO.setRebatePlanLevel(String.valueOf(rebate.getRebatePlanLevel()));
                rebateDetailsList.add(rebateDTO);

            }
        }
        LOGGER.debug("getCustomizedItemResults return List<ItemDetailsDTO> itemDetailsList=" + rebateDetailsList.size());
        return rebateDetailsList;
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
    public DiscountSearchDTO getDeductionGroupInfo(final int deductionGroupSystemId) throws SystemException, PortalException {
        LOGGER.debug("getItemGroupInfo started");
        final DynamicQuery deductionGroupDynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionGroup.class);
        deductionGroupDynamicQuery.add(RestrictionsFactoryUtil.eq(CommonUtil.DEDUCTION_GROUP_SID, deductionGroupSystemId));
        final List<DeductionGroup> resultsList = DeductionGroupLocalServiceUtil.dynamicQuery(deductionGroupDynamicQuery);
        DiscountSearchDTO dto = new DiscountSearchDTO();
        DeductionGroup deductionGroup = resultsList.get(0);
        dto.setDiscountName(deductionGroup.getDeductionGroupName());
        dto.setDiscountNo(deductionGroup.getDeductionGroupNo());
        dto.setDiscountDesc(deductionGroup.getDeductionGroupDescription());
        LOGGER.debug("getItemGroupInfo return ItemGroupDTO itemGroupDTO");
        return dto;
    }

    /**
     * Gets the existing deductionGroup names.
     *
     * @return the existing deductionGroup names
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public Map getExistingItemgroupNames() throws SystemException, PortalException {
        LOGGER.debug("getExistingItemgroupNames started");
        final HashMap results = new HashMap();
        final DynamicQuery deductionGroupDynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionGroup.class);
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property(CommonUtil.DEDUCTION_GROUP_SID));
        projList.add(ProjectionFactoryUtil.property(CommonUtil.DEDUCTION_GROUP_NAME));
        deductionGroupDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
        List<Object[]> resultList;
        resultList = DeductionGroupLocalServiceUtil.dynamicQuery(deductionGroupDynamicQuery);
        for (int i = 0; i < resultList.size(); i++) {
            final Object[] obj = resultList.get(i);
            results.put(String.valueOf(obj[0]), String.valueOf(obj[1]));
        }
        LOGGER.debug("getExistingItemgroupNames return HashMap results");
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
    public int getExistingVersion(final int deductionGroupSystemId) throws SystemException, PortalException {
        LOGGER.debug("getExistingVersion started");
        int version = ConstantsUtils.ZERO_NUM;
        final DynamicQuery deductionGroupHistoryDynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionGroup.class);
        deductionGroupHistoryDynamicQuery.add(RestrictionsFactoryUtil.eq(CommonUtil.DEDUCTION_GROUP_SID, deductionGroupSystemId));
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property(CommonUtil.VERSION_NO));
        deductionGroupHistoryDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));

        final List<Integer> finalList = new ArrayList<Integer>();
        final List<Integer> historyList = DeductionGroupLocalServiceUtil.dynamicQuery(deductionGroupHistoryDynamicQuery);
        finalList.addAll(historyList);
        final int size = finalList.size();
        if (size > ConstantsUtils.ZERO_NUM) {

            version = finalList.get(0);
        }
        LOGGER.debug("getExistingCompanyGroupNames return ");
        return version;
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
    public String deletedeductionGroup(final int deductionGroupSystemId) throws SystemException, PortalException {
        LOGGER.debug("deletededuction started with P1:int itemdeductionSystemId=" + deductionGroupSystemId);

        DeductionGroup deductionGroup;
        String deletedItemGroupName;
        List<DeductionGroupDetails> rebateList;
        final DynamicQuery deductionDetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionGroupDetails.class);
        deductionDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(CommonUtil.DEDUCTION_GROUP_SID, deductionGroupSystemId));
        rebateList = DAO.getDeductionDetailsList(deductionDetailsDynamicQuery);
        if (rebateList != null && !rebateList.isEmpty()) {
            for (int i = 0; i < rebateList.size(); i++) {
                final DeductionGroupDetails itemDetails = rebateList.get(i);
                DAO.deleteDeductionDetails(itemDetails);
            }
        }
        deductionGroup = DAO.deleteDeductionGroup(deductionGroupSystemId);
        deletedItemGroupName = deductionGroup.getDeductionGroupName();
        LOGGER.debug("deleteDeductionGroup return String deletedItemGroupName=" + deletedItemGroupName);
        return deletedItemGroupName;

    }

    public String getDeductionQuery(final DiscountSearchDTO dto) {
        LOGGER.debug("getDeductionQuery Started ");

        String query = "DECLARE @V VARCHAR(50)= '" + dto.getUdc2() + "'";
        query += " DECLARE @V1 VARCHAR(50)='" + dto.getRebateScheduleCategory() + "'";
        query += " DECLARE @V2 VARCHAR(50)='" + dto.getRebateProgramType() + "'";
        query += " DECLARE @V3 VARCHAR(50)='" + dto.getRebateContractType() + "'";
        query += " DECLARE @V4 VARCHAR(50)='" + dto.getItemNo().replace("*", "%") + "'";
        query += " DECLARE @V5 VARCHAR(50)='" + dto.getItemName().replace("*", "%") + "'";
        query += " DECLARE @V6 VARCHAR(50)='" + dto.getCustomerNo().replace("*", "%") + "'";
        query += " DECLARE @V7 VARCHAR(50)='" + dto.getCustomerName().replace("*", "%") + "'";
        query += " DECLARE @V8 VARCHAR(50)='" + dto.getContractNo().replace("*", "%") + "'";
        query += " DECLARE @V9 VARCHAR(50)='" + dto.getContractName().replace("*", "%") + "'";
        query += " DECLARE @V10 VARCHAR(50)='" + dto.getTradingPartner().replace("*", "%") + "'";
        query += "SELECT DISCOUNT_ID,\n"
                + "       DISCOUNT_NAME,\n"
                + "       DISCOUNT_NO,\n"
                + "       REBATE_SCHEDULE_TYPE,\n"
                + "       PROGRAM_TYPE,\n"
                + "       REBATE_SCHEDULE_CATEGORY,\n"
                + "       REBATE_PLAN_LEVEL,\n"
                + "       REBATE_SCHEDULE_SID\n"
                + "FROM   (SELECT    R.RS_ID   AS DISCOUNT_ID,\n"
                + "                  R.RS_NO   AS DISCOUNT_NO,\n"
                + "                  R.RS_NAME AS DISCOUNT_NAME,\n"
                + "                  R.RS_MODEL_SID AS REBATE_SCHEDULE_SID,"
                + "                  UDC2=(SELECT DESCRIPTION\n"
                + "                        FROM   HELPER_TABLE\n"
                + "                        WHERE  HELPER_TABLE_SID = U1.UDC2),\n"
                + "                  PROGRAM_TYPE=(SELECT DESCRIPTION\n"
                + "                                FROM   HELPER_TABLE\n"
                + "                                WHERE  HELPER_TABLE_SID = R.REBATE_PROGRAM_TYPE),\n"
                + "                  MARKET_TYPE=(SELECT DESCRIPTION\n"
                + "                               FROM   HELPER_TABLE\n"
                + "                               WHERE  HELPER_TABLE_SID = CM.CONTRACT_TYPE),\n"
                + "                  REBATE_PLAN_LEVEL=(SELECT DESCRIPTION\n"
                + "                                     FROM   HELPER_TABLE\n"
                + "                                     WHERE  HELPER_TABLE_SID = R1.REBATE_PLAN_LEVEL),\n"
                + "                  REBATE_SCHEDULE_CATEGORY=(SELECT DESCRIPTION\n"
                + "                                            FROM   HELPER_TABLE\n"
                + "                                            WHERE  HELPER_TABLE_SID = R1.RS_CATEGORY),\n"
                + "                  REBATE_SCHEDULE_TYPE=(SELECT DESCRIPTION\n"
                + "                                        FROM   HELPER_TABLE\n"
                + "                                        WHERE  HELPER_TABLE_SID = R1.RS_TYPE),\n"
                + "                  COM.COMPANY_NO,\n"
                + "                  COM.COMPANY_NAME,\n"
                + "				  COMPANY_TYPE=(SELECT DESCRIPTION\n"
                + "                                        FROM   HELPER_TABLE\n"
                + "                                        WHERE  HELPER_TABLE_SID = COM.COMPANY_TYPE),\n"
                + "                  IM.ITEM_NO,\n"
                + "                  IM.ITEM_NAME,\n"
                + "                  CM.CONTRACT_NO,\n"
                + "                  CM.CONTRACT_NAME\n"
                + "        FROM      CFP_CONTRACT C1\n"
                + "        JOIN      CFP_CONTRACT_DETAILS C2 ON C1.CFP_CONTRACT_SID = C2.CFP_CONTRACT_SID\n"
                + "        JOIN      IFP_CONTRACT I1 ON I1.CFP_CONTRACT_SID = C1.CFP_CONTRACT_SID\n"
                + "                                 AND I1.CONTRACT_MASTER_SID = C1.CONTRACT_MASTER_SID\n"
                + "        JOIN      IFP_CONTRACT_DETAILS I2 ON I2.IFP_CONTRACT_SID = I1.IFP_CONTRACT_SID\n"
                + "        JOIN      RS_CONTRACT R1 ON R1.CFP_CONTRACT_SID = C1.CFP_CONTRACT_SID\n"
                + "                                AND R1.IFP_CONTRACT_SID = I1.IFP_CONTRACT_SID\n"
                + "                                AND R1.CONTRACT_MASTER_SID = C1.CONTRACT_MASTER_SID\n"
                + "        JOIN      RS_CONTRACT_DETAILS R2 ON R2.RS_CONTRACT_SID = R1.RS_CONTRACT_SID\n"
                + "                                        AND R2.ITEM_MASTER_SID = I2.ITEM_MASTER_SID\n"
                + "        JOIN      RS_MODEL R ON R.RS_MODEL_SID = R1.RS_MODEL_SID\n"
                + "        JOIN      dbo.CONTRACT_MASTER CM ON CM.CONTRACT_MASTER_SID = C1.CONTRACT_MASTER_SID\n"
                + "        JOIN      dbo.COMPANY_MASTER COM ON COM.COMPANY_MASTER_SID = C2.COMPANY_MASTER_SID\n"
                + "        JOIN      dbo.ITEM_MASTER IM ON IM.ITEM_MASTER_SID = R2.ITEM_MASTER_SID\n"
                + "        LEFT JOIN UDCS U1 ON U1.MASTER_SID = R1.RS_CONTRACT_SID\n"
                + "                         AND U1.MASTER_TYPE = 'RS_CONTRACT')A\n";
        if (ConstantsUtils.EMPTY.equalsIgnoreCase(dto.getUdc2())) {
            query += "WHERE  (UDC2 LIKE CASE\n"
                    + " WHEN @V = '' THEN '%'\n"
                    + " ELSE @V\n"
                    + " END OR UDC2 IS NULL)\n";
        } else {
            query += "WHERE  (UDC2 LIKE CASE\n"
                    + " WHEN @V = '' THEN '%'\n"
                    + " ELSE @V\n"
                    + " END) \n";
        }
        if (ConstantsUtils.EMPTY.equalsIgnoreCase(dto.getRebateScheduleCategory())) {
            query += "AND (REBATE_SCHEDULE_CATEGORY LIKE CASE\n"
                    + " WHEN @V1 = '' THEN '%'\n"
                    + " ELSE @V1\n"
                    + " END OR REBATE_SCHEDULE_CATEGORY IS NULL)\n";
        } else {
            query += "AND (REBATE_SCHEDULE_CATEGORY LIKE CASE\n"
                    + " WHEN @V1 = '' THEN '%'\n"
                    + " ELSE @V1\n"
                    + " END) \n";
        }
        if (ConstantsUtils.EMPTY.equalsIgnoreCase(dto.getRebateProgramType())) {

            query += "   AND (A.PROGRAM_TYPE LIKE CASE\n"
                    + "                             WHEN @V2 = '' THEN '%'\n"
                    + "                             ELSE @V2\n"
                    + "                           END OR PROGRAM_TYPE IS NULL)\n";
        } else {
            query += "   AND (A.PROGRAM_TYPE LIKE CASE\n"
                    + "                             WHEN @V2 = '' THEN '%'\n"
                    + "                             ELSE @V2\n"
                    + "                           END) \n";
        }
        if (ConstantsUtils.EMPTY.equalsIgnoreCase(dto.getRebateContractType())) {
            query += "   AND (A.MARKET_TYPE LIKE CASE\n"
                    + "                            WHEN @V3 = '' THEN '%'\n"
                    + "                            ELSE @V3\n"
                    + "                          END OR MARKET_TYPE IS NULL)\n";
        } else {
            query += "   AND (A.MARKET_TYPE LIKE CASE\n"
                    + "                            WHEN @V3 = '' THEN '%'\n"
                    + "                            ELSE @V3\n"
                    + "                          END) \n";
        }
        if (ConstantsUtils.EMPTY.equalsIgnoreCase(dto.getItemNo())) {
            query += "   AND (A.ITEM_NO LIKE CASE\n"
                    + "                        WHEN @V4 = '' THEN '%'\n"
                    + "                        ELSE @V4\n"
                    + "                      END OR ITEM_NO IS NULL)\n";

        } else {
            query += "   AND (A.ITEM_NO LIKE CASE\n"
                    + "                        WHEN @V4 = '' THEN '%'\n"
                    + "                        ELSE @V4\n"
                    + "                      END) \n";
        }
        if (ConstantsUtils.EMPTY.equalsIgnoreCase(dto.getItemName())) {
            query += "   AND (A.ITEM_NAME LIKE CASE\n"
                    + "                          WHEN @V5 = '' THEN '%'\n"
                    + "                          ELSE @V5\n"
                    + "                        END OR ITEM_NAME IS NULL)\n";
        } else {
            query += "   AND (A.ITEM_NAME LIKE CASE\n"
                    + "                          WHEN @V5 = '' THEN '%'\n"
                    + "                          ELSE @V5\n"
                    + "                        END) \n";
        }
        if (ConstantsUtils.EMPTY.equalsIgnoreCase(dto.getCustomerNo())) {
            query += "   AND (A.COMPANY_NO LIKE CASE\n"
                    + "                           WHEN @V6 = '' THEN '%'\n"
                    + "                           ELSE @V6\n"
                    + "                         END OR COMPANY_NO IS NULL)\n";
        } else {
            query += "   AND (A.COMPANY_NO LIKE CASE\n"
                    + "                           WHEN @V6 = '' THEN '%'\n"
                    + "                           ELSE @V6\n"
                    + "                         END) \n";
        }
        if (ConstantsUtils.EMPTY.equalsIgnoreCase(dto.getCustomerName())) {
            query += "   AND (A.COMPANY_NAME LIKE CASE\n"
                    + "                             WHEN @V7 = '' THEN '%'\n"
                    + "                             ELSE @V7\n"
                    + "                           END OR COMPANY_NAME IS NULL)\n";
        } else {
            query += "   AND (A.COMPANY_NAME LIKE CASE\n"
                    + "                             WHEN @V7 = '' THEN '%'\n"
                    + "                             ELSE @V7\n"
                    + "                           END) \n";
        }
        if (ConstantsUtils.EMPTY.equalsIgnoreCase(dto.getContractNo())) {
            query += "   AND (A.CONTRACT_NO LIKE CASE\n"
                    + "                            WHEN @V8 = '' THEN '%'\n"
                    + "                            ELSE @V8\n"
                    + "                          END OR CONTRACT_NO IS NULL)\n";
        } else {
            query += "   AND (A.CONTRACT_NO LIKE CASE\n"
                    + "                            WHEN @V8 = '' THEN '%'\n"
                    + "                            ELSE @V8\n"
                    + "                          END) \n";
        }
        if (ConstantsUtils.EMPTY.equalsIgnoreCase(dto.getContractName())) {
            query += "   AND (A.CONTRACT_NAME LIKE CASE\n"
                    + "                              WHEN @V9 = '' THEN '%'\n"
                    + "                              ELSE @V9\n"
                    + "                            END OR CONTRACT_NAME IS NULL)\n";
        } else {
            query += "   AND (A.CONTRACT_NAME LIKE CASE\n"
                    + "                              WHEN @V9 = '' THEN '%'\n"
                    + "                              ELSE @V9\n"
                    + "                            END) \n";
        }
        if (ConstantsUtils.EMPTY.equalsIgnoreCase(dto.getTradingPartner())) {
            query += "   AND (A.COMPANY_TYPE LIKE CASE\n"
                    + "                             WHEN @V10 = '' THEN '%'\n"
                    + "                             ELSE @V10\n"
                    + "                           END OR COMPANY_TYPE IS NULL) ";
        } else {
            query += "   AND (A.COMPANY_TYPE LIKE CASE\n"
                    + "                             WHEN @V10 = '' THEN '%'\n"
                    + "                             ELSE @V10\n"
                    + "                           END ) ";
        }

        LOGGER.debug("getDeductionQuery Ended ");
        return query;
    }
}
