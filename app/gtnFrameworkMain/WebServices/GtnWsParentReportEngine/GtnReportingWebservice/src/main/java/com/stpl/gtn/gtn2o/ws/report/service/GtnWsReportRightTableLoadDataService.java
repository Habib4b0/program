package com.stpl.gtn.gtn2o.ws.report.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
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
	// GtnWsReportVaribleRowResultTransformer transFormer;
	GtnWsReportRightTableResultTransformer transFormer;

	public Map<String, Map<String, Double>> getDataFromBackend() {
		try {
			String query = getQueryFromProcedure();
			List<?> object = gtnSqlQueryEngine.executeSelectQuery(query, new Object[] {}, new GtnFrameworkDataType[] {},
					transFormer);
			return (Map<String, Map<String, Double>>) object.get(0);
		} catch (GtnFrameworkGeneralException e) {
			e.printStackTrace();
		}

		return Collections.emptyMap();

	}

	public String getQueryFromProcedure() throws GtnFrameworkGeneralException {
		List<?> outputFromProcedure = gtnSqlQueryEngine.executeProcedure("getByName ?", Arrays.asList("Name"));
		System.out.println(outputFromProcedure);
		return (String) outputFromProcedure.get(0);
	}
}
