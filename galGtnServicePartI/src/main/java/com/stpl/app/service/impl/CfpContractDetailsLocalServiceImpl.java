package com.stpl.app.service.impl;

import java.util.List;
import java.util.Map;

import com.stpl.app.service.base.CfpContractDetailsLocalServiceBaseImpl;
import com.stpl.app.service.persistence.CfpContractDetailsFinderUtil;

/**
 * The implementation of the cfp contract details local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.CfpContractDetailsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.CfpContractDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.CfpContractDetailsLocalServiceUtil
 */
public class CfpContractDetailsLocalServiceImpl
    extends CfpContractDetailsLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.CfpContractDetailsLocalServiceUtil} to access the cfp contract details local service.
     */
	public java.lang.Boolean saveCfpDetailsAttached(
	        java.util.List<java.lang.Object> input, java.lang.Object future) {
	        return CfpContractDetailsFinderUtil.saveCfpDetailsAttached(input, future);
	    }
	    
	    public List getCompaniesList(String searchField, String searchVal,Map<String, Object> filterMap, int start, int offset, String column, String orderBy, Object future1, Object future2) {
	        return CfpContractDetailsFinderUtil.getCompaniesList(searchField, searchVal,filterMap, start, offset, column, orderBy, future1, future2);
	    }
	
}
