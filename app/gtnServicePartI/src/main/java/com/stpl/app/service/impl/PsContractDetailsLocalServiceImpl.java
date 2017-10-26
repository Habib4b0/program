package com.stpl.app.service.impl;

import com.stpl.app.service.base.PsContractDetailsLocalServiceBaseImpl;
import com.stpl.app.service.persistence.PsContractDetailsFinderUtil;

/**
 * The implementation of the ps contract details local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.PsContractDetailsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.PsContractDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.PsContractDetailsLocalServiceUtil
 */
public class PsContractDetailsLocalServiceImpl
    extends PsContractDetailsLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.PsContractDetailsLocalServiceUtil} to access the ps contract details local service.
     */
	public java.lang.Boolean savePsDetailsAttached(
	        java.util.List<java.lang.Object> input, java.lang.Object future) {
	        return PsContractDetailsFinderUtil.savePsDetailsAttached(input, future);
	    }
}
