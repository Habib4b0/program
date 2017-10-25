/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dao.impl;


import java.util.List;

import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.ContractAliasMaster;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ContractAliasMasterLocalServiceUtil;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.domain.contract.contractheader.ContractHeaderDAO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * DAO Operation for Contract Header Logic.
 *
 * @author sibi
 */
public class ContractHeaderLogicDAOImpl implements ContractHeaderDAO {

	/**
	 * Retrieves the results from the Contract Master Table and returns as list.
	 *
	 * @param dynamicQuery
	 *            - To retrieve the Contract Master.
	 * @return List of Contract Master.
	 * @throws com.stpl.portal.kernel.exception.SystemException
	 */
	public List<ContractMaster> getContractMasterList(final DynamicQuery dynamicQuery) throws SystemException {
		return ContractMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

	/**
	 * Retrieves the results from the Helper Table and returns as list.
	 *
	 * @param dynamicQuery
	 * @return
	 * @throws com.stpl.portal.kernel.exception.SystemException
	 */
	public List<HelperTable> getHelperTableList(final DynamicQuery dynamicQuery) throws SystemException {
		return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

	/**
	 * Retrieves the results from the Company Master Table and returns as list.
	 * 
	 * @param dynamicQuery
	 *            - Retrieves the company Master based on company Type
	 * @param start
	 *            - Starting index of company Master.
	 * @param end
	 *            - Ending index of company Master.
	 * @return
	 * @throws com.stpl.portal.kernel.exception.SystemException
	 */
	public List<CompanyMaster> getCompanyMasterList(final DynamicQuery dynamicQuery, final int start, final int end) throws SystemException {
		return CompanyMasterLocalServiceUtil.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Retrieves the results from the Company Master Table and returns as list.
	 *
	 * @param dynamicQuery
	 * @return
	 * @throws com.stpl.portal.kernel.exception.SystemException
	 */
	public List<CompanyMaster> getCompanyMasterList(final DynamicQuery dynamicQuery) throws SystemException {
		return CompanyMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the contract master alias from Database with id.
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
	 * Deletes the contract alias master from the database.
	 *
	 * @param id
	 * @return
	 * @throws SystemException
	 * @throws com.stpl.portal.kernel.exception.PortalException
	 */
	public ContractAliasMaster deleteContractAliasMaster(final int systemId) throws SystemException, PortalException {
		return ContractAliasMasterLocalServiceUtil.deleteContractAliasMaster(systemId);
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
	 * Returns the contract master with the primary key.
	 *
	 * @param id
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 */
	public ContractMaster getContractMasterById(final int systemId) throws SystemException, PortalException {
		return ContractMasterLocalServiceUtil.getContractMaster(systemId);
	}

	/**
	 * Adds the ContractMaster to the Database
	 *
	 * @param contractMaster
	 * @return
	 * @throws SystemException
	 */
	public ContractMaster addContractMaster(final ContractMaster contractMaster) throws SystemException {
		return ContractMasterLocalServiceUtil.addContractMaster(contractMaster);
	}

	/**
	 * Updates the ContractMaster in the Database.
	 *
	 * @param contractMaster
	 * @return
	 * @throws SystemException
	 */
	public ContractMaster updateContractMaster(final ContractMaster contractMaster) throws SystemException {
		return ContractMasterLocalServiceUtil.updateContractMaster(contractMaster);
	}

	/**
	 * deletes the ContractMaster form Database.
	 *
	 * @param id
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 */
	public ContractMaster deleteContractMaster(final int systemId) throws SystemException, PortalException {
		return ContractMasterLocalServiceUtil.deleteContractMaster(systemId);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 */
	public long dynamicQueryCount(final DynamicQuery dynamicQuery) throws SystemException {
		return ContractMasterLocalServiceUtil.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of contract masters.
	 *
	 * @return
	 * @throws SystemException
	 */
	public int getContractMasterCount() throws SystemException {
		return ContractMasterLocalServiceUtil.getContractMastersCount();
	}

	/**
	 * Returns the company master with the primary key.
	 *
	 * @param id
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 */
	public CompanyMaster getCompanyMaster(final int systemId) throws SystemException, PortalException {
		return CompanyMasterLocalServiceUtil.getCompanyMaster(systemId);
	}

	/**
	 * Updates the HelperTable and returns the updated HelperTable model object.
	 *
	 * @param helperTable
	 *            - HelperTable
	 * @return updated HelperTable model object.
	 * @throws SystemException
	 */
	public HelperTable updateHelperTable(final HelperTable helperTable) throws SystemException {
		return HelperTableLocalServiceUtil.updateHelperTable(helperTable);
	}

        /**
	 * Returns the company master with the primary key.
	 *
	 * @param id
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 */
	public ContractMaster getContractMaster(final int systemId) throws SystemException, PortalException {
		return ContractMasterLocalServiceUtil.getContractMaster(systemId);
	}
}
