/**
 * 
 */
package com.stpl.domain.contract.contractdashboard;

import java.util.List;

import com.stpl.app.contract.contractheader.dto.ContractAliasMasterDTO;
import com.stpl.app.contract.contractheader.dto.ContractMasterDTO;
import com.stpl.app.contract.global.dto.CFPCompanyDTO;
import com.stpl.app.contract.global.dto.RsItemDetailsDTO;
import com.stpl.app.contract.global.dto.VwContractPriceInfoDTO;
import com.stpl.app.model.ContractMaster;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.search.ParseException;

/** 
 * <!-- begin-UML-doc -->
 * The&nbsp;Class&nbsp;DashBoardLogic.
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface ContractBuilderLogic {
	/** 
	 * <!-- begin-UML-doc -->
	 * to&nbsp;search&nbsp;Contract&nbsp;Master<br><br>@param&nbsp;searchItemForm<br>@return<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param searchItemForm
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<ContractMasterDTO> searchContractMaster(
			final CustomFieldGroup searchItemForm) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Method&nbsp;used&nbsp;for&nbsp;Save&nbsp;contract&nbsp;alias&nbsp;master&nbsp;list.<br><br>@param&nbsp;aliasList&nbsp;the&nbsp;alias&nbsp;list<br>@param&nbsp;result&nbsp;the&nbsp;result<br>@throws&nbsp;SystemException
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
	 * Method&nbsp;used&nbsp;for&nbsp;Save&nbsp;contract&nbsp;dash&nbsp;board.<br><br>@param&nbsp;contractMB&nbsp;the&nbsp;contract&nbsp;mb<br>@param&nbsp;aliasMRB&nbsp;the&nbsp;alias&nbsp;mrb<br>@param&nbsp;cfpResultsBean&nbsp;the&nbsp;cfp&nbsp;results&nbsp;bean<br>@param&nbsp;itemDRB&nbsp;the&nbsp;item&nbsp;drb<br>@param&nbsp;rebateBinder&nbsp;the&nbsp;rebate&nbsp;binder<br>@param&nbsp;rsDRB&nbsp;the&nbsp;rs&nbsp;drb<br>@return&nbsp;the&nbsp;string<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param contractMB
	 * @param aliasMRB
	 * @param cfpResultsBean
	 * @param itemDRB
	 * @param rebateBinder
	 * @param rsDRB
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws ParseException
	 * @throws ParseException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public String saveContractDashBoard(final CustomFieldGroup contractMB,
			final List<ContractAliasMasterDTO> aliasMRB,
			final List<CFPCompanyDTO> cfpResultsBean,
			final List<VwContractPriceInfoDTO> itemDRB,
			final CustomFieldGroup rebateBinder,
			final List<RsItemDetailsDTO> rsDRB) throws SystemException,
			ParseException, java.text.ParseException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Method&nbsp;used&nbsp;for&nbsp;Save&nbsp;master&nbsp;details.<br><br>@param&nbsp;contractMF&nbsp;the&nbsp;contract&nbsp;mf<br>@param&nbsp;aliasMasterBeans&nbsp;the&nbsp;alias&nbsp;master&nbsp;beans<br>@param&nbsp;contractsystemId&nbsp;the&nbsp;contractsystem&nbsp;id<br>@throws&nbsp;PortalException<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param contractMF
	 * @param aliasMasterBeans
	 * @param contractsystemId
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void saveMasterDetails(final CustomFieldGroup contractMF,
			final List<ContractAliasMasterDTO> aliasMasterBeans,
			final int contractsystemId) throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;contract&nbsp;master&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;contractId&nbsp;the&nbsp;contract&nbsp;id<br>@return&nbsp;the&nbsp;contract&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
	 * <!-- end-UML-doc -->
	 * @param contractId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public ContractMaster getContractMaster(final int contractId)
			throws SystemException, PortalException;
}