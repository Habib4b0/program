package com.stpl.app.global.rebateplan.logic;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.company.util.CommonUtils;
import com.stpl.app.global.dao.impl.RebatePlanSearchLogicDAOImpl;
import com.stpl.app.global.rebateplan.dto.RebatePlanMasterDTO;
import com.stpl.app.global.rebateplan.dto.RebatePlanTierResults;
import com.stpl.app.model.CdrModel;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ItemPricingQualifier;
import com.stpl.app.model.NetSalesFormulaMaster;
import com.stpl.app.model.RebatePlanMaster;
import com.stpl.app.model.RebatePlanTier;
import com.stpl.app.model.RebateTierFormula;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.service.CdrModelLocalServiceUtil;
import com.stpl.app.service.FormulaDetailsMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ItemPricingQualifierLocalServiceUtil;
import com.stpl.app.service.NetSalesFormulaMasterLocalServiceUtil;
import com.stpl.app.service.RebatePlanMasterLocalServiceUtil;
import com.stpl.app.service.RebatePlanTierLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.ui.UDCIncrementCheck;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.GeneralCommonUtils;
import com.stpl.app.util.HelperDTO;
import com.stpl.app.util.NotesTabLogic;
import com.stpl.domain.global.rebateplan.RebatePlanDAO;
import com.stpl.domain.global.rebateplan.RebatePlanLogic;
import com.stpl.ifs.ui.NotesDTO;
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
import com.vaadin.ui.TextField;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.OrderByColumn.Type;

// TODO: Auto-generated Javadoc
/**
 * The Class RebatePlanSearchLogic.
 */
public class RebatePlanSearchLogic extends BeanItemContainer<RebatePlanMaster> implements RebatePlanLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(RebatePlanSearchLogic.class);
    /**
     * The dao.
     */
    private static RebatePlanDAO dao = new RebatePlanSearchLogicDAOImpl();
    /**
     * The rebate plan name.
     *
     * @return the dao
     */
    private String rebatePlanName;
    /**
     * The result value.
     */
    private int resultValue;
    /**
     * The results.
     */
    /**
     * The List of Rebate Plan Tier.
     */
    private List<RebatePlanTier> results;
    /**
     * The List of Helper Dto
     */
    List<HelperDTO> rebateStatus = new ArrayList<HelperDTO>();
    /**
     * The List of Helper Dto
     */
    List<HelperDTO> rebateType = new ArrayList<HelperDTO>();

    public static final SimpleDateFormat DB_DATE = new SimpleDateFormat("yyyy-MM-dd");    public static ResourceBundle constantProperties = ResourceBundle.getBundle("properties.constants");

    /**
     * Gets the rebate plan name.
     *
     * @return the rebate plan name
     */
    public String getRebatePlanName() {
        return rebatePlanName;
    }

    /**
     * Sets the rebate plan name.
     *
     * @param rebatePlanName the new rebate plan name
     */
    public void setRebatePlanName(final String rebatePlanName) {
        this.rebatePlanName = rebatePlanName;
    }

    /**
     * Gets the result value.
     *
     * @return the result value
     */
    public int getResultValue() {
        return resultValue;
    }

    /**
     * Sets the result value.
     *
     * @param resultValue the new result value
     */
    public void setResultValue(final int resultValue) {
        this.resultValue = resultValue;
    }

    /**
     * Gets the results.
     *
     * @return the results
     */
    public List<RebatePlanTier> getResults() {
        return results;
    }

    /**
     * Sets the results.
     *
     * @param results the new results
     */
    public void setResults(final List<RebatePlanTier> results) {
        this.results = results;
    }

    /**
     * Gets the dao.
     *
     * @return the dao
     */
    public RebatePlanDAO getDao() {
        return dao;
    }

    /**
     * Sets the dao.
     *
     * @param dao the dao to set
     */
    public void setDao(final RebatePlanDAO dao) {
        this.dao = dao;
    }

    /**
     * The Constructor.
     */
    public RebatePlanSearchLogic() {
        super(RebatePlanMaster.class);

    }

    private NotesTabLogic rsLogic = new NotesTabLogic();

    private static HelperListUtil helperListUtil=HelperListUtil.getInstance();
    
    final DecimalFormat formatter = new DecimalFormat("#.00");
    
    /**
     * Search rebate plan.
     *
     * @param searchRebatePlanForm the search rebate plan form
     * @param start the start
     * @param end the end
     * @param columns the columns
     * @return the list< rebate plan master dt o>
     */
    @SuppressWarnings(ConstantsUtils.UNCHECK)
    public List<SearchResultsDTO> searchRebatePlan(
            final ErrorfulFieldGroup searchRebatePlanForm, final int start, final int end, final List<OrderByColumn> columns,final BeanSearchCriteria searchCriteria) throws SystemException, Exception {
        List<SearchResultsDTO> searchList = new ArrayList<SearchResultsDTO>();
        LOGGER.info("Entering searchRebatePlan with start of=" + start + "and endIndex of= " + end + "  Column Size +" + ((columns == null) ? columns : columns.size()));

        String rebatePlanId = StringUtils.EMPTY;
        String rebatePlanNo = StringUtils.EMPTY;
        String rebatePlanName = StringUtils.EMPTY;
        String rebatePlanStatus = StringUtils.EMPTY;
        String rebatePlanType = StringUtils.EMPTY;

        if (searchRebatePlanForm.getField(ConstantsUtils.TEXT1).getValue().toString() != null) {
            rebatePlanId = searchRebatePlanForm.getField(ConstantsUtils.TEXT1)
                    .getValue().toString().trim();
        }
        if (searchRebatePlanForm.getField(ConstantsUtils.TEXT2).getValue().toString() != null) {

            rebatePlanNo = searchRebatePlanForm.getField(ConstantsUtils.TEXT2)
                    .getValue().toString().trim();
        }
        if (searchRebatePlanForm.getField(ConstantsUtils.TEXT3).getValue()
                .toString() != null) {
            rebatePlanName = searchRebatePlanForm.getField(ConstantsUtils.TEXT3)
                    .getValue().toString().trim();
        }
        if (searchRebatePlanForm.getField(ConstantsUtils.COMBO1).getValue() != null) {

            rebatePlanStatus = String.valueOf(((HelperDTO)searchRebatePlanForm
                    .getField(ConstantsUtils.COMBO1).getValue()).getSystemId());
        }
        if (searchRebatePlanForm.getField(ConstantsUtils.COMBO2).getValue() != null) {
            rebatePlanType = String.valueOf(((HelperDTO)searchRebatePlanForm.getField(ConstantsUtils.COMBO2)
                    .getValue()).getSystemId());
        }
        final DynamicQuery rebatePlanDynamicQuery = DynamicQueryFactoryUtil
                .forClass(RebatePlanMaster.class);

        // Added to check INBOUND_STATUS is A. ETL Soft delete data should not be included, where INBOUND_STATUS is D
        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));

        if (StringUtils.isNotBlank(rebatePlanId)) {
            rebatePlanId = rebatePlanId.replace(GeneralCommonUtils.CHAR_ASTERISK,
                    GeneralCommonUtils.CHAR_PERCENT);
            rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(
                    ConstantsUtils.REBATE_PLAN_ID, rebatePlanId));
        }
        if (StringUtils.isNotBlank(rebatePlanNo)) {
            rebatePlanNo = rebatePlanNo.replace(GeneralCommonUtils.CHAR_ASTERISK,
                    GeneralCommonUtils.CHAR_PERCENT);
            rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(
                    ConstantsUtils.REBATE_PLAN_NO, rebatePlanNo));
        }
        if (StringUtils.isNotBlank(rebatePlanName)) {
            rebatePlanName = rebatePlanName.replace(GeneralCommonUtils.CHAR_ASTERISK,
                    GeneralCommonUtils.CHAR_PERCENT);
            rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(
                    ConstantsUtils.REBATE_PLAN_NAME, rebatePlanName));
        }
        if (StringUtils.isNotBlank(rebatePlanStatus)) {
            
            rebateStatus = getDropDownList(GeneralCommonUtils.STATUS);
            for (int i = 0; i < rebateStatus.size(); i++) {
                if (String.valueOf(rebateStatus.get(i).getId()).equals(rebatePlanStatus)) {
                    int ik = rebateStatus.get(i).getSystemId();
                    rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.REBATE_PLAN_STATUS, ik));
                }
            }
        }
        if (StringUtils.isNotBlank(rebatePlanType)) {
           
            rebateType = getDropDownList(GeneralCommonUtils.REBATE_PLAN_TYPE);
            for (int i = 0; i < rebateType.size(); i++) {
                if (String.valueOf(rebateType.get(i).getId()).equals(rebatePlanType)) {
                    int ik = rebateType.get(i).getSystemId();
                    rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.REBATE_PLAN_TYPE, ik));
                }
            }
        }
        for (final Iterator<OrderByColumn> iterator = columns.iterator(); iterator.hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
            if (orderByColumn.getType() == Type.ASC) {
                rebatePlanDynamicQuery.addOrder(OrderFactoryUtil.asc(orderByColumn.getName()));
            } else {
                rebatePlanDynamicQuery.addOrder(OrderFactoryUtil.desc(orderByColumn.getName()));
            }
        }

        if (searchCriteria != null && searchCriteria.getFilters() != null) {
            for (Container.Filter filter : searchCriteria.getFilters()) {

                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;                    
                    if (ConstantsUtils.REBATE_PLAN_ID.equals(stringFilter.getPropertyId())) {
                        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.REBATE_PLAN_ID, filterString));
                    }else         
                    if (ConstantsUtils.REBATE_PLAN_NO.equals(stringFilter.getPropertyId())) {
                        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.REBATE_PLAN_NO, filterString));
                    }else
                    if (ConstantsUtils.REBATE_PLAN_NAME.equals(stringFilter.getPropertyId())) {
                        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.REBATE_PLAN_NAME, filterString));
                    }else
                    if (ConstantsUtils.SECONDARY_REBATE_PLAN_ID.equals(stringFilter.getPropertyId())) {
                        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.SECONDARY_REBATE_PLAN_ID, filterString));
                    }else         
                    if (ConstantsUtils.SECONDARY_REBATE_PLAN_NO.equals(stringFilter.getPropertyId())) {
                        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.SECONDARY_REBATE_PLAN_NO, filterString));
                    }else
                    if (ConstantsUtils.SECONDARY_REBATE_PLAN_NAME.equals(stringFilter.getPropertyId())) {
                        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.SECONDARY_REBATE_PLAN_NAME, filterString));
                    }
                   
                    

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;

                    if (stringFilter.getValue() instanceof Integer) {
                        Integer filterValue = (Integer) stringFilter.getValue();
                        if (ConstantsUtils.REBATE_PLAN_STATUS.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_PLAN_STATUS, filterValue));
                            }
                        }else
                        if (ConstantsUtils.REBATE_PLAN_TYPE.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_PLAN_TYPE, filterValue));
                            }
                        }else
                        if (ConstantsUtils.REBATE_BASED_ON.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_BASED_ON, filterValue));
                            }
                        }else
                        if (ConstantsUtils.REBATE_STRUCTURE.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_STRUCTURE, String.valueOf(filterValue)));
                            }
                        }else
                        if (ConstantsUtils.REBATE_RANGE_BASED_ON.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_RANGE_BASED_ON, filterValue));
                            }
                        }  
                    } 
                } 
            }
        }
        
        rebatePlanDynamicQuery.setLimit(start, end);

        final List<RebatePlanMaster> list = dao.getRebatePlanMasterList(rebatePlanDynamicQuery);
        LOGGER.info("After Query Hit,list size for Search results is = " + ((list == null) ? list : list.size()));
        searchList = getCustomizedSearchFormFromModel(list);
        LOGGER.info("Ends searchRebatePlan wiht search size ----" + ((searchList == null) ? searchList : searchList.size()));

        return searchList;

    }

    /**
     * Gets the dynamic query search.
     *
     * @param searchItemForm the search item form
     * @param searchCriteria
     * @return the dynamic query search
     */
    public DynamicQuery getDynamicQuerySearch(final ErrorfulFieldGroup searchItemForm,final BeanSearchCriteria searchCriteria) throws Exception {
        LOGGER.info("Enters getDynamicQuerySearch p1:ErrorfulFieldGroup");
        String rebatePlanId = StringUtils.EMPTY;
        String rebatePlanNo = StringUtils.EMPTY;
        String rebatePlanName = StringUtils.EMPTY;
        int rebatePlanStatus = 0;
        int rebatePlanType = 0;
        final DynamicQuery rebatePlanDynamicQuery = DynamicQueryFactoryUtil
                .forClass(RebatePlanMaster.class);

        // Added to check INBOUND_STATUS is A. ETL Soft delete data should not be included, where INBOUND_STATUS is D
        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));

        if (searchItemForm.getField(ConstantsUtils.TEXT1).getValue().toString() != null) {
            rebatePlanId = searchItemForm.getField(ConstantsUtils.TEXT1).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantsUtils.TEXT2).getValue().toString() != null) {
            rebatePlanNo = searchItemForm.getField(ConstantsUtils.TEXT2).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantsUtils.TEXT3).getValue().toString() != null) {
            rebatePlanName = searchItemForm.getField(ConstantsUtils.TEXT3)
                    .getValue().toString().trim();
        }
        if (searchItemForm.getField(ConstantsUtils.COMBO1).getValue() != null) {
            rebatePlanStatus = ((HelperDTO)(searchItemForm.getField(ConstantsUtils.COMBO1)
                    .getValue())).getSystemId();
        }
        if (searchItemForm.getField(ConstantsUtils.COMBO2).getValue() != null) {
            rebatePlanType = ((HelperDTO)(searchItemForm.getField(ConstantsUtils.COMBO2)
                    .getValue())).getSystemId();
        }
        if (StringUtils.isNotBlank(rebatePlanId)) {
            rebatePlanId = rebatePlanId.replace(GeneralCommonUtils.CHAR_ASTERISK,
                    GeneralCommonUtils.CHAR_PERCENT);
            rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(
                    ConstantsUtils.REBATE_PLAN_ID, rebatePlanId));
        }
        if (StringUtils.isNotBlank(rebatePlanNo)) {
            rebatePlanNo = rebatePlanNo.replace(GeneralCommonUtils.CHAR_ASTERISK,
                    GeneralCommonUtils.CHAR_PERCENT);
            rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(
                    ConstantsUtils.REBATE_PLAN_NO, rebatePlanNo));
        }
        if (StringUtils.isNotBlank(rebatePlanName)) {
            rebatePlanName = rebatePlanName.replace(GeneralCommonUtils.CHAR_ASTERISK,
                    GeneralCommonUtils.CHAR_PERCENT);
            rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(
                    ConstantsUtils.REBATE_PLAN_NAME, rebatePlanName));
        }
        if (rebatePlanStatus != 0) {
            rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.REBATE_PLAN_STATUS, rebatePlanStatus));
        }
        if (rebatePlanType != 0) {
            rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.REBATE_PLAN_TYPE, rebatePlanType));
        }
        LOGGER.info("Ends getDynamicQuerySearch with rebatePlanDynamicQuery");

        if (searchCriteria != null && searchCriteria.getFilters() != null) {
            for (Container.Filter filter : searchCriteria.getFilters()) {

                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;                    
                    if (ConstantsUtils.REBATE_PLAN_ID.equals(stringFilter.getPropertyId())) {
                        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.REBATE_PLAN_ID, filterString));
                    }         
                    if (ConstantsUtils.REBATE_PLAN_NO.equals(stringFilter.getPropertyId())) {
                        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.REBATE_PLAN_NO, filterString));
                    }
                    if (ConstantsUtils.REBATE_PLAN_NAME.equals(stringFilter.getPropertyId())) {
                        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.REBATE_PLAN_NAME, filterString));
                    }
                    if (ConstantsUtils.SECONDARY_REBATE_PLAN_ID.equals(stringFilter.getPropertyId())) {
                        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.SECONDARY_REBATE_PLAN_ID, filterString));
                    }         
                    if (ConstantsUtils.SECONDARY_REBATE_PLAN_NO.equals(stringFilter.getPropertyId())) {
                        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.SECONDARY_REBATE_PLAN_NO, filterString));
                    }
                    if (ConstantsUtils.SECONDARY_REBATE_PLAN_NAME.equals(stringFilter.getPropertyId())) {
                        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.SECONDARY_REBATE_PLAN_NAME, filterString));
                    }
                   
                    

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;

                    if (stringFilter.getValue() instanceof Integer) {
                        Integer filterValue = (Integer) stringFilter.getValue();
                        if (ConstantsUtils.REBATE_PLAN_STATUS.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_PLAN_STATUS, filterValue));
                            }
                        }else
                        if (ConstantsUtils.REBATE_PLAN_TYPE.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_PLAN_TYPE, filterValue));
                            }
                        }else
                        if (ConstantsUtils.REBATE_BASED_ON.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_BASED_ON, filterValue));
                            }
                        }else
                        if (ConstantsUtils.REBATE_STRUCTURE.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_STRUCTURE, String.valueOf(filterValue)));
                            }
                        }else
                        if (ConstantsUtils.REBATE_RANGE_BASED_ON.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_RANGE_BASED_ON, filterValue));
                            }
                        }  
                    }  
                } 
            }
        }
        
        return rebatePlanDynamicQuery;
        }

    /**
     * Gets the customized search form from model.
     *
     * @param list the list
     * @return the customized search form from model
     */
    public List<SearchResultsDTO> getCustomizedSearchFormFromModel(
            final List<RebatePlanMaster> list) throws Exception {
        final List<SearchResultsDTO> searchRebatePlanList = new ArrayList<SearchResultsDTO>();
        Map<Integer, String> hm = GeneralCommonUtils.getCodeDescription();

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final SearchResultsDTO searchRebatePlanDTO = new SearchResultsDTO();
                final RebatePlanMaster rebatePlanMaster = list.get(i);

                if (StringUtils.isNotBlank(rebatePlanMaster.getRebateStructure()) && !ConstantsUtils.ZERO.equals(rebatePlanMaster.getRebateStructure())) {
                    searchRebatePlanDTO.setRebateStructure(hm.get(Integer.parseInt(rebatePlanMaster.getRebateStructure())));
                }
                searchRebatePlanDTO.setSecondaryRebatePlanNo(rebatePlanMaster.getSecondaryRebatePlanNo());
                if (rebatePlanMaster.getRebateRangeBasedOn() != 0) {
                    searchRebatePlanDTO.setRebateRangeBasedOn(hm.get(rebatePlanMaster.getRebateRangeBasedOn()));
                }
                searchRebatePlanDTO.setCreatedBy(String.valueOf(rebatePlanMaster.getCreatedBy()));
                if(StringUtils.isNotBlank(String.valueOf(rebatePlanMaster.getRebateBasedOn())) && !ConstantsUtils.ZERO.equals(rebatePlanMaster.getRebateBasedOn())) {
                    searchRebatePlanDTO.setRebateBasedOn(hm.get(Integer.parseInt(String.valueOf(rebatePlanMaster.getRebateBasedOn()))));
                }
                if (rebatePlanMaster.getRebatePlanType() != 0) {
                    searchRebatePlanDTO.setRebatePlanType(hm.get(rebatePlanMaster.getRebatePlanType()));
                }
                searchRebatePlanDTO.setRebatePlanId(rebatePlanMaster.getRebatePlanId());
                searchRebatePlanDTO.setModifiedBy(String.valueOf(rebatePlanMaster.getModifiedBy()));
                searchRebatePlanDTO.setSecondaryRebatePlanId(rebatePlanMaster.getSecondaryRebatePlanId());
                if (rebatePlanMaster.getRebatePlanStatus() != 0) {
                    searchRebatePlanDTO.setRebatePlanStatus(hm.get(rebatePlanMaster.getRebatePlanStatus()));
                }
                searchRebatePlanDTO.setSecondaryRebatePlanName(rebatePlanMaster.getSecondaryRebatePlanName());
                searchRebatePlanDTO.setSystemID(String.valueOf(rebatePlanMaster.getRebatePlanMasterSid()));
                searchRebatePlanDTO.setRecordLockStatus(rebatePlanMaster.getRecordLockStatus());
                searchRebatePlanDTO.setRebatePlanName(rebatePlanMaster.getRebatePlanName());
                searchRebatePlanDTO.setBatchId(rebatePlanMaster.getBatchId());
                if(rebatePlanMaster.getFormulaType()!=0){
                searchRebatePlanDTO.setFormulaType(getHelperTableValue(rebatePlanMaster.getFormulaType()).getDescription());
                }
                searchRebatePlanDTO.setRebatePlanNo(rebatePlanMaster.getRebatePlanNo());

                searchRebatePlanList.add(searchRebatePlanDTO);
            }
        }

        return searchRebatePlanList;
    }

    /**
     * Gets the rebate plan master by id.
     *
     * @param idValue the id value
     * @return the rebate plan master by id
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public RebatePlanMasterDTO getRebatePlanMasterById(final int idValue)
            throws PortalException, SystemException, Exception {
        LOGGER.info("Enters getRebatePlanMasterById p1:id " + idValue);
        RebatePlanMaster rebatePlanMaster = null;
        final RebatePlanMasterDTO rebatePlanMasterDTO = new RebatePlanMasterDTO();

        rebatePlanMaster = dao.getRebatePlanMasterBySystemId(idValue);
        rebatePlanMasterDTO.setRebatePlanSystemId(rebatePlanMaster.getRebatePlanMasterSid());
        rebatePlanMasterDTO.setSelfGrowthIndicator(rebatePlanMaster.getSelfGrowthIndicator());
        rebatePlanMasterDTO.setRebateStructure(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(rebatePlanMaster.getRebateStructure())));
        rebatePlanMasterDTO.setMarketShareFrom(rebatePlanMaster.getMarketShareFrom());

        rebatePlanMasterDTO.setSecondaryRebatePlanNo(rebatePlanMaster.getSecondaryRebatePlanNo());
        rebatePlanMasterDTO.setModifiedDate(rebatePlanMaster.getModifiedDate());
        rebatePlanMasterDTO.setRebateRangeBasedOn(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(rebatePlanMaster.getRebateRangeBasedOn())));
        rebatePlanMasterDTO.setRebateRule(rebatePlanMaster.getRebateRule());
        rebatePlanMasterDTO.setCreatedDate(rebatePlanMaster.getCreatedDate());
        rebatePlanMasterDTO.setCreatedBy(String.valueOf(rebatePlanMaster.getCreatedBy()));
        rebatePlanMasterDTO.setInboundStatus(rebatePlanMaster.getInboundStatus());
        rebatePlanMasterDTO.setRebateBasedOn(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(rebatePlanMaster.getRebateBasedOn())));
        rebatePlanMasterDTO.setRebatePlanType(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(rebatePlanMaster.getRebatePlanType())));       
        rebatePlanMasterDTO.setRebatePlanId(rebatePlanMaster.getRebatePlanId());
        rebatePlanMasterDTO.setModifiedBy(String.valueOf(rebatePlanMaster.getModifiedBy()));
        rebatePlanMasterDTO.setSecondaryRebatePlanId(rebatePlanMaster.getSecondaryRebatePlanId());
        rebatePlanMasterDTO.setMarketShareIndicator(rebatePlanMaster.getMarketShareIndicator());        
        rebatePlanMasterDTO.setMarketShareTo(rebatePlanMaster.getMarketShareTo());
        rebatePlanMasterDTO.setRebatePlanStatus(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(rebatePlanMaster.getRebatePlanStatus())));

        rebatePlanMasterDTO.setMarketShareReference(rebatePlanMaster.getMarketShareReference());
        rebatePlanMasterDTO.setSelfGrowthFrom(rebatePlanMaster.getSelfGrowthFrom());
        rebatePlanMasterDTO.setInternalNotes(rebatePlanMaster.getInternalNotes());
        rebatePlanMasterDTO.setSecondaryRebatePlanName(rebatePlanMaster.getSecondaryRebatePlanName());
        rebatePlanMasterDTO.setRebatePlanSystemId(rebatePlanMaster.getRebatePlanMasterSid());
        rebatePlanMasterDTO.setRecordLockStatus(rebatePlanMaster.getRecordLockStatus());
        rebatePlanMasterDTO.setRebatePlanName(rebatePlanMaster.getRebatePlanName());
        rebatePlanMasterDTO.setSelfGrowthReference(rebatePlanMaster.getSelfGrowthReference());
        rebatePlanMasterDTO.setBatchId(rebatePlanMaster.getBatchId());
        rebatePlanMasterDTO.setManufacturer(rebatePlanMaster.getManfCompanyMasterSid());
        rebatePlanMasterDTO.setNetSalesFormulaSid(rebatePlanMaster.getNetSalesFormulaMasterSid() == null ? 0 : Integer.valueOf(rebatePlanMaster.getNetSalesFormulaMasterSid()));
        if(rebatePlanMaster.getNetSalesFormulaMasterSid() != null){
            NetSalesFormulaMaster nsfMaster = getNetSalesFormulaMaster(Integer.valueOf(rebatePlanMaster.getNetSalesFormulaMasterSid()));
            rebatePlanMasterDTO.setNetSalesFormulaName(nsfMaster.getNetSalesFormulaName());
        }
        rebatePlanMasterDTO.setNetSalesRuleSid(rebatePlanMaster.getCdrModelSid() == null ? 0 : Integer.valueOf(rebatePlanMaster.getCdrModelSid()));
        if(rebatePlanMaster.getCdrModelSid() != null){
            CdrModel cdrModel = getCDRModel(Integer.valueOf(rebatePlanMaster.getCdrModelSid()));
            rebatePlanMasterDTO.setNetSalesRule(cdrModel.getRuleName());
        }
        
        rebatePlanMasterDTO.setFormulaType(getHelperTableValue(rebatePlanMaster.getFormulaType()).getDescription());

        rebatePlanMasterDTO.setSelfGrowthTo(rebatePlanMaster.getSelfGrowthTo());
        rebatePlanMasterDTO.setRebatePlanNo(rebatePlanMaster.getRebatePlanNo());
        LOGGER.info("Ends  getRebatePlanMasterById rebatePlanMasterDTO and its rebate plan id -----" + rebatePlanMasterDTO.getRebatePlanId());

        return rebatePlanMasterDTO;
    }
    
    
    private NetSalesFormulaMaster getNetSalesFormulaMaster(int systemId){
        try {
            return NetSalesFormulaMasterLocalServiceUtil.getNetSalesFormulaMaster(systemId);
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        
        return null;
    }
    
    private CdrModel getCDRModel(int systemId){
        try {
            return CdrModelLocalServiceUtil.getCdrModel(systemId);
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex);
        }
        
        return null;
    }

    /**
     * Getting the helper table description
     *
     * @param id - system id
     * @return
     *
     */
    private HelperDTO getHelperTableValue(int id) {
        HelperTable helpertable;
        HelperDTO dto = new HelperDTO();
        try {
            helpertable = HelperTableLocalServiceUtil.getHelperTable(id);
           
            dto.setId(helpertable.getHelperTableSid());
            dto.setDescription(helpertable.getDescription());

        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(RebatePlanSearchLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(RebatePlanSearchLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dto;
    }

    /**
     * Save rebate plan.
     *
     * @param rebatePlanForm the rebate plan form
     * @param rebatePlanTiers the rebate plan tiers
     * @return the string
     * @throws SystemException the system exception
     */
    @SuppressWarnings(ConstantsUtils.UNCHECK)
    public String saveRebatePlan(final ErrorfulFieldGroup rebatePlanForm, final BeanItemContainer<RebatePlanTierResults> rebatePlanTiers, final List<NotesDTO> availableUploadedInformation, final String addedNotes,final List<NotesDTO> removedDetailsList
                ,final SessionDTO sessionDTO) throws SystemException, IllegalAccessException, InvocationTargetException, PortalException, ParseException, Exception {

        LOGGER.info("Entering saveRebatePlan");
        String oldSystemId = (rebatePlanForm.getField(ConstantsUtils.REBATE_PLAN_SYSTEM_ID).getValue()!=null?rebatePlanForm.getField(ConstantsUtils.REBATE_PLAN_SYSTEM_ID).getValue().toString():null);
        String systemId;
        final DynamicQuery rebateIdDelDynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanMaster.class);
        rebateIdDelDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_PLAN_ID, rebatePlanForm.getField(ConstantsUtils.REBATE_PLAN_ID).getValue().toString().trim()));
        rebateIdDelDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
        final List<RebatePlanMaster> rebateIdDelMaster = dao.getRebatePlanMasterList(rebateIdDelDynamicQuery);
        
        if(rebateIdDelMaster.isEmpty()) {
            systemId = oldSystemId;
        } else {
            systemId = String.valueOf(rebateIdDelMaster.get(0).getRebatePlanMasterSid());
        }
        
        if (systemId == null || ConstantsUtils.NULL.equals(systemId) || ConstantsUtils.ZERO.equals(systemId)) {
            
//          First Tab
            final RebatePlanMaster rebatePlanMaster = RebatePlanMasterLocalServiceUtil.createRebatePlanMaster(0);
            rebatePlanMaster.setRebatePlanId(rebatePlanForm.getField(ConstantsUtils.REBATE_PLAN_ID).getValue().toString().trim());
            rebatePlanMaster.setRebatePlanNo(rebatePlanForm.getField(ConstantsUtils.REBATE_PLAN_NO).getValue().toString().trim());
            rebatePlanMaster.setRebatePlanName(rebatePlanForm.getField(ConstantsUtils.REBATE_PLAN_NAME).getValue().toString().trim());
            rebatePlanMaster.setRebatePlanStatus(((com.stpl.ifs.util.HelperDTO)rebatePlanForm.getField(ConstantsUtils.REBATE_PLAN_STATUS).getValue()).getId());
            if (rebatePlanForm.getField(ConstantsUtils.REBATE_PLAN_TYPE).getValue() != null) {
                rebatePlanMaster.setRebatePlanType(((com.stpl.ifs.util.HelperDTO) rebatePlanForm.getField(ConstantsUtils.REBATE_PLAN_TYPE).getValue()).getId());
            }
            String formulaType = String.valueOf(rebatePlanForm.getField(ConstantsUtils.FORMULA_TYPE).getValue());
            List<HelperDTO> formulaTypeList = getDropDownList(GeneralCommonUtils.REBATE_PLAN_FORMULA_TYPE);
            for (int i = 0; i < formulaTypeList.size(); i++) {
                if (formulaTypeList.get(i).getDescription().equals(formulaType)) {
                    int ik = formulaTypeList.get(i).getSystemId();
                    rebatePlanMaster.setFormulaType(ik);
                }
            }            
            
//          Second Tab
            rebatePlanMaster.setRebateStructure(String.valueOf(((com.stpl.ifs.util.HelperDTO)rebatePlanForm.getField(ConstantsUtils.REBATE_STRUCTURE).getValue()).getId()));            
            rebatePlanMaster.setRebateBasedOn(((com.stpl.ifs.util.HelperDTO)rebatePlanForm.getField(ConstantsUtils.REBATE_BASED_ON).getValue()).getId());
            rebatePlanMaster.setRebateRangeBasedOn(((com.stpl.ifs.util.HelperDTO)rebatePlanForm.getField(ConstantsUtils.REBATE_RANGE_BASED_ON).getValue()).getId());  
            String selfGrowthIndicator = ConstantsUtils.NULL.equals(String.valueOf(rebatePlanForm.getField(ConstantsUtils.SELF_GROWTH_INDICATOR).getValue())) || StringUtils.isBlank(rebatePlanForm.getField(ConstantsUtils.SELF_GROWTH_INDICATOR).getValue().toString())
                    ? StringUtils.EMPTY : String.valueOf(rebatePlanForm.getField(ConstantsUtils.SELF_GROWTH_INDICATOR).getValue());
            rebatePlanMaster.setSelfGrowthIndicator(selfGrowthIndicator.length() > 1 ? StringUtils.EMPTY : selfGrowthIndicator);
            rebatePlanMaster.setSelfGrowthReference(rebatePlanForm.getField(ConstantsUtils.SELF_GROWTH_REFERENCE).getValue().toString().trim());
            rebatePlanMaster.setSelfGrowthFrom((Date) rebatePlanForm.getField(ConstantsUtils.SELF_GROWTH_FROM).getValue());
            rebatePlanMaster.setSelfGrowthTo((Date) rebatePlanForm.getField(ConstantsUtils.SELF_GROWTH_TO).getValue());
            rebatePlanMaster.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
            String marketTypeIndicator = ConstantsUtils.NULL.equals(String.valueOf(rebatePlanForm.getField(ConstantsUtils.MARKET_SHARE_INDICATOR).getValue())) || StringUtils.isBlank(rebatePlanForm.getField(ConstantsUtils.SELF_GROWTH_INDICATOR).getValue().toString())
                    ? StringUtils.EMPTY : String.valueOf(rebatePlanForm.getField(ConstantsUtils.MARKET_SHARE_INDICATOR).getValue());
            rebatePlanMaster.setMarketShareIndicator(marketTypeIndicator.length() > 1 ? StringUtils.EMPTY : marketTypeIndicator );
            rebatePlanMaster.setMarketShareReference(rebatePlanForm.getField(ConstantsUtils.MARKET_SHARE_REFERENCE).getValue().toString().trim());
            rebatePlanMaster.setMarketShareFrom((Date) rebatePlanForm.getField(ConstantsUtils.MARKET_SHARE_FROM).getValue());
            rebatePlanMaster.setMarketShareTo((Date) rebatePlanForm.getField(ConstantsUtils.MARKET_SHARE_TO).getValue());            
            
            final String user = VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString();
            rebatePlanMaster.setCreatedDate(new Date());
            rebatePlanMaster.setCreatedBy(Integer.valueOf(user));
            rebatePlanMaster.setModifiedDate(new Date());
            rebatePlanMaster.setModifiedBy(Integer.valueOf(user));
            rebatePlanMaster.setRecordLockStatus(false);
            rebatePlanMaster.setInternalNotes(addedNotes);
            int nsfSid= Integer.valueOf(rebatePlanForm.getField(ConstantsUtils.NET_SALES_FORMULAID).getValue().toString());
            if(nsfSid!=0){
                rebatePlanMaster.setNetSalesFormulaMasterSid(String.valueOf(rebatePlanForm.getField(ConstantsUtils.NET_SALES_FORMULAID).getValue()));
            }
            int cdrSid= ((TextField)rebatePlanForm.getField(ConstantsUtils.NET_SALES_RULE)).getData() != null ? Integer.valueOf(((TextField)rebatePlanForm.getField(ConstantsUtils.NET_SALES_RULE)).getData().toString()) : 0;
            if(cdrSid!=0){
                rebatePlanMaster.setCdrModelSid(String.valueOf(cdrSid));
            }
            final DynamicQuery rebateDynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanMaster.class);
            rebateDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_PLAN_ID, rebatePlanMaster.getRebatePlanId()));
            rebateDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
            final List<RebatePlanMaster> rebateMaster = dao.getRebatePlanMasterList(rebateDynamicQuery);

            final DynamicQuery rebateNoDynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanMaster.class);
            rebateNoDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_PLAN_NO, rebatePlanMaster.getRebatePlanNo()));
            rebateNoDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
            final List<RebatePlanMaster> rebateNoMaster = dao.getRebatePlanMasterList(rebateNoDynamicQuery);

            final DynamicQuery rebateNameDynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanMaster.class);
            rebateNameDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_PLAN_NAME, rebatePlanMaster.getRebatePlanName()));
            rebateNameDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
            final List<RebatePlanMaster> rebateNameMaster = dao.getRebatePlanMasterList(rebateNameDynamicQuery);
            final RebatePlanMaster rebate;
            
            if (rebateMaster.size() < GeneralCommonUtils.ONE && rebateNoMaster.size() < GeneralCommonUtils.ONE && rebateNameMaster.size() < GeneralCommonUtils.ONE) {

             rebate = dao.saveRebatePlanMaster(rebatePlanMaster);
                sessionDTO.setSystemId(rebate.getRebatePlanMasterSid());
                if (rebate.getRebatePlanType() != 0) {
                    UDCIncrementCheck.increment(GeneralCommonUtils.getHelperDescription(rebate.getRebatePlanType()), GeneralCommonUtils.REBATE_PLAN_TYPE);
                }
                if (rebate.getRebatePlanStatus() != 0) {
                    UDCIncrementCheck.increment(GeneralCommonUtils.getHelperDescription(rebate.getRebatePlanStatus()), GeneralCommonUtils.REBATE_PLAN_STATUS);
                }
                if (rebate.getRebateBasedOn() != 0) {
                    UDCIncrementCheck.increment(rebate.getRebateBasedOn(), GeneralCommonUtils.REBATE_BASED_ON);
                }               
                if (rebate.getRebateStructure() != null) {
                    UDCIncrementCheck.increment(rebate.getRebateStructure(), GeneralCommonUtils.REBATE_STRUCTURE);
                }
                if (rebate.getRebateRangeBasedOn() != 0) {
                    UDCIncrementCheck.increment(GeneralCommonUtils.getHelperDescription(rebate.getRebateRangeBasedOn()), GeneralCommonUtils.REBATE_RANGE_BASED_ON);
                }
                rsLogic.saveUploadedInformation(availableUploadedInformation, "REBATE_PLAN_MASTER", rebate.getRebatePlanMasterSid());
            } else if (rebateNoMaster.size() >= GeneralCommonUtils.ONE) {
                return ConstantsUtils.DUPLICATENO;
            } else if (rebateNameMaster.size() >= GeneralCommonUtils.ONE) {
                return ConstantsUtils.DUPLICATENAME;
            } else {
                LOGGER.info(ConstantsUtils.DUPLICATE);
                return ConstantsUtils.DUPLICATE;
            }

             RebatePlanTier rebatePlanTier;
            for (int i = 0; i < rebatePlanTiers.size(); i++) {
                rebatePlanTier = RebatePlanTierLocalServiceUtil.createRebatePlanTier(0);
                final RebatePlanTierResults rebatePlanTierResult = (RebatePlanTierResults) rebatePlanTiers.getIdByIndex(i);
                BeanUtils.copyProperties(rebatePlanTier, rebatePlanTierResult);
                rebatePlanTier.setTierLevel(String.valueOf(i + 1));
                rebatePlanTier.setRebatePlanMasterSid(rebate.getRebatePlanMasterSid());
                rebatePlanTier.setRebatePlanTierId(String.valueOf(new Date().getTime()) + i);
                if(ConstantsUtils.DOLLAR.equals(rebatePlanTierResult.getTierOperatorValue())){
                    if(rebatePlanTierResult.getTierValueId().contains("$")){
                        String tiervalue=rebatePlanTierResult.getTierValueId().replace("$", "");
                        rebatePlanTier.setTierValue(Double.valueOf(tiervalue));
                        rebatePlanTier.setItemPricingQualifierSid(null);
                    }else{
                        rebatePlanTier.setItemPricingQualifierSid(rebatePlanTierResult.getTierValueId());
                        rebatePlanTier.setTierValue(0);
                    }
                }else if(ConstantsUtils.PERCENCTAGE.equals(rebatePlanTierResult.getTierOperatorValue())){
                       if(rebatePlanTierResult.getTierValueId().contains("%")){
                      String tiervalue=rebatePlanTierResult.getTierValueId().replace("%", "");
                        rebatePlanTier.setTierValue(Double.valueOf(tiervalue));
                        rebatePlanTier.setReturnRateSid(null);
                    }else{
                        rebatePlanTier.setReturnRateSid(String.valueOf(GeneralCommonUtils.getHelperCode("TIER_PERCENT_VALUE",rebatePlanTierResult.getTierValueId())));
                        rebatePlanTier.setTierValue(0);
                }
                }
                rebatePlanTier.setRecordLockStatus(false);
               
                final DynamicQuery rebatetierDynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanTier.class);
                rebatetierDynamicQuery.add(RestrictionsFactoryUtil.eq("rebatePlanTierId", rebatePlanTier.getRebatePlanTierId()));
                final RebatePlanTier tier = dao.saveRebatePlanTier(rebatePlanTier);
                
                if (tier.getTierOperator() != null) {
                    UDCIncrementCheck.increment(tier.getTierOperator(), GeneralCommonUtils.TIER_OPERATOR);
                }
            }
        } else {

            // Update Logic
            
            String sysId = systemId.replace(ConstantsUtils.COMMA, StringUtils.EMPTY);
            final int rebatePlanSystemId = Integer.valueOf(sysId);

            LOGGER.info("rebatePlanSystemId = " + rebatePlanSystemId);
//          First Tab
            RebatePlanMaster rebatePlanMaster  = dao.fetchRebatePlanMaster(rebatePlanSystemId);
            rebatePlanMaster.setRebatePlanId(rebatePlanForm.getField(ConstantsUtils.REBATE_PLAN_ID).getValue().toString().trim());
            rebatePlanMaster.setRebatePlanNo(rebatePlanForm.getField(ConstantsUtils.REBATE_PLAN_NO).getValue().toString().trim());
            rebatePlanMaster.setRebatePlanName(rebatePlanForm.getField(ConstantsUtils.REBATE_PLAN_NAME).getValue().toString().trim());
            rebatePlanMaster.setRebatePlanStatus(((com.stpl.ifs.util.HelperDTO)rebatePlanForm.getField(ConstantsUtils.REBATE_PLAN_STATUS).getValue()).getId());
            if (rebatePlanForm.getField(ConstantsUtils.REBATE_PLAN_TYPE).getValue() != null) {
                rebatePlanMaster.setRebatePlanType(((com.stpl.ifs.util.HelperDTO) rebatePlanForm.getField(ConstantsUtils.REBATE_PLAN_TYPE).getValue()).getId());
            }
            String formulaType = String.valueOf(rebatePlanForm.getField(ConstantsUtils.FORMULA_TYPE).getValue());
            List<HelperDTO> formulaTypeList = getDropDownList(GeneralCommonUtils.REBATE_PLAN_FORMULA_TYPE);
            for (int i = 0; i < formulaTypeList.size(); i++) {
                if (formulaTypeList.get(i).getDescription().equals(formulaType)) {
                    int ik = formulaTypeList.get(i).getSystemId();
                    rebatePlanMaster.setFormulaType(ik);
                }
            }
            
//          Second Tab
            rebatePlanMaster.setRebateStructure(String.valueOf(((com.stpl.ifs.util.HelperDTO)rebatePlanForm.getField(ConstantsUtils.REBATE_STRUCTURE).getValue()).getId()));
            rebatePlanMaster.setRebateBasedOn(((com.stpl.ifs.util.HelperDTO)rebatePlanForm.getField(ConstantsUtils.REBATE_BASED_ON).getValue()).getId());
            rebatePlanMaster.setRebateRangeBasedOn(((com.stpl.ifs.util.HelperDTO)rebatePlanForm.getField(ConstantsUtils.REBATE_RANGE_BASED_ON).getValue()).getId());            

            rebatePlanMaster.setSelfGrowthReference(rebatePlanForm.getField(ConstantsUtils.SELF_GROWTH_REFERENCE).getValue().toString().trim());
            rebatePlanMaster.setSelfGrowthFrom((Date) rebatePlanForm.getField(ConstantsUtils.SELF_GROWTH_FROM).getValue());
            rebatePlanMaster.setSelfGrowthTo((Date) rebatePlanForm.getField(ConstantsUtils.SELF_GROWTH_TO).getValue());
            String selfGrowthIndicator = ConstantsUtils.NULL.equals(String.valueOf(rebatePlanForm.getField(ConstantsUtils.SELF_GROWTH_INDICATOR).getValue())) || StringUtils.isBlank(rebatePlanForm.getField(ConstantsUtils.SELF_GROWTH_INDICATOR).getValue().toString())
                    ? StringUtils.EMPTY : String.valueOf(rebatePlanForm.getField(ConstantsUtils.SELF_GROWTH_INDICATOR).getValue());
            rebatePlanMaster.setSelfGrowthIndicator(selfGrowthIndicator.length() > 1 ? StringUtils.EMPTY : selfGrowthIndicator);

            String marketTypeIndicator = ConstantsUtils.NULL.equals(String.valueOf(rebatePlanForm.getField(ConstantsUtils.MARKET_SHARE_INDICATOR).getValue())) || StringUtils.isBlank(rebatePlanForm.getField(ConstantsUtils.MARKET_SHARE_INDICATOR).getValue().toString())
                    ? StringUtils.EMPTY : String.valueOf(rebatePlanForm.getField(ConstantsUtils.MARKET_SHARE_INDICATOR).getValue());
            rebatePlanMaster.setMarketShareIndicator(marketTypeIndicator.length() > 1 ? StringUtils.EMPTY : marketTypeIndicator );
            rebatePlanMaster.setMarketShareReference(rebatePlanForm.getField(ConstantsUtils.MARKET_SHARE_REFERENCE).getValue().toString().trim());
            rebatePlanMaster.setMarketShareFrom((Date) rebatePlanForm.getField(ConstantsUtils.MARKET_SHARE_FROM).getValue());
            rebatePlanMaster.setMarketShareTo((Date) rebatePlanForm.getField(ConstantsUtils.MARKET_SHARE_TO).getValue());            
            int nsfSid= Integer.valueOf(rebatePlanForm.getField(ConstantsUtils.NET_SALES_FORMULAID).getValue().toString());
            if(nsfSid!=0){
                rebatePlanMaster.setNetSalesFormulaMasterSid(String.valueOf(rebatePlanForm.getField(ConstantsUtils.NET_SALES_FORMULAID).getValue()));
            }
            int cdrSid= ((TextField)rebatePlanForm.getField(ConstantsUtils.NET_SALES_RULE)).getData() != null ? Integer.valueOf(((TextField)rebatePlanForm.getField(ConstantsUtils.NET_SALES_RULE)).getData().toString()) : 0;
            if(cdrSid!=0){
                rebatePlanMaster.setCdrModelSid(String.valueOf(cdrSid));
            }
            rebatePlanMaster.setRecordLockStatus(false);
            rebatePlanMaster.setModifiedDate(new Date());
            rebatePlanMaster.setModifiedBy(Integer.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
            rebatePlanMaster.setInboundStatus(ConstantsUtils.INBOUND_STATUS_C);
            rebatePlanMaster.setInternalNotes(addedNotes);
            
            final DynamicQuery rpDynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanMaster.class);

            rpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_PLAN_ID, rebatePlanMaster.getRebatePlanId()));
            rpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
            final List<RebatePlanMaster> rebateMaster = dao.getRebatePlanMasterList(rpDynamicQuery);
            int count = 0;
            for (int i = 0; i < rebateMaster.size(); i++) {
                if (rebatePlanSystemId == rebateMaster.get(i).getRebatePlanMasterSid()) {
                } else {

                    count++;
                }
            }

            final DynamicQuery rebateNoDynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanMaster.class);
            rebateNoDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_PLAN_NO, rebatePlanMaster.getRebatePlanNo()));
            rebateNoDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
            final List<RebatePlanMaster> rebateNoMaster = dao.getRebatePlanMasterList(rebateNoDynamicQuery);
            final DynamicQuery rebateNameDynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanMaster.class);
            rebateNameDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_PLAN_NAME, rebatePlanMaster.getRebatePlanName()));
            rebateNameDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
            final List<RebatePlanMaster> rebateNameMaster = dao.getRebatePlanMasterList(rebateNameDynamicQuery);
            int noCount = 0;
            for (int i = 0; i < rebateNoMaster.size(); i++) {
                if (rebatePlanSystemId == rebateNoMaster.get(i).getRebatePlanMasterSid()) {
                } else {

                    noCount++;
                }
            }
            int nameCount = 0;
            for (int i = 0; i < rebateNameMaster.size(); i++) {
                if (rebatePlanSystemId == rebateNameMaster.get(i).getRebatePlanMasterSid()) {
                } else {

                    nameCount++;
                }
            }
            final RebatePlanMaster result;
            if (count < GeneralCommonUtils.ONE && noCount < GeneralCommonUtils.ONE && nameCount < GeneralCommonUtils.ONE) {

                LOGGER.info("Entering getRebatePlanMasterBySystemId -" + rebatePlanMaster.getRebatePlanMasterSid());
                final RebatePlanMaster master = dao.getRebatePlanMasterBySystemId(rebatePlanMaster.getRebatePlanMasterSid());

                if (master.getRebatePlanType() != 0) {
                    UDCIncrementCheck.decrement(GeneralCommonUtils.getHelperDescription(master.getRebatePlanType()), GeneralCommonUtils.REBATE_PLAN_TYPE);
                }
                if (master.getRebatePlanStatus() != 0) {
                    UDCIncrementCheck.decrement(GeneralCommonUtils.getHelperDescription(master.getRebatePlanStatus()), GeneralCommonUtils.REBATE_PLAN_STATUS);
                }
                if (master.getRebateBasedOn() != 0) {
                    UDCIncrementCheck.decrement(master.getRebateBasedOn(), GeneralCommonUtils.REBATE_BASED_ON);
                }
                if (master.getRebateStructure() != null) {
                    UDCIncrementCheck.decrement(master.getRebateStructure(), GeneralCommonUtils.REBATE_STRUCTURE);
                }
                if (master.getRebateRangeBasedOn() != 0) {
                    UDCIncrementCheck.decrement(GeneralCommonUtils.getHelperDescription(master.getRebateRangeBasedOn()), GeneralCommonUtils.REBATE_RANGE_BASED_ON);
                }

                 result = dao.updateRebatePlanMaster(rebatePlanMaster);
                 sessionDTO.setSystemId(result.getRebatePlanMasterSid());

                if (result.getRebatePlanType() != 0) {
                    UDCIncrementCheck.increment(GeneralCommonUtils.getHelperDescription(result.getRebatePlanType()), GeneralCommonUtils.REBATE_PLAN_TYPE);
                }
                if (result.getRebatePlanStatus() != 0) {
                    UDCIncrementCheck.increment(GeneralCommonUtils.getHelperDescription(result.getRebatePlanStatus()), GeneralCommonUtils.REBATE_PLAN_STATUS);
                }
                if (result.getRebateBasedOn() != 0) {
                    UDCIncrementCheck.increment(result.getRebateBasedOn(), GeneralCommonUtils.REBATE_BASED_ON);
                }
                if (result.getRebateStructure() != null) {
                    UDCIncrementCheck.increment(result.getRebateStructure(), GeneralCommonUtils.REBATE_STRUCTURE);
                }
                if (result.getRebateRangeBasedOn() != 0) {
                    UDCIncrementCheck.increment(GeneralCommonUtils.getHelperDescription(result.getRebateRangeBasedOn()), GeneralCommonUtils.REBATE_RANGE_BASED_ON);
                }
                
            if (!removedDetailsList.isEmpty()) {
                for (int i = 0; i < removedDetailsList.size(); i++) {
                    NotesDTO dtoValue = removedDetailsList.get(i);
                    if (dtoValue.getDocDetailsId() != 0) {
                        rsLogic.deleteUploadedFile(dtoValue.getDocDetailsId(), StringUtils.EMPTY, dtoValue.getDocumentFullPath());
                    }
                }
            }
                rsLogic.saveUploadedInformation(availableUploadedInformation, "REBATE_PLAN_MASTER", result.getRebatePlanMasterSid());
                 
            } else if (noCount >= GeneralCommonUtils.ONE) {
                return ConstantsUtils.DUPLICATENO;
            } else if (nameCount >= GeneralCommonUtils.ONE) {
                return ConstantsUtils.DUPLICATENAME;
            } else {
                LOGGER.info(ConstantsUtils.DUPLICATE);
                return ConstantsUtils.DUPLICATE;
            }

            final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanTier.class);

            dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_PLAN_MASTER_SID, rebatePlanSystemId));

            List<RebatePlanTier> tiers = new ArrayList<RebatePlanTier>();
            LOGGER.info("Entering getRebatePlanTierList");
            tiers = dao.getRebatePlanTierList(dynamicQuery);
            // To delete the attached existing tiers
            for (int i = 0; i < tiers.size(); i++) {

                final RebatePlanTier tier = dao.deleteRebatePlanTier(tiers.get(i).getRebatePlanTierSid());

                if (tier.getTierOperator() != null) {
                    UDCIncrementCheck.decrement(tier.getTierOperator(), GeneralCommonUtils.TIER_OPERATOR);
                }
            }
            RebatePlanTier rebatePlanTier;
            for (int i = 0; i < rebatePlanTiers.size(); i++) {
                rebatePlanTier = RebatePlanTierLocalServiceUtil.createRebatePlanTier(0);
                final RebatePlanTierResults rebatePlanTierResult = (RebatePlanTierResults) rebatePlanTiers.getIdByIndex(i);
                BeanUtils.copyProperties(rebatePlanTier, rebatePlanTierResult);
                rebatePlanTier.setTierLevel(String.valueOf(i + 1));
                rebatePlanTier.setRebatePlanMasterSid(result.getRebatePlanMasterSid());
                rebatePlanTier.setRebatePlanTierId(String.valueOf(new Date().getTime()) + i);
                if(ConstantsUtils.DOLLAR.equals(rebatePlanTierResult.getTierOperatorValue())){                    
                   if(rebatePlanTierResult.getTierValueId().contains("$")){
                        String tiervalue=rebatePlanTierResult.getTierValueId().replace("$", "");
                        rebatePlanTier.setTierValue(Double.valueOf(tiervalue));
                        rebatePlanTier.setItemPricingQualifierSid(null);
                    }else{
                        rebatePlanTier.setItemPricingQualifierSid(rebatePlanTierResult.getTierValueId());
                        rebatePlanTier.setTierValue(0);
                    }
                }else if(ConstantsUtils.PERCENCTAGE.equals(rebatePlanTierResult.getTierOperatorValue())){      
                   if(rebatePlanTierResult.getTierValueId().contains("%")){
                        String tiervalue=rebatePlanTierResult.getTierValueId().replace("%", "");
                        rebatePlanTier.setTierValue(Double.valueOf(tiervalue));
                        rebatePlanTier.setReturnRateSid(null);
                    }else{
                        rebatePlanTier.setReturnRateSid(String.valueOf(GeneralCommonUtils.getHelperCode("TIER_PERCENT_VALUE",rebatePlanTierResult.getTierValueId())));
                        rebatePlanTier.setTierValue(0);
                }
                
                }
                
                final DynamicQuery rebatetierDynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanTier.class);
                rebatetierDynamicQuery.add(RestrictionsFactoryUtil.eq("rebatePlanTierId", rebatePlanTier.getRebatePlanTierId()));
                final RebatePlanTier tier = dao.saveRebatePlanTier(rebatePlanTier);
                if (tier.getTierOperator() != null) {
                    UDCIncrementCheck.increment(tier.getTierOperator(), GeneralCommonUtils.TIER_OPERATOR);
                }
            }
            
        }

        LOGGER.info("Return success");
        return ConstantsUtils.SUCCESS;

    }

    /**
     * Gets the int value.
     *
     * @param value the value
     * @return the int value
     */
    public int getIntValue(final String value) throws ParseException, Exception {
        LOGGER.info("getIntValue p1:Vlaue = " + value);
        int integerValue = 0;

        final NumberFormat format = NumberFormat.getInstance(Locale.ENGLISH);
        Number number;

        number = format.parse(value);
        integerValue = number.intValue();
        LOGGER.info("Return integerValue =" + integerValue);

        return integerValue;
    }

    /**
     * Gets the rebate plan by id.
     *
     * @param rebatePlanMasterId the rebate plan master id
     * @return the rebate plan by id
     */
    public RebatePlanMaster getRebatePlanById(final Integer rebatePlanMasterId) throws SystemException, PortalException, Exception {
        RebatePlanMaster master;

        LOGGER.info("getRebatePlanById method, p1 :rebatePlanMasterId " + rebatePlanMasterId);

        master = dao.fetchRebatePlanMaster(rebatePlanMasterId);

        return master;
    }

    /**
     * Gets the rebate plan tiersfrom id.
     *
     * @param rebatePlanMasterId
     * @param rebatePlanMasterDTO
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws SystemException
     * @throws Exception
     */
    @SuppressWarnings(ConstantsUtils.UNCHECK)
    public List<RebatePlanTierResults> getRebatePlanTiersfromId(
            final int rebatePlanMasterId, final RebatePlanMasterDTO rebatePlanMasterDTO) throws IllegalAccessException, InvocationTargetException, SystemException, Exception {
        List<RebatePlanTier> results;
        LOGGER.info("Inside getRebatePlanTiersfromId p1 : rebatePlanMasterId " + rebatePlanMasterId);
        final List<RebatePlanTierResults> rebatePlanTiers = new ArrayList<>();                
        Map<Integer, String> helperListMap = helperListUtil.getIdDescMap();
        final DynamicQuery rebatePlanTierDynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanTier.class);        
        rebatePlanTierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_PLAN_MASTER_SID, rebatePlanMasterId));
        rebatePlanTierDynamicQuery.addOrder(OrderFactoryUtil.asc("tierLevel"));
        LOGGER.info("getRebatePlanTierList p1:rebatePlanTierDynamicQuery ");
        results = dao.getRebatePlanTierList(rebatePlanTierDynamicQuery);
        LOGGER.info("-------------------results---------------------" + results.size());
        for (int i = 0; i < results.size(); i++) {
            RebatePlanTier rptier = results.get(i);
            final RebatePlanTierResults rebatePlanTier = new RebatePlanTierResults();            
            if (rptier.getTierTo() == null) {
                rptier.setTierTo(ConstantsUtils.ZERO);
            }            
            BeanUtils.copyProperties(rebatePlanTier, rptier);            
            if (ConstantsUtils.ZERO.equals(rptier.getTierTo())) {
                rebatePlanTier.setTierTo(null);
            }            
            if (ConstantsUtils.DOLLAR.equals(helperListMap.get(Integer.valueOf(String.valueOf(rptier.getTierOperator()))))) {
                if(rptier.getItemPricingQualifierSid()!=null && !"null".equals(rptier.getItemPricingQualifierSid()) && !"".equals(rptier.getItemPricingQualifierSid()) ){
                    rebatePlanTier.setTierValueId(String.valueOf(rptier.getItemPricingQualifierSid()));
                    int tierValueId = Double.valueOf(String.valueOf(rptier.getItemPricingQualifierSid())).intValue();
                    rebatePlanTier.setTierValue(rebatePlanMasterDTO.getItemPricingQualifierMap().get(tierValueId));
                }else{
                    rebatePlanTier.setTierValueId("$"+String.valueOf(rptier.getTierValue()));
                    rebatePlanTier.setTierValue(String.valueOf(rptier.getTierValue()));
                }
                }
            else if (ConstantsUtils.PERCENCTAGE.equals(helperListMap.get(Integer.valueOf(String.valueOf(rptier.getTierOperator()))))) {
                if(rptier.getReturnRateSid()!=null && !"null".equals(rptier.getReturnRateSid()) && !"".equals(rptier.getReturnRateSid()) ){
                    rebatePlanTier.setTierValueId(GeneralCommonUtils.getHelperDescription(Integer.parseInt(rptier.getReturnRateSid())));
                    rebatePlanTier.setTierValue(GeneralCommonUtils.getHelperDescription(Integer.parseInt(rptier.getReturnRateSid())));
                }else{
                    rebatePlanTier.setTierValueId("%"+String.valueOf(rptier.getTierValue()));
                    rebatePlanTier.setTierValue(String.valueOf(rptier.getTierValue()));
                }
                }
            if(rebatePlanTier.getTierTo()!=null){
                rebatePlanTier.setTierTo(new BigDecimal(formatter.format(rebatePlanTier.getTierTo())));
            }
            rebatePlanTier.setRebatePlanTierSystemId(rptier.getRebatePlanTierSid());
            rebatePlanTier.setTierOperatorValue(GeneralCommonUtils.getHelperDescription(Integer.parseInt(rptier.getTierOperator())));
            rebatePlanTiers.add(rebatePlanTier);
        }
        
        LOGGER.info("Ends  getRebatePlanTiersfromId with listr size" + rebatePlanTiers.size());
        return rebatePlanTiers;
    }

    /**
     * Delete rebate plan by id.
     *
     * @param systemId the system id
     * @return the rebate plan master
     */
    public RebatePlanMaster deleteRebatePlanById(final int systemId,final RebatePlanMasterDTO rebatePlanMasterDTO) throws SystemException, PortalException, Exception {
        

        LOGGER.info("Entering deleteRebatePlanMaster P1: " + systemId);
        RebatePlanMaster rebatePlanMaster = dao.getRebatePlanMasterBySystemId(systemId);
        rebatePlanMaster.setInboundStatus(ConstantsUtils.INBOUND_STATUS_D);
        dao.updateRebatePlanMaster(rebatePlanMaster);
        if (rebatePlanMaster.getRebatePlanType() != 0) {
            UDCIncrementCheck.decrement(GeneralCommonUtils.getHelperDescription(rebatePlanMaster.getRebatePlanType()), GeneralCommonUtils.REBATE_PLAN_TYPE);
        }
        if (rebatePlanMaster.getRebatePlanStatus() != 0) {
            UDCIncrementCheck.decrement(GeneralCommonUtils.getHelperDescription(rebatePlanMaster.getRebatePlanStatus()), GeneralCommonUtils.REBATE_PLAN_STATUS);
        }
        if (rebatePlanMaster.getRebateBasedOn() != 0) {
            UDCIncrementCheck.decrement(rebatePlanMaster.getRebateBasedOn(), GeneralCommonUtils.REBATE_BASED_ON);
        }
        if (rebatePlanMaster.getRebateStructure() != null) {
            UDCIncrementCheck.decrement(rebatePlanMaster.getRebateStructure(), GeneralCommonUtils.REBATE_STRUCTURE);
        }
        if (rebatePlanMaster.getRebateRangeBasedOn() != 0) {
            UDCIncrementCheck.decrement(GeneralCommonUtils.getHelperDescription(rebatePlanMaster.getRebateRangeBasedOn()), GeneralCommonUtils.REBATE_RANGE_BASED_ON);
        }

        List<RebatePlanTierResults> rebatePlanTiers = new ArrayList<RebatePlanTierResults>();
        rebatePlanTiers = getRebatePlanTiersfromId(systemId,rebatePlanMasterDTO);
        for (int i = 0; i < rebatePlanTiers.size(); i++) {
            final RebatePlanTier tier = dao.deleteRebatePlanTier(rebatePlanTiers.get(i)
                    .getRebatePlanTierSystemId());

            if (tier.getTierOperator() != null) {
                UDCIncrementCheck.increment(tier.getTierOperator(), GeneralCommonUtils.TIER_OPERATOR);
            }

        }
        LOGGER.info("Ending deleteRebatePlanMaster");

        LOGGER.info("Return RebatePlanMaster");
        return rebatePlanMaster;
    }

    /**
     * Gets the dynamic query count.
     *
     * @param dynamicQuerySearch the dynamic query search
     * @return the dynamic query count
     */
    public int getDynamicQueryCount(final DynamicQuery dynamicQuerySearch) throws SystemException {
        int resultValue;
        resultValue = GeneralCommonUtils.ZERO;

        LOGGER.info("Method getRebatePlanMasterQueryCount p1: DynamicQuery");
        resultValue = dao.getRebatePlanMasterQueryCount(dynamicQuerySearch);

        return resultValue;
    }

    /**
     * Gets the actuals total count.
     *
     * @return the actuals total count
     */
    public int getActualsTotalCount() throws SystemException {
        int result = GeneralCommonUtils.ZERO;

        LOGGER.info("Method getRebatePlanMasterTotalCount ");
        result = dao.getRebatePlanMasterTotalCount();

        return result;
    }

    /**
     * Load all actualss.
     *
     * @param startIndex the start index
     * @param endIndex the end index
     * @return the list< rebate plan master dt o>
     */
    public List<SearchResultsDTO> loadAllActualss(final int startIndex,
            final int endIndex) throws SystemException, Exception {
        List<SearchResultsDTO> result = null;

        LOGGER.info("Method getRebatePlanMasterByLimit p1:startIndex ,p2:End index");
        final List<RebatePlanMaster> list = dao.getRebatePlanMasterByLimit(startIndex, endIndex);
        result = getCustomizedSearchFormFromModel(list);

        return result;
    }

    /**
     * Gets the tier formula no.
     *
     * @return the tier formula no
     */
    @SuppressWarnings(ConstantsUtils.UNCHECK)
    public static List<String> getTierFormulaNo() throws SystemException, PortalException, Exception {
        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(RebateTierFormula.class);
        companyDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(ConstantsUtils.REBATE_TIER_FORMULA_NO)));
        List<String> resultList = new ArrayList<String>();
        final RebatePlanDAO staticDao = new RebatePlanSearchLogicDAOImpl();

        LOGGER.info("Method getRebatePlanMasterTotalCount ");
        resultList = staticDao.getTierFormula(companyDynamicQuery);

        return resultList;
    }

    /**
     * Gets the tier formula name.
     *
     * @return the tier formula name
     */
    @SuppressWarnings(ConstantsUtils.UNCHECK)
    public static List<String> getTierFormulaName() throws SystemException, PortalException, Exception {
        LOGGER.info("getTierFormulaName method ");
        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(RebateTierFormula.class);
        companyDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(ConstantsUtils.REBATE_TIER_FORMULA_NAME)));
        List<String> resultList = new ArrayList<String>();
        final RebatePlanDAO staticDao = new RebatePlanSearchLogicDAOImpl();

        resultList = staticDao.getTierFormula(companyDynamicQuery);

        return resultList;
    }

    /**
     * Gets the selected tier formula id.
     *
     * @return the selected tier formula id
     */
    @SuppressWarnings(ConstantsUtils.UNCHECK)
    public static List<RebatePlanMasterDTO> getSelectedTierFormulaId() throws SystemException, Exception {
        List<RebateTierFormula> list = null;
        RebatePlanMasterDTO tierFormulaDTO;
        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(RebateTierFormula.class);
        final RebatePlanDAO staticDao = new RebatePlanSearchLogicDAOImpl();
        final List<RebatePlanMasterDTO> resultList = new ArrayList<RebatePlanMasterDTO>();

        LOGGER.info("Enter getSelectedTierFormulaId method ");
        list = staticDao.getTierFormulaList(companyDynamicQuery);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                tierFormulaDTO = new RebatePlanMasterDTO(String.valueOf(list.get(i).getRebateTierFormulaSid()), String.valueOf(list.get(i).getRebateTierFormulaId()), list.get(i).getRebateTierFormulaNo(), list.get(i).getRebateTierFormulaName());
                resultList.add(tierFormulaDTO);
            }
        }
        LOGGER.info("End of  resultList getSelectedTierFormulaId size  " + resultList.size());

        return resultList;
    }

    /**
     * Gets the formula by id.
     *
     * @param idValue the id value
     * @return the formula by id
     */
    public RebatePlanMasterDTO getFormulaById(final int idValue) throws SystemException, PortalException, Exception {
        
        final RebatePlanMasterDTO rebatePlanMasterDTO = new RebatePlanMasterDTO();
        final RebatePlanDAO staticDao = new RebatePlanSearchLogicDAOImpl();

        LOGGER.info("getFormulaById methjod with id =" + idValue);
        RebateTierFormula rebateTierFormula = staticDao.getRebateTierFormula(idValue);

        rebatePlanMasterDTO.setTierFormulaId(String.valueOf(rebateTierFormula.getRebateTierFormulaSid()));
        rebatePlanMasterDTO.setTierFormulaNo(rebateTierFormula.getRebateTierFormulaNo());
        rebatePlanMasterDTO.setTierFormulaName(rebateTierFormula.getRebateTierFormulaName());
        LOGGER.info("rebatePlanMasterDTO dto  with TierFormulaId  ---- " + rebatePlanMasterDTO.getTierFormulaId());

        return rebatePlanMasterDTO;
    }

    /**
     * Gets the record count.
     *
     * @param rebatePlanLogic the rebate plan logic
     * @param binder the binder
     * @return the record count
     */
    public static long getRecordCount(final RebatePlanSearchLogic rebatePlanLogic, final ErrorfulFieldGroup binder) throws Exception {
        LOGGER.info("Entering getRecordCount");
        long result = GeneralCommonUtils.ZERO;

        final DynamicQuery query = rebatePlanLogic.getDynamicQuerySearch(binder,null);
        result = rebatePlanLogic.getDynamicQueryCount(query);
        
        LOGGER.info("return count -" + result);
        return result;
        }

    /**
     * Gets the rebate plan tier list.
     *
     * @param query the query
     * @return the rebate plan tier list
     * @throws SystemException the system exception
     */
    public List<RebatePlanTier> getRebatePlanTierList(final DynamicQuery query) throws SystemException {
        LOGGER.info("Entering getRebatePlanTierList");
        return dao.getRebatePlanTierList(query);
    }
    /**
     * Gets the lazy tier formula id count.
     * 
     * @param filter
     * @return
     * @throws PortalException
     * @throws SystemException 
     */
    public int getItemPriceTypesCount(String filter) throws PortalException, SystemException {
        filter = StringUtils.trimToEmpty(filter) + ConstantsUtils.PERCENCTAGE;
        LOGGER.info("Entering getLazyTierFormulaIdCount method with filterText :" + filter);
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class);
        dynamicQuery.add(RestrictionsFactoryUtil.like("itemPricingQualifierName", filter));        
        Long count = FormulaDetailsMasterLocalServiceUtil.dynamicQueryCount(dynamicQuery);
        LOGGER.info("Ending getLazyTierFormulaIdCount method with filterText with count :" + count);
        return count.intValue();
    }
    
    
    /**
     * Gets the lazy tier formula id count.
     *
     * @param filter
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public List<com.stpl.ifs.util.HelperDTO> getItemPriceTypesResults(final int start, final int end, String filter) throws PortalException, SystemException {
        filter = StringUtils.trimToEmpty(filter) + ConstantsUtils.PERCENCTAGE;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class);
        dynamicQuery.add(RestrictionsFactoryUtil.like("itemPricingQualifierName", filter));
        dynamicQuery.setLimit(start, end);
        List<ItemPricingQualifier> list = ItemPricingQualifierLocalServiceUtil.dynamicQuery(dynamicQuery);
        List<com.stpl.ifs.util.HelperDTO> resultList = new ArrayList();
        final com.stpl.ifs.util.HelperDTO defaultValue = new com.stpl.ifs.util.HelperDTO(0, ConstantsUtils.SELECT_ONE);
        resultList.add(defaultValue);
        for (ItemPricingQualifier itemPricingQualifier : list) {
            com.stpl.ifs.util.HelperDTO helperDTO = new com.stpl.ifs.util.HelperDTO();
            helperDTO.setId(itemPricingQualifier.getItemPricingQualifierSid());
            helperDTO.setDescription(StringUtils.isNotBlank(itemPricingQualifier.getItemPricingQualifierName()) ? itemPricingQualifier.getItemPricingQualifierName() : StringUtils.EMPTY);
            if (helperDTO.getDescription() != null && StringUtils.isNotBlank(helperDTO.getDescription())) {
                resultList.add(helperDTO);
            }
        }
        return resultList;
    }

    @Override
    public String saveRebatePlan(ErrorfulFieldGroup rebatePlanForm, BeanItemContainer<RebatePlanTierResults> rebatePlanTiers) throws SystemException, IllegalAccessException, InvocationTargetException, PortalException, ParseException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
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
        
        list = HelperTableLocalServiceUtil.findByHelperTableDetails(listName);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));
            }
        }
        Collections.sort(helperList);
        return helperList;
    }

    /**
     * 
     * @param searchRebatePlanForm
     * @param start
     * @param end
     * @param columns
     * @param filterSet
     * @return
     * @throws SystemException
     * @throws Exception 
     */
    @SuppressWarnings(ConstantsUtils.UNCHECK)
    public List<SearchResultsDTO> getResultsForSearch(
            final ErrorfulFieldGroup searchRebatePlanForm, final int start, final int end, final List<SortByColumn> columns,final Set<Container.Filter> filterSet) throws SystemException, Exception {
        List<SearchResultsDTO> searchList = new ArrayList<SearchResultsDTO>();
        LOGGER.info("Entering searchRebatePlan with start of=" + start + "and endIndex of= " + end + "  Column Size +" + ((columns == null) ? columns : columns.size()));

        String rebatePlanName;
        String rebatePlanId = StringUtils.EMPTY;
        String rebatePlanNo = StringUtils.EMPTY;
        rebatePlanName = StringUtils.EMPTY;
        String rebatePlanStatus = StringUtils.EMPTY;
        String rebatePlanType = StringUtils.EMPTY;

        if (searchRebatePlanForm.getField(ConstantsUtils.TEXT1).getValue().toString() != null) {
            rebatePlanId = searchRebatePlanForm.getField(ConstantsUtils.TEXT1)
                    .getValue().toString().trim();
        }
        if (searchRebatePlanForm.getField(ConstantsUtils.TEXT2).getValue().toString() != null) {

            rebatePlanNo = searchRebatePlanForm.getField(ConstantsUtils.TEXT2)
                    .getValue().toString().trim();
        }
        if (searchRebatePlanForm.getField(ConstantsUtils.TEXT3).getValue()
                .toString() != null) {
            rebatePlanName = searchRebatePlanForm.getField(ConstantsUtils.TEXT3)
                    .getValue().toString().trim();
        }
        if (searchRebatePlanForm.getField(ConstantsUtils.COMBO1).getValue() != null) {

            rebatePlanStatus = String.valueOf(((HelperDTO)searchRebatePlanForm
                    .getField(ConstantsUtils.COMBO1).getValue()).getSystemId());
        }
        if (searchRebatePlanForm.getField(ConstantsUtils.COMBO2).getValue() != null) {
            rebatePlanType = String.valueOf(((HelperDTO)searchRebatePlanForm.getField(ConstantsUtils.COMBO2)
                    .getValue()).getSystemId());
        }
        final DynamicQuery rebatePlanDynamicQuery = DynamicQueryFactoryUtil
                .forClass(RebatePlanMaster.class);

        // Added to check INBOUND_STATUS is A. ETL Soft delete data should not be included, where INBOUND_STATUS is D
        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));

        if (StringUtils.isNotBlank(rebatePlanId)) {
            rebatePlanId = rebatePlanId.replace(GeneralCommonUtils.CHAR_ASTERISK,
                    GeneralCommonUtils.CHAR_PERCENT);
            rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(
                    ConstantsUtils.REBATE_PLAN_ID, rebatePlanId));
        }
        if (StringUtils.isNotBlank(rebatePlanNo)) {
            rebatePlanNo = rebatePlanNo.replace(GeneralCommonUtils.CHAR_ASTERISK,
                    GeneralCommonUtils.CHAR_PERCENT);
            rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(
                    ConstantsUtils.REBATE_PLAN_NO, rebatePlanNo));
        }
        if (StringUtils.isNotBlank(rebatePlanName)) {
            rebatePlanName = rebatePlanName.replace(GeneralCommonUtils.CHAR_ASTERISK,
                    GeneralCommonUtils.CHAR_PERCENT);
            rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(
                    ConstantsUtils.REBATE_PLAN_NAME, rebatePlanName));
        }
        if (StringUtils.isNotBlank(rebatePlanStatus)) {
            
            rebateStatus = getDropDownList(GeneralCommonUtils.STATUS);
            for (int i = 0; i < rebateStatus.size(); i++) {
                if (String.valueOf(rebateStatus.get(i).getId()).equals(rebatePlanStatus)) {
                    int ik = rebateStatus.get(i).getSystemId();
                    rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.REBATE_PLAN_STATUS, ik));
                }
            }
        }
        if (StringUtils.isNotBlank(rebatePlanType)) {
           
            rebateType = getDropDownList(GeneralCommonUtils.REBATE_PLAN_TYPE);
            for (int i = 0; i < rebateType.size(); i++) {
                if (String.valueOf(rebateType.get(i).getId()).equals(rebatePlanType)) {
                    int ik = rebateType.get(i).getSystemId();
                    rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.REBATE_PLAN_TYPE, ik));
                }
            }
        }
        for (final Iterator<SortByColumn> iterator = columns.iterator(); iterator.hasNext();) {
            final SortByColumn sortByColumn = (SortByColumn) iterator.next();
            if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                rebatePlanDynamicQuery.addOrder(OrderFactoryUtil.asc(sortByColumn.getName()));
            } else {
                rebatePlanDynamicQuery.addOrder(OrderFactoryUtil.desc(sortByColumn.getName()));
            }
        }

        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {

                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;                    
                    if (ConstantsUtils.REBATE_PLAN_ID.equals(stringFilter.getPropertyId())) {
                        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.REBATE_PLAN_ID, filterString));
                    }else         
                    if (ConstantsUtils.REBATE_PLAN_NO.equals(stringFilter.getPropertyId())) {
                        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.REBATE_PLAN_NO, filterString));
                    }else
                    if (ConstantsUtils.REBATE_PLAN_NAME.equals(stringFilter.getPropertyId())) {
                        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.REBATE_PLAN_NAME, filterString));
                    }else
                    if (ConstantsUtils.SECONDARY_REBATE_PLAN_ID.equals(stringFilter.getPropertyId())) {
                        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.SECONDARY_REBATE_PLAN_ID, filterString));
                    }else         
                    if (ConstantsUtils.SECONDARY_REBATE_PLAN_NO.equals(stringFilter.getPropertyId())) {
                        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.SECONDARY_REBATE_PLAN_NO, filterString));
                    }else
                    if (ConstantsUtils.SECONDARY_REBATE_PLAN_NAME.equals(stringFilter.getPropertyId())) {
                        rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.SECONDARY_REBATE_PLAN_NAME, filterString));
                    }

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;

                    if (stringFilter.getValue() instanceof Integer) {
                        Integer filterValue = (Integer) stringFilter.getValue();
                        if (ConstantsUtils.REBATE_PLAN_STATUS.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_PLAN_STATUS, filterValue));
                            }
                        }else
                        if (ConstantsUtils.REBATE_PLAN_TYPE.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_PLAN_TYPE, filterValue));
                            }
                        }else
                        if (ConstantsUtils.REBATE_BASED_ON.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_BASED_ON, filterValue));
                            }
                        }else
                        if (ConstantsUtils.REBATE_STRUCTURE.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_STRUCTURE, String.valueOf(filterValue)));
                            }
                        }else
                        if (ConstantsUtils.REBATE_RANGE_BASED_ON.equals(stringFilter.getPropertyId())) {
                            if (filterValue != 0) {
                                rebatePlanDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_RANGE_BASED_ON, filterValue));
                            }
                        }  
                    } 
                } 
            }
        }
        
        rebatePlanDynamicQuery.setLimit(start, end);
        final List<RebatePlanMaster> list = dao.getRebatePlanMasterList(rebatePlanDynamicQuery);
        LOGGER.info("After Query Hit,list size for Search results is = " + ((list == null) ? list : list.size()));
        searchList = getCustomizedSearchFormFromModel(list);
        LOGGER.info("Ends searchRebatePlan wiht search size ----" + ((searchList == null) ? searchList : searchList.size()));

        return searchList;

    }
    
    public int getRebatePlanResultsCount(
            final ErrorfulFieldGroup searchRebatePlanForm, final int start, final int end, final List<SortByColumn> columns,final Set<Container.Filter> filterSet) throws SystemException, Exception{
        LOGGER.info("Entering searchRebatePlan with count" );

        String rebatePlanName;
        String rebatePlanId = StringUtils.EMPTY;
        String rebatePlanNo = StringUtils.EMPTY;
        rebatePlanName = StringUtils.EMPTY;
        String rebatePlanStatus = StringUtils.EMPTY;
        String rebatePlanType = StringUtils.EMPTY;

        String sql = "select count(*) from (";
        sql+= CustomSQLUtil.get("com.global.rebateplan.RebatePlanSearch"); 
        
        
        if (searchRebatePlanForm.getField(ConstantsUtils.TEXT1).getValue() != null && StringUtils.isNotBlank(String.valueOf(searchRebatePlanForm.getField(ConstantsUtils.TEXT1).getValue()))) {
            rebatePlanId = searchRebatePlanForm.getField(ConstantsUtils.TEXT1)
                    .getValue().toString().trim();
            rebatePlanId = rebatePlanId.replace(GeneralCommonUtils.CHAR_ASTERISK,
                    GeneralCommonUtils.CHAR_PERCENT);
            sql += "AND RPM.REBATE_PLAN_ID LIKE '" + rebatePlanId + "' ";
        }
        if (searchRebatePlanForm.getField(ConstantsUtils.TEXT2).getValue() != null && StringUtils.isNotBlank(String.valueOf(searchRebatePlanForm.getField(ConstantsUtils.TEXT2).getValue()))) {
            rebatePlanNo = searchRebatePlanForm.getField(ConstantsUtils.TEXT2)
                    .getValue().toString().trim();
            rebatePlanNo = rebatePlanNo.replace(GeneralCommonUtils.CHAR_ASTERISK,
                    GeneralCommonUtils.CHAR_PERCENT);
            sql += "AND RPM.REBATE_PLAN_NO LIKE '" + rebatePlanNo + "' ";
        }
        if (searchRebatePlanForm.getField(ConstantsUtils.TEXT3).getValue() != null && StringUtils.isNotBlank(String.valueOf(searchRebatePlanForm.getField(ConstantsUtils.TEXT3).getValue()))) {
            rebatePlanName = searchRebatePlanForm.getField(ConstantsUtils.TEXT3)
                    .getValue().toString().trim();
            rebatePlanName = rebatePlanName.replace(GeneralCommonUtils.CHAR_ASTERISK,
                    GeneralCommonUtils.CHAR_PERCENT);
            sql += "AND RPM.REBATE_PLAN_NAME LIKE '" + rebatePlanName + "' ";
        }
        if (searchRebatePlanForm.getField(ConstantsUtils.COMBO1).getValue() != null && ((HelperDTO)searchRebatePlanForm.getField(ConstantsUtils.COMBO1).getValue()).getId() != 0) {

            rebatePlanStatus = String.valueOf(((HelperDTO)searchRebatePlanForm
                    .getField(ConstantsUtils.COMBO1).getValue()).getSystemId());
            sql += "AND HT_STATUS.HELPER_TABLE_SID = '"+rebatePlanStatus+"' " ;
        }
        if (searchRebatePlanForm.getField(ConstantsUtils.COMBO2).getValue() != null && ((HelperDTO)searchRebatePlanForm.getField(ConstantsUtils.COMBO2).getValue()).getId() != 0) {
            rebatePlanType = String.valueOf(((HelperDTO)searchRebatePlanForm.getField(ConstantsUtils.COMBO2)
                    .getValue()).getSystemId());
            sql += "AND HT_TYPE.HELPER_TABLE_SID = '"+rebatePlanType+"' " ;
        }
        
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {

                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;                    
                    if (ConstantsUtils.REBATE_PLAN_ID.equals(stringFilter.getPropertyId())) {
                        sql += "AND RPM.REBATE_PLAN_ID LIKE '"+filterString+"' ";                        
                    }else         
                    if (ConstantsUtils.REBATE_PLAN_NO.equals(stringFilter.getPropertyId())) {
                        sql += "AND RPM.REBATE_PLAN_NO LIKE '"+filterString+"' ";                        
                    }else
                    if (ConstantsUtils.REBATE_PLAN_NAME.equals(stringFilter.getPropertyId())) {
                        sql += "AND RPM.REBATE_PLAN_NAME LIKE '"+filterString+"' ";                        
                    }else
                    if (ConstantsUtils.SECONDARY_REBATE_PLAN_ID.equals(stringFilter.getPropertyId())) {
                        sql += "AND RPM.SECONDARY_REBATE_PLAN_ID LIKE '"+filterString+"' ";                        
                    }else         
                    if (ConstantsUtils.SECONDARY_REBATE_PLAN_NO.equals(stringFilter.getPropertyId())) {
                        sql += "AND RPM.SECONDARY_REBATE_PLAN_NO LIKE '"+filterString+"' ";                        
                    }else
                    if (ConstantsUtils.SECONDARY_REBATE_PLAN_NAME.equals(stringFilter.getPropertyId())) {
                        sql += "AND RPM.SECONDARY_REBATE_PLAN_NAME LIKE '"+filterString+"' ";                       
                    }else
                    if (ConstantsUtils.REBATE_NSF_NAME.equals(stringFilter.getPropertyId())) {
                        sql += "AND NSF.NET_SALES_FORMULA_NAME LIKE '"+filterString+"' ";                       
                    }else
                    if (ConstantsUtils.REBATE_RULE_NAME.equals(stringFilter.getPropertyId())) {
                        sql += "AND CM.RULE_NAME LIKE '"+filterString+"' ";                       
                    }
                    if (ConstantsUtils.REBATE_PLAN_STATUS.equals(stringFilter.getPropertyId())) {
                            if (!ConstantsUtils.ZERO.equals(stringFilter.getFilterString())) {
                                sql += "AND HT_STATUS.HELPER_TABLE_SID = '"+stringFilter.getFilterString()+"' ";
                            }
                        }else
                        if (ConstantsUtils.REBATE_PLAN_TYPE.equals(stringFilter.getPropertyId())) {
                            if (!ConstantsUtils.ZERO.equals(stringFilter.getFilterString())) {
                                sql += "AND HT_TYPE.HELPER_TABLE_SID = '"+stringFilter.getFilterString()+"' ";
                            }
                        }else
                        if (ConstantsUtils.REBATE_BASED_ON.equals(stringFilter.getPropertyId())) {
                            if (!ConstantsUtils.ZERO.equals(stringFilter.getFilterString())) {
                                sql += "AND HT_RBO.HELPER_TABLE_SID = '"+stringFilter.getFilterString()+"' ";
                            }
                        }else
                        if (ConstantsUtils.REBATE_STRUCTURE.equals(stringFilter.getPropertyId())) {
                            if (!ConstantsUtils.ZERO.equals(stringFilter.getFilterString())) {
                                sql += "AND HT_RBS.HELPER_TABLE_SID = '"+stringFilter.getFilterString()+"' ";
                            }
                        }else
                        if (ConstantsUtils.REBATE_RANGE_BASED_ON.equals(stringFilter.getPropertyId())) {
                            if (!ConstantsUtils.ZERO.equals(stringFilter.getFilterString())) {
                                sql += "AND HT_RRBO.HELPER_TABLE_SID = '"+stringFilter.getFilterString()+"' ";
                            }
                        }else
                        if(ConstantsUtils.FORMULA_TYPE.equals(stringFilter.getPropertyId())){
                            if (!ConstantsUtils.ZERO.equals(stringFilter.getFilterString())) {
                                sql += "AND HT_FORMULA_TYPE.HELPER_TABLE_SID = '"+stringFilter.getFilterString()+"' ";
                            }
                        }
                    else
                        if(ConstantsUtils.CREATEDBY.equals(stringFilter.getPropertyId())){
                            if (!ConstantsUtils.ZERO.equals(stringFilter.getFilterString())) {
                                sql += "AND RPM.CREATED_BY = '"+stringFilter.getFilterString()+"' ";
                            }
                        }
                    else
                        if(ConstantsUtils.MODIFIEDBY.equals(stringFilter.getPropertyId())){
                            if (!ConstantsUtils.ZERO.equals(stringFilter.getFilterString())) {
                                sql += "AND RPM.MODIFIED_BY = '"+stringFilter.getFilterString()+"' ";
                            }
                        }
                } 
            }
        }
        
        
        
           sql +=") a"; 
        
          
        final List size = (List) RsModelLocalServiceUtil.executeSelectQuery(sql, null, null);
        int count = 0;
            if(size!=null && ! size.isEmpty()){
                count= (int)size.get(0);
            }else{
                count=0;
            }
        LOGGER.info("Ends searchRebatePlan wiht search size ----" + count);

        return count;
        
    }
    
    public List<SearchResultsDTO> getRebatePlanResults(
            final ErrorfulFieldGroup searchRebatePlanForm, final int start, final int end, final List<SortByColumn> columns,final Set<Container.Filter> filterSet) throws SystemException, Exception{
        
        List<SearchResultsDTO> searchList = new ArrayList<SearchResultsDTO>();
        LOGGER.info("Entering searchRebatePlan with start of=" + start + "and endIndex of= " + end + "  Column Size +" + ((columns == null) ? columns : columns.size()));

        String rebatePlanName;
        String rebatePlanId = StringUtils.EMPTY;
        String rebatePlanNo = StringUtils.EMPTY;
        rebatePlanName = StringUtils.EMPTY;
        String rebatePlanStatus = StringUtils.EMPTY;
        String rebatePlanType = StringUtils.EMPTY;

        String sql;
        
       
        sql="SELECT \n" +
"            RPM.REBATE_PLAN_ID,\n" +
"            RPM.REBATE_PLAN_NO,\n" +
"            REBATE_PLAN_NAME,\n" +
"            HT_STATUS.DESCRIPTION AS REBATE_PLAN_STATUS,\n" +
"            HT_TYPE.DESCRIPTION AS REBATE_PLAN_TYPE,\n" +
"            HT_FORMULA_TYPE.DESCRIPTION AS FORMULA_TYPE,\n" +
"            HT_RBO.DESCRIPTION AS REBATE_BASED_ON,\n" +
"            HT_RBS.DESCRIPTION AS REBATE_STRUCTURE,\n" +
"            HT_RRBO.DESCRIPTION AS REBATE_RANGE_BASED_ON,\n" +
"            RPM.SECONDARY_REBATE_PLAN_ID,\n" +
"            RPM.SECONDARY_REBATE_PLAN_NO,\n" +
"            RPM.SECONDARY_REBATE_PLAN_NAME,\n" +
"            RPM.BATCH_ID,\n" +
"            RPM.RECORD_LOCK_STATUS,\n" +
"            RPM.REBATE_PLAN_MASTER_SID,RPM.MODIFIED_BY,RPM.CREATED_BY,\n" +
"            NSF.NET_SALES_FORMULA_NAME,\n" +
"            CM.RULE_NAME,RPM.CREATED_DATE,RPM.MODIFIED_DATE\n" +
"        FROM \n" +
"            dbo.REBATE_PLAN_MASTER RPM\n" +
"        LEFT JOIN \n" +
"            NET_SALES_FORMULA_MASTER NSF on NSF.NET_SALES_FORMULA_MASTER_SID=RPM.NET_SALES_FORMULA_MASTER_SID\n" +
"        LEFT JOIN \n" +
"            CDR_MODEL CM on CM.CDR_MODEL_SID=RPM.CDR_MODEL_SID\n" +
"        LEFT JOIN\n" +
"            HELPER_TABLE HT_STATUS ON HT_STATUS.HELPER_TABLE_SID = RPM.REBATE_PLAN_STATUS\n" +
"        LEFT JOIN\n" +
"            HELPER_TABLE HT_TYPE ON HT_TYPE.HELPER_TABLE_SID = RPM.REBATE_PLAN_TYPE\n" +
"        LEFT JOIN\n" +
"            HELPER_TABLE HT_FORMULA_TYPE ON HT_FORMULA_TYPE.HELPER_TABLE_SID = RPM.FORMULA_TYPE\n" +
"        LEFT JOIN\n" +
"            HELPER_TABLE HT_RBO ON HT_RBO.HELPER_TABLE_SID = RPM.REBATE_BASED_ON\n" +
"        LEFT JOIN\n" +
"            HELPER_TABLE HT_RBS ON HT_RBS.HELPER_TABLE_SID = RPM.REBATE_STRUCTURE\n" +
"        LEFT JOIN\n" +
"            HELPER_TABLE HT_RRBO ON HT_RRBO.HELPER_TABLE_SID = RPM.REBATE_RANGE_BASED_ON\n" +
"        WHERE\n" +
"            RPM.INBOUND_STATUS <> 'D'";
        
        if (searchRebatePlanForm.getField(ConstantsUtils.TEXT1).getValue() != null && StringUtils.isNotBlank(String.valueOf(searchRebatePlanForm.getField(ConstantsUtils.TEXT1).getValue()))) {
            rebatePlanId = searchRebatePlanForm.getField(ConstantsUtils.TEXT1)
                    .getValue().toString().trim();
            rebatePlanId = rebatePlanId.replace(GeneralCommonUtils.CHAR_ASTERISK,
                    GeneralCommonUtils.CHAR_PERCENT);
            sql += "AND RPM.REBATE_PLAN_ID LIKE '" + rebatePlanId + "' ";
        }
        if (searchRebatePlanForm.getField(ConstantsUtils.TEXT2).getValue() != null && StringUtils.isNotBlank(String.valueOf(searchRebatePlanForm.getField(ConstantsUtils.TEXT2).getValue()))) {
            rebatePlanNo = searchRebatePlanForm.getField(ConstantsUtils.TEXT2)
                    .getValue().toString().trim();
            rebatePlanNo = rebatePlanNo.replace(GeneralCommonUtils.CHAR_ASTERISK,
                    GeneralCommonUtils.CHAR_PERCENT);
            sql += "AND RPM.REBATE_PLAN_NO LIKE '" + rebatePlanNo + "' ";
        }
        if (searchRebatePlanForm.getField(ConstantsUtils.TEXT3).getValue() != null && StringUtils.isNotBlank(String.valueOf(searchRebatePlanForm.getField(ConstantsUtils.TEXT3).getValue()))) {
            rebatePlanName = searchRebatePlanForm.getField(ConstantsUtils.TEXT3)
                    .getValue().toString().trim();
            rebatePlanName = rebatePlanName.replace(GeneralCommonUtils.CHAR_ASTERISK,
                    GeneralCommonUtils.CHAR_PERCENT);
            sql += "AND RPM.REBATE_PLAN_NAME LIKE '" + rebatePlanName + "' ";
        }
        if (searchRebatePlanForm.getField(ConstantsUtils.COMBO1).getValue() != null && ((HelperDTO)searchRebatePlanForm.getField(ConstantsUtils.COMBO1).getValue()).getId() != 0) {

            rebatePlanStatus = String.valueOf(((HelperDTO)searchRebatePlanForm
                    .getField(ConstantsUtils.COMBO1).getValue()).getSystemId());
            sql += "AND HT_STATUS.HELPER_TABLE_SID = '"+rebatePlanStatus+"' " ;
        }
        if (searchRebatePlanForm.getField(ConstantsUtils.COMBO2).getValue() != null && ((HelperDTO)searchRebatePlanForm.getField(ConstantsUtils.COMBO2).getValue()).getId() != 0) {
            rebatePlanType = String.valueOf(((HelperDTO)searchRebatePlanForm.getField(ConstantsUtils.COMBO2)
                    .getValue()).getSystemId());
            sql += "AND HT_TYPE.HELPER_TABLE_SID = '"+rebatePlanType+"' " ;
        }
        
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {

                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;                    
                    if (ConstantsUtils.REBATE_PLAN_ID.equals(stringFilter.getPropertyId())) {
                        sql += "AND RPM.REBATE_PLAN_ID LIKE '"+filterString+"' ";                        
                    }else         
                    if (ConstantsUtils.REBATE_PLAN_NO.equals(stringFilter.getPropertyId())) {
                        sql += "AND RPM.REBATE_PLAN_NO LIKE '"+filterString+"' ";                        
                    }else
                    if (ConstantsUtils.REBATE_PLAN_NAME.equals(stringFilter.getPropertyId())) {
                        sql += "AND RPM.REBATE_PLAN_NAME LIKE '"+filterString+"' ";                        
                    }else
                    if (ConstantsUtils.SECONDARY_REBATE_PLAN_ID.equals(stringFilter.getPropertyId())) {
                        sql += "AND RPM.SECONDARY_REBATE_PLAN_ID LIKE '"+filterString+"' ";                        
                    }else         
                    if (ConstantsUtils.SECONDARY_REBATE_PLAN_NO.equals(stringFilter.getPropertyId())) {
                        sql += "AND RPM.SECONDARY_REBATE_PLAN_NO LIKE '"+filterString+"' ";                        
                    }else
                    if (ConstantsUtils.SECONDARY_REBATE_PLAN_NAME.equals(stringFilter.getPropertyId())) {
                        sql += "AND RPM.SECONDARY_REBATE_PLAN_NAME LIKE '"+filterString+"' ";                       
                    }else
                    if (ConstantsUtils.REBATE_NSF_NAME.equals(stringFilter.getPropertyId())) {
                        sql += "AND NSF.NET_SALES_FORMULA_NAME LIKE '"+filterString+"' ";                       
                    }else
                    if (ConstantsUtils.REBATE_RULE_NAME.equals(stringFilter.getPropertyId())) {
                        sql += "AND CM.RULE_NAME LIKE '"+filterString+"' ";                       
                    }
                    if (ConstantsUtils.REBATE_PLAN_STATUS.equals(stringFilter.getPropertyId())) {
                            if (!ConstantsUtils.ZERO.equals(stringFilter.getFilterString())) {
                                sql += "AND HT_STATUS.HELPER_TABLE_SID = '"+stringFilter.getFilterString()+"' ";
                            }
                        }else
                        if (ConstantsUtils.REBATE_PLAN_TYPE.equals(stringFilter.getPropertyId())) {
                            if (!ConstantsUtils.ZERO.equals(stringFilter.getFilterString())) {
                                sql += "AND HT_TYPE.HELPER_TABLE_SID = '"+stringFilter.getFilterString()+"' ";
                            }
                        }else
                        if (ConstantsUtils.REBATE_BASED_ON.equals(stringFilter.getPropertyId())) {
                            if (!ConstantsUtils.ZERO.equals(stringFilter.getFilterString())) {
                                sql += "AND HT_RBO.HELPER_TABLE_SID = '"+stringFilter.getFilterString()+"' ";
                            }
                        }else
                        if (ConstantsUtils.REBATE_STRUCTURE.equals(stringFilter.getPropertyId())) {
                            if (!ConstantsUtils.ZERO.equals(stringFilter.getFilterString())) {
                                sql += "AND HT_RBS.HELPER_TABLE_SID = '"+stringFilter.getFilterString()+"' ";
                            }
                        }else
                        if (ConstantsUtils.REBATE_RANGE_BASED_ON.equals(stringFilter.getPropertyId())) {
                            if (!ConstantsUtils.ZERO.equals(stringFilter.getFilterString())) {
                                sql += "AND HT_RRBO.HELPER_TABLE_SID = '"+stringFilter.getFilterString()+"' ";
                            }
                        }else
                        if(ConstantsUtils.FORMULA_TYPE.equals(stringFilter.getPropertyId())){
                            if (!ConstantsUtils.ZERO.equals(stringFilter.getFilterString())) {
                                sql += "AND HT_FORMULA_TYPE.HELPER_TABLE_SID = '"+stringFilter.getFilterString()+"' ";
                            }
                        }
                    else
                        if(ConstantsUtils.CREATEDBY.equals(stringFilter.getPropertyId())){
                            if (!ConstantsUtils.ZERO.equals(stringFilter.getFilterString())) {
                                sql += "AND RPM.CREATED_BY = '"+stringFilter.getFilterString()+"' ";
                } 
            }
                    else
                        if(ConstantsUtils.MODIFIEDBY.equals(stringFilter.getPropertyId())){
                            if (!ConstantsUtils.ZERO.equals(stringFilter.getFilterString())) {
                                sql += "AND RPM.MODIFIED_BY = '"+stringFilter.getFilterString()+"' ";
        }
                        }
                } else if (filter instanceof Between) {
        
                    Between stringFilter = (Between) filter;
                    String startValue = DB_DATE.format(stringFilter.getStartValue());
                    String endValue = DB_DATE.format(stringFilter.getEndValue());

                    if (startValue != null) {
                        sql+=" AND "+(constantProperties.getString(stringFilter.getPropertyId().toString()))+" >= '"+startValue+"' ";
                    } 
                    if (endValue != null) {
                      sql+=" AND "+(constantProperties.getString(stringFilter.getPropertyId().toString()))+" <= '"+endValue+"' ";

                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (stringFilter.getValue() instanceof Date) {
                        String filterString = DB_DATE.format(stringFilter.getValue());
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                      sql+=" AND "+(constantProperties.getString(stringFilter.getPropertyId().toString()))+" >= '"+filterString+"' ";
                       } else {
                      sql+=" AND "+(constantProperties.getString(stringFilter.getPropertyId().toString()))+" <= '"+filterString+"' ";
                        }
                    }
                }
            }
        }
        
        String columnName = "RPM.REBATE_PLAN_ID";
        String order = "ASC";
        
        for (final Iterator<SortByColumn> iterator = columns.iterator(); iterator.hasNext();) {
            final SortByColumn sortByColumn = (SortByColumn) iterator.next();
            columnName = String.valueOf(getColumnMap().get(sortByColumn.getName()));
            
            if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                order = " ASC";
            } else {
                order = " DESC";
            }
        }
       
            sql += " ORDER BY "+columnName+" "+order+" ";
            sql += " OFFSET " + start + " ROWS FETCH NEXT " + end + " ROWS ONLY;";
        LOGGER.info("sql ------------>RPPPPP= " + sql);
        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(sql, null, null);
        LOGGER.info("After Query Hit,list size for Search results is = " + ((list == null) ? list : list.size()));
        searchList = getCustomizedSearchFormToDTO(list);
        LOGGER.info("Ends searchRebatePlan wiht search size ----" + ((searchList == null) ? searchList : searchList.size()));

        return searchList;
        
    }
    
    public Map getColumnMap() {
        Map map = new HashMap();
        map.put(ConstantsUtils.REBATE_PLAN_ID, "RPM.REBATE_PLAN_ID");
        map.put(ConstantsUtils.REBATE_PLAN_NO, "RPM.REBATE_PLAN_NO");
        map.put(ConstantsUtils.REBATE_PLAN_NAME, "RPM.REBATE_PLAN_NAME");
        map.put(ConstantsUtils.REBATE_PLAN_STATUS, "REBATE_PLAN_STATUS");
        map.put(ConstantsUtils.REBATE_PLAN_TYPE, "REBATE_PLAN_TYPE");
        map.put(ConstantsUtils.FORMULA_TYPE, "FORMULA_TYPE");
        map.put(ConstantsUtils.REBATE_BASED_ON, "REBATE_BASED_ON");
        map.put(ConstantsUtils.REBATE_STRUCTURE, "REBATE_STRUCTURE");
        map.put(ConstantsUtils.REBATE_RANGE_BASED_ON, "REBATE_RANGE_BASED_ON");
        map.put(ConstantsUtils.SECONDARY_REBATE_PLAN_ID, "RPM.SECONDARY_REBATE_PLAN_ID");
        map.put(ConstantsUtils.SECONDARY_REBATE_PLAN_NO, "RPM.SECONDARY_REBATE_PLAN_NO");
        map.put(ConstantsUtils.SECONDARY_REBATE_PLAN_NAME, "RPM.SECONDARY_REBATE_PLAN_NAME");
        map.put(ConstantsUtils.REBATE_NSF_NAME, "NSF.NET_SALES_FORMULA_NAME");
        map.put(ConstantsUtils.REBATE_RULE_NAME, "CM.RULE_NAME");
        map.put(ConstantsUtils.CREATEDDATE, "RPM.CREATED_DATE");
        map.put(ConstantsUtils.CREATEDBY, "RPM.CREATED_BY");
        map.put(ConstantsUtils.MODIFIEDDATE, "RPM.MODIFIED_DATE");
        map.put(ConstantsUtils.MODIFIEDBY, "RPM.MODIFIED_BY");
        return map;
    }
    
    /**
     * Gets the customized search form from model.
     *
     * @param list the list
     * @return the customized search form from model
     */
    public List<SearchResultsDTO> getCustomizedSearchFormToDTO(
            final List list) throws Exception {
        final List<SearchResultsDTO> searchRebatePlanList = new ArrayList<SearchResultsDTO>();
         Map<Integer, String> userMap= StplSecurity.getUserName();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final SearchResultsDTO searchRebatePlanDTO = new SearchResultsDTO();
                final Object[] object = (Object[]) list.get(i);

                searchRebatePlanDTO.setRebatePlanId(!ConstantsUtils.NULL.equals(String.valueOf(object[0])) && StringUtils.isNotBlank(String.valueOf(object[0])) ? String.valueOf(object[0]) : StringUtils.EMPTY);
                searchRebatePlanDTO.setRebatePlanNo(!ConstantsUtils.NULL.equals(String.valueOf(object[1])) && StringUtils.isNotBlank(String.valueOf(object[1])) ? String.valueOf(object[1]) : StringUtils.EMPTY);
                searchRebatePlanDTO.setRebatePlanName(!ConstantsUtils.NULL.equals(String.valueOf(object[2])) && StringUtils.isNotBlank(String.valueOf(object[2])) ? String.valueOf(object[2]) : StringUtils.EMPTY);
                searchRebatePlanDTO.setRebatePlanStatus(!ConstantsUtils.NULL.equals(String.valueOf(object[3])) && StringUtils.isNotBlank(String.valueOf(object[3])) ? String.valueOf(object[3]) : StringUtils.EMPTY);                
                searchRebatePlanDTO.setRebatePlanType(!ConstantsUtils.NULL.equals(String.valueOf(object[4])) && StringUtils.isNotBlank(String.valueOf(object[4])) && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(object[4])) ? String.valueOf(object[4]) : StringUtils.EMPTY);                
                searchRebatePlanDTO.setFormulaType(!ConstantsUtils.NULL.equals(String.valueOf(object[5])) && StringUtils.isNotBlank(String.valueOf(object[5])) ? String.valueOf(object[5]) : StringUtils.EMPTY);                
                searchRebatePlanDTO.setRebateBasedOn(!ConstantsUtils.NULL.equals(String.valueOf(object[6])) && StringUtils.isNotBlank(String.valueOf(object[6])) ? String.valueOf(object[6]) : StringUtils.EMPTY);                
                searchRebatePlanDTO.setRebateStructure(!ConstantsUtils.NULL.equals(String.valueOf(object[7])) && StringUtils.isNotBlank(String.valueOf(object[7])) ? String.valueOf(object[7]) : StringUtils.EMPTY);                
                
                searchRebatePlanDTO.setRebateRangeBasedOn(!ConstantsUtils.NULL.equals(String.valueOf(object[8])) && StringUtils.isNotBlank(String.valueOf(object[8])) ? String.valueOf(object[8]) : StringUtils.EMPTY);                
                searchRebatePlanDTO.setSecondaryRebatePlanId(!ConstantsUtils.NULL.equals(String.valueOf(object[9])) && StringUtils.isNotBlank(String.valueOf(object[9])) ? String.valueOf(object[9]) : StringUtils.EMPTY);
                searchRebatePlanDTO.setSecondaryRebatePlanNo(!ConstantsUtils.NULL.equals(String.valueOf(object[10])) && StringUtils.isNotBlank(String.valueOf(object[10])) ? String.valueOf(object[10]) : StringUtils.EMPTY);
                searchRebatePlanDTO.setSecondaryRebatePlanName(!ConstantsUtils.NULL.equals(String.valueOf(object[11])) && StringUtils.isNotBlank(String.valueOf(object[11])) ? String.valueOf(object[11]) : StringUtils.EMPTY);
                
                searchRebatePlanDTO.setBatchId(!ConstantsUtils.NULL.equals(String.valueOf(object[12])) && StringUtils.isNotBlank(String.valueOf(object[12])) ? String.valueOf(object[12]) : StringUtils.EMPTY);
                searchRebatePlanDTO.setRecordLockStatus((Boolean) object[13]);
                searchRebatePlanDTO.setSystemID(!ConstantsUtils.NULL.equals(String.valueOf(object[14])) && StringUtils.isNotBlank(String.valueOf(object[14])) ? String.valueOf(object[14]) : StringUtils.EMPTY);
                searchRebatePlanDTO.setModifiedBy(userMap.get(Integer.valueOf(String.valueOf(object[15]))) == null ? StringUtils.EMPTY : userMap.get(Integer.valueOf(String.valueOf(object[15]))));
                searchRebatePlanDTO.setCreatedBy(userMap.get(Integer.valueOf(String.valueOf(object[16]))) == null ? StringUtils.EMPTY : userMap.get(Integer.valueOf(String.valueOf(object[16]))));
                searchRebatePlanDTO.setRebateNsfName(!ConstantsUtils.NULL.equals(String.valueOf(object[17])) && StringUtils.isNotBlank(String.valueOf(object[17])) ? String.valueOf(object[17]) : StringUtils.EMPTY);
                searchRebatePlanDTO.setRebateRuleName(!ConstantsUtils.NULL.equals(String.valueOf(object[18])) && StringUtils.isNotBlank(String.valueOf(object[18])) ? String.valueOf(object[18]) : StringUtils.EMPTY);
                  if (object[19] != null) {
                        Date date = (Date) object[19];
                        searchRebatePlanDTO.setRpCreationDate(CommonUtils.convertDateToString(date));
                        DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                        date = df.parse(searchRebatePlanDTO.getRpCreationDate());
                        searchRebatePlanDTO.setRpCreatedDate(date);
                        searchRebatePlanDTO.setCreatedDate(searchRebatePlanDTO.getRpCreationDate());

                    }
                  if (object[20] != null) {
                        Date modifyDate = (Date) object[20];
                        searchRebatePlanDTO.setRpModifyDate(CommonUtils.convertDateToString(modifyDate));
                        DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                        modifyDate = df.parse(searchRebatePlanDTO.getRpModifyDate());
                        searchRebatePlanDTO.setRpModifiedDate(modifyDate);
                        searchRebatePlanDTO.setModifiedDate(searchRebatePlanDTO.getRpModifyDate());

                    }
                searchRebatePlanList.add(searchRebatePlanDTO);
            }
        }

        return searchRebatePlanList;
    }
    
    public static Map<Integer, String> getItemPricingQualifiers() throws SystemException {
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class);
          dynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        Map<Integer, String> qualifierMap = new HashMap<>();
        List<ItemPricingQualifier> list = ItemPricingQualifierLocalServiceUtil.dynamicQuery(dynamicQuery);
        for (ItemPricingQualifier itemPricingQualifier : list) {
            qualifierMap.put(itemPricingQualifier.getItemPricingQualifierSid(), itemPricingQualifier.getItemPricingQualifierName());
        }
        return qualifierMap;
    }

    @Override
    public RebatePlanMaster deleteRebatePlanById(int systemId) throws SystemException, PortalException, Exception {
        return null;
    }
    public String parseDateLogic(Object object) throws ParseException{  
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String date = formatter.format(object);
        return date;
    }
        }
