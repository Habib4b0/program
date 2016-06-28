package com.stpl.app.service.impl;

import com.stpl.app.service.base.CompanyTradeClassLocalServiceBaseImpl;
import com.stpl.app.service.persistence.CompanyTradeClassFinderUtil;

/**
 * The implementation of the company trade class local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.CompanyTradeClassLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.CompanyTradeClassLocalServiceBaseImpl
 * @see com.stpl.app.service.CompanyTradeClassLocalServiceUtil
 */
public class CompanyTradeClassLocalServiceImpl
    extends CompanyTradeClassLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.CompanyTradeClassLocalServiceUtil} to access the company trade class local service.
     */
    public java.util.List getTradeClassDetails(java.lang.String companySystemId, java.lang.String tradeClassSysId) {
		return CompanyTradeClassFinderUtil.getTradeClassDetails(companySystemId, tradeClassSysId);
	}

	public java.util.List getCompanyparentDetails(java.lang.String companySystemId, java.lang.String parentSysId) {
		return CompanyTradeClassFinderUtil.getCompanyparentDetails(companySystemId, parentSysId);
	}
}
