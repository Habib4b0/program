package com.stpl.app.service.impl;

import com.stpl.app.service.base.IfpDetailsLocalServiceBaseImpl;
import com.stpl.app.service.persistence.IfpDetailsUtil;

/**
 * The implementation of the ifp details local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.IfpDetailsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.IfpDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.IfpDetailsLocalServiceUtil
 */
public class IfpDetailsLocalServiceImpl extends IfpDetailsLocalServiceBaseImpl {
	 /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.global.service.IfpDetailsLocalServiceUtil} to access the ifp details local service.
     */
     public java.util.List<com.stpl.app.model.IfpDetails> findByItemFamilyPlanDetails(
        int ifpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return IfpDetailsUtil.findByItemFamilyPlanDetails(ifpModelSid);
    }
}
