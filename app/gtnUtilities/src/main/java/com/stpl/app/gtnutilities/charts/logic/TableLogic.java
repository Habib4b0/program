/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnutilities.charts.logic;

import com.stpl.app.gtnutilities.util.Constants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author Karthik.Raja
 */
public class TableLogic extends PageTableLogic {

    final SearchLogic searchLogic = SearchLogic.getInstance();
    final com.stpl.app.gtnutilities.serverlogging.logic.SearchLogic loggingSearchLogic = com.stpl.app.gtnutilities.serverlogging.logic.SearchLogic.getInstance();
    List list = null;
    Object[] input = null;
    String screenName = Constants.EMPTY;

    @Override
    public int getCount() {
        if (screenName.equals(Constants.Query_Statistics)) {
            list = searchLogic.fetchDataFromDB(input, NumericConstants.ZERO, NumericConstants.ZERO, true, this.getFilters(), this.getSortByColumns(), false);
            return list == null ? NumericConstants.ZERO : Integer.valueOf(String.valueOf(list.get(NumericConstants.ZERO)));
        } else if (screenName.equals(Constants.Schedule_Job)) {
            list = searchLogic.fetchDataFromDBForJOb(input, 0, 0, true, this.getFilters(), this.getSortByColumns(), false);
            return list == null ? NumericConstants.ZERO : Integer.valueOf(String.valueOf(list.get(NumericConstants.ZERO)));
        } else if (screenName.equals(Constants.Server_Logging)) {
            list = loggingSearchLogic.searchResults(true, 0, 0);
            return list.get(0) == null ? 0 : Integer.valueOf(String.valueOf(list.get(0)));
        }
        return NumericConstants.ZERO;

    }

    @Override
    public List loadData(int start, int offset) {
        if (screenName.equals(Constants.Query_Statistics)) {
            list = searchLogic.fetchDataFromDB(input, start, offset, false, this.getFilters(), this.getSortByColumns(), false);
        } else if (screenName.equals(Constants.Schedule_Job)) {
            list = searchLogic.fetchDataFromDBForJOb(input, start, offset, false, this.getFilters(), this.getSortByColumns(), false);
        } else if (screenName.equals(Constants.Server_Logging)) {
            list = (List) loggingSearchLogic.searchResults(false, start, offset);
        }

        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        container.addItem(object);
        return object;
//        if (screenName.equals(Constants.Query_Statistics)) {
//            ChartsDTO dto = (ChartsDTO) object;
//            ((BeanItemContainer<ChartsDTO>) container).addBean(dto);
//            return dto;
//        } else if (screenName.equals(Constants.Server_Logging)) {
//            LoggingDto dto = (LoggingDto) object;
//            ((BeanItemContainer<LoggingDto>) container).addBean(dto);
//            return dto;
//
//        } else {
//            JobDTO dto = (JobDTO) object;
//            ((BeanItemContainer<JobDTO>) container).addBean(dto);
//            return dto;
//        }

    }

    public void configureSearchData(Object[] input, String screenName) {
        this.input = input;
        this.screenName = screenName;
        this.clearAll();
        this.getFilters().clear();
        this.setRequiredCount(true);
        this.setCurrentPage(NumericConstants.ONE);
    }
}
