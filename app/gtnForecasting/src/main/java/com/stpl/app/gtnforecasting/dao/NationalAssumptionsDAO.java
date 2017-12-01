/**
 * 
 */
package com.stpl.app.gtnforecasting.dao;


import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.NaProjDetails;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import java.util.List;

/** 
 *
 * @author Vinodhini
 */
public interface NationalAssumptionsDAO {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param brandQuery
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getBrandList(DynamicQuery brandQuery) throws PortalException,
			SystemException;
       /** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param therapeuticQuery
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
        List getItemList(DynamicQuery therapeuticQuery) throws PortalException,
			SystemException;
        List<HelperTable> getHelperTable(final DynamicQuery dynamicQuery)
			throws PortalException, SystemException;
        List<ItemMaster> getItemMaster(final DynamicQuery dynamicQuery)
			throws PortalException, SystemException;
        List<NaProjDetails> getNaProjDetails(final DynamicQuery dynamicQuery)
			throws PortalException, SystemException;
	}