package com.stpl.gtn.gtn2o.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsMethodologyBean;
import com.stpl.gtn.gtn2o.ws.constants.GtnWsFrequencyConstants;
import com.stpl.gtn.gtn2o.ws.methodology.GtnWsMethodologyType;
import com.stpl.gtn.gtn2o.ws.service.GtnWsMethodologyService;

public class GtnWsTierOneMethodologyTest {

	@Test
	public void testTierOneMethodology() {
		GtnWsMethodologyService methodologyService = new GtnWsMethodologyService();
		GtnWsMethodologyBean methodologyBean = createMethodologyBean();
		methodologyService.calculate(methodologyBean);
	}

	private GtnWsMethodologyBean createMethodologyBean() {

		GtnWsMethodologyBean methodologyBean = new GtnWsMethodologyBean();

		methodologyBean.setMethodologyType(GtnWsMethodologyType.TIER_ONE);
		methodologyBean.setFrequency(GtnWsFrequencyConstants.QUARTERLY);

		methodologyBean.setActualStartYear(2014);
		methodologyBean.setCalculationStartPeriod("Q1 2015");
		methodologyBean.setCalculationEndPeriod("Q4 2016");

		methodologyBean.setProjectionStartYear(2014);
		methodologyBean.setProjectionStartMonth(0);
		methodologyBean.setProjectionEndYear(2019);
		methodologyBean.setProjectionEndMonth(0);

		methodologyBean.setDataIndex(getDataIndex());

		methodologyBean.setBaselineFormula("CUM_RETURN_UNITS/ORIG_SALE_UNITS");
		methodologyBean.setBaselinePeriodList(getBaselinePeriodList());

		methodologyBean.setProjectionFormula("BASELINE*(1+(GROWTH_RATE))");
		methodologyBean.setProjectionComponent("PROJECTED_RETURN_PERCENT");

		methodologyBean.setFileComponentFormulaMap(getFormulaMap());

		String basepath = "D:/Sibi/Server/bpigtn_dev_ws/jboss-7.1.1/gtnframework_base_path";

		methodologyBean.setActualFileName(
				basepath + "/gtndata/returns/Apr_26_2017/774425/063951537/RETURNS_FORECAST_ACTUAL.stpl");
		methodologyBean.setProjectionFileName(
				basepath + "/gtndata/returns/Apr_26_2017/774425/063951537/RETURNS_FORECAST_PROJECTION.stpl");
		methodologyBean.setMasterFileName(
				basepath + "/gtndata/returns/Apr_26_2017/774425/063951537/RETURNS_FORECAST_MASTER.stpl");

		methodologyBean.setActualFileHeaders(new String[] { "PERIOD_SID", "ITEM_MASTER_SID", "ACTUAL_RETURN_PERCENT",
				"ACTUAL_RPU", "ACTUAL_RETURN_AMOUNT", "ORIG_SALE_UNITS", "ORIG_SALE_DOLLARS", "CUM_RETURN_UNITS", "ASP",
				"EXPECTED_RETURN_RATE" });

		methodologyBean.setProjectionFileHeaders(new String[] { "PERIOD_SID", "ITEM_MASTER_SID",
				"PROJECTED_RETURN_PERCENT", "PROJECTED_RPU", "PROJECTED_RETURN_AMOUNT", "GROWTH_RATE",
				"PROJECTED_RETURN_UNITS", "ACTIVE_EXFACTORY_SALES_AMOUNT", "ACTIVE_EXFACTORY_SALES_UNITS" });

		methodologyBean.setMasterFileHeaders(new String[] { "HIERARCHY_NO", "ITEM_MASTER_SID", "METHODOLOGY",
				"METHODOLOGY_START_DATE", "METHODOLOGY_END_DATE", "CALCULATION_PERIODS", "IS_TIER_ONE", "CHECK_RECORD",
				"LAG", "CLOSED_DATE", "LOE_DATE" });
		
		methodologyBean.setTierOneFilterColumnName("IS_TIER_ONE");

		return methodologyBean;
	}

	/*
	 * TIER_ONE_ will be appended before PROJECTED_RETURN_AMOUNT and
	 * PROJECTED_RPU in the formula properties file.
	 * 
	 * Will be like TIER_ONE_PROJECTED_RETURN_AMOUNT and
	 * TIER_ONE_PROJECTED_RPU.
	 */
	private Map<String, String> getFormulaMap() {
		Map<String, String> map = new HashMap<>();
		map.put("PROJECTED_RETURN_AMOUNT", "PROJECTED_RETURN_PERCENT*ACTIVE_EXFACTORY_SALES_AMOUNT");
		map.put("PROJECTED_RPU", "PROJECTED_RETURN_AMOUNT/ACTIVE_EXFACTORY_SALES_UNITS");
		map.put("PROJECTED_RETURN_UNITS",
				"PROJECTED_RETURN_AMOUNT/(ACTIVE_EXFACTORY_SALES_AMOUNT/ACTIVE_EXFACTORY_SALES_UNITS)");
		return map;
	}

	private List<String> getBaselinePeriodList() {
		List<String> list = new ArrayList<>();
		list.add("Q4 2014");
		return list;
	}

	private List<Integer> getDataIndex() {
		List<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		return list;
	}

}
