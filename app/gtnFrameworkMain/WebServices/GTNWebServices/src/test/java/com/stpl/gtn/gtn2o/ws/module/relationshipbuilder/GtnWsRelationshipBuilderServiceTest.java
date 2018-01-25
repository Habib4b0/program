package com.stpl.gtn.gtn2o.ws.module.relationshipbuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderConstants;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsRelationshipBuilderServiceTest {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnWsRelationshipBuilderServiceTest.class);
	@Autowired
	GtnWsRelationshipBuilderService service;

	@Test
	public void loadAutoBuildData() {
		try {
			System.setProperty("com.stpl.gtnframework.base.path", "D:/SriThAr/Allergan/Server");
			List<String> hiddenIdList = new ArrayList<>();
			hiddenIdList.add("");
			hiddenIdList.add("204");
			hiddenIdList.add("1091");
			hiddenIdList.add("199");
			hiddenIdList.add("222");
			hiddenIdList.add("192");
			// hiddenIdList.add("113");

			GtnWsRecordBean dto = new GtnWsRecordBean();
			Object value = GtnWsRelationshipBuilderConstants.VALUE_PROPERTY_ID;
			dto.setRecordHeader(Arrays.asList(value));
			List<Object> properties = Arrays.<Object>asList("", "5", "", "", "", "", "", hiddenIdList, "", "",
					false);
			dto.setProperties(properties);
			service.loadAutoBuildData(33, 1, dto, hiddenIdList.subList(1, hiddenIdList.size()));
		} catch (Exception e) {
			
			gtnLogger.error("Exception in loadFilteredResultLayout", e);
		}

	}
}
