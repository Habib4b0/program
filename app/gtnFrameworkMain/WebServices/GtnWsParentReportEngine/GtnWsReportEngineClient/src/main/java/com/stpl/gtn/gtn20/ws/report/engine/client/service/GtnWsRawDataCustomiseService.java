package com.stpl.gtn.gtn20.ws.report.engine.client.service;

import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn20.ws.report.engine.mongo.service.GtnWsMongoService;
import com.stpl.gtn.gtn2o.ws.report.engine.rawdatagenerator.GtnWsGenerateRawData;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsDiscountBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsProjectionDetailsValuesBean;

public class GtnWsRawDataCustomiseService {

	private static final GtnWsGenerateRawData RAW_DATA_INSTANCE = GtnWsGenerateRawData.getInstance();

	private static final GtnWsMongoService MONGO_SERVICE = GtnWsMongoService.getInstance();

	public void generateDataToMongo() {
		writeRawDataToMongo("testingpurpose", generateApprovedData());
	}

	private List<GtnWsProjectionBean> generateApprovedData() {
		List<Object[]> rawList = RAW_DATA_INSTANCE.generateRawData(0, 0);
		return customizeRawApprovedData(rawList);
	}

	private List<GtnWsProjectionBean> customizeRawApprovedData(List<Object[]> rawList) {
		System.out.println("setProjectionDetailsBean started ");
		long startTime = System.currentTimeMillis();
		String oldValue = "", newValue = "";
		String rsOldValue = "", rsNewValue = "";
		int oldPeriod = 0, newPeriod = 0;
		List<GtnWsProjectionBean> returnList = new ArrayList<>();
		GtnWsProjectionBean projectionBean = null;
		GtnWsProjectionDetailsValuesBean projectionDetailsValuesBean = null;
		GtnWsDiscountBean discountBean = null;
		int size = rawList.size();
		for (int i = 0; i < size; i++) {
			Object[] obj = rawList.get(i);
			newValue = obj[0] + "";
			rsNewValue = obj[2] + "";
			newPeriod = Integer.parseInt(obj[1] + "");
			if (!oldValue.equals(newValue)) {
				projectionBean = new GtnWsProjectionBean();
				projectionDetailsValuesBean = new GtnWsProjectionDetailsValuesBean();
				discountBean = new GtnWsDiscountBean();
				projectionBean.setCcpId(Integer.parseInt(newValue));
				projectionDetailsValuesBean.setPeriodSid(Integer.parseInt(obj[1] + ""));
				projectionDetailsValuesBean.setSalesActuals(getDoubleValue(obj[3]));
				projectionDetailsValuesBean.setSalesProjection(getDoubleValue(obj[5]));
				projectionDetailsValuesBean.setSalesUnitsActuals(getDoubleValue(obj[4]));
				projectionDetailsValuesBean.setSalesUnitsProjection(getDoubleValue(obj[6]));
				projectionDetailsValuesBean.setQuarter(getIntValue(obj[9]));
				projectionDetailsValuesBean.setSemiAnnual(getIntValue(obj[10]));
				projectionDetailsValuesBean.setYear(getIntValue(obj[11]));
				projectionDetailsValuesBean.setExfactoryActuals(getDoubleValue(obj[12]));
				projectionDetailsValuesBean.setExfactoryProjection(getDoubleValue(obj[13]));
				if (!"null".equals(rsNewValue)) {
					discountBean.setRsId(Integer.parseInt(rsNewValue));
					discountBean.setDiscountActuals(getDoubleValue(obj[7]));
					discountBean.setDiscountProjection(getDoubleValue(obj[8]));
					projectionDetailsValuesBean.addDiscountBean(discountBean);
				}
				projectionBean.addProjectionDetailsValues(projectionDetailsValuesBean);
				returnList.add(projectionBean);
			} else if (oldPeriod != newPeriod) {
				projectionDetailsValuesBean = new GtnWsProjectionDetailsValuesBean();
				projectionDetailsValuesBean.setPeriodSid(Integer.parseInt(obj[1] + ""));
				projectionDetailsValuesBean.setSalesActuals(getDoubleValue(obj[3]));
				projectionDetailsValuesBean.setSalesProjection(getDoubleValue(obj[5]));
				projectionDetailsValuesBean.setSalesUnitsActuals(getDoubleValue(obj[4]));
				projectionDetailsValuesBean.setSalesUnitsProjection(getDoubleValue(obj[6]));
				projectionDetailsValuesBean.setQuarter(getIntValue(obj[9]));
				projectionDetailsValuesBean.setSemiAnnual(getIntValue(obj[10]));
				projectionDetailsValuesBean.setYear(getIntValue(obj[11]));
				projectionDetailsValuesBean.setExfactoryActuals(getDoubleValue(obj[12]));
				projectionDetailsValuesBean.setExfactoryProjection(getDoubleValue(obj[13]));
				if (!"null".equals(rsNewValue) && !rsOldValue.equals(rsNewValue)) {
					discountBean = new GtnWsDiscountBean();
					discountBean.setRsId(Integer.parseInt(rsNewValue));
					discountBean.setDiscountActuals(getDoubleValue(obj[7]));
					discountBean.setDiscountProjection(getDoubleValue(obj[8]));
					projectionDetailsValuesBean.addDiscountBean(discountBean);
				}
				projectionBean.addProjectionDetailsValues(projectionDetailsValuesBean);
			} else if (!"null".equals(rsNewValue) && !rsOldValue.equals(rsNewValue)) {
				discountBean = new GtnWsDiscountBean();
				discountBean.setRsId(Integer.parseInt(rsNewValue));
				discountBean.setDiscountActuals(getDoubleValue(obj[7]));
				discountBean.setDiscountProjection(getDoubleValue(obj[8]));
				projectionDetailsValuesBean.addDiscountBean(discountBean);
			}
			oldValue = newValue;
			rsOldValue = rsNewValue;
			oldPeriod = newPeriod;
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Total Time taken to customize = " + totalTime);
		return returnList;
	}

	public Double getDoubleValue(Object obj) {
		if (obj == null) {
			return null;
		}
		return Double.parseDouble(obj.toString());
	}

	public int getIntValue(Object obj) {
		if (obj == null) {
			return 0;
		}
		return Integer.parseInt(obj.toString());
	}

	private void writeRawDataToMongo(String filename, List<GtnWsProjectionBean> rawData) {
		// JSON_SERVICE_INSTANCE.writeToJsonFile(filename, rawData);
                MongoCollection<GtnWsProjectionBean> collection = (MongoCollection<GtnWsProjectionBean>)
                        MONGO_SERVICE.getCollectionForCustomClass(filename, GtnWsProjectionBean.class);
                MONGO_SERVICE.insertManyRecordsToMongoDbUsingCustomClass(collection, rawData);
	}

}
