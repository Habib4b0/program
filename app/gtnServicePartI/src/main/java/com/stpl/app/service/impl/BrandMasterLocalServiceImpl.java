package com.stpl.app.service.impl;

import com.stpl.app.service.base.BrandMasterLocalServiceBaseImpl;
import com.stpl.app.service.persistence.BrandMasterFinderUtil;

/**
 * The implementation of the brand master local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.BrandMasterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.BrandMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.BrandMasterLocalServiceUtil
 */
public class BrandMasterLocalServiceImpl extends BrandMasterLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.BrandMasterLocalServiceUtil} to access the brand master local service.
     */
	public java.lang.Object executeSelectQuery(java.lang.String query,
            java.lang.Object udc1, java.lang.Object udc2) {
        return BrandMasterFinderUtil.executeSelectQuery(query, udc1, udc2);
    }

    public java.lang.Object executeBulkUpdateQuery(java.lang.String query,
            java.lang.Object udc1, java.lang.Object udc2) {
        return BrandMasterFinderUtil.executeBulkUpdateQuery(query, udc1, udc2);
    }

    public java.lang.Object executeUpdateQuery(java.util.List<?> nmSalesList,
            java.lang.Object udc1, java.lang.Object udc2, java.lang.Object udc3) {

        return BrandMasterFinderUtil.executeUpdateQuery(nmSalesList, udc1, udc2, udc3);
    }
}
