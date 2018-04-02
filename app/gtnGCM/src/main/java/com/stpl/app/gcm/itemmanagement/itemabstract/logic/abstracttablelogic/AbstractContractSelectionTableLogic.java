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
import com.stpl.app.gcm.util.Constants;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.Page;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.JavaScript;
import com.vaadin.v7.ui.themes.Reindeer;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author Srithar
 */
public class AbstractContractSelectionTableLogic extends PageTableLogic {

    private final AbstractContractSearchDTO binderDto = new AbstractContractSearchDTO();
    private final AbstractLogic logic = AbstractLogic.getInstance();
    private SelectionDTO selection;
    private boolean isGenerated = false;
    private List<ItemIndexDto> selectedItemList;
    private List input;
    private final List withStringinput = new ArrayList();

    public AbstractContractSelectionTableLogic() {
        super();
    }

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
        this.input = input == null ? input : new ArrayList<>(input);
        this.selection = selection;
        this.setSelectedItemList(selectedItemList == null ? selectedItemList : new ArrayList<>(selectedItemList));
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
        final Button projectionId = new Button(dto.getProjectionId());
        projectionId.setCaption(dto.getProjectionId()); // for setting revision date in excel
        projectionId.setData(dto);
        projectionId.setStyleName(Reindeer.BUTTON_LINK);
        String furl = StringUtils.EMPTY;
        furl = Constants.HTTP + Page.getCurrent().getLocation().getHost() + ":" + Page.getCurrent().getLocation().getPort() + Constants.WEB_WORKFLOW;

        BrowserWindowOpener opener = new BrowserWindowOpener(furl);
        opener.setFeatures(Constants.HEIGHT_WIDTH);
        opener.setFeatures(Constants.TOOL_BAR);
        opener.setParameter(Constants.PROJECTION_MASTER_SID,
                dto.getProjectionId());
        opener.extend(projectionId);
        JavaScript.getCurrent()
                .execute("localStorage.setItem('" + dto.getProjectionId() + "', 'false');");

        return projectionId;

    }

	public List<ItemIndexDto> getSelectedItemList() {
		return selectedItemList;
	}

	public void setSelectedItemList(List<ItemIndexDto> selectedItemList) {
		this.selectedItemList = selectedItemList;
	}
}
