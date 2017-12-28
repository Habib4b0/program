/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.add.logic.tablelogic;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.add.dto.AddItemTableDTO;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.AbstractContractSearchDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.ui.form.WorkFlowLookup;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Abishekram.r
 */
public class AddItemDetailsTableLogic extends PageTableLogic {

    AddItemTableDTO binderDto = new AddItemTableDTO();
    AbstractLogic logic =AbstractLogic.getInstance();
    SelectionDTO selection;
    boolean isGenerated = false;
    boolean isSummary = false;
    List<ItemIndexDto> selectedItemList;
    public static final Logger LOGGER = Logger.getLogger(AddItemDetailsTableLogic.class);
    /**
     * Record count method
     *
     * @return Integer
     */
    @Override
    public int getCount() {
        
        LOGGER.debug("Inside COUNT");
        if (isGenerated) {
            binderDto.setIsCount(false);
            selection.setFilters(getFilters());
            return logic.getContractCount(selection, binderDto);
        }
        return 0;
    }

    /**
     * Loading the grid
     *
     * @param start
     * @param offset
     * @return List of results items
     */
    @Override
    public List loadData(int start, int offset) {
       LOGGER.debug("Inside Load data");
        binderDto.setIsCount(true);
        selection.setFilters(getFilters());
        return logic.getContractResults(selection, start, offset, binderDto);
    }

    /**
     * configureContainer
     *
     * @param object
     * @param container
     * @return Object
     */
    @Override
    public Object configureContainer(Object object, Container container) {

        AbstractContractSearchDTO dto = (AbstractContractSearchDTO) object;
        if (!dto.getWorkFlowStatus().equals(StringUtils.EMPTY)) {
            dto.setProjectionIdLink(addProjectionWorkFlowLink(dto));
        }
        ((BeanItemContainer<AbstractContractSearchDTO>) container).addBean(dto);
        return dto;
    }

    /**
     * Setting up the datas which we pass to the Logic
     *
     * @param binderDto
     * @return boolean - count is 0 or not
     */
    public boolean loadSetData(AddItemTableDTO binderDto, SelectionDTO selection, boolean isSummary, List<ItemIndexDto> selectedItemList) {
        this.binderDto = binderDto;
        this.selection = selection;
        isGenerated = selection.isReset();
        this.isSummary = isSummary;
        this.selectedItemList = selectedItemList;
        clearAll();
        setRequiredCount(true);
        setCurrentPage(1);
        return getRecordCount() != 0;
    }

    private Button addProjectionWorkFlowLink(final AbstractContractSearchDTO dto) {
        Button projectionId = new Button(dto.getProjectionId());
        projectionId.setCaption(dto.getProjectionId()); // for setting revision date in excel
        projectionId.setData(dto);
        projectionId.setImmediate(true);
        projectionId.setStyleName(Reindeer.BUTTON_LINK);
        projectionId.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                WorkFlowLookup wLookUp = new WorkFlowLookup(new SessionDTO(), dto.getProjectionId());
                UI.getCurrent().addWindow(wLookUp);
                wLookUp.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        loadSetData(binderDto, selection, false, selectedItemList);
                    }
                });
            }
        });

        return projectionId;

    }
}
