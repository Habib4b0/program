package com.stpl.app.service.impl;

import com.stpl.app.service.base.RelationshipBuilderLocalServiceBaseImpl;
import com.stpl.app.service.persistence.RelationshipBuilderFinderUtil;
import java.util.List;

/**
 * The implementation of the relationship builder local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.RelationshipBuilderLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.RelationshipBuilderLocalServiceBaseImpl
 * @see com.stpl.app.service.RelationshipBuilderLocalServiceUtil
 */
public class RelationshipBuilderLocalServiceImpl
    extends RelationshipBuilderLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.RelationshipBuilderLocalServiceUtil} to access the relationship builder local service.
     */
    	public java.util.List findByTableName(java.lang.String tableName, java.lang.String columnName) throws com.stpl.portal.kernel.exception.SystemException {
		return RelationshipBuilderFinderUtil.findByTableName(tableName, columnName);
	}

	public java.util.List findByTableName(java.lang.String tableName, java.lang.String columnName, List hierValues) throws com.stpl.portal.kernel.exception.SystemException {
		return RelationshipBuilderFinderUtil.findByTableName(tableName, columnName, hierValues);
	}
}
