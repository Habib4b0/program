package com.stpl.app.service;

import com.stpl.portal.service.InvokableLocalService;

/**
 * @author
 * @generated
 */
public class ImtdCfpDetailsLocalServiceClp implements ImtdCfpDetailsLocalService {
    private InvokableLocalService _invokableLocalService;
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
    private String _methodName16;
    private String[] _methodParameterTypes16;
    private String _methodName17;
    private String[] _methodParameterTypes17;
    private String _methodName19;
    private String[] _methodParameterTypes19;
    private String _methodName20;
    private String[] _methodParameterTypes20;
    private String _methodName21;
    private String[] _methodParameterTypes21;
    private String _methodName22;
    private String[] _methodParameterTypes22;
    private String _methodName23;
    private String[] _methodParameterTypes23;
    private String _methodName24;
    private String[] _methodParameterTypes24;
    private String _methodName25;
    private String[] _methodParameterTypes25;
    private String _methodName26;
    private String[] _methodParameterTypes26;
    private String _methodName27;
    private String[] _methodParameterTypes27;
    private String _methodName28;
    private String[] _methodParameterTypes28;
    private String _methodName29;
    private String[] _methodParameterTypes29;
    private String _methodName30;
    private String[] _methodParameterTypes30;
    private String _methodName31;
    private String[] _methodParameterTypes31;
    private String _methodName32;
    private String[] _methodParameterTypes32;
    private String _methodName33;
    private String[] _methodParameterTypes33;
    private String _methodName34;
    private String[] _methodParameterTypes34;
    private String _methodName35;
    private String[] _methodParameterTypes35;
    private String _methodName36;
    private String[] _methodParameterTypes36;
    private String _methodName37;
    private String[] _methodParameterTypes37;
    private String _methodName38;
    private String[] _methodParameterTypes38;
    private String _methodName39;
    private String[] _methodParameterTypes39;
    private String _methodName40;
    private String[] _methodParameterTypes40;

    public ImtdCfpDetailsLocalServiceClp(
        InvokableLocalService invokableLocalService) {
        _invokableLocalService = invokableLocalService;

        _methodName0 = "addImtdCfpDetails";

        _methodParameterTypes0 = new String[] {
                "com.stpl.app.model.ImtdCfpDetails"
            };

        _methodName1 = "createImtdCfpDetails";

        _methodParameterTypes1 = new String[] { "int" };

        _methodName2 = "deleteImtdCfpDetails";

        _methodParameterTypes2 = new String[] { "int" };

        _methodName3 = "deleteImtdCfpDetails";

        _methodParameterTypes3 = new String[] {
                "com.stpl.app.model.ImtdCfpDetails"
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

        _methodName10 = "fetchImtdCfpDetails";

        _methodParameterTypes10 = new String[] { "int" };

        _methodName11 = "getImtdCfpDetails";

        _methodParameterTypes11 = new String[] { "int" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getImtdCfpDetailses";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getImtdCfpDetailsesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateImtdCfpDetails";

        _methodParameterTypes15 = new String[] {
                "com.stpl.app.model.ImtdCfpDetails"
            };

        _methodName16 = "getBeanIdentifier";

        _methodParameterTypes16 = new String[] {  };

        _methodName17 = "setBeanIdentifier";

        _methodParameterTypes17 = new String[] { "java.lang.String" };

        _methodName19 = "deleteAll";

        _methodParameterTypes19 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.Object", "java.lang.Object",
                "java.lang.Object", "java.lang.Object"
            };

        _methodName20 = "updateOperation";

        _methodParameterTypes20 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.Object", "java.lang.Object",
                "java.lang.Object", "java.lang.Object"
            };

        _methodName21 = "updateForPopulate";

        _methodParameterTypes21 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.Object", "java.lang.Object",
                "java.lang.Object", "java.lang.Object"
            };

        _methodName22 = "updateForPopulateAll";

        _methodParameterTypes22 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.Object", "java.lang.Object",
                "java.lang.Object", "java.lang.Object"
            };

        _methodName23 = "getCompanyLazyList";

        _methodParameterTypes23 = new String[] {
                "int", "int", "java.lang.Object", "java.lang.Object",
                "java.lang.Object", "java.lang.String", "java.lang.String",
                "java.util.Map"
            };

        _methodName24 = "getSelectedCompanies";

        _methodParameterTypes24 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.String", "java.util.Map",
                "java.lang.String"
            };

        _methodName25 = "updateToCFPDetails";

        _methodParameterTypes25 = new String[] {
                "int", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.String", "java.lang.Object",
                "java.lang.Object", "java.lang.Object", "java.lang.Object"
            };

        _methodName26 = "insertTempCfpDetailsInADD";

        _methodParameterTypes26 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.Object", "java.lang.Object",
                "java.lang.Object", "java.lang.Object"
            };

        _methodName27 = "insertTempCfpDetailsInEdit";

        _methodParameterTypes27 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.Object", "java.lang.Object",
                "java.lang.Object", "java.lang.Object"
            };

        _methodName28 = "validateTempCFPDeatils";

        _methodParameterTypes28 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.Object", "java.lang.Object",
                "java.lang.Object", "java.lang.Object"
            };

        _methodName29 = "getTempCFPLazyList";

        _methodParameterTypes29 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.Object", "java.lang.Object",
                "java.lang.Object", "java.lang.Object", "java.util.Map"
            };

        _methodName30 = "updateAll";

        _methodParameterTypes30 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.Object", "java.lang.Object",
                "java.lang.Object", "java.lang.Object"
            };

        _methodName31 = "updateCFPDetails";

        _methodParameterTypes31 = new String[] {
                "int", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.String", "java.lang.Object",
                "java.lang.Object", "java.lang.Object", "java.lang.Object"
            };

        _methodName32 = "getTempCfpDetails";

        _methodParameterTypes32 = new String[] {
                "java.lang.String", "java.lang.String", "int", "int",
                "java.lang.String", "java.lang.String", "java.util.Map"
            };

        _methodName33 = "loadTempCompanydetails";

        _methodParameterTypes33 = new String[] {
                "java.util.List", "java.lang.Object"
            };

        _methodName34 = "massPopulate";

        _methodParameterTypes34 = new String[] {
                "java.util.List", "java.lang.Object"
            };

        _methodName35 = "massPopulateAll";

        _methodParameterTypes35 = new String[] {
                "java.util.List", "java.lang.Object"
            };

        _methodName36 = "saveCompany";

        _methodParameterTypes36 = new String[] {
                "java.util.List", "java.lang.Object"
            };

        _methodName37 = "deleteAll";

        _methodParameterTypes37 = new String[] {
                "java.util.List", "java.lang.Object"
            };

        _methodName38 = "updateAll";

        _methodParameterTypes38 = new String[] {
                "java.util.List", "java.lang.Object"
            };

        _methodName39 = "getOverlapedCompanies";

        _methodParameterTypes39 = new String[] {
                "java.util.List", "java.lang.Object"
            };

        _methodName40 = "getSelectedCompanies";

        _methodParameterTypes40 = new String[] {
                "java.lang.String", "java.lang.String", "int", "int",
                "java.lang.String", "java.lang.String", "boolean",
                "java.lang.Object", "java.util.Map", "boolean"
            };
    }

    @Override
    public com.stpl.app.model.ImtdCfpDetails addImtdCfpDetails(
        com.stpl.app.model.ImtdCfpDetails imtdCfpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName0,
                    _methodParameterTypes0,
                    new Object[] { ClpSerializer.translateInput(imtdCfpDetails) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.stpl.portal.kernel.exception.SystemException) {
                throw (com.stpl.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.stpl.app.model.ImtdCfpDetails) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public com.stpl.app.model.ImtdCfpDetails createImtdCfpDetails(
        int imtdCfpDetailsSid) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName1,
                    _methodParameterTypes1, new Object[] { imtdCfpDetailsSid });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.stpl.app.model.ImtdCfpDetails) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public com.stpl.app.model.ImtdCfpDetails deleteImtdCfpDetails(
        int imtdCfpDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName2,
                    _methodParameterTypes2, new Object[] { imtdCfpDetailsSid });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.stpl.portal.kernel.exception.PortalException) {
                throw (com.stpl.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.stpl.portal.kernel.exception.SystemException) {
                throw (com.stpl.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.stpl.app.model.ImtdCfpDetails) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public com.stpl.app.model.ImtdCfpDetails deleteImtdCfpDetails(
        com.stpl.app.model.ImtdCfpDetails imtdCfpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName3,
                    _methodParameterTypes3,
                    new Object[] { ClpSerializer.translateInput(imtdCfpDetails) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.stpl.portal.kernel.exception.SystemException) {
                throw (com.stpl.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.stpl.app.model.ImtdCfpDetails) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName4,
                    _methodParameterTypes4, new Object[] {  });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.stpl.portal.kernel.dao.orm.DynamicQuery) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName5,
                    _methodParameterTypes5,
                    new Object[] { ClpSerializer.translateInput(dynamicQuery) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.stpl.portal.kernel.exception.SystemException) {
                throw (com.stpl.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName6,
                    _methodParameterTypes6,
                    new Object[] {
                        ClpSerializer.translateInput(dynamicQuery),
                        
                    start,
                        
                    end
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.stpl.portal.kernel.exception.SystemException) {
                throw (com.stpl.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName7,
                    _methodParameterTypes7,
                    new Object[] {
                        ClpSerializer.translateInput(dynamicQuery),
                        
                    start,
                        
                    end,
                        
                    ClpSerializer.translateInput(orderByComparator)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.stpl.portal.kernel.exception.SystemException) {
                throw (com.stpl.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName8,
                    _methodParameterTypes8,
                    new Object[] { ClpSerializer.translateInput(dynamicQuery) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.stpl.portal.kernel.exception.SystemException) {
                throw (com.stpl.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Long) returnObj).longValue();
    }

    @Override
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.stpl.portal.kernel.dao.orm.Projection projection)
        throws com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName9,
                    _methodParameterTypes9,
                    new Object[] {
                        ClpSerializer.translateInput(dynamicQuery),
                        
                    ClpSerializer.translateInput(projection)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.stpl.portal.kernel.exception.SystemException) {
                throw (com.stpl.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Long) returnObj).longValue();
    }

    @Override
    public com.stpl.app.model.ImtdCfpDetails fetchImtdCfpDetails(
        int imtdCfpDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName10,
                    _methodParameterTypes10, new Object[] { imtdCfpDetailsSid });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.stpl.portal.kernel.exception.SystemException) {
                throw (com.stpl.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.stpl.app.model.ImtdCfpDetails) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public com.stpl.app.model.ImtdCfpDetails getImtdCfpDetails(
        int imtdCfpDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName11,
                    _methodParameterTypes11, new Object[] { imtdCfpDetailsSid });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.stpl.portal.kernel.exception.PortalException) {
                throw (com.stpl.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.stpl.portal.kernel.exception.SystemException) {
                throw (com.stpl.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.stpl.app.model.ImtdCfpDetails) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName12,
                    _methodParameterTypes12,
                    new Object[] { ClpSerializer.translateInput(primaryKeyObj) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.stpl.portal.kernel.exception.PortalException) {
                throw (com.stpl.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.stpl.portal.kernel.exception.SystemException) {
                throw (com.stpl.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.stpl.portal.model.PersistedModel) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.util.List<com.stpl.app.model.ImtdCfpDetails> getImtdCfpDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName13,
                    _methodParameterTypes13, new Object[] { start, end });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.stpl.portal.kernel.exception.SystemException) {
                throw (com.stpl.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.stpl.app.model.ImtdCfpDetails>) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public int getImtdCfpDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName14,
                    _methodParameterTypes14, new Object[] {  });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.stpl.portal.kernel.exception.SystemException) {
                throw (com.stpl.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Integer) returnObj).intValue();
    }

    @Override
    public com.stpl.app.model.ImtdCfpDetails updateImtdCfpDetails(
        com.stpl.app.model.ImtdCfpDetails imtdCfpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName15,
                    _methodParameterTypes15,
                    new Object[] { ClpSerializer.translateInput(imtdCfpDetails) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.stpl.portal.kernel.exception.SystemException) {
                throw (com.stpl.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.stpl.app.model.ImtdCfpDetails) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.lang.String getBeanIdentifier() {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName16,
                    _methodParameterTypes16, new Object[] {  });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.String) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        try {
            _invokableLocalService.invokeMethod(_methodName17,
                _methodParameterTypes17,
                new Object[] { ClpSerializer.translateInput(beanIdentifier) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.lang.Object deleteAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName19,
                    _methodParameterTypes19,
                    new Object[] {
                        ClpSerializer.translateInput(userId),
                        
                    ClpSerializer.translateInput(sessionId),
                        
                    ClpSerializer.translateInput(dateCreated),
                        
                    ClpSerializer.translateInput(operation),
                        
                    ClpSerializer.translateInput(future1),
                        
                    ClpSerializer.translateInput(future2),
                        
                    ClpSerializer.translateInput(future3),
                        
                    ClpSerializer.translateInput(future4)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Object) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.lang.Object updateOperation(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName20,
                    _methodParameterTypes20,
                    new Object[] {
                        ClpSerializer.translateInput(userId),
                        
                    ClpSerializer.translateInput(sessionId),
                        
                    ClpSerializer.translateInput(createdDate),
                        
                    ClpSerializer.translateInput(operation),
                        
                    ClpSerializer.translateInput(future1),
                        
                    ClpSerializer.translateInput(future2),
                        
                    ClpSerializer.translateInput(future3),
                        
                    ClpSerializer.translateInput(future4)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Object) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.lang.Object updateForPopulate(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName21,
                    _methodParameterTypes21,
                    new Object[] {
                        ClpSerializer.translateInput(userId),
                        
                    ClpSerializer.translateInput(sessionId),
                        
                    ClpSerializer.translateInput(createdDate),
                        
                    ClpSerializer.translateInput(operation),
                        
                    ClpSerializer.translateInput(future1),
                        
                    ClpSerializer.translateInput(future2),
                        
                    ClpSerializer.translateInput(future3),
                        
                    ClpSerializer.translateInput(future4)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Object) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.lang.Object updateForPopulateAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName22,
                    _methodParameterTypes22,
                    new Object[] {
                        ClpSerializer.translateInput(userId),
                        
                    ClpSerializer.translateInput(sessionId),
                        
                    ClpSerializer.translateInput(createdDate),
                        
                    ClpSerializer.translateInput(operation),
                        
                    ClpSerializer.translateInput(future1),
                        
                    ClpSerializer.translateInput(future2),
                        
                    ClpSerializer.translateInput(future3),
                        
                    ClpSerializer.translateInput(future4)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Object) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.util.List getCompanyLazyList(int start, int offset,
        java.lang.Object companyIdList, java.lang.Object operation,
        java.lang.Object searchValue, java.lang.String column,
        java.lang.String orderBy,
        java.util.Map<java.lang.Object, java.lang.Object> filterMap) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName23,
                    _methodParameterTypes23,
                    new Object[] {
                        start,
                        
                    offset,
                        
                    ClpSerializer.translateInput(companyIdList),
                        
                    ClpSerializer.translateInput(operation),
                        
                    ClpSerializer.translateInput(searchValue),
                        
                    ClpSerializer.translateInput(column),
                        
                    ClpSerializer.translateInput(orderBy),
                        
                    ClpSerializer.translateInput(filterMap)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.util.List getSelectedCompanies(int start, int offset,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String columnName, java.lang.String orderBy,
        java.util.Map<java.lang.Object, java.lang.Object> filterMap,
        java.lang.String operation) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName24,
                    _methodParameterTypes24,
                    new Object[] {
                        start,
                        
                    offset,
                        
                    ClpSerializer.translateInput(userId),
                        
                    ClpSerializer.translateInput(sessionId),
                        
                    ClpSerializer.translateInput(columnName),
                        
                    ClpSerializer.translateInput(orderBy),
                        
                    ClpSerializer.translateInput(filterMap),
                        
                    ClpSerializer.translateInput(operation)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.lang.Object updateToCFPDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName25,
                    _methodParameterTypes25,
                    new Object[] {
                        ifpMasterSystemId,
                        
                    ClpSerializer.translateInput(userId),
                        
                    ClpSerializer.translateInput(sessionId),
                        
                    ClpSerializer.translateInput(createdDate),
                        
                    ClpSerializer.translateInput(operation),
                        
                    ClpSerializer.translateInput(future1),
                        
                    ClpSerializer.translateInput(future2),
                        
                    ClpSerializer.translateInput(future3),
                        
                    ClpSerializer.translateInput(future4)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Object) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.lang.Object insertTempCfpDetailsInADD(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName26,
                    _methodParameterTypes26,
                    new Object[] {
                        ClpSerializer.translateInput(userId),
                        
                    ClpSerializer.translateInput(sessionId),
                        
                    ClpSerializer.translateInput(createdDate),
                        
                    ClpSerializer.translateInput(operation),
                        
                    ClpSerializer.translateInput(future1),
                        
                    ClpSerializer.translateInput(future2),
                        
                    ClpSerializer.translateInput(future3),
                        
                    ClpSerializer.translateInput(future4)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Object) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.lang.Object insertTempCfpDetailsInEdit(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName27,
                    _methodParameterTypes27,
                    new Object[] {
                        ClpSerializer.translateInput(userId),
                        
                    ClpSerializer.translateInput(sessionId),
                        
                    ClpSerializer.translateInput(createdDate),
                        
                    ClpSerializer.translateInput(operation),
                        
                    ClpSerializer.translateInput(future1),
                        
                    ClpSerializer.translateInput(future2),
                        
                    ClpSerializer.translateInput(future3),
                        
                    ClpSerializer.translateInput(future4)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Object) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.lang.Object validateTempCFPDeatils(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName28,
                    _methodParameterTypes28,
                    new Object[] {
                        ClpSerializer.translateInput(userId),
                        
                    ClpSerializer.translateInput(sessionId),
                        
                    ClpSerializer.translateInput(createdDate),
                        
                    ClpSerializer.translateInput(operation),
                        
                    ClpSerializer.translateInput(future1),
                        
                    ClpSerializer.translateInput(future2),
                        
                    ClpSerializer.translateInput(future3),
                        
                    ClpSerializer.translateInput(future4)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Object) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.lang.Object getTempCFPLazyList(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4,
        java.util.Map<java.lang.Object, java.lang.Object> filterMap) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName29,
                    _methodParameterTypes29,
                    new Object[] {
                        ClpSerializer.translateInput(userId),
                        
                    ClpSerializer.translateInput(sessionId),
                        
                    ClpSerializer.translateInput(createdDate),
                        
                    ClpSerializer.translateInput(operation),
                        
                    ClpSerializer.translateInput(future1),
                        
                    ClpSerializer.translateInput(future2),
                        
                    ClpSerializer.translateInput(future3),
                        
                    ClpSerializer.translateInput(future4),
                        
                    ClpSerializer.translateInput(filterMap)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Object) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.lang.Object updateAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object tempCfpSystemId,
        java.lang.Object deleteYesterdayRecordFlag, java.lang.Object future3,
        java.lang.Object future4) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName30,
                    _methodParameterTypes30,
                    new Object[] {
                        ClpSerializer.translateInput(userId),
                        
                    ClpSerializer.translateInput(sessionId),
                        
                    ClpSerializer.translateInput(dateCreated),
                        
                    ClpSerializer.translateInput(operation),
                        
                    ClpSerializer.translateInput(tempCfpSystemId),
                        
                    ClpSerializer.translateInput(deleteYesterdayRecordFlag),
                        
                    ClpSerializer.translateInput(future3),
                        
                    ClpSerializer.translateInput(future4)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Object) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.lang.Object updateCFPDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName31,
                    _methodParameterTypes31,
                    new Object[] {
                        ifpMasterSystemId,
                        
                    ClpSerializer.translateInput(userId),
                        
                    ClpSerializer.translateInput(sessionId),
                        
                    ClpSerializer.translateInput(createdDate),
                        
                    ClpSerializer.translateInput(operation),
                        
                    ClpSerializer.translateInput(future1),
                        
                    ClpSerializer.translateInput(future2),
                        
                    ClpSerializer.translateInput(future3),
                        
                    ClpSerializer.translateInput(future4)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Object) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.util.List getTempCfpDetails(java.lang.String userId,
        java.lang.String sessionId, int start, int offset,
        java.lang.String column, java.lang.String orederBy,
        java.util.Map<java.lang.Object, java.lang.Object> filterMap) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName32,
                    _methodParameterTypes32,
                    new Object[] {
                        ClpSerializer.translateInput(userId),
                        
                    ClpSerializer.translateInput(sessionId),
                        
                    start,
                        
                    offset,
                        
                    ClpSerializer.translateInput(column),
                        
                    ClpSerializer.translateInput(orederBy),
                        
                    ClpSerializer.translateInput(filterMap)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.lang.Boolean loadTempCompanydetails(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName33,
                    _methodParameterTypes33,
                    new Object[] {
                        ClpSerializer.translateInput(input),
                        
                    ClpSerializer.translateInput(future)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Boolean) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.lang.Boolean massPopulate(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName34,
                    _methodParameterTypes34,
                    new Object[] {
                        ClpSerializer.translateInput(input),
                        
                    ClpSerializer.translateInput(future)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Boolean) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.lang.Boolean massPopulateAll(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName35,
                    _methodParameterTypes35,
                    new Object[] {
                        ClpSerializer.translateInput(input),
                        
                    ClpSerializer.translateInput(future)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Boolean) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.lang.Boolean saveCompany(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName36,
                    _methodParameterTypes36,
                    new Object[] {
                        ClpSerializer.translateInput(input),
                        
                    ClpSerializer.translateInput(future)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Boolean) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.lang.Boolean deleteAll(java.util.List<java.lang.Object> input,
        java.lang.Object future) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName37,
                    _methodParameterTypes37,
                    new Object[] {
                        ClpSerializer.translateInput(input),
                        
                    ClpSerializer.translateInput(future)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Boolean) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.lang.Boolean updateAll(java.util.List<java.lang.Object> input,
        java.lang.Object future) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName38,
                    _methodParameterTypes38,
                    new Object[] {
                        ClpSerializer.translateInput(input),
                        
                    ClpSerializer.translateInput(future)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Boolean) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.lang.Object getOverlapedCompanies(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName39,
                    _methodParameterTypes39,
                    new Object[] {
                        ClpSerializer.translateInput(input),
                        
                    ClpSerializer.translateInput(future)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Object) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.util.List getSelectedCompanies(java.lang.String userID,
        java.lang.String sessionID, int start, int offset,
        java.lang.String column, java.lang.String orderBy, boolean flag,
        java.lang.Object future1,
        java.util.Map<java.lang.String, java.lang.Object> filterMap,
        boolean isCount) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName40,
                    _methodParameterTypes40,
                    new Object[] {
                        ClpSerializer.translateInput(userID),
                        
                    ClpSerializer.translateInput(sessionID),
                        
                    start,
                        
                    offset,
                        
                    ClpSerializer.translateInput(column),
                        
                    ClpSerializer.translateInput(orderBy),
                        
                    flag,
                        
                    ClpSerializer.translateInput(future1),
                        
                    ClpSerializer.translateInput(filterMap),
                        
                    isCount
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List) ClpSerializer.translateOutput(returnObj);
    }
}
