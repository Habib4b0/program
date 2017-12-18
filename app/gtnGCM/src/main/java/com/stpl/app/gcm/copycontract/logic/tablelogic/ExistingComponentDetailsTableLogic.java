/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.logic.tablelogic;

import com.stpl.app.gcm.copycontract.dto.ExistingComponentDTO;
import com.stpl.app.gcm.copycontract.logic.CopyContractLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;
/**
 *
 * @author srithar
 */
public class ExistingComponentDetailsTableLogic extends PageTableLogic {

    boolean generate = false;
    String componentType;
    CopyContractLogic logic = new CopyContractLogic();
    ExistingComponentDTO dto;
    List newInput = new ArrayList();
    private static final Logger LOGGER = Logger.getLogger(ExistingComponentDetailsTableLogic.class);

    /**
     * Record count logic
     *
     * @return integer as count
     */
    @Override
    public int getCount() {
        if (generate) {
            try {
                return Integer.valueOf(String.valueOf(logic.getExistingDetailsData(componentType, dto, true, 0, 0)));
            } catch (ParseException ex) {
             LOGGER.error(ex);
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
        List<ExistingComponentDTO> resultList = null;
        try {
            resultList = (List<ExistingComponentDTO>) logic.getExistingDetailsData(componentType, dto, false, start, offset);
        } catch (ParseException ex) {
            LOGGER.error(ex);
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
        ExistingComponentDTO existingComponentDTO = (ExistingComponentDTO) object;
        ((BeanItemContainer<ExistingComponentDTO>) container).addBean(existingComponentDTO);
        return existingComponentDTO;
    }

    /**
     * Method to setting up the data which we pass to logic
     *
     * @param componentType
     * @param componentInnerType
     * @param componentSelection
     * @param isReset
     * @return
     */
    public boolean loadSetData(String componentType, ExistingComponentDTO dto, boolean isReset) {
        this.componentType = componentType;
        this.dto = dto;
        clearAll();
        setRequiredCount(true);
        generate = isReset;
        setCurrentPage(1);
        return recordCount == 0;
    }
}
