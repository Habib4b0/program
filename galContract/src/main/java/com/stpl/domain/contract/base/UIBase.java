/**
 * 
 */
package com.stpl.domain.contract.base;

import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.exception.PortalException;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface UIBase {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void init() throws SystemException, PortalException, Exception;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void configureFields() throws SystemException, PortalException,
			Exception;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void addToContent() throws SystemException, PortalException,
			Exception;
}