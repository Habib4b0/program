package com.stpl.gtn.gtn2o.ws.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomViewBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;

@Service
public class GtnWsReportCustomViewService {

	@Autowired
	private GtnWsReportSqlService sqlStringService;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnWsReportCustomViewService.class);

	public GtnWsReportCustomViewService() {
		super();
	}

	public GtnUIFrameworkDataTable loadHierarchy(GtnUIFrameworkWebserviceRequest gtnWsRequestF)
			throws GtnFrameworkGeneralException {
		GtnWsReportRequest request = gtnWsRequestF.getGtnWsReportRequest();
		return getHierarachyLevels(gtnWsRequestF.getGtnWsReportRequest().getReportBean().getDataSelectionBean(),
				request.getReportBean().getCustomViewBean());
	}

	public GtnUIFrameworkDataTable loadDeductionHierarchy(GtnUIFrameworkWebserviceRequest gtnWsRequestF)
			throws GtnFrameworkGeneralException {
		GtnWsReportRequest request = gtnWsRequestF.getGtnWsReportRequest();
		return getDeductionHierarachyLevels(
				gtnWsRequestF.getGtnWsReportRequest().getReportBean().getDataSelectionBean(),
				request.getReportBean().getCustomViewBean());
	}

	private GtnUIFrameworkDataTable getHierarachyLevels(GtnWsReportDataSelectionBean bean,
			GtnWsReportCustomViewBean gtnWsReportCustomViewBean) throws GtnFrameworkGeneralException {
		double hierarchySid;
		int levelNo;
		if (gtnWsReportCustomViewBean.getHierarchyType().equals(GtnWsHierarchyType.CUSTOMER)) {
			hierarchySid = bean.getCustomerRelationshipBuilderSid();
			levelNo = bean.getCustomerHierarchyForecastLevel();
		} else {
			hierarchySid = bean.getProductRelationshipBuilderSid();
			levelNo = bean.getProductHierarchyForecastLevel();
		}
		gtnLogger.debug(sqlStringService.getQuery("getHierarchyLevels"));
		List<?> hierarchyData = gtnSqlQueryEngine.executeSelectQuery(sqlStringService.getQuery("getHierarchyLevels"),
				new Object[] { gtnWsReportCustomViewBean.getHierarchyType().toString(), hierarchySid, levelNo },
				new GtnFrameworkDataType[] { GtnFrameworkDataType.STRING, GtnFrameworkDataType.DOUBLE,
						GtnFrameworkDataType.INTEGER });
		GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
		gtnUIFrameworkDataTable.addData((List<Object[]>) hierarchyData);
		return gtnUIFrameworkDataTable;
	}

	@SuppressWarnings("unused")
	private GtnUIFrameworkDataTable getDeductionHierarachyLevels(GtnWsReportDataSelectionBean bean,
			GtnWsReportCustomViewBean gtnWsReportCustomViewBean) throws GtnFrameworkGeneralException {
		double productRelationSid = bean.getProductRelationshipBuilderSid();
		gtnLogger.debug(sqlStringService.getQuery("getDeductionHierarchyLevels"));
		List<?> hierarchyData = gtnSqlQueryEngine.executeSelectQuery(
				sqlStringService.getQuery("getDeductionHierarchyLevels"),
				new Object[] { gtnWsReportCustomViewBean.getHierarchyType().toString(), productRelationSid },
				new GtnFrameworkDataType[] { GtnFrameworkDataType.STRING, GtnFrameworkDataType.DOUBLE });
		GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
		gtnUIFrameworkDataTable.addData((List<Object[]>) hierarchyData);
		return gtnUIFrameworkDataTable;
	}

}
