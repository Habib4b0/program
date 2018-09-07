package com.stpl.app.cff.bpm.persistance.provider;

import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author arulmurugan
 */
public class BasePersistanceProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasePersistanceProvider.class);
    protected BasePersistanceProvider()
    {
        LOGGER.debug("BasePersistanceProvider");
    }

    @SuppressWarnings("unchecked")
    protected static List<Object[]> executeSelectQuery(String customQuery) {

        return HelperTableLocalServiceUtil.executeSelectQuery(customQuery);
    }

    protected static Boolean executeBulkUpdateQuery(String customQuery) {
        int count = HelperTableLocalServiceUtil.executeUpdateQueryCount(customQuery);
        return count > 0;
    }
}
