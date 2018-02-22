package com.stpl.gtn.gtn2o.ws.report.engine.bean;

import com.stpl.gtn.gtn2o.ws.report.engine.calculation.GtnWsCalculationType;
import java.text.DecimalFormat;
import org.bson.Document;

public class GtnWsVariableCategoryBean {

    private Document currentDocument;
    private Document priorDocument;
    private Document calculatedDocument;
    private GtnWsCalculationType calculationType;
    private String variableCategory;
    private DecimalFormat decimalFormat;
    private String comparisonBasis;
    private int projectionId = 0;
    private final String[] comparisonBasisArray = new String[]{"exfactory", "contractSales"};
//    private final String[] comparisonBasisArray = new String[]{"exfactory", "contractSales", "contractUnits", "discountAmount", "discountPercent", "rpu", "netContractSales",
//        "grossContractSalesPerExFactory", "deductionPerExfactory", "netContractSalesPerExfactory", "netExfactorySales", "contractSalesPerTotalContractSales",
//        "netExfactorySalesPerTotalExfactory"};

    public Document getCurrentDocument() {
        return currentDocument;
    }

    public void setCurrentDocument(Document currentDocument) {
        this.currentDocument = currentDocument;
    }

    public Document getPriorDocument() {
        return priorDocument;
    }

    public void setPriorDocument(Document priorDocument) {
        this.priorDocument = priorDocument;
    }

    public Document getCalculatedDocument() {
        return calculatedDocument;
    }

    public void setCalculatedDocument(Document calculatedDocument) {
        this.calculatedDocument = calculatedDocument;
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
