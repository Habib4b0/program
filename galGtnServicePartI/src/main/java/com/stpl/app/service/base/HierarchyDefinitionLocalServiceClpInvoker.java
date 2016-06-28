package com.stpl.app.service.base;

import com.stpl.app.service.HierarchyDefinitionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author
 * @generated
 */
public class HierarchyDefinitionLocalServiceClpInvoker {
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
    private String _methodName970;
    private String[] _methodParameterTypes970;
    private String _methodName971;
    private String[] _methodParameterTypes971;
    private String _methodName972;
    private String[] _methodParameterTypes972;

    public HierarchyDefinitionLocalServiceClpInvoker() {
        _methodName0 = "addHierarchyDefinition";

        _methodParameterTypes0 = new String[] {
                "com.stpl.app.model.HierarchyDefinition"
            };

        _methodName1 = "createHierarchyDefinition";

        _methodParameterTypes1 = new String[] { "int" };

        _methodName2 = "deleteHierarchyDefinition";

        _methodParameterTypes2 = new String[] { "int" };

        _methodName3 = "deleteHierarchyDefinition";

        _methodParameterTypes3 = new String[] {
                "com.stpl.app.model.HierarchyDefinition"
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

        _methodName10 = "fetchHierarchyDefinition";

        _methodParameterTypes10 = new String[] { "int" };

        _methodName11 = "getHierarchyDefinition";

        _methodParameterTypes11 = new String[] { "int" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getHierarchyDefinitions";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getHierarchyDefinitionsCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateHierarchyDefinition";

        _methodParameterTypes15 = new String[] {
                "com.stpl.app.model.HierarchyDefinition"
            };

        _methodName964 = "getBeanIdentifier";

        _methodParameterTypes964 = new String[] {  };

        _methodName965 = "setBeanIdentifier";

        _methodParameterTypes965 = new String[] { "java.lang.String" };

        _methodName970 = "getHierarchyGroup";

        _methodParameterTypes970 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String"
            };

        _methodName971 = "getLevelsFromHierarchy";

        _methodParameterTypes971 = new String[] { "java.util.Map" };

        _methodName972 = "getHierarchySystemId";

        _methodParameterTypes972 = new String[] { "int" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return HierarchyDefinitionLocalServiceUtil.addHierarchyDefinition((com.stpl.app.model.HierarchyDefinition) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return HierarchyDefinitionLocalServiceUtil.createHierarchyDefinition(((Integer) arguments[0]).intValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return HierarchyDefinitionLocalServiceUtil.deleteHierarchyDefinition(((Integer) arguments[0]).intValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return HierarchyDefinitionLocalServiceUtil.deleteHierarchyDefinition((com.stpl.app.model.HierarchyDefinition) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return HierarchyDefinitionLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return HierarchyDefinitionLocalServiceUtil.dynamicQuery((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return HierarchyDefinitionLocalServiceUtil.dynamicQuery((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return HierarchyDefinitionLocalServiceUtil.dynamicQuery((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.stpl.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return HierarchyDefinitionLocalServiceUtil.dynamicQueryCount((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return HierarchyDefinitionLocalServiceUtil.dynamicQueryCount((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.stpl.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return HierarchyDefinitionLocalServiceUtil.fetchHierarchyDefinition(((Integer) arguments[0]).intValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return HierarchyDefinitionLocalServiceUtil.getHierarchyDefinition(((Integer) arguments[0]).intValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return HierarchyDefinitionLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return HierarchyDefinitionLocalServiceUtil.getHierarchyDefinitions(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return HierarchyDefinitionLocalServiceUtil.getHierarchyDefinitionsCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return HierarchyDefinitionLocalServiceUtil.updateHierarchyDefinition((com.stpl.app.model.HierarchyDefinition) arguments[0]);
        }

        if (_methodName964.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes964, parameterTypes)) {
            return HierarchyDefinitionLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName965.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes965, parameterTypes)) {
            HierarchyDefinitionLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName970.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes970, parameterTypes)) {
            return HierarchyDefinitionLocalServiceUtil.getHierarchyGroup((java.lang.String) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName971.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes971, parameterTypes)) {
            return HierarchyDefinitionLocalServiceUtil.getLevelsFromHierarchy((java.util.Map<java.lang.String, java.lang.Object>) arguments[0]);
        }

        if (_methodName972.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes972, parameterTypes)) {
            return HierarchyDefinitionLocalServiceUtil.getHierarchySystemId(((Integer) arguments[0]).intValue());
        }

        throw new UnsupportedOperationException();
    }
}
