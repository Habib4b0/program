/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.dao;

import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
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
    public void savePPAProjectionView(String indicater, String projectionId)throws SystemException, PortalException, Exception;;

    public List<Object[]> getPPAResultsData(Integer projectionId,int levelNo,String parent,boolean last,int startIndex,int endIndex,boolean isCount,List input,String levelName);

    public List getLevelValues(int projectionId, int levelNo, String parent);
    
}
