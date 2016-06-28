package com.stpl.app.service.impl;

import java.util.List;
import java.util.Map;

import com.stpl.app.service.base.CfpModelLocalServiceBaseImpl;
import com.stpl.app.service.persistence.CfpModelFinderUtil;

/**
 * The implementation of the cfp model local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.CfpModelLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.CfpModelLocalServiceBaseImpl
 * @see com.stpl.app.service.CfpModelLocalServiceUtil
 */
public class CfpModelLocalServiceImpl extends CfpModelLocalServiceBaseImpl {
	 /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.global.service.CfpModelLocalServiceUtil} to access the cfp model local service.
     */
    
    public List findCfpModelV1(Map<Object, Object> cfp, String orderByColumn, Boolean sortOrder, Object index, Object next, Map<Object, Object> parameters, String operation, Object future, Object future1) {
        return CfpModelFinderUtil.findCfpModelV1(cfp, orderByColumn, sortOrder, index, next, parameters, operation, future, future1); 
    }
}
