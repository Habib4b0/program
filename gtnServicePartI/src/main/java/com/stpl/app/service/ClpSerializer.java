package com.stpl.app.service;

import com.stpl.app.model.AccrualDetailsClp;
import com.stpl.app.model.AccrualMasterClp;
import com.stpl.app.model.ActualsMasterClp;
import com.stpl.app.model.AdditionalNotesClp;
import com.stpl.app.model.AuditMasterInboundClp;
import com.stpl.app.model.AverageShelfLifeMasterClp;
import com.stpl.app.model.BestPriceMasterClp;
import com.stpl.app.model.BrandMasterClp;
import com.stpl.app.model.BusinessroleMasterClp;
import com.stpl.app.model.BusinessroleModuleClp;
import com.stpl.app.model.CcpDetailsClp;
import com.stpl.app.model.CcpMapClp;
import com.stpl.app.model.CdrDetailsClp;
import com.stpl.app.model.CdrModelClp;
import com.stpl.app.model.CfpContractClp;
import com.stpl.app.model.CfpContractDetailsClp;
import com.stpl.app.model.CfpDetailsClp;
import com.stpl.app.model.CfpModelClp;
import com.stpl.app.model.ChActualDiscountClp;
import com.stpl.app.model.ChActualSalesClp;
import com.stpl.app.model.ChAssumptionsClp;
import com.stpl.app.model.ChDiscountProjMasterClp;
import com.stpl.app.model.ChProjectionDiscountClp;
import com.stpl.app.model.ChProjectionSelectionClp;
import com.stpl.app.model.ChSalesProjectionClp;
import com.stpl.app.model.ChSalesProjectionMasterClp;
import com.stpl.app.model.CompanyGroupClp;
import com.stpl.app.model.CompanyGroupDetailsClp;
import com.stpl.app.model.CompanyIdentifierClp;
import com.stpl.app.model.CompanyMasterClp;
import com.stpl.app.model.CompanyParentDetailsClp;
import com.stpl.app.model.CompanyQualifierClp;
import com.stpl.app.model.CompanyTradeClassClp;
import com.stpl.app.model.ContractAliasMasterClp;
import com.stpl.app.model.ContractMasterClp;
import com.stpl.app.model.CpiIndexMasterClp;
import com.stpl.app.model.CustomViewDetailsClp;
import com.stpl.app.model.CustomViewMasterClp;
import com.stpl.app.model.DeductionCalendarDetailsClp;
import com.stpl.app.model.DeductionCalendarMasterClp;
import com.stpl.app.model.DeductionDetailsClp;
import com.stpl.app.model.DeductionGroupClp;
import com.stpl.app.model.DeductionGroupDetailsClp;
import com.stpl.app.model.DemandForecastClp;
import com.stpl.app.model.DocDetailsClp;
import com.stpl.app.model.FcpActualsClp;
import com.stpl.app.model.FcpProjClp;
import com.stpl.app.model.FederalNewNdcClp;
import com.stpl.app.model.FileManagementClp;
import com.stpl.app.model.ForecastConfigClp;
import com.stpl.app.model.ForecastingFormulaClp;
import com.stpl.app.model.ForecastingMasterClp;
import com.stpl.app.model.ForecastingViewMasterClp;
import com.stpl.app.model.FormulaDetailsMasterClp;
import com.stpl.app.model.GcmCompanyDetailsClp;
import com.stpl.app.model.GcmCompanyLinkClp;
import com.stpl.app.model.GcmContractDetailsClp;
import com.stpl.app.model.GcmGlobalDetailsClp;
import com.stpl.app.model.GcmItemDetailsClp;
import com.stpl.app.model.GlBalanceMasterClp;
import com.stpl.app.model.GlCostCenterMasterClp;
import com.stpl.app.model.HelperTableClp;
import com.stpl.app.model.HierarchyDefinitionClp;
import com.stpl.app.model.HierarchyLevelDefinitionClp;
import com.stpl.app.model.HierarchyLevelValuesClp;
import com.stpl.app.model.HistCompanyGroupClp;
import com.stpl.app.model.HistCompanyGroupDetailsClp;
import com.stpl.app.model.HistHierarchyDefinitionClp;
import com.stpl.app.model.HistHierarchyLevelDefnClp;
import com.stpl.app.model.HistHierarchyLevelValuesClp;
import com.stpl.app.model.HistItemGroupClp;
import com.stpl.app.model.HistItemGroupDetailsClp;
import com.stpl.app.model.HistRelationshipBuilderClp;
import com.stpl.app.model.HistRelationshipLevelDefnClp;
import com.stpl.app.model.HistWorkflowMasterClp;
import com.stpl.app.model.IfpContractClp;
import com.stpl.app.model.IfpContractDetailsClp;
import com.stpl.app.model.IfpDetailsClp;
import com.stpl.app.model.IfpModelClp;
import com.stpl.app.model.ImtdCfpDetailsClp;
import com.stpl.app.model.ImtdDeductionDetailsClp;
import com.stpl.app.model.ImtdIfpDetailsClp;
import com.stpl.app.model.ImtdItemPriceRebateDetailsClp;
import com.stpl.app.model.ImtdLevelValuesClp;
import com.stpl.app.model.ImtdPsDetailsClp;
import com.stpl.app.model.ImtdRsContractDetailsFrClp;
import com.stpl.app.model.ImtdRsDetailsClp;
import com.stpl.app.model.ImtdRsDetailsFrClp;
import com.stpl.app.model.ImtdSalesBasisDetailsClp;
import com.stpl.app.model.InventoryWdActualMasClp;
import com.stpl.app.model.InventoryWdProjMasClp;
import com.stpl.app.model.ItemGroupClp;
import com.stpl.app.model.ItemGroupDetailsClp;
import com.stpl.app.model.ItemHierarchyDefMasterClp;
import com.stpl.app.model.ItemHierarchyMasterClp;
import com.stpl.app.model.ItemIdentifierClp;
import com.stpl.app.model.ItemMasterClp;
import com.stpl.app.model.ItemPricingClp;
import com.stpl.app.model.ItemPricingQualifierClp;
import com.stpl.app.model.ItemQualifierClp;
import com.stpl.app.model.IvldActualMasterClp;
import com.stpl.app.model.IvldAverageShelfLifeClp;
import com.stpl.app.model.IvldBestPriceClp;
import com.stpl.app.model.IvldCpiClp;
import com.stpl.app.model.IvldDemandActualClp;
import com.stpl.app.model.IvldDemandForecastClp;
import com.stpl.app.model.IvldForecastSalesClp;
import com.stpl.app.model.IvldFormulaDetailsClp;
import com.stpl.app.model.IvldGlBalanceClp;
import com.stpl.app.model.IvldGlCostCenterClp;
import com.stpl.app.model.IvldInventoryWdActualMasClp;
import com.stpl.app.model.IvldInventoryWdProjMasClp;
import com.stpl.app.model.IvldItemHierarchyClp;
import com.stpl.app.model.IvldItemHierarchyDefinitionClp;
import com.stpl.app.model.IvldLotMasterClp;
import com.stpl.app.model.IvldMasterDataAttributeClp;
import com.stpl.app.model.IvldReturnsClp;
import com.stpl.app.model.IvldSalesMasterClp;
import com.stpl.app.model.LotMasterClp;
import com.stpl.app.model.MAssumptionsClp;
import com.stpl.app.model.MParityLookupClp;
import com.stpl.app.model.MProjectionSelectionClp;
import com.stpl.app.model.MSalesProjectionMasterClp;
import com.stpl.app.model.MSupplementalDiscActualsClp;
import com.stpl.app.model.MSupplementalDiscMasterClp;
import com.stpl.app.model.MSupplementalDiscProjClp;
import com.stpl.app.model.MailNotificationMasterClp;
import com.stpl.app.model.MasterDataAttributeClp;
import com.stpl.app.model.MasterDataFilesClp;
import com.stpl.app.model.MedicaidNewNdcClp;
import com.stpl.app.model.MedicaidUraActualsClp;
import com.stpl.app.model.MedicaidUraProjClp;
import com.stpl.app.model.ModulePropertiesClp;
import com.stpl.app.model.ModuleSubmoduleMasterClp;
import com.stpl.app.model.NaProjDetailsClp;
import com.stpl.app.model.NaProjMasterClp;
import com.stpl.app.model.NaProjectionSelectionClp;
import com.stpl.app.model.NationalAssumptionsActualsClp;
import com.stpl.app.model.NationalAssumptionsClp;
import com.stpl.app.model.NationalAssumptionsProjClp;
import com.stpl.app.model.NetSalesFormulaMasterClp;
import com.stpl.app.model.NmActualDiscountClp;
import com.stpl.app.model.NmActualPpaClp;
import com.stpl.app.model.NmDiscountProjMasterClp;
import com.stpl.app.model.NmDiscountProjectionClp;
import com.stpl.app.model.NmPpaProjectionClp;
import com.stpl.app.model.NmPpaProjectionMasterClp;
import com.stpl.app.model.NmProjectionSelectionClp;
import com.stpl.app.model.NmSalesProjectionClp;
import com.stpl.app.model.NmSalesProjectionMasterClp;
import com.stpl.app.model.PeriodClp;
import com.stpl.app.model.PhsActualsClp;
import com.stpl.app.model.PhsProjClp;
import com.stpl.app.model.ProjectionCustDetailsClp;
import com.stpl.app.model.ProjectionCustHierarchyClp;
import com.stpl.app.model.ProjectionDetailsClp;
import com.stpl.app.model.ProjectionMasterClp;
import com.stpl.app.model.ProjectionNameConfigClp;
import com.stpl.app.model.ProjectionProdDetailsClp;
import com.stpl.app.model.ProjectionProdHierarchyClp;
import com.stpl.app.model.PsContractClp;
import com.stpl.app.model.PsContractDetailsClp;
import com.stpl.app.model.PsDetailsClp;
import com.stpl.app.model.PsModelClp;
import com.stpl.app.model.RebatePlanMasterClp;
import com.stpl.app.model.RebatePlanTierClp;
import com.stpl.app.model.RebateTierFormulaClp;
import com.stpl.app.model.RelationshipBuilderClp;
import com.stpl.app.model.RelationshipLevelDefinitionClp;
import com.stpl.app.model.ReturnsMasterClp;
import com.stpl.app.model.RsContractClp;
import com.stpl.app.model.RsContractDetailsClp;
import com.stpl.app.model.RsContractDetailsFrClp;
import com.stpl.app.model.RsDetailsClp;
import com.stpl.app.model.RsDetailsFrClp;
import com.stpl.app.model.RsModelClp;
import com.stpl.app.model.SalesBasisDetailsClp;
import com.stpl.app.model.SalesMasterClp;
import com.stpl.app.model.StChActualDiscountClp;
import com.stpl.app.model.StChAssumptionsClp;
import com.stpl.app.model.StChDiscountProjMasterClp;
import com.stpl.app.model.StChProjectionDiscountClp;
import com.stpl.app.model.StChSalesProjectionMasterClp;
import com.stpl.app.model.StDeductionCalendarDetailsClp;
import com.stpl.app.model.StFederalNewNdcClp;
import com.stpl.app.model.StMAssumptionsClp;
import com.stpl.app.model.StMSupplementalDiscActualsClp;
import com.stpl.app.model.StMSupplementalDiscMasterClp;
import com.stpl.app.model.StMSupplementalDiscProjClp;
import com.stpl.app.model.StMedicaidNewNdcClp;
import com.stpl.app.model.StNationalAssumptionsClp;
import com.stpl.app.model.StNewNdcClp;
import com.stpl.app.model.StNmActualDiscountClp;
import com.stpl.app.model.StNmActualPpaClp;
import com.stpl.app.model.StNmAssumptionsClp;
import com.stpl.app.model.StNmDiscountProjMasterClp;
import com.stpl.app.model.StNmDiscountProjectionClp;
import com.stpl.app.model.StNmPpaProjectionClp;
import com.stpl.app.model.StNmPpaProjectionMasterClp;
import com.stpl.app.model.StSelectionTableClp;
import com.stpl.app.model.TransactionModuleDetailsClp;
import com.stpl.app.model.TransactionModuleMasterClp;
import com.stpl.app.model.UdcsClp;
import com.stpl.app.model.UsergroupBusinessroleClp;
import com.stpl.app.model.UsergroupDomainMasterClp;
import com.stpl.app.model.VwDemandForecastActualClp;
import com.stpl.app.model.VwInventoryWdActualProjMasClp;
import com.stpl.app.model.VwIvldDemandForecastActualClp;
import com.stpl.app.model.VwIvldInventoryWdActualProjMasClp;
import com.stpl.app.model.VwUserTablesClp;
import com.stpl.app.model.WfMailConfigClp;
import com.stpl.app.model.WorkflowMasterClp;
import com.stpl.app.model.WorkflowProcessInfoClp;
import com.stpl.app.model.WorkflowProfileClp;

import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.stpl.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.stpl.portal.kernel.log.Log;
import com.stpl.portal.kernel.log.LogFactoryUtil;
import com.stpl.portal.kernel.util.ClassLoaderObjectInputStream;
import com.stpl.portal.kernel.util.PropsUtil;
import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.BaseModel;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;


public class ClpSerializer {
    private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
    private static String _servletContextName;
    private static boolean _useReflectionToTranslateThrowable = true;

    public static String getServletContextName() {
        if (Validator.isNotNull(_servletContextName)) {
            return _servletContextName;
        }

        synchronized (ClpSerializer.class) {
            if (Validator.isNotNull(_servletContextName)) {
                return _servletContextName;
            }

            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Class<?> portletPropsClass = classLoader.loadClass(
                        "com.stpl.util.portlet.PortletProps");

                Method getMethod = portletPropsClass.getMethod("get",
                        new Class<?>[] { String.class });

                String portletPropsServletContextName = (String) getMethod.invoke(null,
                        "gtnPartI-deployment-context");

                if (Validator.isNotNull(portletPropsServletContextName)) {
                    _servletContextName = portletPropsServletContextName;
                }
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info(
                        "Unable to locate deployment context from portlet properties");
                }
            }

            if (Validator.isNull(_servletContextName)) {
                try {
                    String propsUtilServletContextName = PropsUtil.get(
                            "gtnPartI-deployment-context");

                    if (Validator.isNotNull(propsUtilServletContextName)) {
                        _servletContextName = propsUtilServletContextName;
                    }
                } catch (Throwable t) {
                    if (_log.isInfoEnabled()) {
                        _log.info(
                            "Unable to locate deployment context from portal properties");
                    }
                }
            }

            if (Validator.isNull(_servletContextName)) {
                _servletContextName = "gtnPartI";
            }

            return _servletContextName;
        }
    }

    public static Object translateInput(BaseModel<?> oldModel) {
        Class<?> oldModelClass = oldModel.getClass();

        String oldModelClassName = oldModelClass.getName();

        if (oldModelClassName.equals(AccrualDetailsClp.class.getName())) {
            return translateInputAccrualDetails(oldModel);
        }

        if (oldModelClassName.equals(AccrualMasterClp.class.getName())) {
            return translateInputAccrualMaster(oldModel);
        }

        if (oldModelClassName.equals(ActualsMasterClp.class.getName())) {
            return translateInputActualsMaster(oldModel);
        }

        if (oldModelClassName.equals(AdditionalNotesClp.class.getName())) {
            return translateInputAdditionalNotes(oldModel);
        }

        if (oldModelClassName.equals(AuditMasterInboundClp.class.getName())) {
            return translateInputAuditMasterInbound(oldModel);
        }

        if (oldModelClassName.equals(AverageShelfLifeMasterClp.class.getName())) {
            return translateInputAverageShelfLifeMaster(oldModel);
        }

        if (oldModelClassName.equals(BestPriceMasterClp.class.getName())) {
            return translateInputBestPriceMaster(oldModel);
        }

        if (oldModelClassName.equals(BrandMasterClp.class.getName())) {
            return translateInputBrandMaster(oldModel);
        }

        if (oldModelClassName.equals(BusinessroleMasterClp.class.getName())) {
            return translateInputBusinessroleMaster(oldModel);
        }

        if (oldModelClassName.equals(BusinessroleModuleClp.class.getName())) {
            return translateInputBusinessroleModule(oldModel);
        }

        if (oldModelClassName.equals(CcpDetailsClp.class.getName())) {
            return translateInputCcpDetails(oldModel);
        }

        if (oldModelClassName.equals(CcpMapClp.class.getName())) {
            return translateInputCcpMap(oldModel);
        }

        if (oldModelClassName.equals(CdrDetailsClp.class.getName())) {
            return translateInputCdrDetails(oldModel);
        }

        if (oldModelClassName.equals(CdrModelClp.class.getName())) {
            return translateInputCdrModel(oldModel);
        }

        if (oldModelClassName.equals(CfpContractClp.class.getName())) {
            return translateInputCfpContract(oldModel);
        }

        if (oldModelClassName.equals(CfpContractDetailsClp.class.getName())) {
            return translateInputCfpContractDetails(oldModel);
        }

        if (oldModelClassName.equals(CfpDetailsClp.class.getName())) {
            return translateInputCfpDetails(oldModel);
        }

        if (oldModelClassName.equals(CfpModelClp.class.getName())) {
            return translateInputCfpModel(oldModel);
        }

        if (oldModelClassName.equals(ChActualDiscountClp.class.getName())) {
            return translateInputChActualDiscount(oldModel);
        }

        if (oldModelClassName.equals(ChActualSalesClp.class.getName())) {
            return translateInputChActualSales(oldModel);
        }

        if (oldModelClassName.equals(ChAssumptionsClp.class.getName())) {
            return translateInputChAssumptions(oldModel);
        }

        if (oldModelClassName.equals(ChDiscountProjMasterClp.class.getName())) {
            return translateInputChDiscountProjMaster(oldModel);
        }

        if (oldModelClassName.equals(ChProjectionDiscountClp.class.getName())) {
            return translateInputChProjectionDiscount(oldModel);
        }

        if (oldModelClassName.equals(ChProjectionSelectionClp.class.getName())) {
            return translateInputChProjectionSelection(oldModel);
        }

        if (oldModelClassName.equals(ChSalesProjectionClp.class.getName())) {
            return translateInputChSalesProjection(oldModel);
        }

        if (oldModelClassName.equals(ChSalesProjectionMasterClp.class.getName())) {
            return translateInputChSalesProjectionMaster(oldModel);
        }

        if (oldModelClassName.equals(CompanyGroupClp.class.getName())) {
            return translateInputCompanyGroup(oldModel);
        }

        if (oldModelClassName.equals(CompanyGroupDetailsClp.class.getName())) {
            return translateInputCompanyGroupDetails(oldModel);
        }

        if (oldModelClassName.equals(CompanyIdentifierClp.class.getName())) {
            return translateInputCompanyIdentifier(oldModel);
        }

        if (oldModelClassName.equals(CompanyMasterClp.class.getName())) {
            return translateInputCompanyMaster(oldModel);
        }

        if (oldModelClassName.equals(CompanyParentDetailsClp.class.getName())) {
            return translateInputCompanyParentDetails(oldModel);
        }

        if (oldModelClassName.equals(CompanyQualifierClp.class.getName())) {
            return translateInputCompanyQualifier(oldModel);
        }

        if (oldModelClassName.equals(CompanyTradeClassClp.class.getName())) {
            return translateInputCompanyTradeClass(oldModel);
        }

        if (oldModelClassName.equals(ContractAliasMasterClp.class.getName())) {
            return translateInputContractAliasMaster(oldModel);
        }

        if (oldModelClassName.equals(ContractMasterClp.class.getName())) {
            return translateInputContractMaster(oldModel);
        }

        if (oldModelClassName.equals(CpiIndexMasterClp.class.getName())) {
            return translateInputCpiIndexMaster(oldModel);
        }

        if (oldModelClassName.equals(CustomViewDetailsClp.class.getName())) {
            return translateInputCustomViewDetails(oldModel);
        }

        if (oldModelClassName.equals(CustomViewMasterClp.class.getName())) {
            return translateInputCustomViewMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    DeductionCalendarDetailsClp.class.getName())) {
            return translateInputDeductionCalendarDetails(oldModel);
        }

        if (oldModelClassName.equals(DeductionCalendarMasterClp.class.getName())) {
            return translateInputDeductionCalendarMaster(oldModel);
        }

        if (oldModelClassName.equals(DeductionDetailsClp.class.getName())) {
            return translateInputDeductionDetails(oldModel);
        }

        if (oldModelClassName.equals(DeductionGroupClp.class.getName())) {
            return translateInputDeductionGroup(oldModel);
        }

        if (oldModelClassName.equals(DeductionGroupDetailsClp.class.getName())) {
            return translateInputDeductionGroupDetails(oldModel);
        }

        if (oldModelClassName.equals(DemandForecastClp.class.getName())) {
            return translateInputDemandForecast(oldModel);
        }

        if (oldModelClassName.equals(DocDetailsClp.class.getName())) {
            return translateInputDocDetails(oldModel);
        }

        if (oldModelClassName.equals(FcpActualsClp.class.getName())) {
            return translateInputFcpActuals(oldModel);
        }

        if (oldModelClassName.equals(FcpProjClp.class.getName())) {
            return translateInputFcpProj(oldModel);
        }

        if (oldModelClassName.equals(FederalNewNdcClp.class.getName())) {
            return translateInputFederalNewNdc(oldModel);
        }

        if (oldModelClassName.equals(FileManagementClp.class.getName())) {
            return translateInputFileManagement(oldModel);
        }

        if (oldModelClassName.equals(ForecastConfigClp.class.getName())) {
            return translateInputForecastConfig(oldModel);
        }

        if (oldModelClassName.equals(ForecastingFormulaClp.class.getName())) {
            return translateInputForecastingFormula(oldModel);
        }

        if (oldModelClassName.equals(ForecastingMasterClp.class.getName())) {
            return translateInputForecastingMaster(oldModel);
        }

        if (oldModelClassName.equals(ForecastingViewMasterClp.class.getName())) {
            return translateInputForecastingViewMaster(oldModel);
        }

        if (oldModelClassName.equals(FormulaDetailsMasterClp.class.getName())) {
            return translateInputFormulaDetailsMaster(oldModel);
        }

        if (oldModelClassName.equals(GcmCompanyDetailsClp.class.getName())) {
            return translateInputGcmCompanyDetails(oldModel);
        }

        if (oldModelClassName.equals(GcmCompanyLinkClp.class.getName())) {
            return translateInputGcmCompanyLink(oldModel);
        }

        if (oldModelClassName.equals(GcmContractDetailsClp.class.getName())) {
            return translateInputGcmContractDetails(oldModel);
        }

        if (oldModelClassName.equals(GcmGlobalDetailsClp.class.getName())) {
            return translateInputGcmGlobalDetails(oldModel);
        }

        if (oldModelClassName.equals(GcmItemDetailsClp.class.getName())) {
            return translateInputGcmItemDetails(oldModel);
        }

        if (oldModelClassName.equals(GlBalanceMasterClp.class.getName())) {
            return translateInputGlBalanceMaster(oldModel);
        }

        if (oldModelClassName.equals(GlCostCenterMasterClp.class.getName())) {
            return translateInputGlCostCenterMaster(oldModel);
        }

        if (oldModelClassName.equals(HelperTableClp.class.getName())) {
            return translateInputHelperTable(oldModel);
        }

        if (oldModelClassName.equals(HierarchyDefinitionClp.class.getName())) {
            return translateInputHierarchyDefinition(oldModel);
        }

        if (oldModelClassName.equals(
                    HierarchyLevelDefinitionClp.class.getName())) {
            return translateInputHierarchyLevelDefinition(oldModel);
        }

        if (oldModelClassName.equals(HierarchyLevelValuesClp.class.getName())) {
            return translateInputHierarchyLevelValues(oldModel);
        }

        if (oldModelClassName.equals(HistCompanyGroupClp.class.getName())) {
            return translateInputHistCompanyGroup(oldModel);
        }

        if (oldModelClassName.equals(HistCompanyGroupDetailsClp.class.getName())) {
            return translateInputHistCompanyGroupDetails(oldModel);
        }

        if (oldModelClassName.equals(HistHierarchyDefinitionClp.class.getName())) {
            return translateInputHistHierarchyDefinition(oldModel);
        }

        if (oldModelClassName.equals(HistHierarchyLevelDefnClp.class.getName())) {
            return translateInputHistHierarchyLevelDefn(oldModel);
        }

        if (oldModelClassName.equals(
                    HistHierarchyLevelValuesClp.class.getName())) {
            return translateInputHistHierarchyLevelValues(oldModel);
        }

        if (oldModelClassName.equals(HistItemGroupClp.class.getName())) {
            return translateInputHistItemGroup(oldModel);
        }

        if (oldModelClassName.equals(HistItemGroupDetailsClp.class.getName())) {
            return translateInputHistItemGroupDetails(oldModel);
        }

        if (oldModelClassName.equals(HistRelationshipBuilderClp.class.getName())) {
            return translateInputHistRelationshipBuilder(oldModel);
        }

        if (oldModelClassName.equals(
                    HistRelationshipLevelDefnClp.class.getName())) {
            return translateInputHistRelationshipLevelDefn(oldModel);
        }

        if (oldModelClassName.equals(HistWorkflowMasterClp.class.getName())) {
            return translateInputHistWorkflowMaster(oldModel);
        }

        if (oldModelClassName.equals(IfpContractClp.class.getName())) {
            return translateInputIfpContract(oldModel);
        }

        if (oldModelClassName.equals(IfpContractDetailsClp.class.getName())) {
            return translateInputIfpContractDetails(oldModel);
        }

        if (oldModelClassName.equals(IfpDetailsClp.class.getName())) {
            return translateInputIfpDetails(oldModel);
        }

        if (oldModelClassName.equals(IfpModelClp.class.getName())) {
            return translateInputIfpModel(oldModel);
        }

        if (oldModelClassName.equals(ImtdCfpDetailsClp.class.getName())) {
            return translateInputImtdCfpDetails(oldModel);
        }

        if (oldModelClassName.equals(ImtdDeductionDetailsClp.class.getName())) {
            return translateInputImtdDeductionDetails(oldModel);
        }

        if (oldModelClassName.equals(ImtdIfpDetailsClp.class.getName())) {
            return translateInputImtdIfpDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    ImtdItemPriceRebateDetailsClp.class.getName())) {
            return translateInputImtdItemPriceRebateDetails(oldModel);
        }

        if (oldModelClassName.equals(ImtdLevelValuesClp.class.getName())) {
            return translateInputImtdLevelValues(oldModel);
        }

        if (oldModelClassName.equals(ImtdPsDetailsClp.class.getName())) {
            return translateInputImtdPsDetails(oldModel);
        }

        if (oldModelClassName.equals(ImtdRsContractDetailsFrClp.class.getName())) {
            return translateInputImtdRsContractDetailsFr(oldModel);
        }

        if (oldModelClassName.equals(ImtdRsDetailsClp.class.getName())) {
            return translateInputImtdRsDetails(oldModel);
        }

        if (oldModelClassName.equals(ImtdRsDetailsFrClp.class.getName())) {
            return translateInputImtdRsDetailsFr(oldModel);
        }

        if (oldModelClassName.equals(ImtdSalesBasisDetailsClp.class.getName())) {
            return translateInputImtdSalesBasisDetails(oldModel);
        }

        if (oldModelClassName.equals(InventoryWdActualMasClp.class.getName())) {
            return translateInputInventoryWdActualMas(oldModel);
        }

        if (oldModelClassName.equals(InventoryWdProjMasClp.class.getName())) {
            return translateInputInventoryWdProjMas(oldModel);
        }

        if (oldModelClassName.equals(ItemGroupClp.class.getName())) {
            return translateInputItemGroup(oldModel);
        }

        if (oldModelClassName.equals(ItemGroupDetailsClp.class.getName())) {
            return translateInputItemGroupDetails(oldModel);
        }

        if (oldModelClassName.equals(ItemHierarchyDefMasterClp.class.getName())) {
            return translateInputItemHierarchyDefMaster(oldModel);
        }

        if (oldModelClassName.equals(ItemHierarchyMasterClp.class.getName())) {
            return translateInputItemHierarchyMaster(oldModel);
        }

        if (oldModelClassName.equals(ItemIdentifierClp.class.getName())) {
            return translateInputItemIdentifier(oldModel);
        }

        if (oldModelClassName.equals(ItemMasterClp.class.getName())) {
            return translateInputItemMaster(oldModel);
        }

        if (oldModelClassName.equals(ItemPricingClp.class.getName())) {
            return translateInputItemPricing(oldModel);
        }

        if (oldModelClassName.equals(ItemPricingQualifierClp.class.getName())) {
            return translateInputItemPricingQualifier(oldModel);
        }

        if (oldModelClassName.equals(ItemQualifierClp.class.getName())) {
            return translateInputItemQualifier(oldModel);
        }

        if (oldModelClassName.equals(IvldActualMasterClp.class.getName())) {
            return translateInputIvldActualMaster(oldModel);
        }

        if (oldModelClassName.equals(IvldAverageShelfLifeClp.class.getName())) {
            return translateInputIvldAverageShelfLife(oldModel);
        }

        if (oldModelClassName.equals(IvldBestPriceClp.class.getName())) {
            return translateInputIvldBestPrice(oldModel);
        }

        if (oldModelClassName.equals(IvldCpiClp.class.getName())) {
            return translateInputIvldCpi(oldModel);
        }

        if (oldModelClassName.equals(IvldDemandActualClp.class.getName())) {
            return translateInputIvldDemandActual(oldModel);
        }

        if (oldModelClassName.equals(IvldDemandForecastClp.class.getName())) {
            return translateInputIvldDemandForecast(oldModel);
        }

        if (oldModelClassName.equals(IvldForecastSalesClp.class.getName())) {
            return translateInputIvldForecastSales(oldModel);
        }

        if (oldModelClassName.equals(IvldFormulaDetailsClp.class.getName())) {
            return translateInputIvldFormulaDetails(oldModel);
        }

        if (oldModelClassName.equals(IvldGlBalanceClp.class.getName())) {
            return translateInputIvldGlBalance(oldModel);
        }

        if (oldModelClassName.equals(IvldGlCostCenterClp.class.getName())) {
            return translateInputIvldGlCostCenter(oldModel);
        }

        if (oldModelClassName.equals(
                    IvldInventoryWdActualMasClp.class.getName())) {
            return translateInputIvldInventoryWdActualMas(oldModel);
        }

        if (oldModelClassName.equals(IvldInventoryWdProjMasClp.class.getName())) {
            return translateInputIvldInventoryWdProjMas(oldModel);
        }

        if (oldModelClassName.equals(IvldItemHierarchyClp.class.getName())) {
            return translateInputIvldItemHierarchy(oldModel);
        }

        if (oldModelClassName.equals(
                    IvldItemHierarchyDefinitionClp.class.getName())) {
            return translateInputIvldItemHierarchyDefinition(oldModel);
        }

        if (oldModelClassName.equals(IvldLotMasterClp.class.getName())) {
            return translateInputIvldLotMaster(oldModel);
        }

        if (oldModelClassName.equals(IvldMasterDataAttributeClp.class.getName())) {
            return translateInputIvldMasterDataAttribute(oldModel);
        }

        if (oldModelClassName.equals(IvldReturnsClp.class.getName())) {
            return translateInputIvldReturns(oldModel);
        }

        if (oldModelClassName.equals(IvldSalesMasterClp.class.getName())) {
            return translateInputIvldSalesMaster(oldModel);
        }

        if (oldModelClassName.equals(LotMasterClp.class.getName())) {
            return translateInputLotMaster(oldModel);
        }

        if (oldModelClassName.equals(MailNotificationMasterClp.class.getName())) {
            return translateInputMailNotificationMaster(oldModel);
        }

        if (oldModelClassName.equals(MAssumptionsClp.class.getName())) {
            return translateInputMAssumptions(oldModel);
        }

        if (oldModelClassName.equals(MasterDataAttributeClp.class.getName())) {
            return translateInputMasterDataAttribute(oldModel);
        }

        if (oldModelClassName.equals(MasterDataFilesClp.class.getName())) {
            return translateInputMasterDataFiles(oldModel);
        }

        if (oldModelClassName.equals(MedicaidNewNdcClp.class.getName())) {
            return translateInputMedicaidNewNdc(oldModel);
        }

        if (oldModelClassName.equals(MedicaidUraActualsClp.class.getName())) {
            return translateInputMedicaidUraActuals(oldModel);
        }

        if (oldModelClassName.equals(MedicaidUraProjClp.class.getName())) {
            return translateInputMedicaidUraProj(oldModel);
        }

        if (oldModelClassName.equals(ModulePropertiesClp.class.getName())) {
            return translateInputModuleProperties(oldModel);
        }

        if (oldModelClassName.equals(ModuleSubmoduleMasterClp.class.getName())) {
            return translateInputModuleSubmoduleMaster(oldModel);
        }

        if (oldModelClassName.equals(MParityLookupClp.class.getName())) {
            return translateInputMParityLookup(oldModel);
        }

        if (oldModelClassName.equals(MProjectionSelectionClp.class.getName())) {
            return translateInputMProjectionSelection(oldModel);
        }

        if (oldModelClassName.equals(MSalesProjectionMasterClp.class.getName())) {
            return translateInputMSalesProjectionMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    MSupplementalDiscActualsClp.class.getName())) {
            return translateInputMSupplementalDiscActuals(oldModel);
        }

        if (oldModelClassName.equals(MSupplementalDiscMasterClp.class.getName())) {
            return translateInputMSupplementalDiscMaster(oldModel);
        }

        if (oldModelClassName.equals(MSupplementalDiscProjClp.class.getName())) {
            return translateInputMSupplementalDiscProj(oldModel);
        }

        if (oldModelClassName.equals(NaProjDetailsClp.class.getName())) {
            return translateInputNaProjDetails(oldModel);
        }

        if (oldModelClassName.equals(NaProjectionSelectionClp.class.getName())) {
            return translateInputNaProjectionSelection(oldModel);
        }

        if (oldModelClassName.equals(NaProjMasterClp.class.getName())) {
            return translateInputNaProjMaster(oldModel);
        }

        if (oldModelClassName.equals(NationalAssumptionsClp.class.getName())) {
            return translateInputNationalAssumptions(oldModel);
        }

        if (oldModelClassName.equals(
                    NationalAssumptionsActualsClp.class.getName())) {
            return translateInputNationalAssumptionsActuals(oldModel);
        }

        if (oldModelClassName.equals(NationalAssumptionsProjClp.class.getName())) {
            return translateInputNationalAssumptionsProj(oldModel);
        }

        if (oldModelClassName.equals(NetSalesFormulaMasterClp.class.getName())) {
            return translateInputNetSalesFormulaMaster(oldModel);
        }

        if (oldModelClassName.equals(NmActualDiscountClp.class.getName())) {
            return translateInputNmActualDiscount(oldModel);
        }

        if (oldModelClassName.equals(NmActualPpaClp.class.getName())) {
            return translateInputNmActualPpa(oldModel);
        }

        if (oldModelClassName.equals(NmDiscountProjectionClp.class.getName())) {
            return translateInputNmDiscountProjection(oldModel);
        }

        if (oldModelClassName.equals(NmDiscountProjMasterClp.class.getName())) {
            return translateInputNmDiscountProjMaster(oldModel);
        }

        if (oldModelClassName.equals(NmPpaProjectionClp.class.getName())) {
            return translateInputNmPpaProjection(oldModel);
        }

        if (oldModelClassName.equals(NmPpaProjectionMasterClp.class.getName())) {
            return translateInputNmPpaProjectionMaster(oldModel);
        }

        if (oldModelClassName.equals(NmProjectionSelectionClp.class.getName())) {
            return translateInputNmProjectionSelection(oldModel);
        }

        if (oldModelClassName.equals(NmSalesProjectionClp.class.getName())) {
            return translateInputNmSalesProjection(oldModel);
        }

        if (oldModelClassName.equals(NmSalesProjectionMasterClp.class.getName())) {
            return translateInputNmSalesProjectionMaster(oldModel);
        }

        if (oldModelClassName.equals(PeriodClp.class.getName())) {
            return translateInputPeriod(oldModel);
        }

        if (oldModelClassName.equals(PhsActualsClp.class.getName())) {
            return translateInputPhsActuals(oldModel);
        }

        if (oldModelClassName.equals(PhsProjClp.class.getName())) {
            return translateInputPhsProj(oldModel);
        }

        if (oldModelClassName.equals(ProjectionCustDetailsClp.class.getName())) {
            return translateInputProjectionCustDetails(oldModel);
        }

        if (oldModelClassName.equals(ProjectionCustHierarchyClp.class.getName())) {
            return translateInputProjectionCustHierarchy(oldModel);
        }

        if (oldModelClassName.equals(ProjectionDetailsClp.class.getName())) {
            return translateInputProjectionDetails(oldModel);
        }

        if (oldModelClassName.equals(ProjectionMasterClp.class.getName())) {
            return translateInputProjectionMaster(oldModel);
        }

        if (oldModelClassName.equals(ProjectionNameConfigClp.class.getName())) {
            return translateInputProjectionNameConfig(oldModel);
        }

        if (oldModelClassName.equals(ProjectionProdDetailsClp.class.getName())) {
            return translateInputProjectionProdDetails(oldModel);
        }

        if (oldModelClassName.equals(ProjectionProdHierarchyClp.class.getName())) {
            return translateInputProjectionProdHierarchy(oldModel);
        }

        if (oldModelClassName.equals(PsContractClp.class.getName())) {
            return translateInputPsContract(oldModel);
        }

        if (oldModelClassName.equals(PsContractDetailsClp.class.getName())) {
            return translateInputPsContractDetails(oldModel);
        }

        if (oldModelClassName.equals(PsDetailsClp.class.getName())) {
            return translateInputPsDetails(oldModel);
        }

        if (oldModelClassName.equals(PsModelClp.class.getName())) {
            return translateInputPsModel(oldModel);
        }

        if (oldModelClassName.equals(RebatePlanMasterClp.class.getName())) {
            return translateInputRebatePlanMaster(oldModel);
        }

        if (oldModelClassName.equals(RebatePlanTierClp.class.getName())) {
            return translateInputRebatePlanTier(oldModel);
        }

        if (oldModelClassName.equals(RebateTierFormulaClp.class.getName())) {
            return translateInputRebateTierFormula(oldModel);
        }

        if (oldModelClassName.equals(RelationshipBuilderClp.class.getName())) {
            return translateInputRelationshipBuilder(oldModel);
        }

        if (oldModelClassName.equals(
                    RelationshipLevelDefinitionClp.class.getName())) {
            return translateInputRelationshipLevelDefinition(oldModel);
        }

        if (oldModelClassName.equals(ReturnsMasterClp.class.getName())) {
            return translateInputReturnsMaster(oldModel);
        }

        if (oldModelClassName.equals(RsContractClp.class.getName())) {
            return translateInputRsContract(oldModel);
        }

        if (oldModelClassName.equals(RsContractDetailsClp.class.getName())) {
            return translateInputRsContractDetails(oldModel);
        }

        if (oldModelClassName.equals(RsContractDetailsFrClp.class.getName())) {
            return translateInputRsContractDetailsFr(oldModel);
        }

        if (oldModelClassName.equals(RsDetailsClp.class.getName())) {
            return translateInputRsDetails(oldModel);
        }

        if (oldModelClassName.equals(RsDetailsFrClp.class.getName())) {
            return translateInputRsDetailsFr(oldModel);
        }

        if (oldModelClassName.equals(RsModelClp.class.getName())) {
            return translateInputRsModel(oldModel);
        }

        if (oldModelClassName.equals(SalesBasisDetailsClp.class.getName())) {
            return translateInputSalesBasisDetails(oldModel);
        }

        if (oldModelClassName.equals(SalesMasterClp.class.getName())) {
            return translateInputSalesMaster(oldModel);
        }

        if (oldModelClassName.equals(StChActualDiscountClp.class.getName())) {
            return translateInputStChActualDiscount(oldModel);
        }

        if (oldModelClassName.equals(StChAssumptionsClp.class.getName())) {
            return translateInputStChAssumptions(oldModel);
        }

        if (oldModelClassName.equals(StChDiscountProjMasterClp.class.getName())) {
            return translateInputStChDiscountProjMaster(oldModel);
        }

        if (oldModelClassName.equals(StChProjectionDiscountClp.class.getName())) {
            return translateInputStChProjectionDiscount(oldModel);
        }

        if (oldModelClassName.equals(
                    StChSalesProjectionMasterClp.class.getName())) {
            return translateInputStChSalesProjectionMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    StDeductionCalendarDetailsClp.class.getName())) {
            return translateInputStDeductionCalendarDetails(oldModel);
        }

        if (oldModelClassName.equals(StFederalNewNdcClp.class.getName())) {
            return translateInputStFederalNewNdc(oldModel);
        }

        if (oldModelClassName.equals(StMAssumptionsClp.class.getName())) {
            return translateInputStMAssumptions(oldModel);
        }

        if (oldModelClassName.equals(StMedicaidNewNdcClp.class.getName())) {
            return translateInputStMedicaidNewNdc(oldModel);
        }

        if (oldModelClassName.equals(
                    StMSupplementalDiscActualsClp.class.getName())) {
            return translateInputStMSupplementalDiscActuals(oldModel);
        }

        if (oldModelClassName.equals(
                    StMSupplementalDiscMasterClp.class.getName())) {
            return translateInputStMSupplementalDiscMaster(oldModel);
        }

        if (oldModelClassName.equals(StMSupplementalDiscProjClp.class.getName())) {
            return translateInputStMSupplementalDiscProj(oldModel);
        }

        if (oldModelClassName.equals(StNationalAssumptionsClp.class.getName())) {
            return translateInputStNationalAssumptions(oldModel);
        }

        if (oldModelClassName.equals(StNewNdcClp.class.getName())) {
            return translateInputStNewNdc(oldModel);
        }

        if (oldModelClassName.equals(StNmActualDiscountClp.class.getName())) {
            return translateInputStNmActualDiscount(oldModel);
        }

        if (oldModelClassName.equals(StNmActualPpaClp.class.getName())) {
            return translateInputStNmActualPpa(oldModel);
        }

        if (oldModelClassName.equals(StNmAssumptionsClp.class.getName())) {
            return translateInputStNmAssumptions(oldModel);
        }

        if (oldModelClassName.equals(StNmDiscountProjectionClp.class.getName())) {
            return translateInputStNmDiscountProjection(oldModel);
        }

        if (oldModelClassName.equals(StNmDiscountProjMasterClp.class.getName())) {
            return translateInputStNmDiscountProjMaster(oldModel);
        }

        if (oldModelClassName.equals(StNmPpaProjectionClp.class.getName())) {
            return translateInputStNmPpaProjection(oldModel);
        }

        if (oldModelClassName.equals(StNmPpaProjectionMasterClp.class.getName())) {
            return translateInputStNmPpaProjectionMaster(oldModel);
        }

        if (oldModelClassName.equals(StSelectionTableClp.class.getName())) {
            return translateInputStSelectionTable(oldModel);
        }

        if (oldModelClassName.equals(
                    TransactionModuleDetailsClp.class.getName())) {
            return translateInputTransactionModuleDetails(oldModel);
        }

        if (oldModelClassName.equals(TransactionModuleMasterClp.class.getName())) {
            return translateInputTransactionModuleMaster(oldModel);
        }

        if (oldModelClassName.equals(UdcsClp.class.getName())) {
            return translateInputUdcs(oldModel);
        }

        if (oldModelClassName.equals(UsergroupBusinessroleClp.class.getName())) {
            return translateInputUsergroupBusinessrole(oldModel);
        }

        if (oldModelClassName.equals(UsergroupDomainMasterClp.class.getName())) {
            return translateInputUsergroupDomainMaster(oldModel);
        }

        if (oldModelClassName.equals(VwDemandForecastActualClp.class.getName())) {
            return translateInputVwDemandForecastActual(oldModel);
        }

        if (oldModelClassName.equals(
                    VwInventoryWdActualProjMasClp.class.getName())) {
            return translateInputVwInventoryWdActualProjMas(oldModel);
        }

        if (oldModelClassName.equals(
                    VwIvldDemandForecastActualClp.class.getName())) {
            return translateInputVwIvldDemandForecastActual(oldModel);
        }

        if (oldModelClassName.equals(
                    VwIvldInventoryWdActualProjMasClp.class.getName())) {
            return translateInputVwIvldInventoryWdActualProjMas(oldModel);
        }

        if (oldModelClassName.equals(VwUserTablesClp.class.getName())) {
            return translateInputVwUserTables(oldModel);
        }

        if (oldModelClassName.equals(WfMailConfigClp.class.getName())) {
            return translateInputWfMailConfig(oldModel);
        }

        if (oldModelClassName.equals(WorkflowMasterClp.class.getName())) {
            return translateInputWorkflowMaster(oldModel);
        }

        if (oldModelClassName.equals(WorkflowProcessInfoClp.class.getName())) {
            return translateInputWorkflowProcessInfo(oldModel);
        }

        if (oldModelClassName.equals(WorkflowProfileClp.class.getName())) {
            return translateInputWorkflowProfile(oldModel);
        }

        return oldModel;
    }

    public static Object translateInput(List<Object> oldList) {
        List<Object> newList = new ArrayList<Object>(oldList.size());

        for (int i = 0; i < oldList.size(); i++) {
            Object curObj = oldList.get(i);

            newList.add(translateInput(curObj));
        }

        return newList;
    }

    public static Object translateInputAccrualDetails(BaseModel<?> oldModel) {
        AccrualDetailsClp oldClpModel = (AccrualDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getAccrualDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputAccrualMaster(BaseModel<?> oldModel) {
        AccrualMasterClp oldClpModel = (AccrualMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getAccrualMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputActualsMaster(BaseModel<?> oldModel) {
        ActualsMasterClp oldClpModel = (ActualsMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getActualsMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputAdditionalNotes(BaseModel<?> oldModel) {
        AdditionalNotesClp oldClpModel = (AdditionalNotesClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getAdditionalNotesRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputAuditMasterInbound(BaseModel<?> oldModel) {
        AuditMasterInboundClp oldClpModel = (AuditMasterInboundClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getAuditMasterInboundRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputAverageShelfLifeMaster(
        BaseModel<?> oldModel) {
        AverageShelfLifeMasterClp oldClpModel = (AverageShelfLifeMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getAverageShelfLifeMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputBestPriceMaster(BaseModel<?> oldModel) {
        BestPriceMasterClp oldClpModel = (BestPriceMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getBestPriceMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputBrandMaster(BaseModel<?> oldModel) {
        BrandMasterClp oldClpModel = (BrandMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getBrandMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputBusinessroleMaster(BaseModel<?> oldModel) {
        BusinessroleMasterClp oldClpModel = (BusinessroleMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getBusinessroleMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputBusinessroleModule(BaseModel<?> oldModel) {
        BusinessroleModuleClp oldClpModel = (BusinessroleModuleClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getBusinessroleModuleRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCcpDetails(BaseModel<?> oldModel) {
        CcpDetailsClp oldClpModel = (CcpDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCcpDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCcpMap(BaseModel<?> oldModel) {
        CcpMapClp oldClpModel = (CcpMapClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCcpMapRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCdrDetails(BaseModel<?> oldModel) {
        CdrDetailsClp oldClpModel = (CdrDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCdrDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCdrModel(BaseModel<?> oldModel) {
        CdrModelClp oldClpModel = (CdrModelClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCdrModelRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCfpContract(BaseModel<?> oldModel) {
        CfpContractClp oldClpModel = (CfpContractClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCfpContractRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCfpContractDetails(BaseModel<?> oldModel) {
        CfpContractDetailsClp oldClpModel = (CfpContractDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCfpContractDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCfpDetails(BaseModel<?> oldModel) {
        CfpDetailsClp oldClpModel = (CfpDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCfpDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCfpModel(BaseModel<?> oldModel) {
        CfpModelClp oldClpModel = (CfpModelClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCfpModelRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputChActualDiscount(BaseModel<?> oldModel) {
        ChActualDiscountClp oldClpModel = (ChActualDiscountClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getChActualDiscountRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputChActualSales(BaseModel<?> oldModel) {
        ChActualSalesClp oldClpModel = (ChActualSalesClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getChActualSalesRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputChAssumptions(BaseModel<?> oldModel) {
        ChAssumptionsClp oldClpModel = (ChAssumptionsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getChAssumptionsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputChDiscountProjMaster(
        BaseModel<?> oldModel) {
        ChDiscountProjMasterClp oldClpModel = (ChDiscountProjMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getChDiscountProjMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputChProjectionDiscount(
        BaseModel<?> oldModel) {
        ChProjectionDiscountClp oldClpModel = (ChProjectionDiscountClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getChProjectionDiscountRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputChProjectionSelection(
        BaseModel<?> oldModel) {
        ChProjectionSelectionClp oldClpModel = (ChProjectionSelectionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getChProjectionSelectionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputChSalesProjection(BaseModel<?> oldModel) {
        ChSalesProjectionClp oldClpModel = (ChSalesProjectionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getChSalesProjectionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputChSalesProjectionMaster(
        BaseModel<?> oldModel) {
        ChSalesProjectionMasterClp oldClpModel = (ChSalesProjectionMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getChSalesProjectionMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCompanyGroup(BaseModel<?> oldModel) {
        CompanyGroupClp oldClpModel = (CompanyGroupClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCompanyGroupRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCompanyGroupDetails(
        BaseModel<?> oldModel) {
        CompanyGroupDetailsClp oldClpModel = (CompanyGroupDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCompanyGroupDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCompanyIdentifier(BaseModel<?> oldModel) {
        CompanyIdentifierClp oldClpModel = (CompanyIdentifierClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCompanyIdentifierRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCompanyMaster(BaseModel<?> oldModel) {
        CompanyMasterClp oldClpModel = (CompanyMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCompanyMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCompanyParentDetails(
        BaseModel<?> oldModel) {
        CompanyParentDetailsClp oldClpModel = (CompanyParentDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCompanyParentDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCompanyQualifier(BaseModel<?> oldModel) {
        CompanyQualifierClp oldClpModel = (CompanyQualifierClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCompanyQualifierRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCompanyTradeClass(BaseModel<?> oldModel) {
        CompanyTradeClassClp oldClpModel = (CompanyTradeClassClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCompanyTradeClassRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputContractAliasMaster(
        BaseModel<?> oldModel) {
        ContractAliasMasterClp oldClpModel = (ContractAliasMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getContractAliasMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputContractMaster(BaseModel<?> oldModel) {
        ContractMasterClp oldClpModel = (ContractMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getContractMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCpiIndexMaster(BaseModel<?> oldModel) {
        CpiIndexMasterClp oldClpModel = (CpiIndexMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCpiIndexMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCustomViewDetails(BaseModel<?> oldModel) {
        CustomViewDetailsClp oldClpModel = (CustomViewDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCustomViewDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCustomViewMaster(BaseModel<?> oldModel) {
        CustomViewMasterClp oldClpModel = (CustomViewMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCustomViewMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputDeductionCalendarDetails(
        BaseModel<?> oldModel) {
        DeductionCalendarDetailsClp oldClpModel = (DeductionCalendarDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getDeductionCalendarDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputDeductionCalendarMaster(
        BaseModel<?> oldModel) {
        DeductionCalendarMasterClp oldClpModel = (DeductionCalendarMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getDeductionCalendarMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputDeductionDetails(BaseModel<?> oldModel) {
        DeductionDetailsClp oldClpModel = (DeductionDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getDeductionDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputDeductionGroup(BaseModel<?> oldModel) {
        DeductionGroupClp oldClpModel = (DeductionGroupClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getDeductionGroupRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputDeductionGroupDetails(
        BaseModel<?> oldModel) {
        DeductionGroupDetailsClp oldClpModel = (DeductionGroupDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getDeductionGroupDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputDemandForecast(BaseModel<?> oldModel) {
        DemandForecastClp oldClpModel = (DemandForecastClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getDemandForecastRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputDocDetails(BaseModel<?> oldModel) {
        DocDetailsClp oldClpModel = (DocDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getDocDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputFcpActuals(BaseModel<?> oldModel) {
        FcpActualsClp oldClpModel = (FcpActualsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getFcpActualsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputFcpProj(BaseModel<?> oldModel) {
        FcpProjClp oldClpModel = (FcpProjClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getFcpProjRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputFederalNewNdc(BaseModel<?> oldModel) {
        FederalNewNdcClp oldClpModel = (FederalNewNdcClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getFederalNewNdcRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputFileManagement(BaseModel<?> oldModel) {
        FileManagementClp oldClpModel = (FileManagementClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getFileManagementRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputForecastConfig(BaseModel<?> oldModel) {
        ForecastConfigClp oldClpModel = (ForecastConfigClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getForecastConfigRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputForecastingFormula(BaseModel<?> oldModel) {
        ForecastingFormulaClp oldClpModel = (ForecastingFormulaClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getForecastingFormulaRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputForecastingMaster(BaseModel<?> oldModel) {
        ForecastingMasterClp oldClpModel = (ForecastingMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getForecastingMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputForecastingViewMaster(
        BaseModel<?> oldModel) {
        ForecastingViewMasterClp oldClpModel = (ForecastingViewMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getForecastingViewMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputFormulaDetailsMaster(
        BaseModel<?> oldModel) {
        FormulaDetailsMasterClp oldClpModel = (FormulaDetailsMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getFormulaDetailsMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputGcmCompanyDetails(BaseModel<?> oldModel) {
        GcmCompanyDetailsClp oldClpModel = (GcmCompanyDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getGcmCompanyDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputGcmCompanyLink(BaseModel<?> oldModel) {
        GcmCompanyLinkClp oldClpModel = (GcmCompanyLinkClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getGcmCompanyLinkRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputGcmContractDetails(BaseModel<?> oldModel) {
        GcmContractDetailsClp oldClpModel = (GcmContractDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getGcmContractDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputGcmGlobalDetails(BaseModel<?> oldModel) {
        GcmGlobalDetailsClp oldClpModel = (GcmGlobalDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getGcmGlobalDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputGcmItemDetails(BaseModel<?> oldModel) {
        GcmItemDetailsClp oldClpModel = (GcmItemDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getGcmItemDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputGlBalanceMaster(BaseModel<?> oldModel) {
        GlBalanceMasterClp oldClpModel = (GlBalanceMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getGlBalanceMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputGlCostCenterMaster(BaseModel<?> oldModel) {
        GlCostCenterMasterClp oldClpModel = (GlCostCenterMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getGlCostCenterMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputHelperTable(BaseModel<?> oldModel) {
        HelperTableClp oldClpModel = (HelperTableClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getHelperTableRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputHierarchyDefinition(
        BaseModel<?> oldModel) {
        HierarchyDefinitionClp oldClpModel = (HierarchyDefinitionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getHierarchyDefinitionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputHierarchyLevelDefinition(
        BaseModel<?> oldModel) {
        HierarchyLevelDefinitionClp oldClpModel = (HierarchyLevelDefinitionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getHierarchyLevelDefinitionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputHierarchyLevelValues(
        BaseModel<?> oldModel) {
        HierarchyLevelValuesClp oldClpModel = (HierarchyLevelValuesClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getHierarchyLevelValuesRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputHistCompanyGroup(BaseModel<?> oldModel) {
        HistCompanyGroupClp oldClpModel = (HistCompanyGroupClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getHistCompanyGroupRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputHistCompanyGroupDetails(
        BaseModel<?> oldModel) {
        HistCompanyGroupDetailsClp oldClpModel = (HistCompanyGroupDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getHistCompanyGroupDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputHistHierarchyDefinition(
        BaseModel<?> oldModel) {
        HistHierarchyDefinitionClp oldClpModel = (HistHierarchyDefinitionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getHistHierarchyDefinitionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputHistHierarchyLevelDefn(
        BaseModel<?> oldModel) {
        HistHierarchyLevelDefnClp oldClpModel = (HistHierarchyLevelDefnClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getHistHierarchyLevelDefnRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputHistHierarchyLevelValues(
        BaseModel<?> oldModel) {
        HistHierarchyLevelValuesClp oldClpModel = (HistHierarchyLevelValuesClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getHistHierarchyLevelValuesRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputHistItemGroup(BaseModel<?> oldModel) {
        HistItemGroupClp oldClpModel = (HistItemGroupClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getHistItemGroupRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputHistItemGroupDetails(
        BaseModel<?> oldModel) {
        HistItemGroupDetailsClp oldClpModel = (HistItemGroupDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getHistItemGroupDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputHistRelationshipBuilder(
        BaseModel<?> oldModel) {
        HistRelationshipBuilderClp oldClpModel = (HistRelationshipBuilderClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getHistRelationshipBuilderRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputHistRelationshipLevelDefn(
        BaseModel<?> oldModel) {
        HistRelationshipLevelDefnClp oldClpModel = (HistRelationshipLevelDefnClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getHistRelationshipLevelDefnRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputHistWorkflowMaster(BaseModel<?> oldModel) {
        HistWorkflowMasterClp oldClpModel = (HistWorkflowMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getHistWorkflowMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIfpContract(BaseModel<?> oldModel) {
        IfpContractClp oldClpModel = (IfpContractClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIfpContractRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIfpContractDetails(BaseModel<?> oldModel) {
        IfpContractDetailsClp oldClpModel = (IfpContractDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIfpContractDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIfpDetails(BaseModel<?> oldModel) {
        IfpDetailsClp oldClpModel = (IfpDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIfpDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIfpModel(BaseModel<?> oldModel) {
        IfpModelClp oldClpModel = (IfpModelClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIfpModelRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputImtdCfpDetails(BaseModel<?> oldModel) {
        ImtdCfpDetailsClp oldClpModel = (ImtdCfpDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getImtdCfpDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputImtdDeductionDetails(
        BaseModel<?> oldModel) {
        ImtdDeductionDetailsClp oldClpModel = (ImtdDeductionDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getImtdDeductionDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputImtdIfpDetails(BaseModel<?> oldModel) {
        ImtdIfpDetailsClp oldClpModel = (ImtdIfpDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getImtdIfpDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputImtdItemPriceRebateDetails(
        BaseModel<?> oldModel) {
        ImtdItemPriceRebateDetailsClp oldClpModel = (ImtdItemPriceRebateDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getImtdItemPriceRebateDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputImtdLevelValues(BaseModel<?> oldModel) {
        ImtdLevelValuesClp oldClpModel = (ImtdLevelValuesClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getImtdLevelValuesRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputImtdPsDetails(BaseModel<?> oldModel) {
        ImtdPsDetailsClp oldClpModel = (ImtdPsDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getImtdPsDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputImtdRsContractDetailsFr(
        BaseModel<?> oldModel) {
        ImtdRsContractDetailsFrClp oldClpModel = (ImtdRsContractDetailsFrClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getImtdRsContractDetailsFrRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputImtdRsDetails(BaseModel<?> oldModel) {
        ImtdRsDetailsClp oldClpModel = (ImtdRsDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getImtdRsDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputImtdRsDetailsFr(BaseModel<?> oldModel) {
        ImtdRsDetailsFrClp oldClpModel = (ImtdRsDetailsFrClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getImtdRsDetailsFrRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputImtdSalesBasisDetails(
        BaseModel<?> oldModel) {
        ImtdSalesBasisDetailsClp oldClpModel = (ImtdSalesBasisDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getImtdSalesBasisDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputInventoryWdActualMas(
        BaseModel<?> oldModel) {
        InventoryWdActualMasClp oldClpModel = (InventoryWdActualMasClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getInventoryWdActualMasRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputInventoryWdProjMas(BaseModel<?> oldModel) {
        InventoryWdProjMasClp oldClpModel = (InventoryWdProjMasClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getInventoryWdProjMasRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputItemGroup(BaseModel<?> oldModel) {
        ItemGroupClp oldClpModel = (ItemGroupClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getItemGroupRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputItemGroupDetails(BaseModel<?> oldModel) {
        ItemGroupDetailsClp oldClpModel = (ItemGroupDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getItemGroupDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputItemHierarchyDefMaster(
        BaseModel<?> oldModel) {
        ItemHierarchyDefMasterClp oldClpModel = (ItemHierarchyDefMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getItemHierarchyDefMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputItemHierarchyMaster(
        BaseModel<?> oldModel) {
        ItemHierarchyMasterClp oldClpModel = (ItemHierarchyMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getItemHierarchyMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputItemIdentifier(BaseModel<?> oldModel) {
        ItemIdentifierClp oldClpModel = (ItemIdentifierClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getItemIdentifierRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputItemMaster(BaseModel<?> oldModel) {
        ItemMasterClp oldClpModel = (ItemMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getItemMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputItemPricing(BaseModel<?> oldModel) {
        ItemPricingClp oldClpModel = (ItemPricingClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getItemPricingRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputItemPricingQualifier(
        BaseModel<?> oldModel) {
        ItemPricingQualifierClp oldClpModel = (ItemPricingQualifierClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getItemPricingQualifierRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputItemQualifier(BaseModel<?> oldModel) {
        ItemQualifierClp oldClpModel = (ItemQualifierClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getItemQualifierRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldActualMaster(BaseModel<?> oldModel) {
        IvldActualMasterClp oldClpModel = (IvldActualMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldActualMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldAverageShelfLife(
        BaseModel<?> oldModel) {
        IvldAverageShelfLifeClp oldClpModel = (IvldAverageShelfLifeClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldAverageShelfLifeRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldBestPrice(BaseModel<?> oldModel) {
        IvldBestPriceClp oldClpModel = (IvldBestPriceClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldBestPriceRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldCpi(BaseModel<?> oldModel) {
        IvldCpiClp oldClpModel = (IvldCpiClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldCpiRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldDemandActual(BaseModel<?> oldModel) {
        IvldDemandActualClp oldClpModel = (IvldDemandActualClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldDemandActualRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldDemandForecast(BaseModel<?> oldModel) {
        IvldDemandForecastClp oldClpModel = (IvldDemandForecastClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldDemandForecastRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldForecastSales(BaseModel<?> oldModel) {
        IvldForecastSalesClp oldClpModel = (IvldForecastSalesClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldForecastSalesRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldFormulaDetails(BaseModel<?> oldModel) {
        IvldFormulaDetailsClp oldClpModel = (IvldFormulaDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldFormulaDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldGlBalance(BaseModel<?> oldModel) {
        IvldGlBalanceClp oldClpModel = (IvldGlBalanceClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldGlBalanceRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldGlCostCenter(BaseModel<?> oldModel) {
        IvldGlCostCenterClp oldClpModel = (IvldGlCostCenterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldGlCostCenterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldInventoryWdActualMas(
        BaseModel<?> oldModel) {
        IvldInventoryWdActualMasClp oldClpModel = (IvldInventoryWdActualMasClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldInventoryWdActualMasRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldInventoryWdProjMas(
        BaseModel<?> oldModel) {
        IvldInventoryWdProjMasClp oldClpModel = (IvldInventoryWdProjMasClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldInventoryWdProjMasRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldItemHierarchy(BaseModel<?> oldModel) {
        IvldItemHierarchyClp oldClpModel = (IvldItemHierarchyClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldItemHierarchyRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldItemHierarchyDefinition(
        BaseModel<?> oldModel) {
        IvldItemHierarchyDefinitionClp oldClpModel = (IvldItemHierarchyDefinitionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldItemHierarchyDefinitionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldLotMaster(BaseModel<?> oldModel) {
        IvldLotMasterClp oldClpModel = (IvldLotMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldLotMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldMasterDataAttribute(
        BaseModel<?> oldModel) {
        IvldMasterDataAttributeClp oldClpModel = (IvldMasterDataAttributeClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldMasterDataAttributeRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldReturns(BaseModel<?> oldModel) {
        IvldReturnsClp oldClpModel = (IvldReturnsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldReturnsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldSalesMaster(BaseModel<?> oldModel) {
        IvldSalesMasterClp oldClpModel = (IvldSalesMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldSalesMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputLotMaster(BaseModel<?> oldModel) {
        LotMasterClp oldClpModel = (LotMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getLotMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMailNotificationMaster(
        BaseModel<?> oldModel) {
        MailNotificationMasterClp oldClpModel = (MailNotificationMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMailNotificationMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMAssumptions(BaseModel<?> oldModel) {
        MAssumptionsClp oldClpModel = (MAssumptionsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMAssumptionsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMasterDataAttribute(
        BaseModel<?> oldModel) {
        MasterDataAttributeClp oldClpModel = (MasterDataAttributeClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMasterDataAttributeRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMasterDataFiles(BaseModel<?> oldModel) {
        MasterDataFilesClp oldClpModel = (MasterDataFilesClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMasterDataFilesRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMedicaidNewNdc(BaseModel<?> oldModel) {
        MedicaidNewNdcClp oldClpModel = (MedicaidNewNdcClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMedicaidNewNdcRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMedicaidUraActuals(BaseModel<?> oldModel) {
        MedicaidUraActualsClp oldClpModel = (MedicaidUraActualsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMedicaidUraActualsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMedicaidUraProj(BaseModel<?> oldModel) {
        MedicaidUraProjClp oldClpModel = (MedicaidUraProjClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMedicaidUraProjRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputModuleProperties(BaseModel<?> oldModel) {
        ModulePropertiesClp oldClpModel = (ModulePropertiesClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getModulePropertiesRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputModuleSubmoduleMaster(
        BaseModel<?> oldModel) {
        ModuleSubmoduleMasterClp oldClpModel = (ModuleSubmoduleMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getModuleSubmoduleMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMParityLookup(BaseModel<?> oldModel) {
        MParityLookupClp oldClpModel = (MParityLookupClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMParityLookupRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMProjectionSelection(
        BaseModel<?> oldModel) {
        MProjectionSelectionClp oldClpModel = (MProjectionSelectionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMProjectionSelectionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMSalesProjectionMaster(
        BaseModel<?> oldModel) {
        MSalesProjectionMasterClp oldClpModel = (MSalesProjectionMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMSalesProjectionMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMSupplementalDiscActuals(
        BaseModel<?> oldModel) {
        MSupplementalDiscActualsClp oldClpModel = (MSupplementalDiscActualsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMSupplementalDiscActualsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMSupplementalDiscMaster(
        BaseModel<?> oldModel) {
        MSupplementalDiscMasterClp oldClpModel = (MSupplementalDiscMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMSupplementalDiscMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMSupplementalDiscProj(
        BaseModel<?> oldModel) {
        MSupplementalDiscProjClp oldClpModel = (MSupplementalDiscProjClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMSupplementalDiscProjRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputNaProjDetails(BaseModel<?> oldModel) {
        NaProjDetailsClp oldClpModel = (NaProjDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getNaProjDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputNaProjectionSelection(
        BaseModel<?> oldModel) {
        NaProjectionSelectionClp oldClpModel = (NaProjectionSelectionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getNaProjectionSelectionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputNaProjMaster(BaseModel<?> oldModel) {
        NaProjMasterClp oldClpModel = (NaProjMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getNaProjMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputNationalAssumptions(
        BaseModel<?> oldModel) {
        NationalAssumptionsClp oldClpModel = (NationalAssumptionsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getNationalAssumptionsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputNationalAssumptionsActuals(
        BaseModel<?> oldModel) {
        NationalAssumptionsActualsClp oldClpModel = (NationalAssumptionsActualsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getNationalAssumptionsActualsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputNationalAssumptionsProj(
        BaseModel<?> oldModel) {
        NationalAssumptionsProjClp oldClpModel = (NationalAssumptionsProjClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getNationalAssumptionsProjRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputNetSalesFormulaMaster(
        BaseModel<?> oldModel) {
        NetSalesFormulaMasterClp oldClpModel = (NetSalesFormulaMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getNetSalesFormulaMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputNmActualDiscount(BaseModel<?> oldModel) {
        NmActualDiscountClp oldClpModel = (NmActualDiscountClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getNmActualDiscountRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputNmActualPpa(BaseModel<?> oldModel) {
        NmActualPpaClp oldClpModel = (NmActualPpaClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getNmActualPpaRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputNmDiscountProjection(
        BaseModel<?> oldModel) {
        NmDiscountProjectionClp oldClpModel = (NmDiscountProjectionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getNmDiscountProjectionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputNmDiscountProjMaster(
        BaseModel<?> oldModel) {
        NmDiscountProjMasterClp oldClpModel = (NmDiscountProjMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getNmDiscountProjMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputNmPpaProjection(BaseModel<?> oldModel) {
        NmPpaProjectionClp oldClpModel = (NmPpaProjectionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getNmPpaProjectionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputNmPpaProjectionMaster(
        BaseModel<?> oldModel) {
        NmPpaProjectionMasterClp oldClpModel = (NmPpaProjectionMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getNmPpaProjectionMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputNmProjectionSelection(
        BaseModel<?> oldModel) {
        NmProjectionSelectionClp oldClpModel = (NmProjectionSelectionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getNmProjectionSelectionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputNmSalesProjection(BaseModel<?> oldModel) {
        NmSalesProjectionClp oldClpModel = (NmSalesProjectionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getNmSalesProjectionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputNmSalesProjectionMaster(
        BaseModel<?> oldModel) {
        NmSalesProjectionMasterClp oldClpModel = (NmSalesProjectionMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getNmSalesProjectionMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPeriod(BaseModel<?> oldModel) {
        PeriodClp oldClpModel = (PeriodClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPeriodRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPhsActuals(BaseModel<?> oldModel) {
        PhsActualsClp oldClpModel = (PhsActualsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPhsActualsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPhsProj(BaseModel<?> oldModel) {
        PhsProjClp oldClpModel = (PhsProjClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPhsProjRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProjectionCustDetails(
        BaseModel<?> oldModel) {
        ProjectionCustDetailsClp oldClpModel = (ProjectionCustDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProjectionCustDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProjectionCustHierarchy(
        BaseModel<?> oldModel) {
        ProjectionCustHierarchyClp oldClpModel = (ProjectionCustHierarchyClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProjectionCustHierarchyRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProjectionDetails(BaseModel<?> oldModel) {
        ProjectionDetailsClp oldClpModel = (ProjectionDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProjectionDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProjectionMaster(BaseModel<?> oldModel) {
        ProjectionMasterClp oldClpModel = (ProjectionMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProjectionMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProjectionNameConfig(
        BaseModel<?> oldModel) {
        ProjectionNameConfigClp oldClpModel = (ProjectionNameConfigClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProjectionNameConfigRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProjectionProdDetails(
        BaseModel<?> oldModel) {
        ProjectionProdDetailsClp oldClpModel = (ProjectionProdDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProjectionProdDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProjectionProdHierarchy(
        BaseModel<?> oldModel) {
        ProjectionProdHierarchyClp oldClpModel = (ProjectionProdHierarchyClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProjectionProdHierarchyRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPsContract(BaseModel<?> oldModel) {
        PsContractClp oldClpModel = (PsContractClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPsContractRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPsContractDetails(BaseModel<?> oldModel) {
        PsContractDetailsClp oldClpModel = (PsContractDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPsContractDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPsDetails(BaseModel<?> oldModel) {
        PsDetailsClp oldClpModel = (PsDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPsDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPsModel(BaseModel<?> oldModel) {
        PsModelClp oldClpModel = (PsModelClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPsModelRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputRebatePlanMaster(BaseModel<?> oldModel) {
        RebatePlanMasterClp oldClpModel = (RebatePlanMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getRebatePlanMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputRebatePlanTier(BaseModel<?> oldModel) {
        RebatePlanTierClp oldClpModel = (RebatePlanTierClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getRebatePlanTierRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputRebateTierFormula(BaseModel<?> oldModel) {
        RebateTierFormulaClp oldClpModel = (RebateTierFormulaClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getRebateTierFormulaRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputRelationshipBuilder(
        BaseModel<?> oldModel) {
        RelationshipBuilderClp oldClpModel = (RelationshipBuilderClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getRelationshipBuilderRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputRelationshipLevelDefinition(
        BaseModel<?> oldModel) {
        RelationshipLevelDefinitionClp oldClpModel = (RelationshipLevelDefinitionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getRelationshipLevelDefinitionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputReturnsMaster(BaseModel<?> oldModel) {
        ReturnsMasterClp oldClpModel = (ReturnsMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getReturnsMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputRsContract(BaseModel<?> oldModel) {
        RsContractClp oldClpModel = (RsContractClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getRsContractRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputRsContractDetails(BaseModel<?> oldModel) {
        RsContractDetailsClp oldClpModel = (RsContractDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getRsContractDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputRsContractDetailsFr(
        BaseModel<?> oldModel) {
        RsContractDetailsFrClp oldClpModel = (RsContractDetailsFrClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getRsContractDetailsFrRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputRsDetails(BaseModel<?> oldModel) {
        RsDetailsClp oldClpModel = (RsDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getRsDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputRsDetailsFr(BaseModel<?> oldModel) {
        RsDetailsFrClp oldClpModel = (RsDetailsFrClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getRsDetailsFrRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputRsModel(BaseModel<?> oldModel) {
        RsModelClp oldClpModel = (RsModelClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getRsModelRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputSalesBasisDetails(BaseModel<?> oldModel) {
        SalesBasisDetailsClp oldClpModel = (SalesBasisDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getSalesBasisDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputSalesMaster(BaseModel<?> oldModel) {
        SalesMasterClp oldClpModel = (SalesMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getSalesMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStChActualDiscount(BaseModel<?> oldModel) {
        StChActualDiscountClp oldClpModel = (StChActualDiscountClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStChActualDiscountRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStChAssumptions(BaseModel<?> oldModel) {
        StChAssumptionsClp oldClpModel = (StChAssumptionsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStChAssumptionsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStChDiscountProjMaster(
        BaseModel<?> oldModel) {
        StChDiscountProjMasterClp oldClpModel = (StChDiscountProjMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStChDiscountProjMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStChProjectionDiscount(
        BaseModel<?> oldModel) {
        StChProjectionDiscountClp oldClpModel = (StChProjectionDiscountClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStChProjectionDiscountRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStChSalesProjectionMaster(
        BaseModel<?> oldModel) {
        StChSalesProjectionMasterClp oldClpModel = (StChSalesProjectionMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStChSalesProjectionMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStDeductionCalendarDetails(
        BaseModel<?> oldModel) {
        StDeductionCalendarDetailsClp oldClpModel = (StDeductionCalendarDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStDeductionCalendarDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStFederalNewNdc(BaseModel<?> oldModel) {
        StFederalNewNdcClp oldClpModel = (StFederalNewNdcClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStFederalNewNdcRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStMAssumptions(BaseModel<?> oldModel) {
        StMAssumptionsClp oldClpModel = (StMAssumptionsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStMAssumptionsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStMedicaidNewNdc(BaseModel<?> oldModel) {
        StMedicaidNewNdcClp oldClpModel = (StMedicaidNewNdcClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStMedicaidNewNdcRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStMSupplementalDiscActuals(
        BaseModel<?> oldModel) {
        StMSupplementalDiscActualsClp oldClpModel = (StMSupplementalDiscActualsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStMSupplementalDiscActualsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStMSupplementalDiscMaster(
        BaseModel<?> oldModel) {
        StMSupplementalDiscMasterClp oldClpModel = (StMSupplementalDiscMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStMSupplementalDiscMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStMSupplementalDiscProj(
        BaseModel<?> oldModel) {
        StMSupplementalDiscProjClp oldClpModel = (StMSupplementalDiscProjClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStMSupplementalDiscProjRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStNationalAssumptions(
        BaseModel<?> oldModel) {
        StNationalAssumptionsClp oldClpModel = (StNationalAssumptionsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStNationalAssumptionsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStNewNdc(BaseModel<?> oldModel) {
        StNewNdcClp oldClpModel = (StNewNdcClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStNewNdcRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStNmActualDiscount(BaseModel<?> oldModel) {
        StNmActualDiscountClp oldClpModel = (StNmActualDiscountClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStNmActualDiscountRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStNmActualPpa(BaseModel<?> oldModel) {
        StNmActualPpaClp oldClpModel = (StNmActualPpaClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStNmActualPpaRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStNmAssumptions(BaseModel<?> oldModel) {
        StNmAssumptionsClp oldClpModel = (StNmAssumptionsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStNmAssumptionsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStNmDiscountProjection(
        BaseModel<?> oldModel) {
        StNmDiscountProjectionClp oldClpModel = (StNmDiscountProjectionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStNmDiscountProjectionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStNmDiscountProjMaster(
        BaseModel<?> oldModel) {
        StNmDiscountProjMasterClp oldClpModel = (StNmDiscountProjMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStNmDiscountProjMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStNmPpaProjection(BaseModel<?> oldModel) {
        StNmPpaProjectionClp oldClpModel = (StNmPpaProjectionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStNmPpaProjectionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStNmPpaProjectionMaster(
        BaseModel<?> oldModel) {
        StNmPpaProjectionMasterClp oldClpModel = (StNmPpaProjectionMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStNmPpaProjectionMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStSelectionTable(BaseModel<?> oldModel) {
        StSelectionTableClp oldClpModel = (StSelectionTableClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStSelectionTableRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputTransactionModuleDetails(
        BaseModel<?> oldModel) {
        TransactionModuleDetailsClp oldClpModel = (TransactionModuleDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getTransactionModuleDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputTransactionModuleMaster(
        BaseModel<?> oldModel) {
        TransactionModuleMasterClp oldClpModel = (TransactionModuleMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getTransactionModuleMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputUdcs(BaseModel<?> oldModel) {
        UdcsClp oldClpModel = (UdcsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getUdcsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputUsergroupBusinessrole(
        BaseModel<?> oldModel) {
        UsergroupBusinessroleClp oldClpModel = (UsergroupBusinessroleClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getUsergroupBusinessroleRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputUsergroupDomainMaster(
        BaseModel<?> oldModel) {
        UsergroupDomainMasterClp oldClpModel = (UsergroupDomainMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getUsergroupDomainMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputVwDemandForecastActual(
        BaseModel<?> oldModel) {
        VwDemandForecastActualClp oldClpModel = (VwDemandForecastActualClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getVwDemandForecastActualRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputVwInventoryWdActualProjMas(
        BaseModel<?> oldModel) {
        VwInventoryWdActualProjMasClp oldClpModel = (VwInventoryWdActualProjMasClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getVwInventoryWdActualProjMasRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputVwIvldDemandForecastActual(
        BaseModel<?> oldModel) {
        VwIvldDemandForecastActualClp oldClpModel = (VwIvldDemandForecastActualClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getVwIvldDemandForecastActualRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputVwIvldInventoryWdActualProjMas(
        BaseModel<?> oldModel) {
        VwIvldInventoryWdActualProjMasClp oldClpModel = (VwIvldInventoryWdActualProjMasClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getVwIvldInventoryWdActualProjMasRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputVwUserTables(BaseModel<?> oldModel) {
        VwUserTablesClp oldClpModel = (VwUserTablesClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getVwUserTablesRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputWfMailConfig(BaseModel<?> oldModel) {
        WfMailConfigClp oldClpModel = (WfMailConfigClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getWfMailConfigRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputWorkflowMaster(BaseModel<?> oldModel) {
        WorkflowMasterClp oldClpModel = (WorkflowMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getWorkflowMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputWorkflowProcessInfo(
        BaseModel<?> oldModel) {
        WorkflowProcessInfoClp oldClpModel = (WorkflowProcessInfoClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getWorkflowProcessInfoRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputWorkflowProfile(BaseModel<?> oldModel) {
        WorkflowProfileClp oldClpModel = (WorkflowProfileClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getWorkflowProfileRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInput(Object obj) {
        if (obj instanceof BaseModel<?>) {
            return translateInput((BaseModel<?>) obj);
        } else if (obj instanceof List<?>) {
            return translateInput((List<Object>) obj);
        } else {
            return obj;
        }
    }

    public static Object translateOutput(BaseModel<?> oldModel) {
        Class<?> oldModelClass = oldModel.getClass();

        String oldModelClassName = oldModelClass.getName();

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.AccrualDetailsImpl")) {
            return translateOutputAccrualDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.AccrualMasterImpl")) {
            return translateOutputAccrualMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ActualsMasterImpl")) {
            return translateOutputActualsMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.AdditionalNotesImpl")) {
            return translateOutputAdditionalNotes(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.AuditMasterInboundImpl")) {
            return translateOutputAuditMasterInbound(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.AverageShelfLifeMasterImpl")) {
            return translateOutputAverageShelfLifeMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.BestPriceMasterImpl")) {
            return translateOutputBestPriceMaster(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.BrandMasterImpl")) {
            return translateOutputBrandMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.BusinessroleMasterImpl")) {
            return translateOutputBusinessroleMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.BusinessroleModuleImpl")) {
            return translateOutputBusinessroleModule(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.CcpDetailsImpl")) {
            return translateOutputCcpDetails(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.CcpMapImpl")) {
            return translateOutputCcpMap(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.CdrDetailsImpl")) {
            return translateOutputCdrDetails(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.CdrModelImpl")) {
            return translateOutputCdrModel(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.CfpContractImpl")) {
            return translateOutputCfpContract(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.CfpContractDetailsImpl")) {
            return translateOutputCfpContractDetails(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.CfpDetailsImpl")) {
            return translateOutputCfpDetails(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.CfpModelImpl")) {
            return translateOutputCfpModel(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ChActualDiscountImpl")) {
            return translateOutputChActualDiscount(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ChActualSalesImpl")) {
            return translateOutputChActualSales(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ChAssumptionsImpl")) {
            return translateOutputChAssumptions(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ChDiscountProjMasterImpl")) {
            return translateOutputChDiscountProjMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ChProjectionDiscountImpl")) {
            return translateOutputChProjectionDiscount(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ChProjectionSelectionImpl")) {
            return translateOutputChProjectionSelection(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ChSalesProjectionImpl")) {
            return translateOutputChSalesProjection(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ChSalesProjectionMasterImpl")) {
            return translateOutputChSalesProjectionMaster(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.CompanyGroupImpl")) {
            return translateOutputCompanyGroup(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.CompanyGroupDetailsImpl")) {
            return translateOutputCompanyGroupDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.CompanyIdentifierImpl")) {
            return translateOutputCompanyIdentifier(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.CompanyMasterImpl")) {
            return translateOutputCompanyMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.CompanyParentDetailsImpl")) {
            return translateOutputCompanyParentDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.CompanyQualifierImpl")) {
            return translateOutputCompanyQualifier(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.CompanyTradeClassImpl")) {
            return translateOutputCompanyTradeClass(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ContractAliasMasterImpl")) {
            return translateOutputContractAliasMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ContractMasterImpl")) {
            return translateOutputContractMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.CpiIndexMasterImpl")) {
            return translateOutputCpiIndexMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.CustomViewDetailsImpl")) {
            return translateOutputCustomViewDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.CustomViewMasterImpl")) {
            return translateOutputCustomViewMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.DeductionCalendarDetailsImpl")) {
            return translateOutputDeductionCalendarDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.DeductionCalendarMasterImpl")) {
            return translateOutputDeductionCalendarMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.DeductionDetailsImpl")) {
            return translateOutputDeductionDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.DeductionGroupImpl")) {
            return translateOutputDeductionGroup(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.DeductionGroupDetailsImpl")) {
            return translateOutputDeductionGroupDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.DemandForecastImpl")) {
            return translateOutputDemandForecast(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.DocDetailsImpl")) {
            return translateOutputDocDetails(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.FcpActualsImpl")) {
            return translateOutputFcpActuals(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.FcpProjImpl")) {
            return translateOutputFcpProj(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.FederalNewNdcImpl")) {
            return translateOutputFederalNewNdc(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.FileManagementImpl")) {
            return translateOutputFileManagement(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ForecastConfigImpl")) {
            return translateOutputForecastConfig(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ForecastingFormulaImpl")) {
            return translateOutputForecastingFormula(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ForecastingMasterImpl")) {
            return translateOutputForecastingMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ForecastingViewMasterImpl")) {
            return translateOutputForecastingViewMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.FormulaDetailsMasterImpl")) {
            return translateOutputFormulaDetailsMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.GcmCompanyDetailsImpl")) {
            return translateOutputGcmCompanyDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.GcmCompanyLinkImpl")) {
            return translateOutputGcmCompanyLink(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.GcmContractDetailsImpl")) {
            return translateOutputGcmContractDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.GcmGlobalDetailsImpl")) {
            return translateOutputGcmGlobalDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.GcmItemDetailsImpl")) {
            return translateOutputGcmItemDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.GlBalanceMasterImpl")) {
            return translateOutputGlBalanceMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.GlCostCenterMasterImpl")) {
            return translateOutputGlCostCenterMaster(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.HelperTableImpl")) {
            return translateOutputHelperTable(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.HierarchyDefinitionImpl")) {
            return translateOutputHierarchyDefinition(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.HierarchyLevelDefinitionImpl")) {
            return translateOutputHierarchyLevelDefinition(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.HierarchyLevelValuesImpl")) {
            return translateOutputHierarchyLevelValues(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.HistCompanyGroupImpl")) {
            return translateOutputHistCompanyGroup(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.HistCompanyGroupDetailsImpl")) {
            return translateOutputHistCompanyGroupDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.HistHierarchyDefinitionImpl")) {
            return translateOutputHistHierarchyDefinition(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.HistHierarchyLevelDefnImpl")) {
            return translateOutputHistHierarchyLevelDefn(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.HistHierarchyLevelValuesImpl")) {
            return translateOutputHistHierarchyLevelValues(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.HistItemGroupImpl")) {
            return translateOutputHistItemGroup(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.HistItemGroupDetailsImpl")) {
            return translateOutputHistItemGroupDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.HistRelationshipBuilderImpl")) {
            return translateOutputHistRelationshipBuilder(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.HistRelationshipLevelDefnImpl")) {
            return translateOutputHistRelationshipLevelDefn(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.HistWorkflowMasterImpl")) {
            return translateOutputHistWorkflowMaster(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.IfpContractImpl")) {
            return translateOutputIfpContract(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.IfpContractDetailsImpl")) {
            return translateOutputIfpContractDetails(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.IfpDetailsImpl")) {
            return translateOutputIfpDetails(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.IfpModelImpl")) {
            return translateOutputIfpModel(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ImtdCfpDetailsImpl")) {
            return translateOutputImtdCfpDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ImtdDeductionDetailsImpl")) {
            return translateOutputImtdDeductionDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ImtdIfpDetailsImpl")) {
            return translateOutputImtdIfpDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ImtdItemPriceRebateDetailsImpl")) {
            return translateOutputImtdItemPriceRebateDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ImtdLevelValuesImpl")) {
            return translateOutputImtdLevelValues(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ImtdPsDetailsImpl")) {
            return translateOutputImtdPsDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ImtdRsContractDetailsFrImpl")) {
            return translateOutputImtdRsContractDetailsFr(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ImtdRsDetailsImpl")) {
            return translateOutputImtdRsDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ImtdRsDetailsFrImpl")) {
            return translateOutputImtdRsDetailsFr(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ImtdSalesBasisDetailsImpl")) {
            return translateOutputImtdSalesBasisDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.InventoryWdActualMasImpl")) {
            return translateOutputInventoryWdActualMas(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.InventoryWdProjMasImpl")) {
            return translateOutputInventoryWdProjMas(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.ItemGroupImpl")) {
            return translateOutputItemGroup(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ItemGroupDetailsImpl")) {
            return translateOutputItemGroupDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ItemHierarchyDefMasterImpl")) {
            return translateOutputItemHierarchyDefMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ItemHierarchyMasterImpl")) {
            return translateOutputItemHierarchyMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ItemIdentifierImpl")) {
            return translateOutputItemIdentifier(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.ItemMasterImpl")) {
            return translateOutputItemMaster(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.ItemPricingImpl")) {
            return translateOutputItemPricing(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ItemPricingQualifierImpl")) {
            return translateOutputItemPricingQualifier(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ItemQualifierImpl")) {
            return translateOutputItemQualifier(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.IvldActualMasterImpl")) {
            return translateOutputIvldActualMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.IvldAverageShelfLifeImpl")) {
            return translateOutputIvldAverageShelfLife(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.IvldBestPriceImpl")) {
            return translateOutputIvldBestPrice(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.IvldCpiImpl")) {
            return translateOutputIvldCpi(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.IvldDemandActualImpl")) {
            return translateOutputIvldDemandActual(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.IvldDemandForecastImpl")) {
            return translateOutputIvldDemandForecast(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.IvldForecastSalesImpl")) {
            return translateOutputIvldForecastSales(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.IvldFormulaDetailsImpl")) {
            return translateOutputIvldFormulaDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.IvldGlBalanceImpl")) {
            return translateOutputIvldGlBalance(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.IvldGlCostCenterImpl")) {
            return translateOutputIvldGlCostCenter(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.IvldInventoryWdActualMasImpl")) {
            return translateOutputIvldInventoryWdActualMas(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.IvldInventoryWdProjMasImpl")) {
            return translateOutputIvldInventoryWdProjMas(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.IvldItemHierarchyImpl")) {
            return translateOutputIvldItemHierarchy(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.IvldItemHierarchyDefinitionImpl")) {
            return translateOutputIvldItemHierarchyDefinition(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.IvldLotMasterImpl")) {
            return translateOutputIvldLotMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.IvldMasterDataAttributeImpl")) {
            return translateOutputIvldMasterDataAttribute(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.IvldReturnsImpl")) {
            return translateOutputIvldReturns(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.IvldSalesMasterImpl")) {
            return translateOutputIvldSalesMaster(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.LotMasterImpl")) {
            return translateOutputLotMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.MailNotificationMasterImpl")) {
            return translateOutputMailNotificationMaster(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.MAssumptionsImpl")) {
            return translateOutputMAssumptions(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.MasterDataAttributeImpl")) {
            return translateOutputMasterDataAttribute(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.MasterDataFilesImpl")) {
            return translateOutputMasterDataFiles(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.MedicaidNewNdcImpl")) {
            return translateOutputMedicaidNewNdc(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.MedicaidUraActualsImpl")) {
            return translateOutputMedicaidUraActuals(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.MedicaidUraProjImpl")) {
            return translateOutputMedicaidUraProj(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ModulePropertiesImpl")) {
            return translateOutputModuleProperties(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ModuleSubmoduleMasterImpl")) {
            return translateOutputModuleSubmoduleMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.MParityLookupImpl")) {
            return translateOutputMParityLookup(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.MProjectionSelectionImpl")) {
            return translateOutputMProjectionSelection(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.MSalesProjectionMasterImpl")) {
            return translateOutputMSalesProjectionMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.MSupplementalDiscActualsImpl")) {
            return translateOutputMSupplementalDiscActuals(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.MSupplementalDiscMasterImpl")) {
            return translateOutputMSupplementalDiscMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.MSupplementalDiscProjImpl")) {
            return translateOutputMSupplementalDiscProj(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.NaProjDetailsImpl")) {
            return translateOutputNaProjDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.NaProjectionSelectionImpl")) {
            return translateOutputNaProjectionSelection(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.NaProjMasterImpl")) {
            return translateOutputNaProjMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.NationalAssumptionsImpl")) {
            return translateOutputNationalAssumptions(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.NationalAssumptionsActualsImpl")) {
            return translateOutputNationalAssumptionsActuals(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.NationalAssumptionsProjImpl")) {
            return translateOutputNationalAssumptionsProj(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.NetSalesFormulaMasterImpl")) {
            return translateOutputNetSalesFormulaMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.NmActualDiscountImpl")) {
            return translateOutputNmActualDiscount(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.NmActualPpaImpl")) {
            return translateOutputNmActualPpa(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.NmDiscountProjectionImpl")) {
            return translateOutputNmDiscountProjection(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.NmDiscountProjMasterImpl")) {
            return translateOutputNmDiscountProjMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.NmPpaProjectionImpl")) {
            return translateOutputNmPpaProjection(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.NmPpaProjectionMasterImpl")) {
            return translateOutputNmPpaProjectionMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.NmProjectionSelectionImpl")) {
            return translateOutputNmProjectionSelection(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.NmSalesProjectionImpl")) {
            return translateOutputNmSalesProjection(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.NmSalesProjectionMasterImpl")) {
            return translateOutputNmSalesProjectionMaster(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.PeriodImpl")) {
            return translateOutputPeriod(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.PhsActualsImpl")) {
            return translateOutputPhsActuals(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.PhsProjImpl")) {
            return translateOutputPhsProj(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ProjectionCustDetailsImpl")) {
            return translateOutputProjectionCustDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ProjectionCustHierarchyImpl")) {
            return translateOutputProjectionCustHierarchy(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ProjectionDetailsImpl")) {
            return translateOutputProjectionDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ProjectionMasterImpl")) {
            return translateOutputProjectionMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ProjectionNameConfigImpl")) {
            return translateOutputProjectionNameConfig(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ProjectionProdDetailsImpl")) {
            return translateOutputProjectionProdDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ProjectionProdHierarchyImpl")) {
            return translateOutputProjectionProdHierarchy(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.PsContractImpl")) {
            return translateOutputPsContract(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.PsContractDetailsImpl")) {
            return translateOutputPsContractDetails(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.PsDetailsImpl")) {
            return translateOutputPsDetails(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.PsModelImpl")) {
            return translateOutputPsModel(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.RebatePlanMasterImpl")) {
            return translateOutputRebatePlanMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.RebatePlanTierImpl")) {
            return translateOutputRebatePlanTier(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.RebateTierFormulaImpl")) {
            return translateOutputRebateTierFormula(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.RelationshipBuilderImpl")) {
            return translateOutputRelationshipBuilder(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.RelationshipLevelDefinitionImpl")) {
            return translateOutputRelationshipLevelDefinition(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.ReturnsMasterImpl")) {
            return translateOutputReturnsMaster(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.RsContractImpl")) {
            return translateOutputRsContract(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.RsContractDetailsImpl")) {
            return translateOutputRsContractDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.RsContractDetailsFrImpl")) {
            return translateOutputRsContractDetailsFr(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.RsDetailsImpl")) {
            return translateOutputRsDetails(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.RsDetailsFrImpl")) {
            return translateOutputRsDetailsFr(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.RsModelImpl")) {
            return translateOutputRsModel(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.SalesBasisDetailsImpl")) {
            return translateOutputSalesBasisDetails(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.SalesMasterImpl")) {
            return translateOutputSalesMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StChActualDiscountImpl")) {
            return translateOutputStChActualDiscount(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StChAssumptionsImpl")) {
            return translateOutputStChAssumptions(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StChDiscountProjMasterImpl")) {
            return translateOutputStChDiscountProjMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StChProjectionDiscountImpl")) {
            return translateOutputStChProjectionDiscount(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StChSalesProjectionMasterImpl")) {
            return translateOutputStChSalesProjectionMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StDeductionCalendarDetailsImpl")) {
            return translateOutputStDeductionCalendarDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StFederalNewNdcImpl")) {
            return translateOutputStFederalNewNdc(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StMAssumptionsImpl")) {
            return translateOutputStMAssumptions(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StMedicaidNewNdcImpl")) {
            return translateOutputStMedicaidNewNdc(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StMSupplementalDiscActualsImpl")) {
            return translateOutputStMSupplementalDiscActuals(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StMSupplementalDiscMasterImpl")) {
            return translateOutputStMSupplementalDiscMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StMSupplementalDiscProjImpl")) {
            return translateOutputStMSupplementalDiscProj(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StNationalAssumptionsImpl")) {
            return translateOutputStNationalAssumptions(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.StNewNdcImpl")) {
            return translateOutputStNewNdc(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StNmActualDiscountImpl")) {
            return translateOutputStNmActualDiscount(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StNmActualPpaImpl")) {
            return translateOutputStNmActualPpa(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StNmAssumptionsImpl")) {
            return translateOutputStNmAssumptions(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StNmDiscountProjectionImpl")) {
            return translateOutputStNmDiscountProjection(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StNmDiscountProjMasterImpl")) {
            return translateOutputStNmDiscountProjMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StNmPpaProjectionImpl")) {
            return translateOutputStNmPpaProjection(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StNmPpaProjectionMasterImpl")) {
            return translateOutputStNmPpaProjectionMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.StSelectionTableImpl")) {
            return translateOutputStSelectionTable(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.TransactionModuleDetailsImpl")) {
            return translateOutputTransactionModuleDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.TransactionModuleMasterImpl")) {
            return translateOutputTransactionModuleMaster(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.UdcsImpl")) {
            return translateOutputUdcs(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.UsergroupBusinessroleImpl")) {
            return translateOutputUsergroupBusinessrole(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.UsergroupDomainMasterImpl")) {
            return translateOutputUsergroupDomainMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.VwDemandForecastActualImpl")) {
            return translateOutputVwDemandForecastActual(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.VwInventoryWdActualProjMasImpl")) {
            return translateOutputVwInventoryWdActualProjMas(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.VwIvldDemandForecastActualImpl")) {
            return translateOutputVwIvldDemandForecastActual(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.VwIvldInventoryWdActualProjMasImpl")) {
            return translateOutputVwIvldInventoryWdActualProjMas(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.VwUserTablesImpl")) {
            return translateOutputVwUserTables(oldModel);
        }

        if (oldModelClassName.equals("com.stpl.app.model.impl.WfMailConfigImpl")) {
            return translateOutputWfMailConfig(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.WorkflowMasterImpl")) {
            return translateOutputWorkflowMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.WorkflowProcessInfoImpl")) {
            return translateOutputWorkflowProcessInfo(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.model.impl.WorkflowProfileImpl")) {
            return translateOutputWorkflowProfile(oldModel);
        }

        return oldModel;
    }

    public static Object translateOutput(List<Object> oldList) {
        List<Object> newList = new ArrayList<Object>(oldList.size());

        for (int i = 0; i < oldList.size(); i++) {
            Object curObj = oldList.get(i);

            newList.add(translateOutput(curObj));
        }

        return newList;
    }

    public static Object translateOutput(Object obj) {
        if (obj instanceof BaseModel<?>) {
            return translateOutput((BaseModel<?>) obj);
        } else if (obj instanceof List<?>) {
            return translateOutput((List<Object>) obj);
        } else {
            return obj;
        }
    }

    public static Throwable translateThrowable(Throwable throwable) {
        if (_useReflectionToTranslateThrowable) {
            try {
                UnsyncByteArrayOutputStream unsyncByteArrayOutputStream = new UnsyncByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(unsyncByteArrayOutputStream);

                objectOutputStream.writeObject(throwable);

                objectOutputStream.flush();
                objectOutputStream.close();

                UnsyncByteArrayInputStream unsyncByteArrayInputStream = new UnsyncByteArrayInputStream(unsyncByteArrayOutputStream.unsafeGetByteArray(),
                        0, unsyncByteArrayOutputStream.size());

                Thread currentThread = Thread.currentThread();

                ClassLoader contextClassLoader = currentThread.getContextClassLoader();

                ObjectInputStream objectInputStream = new ClassLoaderObjectInputStream(unsyncByteArrayInputStream,
                        contextClassLoader);

                throwable = (Throwable) objectInputStream.readObject();

                objectInputStream.close();

                return throwable;
            } catch (SecurityException se) {
                if (_log.isInfoEnabled()) {
                    _log.info("Do not use reflection to translate throwable");
                }

                _useReflectionToTranslateThrowable = false;
            } catch (Throwable throwable2) {
                _log.error(throwable2, throwable2);

                return throwable2;
            }
        }

        Class<?> clazz = throwable.getClass();

        String className = clazz.getName();

        if (className.equals(PortalException.class.getName())) {
            return new PortalException();
        }

        if (className.equals(SystemException.class.getName())) {
            return new SystemException();
        }

        if (className.equals("com.stpl.app.NoSuchAccrualDetailsException")) {
            return new com.stpl.app.NoSuchAccrualDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchAccrualMasterException")) {
            return new com.stpl.app.NoSuchAccrualMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchActualsMasterException")) {
            return new com.stpl.app.NoSuchActualsMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchAdditionalNotesException")) {
            return new com.stpl.app.NoSuchAdditionalNotesException();
        }

        if (className.equals("com.stpl.app.NoSuchAuditMasterInboundException")) {
            return new com.stpl.app.NoSuchAuditMasterInboundException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchAverageShelfLifeMasterException")) {
            return new com.stpl.app.NoSuchAverageShelfLifeMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchBestPriceMasterException")) {
            return new com.stpl.app.NoSuchBestPriceMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchBrandMasterException")) {
            return new com.stpl.app.NoSuchBrandMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchBusinessroleMasterException")) {
            return new com.stpl.app.NoSuchBusinessroleMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchBusinessroleModuleException")) {
            return new com.stpl.app.NoSuchBusinessroleModuleException();
        }

        if (className.equals("com.stpl.app.NoSuchCcpDetailsException")) {
            return new com.stpl.app.NoSuchCcpDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchCcpMapException")) {
            return new com.stpl.app.NoSuchCcpMapException();
        }

        if (className.equals("com.stpl.app.NoSuchCdrDetailsException")) {
            return new com.stpl.app.NoSuchCdrDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchCdrModelException")) {
            return new com.stpl.app.NoSuchCdrModelException();
        }

        if (className.equals("com.stpl.app.NoSuchCfpContractException")) {
            return new com.stpl.app.NoSuchCfpContractException();
        }

        if (className.equals("com.stpl.app.NoSuchCfpContractDetailsException")) {
            return new com.stpl.app.NoSuchCfpContractDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchCfpDetailsException")) {
            return new com.stpl.app.NoSuchCfpDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchCfpModelException")) {
            return new com.stpl.app.NoSuchCfpModelException();
        }

        if (className.equals("com.stpl.app.NoSuchChActualDiscountException")) {
            return new com.stpl.app.NoSuchChActualDiscountException();
        }

        if (className.equals("com.stpl.app.NoSuchChActualSalesException")) {
            return new com.stpl.app.NoSuchChActualSalesException();
        }

        if (className.equals("com.stpl.app.NoSuchChAssumptionsException")) {
            return new com.stpl.app.NoSuchChAssumptionsException();
        }

        if (className.equals("com.stpl.app.NoSuchChDiscountProjMasterException")) {
            return new com.stpl.app.NoSuchChDiscountProjMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchChProjectionDiscountException")) {
            return new com.stpl.app.NoSuchChProjectionDiscountException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchChProjectionSelectionException")) {
            return new com.stpl.app.NoSuchChProjectionSelectionException();
        }

        if (className.equals("com.stpl.app.NoSuchChSalesProjectionException")) {
            return new com.stpl.app.NoSuchChSalesProjectionException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchChSalesProjectionMasterException")) {
            return new com.stpl.app.NoSuchChSalesProjectionMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchCompanyGroupException")) {
            return new com.stpl.app.NoSuchCompanyGroupException();
        }

        if (className.equals("com.stpl.app.NoSuchCompanyGroupDetailsException")) {
            return new com.stpl.app.NoSuchCompanyGroupDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchCompanyIdentifierException")) {
            return new com.stpl.app.NoSuchCompanyIdentifierException();
        }

        if (className.equals("com.stpl.app.NoSuchCompanyMasterException")) {
            return new com.stpl.app.NoSuchCompanyMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchCompanyParentDetailsException")) {
            return new com.stpl.app.NoSuchCompanyParentDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchCompanyQualifierException")) {
            return new com.stpl.app.NoSuchCompanyQualifierException();
        }

        if (className.equals("com.stpl.app.NoSuchCompanyTradeClassException")) {
            return new com.stpl.app.NoSuchCompanyTradeClassException();
        }

        if (className.equals("com.stpl.app.NoSuchContractAliasMasterException")) {
            return new com.stpl.app.NoSuchContractAliasMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchContractMasterException")) {
            return new com.stpl.app.NoSuchContractMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchCpiIndexMasterException")) {
            return new com.stpl.app.NoSuchCpiIndexMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchCustomViewDetailsException")) {
            return new com.stpl.app.NoSuchCustomViewDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchCustomViewMasterException")) {
            return new com.stpl.app.NoSuchCustomViewMasterException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchDeductionCalendarDetailsException")) {
            return new com.stpl.app.NoSuchDeductionCalendarDetailsException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchDeductionCalendarMasterException")) {
            return new com.stpl.app.NoSuchDeductionCalendarMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchDeductionDetailsException")) {
            return new com.stpl.app.NoSuchDeductionDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchDeductionGroupException")) {
            return new com.stpl.app.NoSuchDeductionGroupException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchDeductionGroupDetailsException")) {
            return new com.stpl.app.NoSuchDeductionGroupDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchDemandForecastException")) {
            return new com.stpl.app.NoSuchDemandForecastException();
        }

        if (className.equals("com.stpl.app.NoSuchDocDetailsException")) {
            return new com.stpl.app.NoSuchDocDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchFcpActualsException")) {
            return new com.stpl.app.NoSuchFcpActualsException();
        }

        if (className.equals("com.stpl.app.NoSuchFcpProjException")) {
            return new com.stpl.app.NoSuchFcpProjException();
        }

        if (className.equals("com.stpl.app.NoSuchFederalNewNdcException")) {
            return new com.stpl.app.NoSuchFederalNewNdcException();
        }

        if (className.equals("com.stpl.app.NoSuchFileManagementException")) {
            return new com.stpl.app.NoSuchFileManagementException();
        }

        if (className.equals("com.stpl.app.NoSuchForecastConfigException")) {
            return new com.stpl.app.NoSuchForecastConfigException();
        }

        if (className.equals("com.stpl.app.NoSuchForecastingFormulaException")) {
            return new com.stpl.app.NoSuchForecastingFormulaException();
        }

        if (className.equals("com.stpl.app.NoSuchForecastingMasterException")) {
            return new com.stpl.app.NoSuchForecastingMasterException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchForecastingViewMasterException")) {
            return new com.stpl.app.NoSuchForecastingViewMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchFormulaDetailsMasterException")) {
            return new com.stpl.app.NoSuchFormulaDetailsMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchGcmCompanyDetailsException")) {
            return new com.stpl.app.NoSuchGcmCompanyDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchGcmCompanyLinkException")) {
            return new com.stpl.app.NoSuchGcmCompanyLinkException();
        }

        if (className.equals("com.stpl.app.NoSuchGcmContractDetailsException")) {
            return new com.stpl.app.NoSuchGcmContractDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchGcmGlobalDetailsException")) {
            return new com.stpl.app.NoSuchGcmGlobalDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchGcmItemDetailsException")) {
            return new com.stpl.app.NoSuchGcmItemDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchGlBalanceMasterException")) {
            return new com.stpl.app.NoSuchGlBalanceMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchGlCostCenterMasterException")) {
            return new com.stpl.app.NoSuchGlCostCenterMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchHelperTableException")) {
            return new com.stpl.app.NoSuchHelperTableException();
        }

        if (className.equals("com.stpl.app.NoSuchHierarchyDefinitionException")) {
            return new com.stpl.app.NoSuchHierarchyDefinitionException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchHierarchyLevelDefinitionException")) {
            return new com.stpl.app.NoSuchHierarchyLevelDefinitionException();
        }

        if (className.equals("com.stpl.app.NoSuchHierarchyLevelValuesException")) {
            return new com.stpl.app.NoSuchHierarchyLevelValuesException();
        }

        if (className.equals("com.stpl.app.NoSuchHistCompanyGroupException")) {
            return new com.stpl.app.NoSuchHistCompanyGroupException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchHistCompanyGroupDetailsException")) {
            return new com.stpl.app.NoSuchHistCompanyGroupDetailsException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchHistHierarchyDefinitionException")) {
            return new com.stpl.app.NoSuchHistHierarchyDefinitionException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchHistHierarchyLevelDefnException")) {
            return new com.stpl.app.NoSuchHistHierarchyLevelDefnException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchHistHierarchyLevelValuesException")) {
            return new com.stpl.app.NoSuchHistHierarchyLevelValuesException();
        }

        if (className.equals("com.stpl.app.NoSuchHistItemGroupException")) {
            return new com.stpl.app.NoSuchHistItemGroupException();
        }

        if (className.equals("com.stpl.app.NoSuchHistItemGroupDetailsException")) {
            return new com.stpl.app.NoSuchHistItemGroupDetailsException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchHistRelationshipBuilderException")) {
            return new com.stpl.app.NoSuchHistRelationshipBuilderException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchHistRelationshipLevelDefnException")) {
            return new com.stpl.app.NoSuchHistRelationshipLevelDefnException();
        }

        if (className.equals("com.stpl.app.NoSuchHistWorkflowMasterException")) {
            return new com.stpl.app.NoSuchHistWorkflowMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchIfpContractException")) {
            return new com.stpl.app.NoSuchIfpContractException();
        }

        if (className.equals("com.stpl.app.NoSuchIfpContractDetailsException")) {
            return new com.stpl.app.NoSuchIfpContractDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchIfpDetailsException")) {
            return new com.stpl.app.NoSuchIfpDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchIfpModelException")) {
            return new com.stpl.app.NoSuchIfpModelException();
        }

        if (className.equals("com.stpl.app.NoSuchImtdCfpDetailsException")) {
            return new com.stpl.app.NoSuchImtdCfpDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchImtdDeductionDetailsException")) {
            return new com.stpl.app.NoSuchImtdDeductionDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchImtdIfpDetailsException")) {
            return new com.stpl.app.NoSuchImtdIfpDetailsException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchImtdItemPriceRebateDetailsException")) {
            return new com.stpl.app.NoSuchImtdItemPriceRebateDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchImtdLevelValuesException")) {
            return new com.stpl.app.NoSuchImtdLevelValuesException();
        }

        if (className.equals("com.stpl.app.NoSuchImtdPsDetailsException")) {
            return new com.stpl.app.NoSuchImtdPsDetailsException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchImtdRsContractDetailsFrException")) {
            return new com.stpl.app.NoSuchImtdRsContractDetailsFrException();
        }

        if (className.equals("com.stpl.app.NoSuchImtdRsDetailsException")) {
            return new com.stpl.app.NoSuchImtdRsDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchImtdRsDetailsFrException")) {
            return new com.stpl.app.NoSuchImtdRsDetailsFrException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchImtdSalesBasisDetailsException")) {
            return new com.stpl.app.NoSuchImtdSalesBasisDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchInventoryWdActualMasException")) {
            return new com.stpl.app.NoSuchInventoryWdActualMasException();
        }

        if (className.equals("com.stpl.app.NoSuchInventoryWdProjMasException")) {
            return new com.stpl.app.NoSuchInventoryWdProjMasException();
        }

        if (className.equals("com.stpl.app.NoSuchItemGroupException")) {
            return new com.stpl.app.NoSuchItemGroupException();
        }

        if (className.equals("com.stpl.app.NoSuchItemGroupDetailsException")) {
            return new com.stpl.app.NoSuchItemGroupDetailsException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchItemHierarchyDefMasterException")) {
            return new com.stpl.app.NoSuchItemHierarchyDefMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchItemHierarchyMasterException")) {
            return new com.stpl.app.NoSuchItemHierarchyMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchItemIdentifierException")) {
            return new com.stpl.app.NoSuchItemIdentifierException();
        }

        if (className.equals("com.stpl.app.NoSuchItemMasterException")) {
            return new com.stpl.app.NoSuchItemMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchItemPricingException")) {
            return new com.stpl.app.NoSuchItemPricingException();
        }

        if (className.equals("com.stpl.app.NoSuchItemPricingQualifierException")) {
            return new com.stpl.app.NoSuchItemPricingQualifierException();
        }

        if (className.equals("com.stpl.app.NoSuchItemQualifierException")) {
            return new com.stpl.app.NoSuchItemQualifierException();
        }

        if (className.equals("com.stpl.app.NoSuchIvldActualMasterException")) {
            return new com.stpl.app.NoSuchIvldActualMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchIvldAverageShelfLifeException")) {
            return new com.stpl.app.NoSuchIvldAverageShelfLifeException();
        }

        if (className.equals("com.stpl.app.NoSuchIvldBestPriceException")) {
            return new com.stpl.app.NoSuchIvldBestPriceException();
        }

        if (className.equals("com.stpl.app.NoSuchIvldCpiException")) {
            return new com.stpl.app.NoSuchIvldCpiException();
        }

        if (className.equals("com.stpl.app.NoSuchIvldDemandActualException")) {
            return new com.stpl.app.NoSuchIvldDemandActualException();
        }

        if (className.equals("com.stpl.app.NoSuchIvldDemandForecastException")) {
            return new com.stpl.app.NoSuchIvldDemandForecastException();
        }

        if (className.equals("com.stpl.app.NoSuchIvldForecastSalesException")) {
            return new com.stpl.app.NoSuchIvldForecastSalesException();
        }

        if (className.equals("com.stpl.app.NoSuchIvldFormulaDetailsException")) {
            return new com.stpl.app.NoSuchIvldFormulaDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchIvldGlBalanceException")) {
            return new com.stpl.app.NoSuchIvldGlBalanceException();
        }

        if (className.equals("com.stpl.app.NoSuchIvldGlCostCenterException")) {
            return new com.stpl.app.NoSuchIvldGlCostCenterException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchIvldInventoryWdActualMasException")) {
            return new com.stpl.app.NoSuchIvldInventoryWdActualMasException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchIvldInventoryWdProjMasException")) {
            return new com.stpl.app.NoSuchIvldInventoryWdProjMasException();
        }

        if (className.equals("com.stpl.app.NoSuchIvldItemHierarchyException")) {
            return new com.stpl.app.NoSuchIvldItemHierarchyException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchIvldItemHierarchyDefinitionException")) {
            return new com.stpl.app.NoSuchIvldItemHierarchyDefinitionException();
        }

        if (className.equals("com.stpl.app.NoSuchIvldLotMasterException")) {
            return new com.stpl.app.NoSuchIvldLotMasterException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchIvldMasterDataAttributeException")) {
            return new com.stpl.app.NoSuchIvldMasterDataAttributeException();
        }

        if (className.equals("com.stpl.app.NoSuchIvldReturnsException")) {
            return new com.stpl.app.NoSuchIvldReturnsException();
        }

        if (className.equals("com.stpl.app.NoSuchIvldSalesMasterException")) {
            return new com.stpl.app.NoSuchIvldSalesMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchLotMasterException")) {
            return new com.stpl.app.NoSuchLotMasterException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchMailNotificationMasterException")) {
            return new com.stpl.app.NoSuchMailNotificationMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchMAssumptionsException")) {
            return new com.stpl.app.NoSuchMAssumptionsException();
        }

        if (className.equals("com.stpl.app.NoSuchMasterDataAttributeException")) {
            return new com.stpl.app.NoSuchMasterDataAttributeException();
        }

        if (className.equals("com.stpl.app.NoSuchMasterDataFilesException")) {
            return new com.stpl.app.NoSuchMasterDataFilesException();
        }

        if (className.equals("com.stpl.app.NoSuchMedicaidNewNdcException")) {
            return new com.stpl.app.NoSuchMedicaidNewNdcException();
        }

        if (className.equals("com.stpl.app.NoSuchMedicaidUraActualsException")) {
            return new com.stpl.app.NoSuchMedicaidUraActualsException();
        }

        if (className.equals("com.stpl.app.NoSuchMedicaidUraProjException")) {
            return new com.stpl.app.NoSuchMedicaidUraProjException();
        }

        if (className.equals("com.stpl.app.NoSuchModulePropertiesException")) {
            return new com.stpl.app.NoSuchModulePropertiesException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchModuleSubmoduleMasterException")) {
            return new com.stpl.app.NoSuchModuleSubmoduleMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchMParityLookupException")) {
            return new com.stpl.app.NoSuchMParityLookupException();
        }

        if (className.equals("com.stpl.app.NoSuchMProjectionSelectionException")) {
            return new com.stpl.app.NoSuchMProjectionSelectionException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchMSalesProjectionMasterException")) {
            return new com.stpl.app.NoSuchMSalesProjectionMasterException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchMSupplementalDiscActualsException")) {
            return new com.stpl.app.NoSuchMSupplementalDiscActualsException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchMSupplementalDiscMasterException")) {
            return new com.stpl.app.NoSuchMSupplementalDiscMasterException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchMSupplementalDiscProjException")) {
            return new com.stpl.app.NoSuchMSupplementalDiscProjException();
        }

        if (className.equals("com.stpl.app.NoSuchNaProjDetailsException")) {
            return new com.stpl.app.NoSuchNaProjDetailsException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchNaProjectionSelectionException")) {
            return new com.stpl.app.NoSuchNaProjectionSelectionException();
        }

        if (className.equals("com.stpl.app.NoSuchNaProjMasterException")) {
            return new com.stpl.app.NoSuchNaProjMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchNationalAssumptionsException")) {
            return new com.stpl.app.NoSuchNationalAssumptionsException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchNationalAssumptionsActualsException")) {
            return new com.stpl.app.NoSuchNationalAssumptionsActualsException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchNationalAssumptionsProjException")) {
            return new com.stpl.app.NoSuchNationalAssumptionsProjException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchNetSalesFormulaMasterException")) {
            return new com.stpl.app.NoSuchNetSalesFormulaMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchNmActualDiscountException")) {
            return new com.stpl.app.NoSuchNmActualDiscountException();
        }

        if (className.equals("com.stpl.app.NoSuchNmActualPpaException")) {
            return new com.stpl.app.NoSuchNmActualPpaException();
        }

        if (className.equals("com.stpl.app.NoSuchNmDiscountProjectionException")) {
            return new com.stpl.app.NoSuchNmDiscountProjectionException();
        }

        if (className.equals("com.stpl.app.NoSuchNmDiscountProjMasterException")) {
            return new com.stpl.app.NoSuchNmDiscountProjMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchNmPpaProjectionException")) {
            return new com.stpl.app.NoSuchNmPpaProjectionException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchNmPpaProjectionMasterException")) {
            return new com.stpl.app.NoSuchNmPpaProjectionMasterException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchNmProjectionSelectionException")) {
            return new com.stpl.app.NoSuchNmProjectionSelectionException();
        }

        if (className.equals("com.stpl.app.NoSuchNmSalesProjectionException")) {
            return new com.stpl.app.NoSuchNmSalesProjectionException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchNmSalesProjectionMasterException")) {
            return new com.stpl.app.NoSuchNmSalesProjectionMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchPeriodException")) {
            return new com.stpl.app.NoSuchPeriodException();
        }

        if (className.equals("com.stpl.app.NoSuchPhsActualsException")) {
            return new com.stpl.app.NoSuchPhsActualsException();
        }

        if (className.equals("com.stpl.app.NoSuchPhsProjException")) {
            return new com.stpl.app.NoSuchPhsProjException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchProjectionCustDetailsException")) {
            return new com.stpl.app.NoSuchProjectionCustDetailsException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchProjectionCustHierarchyException")) {
            return new com.stpl.app.NoSuchProjectionCustHierarchyException();
        }

        if (className.equals("com.stpl.app.NoSuchProjectionDetailsException")) {
            return new com.stpl.app.NoSuchProjectionDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchProjectionMasterException")) {
            return new com.stpl.app.NoSuchProjectionMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchProjectionNameConfigException")) {
            return new com.stpl.app.NoSuchProjectionNameConfigException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchProjectionProdDetailsException")) {
            return new com.stpl.app.NoSuchProjectionProdDetailsException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchProjectionProdHierarchyException")) {
            return new com.stpl.app.NoSuchProjectionProdHierarchyException();
        }

        if (className.equals("com.stpl.app.NoSuchPsContractException")) {
            return new com.stpl.app.NoSuchPsContractException();
        }

        if (className.equals("com.stpl.app.NoSuchPsContractDetailsException")) {
            return new com.stpl.app.NoSuchPsContractDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchPsDetailsException")) {
            return new com.stpl.app.NoSuchPsDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchPsModelException")) {
            return new com.stpl.app.NoSuchPsModelException();
        }

        if (className.equals("com.stpl.app.NoSuchRebatePlanMasterException")) {
            return new com.stpl.app.NoSuchRebatePlanMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchRebatePlanTierException")) {
            return new com.stpl.app.NoSuchRebatePlanTierException();
        }

        if (className.equals("com.stpl.app.NoSuchRebateTierFormulaException")) {
            return new com.stpl.app.NoSuchRebateTierFormulaException();
        }

        if (className.equals("com.stpl.app.NoSuchRelationshipBuilderException")) {
            return new com.stpl.app.NoSuchRelationshipBuilderException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchRelationshipLevelDefinitionException")) {
            return new com.stpl.app.NoSuchRelationshipLevelDefinitionException();
        }

        if (className.equals("com.stpl.app.NoSuchReturnsMasterException")) {
            return new com.stpl.app.NoSuchReturnsMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchRsContractException")) {
            return new com.stpl.app.NoSuchRsContractException();
        }

        if (className.equals("com.stpl.app.NoSuchRsContractDetailsException")) {
            return new com.stpl.app.NoSuchRsContractDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchRsContractDetailsFrException")) {
            return new com.stpl.app.NoSuchRsContractDetailsFrException();
        }

        if (className.equals("com.stpl.app.NoSuchRsDetailsException")) {
            return new com.stpl.app.NoSuchRsDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchRsDetailsFrException")) {
            return new com.stpl.app.NoSuchRsDetailsFrException();
        }

        if (className.equals("com.stpl.app.NoSuchRsModelException")) {
            return new com.stpl.app.NoSuchRsModelException();
        }

        if (className.equals("com.stpl.app.NoSuchSalesBasisDetailsException")) {
            return new com.stpl.app.NoSuchSalesBasisDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchSalesMasterException")) {
            return new com.stpl.app.NoSuchSalesMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchStChActualDiscountException")) {
            return new com.stpl.app.NoSuchStChActualDiscountException();
        }

        if (className.equals("com.stpl.app.NoSuchStChAssumptionsException")) {
            return new com.stpl.app.NoSuchStChAssumptionsException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchStChDiscountProjMasterException")) {
            return new com.stpl.app.NoSuchStChDiscountProjMasterException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchStChProjectionDiscountException")) {
            return new com.stpl.app.NoSuchStChProjectionDiscountException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchStChSalesProjectionMasterException")) {
            return new com.stpl.app.NoSuchStChSalesProjectionMasterException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchStDeductionCalendarDetailsException")) {
            return new com.stpl.app.NoSuchStDeductionCalendarDetailsException();
        }

        if (className.equals("com.stpl.app.NoSuchStFederalNewNdcException")) {
            return new com.stpl.app.NoSuchStFederalNewNdcException();
        }

        if (className.equals("com.stpl.app.NoSuchStMAssumptionsException")) {
            return new com.stpl.app.NoSuchStMAssumptionsException();
        }

        if (className.equals("com.stpl.app.NoSuchStMedicaidNewNdcException")) {
            return new com.stpl.app.NoSuchStMedicaidNewNdcException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchStMSupplementalDiscActualsException")) {
            return new com.stpl.app.NoSuchStMSupplementalDiscActualsException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchStMSupplementalDiscMasterException")) {
            return new com.stpl.app.NoSuchStMSupplementalDiscMasterException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchStMSupplementalDiscProjException")) {
            return new com.stpl.app.NoSuchStMSupplementalDiscProjException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchStNationalAssumptionsException")) {
            return new com.stpl.app.NoSuchStNationalAssumptionsException();
        }

        if (className.equals("com.stpl.app.NoSuchStNewNdcException")) {
            return new com.stpl.app.NoSuchStNewNdcException();
        }

        if (className.equals("com.stpl.app.NoSuchStNmActualDiscountException")) {
            return new com.stpl.app.NoSuchStNmActualDiscountException();
        }

        if (className.equals("com.stpl.app.NoSuchStNmActualPpaException")) {
            return new com.stpl.app.NoSuchStNmActualPpaException();
        }

        if (className.equals("com.stpl.app.NoSuchStNmAssumptionsException")) {
            return new com.stpl.app.NoSuchStNmAssumptionsException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchStNmDiscountProjectionException")) {
            return new com.stpl.app.NoSuchStNmDiscountProjectionException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchStNmDiscountProjMasterException")) {
            return new com.stpl.app.NoSuchStNmDiscountProjMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchStNmPpaProjectionException")) {
            return new com.stpl.app.NoSuchStNmPpaProjectionException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchStNmPpaProjectionMasterException")) {
            return new com.stpl.app.NoSuchStNmPpaProjectionMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchStSelectionTableException")) {
            return new com.stpl.app.NoSuchStSelectionTableException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchTransactionModuleDetailsException")) {
            return new com.stpl.app.NoSuchTransactionModuleDetailsException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchTransactionModuleMasterException")) {
            return new com.stpl.app.NoSuchTransactionModuleMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchUdcsException")) {
            return new com.stpl.app.NoSuchUdcsException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchUsergroupBusinessroleException")) {
            return new com.stpl.app.NoSuchUsergroupBusinessroleException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchUsergroupDomainMasterException")) {
            return new com.stpl.app.NoSuchUsergroupDomainMasterException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchVwDemandForecastActualException")) {
            return new com.stpl.app.NoSuchVwDemandForecastActualException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchVwInventoryWdActualProjMasException")) {
            return new com.stpl.app.NoSuchVwInventoryWdActualProjMasException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchVwIvldDemandForecastActualException")) {
            return new com.stpl.app.NoSuchVwIvldDemandForecastActualException();
        }

        if (className.equals(
                    "com.stpl.app.NoSuchVwIvldInventoryWdActualProjMasException")) {
            return new com.stpl.app.NoSuchVwIvldInventoryWdActualProjMasException();
        }

        if (className.equals("com.stpl.app.NoSuchVwUserTablesException")) {
            return new com.stpl.app.NoSuchVwUserTablesException();
        }

        if (className.equals("com.stpl.app.NoSuchWfMailConfigException")) {
            return new com.stpl.app.NoSuchWfMailConfigException();
        }

        if (className.equals("com.stpl.app.NoSuchWorkflowMasterException")) {
            return new com.stpl.app.NoSuchWorkflowMasterException();
        }

        if (className.equals("com.stpl.app.NoSuchWorkflowProcessInfoException")) {
            return new com.stpl.app.NoSuchWorkflowProcessInfoException();
        }

        if (className.equals("com.stpl.app.NoSuchWorkflowProfileException")) {
            return new com.stpl.app.NoSuchWorkflowProfileException();
        }

        return throwable;
    }

    public static Object translateOutputAccrualDetails(BaseModel<?> oldModel) {
        AccrualDetailsClp newModel = new AccrualDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setAccrualDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputAccrualMaster(BaseModel<?> oldModel) {
        AccrualMasterClp newModel = new AccrualMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setAccrualMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputActualsMaster(BaseModel<?> oldModel) {
        ActualsMasterClp newModel = new ActualsMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setActualsMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputAdditionalNotes(BaseModel<?> oldModel) {
        AdditionalNotesClp newModel = new AdditionalNotesClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setAdditionalNotesRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputAuditMasterInbound(
        BaseModel<?> oldModel) {
        AuditMasterInboundClp newModel = new AuditMasterInboundClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setAuditMasterInboundRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputAverageShelfLifeMaster(
        BaseModel<?> oldModel) {
        AverageShelfLifeMasterClp newModel = new AverageShelfLifeMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setAverageShelfLifeMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputBestPriceMaster(BaseModel<?> oldModel) {
        BestPriceMasterClp newModel = new BestPriceMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setBestPriceMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputBrandMaster(BaseModel<?> oldModel) {
        BrandMasterClp newModel = new BrandMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setBrandMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputBusinessroleMaster(
        BaseModel<?> oldModel) {
        BusinessroleMasterClp newModel = new BusinessroleMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setBusinessroleMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputBusinessroleModule(
        BaseModel<?> oldModel) {
        BusinessroleModuleClp newModel = new BusinessroleModuleClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setBusinessroleModuleRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCcpDetails(BaseModel<?> oldModel) {
        CcpDetailsClp newModel = new CcpDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCcpDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCcpMap(BaseModel<?> oldModel) {
        CcpMapClp newModel = new CcpMapClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCcpMapRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCdrDetails(BaseModel<?> oldModel) {
        CdrDetailsClp newModel = new CdrDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCdrDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCdrModel(BaseModel<?> oldModel) {
        CdrModelClp newModel = new CdrModelClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCdrModelRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCfpContract(BaseModel<?> oldModel) {
        CfpContractClp newModel = new CfpContractClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCfpContractRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCfpContractDetails(
        BaseModel<?> oldModel) {
        CfpContractDetailsClp newModel = new CfpContractDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCfpContractDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCfpDetails(BaseModel<?> oldModel) {
        CfpDetailsClp newModel = new CfpDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCfpDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCfpModel(BaseModel<?> oldModel) {
        CfpModelClp newModel = new CfpModelClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCfpModelRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputChActualDiscount(BaseModel<?> oldModel) {
        ChActualDiscountClp newModel = new ChActualDiscountClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setChActualDiscountRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputChActualSales(BaseModel<?> oldModel) {
        ChActualSalesClp newModel = new ChActualSalesClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setChActualSalesRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputChAssumptions(BaseModel<?> oldModel) {
        ChAssumptionsClp newModel = new ChAssumptionsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setChAssumptionsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputChDiscountProjMaster(
        BaseModel<?> oldModel) {
        ChDiscountProjMasterClp newModel = new ChDiscountProjMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setChDiscountProjMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputChProjectionDiscount(
        BaseModel<?> oldModel) {
        ChProjectionDiscountClp newModel = new ChProjectionDiscountClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setChProjectionDiscountRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputChProjectionSelection(
        BaseModel<?> oldModel) {
        ChProjectionSelectionClp newModel = new ChProjectionSelectionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setChProjectionSelectionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputChSalesProjection(BaseModel<?> oldModel) {
        ChSalesProjectionClp newModel = new ChSalesProjectionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setChSalesProjectionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputChSalesProjectionMaster(
        BaseModel<?> oldModel) {
        ChSalesProjectionMasterClp newModel = new ChSalesProjectionMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setChSalesProjectionMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCompanyGroup(BaseModel<?> oldModel) {
        CompanyGroupClp newModel = new CompanyGroupClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCompanyGroupRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCompanyGroupDetails(
        BaseModel<?> oldModel) {
        CompanyGroupDetailsClp newModel = new CompanyGroupDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCompanyGroupDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCompanyIdentifier(BaseModel<?> oldModel) {
        CompanyIdentifierClp newModel = new CompanyIdentifierClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCompanyIdentifierRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCompanyMaster(BaseModel<?> oldModel) {
        CompanyMasterClp newModel = new CompanyMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCompanyMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCompanyParentDetails(
        BaseModel<?> oldModel) {
        CompanyParentDetailsClp newModel = new CompanyParentDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCompanyParentDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCompanyQualifier(BaseModel<?> oldModel) {
        CompanyQualifierClp newModel = new CompanyQualifierClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCompanyQualifierRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCompanyTradeClass(BaseModel<?> oldModel) {
        CompanyTradeClassClp newModel = new CompanyTradeClassClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCompanyTradeClassRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputContractAliasMaster(
        BaseModel<?> oldModel) {
        ContractAliasMasterClp newModel = new ContractAliasMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setContractAliasMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputContractMaster(BaseModel<?> oldModel) {
        ContractMasterClp newModel = new ContractMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setContractMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCpiIndexMaster(BaseModel<?> oldModel) {
        CpiIndexMasterClp newModel = new CpiIndexMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCpiIndexMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCustomViewDetails(BaseModel<?> oldModel) {
        CustomViewDetailsClp newModel = new CustomViewDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCustomViewDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCustomViewMaster(BaseModel<?> oldModel) {
        CustomViewMasterClp newModel = new CustomViewMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCustomViewMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputDeductionCalendarDetails(
        BaseModel<?> oldModel) {
        DeductionCalendarDetailsClp newModel = new DeductionCalendarDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setDeductionCalendarDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputDeductionCalendarMaster(
        BaseModel<?> oldModel) {
        DeductionCalendarMasterClp newModel = new DeductionCalendarMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setDeductionCalendarMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputDeductionDetails(BaseModel<?> oldModel) {
        DeductionDetailsClp newModel = new DeductionDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setDeductionDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputDeductionGroup(BaseModel<?> oldModel) {
        DeductionGroupClp newModel = new DeductionGroupClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setDeductionGroupRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputDeductionGroupDetails(
        BaseModel<?> oldModel) {
        DeductionGroupDetailsClp newModel = new DeductionGroupDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setDeductionGroupDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputDemandForecast(BaseModel<?> oldModel) {
        DemandForecastClp newModel = new DemandForecastClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setDemandForecastRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputDocDetails(BaseModel<?> oldModel) {
        DocDetailsClp newModel = new DocDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setDocDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputFcpActuals(BaseModel<?> oldModel) {
        FcpActualsClp newModel = new FcpActualsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setFcpActualsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputFcpProj(BaseModel<?> oldModel) {
        FcpProjClp newModel = new FcpProjClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setFcpProjRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputFederalNewNdc(BaseModel<?> oldModel) {
        FederalNewNdcClp newModel = new FederalNewNdcClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setFederalNewNdcRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputFileManagement(BaseModel<?> oldModel) {
        FileManagementClp newModel = new FileManagementClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setFileManagementRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputForecastConfig(BaseModel<?> oldModel) {
        ForecastConfigClp newModel = new ForecastConfigClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setForecastConfigRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputForecastingFormula(
        BaseModel<?> oldModel) {
        ForecastingFormulaClp newModel = new ForecastingFormulaClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setForecastingFormulaRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputForecastingMaster(BaseModel<?> oldModel) {
        ForecastingMasterClp newModel = new ForecastingMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setForecastingMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputForecastingViewMaster(
        BaseModel<?> oldModel) {
        ForecastingViewMasterClp newModel = new ForecastingViewMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setForecastingViewMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputFormulaDetailsMaster(
        BaseModel<?> oldModel) {
        FormulaDetailsMasterClp newModel = new FormulaDetailsMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setFormulaDetailsMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputGcmCompanyDetails(BaseModel<?> oldModel) {
        GcmCompanyDetailsClp newModel = new GcmCompanyDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setGcmCompanyDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputGcmCompanyLink(BaseModel<?> oldModel) {
        GcmCompanyLinkClp newModel = new GcmCompanyLinkClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setGcmCompanyLinkRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputGcmContractDetails(
        BaseModel<?> oldModel) {
        GcmContractDetailsClp newModel = new GcmContractDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setGcmContractDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputGcmGlobalDetails(BaseModel<?> oldModel) {
        GcmGlobalDetailsClp newModel = new GcmGlobalDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setGcmGlobalDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputGcmItemDetails(BaseModel<?> oldModel) {
        GcmItemDetailsClp newModel = new GcmItemDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setGcmItemDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputGlBalanceMaster(BaseModel<?> oldModel) {
        GlBalanceMasterClp newModel = new GlBalanceMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setGlBalanceMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputGlCostCenterMaster(
        BaseModel<?> oldModel) {
        GlCostCenterMasterClp newModel = new GlCostCenterMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setGlCostCenterMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputHelperTable(BaseModel<?> oldModel) {
        HelperTableClp newModel = new HelperTableClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setHelperTableRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputHierarchyDefinition(
        BaseModel<?> oldModel) {
        HierarchyDefinitionClp newModel = new HierarchyDefinitionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setHierarchyDefinitionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputHierarchyLevelDefinition(
        BaseModel<?> oldModel) {
        HierarchyLevelDefinitionClp newModel = new HierarchyLevelDefinitionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setHierarchyLevelDefinitionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputHierarchyLevelValues(
        BaseModel<?> oldModel) {
        HierarchyLevelValuesClp newModel = new HierarchyLevelValuesClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setHierarchyLevelValuesRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputHistCompanyGroup(BaseModel<?> oldModel) {
        HistCompanyGroupClp newModel = new HistCompanyGroupClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setHistCompanyGroupRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputHistCompanyGroupDetails(
        BaseModel<?> oldModel) {
        HistCompanyGroupDetailsClp newModel = new HistCompanyGroupDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setHistCompanyGroupDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputHistHierarchyDefinition(
        BaseModel<?> oldModel) {
        HistHierarchyDefinitionClp newModel = new HistHierarchyDefinitionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setHistHierarchyDefinitionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputHistHierarchyLevelDefn(
        BaseModel<?> oldModel) {
        HistHierarchyLevelDefnClp newModel = new HistHierarchyLevelDefnClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setHistHierarchyLevelDefnRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputHistHierarchyLevelValues(
        BaseModel<?> oldModel) {
        HistHierarchyLevelValuesClp newModel = new HistHierarchyLevelValuesClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setHistHierarchyLevelValuesRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputHistItemGroup(BaseModel<?> oldModel) {
        HistItemGroupClp newModel = new HistItemGroupClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setHistItemGroupRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputHistItemGroupDetails(
        BaseModel<?> oldModel) {
        HistItemGroupDetailsClp newModel = new HistItemGroupDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setHistItemGroupDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputHistRelationshipBuilder(
        BaseModel<?> oldModel) {
        HistRelationshipBuilderClp newModel = new HistRelationshipBuilderClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setHistRelationshipBuilderRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputHistRelationshipLevelDefn(
        BaseModel<?> oldModel) {
        HistRelationshipLevelDefnClp newModel = new HistRelationshipLevelDefnClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setHistRelationshipLevelDefnRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputHistWorkflowMaster(
        BaseModel<?> oldModel) {
        HistWorkflowMasterClp newModel = new HistWorkflowMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setHistWorkflowMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIfpContract(BaseModel<?> oldModel) {
        IfpContractClp newModel = new IfpContractClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIfpContractRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIfpContractDetails(
        BaseModel<?> oldModel) {
        IfpContractDetailsClp newModel = new IfpContractDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIfpContractDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIfpDetails(BaseModel<?> oldModel) {
        IfpDetailsClp newModel = new IfpDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIfpDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIfpModel(BaseModel<?> oldModel) {
        IfpModelClp newModel = new IfpModelClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIfpModelRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputImtdCfpDetails(BaseModel<?> oldModel) {
        ImtdCfpDetailsClp newModel = new ImtdCfpDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setImtdCfpDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputImtdDeductionDetails(
        BaseModel<?> oldModel) {
        ImtdDeductionDetailsClp newModel = new ImtdDeductionDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setImtdDeductionDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputImtdIfpDetails(BaseModel<?> oldModel) {
        ImtdIfpDetailsClp newModel = new ImtdIfpDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setImtdIfpDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputImtdItemPriceRebateDetails(
        BaseModel<?> oldModel) {
        ImtdItemPriceRebateDetailsClp newModel = new ImtdItemPriceRebateDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setImtdItemPriceRebateDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputImtdLevelValues(BaseModel<?> oldModel) {
        ImtdLevelValuesClp newModel = new ImtdLevelValuesClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setImtdLevelValuesRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputImtdPsDetails(BaseModel<?> oldModel) {
        ImtdPsDetailsClp newModel = new ImtdPsDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setImtdPsDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputImtdRsContractDetailsFr(
        BaseModel<?> oldModel) {
        ImtdRsContractDetailsFrClp newModel = new ImtdRsContractDetailsFrClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setImtdRsContractDetailsFrRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputImtdRsDetails(BaseModel<?> oldModel) {
        ImtdRsDetailsClp newModel = new ImtdRsDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setImtdRsDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputImtdRsDetailsFr(BaseModel<?> oldModel) {
        ImtdRsDetailsFrClp newModel = new ImtdRsDetailsFrClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setImtdRsDetailsFrRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputImtdSalesBasisDetails(
        BaseModel<?> oldModel) {
        ImtdSalesBasisDetailsClp newModel = new ImtdSalesBasisDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setImtdSalesBasisDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputInventoryWdActualMas(
        BaseModel<?> oldModel) {
        InventoryWdActualMasClp newModel = new InventoryWdActualMasClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setInventoryWdActualMasRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputInventoryWdProjMas(
        BaseModel<?> oldModel) {
        InventoryWdProjMasClp newModel = new InventoryWdProjMasClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setInventoryWdProjMasRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputItemGroup(BaseModel<?> oldModel) {
        ItemGroupClp newModel = new ItemGroupClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setItemGroupRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputItemGroupDetails(BaseModel<?> oldModel) {
        ItemGroupDetailsClp newModel = new ItemGroupDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setItemGroupDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputItemHierarchyDefMaster(
        BaseModel<?> oldModel) {
        ItemHierarchyDefMasterClp newModel = new ItemHierarchyDefMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setItemHierarchyDefMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputItemHierarchyMaster(
        BaseModel<?> oldModel) {
        ItemHierarchyMasterClp newModel = new ItemHierarchyMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setItemHierarchyMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputItemIdentifier(BaseModel<?> oldModel) {
        ItemIdentifierClp newModel = new ItemIdentifierClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setItemIdentifierRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputItemMaster(BaseModel<?> oldModel) {
        ItemMasterClp newModel = new ItemMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setItemMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputItemPricing(BaseModel<?> oldModel) {
        ItemPricingClp newModel = new ItemPricingClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setItemPricingRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputItemPricingQualifier(
        BaseModel<?> oldModel) {
        ItemPricingQualifierClp newModel = new ItemPricingQualifierClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setItemPricingQualifierRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputItemQualifier(BaseModel<?> oldModel) {
        ItemQualifierClp newModel = new ItemQualifierClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setItemQualifierRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldActualMaster(BaseModel<?> oldModel) {
        IvldActualMasterClp newModel = new IvldActualMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldActualMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldAverageShelfLife(
        BaseModel<?> oldModel) {
        IvldAverageShelfLifeClp newModel = new IvldAverageShelfLifeClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldAverageShelfLifeRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldBestPrice(BaseModel<?> oldModel) {
        IvldBestPriceClp newModel = new IvldBestPriceClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldBestPriceRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldCpi(BaseModel<?> oldModel) {
        IvldCpiClp newModel = new IvldCpiClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldCpiRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldDemandActual(BaseModel<?> oldModel) {
        IvldDemandActualClp newModel = new IvldDemandActualClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldDemandActualRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldDemandForecast(
        BaseModel<?> oldModel) {
        IvldDemandForecastClp newModel = new IvldDemandForecastClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldDemandForecastRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldForecastSales(BaseModel<?> oldModel) {
        IvldForecastSalesClp newModel = new IvldForecastSalesClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldForecastSalesRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldFormulaDetails(
        BaseModel<?> oldModel) {
        IvldFormulaDetailsClp newModel = new IvldFormulaDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldFormulaDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldGlBalance(BaseModel<?> oldModel) {
        IvldGlBalanceClp newModel = new IvldGlBalanceClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldGlBalanceRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldGlCostCenter(BaseModel<?> oldModel) {
        IvldGlCostCenterClp newModel = new IvldGlCostCenterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldGlCostCenterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldInventoryWdActualMas(
        BaseModel<?> oldModel) {
        IvldInventoryWdActualMasClp newModel = new IvldInventoryWdActualMasClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldInventoryWdActualMasRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldInventoryWdProjMas(
        BaseModel<?> oldModel) {
        IvldInventoryWdProjMasClp newModel = new IvldInventoryWdProjMasClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldInventoryWdProjMasRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldItemHierarchy(BaseModel<?> oldModel) {
        IvldItemHierarchyClp newModel = new IvldItemHierarchyClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldItemHierarchyRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldItemHierarchyDefinition(
        BaseModel<?> oldModel) {
        IvldItemHierarchyDefinitionClp newModel = new IvldItemHierarchyDefinitionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldItemHierarchyDefinitionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldLotMaster(BaseModel<?> oldModel) {
        IvldLotMasterClp newModel = new IvldLotMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldLotMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldMasterDataAttribute(
        BaseModel<?> oldModel) {
        IvldMasterDataAttributeClp newModel = new IvldMasterDataAttributeClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldMasterDataAttributeRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldReturns(BaseModel<?> oldModel) {
        IvldReturnsClp newModel = new IvldReturnsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldReturnsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldSalesMaster(BaseModel<?> oldModel) {
        IvldSalesMasterClp newModel = new IvldSalesMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldSalesMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputLotMaster(BaseModel<?> oldModel) {
        LotMasterClp newModel = new LotMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setLotMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMailNotificationMaster(
        BaseModel<?> oldModel) {
        MailNotificationMasterClp newModel = new MailNotificationMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMailNotificationMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMAssumptions(BaseModel<?> oldModel) {
        MAssumptionsClp newModel = new MAssumptionsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMAssumptionsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMasterDataAttribute(
        BaseModel<?> oldModel) {
        MasterDataAttributeClp newModel = new MasterDataAttributeClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMasterDataAttributeRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMasterDataFiles(BaseModel<?> oldModel) {
        MasterDataFilesClp newModel = new MasterDataFilesClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMasterDataFilesRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMedicaidNewNdc(BaseModel<?> oldModel) {
        MedicaidNewNdcClp newModel = new MedicaidNewNdcClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMedicaidNewNdcRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMedicaidUraActuals(
        BaseModel<?> oldModel) {
        MedicaidUraActualsClp newModel = new MedicaidUraActualsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMedicaidUraActualsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMedicaidUraProj(BaseModel<?> oldModel) {
        MedicaidUraProjClp newModel = new MedicaidUraProjClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMedicaidUraProjRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputModuleProperties(BaseModel<?> oldModel) {
        ModulePropertiesClp newModel = new ModulePropertiesClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setModulePropertiesRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputModuleSubmoduleMaster(
        BaseModel<?> oldModel) {
        ModuleSubmoduleMasterClp newModel = new ModuleSubmoduleMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setModuleSubmoduleMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMParityLookup(BaseModel<?> oldModel) {
        MParityLookupClp newModel = new MParityLookupClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMParityLookupRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMProjectionSelection(
        BaseModel<?> oldModel) {
        MProjectionSelectionClp newModel = new MProjectionSelectionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMProjectionSelectionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMSalesProjectionMaster(
        BaseModel<?> oldModel) {
        MSalesProjectionMasterClp newModel = new MSalesProjectionMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMSalesProjectionMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMSupplementalDiscActuals(
        BaseModel<?> oldModel) {
        MSupplementalDiscActualsClp newModel = new MSupplementalDiscActualsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMSupplementalDiscActualsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMSupplementalDiscMaster(
        BaseModel<?> oldModel) {
        MSupplementalDiscMasterClp newModel = new MSupplementalDiscMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMSupplementalDiscMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMSupplementalDiscProj(
        BaseModel<?> oldModel) {
        MSupplementalDiscProjClp newModel = new MSupplementalDiscProjClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMSupplementalDiscProjRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputNaProjDetails(BaseModel<?> oldModel) {
        NaProjDetailsClp newModel = new NaProjDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setNaProjDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputNaProjectionSelection(
        BaseModel<?> oldModel) {
        NaProjectionSelectionClp newModel = new NaProjectionSelectionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setNaProjectionSelectionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputNaProjMaster(BaseModel<?> oldModel) {
        NaProjMasterClp newModel = new NaProjMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setNaProjMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputNationalAssumptions(
        BaseModel<?> oldModel) {
        NationalAssumptionsClp newModel = new NationalAssumptionsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setNationalAssumptionsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputNationalAssumptionsActuals(
        BaseModel<?> oldModel) {
        NationalAssumptionsActualsClp newModel = new NationalAssumptionsActualsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setNationalAssumptionsActualsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputNationalAssumptionsProj(
        BaseModel<?> oldModel) {
        NationalAssumptionsProjClp newModel = new NationalAssumptionsProjClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setNationalAssumptionsProjRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputNetSalesFormulaMaster(
        BaseModel<?> oldModel) {
        NetSalesFormulaMasterClp newModel = new NetSalesFormulaMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setNetSalesFormulaMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputNmActualDiscount(BaseModel<?> oldModel) {
        NmActualDiscountClp newModel = new NmActualDiscountClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setNmActualDiscountRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputNmActualPpa(BaseModel<?> oldModel) {
        NmActualPpaClp newModel = new NmActualPpaClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setNmActualPpaRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputNmDiscountProjection(
        BaseModel<?> oldModel) {
        NmDiscountProjectionClp newModel = new NmDiscountProjectionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setNmDiscountProjectionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputNmDiscountProjMaster(
        BaseModel<?> oldModel) {
        NmDiscountProjMasterClp newModel = new NmDiscountProjMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setNmDiscountProjMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputNmPpaProjection(BaseModel<?> oldModel) {
        NmPpaProjectionClp newModel = new NmPpaProjectionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setNmPpaProjectionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputNmPpaProjectionMaster(
        BaseModel<?> oldModel) {
        NmPpaProjectionMasterClp newModel = new NmPpaProjectionMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setNmPpaProjectionMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputNmProjectionSelection(
        BaseModel<?> oldModel) {
        NmProjectionSelectionClp newModel = new NmProjectionSelectionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setNmProjectionSelectionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputNmSalesProjection(BaseModel<?> oldModel) {
        NmSalesProjectionClp newModel = new NmSalesProjectionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setNmSalesProjectionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputNmSalesProjectionMaster(
        BaseModel<?> oldModel) {
        NmSalesProjectionMasterClp newModel = new NmSalesProjectionMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setNmSalesProjectionMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPeriod(BaseModel<?> oldModel) {
        PeriodClp newModel = new PeriodClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPeriodRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPhsActuals(BaseModel<?> oldModel) {
        PhsActualsClp newModel = new PhsActualsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPhsActualsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPhsProj(BaseModel<?> oldModel) {
        PhsProjClp newModel = new PhsProjClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPhsProjRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProjectionCustDetails(
        BaseModel<?> oldModel) {
        ProjectionCustDetailsClp newModel = new ProjectionCustDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProjectionCustDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProjectionCustHierarchy(
        BaseModel<?> oldModel) {
        ProjectionCustHierarchyClp newModel = new ProjectionCustHierarchyClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProjectionCustHierarchyRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProjectionDetails(BaseModel<?> oldModel) {
        ProjectionDetailsClp newModel = new ProjectionDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProjectionDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProjectionMaster(BaseModel<?> oldModel) {
        ProjectionMasterClp newModel = new ProjectionMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProjectionMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProjectionNameConfig(
        BaseModel<?> oldModel) {
        ProjectionNameConfigClp newModel = new ProjectionNameConfigClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProjectionNameConfigRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProjectionProdDetails(
        BaseModel<?> oldModel) {
        ProjectionProdDetailsClp newModel = new ProjectionProdDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProjectionProdDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProjectionProdHierarchy(
        BaseModel<?> oldModel) {
        ProjectionProdHierarchyClp newModel = new ProjectionProdHierarchyClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProjectionProdHierarchyRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPsContract(BaseModel<?> oldModel) {
        PsContractClp newModel = new PsContractClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPsContractRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPsContractDetails(BaseModel<?> oldModel) {
        PsContractDetailsClp newModel = new PsContractDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPsContractDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPsDetails(BaseModel<?> oldModel) {
        PsDetailsClp newModel = new PsDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPsDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPsModel(BaseModel<?> oldModel) {
        PsModelClp newModel = new PsModelClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPsModelRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputRebatePlanMaster(BaseModel<?> oldModel) {
        RebatePlanMasterClp newModel = new RebatePlanMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setRebatePlanMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputRebatePlanTier(BaseModel<?> oldModel) {
        RebatePlanTierClp newModel = new RebatePlanTierClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setRebatePlanTierRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputRebateTierFormula(BaseModel<?> oldModel) {
        RebateTierFormulaClp newModel = new RebateTierFormulaClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setRebateTierFormulaRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputRelationshipBuilder(
        BaseModel<?> oldModel) {
        RelationshipBuilderClp newModel = new RelationshipBuilderClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setRelationshipBuilderRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputRelationshipLevelDefinition(
        BaseModel<?> oldModel) {
        RelationshipLevelDefinitionClp newModel = new RelationshipLevelDefinitionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setRelationshipLevelDefinitionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputReturnsMaster(BaseModel<?> oldModel) {
        ReturnsMasterClp newModel = new ReturnsMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setReturnsMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputRsContract(BaseModel<?> oldModel) {
        RsContractClp newModel = new RsContractClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setRsContractRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputRsContractDetails(BaseModel<?> oldModel) {
        RsContractDetailsClp newModel = new RsContractDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setRsContractDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputRsContractDetailsFr(
        BaseModel<?> oldModel) {
        RsContractDetailsFrClp newModel = new RsContractDetailsFrClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setRsContractDetailsFrRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputRsDetails(BaseModel<?> oldModel) {
        RsDetailsClp newModel = new RsDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setRsDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputRsDetailsFr(BaseModel<?> oldModel) {
        RsDetailsFrClp newModel = new RsDetailsFrClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setRsDetailsFrRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputRsModel(BaseModel<?> oldModel) {
        RsModelClp newModel = new RsModelClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setRsModelRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputSalesBasisDetails(BaseModel<?> oldModel) {
        SalesBasisDetailsClp newModel = new SalesBasisDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setSalesBasisDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputSalesMaster(BaseModel<?> oldModel) {
        SalesMasterClp newModel = new SalesMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setSalesMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStChActualDiscount(
        BaseModel<?> oldModel) {
        StChActualDiscountClp newModel = new StChActualDiscountClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStChActualDiscountRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStChAssumptions(BaseModel<?> oldModel) {
        StChAssumptionsClp newModel = new StChAssumptionsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStChAssumptionsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStChDiscountProjMaster(
        BaseModel<?> oldModel) {
        StChDiscountProjMasterClp newModel = new StChDiscountProjMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStChDiscountProjMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStChProjectionDiscount(
        BaseModel<?> oldModel) {
        StChProjectionDiscountClp newModel = new StChProjectionDiscountClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStChProjectionDiscountRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStChSalesProjectionMaster(
        BaseModel<?> oldModel) {
        StChSalesProjectionMasterClp newModel = new StChSalesProjectionMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStChSalesProjectionMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStDeductionCalendarDetails(
        BaseModel<?> oldModel) {
        StDeductionCalendarDetailsClp newModel = new StDeductionCalendarDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStDeductionCalendarDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStFederalNewNdc(BaseModel<?> oldModel) {
        StFederalNewNdcClp newModel = new StFederalNewNdcClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStFederalNewNdcRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStMAssumptions(BaseModel<?> oldModel) {
        StMAssumptionsClp newModel = new StMAssumptionsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStMAssumptionsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStMedicaidNewNdc(BaseModel<?> oldModel) {
        StMedicaidNewNdcClp newModel = new StMedicaidNewNdcClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStMedicaidNewNdcRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStMSupplementalDiscActuals(
        BaseModel<?> oldModel) {
        StMSupplementalDiscActualsClp newModel = new StMSupplementalDiscActualsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStMSupplementalDiscActualsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStMSupplementalDiscMaster(
        BaseModel<?> oldModel) {
        StMSupplementalDiscMasterClp newModel = new StMSupplementalDiscMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStMSupplementalDiscMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStMSupplementalDiscProj(
        BaseModel<?> oldModel) {
        StMSupplementalDiscProjClp newModel = new StMSupplementalDiscProjClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStMSupplementalDiscProjRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStNationalAssumptions(
        BaseModel<?> oldModel) {
        StNationalAssumptionsClp newModel = new StNationalAssumptionsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStNationalAssumptionsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStNewNdc(BaseModel<?> oldModel) {
        StNewNdcClp newModel = new StNewNdcClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStNewNdcRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStNmActualDiscount(
        BaseModel<?> oldModel) {
        StNmActualDiscountClp newModel = new StNmActualDiscountClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStNmActualDiscountRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStNmActualPpa(BaseModel<?> oldModel) {
        StNmActualPpaClp newModel = new StNmActualPpaClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStNmActualPpaRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStNmAssumptions(BaseModel<?> oldModel) {
        StNmAssumptionsClp newModel = new StNmAssumptionsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStNmAssumptionsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStNmDiscountProjection(
        BaseModel<?> oldModel) {
        StNmDiscountProjectionClp newModel = new StNmDiscountProjectionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStNmDiscountProjectionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStNmDiscountProjMaster(
        BaseModel<?> oldModel) {
        StNmDiscountProjMasterClp newModel = new StNmDiscountProjMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStNmDiscountProjMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStNmPpaProjection(BaseModel<?> oldModel) {
        StNmPpaProjectionClp newModel = new StNmPpaProjectionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStNmPpaProjectionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStNmPpaProjectionMaster(
        BaseModel<?> oldModel) {
        StNmPpaProjectionMasterClp newModel = new StNmPpaProjectionMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStNmPpaProjectionMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStSelectionTable(BaseModel<?> oldModel) {
        StSelectionTableClp newModel = new StSelectionTableClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStSelectionTableRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputTransactionModuleDetails(
        BaseModel<?> oldModel) {
        TransactionModuleDetailsClp newModel = new TransactionModuleDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setTransactionModuleDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputTransactionModuleMaster(
        BaseModel<?> oldModel) {
        TransactionModuleMasterClp newModel = new TransactionModuleMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setTransactionModuleMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputUdcs(BaseModel<?> oldModel) {
        UdcsClp newModel = new UdcsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setUdcsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputUsergroupBusinessrole(
        BaseModel<?> oldModel) {
        UsergroupBusinessroleClp newModel = new UsergroupBusinessroleClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setUsergroupBusinessroleRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputUsergroupDomainMaster(
        BaseModel<?> oldModel) {
        UsergroupDomainMasterClp newModel = new UsergroupDomainMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setUsergroupDomainMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputVwDemandForecastActual(
        BaseModel<?> oldModel) {
        VwDemandForecastActualClp newModel = new VwDemandForecastActualClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setVwDemandForecastActualRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputVwInventoryWdActualProjMas(
        BaseModel<?> oldModel) {
        VwInventoryWdActualProjMasClp newModel = new VwInventoryWdActualProjMasClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setVwInventoryWdActualProjMasRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputVwIvldDemandForecastActual(
        BaseModel<?> oldModel) {
        VwIvldDemandForecastActualClp newModel = new VwIvldDemandForecastActualClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setVwIvldDemandForecastActualRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputVwIvldInventoryWdActualProjMas(
        BaseModel<?> oldModel) {
        VwIvldInventoryWdActualProjMasClp newModel = new VwIvldInventoryWdActualProjMasClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setVwIvldInventoryWdActualProjMasRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputVwUserTables(BaseModel<?> oldModel) {
        VwUserTablesClp newModel = new VwUserTablesClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setVwUserTablesRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputWfMailConfig(BaseModel<?> oldModel) {
        WfMailConfigClp newModel = new WfMailConfigClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setWfMailConfigRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputWorkflowMaster(BaseModel<?> oldModel) {
        WorkflowMasterClp newModel = new WorkflowMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setWorkflowMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputWorkflowProcessInfo(
        BaseModel<?> oldModel) {
        WorkflowProcessInfoClp newModel = new WorkflowProcessInfoClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setWorkflowProcessInfoRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputWorkflowProfile(BaseModel<?> oldModel) {
        WorkflowProfileClp newModel = new WorkflowProfileClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setWorkflowProfileRemoteModel(oldModel);

        return newModel;
    }
}
