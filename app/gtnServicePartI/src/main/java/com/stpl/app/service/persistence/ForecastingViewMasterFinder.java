package com.stpl.app.service.persistence;

public interface ForecastingViewMasterFinder {
    public java.util.List findViewByName(java.lang.String viewName,
        java.lang.String forecastType, java.lang.String userId,
        java.lang.String viewType);

    public java.util.List findViewByNameForChannels(java.lang.String viewName,
        java.lang.String forecastType, java.lang.String userId,
        java.lang.String viewType);
}
