package com.stpl.gtn.gtn2o.querygenerator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;

public class GtnFrameworkQueryGeneratorTest {
	private GtnFrameworkQueryGeneratorBean queryGeneratorConfig;

	@Before
	public void init() {
		queryGeneratorConfig = new GtnFrameworkQueryGeneratorBean();

		queryGeneratorConfig.addSelectClauseBean("MTP.SINGLE_HEADER_VISIBLE_COLUMNS");
		queryGeneratorConfig.addSelectClauseBean("MCM.COMPONENT_DESC");
		queryGeneratorConfig.addSelectClauseBean("MCM.SCREEN_NAME");
		queryGeneratorConfig.addSelectClauseBean("MCRD.IS_VISIBLE");
		queryGeneratorConfig.addSelectClauseBean("MCRD.IS_EDITABLE");
		queryGeneratorConfig.addSelectClauseBean("MCRD.IS_TABLE_PROPERTY");
		queryGeneratorConfig.addSelectClauseBean("MSM.SUBMODULE_NAME");
		queryGeneratorConfig.addSelectClauseBean("MTP.GTN_UITABLE_COMPONENT_DETAILS_SID");
		queryGeneratorConfig.addSelectClauseBean("MCRD.GTN_MODULE_COMPONENT_ROLE_DETAILS_SID");

		GtnFrameworkJoinClauseBean joinClauseBean = queryGeneratorConfig.addJoinClauseBean("MODULE_SUBMODULE_MASTER",
				"MSM", GtnFrameworkJoinType.JOIN);
		joinClauseBean.addConditionBean("MSM.MODULE_SUBMODULE_SID", "MTP.MODULE_SUBMODULE_MASTER_SID",
				GtnFrameworkOperatorType.EQUAL_TO);

		GtnFrameworkJoinClauseBean joinClauseBean1 = queryGeneratorConfig
				.addJoinClauseBean("GTN_MODULE_COMPONENT_MASTER", "MCM", GtnFrameworkJoinType.JOIN);
		joinClauseBean1.addConditionBean("MCM.GTN_MODULE_COMPONENT_MASTER_SID", "MTP.GTN_MODULE_COMPONENT_MASTER_SID",
				GtnFrameworkOperatorType.EQUAL_TO);

		GtnFrameworkJoinClauseBean joinClauseBean2 = queryGeneratorConfig
				.addJoinClauseBean("GTN_MODULE_COMPONENT_ROLE_DETAILS", "MCRD", GtnFrameworkJoinType.JOIN);
		joinClauseBean2.addConditionBean("MCRD.GTN_COMPONENT_SID", "MTP.GTN_UITABLE_COMPONENT_DETAILS_SID",
				GtnFrameworkOperatorType.EQUAL_TO);

		queryGeneratorConfig.addWhereClauseBean("MCRD.IS_TABLE_PROPERTY", null, GtnFrameworkOperatorType.EQUAL_TO,
				GtnFrameworkDataType.INTEGER, 1);
		queryGeneratorConfig.addWhereClauseBean("MSM.SUBMODULE_NAME", null, GtnFrameworkOperatorType.EQUAL_TO,
				GtnFrameworkDataType.STRING, "Company Master");

		queryGeneratorConfig.setFromTableNameWithAlies("GTN_UITABLE_COMPONENT_DETAILS MTP");
	}

	@Test
	public void generateQuery() {
		String finalQuery = queryGeneratorConfig.generateQuery();
		
		Assert.assertEquals(true, finalQuery.length() != 0);
	}

}
