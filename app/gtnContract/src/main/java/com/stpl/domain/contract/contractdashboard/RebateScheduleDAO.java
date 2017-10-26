/**
 * 
 */
package com.stpl.domain.contract.contractdashboard;

import java.util.List;

import com.stpl.app.model.HelperTable;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

/** 
 * <!-- begin-UML-doc -->
 * The&nbsp;Interface&nbsp;RebateScheduleLogicDAO.<br><br>@author&nbsp;sibi
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface RebateScheduleDAO {
	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List dynamicQuery(DynamicQuery dynamicQuery) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;number&nbsp;of&nbsp;rows&nbsp;that&nbsp;match&nbsp;the&nbsp;dynamic&nbsp;query.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;long<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	long dynamicQueryCount(DynamicQuery dynamicQuery) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;a&nbsp;list&nbsp;of&nbsp;Helper&nbsp;Table.<br><br>@param&nbsp;type&nbsp;the&nbsp;type<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param type
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<HelperTable> findByHelperTableDetails(String type)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int getRebateScheduleMastersCount() throws SystemException;
        
            /**
         * 
         * @param dynamicQuery
         * @return
         * @throws PortalException
         * @throws SystemException 
         */
        List<Object[]> getRebatePlanList(DynamicQuery dynamicQuery) throws PortalException, SystemException ;
}