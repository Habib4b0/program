package com.stpl.app.service;

import com.stpl.portal.service.InvokableLocalService;

/**
 * @author
 * @generated
 */
public class ForecastingMasterLocalServiceClp
    implements ForecastingMasterLocalService {
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

    public ForecastingMasterLocalServiceClp(
        InvokableLocalService invokableLocalService) {
        _invokableLocalService = invokableLocalService;

        _methodName0 = "addForecastingMaster";

        _methodParameterTypes0 = new String[] {
                "com.stpl.app.model.ForecastingMaster"
            };

        _methodName1 = "createForecastingMaster";

        _methodParameterTypes1 = new String[] { "int" };

        _methodName2 = "deleteForecastingMaster";

        _methodParameterTypes2 = new String[] { "int" };

        _methodName3 = "deleteForecastingMaster";

        _methodParameterTypes3 = new String[] {
                "com.stpl.app.model.ForecastingMaster"
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

        _methodName10 = "fetchForecastingMaster";

        _methodParameterTypes10 = new String[] { "int" };

        _methodName11 = "getForecastingMaster";

        _methodParameterTypes11 = new String[] { "int" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getForecastingMasters";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getForecastingMastersCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateForecastingMaster";

        _methodParameterTypes15 = new String[] {
                "com.stpl.app.model.ForecastingMaster"
            };

        _methodName16 = "getBeanIdentifier";

        _methodParameterTypes16 = new String[] {  };

        _methodName17 = "setBeanIdentifier";

        _methodParameterTypes17 = new String[] { "java.lang.String" };

        _methodName19 = "getResults";

        _methodParameterTypes19 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.String"
            };

        _methodName20 = "getDetailsResults";

        _methodParameterTypes20 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.lang.String", "int"
            };

        _methodName21 = "executeSelectQuery";

        _methodParameterTypes21 = new String[] {
                "java.lang.String", "java.lang.Object", "java.lang.Object"
            };

        _methodName22 = "executeBulkUpdateQuery";

        _methodParameterTypes22 = new String[] {
                "java.lang.String", "java.lang.Object", "java.lang.Object"
            };

        _methodName23 = "executeUpdateQuery";

        _methodParameterTypes23 = new String[] {
                "java.util.List", "java.lang.Object", "java.lang.Object",
                "java.lang.Object"
            };

        _methodName24 = "getFileSearchResults";

        _methodParameterTypes24 = new String[] { "java.lang.String" };
    }

    @Override
    public com.stpl.app.model.ForecastingMaster addForecastingMaster(
        com.stpl.app.model.ForecastingMaster forecastingMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName0,
                    _methodParameterTypes0,
                    new Object[] { ClpSerializer.translateInput(
                            forecastingMaster) });
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

        return (com.stpl.app.model.ForecastingMaster) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public com.stpl.app.model.ForecastingMaster createForecastingMaster(
        int forecastMasterSid) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName1,
                    _methodParameterTypes1, new Object[] { forecastMasterSid });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.stpl.app.model.ForecastingMaster) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public com.stpl.app.model.ForecastingMaster deleteForecastingMaster(
        int forecastMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName2,
                    _methodParameterTypes2, new Object[] { forecastMasterSid });
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

        return (com.stpl.app.model.ForecastingMaster) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public com.stpl.app.model.ForecastingMaster deleteForecastingMaster(
        com.stpl.app.model.ForecastingMaster forecastingMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName3,
                    _methodParameterTypes3,
                    new Object[] { ClpSerializer.translateInput(
                            forecastingMaster) });
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

        return (com.stpl.app.model.ForecastingMaster) ClpSerializer.translateOutput(returnObj);
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
    public com.stpl.app.model.ForecastingMaster fetchForecastingMaster(
        int forecastMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName10,
                    _methodParameterTypes10, new Object[] { forecastMasterSid });
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

        return (com.stpl.app.model.ForecastingMaster) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public com.stpl.app.model.ForecastingMaster getForecastingMaster(
        int forecastMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName11,
                    _methodParameterTypes11, new Object[] { forecastMasterSid });
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

        return (com.stpl.app.model.ForecastingMaster) ClpSerializer.translateOutput(returnObj);
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
    public java.util.List<com.stpl.app.model.ForecastingMaster> getForecastingMasters(
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

        return (java.util.List<com.stpl.app.model.ForecastingMaster>) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public int getForecastingMastersCount()
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
    public com.stpl.app.model.ForecastingMaster updateForecastingMaster(
        com.stpl.app.model.ForecastingMaster forecastingMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName15,
                    _methodParameterTypes15,
                    new Object[] { ClpSerializer.translateInput(
                            forecastingMaster) });
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

        return (com.stpl.app.model.ForecastingMaster) ClpSerializer.translateOutput(returnObj);
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
    public java.util.List getResults(java.lang.String fileType,
        java.lang.String country, java.lang.String fileName,
        java.lang.String type, java.lang.String version,
        java.lang.String forecastYear, java.lang.String fromDate,
        java.lang.String toDate) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName19,
                    _methodParameterTypes19,
                    new Object[] {
                        ClpSerializer.translateInput(fileType),
                        
                    ClpSerializer.translateInput(country),
                        
                    ClpSerializer.translateInput(fileName),
                        
                    ClpSerializer.translateInput(type),
                        
                    ClpSerializer.translateInput(version),
                        
                    ClpSerializer.translateInput(forecastYear),
                        
                    ClpSerializer.translateInput(fromDate),
                        
                    ClpSerializer.translateInput(toDate)
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
    public java.util.List getDetailsResults(java.lang.String fileName,
        java.lang.String version, java.lang.String fileType,
        java.lang.String country, int year) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName20,
                    _methodParameterTypes20,
                    new Object[] {
                        ClpSerializer.translateInput(fileName),
                        
                    ClpSerializer.translateInput(version),
                        
                    ClpSerializer.translateInput(fileType),
                        
                    ClpSerializer.translateInput(country),
                        
                    year
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
    public java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName21,
                    _methodParameterTypes21,
                    new Object[] {
                        ClpSerializer.translateInput(query),
                        
                    ClpSerializer.translateInput(udc1),
                        
                    ClpSerializer.translateInput(udc2)
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
    public java.lang.Object executeBulkUpdateQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName22,
                    _methodParameterTypes22,
                    new Object[] {
                        ClpSerializer.translateInput(query),
                        
                    ClpSerializer.translateInput(udc1),
                        
                    ClpSerializer.translateInput(udc2)
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
    public java.lang.Object executeUpdateQuery(java.util.List<?> nmSalesList,
        java.lang.Object udc1, java.lang.Object udc2, java.lang.Object udc3) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName23,
                    _methodParameterTypes23,
                    new Object[] {
                        ClpSerializer.translateInput(nmSalesList),
                        
                    ClpSerializer.translateInput(udc1),
                        
                    ClpSerializer.translateInput(udc2),
                        
                    ClpSerializer.translateInput(udc3)
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
    public java.util.List getFileSearchResults(java.lang.String query) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName24,
                    _methodParameterTypes24,
                    new Object[] { ClpSerializer.translateInput(query) });
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
