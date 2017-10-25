package com.stpl.app.adminconsole.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.dao.HierarchyBuilderLogicDAO;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.HierarchyDefinition;
import com.stpl.app.model.HierarchyLevelDefinition;
import com.stpl.app.model.HierarchyLevelValues;
import com.stpl.app.model.ImtdLevelValues;
import com.stpl.app.model.RelationshipBuilder;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.HierarchyDefinitionLocalServiceUtil;
import com.stpl.app.service.HierarchyLevelDefinitionLocalServiceUtil;
import com.stpl.app.service.HierarchyLevelValuesLocalServiceUtil;
import com.stpl.app.service.HistHierarchyDefinitionLocalServiceUtil;
import com.stpl.app.service.HistHierarchyLevelDefnLocalServiceUtil;
import com.stpl.app.service.HistHierarchyLevelValuesLocalServiceUtil;
import com.stpl.app.service.ImtdLevelValuesLocalServiceUtil;
import com.stpl.app.service.RelationshipBuilderLocalServiceUtil;
import com.stpl.app.service.VwUserTablesLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.service.UserLocalServiceUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class HierarchyBuilderLogicDAOImpl.
 *
 * @author sriram
 */
public class HierarchyBuilderLogicDAOImpl implements HierarchyBuilderLogicDAO {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(HierarchyBuilderLogicDAOImpl.class);

    /**
     * To get Hierarchy Definition List from HierarchyDefinition table based on
     * the query.
     *
     * @param query the query
     * @return the hierachy definition list
     * @throws SystemException the system exception
     */
    public List getHierachyDefinitionList(final DynamicQuery query) throws SystemException {
        LOGGER.debug("In query-getCompanyGroupList started with P1:DynamicQuery query");
        return HierarchyDefinitionLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * To get a particular HierarchyDefinition corresponding to the systemId.
     *
     * @param systemId the system id
     * @return the hierarchy definition
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public HierarchyDefinition getHierarchyDefinition(final int systemId) throws PortalException, SystemException {
        LOGGER.debug("In query-getHierarchyDefinition started with P1:int systemId=" + systemId);
        return HierarchyDefinitionLocalServiceUtil.getHierarchyDefinition(systemId);
    }

    /**
     * To add a Hierarchy Definition in HierarchyDefinition Table.
     *
     * @param item the item
     * @return the hierarchy definition
     * @throws SystemException the system exception
     */
    public HierarchyDefinition addHierarchyDefinition(final HierarchyDefinition item) throws SystemException {
        LOGGER.debug("In query-addHierarchyDefinition started with P1:HierarchyDefinition item");
        return HierarchyDefinitionLocalServiceUtil.addHierarchyDefinition(item);
    }

    /**
     * To update the HierarchyDefinition in the HierarchyDefinition table.
     *
     * @param item the item
     * @return the hierarchy definition
     * @throws SystemException the system exception
     */
    public HierarchyDefinition updateHierarchyDefinition(final HierarchyDefinition item) throws SystemException {
        LOGGER.debug("In query-updateHierarchyDefinition started with P1:HierarchyDefinition item");
        return HierarchyDefinitionLocalServiceUtil.updateHierarchyDefinition(item);
    }

    /**
     * To delete the HierarchyDefinition in the HierarchyDefinition table.
     *
     * @param systemId the system id
     * @return the hierarchy definition
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public HierarchyDefinition deleteHierarchyDefinition(final int systemId) throws PortalException, SystemException {
        LOGGER.debug("In query-deleteHierarchyDefinition started with P1:int systemId=" + systemId);
        return HierarchyDefinitionLocalServiceUtil.deleteHierarchyDefinition(systemId);
    }

    /**
     * To get the list of HierarchyLevelDefinition based on the query.
     *
     * @param query the query
     * @return the hierarchylevel definition list
     * @throws SystemException the system exception
     */
    public List getHierarchylevelDefinitionList(final DynamicQuery query) throws SystemException {
        LOGGER.debug("In query-getHierarchylevelDefinitionList started with P1:DynamicQuery query");
        return HierarchyLevelDefinitionLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * To get the HierarchyLevelDefinition corresponding to the particular
     * system id.
     *
     * @param systemId the system id
     * @return the hierarchylevel definition
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public HierarchyLevelDefinition getHierarchylevelDefinition(final int systemId) throws PortalException, SystemException {
        LOGGER.debug("In query-getHierarchylevelDefinition started with P1:int systemId=" + systemId);
        return HierarchyLevelDefinitionLocalServiceUtil.getHierarchyLevelDefinition(systemId);
    }

    /**
     * To update the HierarchyLevelDefinition in the HierarchyLevelDefinition
     * Table.
     *
     * @param level the level
     * @return the hierarchy level definition
     * @throws SystemException the system exception
     */
    public HierarchyLevelDefinition updateHierarchylevelDefinition(final HierarchyLevelDefinition level) throws SystemException {
        LOGGER.debug("In query-updateHierarchylevelDefinition started with P1:HierarchyLevelDefinition level");
        return HierarchyLevelDefinitionLocalServiceUtil.updateHierarchyLevelDefinition(level);
    }

    /**
     * To add a HierarchyLevelDefinition in the HierarchyLevelDefinition Table.
     *
     * @param level the level
     * @return the hierarchy level definition
     * @throws SystemException the system exception
     */
    public HierarchyLevelDefinition addHierarchylevelDefinition(final HierarchyLevelDefinition level) throws SystemException {
        LOGGER.debug("In query-addHierarchylevelDefinition started with P1:HierarchyLevelDefinition level");
        return HierarchyLevelDefinitionLocalServiceUtil.addHierarchyLevelDefinition(level);
    }

    /**
     * To delete a HierarchyLevelDefinition corresponding to the from the
     * HierarchyLevelDefinition table.
     *
     * @param systemId the system id
     * @return the hierarchy level definition
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public HierarchyLevelDefinition deleteHierarchylevelDefinition(final int systemId) throws PortalException, SystemException {
        LOGGER.debug("In query-deleteHierarchylevelValues started with P1:int systemId=" + systemId);
        return HierarchyLevelDefinitionLocalServiceUtil.deleteHierarchyLevelDefinition(systemId);
    }

    /**
     * To delete a HierarchyLevelDefinition from the HierarchyLevelDefinition
     * table.
     *
     * @param details the details
     * @throws SystemException the system exception
     */
    public void deleteHierarchylevelDefinition(final HierarchyLevelDefinition details) throws SystemException {
        LOGGER.debug("In query-addLevels started with P1:TempLevelValues level");
        HierarchyLevelDefinitionLocalServiceUtil.deleteHierarchyLevelDefinition(details);
    }

    /**
     * To get the HierarchyLevelValues List based on the query.
     *
     * @param query the query
     * @return the hierarchylevel values list
     * @throws SystemException the system exception
     */
    public List getHierarchylevelValuesList(final DynamicQuery query) throws SystemException {
        LOGGER.debug("In query-getCompanyGroupList started with P1:DynamicQuery query");
        return HierarchyLevelValuesLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * To delete data from HierarchyLevelValues for the corresponding systemId.
     *
     * @param systemId the system id
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public void deleteHierarchylevelValues(final int systemId) throws PortalException, SystemException {
        LOGGER.debug("In query-deleteHierarchylevelValues started with P1:int systemId=" + systemId);
        HierarchyLevelValuesLocalServiceUtil.deleteHierarchyLevelValues(systemId);
    }

    /**
     * To delete data from HierarchyLevelValues table.
     *
     * @param dto the dto
     * @throws SystemException the system exception
     */
    public void deleteHierarchylevelValues(final HierarchyLevelValues dto) throws SystemException {
        LOGGER.debug("In query-deleteHierarchylevelValues started with P1:HierarchyLevelValues dto");
        HierarchyLevelValuesLocalServiceUtil.deleteHierarchyLevelValues(dto);
    }

    /**
     * To update data in HierarchyLevelValues table.
     *
     * @param level the level
     * @return the hierarchy level values
     * @throws SystemException the system exception
     */
    public HierarchyLevelValues updateHierarchylevelValues(final HierarchyLevelValues level) throws SystemException {
        LOGGER.debug("In query-updateHierarchylevelValues started with P1:HierarchyLevelValues level");
        return HierarchyLevelValuesLocalServiceUtil.updateHierarchyLevelValues(level);
    }

    /**
     * To add data in HierarchyLevelValues table.
     *
     * @param level the level
     * @return the hierarchy level values
     * @throws SystemException the system exception
     */
    public HierarchyLevelValues addHierarchylevelValues(final HierarchyLevelValues level) throws SystemException {
        LOGGER.debug("In query-addHierarchylevelValues started with P1:HierarchyLevelValues level");
        return HierarchyLevelValuesLocalServiceUtil.addHierarchyLevelValues(level);
    }

    /**
     * To get the count from TempLevelValues table.
     *
     * @param query the query
     * @return the count
     * @throws SystemException the system exception
     */
    public int getCount(final DynamicQuery query) throws SystemException {
        LOGGER.debug("In query-getCount started with P1:DynamicQuery query");
        return (int) ImtdLevelValuesLocalServiceUtil.dynamicQueryCount(query);
    }

    /**
     * To get the list of levels from TempLevelValues table.
     *
     * @param query the query
     * @return the level list
     * @throws SystemException the system exception
     */
    public List getLevelList(final DynamicQuery query) throws SystemException {
        LOGGER.debug("In query-getLevelList started with P1:DynamicQuery query");
        return ImtdLevelValuesLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * To delete the level Values based on the system Id from TempLevelValues
     * table.
     *
     * @param systemId the system id
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public void deleteLevels(final int systemId) throws PortalException, SystemException {
        LOGGER.debug("In query-deleteLevels started with P1:int systemId=" + systemId);
        ImtdLevelValuesLocalServiceUtil.deleteImtdLevelValues(systemId);
    }

    /**
     * To add levels in TempLevelValues table.
     *
     * @param level the level
     * @throws SystemException the system exception
     */
    public void addLevels(final ImtdLevelValues level) throws SystemException {
        LOGGER.debug("In query-addLevels started with P1:TempLevelValues level");
        ImtdLevelValuesLocalServiceUtil.addImtdLevelValues(level);
    }

    /**
     * To delete Multiple levels in TempLevelValues table.
     *
     * @return the list
     * @throws SystemException the system exception
     */
    public List deleteMultipleLevels() throws SystemException {
        LOGGER.debug("In query-deleteMultipleLevels started");
        final int count = ImtdLevelValuesLocalServiceUtil.getImtdLevelValuesesCount();
        return ImtdLevelValuesLocalServiceUtil.getImtdLevelValueses(0, count);
    }

    /**
     * To get the level values based on the passed parameters from
     * RelationshipBuilder table.
     *
     * @param tableName the table name
     * @param fieldName the field name
     * @param hierarchialDTO the hierarchial dto
     * @return the level values
     * @throws SystemException the system exception
     */
    public List getLevelValues(final String tableName, final String fieldName, final List hierarchialDTO) throws SystemException {
        LOGGER.debug("In query-getLevelValues started with P1:String tableName=" + tableName + " P2:String fieldName=" + fieldName + " P3:List hierarchialDTO");
        return RelationshipBuilderLocalServiceUtil.findByTableName(tableName, fieldName, hierarchialDTO);
    }

    /**
     * To get the categories for the corresponding category code from
     * HelperTable.
     *
     * @param categoryCode the category code
     * @return the category
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public HelperTable getCategory(final int categoryCode) throws PortalException, SystemException {
        LOGGER.debug("In query-getCategory started with P1:int categoryCode=" + categoryCode);
        return HelperTableLocalServiceUtil.getHelperTable(categoryCode);
    }

    /**
     * To get the list for the corresponding query from HelperTable.
     *
     * @param query the query
     * @return the helper table list
     * @throws SystemException the system exception
     */
    public List getHelperTableList(final DynamicQuery query) throws SystemException {
        LOGGER.debug("In query-getHelperTableList started with P1:DynamicQuery query");
        return HelperTableLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * To get the list of users from User Table.
     *
     * @param query the query
     * @return the users list
     * @throws SystemException the system exception
     */
    public List getUsersList(final DynamicQuery query) throws SystemException {
        LOGGER.debug("In query-getUsersList started with P1:DynamicQuery query");
        return UserLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * To get the lookup data from VwUserTables view.
     *
     * @param query the query
     * @return the look up data
     * @throws SystemException the system exception
     */
    public List getLookUpData(final DynamicQuery query) throws SystemException {
        LOGGER.debug("In query-getLookUpData started with P1:DynamicQuery query");
        return VwUserTablesLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Get to check the level count.
     *
     * @param countQuery the count query
     * @return the int
     * @throws SystemException the system exception
     */
    public int levelCheckCount(final DynamicQuery countQuery) throws SystemException {
        return (int) ImtdLevelValuesLocalServiceUtil.dynamicQueryCount(countQuery);

    }

    /**
     * Delete the level values.
     *
     * @param tempLevelValues the temp level values
     * @throws SystemException the system exception
     */
    public void deleteTempLevelValues(final List<ImtdLevelValues> tempLevelValues) throws SystemException {
        for (final Iterator<ImtdLevelValues> iterator = tempLevelValues.iterator(); iterator.hasNext();) {
            final ImtdLevelValues dto = iterator.next();
            ImtdLevelValuesLocalServiceUtil.deleteImtdLevelValues(dto);
        }
    }

    /**
     * Get HierarchyDefinition in List.
     *
     * @param query the query
     * @return the hierarchy definition list
     * @throws SystemException the system exception
     */
    public List<HierarchyDefinition> getHierarchyDefinitionList(final DynamicQuery query) throws SystemException {
        return HierarchyDefinitionLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Get HierarchyDefinitionLevel in List.
     *
     * @param query the query
     * @return the hierarchy definition level list
     * @throws SystemException the system exception
     */
    public List<HierarchyLevelDefinition> getHierarchyDefinitionLevelList(final DynamicQuery query) throws SystemException {
        return HierarchyDefinitionLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Get HierarchyDefinitionLevel in List.
     *
     * @param query the query
     * @return the hierarchy definition level value list
     * @throws SystemException the system exception
     */
    public List<HierarchyLevelValues> getHierarchyDefinitionLevelValueList(final DynamicQuery query) throws SystemException {
        return HierarchyDefinitionLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Get RelationShipBuilder in List.
     *
     * @param query the query
     * @return the relationship builder list
     * @throws SystemException the system exception
     */
    public List<RelationshipBuilder> getRelationshipBuilderList(final DynamicQuery query) throws SystemException {
        return HierarchyDefinitionLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Get HierarchyDefinitionHistory in List.
     *
     * @param query the query
     * @return the hierachy definition history list
     * @throws SystemException the system exception
     */
    public List getHierachyDefinitionHistoryList(final DynamicQuery query) throws SystemException {
        return HistHierarchyDefinitionLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * (non-Javadoc)
     * getHistHierachyDefinitionList(com.stpl.portal.kernel.dao.orm.DynamicQuery)
     */
    public List getHistHierachyDefinitionList(final DynamicQuery query) throws SystemException {
        return HistHierarchyDefinitionLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * (non-Javadoc)
     * getHistHierarchylevelDefnList(com.stpl.portal.kernel.dao.orm.DynamicQuery)
     */
    public List getHistHierarchylevelDefnList(final DynamicQuery query) throws SystemException {
        return HistHierarchyLevelDefnLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * getHistHierarchylevelValuesList(com.stpl.portal.kernel.dao.orm.DynamicQuery)
     */
    public List getHistHierarchylevelValuesList(final DynamicQuery query) throws SystemException {
        return HistHierarchyLevelValuesLocalServiceUtil.dynamicQuery(query);
    }

    public List getColumnValues(String tableName, String fieldName) throws SystemException {
        return RelationshipBuilderLocalServiceUtil.findByTableName(tableName, fieldName);
    }

    /**
     * To get a List of HierachyDefinition based on the query count.
     *
     * @param query -dynamic query
     * @throws SystemException
     * @table CompanyGroup
     */
    public int getHierachyDefinitionCount(final DynamicQuery query) throws SystemException {
        return (int) HierarchyDefinitionLocalServiceUtil.dynamicQueryCount(query);
    }

    /**
     * Used to retrieve the hierarchy for this RB
     *
     * @param systemId
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public RelationshipBuilder getRelationshipBuilder(final int systemId) throws SystemException, PortalException {
        LOGGER.debug("In query-getRelationshipBuilder started with P1:HierarchyLevelDefinition level");
        return RelationshipBuilderLocalServiceUtil.getRelationshipBuilder(systemId);
    }
}
