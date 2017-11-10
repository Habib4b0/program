package com.stpl.gtn.gtn2o.ws.module.itemgroups.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnWsItemGroupBean;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnWsItemGrpValidationBean;
import com.stpl.gtn.gtn2o.ws.itemgroup.constants.GtnWsItemGrpContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.itemgroups.config.GtnWebServiceItemGroupConfig;
import com.stpl.gtn.gtn2o.ws.module.itemgroups.service.GtnWsItemGrpService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.itemgroupreponse.GtnWsItemGroupResponse;

@RestController
@RequestMapping(value = GtnWsItemGrpContants.GTN_WS_ITEM_GRP_SERVICE)
public class GtnWsItemGrpCotroller {
	public GtnWsItemGrpCotroller() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsItemGrpCotroller.class);

	@Autowired
	private GtnWsItemGrpService itemGrpService;

	@PostMapping(value = GtnWsItemGrpContants.GTN_WS_ITEM_GRP_ADD_SERVICE)
	public GtnUIFrameworkWebserviceResponse itemAddService(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter itemAddService");
			count = itemGrpService.addItemQuery(gtnWsRequest);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting itemAddService Query", ex);
			return gtnResponse;
		} finally {
			logger.info("Exit itemAddService and inserted or updated " + count + GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@PostMapping(value = GtnWsItemGrpContants.GTN_WS_ITEM_GRP_ADDALL_SERVICE)
	public GtnUIFrameworkWebserviceResponse itemAddAllService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter itemAddAllService");
			count = itemGrpService.getAddAllQuery(gtnWsRequest);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting itemAddAllService Query", ex);
			return gtnResponse;
		} finally {
			logger.info("Exit itemAddAllService and deleted " + count + GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@PostMapping(value = GtnWsItemGrpContants.GTN_WS_ITEM_GRP_REMOVE_SERVICE)
	public GtnUIFrameworkWebserviceResponse companyRemoveService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter companyRemoveService");
			count = itemGrpService.getRemoveQuery(gtnWsRequest);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting companyRemoveService Query", ex);
			return gtnResponse;
		} finally {
			logger.info("Exit companyRemoveService and deleted " + count + GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@PostMapping(value = GtnWsItemGrpContants.GTN_WS_ITEM_GRP_REMOVEALL_SERVICE)
	public GtnUIFrameworkWebserviceResponse removeAllService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter removeAllService");
			count = itemGrpService.getRemoveAllQuery(gtnWsRequest);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting removeAllService Query", ex);
			return gtnResponse;
		} finally {
			logger.info("Exit removeAllService and inserted or updated " + count + GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@PostMapping(value = GtnWsItemGrpContants.GTN_WS_ITEM_GRP_FETCH_SERVICE)
	public GtnUIFrameworkWebserviceResponse fetchItemGrpInformation(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter companiesResultTableData");
			itemGrpService.getItemGrpFetch(gtnWsRequest, gtnResponse);
			itemGrpService.updateItemGrpDetails(gtnWsRequest);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting companiesResultTableData Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit companiesColumnUpdate ");
		}
	}

	@PostMapping(value = GtnWsItemGrpContants.GTN_WS_ITEM_GRP_COPY_SERVICE)
	public GtnUIFrameworkWebserviceResponse copyItemGrpInformation(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter copyItemGrpInformation");
			itemGrpService.updateItemGrpDetails(gtnWsRequest);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting copyItemGrpInformation Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit copyItemGrpInformation ");
		}
	}

	@PostMapping(value = GtnWsItemGrpContants.GTN_WS_ITEM_GRP_SAVE_SERVICE)
	public GtnUIFrameworkWebserviceResponse saveService(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

		GtnWsItemGroupBean itemGrpBean = gtnWsRequest.getGtnWsItemGroupRequest().getGtnWsItemGroupBean();
		Integer itemGrpSid = itemGrpBean.getItemGrpInfoBean().getItemGrpSid();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter saveService");
			if (itemGrpSid == null || itemGrpSid == 0) {
				itemGrpSid = itemGrpService.saveItemGrpQuery(itemGrpBean);
			} else {
				itemGrpService.updateItemGrpQuery(itemGrpBean);
			}
			count = itemGrpService.updateItemGrpDetailsTable(gtnWsRequest, itemGrpSid);
			itemGrpBean.getItemGrpInfoBean().setItemGrpSid(itemGrpSid);
			GtnWsItemGroupResponse gtnCompanyGroupResponse = new GtnWsItemGroupResponse();
			gtnCompanyGroupResponse.setGtnItemGrpInformationBean(itemGrpBean.getItemGrpInfoBean());
			gtnResponse.setGtnWsItemGroupResponse(gtnCompanyGroupResponse);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting saveService Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit saveService with sid :" + itemGrpSid + " and update in details :" + count);
		}
	}

	@PostMapping(value = GtnWsItemGrpContants.GTN_WS_ITEM_GRP_DELETE_SERVICE)
	public GtnUIFrameworkWebserviceResponse getCompanyGroupDelete(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getCompanyGroupDelete");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);

			count = itemGrpService.getItemGrpDeleteQuery(gtnWsRequest);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting getCompanyGroupDelete Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit getCompanyGroupDelete and deleted count-:" + count);
		}
	}

	@PostMapping(value = GtnWsItemGrpContants.GTN_WS_ITEM_GRP_COMMON_VALIDATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse validationItemGroupDeatilsTemp(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter validationItemGroupDeatilsTemp");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);

			count = itemGrpService.getItemGroupImtdCount(gtnWsRequest);
			GtnWsItemGrpValidationBean valBean = new GtnWsItemGrpValidationBean();
			valBean.setImtdCount(count);
			GtnWsItemGroupResponse itemGrpResponse = new GtnWsItemGroupResponse();
			itemGrpResponse.setGtnWsItemGrpValidationBean(valBean);
			gtnResponse.setGtnWsItemGroupResponse(itemGrpResponse);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting validationItemGroupDeatilsTemp Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit validationItemGroupDeatilsTemp count-:" + count);
		}
	}

	@PostMapping(value = GtnWsItemGrpContants.GTN_WS_ITEM_GRP_SELECTED_TABLE_SERVICE)
	public GtnUIFrameworkWebserviceResponse itemAdditionSearch(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse itemGroupResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			itemGroupResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			itemGroupResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter itemAdditionSearch");
			GtnSerachResponse itemGroupSerachResponse = new GtnSerachResponse();
			GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWebServiceItemGroupConfig()
					.getSearchQueryConfigMap().get("itemGrpAddTabSelectedSearchQuery");
			List<Object[]> itemGroupResultList = itemGrpService.getSelectedTableData(gtnWsRequest,
					gtnWebServiceSearchQueryConfig);
			if (gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				itemGroupSerachResponse.setCount(Integer.valueOf(String.valueOf(itemGroupResultList.get(0))));
			} else {
				itemGrpService.getCustomizedSearchFormFromObject(itemGroupResultList, gtnWebServiceSearchQueryConfig,
						gtnWsRequest.getGtnWsSearchRequest().getSearchColumnNameList());
				GtnUIFrameworkDataTable itemGroupDataTable = new GtnUIFrameworkDataTable();
				itemGroupDataTable.addData(itemGroupResultList);
				itemGroupSerachResponse.setResultSet(itemGroupDataTable);
			}
			itemGroupResponse.setGtnSerachResponse(itemGroupSerachResponse);
			return itemGroupResponse;
		} catch (Exception ex) {
			itemGroupResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting itemAdditionSearch Query", ex);
			itemGroupResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return itemGroupResponse;
		} finally {
			logger.info("Exit itemAdditionSearch");
		}
	}

	@PostMapping(value = GtnWsItemGrpContants.GTN_WS_ITEM_GRP_NAME_DUPLICATE_VALIDATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse getItemGrpNameDuplicateValidation(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter getItemGrpNameDuplicateValidation");
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			List<Object> resultList = itemGrpService.getItemGrpNameDuplicateValidationService(gtnWsRequest);
			boolean isitemGrpNameExists = false;
			if (resultList != null && !resultList.isEmpty()) {
				String returnValue = resultList.get(0).toString().trim();
				isitemGrpNameExists = !returnValue.isEmpty() && returnValue.contains("ITEM_GROUP_NAME");
			}
			GtnWsItemGroupResponse gtnWsItemGroupResponse = new GtnWsItemGroupResponse();
			gtnResponse.setGtnWsItemGroupResponse(gtnWsItemGroupResponse);
			GtnWsItemGrpValidationBean validationBean = new GtnWsItemGrpValidationBean();
			gtnWsItemGroupResponse.setGtnWsItemGrpValidationBean(validationBean);
			validationBean.setGroupNameExists(isitemGrpNameExists);
			gtnResponse.setGtnSerachResponse(gtnSerachResponse);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting getItemGrpNameDuplicateValidation Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit getItemGrpNameDuplicateValidation");
		}
	}

	@PostMapping(value = GtnWsItemGrpContants.GTN_WS_ITEM_GRP_TEMP_TABLE_CLEAR_SERVICE)
	public GtnUIFrameworkWebserviceResponse companyGroupTempTableClearService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter companyGroup Temp clear Sevice");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			itemGrpService.clearItemGroupTempTable(gtnWsRequest);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting Item Group Temp clear Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit companyGroup Temp clear with table ");
		}
	}
}
