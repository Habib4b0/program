package com.stpl.gtn.gtn2o.registry.config.lookups.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class GtnForecastFilterAction implements GtnUIFrameWorkAction,GtnUIFrameworkActionShareable,GtnUIFrameworkDynamicClass{

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        return;
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
       List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
       GtnUIFrameworkBaseComponent selectedGrid = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(actionParamsList.get(actionParamsList.size()-1).toString());
       PagedGrid pagedGrid = (PagedGrid) selectedGrid.getComponentData().getCustomData();
            Grid<GtnWsRecordBean> grid = pagedGrid.getGrid();
            ListDataProvider<GtnWsRecordBean> dataprovider = (ListDataProvider<GtnWsRecordBean>) grid.getDataProvider();
            if (actionParamsList.get(0) == null) {
                dataprovider.clearFilters();
                return;
            }
            filterDate(dataprovider, actionParamsList.get(1).toString(), actionParamsList);
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
        return GtnUIFrameWorkAction.super.createInstance(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
