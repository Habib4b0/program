/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.glReserveMapping.tableLogic;

import com.stpl.app.accountclose.dto.GLReserveMappingDTO;
import com.stpl.app.accountclose.logic.GLReserveMappingLogic;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.List;
import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author mohamed.hameed
 */
public class GLRIndexTableLogic extends PageTableLogic {

    GLReserveMappingDTO binderDto;
    boolean search = false;
    Boolean isFlag;
    GLReserveMappingLogic logic = new GLReserveMappingLogic();
    
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(GLRIndexTableLogic.class);

    @Override
    public int getCount() {
        if (!search) {
            try {
                binderDto.setFilters(getFilters());
                return logic.loadIndexTableCount(binderDto, isFlag);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            binderDto.setStartIndex(start);
            binderDto.setEndIndex(offset);
            binderDto.setFilters(getFilters());
            binderDto.setSortedList(getSortByColumns());
            List list = logic.loadIndexTableData(binderDto, isFlag);
            return list;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return new ArrayList();
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        GLReserveMappingDTO dto = (GLReserveMappingDTO) object;
        ((ExtContainer<GLReserveMappingDTO>) container).addBean(dto);
        return dto;
    }

    /**
     * Method to setting up the data which we pass to logic
     *
     * @param binderDto
     */
    public boolean loadSetData(GLReserveMappingDTO binderDto, Boolean isReset, Boolean isFlag) {
        this.isFlag = isFlag;
        this.binderDto = binderDto;
        clearAll();
        setRequiredCount(true);
        search = isReset;
        setCurrentPage(1);
        return recordCount != 0;
    }
}
