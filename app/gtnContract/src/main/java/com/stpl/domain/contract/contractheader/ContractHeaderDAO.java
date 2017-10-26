/**
 * 
 */
package com.stpl.domain.contract.contractheader;

import java.util.List;

import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.ContractAliasMaster;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

/** 
 * <!-- begin-UML-doc -->
 * Defines&nbsp;DAO&nbsp;Operation&nbsp;for&nbsp;Contract&nbsp;Header&nbsp;Logic.<br><br>@author&nbsp;sibi
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface ContractHeaderDAO {
	/** 
	 * <!-- begin-UML-doc -->
	 * Retrieves&nbsp;the&nbsp;results&nbsp;from&nbsp;the&nbsp;Contract&nbsp;Master&nbsp;Table&nbsp;and&nbsp;returns&nbsp;as&nbsp;list.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;contract&nbsp;master&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<ContractMaster> getContractMasterList(DynamicQuery dynamicQuery)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;contract&nbsp;master&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;contract&nbsp;master&nbsp;by&nbsp;id<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ContractMaster getContractMasterById(int systemId) throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;ContractMaster&nbsp;to&nbsp;the&nbsp;Database.<br><br>@param&nbsp;contractMaster&nbsp;the&nbsp;contract&nbsp;master<br>@return&nbsp;the&nbsp;contract&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param contractMaster
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ContractMaster addContractMaster(ContractMaster contractMaster)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Updates&nbsp;the&nbsp;ContractMaster&nbsp;in&nbsp;the&nbsp;Database.<br><br>@param&nbsp;contractMaster&nbsp;the&nbsp;contract&nbsp;master<br>@return&nbsp;the&nbsp;contract&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param contractMaster
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ContractMaster updateContractMaster(ContractMaster contractMaster)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * deletes&nbsp;the&nbsp;ContractMaster&nbsp;form&nbsp;Database.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;contract&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ContractMaster deleteContractMaster(int systemId) throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;number&nbsp;of&nbsp;rows&nbsp;that&nbsp;match&nbsp;the&nbsp;dynamic&nbsp;query.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;long<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	long dynamicQueryCount(DynamicQuery dynamicQuery) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Retrieves&nbsp;the&nbsp;results&nbsp;from&nbsp;the&nbsp;Company&nbsp;Master&nbsp;Table&nbsp;and&nbsp;returns&nbsp;as&nbsp;list.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@param&nbsp;start&nbsp;the&nbsp;start<br>@param&nbsp;end&nbsp;the&nbsp;end<br>@return&nbsp;the&nbsp;company&nbsp;master&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @param start
	 * @param end
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<CompanyMaster> getCompanyMasterList(DynamicQuery dynamicQuery,
			int start, int end) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Retrieves&nbsp;the&nbsp;results&nbsp;from&nbsp;the&nbsp;Company&nbsp;Master&nbsp;Table&nbsp;and&nbsp;returns&nbsp;as&nbsp;list.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;company&nbsp;master&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<CompanyMaster> getCompanyMasterList(DynamicQuery dynamicQuery)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;company&nbsp;master&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;company&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CompanyMaster getCompanyMaster(int systemId) throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Retrieves&nbsp;the&nbsp;results&nbsp;from&nbsp;the&nbsp;Helper&nbsp;Table&nbsp;and&nbsp;returns&nbsp;as&nbsp;list.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;helper&nbsp;table&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<HelperTable> getHelperTableList(DynamicQuery dynamicQuery)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;contract&nbsp;master&nbsp;alias&nbsp;from&nbsp;Database&nbsp;with&nbsp;systemId.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<ContractAliasMaster> findByContractSystemId(int systemId)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;contract&nbsp;alias&nbsp;master&nbsp;from&nbsp;the&nbsp;database.<br><br>@param&nbsp;contractAliasMaster&nbsp;the&nbsp;contract&nbsp;alias&nbsp;master<br>@return&nbsp;the&nbsp;contract&nbsp;alias&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param contractAliasMaster
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ContractAliasMaster deleteContractAliasMaster(
			ContractAliasMaster contractAliasMaster) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;contract&nbsp;alias&nbsp;master&nbsp;from&nbsp;the&nbsp;database.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;contract&nbsp;alias&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ContractAliasMaster deleteContractAliasMaster(int systemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;contract&nbsp;alias&nbsp;master&nbsp;to&nbsp;the&nbsp;database.<br><br>@param&nbsp;contractAliasMaster&nbsp;the&nbsp;contract&nbsp;alias&nbsp;master<br>@return&nbsp;the&nbsp;contract&nbsp;alias&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param contractAliasMaster
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ContractAliasMaster addContractAliasMaster(
			ContractAliasMaster contractAliasMaster) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Updates&nbsp;the&nbsp;HelperTable&nbsp;and&nbsp;returns&nbsp;the&nbsp;updated&nbsp;HelperTable&nbsp;model&nbsp;object.<br><br>@param&nbsp;helperTable&nbsp;-&nbsp;HelperTable<br>@return&nbsp;updated&nbsp;HelperTable&nbsp;model&nbsp;object.<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param helperTable
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	HelperTable updateHelperTable(HelperTable helperTable)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int getContractMasterCount() throws SystemException;
        
        /**
         * Getting Contract Master data to check the process status for delete
         * 
         * @param systemId
         * @return
         * @throws SystemException
         * @throws PortalException 
         */
        ContractMaster getContractMaster(int systemId) throws SystemException,
			PortalException;
        
}