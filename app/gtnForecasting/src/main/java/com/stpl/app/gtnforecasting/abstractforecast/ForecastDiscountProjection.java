/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.abstractforecast;

import static com.stpl.app.utils.Constants.LabelConstants.ACCESS;
import static com.stpl.app.utils.Constants.LabelConstants.CONTRACT_PRICE;
import static com.stpl.app.utils.Constants.LabelConstants.DISCOUNT1;
import static com.stpl.app.utils.Constants.LabelConstants.DISCOUNT2;
import static com.stpl.app.utils.Constants.LabelConstants.METHODOLOGY;
import static com.stpl.app.utils.Constants.LabelConstants.PARITY_SETTINGS;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.utils.Constants.ButtonConstants.ALL;
import static com.stpl.app.utils.Constants.ButtonConstants.SELECT;
import static com.stpl.app.utils.Constants.CalendarConstants.CURRENT_YEAR;
import static com.stpl.app.utils.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.utils.Constants.CommonConstantsForChannels.HORIZONTAL;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.utils.Constants.LabelConstants.ACTUALS;
import static com.stpl.app.utils.Constants.LabelConstants.AMOUNT;
import static com.stpl.app.utils.Constants.LabelConstants.ASCENDING;
import static com.stpl.app.utils.Constants.LabelConstants.BOTH;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOM;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOMER;
import static com.stpl.app.utils.Constants.LabelConstants.DESCENDING;
import static com.stpl.app.utils.Constants.LabelConstants.DISABLE;
import static com.stpl.app.utils.Constants.LabelConstants.ENABLE;
import static com.stpl.app.utils.Constants.LabelConstants.INCREMENTAL;
import static com.stpl.app.utils.Constants.LabelConstants.OVERRIDE;
import static com.stpl.app.utils.Constants.LabelConstants.PERCENTAGE;
import static com.stpl.app.utils.Constants.LabelConstants.PRODUCT;
import static com.stpl.app.utils.Constants.LabelConstants.PROGRAM;
import static com.stpl.app.utils.Constants.LabelConstants.PROGRAM_CATEGORY;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTIONS;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author shyam.d
 */
public abstract class ForecastDiscountProjection extends CustomComponent implements View {

    protected SessionDTO session;
    protected String screenName;

    /* The Excel table */
    protected ExtFilterTreeTable excelTable = new ExtFilterTreeTable();
    /* The excel export image */
    protected final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    /**
     * The forecastTab VerticalLayout.
     */
    @UiField("forecastTabVlayout")
    protected VerticalLayout forecastTabVlayout;
    /**
     * The resultsTableLayout VerticalLayout.
     */
    @UiField("resultsTableLayout")
    protected VerticalLayout resultsTableLayout;
   
    /**
     * The viewHlayout HorizontalLayout.
     */
    @UiField("viewHlayout")
    protected HorizontalLayout viewHlayout;
    /**
     * The Mandated Panel.
     */
    @UiField("panelM")
    protected Panel panelM;
    /**
     * The Alternate History Panel.
     */
    @UiField("alternateHistoryPanel")
    protected Panel alternateHistoryPanel;
    /**
     * The Program Level Panel.
     */
    @UiField("programLevelPanel")
    protected Panel programLevelPanel;
    /**
     * The Non Mandated Panel.
     */
    @UiField("panelNM")
    protected Panel panelNM;
    /**
     * The massUpdate VerticalLayout.
     */
    @UiField("massUpdatePanel")
    protected Panel massUpdatePanel;
    /**
     * The forecastTab VerticalLayout.
     */
    @UiField("forecastAdjustmentVlayout")
    protected VerticalLayout forecastAdjustmentVlayout;
    /**
     * The Mass Update VerticalLayout.
     */
    @UiField("massUpdateVlayout")
    protected VerticalLayout massUpdateVlayout;
    /**
     * The TabSheet.
     */
    @UiField("tabSheet")
    protected TabSheet tabSheet;
    /**
     * The value TextField.
     */
    @UiField("value")
    protected TextField value;
    /**
     * The basis.
     */
    @UiField("adjustment")
    public TextField adjustment;
    /**
     * The value look up.
     */
    @UiField("valueLookUp")
    protected CustomTextField valueLookUp;
    /**
     * The contract look up.
     */
    @UiField("contract")
    protected CustomTextField contract;
    /**
     * The brand look up.
     */
    @UiField("brand")
    protected CustomTextField brand;
    /**
     * The value optionGroup.
     */
    @UiField("valueOption")
    protected OptionGroup valueOption;

    @UiField("yearSelection")
    protected ComboBox yearSelection;
    /**
     * The history Ddlb.
     */
    @UiField("valueDdlb")
    protected ComboBox valueDdlb;
    /**
     * The Alloc Methodology Ddlb.
     */
    @UiField("allocMethodology")
    protected ComboBox allocMethodology;
    
    /**
     * The periodOrder OptionGroup.
     */
    @UiField("level")
    protected OptionGroup level;


    @UiField("adjprogramsLb")
    protected Label adjprogramsLb;
    /**
     * The adjprograms OptionGroup.
     */
    @UiField("lblStart")
    protected Label lblStart;
    /**
     * The adjprograms OptionGroup.
     */
    @UiField("lblEnd")
    protected Label lblEnd;
    /**
     * The groupFilterLb Label.
     */
    @UiField("groupFilterLb")
    protected Label groupFilterLb;

    @UiField("variablesForMandated")
    public OptionGroup variablesForMandated;
    /**
     * The programSelection ComboBox.
     */
    @UiField("programSelection")
    protected ComboBox programSelection;
    /**
     * The fieldDdlb ComboBox.
     */
    @UiField("fieldDdlb")
    protected ComboBox fieldDdlb;
    /**
     * The endPeriod ComboBox.
     */
    @UiField("endPeriod")
    protected ComboBox endPeriod;
    /**
     * The startPeriod ComboBox.
     */
    @UiField("startPeriod")
    protected ComboBox startPeriod;
   
    @UiField("levelDdlb")
    protected ComboBox levelDdlb;
    /**
     * The levelFilterDdlb ComboBox.
     */
    @UiField("levelFilterDdlb")
    protected ComboBox levelFilterDdlb;
    /**
     * The methodologyDdlb ComboBox.
     */
    @UiField("methodologyDdlb")
    protected ComboBox methodologyDdlb;
    /**
     * The viewDdlb ComboBox.
     */
    @UiField("viewDdlb")
    protected ComboBox viewDdlb;
    /**
     * The group filter ddlb.
     */
    @UiField("groupFilterDdlb")
    public ComboBox groupFilterDdlb;
    /**
     * The type OptionGroup.
     */
    @UiField("type")
    protected OptionGroup type;
    /**
     * The basis OptionGroup.
     */
    @UiField("basis")
    protected OptionGroup basis;
    /**
     * The adjprograms OptionGroup.
     */
    @UiField("adjprograms")
    protected OptionGroup adjprograms;
    /**
     * The adjperiods OptionGroup.
     */
    @UiField("adjperiods")
    protected OptionGroup adjperiods;
    /**
     * The adjperiods OptionGroup.
     */
    @UiField("view")
    protected OptionGroup view;
    /**
     * The massCheck OptionGroup.
     */
    @UiField(MASS_CHECK)
    protected OptionGroup massCheck;
    /**
     * The programSelectionlookup Button.
     */
    @UiField("programSelectionLookup")
    protected Button programSelectionLookup;
    /**
     * The generate Button.
     */
    @UiField("generateBtn")
    protected Button generateBtn;
    /**
     * The editBtn Button.
     */
    @UiField("editBtn")
    protected Button editBtn;
    /**
     * The newBtn Button.
     */
    @UiField("newBtn")
    protected Button newBtn;
    /**
     * The excelExport Button.
     */
    @UiField("excelExport")
    protected Button excelExport;
    /**
     * The refresh Button.
     */
    @UiField("refreshBtn")
    protected Button refreshBtn;
    /**
     * The altHistory Button.
     */
    @UiField("altHistoryBtn")
    protected Button altHistoryBtn;
    /**
     * The reset Button.
     */
    @UiField("resetBtnForTable")
    protected Button resetBtnForTable;
    /**
     * The reset Button.
     */
    @UiField("resetBtn")
    protected Button resetBtn;
    /**
     * The expandBtn Button.
     */
    @UiField("expandBtn")
    protected Button expandBtn;
    /**
     * The collapseBtn Button.
     */
    @UiField("collapseBtn")
    protected Button collapseBtn;
    /**
     * The adjustBtn Button.
     */
    @UiField("adjustBtn")
    protected Button adjustBtn;
    /**
     * The populate Button.
     */
    @UiField("populateBtn")
    protected Button populateBtn;
    /**
     * The calculateBtn Button.
     */
    @UiField("calculateBtn")
    protected Button calculateBtn;

    public Label frequency = new Label();
    public ComboBox frequencyDdlb = new ComboBox();
    public Label actualsProjections = new Label();
    public OptionGroup actualsProjs = new OptionGroup();
    public Label projectionPeriodOrder = new Label();
    public OptionGroup periodOrder = new OptionGroup();
    public Label history = new Label();
    public ComboBox historyDdlb = new ComboBox();
    public Label variablesLb = new Label();
    public OptionGroup variables = new OptionGroup();
    public Label projTypeLb = new Label();
    public OptionGroup projType = new OptionGroup();
    public Label discTypeLb = new Label();
    public OptionGroup discType = new OptionGroup();

    /**
     * The bean for loading Start Period drop down.
     */
    final protected BeanItemContainer<String> startPeriodBean = new BeanItemContainer<>(String.class);
    /**
     * The bean for loading End Period drop down.
     */
    final protected BeanItemContainer<String> endPeriodBean = new BeanItemContainer<>(String.class);
    /**
     * The bean for loading Start Period drop down.
     */
    final protected BeanItemContainer<String> forecaststartBean = new BeanItemContainer<>(String.class);
    /**
     * The bean for loading End Period drop down.
     */
    final protected BeanItemContainer<String> forecastendBean = new BeanItemContainer<>(String.class);
    final protected Property.ValueChangeListener propertyVlaueChangeListener = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            propertyVlaueChange(event);
        }
    };
    final protected Button.ClickListener buttonClickListener = new Button.ClickListener() {
        @Override
        public void buttonClick(Button.ClickEvent event) {
            buttonClickEvent(event);
        }
    };

    public ForecastDiscountProjection(SessionDTO session, String screenName)  {
        this.session = session;
        this.screenName = screenName;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/abstractforecast/forecastDiscountProjection.xml"), this));
        configureFields();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void configureFields() {
        tabSheet.addTab(massUpdateVlayout, "Mass Update");
        tabSheet.addTab(forecastTabVlayout, "Forecast");
        tabSheet.addTab(forecastAdjustmentVlayout, "Adjustment");
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

        frequencyDdlb.addItem(SELECT_ONE.getConstant());
        frequencyDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        frequencyDdlb.addItem(MONTHLY.getConstant());
        frequencyDdlb.addItem(QUARTERLY.getConstant());
        frequencyDdlb.addItem(SEMI_ANNUALLY.getConstant());
        frequencyDdlb.addItem(ANNUALLY.getConstant());
        frequencyDdlb.setValue(QUARTERLY.getConstant());
        frequencyDdlb.focus();

        loadFrequency(QUARTERLY.getConstant());
        historyDdlb.setNullSelectionAllowed(false);
        historyDdlb.setValue("4");
        frequencyDdlb.setData("frequencyDdlb");

        periodOrder.addItem(ASCENDING.getConstant());
        periodOrder.addItem(DESCENDING.getConstant());
        periodOrder.select(ASCENDING.getConstant());

        actualsProjs.addItem(ACTUALS.getConstant());
        actualsProjs.addItem(PROJECTIONS.getConstant());
        actualsProjs.addItem(BOTH.getConstant());
        actualsProjs.select(ACTUALS.getConstant());

        level.addItem(PROGRAM_CATEGORY.getConstant());
        level.addItem(PROGRAM.getConstant());
        level.select(PROGRAM_CATEGORY.getConstant());

        type.addItem(INCREMENTAL);
        type.addItem(OVERRIDE);
        type.select(INCREMENTAL);

        basis.addItem(AMOUNT);
        basis.addItem(PERCENTAGE);
        basis.select(AMOUNT);

        adjprograms.addItem(ALL);
        adjprograms.addItem(SELECT);
        adjprograms.select(ALL);

        adjperiods.addItem(ALL);
        adjperiods.addItem(SELECT);
        adjperiods.select(ALL);

        view.addItem(CUSTOMER.getConstant());
        view.addItem(PRODUCT.getConstant());
        view.addItem(CUSTOM.getConstant());
        view.select(CUSTOMER.getConstant());

        massCheck.addItem(ENABLE.getConstant());
        massCheck.addItem(DISABLE.getConstant());
        massCheck.addStyleName(HORIZONTAL.getConstant());

        massCheck.select(DISABLE.getConstant());

        populateBtn.setEnabled(false);

        fieldDdlb.addItem(SELECT_ONE.getConstant());
        fieldDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        levelDdlb.addItem(SELECT_ONE.getConstant());
        levelDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        levelDdlb.setValue(SELECT_ONE.getConstant());
        levelDdlb.addStyleName(Constant.POPUPCONTENTCOMBOSIZE);
        levelDdlb.setImmediate(true);
        levelFilterDdlb.addStyleName(Constant.POPUPCONTENTCOMBOSIZE);
        levelFilterDdlb.setImmediate(true);
        levelFilterDdlb.addItem(SELECT_ONE.getConstant());
        levelFilterDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        levelFilterDdlb.setValue(SELECT_ONE.getConstant());

        viewDdlb.addItem(SELECT_ONE.getConstant());
        viewDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        viewDdlb.setValue(SELECT_ONE.getConstant());
        viewDdlb.setEnabled(false);
        viewDdlb.setData("viewDdlb");

        editBtn.setEnabled(false);
        newBtn.setEnabled(false);

        excelExport.setIcon(excelExportImage);
        excelTable.setVisible(false);

        view.setData("view");

        adjprograms.setData("adjprograms");

        adjperiods.setData("adjperiods");

        massCheck.setData(MASS_CHECK);

        startPeriod.setData("startPeriod");

        levelFilterDdlb.setData("levelFilterDdlb");

        calculateBtn.setData("calculateBtn");
        calculateBtn.addClickListener(buttonClickListener);
        populateBtn.setData("populateBtn");
        populateBtn.addClickListener(buttonClickListener);
        excelExport.setData("excelExport");
        excelExport.addClickListener(buttonClickListener);
        generateBtn.setData("generateBtn");
        generateBtn.addClickListener(buttonClickListener);
        resetBtn.setData("resetBtn");
        resetBtn.addClickListener(buttonClickListener);
        resetBtnForTable.setData("resetBtnForTable");
        resetBtnForTable.addClickListener(buttonClickListener);
        editBtn.setData("editBtn");
        editBtn.addClickListener(buttonClickListener);
        newBtn.setData("newBtn");
        newBtn.addClickListener(buttonClickListener);
        adjustBtn.setData("adjustBtn");
        adjustBtn.addClickListener(buttonClickListener);
        expandBtn.setData("expandBtn");
        expandBtn.addClickListener(buttonClickListener);
        collapseBtn.setData("collapseBtn");
        collapseBtn.addClickListener(buttonClickListener);

        screenLoad();

            }
    public static final String MASS_CHECK = "massCheck";

    private void screenLoad() {
        switch (screenName) {
            case CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED:
                mandatedScreenLoad();
                break;
            case CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED:
                nonMandatedScreenLoad();
                break;
                           
            }
            }

    private void nonMandatedScreenLoad() {
        projType.setVisible(false);
        projTypeLb.setVisible(false);
        discType.setVisible(false);
        discTypeLb.setVisible(false);
        alternateHistoryPanel.setVisible(false);
        panelM.setVisible(false);
        massUpdatePanel.setVisible(false);

        altHistoryBtn.setVisible(true);
        refreshBtn.setVisible(true);
        resetBtnForTable.setVisible(true);
            }

    private void mandatedScreenLoad() {

            panelNM.setVisible(false);
            altHistoryBtn.setVisible(false);
            refreshBtn.setVisible(false);
            resetBtnForTable.setVisible(false);
            viewHlayout.setVisible(false);
        tabSheet.setVisible(false);
        value.setVisible(false);

        panelM.setVisible(true);
            valueDdlb.setVisible(true);
        massUpdatePanel.setVisible(true);


            massUpdatePanel.setContent(massUpdateVlayout);

            variablesForMandated.addStyleName(Constant.HORIZONTAL);
            variablesForMandated.addStyleName(Constant.OPTION_GROUP_WIDTH);
            variablesForMandated.addItem(METHODOLOGY.getConstant());
            variablesForMandated.addItem(CONTRACT_PRICE.getConstant());
            variablesForMandated.addItem(DISCOUNT1.getConstant());
            variablesForMandated.addItem(DISCOUNT2.getConstant());
            variablesForMandated.addItem(ACCESS.getConstant());
            variablesForMandated.addItem(PARITY_SETTINGS.getConstant());
            variablesForMandated.setImmediate(true);
            massCheck.setData(MASS_CHECK);
            massCheck.addValueChangeListener(propertyVlaueChangeListener);
        }

    private void loadFrequency(String frequency) {
        CommonUtils.frequenceValueChange(frequency, historyDdlb, session);
    }

    public List<String> loadYearSelection() {
        List<String> year = new ArrayList();
        Calendar historyCalendar = Calendar.getInstance();
        historyCalendar.setTime(session.getForecastDTO().getHistoryStartDate());

        Calendar projectionCalendar = Calendar.getInstance();
        projectionCalendar.setTime(session.getToDate());

        int yearToAdd = historyCalendar.get(Calendar.YEAR);
        int historyYearCount = CURRENT_YEAR.getConstant() - historyCalendar.get(Calendar.YEAR);
        int projectionYearCount = projectionCalendar.get(Calendar.YEAR) - CURRENT_YEAR.getConstant();
        year.add(SELECT_ONE.getConstant());
        year.add(ALL.getConstant());
        for (int i = 0; i <= (projectionYearCount + historyYearCount); i++, yearToAdd++) {
            year.add(String.valueOf(yearToAdd));
        }
        return year;
    }

    @UiHandler("viewDdlb")
    public void customDdlbChangeOption(Property.ValueChangeEvent event) {
        customDdlbLogic();
    }

    /**
     * To set selection in edit and view mode
     *
     */
    protected abstract void customDdlbLogic();

    /**
     * To set selection in edit and view mode
     *
     */
    protected abstract void viewValueChangeLogic();

    protected abstract void adjProgramsValueChangeLogic(String adjustmentProgram);

    protected abstract void adjPeriodValueChangeLogic(String adjustmentPeriods);

    protected abstract void populateBtnClickLogic();

    protected abstract void calculateBtnClickLogic();

    protected abstract void newBtnClickLogic();

    protected abstract void editBtnClickLogic();

    protected abstract void adjustBtnClickLogic();

    protected abstract void excelExportClickLogic();

    protected abstract void levelFilterValueChangeLogic(Property.ValueChangeEvent event);

    protected abstract void generateBtnClickLogic(Boolean isGenerate);

    protected abstract void resetBtnClickLogic();

    protected abstract void expandBtnClickLogic();

    protected abstract void collapseBtnClickLogic();

    protected abstract void massCheckValueChangeLogic(Property.ValueChangeEvent event);

    protected abstract void startPeriodValueChangeLogic(Property.ValueChangeEvent event);

    protected abstract void resetBtnForTableLogic();

    protected abstract void fieldDdlbValueChangeLogic(Property.ValueChangeEvent event);

    public void removePropertyValueChangeListeners(AbstractField... field) {
        if (field != null && field.length != 0) {
            for (AbstractField abstractField : field) {
                removePropertyValueChangeListener(abstractField);
}
        }
    }

    public void addPropertyValueChangeListeners(AbstractField... field) {
        if (field != null && field.length != 0) {
            for (AbstractField abstractField : field) {
                addPropertyValueChangeListener(abstractField);
            }
        }
    }

    public void removePropertyValueChangeListener(AbstractField field) {
        field.removeValueChangeListener(propertyVlaueChangeListener);
    }

    public void addPropertyValueChangeListener(AbstractField field) {
        field.addValueChangeListener(propertyVlaueChangeListener);
    }

    protected void propertyVlaueChange(Property.ValueChangeEvent event) {
        if (event.getProperty() instanceof AbstractComponent) {
            AbstractComponent field = (AbstractComponent) (event.getProperty());
            String data = String.valueOf(field.getData());
           
            switch (data) {
                case "view":
                    viewValueChangeLogic();
                    break;
                case "adjprograms":
                    adjProgramsValueChangeLogic(String.valueOf(event.getProperty().getValue()));
                    break;
                case "adjperiods":
                    adjPeriodValueChangeLogic(String.valueOf(event.getProperty().getValue()));
                    break;
                case "frequencyDdlb":
                    loadFrequency(String.valueOf(event.getProperty().getValue()));
                    break;
                case "levelFilterDdlb":
                    levelFilterValueChangeLogic(event);
                    break;
                case MASS_CHECK:
                    massCheckValueChangeLogic(event);
                    break;
                case "startPeriod":
                    startPeriodValueChangeLogic(event);
                    break;
                case "viewDdlb":
                    customDdlbLogic();
                    break;
                case "fieldDdlb":
                    fieldDdlbValueChangeLogic(event);
                    break;
               
            }

        }


    }

    protected void buttonClickEvent(Button.ClickEvent event) {
        String data = String.valueOf(event.getButton().getData());
        switch (data) {
            case "calculateBtn":
                calculateBtnClickLogic();
                break;
            case "populateBtn":
                populateBtnClickLogic();
                break;
            case "excelExport":
                excelExportClickLogic();
                break;
            case "generateBtn":
                generateBtnClickLogic(true);
                break;
            case "resetBtn":
                resetBtnClickLogic();
                break;
            case "resetBtnForTable":
                resetBtnForTableLogic();
                break;
            case "editBtn":
                editBtnClickLogic();
                break;
            case "newBtn":
                newBtnClickLogic();
                break;
            case "adjustBtn":
                adjustBtnClickLogic();
                break;
            case "expandBtn":
                expandBtnClickLogic();
                break;
            case "collapseBtn":
                collapseBtnClickLogic();
                break;
           
        }
    }
}
