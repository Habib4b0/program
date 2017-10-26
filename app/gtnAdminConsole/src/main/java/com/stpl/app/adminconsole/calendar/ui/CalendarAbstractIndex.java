/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.calendar.ui;

import com.stpl.app.adminconsole.calendar.createcalendar.logic.CalendarLogic;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.serviceUtils.CommonUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Abishek.Ram
 */
public abstract class CalendarAbstractIndex extends CustomComponent {

    private final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(CalendarAbstractIndex.class);

    GregorianCalendar tempCalendar = new GregorianCalendar();
    int currentyearValue = Calendar.getInstance().getTime().getYear();
    CalendarLogic clLogic = new CalendarLogic();

    SessionDTO sessionDTO;

    /**
     * Calendar Names
     */
    @UiField("calendarNameTB")
    public TextField calendarNameTB;

    @UiField("labelCalendarName")
    public Label labelCalendarName;

    @UiField("calendarDescTB")
    public TextField calendarDescTB;

    @UiField("labelCalendarDesc")
    public Label labelCalendarDesc;

    @UiField("calendarYearDDLB")
    public ComboBox calendarYearDDLB;

    @UiField("labelCalendarYear")
    public Label labelCalendarYear;

    @UiField("countryDDLB")
    public ComboBox countryDDLB;

    @UiField("labelCountry")
    public Label labelCountry;

    @UiField("resultsTableLayoutRes")
    public VerticalLayout resultsTableLayoutRes;

    @UiField("resultView")
    public Panel resultList;

    @UiField("searchFormLayoutRes")
    public HorizontalLayout searchFormLayoutRes;

    @UiField("searchActionLayout")
    public HorizontalLayout searchActionLayout;

    @UiField("searchLayout")
    public GridLayout searchLayout;

    @UiField("calendarLayout")
    public VerticalLayout calendarLayout;

    @UiField("saveAcction")
    public HorizontalLayout saveAcctionLayout;

    @UiField("calMainPanel")
    public Panel calMainPanel;

    @UiField("calSearchPanel")
    public Panel calSearchPanel;

    @UiField("calMainLayout")
    public VerticalLayout calMainLayout;

    @UiField("resultsMainTableLayoutRes")
    public VerticalLayout resultsMainTableLayoutRes;
    @UiField("calSearchVLayout")
    public VerticalLayout calSearchVLayout;
    @UiField("calSearchHLayout")
    public HorizontalLayout calSearchHLayout;
    /**
     * Excel Export button instance
     */
    @UiField("excelExport")
    public Button excelExport;

    /**
     *
     * @param sessionDTO
     */
    public CalendarAbstractIndex(SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/calenderConfigurationSearch.xml"), this));
        configurePermission();
        configureFields();
    }

    private void configureFields() {
        loadYearDDLB();
        loadCountryDDLB();
        calendarNameTB.focus();
        calMainPanel.setSizeFull();
        calMainLayout.setSizeFull();
        calSearchVLayout.setSizeUndefined();
        calSearchHLayout.setSizeUndefined();
        searchLayout.setSizeUndefined();
        calSearchPanel.setSizeFull();
        resultList.setSizeFull();
        resultsMainTableLayoutRes.setSizeFull();
        resultsTableLayoutRes.setSizeFull();
        setExcelButtonVisible();
    }

    private void loadYearDDLB() {
        LOGGER.debug("Loading with Current Year - " + tempCalendar.get(Calendar.YEAR));
        Set yearSet = new TreeSet();
        int currentYear = tempCalendar.get(Calendar.YEAR);
        calendarYearDDLB.addItem(CommonUtils.SELECT_ONE);
        yearSet.add(currentYear);
        for (int i = 1; i < NumericConstants.FOUR; i++) {
            yearSet.add(currentYear + i);
            yearSet.add(currentYear - i);
        }
        calendarYearDDLB.addItems(yearSet);
        calendarYearDDLB.setNullSelectionAllowed(true);
        calendarYearDDLB.setNullSelectionItemId(CommonUtils.SELECT_ONE);
    }

    private void loadCountryDDLB() {
        countryDDLB.addItems(clLogic.loadCountry());
        countryDDLB.setNullSelectionAllowed(true);
        countryDDLB.addItem(CommonUtils.SELECT_ONE);
        countryDDLB.setNullSelectionItemId(CommonUtils.SELECT_ONE);

    }

    /**
     *
     * @param value
     * @return
     */
    public boolean validateNull(Object value) {
        String stringified = String.valueOf(value);
        return !(stringified.equals("null") || stringified.trim().isEmpty());
    }

    protected void setExcelButtonVisible() {
        excelExport.setVisible(Boolean.FALSE);
    }

    public void configurePermission() {
        try {

            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(sessionDTO.getUserId());
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Calender Configuration" + "," + "Calender Configuration Add screen");

            if (functionHM.get("calendarNameTB") != null && !((AppPermission) functionHM.get("calendarNameTB")).isFunctionFlag()) {
                calendarNameTB.setVisible(false);
                labelCalendarName.setVisible(false);
            } else {
                calendarNameTB.setVisible(true);
                labelCalendarName.setVisible(true);
            }
            if (functionHM.get("calendarDescTB") != null && !((AppPermission) functionHM.get("calendarDescTB")).isFunctionFlag()) {
                calendarDescTB.setVisible(false);
                labelCalendarDesc.setVisible(false);
            } else {
                calendarDescTB.setVisible(true);
                labelCalendarDesc.setVisible(true);
            }
            if (functionHM.get("calendarYearDDLB") != null && !((AppPermission) functionHM.get("calendarYearDDLB")).isFunctionFlag()) {
                calendarYearDDLB.setVisible(false);
                labelCalendarYear.setVisible(false);
            } else {
                calendarYearDDLB.setVisible(true);
                labelCalendarYear.setVisible(true);
            }
            if (functionHM.get("countryDDLB") != null && !((AppPermission) functionHM.get("countryDDLB")).isFunctionFlag()) {
                countryDDLB.setVisible(false);
                labelCountry.setVisible(false);
            } else {
                countryDDLB.setVisible(true);
                labelCountry.setVisible(true);
            }
            if (functionHM.get("calendarLayout") != null && !((AppPermission) functionHM.get("calendarLayout")).isFunctionFlag()) {
                calendarLayout.setVisible(false);

            } else {
                calendarLayout.setVisible(true);

            }

        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }
}
