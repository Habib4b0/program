/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.dao;

import com.stpl.app.model.HelperTable;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author mohamed.hameed
 */
public interface CommonDao {

    public List executeSelect(String query);
    /** 
    * <!-- begin-UML-doc -->
    * <!-- end-UML-doc -->
    * @param ifpDynamicQuery
    * @return
    * @throws PortalException
    * @throws SystemException
    * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
    */
   List getBrandList(DynamicQuery ifpDynamicQuery) throws PortalException;
   /** 
    * <!-- begin-UML-doc -->
    * <br>@param&nbsp;cfpDynamicQuery<br>@return&nbsp;
    * <!-- end-UML-doc -->
    * @param cfpDynamicQuery
    * @return
    * @throws PortalException
    * @throws SystemException
    * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
    */
   List itemIrtQualifierNameList(DynamicQuery cfpDynamicQuery)
                   throws PortalException;
   /** 
    * <!-- begin-UML-doc -->
    * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;HelperTable<br>@return&nbsp;list&nbsp;of&nbsp;HelperTable<br>@throws&nbsp;SystemException
    * <!-- end-UML-doc -->
    * @param query
    * @return
    * @throws PortalException
    * @throws SystemException
    * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
    */
   List<HelperTable> getHelperTableList(DynamicQuery query)
                   throws PortalException;

   /** 
    * <!-- begin-UML-doc -->
    * Returns&nbsp;the&nbsp;list&nbsp;of&nbsp;HelperTable&nbsp;by&nbsp;list&nbsp;name.<br><br>@param&nbsp;listName&nbsp;the&nbsp;list&nbsp;name<br>@return&nbsp;list&nbsp;of&nbsp;helperTable<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
    * <!-- end-UML-doc -->
    * @param listName
    * @return
    * @throws PortalException
    * @throws SystemException
    * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
    */
   List<HelperTable> getHelperTableDetailsByListName()
                   throws PortalException;
   
}
