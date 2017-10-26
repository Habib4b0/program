package com.stpl.app.service.base;

import com.stpl.app.service.IfpContractDetailsLocalServiceUtil;

import java.util.Arrays;

/**
 * @author
 * @generated
 */
public class IfpContractDetailsLocalServiceClpInvoker {
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

    public IfpContractDetailsLocalServiceClpInvoker() {
        _methodName0 = "addIfpContractDetails";

        _methodParameterTypes0 = new String[] {
                "com.stpl.app.model.IfpContractDetails"
            };

        _methodName1 = "createIfpContractDetails";

        _methodParameterTypes1 = new String[] { "int" };

        _methodName2 = "deleteIfpContractDetails";

        _methodParameterTypes2 = new String[] { "int" };

        _methodName3 = "deleteIfpContractDetails";

        _methodParameterTypes3 = new String[] {
                "com.stpl.app.model.IfpContractDetails"
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

        _methodName10 = "fetchIfpContractDetails";

        _methodParameterTypes10 = new String[] { "int" };

        _methodName11 = "getIfpContractDetails";

        _methodParameterTypes11 = new String[] { "int" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getIfpContractDetailses";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getIfpContractDetailsesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateIfpContractDetails";

        _methodParameterTypes15 = new String[] {
                "com.stpl.app.model.IfpContractDetails"
            };

        _methodName964 = "getBeanIdentifier";

        _methodParameterTypes964 = new String[] {  };

        _methodName965 = "setBeanIdentifier";

        _methodParameterTypes965 = new String[] { "java.lang.String" };

        _methodName970 = "saveIfpDetailsAttached";

        _methodParameterTypes970 = new String[] {
                "java.util.List", "java.lang.Object"
            };

        _methodName971 = "findIFP";

        _methodParameterTypes971 = new String[] {
                "java.lang.Object", "java.lang.Object", "java.util.List",
                "java.util.Map", "int", "int", "java.lang.String",
                "java.lang.String", "java.lang.Object"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return IfpContractDetailsLocalServiceUtil.addIfpContractDetails((com.stpl.app.model.IfpContractDetails) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return IfpContractDetailsLocalServiceUtil.createIfpContractDetails(((Integer) arguments[0]).intValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return IfpContractDetailsLocalServiceUtil.deleteIfpContractDetails(((Integer) arguments[0]).intValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return IfpContractDetailsLocalServiceUtil.deleteIfpContractDetails((com.stpl.app.model.IfpContractDetails) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return IfpContractDetailsLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return IfpContractDetailsLocalServiceUtil.dynamicQuery((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return IfpContractDetailsLocalServiceUtil.dynamicQuery((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return IfpContractDetailsLocalServiceUtil.dynamicQuery((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.stpl.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return IfpContractDetailsLocalServiceUtil.dynamicQueryCount((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return IfpContractDetailsLocalServiceUtil.dynamicQueryCount((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.stpl.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return IfpContractDetailsLocalServiceUtil.fetchIfpContractDetails(((Integer) arguments[0]).intValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return IfpContractDetailsLocalServiceUtil.getIfpContractDetails(((Integer) arguments[0]).intValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return IfpContractDetailsLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return IfpContractDetailsLocalServiceUtil.getIfpContractDetailses(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return IfpContractDetailsLocalServiceUtil.getIfpContractDetailsesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return IfpContractDetailsLocalServiceUtil.updateIfpContractDetails((com.stpl.app.model.IfpContractDetails) arguments[0]);
        }

        if (_methodName964.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes964, parameterTypes)) {
            return IfpContractDetailsLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName965.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes965, parameterTypes)) {
            IfpContractDetailsLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName970.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes970, parameterTypes)) {
            return IfpContractDetailsLocalServiceUtil.saveIfpDetailsAttached((java.util.List<java.lang.Object>) arguments[0],
                (java.lang.Object) arguments[1]);
        }

        if (_methodName971.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes971, parameterTypes)) {
            return IfpContractDetailsLocalServiceUtil.findIFP((java.lang.Object) arguments[0],
                (java.lang.Object) arguments[1],
                (java.util.List<java.lang.Integer>) arguments[2],
                (java.util.Map<java.lang.String, java.lang.Object>) arguments[3],
                ((Integer) arguments[4]).intValue(),
                ((Integer) arguments[5]).intValue(),
                (java.lang.String) arguments[6],
                (java.lang.String) arguments[7], (java.lang.Object) arguments[8]);
        }

        throw new UnsupportedOperationException();
    }
}
