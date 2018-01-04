package com.stpl.app.cff.dao;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.app.model.UsergroupDomainMaster;
import java.util.List;

/**
 * <!-- begin-UML-doc -->
 * Defines&nbsp;DAO&nbsp;operations&nbsp;for&nbsp;Security<br><br>@author&nbsp;shrihariharan<br>
 * <!-- end-UML-doc -->
 * @author porchelvi.g
 * @generated "UML to JPA
 * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface StplSecurityDAO {
	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;user&nbsp;details&nbsp;corresponding&nbsp;to&nbsp;the&nbsp;userId<br><br>@param&nbsp;userId<br>@return&nbsp;User&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param userId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	User getUserByUserId(long userId) throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;UsergroupBusinessroleMaster<br>@return&nbsp;list&nbsp;of&nbsp;UsergroupBusinessroleMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<UsergroupBusinessrole> getUsergroupBusinessroleMasterList(
			DynamicQuery query) throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;UsergroupDomainMaster<br>@return&nbsp;list&nbsp;of&nbsp;UsergroupDomainMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<UsergroupDomainMaster> getUsergroupDomainMasterList(DynamicQuery query)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;list&nbsp;of&nbsp;BusinessroleModuleMaster<br><br>@param&nbsp;businessRoleIds<br>@param&nbsp;moduleName<br>@return&nbsp;list&nbsp;of&nbsp;BusinessroleModuleMaster<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param businessRoleIds
	 * @param moduleName
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getBusinessroleModuleMasterFunctionList(String businessRoleIds,
			String moduleName) throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;list&nbsp;of&nbsp;BusinessroleModuleMaster<br><br>@param&nbsp;businessRoleIds<br>@param&nbsp;moduleName<br>@return&nbsp;list&nbsp;of&nbsp;BusinessroleModuleMaster<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param businessRoleIds
	 * @param moduleName
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @throws RuntimeException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getBusinessroleModuleMasterFieldList(String businessRoleIds,
			String moduleName) throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;list&nbsp;of&nbsp;BusinessroleModuleMaster<br><br>@param&nbsp;businessRoleIds<br>@param&nbsp;moduleName<br>@return&nbsp;list&nbsp;of&nbsp;BusinessroleModuleMaster<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param businessRoleIds
	 * @param moduleName
	 * @return
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getBusinessroleModuleMasterTabList(String businessRoleIds,
			String moduleName);
      
}