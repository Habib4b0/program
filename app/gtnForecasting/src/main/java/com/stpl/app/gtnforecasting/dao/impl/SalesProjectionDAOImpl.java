/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.SalesProjectionDAO;
import com.stpl.app.gtnforecasting.service.finderImpl.NmSalesProjectionImpl;
import com.stpl.app.gtnforecasting.service.finderImpl.StChSalesProjectionImpl;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;



/**
 * The Class SalesProjectionDAOImpl.
 *
 * @author lokeshwari
 */
public class SalesProjectionDAOImpl implements SalesProjectionDAO {

    /**
     * Gets the pmpy contract holder list.
     *
     * @param tpName the tp name
     * @return the pmpy contract holder list
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @Override
    public List<String> getPMPYTradingPartnerList(String tpName) throws PortalException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /* (non-Javadoc)
     * @see com.stpl.app.gtnforecasting.dao.SalesProjectionDAO#getPMPYContractHolder()
     */
    @Override
    public List<String> getPMPYContractHolder(DynamicQuery dynamicQuery) throws PortalException {
        return CompanyMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Gets the sales projection.
     *
     * @param query the query
     * @return the sales projection
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @Override
    public List getSalesProjection(Object[] inputs) throws PortalException {
        return new NmSalesProjectionImpl().getSalesResult(inputs);

    }

    /**
     * Gets the sales projection result.
     *
     * @param query the query
     * @return the sales projection result
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @Override
    public List getSalesProjectionResult(DynamicQuery query) throws PortalException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Gets the sales contract holder summary.
     *
     * @param projectionId the projection id
     * @param indicator the indicator
     * @param comparisonProjections the comparison projections
     * @param frequency the frequency
     * @param level the level
     * @param discountLevel the discount level
     * @return the contract holder summary
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @Override
    public List getContractHolderSummary(int projectionId, String indicator, List<Integer> comparisonProjections, String frequency,
            String level, String discountLevel) throws PortalException {

        return Collections.emptyList();
    }

    /**
     * Gets the group list.
     *
     * @param query the query
     * @return the group list
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public List<String> getGroupList(DynamicQuery query) throws SystemException {

        return Collections.emptyList();
    }

    /**
     * Gets the sales projection result.
     *
     * @param query the query
     * @return the sales projection result
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @Override
    public List getSalesProjectionResults(Object[] inputs) throws PortalException {
        return new NmSalesProjectionImpl().getSalesProjectionResults(inputs);
    }

    @Override
    public List getSalesProjectionResultLevels(Object[] inputs) throws PortalException {
        return new NmSalesProjectionImpl().getSalesProjectionResultLevels(inputs);
    }

    /**
     * Gets the sales projection.
     *
     * @param query the query
     * @return the sales projection
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @Override
    public List executeQuery(Map<String, Object> parameters) throws PortalException {
        return new StChSalesProjectionImpl().executeQuery(parameters);
    }

    @Override
    public Object executeSelectQuery(String query) throws PortalException {
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    
    @Override
    public List<HelperTable> getHelperTableList(final DynamicQuery query) throws  SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public List executeUpdateQuery(String queryList) throws PortalException {
        List list = new ArrayList();
        list.add(HelperTableLocalServiceUtil.executeUpdateQueryCount(queryList));
        return list;
    }
   
    @Override
    public void executeUpdateQuery(List<StringBuilder> queryList) throws PortalException {
        new NACommonResultsDAOImpl().executeUpdateQuery(queryList);
    }

}
