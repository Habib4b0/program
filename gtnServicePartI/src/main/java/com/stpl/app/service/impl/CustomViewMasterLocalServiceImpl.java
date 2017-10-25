package com.stpl.app.service.impl;

import com.stpl.app.service.base.CustomViewMasterLocalServiceBaseImpl;
import com.stpl.app.service.persistence.CustomViewMasterFinderUtil;

/**
 * The implementation of the custom view master local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.CustomViewMasterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.CustomViewMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.CustomViewMasterLocalServiceUtil
 */
public class CustomViewMasterLocalServiceImpl
    extends CustomViewMasterLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.CustomViewMasterLocalServiceUtil} to access the custom view master local service.
     */
    
     public  java.util.List getHierarchyLevelsForDiscount(
        int projectionId, java.lang.String hierarchyIndicator, int levelNo,
        int hierarchyLevelDefId) {
        return CustomViewMasterFinderUtil
                   .getHierarchyLevelsForDiscount(projectionId,
            hierarchyIndicator, levelNo, hierarchyLevelDefId);
    }
}
