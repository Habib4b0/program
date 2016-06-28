package com.stpl.app.service.impl;

import com.stpl.app.service.base.NmSalesProjectionLocalServiceBaseImpl;
import com.stpl.app.service.persistence.NmSalesProjectionFinderUtil;

/**
 * The implementation of the nm sales projection local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.NmSalesProjectionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.NmSalesProjectionLocalServiceBaseImpl
 * @see com.stpl.app.service.NmSalesProjectionLocalServiceUtil
 */
public class NmSalesProjectionLocalServiceImpl
    extends NmSalesProjectionLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.NmSalesProjectionLocalServiceUtil} to access the nm sales projection local service.
     */
      public java.util.List getSalesResult(java.lang.Object[] inputs) {
        return NmSalesProjectionFinderUtil.getSalesResult(inputs);
    }
    
    public java.util.List getAssumptionResult(java.util.List input, String queryName) {
        return NmSalesProjectionFinderUtil.getAssumptionResult(input, queryName);
    }

    public java.util.List getSalesProjectionResults(java.lang.Object[] inputs) {
        return NmSalesProjectionFinderUtil.getSalesProjectionResults(inputs);
    }

    public java.util.List getSalesProjectionResultLevels(java.lang.Object[] inputs) {
        return NmSalesProjectionFinderUtil.getSalesProjectionResultLevels(inputs);
    }

    public java.util.List getVarianceSales(int projectionId,
            java.lang.String frequency,
            java.util.List<java.lang.Integer> startAndEndPeriods,
            java.lang.String actualsOrProjections, java.lang.String parentName,
            java.lang.String year, int levelNo, java.lang.String sales) {
        return NmSalesProjectionFinderUtil.getVarianceSales(projectionId, frequency,
                startAndEndPeriods, actualsOrProjections, parentName, year,
                levelNo, sales);
    }
}
