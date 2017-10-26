package com.stpl.app.service.persistence;

public interface NmSalesProjectionFinder {
    public java.util.List getSalesResult(java.lang.Object[] inputs);

    public java.util.List getAssumptionResult(java.util.List input,
        java.lang.String queryName);

    public java.util.List getSalesProjectionResults(java.lang.Object[] inputs);

    public java.util.List getSalesProjectionResultLevels(
        java.lang.Object[] inputs);

    public java.util.List getVarianceSales(int projectionId,
        java.lang.String frequency,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String actualsOrProjections, java.lang.String parentName,
        java.lang.String year, int levelNo, java.lang.String sales);
}
