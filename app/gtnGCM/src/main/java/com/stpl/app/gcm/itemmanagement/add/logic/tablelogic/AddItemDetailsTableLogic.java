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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Abishekram.r
 */
public class AddItemDetailsTableLogic extends PageTableLogic {

    private AddItemTableDTO binderDto = new AddItemTableDTO();
    private final AbstractLogic logic = AbstractLogic.getInstance();
    private SelectionDTO selection;
    private boolean isGenerated = false;
    private boolean isSummary = false;
    private List<ItemIndexDto> selectedItemList;
    public static final Logger LOGGER = LoggerFactory.getLogger(AddItemDetailsTableLogic.class);

    public AddItemDetailsTableLogic() {
    }
    
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
        this.setSummary(isSummary);
        this.setSelectedItemList(selectedItemList == null ? selectedItemList : new ArrayList<>(selectedItemList));
        clearAll();
        setRequiredCount(true);
        setCurrentPage(1);
        return getRecordCount() != 0;
    }

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

	public boolean isSummary() {
		return isSummary;
	}

	public void setSummary(boolean isSummary) {
		this.isSummary = isSummary;
	}

	public List<ItemIndexDto> getSelectedItemList() {
		return selectedItemList;
	}

	public void setSelectedItemList(List<ItemIndexDto> selectedItemList) {
		this.selectedItemList = selectedItemList;
	}
}
