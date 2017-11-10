package com.stpl.gtn.gtn2o.ui.framework.action.validation;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnUIFrameworkCustomValidator implements GtnUIFrameworkValidator {

	@Override
	public void validate(String componentId, String fieldId,
			GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig)
			throws GtnFrameworkValidationFailedException {
		if (gtnUIFrameworkValidationConfig != null
				&& gtnUIFrameworkValidationConfig.getCustomServiceValidations() != null) {
			callService(gtnUIFrameworkValidationConfig);
		}

	}

	private void callService(GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig)
			throws GtnFrameworkValidationFailedException {
		for (String url : gtnUIFrameworkValidationConfig.getCustomServiceValidations()) {

			callClient(url);

		}
	}

	private void callClient(String url) throws GtnFrameworkValidationFailedException {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(url, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		if (response.getGtnSerachResponse().getCount() == 0) {
			return;
		}
		throw new GtnFrameworkValidationFailedException("Custom Vaidation Failed");
	}

	@Override
	public GtnUIFrameworkValidator createInstance() {
		return new GtnUIFrameworkCustomValidator();
	}

}
