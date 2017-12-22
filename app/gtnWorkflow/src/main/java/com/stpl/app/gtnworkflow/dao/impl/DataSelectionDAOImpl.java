package com.stpl.app.gtnworkflow.dao.impl;

import com.stpl.app.gtnworkflow.dao.DataSelectionDAO;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 *
 * @author santanukumar
 */
public class DataSelectionDAOImpl implements DataSelectionDAO {

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
