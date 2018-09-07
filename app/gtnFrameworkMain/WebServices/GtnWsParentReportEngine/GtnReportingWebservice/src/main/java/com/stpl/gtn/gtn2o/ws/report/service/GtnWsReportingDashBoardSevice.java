/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.report.service;

import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.mongodb.client.FindIterable;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsQueryConstants;

/**
 *
 * @author Karthik.Raja
 */
@Service
public class GtnWsReportingDashBoardSevice {

	public void displayNodeValues(FindIterable<Document> documents, List<GtnWsRecordBean> data,
			List<Object> recordHeader) {
		if (documents != null) {
			for (Document document : documents) {
				data.add(convertBean(document, recordHeader));
			}
		}
	}

	GtnWsRecordBean convertBean(Document document, List<Object> recordHeader) {

		GtnWsRecordBean bean = new GtnWsRecordBean();
		if (recordHeader.isEmpty()) {
			recordHeader.add(GtnWsQueryConstants.LEVEL_NUMBER);
			recordHeader.add(GtnWsQueryConstants.HIERARCHY_NO);
			recordHeader.add(GtnWsQueryConstants.LEVEL_NAME);
			recordHeader.add(GtnWsQueryConstants.LEVEL_VALUE);
			recordHeader.add(GtnWsQueryConstants.GENERATED_HIERARCHY_NO);
		}
		bean.setRecordHeader(recordHeader);
		bean.addProperties(GtnWsQueryConstants.LEVEL_NUMBER, document.get(GtnWsQueryConstants.LEVEL_NUMBER));
		bean.addProperties(GtnWsQueryConstants.HIERARCHY_NO, document.get(GtnWsQueryConstants.HIERARCHY_NO));
		bean.addProperties(GtnWsQueryConstants.LEVEL_NAME, document.get(GtnWsQueryConstants.LEVEL_NAME));
		bean.addProperties(GtnWsQueryConstants.LEVEL_VALUE, document.get(GtnWsQueryConstants.LEVEL_VALUE));
		bean.addProperties(GtnWsQueryConstants.GENERATED_HIERARCHY_NO,
				document.get(GtnWsQueryConstants.GENERATED_HIERARCHY_NO));
		return bean;
	}
}
