package com.stpl.gtn.gtn2o.ws.methodology;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.baseline.GtnWsBaseline;
import com.stpl.gtn.gtn2o.ws.baseline.GtnWsBaselineType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsMethodologyBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsFileIOService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsGeneralCalculationService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsPeriodCalculationService;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

public class GtnWsSinglePeriodMethodology implements GtnWsMethodology {

	private GtnWsMethodologyBean methodologyBean;

	Evaluator evaluator = new Evaluator();

	@Override
	public void initiateMethodologyCalculation(GtnWsMethodologyBean methodologyBean) {
		try {
			registerMethodologyBean(methodologyBean);
			readFiles(methodologyBean);
			calculateBaseline();
			cummulativeMultiplication();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void registerMethodologyBean(GtnWsMethodologyBean methodologyBean) {
		if (!this.validateMethodologyBean(methodologyBean)) {
			throw new IllegalArgumentException("Please check Methodology Bean.");
		}
		this.methodologyBean = methodologyBean;
	}

	private boolean validateMethodologyBean(GtnWsMethodologyBean methodologyBean) {
		return true;
	}

	@Override
	public void calculateBaseline() {
		try {
			GtnWsBaseline baseline = GtnWsBaselineType.SINGLE_PERIOD_BASELINE.getBaseline();
			baseline.calculateBaseline(methodologyBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void cummulativeMultiplication() {
		try {
			List<List<Object>> projectionFileList = methodologyBean.getProjectionFileList();
			List<Integer[]> periods = new ArrayList<>();

			GtnWsPeriodCalculationService periodCalculationService = GtnWsPeriodCalculationService.getInstance();
			periodCalculationService.getCalculationPeriods(periods, methodologyBean);

			GtnWsGeneralCalculationService generalCalculationService = GtnWsGeneralCalculationService.getInstance();
			Map<String, Integer> headerIndexMap = generalCalculationService
					.getFileHeaderIndex(methodologyBean.getProjectionFileHeaders());

			Map<String, Number[]> futureBaselineMap = generalCalculationService.getFormulaComponentIndex(
					methodologyBean.getFutureBaselineFormula(), methodologyBean.getProjectionFileHeaders());

			for (Integer fileLineIndex : methodologyBean.getDataIndex()) {

				List<Object> fileList = projectionFileList.get(fileLineIndex);
				double baselineValue = (double) methodologyBean.getBaselineMap().get(fileLineIndex);
				for (Integer[] period : periods) {
					applyCalculation(baselineValue, fileList, methodologyBean, period, headerIndexMap);
					calculateComponents(fileList, methodologyBean, period, headerIndexMap);
					baselineValue = calculateFutureBaseline(baselineValue, futureBaselineMap, methodologyBean, period,
							fileList);
				}
				resetMapValues(futureBaselineMap);
			}

			writeOutput(methodologyBean);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void writeOutput(GtnWsMethodologyBean methodologyBean) throws Exception {
		GtnWsFileIOService gtnFileIOService = new GtnWsFileIOService();
		gtnFileIOService.writeJsonDataToFile(methodologyBean.getProjectionFileName(),
				methodologyBean.getProjectionFileList());
	}

	/**
	 * Method to calculate the selected component.
	 * 
	 * @param baselineValue
	 * @param projectionFileDataList
	 * @param methodologyBean
	 * @param periodArray
	 * @param headerIndexMap
	 * @return
	 * @throws NumberFormatException
	 * @throws EvaluationException
	 */
	private double applyCalculation(double baselineValue, List<Object> projectionFileDataList,
			GtnWsMethodologyBean methodologyBean, Integer[] periodArray, Map<String, Integer> headerIndexMap)
			throws NumberFormatException, EvaluationException {

		GtnWsGeneralCalculationService generalCalculationService = GtnWsGeneralCalculationService.getInstance();

		String expression = methodologyBean.getProjectionFormula().replace("BASELINE",
				generalCalculationService.convertDoubleToString(baselineValue, methodologyBean.getDecimalFormat()));

		expression = generalCalculationService.generateExpression(expression, projectionFileDataList, periodArray,
				headerIndexMap, methodologyBean);

		int resultIndex = generalCalculationService.getIndex(periodArray[0],
				(periodArray[1] - methodologyBean.getProjectionStartYear()),
				methodologyBean.getProjectionFileHeaders().length,
				headerIndexMap.get(methodologyBean.getProjectionComponent()));

		baselineValue = evaluator.getNumberResult(expression);
		projectionFileDataList.set(resultIndex, baselineValue);

		return baselineValue;
	}

	/**
	 * Method to calculate other components available in the file based on the
	 * calculated component.
	 * 
	 * @param baselineValue
	 * @param projectionFileDataList
	 * @param methodologyBean
	 * @param periodArray
	 * @param headerIndexMap
	 * @throws EvaluationException
	 */
	private void calculateComponents(List<Object> projectionFileDataList, GtnWsMethodologyBean methodologyBean,
			Integer[] periodArray, Map<String, Integer> headerIndexMap) throws EvaluationException {

		Map<String, String> projFileComponentsFormulaMap = methodologyBean.getFileComponentFormulaMap();
		GtnWsGeneralCalculationService generalCalculationService = GtnWsGeneralCalculationService.getInstance();
		for (Map.Entry<String, String> entry : projFileComponentsFormulaMap.entrySet()) {

			String expression = generalCalculationService.generateExpression(entry.getValue(), projectionFileDataList,
					periodArray, headerIndexMap, methodologyBean);

			int resultIndex = generalCalculationService.getIndex(periodArray[0],
					(periodArray[1] - methodologyBean.getProjectionStartYear()),
					methodologyBean.getProjectionFileHeaders().length, headerIndexMap.get(entry.getKey()));

			projectionFileDataList.set(resultIndex, evaluator.getNumberResult(expression));
		}

	}

	/**
	 * Method calculates the baseline for the projection periods.
	 * <p>
	 * Example: Initial Baseline: Q3-2014,Q4-2014 - This will be calculated
	 * using the baseline class.
	 * <p>
	 * Calculation Periods: Q1-2015 - Q4-2015
	 * <p>
	 * Now for Q2-2015, the baseline will be the calculated value of Q1-2015.
	 * This method is used in calculating the baseline of Q2-2015.
	 * 
	 * @param baselineValue
	 * @param futureBaselineMap
	 * @param methodologyBean
	 * @param period
	 * @param projectionFileDataList
	 * @return
	 * @throws Exception
	 */
	private double calculateFutureBaseline(double baselineValue, Map<String, Number[]> futureBaselineMap,
			GtnWsMethodologyBean methodologyBean, Integer[] period, List<Object> projectionFileDataList)
			throws Exception {

		GtnWsGeneralCalculationService generalCalculationService = GtnWsGeneralCalculationService.getInstance();

		for (String key : futureBaselineMap.keySet()) {
			if (futureBaselineMap.get(key)[1].equals(0.0)) {
				futureBaselineMap.get(key)[1] = 0.0;
			}
			int index = generalCalculationService.getIndex(period[0],
					(period[1] - methodologyBean.getProjectionStartYear()),
					methodologyBean.getProjectionFileHeaders().length, (Integer) futureBaselineMap.get(key)[0]);

			futureBaselineMap.get(key)[1] = (double) futureBaselineMap.get(key)[1]
					+ generalCalculationService.doubleConversion(String.valueOf(projectionFileDataList.get(index)));
		}

		if (generalCalculationService.checkFrequencyEnd(methodologyBean, period)) {
			baselineValue = generalCalculationService.calculate(methodologyBean,
					methodologyBean.getFutureBaselineFormula(), futureBaselineMap);
			resetMapValues(futureBaselineMap);
		}

		return baselineValue;
	}

	/**
	 * Method resets the value of the formula components to the default value.
	 * 
	 * @param formulaDetailsMap
	 */
	private void resetMapValues(Map<String, Number[]> formulaDetailsMap) {

		for (Map.Entry<String, Number[]> entry : formulaDetailsMap.entrySet()) {
			entry.getValue()[1] = 0.0;
		}

	}

	/**
	 * Method reads the files from the given path.
	 * 
	 * @param methodologyBean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void readFiles(GtnWsMethodologyBean methodologyBean) throws Exception {
		GtnWsFileIOService gtnWsFileIOService = new GtnWsFileIOService();
		methodologyBean.setActualFileList(
				gtnWsFileIOService.readJSONDataFromFile(methodologyBean.getActualFileName(), List.class));
		methodologyBean.setProjectionFileList(
				gtnWsFileIOService.readJSONDataFromFile(methodologyBean.getProjectionFileName(), List.class));
	}

}
