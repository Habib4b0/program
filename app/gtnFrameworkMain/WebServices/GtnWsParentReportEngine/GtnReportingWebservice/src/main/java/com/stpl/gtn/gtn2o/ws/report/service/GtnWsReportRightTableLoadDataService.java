package com.stpl.gtn.gtn2o.ws.report.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.service.transform.GtnWsReportRightTableResultTransformer;

@Service
public class GtnWsReportRightTableLoadDataService {
	@Autowired
	GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
	@Autowired
	GtnWsReportRightTableResultTransformer transFormer;

	public void getDataFromBackend() {
		try {
			String query = getQueryFromProcedure();
			List<?> object = gtnSqlQueryEngine.executeSelectQuery(query, new Object[] {}, new GtnFrameworkDataType[] {},
					transFormer);
			System.out.println(object.get(0));

		} catch (GtnFrameworkGeneralException e) {
			e.printStackTrace();
		}

	}

	public String getQueryFromProcedure() throws GtnFrameworkGeneralException {
		List<?> outputFromProcedure = gtnSqlQueryEngine.executeProcedure("getByName ?", Arrays.asList("Name"));
		System.out.println(outputFromProcedure);
		return (String) ((Map<?, ?>) outputFromProcedure.get(0)).entrySet().iterator().next().getValue();
	}
}
