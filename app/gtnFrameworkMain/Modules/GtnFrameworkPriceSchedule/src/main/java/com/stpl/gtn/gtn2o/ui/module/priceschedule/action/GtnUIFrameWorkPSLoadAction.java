/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.priceschedule.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.GtnUIFrameworkNotesTab;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.NotesDTO;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory.GtnFrameworkPriceProtectionValueChangeManager;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory.GtnFrameworkPriceTabValueChangeManager;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.constants.GtnFrameworkPSConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.priceschedule.bean.GtnUIFrameWorkPSInfoBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.priceschedule.GtnWsPriceScheduleGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.priceschedule.GtnWsPriceScheduleGeneralResponse;

/**
 *
 * @author Mahesh.James
 */
public class GtnUIFrameWorkPSLoadAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameWorkPSInfoBean priceScheduleInfoBean;
		List<NotesTabBean> noteBeanList = new ArrayList<>();
		priceScheduleInfoBean = loadDataFromService(noteBeanList);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleId1")
				.setPropertyValue(priceScheduleInfoBean.getPsId());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleNo1")
				.setPropertyValue(priceScheduleInfoBean.getPsNo());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleName1")
				.setPropertyValue(priceScheduleInfoBean.getPsName());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentPriceScheduleName")
				.setPropertyValue(priceScheduleInfoBean.getParentpSNo());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.CREATED_BY)
				.loadFieldValue(priceScheduleInfoBean.getCreatedBy());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("modifiedBy")
				.loadFieldValue(priceScheduleInfoBean.getModifiedBy());

		setValueForComponent(GtnFrameworkCommonConstants.PARENT_PRICE_SCHEDULE_ID,
				priceScheduleInfoBean.getParentPsSid());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.PARENT_PRICE_SCHEDULE_ID)
				.loadFieldValue(priceScheduleInfoBean.getParentPsId());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleStatus1")
				.loadComboBoxComponentValue(priceScheduleInfoBean.getPsStatus());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleDesignation")
				.loadComboBoxComponentValue(priceScheduleInfoBean.getPsDesignation());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleType1")
				.loadComboBoxComponentValue(priceScheduleInfoBean.getPsType());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleCategory")
				.loadComboBoxComponentValue(priceScheduleInfoBean.getPsCategory());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleTradeClass")
				.loadComboBoxComponentValue(priceScheduleInfoBean.getPsTradeClass());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleStartDate")
				.loadDateValue(priceScheduleInfoBean.getPsStartDate());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleEndDate")
				.loadDateValue(priceScheduleInfoBean.getPsEndDate());
		Object mode = GtnUIFrameworkGlobalUI.getSessionProperty("mode");
		if (mode != null && mode != GtnUIFrameworkModeType.ADD) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleIdTop")
					.setPropertyValue(priceScheduleInfoBean.getPsId());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleNoTop")
					.setPropertyValue(priceScheduleInfoBean.getPsNo());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleNameTop")
					.setPropertyValue(priceScheduleInfoBean.getPsName());
		}
		resetNotesTab();
		loadNotesTab(priceScheduleInfoBean, noteBeanList);

		loadIfpSelectedTable(priceScheduleInfoBean,
				(Integer) GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SYSTEM_ID));
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("psPricingTabResultDataTable")
				.setFilterFieldVisible(GtnFrameworkCommonConstants.CHECK_RECORD_ID, Boolean.FALSE);

	}

	private GtnUIFrameWorkPSInfoBean loadDataFromService(List<NotesTabBean> noteBeanList) {
		int systemId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SYSTEM_ID);
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		GtnUIFrameWorkPSInfoBean psInfoBean = new GtnUIFrameWorkPSInfoBean();

		gtnWsGeneralRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		gtnWsGeneralRequest.setSessionId(
				GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SESSION_ID).toString());
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		psInfoBean.setSystemId(systemId);

		GtnWsPriceScheduleGeneralRequest getGtnWsPriceScheduleGeneralRequest = new GtnWsPriceScheduleGeneralRequest();

		getGtnWsPriceScheduleGeneralRequest.setPsInfoBean(psInfoBean);
		request.setGtnWsPriceScheduleGeneralRequest(getGtnWsPriceScheduleGeneralRequest);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				"/" + GtnWsCDRContants.PS_SERVICE + "/getGTNPriceScheduleService", request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsPriceScheduleGeneralResponse priceScheduleGeneralResponse = response
				.getGtnWsPriceScheduleGeneralResponse();
		GtnUIFrameWorkPSInfoBean frameWorkPSInfoBean = priceScheduleGeneralResponse.getPriceScheduleInfoBean();
		noteBeanList.addAll(frameWorkPSInfoBean.getNoteBeanList());
		return response.getGtnWsPriceScheduleGeneralResponse().getPriceScheduleInfoBean();

	}

	private void loadNotesTab(GtnUIFrameWorkPSInfoBean priceScheduleInfoBean, List<NotesTabBean> noteBeanList) {

		List<NotesDTO> notesDTOList = new ArrayList<>();
		List<Object> result = new ArrayList<>();
		NotesDTO attachmentDTO;
		if (noteBeanList != null) {
			for (NotesTabBean notesTabBean : noteBeanList) {
				attachmentDTO = new NotesDTO();
				attachmentDTO.setDocDetailsId(notesTabBean.getMasterTableSystemId());
				String filePath = notesTabBean.getFilePath();
				attachmentDTO.setDocumentFullPath(filePath);
				attachmentDTO.setDocumentName("--");
				String tempfilePath = notesTabBean.getFilePath();
				attachmentDTO.setDocumentFullPath(tempfilePath);
				String fileName = filePath.substring(filePath.lastIndexOf('/') + 1, filePath.length());
				attachmentDTO.setDocumentName(fileName);
				SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
				TimeZone central = TimeZone.getTimeZone("CST");
				format.setTimeZone(central);
				attachmentDTO.setDateAdded(format.format(notesTabBean.getCreatedDate()));
				attachmentDTO.setUserId(notesTabBean.getCreatedBy());
				attachmentDTO.setUserName(String.valueOf(notesTabBean.getCreatedByName()));
				notesDTOList.add(attachmentDTO);
			}
		}
		result.add(priceScheduleInfoBean.getInternalNotes());
		result.add(notesDTOList);
		GtnUIFrameworkGlobalUI.loadNotesTab(result, "notesTab");
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void setValueForComponent(String componentId, String sysId) {

		GtnWsRecordBean dto = new GtnWsRecordBean();
		dto.setProperties(Arrays.asList(new Object[] { GtnFrameworkCommonConstants.SYSTEM_ID }));
		dto.setRecordHeader(Arrays.asList(new Object[] { GtnFrameworkCommonConstants.SYSTEM_ID }));
		dto.addStringProperties(GtnFrameworkCommonConstants.SYSTEM_ID, sysId);
		GtnUIFrameworkGlobalUI.getVaadinComponentData(componentId).setCustomData(dto);
	}

	@SuppressWarnings("rawtypes")
	private void loadIfpSelectedTable(GtnUIFrameWorkPSInfoBean priceScheduleInfoBean, int sysid)
			throws GtnFrameworkGeneralException {
		GtnFrameworkPriceProtectionValueChangeManager.setValueChangeAllowed(Boolean.FALSE);
		GtnFrameworkPriceTabValueChangeManager.setValueChangeAllowed(Boolean.FALSE);
		List<Object> dataList = priceScheduleInfoBean.getIfpDataList();
		GtnWsRecordBean dto = new GtnWsRecordBean();
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData("CFPrightResultTable");

		List<Object> colList = new ArrayList<>();
		colList.addAll(dataList);
		dto.setRecordHeader(Arrays.asList(
				new Object[] { "IFP ID", "IFP No", "IFP Name", "IFP Status", "End Date", "IFP Type", "IFP Category" }));
		dto.setProperties(colList);

		GtnUIFrameworkPagedTableLogic tableLogic = componentData.getCurrentPageTableLogic();
		tableLogic.clearAll();
		tableLogic.configureContainer(dto, tableLogic.getContainerDataSource());
		((ExtPagedTable) componentData.getCustomData()).select(dto);

		Object mode = GtnUIFrameworkGlobalUI.getSessionProperty("mode");
		if (mode != null && mode == GtnUIFrameworkModeType.VIEW) {
			loadPriceTabsForView(sysid, Boolean.FALSE);
		} else {
			loadPriceTabsForView(sysid, Boolean.TRUE);
		}
		GtnFrameworkPriceProtectionValueChangeManager.setValueChangeAllowed(Boolean.TRUE);
		GtnFrameworkPriceTabValueChangeManager.setValueChangeAllowed(Boolean.TRUE);

	}

	public void loadPriceTabsForView(int systemId, boolean isEditable) throws GtnFrameworkGeneralException {
		String pSDesignation = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleDesignation")
				.getCaptionFromComboBox();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentPriceScheduleID").setEnable(false);
		if ("Child".equals(pSDesignation)) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentPriceScheduleID").setEnable(isEditable);
		}
                GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentPriceScheduleID").setEnable(isEditable);
                GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentPriceScheduleID").setEnable(isEditable);
                GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentPriceScheduleID").setEnable(isEditable);
		GtnUIFrameworkBaseComponent pricingResultDataTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.PS_PRICING_TAB_RESULT_DATA_TABLE);
		GtnUIFrameworkBaseComponent priceProtectionResultDataTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_RESULT_DATA_TABLE);

		loadPricingTable(pricingResultDataTable, isEditable, systemId);
		loadPriceProtectionTable(priceProtectionResultDataTable, isEditable, systemId);

	}

	private void loadPricingTable(GtnUIFrameworkBaseComponent pricingResultDataTable, boolean isEditable, int systemId)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkPagedTableConfig pricingTabPagedTableConfig = getPagedTable(pricingResultDataTable);
		String[] tableHeaderPricingArray = getPricingHeaderArray(isEditable);
		Object[] visibleColumnsPricingArray = getPricingColumnIdArray(isEditable);
		Class<?>[] tableHeaderPricingDataTypeArray = getPricingTableDataTypeArray(isEditable);
		loadTableMetaData(visibleColumnsPricingArray, tableHeaderPricingArray, tableHeaderPricingDataTypeArray,
				pricingTabPagedTableConfig);
		String moduleQueryName = isEditable ? "priceSchedulePrice" : "priceSchedulePriceView";
		configureDataLoadingType(moduleQueryName, GtnFrameworkPSConstants.getPricingEditableFieldPropertiesArray(),
				GtnFrameworkCommonConstants.CHECK_RECORD_ID, pricingTabPagedTableConfig, isEditable);
		configureTableURL(pricingTabPagedTableConfig);
		resetTable(systemId, "psPricingTabTabResultLayout", isEditable, pricingResultDataTable.getComponentConfig(),
				pricingTabPagedTableConfig);
	}

	private void loadPriceProtectionTable(GtnUIFrameworkBaseComponent priceProtectionResultDataTable,
			boolean isEditable, int systemId) throws GtnFrameworkGeneralException {
		GtnUIFrameworkPagedTableConfig pricingTabPagedTableConfig = getPagedTable(priceProtectionResultDataTable);
		String[] tableHeaderPricingArray = getPriceProtectionViewHeaderArray(isEditable);
		Object[] visibleColumnsPricingArray = getPriceProtectionViewColumnIdArray(isEditable);
		Class<?>[] tableHeaderPricingDataTypeArray = getPriceProtectionTableDataTypeArray(isEditable);
		loadTableMetaData(visibleColumnsPricingArray, tableHeaderPricingArray, tableHeaderPricingDataTypeArray,
				pricingTabPagedTableConfig);
		pricingTabPagedTableConfig.setInvisibleFilterPropertyIds(
				GtnFrameworkPSConstants.getPriceProtectionEditableList().toArray()[0],
				GtnFrameworkPSConstants.getPriceProtectionEditableList().toArray()[12]);
		String moduleQueryName = isEditable ? "priceSchedulePriceProtection" : "priceSchedulePriceProtectionView";
		configureDataLoadingType(moduleQueryName, GtnFrameworkPSConstants.getPriceProtectionEditableList(),
				GtnFrameworkCommonConstants.CHECK_RECORD_ID, pricingTabPagedTableConfig, isEditable);
		configureTableURL(pricingTabPagedTableConfig);
		resetTable(systemId, "psPriceProtectionTabResultLayout", isEditable,
				priceProtectionResultDataTable.getComponentConfig(), pricingTabPagedTableConfig);
		String tableID = priceProtectionResultDataTable.getComponentConfig().getComponentId();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableID)
				.setTableColumns(getPriceProtectionColumnIdArray(isEditable));
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableID)
				.setTableColumnHeaders(getPriceProtectionHeaderArray(isEditable));
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableID);
		if (isEditable)
			tableBaseComponent.setFilterFieldVisible(
					GtnFrameworkPSConstants.getPriceProtectionEditableList().toArray()[0], false);
		tableBaseComponent.setFilterFieldVisible(GtnFrameworkPSConstants.getPriceProtectionEditableList().toArray()[12],
				false);
	}

	private Class<?>[] getPricingTableDataTypeArray(boolean isEditable) {
		if (isEditable) {
			return new Class<?>[] { Boolean.class, String.class, String.class, String.class, String.class,
					Integer.class, Date.class, Date.class, Integer.class, String.class, String.class, String.class,
					Date.class, Date.class };

		} else {
			return new Class<?>[] { String.class, String.class, String.class, String.class, String.class, Date.class,
					Date.class, String.class, String.class, String.class, String.class, Date.class, Date.class };
		}
	}

	private Object[] getPricingColumnIdArray(boolean isEditable) {
		if (isEditable) {
			return new Object[] { GtnFrameworkCommonConstants.CHECK_RECORD_ID, GtnFrameworkCommonConstants.ITEM_ID,
					GtnFrameworkCommonConstants.ITEM_NO, GtnFrameworkCommonConstants.ITEM_NAME,
					GtnFrameworkCommonConstants.BRAND_NAME, "psStatus", "cpStartDate", "cpEndDate", "priceType",
					"psPrice", "suggestedPrice", GtnFrameworkCommonConstants.CREATED_BY, "psCreatedDate",
					GtnFrameworkCommonConstants.PS_ATTACHED_DATE };
		} else {
			return new Object[] { GtnFrameworkCommonConstants.ITEM_ID, GtnFrameworkCommonConstants.ITEM_NO,
					GtnFrameworkCommonConstants.ITEM_NAME, GtnFrameworkCommonConstants.BRAND_NAME, "psStatusDes",
					"cpStartDate", "cpEndDate", "priceTypeDes", "psPrice", "suggestedPrice",
					GtnFrameworkCommonConstants.CREATED_BY, "psCreatedDate",
					GtnFrameworkCommonConstants.PS_ATTACHED_DATE };
		}

	}

	private String[] getPricingHeaderArray(boolean isEditable) {
		if (isEditable) {
			return new String[] { "", "Item ID", "Item NO", GtnFrameworkCommonConstants.LABEL_ITEM_NAME, "Brand Name",
					"Status", "CP Start Date", "CP End Date", "Price Type", "Price", "Suggested Price", "Created By",
					"Created Date", "Attached Date" };
		} else {

			return new String[] { "Item ID", "Item NO", GtnFrameworkCommonConstants.LABEL_ITEM_NAME, "Brand Name",
					"Status", "CP Start Date", "CP End Date", "Price Type", "Price", "Suggested Price", "Created By",
					"Created Date", "Attached Date" };
		}

	}

	private Class<?>[] getPriceProtectionTableDataTypeArray(boolean isEditable) {
		if (isEditable) {
			return new Class<?>[] { Boolean.class, String.class, String.class, String.class, String.class,
					Integer.class, Date.class, Date.class, Integer.class, String.class, String.class, Integer.class,
					Object.class, Integer.class, String.class, Integer.class, Integer.class, String.class,
					Integer.class, Integer.class, Integer.class, String.class, String.class, Integer.class,
					Integer.class, Date.class, Integer.class, Integer.class, Integer.class, Integer.class, String.class,
					Integer.class, String.class, Date.class, Date.class, Integer.class, String.class };

		} else {
			return new Class<?>[] { String.class, String.class, String.class, String.class, String.class, Date.class,
					Date.class, String.class, String.class, String.class, String.class, Object.class, String.class,
					String.class, String.class, String.class, String.class, String.class, String.class, String.class,
					String.class, String.class, String.class, String.class, Date.class, String.class, String.class,
					String.class, String.class, String.class, String.class, String.class, Date.class, Date.class,
					Integer.class, String.class };
		}
	}

	private Object[] getPriceProtectionColumnIdArray(boolean isEditable) {
		if (isEditable) {
			return new Object[] { GtnFrameworkCommonConstants.CHECK_RECORD_ID, GtnFrameworkCommonConstants.ITEM_ID,
					GtnFrameworkCommonConstants.ITEM_NO, GtnFrameworkCommonConstants.ITEM_NAME,
					GtnFrameworkCommonConstants.BRAND_NAME, "psPPStatus", "psPPStartDate", "psPPEndDate",
					"psPPPriceType", "psNEP", "psNEPFormula", "psBasePriceType", "psBasePriceEntry", "psNetBasePrice",
					"psNetBasePriceFormulaId", "psSubseqPeriodPriceType", "psNetBSubseqPeriodPrice",
					"psNetBSubseqPriceFormulaId", "psToleranceInterval", "psToleranceFreq", "psToleranceType",
					"psDetailsPriceTol", "psMaxIncrementalChange", "psResetEligible", "psResetType", "psResetDate",
					"psResetInterval", "psResetFrequency", "psResetPriceType", "psNetResetPriceType",
					"psNetResetPriceFormulaId", "psNetPriceType", "psNetResetPriceFormulaName",
					GtnFrameworkCommonConstants.PS_ATTACHED_DATE };
		} else {
			return new Object[] { GtnFrameworkCommonConstants.ITEM_ID, GtnFrameworkCommonConstants.ITEM_NO,
					GtnFrameworkCommonConstants.ITEM_NAME, GtnFrameworkCommonConstants.BRAND_NAME, "psPPStatus",
					"psPPStartDate", "psPPEndDate", "psPPPriceType", "psNEP", "psNEPFormula", "psBasePriceType",
					"psBasePriceEntry", "psNetBasePrice", "psNetBasePriceFormulaId", "psSubseqPeriodPriceType",
					"psNetBSubseqPeriodPrice", "psNetBSubseqPriceFormulaId", "psToleranceInterval", "psToleranceFreq",
					"psToleranceType", "psDetailsPriceTol", "psMaxIncrementalChange", "psResetEligible", "psResetType",
					"psResetDate", "psResetInterval", "psResetFrequency", "psResetPriceType", "psNetResetPriceType",
					"psNetResetPriceFormulaId", "psNetPriceType", "psNetResetPriceFormulaName",
					GtnFrameworkCommonConstants.PS_ATTACHED_DATE };
		}

	}

	private String[] getPriceProtectionHeaderArray(boolean isEditable) {
		if (isEditable) {
			return new String[] { "", "Item Id", "Item No", GtnFrameworkCommonConstants.LABEL_ITEM_NAME, "Brand",
					"Price Protection Status", "Price Protection Start Date", "Price Protection End Date",
					"Measurement Price", "NEP", "NEP Formula", "Base Price Type", "Baseline WAC", "Baseline Net WAC",
					"Net Baseline WAC Formula", "Subsequent Period Price Type", "Net Subsequent Period Price",
					"NetSubsequent Price Formula", "Price Tolerance Interval", "Price Tolerance Frequency",
					"Price Tolerance Type", "Price Tolerance", "Max Incremental Change", "Reset Eligible", "Reset Type",
					"Reset Date", "Reset Interval", "Reset Frequency", "Reset Price Type", "Net Reset Price Type",
					"Net Reset Price Formula", "Net Price Type", "Net Price Type Formula", " AttachedDate" };
		} else {

			return new String[] { "Item Id", "Item No", GtnFrameworkCommonConstants.LABEL_ITEM_NAME, "Brand",
					"Price Protection Status", "Price Protection Start Date", "Price Protection End Date",
					"Measurement Price", "NEP", "NEP Formula", "Base Price Type", "Baseline WAC", "Baseline Net WAC",
					"Net Baseline WAC Formula", "Subsequent Period Price Type", "Net Subsequent Period Price",
					"NetSubsequent Price Formula", "Price Tolerance Interval", "Price Tolerance Frequency",
					"Price Tolerance Type", "Price Tolerance", "Max Incremental Change", "Reset Eligible", "Reset Type",
					"Reset Date", "Reset Interval", "Reset Frequency", "Reset Price Type", "Net Reset Price Type",
					"Net Reset Price Formula", "Net Price Type", "Net Price Type Formula", " AttachedDate" };
		}

	}

	private void resetTable(int systemId, String layoutName, boolean isEditable,
			GtnUIFrameworkComponentConfig componentConfig, GtnUIFrameworkPagedTableConfig pricingTabPagedTableConfig)
			throws GtnFrameworkGeneralException {
		componentConfig.setGtnPagedTableConfig(pricingTabPagedTableConfig);
		GtnUIFrameworkGlobalUI.addChildComponent(layoutName, Arrays.asList(componentConfig));
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(componentConfig.getComponentId());
		GtnUIFrameworkPagedTableLogic priceTabTableLogic = componentData.getCurrentPageTableLogic();

		List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList = getAdditionalCriteriaList(isEditable,
				systemId);
		setTableEnableDisable(isEditable);
		priceTabTableLogic.resetSearchCriteriaList();
		priceTabTableLogic.setAdditioanlSearchCriteriaList(additioanlSearchCriteriaList);
		priceTabTableLogic.startSearchProcess(new ArrayList<String>(), true);

	}

	private void configureTableURL(GtnUIFrameworkPagedTableConfig pricingTabPagedTableConfig) {
		pricingTabPagedTableConfig.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		pricingTabPagedTableConfig.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
	}

	private void configureDataLoadingType(String moduleAndQueryName, List<String> editableColumnPricingList,
			String checkRecordColum, GtnUIFrameworkPagedTableConfig pricingTabPagedTableConfig, boolean isEditable) {
		if (isEditable) {
			pricingTabPagedTableConfig.setEditableColumnList(editableColumnPricingList);
			pricingTabPagedTableConfig.setColumnCheckBoxId(checkRecordColum);
		} else {
			pricingTabPagedTableConfig.setEditableColumnList(Collections.<String>emptyList());
			pricingTabPagedTableConfig.setColumnCheckBoxId("");
		}
		pricingTabPagedTableConfig.setModuleName(moduleAndQueryName);
		pricingTabPagedTableConfig.setQueryName(moduleAndQueryName);
		pricingTabPagedTableConfig.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.PRICE_SCHEDULE);
	}

	private void loadTableMetaData(Object[] visibleColumnsPricingArray, String[] tableHeaderPricingArray,
			Class<?>[] tableHeaderPricingDataTypeArray, GtnUIFrameworkPagedTableConfig pricingTabPagedTableConfig) {
		pricingTabPagedTableConfig.setTableColumnMappingId(visibleColumnsPricingArray);
		pricingTabPagedTableConfig.setTableColumnDataType(tableHeaderPricingDataTypeArray);
		pricingTabPagedTableConfig.setTableVisibleHeader(tableHeaderPricingArray);
	}

	private GtnUIFrameworkPagedTableConfig getPagedTable(GtnUIFrameworkBaseComponent pricingResultDataTable) {
		return pricingResultDataTable.getComponentConfig().getGtnPagedTableConfig();

	}

	private List<GtnWebServiceSearchCriteria> getAdditionalCriteriaList(Boolean isView, int sysId) {
		List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList = new ArrayList<>();
		if (isView) {
			GtnWebServiceSearchCriteria userIdCriteria = new GtnWebServiceSearchCriteria();
			GtnWebServiceSearchCriteria sessionIdCriteria = new GtnWebServiceSearchCriteria();
			additioanlSearchCriteriaList.add(userIdCriteria);
			additioanlSearchCriteriaList.add(sessionIdCriteria);
			userIdCriteria.setFieldId("userId");
			userIdCriteria.setExpression(GtnFrameworkCommonConstants.PROPERTY_EQUALS);
			userIdCriteria.setFilterValue1(GtnUIFrameworkGlobalUI.getCurrentUser());
			sessionIdCriteria.setFieldId(GtnFrameworkCommonConstants.SESSION_ID);
			sessionIdCriteria.setExpression(GtnFrameworkCommonConstants.PROPERTY_EQUALS);
			sessionIdCriteria.setFilterValue1(
					GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SESSION_ID).toString());
		} else {
			GtnWebServiceSearchCriteria masterIdCriteria = new GtnWebServiceSearchCriteria();
			additioanlSearchCriteriaList.add(masterIdCriteria);
			masterIdCriteria.setFieldId("IMPSD.PS_MODEL_SID");
			masterIdCriteria.setExpression(GtnFrameworkCommonConstants.PROPERTY_EQUALS);
			masterIdCriteria.setFilterValue1(String.valueOf(sysId));

		}
		return additioanlSearchCriteriaList;
	}

	private void setTableEnableDisable(boolean isEditable) {

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.PS_PRICING_TAB_RESULT_DATA_TABLE)
				.getExtPagedTable().setReadOnly(!isEditable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.PS_PRICING_TAB_RESULT_DATA_TABLE)
				.getExtPagedTable().setEditable(isEditable);
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_RESULT_DATA_TABLE)
				.getExtPagedTable().setReadOnly(!isEditable);
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_RESULT_DATA_TABLE)
				.getExtPagedTable().setEditable(isEditable);

	}

	private void resetNotesTab() {
		GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
				.getVaadinComponent("notesTab");
		notesTab.refreshNotesTab();
	}

	private String[] getPriceProtectionViewHeaderArray(boolean isEditable) {
		if (isEditable) {
			return GtnFrameworkPSConstants.getPriceProtectionHeader();
		} else {
			return GtnFrameworkPSConstants.getPriceProtectionViewHeader();
		}

	}

	private Object[] getPriceProtectionViewColumnIdArray(boolean isEditable) {
		if (isEditable) {
			return GtnFrameworkPSConstants.getPriceProtectionEditableList().toArray();
		} else {

			return GtnFrameworkPSConstants.getPriceProtectionEditableList()
					.subList(1, GtnFrameworkPSConstants.getPriceProtectionEditableList().size()).toArray();
		}
	}

}
