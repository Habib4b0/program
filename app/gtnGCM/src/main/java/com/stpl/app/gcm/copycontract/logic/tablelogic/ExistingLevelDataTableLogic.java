/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.logic.tablelogic;

import com.stpl.app.gcm.copycontract.dto.CopyComponentDTO;
import com.stpl.app.gcm.copycontract.logic.CopyContractLogic;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.text.ParseException;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author srithar
 */
public class ExistingLevelDataTableLogic extends PageTableLogic {

    private boolean generate = false;
    private Integer levelNo;
    private final CopyContractLogic logic = new CopyContractLogic();
    private Integer id;
    private static final Logger LOGGER = LoggerFactory.getLogger(ExistingLevelDataTableLogic.class);

    /**
     * Record count logic
     *
     * @return integer as count
     */
    @Override
    public int getCount() {
        if (generate) {
            try {
                return Integer.parseInt(String.valueOf(logic.getComponentLevelData(levelNo, id, true, 0, 0)));
            } catch (ParseException ex) {
                LOGGER.error("",ex);
            }
        }
        return 0;
    }

    /**
     * Loading Grid
     *
     * @param start
     * @param offset
     * @return List of results
     */
    @Override
    public List loadData(int start, int offset) {
        List<CopyComponentDTO> resultList = null;
        try {
            resultList = (List<CopyComponentDTO>) logic.getComponentLevelData(levelNo, id, false, start, offset);
        } catch (ParseException ex) {
            LOGGER.error("",ex);
        }
        return resultList;
    }

    /**
     * Configure container
     *
     * @param object
     * @param container
     * @return Object
     */
    @Override
    public Object configureContainer(Object object, Container container) {
        CopyComponentDTO dto = (CopyComponentDTO) object;
        ((BeanItemContainer<CopyComponentDTO>) container).addBean(dto);
        return dto;
    }

    /**
     * Method to setting up the data which we pass to logic
     *
     * @param levelNo
     * @param isReset
     * @param id
     * @return
     */
    public boolean loadSetData(Integer levelNo, Integer id, boolean isReset) {
        this.levelNo = levelNo;
        this.id = id;
        clearAll();
        setRequiredCount(true);
        generate = isReset;
        setCurrentPage(1);
        return recordCount == 0;
    }
}
