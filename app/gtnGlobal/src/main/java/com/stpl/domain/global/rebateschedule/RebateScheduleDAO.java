/**
 * 
 */
package com.stpl.domain.global.rebateschedule;

import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;
import com.stpl.app.model.RsModel;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.app.model.RsDetails;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.RebatePlanMaster;
import com.stpl.app.model.RebateTierFormula;

/** 
 * <!-- begin-UML-doc -->
 * Defines&nbsp;DAO&nbsp;operations&nbsp;for&nbsp;RebateScheduleLogic<br><br>@author&nbsp;shrihariharan
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface RebateScheduleDAO {
	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;retrieve&nbsp;the&nbsp;list&nbsp;of&nbsp;rebateSchedule&nbsp;based&nbsp;on&nbsp;the&nbsp;query<br>passed<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;RebateScheduleMaster<br>@return&nbsp;list&nbsp;of&nbsp;type&nbsp;RebateScheduleMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<RsModel> getRebateScheduleMasterList(DynamicQuery query)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;retrieve&nbsp;the&nbsp;list&nbsp;of&nbsp;rebateScheduleDetails&nbsp;based&nbsp;on&nbsp;the<br>query&nbsp;passed<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;RebateScheduleDetails<br>@return&nbsp;list&nbsp;of&nbsp;type&nbsp;RebateScheduleDetails<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<RsDetails> getRebateScheduleDetailsList(DynamicQuery query)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;retrieve&nbsp;the&nbsp;rebateSchedule&nbsp;based&nbsp;on&nbsp;the&nbsp;systemId<br><br>@param&nbsp;systemId<br>@return&nbsp;object&nbsp;of&nbsp;type&nbsp;RebateScheduleMaster<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	RsModel getRebateScheduleMasterBySystemId(int systemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;save&nbsp;the&nbsp;rebateSchedule&nbsp;into&nbsp;the&nbsp;database<br><br>@param&nbsp;rebateScheduleMaster<br>@return&nbsp;object&nbsp;of&nbsp;type&nbsp;RebateScheduleMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param rebateScheduleMaster
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	RsModel saveRebateScheduleMaster(RsModel rebateScheduleMaster)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;save&nbsp;the&nbsp;rebateScheduleDetails&nbsp;that&nbsp;has&nbsp;been&nbsp;attached&nbsp;to<br>the&nbsp;rebateSchedule&nbsp;into&nbsp;the&nbsp;database<br><br>@param&nbsp;rebateScheduledetail<br>@return&nbsp;object&nbsp;of&nbsp;type&nbsp;RebateScheduleDetails<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param rebateScheduledetail
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	RsDetails saveRebateScheduleDetails(RsDetails rebateScheduledetail)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;delete&nbsp;the&nbsp;rebateSchedule&nbsp;based&nbsp;on&nbsp;the&nbsp;systemId<br><br>@param&nbsp;rebateScheduleMasterSystemId<br>@return&nbsp;object&nbsp;of&nbsp;type&nbsp;RebateScheduleMaster<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param rebateScheduleMasterSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	RsModel deleteRebateScheduleMaster(int rebateScheduleMasterSystemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;delete&nbsp;the&nbsp;rebateScheduleDetails&nbsp;based&nbsp;on&nbsp;the&nbsp;systemId<br><br>@param&nbsp;rebateScheduleDetails<br>@return&nbsp;object&nbsp;of&nbsp;type&nbsp;RebateScheduleDetails<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param rebateScheduleDetails
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	RsDetails deleteRebateScheduleDetails(RsDetails rebateScheduleDetails)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <br>@param&nbsp;rebateScheduleDetails<br>@return<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException&nbsp;
	 * <!-- end-UML-doc -->
	 * @param rebateScheduleDetails
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	RsDetails updateRebateScheduleDetails(RsDetails rebateScheduleDetails)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;update&nbsp;the&nbsp;edited&nbsp;rebateSchedule&nbsp;into&nbsp;the&nbsp;database<br><br>@param&nbsp;rebateScheduleMaster<br>@return&nbsp;object&nbsp;of&nbsp;type&nbsp;RebateScheduleMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param rebateScheduleMaster
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	RsModel updateRebateScheduleMaster(RsModel rebateScheduleMaster)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;fetch&nbsp;the&nbsp;rebateSchedule&nbsp;based&nbsp;on&nbsp;the&nbsp;systemId<br><br>@param&nbsp;systemId<br>@return&nbsp;object&nbsp;of&nbsp;type&nbsp;RebateScheduleMaster<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	RsModel fetchRebateScheduleMaster(int systemId) throws SystemException,
			PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;retrieve&nbsp;the&nbsp;Helper&nbsp;Table&nbsp;value&nbsp;based&nbsp;on&nbsp;the&nbsp;dynamic<br>query&nbsp;created<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;HelperTable<br>@return&nbsp;list&nbsp;of&nbsp;type&nbsp;HelperTable<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<HelperTable> getHelperTableList(DynamicQuery query)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;retrieve&nbsp;the&nbsp;values&nbsp;from&nbsp;Helper&nbsp;Table&nbsp;based&nbsp;on&nbsp;the<br>listName<br><br>@param&nbsp;listName<br>@return&nbsp;list&nbsp;of&nbsp;type&nbsp;HelperTable<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param listName
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<HelperTable> getHelperTableDetailsByListName(String listName)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;retrieve&nbsp;the&nbsp;list&nbsp;of&nbsp;IFP&nbsp;based&nbsp;on&nbsp;the&nbsp;query&nbsp;passed<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;ItemFamilyplanMaster<br>@return&nbsp;list&nbsp;of&nbsp;type&nbsp;ItemFamilyplanMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<IfpModel> getItemFamilyplanMasterList(DynamicQuery query)
			throws SystemException;


	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;fetch&nbsp;the&nbsp;IFP&nbsp;based&nbsp;on&nbsp;the&nbsp;systemId<br><br>@param&nbsp;systemId<br>@return&nbsp;object&nbsp;of&nbsp;type&nbsp;ItemFamilyplanMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	IfpModel fetchItemFamilyplanMaster(int systemId) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;retrieve&nbsp;rebateSchedule&nbsp;based&nbsp;on&nbsp;the&nbsp;systemID&nbsp;passed<br><br>@param&nbsp;systemId<br>@return&nbsp;list&nbsp;that&nbsp;contains&nbsp;rebateSchedule&nbsp;values<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getRebateScheduleDetails(int systemId,Object future1,Object future2) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;return&nbsp;the&nbsp;list&nbsp;of&nbsp;rebatePlan&nbsp;records&nbsp;within&nbsp;the<br>particular&nbsp;limit<br><br>@param&nbsp;startIndex<br>@param&nbsp;endindex<br>@return&nbsp;list&nbsp;of&nbsp;type&nbsp;rebatePlan<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param startIndex
	 * @param endindex
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<RebatePlanMaster> getRebatePlanMasterByLimit(int startIndex,
			int endindex) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;return&nbsp;the&nbsp;count&nbsp;of&nbsp;the&nbsp;rebateSchedule&nbsp;records&nbsp;based&nbsp;on<br>the&nbsp;query&nbsp;passed<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;RebatePlanMaster<br>@return&nbsp;count&nbsp;of&nbsp;the&nbsp;records<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	int getRebatePlanMasterQueryCount(DynamicQuery query)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;return&nbsp;the&nbsp;list&nbsp;of&nbsp;rebateSchedule&nbsp;records&nbsp;within&nbsp;the<br>particular&nbsp;limit<br><br>@param&nbsp;startIndex<br>@param&nbsp;endindex<br>@return&nbsp;list&nbsp;of&nbsp;type&nbsp;rebatePlan<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param startIndex
	 * @param endindex
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<RsModel> getRebateScheduleMasterByLimit(int startIndex, int endindex)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;retrieve&nbsp;the&nbsp;list&nbsp;of&nbsp;rebateTierFormula&nbsp;based&nbsp;on&nbsp;the<br>query&nbsp;passed<br><br>@param&nbsp;systemId<br>@return&nbsp;list&nbsp;of&nbsp;type&nbsp;tierFormula<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<RebateTierFormula> getTierFormulaList(DynamicQuery query)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;retrieve&nbsp;the&nbsp;list&nbsp;of&nbsp;formula&nbsp;id&nbsp;based&nbsp;on&nbsp;the<br>query&nbsp;passed<br><br>@param&nbsp;rsDynamicQuery<br>@return&nbsp;list&nbsp;of&nbsp;type&nbsp;formula&nbsp;id<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param rsDynamicQuery
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getFormulaIdList(DynamicQuery rsDynamicQuery) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <br>@param&nbsp;dynamicQuery<br>@return<br>@throws&nbsp;PortalException<br>@throws&nbsp;SystemException&nbsp;
	 * <!-- end-UML-doc -->
	 * @param dynamicQuery
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<Object[]> getRebatePlanList(DynamicQuery dynamicQuery)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param cfpDynamicQuery
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getRebatePlanNameList(DynamicQuery cfpDynamicQuery)
			throws PortalException, SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int getRebatePlanMasterTotalCount() throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int getRebateScheduleMasterTotalCount() throws SystemException;
	
	  /**
		 * This method will return the count of the rebateSchedule records based on
		 * the query passed
		 * 
		 * @param query
		 *            - dynamic query of RebatePlanMaster
		 * @return count of the records
		 * @throws SystemException
		 */
		int getRsModelQueryCount(DynamicQuery query) throws SystemException;
                
                int getRsDetailsQueryCount(DynamicQuery query) throws SystemException;
                
                List<Integer> getRebateScheduleSIdList(DynamicQuery query) throws SystemException;
}