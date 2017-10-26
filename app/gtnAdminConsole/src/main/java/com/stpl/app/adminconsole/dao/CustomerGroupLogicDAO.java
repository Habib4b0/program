package com.stpl.app.adminconsole.dao;

import java.util.List;

import com.stpl.app.model.CompanyGroup;
import com.stpl.app.model.CompanyGroupDetails;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * Defines DAO operation for CustomerGroupLogic
 *
 * @author sriram
 */
public interface CustomerGroupLogicDAO {

    /**
     * To get a List of CompanyGroup based on the query
     *
     * @throws Exception
     * @table CompanyGroup
     * @param query - dynamic query
     * @return List<CompanyGroup>
     */
    List getCompanyGroupList(DynamicQuery query) throws SystemException;

    /**
     * To get a List of CompanyGroup based on the query count
     *
     * @throws Exception
     * @table CompanyGroup
     * @param query - dynamic query
     * @return List<CompanyGroup>
     */
    int getCompanyGroupListCount(DynamicQuery query) throws SystemException;

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

    /**
     * To get a List of CompanyGroupDetails based on the query
     *
     * @table CompanyGroupDetails
     * @param query - dynamic query
     * @return List<CompanyGroupDetails>
     * @throws Exception
     */
    List getCompanyGroupDetailsList(DynamicQuery query) throws SystemException;

    /**
     * To get the CompanyGroup for a particular systemId from CompanyGroup Table
     *
     * @param customerGroupSystemId
     * @return CompanyGroup
     * @throws Exception
     */
    CompanyGroup getCompanyGroup(int customerGroupSystemId) throws PortalException, SystemException;

    /**
     * Updates the CompanyGroup Table
     *
     * @param customerGroup
     * @return CompanyGroup
     * @throws Exception
     */
    CompanyGroup updateCompanyGroup(CompanyGroup customerGroup) throws SystemException;

    /**
     * Adds the new company group from the values in customerGroup in
     * CompanyGroup table
     *
     * @param customerGroup
     * @return CompanyGroup
     * @throws Exception
     */
    CompanyGroup addCompanyGroup(CompanyGroup customerGroup) throws SystemException;

    /**
     * To delete a company group having the systemId in CompanyGroup Table
     *
     * @param companyGroupSystemId
     * @return CompanyGroup
     * @throws Exception
     */
    CompanyGroup deleteCompanyGroup(int companyGroupSystemId) throws PortalException, SystemException;

    /**
     * To update data in CompanyGroupDetails table
     *
     * @table CompanyGroupDetails
     * @param companyToUpdate
     * @return CompanyGroupDetails
     * @throws Exception
     */
    CompanyGroupDetails updateCompanyGroupDetails(CompanyGroupDetails companyGroupDetails) throws SystemException;

    /**
     * To add data to the CompanyGroupDetails table
     *
     * @table CompanyGroupDetails
     * @param companyToAdd
     * @return CompanyGroupDetails
     * @throws Exception
     */
    CompanyGroupDetails addCompanyGroupDetails(CompanyGroupDetails companyGroupDetails) throws SystemException;

    /**
     * To delete data from the CompanyGroupDetails table
     *
     * @table CompanyGroupDetails
     * @param companyToDelete
     * @throws Exception
     */
    void deleteCompanyGroupDetails(CompanyGroupDetails companyToDelete) throws SystemException;

    /**
     * To get a List of HistoryCompanyGroup based on the query
     *
     * @table CompanyGroupDetails
     * @param query - dynamic query
     * @return List<CompanyGroupDetails>
     * @throws Exception
     */
    List getCompanyGroupDetailsHistoryList(DynamicQuery query) throws SystemException;
    
     /**
     * To get a List of HistoryCompanyGroup based on the query
     *
     * @table CompanyGroupDetails
     * @param query - dynamic query
     * @return List<CompanyGroupDetails>
     * @throws Exception
     */
    int getCgmHistoryCount(DynamicQuery query) throws SystemException;
    /**
     * get the projection count which is associated with item group
     *
     * @param query
     * @return
     * @throws SystemException
     */
    int getProjectionCount(final DynamicQuery query) throws SystemException;
}
