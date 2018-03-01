package com.stpl.app.common;

import com.stpl.app.common.dao.CommonDao;
import com.stpl.app.common.dao.impl.CommonImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppDataUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppDataUtils.class);
    private static final CommonDao ITEMDAO = CommonImpl.getInstance();

    public static List getAppData(List input, String queryName, String queryName2) {
        LOGGER.debug("Inside item get data");
        List list = new ArrayList();
        StringBuilder sql;
        if (queryName != null && !queryName.isEmpty()) {
            try {
                sql = new StringBuilder(SQlUtil.getQuery(queryName));
                if (queryName2 != null && !queryName2.equals(StringUtils.EMPTY)) {
                    sql.append(' ');
                    sql.append(SQlUtil.getQuery(queryName2));
                }
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                list = (List<Object[]>) ITEMDAO.executeSelect(sql.toString());
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }

        LOGGER.debug("End of item get Data");
        return list;
    }
    public static String getValueForKeyFromProperty(String string) {
		FileInputStream fileInput = null;
		Properties properties = new Properties();
		String key=string;
		String valueForKey=null;
		File file = new File("");
		try (InputStream inputStream=AppDataUtils.class.getResourceAsStream("/properties/alertmessage.properties");){
			FileUtils.copyInputStreamToFile(inputStream, file);
			fileInput = new FileInputStream(file);
			properties.load(fileInput);
			valueForKey = properties.getProperty(key);
		} catch (IOException e1) {
			LOGGER.error("Exception:"+e1.getMessage());
		}
		finally{
			try {
				if(fileInput!=null)
				{
				fileInput.close();
				}
			} catch (IOException exception) {
				LOGGER.error("Exception Occurred:"+exception.getMessage());
			}
		}
		return valueForKey;
	}
}
