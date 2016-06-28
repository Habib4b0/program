package com.stpl.app.service.impl;

import com.stpl.app.service.base.HistRelationshipBuilderLocalServiceBaseImpl;
import com.stpl.app.service.persistence.HistRelationshipBuilderFinderUtil;

/**
 * The implementation of the hist relationship builder local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.HistRelationshipBuilderLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.HistRelationshipBuilderLocalServiceBaseImpl
 * @see com.stpl.app.service.HistRelationshipBuilderLocalServiceUtil
 */
public class HistRelationshipBuilderLocalServiceImpl
    extends HistRelationshipBuilderLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.HistRelationshipBuilderLocalServiceUtil} to access the hist relationship builder local service.
     */
        public java.util.List findByTableName(java.lang.String tableName,
        java.lang.String columnName){
        return HistRelationshipBuilderFinderUtil.findByTableName(tableName, columnName);
    }

    public java.util.List findByTableName(java.lang.String tableName,
        java.lang.String columnName, java.util.List hierListValues){
        return HistRelationshipBuilderFinderUtil.findByTableName(tableName, columnName, hierListValues);
    }
    
    public java.util.List findFilteredLevelValues(java.lang.String query){
        return HistRelationshipBuilderFinderUtil.findFilteredLevelValues(query);
    }
    
     public  java.util.List executeQuery(java.lang.String query) {
        return HistRelationshipBuilderFinderUtil.executeQuery(query);
    }
}
