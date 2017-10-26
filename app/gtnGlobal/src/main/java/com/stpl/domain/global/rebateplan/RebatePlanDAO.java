/**
 * 
 */
package com.stpl.domain.global.rebateplan;

import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;
import com.stpl.app.model.RebatePlanMaster;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.RebatePlanTier;
import com.stpl.app.model.RebateTierFormula;

/** 
 * <!-- begin-UML-doc -->
 * Defines&nbsp;DAO&nbsp;operations&nbsp;for&nbsp;RebatePlanSearchLogic<br><br>@author&nbsp;shrihariharan
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface RebatePlanDAO {
	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;retrieve&nbsp;the&nbsp;rebatePlan&nbsp;value&nbsp;based&nbsp;on&nbsp;the&nbsp;dynamic&nbsp;query<br>created<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;RebatePlanMaster<br>@return&nbsp;list&nbsp;of&nbsp;type&nbsp;RebatePlanMaster<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<RebatePlanMaster> getRebatePlanMasterList(DynamicQuery query)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;retrieve&nbsp;the&nbsp;rebatePlan&nbsp;master&nbsp;based&nbsp;on&nbsp;the&nbsp;systemID<br><br>@param&nbsp;systemId<br>@return&nbsp;object&nbsp;of&nbsp;rebatePlan<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	RebatePlanMaster getRebatePlanMasterBySystemId(int systemId)
			throws SystemException, PortalException;

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
	 * This&nbsp;method&nbsp;will&nbsp;fetch&nbsp;the&nbsp;rebatePlan&nbsp;master&nbsp;based&nbsp;on&nbsp;the&nbsp;systemID<br><br>@param&nbsp;systemId<br>@return&nbsp;object&nbsp;of&nbsp;rebatePlan<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param rebatePlanSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	RebatePlanMaster fetchRebatePlanMaster(int rebatePlanSystemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;update&nbsp;the&nbsp;edited&nbsp;rebatePlan&nbsp;based&nbsp;on&nbsp;the&nbsp;systemId<br><br>@param&nbsp;rebatePlanMaster<br>@return&nbsp;object&nbsp;of&nbsp;rebatePlan<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param rebatePlanMaster
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	RebatePlanMaster updateRebatePlanMaster(RebatePlanMaster rebatePlanMaster)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;save&nbsp;the&nbsp;newly&nbsp;created&nbsp;rebatePlan<br><br>@param&nbsp;rebatePlanMaster<br>@return&nbsp;object&nbsp;of&nbsp;rebatePlan<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param rebatePlanMaster
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	RebatePlanMaster saveRebatePlanMaster(RebatePlanMaster rebatePlanMaster)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;retrieve&nbsp;the&nbsp;rebatePlanTier&nbsp;value&nbsp;based&nbsp;on&nbsp;the&nbsp;dynamic<br>query&nbsp;passed&nbsp;as&nbsp;an&nbsp;argument<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;RebatePlanTier<br>@return&nbsp;list&nbsp;of&nbsp;type&nbsp;RebatePlanTier<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<RebatePlanTier> getRebatePlanTierList(DynamicQuery query)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;is&nbsp;to&nbsp;delete&nbsp;the&nbsp;selected&nbsp;rebatePlanTier&nbsp;based&nbsp;on&nbsp;the<br>rebatePlanTierSystemId<br><br>@param&nbsp;rebatePlanTierSystemId<br>@return&nbsp;object&nbsp;of&nbsp;type&nbsp;RebatePlanTier<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param rebatePlanTierSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	RebatePlanTier deleteRebatePlanTier(int rebatePlanTierSystemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;save&nbsp;the&nbsp;newly&nbsp;created&nbsp;rebatePlanTier<br><br>@param&nbsp;rebatePlanTier<br>@return&nbsp;object&nbsp;of&nbsp;type&nbsp;RebatePlanTier<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param rebatePlanTier
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	RebatePlanTier saveRebatePlanTier(RebatePlanTier rebatePlanTier)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;is&nbsp;to&nbsp;delete&nbsp;the&nbsp;selected&nbsp;rebatePlan&nbsp;based&nbsp;on&nbsp;the<br>rebatePlanMasterSystemId<br><br>@param&nbsp;rebatePlanMasterSystemId<br>@return&nbsp;RebatePlanMaster&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param rebatePlanMasterSystemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	RebatePlanMaster deleteRebatePlanMaster(int rebatePlanMasterSystemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;return&nbsp;the&nbsp;count&nbsp;of&nbsp;the&nbsp;rebateplan&nbsp;records&nbsp;based&nbsp;on&nbsp;the<br>query&nbsp;passed<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;RebatePlanMaster<br>@return&nbsp;count&nbsp;of&nbsp;the&nbsp;records<br>@throws&nbsp;SystemException
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
	 * This&nbsp;method&nbsp;will&nbsp;retrieve&nbsp;the&nbsp;rebateTierFormula&nbsp;based&nbsp;on&nbsp;the&nbsp;query&nbsp;passed<br><br>@param&nbsp;systemId<br>@return&nbsp;list&nbsp;of&nbsp;tierFormulas&nbsp;of&nbsp;type&nbsp;String<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List<String> getTierFormula(DynamicQuery query) throws SystemException,
			PortalException;

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
	 * This&nbsp;method&nbsp;will&nbsp;retrieve&nbsp;the&nbsp;rebateTierFormula&nbsp;based&nbsp;on&nbsp;the&nbsp;systemId<br><br>@param&nbsp;systemId<br>@return&nbsp;object&nbsp;of&nbsp;RebateTierFormula<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param systemId
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	RebateTierFormula getRebateTierFormula(int systemId)
			throws SystemException, PortalException;

	/** 
	 * <!-- begin-UML-doc -->
	 * This&nbsp;method&nbsp;will&nbsp;retrieve&nbsp;the&nbsp;list&nbsp;of&nbsp;rebateTierFormula&nbsp;based&nbsp;on&nbsp;the<br>query&nbsp;passed<br><br>@param&nbsp;systemId<br>@return&nbsp;list&nbsp;of&nbsp;type&nbsp;tierFormula<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getTierFormulaIdList(DynamicQuery query) throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int getRebatePlanMasterTotalCount() throws SystemException;
}