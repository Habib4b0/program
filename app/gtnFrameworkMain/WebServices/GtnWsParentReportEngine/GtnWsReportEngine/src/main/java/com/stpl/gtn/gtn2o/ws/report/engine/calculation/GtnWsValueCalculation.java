package com.stpl.gtn.gtn2o.ws.report.engine.calculation;

import com.stpl.gtn.gtn2o.ws.report.engine.bean.GtnWsVariableCategoryBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.service.GtnWsCalculationValidation;
import java.util.List;
import org.bson.Document;

public class GtnWsValueCalculation implements GtnWsCalculation {

    private GtnWsVariableCategoryBean variableCategoryBean;
    private Document newDocument;
    private int projectionId;
    private String variableCategory;
    private final GtnWsCalculationValidation gtnWsCalculationValidation = GtnWsCalculationValidation.getInstance();

    @Override
    public void initiateCalculation(GtnWsVariableCategoryBean variableCategoryBean) {
        setVariableCategoryBean(variableCategoryBean);
        setDocument(this.variableCategoryBean.getCalculatedDocument());
        setProjectionId(this.variableCategoryBean.getProjectionId());
        setVariableCategoryStr(this.variableCategoryBean.getVariableCategory());
        setValue();
    }

    private void setVariableCategoryBean(GtnWsVariableCategoryBean variableCategoryBean) {
        this.variableCategoryBean = variableCategoryBean;
    }

    private void setDocument(Document newDocument) {
        this.newDocument = newDocument;
    }

    private void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }

    private void setVariableCategoryStr(String variableCategory) {
        this.variableCategory = variableCategory;
    }

    private void setValue() {
        List<String[]> comparisonBasis = gtnWsCalculationValidation.getComparisonBasis(variableCategoryBean.getComparisonBasis());
        for (String[] comparisonBasisStr : comparisonBasis) {
            for (String basis : comparisonBasisStr) {
                if (this.projectionId == 0) {
                    for (String selectColumn : this.variableCategoryBean.getComparisonBasisArray()) {
                        this.newDocument.append(selectColumn + basis + this.variableCategory,
                                this.variableCategoryBean.getCurrentDocument().get(selectColumn + basis));
                        this.newDocument.append(selectColumn + basis + this.variableCategory + this.projectionId,
                                this.variableCategoryBean.getPriorDocument().get(selectColumn + basis));
                    }
                } else {
                    for (String selectColumn : this.variableCategoryBean.getComparisonBasisArray()) {
                        this.newDocument.append(selectColumn + basis + this.variableCategory + this.projectionId,
                                this.variableCategoryBean.getPriorDocument().get(selectColumn + basis));
                    }
                }
            }
        }

    }

    @Override
    public Double executeCalculation(String currentProperty, String priorProperty) {
        //For value this will not be called since no calculation is needed
        return 0.0;
    }

}
