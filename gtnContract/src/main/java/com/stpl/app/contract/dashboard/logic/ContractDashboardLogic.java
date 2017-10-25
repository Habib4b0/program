package com.stpl.app.contract.dashboard.logic;

import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import com.stpl.app.contract.common.util.HelperListUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

import com.stpl.app.contract.dao.impl.ContractDashboardLogicDAOImpl;
import com.stpl.app.contract.dao.impl.DashboardLogicDAOImpl;
import com.stpl.app.contract.dashboard.dto.ContractMember;
import com.stpl.app.contract.dashboard.dto.DashBoardSearchDto;
import com.stpl.app.contract.dashboard.dto.NetSalesRuleLookupDto;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.dashboard.util.CustomTreeContainer;
import com.stpl.app.contract.dashboard.util.QueryUtil;
import com.stpl.app.contract.ui.ErrorfulFieldGroup;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.DataSourceConnection;
import com.stpl.app.model.CfpContract;
import com.stpl.app.model.CfpContractDetails;
import com.stpl.app.model.CfpModel;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.IfpContract;
import com.stpl.app.model.IfpContractDetails;
import com.stpl.app.model.IfpDetails;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.PsContract;
import com.stpl.app.model.PsContractDetails;
import com.stpl.app.model.PsDetails;
import com.stpl.app.model.PsModel;
import com.stpl.app.model.RsContract;
import com.stpl.app.model.RsContractDetails;
import com.stpl.app.model.RsContractDetailsFr;
import com.stpl.app.model.RsDetails;
import com.stpl.app.model.RsModel;
import com.stpl.app.service.CfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.CfpContractLocalServiceUtil;
import com.stpl.app.service.CfpModelLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.IfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.IfpContractLocalServiceUtil;
import com.stpl.app.service.IfpDetailsLocalServiceUtil;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.PsContractDetailsLocalServiceUtil;
import com.stpl.app.service.PsContractLocalServiceUtil;
import com.stpl.app.service.PsModelLocalServiceUtil;
import com.stpl.app.service.RsContractDetailsFrLocalServiceUtil;
import com.stpl.app.service.RsContractDetailsLocalServiceUtil;
import com.stpl.app.service.RsContractLocalServiceUtil;
import com.stpl.app.service.RsDetailsLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.domain.contract.contractdashboard.ContractDashboardDAO;
import com.stpl.domain.contract.contractdashboard.DashboardDAO;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import java.text.ParseException;
import java.util.HashMap;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 * The Class ContractDashboardLogic.
 */
public class ContractDashboardLogic extends BeanItemContainer<ContractMaster> {

    /**
     * The rs details list.
     */
    public List<RsContractDetails> rsDetailsList = new ArrayList<RsContractDetails>();

    /**
     * Logger used for ContractDashboardLogic class.
     */
    private static final Logger LOGGER = Logger.getLogger(ContractDashboardLogic.class);
    /**
     * INSTANTIATE ContractDashboardLogicDAO Implementation logic.
     */
    private final ContractDashboardDAO dao = new ContractDashboardLogicDAOImpl();
    /**
     * The size.
     */
    private final static int SIZE = NumericConstants.TWENTY;
    /**
     * The remaining count.
     */
    private int remCount;
    /**
     * Count.
     */
    private int count;
    /**
     * The enable next.
     */
    private boolean enableNext;
    /**
     * The enable prev.
     */
    private boolean enablePrev;

    /*
     * Variable used for start and end value
     */
    /**
     * The start value.
     */
    private int start;
    /**
     * The end value.
     */
    private int end;
    /**
     * Gets the current date.
     */
    private static final Date CURRENT_DATE = new Date();

    private final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static ResourceBundle constantProperties = ResourceBundle.getBundle("properties.constants");
    static HashMap<String, String> nsrDetailsDbMap = new HashMap<>();
    static HashMap<String, String> nsRuleFilterCriteria = new HashMap<>();
    static HashMap<String, String> criteria = new HashMap<>();
    static HashMap<String, String> nsRuleCriteria = new HashMap<>();
    HelperListUtil helperListUtil = HelperListUtil.getInstance();

    /**
     * Gets the dao.
     *
     * @return the dao
     */
    public ContractDashboardDAO getDao() {
        return dao;
    }

    /**
     * Gets the curren t_ date.
     *
     * @return the current date
     */
    public Date getCurrentDate() {
        return CURRENT_DATE;
    }

    /**
     * Gets the rs details list.
     *
     * @return the rs details list
     */
    public List<RsContractDetails> getRsDetailsList() {
        return rsDetailsList;
    }

    /**
     * Sets the rs details list.
     *
     * @param rsDetailsList the rs details list
     */
    public void setRsDetailsList(final List<RsContractDetails> rsDetailsList) {
        this.rsDetailsList = rsDetailsList;
    }

    /**
     * Gets the rem count.
     *
     * @return the rem count
     */
    public int getRemCount() {
        return remCount;
    }

    /**
     * Sets the rem count.
     *
     * @param remCount the rem count
     */
    public void setRemCount(final int remCount) {
        this.remCount = remCount;
    }

    /**
     * Gets the count.
     *
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the count.
     *
     * @param count the count
     */
    public void setCount(final int count) {
        this.count = count;
    }

    /**
     * Checks if is enable next.
     *
     * @return true, if checks if is enable next
     */
    public boolean isEnableNext() {
        return enableNext;
    }

    /**
     * Sets the enable next.
     *
     * @param enableNext the enable next
     */
    public void setEnableNext(final boolean enableNext) {
        this.enableNext = enableNext;
    }

    /**
     * Checks if is enable prev.
     *
     * @return true, if checks if is enable prev
     */
    public boolean isEnablePrev() {
        return enablePrev;
    }

    /**
     * Sets the enable prev.
     *
     * @param enablePrev the enable prev
     */
    public void setEnablePrev(final boolean enablePrev) {
        this.enablePrev = enablePrev;
    }

    /**
     * Gets the start.
     *
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * Sets the start.
     *
     * @param start the start
     */
    public void setStart(final int start) {
        this.start = start;
    }

    /**
     * Gets the end.
     *
     * @return the end
     */
    public int getEnd() {
        return end;
    }

    /**
     * Sets the end.
     *
     * @param end the end
     */
    public void setEnd(final int end) {
        this.end = end;
    }

    /**
     * Default Constructor for ContractDashboardLogic.
     */
    public ContractDashboardLogic() {
        super(ContractMaster.class);
    }

    //Java date format
    public static String DEFAULT_JAVA_DATE_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";
    //SQL date format
    public static String DEFAULT_SQL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * Method for getAlSearchList following param used.
     *
     * @param idValue the id value
     * @param flag the flag
     * @return the al search list
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public List<ContractMember> getAlSearchList(final String idValue, final String flag) throws SystemException, PortalException {
        List<ContractMember> resultList;
        LOGGER.debug("Entering getAlSearchList method with paramete  idValue=" + idValue + ", flag=" + flag);
        String cid;
        if (idValue.trim().equals(StringUtils.EMPTY)) {
            cid = String.valueOf(Constants.CHAR_PERCENT);
        } else {
            cid = idValue.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }

        if (flag.equals(Constants.CONTRACT)) {
            final DynamicQuery itemDynamicQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class);
            itemDynamicQuery.add(RestrictionsFactoryUtil.like(ContractUtils.CONTRACT_NO, cid));
            itemDynamicQuery.add(RestrictionsFactoryUtil.like(ContractUtils.PROCESS_STATUS, ContractUtils.DRAFT));

            resultList = getCustomizedDTOFromModel(dao.contractMasterDynamicQuery(itemDynamicQuery), flag);
        } else if (flag.equals(Constants.CFP)) {
            final DynamicQuery itemDynamicQuery = DynamicQueryFactoryUtil.forClass(CfpModel.class);
            itemDynamicQuery.add(RestrictionsFactoryUtil.like(ContractUtils.COMPANY_FAMILY_PLANNO, cid));

            resultList = getCustomizedDTOFromModel(dao.companyFamilyplanMasterDynamicQuery(itemDynamicQuery), flag);

        } else if (flag.equals(Constants.IFP)) {
            final DynamicQuery itemDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpModel.class);
            itemDynamicQuery.add(RestrictionsFactoryUtil.like(ContractUtils.ITEMFAMILY_PLANNO, cid));

            resultList = getCustomizedDTOFromModel(dao.itemFamilyPlanMasterDynamicQuery(itemDynamicQuery), flag);

        } else if (flag.equals(Constants.PS_VALUE)) {
            final DynamicQuery itemDynamicQuery = DynamicQueryFactoryUtil.forClass(PsModel.class);
            itemDynamicQuery.add(RestrictionsFactoryUtil.like(ContractUtils.PRICE_SCHEDULE_NO, cid));

            resultList = getCustomizedDTOFromModel(dao.priceScheduleMasterDynamicQuery(itemDynamicQuery), flag);

        } else if (flag.equals(Constants.RS_VALUE)) {
            final DynamicQuery itemDynamicQuery = DynamicQueryFactoryUtil.forClass(RsModel.class);
            itemDynamicQuery.add(RestrictionsFactoryUtil.like(ContractUtils.REBATE_SCHEDULE_NO, cid));

            resultList = getCustomizedDTOFromModel(dao.rebateScheduleMasterDynamicQuery(itemDynamicQuery), flag);

        } else {
            resultList = new ArrayList<ContractMember>();
        }

        LOGGER.debug("End of getAlSearchList method");
        return resultList;
    }

    /**
     * Method used for getCustomizedDTOFromModel.
     *
     * @param list the list
     * @param flag the flag
     * @return the customized dto from model
     */
    public List<ContractMember> getCustomizedDTOFromModel(final List<?> list, final String flag) {
        LOGGER.debug("Entering getCustomizedDTOFromModel method");

        final List<ContractMember> searchList = new ArrayList<ContractMember>();
        ContractMaster contractMaster;
        CfpModel cfp;
        IfpModel ifp;
        PsModel priceSchedule;
        RsModel rebateSchedule;
        if (list != null) {
            for (int i = Constants.ZERO; i < list.size(); i++) {
                if (flag.equals(Constants.CONTRACT)) {
                    contractMaster = (ContractMaster) list.get(i);
                    searchList.add(new ContractMember(contractMaster.getContractMasterSid(), contractMaster.getContractMasterSid(), contractMaster.getContractName(), contractMaster.getContractId(), contractMaster.getContractNo(),
                            Constants.CONTRACT, true));
                }
                if (flag.equals(Constants.CFP)) {
                    cfp = (CfpModel) list.get(i);
                    searchList.add(new ContractMember(cfp.getCfpModelSid(), cfp.getCfpModelSid(), cfp.getCfpName(), cfp.getCfpId(), cfp.getCfpNo(), Constants.CFP,
                            true));
                }
                if (flag.equals(Constants.IFP)) {
                    ifp = (IfpModel) list.get(i);
                    searchList.add(new ContractMember(ifp.getIfpModelSid(), ifp.getIfpModelSid(), ifp.getIfpName(), ifp.getIfpId(), ifp.getIfpNo(), Constants.IFP, true));
                }
                if (flag.equals(Constants.PS_VALUE)) {
                    priceSchedule = (PsModel) list.get(i);
                    searchList.add(new ContractMember(priceSchedule.getPsModelSid(), priceSchedule.getPsModelSid(), priceSchedule.getPsName(), priceSchedule.getPsId(), priceSchedule
                            .getPsNo(), Constants.PS_VALUE, true));
                }
                if (flag.equals(Constants.RS_VALUE)) {
                    rebateSchedule = (RsModel) list.get(i);
                    searchList.add(new ContractMember(rebateSchedule.getRsModelSid(), rebateSchedule.getRsModelSid(), rebateSchedule.getRsName(), rebateSchedule.getRsId(), rebateSchedule
                            .getRsNo(), Constants.RS_VALUE, false));
                }
            }
        }
        LOGGER.debug("End of getCustomizedDTOFromModel method");
        return searchList;
    }

    /**
     * to save CFp
     *
     * @param contractSystemId
     * @param contractMember
     * @throws SystemException
     * @throws PortalException
     */
    public void saveCFp(final int contractSystemId, final ContractMember contractMember) throws SystemException, PortalException {
        LOGGER.debug("Entering saveCFp method with contractSystemId=" + contractSystemId);

        final CfpModel companyFamily = dao.getCompanyFamilyplanMaster(contractMember.getModelSysId());

        DynamicQuery query = DynamicQueryFactoryUtil.forClass(CfpContract.class);
        query.add(RestrictionsFactoryUtil.eq("contractMasterSid", contractSystemId));
        query.add(RestrictionsFactoryUtil.eq("cfpModelSid", contractMember.getModelSysId()));
        List<CfpContract> list = CfpContractLocalServiceUtil.dynamicQuery(query);
        CfpContract cfpContract = CfpContractLocalServiceUtil.createCfpContract(Constants.ZERO);

        if (list.size() > Constants.ZERO) {
            final CfpContract cfpMasterAttached = CfpContractLocalServiceUtil.getCfpContract(list.get(Constants.ZERO).getCfpContractSid());
            cfpMasterAttached.setCfpContractSid(list.get(Constants.ZERO).getCfpContractSid());
            cfpMasterAttached.setCfpName(contractMember.getName());
            cfpMasterAttached.setCfpNo(contractMember.getMemberNo());
            cfpMasterAttached.setCfpType(companyFamily.getCfpType());
            cfpMasterAttached.setCfpCategory(companyFamily.getCfpCategory());
            cfpMasterAttached.setCfpDesignation(companyFamily.getCfpDesignation());
            cfpMasterAttached.setParentCfpId(companyFamily.getParentCfpId());
            cfpMasterAttached.setParentCfpName(companyFamily.getParentCfpName());
            cfpMasterAttached.setCfpStatus(companyFamily.getCfpStatus());
            cfpMasterAttached.setCfpTradeClass(companyFamily.getCfpTradeClass());

            cfpMasterAttached.setCfpStartDate(contractMember.getStartDate());
            cfpMasterAttached.setCfpEndDate(contractMember.getEndDate());
            cfpMasterAttached.setCreatedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
            cfpMasterAttached.setCreatedDate(new Date());
            cfpMasterAttached.setModifiedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
            cfpMasterAttached.setModifiedDate(new Date());
            cfpMasterAttached.setCfpContractAttachedDate(new Date());
            cfpMasterAttached.setRecordLockStatus(false);
            cfpMasterAttached.setInboundStatus(ContractUtils.INBOUND_STATUS_A);
            cfpMasterAttached.setContractMasterSid(contractSystemId);
            cfpMasterAttached.setCfpModelSid(contractMember.getModelSysId());
            cfpMasterAttached.setSource("GTN");
            cfpMasterAttached.setSalesInclusion(companyFamily.getSalesInclusion());
            cfpContract = dao.updateCfpMasterAttached(cfpMasterAttached);

        } else {
            final CfpContract cfpMasterAttached = CfpContractLocalServiceUtil.createCfpContract(Constants.ZERO);
            cfpMasterAttached.setContractMasterSid(contractSystemId);
            cfpMasterAttached.setCfpModelSid(contractMember.getModelSysId());
            cfpMasterAttached.setCfpName(contractMember.getName());
            cfpMasterAttached.setCfpNo(contractMember.getMemberNo());
            cfpMasterAttached.setCfpType(companyFamily.getCfpType());
            cfpMasterAttached.setCfpCategory(companyFamily.getCfpCategory());
            cfpMasterAttached.setCfpDesignation(companyFamily.getCfpDesignation());
            cfpMasterAttached.setParentCfpId(companyFamily.getParentCfpId());
            cfpMasterAttached.setParentCfpName(companyFamily.getParentCfpName());
            cfpMasterAttached.setCfpStatus(companyFamily.getCfpStatus());
            cfpMasterAttached.setCfpTradeClass(companyFamily.getCfpTradeClass());
            cfpMasterAttached.setCfpStartDate(contractMember.getStartDate());
            cfpMasterAttached.setCfpEndDate(contractMember.getEndDate());
            cfpMasterAttached.setCreatedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
            cfpMasterAttached.setCreatedDate(new Date());
            cfpMasterAttached.setModifiedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
            cfpMasterAttached.setModifiedDate(new Date());
            cfpMasterAttached.setCfpContractAttachedDate(new Date());
            cfpMasterAttached.setRecordLockStatus(false);
            cfpMasterAttached.setInboundStatus(ContractUtils.INBOUND_STATUS_A);
            cfpMasterAttached.setSource("GTN");
            cfpMasterAttached.setSalesInclusion(companyFamily.getSalesInclusion());
            cfpContract = dao.addCfpMasterAttached(cfpMasterAttached);
        }

        contractMember.setCfpContractId(cfpContract.getCfpContractSid());
        List<Object> input = new ArrayList<Object>(NumericConstants.EIGHT);
        input.add(cfpContract.getCfpContractSid());
        input.add(VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID));
        input.add(FORMAT.format(new Date()));
        input.add(VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID));
        input.add(FORMAT.format(new Date()));
        input.add(contractMember.getModelSysId());
        input.add(cfpContract.getCfpStartDate());
        input.add(cfpContract.getCfpEndDate() == null ? null : cfpContract.getCfpEndDate());

        CfpContractDetailsLocalServiceUtil.saveCfpDetailsAttached(input, null);
        LOGGER.debug("End of saveCFp method");

    }

    /**
     * to saveIFP
     *
     * @param contractSystemId
     * @param cfpSystemId
     * @param contractMember
     * @throws SystemException
     * @throws PortalException
     */
    public void saveIFP(final int contractSystemId, final int cfpSystemId, final ContractMember contractMember) throws SystemException, PortalException {

        LOGGER.debug("Entering saveIFP method with contractSystemId=" + contractSystemId);

        final IfpModel itemFamily = dao.getItemFamilyPlanMaster(contractMember.getModelSysId());

        DynamicQuery query = DynamicQueryFactoryUtil.forClass(IfpContract.class);
        query.add(RestrictionsFactoryUtil.eq("contractMasterSid", contractSystemId));
        if (cfpSystemId != Constants.ZERO) {
            query.add(RestrictionsFactoryUtil.ilike(Constants.CFP_CONTRACT_SID, String.valueOf(cfpSystemId)));
        } else {
            query.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
        }
        query.add(RestrictionsFactoryUtil.eq(Constants.IFP_SYSTEM_ID, contractMember.getModelSysId()));
        List<IfpContract> list = IfpContractLocalServiceUtil.dynamicQuery(query);
        IfpContract ifpContract = IfpContractLocalServiceUtil.createIfpContract(Constants.ZERO);

        if (list.size() > Constants.ZERO) {

            final IfpContract ifpMasterAttached = IfpContractLocalServiceUtil.getIfpContract(list.get(Constants.ZERO).getIfpContractSid());
            ifpMasterAttached.setIfpModelSid(contractMember.getModelSysId());
            ifpMasterAttached.setIfpName(contractMember.getName());
            ifpMasterAttached.setIfpNo(contractMember.getMemberNo());
            ifpMasterAttached.setIfpType(itemFamily.getIfpType());
            ifpMasterAttached.setIfpCategory(itemFamily.getIfpCategory());
            ifpMasterAttached.setIfpDesignation(itemFamily.getIfpDesignation());
            ifpMasterAttached.setParentIfpId(itemFamily.getParentIfpId());
            ifpMasterAttached.setParentIfpName(itemFamily.getParentIfpName());
            ifpMasterAttached.setIfpStatus(itemFamily.getIfpStatus());

            ifpMasterAttached.setIfpStartDate(contractMember.getStartDate());
            ifpMasterAttached.setIfpEndDate(contractMember.getEndDate());
            ifpMasterAttached.setIfpContractAttachedDate(new Date());
            ifpMasterAttached.setCreatedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
            ifpMasterAttached.setCreatedDate(new Date());
            ifpMasterAttached.setModifiedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
            ifpMasterAttached.setModifiedDate(new Date());
            ifpMasterAttached.setRecordLockStatus(false);
            ifpMasterAttached.setInboundStatus(ContractUtils.INBOUND_STATUS_A);
            ifpMasterAttached.setSource("GTN");
            ifpMasterAttached.setContractMasterSid(contractSystemId);

            ifpMasterAttached.setCfpContractSid((cfpSystemId != Constants.ZERO) ? String.valueOf(cfpSystemId) : null);

            ifpMasterAttached.setIfpModelSid(contractMember.getModelSysId());

            ifpContract = dao.updateIfpMasterAttached(ifpMasterAttached);

        } else {
            final IfpContract ifpMasterAttached = IfpContractLocalServiceUtil.createIfpContract(Constants.ZERO);
            ifpMasterAttached.setContractMasterSid(contractSystemId);
            ifpMasterAttached.setIfpModelSid(contractMember.getModelSysId());
            ifpMasterAttached.setIfpName(contractMember.getName());
            ifpMasterAttached.setIfpNo(contractMember.getMemberNo());
            ifpMasterAttached.setIfpType(itemFamily.getIfpType());
            ifpMasterAttached.setIfpCategory(itemFamily.getIfpCategory());
            ifpMasterAttached.setIfpDesignation(itemFamily.getIfpDesignation());
            ifpMasterAttached.setParentIfpId(itemFamily.getParentIfpId());
            ifpMasterAttached.setParentIfpName(itemFamily.getParentIfpName());
            ifpMasterAttached.setIfpStatus(itemFamily.getIfpStatus());
            ifpMasterAttached.setIfpStartDate(contractMember.getStartDate());
            ifpMasterAttached.setIfpEndDate(contractMember.getEndDate());
            ifpMasterAttached.setIfpContractAttachedDate(new Date());
            ifpMasterAttached.setCreatedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
            ifpMasterAttached.setCreatedDate(new Date());
            ifpMasterAttached.setModifiedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
            ifpMasterAttached.setModifiedDate(new Date());
            ifpMasterAttached.setRecordLockStatus(false);
            ifpMasterAttached.setInboundStatus(ContractUtils.INBOUND_STATUS_A);
            ifpMasterAttached.setCfpContractSid((cfpSystemId != Constants.ZERO) ? String.valueOf(cfpSystemId) : null);
            ifpMasterAttached.setSource("GTN");
            ifpMasterAttached.setIfpModelSid(contractMember.getModelSysId());

            ifpContract = dao.addIfpMasterAttached(ifpMasterAttached);
        }

        contractMember.setIfpContractId(ifpContract.getIfpContractSid());
        List<Object> input = new ArrayList<Object>(NumericConstants.TEN);
        input.add(ifpContract.getIfpContractSid());
        input.add(VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID));
        input.add(FORMAT.format(new Date()));
        input.add(VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID));
        input.add(FORMAT.format(new Date()));
        input.add(contractMember.getModelSysId());
        input.add(ifpContract.getIfpStartDate());
        input.add(ifpContract.getIfpEndDate() == null ? null : ifpContract.getIfpEndDate());
        input.add(FORMAT.format(new Date()));
        IfpContractDetailsLocalServiceUtil.saveIfpDetailsAttached(input, null);
        LOGGER.debug("End of saveIFP method");
    }

    /**
     * Method used for savePS.
     *
     * @param contractSystemId the contract system id
     * @param cfpSystemId the cfp system id
     * @param ifpSystemId the ifp system id
     * @param contractMember the contract member
     */
    public void savePS(final int contractSystemId, final int cfpSystemId, final int ifpSystemId, final ContractMember contractMember) throws SystemException, PortalException {
        LOGGER.debug("Entering getChildNodeOfPriorContractSales method with contractSystemId=" + contractSystemId);

        final PsModel priceSchedule = dao.getPriceScheduleMaster(contractMember.getModelSysId());

        DynamicQuery query = DynamicQueryFactoryUtil.forClass(PsContract.class);
        query.add(RestrictionsFactoryUtil.eq("contractMasterSid", contractSystemId));
        if (cfpSystemId != Constants.ZERO) {
            query.add(RestrictionsFactoryUtil.ilike(Constants.CFP_CONTRACT_SID, String.valueOf(cfpSystemId)));
        } else {
            query.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
        }
        if (ifpSystemId != Constants.ZERO) {
            query.add(RestrictionsFactoryUtil.ilike(Constants.IFP_CONTRACT_SID, String.valueOf(ifpSystemId)));
        } else {
            query.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
        }

        query.add(RestrictionsFactoryUtil.eq("psModelSid", contractMember.getModelSysId()));
        List<PsContract> list = PsContractLocalServiceUtil.dynamicQuery(query);
        PsContract psContract = PsContractLocalServiceUtil.createPsContract(Constants.ZERO);
        if (list.size() > Constants.ZERO) {
            final PsContract psMasterAttached = PsContractLocalServiceUtil.getPsContract(list.get(Constants.ZERO).getPsContractSid());
            psMasterAttached.setContractMasterSid(contractSystemId);
            psMasterAttached.setCfpContractSid((cfpSystemId != Constants.ZERO) ? String.valueOf(cfpSystemId) : null);
            psMasterAttached.setIfpContractSid((ifpSystemId != Constants.ZERO) ? String.valueOf(ifpSystemId) : null);
            psMasterAttached.setPsModelSid(contractMember.getModelSysId());
            psMasterAttached.setPsName(priceSchedule.getPsName());
            psMasterAttached.setPsNo(priceSchedule.getPsNo());
            psMasterAttached.setPsType(priceSchedule.getPsType());
            psMasterAttached.setPsCategory(priceSchedule.getPsCategory());
            psMasterAttached.setPsDesignation(priceSchedule.getPsDesignation());
            psMasterAttached.setPsStatus(priceSchedule.getPsStatus());

            psMasterAttached.setPsStartDate(contractMember.getStartDate());
            psMasterAttached.setPsEndDate(contractMember.getEndDate());
            psMasterAttached.setPsContractAttachedDate(new Date());
            psMasterAttached.setCreatedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
            psMasterAttached.setCreatedDate(new Date());
            psMasterAttached.setModifiedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
            psMasterAttached.setModifiedDate(new Date());
            psMasterAttached.setRecordLockStatus(false);
            psMasterAttached.setInboundStatus(ContractUtils.INBOUND_STATUS_A);
            psMasterAttached.setSource("GTN");
            psContract = dao.updatePsMasterAttached(psMasterAttached);

        } else {
            final PsContract psMasterAttached = PsContractLocalServiceUtil.createPsContract(Constants.ZERO);

            psMasterAttached.setContractMasterSid(contractSystemId);
            psMasterAttached.setCfpContractSid((cfpSystemId != Constants.ZERO) ? String.valueOf(cfpSystemId) : null);
            psMasterAttached.setIfpContractSid((ifpSystemId != Constants.ZERO) ? String.valueOf(ifpSystemId) : null);
            psMasterAttached.setPsModelSid(contractMember.getModelSysId());
            psMasterAttached.setPsName(priceSchedule.getPsName());
            psMasterAttached.setPsNo(priceSchedule.getPsNo());
            psMasterAttached.setPsType(priceSchedule.getPsType());
            psMasterAttached.setPsCategory(priceSchedule.getPsCategory());
            psMasterAttached.setPsDesignation(priceSchedule.getPsDesignation());
            psMasterAttached.setPsStatus(priceSchedule.getPsStatus());
            psMasterAttached.setPsStartDate(contractMember.getStartDate());
            psMasterAttached.setPsEndDate(contractMember.getEndDate());
            psMasterAttached.setPsContractAttachedDate(new Date());
            psMasterAttached.setCreatedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
            psMasterAttached.setCreatedDate(new Date());
            psMasterAttached.setModifiedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
            psMasterAttached.setModifiedDate(new Date());
            psMasterAttached.setRecordLockStatus(false);
            psMasterAttached.setInboundStatus(ContractUtils.INBOUND_STATUS_A);
            psMasterAttached.setSource("GTN");
            psContract = dao.addPsMasterAttached(psMasterAttached);
        }

        contractMember.setPsContractId(psContract.getPsContractSid());
        List<Object> input = new ArrayList<Object>(NumericConstants.TEN);
        input.add(psContract.getPsContractSid());
        input.add(VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID));
        input.add(FORMAT.format(new Date()));
        input.add(VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID));
        input.add(FORMAT.format(new Date()));
        input.add(contractMember.getModelSysId());
        input.add(psContract.getPsStartDate());
        input.add(psContract.getPsEndDate() == null ? null : psContract.getPsEndDate());
        input.add(FORMAT.format(new Date()));
        PsContractDetailsLocalServiceUtil.savePsDetailsAttached(input, null);

    }

    /**
     * TO save RS
     *
     * @param contractSystemId
     * @param cfpSystemId
     * @param ifpSystemId
     * @param psSystemId
     * @param contractMember
     * @throws SystemException
     * @throws PortalException
     */
    public void saveRS(final int contractSystemId, final int cfpSystemId, final int ifpSystemId, final int psSystemId, final ContractMember contractMember) throws SystemException, PortalException {
        LOGGER.debug("Entering saveRS method with contractSystemId=" + contractSystemId);

        final RsModel rebateMaster = dao.getRebateScheduleMaster(contractMember.getModelSysId());

        DynamicQuery query = DynamicQueryFactoryUtil.forClass(RsContract.class);
        query.add(RestrictionsFactoryUtil.eq("contractMasterSid", contractSystemId));
        if (cfpSystemId != Constants.ZERO) {
            query.add(RestrictionsFactoryUtil.ilike(Constants.CFP_CONTRACT_SID, String.valueOf(cfpSystemId)));
        } else {
            query.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
        }
        if (ifpSystemId != Constants.ZERO) {
            query.add(RestrictionsFactoryUtil.ilike(Constants.IFP_CONTRACT_SID, String.valueOf(ifpSystemId)));
        } else {
            query.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
        }
        if (psSystemId != Constants.ZERO) {
            query.add(RestrictionsFactoryUtil.ilike(Constants.PS_CONTRACT_SID, String.valueOf(psSystemId)));
        } else {
            query.add(RestrictionsFactoryUtil.isNull(Constants.PS_CONTRACT_SID));
        }
        query.add(RestrictionsFactoryUtil.eq("rsModelSid", contractMember.getModelSysId()));
        List<RsContract> list = RsContractLocalServiceUtil.dynamicQuery(query);
        RsContract rsContract = RsContractLocalServiceUtil.createRsContract(Constants.ZERO);

        if (list.size() > Constants.ZERO) {
            final RsContract rsMasterAttached = RsContractLocalServiceUtil.getRsContract(list.get(Constants.ZERO).getRsContractSid());
            rsMasterAttached.setContractMasterSid(contractSystemId);
            rsMasterAttached.setCfpContractSid((cfpSystemId != Constants.ZERO) ? String.valueOf(cfpSystemId) : null);
            rsMasterAttached.setIfpContractSid((ifpSystemId != Constants.ZERO) ? String.valueOf(ifpSystemId) : null);
            rsMasterAttached.setPsContractSid((psSystemId != Constants.ZERO) ? String.valueOf(psSystemId) : null);
            rsMasterAttached.setRsModelSid(contractMember.getModelSysId());
            rsMasterAttached.setRsName(contractMember.getName());
            rsMasterAttached.setRsId(rebateMaster.getRsId());
            rsMasterAttached.setRsNo(rebateMaster.getRsNo());
            rsMasterAttached.setRsType(rebateMaster.getRsType());
            rsMasterAttached.setRebateProgramType(rebateMaster.getRebateProgramType());
            rsMasterAttached.setRsCategory(rebateMaster.getRsCategory());
            if (rebateMaster.getRsStartDate().equals(rebateMaster.getRsEndDate())) {
                rsMasterAttached.setRsStartDate(contractMember.getStartDate());
                rsMasterAttached.setRsEndDate(null);
            } else {
                rsMasterAttached.setRsStartDate(contractMember.getStartDate());
                rsMasterAttached.setRsEndDate(contractMember.getEndDate());
            }
            rsMasterAttached.setRsTradeClass(rebateMaster.getRsTradeClass());
            rsMasterAttached.setRsDesignation(rebateMaster.getRsDesignation() != Constants.ZERO ? String.valueOf(rebateMaster.getRsDesignation()) : null);
            rsMasterAttached.setParentRsId(rebateMaster.getParentRsId());
            rsMasterAttached.setParentRsName(rebateMaster.getParentRsName());
            rsMasterAttached.setRsStatus(rebateMaster.getRsStatus());
            rsMasterAttached.setRsTransRefId(rebateMaster.getRsTransRefId());
            rsMasterAttached.setRsTransRefNo(rebateMaster.getRsTransRefNo());
            rsMasterAttached.setRsTransRefName(rebateMaster.getRsTransRefName());
            rsMasterAttached.setPaymentMethod(rebateMaster.getPaymentMethod());
            rsMasterAttached.setPaymentFrequency(rebateMaster.getPaymentFrequency());
            rsMasterAttached.setPaymentTerms(rebateMaster.getPaymentTerms());
            rsMasterAttached.setRebateFrequency(rebateMaster.getRebateFrequency());
            rsMasterAttached.setRsCalendar((rebateMaster.getRsCalendar() != Constants.ZERO) ? String.valueOf(rebateMaster.getRsCalendar()) : null);
            rsMasterAttached.setRebateProcessingType(rebateMaster.getRebateProcessingType());
            rsMasterAttached.setRsValidationProfile(rebateMaster.getRsValidationProfile());
            rsMasterAttached.setRebateRuleType(rebateMaster.getRebateRuleType());
            rsMasterAttached.setRebateRuleAssociation(rebateMaster.getRebateRuleAssociation());
            rsMasterAttached.setRebatePlanLevel(rebateMaster.getRebatePlanLevel());
            rsMasterAttached.setInterestBearingIndicator(Integer.valueOf(String.valueOf(rebateMaster.getInterestBearingIndicator())));
            rsMasterAttached.setInterestBearingBasis(Integer.valueOf(String.valueOf(rebateMaster.getInterestBearingBasis())));
            rsMasterAttached.setPaymentGracePeriod(rebateMaster.getPaymentGracePeriod());
            rsMasterAttached.setRsContractAttachedDate(new Date());
            rsMasterAttached.setMakePayableTo(rebateMaster.getMakePayableTo());
            rsMasterAttached.setAddress1(rebateMaster.getAddress1());
            rsMasterAttached.setAddress2(rebateMaster.getAddress2());
            rsMasterAttached.setCity(rebateMaster.getCity());
            rsMasterAttached.setState(rebateMaster.getState());
            rsMasterAttached.setZipCode(rebateMaster.getZipCode());
            rsMasterAttached.setCreatedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
            rsMasterAttached.setCreatedDate(new Date());
            rsMasterAttached.setModifiedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
            rsMasterAttached.setModifiedDate(new Date());
            rsMasterAttached.setFormulaMethodId(rebateMaster.getFormulaMethodId());
            rsMasterAttached.setRecordLockStatus(false);
            rsMasterAttached.setInboundStatus(ContractUtils.INBOUND_STATUS_A);
            rsMasterAttached.setSource("GTN");
            rsMasterAttached.setCalculationType(rebateMaster.getCalculationType());
            rsMasterAttached.setCalculationLevel(rebateMaster.getCalculationLevel());
            rsMasterAttached.setCalculationRule(rebateMaster.getCalculationRule());
            rsMasterAttached.setCalculationRuleLevel(rebateMaster.getCalculationRuleLevel());
            rsMasterAttached.setEvaluationRuleType(rebateMaster.getEvaluationRuleType());
            rsMasterAttached.setEvaluationRuleLevel(rebateMaster.getEvaluationRuleLevel());
            rsMasterAttached.setEvaluationRuleOrAssociation(rebateMaster.getEvaluationRuleOrAssociation());
            rsMasterAttached.setDeductionInclusion(rebateMaster.getDeductionInclusion());

            rsContract = dao.updateRsMasterAttached(rsMasterAttached);

        } else {
            final RsContract rsMasterAttached = RsContractLocalServiceUtil.createRsContract(Constants.ZERO);
            rsMasterAttached.setContractMasterSid(contractSystemId);
            rsMasterAttached.setCfpContractSid((cfpSystemId != Constants.ZERO) ? String.valueOf(cfpSystemId) : null);
            rsMasterAttached.setIfpContractSid((ifpSystemId != Constants.ZERO) ? String.valueOf(ifpSystemId) : null);
            rsMasterAttached.setPsContractSid((psSystemId != Constants.ZERO) ? String.valueOf(psSystemId) : null);
            rsMasterAttached.setRsModelSid(contractMember.getModelSysId());
            rsMasterAttached.setRsName(contractMember.getName());
            rsMasterAttached.setRsId(rebateMaster.getRsId());
            rsMasterAttached.setRsNo(rebateMaster.getRsNo());
            rsMasterAttached.setRsType(rebateMaster.getRsType());
            rsMasterAttached.setRebateProgramType(rebateMaster.getRebateProgramType());
            rsMasterAttached.setRsCategory(rebateMaster.getRsCategory());
            rsMasterAttached.setRsStartDate(contractMember.getStartDate());
            if (rebateMaster.getRsStartDate().equals(rebateMaster.getRsEndDate())) {
                rsMasterAttached.setRsEndDate(null);
            } else {
                rsMasterAttached.setRsEndDate(contractMember.getEndDate());
            }
            rsMasterAttached.setRsTradeClass(rebateMaster.getRsTradeClass());
            rsMasterAttached.setRsDesignation(rebateMaster.getRsDesignation() != Constants.ZERO ? String.valueOf(rebateMaster.getRsDesignation()) : null);
            rsMasterAttached.setParentRsId(rebateMaster.getParentRsId());
            rsMasterAttached.setParentRsName(rebateMaster.getParentRsName());
            rsMasterAttached.setRsStatus(rebateMaster.getRsStatus());
            rsMasterAttached.setRsTransRefId(rebateMaster.getRsTransRefId());
            rsMasterAttached.setRsTransRefNo(rebateMaster.getRsTransRefNo());
            rsMasterAttached.setRsTransRefName(rebateMaster.getRsTransRefName());
            rsMasterAttached.setPaymentMethod(rebateMaster.getPaymentMethod());
            rsMasterAttached.setPaymentFrequency(rebateMaster.getPaymentFrequency());
            rsMasterAttached.setPaymentTerms(rebateMaster.getPaymentTerms());
            rsMasterAttached.setRebateFrequency(rebateMaster.getRebateFrequency());
            rsMasterAttached.setRsCalendar((rebateMaster.getRsCalendar() != Constants.ZERO) ? String.valueOf(rebateMaster.getRsCalendar()) : null);
            rsMasterAttached.setRebateProcessingType(rebateMaster.getRebateProcessingType());
            rsMasterAttached.setRsValidationProfile(rebateMaster.getRsValidationProfile());
            rsMasterAttached.setRebateRuleType(rebateMaster.getRebateRuleType());
            rsMasterAttached.setRebateRuleAssociation(rebateMaster.getRebateRuleAssociation());
            rsMasterAttached.setRebatePlanLevel(rebateMaster.getRebatePlanLevel());
            rsMasterAttached.setInterestBearingIndicator(Integer.valueOf(String.valueOf(rebateMaster.getInterestBearingIndicator())));
            rsMasterAttached.setInterestBearingBasis(Integer.valueOf(String.valueOf(rebateMaster.getInterestBearingBasis())));
            rsMasterAttached.setPaymentGracePeriod(rebateMaster.getPaymentGracePeriod());
            rsMasterAttached.setRsContractAttachedDate(new Date());
            rsMasterAttached.setMakePayableTo(rebateMaster.getMakePayableTo());
            rsMasterAttached.setAddress1(rebateMaster.getAddress1());
            rsMasterAttached.setAddress2(rebateMaster.getAddress2());
            rsMasterAttached.setCity(rebateMaster.getCity());
            rsMasterAttached.setState(rebateMaster.getState());
            rsMasterAttached.setZipCode(rebateMaster.getZipCode());
            rsMasterAttached.setCreatedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
            rsMasterAttached.setCreatedDate(new Date());
            rsMasterAttached.setModifiedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
            rsMasterAttached.setModifiedDate(new Date());
            rsMasterAttached.setFormulaMethodId(rebateMaster.getFormulaMethodId());
            rsMasterAttached.setRecordLockStatus(false);
            rsMasterAttached.setInboundStatus(ContractUtils.INBOUND_STATUS_A);
            rsMasterAttached.setContractMasterSid(contractSystemId);
            rsMasterAttached.setSource("GTN");
            rsMasterAttached.setCalculationType(rebateMaster.getCalculationType());
            rsMasterAttached.setCalculationLevel(rebateMaster.getCalculationLevel());
            rsMasterAttached.setCalculationRule(rebateMaster.getCalculationRule());
            rsMasterAttached.setCalculationRuleLevel(rebateMaster.getCalculationRuleLevel());
            rsMasterAttached.setEvaluationRuleType(rebateMaster.getEvaluationRuleType());
            rsMasterAttached.setEvaluationRuleLevel(rebateMaster.getEvaluationRuleLevel());
            rsMasterAttached.setEvaluationRuleOrAssociation(rebateMaster.getEvaluationRuleOrAssociation());
            rsMasterAttached.setDeductionInclusion(rebateMaster.getDeductionInclusion());
            rsContract = dao.addRsMasterAttached(rsMasterAttached);
        }

        List<Object> input = new ArrayList<Object>(NumericConstants.TEN);
        input.add(rsContract.getRsContractSid());
        input.add(VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID));
        input.add(FORMAT.format(new Date()));
        input.add(VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID));
        input.add(FORMAT.format(new Date()));
        input.add(contractMember.getModelSysId());
        input.add(rsContract.getRsStartDate());
        input.add(rsContract.getRsEndDate() == null ? null : rsContract.getRsEndDate());
        input.add(FORMAT.format(new Date()));
        RsContractDetailsLocalServiceUtil.saveRsDetailsAttached(input, null);

        DynamicQuery rsContractDetailsQuery = DynamicQueryFactoryUtil.forClass(RsContractDetails.class);
        rsContractDetailsQuery.add(RestrictionsFactoryUtil.eq(Constants.RS_CONTRACT_SID, rsContract.getRsContractSid()));
        rsContractDetailsQuery.add(RestrictionsFactoryUtil.ne(Constants.INBOUND_STATUS, Constants.STATUS_D));
        List<RsContractDetails> rsContDetList = RsContractDetailsLocalServiceUtil.dynamicQuery(rsContractDetailsQuery);
        for (int j = Constants.ZERO; j < rsContDetList.size(); j++) {
            RsContract rsCont = RsContractLocalServiceUtil.getRsContract(rsContDetList.get(j).getRsContractSid());
            DynamicQuery rsDetailsQuery = DynamicQueryFactoryUtil.forClass(RsDetails.class);
            rsDetailsQuery.add(RestrictionsFactoryUtil.eq("rsModelSid", rsCont.getRsModelSid()));
            rsDetailsQuery.add(RestrictionsFactoryUtil.eq("itemMasterSid", rsContDetList.get(j).getItemMasterSid()));
            rsDetailsQuery.add(RestrictionsFactoryUtil.ne(Constants.INBOUND_STATUS, Constants.STATUS_D));
            List<RsDetails> rsdList = RsDetailsLocalServiceUtil.dynamicQuery(rsDetailsQuery);
            if (rsdList != null && !rsdList.isEmpty()) {
                RsContractDetailsLocalServiceUtil.saveFormulaToContractRs(rsContDetList.get(j).getRsContractDetailsSid(), rsdList.get(Constants.ZERO).getRsDetailsSid(), rsContDetList.get(j).getItemMasterSid());
            }
        }
        LOGGER.debug("End of saveRS method");
    }

    public List<Object> addToTreeValidation(ContractMember srcBean) {
        List<Object> result = null;
        String query;
        QueryUtil sql = new QueryUtil();
        query = sql.addToTreeValidationQuery(srcBean.getCategory());
        query = query.replace("@MODEL_SID", String.valueOf(srcBean.getModelSysId()));
        query = query.replace("@START_DATE", "'" + srcBean.getStartDate() + "'");
        if (srcBean.getEndDate() == null) {
            query = query.replace("@END_DATE", "NULL");
        } else {
            query = query.replace("@END_DATE", "'" + srcBean.getEndDate() + "'");
        }
        result = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return result;
    }

    /**
     * TO update Contract
     *
     * @param contractSystemId
     * @throws SystemException
     * @throws PortalException
     */
    public void updateContract(final int contractSystemId) throws SystemException, PortalException {
        LOGGER.debug("Entering updateContract method with contractSystemId=" + contractSystemId);

        final ContractMaster contractMaster = dao.getContractMaster(contractSystemId);
        contractMaster.setProcessStatus(true);
        contractMaster.setModifiedBy(Integer.valueOf(VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID).toString()));
        contractMaster.setModifiedDate(new Date());
        contractMaster.setSource("GTN");
        contractMaster.setInboundStatus(ContractUtils.INBOUND_STATUS_C);
        dao.updateContractMaster(contractMaster);

    }

    /**
     * Method used for getProcessedQuery.
     *
     * @param contractId the contract id
     * @param start
     * @param end
     * @return the processed query
     */
    public DynamicQuery getProcessedQuery(final String contractId, final int start, final int end) {
        LOGGER.debug("Entering getProcessedQuery method");

        final DynamicQuery contractQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class);
        String contract;
        if (contractId.trim().equals(StringUtils.EMPTY)) {
            contract = String.valueOf(Constants.CHAR_PERCENT);
        } else {
            contract = contractId.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }
        contractQuery.add(RestrictionsFactoryUtil.eq("processStatus", true));
        contractQuery.add(RestrictionsFactoryUtil.like(Constants.CONTRACT_NO, contract));
        contractQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
        contractQuery.setLimit(start, end);
        LOGGER.debug("End of getProcessedQuery method");
        return contractQuery;
    }

    /**
     * Method used for getProcessedQuery.
     *
     * @param contractId the contract id
     * @param start
     * @param end
     * @return the processed query
     */
    public List getRightProcessedQuery(final CustomFieldGroup rightSearchBinder, final int start, final int end) {
        LOGGER.debug("Entering getProcessedQuery method");
        QueryUtil queryUtil = new QueryUtil();
        DashboardDAO dao = new DashboardLogicDAOImpl();
        String query = queryUtil.getRightContractComponentSearch(rightSearchBinder, start, end, false);
        List resultCount = dao.executeSelectQuery(query);

        LOGGER.debug("End of getProcessedQuery method");
        return resultCount;
    }

    /**
     * Method used for getQueriedCount.
     *
     * @param contractId the contract id
     * @return the queried count
     * @throws SystemException
     */
    public int getQueriedCount(final String contractId) throws SystemException {
        LOGGER.debug("Entering getQueriedCount method");
        final DynamicQuery contractQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class);
        String contract;
        if (contractId.trim().equals(StringUtils.EMPTY)) {
            contract = String.valueOf(Constants.CHAR_PERCENT);
        } else {
            contract = contractId.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }
        contractQuery.add(RestrictionsFactoryUtil.eq("processStatus", true));
        contractQuery.add(RestrictionsFactoryUtil.like(Constants.CONTRACT_NO, contract));
        contractQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
        LOGGER.debug("End of getQueriedCount method");
        return (int) dao.contractMasterDynamicQueryCount(contractQuery);
    }

    /**
     * Method used for getQueriedCount.
     *
     * @param contractId the contract id
     * @return the queried count
     * @throws SystemException
     */
    public int getRightQueriedCount(final CustomFieldGroup rightSearchBinder) throws SystemException {
        LOGGER.debug("Entering getQueriedCount method");
        QueryUtil queryUtil = new QueryUtil();
        DashboardDAO dao = new DashboardLogicDAOImpl();
        int count = Constants.ZERO;
        try {
            String query = queryUtil.getRightContractComponentSearch(rightSearchBinder, Constants.ZERO, Constants.ZERO, true);
            List resultCount = dao.executeSelectQuery(query);

            if (resultCount != null && !resultCount.isEmpty()) {
                count = Integer.valueOf(String.valueOf(resultCount.get(Constants.ZERO)));
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("End of getQueriedCount method");
        return count;
    }

    /**
     * Method used for getTotalCount.
     *
     * @return the total count
     */
    public int getTotalCount() throws SystemException {
        return dao.getContractMasterCount();
    }

    /**
     * Method used for getHierarchyList.
     *
     * @param contractId the contract id
     * @param startIndex the start index
     * @param offset the offset
     * @param contractML the contract ml
     * @return the hierarchy list
     */
    public HierarchicalContainer getHierarchyList(final List<ContractMember> contractML) {
        LOGGER.debug("Entering getHierarchyList method");

        final HierarchicalContainer container = new HierarchicalContainer();
        container.addContainerProperty(Constants.CATEGORY, String.class, StringUtils.EMPTY);
        container.addContainerProperty(Constants.MEMBER_ID, String.class, StringUtils.EMPTY);
        container.addContainerProperty(Constants.MEMBER_NO, String.class, StringUtils.EMPTY);
        container.addContainerProperty(Constants.NAME, String.class, StringUtils.EMPTY);

        new Object() {
            /**
             * Method used for put
             *
             * @param contractMemberList
             * @param parent
             * @param container
             */
            @SuppressWarnings("PMD")
            public void put(final List<ContractMember> contractList, final Object parent, final HierarchicalContainer container) {
                for (int i = Constants.ZERO; i < contractList.size(); i++) {
                    final ContractMember contractMember = (ContractMember) contractList.get(i);
                    final BeanItem<ContractMember> item = new BeanItem<ContractMember>(contractMember);

                    container.addItem(item);

                    if (contractMember.getSub() != null && contractMember.getSub().size() > Constants.ZERO) {
                        container.setChildrenAllowed(item, true);

                    } else {
                        container.setChildrenAllowed(item, false);
                    }
                    final Collection<?> ids = container.getContainerPropertyIds();
                    for (final Iterator<?> iterator = ids.iterator(); iterator.hasNext();) {
                        final Object propertyId = iterator.next();
                        container.getItem(item).getItemProperty(propertyId.toString()).setValue(item.getItemProperty(propertyId.toString()).getValue());
                    }
                    if (parent != null) {
                        container.setParent(item, parent);
                    }

                }
            }
        }.put(contractML, null, container);
        LOGGER.debug("End of getHierarchyList method");
        return container;
    }

    /**
     * to get Level 1 Hierarchy
     *
     * @param contractId
     * @param container
     * @param start
     * @param end
     * @return
     * @throws SystemException
     */
    public CustomTreeContainer<ContractMember> getLevel1Hierarchy(final CustomFieldGroup rightSearchBinder, final CustomTreeContainer<ContractMember> container, final int start, final int end) throws SystemException {
        LOGGER.debug("Entering getLevel1Hierarchy method");
        final List<ContractMember> contractList = getContractList(rightSearchBinder, ContractMember.LEVEL1, start, end);
        container.removeAllItems();
        for (final Iterator<ContractMember> iterator = contractList.iterator(); iterator.hasNext();) {
            final ContractMember contractMember = (ContractMember) iterator.next();
            container.addBean(contractMember);
            if (!Constants.RS_VALUE.equals(contractMember.getCategory()) && isLevel2ListAvlbl(contractMember.getSystemId(), contractMember.getCategory())) {
                container.setChildrenAllowed(contractMember, true);
            } else {
                container.setChildrenAllowed(contractMember, false);
            }
        }
        LOGGER.debug("End of getLevel1Hierarchy method");
        return container;
    }

    /**
     * to get Level NumericConstants.TWO Hierarchy
     *
     * @param parent
     * @param container
     * @return
     * @throws SystemException
     */
    public CustomTreeContainer<ContractMember> getLevel2Hierarchy(final ContractMember parent, final CustomTreeContainer<ContractMember> container, final int start, final int end) throws SystemException, PortalException {
        LOGGER.debug("Entering getLevel2Hierarchy method");
        container.removeAllItems();
        container.addBean(parent);
        container.setChildrenAllowed(parent, !Constants.RS_VALUE.equals(parent.getCategory()));
        ContractMember contractMember;
        final List<ContractMember> contractList = getLevel2List(parent, start, end, true);
        for (final Iterator<ContractMember> iterator = contractList.iterator(); iterator.hasNext();) {
            contractMember = iterator.next();
            container.addBean(contractMember);
            if (!Constants.RS_VALUE.equals(contractMember.getCategory()) && isLevel3ListAvlbl(contractMember.getSystemId(), contractMember.getCategory())) {
                container.setChildrenAllowed(contractMember, true);
            } else {
                container.setChildrenAllowed(contractMember, false);
            }
            container.setParent(contractMember, parent);
        }
        LOGGER.debug("End of getLevel2Hierarchy method");
        return container;
    }

    /**
     * To get Level 3Hierarachy
     *
     * @param parent2
     * @param container
     * @return
     * @throws SystemException
     */
    public CustomTreeContainer<ContractMember> getLevel3Hierarchy(final ContractMember parent2, final CustomTreeContainer<ContractMember> container, final int start, final int end) throws SystemException, PortalException {
        LOGGER.debug("Entering getLevel3Hierarchy method");
        container.removeAllItems();

        container.addBean(parent2.getParent1());

        container.setChildrenAllowed(parent2.getParent1(), true);
        container.addBean(parent2);
        container.setChildrenAllowed(parent2, true);
        container.setParent(parent2, parent2.getParent1());

        final List<ContractMember> contractML = getLevel3List(parent2.getParent1(), parent2, start, end, true);

        ContractMember contractMember;
        for (final Iterator<ContractMember> iterator = contractML.iterator(); iterator.hasNext();) {
            contractMember = iterator.next();

            container.addBean(contractMember);

            if (!Constants.RS_VALUE.equals(contractMember.getCategory()) && isLevel4ListAvlbl(contractMember.getSystemId(), contractMember.getCategory())) {
                container.setChildrenAllowed(contractMember, true);
            } else {
                container.setChildrenAllowed(contractMember, false);
            }

            container.setParent(contractMember, parent2);

        }
        LOGGER.debug("End of getLevel3Hierarchy method");
        return container;
    }

    /**
     * to get Level 4 Hierarchy
     *
     * @param parent3
     * @param container
     * @return
     * @throws SystemException
     */
    public CustomTreeContainer<ContractMember> getLevel4Hierarchy(final ContractMember parent3, final CustomTreeContainer<ContractMember> container, final int start, final int end) throws SystemException, PortalException {
        LOGGER.debug("Entering getLevel4Hierarchy method");

        container.removeAllItems();
        container.addBean(parent3.getParent1());
        container.setChildrenAllowed(parent3.getParent1(), true);
        container.addBean(parent3.getParent2());
        container.setChildrenAllowed(parent3.getParent2(), true);
        container.setParent(parent3.getParent2(), parent3.getParent1());
        container.addBean(parent3);
        container.setChildrenAllowed(parent3, true);
        container.setParent(parent3, parent3.getParent2());

        final List<ContractMember> contractList = getLevel4List(parent3.getParent1(), parent3.getParent2(), parent3, start, end, true);
        ContractMember contractMember;
        for (final Iterator<ContractMember> iterator = contractList.iterator(); iterator.hasNext();) {
            contractMember = iterator.next();

            container.addBean(contractMember);

            if (!Constants.RS_VALUE.equals(contractMember.getCategory()) && isLevel4ListAvlbl(contractMember.getSystemId(), contractMember.getCategory())) {
                container.setChildrenAllowed(contractMember, true);
            } else {
                container.setChildrenAllowed(contractMember, false);
            }

            container.setParent(contractMember, parent3);

        }
        LOGGER.debug("End of getLevel4Hierarchy method");
        return container;
    }

    /**
     * to get Level 5 Hierarchy
     *
     * @param parent4
     * @param container
     * @return
     * @throws SystemException
     */
    public CustomTreeContainer<ContractMember> getLevel5Hierarchy(final ContractMember parent4, final CustomTreeContainer<ContractMember> container, final int start, final int end) throws SystemException, PortalException {
        LOGGER.debug("Entering getLevel5Hierarchy method");

        container.removeAllItems();
        container.addBean(parent4.getParent1());
        container.setChildrenAllowed(parent4.getParent1(), true);
        container.addBean(parent4.getParent2());
        container.setChildrenAllowed(parent4.getParent2(), true);
        container.setParent(parent4.getParent2(), parent4.getParent1());
        container.addBean(parent4.getParent3());
        container.setChildrenAllowed(parent4.getParent3(), true);
        container.setParent(parent4.getParent3(), parent4.getParent2());
        container.addBean(parent4);
        container.setChildrenAllowed(parent4, true);
        container.setParent(parent4, parent4.getParent3());

        if (!Constants.RS_VALUE.equals(parent4.getCategory()) && isLevel5ListAvlbl(parent4.getSystemId())) {
            final List<ContractMember> contractList = getLevel5List(parent4.getParent1(), parent4.getParent2(), parent4.getParent3(), parent4, start, end, true);
            ContractMember contractMember;
            for (final Iterator<ContractMember> iterator = contractList.iterator(); iterator.hasNext();) {
                contractMember = iterator.next();
                container.addBean(contractMember);

                container.setChildrenAllowed(contractMember, false);

                container.setParent(contractMember, parent4);

            }
        }
        LOGGER.debug("End of getLevel5Hierarchy method");
        return container;
    }

    /**
     * to get Level 2 List
     *
     * @param parent1
     * @return
     * @throws SystemException
     */
    public List<ContractMember> getLevel2List(final ContractMember parent1, final int start, final int end, final boolean isLimit) throws SystemException, PortalException {

        LOGGER.debug("Entering getLevel2List method");
        List<ContractMember> level2List;
        level2List = new ArrayList<ContractMember>();
        if (getCFPQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level2List = getCFPList(parent1, ContractMember.LEVEL2, start, end, isLimit);
        }
        if (getIFPCount(parent1.getSystemId(), Constants.ZERO, NumericConstants.ONE) > Constants.ZERO) {
            level2List.addAll(getIFPList(parent1, null, ContractMember.LEVEL2, start, end, isLimit));
        }
        if (getPSCount(parent1.getSystemId(), Constants.ZERO, Constants.ZERO, NumericConstants.ONE) > Constants.ZERO) {
            level2List.addAll(getPSList(parent1, null, null, ContractMember.LEVEL2, start, end, isLimit));
        }
        if (getRSCount(parent1.getSystemId(), Constants.ZERO, Constants.ZERO, Constants.ZERO, NumericConstants.ONE) > Constants.ZERO) {
            level2List.addAll(getRSList(parent1, null, null, null, ContractMember.LEVEL2, start, end, isLimit));
        }
        LOGGER.debug("End of getLevel2List method");
        return level2List;
    }

    /**
     * Gets the level3 list.
     *
     * @param parent1 the parent1
     * @param parent2 the parent2
     * @return the level3 list
     */
    public List<ContractMember> getLevel3List(final ContractMember parent1, final ContractMember parent2, final int start, final int end, final boolean isLimit) throws SystemException, PortalException {

        LOGGER.debug("Entering getLevel3List method");
        List<ContractMember> level3List;
        level3List = new ArrayList<ContractMember>();
        if (Constants.CFP.equals(parent2.getCategory())) {
            if (getIFPCount(parent1.getSystemId(), parent2.getInternalId(), NumericConstants.TWO) > Constants.ZERO) {
                level3List.addAll(getIFPList(parent1, parent2, ContractMember.LEVEL3, start, end, isLimit));
            }
            if (getPSCount(parent1.getSystemId(), parent2.getInternalId(),Constants.ZERO, NumericConstants.TWO) > Constants.ZERO) {
                level3List.addAll(getPSList(parent1, parent2, null, ContractMember.LEVEL3, start, end, isLimit));
            }
            if (getRSCount(parent1.getSystemId(), parent2.getInternalId(), Constants.ZERO, Constants.ZERO, NumericConstants.TWO) > Constants.ZERO) {
                level3List.addAll(getRSList(parent1, parent2, null, null, ContractMember.LEVEL3, start, end, isLimit));
            }

        } else if (Constants.IFP.equals(parent2.getCategory())) {
            if (getPSCount(parent1.getSystemId(), Constants.ZERO, parent2.getInternalId(), NumericConstants.TWO) > Constants.ZERO) {
                level3List.addAll(getPSList(parent1, null, parent2, ContractMember.LEVEL3, start, end, isLimit));
            }
            if (getRSCount(parent1.getSystemId(), Constants.ZERO, parent2.getInternalId(), Constants.ZERO, NumericConstants.TWO) > Constants.ZERO) {
                level3List.addAll(getRSList(parent1, null, parent2, null, ContractMember.LEVEL3, start, end, isLimit));
            }

        } else if (Constants.PS_VALUE.equals(parent2.getCategory()) && getRSCount(parent1.getSystemId(), Constants.ZERO, Constants.ZERO, parent2.getInternalId(), NumericConstants.TWO) > Constants.ZERO) {
                level3List.addAll(getRSList(parent1, null, null, parent2, ContractMember.LEVEL3, start, end, isLimit));
        }
        LOGGER.debug("End of getLevel3List method");
        return level3List;
    }

    /**
     * Gets the level4 list.
     *
     * @param parent1 the parent1
     * @param parent2 the parent2
     * @param parent3 the parent3
     * @return the level4 list
     */
    public List<ContractMember> getLevel4List(final ContractMember parent1, final ContractMember parent2, final ContractMember parent3, final int start, final int end, final boolean isLimit) throws SystemException, PortalException {
        LOGGER.debug("Entering getLevel4List method");
              
        List<ContractMember> level4List;
        level4List = new ArrayList<ContractMember>();
        try{
        if (Constants.IFP.equals(parent3.getCategory())) {
            if (getPSCount(parent1.getSystemId(), parent2.getInternalId(), parent3.getInternalId(), NumericConstants.THREE) > Constants.ZERO) {
                level4List.addAll(getPSList(parent1, parent2, parent3, ContractMember.LEVEL4, start, end, isLimit));
            }
            if (getRSCount(parent1.getSystemId(), parent2.getInternalId(), parent3.getInternalId(), Constants.ZERO, NumericConstants.THREE) > Constants.ZERO) {
                level4List.addAll(getRSList(parent1, parent2, parent3, null, ContractMember.LEVEL4, start, end, isLimit));
            }
        } else if (Constants.PS_VALUE.equals(parent3.getCategory()) && (getRSCount(parent1.getSystemId(), parent2.getInternalId(), Constants.ZERO, parent3.getInternalId(), NumericConstants.THREE) > Constants.ZERO)) {
                level4List.addAll(getRSList(parent1, parent2, parent3, null, ContractMember.LEVEL4, start, end, isLimit));
        }
        LOGGER.debug("End of getLevel4List method");        
        }
        catch(Exception ex){
            LOGGER.error(ex);
        }
        return level4List;
}

    /**
     * Gets the level5 list.
     *
     * @param parent1
     * @param parent2
     * @param parent3
     * @param parent4
     * @return
     * @throws SystemException
     */
    public List<ContractMember> getLevel5List(final ContractMember parent1, final ContractMember parent2, final ContractMember parent3, final ContractMember parent4, final int start, final int end, final boolean isLimit) throws SystemException, PortalException {

        LOGGER.debug("Entering getLevel5List method");

        List<ContractMember> level5List;
        if (!Constants.RS_VALUE.equals(parent3.getCategory()) && getRSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level5List = getRSList(parent1, parent2, parent3, parent4, ContractMember.LEVEL5, start, end, isLimit);
        } else {
            level5List = new ArrayList<ContractMember>();
        }

        LOGGER.debug("End of getLevel5List method");
        return level5List;
    }

    /**
     * Checks if is level2 list avlbl.
     *
     * @param contractSystemId the contract system id
     * @return true, if checks if is level2 list avlbl
     */
    public boolean isLevel2ListAvlbl(final int contractSystemId, final String category) throws SystemException {
        LOGGER.debug("Entering isLevel2ListAvlbl method");
        boolean available;
        if (!Constants.CFP.equals(category) && getCFPQueriedCount(contractSystemId) > Constants.ZERO) {
            available = true;
        } else if (!Constants.IFP.equals(category) && getIFPQueriedCount(contractSystemId) > Constants.ZERO) {
            available = true;
        } else if (!Constants.PS_VALUE.equals(category) && getPSQueriedCount(contractSystemId) > Constants.ZERO) {
            available = true;
        } else if (!Constants.RS_VALUE.equals(category) && getRSQueriedCount(contractSystemId) > Constants.ZERO) {
            available = true;
        } else {
            available = false;
        }

        LOGGER.debug("End of isLevel2ListAvlbl method");
        return available;
    }

    /**
     * Checks if is level3 list avlbl.
     *
     * @param contractSystemId the contract system id
     * @return true, if checks if is level3 list avlbl
     */
    public boolean isLevel3ListAvlbl(final int contractSystemId, final String category) throws SystemException {

        LOGGER.debug("Entering isLevel3ListAvlbl method");
        boolean available;
        if (!Constants.IFP.equals(category) && getIFPQueriedCount(contractSystemId) > Constants.ZERO) {
            available = true;
        } else if (!Constants.PS_VALUE.equals(category) && getPSQueriedCount(contractSystemId) > Constants.ZERO) {
            available = true;
        } else if (!Constants.RS_VALUE.equals(category) && getRSQueriedCount(contractSystemId) > Constants.ZERO) {
            available = true;
        } else {
            available = false;
        }
        LOGGER.debug("End of isLevel3ListAvlbl method");
        return available;
    }

    public boolean isLevel4ListAvlbl(final int contractSystemId, final String category) throws SystemException {

        LOGGER.debug("Entering isLevel4ListAvlbl method");
        boolean available;
        available = (!Constants.PS_VALUE.equals(category) && getPSQueriedCount(contractSystemId) > Constants.ZERO) || (!Constants.RS_VALUE.equals(category) && getRSQueriedCount(contractSystemId) > Constants.ZERO);

        LOGGER.debug("End of isLevel4ListAvlbl method");
        return available;
    }

    /**
     * Checks if is level5 list avlbl.
     *
     * @param contractSystemId the contract system id
     * @return true, if checks if is level5 list avlbl
     */
    public boolean isLevel5ListAvlbl(final int contractSystemId) throws SystemException {
        LOGGER.debug("Entering isLevel5ListAvlbl method");
        if (getRSQueriedCount(contractSystemId) > Constants.ZERO) {
            return true;
        }
        LOGGER.debug("End of isLevel5ListAvlbl method");
        return false;
    }

    public int getCFPQueriedCount(final int contractSystemId) throws SystemException {
        LOGGER.debug("Entering getCFPQueriedCount method");

        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CfpContract.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_MASTER_SID, contractSystemId));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
        LOGGER.debug("End of getCFPQueriedCount method");
        return (int) dao.contractMasterDynamicQueryCount(cfpDynamicQuery);
    }

    public int getIFPQueriedCount(final int contractSystemId) throws SystemException {
        LOGGER.debug("Entering getIFPQueriedCount method");

        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContract.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_MASTER_SID, contractSystemId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
        LOGGER.debug("End of getIFPQueriedCount method");
        return (int) dao.contractMasterDynamicQueryCount(ifpDynamicQuery);
    }

    public int getIFPCount(final int contractSystemId, final int cfpContractSystemId, final int levelNo) throws SystemException {
        LOGGER.debug("Entering getIFPQueriedCount method");

        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContract.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_MASTER_SID, contractSystemId));
        if (levelNo == NumericConstants.ONE) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
        } else if (levelNo == NumericConstants.TWO) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.CFP_CONTRACT_SID, String.valueOf(cfpContractSystemId)));
        }

        ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
        LOGGER.debug("End of getIFPQueriedCount method");
        return (int) dao.contractMasterDynamicQueryCount(ifpDynamicQuery);
    }

    /**
     * Gets the ps queried count.
     *
     * @param contractSystemId the contract system id
     * @return the PS queried count
     */
    public int getPSQueriedCount(final int contractSystemId) throws SystemException {
        LOGGER.debug("Entering getPSQueriedCount method");

        final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsContract.class);
        psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_MASTER_SID, contractSystemId));
        psDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
        LOGGER.debug("End of getPSQueriedCount method");
        return (int) dao.contractMasterDynamicQueryCount(psDynamicQuery);
    }

    public int getPSCount(final int contractSystemId, final int cfpContractSystemId, final int ifpContractSystemId, final int levelNo) throws SystemException {
        LOGGER.debug("Entering getPSQueriedCount method");

        final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsContract.class);
        psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_MASTER_SID, contractSystemId));
        if (levelNo == NumericConstants.ONE) {
            psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
            psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
        } else if (levelNo == NumericConstants.TWO && ifpContractSystemId == Constants.ZERO) {
            psDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.CFP_CONTRACT_SID, String.valueOf(cfpContractSystemId)));
            psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
        } else if (levelNo == NumericConstants.TWO && cfpContractSystemId == Constants.ZERO) {
            psDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.IFP_CONTRACT_SID, String.valueOf(ifpContractSystemId)));
            psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
        } else if (levelNo == NumericConstants.THREE) {
            psDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.IFP_CONTRACT_SID, String.valueOf(ifpContractSystemId)));
            psDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.CFP_CONTRACT_SID, String.valueOf(cfpContractSystemId)));
        }

        psDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
        LOGGER.debug("End of getPSQueriedCount method");
        return (int) dao.contractMasterDynamicQueryCount(psDynamicQuery);
    }

    public int getRSQueriedCount(final int contractSystemId) throws SystemException {
        LOGGER.debug("Entering getRSQueriedCount method");

        final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsContract.class);
        rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_MASTER_SID, contractSystemId));
        rsDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
        LOGGER.debug("End of getRSQueriedCount method");
        return (int) dao.contractMasterDynamicQueryCount(rsDynamicQuery);
    }

    public int getRSCount(final int contractSystemId, final int cfpContractSystemId, final int ifpContractSystemId, final int psContractSystemId, final int levelNo) throws SystemException {
        LOGGER.debug("Entering getRSQueriedCount method");

        final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsContract.class);
        rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_MASTER_SID, contractSystemId));
        if (levelNo == NumericConstants.ONE) {
            rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
            rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
            rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.PS_CONTRACT_SID));
        } else if (levelNo == NumericConstants.TWO) {
            if (cfpContractSystemId != Constants.ZERO) {
                rsDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.CFP_CONTRACT_SID, String.valueOf(cfpContractSystemId)));
                rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
                rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.PS_CONTRACT_SID));
            } else if (ifpContractSystemId != Constants.ZERO) {
                rsDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.IFP_CONTRACT_SID, String.valueOf(ifpContractSystemId)));
                rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
                rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.PS_CONTRACT_SID));
            } else if (psContractSystemId != Constants.ZERO) {
                rsDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.PS_CONTRACT_SID, String.valueOf(psContractSystemId)));
                rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
                rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
            }

        } else if (levelNo == NumericConstants.THREE) {
            if (cfpContractSystemId == Constants.ZERO) {
                rsDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.IFP_CONTRACT_SID, String.valueOf(ifpContractSystemId)));
                rsDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.PS_CONTRACT_SID, String.valueOf(psContractSystemId)));
                rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
            } else if (ifpContractSystemId == Constants.ZERO) {
                rsDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.CFP_CONTRACT_SID, String.valueOf(cfpContractSystemId)));
                rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
                rsDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.PS_CONTRACT_SID, String.valueOf(psContractSystemId)));
            } else if (psContractSystemId == Constants.ZERO) {
                rsDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.IFP_CONTRACT_SID, String.valueOf(ifpContractSystemId)));
                rsDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.CFP_CONTRACT_SID, String.valueOf(cfpContractSystemId)));
                rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.PS_CONTRACT_SID));
            }

        }

        rsDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
        LOGGER.debug("End of getRSQueriedCount method");       
         return (int) dao.contractMasterDynamicQueryCount(rsDynamicQuery);
    }

    /**
     * Gets the contract list.
     *
     * @param contractId the contract id
     * @param level the level
     * @return the contract list
     */
    @SuppressWarnings(ContractUtils.UNCHECKED)
    public List<ContractMember> getContractList(final CustomFieldGroup rightSearchBinder, final int level, final int start, final int end) throws SystemException {
        LOGGER.debug("Entering getContractList method");

        final List<ContractMember> contractList = new ArrayList<ContractMember>();
        try {
            List result = getRightProcessedQuery(rightSearchBinder, start, end);
            Object[] record;

            ContractMember contractMember;

            if (result != null) {
                for (int i = Constants.ZERO; i < result.size(); i++) {
                    record = (Object[]) result.get(i);
                    contractMember = new ContractMember();
                    contractMember.setSystemId(Integer.valueOf(String.valueOf(record[Constants.ZERO])));
                    contractMember.setModelSysId(Integer.valueOf(String.valueOf(record[Constants.ZERO])));
                    contractMember.setMemberId(record[NumericConstants.ONE] != null ? String.valueOf(record[NumericConstants.ONE]) : StringUtils.EMPTY);
                    contractMember.setMemberNo(record[NumericConstants.TWO] != null ? String.valueOf(record[NumericConstants.TWO]) : StringUtils.EMPTY);
                    contractMember.setName(record[NumericConstants.THREE] != null ? String.valueOf(record[NumericConstants.THREE]) : StringUtils.EMPTY);
                    contractMember.setCategory(Constants.CONTRACT);
                    contractMember.setLevel(level);
                    contractMember.setInternalId(Integer.valueOf(String.valueOf(record[Constants.ZERO])));
                    contractMember.setStartDate(record[NumericConstants.FOUR] == null ? null : (Date) record[NumericConstants.FOUR]);
                    contractMember.setEndDate(record[NumericConstants.FIVE] == null ? null : (Date) record[NumericConstants.FIVE]);
                    contractList.add(contractMember);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("End of getContractList method");
        return contractList;
    }

    /**
     * Enable next button.
     *
     * @return true, if enable next button
     */
    public boolean enableNextButton() {
        return enableNext;
    }

    /*
     * Method used for return boolean
     */
    /**
     * Enable prev button.
     *
     * @return true, if enable prev button
     */
    public boolean enablePrevButton() {
        return enablePrev;
    }

    /**
     * Check page.
     *
     * @param parent1 the parent1
     */
    public void checkPage(final ContractMember parent1) throws SystemException {
        final int count = getCFPQueriedCount(parent1.getSystemId());
        enableNext = count - end > SIZE;
        enablePrev = start > SIZE;
    }

    /**
     * Click next logic.
     */
    public void clickNextLogic() {
        start = start + SIZE;
        end = end + SIZE > remCount ? remCount : SIZE;
        remCount = count - end;
    }

    /**
     * Click prev logic.
     */
    public void clickPrevLogic() {
        start = start - SIZE < remCount ? SIZE : remCount;
        end = end - SIZE;
        remCount = count - start;
    }

    @SuppressWarnings(ContractUtils.UNCHECKED)
    private List<ContractMember> getCFPList(final ContractMember parent1, final int level, final int start, final int end, final boolean isLimit) throws SystemException, PortalException {
        LOGGER.debug("Entering getCFPList method");

        final List<ContractMember> cfpList = new ArrayList<ContractMember>();
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CfpContract.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_MASTER_SID, parent1.getSystemId()));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
        if (isLimit) {
            cfpDynamicQuery.setLimit(start, end);
        }
        final List<CfpContract> cfpMasterList = dao.cfpMasterDynamicQuery(cfpDynamicQuery);

        ContractMember contractMember;
        CfpContract cfpMaster;
        for (final Iterator<CfpContract> iterator = cfpMasterList.iterator(); iterator.hasNext();) {
            cfpMaster = (CfpContract) iterator.next();
            contractMember = new ContractMember();
            contractMember.setSystemId(cfpMaster.getContractMasterSid());
            contractMember.setContractId(cfpMaster.getContractMasterSid());
            contractMember.setMemberNo(cfpMaster.getCfpNo());
            contractMember.setName(cfpMaster.getCfpName());
            CfpModel cfpModel = CfpModelLocalServiceUtil.getCfpModel(cfpMaster.getCfpModelSid());

            contractMember.setMemberId(cfpModel.getCfpId());
            contractMember.setModelSysId(cfpModel.getCfpModelSid());
            contractMember.setCategory(Constants.CFP);
            contractMember.setInternalId(cfpMaster.getCfpContractSid());
            contractMember.setLevel(level);
            contractMember.setParent1(parent1);
            contractMember.setStartDate(cfpMaster.getCfpStartDate());
            contractMember.setEndDate(cfpMaster.getCfpEndDate());
            cfpList.add(contractMember);

        }
        LOGGER.debug("End of getCFPList method");
        return cfpList;
    }

    @SuppressWarnings(ContractUtils.UNCHECKED)
    private List<ContractMember> getIFPList(final ContractMember parent1, final ContractMember parent2, final int level, final int start, final int end, final boolean isLimit) throws SystemException, PortalException {
        LOGGER.debug("Entering getIFPList method");

        final List<ContractMember> ifpList = new ArrayList<ContractMember>();
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContract.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_MASTER_SID, parent1.getSystemId()));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
        if (parent2 != null) {

            if (parent2.getCategory().equals(Constants.CFP)) {

                if (parent2.getInternalId() == Constants.ZERO) {
                    ifpDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
                } else {
                    ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CFP_CONTRACT_SID, String.valueOf(parent2.getInternalId())));
                }

            }
        } else {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
        }
        if (isLimit) {
            ifpDynamicQuery.setLimit(start, end);
        }
        final List<IfpContract> ifpMasterList = dao.ifpMasterDynamicQuery(ifpDynamicQuery);
        ContractMember contractMember;
        IfpContract ifpContract;
        if (!ifpMasterList.isEmpty()) {
            for (final Iterator<IfpContract> iterator = ifpMasterList.iterator(); iterator.hasNext();) {
                ifpContract = (IfpContract) iterator.next();
                contractMember = new ContractMember();
                contractMember.setSystemId(ifpContract.getContractMasterSid());
                contractMember.setContractId(ifpContract.getContractMasterSid()); // Added for GAL-6400
                contractMember.setMemberNo(ifpContract.getIfpNo());
                contractMember.setName(ifpContract.getIfpNo());
                IfpModel ifpModel = IfpModelLocalServiceUtil.getIfpModel(ifpContract.getIfpModelSid());
                contractMember.setMemberId(ifpModel.getIfpId());
                contractMember.setName(ifpContract.getIfpName());
                contractMember.setModelSysId(ifpModel.getIfpModelSid());
                contractMember.setCategory(Constants.IFP);
                contractMember.setLevel(level);
                contractMember.setParent1(parent1);
                contractMember.setParent2(parent2);
                contractMember.setInternalId(ifpContract.getIfpContractSid());
                contractMember.setStartDate(ifpContract.getIfpStartDate());
                contractMember.setEndDate(ifpContract.getIfpEndDate());
                ifpList.add(contractMember);
            }
        }
        LOGGER.debug("End of getIFPList method");
        return ifpList;
    }

    /**
     * Gets the ps list.
     *
     * @param parent1 the parent1
     * @param parent2 the parent2
     * @param parent3 the parent3
     * @param level the level
     * @return the PS list
     */
    @SuppressWarnings(ContractUtils.UNCHECKED)
    private List<ContractMember> getPSList(final ContractMember parent1, final ContractMember parent2, final ContractMember parent3, final int level, final int start, final int end, final boolean isLimit) throws SystemException, PortalException {
        LOGGER.debug("Entering getPSList method");

        final List<ContractMember> psList = new ArrayList<ContractMember>();
        final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsContract.class);
        psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_MASTER_SID, parent1.getSystemId()));
        psDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
        if (parent2 != null) {

            if (parent2.getCategory().equals(Constants.CFP)) {
                if (parent2.getInternalId() == Constants.ZERO) {
                    psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
                } else {
                    psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CFP_CONTRACT_SID, String.valueOf(parent2.getInternalId())));
                }
            }
            if (parent2.getCategory().equals(Constants.IFP)) {

                if (parent2.getInternalId() == Constants.ZERO) {
                    psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
                } else {
                    psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IFP_CONTRACT_SID, String.valueOf(parent2.getInternalId())));
                }

            }
        } else {
            psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
        }
        if (parent3 != null) {

            if (parent3.getCategory().equals(Constants.CFP)) {
                if (parent3.getInternalId() == Constants.ZERO) {
                    psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
                } else {
                    psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CFP_CONTRACT_SID, String.valueOf(parent3.getInternalId())));
                }

            }
            if (parent3.getCategory().equals(Constants.IFP)) {
                if (parent3.getInternalId() == Constants.ZERO) {
                    psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
                } else {
                    psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IFP_CONTRACT_SID, String.valueOf(parent3.getInternalId())));
                }

            }
        } else {
            psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
        }
        if (isLimit) {
            psDynamicQuery.setLimit(start, end);
        }
        final List<PsContract> psMasterList = dao.psMasterDynamicQuery(psDynamicQuery);

        ContractMember contractMember;

        for (final Iterator<PsContract> iterator = psMasterList.iterator(); iterator.hasNext();) {
            final PsContract psMaster = (PsContract) iterator.next();
            contractMember = new ContractMember();
            contractMember.setSystemId(psMaster.getContractMasterSid());
            contractMember.setContractId(psMaster.getContractMasterSid());// Added for GAL-6400
            contractMember.setName(psMaster.getPsName());
            contractMember.setMemberNo(psMaster.getPsNo());
            PsModel psModel = PsModelLocalServiceUtil.getPsModel(psMaster.getPsModelSid());
            contractMember.setMemberId(psModel.getPsId());
            contractMember.setModelSysId(psModel.getPsModelSid());
            contractMember.setCategory(Constants.PS_VALUE);
            contractMember.setLevel(level);
            contractMember.setParent1(parent1);
            contractMember.setParent2(parent2);
            contractMember.setParent3(parent3);
            contractMember.setInternalId(psMaster.getPsContractSid());
            contractMember.setStartDate(psMaster.getPsStartDate());
            contractMember.setEndDate(psMaster.getPsEndDate());
            psList.add(contractMember);
        }
        LOGGER.debug("End of getPSList method");
        return psList;
    }

    @SuppressWarnings(ContractUtils.UNCHECKED)
    private List<ContractMember> getRSList(final ContractMember parent1, final ContractMember parent2, final ContractMember parent3, final ContractMember parent4, final int level, final int start, final int end, final boolean isLimit)
            throws SystemException, PortalException {
        LOGGER.debug("Entering getRSList method");

        final List<ContractMember> rsList = new ArrayList<ContractMember>();
        final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsContract.class);
        rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_MASTER_SID, parent1.getSystemId()));
        rsDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
        if (parent2 != null) {

            if (parent2.getCategory().equals(Constants.CFP)) {
                if (parent2.getInternalId() == Constants.ZERO) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CFP_CONTRACT_SID, String.valueOf(parent2.getInternalId())));
                }
            }
            if (parent2.getCategory().equals(Constants.IFP)) {

                if (parent2.getInternalId() == Constants.ZERO) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IFP_CONTRACT_SID, String.valueOf(parent2.getInternalId())));
                }

            }
            if (parent2.getCategory().equals(Constants.PS_VALUE)) {

                if (parent2.getInternalId() == Constants.ZERO) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.PS_CONTRACT_SID));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.PS_CONTRACT_SID, String.valueOf(parent2.getInternalId())));
                }

            }
        } else {
            rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
        }
        if (parent3 != null) {

            if (parent3.getCategory().equals(Constants.CFP)) {
                if (parent3.getInternalId() == Constants.ZERO) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CFP_CONTRACT_SID, String.valueOf(parent3.getInternalId())));
                }

            }
            if (parent3.getCategory().equals(Constants.IFP)) {
                if (parent3.getInternalId() == Constants.ZERO) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IFP_CONTRACT_SID, String.valueOf(parent3.getInternalId())));
                }

            }
            if (parent3.getCategory().equals(Constants.PS_VALUE)) {

                if (parent3.getInternalId() == Constants.ZERO) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.PS_CONTRACT_SID));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.PS_CONTRACT_SID, String.valueOf(parent3.getInternalId())));
                }

            }
        } else {
            rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
        }
        if (parent4 != null) {

            if (parent4.getCategory().equals(Constants.CFP)) {
                if (parent4.getInternalId() == Constants.ZERO) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CFP_CONTRACT_SID, String.valueOf(parent4.getInternalId())));
                }

            }
            if (parent4.getCategory().equals(Constants.IFP)) {
                if (parent4.getInternalId() == Constants.ZERO) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IFP_CONTRACT_SID, String.valueOf(parent4.getInternalId())));
                }

            }
            if (parent4.getCategory().equals(Constants.PS_VALUE)) {

                if (parent4.getInternalId() == Constants.ZERO) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.PS_CONTRACT_SID));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.PS_CONTRACT_SID, String.valueOf(parent4.getInternalId())));
                }

            }
        } else {
            rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.PS_CONTRACT_SID));
        }
        if (isLimit) {
            rsDynamicQuery.setLimit(start, end);
        }
        final List<RsContract> rsMasterList = dao.rsMasterDynamicQuery(rsDynamicQuery);

        ContractMember contractMember;
        RsContract rsMaster;
        for (final Iterator<RsContract> iterator = rsMasterList.iterator(); iterator.hasNext();) {
            rsMaster = (RsContract) iterator.next();
            contractMember = new ContractMember();
            contractMember.setSystemId(rsMaster.getContractMasterSid());
            contractMember.setContractId(rsMaster.getContractMasterSid()); // Added for GAL-6400
            contractMember.setName(rsMaster.getRsName());
            contractMember.setMemberNo(rsMaster.getRsNo());
            RsModel rsModel = RsModelLocalServiceUtil.getRsModel(rsMaster.getRsModelSid());
            contractMember.setMemberId(rsModel.getRsId());
            contractMember.setModelSysId(rsModel.getRsModelSid());
            contractMember.setCategory(Constants.RS_VALUE);
            contractMember.setLevel(level);
            contractMember.setParent1(parent1);
            contractMember.setParent2(parent2);
            contractMember.setParent3(parent3);
            contractMember.setParent4(parent4);
            contractMember.setInternalId(rsMaster.getRsContractSid());
            contractMember.setStartDate(rsMaster.getRsStartDate());
            contractMember.setEndDate(rsMaster.getRsEndDate());
            rsList.add(contractMember);
        }
        LOGGER.debug("End of getRSList method");
        return rsList;
    }


    private List<Integer> getContractIdList(final List<ContractMaster> allContractList) {
        final List<Integer> contractIdList = new ArrayList<Integer>();
        if (allContractList != null) {
            for (int i = Constants.ZERO; i < allContractList.size(); i++) {
                final ContractMaster contractMaster = allContractList.get(i);
                contractIdList.add(contractMaster.getContractMasterSid());
            }
        }
        return contractIdList;
    }

    private List<RsContract> getAllRsList(final List<Integer> contractIdList) throws SystemException {
        final List<RsContract> allRsList = new ArrayList<RsContract>();
        if (!contractIdList.isEmpty()) {
            final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsContract.class);
            rsDynamicQuery.add(RestrictionsFactoryUtil.in(Constants.CONTRACT_MASTER_SID, contractIdList.toArray()));
            final List<RsContract> tempRsList = dao.rsMasterDynamicQuery(rsDynamicQuery);
            if (tempRsList != null) {
                for (final Iterator<RsContract> iterator = tempRsList.iterator(); iterator.hasNext();) {
                    final RsContract rsValue = iterator.next();
                    allRsList.add(rsValue);
                }
            }
        }
        return allRsList;
    }

    private List<PsContract> getAllPsList(final List<Integer> contractIdList) throws SystemException {
        final List<PsContract> allPsList = new ArrayList<PsContract>();
        if (!contractIdList.isEmpty()) {
            final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsContract.class);
            psDynamicQuery.add(RestrictionsFactoryUtil.in(Constants.CONTRACT_MASTER_SID, contractIdList.toArray()));
            final List<PsContract> tempPsList = dao.psMasterDynamicQuery(psDynamicQuery);
            if (tempPsList != null) {
                for (int i = Constants.ZERO; i < tempPsList.size(); i++) {
                    final PsContract psValue = tempPsList.get(i);
                    allPsList.add(psValue);
                }
            }
        }
        return allPsList;
    }

    private List<CfpContract> getAllCfpList(final List<Integer> contractIdList) throws SystemException {

        final List<CfpContract> allCfpList = new ArrayList<CfpContract>();

        if (!contractIdList.isEmpty()) {
            final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CfpContract.class);
            cfpDynamicQuery.add(RestrictionsFactoryUtil.in(Constants.CONTRACT_MASTER_SID, contractIdList.toArray()));
            LOGGER.debug("cfpMasterDynamicQuery(cfpDynamicQuery)");
            final List<CfpContract> tempCfpList = dao.cfpMasterDynamicQuery(cfpDynamicQuery);
            LOGGER.debug("returns  List<CfpMasterAttached> size=" + tempCfpList.size());
            for (int i = Constants.ZERO; i < tempCfpList.size(); i++) {
                final CfpContract cfp = tempCfpList.get(i);
                allCfpList.add(cfp);
            }
        }
        return allCfpList;
    }

    private List<IfpContract> getAllIfpList(final List<Integer> contractIdList) throws SystemException {
        final List<IfpContract> allIfpList = new ArrayList<IfpContract>();
        if (!contractIdList.isEmpty()) {
            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContract.class);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.in(Constants.CONTRACT_MASTER_SID, contractIdList.toArray()));
            LOGGER.debug("IfpMasterDynamicQuery(ifpDynamicQuery)");
            final List<IfpContract> tempIfpList = dao.ifpMasterDynamicQuery(ifpDynamicQuery);
            LOGGER.debug("returns  List<IfpMasterAttached> size=" + tempIfpList.size());
            if (tempIfpList != null) {
                for (int i = Constants.ZERO; i < tempIfpList.size(); i++) {
                    final IfpContract ifp = tempIfpList.get(i);
                    allIfpList.add(ifp);
                }
            }
        }
        return allIfpList;
    }

    private List<CfpContract> getCfpList(final List<CfpContract> allCfpList, final ContractMaster contractName) {
        final List<CfpContract> cfpList = new ArrayList<CfpContract>();
        for (int i = Constants.ZERO; i < allCfpList.size(); i++) {
            final CfpContract cfp = allCfpList.get(i);
            if (cfp.getContractMasterSid() == contractName.getContractMasterSid()) {
                cfpList.add(cfp);
            }
        }
        return cfpList;
    }
    /**
     * Disassemple.
     *
     * @param contractSystemId the contract system id
     * @return true, if disassemple
     * @throws PortalException
     * @throws SystemException
     */
    public Boolean disassemple(final int contractSystemId) throws SystemException, PortalException {
        /**
         * Boolean used for status
         */
        Boolean status = false;
        LOGGER.debug("Entering disassemple method");
        try {
            final ContractMaster contractMaster = dao.getContractMaster(contractSystemId);
            if (contractMaster.getRecordLockStatus()) {
                status = false;
            } else {

                final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CfpContract.class);
                cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_MASTER_SID, contractSystemId));
                cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.RECORD_LOCK_STATUS, false));
                final List<CfpContract> cfpList = dao.cfpMasterDynamicQuery(cfpDynamicQuery);
                if (cfpList != null) {
                    for (CfpContract cfpMasterAttached : cfpList) {
                        cfpMasterAttached.setInboundStatus(ContractUtils.INBOUND_STATUS_D);
                        dao.updateCfpMasterAttached(cfpMasterAttached);
                    }
                }
                if (cfpList != null) {
                    for (CfpContract cfpContract : cfpList) {
                        final DynamicQuery cfpDetailsQuery = DynamicQueryFactoryUtil.forClass(CfpContractDetails.class);
                        cfpDetailsQuery.add(RestrictionsFactoryUtil.eq(Constants.CFP_CONTRACT_SID, cfpContract.getCfpContractSid()));
                        cfpDetailsQuery.add(RestrictionsFactoryUtil.eq(Constants.RECORD_LOCK_STATUS, Boolean.FALSE));
                        final List<CfpContractDetails> cfpDetailsList = dao.cfpDetailsDynamicQuery(cfpDetailsQuery);
                        if (cfpDetailsList != null) {
                            for (CfpContractDetails cfpAttached : cfpDetailsList) {
                                cfpAttached.setInboundStatus(ContractUtils.INBOUND_STATUS_D);
                                dao.updateCfpDetailsAttached(cfpAttached);
                            }
                        }
                    }
                }
                final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContract.class);
                ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_MASTER_SID, contractSystemId));
                ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.RECORD_LOCK_STATUS, Boolean.FALSE));
                final List<IfpContract> ifpList = dao.ifpMasterDynamicQuery(ifpDynamicQuery);
                if (ifpList != null) {
                    for (IfpContract ifpMasterAttached : ifpList) {
                        ifpMasterAttached.setInboundStatus(ContractUtils.INBOUND_STATUS_D);
                        dao.updateIfpMasterAttached(ifpMasterAttached);
                    }
                }
                if (ifpList != null) {
                    for (IfpContract ifpContract : ifpList) {
                        final DynamicQuery ifpDDQuery = DynamicQueryFactoryUtil.forClass(IfpContractDetails.class);
                        ifpDDQuery.add(RestrictionsFactoryUtil.eq(Constants.IFP_CONTRACT_SID, ifpContract.getIfpContractSid()));
                        ifpDDQuery.add(RestrictionsFactoryUtil.eq(Constants.RECORD_LOCK_STATUS, Boolean.FALSE));
                        final List<IfpContractDetails> ifpDetailsList = dao.ifpDetailsDynamicQuery(ifpDDQuery);
                        if (ifpDetailsList != null) {
                            for (IfpContractDetails ifpDetails : ifpDetailsList) {
                                ifpDetails.setInboundStatus(ContractUtils.INBOUND_STATUS_D);
                                dao.updateIfpDetailsAttached(ifpDetails);
                            }
                        }
                    }
                }
                final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsContract.class);
                psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_MASTER_SID, contractSystemId));
                psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.RECORD_LOCK_STATUS, Boolean.FALSE));
                final List<PsContract> psList = dao.psMasterDynamicQuery(psDynamicQuery);
                if (psList != null) {
                    for (PsContract psMasterAttached : psList) {
                        psMasterAttached.setInboundStatus(ContractUtils.INBOUND_STATUS_D);
                        dao.updatePsMasterAttached(psMasterAttached);
                    }
                }
                if (psList != null) {
                    for (PsContract psContract : psList) {
                        final DynamicQuery psDDQuery = DynamicQueryFactoryUtil.forClass(PsContractDetails.class);
                        psDDQuery.add(RestrictionsFactoryUtil.eq(Constants.PS_CONTRACT_SID, psContract.getPsContractSid()));
                        psDDQuery.add(RestrictionsFactoryUtil.eq(Constants.RECORD_LOCK_STATUS, Boolean.FALSE));
                        final List<PsContractDetails> psDetailsList = dao.psDetailsDynamicQuery(psDDQuery);
                        if (psDetailsList != null) {
                            for (PsContractDetails psDetailsAttached : psDetailsList) {
                                psDetailsAttached.setInboundStatus(ContractUtils.INBOUND_STATUS_D);
                                dao.updatePsDetailsAttached(psDetailsAttached);
                            }
                        }
                    }
                }
                final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsContract.class);
                rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_MASTER_SID, contractSystemId));
                rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.RECORD_LOCK_STATUS, Boolean.FALSE));
                final List<RsContract> rsList = dao.rsMasterDynamicQuery(rsDynamicQuery);
                if (rsList != null) {
                    for (int i = Constants.ZERO; i < rsList.size(); i++) {
                        final RsContract rsMasterAttached = rsList.get(i);
                        rsMasterAttached.setInboundStatus(ContractUtils.INBOUND_STATUS_D);
                        dao.updateRsMasterAttached(rsMasterAttached);
                    }
                }
                if (rsList != null) {
                    for (int j = Constants.ZERO; j < rsList.size(); j++) {
                        final RsContract rsContract = rsList.get(j);
                        final DynamicQuery rsDDyQuery = DynamicQueryFactoryUtil.forClass(RsContractDetails.class);
                        rsDDyQuery.add(RestrictionsFactoryUtil.eq(Constants.RS_CONTRACT_SID, rsContract.getRsContractSid()));
                        rsDDyQuery.add(RestrictionsFactoryUtil.eq(Constants.RECORD_LOCK_STATUS, Boolean.FALSE));
                        rsDetailsList = dao.rsDetailsDynamicQuery(rsDDyQuery);
                        if (rsDetailsList != null) {
                            for (int i = Constants.ZERO; i < rsDetailsList.size(); i++) {
                                final RsContractDetails rsDetailsAttached = rsDetailsList.get(i);
                                rsDetailsAttached.setInboundStatus(ContractUtils.INBOUND_STATUS_D);
                                dao.updateRsDetailsAttached(rsDetailsAttached);
                                final DynamicQuery rsdFrDyQuery = DynamicQueryFactoryUtil.forClass(RsContractDetailsFr.class);
                                rsdFrDyQuery.add(RestrictionsFactoryUtil.eq("rsContractDetailsSid", rsDetailsAttached.getRsContractDetailsSid()));
                                rsdFrDyQuery.add(RestrictionsFactoryUtil.eq(Constants.RECORD_LOCK_STATUS, Boolean.FALSE));
                                List<RsContractDetailsFr> frList = RsContractDetailsFrLocalServiceUtil.dynamicQuery(rsdFrDyQuery);
                                if (frList != null) {
                                    for (int s = Constants.ZERO; s < frList.size(); s++) {
                                        final RsContractDetailsFr rsContractDetailsFr = frList.get(s);
                                        rsContractDetailsFr.setInboundStatus(ContractUtils.INBOUND_STATUS_D);
                                        RsContractDetailsFrLocalServiceUtil.updateRsContractDetailsFr(rsContractDetailsFr);
                                    }
                                }
                            }
                        }
                    }
                }
                contractMaster.setModifiedDate(new Date());
                dao.updateContractMaster(contractMaster);
                status = true;

            }
        } catch (Exception e) {
              LOGGER.debug(e);

        }
        LOGGER.debug("End of disassemple method");
        return status;
    }

    /*
     * Method used for getMainContractQuery
     */
    /**
     * Gets the main contract query.
     *
     * @param contractId the contract id
     * @return the main contract query
     */
    public DynamicQuery getMainContractQuery(final String contractId) {

        final DynamicQuery contractQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class);
        LOGGER.debug("Entering getMainContractQuery method");
        String contract;
        if (contractId.trim().equals(StringUtils.EMPTY)) {
            contract = String.valueOf(Constants.CHAR_PERCENT);
        } else {
            contract = contractId.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }

        contractQuery.add(RestrictionsFactoryUtil.like(Constants.CONTRACT_NO, contract));
        contractQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.like(Constants.PROCESS_STATUS, ContractUtils.SAVED), RestrictionsFactoryUtil.isNull(Constants.PROCESS_STATUS)));
        LOGGER.debug("End of getMainContractQuery method");
        return contractQuery;
    }

    /*
     * Method used for get Contract Queried Count
     */
    /**
     * Gets the contract queried count.
     *
     * @param searchDTO the search dto
     * @return the contract queried count
     * @throws SystemException
     */
    public int getContractQueriedCount(final DashBoardSearchDto searchDTO, final BeanSearchCriteria searchCriteria) throws SystemException {

        int count;
        LOGGER.debug("Entering getContractQueriedCount method");
        /**
         * Variable used for flag
         */
        final String flag = String.valueOf(searchDTO.getFlag());
        /**
         * Variable used for id
         */
        String idValue = String.valueOf(searchDTO.getFlagId());
        if (idValue.trim().equals(StringUtils.EMPTY)) {
            idValue = String.valueOf(Constants.CHAR_PERCENT);
        } else {
            idValue = idValue.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }
        if (flag.equals(Constants.CONTRACT)) {
            final DynamicQuery contractQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class);
            contractQuery.add(RestrictionsFactoryUtil.like(Constants.CONTRACT_NO, idValue));
            contractQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
            if (searchCriteria != null && searchCriteria.getFilters() != null) {
                for (Container.Filter filter : searchCriteria.getFilters()) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        if (stringFilter.getPropertyId().equals("memberId")) {
                            contractQuery.add(RestrictionsFactoryUtil.ilike(Constants.CONTRACT_ID, filterText));
                        }
                        if (stringFilter.getPropertyId().equals("memberNo")) {
                            contractQuery.add(RestrictionsFactoryUtil.ilike(Constants.CONTRACT_NO, filterText));
                        }
                        if (stringFilter.getPropertyId().equals("name")) {
                            contractQuery.add(RestrictionsFactoryUtil.ilike(Constants.CONTRACT_NAME, filterText));
                        }
                    }
                }
            }

            count = (int) dao.contractMasterDynamicQueryCount(contractQuery);
        } else if (flag.equals(Constants.CFP)) {
            final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CfpModel.class);
            cfpDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.CFP_NO, idValue));

            cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
            if (searchCriteria != null && searchCriteria.getFilters() != null) {
                for (Container.Filter filter : searchCriteria.getFilters()) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        if (stringFilter.getPropertyId().equals("memberId")) {
                            cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike("cfpId", filterText));
                        }
                        if (stringFilter.getPropertyId().equals("memberNo")) {
                            cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike("cfpNo", filterText));
                        }
                        if (stringFilter.getPropertyId().equals("name")) {
                            cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike("cfpName", filterText));
                        }
                    }
                }
            }
            count = (int) dao.contractMasterDynamicQueryCount(cfpDynamicQuery);
        } else if (flag.equals(Constants.IFP)) {
            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpModel.class);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.IFP_NO, idValue));
            if (searchCriteria != null && searchCriteria.getFilters() != null) {
                for (Container.Filter filter : searchCriteria.getFilters()) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        if (stringFilter.getPropertyId().equals("memberId")) {
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike("ifpId", filterText));
                        }
                        if (stringFilter.getPropertyId().equals("memberNo")) {
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike("ifpNo", filterText));
                        }
                        if (stringFilter.getPropertyId().equals("name")) {
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike("ifpName", filterText));
                        }
                    }
                }
            }
            ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));

            count = (int) dao.contractMasterDynamicQueryCount(ifpDynamicQuery);
        } else if (flag.equals(Constants.PS_VALUE)) {
            final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(PsModel.class);
            rsDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
            rsDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.PS_NO, idValue));
            if (searchCriteria != null && searchCriteria.getFilters() != null) {
                for (Container.Filter filter : searchCriteria.getFilters()) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        if (stringFilter.getPropertyId().equals("memberId")) {
                            rsDynamicQuery.add(RestrictionsFactoryUtil.ilike("psId", filterText));
                        }
                        if (stringFilter.getPropertyId().equals("memberNo")) {
                            rsDynamicQuery.add(RestrictionsFactoryUtil.ilike("psNo", filterText));
                        }
                        if (stringFilter.getPropertyId().equals("name")) {
                            rsDynamicQuery.add(RestrictionsFactoryUtil.ilike("psName", filterText));
                        }
                    }
                }
            }

            count = (int) dao.contractMasterDynamicQueryCount(rsDynamicQuery);
        } else if (flag.equals(Constants.RS_VALUE)) {
            final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(RsModel.class);

            psDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
            psDynamicQuery.add(RestrictionsFactoryUtil.like(ContractUtils.REBATE_SCHEDULE_NO, idValue));
            if (searchCriteria != null && searchCriteria.getFilters() != null) {
                for (Container.Filter filter : searchCriteria.getFilters()) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        if (stringFilter.getPropertyId().equals("memberId")) {
                            psDynamicQuery.add(RestrictionsFactoryUtil.ilike("rsId", filterText));
                        }
                        if (stringFilter.getPropertyId().equals("memberNo")) {
                            psDynamicQuery.add(RestrictionsFactoryUtil.ilike("rsNo", filterText));
                        }
                        if (stringFilter.getPropertyId().equals("name")) {
                            psDynamicQuery.add(RestrictionsFactoryUtil.ilike("rsName", filterText));
                        }
                    }
                }
            }
            count = (int) dao.contractMasterDynamicQueryCount(psDynamicQuery);

        } else {
            count = Constants.ZERO;
        }

        LOGGER.debug("End of getContractQueriedCount method");
        return count;

    }

    /**
     * Gets the al search list.
     *
     * @param searchDTO the search dto
     * @param start the start
     * @param end the end
     * @param orderByColumns the order by columns
     * @return the al search list
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public List<ContractMember> getAlSearchList(final DashBoardSearchDto searchDTO, final int start, final int end, final BeanSearchCriteria searchCriteria) throws SystemException, PortalException {

        List<ContractMember> resultList;
        LOGGER.debug("Entering getAlSearchList method");

        /**
         * Variable used for flag
         */
        final String flag = String.valueOf(searchDTO.getFlag());
        /**
         * Variable used for id
         */
        String idValue = String.valueOf(searchDTO.getFlagId());
        if (idValue.trim().equals(StringUtils.EMPTY)) {
            idValue = String.valueOf(Constants.CHAR_PERCENT);
        } else {
            idValue = idValue.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }

        if (flag.equals(Constants.CONTRACT)) {
            final DynamicQuery contractQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class);
            contractQuery.add(RestrictionsFactoryUtil.like(ContractUtils.CONTRACT_NO, idValue));
            contractQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
            if (searchCriteria != null && searchCriteria.getFilters() != null) {
                for (Container.Filter filter : searchCriteria.getFilters()) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        if (stringFilter.getPropertyId().equals("memberId")) {
                            contractQuery.add(RestrictionsFactoryUtil.ilike(Constants.CONTRACT_ID, filterText));
                        }
                        if (stringFilter.getPropertyId().equals("memberNo")) {
                            contractQuery.add(RestrictionsFactoryUtil.ilike(Constants.CONTRACT_NO, filterText));
                        }
                        if (stringFilter.getPropertyId().equals("name")) {
                            contractQuery.add(RestrictionsFactoryUtil.ilike(Constants.CONTRACT_NAME, filterText));
                        }
                    }
                }
            }
            contractQuery.setLimit(start, end);
            resultList = getCustomizedDTOFromModel(dao.contractMasterDynamicQuery(contractQuery), flag);
        } else if (flag.equals(Constants.CFP)) {
            final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CfpModel.class);
            cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ContractUtils.COMPANY_FAMILY_PLANNO, idValue));

            cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
            if (searchCriteria != null && searchCriteria.getFilters() != null) {
                for (Container.Filter filter : searchCriteria.getFilters()) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        if (stringFilter.getPropertyId().equals("memberId")) {
                            cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike("cfpId", filterText));
                        }
                        if (stringFilter.getPropertyId().equals("memberNo")) {
                            cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike("cfpNo", filterText));
                        }
                        if (stringFilter.getPropertyId().equals("name")) {
                            cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike("cfpName", filterText));
                        }
                    }
                }
            }
            cfpDynamicQuery.setLimit(start, end);
            resultList = getCustomizedDTOFromModel(dao.companyFamilyplanMasterDynamicQuery(cfpDynamicQuery), flag);
        } else if (flag.equals(Constants.IFP)) {
            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpModel.class);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(ContractUtils.ITEMFAMILY_PLANNO, idValue));

            ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
            if (searchCriteria != null && searchCriteria.getFilters() != null) {
                for (Container.Filter filter : searchCriteria.getFilters()) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        if (stringFilter.getPropertyId().equals("memberId")) {
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike("ifpId", filterText));
                        }
                        if (stringFilter.getPropertyId().equals("memberNo")) {
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike("ifpNo", filterText));
                        }
                        if (stringFilter.getPropertyId().equals("name")) {
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike("ifpName", filterText));
                        }
                    }
                }
            }
            ifpDynamicQuery.setLimit(start, end);
            resultList = getCustomizedDTOFromModel(dao.itemFamilyPlanMasterDynamicQuery(ifpDynamicQuery), flag);
        } else if (flag.equals(Constants.PS_VALUE)) {
            final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsModel.class);
            psDynamicQuery.add(RestrictionsFactoryUtil.like(ContractUtils.PRICE_SCHEDULE_NO, idValue));

            psDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
            if (searchCriteria != null && searchCriteria.getFilters() != null) {
                for (Container.Filter filter : searchCriteria.getFilters()) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        if (stringFilter.getPropertyId().equals("memberId")) {
                            psDynamicQuery.add(RestrictionsFactoryUtil.ilike("psId", filterText));
                        }
                        if (stringFilter.getPropertyId().equals("memberNo")) {
                            psDynamicQuery.add(RestrictionsFactoryUtil.ilike("psNo", filterText));
                        }
                        if (stringFilter.getPropertyId().equals("name")) {
                            psDynamicQuery.add(RestrictionsFactoryUtil.ilike("psName", filterText));
                        }
                    }
                }
            }
            psDynamicQuery.setLimit(start, end);
            resultList = getCustomizedDTOFromModel(dao.priceScheduleMasterDynamicQuery(psDynamicQuery), flag);
        } else if (flag.equals(Constants.RS_VALUE)) {
            final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsModel.class);
            rsDynamicQuery.add(RestrictionsFactoryUtil.like(ContractUtils.REBATE_SCHEDULE_NO, idValue));

            rsDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ContractUtils.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D)));
            if (searchCriteria != null && searchCriteria.getFilters() != null) {
                for (Container.Filter filter : searchCriteria.getFilters()) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        if (stringFilter.getPropertyId().equals("memberId")) {
                            rsDynamicQuery.add(RestrictionsFactoryUtil.ilike("rsId", filterText));
                        }
                        if (stringFilter.getPropertyId().equals("memberNo")) {
                            rsDynamicQuery.add(RestrictionsFactoryUtil.ilike("rsNo", filterText));
                        }
                        if (stringFilter.getPropertyId().equals("name")) {
                            rsDynamicQuery.add(RestrictionsFactoryUtil.ilike("rsName", filterText));
                        }
                    }
                }
            }
            rsDynamicQuery.setLimit(start, end);
            resultList = getCustomizedDTOFromModel(dao.rebateScheduleMasterDynamicQuery(rsDynamicQuery), flag);
        } else {
            resultList = new ArrayList<ContractMember>();
        }

        LOGGER.debug("End of getAlSearchList method");
        return resultList;
    }

    private String getContract(final String contractId) {
        String contract;
        if (contractId.trim().equals(StringUtils.EMPTY)) {
            contract = String.valueOf(Constants.CHAR_PERCENT);
        } else {
            contract = contractId.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }
        return contract;
    }

    /**
     * Gets the number of IFP present in the database for the given Contract and
     * CFP.
     *
     * @param contractSystemId - Contract System Id.
     * @param cfpSystemID - CFP System Id.
     * @return int - number of IFP in database.
     * @throws SystemException
     */
    public int getIFPCount(final int contractSystemId, final int cfpSystemID) throws SystemException {
        LOGGER.debug("Entering getIFPQueriedCount method");
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContract.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_MASTER_SID, contractSystemId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IFP_CONTRACT_SID, cfpSystemID));
        LOGGER.debug("End of getIFPQueriedCount method");
        return (int) dao.contractMasterDynamicQueryCount(ifpDynamicQuery);
    }

    /**
     * Gets the number of PS present in the database for the given Contract ,
     * CFP and IFP.
     *
     * @param contractSystemId - Contract System Id.
     * @param cfpSystemID - CFP System Id.
     * @param ifpSystemID - IFP System Id.
     * @return int - number of PS in database.
     * @throws SystemException
     */
    public int getPSCount(final int contractSystemId, final int cfpSystemID, final int ifpSystemID) throws SystemException {
        LOGGER.debug("Entering getPSQueriedCount method");
        final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsContract.class);
        psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_MASTER_SID, contractSystemId));
        if (cfpSystemID == Constants.ZERO) {
            psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
        } else {
            psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CFP_CONTRACT_SID, String.valueOf(cfpSystemID)));
        }
        if (ifpSystemID == Constants.ZERO) {
            psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
        } else {
            psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IFP_CONTRACT_SID, String.valueOf(ifpSystemID)));
        }
        LOGGER.debug("End of getPSQueriedCount method");
        return (int) dao.contractMasterDynamicQueryCount(psDynamicQuery);
    }

    /**
     * Gets the number of RS present in the database for the given
     * Contract,CFP,IFP and PS.
     *
     * @param contractSystemId - Contract System Id.
     * @param cfpSystemID - CFP System Id.
     * @param ifpSystemID - IFP System Id.
     * @param psSystemID - PS System Id.
     * @return int - number of RS in database.
     * @throws SystemException
     */
    public int getRSCount(final int contractSystemId, final int cfpSystemID, final int ifpSystemID, final int psSystemID) throws SystemException {
        LOGGER.debug("Entering getRSQueriedCount method");
        final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsContract.class);
        rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_MASTER_SID, contractSystemId));
        if (cfpSystemID == Constants.ZERO) {
            rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
        } else {
            rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CFP_CONTRACT_SID, String.valueOf(cfpSystemID)));
        }
        if (ifpSystemID == Constants.ZERO) {
            rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
        } else {
            rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IFP_CONTRACT_SID, String.valueOf(ifpSystemID)));
        }
        if (psSystemID == Constants.ZERO) {
            rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.PS_CONTRACT_SID));
        } else {
            rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.PS_CONTRACT_SID, String.valueOf(psSystemID)));
        }
        LOGGER.debug("End of getRSQueriedCount method");
        return (int) dao.contractMasterDynamicQueryCount(rsDynamicQuery);
    }

    public boolean validatePS(final int ifpSystemId, final int psSystemID) throws SystemException {
        boolean result = false;
        final Set<Integer> psItems = new HashSet<Integer>();
        final Set<Integer> ifpItems = new HashSet<Integer>();

        final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsDetails.class);
        psDynamicQuery.add(RestrictionsFactoryUtil.eq("psModelSid", psSystemID));
        psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IFP_SYSTEM_ID, ifpSystemId));
        psDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.INBOUND_STATUS, Constants.STATUS_D));
        final List<PsDetails> priceScheduleDetailsList = new DashBoardLogic().priceScheduleDetailsDynamicQuery(psDynamicQuery);
        for (Iterator<PsDetails> ps = priceScheduleDetailsList.iterator(); ps.hasNext();) {
            final PsDetails psItem = ps.next();
            int item = psItem.getItemMasterSid();
            psItems.add(item);
        }

        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpDetails.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IFP_SYSTEM_ID, ifpSystemId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.INBOUND_STATUS, Constants.STATUS_D));
        final List<IfpDetails> itemFamilyplanDetailsList = IfpDetailsLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
        for (Iterator<IfpDetails> ifp = itemFamilyplanDetailsList.iterator(); ifp.hasNext();) {
            final IfpDetails ifpItem = ifp.next();
            int item = ifpItem.getIfpModelSid();
            ifpItems.add(item);
        }
        if (itemFamilyplanDetailsList.size() > Constants.ZERO && priceScheduleDetailsList.size() > Constants.ZERO && ifpItems.equals(psItems)) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }


    public boolean callCcpProcedure() throws SystemException, SQLException {
        boolean status = false;

        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = dataSourceConnection.getConnection();

            LOGGER.debug("Entering callCcpProcedure  ::::");

            statement = connection.prepareCall("{call PRC_CCP_POPULATION ()}");

            statement.execute();

            LOGGER.debug("Ending callCcpProcedure return  ");
        } catch (Exception ex) {

            LOGGER.error(ex);

            return status;

        } finally {
            statement.close();
            connection.close();
        }

        return true;

    }

    public int getNsRuleCount(final ErrorfulFieldGroup searchFields, final Set<Container.Filter> filterSet) throws ParseException  {
        int count = Constants.ZERO;
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder = buildNsRuleSearchQuery(searchFields, true);
        queryBuilder = getNsRuleFilterQuery(filterSet, queryBuilder);

        queryBuilder = new StringBuilder(queryBuilder.toString().replaceAll("WHERE AND", " WHERE "));
        queryBuilder = new StringBuilder(queryBuilder.toString().endsWith("WHERE") ? queryBuilder.toString().replace("WHERE", " ") : queryBuilder);
        List<Object> masterData = (List<Object>) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        if (masterData != null && !masterData.isEmpty()) {
            Object ob = masterData.get(Constants.ZERO);
            count += Integer.valueOf(String.valueOf(ob));
        }
        LOGGER.debug(" ns rule lookup count===>" + count);
        return count;
    }

    private StringBuilder buildNsRuleSearchQuery(ErrorfulFieldGroup searchFields, boolean isCount) {
        StringBuilder queryBuilder = new StringBuilder();
        String query = isCount ? "COUNT( * )" : constantProperties.getString("netSalesRule");
        queryBuilder.append(" SELECT ").append(query).append(" FROM CDR_MODEL WHERE");
        if (nsRuleCriteria.isEmpty()) {
            loadNsRuleCriteriaMap();
        }
        Set<String> keys = nsRuleCriteria.keySet();
        for (String fields : keys) {

            if (searchFields.getField(fields).getValue() != null && !ConstantUtil.SELECT_ONE.equals(searchFields.getField(fields).getValue().toString()) && !searchFields.getField(fields).getValue().toString().trim().isEmpty()) {
                if ("ruleType".equalsIgnoreCase(fields) || "ruleCategory".equalsIgnoreCase(fields)) {
                    queryBuilder.append(" AND ").append(nsRuleCriteria.get(fields)).append(" = '").append(((HelperDTO) searchFields.getField(fields).getValue()).getId()).append("'");
                } else {
                    queryBuilder.append(" AND ").append(nsRuleCriteria.get(fields)).append(" LIKE '").append(String.valueOf(searchFields.getField(fields).getValue()).trim().replace("*", Constants.PERCENT)).append("'");
                }
            }
        }
        return queryBuilder;
    }

    private StringBuilder getNsRuleFilterQuery(final Set<Container.Filter> filterSet, final StringBuilder stringBuilder) throws ParseException {
        if (nsRuleFilterCriteria.isEmpty()) {
            nsRuleFilterCriteria.clear();
            nsRuleFilterCriteria.put("ruleType", "RULE_TYPE");
            nsRuleFilterCriteria.put("ruleNo", "RULE_NO");
            nsRuleFilterCriteria.put("ruleName", "RULE_NAME");
            nsRuleFilterCriteria.put("ruleCategoryString", "RULE_CATEGORY");
        }

        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    stringBuilder.append(" AND ").append(nsRuleFilterCriteria.get(stringFilter.getPropertyId().toString())).append(" LIKE '%").append(stringFilter.getFilterString()).append("%'");
                }
            }
        }

        return stringBuilder;
    }

    private void loadNsRuleCriteriaMap() {
        nsRuleCriteria.clear();
        nsRuleCriteria.put("ruleType", "RULE_TYPE");
        nsRuleCriteria.put("ruleNo", "RULE_NO");
        nsRuleCriteria.put("ruleName", "RULE_NAME");
        nsRuleCriteria.put("ruleCategory", "RULE_CATEGORY");
    }

    public List<NetSalesRuleLookupDto> loadNsRuleResults(
            final ErrorfulFieldGroup searchFields, final int start, final int end, final List<SortByColumn> columns, final Set<Container.Filter> filterSet) throws SystemException,ParseException {
        List<NetSalesRuleLookupDto> searchList = new ArrayList<>();
        LOGGER.debug("Entering loadNsRuleResults with start of=" + start + "and endIndex of= " + end + "  Column Size +" + ((columns == null) ? columns : columns.size()));
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder = buildNsRuleSearchQuery(searchFields, false);
        queryBuilder = getNsRuleFilterQuery(filterSet, queryBuilder);
        queryBuilder = getNsRuleOrderQuery(queryBuilder, columns, start, end);

        queryBuilder = new StringBuilder(queryBuilder.toString().replaceAll("WHERE AND", " WHERE "));
        queryBuilder = new StringBuilder(queryBuilder.toString().endsWith("WHERE") ? queryBuilder.toString().replace("WHERE", " ") : queryBuilder);

        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        searchList = getCustomizedNsRuleDTO(list);
        LOGGER.debug("End of loadNsRuleResults");
        return searchList;
    }

    private StringBuilder getNsRuleOrderQuery(StringBuilder stringBuilder, final List<SortByColumn> sortByColumns, final int startIndex, final int endIndex) {
        boolean sortOrder = false;
        String orderByColumn = null;
        if (nsRuleFilterCriteria.isEmpty()) {
            nsRuleFilterCriteria.clear();
            nsRuleFilterCriteria.put("ruleType", "RULE_TYPE");
            nsRuleFilterCriteria.put("ruleNo", "RULE_NO");
            nsRuleFilterCriteria.put("ruleName", "RULE_NAME");
            nsRuleFilterCriteria.put("ruleCategoryString", "RULE_CATEGORY");
        }
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                orderByColumn = nsRuleFilterCriteria.get(sortByColumn.getName());
                sortOrder = sortByColumn.getType() != SortByColumn.Type.ASC;
            }
        }
        stringBuilder = new StringBuilder(stringBuilder.toString().endsWith("WHERE") ? stringBuilder.toString().replace("WHERE", " ") : stringBuilder);
        if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
            stringBuilder.append(" ORDER BY CREATED_DATE ");
        } else {
            stringBuilder.append(" ORDER BY ").append(orderByColumn).append((!sortOrder) ? " ASC " : " DESC ");
        }

        stringBuilder.append(" OFFSET ").append(startIndex);
        stringBuilder.append(" ROWS FETCH NEXT ").append(endIndex).append(" ROWS ONLY;");

        return stringBuilder;
    }

    private List<NetSalesRuleLookupDto> getCustomizedNsRuleDTO(List list) {
        final List<NetSalesRuleLookupDto> searchResultsList = new ArrayList<>();
        try {
            if (list != null) {

                for (int i = Constants.ZERO; i < list.size(); i++) {
                    final NetSalesRuleLookupDto searchDTO = new NetSalesRuleLookupDto();
                    final Object[] object = (Object[]) list.get(i);
                    searchDTO.setRuleSystemId(!Constants.NULL.equals(String.valueOf(object[Constants.ZERO])) && StringUtils.isNotBlank(String.valueOf(object[Constants.ZERO])) ? String.valueOf(object[Constants.ZERO]) : "0");
                    searchDTO.setRuleType(helperListUtil.getIdHelperDTOMap().get(object[NumericConstants.ONE] != null ? Integer.valueOf(String.valueOf(object[NumericConstants.ONE])) : Constants.ZERO));
                    searchDTO.setRuleNo(!Constants.NULL.equals(String.valueOf(object[NumericConstants.TWO])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.TWO])) ? String.valueOf(object[NumericConstants.TWO]) : StringUtils.EMPTY);
                    searchDTO.setRuleName(!Constants.NULL.equals(String.valueOf(object[NumericConstants.THREE])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.THREE])) ? String.valueOf(object[NumericConstants.THREE]) : StringUtils.EMPTY);
                    if (object[NumericConstants.FOUR] != null) {
                        searchDTO.setRuleCategory(helperListUtil.getIdHelperDTOMap().get(object[NumericConstants.FOUR] != null ? Integer.valueOf(String.valueOf(object[NumericConstants.FOUR])) : Constants.ZERO));
                        searchDTO.setRuleCategoryString(ConstantsUtils.SELECT_ONE.equals(searchDTO.getRuleCategory().getDescription()) ? StringUtils.EMPTY : searchDTO.getRuleCategory().getDescription());
                    }
                    searchResultsList.add(searchDTO);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return searchResultsList;
    }

    public int getNsRuleDetailsCount(final String ruleSystemId, final Set<Container.Filter> filterSet) throws ParseException {
        LOGGER.debug("Entering getNsRuleDetailsCount ");
      int count = Constants.ZERO;
        if (!"0".equals(ruleSystemId)) {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder = buildNsRuleDetailsQuery(ruleSystemId, true);
            queryBuilder = getNsRuleDetailsFilterQuery(filterSet, queryBuilder);

            queryBuilder = new StringBuilder(queryBuilder.toString().replaceAll("WHERE AND", " WHERE "));
            queryBuilder = new StringBuilder(queryBuilder.toString().endsWith("WHERE") ? queryBuilder.toString().replace("WHERE", " ") : queryBuilder);

            List<Object> masterData = (List<Object>) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
            if (masterData != null && !masterData.isEmpty()) {
                Object ob = masterData.get(Constants.ZERO);
                count += Integer.valueOf(String.valueOf(ob));
            }
        }
        LOGGER.debug("End of getNsRuleDetailsCount= " + count);
        return count;
    }

    private StringBuilder buildNsRuleDetailsQuery(String ruleSystemId, boolean isCount) {
        StringBuilder queryBuilder = new StringBuilder();
        String query = isCount ? "COUNT( * )" : constantProperties.getString("netSalesRuleDetails");
        queryBuilder.append(" SELECT ").append(query).append(" FROM CDR_DETAILS WHERE CDR_MODEL_SID = ").append(ruleSystemId);

        return queryBuilder;
    }

    private StringBuilder getNsRuleDetailsFilterQuery(final Set<Container.Filter> filterSet, final StringBuilder stringBuilder) throws ParseException {

        if (nsrDetailsDbMap.isEmpty()) {
            loadNsRuleDetailsMap();
        }

        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    stringBuilder.append(" AND ").append(nsrDetailsDbMap.get(stringFilter.getPropertyId().toString())).append(" LIKE '%").append(stringFilter.getFilterString()).append("%'");
                }
            }
        }

        return stringBuilder;
    }

    private void loadNsRuleDetailsMap() {
        nsrDetailsDbMap.clear();
        nsrDetailsDbMap.put("lineType", "LINE_TYPE");
        nsrDetailsDbMap.put("association", "ITEM_GROUP_MS_ASSOCIATION");
        nsrDetailsDbMap.put("keyword", "KEYWORD");
        nsrDetailsDbMap.put("operator", "OPERATOR");
        nsrDetailsDbMap.put("value", "VALUE");
        nsrDetailsDbMap.put("comparison", "COMPARISON");
        nsrDetailsDbMap.put("logicalOperator", "LOGICAL_OPERATOR");
    }

    public List<NetSalesRuleLookupDto> loadNsRuleDetailsResults(
            final String ruleSystemId, final int start, final int end, final List<SortByColumn> columns, final Set<Container.Filter> filterSet) throws SystemException {
        List<NetSalesRuleLookupDto> searchList = new ArrayList<>();
        try {
            LOGGER.debug("Entering loadNsRuleResults with start of=" + start + "and endIndex of= " + end + "  Column Size +" + ((columns == null) ? columns : columns.size()));
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder = buildNsRuleDetailsQuery(ruleSystemId, false);
            queryBuilder = getNsRuleDetailsFilterQuery(filterSet, queryBuilder);
            queryBuilder = getNsRuleDetailsOrderQuery(queryBuilder, columns, start, end);

            queryBuilder = new StringBuilder(queryBuilder.toString().replaceAll("WHERE AND", " WHERE "));
            queryBuilder = new StringBuilder(queryBuilder.toString().endsWith("WHERE") ? queryBuilder.toString().replace("WHERE", " ") : queryBuilder);

            final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
            searchList = getCustomizedNsRuleDetailsDTO(list);
        } catch (Exception ec) {
            LOGGER.error(ec);
        }
        LOGGER.debug("End of loadNsRuleResults");
        return searchList;
    }

    private StringBuilder getNsRuleDetailsOrderQuery(final StringBuilder stringBuilder, final List<SortByColumn> sortByColumns, final int startIndex, final int endIndex) {
        boolean sortOrder = false;
        String orderByColumn = null;
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                orderByColumn = nsrDetailsDbMap.get(sortByColumn.getName());
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

    private List<NetSalesRuleLookupDto> getCustomizedNsRuleDetailsDTO(List list) {
        final List<NetSalesRuleLookupDto> searchResultsList = new ArrayList<>();
        try {
            if (list != null) {

                for (Object nsrList : list) {
                    final NetSalesRuleLookupDto searchDTO = new NetSalesRuleLookupDto();
                    final Object[] object = (Object[]) nsrList;
                    searchDTO.setRuleSystemId(!Constants.NULL.equals(String.valueOf(object[Constants.ZERO])) && StringUtils.isNotBlank(String.valueOf(object[Constants.ZERO])) ? String.valueOf(object[Constants.ZERO]) : "0");
                    searchDTO.setLineType(object[NumericConstants.ONE] != null ? getDescById((int) object[NumericConstants.ONE]) : StringUtils.EMPTY);
                    searchDTO.setAssociation(object[NumericConstants.TWO] != null ? getDescById((int) object[NumericConstants.TWO]) : StringUtils.EMPTY);
                    searchDTO.setKeyword(object[NumericConstants.THREE] != null ? getDescById((int) object[NumericConstants.THREE]) : StringUtils.EMPTY);
                    searchDTO.setOperator(object[NumericConstants.FOUR] != null ? getDescById((int) object[NumericConstants.FOUR]) : StringUtils.EMPTY);
                    searchDTO.setValue(!Constants.NULL.equals(String.valueOf(object[NumericConstants.FIVE])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.FIVE])) ? String.valueOf(object[NumericConstants.FIVE]) : StringUtils.EMPTY);
                    searchDTO.setComparison(object[NumericConstants.SIX] != null ? getDescById((int) object[NumericConstants.SIX]) : StringUtils.EMPTY);
                    searchDTO.setLogicalOperator(object[NumericConstants.SEVEN] != null ? getDescById((int) object[NumericConstants.SEVEN]) : StringUtils.EMPTY);
                    searchResultsList.add(searchDTO);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return searchResultsList;
    }

    private String getDescById(int systemId) throws PortalException, SystemException {
        if (systemId == Constants.ZERO) {
            return StringUtils.EMPTY;
        } else {
             return helperListUtil.getIdHelperDTOMap().get(systemId).getDescription(); 
        }
    }
}
