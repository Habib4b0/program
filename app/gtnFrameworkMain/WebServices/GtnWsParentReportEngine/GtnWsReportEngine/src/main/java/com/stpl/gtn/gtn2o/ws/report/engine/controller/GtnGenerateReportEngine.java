package com.stpl.gtn.gtn2o.ws.report.engine.controller;

import com.mongodb.client.MongoCollection;
import com.stpl.gtn.gtn2o.ws.report.engine.calculation.GtnWsMongoCalculation;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsTreeNode;
import org.bson.Document;

public class GtnGenerateReportEngine {

    public GtnWsTreeNode generateReportOutput(GtnWsTreeNode input) {

        GtnWsMongoCalculation mongoCalc = new GtnWsMongoCalculation();
        MongoCollection<Document> collection = mongoCalc.getCollection("calculation");
        mongoCalc.setRowValue(input, collection, null);
//        collection = mongoCalc.getCollection("calculation1");
//        mongoCalc.setRowValue(input, collection, null);
//        collection = mongoCalc.getCollection("calculation2");
//        mongoCalc.setRowValue(input, collection, null);
        mongoCalc.setVariableCategory(input, 0);
        return input;
    }
}
