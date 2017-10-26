package com.stpl.app.service.messaging;

import com.stpl.app.service.AccrualDetailsLocalServiceUtil;
import com.stpl.app.service.AccrualMasterLocalServiceUtil;
import com.stpl.app.service.ActualsMasterLocalServiceUtil;
import com.stpl.app.service.AdditionalNotesLocalServiceUtil;
import com.stpl.app.service.AuditMasterInboundLocalServiceUtil;
import com.stpl.app.service.AverageShelfLifeMasterLocalServiceUtil;
import com.stpl.app.service.BestPriceMasterLocalServiceUtil;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.BusinessroleMasterLocalServiceUtil;
import com.stpl.app.service.BusinessroleModuleLocalServiceUtil;
import com.stpl.app.service.CcpDetailsLocalServiceUtil;
import com.stpl.app.service.CcpMapLocalServiceUtil;
import com.stpl.app.service.CdrDetailsLocalServiceUtil;
import com.stpl.app.service.CdrModelLocalServiceUtil;
import com.stpl.app.service.CfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.CfpContractLocalServiceUtil;
import com.stpl.app.service.CfpDetailsLocalServiceUtil;
import com.stpl.app.service.CfpModelLocalServiceUtil;
import com.stpl.app.service.ChActualDiscountLocalServiceUtil;
import com.stpl.app.service.ChActualSalesLocalServiceUtil;
import com.stpl.app.service.ChAssumptionsLocalServiceUtil;
import com.stpl.app.service.ChDiscountProjMasterLocalServiceUtil;
import com.stpl.app.service.ChProjectionDiscountLocalServiceUtil;
import com.stpl.app.service.ChProjectionSelectionLocalServiceUtil;
import com.stpl.app.service.ChSalesProjectionLocalServiceUtil;
import com.stpl.app.service.ChSalesProjectionMasterLocalServiceUtil;
import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.CompanyGroupDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyGroupLocalServiceUtil;
import com.stpl.app.service.CompanyIdentifierLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.CompanyParentDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyQualifierLocalServiceUtil;
import com.stpl.app.service.CompanyTradeClassLocalServiceUtil;
import com.stpl.app.service.ContractAliasMasterLocalServiceUtil;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.app.service.CpiIndexMasterLocalServiceUtil;
import com.stpl.app.service.CustomViewDetailsLocalServiceUtil;
import com.stpl.app.service.CustomViewMasterLocalServiceUtil;
import com.stpl.app.service.DeductionCalendarDetailsLocalServiceUtil;
import com.stpl.app.service.DeductionCalendarMasterLocalServiceUtil;
import com.stpl.app.service.DeductionDetailsLocalServiceUtil;
import com.stpl.app.service.DeductionGroupDetailsLocalServiceUtil;
import com.stpl.app.service.DeductionGroupLocalServiceUtil;
import com.stpl.app.service.DemandForecastLocalServiceUtil;
import com.stpl.app.service.DocDetailsLocalServiceUtil;
import com.stpl.app.service.FcpActualsLocalServiceUtil;
import com.stpl.app.service.FcpProjLocalServiceUtil;
import com.stpl.app.service.FederalNewNdcLocalServiceUtil;
import com.stpl.app.service.FileManagementLocalServiceUtil;
import com.stpl.app.service.ForecastConfigLocalServiceUtil;
import com.stpl.app.service.ForecastingFormulaLocalServiceUtil;
import com.stpl.app.service.ForecastingMasterLocalServiceUtil;
import com.stpl.app.service.ForecastingViewMasterLocalServiceUtil;
import com.stpl.app.service.FormulaDetailsMasterLocalServiceUtil;
import com.stpl.app.service.GcmCompanyDetailsLocalServiceUtil;
import com.stpl.app.service.GcmCompanyLinkLocalServiceUtil;
import com.stpl.app.service.GcmContractDetailsLocalServiceUtil;
import com.stpl.app.service.GcmGlobalDetailsLocalServiceUtil;
import com.stpl.app.service.GcmItemDetailsLocalServiceUtil;
import com.stpl.app.service.GlBalanceMasterLocalServiceUtil;
import com.stpl.app.service.GlCostCenterMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.HierarchyDefinitionLocalServiceUtil;
import com.stpl.app.service.HierarchyLevelDefinitionLocalServiceUtil;
import com.stpl.app.service.HierarchyLevelValuesLocalServiceUtil;
import com.stpl.app.service.HistCompanyGroupDetailsLocalServiceUtil;
import com.stpl.app.service.HistCompanyGroupLocalServiceUtil;
import com.stpl.app.service.HistHierarchyDefinitionLocalServiceUtil;
import com.stpl.app.service.HistHierarchyLevelDefnLocalServiceUtil;
import com.stpl.app.service.HistHierarchyLevelValuesLocalServiceUtil;
import com.stpl.app.service.HistItemGroupDetailsLocalServiceUtil;
import com.stpl.app.service.HistItemGroupLocalServiceUtil;
import com.stpl.app.service.HistRelationshipBuilderLocalServiceUtil;
import com.stpl.app.service.HistRelationshipLevelDefnLocalServiceUtil;
import com.stpl.app.service.HistWorkflowMasterLocalServiceUtil;
import com.stpl.app.service.IfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.IfpContractLocalServiceUtil;
import com.stpl.app.service.IfpDetailsLocalServiceUtil;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.ImtdCfpDetailsLocalServiceUtil;
import com.stpl.app.service.ImtdDeductionDetailsLocalServiceUtil;
import com.stpl.app.service.ImtdIfpDetailsLocalServiceUtil;
import com.stpl.app.service.ImtdItemPriceRebateDetailsLocalServiceUtil;
import com.stpl.app.service.ImtdLevelValuesLocalServiceUtil;
import com.stpl.app.service.ImtdPsDetailsLocalServiceUtil;
import com.stpl.app.service.ImtdRsContractDetailsFrLocalServiceUtil;
import com.stpl.app.service.ImtdRsDetailsFrLocalServiceUtil;
import com.stpl.app.service.ImtdRsDetailsLocalServiceUtil;
import com.stpl.app.service.ImtdSalesBasisDetailsLocalServiceUtil;
import com.stpl.app.service.InventoryWdActualMasLocalServiceUtil;
import com.stpl.app.service.InventoryWdProjMasLocalServiceUtil;
import com.stpl.app.service.ItemGroupDetailsLocalServiceUtil;
import com.stpl.app.service.ItemGroupLocalServiceUtil;
import com.stpl.app.service.ItemHierarchyDefMasterLocalServiceUtil;
import com.stpl.app.service.ItemHierarchyMasterLocalServiceUtil;
import com.stpl.app.service.ItemIdentifierLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.app.service.ItemPricingLocalServiceUtil;
import com.stpl.app.service.ItemPricingQualifierLocalServiceUtil;
import com.stpl.app.service.ItemQualifierLocalServiceUtil;
import com.stpl.app.service.IvldActualMasterLocalServiceUtil;
import com.stpl.app.service.IvldAverageShelfLifeLocalServiceUtil;
import com.stpl.app.service.IvldBestPriceLocalServiceUtil;
import com.stpl.app.service.IvldCpiLocalServiceUtil;
import com.stpl.app.service.IvldDemandActualLocalServiceUtil;
import com.stpl.app.service.IvldDemandForecastLocalServiceUtil;
import com.stpl.app.service.IvldForecastSalesLocalServiceUtil;
import com.stpl.app.service.IvldFormulaDetailsLocalServiceUtil;
import com.stpl.app.service.IvldGlBalanceLocalServiceUtil;
import com.stpl.app.service.IvldGlCostCenterLocalServiceUtil;
import com.stpl.app.service.IvldInventoryWdActualMasLocalServiceUtil;
import com.stpl.app.service.IvldInventoryWdProjMasLocalServiceUtil;
import com.stpl.app.service.IvldItemHierarchyDefinitionLocalServiceUtil;
import com.stpl.app.service.IvldItemHierarchyLocalServiceUtil;
import com.stpl.app.service.IvldLotMasterLocalServiceUtil;
import com.stpl.app.service.IvldMasterDataAttributeLocalServiceUtil;
import com.stpl.app.service.IvldReturnsLocalServiceUtil;
import com.stpl.app.service.IvldSalesMasterLocalServiceUtil;
import com.stpl.app.service.LotMasterLocalServiceUtil;
import com.stpl.app.service.MAssumptionsLocalServiceUtil;
import com.stpl.app.service.MParityLookupLocalServiceUtil;
import com.stpl.app.service.MProjectionSelectionLocalServiceUtil;
import com.stpl.app.service.MSalesProjectionMasterLocalServiceUtil;
import com.stpl.app.service.MSupplementalDiscActualsLocalServiceUtil;
import com.stpl.app.service.MSupplementalDiscMasterLocalServiceUtil;
import com.stpl.app.service.MSupplementalDiscProjLocalServiceUtil;
import com.stpl.app.service.MailNotificationMasterLocalServiceUtil;
import com.stpl.app.service.MasterDataAttributeLocalServiceUtil;
import com.stpl.app.service.MasterDataFilesLocalServiceUtil;
import com.stpl.app.service.MedicaidNewNdcLocalServiceUtil;
import com.stpl.app.service.MedicaidUraActualsLocalServiceUtil;
import com.stpl.app.service.MedicaidUraProjLocalServiceUtil;
import com.stpl.app.service.ModulePropertiesLocalServiceUtil;
import com.stpl.app.service.ModuleSubmoduleMasterLocalServiceUtil;
import com.stpl.app.service.NaProjDetailsLocalServiceUtil;
import com.stpl.app.service.NaProjMasterLocalServiceUtil;
import com.stpl.app.service.NaProjectionSelectionLocalServiceUtil;
import com.stpl.app.service.NationalAssumptionsActualsLocalServiceUtil;
import com.stpl.app.service.NationalAssumptionsLocalServiceUtil;
import com.stpl.app.service.NationalAssumptionsProjLocalServiceUtil;
import com.stpl.app.service.NetSalesFormulaMasterLocalServiceUtil;
import com.stpl.app.service.NmActualDiscountLocalServiceUtil;
import com.stpl.app.service.NmActualPpaLocalServiceUtil;
import com.stpl.app.service.NmDiscountProjMasterLocalServiceUtil;
import com.stpl.app.service.NmDiscountProjectionLocalServiceUtil;
import com.stpl.app.service.NmPpaProjectionLocalServiceUtil;
import com.stpl.app.service.NmPpaProjectionMasterLocalServiceUtil;
import com.stpl.app.service.NmProjectionSelectionLocalServiceUtil;
import com.stpl.app.service.NmSalesProjectionLocalServiceUtil;
import com.stpl.app.service.NmSalesProjectionMasterLocalServiceUtil;
import com.stpl.app.service.PeriodLocalServiceUtil;
import com.stpl.app.service.PhsActualsLocalServiceUtil;
import com.stpl.app.service.PhsProjLocalServiceUtil;
import com.stpl.app.service.ProjectionCustDetailsLocalServiceUtil;
import com.stpl.app.service.ProjectionCustHierarchyLocalServiceUtil;
import com.stpl.app.service.ProjectionDetailsLocalServiceUtil;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.app.service.ProjectionNameConfigLocalServiceUtil;
import com.stpl.app.service.ProjectionProdDetailsLocalServiceUtil;
import com.stpl.app.service.ProjectionProdHierarchyLocalServiceUtil;
import com.stpl.app.service.PsContractDetailsLocalServiceUtil;
import com.stpl.app.service.PsContractLocalServiceUtil;
import com.stpl.app.service.PsDetailsLocalServiceUtil;
import com.stpl.app.service.PsModelLocalServiceUtil;
import com.stpl.app.service.RebatePlanMasterLocalServiceUtil;
import com.stpl.app.service.RebatePlanTierLocalServiceUtil;
import com.stpl.app.service.RebateTierFormulaLocalServiceUtil;
import com.stpl.app.service.RelationshipBuilderLocalServiceUtil;
import com.stpl.app.service.RelationshipLevelDefinitionLocalServiceUtil;
import com.stpl.app.service.ReturnsMasterLocalServiceUtil;
import com.stpl.app.service.RsContractDetailsFrLocalServiceUtil;
import com.stpl.app.service.RsContractDetailsLocalServiceUtil;
import com.stpl.app.service.RsContractLocalServiceUtil;
import com.stpl.app.service.RsDetailsFrLocalServiceUtil;
import com.stpl.app.service.RsDetailsLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.service.SalesBasisDetailsLocalServiceUtil;
import com.stpl.app.service.SalesMasterLocalServiceUtil;
import com.stpl.app.service.StChActualDiscountLocalServiceUtil;
import com.stpl.app.service.StChAssumptionsLocalServiceUtil;
import com.stpl.app.service.StChDiscountProjMasterLocalServiceUtil;
import com.stpl.app.service.StChProjectionDiscountLocalServiceUtil;
import com.stpl.app.service.StChSalesProjectionMasterLocalServiceUtil;
import com.stpl.app.service.StDeductionCalendarDetailsLocalServiceUtil;
import com.stpl.app.service.StFederalNewNdcLocalServiceUtil;
import com.stpl.app.service.StMAssumptionsLocalServiceUtil;
import com.stpl.app.service.StMSupplementalDiscActualsLocalServiceUtil;
import com.stpl.app.service.StMSupplementalDiscMasterLocalServiceUtil;
import com.stpl.app.service.StMSupplementalDiscProjLocalServiceUtil;
import com.stpl.app.service.StMedicaidNewNdcLocalServiceUtil;
import com.stpl.app.service.StNationalAssumptionsLocalServiceUtil;
import com.stpl.app.service.StNewNdcLocalServiceUtil;
import com.stpl.app.service.StNmActualDiscountLocalServiceUtil;
import com.stpl.app.service.StNmActualPpaLocalServiceUtil;
import com.stpl.app.service.StNmAssumptionsLocalServiceUtil;
import com.stpl.app.service.StNmDiscountProjMasterLocalServiceUtil;
import com.stpl.app.service.StNmDiscountProjectionLocalServiceUtil;
import com.stpl.app.service.StNmPpaProjectionLocalServiceUtil;
import com.stpl.app.service.StNmPpaProjectionMasterLocalServiceUtil;
import com.stpl.app.service.StSelectionTableLocalServiceUtil;
import com.stpl.app.service.TransactionModuleDetailsLocalServiceUtil;
import com.stpl.app.service.TransactionModuleMasterLocalServiceUtil;
import com.stpl.app.service.UdcsLocalServiceUtil;
import com.stpl.app.service.UsergroupBusinessroleLocalServiceUtil;
import com.stpl.app.service.UsergroupDomainMasterLocalServiceUtil;
import com.stpl.app.service.VwDemandForecastActualLocalServiceUtil;
import com.stpl.app.service.VwInventoryWdActualProjMasLocalServiceUtil;
import com.stpl.app.service.VwIvldDemandForecastActualLocalServiceUtil;
import com.stpl.app.service.VwIvldInventoryWdActualProjMasLocalServiceUtil;
import com.stpl.app.service.VwUserTablesLocalServiceUtil;
import com.stpl.app.service.WfMailConfigLocalServiceUtil;
import com.stpl.app.service.WorkflowMasterLocalServiceUtil;
import com.stpl.app.service.WorkflowProcessInfoLocalServiceUtil;
import com.stpl.app.service.WorkflowProfileLocalServiceUtil;

import com.stpl.portal.kernel.messaging.BaseMessageListener;
import com.stpl.portal.kernel.messaging.Message;


public class ClpMessageListener extends BaseMessageListener {
    public static String getServletContextName() {
        return ClpSerializer.getServletContextName();
    }

    @Override
    protected void doReceive(Message message) throws Exception {
        String command = message.getString("command");
        String servletContextName = message.getString("servletContextName");

        if (command.equals("undeploy") &&
                servletContextName.equals(getServletContextName())) {
            AccrualDetailsLocalServiceUtil.clearService();

            AccrualMasterLocalServiceUtil.clearService();

            ActualsMasterLocalServiceUtil.clearService();

            AdditionalNotesLocalServiceUtil.clearService();

            AuditMasterInboundLocalServiceUtil.clearService();

            AverageShelfLifeMasterLocalServiceUtil.clearService();

            BestPriceMasterLocalServiceUtil.clearService();

            BrandMasterLocalServiceUtil.clearService();

            BusinessroleMasterLocalServiceUtil.clearService();

            BusinessroleModuleLocalServiceUtil.clearService();

            CcpDetailsLocalServiceUtil.clearService();

            CcpMapLocalServiceUtil.clearService();

            CdrDetailsLocalServiceUtil.clearService();

            CdrModelLocalServiceUtil.clearService();

            CfpContractLocalServiceUtil.clearService();

            CfpContractDetailsLocalServiceUtil.clearService();

            CfpDetailsLocalServiceUtil.clearService();

            CfpModelLocalServiceUtil.clearService();

            ChActualDiscountLocalServiceUtil.clearService();

            ChActualSalesLocalServiceUtil.clearService();

            ChAssumptionsLocalServiceUtil.clearService();

            ChDiscountProjMasterLocalServiceUtil.clearService();

            ChProjectionDiscountLocalServiceUtil.clearService();

            ChProjectionSelectionLocalServiceUtil.clearService();

            ChSalesProjectionLocalServiceUtil.clearService();

            ChSalesProjectionMasterLocalServiceUtil.clearService();

            CompanyGroupLocalServiceUtil.clearService();

            CompanyGroupDetailsLocalServiceUtil.clearService();

            CompanyIdentifierLocalServiceUtil.clearService();

            CompanyMasterLocalServiceUtil.clearService();

            CompanyParentDetailsLocalServiceUtil.clearService();

            CompanyQualifierLocalServiceUtil.clearService();

            CompanyTradeClassLocalServiceUtil.clearService();

            ContractAliasMasterLocalServiceUtil.clearService();

            ContractMasterLocalServiceUtil.clearService();

            CpiIndexMasterLocalServiceUtil.clearService();

            CustomViewDetailsLocalServiceUtil.clearService();

            CustomViewMasterLocalServiceUtil.clearService();

            DeductionCalendarDetailsLocalServiceUtil.clearService();

            DeductionCalendarMasterLocalServiceUtil.clearService();

            DeductionDetailsLocalServiceUtil.clearService();

            DeductionGroupLocalServiceUtil.clearService();

            DeductionGroupDetailsLocalServiceUtil.clearService();

            DemandForecastLocalServiceUtil.clearService();

            DocDetailsLocalServiceUtil.clearService();

            FcpActualsLocalServiceUtil.clearService();

            FcpProjLocalServiceUtil.clearService();

            FederalNewNdcLocalServiceUtil.clearService();

            FileManagementLocalServiceUtil.clearService();

            ForecastConfigLocalServiceUtil.clearService();

            ForecastingFormulaLocalServiceUtil.clearService();

            ForecastingMasterLocalServiceUtil.clearService();

            ForecastingViewMasterLocalServiceUtil.clearService();

            FormulaDetailsMasterLocalServiceUtil.clearService();

            GcmCompanyDetailsLocalServiceUtil.clearService();

            GcmCompanyLinkLocalServiceUtil.clearService();

            GcmContractDetailsLocalServiceUtil.clearService();

            GcmGlobalDetailsLocalServiceUtil.clearService();

            GcmItemDetailsLocalServiceUtil.clearService();

            GlBalanceMasterLocalServiceUtil.clearService();

            GlCostCenterMasterLocalServiceUtil.clearService();

            HelperTableLocalServiceUtil.clearService();

            HierarchyDefinitionLocalServiceUtil.clearService();

            HierarchyLevelDefinitionLocalServiceUtil.clearService();

            HierarchyLevelValuesLocalServiceUtil.clearService();

            HistCompanyGroupLocalServiceUtil.clearService();

            HistCompanyGroupDetailsLocalServiceUtil.clearService();

            HistHierarchyDefinitionLocalServiceUtil.clearService();

            HistHierarchyLevelDefnLocalServiceUtil.clearService();

            HistHierarchyLevelValuesLocalServiceUtil.clearService();

            HistItemGroupLocalServiceUtil.clearService();

            HistItemGroupDetailsLocalServiceUtil.clearService();

            HistRelationshipBuilderLocalServiceUtil.clearService();

            HistRelationshipLevelDefnLocalServiceUtil.clearService();

            HistWorkflowMasterLocalServiceUtil.clearService();

            IfpContractLocalServiceUtil.clearService();

            IfpContractDetailsLocalServiceUtil.clearService();

            IfpDetailsLocalServiceUtil.clearService();

            IfpModelLocalServiceUtil.clearService();

            ImtdCfpDetailsLocalServiceUtil.clearService();

            ImtdDeductionDetailsLocalServiceUtil.clearService();

            ImtdIfpDetailsLocalServiceUtil.clearService();

            ImtdItemPriceRebateDetailsLocalServiceUtil.clearService();

            ImtdLevelValuesLocalServiceUtil.clearService();

            ImtdPsDetailsLocalServiceUtil.clearService();

            ImtdRsContractDetailsFrLocalServiceUtil.clearService();

            ImtdRsDetailsLocalServiceUtil.clearService();

            ImtdRsDetailsFrLocalServiceUtil.clearService();

            ImtdSalesBasisDetailsLocalServiceUtil.clearService();

            InventoryWdActualMasLocalServiceUtil.clearService();

            InventoryWdProjMasLocalServiceUtil.clearService();

            ItemGroupLocalServiceUtil.clearService();

            ItemGroupDetailsLocalServiceUtil.clearService();

            ItemHierarchyDefMasterLocalServiceUtil.clearService();

            ItemHierarchyMasterLocalServiceUtil.clearService();

            ItemIdentifierLocalServiceUtil.clearService();

            ItemMasterLocalServiceUtil.clearService();

            ItemPricingLocalServiceUtil.clearService();

            ItemPricingQualifierLocalServiceUtil.clearService();

            ItemQualifierLocalServiceUtil.clearService();

            IvldActualMasterLocalServiceUtil.clearService();

            IvldAverageShelfLifeLocalServiceUtil.clearService();

            IvldBestPriceLocalServiceUtil.clearService();

            IvldCpiLocalServiceUtil.clearService();

            IvldDemandActualLocalServiceUtil.clearService();

            IvldDemandForecastLocalServiceUtil.clearService();

            IvldForecastSalesLocalServiceUtil.clearService();

            IvldFormulaDetailsLocalServiceUtil.clearService();

            IvldGlBalanceLocalServiceUtil.clearService();

            IvldGlCostCenterLocalServiceUtil.clearService();

            IvldInventoryWdActualMasLocalServiceUtil.clearService();

            IvldInventoryWdProjMasLocalServiceUtil.clearService();

            IvldItemHierarchyLocalServiceUtil.clearService();

            IvldItemHierarchyDefinitionLocalServiceUtil.clearService();

            IvldLotMasterLocalServiceUtil.clearService();

            IvldMasterDataAttributeLocalServiceUtil.clearService();

            IvldReturnsLocalServiceUtil.clearService();

            IvldSalesMasterLocalServiceUtil.clearService();

            LotMasterLocalServiceUtil.clearService();

            MailNotificationMasterLocalServiceUtil.clearService();

            MAssumptionsLocalServiceUtil.clearService();

            MasterDataAttributeLocalServiceUtil.clearService();

            MasterDataFilesLocalServiceUtil.clearService();

            MedicaidNewNdcLocalServiceUtil.clearService();

            MedicaidUraActualsLocalServiceUtil.clearService();

            MedicaidUraProjLocalServiceUtil.clearService();

            ModulePropertiesLocalServiceUtil.clearService();

            ModuleSubmoduleMasterLocalServiceUtil.clearService();

            MParityLookupLocalServiceUtil.clearService();

            MProjectionSelectionLocalServiceUtil.clearService();

            MSalesProjectionMasterLocalServiceUtil.clearService();

            MSupplementalDiscActualsLocalServiceUtil.clearService();

            MSupplementalDiscMasterLocalServiceUtil.clearService();

            MSupplementalDiscProjLocalServiceUtil.clearService();

            NaProjDetailsLocalServiceUtil.clearService();

            NaProjectionSelectionLocalServiceUtil.clearService();

            NaProjMasterLocalServiceUtil.clearService();

            NationalAssumptionsLocalServiceUtil.clearService();

            NationalAssumptionsActualsLocalServiceUtil.clearService();

            NationalAssumptionsProjLocalServiceUtil.clearService();

            NetSalesFormulaMasterLocalServiceUtil.clearService();

            NmActualDiscountLocalServiceUtil.clearService();

            NmActualPpaLocalServiceUtil.clearService();

            NmDiscountProjectionLocalServiceUtil.clearService();

            NmDiscountProjMasterLocalServiceUtil.clearService();

            NmPpaProjectionLocalServiceUtil.clearService();

            NmPpaProjectionMasterLocalServiceUtil.clearService();

            NmProjectionSelectionLocalServiceUtil.clearService();

            NmSalesProjectionLocalServiceUtil.clearService();

            NmSalesProjectionMasterLocalServiceUtil.clearService();

            PeriodLocalServiceUtil.clearService();

            PhsActualsLocalServiceUtil.clearService();

            PhsProjLocalServiceUtil.clearService();

            ProjectionCustDetailsLocalServiceUtil.clearService();

            ProjectionCustHierarchyLocalServiceUtil.clearService();

            ProjectionDetailsLocalServiceUtil.clearService();

            ProjectionMasterLocalServiceUtil.clearService();

            ProjectionNameConfigLocalServiceUtil.clearService();

            ProjectionProdDetailsLocalServiceUtil.clearService();

            ProjectionProdHierarchyLocalServiceUtil.clearService();

            PsContractLocalServiceUtil.clearService();

            PsContractDetailsLocalServiceUtil.clearService();

            PsDetailsLocalServiceUtil.clearService();

            PsModelLocalServiceUtil.clearService();

            RebatePlanMasterLocalServiceUtil.clearService();

            RebatePlanTierLocalServiceUtil.clearService();

            RebateTierFormulaLocalServiceUtil.clearService();

            RelationshipBuilderLocalServiceUtil.clearService();

            RelationshipLevelDefinitionLocalServiceUtil.clearService();

            ReturnsMasterLocalServiceUtil.clearService();

            RsContractLocalServiceUtil.clearService();

            RsContractDetailsLocalServiceUtil.clearService();

            RsContractDetailsFrLocalServiceUtil.clearService();

            RsDetailsLocalServiceUtil.clearService();

            RsDetailsFrLocalServiceUtil.clearService();

            RsModelLocalServiceUtil.clearService();

            SalesBasisDetailsLocalServiceUtil.clearService();

            SalesMasterLocalServiceUtil.clearService();

            StChActualDiscountLocalServiceUtil.clearService();

            StChAssumptionsLocalServiceUtil.clearService();

            StChDiscountProjMasterLocalServiceUtil.clearService();

            StChProjectionDiscountLocalServiceUtil.clearService();

            StChSalesProjectionMasterLocalServiceUtil.clearService();

            StDeductionCalendarDetailsLocalServiceUtil.clearService();

            StFederalNewNdcLocalServiceUtil.clearService();

            StMAssumptionsLocalServiceUtil.clearService();

            StMedicaidNewNdcLocalServiceUtil.clearService();

            StMSupplementalDiscActualsLocalServiceUtil.clearService();

            StMSupplementalDiscMasterLocalServiceUtil.clearService();

            StMSupplementalDiscProjLocalServiceUtil.clearService();

            StNationalAssumptionsLocalServiceUtil.clearService();

            StNewNdcLocalServiceUtil.clearService();

            StNmActualDiscountLocalServiceUtil.clearService();

            StNmActualPpaLocalServiceUtil.clearService();

            StNmAssumptionsLocalServiceUtil.clearService();

            StNmDiscountProjectionLocalServiceUtil.clearService();

            StNmDiscountProjMasterLocalServiceUtil.clearService();

            StNmPpaProjectionLocalServiceUtil.clearService();

            StNmPpaProjectionMasterLocalServiceUtil.clearService();

            StSelectionTableLocalServiceUtil.clearService();

            TransactionModuleDetailsLocalServiceUtil.clearService();

            TransactionModuleMasterLocalServiceUtil.clearService();

            UdcsLocalServiceUtil.clearService();

            UsergroupBusinessroleLocalServiceUtil.clearService();

            UsergroupDomainMasterLocalServiceUtil.clearService();

            VwDemandForecastActualLocalServiceUtil.clearService();

            VwInventoryWdActualProjMasLocalServiceUtil.clearService();

            VwIvldDemandForecastActualLocalServiceUtil.clearService();

            VwIvldInventoryWdActualProjMasLocalServiceUtil.clearService();

            VwUserTablesLocalServiceUtil.clearService();

            WfMailConfigLocalServiceUtil.clearService();

            WorkflowMasterLocalServiceUtil.clearService();

            WorkflowProcessInfoLocalServiceUtil.clearService();

            WorkflowProfileLocalServiceUtil.clearService();
        }
    }
}
