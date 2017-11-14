package com.stpl.gtn.gtn2o.ui.framework.action.executor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkClassLoader;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnUIFrameworkActionExecutor {
	private GtnUIFrameworkActionExecutor() {
		/**
		 * empty constructor
		 */
	}

	private static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameworkActionExecutor.class);

	private static final Map<String, GtnUIFrameWorkAction> gtnUIFrameWorkActionMap = new HashMap<>();

	private static long beforeAction(GtnUIFrameWorkActionConfig actionConfig) {
		logger.debug("Start executing action " + actionConfig.getActionType());
		return System.currentTimeMillis();
	}

	private static void afterAction(GtnUIFrameWorkActionConfig actionConfig, long startTime) {
		logger.debug("End executing action " + actionConfig.getActionType() + " Time taken : "
				+ (System.currentTimeMillis() - startTime));
	}

	public static void executeSingleAction(String sourceComponentId, GtnUIFrameWorkActionConfig actionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameWorkAction action = actionConfig.getActionType().getGtnUIFrameWorkAction();

		long startTime = beforeAction(actionConfig);

		action.configureParams(actionConfig);

		executeAction(action, sourceComponentId, actionConfig);

		afterAction(actionConfig, startTime);
	}

	public static void executeActionList(String sourceComponentId, List<GtnUIFrameWorkActionConfig> actionConfigList)
			throws GtnFrameworkGeneralException {

		try {
			for (GtnUIFrameWorkActionConfig actionConfig : actionConfigList) {
				executeSingleAction(sourceComponentId, actionConfig);
			}
		} catch (GtnFrameworkSkipActionException skipException) {
			logger.debug(GtnFrameworkCommonConstants.ACTION_WILL_BE_SKIPPED);
		} catch (GtnFrameworkGeneralException generalException) {
			logger.error("Action Execution exception", generalException);
			throw generalException;
		}

	}

	public static void executeActionClass(String sourceComponentId, String className,
			GtnUIFrameWorkActionConfig actionConfig) throws GtnFrameworkGeneralException {
		try {

			GtnUIFrameWorkAction actionObj = null;
			if (gtnUIFrameWorkActionMap.get(className) == null) {
				GtnUIFrameworkClassLoader classLoader = new GtnUIFrameworkClassLoader();
				actionObj = (GtnUIFrameWorkAction) classLoader.loadDynamicClass(className);
				if (actionObj instanceof GtnUIFrameworkActionShareable) {
					gtnUIFrameWorkActionMap.put(className, actionObj);
				}
			} else {
				actionObj = gtnUIFrameWorkActionMap.get(className);
			}

			long startTime = beforeAction(actionConfig);

			actionObj.configureParams(actionConfig);

			executeAction(actionObj, sourceComponentId, actionConfig);

			afterAction(actionConfig, startTime);
		} catch (GtnFrameworkSkipActionException skipException) {
			logger.debug(GtnFrameworkCommonConstants.ACTION_WILL_BE_SKIPPED);
			throw skipException;
		} catch (GtnFrameworkGeneralException generalException) {
			throw generalException;
		}

	}

	private static void executeAction(GtnUIFrameWorkAction actionObj, String sourceComponentId,
			GtnUIFrameWorkActionConfig actionConfig) throws GtnFrameworkGeneralException {
		try {
			actionObj.doAction(sourceComponentId, actionConfig);
		} catch (GtnFrameworkSkipActionException skipException) {
			logger.debug(GtnFrameworkCommonConstants.ACTION_WILL_BE_SKIPPED);
			throw skipException;
		} catch (GtnFrameworkValidationFailedException validationException) {
			showErrorAsBanner(validationException);
			throw new GtnFrameworkSkipActionException(validationException.getErrorMessage());
		} catch (GtnFrameworkGeneralException generalException) {
			throw generalException;
		}
	}

	private static void showErrorAsBanner(GtnFrameworkValidationFailedException validationException)
			throws GtnFrameworkGeneralException {
		if (validationException.getComponentId() != null && !validationException.getComponentId().isEmpty()) {
			String viewId = getViewId(validationException.getComponentId());
			Object validationMsg = validationException.getMessage();
			if (viewId != null && !viewId.isEmpty()) {
				GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
				alertActionConfig.setActionType(GtnUIFrameworkActionType.SHOW_ERROR_BANNER_ACTION);
				alertActionConfig.setActionParameterList(
						Arrays.asList(viewId + GtnFrameworkCommonStringConstants.ERROR_BANNER, validationMsg));
				executeSingleAction(viewId, alertActionConfig);
			}
		}
	}

	public static void clearErrorBanner(String componentId) throws GtnFrameworkGeneralException {
		showErrorAsBanner(
				new GtnFrameworkValidationFailedException(GtnFrameworkCommonStringConstants.STRING_EMPTY, componentId));
	}

	private static String getViewId(String componentId) {
		if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getComponentData() != null) {
			return GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getComponentData().getViewId();
		}
		return GtnFrameworkCommonStringConstants.STRING_EMPTY;
	}

	public static void executeCustomAction(String sourceComponentId, GtnUIFrameWorkActionConfig actionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameWorkAction customAction = GtnUIFrameworkActionType.CUSTOM_ACTION.getSingletonAction();
		long startTime = beforeAction(actionConfig);
		customAction.configureParams(actionConfig);
		executeAction(customAction, sourceComponentId, actionConfig);
		afterAction(actionConfig, startTime);
	}

}
