/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.report.service;

import com.mongodb.client.FindIterable;
import com.stpl.gtn.gtn20.ws.report.engine.mongo.service.GtnWsMongoService;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.constants.MongoStringConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Karthik.Raja
 */
@Service
public class GtnWsReportingDashBoardSevice {

	@Autowired
	GtnWsMongoService gtnMongoService;

	// public static void main(String[] args) {
	//// new GtnWsReportingDashBoardSevice().getDashboardLeftData();
	// }
	public List<GtnWsRecordBean> getDashboardLeftData(GtnWsSearchRequest gtnWsSearchRequest,
			GtnWsReportDashboardBean reportDashboardBean) {

		Object inputs[] = gtnWsSearchRequest.getQueryInput().toArray();
		Object values[] = gtnWsSearchRequest.getQueryInputList().toArray();
//		FindIterable<Document> documents = gtnMongoService.fetchDataFromMongo(
//				reportDashboardBean.getTableNameWithUniqueId(MongoStringConstants.COMPUTED_TREE_RESULTS), inputs,
//				values);
                FindIterable<Document> documents = gtnMongoService.fetchDataFromMongo(reportDashboardBean.getTableNameWithUniqueId(MongoStringConstants.COMPUTED_TREE_RESULTS), inputs,
				values,gtnWsSearchRequest.getTableRecordStart(),gtnWsSearchRequest.getTableRecordOffset(),"levelIndex");
		List<GtnWsRecordBean> data = new ArrayList<>();
		displayNodeValues(documents, data, gtnWsSearchRequest.getRecordHeader(),"S");
		return data;
	}

    public void displayNodeValues(FindIterable<Document> documents, List<GtnWsRecordBean> data,
            List<Object> recordHeader,String freq) {
        if (documents != null) {
            if (recordHeader == null || recordHeader.isEmpty()) {
                recordHeader.add("levelNumber");
                recordHeader.add("levelName");
                recordHeader.add("levelValue");
                recordHeader.add("generatedHierarchyNo");
                recordHeader.add("nodeIndex");
                recordHeader.add("levelIndex");
                recordHeader.add("childCount");
            }
            for (Document document : documents) {
                // System.out.println(gtnWsTreeNode.toString());
                data.add(convertBean(document, recordHeader,freq));
				// if (gtnWsTreeNode.getChildren() != null) {
                // displayNodeValues(gtnWsTreeNode,data,recordHeader);
                // }
            }
        }
    }

    GtnWsRecordBean convertBean(Document document, List<Object> recordHeader, String freq) {

        GtnWsRecordBean bean = new GtnWsRecordBean();
        String minus = "-";
        String tilt = "~";
        String year = "year";
        bean.setRecordHeader(recordHeader);
        bean.addProperties("levelNumber", document.get("levelNumber"));
        bean.addProperties("levelName", document.get("levelName"));
        bean.addProperties("levelValue", document.get("levelValue"));
        bean.addProperties("generatedHierarchyNo", document.get("generatedHierarchyNo"));
        bean.addProperties("nodeIndex", document.get("nodeIndex"));
        bean.addProperties("childCount", document.get("childCount"));
        bean.addProperties("levelIndex", document.get("levelIndex"));

        @SuppressWarnings("unchecked")
        List<Document> data = (List<Document>) document.get("attributes");
        String frequencyColumn = "";
        String frequencyValue = "S";
        if (freq.equals("S")) {
            frequencyColumn = "semiAnnual";
        }
        if (data != null) {
            for (Document dataNode : data) {
                String prop = frequencyValue + dataNode.get(frequencyColumn) + minus + dataNode.get(year) + tilt;
                for (String key : dataNode.keySet()) {
                    String property = prop + key;
                    if (bean.getRecordHeader().contains(property)) {
                        bean.addProperties(property, dataNode.get(key));
                    }
                }
            }
        }
        return bean;
    }
}
