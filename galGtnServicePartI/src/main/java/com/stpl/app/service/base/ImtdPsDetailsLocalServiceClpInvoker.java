package com.stpl.app.service.base;

import com.stpl.app.service.ImtdPsDetailsLocalServiceUtil;

import java.util.Arrays;

/**
 * @author
 * @generated
 */
public class ImtdPsDetailsLocalServiceClpInvoker {
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
    private String _methodName975;
    private String[] _methodParameterTypes975;
    private String _methodName976;
    private String[] _methodParameterTypes976;
    private String _methodName977;
    private String[] _methodParameterTypes977;
    private String _methodName978;
    private String[] _methodParameterTypes978;
    private String _methodName979;
    private String[] _methodParameterTypes979;
    private String _methodName980;
    private String[] _methodParameterTypes980;
    private String _methodName981;
    private String[] _methodParameterTypes981;

    public ImtdPsDetailsLocalServiceClpInvoker() {
        _methodName0 = "addImtdPsDetails";

        _methodParameterTypes0 = new String[] { "com.stpl.app.model.ImtdPsDetails" };

        _methodName1 = "createImtdPsDetails";

        _methodParameterTypes1 = new String[] { "int" };

        _methodName2 = "deleteImtdPsDetails";

        _methodParameterTypes2 = new String[] { "int" };

        _methodName3 = "deleteImtdPsDetails";

        _methodParameterTypes3 = new String[] { "com.stpl.app.model.ImtdPsDetails" };

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

        _methodName10 = "fetchImtdPsDetails";

        _methodParameterTypes10 = new String[] { "int" };

        _methodName11 = "getImtdPsDetails";

        _methodParameterTypes11 = new String[] { "int" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getImtdPsDetailses";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getImtdPsDetailsesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateImtdPsDetails";

        _methodParameterTypes15 = new String[] {
                "com.stpl.app.model.ImtdPsDetails"
            };

        _methodName964 = "getBeanIdentifier";

        _methodParameterTypes964 = new String[] {  };

        _methodName965 = "setBeanIdentifier";

        _methodParameterTypes965 = new String[] { "java.lang.String" };

        _methodName970 = "deleteAll";

        _methodParameterTypes970 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.Object", "java.lang.Object",
                "java.lang.Object", "java.lang.Object"
            };

        _methodName971 = "updateOperation";

        _methodParameterTypes971 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.Object", "java.lang.Object",
                "java.lang.Object", "java.lang.Object"
            };

        _methodName972 = "updateForPopulate";

        _methodParameterTypes972 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.Object", "java.lang.Object",
                "java.lang.Object", "java.lang.Object"
            };

        _methodName973 = "updateForPopulateAll";

        _methodParameterTypes973 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.Object", "java.lang.Object",
                "java.lang.Object", "java.lang.Object"
            };

        _methodName974 = "getIFPLazyList";

        _methodParameterTypes974 = new String[] {
                "int", "int", "java.lang.Object", "java.lang.Object",
                "java.lang.Object"
            };

        _methodName975 = "updateToPsDetails";

        _methodParameterTypes975 = new String[] {
                "int", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.String", "java.lang.Object",
                "java.lang.Object", "java.lang.Object", "java.lang.Object"
            };

        _methodName976 = "insertTempPsDetailsInADD";

        _methodParameterTypes976 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.Object", "java.lang.Object",
                "java.lang.Object", "java.lang.Object"
            };

        _methodName977 = "insertTempPsDetailsInEdit";

        _methodParameterTypes977 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.Object", "java.lang.Object",
                "java.lang.Object", "java.lang.Object"
            };

        _methodName978 = "validateTempPSDeatils";

        _methodParameterTypes978 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.Object", "java.lang.Object",
                "java.lang.Object", "java.lang.Object"
            };

        _methodName979 = "getTempCFPLazyList";

        _methodParameterTypes979 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.Object", "java.lang.Object",
                "java.lang.Object", "java.lang.Object"
            };

        _methodName980 = "getItemPriceDetails";

        _methodParameterTypes980 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.String", "java.util.Map"
            };

        _methodName981 = "executeUpdateQuery";

        _methodParameterTypes981 = new String[] {
                "java.lang.String", "java.lang.Object", "java.lang.Object"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.addImtdPsDetails((com.stpl.app.model.ImtdPsDetails) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.createImtdPsDetails(((Integer) arguments[0]).intValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.deleteImtdPsDetails(((Integer) arguments[0]).intValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.deleteImtdPsDetails((com.stpl.app.model.ImtdPsDetails) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.dynamicQuery((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.dynamicQuery((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.dynamicQuery((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.stpl.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.dynamicQueryCount((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.dynamicQueryCount((com.stpl.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.stpl.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.fetchImtdPsDetails(((Integer) arguments[0]).intValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.getImtdPsDetails(((Integer) arguments[0]).intValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.getImtdPsDetailses(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.getImtdPsDetailsesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.updateImtdPsDetails((com.stpl.app.model.ImtdPsDetails) arguments[0]);
        }

        if (_methodName964.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes964, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName965.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes965, parameterTypes)) {
            ImtdPsDetailsLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName970.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes970, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.deleteAll((java.lang.String) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                (java.lang.Object) arguments[4],
                (java.lang.Object) arguments[5],
                (java.lang.Object) arguments[6], (java.lang.Object) arguments[7]);
        }

        if (_methodName971.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes971, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.updateOperation((java.lang.String) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                (java.lang.Object) arguments[4],
                (java.lang.Object) arguments[5],
                (java.lang.Object) arguments[6], (java.lang.Object) arguments[7]);
        }

        if (_methodName972.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes972, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.updateForPopulate((java.lang.String) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                (java.lang.Object) arguments[4],
                (java.lang.Object) arguments[5],
                (java.lang.Object) arguments[6], (java.lang.Object) arguments[7]);
        }

        if (_methodName973.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes973, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.updateForPopulateAll((java.lang.String) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                (java.lang.Object) arguments[4],
                (java.lang.Object) arguments[5],
                (java.lang.Object) arguments[6], (java.lang.Object) arguments[7]);
        }

        if (_methodName974.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes974, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.getIFPLazyList(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.Object) arguments[2],
                (java.lang.Object) arguments[3], (java.lang.Object) arguments[4]);
        }

        if (_methodName975.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes975, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.updateToPsDetails(((Integer) arguments[0]).intValue(),
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                (java.lang.String) arguments[4],
                (java.lang.Object) arguments[5],
                (java.lang.Object) arguments[6],
                (java.lang.Object) arguments[7], (java.lang.Object) arguments[8]);
        }

        if (_methodName976.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes976, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.insertTempPsDetailsInADD((java.lang.String) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                (java.lang.Object) arguments[4],
                (java.lang.Object) arguments[5],
                (java.lang.Object) arguments[6], (java.lang.Object) arguments[7]);
        }

        if (_methodName977.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes977, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.insertTempPsDetailsInEdit((java.lang.String) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                (java.lang.Object) arguments[4],
                (java.lang.Object) arguments[5],
                (java.lang.Object) arguments[6], (java.lang.Object) arguments[7]);
        }

        if (_methodName978.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes978, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.validateTempPSDeatils((java.lang.String) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                (java.lang.Object) arguments[4],
                (java.lang.Object) arguments[5],
                (java.lang.Object) arguments[6], (java.lang.Object) arguments[7]);
        }

        if (_methodName979.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes979, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.getTempCFPLazyList((java.lang.String) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                (java.lang.Object) arguments[4],
                (java.lang.Object) arguments[5],
                (java.lang.Object) arguments[6], (java.lang.Object) arguments[7]);
        }

        if (_methodName980.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes980, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.getItemPriceDetails(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                (java.lang.String) arguments[4],
                (java.lang.String) arguments[5],
                (java.util.Map<java.lang.String, java.lang.Object>) arguments[6]);
        }

        if (_methodName981.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes981, parameterTypes)) {
            return ImtdPsDetailsLocalServiceUtil.executeUpdateQuery((java.lang.String) arguments[0],
                (java.lang.Object) arguments[1], (java.lang.Object) arguments[2]);
        }

        throw new UnsupportedOperationException();
    }
}
