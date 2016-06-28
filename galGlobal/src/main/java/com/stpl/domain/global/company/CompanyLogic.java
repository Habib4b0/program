/**
 * 
 */
package com.stpl.domain.global.company;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.company.dto.CompanyCrtIdentifierDTO;
import com.stpl.app.global.company.dto.CompanyMasterDTO;
import com.stpl.app.model.CompanyIdentifier;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.CompanyParentDetails;
import com.stpl.app.model.CompanyQualifier;


import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

import java.text.ParseException;
import java.util.List;

import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/** 
 * <!-- begin-UML-doc -->
 * The&nbsp;Class&nbsp;CompanySearchLogic&nbsp;holds&nbsp;all&nbsp;business&nbsp;logic&nbsp;for&nbsp;company.
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface CompanyLogic {
	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;search&nbsp;count.<br><br>@param&nbsp;searchCompanyForm&nbsp;the&nbsp;search&nbsp;company&nbsp;form<br>@return&nbsp;the&nbsp;search&nbsp;count<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;Exception&nbsp;the&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param searchCompanyForm
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int getSearchCount(final ErrorfulFieldGroup searchCompanyForm, final BeanSearchCriteria criteria)
			throws SystemException, PortalException, Exception;

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;records&nbsp;from&nbsp;Company&nbsp;Master&nbsp;table&nbsp;based&nbsp;on&nbsp;user&nbsp;selection&nbsp;with<br>start&nbsp;and&nbsp;end&nbsp;index.<br><br>@param&nbsp;searchCompanyForm&nbsp;the&nbsp;search&nbsp;company&nbsp;form<br>@param&nbsp;start&nbsp;the&nbsp;start<br>@param&nbsp;end&nbsp;the&nbsp;end<br>@param&nbsp;orderByColumns&nbsp;the&nbsp;order&nbsp;by&nbsp;columns<br>@return&nbsp;the&nbsp;list<&nbsp;search&nbsp;company&nbsp;form><br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;Exception&nbsp;the&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param searchCompanyForm
	 * @param start
	 * @param end
	 * @param orderByColumns
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<SearchResultsDTO> searchCompany(
			final ErrorfulFieldGroup searchCompanyForm, final int start,
			final int end, final List<OrderByColumn> orderByColumns, final BeanSearchCriteria criteria)
			throws SystemException, PortalException, Exception;

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;record&nbsp;based&nbsp;on&nbsp;system&nbsp;ID.<br><br>@param&nbsp;companySystemId&nbsp;the&nbsp;company&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;trade&nbsp;class&nbsp;table<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;Exception&nbsp;the&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param companySystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<CompanyMasterDTO> getTradeClassTable(final int companySystemId)
			throws SystemException, PortalException, Exception;

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;company&nbsp;master&nbsp;by&nbsp;id.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;company&nbsp;master&nbsp;by&nbsp;id<br>@throws&nbsp;ParseException&nbsp;the&nbsp;parse&nbsp;exception<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;Exception&nbsp;the&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @return
	 * @throws ParseException
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public CompanyMasterDTO getCompanyMasterById(final int systemId)
			throws ParseException, SystemException, PortalException, Exception;

	/** 
	 * <!-- begin-UML-doc -->
	 * To&nbsp;Save&nbsp;company&nbsp;master.<br><br>@param&nbsp;companyMasterForm&nbsp;the&nbsp;company&nbsp;master&nbsp;form<br>@param&nbsp;identifierList&nbsp;the&nbsp;identifier&nbsp;list<br>@param&nbsp;companyTradeList&nbsp;the&nbsp;company&nbsp;trade&nbsp;list<br>@param&nbsp;parentCompanyList&nbsp;the&nbsp;parent&nbsp;company&nbsp;list<br>@return&nbsp;the&nbsp;string
	 * <!-- end-UML-doc -->
	 * @param companyMasterForm
	 * @param identifierList
	 * @param companyTradeList
	 * @param parentCompanyList
	 * @return
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public String saveCompanyMaster(final ErrorfulFieldGroup companyMasterForm,
			final List<CompanyCrtIdentifierDTO> identifierList,
			final List<CompanyMasterDTO> companyTradeList,
			final List<CompanyMasterDTO> parentCompanyList,
                        final List<NotesDTO> availableUploadedInformation, 
                        final String addedNotes,final List<NotesDTO> removeDetailsList);

	/** 
	 * <!-- begin-UML-doc -->
	 * To&nbsp;Save&nbsp;identifiers&nbsp;list.<br><br>@param&nbsp;identifierList&nbsp;the&nbsp;identifier&nbsp;list<br>@param&nbsp;result&nbsp;the&nbsp;result<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;Exception&nbsp;the&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param identifierList
	 * @param result
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void saveIdentifiersList(
			final List<CompanyCrtIdentifierDTO> identifierList,
			final CompanyMaster result) throws SystemException,
			PortalException, Exception;

	/** 
	 * <!-- begin-UML-doc -->
	 * Save&nbsp;company&nbsp;trade&nbsp;class.<br><br>@param&nbsp;companyTradeList&nbsp;the&nbsp;company&nbsp;trade&nbsp;list<br>@param&nbsp;result&nbsp;the&nbsp;result
	 * <!-- end-UML-doc -->
	 * @param companyTradeList
	 * @param result
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void saveCompanyTradeClass(
			final List<CompanyMasterDTO> companyTradeList,
			final CompanyMaster result);

	/** 
	 * <!-- begin-UML-doc -->
	 * Save&nbsp;parent&nbsp;company&nbsp;details.<br><br>@param&nbsp;parentCompanyList&nbsp;the&nbsp;parent&nbsp;company&nbsp;list<br>@param&nbsp;result&nbsp;the&nbsp;result
	 * <!-- end-UML-doc -->
	 * @param parentCompanyList
	 * @param result
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void saveParentCompanyDetails(
			final List<CompanyMasterDTO> parentCompanyList,
			final CompanyMaster result);

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;CompanyCrtIdentifier&nbsp;model&nbsp;from&nbsp;CompanyCrtIdentifierDTO.<br><br>@param&nbsp;form&nbsp;the&nbsp;form<br>@return&nbsp;the&nbsp;company&nbsp;crt&nbsp;identifier<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;Exception&nbsp;the&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param form
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public CompanyIdentifier getCompanyCrtIdentifier(
			final CompanyCrtIdentifierDTO form) throws SystemException,
			PortalException, Exception;

	/** 
	 * <!-- begin-UML-doc -->
	 * Delete&nbsp;company&nbsp;master&nbsp;by&nbsp;id.<br><br>@param&nbsp;companySystemId&nbsp;the&nbsp;company&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;company&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;Exception&nbsp;the&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param companySystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public CompanyMaster deleteCompanyMasterById(final int companySystemId)
			throws SystemException, PortalException, Exception;

	/** 
	 * <!-- begin-UML-doc -->
	 * Delete&nbsp;crt&nbsp;identifier.<br><br>@param&nbsp;identifierSystemId&nbsp;the&nbsp;identifier&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;list<&nbsp;company&nbsp;crt&nbsp;identifier&nbsp;dt&nbsp;o><br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param identifierSystemId
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<CompanyCrtIdentifierDTO> deleteCrtIdentifier(
			final int identifierSystemId) throws PortalException,
			SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Delete&nbsp;crt&nbsp;qualifer&nbsp;by&nbsp;qualifier&nbsp;ID.<br><br>@param&nbsp;qualifierId&nbsp;the&nbsp;qualifier&nbsp;id<br>@return&nbsp;the&nbsp;list<&nbsp;company&nbsp;crt&nbsp;identifier&nbsp;dt&nbsp;o><br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param qualifierId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<CompanyCrtIdentifierDTO> deleteCrtQualifer(final int qualifierId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Save&nbsp;crt&nbsp;qualifier.<br><br>@param&nbsp;binder&nbsp;the&nbsp;binder<br>@return&nbsp;the&nbsp;list&nbsp;of&nbsp;CompanyCrtIdentifierDTO<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param binder
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<CompanyCrtIdentifierDTO> saveCrtQualifer(
			final ErrorfulFieldGroup binder) throws PortalException,
			SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;company&nbsp;crt&nbsp;qualifier&nbsp;by&nbsp;qualifier&nbsp;name.<br><br>@param&nbsp;companyCrtQualifierName&nbsp;the&nbsp;company&nbsp;crt&nbsp;qualifier&nbsp;name<br>@return&nbsp;the&nbsp;company&nbsp;crt&nbsp;qualifier&nbsp;by&nbsp;qualifier&nbsp;name
	 * <!-- end-UML-doc -->
	 * @param companyCrtQualifierName
	 * @return
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public CompanyQualifier getCompanyCrtQualifierByQualifierName(
			final String companyCrtQualifierName);

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;list&nbsp;of&nbsp;CompanyCrtIdentifier&nbsp;from&nbsp;dynamic&nbsp;query.<br><br>@param&nbsp;query&nbsp;the&nbsp;query<br>@return&nbsp;the&nbsp;company&nbsp;crt&nbsp;identifierlist
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<CompanyIdentifier> getCompanyCrtIdentifierlist(
			final DynamicQuery query);

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;company&nbsp;master&nbsp;by&nbsp;system&nbsp;id.<br><br>@param&nbsp;companySystemId&nbsp;the&nbsp;company&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;company&nbsp;master&nbsp;by&nbsp;system&nbsp;id
	 * <!-- end-UML-doc -->
	 * @param companySystemId
	 * @return
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public CompanyMaster getCompanyMasterBySystemId(final int companySystemId);

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;company&nbsp;master&nbsp;list.<br><br>@param&nbsp;query&nbsp;the&nbsp;query<br>@return&nbsp;the&nbsp;company&nbsp;master&nbsp;list
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<CompanyMaster> getCompanyMasterList(final DynamicQuery query);

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;parent&nbsp;company&nbsp;table.<br><br>@param&nbsp;companySystemId&nbsp;the&nbsp;company&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;parent&nbsp;company&nbsp;table
	 * <!-- end-UML-doc -->
	 * @param companySystemId
	 * @return
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<CompanyMasterDTO> getParentCompanyTable(
			final int companySystemId);

	/** 
	 * <!-- begin-UML-doc -->
	 * Delete&nbsp;company&nbsp;trade&nbsp;class&nbsp;for&nbsp;update.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;system&nbsp;id<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void deleteCompanyTradeClassForUpdate(final int systemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Delete&nbsp;parent&nbsp;company&nbsp;for&nbsp;update.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;system&nbsp;id<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void deleteParentCompanyForUpdate(final int systemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Gets&nbsp;the&nbsp;company&nbsp;parent&nbsp;details&nbsp;list.<br><br>@param&nbsp;query&nbsp;the&nbsp;query<br>@return&nbsp;the&nbsp;company&nbsp;parent&nbsp;details&nbsp;list
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<CompanyParentDetails> getCompanyParentDetailsList(
			final DynamicQuery query);
}