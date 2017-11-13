/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service.calculation;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsMethodologyBean;
import com.stpl.gtn.gtn2o.ws.constants.CommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.GtnWsFrequencyConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsFileIOService;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsResourceService;
import com.stpl.gtn.gtn2o.ws.forecast.service.calculation.manualEntry.GtnWsReturnsManualEntryAmount;
import com.stpl.gtn.gtn2o.ws.forecast.service.calculation.manualEntry.GtnWsReturnsManualEntryGrowthRate;
import com.stpl.gtn.gtn2o.ws.forecast.service.calculation.manualEntry.GtnWsReturnsManualEntryPercentage;
import com.stpl.gtn.gtn2o.ws.forecast.service.calculation.manualEntry.GtnWsReturnsManualEntryRPU;
import com.stpl.gtn.gtn2o.ws.forecast.service.calculation.massudate.GtnWsReturnsMassUpdateAmount;
import com.stpl.gtn.gtn2o.ws.forecast.service.calculation.massudate.GtnWsReturnsMassUpdateGrowthRate;
import com.stpl.gtn.gtn2o.ws.forecast.service.calculation.massudate.GtnWsReturnsMassUpdatePercentage;
import com.stpl.gtn.gtn2o.ws.forecast.service.calculation.massudate.GtnWsReturnsMassUpdateRPU;
import com.stpl.gtn.gtn2o.ws.methodology.GtnWsMethodologyType;
import com.stpl.gtn.gtn2o.ws.service.GtnWsMethodologyService;

/**
 *
 * @author STPL
 */
@Service
public class GtnWsCalculationService {

	@Autowired
	private GtnWsReturnsManualEntryPercentage gtnWsReturnsManualEntryPercentage;

	@Autowired
	private GtnWsReturnsManualEntryRPU gtnWsReturnsManualEntryRPU;

	@Autowired
	private GtnWsReturnsManualEntryAmount gtnWsReturnsManualEntryAmount;

	@Autowired
	private GtnWsReturnsManualEntryGrowthRate gtnWsReturnsManualEntryGrowthRate;

	@Autowired
	private GtnWsReturnsMassUpdatePercentage gtnWsReturnsMassUpdatePercentage;

	@Autowired
	private GtnWsReturnsMassUpdateRPU gtnWsReturnsMassUpdateRPU;

	@Autowired
	private GtnWsReturnsMassUpdateAmount gtnWsReturnsMassUpdateAmount;

	@Autowired
	private GtnWsReturnsMassUpdateGrowthRate gtnWsReturnsMassUpdateGrowthRate;

	@Autowired
	private GtnWsReturnsFileIOService fileService;

	@Autowired
	private GtnWsCalculationUtilService calculationUtilService;

	@Autowired
	private GtnWsReturnsResourceService resourceService;

	@SuppressWarnings("unchecked")
	public void methodologyCalculation(final GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {
		gtnForecastBean.setManualEntryValue(inputFormatValidation(gtnForecastBean.getManualEntryValue()));
		gtnForecastBean.setManualEntryOldValue(inputFormatValidation(gtnForecastBean.getManualEntryOldValue()));

		String basePath = fileService.getFilePath(fileService.getModuleName(gtnForecastBean),
				gtnForecastBean.getUserId(), gtnForecastBean.getForecastSessionId(), gtnForecastBean.getTestFilePath());
		List<List<Object>> masterFileData = fileService.readJSONDataFromFile(
				basePath + "RETURNS_FORECAST_MASTER" + GtnFrameworkCommonStringConstants.STPL_EXTENSION, List.class);

		if (gtnForecastBean.isMethodologyCalculationFlag()) {

			doCaculationBasedOnMethodology(gtnForecastBean);
			fileService.createGroupedDataFile(gtnForecastBean, masterFileData);

		} else if ("Manual Entry-PROJECTED_RETURN_PERCENT".equalsIgnoreCase(gtnForecastBean.getForecastMethodology())) {

			gtnWsReturnsManualEntryPercentage.calculations(gtnForecastBean);
			fileService.createGroupedDataFile(gtnForecastBean, masterFileData);

		} else if ("Manual Entry-PROJECTED_RPU".equalsIgnoreCase(gtnForecastBean.getForecastMethodology())) {

			gtnWsReturnsManualEntryRPU.calculations(gtnForecastBean);
			fileService.createGroupedDataFile(gtnForecastBean, masterFileData);

		} else if ("Manual Entry-PROJECTED_RETURN_AMOUNT".equalsIgnoreCase(gtnForecastBean.getForecastMethodology())) {

			gtnWsReturnsManualEntryAmount.calculations(gtnForecastBean);
			fileService.createGroupedDataFile(gtnForecastBean, masterFileData);

		} else if ("Manual Entry-GROWTH_RATE".equalsIgnoreCase(gtnForecastBean.getForecastMethodology())) {

			gtnWsReturnsManualEntryGrowthRate.calculations(gtnForecastBean);
			fileService.createGroupedDataFile(gtnForecastBean, masterFileData);

		} else if ("Mass Update-Projected Return %".equalsIgnoreCase(gtnForecastBean.getForecastMethodology())) {

			gtnForecastBean.setMassUpdate(true);
			gtnWsReturnsMassUpdatePercentage.calculations(gtnForecastBean);
			fileService.createGroupedDataFile(gtnForecastBean, masterFileData);

		} else if ("Mass Update-Projected RPU".equalsIgnoreCase(gtnForecastBean.getForecastMethodology())) {

			gtnForecastBean.setMassUpdate(true);
			gtnWsReturnsMassUpdateRPU.calculations(gtnForecastBean);
			fileService.createGroupedDataFile(gtnForecastBean, masterFileData);

		} else if ("Mass Update-Projected Return Amount".equalsIgnoreCase(gtnForecastBean.getForecastMethodology())) {

			gtnForecastBean.setMassUpdate(true);
			gtnWsReturnsMassUpdateAmount.calculations(gtnForecastBean);
			fileService.createGroupedDataFile(gtnForecastBean, masterFileData);

		} else if ("Mass Update-Growth Rate".equalsIgnoreCase(gtnForecastBean.getForecastMethodology())) {
			gtnForecastBean.setMassUpdate(true);
			gtnWsReturnsMassUpdateGrowthRate.calculations(gtnForecastBean);
			fileService.createGroupedDataFile(gtnForecastBean, masterFileData);

		}
	}

	private boolean validator(final GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {
		if (isEmpty(gtnForecastBean.getFrequency())) {
			throw new GtnFrameworkGeneralException("Frequency is empty");
		} else if (isEmpty(gtnForecastBean.getCalculationStartPeriod())) {
			throw new GtnFrameworkGeneralException("Calculation Start Period is empty");
		} else if (isEmpty(gtnForecastBean.getHierarchyType())) {
			throw new GtnFrameworkGeneralException("Hierarchy Type is empty");
		} else if (isEmpty(gtnForecastBean.getModuleName())) {
			throw new GtnFrameworkGeneralException("Module Name is empty");
		} else if (isEmpty(gtnForecastBean.getUserId())) {
			throw new GtnFrameworkGeneralException("User Id is empty");
		} else if (isEmpty(gtnForecastBean.getForecastSessionId())) {
			throw new GtnFrameworkGeneralException("Session Id is empty");
		} else if (isEmpty(gtnForecastBean.getBaselinePeriodList())) {
			throw new GtnFrameworkGeneralException("Selected header period is empty");
		} else if (isEmpty(gtnForecastBean.getCheckedHierarchyNumbers())) {
			throw new GtnFrameworkGeneralException("Selected Hierarchy no is empty");
		}
		return Boolean.TRUE;
	}

	private boolean isEmpty(final String value) {
		return StringUtils.isEmpty(value) || value == null;
	}

	private boolean isEmpty(final List<String> value) {
		return value == null || value.isEmpty();
	}

	private String inputFormatValidation(String value) {
		value = StringUtils.remove(value, ",");
		value = StringUtils.remove(value, "%");
		value = StringUtils.remove(value, "$");
		return value;
	}

	/**
	 * Method used to configure the GtnWsMethodologyBean based on the selected
	 * methodology and calculate.
	 * 
	 * @param gtnForecastBean
	 * @throws GtnFrameworkGeneralException
	 */
	public void doCaculationBasedOnMethodology(GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {
		GtnWsMethodologyService methodologyService = new GtnWsMethodologyService();
		GtnWsMethodologyBean methodologyBean = createMethodologyBean(gtnForecastBean);
		methodologyService.calculate(methodologyBean);
	}

	/**
	 * Method used to create the GtnWsMethodologyBean based on the selected
	 * methodology. All the formulas, file headers are read from the .properties
	 * file and set in the GtnWsMethodologyBean bean.
	 * 
	 * @param gtnForecastBean
	 * @return
	 * @throws GtnFrameworkGeneralException
	 */
	private GtnWsMethodologyBean createMethodologyBean(GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException {

		GtnWsMethodologyBean methodologyBean = new GtnWsMethodologyBean();

		configureMethodologyComponents(gtnForecastBean, methodologyBean);

		methodologyBean.setFrequency(getFrequency(gtnForecastBean));

		methodologyBean.setActualStartYear(gtnForecastBean.getHistoryStartYear());
		methodologyBean.setCalculationStartPeriod(gtnForecastBean.getCalculationStartPeriod());
		methodologyBean.setCalculationEndPeriod(gtnForecastBean.getCalculationEndPeriod());

		methodologyBean.setProjectionStartYear(gtnForecastBean.getProjectionStartYear());
		methodologyBean.setProjectionStartMonth(gtnForecastBean.getProjectionStartMonth());
		methodologyBean.setProjectionEndYear(gtnForecastBean.getProjectionEndYear());
		methodologyBean.setProjectionEndMonth(gtnForecastBean.getProjectionEndMonth());

		methodologyBean.setDataIndex(calculationUtilService.getEndLevelHierarchyNode(gtnForecastBean));

		methodologyBean.setActualFileName(getFileName(gtnForecastBean, CommonConstants.RETURNS_FORECAST_ACTUAL));
		methodologyBean
				.setProjectionFileName(getFileName(gtnForecastBean, CommonConstants.RETURNS_FORECAST_PROJECTION));

		methodologyBean.setActualFileHeaders(getPropertiesAsArray("RETURNS_FORECAST_ACTUAL_FILE_COLUMN_INFO"));
		methodologyBean.setProjectionFileHeaders(getPropertiesAsArray("RETURNS_FORECAST_PROJECTION_FILE_COLUMN_INFO"));

		return methodologyBean;
	}

	/**
	 * Method checks the methodology type and call the methodology specific
	 * methods for configuration.
	 * 
	 * @param gtnForecastBean
	 * @param methodologyBean
	 * @throws GtnFrameworkGeneralException
	 */
	private void configureMethodologyComponents(GtnForecastBean gtnForecastBean, GtnWsMethodologyBean methodologyBean)
			throws GtnFrameworkGeneralException {

		if (StringUtils.isNotBlank(gtnForecastBean.getForecastMethodology())) {

			switch (gtnForecastBean.getForecastMethodology().toLowerCase()) {

			case "single period":
				configureSinglePeriodMethodologyVariables(gtnForecastBean, methodologyBean);
				break;

			case "average":
				configureAverageMethodologyVariables(gtnForecastBean, methodologyBean);
				break;

			case "rolling annual trend":
				configureRATMethodologyVariables(gtnForecastBean, methodologyBean);
				break;

			case "% of ex-factory":
				configurePercentOfExfactoryMethodologyVariables(gtnForecastBean, methodologyBean);
				break;

			case "quartile":
				configureQuartileMethodologyVariables(gtnForecastBean, methodologyBean);
				break;

			case "tier 1":
				configureTierOneMethodologyVariables(gtnForecastBean, methodologyBean);
				break;

			case "tier 2":
				configureTierTwoMethodologyVariables(gtnForecastBean, methodologyBean);
				break;

			default:
				throw new GtnFrameworkGeneralException(
						"Invalid Methodology. Methodology:" + gtnForecastBean.getForecastMethodology());

			}

		} else {
			throw new GtnFrameworkGeneralException(
					"Please Check Methodology. Methodology:" + gtnForecastBean.getForecastMethodology());
		}

	}

	/**
	 * Method returns the GtnWsFrequencyConstants enum based on the selected
	 * frequency.
	 * 
	 * @param gtnForecastBean
	 * @return
	 */
	private GtnWsFrequencyConstants getFrequency(GtnForecastBean gtnForecastBean) {

		if (StringUtils.isNotBlank(gtnForecastBean.getFrequency())) {

			if ("quarterly".equalsIgnoreCase(gtnForecastBean.getFrequency())) {
				return GtnWsFrequencyConstants.QUARTERLY;
			}

			if ("semi-annually".equalsIgnoreCase(gtnForecastBean.getFrequency())) {
				return GtnWsFrequencyConstants.SEMIANNUAL;
			}

			if ("annually".equalsIgnoreCase(gtnForecastBean.getFrequency())) {
				return GtnWsFrequencyConstants.ANNUALLY;
			}

			if ("monthly".equalsIgnoreCase(gtnForecastBean.getFrequency())) {
				return GtnWsFrequencyConstants.MONTHLY;
			}

		}
		return null;
	}

	/**
	 * Method reads the properties related to Single Period Methodology and sets
	 * in the GtnWsMethodologyBean bean.
	 * 
	 * @param gtnForecastBean
	 * @param methodologyBean
	 * @throws GtnFrameworkGeneralException
	 */
	private void configureSinglePeriodMethodologyVariables(GtnForecastBean gtnForecastBean,
			GtnWsMethodologyBean methodologyBean) throws GtnFrameworkGeneralException {
		if (validator(gtnForecastBean)) {
			methodologyBean.setMethodologyType(GtnWsMethodologyType.SINGLE_PERIOD);

			methodologyBean.setBaselineFormula(getFormula("SINGLE_PERIOD_BASELINE"));
			methodologyBean.setBaselinePeriodList(gtnForecastBean.getBaselinePeriodList());

			methodologyBean.setFutureBaselineFormula(getFormula("SINGLE_PERIOD_FUTURE_BASELINE"));

			methodologyBean.setProjectionFormula(getFormula("SINGLE_PERIOD_PROJECTED_RETURN_PERCENT"));
			methodologyBean.setProjectionComponent(GtnFrameworkWebserviceConstant.PROJECTED_RETURN_PERCENT);

			methodologyBean
					.setFileComponentFormulaMap(getFormulaMap("SINGLE_PERIOD_CALCULATION_COMPONENTS", "SINGLE_PERIOD"));
		}
	}

	/**
	 * Method reads the properties related to Average Methodology and sets in
	 * the GtnWsMethodologyBean bean.
	 * 
	 * @param gtnForecastBean
	 * @param methodologyBean
	 */
	private void configureAverageMethodologyVariables(GtnForecastBean gtnForecastBean,
			GtnWsMethodologyBean methodologyBean) {
		methodologyBean.setMethodologyType(GtnWsMethodologyType.AVERAGE);

		methodologyBean.setBaselineFormula(getFormula("AVERAGE_BASELINE"));
		methodologyBean.setBaselinePeriodList(gtnForecastBean.getBaselinePeriodList());

		methodologyBean.setFutureBaselineFormula(getFormula("AVERAGE_FUTURE_BASELINE"));

		methodologyBean.setProjectionFormula(getFormula("AVERAGE_PROJECTED_RETURN_PERCENT"));
		methodologyBean.setProjectionComponent(GtnFrameworkWebserviceConstant.PROJECTED_RETURN_PERCENT);

		methodologyBean.setFileComponentFormulaMap(getFormulaMap("AVERAGE_CALCULATION_COMPONENTS", "AVERAGE"));
	}

	/**
	 * Method reads the properties related to Rolling Annual Trend Methodology
	 * and sets in the GtnWsMethodologyBean bean.
	 * 
	 * @param gtnForecastBean
	 * @param methodologyBean
	 */
	private void configureRATMethodologyVariables(GtnForecastBean gtnForecastBean,
			GtnWsMethodologyBean methodologyBean) {
		methodologyBean.setMethodologyType(GtnWsMethodologyType.ROLLING_ANNUAL_TREND);

		methodologyBean.setBaselineFormula(getFormula("RAT_BASELINE"));
		methodologyBean.setBaselinePeriodList(gtnForecastBean.getBaselinePeriodList());

		methodologyBean.setProjectionFormula(getFormula("RAT_PROJECTED_RETURN_PERCENT"));
		methodologyBean.setProjectionComponent(GtnFrameworkWebserviceConstant.PROJECTED_RETURN_PERCENT);

		methodologyBean.setFileComponentFormulaMap(getFormulaMap("RAT_CALCULATION_COMPONENTS", "RAT"));
	}

	/**
	 * Method reads the properties related to Percent of Ex-factory Methodology
	 * and sets in the GtnWsMethodologyBean bean.
	 * 
	 * @param gtnForecastBean
	 * @param methodologyBean
	 */
	private void configurePercentOfExfactoryMethodologyVariables(GtnForecastBean gtnForecastBean,
			GtnWsMethodologyBean methodologyBean) {
		methodologyBean.setMethodologyType(GtnWsMethodologyType.PERCENTAGE_OF_EXFACTORY);

		methodologyBean.setBaselineFormula(getFormula("EX_FACTORY_BASELINE"));
		methodologyBean.setBaselinePeriodList(gtnForecastBean.getBaselinePeriodList());

		methodologyBean.setProjectionFormula(getFormula("EX_FACTORY_PROJECTED_RETURN_AMOUNT"));
		methodologyBean.setProjectionComponent("PROJECTED_RETURN_AMOUNT");

		methodologyBean.setFileComponentFormulaMap(getFormulaMap("EX_FACTORY_CALCULATION_COMPONENTS", "EX_FACTORY"));
	}

	/**
	 * Method reads the properties related to Percent of QUARTILE Methodology
	 * and sets in the GtnWsMethodologyBean bean.
	 * 
	 * @param gtnForecastBean
	 * @param methodologyBean
	 */
	private void configureQuartileMethodologyVariables(GtnForecastBean gtnForecastBean,
			GtnWsMethodologyBean methodologyBean) {
		methodologyBean.setMethodologyType(GtnWsMethodologyType.QUARTILE);

		methodologyBean.setBaselineFormula(getFormula("QUARTILE_BASELINE"));
		methodologyBean.setBaselinePeriodList(gtnForecastBean.getBaselinePeriodList());

		methodologyBean.setProjectionFormula(getFormula("QUARTILE_PROJECTED_RETURN_PERCENT"));
		methodologyBean.setProjectionComponent(GtnFrameworkWebserviceConstant.PROJECTED_RETURN_PERCENT);

		methodologyBean.setFileComponentFormulaMap(getFormulaMap("QUARTILE_CALCULATION_COMPONENTS", "QUARTILE"));
	}

	/**
	 * Method reads the properties related to Percent of TIER_ONE Methodology
	 * and sets in the GtnWsMethodologyBean bean.
	 * 
	 * @param gtnForecastBean
	 * @param methodologyBean
	 * @throws GtnFrameworkGeneralException
	 */
	private void configureTierOneMethodologyVariables(GtnForecastBean gtnForecastBean,
			GtnWsMethodologyBean methodologyBean) throws GtnFrameworkGeneralException {
		methodologyBean.setMethodologyType(GtnWsMethodologyType.TIER_ONE);

		methodologyBean.setBaselineFormula(getFormula("TIER_ONE_BASELINE"));
		methodologyBean.setBaselinePeriodList(gtnForecastBean.getBaselinePeriodList());

		methodologyBean.setProjectionFormula(getFormula("TIER_ONE_PROJECTED_RETURN_PERCENT"));
		methodologyBean.setProjectionComponent(GtnFrameworkWebserviceConstant.PROJECTED_RETURN_PERCENT);

		methodologyBean.setFileComponentFormulaMap(getFormulaMap("TIER_ONE_CALCULATION_COMPONENTS", "TIER_ONE"));

		methodologyBean.setTierOneFilterColumnName("IS_TIER_ONE");

		methodologyBean.setMasterFileName(getFileName(gtnForecastBean, CommonConstants.RETURNS_FORECAST_MASTER));

		methodologyBean
				.setMasterFileHeaders(getPropertiesAsArray(CommonConstants.RETURNS_FORECAST_MASTER_FILE_COLUMN_INFO));
	}

	/**
	 * Method reads the properties related to Percent of TIER_TWO Methodology
	 * and sets in the GtnWsMethodologyBean bean.
	 * 
	 * @param gtnForecastBean
	 * @param methodologyBean
	 * @throws GtnFrameworkGeneralException
	 */
	private void configureTierTwoMethodologyVariables(GtnForecastBean gtnForecastBean,
			GtnWsMethodologyBean methodologyBean) throws GtnFrameworkGeneralException {
		methodologyBean.setMethodologyType(GtnWsMethodologyType.TIER_TWO);

		methodologyBean.setBaselineFormula(getFormula("TIER_TWO_BASELINE"));
		methodologyBean.setBaselinePeriodList(gtnForecastBean.getBaselinePeriodList());

		methodologyBean.setProjectionFormula(getFormula("TIER_TWO_PROJECTED_RETURN_PERCENT"));
		methodologyBean.setProjectionComponent(GtnFrameworkWebserviceConstant.PROJECTED_RETURN_PERCENT);

		methodologyBean.setFileComponentFormulaMap(getFormulaMap("TIER_TWO_CALCULATION_COMPONENTS", "TIER_TWO"));
	}

	/**
	 * Method returns full path for the given file name.Path will be based on
	 * the Module Name,User Id, Session Id.
	 * 
	 * eg: basepath/${gtndata}/${modulename}/${userId}/${seesionId}/filename.
	 * 
	 * @param gtnForecastBean
	 * @param fileName
	 * @return
	 * @throws GtnFrameworkGeneralException
	 */
	private String getFileName(GtnForecastBean gtnForecastBean, String fileName) throws GtnFrameworkGeneralException {
		StringBuilder path = new StringBuilder(
				fileService.getFilePath(gtnForecastBean.getModuleName(), gtnForecastBean.getUserId(),
						gtnForecastBean.getForecastSessionId(), gtnForecastBean.getTestFilePath()));
		path.append(fileName);
		path.append(GtnFrameworkCommonStringConstants.STPL_EXTENSION);
		return path.toString();
	}

	/**
	 * Method returns the formula for the given formula-key. Formula should be
	 * available in gtn-returns-forecast-methodology-formula.properties file.
	 * 
	 * @param formulaKey
	 * @return
	 */
	private String getFormula(String formulaKey) {
		return resourceService.getPropertyValue(GtnFrameworkWebserviceConstant.GTNRETURNSFORECASTMETHODOLOGYFORMULA,
				CommonConstants.RESOURCES_PATH, formulaKey);
	}

	/**
	 * Method reads the formula from
	 * gtn-returns-forecast-methodology-formula.properties file and stores the
	 * formulae along with the key in a map.
	 * 
	 * @param formulaKeyListId
	 * @param methodologyName
	 * @return
	 */
	private Map<String, String> getFormulaMap(String formulaKeyListId, String methodologyName) {

		String value = resourceService.getPropertyValue(
				GtnFrameworkWebserviceConstant.GTNRETURNSFORECASTMETHODOLOGYFORMULA, CommonConstants.RESOURCES_PATH,
				formulaKeyListId);
		String[] formulaKeyArray = value.split(",");

		Map<String, String> formulaMap = resourceService.loadSelectedPropertiesInMap(
				GtnFrameworkWebserviceConstant.GTNRETURNSFORECASTMETHODOLOGYFORMULA, CommonConstants.RESOURCES_PATH,
				formulaKeyArray);

		for (String formulaKey : formulaKeyArray) {
			formulaMap.put(formulaKey.replace(methodologyName + "_", StringUtils.EMPTY), formulaMap.remove(formulaKey));
		}

		return formulaMap;
	}

	/**
	 * Method returns the comma separated values for the given key as String
	 * array.
	 * 
	 * @param key
	 * @return
	 */
	private String[] getPropertiesAsArray(String key) {
		String value = resourceService.getPropertyValue("file-name", CommonConstants.RESOURCES_PATH, key);
		return value.split(",");
	}

}