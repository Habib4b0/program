/**
 * 
 */
package com.stpl.domain.contract.contractdashboard;

import java.util.List;

import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.Map;

/** 
 * <!-- begin-UML-doc -->
 * Defines&nbsp;DAO&nbsp;Operation&nbsp;for&nbsp;CompanyFamilyPlan&nbsp;Logic.<br><br>@author&nbsp;sibi
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface CompanyFamilyplanDAO {
	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;-&nbsp;returns&nbsp;the&nbsp;CompanyMaster&nbsp;based&nbsp;on&nbsp;Search&nbsp;Criteria.<br>@return&nbsp;List&nbsp;of&nbsp;CompanyMaster<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
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
	 * Returns&nbsp;the&nbsp;number&nbsp;of&nbsp;rows&nbsp;that&nbsp;match&nbsp;the&nbsp;dynamic&nbsp;query.<br><br>@param&nbsp;dynamicQuery&nbsp;-&nbsp;returns&nbsp;no.&nbsp;of&nbsp;rows&nbsp;based&nbsp;on&nbsp;search&nbsp;criteria.<br>@return&nbsp;long&nbsp;-&nbsp;numbers&nbsp;of&nbsp;records&nbsp;in&nbsp;Database<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	long dynamicQueryCount(DynamicQuery dynamicQuery) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;HelperTable&nbsp;with&nbsp;company&nbsp;type.<br><br>@param&nbsp;list&nbsp;-&nbsp;company&nbsp;type.<br>@return&nbsp;List&nbsp;of&nbsp;HelperTable.<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param list
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<HelperTable> findByHelperTableDetails(String list)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	long companyTypeQueryCount(DynamicQuery dynamicQuery)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;number&nbsp;of&nbsp;rows&nbsp;that&nbsp;match&nbsp;the&nbsp;dynamic&nbsp;query.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;long<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	long companyAdditionDynamicQueryCount(DynamicQuery dynamicQuery)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<HelperTable> getHelperTable(final DynamicQuery dynamicQuery)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int getCompanyMasterCount() throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List getHelperTableDetailsByListName() throws SystemException;
        
        public List findCfpModelV1(Map<Object, Object> cfp, String orderByColumn, Boolean sortOrder, Object index, Object next, Map<Object, Object> parameters, String operation, Object future, Object future1);
        
        public int getCompanyFamilyplanMasterTotalCount() throws SystemException,
			PortalException;
}