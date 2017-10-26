package com.stpl.app.service.persistence;

public interface ForecastingMasterFinder {
    public java.util.List getResults(java.lang.String fileType,
        java.lang.String country, java.lang.String fileName,
        java.lang.String type, java.lang.String version,
        java.lang.String forecastYear, java.lang.String fromDate,
        java.lang.String toDate);

    public java.util.List getDetailsResults(java.lang.String fileName,
        java.lang.String version, java.lang.String fileType,
        java.lang.String country, int year);

    public java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2);

    public java.lang.Object executeBulkUpdateQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2);

    public java.lang.Object executeUpdateQuery(java.util.List<?> nmSalesList,
        java.lang.Object udc1, java.lang.Object udc2, java.lang.Object udc3);

    public java.util.List getFileSearchResults(java.lang.String query);
}
