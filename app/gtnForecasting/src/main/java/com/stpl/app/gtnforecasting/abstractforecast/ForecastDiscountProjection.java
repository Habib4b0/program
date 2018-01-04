/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.abstractforecast;

import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
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
import static com.stpl.app.utils.Constants.LabelConstants.ACCESS;
import static com.stpl.app.utils.Constants.LabelConstants.ACTUALS;
import static com.stpl.app.utils.Constants.LabelConstants.AMOUNT;
import static com.stpl.app.utils.Constants.LabelConstants.ASCENDING;
import static com.stpl.app.utils.Constants.LabelConstants.BOTH;
import static com.stpl.app.utils.Constants.LabelConstants.CONTRACT_PRICE;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOM;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOMER;
import static com.stpl.app.utils.Constants.LabelConstants.DESCENDING;
import static com.stpl.app.utils.Constants.LabelConstants.DISABLE;
import static com.stpl.app.utils.Constants.LabelConstants.DISCOUNT1;
import static com.stpl.app.utils.Constants.LabelConstants.DISCOUNT2;
import static com.stpl.app.utils.Constants.LabelConstants.ENABLE;
import static com.stpl.app.utils.Constants.LabelConstants.INCREMENTAL;
import static com.stpl.app.utils.Constants.LabelConstants.METHODOLOGY;
import static com.stpl.app.utils.Constants.LabelConstants.OVERRIDE;
import static com.stpl.app.utils.Constants.LabelConstants.PARITY_SETTINGS;
import static com.stpl.app.utils.Constants.LabelConstants.PERCENTAGE;
import static com.stpl.app.utils.Constants.LabelConstants.PRODUCT;
import static com.stpl.app.utils.Constants.LabelConstants.PROGRAM;
import static com.stpl.app.utils.Constants.LabelConstants.PROGRAM_CATEGORY;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTIONS;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    protected static final Logger LOGGER = LoggerFactory.getLogger(ForecastDiscountProjection.class);
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
    protected TextField adjustment;
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
    
    @UiField("adjprograms")
    protected OptionGroup adjprograms;
    
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
    protected OptionGroup variablesForMandated;
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
    protected ComboBox groupFilterDdlb;
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
     * The disountPanel Button.
     */
    @UiField("disountPanel")
    protected Panel disountPanel;
    /**
     * The customerlevelDdlb Button.
     */
    @UiField("customerlevelDdlb")
    protected ComboBox customerlevelDdlb;
    /**
     * The customerlevelDdlb Button.
     */
    @UiField("productlevelDdlb")
    protected ComboBox productlevelDdlb;
    /**
     * The deductionlevelDdlb Button.
     */
    @UiField("deductionlevelDdlb")
    protected ComboBox deductionlevelDdlb;
    /**
     * The deductionInclusionDdlb Button.
     */
    @UiField("deductionInclusionDdlb")
    protected CustomMenuBar deductionInclusionDdlb;
    /**
     * The customerFilterDdlb Button.
     */
    @UiField("customerFilterDdlb")
    protected CustomMenuBar customerFilterDdlb;
    /**
     * The productFilterDdlb Button.
     */
    @UiField("productFilterDdlb")
    protected CustomMenuBar productFilterDdlb;
    /**
     * The deductionFilterDdlb Button.
     */
    @UiField("deductionFilterDdlb")
    protected CustomMenuBar deductionFilterDdlb;
    /**
     * discountSelection HorizontalLayout.
     */
    @UiField("discountSelection")
    protected HorizontalLayout discountSelection;
    /**
     * HorizontalLayout.
     */
    @UiField("discountProjectionfilterLayout")
    protected HorizontalLayout discountProjectionfilterLayout;
    /**
     * HorizontalLayout.
     */
    @UiField("tabsheetDiscount")
    protected TabSheet tabsheetDiscount;
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

    protected Label frequency = new Label();
    protected ComboBox frequencyDdlb = new ComboBox();
    protected Label actualsProjections = new Label();
    protected OptionGroup actualsProjs = new OptionGroup();
    protected Label projectionPeriodOrder = new Label();
    protected OptionGroup periodOrder = new OptionGroup();
    protected Label history = new Label();
    protected ComboBox historyDdlb = new ComboBox();
    protected Label variablesLb = new Label();
    protected OptionGroup variables = new OptionGroup();
    protected Label projTypeLb = new Label();
    protected OptionGroup projType = new OptionGroup();
    protected Label discTypeLb = new Label();
    protected OptionGroup discType = new OptionGroup();
    protected Label uomLb = new Label("Unit Of Measure:");
    protected ComboBox uomDdlb = new ComboBox();
    protected Label displayFormatLabel = new Label("Display Format:");
    protected CustomMenuBar displayFormatDdlb = new CustomMenuBar();
    protected Label conversionFactor = new Label("Deduction Conversion:");
    protected ComboBox conversionFactorDdlb = new ComboBox();
    protected CustomMenuBar.CustomMenuItem deductionInclusionValues;
    protected CustomMenuBar.CustomMenuItem customerFilterValues;
    protected CustomMenuBar.CustomMenuItem productFilterValues;
    protected CustomMenuBar.CustomMenuItem deductionFilterValues;
    protected CustomMenuBar.CustomMenuItem displayFormatValues;

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
        
        if (CommonUtil.isValueEligibleForLoading()) {
            disountPanel.setVisible(false);
            tabsheetDiscount.addTab(discountSelection, "Display Selection");
            tabsheetDiscount.addTab(discountProjectionfilterLayout, "Filter Option");
            tabsheetDiscount.addStyleName(ValoTheme.TABSHEET_FRAMED);
            tabsheetDiscount.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
            programLevelPanel.setVisible(false);
            panelNM.setVisible(false);
        } else {
            discountSelection.setVisible(false);
            discountProjectionfilterLayout.setVisible(false);
        }
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
        if (CUSTOM.getConstant().equals(String.valueOf(view.getValue()))) {
            adjprogramsLb.setVisible(false);
            adjprograms.setVisible(false);
        } else {
            adjprogramsLb.setVisible(false);
            adjprograms.setVisible(false);
        }

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
            default:
                LOGGER.warn("screenName is not valid: " + screenName);
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
    
    protected abstract void customCalculateBtnClickLogic();

    protected abstract void newBtnClickLogic();

    protected abstract void editBtnClickLogic();

    protected abstract void adjustBtnClickLogic();

    protected abstract void adjustBtnClickLogicCustom();
    
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
                default:
                LOGGER.warn("data is not valid: " + data);
                break;
               
            }

        }


    }

    protected void buttonClickEvent(Button.ClickEvent event) {
        String data = String.valueOf(event.getButton().getData());
        switch (data) {
            case "calculateBtn":
                if (CUSTOM.getConstant().equals(String.valueOf(view.getValue())) && CommonUtil.isValueEligibleForLoading()) {
                    customCalculateBtnClickLogic();
                } else {
                    calculateBtnClickLogic();
                }
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
                if (CUSTOM.getConstant().equals(String.valueOf(view.getValue())) && CommonUtil.isValueEligibleForLoading()) {
                    adjustBtnClickLogicCustom();
                } else {
                    adjustBtnClickLogic();
                }
                break;
            case "expandBtn":
                expandBtnClickLogic();
                break;
            case "collapseBtn":
                collapseBtnClickLogic();
                break;
            default:
                LOGGER.warn("data is not valid: " + data);
                break;
           
        }
    }
}
