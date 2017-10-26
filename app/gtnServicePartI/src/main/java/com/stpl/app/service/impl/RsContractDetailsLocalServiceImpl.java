package com.stpl.app.service.impl;

import com.stpl.app.service.base.RsContractDetailsLocalServiceBaseImpl;
import com.stpl.app.service.persistence.RsContractDetailsFinderUtil;

/**
 * The implementation of the rs contract details local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.RsContractDetailsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.RsContractDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.RsContractDetailsLocalServiceUtil
 */
public class RsContractDetailsLocalServiceImpl
    extends RsContractDetailsLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.RsContractDetailsLocalServiceUtil} to access the rs contract details local service.
     */
	public java.lang.Boolean saveRsDetailsAttached(
	        java.util.List<java.lang.Object> input, java.lang.Object future) {
	        return RsContractDetailsFinderUtil.saveRsDetailsAttached(input, future);
	    }
	    
	    public void saveFormulaToContractRs(int contRsdSid,int rsdSid,int itemSid){
	        RsContractDetailsFinderUtil.saveFormulaToContractRs(contRsdSid,rsdSid,itemSid);
	    }
}
