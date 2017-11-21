/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.entity.contract.ImtdItemPriceRebateDetails;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.controller.GtnWsContractDashboardController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsExcelResponse;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnWsContractDashboardItemLogic {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsContractDashboardItemLogic.class);
	private final GtnWsContractDashboardController controller;
	private final GtnWsContractDashboardCommonLogic commonLogic;
	private static final String AND = " AND ";

	public GtnWsContractDashboardItemLogic(GtnWsContractDashboardController controller) {
		this.controller = controller;
		this.commonLogic = new GtnWsContractDashboardCommonLogic();
	}

	public GtnWsContractDashboardController getController() {
		return controller;
	}

	public String getQuery(String queryName) {
		return getController().getQuery(queryName);
	}

	public String getWhereClauseForAColumn(String expersion, String field, String value1, String value2)
			throws GtnFrameworkGeneralException {
		return getController().getWhereClauseForAColumn(expersion, field, value1, value2);
	}

	public GtnUIFrameworkWebserviceResponse getCDItemAdditionViewTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getCDItemAdditionViewCountQuery"
					: "getCDItemAdditionViewResultsQuery";
			List<Object> inputlist = getItemAdditionSearchInput(gtnWsRequest.getGtnWsSearchRequest());
			commonLogic.addUserIdSessionId(gtnWsRequest, inputlist);
			commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
		} catch (Exception ex) {
			logger.error("Exception in getCDItemAdditionViewTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDItemAdditionViewTableData", ex);
		}
		return gtnResponse;
	}

	private List<Object> getItemAdditionSearchInput(GtnWsSearchRequest searchRequest)
			throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		StringBuilder inputWhereConditions = new StringBuilder();
		try {
			for (int i = 0; i < searchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria searchCriteria = searchRequest.getGtnWebServiceSearchCriteriaList().get(i);

				if (searchCriteria.isFilter()) {
					String value = "%" + searchCriteria.getFilterValue1() + "%";
					inputWhereConditions.append(GtnFrameworkWebserviceConstant.AND_COLUMN)
							.append(getWhereClauseForAColumn("LIKE",
									getItemAdditionTabColumns(searchCriteria.getFieldId()), value, ""));
				} else if (i != 0) {
					String field = searchRequest.getGtnWebServiceSearchCriteriaList().get(i - 1).getFilterValue1()
							.trim();
					if ("Brand Name".equals(field)) {
						list.add("findCDBrand");
						inputWhereConditions.append(GtnFrameworkWebserviceConstant.AND_COLUMN)
								.append(getWhereClauseForItemAddition(searchCriteria.getFilterValue1()));
					} else {
						list.add(field.contains("IFP") ? "findCDIFP" : "findCDITEM");
						inputWhereConditions.append(GtnFrameworkWebserviceConstant.AND_COLUMN)
								.append(getWhereClauseForAColumn("LIKE", getItemAdditionTabColumns(field + "1"),
										searchCriteria.getFilterValue1(), ""));
					}
				}
			}

			list.add(inputWhereConditions);
			if (!searchRequest.isCount()) {
				list.add(getItemAdditionSortedInputs(searchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(searchRequest.getTableRecordStart());
				list.add(searchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getItemAdditionSearchInput", ex);
			throw new GtnFrameworkGeneralException("Error in getItemAdditionSearchInput : ", ex);

		}
		return list;
	}

	private String getItemAdditionSortedInputs(List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		String property = GtnFrameworkCommonConstants.ITEM_NO;
		String order = " ASC";
		if (gtnWebServiceOrderByCriteriaList != null && !gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			property = dto.getPropertyId();
			order = " " + dto.getOrderByCriteria();
		}
		return getItemAdditionTabColumns(property) + order;
	}

	private String getItemAdditionTabColumns(String property) {
		String columnName = commonLogic.getItemAdditionTabColumns(property);
		if (columnName == null) {
			columnName = "im.ITEM_NO";
		}
		return columnName;
	}

	private String getWhereClauseForItemAddition(String value) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"  im.BRAND_MASTER_SID in(select BMF.BRAND_MASTER_SID from BRAND_MASTER BMF where BMF.BRAND_NAME like '")
				.append(value.replace('*', '%')).append("') ");
		return sql.toString();
	}

	public GtnUIFrameworkWebserviceResponse getCDItemAdditionLeftTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			List<Object> inputlist = getItemAdditionSearchInput(gtnWsRequest.getGtnWsSearchRequest());
			String queryName = inputlist.remove(0).toString();
			String orderByQuery = "";
			if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				orderByQuery = getOrderByStartOffsetQuery(inputlist.remove(inputlist.size() - 3).toString(),
						inputlist.remove(inputlist.size() - 2).toString(),
						inputlist.remove(inputlist.size() - 1).toString());
			}
			inputlist.add(0, getQuery(gtnWsRequest.getGtnWsSearchRequest().isCount() ? "findCDItemAdditionCount"
					: "findCDItemAdditionResults"));
			commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName, orderByQuery);
		} catch (Exception ex) {
			logger.error("Exception in getCDItemAdditionLeftTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDItemAdditionLeftTableData", ex);
		}
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceResponse itemAdditionMoveRight(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		Session session = getController().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			GtnWsContractDashboardRequest cdRequest = gtnWsRequest.getGtnWsContractDashboardRequest();
			GtnWsGeneralRequest getRequest = gtnWsRequest.getGtnWsGeneralRequest();
			GtnWsRecordBean recordBean = gtnWsRequest.getGtnWsContractDashboardRequest().getTableBean();
			int itemMasterSid = recordBean.getProperties().get(9) == null ? 0 : recordBean.getIntegerPropertyByIndex(9);

			Criteria cr = session.createCriteria(ImtdItemPriceRebateDetails.class)
					.add(Restrictions.eq("itemMasterSid", itemMasterSid))
					.add(Restrictions.eq("usersSid", Integer.valueOf(getRequest.getUserId())))
					.add(Restrictions.eq("sessionId", getRequest.getSessionId()));
			List<ImtdItemPriceRebateDetails> results = cr.list();

			if (!results.isEmpty()) {
				ImtdItemPriceRebateDetails imtdItem = results.get(0);
				if ("D".equals(imtdItem.getOperation())) {
					imtdItem.setSource(recordBean.getStringPropertyByIndex(15));
					imtdItem.setOperation("U");
					session.saveOrUpdate(imtdItem);
				}
			} else {
				ImtdItemPriceRebateDetails imtdItem = new ImtdItemPriceRebateDetails();
				imtdItem.setAttachedDate(new Date());
				imtdItem.setItemId(recordBean.getStringPropertyByIndex(10));
				imtdItem.setItemName(recordBean.getStringPropertyByIndex(1));
				imtdItem.setItemNo(recordBean.getStringPropertyByIndex(0));
				imtdItem.setPackageSize(recordBean.getStringPropertyByIndex(13));
				String primaryUom = recordBean.getStringPropertyByIndex(14);
				if (StringUtils.isNotBlank(primaryUom) && !"0".equals(primaryUom)) {
					imtdItem.setPrimaryUom(Integer.valueOf(primaryUom));
				}
				String itemStatus = recordBean.getStringPropertyByIndex(11);
				imtdItem.setPsStatus(StringUtils.isBlank(itemStatus) ? 0 : Integer.valueOf(itemStatus));
				imtdItem.setItemMasterSid(itemMasterSid);
				imtdItem.setContractMasterSid(cdRequest.getContractId());
				imtdItem.setCfpModelSid(cdRequest.getCfpContractId());
				imtdItem.setIfpModelSid(cdRequest.getIfpContractId());
				imtdItem.setPsModelSid(cdRequest.getPsContractId());
				imtdItem.setRsModelSid(cdRequest.getRsContractId());
				imtdItem.setUsersSid(Integer.valueOf(getRequest.getUserId()));
				imtdItem.setSessionId(getRequest.getSessionId());
				imtdItem.setOperation("A");
				imtdItem.setImtdCreatedDate(new Date());
				imtdItem.setCreatedBy(Integer.valueOf(getRequest.getUserId()));
				imtdItem.setCreatedDate(new Date());
				imtdItem.setModifiedBy(Integer.valueOf(getRequest.getUserId()));
				imtdItem.setModifiedDate(new Date());
				imtdItem.setSource(recordBean.getStringPropertyByIndex(15));
				imtdItem.setBrandMasterSid(recordBean.getIntegerPropertyByIndex(12));
				session.saveOrUpdate(imtdItem);
			}
			tx.commit();

		} catch (Exception ex) {
			tx.rollback();
			logger.error("Exception in itemAdditionMoveRight", ex);
			throw new GtnFrameworkGeneralException("Exception in itemAdditionMoveRight", ex);
		} finally {
			session.close();
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse itemAdditionMoveLeft(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		Session session = getController().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			GtnWsRecordBean recordBean = gtnWsRequest.getGtnWsContractDashboardRequest().getTableBean();
			int imtdItemPriceRebateSid = recordBean.getProperties().get(9) == null ? 0
					: recordBean.getIntegerPropertyByIndex(9);
			ImtdItemPriceRebateDetails imtdItem = session.load(ImtdItemPriceRebateDetails.class,
					imtdItemPriceRebateSid);
			if ("U".equals(imtdItem.getOperation())) {
				imtdItem.setOperation("D");
				session.saveOrUpdate(imtdItem);
			} else {
				session.delete(imtdItem);
			}
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error("Exception in itemAdditionMoveLeft", ex);
			throw new GtnFrameworkGeneralException("Exception in itemAdditionMoveLeft", ex);
		} finally {
			session.close();
		}
		return gtnResponse;
	}

	private String getOrderByStartOffsetQuery(String column, String start, String offset) {
		StringBuilder sql = new StringBuilder();
		sql.append("  ORDER BY ").append(column).append(" OFFSET ").append(start).append(" ROWS FETCH NEXT ")
				.append(offset).append(" ROWS ONLY");
		return sql.toString();
	}

	private String getItemMoveAllRightQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		gtnWsRequest.getGtnWsSearchRequest().setCount(true);
		GtnWsContractDashboardRequest cdRequest = gtnWsRequest.getGtnWsContractDashboardRequest();
		List<Object> inputlist = getItemAdditionSearchInput(gtnWsRequest.getGtnWsSearchRequest());
		String queryName = inputlist.remove(0).toString();
		inputlist.add(0, getQuery("findCDItemAdditionResults"));
		String queryInput = controller.getQuery(inputlist, queryName);
		inputlist.clear();
		inputlist.add(gtnWsRequest.getGtnWsGeneralRequest().getUserId());
		inputlist.add(gtnWsRequest.getGtnWsGeneralRequest().getSessionId());
		inputlist.add(cdRequest.getContractId());
		inputlist.add(cdRequest.getCfpContractId());
		inputlist.add(cdRequest.getIfpContractId());
		inputlist.add(cdRequest.getPsContractId());
		inputlist.add(cdRequest.getRsContractId());
		inputlist.add(queryInput);
		return controller.getQuery(inputlist, "addAllCDItemAddition");

	}

	public GtnUIFrameworkWebserviceResponse itemAdditionMoveAllRight(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			getController().executeUpdateQuery(getItemMoveAllRightQuery(gtnWsRequest));
		} catch (Exception ex) {
			logger.error("Exception in itemAdditionMoveAllRight", ex);
			throw new GtnFrameworkGeneralException("Exception in itemAdditionMoveAllRight", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse itemAdditionMoveAllLeft(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsGeneralRequest getRequest = gtnWsRequest.getGtnWsGeneralRequest();
			List<String> inputList = Arrays.asList(getRequest.getUserId(), getRequest.getSessionId());
			getController().executeUpdateQuery(controller.getQuery(inputList, "updateOperationAllCDItemAddition"));
			getController().executeUpdateQuery(controller.getQuery(inputList, "removeAllCDItemAddition"));
		} catch (Exception ex) {
			logger.error("Exception in itemAdditionMoveAllLeft", ex);
			throw new GtnFrameworkGeneralException("Exception in itemAdditionMoveAllLeft", ex);
		}
		return gtnResponse;
	}

	private boolean skipCriteria(String field) {
		boolean ret = false;
		switch (field) {
		case "recordType":
		case "BasePrice":
			ret = true;
			break;
		default:
			ret = false;
		}
		return ret;
	}

	private List<Object> getItemsSearchInput(GtnWsSearchRequest itemSearchRequest, boolean isStartDateEndDate)
			throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		StringBuilder inputWhereConditions = new StringBuilder();
		String recordType = "";
		try {

			itemDetailsSearchInputTableData(itemSearchRequest, inputWhereConditions);

			if ((!itemSearchRequest.isCount()) && (!itemSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty())) {

				GtnWebServiceSearchCriteria searchCriteria = itemSearchRequest.getGtnWebServiceSearchCriteriaList()
						.get(0);
				if (!searchCriteria.isFilter()) {
					recordType = searchCriteria.getFilterValue1().replace("[", "").replace("]", "");
				}

			}
			String timeFilter = getCurrentHistoryFilter(recordType,
					isStartDateEndDate ? "ITEM_REBATE_START_DATE" : "START_DATE",
					isStartDateEndDate ? "ITEM_REBATE_END_DATE" : "END_DATE");
			if (!timeFilter.isEmpty()) {
				inputWhereConditions.append(timeFilter);
			}

			list.add(inputWhereConditions);
			if (!itemSearchRequest.isCount()) {
				list.add(getItemsDetailSortedInputs(itemSearchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(itemSearchRequest.getTableRecordStart());
				list.add(itemSearchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getItemsSearchInput", ex);
			throw new GtnFrameworkGeneralException("Error in getItemsSearchInput : ", ex);

		}
		return list;
	}

	private String getItemsDetailSortedInputs(List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		String property = GtnFrameworkCommonConstants.ITEM_NO;
		String order = " ASC";
		if (gtnWebServiceOrderByCriteriaList != null && !gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			property = dto.getPropertyId();
			order = " " + dto.getOrderByCriteria();
		}
		return getItemAdditionTabColumns(property) + order;
	}

	public GtnUIFrameworkWebserviceResponse getCDItemsDetailTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse, boolean isStartDateEndDate)
			throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getItemsDetailsCount"
					: "getItemsDetailsResults";
			itemDetailsTableData(gtnWsRequest, gtnResponse, queryName, isStartDateEndDate);
		} catch (Exception ex) {
			logger.error("Exception in getCDItemsDetailTableDataLogger", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDItemsDetailTableData", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse getCDItemsDetailViewTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse, boolean isStartDateEndDate)
			throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getItemsDetailsCount"
					: "getItemsDetailsViewResults";
			itemDetailsTableData(gtnWsRequest, gtnResponse, queryName, isStartDateEndDate);
		} catch (Exception ex) {
			logger.error("Exception in getCDItemsDetailViewTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDItemsDetailViewTableData", ex);
		}
		return gtnResponse;
	}

	private void itemDetailsTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse, String queryName, boolean isStartDateEndDate)
			throws GtnFrameworkGeneralException {
		List<Object> inputlist = getItemsSearchInput(gtnWsRequest.getGtnWsSearchRequest(), isStartDateEndDate);
		String catalog = getController().getSysSchemaCatalog();
		commonLogic.addUserIdSessionId(gtnWsRequest, inputlist);
		inputlist.add(0, catalog);
		inputlist.add(0, catalog);
		commonLogic.addInputRecordType(gtnWsRequest, inputlist);
		commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
	}

	public GtnUIFrameworkWebserviceResponse populateAllItems(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsContractDashboardRequest cdRequest = gtnWsRequest.getGtnWsContractDashboardRequest();
			GtnWsGeneralRequest genRequest = gtnWsRequest.getGtnWsGeneralRequest();
			List<String> inputlist = Arrays.asList(getItemAdditionTabColumns(cdRequest.getPopulateField()),
					getController().getPopulateValue(cdRequest.getPopulateField(), cdRequest.getPopulateValue()),
					genRequest.getUserId(), genRequest.getSessionId());
			getController().executeUpdateQuery(
					controller.getQuery(inputlist, GtnFrameworkWebserviceConstant.POPULATE_ITEMS_VALUE));
		} catch (Exception ex) {
			logger.error("Exception in populateAllItems", ex);
			throw new GtnFrameworkGeneralException("Exception in populateAllItems", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse populateItem(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsContractDashboardRequest cdRequest = gtnWsRequest.getGtnWsContractDashboardRequest();
			GtnWsGeneralRequest genRequest = gtnWsRequest.getGtnWsGeneralRequest();
			List<String> inputlist = Arrays.asList(getItemAdditionTabColumns(cdRequest.getPopulateField()),
					getController().getPopulateValue(cdRequest.getPopulateField(), cdRequest.getPopulateValue()),
					genRequest.getUserId(), genRequest.getSessionId());
			getController().executeUpdateQuery(
					controller.getQuery(inputlist, GtnFrameworkWebserviceConstant.POPULATE_ITEMS_VALUE)
							+ " AND CHECK_RECORD='1'");
		} catch (Exception ex) {
			logger.error("Exception in populateItem", ex);
			throw new GtnFrameworkGeneralException("Exception in populateItem", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse populateItemField(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsContractDashboardRequest cdRequest = gtnWsRequest.getGtnWsContractDashboardRequest();
			GtnWsGeneralRequest genRequest = gtnWsRequest.getGtnWsGeneralRequest();
			List<String> inputlist = Arrays.asList(getItemAdditionTabColumns(cdRequest.getPopulateField()),
					getController().getPopulateValue(cdRequest.getPopulateField(), cdRequest.getPopulateValue()),
					genRequest.getUserId(), genRequest.getSessionId());
			getController().executeUpdateQuery(
					controller.getQuery(inputlist, GtnFrameworkWebserviceConstant.POPULATE_ITEMS_VALUE)
							+ " AND IMTD_ITEM_PRICE_REBATE_SID='" + cdRequest.getSystemId() + "'");
		} catch (Exception ex) {
			logger.error("Exception in populateItemField", ex);
			throw new GtnFrameworkGeneralException("Exception in populateItemField", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse getCDPricingDetailTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getPricingDetailsCount"
					: "getPricingDetailsResults";
			pricingDetailsDataTable(gtnWsRequest, gtnResponse, queryName);
		} catch (Exception ex) {
			logger.error("Exception in getCDPricingDetailTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDPricingDetailTableData", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse getCDPricingDetailViewTableData(
			GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getPricingDetailsCount"
					: "getPricingDetailsViewResults";
			pricingDetailsDataTable(gtnWsRequest, gtnResponse, queryName);
		} catch (Exception ex) {
			logger.error("Exception in getCDPricingDetailViewTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDPricingDetailViewTableData", ex);
		}
		return gtnResponse;
	}

	private void pricingDetailsDataTable(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse, String queryName) throws GtnFrameworkGeneralException {
		List<Object> inputlist = getItemsSearchPricingInput(gtnWsRequest.getGtnWsSearchRequest());
		String catalog = getController().getSysSchemaCatalog();
		commonLogic.addUserIdSessionId(gtnWsRequest, inputlist);
		inputlist.add(0, catalog);
		commonLogic.addInputItemSId(gtnWsRequest, inputlist);
		commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
	}

	public GtnUIFrameworkWebserviceResponse getCDPricingProtectionTableData(
			GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getPricingProtectionCount"
					: "getPricingProtectionResults";
			itemPriceProtectionProcess(gtnWsRequest, gtnResponse, queryName);
		} catch (Exception ex) {
			logger.error("Exception in getCDPricingProtectionTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDPricingProtectionTableData", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse getCDPricingProtectionExcelData(
			GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException {
		GtnWsExcelResponse excelResponse = new GtnWsExcelResponse();
		String query = getQuery("getPricingProtectionExcelResults");
		List<?> recordHeader = (List<?>) gtnWsRequest.getGtnwsExcelRequest().getInputs()[0];
		List<?> excelData = getController().executeQuery(query,
				new Object[] { gtnWsRequest.getGtnwsExcelRequest().getInputs()[1],
						gtnWsRequest.getGtnwsExcelRequest().getInputs()[2] },
				new GtnFrameworkDataType[] { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING });
		excelResponse.setResultBeanList(loadGtnReadBean(recordHeader, excelData));
		gtnResponse.setGtnWsExcelResponse(excelResponse);
		return gtnResponse;
	}

	private List<GtnWsRecordBean> loadGtnReadBean(List<?> recordHeader, List<?> excelData) {
		List<GtnWsRecordBean> excelRecordBeanList = new ArrayList<>();
		for (int i = 0; i < excelData.size(); i++) {
			GtnWsRecordBean excelBean = new GtnWsRecordBean();
			Object[] dataArray = (Object[]) excelData.get(i);
			excelBean.setRecordHeader(recordHeader);
			excelBean.setProperties(Arrays.asList(dataArray));
			excelRecordBeanList.add(excelBean);
		}
		return excelRecordBeanList;

	}

	public GtnUIFrameworkWebserviceResponse getCDPricingProtectionViewTableData(
			GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getPricingProtectionCount"
					: "getPricingProtectionViewResults";
			itemProcess(gtnWsRequest, gtnResponse, queryName, false);
		} catch (Exception ex) {
			logger.error("Exception in getCDPricingProtectionViewTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDPricingProtectionViewTableData", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse getCDRebateDetailTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getRebateDetailsCount"
					: "getRebateDetailsResults";
			itemRebateProcess(gtnWsRequest, gtnResponse, queryName);
		} catch (Exception ex) {
			logger.error("Exception in getCDRebateDetailTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDRebateDetailTableData", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse getCDRebateDetailViewTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getRebateDetailsViewCount"
					: "getRebateDetailsViewResults";
			itemProcess(gtnWsRequest, gtnResponse, queryName, true);
		} catch (Exception ex) {
			logger.error("Exception in getCDRebateDetailViewTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDRebateDetailViewTableData", ex);
		}
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceResponse getCDItemsDetailPendingTableData(
			GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException {
		try {
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getItemsDetailsPendingCount"
					: "getItemsDetailsPendingResults";
			List<Object> itemSearchInputlist = getItemsSearchPendingInput(gtnWsRequest.getGtnWsSearchRequest());
			String catalog = getController().getSysSchemaCatalog();
			String recordType = "";
			String contractId = "";
			if (!gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {
				GtnWebServiceSearchCriteria cdItemSearchCriteria = gtnWsRequest.getGtnWsSearchRequest()
						.getGtnWebServiceSearchCriteriaList().get(0);
				GtnWebServiceSearchCriteria cdItemSearchCriteriaforId = gtnWsRequest.getGtnWsSearchRequest()
						.getGtnWebServiceSearchCriteriaList().get(1);
				if (!cdItemSearchCriteria.isFilter()) {
					recordType = cdItemSearchCriteria.getFilterValue1().replace("[", "").replace("]", "");
				}
				if (!cdItemSearchCriteriaforId.isFilter()) {
					contractId = cdItemSearchCriteriaforId.getFilterValue1();
				}
			}
			itemSearchInputlist.add(0, catalog);
			itemSearchInputlist.add(0, catalog);

			if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				itemSearchInputlist.add(0, recordType);
			}
			itemSearchInputlist.add(0, contractId);
			List<Object[]> result = getController().executeQuery(controller.getQuery(itemSearchInputlist, queryName));
			if (gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				gtnSerachResponse.setCount(Integer.valueOf(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable cdPendingItemsDataTable = new GtnUIFrameworkDataTable();
				cdPendingItemsDataTable.addData(result);
				gtnSerachResponse.setResultSet(cdPendingItemsDataTable);
			}
			gtnResponse.setGtnSerachResponse(gtnSerachResponse);
		} catch (Exception ex) {
			logger.error("Exception in getCDItemsDetailPendingTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDItemsDetailPendingTableData", ex);
		}
		return gtnResponse;
	}

	private List<Object> getItemsSearchPendingInput(GtnWsSearchRequest cdItemPendingSearchRequest)
			throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		StringBuilder inputWhereConditions = new StringBuilder();
		try {
			for (int i = 0; i < cdItemPendingSearchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria cdItemPendingSearchCriteria = cdItemPendingSearchRequest
						.getGtnWebServiceSearchCriteriaList().get(i);
				if (cdItemPendingSearchCriteria.isFilter() && !skipCriteria(cdItemPendingSearchCriteria.getFieldId())) {
					StringBuilder value = new StringBuilder(cdItemPendingSearchCriteria.getFilterValue1());
					if ("LIKE".equalsIgnoreCase(cdItemPendingSearchCriteria.getExpression())) {
						value.append("%" + value + "%");
					}
					inputWhereConditions.append(GtnFrameworkWebserviceConstant.AND_COLUMN
							+ getWhereClauseForAColumn(cdItemPendingSearchCriteria.getExpression(),
									getItemAdditionTabColumns(cdItemPendingSearchCriteria.getFieldId()),
									value.toString(), cdItemPendingSearchCriteria.getFilterValue2()));
				}
			}
			if (!cdItemPendingSearchRequest.isCount()) {
				list.add(inputWhereConditions);
				list.add(getItemsDetailSortedInputs(cdItemPendingSearchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(cdItemPendingSearchRequest.getTableRecordStart());
				list.add(cdItemPendingSearchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getItemsSearchPendingInput", ex);
			throw new GtnFrameworkGeneralException("Error in getItemsSearchPendingInput : ", ex);

		}
		return list;
	}

	private void itemProcess(GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse gtnResponse,
			String queryName, boolean isStartDateEndDate) throws GtnFrameworkGeneralException {
		List<Object> inputlist = getItemsSearchInput(gtnWsRequest.getGtnWsSearchRequest(), isStartDateEndDate);
		commonLogic.addUserIdSessionId(gtnWsRequest, inputlist);
		commonLogic.addInputItemSId(gtnWsRequest, inputlist);
		commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
	}

	private void itemRebateProcess(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse, String queryName) throws GtnFrameworkGeneralException {
		List<Object> inputlist = getItemsRebateSearchInput(gtnWsRequest.getGtnWsSearchRequest());
		commonLogic.addUserIdSessionId(gtnWsRequest, inputlist);
		commonLogic.addInputItemSId(gtnWsRequest, inputlist);
		commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
	}

	private List<Object> getItemsRebateSearchInput(GtnWsSearchRequest itemRebateSearchRequest)
			throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		StringBuilder inputWhereConditions = new StringBuilder();
		String recordType = "";
		try {
			for (int i = 0; i < itemRebateSearchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria itemRebateSearchCriteria = itemRebateSearchRequest
						.getGtnWebServiceSearchCriteriaList().get(i);
				if (itemRebateSearchCriteria.isFilter() && !skipCriteria(itemRebateSearchCriteria.getFieldId())) {
					StringBuilder value = new StringBuilder(itemRebateSearchCriteria.getFilterValue1());
					if ("LIKE".equalsIgnoreCase(itemRebateSearchCriteria.getExpression())) {
						value.append("%" + value + "%");
					}
					inputWhereConditions.append(GtnFrameworkWebserviceConstant.AND_COLUMN
							+ getWhereClauseForAColumn(itemRebateSearchCriteria.getExpression(),
									getItemAdditionTabColumns(itemRebateSearchCriteria.getFieldId()), value.toString(),
									itemRebateSearchCriteria.getFilterValue2()));
				}
			}

			if ((!itemRebateSearchRequest.isCount())
					&& (!itemRebateSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty())) {

				GtnWebServiceSearchCriteria searchCriteria = itemRebateSearchRequest
						.getGtnWebServiceSearchCriteriaList().get(0);
				if (!searchCriteria.isFilter()) {
					recordType = searchCriteria.getFilterValue1().replace("[", "").replace("]", "");
				}

			}
			String timeFilter = getCurrentHistoryFilter(recordType, "ITEM_REBATE_START_DATE", "ITEM_REBATE_END_DATE");
			if (!timeFilter.isEmpty()) {
				inputWhereConditions.append(timeFilter);
			}

			list.add(inputWhereConditions);
			if (!itemRebateSearchRequest.isCount()) {
				list.add(getItemsDetailSortedInputs(itemRebateSearchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(itemRebateSearchRequest.getTableRecordStart());
				list.add(itemRebateSearchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getItemsPendingSearchInput", ex);
			throw new GtnFrameworkGeneralException("Error in getItemsPendingSearchInput : ", ex);

		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceResponse getCDRebateDetailPendingTableData(
			GtnUIFrameworkWebserviceRequest cdRebatePendingRequest, GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException {
		try {
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			String queryName = cdRebatePendingRequest.getGtnWsSearchRequest().isCount() ? "getRebateDetailsPendingCount"
					: "getRebateDetailsPendingResults";
			List<Object> inputlist = getItemsSearchRebateInput(cdRebatePendingRequest.getGtnWsSearchRequest());
			String recordType = "";
			String contractId = "";

			if (!cdRebatePendingRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {
				GtnWebServiceSearchCriteria cdRebatePendingearchCriteria = cdRebatePendingRequest
						.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(0);
				GtnWebServiceSearchCriteria searchCriteriaforId = cdRebatePendingRequest.getGtnWsSearchRequest()
						.getGtnWebServiceSearchCriteriaList().get(1);
				if (!cdRebatePendingearchCriteria.isFilter()) {
					recordType = cdRebatePendingearchCriteria.getFilterValue1().replace("[", "").replace("]", "");
				}
				if (!searchCriteriaforId.isFilter()) {
					contractId = searchCriteriaforId.getFilterValue1();
				}
			}
			if (!cdRebatePendingRequest.getGtnWsSearchRequest().isCount()) {
				inputlist.add(0, recordType);

				String orderByColumn = inputlist.get(inputlist.size() - 3).toString();
				if (orderByColumn.startsWith("im")) {
					inputlist.set(inputlist.size() - 3, GtnFrameworkWebserviceConstant.IMTDITEM_MASTER_SID);
				}
			}

			inputlist.add(0, contractId);
			List<Object[]> result = getController().executeQuery(controller.getQuery(inputlist, queryName));
			if (cdRebatePendingRequest.getGtnWsSearchRequest().isCount()) {
				gtnSerachResponse.setCount(Integer.valueOf(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable cdPendingRebateDataTable = new GtnUIFrameworkDataTable();
				cdPendingRebateDataTable.addData(result);
				gtnSerachResponse.setResultSet(cdPendingRebateDataTable);
			}
			gtnResponse.setGtnSerachResponse(gtnSerachResponse);
		} catch (Exception ex) {
			logger.error("Exception in getCDRebateDetailPendingTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDRebateDetailPendingTableData", ex);
		}
		return gtnResponse;
	}

	private List<Object> getItemsSearchRebateInput(GtnWsSearchRequest cdItemRebateSearchRequest)
			throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		StringBuilder inputWhereConditions = new StringBuilder();
		try {
			for (int i = 0; i < cdItemRebateSearchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria cdItemRebateSearchCriteria = cdItemRebateSearchRequest
						.getGtnWebServiceSearchCriteriaList().get(i);
				if (cdItemRebateSearchCriteria.isFilter() && !skipCriteria(cdItemRebateSearchCriteria.getFieldId())) {
					StringBuilder value = new StringBuilder(cdItemRebateSearchCriteria.getFilterValue1());
					if ("LIKE".equalsIgnoreCase(cdItemRebateSearchCriteria.getExpression())) {
						value.append("%" + value + "%");
					}
					inputWhereConditions.append(GtnFrameworkWebserviceConstant.AND_COLUMN
							+ getWhereClauseForAColumn(cdItemRebateSearchCriteria.getExpression(),
									getItemAdditionTabColumns(cdItemRebateSearchCriteria.getFieldId()),
									value.toString(), cdItemRebateSearchCriteria.getFilterValue2()));
				}
			}
			if (!cdItemRebateSearchRequest.isCount()) {
				list.add(inputWhereConditions);
				list.add(getItemsDetailSortedInputs(cdItemRebateSearchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(cdItemRebateSearchRequest.getTableRecordStart());
				list.add(cdItemRebateSearchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getItemsSearchRebateInput", ex);
			throw new GtnFrameworkGeneralException("Error in getItemsSearchRebateInput : ", ex);

		}
		return list;
	}

	private List<Object> getItemsSearchPricingInput(GtnWsSearchRequest cdItemPricingSearchRequest)
			throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		StringBuilder inputWhereConditions = new StringBuilder();
		String recordType = "";
		try {
			for (int i = 0; i < cdItemPricingSearchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria cdItemPricingSearchCriteria = cdItemPricingSearchRequest
						.getGtnWebServiceSearchCriteriaList().get(i);
				if (cdItemPricingSearchCriteria.isFilter() && !skipCriteria(cdItemPricingSearchCriteria.getFieldId())) {
					StringBuilder value = new StringBuilder(cdItemPricingSearchCriteria.getFilterValue1());
					if ("LIKE".equalsIgnoreCase(cdItemPricingSearchCriteria.getExpression())) {
						value.append("%" + value + "%");
					}
					inputWhereConditions.append(GtnFrameworkWebserviceConstant.AND_COLUMN
							+ getWhereClauseForAColumn(cdItemPricingSearchCriteria.getExpression(),
									getItemAdditionTabColumns(cdItemPricingSearchCriteria.getFieldId()),
									value.toString(), cdItemPricingSearchCriteria.getFilterValue2()));
				}
			}

			if ((!cdItemPricingSearchRequest.isCount())
					&& (!cdItemPricingSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty())) {
				GtnWebServiceSearchCriteria searchCriteria = cdItemPricingSearchRequest
						.getGtnWebServiceSearchCriteriaList().get(0);
				if (!searchCriteria.isFilter()) {
					recordType = searchCriteria.getFilterValue1().replace("[", "").replace("]", "");
				}
			}
			String timeFilter = getCurrentHistoryFilter(recordType, "CONTRACT_PRICE_START_DATE",
					"CONTRACT_PRICE_END_DATE");
			if (!timeFilter.isEmpty()) {
				inputWhereConditions.append(timeFilter);
			}

			list.add(inputWhereConditions);
			if (!cdItemPricingSearchRequest.isCount()) {
				list.add(getItemsDetailSortedInputs(cdItemPricingSearchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(cdItemPricingSearchRequest.getTableRecordStart());
				list.add(cdItemPricingSearchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getItemsSearchPricingInput", ex);
			throw new GtnFrameworkGeneralException("Error in getItemsSearchPricingInput : ", ex);

		}
		return list;
	}

	private void itemPriceProtectionProcess(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse, String queryName) throws GtnFrameworkGeneralException {
		List<Object> inputlist = getItemsSearchPriceProtectionInput(gtnWsRequest.getGtnWsSearchRequest());
		commonLogic.addUserIdSessionId(gtnWsRequest, inputlist);
		commonLogic.addInputItemSId(gtnWsRequest, inputlist);
		commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
	}

	private List<Object> getItemsSearchPriceProtectionInput(GtnWsSearchRequest itemPriceProtectionSearchRequest)
			throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		StringBuilder inputWhereConditions = new StringBuilder();
		String recordType = "";
		try {
			for (int i = 0; i < itemPriceProtectionSearchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria itemPriceProtectionSearchCriteria = itemPriceProtectionSearchRequest
						.getGtnWebServiceSearchCriteriaList().get(i);
				if (itemPriceProtectionSearchCriteria.isFilter()
						&& !skipCriteria(itemPriceProtectionSearchCriteria.getFieldId())) {
					StringBuilder value = new StringBuilder(itemPriceProtectionSearchCriteria.getFilterValue1());
					if ("LIKE".equalsIgnoreCase(itemPriceProtectionSearchCriteria.getExpression())) {
						value.append("%" + value + "%");
					}
					inputWhereConditions.append(GtnFrameworkWebserviceConstant.AND_COLUMN
							+ getWhereClauseForAColumn(itemPriceProtectionSearchCriteria.getExpression(),
									getItemAdditionTabColumns(itemPriceProtectionSearchCriteria.getFieldId()),
									value.toString(), itemPriceProtectionSearchCriteria.getFilterValue2()));
				}
			}

			if ((!itemPriceProtectionSearchRequest.isCount())
					&& (!itemPriceProtectionSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty())) {

				GtnWebServiceSearchCriteria searchCriteria = itemPriceProtectionSearchRequest
						.getGtnWebServiceSearchCriteriaList().get(0);
				if (!searchCriteria.isFilter()) {
					recordType = searchCriteria.getFilterValue1().replace("[", "").replace("]", "");
				}

			}
			String timeFilter = getCurrentHistoryFilter(recordType, "PRICE_PROTECTION_START_DATE",
					"PRICE_PROTECTION_END_DATE");
			if (!timeFilter.isEmpty()) {
				inputWhereConditions.append(timeFilter);
			}

			list.add(inputWhereConditions);
			if (!itemPriceProtectionSearchRequest.isCount()) {
				list.add(getItemsDetailSortedInputs(
						itemPriceProtectionSearchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(itemPriceProtectionSearchRequest.getTableRecordStart());
				list.add(itemPriceProtectionSearchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getItemsSearchPriceProtectionInput", ex);
			throw new GtnFrameworkGeneralException("Error in getItemsSearchPriceProtectionInput : ", ex);

		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceResponse getCDPricingDetailPendingTableData(
			GtnUIFrameworkWebserviceRequest cdPricingPendingRequest, GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException {
		try {
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			String queryName = cdPricingPendingRequest.getGtnWsSearchRequest().isCount()
					? "getPricingDetailsPendingCount" : "getPricingDetailsPendingResults";
			List<Object> inputlist = getItemsSearchPricingPendingInput(cdPricingPendingRequest.getGtnWsSearchRequest());
			String catalog = getController().getSysSchemaCatalog();
			inputlist.add(0, catalog);
			String recordType = "";
			String contractId = "";

			if (!cdPricingPendingRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {
				GtnWebServiceSearchCriteria cdPendingPricingSearchCriteria = cdPricingPendingRequest
						.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(0);
				GtnWebServiceSearchCriteria searchCriteriaforId = cdPricingPendingRequest.getGtnWsSearchRequest()
						.getGtnWebServiceSearchCriteriaList().get(1);
				if (!cdPendingPricingSearchCriteria.isFilter()) {
					recordType = cdPendingPricingSearchCriteria.getFilterValue1().replace("[", "").replace("]", "");
				}
				if (!searchCriteriaforId.isFilter()) {
					contractId = searchCriteriaforId.getFilterValue1();
				}
			}
			if (!cdPricingPendingRequest.getGtnWsSearchRequest().isCount()) {
				inputlist.add(0, recordType);

				String orderByColumn = inputlist.get(inputlist.size() - 3).toString();
				if (orderByColumn.startsWith("im")) {
					inputlist.set(inputlist.size() - 3, GtnFrameworkWebserviceConstant.IMTDITEM_MASTER_SID);
				}
			}

			inputlist.add(0, contractId);

			List<Object[]> result = getController().executeQuery(controller.getQuery(inputlist, queryName));
			if (cdPricingPendingRequest.getGtnWsSearchRequest().isCount()) {
				gtnSerachResponse.setCount(Integer.valueOf(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable pricingPendingDataTable = new GtnUIFrameworkDataTable();
				pricingPendingDataTable.addData(result);
				gtnSerachResponse.setResultSet(pricingPendingDataTable);
			}
			gtnResponse.setGtnSerachResponse(gtnSerachResponse);
		} catch (Exception ex) {
			logger.error("Exception in getCDPricingDetailPendingTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDPricingDetailPendingTableData", ex);

		}
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceResponse getCDPricingProtectionPendingTableData(
			GtnUIFrameworkWebserviceRequest cdPPPendingRequest, GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException {
		try {
			GtnSerachResponse pendingPPSerachResponse = new GtnSerachResponse();
			String queryName = cdPPPendingRequest.getGtnWsSearchRequest().isCount() ? "getPricingProtectionPendingCount"
					: "getPricingProtectionPendingResults";
			List<Object> pendingPPInputlist = getItemsSearchPriceProtectionPendingInput(
					cdPPPendingRequest.getGtnWsSearchRequest());
			String recordType = "";
			String contractId = "";
			if (!cdPPPendingRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {
				GtnWebServiceSearchCriteria searchCriteria = cdPPPendingRequest.getGtnWsSearchRequest()
						.getGtnWebServiceSearchCriteriaList().get(0);
				GtnWebServiceSearchCriteria searchCriteriaforId = cdPPPendingRequest.getGtnWsSearchRequest()
						.getGtnWebServiceSearchCriteriaList().get(1);
				if (!searchCriteria.isFilter()) {
					recordType = searchCriteria.getFilterValue1().replace("[", "").replace("]", "");
				}
				if (!searchCriteriaforId.isFilter()) {
					contractId = searchCriteriaforId.getFilterValue1();
				}

			}
			if (!cdPPPendingRequest.getGtnWsSearchRequest().isCount()) {
				pendingPPInputlist.add(0, recordType);

				String orderByColumn = pendingPPInputlist.get(pendingPPInputlist.size() - 3).toString();
				if (orderByColumn.startsWith("im")) {
					pendingPPInputlist.set(pendingPPInputlist.size() - 3,
							GtnFrameworkWebserviceConstant.IMTDITEM_MASTER_SID);
				}
			}

			pendingPPInputlist.add(0, contractId);
			List<Object[]> result = getController().executeQuery(controller.getQuery(pendingPPInputlist, queryName));
			if (cdPPPendingRequest.getGtnWsSearchRequest().isCount()) {
				pendingPPSerachResponse.setCount(Integer.valueOf(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable cdPendingPPDataTable = new GtnUIFrameworkDataTable();
				cdPendingPPDataTable.addData(result);
				pendingPPSerachResponse.setResultSet(cdPendingPPDataTable);
			}
			gtnResponse.setGtnSerachResponse(pendingPPSerachResponse);
		} catch (Exception ex) {
			logger.error("Exception in getCDPricingProtectionPendingTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDPricingProtectionPendingTableData", ex);
		}
		return gtnResponse;
	}

	private List<Object> getItemsSearchPricingPendingInput(GtnWsSearchRequest cdPricingPendingearchRequest)
			throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		StringBuilder inputWhereConditions = new StringBuilder();
		try {
			for (int i = 0; i < cdPricingPendingearchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria cdPricingPendingSearchCriteria = cdPricingPendingearchRequest
						.getGtnWebServiceSearchCriteriaList().get(i);
				if (cdPricingPendingSearchCriteria.isFilter()
						&& !skipCriteria(cdPricingPendingSearchCriteria.getFieldId())) {
					StringBuilder value = new StringBuilder(cdPricingPendingSearchCriteria.getFilterValue1());
					if ("LIKE".equalsIgnoreCase(cdPricingPendingSearchCriteria.getExpression())) {
						value.append("%" + value + "%");
					}
					inputWhereConditions.append(GtnFrameworkWebserviceConstant.AND_COLUMN
							+ getWhereClauseForAColumn(cdPricingPendingSearchCriteria.getExpression(),
									getItemAdditionTabColumns(cdPricingPendingSearchCriteria.getFieldId()),
									value.toString(), cdPricingPendingSearchCriteria.getFilterValue2()));
				}
			}
			if (!cdPricingPendingearchRequest.isCount()) {
				list.add(inputWhereConditions);
				list.add(
						getItemsDetailSortedInputs(cdPricingPendingearchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(cdPricingPendingearchRequest.getTableRecordStart());
				list.add(cdPricingPendingearchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getItemsSearchPricingPendingInput", ex);
			throw new GtnFrameworkGeneralException("Error in getItemsSearchPricingPendingInput : ", ex);

		}
		return list;
	}

	private List<Object> getItemsSearchPriceProtectionPendingInput(GtnWsSearchRequest cdPPPendingSearchRequest)
			throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		StringBuilder inputWhereConditions = new StringBuilder();
		try {
			for (int i = 0; i < cdPPPendingSearchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria cdPPPendingSearchCriteria = cdPPPendingSearchRequest
						.getGtnWebServiceSearchCriteriaList().get(i);
				if (cdPPPendingSearchCriteria.isFilter() && !skipCriteria(cdPPPendingSearchCriteria.getFieldId())) {
					StringBuilder value = new StringBuilder(cdPPPendingSearchCriteria.getFilterValue1());
					if ("LIKE".equalsIgnoreCase(cdPPPendingSearchCriteria.getExpression())) {
						value.append("%" + value + "%");
					}
					inputWhereConditions.append(GtnFrameworkWebserviceConstant.AND_COLUMN
							+ getWhereClauseForAColumn(cdPPPendingSearchCriteria.getExpression(),
									getItemAdditionTabColumns(cdPPPendingSearchCriteria.getFieldId()), value.toString(),
									cdPPPendingSearchCriteria.getFilterValue2()));
				}
			}
			if (!cdPPPendingSearchRequest.isCount()) {
				list.add(inputWhereConditions);
				list.add(getItemsDetailSortedInputs(cdPPPendingSearchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(cdPPPendingSearchRequest.getTableRecordStart());
				list.add(cdPPPendingSearchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getItemsSearchPriceProtectionPendingInput", ex);
			throw new GtnFrameworkGeneralException("Error in getItemsSearchPriceProtectionPendingInput : ", ex);

		}
		return list;
	}

	private String getCurrentHistoryFilter(String recordType, String startDateValue, String endDateValue) {
		String defaultJavaDateFormat = GtnFrameworkCommonStringConstants.DEFAULT_JAVA_DATEFORMAT;
		String sqlDateFormat = GtnFrameworkCommonStringConstants.SQL_DATEFORMAT;
		String date = GtnCommonUtil.convertStringToDate(new Date().toString(), defaultJavaDateFormat, sqlDateFormat);
		boolean history = false;
		boolean current = false;
		boolean future = false;
		if (!recordType.equals(GtnFrameworkCommonStringConstants.STRING_EMPTY)) {
			current = recordType.contains(GtnFrameworkCommonStringConstants.CURRENT);
			history = recordType.contains(GtnFrameworkCommonStringConstants.HISTORY);
			future = recordType.contains(GtnFrameworkCommonStringConstants.FUTURE);
		}
		if (!history && !current && !future) {
			return GtnFrameworkCommonStringConstants.STRING_EMPTY;
		}
		String startDateColumn = GtnFrameworkWebserviceConstant.CAST + startDateValue + " AS DATE) ";
		String endDateColumn = GtnFrameworkWebserviceConstant.CAST + endDateValue + " AS DATE) ";
		String betweenDate = "' BETWEEN " + startDateColumn + " AND CAST(ISNULL(" + endDateColumn + ",'";
		String startDate = " " + startDateColumn + " > '";
		String endDate = " " + endDateColumn + " < '";
		String and = GtnFrameworkCommonStringConstants.AND_BRACKET_QUOTE;
		if (history && current && future) {
			String sql = and + date + betweenDate + date + "') AS DATE) ";
			sql += " OR " + endDate + date + "' ";
			sql += " OR " + startDate + date + "' )";
			return sql;
		} else if (history && current) {
			return and + date + betweenDate + date + "') AS DATE) OR " + endDateColumn + " < '" + date + "') ";
		} else if (history && future) {
			return GtnFrameworkCommonStringConstants.AND_BRACKET + endDate + date + "' OR " + startDateColumn + " > '"
					+ date + "') ";
		} else if (current && future) {
			return and + date + betweenDate + date + "') AS DATE) OR " + startDateColumn + " > '" + date + "') ";
		} else if (current) {
			return GtnFrameworkCommonStringConstants.AND_QUOTE + date + betweenDate + date + "') AS DATE) ";
		} else if (history) {
			return AND + endDate + date + "' ";
		} else {
			return AND + startDate + date + "' ";
		}
	}

	private void itemDetailsSearchInputTableData(GtnWsSearchRequest itemSearchRequest,
			StringBuilder inputWhereConditions) throws GtnFrameworkGeneralException {
		try {
			for (int i = 0; i < itemSearchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria itemSearchCriteria = itemSearchRequest.getGtnWebServiceSearchCriteriaList()
						.get(i);
				if (itemSearchCriteria.isFilter() && !skipCriteria(itemSearchCriteria.getFieldId())) {
					StringBuilder value = new StringBuilder(itemSearchCriteria.getFilterValue1());
					if ("LIKE".equalsIgnoreCase(itemSearchCriteria.getExpression())) {
						value.append("%" + value + "%");
					}
					inputWhereConditions.append(GtnFrameworkWebserviceConstant.AND_COLUMN
							+ getWhereClauseForAColumn(itemSearchCriteria.getExpression(),
									getItemAdditionTabColumns(itemSearchCriteria.getFieldId()), value.toString(),
									itemSearchCriteria.getFilterValue2()));
				}
			}

		} catch (Exception ex) {
			logger.error("Exception in itemDetailsSearchInputTableData", ex);
			throw new GtnFrameworkGeneralException("Error in itemDetailsSearchInputTableData : ", ex);

		}
	}

	public void contractPriceProtectionInsertTemp(List<Object> inputValueList,
			GtnUIFrameworkWebserviceResponse gtnContractProtectionUpdateResponse) throws GtnFrameworkGeneralException {
		int psUserId = Integer.parseInt(inputValueList.get(0).toString());
		String psSessionId = inputValueList.get(1).toString();
		String addCopyDelete = inputValueList.get(2).toString();
		String msg = "checkRecord";
		Object[] imtdPsDetailsInsertQueryParams = { psUserId, psSessionId };
		GtnFrameworkDataType[] imtdPsDetailsInsertQueryTypes = { GtnFrameworkDataType.INTEGER,
				GtnFrameworkDataType.STRING };
		List<Object> contractCountList = getResultValue("getAddCopyDeleteLineAlertCountQueryContract",
				imtdPsDetailsInsertQueryParams, imtdPsDetailsInsertQueryTypes);
		if ((Integer) contractCountList.get(0) != 0) {
			msg = "unableToRemove";
			contractCountList = getResultValue("deleteLineAlertCountQueryContract", imtdPsDetailsInsertQueryParams,
					imtdPsDetailsInsertQueryTypes);
		}
		getController().executeUpdateQuery(getController().getGtnWsSqlService().getQuery(addCopyDelete),
				imtdPsDetailsInsertQueryParams, imtdPsDetailsInsertQueryTypes);
		gtnContractProtectionUpdateResponse.setOutBountData(new Object[] { contractCountList.get(0), msg });

	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceResponse getCDItemsViewDetailPendingTableData(
			GtnUIFrameworkWebserviceRequest cditemsgtnWsRequest, GtnUIFrameworkWebserviceResponse cditemsgtnResponse)
			throws GtnFrameworkGeneralException {
		try {
			GtnSerachResponse cditemsgtnSerachResponse = new GtnSerachResponse();
			String cditemsqueryName = cditemsgtnWsRequest.getGtnWsSearchRequest().isCount()
					? "getItemsDetailsPendingCount" : "getItemsDetailsViewPendingResults";
			List<Object> cditemSearchInputlist = getItemsSearchPendingInput(
					cditemsgtnWsRequest.getGtnWsSearchRequest());
			String itemdetailCatalog = getController().getSysSchemaCatalog();
			cditemSearchInputlist.add(0, itemdetailCatalog);
			cditemSearchInputlist.add(0, itemdetailCatalog);
			String cditemsrecordType = "";
			String cditemscontractId = "";

			if (!cditemsgtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {
				GtnWebServiceSearchCriteria cdItemSearchdetailCriteria = cditemsgtnWsRequest.getGtnWsSearchRequest()
						.getGtnWebServiceSearchCriteriaList().get(0);
				GtnWebServiceSearchCriteria cdItemSearchdetailCriteriaforId = cditemsgtnWsRequest
						.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(1);
				if (!cdItemSearchdetailCriteria.isFilter()) {
					cditemsrecordType = cdItemSearchdetailCriteria.getFilterValue1().replace("[", "").replace("]", "");
				}
				if (!cdItemSearchdetailCriteriaforId.isFilter()) {
					cditemscontractId = cdItemSearchdetailCriteriaforId.getFilterValue1();
				}
			}
			if (!cditemsgtnWsRequest.getGtnWsSearchRequest().isCount()) {
				cditemSearchInputlist.add(0, cditemsrecordType);
			}

			cditemSearchInputlist.add(0, cditemscontractId);
			List<Object[]> cditemsresult = getController()
					.executeQuery(controller.getQuery(cditemSearchInputlist, cditemsqueryName));
			if (cditemsgtnWsRequest.getGtnWsSearchRequest().isCount()) {
				cditemsgtnSerachResponse.setCount(Integer.valueOf(String.valueOf(cditemsresult.get(0))));
			} else {
				GtnUIFrameworkDataTable cdPendingItemsDataTable = new GtnUIFrameworkDataTable();
				cdPendingItemsDataTable.addData(cditemsresult);
				cditemsgtnSerachResponse.setResultSet(cdPendingItemsDataTable);
			}
			cditemsgtnResponse.setGtnSerachResponse(cditemsgtnSerachResponse);
		} catch (Exception ex) {
			logger.error("Exception in getCDItemsViewDetailPendingTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDItemsViewDetailPendingTableData", ex);
		}
		return cditemsgtnResponse;
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceResponse getCDRebateDetailViewPendingTableData(
			GtnUIFrameworkWebserviceRequest cdRebatePendingviewRequest,
			GtnUIFrameworkWebserviceResponse cdRebatePendingviewgtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnSerachResponse cdrspendingviewgtnSerachResponse = new GtnSerachResponse();
			String cdrspendingviewqueryName = cdRebatePendingviewRequest.getGtnWsSearchRequest().isCount()
					? "getRebateDetailsPendingCount" : "getRebateDetailsViewPendingResults";
			List<Object> cdrspendingviewinputlist = getItemsSearchRebateInput(
					cdRebatePendingviewRequest.getGtnWsSearchRequest());
			String rspendingviewrecordType = "";
			String rspendingviewcontractId = "";

			if (!cdRebatePendingviewRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {
				GtnWebServiceSearchCriteria cdRebatePendingsearchCriteria = cdRebatePendingviewRequest
						.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(0);

				GtnWebServiceSearchCriteria rspendingviewsearchCriteriaforId = cdRebatePendingviewRequest
						.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(1);
				if (!cdRebatePendingsearchCriteria.isFilter()) {
					rspendingviewrecordType = cdRebatePendingsearchCriteria.getFilterValue1().replace("[", "")
							.replace("]", "");
				}
				if (!rspendingviewsearchCriteriaforId.isFilter()) {
					rspendingviewcontractId = rspendingviewsearchCriteriaforId.getFilterValue1();
				}
			}
			if (!cdRebatePendingviewRequest.getGtnWsSearchRequest().isCount()) {
				cdrspendingviewinputlist.add(0, rspendingviewrecordType);
				String orderByColumn = cdrspendingviewinputlist.get(cdrspendingviewinputlist.size() - 3).toString();
				if (orderByColumn.startsWith("im")) {
					cdrspendingviewinputlist.set(cdrspendingviewinputlist.size() - 3,
							GtnFrameworkWebserviceConstant.IMTDITEM_MASTER_SID);
				}
			}

			cdrspendingviewinputlist.add(0, rspendingviewcontractId);
			List<Object[]> rspendingviewresult = getController()
					.executeQuery(controller.getQuery(cdrspendingviewinputlist, cdrspendingviewqueryName));
			if (cdRebatePendingviewRequest.getGtnWsSearchRequest().isCount()) {
				cdrspendingviewgtnSerachResponse.setCount(Integer.valueOf(String.valueOf(rspendingviewresult.get(0))));
			} else {
				GtnUIFrameworkDataTable cdPendingviewRebateDataTable = new GtnUIFrameworkDataTable();
				cdPendingviewRebateDataTable.addData(rspendingviewresult);
				cdrspendingviewgtnSerachResponse.setResultSet(cdPendingviewRebateDataTable);
			}
			cdRebatePendingviewgtnResponse.setGtnSerachResponse(cdrspendingviewgtnSerachResponse);
		} catch (Exception ex) {
			logger.error("Exception in getCDRebateDetailViewPendingTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDRebateDetailViewPendingTableData", ex);
		}
		return cdRebatePendingviewgtnResponse;
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceResponse getCDPricingDetailViewPendingTableData(
			GtnUIFrameworkWebserviceRequest cdPricingdetPendingRequest,
			GtnUIFrameworkWebserviceResponse pricingdetgtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnSerachResponse pricingdetgtnSerachResponse = new GtnSerachResponse();
			String pricingdetqueryName = cdPricingdetPendingRequest.getGtnWsSearchRequest().isCount()
					? "getPricingDetailsPendingCount" : "getPricingDetailsViewPendingResults";
			List<Object> pricingdetinputlist = getItemsSearchPricingPendingInput(
					cdPricingdetPendingRequest.getGtnWsSearchRequest());
			String pricingdetCatalog = getController().getSysSchemaCatalog();
			pricingdetinputlist.add(0, pricingdetCatalog);
			String pricingdetrecordType = "";
			String pricingdetcontractId = "";

			if (!cdPricingdetPendingRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {
				GtnWebServiceSearchCriteria pricingdetSearchCriteria = cdPricingdetPendingRequest
						.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(0);
				GtnWebServiceSearchCriteria pricingdetsearchCriteriaforId = cdPricingdetPendingRequest
						.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(1);
				if (!pricingdetSearchCriteria.isFilter()) {
					pricingdetrecordType = pricingdetSearchCriteria.getFilterValue1().replace("[", "").replace("]", "");
				}
				if (!pricingdetsearchCriteriaforId.isFilter()) {
					pricingdetcontractId = pricingdetsearchCriteriaforId.getFilterValue1();
				}
			}
			if (!cdPricingdetPendingRequest.getGtnWsSearchRequest().isCount()) {
				pricingdetinputlist.add(0, pricingdetrecordType);
				String pricingdetorderByColumn = pricingdetinputlist.get(pricingdetinputlist.size() - 3).toString();
				if (pricingdetorderByColumn.startsWith("im")) {
					pricingdetinputlist.set(pricingdetinputlist.size() - 3,
							GtnFrameworkWebserviceConstant.IMTDITEM_MASTER_SID);
				}
			}

			pricingdetinputlist.add(0, pricingdetcontractId);

			List<Object[]> pricingdetresult = getController()
					.executeQuery(controller.getQuery(pricingdetinputlist, pricingdetqueryName));
			if (cdPricingdetPendingRequest.getGtnWsSearchRequest().isCount()) {
				pricingdetgtnSerachResponse.setCount(Integer.valueOf(String.valueOf(pricingdetresult.get(0))));
			} else {
				GtnUIFrameworkDataTable pricingdetDataTable = new GtnUIFrameworkDataTable();
				pricingdetDataTable.addData(pricingdetresult);
				pricingdetgtnSerachResponse.setResultSet(pricingdetDataTable);
			}
			pricingdetgtnResponse.setGtnSerachResponse(pricingdetgtnSerachResponse);
		} catch (Exception ex) {
			logger.error("Exception in getCDPricingDetailViewPendingTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDPricingDetailViewPendingTableData", ex);

		}
		return pricingdetgtnResponse;
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceResponse getCDPricingProtectionViewPendingTableData(
			GtnUIFrameworkWebserviceRequest ppPendingRequest, GtnUIFrameworkWebserviceResponse ppgtnResponse)
			throws GtnFrameworkGeneralException {
		try {
			GtnSerachResponse ppSerachResponse = new GtnSerachResponse();
			String ppqueryName = ppPendingRequest.getGtnWsSearchRequest().isCount()
					? "getPricingProtectionViewPendingCount" : "getPricingProtectionViewPendingResults";
			List<Object> ppInputlist = getItemsSearchPriceProtectionPendingInput(
					ppPendingRequest.getGtnWsSearchRequest());
			String pprecordType = "";
			String ppcontractId = "";
			if (!ppPendingRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {
				GtnWebServiceSearchCriteria ppsearchCriteria = ppPendingRequest.getGtnWsSearchRequest()
						.getGtnWebServiceSearchCriteriaList().get(0);
				GtnWebServiceSearchCriteria ppsearchCriteriaforId = ppPendingRequest.getGtnWsSearchRequest()
						.getGtnWebServiceSearchCriteriaList().get(1);
				if (!ppsearchCriteria.isFilter()) {
					pprecordType = ppsearchCriteria.getFilterValue1().replace("[", "").replace("]", "");
				}
				if (!ppsearchCriteriaforId.isFilter()) {
					ppcontractId = ppsearchCriteriaforId.getFilterValue1();
				}

			}
			if (!ppPendingRequest.getGtnWsSearchRequest().isCount()) {
				ppInputlist.add(0, pprecordType);
				String pporderByColumn = ppInputlist.get(ppInputlist.size() - 3).toString();
				if (pporderByColumn.startsWith("im")) {
					ppInputlist.set(ppInputlist.size() - 3, GtnFrameworkWebserviceConstant.IMTDITEM_MASTER_SID);
				}
			}
			ppInputlist.add(0, ppcontractId);
			List<Object[]> ppresult = getController().executeQuery(controller.getQuery(ppInputlist, ppqueryName));
			if (ppPendingRequest.getGtnWsSearchRequest().isCount()) {
				ppSerachResponse.setCount(Integer.valueOf(String.valueOf(ppresult.get(0))));
			} else {
				GtnUIFrameworkDataTable ppDataTable = new GtnUIFrameworkDataTable();
				ppDataTable.addData(ppresult);
				ppSerachResponse.setResultSet(ppDataTable);
			}
			ppgtnResponse.setGtnSerachResponse(ppSerachResponse);
		} catch (Exception ex) {
			logger.error("Exception in getCDPricingProtectionViewPendingTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDPricingProtectionViewPendingTableData", ex);
		}
		return ppgtnResponse;
	}

	private List<Object> getResultValue(String query, Object[] imtdPsDetailsInsertQueryParams,
			GtnFrameworkDataType[] imtdPsDetailsInsertQueryTypes) throws GtnFrameworkGeneralException {
		String contractQuery = getController().getGtnWsSqlService().getQuery(query);
		return (List<Object>) getController().executeQuery(contractQuery, imtdPsDetailsInsertQueryParams,
				imtdPsDetailsInsertQueryTypes);

	}
        public void priceProtectionStartDateAlert(List<Object> inputValueList,
			GtnUIFrameworkWebserviceResponse gtnPsProtectionUpdateResponse) throws GtnFrameworkGeneralException {
            System.out.println("priceProtectionStartDateAlert-----------------------------");
		String psUserId = inputValueList.get(0).toString();
		String psSessionId = inputValueList.get(1).toString();
		Object[] imtdPsDetailsInsertQueryParams = { psUserId, psSessionId };
		GtnFrameworkDataType[] imtdPsDetailsInsertQueryTypes = { GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };

		List<Object> psCountList = (List<Object>) getController().executeQuery(
				getController().getGtnWsSqlService().getQuery("contractPriceProtectionStartDateAlertQuery"), imtdPsDetailsInsertQueryParams,
				imtdPsDetailsInsertQueryTypes);

		gtnPsProtectionUpdateResponse.setOutBountData(new Object[] { psCountList.get(0) });

	}

}
