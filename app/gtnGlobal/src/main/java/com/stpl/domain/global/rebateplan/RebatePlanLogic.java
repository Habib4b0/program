/**
 * 
 */
package com.stpl.domain.global.rebateplan;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import org.vaadin.addons.lazycontainer.OrderByColumn;

import com.stpl.app.model.RebatePlanMaster;
import com.stpl.app.global.rebateplan.dto.RebatePlanMasterDTO;
import com.stpl.app.global.rebateplan.dto.RebatePlanTierResults;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItemContainer;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/** 
 * <!-- begin-UML-doc -->
 * The&nbsp;Class&nbsp;RebatePlanSearchLogic.
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface RebatePlanLogic {
	/** 
	 * <!-- begin-UML-doc -->
	 * Search&nbsp;rebate&nbsp;plan.<br><br>@param&nbsp;searchRebatePlanForm&nbsp;the&nbsp;search&nbsp;rebate&nbsp;plan&nbsp;form<br>@param&nbsp;start&nbsp;the&nbsp;start<br>@param&nbsp;end&nbsp;the&nbsp;end<br>@param&nbsp;columns&nbsp;the&nbsp;columns<br>@return&nbsp;the&nbsp;list<&nbsp;rebate&nbsp;plan&nbsp;master&nbsp;dt&nbsp;o>
	 * <!-- end-UML-doc -->
	 * @param searchRebatePlanForm
	 * @param start
	 * @param end
	 * @param columns
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<SearchResultsDTO> searchRebatePlan(
			final ErrorfulFieldGroup searchRebatePlanForm, final int start,
			final int end, final List<OrderByColumn> columns,final BeanSearchCriteria criteria)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;rebate&nbsp;plan&nbsp;master&nbsp;by&nbsp;id.<br><br>@param&nbsp;idValue&nbsp;the&nbsp;id&nbsp;value<br>@return&nbsp;the&nbsp;rebate&nbsp;plan&nbsp;master&nbsp;by&nbsp;id<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param idValue
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public RebatePlanMasterDTO getRebatePlanMasterById(final int idValue)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Save&nbsp;rebate&nbsp;plan.<br><br>@param&nbsp;rebatePlanForm&nbsp;the&nbsp;rebate&nbsp;plan&nbsp;form<br>@param&nbsp;rebatePlanTiers&nbsp;the&nbsp;rebate&nbsp;plan&nbsp;tiers<br>@return&nbsp;the&nbsp;string<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param rebatePlanForm
	 * @param rebatePlanTiers
	 * @return
	 * @throws SystemException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws PortalException
	 * @throws ParseException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public String saveRebatePlan(final ErrorfulFieldGroup rebatePlanForm,
			final BeanItemContainer<RebatePlanTierResults> rebatePlanTiers) throws SystemException,
			IllegalAccessException, InvocationTargetException, PortalException,
			ParseException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Delete&nbsp;rebate&nbsp;plan&nbsp;by&nbsp;id.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;rebate&nbsp;plan&nbsp;master
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public RebatePlanMaster deleteRebatePlanById(final int systemId)
			throws SystemException, PortalException;

}