package com.stpl.gtn.gtn2o.ws.report.engine.service;

import com.stpl.gtn.gtn2o.ws.report.engine.rawdatagenerator.GtnWsGenerateRawData;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsDiscountBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsProjectionDetailsValuesBean;
import java.util.ArrayList;
import java.util.List;

public class GtnWsRawDataCustomiseService {

    private static final GtnWsGenerateRawData RAW_DATA_INSTANCE = GtnWsGenerateRawData.getInstance();

    public List<GtnWsProjectionBean> generateApprovedData() {
        List<Object[]> rawList = RAW_DATA_INSTANCE.getRawData(0, 0);
        List<GtnWsProjectionBean> customizedRawList = customizeRawApprovedData(rawList);
        return customizedRawList;
    }

    private List<GtnWsProjectionBean> customizeRawApprovedData(List<Object[]> rawList) {
        System.out.println("setProjectionDetailsBean started ");
        long startTime = System.currentTimeMillis();
        String oldValue = "", newValue = "";
        String rsOldValue = "", rsNewValue = "";
        int oldPeriod = 0, newPeriod = 0;
        List<GtnWsProjectionBean> returnList = new ArrayList<>();
        GtnWsProjectionBean bean = null;
        GtnWsProjectionDetailsValuesBean detailsBean = null;
        GtnWsDiscountBean discountBean = null;
        int size = rawList.size();
        for (int i = 0; i < size; i++) {
            Object[] obj = rawList.get(i);
            newValue = obj[0] + "";
            rsNewValue = obj[2] + "";
            newPeriod = Integer.parseInt(obj[1] + "");
            if (!oldValue.equals(newValue)) {
                bean = new GtnWsProjectionBean();
                detailsBean = new GtnWsProjectionDetailsValuesBean();
                discountBean = new GtnWsDiscountBean();
                bean.setCcpId(Integer.parseInt(newValue));
                detailsBean.setPeriodSid(Integer.parseInt(obj[1] + ""));
                detailsBean.setSalesActuals(setDoubleValue(obj[3]));
                detailsBean.setSalesProjection(setDoubleValue(obj[5]));
                detailsBean.setSalesUnitsActuals(setDoubleValue(obj[4]));
                detailsBean.setSalesUnitsProjection(setDoubleValue(obj[6]));
                detailsBean.setQuarter(setIntValue(obj[9]));
                detailsBean.setSemiAnnual(setIntValue(obj[10]));
                detailsBean.setYear(setIntValue(obj[11]));
                detailsBean.setExfactoryActuals(setDoubleValue(obj[12]));
                detailsBean.setExfactoryProjection(setDoubleValue(obj[13]));
                if (!"null".equals(rsNewValue)) {
                    discountBean.setRsId(Integer.parseInt(rsNewValue));
                    discountBean.setDiscountActuals(setDoubleValue(obj[7]));
                    discountBean.setDiscountProjection(setDoubleValue(obj[8]));
                    detailsBean.addDiscountBean(discountBean);
                }
                bean.addProjectionDetailsValues(detailsBean);
                returnList.add(bean);
            } else if (oldPeriod != newPeriod) {
                detailsBean = new GtnWsProjectionDetailsValuesBean();
                detailsBean.setPeriodSid(Integer.parseInt(obj[1] + ""));
                detailsBean.setSalesActuals(setDoubleValue(obj[3]));
                detailsBean.setSalesProjection(setDoubleValue(obj[5]));
                detailsBean.setSalesUnitsActuals(setDoubleValue(obj[4]));
                detailsBean.setSalesUnitsProjection(setDoubleValue(obj[6]));
                detailsBean.setQuarter(setIntValue(obj[9]));
                detailsBean.setSemiAnnual(setIntValue(obj[10]));
                detailsBean.setYear(setIntValue(obj[11]));
                detailsBean.setExfactoryActuals(setDoubleValue(obj[12]));
                detailsBean.setExfactoryProjection(setDoubleValue(obj[13]));
                if (!"null".equals(rsNewValue) && !rsOldValue.equals(rsNewValue)) {
                    discountBean = new GtnWsDiscountBean();
                    discountBean.setRsId(Integer.parseInt(rsNewValue));
                    discountBean.setDiscountActuals(setDoubleValue(obj[7]));
                    discountBean.setDiscountProjection(setDoubleValue(obj[8]));
                    detailsBean.addDiscountBean(discountBean);
                }
                bean.addProjectionDetailsValues(detailsBean);
            } else if (!"null".equals(rsNewValue) && !rsOldValue.equals(rsNewValue)) {
                discountBean = new GtnWsDiscountBean();
                discountBean.setRsId(Integer.parseInt(rsNewValue));
                discountBean.setDiscountActuals(setDoubleValue(obj[7]));
                discountBean.setDiscountProjection(setDoubleValue(obj[8]));
                detailsBean.addDiscountBean(discountBean);
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

    public Double setDoubleValue(Object obj) {
        if (obj == null) {
            return null;
        }
        return Double.parseDouble(obj.toString());

    }

    public int setIntValue(Object obj) {
        if (obj == null) {
            return 0;
        }
        return Integer.parseInt(obj.toString());

    }

}
