/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.dao.impl;

import com.stpl.app.gcm.discount.dao.ContractDetailsDAO;
import com.stpl.app.model.CfpContract;
import com.stpl.app.model.CfpContractDetails;
import com.stpl.app.model.CfpModel;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.IfpContract;
import com.stpl.app.model.IfpContractDetails;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.PsContract;
import com.stpl.app.model.PsContractDetails;
import com.stpl.app.model.PsModel;
import com.stpl.app.model.RsContract;
import com.stpl.app.model.RsContractDetails;
import com.stpl.app.model.RsModel;
import com.stpl.app.service.CfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.CfpContractLocalServiceUtil;
import com.stpl.app.service.CfpDetailsLocalServiceUtil;
import com.stpl.app.service.CfpModelLocalServiceUtil;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.app.service.IfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.IfpContractLocalServiceUtil;
import com.stpl.app.service.IfpDetailsLocalServiceUtil;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.PsContractDetailsLocalServiceUtil;
import com.stpl.app.service.PsContractLocalServiceUtil;
import com.stpl.app.service.PsDetailsLocalServiceUtil;
import com.stpl.app.service.PsModelLocalServiceUtil;
import com.stpl.app.service.RsContractDetailsLocalServiceUtil;
import com.stpl.app.service.RsContractLocalServiceUtil;
import com.stpl.app.service.RsDetailsLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author santanukumar
 */
public class ContractDetailsDaoImpl implements ContractDetailsDAO {

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery it filter by contractSystemId
     * @return List of data from CfpDetailsAttached table
     * @throws SystemException
     */
    @Override
    public List cfpDetailsDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        return CfpContractDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Adds the cfp details attached to the CompanyFamilyplanDetails table .
     *
     * @param cfpDetailsAttached to add in CompanyFamilyplanDetails table
     * @return added row from CompanyFamilyplanDetails table
     * @throws SystemException
     */
    @Override
    public CfpContractDetails addCfpDetailsAttached(final CfpContractDetails cfpDetailsAttached) throws SystemException {
        return CfpContractDetailsLocalServiceUtil.addCfpContractDetails(cfpDetailsAttached);
    }

    /**
     * Deletes the cfp details from the CompanyFamilyplanDetails table based on
     * contract system id.
     *
     * @param cfpDetailsAttached is to remove from CompanyFamilyplanDetails
     * table
     * @return the row that was removed from CompanyFamilyplanDetails table
     * @throws SystemException
     */
    @Override
    public CfpContractDetails deleteCfpDetailsAttached(final CfpContractDetails cfpDetailsAttached) throws SystemException {
        return CfpContractDetailsLocalServiceUtil.deleteCfpContractDetails(cfpDetailsAttached);
    }

    /**
     * Performs a dynamic query on the CfpDetailsAttached table and returns the
     * matching rows.
     *
     * @param dynamicQuery based on contractSystemId in CfpDetailsAttached table
     * @return row of data corresponding to the contractSystemId in
     * CfpMasterAttached table
     * @throws SystemException
     */
    @Override
    public List cfpMasterDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        return CfpContractLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Adds the cfp master attached to the CfpMasterAttached table.
     *
     * @param cfpMasterAttached to add in CfpMasterAttached table
     * @return list of data based on the query from CfpMasterAttached table
     * @throws SystemException
     */
    @Override
    public CfpContract addCfpMasterAttached(final CfpContract cfpMasterAttached) throws SystemException {
        return CfpContractLocalServiceUtil.addCfpContract(cfpMasterAttached);
    }

    /**
     * Deletes the cfp master attached from the CfpMasterAttached table based on
     * the given contract system id..
     *
     * @param cfpMasterAttached to delete from CfpMasterAttached table
     * @return removed data from CfpMasterAttached table
     * @throws SystemException
     */
    @Override
    public CfpContract deleteCfpMasterAttached(final CfpContract cfpMasterAttached) throws SystemException {
        return CfpContractLocalServiceUtil.deleteCfpContract(cfpMasterAttached);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows
     * based on companyFamilyPlanSystemId.
     *
     * @param dynamicQuery to fetch data based on companyFamilyPlanSystemId
     * table
     * @return list of data retrived form CompanyFamilyplanDetails table
     * @throws SystemException
     */
    @Override
    public List companyFamilyplanDetailsDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        return CfpDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Performs a dynamic query on the CompanyFamilyplanMaster table and returns
     * the matching rows.
     *
     * @param dynamicQuery to execute based on companyFamilyPlanNo from
     * CompanyFamilyplanMaster table
     * @return list of data retrived from the CompanyFamilyplanMastertable
     * @throws SystemException
     */
    @Override
    public List companyFamilyplanMasterDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        return CfpModelLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Returns the company family plan master with the primary key.
     *
     * @param id it is like internal id
     * @return list of data from company plan master table
     * @throws SystemException
     * @throws PortalException
     */
    @Override
    public CfpModel getCompanyFamilyplanMaster(final int systemId) throws SystemException, PortalException {
        return CfpModelLocalServiceUtil.getCfpModel(systemId);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery based on contractSystemId from IfpDetailsAttached
     * table
     * @return list of data from IfpDetailsAttached table
     * @throws SystemException
     */
    @Override
    public List ifpDetailsDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        return IfpContractDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Deletes the ifp details attached from the database.
     *
     * @param ifpDetailsAttached to delete from table IfpDetailsAttached table
     * @return deleted row from the table IfpDetailsAttached table
     * @throws SystemException
     */
    @Override
    public IfpContractDetails deleteIfpDetailsAttached(final IfpContractDetails ifpDetailsAttached) throws SystemException {
        return IfpContractDetailsLocalServiceUtil.deleteIfpContractDetails(ifpDetailsAttached);
    }

    /**
     * Adds the ifp details attached to the database.
     *
     * @param ifpDetailsAttached to add to IfpDetailsAttached table
     * @return added value from IfpDetailsAttached table
     * @throws SystemException
     */
    @Override
    public IfpContractDetails addIfpDetailsAttached(final IfpContractDetails ifpDetailsAttached) throws SystemException {
        return IfpContractDetailsLocalServiceUtil.addIfpContractDetails(ifpDetailsAttached);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery to retrieve data from the table IfpDetailsAttached
     * table
     * @return list of data retrieved from IfpDetailsAttached table
     * @throws SystemException
     */
    @Override
    public List ifpMasterDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        return IfpContractLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Deletes the ifp master attached from the database based on given
     * condition in query.
     *
     * @param ifpMasterAttached to delete from IfpMasterAttached table
     * @return deleted row of IfpMasterAttached table
     * @throws SystemException
     */
    @Override
    public IfpContract deleteIfpMasterAttached(final IfpContract ifpMasterAttached) throws SystemException {
        return IfpContractLocalServiceUtil.deleteIfpContract(ifpMasterAttached);
    }

    /**
     * Adds the ifp master attached to the database .
     *
     * @param ifpMasterAttached to add to IfpMasterAttached table
     * @return added value to IfpMasterAttached table
     * @throws SystemException
     */
    @Override
    public IfpContract addIfpMasterAttached(final IfpContract ifpMasterAttached) throws SystemException {
        return IfpContractLocalServiceUtil.addIfpContract(ifpMasterAttached);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery to retrieve the data from ItemFamilyplanDetails table
     * @return list of data retrieved from ItemFamilyplanDetails table * @throws
     * SystemException
     */
    @Override
    public List itemFamilyPlanDetailsDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        return IfpDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery to retrieve the data from ItemFamilyplanMaster table.
     * @return list of data retrieved from the ItemFamilyplanMaster table
     * @throws SystemException
     */
    @Override
    public List itemFamilyPlanMasterDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        return IfpModelLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Returns the item family plan master with the primary key.
     *
     * @param id to retrieve the data from ItemFamilyplanMaster table
     * @return ItemFamilyPlanMasterbased on id it is like internal is
     * @throws SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
     */
    @Override
    public IfpModel getItemFamilyPlanMaster(final int systemId) throws SystemException, PortalException {
        return IfpModelLocalServiceUtil.getIfpModel(systemId);
    }

    /**
     * Performs a dynamic query on the PriceScheduleDetails table and returns
     * the matching rows.
     *
     * @param dynamicQuery to get the data from PriceScheduleDetails table
     * @return list of data retrieved from the PriceScheduleDetails table
     * @throws SystemException
     */
    @Override
    public List priceScheduleDetailsDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        return PsDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Performs a dynamic query on the PriceScheduleMaster table and returns the
     * matching rows.
     *
     * @param dynamicQuery to get the data from PriceScheduleMaster table
     * @return list of data retrieved from the PriceScheduleMaster table
     * @throws SystemException
     */
    @Override
    public List priceScheduleMasterDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        return PsDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Returns the price schedule master with the primary key.
     *
     * @param id to retrieve the data from PriceScheduleMaster
     * @return ItemFamilyPlanMasterbased on id like internal id
     * @throws SystemException
     * @throws PortalException
     */
    @Override
    public PsModel getPriceScheduleMaster(final int systemId) throws SystemException, PortalException {
        return PsModelLocalServiceUtil.getPsModel(systemId);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery to get the data from PsDetailsAttached table
     * @return list of data retrieved from the PsDetailsAttached table
     * @throws SystemException
     */
    @Override
    public List psDetailsDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        return PsContractDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Deletes the ps details attached from the PsDetailsAttached.
     *
     * @param psDetailsAttached is to remove from PsDetailsAttached
     * @return deleted psDetails that was removed from PsDetailsAttached
     * @throws SystemException
     */
    @Override
    public PsContractDetails deletePsDetailsAttached(final PsContractDetails psDetailsAttached) throws SystemException {
        return PsContractDetailsLocalServiceUtil.deletePsContractDetails(psDetailsAttached);
    }

    /**
     * Adds the ps details attached to the database.
     *
     * @param psDetailsAttached this data is to be add to the database
     * @return added data to database
     * @throws SystemException
     */
    @Override
    public PsContractDetails addPsDetailsAttached(final PsContractDetails psDetailsAttached) throws SystemException {
        return PsContractDetailsLocalServiceUtil.addPsContractDetails(psDetailsAttached);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery to get the data from PsDetailsAttached
     * @return list of data retrieved from the PsDetailsAttached
     * @throws SystemException
     */
    @Override
    public List psMasterDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        return PsContractLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Deletes the ps master attached from the PsMasterAttached.
     *
     * @param psMasterAttached is to remove from PsMasterAttached
     * @return deleted psDetails that was removed from PsMasterAttached
     * @throws SystemException
     */
    @Override
    public PsContract deletePsMasterAttached(final PsContract psMasterAttached) throws SystemException {
        return PsContractLocalServiceUtil.deletePsContract(psMasterAttached);
    }

    /**
     * Adds the ps master attached to the database.
     *
     * @param psMasterAttached this data is to be add to the PsMasterAttached
     * @return added data to PsMasterAttached
     * @throws SystemException
     */
    @Override
    public PsContract addPsMasterAttached(final PsContract psMasterAttached) throws SystemException {
        return PsContractLocalServiceUtil.addPsContract(psMasterAttached);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery o get the data from RebateScheduleDetails
     * @return list of data retrieved from the RebateScheduleDetails
     * @throws SystemException
     */
    @Override
    public List rebateScheduleDetailsDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        return RsDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery to get the data from RebateScheduleMaster
     * @return list of data retrieved from the RebateScheduleMaster
     * @throws SystemException
     */
    @Override
    public List rebateScheduleMasterDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        return RsModelLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Returns the rebate schedule master with the primary key.
     *
     * @param id - rebateSchedule Id .it is like InternalId
     * @return row of
     * @throws SystemException
     * @throws PortalException
     */
    @Override
    public RsModel getRebateScheduleMaster(final int systemId) throws SystemException, PortalException {
        return RsModelLocalServiceUtil.getRsModel(systemId);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery o get the data from RsDetailsAttached
     * @return list of data retrieved from the RsDetailsAttached
     * @throws SystemException
     */
    @Override
    public List rsDetailsDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        return RsContractDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Deletes the rs details attached from the database.
     *
     * @param rsDetailsAttached to delete from RsDetailsAttached
     * @return deleted row of IfpMasterAttached from RsDetailsAttached table
     * @throws SystemException
     */
    @Override
    public RsContractDetails deleteRsDetailsAttached(final RsContractDetails rsDetailsAttached) throws SystemException {
        return RsContractDetailsLocalServiceUtil.deleteRsContractDetails(rsDetailsAttached);
    }

    /**
     * Adds the rs details attached to the database.
     *
     * @param rsDetailsAttached this data is to be add to the RsDetailsAttached
     * @return added data to RsDetailsAttached table
     * @throws SystemException
     */
    @Override
    public RsContractDetails addRsDetailsAttached(final RsContractDetails rsDetailsAttached) throws SystemException {
        return RsContractDetailsLocalServiceUtil.addRsContractDetails(rsDetailsAttached);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQueryto get the data from RsMasterAttached table
     * @return list of data retrieved from the RsMasterAttached table
     * @throws SystemException
     */
    @Override
    public List rsMasterDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        return RsContractLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Deletes the rs master attached from the RsMasterAttached table.
     *
     * @param rsMasterAttached to delete from RsMasterAttached table
     * @return deleted row of IfpMasterAttached from RsMasterAttached table
     * @throws SystemException
     */
    @Override
    public RsContract deleteRsMasterAttached(final RsContract rsMasterAttached) throws SystemException {
        return RsContractLocalServiceUtil.deleteRsContract(rsMasterAttached);
    }

    /**
     * Adds the rs master attached to the RsMasterAttached table.
     *
     * @param rsMasterAttached this data is to be add to the RsMasterAttached
     * @return added data to RsMasterAttached table
     * @throws SystemException
     */
    @Override
    public RsContract addRsMasterAttached(final RsContract rsMasterAttached) throws SystemException {
        return RsContractLocalServiceUtil.addRsContract(rsMasterAttached);
    }

    /**
     * Performs a dynamic query on the ContractMaster table and returns the
     * matching rows.
     *
     * @param dynamicQuery to get the data from ContractMaster table
     * @return list of data retrieved from the ContractMaster table
     * @throws SystemException
     */
    @Override
    public List contractMasterDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        return ContractMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Returns the contract master with the primary key.
     *
     * @param id to retrieve data based on the id from contract master
     * @return data retrieved from contract master table
     * @throws SystemException
     * @throws PortalException
     */
    @Override
    public ContractMaster getContractMaster(final int systemId) throws SystemException, PortalException {
        return ContractMasterLocalServiceUtil.getContractMaster(systemId);
    }

    /**
     * Updates the contract master in the ContractMaster or adds it if it does
     * not yet exist.
     *
     * @param contractMaster to add or update the ContractMaster table
     * @return updated or added ContractMaster row
     * @throws SystemException
     */
    @Override
    public ContractMaster updateContractMaster(final ContractMaster contractMaster) throws SystemException {
        return ContractMasterLocalServiceUtil.updateContractMaster(contractMaster);
    }

    /**
     * Returns the number of rows that match the dynamic query from
     * ContractMaster table.
     *
     * @param dynamicQuery to get the count of data present from ContractMaster
     * table
     * @return no of data
     * @throws SystemException
     */
    @Override
    public long contractMasterDynamicQueryCount(final DynamicQuery dynamicQuery) throws SystemException {
        return ContractMasterLocalServiceUtil.dynamicQueryCount(dynamicQuery);
    }

    /**
     *
     *
     * @return contract master total row count from ContractMaster table
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    @Override
    public int getContractMasterCount() throws SystemException {
        return ContractMasterLocalServiceUtil.getContractMastersCount();
    }

    /**
     * Updates the Cfp Master Attached in the CfpMasterAttached or adds it if it
     * does not yet exist.
     *
     * @param cfpMasterAttached to add or update the CfpMasterAttached table
     * @return updated or added CfpMasterAttached row
     * @throws SystemException
     */
    @Override
    public CfpContract updateCfpMasterAttached(final CfpContract cfpMasterAttached) throws SystemException {
        return CfpContractLocalServiceUtil.updateCfpContract(cfpMasterAttached);
    }

    /**
     * Updates the Cfp Details Attached in the CfpDetailsAttached or adds it if
     * it does not yet exist.
     *
     * @param cfpDetailsAttached to add or update the CfpDetailsAttached table
     * @return updated or added CfpDetailsAttached row
     * @throws SystemException
     */
    @Override
    public CfpContractDetails updateCfpDetailsAttached(final CfpContractDetails cfpDetailsAttached) throws SystemException {
        return CfpContractDetailsLocalServiceUtil.updateCfpContractDetails(cfpDetailsAttached);
    }

    /**
     * Updates the Ifp Master Attached in the IfpMasterAttached or adds it if it
     * does not yet exist.
     *
     * @param ifpMasterAttached to add or update the IfpMasterAttached table
     * @return updated or added IfpMasterAttached row
     * @throws SystemException
     */
    @Override
    public IfpContract updateIfpMasterAttached(final IfpContract ifpMasterAttached) throws SystemException {
        return IfpContractLocalServiceUtil.updateIfpContract(ifpMasterAttached);
    }

    /**
     * Updates the Ifp Details Attached in the IfpDetailsAttached or adds it if
     * it does not yet exist.
     *
     * @param ifpDetailsAttached to add or update the IfpDetailsAttached table
     * @return updated or added IfpDetailsAttached row
     * @throws SystemException
     */
    @Override
    public IfpContractDetails updateIfpDetailsAttached(final IfpContractDetails ifpDetailsAttached) throws SystemException {
        return IfpContractDetailsLocalServiceUtil.updateIfpContractDetails(ifpDetailsAttached);
    }

    /**
     * Updates the Ps Master Attached in the PsMasterAttached or adds it if it
     * does not yet exist.
     *
     * @param psMasterAttached to add or update the PsMasterAttached table
     * @return updated or added PsMasterAttached row
     * @throws SystemException
     */
    @Override
    public PsContract updatePsMasterAttached(final PsContract psMasterAttached) throws SystemException {
        return PsContractLocalServiceUtil.updatePsContract(psMasterAttached);
    }

    /**
     * Updates the Ps Details Attached in the PsDetailsAttached or adds it if it
     * does not yet exist.
     *
     * @param psDetailsAttached to add or update the PsDetailsAttached table
     * @return updated or added PsDetailsAttached row
     * @throws SystemException
     */
    @Override
    public PsContractDetails updatePsDetailsAttached(final PsContractDetails psDetailsAttached) throws SystemException {
        return PsContractDetailsLocalServiceUtil.updatePsContractDetails(psDetailsAttached);
    }

    /**
     * Updates the Rs Master Attached in the RsMasterAttached or adds it if it
     * does not yet exist.
     *
     * @param rsMasterAttached to add or update the RsMasterAttached table
     * @return updated or added RsMasterAttached row
     * @throws SystemException
     */
    @Override
    public RsContract updateRsMasterAttached(final RsContract rsMasterAttached) throws SystemException {
        return RsContractLocalServiceUtil.updateRsContract(rsMasterAttached);
    }

    /**
     * Updates the Rs Details Attached in the RsDetailsAttached or adds it if it
     * does not yet exist.
     *
     * @param rsDetailsAttached to add or update the RsDetailsAttached table
     * @return updated or added RsDetailsAttached row
     * @throws SystemException
     */
    @Override
    public RsContractDetails updateRsDetailsAttached(final RsContractDetails rsDetailsAttached) throws SystemException {
        return RsContractDetailsLocalServiceUtil.updateRsContractDetails(rsDetailsAttached);
    }
}
