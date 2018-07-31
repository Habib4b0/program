/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.customview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.customview.constants.GtnWsCustomViewConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.customview.service.GtnWsCustomViewService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsCustomViewResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

/**
 *
 * @author Lokeshwari.Kumarasam
 */
@RestController
@RequestMapping(value = GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SERVICE)
public class GtnWsCustomViewController {
	private static final String ENTERS_CUSTOM_VIEW_TREE_DATA = "Enters customViewTreeData";
	private static final String EXCEPTION_IN_CUSTOM_VIEW_TREE_DATA = "Exception in customViewTreeData";

	public GtnWsCustomViewController() {
		super();
	}

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private GtnWsCustomViewService logic;

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsCustomViewController.class);

	@SuppressWarnings("unchecked")
	@RequestMapping(value = GtnWsCustomViewConstants.GET_CUSTOM_VIEW_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCustomViewTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse tableDataResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			GtnSerachResponse tableLoadResponse = new GtnSerachResponse();
			String queryName = "getCustomViewCustomerHierarchyData";
			List<Object> list = gtnWsRequest.getGtnWsSearchRequest().getQueryInputList();
			List<Object[]> result = executeQuery(getQuery(queryName), list);
			GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
			gtnUIFrameworkDataTable.addData(result);
			tableLoadResponse.setResultSet(gtnUIFrameworkDataTable);
			tableDataResponse.setGtnSerachResponse(tableLoadResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex // | SQLException ex
		) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		tableDataResponse.setGtnWsGeneralResponse(generalResponse);
		return tableDataResponse;
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery, List paramList) throws GtnFrameworkGeneralException {
		return gtnSqlQueryEngine.executeSelectQuery(sqlQuery, paramList);
	}

	public String getQuery(String sqlId) {
		return gtnWsSqlService.getQuery(sqlId);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = GtnWsCustomViewConstants.GET_DEDUCTION_HIERARCHY_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getDeductionHierarchyTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse deductionHierResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			GtnSerachResponse deductionHierLoadResponse = new GtnSerachResponse();
			String queryName = "getCustomViewDeductionHierarchyData";
			List<Object> list = gtnWsRequest.getGtnWsSearchRequest().getQueryInputList();
			List<Object[]> result = executeQuery(getQuery(queryName), list);
			GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
			gtnUIFrameworkDataTable.addData(result);
			deductionHierLoadResponse.setResultSet(gtnUIFrameworkDataTable);
			deductionHierResponse.setGtnSerachResponse(deductionHierLoadResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex // | SQLException ex
		) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		deductionHierResponse.setGtnWsGeneralResponse(generalResponse);
		return deductionHierResponse;
	}

	@RequestMapping(value = GtnWsCustomViewConstants.CHECK_CUSTOM_VIEW_SAVE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse checkCustomViewSave(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enters checkCustomViewSave");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsCustomViewResponse cvResponse = new GtnWsCustomViewResponse();
			gtnResponse.setGtnWsCustomViewResponse(cvResponse);
			logic.checkCustomViewSave(gtnWsRequest.getGtnWsCustomViewRequest(), cvResponse);
		} catch (Exception ex) {
			logger.error("Exception in checkCustomViewSave", ex);
		}

		logger.info("Exit checkCustomViewSave");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsCustomViewConstants.CUSTOM_VIEW_SAVE_LOGIC, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse customViewSaveLogic(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enters customViewSaveLogic");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsCustomViewResponse(logic.saveCustomView(gtnWsRequest.getGtnWsCustomViewRequest()));
		} catch (Exception ex) {
			logger.error("Exception in customViewSaveLogic", ex);
		}

		logger.info("Exit customViewSaveLogic");
		return gtnResponse;
	}

	@RequestMapping(value = "deleteCustomViewReport", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse deleteCustomViewLogic(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enters customViewDeleteLogic");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsCustomViewResponse cvResponse = new GtnWsCustomViewResponse();
			cvResponse.setSuccess(logic.deleteCustViewMaster(gtnWsRequest.getGtnWsCustomViewRequest()));
			gtnResponse.setGtnWsCustomViewResponse(cvResponse);
		} catch (Exception ex) {
			logger.error("Exception in customViewDeleteLogic", ex);
		}

		logger.info("Exit customViewDeleteLogic");
		return gtnResponse;
	}

	@RequestMapping(value = "deleteCustomViewUserValidationReport", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse deleteCustomViewUserValidationLogic(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enters customViewDeletUserValidationLogic");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsCustomViewResponse cvResponse = new GtnWsCustomViewResponse();
			cvResponse.setSuccess(logic.validateCustViewUser(gtnWsRequest.getGtnWsCustomViewRequest()));
			gtnResponse.setGtnWsCustomViewResponse(cvResponse);
		} catch (Exception ex) {
			logger.error("Exception in customViewDeleteUserValidationLogic", ex);
		}

		logger.info("Exit customViewDeleteUserValidationLogic");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsCustomViewConstants.CUSTOM_VIEW_GET_TREE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse customViewTreeData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info(ENTERS_CUSTOM_VIEW_TREE_DATA);
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsCustomViewResponse cvResponse = new GtnWsCustomViewResponse();
			if (gtnWsRequest.getGtnWsCustomViewRequest().getCustomViewType().startsWith("report")) {
				cvResponse = logic.getCustViewMaster(gtnWsRequest.getGtnWsCustomViewRequest());
			} else {
				cvResponse.setCvTreeNodeList(logic.getSavedTreeData(gtnWsRequest.getGtnWsCustomViewRequest()));
			}
			gtnResponse.setGtnWsCustomViewResponse(cvResponse);
		} catch (Exception ex) {
			logger.error(EXCEPTION_IN_CUSTOM_VIEW_TREE_DATA, ex);
		}

		logger.info("Exit customViewTreeData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsCustomViewConstants.CUSTOM_VIEW_LEVEL_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse customViewlevelData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		logger.info(ENTERS_CUSTOM_VIEW_TREE_DATA);
		try {
			response.setGtnUIFrameworkWebserviceComboBoxResponse(
					logic.getCustomViewLevelData(gtnWsRequest.getGtnWsCustomViewRequest()));
			return response;
		} catch (Exception ex) {
			logger.error(EXCEPTION_IN_CUSTOM_VIEW_TREE_DATA, ex);
			return response;
		}
	}

	@RequestMapping(value = GtnWsCustomViewConstants.CUSTOM_VIEW_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse customViewData(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		logger.info(ENTERS_CUSTOM_VIEW_TREE_DATA);
		try {
			response.setGtnUIFrameworkWebserviceComboBoxResponse(logic.getCustomViewList());
			return response;
		} catch (Exception ex) {
			logger.error(EXCEPTION_IN_CUSTOM_VIEW_TREE_DATA, ex);
			return response;
		}
	}

	@RequestMapping(value = GtnWsCustomViewConstants.CUSTOM_VIEW_DELETE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse deleteCustomView(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info(ENTERS_CUSTOM_VIEW_TREE_DATA);
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsCustomViewResponse cvResponse = new GtnWsCustomViewResponse();
			logic.deleteCustomViewFromTable(gtnWsRequest.getGtnWsCustomViewRequest(), cvResponse);
			gtnResponse.setGtnWsCustomViewResponse(cvResponse);
		} catch (Exception ex) {
			logger.error("Exception in customViewDelete", ex);
		}

		logger.info("Exit customViewDelete");
		return gtnResponse;
	}
}
