package com.stpl.gtn.gtn2o.ui.framework.component.grid.paged;

import java.util.Arrays;
import java.util.HashSet;

import com.stpl.gtn.gtn2o.ui.framework.component.grid.bean.QueryBean;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedTreeGrid;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.config.PagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.constants.Queries;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

public class PagedTreeGridUI extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PagedTreeGridUI() {

		PagedTreeTableConfig pagedTableConfig = new PagedTreeTableConfig();
		pagedTableConfig
				.setVisibleColumns(Arrays.asList("actualReturnPercent", "actualRpu", "actualReturnAmount",
						"projectedReturnPercent", "projectedRpu", "projectedReturnAmount", "growthRate"));
		pagedTableConfig.setLeftVisibleColumns(new HashSet<>(Arrays.asList("hierarchyNo", "levelName")));

		pagedTableConfig.setLevelNo(1);

		pagedTableConfig.setRowsPerLevelItem(10);

                
		Object dataQueryInput[] = {1, 1848 };
		Object countQueryInput[] = { "1848" };
		QueryBean queryBean = new QueryBean(Queries.RETURNS_COUNT_QUERY, Queries.RETURNS_DATA_QUERY, dataQueryInput,
				countQueryInput);
                queryBean.setLeftDataQuery(Queries.RETURNS_LEFT_DATA_QUERY);
                queryBean.setLeftDataQueryInputs(countQueryInput);
		pagedTableConfig.setQueryBean(queryBean);

		PagedTreeGrid pagedGrid = new PagedTreeGrid(new GtnUIFrameworkPagedTreeTableConfig());
		setStyleName("demoContentLayout");
		setSizeFull();
		addComponent(pagedGrid.getGrid());
		pagedGrid.getGrid().setWidth("80%");
		pagedGrid.getGrid().setHeight("450px");
		setComponentAlignment(pagedGrid.getGrid(), Alignment.MIDDLE_CENTER);
		addComponent(pagedGrid.getControlLayout());
                setComponentAlignment(pagedGrid.getControlLayout(), Alignment.MIDDLE_CENTER);
		pagedGrid.getGrid().getEditor().setEnabled(true);
	}
}
