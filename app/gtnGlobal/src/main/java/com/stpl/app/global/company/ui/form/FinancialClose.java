/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.company.ui.form;

import com.stpl.app.global.abstractsearch.util.CommonUtils;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.company.dto.FinancialCloseDTO;
import com.stpl.app.global.company.logic.FinancialCloseLogic;
import com.stpl.app.global.company.logic.tablelogic.FinancialCloseTableLogic;
import com.stpl.app.global.company.util.FinancialCloseFilterGenerator;
import com.stpl.app.global.company.util.UIUtils;
import com.stpl.app.ui.DateToStringConverter;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.HelperDTO;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import java.util.HashMap;
import java.util.Map;
import static com.stpl.app.util.ResponsiveUtils.getResponsiveControls;
import com.stpl.app.util.ValidationUtils;
import static com.stpl.ifs.ui.util.AbstractNotificationUtils.LOGGER;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.ExtCustomTable.Align;
import java.sql.SQLException;
import java.util.Set;
import java.util.logging.Level;

/**
 *
 * @author sathyaseelan.v
 */
public final class FinancialClose extends CustomComponent {

    /**
     * The Mode Variable
     */
    @UiField("modeDdlb")
    private ComboBox modeDdlb;
    /**
     * The Month Variable
     */
    @UiField("monthDdlb")
    private ComboBox monthDdlb;
    /**
     * The Business Day Variable
     */
    @UiField("businessDayDdlb")
    private ComboBox businessDayDdlb;
    /**
     * The Hour Variable
     */
    @UiField("hourDdlb")
    private ComboBox hourDdlb;
    /**
     * The Minute Variable
     */
    @UiField("minuteDdlb")
    private ComboBox minuteDdlb;
    /**
     * The Calender Variable
     */
    @UiField("calenderDdlb")
    private ComboBox calenderDdlb;
    /**
     * The Year Variable
     */
    @UiField("yearDdlb")
    private ComboBox yearDdlb;
    /**
     * The Table Layout
     */
    @UiField("parentDetailsTableLayout")
    private VerticalLayout parentDetailsTableLayout;
    /**
     *
     */
    @UiField("resetButton")
    private Button resetButton;
    /**
     *
     */
    @UiField("openButton")
    private Button openButton;
    /**
     *
     */
    @UiField("closeButton")
    private Button closeButton;
    /**
     * The Year Number
     */
    int year;
    /**
     * The Table Logic
     */
    FinancialCloseTableLogic financialCloseTableLogic = new FinancialCloseTableLogic();
    /**
     * The Financial Close Table
     */
    private final ExtPagedTable resultTable = new ExtPagedTable(financialCloseTableLogic);
    /**
     * The Bean Item Container for Financial Close Results Table
     */
    private final BeanItemContainer<FinancialCloseDTO> availableResultsContainer = new BeanItemContainer<>(FinancialCloseDTO.class);
    /**
     * The Months List for Drop downs
     */
    List<String> months = new ArrayList<>();
    /**
     * Binder dto
     */
    FinancialCloseDTO binderDto = new FinancialCloseDTO();
    /**
     * Binder Object creation
     */

    final FinancialCloseLogic logic = new FinancialCloseLogic();
    final CommonUtils commonsUtil = new CommonUtils();
    public static final Logger LOGGER = Logger.getLogger(FinancialClose.class);
    final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
    SessionDTO sessionDTO;
    String mode;

    public ComboBox getModeDdlb() {
        return modeDdlb;
    }

    public FinancialClose(SessionDTO sessionDTO) {
        try {
            this.sessionDTO = sessionDTO;
            this.mode = this.sessionDTO.getMode();
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/companyMaster/Financial-Close.xml"), this));
            configureFields();
            configureOnViewMode();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Fields Configuration for Financial Close
     */
    private void configureFields() throws SystemException, PortalException {
        modeDdlb.setNullSelectionAllowed(true);
        commonsUtil.loadComboBox(modeDdlb, "ARM_MODE");
        modeDdlb.setNullSelectionAllowed(true);
        modeDdlb.setImmediate(true);
        loadCalendarDDLB();
        monthDdlb.setEnabled(false);
        monthCalculation();
        Calendar date = new GregorianCalendar();
        yearDdlb.setImmediate(true);
        yearDdlb.addItem(ConstantsUtils.SELECT_ONE);
        year = date.get(Calendar.YEAR) + 1;
        for (int i = year - NumericConstants.FIVE; i <= year; i++) {
            yearDdlb.addItem(i);
        }
        yearDdlb.setNullSelectionAllowed(true);
        yearDdlb.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        yearDdlb.setValue(year - 1);
        businessDayDdlb.setImmediate(true);
        businessDayDdlb.addItem(ConstantsUtils.SELECT_ONE);
        businessDayDdlb.setNullSelectionAllowed(true);
        businessDayDdlb.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        configureBusinessDayDDLBForCAlendarHolidays();
        hourDdlb.setImmediate(true);
        hourDdlb.addItem(ConstantsUtils.SELECT_ONE);
        for (int j = 1; j <= NumericConstants.TWENTY_FIVE; j++) {
            hourDdlb.addItem(j - 1);
        }
        for (int i = 1; i <= NumericConstants.TWENTY_FIVE; i++) {
            businessDayDdlb.addItem(i);
        }
        hourDdlb.setNullSelectionAllowed(true);
        hourDdlb.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);

        minuteDdlb.setImmediate(true);
        commonsUtil.loadComboBox(minuteDdlb, "ARM_MINUTE");
        minuteDdlb.setNullSelectionAllowed(true);
        hourDdlb.setEnabled(false);
        minuteDdlb.setEnabled(false);
        calenderDdlb.setEnabled(Boolean.FALSE);
        configureModeValueChange();
        configureTable();
        if (!(ConstantsUtils.ADD).equals(sessionDTO.getMode())) {
            setDdlbOnEdit(sessionDTO.getSystemId());
            binderDto.setCompanyMasterSid(sessionDTO.getSystemId());
            binderDto.setFirstTimeEdit(true);
            financialCloseTableLogic.loadSetData(binderDto);
        }

    }

    /**
     * Month List for Drop downs
     *
     * @return List
     */
    public List monthCalculation() {
        final HelperDTO defaultValue = new HelperDTO(ConstantsUtils.SELECT_ONE);
        final Map<Integer, String> monthMap = new HashMap<>();
        monthDdlb.setNullSelectionAllowed(true);
        monthDdlb.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        monthDdlb.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<>(HelperDTO.class);
        List<HelperDTO> helperList = new ArrayList<>();
        helperList.add(new HelperDTO(0, ConstantsUtils.SELECT_ONE));
        List month = logic.getMonths();
        for (int i = 1; i <= NumericConstants.TWELVE; i++) {
            helperList.add(new HelperDTO(i, String.valueOf(month.get(i))));
            monthMap.put(i, String.valueOf(month.get(i)));
        }
        resultContainer.addAll(helperList);
        monthDdlb.setContainerDataSource(resultContainer);
        Calendar date = new GregorianCalendar();
        int currentMonth = date.get(Calendar.MONTH) + 1;
        monthDdlb.select(new HelperDTO(currentMonth, monthMap.get(currentMonth)));
        monthDdlb.markAsDirty();

        monthDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    monthDdlb.select(defaultValue);
                }
            }
        });
        binderDto.setMonthMap(monthMap);
        return months;
    }

    /**
     * Mode Value Change Listener
     */
    private void configureModeValueChange() {
        modeDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (modeDdlb.getValue() != null) {
                    HelperDTO dto = (HelperDTO) modeDdlb.getValue();
                    if (dto.getDescription().equals("Auto")) {

                        hourDdlb.setEnabled(Boolean.TRUE);
                        minuteDdlb.setEnabled(Boolean.TRUE);
                        businessDayDdlb.setEnabled(Boolean.TRUE);
                        yearDdlb.setEnabled(Boolean.FALSE);
                        monthDdlb.setEnabled(Boolean.FALSE);
                        openButton.setEnabled(Boolean.FALSE);
                        calenderDdlb.setEnabled(Boolean.TRUE);
                    } else if (dto.getDescription().equals("Manual")){
                        hourDdlb.setEnabled(Boolean.FALSE);
                        minuteDdlb.setEnabled(Boolean.FALSE);
                        businessDayDdlb.setEnabled(Boolean.FALSE);
                        yearDdlb.setEnabled(Boolean.TRUE);
                        monthDdlb.setEnabled(Boolean.TRUE);
                        openButton.setEnabled(Boolean.TRUE);
                        calenderDdlb.setValue(null);
                        calenderDdlb.setEnabled(Boolean.FALSE);
                    } else if(dto.getDescription().equals("-Select One-")){
                         monthDdlb.setEnabled(Boolean.FALSE);
                         hourDdlb.setEnabled(Boolean.FALSE);
                         minuteDdlb.setEnabled(Boolean.FALSE);
                         businessDayDdlb.setEnabled(Boolean.FALSE);
                         yearDdlb.setEnabled(Boolean.TRUE);
                         calenderDdlb.setEnabled(Boolean.FALSE);
                    }
                }
            }
        });
    }

    @UiHandler("resetButton")
    public void resetButtonLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                resetLogic();
            }

            @Override
            public void noMethod() {
                return;
            }
        }.getConfirmationMessage("Reset", "Are you sure you want to reset the fields to default values?"); //Changed for GAL-7248
    }

    @UiHandler("openButton")
    public void openButtonLogic(Button.ClickEvent event) {
        if (modeDdlb.getValue() != null && !String.valueOf(modeDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE)
                && yearDdlb.getValue() != null && !String.valueOf(yearDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE)
                && monthDdlb.getValue() != null && !String.valueOf(monthDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE)
                && ConstantsUtils.AUTOMATIC.equals(String.valueOf(modeDdlb.getValue())) ? calenderDdlb.getValue() != null && !String.valueOf(calenderDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE) : Boolean.TRUE)
                 {
            final int year = Integer.valueOf(String.valueOf(yearDdlb.getValue()));
            final int month = ((HelperDTO) monthDdlb.getValue()).getId();            
            setManualOpenClose(ConstantsUtils.OPENED, year, month);
        } else {
            AbstractNotificationUtils.getErrorNotification(ConstantsUtils.ERROR, ValidationUtils.MAND_VALID);
        }
    }

    @UiHandler("closeButton")
    public void closeButtonLogic(Button.ClickEvent event) {
        if (modeDdlb.getValue() != null && !String.valueOf(modeDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE)){
            if (((HelperDTO) modeDdlb.getValue()).getDescription().equals(ConstantsUtils.MANUAL)
                     && yearDdlb.getValue() != null && !String.valueOf(yearDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE)
                && monthDdlb.getValue() != null && !String.valueOf(monthDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE))
                  {
                 final int year = Integer.valueOf(String.valueOf(yearDdlb.getValue()));
                final int month = ((HelperDTO) monthDdlb.getValue()).getId();
                binderDto.setAutomatic(Boolean.FALSE);
                setManualOpenClose(ConstantsUtils.CLOSED, year, month);
            } else if (businessDayDdlb.getValue() != null && hourDdlb.getValue() != null && minuteDdlb.getValue() != null && !String.valueOf(minuteDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE)
                    && calenderDdlb.getValue() != null && !String.valueOf(calenderDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE)) {
               setAutomaticClose(ConstantsUtils.CLOSED);
            } else {
                AbstractNotificationUtils.getErrorNotification(ConstantsUtils.ERROR, ValidationUtils.MAND_VALID);
            }
        } else {
            AbstractNotificationUtils.getErrorNotification(ConstantsUtils.ERROR, ValidationUtils.MAND_VALID);
        }
    }

    /**
     * Table Configuration
     */
    private void configureTable() {
        parentDetailsTableLayout.addComponent(resultTable);
        parentDetailsTableLayout.addComponent(getResponsiveControls(financialCloseTableLogic.createControls()));
        financialCloseTableLogic.setContainerDataSource(availableResultsContainer);
        resultTable.setSelectable(true);
        resultTable.setMultiSelect(true);
        financialCloseTableLogic.setPageLength(NumericConstants.TEN);
        financialCloseTableLogic.sinkItemPerPageWithPageLength(false);
        resultTable.setVisibleColumns(UIUtils.getInstance().FINANCIAL_CLOSE_COLUMNS);
        resultTable.setColumnHeaders(UIUtils.getInstance().FINANCIAL_CLOSE_HEADERS);
        resultTable.setFilterBarVisible(true);
        resultTable.setSizeFull();
        resultTable.setImmediate(true);
        resultTable.setPageLength(NumericConstants.FIVE);
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterGenerator(new FinancialCloseFilterGenerator());
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.addStyleName("filtertable");
        resultTable.addStyleName("table-header-normal");
        resultTable.addStyleName("filterComboBox");
        resultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultTable.setColumnAlignment("dateTime", Align.CENTER);
        resultTable.setColumnAlignment("createdDate", Align.CENTER);

        //Since we set the converter factory to the whole UI, we are overriding this with this converter
        DateToStringConverter dateTimeConverter = new DateToStringConverter();
        dateTimeConverter.setDateFormat("MM/dd/yyyy:hh:mm:ss");
        resultTable.setConverter("dateTime", dateTimeConverter);
    }

    /**
     * ManualOpenClose
     *
     * @param openOrClose
     * @param year
     * @param month
     */
    public void setManualOpenClose(final String openOrClose, final int year, final int month) {
        boolean flag = isCanOpenOrClose(openOrClose, year, month);
        String status = openOrClose;
        if (ConstantsUtils.OPENED.equals(openOrClose)) {
            status = ConstantsUtils.OPEN;
        } else if (ConstantsUtils.CLOSED.equals(openOrClose)) {
            status = ConstantsUtils.CLOSE;
        }
        if (flag) {
            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    try {
                        setBinderDto(year, month, openOrClose);
                        if (!(ConstantsUtils.ADD).equals(sessionDTO.getMode())) {
                            binderDto.setCompanyMasterSid(sessionDTO.getSystemId());                            
                        }                         
                        binderDto.setGenerate(true);
                        financialCloseTableLogic.loadSetData(binderDto);
                        CommonUIUtils.successNotification("Status '" + openOrClose + "' updated sucessfully for the selected Year/Month");
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }

                @Override
                public void noMethod() {
                    return;
                }
            }.getConfirmationMessage(status, "Are you sure you want to " + status + " the Month selected?");
        } else {
            AbstractNotificationUtils.getErrorNotification(status, "The Month/Year is already in " + openOrClose.substring(0,1) + openOrClose.substring(1).toLowerCase());
        }
    }

    /**
     * Setting a values to DTO
     *
     * @param year
     * @param month
     * @param openOrClose
     * @throws Exception
     */
    private void setBinderDto(final int year, final int month, final String openOrClose) throws PortalException, SystemException {
        HelperDTO modeValue = (HelperDTO) modeDdlb.getValue();
        Integer calendarDto = (Integer) calenderDdlb.getValue();
        String calendarValue = String.valueOf(calenderDdlb.getItemCaption(calendarDto));
        binderDto.setMode(String.valueOf(modeValue.getId()));
        binderDto.setCalendar(calendarValue);
        binderDto.setCreatedBy(userId);
        binderDto.setYear(year != 0 ? String.valueOf(year) : StringUtils.EMPTY);
        binderDto.setMonth(month != 0 ? String.valueOf(month) : StringUtils.EMPTY);
        binderDto.setModeDdlb(modeValue);
        binderDto.setCalenderDdlb(calendarDto);
        int statusId = FinancialCloseLogic.getHelperCode("ARM_STATUS", openOrClose);
        binderDto.setStatus(String.valueOf(statusId));
        binderDto.setStatusDdlb(new HelperDTO(statusId, openOrClose));
        binderDto.setCompanyMode(sessionDTO.getMode());
        if (modeValue.getDescription().equalsIgnoreCase(ConstantsUtils.AUTOMATIC)) {
            HelperDTO minuteDdlbValue = (HelperDTO) minuteDdlb.getValue();
            binderDto.setMinuteDdlb(minuteDdlbValue);
            binderDto.setHourDdlb(String.valueOf(hourDdlb.getValue()));
            binderDto.setBusinessDayDdlb(String.valueOf(businessDayDdlb.getValue()));
        }
    }

    /**
     * isCanOpenOrClose
     *
     * @param openOrClose
     * @param year
     * @param month
     * @return boolean true
     */
    private boolean isCanOpenOrClose(final String openOrClose, final int year, final int month) {
        boolean flag = false;
            String status = StringUtils.EMPTY;
            if (financialCloseTableLogic.getResultsContainer().size() == 0) {
                status = ConstantsUtils.OPENED;
            } else {
                List<FinancialCloseDTO> openDto = new ArrayList<>(financialCloseTableLogic.getResultsContainer().getItemIds());
                List<FinancialCloseDTO> sortableList = new ArrayList<>();
                sortableList.addAll(openDto);

                for (FinancialCloseDTO dto : sortableList) {
                    if (Integer.valueOf(dto.getYear()) == year && Integer.valueOf(dto.getMonth()) == month) {
                        status = dto.getStatusDdlb().getDescription();
                        break;
                    } else {
                        status = ConstantsUtils.OPENED;
                    }
                }
            }
            if (openOrClose.equals(ConstantsUtils.OPENED)) {
                if (status.equalsIgnoreCase(ConstantsUtils.CLOSED)) {
                    flag = true;
                }
            } else if (openOrClose.equalsIgnoreCase(ConstantsUtils.CLOSED) && status.equalsIgnoreCase(ConstantsUtils.OPENED)) {
                    flag = true;
            }
        return flag;
    }

    /**
     * Method to set automatic values
     */
    private void setAutomaticClose(final String openOrClose) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    int year = 0;
                    int month = 0;
                    setBinderDto(year, month, openOrClose);
                    FinancialCloseLogic.setAutomaticFinancialRecord(binderDto);
                    CommonUIUtils.successNotification("Status '" + openOrClose + "' updated sucessfully for the selected Year/Month");
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

            @Override
            public void noMethod() {
                return;
            }
        }.getConfirmationMessage("Close", "Are you sure you want to Close the Month selected?");
    }

    /**
     * Method to pre populate the Ddlbs values on Edit and view
     */
    private void setDdlbOnEdit(final int companyMasterSid) {
        List<FinancialCloseDTO> list = null;
        try {
            list = logic.getDdlbValuesOnEdit(companyMasterSid);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FinancialClose.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!list.isEmpty()) {
            FinancialCloseDTO dto = list.get(0);
            modeDdlb.setValue(dto.getModeDdlb());
            monthDdlb.setValue(new HelperDTO(Integer.valueOf(dto.getMonth()), binderDto.getMonthMap().get(Integer.valueOf(dto.getMonth()))));
            businessDayDdlb.setValue(Integer.valueOf(dto.getBusinessDayDdlb()));
            hourDdlb.setValue(Integer.valueOf(dto.getHourDdlb()));
            minuteDdlb.setValue(dto.getMinuteDdlb());
            calenderDdlb.setValue(dto.getCalenderDdlb());
            yearDdlb.setValue(Integer.valueOf(dto.getYear()));
        } else {
            binderDto.setAutomatic(Boolean.TRUE);
            List<Object[]> values = logic.getDdlbValues(companyMasterSid);
            for(Object[] obj : values) {
                HelperDTO dto = new HelperDTO();
                dto.setId(Integer.parseInt(String.valueOf(obj[1])));
                dto.setDescription(String.valueOf(obj[NumericConstants.TWO]));
                HelperDTO dto1 = new HelperDTO();
                dto1.setId(Integer.parseInt(String.valueOf(obj[NumericConstants.SIX])));
                dto1.setDescription(String.valueOf(obj[NumericConstants.SEVEN]));
                modeDdlb.setValue(dto);
                businessDayDdlb.setValue(Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR])));
                hourDdlb.setValue(Integer.valueOf(String.valueOf(obj[NumericConstants.FIVE])));
                minuteDdlb.setValue(dto1);
                calenderDdlb.setValue(obj[NumericConstants.THREE]);
            }
        }
    }

    void configureOnViewMode() {
        if (sessionDTO.getMode().equalsIgnoreCase("View")) {
            modeDdlb.setReadOnly(true);
            monthDdlb.setReadOnly(true);
            businessDayDdlb.setReadOnly(true);
            hourDdlb.setReadOnly(true);
            minuteDdlb.setReadOnly(true);
            calenderDdlb.setReadOnly(true);
            yearDdlb.setReadOnly(true);
            resetButton.setEnabled(false);
            closeButton.setEnabled(false);
            openButton.setEnabled(false);
        }
    }

    public void resetLogic() {
        try {
            if (ConstantsUtils.EDIT.equals(sessionDTO.getMode())) {
                setDdlbOnEdit(sessionDTO.getSystemId());
            } else {
                modeDdlb.setValue(new HelperDTO(ConstantsUtils.SELECT_ONE));
                monthCalculation();
                businessDayDdlb.setValue(null);
                hourDdlb.setValue(null);
                minuteDdlb.setValue(new HelperDTO(ConstantsUtils.SELECT_ONE));
                calenderDdlb.setValue(null);
                yearDdlb.setValue(year - 1);
                yearDdlb.setEnabled(Boolean.TRUE);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
    public void resetAddMode() {
        try {
            binderDto.setReset(true);
            financialCloseTableLogic.loadSetData(binderDto);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
    /**
     * Default focus
     */
    
    void setDefaultFocus() {
        modeDdlb.focus();
    }

    private void loadCalendarDDLB() {
        logic.loadCalendarDDLB(calenderDdlb);
        configurForCalenderDdlbCAlendarHolidays();

    }

    private void configureBusinessDayDDLBForCAlendarHolidays() {
        businessDayDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                validateBusineesDay();
            }

        });

    }

    private void validateBusineesDay() {    
        if (businessDayDdlb.getValue() != null && calenderDdlb.getValue() != null) {
            Set expectionalSet = logic.checkIsthereBusinessdayForMonths((Integer) businessDayDdlb.getValue(), (Integer) calenderDdlb.getValue());
            if (!expectionalSet.isEmpty()) {
                AbstractNotificationUtils.getErrorNotification("", " Business Day cannot be assigned for this calendar" + expectionalSet.size());
                businessDayDdlb.setValue(null);
            }
        }
    }

    private void configurForCalenderDdlbCAlendarHolidays() {
        calenderDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                validateBusineesDay();
            }

        });
    }
}
