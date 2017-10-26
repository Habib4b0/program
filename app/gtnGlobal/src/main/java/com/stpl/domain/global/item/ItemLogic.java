/**
 * 
 */
package com.stpl.domain.global.item;

import com.stpl.ifs.ui.NotesDTO;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.exception.PortalException;

import java.util.List;

import com.stpl.app.global.item.dto.ItemIrtIdentifierDTO;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.global.item.dto.ItemPricingDTO;

import org.vaadin.addons.lazycontainer.OrderByColumn;

import com.stpl.app.NoSuchItemQualifierException;
import com.stpl.app.model.ItemIdentifier;
import com.stpl.app.NoSuchItemPricingQualifierException;
import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.model.ItemPricing;
import com.stpl.app.model.ItemMaster;

import java.text.ParseException;

import com.stpl.app.global.item.dto.ItemMasterDTO;

import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/** 
 * <!-- begin-UML-doc -->
 * The&nbsp;Class&nbsp;ItemSearchLogic.
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface ItemLogic {
	/** 
	 * <!-- begin-UML-doc -->
	 * Logic&nbsp;for&nbsp;Delete&nbsp;irt&nbsp;qualifer.<br><br>@param&nbsp;qualifierId&nbsp;the&nbsp;qualifier&nbsp;id<br>@return&nbsp;the&nbsp;list<&nbsp;item&nbsp;irt&nbsp;identifier&nbsp;dt&nbsp;o><br>@throws&nbsp;Exception&nbsp;
	 * <!-- end-UML-doc -->
	 * @param qualifierId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<ItemIrtIdentifierDTO> deleteIrtQualifer(final int qualifierId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Logic&nbsp;for&nbsp;Save&nbsp;the&nbsp;irt&nbsp;qualifer.<br><br>@param&nbsp;binder&nbsp;the&nbsp;binder<br>@return&nbsp;the&nbsp;list<&nbsp;item&nbsp;irt&nbsp;identifier&nbsp;dt&nbsp;o><br>@throws&nbsp;SystemException,&nbsp;Exception&nbsp;<br>@throws&nbsp;PortalException&nbsp;
	 * <!-- end-UML-doc -->
	 * @param binder
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<ItemIrtIdentifierDTO> saveIrtQualifer(
			final ErrorfulFieldGroup binder,int qualifierSid) throws PortalException,
			SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Logic&nbsp;for&nbsp;Delete&nbsp;the&nbsp;&nbsp;pricing&nbsp;qualifer.<br><br>@param&nbsp;qualifierId&nbsp;the&nbsp;qualifier&nbsp;id<br>@return&nbsp;the&nbsp;list<&nbsp;item&nbsp;pricing&nbsp;dt&nbsp;o>
	 * <!-- end-UML-doc -->
	 * @param qualifierId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<ItemIrtIdentifierDTO> deletePricingQualifer(final int qualifierId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Logic&nbsp;for&nbsp;Save&nbsp;the&nbsp;pricing&nbsp;qualifer.<br><br>@param&nbsp;binder&nbsp;the&nbsp;binder<br>@return&nbsp;the&nbsp;list<&nbsp;item&nbsp;pricing&nbsp;dt&nbsp;o>
	 * <!-- end-UML-doc -->
	 * @param binder
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<ItemIrtIdentifierDTO> savePricingQualifer(
			final ErrorfulFieldGroup binder,int pricingSid) throws PortalException,
			SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Logic&nbsp;for&nbsp;Search&nbsp;the&nbsp;items&nbsp;in&nbsp;List.<br><br>@param&nbsp;searchItemForm&nbsp;the&nbsp;search&nbsp;item&nbsp;form<br>@param&nbsp;start&nbsp;the&nbsp;start<br>@param&nbsp;end&nbsp;the&nbsp;end<br>@param&nbsp;orderByColumns&nbsp;the&nbsp;order&nbsp;by&nbsp;columns<br>@return&nbsp;the&nbsp;list<&nbsp;search&nbsp;item&nbsp;form>
	 * <!-- end-UML-doc -->
	 * @param searchItemForm
	 * @param start
	 * @param end
	 * @param orderByColumns
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<SearchResultsDTO> searchItems(
			final ErrorfulFieldGroup searchItemForm, final int start,
			final int end, final List<OrderByColumn> orderByColumns, final BeanSearchCriteria criteria)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;item&nbsp;irt&nbsp;identifier.<br><br>@param&nbsp;form&nbsp;the&nbsp;form<br>@return&nbsp;the&nbsp;item&nbsp;irt&nbsp;identifier
	 * <!-- end-UML-doc -->
	 * @param form
	 * @return
	 * @throws SystemException
	 * @throws NoSuchItemQualifierException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public ItemIdentifier getItemIrtIdentifier(final ItemIrtIdentifierDTO form)
			throws SystemException, NoSuchItemQualifierException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;item&nbsp;pricing.<br><br>@param&nbsp;form&nbsp;the&nbsp;form<br>@return&nbsp;the&nbsp;item&nbsp;pricing
	 * <!-- end-UML-doc -->
	 * @param form
	 * @return
	 * @throws SystemException
	 * @throws NoSuchItemPricingQualifierException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public ItemPricing getItemPricing(final ItemPricingDTO form)
			throws SystemException, NoSuchItemPricingQualifierException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Save&nbsp;item&nbsp;master.<br><br>@param&nbsp;itemMasterForm&nbsp;the&nbsp;item&nbsp;master&nbsp;form<br>@param&nbsp;identifierList&nbsp;the&nbsp;identifier&nbsp;list<br>@param&nbsp;priceList&nbsp;the&nbsp;price&nbsp;list<br>@return&nbsp;the&nbsp;string
	 * <!-- end-UML-doc -->
	 * @param itemMasterForm
	 * @param identifierList
	 * @param priceList
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public String saveItemMaster(final ErrorfulFieldGroup itemMasterForm,
			final List<ItemIrtIdentifierDTO> identifierList,
			final List<ItemPricingDTO> priceList,List<NotesDTO> availableUploadedInformation, final String addedNotes, List<NotesDTO> removedDetailsList,final SessionDTO sessionDTO,final List<ItemPricingDTO> removedItemPriceList) throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Logic&nbsp;for&nbsp;Save&nbsp;identifiers&nbsp;list.<br><br>@param&nbsp;identifierList&nbsp;the&nbsp;identifier&nbsp;list<br>@param&nbsp;priceList&nbsp;the&nbsp;price&nbsp;list<br>@param&nbsp;result&nbsp;the&nbsp;result
	 * <!-- end-UML-doc -->
	 * @param identifierList
	 * @param priceList
	 * @param result
	 * @throws SystemException
	 * @throws NoSuchItemPricingQualifierException
	 * @throws NoSuchItemQualifierException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void saveIdentifiersList(
			final List<ItemIrtIdentifierDTO> identifierList,
			final List<ItemPricingDTO> priceList, final ItemMaster result,final List<ItemPricingDTO> removedItemPriceList)
			throws SystemException, NoSuchItemPricingQualifierException,
			NoSuchItemQualifierException,PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;item&nbsp;master&nbsp;by&nbsp;id.<br><br>@param&nbsp;identifier1&nbsp;the&nbsp;id<br>@return&nbsp;the&nbsp;item&nbsp;master&nbsp;by&nbsp;id
	 * <!-- end-UML-doc -->
	 * @param identifier1
	 * @return
	 * @throws ParseException
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public ItemMasterDTO getItemMasterById(final int identifier1)
			throws ParseException, SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param itemSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public ItemMaster deleteItemMasterById(int itemSystemId)
			throws SystemException, PortalException;
}