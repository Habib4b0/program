/**
 * 
 */
package com.stpl.domain.global.priceschedule;

import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.app.model.ItemMaster;
import java.util.List;
import com.stpl.app.model.IfpDetails;
import com.stpl.app.model.IfpModel;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ItemPricingQualifier;
import com.stpl.app.model.PsModel;
import com.stpl.app.model.PsDetails;

/** 
 * <!-- begin-UML-doc -->
 * Defines&nbsp;DAO&nbsp;operations&nbsp;for&nbsp;Price&nbsp;Schedule&nbsp;Logic<br><br>@author&nbsp;shrihariharan
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface PriceScheduleDAO {
	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;item&nbsp;master&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;itemMasterSystemId<br>@return&nbsp;ItemMaster<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param itemMasterSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemMaster getitemMasterBysystemId(int itemMasterSystemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;list&nbsp;of&nbsp;ItemFamilyplanDetails<br><br>@param&nbsp;ItemFamilyplanSystemId<br>@return&nbsp;list&nbsp;of&nbsp;ItemFamilyplanDetails<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param itemFamilyplanSystemId
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<IfpDetails> getItemFamilyplanDetailsListByItemFamilyplanSystemId(
			int itemFamilyplanSystemId) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;ItemFamilyplanMaster<br>@return&nbsp;list&nbsp;of&nbsp;ItemFamilyplanMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<IfpModel> getItemFamilyplanMasterList(DynamicQuery query)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;HelperTable<br>@return&nbsp;list&nbsp;of&nbsp;HelperTable<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<HelperTable> getHelperTableList(DynamicQuery query)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;list&nbsp;of&nbsp;HelprTable&nbsp;by&nbsp;list&nbsp;name<br><br>@param&nbsp;listName<br>@return&nbsp;list&nbsp;of&nbsp;HelperTable<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param listName
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<HelperTable> getHelperTableDetailsByListName(String listName)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;ItemPricingQualifier<br>@return&nbsp;list&nbsp;of&nbsp;ItemPricingQualifier<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<ItemPricingQualifier> getItemPricingQualifierList(DynamicQuery query)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;PriceScheduleMaster<br>@return&nbsp;list&nbsp;of&nbsp;PriceScheduleMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<PsModel> getPriceScheduleMasterList(DynamicQuery query)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;price&nbsp;schedule&nbsp;master&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;priceSchecduleMasterSystemId<br>@return&nbsp;PriceScheduleMaster&nbsp;-&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param priceSchecduleMasterSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	PsModel getPriceScheduleMasterBySystemId(int priceSchecduleMasterSystemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;price&nbsp;schedule&nbsp;master&nbsp;to&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners<br><br>@param&nbsp;priceScheduleMaster<br>@return&nbsp;saved&nbsp;PriceScheduleMaster&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param priceScheduleMaster
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	PsModel savePriceScheduleMaster(PsModel priceScheduleMaster)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;list&nbsp;of&nbsp;PriceScheduleDetails<br><br>@param&nbsp;systemId<br>@return&nbsp;list&nbsp;of&nbsp;PriceScheduleDetails<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<PsDetails> getPriceScheduleDetailsByPriceScheduleMasterSystemId(
			int systemId) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;price&nbsp;schedule&nbsp;details&nbsp;from&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;priceScheduleDetails<br>@return&nbsp;deleted&nbsp;PriceScheduleDetails&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param priceScheduleDetails
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	PsDetails deletePriceScheduleDetails(PsDetails priceScheduleDetails)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;price&nbsp;schedule&nbsp;details&nbsp;to&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;priceScheduleDetails<br>@return&nbsp;saved&nbsp;PriceScheduleDetails&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param priceScheduleDetails
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	PsDetails savePriceScheduleDetails(PsDetails priceScheduleDetails)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Updates&nbsp;the&nbsp;price&nbsp;schedule&nbsp;details&nbsp;in&nbsp;the&nbsp;database&nbsp;or&nbsp;adds&nbsp;it&nbsp;if&nbsp;it&nbsp;does<br>not&nbsp;yet&nbsp;exist.&nbsp;Also&nbsp;notifies&nbsp;the&nbsp;appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;priceScheduleDetails<br>@return&nbsp;updated&nbsp;PriceScheduleDetails&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param priceScheduleDetails
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	PsDetails updatePriceScheduleDetails(PsDetails priceScheduleDetails)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;item&nbsp;familyplan&nbsp;master&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;itemFamilyplanMasterSystemId<br>@return&nbsp;ItemFamilyplanMaster&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
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
	 * Returns&nbsp;list&nbsp;of&nbsp;Item<br><br>@param&nbsp;ifpId<br>@return&nbsp;list&nbsp;of&nbsp;items<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param ifpId
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getItemAndPricingForPs(int ifpId) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;price&nbsp;schedule&nbsp;master&nbsp;from&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;priceScheduleSystemId<br>@return&nbsp;PriceScheduleMaster&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param priceScheduleSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	PsModel deletePriceScheduleMaster(int priceScheduleSystemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;a&nbsp;range&nbsp;of&nbsp;all&nbsp;the&nbsp;price&nbsp;schedule&nbsp;masters.<br><br>@param&nbsp;startIndex<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;satrt&nbsp;limit<br>@param&nbsp;endIndex<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;end&nbsp;limit<br>@return&nbsp;list&nbsp;of&nbsp;PriceScheduleMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param startIndex
	 * @param endIndex
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<PsModel> getPriceScheduleMasterListByLimit(int startIndex, int endIndex)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;count&nbsp;of&nbsp;ItemFamilyplanMaster<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;ItemFamilyplanMaster<br>@return&nbsp;count<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	int getItemFamilyplanMasterQueryCount(DynamicQuery query)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;a&nbsp;range&nbsp;of&nbsp;all&nbsp;the&nbsp;item&nbsp;familyplan&nbsp;masters.<br><br>@param&nbsp;startIndex<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;start&nbsp;limit<br>@param&nbsp;endindex<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;end&nbsp;limit<br>@return&nbsp;list&nbsp;of&nbsp;ItemFamilyplanMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param startIndex
	 * @param endindex
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<IfpModel> getItemFamilyplanMasterByLimit(int startIndex, int endindex)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Updates&nbsp;the&nbsp;price&nbsp;schedule&nbsp;master&nbsp;in&nbsp;the&nbsp;database&nbsp;or&nbsp;adds&nbsp;it&nbsp;if&nbsp;it&nbsp;does<br>not&nbsp;yet&nbsp;exist.&nbsp;Also&nbsp;notifies&nbsp;the&nbsp;appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;priceScheduleMaster<br>@return&nbsp;PriceScheduleMaster&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param priceScheduleMaster
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	PsModel updatePriceScheduleMaster(PsModel priceScheduleMaster)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;count&nbsp;of&nbsp;PriceScheduleMaster<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;PriceScheduleMaster<br>@return&nbsp;count<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	int getPriceScheduleMasterQueryCount(DynamicQuery query)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;ItemPricingQualifier<br>@return&nbsp;list&nbsp;of&nbsp;ItemPricingQualifier<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getItemPricingTypeList(DynamicQuery query) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;ItemFamilyplanMaster<br>@return&nbsp;list&nbsp;of&nbsp;ItemFamilyplanMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	int getItemFamilyplanMasterCount(DynamicQuery query) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;ItemFamilyplanMaster<br>@return&nbsp;list&nbsp;of&nbsp;ItemFamilyplanMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getIFPList(DynamicQuery query) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int getItemFamilyplanMasterTotalCount() throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int getPriceScheduleMasterTotalCount() throws SystemException;
}