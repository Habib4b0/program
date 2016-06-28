package com.stpl.app.service.base;

import com.stpl.app.service.NmPpaProjectionMasterLocalServiceUtil;

import java.util.Arrays;

/**
 * @author
 * @generated
 */
public class NmPpaProjectionMasterLocalServiceClpInvoker {
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
    private String _methodName973;
    private String[] _methodParameterTypes973;
    private String _methodName974;
    private String[] _methodParameterTypes974;

    public NmPpaProjectionMasterLocalServiceClpInvoker() {
        _methodName0 = "addNmPpaProjectionMaster";

        _methodParameterTypes0 = new String[] {
                "com.stpl.app.model.NmPpaProjectionMaster"
            };

        _methodName1 = "createNmPpaProjectionMaster";

        _methodParameterTypes1 = new String[] { "int" };

        _methodName2 = "deleteNmPpaProjectionMaster";

        _methodParameterTypes2 = new String[] { "int" };

        _methodName3 = "deleteNmPpaProjectionMaster";

        _methodParameterTypes3 = new String[] {
                "com.stpl.app.model.NmPpaProjectionMaster"
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

        _methodName10 = "fetchNmPpaProjectionMaster";

        _methodParameterTypes10 = new String[] { "int" };

        _methodName11 = "getNmPpaProjectionMaster";

        _methodParameterTypes11 = new String[] { "int" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getNmPpaProjectionMasters";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getNmPpaProjectionMastersCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateNmPpaProjectionMaster";

        _methodParameterTypes15 = new String[] {
                "com.stpl.app.model.NmPpaProjectionMaster"
            };

        _methodName964 = "getBeanIdentifier";

        _methodParameterTypes964 = new String[] {  };

        _methodName965 = "setBeanIdentifier";

        _methodParameterTypes965 = new String[] { "java.lang.String" };

        _methodName970 = "getPPAProjectionList";

        _methodParameterTypes970 = new String[] {
                "java.lang.Integer", "int", "java.lang.String", "boolean", "int",
                "int", "boolean", "java.lang.String"
            };

        _methodName971 = "setPPAProjectionMassUpdate";

        _methodParameterTypes971 = new String[] {
                "java.lang.Object", "int", "int", "int", "int", "int",
                "java.lang.String", "java.lang.String"
            };

        _methodName972 = "getPPAResults";

        _methodParameterTypes972 = new String[] {
                "java.lang.Integer", "int", "java.lang.String", "boolean", "int",
                "int", "boolean", "java.util.List", "java.lang.String"
            };

        _methodName973 = "getLevelValues";

        _methodParameterTypes973 = new String[] { "int", "int", "java.lang.String" };

        _methodName974 = "getProductHierarchyLevel";

        _methodParameterTypes974 = new String[] { "int", "int", "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.addNmPpaProjectionMaster((com.stpl.app.model.NmPpaProjectionMaster) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.createNmPpaProjectionMaster(((Integer) arguments[0]).intValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.deleteNmPpaProjectionMaster(((Integer) arguments[0]).intValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.deleteNmPpaProjectionMaster((com.stpl.app.model.NmPpaProjectionMaster) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.dynamicQuery((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.dynamicQuery((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.dynamicQuery((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.stpl.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.dynamicQueryCount((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.dynamicQueryCount((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.stpl.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.fetchNmPpaProjectionMaster(((Integer) arguments[0]).intValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.getNmPpaProjectionMaster(((Integer) arguments[0]).intValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.getNmPpaProjectionMasters(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.getNmPpaProjectionMastersCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.updateNmPpaProjectionMaster((com.stpl.app.model.NmPpaProjectionMaster) arguments[0]);
        }

        if (_methodName964.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes964, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName965.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes965, parameterTypes)) {
            NmPpaProjectionMasterLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName970.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes970, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.getPPAProjectionList((java.lang.Integer) arguments[0],
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue(),
                ((Integer) arguments[4]).intValue(),
                ((Integer) arguments[5]).intValue(),
                ((Boolean) arguments[6]).booleanValue(),
                (java.lang.String) arguments[7]);
        }

        if (_methodName971.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes971, parameterTypes)) {
            NmPpaProjectionMasterLocalServiceUtil.setPPAProjectionMassUpdate((java.lang.Object) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                ((Integer) arguments[3]).intValue(),
                ((Integer) arguments[4]).intValue(),
                ((Integer) arguments[5]).intValue(),
                (java.lang.String) arguments[6], (java.lang.String) arguments[7]);

            return null;
        }

        if (_methodName972.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes972, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.getPPAResults((java.lang.Integer) arguments[0],
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue(),
                ((Integer) arguments[4]).intValue(),
                ((Integer) arguments[5]).intValue(),
                ((Boolean) arguments[6]).booleanValue(),
                (java.util.List<java.lang.String>) arguments[7],
                (java.lang.String) arguments[8]);
        }

        if (_methodName973.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes973, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.getLevelValues(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName974.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes974, parameterTypes)) {
            return NmPpaProjectionMasterLocalServiceUtil.getProductHierarchyLevel(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        throw new UnsupportedOperationException();
    }
}
