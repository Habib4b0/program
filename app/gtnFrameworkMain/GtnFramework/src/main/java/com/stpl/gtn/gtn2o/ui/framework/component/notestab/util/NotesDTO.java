package com.stpl.gtn.gtn2o.ui.framework.component.notestab.util;

import java.io.Serializable;

public class NotesDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int docDetailsId=0;
	private String documentFullPath=null;
	private String documentName=null;
	private String dateAdded=null;
	private String userName=null;
	private String notesHistory=null;
	private int userId=0;
	private String fileType=null;
	private String reasonCode=null;
	private String newNote = "";

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public int getDocDetailsId() {
		return docDetailsId;
	}

	public String getDocumentFullPath() {
		return documentFullPath;
	}

	public String getDocumentName() {
		return documentName;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public String getUserName() {
		return userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setDocDetailsId(int docDetailsId) {
		this.docDetailsId = docDetailsId;
	}

	public void setDocumentFullPath(String documentFullPath) {
		this.documentFullPath = documentFullPath;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getNotesHistory() {
		return notesHistory;
	}

	public void setNotesHistory(String notesHistory) {
		this.notesHistory = notesHistory;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getNewNote() {
		return newNote;
	}

	public void setNewNote(String newNote) {
		this.newNote = newNote;
	}

}
