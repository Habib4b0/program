package com.stpl.app.service.impl;

import com.stpl.app.service.base.ProjectionCustHierarchyLocalServiceBaseImpl;
import com.stpl.app.service.persistence.ProjectionCustHierarchyFinderUtil;

/**
 * The implementation of the projection cust hierarchy local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.ProjectionCustHierarchyLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.ProjectionCustHierarchyLocalServiceBaseImpl
 * @see com.stpl.app.service.ProjectionCustHierarchyLocalServiceUtil
 */
public class ProjectionCustHierarchyLocalServiceImpl
    extends ProjectionCustHierarchyLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.ProjectionCustHierarchyLocalServiceUtil} to access the projection cust hierarchy local service.
     */
      public  void insert(java.util.List list, java.lang.String string1,
        java.lang.String string2) {
        ProjectionCustHierarchyFinderUtil.insert(list, string1, string2);
    }

    public  java.util.List retrive(java.util.List list,
        java.lang.String string1, java.lang.String string2) {
        return ProjectionCustHierarchyFinderUtil.retrive(list, string1, string2);
    }
    
     public java.util.List getComparisonSearch(java.lang.String workflowStatus,
        java.lang.String marketType, java.lang.String brand,
        java.lang.String projName, java.lang.String contHldr,
        java.lang.String ndcNo, java.lang.String ndcName,
        java.lang.String desc, java.lang.String contract,
        java.lang.String from, java.lang.String to){
         
         return ProjectionCustHierarchyFinderUtil.getComparisonSearch(workflowStatus, marketType, brand, projName, contHldr, ndcNo, ndcName, desc, contract, from, to);
     }
}
