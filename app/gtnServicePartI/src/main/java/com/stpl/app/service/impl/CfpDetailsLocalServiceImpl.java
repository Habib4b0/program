package com.stpl.app.service.impl;

import com.stpl.app.service.base.CfpDetailsLocalServiceBaseImpl;
import com.stpl.app.service.persistence.CfpDetailsUtil;

/**
 * The implementation of the cfp details local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.stpl.app.service.CfpDetailsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.CfpDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.CfpDetailsLocalServiceUtil
 */
public class CfpDetailsLocalServiceImpl extends CfpDetailsLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.stpl.app.service.CfpDetailsLocalServiceUtil} to access the cfp
	 * details local service.
	 */
	public java.util.List<com.stpl.app.model.CfpDetails> findByCfpModelSid(
			int cfpModelSid)
			throws com.stpl.portal.kernel.exception.SystemException {
		return CfpDetailsUtil.getPersistence().findByCfpModelSid(cfpModelSid);
	}
}
