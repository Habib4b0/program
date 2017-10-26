package com.stpl.app.service.impl;

import com.stpl.app.service.base.HierarchyDefinitionLocalServiceBaseImpl;
import com.stpl.app.service.persistence.HierarchyDefinitionFinderUtil;

/**
 * The implementation of the hierarchy definition local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.HierarchyDefinitionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.HierarchyDefinitionLocalServiceBaseImpl
 * @see com.stpl.app.service.HierarchyDefinitionLocalServiceUtil
 */
public class HierarchyDefinitionLocalServiceImpl
    extends HierarchyDefinitionLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.HierarchyDefinitionLocalServiceUtil} to access the hierarchy definition local service.
     */
    
      public  java.util.List getHierarchyGroup(
        java.lang.String hierarchyName, java.lang.String hierarchyType,
        java.lang.String customerOrProduct, java.lang.String action) {
        return HierarchyDefinitionFinderUtil
                   .getHierarchyGroup(hierarchyName, hierarchyType,
            customerOrProduct, action);
    }
    
     public java.util.List getLevelsFromHierarchy(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return HierarchyDefinitionFinderUtil.getLevelsFromHierarchy(parameters);
    }
     
      public java.util.List getHierarchySystemId(int relationshipLevelId) {
        return HierarchyDefinitionFinderUtil.getHierarchySystemId(relationshipLevelId);
    }
}
