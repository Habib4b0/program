/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.dao.impl;


import java.util.List;

import com.stpl.app.model.CfpContractDetails;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.ContractAliasMaster;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.IfpContractDetails;
import com.stpl.app.model.ImtdCfpDetails;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.PsContractDetails;
import com.stpl.app.model.RsContract;
import com.stpl.app.model.RsContractDetails;
import com.stpl.app.service.CfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ContractAliasMasterLocalServiceUtil;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.IfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.ImtdCfpDetailsLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.app.service.ItemPricingQualifierLocalServiceUtil;
import com.stpl.app.service.PsContractDetailsLocalServiceUtil;
import com.stpl.app.service.PsContractLocalServiceUtil;
import com.stpl.app.service.PsDetailsLocalServiceUtil;
import com.stpl.app.service.RsContractDetailsLocalServiceUtil;
import com.stpl.app.service.RsContractLocalServiceUtil;
import com.stpl.app.service.RsDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.CfpContractDetailsUtil;
import com.stpl.domain.contract.contractdashboard.DashboardDAO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

/**
 *
 * @author sibi
 */
public class DashboardLogicDAOImpl implements DashboardDAO {

	/**
	 * Returns the CfpDetailsAttached from Database.
	 * 
	 * @param contractSystemId
	 * @param companyFamilyPlanSystemId
	 * @return
	 * @throws SystemException
	 */
	public List<CfpContractDetails> findByCFPDetails(final int contractSystemId, final int companyFamilyPlanSystemId) throws SystemException {
		return CfpContractDetailsUtil.findByCFPDetails(contractSystemId, companyFamilyPlanSystemId);
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 * 
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 */
	public List<CfpContractDetails> cfpDetailsDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
		return CfpContractDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
	}
        /**
	 * Performs a dynamic query on the database and returns the matching rows.
	 * 
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 */
	public List<ImtdCfpDetails> tempCfpDetailsDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
		return ImtdCfpDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

	/**
	 * Deletes the cfp details attached from the database.
	 * 
	 * @param cfpDetailsAttached
	 * @return
	 * @throws SystemException
	 */
	public CfpContractDetails deleteCfpDetailsAttached(final CfpContractDetails cfpDetailsAttached) throws SystemException {
		return CfpContractDetailsLocalServiceUtil.deleteCfpContractDetails(cfpDetailsAttached);
	}

	/**
	 * Adds the cfp details attached to the database.
	 * 
	 * @param cfpDetailsAttached
	 * @return
	 * @throws SystemException
	 */
	public CfpContractDetails addCfpDetailsAttached(final CfpContractDetails cfpDetailsAttached) throws SystemException {
		return CfpContractDetailsLocalServiceUtil.addCfpContractDetails(cfpDetailsAttached);
	}

	/**
	 * Updates the cfp details attached in the database or adds it if it does
	 * not yet exist.
	 * 
	 * @param cfpDetailsAttached
	 * @return
	 * @throws SystemException
	 */
	public CfpContractDetails updateCfpDetailsAttached(final CfpContractDetails cfpDetailsAttached) throws SystemException {
		return CfpContractDetailsLocalServiceUtil.updateCfpContractDetails(cfpDetailsAttached);
	}

	/**
	 * Returns the company master with the primary key.
	 * 
	 * @param companySystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 */
	public CompanyMaster getCompanyMaster(final int companySystemId) throws SystemException, PortalException {
		return CompanyMasterLocalServiceUtil.getCompanyMaster(companySystemId);
	}

	/**
	 * Adds the contract alias master to the database.
	 * 
	 * @param contractAliasMaster
	 * @return
	 * @throws SystemException
	 */
	public ContractAliasMaster addContractAliasMaster(final ContractAliasMaster contractAliasMaster) throws SystemException {
		return ContractAliasMasterLocalServiceUtil.addContractAliasMaster(contractAliasMaster);
	}

	/**
	 * Returns ContractAliasMaster with system id.
	 * 
	 * @param id
	 * @return
	 * @throws SystemException
	 */
	public List<ContractAliasMaster> findByContractSystemId(final int systemId) throws SystemException {
		return ContractAliasMasterLocalServiceUtil.findByContractSystemId(systemId);
	}

	/**
	 * Deletes the contract alias master from the database.
	 * 
	 * @param contractAliasMaster
	 * @return
	 * @throws SystemException
	 */
	public ContractAliasMaster deleteContractAliasMaster(final ContractAliasMaster contractAliasMaster) throws SystemException {
		return ContractAliasMasterLocalServiceUtil.deleteContractAliasMaster(contractAliasMaster);
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 * 
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 */
	public List contractMasterDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
		return ContractMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the contract master with the primary key.
	 * 
	 * @param id
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 */
	public ContractMaster getContractMaster(final int systemId) throws SystemException, PortalException {
		return ContractMasterLocalServiceUtil.getContractMaster(systemId);
	}

	/**
	 * Returns contractPrice from Database with
	 * contractSystemId,cfpId,ifpId,psId.
	 * 
	 * @param contractSystemId
	 * @param cfpId
	 * @param ifpId
	 * @param psId
	 * @throws SystemException
	 */
	public List getContractPriceInfo(final int contractSystemId, final int cfpId, final int ifpId, final int psId) throws SystemException {
		return ContractMasterLocalServiceUtil.getContractPriceInfo(contractSystemId, cfpId, ifpId, psId);
	}

	/**
	 * Updates the contract master in the database or adds it if it does not yet
	 * exist.
	 * 
	 * @param contractMaster
	 * @return
	 * @throws SystemException
	 */
	public ContractMaster updateContractMaster(final ContractMaster contractMaster) throws SystemException {
		return ContractMasterLocalServiceUtil.updateContractMaster(contractMaster);
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 * 
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 */
	public List ifpDetailsDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
		return IfpContractDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

	/**
	 * Deletes the ifp details attached from the database.
	 * 
	 * @param ifpDetailsAttached
	 * @return
	 * @throws SystemException
	 */
	public IfpContractDetails deleteIfpDetailsAttached(final IfpContractDetails ifpDetailsAttached) throws SystemException {
		return IfpContractDetailsLocalServiceUtil.deleteIfpContractDetails(ifpDetailsAttached);
	}

	/**
	 * Adds the ifp details attached to the database.
	 * 
	 * @param ifpDetailsAttached
	 * @return
	 * @throws SystemException
	 */
	public IfpContractDetails addIfpDetailsAttached(final IfpContractDetails ifpDetailsAttached) throws SystemException {
		return IfpContractDetailsLocalServiceUtil.addIfpContractDetails(ifpDetailsAttached);
	}

	/**
	 * Updates the ifp details attached in the database or adds it if it does
	 * not yet exist.
	 * 
	 * @param ifpDetailsAttached
	 * @return
	 * @throws SystemException
	 */
	public IfpContractDetails updateIfpDetailsAttached(final IfpContractDetails ifpDetailsAttached) throws SystemException {
		return IfpContractDetailsLocalServiceUtil.updateIfpContractDetails(ifpDetailsAttached);
	}

	/**
	 * Returns the item master with the primary key.
	 * 
	 * @param id
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 */
	public ItemMaster getItemMaster(final int systemId) throws SystemException, PortalException {
		return ItemMasterLocalServiceUtil.getItemMaster(systemId);
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 * 
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 */
	public List psDetailsDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
		return PsContractLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

	/**
	 * Deletes the ps details attached from the database.
	 * 
	 * @param psDetailsAttached
	 * @return
	 * @throws SystemException
	 */
	public PsContractDetails deletePsDetailsAttached(final PsContractDetails psDetailsAttached) throws SystemException {
		return PsContractDetailsLocalServiceUtil.deletePsContractDetails(psDetailsAttached);
	}

	/**
	 * Adds the ps details attached to the database.
	 * 
	 * @param psDetailsAttached
	 * @return
	 * @throws SystemException
	 */
	public PsContractDetails addPsDetailsAttached(final PsContractDetails psDetailsAttached) throws SystemException {
		return PsContractDetailsLocalServiceUtil.addPsContractDetails(psDetailsAttached);
	}

	/**
	 * Updates the ps details attached in the database or adds it if it does not
	 * yet exist.
	 * 
	 * @param psDetailsAttached
	 * @return
	 * @throws SystemException
	 */
	public PsContractDetails updatePsDetailsAttached(final PsContractDetails psDetailsAttached) throws SystemException {
		return PsContractDetailsLocalServiceUtil.updatePsContractDetails(psDetailsAttached);
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 * 
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 */
	public List rsDetailsDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
		return RsContractDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

	/**
	 * Deletes the rs details attached from the database.
	 * 
	 * @param rsDetailsAttached
	 * @return
	 * @throws SystemException
	 */
	public RsContractDetails deleteRsDetailsAttached(final RsContractDetails rsDetailsAttached) throws SystemException {
		return RsContractDetailsLocalServiceUtil.deleteRsContractDetails(rsDetailsAttached);
	}

	/**
	 * Adds the rs details attached to the database.
	 * 
	 * @param rsDetailsAttached
	 * @return
	 * @throws SystemException
	 */
	public RsContractDetails addRsDetailsAttached(final RsContractDetails rsDetailsAttached) throws SystemException {
		return RsContractDetailsLocalServiceUtil.addRsContractDetails(rsDetailsAttached);
	}

	/**
	 * Updates the rs details attached in the database or adds it if it does not
	 * yet exist.
	 * 
	 * @param rsDetailsAttached
	 * @return
	 * @throws SystemException
	 */
	public RsContractDetails updateRsDetailsAttached(final RsContractDetails rsDetailsAttached) throws SystemException {
		return RsContractDetailsLocalServiceUtil.updateRsContractDetails(rsDetailsAttached);
	}


	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 * 
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 */
	public List rsMasterDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
		return RsContractLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

	/**
	 * Updates the rs master attached in the database or adds it if it does not
	 * yet exist.
	 * 
	 * @param rsMasterAttached
	 * @return
	 * @throws SystemException
	 */
	public RsContract updateRsMasterAttached(final RsContract rsMasterAttached) throws SystemException {
		return RsContractLocalServiceUtil.updateRsContract(rsMasterAttached);
	}

	/**
	 * Returns a list of Helper Table.
	 * 
	 * @param listName
	 * @return
	 * @throws SystemException
	 */
	public List<HelperTable> findByHelperTableDetails(final String listName) throws SystemException {
		return HelperTableLocalServiceUtil.findByHelperTableDetails(listName);
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 * 
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 */
	public List itemPricingDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
		return ItemPricingQualifierLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the PriceScheduleDetails table and returns
	 * the matching rows.
	 * 
	 * @param dynamicQuery
	 *            to get the data from PriceScheduleDetails table
	 * @return list of data retrieved from the PriceScheduleDetails table
	 * @throws SystemException
	 */
	public List priceScheduleDetailsDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
		return PsDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery
	 *            to get the data from RebateScheduleDetails
	 * @return list of data retrieved from the RebateScheduleDetails
	 * @throws SystemException
	 */
	public List rebateScheduleDetailsDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
		return RsDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
	}
	
	/**
	 * To execute Custom SQL select query
	 * @param query
	 * @return
	 */
	public List executeSelectQuery(String query){
		return HelperTableLocalServiceUtil.executeSelectQuery(query);
	}
	
	/**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of ItemPricingQualifier
     * @return list of ItemPricingQualifier
     * @throws SystemException 
     */
    public List getItemPricingTypeList(final DynamicQuery query) throws SystemException {
       return ItemPricingQualifierLocalServiceUtil.dynamicQuery(query);
    }
    }
