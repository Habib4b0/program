/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.ui.form.AdjustmentDetails;

import com.stpl.app.util.CommonUtils;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Window;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Rohit.Vignesh
 */
public class RedemptionPeriod extends Window {

    private static final Logger LOGGER = Logger.getLogger(RedemptionPeriod.class);

    @UiField("submitBtn")
    public Button submitBtn;

    @UiField("closeBtn")
    public Button closeBtn;

    @UiField("creationDateFrom")
    InlineDateField creationDateFrom;

    @UiField("creationDateTo")
    InlineDateField creationDateTo;

    private String fromDate = StringUtils.EMPTY;

    private String toDate = StringUtils.EMPTY;
    
    private CustomTextField redemptionPeriodToDate = null;

    private CustomTextField redemptionPeriod = null;

    public RedemptionPeriod() {
        init();
    }

    public RedemptionPeriod(CustomTextField redemptionPeriod, CustomTextField redemptionPeriodToDate) {
        this.redemptionPeriod = redemptionPeriod;
        this.redemptionPeriodToDate = redemptionPeriodToDate;
        init();
    }

    private void init() {
        addToContent();
        configureFields();

    }

    private void addToContent() {
        setContent(Clara.create(getClass().getResourceAsStream("/ui/adjustment_details/RedemptionPeriod.xml"), this));
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        center();
        setClosable(true);
        setModal(true);
        setWidth("550px");
        setHeight("215px");
        setCaption("Date Selection");

    }

    private void configureFields() {
        LOGGER.debug("Entering configureFields in RedemptionPeriod");
        configureDateFields();
        submitButton();
        closeButton();
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    private Button closeButton() {
        closeBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().setData(null);
                close();
            }
        });
        return closeBtn;

    }

    private void submitButton() {
        submitBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                setFromDate(formatDate(creationDateFrom.getValue(), false));
                setToDate(formatDate(creationDateTo.getValue(), true));
                if (redemptionPeriod != null) {
                    redemptionPeriod.setValue(getFromDate());
                }
                if (redemptionPeriodToDate != null) {
                    redemptionPeriodToDate.setValue(getToDate());
                }
                close();
            }

            private String formatDate(Date date, boolean setAsLastDate) {
                final SimpleDateFormat format = new SimpleDateFormat(CommonUtils.MMDDYYYY);
                String formattedDate = StringUtils.EMPTY;
                if (!setAsLastDate) {
                    formattedDate = format.format(creationDateFrom.getValue());
                } else {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                    formattedDate = format.format(cal.getTime());
                }
                return formattedDate;
            }

        });
    }

    private void configureDateFields() {
        try {
            final SimpleDateFormat format = new SimpleDateFormat(CommonUtils.MMDDYYYY);
            if (redemptionPeriod != null && !StringUtils.EMPTY.equals(redemptionPeriod.getValue())) {
                Date tempFromDate = format.parse(redemptionPeriod.getValue());
                creationDateFrom.setValue(tempFromDate);
            }
            if (redemptionPeriodToDate != null && !StringUtils.EMPTY.equals(redemptionPeriodToDate.getValue())) {
                Date tempToDate = format.parse(redemptionPeriodToDate.getValue());
                creationDateTo.setValue(tempToDate);
            }
        } catch (ParseException ex) {
            LOGGER.error(ex.getMessage());
        }
        creationDateFrom.setResolution(Resolution.MONTH);
        creationDateTo.setResolution(Resolution.MONTH);
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    
    

}
