package com.stpl.gtn.gtn2o.ws.report.engine.calculation;

import com.stpl.gtn.gtn2o.ws.report.engine.bean.GtnWsVariableCategoryBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsAttributeBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.service.GtnWsCalculationValidationService;
import java.util.List;
import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

public class GtnWsVarianceCalculationServiceImpl implements GtnWsCalculationInterface {

    private GtnWsVariableCategoryBean variableCategoryBean;
    private GtnWsAttributeBean newAttribute;
    private String variableCategory;
    private final GtnWsCalculationValidationService gtnWsCalculationValidation = GtnWsCalculationValidationService.getInstance();
    private int projectionId;
    private static final Evaluator EVALUATOR = new Evaluator();

    @Override
    public void initiateCalculation(GtnWsVariableCategoryBean variableCategoryBean) {
        setVariableCategoryBean(variableCategoryBean);
        setNewAttribute(this.variableCategoryBean.getCalculatedNodeAttribute());
        setVariableCategoryStr(this.variableCategoryBean.getVariableCategory());
        setProjectionId(this.variableCategoryBean.getProjectionId());
        calculateVariance();
    }

    private void setVariableCategoryBean(GtnWsVariableCategoryBean variableCategoryBean) {
        this.variableCategoryBean = variableCategoryBean;
    }

    private void setNewAttribute(GtnWsAttributeBean newAttribute) {
        this.newAttribute = newAttribute;
    }

    private void setVariableCategoryStr(String variableCategory) {
        this.variableCategory = variableCategory;
    }

    private void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }

    private void calculateVariance() {
        List<String[]> comparisonBasis = gtnWsCalculationValidation.getComparisonBasis(variableCategoryBean.getComparisonBasis());
        for (String[] basis : comparisonBasis) {
            for (String selectColumn : this.variableCategoryBean.getComparisonBasisArray()) {
                this.newAttribute.putAttributes(selectColumn + basis[1] + this.variableCategory + this.projectionId, executeCalculation(selectColumn + basis[0], selectColumn + basis[1]));
            }
        }

    }

    @Override
    public Double executeCalculation(String currentProperty, String priorProperty) {
        try {
            Object currentObject = this.variableCategoryBean.getCurrentNodeAttribute().getAttributes(currentProperty);
            Object priorObject = this.variableCategoryBean.getPriorNodeAttribute().getAttributes(priorProperty);
            String current = gtnWsCalculationValidation.getDoubleValue(currentObject);
            String prior = gtnWsCalculationValidation.getDoubleValue(priorObject);
            String formula = prior + "-" + current;
            return EVALUATOR.getNumberResult(formula);
        } catch (EvaluationException ex) {
            ex.printStackTrace();
            return 0.0;
        }
    }

}
