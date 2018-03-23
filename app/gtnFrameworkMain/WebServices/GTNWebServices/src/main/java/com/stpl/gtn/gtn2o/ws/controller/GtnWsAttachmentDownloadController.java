package com.stpl.gtn.gtn2o.ws.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsAttachmentBean;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsAttachmentResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnAttachmentDownloadService;

@RestController
@RequestMapping()
public class GtnWsAttachmentDownloadController {
	public GtnWsAttachmentDownloadController() {
		super();
	}

	@Autowired
	private GtnAttachmentDownloadService gtnAttachmentDownloadService;

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsCsvExportController.class);

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DOWNLOAD_FILE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getDownloadFile(
			@RequestBody GtnUIFrameworkWebserviceRequest attachmentDownloadRequest) {

		GtnUIFrameworkWebserviceResponse attachmentDownloadFileResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsAttachmentBean attachmentBean;
		GtnWsAttachmentResponse  gtnWsAttachmentResponse=new GtnWsAttachmentResponse();
		int attachmentDetailsSid=attachmentDownloadRequest.getGtnWsAttachmentRequest().getDocDetailsSid();
		try {
		  attachmentBean = gtnAttachmentDownloadService.getDownloadedFile(attachmentDetailsSid);
		 gtnWsAttachmentResponse.setGtnWsAttachmentBean(attachmentBean);
		 attachmentDownloadFileResponse.setGtnWsAttachmentResponse(gtnWsAttachmentResponse);
		} catch (GtnFrameworkGeneralException ex) {
			LOGGER.error("Exception in GTN_ATTACHMENT_DOWNLOAD_SERVICE", ex);
		}

		return attachmentDownloadFileResponse;
	}
}
