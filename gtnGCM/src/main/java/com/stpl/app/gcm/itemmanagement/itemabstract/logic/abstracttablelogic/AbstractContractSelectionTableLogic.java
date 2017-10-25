/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.logic.abstracttablelogic;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.AbstractContractSearchDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.AbstractFilter;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.ui.form.WorkFlowLookup;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author Srithar
 */
public class AbstractContractSelectionTableLogic extends PageTableLogic {

    AbstractContractSearchDTO binderDto = new AbstractContractSearchDTO();
    AbstractLogic logic = AbstractLogic.getInstance();
    SelectionDTO selection;
    boolean isGenerated = false;
    boolean isSummary = false;
    List<ItemIndexDto> selectedItemList;
    List input;
    List withStringinput = new ArrayList();

    @Override
    public int getCount() {
        if (isGenerated) {
            binderDto.setIsCount(false);
            withStringinput.clear();
            withStringinput.addAll(input);
            appendFilterQuery();
            return logic.getContractCount(selection, withStringinput);
        }
        return 0;
    }

    private void appendFilterQuery() {
        selection.setFilters(getFilters());
        StringBuilder sql = AbstractFilter.getInstance().contractfilterQueryGenerator(getFilters());
        if (sql != null) {
            withStringinput.add(sql);
        } else {
            withStringinput.add(StringUtils.EMPTY);
        }
    }

    @Override
    public List loadData(int start, int offset) {
        binderDto.setIsCount(true);
        List newInput = new ArrayList();
        newInput.addAll(withStringinput);
        return logic.getContractResults(selection, start, offset, newInput);
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        AbstractContractSearchDTO dto = (AbstractContractSearchDTO) object;
        if (!dto.getWorkFlowStatus().equals(StringUtils.EMPTY)) {
            dto.setProjectionIdLink(addProjectionWorkFlowLink(dto));
        }
        ((BeanItemContainer<AbstractContractSearchDTO>) container).addBean(dto);
        return dto;
    }

    public boolean loadSetData(SelectionDTO selection, boolean b, List<ItemIndexDto> selectedItemList, List input) {
        this.input = input;
        this.selection = selection;
        this.selectedItemList = selectedItemList;
        isGenerated = b;
        clearAll();
        setRequiredCount(true);
        setCurrentPage(1);
        return getRecordCount() != 0;
    }

    /**
     * Work FLow Pop up
     *
     * @param dto
     * @return Projection ID
     */
    private Button addProjectionWorkFlowLink(final AbstractContractSearchDTO dto) {
        Button projectionId = new Button(dto.getProjectionId());
        projectionId.setCaption(dto.getProjectionId()); // for setting revision date in excel
        projectionId.setData(dto);
        projectionId.setImmediate(true);
        projectionId.setStyleName(Reindeer.BUTTON_LINK);
        projectionId.addClickListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {

                WorkFlowLookup wLookUp = new WorkFlowLookup(new SessionDTO(), dto.getProjectionId());
                UI.getCurrent().addWindow(wLookUp);
                wLookUp.addCloseListener(new Window.CloseListener() {
                    public void windowClose(Window.CloseEvent e) {
                        loadSetData(selection, true, selectedItemList, input);
                    }
                });
            }
        });

        return projectionId;

    }
}
