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

public class GtnWsQuartileMethodology implements GtnWsMethodology {

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
			GtnWsBaseline baseline = GtnWsBaselineType.QUARTILE_BASELINE.getBaseline();
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

			for (Integer fileLineIndex : methodologyBean.getDataIndex()) {

				List<Object> fileList = projectionFileList.get(fileLineIndex);
				double baselineValue = (double) methodologyBean.getBaselineMap().get(fileLineIndex);
				for (Integer[] period : periods) {
					applyCalculation(baselineValue, fileList, methodologyBean, period, headerIndexMap);
					calculateComponents(fileList, methodologyBean, period, headerIndexMap);
				}
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

	@SuppressWarnings("unchecked")
	private void readFiles(GtnWsMethodologyBean methodologyBean) throws Exception {
		GtnWsFileIOService gtnWsFileIOService = new GtnWsFileIOService();
		methodologyBean.setActualFileList(
				gtnWsFileIOService.readJSONDataFromFile(methodologyBean.getActualFileName(), List.class));
		methodologyBean.setProjectionFileList(
				gtnWsFileIOService.readJSONDataFromFile(methodologyBean.getProjectionFileName(), List.class));
	}

}