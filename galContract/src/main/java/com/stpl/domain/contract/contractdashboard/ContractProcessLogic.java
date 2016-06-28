/**
 * 
 */
package com.stpl.domain.contract.contractdashboard;

import java.util.List;

import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

/** 
 * <!-- begin-UML-doc -->
 * The&nbsp;Class&nbsp;ContractDashboardLogic.
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface ContractProcessLogic {
	/** 
	 * <!-- begin-UML-doc -->
	 * TO&nbsp;update&nbsp;Contract<br><br>@param&nbsp;contractSystemId<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param contractSystemId
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void updateContract(final int contractSystemId)
			throws SystemException, PortalException, Exception;

	/** 
	 * <!-- begin-UML-doc -->
	 * To&nbsp;get&nbsp;processed&nbsp;ContractList<br><br>@param&nbsp;contractId<br>@return<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param contractId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List getprocessedContractList(final String contractId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Disassemple.<br><br>@param&nbsp;contractSystemId&nbsp;the&nbsp;contract&nbsp;system&nbsp;id<br>@return&nbsp;true,&nbsp;if&nbsp;disassemple<br>@throws&nbsp;PortalException<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param contractSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public Boolean disassemple(final int contractSystemId)
			throws SystemException, PortalException;
}