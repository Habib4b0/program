package com.stpl.app.service.impl;

import com.stpl.app.service.base.NmPpaProjectionMasterLocalServiceBaseImpl;
import com.stpl.app.service.persistence.NmPpaProjectionMasterFinderUtil;

/**
 * The implementation of the nm ppa projection master local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.NmPpaProjectionMasterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.NmPpaProjectionMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.NmPpaProjectionMasterLocalServiceUtil
 */
public class NmPpaProjectionMasterLocalServiceImpl
    extends NmPpaProjectionMasterLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.NmPpaProjectionMasterLocalServiceUtil} to access the nm ppa projection master local service.
     */
     public java.util.List getPPAProjectionList(java.lang.Integer projectionId,
        int levelNo, java.lang.String parent, boolean last, int startIndex,
        int endIndex, boolean isCount,java.lang.String levelName){
         return NmPpaProjectionMasterFinderUtil.getPPAProjectionList(projectionId,levelNo,parent,last,startIndex,endIndex,isCount,levelName);
     }
    public void setPPAProjectionMassUpdate(java.lang.Object priceCap,
        int startQuater, int endQuater, int startYear, int endYear,
        int projectionId, java.lang.String parent, java.lang.String levelValue)
    {
        NmPpaProjectionMasterFinderUtil.setPPAProjectionMassUpdate(priceCap, startQuater, endQuater, startYear, endYear, projectionId, parent,levelValue);
    }
    public java.util.List getPPAResults(java.lang.Integer projectionId,
        int levelNo, java.lang.String parent, boolean last, int startIndex,
        int endIndex, boolean isCount,java.util.List<java.lang.String> input,java.lang.String levelName)
    {
        return NmPpaProjectionMasterFinderUtil.getPPAResults(projectionId, levelNo, parent, last, startIndex, endIndex, isCount,input,levelName);
    }

    
    public java.util.List getLevelValues(int projectionId, int levelNo,
        java.lang.String parent){
        return NmPpaProjectionMasterFinderUtil.getLevelValues(projectionId, levelNo, parent);
    }


    public  java.util.List getProductHierarchyLevel(int projectionId,
        int levelNo, java.lang.String parent) {
        return NmPpaProjectionMasterFinderUtil
                   .getProductHierarchyLevel(projectionId, levelNo, parent);
    }
}
