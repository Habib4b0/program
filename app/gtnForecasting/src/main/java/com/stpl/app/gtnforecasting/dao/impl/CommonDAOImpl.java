/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.gtnforecasting.dao.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.CommonDAO;
import com.stpl.app.gtnforecasting.service.finderImpl.NMSalesProjectionMasterImpl;
import com.stpl.app.gtnforecasting.service.finderImpl.NmDiscountImpl;
import com.stpl.app.model.CustomViewDetails;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.model.RelationshipLevelDefinition;
import com.stpl.app.service.CustomViewDetailsLocalServiceUtil;
import com.stpl.app.service.CustomViewMasterLocalServiceUtil;
import com.stpl.app.service.RelationshipLevelDefinitionLocalServiceUtil;
import java.util.List;

/**
 *
 * @author Abhiram
 */
public class CommonDAOImpl implements CommonDAO{
       
    @Override
    public List getRelationshipLevels(DynamicQuery query) throws SystemException {        
        return RelationshipLevelDefinitionLocalServiceUtil.dynamicQuery(query);
    }
    
     @Override
    public RelationshipLevelDefinition getRelationshipLevel(int relationshipLevelSid) throws SystemException, PortalException {
        return RelationshipLevelDefinitionLocalServiceUtil.getRelationshipLevelDefinition(relationshipLevelSid);
    }
    
    @Override
    public List getCustomViewList(DynamicQuery query) throws SystemException {
        return CustomViewMasterLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public CustomViewMaster addCustomView(CustomViewMaster customViewMaster) throws SystemException {
        return CustomViewMasterLocalServiceUtil.addCustomViewMaster(customViewMaster);
    }

    @Override
    public CustomViewMaster deleteCustomView(CustomViewMaster customViewMaster) throws SystemException {
        return CustomViewMasterLocalServiceUtil.deleteCustomViewMaster(customViewMaster);
    }

    @Override
    public CustomViewMaster deleteCustomView(int customViewMasterId) throws SystemException,PortalException {
            return CustomViewMasterLocalServiceUtil.deleteCustomViewMaster(customViewMasterId);
    }

    @Override
    public CustomViewMaster getCustomView(int customViewMasterId) throws SystemException, PortalException {
        return CustomViewMasterLocalServiceUtil.getCustomViewMaster(customViewMasterId);
    }

    @Override
    public CustomViewMaster updateCustomView(CustomViewMaster customViewMaster) throws SystemException {
        return CustomViewMasterLocalServiceUtil.updateCustomViewMaster(customViewMaster);
    }

    @Override
    public List getCustomViewDetailsList(DynamicQuery query) throws SystemException {
        return CustomViewDetailsLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public CustomViewDetails addCustomViewDetails(CustomViewDetails customViewDetails) throws SystemException {
        return CustomViewDetailsLocalServiceUtil.addCustomViewDetails(customViewDetails);
    }

    @Override
    public CustomViewDetails deleteCustomViewDetails(CustomViewDetails customViewDetails) throws SystemException {
        return CustomViewDetailsLocalServiceUtil.deleteCustomViewDetails(customViewDetails);
    }

    @Override
    public CustomViewDetails deleteCustomViewDetails(int customViewDetailsId) throws SystemException, PortalException {
        return CustomViewDetailsLocalServiceUtil.deleteCustomViewDetails(customViewDetailsId);
    }

    @Override
    public CustomViewDetails getCustomViewDetails(int customViewDetailsId) throws SystemException, PortalException {
        return CustomViewDetailsLocalServiceUtil.getCustomViewDetails(customViewDetailsId);
    }

    @Override
    public CustomViewDetails updateCustomViewDetails(CustomViewDetails customViewDetails) throws SystemException {
        return CustomViewDetailsLocalServiceUtil.updateCustomViewDetails(customViewDetails);
    }
    
    @Override
    public List getDiscountNoList(int projectionId, List<String> priceGroupType) {
        return new NmDiscountImpl().getDiscountNo(projectionId,priceGroupType);
    }

    @Override
    public Object executeSelectQuery(String query, Object udc1, Object udc2) {
       return new NMSalesProjectionMasterImpl().executeSelectQuery(query, udc1, udc2);
    }

    @Override
    public Object executeBulkUpdateQuery(String query, Object udc1, Object udc2) {
        return new NMSalesProjectionMasterImpl().executeBulkUpdateQuery(query, udc1, udc2);
    }

    @Override
    public Object executeUpdateQuery(List<?> nmSalesList, Object udc1, Object udc2, Object udc3) {
         return new NMSalesProjectionMasterImpl().executeUpdateQuery(nmSalesList, udc1, udc2, udc3);
    }
}
