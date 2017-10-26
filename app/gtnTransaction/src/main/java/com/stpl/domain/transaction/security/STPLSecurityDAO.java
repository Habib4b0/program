/**
 * 
 */
package com.stpl.domain.transaction.security;

import java.util.List;

import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;

/** 
 * <!-- begin-UML-doc -->
 * Data&nbsp;Access&nbsp;layer&nbsp;for&nbsp;ActualsSearchLogic.<br><br>@author&nbsp;Harlin
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface STPLSecurityDAO {
	/** 
	 * <!-- begin-UML-doc -->
	 * To&nbsp;get&nbsp;the&nbsp;User&nbsp;by&nbsp;user&nbsp;Id.<br><br>@param&nbsp;userId<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the&nbsp;user&nbsp;id<br>@return&nbsp;the&nbsp;user<br>@throws&nbsp;Exception<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param userId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	User getUser(long userId) throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * To&nbsp;get&nbsp;the&nbsp;UsergroupBusinessrole&nbsp;based&nbsp;on&nbsp;dynamic&nbsp;query.<br><br>@param&nbsp;dynamicQuery<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;Exception<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<UsergroupBusinessrole> dynamicQuery(DynamicQuery dynamicQuery)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * To&nbsp;get&nbsp;the&nbsp;business&nbsp;tab&nbsp;permission.<br><br>@param&nbsp;businessRoleIds<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the&nbsp;business&nbsp;role&nbsp;ids<br>@param&nbsp;moduleName<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the&nbsp;module&nbsp;name<br>@return&nbsp;the&nbsp;business&nbsp;tab&nbsp;permission<br>@throws&nbsp;Exception<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param businessRoleIds
	 * @param moduleName
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getBusinessTabPermission(String businessRoleIds, String moduleName)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * To&nbsp;get&nbsp;the&nbsp;business&nbsp;function&nbsp;permission.<br><br>@param&nbsp;businessRoleIds<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the&nbsp;business&nbsp;role&nbsp;ids<br>@param&nbsp;moduleName<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the&nbsp;module&nbsp;name<br>@return&nbsp;the&nbsp;business&nbsp;function&nbsp;permission<br>@throws&nbsp;Exception<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param businessRoleIds
	 * @param moduleName
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getBusinessFunctionPermission(String businessRoleIds, String moduleName)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * To&nbsp;get&nbsp;the&nbsp;business&nbsp;field&nbsp;permission.<br><br>@param&nbsp;businessRoleIds<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the&nbsp;business&nbsp;role&nbsp;ids<br>@param&nbsp;moduleName<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the&nbsp;module&nbsp;name<br>@return&nbsp;the&nbsp;business&nbsp;field&nbsp;permission<br>@throws&nbsp;Exception<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param businessRoleIds
	 * @param moduleName
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getBusinessFieldPermission(String businessRoleIds, String moduleName)
			throws SystemException;
}