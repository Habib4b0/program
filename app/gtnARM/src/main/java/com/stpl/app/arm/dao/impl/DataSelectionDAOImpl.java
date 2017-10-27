/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dao.impl;

import com.stpl.app.arm.dao.DataSelectionDAO;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asha.Ravi
 */
public class DataSelectionDAOImpl implements DataSelectionDAO {

    /**
     * Gets the projection master.
     *
     * @param systemId the system id
     * @return the projection master
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public List<ProjectionMaster> getProjectionMaster(final DynamicQuery dynamicQuery) throws PortalException, SystemException {
        return ProjectionMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Update projection master.
     *
     * @param projectionMaster the projection master
     * @return the projection master
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public ProjectionMaster updateProjectionMaster(final ProjectionMaster projectionMaster) throws PortalException, SystemException {
        return ProjectionMasterLocalServiceUtil.updateProjectionMaster(projectionMaster);
    }

    /**
     * Gets the projection master.
     *
     * @param systemId the system id
     * @return the projection master
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public ProjectionMaster getProjectionMaster(final int systemId) throws PortalException, SystemException {
        return ProjectionMasterLocalServiceUtil.getProjectionMaster(systemId);
    }

    @Override
    public Object tempOperation(Map<String, Object> input, String queryName) throws SystemException {
        return ProjectionMasterLocalServiceUtil.tempOperation(input, queryName);
    }

}
