package com.stpl.app.arm.bpm.persistance.provider;

import java.util.List;


/**
 *
 * @author asha
 */
public class BasePersistanceProvider {

    protected BasePersistanceProvider() {
    }

    @SuppressWarnings("unchecked")
    protected static List<Object[]> executeSelectQuery(String customQuery, Object udc1, Object udc2) {

        return null;
    }

    protected static Boolean executeBulkUpdateQuery(String customQuery, Object udc1, Object udc2) {

       return null;
    }

    protected static Boolean executeUpdateQuery(List<?> nmSalesList, Object udc1, Object udc2, Object udc3) {

        return null;
    }
}
