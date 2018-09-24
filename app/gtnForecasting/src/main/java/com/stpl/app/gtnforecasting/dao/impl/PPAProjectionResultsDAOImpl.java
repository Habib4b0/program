/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.PPAPrjectionResultsDAO;
import com.stpl.app.gtnforecasting.service.finderImpl.NmPpaProjectionMasterImpl;
import java.util.List;

/**
 *
 * @author srithar
 */
public class PPAProjectionResultsDAOImpl implements PPAPrjectionResultsDAO {

    /**
     *
     * @param indicater
     * @param projectionId
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    @Override
    public void savePPAProjectionView(String indicater, String projectionId) throws PortalException {
        return;
    }

    @Override
    public List<Object[]> getPPAResultsData(Integer projectionId, int levelNo, String parent, boolean isCount, List input, String levelName) {
        return new NmPpaProjectionMasterImpl().getPPAResults(projectionId, levelNo, parent,  isCount, input, levelName);
    }

    @Override
    public List getLevelValues(int projectionId, int levelNo, String parent) {
        return new NmPpaProjectionMasterImpl().getLevelValues(projectionId, levelNo, parent);
    }

}
