/**
 * 
 */
package com.stpl.domain.global.rebateschedule;

import java.util.List;

import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.model.RsModel;
import com.stpl.app.global.rebateschedule.dto.IFPDetailsDTO;
import com.stpl.app.global.rebateschedule.dto.ItemDetailsDTO;
import com.stpl.app.global.rebateschedule.dto.RebateScheduleMasterDTO;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

/** 
 * <!-- begin-UML-doc -->
 * Class&nbsp;to&nbsp;implement&nbsp;logic&nbsp;for&nbsp;Rebate&nbsp;Schedule&nbsp;.<br>
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface RebateLogic {
	/** 
	 * <!-- begin-UML-doc -->
	 * Method&nbsp;to&nbsp;search&nbsp;from&nbsp;RebateScheduleMaster&nbsp;based&nbsp;on&nbsp;the&nbsp;parameters<br>passed.<br><br>@param&nbsp;rebateSchForm&nbsp;the&nbsp;rebate&nbsp;schedule&nbsp;form<br>@param&nbsp;start&nbsp;the&nbsp;start<br>@param&nbsp;end&nbsp;the&nbsp;end<br>@param&nbsp;columns&nbsp;the&nbsp;columns<br>@return&nbsp;the&nbsp;list
	 * <!-- end-UML-doc -->
	 * @param rebateSchForm
	 * @param start
	 * @param end
	 * @param columns
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<SearchResultsDTO> searchRebateScheduleMaster(
			final ErrorfulFieldGroup rebateSchForm, final int start,
			final int end, final List<OrderByColumn> columns,final boolean fieldFlag,final BeanSearchCriteria search)
			throws SystemException, Exception;

	/** 
	 * <!-- begin-UML-doc -->
	 * Method&nbsp;to&nbsp;save&nbsp;all&nbsp;details&nbsp;in&nbsp;RebateScheduleMaster&nbsp;table&nbsp;.<br><br>@param&nbsp;rebateSchForm&nbsp;the&nbsp;rebate&nbsp;schedule&nbsp;form<br>@param&nbsp;itemDetails&nbsp;the&nbsp;item&nbsp;details<br>@return&nbsp;the&nbsp;string
	 * <!-- end-UML-doc -->
	 * @param rebateSchForm
	 * @param itemDetails
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public String saveRS(final ErrorfulFieldGroup rebateSchForm,
			final List<ItemDetailsDTO> itemDetails,final List<NotesDTO> availableUploadedInformation, final String addedNotes) throws SystemException,
			PortalException, Exception;

	/** 
	 * <!-- begin-UML-doc -->
	 * Method&nbsp;to&nbsp;save&nbsp;item&nbsp;details&nbsp;in&nbsp;RebateScheduleDetails&nbsp;table&nbsp;.<br><br>@param&nbsp;itemDetails&nbsp;the&nbsp;item&nbsp;details<br>@param&nbsp;rebateSchedule&nbsp;the&nbsp;rebate&nbsp;schedule<br>@return&nbsp;the&nbsp;string
	 * <!-- end-UML-doc -->
	 * @param itemDetails
	 * @param rebateSchedule
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public String saveRebateScheduleDetails(
			final List<ItemDetailsDTO> itemDetails, final RsModel rebateSchedule)
			throws SystemException, Exception;

	/** 
	 * <!-- begin-UML-doc -->
	 * Method&nbsp;to&nbsp;retrieve&nbsp;the&nbsp;rebateSchedule&nbsp;based&nbsp;on&nbsp;the&nbsp;passed&nbsp;SystemId&nbsp;from<br>rebateScheduleMaster&nbsp;table&nbsp;.<br><br>@param&nbsp;idValue&nbsp;the&nbsp;id&nbsp;value<br>@return&nbsp;the&nbsp;rebate&nbsp;schedule&nbsp;master&nbsp;by&nbsp;id
	 * <!-- end-UML-doc -->
	 * @param idValue
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public RebateScheduleMasterDTO getRebateScheduleMasterById(final int idValue)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Method&nbsp;to&nbsp;delete&nbsp;the&nbsp;rebateSchedule&nbsp;based&nbsp;on&nbsp;the&nbsp;passed&nbsp;SystemId&nbsp;from<br>rebateScheduleMaster&nbsp;table&nbsp;.<br><br>@param&nbsp;rebateSchSysId&nbsp;the&nbsp;rebate&nbsp;schedule&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;rebate&nbsp;schedule&nbsp;master
	 * <!-- end-UML-doc -->
	 * @param rebateSchSysId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public RsModel deleteRebateScheduleById(final int rebateSchSysId)
			throws SystemException, PortalException, Exception;

	/** 
	 * <!-- begin-UML-doc -->
	 * Method&nbsp;to&nbsp;retrieve&nbsp;and&nbsp;delete&nbsp;the&nbsp;list&nbsp;of&nbsp;rebate&nbsp;Schedule&nbsp;Details&nbsp;based<br>on&nbsp;the&nbsp;SystemId&nbsp;from&nbsp;RebateScheduleDetails&nbsp;table&nbsp;.<br><br>@param&nbsp;rebateSchSysId&nbsp;the&nbsp;rebate&nbsp;schedule&nbsp;system&nbsp;id
	 * <!-- end-UML-doc -->
	 * @param rebateSchSysId
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void deleteRebateScheduleDetails(final int rebateSchSysId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Method&nbsp;to&nbsp;fetch&nbsp;and&nbsp;append&nbsp;the&nbsp;item&nbsp;family&nbsp;plan&nbsp;from<br>RebateScheduleDetails&nbsp;table.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;item&nbsp;family&nbsp;plan&nbsp;from&nbsp;rsid
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<IFPDetailsDTO> getItemFamilyPlanFromRSID(final int systemId)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Method&nbsp;to&nbsp;collect&nbsp;the&nbsp;item&nbsp;details&nbsp;based&nbsp;on&nbsp;system&nbsp;ID.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;item&nbsp;details
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<ItemDetailsDTO> getItemDetails(final int systemId,String value)
			throws SystemException, PortalException;
}