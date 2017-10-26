
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.logic.tablelogic;

import com.stpl.app.gcm.copycontract.dto.NewComponentDTO;
import com.stpl.app.gcm.copycontract.logic.CopyContractLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentInfoDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.text.ParseException;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author srithar
 */
public class NewComponentsDetailsSearchTableLogic extends PageTableLogic {

    boolean generate = false;
    String componentType;
    String searchValue;
    String componentInnerType;
    CopyContractLogic logic = new CopyContractLogic();
    NewComponentDTO selection = new NewComponentDTO();
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(NewComponentsDetailsSearchTableLogic.class);

    /**
     * Record count logic
     *
     * @return integer as count
     */
    @Override
    public int getCount() {
        if (generate) {
            try {
                int count = 0;
                count = Integer.valueOf(String.valueOf(logic.getComponentInfoSelection(selection, componentType, componentInnerType, searchValue, true)));
                return count;
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

        List<ComponentInfoDTO> resultList = null;
        try {
            selection.setStart(start);
            selection.setOffset(offset);

            resultList = (List<ComponentInfoDTO>) logic.getComponentInfoSelection(selection, componentType, componentInnerType, searchValue, false);
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
        NewComponentDTO dto = (NewComponentDTO) object;
        ((BeanItemContainer<NewComponentDTO>) container).addBean(dto);
        return dto;
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
    public boolean loadSetData(String componentType, String componentInnerType, String componentSelection, boolean isReset) {
        this.searchValue = componentSelection;
        this.componentInnerType = componentInnerType;
        this.componentType = componentType;
        clearAll();
        setRequiredCount(true);
        generate = isReset;
        setCurrentPage(1);
        return recordCount != 0;
    }

}
