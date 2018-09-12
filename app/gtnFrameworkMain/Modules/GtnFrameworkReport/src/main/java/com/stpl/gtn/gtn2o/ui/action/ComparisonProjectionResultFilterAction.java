/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import java.util.List;
import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class ComparisonProjectionResultFilterAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {
    private GtnWSLogger logger = GtnWSLogger.getGTNLogger(ComparisonProjectionResultFilterAction.class);
    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        return;
    }
    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        try {
            List<Object> filterValue = gtnUIFrameWorkActionConfig.getActionParameterList();
            GtnUIFrameworkBaseComponent selectedGrid = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent("comparisonLookupProjectionsResultsPagedTableComponent");
            PagedGrid pagedGrid = (PagedGrid) selectedGrid.getComponentData().getCustomData();
            Grid<GtnWsRecordBean> grid = pagedGrid.getGrid();
            ListDataProvider<GtnWsRecordBean> dataprovider = (ListDataProvider<GtnWsRecordBean>) grid.getDataProvider();
            if (filterValue.get(0) == null) {
                dataprovider.clearFilters();
                return;
            }
            filterDate(dataprovider, filterValue.get(1).toString(), filterValue);
        } catch (Exception exception) {
            logger.error("EXCEPTION", exception);
        }
    }
    private void filterDate(ListDataProvider<GtnWsRecordBean> dataprovider, String componentId, List<Object> filterValue) {
        dataprovider.setFilter(s -> {
            String filteringValue = "";
            String value = "";
            if (componentId.contains("Date")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
                filteringValue = formatter.format((LocalDate) filterValue.get(0));
                value = String.valueOf(s.getPropertyValue(componentId));
            } else {
                filteringValue = (String) filterValue.get(0);
                value = s.getPropertyValue(componentId).toString().toLowerCase(Locale.ENGLISH);
            }
            return value.contains(filteringValue.toLowerCase(Locale.ENGLISH));
        });
    }
    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }
}