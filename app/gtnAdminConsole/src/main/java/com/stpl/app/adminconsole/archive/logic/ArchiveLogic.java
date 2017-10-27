/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.archive.logic;

import com.stpl.app.adminconsole.archive.dto.ArchiveDTO;
import com.stpl.app.adminconsole.dao.HierarchyBuilderLogicDAO;
import com.stpl.app.adminconsole.dao.impl.HierarchyBuilderLogicDAOImpl;
import com.stpl.app.adminconsole.itemgroup.util.CommonUtils;
import com.stpl.app.model.VwUserTables;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Nandhakumar
 */
public class ArchiveLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ArchiveLogic.class);

    /**
     * The dao.
     */
    private final HierarchyBuilderLogicDAO dao = new HierarchyBuilderLogicDAOImpl();

    /**
     * Gets the table name.
     *
     * @param tableName the table name
     * @return the table name
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public List<ArchiveDTO> getTableName(final String tableName) throws SystemException {
        LOGGER.debug("getTableName started with P1:String tableName=" + tableName);
        final List<ArchiveDTO> lookUpList = new ArrayList<>();
        final DynamicQuery vwUserTablesDynamicQuery = DynamicQueryFactoryUtil.forClass(VwUserTables.class);
        ArchiveDTO lookUp;
        if (tableName != null && StringUtils.isNotEmpty(tableName)) {
            final String table1 = tableName;
            final String table = table1.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            vwUserTablesDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.TABLE_NAME, table));

        }

        final List<VwUserTables> list = dao.getLookUpData(vwUserTablesDynamicQuery);
        for (final Iterator<VwUserTables> iterator = list.iterator(); iterator.hasNext();) {
            final VwUserTables dto = iterator.next();
            lookUp = new ArchiveDTO();
            lookUp.setTableName(dto.getTableName());
            lookUp.setFieldName(dto.getColumnName());
            lookUpList.add(lookUp);
        }
        LOGGER.debug("getTableName return List<TableFieldLookUpDTO> lookUpList=" + lookUpList.size());
        return lookUpList;
    }

    /**
     * Gets the level values.
     *
     * @param tableName the table name
     * @param fieldName the field name
     * @param hierDTO the hier dto
     * @return the level values
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public List<ArchiveDTO> getLevelValues(final String tableName, final String fieldName) throws SystemException {
        LOGGER.debug("getLevelValues started with P1:String tableName=" + tableName + " P2:String fieldName=" + fieldName + " P3:List hierDTO");
        final List<ArchiveDTO> levelList = new ArrayList<>();

        ArchiveDTO dto;
        final List valueList = dao.getColumnValues(tableName, fieldName);
        for (int i = 0; i < valueList.size(); i++) {
            dto = new ArchiveDTO();
            dto.setValue(String.valueOf(valueList.get(i)));
            levelList.add(dto);
        }
        LOGGER.debug("getLevelValues return List<LevelDTO> levelList=" + levelList.size());
        return levelList;
    }

}
