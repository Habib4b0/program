/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.logic.tablelogic;

import com.stpl.app.gcm.copycontract.dto.ContractSelectionDTO;
import com.stpl.app.gcm.copycontract.dto.CopyComponentDTO;
import com.stpl.app.gcm.copycontract.logic.CopyContractLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;
/**
 *
 * @author kasiammal.m
 */
public class CopyComponentsResultLogic extends PageTableLogic {

    boolean generate = false;
    List<ContractSelectionDTO> selectedlist = new ArrayList<>();
    CopyContractLogic logic = new CopyContractLogic();
    int count;
    private static final Logger LOGGER = Logger.getLogger(CopyComponentsResultLogic.class);

    @Override
    public int getCount() {
        count = 0;
        try {
            if (generate) {
                count = selectedlist.size();
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<CopyComponentDTO> resultList = null;
        try {
            resultList = logic.getComponentinfoResults(selectedlist);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return resultList;

    }

    @Override
    public Object configureContainer(Object object, Container container) {
        CopyComponentDTO dto = (CopyComponentDTO) object;
        ((BeanItemContainer<CopyComponentDTO>) container).addBean(dto);
        return dto;
    }

    public boolean setData(List<ContractSelectionDTO> selectedlist) {
        this.selectedlist = selectedlist;
        generate = true;
        clearAll();
        setRequiredCount(true);
        setCurrentPage(1);
        return getRecordCount() != 0;
    }

    public int getPageForItem(int pos) {
        int curPage = ((pos - NumericConstants.TWO) / getPageLength()) + 1;
        return curPage;
    }

    public int getItemIndex(int pos) {
        int index = (pos - NumericConstants.TWO) % getPageLength();
        return index;
    }

    public int getStartIndex(int count, int index) {
        int start = count - index;
        return start;
    }

    @Override
    protected void createCurrentPageStart() {
        for (ExtPagedTable extPagedTable : tableList) {
            extPagedTable.setValue(null);
        }
        setRefresh(Boolean.FALSE);
    }

    @Override
    protected void createCurrentPageEnd() {
        setRefresh(Boolean.TRUE);
    }

}
