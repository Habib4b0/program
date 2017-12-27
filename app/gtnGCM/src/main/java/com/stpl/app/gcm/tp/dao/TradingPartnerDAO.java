/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.dao;

import com.liferay.portal.kernel.exception.SystemException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lokeshwari
 */
public interface TradingPartnerDAO {

    /**
     * Search for projections.
     *
     * @param parameters the parameters
     * @return the projection master result list
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public List searchCompanies(final Map<String, Object> parameters) throws SystemException;

    Object executeSelectQuery(String query);

    /**
     * To get the component information
     *
     * @param component
     * @param id
     * @return
     */
   
    /**
     * To get the component information
     * @param moduleName
     * @param component
     * @param id
     * @return
     */
    public void updateSubmitFlag(String moduleName, String screenName, String userId, String sessionId, boolean checkCondition, boolean flag);

    public List getSubmitValidationData(String userId, String sessionId, String screenName, String validationType);

    public List isAnyRecordSelected(String userId, String sessionId, String screenName);

    public List searchList(String query);

    public List searchLinkedCompanies(Map<String, Object> parameters);

    public int updateCustomer(String companyMasterSid, String linkId, String sessionId);

    public int insertCustomer(String companyMasterSid, String companyId, String companyNumber, String companyName, String linkId, String sessionId);

    public int updateCheckRecord(String fromCompanyMasterSid, String toCompanyMasterSid, boolean checkvalue, String sessionId);

    public int removeCustomerLink(String linkedCustomersSessionId);

    public List<String> getLinkedCompaniesList(String linkedCustomersSessionId, boolean fromCompany);

    public List getLinkedCustomersCheckedRecordCount(String linkedCustomersSessionId);
    
    public List<String> getCustomersHavingCommonItems(int sourceProjectionId, int destProjectionId, int sourceContractId, int destContractId, String customerMappings);

    public void insertIntoCcpMap(Map<String, Object> parameters);
}
