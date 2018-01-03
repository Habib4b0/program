/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.itemmanagement.index.logic.ItemIndexTableLogic;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customwindow.CustomWindow;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

/**
 *
 * @author Abishekram.r
 */
public class AbstractAllItemLookup extends CustomWindow {

    private final Panel panel = new Panel();
    private final VerticalLayout layout = new VerticalLayout();
    private final ItemIndexTableLogic tableLogic = new ItemIndexTableLogic();
    public ExtPagedTable itemResults = new ExtPagedTable(tableLogic);
    private List<ItemIndexDto> selecteditemList = new ArrayList<>();
    private final BeanItemContainer<ItemIndexDto> searchContainer = new BeanItemContainer<>(ItemIndexDto.class);
    private final Button close = new Button("CLOSE");
    private final Button export = new Button(StringUtils.EMPTY);
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");
    private ExtCustomTable exportPeriodViewTable;
    private final String excelName = "All Item Information";
    private BeanItemContainer<ItemIndexDto> excelResultBean = new BeanItemContainer<>(ItemIndexDto.class);

    public AbstractAllItemLookup() {
    }

    public AbstractAllItemLookup(List<ItemIndexDto> selectedItemList) {
        super("All Items Lookup");
        this.selecteditemList = selectedItemList;
        setContent(panel);
        setClosable(Boolean.FALSE);
        setResizable(Boolean.FALSE);
        setModal(Boolean.TRUE);
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        configurefields();
    }

    private void configurefields() {
        HorizontalLayout hlayout = new HorizontalLayout();
        hlayout.setSpacing(Boolean.TRUE);
        hlayout.addComponent(close);
        hlayout.addComponent(export);
        configureTable();
        layout.addComponent(itemResults);
        layout.addComponent(hlayout);
        layout.setMargin(Boolean.TRUE);
        panel.setContent(layout);
        export.addStyleName("link");
        export.setIcon(excelExportImage, "Excel Export");
        close.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                close();
            }
        });

        export.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                itemInfoExport();
            }
        });
    }

    private void configureTable() {
        tableLogic.setContainerDataSource(searchContainer);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        itemResults.setVisibleColumns(UiUtils.getInstance().visibleColumnItem);
        itemResults.setColumnHeaders(UiUtils.getInstance().columnHeaderItem);
        itemResults.setSizeFull();
        itemResults.setEditable(Boolean.FALSE);
        itemResults.markAsDirty();
        itemResults.setSelectable(false);
        itemResults.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        itemResults.addItems(selecteditemList);
    }

    public void itemInfoExport() {
        configureExcelResultTable();
        excelResultBean.addAll(selecteditemList);
        ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(exportPeriodViewTable), excelName, excelName, excelName.replace(" ", "_") + ".xls", false);
        excel.export();
        layout.removeComponent(exportPeriodViewTable);
    }

    private void configureExcelResultTable() {
        excelResultBean = new BeanItemContainer<>(ItemIndexDto.class);
        exportPeriodViewTable = new ExtCustomTable();
        layout.addComponent(exportPeriodViewTable);
        exportPeriodViewTable.setRefresh(Boolean.FALSE);
        exportPeriodViewTable.setVisible(false);
        exportPeriodViewTable.setContainerDataSource(excelResultBean);
        exportPeriodViewTable.setVisibleColumns(UiUtils.getInstance().excelVisibleColumnItemSearch);
        exportPeriodViewTable.setColumnHeaders(UiUtils.getInstance().excelColumnHeaderItemSearch);
    }
}
