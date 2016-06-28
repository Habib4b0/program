package com.stpl.app.service.impl;

import java.util.List;
import java.util.Map;

import com.stpl.app.service.base.IfpContractDetailsLocalServiceBaseImpl;
import com.stpl.app.service.persistence.IfpContractDetailsFinderUtil;

/**
 * The implementation of the ifp contract details local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.IfpContractDetailsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.IfpContractDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.IfpContractDetailsLocalServiceUtil
 */
public class IfpContractDetailsLocalServiceImpl
    extends IfpContractDetailsLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.IfpContractDetailsLocalServiceUtil} to access the ifp contract details local service.
     */
	public java.lang.Boolean saveIfpDetailsAttached(
	        java.util.List<java.lang.Object> input, java.lang.Object future) {
	        return IfpContractDetailsFinderUtil.saveIfpDetailsAttached(input, future);
	    }
	    
	    public List findIFP(final Object field, final Object value, final List<Integer> future,Map<String, Object> filterMap, int start, int end, String column, String orderBy, Object future1){
	        return IfpContractDetailsFinderUtil.findIFP(field, value, future,filterMap, start, end, column, orderBy, future1);
	    }
}
