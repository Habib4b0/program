package com.stpl.app.parttwo.service;

import com.stpl.app.parttwo.model.AcBaseRateBaselineDetailsClp;
import com.stpl.app.parttwo.model.AcBrMethodologyDetailsClp;
import com.stpl.app.parttwo.model.AcFdAdjustmentSelectionClp;
import com.stpl.app.parttwo.model.AccClosureDetailsClp;
import com.stpl.app.parttwo.model.AccClosureMasterClp;
import com.stpl.app.parttwo.model.AccClosureViewMasterClp;
import com.stpl.app.parttwo.model.AdjustedDemandForecastClp;
import com.stpl.app.parttwo.model.AhTempDetailsClp;
import com.stpl.app.parttwo.model.ArpOutboundClp;
import com.stpl.app.parttwo.model.CffAdditionalInfoClp;
import com.stpl.app.parttwo.model.CffApprovalDetailsClp;
import com.stpl.app.parttwo.model.CffCustHierarchyClp;
import com.stpl.app.parttwo.model.CffCustomViewDetailsClp;
import com.stpl.app.parttwo.model.CffCustomViewMasterClp;
import com.stpl.app.parttwo.model.CffDetailsClp;
import com.stpl.app.parttwo.model.CffDocDetailsClp;
import com.stpl.app.parttwo.model.CffMasterClp;
import com.stpl.app.parttwo.model.CffProdHierarchyClp;
import com.stpl.app.parttwo.model.CffViewMasterClp;
import com.stpl.app.parttwo.model.CustomerGtsActualClp;
import com.stpl.app.parttwo.model.CustomerGtsForecastClp;
import com.stpl.app.parttwo.model.IvldAccrualInboundClp;
import com.stpl.app.parttwo.model.IvldCompanyIdentifierClp;
import com.stpl.app.parttwo.model.IvldCompanyMasterClp;
import com.stpl.app.parttwo.model.IvldCompanyParentClp;
import com.stpl.app.parttwo.model.IvldCompanyTradeClassClp;
import com.stpl.app.parttwo.model.IvldCustomerGtsActualClp;
import com.stpl.app.parttwo.model.IvldCustomerGtsForecastClp;
import com.stpl.app.parttwo.model.IvldItemIdentifierClp;
import com.stpl.app.parttwo.model.IvldItemMasterClp;
import com.stpl.app.parttwo.model.IvldItemPricingClp;
import com.stpl.app.parttwo.model.SlaCalendarDetailsClp;
import com.stpl.app.parttwo.model.SlaCalendarMasterClp;
import com.stpl.app.parttwo.model.StAccClosureDetailsClp;
import com.stpl.app.parttwo.model.StAdjustmentGtnDetailClp;
import com.stpl.app.parttwo.model.StAdjustmentReserveDetailClp;
import com.stpl.app.parttwo.model.StArpOutboundClp;
import com.stpl.app.parttwo.model.StCffOutboundMasterClp;
import com.stpl.app.parttwo.model.VwAdjustDemandForecastActClp;
import com.stpl.app.parttwo.model.VwCompanyIdentifierClp;
import com.stpl.app.parttwo.model.VwCompanyMasterClp;
import com.stpl.app.parttwo.model.VwCompanyParentDetailsClp;
import com.stpl.app.parttwo.model.VwCompanyTradeClassClp;
import com.stpl.app.parttwo.model.VwCustomerGtsForecastClp;
import com.stpl.app.parttwo.model.VwItemIdentifierClp;
import com.stpl.app.parttwo.model.VwItemMasterClp;
import com.stpl.app.parttwo.model.VwItemPricingClp;
import com.stpl.app.parttwo.model.VwIvldAdjDemandForeActualClp;
import com.stpl.app.parttwo.model.VwIvldReturnReserveClp;
import com.stpl.app.parttwo.model.VwReturnReserveClp;

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
                        "gtnPartII-deployment-context");

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
                            "gtnPartII-deployment-context");

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
                _servletContextName = "gtnPartII";
            }

            return _servletContextName;
        }
    }

    public static Object translateInput(BaseModel<?> oldModel) {
        Class<?> oldModelClass = oldModel.getClass();

        String oldModelClassName = oldModelClass.getName();

        if (oldModelClassName.equals(
                    AcBaseRateBaselineDetailsClp.class.getName())) {
            return translateInputAcBaseRateBaselineDetails(oldModel);
        }

        if (oldModelClassName.equals(AcBrMethodologyDetailsClp.class.getName())) {
            return translateInputAcBrMethodologyDetails(oldModel);
        }

        if (oldModelClassName.equals(AccClosureDetailsClp.class.getName())) {
            return translateInputAccClosureDetails(oldModel);
        }

        if (oldModelClassName.equals(AccClosureMasterClp.class.getName())) {
            return translateInputAccClosureMaster(oldModel);
        }

        if (oldModelClassName.equals(AccClosureViewMasterClp.class.getName())) {
            return translateInputAccClosureViewMaster(oldModel);
        }

        if (oldModelClassName.equals(AcFdAdjustmentSelectionClp.class.getName())) {
            return translateInputAcFdAdjustmentSelection(oldModel);
        }

        if (oldModelClassName.equals(AdjustedDemandForecastClp.class.getName())) {
            return translateInputAdjustedDemandForecast(oldModel);
        }

        if (oldModelClassName.equals(AhTempDetailsClp.class.getName())) {
            return translateInputAhTempDetails(oldModel);
        }

        if (oldModelClassName.equals(ArpOutboundClp.class.getName())) {
            return translateInputArpOutbound(oldModel);
        }

        if (oldModelClassName.equals(CffAdditionalInfoClp.class.getName())) {
            return translateInputCffAdditionalInfo(oldModel);
        }

        if (oldModelClassName.equals(CffApprovalDetailsClp.class.getName())) {
            return translateInputCffApprovalDetails(oldModel);
        }

        if (oldModelClassName.equals(CffCustHierarchyClp.class.getName())) {
            return translateInputCffCustHierarchy(oldModel);
        }

        if (oldModelClassName.equals(CffCustomViewDetailsClp.class.getName())) {
            return translateInputCffCustomViewDetails(oldModel);
        }

        if (oldModelClassName.equals(CffCustomViewMasterClp.class.getName())) {
            return translateInputCffCustomViewMaster(oldModel);
        }

        if (oldModelClassName.equals(CffDetailsClp.class.getName())) {
            return translateInputCffDetails(oldModel);
        }

        if (oldModelClassName.equals(CffDocDetailsClp.class.getName())) {
            return translateInputCffDocDetails(oldModel);
        }

        if (oldModelClassName.equals(CffMasterClp.class.getName())) {
            return translateInputCffMaster(oldModel);
        }

        if (oldModelClassName.equals(CffProdHierarchyClp.class.getName())) {
            return translateInputCffProdHierarchy(oldModel);
        }

        if (oldModelClassName.equals(CffViewMasterClp.class.getName())) {
            return translateInputCffViewMaster(oldModel);
        }

        if (oldModelClassName.equals(CustomerGtsActualClp.class.getName())) {
            return translateInputCustomerGtsActual(oldModel);
        }

        if (oldModelClassName.equals(CustomerGtsForecastClp.class.getName())) {
            return translateInputCustomerGtsForecast(oldModel);
        }

        if (oldModelClassName.equals(IvldAccrualInboundClp.class.getName())) {
            return translateInputIvldAccrualInbound(oldModel);
        }

        if (oldModelClassName.equals(IvldCompanyIdentifierClp.class.getName())) {
            return translateInputIvldCompanyIdentifier(oldModel);
        }

        if (oldModelClassName.equals(IvldCompanyMasterClp.class.getName())) {
            return translateInputIvldCompanyMaster(oldModel);
        }

        if (oldModelClassName.equals(IvldCompanyParentClp.class.getName())) {
            return translateInputIvldCompanyParent(oldModel);
        }

        if (oldModelClassName.equals(IvldCompanyTradeClassClp.class.getName())) {
            return translateInputIvldCompanyTradeClass(oldModel);
        }

        if (oldModelClassName.equals(IvldCustomerGtsActualClp.class.getName())) {
            return translateInputIvldCustomerGtsActual(oldModel);
        }

        if (oldModelClassName.equals(IvldCustomerGtsForecastClp.class.getName())) {
            return translateInputIvldCustomerGtsForecast(oldModel);
        }

        if (oldModelClassName.equals(IvldItemIdentifierClp.class.getName())) {
            return translateInputIvldItemIdentifier(oldModel);
        }

        if (oldModelClassName.equals(IvldItemMasterClp.class.getName())) {
            return translateInputIvldItemMaster(oldModel);
        }

        if (oldModelClassName.equals(IvldItemPricingClp.class.getName())) {
            return translateInputIvldItemPricing(oldModel);
        }

        if (oldModelClassName.equals(SlaCalendarDetailsClp.class.getName())) {
            return translateInputSlaCalendarDetails(oldModel);
        }

        if (oldModelClassName.equals(SlaCalendarMasterClp.class.getName())) {
            return translateInputSlaCalendarMaster(oldModel);
        }

        if (oldModelClassName.equals(StAccClosureDetailsClp.class.getName())) {
            return translateInputStAccClosureDetails(oldModel);
        }

        if (oldModelClassName.equals(StAdjustmentGtnDetailClp.class.getName())) {
            return translateInputStAdjustmentGtnDetail(oldModel);
        }

        if (oldModelClassName.equals(
                    StAdjustmentReserveDetailClp.class.getName())) {
            return translateInputStAdjustmentReserveDetail(oldModel);
        }

        if (oldModelClassName.equals(StArpOutboundClp.class.getName())) {
            return translateInputStArpOutbound(oldModel);
        }

        if (oldModelClassName.equals(StCffOutboundMasterClp.class.getName())) {
            return translateInputStCffOutboundMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    VwAdjustDemandForecastActClp.class.getName())) {
            return translateInputVwAdjustDemandForecastAct(oldModel);
        }

        if (oldModelClassName.equals(VwCompanyIdentifierClp.class.getName())) {
            return translateInputVwCompanyIdentifier(oldModel);
        }

        if (oldModelClassName.equals(VwCompanyMasterClp.class.getName())) {
            return translateInputVwCompanyMaster(oldModel);
        }

        if (oldModelClassName.equals(VwCompanyParentDetailsClp.class.getName())) {
            return translateInputVwCompanyParentDetails(oldModel);
        }

        if (oldModelClassName.equals(VwCompanyTradeClassClp.class.getName())) {
            return translateInputVwCompanyTradeClass(oldModel);
        }

        if (oldModelClassName.equals(VwCustomerGtsForecastClp.class.getName())) {
            return translateInputVwCustomerGtsForecast(oldModel);
        }

        if (oldModelClassName.equals(VwItemIdentifierClp.class.getName())) {
            return translateInputVwItemIdentifier(oldModel);
        }

        if (oldModelClassName.equals(VwItemMasterClp.class.getName())) {
            return translateInputVwItemMaster(oldModel);
        }

        if (oldModelClassName.equals(VwItemPricingClp.class.getName())) {
            return translateInputVwItemPricing(oldModel);
        }

        if (oldModelClassName.equals(
                    VwIvldAdjDemandForeActualClp.class.getName())) {
            return translateInputVwIvldAdjDemandForeActual(oldModel);
        }

        if (oldModelClassName.equals(VwIvldReturnReserveClp.class.getName())) {
            return translateInputVwIvldReturnReserve(oldModel);
        }

        if (oldModelClassName.equals(VwReturnReserveClp.class.getName())) {
            return translateInputVwReturnReserve(oldModel);
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

    public static Object translateInputAcBaseRateBaselineDetails(
        BaseModel<?> oldModel) {
        AcBaseRateBaselineDetailsClp oldClpModel = (AcBaseRateBaselineDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getAcBaseRateBaselineDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputAcBrMethodologyDetails(
        BaseModel<?> oldModel) {
        AcBrMethodologyDetailsClp oldClpModel = (AcBrMethodologyDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getAcBrMethodologyDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputAccClosureDetails(BaseModel<?> oldModel) {
        AccClosureDetailsClp oldClpModel = (AccClosureDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getAccClosureDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputAccClosureMaster(BaseModel<?> oldModel) {
        AccClosureMasterClp oldClpModel = (AccClosureMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getAccClosureMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputAccClosureViewMaster(
        BaseModel<?> oldModel) {
        AccClosureViewMasterClp oldClpModel = (AccClosureViewMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getAccClosureViewMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputAcFdAdjustmentSelection(
        BaseModel<?> oldModel) {
        AcFdAdjustmentSelectionClp oldClpModel = (AcFdAdjustmentSelectionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getAcFdAdjustmentSelectionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputAdjustedDemandForecast(
        BaseModel<?> oldModel) {
        AdjustedDemandForecastClp oldClpModel = (AdjustedDemandForecastClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getAdjustedDemandForecastRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputAhTempDetails(BaseModel<?> oldModel) {
        AhTempDetailsClp oldClpModel = (AhTempDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getAhTempDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputArpOutbound(BaseModel<?> oldModel) {
        ArpOutboundClp oldClpModel = (ArpOutboundClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getArpOutboundRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCffAdditionalInfo(BaseModel<?> oldModel) {
        CffAdditionalInfoClp oldClpModel = (CffAdditionalInfoClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCffAdditionalInfoRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCffApprovalDetails(BaseModel<?> oldModel) {
        CffApprovalDetailsClp oldClpModel = (CffApprovalDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCffApprovalDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCffCustHierarchy(BaseModel<?> oldModel) {
        CffCustHierarchyClp oldClpModel = (CffCustHierarchyClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCffCustHierarchyRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCffCustomViewDetails(
        BaseModel<?> oldModel) {
        CffCustomViewDetailsClp oldClpModel = (CffCustomViewDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCffCustomViewDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCffCustomViewMaster(
        BaseModel<?> oldModel) {
        CffCustomViewMasterClp oldClpModel = (CffCustomViewMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCffCustomViewMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCffDetails(BaseModel<?> oldModel) {
        CffDetailsClp oldClpModel = (CffDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCffDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCffDocDetails(BaseModel<?> oldModel) {
        CffDocDetailsClp oldClpModel = (CffDocDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCffDocDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCffMaster(BaseModel<?> oldModel) {
        CffMasterClp oldClpModel = (CffMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCffMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCffProdHierarchy(BaseModel<?> oldModel) {
        CffProdHierarchyClp oldClpModel = (CffProdHierarchyClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCffProdHierarchyRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCffViewMaster(BaseModel<?> oldModel) {
        CffViewMasterClp oldClpModel = (CffViewMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCffViewMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCustomerGtsActual(BaseModel<?> oldModel) {
        CustomerGtsActualClp oldClpModel = (CustomerGtsActualClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCustomerGtsActualRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputCustomerGtsForecast(
        BaseModel<?> oldModel) {
        CustomerGtsForecastClp oldClpModel = (CustomerGtsForecastClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getCustomerGtsForecastRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldAccrualInbound(BaseModel<?> oldModel) {
        IvldAccrualInboundClp oldClpModel = (IvldAccrualInboundClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldAccrualInboundRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldCompanyIdentifier(
        BaseModel<?> oldModel) {
        IvldCompanyIdentifierClp oldClpModel = (IvldCompanyIdentifierClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldCompanyIdentifierRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldCompanyMaster(BaseModel<?> oldModel) {
        IvldCompanyMasterClp oldClpModel = (IvldCompanyMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldCompanyMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldCompanyParent(BaseModel<?> oldModel) {
        IvldCompanyParentClp oldClpModel = (IvldCompanyParentClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldCompanyParentRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldCompanyTradeClass(
        BaseModel<?> oldModel) {
        IvldCompanyTradeClassClp oldClpModel = (IvldCompanyTradeClassClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldCompanyTradeClassRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldCustomerGtsActual(
        BaseModel<?> oldModel) {
        IvldCustomerGtsActualClp oldClpModel = (IvldCustomerGtsActualClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldCustomerGtsActualRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldCustomerGtsForecast(
        BaseModel<?> oldModel) {
        IvldCustomerGtsForecastClp oldClpModel = (IvldCustomerGtsForecastClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldCustomerGtsForecastRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldItemIdentifier(BaseModel<?> oldModel) {
        IvldItemIdentifierClp oldClpModel = (IvldItemIdentifierClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldItemIdentifierRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldItemMaster(BaseModel<?> oldModel) {
        IvldItemMasterClp oldClpModel = (IvldItemMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldItemMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIvldItemPricing(BaseModel<?> oldModel) {
        IvldItemPricingClp oldClpModel = (IvldItemPricingClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIvldItemPricingRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputSlaCalendarDetails(BaseModel<?> oldModel) {
        SlaCalendarDetailsClp oldClpModel = (SlaCalendarDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getSlaCalendarDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputSlaCalendarMaster(BaseModel<?> oldModel) {
        SlaCalendarMasterClp oldClpModel = (SlaCalendarMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getSlaCalendarMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStAccClosureDetails(
        BaseModel<?> oldModel) {
        StAccClosureDetailsClp oldClpModel = (StAccClosureDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStAccClosureDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStAdjustmentGtnDetail(
        BaseModel<?> oldModel) {
        StAdjustmentGtnDetailClp oldClpModel = (StAdjustmentGtnDetailClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStAdjustmentGtnDetailRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStAdjustmentReserveDetail(
        BaseModel<?> oldModel) {
        StAdjustmentReserveDetailClp oldClpModel = (StAdjustmentReserveDetailClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStAdjustmentReserveDetailRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStArpOutbound(BaseModel<?> oldModel) {
        StArpOutboundClp oldClpModel = (StArpOutboundClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStArpOutboundRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStCffOutboundMaster(
        BaseModel<?> oldModel) {
        StCffOutboundMasterClp oldClpModel = (StCffOutboundMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStCffOutboundMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputVwAdjustDemandForecastAct(
        BaseModel<?> oldModel) {
        VwAdjustDemandForecastActClp oldClpModel = (VwAdjustDemandForecastActClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getVwAdjustDemandForecastActRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputVwCompanyIdentifier(
        BaseModel<?> oldModel) {
        VwCompanyIdentifierClp oldClpModel = (VwCompanyIdentifierClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getVwCompanyIdentifierRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputVwCompanyMaster(BaseModel<?> oldModel) {
        VwCompanyMasterClp oldClpModel = (VwCompanyMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getVwCompanyMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputVwCompanyParentDetails(
        BaseModel<?> oldModel) {
        VwCompanyParentDetailsClp oldClpModel = (VwCompanyParentDetailsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getVwCompanyParentDetailsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputVwCompanyTradeClass(
        BaseModel<?> oldModel) {
        VwCompanyTradeClassClp oldClpModel = (VwCompanyTradeClassClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getVwCompanyTradeClassRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputVwCustomerGtsForecast(
        BaseModel<?> oldModel) {
        VwCustomerGtsForecastClp oldClpModel = (VwCustomerGtsForecastClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getVwCustomerGtsForecastRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputVwItemIdentifier(BaseModel<?> oldModel) {
        VwItemIdentifierClp oldClpModel = (VwItemIdentifierClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getVwItemIdentifierRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputVwItemMaster(BaseModel<?> oldModel) {
        VwItemMasterClp oldClpModel = (VwItemMasterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getVwItemMasterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputVwItemPricing(BaseModel<?> oldModel) {
        VwItemPricingClp oldClpModel = (VwItemPricingClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getVwItemPricingRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputVwIvldAdjDemandForeActual(
        BaseModel<?> oldModel) {
        VwIvldAdjDemandForeActualClp oldClpModel = (VwIvldAdjDemandForeActualClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getVwIvldAdjDemandForeActualRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputVwIvldReturnReserve(
        BaseModel<?> oldModel) {
        VwIvldReturnReserveClp oldClpModel = (VwIvldReturnReserveClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getVwIvldReturnReserveRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputVwReturnReserve(BaseModel<?> oldModel) {
        VwReturnReserveClp oldClpModel = (VwReturnReserveClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getVwReturnReserveRemoteModel();

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
                    "com.stpl.app.parttwo.model.impl.AcBaseRateBaselineDetailsImpl")) {
            return translateOutputAcBaseRateBaselineDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.AcBrMethodologyDetailsImpl")) {
            return translateOutputAcBrMethodologyDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.AccClosureDetailsImpl")) {
            return translateOutputAccClosureDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.AccClosureMasterImpl")) {
            return translateOutputAccClosureMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.AccClosureViewMasterImpl")) {
            return translateOutputAccClosureViewMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.AcFdAdjustmentSelectionImpl")) {
            return translateOutputAcFdAdjustmentSelection(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.AdjustedDemandForecastImpl")) {
            return translateOutputAdjustedDemandForecast(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.AhTempDetailsImpl")) {
            return translateOutputAhTempDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.ArpOutboundImpl")) {
            return translateOutputArpOutbound(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.CffAdditionalInfoImpl")) {
            return translateOutputCffAdditionalInfo(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.CffApprovalDetailsImpl")) {
            return translateOutputCffApprovalDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.CffCustHierarchyImpl")) {
            return translateOutputCffCustHierarchy(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.CffCustomViewDetailsImpl")) {
            return translateOutputCffCustomViewDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.CffCustomViewMasterImpl")) {
            return translateOutputCffCustomViewMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.CffDetailsImpl")) {
            return translateOutputCffDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.CffDocDetailsImpl")) {
            return translateOutputCffDocDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.CffMasterImpl")) {
            return translateOutputCffMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.CffProdHierarchyImpl")) {
            return translateOutputCffProdHierarchy(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.CffViewMasterImpl")) {
            return translateOutputCffViewMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.CustomerGtsActualImpl")) {
            return translateOutputCustomerGtsActual(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.CustomerGtsForecastImpl")) {
            return translateOutputCustomerGtsForecast(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.IvldAccrualInboundImpl")) {
            return translateOutputIvldAccrualInbound(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.IvldCompanyIdentifierImpl")) {
            return translateOutputIvldCompanyIdentifier(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.IvldCompanyMasterImpl")) {
            return translateOutputIvldCompanyMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.IvldCompanyParentImpl")) {
            return translateOutputIvldCompanyParent(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.IvldCompanyTradeClassImpl")) {
            return translateOutputIvldCompanyTradeClass(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.IvldCustomerGtsActualImpl")) {
            return translateOutputIvldCustomerGtsActual(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastImpl")) {
            return translateOutputIvldCustomerGtsForecast(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.IvldItemIdentifierImpl")) {
            return translateOutputIvldItemIdentifier(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.IvldItemMasterImpl")) {
            return translateOutputIvldItemMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.IvldItemPricingImpl")) {
            return translateOutputIvldItemPricing(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.SlaCalendarDetailsImpl")) {
            return translateOutputSlaCalendarDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.SlaCalendarMasterImpl")) {
            return translateOutputSlaCalendarMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.StAccClosureDetailsImpl")) {
            return translateOutputStAccClosureDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailImpl")) {
            return translateOutputStAdjustmentGtnDetail(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.StAdjustmentReserveDetailImpl")) {
            return translateOutputStAdjustmentReserveDetail(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.StArpOutboundImpl")) {
            return translateOutputStArpOutbound(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.StCffOutboundMasterImpl")) {
            return translateOutputStCffOutboundMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.VwAdjustDemandForecastActImpl")) {
            return translateOutputVwAdjustDemandForecastAct(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.VwCompanyIdentifierImpl")) {
            return translateOutputVwCompanyIdentifier(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.VwCompanyMasterImpl")) {
            return translateOutputVwCompanyMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.VwCompanyParentDetailsImpl")) {
            return translateOutputVwCompanyParentDetails(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.VwCompanyTradeClassImpl")) {
            return translateOutputVwCompanyTradeClass(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.VwCustomerGtsForecastImpl")) {
            return translateOutputVwCustomerGtsForecast(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.VwItemIdentifierImpl")) {
            return translateOutputVwItemIdentifier(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.VwItemMasterImpl")) {
            return translateOutputVwItemMaster(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.VwItemPricingImpl")) {
            return translateOutputVwItemPricing(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.VwIvldAdjDemandForeActualImpl")) {
            return translateOutputVwIvldAdjDemandForeActual(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.VwIvldReturnReserveImpl")) {
            return translateOutputVwIvldReturnReserve(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.VwReturnReserveImpl")) {
            return translateOutputVwReturnReserve(oldModel);
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

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchAcBaseRateBaselineDetailsException")) {
            return new com.stpl.app.parttwo.NoSuchAcBaseRateBaselineDetailsException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchAcBrMethodologyDetailsException")) {
            return new com.stpl.app.parttwo.NoSuchAcBrMethodologyDetailsException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchAccClosureDetailsException")) {
            return new com.stpl.app.parttwo.NoSuchAccClosureDetailsException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchAccClosureMasterException")) {
            return new com.stpl.app.parttwo.NoSuchAccClosureMasterException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchAccClosureViewMasterException")) {
            return new com.stpl.app.parttwo.NoSuchAccClosureViewMasterException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchAcFdAdjustmentSelectionException")) {
            return new com.stpl.app.parttwo.NoSuchAcFdAdjustmentSelectionException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchAdjustedDemandForecastException")) {
            return new com.stpl.app.parttwo.NoSuchAdjustedDemandForecastException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchAhTempDetailsException")) {
            return new com.stpl.app.parttwo.NoSuchAhTempDetailsException();
        }

        if (className.equals("com.stpl.app.parttwo.NoSuchArpOutboundException")) {
            return new com.stpl.app.parttwo.NoSuchArpOutboundException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchCffAdditionalInfoException")) {
            return new com.stpl.app.parttwo.NoSuchCffAdditionalInfoException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchCffApprovalDetailsException")) {
            return new com.stpl.app.parttwo.NoSuchCffApprovalDetailsException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchCffCustHierarchyException")) {
            return new com.stpl.app.parttwo.NoSuchCffCustHierarchyException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchCffCustomViewDetailsException")) {
            return new com.stpl.app.parttwo.NoSuchCffCustomViewDetailsException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchCffCustomViewMasterException")) {
            return new com.stpl.app.parttwo.NoSuchCffCustomViewMasterException();
        }

        if (className.equals("com.stpl.app.parttwo.NoSuchCffDetailsException")) {
            return new com.stpl.app.parttwo.NoSuchCffDetailsException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchCffDocDetailsException")) {
            return new com.stpl.app.parttwo.NoSuchCffDocDetailsException();
        }

        if (className.equals("com.stpl.app.parttwo.NoSuchCffMasterException")) {
            return new com.stpl.app.parttwo.NoSuchCffMasterException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchCffProdHierarchyException")) {
            return new com.stpl.app.parttwo.NoSuchCffProdHierarchyException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchCffViewMasterException")) {
            return new com.stpl.app.parttwo.NoSuchCffViewMasterException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchCustomerGtsActualException")) {
            return new com.stpl.app.parttwo.NoSuchCustomerGtsActualException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchCustomerGtsForecastException")) {
            return new com.stpl.app.parttwo.NoSuchCustomerGtsForecastException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchIvldAccrualInboundException")) {
            return new com.stpl.app.parttwo.NoSuchIvldAccrualInboundException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchIvldCompanyIdentifierException")) {
            return new com.stpl.app.parttwo.NoSuchIvldCompanyIdentifierException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchIvldCompanyMasterException")) {
            return new com.stpl.app.parttwo.NoSuchIvldCompanyMasterException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchIvldCompanyParentException")) {
            return new com.stpl.app.parttwo.NoSuchIvldCompanyParentException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchIvldCompanyTradeClassException")) {
            return new com.stpl.app.parttwo.NoSuchIvldCompanyTradeClassException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchIvldCustomerGtsActualException")) {
            return new com.stpl.app.parttwo.NoSuchIvldCustomerGtsActualException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchIvldCustomerGtsForecastException")) {
            return new com.stpl.app.parttwo.NoSuchIvldCustomerGtsForecastException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchIvldItemIdentifierException")) {
            return new com.stpl.app.parttwo.NoSuchIvldItemIdentifierException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchIvldItemMasterException")) {
            return new com.stpl.app.parttwo.NoSuchIvldItemMasterException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchIvldItemPricingException")) {
            return new com.stpl.app.parttwo.NoSuchIvldItemPricingException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchSlaCalendarDetailsException")) {
            return new com.stpl.app.parttwo.NoSuchSlaCalendarDetailsException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchSlaCalendarMasterException")) {
            return new com.stpl.app.parttwo.NoSuchSlaCalendarMasterException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchStAccClosureDetailsException")) {
            return new com.stpl.app.parttwo.NoSuchStAccClosureDetailsException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchStAdjustmentGtnDetailException")) {
            return new com.stpl.app.parttwo.NoSuchStAdjustmentGtnDetailException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchStAdjustmentReserveDetailException")) {
            return new com.stpl.app.parttwo.NoSuchStAdjustmentReserveDetailException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchStArpOutboundException")) {
            return new com.stpl.app.parttwo.NoSuchStArpOutboundException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchStCffOutboundMasterException")) {
            return new com.stpl.app.parttwo.NoSuchStCffOutboundMasterException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchVwAdjustDemandForecastActException")) {
            return new com.stpl.app.parttwo.NoSuchVwAdjustDemandForecastActException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchVwCompanyIdentifierException")) {
            return new com.stpl.app.parttwo.NoSuchVwCompanyIdentifierException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchVwCompanyMasterException")) {
            return new com.stpl.app.parttwo.NoSuchVwCompanyMasterException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchVwCompanyParentDetailsException")) {
            return new com.stpl.app.parttwo.NoSuchVwCompanyParentDetailsException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchVwCompanyTradeClassException")) {
            return new com.stpl.app.parttwo.NoSuchVwCompanyTradeClassException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchVwCustomerGtsForecastException")) {
            return new com.stpl.app.parttwo.NoSuchVwCustomerGtsForecastException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchVwItemIdentifierException")) {
            return new com.stpl.app.parttwo.NoSuchVwItemIdentifierException();
        }

        if (className.equals("com.stpl.app.parttwo.NoSuchVwItemMasterException")) {
            return new com.stpl.app.parttwo.NoSuchVwItemMasterException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchVwItemPricingException")) {
            return new com.stpl.app.parttwo.NoSuchVwItemPricingException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchVwIvldAdjDemandForeActualException")) {
            return new com.stpl.app.parttwo.NoSuchVwIvldAdjDemandForeActualException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchVwIvldReturnReserveException")) {
            return new com.stpl.app.parttwo.NoSuchVwIvldReturnReserveException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchVwReturnReserveException")) {
            return new com.stpl.app.parttwo.NoSuchVwReturnReserveException();
        }

        return throwable;
    }

    public static Object translateOutputAcBaseRateBaselineDetails(
        BaseModel<?> oldModel) {
        AcBaseRateBaselineDetailsClp newModel = new AcBaseRateBaselineDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setAcBaseRateBaselineDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputAcBrMethodologyDetails(
        BaseModel<?> oldModel) {
        AcBrMethodologyDetailsClp newModel = new AcBrMethodologyDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setAcBrMethodologyDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputAccClosureDetails(BaseModel<?> oldModel) {
        AccClosureDetailsClp newModel = new AccClosureDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setAccClosureDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputAccClosureMaster(BaseModel<?> oldModel) {
        AccClosureMasterClp newModel = new AccClosureMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setAccClosureMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputAccClosureViewMaster(
        BaseModel<?> oldModel) {
        AccClosureViewMasterClp newModel = new AccClosureViewMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setAccClosureViewMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputAcFdAdjustmentSelection(
        BaseModel<?> oldModel) {
        AcFdAdjustmentSelectionClp newModel = new AcFdAdjustmentSelectionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setAcFdAdjustmentSelectionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputAdjustedDemandForecast(
        BaseModel<?> oldModel) {
        AdjustedDemandForecastClp newModel = new AdjustedDemandForecastClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setAdjustedDemandForecastRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputAhTempDetails(BaseModel<?> oldModel) {
        AhTempDetailsClp newModel = new AhTempDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setAhTempDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputArpOutbound(BaseModel<?> oldModel) {
        ArpOutboundClp newModel = new ArpOutboundClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setArpOutboundRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCffAdditionalInfo(BaseModel<?> oldModel) {
        CffAdditionalInfoClp newModel = new CffAdditionalInfoClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCffAdditionalInfoRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCffApprovalDetails(
        BaseModel<?> oldModel) {
        CffApprovalDetailsClp newModel = new CffApprovalDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCffApprovalDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCffCustHierarchy(BaseModel<?> oldModel) {
        CffCustHierarchyClp newModel = new CffCustHierarchyClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCffCustHierarchyRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCffCustomViewDetails(
        BaseModel<?> oldModel) {
        CffCustomViewDetailsClp newModel = new CffCustomViewDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCffCustomViewDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCffCustomViewMaster(
        BaseModel<?> oldModel) {
        CffCustomViewMasterClp newModel = new CffCustomViewMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCffCustomViewMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCffDetails(BaseModel<?> oldModel) {
        CffDetailsClp newModel = new CffDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCffDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCffDocDetails(BaseModel<?> oldModel) {
        CffDocDetailsClp newModel = new CffDocDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCffDocDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCffMaster(BaseModel<?> oldModel) {
        CffMasterClp newModel = new CffMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCffMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCffProdHierarchy(BaseModel<?> oldModel) {
        CffProdHierarchyClp newModel = new CffProdHierarchyClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCffProdHierarchyRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCffViewMaster(BaseModel<?> oldModel) {
        CffViewMasterClp newModel = new CffViewMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCffViewMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCustomerGtsActual(BaseModel<?> oldModel) {
        CustomerGtsActualClp newModel = new CustomerGtsActualClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCustomerGtsActualRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputCustomerGtsForecast(
        BaseModel<?> oldModel) {
        CustomerGtsForecastClp newModel = new CustomerGtsForecastClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setCustomerGtsForecastRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldAccrualInbound(
        BaseModel<?> oldModel) {
        IvldAccrualInboundClp newModel = new IvldAccrualInboundClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldAccrualInboundRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldCompanyIdentifier(
        BaseModel<?> oldModel) {
        IvldCompanyIdentifierClp newModel = new IvldCompanyIdentifierClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldCompanyIdentifierRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldCompanyMaster(BaseModel<?> oldModel) {
        IvldCompanyMasterClp newModel = new IvldCompanyMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldCompanyMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldCompanyParent(BaseModel<?> oldModel) {
        IvldCompanyParentClp newModel = new IvldCompanyParentClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldCompanyParentRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldCompanyTradeClass(
        BaseModel<?> oldModel) {
        IvldCompanyTradeClassClp newModel = new IvldCompanyTradeClassClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldCompanyTradeClassRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldCustomerGtsActual(
        BaseModel<?> oldModel) {
        IvldCustomerGtsActualClp newModel = new IvldCustomerGtsActualClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldCustomerGtsActualRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldCustomerGtsForecast(
        BaseModel<?> oldModel) {
        IvldCustomerGtsForecastClp newModel = new IvldCustomerGtsForecastClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldCustomerGtsForecastRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldItemIdentifier(
        BaseModel<?> oldModel) {
        IvldItemIdentifierClp newModel = new IvldItemIdentifierClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldItemIdentifierRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldItemMaster(BaseModel<?> oldModel) {
        IvldItemMasterClp newModel = new IvldItemMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldItemMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIvldItemPricing(BaseModel<?> oldModel) {
        IvldItemPricingClp newModel = new IvldItemPricingClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIvldItemPricingRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputSlaCalendarDetails(
        BaseModel<?> oldModel) {
        SlaCalendarDetailsClp newModel = new SlaCalendarDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setSlaCalendarDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputSlaCalendarMaster(BaseModel<?> oldModel) {
        SlaCalendarMasterClp newModel = new SlaCalendarMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setSlaCalendarMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStAccClosureDetails(
        BaseModel<?> oldModel) {
        StAccClosureDetailsClp newModel = new StAccClosureDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStAccClosureDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStAdjustmentGtnDetail(
        BaseModel<?> oldModel) {
        StAdjustmentGtnDetailClp newModel = new StAdjustmentGtnDetailClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStAdjustmentGtnDetailRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStAdjustmentReserveDetail(
        BaseModel<?> oldModel) {
        StAdjustmentReserveDetailClp newModel = new StAdjustmentReserveDetailClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStAdjustmentReserveDetailRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStArpOutbound(BaseModel<?> oldModel) {
        StArpOutboundClp newModel = new StArpOutboundClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStArpOutboundRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStCffOutboundMaster(
        BaseModel<?> oldModel) {
        StCffOutboundMasterClp newModel = new StCffOutboundMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStCffOutboundMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputVwAdjustDemandForecastAct(
        BaseModel<?> oldModel) {
        VwAdjustDemandForecastActClp newModel = new VwAdjustDemandForecastActClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setVwAdjustDemandForecastActRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputVwCompanyIdentifier(
        BaseModel<?> oldModel) {
        VwCompanyIdentifierClp newModel = new VwCompanyIdentifierClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setVwCompanyIdentifierRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputVwCompanyMaster(BaseModel<?> oldModel) {
        VwCompanyMasterClp newModel = new VwCompanyMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setVwCompanyMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputVwCompanyParentDetails(
        BaseModel<?> oldModel) {
        VwCompanyParentDetailsClp newModel = new VwCompanyParentDetailsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setVwCompanyParentDetailsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputVwCompanyTradeClass(
        BaseModel<?> oldModel) {
        VwCompanyTradeClassClp newModel = new VwCompanyTradeClassClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setVwCompanyTradeClassRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputVwCustomerGtsForecast(
        BaseModel<?> oldModel) {
        VwCustomerGtsForecastClp newModel = new VwCustomerGtsForecastClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setVwCustomerGtsForecastRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputVwItemIdentifier(BaseModel<?> oldModel) {
        VwItemIdentifierClp newModel = new VwItemIdentifierClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setVwItemIdentifierRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputVwItemMaster(BaseModel<?> oldModel) {
        VwItemMasterClp newModel = new VwItemMasterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setVwItemMasterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputVwItemPricing(BaseModel<?> oldModel) {
        VwItemPricingClp newModel = new VwItemPricingClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setVwItemPricingRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputVwIvldAdjDemandForeActual(
        BaseModel<?> oldModel) {
        VwIvldAdjDemandForeActualClp newModel = new VwIvldAdjDemandForeActualClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setVwIvldAdjDemandForeActualRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputVwIvldReturnReserve(
        BaseModel<?> oldModel) {
        VwIvldReturnReserveClp newModel = new VwIvldReturnReserveClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setVwIvldReturnReserveRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputVwReturnReserve(BaseModel<?> oldModel) {
        VwReturnReserveClp newModel = new VwReturnReserveClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setVwReturnReserveRemoteModel(oldModel);

        return newModel;
    }
}
