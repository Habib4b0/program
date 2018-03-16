package com.stpl.addons.grid.paged;

import java.util.Arrays;
import java.util.HashSet;

import com.stpl.addons.grid.paged.bean.QueryBean;
import com.stpl.addons.grid.paged.component.PagedGrid;
import com.stpl.addons.grid.paged.config.PagedTableConfig;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class PagedGridUI extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String dataQuery = "select FORECASTING_FORMULA_SID as forecastingFormulaId, FORMULA_NO as formulaNo, FORMULA_NAME as formulaName, FORMULA as formula, FORMULA_TYPE as formulaType, IS_ACTIVE as isActive, CREATED_DATE as createdDate, MODIFIED_DATE as ModifiedDate"
			+ " from dbo.FORECASTING_FORMULA ORDER BY FORECASTING_FORMULA_SID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
	String countQuery = "select count(*) from dbo.FORECASTING_FORMULA ";

	public PagedGridUI() {

		PagedTableConfig pagedTableConfig = new PagedTableConfig();
		pagedTableConfig.setVisibleColumns(new HashSet<>(Arrays.asList("forecastingFormulaId", "formulaNo",
				"formulaName", "formula", "formulaType", "isActive", "createdDate", "modifiedDate")));
		QueryBean queryBean = new QueryBean(countQuery, dataQuery, null, null);
		pagedTableConfig.setQueryBean(queryBean);

		PagedGrid pagedGrid = new PagedGrid(pagedTableConfig);
		setStyleName("demoContentLayout");
		setSizeFull();
		addComponent(pagedGrid.getGrid());
		pagedGrid.getGrid().setWidth("80%");
		pagedGrid.getGrid().setHeight("450px");
		setComponentAlignment(pagedGrid.getGrid(), Alignment.MIDDLE_CENTER);

		HorizontalLayout tableSelectionControls = new HorizontalLayout();
		tableSelectionControls.setCaption("Table Selection Controls");
		addComponent(tableSelectionControls);

		HorizontalLayout pages = new HorizontalLayout();
		pages.setCaption("Paging controls");
		pages.addComponent(new Button("First", e -> pagedGrid.setPageNumber(0)));
		pages.addComponent(new Button("Previous", e -> pagedGrid.previousPage()));
		pages.addComponent(new Button("Next", e -> pagedGrid.nextPage()));
		pages.addComponent(
				new Button("Last", e -> pagedGrid.setPageNumber(pagedGrid.getCount() / pagedGrid.getPageLength())));
		VerticalLayout controls = new VerticalLayout();
		controls.addComponents(tableSelectionControls, pages);
		controls.setWidth("100%");
		controls.setHeightUndefined();
		controls.setComponentAlignment(tableSelectionControls, Alignment.MIDDLE_CENTER);
		controls.setComponentAlignment(pages, Alignment.BOTTOM_CENTER);
		addComponent(controls);

		pagedGrid.getGrid().getEditor().setEnabled(true);
	}
}
