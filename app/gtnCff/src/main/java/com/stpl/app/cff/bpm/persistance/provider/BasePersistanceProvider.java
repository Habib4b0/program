package com.stpl.app.cff.bpm.persistance.provider;

import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.List;


/**
 *
 * @author arulmurugan
 */
public class BasePersistanceProvider {

    @SuppressWarnings("unchecked")
    protected static List<Object[]> executeSelectQuery(String customQuery, Object udc1, Object udc2) {

        return HelperTableLocalServiceUtil.executeSelectQuery(customQuery);
    }

    protected static Boolean executeBulkUpdateQuery(String customQuery, Object udc1, Object udc2) {
        int count = HelperTableLocalServiceUtil.executeUpdateQueryCount(customQuery);
        return count > 0;
    }
}
