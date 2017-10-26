/**
 * 
 */
package com.stpl.domain.contract.contractdashboard;

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
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

/** 
 * <!-- begin-UML-doc -->
 * Defines&nbsp;DAO&nbsp;Operation&nbsp;for&nbsp;DashboardLogic.<br><br>@author&nbsp;sibi
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface DashboardDAO {
	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;CfpDetailsAttached&nbsp;from&nbsp;Database.<br><br>@param&nbsp;contractSystemId&nbsp;the&nbsp;contract&nbsp;system&nbsp;id<br>@param&nbsp;companyFamilyPlanSystemId&nbsp;the&nbsp;company&nbsp;family&nbsp;plan&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param contractSystemId
	 * @param companyFamilyPlanSystemId
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<CfpContractDetails> findByCFPDetails(int contractSystemId,
			int companyFamilyPlanSystemId) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<CfpContractDetails> cfpDetailsDynamicQuery(DynamicQuery dynamicQuery)
			throws SystemException;
        
        List<ImtdCfpDetails> tempCfpDetailsDynamicQuery(DynamicQuery dynamicQuery)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;cfp&nbsp;details&nbsp;attached&nbsp;from&nbsp;the&nbsp;database.<br><br>@param&nbsp;cfpDetailsAttached&nbsp;the&nbsp;cfp&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;cfp&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param cfpDetailsAttached
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CfpContractDetails deleteCfpDetailsAttached(
			CfpContractDetails cfpDetailsAttached) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;cfp&nbsp;details&nbsp;attached&nbsp;to&nbsp;the&nbsp;database.<br><br>@param&nbsp;cfpDetailsAttached&nbsp;the&nbsp;cfp&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;cfp&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param cfpDetailsAttached
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CfpContractDetails addCfpDetailsAttached(
			CfpContractDetails cfpDetailsAttached) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Updates&nbsp;the&nbsp;cfp&nbsp;details&nbsp;attached&nbsp;in&nbsp;the&nbsp;database&nbsp;or&nbsp;adds&nbsp;it&nbsp;if&nbsp;it&nbsp;does<br>not&nbsp;yet&nbsp;exist.<br><br>@param&nbsp;cfpDetailsAttached&nbsp;the&nbsp;cfp&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;cfp&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param cfpDetailsAttached
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CfpContractDetails updateCfpDetailsAttached(
			CfpContractDetails cfpDetailsAttached) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;company&nbsp;master&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;companySystemId&nbsp;the&nbsp;company&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;company&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param companySystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CompanyMaster getCompanyMaster(int companySystemId) throws SystemException,
			PortalException;

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
	 * Returns&nbsp;ContractAliasMaster&nbsp;with&nbsp;system&nbsp;systemId.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
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
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List contractMasterDynamicQuery(DynamicQuery dynamicQuery)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;contract&nbsp;master&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;contract&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ContractMaster getContractMaster(int systemId) throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;contractPrice&nbsp;from&nbsp;Database&nbsp;with<br>contractSystemId,cfpId,ifpId,psId.<br><br>@param&nbsp;contractSystemId&nbsp;the&nbsp;contract&nbsp;system&nbsp;id<br>@param&nbsp;cfpId&nbsp;the&nbsp;cfp&nbsp;id<br>@param&nbsp;ifpId&nbsp;the&nbsp;ifp&nbsp;id<br>@param&nbsp;psId&nbsp;the&nbsp;ps&nbsp;id<br>@return&nbsp;the&nbsp;contract&nbsp;price&nbsp;info<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param contractSystemId
	 * @param cfpId
	 * @param ifpId
	 * @param psId
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getContractPriceInfo(int contractSystemId, int cfpId, int ifpId,
			int psId) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Updates&nbsp;the&nbsp;contract&nbsp;master&nbsp;in&nbsp;the&nbsp;database&nbsp;or&nbsp;adds&nbsp;it&nbsp;if&nbsp;it&nbsp;does&nbsp;not&nbsp;yet<br>exist.<br><br>@param&nbsp;contractMaster&nbsp;the&nbsp;contract&nbsp;master<br>@return&nbsp;the&nbsp;contract&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
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
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List ifpDetailsDynamicQuery(DynamicQuery dynamicQuery)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;ifp&nbsp;details&nbsp;attached&nbsp;from&nbsp;the&nbsp;database.<br><br>@param&nbsp;ifpDetailsAttached&nbsp;the&nbsp;ifp&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;ifp&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param ifpDetailsAttached
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	IfpContractDetails deleteIfpDetailsAttached(
			IfpContractDetails ifpDetailsAttached) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;ifp&nbsp;details&nbsp;attached&nbsp;to&nbsp;the&nbsp;database.<br><br>@param&nbsp;ifpDetailsAttached&nbsp;the&nbsp;ifp&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;ifp&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param ifpDetailsAttached
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	IfpContractDetails addIfpDetailsAttached(
			IfpContractDetails ifpDetailsAttached) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Updates&nbsp;the&nbsp;ifp&nbsp;details&nbsp;attached&nbsp;in&nbsp;the&nbsp;database&nbsp;or&nbsp;adds&nbsp;it&nbsp;if&nbsp;it&nbsp;does<br>not&nbsp;yet&nbsp;exist.<br><br>@param&nbsp;ifpDetailsAttached&nbsp;the&nbsp;ifp&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;ifp&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param ifpDetailsAttached
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	IfpContractDetails updateIfpDetailsAttached(
			IfpContractDetails ifpDetailsAttached) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;item&nbsp;master&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;item&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemMaster getItemMaster(int systemId) throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List psDetailsDynamicQuery(DynamicQuery dynamicQuery)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;ps&nbsp;details&nbsp;attached&nbsp;from&nbsp;the&nbsp;database.<br><br>@param&nbsp;psDetailsAttached&nbsp;the&nbsp;ps&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;ps&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param psDetailsAttached
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	PsContractDetails deletePsDetailsAttached(
			PsContractDetails psDetailsAttached) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;ps&nbsp;details&nbsp;attached&nbsp;to&nbsp;the&nbsp;database.<br><br>@param&nbsp;psDetailsAttached&nbsp;the&nbsp;ps&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;ps&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param psDetailsAttached
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	PsContractDetails addPsDetailsAttached(PsContractDetails psDetailsAttached)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Updates&nbsp;the&nbsp;ps&nbsp;details&nbsp;attached&nbsp;in&nbsp;the&nbsp;database&nbsp;or&nbsp;adds&nbsp;it&nbsp;if&nbsp;it&nbsp;does&nbsp;not<br>yet&nbsp;exist.<br><br>@param&nbsp;psDetailsAttached&nbsp;the&nbsp;ps&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;ps&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param psDetailsAttached
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	PsContractDetails updatePsDetailsAttached(
			PsContractDetails psDetailsAttached) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List rsDetailsDynamicQuery(DynamicQuery dynamicQuery)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;rs&nbsp;details&nbsp;attached&nbsp;from&nbsp;the&nbsp;database.<br><br>@param&nbsp;rsDetailsAttached&nbsp;the&nbsp;rs&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;rs&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param rsDetailsAttached
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	RsContractDetails deleteRsDetailsAttached(
			RsContractDetails rsDetailsAttached) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;rs&nbsp;details&nbsp;attached&nbsp;to&nbsp;the&nbsp;database.<br><br>@param&nbsp;rsDetailsAttached&nbsp;the&nbsp;rs&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;rs&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param rsDetailsAttached
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	RsContractDetails addRsDetailsAttached(RsContractDetails rsDetailsAttached)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Updates&nbsp;the&nbsp;rs&nbsp;details&nbsp;attached&nbsp;in&nbsp;the&nbsp;database&nbsp;or&nbsp;adds&nbsp;it&nbsp;if&nbsp;it&nbsp;does&nbsp;not<br>yet&nbsp;exist.<br><br>@param&nbsp;rsDetailsAttached&nbsp;the&nbsp;rs&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;rs&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param rsDetailsAttached
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	RsContractDetails updateRsDetailsAttached(
			RsContractDetails rsDetailsAttached) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List rsMasterDynamicQuery(DynamicQuery dynamicQuery) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Updates&nbsp;the&nbsp;rs&nbsp;master&nbsp;attached&nbsp;in&nbsp;the&nbsp;database&nbsp;or&nbsp;adds&nbsp;it&nbsp;if&nbsp;it&nbsp;does&nbsp;not<br>yet&nbsp;exist.<br><br>@param&nbsp;rsMasterAttached&nbsp;the&nbsp;rs&nbsp;master&nbsp;attached<br>@return&nbsp;the&nbsp;rs&nbsp;master&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param rsMasterAttached
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	RsContract updateRsMasterAttached(RsContract rsMasterAttached)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;a&nbsp;list&nbsp;of&nbsp;Helper&nbsp;Table.<br><br>@param&nbsp;listName&nbsp;the&nbsp;list&nbsp;name<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param listName
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<HelperTable> findByHelperTableDetails(String listName)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List itemPricingDynamicQuery(DynamicQuery dynamicQuery)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;PriceScheduleDetails&nbsp;table&nbsp;and&nbsp;returns<br>the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;to&nbsp;get&nbsp;the&nbsp;data&nbsp;from&nbsp;PriceScheduleDetails&nbsp;table<br>@return&nbsp;list&nbsp;of&nbsp;data&nbsp;retrieved&nbsp;from&nbsp;the&nbsp;PriceScheduleDetails&nbsp;table<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List priceScheduleDetailsDynamicQuery(DynamicQuery dynamicQuery)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;o&nbsp;get&nbsp;the&nbsp;data&nbsp;from&nbsp;RebateScheduleDetails<br>@return&nbsp;list&nbsp;of&nbsp;data&nbsp;retrieved&nbsp;from&nbsp;the&nbsp;RebateScheduleDetails<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List rebateScheduleDetailsDynamicQuery(DynamicQuery dynamicQuery)
			throws SystemException;
	
	/**
	 * To execute Custom SQL query
	 * @param query
	 * @return
	 */
	public List executeSelectQuery(String query);
        
        
        /** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;ItemPricingQualifier<br>@return&nbsp;list&nbsp;of&nbsp;ItemPricingQualifier<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getItemPricingTypeList(DynamicQuery query) throws SystemException;

}