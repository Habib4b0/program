/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.report.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.client.FindIterable;
import com.stpl.gtn.gtn20.ws.report.engine.mongo.service.GtnWsMongoService;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.constants.MongoStringConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;

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
		FindIterable<Document> documents = gtnMongoService.fetchDataFromMongo(
				reportDashboardBean.getTableNameWithUniqueId(MongoStringConstants.COMPUTED_TREE_RESULTS), inputs,
				values);
		List<GtnWsRecordBean> data = new ArrayList<>();
		displayNodeValues(documents, data, gtnWsSearchRequest.getRecordHeader());
		return data;
	}

	public void displayNodeValues(FindIterable<Document> documents, List<GtnWsRecordBean> data,
			List<Object> recordHeader) {
		if (documents != null) {
			for (Document document : documents) {
				// System.out.println(gtnWsTreeNode.toString());
				data.add(convertBean(document, recordHeader));
				// if (gtnWsTreeNode.getChildren() != null) {
				// displayNodeValues(gtnWsTreeNode,data,recordHeader);
				// }
			}
		}
	}

	GtnWsRecordBean convertBean(Document document, List<Object> recordHeader) {

		GtnWsRecordBean bean = new GtnWsRecordBean();
		if (recordHeader == null || recordHeader.isEmpty()) {
			recordHeader.add("levelNumber");
			recordHeader.add("hierarchyNo");
			recordHeader.add("levelName");
			recordHeader.add("levelValue");
		        recordHeader.add("generatedHierarchyNo");
		}
		bean.setRecordHeader(recordHeader);
		bean.addProperties("levelNumber", document.get("levelNumber"));
		bean.addProperties("hierarchyNo", document.get("hierarchyNo"));
		bean.addProperties("levelName", document.get("levelName"));
		bean.addProperties("levelValue", document.get("levelValue"));
		bean.addProperties("generatedHierarchyNo", document.get("generatedHierarchyNo"));
		return bean;
	}
}
