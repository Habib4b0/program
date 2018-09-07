package com.stpl.gtn.gtn2o.ws.report.service.displayformat.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.service.displayformat.bean.GtnFrameworkDisplayFormatBean;

public class GtnDisplayFormatMasterBean {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnDisplayFormatMasterBean.class);

	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	private final List<GtnFrameworkDisplayFormatBean> displayFormatList = new ArrayList<>();

	public void addDisplayFormatList(GtnFrameworkDisplayFormatBean format) {
		displayFormatList.add(format);
	}

	public GtnDisplayFormatMasterBean() {
		super();
	}

	public void init() {
		String query = " SELECT TABLE_NAME, COLUMN_NAME, SELECTED_COLUMN_NAME FROM dbo.HIERARCHY_DISPLAY_FORMAT ";
		List<Object[]> results;
		try {
			results = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(query);
			for (Object[] format : results) {
				GtnFrameworkDisplayFormatBean formatBean = new GtnFrameworkDisplayFormatBean();
				formatBean.setTableName(format[0].toString());
				formatBean.setColumnName(format[1].toString());
				formatBean.setSelectedColumnName(format[2].toString());
				addDisplayFormatList(formatBean);
			}
		} catch (GtnFrameworkGeneralException e) {
			gtnLogger.error(e.getErrorMessage(), e);
		}
	}

	public List<GtnFrameworkDisplayFormatBean> getDisplayFormatList() {
		return Collections.unmodifiableList(displayFormatList);
	}
}
