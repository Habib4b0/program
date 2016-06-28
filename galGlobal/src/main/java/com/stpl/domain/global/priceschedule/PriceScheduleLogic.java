/**
 * 
 */
package com.stpl.domain.global.priceschedule;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.PsModel;
import com.stpl.app.global.priceschedule.dto.PSDTO;
import com.stpl.app.global.priceschedule.dto.PSIFPDTO;
import com.stpl.app.global.priceschedule.dto.SearchPriceScheduleDTO;
import com.stpl.app.global.rebateschedule.dto.ItemDetailsDTO;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

import java.text.ParseException;
import java.util.List;

import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/** 
 * <!-- begin-UML-doc -->
 * The&nbsp;Class&nbsp;PsLogic.
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface PriceScheduleLogic {
	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;item&nbsp;master.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;id<br>@return&nbsp;the&nbsp;item&nbsp;master
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<ItemMaster> getItemMaster(final int systemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;ifp&nbsp;for&nbsp;ps.<br><br>@param&nbsp;searchField&nbsp;the&nbsp;search&nbsp;field<br>@param&nbsp;value&nbsp;the&nbsp;value<br>@return&nbsp;the&nbsp;ifp&nbsp;for&nbsp;ps
	 * <!-- end-UML-doc -->
	 * @param searchField
	 * @param value
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<IfpModel> getIfpForPS(final String searchField,
			final String value) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Search&nbsp;ps.<br><br>@param&nbsp;searchItemForm&nbsp;the&nbsp;search&nbsp;item&nbsp;form<br>@param&nbsp;start&nbsp;the&nbsp;start<br>@param&nbsp;start&nbsp;the&nbsp;start<br>@param&nbsp;end&nbsp;the&nbsp;end<br>@param&nbsp;orderByColumns&nbsp;the&nbsp;order&nbsp;by&nbsp;columns<br>@return&nbsp;the&nbsp;list<&nbsp;search&nbsp;price&nbsp;schedule&nbsp;dt&nbsp;o><br>@throws&nbsp;Exception&nbsp;the&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param searchItemForm
	 * @param start
	 * @param end
	 * @param orderByColumns
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<SearchResultsDTO> searchPs(
			final ErrorfulFieldGroup searchItemForm, final int start,
			final int end, final List<OrderByColumn> orderByColumns,final BeanSearchCriteria criteria)
			throws SystemException, Exception;

	/** 
	 * <!-- begin-UML-doc -->
	 * Save&nbsp;ps.<br><br>@param&nbsp;psForm&nbsp;the&nbsp;ps&nbsp;form<br>@param&nbsp;psList&nbsp;the&nbsp;ps&nbsp;list<br>@return&nbsp;the&nbsp;string
	 * <!-- end-UML-doc -->
	 * @param psForm
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public String savePS(final ErrorfulFieldGroup psForm, final List<NotesDTO> availableUploadedInformation, final String addedNotes, final List<NotesDTO> removedDetailsList)
			throws SystemException, PortalException, Exception;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param psMaster
	 * @param saveOrUpdate
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void saveDetailsList(final PsModel psMaster, String saveOrUpdate)
			throws SystemException, PortalException, Exception;

	/** 
	 * <!-- begin-UML-doc -->
	 * Save&nbsp;details&nbsp;list.<br><br>@param&nbsp;ifpList&nbsp;the&nbsp;ifp&nbsp;list<br>@param&nbsp;psMaster&nbsp;the&nbsp;ps&nbsp;master<br>@param&nbsp;check&nbsp;the&nbsp;check
	 * <!-- end-UML-doc -->
	 * @param ifpList
	 * @param psMaster
	 * @param check
	 * @throws SystemException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void saveDetailsList(final List<PSIFPDTO> ifpList,
			final PsModel psMaster, final String check) throws SystemException,
			Exception;

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;priceschedules&nbsp;by&nbsp;id.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;id<br>@return&nbsp;the&nbsp;priceschedules&nbsp;by&nbsp;id
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public PSDTO getPriceschedulesById(final int systemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;ifp&nbsp;details&nbsp;list.<br><br>@param&nbsp;ifpId&nbsp;the&nbsp;ifp&nbsp;id<br>@return&nbsp;the&nbsp;ifp&nbsp;details&nbsp;list
	 * <!-- end-UML-doc -->
	 * @param ifpId
	 * @return
	 * @throws SystemException
	 * @throws ParseException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<PSIFPDTO> getIfpDetailsList(final int ifpId)
			throws SystemException, ParseException, Exception;

	/** 
	 * <!-- begin-UML-doc -->
	 * Delet&nbsp;ps&nbsp;by&nbsp;id.<br><br>@param&nbsp;psSystemId&nbsp;the&nbsp;ps&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;price&nbsp;schedule&nbsp;master
	 * <!-- end-UML-doc -->
	 * @param psSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public PsModel deletPSById(final int psSystemId) throws SystemException,
			PortalException, Exception;
}