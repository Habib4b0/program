/**
 * 
 */
package com.stpl.domain.global.itemfamilyplan;

import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;
import com.stpl.app.model.ItemMaster;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.IfpDetails;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.IfpContract;

/** 
 * <!-- begin-UML-doc -->
 * Defines&nbsp;DAO&nbsp;operations&nbsp;for&nbsp;Item&nbsp;Family&nbsp;Plan&nbsp;Logic<br><br>@author&nbsp;shrihariharan
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface ItemFamilyplanDAO {
	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;ItemMaster<br>@return&nbsp;list&nbsp;of&nbsp;ItemMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<ItemMaster> getItemMasterList(DynamicQuery query)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;ItemFamilyplanMaster<br>@return&nbsp;list&nbsp;of&nbsp;ItemFamilyplanMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<IfpModel> getItemFamilyplanMasterList(DynamicQuery query)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;item&nbsp;familyplan&nbsp;master&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;itemFamilyplanMasterSystemId<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;primary&nbsp;key<br>@return&nbsp;Item&nbsp;Familyplan&nbsp;Master&nbsp;-&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param itemFamilyplanMasterSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	IfpModel getItemFamilyplanMasterBySystemId(int itemFamilyplanMasterSystemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;item&nbsp;familyplan&nbsp;master&nbsp;to&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;itemFamilyplanMaster<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;modal&nbsp;object<br>@return&nbsp;saved&nbsp;ItemFamilyplanMaster&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param itemFamilyplanMaster
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	IfpModel saveItemFamilyplanMaster(IfpModel itemFamilyplanMaster)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;list&nbsp;of&nbsp;ItemFamilyplanDetails<br><br>@param&nbsp;ItemFamilyplanSystemId<br>@return&nbsp;list&nbsp;of&nbsp;ItemFamilyplanDetails<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param itemFamilyplanSystemId
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<IfpDetails> getItemFamilyplanDetailsListByItemFamilyplanSystemId(
			int itemFamilyplanSystemId) throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;item&nbsp;master&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;itemMasterSystemId<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;primary&nbsp;key<br>@return&nbsp;Item&nbsp;Master&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param itemMasterSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemMaster getItemMasterBysystemId(int itemMasterSystemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;item&nbsp;familyplan&nbsp;master&nbsp;from&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;itemFamilyplanMasterSystemId<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;primary&nbsp;key<br>@return&nbsp;deleted&nbsp;ItemFamilyplanMaster&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param itemFamilyplanMasterSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	IfpModel deleteItemFamilyplanMasterBySystemId(
			int itemFamilyplanMasterSystemId) throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;list&nbsp;on&nbsp;helperTable&nbsp;by&nbsp;list&nbsp;name<br><br>@param&nbsp;listName<br>@return&nbsp;drop&nbsp;down&nbsp;name<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param listName
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<HelperTable> getHelperTableDetailsByListName(String listName)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;HelperTable<br>@return&nbsp;list&nbsp;of&nbsp;HelperTable<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<HelperTable> getHelperTableDetailsList(DynamicQuery query)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;number&nbsp;of&nbsp;rows&nbsp;that&nbsp;match&nbsp;the&nbsp;dynamic&nbsp;query.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;ItemFamilyplanMaster<br>@return&nbsp;count<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	int getItemFamilyplanMasterQueryCount(DynamicQuery query)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;a&nbsp;range&nbsp;of&nbsp;all&nbsp;the&nbsp;item&nbsp;familyplan&nbsp;masters.<br><br>@param&nbsp;startIndex<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;start&nbsp;limit<br>@param&nbsp;endindex<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;end&nbsp;limit<br>@return&nbsp;list&nbsp;of&nbsp;ItemFamilyplanMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param startIndex
	 * @param endindex
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<IfpModel> getItemFamilyplanMasterByLimit(int startIndex, int endindex)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Updates&nbsp;the&nbsp;item&nbsp;familyplan&nbsp;master&nbsp;in&nbsp;the&nbsp;database&nbsp;or&nbsp;adds&nbsp;it&nbsp;if&nbsp;it&nbsp;does<br>not&nbsp;yet&nbsp;exist.&nbsp;Also&nbsp;notifies&nbsp;the&nbsp;appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;itemFamilyplanMaster<br>@return&nbsp;updated&nbsp;ItemFamilyplanMaster<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param itemFamilyplanMaster
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	IfpModel updateItemfamilyplanMaster(IfpModel itemFamilyplanMaster)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br>@param&nbsp;query&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;ItemMaster<br>@return&nbsp;list&nbsp;of&nbsp;ItemMaster<br>@throws&nbsp;SystemException&nbsp;
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List getItemMasterCount(final DynamicQuery query)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int getItemFamilyplanMasterTotalCount() throws SystemException,
			PortalException;
        /** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;ItemFamilyplanMaster<br>@return&nbsp;list&nbsp;of&nbsp;ItemFamilyplanMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<IfpContract> getItemFamilyplanContractList(DynamicQuery query)
			throws PortalException, SystemException;
}