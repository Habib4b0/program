package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.bean;

import java.util.Collections;
import java.util.List;

public class SessioBeanForCombobox {

	private static SessioBeanForCombobox sessioBeanForVaadin8 = null;
	private List<String> captionList;
	private List valueList;
	
	private SessioBeanForCombobox(){
		
	}
	
	public static SessioBeanForCombobox getInstance(){
        if(sessioBeanForVaadin8 == null){
        	sessioBeanForVaadin8 = new SessioBeanForCombobox();
        }
        return sessioBeanForVaadin8;
    }

	public List<String> getCaptionList() {
		return captionList == null ? captionList : Collections.unmodifiableList(captionList);
	}

	public void setCaptionList(List<String> captionList) {
		this.captionList = captionList == null ? captionList : Collections.unmodifiableList(captionList);
	}

	public List getValueList() {
		return valueList == null ? valueList : Collections.unmodifiableList(valueList);
	}

	public void setValueList(List valueList) {
		this.valueList = valueList == null ? valueList : Collections.unmodifiableList(valueList);
	}
	
}
