package com.stpl.app.service.persistence;

public interface CfpModelFinder {
    public java.util.List findCfpModelV1(
        java.util.Map<java.lang.Object, java.lang.Object> cfp,
        java.lang.String orderByColumn, java.lang.Boolean sortOrder,
        java.lang.Object index, java.lang.Object next,
        java.util.Map<java.lang.Object, java.lang.Object> parameters,
        java.lang.String operation, java.lang.Object future,
        java.lang.Object future1);
}
