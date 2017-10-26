/**
 * 
 */
package com.stpl.domain.contract.contractdashboard;

import java.util.List;

import com.stpl.app.model.HelperTable;
import com.stpl.portal.kernel.exception.SystemException;

/** 
 * <!-- begin-UML-doc -->
 * The&nbsp;Interface&nbsp;ItemSearchLogicDAO.<br><br>@author&nbsp;sibi
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface ItemDAO {
	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;a&nbsp;list&nbsp;of&nbsp;Helper&nbsp;Table.<br><br>@param&nbsp;itemStatus&nbsp;the&nbsp;item&nbsp;status<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param itemStatus
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<HelperTable> findByHelperTableDetails(String itemStatus)
			throws SystemException;
}