/**
 * 
 */
package com.stpl.domain.global.item;

import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;
import com.stpl.app.model.ItemQualifier;
import com.stpl.app.model.ItemPricingQualifier;
import com.stpl.app.model.ItemMaster;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.app.model.ItemIdentifier;
import com.stpl.app.NoSuchItemQualifierException;
import com.stpl.app.NoSuchItemPricingQualifierException;
import com.stpl.app.model.ItemPricing;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.BrandMaster;
import java.util.Map;
import com.stpl.app.model.Udcs;

/** 
 * <!-- begin-UML-doc -->
 * Defines&nbsp;DAO&nbsp;operations&nbsp;for&nbsp;Item&nbsp;Search&nbsp;Logic<br><br>@author&nbsp;shrihariharan
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface ItemDAO {
	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;a&nbsp;range&nbsp;of&nbsp;all&nbsp;the&nbsp;item&nbsp;irt&nbsp;qualifiers.<br><br>@param&nbsp;startIndex<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;start&nbsp;limit<br>@param&nbsp;endindex<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;end&nbsp;limit<br>@return&nbsp;list&nbsp;of&nbsp;ItemIrtQualifier<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param startIndex
	 * @param endindex
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<ItemQualifier> getItemIrtQualifiersByLimit(int startIndex, int endindex)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;item&nbsp;irt&nbsp;qualifier&nbsp;from&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;qualifierId<br>@return&nbsp;ItemIrtQualifier&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param qualifierId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemQualifier deleteItemIrtQualifierByQualifierId(int qualifierId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;item&nbsp;irt&nbsp;qualifier&nbsp;to&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;itemIrtQualifier<br>@return&nbsp;ItemIrtQualifier&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param itemIrtQualifier
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemQualifier saveItemIrtQualifier(ItemQualifier itemIrtQualifier)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Updates&nbsp;the&nbsp;item&nbsp;irt&nbsp;qualifier&nbsp;in&nbsp;the&nbsp;database&nbsp;or&nbsp;adds&nbsp;it&nbsp;if&nbsp;it&nbsp;does&nbsp;not<br>yet&nbsp;exist.&nbsp;Also&nbsp;notifies&nbsp;the&nbsp;appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;itemIrtQualifier<br>@return&nbsp;ItemIrtQualifier&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param itemIrtQualifier
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemQualifier updateItemIrtQualifier(ItemQualifier itemIrtQualifier)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;a&nbsp;range&nbsp;of&nbsp;all&nbsp;the&nbsp;item&nbsp;pricing&nbsp;qualifiers.<br><br>@param&nbsp;startIndex<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;start&nbsp;limit<br>@param&nbsp;endindex<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;end&nbsp;limit<br>@return&nbsp;list&nbsp;of&nbsp;ItemPricingQualifier<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param startIndex
	 * @param endindex
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<ItemPricingQualifier> getItemPricingQualifierByLimit(int startIndex,
			int endindex) throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;item&nbsp;pricing&nbsp;qualifier&nbsp;from&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;qualifierId<br>@return&nbsp;ItemPricingQualifier&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param qualifierId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemPricingQualifier deleteItemPricingQualifierByQualifierId(int qualifierId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;item&nbsp;pricing&nbsp;qualifier&nbsp;to&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;itemPricingQualifier<br>@return&nbsp;ItemPricingQualifier&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param itemPricingQualifier
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemPricingQualifier saveItemPricingQualifier(
			ItemPricingQualifier itemPricingQualifier) throws PortalException,
			SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Updates&nbsp;the&nbsp;item&nbsp;pricing&nbsp;qualifier&nbsp;in&nbsp;the&nbsp;database&nbsp;or&nbsp;adds&nbsp;it&nbsp;if&nbsp;it&nbsp;does<br>not&nbsp;yet&nbsp;exist.&nbsp;Also&nbsp;notifies&nbsp;the&nbsp;appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;itemPricingQualifier<br>@return&nbsp;ItemPricingQualifier&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param itemPricingQualifier
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemPricingQualifier updateItemPricingQualifier(
			ItemPricingQualifier itemPricingQualifier) throws PortalException,
			SystemException;

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
	 * Adds&nbsp;the&nbsp;item&nbsp;irt&nbsp;identifier&nbsp;to&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;itemIrtIdentifier<br>@return&nbsp;saved&nbsp;ItemIrtIdentifier<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param itemIrtIdentifier
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemIdentifier saveItemIrtIdentifier(ItemIdentifier itemIrtIdentifier)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;list&nbsp;of&nbsp;Item&nbsp;Master<br><br>@param&nbsp;itemId<br>@param&nbsp;itemNo<br>@param&nbsp;itemName<br>@param&nbsp;itemStatus<br>@param&nbsp;itemType<br>@param&nbsp;itemDesc<br>@param&nbsp;manufacturerId<br>@param&nbsp;qualifierId<br>@param&nbsp;itemIdentifier<br>@param&nbsp;brand<br>@return&nbsp;list&nbsp;of&nbsp;item&nbsp;Master
	 * <!-- end-UML-doc -->
	 * @param itemId
	 * @param itemNo
	 * @param itemName
	 * @param itemStatus
	 * @param itemType
	 * @param itemDesc
	 * @param manufacturerId
	 * @param qualifierId
	 * @param itemIdentifier
	 * @param brand
	 * @return
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<ItemMaster> getItemMasterListByQualifierId(String itemId,
			String itemNo, String itemName, String itemStatus, String itemType,
			String itemDesc, String manufacturerId, int qualifierId,
			String itemIdentifier, String brand, String orderByColumn, Boolean sortOrder,final Map<String, Object> parameters);

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;Item&nbsp;irt&nbsp;qualifier<br><br>@param&nbsp;qualifierName<br>@return&nbsp;ItemIrtQualifier&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException<br>@throws&nbsp;NoSuchItemIrtQualifierException
	 * <!-- end-UML-doc -->
	 * @param qualifierName
	 * @return
	 * @throws SystemException
	 * @throws NoSuchItemQualifierException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemQualifier getItemIrtQualifierByName(String qualifierName)
			throws SystemException, NoSuchItemQualifierException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;ItemPricingQualifier<br><br>@param&nbsp;codeQualifierName<br>@return&nbsp;ItemPricingQualifier&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException<br>@throws&nbsp;NoSuchItemPricingQualifierException
	 * <!-- end-UML-doc -->
	 * @param codeQualifierName
	 * @return
	 * @throws SystemException
	 * @throws NoSuchItemPricingQualifierException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemPricingQualifier getItemPricingQualifierByCodeQualifierName(
			String codeQualifierName) throws SystemException,
			NoSuchItemPricingQualifierException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;item&nbsp;master&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;itemSystemId<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;primary&nbsp;key<br>@return&nbsp;ItemMaster<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param itemSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemMaster getItemMasterBySystemId(int itemSystemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;item&nbsp;master&nbsp;to&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the&nbsp;appropriate&nbsp;model<br>listeners.<br><br>@param&nbsp;itemMaster<br>@return&nbsp;ItemMaster&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param itemMaster
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemMaster saveItemMaster(ItemMaster itemMaster) throws PortalException,
			SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;item&nbsp;master&nbsp;to&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the&nbsp;appropriate&nbsp;model<br>listeners.<br><br>@param&nbsp;itemMaster<br>@return&nbsp;ItemMaster&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param itemMaster
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemMaster updateItemMaster(ItemMaster itemMaster) throws PortalException,
			SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;list&nbsp;of&nbsp;ItemIrtIdentifier<br><br>@param&nbsp;itemSystemId<br>@return&nbsp;list&nbsp;of&nbsp;ItemIrtIdentifier<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param itemSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<ItemIdentifier> getItemIrtIdentifierByItemSystemId(int itemSystemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;list&nbsp;of&nbsp;ItemPricing<br><br>@param&nbsp;itemSystemId<br>@return&nbsp;list&nbsp;ItemPricing<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param itemSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<ItemPricing> getItemPricingByItemSystemId(int itemSystemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;item&nbsp;irt&nbsp;identifier&nbsp;from&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the<br>appropriate&nbsp;model&nbsp;listeners.<br><br>@param&nbsp;itemIrtIdentifier<br>@return&nbsp;deleted&nbsp;ItemIrtIdentifier<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param itemIrtIdentifier
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemIdentifier deleteItemIrtIdentifier(ItemIdentifier itemIrtIdentifier)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;item&nbsp;pricing&nbsp;from&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the&nbsp;appropriate<br>model&nbsp;listeners.<br><br>@param&nbsp;itemPricing<br>@return&nbsp;deleted&nbsp;ItemPricing<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param itemPricing
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemPricing deleteItemPricing(ItemPricing itemPricing)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;item&nbsp;pricing&nbsp;to&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the&nbsp;appropriate<br>model&nbsp;listeners.<br><br>@param&nbsp;itemPricing<br>@return&nbsp;ItemPricing&nbsp;-&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param itemPricing
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemPricing saveItemPricing(ItemPricing itemPricing)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Adds&nbsp;the&nbsp;item&nbsp;pricing&nbsp;to&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the&nbsp;appropriate<br>model&nbsp;listeners.<br><br>@param&nbsp;companySystemId<br>@return&nbsp;CompanyMaster&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param companySystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	CompanyMaster getCompanyMasterBySystemId(int companySystemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;item&nbsp;irt&nbsp;qualifier&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;qualifierId<br>@return&nbsp;ItemIrtQualifier<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param qualifierId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemQualifier getItemIrtQualifierByQualifierId(int qualifierId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;item&nbsp;pricing&nbsp;qualifier&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;qualifierId<br>@return&nbsp;ItemPricingQualifier<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param qualifierId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemPricingQualifier getItemPricingQualifierByQualifierId(int qualifierId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;CompanyMaster<br>@return&nbsp;list&nbsp;of&nbsp;CompanyMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<CompanyMaster> getCompanyMasterList(DynamicQuery query)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Deletes&nbsp;the&nbsp;item&nbsp;master&nbsp;from&nbsp;the&nbsp;database.&nbsp;Also&nbsp;notifies&nbsp;the&nbsp;appropriate<br>model&nbsp;listeners.<br><br>@param&nbsp;itemSystemId<br>@return&nbsp;deleted&nbsp;ItemMaster<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param itemSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemMaster deleteItemMasterBySystemId(int itemSystemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;ItemIrtQualifier<br>@return&nbsp;list&nbsp;of&nbsp;ItemIrtQualifier<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<ItemQualifier> getItemIrtQualifierList(DynamicQuery query)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;ItemPricingQualifier<br>@return&nbsp;list&nbsp;of&nbsp;ItemPricingQualifier<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<ItemPricingQualifier> getItemPricingQualifierList(DynamicQuery query)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;HelperTable<br>@return&nbsp;list&nbsp;of&nbsp;HelperTable<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<HelperTable> getHelperTableList(DynamicQuery query)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;BrandMaster<br>@return&nbsp;list&nbsp;of&nbsp;BrandMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<BrandMaster> getBrandMasterList(DynamicQuery query)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;count&nbsp;according&nbsp;to&nbsp;the&nbsp;result&nbsp;of&nbsp;the&nbsp;query<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;ItemMaster<br>@return&nbsp;count<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	int getItemMasterCountByQuery(DynamicQuery query) throws PortalException,
			SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;ItemIrtIdentifier<br>@return&nbsp;list&nbsp;of&nbsp;ItemIrtIdentifier<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<ItemIdentifier> getItemIrtIdentifierList(DynamicQuery query)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;a&nbsp;range&nbsp;of&nbsp;all&nbsp;the&nbsp;item&nbsp;masters.<br><br>@param&nbsp;startIndex<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;start&nbsp;limit<br>@param&nbsp;endindex<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;end&nbsp;limit<br>@return&nbsp;list&nbsp;of&nbsp;ItemMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param startIndex
	 * @param endindex
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<ItemMaster> getItemMasterByLimit(int startIndex, int endindex)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param ifpDynamicQuery
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getBrandList(DynamicQuery ifpDynamicQuery) throws PortalException,
			SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;CompanyMaster<br>@return&nbsp;list&nbsp;of&nbsp;CompanyMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getManufactureIdList(DynamicQuery query) throws PortalException,
			SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <br>@param&nbsp;cfpDynamicQuery<br>@return&nbsp;
	 * <!-- end-UML-doc -->
	 * @param cfpDynamicQuery
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List itemIrtQualifierNameList(DynamicQuery cfpDynamicQuery)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <br>@param&nbsp;query<br>@return<br>@throws&nbsp;SystemException&nbsp;
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getItemPricingTypeList(DynamicQuery query) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <br>@param&nbsp;itemPricing<br>@return<br>@throws&nbsp;PortalException<br>@throws&nbsp;SystemException&nbsp;
	 * <!-- end-UML-doc -->
	 * @param itemPricing
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemPricing updateItemPricing(ItemPricing itemPricing)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <br>@param&nbsp;itemIrtIdentifier<br>@return<br>@throws&nbsp;PortalException<br>@throws&nbsp;SystemException&nbsp;
	 * <!-- end-UML-doc -->
	 * @param itemIrtIdentifier
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	ItemIdentifier updateItemIrtIdentifier(ItemIdentifier itemIrtIdentifier)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param type
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	Map<Integer, Udcs> getUDCS(String type) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param udcsSid
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	Udcs getUDCS(int udcsSid) throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param udcs
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	Udcs saveUdcs(Udcs udcs) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param udcs
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	Udcs updateUdcs(Udcs udcs) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int getItemMasterTotalCount() throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int getItemPricingQualifierTotalCount() throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int getItemIrtQualifiersTotalCount() throws SystemException,
			PortalException;
	
	public List<HelperTable> getHelperTableDetailsByListName()
			throws PortalException, SystemException ;
                  /**
    * To get the count of companyIdentifier associated with given qualifierID
    * 
    * @param query
    * @return List
    * @throws PortalException
    * @throws SystemException 
    */
     List<ItemIdentifier> getItemIdentifierList(DynamicQuery query) throws PortalException, SystemException;
     
     List<ItemPricing> getItemPricingList(DynamicQuery query) throws PortalException, SystemException;
}
