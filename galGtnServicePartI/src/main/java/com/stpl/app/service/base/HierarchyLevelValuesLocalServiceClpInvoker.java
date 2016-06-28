package com.stpl.app.service.base;

import com.stpl.app.service.HierarchyLevelValuesLocalServiceUtil;

import java.util.Arrays;

/**
 * @author
 * @generated
 */
public class HierarchyLevelValuesLocalServiceClpInvoker {
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
    private String _methodName964;
    private String[] _methodParameterTypes964;
    private String _methodName965;
    private String[] _methodParameterTypes965;

    public HierarchyLevelValuesLocalServiceClpInvoker() {
        _methodName0 = "addHierarchyLevelValues";

        _methodParameterTypes0 = new String[] {
                "com.stpl.app.model.HierarchyLevelValues"
            };

        _methodName1 = "createHierarchyLevelValues";

        _methodParameterTypes1 = new String[] { "int" };

        _methodName2 = "deleteHierarchyLevelValues";

        _methodParameterTypes2 = new String[] { "int" };

        _methodName3 = "deleteHierarchyLevelValues";

        _methodParameterTypes3 = new String[] {
                "com.stpl.app.model.HierarchyLevelValues"
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

        _methodName10 = "fetchHierarchyLevelValues";

        _methodParameterTypes10 = new String[] { "int" };

        _methodName11 = "getHierarchyLevelValues";

        _methodParameterTypes11 = new String[] { "int" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getHierarchyLevelValueses";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getHierarchyLevelValuesesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateHierarchyLevelValues";

        _methodParameterTypes15 = new String[] {
                "com.stpl.app.model.HierarchyLevelValues"
            };

        _methodName964 = "getBeanIdentifier";

        _methodParameterTypes964 = new String[] {  };

        _methodName965 = "setBeanIdentifier";

        _methodParameterTypes965 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return HierarchyLevelValuesLocalServiceUtil.addHierarchyLevelValues((com.stpl.app.model.HierarchyLevelValues) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return HierarchyLevelValuesLocalServiceUtil.createHierarchyLevelValues(((Integer) arguments[0]).intValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return HierarchyLevelValuesLocalServiceUtil.deleteHierarchyLevelValues(((Integer) arguments[0]).intValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return HierarchyLevelValuesLocalServiceUtil.deleteHierarchyLevelValues((com.stpl.app.model.HierarchyLevelValues) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return HierarchyLevelValuesLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return HierarchyLevelValuesLocalServiceUtil.dynamicQuery((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return HierarchyLevelValuesLocalServiceUtil.dynamicQuery((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return HierarchyLevelValuesLocalServiceUtil.dynamicQuery((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.stpl.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return HierarchyLevelValuesLocalServiceUtil.dynamicQueryCount((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return HierarchyLevelValuesLocalServiceUtil.dynamicQueryCount((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.stpl.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return HierarchyLevelValuesLocalServiceUtil.fetchHierarchyLevelValues(((Integer) arguments[0]).intValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return HierarchyLevelValuesLocalServiceUtil.getHierarchyLevelValues(((Integer) arguments[0]).intValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return HierarchyLevelValuesLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return HierarchyLevelValuesLocalServiceUtil.getHierarchyLevelValueses(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return HierarchyLevelValuesLocalServiceUtil.getHierarchyLevelValuesesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return HierarchyLevelValuesLocalServiceUtil.updateHierarchyLevelValues((com.stpl.app.model.HierarchyLevelValues) arguments[0]);
        }

        if (_methodName964.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes964, parameterTypes)) {
            return HierarchyLevelValuesLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName965.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes965, parameterTypes)) {
            HierarchyLevelValuesLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
