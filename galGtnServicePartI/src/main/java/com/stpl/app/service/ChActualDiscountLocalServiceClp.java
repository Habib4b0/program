package com.stpl.app.service;

import com.stpl.portal.service.InvokableLocalService;

/**
 * @author
 * @generated
 */
public class ChActualDiscountLocalServiceClp
    implements ChActualDiscountLocalService {
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

    public ChActualDiscountLocalServiceClp(
        InvokableLocalService invokableLocalService) {
        _invokableLocalService = invokableLocalService;

        _methodName0 = "addChActualDiscount";

        _methodParameterTypes0 = new String[] {
                "com.stpl.app.model.ChActualDiscount"
            };

        _methodName1 = "createChActualDiscount";

        _methodParameterTypes1 = new String[] {
                "com.stpl.app.service.persistence.ChActualDiscountPK"
            };

        _methodName2 = "deleteChActualDiscount";

        _methodParameterTypes2 = new String[] {
                "com.stpl.app.service.persistence.ChActualDiscountPK"
            };

        _methodName3 = "deleteChActualDiscount";

        _methodParameterTypes3 = new String[] {
                "com.stpl.app.model.ChActualDiscount"
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

        _methodName10 = "fetchChActualDiscount";

        _methodParameterTypes10 = new String[] {
                "com.stpl.app.service.persistence.ChActualDiscountPK"
            };

        _methodName11 = "getChActualDiscount";

        _methodParameterTypes11 = new String[] {
                "com.stpl.app.service.persistence.ChActualDiscountPK"
            };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getChActualDiscounts";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getChActualDiscountsCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateChActualDiscount";

        _methodParameterTypes15 = new String[] {
                "com.stpl.app.model.ChActualDiscount"
            };

        _methodName16 = "getBeanIdentifier";

        _methodParameterTypes16 = new String[] {  };

        _methodName17 = "setBeanIdentifier";

        _methodParameterTypes17 = new String[] { "java.lang.String" };
    }

    @Override
    public com.stpl.app.model.ChActualDiscount addChActualDiscount(
        com.stpl.app.model.ChActualDiscount chActualDiscount)
        throws com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName0,
                    _methodParameterTypes0,
                    new Object[] { ClpSerializer.translateInput(
                            chActualDiscount) });
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

        return (com.stpl.app.model.ChActualDiscount) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public com.stpl.app.model.ChActualDiscount createChActualDiscount(
        com.stpl.app.service.persistence.ChActualDiscountPK chActualDiscountPK) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName1,
                    _methodParameterTypes1,
                    new Object[] {
                        ClpSerializer.translateInput(chActualDiscountPK)
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

        return (com.stpl.app.model.ChActualDiscount) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public com.stpl.app.model.ChActualDiscount deleteChActualDiscount(
        com.stpl.app.service.persistence.ChActualDiscountPK chActualDiscountPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName2,
                    _methodParameterTypes2,
                    new Object[] {
                        ClpSerializer.translateInput(chActualDiscountPK)
                    });
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

        return (com.stpl.app.model.ChActualDiscount) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public com.stpl.app.model.ChActualDiscount deleteChActualDiscount(
        com.stpl.app.model.ChActualDiscount chActualDiscount)
        throws com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName3,
                    _methodParameterTypes3,
                    new Object[] { ClpSerializer.translateInput(
                            chActualDiscount) });
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

        return (com.stpl.app.model.ChActualDiscount) ClpSerializer.translateOutput(returnObj);
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
    public com.stpl.app.model.ChActualDiscount fetchChActualDiscount(
        com.stpl.app.service.persistence.ChActualDiscountPK chActualDiscountPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName10,
                    _methodParameterTypes10,
                    new Object[] {
                        ClpSerializer.translateInput(chActualDiscountPK)
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

        return (com.stpl.app.model.ChActualDiscount) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public com.stpl.app.model.ChActualDiscount getChActualDiscount(
        com.stpl.app.service.persistence.ChActualDiscountPK chActualDiscountPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName11,
                    _methodParameterTypes11,
                    new Object[] {
                        ClpSerializer.translateInput(chActualDiscountPK)
                    });
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

        return (com.stpl.app.model.ChActualDiscount) ClpSerializer.translateOutput(returnObj);
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
    public java.util.List<com.stpl.app.model.ChActualDiscount> getChActualDiscounts(
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

        return (java.util.List<com.stpl.app.model.ChActualDiscount>) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public int getChActualDiscountsCount()
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
    public com.stpl.app.model.ChActualDiscount updateChActualDiscount(
        com.stpl.app.model.ChActualDiscount chActualDiscount)
        throws com.stpl.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName15,
                    _methodParameterTypes15,
                    new Object[] { ClpSerializer.translateInput(
                            chActualDiscount) });
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

        return (com.stpl.app.model.ChActualDiscount) ClpSerializer.translateOutput(returnObj);
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
}
