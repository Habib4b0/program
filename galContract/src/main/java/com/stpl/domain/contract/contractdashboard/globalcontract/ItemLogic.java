/**
 * 
 */
package com.stpl.domain.contract.contractdashboard.globalcontract;

import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;
import com.stpl.ifs.util.HelperDTO;

/** 
 * <!-- begin-UML-doc -->
 * Logic&nbsp;for&nbsp;Item&nbsp;Search.<br><br>@author
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface ItemLogic {
	/** 
	 * <!-- begin-UML-doc -->
	 * to&nbsp;get&nbsp;Item&nbsp;Type<br><br>@param&nbsp;listType<br>@return<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param listType
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<HelperDTO> getItemType(final String listType)
			throws SystemException;
}