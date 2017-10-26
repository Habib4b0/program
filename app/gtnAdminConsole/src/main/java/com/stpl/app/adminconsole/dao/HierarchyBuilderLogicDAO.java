package com.stpl.app.adminconsole.dao;

import java.util.List;

import com.stpl.app.model.HelperTable;
import com.stpl.app.model.HierarchyDefinition;
import com.stpl.app.model.HierarchyLevelDefinition;
import com.stpl.app.model.HierarchyLevelValues;
import com.stpl.app.model.ImtdLevelValues;
import com.stpl.app.model.RelationshipBuilder;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

// TODO: Auto-generated Javadoc
/**
 * The Interface HierarchyBuilderLogicDAO.
 *
 * @author sriram
 */
public interface HierarchyBuilderLogicDAO {

	/**
	 * Gets the hierachy definition list.
	 *
	 * @param query            the query
	 * @return the hierachy definition list
	 * @throws SystemException the system exception
	 */
	List getHierachyDefinitionList(DynamicQuery query) throws SystemException;
        
        int getHierachyDefinitionCount(DynamicQuery query) throws SystemException;

	/**
	 * Gets the hierarchy definition.
	 *
	 * @param systemId            the system id
	 * @return the hierarchy definition
	 * @throws PortalException the portal exception
	 * @throws SystemException the system exception
	 */
	HierarchyDefinition getHierarchyDefinition(int systemId) throws PortalException, SystemException;

	/**
	 * Adds the hierarchy definition.
	 *
	 * @param item            the item
	 * @return the hierarchy definition
	 * @throws SystemException the system exception
	 */
	HierarchyDefinition addHierarchyDefinition(HierarchyDefinition item) throws SystemException;

	/**
	 * Update hierarchy definition.
	 *
	 * @param item            the item
	 * @return the hierarchy definition
	 * @throws SystemException the system exception
	 */
	HierarchyDefinition updateHierarchyDefinition(HierarchyDefinition item) throws SystemException;

	/**
	 * Delete hierarchy definition.
	 *
	 * @param systemId            the system id
	 * @return the hierarchy definition
	 * @throws PortalException the portal exception
	 * @throws SystemException the system exception
	 */
	HierarchyDefinition deleteHierarchyDefinition(int systemId) throws PortalException, SystemException;

	/**
	 * Gets the hierarchylevel definition list.
	 *
	 * @param query            the query
	 * @return the hierarchylevel definition list
	 * @throws SystemException the system exception
	 */
	List getHierarchylevelDefinitionList(DynamicQuery query) throws SystemException;

	/**
	 * Gets the hierarchylevel definition.
	 *
	 * @param systemId            the system id
	 * @return the hierarchylevel definition
	 * @throws PortalException the portal exception
	 * @throws SystemException the system exception
	 */
	HierarchyLevelDefinition getHierarchylevelDefinition(int systemId) throws PortalException, SystemException;

	/**
	 * Adds the hierarchylevel definition.
	 *
	 * @param level            the level
	 * @return the hierarchy level definition
	 * @throws SystemException the system exception
	 */
	HierarchyLevelDefinition addHierarchylevelDefinition(HierarchyLevelDefinition level) throws SystemException;

	/**
	 * Update hierarchylevel definition.
	 *
	 * @param level            the level
	 * @return the hierarchy level definition
	 * @throws SystemException the system exception
	 */
	HierarchyLevelDefinition updateHierarchylevelDefinition(HierarchyLevelDefinition level) throws SystemException;

	/**
	 * Delete hierarchylevel definition.
	 *
	 * @param details            the details
	 * @throws SystemException the system exception
	 */
	void deleteHierarchylevelDefinition(HierarchyLevelDefinition details) throws SystemException;

	/**
	 * Delete hierarchylevel definition.
	 *
	 * @param systemId            the system id
	 * @return the hierarchy level definition
	 * @throws PortalException the portal exception
	 * @throws SystemException the system exception
	 */
	HierarchyLevelDefinition deleteHierarchylevelDefinition(int systemId) throws PortalException, SystemException;

	/**
	 * Gets the hierarchylevel values list.
	 *
	 * @param query            the query
	 * @return the hierarchylevel values list
	 * @throws SystemException the system exception
	 */
	List getHierarchylevelValuesList(DynamicQuery query) throws SystemException;

	/**
	 * Update hierarchylevel values.
	 *
	 * @param level            the level
	 * @return the hierarchy level values
	 * @throws SystemException the system exception
	 */
	HierarchyLevelValues updateHierarchylevelValues(HierarchyLevelValues level) throws SystemException;

	/**
	 * Adds the hierarchylevel values.
	 *
	 * @param level            the level
	 * @return the hierarchy level values
	 * @throws SystemException the system exception
	 */
	HierarchyLevelValues addHierarchylevelValues(HierarchyLevelValues level) throws SystemException;

	/**
	 * Delete hierarchylevel values.
	 *
	 * @param dto            the dto
	 * @throws SystemException the system exception
	 */
	void deleteHierarchylevelValues(HierarchyLevelValues dto) throws SystemException;

	/**
	 * Delete hierarchylevel values.
	 *
	 * @param systemId            the system id
	 * @throws PortalException the portal exception
	 * @throws SystemException the system exception
	 */
	void deleteHierarchylevelValues(int systemId) throws PortalException, SystemException;

	/**
	 * Gets the count.
	 *
	 * @param query            the query
	 * @return the count
	 * @throws SystemException the system exception
	 */
	int getCount(DynamicQuery query) throws SystemException;

	/**
	 * Gets the level list.
	 *
	 * @param query            the query
	 * @return the level list
	 * @throws SystemException the system exception
	 */
	List getLevelList(DynamicQuery query) throws SystemException;

	/**
	 * Delete levels.
	 *
	 * @param systemId            the system id
	 * @throws PortalException the portal exception
	 * @throws SystemException the system exception
	 */
	void deleteLevels(int systemId) throws PortalException, SystemException;

	/**
	 * Delete multiple levels.
	 *
	 * @return the list
	 * @throws SystemException the system exception
	 */
	List deleteMultipleLevels() throws SystemException;

	/**
	 * Adds the levels.
	 *
	 * @param level            the level
	 * @throws SystemException the system exception
	 */
	void addLevels(ImtdLevelValues level) throws SystemException;

	/**
	 * Gets the level values.
	 *
	 * @param tableName            the table name
	 * @param fieldName            the field name
	 * @param hierarchialDTO            the hierarchial dto
	 * @return the level values
	 * @throws SystemException the system exception
	 */
	List getLevelValues(String tableName, String fieldName, List hierarchialDTO) throws SystemException;

	/**
	 * Gets the category.
	 *
	 * @param categoryCode            the category code
	 * @return the category
	 * @throws PortalException the portal exception
	 * @throws SystemException the system exception
	 */
	HelperTable getCategory(int categoryCode) throws PortalException, SystemException;

	/**
	 * Gets the helper table list.
	 *
	 * @param query            the query
	 * @return the helper table list
	 * @throws SystemException the system exception
	 */
	List getHelperTableList(DynamicQuery query) throws SystemException;

	/**
	 * Gets the users list.
	 *
	 * @param query            the query
	 * @return the users list
	 * @throws SystemException the system exception
	 */
	List getUsersList(DynamicQuery query) throws SystemException;

	/**
	 * Gets the look up data.
	 *
	 * @param query            the query
	 * @return the look up data
	 * @throws SystemException the system exception
	 */
	List getLookUpData(DynamicQuery query) throws SystemException;

	/**
	 * Level check count.
	 *
	 * @param countQuery            the count query
	 * @return the int
	 * @throws SystemException the system exception
	 */
	int levelCheckCount(DynamicQuery countQuery) throws SystemException;

	/**
	 * Delete temp level values.
	 *
	 * @param tempLevelValues            the temp level values
	 * @throws SystemException the system exception
	 */
	void deleteTempLevelValues(List<ImtdLevelValues> tempLevelValues) throws SystemException;

    /**
     * Get HierarchyDefinition in List.
     *
     * @param query the query
     * @return the hierarchy definition list
     * @throws SystemException the system exception
     */		
         List<HierarchyDefinition> getHierarchyDefinitionList(DynamicQuery query) throws SystemException;
        
         /**
          * Get HierarchyDefinitionLevel in List.
          *
          * @param query the query
          * @return the hierarchy definition level list
          * @throws SystemException the system exception
          */             
         List<HierarchyLevelDefinition> getHierarchyDefinitionLevelList(DynamicQuery query) throws SystemException;
        
         /**
          * Get HierarchyDefinitionLevel in List.
          *
          * @param query the query
          * @return the hierarchy definition level value list
          * @throws SystemException the system exception
          */          
          List<HierarchyLevelValues> getHierarchyDefinitionLevelValueList(DynamicQuery query) throws SystemException;
         
          /**
           * Get RelationShipBuilder in List.
           *
           * @param query the query
           * @return the relationship builder list
           * @throws SystemException the system exception
           */                 
         List<RelationshipBuilder> getRelationshipBuilderList(DynamicQuery query) throws SystemException;
         
         /**
          * Gets the hierachy definition History list.
          *
          * @param query            the query
          * @return the hierachy definition History list
          * @throws SystemException the system exception
          */
	List getHierachyDefinitionHistoryList(DynamicQuery query) throws SystemException;
        
        
        /**
         * Gets the hist hierachy definition list.
         *
         * @param query the query
         * @return the hist hierachy definition list
         * @throws SystemException the system exception
         */
        List getHistHierachyDefinitionList(DynamicQuery query) throws SystemException;
        
        /**
         * Gets the hist hierarchylevel defn list.
         *
         * @param query the query
         * @return the hist hierarchylevel defn list
         * @throws SystemException the system exception
         */
        List getHistHierarchylevelDefnList(DynamicQuery query) throws SystemException;
        
        /**
         * Gets the hist hierarchylevel values list.
         *
         * @param query the query
         * @return the hist hierarchylevel values list
         * @throws SystemException the system exception
         */
        List getHistHierarchylevelValuesList(DynamicQuery query) throws SystemException;
        
        
        List getColumnValues(String tableName, String fieldName) throws SystemException;
        
        	/**
	 * Gets the Relation definition.
	 *
	 * @param systemId            the system id
	 * @return the hierarchy definition
	 * @throws PortalException the portal exception
	 * @throws SystemException the system exception
	 */
	RelationshipBuilder getRelationshipBuilder(int systemId) throws PortalException, SystemException;
}
