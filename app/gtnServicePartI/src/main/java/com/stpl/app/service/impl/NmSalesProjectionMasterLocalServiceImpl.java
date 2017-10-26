package com.stpl.app.service.impl;

import com.stpl.app.service.base.NmSalesProjectionMasterLocalServiceBaseImpl;
import com.stpl.app.service.persistence.NmSalesProjectionMasterFinderUtil;
import java.util.List;

/**
 * The implementation of the nm sales projection master local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.NmSalesProjectionMasterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.NmSalesProjectionMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.NmSalesProjectionMasterLocalServiceUtil
 */
public class NmSalesProjectionMasterLocalServiceImpl
    extends NmSalesProjectionMasterLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.NmSalesProjectionMasterLocalServiceUtil} to access the nm sales projection master local service.
     */
    
        public Object executeSelectQuery(String query, Object udc1, Object udc2) {

		return NmSalesProjectionMasterFinderUtil.executeSelectQuery(query, udc1, udc2);
	}

	public Object executeBulkUpdateQuery(String query, Object udc1, Object udc2) {

		return NmSalesProjectionMasterFinderUtil.executeBulkUpdateQuery(query, udc1, udc2);
	}

	public Object executeUpdateQuery(List<?> nmSalesList, Object udc1, Object udc2, Object udc3) {

		return NmSalesProjectionMasterFinderUtil.executeUpdateQuery(nmSalesList, udc1, udc2, udc3);
	}
}
