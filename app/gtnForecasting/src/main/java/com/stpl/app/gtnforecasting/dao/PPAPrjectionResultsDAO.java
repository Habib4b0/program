/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author srithar
 */
public interface PPAPrjectionResultsDAO {
/**
 * 
 * @param indicater
 * @param projectionId
 * @throws SystemException
 * @throws PortalException
 * @throws Exception 
 */
    public void savePPAProjectionView(String indicater, String projectionId)throws PortalException;

    public List<Object[]> getPPAResultsData(Integer projectionId,int levelNo,String parent, boolean isCount,List input,String levelName);

    public List getLevelValues(int projectionId, int levelNo, String parent);
    
}
