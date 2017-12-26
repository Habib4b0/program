/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnworkflow.dao;

import com.stpl.app.model.ProjectionMaster;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 *
 * @author santanukumar
 */
public interface DataSelectionDAO {

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
