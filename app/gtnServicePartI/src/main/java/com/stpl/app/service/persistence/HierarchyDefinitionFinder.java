package com.stpl.app.service.persistence;

public interface HierarchyDefinitionFinder {
    public java.util.List getHierarchyGroup(java.lang.String hierarchyName,
        java.lang.String hierarchyType, java.lang.String customerOrProduct,
        java.lang.String action);

    public java.util.List getLevelsFromHierarchy(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List getHierarchySystemId(int relationshipLevelId);
}
