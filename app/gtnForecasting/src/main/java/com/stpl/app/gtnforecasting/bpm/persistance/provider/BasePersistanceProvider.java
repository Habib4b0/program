package com.stpl.app.gtnforecasting.bpm.persistance.provider;

import com.stpl.app.gtnforecasting.service.finderImpl.NMSalesProjectionMasterImpl;
import java.util.List;

/**
 *
 * @author arulmurugan
 */
public class BasePersistanceProvider {
    
    @SuppressWarnings("unchecked")
    protected static List<Object[]> executeSelectQuery(String customQuery) {

        return (List<Object[]>) new NMSalesProjectionMasterImpl().executeSelectQuery(customQuery);
    }

    protected static Boolean executeBulkUpdateQuery(String customQuery) {

        return (Boolean) new NMSalesProjectionMasterImpl().executeBulkUpdateQuery(customQuery);
    }

    protected static Boolean executeUpdateQuery(List<?> nmSalesList) {

        return (Boolean) new NMSalesProjectionMasterImpl().executeUpdateQuery(nmSalesList);
    }
}
