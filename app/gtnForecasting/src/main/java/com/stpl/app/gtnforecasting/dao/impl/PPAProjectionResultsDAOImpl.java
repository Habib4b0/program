/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao.impl;

import com.stpl.app.gtnforecasting.dao.PPAPrjectionResultsDAO;
import com.stpl.app.service.NmPpaProjectionMasterLocalServiceUtil;
import static com.stpl.app.service.NmPpaProjectionMasterLocalServiceUtil.getPPAResults;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author srithar
 */
public class PPAProjectionResultsDAOImpl implements PPAPrjectionResultsDAO{
/**
 * 
 * @param indicater
 * @param projectionId
 * @throws SystemException
 * @throws PortalException
 * @throws Exception 
 */
    @Override
    public void savePPAProjectionView(String indicater, String projectionId)throws SystemException, PortalException {
        
    }

    @Override
    public List<Object[]> getPPAResultsData(Integer projectionId,int levelNo,String parent,boolean last,int startIndex,int endIndex,boolean isCount,List input,String levelName) {
        return getPPAResults(projectionId, levelNo, parent, last,startIndex,endIndex,isCount,input,levelName);
    }

    @Override
    public List getLevelValues(int projectionId, int levelNo, String parent) {
      return NmPpaProjectionMasterLocalServiceUtil.getLevelValues(projectionId, levelNo, parent);
    }
    
}
