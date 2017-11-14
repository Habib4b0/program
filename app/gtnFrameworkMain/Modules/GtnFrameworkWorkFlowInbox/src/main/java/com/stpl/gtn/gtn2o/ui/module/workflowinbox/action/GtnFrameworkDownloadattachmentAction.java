package com.stpl.gtn.gtn2o.ui.module.workflowinbox.action;

import java.io.File;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxClassConstants;
import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;

public class GtnFrameworkDownloadattachmentAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkDownloadattachmentAction.class);
	protected final FileDownloader fileDownloader = new FileDownloader(new FileResource(new File("tst")));
	private String userId = GtnUIFrameworkGlobalUI.getCurrentUser();

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsRecordBean gtnWsRecordBean = gtnUIFrameWorkActionConfig.getActionParameter().getItemId();
		try {

			System.getProperty(GtnFrameworkCommonStringConstants.GTN_BASE_PATH);

			String fileUploadPath = GtnFrameworkPropertyManager.getProperty("gtn.fileattach")
					+ GtnUIFrameworkGlobalUI.getCurrentSessionBean()
							.getSessionProperty(GtnFrameworkCommonStringConstants.MODULE_NAME)
					+ GtnFrameworkWorkflowInboxClassConstants.EMPTY + userId
					+ GtnFrameworkWorkflowInboxClassConstants.SLASH;

			String documentName = String.valueOf(gtnWsRecordBean.getPropertyValue("documentName"));
			String fileType = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(3));
			File uploadedFile = GtnFileNameUtils.getFile(fileUploadPath + documentName + "." + fileType);

			Resource res = new FileResource(uploadedFile);
			fileDownloader.setFileDownloadResource(res);
			downloadFile(uploadedFile);

		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException(
					"Error in GtnFrameworkFetchHistorytoAttachmentAction class doAction method", ex);
		}
	}

	@SuppressWarnings("deprecation")
	public void downloadFile(File uploadedFile) {
		try {

			if (uploadedFile.exists()) {

				Resource res = new FileResource(uploadedFile);

				Page.getCurrent().open(res, "_blank", true);

			}
		} catch (Exception ex) {
			gtnLogger.error(ex.getMessage(), ex);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
