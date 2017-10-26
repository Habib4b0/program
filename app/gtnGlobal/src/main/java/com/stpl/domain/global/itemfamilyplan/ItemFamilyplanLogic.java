/**
 * 
 */
package com.stpl.domain.global.itemfamilyplan;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;

import java.util.List;

import org.vaadin.addons.lazycontainer.OrderByColumn;

import com.stpl.app.global.ifp.dto.IFPItemDTO;
import com.stpl.app.global.ifp.dto.ItemFamilyplanMasterDTO;
import com.stpl.app.global.item.dto.ItemMasterDTO;
import com.stpl.app.model.IfpModel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/** 
 * <!-- begin-UML-doc -->
 * The&nbsp;Class&nbsp;IFPLogic.
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface ItemFamilyplanLogic {
	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;item&nbsp;for&nbsp;ifp.<br><br>@param&nbsp;searchField&nbsp;the&nbsp;search&nbsp;field<br>@param&nbsp;value&nbsp;the&nbsp;value<br>@return&nbsp;the&nbsp;item&nbsp;for&nbsp;ifp<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;Exception&nbsp;the&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param searchField
	 * @param value
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<ItemMasterDTO> getItemForIFP(final String searchField,
			final String value) throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * To&nbsp;search&nbsp;the&nbsp;results&nbsp;from&nbsp;search&nbsp;criteria.<br><br>@param&nbsp;searchItemForm&nbsp;the&nbsp;search&nbsp;item&nbsp;form<br>@param&nbsp;start&nbsp;the&nbsp;start<br>@param&nbsp;end&nbsp;the&nbsp;end<br>@param&nbsp;orderByColumns&nbsp;the&nbsp;order&nbsp;by&nbsp;columns<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;Exception&nbsp;the&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param searchItemForm
	 * @param start
	 * @param end
	 * @param orderByColumns
     * @param criteria
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<SearchResultsDTO> searchIFP(
			final ErrorfulFieldGroup searchItemForm, final int start,
			final int end, final List<OrderByColumn> orderByColumns, final BeanSearchCriteria criteria)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Save&nbsp;ifp&nbsp;fields&nbsp;when&nbsp;add&nbsp;/save&nbsp;button&nbsp;is&nbsp;triggered.<br><br>@param&nbsp;ifpForm&nbsp;the&nbsp;ifp&nbsp;form<br>@param&nbsp;ifpList&nbsp;the&nbsp;ifp&nbsp;list<br>@return&nbsp;the&nbsp;string<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;Exception&nbsp;the&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param ifpForm
	 * @param ifpList
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public String saveIFP(final ErrorfulFieldGroup ifpForm,
			final List<IFPItemDTO> ifpList, final List<NotesDTO> availableUploadedInformation, final String addedNotes,final List<NotesDTO> removedDetailsList) throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Method&nbsp;to&nbsp;Save&nbsp;details&nbsp;list&nbsp;when&nbsp;the&nbsp;Save&nbsp;IFP&nbsp;is&nbsp;called.<br><br>@param&nbsp;ifpList&nbsp;the&nbsp;ifp&nbsp;list<br>@param&nbsp;itemFamilyPlanMaster&nbsp;the&nbsp;item&nbsp;family&nbsp;plan&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;Exception&nbsp;the&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param ifpList
	 * @param itemFamilyPlanMaster
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void saveDetailsList(final List<IFPItemDTO> ifpList,
			final IfpModel itemFamilyPlanMaster) throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param ifpList
	 * @param itemFamilyPlanMaster
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void updateDetailsList(final List<IFPItemDTO> ifpList,
			final IfpModel itemFamilyPlanMaster) throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;IFP&nbsp;by&nbsp;cfpId.<br><br>@param&nbsp;cfpId&nbsp;the&nbsp;cfpId<br>@return&nbsp;the&nbsp;IFP&nbsp;by&nbsp;cfpId<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param cfpId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public ItemFamilyplanMasterDTO getIFPById(final int cfpId,ItemFamilyplanMasterDTO itemDto) throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Delete&nbsp;ifp&nbsp;master&nbsp;by&nbsp;id.<br><br>@param&nbsp;itemSystemId&nbsp;the&nbsp;item&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;item&nbsp;familyplan&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param itemSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public IfpModel deleteIFPMasterById(final int itemSystemId)
			throws SystemException, PortalException;
}