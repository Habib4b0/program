/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.logic.tablelogic;

import com.stpl.app.gcm.copycontract.dto.CopyComponentDTO;
import com.stpl.app.gcm.copycontract.logic.CopyContractLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author kasiammal.m
 */
public class CopyComponentsSearchLogic extends PageTableLogic {

    private String Query = StringUtils.EMPTY;
    private boolean generate = false;
    private String Component = StringUtils.EMPTY;
    private final CopyContractLogic logic = new CopyContractLogic();
    private int count;
    private static final Logger LOGGER = LoggerFactory.getLogger(CopyComponentsSearchLogic.class);
    private static final BooleanConstant BOOLEAN_CONSTANT = new BooleanConstant();

    @Override
    public int getCount() {
        count = 0;
        try {
            if (generate) {
                count = logic.getComponentCount(Query);
            }
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<CopyComponentDTO> resultList = null;
        try {
            resultList = logic.getComponentResults(Component, Query, start, offset);
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
        return resultList;

    }

    @Override
    public Object configureContainer(Object object, Container container) {
        CopyComponentDTO dto = (CopyComponentDTO) object;
        ((BeanItemContainer<CopyComponentDTO>) container).addBean(dto);
        return dto;
    }

    public boolean setData(String Component, String value) {
        this.Query = value;
        this.Component = Component;
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
        setRefresh(BOOLEAN_CONSTANT.getFalseFlag());
    }

    @Override
    protected void createCurrentPageEnd() {
        setRefresh(BOOLEAN_CONSTANT.getTrueFlag());
    }

}
