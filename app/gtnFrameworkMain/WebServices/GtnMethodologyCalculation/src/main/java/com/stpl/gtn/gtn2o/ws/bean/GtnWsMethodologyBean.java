package com.stpl.gtn.gtn2o.ws.bean;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.stpl.gtn.gtn2o.ws.constants.GtnWsFrequencyConstants;
import com.stpl.gtn.gtn2o.ws.methodology.GtnWsMethodologyType;

public class GtnWsMethodologyBean {

	public GtnWsMethodologyBean() {
		initializeDecimalFormat();
	}

	private GtnWsMethodologyType methodologyType;	

	private GtnWsFrequencyConstants frequency;
	

	private String[] actualFileHeaders;

	private String[] projectionFileHeaders;
	
	private String[] masterFileHeaders;

	private List<List<Object>> actualFileList;

	private List<List<Object>> projectionFileList;

	private List<List<Object>> masterFileList;	

	private String actualFileName;

	private String projectionFileName;

	private String masterFileName;
	
	private List<Integer> dataIndex;


	private Map<Integer, Double> baselineMap;

	private Map<Integer, List<Double>> rollingAnnualTrendBaselineMap;


	private String baselineFormula;
	
	private String futureBaselineFormula;

	private String projectionFormula;

	private String projectionComponent;

	private Map<String, String> fileComponentFormulaMap;


	private int actualStartYear;

	private int projectionStartYear;

	private int projectionStartMonth;

	private int projectionEndYear;

	private int projectionEndMonth;

	private String calculationStartPeriod;

	private String calculationEndPeriod;

	private List<String> baselinePeriodList;
	

	private DecimalFormat decimalFormat;
	
	
	private String tierOneFilterColumnName;
	

	public GtnWsMethodologyType getMethodologyType() {
		return methodologyType;
	}

	public void setMethodologyType(GtnWsMethodologyType methodologyType) {
		this.methodologyType = methodologyType;
	}

	public String[] getActualFileHeaders() {
		return actualFileHeaders;
	}

	public void setActualFileHeaders(String[] actualFileHeaders) {
		this.actualFileHeaders = actualFileHeaders;
	}

	public String[] getProjectionFileHeaders() {
		return projectionFileHeaders;
	}

	public void setProjectionFileHeaders(String[] projectionFileHeaders) {
		this.projectionFileHeaders = projectionFileHeaders;
	}

	public List<List<Object>> getActualFileList() {
		return actualFileList;
	}

	public void setActualFileList(List<List<Object>> actualFileList) {
		this.actualFileList = actualFileList;
	}

	public List<List<Object>> getProjectionFileList() {
		return projectionFileList;
	}

	public void setProjectionFileList(List<List<Object>> projectionFileList) {
		this.projectionFileList = projectionFileList;
	}

	public Map<Integer, Double> getBaselineMap() {
		return baselineMap;
	}

	public void setBaselineMap(Map<Integer, Double> baselineMap) {
		this.baselineMap = baselineMap;
	}

	public int getActualStartYear() {
		return actualStartYear;
	}

	public void setActualStartYear(int actualStartYear) {
		this.actualStartYear = actualStartYear;
	}

	public int getProjectionStartYear() {
		return projectionStartYear;
	}

	public void setProjectionStartYear(int projectionStartYear) {
		this.projectionStartYear = projectionStartYear;
	}

	public List<String> getBaselinePeriodList() {
		return baselinePeriodList;
	}

	public void setBaselinePeriodList(List<String> baselinePeriodList) {
		this.baselinePeriodList = baselinePeriodList;
	}

	public GtnWsFrequencyConstants getFrequency() {
		return frequency;
	}

	public void setFrequency(GtnWsFrequencyConstants frequency) {
		this.frequency = frequency;
	}

	public List<Integer> getDataIndex() {
		return dataIndex;
	}

	public void setDataIndex(List<Integer> dataIndex) {
		this.dataIndex = dataIndex;
	}

	public String getBaselineFormula() {
		return baselineFormula;
	}

	public void setBaselineFormula(String baselineFormula) {
		this.baselineFormula = baselineFormula;
	}

	public String getActualFileName() {
		return actualFileName;
	}

	public void setActualFileName(String actualFileName) {
		this.actualFileName = actualFileName;
	}

	public String getProjectionFileName() {
		return projectionFileName;
	}

	public void setProjectionFileName(String projectionFileName) {
		this.projectionFileName = projectionFileName;
	}

	public String getMasterFileName() {
		return masterFileName;
	}

	public void setMasterFileName(String masterFileName) {
		this.masterFileName = masterFileName;
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

	public String getCalculationStartPeriod() {
		return calculationStartPeriod;
	}

	public void setCalculationStartPeriod(String calculationStartPeriod) {
		this.calculationStartPeriod = calculationStartPeriod;
	}

	public String getCalculationEndPeriod() {
		return calculationEndPeriod;
	}

	public void setCalculationEndPeriod(String calculationEndPeriod) {
		this.calculationEndPeriod = calculationEndPeriod;
	}

	public Map<String, String> getFileComponentFormulaMap() {
		return fileComponentFormulaMap;
	}

	public void setFileComponentFormulaMap(Map<String, String> fileComponentFormulaMap) {
		this.fileComponentFormulaMap = fileComponentFormulaMap;
	}

	public String getProjectionFormula() {
		return projectionFormula;
	}

	public void setProjectionFormula(String projectionFormula) {
		this.projectionFormula = projectionFormula;
	}

	public String getProjectionComponent() {
		return projectionComponent;
	}

	public void setProjectionComponent(String projectionComponent) {
		this.projectionComponent = projectionComponent;
	}

	public int getProjectionStartMonth() {
		return projectionStartMonth;
	}

	public void setProjectionStartMonth(int projectionStartMonth) {
		this.projectionStartMonth = projectionStartMonth;
	}

	public Map<Integer, List<Double>> getRollingAnnualTrendBaselineMap() {
		return rollingAnnualTrendBaselineMap;
	}

	public void setRollingAnnualTrendBaselineMap(Map<Integer, List<Double>> rollingAnnualTrendBaselineMap) {
		this.rollingAnnualTrendBaselineMap = rollingAnnualTrendBaselineMap;
	}

	public DecimalFormat getDecimalFormat() {
		return decimalFormat;
	}

	public List<List<Object>> getMasterFileList() {
		return masterFileList;
	}

	public void setMasterFileList(List<List<Object>> masterFileList) {
		this.masterFileList = masterFileList;
	}	
	
	public String[] getMasterFileHeaders() {
		return masterFileHeaders;
	}

	public void setMasterFileHeaders(String[] masterFileHeaders) {
		this.masterFileHeaders = masterFileHeaders;
	}

	public String getTierOneFilterColumnName() {
		return tierOneFilterColumnName;
	}

	public void setTierOneFilterColumnName(String tierOneFilterColumnName) {
		this.tierOneFilterColumnName = tierOneFilterColumnName;
	}	

	public String getFutureBaselineFormula() {
		return futureBaselineFormula;
	}

	public void setFutureBaselineFormula(String futureBaselineFormula) {
		this.futureBaselineFormula = futureBaselineFormula;
	}

	private void initializeDecimalFormat() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("gtn-ws-methodology-calculation");
		decimalFormat = new DecimalFormat(resourceBundle.getString("calculation.decimal.format"));
	}

}
