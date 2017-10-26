/**
 * 
 */
package com.stpl.domain.contract.contractheader;

import java.util.List;

import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

import com.stpl.app.contract.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.contract.contractheader.dto.ContractAliasMasterDTO;
import com.stpl.app.contract.contractheader.dto.ContractMasterDTO;
import com.stpl.app.model.ContractMaster;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

/** 
 * <!-- begin-UML-doc -->
 * Holds&nbsp;the&nbsp;logic&nbsp;for&nbsp;ContractHeader.
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface HeaderLogic {
	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;contract&nbsp;master&nbsp;based&nbsp;on&nbsp;search&nbsp;Item&nbsp;form.<br><br>@param&nbsp;searchItemForm&nbsp;the&nbsp;search&nbsp;item&nbsp;form<br>@param&nbsp;start&nbsp;the&nbsp;start<br>@param&nbsp;end&nbsp;the&nbsp;end<br>@param&nbsp;orderByColumns&nbsp;the&nbsp;order&nbsp;by&nbsp;columns<br>@return&nbsp;the&nbsp;list<&nbsp;contract&nbsp;master&nbsp;dto><br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param searchItemForm
	 * @param start
	 * @param end
	 * @param orderByColumns
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<SearchResultsDTO> searchContractMaster(
			final CustomFieldGroup searchItemForm, final int start,
			final int end, final List<OrderByColumn> orderByColumns,final BeanSearchCriteria search)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;Contract&nbsp;Master&nbsp;DTO&nbsp;by&nbsp;contractSystemId.<br><br>@param&nbsp;contractSystemId&nbsp;the&nbsp;id<br>@return&nbsp;the&nbsp;contract&nbsp;master&nbsp;by&nbsp;contractSystemId
	 * <!-- end-UML-doc -->
	 * @param contractSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public ContractMasterDTO getContractMasterById(final int contractSystemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Saves&nbsp;the&nbsp;ContractMaster&nbsp;to&nbsp;the&nbsp;DataBase.<br><br>@param&nbsp;contractMasterForm&nbsp;the&nbsp;contract&nbsp;master&nbsp;form<br>@param&nbsp;contractAliasList&nbsp;the&nbsp;contract&nbsp;alias&nbsp;list<br>@return&nbsp;the&nbsp;string&nbsp;-&nbsp;save&nbsp;status.
	 * <!-- end-UML-doc -->
	 * @param contractMasterForm
	 * @param contractAliasList
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public String saveContractMaster(final CustomFieldGroup contractMasterForm,
			final List<ContractAliasMasterDTO> contractAliasList,final List<NotesDTO> availableUploadedInformation, final String addedNotes)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Saves&nbsp;the&nbsp;Contract&nbsp;Alias&nbsp;Master&nbsp;to&nbsp;Database.<br><br>@param&nbsp;aliasList&nbsp;the&nbsp;alias&nbsp;list<br>@param&nbsp;result&nbsp;the&nbsp;result
	 * <!-- end-UML-doc -->
	 * @param aliasList
	 * @param result
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void saveContractAliasMasterList(
			final List<ContractAliasMasterDTO> aliasList,
			final ContractMaster result) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Delete&nbsp;contract&nbsp;master&nbsp;by&nbsp;id.<br><br>@param&nbsp;contractSystemId&nbsp;the&nbsp;contract&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;contract&nbsp;master
	 * <!-- end-UML-doc -->
	 * @param contractSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public ContractMaster deleteContractMasterById(final int contractSystemId)
			throws SystemException, PortalException;
}