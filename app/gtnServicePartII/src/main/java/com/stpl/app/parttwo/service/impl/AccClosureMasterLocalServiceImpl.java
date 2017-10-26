package com.stpl.app.parttwo.service.impl;

import com.stpl.app.parttwo.service.base.AccClosureMasterLocalServiceBaseImpl;
import com.stpl.app.parttwo.service.persistence.AccClosureMasterFinderUtil;

/**
 * The implementation of the acc closure master local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.stpl.app.parttwo.service.AccClosureMasterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.base.AccClosureMasterLocalServiceBaseImpl
 * @see com.stpl.app.parttwo.service.AccClosureMasterLocalServiceUtil
 */
public class AccClosureMasterLocalServiceImpl
        extends AccClosureMasterLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.parttwo.service.AccClosureMasterLocalServiceUtil} to access the acc closure master local service.
     */

    public java.lang.Object executeSelectQuery(java.util.List input,
            java.lang.String queryName, java.lang.String quaryName2) {
        return AccClosureMasterFinderUtil.executeSelectQuery(input, queryName, quaryName2);
    }

    public java.lang.Boolean executeUpdateQuery(java.util.List input,
            java.lang.String queryName) {
        return AccClosureMasterFinderUtil.executeUpdateQuery(input, queryName);
    }

    public java.lang.String getQuery(java.util.List input,
            java.lang.String queryName) {
        return AccClosureMasterFinderUtil.getQuery(input, queryName);
    }
}
