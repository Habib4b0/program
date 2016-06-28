package com.stpl.app.service.impl;

import com.stpl.app.service.base.StChSalesProjectionMasterLocalServiceBaseImpl;
import com.stpl.app.service.persistence.StChSalesProjectionMasterFinderUtil;

/**
 * The implementation of the st ch sales projection master local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.StChSalesProjectionMasterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.StChSalesProjectionMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.StChSalesProjectionMasterLocalServiceUtil
 */
public class StChSalesProjectionMasterLocalServiceImpl
    extends StChSalesProjectionMasterLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.StChSalesProjectionMasterLocalServiceUtil} to access the st ch sales projection master local service.
     */
    
     public java.util.List executeQuery(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return StChSalesProjectionMasterFinderUtil.executeQuery(parameters);
    }
}
