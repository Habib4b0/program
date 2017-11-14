/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsExcelHeaderBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.CommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnTreeTableLoadService;
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnWsTreeInput;
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnWsTreeNode;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.response.GtnWsExcelResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;

import net.sourceforge.jeval.EvaluationException;

/**
 *
 * @author Karthik.Raja
 */
@Service
public class GtnReturnsExcelLoadService {

	@Autowired
	GtnWsReturnsFileIOService gtnWsReturnsFileIOService;

	@Autowired
	GtnTreeTableLoadService gtnTreeTableLoadService;

	public GtnWsReturnsFileIOService getGtnWsReturnsFileIOService() {
		return gtnWsReturnsFileIOService;
	}

	public void setGtnWsReturnsFileIOService(GtnWsReturnsFileIOService gtnWsReturnsFileIOService) {
		this.gtnWsReturnsFileIOService = gtnWsReturnsFileIOService;
	}

	public GtnTreeTableLoadService getGtnTreeTableLoadService() {
		return gtnTreeTableLoadService;
	}

	public void setGtnTreeTableLoadService(GtnTreeTableLoadService gtnTreeTableLoadService) {
		this.gtnTreeTableLoadService = gtnTreeTableLoadService;
	}

	int grouprowCount = 0;
	int totalRowCount = 0;
	List<GtnWsRecordBean> excelResultList;
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnReturnsExcelLoadService.class);

	@SuppressWarnings("unchecked")
	public List<GtnWsRecordBean> loadBulkData(GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException, EvaluationException {

		String basePath = gtnWsReturnsFileIOService.getFilePath(gtnForecastBean.getModuleName(),
				gtnForecastBean.getUserId(), gtnForecastBean.getForecastSessionId(), gtnForecastBean.getTestFilePath());
		String finalFileName = basePath
				+ gtnWsReturnsFileIOService.getTreeFileName(gtnForecastBean.getHierarchyType(), null);
		GtnWsTreeNode rootNode = gtnWsReturnsFileIOService.readDataFromFile(finalFileName, GtnWsTreeNode.class);

		List<List<Object>> groupedDataList = gtnWsReturnsFileIOService.readJSONDataFromFile(basePath + "TreeData.stpl",
				List.class);

		Calendar calendar = gtnTreeTableLoadService.calculateHistoryPeriods(
				Integer.valueOf(gtnForecastBean.getSelectedHistory()), gtnForecastBean.getFrequency());

		GtnWsTreeInput gtnWsTreeInput = gtnTreeTableLoadService.generatReturnsTreeInput(gtnForecastBean);

		gtnWsTreeInput.setSelectedHistoryYear(calendar.get(Calendar.YEAR));
		gtnWsTreeInput.setSelectedHistoryMonth(calendar.get(Calendar.MONTH));

		if (CommonConstants.ANNUAL.equalsIgnoreCase(gtnForecastBean.getFrequency())) {
			gtnWsTreeInput.setFrequency(12);
		} else if (CommonConstants.SEMIANNUAL.equalsIgnoreCase(gtnForecastBean.getFrequency())) {
			gtnWsTreeInput.setFrequency(6);
		} else if (CommonConstants.QUARTERLY.equalsIgnoreCase(gtnForecastBean.getFrequency())) {
			gtnWsTreeInput.setFrequency(3);
		} else if (CommonConstants.MONTHLY.equalsIgnoreCase(gtnForecastBean.getFrequency())) {
			gtnWsTreeInput.setFrequency(1);
		}
		totalRowCount = 0;
		grouprowCount = 0;
		excelResultList = new ArrayList<>();
		getExcelDataList(gtnWsTreeInput, gtnForecastBean, groupedDataList, rootNode);
		return excelResultList;
	}

	private void getExcelDataList(GtnWsTreeInput gtsWsTreeInput, GtnForecastBean gtnForecastBean,
			List<List<Object>> groupedFileData, GtnWsTreeNode gtnWsTreeNode)
			throws EvaluationException, GtnFrameworkGeneralException {

		if (gtnWsTreeNode.getChildren() != null) {
			grouprowCount = 0;
			findGroupingRows(gtnWsTreeNode);
			getGeneratedBulkData(gtnForecastBean, gtsWsTreeInput, groupedFileData, gtnWsTreeNode, Boolean.TRUE);
			for (GtnWsTreeNode childNode : gtnWsTreeNode.getChildren()) {
				getExcelDataList(gtsWsTreeInput, gtnForecastBean, groupedFileData, childNode);

			}
		} else {
			getGeneratedBulkData(gtnForecastBean, gtsWsTreeInput, groupedFileData, gtnWsTreeNode, Boolean.FALSE);
		}

	}

	private void getGeneratedBulkData(GtnForecastBean gtnForecastBean, GtnWsTreeInput gtsWsTreeInput,
			List<List<Object>> groupedFileData, GtnWsTreeNode treeNode, Boolean isGroupingNeeded)
			throws GtnFrameworkGeneralException, EvaluationException {

		if (gtnForecastBean.getLevelFilter() > 0) {
			gtnTreeTableLoadService.getLevelFilterData(gtsWsTreeInput, gtnForecastBean, groupedFileData, treeNode,
					excelResultList, 0);
			return;
		}
		loadExcelDataFromFile(gtsWsTreeInput, groupedFileData, treeNode, isGroupingNeeded,
				gtnForecastBean.getRecordheader());
		return;

	}

	private void loadExcelDataFromFile(GtnWsTreeInput gtsWsTreeInput, List<List<Object>> groupedFileData,
			GtnWsTreeNode treeNode, Boolean isGroupingNeeded, List<Object> recordHeader) throws EvaluationException {
		if (StringUtils.isNotBlank(treeNode.getHierarchyNo())) {
			try {
				List<Object> rowList = groupedFileData.get(treeNode.getNodeIndex());
				GtnWsRecordBean gtnWsRecordBean = new GtnWsRecordBean();
				gtnWsRecordBean.addProperties(String.valueOf(treeNode.isCheckedNode()));
				gtnTreeTableLoadService.addMasterDataList(gtnWsRecordBean, rowList, gtsWsTreeInput);
				gtnWsRecordBean.getProperties().add(1, treeNode.getLevelValue());
				gtnWsRecordBean.setRecordHeader(recordHeader);
				gtnTreeTableLoadService.addActualAndForecastData(treeNode, gtnWsRecordBean, rowList, gtsWsTreeInput);
				putExcelProperties(gtnWsRecordBean, treeNode, isGroupingNeeded);
				excelResultList.add(gtnWsRecordBean);
			} catch (GtnFrameworkGeneralException ex) {
				LOGGER.error(ex.getMessage());
			}
		}
	}

	private void putExcelProperties(GtnWsRecordBean gtnWsRecordBean, GtnWsTreeNode gtnWsTreeNode,
			Boolean isGroupingNeeded) {
		if (StringUtils.isNotBlank(gtnWsTreeNode.getHierarchyNo())) {
			totalRowCount++;
			gtnWsRecordBean.addAdditionalProperty(isGroupingNeeded);
			gtnWsRecordBean.addAdditionalProperty(totalRowCount);
			gtnWsRecordBean.addAdditionalProperty(totalRowCount + grouprowCount - 1);
		}

	}

	private void findGroupingRows(GtnWsTreeNode gtnWsTreeNode) {
		grouprowCount += gtnWsTreeNode.getChildren().size();
		if (gtnWsTreeNode.getChildren() != null) {
			for (GtnWsTreeNode childNode : gtnWsTreeNode.getChildren()) {
				if (childNode.getChildren() != null) {
					findGroupingRows(childNode);
				}
			}
		}
	}

	public GtnWsExcelResponse convertForecastResponsetoExcelResponse(GtnWsForecastResponse header) {
		GtnWsExcelResponse excelresponse = new GtnWsExcelResponse();
		GtnWsExcelHeaderBean headerBean = new GtnWsExcelHeaderBean();
		excelresponse.setExcelHeaderBean(headerBean);
		headerBean.setSingleColumns(header.getSingleColumns());
		headerBean.setSingleHeaders(header.getSingleHeaders());
		excelresponse.setResultBeanList(header.getForecastPagedTableBeanList());
		headerBean.setExcelLeftTableEndIndex(header.getExcelLeftTableEndIndex());
		headerBean.setExcelSplitIndexList(header.getExcelSplitIndexList());
		headerBean.setExcelSplitWorksheetName(header.getExcelSplitWorksheetName());
		return excelresponse;
	}

}
