package com.stpl.gtn.gtn2o.ws.report.engine.calculation;

import com.stpl.gtn.gtn2o.ws.report.engine.bean.GtnWsVariableCategoryBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.service.GtnWsCalculationValidation;
import java.util.List;
import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import org.bson.Document;

public class GtnWsVarianceCalculation implements GtnWsCalculation {

    private GtnWsVariableCategoryBean variableCategoryBean;
    private Document newDocument;
    private String variableCategory;
    private final GtnWsCalculationValidation gtnWsCalculationValidation = GtnWsCalculationValidation.getInstance();
    private int projectionId;
    private static final Evaluator evaluator = new Evaluator();

    @Override
    public void initiateCalculation(GtnWsVariableCategoryBean variableCategoryBean) {
        setVariableCategoryBean(variableCategoryBean);
        setDocument(this.variableCategoryBean.getCalculatedDocument());
        setVariableCategoryStr(this.variableCategoryBean.getVariableCategory());
        setProjectionId(this.variableCategoryBean.getProjectionId());
        calculateVariance();
    }

    private void setVariableCategoryBean(GtnWsVariableCategoryBean variableCategoryBean) {
        this.variableCategoryBean = variableCategoryBean;
    }

    private void setDocument(Document newDocument) {
        this.newDocument = newDocument;
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
                this.newDocument.append(selectColumn + basis[1] + this.variableCategory + this.projectionId, executeCalculation(selectColumn + basis[0], selectColumn + basis[1]));
            }
        }

    }

    @Override
    public Double executeCalculation(String currentProperty, String priorProperty) {
        try {
            Object currentObject = this.variableCategoryBean.getCurrentDocument().get(currentProperty);
            Object priorObject = this.variableCategoryBean.getPriorDocument().get(priorProperty);
            String current = gtnWsCalculationValidation.getDoubleValue(currentObject);
            String prior = gtnWsCalculationValidation.getDoubleValue(priorObject);
            String formula = prior + "-" + current;
            return evaluator.getNumberResult(formula);
        } catch (EvaluationException ex) {
            ex.printStackTrace();
            return 0.0;
        }
    }

}
