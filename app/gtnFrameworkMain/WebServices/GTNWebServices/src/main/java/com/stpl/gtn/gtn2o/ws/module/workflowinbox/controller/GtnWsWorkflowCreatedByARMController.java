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
@RequestMapping(value = "/createdbyarm")

public class GtnWsWorkflowCreatedByARMController {
	public GtnWsWorkflowCreatedByARMController() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsWorkflowCreatedByARMController.class);

	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/getCreatedByArmUsersList")
	public GtnUIFrameworkWebserviceResponse deductionLevelChangeArm(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getCreatedByArmUsersList");
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsCommonWorkflowResponse armUserResonse = new GtnWsCommonWorkflowResponse();
		List<GtnWsRecordBean> resultListBean = new ArrayList<>();
		StringBuilder armQuery = new StringBuilder();
		Session session = null;
		Connection connection = null;
		try {
			session = gtnWebServiceAllListConfig.getSysSessionFactory().openSession();
			connection = gtnWebServiceAllListConfig.getSysSessionFactory().getSessionFactoryOptions()
					.getServiceRegistry().getService(ConnectionProvider.class).getConnection();
			armQuery.append("select firstName+' '+middleName+' '+lastName as userName,firstName,lastName,CONCAT(lastName, ' ', firstName) as fullName from ").append(connection.getCatalog()).append(".dbo.User_");
			List<Object[]> resultList = null;

			resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(armQuery.toString());

			for (int i = 0; i < resultList.size(); i++) {

				Object[] arnUserInfo = resultList.get(i);
				GtnWsRecordBean armUserBean = new GtnWsRecordBean();
				arnUserInfo[0] = String.valueOf(arnUserInfo[0]) == null ? "" : String.valueOf(arnUserInfo[0]);
				arnUserInfo[1] = String.valueOf(arnUserInfo[1]) == null ? "" : String.valueOf(arnUserInfo[1]);
				arnUserInfo[2] = String.valueOf(arnUserInfo[2]) == null ? "" : String.valueOf(arnUserInfo[2]);
				arnUserInfo[3] = String.valueOf(arnUserInfo[3]) == null ? "" : String.valueOf(arnUserInfo[3]);
				armUserBean.setRecordHeader(Arrays.asList("userName", "firstName", "lastName", "fullName"));
				armUserBean.setProperties(Arrays.asList(arnUserInfo));
				resultListBean.add(armUserBean);
			}
			armUserResonse.setResultList(resultListBean);
			response.setGtnWSCommonWorkflowResponse(armUserResonse);
			return response;
		} catch (Exception e) {
			logger.error("Exception in loadAllComboBoxList()----" + e.getMessage());
			return null;

		} finally {
			if (session != null) {
				session.close();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
	}
}