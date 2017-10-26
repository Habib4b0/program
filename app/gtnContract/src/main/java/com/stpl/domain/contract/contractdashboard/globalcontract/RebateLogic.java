/**
 * 
 */
package com.stpl.domain.contract.contractdashboard.globalcontract;

import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;
import com.stpl.app.contract.global.dto.RebateScheduleMasterDTO;
import com.stpl.app.contract.ui.ErrorfulFieldGroup;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/** 
 * <!-- begin-UML-doc -->
 * Logic&nbsp;for&nbsp;Rebate&nbsp;Schedule.<br><br>@author
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface RebateLogic {
	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;a&nbsp;list&nbsp;of&nbsp;RebateScheduleMasterDTO&nbsp;object&nbsp;based&nbsp;on&nbsp;the&nbsp;field<br>group,starting&nbsp;and&nbsp;ending&nbsp;index.<br><br>@param&nbsp;rebateScheduleForm&nbsp;-&nbsp;FieldGroup&nbsp;that&nbsp;holds&nbsp;the&nbsp;values&nbsp;of&nbsp;form.<br>@param&nbsp;start&nbsp;-&nbsp;Starting&nbsp;Index<br>@param&nbsp;end&nbsp;-&nbsp;Ending&nbsp;Index.<br>@param&nbsp;columns&nbsp;-&nbsp;List&nbsp;of&nbsp;OrderBYColumn&nbsp;object<br>@return&nbsp;List&nbsp;of&nbsp;RebateScheduleMasterDTO<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param rebateScheduleForm
	 * @param start
	 * @param end
	 * @param columns
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<RebateScheduleMasterDTO> searchRebateScheduleMaster(
			final ErrorfulFieldGroup rebateScheduleForm,BeanSearchCriteria criteria, final int start,
			final int end, final List<OrderByColumn> columns)
			throws SystemException;
}