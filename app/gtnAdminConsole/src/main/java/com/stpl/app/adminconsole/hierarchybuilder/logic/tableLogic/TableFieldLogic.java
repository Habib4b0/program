/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.hierarchybuilder.logic.tableLogic;

import com.stpl.app.adminconsole.hierarchybuilder.dto.TableFieldLookUpDTO;
import com.stpl.app.adminconsole.hierarchybuilder.logic.HierarchyBuilderLogic;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.drools.core.util.StringUtils;
import org.jboss.logging.Logger;

public class TableFieldLogic extends PageTableLogic {

    boolean loadData = false;
    boolean isFieldTable = false;
    String indicator = StringUtils.EMPTY;
    TableFieldLookUpDTO tableFieldLookUpDTO;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(TableFieldLogic.class);

    @Override
    public int getCount() {
        int count = 0;
        if (loadData) {
            try {
                if (ConstantsUtils.FIELD.equals(indicator)) {
                    count = new HierarchyBuilderLogic().getFieldNameCount(tableFieldLookUpDTO.getTableName(), getFilters());
                } else if (ConstantsUtils.TABLE.equals(indicator)) {
                    count = new HierarchyBuilderLogic().getTableCount(tableFieldLookUpDTO, getFilters());
                } else if (ConstantsUtils.DISPLAY.equals(indicator)) {
                    count = new HierarchyBuilderLogic().getLevelValuesCount(tableFieldLookUpDTO.getTableName(), tableFieldLookUpDTO.getFieldName(), getFilters());
                }
            } catch (SystemException ex) {

                LOGGER.error(ex);
            } catch (Exception ex) {

                LOGGER.error(ex);
            }
        }
        LOGGER.debug("Count -------------------->> " + count);
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<TableFieldLookUpDTO> resultList = new ArrayList<>();
        try {
            if (ConstantsUtils.FIELD.equals(indicator)) {
                resultList = new HierarchyBuilderLogic().getTableName(tableFieldLookUpDTO.getTableName(), start, offset, getFilters(), getSortByColumns());
            } else if (ConstantsUtils.TABLE.equals(indicator)) {
                resultList = new HierarchyBuilderLogic().getUniqueTable(tableFieldLookUpDTO, start, offset, getFilters(), getSortByColumns());
            } else if (ConstantsUtils.DISPLAY.equals(indicator)) {
                resultList = new HierarchyBuilderLogic().getLevelValues(tableFieldLookUpDTO.getTableName(), tableFieldLookUpDTO.getFieldName(), start, offset, getFilters(), getSortByColumns());
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        TableFieldLookUpDTO dto = (TableFieldLookUpDTO) object;
        ((BeanItemContainer<TableFieldLookUpDTO>) container).addBean(dto);
        return dto;
    }

    public boolean fireSetData(TableFieldLookUpDTO tableFieldLookUpDTO, boolean isReset, String tableIndicator) {
        this.tableFieldLookUpDTO = tableFieldLookUpDTO;
        this.isFieldTable = isFieldTable;
        this.indicator = tableIndicator;
        clearAll();
        setRequiredCount(true);
        loadData = !isReset;
        setCurrentPage(1);
        return getRecordCount() != 0;
    }

}
