/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.fileSelection.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.lazyLoad.FileSelectionTableLogic;
import com.stpl.app.cff.logic.CFFLogic;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.ui.ConsolidatedFinancialForecastUI;
import com.stpl.app.cff.ui.fileSelection.dto.FileSelectionDTO;
import com.stpl.app.cff.ui.fileSelection.dto.FileSelectionTableGenerator;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.text.SimpleDateFormat;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.drools.core.util.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author mohamed.hameed
 */
public class FileSelection extends CustomComponent {

    FileSelectionTableLogic tableLogic = new FileSelectionTableLogic();
    final ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    VerticalLayout layout = new VerticalLayout();
    BeanItemContainer<FileSelectionDTO> searchContainer = new BeanItemContainer<>(FileSelectionDTO.class);
    BeanItemContainer<FileSelectionDTO> excelContainer = new BeanItemContainer<>(FileSelectionDTO.class);
    SessionDTO sessionDTO;
      Button excelExport = new Button();
      CFFLogic cffLogic=new CFFLogic();
//      private ExtCustomTable  excelTable = new ExtCustomTable();
      SimpleDateFormat DBDate = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger LOGGER = Logger.getLogger(FileSelection.class);

    public FileSelection(SessionDTO sessionDTO) {
        this.setCompositionRoot(addComponent());
        this.sessionDTO = sessionDTO;
        configureFields();
    }

    Component addComponent() {
        Panel mainPanel = new Panel("File Selection");
        mainPanel.setContent(addResultTable());
        return mainPanel;
    }

    private void configureFields() {
        LOGGER.info("configureFields starts");
        try {
            tableLogic.setContainerDataSource(searchContainer);
            tableLogic.setPageLength(10);
            tableLogic.sinkItemPerPageWithPageLength(false);
            resultsTable.setVisibleColumns(CommonUtils.visibleColumnItemSearch);
            resultsTable.setColumnHeaders(CommonUtils.columnHeaderItemSearch);
            resultsTable.setColumnAlignment(CommonUtils.visibleColumnItemSearch[2], ExtCustomTable.Align.CENTER);
            resultsTable.setColumnAlignment(CommonUtils.visibleColumnItemSearch[3], ExtCustomTable.Align.CENTER);
            resultsTable.setColumnAlignment(CommonUtils.visibleColumnItemSearch[4], ExtCustomTable.Align.CENTER);
            resultsTable.setColumnAlignment(CommonUtils.visibleColumnItemSearch[0], ExtCustomTable.Align.CENTER);
            resultsTable.setColumnAlignment(CommonUtils.visibleColumnItemSearch[1], ExtCustomTable.Align.CENTER);
            
            resultsTable.setSizeUndefined();
            resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            resultsTable.setFilterBarVisible(true);
            resultsTable.addStyleName(CommonUtils.FILTERCOMBOBOX);
            resultsTable.addStyleName("table-header-normal");
            resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            for (Object propertyId : resultsTable.getVisibleColumns()) {
                resultsTable.setColumnWidth(propertyId, -1);

            }
            resultsTable.setWidth("1600");
            resultsTable.markAsDirty();
            resultsTable.setSelectable(false);
            resultsTable.setTableFieldFactory(new FileSelectionTableGenerator(resultsTable, searchContainer, tableLogic, sessionDTO));
            tableLogic.setSearchData(sessionDTO);

            resultsTable.setConverter("activeFromDate", new StringToDateConverter());
            resultsTable.setConverter("activeToDate", new StringToDateConverter());
             for (Object propertyId : resultsTable.getVisibleColumns()) {
            resultsTable.setColumnWidth(propertyId, -1);
              }
            resultsTable.setEditable(true);
           
//             excelTable.setContainerDataSource(excelContainer);
//            excelTable.setVisibleColumns(CommonUtils.visibleColumnItemSearch);
//            excelTable.setColumnHeaders(CommonUtils.columnHeaderItemSearch);
//            excelTable.setSizeFull();
            excelExport.addClickListener(new Button.ClickListener() {
                /**
                 * calls excelExportLogic method on button click
                 *
                 * @param event - Mouse Click event
                 */
                public void buttonClick(final Button.ClickEvent event) {
                    try {
                        LOGGER.info("Entering EXCEL Export Button Click");
                          ConsolidatedFinancialForecastUI.EXCEL_CLOSE=true;
                     //   excelExportLogic();
                        final ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(resultsTable), "File Selection", "File Selection", "FileSelection.xls", false);
                        excel.export();
                        LOGGER.info(" Ends  EXCEL Export Button Click");

                    } catch (Exception exception) {
                        exception.printStackTrace();
                        LOGGER.error(exception.getMessage());
                    }
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e);
        }
           LOGGER.info("configureFields ends");
    }

//    public void excelExportLogic() {
//        excelContainer = new BeanItemContainer<>(FileSelectionDTO.class);
//        excelTable.setContainerDataSource(excelContainer);
//        excelTable.setVisibleColumns(CommonUtils.visibleColumnItemSearch);
//        excelTable.setColumnHeaders(CommonUtils.columnHeaderItemSearch);
//        CFFLogic cfflogic = new CFFLogic();
//        List filesList = (List) cfflogic.getFileName(false);
//        excelContainer.addAll(filesList);
//       
//
//    }
    /**
     * Add Result Table.
     */
    @SuppressWarnings("serial")
    private Component addResultTable() {
        excelExport.setCaption(StringUtils.EMPTY);
        excelExport.setIcon(new ThemeResource("../../icons/excel.png"));
        excelExport.setStyleName("link");
        excelExport.setDescription("Export to excel");
        excelExport.setIconAlternateText("Excel export");
        excelExport.setHtmlContentAllowed(true);
        excelExport.setImmediate(true);
        layout.addComponent(resultsTable);
//        layout.addComponent(excelTable);
//        excelTable.setVisible(false);
        HorizontalLayout controls = tableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        layout.addComponent(controlLayout);
       layout.addComponent(excelExport);


        return layout;
    }

    public void getSelectedFile() {
        try {
            String projId = String.valueOf(VaadinSession.getCurrent().getAttribute("projectionId"));
            int count = cffLogic.getFileSelectionCount(projId, null);
            if (count == 0) {
                List<FileSelectionDTO> fileList = searchContainer.getItemIds();
                for (int i = 0; i < fileList.size(); i++) {
                    FileSelectionDTO dto = fileList.get(i);
               if(!"null".equals(projId)){
                String query = "INSERT INTO dbo.CFF_FILE_SELECTION (CFF_MASTER_SID, FILE_MANAGEMENT_SID, FILE_NAME, VERSION, ACTIVE_FROM, ACTIVE_TO, FILE_TYPE) \n"
                        + "	VALUES('@CFF_MASTER_SID', @FILE_MANAGEMENT_SID, '@FILE_NAME', '@VERSION', @ACTIVE_FROM, @ACTIVE_TO, '@FILE_TYPE')";

                   query = query.replace("@CFF_MASTER_SID", projId);
                    if (dto.getFileManagementSid() == null) {
                        query = query.replace("@FILE_MANAGEMENT_SID", "null");
                    } else {
                        query = query.replace("@FILE_MANAGEMENT_SID", "'"+dto.getFileManagementSid()+"'");
                    }
                    query = query.replace("@FILE_NAME", dto.getFileName());
                    query = query.replace("@VERSION", dto.getVersion());
                    if (dto.getActiveFromDate() != null) {
                        query = query.replace("@ACTIVE_FROM", "'" + DBDate.format(dto.getActiveFromDate()) + "'");
                    } else {
                        query = query.replace("@ACTIVE_FROM", "null");
                    }
                    if (dto.getActiveToDate() != null) {
                        query = query.replace("@ACTIVE_TO", "'" + DBDate.format(dto.getActiveToDate()) + "'");

                    } else {
                        query = query.replace("@ACTIVE_TO", "null");
                    }
                    query = query.replace("@FILE_TYPE", dto.getFileTypeId());
                    LOGGER.info("--final query--------->>>>>" + query);
                    HelperTableLocalServiceUtil.executeUpdateQuery(query);
                    }
                }
            }else{
                List<FileSelectionDTO> fileList = searchContainer.getItemIds();
                for (int i = 0; i < fileList.size(); i++) {
                    FileSelectionDTO dto = fileList.get(i);
                    if(dto.isFileChanged()){
                    String query = " update CFF_FILE_SELECTION set FILE_MANAGEMENT_SID=@FILE_MANAGEMENT_SID,FILE_NAME='@FILE_NAME',"
                            + "VERSION=@VERSION,ACTIVE_FROM=@ACTIVE_FROM,ACTIVE_TO=@ACTIVE_TO WHERE CFF_MASTER_SID=@CFF_MASTER_SID AND FILE_TYPE=@FILE_TYPE";
                    query = query.replace("@CFF_MASTER_SID", projId);
                    if (dto.getFileManagementSid() == null) {
                        query = query.replace("@FILE_MANAGEMENT_SID", "null");
                    } else {
                        query = query.replace("@FILE_MANAGEMENT_SID", "'"+dto.getFileManagementSid()+"'");
                    }
                    query = query.replace("@FILE_NAME", dto.getFileName());
                    query = query.replace("@VERSION", dto.getVersion());
                    if (dto.getActiveFromDate() != null) {
                        query = query.replace("@ACTIVE_FROM", "'" + DBDate.format(dto.getActiveFromDate()) + "'");
                   } else {
                        query = query.replace("@ACTIVE_FROM", "null");
                    }
                    if (dto.getActiveToDate() != null) {
                        query = query.replace("@ACTIVE_TO", "'" + DBDate.format(dto.getActiveToDate()) + "'");

                    } else {
                        query = query.replace("@ACTIVE_TO", "null");
                    }
                    query = query.replace("@FILE_TYPE", dto.getFileTypeId());
                   // LOGGER.info("--final query--------->>>>>" + query);
                    HelperTableLocalServiceUtil.executeUpdateQuery(query);
                      }
                }  
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }

    }
}
