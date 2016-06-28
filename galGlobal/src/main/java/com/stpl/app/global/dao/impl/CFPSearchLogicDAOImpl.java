/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.dao.impl;


import com.stpl.app.model.CfpContract;
import com.stpl.app.model.CfpDetails;
import com.stpl.app.model.CfpModel;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ImtdCfpDetails;
import com.stpl.app.service.CfpContractLocalServiceUtil;
import com.stpl.app.service.CfpDetailsLocalServiceUtil;
import com.stpl.app.service.CfpModelLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ImtdCfpDetailsLocalServiceUtil;
import com.stpl.domain.global.companyfamilyplan.CompanyFamilyplanDAO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * DAO operations for CompanyFamilyPlan Logic
 * @author shrihariharan
 */
public class CFPSearchLogicDAOImpl implements CompanyFamilyplanDAO {

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - Dynamic query of Company Familyplan Master 
     * @return list of CompanyFamilyplanMaster
     * @throws SystemException 
     */
    public List<CfpModel> getCompanyFamilyplanMasterList(final DynamicQuery query) throws SystemException{

            return CfpModelLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Returns a range of all the company familyplan masters
     * @param startIndex - start limit
     * @param endIndex - end limit
     * @return list of CompanyFamilyplanMaster
     * @throws SystemException 
     */
    public List<CfpModel> getCompanyFamilyplanMasterByLimit(final int startIndex, final int endIndex)throws SystemException{
        return CfpModelLocalServiceUtil.getCfpModels(startIndex, endIndex);
    }
    /**
     * Returns the number of company familyplan masters.
     * @return total count
     * @throws SystemException 
     */
    public int getCompanyFamilyplanMasterTotalCount() throws PortalException, SystemException{
        return (int) CfpModelLocalServiceUtil.getCfpModelsCount(); 
    }
    /**
     * Returns the number of rows that match the dynamic query.
     * @param query - dynamic query of CompanyFamilyplanMaster
     * @return count
     * @throws SystemException 
     */
    public int getCompanyFamilyplanMasterQueryCount(final DynamicQuery query)throws PortalException, SystemException {
      return (int) CfpModelLocalServiceUtil.dynamicQueryCount(query);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of CompanyMaster
     * @return list of CompanyMaster
     * @throws SystemException 
     */
    public List<CompanyMaster> getCompanyMasterList(final DynamicQuery query) throws SystemException {
       return CompanyMasterLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Returns the company familyplan master with the primary key.
     * @param companyFamilyplanSystemId - primary key
     * @return CompanyFamilyplanMaster
     * @throws SystemException
     * @throws PortalException 
     */
    public CfpModel getCompanyFamilyplanMasterBySystemId(final int companyFamilyplanSystemId) throws SystemException,PortalException{
       return CfpModelLocalServiceUtil.getCfpModel(companyFamilyplanSystemId);
    }

    /**
     * Adds the company familyplan master to the database. Also notifies the appropriate model listeners.
     * @param companyFamilyplanMaster - Modal Object
     * @return CompanyFamilyplanMaster
     * @throws SystemException 
     */
    public CfpModel saveCompanyFamilyplanMaster(final CfpModel companyFamilyplanMaster) throws SystemException {
       return  CfpModelLocalServiceUtil.addCfpModel(companyFamilyplanMaster);
    }
    
    /**
     * Adds the company familyplan details to the database. Also notifies the appropriate model listeners.
     * @param details - Modal Object
     * @return CompanyFamilyplanDetails
     * @throws SystemException 
     */
    public CfpDetails saveCompanyFamilyplanDetails(final CfpDetails details)throws PortalException, SystemException{
        return CfpDetailsLocalServiceUtil.addCfpDetails(details);
    }

    /**
     * Returns the list of CompanyFamilyplanDetails
     * @param companyFamilyplanSystemId
     * @return list of CompanyFamilyplanDetails
     * @throws SystemException 
     */
    public List<CfpDetails> getCompanyFamilyplanDetailsListByCFPSystemId(final int companyFamilyplanSystemId) throws PortalException, SystemException {
        return CfpDetailsLocalServiceUtil.findByCfpModelSid(companyFamilyplanSystemId);
    }

    /**
     * Deletes the company familyplan details from the database. Also notifies the appropriate model listeners.
     * @param details - Modal Object
     * @return deleted CompanyFamilyplanDetails Modal object
     * @throws SystemException 
     */
    public CfpDetails deleteCompanyFamilyplanDetails(final CfpDetails details) throws PortalException, SystemException {
        return CfpDetailsLocalServiceUtil.deleteCfpDetails(details);
    }
    
    /**
     * Updates the company familyplan master in the database or adds it if it does not yet exist.
     * Also notifies the appropriate model listeners.
     * @param companyFamilyplanMaster - Modal Object
     * @return updated CompanyFamilyplanMaster Modal Object
     * @throws SystemException 
     */
    public CfpModel updateCompanyFamilyplanMaster(final CfpModel companyFamilyplanMaster)throws PortalException, SystemException
    {
        return CfpModelLocalServiceUtil.updateCfpModel(companyFamilyplanMaster);
    }
    
    /**
     * Deletes the company familyplan master from the database. Also notifies the appropriate model listeners.
     * @param CompanyFamilyplanMasterSystemId
     * @return deleted CompanyFamilyplanMaster Modal Object
     * @throws SystemException
     * @throws PortalException 
     */
    public CfpModel deleteCompanyFamilyplanMasterBySystemId(final int companyFamilyplanMasterSystemId)throws SystemException,PortalException
    {
        return CfpModelLocalServiceUtil.deleteCfpModel(companyFamilyplanMasterSystemId);
    }

    /**
     * Returns the list to helperTable Object according to the listName parameter
     * @param listName - DropDown Names
     * @return list of HelperTable Object
     * @throws SystemException 
     */
    public List<HelperTable> getHelperTableDetailsByListName(final String listName) throws SystemException {
        return HelperTableLocalServiceUtil.findByHelperTableDetails(listName);
    }

    /**
     * Returns the company master with the primary key.
     * @param companyMasterSystemId - primary key
     * @return CompanyMaster - Modal Object
     * @throws SystemException
     * @throws PortalException 
     */
    public CompanyMaster getCompanyMasterBySystemId(final int companyMasterSystemId) throws SystemException,PortalException {
        return CompanyMasterLocalServiceUtil.getCompanyMaster(companyMasterSystemId);
    }

    /**
     * Returns the number of rows that match the dynamic query.
     * @param query - dynamic query of CompanyFamilyplanMaster
     * @return count
     * @throws SystemException 
     */
    public int getCompanyMasterQueryCount(final DynamicQuery query)throws PortalException, SystemException {
      return (int) CompanyMasterLocalServiceUtil.dynamicQueryCount(query);
    }

    /**
     * 
     * @param details
     * @return
     * @throws PortalException
     * @throws SystemException 
     */
    public CfpDetails updateCompanyFamilyplanDetails(final CfpDetails details)throws PortalException, SystemException{
        return CfpDetailsLocalServiceUtil.updateCfpDetails(details);
    }
      /**
     * Returns the company master with the primary key.
     * @param companyMasterSystemId - primary key
     * @return CompanyMaster - Modal Object
     * @throws SystemException
     * @throws PortalException 
     */
    public List getCompanyMasterQueryList(final DynamicQuery query) throws SystemException,PortalException {
        return CompanyMasterLocalServiceUtil.dynamicQuery(query);
    }
    /**
     * Returns the number of rows that match the dynamic query.
     * @param query - dynamic query of CompanyFamilyplanMaster
     * @return count
     * @throws SystemException 
     */
    public int getTempCfpDetailsCount(final DynamicQuery query)throws PortalException, SystemException {
      return (int) ImtdCfpDetailsLocalServiceUtil.dynamicQueryCount(query);
    }
    /**
     * Returns the number of rows that match the dynamic query.
     * @param query - dynamic query of CompanyFamilyplanMaster
     * @return count
     * @throws SystemException 
     */
    public List getTempCfpDetails(final DynamicQuery query)throws PortalException, SystemException {
      return  ImtdCfpDetailsLocalServiceUtil.dynamicQuery(query);
    }
    
   
    /**
     * inserts new row in TempCfpDetails
     * @param companyFamilyplanMaster
     * @return
     * @throws SystemException 
     */
    public ImtdCfpDetails saveTempCfpDetails(final ImtdCfpDetails tempCfpDetails) throws SystemException {
       return  ImtdCfpDetailsLocalServiceUtil.addImtdCfpDetails(tempCfpDetails);
    }
    /**
     * inserts new row in TempCfpDetails
     * @param companyFamilyplanMaster
     * @return
     * @throws SystemException 
     */
    public ImtdCfpDetails deleteTempCfpDetails(final int tempCfpDetails) throws SystemException, PortalException{
       return  ImtdCfpDetailsLocalServiceUtil.deleteImtdCfpDetails(tempCfpDetails);
    }
   public Object deleteAllTempCfpDetails(String userId,String sessionId,Date date,Boolean deleteYesterdayRecordFlag,String dateStr,Object tempCfpSystemId) throws PortalException, SystemException{
       return ImtdCfpDetailsLocalServiceUtil.deleteAll(userId, sessionId, dateStr, null, tempCfpSystemId, deleteYesterdayRecordFlag, null, null);
   }
   public List getCompanyLazyList(int start, int offset,Object companyIdList,Object operation,Object future2,String column,String orderBy,Map<Object,Object> filterMap) throws PortalException, SystemException{
       return ImtdCfpDetailsLocalServiceUtil.getCompanyLazyList(start, offset, companyIdList, operation, future2,column,orderBy,filterMap);
   }
    public Object insertTempCfpDetailsInADD(String userId,String sessionId,String createdDate,String operation,Object future1,Object future2,Object future3,Object future4) throws PortalException, SystemException{
         return ImtdCfpDetailsLocalServiceUtil.insertTempCfpDetailsInADD(userId, sessionId, createdDate, operation, future1, future2, future3, future4);
    }
    
    public Object insertTempCfpDetailsInEdit(String userId,String sessionId,String createdDate,String operation,Object future1,Object future2,Object future3,Object future4) throws PortalException, SystemException{
         return ImtdCfpDetailsLocalServiceUtil.insertTempCfpDetailsInEdit(userId, sessionId, createdDate, operation, future1, future2, future3, future4);
    }
   /**
     * inserts new row in TempCfpDetails
     * @param companyFamilyplanMaster
     * @return
     * @throws SystemException 
     */
    public ImtdCfpDetails updateTempCfpDetails(final ImtdCfpDetails tempCfpDetails) throws SystemException {
       return  ImtdCfpDetailsLocalServiceUtil.updateImtdCfpDetails(tempCfpDetails);
    }
public Object updateForPopulate(String userId,String sessionId,String createdDate,String operation,Object future1,Object future2,Object future3,Object future4) throws PortalException, SystemException{
         return ImtdCfpDetailsLocalServiceUtil.updateForPopulate(userId, sessionId, createdDate, operation, future1, future2, future3, future4);
    }
public Object updateForPopulateAll(String userId,String sessionId,String createdDate,String operation,Object future1,Object future2,Object future3,Object future4) throws PortalException, SystemException{
         return ImtdCfpDetailsLocalServiceUtil.updateForPopulateAll(userId, sessionId, createdDate, operation, future1, future2, future3, future4);
    }
public Object updateToCFPDetails(int cfpMasterSystemId,String userId,String sessionId,String createdDate,String operation,Object future1,Object future2,Object future3,Object future4){
    return ImtdCfpDetailsLocalServiceUtil.updateToCFPDetails(cfpMasterSystemId, userId, sessionId, createdDate, operation, future1, future2, future3, future4);
}
/**
 * used to delete the cfp details table
 * @param userId
 * @param sessionId
 * @param createdDate
 * @param operation
 * @param future1
 * @param future2
 * @param future3
 * @param future4
 * @return
 * @throws PortalException
 * @throws SystemException 
 */
public Object updateOperationCFPDeatils(String cfpMasterSystemId) throws PortalException, SystemException{
    return ImtdCfpDetailsLocalServiceUtil.updateOperation(cfpMasterSystemId, null, null, null, null, null, null, null);
}
public Object validateTempCFPDeatils(String userId,String sessionId,String createdDate,String process){
    return ImtdCfpDetailsLocalServiceUtil.validateTempCFPDeatils(userId, sessionId, createdDate, process, null, null, null, null);
}

    public List getTempCfpDetailsCount(String cfpMasterSystemId, String operation,Map<Object,Object> filterMap) throws PortalException, SystemException {
       return (List)ImtdCfpDetailsLocalServiceUtil.getTempCFPLazyList(cfpMasterSystemId, null, null, operation, null, null, null, null,filterMap);
    }

    public List getTempCfpDetails(String start, String end, String cfpMasterSystemId, String operation,String column,String orderBy,Map<Object,Object> filterMap) throws PortalException, SystemException {
      return (List)ImtdCfpDetailsLocalServiceUtil.getTempCFPLazyList(cfpMasterSystemId,start , end, operation, column,orderBy, null, null,filterMap);
    }
    
    public Object updateCFPDetails(int cfpMasterSystemId,String userId,String sessionId,String createdDate,String operation,Object future1,Object future2,Object future3,Object future4){
    return ImtdCfpDetailsLocalServiceUtil.updateCFPDetails(cfpMasterSystemId, userId, sessionId, createdDate, operation, future1, future2, future3, future4);
}
    
    public List getSelectedCompanies(int start, int offset,String userId, String sessionId,String columnName,String orderBy,Map<Object,Object> filterMap,String operation){
        return ImtdCfpDetailsLocalServiceUtil.getSelectedCompanies(start, offset, userId, sessionId, columnName, orderBy, filterMap, operation);
    }

    @Override
    public List findCfpModelV1(Map<Object, Object> cfp, String orderByColumn, Boolean sortOrder, Object index, Object next, Map<Object, Object> parameters, String operation, Object future, Object future1) {
        return CfpModelLocalServiceUtil.findCfpModelV1(cfp, orderByColumn, sortOrder, index, next, parameters, operation, future, future1);
    }

    @Override
    public List<CfpContract> getCfpContractList(DynamicQuery query) throws SystemException {
            return CfpContractLocalServiceUtil.dynamicQuery(query);
    }
    
}
