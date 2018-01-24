package com.stpl.app.common;

import com.stpl.app.common.dao.CommonDao;
import com.stpl.app.common.dao.impl.CommonImpl;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;

public class AppDataUtils {

    private static final Logger LOGGER = Logger.getLogger(AppDataUtils.class);
    private final static CommonDao ITEMDAO = CommonImpl.getInstance();

    public static List getAppData(List input, String queryName, String queryName2) {
        LOGGER.debug("Inside item get data");
        List list = new ArrayList();
        StringBuilder sql;
        if (queryName != null && !queryName.isEmpty()) {
            try {
                sql = new StringBuilder(SQlUtil.getQuery(queryName));
                if (queryName2 != null && !queryName2.equals(StringUtils.EMPTY)) {
                    sql.append(" ");
                    sql.append(SQlUtil.getQuery(queryName2));
                }
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                list = (List<Object[]>) ITEMDAO.executeSelect(sql.toString());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }

        LOGGER.debug("End of item get Data");
        return list;
    }

}
