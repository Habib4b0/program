/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dao;

import com.stpl.app.model.ProjectionMaster;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asha.Ravi
 */
public interface DataSelectionDAO {
    
    /**
     * Gets the projection master.
     *
     * @param systemId the system id
     * @return the projection master
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public List<ProjectionMaster> getProjectionMaster(final DynamicQuery dynamicQuery) throws PortalException, SystemException;
    
    /**
     * Update projection master.
     *
     * @param projectionMaster the projection master
     * @return the projection master
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    ProjectionMaster updateProjectionMaster(ProjectionMaster projectionMaster) throws PortalException, SystemException;
    
    /**
     * Gets the projection master.
     *
     * @param systemId the system id
     * @return the projection master
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    ProjectionMaster getProjectionMaster(int systemId) throws PortalException, SystemException;
    
    public Object tempOperation(final Map<String, Object> input, final String queryName) throws SystemException;
}
