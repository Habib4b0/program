package com.stpl.app.accountclose.bpm.persistance.provider;

import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import java.util.List;


/**
 *
 * @author arulmurugan
 */
public class BasePersistanceProvider {

    @SuppressWarnings("unchecked")
    protected static List<Object[]> executeSelectQuery(String customQuery, Object udc1, Object udc2) {
        return (List<Object[]>) CompanyMasterLocalServiceUtil.executeSelectQuery(customQuery, null, null);
    }

    protected static int executeUpdateQuery(String customQuery) {
        return (int) CompanyMasterLocalServiceUtil.executeUpdateQuery(customQuery);
    }
}
