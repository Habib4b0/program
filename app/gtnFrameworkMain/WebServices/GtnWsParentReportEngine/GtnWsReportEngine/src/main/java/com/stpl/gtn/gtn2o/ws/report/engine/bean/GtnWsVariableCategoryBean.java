package com.stpl.gtn.gtn2o.ws.report.engine.bean;

import java.text.DecimalFormat;

import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsAttributeBean;

public class GtnWsVariableCategoryBean {

	private GtnWsAttributeBean currentNodeAttribute;
	private GtnWsAttributeBean priorNodeAttribute;
	private GtnWsAttributeBean calculatedNodeAttribute;
	private GtnWsCalculationType calculationType;
	private String variableCategory;
	private DecimalFormat decimalFormat;
	private String comparisonBasis;
	private int projectionId = 0;
	private final String[] comparisonBasisArray = new String[] { "contractSales" };
	// private final String[] comparisonBasisArray = new String[]{"exfactory",
	// "contractSales", "contractUnits", "discountAmount", "discountPercent", "rpu",
	// "netContractSales",
	// "grossContractSalesPerExFactory", "deductionPerExfactory",
	// "netContractSalesPerExfactory", "netExfactorySales",
	// "contractSalesPerTotalContractSales",
	// "netExfactorySalesPerTotalExfactory"};

	public GtnWsAttributeBean getCurrentNodeAttribute() {
		return currentNodeAttribute;
	}

	public void setCurrentNodeAttribute(GtnWsAttributeBean currentNodeAttribute) {
		this.currentNodeAttribute = currentNodeAttribute;
	}

	public GtnWsAttributeBean getPriorNodeAttribute() {
		return priorNodeAttribute;
	}

	public void setPriorNodeAttribute(GtnWsAttributeBean priorNodeAttribute) {
		this.priorNodeAttribute = priorNodeAttribute;
	}

	public GtnWsAttributeBean getCalculatedNodeAttribute() {
		return calculatedNodeAttribute;
	}

	public void setCalculatedNodeAttribute(GtnWsAttributeBean calculatedNodeAttribute) {
		this.calculatedNodeAttribute = calculatedNodeAttribute;
	}

	public GtnWsCalculationType getCalculationType() {
		return calculationType;
	}

	public void setCalculationType(GtnWsCalculationType calculationType) {
		this.calculationType = calculationType;
	}

	public String getVariableCategory() {
		return variableCategory;
	}

	public void setVariableCategory(String variableCategory) {
		this.variableCategory = variableCategory;
	}

	public DecimalFormat getDecimalFormat() {
		return decimalFormat;
	}

	public void setDecimalFormat(DecimalFormat decimalFormat) {
		this.decimalFormat = decimalFormat;
	}

	public String getComparisonBasis() {
		return comparisonBasis;
	}

	public void setComparisonBasis(String comparisonBasis) {
		this.comparisonBasis = comparisonBasis;
	}

	public int getProjectionId() {
		return projectionId;
	}

	public void setProjectionId(int projectionId) {
		this.projectionId = projectionId;
	}

	public String[] getComparisonBasisArray() {
		return comparisonBasisArray;
	}

}
