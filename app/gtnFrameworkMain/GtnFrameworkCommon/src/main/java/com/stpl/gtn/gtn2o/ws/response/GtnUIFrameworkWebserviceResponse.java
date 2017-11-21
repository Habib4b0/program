package com.stpl.gtn.gtn2o.ws.response;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.rebateschedule.GtnWsRebateScheduleInfoBean;
import com.stpl.gtn.gtn2o.ws.response.authorization.GtnWsModuleAuthorizationGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.automaticrelationupdate.GtnFrameworkAutomaticRelationshipResponse;
import com.stpl.gtn.gtn2o.ws.response.calendarconfiguration.GtnWsCalendarConfigurationResponse;
import com.stpl.gtn.gtn2o.ws.response.cfpresponse.GtnWsCfpReponse;
import com.stpl.gtn.gtn2o.ws.response.cmresponse.GtnCompanyMasterResponse;
import com.stpl.gtn.gtn2o.ws.response.companygroupreponse.GtnWsCompanyGroupResponse;
import com.stpl.gtn.gtn2o.ws.response.compliance.GtnWsComplianceGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractDashboardResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractHeaderResponse;
import com.stpl.gtn.gtn2o.ws.response.duallistbox.GtnUIFrameworkWebserviceDualListBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.emailconfig.GtnWsMailConfigurationResponse;
import com.stpl.gtn.gtn2o.ws.response.filemanagement.GtnWsFileManagementResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastProjectionSubmitResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;
import com.stpl.gtn.gtn2o.ws.response.forecastconfiguration.GtnWsForecastConfigurationResponse;
import com.stpl.gtn.gtn2o.ws.response.ifpresponse.GtnWsIfpReponse;
import com.stpl.gtn.gtn2o.ws.response.itemgroupreponse.GtnWsItemGroupResponse;
import com.stpl.gtn.gtn2o.ws.response.itemmaster.GtnWsItemMasterResponse;
import com.stpl.gtn.gtn2o.ws.response.netsales.GtnWsNetSalesGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.pagetreetable.GtnWsPagedTreeTableResponse;
import com.stpl.gtn.gtn2o.ws.response.periodconfig.GtnWsPeriodConfigurationResponse;
import com.stpl.gtn.gtn2o.ws.response.priceschedule.GtnWsPriceScheduleGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.processmonitor.GtnWsProcessMonitorResponse;
import com.stpl.gtn.gtn2o.ws.response.rebateplan.GtnWsRebatePlanGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.relationshipbuilder.GtnWsRelationshipBuilderResponse;
import com.stpl.gtn.gtn2o.ws.response.transaction.GtnWsTransactionResponse;
import com.stpl.gtn.gtn2o.ws.response.workflow.GtnWsCommonWorkflowResponse;

public class GtnUIFrameworkWebserviceResponse {

	public GtnUIFrameworkWebserviceResponse() {
		super();
	}

	private List<String> itemValueList;
	private Map<String, Object> editRecord;
	private Object[] outBountData;
	private List<String> itemCodeList;
	private String responseStatus;

	private GtnUIFrameworkWebserviceComboBoxResponse gtnUIFrameworkWebserviceComboBoxResponse = null;
	private GtnUIFrameworkWebserviceDualListBoxResponse gtnUIFrameworkWebserviceDualListBoxResponse = null;
	private GtnUIFrameworkWebserviceTextBoxResponse gtnUIFrameworkWebserviceTextBoxResponse = null;
	private GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
	private GtnCompanyMasterResponse gtnCompanyMasterResponse;
	private GtnSerachResponse gtnSerachResponse;
	private GtnWsCfpReponse gtnWsCfpReponse;
	private GtnWsIfpReponse gtnWsIfpReponse;

	private GtnWsComplianceGeneralResponse gtnWsComplianceGeneralResponse;

	private GtnWsRebatePlanGeneralResponse gtnWsRebatePlanGeneralResponse;

	private GtnWsPriceScheduleGeneralResponse gtnWsPriceScheduleGeneralResponse;

	private GtnWsNetSalesGeneralResponse gtnWsNetSalesGeneralResponse;

	private GtnWsForecastResponse gtnWsForecastResponse;
	private GtnWsRebateScheduleInfoBean rebateScheduleInfoBean;
	private GtnWsItemGroupResponse gtnWsItemGroupResponse;
	private GtnWsCompanyGroupResponse gtnWsCompanyGroupResponse;
	private GtnWsItemMasterResponse gtnWsItemMasterResponse;
	private GtnWsContractHeaderResponse gtnWsContractHeaderResponse;
	private GtnWsContractDashboardResponse gtnWsContractDashboardResponse;
	private GtnWsForecastProjectionSubmitResponse gtnWsForecastProjectionSubmitResponse;
	private GtnWsTransactionResponse gtnWsTransactionResponse;
	private GtnWsForecastConfigurationResponse gtnWsForecastConfigurationResponse;
	private GtnWsProcessMonitorResponse gtnWsProcessMonitorResponse;
	private GtnWsRelationshipBuilderResponse gtnWsRelationshipBuilderResponse;
	private GtnWsExcelResponse gtnWsExcelResponse;
	private GtnWsPagedTreeTableResponse gtnWSPagedTreeTableResponse;
	private String exportFilePath;
	private GtnWsCommonWorkflowResponse gtnWSCommonWorkflowResponse;
	private GtnWsCalendarConfigurationResponse calendarConfigurationResponse;
	private GtnWsMailConfigurationResponse gtnWsMailConfigurationResponse;
	private GtnWsPeriodConfigurationResponse gtnWsPeriodConfigurationResponse;
	private GtnWsFileManagementResponse gtnWsFileManagementResponse;
	private GtnWsModuleAuthorizationGeneralResponse gtnWsModuleAuthorizationGeneralResponse;
	private GtnWsCsvExportResponse gtnWsCsvExportResponse;

	private GtnFrameworkAutomaticRelationshipResponse automaticRelationResponse;

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public GtnWsProcessMonitorResponse getGtnWsProcessMonitorResponse() {
		return gtnWsProcessMonitorResponse;
	}

	public void setGtnWsProcessMonitorResponse(GtnWsProcessMonitorResponse gtnWsProcessMonitorResponse) {
		this.gtnWsProcessMonitorResponse = gtnWsProcessMonitorResponse;
	}

	public List<String> getItemValueList() {
		return itemValueList == null ? itemValueList : Collections.unmodifiableList(itemValueList);
	}

	public GtnUIFrameworkWebserviceComboBoxResponse getGtnUIFrameworkWebserviceComboBoxResponse() {
		return gtnUIFrameworkWebserviceComboBoxResponse;
	}

	public void setGtnUIFrameworkWebserviceComboBoxResponse(
			GtnUIFrameworkWebserviceComboBoxResponse gtnUIFrameworkWebserviceComboBoxResponse) {
		this.gtnUIFrameworkWebserviceComboBoxResponse = gtnUIFrameworkWebserviceComboBoxResponse;
	}

	public GtnUIFrameworkWebserviceDualListBoxResponse getGtnUIFrameworkWebserviceDualListBoxResponse() {
		return gtnUIFrameworkWebserviceDualListBoxResponse;
	}

	public void setGtnUIFrameworkWebserviceDualListBoxResponse(
			GtnUIFrameworkWebserviceDualListBoxResponse gtnUIFrameworkWebserviceDualListBoxResponse) {
		this.gtnUIFrameworkWebserviceDualListBoxResponse = gtnUIFrameworkWebserviceDualListBoxResponse;
	}

	public GtnWsGeneralResponse getGtnWsGeneralResponse() {
		return gtnWsGeneralResponse;
	}

	public void setGtnWsGeneralResponse(GtnWsGeneralResponse gtnWsGeneralResponse) {
		this.gtnWsGeneralResponse = gtnWsGeneralResponse;
	}

	public GtnCompanyMasterResponse getGtnCompanyMasterResponse() {
		return gtnCompanyMasterResponse;
	}

	public void setGtnCompanyMasterResponse(GtnCompanyMasterResponse gtnCompanyMasterResponse) {
		this.gtnCompanyMasterResponse = gtnCompanyMasterResponse;
	}

	public void setItemValueList(List<String> itemValueList) {
		this.itemValueList = itemValueList == null ? itemValueList : Collections.unmodifiableList(itemValueList);
	}

	public Map<String, Object> getEditRecord() {
		return editRecord;
	}

	public void setEditRecord(Map<String, Object> editRecord) {
		this.editRecord = editRecord;
	}

	public Object[] getOutBountData() {
		return outBountData == null ? outBountData : outBountData.clone();
	}

	public void setOutBountData(Object[] outBountData) {
		this.outBountData = outBountData == null ? outBountData : outBountData.clone();
	}

	public List<String> getItemCodeList() {
		return itemCodeList == null ? itemCodeList : Collections.unmodifiableList(itemCodeList);
	}

	public void setItemCodeList(List<String> itemCodeList) {
		this.itemCodeList = itemCodeList == null ? itemCodeList : Collections.unmodifiableList(itemCodeList);
	}

	public GtnSerachResponse getGtnSerachResponse() {
		return gtnSerachResponse;
	}

	public void setGtnSerachResponse(GtnSerachResponse gtnSerachResponse) {
		this.gtnSerachResponse = gtnSerachResponse;
	}

	public GtnWsCfpReponse getGtnWsCfpReponse() {
		return gtnWsCfpReponse;
	}

	public void setGtnWsCfpReponse(GtnWsCfpReponse gtnWsCfpReponse) {
		this.gtnWsCfpReponse = gtnWsCfpReponse;
	}

	public GtnWsIfpReponse getGtnWsIfpReponse() {
		return gtnWsIfpReponse;
	}

	public void setGtnWsIfpReponse(GtnWsIfpReponse gtnWsIfpReponse) {
		this.gtnWsIfpReponse = gtnWsIfpReponse;
	}

	public GtnWsForecastResponse getGtnWsForecastResponse() {
		return gtnWsForecastResponse;
	}

	public void setGtnWsForecastResponse(GtnWsForecastResponse gtnWsForecastResponse) {
		this.gtnWsForecastResponse = gtnWsForecastResponse;
	}

	public GtnWsItemGroupResponse getGtnWsItemGroupResponse() {
		return gtnWsItemGroupResponse;
	}

	public void setGtnWsItemGroupResponse(GtnWsItemGroupResponse gtnWsItemGroupResponse) {
		this.gtnWsItemGroupResponse = gtnWsItemGroupResponse;
	}

	public GtnWsCompanyGroupResponse getGtnWsCompanyGroupResponse() {
		return gtnWsCompanyGroupResponse;
	}

	public void setGtnWsCompanyGroupResponse(GtnWsCompanyGroupResponse gtnWsCompanyGroupResponse) {
		this.gtnWsCompanyGroupResponse = gtnWsCompanyGroupResponse;
	}

	public GtnWsRebateScheduleInfoBean getRebateScheduleInfoBean() {
		return rebateScheduleInfoBean;
	}

	public void setRebateScheduleInfoBean(GtnWsRebateScheduleInfoBean rebateScheduleInfoBean) {
		this.rebateScheduleInfoBean = rebateScheduleInfoBean;
	}

	public GtnWsItemMasterResponse getGtnWsItemMasterResponse() {
		return gtnWsItemMasterResponse;
	}

	public void setGtnWsItemMasterResponse(GtnWsItemMasterResponse gtnWsItemMasterResponse) {
		this.gtnWsItemMasterResponse = gtnWsItemMasterResponse;
	}

	public GtnWsContractHeaderResponse getGtnWsContractHeaderResponse() {
		return gtnWsContractHeaderResponse;
	}

	public void setGtnWsContractHeaderResponse(GtnWsContractHeaderResponse gtnWsContractHeaderResponse) {
		this.gtnWsContractHeaderResponse = gtnWsContractHeaderResponse;
	}

	public GtnWsContractDashboardResponse getGtnWsContractDashboardResponse() {
		return gtnWsContractDashboardResponse;
	}

	public void setGtnWsContractDashboardResponse(GtnWsContractDashboardResponse gtnWsContractDashboardResponse) {
		this.gtnWsContractDashboardResponse = gtnWsContractDashboardResponse;
	}

	public GtnWsForecastProjectionSubmitResponse getGtnWsForecastProjectionSubmitResponse() {
		return gtnWsForecastProjectionSubmitResponse;
	}

	public void setGtnWsForecastProjectionSubmitResponse(
			GtnWsForecastProjectionSubmitResponse gtnWsForecastProjectionSubmitResponse) {
		this.gtnWsForecastProjectionSubmitResponse = gtnWsForecastProjectionSubmitResponse;
	}

	public GtnWsComplianceGeneralResponse getGtnWsComplianceGeneralResponse() {
		return gtnWsComplianceGeneralResponse;
	}

	public void setGtnWsComplianceGeneralResponse(GtnWsComplianceGeneralResponse gtnWsComplianceGeneralResponse) {
		this.gtnWsComplianceGeneralResponse = gtnWsComplianceGeneralResponse;
	}

	public GtnWsRebatePlanGeneralResponse getGtnWsRebatePlanGeneralResponse() {
		return gtnWsRebatePlanGeneralResponse;
	}

	public void setGtnWsRebatePlanGeneralResponse(GtnWsRebatePlanGeneralResponse gtnWsRebatePlanGeneralResponse) {
		this.gtnWsRebatePlanGeneralResponse = gtnWsRebatePlanGeneralResponse;
	}

	public GtnWsPriceScheduleGeneralResponse getGtnWsPriceScheduleGeneralResponse() {
		return gtnWsPriceScheduleGeneralResponse;
	}

	public void setGtnWsPriceScheduleGeneralResponse(
			GtnWsPriceScheduleGeneralResponse gtnWsPriceScheduleGeneralResponse) {
		this.gtnWsPriceScheduleGeneralResponse = gtnWsPriceScheduleGeneralResponse;
	}

	public GtnWsNetSalesGeneralResponse getGtnWsNetSalesGeneralResponse() {
		return gtnWsNetSalesGeneralResponse;
	}

	public void setGtnWsNetSalesGeneralResponse(GtnWsNetSalesGeneralResponse gtnWsNetSalesGeneralResponse) {
		this.gtnWsNetSalesGeneralResponse = gtnWsNetSalesGeneralResponse;
	}

	public GtnWsTransactionResponse getGtnWsTransactionResponse() {
		return gtnWsTransactionResponse;
	}

	public void setGtnWsTransactionResponse(GtnWsTransactionResponse gtnWsTransactionResponse) {
		this.gtnWsTransactionResponse = gtnWsTransactionResponse;
	}

	public GtnWsForecastConfigurationResponse getGtnWsForecastConfigurationResponse() {
		return gtnWsForecastConfigurationResponse;
	}

	public void setGtnWsForecastConfigurationResponse(
			GtnWsForecastConfigurationResponse gtnWsForecastConfigurationResponse) {
		this.gtnWsForecastConfigurationResponse = gtnWsForecastConfigurationResponse;
	}

	public GtnWsRelationshipBuilderResponse getGtnWsRelationshipBuilderResponse() {
		return gtnWsRelationshipBuilderResponse;
	}

	public void setGtnWsRelationshipBuilderResponse(GtnWsRelationshipBuilderResponse gtnWsRelationshipBuilderResponse) {
		this.gtnWsRelationshipBuilderResponse = gtnWsRelationshipBuilderResponse;
	}

	public GtnUIFrameworkWebserviceTextBoxResponse getGtnUIFrameworkWebserviceTextBoxResponse() {
		return gtnUIFrameworkWebserviceTextBoxResponse;
	}

	public void setGtnUIFrameworkWebserviceTextBoxResponse(
			GtnUIFrameworkWebserviceTextBoxResponse gtnUIFrameworkWebserviceTextBoxResponse) {
		this.gtnUIFrameworkWebserviceTextBoxResponse = gtnUIFrameworkWebserviceTextBoxResponse;
	}

	public GtnWsExcelResponse getGtnWsExcelResponse() {
		return gtnWsExcelResponse;
	}

	public void setGtnWsExcelResponse(GtnWsExcelResponse gtnWsExcelResponse) {
		this.gtnWsExcelResponse = gtnWsExcelResponse;
	}

	public GtnWsPagedTreeTableResponse getGtnWSPagedTreeTableResponse() {
		return gtnWSPagedTreeTableResponse;
	}

	public void setGtnWSPagedTreeTableResponse(GtnWsPagedTreeTableResponse gtnWSPagedTreeTableResponse) {
		this.gtnWSPagedTreeTableResponse = gtnWSPagedTreeTableResponse;
	}

	public GtnWsCommonWorkflowResponse getGtnWSCommonWorkflowResponse() {
		return gtnWSCommonWorkflowResponse;
	}

	public void setGtnWSCommonWorkflowResponse(GtnWsCommonWorkflowResponse gtnWSCommonWorkflowResponse) {
		this.gtnWSCommonWorkflowResponse = gtnWSCommonWorkflowResponse;
	}

	public String getExportFilePath() {
		return exportFilePath;
	}

	public void setExportFilePath(String exportFilePath) {
		this.exportFilePath = exportFilePath;
	}

	public GtnWsCalendarConfigurationResponse getCalendarConfigurationResponse() {
		return calendarConfigurationResponse;
	}

	public void setCalendarConfigurationResponse(GtnWsCalendarConfigurationResponse calendarConfigurationResponse) {
		this.calendarConfigurationResponse = calendarConfigurationResponse;
	}

	public GtnWsMailConfigurationResponse getGtnWsMailConfigurationResponse() {
		return gtnWsMailConfigurationResponse;
	}

	public void setGtnWsMailConfigurationResponse(GtnWsMailConfigurationResponse gtnWsMailConfigurationResponse) {
		this.gtnWsMailConfigurationResponse = gtnWsMailConfigurationResponse;
	}

	public GtnWsPeriodConfigurationResponse getGtnWsPeriodConfigurationResponse() {
		return gtnWsPeriodConfigurationResponse;
	}

	public void setGtnWsPeriodConfigurationResponse(GtnWsPeriodConfigurationResponse gtnWsPeriodConfigurationResponse) {
		this.gtnWsPeriodConfigurationResponse = gtnWsPeriodConfigurationResponse;
	}

	public GtnWsFileManagementResponse getGtnWsFileManagementResponse() {
		return gtnWsFileManagementResponse;
	}

	public void setGtnWsFileManagementResponse(GtnWsFileManagementResponse gtnWsFileManagementResponse) {
		this.gtnWsFileManagementResponse = gtnWsFileManagementResponse;
	}

	public GtnWsModuleAuthorizationGeneralResponse getGtnWsModuleAuthorizationGeneralResponse() {
		return gtnWsModuleAuthorizationGeneralResponse;
	}

	public void setGtnWsModuleAuthorizationGeneralResponse(
			GtnWsModuleAuthorizationGeneralResponse gtnWsModuleSecurityGeneralResponse) {
		this.gtnWsModuleAuthorizationGeneralResponse = gtnWsModuleSecurityGeneralResponse;
	}

	public GtnWsCsvExportResponse getGtnWsCsvExportResponse() {
		return gtnWsCsvExportResponse;
	}

	public void setGtnWsCsvExportResponse(GtnWsCsvExportResponse gtnWsCsvExportResponse) {
		this.gtnWsCsvExportResponse = gtnWsCsvExportResponse;
	}

	public GtnFrameworkAutomaticRelationshipResponse getAutomaticRelationResponse() {
		return automaticRelationResponse;
	}

	public void setAutomaticRelationResponse(GtnFrameworkAutomaticRelationshipResponse automaticRelationResponse) {
		this.automaticRelationResponse = automaticRelationResponse;
	}

}
