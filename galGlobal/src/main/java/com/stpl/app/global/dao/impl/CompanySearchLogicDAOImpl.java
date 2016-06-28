/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.dao.impl;


import com.stpl.app.model.CompanyIdentifier;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.CompanyParentDetails;
import com.stpl.app.model.CompanyQualifier;
import com.stpl.app.model.CompanyTradeClass;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.Udcs;
import com.stpl.app.service.CompanyIdentifierLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.CompanyParentDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyQualifierLocalServiceUtil;
import com.stpl.app.service.CompanyTradeClassLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.UdcsLocalServiceUtil;
import com.stpl.domain.global.company.CompanyMasterDAO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * DAO operations for CompanySearchLogic.
 *
 * @author shrihariharan
 */
public class CompanySearchLogicDAOImpl implements CompanyMasterDAO{

    /**
     * Returns the number of company masters.
     *
     * @return count
     * @throws SystemException the system exception
     */
    public int getCompanyMasterTotalCount() throws SystemException {
        return (int)CompanyMasterLocalServiceUtil.getCompanyMastersCount();
    }
    
    /**
     * Returns a range of all the company masters.
     *
     * @param startIndex - startLimit
     * @param endIndex - endLimit
     * @return list of CompanyMaster
     * @throws SystemException the system exception
     */
    public List<CompanyMaster> getCompanyMasterByLimit(final int startIndex, final int endIndex)throws SystemException{
        return CompanyMasterLocalServiceUtil.getCompanyMasters(startIndex, endIndex);
    }

    
    /**
     * Returns the number of rows that match the dynamic query.
     *
     * @param query - dynamic query of CompanyMaster
     * @return count
     * @throws SystemException the system exception
     */
    public int getCompanyMasterCountByQuery(final DynamicQuery query) throws SystemException {
        return (int) CompanyMasterLocalServiceUtil.dynamicQueryCount(query);
    }

    /**
     * Returns list of company Master List.
     *
     * @param companyId the company id
     * @param companyNo the company no
     * @param companyName the company name
     * @param companyStatus the company status
     * @param companyType the company type
     * @param tradeClass the trade class
     * @param qualifierId the qualifier id
     * @param itemIdentifier the item identifier
     * @return list of Company Master
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public List getCompanyMasterListByQualifierId(final String companyId, final String companyNo, final String companyName, final String companyStatus,final String companyType, final String tradeClass, final int qualifierId, final String itemIdentifier, String orderByColumn, Boolean sortOrder) throws PortalException,SystemException {
      return CompanyMasterLocalServiceUtil.findCompanyMaster(
                    companyId, companyNo, companyName, companyStatus,
                    companyType, tradeClass, qualifierId, itemIdentifier,  orderByColumn,  sortOrder);
    }
    
    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param query - dynamic query of CompanyMaster
     * @return list of Company Master
     * @throws SystemException the system exception
     */
    public List<CompanyMaster> getCompanyMasterList(final DynamicQuery query) throws SystemException {
       return CompanyMasterLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Returns a range of all the company masters.
     *
     * @param companySystemId - primary key
     * @return list of Company Master
     * @throws SystemException the system exception
     */
    public List getCompanyTradeClassBySystemId(final int companySystemId) throws SystemException {
      return CompanyMasterLocalServiceUtil.getCompanyTradeClass(companySystemId);
    }

    /**
     * Returns the company master with the primary key.
     *
     * @param companySystemId - primary key
     * @return CompanyMaster modal object
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public CompanyMaster getCompanyMasterBySystemId(final int companySystemId) throws SystemException,PortalException {
        return CompanyMasterLocalServiceUtil.getCompanyMaster(companySystemId);
    }

    /**
     * Returns the list of CompanyCrtIdentifier.
     *
     * @param companySystemId the company system id
     * @return list of CompanyCrtIdentifier
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public List<CompanyIdentifier> getCompanyIdentifierByCompanySystemId(final int companySystemId) throws SystemException, PortalException {
       return CompanyIdentifierLocalServiceUtil.findByCompanyCrtIdentifierDetails(companySystemId);
    }

    /**
     * Returns the company crt qualifier with the primary key.
     *
     * @param companyCrtQualifierId - primary key
     * @return CompanyCrtQualifier modal object
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public CompanyQualifier getCompanyCrtQualifierByQualifierId(final int companyCrtQualifierId) throws SystemException,PortalException {
     return CompanyQualifierLocalServiceUtil.getCompanyQualifier(companyCrtQualifierId);
    }

    /**
     * Adds the company master to the database. Also notifies the appropriate model listeners.
     *
     * @param companyMaster - Modal Object
     * @return saved CompanyMaster modal Object
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public CompanyMaster saveCompanyMaster(final CompanyMaster companyMaster) throws SystemException, PortalException {
        return CompanyMasterLocalServiceUtil.addCompanyMaster(companyMaster);
    }

    /**
     * Adds the company crt identifier to the database. Also notifies the appropriate model listeners.
     *
     * @param companyCrtIdentifier - Modal Object
     * @return saved companyCrtIdentifier modal object
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public CompanyIdentifier saveCompanyCrtIdentifier(final CompanyIdentifier companyCrtIdentifier) throws SystemException, PortalException {
        return CompanyIdentifierLocalServiceUtil.addCompanyIdentifier(companyCrtIdentifier);
    }

    /**
     * Returns CompanyCrtQualifier according to the companyCrtQualifierName.
     *
     * @param companyCrtQualifierName the company crt qualifier name
     * @return CompanyCrtQualifier modal object
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public CompanyQualifier getCompanyCrtQualifierByQualifierName(final String companyCrtQualifierName) throws SystemException, PortalException {
        return CompanyQualifierLocalServiceUtil.findByCompanyCrtQualifierByName(companyCrtQualifierName);
    }

    /**
     * Deletes the company master from the database. Also notifies the appropriate model listeners.
     *
     * @param companySystemId - primary key
     * @return deleted CompanyMaster modal object
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public CompanyMaster deleteCompanyMasterBySystemId(final int companySystemId) throws SystemException,PortalException {
        return CompanyMasterLocalServiceUtil.deleteCompanyMaster(companySystemId);
    }

    /**
     * Deletes the company crt identifier from the database. Also notifies the appropriate model listeners.
     *
     * @param crtIdentifierSystemId - primary key
     * @return deleted CompanyCrtIdentifier modal object
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public CompanyIdentifier deleteCompanyCrtIdentifierByCrtIdentifierSystemId(final int crtIdentifierSystemId) throws SystemException, PortalException {
        return CompanyIdentifierLocalServiceUtil.deleteCompanyIdentifier(crtIdentifierSystemId);
    }

    /**
     * Deletes the company trade class from database by company System ID.
     *
     * @param companySystemId the company system id
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public void deleteCompanyTradeClassForUpdate(final int companySystemId) throws SystemException, PortalException {
        CompanyMasterLocalServiceUtil.deleteCompanyTradeClassForUpdate(companySystemId);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param query - dynamic query of CompanyCrtQualifier
     * @return list of CompanyCrtQualifier
     * @throws SystemException the system exception
     */
    public List<CompanyQualifier> getCompanyCrtQualifierList(final DynamicQuery query) throws SystemException {
        return CompanyQualifierLocalServiceUtil.dynamicQuery(query);
    }
   
    /**
     * Returns the list of HelperTable by list name.
     *
     * @param listName the list name
     * @return list of helperTable
     * @throws SystemException the system exception
     */
    public List<HelperTable> getHelperTableDetailsByListName(final String listName) throws SystemException {
        return HelperTableLocalServiceUtil.findByHelperTableDetails(listName);
    }
    
    public List<HelperTable> getHelperTableDetailsByListName() throws SystemException {
        return HelperTableLocalServiceUtil.getHelperTables(0, HelperTableLocalServiceUtil.getHelperTablesCount());
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param query - dynamic query of HelperTable
     * @return list of HelperTable
     * @throws SystemException the system exception
     */
    public List<HelperTable> getHelperTableDetailsList(final DynamicQuery query) throws SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Returns the number of company crt qualifiers.
     *
     * @return total count
     * @throws SystemException the system exception
     */
    public int getCompanyCrtQualifierTotalCount() throws SystemException {
       return CompanyQualifierLocalServiceUtil.getCompanyQualifiersCount();
    }

    /**
     * Returns a range of all the company crt qualifiers.
     *
     * @param startindex - start limit
     * @param endIndex - end limit
     * @return list of CompanyCrtQualifier
     * @throws SystemException the system exception
     */
    public List<CompanyQualifier> getCompanyCrtQualifiersByLimit(final int startindex, final int endIndex)throws SystemException{
        return CompanyQualifierLocalServiceUtil.getCompanyQualifiers(startindex, endIndex);
    }

    /**
     * Deletes the company crt qualifier from the database. Also notifies the appropriate model listeners.
     *
     * @param qualifierId the qualifier id
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public void deleteCompanyCrtQualifierByQualifierId(final int qualifierId) throws SystemException, PortalException {
        CompanyQualifierLocalServiceUtil.deleteCompanyQualifier(qualifierId);
    }

    /**
     * Adds the company crt qualifier to the database. Also notifies the appropriate model listeners.
     *
     * @param companyCrtQualifier the company crt qualifier
     * @return saved CompanyCrtQualifier modal object
     * @throws SystemException the system exception
     */
    public CompanyQualifier saveCompanyCrtQualifier(final CompanyQualifier companyCrtQualifier) throws SystemException{
        return CompanyQualifierLocalServiceUtil.addCompanyQualifier(companyCrtQualifier);
    }

    /**
     * Updates the company crt qualifier in the database or adds it if it does not yet exist. 
     * Also notifies the appropriate model listeners.
     *
     * @param companyCrtQualifier the company crt qualifier
     * @return Updated CompanyCrtQualifier modal object
     * @throws SystemException the system exception
     */
    public CompanyQualifier updateCompanyCrtQualifier(final CompanyQualifier companyCrtQualifier) throws SystemException {
        return CompanyQualifierLocalServiceUtil.updateCompanyQualifier(companyCrtQualifier);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param query - dynamic query of CompanyCrtIdentifier
     * @return - list CompanyCrtIdentifier
     * @throws SystemException the system exception
     */
    public List<CompanyIdentifier> getCompanyIdentifierlist(final DynamicQuery query) throws SystemException {
        return CompanyIdentifierLocalServiceUtil.dynamicQuery(query);
    }
    
    /**
     * Updates the company master in the database or adds it if it does not yet exist. 
     * Also notifies the appropriate model listeners.
     *
     * @param companyMaster the company master
     * @return CompanyMaster modal object
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public CompanyMaster updateCompanyMaster(final CompanyMaster companyMaster)throws SystemException,PortalException{
    	return CompanyMasterLocalServiceUtil.updateCompanyMaster(companyMaster);
    }
    
    /**
     * Deletes the company crt identifier from the database. Also notifies the appropriate model listeners.
     *
     * @param companyCrtIdentifier the company crt identifier
     * @return deleted CompanyCrtIdentifier modal object
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public CompanyIdentifier deleteCompanyCrtIdentifier(final CompanyIdentifier companyCrtIdentifier)throws SystemException,PortalException{
    	return CompanyIdentifierLocalServiceUtil.deleteCompanyIdentifier(companyCrtIdentifier);
    }

    /**
     * getCompanyTradeClassList(com.stpl.portal.kernel.dao.orm.DynamicQuery)
     */
    public List<CompanyTradeClass> getCompanyTradeClassList(final DynamicQuery query) throws SystemException {
        return CompanyTradeClassLocalServiceUtil.dynamicQuery(query);
    }
    
       /**
        * @see com.stpl.app.global.dao.CompanySearchLogicDAO#getCompanyParentDetails(com.stpl.portal.kernel.dao.orm.DynamicQuery)
        */
       public List<CompanyParentDetails> getCompanyParentDetails(final DynamicQuery query) throws SystemException {
        return CompanyParentDetailsLocalServiceUtil.dynamicQuery(query);
    }
/**
 * getting count and results for CompanyCrtQualifierName using lazy
     * container
 * @param query
 * @return
 * @throws PortalException
 * @throws SystemException 
 */
    public List getCompanyCrtQualifierNameList(final DynamicQuery query) throws PortalException, SystemException {
         return CompanyQualifierLocalServiceUtil.dynamicQuery(query);
    }
      
    /**
     * 
     * @param companyCrtIdentifier
     * @return
     * @throws SystemException
     * @throws PortalException 
     */
    public CompanyIdentifier updateCompanyCrtIdentifier(final CompanyIdentifier companyCrtIdentifier) throws SystemException, PortalException {
        return CompanyIdentifierLocalServiceUtil.updateCompanyIdentifier(companyCrtIdentifier);
    }
    
    public Map<Integer, Udcs> getUDCS(String type) throws SystemException{
        Map<Integer,Udcs> udcMap = new HashMap<Integer,Udcs>();
        final DynamicQuery udcsDynamicQuery = DynamicQueryFactoryUtil.forClass(Udcs.class);
        udcsDynamicQuery.add(RestrictionsFactoryUtil.eq("udcType", type));
        List<Udcs> udcsList = UdcsLocalServiceUtil.dynamicQuery(udcsDynamicQuery);
        for(Udcs udcs: udcsList){
            udcMap.put(udcs.getUdcsSid(), udcs);
        }
        return udcMap;
    }    
    
    public Udcs getUDCS(int udcsSid) throws SystemException, PortalException{
        return UdcsLocalServiceUtil.getUdcs(udcsSid);
    }
    
    public Udcs saveUdcs(Udcs udcs) throws SystemException{
        return UdcsLocalServiceUtil.addUdcs(udcs);        
    }
    
    public Udcs updateUdcs(Udcs udcs) throws SystemException{
        return UdcsLocalServiceUtil.updateUdcs(udcs);        
    }    

   /**
    * To get the count of companyIdentifier associated with given qualifierID
    * 
    * @param query
    * @return List
    * @throws PortalException
    * @throws SystemException 
    */
    public List getCompanyIdentifierList(final DynamicQuery query) throws PortalException, SystemException {
         return CompanyIdentifierLocalServiceUtil.dynamicQuery(query);
    }
    
    public List findCompanyMasterV1(final String companyId, final String companyNo, final String companyName, final String companyStatus,final String companyType,final String companyCategory,final String companyGroup, final String tradeClass, final int qualifierId, final String itemIdentifier, String orderByColumn, Boolean sortOrder, Object index, Object next,Map<String, Object> parameters) throws PortalException,SystemException {
      return CompanyMasterLocalServiceUtil.findCompanyMasterV1(
                    companyId, companyNo, companyName, companyStatus,
                    companyType,companyCategory,companyGroup, tradeClass, qualifierId, itemIdentifier,  orderByColumn,  sortOrder, index, next,parameters);
    } 
    }
