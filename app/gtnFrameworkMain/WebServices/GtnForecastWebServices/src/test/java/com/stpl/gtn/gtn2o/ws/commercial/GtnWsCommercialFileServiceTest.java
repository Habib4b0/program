package com.stpl.gtn.gtn2o.ws.commercial;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.commercial.service.GtnWsCommercialFileService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/applicationServiceContext-CommercialForecasting.xml" })
@Ignore
public class GtnWsCommercialFileServiceTest {

	@Autowired
	GtnWsCommercialFileService commercialFileService;

	@Test
	public void createFilesTest() {
		try {
			GtnForecastBean gtnForecastBean = new GtnForecastBean();
			gtnForecastBean.setModuleName("Commercial");
			gtnForecastBean.setUserId("123");
			gtnForecastBean.setForecastSessionId("321");
			gtnForecastBean.setModuleName("Commercial");
			gtnForecastBean.setTestFilePath("D:/Sibi/Server/bpigtn_dev_ws/jboss-7.1.1/gtnframework_base_path");
			gtnForecastBean.setQueryParameters(getBaseFileQueryParameters());
			gtnForecastBean.setMode("ADD");
			commercialFileService.writeSalesBaseFiles(gtnForecastBean);
		} catch (GtnFrameworkGeneralException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void createCustomerTreeFile() {
		try {
			GtnForecastBean gtnForecastBean = new GtnForecastBean();
			gtnForecastBean.setModuleName("Commercial");
			gtnForecastBean.setUserId("123");
			gtnForecastBean.setForecastSessionId("321");
			gtnForecastBean.setModuleName("Commercial");
			gtnForecastBean.setTestFilePath("D:/Sibi/Server/bpigtn_dev_ws/jboss-7.1.1/gtnframework_base_path");
			gtnForecastBean.setQueryParameters(getCustomerTreeQueryParameters());
			gtnForecastBean.setMode("ADD");

			gtnForecastBean.setRelationshipBuilderSid(144);
			gtnForecastBean.setForecastLevel(2);
			commercialFileService.writeCustomerTreeFile(gtnForecastBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	@Test
	public void createCustomerTreeDataFile() {
		try {
			GtnForecastBean gtnForecastBean = new GtnForecastBean();
			gtnForecastBean.setModuleName("Commercial");
			gtnForecastBean.setUserId("123");
			gtnForecastBean.setForecastSessionId("321");
			gtnForecastBean.setModuleName("Commercial");
			gtnForecastBean.setTestFilePath("D:/Sibi/Server/bpigtn_dev_ws/jboss-7.1.1/gtnframework_base_path");
			gtnForecastBean.setMode("ADD");

			gtnForecastBean.setRelationshipBuilderSid(144);
			gtnForecastBean.setForecastLevel(2);

			gtnForecastBean.setForecastStartDate(new Date(2014 - 1900, 7, 1));
			gtnForecastBean.setForecastEndDate(new Date(2015 - 1900, 8, 30));

			System.out.println("Date::::>>" + gtnForecastBean.getForecastStartDate());
			System.out.println("Date::::>>" + gtnForecastBean.getForecastEndDate());

			commercialFileService.writeSalesCustomerGroupedFile(gtnForecastBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Map<String, List<String>> getBaseFileQueryParameters() {

		Map<String, List<String>> queryParameters = new HashMap<>();
		queryParameters.put("COMMERCIAL_SALES_MASTER_ADD", new ArrayList<String>());
		queryParameters.put("COMMERCIAL_SALES_ACTUAL_ADD", new ArrayList<String>());
		queryParameters.put("COMMERCIAL_SALES_PROJECTION_ADD", new ArrayList<String>());

		return queryParameters;

	}

	private Map<String, List<String>> getCustomerTreeQueryParameters() {

		Map<String, List<String>> queryParameters = new HashMap<>();
		List<String> list = new ArrayList<>();
		list.add("('144-1.1.1.1.'),('144-1.1.2.1.')");
		queryParameters.put("hierarchy_no", list);

		return queryParameters;

	}

}
