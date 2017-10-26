/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.dao;

import com.stpl.app.model.DemandForecast;
import java.util.List;

import com.stpl.app.model.FileManagement;
import com.stpl.app.model.ForecastingMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

// TODO: Auto-generated Javadoc
/**
 * The Interface FileManagementLogicDAO.
 *
 * @author sriram
 */
public interface FileManagementLogicDAO {

	/**
	 * Gets the forecast list.
	 *
	 * @param query
	 *            the query
	 * @return the forecast list
	 * @throws Exception
	 *             the exception
	 */
	List getForecastList(DynamicQuery query) throws SystemException;

	/**
	 * Gets the forecast details.
	 *
	 * @param fileName
	 *            the file name
	 * @param version
	 *            the version
	 * @param fileType
	 *            the file type
	 * @param country
	 *            the country
	 * @return the forecast details
	 * @throws Exception
	 *             the exception
	 */
	List getForecastDetails(String fileName, String version, String fileType, String country,int year) throws SystemException;

	/**
	 * Gets the files list.
	 *
	 * @param query
	 *            the query
	 * @return the files list
	 * @throws Exception
	 *             the exception
	 */
	List getFilesList(DynamicQuery query) throws SystemException;

	/**
	 * Update files.
	 *
	 * @param file
	 *            the file
	 * @throws Exception
	 *             the exception
	 */
	void updateFiles(FileManagement file) throws SystemException;

	/**
	 * Adds the files.
	 *
	 * @param file
	 *            the file
	 * @throws Exception
	 *             the exception
	 */
	void addFiles(FileManagement file) throws SystemException;
        
        
        /**
	 * Adds the Forecast details.
	 *
	 * @param master
	 *            the file
     * @throws com.stpl.portal.kernel.exception.SystemException
	 * @throws Exception
	 *             the exception
	 */
        
        void addForecastDetails(ForecastingMaster master) throws SystemException;
        void addDemandDetails(DemandForecast master) throws SystemException;
        /**
         * 
     * @param query
     * @param udc1
     * @param udc2
         */
        Object executeSelectQuery(String query, Object udc1, Object udc2)  throws SystemException;
        
            
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
			throws PortalException, SystemException;
        /** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param ifpDynamicQuery
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	List getBrandList(DynamicQuery ifpDynamicQuery) throws PortalException,
			SystemException;
        
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
			throws PortalException, SystemException;
        /**
        * To get a list of companies from the companyMaster Table
        *
        * @throws com.stpl.portal.kernel.exception.SystemException
        * @table CompanyMaster
        * @param query - dynamic query
        * @return List<CompanyMaster>
        * @throws Exception
        */
       List getCompanyMasterList(DynamicQuery query) throws SystemException;
       
       int getFileManagementCount(final DynamicQuery query) throws SystemException;

}
