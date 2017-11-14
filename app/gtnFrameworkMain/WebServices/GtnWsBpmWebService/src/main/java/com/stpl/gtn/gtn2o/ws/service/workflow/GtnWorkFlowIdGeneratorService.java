/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.service.workflow;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import com.stpl.gtn.gtn2o.ws.bean.xmlbean.GtnWsBPIGeneratorIDs;
import com.stpl.gtn.gtn2o.ws.bean.xmlbean.GtnWsRowConfig;
import com.stpl.gtn.gtn2o.ws.constants.workflow.GtnWsBpmCommonConstants;
import com.stpl.gtn.gtn2o.ws.util.xmlparser.GtnWsXmlParserService;

/**
 *
 * @author STPL
 */
public class GtnWorkFlowIdGeneratorService {

	public String generateId(String fileWithPath, String moduleName) throws FileNotFoundException, JAXBException {

		Map<String, String> hmCounterAndDate = readBPIXML(fileWithPath, moduleName);

		HashMap<String, String> hmBPIConterAndUpdateValues = getBPIWorkflowIDAndRequiredUpdates(hmCounterAndDate,
				moduleName);

		writeBPIXML(hmBPIConterAndUpdateValues, fileWithPath, moduleName);
		return hmBPIConterAndUpdateValues.get("bpiWKid");

	}

	/**
	 * Method to take current Counter and Update Date values, create the
	 * BPIWorklflowID and provide the values to be updated for Counter and
	 * Dates.
	 *
	 * @param hmCounterAndDate
	 * @param moduleName
	 * @return
	 */
	private HashMap<String, String> getBPIWorkflowIDAndRequiredUpdates(Map<String, String> hmCounterAndDate,
			String moduleName) {
		HashMap<String, String> hm = new HashMap<>();

		String counterValue = hmCounterAndDate.get(GtnWsBpmCommonConstants.COUNTER_VALUE);
		String updateDate = hmCounterAndDate.get("updateDate");

		int c = 0;
		String dateFormat = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Calendar c1 = Calendar.getInstance();
		String currentDate = sdf.format(c1.getTime());

		if (Integer.parseInt(updateDate) < Integer.parseInt(currentDate)) {

			updateDate = currentDate;

			counterValue = GtnWsBpmCommonConstants.STRING_ONE;

		} else {
			c = Integer.parseInt(counterValue) + 1;
			counterValue = (Integer.valueOf(c)).toString();

		}

		String fmt = "0000";
		DecimalFormat df = new DecimalFormat(fmt);
		if (c == 0) {
			c = 1;
		}
		String bpiIDCounter = df.format(c);

		String bpiWKid = moduleName + "F" + updateDate + bpiIDCounter;

		hm.put("dateToUpdate", updateDate);
		hm.put("counterToUpdate", counterValue);
		hm.put("bpiWKid", bpiWKid);

		return hm;
	}

	public Map<String, String> readBPIXML(String fileWithPath, String modName) throws JAXBException {
		HashMap<String, String> counterAndDateMap = new HashMap<>();
		GtnWsXmlParserService xmlParserService = new GtnWsXmlParserService();
		String updateDate = "NA";
		String counterValue = "NA";
		GtnWsBPIGeneratorIDs gtnWsBpiGeneratorIds = xmlParserService.xmlReader(fileWithPath);
		List<GtnWsRowConfig> rowList = gtnWsBpiGeneratorIds.getRowList();
		for (GtnWsRowConfig gtnWsRowConfig : rowList) {
			if (gtnWsRowConfig.getModuleName().equals(modName)) {
				counterValue = gtnWsRowConfig.getCounterValue();
				updateDate = gtnWsRowConfig.getCounterUpdatedDate();
			}
			counterAndDateMap.put(GtnWsBpmCommonConstants.COUNTER_VALUE, counterValue);
			counterAndDateMap.put("updateDate", updateDate);
		}

		return counterAndDateMap;

	}

	public void writeBPIXML(Map<String, String> hmBPIConterAndUpdateValues, String fileWithPath, String modName)
			throws JAXBException, FileNotFoundException {
		String updateDate = hmBPIConterAndUpdateValues.get("dateToUpdate");
		String counterValue = hmBPIConterAndUpdateValues.get("counterToUpdate");
		GtnWsXmlParserService xmlParserService = new GtnWsXmlParserService();
		GtnWsBPIGeneratorIDs gtnWsBpiGeneratorIds = xmlParserService.xmlReader(fileWithPath);
		List<GtnWsRowConfig> rowList = gtnWsBpiGeneratorIds.getRowList();
		for (GtnWsRowConfig gtnWsRowConfig : rowList) {
			if (gtnWsRowConfig.getModuleName().equals(modName)) {
				gtnWsRowConfig.setCounterValue(counterValue);
				gtnWsRowConfig.setCounterUpdatedDate(updateDate);
			}

		}
		xmlParserService.xmlWriter(gtnWsBpiGeneratorIds, fileWithPath);
	}
}
