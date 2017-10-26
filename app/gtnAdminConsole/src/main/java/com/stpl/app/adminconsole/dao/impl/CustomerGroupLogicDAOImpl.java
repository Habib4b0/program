package com.stpl.app.adminconsole.dao.impl;

import java.util.List;

import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.dao.CustomerGroupLogicDAO;
import com.stpl.app.model.CompanyGroup;
import com.stpl.app.model.CompanyGroupDetails;
import com.stpl.app.service.CompanyGroupDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyGroupLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HistCompanyGroupDetailsLocalServiceUtil;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * DAO operation for CustomerGroupLogic.
 *
 * @author sriram
 */
public class CustomerGroupLogicDAOImpl implements CustomerGroupLogicDAO {

  
    private static final Logger LOGGER = Logger.getLogger(CustomerGroupLogicDAOImpl.class);

    /**
     * To get a List of CompanyGroup based on the query.
     *
     * @param query -dynamic query
     * @return List<CompanyGroup>
     * @throws SystemException
     * @throws Exception the exception
     * @table CompanyGroup
     */
    public List getCompanyGroupList(final DynamicQuery query) throws SystemException {
        LOGGER.debug("In query-getCompanyGroupList started with P1:DynamicQuery query");
        return CompanyGroupLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * To get a List of CompanyGroup based on the query count.
     *
     * @param query -dynamic query
     * @return List<CompanyGroup>
     * @throws SystemException
     * @throws Exception the exception
     * @table CompanyGroup
     */
    public int getCompanyGroupListCount(final DynamicQuery query) throws SystemException {
        LOGGER.debug("In query-getCompanyGroupList started with P1:DynamicQuery query");
        return (int) CompanyGroupLocalServiceUtil.dynamicQueryCount(query);
    }

    /**
     * To get the CompanyGroup for a particular systemId from CompanyGroup
     * Table.
     *
     * @param customerGroupSystemId the customer group system id
     * @return CompanyGroup
     * @throws SystemException
     * @throws PortalException
     * @throws Exception the exception
     */
    public CompanyGroup getCompanyGroup(final int customerGroupSystemId) throws PortalException, SystemException {
        LOGGER.debug("In query-getCompanyGroup started with P1:int customerGroupSystemId=" + customerGroupSystemId);
        return CompanyGroupLocalServiceUtil.getCompanyGroup(customerGroupSystemId);
    }

    /**
     * Updates the CompanyGroup Table.
     *
     * @param customerGroup the customer group
     * @return CompanyGroup
     * @throws SystemException
     * @throws Exception the exception
     */
    public CompanyGroup updateCompanyGroup(final CompanyGroup customerGroup) throws SystemException {
        LOGGER.debug("In query-updateCompanyGroup started with P1:CompanyGroup customerGroup");
        return CompanyGroupLocalServiceUtil.updateCompanyGroup(customerGroup);
    }

    /**
     * Adds the new company group from the values in customerGroup in
     * CompanyGroup table.
     *
     * @param customerGroup the customer group
     * @return CompanyGroup
     * @throws SystemException
     * @throws Exception the exception
     */
    public CompanyGroup addCompanyGroup(final CompanyGroup customerGroup) throws SystemException {
        LOGGER.debug("In query-addCompanyGroup started with P1:CompanyGroup customerGroup");
        return CompanyGroupLocalServiceUtil.addCompanyGroup(customerGroup);
    }

    /**
     * To delete a company group having the systemId in CompanyGroup Table.
     *
     * @param companyGroupSystemId the company group system id
     * @return CompanyGroup
     * @throws SystemException
     * @throws PortalException
     * @throws Exception the exception
     */
    public CompanyGroup deleteCompanyGroup(final int companyGroupSystemId) throws PortalException, SystemException {
        LOGGER.debug("In query-deleteCompanyGroup started with P1:int companyGroupSystemId=" + companyGroupSystemId);
        return CompanyGroupLocalServiceUtil.deleteCompanyGroup(companyGroupSystemId);
    }

    /**
     * To get a list of companies from the companyMaster Table.
     *
     * @param query the query
     * @return List<CompanyMaster>
     * @throws SystemException
     * @throws Exception the exception
     * @table CompanyMaster
     */
    public List getCompanyMasterList(final DynamicQuery query) throws SystemException {
        LOGGER.debug("In query-getCompanyMasterList started with P1:DynamicQuery query");
        return CompanyMasterLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * To get a List of CompanyGroupDetails based on the query.
     *
     * @param query - dynamic query
     * @return List<CompanyGroupDetails>
     * @throws SystemException
     * @throws Exception the exception
     * @table CompanyGroupDetails
     */
    public List getCompanyGroupDetailsList(final DynamicQuery query) throws SystemException {
        LOGGER.debug("In query-getCompanyGroupDetailsList started with P1:DynamicQuery query");
        return CompanyGroupDetailsLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * To update data in CompanyGroupDetails table.
     *
     * @param companyToUpdate the company to update
     * @return CompanyGroupDetails
     * @throws SystemException
     * @throws Exception the exception
     * @table CompanyGroupDetails
     */
    public CompanyGroupDetails updateCompanyGroupDetails(final CompanyGroupDetails companyToUpdate) throws SystemException {
        LOGGER.debug("In query-updateCompanyGroupDetails started with P1:CompanyGroupDetails companyToUpdate");
        return CompanyGroupDetailsLocalServiceUtil.updateCompanyGroupDetails(companyToUpdate);
    }

    /**
     * To add data to the CompanyGroupDetails table.
     *
     * @param companyToAdd the company to add
     * @return CompanyGroupDetails
     * @throws SystemException
     * @throws Exception the exception
     * @table CompanyGroupDetails
     */
    public CompanyGroupDetails addCompanyGroupDetails(final CompanyGroupDetails companyToAdd) throws SystemException {
        LOGGER.debug("In query-addCompanyGroupDetails started with P1:CompanyGroupDetails companyToAdd");
        return CompanyGroupDetailsLocalServiceUtil.addCompanyGroupDetails(companyToAdd);
    }

    /**
     * To delete data from the CompanyGroupDetails table.
     *
     * @param companyToDelete the company to delete
     * @throws SystemException
     * @throws Exception the exception
     * @table CompanyGroupDetails
     */
    public void deleteCompanyGroupDetails(final CompanyGroupDetails companyToDelete) throws SystemException {
        LOGGER.debug("In query-deleteCompanyGroupDetails started with P1:CompanyGroupDetails companyToDelete");
        CompanyGroupDetailsLocalServiceUtil.deleteCompanyGroupDetails(companyToDelete);
    }

    /**
     * To get a List of CompanyGroupDetailsHistory based on the query.
     *
     * @param query - dynamic query
     * @return List<CompanyGroupDetailsHistory>
     * @throws SystemException
     * @throws Exception the exception
     * @table CompanyGroupDetailsHistory
     */
    public List getCompanyGroupDetailsHistoryList(final DynamicQuery query) throws SystemException {
        LOGGER.debug("In query-getCompanyGroupDetailsList started with P1:DynamicQuery query");
        return HistCompanyGroupDetailsLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * count method for history table
     *
     * @param query
     * @return
     * @throws SystemException
     */
    public int getCgmHistoryCount(DynamicQuery query) throws SystemException {
        return (int) HistCompanyGroupDetailsLocalServiceUtil.dynamicQueryCount(query);
    }
     public int getProjectionCount(DynamicQuery query) throws SystemException {
        return (int) ProjectionMasterLocalServiceUtil.dynamicQueryCount(query);
    }
}
