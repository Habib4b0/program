/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao.impl;

import com.stpl.app.gtnforecasting.dao.SalesProjectionDAO;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;

import com.stpl.app.service.HelperTableLocalServiceUtil;

import com.stpl.app.service.MSalesProjectionMasterLocalServiceUtil;
import com.stpl.app.service.NmSalesProjectionLocalServiceUtil;
import com.stpl.app.service.StChSalesProjectionMasterLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
// TODO: Auto-generated Javadoc
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
    public List<String> getPMPYTradingPartnerList(String tpName) throws SystemException, PortalException{
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    /* (non-Javadoc)
     * @see com.stpl.app.gtnforecasting.dao.SalesProjectionDAO#getPMPYContractHolder()
     */
    @Override
    public List<String> getPMPYContractHolder(DynamicQuery dynamicQuery) throws SystemException, PortalException{
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
    public List getSalesProjection(Object[] inputs) throws SystemException, PortalException{
         return  NmSalesProjectionLocalServiceUtil.getSalesResult(inputs);
      
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
    public List getSalesProjectionResult(DynamicQuery query) throws SystemException, PortalException{
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
			String level, String discountLevel) throws SystemException, PortalException{
		
		
		return null;
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
		
		
		return null;
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
    public List getSalesProjectionResults(Object[] inputs) throws SystemException, PortalException {
        return NmSalesProjectionLocalServiceUtil.getSalesProjectionResults(inputs);
    }

    @Override
    public List getSalesProjectionResultLevels(Object[] inputs) throws SystemException, PortalException {
        return NmSalesProjectionLocalServiceUtil.getSalesProjectionResultLevels(inputs);
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
    public List executeQuery(Map<String, Object> parameters) throws SystemException, PortalException{
        return StChSalesProjectionMasterLocalServiceUtil.executeQuery(parameters);
    }

    

    public Object executeSelectQuery(String query) throws SystemException, PortalException {
        return MSalesProjectionMasterLocalServiceUtil.executeSelectQuery(query, null, null);
    }
    
    public List<HelperTable> getHelperTableList(final DynamicQuery query) throws PortalException, SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public List executeUpdateQuery(String queryList) throws SystemException, PortalException{
        return MSalesProjectionMasterLocalServiceUtil.executeUpdateQuery(queryList, null, null);
    }
    
    public void executeUpdateQuery(List<StringBuilder> queryList) throws SystemException, PortalException {
        MSalesProjectionMasterLocalServiceUtil.executeUpdateQuery(queryList,null, null);
    }

    

}
