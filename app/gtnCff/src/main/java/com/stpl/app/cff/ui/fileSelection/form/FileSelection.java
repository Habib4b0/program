/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.fileSelection.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.cff.dao.CommonServiceImpl;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.lazyLoad.FileSelectionTableLogic;
import com.stpl.app.cff.logic.CFFLogic;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.security.StplSecurity;
import com.stpl.app.cff.ui.ConsolidatedFinancialForecastUI;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.ui.fileSelection.dto.FileSelectionDTO;
import com.stpl.app.cff.ui.fileSelection.dto.FileSelectionTableGenerator;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.TableResultCustom;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Panel;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.converter.StringToDateConverter;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.VerticalLayout;

/**
 *
 * @author mohamed.hameed
 */
public class FileSelection extends CustomComponent {

    private final FileSelectionTableLogic tableLogic = new FileSelectionTableLogic();
    private final ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private final VerticalLayout layout = new VerticalLayout();
    private final BeanItemContainer<FileSelectionDTO> searchContainer = new BeanItemContainer<>(FileSelectionDTO.class);
    private final SessionDTO sessionDTO;
    private final Button excelExport = new Button();
    private final CFFLogic cffLogic = new CFFLogic();
    private final SimpleDateFormat DBDate = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger LOGGER = LoggerFactory.getLogger(FileSelection.class);
    private final ComboBox businessUnit;
    private final CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();

    public FileSelection(SessionDTO sessionDTO, ComboBox businessUnit) {
        this.setCompositionRoot(addComponent());
        this.sessionDTO = sessionDTO;
        this.businessUnit = businessUnit;
        configureFields();
    }

    Component addComponent() {
        Panel mainPanel = new Panel(StringConstantsUtil.FILE_SELECTION);
        mainPanel.setContent(addResultTable());
        return mainPanel;
    }

    private void configureFields() {
        LOGGER.debug("configureFields starts");
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, "Consolidated Financial Forecast" + ConstantsUtils.COMMA + StringConstantsUtil.FILE_SELECTION, false);
            List<Object> resultList = getFieldsForSecurity("Consolidated Financial Forecast", StringConstantsUtil.FILE_SELECTION);
            Object[] obj = CommonUtils.getInstance().visibleColumnItemSearch;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM, CommonSecurityLogic.ADD);
            if (tableResultCustom.getObjResult().length == 0) {
                resultsTable.setVisible(false);
            }
            tableLogic.setContainerDataSource(searchContainer);
            tableLogic.setPageLength(NumericConstants.TEN);
            tableLogic.sinkItemPerPageWithPageLength(false);
            resultsTable.setVisibleColumns(tableResultCustom.getObjResult());
            resultsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            resultsTable.setColumnAlignment(CommonUtils.getInstance().visibleColumnItemSearch[NumericConstants.TWO], ExtCustomTable.Align.CENTER);
            resultsTable.setColumnAlignment(CommonUtils.getInstance().visibleColumnItemSearch[NumericConstants.THREE], ExtCustomTable.Align.CENTER);
            resultsTable.setColumnAlignment(CommonUtils.getInstance().visibleColumnItemSearch[NumericConstants.FOUR], ExtCustomTable.Align.CENTER);
            resultsTable.setColumnAlignment(CommonUtils.getInstance().visibleColumnItemSearch[0], ExtCustomTable.Align.CENTER);
            resultsTable.setColumnAlignment(CommonUtils.getInstance().visibleColumnItemSearch[1], ExtCustomTable.Align.CENTER);

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
            resultsTable.setTableFieldFactory(new FileSelectionTableGenerator(searchContainer, sessionDTO, String.valueOf(businessUnit.getValue())));
            tableLogic.setSearchData(sessionDTO, String.valueOf(businessUnit.getValue()));

            resultsTable.setConverter("activeFromDate", new StringToDateConverter());
            resultsTable.setConverter("activeToDate", new StringToDateConverter());
            for (Object propertyId : resultsTable.getVisibleColumns()) {
                resultsTable.setColumnWidth(propertyId, -1);
            }
            resultsTable.setEditable(true);
            excelExport.addClickListener(new Button.ClickListener() {
                /**
                 * calls excelExportLogic method on button click
                 *
                 * @param event - Mouse Click event
                 */
                @Override
                public void buttonClick(final Button.ClickEvent event) {
                    try {
                        LOGGER.debug("Entering EXCEL Export Button Click");
                        ConsolidatedFinancialForecastUI.setEXCEL_CLOSE(true);
                        final ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(resultsTable), StringConstantsUtil.FILE_SELECTION, StringConstantsUtil.FILE_SELECTION, "FileSelection.xls", false);
                        excel.export();
                        LOGGER.debug(" Ends  EXCEL Export Button Click");

                    } catch (Exception exception) {
                        LOGGER.error(exception.getMessage());
                    }
                }

            });
        } catch (PortalException | SystemException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("configureFields ends");
    }

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
        layout.addComponent(resultsTable);
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
                    if (!"null".equals(projId)) {
                        String query = "INSERT INTO dbo.CFF_FILE_SELECTION (CFF_MASTER_SID, FILE_MANAGEMENT_SID, FILE_NAME, VERSION, ACTIVE_FROM, ACTIVE_TO, FILE_TYPE) \n"
                                + "	VALUES(@CFF_MASTER_SID, @FILE_MANAGEMENT_SID, '@FILE_NAME', '@VERSION', @ACTIVE_FROM, @ACTIVE_TO, @FILE_TYPE)";

                        query = query.replace("@CFF_MASTER_SID", projId);
                        if (dto.getFileManagementSid() == null) {
                            query = query.replace(StringConstantsUtil.FILE_MANAGEMENT_SID, "null");
                        } else {
                            query = query.replace(StringConstantsUtil.FILE_MANAGEMENT_SID, "'" + dto.getFileManagementSid() + "'");
                        }
                        query = query.replace("@FILE_NAME", dto.getFileName());
                        query = query.replace("@VERSION", dto.getVersion());
                        if (dto.getActiveFromDate() != null) {
                            query = query.replace(StringConstantsUtil.ACTIVE_FROM, "'" + DBDate.format(dto.getActiveFromDate()) + "'");
                        } else {
                            query = query.replace(StringConstantsUtil.ACTIVE_FROM, "null");
                        }
                        if (dto.getActiveToDate() != null) {
                            query = query.replace(StringConstantsUtil.ACTIVE_TO, "'" + DBDate.format(dto.getActiveToDate()) + "'");

                        } else {
                            query = query.replace(StringConstantsUtil.ACTIVE_TO, "null");
                        }
                        query = query.replace("@FILE_TYPE", dto.getFileTypeId());
                        LOGGER.debug("--final query--------->>>>> {}",query);
                        HelperTableLocalServiceUtil.executeUpdateQuery(query);
                    }
                }
            } else {
                List<FileSelectionDTO> fileList = searchContainer.getItemIds();
                for (int i = 0; i < fileList.size(); i++) {
                    FileSelectionDTO dto = fileList.get(i);
                    if (dto.isFileChanged()) {
                        String query = " update CFF_FILE_SELECTION set FILE_MANAGEMENT_SID=@FILE_MANAGEMENT_SID,FILE_NAME='@FILE_NAME',"
                                + "VERSION=@VERSION,ACTIVE_FROM=@ACTIVE_FROM,ACTIVE_TO=@ACTIVE_TO WHERE CFF_MASTER_SID=@CFF_MASTER_SID AND FILE_TYPE=@FILE_TYPE";
                        query = query.replace("@CFF_MASTER_SID", projId);
                        if (dto.getFileManagementSid() == null) {
                            query = query.replace(StringConstantsUtil.FILE_MANAGEMENT_SID, "null");
                        } else {
                            query = query.replace(StringConstantsUtil.FILE_MANAGEMENT_SID, "'" + dto.getFileManagementSid() + "'");
                        }
                        query = query.replace("@FILE_NAME", dto.getFileName());
                        query = query.replace("@VERSION", dto.getVersion());
                        if (dto.getActiveFromDate() != null) {
                            query = query.replace(StringConstantsUtil.ACTIVE_FROM, "'" + DBDate.format(dto.getActiveFromDate()) + "'");
                        } else {
                            query = query.replace(StringConstantsUtil.ACTIVE_FROM, "null");
                        }
                        if (dto.getActiveToDate() != null) {
                            query = query.replace(StringConstantsUtil.ACTIVE_TO, "'" + DBDate.format(dto.getActiveToDate()) + "'");

                        } else {
                            query = query.replace(StringConstantsUtil.ACTIVE_TO, "null");
                        }
                        query = query.replace("@FILE_TYPE", dto.getFileTypeId());
                        HelperTableLocalServiceUtil.executeUpdateQuery(query);
                    }
                }
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

    }

    public void refreshTable() {
        resultsTable.setTableFieldFactory(new FileSelectionTableGenerator(searchContainer, sessionDTO, String.valueOf(businessUnit.getValue())));
        tableLogic.setSearchData(sessionDTO, String.valueOf(businessUnit.getValue()));
    }

    /**
     * Search forecast results to load table .
     *
     * @param forecastDTO the forecast dto
     * @return object of list or count
     */
    public List<Object> getFieldsForSecurity(String moduleName, String tabName) {
        List<Object> resultList = new ArrayList<>();
        try {
            resultList = CommonServiceImpl.getInstance().fetchFieldsForSecurity(moduleName, tabName);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return resultList;
    }
}
