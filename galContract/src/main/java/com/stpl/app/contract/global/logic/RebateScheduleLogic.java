package com.stpl.app.contract.global.logic;

import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import com.stpl.app.contract.common.dto.SessionDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.OrderByColumn.Type;

import com.stpl.app.contract.common.util.HelperListUtil;
import com.stpl.app.contract.dao.impl.RebateScheduleLogicDAOImpl;
import com.stpl.app.contract.dashboard.dto.ImtdContRsDetailsFrDTO;
import com.stpl.app.contract.dashboard.dto.RSFormulaDTO;
import com.stpl.app.contract.dashboard.logic.DashBoardLogic;
import com.stpl.app.contract.global.dto.ItemMasterDTO;
import com.stpl.app.contract.global.dto.RebateScheduleMasterDTO;
import com.stpl.app.contract.global.dto.RsDeductionLookupDto;
import com.stpl.app.contract.global.dto.VwContractPriceInfoDTO;
import com.stpl.app.contract.global.util.CommonUtils;
import com.stpl.app.contract.ui.ErrorfulFieldGroup;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.model.ForecastingFormula;
import com.stpl.app.model.FormulaDetailsMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ImtdItemPriceRebateDetails;
import com.stpl.app.model.ImtdRsContractDetailsFr;
import com.stpl.app.model.RebatePlanMaster;
import com.stpl.app.model.RsContractDetails;
import com.stpl.app.model.RsModel;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.service.ForecastingFormulaLocalServiceUtil;
import com.stpl.app.service.FormulaDetailsMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ImtdItemPriceRebateDetailsLocalServiceUtil;
import com.stpl.app.service.ImtdRsContractDetailsFrLocalServiceUtil;
import com.stpl.app.service.RsDetailsLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.domain.contract.contractdashboard.RebateScheduleDAO;
import com.stpl.domain.contract.contractdashboard.globalcontract.RebateLogic;
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
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;


/**
 * Logic for Rebate Schedule.
 *
 * @author
 */
public class RebateScheduleLogic extends BeanItemContainer<RsModel> implements RebateLogic {

    /**
     * The dao.
     */
    private static final RebateScheduleDAO dao = new RebateScheduleLogicDAOImpl();
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(RebateScheduleLogic.class);

    SessionDTO sessionDTO;
    
    static HashMap<String, String> deductionCriteria = new HashMap<>();
    static HashMap<String, String> deductionFilter = new HashMap<>();
    public static ResourceBundle constantProperties = ResourceBundle.getBundle("properties.constants");

    /**
     * Default Constructor.
     */
    public RebateScheduleLogic() {
        super(RsModel.class);
    }

    public RebateScheduleLogic(final SessionDTO sessionDTO) {
        super(RsModel.class);
        this.sessionDTO = sessionDTO;
    }

    HelperListUtil helperListUtil = HelperListUtil.getInstance();

    /**
     * Returns the list of String from the HelperTable based on the given list
     * type.
     *
     * @param listType -
     * @return List of String.
     */
    public List<HelperDTO> getHelperDetails(final String listType) throws SystemException {
        LOGGER.info("Entering getHelperDetails()");
        final List<HelperDTO> returnList = new ArrayList<HelperDTO>();

        final List<HelperTable> list = dao.findByHelperTableDetails(listType);
        returnList.add(new HelperDTO(0, Constants.SELECT_ONE));
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                returnList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }
        }
        LOGGER.info("End of getHelperDetails() with returnList size=" + returnList.size());
        return returnList;
    }

    /**
     * Returns the list of String from the HelperTable based on the given list
     * type.
     *
     * @param listType -
     * @return List of String.
     */
    public List<String> getHelperDetailsDesc(final String listType) {
        LOGGER.info("Entering getHelperDetails()");
        final List<String> returnList = new ArrayList<String>();
        try {
            final List<HelperTable> list = dao.findByHelperTableDetails(listType);

            returnList.add(Constants.SELECT_ONE);
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    final HelperTable helperTable = (HelperTable) list.get(i);
                    returnList.add(helperTable.getDescription());
                }
            }
            Collections.sort(returnList);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("End of getHelperDetails() with returnList size=" + returnList.size());
        return returnList;
    }

    /**
     * Returns the count of the records based on the Rebate schedule search
     * criteria.
     *
     * @param rebateScheduleForm - FieldGroup.
     * @return int - count of records
     * @throws SystemException the system exception
     */
    public int getSearchCount(final ErrorfulFieldGroup rebateScheduleForm, BeanSearchCriteria searchCriteria) throws SystemException {
        final DynamicQuery rebateScheduleDynamicQuery = DynamicQueryFactoryUtil.forClass(RsModel.class);
        LOGGER.info("Entering getSearchCount()");
        String rebateScheduleId;
        String rebateScheduleNo;
        String rebateScheduleName;
        int rebateScheduleStatus;
        int rebateScheduleType;
        int rebateProgramType;

        if (rebateScheduleForm.getField(Constants.RS_ID).getValue() == null) {
            rebateScheduleId = StringUtils.EMPTY;
        } else {
            rebateScheduleId = rebateScheduleForm.getField(Constants.RS_ID).getValue().toString();
        }
        if (rebateScheduleForm.getField(Constants.RS_NO).getValue() == null) {
            rebateScheduleNo = StringUtils.EMPTY;
        } else {

            rebateScheduleNo = rebateScheduleForm.getField(Constants.RS_NO).getValue().toString();
        }
        if (rebateScheduleForm.getField(Constants.RS_NAME).getValue() == null) {
            rebateScheduleName = StringUtils.EMPTY;
        } else {
            rebateScheduleName = rebateScheduleForm.getField(Constants.RS_NAME).getValue().toString();
        }
        if (rebateScheduleForm.getField(Constants.RS_STATUS).getValue() == null) {
            rebateScheduleStatus = 0;
        } else {
            rebateScheduleStatus = DashBoardLogic.getHelperCode(CommonUtils.STATUS, rebateScheduleForm.getField(Constants.RS_STATUS).getValue().toString());

        }
        if (rebateScheduleForm.getField(Constants.RS_TYPE).getValue() == null) {
            rebateScheduleType = 0;
        } else {
            rebateScheduleType = DashBoardLogic.getHelperCode(CommonUtils.RS_TYPE, rebateScheduleForm.getField(Constants.RS_TYPE).getValue().toString());
        }
        if (rebateScheduleForm.getField(Constants.REBATE_PROGRAM_TYPE).getValue() == null) {
            rebateProgramType = 0;
        } else {
            rebateProgramType = DashBoardLogic.getHelperCode(CommonUtils.REBATE_PROGRAM_TYPE, rebateScheduleForm.getField(Constants.REBATE_PROGRAM_TYPE).getValue().toString());
        }

        if (StringUtils.isNotBlank(rebateScheduleId)) {
            rebateScheduleId = rebateScheduleId.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
            rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.RS_ID1, rebateScheduleId));
        }
        if (StringUtils.isNotBlank(rebateScheduleNo)) {
            rebateScheduleNo = rebateScheduleNo.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
            rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.RS_NO1, rebateScheduleNo));
        }
        if (StringUtils.isNotBlank(rebateScheduleName)) {
            rebateScheduleName = rebateScheduleName.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
            rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.RS_NAME1, rebateScheduleName));
        }
        if (rebateScheduleStatus != 0) {
            rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.RS_STATUS1, rebateScheduleStatus));
        }
        if (rebateScheduleType != 0) {
            rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.RS_TYPE1, rebateScheduleType));
        }
        if (rebateProgramType != 0) {
            rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.REBATE_PROGRAM_TYPE, rebateProgramType));
        }

        if (searchCriteria != null && searchCriteria.getFilters() != null) {
            for (Container.Filter filter : searchCriteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;

                    if (stringFilter.getPropertyId().equals("rebateScheduleId")) {
                        rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.RS_ID1, filterText));
                    } else if (stringFilter.getPropertyId().equals("rebateScheduleNo")) {
                        rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.RS_NO1, filterText));
                    } else if (stringFilter.getPropertyId().equals("rebateScheduleName")) {
                        rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.RS_NAME1, filterText));
                    } else if (stringFilter.getPropertyId().equals("displayRsStatus")) {
                        rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.RS_STATUS1, Integer.valueOf(stringFilter.getFilterString())));
                    } else if (stringFilter.getPropertyId().equals(Constants.RS_TYPE1)) {
                        rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.RS_TYPE1, Integer.valueOf(stringFilter.getFilterString())));
                    } else if (stringFilter.getPropertyId().equals("displayRsType")) {
                        rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.REBATE_PROGRAM_TYPE, Integer.valueOf(stringFilter.getFilterString())));
                    }
                }
            }
        }

        LOGGER.info("End of getSearchCount");
        return (int) dao.dynamicQueryCount(rebateScheduleDynamicQuery);
    }

    /**
     * Returns the total count of records in the Rebate Schedule Master Table.
     *
     * @return - total count of records
     * @throws SystemException
     */
    public int getTotalCount() throws SystemException {
        LOGGER.info("Entering getTotalCount()");
        return dao.getRebateScheduleMastersCount();

    }

    /**
     * Returns a list of RebateScheduleMasterDTO object based on the field
     * group,starting and ending index.
     *
     * @param rebateScheduleForm - FieldGroup that holds the values of form.
     * @param start - Starting Index
     * @param end - Ending Index.
     * @param columns - List of OrderBYColumn object
     * @return List of RebateScheduleMasterDTO
     * @throws SystemException
     */
    @SuppressWarnings("unchecked")
    public List<RebateScheduleMasterDTO> searchRebateScheduleMaster(final ErrorfulFieldGroup rebateScheduleForm, BeanSearchCriteria searchCriteria, final int start, final int end, final List<OrderByColumn> columns)
            throws SystemException {
        LOGGER.info("Entering searchRebateScheduleMaster()");

        String rebateScheduleId;
        String rebateScheduleNo;
        String rebateScheduleName;
        int rebateScheduleStatus;
        int rebateScheduleType;
        int rebateProgramType;

        if (rebateScheduleForm.getField(Constants.RS_ID).getValue() == null) {
            rebateScheduleId = StringUtils.EMPTY;
        } else {
            rebateScheduleId = rebateScheduleForm.getField(Constants.RS_ID).getValue().toString();
        }
        if (rebateScheduleForm.getField(Constants.RS_NO).getValue() == null) {
            rebateScheduleNo = StringUtils.EMPTY;
        } else {

            rebateScheduleNo = rebateScheduleForm.getField(Constants.RS_NO).getValue().toString();
        }
        if (rebateScheduleForm.getField(Constants.RS_NAME).getValue() == null) {
            rebateScheduleName = StringUtils.EMPTY;
        } else {
            rebateScheduleName = rebateScheduleForm.getField(Constants.RS_NAME).getValue().toString();
        }
        if (rebateScheduleForm.getField(Constants.RS_STATUS).getValue() == null) {
            rebateScheduleStatus = 0;
        } else {
            rebateScheduleStatus = DashBoardLogic.getHelperCode(CommonUtils.STATUS, rebateScheduleForm.getField(Constants.RS_STATUS).getValue().toString());

        }
        if (rebateScheduleForm.getField(Constants.RS_TYPE).getValue() == null) {
            rebateScheduleType = 0;
        } else {
            rebateScheduleType = DashBoardLogic.getHelperCode(CommonUtils.RS_TYPE, rebateScheduleForm.getField(Constants.RS_TYPE).getValue().toString());
        }
        if (rebateScheduleForm.getField(Constants.REBATE_PROGRAM_TYPE).getValue() == null) {
            rebateProgramType = 0;
        } else {
            rebateProgramType = DashBoardLogic.getHelperCode(CommonUtils.REBATE_PROGRAM_TYPE, rebateScheduleForm.getField(Constants.REBATE_PROGRAM_TYPE).getValue().toString());
        }

        final DynamicQuery rebateScheduleDynamicQuery = DynamicQueryFactoryUtil.forClass(RsModel.class);

        if (StringUtils.isNotBlank(rebateScheduleId)) {
            rebateScheduleId = rebateScheduleId.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
            rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.RS_ID1, rebateScheduleId));
        }
        if (StringUtils.isNotBlank(rebateScheduleNo)) {
            rebateScheduleNo = rebateScheduleNo.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
            rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.RS_NO1, rebateScheduleNo));
        }
        if (StringUtils.isNotBlank(rebateScheduleName)) {
            rebateScheduleName = rebateScheduleName.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
            rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.RS_NAME1, rebateScheduleName));
        }
        if (rebateScheduleStatus != 0) {
            rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.RS_STATUS1, rebateScheduleStatus));
        }
        if (rebateScheduleType != 0) {
            rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.RS_TYPE1, rebateScheduleType));
        }
        if (rebateProgramType != 0) {
            rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.REBATE_PROGRAM_TYPE, rebateProgramType));
        }

        for (final Iterator<OrderByColumn> iterator = columns.iterator(); iterator.hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
            if (orderByColumn.getType() == Type.ASC) {
                rebateScheduleDynamicQuery.addOrder(OrderFactoryUtil.asc(orderByColumn.getName()));
            } else {
                rebateScheduleDynamicQuery.addOrder(OrderFactoryUtil.desc(orderByColumn.getName()));
            }
        }

        if (searchCriteria != null && searchCriteria.getFilters() != null) {
            for (Container.Filter filter : searchCriteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;

                    if (stringFilter.getPropertyId().equals("rebateScheduleId")) {
                        rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.RS_ID1, filterText));
                    } else if (stringFilter.getPropertyId().equals("rebateScheduleNo")) {
                        rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.RS_NO1, filterText));
                    } else if (stringFilter.getPropertyId().equals("rebateScheduleName")) {
                        rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.RS_NAME1, filterText));
                    } else if (stringFilter.getPropertyId().equals("displayRsStatus")) {
                        rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.RS_STATUS1, Integer.valueOf(stringFilter.getFilterString())));
                    } else if (stringFilter.getPropertyId().equals("displayRsType")) {
                        rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.RS_TYPE1, Integer.valueOf(stringFilter.getFilterString())));
                    } else if (stringFilter.getPropertyId().equals("displayRpType")) {
                        rebateScheduleDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.REBATE_PROGRAM_TYPE, Integer.valueOf(stringFilter.getFilterString())));
                    }
                }
            }
        }

        rebateScheduleDynamicQuery.setLimit(start, end);
        final List<RsModel> searchList = dao.dynamicQuery(rebateScheduleDynamicQuery);

        LOGGER.info("End of searchRebateScheduleMaster searchList size=" + searchList.size());
        return getCustomizedModel(searchList);
    }

    /**
     * Gets the customized model.
     *
     * @param searchList the search list
     * @return the customized model
     */
    private List<RebateScheduleMasterDTO> getCustomizedModel(final List<RsModel> searchList) {
        LOGGER.info("Entering getCustomizedModel()");
        if (searchList != null) {
            final List<RebateScheduleMasterDTO> rsDTOlist = new ArrayList<RebateScheduleMasterDTO>();

            for (int i = 0; i < searchList.size(); i++) {
                final RsModel rsMaster = searchList.get(i);
                final RebateScheduleMasterDTO rsDTO = new RebateScheduleMasterDTO();
                rsDTO.setRebateScheduleTransRefNo(rsMaster.getRsTransRefNo());
                rsDTO.setFormulaMethodId(rsMaster.getFormulaMethodId());
                rsDTO.setPaymentFrequency(String.valueOf(rsMaster.getPaymentFrequency()));
                rsDTO.setRebateScheduleTransRefName(rsMaster.getRsTransRefName());
                rsDTO.setModifiedDate(rsMaster.getModifiedDate());
                rsDTO.setParentRebateScheduleName(rsMaster.getParentRsName());
                rsDTO.setParentRebateScheduleId(rsMaster.getParentRsId());
                rsDTO.setRebatePlanLevel(String.valueOf(rsMaster.getRebatePlanLevel()));
                rsDTO.setRebateRuleType(String.valueOf(rsMaster.getRebateRuleType()));
                rsDTO.setInboundStatus(rsMaster.getInboundStatus());
                rsDTO.setRebateScheduleStatus(helperListUtil.getIdHelperDTOMap().get(rsMaster.getRsStatus()));
                if (rsMaster.getRsStatus() != 0) {
                    rsDTO.setDisplayRsStatus(DashBoardLogic.loadDescription(rsMaster.getRsStatus()));
                }
                rsDTO.setModifiedBy(String.valueOf(rsMaster.getModifiedBy()));
                rsDTO.setRebateScheduleNo(rsMaster.getRsNo());
                rsDTO.setPaymentMethod(String.valueOf(rsMaster.getPaymentMethod()));
                rsDTO.setZipCode(rsMaster.getZipCode());
                rsDTO.setRebateRuleAssociation(rsMaster.getRebateRuleAssociation());
                rsDTO.setInternalNotes(rsMaster.getInternalNotes());
                rsDTO.setRecordLockStatus(rsMaster.getRecordLockStatus());
                rsDTO.setRebateScheduleDesignation(String.valueOf(rsMaster.getRsDesignation()));
                rsDTO.setRebateScheduleTransRefId(rsMaster.getRsTransRefId());
                rsDTO.setRebateScheduleName(rsMaster.getRsName());
                rsDTO.setRebateProgramType(helperListUtil.getIdHelperDTOMap().get(rsMaster.getRebateProgramType()));
                if (rsMaster.getRebateProgramType() != 0) {
                    rsDTO.setDisplayRpType(DashBoardLogic.loadDescription(rsMaster.getRebateProgramType()));
                }
                rsDTO.setInterestBearingBasis(String.valueOf(rsMaster.getInterestBearingBasis()));
                rsDTO.setCity(rsMaster.getCity());
                rsDTO.setRebateProcessingType(String.valueOf(rsMaster.getRebateProcessingType()));
                rsDTO.setState(String.valueOf(rsMaster.getState()));
                rsDTO.setRebateFrequency(String.valueOf(rsMaster.getRebateFrequency()));
                rsDTO.setRebateScheduleId(rsMaster.getRsId());
                rsDTO.setMakePayableTo(rsMaster.getMakePayableTo());
                rsDTO.setCreatedBy(String.valueOf(rsMaster.getCreatedBy()));
                rsDTO.setCreatedDate(rsMaster.getCreatedDate());
                rsDTO.setRebateScheduleSystemId(rsMaster.getRsModelSid());
                rsDTO.setTradeClass(String.valueOf(rsMaster.getRsTradeClass()));
                rsDTO.setInterestBearingIndicator(String.valueOf(rsMaster.getInterestBearingIndicator()));
                rsDTO.setPaymentTerms(String.valueOf(rsMaster.getPaymentTerms()));
                rsDTO.setAddress1(rsMaster.getAddress1());
                rsDTO.setRebateScheduleType(helperListUtil.getIdHelperDTOMap().get(rsMaster.getRsType()));
                if (rsMaster.getRsType() != 0) {
                    rsDTO.setDisplayRsType(DashBoardLogic.loadDescription(rsMaster.getRsType()));
                }
                rsDTO.setAddress2(rsMaster.getAddress2());
                rsDTO.setValidationProfile(String.valueOf(rsMaster.getRsValidationProfile()));
                rsDTO.setRebateScheduleCategory(String.valueOf(rsMaster.getRsCategory()));
                rsDTO.setCalendar(String.valueOf(rsMaster.getRsCalendar()));
                rsDTO.setPaymentGracePeriod(rsMaster.getPaymentGracePeriod());
                rsDTO.setBatchId(rsMaster.getBatchId());
                rsDTOlist.add(rsDTO);
            }
            LOGGER.info("End of getCustomizedModel()");
            return rsDTOlist;
        }
        return null;
    }

    /**
     * Gets the dao.
     *
     * @return the dao
     */
    public RebateScheduleDAO getDao() {
        return dao;
    }

    public int getLazySelectedCompaniesCount() throws PortalException, SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdItemPriceRebateDetails.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.USERS_SID, userId));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.SESSION_ID, sessionId));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.OPERATION, Constants.STATUS_D));
        final Long count = ImtdItemPriceRebateDetailsLocalServiceUtil.dynamicQueryCount(cfpDynamicQuery);
        LOGGER.info("selected count :" + count);
        return Integer.parseInt(String.valueOf(count));
    }

    public List<VwContractPriceInfoDTO> getLazySelectedItemDeatils(int start, int end, Boolean flag) throws PortalException, SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdItemPriceRebateDetails.class);
        List<VwContractPriceInfoDTO> companyList = new ArrayList<VwContractPriceInfoDTO>();
        cfpDynamicQuery.setLimit(start, end);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.USERS_SID, userId));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.SESSION_ID, sessionId));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.OPERATION, Constants.STATUS_D));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc("tradingPartnerSystemId"));
        ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property("tradingPartnerSystemId"));
        projList.add(ProjectionFactoryUtil.property("companyId"));
        projList.add(ProjectionFactoryUtil.property("companyType"));
        projList.add(ProjectionFactoryUtil.property("companyNo"));
        projList.add(ProjectionFactoryUtil.property("companyName"));
        projList.add(ProjectionFactoryUtil.property("companyStatus"));
        projList.add(ProjectionFactoryUtil.property("tempCFPSystemID"));
        if (flag) {
            projList.add(ProjectionFactoryUtil.property("companyStartDate"));
            projList.add(ProjectionFactoryUtil.property("companyEndDate"));
            projList.add(ProjectionFactoryUtil.property("startDate"));
            projList.add(ProjectionFactoryUtil.property("endDate"));
            projList.add(ProjectionFactoryUtil.property("attachedStatus"));
            projList.add(ProjectionFactoryUtil.property("attachedDate"));
            projList.add(ProjectionFactoryUtil.property("checkRecord"));
        }
        cfpDynamicQuery.setProjection(projList);
        final List<Object[]> returnList = ImtdItemPriceRebateDetailsLocalServiceUtil.dynamicQuery(cfpDynamicQuery);
        LOGGER.info("selected results :" + returnList.size());
        return getCustomizedCompanyMasterDTO(returnList, companyList, flag);
    }

    private static List<VwContractPriceInfoDTO> getCustomizedCompanyMasterDTO(List<Object[]> returnList, List<VwContractPriceInfoDTO> companyList, Boolean flag) {
        VwContractPriceInfoDTO companyDTO;
        for (Object[] tempCfp : returnList) {
            companyDTO = new VwContractPriceInfoDTO();
            int j = 0;
            companyList.add(companyDTO);
        }
        return companyList;
    }

    public void loadTempIFP(Object cfpSystemId, Object contractSystemId) throws SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String tempDate = String.valueOf(sessionDTO.getSessionDate());
        final List<Object> input = new ArrayList<Object>(5);
        input.add(userId);
        input.add(sessionId);
        input.add(tempDate);
        input.add(cfpSystemId);
        input.add(contractSystemId);
        ImtdItemPriceRebateDetailsLocalServiceUtil.deleteAll(input, "Load");
        ImtdItemPriceRebateDetailsLocalServiceUtil.loadTempItemdetails(input, StringUtils.EMPTY);
    }

    public void addToTempIFP(Object searchField, String searchValue) throws SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String cfpSystemId = String.valueOf(sessionDTO.getCfpSystemId());
        final String contractSystemId = String.valueOf(sessionDTO.getContractSystemId());
        final String tempDate = String.valueOf(sessionDTO.getSessionDate());
        final Map<String, String> map = new HashMap<String, String>();
        map.put("Company No", Constants.COMPANY_NO);
        map.put("Company Name", Constants.COMPANY_NAME);
        map.put("Company Type", Constants.COMPANY_TYPE);
        map.put("Company Status", Constants.COMPANY_STATUS);
        final List<Object> input = new ArrayList<Object>(8);
        input.add(cfpSystemId);
        input.add(contractSystemId);
        input.add(tempDate);
        input.add(userId);
        input.add(sessionId);
        input.add(tempDate);
        input.add(map.get(searchField));
        input.add(searchValue.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT));
        ImtdItemPriceRebateDetailsLocalServiceUtil.loadTempItemdetails(input, "Add");
    }

    public void clearTempIFP() throws SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final List<Object> input = new ArrayList<Object>(5);
        input.add(userId);
        input.add(sessionId);
        ImtdItemPriceRebateDetailsLocalServiceUtil.deleteAll(input, "Back");
    }

    public void populateToTempIFP(Object populateField, Object populateValue, Boolean flag) throws SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final List<Object> input = new ArrayList<Object>(5);
        input.add(populateField);
        input.add(populateValue);
        input.add(userId);
        input.add(sessionId);
        if (flag) {
            ImtdItemPriceRebateDetailsLocalServiceUtil.massPopulateAll(input, null);
        } else {
            ImtdItemPriceRebateDetailsLocalServiceUtil.massPopulate(input, null);
        }
    }

    public static Boolean saveToTempIFP(List<VwContractPriceInfoDTO> saveList) throws PortalException, SystemException {
        List<ImtdItemPriceRebateDetails> saveDetailsList = new ArrayList<ImtdItemPriceRebateDetails>();
        
        List<Object> input = new ArrayList<Object>(1);
        input.add(saveDetailsList);
        return ImtdItemPriceRebateDetailsLocalServiceUtil.saveItem(input, "SaveDetails");
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
            temp.setOperation(Constants.STATUS_D);
            ImtdItemPriceRebateDetailsLocalServiceUtil.updateImtdItemPriceRebateDetails(temp);
        } else {
            ImtdItemPriceRebateDetailsLocalServiceUtil.deleteImtdItemPriceRebateDetails(tempIfpSystemId);
        }
    }

    public void addItem(ItemMasterDTO companyDto) throws SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String cfpSystemId = String.valueOf(sessionDTO.getCfpSystemId());
        final String contractSystemId = String.valueOf(sessionDTO.getContractSystemId());
        final List<Object> input = new ArrayList<Object>(5);
        input.add(userId);
        input.add(sessionId);
    }

    public void loadOptionGroup(OptionGroup rebateRuleType, String REBATE_RULE_TYPE) {

        try {
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            dynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.LIST_NAME, REBATE_RULE_TYPE));
            ProjectionList projectonList = ProjectionFactoryUtil.projectionList();
            projectonList.add(ProjectionFactoryUtil.property(Constants.DESCRIPTION));
            dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectonList));
            List<String> listInt = HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
            if (!listInt.isEmpty()) {
                for (int i = 0; i < listInt.size(); i++) {
                    rebateRuleType.addItem(listInt.get(i));
                    if (i == 0) {
                        rebateRuleType.select(listInt.get(0));
                    }
                }

            }
        } catch (SystemException ex) {
            LOGGER.error(ex);;
        }

    }

    /**
     * getting count for Rebate Plan Name
     *
     * @param filter
     * @return
     */
    public static int getRebatePlanCount(String filter, final HelperDTO rebatePlan) throws PortalException, SystemException {
        LOGGER.info("Entering getRebatePlanCount method ");
        filter = StringUtils.trimToEmpty(filter) + Constants.PERCENT;
        List<Object[]> rebateList;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.ne("inboundStatus", Constants.STATUS_D));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.RP_NAME, filter));

        if (rebatePlan != null && rebatePlan.getId() != 0) {
            dynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.REBATE_PLAN_MASTER_SID, rebatePlan.getId()));
        }
        dynamicQuery.setProjection(ProjectionFactoryUtil.count(Constants.RP_NAME));
        rebateList = dao.getRebatePlanList(dynamicQuery);
        int rebateCount = Integer.parseInt(String.valueOf(rebateList.get(0)));
        LOGGER.info("Ending getRebatePlanCount method : returning count :" + rebateCount);
        return rebateCount;
    }

    /**
     *
     * @param start
     * @param end
     * @param filter
     * @return
     */
    public static List<HelperDTO> getRebatePlanResults(int start, int end, String filter, final HelperDTO rebatePlan) throws PortalException, SystemException {
        LOGGER.info("Entering getLazyBrandCount method ");
        filter = StringUtils.trimToEmpty(filter) + Constants.PERCENT;
        List<Object[]> qualifierList;
        final List<HelperDTO> list = new ArrayList<HelperDTO>();
        int startValue = start;
        int endValue = end;
        if (start == Constants.ZERO) {
            startValue = start;
            endValue = end - 1;
        } else {
            startValue = start - 1;
            endValue = end - 1;
        }

        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.RP_NAME, filter));
        dynamicQuery.setLimit(startValue, endValue);
        if (rebatePlan != null && rebatePlan.getId() != 0) {
            dynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.REBATE_PLAN_MASTER_SID, rebatePlan.getId()));
        }
        dynamicQuery.add(RestrictionsFactoryUtil.ne("inboundStatus", Constants.STATUS_D));
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(Constants.REBATE_PLAN_MASTER_SID));
        projectionList.add(ProjectionFactoryUtil.property(Constants.RP_NAME));
        dynamicQuery.setProjection(projectionList);
        dynamicQuery.addOrder(OrderFactoryUtil.asc(Constants.RP_NAME));

        qualifierList = dao.getRebatePlanList(dynamicQuery);

        HelperDTO dto;
        if (start == Constants.ZERO) {
            dto = new HelperDTO(Constants.SELECT_ONE);
            dto.setDescription(Constants.SELECT_ONE);
            list.add(dto);
            if (rebatePlan != null && rebatePlan.getId() != 0) {
                list.add(rebatePlan);
            }

        }
        for (final Iterator<Object[]> iterator = qualifierList.iterator(); iterator.hasNext();) {
            final Object[] value = iterator.next();
            dto = new HelperDTO(StringUtils.EMPTY);
            dto.setId(value[0] != null ? Integer.valueOf(value[0].toString()) : 0);
            dto.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
            list.add(dto);
        }
        LOGGER.info("return Brand size -" + list.size());
        return list;
    }

    public int getForecastingFormulaCount() {

        try {
            return ForecastingFormulaLocalServiceUtil.getForecastingFormulasCount();
        } catch (SystemException ex) {
           LOGGER.error(ex.getMessage());
        }
        return 0;
    }

    public List<RSFormulaDTO> getForecastingFormula(int start, int end) {

        List<RSFormulaDTO> resultList = new ArrayList<RSFormulaDTO>();

        final DynamicQuery forecastingFormula = DynamicQueryFactoryUtil.forClass(ForecastingFormula.class);
        forecastingFormula.setLimit(start, end);
        List<ForecastingFormula> list;
        try {
            list = ForecastingFormulaLocalServiceUtil.dynamicQuery(forecastingFormula);
            resultList = getCustomisedForecastingFormula(list);
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }

        return resultList;
    }

    public List<RSFormulaDTO> getCustomisedForecastingFormula(List<ForecastingFormula> list) {
        List<RSFormulaDTO> resultList = new ArrayList<RSFormulaDTO>();
        RSFormulaDTO dto;
        try {
            if (list != null) {
                for (ForecastingFormula formula : list) {
                    dto = new RSFormulaDTO();
                    dto.setForectastingFormulaSid(formula.getForecastingFormulaSid());
                    dto.setFormulaNo(formula.getFormulaNo());
                    dto.setFormulaName(formula.getFormulaName());
                    resultList.add(dto);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return resultList;
    }

    /**
     * To get the result for lazy load
     *
     * @param itemId
     * @return
     * @throws SystemException
     */
    public int tempContractTableFormulaValidation(final int itemSid, int formulaId) throws SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));

        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(ImtdRsContractDetailsFr.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.ITEM_MASTER_SID, itemSid));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.FORMULA_ID, formulaId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.USER_SID, userId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.SESSION_ID, sessionId));
        return Integer.parseInt(String.valueOf(ImtdRsContractDetailsFrLocalServiceUtil.dynamicQueryCount(ifpDynamicQuery)));
    }

    public int getImtdRsDetailsFrCount(int itemMasterId) {
        try {
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));

            final String sessionId = String.valueOf(sessionDTO.getUiSessionId());

            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(ImtdRsContractDetailsFr.class);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.ITEM_MASTER_SID, itemMasterId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.USER_SID, userId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.SESSION_ID, sessionId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.OPERATION, Constants.STATUS_D));

            int count = (int) ImtdRsContractDetailsFrLocalServiceUtil.dynamicQueryCount(ifpDynamicQuery);
            return count;
        } catch (SystemException ex) {
          LOGGER.error(ex.getMessage());
        }

        return 0;
    }

    public List<ImtdContRsDetailsFrDTO> getImtdRsDetailsFr(int itemMasterId, int start, int offset) {
        List<ImtdContRsDetailsFrDTO> resultList = new ArrayList<ImtdContRsDetailsFrDTO>();
        try {

            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));

            final String sessionId = String.valueOf(sessionDTO.getUiSessionId());

            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(ImtdRsContractDetailsFr.class);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.ITEM_MASTER_SID, itemMasterId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.USER_SID, userId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.SESSION_ID, sessionId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.OPERATION, Constants.STATUS_D));
            ifpDynamicQuery.setLimit(start, offset);
            List<ImtdRsContractDetailsFr> list = ImtdRsContractDetailsFrLocalServiceUtil.dynamicQuery(ifpDynamicQuery);

            resultList = getCustomisedImtdContRsDetailsFr(list);

        } catch (SystemException ex) {
            LOGGER.error(ex);
        }

        return resultList;
    }

    public static List<Integer> getImtdFormulaDescList(int itemMasterId, SessionDTO sessionDTO) {
        List<Integer> list = new ArrayList<Integer>();
        try {

            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));

            final String sessionId = String.valueOf(sessionDTO.getUiSessionId());

            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(ImtdRsContractDetailsFr.class);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.ITEM_MASTER_SID, itemMasterId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.USER_SID, userId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.SESSION_ID, sessionId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.OPERATION, Constants.STATUS_D));
            final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
            projectionList.add(ProjectionFactoryUtil.property(Constants.FORMULA_ID));
            ifpDynamicQuery.setProjection(projectionList);

            list = ImtdRsContractDetailsFrLocalServiceUtil.dynamicQuery(ifpDynamicQuery);

        } catch (SystemException ex) {
            LOGGER.error(ex);
        }

        return list;
    }

    public List<ImtdContRsDetailsFrDTO> getCustomisedImtdContRsDetailsFr(List<ImtdRsContractDetailsFr> list) {
        List<ImtdContRsDetailsFrDTO> resultList = new ArrayList<ImtdContRsDetailsFrDTO>();
        ImtdContRsDetailsFrDTO dto;
        for (ImtdRsContractDetailsFr bean : list) {
            try {
                dto = new ImtdContRsDetailsFrDTO();
                dto.setImtdContRsDetailsFrSid(bean.getImtdRsContractDetailsFrSid());
                dto.setImtdContRsDetailsSid(bean.getImtdItemPriceRebateDetailsSid());
                dto.setItemMasterSid(bean.getItemMasterSid());
                dto.setFormulaMethodId(bean.getFormulaMethodId());
                dto.setContRsDetailsSid(bean.getRsContractDetailsSid());
                dto.setContRsDetailsFrSid(bean.getRsContractDetailsFrSid());
                dto.setFormulaId(bean.getFormulaId());

                DynamicQuery query = DynamicQueryFactoryUtil.forClass(FormulaDetailsMaster.class);
                query.add(RestrictionsFactoryUtil.eq(Constants.FORMULA_ID, String.valueOf(bean.getFormulaId())));

                List<FormulaDetailsMaster> formulaList = FormulaDetailsMasterLocalServiceUtil.dynamicQuery(query);
                if (formulaList != null && !formulaList.isEmpty()) {
                    dto.setFormulaName(formulaList.get(0).getFormulaDesc());
                }
                resultList.add(dto);
            } catch (SystemException ex) {
                LOGGER.error(ex);
            }
        }
        return resultList;
    }

    public void removeImtdRsDetailsFr(ImtdContRsDetailsFrDTO dto) {
        try {
            ImtdRsContractDetailsFr bean = ImtdRsContractDetailsFrLocalServiceUtil.getImtdRsContractDetailsFr(dto.getImtdContRsDetailsFrSid());

            bean.setOperation(Constants.STATUS_D);

            ImtdRsContractDetailsFrLocalServiceUtil.updateImtdRsContractDetailsFr(bean);

        } catch (PortalException ex) {
            LOGGER.error(ex);

        } catch (SystemException ex) {
            LOGGER.error(ex);

        }

    }

    public void loadFormulaToImtdRsdFr(int rsContractSId) {
        try {
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
            final String createdDate = String.valueOf(sessionDTO.getSessionDate());

            DynamicQuery query = DynamicQueryFactoryUtil.forClass(RsContractDetails.class);
            query.add(RestrictionsFactoryUtil.eq("rsContractSid", rsContractSId));
            query.add(RestrictionsFactoryUtil.ne("inboundStatus", Constants.STATUS_D));
            List<RsContractDetails> list = RsDetailsLocalServiceUtil.dynamicQuery(query);

            for (RsContractDetails bean : list) {
                ImtdItemPriceRebateDetailsLocalServiceUtil.insertFormulaToContractRsdFrImtd(bean.getRsContractDetailsSid(), userId, sessionId, createdDate);
            }

        } catch (SystemException ex) {
            LOGGER.error(ex);
        }

    }

    public void addAllFormulas(int itemSid) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String createdDate = String.valueOf(sessionDTO.getSessionDate());
        ImtdItemPriceRebateDetailsLocalServiceUtil.addAllFormulaToContractRsdFrImtd(itemSid, userId, sessionId, createdDate);
    }

    public void removeAllFormulas(int itemSid) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String createdDate = String.valueOf(sessionDTO.getSessionDate());
        ImtdItemPriceRebateDetailsLocalServiceUtil.remaoveAllFormulaToContractRsdFrImtd(itemSid, userId, sessionId, createdDate);
    }

    public List<RSFormulaDTO> getLoadForecastingFormula() {

        List<RSFormulaDTO> resultList = new ArrayList<RSFormulaDTO>();

        final DynamicQuery forecastingFormula = DynamicQueryFactoryUtil.forClass(ForecastingFormula.class);

        List<ForecastingFormula> list;
        try {
            list = ForecastingFormulaLocalServiceUtil.dynamicQuery(forecastingFormula);
            resultList = getCustomisedForecastingFormula(list);
        } catch (SystemException ex) {
          LOGGER.error(ex.getMessage());
        }

        return resultList;
    }

    public List<ImtdContRsDetailsFrDTO> getLoadImtdContRsDetailsFr(int itemMasterId) {
        List<ImtdContRsDetailsFrDTO> resultList = new ArrayList<ImtdContRsDetailsFrDTO>();
        try {

            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));

            final String sessionId = String.valueOf(sessionDTO.getUiSessionId());

            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(ImtdRsContractDetailsFr.class);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.ITEM_MASTER_SID, itemMasterId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.USER_SID, userId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.SESSION_ID, sessionId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.OPERATION, Constants.STATUS_D));
            List<ImtdRsContractDetailsFr> list = ImtdRsContractDetailsFrLocalServiceUtil.dynamicQuery(ifpDynamicQuery);

            resultList = getCustomisedImtdContRsDetailsFr(list);

        } catch (SystemException ex) {
            LOGGER.error(ex);
        }

        return resultList;
    }

    public ImtdContRsDetailsFrDTO addToSelectedFormulaList(RSFormulaDTO formula, int imtdContRsdSid, int contRsdFrSid, int contRsdSid, int itemSid, String itemId) {
        ImtdContRsDetailsFrDTO selectedDto = new ImtdContRsDetailsFrDTO();
        try {
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(FormulaDetailsMaster.class);
            query.add(RestrictionsFactoryUtil.ilike("formulaDesc", formula.getFormulaName()));
            List<FormulaDetailsMaster> list = FormulaDetailsMasterLocalServiceUtil.dynamicQuery(query);
            if (list != null && !list.isEmpty()) {
                selectedDto.setFormulaId(Integer.valueOf(list.get(0).getFormulaId()));
                selectedDto.setFormulaName(list.get(0).getFormulaDesc());
            }
            selectedDto.setImtdContRsDetailsSid(imtdContRsdSid);
            selectedDto.setContRsDetailsFrSid(contRsdFrSid);
            selectedDto.setContRsDetailsSid(contRsdSid);
            selectedDto.setItemMasterSid(itemSid);

        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return selectedDto;
    }

    public void removeAllItemsFormula(int itemSid) {
        try {
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(ImtdRsContractDetailsFr.class);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.ITEM_MASTER_SID, itemSid));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.USER_SID, userId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.SESSION_ID, sessionId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.OPERATION, Constants.STATUS_D));

            List<ImtdRsContractDetailsFr> list = ImtdRsContractDetailsFrLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
            for (ImtdRsContractDetailsFr bean : list) {
                bean.setOperation(Constants.STATUS_D);
                ImtdRsContractDetailsFrLocalServiceUtil.updateImtdRsContractDetailsFr(bean);
            }
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }

    public void saveToImtdFormulaRsDetails(ImtdContRsDetailsFrDTO formula, int imtdContRsdSid, int rsdContFrSid, int rsdContSid, int itemSid) {

        ImtdRsContractDetailsFr bean = ImtdRsContractDetailsFrLocalServiceUtil.createImtdRsContractDetailsFr(0);
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        try {
            if (tempContractTableFormulaValidation(itemSid, formula.getFormulaId()) == Constants.ZERO) {
                bean.setImtdItemPriceRebateDetailsSid(imtdContRsdSid);
                bean.setRsContractDetailsFrSid(rsdContFrSid);
                bean.setRsContractDetailsSid(rsdContSid);
                bean.setItemMasterSid(itemSid);
                bean.setOperation("A");
                bean.setFormulaMethodId(formula.getFormulaMethodId());
                bean.setUsersId(userId);
                bean.setSessionId(sessionId);
                bean.setImtdCreatedDate(new Date());
                bean.setFormulaId(formula.getFormulaId());
                ImtdRsContractDetailsFrLocalServiceUtil.addImtdRsContractDetailsFr(bean);
            } else {
                final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                        .forClass(ImtdRsContractDetailsFr.class);
                ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.ITEM_MASTER_SID, itemSid));
                ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.FORMULA_ID, formula.getFormulaId()));
                ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.USER_SID, userId));
                ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.SESSION_ID, sessionId));
                List<ImtdRsContractDetailsFr> list = ImtdRsContractDetailsFrLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
                bean.setImtdRsContractDetailsFrSid(list.get(0).getImtdRsContractDetailsFrSid());
                bean.setImtdItemPriceRebateDetailsSid(list.get(0).getImtdItemPriceRebateDetailsSid());
                bean.setRsContractDetailsFrSid(list.get(0).getRsContractDetailsFrSid());
                bean.setRsContractDetailsSid(list.get(0).getRsContractDetailsSid());
                bean.setItemMasterSid(itemSid);
                bean.setOperation("U");
                bean.setFormulaMethodId(formula.getFormulaMethodId());
                bean.setUsersId(userId);
                bean.setSessionId(sessionId);
                bean.setFormulaId(formula.getFormulaId());
                bean.setImtdCreatedDate(new Date());
                ImtdRsContractDetailsFrLocalServiceUtil.updateImtdRsContractDetailsFr(bean);
            }
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }

    public int getDeductionCount(final ErrorfulFieldGroup searchFields, final Set<Container.Filter> filterSet) throws Exception {
        int count = 0;
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder = buildDeductionSearchQuery(searchFields, true);
        queryBuilder = getDeductionFilterQuery(filterSet, queryBuilder);

        List<Object> masterData = (List<Object>) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        if (masterData != null && !masterData.isEmpty()) {
            Object ob = masterData.get(0);
            count += Integer.valueOf(String.valueOf(ob));
        }
        LOGGER.info(" getDeductionCount returns count=" + count);
        return count;
    }

    public List<RsDeductionLookupDto> loadDeductionResults(
            final ErrorfulFieldGroup searchFields, final int start, final int end, final List<SortByColumn> columns, final Set<Container.Filter> filterSet) throws SystemException, Exception {
        List<RsDeductionLookupDto> searchList = new ArrayList<>();
        LOGGER.info("Entering loadDeductionResults with start of=" + start + "and endIndex of= " + end + "  Column Size +" + ((columns == null) ? columns : columns.size()));
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder = buildDeductionSearchQuery(searchFields, false);
        queryBuilder = getDeductionFilterQuery(filterSet, queryBuilder);
        queryBuilder = getDeductionOrderQuery(queryBuilder, columns, start, end);

        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        searchList = getCustomizedDeductionDTO(list);
        LOGGER.info("End of loadDeductionResults");
        return searchList;
    }
    
    private StringBuilder buildDeductionSearchQuery(ErrorfulFieldGroup searchFields, boolean isCount) {
        StringBuilder queryBuilder = new StringBuilder();
        String query = isCount ? "COUNT( * )" : constantProperties.getString("rsdeductionLookup");
        queryBuilder.append(" SELECT ").append(query).append(" FROM DEDUCTION_CALENDAR_MASTER where INBOUND_STATUS <> 'D' ");
        if (deductionCriteria.isEmpty()) {
            loadDeductionCriteriaMap();
        }
        Set<String> keys = deductionCriteria.keySet();
        for (String fields : keys) {
          
            if (searchFields.getField(fields) != null) {
                if (searchFields.getField(fields).getValue() != null && !ConstantUtil.SELECT_ONE.equals(String.valueOf(searchFields.getField(fields).getValue())) && !String.valueOf(searchFields.getField(fields).getValue()).trim().isEmpty()) {
                    if ("category".equalsIgnoreCase(fields)) {
                        queryBuilder.append(" AND ").append(deductionCriteria.get(fields)).append(" = '").append(checkEmptyDataFromFields(fields, searchFields) ? com.stpl.app.util.Constants.ZERO : ((HelperDTO) searchFields.getField(fields).getValue()).getId()).append("'");
                    } else {
                        queryBuilder.append(" AND ").append(deductionCriteria.get(fields)).append(" LIKE '").append(String.valueOf(searchFields.getField(fields).getValue()).trim().replace("*", Constants.PERCENT)).append("'");
                    }
                }
            }
        }
        return queryBuilder;
    }
    
    private StringBuilder getDeductionFilterQuery(final Set<Container.Filter> filterSet, final StringBuilder stringBuilder) throws ParseException {
        if (deductionFilter.isEmpty()) {
            deductionFilter.clear();
            deductionFilter.put("deductionNo", "DEDUCTION_CALENDAR_NO");
            deductionFilter.put("deductionName", "DEDUCTION_CALENDAR_NAME");
            deductionFilter.put("deductionDesc", "DEDUCTION_CALENDAR_DESC");
            deductionFilter.put("deductionCategory", "CATEGORY");
            deductionFilter.put("creationDate", "CREATED_DATE");
            deductionFilter.put("createdBy", "CREATED_BY");
            deductionFilter.put("modifiedDate", "MODIFIED_DATE");
            deductionFilter.put("modifiedBy", "MODIFIED_BY");
        }
        final SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (filterSet != null) {

            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                   
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    stringBuilder.append(" AND ").append(deductionFilter.get(String.valueOf(stringFilter.getPropertyId()))).append(" LIKE '%").append(stringFilter.getFilterString()).append("%'");
               
                } else if (filter instanceof Between) {

                    Between stringFilter = (Between) filter;
                    String startValue = dbDateFormat.format(stringFilter.getStartValue());
                    String endValue = dbDateFormat.format(stringFilter.getEndValue());

                    if (startValue != null) {
                        stringBuilder.append(" AND ").append(deductionFilter.get(stringFilter.getPropertyId().toString())).append(" >= '").append(startValue).append("' ");
                    }
                    if (endValue != null) {
                        stringBuilder.append(" AND ").append(deductionFilter.get(stringFilter.getPropertyId().toString())).append(" <= '").append(endValue).append("' ");
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (stringFilter.getValue() instanceof Date) {
                        String filterString = dbDateFormat.format(stringFilter.getValue());
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            stringBuilder.append(" AND ").append(deductionFilter.get(String.valueOf(stringFilter.getPropertyId()))).append(" >= '").append(filterString).append("' ");
                        } else {
                            stringBuilder.append(" AND ").append(deductionFilter.get(String.valueOf(stringFilter.getPropertyId()))).append(" <= '").append(filterString).append("' ");
                        }
                    }
                }
            }
        }

        return stringBuilder;
    }

    private StringBuilder getDeductionOrderQuery(final StringBuilder stringBuilder, final List<SortByColumn> sortByColumns, final int startIndex, final int endIndex) {
        boolean sortOrder = false;
        String orderByColumn = null;
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                orderByColumn = deductionFilter.get(sortByColumn.getName());
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
    
    private List<RsDeductionLookupDto> getCustomizedDeductionDTO(List list) {
        final List<RsDeductionLookupDto> searchResultsList = new ArrayList<>();
        try {
            if (list != null) {
                Map<Integer, String> userMap = StplSecurity.userMap;
                if (userMap.isEmpty()) {
                    userMap = StplSecurity.getUserName();
                }
                for (Object deductionList : list) {
                    final RsDeductionLookupDto searchDTO = new RsDeductionLookupDto();
                    final Object[] object = (Object[]) deductionList;
                    searchDTO.setDeductionSystemId(!Constants.NULL.equals(String.valueOf(object[0])) && StringUtils.isNotBlank(String.valueOf(object[0])) ? String.valueOf(object[0]) : "0");
                    searchDTO.setDeductionNo(!Constants.NULL.equals(String.valueOf(object[1])) && StringUtils.isNotBlank(String.valueOf(object[1])) ? String.valueOf(object[1]) : StringUtils.EMPTY);
                    searchDTO.setDeductionName(!Constants.NULL.equals(String.valueOf(object[2])) && StringUtils.isNotBlank(String.valueOf(object[2])) ? String.valueOf(object[2]) : StringUtils.EMPTY);
                    searchDTO.setDeductionDesc(!Constants.NULL.equals(String.valueOf(object[3])) && StringUtils.isNotBlank(String.valueOf(object[3])) ? String.valueOf(object[3]) : StringUtils.EMPTY);
                    if (object[4] != null) {
                        searchDTO.setCategory((helperListUtil.getIdHelperDTOMap().get(object[4] != null ? Integer.valueOf(String.valueOf(object[4])) : 0)));
                        searchDTO.setDeductionCategory(ConstantsUtils.SELECT_ONE.equals(searchDTO.getCategory().getDescription()) ? StringUtils.EMPTY : searchDTO.getCategory().getDescription());
                    }
                    searchDTO.setCreatedBy(object[5] != null ? userMap.get(Integer.valueOf(String.valueOf(object[5]))) : StringUtils.EMPTY);
                    if (object[6] != null) {
                        Date createdDate = (Date) object[6];
                        searchDTO.setCreatedDate(CommonUtils.convertDateToString(createdDate));
                        DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                        createdDate = df.parse(searchDTO.getCreatedDate());
                        searchDTO.setCreationDate(createdDate);
                    }
                    searchDTO.setCreatedBy(object[7] != null ? userMap.get(Integer.valueOf(String.valueOf(object[7]))) : StringUtils.EMPTY);
                    if (object[8] != null) {
                        Date modifiedDate = (Date) object[8];
                        searchDTO.setModifyDate(CommonUtils.convertDateToString(modifiedDate));
                        DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                        modifiedDate = df.parse(searchDTO.getModifyDate());
                        searchDTO.setModifiedDate(modifiedDate);
                    }

                    searchResultsList.add(searchDTO);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return searchResultsList;
    }
    
    private void loadDeductionCriteriaMap() {
        deductionCriteria.clear();
        deductionCriteria.put("deductionNo", "DEDUCTION_CALENDAR_NO");
        deductionCriteria.put("deductionName", "DEDUCTION_CALENDAR_NAME");
        deductionCriteria.put("deductionDesc", "DEDUCTION_CALENDAR_DESC");
        deductionCriteria.put("category", "CATEGORY");
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
            TextField textField = (TextField)binder.getField(fieldName);
            isEmpty = StringUtils.isBlank(textField.getValue()) || Constants.NULL.equals(textField.getValue());
        }
        if (binder.getField(fieldName) instanceof ComboBox) {
            ComboBox comboBox = (ComboBox) binder.getField(fieldName);
            if (comboBox.getValue() instanceof HelperDTO) {
                isEmpty = comboBox.getValue() == null || Constants.SELECT_ONE.equals(((HelperDTO) comboBox.getValue()).getDescription());
            } else if ( comboBox.getValue() instanceof HelperDTO) {
                isEmpty = comboBox.getValue() == null || Constants.SELECT_ONE.equals(((HelperDTO) comboBox.getValue()).getDescription());
            } else if( comboBox.getValue() == null){
                isEmpty = true;
            }
        }      
        return isEmpty;
    }
}
