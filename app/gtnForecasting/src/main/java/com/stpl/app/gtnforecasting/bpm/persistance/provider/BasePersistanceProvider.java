package com.stpl.app.gtnforecasting.bpm.persistance.provider;

import com.stpl.app.gtnforecasting.service.finderImpl.NMSalesProjectionMasterImpl;
import com.stpl.app.service.NmSalesProjectionMasterLocalServiceUtil;
import java.util.List;

/**
 *
 * @author arulmurugan
 */
public class BasePersistanceProvider {

    @SuppressWarnings("unchecked")
    protected static List<Object[]> executeSelectQuery(String customQuery, Object udc1, Object udc2) {

        return (List<Object[]>) new NMSalesProjectionMasterImpl().executeSelectQuery(customQuery, udc1, udc2);
    }

    protected static Boolean executeBulkUpdateQuery(String customQuery, Object udc1, Object udc2) {

        return (Boolean) new NMSalesProjectionMasterImpl().executeBulkUpdateQuery(customQuery, udc1, udc2);
    }

    protected static Boolean executeUpdateQuery(List<?> nmSalesList, Object udc1, Object udc2, Object udc3) {

        return (Boolean) new NMSalesProjectionMasterImpl().executeUpdateQuery(nmSalesList, udc1, udc2, udc3);
    }
}
