package com.stpl.app.parttwo.service;

import com.stpl.app.parttwo.model.AcBaseRateBaselineDetailsClp;
import com.stpl.app.parttwo.model.AcBrMethodologyDetailsClp;
import com.stpl.app.parttwo.model.AcFdAdjustmentSelectionClp;
import com.stpl.app.parttwo.model.AccClosureDetailsClp;
import com.stpl.app.parttwo.model.AccClosureMasterClp;
import com.stpl.app.parttwo.model.AccClosureViewMasterClp;
import com.stpl.app.parttwo.model.AdjustedDemandForecastClp;
import com.stpl.app.parttwo.model.AhTempDetailsClp;
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
import com.stpl.app.parttwo.model.IvldCustomerGtsActualClp;
import com.stpl.app.parttwo.model.IvldCustomerGtsForecastClp;
import com.stpl.app.parttwo.model.SlaCalendarDetailsClp;
import com.stpl.app.parttwo.model.SlaCalendarMasterClp;
import com.stpl.app.parttwo.model.StAccClosureDetailsClp;
import com.stpl.app.parttwo.model.VwAdjustDemandForecastActClp;
import com.stpl.app.parttwo.model.VwCustomerGtsForecastClp;
import com.stpl.app.parttwo.model.VwIvldAdjDemandForeActualClp;

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
                        "galGtnPartII-deployment-context");

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
                            "galGtnPartII-deployment-context");

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
                _servletContextName = "galGtnPartII";
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

        if (oldModelClassName.equals(IvldCustomerGtsActualClp.class.getName())) {
            return translateInputIvldCustomerGtsActual(oldModel);
        }

        if (oldModelClassName.equals(IvldCustomerGtsForecastClp.class.getName())) {
            return translateInputIvldCustomerGtsForecast(oldModel);
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

        if (oldModelClassName.equals(
                    VwAdjustDemandForecastActClp.class.getName())) {
            return translateInputVwAdjustDemandForecastAct(oldModel);
        }

        if (oldModelClassName.equals(VwCustomerGtsForecastClp.class.getName())) {
            return translateInputVwCustomerGtsForecast(oldModel);
        }

        if (oldModelClassName.equals(
                    VwIvldAdjDemandForeActualClp.class.getName())) {
            return translateInputVwIvldAdjDemandForeActual(oldModel);
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

    public static Object translateInputVwAdjustDemandForecastAct(
        BaseModel<?> oldModel) {
        VwAdjustDemandForecastActClp oldClpModel = (VwAdjustDemandForecastActClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getVwAdjustDemandForecastActRemoteModel();

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

    public static Object translateInputVwIvldAdjDemandForeActual(
        BaseModel<?> oldModel) {
        VwIvldAdjDemandForeActualClp oldClpModel = (VwIvldAdjDemandForeActualClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getVwIvldAdjDemandForeActualRemoteModel();

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
                    "com.stpl.app.parttwo.model.impl.IvldCustomerGtsActualImpl")) {
            return translateOutputIvldCustomerGtsActual(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastImpl")) {
            return translateOutputIvldCustomerGtsForecast(oldModel);
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
                    "com.stpl.app.parttwo.model.impl.VwAdjustDemandForecastActImpl")) {
            return translateOutputVwAdjustDemandForecastAct(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.VwCustomerGtsForecastImpl")) {
            return translateOutputVwCustomerGtsForecast(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.stpl.app.parttwo.model.impl.VwIvldAdjDemandForeActualImpl")) {
            return translateOutputVwIvldAdjDemandForeActual(oldModel);
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
                    "com.stpl.app.parttwo.NoSuchIvldCustomerGtsActualException")) {
            return new com.stpl.app.parttwo.NoSuchIvldCustomerGtsActualException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchIvldCustomerGtsForecastException")) {
            return new com.stpl.app.parttwo.NoSuchIvldCustomerGtsForecastException();
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
                    "com.stpl.app.parttwo.NoSuchVwAdjustDemandForecastActException")) {
            return new com.stpl.app.parttwo.NoSuchVwAdjustDemandForecastActException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchVwCustomerGtsForecastException")) {
            return new com.stpl.app.parttwo.NoSuchVwCustomerGtsForecastException();
        }

        if (className.equals(
                    "com.stpl.app.parttwo.NoSuchVwIvldAdjDemandForeActualException")) {
            return new com.stpl.app.parttwo.NoSuchVwIvldAdjDemandForeActualException();
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

    public static Object translateOutputVwAdjustDemandForecastAct(
        BaseModel<?> oldModel) {
        VwAdjustDemandForecastActClp newModel = new VwAdjustDemandForecastActClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setVwAdjustDemandForecastActRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputVwCustomerGtsForecast(
        BaseModel<?> oldModel) {
        VwCustomerGtsForecastClp newModel = new VwCustomerGtsForecastClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setVwCustomerGtsForecastRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputVwIvldAdjDemandForeActual(
        BaseModel<?> oldModel) {
        VwIvldAdjDemandForeActualClp newModel = new VwIvldAdjDemandForeActualClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setVwIvldAdjDemandForeActualRemoteModel(oldModel);

        return newModel;
    }
}
