/**
 * 
 */
package com.stpl.domain.contract.contractdashboard.globalcontract;

import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;
import com.stpl.app.contract.global.dto.CompanyMasterDTO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import com.stpl.app.contract.global.dto.CompanySearchDto;
import com.stpl.app.contract.ui.ErrorfulFieldGroup;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/** 
 * <!-- begin-UML-doc -->
 * Logic&nbsp;for&nbsp;Company&nbsp;Family&nbsp;Plan.<br><br>@author
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface CompanyFamilyplanLogic {
	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;List&nbsp;of&nbsp;Company&nbsp;Master&nbsp;DTO&nbsp;with&nbsp;the&nbsp;given&nbsp;search&nbsp;field&nbsp;and<br>Value.<br><br>@param&nbsp;searchField&nbsp;-&nbsp;String&nbsp;contains&nbsp;Company&nbsp;Details<br>@param&nbsp;value&nbsp;-&nbsp;Value&nbsp;for&nbsp;wild&nbsp;card<br>@return&nbsp;List&nbsp;of&nbsp;CompanyMasterDTO.<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param searchField
	 * @param val
	 * @param start
	 * @param end
	 * @param columns
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<CompanyMasterDTO> getCompaniesForCFP(final String searchField,
			final String val, int start, int end, List<OrderByColumn> columns,final BeanSearchCriteria criteria)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;List&nbsp;of&nbsp;CompanySearchDTO&nbsp;based&nbsp;on&nbsp;SearchCompanyForm.<br><br>@param&nbsp;searchCompanyForm&nbsp;-&nbsp;FieldGroup.<br>@param&nbsp;start&nbsp;-&nbsp;Starting&nbsp;Index<br>@param&nbsp;end&nbsp;-&nbsp;Ending&nbsp;Index<br>@param&nbsp;orderByColumns&nbsp;-&nbsp;Order<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param searchCompanyForm
	 * @param start
	 * @param end
	 * @param orderByColumns
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<CompanySearchDto> searchCompanyName(
			final ErrorfulFieldGroup searchCompanyForm,BeanSearchCriteria criteria, final int start,
			final int end, final List<OrderByColumn> orderByColumns)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void saveCompanyDetails() throws SystemException;
}