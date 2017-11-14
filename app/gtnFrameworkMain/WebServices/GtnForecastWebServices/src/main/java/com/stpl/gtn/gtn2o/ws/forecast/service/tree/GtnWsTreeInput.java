/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service.tree;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author STPL
 */
public class GtnWsTreeInput {

	private List<String> masterFileInputColumn;

	private List<String> actualDataFileInputColumn;

	private List<String> projectionDataFileInputColumn;

	private Map<Integer, String> actualFormulaMap;

	private Map<Integer, String> projectionFormulaMap;

	private int actualStartYear;

	private int actualStartMonth;

	private int actualEndYear;

	private int actualEndMonth;

	private int projectionStartYear;

	private int projectionStartMonth;

	private int projectionEndYear;

	private int projectionEndMonth;

	private int masterFileIgnoreIndex = 7;

	private int actualFileIgnoreIndex = 0;

	private int projectionFileIgnoreIndex = 0;

	private List<Integer> selectedActualListViewColumn;

	private List<Integer> selectedProjectedListViewColumn;

	private int selectedHistoryMonth;

	private int selectedHistoryYear;

	private int frequency = 1;

	private boolean isAscending;

	private String actualOrProjection;

	private List<DecimalFormat> actualColumnFormat;

	private List<DecimalFormat> projectionColumnFormat;

	public List<String> getMasterFileInputColumn() {
		return masterFileInputColumn;
	}

	public void setMasterFileInputColumn(List<String> masterFileInputColumn) {
		this.masterFileInputColumn = masterFileInputColumn;
	}

	public List<String> getActualDataFileInputColumn() {
		return actualDataFileInputColumn;
	}

	public void setActualDataFileInputColumn(List<String> actualDataFileInputColumn) {
		this.actualDataFileInputColumn = actualDataFileInputColumn;
	}

	public List<String> getProjectionDataFileInputColumn() {
		return projectionDataFileInputColumn;
	}

	public void setProjectionDataFileInputColumn(List<String> projectionDataFileInputColumn) {
		this.projectionDataFileInputColumn = projectionDataFileInputColumn;
	}

	public int getActualStartYear() {
		return actualStartYear;
	}

	public void setActualStartYear(int actualStartYear) {
		this.actualStartYear = actualStartYear;
	}

	public int getActualStartMonth() {
		return actualStartMonth;
	}

	public void setActualStartMonth(int actualStartMonth) {
		this.actualStartMonth = actualStartMonth;
	}

	public int getActualEndYear() {
		return actualEndYear;
	}

	public void setActualEndYear(int actualEndYear) {
		this.actualEndYear = actualEndYear;
	}

	public int getActualEndMonth() {
		return actualEndMonth;
	}

	public void setActualEndMonth(int actualEndMonth) {
		this.actualEndMonth = actualEndMonth;
	}

	public int getProjectionStartYear() {
		return projectionStartYear;
	}

	public void setProjectionStartYear(int projectionStartYear) {
		this.projectionStartYear = projectionStartYear;
	}

	public int getProjectionStartMonth() {
		return projectionStartMonth;
	}

	public void setProjectionStartMonth(int projectionStartMonth) {
		this.projectionStartMonth = projectionStartMonth;
	}

	public int getProjectionEndYear() {
		return projectionEndYear;
	}

	public void setProjectionEndYear(int projectionEndYear) {
		this.projectionEndYear = projectionEndYear;
	}

	public int getProjectionEndMonth() {
		return projectionEndMonth;
	}

	public void setProjectionEndMonth(int projectionEndMonth) {
		this.projectionEndMonth = projectionEndMonth;
	}

	public Map<Integer, String> getActualFormulaMap() {
		return actualFormulaMap;
	}

	public void setActualFormulaMap(Map<Integer, String> actualFormulaMap) {
		this.actualFormulaMap = actualFormulaMap;
	}

	public Map<Integer, String> getProjectionFormulaMap() {
		return projectionFormulaMap;
	}

	public void setProjectionFormulaMap(Map<Integer, String> projectionFormulaMap) {
		this.projectionFormulaMap = projectionFormulaMap;
	}

	public int getMasterFileIgnoreIndex() {
		return masterFileIgnoreIndex;
	}

	public void setMasterFileIgnoreIndex(int masterFileIgnoreIndex) {
		this.masterFileIgnoreIndex = masterFileIgnoreIndex;
	}

	public int getActualFileIgnoreIndex() {
		return actualFileIgnoreIndex;
	}

	public void setActualFileIgnoreIndex(int actualFileIgnoreIndex) {
		this.actualFileIgnoreIndex = actualFileIgnoreIndex;
	}

	public int getProjectionFileIgnoreIndex() {
		return projectionFileIgnoreIndex;
	}

	public void setProjectionFileIgnoreIndex(int projectionFileIgnoreIndex) {
		this.projectionFileIgnoreIndex = projectionFileIgnoreIndex;
	}

	public List<Integer> getSelectedActualListViewColumn() {
		return selectedActualListViewColumn;
	}

	public void setSelectedActualListViewColumn(List<Integer> selectedActualListViewColumn) {
		this.selectedActualListViewColumn = selectedActualListViewColumn;
	}

	public List<Integer> getSelectedProjectedListViewColumn() {
		return selectedProjectedListViewColumn;
	}

	public void setSelectedProjectedListViewColumn(List<Integer> selectedProjectedListViewColumn) {
		this.selectedProjectedListViewColumn = selectedProjectedListViewColumn;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getSelectedHistoryMonth() {
		return selectedHistoryMonth;
	}

	public void setSelectedHistoryMonth(int selectedHistoryMonth) {
		this.selectedHistoryMonth = selectedHistoryMonth;
	}

	public int getSelectedHistoryYear() {
		return selectedHistoryYear;
	}

	public void setSelectedHistoryYear(int selectedHistoryYear) {
		this.selectedHistoryYear = selectedHistoryYear;
	}

	public boolean isIsAscending() {
		return isAscending;
	}

	public void setIsAscending(boolean isAscending) {
		this.isAscending = isAscending;
	}

	public String getActualOrProjection() {
		return actualOrProjection;
	}

	public void setActualOrProjection(String actualOrProjection) {
		this.actualOrProjection = actualOrProjection;
	}

	public List<DecimalFormat> getActualColumnFormat() {
		return actualColumnFormat;
	}

	public void setActualColumnFormat(List<String> actualColumnFormat) {
		Iterator<String> iterator = actualColumnFormat.iterator();
		if (this.actualColumnFormat == null) {
			this.actualColumnFormat = new ArrayList<>(actualColumnFormat.size());
		}
		while (iterator.hasNext()) {
			this.actualColumnFormat.add(new DecimalFormat(iterator.next()));
		}
	}

	public List<DecimalFormat> getProjectionColumnFormat() {
		return projectionColumnFormat;
	}

	public void setProjectionColumnFormat(List<String> projectionColumnFormat) {
		Iterator<String> iterator = projectionColumnFormat.iterator();
		if (this.projectionColumnFormat == null) {
			this.projectionColumnFormat = new ArrayList<>(projectionColumnFormat.size());
		}
		while (iterator.hasNext()) {
			this.projectionColumnFormat.add(new DecimalFormat(iterator.next()));
		}
	}

}
