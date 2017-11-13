/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.queryengine.engine;

import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Abishek.Ram
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:GtnFrameworkQueryEngine-test.xml"})
public class GtnFrameworkSqlQueryEngineTest {

    @Autowired
   private  GtnFrameworkSqlQueryEngine frameworkSqlQueryEngine;

    public GtnFrameworkSqlQueryEngineTest() {
        super();
    }

    @Test
    public void testSelectQuery() throws GtnFrameworkGeneralException {
        List<?> selectData = frameworkSqlQueryEngine.executeSelectQuery("select * from HELPER_TABLE");
        System.out.println("List size--- " + selectData.size());
        Assert.assertEquals(true, selectData.size() > 0);
    }

    @Ignore
    public void testSelectQueryWithParam() throws GtnFrameworkGeneralException {
        List<?> selectData = frameworkSqlQueryEngine.executeSelectQuery("select * from HELPER_TABLE WHERE HELPER_TABLE_SID > ? ", new Object[]{1}, new GtnFrameworkDataType[]{GtnFrameworkDataType.INTEGER});
        System.out.println("List size--- " + selectData.size());
        Assert.assertEquals(true, selectData.size() > 0);
    }

    @Ignore
    public void testinsertOrUpdate() throws GtnFrameworkGeneralException {
        String randasom = "Junit" + RandomStringUtils.randomAlphanumeric(3);
        int selectData = frameworkSqlQueryEngine.executeInsertOrUpdateQuery("INSERT INTO COMPANY_MASTER (COMPANY_ID, COMPANY_NO, COMPANY_NAME, COMPANY_TYPE, COMPANY_STATUS, COMPANY_CATEGORY, COMPANY_GROUP, COMPANY_START_DATE, COMPANY_END_DATE, ORGANIZATION_KEY, LIVES, FINANCIAL_SYSTEM, ADDRESS1, ADDRESS2, CITY, \"STATE\", ZIP_CODE, COUNTRY, REGION_CODE, LAST_UPDATED_DATE, INTERNAL_NOTES, INBOUND_STATUS, RECORD_LOCK_STATUS, BATCH_ID, \"SOURCE\", CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE) \n"
                + "	VALUES ( '" + randasom + "dev67', '" + randasom + "dev67', 'dev67', 2046, 1877, 0, 0, '2017-01-03 00:00:00.0', NULL, 0, 0, '', '', '', '', 0, '', 0, '', NULL, '', 'C', 1, '', '', 767497, '2017-01-03 07:46:19.507', 767497, '2017-01-03 08:04:51.127');\n"
                + "");
        System.out.println("Changed row size--- " + selectData);
        Assert.assertEquals(true, selectData > 0);
    }

    @Ignore
    public void testinsertOrUpdateWithParams() throws GtnFrameworkGeneralException {
        String randasom = "Junit" + RandomStringUtils.randomAlphanumeric(3);
        int selectData = frameworkSqlQueryEngine.executeInsertOrUpdateQuery("INSERT INTO COMPANY_MASTER (COMPANY_ID, COMPANY_NO, COMPANY_NAME, COMPANY_TYPE, COMPANY_STATUS, COMPANY_CATEGORY, COMPANY_GROUP, COMPANY_START_DATE, COMPANY_END_DATE, ORGANIZATION_KEY, LIVES, FINANCIAL_SYSTEM, ADDRESS1, ADDRESS2, CITY, \"STATE\", ZIP_CODE, COUNTRY, REGION_CODE, LAST_UPDATED_DATE, INTERNAL_NOTES, INBOUND_STATUS, RECORD_LOCK_STATUS, BATCH_ID, \"SOURCE\", CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE) \n"
                + "	VALUES ( ?,?, 'dev67', 2046, 1877, 0, 0, '2017-01-03 00:00:00.0', NULL, 0, 0, '', '', '', '', 0, '', 0, '', NULL, '', 'C', 1, '', '', 767497, '2017-01-03 07:46:19.507', 767497, '2017-01-03 08:04:51.127');\n"
                + "", new Object[]{randasom + "dev67", randasom + "dev67"}, new GtnFrameworkDataType[]{GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING});
        System.out.println("Changed row Size --- " + selectData);
        Assert.assertEquals(true, selectData > 0);
    }

}
