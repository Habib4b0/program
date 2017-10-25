/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.dao;

import com.stpl.app.gcm.promotetptocontract.dto.CurrentContractDTO;
import com.stpl.app.model.HelperTable;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alok.v
 */
public interface PromoteTpDAO {

    /**
     * Search Company Count
     *
     * @param parameters
     * @return
     * @throws SystemException
     */
    public List companyCount(final Map<String, Object> parameters) throws SystemException;

    public List searchTPCompanies(final Map<String, Object> parameters) throws SystemException;

    public List loadCompanyTypeDDLB(Map<String, Object> parameters);

    public int getCompanyTypeCount(DynamicQuery dynamicQuery);

    public List<Object[]> getCompanyTypes(DynamicQuery dynamicQuery);

    public List searchCurrentContracts(final Map<String, Object> parameters) throws SystemException;

    public List getComponentInfo(String component, String rebateId);

    public void updateSubmitFlag(String contractType, String userId, String sessionId, boolean flag,CurrentContractDTO dto);

    public Object executeSelectQuery(String query) throws SystemException, PortalException;

    List<HelperTable> getHelperTableList(DynamicQuery dynamicQuery) throws SystemException;

    public void updateCFP(List<Object> input);

    public void updateIFP(List<Object> input);

    public void updatePS(List<Object> input);

    public void updateRS(List<Object> input);

    public List getItems(String query) throws SystemException;

    public List getComponentInformation(String component, String[] id);

    public List startDateAndEndDateValidation(String userId, String sessionId, String screenName);

    public List isAnyRecordSelected(String userId, String sessionId, String screenName);

    public List searchCompanies(final Map<String, Object> parameters) throws SystemException;
}
