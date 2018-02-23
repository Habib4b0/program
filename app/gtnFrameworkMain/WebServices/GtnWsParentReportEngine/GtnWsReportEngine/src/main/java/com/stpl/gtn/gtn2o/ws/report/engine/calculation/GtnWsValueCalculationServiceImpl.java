package com.stpl.gtn.gtn2o.ws.report.engine.calculation;

import com.stpl.gtn.gtn2o.ws.report.engine.bean.GtnWsVariableCategoryBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsAttributeBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.service.GtnWsCalculationValidationService;
import java.util.List;

public class GtnWsValueCalculationServiceImpl implements GtnWsCalculationInterface {

    private GtnWsVariableCategoryBean variableCategoryBean;
    private GtnWsAttributeBean newAttributes;
    private int projectionId;
    private String variableCategory;
    private final GtnWsCalculationValidationService gtnWsCalculationValidation = GtnWsCalculationValidationService.getInstance();

    @Override
    public void initiateCalculation(GtnWsVariableCategoryBean variableCategoryBean) {
        setVariableCategoryBean(variableCategoryBean);
        setNewAttributes(this.variableCategoryBean.getCalculatedNodeAttribute());
        setProjectionId(this.variableCategoryBean.getProjectionId());
        setVariableCategoryStr(this.variableCategoryBean.getVariableCategory());
        setValue();
    }

    private void setVariableCategoryBean(GtnWsVariableCategoryBean variableCategoryBean) {
        this.variableCategoryBean = variableCategoryBean;
    }

    private void setNewAttributes(GtnWsAttributeBean newAttributes) {
        this.newAttributes = newAttributes;
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
                        this.newAttributes.putAttributes(selectColumn + basis + this.variableCategory,
                                this.variableCategoryBean.getCurrentNodeAttribute().getAttributes(selectColumn + basis));
                        this.newAttributes.putAttributes(selectColumn + basis + this.variableCategory + this.projectionId,
                                this.variableCategoryBean.getPriorNodeAttribute().getAttributes(selectColumn + basis));
                    }
                } else {
                    for (String selectColumn : this.variableCategoryBean.getComparisonBasisArray()) {
                        this.newAttributes.putAttributes(selectColumn + basis + this.variableCategory + this.projectionId,
                                this.variableCategoryBean.getPriorNodeAttribute().getAttributes(selectColumn + basis));
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
