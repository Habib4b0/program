package com.stpl.gtn.gtn2o.ws.report.engine.engine;

import com.stpl.gtn.gtn2o.ws.report.engine.calculation.GtnWsMongoCalculation;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;

public class GtnGenerateReportEngine {

    public GtnWsReportEngineTreeNode generateReportOutput(GtnWsReportEngineTreeNode input, GtnWsReportEngineBean engineBean) {

        GtnWsMongoCalculation mongoCalc = new GtnWsMongoCalculation();
        mongoCalc.setNodeData(input, engineBean);
        mongoCalc.setVariableCategory(input, engineBean);
        return input;
    }
}
