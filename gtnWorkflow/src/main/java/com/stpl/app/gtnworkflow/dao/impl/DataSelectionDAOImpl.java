package com.stpl.app.gtnworkflow.dao.impl;

import com.stpl.app.gtnworkflow.dao.DataSelectionDAO;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;

/**
 *
 * @author santanukumar
 */
public class DataSelectionDAOImpl implements DataSelectionDAO {

    /**
     * Gets the user.
     *
     * @param systemId the system id
     * @return the user
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @Override
    public User getUser(final Long systemId) throws SystemException, PortalException {
        return UserLocalServiceUtil.getUser(systemId);
    }

    /**
     * Adds the projection master.
     *
     * @param projectionMaster the projection master
     * @return the projection master
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public ProjectionMaster addProjectionMaster(final ProjectionMaster projectionMaster) throws SystemException {
        return ProjectionMasterLocalServiceUtil.addProjectionMaster(projectionMaster);
    }

    /**
     * @param
     *
     * @param projectionMaster
     * @return
     * @throws Exception
     */
    @Override
    public ProjectionMaster updateProjectionMaster(ProjectionMaster projectionMaster) throws SystemException {
        return ProjectionMasterLocalServiceUtil.updateProjectionMaster(projectionMaster);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public ProjectionMaster getProjectionMaster(int id) throws SystemException,PortalException   {
        return ProjectionMasterLocalServiceUtil.getProjectionMaster(id);
    }

}
