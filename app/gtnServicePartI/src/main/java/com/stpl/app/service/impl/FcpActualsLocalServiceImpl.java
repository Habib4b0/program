package com.stpl.app.service.impl;

import com.stpl.app.service.base.FcpActualsLocalServiceBaseImpl;
import com.stpl.app.service.persistence.FcpActualsFinderUtil;
import java.util.List;

/**
 * The implementation of the fcp actuals local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.FcpActualsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.FcpActualsLocalServiceBaseImpl
 * @see com.stpl.app.service.FcpActualsLocalServiceUtil
 */
public class FcpActualsLocalServiceImpl extends FcpActualsLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.FcpActualsLocalServiceUtil} to access the fcp actuals local service.
     */
 @Override
    public Object executeSelectQuery(String query) {
        return FcpActualsFinderUtil.executeSelectQuery(query);
    }

    @Override
    public Object executeBulkUpdateQuery(String query) {
        return FcpActualsFinderUtil.executeBulkUpdateQuery(query);
    }

    @Override
    public Object executeUpdateQuery(List<StringBuilder> queryList) {
        return FcpActualsFinderUtil.executeUpdateQuery(queryList);
    }
    @Override
    public Object executeUpdateQuery(String query) {
        return FcpActualsFinderUtil.executeUpdateQuery(query);
    }
}

