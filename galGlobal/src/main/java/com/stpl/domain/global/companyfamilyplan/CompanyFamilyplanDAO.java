/**
 * 
 */
package com.stpl.domain.global.companyfamilyplan;

import com.stpl.app.model.CfpContract;
import com.stpl.app.model.CfpDetails;
import com.stpl.app.model.CfpModel;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ImtdCfpDetails;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/** 
 * <!-- begin-UML-doc -->
 * Defines&nbsp;DAO&nbsp;operations&nbsp;for&nbsp;CompanyFamilyPlan&nbsp;Logic<br><br>@author&nbsp;shrihariharan
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface CompanyFamilyplanDAO {
	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;Dynamic&nbsp;query&nbsp;of&nbsp;Company&nbsp;Familyplan&nbsp;Master<br>@return&nbsp;list&nbsp;of&nbsp;CompanyFamilyplanMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<CfpModel> getCompanyFamilyplanMasterList(DynamicQuery query)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;a&nbsp;range&nbsp;of&nbsp;all&nbsp;the&nbsp;company&nbsp;familyplan&nbsp;masters<br><br>@param&nbsp;startIndex<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;start&nbsp;limit<br>@param&nbsp;endIndex<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;end&nbsp;limit<br>@return&nbsp;list&nbsp;of&nbsp;CompanyFamilyplanMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param startIndex
	 * @param endIndex
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<CfpModel> getCompanyFamilyplanMasterByLimit(int startIndex,
			int endIndex) throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;number&nbsp;of&nbsp;rows&nbsp;that&nbsp;match&nbsp;the&nbsp;dynamic&nbsp;query.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;CompanyFamilyplanMaster<br>@return&nbsp;count<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	int getCompanyFamilyplanMasterQueryCount(DynamicQuery query)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;CompanyMaster<br>@return&nbsp;list&nbsp;of&nbsp;CompanyMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<CompanyMaster> getCompanyMasterList(DynamicQuery query)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;company&nbsp;familyplan&nbsp;master&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;companyFamilyplanSystemId<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;primary&nbsp;key<br>@return&nbsp;CompanyFamilyplanMaster<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param companyFamilyplanSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CfpModel getCompanyFamilyplanMasterBySystemId(int companyFamilyplanSystemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;company&nbsp;familyplan&nbsp;master&nbsp;to&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;companyFamilyplanMaster<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;Modal&nbsp;Object<br>@return&nbsp;CompanyFamilyplanMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param companyFamilyplanMaster
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CfpModel saveCompanyFamilyplanMaster(CfpModel companyFamilyplanMaster)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;company&nbsp;familyplan&nbsp;details&nbsp;to&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;details<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;Modal&nbsp;Object<br>@return&nbsp;CompanyFamilyplanDetails<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param details
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CfpDetails saveCompanyFamilyplanDetails(CfpDetails details)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;list&nbsp;of&nbsp;CompanyFamilyplanDetails<br><br>@param&nbsp;companyFamilyplanSystemId<br>@return&nbsp;list&nbsp;of&nbsp;CompanyFamilyplanDetails<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param companyFamilyplanSystemId
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<CfpDetails> getCompanyFamilyplanDetailsListByCFPSystemId(
			int companyFamilyplanSystemId) throws PortalException,
			SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;company&nbsp;familyplan&nbsp;details&nbsp;from&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies<br>the&nbsp;appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;details<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;Modal&nbsp;Object<br>@return&nbsp;deleted&nbsp;CompanyFamilyplanDetails&nbsp;Modal&nbsp;object<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param details
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CfpDetails deleteCompanyFamilyplanDetails(CfpDetails details)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Updates&nbsp;the&nbsp;company&nbsp;familyplan&nbsp;master&nbsp;in&nbsp;the&nbsp;database&nbsp;or&nbsp;adds&nbsp;it&nbsp;if&nbsp;it<br>does&nbsp;not&nbsp;yet&nbsp;exist.&nbsp;Also&nbsp;notifies&nbsp;the&nbsp;appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;companyFamilyplanMaster<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;Modal&nbsp;Object<br>@return&nbsp;updated&nbsp;CompanyFamilyplanMaster&nbsp;Modal&nbsp;Object<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param companyFamilyplanMaster
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CfpModel updateCompanyFamilyplanMaster(CfpModel companyFamilyplanMaster)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;company&nbsp;familyplan&nbsp;master&nbsp;from&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies<br>the&nbsp;appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;CompanyFamilyplanMasterSystemId<br>@return&nbsp;deleted&nbsp;CompanyFamilyplanMaster&nbsp;Modal&nbsp;Object<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param companyFamilyplanMasterSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CfpModel deleteCompanyFamilyplanMasterBySystemId(
			int companyFamilyplanMasterSystemId) throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;list&nbsp;to&nbsp;helperTable&nbsp;Object&nbsp;according&nbsp;to&nbsp;the&nbsp;listName<br>parameter<br><br>@param&nbsp;listName<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;DropDown&nbsp;Names<br>@return&nbsp;list&nbsp;of&nbsp;HelperTable&nbsp;Object<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param listName
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<HelperTable> getHelperTableDetailsByListName(String listName)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;company&nbsp;master&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;companyMasterSystemId<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;primary&nbsp;key<br>@return&nbsp;CompanyMaster&nbsp;-&nbsp;Modal&nbsp;Object<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param companyMasterSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CompanyMaster getCompanyMasterBySystemId(int companyMasterSystemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;number&nbsp;of&nbsp;rows&nbsp;that&nbsp;match&nbsp;the&nbsp;dynamic&nbsp;query.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;CompanyFamilyplanMaster<br>@return&nbsp;count<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	int getCompanyMasterQueryCount(DynamicQuery query) throws PortalException,
			SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;updated&nbsp;CompanyFamilyplanDetails<br>@param&nbsp;details<br>@return<br>@throws&nbsp;PortalException<br>@throws&nbsp;SystemException&nbsp;
	 * <!-- end-UML-doc -->
	 * @param details
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CfpDetails updateCompanyFamilyplanDetails(CfpDetails details)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getCompanyMasterQueryList(final DynamicQuery query)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;number&nbsp;of&nbsp;rows&nbsp;that&nbsp;match&nbsp;the&nbsp;dynamic&nbsp;query.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;CompanyFamilyplanMaster<br>@return&nbsp;count<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	int getTempCfpDetailsCount(DynamicQuery query) throws PortalException,
			SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;number&nbsp;of&nbsp;rows&nbsp;that&nbsp;match&nbsp;the&nbsp;dynamic&nbsp;query.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;CompanyFamilyplanMaster<br>@return&nbsp;count<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getTempCfpDetails(DynamicQuery query) throws PortalException,
			SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;cTempCfpDetails&nbsp;to&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;companyFamilyplanMaster<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;Modal&nbsp;Object<br>@return&nbsp;CompanyFamilyplanMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param companyFamilyplanMaster
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ImtdCfpDetails saveTempCfpDetails(ImtdCfpDetails companyFamilyplanMaster)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;company&nbsp;familyplan&nbsp;details&nbsp;from&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies<br>the&nbsp;appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;details<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;Modal&nbsp;Object<br>@return&nbsp;deleted&nbsp;CompanyFamilyplanDetails&nbsp;Modal&nbsp;object<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param details
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ImtdCfpDetails deleteTempCfpDetails(int details) throws PortalException,
			SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;company&nbsp;familyplan&nbsp;details&nbsp;from&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies<br>the&nbsp;appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;details<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;Modal&nbsp;Object<br>@return&nbsp;deleted&nbsp;CompanyFamilyplanDetails&nbsp;Modal&nbsp;object<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param userId
	 * @param sessionId
	 * @param date
	 * @param deleteAllOperationFlag
	 * @param dateStr
	 * @param tempCfpSystemId
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	Object deleteAllTempCfpDetails(String userId, String sessionId, Date date,
			Boolean deleteAllOperationFlag, String dateStr,
			Object tempCfpSystemId) throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param start
	 * @param offset
	 * @param companyIdList
	 * @param operation
	 * @param future2
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getCompanyLazyList(int start, int offset, Object companyIdList,
			Object operation, Object future2,String column,String orderBy,Map<Object,Object> filterMap) throws PortalException,
			SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param userId
	 * @param sessionId
	 * @param createdDate
	 * @param operation
	 * @param future1
	 * @param future2
	 * @param future3
	 * @param future4
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	Object insertTempCfpDetailsInADD(String userId, String sessionId,
			String createdDate, String operation, Object future1,
			Object future2, Object future3, Object future4)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param userId
	 * @param sessionId
	 * @param createdDate
	 * @param operation
	 * @param future1
	 * @param future2
	 * @param future3
	 * @param future4
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	Object insertTempCfpDetailsInEdit(String userId, String sessionId,
			String createdDate, String operation, Object future1,
			Object future2, Object future3, Object future4)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;cTempCfpDetails&nbsp;to&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;tempCfpDetails<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;Modal&nbsp;Object<br>@return&nbsp;CompanyFamilyplanMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param tempCfpDetails
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ImtdCfpDetails updateTempCfpDetails(ImtdCfpDetails tempCfpDetails)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param userId
	 * @param sessionId
	 * @param createdDate
	 * @param operation
	 * @param future1
	 * @param future2
	 * @param future3
	 * @param future4
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	Object updateForPopulate(String userId, String sessionId,
			String createdDate, String operation, Object future1,
			Object future2, Object future3, Object future4)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param userId
	 * @param sessionId
	 * @param createdDate
	 * @param operation
	 * @param future1
	 * @param future2
	 * @param future3
	 * @param future4
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	Object updateForPopulateAll(String userId, String sessionId,
			String createdDate, String operation, Object future1,
			Object future2, Object future3, Object future4)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param cfpMasterSystemId
	 * @param userId
	 * @param sessionId
	 * @param createdDate
	 * @param operation
	 * @param future1
	 * @param future2
	 * @param future3
	 * @param future4
	 * @return
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public Object updateToCFPDetails(int cfpMasterSystemId, String userId,
			String sessionId, String createdDate, String operation,
			Object future1, Object future2, Object future3, Object future4);

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param cfpMasterSystemId
	 * @param userId
	 * @param sessionId
	 * @param createdDate
	 * @param operation
	 * @param future1
	 * @param future2
	 * @param future3
	 * @param future4
	 * @return
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public Object updateCFPDetails(int cfpMasterSystemId, String userId,
			String sessionId, String createdDate, String operation,
			Object future1, Object future2, Object future3, Object future4);

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param cfpMasterSystemId
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	Object updateOperationCFPDeatils(String cfpMasterSystemId)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param userId
	 * @param sessionId
	 * @param createdDate
	 * @param process
	 * @return
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	Object validateTempCFPDeatils(String userId, String sessionId,
			String createdDate, String process);

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;number&nbsp;of&nbsp;rows&nbsp;that&nbsp;match&nbsp;the&nbsp;cfp&nbsp;master&nbsp;system&nbsp;id<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;CompanyFamilyplanMaster<br>@return&nbsp;count<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param cfpMasterSystemId
	 * @param operation
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getTempCfpDetailsCount(String cfpMasterSystemId, String operation,Map<Object,Object> filterMap)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;results&nbsp;that&nbsp;match&nbsp;the&nbsp;cfp&nbsp;master&nbsp;system&nbsp;id<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;CompanyFamilyplanMaster<br>@return&nbsp;count<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param start
	 * @param end
	 * @param cfpMasterSystemId
	 * @param operation
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getTempCfpDetails(String start, String end, String cfpMasterSystemId,
			String operation,String column,String orderBy,Map<Object,Object> filterMap) throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int getCompanyFamilyplanMasterTotalCount() throws SystemException,
			PortalException;
        
        public List getSelectedCompanies(int start, int offset,String userId, String sessionId,String columnName,String orderBy,Map<Object,Object> filterMap,String operation);

    public List findCfpModelV1(Map<Object, Object> cfp, String orderByColumn, Boolean sortOrder, Object index, Object next, Map<Object, Object> parameters, String operation, Object future, Object future1);
  
          /** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;Dynamic&nbsp;query&nbsp;of&nbsp;Company&nbsp;Familyplan&nbsp;Master<br>@return&nbsp;list&nbsp;of&nbsp;CompanyFamilyplanMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<CfpContract> getCfpContractList(DynamicQuery query)
			throws SystemException;
}
