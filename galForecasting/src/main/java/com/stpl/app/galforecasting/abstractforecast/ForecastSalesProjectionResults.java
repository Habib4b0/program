/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.abstractforecast;

import com.stpl.app.galforecasting.utils.Constant;
import com.vaadin.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author shrihariharan
 */
public abstract class ForecastSalesProjectionResults extends CustomComponent implements View {
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger
            .getLogger(ForecastSalesProjectionResults.class);
    /**
     * The frequency.
     */
    @UiField("frequency")
    protected ComboBox frequency;
    /**
     * The SALES_SMALL or UNITS_SMALL.
     */
    @UiField("salesOrUnits")
    protected OptionGroup salesOrUnits;
    /**
     * The period order.
     */
    @UiField("periodOrder")
    protected OptionGroup periodOrder;
    /**
     * The actual or proj.
     */
    @UiField("actualOrProj")
    protected OptionGroup actualOrProj;
    /**
     * The pivot view.
     */
    @UiField("pivotView")
    protected OptionGroup pivotView;
    /**
     * The view.
     */
    @UiField(Constant.VIEW)
    protected OptionGroup view;
    /**
     * The excel btn.
     */
    @UiField("excelBtn")
    protected Button excelBtn;
    /**
     * The graph btn.
     */
    @UiField("graphBtn")
    protected Button graphBtn;
    /**
     * The history.
     */
    @UiField("history")
    protected ComboBox history;
    /**
     * The level.
     */
    @UiField("level")
    protected ComboBox level;
    /**
     * The level filter.
     */
    @UiField("levelFilter")
    protected ComboBox levelFilter;
    /**
     * The custom ddlb.
     */
    @UiField("customDdlb")
    protected ComboBox customDdlb;
    /**
     * The reset btn.
     */
    @UiField("resetBtn")
    protected Button resetBtn;
    /**
     * Custom View New button
     */
    @UiField("newBtn")
    protected Button newBtn;
    /**
     * Custom View Edit Button
     */
    @UiField("editBtn")
    protected Button editBtn;
    /**
     * The generate btn.
     */
    @UiField("generateBtn")
    protected Button generateBtn;
    /**
     * Expand Button
     */
    @UiField("expandBtn")
    protected Button expandBtn;
    /**
     * Collapse Button
     */
    @UiField("collapseBtn")
    protected Button collapseBtn;
  
    /**
     * pivot View panel
     */
    @UiField("pivotViewPanel")
    protected Panel panelpivot;
    /**
     * Grid Panel
     */
    @UiField("tableVerticalLayout")
    protected VerticalLayout layout;
    
    /**
     * Constructor
     *
     * @param session
     * @param screenName
     */
    public ForecastSalesProjectionResults() {
     
        VerticalLayout layout = new VerticalLayout();
            layout.addComponent(Clara.create(getClass().getResourceAsStream("/salesProjectionResults.xml"), this));
            Panel panel = new Panel();
            panel.setContent(layout);
            setCompositionRoot(panel);
            configureField();
        
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
    public void configureField(){
    level.addStyleName(Constant.POPUPCONTENTCOMBOSIZE);
    level.setImmediate(true);
    levelFilter.addStyleName(Constant.POPUPCONTENTCOMBOSIZE);
    levelFilter.setImmediate(true);       
    }
    
    @UiHandler("frequency")
    public void loadFrequencyddlb(Property.ValueChangeEvent event) {
        LOGGER.info("Entering Load Frequency");
        loadFrequency();
        LOGGER.info("Ending Load Frequency");
    }
    
    /**
     * Reset button logic.
     *
     * @param event the event
     */
    @UiHandler("resetBtn")
    public void resetButton(Button.ClickEvent event) {
        LOGGER.info("Entering resetButton");
        resetButtonLogic();
        LOGGER.info("Ending resetButton");
    }
    /**
     * Generate button logic.
     *
     * @param event the event
     */
    @UiHandler("generateBtn")
    public void generateButton(Button.ClickEvent event) {
        LOGGER.info("Entering generateButton");
        generateButtonLogic();
        LOGGER.info("Ending generateButton");
    }
    
    @UiHandler("newBtn")
    public void newCustomHierarchhy(Button.ClickEvent event) {
        LOGGER.info("Entering newCustomHierarchhy");
        newCustomHierarchyLogic();
        LOGGER.info("Ending newCustomHierarchhy");
    }
    
    @UiHandler("excelBtn")
    public void excelButton(Button.ClickEvent event) {
        LOGGER.info("Entering excelButton");
        excelButtonLogic();
        LOGGER.info("Ending excelButton");
    }
    
    @UiHandler("editBtn")
    public void editHierarchyBtn(Button.ClickEvent event) {
        LOGGER.info("Entering editHierarchyBtn");
        editHierarchyBtnLogic();
        LOGGER.info("Ending editHierarchyBtn");
    }
    @UiHandler("customDdlb")
    public void customDdlbChangeOption(Property.ValueChangeEvent event) {
        LOGGER.info("Entering customDdlbChangeOption");
        customDdlbChangeOptionLogic();
        LOGGER.info("Ending customDdlbChangeOption");
    }
    @UiHandler("graphBtn")
    public void graphExport(Button.ClickEvent event) {
        LOGGER.info("Entering graphExport");
        graphExportLogics();
        LOGGER.info("Ending graphExport");
    }
    @UiHandler("expandBtn")
    public void expandButton(Button.ClickEvent event) {
        LOGGER.info("Entering expandButton");
        expandButtonLogic();
        LOGGER.info("Ending expandButton");
    }
    @UiHandler("collapseBtn")
    public void collapseButton(Button.ClickEvent event) {
        LOGGER.info("Entering collapseButtonlogic");
        collapseButtonLogic();
        LOGGER.info("Ending collapseButtonlogic");
    }
    
    protected abstract void resetButtonLogic();
    
    protected abstract void loadFrequency();
    
    protected abstract void generateButtonLogic();
    
    protected abstract void newCustomHierarchyLogic();
    
    protected abstract void excelButtonLogic();
    
    protected abstract void editHierarchyBtnLogic();
    
    protected abstract void customDdlbChangeOptionLogic();
    
    protected abstract void graphExportLogics();
    
    protected abstract void expandButtonLogic();
    
    protected abstract void collapseButtonLogic();
    
}
