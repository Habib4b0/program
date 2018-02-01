/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.commercial.constants.GtnWsCommercialForecastingConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.forecast.service.duallistbox.GtnWsReturnsDualListBoxService;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.duallistbox.GtnUIFrameworkWebserviceDualListBoxResponse;

@RestController
public class GtnWsReturnsDualListBoxController {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsReturnsDualListBoxController.class);

	@Autowired
	GtnWsReturnsDualListBoxService dualListBoxService;

	public GtnWsReturnsDualListBoxService getDualListBoxService() {
		return dualListBoxService;
	}

	public void setDualListBoxService(GtnWsReturnsDualListBoxService dualListBoxService) {
		this.dualListBoxService = dualListBoxService;
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FROECAST_DUAL_LIST_BOX_LEFT_TABLE_LOAD_SERVICE)
	public GtnUIFrameworkWebserviceResponse getLeftTableData(@RequestBody GtnUIFrameworkWebserviceRequest request) {
		/*
		 * 0 - "relationShipCombobox", 1 - "productLevelComboBox", 2 -
		 * "company", 3 - "businessUnit", 4 - "productGroup", 5 - "levelNo", 6 -
		 * "product selection", 7 - "returns" 8 - forecast session Id
		 */
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

		try {
                        @SuppressWarnings("unchecked")
			List<Object> inputList = new ArrayList(request.getGtnWsSearchRequest().getQueryInputList());
			GtnUIFrameworkWebserviceDualListBoxResponse dualListResponse = new GtnUIFrameworkWebserviceDualListBoxResponse();

			List<Object> newInputList = new ArrayList<>();
			newInputList.addAll(Arrays.asList(inputList.get(0).toString(), inputList.get(1).toString(),
					inputList.get(2).toString(), inputList.get(3).toString()));
			boolean checkForDataFlag = checkForData(newInputList);
			if (checkForDataFlag) {
				inputList.add(request.getGtnWsGeneralRequest().getUserId());
				inputList.add(request.getGtnWsSearchRequest().getSearchColumnNameList());

				if ((inputList.get(1) == null || StringUtils.isBlank(String.valueOf(inputList.get(1))))
						|| "0".equals(inputList.get(1))) {
					dualListResponse.setDualListBoxTableDataList(null);
				} else {
					String fileName = dualListBoxService.generateFile(inputList, StringUtils.EMPTY);
					List<GtnWsRecordBean> resultList = dualListBoxService.getLeftTableData(inputList, fileName);
					dualListResponse.setDualListBoxTableDataList(resultList);
					dualListResponse.setFileName(fileName);
				}
				gtnResponse.setGtnWsGeneralResponse(generateGeneralWsResopnse(true));
			}
			gtnResponse.setGtnUIFrameworkWebserviceDualListBoxResponse(dualListResponse);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			gtnResponse.setGtnWsGeneralResponse(generateGeneralWsResopnse(false));
		}
		return gtnResponse;
	}

	private boolean checkForData(List<Object> inputList) {
		for (int i = 0; i < inputList.size(); i++) {
			if ((inputList.get(i) == null || StringUtils.isBlank(String.valueOf(inputList.get(i))))
					|| "0".equals(inputList.get(i))) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FROECAST_DUAL_LIST_BOX_RIGHT_TABLE_LOAD_SERVICE)
	public GtnUIFrameworkWebserviceResponse getRightTableData(@RequestBody GtnUIFrameworkWebserviceRequest request) {
		/*
		 * 0 - flag (loadbulkdata or loaddata) 1 - inputfilterlist 2 - filename
		 * 3 - isMoveAll
		 */
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			List<Object> inputList = new ArrayList(request.getGtnWsSearchRequest().getQueryInputList());
			List<String> filterDataList = (List<String>) inputList.get(1);
			String fileName = (String) inputList.get(2);
			boolean isMoveAll = false;
			if (inputList.get(0).equals(GtnFrameworkWebserviceConstant.LOADDATA)) {
				isMoveAll = (boolean) inputList.get(3);
			}

			GtnUIFrameworkWebserviceDualListBoxResponse dualListResponse = new GtnUIFrameworkWebserviceDualListBoxResponse();
			dualListResponse
					.setDualListBoxTableDataList(dualListBoxService.getTreeData(filterDataList, fileName, isMoveAll));
			gtnResponse.setGtnUIFrameworkWebserviceDualListBoxResponse(dualListResponse);
			gtnResponse.setGtnWsGeneralResponse(generateGeneralWsResopnse(true));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			gtnResponse.setGtnWsGeneralResponse(generateGeneralWsResopnse(false));
		}
		return gtnResponse;
	}

	@PostMapping(value = GtnWsCommercialForecastingConstants.COMMERCIAL_FORECASTING_PROD_HIERARCHY_DUAL_LIST_BOX_LEFT_TABLE_LOAD_SERVICE)
	public GtnUIFrameworkWebserviceResponse getCommercialProdHierarchyLeftTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest request) {
		/*
		 * 0 - "relationShipCombobox", 1 - "productLevelComboBox", 2 -
		 * "company", 3 - "businessUnit", 4 - "productGroup", 5 - "levelNo", 6 -
		 * "product selection", 7 - "returns" 8 - forecast session Id
		 */

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

		try {
			List<Object> inputList = new ArrayList(request.getGtnWsSearchRequest().getQueryInputList());
			GtnUIFrameworkWebserviceDualListBoxResponse dualListResponse = new GtnUIFrameworkWebserviceDualListBoxResponse();

			List<Object> newInputList = new ArrayList<>();
			newInputList.addAll(Arrays.asList(inputList.get(0).toString(), inputList.get(1).toString(),
					inputList.get(2).toString(), inputList.get(3).toString()));
			boolean checkForDataFlag = checkForData(newInputList);
			if (checkForDataFlag) {
				inputList.add(request.getGtnWsGeneralRequest().getUserId());
				inputList.add(request.getGtnWsSearchRequest().getSearchColumnNameList());

				if ((inputList.get(1) == null || StringUtils.isBlank(String.valueOf(inputList.get(1))))
						|| "0".equals(inputList.get(1))) {
					dualListResponse.setDualListBoxTableDataList(null);
				} else {
					String fileName = dualListBoxService.generateFile(inputList, StringUtils.EMPTY);
					List<GtnWsRecordBean> resultList = dualListBoxService.getLeftTableData(inputList, fileName);
					dualListResponse.setDualListBoxTableDataList(resultList);
					dualListResponse.setFileName(fileName);
				}
				gtnResponse.setGtnWsGeneralResponse(generateGeneralWsResopnse(true));
			}
			gtnResponse.setGtnUIFrameworkWebserviceDualListBoxResponse(dualListResponse);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			gtnResponse.setGtnWsGeneralResponse(generateGeneralWsResopnse(false));
		}
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = GtnWsCommercialForecastingConstants.COMMERCIAL_FORECASTING_PROD_HIERARCHY_DUAL_LIST_BOX_RIGHT_TABLE_LOAD_SERVICE)
	public GtnUIFrameworkWebserviceResponse getCommercialProdHierarchyRightTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest request) {
		/*
		 * 0 - flag (loadbulkdata or loaddata) 1 - inputfilterlist 2 - filename
		 * 3 - isMoveAll
		 */
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			List<Object> inputList =new ArrayList(request.getGtnWsSearchRequest().getQueryInputList());
			List<String> filterDataList = (List<String>) inputList.get(1);
			String fileName = (String) inputList.get(2);
			boolean isMoveAll = false;
			if (inputList.get(0).equals(GtnFrameworkWebserviceConstant.LOADDATA)) {
				isMoveAll = (boolean) inputList.get(3);
			}

			GtnUIFrameworkWebserviceDualListBoxResponse dualListResponse = new GtnUIFrameworkWebserviceDualListBoxResponse();
			dualListResponse
					.setDualListBoxTableDataList(dualListBoxService.getTreeData(filterDataList, fileName, isMoveAll));
			gtnResponse.setGtnUIFrameworkWebserviceDualListBoxResponse(dualListResponse);
			gtnResponse.setGtnWsGeneralResponse(generateGeneralWsResopnse(true));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			gtnResponse.setGtnWsGeneralResponse(generateGeneralWsResopnse(false));
		}
		return gtnResponse;
	}

	@PostMapping(value = GtnWsCommercialForecastingConstants.COMMERCIAL_FORECASTING_CUST_HIERARCHY_DUAL_LIST_BOX_LEFT_TABLE_LOAD_SERVICE)
	public GtnUIFrameworkWebserviceResponse getCommercialCustHierachyLeftTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest request) {
		/*
		 * 0 - "relationShipCombobox", 1 - "productLevelComboBox", 2 -
		 * "company", 3 - "businessUnit", 4 - "productGroup", 5 - "levelNo", 6 -
		 * "product selection", 7 - "returns" 8 - forecast session Id
		 */
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

		try {
			List<Object> inputList = new ArrayList(request.getGtnWsSearchRequest().getQueryInputList());
			GtnUIFrameworkWebserviceDualListBoxResponse dualListResponse = new GtnUIFrameworkWebserviceDualListBoxResponse();

			List<Object> newInputList = new ArrayList<>();
			newInputList.addAll(Arrays.asList(inputList.get(0).toString(), inputList.get(1).toString()));
			boolean checkForDataFlag = checkForData(newInputList);
			if (checkForDataFlag) {
				inputList.add(request.getGtnWsGeneralRequest().getUserId());
				inputList.add(request.getGtnWsSearchRequest().getSearchColumnNameList());

				if ((inputList.get(1) == null || StringUtils.isBlank(String.valueOf(inputList.get(1))))
						|| "0".equals(inputList.get(1))) {
					dualListResponse.setDualListBoxTableDataList(null);
				} else {
					String fileName = dualListBoxService.generateFile(inputList, StringUtils.EMPTY);
					List<GtnWsRecordBean> resultList = dualListBoxService.getLeftTableData(inputList, fileName);
					dualListResponse.setDualListBoxTableDataList(resultList);
					dualListResponse.setFileName(fileName);
				}
				gtnResponse.setGtnWsGeneralResponse(generateGeneralWsResopnse(true));
			}
			gtnResponse.setGtnUIFrameworkWebserviceDualListBoxResponse(dualListResponse);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			gtnResponse.setGtnWsGeneralResponse(generateGeneralWsResopnse(false));
		}
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = GtnWsCommercialForecastingConstants.COMMERCIAL_FORECASTING_CUST_HIERARCHY_DUAL_LIST_BOX_RIGHT_TABLE_LOAD_SERVICE)
	public GtnUIFrameworkWebserviceResponse getCommercialCustHierarchyRightTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest request) {
		/*
		 * 0 - flag (loadbulkdata or loaddata) 1 - inputfilterlist 2 - filename
		 * 3 - isMoveAll
		 */
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			List<Object> inputList = new ArrayList(request.getGtnWsSearchRequest().getQueryInputList());
			List<String> filterDataList = (List<String>) inputList.get(1);
			String fileName = (String) inputList.get(2);
			boolean isMoveAll = false;
			if (inputList.get(0).equals(GtnFrameworkWebserviceConstant.LOADDATA)) {
				isMoveAll = (boolean) inputList.get(3);
			}

			GtnUIFrameworkWebserviceDualListBoxResponse dualListResponse = new GtnUIFrameworkWebserviceDualListBoxResponse();
			dualListResponse
					.setDualListBoxTableDataList(dualListBoxService.getTreeData(filterDataList, fileName, isMoveAll));
			gtnResponse.setGtnUIFrameworkWebserviceDualListBoxResponse(dualListResponse);
			gtnResponse.setGtnWsGeneralResponse(generateGeneralWsResopnse(true));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			gtnResponse.setGtnWsGeneralResponse(generateGeneralWsResopnse(false));
		}
		return gtnResponse;
	}

	private GtnWsGeneralResponse generateGeneralWsResopnse(boolean isSuccess) {
		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();
		generalWSResponse.setSucess(isSuccess);
		return generalWSResponse;
	}

}
