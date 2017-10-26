package com.stpl.app.service.impl;

import com.stpl.app.service.base.CompanyQualifierLocalServiceBaseImpl;
import com.stpl.app.service.persistence.CompanyQualifierUtil;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * The implementation of the company qualifier local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.CompanyQualifierLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.CompanyQualifierLocalServiceBaseImpl
 * @see com.stpl.app.service.CompanyQualifierLocalServiceUtil
 */
public class CompanyQualifierLocalServiceImpl
    extends CompanyQualifierLocalServiceBaseImpl {
	/**
	    * Returns the company qualifier where companyQualifierName = &#63; or throws a {@link com.stpl.app.global.NoSuchCompanyQualifierException} if it could not be found.
	    *
	    * @param companyQualifierName the company qualifier name
	    * @return the matching company qualifier
	    * @throws com.stpl.app.global.NoSuchCompanyQualifierException if a matching company qualifier could not be found
	    * @throws SystemException if a system exception occurred
	    */
	    public com.stpl.app.model.CompanyQualifier findByCompanyCrtQualifierByName(
	        java.lang.String companyQualifierName)
	        throws com.stpl.app.NoSuchCompanyQualifierException,
	            com.stpl.portal.kernel.exception.SystemException {
	        return CompanyQualifierUtil.findByCompanyCrtQualifierByName(companyQualifierName);
	    }
}
