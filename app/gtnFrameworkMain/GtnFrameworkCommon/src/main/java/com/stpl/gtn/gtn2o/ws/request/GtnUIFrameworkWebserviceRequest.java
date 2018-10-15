package com.stpl.gtn.gtn2o.ws.request;

import com.stpl.gtn.gtn2o.ws.request.adjustmentdetails.GtnWsAdjusmentDetailsRequest;
import com.stpl.gtn.gtn2o.ws.request.adjustmentdetails.GtnWsAdjustmentDetailsSaveViewMasterRequest;
import com.stpl.gtn.gtn2o.ws.request.arm.GtnWsArmRequest;
import com.stpl.gtn.gtn2o.ws.request.authorization.GtnWsModuleAuthorizationGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.automaticrelationupdate.GtnFrameworkAutomaticRelationshipRequest;
import com.stpl.gtn.gtn2o.ws.request.balancesummaryrequest.GtnWSAdjusmentSummaryRequest;
import com.stpl.gtn.gtn2o.ws.request.balancesummaryrequest.dataselection.GtnBSTimePeriodRequest;
import com.stpl.gtn.gtn2o.ws.request.bcp.GtnWsBcpServiceRequest;
import com.stpl.gtn.gtn2o.ws.request.calendarconfiguration.GtnWsCalendarConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.request.cff.GtnWsCFFSubmitRequest;
import com.stpl.gtn.gtn2o.ws.request.cfprequest.GtnWsCfpRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;
import com.stpl.gtn.gtn2o.ws.request.companygroup.GtnCompanyGroupRequest;
import com.stpl.gtn.gtn2o.ws.request.compliancededuction.GtnWsComplianceGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractHeaderRequest;
import com.stpl.gtn.gtn2o.ws.request.customview.GtnWsCustomViewRequest;
import com.stpl.gtn.gtn2o.ws.request.dataselectionedit.GtnWsForecastHierarchyInsertRequest;
import com.stpl.gtn.gtn2o.ws.request.deductioncalendar.GtnWsDeductionCalendarRequest;
import com.stpl.gtn.gtn2o.ws.request.duallistbox.GTNUIFrameworkDualListBoxRequest;
import com.stpl.gtn.gtn2o.ws.request.emailconfig.GtnWsMailConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.request.filemanagement.GtnWsFileManagementRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastProjectionSubmitRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.newarch.GtnWsForecastNewArchRequest;
import com.stpl.gtn.gtn2o.ws.request.forecastconfiguration.GtnWsForecastConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.request.forecastingsearch.GtnGeneralSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;
import com.stpl.gtn.gtn2o.ws.request.itemgroup.GtnWsItemGroupRequest;
import com.stpl.gtn.gtn2o.ws.request.itemmaster.GtnWsItemMasterRequest;
import com.stpl.gtn.gtn2o.ws.request.netsales.GtnWsNetSalesFormulaGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.pagetreetable.GtnWsPagedTreeTableRequest;
import com.stpl.gtn.gtn2o.ws.request.periodconfig.GtnWsPeriodConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.request.priceschedule.GtnWsPriceScheduleGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.processmonitor.GtnWsProcessMonitorRequest;
import com.stpl.gtn.gtn2o.ws.request.processscheduler.GtnWsProcessSchedulerRequest;
import com.stpl.gtn.gtn2o.ws.request.rebateplan.GtnWsRebatePlanGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.rebateschedule.GtnWsRebateScheduleGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.relationshipbuilder.GtnWsRelationshipBuilderRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.request.transaction.GtnWsTransactionRequest;
import com.stpl.gtn.gtn2o.ws.request.udc.GtnWsUdcRequest;
import com.stpl.gtn.gtn2o.ws.request.workflow.GtnWsCommonWorkflowRequest;

public class GtnUIFrameworkWebserviceRequest {

    public GtnUIFrameworkWebserviceRequest() {
        super();
    }

    private GtnBSTimePeriodRequest gtnBSTimePeriodRequest;

    private GTNUIFrameworkDualListBoxRequest gTNUIFrameworkDualListBoxRequest;

    private GtnWsGeneralRequest gtnWsGeneralRequest;

    private GtnCMasterRequest gtnCMasterRequest;

    private GtnWsSearchRequest gtnWsSearchRequest;

    private GtnWSAdjusmentSummaryRequest gtnWSAdjusmentSummaryRequest;

    private GtnWsCfpRequest gtnWsCfpRequest;

    private GtnWsIfpRequest gtnWsIfpRequest;

    private GtnWsForecastRequest gtnWsForecastRequest;
    
    private GtnWsForecastNewArchRequest gtnWsForecastNewArchRequest;

    private GtnWsArmRequest gtnWsArmRequest;

    private GtnCompanyGroupRequest gtnCompanyGroupRequest;

    private GtnWsCheckAllUpdateRequest gtnWsCheckAllUpdateRequest;

    private GtnWsComplianceGeneralRequest gtnWsComplianceGeneralRequest;

    private GtnWsRebatePlanGeneralRequest gtnWsRebatePlanGeneralRequest;

    private GtnWsPriceScheduleGeneralRequest gtnWsPriceScheduleGeneralRequest;

    private GtnWsRebateScheduleGeneralRequest gtnWsRebateScheduleGeneralRequest;

    private GtnWsItemGroupRequest gtnWsItemGroupRequest;

    private GtnWsItemMasterRequest gtnWsItemMasterRequest;

    private GtnWsForecastProjectionSubmitRequest gtnWsForecastProjectionSubmitRequest;

    private GtnWsCFFSubmitRequest gtnCffsubmitRequest;

    private GtnWsNetSalesFormulaGeneralRequest gtnWsNetSalesGeneralRequest;

    private GtnWsContractHeaderRequest gtnWsContractHeaderRequest;

    private GtnWsContractDashboardRequest gtnWsContractDashboardRequest;

    private GtnWsDeductionCalendarRequest deductionCalendarRequest;

    private GtnwsExcelRequest gtnwsExcelRequest;

    private GtnWsTransactionRequest gtnWsTransactionRequest;

    private GtnWsForecastConfigurationRequest forecastConfigurationRequest;

    private GtnWsProcessMonitorRequest processMonitorRequest;

    private GtnWsProcessSchedulerRequest processSchedulerRequest;

    private GtnWsRelationshipBuilderRequest relationshipBuilderRequest;

    private GtnWsPagedTreeTableRequest gtnWsPagedTreeTableRequest;

    private GtnWsCommonWorkflowRequest gtnWSCommonWorkflowRequest;

    private GtnWsCalendarConfigurationRequest calendarConfigurationRequest;

    private GtnWsMailConfigurationRequest emailConfigurationRequest;

    private GtnWsPeriodConfigurationRequest gtnWsPeriodConfigurationRequest;

    private GtnWsFileManagementRequest gtnWsFileManagementRequest;

    private GtnWsModuleAuthorizationGeneralRequest gtnWsModuleAuthorizationGeneralRequest;

    private GtnFrameworkAutomaticRelationshipRequest automaticRelationEequest;
    private GtnWsCsvExportRequest gtnWsCsvExportRequest;

    private GtnWsBcpServiceRequest gtnWsBcpServiceRequest;
    private GtnWsUdcRequest gtnWsUdcRequest;

    private GtnWsAdjustmentDetailsSaveViewMasterRequest gtnWsAdjustmentDetailsSaveViewMasterRequest;
    
    private GtnGeneralSearchRequest GtnGeneralSearchRequest;

    public GtnGeneralSearchRequest getGtnGeneralSearchRequest() {
        return GtnGeneralSearchRequest;
    }

    public void setGtnGeneralSearchRequest(GtnGeneralSearchRequest GtnGeneralSearchRequest) {
        this.GtnGeneralSearchRequest = GtnGeneralSearchRequest;
    }

    public GtnWsSearchRequest getGtnWsSearchRequest() {
        return gtnWsSearchRequest;
    }
    private GtnWsForecastHierarchyInsertRequest gtnWshirarchyInsertRequest;

    private GtnWsAttachmentRequest gtnWsAttachmentRequest;

    private GtnWsReportRequest gtnWsReportRequest;
    private GtnWsCustomViewRequest gtnWsCustomViewRequest;

    private GtnWsAdjusmentDetailsRequest gtnWsAdjusmentDetailsRequest;

    private GtnServiceRegistryWsRequest gtnServiceRegistryWsRequest;
    
    public GtnWsForecastHierarchyInsertRequest getGtnWshirarchyInsertRequest() {
        return gtnWshirarchyInsertRequest;
    }

    public void setGtnWshirarchyInsertRequest(GtnWsForecastHierarchyInsertRequest gtnWshirarchyInsertRequest) {
        this.gtnWshirarchyInsertRequest = gtnWshirarchyInsertRequest;
    }

    public GtnBSTimePeriodRequest getGtnBSTimePeriodRequest() {
        return gtnBSTimePeriodRequest;
    }

    public void setGtnBSTimePeriodRequest(GtnBSTimePeriodRequest gtnBSTimePeriodRequest) {
        this.gtnBSTimePeriodRequest = gtnBSTimePeriodRequest;
    }

    public GTNUIFrameworkDualListBoxRequest getgTNUIFrameworkDualListBoxRequest() {
        return gTNUIFrameworkDualListBoxRequest;
    }

    public void setgTNUIFrameworkDualListBoxRequest(GTNUIFrameworkDualListBoxRequest gTNUIFrameworkDualListBoxRequest) {
        this.gTNUIFrameworkDualListBoxRequest = gTNUIFrameworkDualListBoxRequest;
    }

    public GtnWsGeneralRequest getGtnWsGeneralRequest() {
        return gtnWsGeneralRequest;
    }

    public void setGtnWsGeneralRequest(GtnWsGeneralRequest gtnWsGeneralRequest) {
        this.gtnWsGeneralRequest = gtnWsGeneralRequest;
    }

    public GtnCMasterRequest getGtnCMasterRequest() {
        return gtnCMasterRequest;
    }

    public void setGtnCMasterRequest(GtnCMasterRequest gtnCMasterRequest) {
        this.gtnCMasterRequest = gtnCMasterRequest;
    }

    public void setGtnWsSearchRequest(GtnWsSearchRequest gtnWsSearchRequest) {
        this.gtnWsSearchRequest = gtnWsSearchRequest;
    }

    public GtnWSAdjusmentSummaryRequest getGtnWSAdjusmentSummaryRequest() {
        return gtnWSAdjusmentSummaryRequest;
    }

    public void setGtnWSAdjusmentSummaryRequest(GtnWSAdjusmentSummaryRequest gtnWSAdjusmentSummaryRequest) {
        this.gtnWSAdjusmentSummaryRequest = gtnWSAdjusmentSummaryRequest;
    }

    public GtnWsCfpRequest getGtnWsCfpRequest() {
        return gtnWsCfpRequest;
    }

    public void setGtnWsCfpRequest(GtnWsCfpRequest gtnWsCfpRequest) {
        this.gtnWsCfpRequest = gtnWsCfpRequest;
    }

    public GtnWsIfpRequest getGtnWsIfpRequest() {
        return gtnWsIfpRequest;
    }

    public void setGtnWsIfpRequest(GtnWsIfpRequest gtnWsIfpRequest) {
        this.gtnWsIfpRequest = gtnWsIfpRequest;
    }

    public GtnCompanyGroupRequest getGtnCompanyGroupRequest() {
        return gtnCompanyGroupRequest;
    }

    public void setGtnCompanyGroupRequest(GtnCompanyGroupRequest gtnCompanyGroupRequest) {
        this.gtnCompanyGroupRequest = gtnCompanyGroupRequest;
    }

    public GtnWsForecastRequest getGtnWsForecastRequest() {
        return gtnWsForecastRequest;
    }

    public void setGtnWsForecastRequest(GtnWsForecastRequest gtnWsForecastRequest) {
        this.gtnWsForecastRequest = gtnWsForecastRequest;
    }
    
    public GtnWsForecastNewArchRequest getGtnWsForecastNewArchRequest() {
        return gtnWsForecastNewArchRequest;
    }

    public void setGtnWsForecastNewArchRequest(GtnWsForecastNewArchRequest gtnWsForecastNewArchRequest) {
        this.gtnWsForecastNewArchRequest = gtnWsForecastNewArchRequest;
    }


    public GtnWsCheckAllUpdateRequest getGtnWsCheckAllUpdateRequest() {
        return gtnWsCheckAllUpdateRequest;
    }

    public void setGtnWsCheckAllUpdateRequest(GtnWsCheckAllUpdateRequest gtnWsCheckAllUpdateRequest) {
        this.gtnWsCheckAllUpdateRequest = gtnWsCheckAllUpdateRequest;
    }

    public GtnWsItemGroupRequest getGtnWsItemGroupRequest() {
        return gtnWsItemGroupRequest;
    }

    public void setGtnWsItemGroupRequest(GtnWsItemGroupRequest gtnWsItemGroupRequest) {
        this.gtnWsItemGroupRequest = gtnWsItemGroupRequest;
    }

    public GtnWsItemMasterRequest getGtnWsItemMasterRequest() {
        return gtnWsItemMasterRequest;
    }

    public void setGtnWsItemMasterRequest(GtnWsItemMasterRequest gtnWsItemMasterRequest) {
        this.gtnWsItemMasterRequest = gtnWsItemMasterRequest;
    }

    public GtnWsContractHeaderRequest getGtnWsContractHeaderRequest() {
        return gtnWsContractHeaderRequest;
    }

    public void setGtnWsContractHeaderRequest(GtnWsContractHeaderRequest gtnWsContractHeaderRequest) {
        this.gtnWsContractHeaderRequest = gtnWsContractHeaderRequest;
    }

    public GtnWsContractDashboardRequest getGtnWsContractDashboardRequest() {
        return gtnWsContractDashboardRequest;
    }

    public GtnWsCFFSubmitRequest getGtnCffsubmitRequest() {
        return gtnCffsubmitRequest;
    }

    public void setGtnCffsubmitRequest(GtnWsCFFSubmitRequest gtnCffsubmitRequest) {
        this.gtnCffsubmitRequest = gtnCffsubmitRequest;
    }

    public GtnWsReportRequest getGtnWsReportRequest() {
        return gtnWsReportRequest;
    }

    public void setGtnWsReportRequest(GtnWsReportRequest gtnWsReportRequest) {
        this.gtnWsReportRequest = gtnWsReportRequest;
    }

    public void setGtnWsContractDashboardRequest(GtnWsContractDashboardRequest gtnWsContractDashboardRequest) {
        this.gtnWsContractDashboardRequest = gtnWsContractDashboardRequest;
    }

    public GtnWsForecastProjectionSubmitRequest getGtnWsForecastProjectionSubmitRequest() {
        return gtnWsForecastProjectionSubmitRequest;
    }

    public void setGtnWsForecastProjectionSubmitRequest(
            GtnWsForecastProjectionSubmitRequest gtnWsForecastProjectionSubmitRequest) {
        this.gtnWsForecastProjectionSubmitRequest = gtnWsForecastProjectionSubmitRequest;
    }

    public GtnWsDeductionCalendarRequest getDeductionCalendarRequest() {
        return deductionCalendarRequest;
    }

    public void setDeductionCalendarRequest(GtnWsDeductionCalendarRequest deductionCalendarRequest) {
        this.deductionCalendarRequest = deductionCalendarRequest;
    }

    public GtnWsNetSalesFormulaGeneralRequest getGtnWsNetSalesGeneralRequest() {
        return gtnWsNetSalesGeneralRequest;
    }

    public void setGtnWsNetSalesGeneralRequest(GtnWsNetSalesFormulaGeneralRequest gtnWsNetSalesGeneralRequest) {
        this.gtnWsNetSalesGeneralRequest = gtnWsNetSalesGeneralRequest;
    }

    public GtnWsComplianceGeneralRequest getGtnWsComplianceGeneralRequest() {
        return gtnWsComplianceGeneralRequest;
    }

    public void setGtnWsComplianceGeneralRequest(GtnWsComplianceGeneralRequest gtnWsComplianceGeneralRequest) {
        this.gtnWsComplianceGeneralRequest = gtnWsComplianceGeneralRequest;
    }

    public GtnWsRebatePlanGeneralRequest getGtnWsRebatePlanGeneralRequest() {
        return gtnWsRebatePlanGeneralRequest;
    }

    public void setGtnWsRebatePlanGeneralRequest(GtnWsRebatePlanGeneralRequest gtnWsRebatePlanGeneralRequest) {
        this.gtnWsRebatePlanGeneralRequest = gtnWsRebatePlanGeneralRequest;
    }

    public GtnWsPriceScheduleGeneralRequest getGtnWsPriceScheduleGeneralRequest() {
        return gtnWsPriceScheduleGeneralRequest;
    }

    public void setGtnWsPriceScheduleGeneralRequest(GtnWsPriceScheduleGeneralRequest gtnWsPriceScheduleGeneralRequest) {
        this.gtnWsPriceScheduleGeneralRequest = gtnWsPriceScheduleGeneralRequest;
    }

    public GtnWsRebateScheduleGeneralRequest getGtnWsRebateScheduleGeneralRequest() {
        return gtnWsRebateScheduleGeneralRequest;
    }

    public void setGtnWsRebateScheduleGeneralRequest(
            GtnWsRebateScheduleGeneralRequest gtnWsRebateScheduleGeneralRequest) {
        this.gtnWsRebateScheduleGeneralRequest = gtnWsRebateScheduleGeneralRequest;
    }

    public GtnWsTransactionRequest getGtnWsTransactionRequest() {
        return gtnWsTransactionRequest;
    }

    public GtnwsExcelRequest getGtnwsExcelRequest() {
        return gtnwsExcelRequest;
    }

    public void setGtnWsTransactionRequest(GtnWsTransactionRequest gtnWsTransactionRequest) {
        this.gtnWsTransactionRequest = gtnWsTransactionRequest;
    }

    public void setGtnwsExcelRequest(GtnwsExcelRequest gtnwsExcelRequest) {
        this.gtnwsExcelRequest = gtnwsExcelRequest;
    }

    public GtnWsForecastConfigurationRequest getForecastConfigurationRequest() {
        return forecastConfigurationRequest;
    }

    public void setForecastConfigurationRequest(GtnWsForecastConfigurationRequest forecastConfigurationRequest) {
        this.forecastConfigurationRequest = forecastConfigurationRequest;
    }

    public GtnWsRelationshipBuilderRequest getRelationshipBuilderRequest() {
        return relationshipBuilderRequest;
    }

    public void setRelationshipBuilderRequest(GtnWsRelationshipBuilderRequest relationshipBuilderRequest) {
        this.relationshipBuilderRequest = relationshipBuilderRequest;
    }

    public GtnWsPagedTreeTableRequest getGtnWsPagedTreeTableRequest() {
        return gtnWsPagedTreeTableRequest;
    }

    public void setGtnWsPagedTreeTableRequest(GtnWsPagedTreeTableRequest gtnWsPagedTreeTableRequest) {
        this.gtnWsPagedTreeTableRequest = gtnWsPagedTreeTableRequest;
    }

    public GtnWsCommonWorkflowRequest getGtnWSCommonWorkflowRequest() {
        return gtnWSCommonWorkflowRequest;
    }

    public void setGtnWSCommonWorkflowRequest(GtnWsCommonWorkflowRequest gtnWSCommonWorkflowRequest) {
        this.gtnWSCommonWorkflowRequest = gtnWSCommonWorkflowRequest;
    }

    public GtnWsCalendarConfigurationRequest getCalendarConfigurationRequest() {
        return calendarConfigurationRequest;
    }

    public void setCalendarConfigurationRequest(GtnWsCalendarConfigurationRequest calendarConfigurationRequest) {
        this.calendarConfigurationRequest = calendarConfigurationRequest;
    }

    public GtnWsProcessMonitorRequest getProcessMonitorRequest() {
        return processMonitorRequest;
    }

    public void setProcessMonitorRequest(GtnWsProcessMonitorRequest processMonitorRequest) {
        this.processMonitorRequest = processMonitorRequest;
    }

    public GtnWsProcessSchedulerRequest getProcessSchedulerRequest() {
        return processSchedulerRequest;
    }

    public void setProcessSchedulerRequest(GtnWsProcessSchedulerRequest processSchedulerRequest) {
        this.processSchedulerRequest = processSchedulerRequest;
    }

    public GtnWsMailConfigurationRequest getMailConfigurationRequest() {
        return emailConfigurationRequest;
    }

    public void setMailConfigurationRequest(GtnWsMailConfigurationRequest emailConfigurationRequest) {
        this.emailConfigurationRequest = emailConfigurationRequest;
    }

    public GtnWsPeriodConfigurationRequest getGtnWsPeriodConfigurationRequest() {
        return gtnWsPeriodConfigurationRequest;
    }

    public void setGtnWsPeriodConfigurationRequest(GtnWsPeriodConfigurationRequest gtnWsPeriodConfigurationRequest) {
        this.gtnWsPeriodConfigurationRequest = gtnWsPeriodConfigurationRequest;
    }

    public GtnWsFileManagementRequest getGtnWsFileManagementRequest() {
        return gtnWsFileManagementRequest;
    }

    public void setGtnWsFileManagementRequest(GtnWsFileManagementRequest gtnWsFileManagementRequest) {
        this.gtnWsFileManagementRequest = gtnWsFileManagementRequest;
    }

    public GtnWsModuleAuthorizationGeneralRequest getGtnWsModuleAuthorizationGeneralRequest() {
        return gtnWsModuleAuthorizationGeneralRequest;
    }

    public void setGtnWsModuleAuthorizationGeneralRequest(
            GtnWsModuleAuthorizationGeneralRequest gtnWsModuleAuthorizationGeneralRequest) {
        this.gtnWsModuleAuthorizationGeneralRequest = gtnWsModuleAuthorizationGeneralRequest;
    }

    public GtnFrameworkAutomaticRelationshipRequest getAutomaticRelationEequest() {
        return automaticRelationEequest;
    }

    public void setAutomaticRelationEequest(GtnFrameworkAutomaticRelationshipRequest automaticRelationEequest) {
        this.automaticRelationEequest = automaticRelationEequest;
    }

    public GtnWsCsvExportRequest getGtnWsCsvExportRequest() {
        return gtnWsCsvExportRequest;
    }

    public void setGtnWsCsvExportRequest(GtnWsCsvExportRequest gtnWsCsvExportRequest) {
        this.gtnWsCsvExportRequest = gtnWsCsvExportRequest;
    }

    public GtnWsBcpServiceRequest getGtnWsBcpServiceRequest() {
        return gtnWsBcpServiceRequest;
    }

    public void setGtnWsBcpServiceRequest(GtnWsBcpServiceRequest gtnWsBcpServiceRequest) {
        this.gtnWsBcpServiceRequest = gtnWsBcpServiceRequest;
    }

    public GtnWsUdcRequest getGtnWsUdcRequest() {
        return gtnWsUdcRequest;
    }

    public void setGtnWsUdcRequest(GtnWsUdcRequest gtnWsUdcRequest) {
        this.gtnWsUdcRequest = gtnWsUdcRequest;
    }

    public GtnWsAttachmentRequest getGtnWsAttachmentRequest() {
        return gtnWsAttachmentRequest;
    }

    public void setGtnWsAttachmentRequest(GtnWsAttachmentRequest gtnWsAttachmentRequest) {
        this.gtnWsAttachmentRequest = gtnWsAttachmentRequest;
    }

    public GtnWsCustomViewRequest getGtnWsCustomViewRequest() {
        return gtnWsCustomViewRequest;
    }

    public void setGtnWsCustomViewRequest(GtnWsCustomViewRequest gtnWsCustomViewRequest) {
        this.gtnWsCustomViewRequest = gtnWsCustomViewRequest;
    }

    public GtnWsArmRequest getGtnWsArmRequest() {
        return gtnWsArmRequest;
    }

    public void setGtnWsArmRequest(GtnWsArmRequest gtnWsArmRequest) {
        this.gtnWsArmRequest = gtnWsArmRequest;
    }

    public GtnWsAdjusmentDetailsRequest getGtnWsAdjusmentDetailsRequest() {
        return gtnWsAdjusmentDetailsRequest;
    }

    public void setGtnWsAdjusmentDetailsRequest(GtnWsAdjusmentDetailsRequest gtnWsAdjusmentDetailsRequest) {
        this.gtnWsAdjusmentDetailsRequest = gtnWsAdjusmentDetailsRequest;
    }

    public GtnWsAdjustmentDetailsSaveViewMasterRequest getGtnWsAdjustmentDetailsSaveViewMasterRequest() {
        return gtnWsAdjustmentDetailsSaveViewMasterRequest;
    }

    public void setGtnWsAdjustmentDetailsSaveViewMasterRequest(GtnWsAdjustmentDetailsSaveViewMasterRequest gtnWsAdjustmentDetailsSaveViewMasterRequest) {
        this.gtnWsAdjustmentDetailsSaveViewMasterRequest = gtnWsAdjustmentDetailsSaveViewMasterRequest;
    }

	public GtnServiceRegistryWsRequest getGtnServiceRegistryWsRequest() {
		return gtnServiceRegistryWsRequest;
	}

	public void setGtnServiceRegistryWsRequest(GtnServiceRegistryWsRequest gtnServiceRegistryWsRequest) {
		this.gtnServiceRegistryWsRequest = gtnServiceRegistryWsRequest;
	}

}
