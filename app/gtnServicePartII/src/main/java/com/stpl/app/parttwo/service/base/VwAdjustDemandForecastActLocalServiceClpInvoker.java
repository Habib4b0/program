package com.stpl.app.parttwo.service.base;

import com.stpl.app.parttwo.service.VwAdjustDemandForecastActLocalServiceUtil;

import java.util.Arrays;

/**
 * @author
 * @generated
 */
public class VwAdjustDemandForecastActLocalServiceClpInvoker {
    private String _methodName0;
    private String[] _methodParameterTypes0;
    private String _methodName1;
    private String[] _methodParameterTypes1;
    private String _methodName2;
    private String[] _methodParameterTypes2;
    private String _methodName3;
    private String[] _methodParameterTypes3;
    private String _methodName4;
    private String[] _methodParameterTypes4;
    private String _methodName5;
    private String[] _methodParameterTypes5;
    private String _methodName6;
    private String[] _methodParameterTypes6;
    private String _methodName7;
    private String[] _methodParameterTypes7;
    private String _methodName8;
    private String[] _methodParameterTypes8;
    private String _methodName9;
    private String[] _methodParameterTypes9;
    private String _methodName10;
    private String[] _methodParameterTypes10;
    private String _methodName11;
    private String[] _methodParameterTypes11;
    private String _methodName12;
    private String[] _methodParameterTypes12;
    private String _methodName13;
    private String[] _methodParameterTypes13;
    private String _methodName14;
    private String[] _methodParameterTypes14;
    private String _methodName15;
    private String[] _methodParameterTypes15;
    private String _methodName230;
    private String[] _methodParameterTypes230;
    private String _methodName231;
    private String[] _methodParameterTypes231;

    public VwAdjustDemandForecastActLocalServiceClpInvoker() {
        _methodName0 = "addVwAdjustDemandForecastAct";

        _methodParameterTypes0 = new String[] {
                "com.stpl.app.parttwo.model.VwAdjustDemandForecastAct"
            };

        _methodName1 = "createVwAdjustDemandForecastAct";

        _methodParameterTypes1 = new String[] { "int" };

        _methodName2 = "deleteVwAdjustDemandForecastAct";

        _methodParameterTypes2 = new String[] { "int" };

        _methodName3 = "deleteVwAdjustDemandForecastAct";

        _methodParameterTypes3 = new String[] {
                "com.stpl.app.parttwo.model.VwAdjustDemandForecastAct"
            };

        _methodName4 = "dynamicQuery";

        _methodParameterTypes4 = new String[] {  };

        _methodName5 = "dynamicQuery";

        _methodParameterTypes5 = new String[] {
                "com.stpl.portal.kernel.dao.orm.DynamicQuery"
            };

        _methodName6 = "dynamicQuery";

        _methodParameterTypes6 = new String[] {
                "com.stpl.portal.kernel.dao.orm.DynamicQuery", "int", "int"
            };

        _methodName7 = "dynamicQuery";

        _methodParameterTypes7 = new String[] {
                "com.stpl.portal.kernel.dao.orm.DynamicQuery", "int", "int",
                "com.stpl.portal.kernel.util.OrderByComparator"
            };

        _methodName8 = "dynamicQueryCount";

        _methodParameterTypes8 = new String[] {
                "com.stpl.portal.kernel.dao.orm.DynamicQuery"
            };

        _methodName9 = "dynamicQueryCount";

        _methodParameterTypes9 = new String[] {
                "com.stpl.portal.kernel.dao.orm.DynamicQuery",
                "com.stpl.portal.kernel.dao.orm.Projection"
            };

        _methodName10 = "fetchVwAdjustDemandForecastAct";

        _methodParameterTypes10 = new String[] { "int" };

        _methodName11 = "getVwAdjustDemandForecastAct";

        _methodParameterTypes11 = new String[] { "int" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getVwAdjustDemandForecastActs";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getVwAdjustDemandForecastActsCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateVwAdjustDemandForecastAct";

        _methodParameterTypes15 = new String[] {
                "com.stpl.app.parttwo.model.VwAdjustDemandForecastAct"
            };

        _methodName230 = "getBeanIdentifier";

        _methodParameterTypes230 = new String[] {  };

        _methodName231 = "setBeanIdentifier";

        _methodParameterTypes231 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return VwAdjustDemandForecastActLocalServiceUtil.addVwAdjustDemandForecastAct((com.stpl.app.parttwo.model.VwAdjustDemandForecastAct) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return VwAdjustDemandForecastActLocalServiceUtil.createVwAdjustDemandForecastAct(((Integer) arguments[0]).intValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return VwAdjustDemandForecastActLocalServiceUtil.deleteVwAdjustDemandForecastAct(((Integer) arguments[0]).intValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return VwAdjustDemandForecastActLocalServiceUtil.deleteVwAdjustDemandForecastAct((com.stpl.app.parttwo.model.VwAdjustDemandForecastAct) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return VwAdjustDemandForecastActLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return VwAdjustDemandForecastActLocalServiceUtil.dynamicQuery((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return VwAdjustDemandForecastActLocalServiceUtil.dynamicQuery((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return VwAdjustDemandForecastActLocalServiceUtil.dynamicQuery((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.stpl.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return VwAdjustDemandForecastActLocalServiceUtil.dynamicQueryCount((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return VwAdjustDemandForecastActLocalServiceUtil.dynamicQueryCount((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.stpl.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return VwAdjustDemandForecastActLocalServiceUtil.fetchVwAdjustDemandForecastAct(((Integer) arguments[0]).intValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return VwAdjustDemandForecastActLocalServiceUtil.getVwAdjustDemandForecastAct(((Integer) arguments[0]).intValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return VwAdjustDemandForecastActLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return VwAdjustDemandForecastActLocalServiceUtil.getVwAdjustDemandForecastActs(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return VwAdjustDemandForecastActLocalServiceUtil.getVwAdjustDemandForecastActsCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return VwAdjustDemandForecastActLocalServiceUtil.updateVwAdjustDemandForecastAct((com.stpl.app.parttwo.model.VwAdjustDemandForecastAct) arguments[0]);
        }

        if (_methodName230.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes230, parameterTypes)) {
            return VwAdjustDemandForecastActLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName231.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes231, parameterTypes)) {
            VwAdjustDemandForecastActLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
