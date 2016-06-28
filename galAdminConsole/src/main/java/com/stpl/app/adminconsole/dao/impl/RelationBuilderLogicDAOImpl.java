/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.dao.impl;

import java.util.List;

import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.dao.RelationBuilderLogicDAO;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.RelationshipBuilder;
import com.stpl.app.model.RelationshipLevelDefinition;
import com.stpl.app.service.CcpMapLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.HierarchyDefinitionLocalServiceUtil;
import com.stpl.app.service.HierarchyLevelDefinitionLocalServiceUtil;
import com.stpl.app.service.HierarchyLevelValuesLocalServiceUtil;
import com.stpl.app.service.HistHierarchyDefinitionLocalServiceUtil;
import com.stpl.app.service.HistHierarchyLevelDefnLocalServiceUtil;
import com.stpl.app.service.HistHierarchyLevelValuesLocalServiceUtil;
import com.stpl.app.service.HistRelationshipBuilderLocalServiceUtil;
import com.stpl.app.service.HistRelationshipLevelDefnLocalServiceUtil;
import com.stpl.app.service.ProjectionCustHierarchyLocalServiceUtil;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.app.service.ProjectionProdHierarchyLocalServiceUtil;
import com.stpl.app.service.RelationshipBuilderLocalServiceUtil;
import com.stpl.app.service.RelationshipLevelDefinitionLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

// TODO: Auto-generated Javadoc
/**
 * The Class RelationBuilderLogicDAOImpl.
 *
 * @author sriram
 */
public class RelationBuilderLogicDAOImpl implements RelationBuilderLogicDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(RelationBuilderLogicDAOImpl.class);

	/**
	 * To get eh List of HierarchyLevelDefinition from HierarchyLevelDefinition
	 * table based on the query.
	 *
	 * @param query            the query
	 * @return the hierarchy level definition list
	 * @throws SystemException the system exception
	 */
	public List getHierarchyLevelDefinitionList(final DynamicQuery query) throws SystemException {
		LOGGER.info("In query-getHierarchyLevelDefinitionList started with P1:DynamicQuery query");
		return HierarchyLevelDefinitionLocalServiceUtil.dynamicQuery(query);
	}
        
        /**
         * To get eh List of HierarchyLevelDefinition from HierarchyLevelDefinition
         * table based on the query.
         *
         * @param query            the query
         * @return the hierarchy level definition list
         * @throws SystemException the system exception
         */
	public List getHistHierarchyLevelDefinitionList(final DynamicQuery query) throws SystemException {
		LOGGER.info("In query-getHierarchyLevelDefinitionList started with P1:DynamicQuery query");
		return HistHierarchyLevelDefnLocalServiceUtil.dynamicQuery(query);
	}

	/**
	 * To get the list of RelationshipBuilder based on the query from
	 * RelationshipBuilder table.
	 *
	 * @param query            the query
	 * @return the relationship builder list
	 * @throws SystemException the system exception
	 */
	public List getRelationshipBuilderList(final DynamicQuery query) throws SystemException {
		LOGGER.info("In query-getRelationshipBuilderList started with P1:DynamicQuery query");
		return RelationshipBuilderLocalServiceUtil.dynamicQuery(query);
	}

	/**
	 * To get the List of HierarchyDefinition for the query from
	 * HierarchyDefinition table.
	 *
	 * @param query            the query
	 * @return the hierachy definition list
	 * @throws SystemException the system exception
	 */
	public List getHierachyDefinitionList(final DynamicQuery query) throws SystemException {
		LOGGER.info("In query-getHierachyDefinitionList started with P1:DynamicQuery query");
		return HierarchyDefinitionLocalServiceUtil.dynamicQuery(query);
	}
        
        /**
         * To get the List of Hist HierarchyDefinition for the query from
         * HierarchyDefinition table.
         *
         * @param query            the query
         * @return the hierachy definition list
         * @throws SystemException the system exception
         */
	public List getHistHierachyDefinitionList(final DynamicQuery query) throws SystemException {
		LOGGER.info("In query-getHierachyDefinitionList started with P1:DynamicQuery query");
		return HistHierarchyDefinitionLocalServiceUtil.dynamicQuery(query);
	}

	/**
	 * To get categories from HelperTable.
	 *
	 * @param systemId the system id
	 * @return the category
	 * @throws PortalException the portal exception
	 * @throws SystemException the system exception
	 */
	public HelperTable getCategory(final int systemId) throws PortalException, SystemException {
		LOGGER.info("In query-getCategory started with P1:int systemId=" + systemId);
		return HelperTableLocalServiceUtil.getHelperTable(systemId);
	}

	/**
	 * To get the HierarchyLevelValues from HierarchyLevelValues table.
	 *
	 * @param query            the query
	 * @return the hierarchylevel values list
	 * @throws SystemException the system exception
	 */
	public List getHierarchylevelValuesList(final DynamicQuery query) throws SystemException {
		LOGGER.info("In query-getHierarchylevelValuesList started with P1:DynamicQuery query");
		return HierarchyLevelValuesLocalServiceUtil.dynamicQuery(query);
	}

        /**
         * To get the HierarchyLevelValues from HierarchyLevelValues table.
         *
         * @param query            the query
         * @return the hierarchylevel values list
         * @throws SystemException the system exception
         */
	public List getHistHierarchylevelValuesList(final DynamicQuery query) throws SystemException {
		LOGGER.info("In query-getHierarchylevelValuesList started with P1:DynamicQuery query");
		return HistHierarchyLevelValuesLocalServiceUtil.dynamicQuery(query);
	}

        
	/**
	 * To get the RelationshipBuilder list from RelationshipBuilder table.
	 *
	 * @param tableName            the table name
	 * @param columnName            the column name
	 * @param hierarchyDTO            the hierarchy dto
	 * @return the relationship builder list
	 * @throws SystemException the system exception
	 */
	public List getRelationshipBuilderList(final String tableName, final String columnName, final List hierarchyDTO) throws SystemException {
		LOGGER.info("In query-getRelationshipBuilderList started with P1:String tableName=" + tableName + " P2:String columnName=" + columnName + " P3:List hierarchyDTO");
		return RelationshipBuilderLocalServiceUtil.findByTableName(tableName, columnName, hierarchyDTO);
	}
        
        /**
         * To get the RelationshipBuilder list from RelationshipBuilder table.
         *
         * @param tableName            the table name
         * @param columnName            the column name
         * @param hierarchyDTO            the hierarchy dto
         * @return the relationship builder list
         * @throws SystemException the system exception
         */
	public List getHistRelationshipBuilderList(final String tableName, final String columnName, final List hierarchyDTO) throws SystemException {
		LOGGER.info("In query-getRelationshipBuilderList started with P1:String tableName=" + tableName + " P2:String columnName=" + columnName + " P3:List hierarchyDTO");
		return HistRelationshipBuilderLocalServiceUtil.findByTableName(tableName, columnName, hierarchyDTO);
                
	}

	/**
	 * To get the RelationshipBuilder for the corresponding systemId.
	 *
	 * @param systemId            the system id
	 * @return the relationship builder
	 * @throws PortalException the portal exception
	 * @throws SystemException the system exception
	 */
	public RelationshipBuilder getRelationshipBuilder(final int systemId) throws PortalException, SystemException {
		LOGGER.info("In query-getRelationshipBuilder started with P1:int systemId=" + systemId);
		return RelationshipBuilderLocalServiceUtil.getRelationshipBuilder(systemId);
	}
        	
	        /**
	         * To get the HistRelationshipBuilder for the corresponding systemId.
	         *
	         * @param dynamicQuery the dynamic query
	         * @return the relationship builder
	         * @throws PortalException the portal exception
	         * @throws SystemException the system exception
	         */
	public List getHistRelationshipBuilder(final DynamicQuery dynamicQuery) throws PortalException, SystemException {
		LOGGER.info("In query-getRelationshipBuilder started with P1:int systemId=" + dynamicQuery);
		return HistRelationshipBuilderLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

	/**
	 * To update date in RelationshipBuilder table.
	 *
	 * @param build            the build
	 * @return the relationship builder
	 * @throws SystemException the system exception
	 */
	public RelationshipBuilder updateRelationshipBuilder(final RelationshipBuilder build) throws SystemException {
		LOGGER.info("In query-updateRelationshipBuilder started with P1:RelationshipBuilder build");
		return RelationshipBuilderLocalServiceUtil.updateRelationshipBuilder(build);
	}

	/**
	 * To add data in RelationshipBuilder table.
	 *
	 * @param build            the build
	 * @return the relationship builder
	 * @throws SystemException the system exception
	 */
	public RelationshipBuilder addRelationshipBuilder(final RelationshipBuilder build) throws SystemException {
		LOGGER.info("In query-addRelationshipBuilder started with P1:RelationshipBuilder build");
		return RelationshipBuilderLocalServiceUtil.addRelationshipBuilder(build);
	}

	/**
	 * To delete RelationshipBuilder from RelationshipBuilder table.
	 *
	 * @param systemId            the system id
	 * @return the relationship builder
	 * @throws PortalException the portal exception
	 * @throws SystemException the system exception
	 */
	public RelationshipBuilder deleteRelationshipBuilder(final int systemId) throws PortalException, SystemException {
		LOGGER.info("In query-deleteRelationshipBuilder started with P1:int systemId=" + systemId);
		return RelationshipBuilderLocalServiceUtil.deleteRelationshipBuilder(systemId);
	}

	/**
	 * To get RelationshipLevelDefinition list from RelationshipLevelDefinition
	 * table.
	 *
	 * @param query            the query
	 * @return the relationship level list
	 * @throws SystemException the system exception
	 */
	public List getRelationshipLevelList(final DynamicQuery query) throws SystemException {
		LOGGER.info("In query-getRelationshipLevelList started with P1:DynamicQuery query");
		return RelationshipLevelDefinitionLocalServiceUtil.dynamicQuery(query);
	}
        
	/**
	 * To get RelationshipLevelDefinition list from HistRelationshipLevelDefinition
	 * table.
	 *
	 * @param query            the query
	 * @return the relationship level list
	 * @throws SystemException the system exception
	 */
	public List getHistRelationshipLevelList(final DynamicQuery query) throws SystemException {
		LOGGER.info("In query-getRelationshipLevelList started with P1:DynamicQuery query");
		return HistRelationshipLevelDefnLocalServiceUtil.dynamicQuery(query);
	}

	/**
	 * To update data in RelationshipLevelDefinition table.
	 *
	 * @param relationshipLevel            the relationship level
	 * @return the relationship level definition
	 * @throws SystemException the system exception
	 */
	public RelationshipLevelDefinition updateRelationshipLevelDefinition(final RelationshipLevelDefinition relationshipLevel) throws SystemException {
		LOGGER.info("In query-updateRelationshipLevelDefinition started with P1:RelationshipLevelDefinition relationshipLevel");
		return RelationshipLevelDefinitionLocalServiceUtil.updateRelationshipLevelDefinition(relationshipLevel);
	}

	/**
	 * To add data in RelationshipLevelDefinition table.
	 *
	 * @param relationshipLevel            the relationship level
	 * @return the relationship level definition
	 * @throws SystemException the system exception
	 */
	public RelationshipLevelDefinition addRelationshipLevelDefinition(final RelationshipLevelDefinition relationshipLevel) throws SystemException {
		LOGGER.info("In query-RelationshipLevelDefinition started with P1:RelationshipLevelDefinition relationshipLevel");
		return RelationshipLevelDefinitionLocalServiceUtil.addRelationshipLevelDefinition(relationshipLevel);
	}

	/**
	 * To delete data from RelationshipLevelDefinition table.
	 *
	 * @param levelToDelete            the level to delete
	 * @throws SystemException the system exception
	 */
	public void deleteRelationshipLevelDefinition(final RelationshipLevelDefinition levelToDelete) throws SystemException {
		LOGGER.info("In query-deleteRelationshipLevelDefinition started with P1:RelationshipLevelDefinition levelToDelete");
		RelationshipLevelDefinitionLocalServiceUtil.deleteRelationshipLevelDefinition(levelToDelete);
	}

	/**
	 * To get RelationaShip Builder window.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the relationship builder window
	 * @throws SystemException the system exception
	 */
	public List<RelationshipLevelDefinition> getRelationshipBuilderWindow(final DynamicQuery dynamicQuery) throws SystemException {
		return RelationshipLevelDefinitionLocalServiceUtil.dynamicQuery(dynamicQuery);
	}
        
        /**
         * To get the RelationshipBuilderHIstory list.
         *
         * @param query            the dynamic Query 
         * @return the relationship builder history list
         * @throws SystemException the system exception
         */
	   public List getRelationshipBuilderHistoryList(final DynamicQuery query) throws SystemException {
        LOGGER.info("In query-getRelationshipBuilderHistoryList ");
        return HistRelationshipBuilderLocalServiceUtil.dynamicQuery(query);
    }

    public int getProjectionCustCount(DynamicQuery query) throws SystemException {
        return (int) ProjectionCustHierarchyLocalServiceUtil.dynamicQueryCount(query);
    }

    public int getProjectionProdCount(DynamicQuery query) throws SystemException {
        return (int) ProjectionProdHierarchyLocalServiceUtil.dynamicQueryCount(query);
    }
    /**
     * To get the Count of CCPMap using RelationShipLevelSid
     * 
     * @param query
     * @return
     * @throws SystemException 
     */
    public int getProjectionCCPMapCount(DynamicQuery query) throws SystemException {
        return (int) CcpMapLocalServiceUtil.dynamicQueryCount(query);
    }

    /**
	 * To get RelationshipLevelDefinition list from RelationshipLevelDefinition
	 * table.
	 *
	 * @param query            the query
	 * @return the relationship level list
	 * @throws SystemException the system exception
	 */
	public List getRelationshipBuilderListCount(final DynamicQuery query) throws SystemException {
		LOGGER.info("In query-getRelationshipLevelList started with P1:DynamicQuery query");
		return ProjectionMasterLocalServiceUtil.dynamicQuery(query);
	}
}
