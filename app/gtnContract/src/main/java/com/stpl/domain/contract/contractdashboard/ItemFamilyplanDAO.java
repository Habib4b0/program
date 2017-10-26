/**
 * 
 */
package com.stpl.domain.contract.contractdashboard;

import java.util.List;

import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/** 
 * <!-- begin-UML-doc -->
 * Defines&nbsp;DAO&nbsp;operation&nbsp;for&nbsp;ItemFamilyPlan&nbsp;Logic.<br><br>@author
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface ItemFamilyplanDAO {
	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;based&nbsp;on&nbsp;Item&nbsp;Details&nbsp;and<br>returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;-&nbsp;returns&nbsp;records&nbsp;from&nbsp;ItemMaster&nbsp;based&nbsp;on<br>ItemDetails&nbsp;and&nbsp;value.<br>@return&nbsp;List&nbsp;of&nbsp;ItemMaster<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List dynamicQuery(DynamicQuery dynamicQuery) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	long itemAddtionDynamicQueryCount(DynamicQuery dynamicQuery)
			throws SystemException;
	
	/**
     * To get the query count for lazy load in Brand Master
     * @param dynamicQuery
     * @return count
     * @throws SystemException
     */
    public int getBrandMasterCount(DynamicQuery dynamicQuery) throws SystemException;
    
    /**
     * To get query list for the lazy load in Brand Master
     * @param dynamicQuery
     * @return brand name list
     * @throws SystemException
     */
    public List<BrandMaster> getBrandMasterList(DynamicQuery dynamicQuery) throws SystemException;
    
    	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;retrieve&nbsp;the&nbsp;values&nbsp;from&nbsp;Helper&nbsp;Table&nbsp;based&nbsp;on&nbsp;the<br>listName<br><br>@param&nbsp;listName<br>@return&nbsp;list&nbsp;of&nbsp;type&nbsp;HelperTable<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param listName
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<HelperTable> getHelperTableDetailsByListName(String listName)
			throws SystemException;
}