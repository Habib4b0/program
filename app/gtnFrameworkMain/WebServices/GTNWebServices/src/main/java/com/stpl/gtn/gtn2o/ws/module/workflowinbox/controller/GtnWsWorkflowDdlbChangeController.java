package com.stpl.gtn.gtn2o.ws.module.workflowinbox.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.workflow.GtnWsCommonWorkflowResponse;

@RestController
@RequestMapping(value = "/workFlowLookup")
public class GtnWsWorkflowDdlbChangeController {
	public GtnWsWorkflowDdlbChangeController() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger gtnWsLogger = GtnWSLogger.getGTNLogger(GtnWsWorkflowDdlbChangeController.class);

	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/getWfUsersList")
	public GtnUIFrameworkWebserviceResponse deductionLevelChange(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		gtnWsLogger.info("Enter wfUsersList");
		GtnUIFrameworkWebserviceResponse deductionResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsCommonWorkflowResponse wsResonse = new GtnWsCommonWorkflowResponse();
		List<GtnWsRecordBean> resultListBean = new ArrayList<>();
		StringBuilder sqlQuery = new StringBuilder();

		Session getWfUsersListSession = null;
		Connection getWfUsersListConnection = null;
		try {

			getWfUsersListSession = gtnWebServiceAllListConfig.getSysSessionFactory().openSession();

			getWfUsersListConnection = gtnWebServiceAllListConfig.getSysSessionFactory().getSessionFactoryOptions()
					.getServiceRegistry().getService(ConnectionProvider.class).getConnection();
			sqlQuery.append("select lastName,firstName,CONCAT(lastName, ' ', firstName) as fullName,userId from ").append(getWfUsersListConnection.getCatalog()).append(".dbo.User_");
			List<Object[]> resultList = null;

			resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(sqlQuery.toString());

			for (int i = 0; i < resultList.size(); i++) {

				Object[] currentRow = resultList.get(i);
				GtnWsRecordBean bean = new GtnWsRecordBean();

				bean.setRecordHeader(Arrays.asList("lastName", "firstName", "fullName", "userId"));
				currentRow[0] = String.valueOf(currentRow[0]) == null ? "" : String.valueOf(currentRow[0]);
				currentRow[1] = String.valueOf(currentRow[1]) == null ? "" : String.valueOf(currentRow[1]);
				currentRow[2] = String.valueOf(currentRow[2]) == null ? "" : String.valueOf(currentRow[2]);
                                currentRow[3] = String.valueOf(currentRow[3]) == null ? "" : String.valueOf(currentRow[3]);
				bean.setProperties(Arrays.asList(currentRow));
				resultListBean.add(bean);

			}

			wsResonse.setResultList(resultListBean);
			deductionResponse.setGtnWSCommonWorkflowResponse(wsResonse);
			return deductionResponse;
		} catch (Exception exception) {
			gtnWsLogger.error("Exception in loadingComboBoxList() " + exception.getMessage());
			return null;

		} finally {
			if (getWfUsersListSession != null) {
				getWfUsersListSession.close();
			}
			try {
				if (getWfUsersListConnection != null) {
					getWfUsersListConnection.close();
				}
			} catch (SQLException e) {
				gtnWsLogger.error(e.getMessage());
			}
		}
	}

}
