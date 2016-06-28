/**
 * 
 */
package com.stpl.domain.contract.contractdashboard.globalcontract;

import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;
import com.stpl.app.contract.global.dto.ItemMasterDTO;
import com.vaadin.data.Container;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/** 
 * <!-- begin-UML-doc -->
 * Logic&nbsp;for&nbsp;ItemFamilyPlan.<br><br>@author
 * <!-- end-UML-doc -->
 * @author Sibi
 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface ItemFamilyplanLogic {
	/** 
	 * <!-- begin-UML-doc -->
	 * Returns&nbsp;the&nbsp;List&nbsp;of&nbsp;ItemMasterDTO&nbsp;based&nbsp;on&nbsp;search&nbsp;field.<br><br>@param&nbsp;searchField&nbsp;-&nbsp;String&nbsp;contains&nbsp;Item&nbsp;Details.<br>@param&nbsp;value&nbsp;-&nbsp;Value&nbsp;for&nbsp;WildCard<br>@return&nbsp;List&nbsp;of&nbsp;ItemMasterDTO.<br>@throws&nbsp;SystemException
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
	public List<ItemMasterDTO> getItemForIFP(final String searchField,
			final String val, int start, int end, List<SortByColumn> columns,final Set<Container.Filter> filterSet)
			throws SystemException;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param searchField
	 * @param searchValue
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void saveItemDetails(Object searchField, String searchValue)
			throws SystemException;
}