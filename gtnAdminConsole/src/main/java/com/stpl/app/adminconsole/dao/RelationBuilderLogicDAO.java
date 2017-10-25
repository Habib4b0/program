/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.dao;

import java.util.List;

import com.stpl.app.model.HelperTable;
import com.stpl.app.model.RelationshipBuilder;
import com.stpl.app.model.RelationshipLevelDefinition;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

// TODO: Auto-generated Javadoc
/**
 * The Interface RelationBuilderLogicDAO.
 *
 * @author sriram
 */
public interface RelationBuilderLogicDAO {

    /**
     * Gets the hierarchy level definition list.
     *
     * @param query the query
     * @return the hierarchy level definition list
     * @throws Exception the exception
     */
    List getHierarchyLevelDefinitionList(DynamicQuery query) throws SystemException;
    
     /**
     * Gets the hierarchy level definition list.
     *
     * @param query the query
     * @return the hierarchy level definition list
     * @throws Exception the exception
     */
    List getHistHierarchyLevelDefinitionList(DynamicQuery query) throws SystemException;


    /**
     * Gets the relationship builder list.
     *
     * @param query the query
     * @return the relationship builder list
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws Exception the exception
     */
    List getRelationshipBuilderList(DynamicQuery query) throws SystemException;

    /**
     * Gets the hierachy definition list.
     *
     * @param query the query
     * @return the hierachy definition list
     * @throws Exception the exception
     */
    List getHierachyDefinitionList(DynamicQuery query) throws SystemException;

    /**
     * Gets the hierachy definition list.
     *
     * @param query the query
     * @return the hierachy definition list
     * @throws Exception the exception
     */
    List getHistHierachyDefinitionList(DynamicQuery query) throws SystemException;

    /**
     * Gets the category.
     *
     * @param systemId the systemId
     * @return the category
     * @throws Exception the exception
     */
    HelperTable getCategory(int systemId) throws PortalException, SystemException;

    /**
     * Gets the hierarchylevel values list.
     *
     * @param query the query
     * @return the hierarchylevel values list
     * @throws Exception the exception
     */
    List getHierarchylevelValuesList(DynamicQuery query) throws SystemException;
    
     /**
     * Gets the hist hierarchylevel values list.
     *
     * @param query the query
     * @return the hierarchylevel values list
     * @throws Exception the exception
     */
    List getHistHierarchylevelValuesList(DynamicQuery query) throws SystemException;

    /**
     * Gets the relationship level list.
     *
     * @param query the query
     * @return the relationship level list
     * @throws Exception the exception
     */
    List getRelationshipLevelList(DynamicQuery query) throws SystemException;

    /**
     * Gets the relationship level list for hist.
     *
     * @param query the query
     * @return the relationship level list
     * @throws Exception the exception
     */
    List getHistRelationshipLevelList(DynamicQuery query) throws SystemException;

    /**
     * Gets the relationship builder list.
     *
     * @param tableName the table name
     * @param columnName the column name
     * @param hierarchyDTO the hierarchy dto
     * @return the relationship builder list
     * @throws Exception the exception
     */
    List getRelationshipBuilderList(String tableName, String columnName, List hierarchyDTO) throws SystemException;

     /**
     * Gets the hist relationship builder list.
     *
     * @param tableName the table name
     * @param columnName the column name
     * @param hierarchyDTO the hierarchy dto
     * @return the relationship builder list
     * @throws Exception the exception
     */
    List getHistRelationshipBuilderList(String tableName, String columnName, List hierarchyDTO) throws SystemException;

    /**
     * Gets the relationship builder.
     *
     * @param systemId the system id
     * @return the relationship builder
     * @throws Exception the exception
     */
    RelationshipBuilder getRelationshipBuilder(int systemId) throws PortalException, SystemException;

    /**
     * Gets the relationship builder.
     *
     * @param systemId the system id
     * @return the relationship builder
     * @throws Exception the exception
     */
    List getHistRelationshipBuilder(DynamicQuery dynamicQuery) throws PortalException, SystemException;

    /**
     * Update relationship builder.
     *
     * @param build the build
     * @return the relationship builder
     * @throws Exception the exception
     */
    RelationshipBuilder updateRelationshipBuilder(RelationshipBuilder build) throws SystemException;

    /**
     * Adds the relationship builder.
     *
     * @param build the build
     * @return the relationship builder
     * @throws Exception the exception
     */
    RelationshipBuilder addRelationshipBuilder(RelationshipBuilder build) throws SystemException;

    /**
     * Delete relationship builder.
     *
     * @param systemId the system id
     * @return the relationship builder
     * @throws Exception the exception
     */
    RelationshipBuilder deleteRelationshipBuilder(int systemId) throws PortalException, SystemException;

    /**
     * Update relationship level definition.
     *
     * @param relationshipLevel the relationship level
     * @return the relationship level definition
     * @throws Exception the exception
     */
    RelationshipLevelDefinition updateRelationshipLevelDefinition(RelationshipLevelDefinition relationshipLevel) throws SystemException;

    /**
     * Adds the relationship level definition.
     *
     * @param relationshipLevel the relationship level
     * @return the relationship level definition
     * @throws Exception the exception
     */
    RelationshipLevelDefinition addRelationshipLevelDefinition(RelationshipLevelDefinition relationshipLevel) throws SystemException;

    /**
     * Delete relationship level definition.
     *
     * @param levelToDelete the level to delete
     * @throws Exception the exception
     */
    void deleteRelationshipLevelDefinition(RelationshipLevelDefinition levelToDelete) throws SystemException;

    /**
     * Gets the relationship builder window.
     *
     * @param dynamicQuery the dynamic query
     * @return the relationship builder window
     * @throws Exception the exception
     */
    List<RelationshipLevelDefinition> getRelationshipBuilderWindow(DynamicQuery dynamicQuery) throws SystemException;

    /**
     * Gets the relationship builder History list.
     *
     * @param query the query
     * @return the relationship builder history list
     * @throws Exception the exception
     */
    List getRelationshipBuilderHistoryList(DynamicQuery query) throws SystemException;
     /**
     * get the projection Cust count which is associated with item group
     *
     * @param query
     * @return
     * @throws SystemException
     */
    int getProjectionCustCount(final DynamicQuery query) throws SystemException;
     /**
     * get the projection Prod count which is associated with item group
     *
     * @param query
     * @return
     * @throws SystemException
     */
    int getProjectionProdCount(final DynamicQuery query) throws SystemException;
    
    /**
     * To get the Count of CCPMap using RelationShipLevelSid
     * 
     * @param query
     * @return
     * @throws SystemException 
     */
    int getProjectionCCPMapCount(final DynamicQuery query) throws SystemException;
    
        /**
     * Gets the relationship level list.
     *
     * @param query the query
     * @return the relationship level list
     * @throws Exception the exception
     */
    List getRelationshipBuilderListCount(DynamicQuery query) throws SystemException;
}
