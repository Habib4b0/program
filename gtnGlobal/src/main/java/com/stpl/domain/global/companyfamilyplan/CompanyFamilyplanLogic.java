/**
 * 
 */
package com.stpl.domain.global.companyfamilyplan;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.cfp.dto.CFPCompanyDTO;
import com.stpl.app.model.CfpModel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

import java.text.ParseException;
import java.util.List;

import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/** 
 * <!-- begin-UML-doc -->
 * This&nbsp;class&nbsp;contains&nbsp;all&nbsp;business&nbsp;logic&nbsp;methods&nbsp;for&nbsp;the&nbsp;Company&nbsp;Family&nbsp;Plan.<br><br>@author&nbsp;pvinoth
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface CompanyFamilyplanLogic {
	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;searched&nbsp;CFP&nbsp;list&nbsp;.<br><br>@param&nbsp;companyFamilyplanMaster&nbsp;-&nbsp;binder&nbsp;object&nbsp;holds&nbsp;the&nbsp;value&nbsp;entered&nbsp;in<br>the&nbsp;screen<br>@param&nbsp;start&nbsp;-&nbsp;start&nbsp;limit<br>@param&nbsp;end&nbsp;-&nbsp;end&nbsp;limit<br>@param&nbsp;orderByColumns&nbsp;-&nbsp;columns&nbsp;going&nbsp;to&nbsp;get&nbsp;sorted<br>@return&nbsp;list&nbsp;of&nbsp;CFPDTO&nbsp;objects
	 * <!-- end-UML-doc -->
	 * @param companyFamilyplanMaster
	 * @param start
	 * @param end
	 * @param orderByColumns
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<SearchResultsDTO> searchCFP(
			final ErrorfulFieldGroup companyFamilyplanMaster, final int start,
			final int end, final List<OrderByColumn> orderByColumns,final BeanSearchCriteria criteria)
			throws PortalException, SystemException, ParseException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;CompanyFamilyplanMaster&nbsp;for&nbsp;given&nbsp;CompanyFamilyplanMasterId.<br><br>@param&nbsp;companyFamilyPlanSystemId&nbsp;-&nbsp;primary&nbsp;key<br>@return&nbsp;CompanyFamilyplanMaster&nbsp;modal&nbsp;obejct<br>@throws&nbsp;StplR2Exception&nbsp;the&nbsp;stpl&nbsp;r2&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param companyFamilyPlanSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws ParseException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public CfpModel getCompanyMasterById(final int companyFamilyPlanSystemId)
			throws SystemException, PortalException, ParseException, ParseException;

	/** 
	 * <!-- begin-UML-doc -->
	 * method&nbsp;to&nbsp;save&nbsp;CompanyMaster.<br><br>@param&nbsp;cfpForm&nbsp;the&nbsp;cfp&nbsp;form<br>@param&nbsp;ifpList&nbsp;the&nbsp;ifp&nbsp;list<br>@return&nbsp;the&nbsp;string<br>@throws&nbsp;StplR2Exception&nbsp;the&nbsp;stpl&nbsp;r2&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param cfpForm
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public String saveCompanyMaster(final ErrorfulFieldGroup cfpForm, final List<NotesDTO> availableUploadedInformation, final String addedNotes,final List<NotesDTO> removedDetailsList)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * save&nbsp;the&nbsp;companies&nbsp;to&nbsp;the&nbsp;table.<br><br>@param&nbsp;ifpList&nbsp;the&nbsp;ifp&nbsp;list<br>@param&nbsp;companyFamilyPlanMaster&nbsp;the&nbsp;company&nbsp;family&nbsp;plan&nbsp;master<br>@throws&nbsp;StplR2Exception&nbsp;the&nbsp;stpl&nbsp;r2&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param companyFamilyPlanMaster
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void saveCompaniesList(final CfpModel companyFamilyPlanMaster)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * To&nbsp;delete&nbsp;the&nbsp;CFP&nbsp;Details&nbsp;by&nbsp;SystemID.<br><br>@param&nbsp;cfpSystemId&nbsp;the&nbsp;cfp&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;string<br>@throws&nbsp;com.stpl.portal.kernel.exception.PortalException<br>@throws&nbsp;com.stpl.portal.kernel.exception.SystemException
	 * <!-- end-UML-doc -->
	 * @param cfpSystemId
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public String deleteCFPDetailsById(final int cfpSystemId)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * To&nbsp;delete&nbsp;the&nbsp;CFP&nbsp;Details&nbsp;in&nbsp;Master&nbsp;Table&nbsp;by&nbsp;SystemID.<br><br>@param&nbsp;cfpSystemId&nbsp;the&nbsp;cfp&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;company&nbsp;familyplan&nbsp;master<br>@throws&nbsp;StplR2Exception&nbsp;the&nbsp;stpl&nbsp;r2&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param cfpSystemId
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public CfpModel deleteCFPMasterById(final int cfpSystemId)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * returns&nbsp;the&nbsp;CompanyFamilyplanMaster&nbsp;by&nbsp;cfpId.<br><br>@param&nbsp;cfpId&nbsp;the&nbsp;cfp&nbsp;id<br>@return&nbsp;the&nbsp;CFP&nbsp;by&nbsp;id<br>@throws&nbsp;StplR2Exception&nbsp;the&nbsp;stpl&nbsp;r2&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param cfpId
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public CFPCompanyDTO getCFPById(final int cfpId) throws SystemException, PortalException;

	
}