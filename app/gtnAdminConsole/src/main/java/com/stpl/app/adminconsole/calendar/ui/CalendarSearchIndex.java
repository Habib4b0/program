/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.calendar.ui;

import com.stpl.app.adminconsole.calendar.createcalendar.logic.dto.CalendarDetailsDTO;
import com.stpl.app.adminconsole.calendar.createcalendar.logic.tableLogic.SearchTableLogic;
import com.stpl.app.adminconsole.calendar.ui.view.CalendarSaveView;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.Message;
import com.stpl.app.adminconsole.util.MessageUtil;
import static com.stpl.app.adminconsole.util.ResponsiveUtils.getResponsiveControl;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.ImtdIfpDetailsLocalServiceUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.converter.StringToIntegerConverter;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilteringTableConstant;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Abishek.Ram
 */
public class CalendarSearchIndex extends CalendarAbstractIndex {

    private final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(CalendarSearchIndex.class);

    @UiField("addBtnRes")
    Button addButton;

    @UiField("editBtnRes")
    Button editButton;

    @UiField("viewBtnRes")
    Button viewButton;

    @UiField("deleteBtnRes")
    Button deleteButton;

    @UiField("copyBtnRes")
    Button copyButton;

    @UiField("searchBtnRes")
    Button searchButton;

    @UiField("resetBtnRes")
    Button resetButton;

    BeanItemContainer<CalendarDetailsDTO> calendarDetailContainer = new BeanItemContainer<>(CalendarDetailsDTO.class);
    SearchTableLogic searchTableLogic = new SearchTableLogic();
    ExtPagedTable resultsTable = new ExtPagedTable(searchTableLogic);
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    Object[] visibleColumn = new String[]{"calendarName", "calendarDescription", "calendarYear", "createdBy", CommonUtils.CREATED_DATE, "modifiedBy", CommonUtils.MODIFIED_DATE};
    String[] header = new String[]{"Calendar Name", "Calendar Description", "Calendar Year", "Created By", "Created Date", "Modified By", "Modified Date"};
    List excelData = null;

    /**
     *
     * @param sessionDTO
     */
    public CalendarSearchIndex(SessionDTO sessionDTO) {
        super(sessionDTO);
        configurePermission();
        configureForSearch();
    }

    private void loadTable() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, "Calender Configuration" + ConstantsUtils.COMMA + "Result screen", false);
            String mode = sessionDTO.getMode();
            List<Object> resultList = getFieldsForSecurity("Calender Configuration", "Result screen");
            Object[] obj = visibleColumn;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM, "Add");
            if (tableResultCustom.getObjResult().length == 0) {
                resultsTable.setVisible(false);
            }
            resultsTable.setSizeFull();
            resultsTable.addStyleName(ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE);
            resultsTable.addStyleName("filterbar");
            resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            resultsTable.setImmediate(true);
            resultsTableLayoutRes.addComponent(resultsTable);
            resultsTableLayoutRes.addComponent(getResponsiveControl(searchTableLogic.createControls()));
            searchTableLogic.setContainerDataSource(calendarDetailContainer);
            resultsTable.setVisibleColumns(tableResultCustom.getObjResult());
            resultsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            resultsTable.setSelectable(true);
            resultsTable.setFilterBarVisible(true);
            searchTableLogic.setPageLength(NumericConstants.TEN);
            searchTableLogic.sinkItemPerPageWithPageLength(false);

            configureDateFormatColumn();
            configureIntegerColumn();
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }

    private void configureIntegerColumn() {
        Converter integerToString = new StringToIntegerConverter() {
            @Override
            public String convertToPresentation(Integer value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
                return String.valueOf(value); //To change body of generated methods, choose Tools | Templates.
            }
        };
        resultsTable.setConverter("calendarYear", integerToString);
    }

    private void configureDateFormatColumn() {

        final DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Converter dateToString = new Converter<String, Date>() {
            @Override
            public Date convertToModel(String value, Class<? extends Date> targetType, Locale locale) throws Converter.ConversionException {
                try {
                    return format.parse(value);
                } catch (ParseException ex) {
                    Logger.getLogger(CalendarSearchIndex.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }

            @Override
            public String convertToPresentation(Date value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
                if (value != null) {
                    return format.format(value);
                } else {
                    return "";
                }
            }

            @Override
            public Class<Date> getModelType() {
                return Date.class;
            }

            @Override
            public Class<String> getPresentationType() {
                return String.class;
            }
        };

        resultsTable.setConverter(CommonUtils.CREATED_DATE, dateToString);
        resultsTable.setConverter(CommonUtils.MODIFIED_DATE, dateToString);
        resultsTable.setColumnAlignment(CommonUtils.CREATED_DATE, ExtPagedTable.ALIGN_CENTER);
        resultsTable.setColumnAlignment(CommonUtils.MODIFIED_DATE, ExtPagedTable.ALIGN_CENTER);
    }

    private void configureForSearch() {
        calendarLayout.setVisible(false);
        saveAcctionLayout.setVisible(false);
        loadTable();
        configureButtons();
    }

    private void configureButtons() {
        configureAddButton();
        configureEditButton();
        configureViewButton();
        configureDeleteButton();
        configureCopyButton();
        configureSearchButton();
        configureResetButon();
    }

    private void configureEditButton() {
        editButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().getNavigator().addView(CalendarSaveView.NAME, new CalendarSaveView(sessionDTO, getTableValue(), "edit"));
                getUI().getNavigator().navigateTo(CalendarSaveView.NAME);
            }
        });
    }

    private void configureAddButton() {
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().getNavigator().addView(CalendarSaveView.NAME, new CalendarSaveView(sessionDTO, new CalendarDetailsDTO(), "add"));
                getUI().getNavigator().navigateTo(CalendarSaveView.NAME);
            }
        });
    }

    private void configureViewButton() {
        viewButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().getNavigator().addView(CalendarSaveView.NAME, new CalendarSaveView(sessionDTO, getTableValue(), "view"));
                getUI().getNavigator().navigateTo(CalendarSaveView.NAME);
            }
        });
    }

    private void configureDeleteButton() {
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getTableValue();
                final AbstractNotificationUtils notificationUtils = new AbstractNotificationUtils() {
                    /**
                     * Executed by clicking yes .
                     */
                    @SuppressWarnings("PMD")
                    @Override
                    public void yesMethod() {
                        if (clLogic.validateBeforeDelete(getTableValue())) {
                            AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.ERROR_HEADER), "Calendar cannot be deleted as it is associated to Company");
                        } else {
                            clLogic.deleteRecord(getTableValue());
                            searchTableLogic.loadsetData(validateMap(getSearchMap()), true);
                            resultsTable.setValue(null);
                        }
                    }

                    /**
                     * Executed by clicking No
                     */
                    @Override
                    public void noMethod() {

                    }
                };
                notificationUtils.getConfirmationMessage(ConstantsUtils.CONFORMATION, "Are you sure you want to delete the selected Calendar?");

            }
        });

    }

    private void configureCopyButton() {
        copyButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().getNavigator().addView(CalendarSaveView.NAME, new CalendarSaveView(sessionDTO, getTableValue(), "copy"));
                getUI().getNavigator().navigateTo(CalendarSaveView.NAME);
            }
        });
    }

    private void configureSearchButton() {
        searchButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!searchTableLogic.loadsetData(validateMap(getSearchMap()), true)) {
                    CommonUtils.successNotification(MessageUtil.getMessage(Message.NO_RESULTS_FOUND));
                } else {
                    CommonUtils.successNotification(MessageUtil.getMessage(Message.SEARCH_COMPLETED));
                }
            }
        });
    }

    private Map validateMap(Map<String, String> searchMap) {
        if (searchMap.isEmpty()) {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.ERROR_HEADER), MessageUtil.getMessage(Message.NO_SEARCH_VALUE));
            throw new IllegalArgumentException("No search criteria");
        }
        return searchMap;
    }

    private CalendarDetailsDTO getTableValue() {
        if (resultsTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.ERROR_HEADER), MessageUtil.getMessage(Message.NO_CALENDAR_SELECTED));
            throw new IllegalArgumentException("No table Value");
        } else {
            CalendarDetailsDTO tbValue = (CalendarDetailsDTO) resultsTable.getValue();
            return tbValue;
        }
    }

    private Map getSearchMap() {
        Map searchMap = new HashMap<String, String>();
        addToMapIfNotEmpty(getSearchValueFromComponent(calendarNameTB), searchMap, "CALENDAR_NAME");
        addToMapIfNotEmpty(getSearchValueFromComponent(calendarDescTB), searchMap, "CALENDAR_DESCRIPTION");
        addToMapIfNotEmpty(getSearchValueFromComponent(calendarYearDDLB), searchMap, "CALENDAR_YEAR");
        addToMapIfNotEmpty(getSearchValueFromComponent(countryDDLB), searchMap, "COUNTRY");
        return searchMap;
    }

    private void addToMapIfNotEmpty(String value, Map map, String key) {
        if (!(value.equals("null") || value.isEmpty())) {
            map.put(key, value.replaceAll("[*]", "%"));
        }
    }

    private String getSearchValueFromComponent(AbstractField field) {
        return String.valueOf(field.getValue());
    }

    /**
     *
     */
    public void setToDefault() {
        countryDDLB.setValue(null);
        calendarNameTB.setValue("");
        calendarNameTB.focus();
        calendarDescTB.setValue("");
        calendarYearDDLB.setValue(null);
        searchTableLogic.loadsetData(new HashMap(), false);
        resultsTable.removeAllItems();
    }

    private void configureResetButon() {
        resetButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                new AbstractNotificationUtils() {
                    @Override
                    public void noMethod() {

                    }

                    @Override
                    public void yesMethod() {
                        setToDefault();
                    }
                }.getConfirmationMessage(ConstantsUtils.CONFORMATION, "Are you sure you want to Reset the screen?");
            }
        });
    }

    @Override
    protected void setExcelButtonVisible() {
        excelExport.setVisible(Boolean.TRUE);
        excelExport.setIcon(new ThemeResource("../../icons/excel.png"), "Excel Export");
    }

    @UiHandler("excelExport")
    public void excelExportButtonClick(Button.ClickEvent event) {
        try {
            createWorkSheet("Calendar Configuration", resultsTable);
            excelData = null;
        } catch (SystemException | PortalException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            LOGGER.error(ex);
        }
    }

    public void createWorkSheet(String moduleName, ExtPagedTable resultTable) throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        long recordCount = 0;
        List<String> visibleList = Arrays.asList(header);
        if (resultTable.size() != 0) {
            excelData = clLogic.getcalendar(0, 0, searchTableLogic.getSortByColumns(), getSearchMap(), searchTableLogic.getFilters(), true);
            recordCount = excelData.size();
        }
        ExcelExportforBB.createWorkSheet(visibleList.toArray(new String[visibleList.size()]), recordCount, this, UI.getCurrent(), moduleName.toUpperCase());
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException {

        List visibleList = Arrays.asList(visibleColumn);
        try {
            if (end != 0) {
                ExcelExportforBB.createFileContent(visibleList.toArray(), excelData, printWriter);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void configurePermission() {
        try {

            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(sessionDTO.getUserId());
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Calender Configuration" + "," + "Landing screen");
            if (functionHM.get("addButton") != null && !((AppPermission) functionHM.get("addButton")).isFunctionFlag()) {
                addButton.setVisible(false);
            } else {
                addButton.setVisible(true);
            }
            if (functionHM.get("editButton") != null && !((AppPermission) functionHM.get("editButton")).isFunctionFlag()) {
                editButton.setVisible(false);
            } else {
                editButton.setVisible(true);
            }
            if (functionHM.get("viewButton") != null && !((AppPermission) functionHM.get("viewButton")).isFunctionFlag()) {
                viewButton.setVisible(false);
            } else {
                viewButton.setVisible(true);
            }
            if (functionHM.get("deleteButton") != null && !((AppPermission) functionHM.get("deleteButton")).isFunctionFlag()) {
                deleteButton.setVisible(false);
            } else {
                deleteButton.setVisible(true);
            }
            if (functionHM.get("copyButton") != null && !((AppPermission) functionHM.get("copyButton")).isFunctionFlag()) {
                copyButton.setVisible(false);
            } else {
                copyButton.setVisible(true);
            }
            if (functionHM.get("searchButton") != null && !((AppPermission) functionHM.get("searchButton")).isFunctionFlag()) {
                searchButton.setVisible(false);
            } else {
                searchButton.setVisible(true);
            }
            if (functionHM.get("resetButton") != null && !((AppPermission) functionHM.get("resetButton")).isFunctionFlag()) {
                resetButton.setVisible(false);
            } else {
                resetButton.setVisible(true);
            }

        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Search forecast results to load table .
     *
     * @param forecastDTO the forecast dto
     * @return object of list or count
     */
    public List<Object> getFieldsForSecurity(String moduleName, String tabName) {
        List<Object> resultList = new ArrayList<Object>();
        try {
            resultList = ImtdIfpDetailsLocalServiceUtil.fetchFieldsForSecurity(moduleName, tabName, null, null, null);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }
}
