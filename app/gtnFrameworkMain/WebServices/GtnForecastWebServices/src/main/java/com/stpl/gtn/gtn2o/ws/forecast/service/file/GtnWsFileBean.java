package com.stpl.gtn.gtn2o.ws.forecast.service.file;

public class GtnWsFileBean {

	private String fileName;

	private String filePath;

	private int fileStartIndex;

	private int groupingColumnIndex;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getFileStartIndex() {
		return fileStartIndex;
	}

	public void setFileStartIndex(int fileStartIndex) {
		this.fileStartIndex = fileStartIndex;
	}

	public int getGroupingColumnIndex() {
		return groupingColumnIndex;
	}

	public void setGroupingColumnIndex(int groupingColumnIndex) {
		this.groupingColumnIndex = groupingColumnIndex;
	}

}
