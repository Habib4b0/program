package com.stpl.gtn.gtn2o.ws.report.engine.calculation;

import java.util.List;

import com.stpl.gtn.gtn2o.ws.report.engine.bean.GtnWsVariableCategoryBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsAttributeBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.service.GtnWsCalculationValidationService;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

public class GtnWsPerChangeCalculationServiceImpl implements GtnWsCalculationInterface {

	GtnWsCalculationValidationService gtnWsCalculationValidation = GtnWsCalculationValidationService.getInstance();

	private GtnWsVariableCategoryBean variableCategoryBean;
	private GtnWsAttributeBean newAttributes;
	private String variableCategory;

	private int projectionId;
	private static final Evaluator EVALUATOR = new Evaluator();

	@Override
	public void initiateCalculation(GtnWsVariableCategoryBean variableCategoryBean) {
		setVariableCategoryBean(variableCategoryBean);
		setNewAttributes(this.variableCategoryBean.getCalculatedNodeAttribute());
		setVariableCategoryStr(this.variableCategoryBean.getVariableCategory());
		setProjectionId(this.variableCategoryBean.getProjectionId());
		calculateVariance();
	}

	private void setVariableCategoryBean(GtnWsVariableCategoryBean variableCategoryBean) {
		this.variableCategoryBean = variableCategoryBean;
	}

	private void setNewAttributes(GtnWsAttributeBean newAttributes) {
		this.newAttributes = newAttributes;
	}

	private void setVariableCategoryStr(String variableCategory) {
		this.variableCategory = variableCategory;
	}

	private void setProjectionId(int projectionId) {
		this.projectionId = projectionId;
	}

	private void calculateVariance() {
		List<String[]> comparisonBasis = gtnWsCalculationValidation
				.getComparisonBasis(variableCategoryBean.getComparisonBasis());
		for (String[] basis : comparisonBasis) {
			for (String selectColumn : this.variableCategoryBean.getComparisonBasisArray()) {
				this.newAttributes.putAttributes(selectColumn+ this.variableCategory  + basis[1] + this.projectionId,
						executeCalculation(selectColumn + "Variance"  + basis[1] + this.projectionId,
								selectColumn + basis[1]));
			}
		}
	}

	@Override
	public Double executeCalculation(String currentProperty, String priorProperty) {
		try {
			Object currentObject = this.variableCategoryBean.getCalculatedNodeAttribute().getAttribute(currentProperty);
			Object priorObject = this.variableCategoryBean.getPriorNodeAttribute().getAttribute(priorProperty);
			String current = gtnWsCalculationValidation.getDoubleValue(currentObject);
			String prior = gtnWsCalculationValidation.getDoubleValue(priorObject);
			String formula = "(" + current + "/" + prior + ")*100";
			return gtnWsCalculationValidation.getDoubleValue(EVALUATOR.getNumberResult(formula));
		} catch (EvaluationException ex) {
			ex.printStackTrace();
			return 0.0;
		}
	}

}
