package com.stpl.ifs.ui.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jboss.logging.Logger;

import com.stpl.addons.tableexport.TemporaryFileDownloadResource;
import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsCsvExportBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsCsvExportRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;

public class GtnWsCsvExportUtil {

	private GtnWsCsvExportUtil() {
		super();
	}

	private static final Logger LOGGER = Logger.getLogger(GtnWsCsvExportUtil.class);

	public static final String EXCEL_MIME_TYPE = "application/vnd.ms-excel";

	public static String getExportFileName(String exportName, String countQuery, String dataQuery,
			List<String> headerList, String userId, String sessionId) {
		GtnUIFrameworkWebServiceClient wsClient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest wsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCsvExportRequest csvWsRequest = new GtnWsCsvExportRequest();
		GtnWsCsvExportBean csvExportBean = new GtnWsCsvExportBean();
		wsRequest.setGtnWsCsvExportRequest(csvWsRequest);
		csvWsRequest.setGtnWsCsvExportBean(csvExportBean);
		csvExportBean.setExportName(exportName);
		csvExportBean.setCountQuery(countQuery);
		csvExportBean.setDataQuery(dataQuery);
		csvExportBean.setHeaderList(headerList);

		GtnUIFrameworkWebserviceResponse wsResponse = wsClient.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_CSV_EXPORT_FILE_SERVICE, wsRequest,
				getGtnWsSecurityToken(userId, sessionId));
		return wsResponse.getGtnWsCsvExportResponse().getFileName();

	}

	@SuppressWarnings("deprecation")
	public static void sendTheExcelToUser(String exportFileName, String filePath, boolean isWriteFileInWebService,
			String userId, String sessionId) {
		String exportFile = exportFileName;
		TemporaryFileDownloadResource resource;
		File tempFile = GtnFileNameUtils.getFile(filePath);
		try {
			if (Page.getCurrent().getWebBrowser().isFirefox()) {
				exportFile = exportFile.replace(GtnFrameworkCommonStringConstants.SPACE,
						GtnFrameworkCommonStringConstants.UNDERSCORE);
			}

			resource = new TemporaryFileDownloadResource(null,
					exportFile + GtnFrameworkCommonStringConstants.CSV_EXTENSION, EXCEL_MIME_TYPE, tempFile);
			UI.getCurrent().getPage().open(resource, GtnFrameworkCommonStringConstants.UNDERSCORE_BLANK, false);
		} catch (final IOException e) {
			LOGGER.error(exportFile, e);
		} finally {
			deleteTempFile(tempFile.getAbsolutePath(), isWriteFileInWebService, userId, sessionId);
		}
	}

	public static void deleteTempFile(String filepath, boolean isWriteFileInWebService, String userId,
			String sessionId) {
		if (isWriteFileInWebService) {
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();
			GtnWsGeneralRequest request = new GtnWsGeneralRequest();
			request.setExtraParameter(filepath);
			serviceRequest.setGtnWsGeneralRequest(request);
			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
					GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_TEMP_EXCEL_FILE_DELETE,
					serviceRequest, getGtnWsSecurityToken(userId, sessionId));
			GtnWsGeneralResponse generalResponse = response.getGtnWsGeneralResponse();
			if (!generalResponse.isSucess()) {
				LOGGER.error("Error while deleting Temp file---", generalResponse.getGtnGeneralException());
			}
		}

	}

	public static GtnWsSecurityToken getGtnWsSecurityToken(String userId, String sessionId) {
		GtnWsSecurityToken token = new GtnWsSecurityToken();
		token.setUserId(userId);
		token.setSessionId(sessionId);
		return token;

	}

}
