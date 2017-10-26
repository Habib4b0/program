/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnworkflow.dao;

import com.stpl.app.model.ProjectionMaster;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;

/**
 *
 * @author santanukumar
 */
public interface DataSelectionDAO {

    /**
     * Gets the user.
     *
     * @param systemId the system id
     * @return the user
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    User getUser(Long systemId) throws SystemException, PortalException;

    /**
     * Adds the projection master.
     *
     * @param projectionMaster the projection master
     * @return the projection master
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    ProjectionMaster addProjectionMaster(ProjectionMaster projectionMaster) throws SystemException;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    ProjectionMaster getProjectionMaster(int id) throws SystemException,PortalException ;

    /**
     * 
     * @param projectionMaster
     * @return
     * @throws Exception 
     */
    ProjectionMaster updateProjectionMaster(ProjectionMaster projectionMaster) throws SystemException ;

}
