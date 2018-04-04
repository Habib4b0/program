package com.stpl.gtn.gtn2o.ui.framework.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.NotesDTO;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkLoadNotesTabAction implements GtnUIFrameWorkAction {


	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// Empty Method
	}

	/**
	 * input 0 -NotesTabBeanList; input 1 -Internal Notes
	 */
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<NotesDTO> notesDTOList = new ArrayList<>();
		List<Object> resultList = new ArrayList<>();
		TimeZone central = TimeZone.getTimeZone("CST");
		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		@SuppressWarnings("unchecked")
		List<NotesTabBean> notesTabBeanList = (List<NotesTabBean>) gtnUIFrameWorkActionConfig.getActionParameterList()
				.get(0);
		String internalNotes = String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(1));
		NotesDTO attachmentDTO;
		String filePath;
		if (notesTabBeanList != null) {
			for (NotesTabBean notesTabBean : notesTabBeanList) {
				attachmentDTO = new NotesDTO();
				filePath = notesTabBean.getFilePath();
				attachmentDTO.setDocDetailsId(notesTabBean.getMasterTableSystemId());
				attachmentDTO.setDocumentFullPath(filePath);
				attachmentDTO.setDocumentName(filePath);
				format.setTimeZone(central);
				attachmentDTO.setDateAdded(format.format(notesTabBean.getCreatedDate()));
				attachmentDTO.setUserId(notesTabBean.getCreatedBy());
				attachmentDTO.setUserName(String.valueOf(notesTabBean.getCreatedByName()));
				notesDTOList.add(attachmentDTO);

			}
		}
		resultList.add(internalNotes);
		resultList.add(notesDTOList);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab").setNotesTabValue(resultList);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
