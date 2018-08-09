package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.listeners.GtnUIFrameworkMoveAllRightButtonClickListener;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.listeners.GtnUIFrameworkMoveLeftButtonClickListener;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.listeners.GtnUIFrameworkMoveRightButtonClickListener;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeGrid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.HeaderRow;

public class GtnUIFrameworkV8DualListBoxComponent implements GtnUIFrameworkComponent {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnUIFrameworkV8DualListBoxComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException {
		try {
			GtnFrameworkV8DualListBoxBean dualListBoxBean = createComponents(
					componentConfig.getGtnUIFrameworkV8DualListBoxConfig(), componentConfig);
			return dualListBoxComponent(dualListBoxBean);
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException(
					"Exception in GtnUIFrameworkDualListBoxComponent.class - buildVaadinComponent method", ex);
		}
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
			Object reloadInput) {

		if (reloadInput instanceof List) {
			HorizontalLayout dualListBox = (HorizontalLayout) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
			GtnUIFrameworkComponentData gtnUIFrameworkComponentData = (GtnUIFrameworkComponentData) dualListBox
					.getData();
			GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) gtnUIFrameworkComponentData
					.getCustomData();
			List<?> reloadInputList = (List<?>) reloadInput;
			if (GtnFrameworkCommonStringConstants.RETURNS_FORECAST_DUALLIST_CONFIG_ACTION
					.equals(reloadInputList.get(0))) {

				resetHeadersForLeftTable((List<?>) reloadInput, dualListBoxBean);
			}
		}

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		AbstractComponent dualListBoxLayout = GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		GtnUIFrameworkComponentData dualListBoxcomponentData = (GtnUIFrameworkComponentData) dualListBoxLayout
				.getData();
		GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) dualListBoxcomponentData
				.getCustomData();
		dualListBoxBean.clearLeftTableData();
		dualListBoxBean.clearRightTableData();
	}

	@SuppressWarnings("unchecked")
	private void resetHeadersForLeftTable(List<?> reloadInput, GtnFrameworkV8DualListBoxBean dualListBoxBean) {
		List<Object> visibleColumns = (List<Object>) reloadInput.get(1);
		List<String> headers = (List<String>) reloadInput.get(2);
		resetDualListBoxHeadersAndColumns(visibleColumns, headers, dualListBoxBean);
	}

	private void resetDualListBoxHeadersAndColumns(List<Object> visibleColumns, List<String> listViewHeaders,
			GtnFrameworkV8DualListBoxBean dualListBoxBean) {
		dualListBoxBean.getLeftTable().removeAllColumns();
		for (int i = 0; i < visibleColumns.size(); i++) {
			String column = visibleColumns.get(i).toString();
			LOGGER.info("column name is ---->" + column);
			dualListBoxBean.getLeftTable().addColumn(row -> row.getPropertyValue(column))
					.setCaption(listViewHeaders.get(i));
		}
	}

	private GtnFrameworkV8DualListBoxBean createComponents(GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig,
			GtnUIFrameworkComponentConfig componentConfig) {
		LOGGER.info("Entering into the createComponents() of VaadinDualListBox with id: "
				+ componentConfig.getComponentId());

		Grid<GtnWsRecordBean> leftTable;
		TreeGrid<GtnWsRecordBean> rightTable;
		Button moveLeft;
		Button moveRight;
		Button moveAllRight;

		leftTable = new Grid<>();
		leftTable.setSelectionMode(Grid.SelectionMode.SINGLE);
		for (int i = 0; i < dualListBoxConfig.getLeftVisibleColumns().length; i++) {
			String column = dualListBoxConfig.getLeftVisibleColumns()[i].toString();
			LOGGER.info("column---------->" + column);
			leftTable.addColumn(row -> row.getPropertyValue(column))
					.setCaption(dualListBoxConfig.getLeftVisibleHeaders()[i]).setId(column);
			addFilterToGrid(leftTable, column);
		}

		rightTable = new TreeGrid<>();
		rightTable.setSelectionMode(Grid.SelectionMode.SINGLE);
		for (int i = 0; i < dualListBoxConfig.getRightVisibleColumns().length; i++) {
			String column = dualListBoxConfig.getRightVisibleColumns()[i].toString();
			rightTable.addColumn(row -> row.getPropertyValue(column))
					.setCaption(dualListBoxConfig.getRightVisibleHeaders()[i]);
		}
		moveLeft = new Button(" < ");
		moveRight = new Button(" > ");
		moveAllRight = new Button("ALL");
		leftTable.setHeight(GtnFrameworkCssConstants.PIXEL_300);
		rightTable.setHeight(GtnFrameworkCssConstants.PIXEL_300);
		leftTable.setWidth(GtnFrameworkCssConstants.PIXEL_400);
		rightTable.setWidth(GtnFrameworkCssConstants.PIXEL_400);

		moveLeft.setId("dualListBoxMoveLeftButton");
		moveRight.setId("dualListBoxMoveRightButton");
		moveAllRight.setId("dualListBoxMoveAllButton");
		moveLeft.addStyleName(GtnFrameworkCommonConstants.DUALLISTBOXBUTTON);
		moveRight.addStyleName(GtnFrameworkCommonConstants.DUALLISTBOXBUTTON);
		moveAllRight.addStyleName(GtnFrameworkCommonConstants.DUALLISTBOXBUTTON);

		GtnFrameworkV8DualListBoxBean dualListBoxBean = new GtnFrameworkV8DualListBoxBean(leftTable, rightTable,
				moveLeft, moveRight, moveAllRight, this, dualListBoxConfig);

		LOGGER.info(
				"End into the createComponents() of VaadinDualListBox with id: " + componentConfig.getComponentId());
		return dualListBoxBean;
	}

	private void addFilterToGrid(Grid grid, String column) {
		HeaderRow filterRow = grid.appendHeaderRow();
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setMargin(false);
		horizontalLayout.setWidth("113%");
		horizontalLayout.setHeight("37px");
		TextField gridShowAllTextField = new TextField();
		gridShowAllTextField.setPlaceholder("Show All");
		gridShowAllTextField.setId(column);
		gridShowAllTextField.setHeight("100%");
		gridShowAllTextField.setWidth("100%");

		horizontalLayout.addComponent(gridShowAllTextField);

		horizontalLayout.addLayoutClickListener(new LayoutClickListener() {
			@Override
			public void layoutClick(LayoutClickEvent gridShowAllTextFieldEvent) {

				if (gridShowAllTextFieldEvent.getChildComponent() == gridShowAllTextField) {
					gridShowAllTextField.setPlaceholder("");
				}
			}
		});
		gridShowAllTextField.addBlurListener(new BlurListener() {
			@Override
			public void blur(BlurEvent gridShowAllTextFieldBlurEvent) {
				if (gridShowAllTextFieldBlurEvent.getComponent() == gridShowAllTextField) {
					String value = gridShowAllTextField.getValue();
					if (value.equals(""))
						gridShowAllTextField.setPlaceholder("Show All");
				}
			}
		});
		gridShowAllTextField.addValueChangeListener(event -> {
			String filterText = event.getValue();
			ListDataProvider<GtnWsRecordBean> dataprovider = (ListDataProvider<GtnWsRecordBean>) grid.getDataProvider();
			dataprovider.setFilter(s -> {
				String value = s.getPropertyValue("levelValue").toString().toLowerCase(Locale.ENGLISH);
				return value.contains(filterText.toLowerCase());
			});
		});
		filterRow.getCell(String.valueOf(column)).setComponent(horizontalLayout);
	}

	private HorizontalLayout dualListBoxComponent(GtnFrameworkV8DualListBoxBean dualListBoxBean) {
		LOGGER.info("Entering into the addComponents() of VaadinDualListBoxw");

		HorizontalLayout dualListBoxLayout = new HorizontalLayout();
		dualListBoxLayout.setSpacing(true);
		VerticalLayout dualListBoxButtonLayout = new VerticalLayout();
		dualListBoxButtonLayout.addComponent(dualListBoxBean.getMoveRightBtn());
		dualListBoxButtonLayout.addComponent(dualListBoxBean.getMoveLeftBtn());
		dualListBoxButtonLayout.addComponent(dualListBoxBean.getMoveAllRightBtn());
		dualListBoxButtonLayout.setSpacing(true);
		dualListBoxButtonLayout.setMargin(false);
		dualListBoxButtonLayout.addStyleName("move-buttons");
		dualListBoxLayout.addComponent(dualListBoxBean.getLeftTable());
		dualListBoxLayout.addComponent(dualListBoxButtonLayout);
		dualListBoxLayout.addComponent(dualListBoxBean.getRightTable());

		addListeners(dualListBoxBean);

		GtnUIFrameworkComponentData dualListBoxComponentData = new GtnUIFrameworkComponentData();
		dualListBoxComponentData.setCustomData(dualListBoxBean);
		dualListBoxBean.getMoveLeftBtn().setData(dualListBoxComponentData);
		dualListBoxBean.getMoveRightBtn().setData(dualListBoxComponentData);
		dualListBoxBean.getMoveAllRightBtn().setData(dualListBoxComponentData);
		dualListBoxLayout.setData(dualListBoxComponentData);
		LOGGER.info("End into the addComponents() of VaadinDualListBox + " + dualListBoxLayout.getClass());
		return dualListBoxLayout;
	}

	private void addListeners(GtnFrameworkV8DualListBoxBean dualListBoxBean) {
		dualListBoxBean.getMoveRightBtn().addClickListener(GtnUIFrameworkMoveRightButtonClickListener.getInstance());
		dualListBoxBean.getMoveLeftBtn().addClickListener(GtnUIFrameworkMoveLeftButtonClickListener.getInstance());
		dualListBoxBean.getMoveAllRightBtn()
				.addClickListener(GtnUIFrameworkMoveAllRightButtonClickListener.getInstance());
	}

	@SuppressWarnings("rawtypes")
	private Map<Object, Class> getProperties(final Object[] visibleColumnsArray) {
		Map<Object, Class> properties = new HashMap<>();
		for (Object visibleColumn : visibleColumnsArray) {
			properties.put(visibleColumn, String.class);
		}
		return properties;
	}

}