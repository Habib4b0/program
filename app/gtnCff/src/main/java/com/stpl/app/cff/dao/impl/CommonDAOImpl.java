/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dao.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.dao.CommonDAO;
import com.stpl.app.model.RelationshipLevelDefinition;
import com.stpl.app.parttwo.model.CffCustomViewDetails;
import com.stpl.app.parttwo.model.CffCustomViewMaster;
import com.stpl.app.parttwo.service.CffCustomViewDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.CffCustomViewMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.RelationshipLevelDefinitionLocalServiceUtil;
import java.util.List;

/**
 *
 * @author mohamed.hameed
 */
public class CommonDAOImpl implements CommonDAO {

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
        return CffCustomViewMasterLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public CffCustomViewMaster addCustomView(CffCustomViewMaster customViewMaster) throws SystemException {
        return CffCustomViewMasterLocalServiceUtil.addCffCustomViewMaster(customViewMaster);
    }

    @Override
    public CffCustomViewMaster deleteCustomView(CffCustomViewMaster customViewMaster) throws SystemException {
        return CffCustomViewMasterLocalServiceUtil.deleteCffCustomViewMaster(customViewMaster);
    }

    @Override
    public CffCustomViewMaster deleteCustomView(int customViewMasterId) throws SystemException, PortalException {
        return CffCustomViewMasterLocalServiceUtil.deleteCffCustomViewMaster(customViewMasterId);
    }

    @Override
    public CffCustomViewMaster getCustomView(int customViewMasterId) throws SystemException, PortalException {
        return CffCustomViewMasterLocalServiceUtil.getCffCustomViewMaster(customViewMasterId);
    }

    @Override
    public CffCustomViewMaster updateCustomView(CffCustomViewMaster customViewMaster) throws SystemException {
        return CffCustomViewMasterLocalServiceUtil.updateCffCustomViewMaster(customViewMaster);
    }

    @Override
    public List getCustomViewDetailsList(DynamicQuery query) throws SystemException {
        return CffCustomViewDetailsLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public CffCustomViewDetails addCustomViewDetails(CffCustomViewDetails customViewDetails) throws SystemException {
        return CffCustomViewDetailsLocalServiceUtil.addCffCustomViewDetails(customViewDetails);
    }

    @Override
    public CffCustomViewDetails deleteCustomViewDetails(CffCustomViewDetails customViewDetails) throws SystemException {
        return CffCustomViewDetailsLocalServiceUtil.deleteCffCustomViewDetails(customViewDetails);
    }

    @Override
    public CffCustomViewDetails deleteCustomViewDetails(int customViewDetailsId) throws SystemException, PortalException {
        return CffCustomViewDetailsLocalServiceUtil.deleteCffCustomViewDetails(customViewDetailsId);
    }

    @Override
    public CffCustomViewDetails getCustomViewDetails(int customViewDetailsId) throws SystemException, PortalException {
        return CffCustomViewDetailsLocalServiceUtil.getCffCustomViewDetails(customViewDetailsId);
    }

    @Override
    public CffCustomViewDetails updateCustomViewDetails(CffCustomViewDetails customViewDetails) throws SystemException {
        return CffCustomViewDetailsLocalServiceUtil.updateCffCustomViewDetails(customViewDetails);
    }

    @Override
    public Object executeSelectQuery(String query) {
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

}
