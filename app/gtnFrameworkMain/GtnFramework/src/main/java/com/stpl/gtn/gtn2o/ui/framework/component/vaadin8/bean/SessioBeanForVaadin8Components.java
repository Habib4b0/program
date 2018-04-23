package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.bean;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class SessioBeanForVaadin8Components {

	protected static SessioBeanForVaadin8Components sessioBeanForVaadin8 = null;
	private List<String> captionList;
	private List valueList;
	private LocalDateTime startDateForFilterGrid;
	private LocalDateTime endDateForFilterGrid;
	
	private SessioBeanForVaadin8Components(){
		
	}
	
	public static synchronized SessioBeanForVaadin8Components getInstance(){
        if(sessioBeanForVaadin8 != null){
        	 return sessioBeanForVaadin8;
        }
        sessioBeanForVaadin8 = new SessioBeanForVaadin8Components();
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

	public LocalDateTime getEndDateForFilterGrid() {
		return endDateForFilterGrid;
	}

	public void setEndDateForFilterGrid(LocalDateTime endDateForFilterGrid) {
		this.endDateForFilterGrid = endDateForFilterGrid;
	}
	
	public LocalDateTime getStartDateForFilterGrid() {
		return startDateForFilterGrid;
	}

	public void setStartDateForFilterGrid(LocalDateTime startDateForFilterGrid) {
		this.startDateForFilterGrid = startDateForFilterGrid;
	}

}
