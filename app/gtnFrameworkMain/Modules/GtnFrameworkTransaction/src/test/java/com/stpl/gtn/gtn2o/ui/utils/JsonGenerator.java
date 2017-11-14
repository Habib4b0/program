package com.stpl.gtn.gtn2o.ui.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.stpl.gtn.gtn2o.ui.module.transaction.constants.GtnTransactionUIConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnUIFrameworkTransactionTabsheetBean;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionColumnBean;

public class JsonGenerator {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(JsonGenerator.class);

	public static void main(String[] args) {
		new JsonGenerator().generateTabsheetConfig("test.json");

	}

	public void generateJson() {
		String[] listViewColoumn = new String[] { "Account ID", "Program State Code", "Settlement No",
				"Accrual Actual End Date", "Actual ID", "Division ID", "Organization Key", "Actuals Master ID",
				"Quantity Inclusion", "Cash Paid Date", "Analysis Code", "Accrual Actual Start Date", "Sales Amount",
				"Quantity", "Sent Out", "Account No", "Account Name", "Provision ID", "Amount", "Market ID",
				"Is Active", "Settlement Method No", "Item Identifier Code Qualifier", "Price Adjustment Name",
				"Record Sequence", "Mandated Discount Amount", "Parentcom Divmkt Brand Prodkey", "Price",
				"Dispensed Date", "Item ID", "Accrual Processed", "Upload Date", "Invoice Line No", "Item No",
				"Acct Identifier Code Qualifier", "Contract ID", "Brand ID", "Com DivMkt Brand ProdKey",
				"Claim Indicator", "Provision Claim Indicator", "Source", "Batch ID" };
		List<GtnWSTransactionColumnBean> list = new ArrayList<>();
		for (String string : listViewColoumn) {
			GtnWSTransactionColumnBean bean = getTransactionBeanForListView(string);
			list.add(bean);

		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			gtnLogger.debug(mapper.writeValueAsString(list));
		} catch (JsonProcessingException e) {
			gtnLogger.error(e.getMessage());
		}

	}

	private void generateTabsheetConfig(String filePath) {
		GtnUIFrameworkTransactionTabsheetBean bean = new GtnUIFrameworkTransactionTabsheetBean();
		bean.setNoOfTabs(3);
		bean.setNoOfElementsInTabArray(new int[] { 2, 3, 2 });
		bean.setTabNameArray(new String[] { "Ads", "adas", "asdasd" });
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			mapper.writeValue(new File(filePath), bean);
		} catch (IOException e) {
			gtnLogger.error(e.getMessage());
		}
	}

	private GtnWSTransactionColumnBean getTransactionBeanForListView(String value) {
		GtnWSTransactionColumnBean bean = new GtnWSTransactionColumnBean();
		bean.setColumnID("");
		bean.setColumnName(value);
		bean.setIsResultView(true);
		bean.setComponentType(GtnTransactionUIConstants.JSON_TEXTBOX);
		return bean;

	}
}
