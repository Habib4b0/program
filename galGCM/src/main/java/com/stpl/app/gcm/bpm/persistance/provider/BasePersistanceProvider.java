package com.stpl.app.galforecasting.bpm.persistance.provider;

import java.util.List;

import com.stpl.app.service.NmSalesProjectionMasterLocalServiceUtil;

/**
 *
 * @author arulmurugan
 */
public class BasePersistanceProvider {

    @SuppressWarnings("unchecked")
    protected static List<Object[]> executeSelectQuery(String customQuery, Object udc1, Object udc2) {

        return (List<Object[]>) NmSalesProjectionMasterLocalServiceUtil.executeSelectQuery(customQuery, udc1, udc2);
    }

    protected static Boolean executeBulkUpdateQuery(String customQuery, Object udc1, Object udc2) {

        return (Boolean) NmSalesProjectionMasterLocalServiceUtil.executeBulkUpdateQuery(customQuery, udc1, udc2);
    }

    protected static Boolean executeUpdateQuery(List<?> nmSalesList, Object udc1, Object udc2, Object udc3) {

        return (Boolean) NmSalesProjectionMasterLocalServiceUtil.executeUpdateQuery(nmSalesList, udc1, udc2, udc3);
    }
}
