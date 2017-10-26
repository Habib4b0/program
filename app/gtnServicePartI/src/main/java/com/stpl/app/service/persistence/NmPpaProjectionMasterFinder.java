package com.stpl.app.service.persistence;

public interface NmPpaProjectionMasterFinder {
    public java.util.List getPPAProjectionList(java.lang.Integer projectionId,
        int levelNo, java.lang.String parent, boolean last, int startIndex,
        int endIndex, boolean isCount, java.lang.String levelName);

    public void setPPAProjectionMassUpdate(java.lang.Object priceCap,
        int startQuater, int endQuater, int startYear, int endYear,
        int projectionId, java.lang.String parent, java.lang.String levelValue);

    public java.util.List getPPAResults(java.lang.Integer projectionId,
        int levelNo, java.lang.String parent, boolean last, int startIndex,
        int endIndex, boolean isCount, java.util.List<java.lang.String> input,
        java.lang.String levelName);

    public java.util.List getLevelValues(int projectionId, int levelNo,
        java.lang.String parent);

    public java.util.List getProductHierarchyLevel(int projectionId,
        int levelNo, java.lang.String parent);
}
