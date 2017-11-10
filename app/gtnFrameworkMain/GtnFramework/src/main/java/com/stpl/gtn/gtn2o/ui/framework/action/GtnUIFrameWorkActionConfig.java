package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;

public class GtnUIFrameWorkActionConfig {

	private GtnUIFrameworkActionType actionType;

	private List<String> fieldValues;

	private List<String> fieldDescription;

	private List<Object> actionParameterList;

	private List<String> remoteComponentIdList;

	private Object eventParameter;

	private GtnUIFrameworkActionParameter actionParameter;

	private GtnUIFrameworkComponentConfig componentConfig;

	private List<GtnUIFrameWorkActionConfig> childActionList;

	/**
	 * Initializes type of action to be Executed
	 * 
	 * @param actionType
	 */
	public GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType actionType) {
		this.actionType = actionType;
	}

	public GtnUIFrameWorkActionConfig() {

	}

	public void addActionParameter(Object actionParameter) {
		if (actionParameterList == null) {
			actionParameterList = new ArrayList<>();
		}
		actionParameterList.add(actionParameter);
	}

	public void removeActionParameter(Object actionParameter) {
		if (actionParameterList != null) {
			actionParameterList.remove(actionParameter);
		}
	}

	public List<Object> getActionParameterList() {
		return actionParameterList == null ? actionParameterList : Collections.unmodifiableList(actionParameterList);
	}

	public void setActionParameterList(List<Object> actionParametersList) {
		this.actionParameterList = actionParametersList == null ? actionParametersList
				: new ArrayList<>(actionParametersList);
	}

	public GtnUIFrameworkActionType getActionType() {
		return actionType;
	}

	public void setActionType(GtnUIFrameworkActionType actionType) {
		this.actionType = actionType;
	}

	public List<String> getFieldValues() {
		return fieldValues == null ? fieldValues : Collections.unmodifiableList(fieldValues);
	}

	public void setFieldValues(List<String> fieldValues) {
		this.fieldValues = fieldValues != null ? new ArrayList<>(fieldValues) : fieldValues;
	}

	public Object getEventParameter() {
		return eventParameter;
	}

	public void setEventParameter(Object eventParameter) {
		this.eventParameter = eventParameter;
	}

	public GtnUIFrameworkActionParameter getActionParameter() {
		return actionParameter;
	}

	public void setActionParameter(GtnUIFrameworkActionParameter actionParameter) {
		this.actionParameter = actionParameter;
	}

	public List<String> getFieldDescription() {
		return fieldDescription == null ? fieldDescription : Collections.unmodifiableList(fieldDescription);
	}

	public void setFieldDescription(List<String> fieldDescription) {
		this.fieldDescription = fieldDescription != null ? new ArrayList<>(fieldDescription) : fieldDescription;
	}

	public List<String> getRemoteComponentIdList() {
		return remoteComponentIdList == null ? remoteComponentIdList
				: Collections.unmodifiableList(remoteComponentIdList);
	}

	public void setRemoteComponentIdList(List<String> remoteComponentIdList) {
		this.remoteComponentIdList = remoteComponentIdList == null ? remoteComponentIdList
				: new ArrayList<>(remoteComponentIdList);
	}

	public List<GtnUIFrameWorkActionConfig> getChildActionList() {
		return childActionList == null ? childActionList : Collections.unmodifiableList(childActionList);
	}

	public void setChildActionList(List<GtnUIFrameWorkActionConfig> childActionList) {
		this.childActionList = childActionList == null ? childActionList : new ArrayList<>(childActionList);
	}

	public GtnUIFrameworkComponentConfig getComponentConfig() {
		return componentConfig;
	}

	public void setComponentConfig(GtnUIFrameworkComponentConfig componentConfig) {
		this.componentConfig = componentConfig;
	}

}
