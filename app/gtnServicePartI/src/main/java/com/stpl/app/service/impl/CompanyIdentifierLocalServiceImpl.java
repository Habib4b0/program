package com.stpl.app.service.impl;

import com.stpl.app.service.base.CompanyIdentifierLocalServiceBaseImpl;
import com.stpl.app.service.persistence.CompanyIdentifierUtil;

/**
 * The implementation of the company identifier local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.stpl.app.service.CompanyIdentifierLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.CompanyIdentifierLocalServiceBaseImpl
 * @see com.stpl.app.service.CompanyIdentifierLocalServiceUtil
 */
public class CompanyIdentifierLocalServiceImpl extends
		CompanyIdentifierLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.stpl.app.global.service.CompanyIdentifierLocalServiceUtil} to access
	 * the company identifier local service.
	 */
	public java.util.List<com.stpl.app.model.CompanyIdentifier> findByCompanyCrtIdentifierDetails(
			int companyMasterSid)
			throws com.stpl.portal.kernel.exception.SystemException {
		return CompanyIdentifierUtil
				.findByCompanyCrtIdentifierDetails(companyMasterSid);
	}
}
