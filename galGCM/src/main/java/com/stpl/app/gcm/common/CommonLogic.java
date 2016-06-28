

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.common;

import com.stpl.app.gcm.bpm.logic.DSCalculationLogic;
import com.stpl.app.gcm.common.dao.CommonDao;
import com.stpl.app.gcm.common.dao.impl.CommonImpl;
import com.stpl.app.gcm.discount.dao.ContractDetailsDAO;
import com.stpl.app.gcm.discount.dao.DiscountDAO;
import com.stpl.app.gcm.discount.dao.impl.ContractDetailsDaoImpl;
import com.stpl.app.gcm.discount.dao.impl.DiscountDaoImpl;
import com.stpl.app.gcm.discount.dto.ContractsDetailsDto;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dao.TradingPartnerDAO;
import com.stpl.app.gcm.tp.dao.impl.TradingPartnerDAOImpl;
import com.stpl.app.gcm.tp.logic.LoadTabLogic;
import com.stpl.app.gcm.transfercontract.util.CommonUtil;
import com.stpl.app.gcm.transfercontract.util.Constant;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.Constants.IndicatorConstants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.TRADING_PARTNER_REMOVE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.TRADING_PARTNER_UPDATE;
import com.stpl.app.gcm.util.Converters;
import com.stpl.app.model.CfpContract;
import com.stpl.app.model.CfpModel;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.IfpContract;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.PsContract;
import com.stpl.app.model.PsModel;
import com.stpl.app.model.RsContract;
import com.stpl.app.model.RsModel;
import com.stpl.app.service.CfpModelLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.PsModelLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.ifs.util.HelperDTO;
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
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.jboss.logging.Logger;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.model.TaskSummary;

/**
 *
 * @author santanukumar
 */
public class CommonLogic {

    /**
     * INSTANTIATE ContractDashboardLogicDAO Implementation logic.
     */
    static CommonDao DAO = CommonImpl.getInstance();
    private final ContractDetailsDAO dao = new ContractDetailsDaoImpl();
    private static final DiscountDAO discountDAO = new DiscountDaoImpl();
    private static final Logger LOGGER = Logger.getLogger(CommonLogic.class);
    private int newProjectionId;
    private String forecastingType = StringUtils.EMPTY;
    private int prodRelationshipId;

    public int getProdRelationshipId() {
        return prodRelationshipId;
    }

    public void setProdRelationshipId(int prodRelationshipId) {
        this.prodRelationshipId = prodRelationshipId;
    }

    public int getNewProjectionId() {
        return newProjectionId;
    }

    public void setNewProjectionId(int newProjectionId) {
        this.newProjectionId = newProjectionId;
    }

    public String getForecastingType() {
        return forecastingType;
    }

    public void setForecastingType(String forecastingType) {
        this.forecastingType = forecastingType;
    }

    public ExtTreeContainer<ContractsDetailsDto> getLevel1Hierarchy(final String contractId, final ExtTreeContainer<ContractsDetailsDto> container, final List<ContractsDetailsDto> cfpList) throws SystemException {
        LOGGER.info("Entering getLevel1Hierarchy method");
        final List<ContractsDetailsDto> contractList = getContractList(contractId, ContractsDetailsDto.LEVEL1, cfpList);
        container.removeAllItems();
        for (final Iterator<ContractsDetailsDto> iterator = contractList.iterator(); iterator.hasNext();) {
            final ContractsDetailsDto contractMember = (ContractsDetailsDto) iterator.next();
            container.addBean(contractMember);
            if (!IndicatorConstants.RS_VALUE.getConstant().equals(contractMember.getCategory()) && isLevel2ListAvlbl(contractMember.getSystemId(), contractMember.getCategory())) {
                container.setChildrenAllowed(contractMember, true);
            } else {
                container.setChildrenAllowed(contractMember, false);
            }
        }
        LOGGER.info("End of getLevel1Hierarchy method");
        return container;
    }

    /**
     * Gets the contract list.
     *
     * @param contractId the contract id
     * @param level the level
     * @return the contract list
     */
    private List<ContractsDetailsDto> getContractList(final String contractId, final int level, final List<ContractsDetailsDto> cfpList) throws SystemException {
        LOGGER.info("Entering getContractList method");

        String contract;
        if (contractId.trim().equals(StringUtils.EMPTY)) {
            contract = String.valueOf(IndicatorConstants.CHAR_PERCENT);
        } else {
            contract = contractId.replace(IndicatorConstants.CHAR_ASTERISK.getConstant(), IndicatorConstants.CHAR_PERCENT.getConstant());
        }
        final List<ContractsDetailsDto> contractList = new ArrayList<ContractsDetailsDto>();
        // TODO change the limits in the query
        final List<ContractMaster> contractML = dao.contractMasterDynamicQuery(getProcessedQuery(contract));

        ContractsDetailsDto contractDetails;
        ContractMaster contractMaster;
        for (final Iterator<ContractMaster> iterator = contractML.iterator(); iterator.hasNext();) {
            contractMaster = (ContractMaster) iterator.next();
            contractDetails = new ContractsDetailsDto();
            contractDetails.setSystemId(contractMaster.getContractMasterSid());
            contractDetails.setName(contractMaster.getContractName());
            contractDetails.setContractName(contractMaster.getContractName());
            contractDetails.setId(contractMaster.getContractId());
            contractDetails.setNumber(contractMaster.getContractNo());
            contractDetails.setCategory(IndicatorConstants.CONTRACT.getConstant());
            contractDetails.setLevel(level);
            contractDetails.setInternalId(contractMaster.getContractMasterSid());
            contractList.add(contractDetails);
        }
        if(cfpList != null) {
            contractList.addAll(cfpList);
        }
        LOGGER.info("End of getContractList method");
        return contractList;
    }

    /**
     * Method used for getProcessedQuery.
     *
     * @param contractId the contract id
     * @param start
     * @param end
     * @return the processed query
     */
    public DynamicQuery getProcessedQuery(final String contractId) {
        LOGGER.info("Entering getProcessedQuery method");

        final DynamicQuery contractQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class);
        String contract;
        if (contractId.trim().equals(StringUtils.EMPTY)) {
            contract = IndicatorConstants.CHAR_PERCENT.getConstant();
        } else {
            contract = contractId.replace(IndicatorConstants.CHAR_ASTERISK.getConstant(), IndicatorConstants.CHAR_PERCENT.getConstant());
        }
        contractQuery.add(RestrictionsFactoryUtil.eq("processStatus", true));
        contractQuery.add(RestrictionsFactoryUtil.like(Constants.CONTRACT_NO, contract));
        contractQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like("inboundStatus", "D")));
        //contractQuery.setLimit(start, end);
        LOGGER.info("End of getProcessedQuery method");
        return contractQuery;
    }

    /**
     * Checks if is level2 list avlbl.
     *
     * @param contractSystemId the contract system id
     * @return true, if checks if is level2 list avlbl
     */
    private boolean isLevel2ListAvlbl(final int contractSystemId, final String category) throws SystemException {
        LOGGER.info("Entering isLevel2ListAvlbl method");
        boolean available;
        if (!IndicatorConstants.CFP.getConstant().equals(category) && getCFPQueriedCount(contractSystemId) > Constants.ZERO) {
            available = true;
        } else if (!IndicatorConstants.IFP.getConstant().equals(category) && getIFPQueriedCount(contractSystemId) > Constants.ZERO) {
            available = true;
        } else if (!IndicatorConstants.PS_VALUE.getConstant().equals(category) && getPSQueriedCount(contractSystemId) > Constants.ZERO) {
            available = true;
        } else if (!IndicatorConstants.RS_VALUE.getConstant().equals(category) && getRSQueriedCount(contractSystemId) > Constants.ZERO) {
            available = true;
        } else {
            available = false;
        }

        LOGGER.info("End of isLevel2ListAvlbl method");
        return available;
    }

    public int getCFPQueriedCount(final int contractSystemId) throws SystemException {
        LOGGER.info("Entering getCFPQueriedCount method");

        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CfpContract.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(IndicatorConstants.CONTRACT_MASTER_SID.getConstant(), contractSystemId));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        LOGGER.info("End of getCFPQueriedCount method");
        return (int) dao.contractMasterDynamicQueryCount(cfpDynamicQuery);
    }

    public int getIFPQueriedCount(final int contractSystemId) throws SystemException {
        LOGGER.info("Entering getIFPQueriedCount method");

        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContract.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(IndicatorConstants.CONTRACT_MASTER_SID.getConstant(), contractSystemId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        LOGGER.info("End of getIFPQueriedCount method");
        return (int) dao.contractMasterDynamicQueryCount(ifpDynamicQuery);
    }

    /**
     * Gets the ps queried count.
     *
     * @param contractSystemId the contract system id
     * @return the PS queried count
     */
    public int getPSQueriedCount(final int contractSystemId) throws SystemException {
        LOGGER.info("Entering getPSQueriedCount method");

        final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsContract.class);
        psDynamicQuery.add(RestrictionsFactoryUtil.eq(IndicatorConstants.CONTRACT_MASTER_SID.getConstant(), contractSystemId));
        psDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        LOGGER.info("End of getPSQueriedCount method");
        return (int) dao.contractMasterDynamicQueryCount(psDynamicQuery);
    }

    public int getRSQueriedCount(final int contractSystemId) throws SystemException {
        LOGGER.info("Entering getRSQueriedCount method");

        final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsContract.class);
        rsDynamicQuery.add(RestrictionsFactoryUtil.eq(IndicatorConstants.CONTRACT_MASTER_SID.getConstant(), contractSystemId));
        rsDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        LOGGER.info("End of getRSQueriedCount method");
        return (int) dao.contractMasterDynamicQueryCount(rsDynamicQuery);
    }

    public ExtTreeContainer<ContractsDetailsDto> getLevel2Hierarchy(final ContractsDetailsDto parent, final ExtTreeContainer<ContractsDetailsDto> container, final List<ContractsDetailsDto> cfpList, final List<ContractsDetailsDto> ifpList, final List<ContractsDetailsDto> psList, final List<ContractsDetailsDto> rsList) throws SystemException, PortalException {
        LOGGER.info("Entering getLevel2Hierarchy method and CFP list size is ============");
        container.removeAllItems();
        container.addBean(parent);
        container.setChildrenAllowed(parent, !IndicatorConstants.RS_VALUE.getConstant().equals(parent.getCategory()));
        ContractsDetailsDto contractMember;
        final List<ContractsDetailsDto> contractList = getLevel2List(parent, cfpList, ifpList, psList, rsList);
        for (final Iterator<ContractsDetailsDto> iterator = contractList.iterator(); iterator.hasNext();) {
            contractMember = iterator.next();
            container.addBean(contractMember);
            if (!IndicatorConstants.RS_VALUE.getConstant().equals(contractMember.getCategory()) && isLevel3ListAvlbl(contractMember.getSystemId(), contractMember.getCategory())) {
                container.setChildrenAllowed(contractMember, true);
            } else {
                container.setChildrenAllowed(contractMember, false);
            }
            container.setParent(contractMember, parent);
        }
        LOGGER.info("End of getLevel2Hierarchy method");
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
    public ExtTreeContainer<ContractsDetailsDto> getLevel3Hierarchy(final ContractsDetailsDto parent2, final ExtTreeContainer<ContractsDetailsDto> container, final List<ContractsDetailsDto> ifpList, final List<ContractsDetailsDto> psList, final List<ContractsDetailsDto> rsList) throws SystemException, PortalException {
        LOGGER.info("Entering getLevel3Hierarchy method");
        container.removeAllItems();

        container.addBean(parent2.getParent1());

        container.setChildrenAllowed(parent2.getParent1(), true);
        container.addBean(parent2);
        container.setChildrenAllowed(parent2, true);
        container.setParent(parent2, parent2.getParent1());

        final List<ContractsDetailsDto> contractML = getLevel3List(parent2.getParent1(), parent2, ifpList, psList, rsList);

        ContractsDetailsDto contractMember;
        for (final Iterator<ContractsDetailsDto> iterator = contractML.iterator(); iterator.hasNext();) {
            contractMember = iterator.next();

            container.addBean(contractMember);

            if (!IndicatorConstants.RS_VALUE.getConstant().equals(contractMember.getCategory()) && isLevel4ListAvlbl(contractMember.getSystemId(), contractMember.getCategory())) {
                container.setChildrenAllowed(contractMember, true);
            } else {
                container.setChildrenAllowed(contractMember, false);
            }

            container.setParent(contractMember, parent2);

        }
        LOGGER.info("End of getLevel3Hierarchy method");
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
    public ExtTreeContainer<ContractsDetailsDto> getLevel4Hierarchy(final ContractsDetailsDto parent3, final ExtTreeContainer<ContractsDetailsDto> container, final List<ContractsDetailsDto> psList, final List<ContractsDetailsDto> rsList) throws SystemException, PortalException {
        LOGGER.info("Entering getLevel4Hierarchy method");

//        container.removeAllItems();
//        container.addBean(parent3.getParent1());
//        container.setChildrenAllowed(parent3.getParent1(), true);
//        container.addBean(parent3.getParent2());
//        container.setChildrenAllowed(parent3.getParent2(), true);
//        container.setParent(parent3.getParent2(), parent3.getParent1());
//        container.addBean(parent3);
//        container.setChildrenAllowed(parent3, true);
        container.setParent(parent3, parent3.getParent2());

        final List<ContractsDetailsDto> contractList = getLevel4List(parent3.getParent1(), parent3.getParent2(), parent3, psList, rsList);
        ContractsDetailsDto contractMember;
        for (final Iterator<ContractsDetailsDto> iterator = contractList.iterator(); iterator.hasNext();) {
            contractMember = iterator.next();

            container.addBean(contractMember);

            if (!IndicatorConstants.RS_VALUE.getConstant().equals(contractMember.getCategory()) && isLevel4ListAvlbl(contractMember.getSystemId(), contractMember.getCategory())) {
                container.setChildrenAllowed(contractMember, true);
            } else {
                container.setChildrenAllowed(contractMember, false);
            }

            container.setParent(contractMember, parent3);

        }
        LOGGER.info("End of getLevel4Hierarchy method");
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
    public ExtTreeContainer<ContractsDetailsDto> getLevel5Hierarchy(final ContractsDetailsDto parent4, final ExtTreeContainer<ContractsDetailsDto> container, final List<ContractsDetailsDto> rsList) throws SystemException, PortalException {
        LOGGER.info("Entering getLevel5Hierarchy method");

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

        if (!IndicatorConstants.RS_VALUE.getConstant().equals(parent4.getCategory()) && isLevel5ListAvlbl(parent4.getSystemId())) {
            final List<ContractsDetailsDto> contractList = getLevel5List(parent4.getParent1(), parent4.getParent2(), parent4.getParent3(), parent4, rsList);
            ContractsDetailsDto contractMember;
            for (final Iterator<ContractsDetailsDto> iterator = contractList.iterator(); iterator.hasNext();) {
                contractMember = iterator.next();
                container.addBean(contractMember);

                container.setChildrenAllowed(contractMember, false);

                container.setParent(contractMember, parent4);

            }
        }
        LOGGER.info("End of getLevel5Hierarchy method");
        return container;
    }

    public List<ContractsDetailsDto> getLevel2List(final ContractsDetailsDto parent1, List<ContractsDetailsDto> cfpList, final List<ContractsDetailsDto> ifpList, final List<ContractsDetailsDto> psList, final List<ContractsDetailsDto> rsList) throws SystemException, PortalException {

        LOGGER.info("Entering getLevel2List method");
        List<ContractsDetailsDto> level2List;
        if (getCFPQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level2List = getCFPList(parent1, ContractsDetailsDto.LEVEL2, cfpList);
        } else if (getIFPQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level2List = getIFPList(parent1, null, ContractsDetailsDto.LEVEL2, ifpList);
        } else if (getPSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level2List = getPSList(parent1, null, null, ContractsDetailsDto.LEVEL2, psList);
        } else if (getRSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level2List = getRSList(parent1, null, null, null, ContractsDetailsDto.LEVEL2, rsList);
        } else {
            level2List = new ArrayList<ContractsDetailsDto>();
        }
        LOGGER.info("End of getLevel2List method");
        return level2List;
    }

    /**
     * Gets the level3 list.
     *
     * @param parent1 the parent1
     * @param parent2 the parent2
     * @return the level3 list
     */
    public List<ContractsDetailsDto> getLevel3List(final ContractsDetailsDto parent1, final ContractsDetailsDto parent2, final List<ContractsDetailsDto> ifpList, final List<ContractsDetailsDto> psList, final List<ContractsDetailsDto> rsList) throws SystemException, PortalException {

        LOGGER.info("Entering getLevel3List method");
        List<ContractsDetailsDto> level3List;
        if (!IndicatorConstants.IFP.getConstant().equals(parent2.getCategory()) && getIFPQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level3List = getIFPList(parent1, parent2, ContractsDetailsDto.LEVEL3, ifpList);
        } else if (!IndicatorConstants.PS_VALUE.getConstant().equals(parent2.getCategory()) && getPSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level3List = getPSList(parent1, parent2, null, ContractsDetailsDto.LEVEL3, psList);
        } else if (!IndicatorConstants.RS_VALUE.getConstant().equals(parent2.getCategory()) && getRSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level3List = getRSList(parent1, parent2, null, null, ContractsDetailsDto.LEVEL3, rsList);
        } else {
            level3List = new ArrayList<ContractsDetailsDto>();
        }

        LOGGER.info("End of getLevel3List method");
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
    public List<ContractsDetailsDto> getLevel4List(final ContractsDetailsDto parent1, final ContractsDetailsDto parent2, final ContractsDetailsDto parent3, final List<ContractsDetailsDto> psList, final List<ContractsDetailsDto> rsList) throws SystemException, PortalException {
        LOGGER.info("Entering getLevel4List method");

        List<ContractsDetailsDto> level4List;
        if (!IndicatorConstants.PS_VALUE.getConstant().equals(parent3.getCategory()) && getPSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level4List = getPSList(parent1, parent2, parent3, ContractsDetailsDto.LEVEL4, psList);
        } else if (!IndicatorConstants.RS_VALUE.getConstant().equals(parent3.getCategory()) && getRSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level4List = getRSList(parent1, parent2, parent3, null, ContractsDetailsDto.LEVEL4, rsList);
        } else {
            level4List = new ArrayList<ContractsDetailsDto>();
        }

        LOGGER.info("End of getLevel4List method");
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
    public List<ContractsDetailsDto> getLevel5List(final ContractsDetailsDto parent1, final ContractsDetailsDto parent2, final ContractsDetailsDto parent3, final ContractsDetailsDto parent4, final List<ContractsDetailsDto> rsList) throws SystemException, PortalException {

        LOGGER.info("Entering getLevel5List method");

        List<ContractsDetailsDto> level5List;
        if (!IndicatorConstants.RS_VALUE.getConstant().equals(parent3.getCategory()) && getRSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level5List = getRSList(parent1, parent2, parent3, parent4, ContractsDetailsDto.LEVEL5, rsList);
        } else {
            level5List = new ArrayList<ContractsDetailsDto>();
        }

        LOGGER.info("End of getLevel5List method");
        return level5List;
    }

    /**
     * Checks if is level3 list avlbl.
     *
     * @param contractSystemId the contract system id
     * @return true, if checks if is level3 list avlbl
     */
    public boolean isLevel3ListAvlbl(final int contractSystemId, final String category) throws SystemException {

        LOGGER.info("Entering isLevel3ListAvlbl method");
        boolean available;
        if (!IndicatorConstants.IFP.getConstant().equals(category) && getIFPQueriedCount(contractSystemId) > Constants.ZERO) {
            available = true;
        } else if (!IndicatorConstants.PS_VALUE.getConstant().equals(category) && getPSQueriedCount(contractSystemId) > Constants.ZERO) {
            available = true;
        } else if (!IndicatorConstants.RS_VALUE.getConstant().equals(category) && getRSQueriedCount(contractSystemId) > Constants.ZERO) {
            available = true;
        } else if (IndicatorConstants.CFP.getConstant().equals(category)) {
            available = true;
        } else {
            available = false;
        }
        LOGGER.info("End of isLevel3ListAvlbl method");
        return available;
    }

    public boolean isLevel4ListAvlbl(final int contractSystemId, final String category) throws SystemException {

        LOGGER.info("Entering isLevel4ListAvlbl method");
        boolean available;
        available = (!IndicatorConstants.PS_VALUE.getConstant().equals(category) && getPSQueriedCount(contractSystemId) > Constants.ZERO) || (!IndicatorConstants.RS_VALUE.getConstant().equals(category) && getRSQueriedCount(contractSystemId) > Constants.ZERO);

        LOGGER.info("End of isLevel4ListAvlbl method");
        return available;
    }

    /**
     * Checks if is level5 list avlbl.
     *
     * @param contractSystemId the contract system id
     * @return true, if checks if is level5 list avlbl
     */
    private boolean isLevel5ListAvlbl(final int contractSystemId) throws SystemException {
        LOGGER.info("Entering isLevel5ListAvlbl method");
        if (getRSQueriedCount(contractSystemId) > Constants.ZERO) {
            return true;
        }
        LOGGER.info("End of isLevel5ListAvlbl method");
        return false;
    }

    private List<ContractsDetailsDto> getCFPList(final ContractsDetailsDto parent1, final int level, List<ContractsDetailsDto> cfList) throws SystemException, PortalException {
        LOGGER.info("Entering getCFPList method");

        final List<ContractsDetailsDto> cfpList = new ArrayList<ContractsDetailsDto>();
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CfpContract.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(IndicatorConstants.CONTRACT_MASTER_SID.getConstant(), parent1.getSystemId()));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        final List<CfpContract> cfpMasterList = dao.cfpMasterDynamicQuery(cfpDynamicQuery);

        ContractsDetailsDto contractMember;
        CfpContract cfpMaster;
        for (final Iterator<CfpContract> iterator = cfpMasterList.iterator(); iterator.hasNext();) {
            cfpMaster = (CfpContract) iterator.next();
            contractMember = new ContractsDetailsDto();
            contractMember.setSystemId(cfpMaster.getContractMasterSid());
            contractMember.setName(cfpMaster.getCfpName());

            CfpModel cfpModel = CfpModelLocalServiceUtil.getCfpModel(cfpMaster.getCfpModelSid());

            /*contractMember.setContractId(getValuesFromModel(cfpModel.getCfpModelSid(),true));
             contractMember.setContractNo(getValuesFromModel(cfpMaster.getCfpModelSid(),false));
             */
            contractMember.setId(cfpModel.getCfpId());
            contractMember.setNumber(cfpModel.getCfpNo());
            contractMember.setModelSysId(cfpModel.getCfpModelSid());
            contractMember.setCategory(IndicatorConstants.CFP.getConstant());
            contractMember.setInternalId(cfpMaster.getCfpContractSid());
            contractMember.setLevel(level);
            contractMember.setParent1(parent1);
            contractMember.setCfpContractId(cfpMaster.getCfpContractSid());
            cfpList.add(contractMember);
        }
        if(cfList != null) {
        cfpList.addAll(cfList);
        }
        LOGGER.info("End of getCFPList method");
        return cfpList;
    }

    private List<ContractsDetailsDto> getIFPList(final ContractsDetailsDto parent1, final ContractsDetailsDto parent2, final int level, final List<ContractsDetailsDto> ifList) throws SystemException, PortalException {
        LOGGER.info("Entering getIFPList method");

        final List<ContractsDetailsDto> ifpList = new ArrayList<ContractsDetailsDto>();
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContract.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(IndicatorConstants.CONTRACT_MASTER_SID.getConstant(), parent1.getSystemId()));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
      if (parent2 != null) {
            String category=parent2.getCategory().trim();
            if(category.equals("CFP")){
                if (parent2.getInternalId() == 0) {
                    ifpDynamicQuery.add(RestrictionsFactoryUtil.isNull("cfpContractSid"));
                } else {
                    ifpDynamicQuery.add(RestrictionsFactoryUtil.eq("cfpContractSid", String.valueOf(parent2.getInternalId())));
                }

            }
        }
       // ifpDynamicQuery.setLimit(start, end);
        final List<IfpContract> ifpMasterList = dao.ifpMasterDynamicQuery(ifpDynamicQuery);
        ContractsDetailsDto contractMember;
        IfpContract ifpContract;
        for (final Iterator<IfpContract> iterator = ifpMasterList.iterator(); iterator.hasNext();) {
            ifpContract = (IfpContract) iterator.next();
            contractMember = new ContractsDetailsDto();
            contractMember.setSystemId(ifpContract.getContractMasterSid());
            contractMember.setContractName(ifpContract.getIfpName());
            IfpModel ifpModel = IfpModelLocalServiceUtil.getIfpModel(ifpContract.getIfpModelSid());
            contractMember.setId(ifpModel.getIfpId());
            contractMember.setNumber(ifpModel.getIfpNo());
            contractMember.setModelSysId(ifpModel.getIfpModelSid());
            contractMember.setCategory(IndicatorConstants.IFP.getConstant());
            contractMember.setLevel(level);
            contractMember.setParent1(parent1);
            contractMember.setParent2(parent2);
            contractMember.setInternalId(ifpContract.getIfpContractSid());
            contractMember.setIfpContractId(ifpContract.getIfpContractSid());
            ifpList.add(contractMember);
        }
        if (ifList != null) {
            for (ContractsDetailsDto dto : ifList) {
                if(dto != null && parent2 != null && dto.getRelation().containsKey(parent2.getId() + parent2.getName() + parent2.getNumber())) {
                    ifpList.addAll(dto.getRelation().get(parent2.getId() + parent2.getName() + parent2.getNumber()));
                }
                if(dto != null && parent1 != null && dto.getRelation().containsKey(parent1.getId() + parent1.getName() + parent1.getNumber())) {
                    ifpList.addAll(dto.getRelation().get(parent1.getId() + parent1.getName() + parent1.getNumber()));
                }
            }
        }
        LOGGER.info("End of getIFPList method");
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
    private List<ContractsDetailsDto> getPSList(final ContractsDetailsDto parent1, final ContractsDetailsDto parent2, final ContractsDetailsDto parent3, final int level, final List<ContractsDetailsDto> pList) throws SystemException, PortalException {
        LOGGER.info("Entering getPSList method");

        final List<ContractsDetailsDto> psList = new ArrayList<ContractsDetailsDto>();
        final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsContract.class);
        psDynamicQuery.add(RestrictionsFactoryUtil.eq(IndicatorConstants.CONTRACT_MASTER_SID.getConstant(), parent1.getSystemId()));
        psDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        if (parent2 != null) {

            if (parent2.getCategory().equals(IndicatorConstants.CFP.getConstant())) {
                if (parent2.getInternalId() == 0) {
                    psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
                } else {
                    psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CFP_CONTRACT_SID, String.valueOf(parent2.getInternalId())));
                }
            }
            if (parent2.getCategory().equals(IndicatorConstants.IFP.getConstant())) {

                if (parent2.getInternalId() == 0) {
                    psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
                } else {
                    psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IFP_CONTRACT_SID, String.valueOf(parent2.getInternalId())));
                }

            }
        }
        if (parent3 != null) {

            if (parent3.getCategory().equals(IndicatorConstants.CFP.getConstant())) {
                if (parent3.getInternalId() == 0) {
                    psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
                } else {
                    psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CFP_CONTRACT_SID, String.valueOf(parent3.getInternalId())));
                }

            }
            if (parent3.getCategory().equals(IndicatorConstants.IFP.getConstant())) {
                if (parent3.getInternalId() == 0) {
                    psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
                } else {
                    psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IFP_CONTRACT_SID, String.valueOf(parent3.getInternalId())));
                }

            }
        }

     //   psDynamicQuery.setLimit(start, end);
        final List<PsContract> psMasterList = dao.psMasterDynamicQuery(psDynamicQuery);

        ContractsDetailsDto contractMember;

        for (final Iterator<PsContract> iterator = psMasterList.iterator(); iterator.hasNext();) {
            final PsContract psMaster = (PsContract) iterator.next();
            contractMember = new ContractsDetailsDto();
            contractMember.setSystemId(psMaster.getContractMasterSid());
            contractMember.setContractName(psMaster.getPsName());
            PsModel psModel = PsModelLocalServiceUtil.getPsModel(psMaster.getPsModelSid());
            contractMember.setId(psModel.getPsId());
            contractMember.setNumber(psModel.getPsNo());
            contractMember.setModelSysId(psModel.getPsModelSid());
            contractMember.setCategory(IndicatorConstants.PS_VALUE.getConstant());
            contractMember.setLevel(level);
            contractMember.setParent1(parent1);
            contractMember.setParent2(parent2);
            contractMember.setParent3(parent3);
            contractMember.setInternalId(psMaster.getPsContractSid());
            contractMember.setPsContractId(psMaster.getPsContractSid());
            psList.add(contractMember);
        }
        if(pList!= null) {
            for (ContractsDetailsDto dto : pList) {
                if(dto != null && parent3 != null && dto.getRelation().containsKey(parent3.getId() + parent3.getName() + parent3.getNumber())) {
                    psList.addAll(dto.getRelation().get(parent3.getId() + parent3.getName() + parent3.getNumber()));
                }
                if(dto != null && parent2 != null && dto.getRelation().containsKey(parent2.getId() + parent2.getName() + parent2.getNumber())) {
                    psList.addAll(dto.getRelation().get(parent2.getId() + parent2.getName() + parent2.getNumber()));
                }
                if(dto != null && parent1 != null && dto.getRelation().containsKey(parent1.getId() + parent1.getName() + parent1.getNumber())) {
                    psList.addAll(dto.getRelation().get(parent1.getId() + parent1.getName() + parent1.getNumber()));
                }
            }
        }
        LOGGER.info("End of getPSList method");
        return psList;
    }

    private List<ContractsDetailsDto> getRSList(final ContractsDetailsDto parent1, final ContractsDetailsDto parent2, final ContractsDetailsDto parent3, final ContractsDetailsDto parent4, final int level, final List<ContractsDetailsDto> rList)
            throws SystemException, PortalException {
        LOGGER.info("Entering getRSList method");

        final List<ContractsDetailsDto> rsList = new ArrayList<ContractsDetailsDto>();
        final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsContract.class);
        rsDynamicQuery.add(RestrictionsFactoryUtil.eq(IndicatorConstants.CONTRACT_MASTER_SID.getConstant(), parent1.getSystemId()));
        rsDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
      //  rsDynamicQuery.add(RestrictionsFactoryUtil.in(IndicatorConstants.RSCONTRACTSID.getConstant(), parent1.getRebateList()));
        if (parent2 != null) {

            if (parent2.getCategory().equals(IndicatorConstants.CFP.getConstant())) {
                if (parent2.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(IndicatorConstants.CFP_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(IndicatorConstants.CFP_CONTRACT_SID.getConstant(), String.valueOf(parent2.getInternalId())));
                }
            }
            if (parent2.getCategory().equals(IndicatorConstants.IFP.getConstant())) {

                if (parent2.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(IndicatorConstants.IFP_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(IndicatorConstants.IFP_CONTRACT_SID.getConstant(), String.valueOf(parent2.getInternalId())));
                }

            }
            if (parent2.getCategory().equals(IndicatorConstants.PS_VALUE.getConstant())) {

                if (parent2.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(IndicatorConstants.PS_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(IndicatorConstants.PS_CONTRACT_SID.getConstant(), String.valueOf(parent2.getInternalId())));
                }

            }
        }
        if (parent3 != null) {

            if (parent3.getCategory().equals(IndicatorConstants.CFP.getConstant())) {
                if (parent3.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(IndicatorConstants.CFP_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(IndicatorConstants.CFP_CONTRACT_SID.getConstant(), String.valueOf(parent3.getInternalId())));
                }

            }
            if (parent3.getCategory().equals(IndicatorConstants.IFP.getConstant())) {
                if (parent3.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(IndicatorConstants.IFP_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(IndicatorConstants.IFP_CONTRACT_SID.getConstant(), String.valueOf(parent3.getInternalId())));
                }

            }
            if (parent3.getCategory().equals(IndicatorConstants.PS_VALUE.getConstant())) {

                if (parent3.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(IndicatorConstants.PS_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(IndicatorConstants.PS_CONTRACT_SID.getConstant(), String.valueOf(parent3.getInternalId())));
                }

            }
        }
        if (parent4 != null) {

            if (parent4.getCategory().equals(IndicatorConstants.CFP.getConstant())) {
                if (parent4.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(IndicatorConstants.CFP_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(IndicatorConstants.CFP_CONTRACT_SID.getConstant(), String.valueOf(parent4.getInternalId())));
                }

            }
            if (parent4.getCategory().equals(IndicatorConstants.IFP.getConstant())) {
                if (parent4.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(IndicatorConstants.IFP_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(IndicatorConstants.IFP_CONTRACT_SID.getConstant(), String.valueOf(parent4.getInternalId())));
                }

            }
            if (parent4.getCategory().equals(IndicatorConstants.PS_VALUE.getConstant())) {

                if (parent4.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(IndicatorConstants.PS_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(IndicatorConstants.PS_CONTRACT_SID.getConstant(), String.valueOf(parent4.getInternalId())));
                }

            }
        }

       // rsDynamicQuery.setLimit(start, end);
        final List<RsContract> rsMasterList = dao.rsMasterDynamicQuery(rsDynamicQuery);

        ContractsDetailsDto contractMember;
        RsContract rsMaster;
        for (final Iterator<RsContract> iterator = rsMasterList.iterator(); iterator.hasNext();) {
            rsMaster = (RsContract) iterator.next();
            contractMember = new ContractsDetailsDto();
            contractMember.setSystemId(rsMaster.getContractMasterSid());
            contractMember.setName(rsMaster.getRsName());
            RsModel rsModel = RsModelLocalServiceUtil.getRsModel(rsMaster.getRsModelSid());
            contractMember.setId(rsModel.getRsId());
            contractMember.setNumber(rsModel.getRsNo());
            contractMember.setRsName(rsModel.getRsName());
            contractMember.setModelSysId(rsModel.getRsModelSid());
            contractMember.setCategory(IndicatorConstants.RS_VALUE.getConstant());
            contractMember.setLevel(level);
            contractMember.setParent1(parent1);
            contractMember.setParent2(parent2);
            contractMember.setParent3(parent3);
            contractMember.setParent4(parent4);
            contractMember.setInternalId(rsMaster.getRsContractSid());
            contractMember.setRsSystemId(String.valueOf(rsMaster.getRsContractSid()));
            rsList.add(contractMember);
        }
        if(rList != null) {
            for (ContractsDetailsDto dto : rList) {
                if(dto != null && parent4 != null && dto.getRelation().containsKey(parent4.getId() + parent4.getName() + parent4.getNumber())) {
                    rsList.addAll(dto.getRelation().get(parent4.getId() + parent4.getName() + parent4.getNumber()));
                }
                if(dto != null && parent3 != null && dto.getRelation().containsKey(parent3.getId() + parent3.getName() + parent3.getNumber())) {
                    rsList.addAll(dto.getRelation().get(parent3.getId() + parent3.getName() + parent3.getNumber()));
                }
                if(dto != null && parent2 != null && dto.getRelation().containsKey(parent2.getId() + parent2.getName() + parent2.getNumber())) {
                    rsList.addAll(dto.getRelation().get(parent2.getId() + parent2.getName() + parent2.getNumber()));
                }
                if(dto != null && parent1 != null && dto.getRelation().containsKey(parent1.getId() + parent1.getName() + parent1.getNumber())) {
                    rsList.addAll(dto.getRelation().get(parent1.getId() + parent1.getName() + parent1.getNumber()));
                }
            }
        }
        LOGGER.info("End of getRSList method");
        return rsList;
    }

    public static String getDescriptionFromID(final int systemId) throws Exception {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq("helperTableSid", systemId));
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("description"));
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        List resultList = discountDAO.getHelperTableListNames(dynamicQuery);
        return Converters.convertNullToEmpty(resultList.get(0));
    }

    public static ComboBox loadComponentType(ComboBox componentTypeDdlb, String componenttype, boolean flag) {
        if (flag) {
            componentTypeDdlb.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            componentTypeDdlb.setNullSelectionAllowed(true);
            componentTypeDdlb.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            componentTypeDdlb.addItem(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN);
            componentTypeDdlb.addItem(Constants.IndicatorConstants.ITEM_FAMILY_PLAN);
            componentTypeDdlb.addItem(Constants.IndicatorConstants.PRICE_SCHEDULE);
            componentTypeDdlb.addItem(Constants.IndicatorConstants.REBATE_SCHEDULE);
            componentTypeDdlb.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            componentTypeDdlb.setImmediate(true);
        } else {
            componentTypeDdlb.removeAllItems();
            componentTypeDdlb.addItem(componenttype);
            componentTypeDdlb.setValue(componenttype);
        }
        return componentTypeDdlb;
    }

    public static ComboBox loadSearchDdlb(ComboBox searchDdlb) {
        searchDdlb.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        searchDdlb.setNullSelectionAllowed(true);
        searchDdlb.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        searchDdlb.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        searchDdlb.setImmediate(true);
        return searchDdlb;
    }

    public static List<String> getLatestApprovedProjection() {
        final List<Object[]> list = ItemQueries.getItemData(new ArrayList(), "getLatestApproved", null);
        final List<String> resultList = new ArrayList<String>();
        String projectionId = StringUtils.EMPTY;
        String forecastingType = StringUtils.EMPTY;
        for (Object[] obj : list) {
            projectionId = obj[0] == null ? Constants.ZEROSTRING : String.valueOf(obj[0]);
            forecastingType = obj[1] == null ? StringUtils.EMPTY : String.valueOf(obj[1]);
        }
        resultList.add(projectionId);
        resultList.add(forecastingType);
        return resultList;
    }

    public static List<String> getApprovedProjectionResults(final String forecastingType, final boolean salesFlag) {
        final List<String> list = new ArrayList<String>();
        if (forecastingType.equals(Constants.IndicatorConstants.NON_MANDATED.getConstant())) {
            if (salesFlag) {
                list.add("NM_SALES_PROJECTION");
                list.add("PROJECTION_SALES");
                list.add("PROJECTION_UNITS");
            } else {
                list.add("NM_DISCOUNT_PROJECTION");
                list.add("PROJECTION_SALES");
                list.add("PROJECTION_RATE");
            }
            return list;
        } else if (forecastingType.equals(Constants.IndicatorConstants.MANDATED.getConstant())) {
            if (salesFlag) {
                list.add("M_SALES_PROJECTION");
                list.add("PROJECTION_SALES");
                list.add("PROJECTION_UNITS");
            } else {
                list.add("M_SUPPLEMENTAL_DISC_PROJ");
                list.add("PROJECTION_SALES");
                list.add("PROJECTION_RATE");
            }
            return list;
        } else if (forecastingType.equals("Channel")) {
            if (salesFlag) {
                list.add("CH_SALES_PROJECTION");
                list.add("CONTRACT_SALES");
                list.add("CONTRACT_UNITS");
            } else {
                list.add("CH_PROJECTION_DISCOUNT");
                list.add("DISCOUNT_AMOUNT");
                list.add("DISCOUNT_RATE");
            }
            return list;
        }
        return list;
    }

    public static ComboBox loadExistingTabSearchField(ComboBox searchDdlb, ComboBox componentType) {
        String compType = String.valueOf(componentType.getValue());
        if (compType.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
            searchDdlb.removeAllItems();
            searchDdlb = loadValues(searchDdlb, Constants.CFP);
        } else if (compType.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
            searchDdlb.removeAllItems();
            searchDdlb = loadValues(searchDdlb, Constants.IFP);
        } else if (compType.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
            searchDdlb.removeAllItems();
            searchDdlb = loadValues(searchDdlb, Constants.PS);
        } else if (compType.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
            searchDdlb.removeAllItems();
            searchDdlb = loadValues(searchDdlb, Constants.RS);
        } else {
            searchDdlb.removeAllItems();
        }
        return searchDdlb;
    }

    private static ComboBox loadValues(ComboBox searchDdlb, String rs) {
        searchDdlb.addItem(rs + " " + "ID");
        searchDdlb.addItem(rs + " " + "No");
        searchDdlb.addItem(rs + " " + "Name");
        searchDdlb.addItem(rs + " " + "Status");
        searchDdlb.addItem(rs + " " + "Type");
        return searchDdlb;
    }

    public static HorizontalLayout getResponsiveControls(HorizontalLayout tempLayout) {
        HorizontalLayout controlBar = new HorizontalLayout();
        controlBar.setStyleName("responsivePagedTable");

        HorizontalLayout pageSize = (HorizontalLayout) tempLayout.getComponent(0);
        HorizontalLayout pageManagement = (HorizontalLayout) tempLayout.getComponent(1);

        CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();
        cssLayout.addComponent(pageSize.getComponent(0));
        cssLayout.addComponent(pageSize.getComponent(0));
        for (int index = 0; index < 8; index++) {
            cssLayout.addComponent(pageManagement.getComponent(0));
        }
        controlBar.addComponent(cssLayout);
        return controlBar;
    }

    public static List<HelperDTO> getDropDownList(final String listType) throws SystemException {
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        LOGGER.info("Helper Table listType=" + listType);
        final DynamicQuery helperTableQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        helperTableQuery.add(RestrictionsFactoryUtil.like(Constants.LIST_NAME, listType));
        helperTableQuery.addOrder(OrderFactoryUtil.asc(Constants.DESCRIPTION));
        final List<HelperTable> list = HelperTableLocalServiceUtil.dynamicQuery(helperTableQuery);
        helperList.add(Constant.HELPER_DTO);
        if (list != null) {
            for (HelperTable temp : list) {
                final HelperTable helperTable = (HelperTable) temp;
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(),
                        helperTable.getDescription()));
            }
        }
        LOGGER.info("Helper Table list size =" + helperList.size());
        return helperList;
    }

    public static ComboBox getNativeSelect(final ComboBox select, final List<HelperDTO> helperList) throws Exception {
        select.removeAllItems();
        int size = helperList.size();
        for (int i = 0; i < size; i++) {
            final HelperDTO helperDTO = helperList.get(i);
            String itemId1 = StringUtils.EMPTY + helperDTO.getId();
            select.addItem(itemId1);
            select.setItemCaption(itemId1, helperDTO.getDescription());
        }
        select.setValue(Constants.ZEROSTRING);
        return select;
    }

    public static ComboBox loadNewTabSearchDdlb(ComboBox searchDdlb, String componentType) {
        searchDdlb.removeAllItems();

        if (componentType.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
            searchDdlb.addItem(Constants.COMPANY_ID);
            searchDdlb.addItem(Constants.COMPANYNAME);
            searchDdlb.addItem(Constants.COMPANYNO);
            searchDdlb.addItem(Constants.COMPANYSTATUS);
            searchDdlb.addItem(Constants.COMPANYTYPE);
            searchDdlb.addItem(Constants.COMPANYCATEGORY);
            searchDdlb.addItem(Constants.TRADECLASS);
            searchDdlb.setValue(Constants.COMPANY_ID);
        } else if (componentType.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
            searchDdlb.addItem(Constants.ITEM_ID);
            searchDdlb.addItem(Constants.ITEM_NAME);
            searchDdlb.addItem(Constants.ITEM_NO);
            searchDdlb.addItem(Constants.ITEM_STATUS);
            searchDdlb.addItem(Constants.ITEM_TYPE);
            searchDdlb.addItem(Constants.BRAND);
            searchDdlb.addItem(Constants.FORM);
            searchDdlb.addItem(Constants.STRENGTH);
            searchDdlb.addItem(Constants.THERAPY_CLASS);
            searchDdlb.addItem(Constants.ITEM_START_DATE);
            searchDdlb.addItem(Constants.ITEM_END_DATE);
            searchDdlb.setValue(Constants.ITEM_ID);
        } else if (componentType.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString()) || componentType.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
            searchDdlb.addItem(Constants.IFP_ID);
            searchDdlb.addItem(Constants.IfpNAME);
            searchDdlb.addItem(Constants.IFP_NO);
            searchDdlb.addItem(Constants.IFP_STATUS);
            searchDdlb.addItem(Constants.IFPTYPE);
            searchDdlb.setValue(Constants.IFP_ID);
        }
        return searchDdlb;
    }

    public static String convertDateFormat(String stringDate, String inputFormat, String outputFormat) {
        try {
            SimpleDateFormat inputDateFormatter = new SimpleDateFormat(inputFormat);
            SimpleDateFormat outputDateFormatter = new SimpleDateFormat(outputFormat);
            Date date = new Date();
            date = inputDateFormatter.parse(stringDate);
            return outputDateFormatter.format(date);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(CommonLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Date convertStringToDate(String stringDate) {
        if (stringDate.equals(Constants.NULL)) {
            return null;
        }
        try {
            SimpleDateFormat inputDateFormatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            Date date = new Date();
            date = inputDateFormatter.parse(stringDate);
            return date;
        } catch (ParseException ex) {
            LOGGER.error("Unparsable Date");
        }
        return null;
    }

    public static int convertStringToInt(String value) {
        int convertedValue = 0;
        try {
            convertedValue = Integer.parseInt(value);
        } catch (Exception e) {
            LOGGER.error("Unable To convert to Int " + value);
        }
        return convertedValue;
    }

    public static List<HelperDTO> getPriceTypeResults() throws Exception {
        final List<HelperDTO> list = new ArrayList<HelperDTO>();

        String query = CommonUtil.getQuery(null, "ad.loadPriceType");
        List results = discountDAO.getRebates(query);

        HelperDTO helperTable;

        for (final Iterator<Object[]> iterator = results.iterator(); iterator.hasNext();) {
            final Object[] value = iterator.next();
            helperTable = new HelperDTO();
            helperTable.setId(value[0] != null ? Integer.valueOf(value[0].toString()) : 0);
            helperTable.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
            if (!StringUtils.EMPTY.equals(helperTable.getDescription())) {
                list.add(helperTable);
            }
        }
        LOGGER.info("Ending getLazyPriceTypeResults  return list size :" + +list.size());
        return list;
    }

    public static int getProjectionIdForSubmittedContract(String sessionId, boolean isCustomerAdded) {
        LOGGER.info(" Inside getLastApprovedProjectionId");
        List input = new ArrayList();
        input.add(sessionId);
        String udc = StringUtils.EMPTY;
        if (!isCustomerAdded) {
            udc = "1";
        } else {
            udc = "2";
        }
        input.add(udc);

        List list = ItemQueries.getItemData(input, "get Selected ProjectionId", null);
        int projectionId = 0;
        if (list != null && !list.isEmpty()) {

            projectionId = Integer.parseInt(String.valueOf(list.get(0)));
        }
        LOGGER.info(" exiting getLastApprovedProjectionId. Projection id =  " + projectionId);
        return projectionId;

    }

    public static int getProjectionIdForCheckedContract(String sessionId, String screenName, boolean check) {
        LOGGER.info(" Inside getProjectionIdForCheckedContract");
        int checkRecord = check ? 1 : 0;
        int projectionId = 0;

        String query = "SELECT TOP 1 PM.PROJECTION_MASTER_SID FROM PROJECTION_MASTER PM\n"
                + "       JOIN GCM_GLOBAL_DETAILS Temp ON PM.PROJECTION_MASTER_SID = Temp.PROJECTION_MASTER_SID\n"
                + "            WHERE Temp.SESSION_ID = '" + sessionId + "' \n"
                + "            AND Temp.OPERATION = '0'\n"
                + "            AND Temp.CHECK_RECORD = '" + checkRecord + "' \n"
                + "            AND Temp.SCREEN_NAME = '" + screenName + "' \n"
                + "            AND PM.IS_APPROVED = 'A' \n"
                + "  ORDER  BY PM.MODIFIED_DATE DESC ";

        List list = CompanyMasterLocalServiceUtil.executeQuery(query);

        if (list != null && !list.isEmpty()) {
            projectionId = Integer.parseInt(String.valueOf(list.get(0)));
        }
        LOGGER.info(" exiting getProjectionIdForCheckedContract. Projection id =  " + projectionId);
        return projectionId;

    }

    public static int getCheckedContractSid(String sessionId, String screenName, boolean check) {
        LOGGER.info(" Inside getCheckedContractSid");

        int checkRecord = check ? 1 : 0;
        String queryString = StringUtils.EMPTY;
        queryString = "Select distinct CONTRACT_MASTER_SID from GCM_GLOBAL_DETAILS where SESSION_ID='" + sessionId + "' and OPERATION='0' AND SCREEN_NAME = '" + screenName + "'  AND CHECK_RECORD = '" + checkRecord + "' ";
        List<Integer> list = (List<Integer>) DAO.executeSelect(queryString);
        Object[] obj = null;
        int contractMasterSid = 0;
        if (list != null && !list.isEmpty()) {
            contractMasterSid = list.get(0);
        }
        LOGGER.info(" exiting getCheckedContractSid. CONTRACT_MASTER_SID =  " + contractMasterSid);
        return contractMasterSid;

    }

    public static int getSelectedContractSid(String sessionId, boolean isCustomerAdded) {
        LOGGER.info(" Inside getSelectedContractSid");
        String queryString = StringUtils.EMPTY;
        String udc = StringUtils.EMPTY;
        if (!isCustomerAdded) {
            udc = "1";
        } else {
            udc = "2";
        }
        queryString += "Select distinct CONTRACT_MASTER_SID from GCM_GLOBAL_DETAILS where SESSION_ID='" + sessionId + "' and OPERATION='" + udc + "'";

        List<Integer> list = (List<Integer>) DAO.executeSelect(queryString);
        Object[] obj = null;
        int contractMasterSid = 0;
        if (list != null && !list.isEmpty()) {
            contractMasterSid = list.get(0);
        }
        LOGGER.info(" exiting getSelectedContractSid. CONTRACT_MASTER_SID =  " + contractMasterSid);
        return contractMasterSid;

    }

    public static String getSelectedContractName(String sessionId, boolean isTransfer) {
        LOGGER.info(" Inside getSelectedContractName");
        String queryString = StringUtils.EMPTY;
        String udc = StringUtils.EMPTY;
        if (!isTransfer) {
            udc = "1";
        } else {
            udc = "2";
        }
        List input = new ArrayList();
        input.add(sessionId);
        input.add(udc);
        List<String> list = ItemQueries.getItemData(input, "Get Selected Contract Name", null);
        String contractMasterName = StringUtils.EMPTY;
        if (list != null && !list.isEmpty()) {
            contractMasterName = list.get(0);
        }
        LOGGER.info(" exiting getSelectedContractSid. CONTRACT_MASTER_SID =  " + contractMasterName);
        return contractMasterName;

    }

    public static String getSelectedCompanyNames(List<String> companyMasterSids) {
        LOGGER.info(" Inside getSelectedCompanyNames");
        String queryString = StringUtils.EMPTY;
        queryString += "select DISTINCT COMPANY_NAME from COMPANY_MASTER where COMPANY_MASTER_SID in (" + CommonUtils.CollectionToString(companyMasterSids, true) + ")";
        List<String> companyNamesList = (List<String>) DAO.executeSelect(queryString);
        String companyNames = CommonUtils.CollectionToString(companyNamesList, false);
        LOGGER.info(" exiting getSelectedCompanyNames  =  " + companyNames);
        return companyNames;

    }

    public static List<String> getSelectedCfpSid(String sessionId, boolean isTransfer) {
        LOGGER.info(" Inside getSelectedCfpSid");
        String queryString = StringUtils.EMPTY;
        String udc = StringUtils.EMPTY;
        if (!isTransfer) {
            udc = "1";
        } else {
            udc = "2";
        }
        queryString += "Select distinct CFP_CONTRACT_SID from GCM_GLOBAL_DETAILS where SESSION_ID='" + sessionId + "' and OPERATION='" + udc + "'";
        return (List<String>) DAO.executeSelect(queryString);

    }

    public static String getDateForSubmittedContract(String sessionId, boolean isTransfer, boolean isStartDate, boolean isMax) {
        LOGGER.info(" Inside getDateForSubmittedContract");
        String queryString = StringUtils.EMPTY;
        String operation = StringUtils.EMPTY;
        String minMax = StringUtils.EMPTY;
        String date = StringUtils.EMPTY;
        if (!isTransfer) {
            operation = "Current Contract";
        } else {
            operation = "Transfer Contract";
        }

        if (isMax) {
            minMax = "MAX";
        } else {
            minMax = "MIN";
        }

        if (isStartDate) {
            date = "START_DATE";
        } else {
            date = "END_DATE";
        }
        queryString += "Select " + minMax + "(" + date + ") from GCM_GLOBAL_DETAILS where SESSION_ID='" + sessionId + "' and SCREEN_NAME='" + operation + "'";

        if (!isTransfer) {
            queryString += " AND OPERATION = '1'";
        } else {
            queryString += " AND OPERATION = '2'";
        }

        LOGGER.info(" get Date for submitted contract query - " + queryString);
        List<String> list = (List<String>) DAO.executeSelect(queryString);
        String newDate = StringUtils.EMPTY;
        if (list != null && !list.isEmpty()) {
            newDate = String.valueOf(list.get(0));
        }

        LOGGER.info(" exiting getDateForSubmittedContract =  " + newDate);
        return newDate;

    }

    public static String getDateForCheckedContract(String sessionId, boolean isTransfer, boolean isStartDate, boolean isMax) {
        LOGGER.info(" Inside getDateForCheckedContract");
        String queryString = StringUtils.EMPTY;
        String operation = StringUtils.EMPTY;
        String minMax = StringUtils.EMPTY;
        String date = StringUtils.EMPTY;
        if (!isTransfer) {
            operation = "Current Contract";
        } else {
            operation = "Transfer Contract";
        }

        if (isMax) {
            minMax = "MAX";
        } else {
            minMax = "MIN";
        }

        if (isStartDate) {
            date = "START_DATE";
        } else {
            date = "END_DATE";
        }
        queryString += "Select " + minMax + "(" + date + ") from GCM_GLOBAL_DETAILS where SESSION_ID='" + sessionId + "' and SCREEN_NAME='" + operation + "'";
        queryString += " AND CHECK_RECORD = '1'";

        LOGGER.info(" get Date for submitted contract query - " + queryString);
        List<String> list = (List<String>) DAO.executeSelect(queryString);
        String newDate = StringUtils.EMPTY;
        if (list != null && !list.isEmpty()) {
            newDate = String.valueOf(list.get(0));
        }

        LOGGER.info(" exiting getDateForCheckedContract =  " + newDate);
        return newDate;

    }

    /**
     * Creation of New Projection in GTN System
     *
     * @param oldProjectionId
     * @param userId
     * @param sessionId
     * @param masterSids
     * @param isCustomerModule
     * @param isAddModule
     * @return
     */
    public List<String> generateNewProjection(String userId, String sessionId, int oldProjectionId, List<String> masterSids, boolean isCustomerModule, boolean isAddModule) {

        LOGGER.info("Entering generateNewProjection" + oldProjectionId);
        List<String> tempList = new ArrayList<String>();
        try {

            if (callCcpInsertProcedure()) {

                String relationShipBuilderSidQuery = "select CUST_RELATIONSHIP_BUILDER_SID, PROD_RELATIONSHIP_BUILDER_SID, FORECASTING_TYPE, CUSTOMER_HIERARCHY_SID from PROJECTION_MASTER where PROJECTION_MASTER_SID = " + oldProjectionId;
                Object[] projectionMasterRow = (Object[]) CompanyMasterLocalServiceUtil.executeQuery(relationShipBuilderSidQuery).get(0);
                LOGGER.info(" cust Rel Builder Sid " + String.valueOf(projectionMasterRow[0]));
                LOGGER.info(" prod Rel Builder Sid " + String.valueOf(projectionMasterRow[1]));

                List<String> relationshipBuilderSids = new ArrayList<String>();
                relationshipBuilderSids.add(String.valueOf(projectionMasterRow[0]));
                relationshipBuilderSids.add(String.valueOf(projectionMasterRow[1]));
                updateProdHirarechy(newProjectionId, getProdRelationshipId(), masterSids);
                List<String> roleList = new ArrayList<String>();
                ProcessInstance processInstance = DSCalculationLogic.startWorkflow();            
                User userModel = UserLocalServiceUtil.getUser(Long.parseLong(userId));
                // Please dont delet the below line as we are intializing BPM session in inner methods
                DSCalculationLogic.isValidWorkflowUser(userModel, roleList, processInstance.getId());
                if (insertIntoCcpMap(relationshipBuilderSids)) {
                    newProjectionId = cloneProjection(oldProjectionId,userId);
                     if (newProjectionId != 0) {
                            Long processInstanceId = processInstance.getId();
                            try {
                                TaskSummary taskSummary = DSCalculationLogic.startAndCompleteTask(userModel, newProjectionId, processInstanceId);
                                processInstanceId = taskSummary.getProcessInstanceId();
                            } catch (Exception e) {
                                LOGGER.error(e);
                            }
                     }
                    LOGGER.info(" New Projection Id ===== " + newProjectionId);
                    if (newProjectionId != 0) {

                        boolean isProjectionCustUpdated = false;
                        boolean isProjectionProdUpdated = false;

                        List custRelationshipLevelSids = getRelationShipLevelSid(isCustomerModule ? masterSids : null, relationshipBuilderSids.get(0));
                        if (custRelationshipLevelSids != null && !custRelationshipLevelSids.isEmpty()) {

                            cloneCustomerAndProductHierarchy(oldProjectionId, newProjectionId, true, false);
                            for (Object relationshipLevelSid : custRelationshipLevelSids) {
                                updateCustomerOrProductHierarchy(true, newProjectionId, String.valueOf(relationshipLevelSid));
                            }
                            isProjectionCustUpdated = true;
                        } else {
                            isProjectionCustUpdated = false;
                            LOGGER.info(" cust relationshipLevelSids is empty or null ");
                        }

                        List prodRelationshipLevelSids = getRelationShipLevelSid(!isCustomerModule ? masterSids : null, relationshipBuilderSids.get(1));
                        if (prodRelationshipLevelSids != null && !prodRelationshipLevelSids.isEmpty()) {

                            cloneCustomerAndProductHierarchy(oldProjectionId, newProjectionId, false, true);
                            for (Object relationshipLevelSid : prodRelationshipLevelSids) {
                                updateCustomerOrProductHierarchy(false, newProjectionId, String.valueOf(relationshipLevelSid));
                            }
                            isProjectionProdUpdated = true;
                        } else {
                            isProjectionProdUpdated = false;
                            LOGGER.info(" prod relationshipLevelSids is empty or null ");
                        }
                          if (isProjectionCustUpdated && isProjectionProdUpdated) {
                            if (insertIntoProjectionDetails(relationshipBuilderSids.get(0), newProjectionId)) {

                                Object[] inputs = new Object[4];
                                inputs[0] = newProjectionId;
                                inputs[1] = userId;
                                inputs[2] = sessionId;

                                String moduleName = String.valueOf(projectionMasterRow[2]);
                                String marketType = StringUtils.EMPTY;

                                if (Constants.IndicatorConstants.MANDATED.equals(moduleName)) {
                                    marketType = getMarketType(String.valueOf(projectionMasterRow[3]), String.valueOf(projectionMasterRow[0]));
                                }

                                if (isAddModule) {
                                    LOGGER.info(" Old Projection - " + oldProjectionId + " New projection - " + newProjectionId + " Market Type" + marketType);
                                    Object[] orderedArgs = {oldProjectionId, newProjectionId, marketType};
                                    AbstractLogic.callProcedure("PRC_FE_ADD_EVENT", orderedArgs);

                                } else {
                                    if ("Non Mandated".equals(moduleName.trim())) {
                                        callTableInsert(inputs, "PRC_NM_DISCOUNT_INSERT");
                                        callTableInsert(inputs, "PRC_NM_PPA_INSERT");
                                        saveTempToMain(newProjectionId, userId, sessionId);
                                    } else if ("Mandated".equals(moduleName.trim())) {

                                        Object[] suppInputs = {newProjectionId, userId, marketType, sessionId};
                                        callDiscountTableInsert(suppInputs, "PRC_M_SUPP_INSERT");
                                        callTableInsert(inputs, "PRC_M_SUPP_PROJECTION");
                                        Object[] discInputs = {newProjectionId, userId, "SPAP", sessionId};
                                        callDiscountTableInsert(discInputs, "PRC_M_DISCOUNT_INSERT");
                                        mandatedTempToMainSave(userId, sessionId, newProjectionId);
                                    } else {
                                        LOGGER.error("New module Name found " + moduleName);
                                    }
                                }

                                LoadTabLogic loadTabLogic = new LoadTabLogic();
                                loadTabLogic.setForecastingType(newProjectionId);
                                tempList.add(swapForecastingType(loadTabLogic.forecatingType));
                                tempList.add(loadTabLogic.getProjectionName(newProjectionId));
                                tempList.add(String.valueOf(newProjectionId));
                                tempList.add("\n New Projection created with forecasting type -" + tempList.get(0) + "\n and Projection Name - " + tempList.get(1) + " ");
                            } else {
                                LOGGER.error(" Projection details insert failed");
                            }
                        } else {
                            LOGGER.error(" projection prod or cust hierarchy update failed");
                        }

                    } else {
                        LOGGER.error(" Projection cloning error ");
                    }
                } else {
                    LOGGER.error(" CCP MAP insert failed ");
                }

            } else {
                LOGGER.error(" CCP insert procedure failed ");
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }

        LOGGER.info("Exiting generateNewProjection");
        return tempList;
    }

    public boolean callCcpInsertProcedure() {
        LOGGER.info("calling CcpInsertProcedure");
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            if (datasource != null) {
                connection = datasource.getConnection();
            } else {
                LOGGER.info("Failed to lookup datasource.");
            }
            if (connection != null) {

                LOGGER.info("Got Connection " + connection.toString() + ", ");
                statement = connection.prepareCall("{call PRC_CCP_POPULATION()}");
                statement.execute();
            }
        } catch (Exception ex) {

            LOGGER.error(ex.getMessage());
            return false;
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        LOGGER.info("exiting CcpInsertProcedure");
        return true;
    }

    public boolean insertIntoCcpMap(List<String> relationshipBuilderSids) {
        List<String> relationshipBuilderSidsList = null;
        TradingPartnerDAO dao = new TradingPartnerDAOImpl();
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (relationshipBuilderSids != null && !relationshipBuilderSids.isEmpty()) {
            relationshipBuilderSidsList = new ArrayList<String>(relationshipBuilderSids);
        }
        parameters.put("indicator", "insertToCcpMap");
        parameters.put("relationshipBuilderSids", relationshipBuilderSidsList);
        dao.insertIntoCcpMap(parameters);
        return true;
    }

    private int cloneProjection(int projectionId,String userId) {
        List input = new ArrayList();
        input.add(userId);
        input.add(CommonUtils.getCurrentTimestamp());
        input.add(CommonUtils.getCurrentTimestamp());
        input.add(projectionId);
        List finalList = ItemQueries.getItemData(input, "clone Projection", null);
        return CommonUtils.convertToInteger(String.valueOf(finalList.get(0)));
    }

    private List getRelationShipLevelSid(List<String> masterSidList, String relationshipBuilderSid) {
        LOGGER.info("Entering getRelationShipLevelSid " + masterSidList);
        String query = "select DISTINCT RELATIONSHIP_LEVEL_SID from RELATIONSHIP_LEVEL_DEFINITION RLD, (select HIERARCHY_NO from RELATIONSHIP_LEVEL_DEFINITION where \n";
        if (masterSidList != null && !masterSidList.isEmpty()) {
            query += " RELATIONSHIP_LEVEL_VALUES in (" + CommonUtils.CollectionToString(masterSidList, true) + ") and ";
        }
        query += " RELATIONSHIP_BUILDER_SID = " + relationshipBuilderSid + ") A \n"
                + " where RLD.HIERARCHY_NO like A.HIERARCHY_NO+'%'";
        LOGGER.info(" getRelationShipLevelSid query " + query);
        List list = CompanyMasterLocalServiceUtil.executeQuery(query);
        LOGGER.info("Existing getRelationShipLevelSid");

        return list;
    }

    private void cloneCustomerAndProductHierarchy(int oldProjectionId, int newProjectionId, boolean custHierarchyClone, boolean prodHierarchyClone) {
        LOGGER.info("Entering cloneCustomerAndProductHierarchy" + oldProjectionId);
        if (custHierarchyClone) {
            String custQuery = "INSERT INTO PROJECTION_CUST_HIERARCHY (PROJECTION_MASTER_SID, RELATIONSHIP_LEVEL_SID) \n"
                    + "SELECT " + newProjectionId + ", RELATIONSHIP_LEVEL_SID from PROJECTION_CUST_HIERARCHY where PROJECTION_MASTER_SID = " + oldProjectionId;
            CompanyMasterLocalServiceUtil.executeUpdateQuery(custQuery);

        }
        if (prodHierarchyClone) {
            String prodQuery = "INSERT INTO PROJECTION_PROD_HIERARCHY (PROJECTION_MASTER_SID, RELATIONSHIP_LEVEL_SID) \n"
                    + "SELECT " + newProjectionId + ", RELATIONSHIP_LEVEL_SID from PROJECTION_PROD_HIERARCHY where PROJECTION_MASTER_SID = " + oldProjectionId;
            CompanyMasterLocalServiceUtil.executeUpdateQuery(prodQuery);

        }

        LOGGER.info("Exiting cloneCustomerAndProductHierarchy");
    }

    private int updateCustomerOrProductHierarchy(boolean customerUpdated, int projectionId, String relationshipLevelSid) {

        String tableName = StringUtils.EMPTY;
        String query = StringUtils.EMPTY;

        if (customerUpdated) {
            tableName = "PROJECTION_CUST_HIERARCHY";
        } else {
            tableName = "PROJECTION_PROD_HIERARCHY";
        }

        LOGGER.info("Entering updateCustomerOrProductHierarchy " + tableName);

        query = "IF NOT EXISTS(SELECT " + tableName + "_SID from " + tableName + " where PROJECTION_MASTER_SID = " + projectionId + " and RELATIONSHIP_LEVEL_SID = " + relationshipLevelSid + ")"
                + " INSERT into " + tableName + " (PROJECTION_MASTER_SID, RELATIONSHIP_LEVEL_SID) values (" + projectionId + "," + relationshipLevelSid + ")";

        int recordNos = CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
        LOGGER.info("Exiting updateCustomerOrProductHierarchy");
        return recordNos;
    }

    public boolean insertIntoProjectionDetails(String relationshipBuilderSid, int newProjectionId) {

        LOGGER.info("Entering insertIntoProjectionDetails method " + relationshipBuilderSid + newProjectionId);
        boolean status = false;
        String lastHierarchyNoQuery = "select\n"
                + "RLD.HIERARCHY_NO\n"
                + "from RELATIONSHIP_LEVEL_DEFINITION RLD JOIN \n"
                + "PROJECTION_CUST_HIERARCHY PCH on PCH.RELATIONSHIP_LEVEL_SID = RLD.RELATIONSHIP_LEVEL_SID\n"
                + "JOIN PROJECTION_MASTER PM on PCH.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID\n"
                + "and  RLD.LEVEL_NO = PM.CUSTOMER_HIERARCHY_INNER_LEVEL\n"
                + "and RLD.RELATIONSHIP_BUILDER_SID = "+relationshipBuilderSid+"\n"
                + "AND PCH.PROJECTION_MASTER_SID = " + newProjectionId;

        List lastHierarchyNoList = CompanyMasterLocalServiceUtil.executeQuery(lastHierarchyNoQuery);

        try {
            if (lastHierarchyNoList != null && !lastHierarchyNoList.isEmpty()) {
                for (Object hierarchyNo : lastHierarchyNoList) {
                    StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);
                    customSql.append(CustomSQLUtil.get("gcm.saveCcp"));

                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(newProjectionId));
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(hierarchyNo));
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(newProjectionId));

                    CompanyMasterLocalServiceUtil.executeUpdateQuery(String.valueOf(customSql));
                }
                status = true;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }

        LOGGER.info("End of insertIntoProjectionDetails method");
        return status;
    }

    /**
     * To call all NM Tables Insert procedure
     *
     * @return
     */
    public void callNMTableInsert(final Object[] inputs) {
        callTableInsert(inputs, "PRC_NM_SALES_INSERT");
        callTableInsert(inputs, "PRC_NM_DISCOUNT_INSERT");
        callTableInsert(inputs, "PRC_NM_PPA_INSERT");
    }

    public void saveTempToMain(final int projectionId, final String userId, final String sessionId) {
        try {
            LOGGER.info("Entering tempOperation method ");

            String customSql = CustomSQLUtil.get("nm.saveToMainTable");
            customSql = customSql.replace("?UID", userId);
            customSql = customSql.replace("?SID", sessionId);

            CompanyMasterLocalServiceUtil.executeUpdateQuery(customSql);
            updateSaveFlag(projectionId);
            LOGGER.info("End of tempOperation method");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static void callPrcFeSalesTransfer(final String sessionId) {
        LOGGER.info("Entering callPrcFeSalesTransfer");

        Connection connection = null;
        CallableStatement statement = null;
        DataSource datasource;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            if (datasource != null) {
                connection = datasource.getConnection();
            }

            if (connection != null) {
                statement = connection.prepareCall("{call PRC_FE_TRANSFER_SALES('" + sessionId + "')}");
                statement.execute();
            }

            LOGGER.info("Ending callPrcFeSalesTransfer Procedure");

        } catch (Exception ex) {
            LOGGER.error(new Date() + ex.getMessage());
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        LOGGER.info("Exiting callPrcFeSalesTransfer");
    }

    public static void callPrcFeProjectionDetailsTransfer(final String sessionId) {
        LOGGER.info("Entering callPrcFeProjectionDetailsTransfer" + sessionId);

        Connection connection = null;
        CallableStatement statement = null;
        DataSource datasource;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            if (datasource != null) {
                connection = datasource.getConnection();
            }

            if (connection != null) {
                statement = connection.prepareCall("{call PRC_FE_PROJ_DET_TRANSFER_SALES('" + sessionId + "')}");
                statement.execute();
            }

            LOGGER.info("Ending callPrcFeProjectionDetailsTransfer Procedure");

        } catch (Exception ex) {
            LOGGER.error(new Date() + ex.getMessage());
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        LOGGER.info("Exiting callPrcFeProjectionDetailsTransfer");
    }

    /**
     * To call all NM Tables Insert procedure
     *
     * @return
     */
    public void callTableInsert(final Object[] inputs, String procedureName) {
        LOGGER.info("Entering callTableInsert");

        Connection connection = null;
        CallableStatement statement = null;
        DataSource datasource;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            if (datasource != null) {
                connection = datasource.getConnection();
            }

            if (connection != null) {
                LOGGER.info(" Executing " + procedureName + " procedure ");
                statement = connection.prepareCall("{call " + procedureName + "(?,?,?)}");
                statement.setObject(1, inputs[0]);
                statement.setObject(2, Integer.parseInt((String) inputs[1]));
                statement.setObject(3, Integer.parseInt((String) inputs[2]));
                statement.execute();
            }

            LOGGER.info("Ending " + procedureName + " Procedure");

        } catch (Exception ex) {
            LOGGER.error(new Date() + ex.getMessage());
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        LOGGER.info("Exiting callTableInsert");
    }

    private void updateSaveFlag(final int projectionId) throws SystemException, Exception {
        LOGGER.info("Entering updateSaveFlag method ");
        CompanyMasterLocalServiceUtil.executeUpdateQuery("UPDATE PROJECTION_MASTER SET SAVE_FLAG = 1 where PROJECTION_MASTER_SID = " + projectionId);
        LOGGER.info("Exiting updateSaveFlag method ");
    }

    public String idString(List<String> list) {
        String value = Constants.EMPTY;
        if (list != null && list.size() > 0) {
            Boolean f = false;
            for (Object item : list) {
                if (!f) {
                    String company = String.valueOf(item);
                    company = "'" + company + "'";
                    value = company;
                    f = true;
                } else {
                    String company = String.valueOf(item);
                    company = "'" + company + "'";
                    value = value + Constants.COMMA + company;
                }
            }
        }

        return value;
    }

    public static List<HelperDTO> getBrand(String queryName) {
        List results = new ArrayList();
        List<HelperDTO> searchList = new ArrayList<HelperDTO>();
        String query = CustomSQLUtil.get(queryName);
        try {
            results = discountDAO.getRebates(query);

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CommonLogic.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        int size = results.size();
        for (int i = 0; i < size; i++) {
            Object arr[] = (Object[]) results.get(i);
            HelperDTO dTO = new HelperDTO();
            dTO.setId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
            dTO.setDescription(Converters.convertNullToEmpty(arr[1]));
            searchList.add(dTO);
        }
        return searchList;
    }

    public void updateMainTable(List<String> companyMasterSids, int newProjectionId, String contractMasterSid, String forecastingType, String moduleName) {
        StringBuilder salesQuery = new StringBuilder(StringUtils.EMPTY);
        StringBuilder discountQuery = new StringBuilder(StringUtils.EMPTY);
        StringBuilder rebateQuery = new StringBuilder(StringUtils.EMPTY);
        List queryList = new ArrayList();
        if ("Non Mandated".equalsIgnoreCase(forecastingType)) {
            salesQuery.append(CustomSQLUtil.get("nm.salesTableUpdate"));
            discountQuery.append(CustomSQLUtil.get("nm.discountTableUpdate"));
            rebateQuery.append(CustomSQLUtil.get("nm.ppaTableUpdate"));
        } else if ("Mandated".equalsIgnoreCase(forecastingType)) {
            salesQuery.append(CustomSQLUtil.get("man.salesTableUpdate"));
            discountQuery.append(CustomSQLUtil.get("man.suppTableUpdate"));
            rebateQuery.append(CustomSQLUtil.get("man.discountTableUpdate"));
        }
        salesQuery.replace(salesQuery.indexOf("?CON"), salesQuery.indexOf("?CON") + 4, contractMasterSid);
        salesQuery.replace(salesQuery.indexOf("?COM"), salesQuery.indexOf("?COM") + 4, CommonUtils.CollectionToString(companyMasterSids, false));
        salesQuery.replace(salesQuery.indexOf("?PM"), salesQuery.indexOf("?PM") + 3, String.valueOf(newProjectionId));

        discountQuery.replace(discountQuery.indexOf("?CON"), discountQuery.indexOf("?CON") + 4, contractMasterSid);
        discountQuery.replace(discountQuery.indexOf("?COM"), discountQuery.indexOf("?COM") + 4, CommonUtils.CollectionToString(companyMasterSids, false));
        discountQuery.replace(discountQuery.indexOf("?PM"), discountQuery.indexOf("?PM") + 3, String.valueOf(newProjectionId));

        rebateQuery.replace(rebateQuery.indexOf("?CON"), rebateQuery.indexOf("?CON") + 4, contractMasterSid);
        rebateQuery.replace(rebateQuery.indexOf("?COM"), rebateQuery.indexOf("?COM") + 4, CommonUtils.CollectionToString(companyMasterSids, false));
        rebateQuery.replace(rebateQuery.indexOf("?PM"), rebateQuery.indexOf("?PM") + 3, String.valueOf(newProjectionId));

        if (TRADING_PARTNER_REMOVE.getConstant().equals(moduleName)) {
            salesQuery.replace(salesQuery.indexOf("?FIELD"), salesQuery.indexOf("?FIELD") + 6, "COMPANY_MASTER_SID");
            discountQuery.replace(discountQuery.indexOf("?FIELD"), discountQuery.indexOf("?FIELD") + 6, "COMPANY_MASTER_SID");
            rebateQuery.replace(rebateQuery.indexOf("?FIELD"), rebateQuery.indexOf("?FIELD") + 6, "COMPANY_MASTER_SID");
        } else if ("Item Remove".equals(moduleName)) {
            salesQuery.replace(salesQuery.indexOf("?FIELD"), salesQuery.indexOf("?FIELD") + 6, "ITEM_MASTER_SID");
            discountQuery.replace(discountQuery.indexOf("?FIELD"), discountQuery.indexOf("?FIELD") + 6, "ITEM_MASTER_SID");
            rebateQuery.replace(rebateQuery.indexOf("?FIELD"), rebateQuery.indexOf("?FIELD") + 6, "ITEM_MASTER_SID");
        }
        CompanyMasterLocalServiceUtil.executeUpdateQuery(salesQuery.toString());
        CompanyMasterLocalServiceUtil.executeUpdateQuery(discountQuery.toString());
        CompanyMasterLocalServiceUtil.executeUpdateQuery(rebateQuery.toString());
    }

    /**
     * To call all M Tables Insert procedure
     *
     * @return
     */
    public void callMTableInsert(final Object[] inputs) {
        callTableInsert(inputs, "Prc_mandated_sales_insert");
        callTableInsert(inputs, "PRC_M_SUPP_INSERT");
        callTableInsert(inputs, "PRC_M_DISCOUNT_INSERT");
    }

    /**
     * To call all NM Tables Insert procedure
     *
     * @return
     */
    public void callDiscountTableInsert(final Object[] inputs, String procedureName) {
        LOGGER.info("Entering callTableInsert");

        Connection connection = null;
        CallableStatement statement = null;
        DataSource datasource;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            if (datasource != null) {
                connection = datasource.getConnection();
            }

            if (connection != null) {
                LOGGER.info(" Executing " + procedureName + " procedure ");
                statement = connection.prepareCall("{call " + procedureName + "(?,?,?,?)}");

                statement.setObject(1, inputs[0]);
                statement.setObject(2, inputs[1]);
                statement.setObject(3, inputs[2]);
                statement.setObject(4, inputs[3]);
                statement.execute();
            }

            LOGGER.info("Ending " + procedureName + " Procedure");

        } catch (Exception ex) {
            LOGGER.error(new Date() + ex.getMessage());
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        LOGGER.info("Exiting callTableInsert");
    }

    public void mandatedTempToMainSave(String userId, String sessionId, int newProjectionID) {
        LOGGER.info("Entering mandatedTempToMainSave");
        List input = new ArrayList();
        input.add(userId);
        input.add(sessionId);
        ItemQueries.itemUpdate(input, "M_SALES_PROJECTION_MASTER INSERT");
        ItemQueries.itemUpdate(input, "M_ACTUAL_SALES INSERT");
        ItemQueries.itemUpdate(input, "M_SALES_PROJECTION INSERT");
        ItemQueries.itemUpdate(input, "M_SUPPLEMENTAL_DISC_MASTER INSERT");
        ItemQueries.itemUpdate(input, "M_SUPPLEMENTAL_DISC_PROJ INSERT");
        input.add(newProjectionID);
        ItemQueries.itemUpdate(input, "UPDATE TABLE DATA Sales");
        ItemQueries.itemUpdate(input, "UPDATE TABLE DATA Discount");
    }

    public String getGenerateMarketValue(int rbID) {
        LOGGER.info("Endtering getGenerateMarketValue");
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        List input = new ArrayList();
        input.add(rbID);
        List<Object> temp = ItemQueries.getItemData(input, "get Generate MarketValue", null);
        String marketType = StringUtils.EMPTY;
        if (temp.size() > 0) {
            if (String.valueOf(temp.get(0)) != null && !StringUtils.EMPTY.equals(String.valueOf(temp.get(0)))) {
                marketType = String.valueOf(temp.get(0));
            }
        }
        LOGGER.info("Exiting getGenerateMarketValue");
        return marketType;
    }

    public String getDefinedValue(String definedValue) {
        String str = StringUtils.EMPTY;
        String query = "select LEVEL_VALUE_REFERENCE from HIERARCHY_LEVEL_DEFINITION where HIERARCHY_DEFINITION_SID=" + definedValue + " and LEVEL_NAME='Market Type'";
        List<Object> listValue = CompanyMasterLocalServiceUtil.executeQuery(query);
        if (listValue.size() > 0) {
            for (int i = 0; i < listValue.size(); i++) {
                str = String.valueOf(listValue.get(0));
            }
        }
        return str;
    }

    public String getHelperValue(String marketType) {
        String marketTypeValue = StringUtils.EMPTY;
        String query = "select DESCRIPTION from HELPER_TABLE where HELPER_TABLE_SID in('" + marketType + "') and LIST_NAME='CONTRACT_TYPE' ";
        List<Object> temp = CompanyMasterLocalServiceUtil.executeQuery(query);
        if (temp.size() > 0) {
            for (int i = 0; i < temp.size(); i++) {
                marketTypeValue = String.valueOf(temp.get(i));
            }
        }
        return marketTypeValue;
    }

    public List<String> createNewProjection(int oldProjectionId, List<String> masterSids, boolean isCustomerUpdated, SessionDTO session)  {
        List<String> tempList = new ArrayList<String>();
        try {
            String relationShipBuilderSidQuery = "select CUST_RELATIONSHIP_BUILDER_SID, PROD_RELATIONSHIP_BUILDER_SID, FORECASTING_TYPE, CUSTOMER_HIERARCHY_SID from PROJECTION_MASTER where PROJECTION_MASTER_SID = " + oldProjectionId;
            Object[] projectionMasterRow = (Object[]) CompanyMasterLocalServiceUtil.executeQuery(relationShipBuilderSidQuery).get(0);
            LOGGER.info(" cust Rel Builder Sid " + String.valueOf(projectionMasterRow[0]));
            LOGGER.info(" prod Rel Builder Sid " + String.valueOf(projectionMasterRow[1]));
            setProdRelationshipId(Integer.valueOf(String.valueOf(projectionMasterRow[1])));
            List<String> relationshipBuilderSids = new ArrayList<String>();
            relationshipBuilderSids.add(String.valueOf(projectionMasterRow[0]));
            relationshipBuilderSids.add(String.valueOf(projectionMasterRow[1]));
            List<String> roleList = new ArrayList<String>();
            ProcessInstance processInstance = DSCalculationLogic.startWorkflow();            
            User userModel = UserLocalServiceUtil.getUser(Long.parseLong(session.getUserId()));
            // Please dont delet the below line as we are intializing BPM session in inner methods
            DSCalculationLogic.isValidWorkflowUser(userModel, roleList, processInstance.getId());
            int newProjectionId = cloneProjection(oldProjectionId,session.getUserId());
            LOGGER.info(" New Projection Id ===== " + newProjectionId);
            if (newProjectionId != 0) {
                setNewProjectionId(newProjectionId);
                setForecastingType(String.valueOf(projectionMasterRow[2]));
                cloneCustomerAndProductHierarchy(oldProjectionId, newProjectionId, true, true);
                for (Object relationshipLevelSid : relationshipBuilderSids) {
                    updateCustomerOrProductHierarchy(false, newProjectionId, String.valueOf(relationshipLevelSid));
                }
                if (newProjectionId != 0) {
                    Long processInstanceId = processInstance.getId();
                    try {
                        TaskSummary taskSummary = DSCalculationLogic.startAndCompleteTask(userModel, newProjectionId, processInstanceId);
                        processInstanceId = taskSummary.getProcessInstanceId();
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                }
                if (insertIntoProjectionDetails(relationshipBuilderSids.get(0), newProjectionId)) {
                    String marketType = StringUtils.EMPTY;
                    Object[] inputs = new Object[4];
                    inputs[0] = newProjectionId;
                    inputs[1] = session.getUserId();
                    inputs[2] = session.getSessionId();
                    boolean flag = false;
                    if (Constants.IndicatorConstants.NON_MANDATED.getConstant().toString().equals(String.valueOf(projectionMasterRow[2]))) {
                        callNMTableInsert(inputs);
                        saveTempToMain(newProjectionId, session.getUserId(), session.getSessionId());
                        flag = true;
                    } else if (Constants.IndicatorConstants.MANDATED.getConstant().toString().equals(String.valueOf(projectionMasterRow[2]))) {
                        if (!StringUtils.EMPTY.equals(String.valueOf(projectionMasterRow[0])) && !Constants.ZEROSTRING.equals(String.valueOf(projectionMasterRow[0]))) {
                            marketType = getGenerateMarketValue(Integer.valueOf(String.valueOf(projectionMasterRow[0])));
                        }

                        String definedOrUDValue = StringUtils.EMPTY;
                        String definedValue = getDefinedValue(String.valueOf(projectionMasterRow[3]));
                        if ("User Defined".equalsIgnoreCase(definedValue)) {
                            definedOrUDValue = marketType;
                        } else {
                            definedOrUDValue = getHelperValue(marketType);
                        }
                        callTableInsert(inputs, "Prc_mandated_sales_insert");
                        Object[] suppInputs = {newProjectionId, definedOrUDValue, session.getUserId(), session.getSessionId()};
                        callDiscountTableInsert(suppInputs, "PRC_M_SUPP_INSERT");
                        callTableInsert(inputs, "PRC_M_SUPP_PROJECTION");
                        Object[] discInputs = {newProjectionId, session.getUserId(), "SPAP", session.getSessionId()};
                        callDiscountTableInsert(discInputs, "PRC_M_DISCOUNT_INSERT");
                        mandatedTempToMainSave(session.getUserId(), session.getSessionId(), newProjectionId);
                        flag = true;
                    }
                    if (flag) {
                        LoadTabLogic loadTabLogic = new LoadTabLogic();
                        if (TRADING_PARTNER_UPDATE.getConstant().equals(session.getModuleName())) {
                            session.setProjectionId(newProjectionId);
                            loadTabLogic.updateSalesAndDiscount(session);
                        } else {
                            if (!session.getModuleName().contains("Item")) {
                                updateMainTable(masterSids, newProjectionId, session.getContractMasterSid(), String.valueOf(projectionMasterRow[2]), session.getModuleName());
                            }
                        }
                        loadTabLogic.setForecastingType(newProjectionId);
                        tempList.add(swapForecastingType(loadTabLogic.forecatingType));
                        tempList.add(loadTabLogic.getProjectionName(newProjectionId));
                        tempList.add(String.valueOf(newProjectionId));
                        
                        tempList.add("'\n new Projection created with forecasting type -" + tempList.get(0) + "\n and Projection Name - " + tempList.get(1) + " ");
                    } else {
                        LOGGER.info(" Projection details insert failed");
                    }

                } else {
                    LOGGER.info(" Projection cloning error ");
                }

            }
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
        }

        return tempList;
    }

    public static String getForecastFlavour(int toProjectionId) {
        String query = "select FORECASTING_TYPE from PROJECTION_MASTER where PROJECTION_MASTER_SID = '" + toProjectionId + "'";

        return String.valueOf(((List) DAO.executeSelect(query)).get(0));
    }

    public List<String> copyProjection(int oldProjectionId, boolean isDiscountModule, List<String> contractList, List<String> companyList, List<String> rsList) {
        List<String> tempList = new ArrayList<String>();

        SessionDTO session = new SessionDTO();
        session = CommonUtils.attachSessionId(session);
        String relationShipBuilderSidQuery = "select CUST_RELATIONSHIP_BUILDER_SID, PROD_RELATIONSHIP_BUILDER_SID,FORECASTING_TYPE, CUSTOMER_HIERARCHY_SID from PROJECTION_MASTER where PROJECTION_MASTER_SID = " + oldProjectionId;
        Object[] projectionMasterRow = (Object[]) CompanyMasterLocalServiceUtil.executeQuery(relationShipBuilderSidQuery).get(0);

        LOGGER.info(" cust Rel Builder Sid " + String.valueOf(projectionMasterRow[0]));
        LOGGER.info(" prod Rel Builder Sid " + String.valueOf(projectionMasterRow[1]));

        List<String> relationshipBuilderSids = new ArrayList<String>();
        relationshipBuilderSids.add(String.valueOf(projectionMasterRow[0]));
        relationshipBuilderSids.add(String.valueOf(projectionMasterRow[1]));
        List<String> roleList = new ArrayList<String>();
        ProcessInstance processInstance = DSCalculationLogic.startWorkflow();             
        User userModel = null;
        try {
            userModel = UserLocalServiceUtil.getUser(Long.parseLong(session.getUserId()));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        // Please dont delet the below line as we are intializing BPM session in inner methods
        DSCalculationLogic.isValidWorkflowUser(userModel, roleList, processInstance.getId());
        int newProjectionId = cloneProjection(oldProjectionId,session.getUserId());

        LOGGER.info(" New Projection Id =====>>>>> " + newProjectionId);
        if (newProjectionId != 0) {
            if (newProjectionId != 0) {
                Long processInstanceId = processInstance.getId();
                try {
                    TaskSummary taskSummary = DSCalculationLogic.startAndCompleteTask(userModel, newProjectionId, processInstanceId);
                    processInstanceId = taskSummary.getProcessInstanceId();
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
            cloneCustomerAndProductHierarchy(oldProjectionId, newProjectionId, true, true);
            LOGGER.info("String.valueOf(projectionMasterRow[0])" + String.valueOf(projectionMasterRow[0]));
            LOGGER.info("relationshipBuilderSids.get(0)" + relationshipBuilderSids.get(0));
           
            if (insertIntoProjectionDetails(relationshipBuilderSids.get(0), newProjectionId)) {
                String marketType = StringUtils.EMPTY;
                Object[] inputs = new Object[4];
                inputs[0] = newProjectionId;
                inputs[1] = session.getUserId();
                inputs[2] = session.getSessionId();
                boolean flag = false;
                if ("Non Mandated".equals(String.valueOf(projectionMasterRow[2]))) {
                    if (isDiscountModule) {
                        callTableInsert(inputs, "PRC_NM_SALES_INSERT");
                    }
                    callTableInsert(inputs, "PRC_NM_DISCOUNT_INSERT");
                    callTableInsert(inputs, "PRC_NM_PPA_INSERT");
                    saveTempToMain(newProjectionId, session.getUserId(), session.getSessionId());
                    flag = true;
                } else if ("Mandated".equals(String.valueOf(projectionMasterRow[2]))) {
                    if (!StringUtils.EMPTY.equals(String.valueOf(projectionMasterRow[0])) && !Constants.ZEROSTRING.equals(String.valueOf(projectionMasterRow[0]))) {
                        marketType = getGenerateMarketValue(Integer.valueOf(String.valueOf(projectionMasterRow[0])));
                    }

                    String definedOrUDValue = StringUtils.EMPTY;
                    String definedValue = getDefinedValue(String.valueOf(projectionMasterRow[3]));
                    if ("User Defined".equalsIgnoreCase(definedValue)) {
                        definedOrUDValue = marketType;
                    } else {
                        definedOrUDValue = getHelperValue(marketType);
                    }
                    if (isDiscountModule) {
                        callTableInsert(inputs, "Prc_mandated_sales_insert");
                    }
                    Object[] suppInputs = {newProjectionId, session.getUserId(), definedOrUDValue, session.getSessionId()};
                    callDiscountTableInsert(suppInputs, "PRC_M_SUPP_INSERT");
                    callTableInsert(inputs, "PRC_M_SUPP_PROJECTION");
                    Object[] discInputs = {newProjectionId, session.getUserId(), "SPAP", session.getSessionId()};
                    callDiscountTableInsert(discInputs, "PRC_M_DISCOUNT_INSERT");
                    mandatedTempToMainSave(session.getUserId(), session.getSessionId(), newProjectionId);
                    flag = true;
                }
                if (flag) {
                    if (isDiscountModule) {
                        updateMainTableRemove(companyList, newProjectionId, contractList, String.valueOf(projectionMasterRow[2]), rsList);
                    }
                    LoadTabLogic loadTabLogic = new LoadTabLogic();
                    loadTabLogic.setForecastingType(newProjectionId);
                    tempList.add(swapForecastingType(loadTabLogic.forecatingType));
                    tempList.add(loadTabLogic.getProjectionName(newProjectionId));
                    tempList.add(String.valueOf(newProjectionId));
                    tempList.add("'\n new Projection created with forecasting type -" + tempList.get(0) + "\n and Projection Name - " + tempList.get(1) + " ");
                }

            } else {
                LOGGER.info(" Projection details insert failed");
            }

        } else {
            LOGGER.info(" Projection cloning error ");
        }
        LOGGER.info("Ending copyProjection ");
        return tempList;
    }

    public void updateMainTableRemove(List<String> companyMasterSids, int newProjectionId, List<String> contractList, String forecastingType, List<String> rsList) {
        StringBuilder salesQuery = new StringBuilder(StringUtils.EMPTY);
        StringBuilder discountQuery = new StringBuilder(StringUtils.EMPTY);
        StringBuilder rebateQuery = new StringBuilder(StringUtils.EMPTY);
        List queryList = new ArrayList();
        try {
            if ("Non Mandated".equalsIgnoreCase(forecastingType)) {
                salesQuery.append(CustomSQLUtil.get("nm.salesTableUpdate"));
                discountQuery.append(CustomSQLUtil.get("nm.removediscountupdate"));
                rebateQuery.append(CustomSQLUtil.get("nm.ppaTableUpdate"));
            } else if ("Mandated".equalsIgnoreCase(forecastingType)) {
                salesQuery.append(CustomSQLUtil.get("man.salesTableUpdate"));
                discountQuery.append(CustomSQLUtil.get("man.suppTableUpdate"));
                rebateQuery.append(CustomSQLUtil.get("man.discountTableUpdate"));
            }
            salesQuery.replace(salesQuery.indexOf("?CON"), salesQuery.indexOf("?CON") + 4, String.valueOf(contractList.get(0)));
            salesQuery.replace(salesQuery.indexOf("?COM"), salesQuery.indexOf("?COM") + 4, CommonUtils.CollectionToString(companyMasterSids, true));
            salesQuery.replace(salesQuery.indexOf("?PM"), salesQuery.indexOf("?PM") + 3, String.valueOf(newProjectionId));

            discountQuery.replace(discountQuery.indexOf("?CON"), discountQuery.indexOf("?CON") + 4, String.valueOf(contractList.get(0)));
            discountQuery.replace(discountQuery.indexOf("?COM"), discountQuery.indexOf("?COM") + 4, CommonUtils.CollectionToString(companyMasterSids, true));
            discountQuery.replace(discountQuery.indexOf("?PM"), discountQuery.indexOf("?PM") + 3, String.valueOf(newProjectionId));
            discountQuery.replace(discountQuery.indexOf("?RS"), discountQuery.indexOf("?RS") + 3, CommonUtils.CollectionToString(rsList, false));

            rebateQuery.replace(rebateQuery.indexOf("?CON"), rebateQuery.indexOf("?CON") + 4, String.valueOf(contractList.get(0)));
            rebateQuery.replace(rebateQuery.indexOf("?COM"), rebateQuery.indexOf("?COM") + 4, CommonUtils.CollectionToString(companyMasterSids, true));
            rebateQuery.replace(rebateQuery.indexOf("?PM"), rebateQuery.indexOf("?PM") + 3, String.valueOf(newProjectionId));

            salesQuery.replace(salesQuery.indexOf("?FIELD"), salesQuery.indexOf("?FIELD") + 6, "COMPANY_MASTER_SID");

            rebateQuery.replace(rebateQuery.indexOf("?FIELD"), rebateQuery.indexOf("?FIELD") + 6, "COMPANY_MASTER_SID");

            queryList.add(salesQuery.toString());
            queryList.add(rebateQuery.toString());
            CompanyMasterLocalServiceUtil.executeUpdateQuery(salesQuery.toString());
            CompanyMasterLocalServiceUtil.executeUpdateQuery(discountQuery.toString());
            CompanyMasterLocalServiceUtil.executeUpdateQuery(rebateQuery.toString());
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static void insertInputsBeforeTranfer(int fromProjectionId, int copyFromProjectionId, int toProjectionid, int copyToProjectionId,
            int fromContractSid, int toContractSid, String companies, String fromEndDate, String toStartDate, boolean transferFlag, String sessionId) {
        LOGGER.info("Entering insertInputsBeforeTranfer");
        String query = "INSERT INTO FUTURE_EVENTS_INPUT (FROM_PROJECTION, COPY_FROM_PROJECTION, TO_PROJECTION, COPY_TO_PROJECTION, FROM_CONTRACT_MASTER_SID, TO_CONTRACT_MASTER_SID, \n"
                + "COMPANY_MASTER_SID, FROM_END_DATE, TO_START_DATE, TRANSFER_FLAG, SESSION_ID) VALUES('" + fromProjectionId + "','" + copyFromProjectionId + "','" + toProjectionid + "'"
                + ",'" + copyToProjectionId + "','" + fromContractSid + "','" + toContractSid + "','" + companies + "','" + fromEndDate + "','" + toStartDate + "','" + transferFlag + "', '" + sessionId + "')";
        LOGGER.info("Exiting insertInputsBeforeTranfer");
        CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
    }

    public static List<String> getDiscriptionList(final String listType) throws SystemException {
        final List<String> helperList = new ArrayList<String>();
        LOGGER.info("Helper Table listType=" + listType);
        final DynamicQuery helperTableQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        helperTableQuery.add(RestrictionsFactoryUtil.like(Constants.LIST_NAME, listType));
        helperTableQuery.addOrder(OrderFactoryUtil.asc(Constants.DESCRIPTION));
        final List<HelperTable> list = HelperTableLocalServiceUtil.dynamicQuery(helperTableQuery);
        if (list
                != null) {
            for (HelperTable temp : list) {
                final HelperTable helperTable = (HelperTable) temp;
                helperList.add(helperTable.getDescription());
            }
        }

        LOGGER.info("Helper Table list size =" + helperList.size());
        return helperList;
    }

    public static String generateCustomerMappings(List<String> sourceCompanies, List<String> destinationCompanies) {
        String customerMapping = StringUtils.EMPTY;
        for (int i = 0; i < sourceCompanies.size(); i++) {
            customerMapping += String.valueOf(sourceCompanies.get(i)) + " - " + String.valueOf(destinationCompanies.get(i));
            if (i < sourceCompanies.size() - 1) {
                customerMapping += ",";
            }
        }
        return customerMapping;
    }

    private void updateProdHirarechy(int newProjectionId, int prodRelationshipId, List<String> itemList) {
        List input = new ArrayList();
        input.add(newProjectionId);
        input.add(prodRelationshipId);
        input.add(CommonUtils.getListToString(itemList));
        ItemQueries.itemUpdate(input, "Prod Hirarechy UPdate");
    }

    public static boolean callPromoteProcedure(String sessionId) {
        LOGGER.info("calling promoteProcedure");
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            if (datasource != null) {
                connection = datasource.getConnection();
            } else {
                LOGGER.info("Failed to lookup datasource.");
            }
            if (connection != null) {

                LOGGER.info("Got Connection " + connection.toString() + ", ");
                statement = connection.prepareCall("{call PRC_FE_PROMOTE_TP(" + sessionId + ")}");
                statement.execute();
            }
        } catch (Exception ex) {

            LOGGER.error(ex.getMessage());
            return false;
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        LOGGER.info("exiting promoteProcedure");
        return true;
    }

    private String getMarketType(String hierarchyDefinitionSid, String relationshipSid) {

        String marketType = StringUtils.EMPTY;
        String marketValue = StringUtils.EMPTY;
        if (!relationshipSid.isEmpty() && !Constants.ZEROSTRING.equals(relationshipSid)) {
            marketValue = getGenerateMarketValue(Integer.valueOf(relationshipSid));
        }

        String definedValue = getDefinedValue(hierarchyDefinitionSid);
        if ("User Defined".equalsIgnoreCase(definedValue)) {
            marketType = marketValue;
        } else {
            marketType = getHelperValue(marketValue);
        }

        return marketType;
    }

    private String swapForecastingType(final String oldForecastType) {
        String forecastType = StringUtils.EMPTY;
        if (oldForecastType.equals(Constants.IndicatorConstants.NON_MANDATED.getConstant())) {
            forecastType = "Commercial";
        } else if (oldForecastType.equals(Constants.IndicatorConstants.MANDATED.getConstant())) {
            forecastType = "Government";
        }
        return forecastType;
    }

    public static List<Object[]> getProjectionIdForPromoteCustomer(String sessionId, String userId) {
        LOGGER.info(" Inside getProjectionIdFor Promote Tp Submit ");
        int projectionId = 0;
        String query = "select PROJECTION_MASTER_SID,CONTRACT_MASTER_SID  from dbo.GCM_GLOBAL_DETAILS where  \n"
                + " USER_ID='" + userId + "' AND SESSION_ID='" + sessionId + "' and CHECK_RECORD=1 and \"OPERATION\" like 'Promote_TP_Submit' ";

        List<Object[]> list = CompanyMasterLocalServiceUtil.executeQuery(query);
        LOGGER.info(" exiting Promote Tp Submit Projection id =  " + projectionId);
        return list;

    }

    public String getRelationBuilderType(int oldProjectionId) {
        String relationType = "";
        try {
            List list = new ArrayList<>();
            list.add(oldProjectionId);
            List<Object[]> resultList = ItemQueries.getItemData(list, "getBuilderType", null);
            Object[] obj = resultList != null && !resultList.isEmpty() ? resultList.get(0) : new Object[1];
            relationType = obj[0] != null ? String.valueOf(obj[0]) : StringUtils.EMPTY;
            LOGGER.info("relationType ============================== " + relationType);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return relationType;
    }
     public int getInternalIds(ContractsDetailsDto parent){
     String Query = null;  
     if(parent.getCategory().equals("IFP")){
     Query="select IFP_CONTRACT_SID from dbo.IFP_CONTRACT where IFP_MODEL_SID="+parent.getInternalId()+";";
     }else if(parent.getCategory().equals("CFP")){
     Query="select CFP_CONTRACT_SID from dbo.CFP_CONTRACT where CFP_MODEL_SID="+parent.getInternalId()+";";
     }else if(parent.getCategory().equals("PS")){
     Query="select PS_CONTRACT_SID from dbo.PS_CONTRACT where PS_MODEL_SID="+parent.getInternalId()+";";
     }else{
     Query="select RS_CONTRACT_SID from dbo.RS_CONTRACT where RS_MODEL_SID="+parent.getInternalId()+";";
     }
     List <Object>results = new ArrayList<>();
     results= HelperTableLocalServiceUtil.executeSelectQuery(Query);
     return Integer.valueOf(String.valueOf(results.get(0)));
    }
     
     
     public List<String> copyTempProjection(int oldProjectionId, boolean isDiscountModule, List<String> contractList, List<String> companyList, List<String> rsList) {
        List<String> tempList = new ArrayList<String>();

        SessionDTO session = new SessionDTO();
        session = CommonUtils.attachSessionId(session);
        String relationShipBuilderSidQuery = "select CUST_RELATIONSHIP_BUILDER_SID, PROD_RELATIONSHIP_BUILDER_SID,FORECASTING_TYPE, CUSTOMER_HIERARCHY_SID from PROJECTION_MASTER where PROJECTION_MASTER_SID = " + oldProjectionId;
        Object[] projectionMasterRow = (Object[]) CompanyMasterLocalServiceUtil.executeQuery(relationShipBuilderSidQuery).get(0);

        LOGGER.info(" cust Rel Builder Sid " + String.valueOf(projectionMasterRow[0]));
        LOGGER.info(" prod Rel Builder Sid " + String.valueOf(projectionMasterRow[1]));

        List<String> relationshipBuilderSids = new ArrayList<String>();
        relationshipBuilderSids.add(String.valueOf(projectionMasterRow[0]));
        relationshipBuilderSids.add(String.valueOf(projectionMasterRow[1]));
        List<String> roleList = new ArrayList<String>();
        int count = 1;
        ProcessInstance processInstance = null;
        for (int i = 0; i < count && i < 5; i++) {
            try {
                processInstance = DSCalculationLogic.startGCMWorkflow();
                if (processInstance != null) {
                    count = 0;
                    break;
                }
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
                count++;
            }
        }         
        User userModel = null;
        try {
            userModel = UserLocalServiceUtil.getUser(Long.parseLong(session.getUserId()));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        // Please dont delet the below line as we are intializing BPM session in inner methods
        DSCalculationLogic.isValidWorkflowUser(userModel, roleList, processInstance.getId());
        int newProjectionId = cloneProjection(oldProjectionId, session.getUserId());

        LOGGER.info(" New Projection Id =====>>>>> " + newProjectionId);
        if (newProjectionId != 0) {
            if (newProjectionId != 0) {
                Long processInstanceId = processInstance.getId();
                try {
                    TaskSummary taskSummary = DSCalculationLogic.startAndCompleteTask(userModel, newProjectionId, processInstanceId);
                    processInstanceId = taskSummary.getProcessInstanceId();
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
            cloneCustomerAndProductHierarchy(oldProjectionId, newProjectionId, true, true);
            LOGGER.info("String.valueOf(projectionMasterRow[0])" + String.valueOf(projectionMasterRow[0]));
            LOGGER.info("relationshipBuilderSids.get(0)" + relationshipBuilderSids.get(0));

            if (insertIntoProjectionDetails(relationshipBuilderSids.get(0), newProjectionId)) {
                String marketType = StringUtils.EMPTY;
                Object[] inputs = new Object[4];
                inputs[0] = newProjectionId;
                inputs[1] = session.getUserId();
                inputs[2] = session.getSessionId();
                boolean flag = false;
                if ("Non Mandated".equals(String.valueOf(projectionMasterRow[2]))) {
                    if (isDiscountModule) {
                        callTableInsert(inputs, "PRC_NM_SALES_INSERT");
                    }
                    callTableInsert(inputs, "PRC_NM_DISCOUNT_INSERT");
                    callTableInsert(inputs, "PRC_NM_PPA_INSERT");
                    saveTempToMain(newProjectionId, session.getUserId(), session.getSessionId());
                    flag = true;
                } else if ("Mandated".equals(String.valueOf(projectionMasterRow[2]))) {
                    if (!StringUtils.EMPTY.equals(String.valueOf(projectionMasterRow[0])) && !Constants.ZEROSTRING.equals(String.valueOf(projectionMasterRow[0]))) {
                        marketType = getGenerateMarketValue(Integer.valueOf(String.valueOf(projectionMasterRow[0])));
                    }

                    String definedOrUDValue = StringUtils.EMPTY;
                    String definedValue = getDefinedValue(String.valueOf(projectionMasterRow[3]));
                    if ("User Defined".equalsIgnoreCase(definedValue)) {
                        definedOrUDValue = marketType;
                    } else {
                        definedOrUDValue = getHelperValue(marketType);
                    }
                    if (isDiscountModule) {
                        callTableInsert(inputs, "Prc_mandated_sales_insert");
                    }
                    Object[] suppInputs = {newProjectionId, session.getUserId(), definedOrUDValue, session.getSessionId()};
                    callDiscountTableInsert(suppInputs, "PRC_M_SUPP_INSERT");
                    callTableInsert(inputs, "PRC_M_SUPP_PROJECTION");
                    Object[] discInputs = {newProjectionId, session.getUserId(), "SPAP", session.getSessionId()};
                    callDiscountTableInsert(discInputs, "PRC_M_DISCOUNT_INSERT");
                    mandatedTempToMainSave(session.getUserId(), session.getSessionId(), newProjectionId);
                    flag = true;
                }
                if (flag) {
                    if (isDiscountModule) {
                        updateMainTableRemove(companyList, newProjectionId, contractList, String.valueOf(projectionMasterRow[2]), rsList);
                    }
                    LoadTabLogic loadTabLogic = new LoadTabLogic();
                    loadTabLogic.setForecastingType(newProjectionId);
                    tempList.add(swapForecastingType(loadTabLogic.forecatingType));
                    tempList.add(loadTabLogic.getProjectionName(newProjectionId));
                    tempList.add(String.valueOf(newProjectionId));
                    tempList.add("'\n new Projection created with forecasting type -" + tempList.get(0) + "\n and Projection Name - " + tempList.get(1) + " ");
                }

            } else {
                LOGGER.info(" Projection details insert failed");
            }

        } else {
            LOGGER.info(" Projection cloning error ");
        }
        LOGGER.info("Ending copyProjection ");
        return tempList;
    }
        }

