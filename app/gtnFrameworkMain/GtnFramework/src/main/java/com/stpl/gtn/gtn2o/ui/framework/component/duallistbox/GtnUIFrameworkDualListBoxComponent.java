package com.stpl.gtn.gtn2o.ui.framework.component.duallistbox;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.bean.GtnFrameworkDualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.listeners.GtnUIFrameworkMoveAllRightButtonClickListener;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.listeners.GtnUIFrameworkMoveLeftButtonClickListener;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.listeners.GtnUIFrameworkMoveRightButtonClickListener;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TreeGrid;
import com.vaadin.ui.VerticalLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GtnUIFrameworkDualListBoxComponent implements GtnUIFrameworkComponent {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnUIFrameworkDualListBoxComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException {
		try {
			GtnFrameworkDualListBoxBean dualListBoxBean = createComponents(
					componentConfig.getGtnUIFrameworkDualListBoxConfig());
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
			GtnFrameworkDualListBoxBean dualListBoxBean = (GtnFrameworkDualListBoxBean) gtnUIFrameworkComponentData
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
		GtnFrameworkDualListBoxBean dualListBoxBean = (GtnFrameworkDualListBoxBean) dualListBoxcomponentData
				.getCustomData();
		dualListBoxBean.clearLeftTableData();
		dualListBoxBean.clearRightTableData();
	}

	@SuppressWarnings("unchecked")
	private void resetHeadersForLeftTable(List<?> reloadInput, GtnFrameworkDualListBoxBean dualListBoxBean) {
		List<Object> visibleColumns = (List<Object>) reloadInput.get(1);
		List<String> headers = (List<String>) reloadInput.get(2);
		resetDualListBoxHeadersAndColumns(visibleColumns, headers, dualListBoxBean);
	}

	private void resetDualListBoxHeadersAndColumns(List<Object> visibleColumns, List<String> listViewHeaders,
			GtnFrameworkDualListBoxBean dualListBoxBean) {
//		@SuppressWarnings("unchecked")
//		ExtContainer<GtnWsRecordBean> extContainer = (ExtContainer<GtnWsRecordBean>) dualListBoxBean.getLeftTable()
//				.getContainerDataSource();
//		extContainer.setRecordHeader(visibleColumns);
//		extContainer.setColumnProperties(getProperties(visibleColumns.toArray()));
                dualListBoxBean.getLeftTable().removeAllColumns();
                for (int i = 0; i <  visibleColumns.size(); i++) {
                String column = visibleColumns.get(i).toString();
			dualListBoxBean.getLeftTable().addColumn(row -> row.getValue(column)).setCaption(listViewHeaders.get(i));
		}
//		dualListBoxBean.getLeftTable().setVisibleColumns(visibleColumns.toArray());
//		dualListBoxBean.getLeftTable().setColumnHeaders(listViewHeaders.toArray(new String[listViewHeaders.size()]));
//		dualListBoxBean.getLeftTable().setFilterBarVisible(true);
//		dualListBoxBean.getLeftTable().setFilterDecorator(new ExtDemoFilterDecorator());
	}

	private GtnFrameworkDualListBoxBean createComponents(GtnUIFrameworkDualListBoxConfig dualListBoxConfig) {
		LOGGER.info("Entering into the createComponents() of VaadinDualListBox");

		Grid<GtnWsRecordBean> leftTable;
		TreeGrid<GtnWsRecordBean> rightTable;
		Button moveLeft;
		Button moveRight;
		Button moveAllRight;

		leftTable = new Grid<>();
                leftTable.setSelectionMode(Grid.SelectionMode.SINGLE);
//		ExtContainer<GtnWsRecordBean> extContainer = new ExtContainer<>(GtnWsRecordBean.class);
//		extContainer.setRecordHeader(Arrays.asList(dualListBoxConfig.getLeftVisibleColumns()));
//		extContainer.setColumnProperties(getProperties(dualListBoxConfig.getLeftVisibleColumns()));
//		extContainer.setDataStructureMode(ExtContainer.DataStructureMode.LIST);
//		leftTable.setContainerDataSource(extContainer);
//		leftTable.setVisibleColumns(dualListBoxConfig.getLeftVisibleColumns());
//		leftTable.setColumnHeaders(dualListBoxConfig.getLeftVisibleHeaders());
                  for (int i = 0; i <  dualListBoxConfig.getLeftVisibleColumns().length; i++) {
                String column = dualListBoxConfig.getRightVisibleColumns()[i].toString();
			leftTable.addColumn(row -> row.getPropertyValue(column)).setCaption(dualListBoxConfig.getLeftVisibleHeaders()[i]);
		}
		rightTable = new TreeGrid<>();
                rightTable.setSelectionMode(Grid.SelectionMode.SINGLE);
//		ExtTreeContainer<GtnWsRecordBean> extTreeContainer = new ExtTre;reMode.LIST);
//		rightTable.setContainerDataSource(extTreeContainer);
//
//		rightTable.setVisibleColumns(dualListBoxConfig.getRightVisibleColumns());
//		rightTable.setColumnHeaders(dualListBoxConfig.getRightVisibleHeaders());
                for (int i = 0; i <  dualListBoxConfig.getRightVisibleColumns().length; i++) {
                String column = dualListBoxConfig.getRightVisibleColumns()[i].toString();
			rightTable.addColumn(row -> row.getPropertyValue(column)).setCaption(dualListBoxConfig.getRightVisibleHeaders()[i]);
		}
		moveLeft = new Button(" < ");
		moveRight = new Button(" > ");
		moveAllRight = new Button("ALL");
		leftTable.setHeight(GtnFrameworkCssConstants.PIXEL_250);
		rightTable.setHeight(GtnFrameworkCssConstants.PIXEL_250);
		leftTable.setWidth(GtnFrameworkCssConstants.PIXEL_300);
		rightTable.setWidth(GtnFrameworkCssConstants.PIXEL_300);
//		leftTable.setSelectable(true);
////		rightTable.setSelectable(true);
//		leftTable.setFilterBarVisible(true);
//		leftTable.setFilterDecorator(new ExtDemoFilterDecorator());

		moveLeft.setId("dualListBoxMoveLeftButton");
		moveRight.setId("dualListBoxMoveRightButton");
		moveAllRight.setId("dualListBoxMoveAllButton");
		moveLeft.addStyleName(GtnFrameworkCommonConstants.DUALLISTBOXBUTTON);
		moveRight.addStyleName(GtnFrameworkCommonConstants.DUALLISTBOXBUTTON);
		moveAllRight.addStyleName(GtnFrameworkCommonConstants.DUALLISTBOXBUTTON);

		GtnFrameworkDualListBoxBean dualListBoxBean = new GtnFrameworkDualListBoxBean(leftTable, rightTable, moveLeft,
				moveRight, moveAllRight, this, dualListBoxConfig);

		LOGGER.info("End into the createComponents() of VaadinDualListBox");
		return dualListBoxBean;
	}

	private HorizontalLayout dualListBoxComponent(GtnFrameworkDualListBoxBean dualListBoxBean) {
		LOGGER.info("Entering into the addComponents() of VaadinDualListBox");

		HorizontalLayout dualListBoxHorizontalLayout = new HorizontalLayout();
		dualListBoxHorizontalLayout.setSpacing(true);
		VerticalLayout buttonLayout = new VerticalLayout();
		buttonLayout.addComponent(dualListBoxBean.getMoveRight());
		buttonLayout.addComponent(dualListBoxBean.getMoveLeft());
		buttonLayout.addComponent(dualListBoxBean.getMoveAllRight());
		buttonLayout.setSpacing(true);
		buttonLayout.addStyleName("move-buttons");
		dualListBoxHorizontalLayout.addComponent(dualListBoxBean.getLeftTable());
		dualListBoxHorizontalLayout.addComponent(buttonLayout);
		dualListBoxHorizontalLayout.addComponent(dualListBoxBean.getRightTable());

		addListeners(dualListBoxBean);

		GtnUIFrameworkComponentData dualListBoxData = new GtnUIFrameworkComponentData();
		dualListBoxData.setCustomData(dualListBoxBean);
		dualListBoxBean.getMoveLeft().setData(dualListBoxData);
		dualListBoxBean.getMoveRight().setData(dualListBoxData);
		dualListBoxBean.getMoveAllRight().setData(dualListBoxData);
		dualListBoxHorizontalLayout.setData(dualListBoxData);
		LOGGER.info("End into the addComponents() of VaadinDualListBox");
		return dualListBoxHorizontalLayout;
	}

	private void addListeners(GtnFrameworkDualListBoxBean dualListBoxBean) {
		dualListBoxBean.getMoveRight().addClickListener(GtnUIFrameworkMoveRightButtonClickListener.getInstance());
		dualListBoxBean.getMoveLeft().addClickListener(GtnUIFrameworkMoveLeftButtonClickListener.getInstance());
		dualListBoxBean.getMoveAllRight().addClickListener(GtnUIFrameworkMoveAllRightButtonClickListener.getInstance());
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