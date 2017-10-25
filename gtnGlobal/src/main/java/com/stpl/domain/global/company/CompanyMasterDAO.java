/**
 * 
 */
package com.stpl.domain.global.company;

import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import java.util.List;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.CompanyIdentifier;
import com.stpl.app.model.CompanyQualifier;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.CompanyTradeClass;
import com.stpl.app.model.CompanyParentDetails;
import java.util.Map;
import com.stpl.app.model.Udcs;

/** 
 * <!-- begin-UML-doc -->
 * Defines&nbsp;DAO&nbsp;operations&nbsp;for&nbsp;CompanySearchLogic.<br><br>@author&nbsp;shrihariharan
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface CompanyMasterDAO {
	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;number&nbsp;of&nbsp;rows&nbsp;that&nbsp;match&nbsp;the&nbsp;dynamic&nbsp;query.<br><br>@param&nbsp;query&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;CompanyMaster<br>@return&nbsp;count<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	int getCompanyMasterCountByQuery(DynamicQuery query)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;a&nbsp;range&nbsp;of&nbsp;all&nbsp;the&nbsp;company&nbsp;masters.<br><br>@param&nbsp;startIndex&nbsp;-&nbsp;startLimit<br>@param&nbsp;endIndex&nbsp;-&nbsp;endLimit<br>@return&nbsp;list&nbsp;of&nbsp;CompanyMaster<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param startIndex
	 * @param endIndex
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<CompanyMaster> getCompanyMasterByLimit(int startIndex, int endIndex)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;CompanyMaster<br>@return&nbsp;list&nbsp;of&nbsp;Company&nbsp;Master<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<CompanyMaster> getCompanyMasterList(DynamicQuery query)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;list&nbsp;of&nbsp;company&nbsp;Master&nbsp;List.<br><br>@param&nbsp;companyId&nbsp;the&nbsp;company&nbsp;id<br>@param&nbsp;companyNo&nbsp;the&nbsp;company&nbsp;no<br>@param&nbsp;companyName&nbsp;the&nbsp;company&nbsp;name<br>@param&nbsp;companyStatus&nbsp;the&nbsp;company&nbsp;status<br>@param&nbsp;companyType&nbsp;the&nbsp;company&nbsp;type<br>@param&nbsp;tradeClass&nbsp;the&nbsp;trade&nbsp;class<br>@param&nbsp;qualifierId&nbsp;the&nbsp;qualifier&nbsp;id<br>@param&nbsp;itemIdentifier&nbsp;the&nbsp;item&nbsp;identifier<br>@return&nbsp;list&nbsp;of&nbsp;Company&nbsp;Master<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param companyId
	 * @param companyNo
	 * @param companyName
	 * @param companyStatus
	 * @param companyType
	 * @param tradeClass
	 * @param qualifierId
	 * @param itemIdentifier
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getCompanyMasterListByQualifierId(String companyId, String companyNo,
			String companyName, String companyStatus, String companyType,
			String tradeClass, int qualifierId, String itemIdentifier, String orderByColumn, Boolean sortOrder)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;a&nbsp;range&nbsp;of&nbsp;all&nbsp;the&nbsp;company&nbsp;masters.<br><br>@param&nbsp;companySystemId&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;primary&nbsp;key<br>@return&nbsp;list&nbsp;of&nbsp;Company&nbsp;Master<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param companySystemId
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getCompanyTradeClassBySystemId(int companySystemId)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;company&nbsp;master&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;companySystemId&nbsp;-&nbsp;primary&nbsp;key<br>@return&nbsp;CompanyMaster&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param companySystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CompanyMaster getCompanyMasterBySystemId(int companySystemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;list&nbsp;of&nbsp;CompanyCrtIdentifier.<br><br>@param&nbsp;companySystemId&nbsp;the&nbsp;company&nbsp;system&nbsp;id<br>@return&nbsp;list&nbsp;of&nbsp;CompanyCrtIdentifier<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param companySystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<CompanyIdentifier> getCompanyIdentifierByCompanySystemId(
			int companySystemId) throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;company&nbsp;crt&nbsp;qualifier&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;companyCrtQualifierId&nbsp;-&nbsp;primary&nbsp;key<br>@return&nbsp;CompanyCrtQualifier&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param companyCrtQualifierId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CompanyQualifier getCompanyCrtQualifierByQualifierId(
			int companyCrtQualifierId) throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;company&nbsp;master&nbsp;to&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the&nbsp;appropriate<br>model&nbsp;listeners.<br><br>@param&nbsp;companyMaster&nbsp;-&nbsp;Modal&nbsp;Object<br>@return&nbsp;saved&nbsp;CompanyMaster&nbsp;modal&nbsp;Object<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param companyMaster
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CompanyMaster saveCompanyMaster(CompanyMaster companyMaster)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;company&nbsp;crt&nbsp;identifier&nbsp;to&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;companyCrtIdentifier&nbsp;-&nbsp;Modal&nbsp;Object<br>@return&nbsp;saved&nbsp;companyCrtIdentifier&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param companyCrtIdentifier
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CompanyIdentifier saveCompanyCrtIdentifier(
			CompanyIdentifier companyCrtIdentifier) throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;CompanyCrtQualifier&nbsp;according&nbsp;to&nbsp;the&nbsp;companyCrtQualifierName.<br><br>@param&nbsp;companyCrtQualifierName&nbsp;the&nbsp;company&nbsp;crt&nbsp;qualifier&nbsp;name<br>@return&nbsp;CompanyCrtQualifier&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param companyCrtQualifierName
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CompanyQualifier getCompanyCrtQualifierByQualifierName(
			String companyCrtQualifierName) throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;company&nbsp;master&nbsp;from&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;companySystemId&nbsp;-&nbsp;primary&nbsp;key<br>@return&nbsp;deleted&nbsp;CompanyMaster&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param companySystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CompanyMaster deleteCompanyMasterBySystemId(int companySystemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;company&nbsp;crt&nbsp;identifier&nbsp;from&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;crtIdentifierSystemId&nbsp;-&nbsp;primary&nbsp;key<br>@return&nbsp;deleted&nbsp;CompanyCrtIdentifier&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param crtIdentifierSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CompanyIdentifier deleteCompanyCrtIdentifierByCrtIdentifierSystemId(
			int crtIdentifierSystemId) throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;company&nbsp;trade&nbsp;class&nbsp;from&nbsp;database&nbsp;by&nbsp;company&nbsp;System&nbsp;ID.<br><br>@param&nbsp;companySystemId&nbsp;the&nbsp;company&nbsp;system&nbsp;id<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param companySystemId
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	void deleteCompanyTradeClassForUpdate(int companySystemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;CompanyCrtQualifier<br>@return&nbsp;list&nbsp;of&nbsp;CompanyCrtQualifier<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<CompanyQualifier> getCompanyCrtQualifierList(DynamicQuery query)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;list&nbsp;of&nbsp;HelperTable&nbsp;by&nbsp;list&nbsp;name.<br><br>@param&nbsp;listName&nbsp;the&nbsp;list&nbsp;name<br>@return&nbsp;list&nbsp;of&nbsp;helperTable<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param listName
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<HelperTable> getHelperTableDetailsByListName(String listName)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;HelperTable<br>@return&nbsp;list&nbsp;of&nbsp;HelperTable<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<HelperTable> getHelperTableDetailsList(DynamicQuery query)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;a&nbsp;range&nbsp;of&nbsp;all&nbsp;the&nbsp;company&nbsp;crt&nbsp;qualifiers.<br><br>@param&nbsp;startindex&nbsp;-&nbsp;start&nbsp;limit<br>@param&nbsp;endIndex&nbsp;-&nbsp;end&nbsp;limit<br>@return&nbsp;list&nbsp;of&nbsp;CompanyCrtQualifier<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param startindex
	 * @param endIndex
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<CompanyQualifier> getCompanyCrtQualifiersByLimit(int startindex,
			int endIndex) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;company&nbsp;crt&nbsp;qualifier&nbsp;from&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;qualifierId&nbsp;the&nbsp;qualifier&nbsp;id<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param qualifierId
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	void deleteCompanyCrtQualifierByQualifierId(int qualifierId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;company&nbsp;crt&nbsp;qualifier&nbsp;to&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;companyCrtQualifier&nbsp;the&nbsp;company&nbsp;crt&nbsp;qualifier<br>@return&nbsp;saved&nbsp;CompanyCrtQualifier&nbsp;modal&nbsp;object<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param companyCrtQualifier
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CompanyQualifier saveCompanyCrtQualifier(
			CompanyQualifier companyCrtQualifier) throws PortalException,
			SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Updates&nbsp;the&nbsp;company&nbsp;crt&nbsp;qualifier&nbsp;in&nbsp;the&nbsp;database&nbsp;or&nbsp;adds&nbsp;it&nbsp;if&nbsp;it&nbsp;does<br>not&nbsp;yet&nbsp;exist.&nbsp;Also&nbsp;notifies&nbsp;the&nbsp;appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;companyCrtQualifier&nbsp;the&nbsp;company&nbsp;crt&nbsp;qualifier<br>@return&nbsp;Updated&nbsp;CompanyCrtQualifier&nbsp;modal&nbsp;object<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param companyCrtQualifier
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CompanyQualifier updateCompanyCrtQualifier(
			CompanyQualifier companyCrtQualifier) throws PortalException,
			SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;CompanyCrtIdentifier<br>@return&nbsp;-&nbsp;list&nbsp;CompanyCrtIdentifier<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<CompanyIdentifier> getCompanyIdentifierlist(DynamicQuery query)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Updates&nbsp;the&nbsp;company&nbsp;master&nbsp;in&nbsp;the&nbsp;database&nbsp;or&nbsp;adds&nbsp;it&nbsp;if&nbsp;it&nbsp;does&nbsp;not&nbsp;yet<br>exist.&nbsp;Also&nbsp;notifies&nbsp;the&nbsp;appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;companyMaster&nbsp;the&nbsp;company&nbsp;master<br>@return&nbsp;CompanyMaster&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param companyMaster
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CompanyMaster updateCompanyMaster(CompanyMaster companyMaster)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;company&nbsp;crt&nbsp;identifier&nbsp;from&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;companyCrtIdentifier&nbsp;the&nbsp;company&nbsp;crt&nbsp;identifier<br>@return&nbsp;deleted&nbsp;CompanyCrtIdentifier&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param companyCrtIdentifier
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CompanyIdentifier deleteCompanyCrtIdentifier(
			CompanyIdentifier companyCrtIdentifier) throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;company&nbsp;trade&nbsp;class&nbsp;list.<br><br>@param&nbsp;query&nbsp;the&nbsp;query<br>@return&nbsp;the&nbsp;company&nbsp;trade&nbsp;class&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<CompanyTradeClass> getCompanyTradeClassList(DynamicQuery query)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;company&nbsp;parent&nbsp;details.<br><br>@param&nbsp;query&nbsp;the&nbsp;query<br>@return&nbsp;the&nbsp;company&nbsp;parent&nbsp;details<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<CompanyParentDetails> getCompanyParentDetails(DynamicQuery query)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * getting&nbsp;count&nbsp;and&nbsp;results&nbsp;for&nbsp;CompanyCrtQualifierName&nbsp;using&nbsp;lazy<br>container.<br><br>@param&nbsp;query&nbsp;the&nbsp;query<br>@return&nbsp;the&nbsp;company&nbsp;crt&nbsp;qualifier&nbsp;name&nbsp;list<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getCompanyCrtQualifierNameList(DynamicQuery query)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * updated&nbsp;the&nbsp;CompanyCrtIdentifier<br>@param&nbsp;companyCrtIdentifier<br>@return<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException&nbsp;
	 * <!-- end-UML-doc -->
	 * @param companyCrtIdentifier
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CompanyIdentifier updateCompanyCrtIdentifier(
			CompanyIdentifier companyCrtIdentifier) throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param type
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	Map<Integer, Udcs> getUDCS(String type) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param udcsSid
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	Udcs getUDCS(int udcsSid) throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param udcs
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	Udcs saveUdcs(Udcs udcs) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param udcs
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	Udcs updateUdcs(Udcs udcs) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int getCompanyMasterTotalCount() throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int getCompanyCrtQualifierTotalCount() throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List getHelperTableDetailsByListName() throws SystemException,
			PortalException;
        
     /**
     * To get the count of companyIdentifier associated with given qualifierID
     *
     * @param query
     * @return List
     * @throws PortalException
     * @throws SystemException
     */
    List<CompanyIdentifier> getCompanyIdentifierList(DynamicQuery query) throws PortalException, SystemException;
    
    public List findCompanyMasterV1(final String companyId, final String companyNo, final String companyName, final String companyStatus,final String companyType,final String companyCategory,final String companyGroup, final String tradeClass, final int qualifierId, final String itemIdentifier, String orderByColumn, Boolean sortOrder, Object index, Object next,Map<String, Object> parameters) throws PortalException,SystemException ;
}