package com.stpl.gtn.gtn2o.ui.framework.component.label;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;

public class GtnUIFrameworkLabelConfig {

	private boolean enable = true;
	private boolean immediate = true;
	private boolean readOnly = false;
	private List<GtnUIFrameWorkActionConfig> valueChangeActionConfigList = new ArrayList<>();

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public void addValueChangeActionConfig(GtnUIFrameWorkActionConfig actionConfig) {
		if (this.valueChangeActionConfigList == null) {
			this.valueChangeActionConfigList = new ArrayList<>();
		}
		this.valueChangeActionConfigList.add(actionConfig);
	}

	public boolean isImmediate() {
		return immediate;
	}

	public void setImmediate(boolean immediate) {
		this.immediate = immediate;
	}

	public List<GtnUIFrameWorkActionConfig> getValueChangeActionConfigList() {
		return valueChangeActionConfigList == null ? valueChangeActionConfigList
				: Collections.unmodifiableList(valueChangeActionConfigList);
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public void setValueChangeActionConfigList(List<GtnUIFrameWorkActionConfig> valueChangeActionConfigList) {
		this.valueChangeActionConfigList = new ArrayList<>(valueChangeActionConfigList);
	}

}