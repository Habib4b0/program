package com.stpl.gtn.gtn2o.ws.report.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsQueryConstants;

@Service
@Scope(value = "singleton")
public class GtnReportVariableDescriptionIndicatorService {
	private static final GtnWSLogger GTNLOGGER = GtnWSLogger
			.getGTNLogger(GtnReportVariableDescriptionIndicatorService.class);
	private String[] indicatorStringData = new String[14];

	public GtnReportVariableDescriptionIndicatorService() {
		super();
	}

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@PostConstruct
	public void loadVariableDescription() {

		try {
			loadIndicator((List<Object[]>) gtnSqlQueryEngine
					.executeSelectQuery(GtnWsQueryConstants.HIERARCHY_INDICATOR_QUERY));
		} catch (GtnFrameworkGeneralException e) {
			GTNLOGGER.error("Exeception in loading Variable dscription", e);
		}

	}

	private void loadIndicator(List<Object[]> varaibleIndicatorData) {
		for (Object[] hierarchyData : varaibleIndicatorData) {
			char data = String.valueOf(hierarchyData[1]).charAt(0);
			indicatorStringData[data - 65] = String.valueOf(hierarchyData[0]);
		}
	}

	public String getVariable(char variableIndicator) {
		int index = variableIndicator - 65;
		return indicatorStringData[index];
	}
}
