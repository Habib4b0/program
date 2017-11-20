package com.stpl.gtn.gtn2o.ws.forecast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsFileIOService;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsRowCheckIOService;
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnWsTreeNode;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;
import com.stpl.gtn.gtn2o.ws.response.pagetreetable.GtnWsPagedTreeTableResponse;

@RestController
public class GtnWsReturnsRowCheckController {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsReturnsRowCheckController.class);

	@Autowired
	private GtnWsReturnsFileIOService fileService;

	@Autowired
	private GtnWsReturnsRowCheckIOService gtnWsReturnsRowCheckIOService;

	String basePath = "";
	String finalFileName = "";

	public GtnWsReturnsFileIOService getFileService() {
		return fileService;
	}

	public void setFileService(GtnWsReturnsFileIOService fileService) {
		this.fileService = fileService;
	}

	public GtnWsReturnsRowCheckIOService getGtnWsReturnsRowCheckIOService() {
		return gtnWsReturnsRowCheckIOService;
	}

	public void setGtnWsReturnsRowCheckIOService(GtnWsReturnsRowCheckIOService gtnWsReturnsRowCheckIOService) {
		this.gtnWsReturnsRowCheckIOService = gtnWsReturnsRowCheckIOService;
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_UPDATE_CHECKBOX_SERVICE)
	public GtnUIFrameworkWebserviceResponse updateCheckInFile(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsForecastRequest request = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
			GtnForecastBean gtnForecastBean = request.getGtnForecastBean();

			basePath = fileService.getFilePath(gtnForecastBean.getModuleName(), gtnForecastBean.getUserId(),
					gtnForecastBean.getForecastSessionId(), gtnForecastBean.getTestFilePath());

			finalFileName = basePath + fileService.getTreeFileName(gtnForecastBean.getHierarchyType(), null);
			GtnWsTreeNode rootNode = fileService.readDataFromFile(finalFileName, GtnWsTreeNode.class);

			boolean isAllRecordsChecked;

			if (gtnForecastBean.isCheckAllFlag()) {
				isAllRecordsChecked = gtnWsReturnsRowCheckIOService.updateAllRecords(rootNode, gtnForecastBean,
						finalFileName);
			} else {
				isAllRecordsChecked = gtnWsReturnsRowCheckIOService.updateTreeNode(rootNode, gtnForecastBean,
						finalFileName);
			}

			GtnWsPagedTreeTableResponse gtnWsPagedTreeTableResponse = new GtnWsPagedTreeTableResponse();
			gtnWsPagedTreeTableResponse.setCheckAll(isAllRecordsChecked);
			gtnUIFrameworkWebserviceResponse.setGtnWSPagedTreeTableResponse(gtnWsPagedTreeTableResponse);

		} catch (GtnFrameworkGeneralException ex) {
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			LOGGER.error("Exception while Excuting writeFile", ex);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		} finally {
			LOGGER.info("Exit updateCheckInFile");
		}
		return gtnUIFrameworkWebserviceResponse;
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_READ_CHECKBOX_SERVICE)
	public GtnUIFrameworkWebserviceResponse readCheckedNodesFromFile(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		try {
			response.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			response.getGtnWsGeneralResponse().setSucess(true);
			GtnWsForecastResponse gtnWsForecastResponse = new GtnWsForecastResponse();

			GtnWsForecastRequest request = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest();

			GtnForecastBean gtnForecastBean = request.getGtnForecastBean();

			basePath = fileService.getFilePath(gtnForecastBean.getModuleName(), gtnForecastBean.getUserId(),
					gtnForecastBean.getForecastSessionId(), gtnForecastBean.getTestFilePath());
			finalFileName = basePath + fileService.getTreeFileName(gtnForecastBean.getHierarchyType(), null);
			GtnWsTreeNode rootNode = fileService.readDataFromFile(finalFileName, GtnWsTreeNode.class);

			gtnForecastBean.setCheckedLeftTreeTable(
					gtnWsReturnsRowCheckIOService.getCheckedNodeForMethodologyValidation(rootNode, gtnForecastBean));

			gtnWsForecastResponse.setGtnForecastBean(gtnForecastBean);

			response.setGtnWsForecastResponse(gtnWsForecastResponse);
		} catch (GtnFrameworkGeneralException ex) {
			response.getGtnWsGeneralResponse().setSucess(false);
			LOGGER.error("Exception in readCheckedNodesFromFile method", ex);
			response.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return response;
		} finally {
			LOGGER.info("Exit writeFile");
		}
		return response;
	}

}
