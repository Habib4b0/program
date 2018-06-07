package com.stpl.gtn.gtn2o.ui.framework.component.button;

public class GtnUIFrameworkButtonConfig {

	private GtnUiFrameworkButtonType buttonType = GtnUiFrameworkButtonType.BUTTON;
	private boolean isButtonCaptionInUpperCase = true;
	private String iconUrl;

	public GtnUiFrameworkButtonType getButtonType() {
		return buttonType;
	}

	public void setButtonType(GtnUiFrameworkButtonType buttonType) {
		this.buttonType = buttonType;
	}

	public boolean isButtonCaptionInUpperCase() {
		return isButtonCaptionInUpperCase;
	}

	/**
	 * Set true If u want to display Button Caption in Upper Case
	 * 
	 * @param isButtonCaptionInUpperCase
	 */
	public void setButtonCaptionInUpperCase(boolean isButtonCaptionInUpperCase) {
		this.isButtonCaptionInUpperCase = isButtonCaptionInUpperCase;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

}
