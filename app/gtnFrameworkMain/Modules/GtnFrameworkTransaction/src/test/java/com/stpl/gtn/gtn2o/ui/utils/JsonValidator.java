package com.stpl.gtn.gtn2o.ui.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.stpl.gtn.gtn2o.ui.module.transaction.constants.GtnTransactionUIConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionColumnBean;

public class JsonValidator {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(JsonValidator.class);

	public void validateJsonViewIndex() {

		List<GtnWSTransactionColumnBean> transactionBeanList = new ArrayList<>();

		validateList(transactionBeanList);

	}

	private void validateDateProperty() {
		List<GtnWSTransactionColumnBean> transactionBeanList = readList("ActualsMaster.json");
		for (GtnWSTransactionColumnBean gtnWSTransactionColumnBean : transactionBeanList) {
			if ((gtnWSTransactionColumnBean.getColumnName().contains("date")
					|| gtnWSTransactionColumnBean.getColumnName().contains("Date"))
					&& !gtnWSTransactionColumnBean.getComponentType().equals(GtnTransactionUIConstants.JSON_DATEFIELD)
					&& (gtnWSTransactionColumnBean.getIsResultView()
							|| gtnWSTransactionColumnBean.isViewOnlyColumn())) {
				gtnLogger.debug(
						gtnWSTransactionColumnBean.getColumnName() + " is that type wants to be other than date ");
			}
		}
	}

	public void validateDateAndChange() {
		List<GtnWSTransactionColumnBean> transactionBeanList = readList("ActualsMaster.json");
		for (GtnWSTransactionColumnBean gtnWSTransactionColumnBean : transactionBeanList) {
			if ((gtnWSTransactionColumnBean.getColumnName().contains("date")
					|| gtnWSTransactionColumnBean.getColumnName().contains("Date"))
					&& !gtnWSTransactionColumnBean.getComponentType().equals(GtnTransactionUIConstants.JSON_DATEFIELD)
					&& (gtnWSTransactionColumnBean.getIsResultView()
							|| gtnWSTransactionColumnBean.isViewOnlyColumn())) {
				gtnWSTransactionColumnBean.setComponentType(GtnTransactionUIConstants.JSON_DATEFIELD);
			}
		}

		printAsJson(transactionBeanList);

	}

	private List<GtnWSTransactionColumnBean> readList(String filePath) {
		ObjectMapper mapper = new ObjectMapper();
		List<GtnWSTransactionColumnBean> transactionBeanList = null;
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			transactionBeanList = mapper.readValue(new File(filePath),
					mapper.getTypeFactory().constructCollectionType(List.class, GtnWSTransactionColumnBean.class));

		} catch (IOException e) {

			gtnLogger.error(e.getMessage());
		}
		return transactionBeanList;

	}

	private void validateList(List<GtnWSTransactionColumnBean> transactionBeanList) {
		List<Integer> indexList = new ArrayList<>();
		for (GtnWSTransactionColumnBean gtnWSTransactionColumnBean : transactionBeanList) {
			if (gtnWSTransactionColumnBean.getViewModeIndex() > 0 && gtnWSTransactionColumnBean.getIsResultView()) {
				if (indexList.contains(gtnWSTransactionColumnBean.getViewModeIndex())) {
					gtnLogger.debug("gtnWSTransactionColumnBean name -- " + gtnWSTransactionColumnBean.getColumnName()
							+ " with index" + gtnWSTransactionColumnBean.getViewModeIndex() + " Already exits");
				}
				indexList.add(gtnWSTransactionColumnBean.getViewModeIndex());
			} else if (gtnWSTransactionColumnBean.getIsResultView()) {
				gtnLogger.debug("No index for this " + gtnWSTransactionColumnBean.getColumnName());
			}
		}
		Collections.sort(indexList);

	}

	private void printAsJson(List<GtnWSTransactionColumnBean> transactionBeanList) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		validateList(transactionBeanList);
		try {
			gtnLogger.debug(mapper.writeValueAsString(transactionBeanList));
		} catch (JsonProcessingException e) {
			gtnLogger.error(e.getMessage());
		}
	}

	public void validateJsonViewIndexAndIncrement(String filePath) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		List<GtnWSTransactionColumnBean> transactionBeanList = new ArrayList<>();
		try {
			transactionBeanList = mapper.readValue(new File(filePath),
					mapper.getTypeFactory().constructCollectionType(List.class, GtnWSTransactionColumnBean.class));
		} catch (IOException e) {

			gtnLogger.error(e.getMessage());
		}
		List<Integer> indexList = new ArrayList<>();
		for (GtnWSTransactionColumnBean gtnWSTransactionColumnBean : transactionBeanList) {
			if (gtnWSTransactionColumnBean.getViewModeIndex() > 0 && gtnWSTransactionColumnBean.getIsResultView()) {
				if (indexList.contains(gtnWSTransactionColumnBean.getViewModeIndex())) {
					incrementFromehere(gtnWSTransactionColumnBean.getViewModeIndex() + 1, transactionBeanList);

				}
				indexList.add(gtnWSTransactionColumnBean.getViewModeIndex());
			} else if (gtnWSTransactionColumnBean.getIsResultView()) {
				gtnLogger.debug("No index for this " + gtnWSTransactionColumnBean.getColumnName());
			}
		}

		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		validateList(transactionBeanList);
		try {
			mapper.writeValueAsString(transactionBeanList);
		} catch (JsonProcessingException e) {
			gtnLogger.error(e.getMessage());
		}
	}

	private void incrementFromehere(int i, List<GtnWSTransactionColumnBean> transactionBeanList) {
		for (GtnWSTransactionColumnBean gtnWSTransactionColumnBean : transactionBeanList) {
			if (gtnWSTransactionColumnBean.getViewModeIndex() > i) {
				gtnWSTransactionColumnBean.setViewModeIndex(gtnWSTransactionColumnBean.getViewModeIndex() + 1);
			}
		}
	}

	public GtnWSTransactionColumnBean findForIndex(int i, List<GtnWSTransactionColumnBean> transactionBeanList) {
		for (GtnWSTransactionColumnBean gtnWSTransactionColumnBean : transactionBeanList) {
			if (gtnWSTransactionColumnBean.getViewModeIndex() == i) {
				return gtnWSTransactionColumnBean;
			}
		}
		return null;
	}

	public static void main(String[] arg) {
		new JsonValidator().validateDateProperty();
	}
}
